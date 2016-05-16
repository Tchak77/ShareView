package shapes;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;


public class Texte implements Shape{


    private int x;
    private int y;
    private String text;
    private int color;
    private int size;

    public Texte(int x, int y, String text, int color, int size) {
        this.x = x;
        this.y = y;
        this.text = text;
        this.color = color;
        this.size = size;
    }

    @Override
    public void draw(Canvas canvas) {
        Paint p = new Paint();
        p.setTextSize(size);
        p.setColor(color);
        canvas.drawText(text, x, y, p);
    }

    @Override
    public String toJSON(int dx, int dy) {
        String str = "";
        float[] colors = new float[3];
        colors[0] = Color.red(color);
        colors[1] = Color.green(color);
        colors[2] = Color.blue(color);
        str += "{\\\"draw\\\": { \\\"shape\\\": \\\"text\\\", \\\"position\\\":["+(x-dx)+","+(y-dy)+"], \\\"content\\\": \\\""+text+"\\\", \\\"options\\\": { \\\"strokeColor\\\": ["+ Color.alpha(color)+", "+colors[0]+", "+colors[1]+", "+colors[2]+"], \\\"strokeWidth\\\": "+size+"} } }";
        return str;
    }

    @Override
    public void translate(int dx, int dy) {
        x += dx;
        y += dy;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

}
