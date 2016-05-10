package shapes;

import android.graphics.Canvas;
import android.graphics.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kevin on 05/05/2016.
 */
public class ShapesManager {

    private static ShapesManager manager;
    private List<Shape> shapes;
    private int current;
    private int color = Color.BLACK;
    private int texteSize = 15;
    private int stokeSize = 15;


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
    public boolean isRectangle(){ return current == Shape.RECTANGLE; }
    public boolean isEllipse(){ return current == Shape.ELLIPSE; }
    public boolean isLine(){ return current == Shape.LINE; }

    public void drawShapes(Canvas canvas){
        for(Shape shape: shapes){
            shape.draw(canvas);
        }
    }

    public void setCurrentShape(int current){
        this.current = current;
    }

    public Shape createShape(int x, int y, int width, int height){
        switch(current){
            case Shape.ELLIPSE:
                return new Ellipse(x,y,width,height,color);

            case Shape.RECTANGLE:
                return new Rectangle(x,y,width,height,color);

            case Shape.LINE:
                return new Line(x, y, x+width, y+height, color, stokeSize);

            case Shape.POLYLINE:
                return new Polyline(x, y, color);
        }
        return null;
    }

    public Shape getLastShape() {
        if (shapes.size() > 0) {
            return shapes.get(shapes.size() - 1);
        }
        return null;
    }
    public Texte createTexte(int x, int y, String texte ){
        return new Texte(x,y,texte,color, texteSize);

    }

    public void setColor(int color){
        this.color = color;
        if(isPolyLine()){
            ((Polyline)getLastShape()).setColor(color);
        }
    }

    public void resetBoard(){
        shapes = new ArrayList<>();
    }


    public void setBoard(List<Shape> shapes){
        this.shapes = shapes;
    }

    public String JSON(){
        StringBuilder strb = new StringBuilder();
        for(Shape shape: shapes){
            strb.append(shape.toJSON());
        }
        return strb.toString();
    }


    public void setFontSize(int size){
        texteSize = size;
    }

    public void setStokeSize(int size) { stokeSize = size; }



}
