package ir2.esipe.shareview;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import shapes.Shape;
import shapes.ShapesManager;

/**
 * A placeholder fragment containing a simple view.
 */
public class BoardFragment extends Fragment {

    public BoardFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_board, container, false);

        final ImageButton ellipseButton = (ImageButton) rootView.findViewById(R.id.elipsebtn);
        final ImageButton rectBtn = (ImageButton)rootView.findViewById(R.id.recbtn);
        final ImageButton lineBtn = (ImageButton) rootView.findViewById(R.id.linebtn);
        final ImageButton polylineBtn = (ImageButton) rootView.findViewById(R.id.polyline);
        final ImageButton texteBtn = (ImageButton) rootView.findViewById(R.id.texte);


        final ShapesManager shapesManager = ShapesManager.getSingleton();


        if (ellipseButton != null && rectBtn != null && lineBtn != null) {

            ellipseButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    clearToolbar(ellipseButton,rectBtn, lineBtn, polylineBtn, texteBtn);
                    shapesManager.setCurrentShape(shapes.Shape.ELLIPSE);
                    v.setBackground(getActivity().getDrawable(R.drawable.ellipseused));
                }
            });

            rectBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clearToolbar(ellipseButton,rectBtn, lineBtn, polylineBtn, texteBtn);
                    shapesManager.setCurrentShape(Shape.RECTANGLE);
                    v.setBackground(getActivity().getDrawable(R.drawable.rectangleused));
                }
            });

            lineBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clearToolbar(ellipseButton,rectBtn, lineBtn, polylineBtn, texteBtn);
                    shapesManager.setCurrentShape(Shape.LINE);
                    v.setBackground(getActivity().getDrawable(R.drawable.lineused));
                }
            });

            polylineBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clearToolbar(ellipseButton,rectBtn, lineBtn, polylineBtn, texteBtn);
                    shapesManager.setCurrentShape(Shape.POLYLINE);
                    v.setBackground(getActivity().getDrawable(R.drawable.polylineused));
                }
            });

            texteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clearToolbar(ellipseButton,rectBtn, lineBtn, polylineBtn, texteBtn);
                    shapesManager.setCurrentShape(Shape.TEXTE);
                    v.setBackground(getActivity().getDrawable(R.drawable.texteused));
                }
            });

        } else {
            Log.e(MainActivity.class.getSimpleName(),"Error using toolbar listener");
        }

        return rootView;
    }


    private void clearToolbar(ImageButton ellipse, ImageButton rectangle, ImageButton line, ImageButton polyline, ImageButton texte){
        ellipse.setBackground(getActivity().getDrawable(R.drawable.ellipse));
        rectangle.setBackground(getActivity().getDrawable(R.drawable.rectangle));
        line.setBackground(getActivity().getDrawable(R.drawable.line));
        polyline.setBackground(getActivity().getDrawable(R.drawable.polyline));
        texte.setBackground(getActivity().getDrawable(R.drawable.texte));
    }
}
