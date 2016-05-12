package shapes;

import android.graphics.Canvas;
import android.graphics.Color;

import java.util.ArrayList;
import java.util.List;


public class ShapesManager {

    private static ShapesManager manager;
    private List<Shape> shapes;
    private int current;
    private int color = Color.BLACK;
    private int texteSize = 15;
    private int stokeSize = 15;

    private int indiceTranslationX = 0;
    private int indiceTranslationY = 0;


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

    public String toJSON(){
        StringBuilder strb = new StringBuilder();
        for(Shape shape: shapes){
            strb.append(shape.toJSON(indiceTranslationX, indiceTranslationY));
        }
        return strb.toString();
    }


    public void setFontSize(int size){
        texteSize = size;
    }

    public void setStokeSize(int size) { stokeSize = size; }


    public void translate(int dx, int dy){
        indiceTranslationX += dx;
        indiceTranslationY += dy;

        for(Shape shape: shapes){
            shape.translate(dx, dy);
        }
    }


    public boolean hasShapeOnTop(){
        for(Shape shape: shapes){
            if(shape.getX() < 0){
                return true;
            }
        }
        return false;
    }

    public boolean hasShapeOnBottom(int bottom){
        for(Shape shape: shapes){
            if(shape.getX() > bottom){
                return true;
            }
        }
        return false;
    }

    public boolean hasShapeOnLeft(){
        for(Shape shape: shapes){
            if(shape.getY() < 0){
                return true;
            }
        }
        return false;
    }

    public boolean hasShapeOnRight(int right){
        for(Shape shape: shapes){
            if(shape.getY() > right){
                return true;
            }
        }
        return false;
    }



}
