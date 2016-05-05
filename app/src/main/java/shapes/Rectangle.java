package shapes;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

/**
 * Created by Kevin on 05/05/2016.
 */
public class Rectangle {

    private int X;
    private int Y;
    private int width;
    private int height;
    private int color;


    public Rectangle(int x, int y, int width, int height) {
        X = x;
        Y = y;
        this.width = width;
        this.height = height;
    }


    public Rectangle(int x, int y) {
        X = x;
        Y = y;
        this.height = 100;
        this.width = 200;
    }


    public void drawRectangle(Canvas canvas){
        Paint p = new Paint();
        p.setColor(color);
        canvas.drawRect(new RectF(X , Y, X+width, Y+height), p);
    }
}
