package fragments;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import activities.MainActivity;
import activities.R;


public class UsersFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private List<String> users;

    public UsersFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        users=((MainActivity)getActivity()).getUsers();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_user, container, false);

        ListView listView = (ListView)rootView.findViewById(R.id.listView);
        ArrayAdapter arrayAdapter = new ArrayAdapter<>(getActivity(), R.layout.listitem_user, R.id.listitem_user_textview, users.toArray());
        listView.setAdapter(arrayAdapter);


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

