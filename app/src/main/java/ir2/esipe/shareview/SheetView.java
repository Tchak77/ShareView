package ir2.esipe.shareview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

import shapes.Ellipse;
import shapes.ShapesManager;


public class SheetView extends View {

    public SheetView(Context context) {
        super(context);
    }

    public SheetView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SheetView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public SheetView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private ShapesManager shapesManager = new ShapesManager();
    private ScaleGestureDetector mScaleDetector = new ScaleGestureDetector(getContext(), new ScaleListener());
    private float oldDist = 1;
    private float newDist = 1;
    private float scale = 1;

    public boolean onTouchEvent(MotionEvent event){
        int action = event.getActionIndex();
        mScaleDetector.onTouchEvent(event);
        //Indique le nombre de doigts sur l'écran
        int nbTouch = event.getPointerCount();
        Log.v(SheetView.class.getSimpleName(), "Touch:"+nbTouch);


        switch (action){
            case MotionEvent.ACTION_DOWN:
                shapesManager.addEllipse(new Ellipse((int)event.getX(), (int)event.getY()));
                invalidate();
                break;
        }
        return true;
    }

    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        canvas.save();
        canvas.scale(scale,scale);
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        shapesManager.drawEllipses(canvas);

        paint.setColor(Color.GREEN);
        shapesManager.drawRectangles(canvas);
        canvas.restore();
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {

            scale *= detector.getScaleFactor();
            // Don't let the object get too small or too large.
            scale = Math.max(0.1f, Math.min(scale, 5.0f));

            invalidate();
            return true;
        }
    }
}