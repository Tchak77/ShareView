package shapes;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by Kevin on 07/05/2016.
 */
public class Line implements Shape {


    private int Xstart;
    private int Ystart;
    private int Xend;
    private int Yend;
    private int color;
    private int stroke;


    public Line(int xstart, int ystart, int xend, int yend, int color, int stoke) {


        Xstart = xstart;
        Ystart = ystart;
        Xend = xend;
        Yend = yend;
        this.stroke = stoke;

        this.color = color;
    }

    @Override
    public void draw(Canvas canvas) {
        Paint p = new Paint();
        p.setColor(color);
        p.setStrokeWidth(stroke);
        canvas.drawLine(Xstart, Ystart, Xend, Yend, p);
    }

    @Override
    public String toJSON(int dx, int dy) {
        String str = "";

        str += "{\"draw\": { \"shape\": \"polyline\", \"coordinates\":[["+(Xstart-dx)+","+(Ystart-dy)+"],["+(Xend-dx)+","+(Yend-dy)+"]] } }";
        return str;
    }

    @Override
    public void translate(int dx, int dy) {
        Xstart += dx;
        Xend += dx;
        Ystart += dy;
        Yend += dy;
    }

}
