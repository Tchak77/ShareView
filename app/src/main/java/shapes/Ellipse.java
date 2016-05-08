package shapes;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

/**
 * Created by Kevin on 05/05/2016.
 */
public class Ellipse implements Shape {

    private int X;
    private int Y;
    private int height;
    private int width;
    private int color;

    public Ellipse(int X, int Y, int width, int height, int color){
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


    public void draw(Canvas canvas){
        Paint p = new Paint();
        p.setColor(color);
        canvas.drawOval(new RectF(X , Y  , X + width, Y + height), p);
    }

    @Override
    public String toJSON() {
        String str = "";

        str += "{\"draw\": { \"shape\": \"ellipse\", \"center\":["+X+","+Y+"], \"radius\": ["+width+","+height+"] } }";
        return str;
    }


}
