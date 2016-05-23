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

import activities.R;
import messages.MessagesManager;


public class UsersFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private List<String> users;
    private ArrayAdapter<String> arrayAdapter;

    public UsersFragment() {
        // Required empty public constructor
    }


    /**
     * Actions to performe at the creation of the fragment
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MessagesManager manager = MessagesManager.getSingleton();
        manager.setUsersFragment(this);
        users = manager.getUsers();
    }


    /**
     * Prepare the layout and the list listener
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_user, container, false);

        ListView listView = (ListView) rootView.findViewById(R.id.listView);
        arrayAdapter = new ArrayAdapter<>(getActivity(), R.layout.listitem_user, R.id.listitem_user_textview, users);
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

    /**
     * Notify the list adapter that a new person arrived/leaved
     */
    public void update() {
        if(getActivity()!=null){
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    arrayAdapter.notifyDataSetChanged();
                }
            });
        }
    }
}

