package shapes;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

public class Line implements Shape {


    private int Xstart;
    private int Ystart;
    private int Xend;
    private int Yend;
    private int color;
    private int stroke;

    /**
     * Construction that represents a Line
     *
     * @param xstart Coordinates of the first point on X axis
     * @param ystart Coordinates of the first point on Y axis
     * @param xend Coordinates of the second point on X axis
     * @param yend Coordinates of the second point on Y axis
     * @param color Color the line
     * @param stroke Stroke of the line
     */
    public Line(int xstart, int ystart, int xend, int yend, int color, int stroke) {

        Xstart = xstart;
        Ystart = ystart;
        Xend = xend;
        Yend = yend;
        this.stroke = stroke;

        this.color = color;
    }

    /**
     * Draw a line on the view thanks to the canvas
     * @param canvas
     */
    @Override
    public void draw(Canvas canvas) {
        Paint p = new Paint();
        p.setColor(color);
        p.setStrokeWidth(stroke);
        canvas.drawLine(Xstart, Ystart, Xend, Yend, p);
    }

    /**
     * Serialize the line into a string into a JSON format
     *
     * @param dx Translation done on x axis
     * @param dy Translation done on y axis
     * @return
     */
    @Override
    public String toJSON(int dx, int dy) {
        String str = "";
        float[] colors = new float[3];
        colors[0] = Color.red(color);
        colors[1] = Color.green(color);
        colors[2] = Color.blue(color);
        str += "{\\\"draw\\\": { \\\"shape\\\": \\\"polyline\\\", \\\"coordinates\\\":[["+(Xstart-dx)+","+(Ystart-dy)+"],["+(Xend-dx)+","+(Yend-dy)+"]], \\\"options\\\": {\\\"strokeColor\\\": ["+ Color.alpha(color)+", "+colors[0]+", "+colors[1]+", "+colors[2]+"], \\\"strokeWidth\\\": "+stroke+"}} }";
       Log.v("toto",str);
        return str;
    }

    /**
     * Move the shape on the view
     * @param dx Value of the translation on X axis
     * @param dy Value of the translation on Y axis
     */
    @Override
    public void translate(int dx, int dy) {
        Xstart += dx;
        Xend += dx;
        Ystart += dy;
        Yend += dy;
    }

    /**
     * Return the value on left side
     * @return X
     */
    public int getX(){
        return Xstart;
    }
    /**
     * Return the value on top side
     * @return Y
     */
    public int getY(){
        return Ystart;
    }

}
