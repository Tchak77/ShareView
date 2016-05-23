package shapes;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;


public class Ellipse implements Shape {

    private int X;
    private int Y;
    private int height;
    private int width;
    private int color;
    private int borderSize;
    private int borderColor;

    public Ellipse(int X, int Y, int width, int height, int color, int borderSize, int borderColor){

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
        this.borderSize = borderSize;
        this.borderColor = borderColor;
        this.color = color;
    }



    public void draw(Canvas canvas){
        Paint p = new Paint();
        p.setColor(color);
        Paint border = new Paint();
        border.setColor(borderColor);
        canvas.drawOval(new RectF(X, Y, X+width, Y + height),border);
        canvas.drawOval(new RectF(X + (borderSize/2) , Y + (borderSize/2)  , X + width - (borderSize/2), Y + height - (borderSize/2)), p);


    }

    @Override
    public String toJSON(int dx, int dy) {
        float[] colors = new float[3];
        colors[0] = Color.red(color);
        colors[1] = Color.green(color);
        colors[2] = Color.blue(color);

        float[] borderColors = new float[3];
        borderColors[0] = Color.red(borderColor);
        borderColors[1] = Color.green(borderColor);
        borderColors[2] = Color.blue(borderColor);
        String str = "";
        str += "{\\\"draw\\\": { \\\"shape\\\": \\\"ellipse\\\", \\\"center\\\":["+(X-dx)+","+(Y-dy)+"], \\\"radius\\\": ["+width+","+height+"], \\\"options\\\": {\\\"fillColor\\\": ["+Color.alpha(color)+", "+(int)colors[0]+", "+(int)colors[1]+", "+(int)colors[2]+"], \\\"strokeWidth\\\":"+borderSize+", \\\"strokeColor\\\": ["+Color.alpha(borderColor)+", "+(int)borderColors[0]+", "+(int)borderColors[1]+", "+(int)borderColors[2]+"] } } }";

        return str;
    }

    @Override
    public void translate(int dx, int dy) {

        this.X += dx;
        this.Y += dy;

    }

    public int getX(){
        return X;
    }
    public int getY(){
        return Y;
    }


}
