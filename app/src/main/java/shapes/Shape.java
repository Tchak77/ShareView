package shapes;

import android.graphics.Canvas;

/**
 * Created by Kevin on 05/05/2016.
 */
public interface Shape {

    public static int ELLIPSE = 1;
    public static int RECTANGLE = 2;


    void draw(Canvas canvas);

}
