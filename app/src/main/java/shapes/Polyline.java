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

    @Override
    public String toJSON() {
        StringBuilder str = new StringBuilder();
        str.append("{\"draw\": { \"shape\": \"polyline\", \"coordinates\":[");
        for(int i=0; i<points.size(); i++){
            str.append("["+points.get(i).getX()+", "+points.get(i).getY()+"],");
        }
        str.setLength(str.length()-1); // On enleve la dernere virgule
        str.append("] } }");
        return str.toString();
    }
}
