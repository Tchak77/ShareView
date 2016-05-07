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
        final ImageButton lineBtn = (ImageButton) findViewById(R.id.linebtn);
        final ImageButton polylineBtn = (ImageButton) findViewById(R.id.polyline);
        final ImageButton texteBtn = (ImageButton) findViewById(R.id.texte);


        final ShapesManager shapesManager = ShapesManager.getSingleton();


        if (ellipseButton != null && rectBtn != null && lineBtn != null) {

            ellipseButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    clearToolbar(ellipseButton,rectBtn, lineBtn, polylineBtn, texteBtn);
                    shapesManager.setCurrentShape(Shape.ELLIPSE);
                    v.setBackground(getDrawable(R.drawable.ellipseused));
                }
            });

            rectBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clearToolbar(ellipseButton,rectBtn, lineBtn, polylineBtn, texteBtn);
                    shapesManager.setCurrentShape(Shape.RECTANGLE);
                    v.setBackground(getDrawable(R.drawable.rectangleused));
                }
            });

            lineBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clearToolbar(ellipseButton,rectBtn, lineBtn, polylineBtn, texteBtn);
                    shapesManager.setCurrentShape(Shape.LINE);
                    v.setBackground(getDrawable(R.drawable.lineused));
                }
            });

            polylineBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clearToolbar(ellipseButton,rectBtn, lineBtn, polylineBtn, texteBtn);
                    shapesManager.setCurrentShape(Shape.POLYLINE);
                    v.setBackground(getDrawable(R.drawable.polylineused));
                }
            });

            texteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clearToolbar(ellipseButton,rectBtn, lineBtn, polylineBtn, texteBtn);
                    shapesManager.setCurrentShape(Shape.TEXTE);
                    v.setBackground(getDrawable(R.drawable.texteused));
                }
            });

        } else {
            Log.e(MainActivity.class.getSimpleName(),"Error using toolbar listener");
        }
    }



    private void clearToolbar(ImageButton ellipse, ImageButton rectangle, ImageButton line, ImageButton polyline, ImageButton texte){
        ellipse.setBackground(getDrawable(R.drawable.ellipse));
        rectangle.setBackground(getDrawable(R.drawable.rectangle));
        line.setBackground(getDrawable(R.drawable.line));
        polyline.setBackground(getDrawable(R.drawable.polyline));
        texte.setBackground(getDrawable(R.drawable.texte));
    }
}
