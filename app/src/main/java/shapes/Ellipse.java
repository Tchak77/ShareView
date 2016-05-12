package shapes;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;


public class Ellipse implements Shape {

    private int X;
    private int Y;
    private int height;
    private int width;
    private int color;

    public Ellipse(int X, int Y, int width, int height, int color){

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
        this.color = color;
    }



    public void draw(Canvas canvas){
        Paint p = new Paint();
        p.setColor(color);
        canvas.drawOval(new RectF(X , Y  , X + width, Y + height), p);
    }

    @Override
    public String toJSON(int dx, int dy) {
        String str = "";
        str += "{\"draw\": { \"shape\": \"ellipse\", \"center\":["+(X-dx)+","+(Y-dy)+"], \"radius\": ["+width+","+height+"] } }";
        return str;
    }

    @Override
    public void translate(int dx, int dy) {
        Log.v("toto", "De base: "+X+", "+Y+",   dx="+dx+"  dy="+dy);
        this.X += dx;
        this.Y += dy;
        Log.v("toto", "Final: "+X+", "+Y);
    }

    public int getX(){
        return X;
    }
    public int getY(){
        return Y;
    }


}
