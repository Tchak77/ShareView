package shapes;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

/**
 * Created by Kevin on 05/05/2016.
 */
public class Ellipse {

    private int X;
    private int Y;
    private int height;
    private int width;
    private int color;

    public Ellipse(int X, int Y, int height, int width, int color){
        this.X = X;
        this.Y = Y;
        this.height = height;
        this.width = width;
        this.color = color;
    }

    public Ellipse(int X, int Y){
        this.X = X;
        this.Y = Y;
        this.height = 100;
        this.width = 200;
        color = Color.BLUE;
    }


    public void drawEllipse(Canvas canvas){
        Paint p = new Paint();
        p.setColor(color);
        canvas.drawOval(new RectF(X , Y  , X + width, Y + height), p);
    }



}
