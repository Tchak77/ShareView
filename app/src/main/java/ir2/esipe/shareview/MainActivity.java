package ir2.esipe.shareview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import shapes.ShapesManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw);

        ImageButton ellipseButton = (ImageButton) findViewById(R.id.elipsebtn);
        ImageButton rectBtn = (ImageButton) findViewById(R.id.recbtn);
        final ShapesManager shapesManager = ShapesManager.getSingleton();


        if (ellipseButton != null && rectBtn != null) {

            ellipseButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    shapesManager.setCurrentShape(1);
                }
            });

            rectBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    shapesManager.setCurrentShape(2);
                }
            });

        } else {
            Log.e(MainActivity.class.getSimpleName(),"Error using toolbar listener");
        }
    }
}
