package ir2.esipe.shareview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;


public class SheetView extends View {


    public int OVAL_HEIGHT = 200;
    public int OVAL_WIDTH = 100;

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

    private List<Point> shapeCoodinates = new ArrayList<Point>();

    public boolean onTouchEvent(MotionEvent event){
        int action = event.getActionMasked();
        switch (action){
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                shapeCoodinates.add(new Point((int) event.getX(), (int) event.getY()));
                invalidate();
                break;
        }
        return super.onTouchEvent(event);
    }

    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        for(Point p: shapeCoodinates){
            canvas.drawOval(new RectF(p.x - OVAL_WIDTH/2 , p.y - OVAL_HEIGHT/2 , p.x + OVAL_WIDTH/2, p.y + OVAL_HEIGHT/2), paint);
        }
    }


}