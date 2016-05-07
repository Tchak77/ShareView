package ir2.esipe.shareview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import shapes.Shape;
import shapes.ShapesManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw);

        final ImageButton ellipseButton = (ImageButton) findViewById(R.id.elipsebtn);
        final ImageButton rectBtn = (ImageButton) findViewById(R.id.recbtn);
        final ShapesManager shapesManager = ShapesManager.getSingleton();


        if (ellipseButton != null && rectBtn != null) {

            ellipseButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    clearToolbar(ellipseButton,rectBtn);
                    shapesManager.setCurrentShape(Shape.ELLIPSE);
                    v.setBackground(getDrawable(R.drawable.ellipseused));
                }
            });

            rectBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clearToolbar(ellipseButton,rectBtn);
                    shapesManager.setCurrentShape(Shape.RECTANGLE);
                    v.setBackground(getDrawable(R.drawable.rectangleused));
                }
            });

        } else {
            Log.e(MainActivity.class.getSimpleName(),"Error using toolbar listener");
        }
    }



    private void clearToolbar(ImageButton ellipse, ImageButton rectangle){
        ellipse.setBackground(getDrawable(R.drawable.ellipse));
        rectangle.setBackground(getDrawable(R.drawable.rectangle));
    }
}
