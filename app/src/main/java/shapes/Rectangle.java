package shapes;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;


public class Rectangle implements Shape {

    private int X;
    private int Y;
    private int width;
    private int height;
    private int color;
    private int borderSize;
    private int borderColor;


    public Rectangle(int x, int y, int width, int height, int color, int borderSize, int borderColor) {

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
        this.borderSize = borderSize;
        this.borderColor = borderColor;
        this.color = color;
    }



    public void draw(Canvas canvas){
        Paint p = new Paint();
        p.setColor(color);
        Paint border = new Paint();
        border.setColor(borderColor);

        canvas.drawRect(new RectF(X , Y, X+width, Y+height), border);
        canvas.drawRect(X+(borderSize/2), Y + (borderSize/2), X + width - (borderSize/2), Y+height - (borderSize/2), p);
    }

    @Override
    public String toJSON(int dx, int dy) {
        String str = "";
        float[] colors = new float[3];
        colors[0] = Color.red(color);
        colors[1] = Color.green(color);
        colors[2] = Color.blue(color);

        float[] borderColors = new float[3];
        borderColors[0] = Color.red(borderColor);
        borderColors[1] = Color.green(borderColor);
        borderColors[2] = Color.blue(borderColor);

        str += "{\\\"draw\\\": { \\\"shape\\\": \\\"rectangle\\\", \\\"left\\\":"+(X-dx)+", \\\"top\\\":"+(Y-dy)+",\\\"right\\\":"+((X+width)-dx)+",\\\"bottom\\\":"+((Y+height)-dy)+", \\\"options\\\": {\\\"fillColor\\\": ["+ Color.alpha(color)+", "+colors[0]+", "+colors[1]+", "+colors[2]+"], \\\"strokeWidth\\\":"+borderSize+", \\\"strokeColor\\\": ["+Color.alpha(borderColor)+", "+(int)borderColors[0]+", "+(int)borderColors[1]+", "+(int)borderColors[2]+"] }} }";
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
