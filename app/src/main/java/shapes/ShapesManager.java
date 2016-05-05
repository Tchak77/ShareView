package shapes;

import android.graphics.Canvas;

import java.util.ArrayList;

/**
 * Created by Kevin on 05/05/2016.
 */
public class ShapesManager {

    private ArrayList<Ellipse> ellipses;
    private ArrayList<Rectangle> rectangles;


    public ShapesManager(){
        ellipses = new ArrayList<>();
        rectangles = new ArrayList<>();
    }


    public void addEllipse(Ellipse p){
        ellipses.add(p);
    }

    public void addRectangle(Rectangle rec){
        rectangles.add(rec);
    }

    public void drawEllipses(Canvas canvas){
        for(Ellipse ellipse: ellipses){
            ellipse.drawEllipse(canvas);
        }
    }

    public void drawRectangles(Canvas canvas){
        for(Rectangle rectangle: rectangles){
            rectangle.drawRectangle(canvas);
        }
    }



}
