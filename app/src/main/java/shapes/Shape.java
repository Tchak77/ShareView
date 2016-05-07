package shapes;

import android.graphics.Canvas;

/**
 * Created by Kevin on 05/05/2016.
 */
public interface Shape {

    int ELLIPSE = 1;
    int RECTANGLE = 2;
    int LINE = 3;
    int POLYLINE = 4;
    int TEXTE = 5;


    void draw(Canvas canvas);

}
