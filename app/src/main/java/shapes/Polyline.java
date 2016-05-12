package shapes;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kevin on 08/05/2016.
 */
public class Polyline implements Shape {


    private int color;
    List<Point> points;


    public Polyline(float xstart, float ystart, int color) {
        points = new ArrayList<>();
        points.add(new Point(xstart, ystart));
        this.color = color;
    }

    public void addPoint(int x, int y){
        points.add(new Point(x,y));
    }

    @Override
    public void draw(Canvas canvas) {
        Paint p = new Paint();
        p.setColor(color);
        p.setStrokeWidth(50);
        for(int i=0; i<points.size()-1; i++){
            canvas.drawLine(points.get(i).getX(), points.get(i).getY(), points.get(i+1).getX(), points.get(i+1).getY(), p);
        }
    }


    public void setColor(int color){
        this.color = color;
    }

    public Point getPoint(){
        return points.get(points.size()-1);
    }

    @Override
    public String toJSON(int dx, int dy) {
        StringBuilder str = new StringBuilder();
        str.append("{\"draw\": { \"shape\": \"polyline\", \"coordinates\":[");
        for(int i=0; i<points.size(); i++){
            str.append("["+(points.get(i).getX()-dx)+", "+(points.get(i).getY()-dy)+"],");
        }
        str.setLength(str.length()-1); // On enleve la dernere virgule
        str.append("] } }");
        return str.toString();
    }

    @Override
    public void translate(int dx, int dy) {
        for(Point point: points){
            point.translate(dx, dy);
        }
    }


}
