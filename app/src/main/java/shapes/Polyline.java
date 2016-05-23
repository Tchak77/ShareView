package shapes;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.List;


public class Polyline implements Shape {


    private int color;
    private List<Point> points;
    private int stroke;

    /**
     * Creates a new Polyline with a Point
     * @param xstart Coordinates on the X axis
     * @param ystart Coordiantes on the Y axis
     * @param color Color of the polyline
     * @param stroke Stroke of the polyline
     */
    public Polyline(float xstart, float ystart, int color, int stroke) {
        points = new ArrayList<>();
        points.add(new Point(xstart, ystart));
        this.color = color;
        this.stroke = stroke;
    }


    /**
     * Add a point into the Polyline
     * @param x Coodinates of the new Point on X axis
     * @param y Coodinates of the new Point on Y axis
     */
    public void addPoint(int x, int y){
        points.add(new Point(x,y));
    }


    /**
     * Draw the Polyline on the view thanks to the canvas
     * @param canvas
     */
    @Override
    public void draw(Canvas canvas) {
        Paint p = new Paint();
        p.setColor(color);
        p.setStrokeWidth(stroke);
        for(int i=0; i<points.size()-1; i++){
            canvas.drawLine(points.get(i).getX(), points.get(i).getY(), points.get(i+1).getX(), points.get(i+1).getY(), p);
        }
    }

    /**
     * Set the color of the polyline
     * @param color
     */
    public void setColor(int color){
        this.color = color;
    }

    /**
     * Return the last Point of the Polyline
     * @return the last Point of the Polyline
     */
    public Point getPoint(){
        return points.get(points.size()-1);
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
        StringBuilder str = new StringBuilder();
        str.append("{\\\"draw\\\": { \\\"shape\\\": \\\"polyline\\\", \\\"coordinates\\\":[");
        for(int i=0; i<points.size(); i++){
            str.append("[").append(points.get(i).getX()-dx).append(", ").append(points.get(i).getY()-dy).append("],");
        }
        str.setLength(str.length()-1); // On enleve la dernere virgule
        str.append("], \\\"options\\\": {\\\"strokeColor\\\": ["+ Color.alpha(color)+", "+colors[0]+", "+colors[1]+", "+colors[2]+"], \\\"strokeWidth\\\": "+stroke+"} } }");
        return str.toString();
    }

    /**
     * Move the shape on the view
     * @param dx Value of the translation on X axis
     * @param dy Value of the translation on Y axis
     */
    @Override
    public void translate(int dx, int dy) {
        for(Point point: points){
            point.translate(dx, dy);
        }
    }

    /**
     * Return the X axis of the first Point
     * @return X
     */
    public int getX(){
        return (int)points.get(0).getX();
    }

    /**
     * Return the Y axis of the first Point
     * @return Y
     */
    public int getY(){
        return (int)points.get(0).getY();
    }


}
