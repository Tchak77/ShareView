package shapes;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;

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

        Log.v("toto","X="+X+" Y="+Y+" width="+width+ "height = "+height);
        if(width < 0){
            this.X = X+width;
            this.width = -width;
        }else {
            this.X = X;
            this.width = width;
        }

        if(height < 0){
            this.Y = Y+height;
            this.height = -height;
        } else {
            this.Y = Y;
            this.height = height;
        }
        Log.v("toto","X="+this.X+" Y="+this.Y+" width="+this.width+ "height = "+this.height);
        this.color = color;
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
