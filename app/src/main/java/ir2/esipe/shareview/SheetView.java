package ir2.esipe.shareview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

import shapes.ShapesManager;


public class SheetView extends View {

    private ShapesManager shapesManager = ShapesManager.getSingleton();
    private ScaleGestureDetector mScaleDetector = new ScaleGestureDetector(getContext(), new ScaleListener());

    private float upperLeftX = 0;
    private float upperLeftY = 0;

    private float scale = 1;

    public SheetView(Context context) {
        super(context);

    }

    public SheetView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SheetView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public boolean onTouchEvent(MotionEvent event){

        int action = event.getAction();
        mScaleDetector.onTouchEvent(event);
        //Indique le nombre de doigts sur l'écran
        int nbTouch = event.getPointerCount();
        Log.v(SheetView.class.getSimpleName(), "Touch:"+nbTouch);


        switch (action){
            case MotionEvent.ACTION_DOWN:
                upperLeftX = event.getX();
                upperLeftY = event.getY();
                return true;

            case MotionEvent.ACTION_UP:
                shapesManager.addShape(shapesManager.createShape((int)upperLeftX, (int)upperLeftY, (int)(event.getX() - upperLeftX), (int)(event.getY() - upperLeftY),  Color.GREEN));
                invalidate();
                return true;
            default:
                return true;
        }

    }






    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        canvas.save();
        canvas.scale(scale,scale);
        shapesManager.drawShapes(canvas);
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