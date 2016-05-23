package fragments;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Switch;

import activities.R;
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
        final ImageButton blanc = (ImageButton) rootView.findViewById(R.id.blanc);

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
        blanc.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                shapesManager.setColor(Color.WHITE);
                return true;
            }
        });


        final SeekBar previous = (SeekBar) rootView.findViewById(R.id.previousBar);
        final Switch enablePrevious = (Switch) rootView.findViewById(R.id.enablePrevious);
        previous.setMax(shapesManager.getSize());

       enablePrevious.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               if(isChecked){
                   shapesManager.displayUnderLimit(previous.getProgress());
               } else {
                   shapesManager.displayUnderLimit(-1);
               }
           }
       });


        previous.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(enablePrevious.isChecked()) {
                    shapesManager.displayUnderLimit(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



        SeekBar borderSizeBar = (SeekBar) rootView.findViewById(R.id.borderBar);

        borderSizeBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                shapesManager.setBorderSize(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //NOTHING TO DO
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //NOTHING TO DO
            }
        });

        final ImageButton borderBlue = (ImageButton) rootView.findViewById(R.id.borderBlue);
        final ImageButton borderRed = (ImageButton) rootView.findViewById(R.id.borderRed);
        final ImageButton borderGreen = (ImageButton) rootView.findViewById(R.id.borderGreen);
        final ImageButton borderBlack = (ImageButton) rootView.findViewById(R.id.borderBlack);
        final ImageButton borderWhite = (ImageButton) rootView.findViewById(R.id.borderWhite);

        borderBlue.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                shapesManager.setBorderColor(Color.BLUE);
                return true;
            }
        });
        borderRed.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                shapesManager.setBorderColor(Color.RED);
                return true;
            }
        });
        borderGreen.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                shapesManager.setBorderColor(Color.GREEN);
                return true;
            }
        });
        borderBlack.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                shapesManager.setBorderColor(Color.BLACK);
                return true;
            }
        });
        borderWhite.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                shapesManager.setBorderColor(Color.WHITE);
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



    public interface OnFragmentInteractionListener {

    }
}
