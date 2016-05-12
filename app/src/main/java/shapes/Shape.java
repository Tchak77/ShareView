package shapes;

import android.graphics.Canvas;


public interface Shape {

    int ELLIPSE = 1;
    int RECTANGLE = 2;
    int LINE = 3;
    int POLYLINE = 4;
    int TEXTE = 5;


    void draw(Canvas canvas);
    String toJSON(int dx, int dy);
    void translate(int dx, int dy);
    int getX();
    int getY();

}
