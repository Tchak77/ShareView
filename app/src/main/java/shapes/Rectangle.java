package shapes;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

/**
 * Created by Kevin on 05/05/2016.
 */
public class Rectangle implements Shape {

    private int X;
    private int Y;
    private int width;
    private int height;
    private int color;


    public Rectangle(int x, int y, int width, int height, int color) {

        if(width < 0){
            X = x+width;
            this.width = -width;
        } else {
            X = x;
            this.width = width;
        }
        if(height < 0){
            Y = y+height;
            this.height = -height;
        } else {
            Y = y;
            this.height = height;
        }
        this.color = color;
    }



    public void draw(Canvas canvas){
        Paint p = new Paint();
        p.setColor(color);
        canvas.drawRect(new RectF(X , Y, X+width, Y+height), p);
    }

    @Override
    public String toJSON() {
        String str = "";

        str += "{\"draw\": { \"shape\": \"rectangle\", \"left\":"+X+", \"top\":"+Y+",\"right\":"+(X+width)+",\"bottom\":"+(Y+height)+"} }";
        return str;
    }
}
