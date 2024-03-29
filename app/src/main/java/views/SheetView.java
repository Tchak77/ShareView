package views;


import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.support.v7.app.AlertDialog;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import activities.R;
import shapes.Polyline;
import shapes.ShapesManager;

public class SheetView extends View {

    private ShapesManager shapesManager = ShapesManager.getSingleton();

    private float upperLeftX = -1;
    private float upperLeftY = -1;
    private boolean lastShapePolyLine = false;


    public SheetView(Context context) {
        super(context);
        shapesManager.setView(this);

    }
    public SheetView(Context context, AttributeSet attrs) {
        super(context, attrs);
        shapesManager.setView(this);
    }
    public SheetView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        shapesManager.setView(this);
    }


    /**
     * Decides which action to perform depending of the finger's motion on screen
     * Two finger: Move the board
     * One finger: Create a new Shape or add points in a Polyline
     * @param event Motion captured
     * @return true
     */
    public boolean onTouchEvent(final MotionEvent event){

        int action = event.getAction();
        int nbFinger = event.getPointerCount();


        if(nbFinger == 2){
            if(action == MotionEvent.ACTION_POINTER_UP){
                //On gère le déplacement dans le tableau
                shapesManager.translate((int)(event.getRawX() - upperLeftX), (int) (event.getRawY()- upperLeftY ));
                upperLeftX = -1;
                upperLeftY = -1;
                invalidate();
                return true;
            }
        }



        if(shapesManager.isPolyLine()){

            if(action == MotionEvent.ACTION_DOWN){

                if(lastShapePolyLine && shapesManager.getLastShape() != null && shapesManager.getLastShape() instanceof Polyline && ((Polyline) shapesManager.getLastShape()).getPoint() != null ){
                    upperLeftX = ((Polyline) shapesManager.getLastShape()).getPoint().getX();
                    upperLeftY = ((Polyline) shapesManager.getLastShape()).getPoint().getY();
                }
                lastShapePolyLine = true;
                if(upperLeftX == -1){

                    upperLeftX = event.getX();
                    upperLeftY = event.getY();
                    shapesManager.createShape((int)upperLeftX, (int)upperLeftY, 0, 0);
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
        if(lastShapePolyLine == true){
            Polyline polyline = (Polyline) shapesManager.getLastShape();
            shapesManager.sendPolylineShape(polyline);
            lastShapePolyLine = false;
        }
        switch (action){
            case MotionEvent.ACTION_DOWN:
                upperLeftX = event.getX();
                upperLeftY = event.getY();
                return true;

            //Quand on relève le doigt, on vérifie qu'on sort pas d'un translate et on dessine la forme
            case MotionEvent.ACTION_UP:
                if(upperLeftX != -1) {
                    if (shapesManager.isTexte()) {
                        setTextOnBoard(event);
                    } else {
                        shapesManager.createShape((int) upperLeftX, (int) upperLeftY, (int) (event.getX() - upperLeftX), (int) (event.getY() - upperLeftY));
                    }
                    upperLeftY = -1;
                    upperLeftX = -1;
                    invalidate();
                }
                return true;
            default:
                return true;
        }

    }


    /**
     * Create a new Text on the board asking for the user in a Dialog the String to print
     * @param event
     */
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
                shapesManager.createTexte(x, y, text);
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


    /**
     * Draw all the shapes on the view
     * @param canvas
     */
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);

        canvas.save();
        shapesManager.drawShapes(canvas);
        canvas.restore();
    }


    /**
     * Refresh the view
     */
    public void invalidateView(){
        if (getContext() != null) {
            ((Activity) getContext()).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    invalidate();
                }
            });
        }
    }
}
