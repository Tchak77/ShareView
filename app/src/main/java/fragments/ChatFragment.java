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
import messages.Message;
import messages.MessagesManager;


public class ChatFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private List<Message> messages;

    public ChatFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MessagesManager manager = MessagesManager.getSingleton();
        messages = manager.getMessages();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_chat, container, false);


        ListView listView = (ListView)rootView.findViewById(R.id.chatList);
        ArrayAdapter arrayAdapter = new ArrayAdapter<>(getActivity(), R.layout.listitem_chat, R.id.listitem_chat_textview, messages.toArray());
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
