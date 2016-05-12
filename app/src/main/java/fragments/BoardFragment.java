package fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import ir2.esipe.shareview.MainActivity;
import ir2.esipe.shareview.R;
import shapes.Shape;
import shapes.ShapesManager;

/**
 * A placeholder fragment containing a simple view.
 */
public class BoardFragment extends Fragment {

    private String title;
    private OnFragmentInteractionListener mListener;

    public BoardFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
        title = getActivity().getIntent().getStringExtra("title");

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


        final ImageButton move = (ImageButton) rootView.findViewById(R.id.movebtn);

        final ShapesManager shapesManager = ShapesManager.getSingleton();



        if(shapesManager.isPolyLine()){
            polylineBtn.setBackground(getActivity().getDrawable(R.drawable.polylineused));
        } else if(shapesManager.isTexte()){
            texteBtn.setBackground(getActivity().getDrawable(R.drawable.texteused));
        } else if(shapesManager.isEllipse()){
            ellipseButton.setBackground(getActivity().getDrawable(R.drawable.ellipseused));
        } else if(shapesManager.isRectangle()){
            rectBtn.setBackground(getActivity().getDrawable(R.drawable.rectangleused));
        } else {
            lineBtn.setBackground(getActivity().getDrawable(R.drawable.lineused));
        }

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



            move.setOnTouchListener(new View.OnTouchListener() {

                float dX, dY;
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    View view = getActivity().findViewById(R.id.toolbarDraw);

                    switch (event.getAction()) {

                        case MotionEvent.ACTION_DOWN:

                            dX = view.getX() - event.getRawX();
                            dY = view.getY() - event.getRawY();
                            break;

                        case MotionEvent.ACTION_MOVE:

                            view.animate()
                                    .x(event.getRawX() + dX)
                                    .y(event.getRawY() + dY)
                                    .setDuration(0)
                                    .start();
                            break;
                        default:
                            return false;
                    }
                    return true;
            }});

        } else {
            Log.e(MainActivity.class.getSimpleName(),"Error using toolbar listener");
        }

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_board, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.reset){
            ShapesManager.getSingleton().resetBoard();
            getActivity().findViewById(R.id.sheetView).invalidate();
            return true;
        }

        return true;
    }

    private void clearToolbar(ImageButton ellipse, ImageButton rectangle, ImageButton line, ImageButton polyline, ImageButton texte){
        ellipse.setBackground(getActivity().getDrawable(R.drawable.ellipse));
        rectangle.setBackground(getActivity().getDrawable(R.drawable.rectangle));
        line.setBackground(getActivity().getDrawable(R.drawable.line));
        polyline.setBackground(getActivity().getDrawable(R.drawable.polyline));
        texte.setBackground(getActivity().getDrawable(R.drawable.texte));

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {

    }
}