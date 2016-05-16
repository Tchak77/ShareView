package shapes;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

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
        float[] colors = new float[3];
        colors[0] = Color.red(color);
        colors[1] = Color.green(color);
        colors[2] = Color.blue(color);
        str += "{\\\"draw\\\": { \\\"shape\\\": \\\"polyline\\\", \\\"coordinates\\\":[["+(Xstart-dx)+","+(Ystart-dy)+"],["+(Xend-dx)+","+(Yend-dy)+"]], \\\"options\\\": {\\\"strokeColor\\\": ["+ Color.alpha(color)+", "+colors[0]+", "+colors[1]+", "+colors[2]+"], \\\"strokeWidth\\\": "+stroke+"}} }";
       Log.v("toto",str);
        return str;
    }

    @Override
    public void translate(int dx, int dy) {
        Xstart += dx;
        Xend += dx;
        Ystart += dy;
        Yend += dy;
    }


    public int getX(){
        return Xstart;
    }
    public int getY(){
        return Ystart;
    }

}
