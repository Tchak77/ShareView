package shapes;

import android.graphics.Canvas;

import java.util.ArrayList;

/**
 * Created by Kevin on 05/05/2016.
 */
public class ShapesManager {

    private static ShapesManager manager;
    private ArrayList<Shape> shapes;
    private int current;

    private ShapesManager(){

        shapes = new ArrayList<>();
        current =1;
    }

    public static ShapesManager getSingleton(){
        if(manager == null){
            manager = new ShapesManager();
        }
        return manager;
    }

    public void addShape(Shape p){
        shapes.add(p);
    }
    public boolean isPolyLine(){ return current == Shape.POLYLINE; }
    public boolean isTexte(){ return current == Shape.TEXTE; }

    public void drawShapes(Canvas canvas){
        for(Shape shape: shapes){
            shape.draw(canvas);
        }
    }

    public void setCurrentShape(int current){
        this.current = current;
    }
    public Shape createShape(int x, int y, int width, int height, int color){
        switch(current){
            case Shape.ELLIPSE:
                return new Ellipse(x,y,width,height,color);

            case Shape.RECTANGLE:
                return new Rectangle(x,y,width,height,color);

            case Shape.LINE:
                return new Line(x, y, x+width, y+height, color);

            case Shape.POLYLINE:
                return new Line(x, y, x+width, y+height, color);
        }
        return null;
    }
    public Texte createTexte(int x, int y, String texte, int color ){
        return new Texte(x,y,texte,color);
    }




}
