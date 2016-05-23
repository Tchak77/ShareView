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


    /**
     * Creates an Ellipse
     *
     * @param X position of the left side on X axis
     * @param Y position on the top side Y axis
     * @param width width of the ellipse
     * @param height height of the ellipse
     * @param color Color of the heart of the shape
     * @param borderSize Size of the border
     * @param borderColor Color of the border
     */
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


    /**
     * Draw the ellipse on the view thanks to the canvas
     * @param canvas
     */
    public void draw(Canvas canvas){
        Paint p = new Paint();
        p.setColor(color);
        Paint border = new Paint();
        border.setColor(borderColor);
        canvas.drawOval(new RectF(X, Y, X+width, Y + height),border);
        canvas.drawOval(new RectF(X + (borderSize/2) , Y + (borderSize/2)  , X + width - (borderSize/2), Y + height - (borderSize/2)), p);


    }

    /**
     * Serialize the ellipse into a string in JSON format
     *
     * @param dx Translation done on x axis
     * @param dy Translation done on y axis
     * @return
     */
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

    /**
     * Move the shape on the view
     * @param dx Value of the translation on X axis
     * @param dy Value of the translation on Y axis
     */
    @Override
    public void translate(int dx, int dy) {

        this.X += dx;
        this.Y += dy;

    }

    /**
     * Return the value on left side
     * @return X
     */
    public int getX(){
        return X;
    }

    /**
     * Return the value on the top side
     * @return Y
     */
    public int getY(){
        return Y;
    }


}
