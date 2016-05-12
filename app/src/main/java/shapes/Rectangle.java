package shapes;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;


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
    public String toJSON(int dx, int dy) {
        String str = "";

        str += "{\"draw\": { \"shape\": \"rectangle\", \"left\":"+(X-dx)+", \"top\":"+(Y-dy)+",\"right\":"+(X+width)+",\"bottom\":"+(Y+height)+"} }";
        return str;
    }

    @Override
    public void translate(int dx, int dy) {
        X += dx;
        Y += dy;
    }

   public int getX(){
        return X;
    }

    public int getY(){
        return Y;
    }


}
