package shapes;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by Kevin on 07/05/2016.
 */
public class Texte implements Shape{


    private int x;
    private int y;
    private String text;
    private int color;

    public Texte(int x, int y, String text, int color) {
        this.x = x;
        this.y = y;
        this.text = text;
        this.color = color;
    }

    @Override
    public void draw(Canvas canvas) {
        Paint p = new Paint();
        p.setColor(color);
        canvas.drawText(text, x, y, p);
    }
}
