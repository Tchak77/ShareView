package fragments;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.SeekBar;

import ir2.esipe.shareview.R;
import shapes.ShapesManager;


public class OptionsFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    final ShapesManager shapesManager = ShapesManager.getSingleton();

    public OptionsFragment() {
        // Required empty public constructor
    }


    public static OptionsFragment newInstance() {
        OptionsFragment fragment = new OptionsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);






    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_option, container, false);
        SeekBar txtSizeBar = (SeekBar)rootView.findViewById(R.id.txtSizeBar);
        txtSizeBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                shapesManager.setFontSize(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //Nothing to do
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //Nothing to do
            }
        });

        SeekBar strokeSizeBar = (SeekBar)rootView.findViewById(R.id.strokeSizeBar);
        strokeSizeBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                shapesManager.setStokeSize(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //Nothing to do
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //Nothing to do
            }
        });



        final ImageButton bleu = (ImageButton) rootView.findViewById(R.id.blue);
        final ImageButton rouge = (ImageButton) rootView.findViewById(R.id.rouge);
        final ImageButton vert = (ImageButton) rootView.findViewById(R.id.vert);
        final ImageButton noir = (ImageButton) rootView.findViewById(R.id.noir);

        bleu.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                shapesManager.setColor(Color.BLUE);
                return true;
            }
        });
        rouge.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                shapesManager.setColor(Color.RED);
                return true;
            }
        });
        vert.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                shapesManager.setColor(Color.GREEN);
                return true;
            }
        });
        noir.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                shapesManager.setColor(Color.BLACK);
                return true;
            }
        });

        return rootView;
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


    public void onFragmentInteraction(Uri uri) {

    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
