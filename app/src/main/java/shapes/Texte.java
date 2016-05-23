package shapes;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;


public class Texte implements Shape{


    private int x;
    private int y;
    private String text;
    private int color;
    private int size;

    /**
     * Creates a new Text
     * @param x value of the left coordinates of the text
     * @param y value of the top coordinates of the text
     * @param text String to print
     * @param color Color of the text
     * @param size Size of the text
     */
    public Texte(int x, int y, String text, int color, int size) {
        this.x = x;
        this.y = y;
        this.text = text;
        this.color = color;
        this.size = size;
    }

    /**
     * Print the text
     * @param canvas
     */
    @Override
    public void draw(Canvas canvas) {
        Paint p = new Paint();
        p.setTextSize(size);
        p.setColor(color);
        canvas.drawText(text, x, y, p);
    }

    /**
     * Serialize the text into a String in a JSON format
     * @param dx translation done in X axis
     * @param dy translation done in Y axis
     * @return
     */
    @Override
    public String toJSON(int dx, int dy) {
        String str = "";
        float[] colors = new float[3];
        colors[0] = Color.red(color);
        colors[1] = Color.green(color);
        colors[2] = Color.blue(color);
        str += "{\\\"draw\\\": { \\\"shape\\\": \\\"text\\\", \\\"position\\\":["+(x-dx)+","+(y-dy)+"], \\\"content\\\": \\\""+text+"\\\", \\\"options\\\": { \\\"strokeColor\\\": ["+ Color.alpha(color)+", "+colors[0]+", "+colors[1]+", "+colors[2]+"], \\\"strokeWidth\\\": "+size+"} } }";
        return str;
    }


    /**
     * Move the shape on the view
     * @param dx Value of the translation on X axis
     * @param dy Value of the translation on Y axis
     */
    @Override
    public void translate(int dx, int dy) {
        x += dx;
        y += dy;
    }


    /**
     * Return the value on left side
     * @return X
     */
    public int getX(){
        return x;
    }

    /**
     * Return the value on the top side
     * @return Y
     */
    public int getY(){
        return y;
    }

}
