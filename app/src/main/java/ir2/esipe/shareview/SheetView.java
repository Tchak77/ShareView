package ir2.esipe.shareview;


import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.support.v7.app.AlertDialog;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import shapes.Polyline;
import shapes.ShapesManager;

public class SheetView extends View {

    private ShapesManager shapesManager = ShapesManager.getSingleton();

    private float upperLeftX = -1;
    private float upperLeftY = -1;

    public SheetView(Context context) {
        super(context);

    }

    public SheetView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SheetView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public boolean onTouchEvent(final MotionEvent event){

        int action = event.getAction();

        if(shapesManager.isPolyLine()){
            if(action == MotionEvent.ACTION_DOWN){
                if(upperLeftY == -1){

                    upperLeftX = event.getX();
                    upperLeftY = event.getY();
                    shapesManager.addShape(shapesManager.createShape((int)upperLeftX, (int)upperLeftY, 0, 0));
                    return true;
                } else {
                    upperLeftX = event.getX();
                    upperLeftY = event.getY();
                    ((Polyline)(shapesManager.getLastShape())).addPoint((int)upperLeftX, (int)upperLeftY);
                    invalidate();
                    return true;
                }
            }
            return true;
        }
        switch (action){
            case MotionEvent.ACTION_DOWN:
                upperLeftX = event.getX();
                upperLeftY = event.getY();
                return true;

            case MotionEvent.ACTION_UP:

                if(shapesManager.isTexte()){
                    setTextOnBoard(event);

                } else {
                    shapesManager.addShape(shapesManager.createShape((int)upperLeftX, (int)upperLeftY, (int)(event.getX() - upperLeftX), (int)(event.getY() - upperLeftY)));
                }
                upperLeftY = -1;
                upperLeftX = -1;
                invalidate();
                return true;
            default:
                return true;
        }

    }

    private void setTextOnBoard(MotionEvent event) {
        final int x = (int)event.getX();
        final int y =(int)event.getY();
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(R.string.text);
        final EditText input = new EditText(getContext());
        builder.setView(input);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String text = input.getText().toString();
                shapesManager.addShape(shapesManager.createTexte(x, y, text));
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        canvas.save();
        shapesManager.drawShapes(canvas);
        canvas.restore();
    }

}