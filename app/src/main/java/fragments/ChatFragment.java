package fragments;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

import activities.R;
import messages.Message;
import messages.MessagesManager;


public class ChatFragment extends Fragment {
    private OnFragmentInteractionListener mListener;
    private List<Message> messages;
    private ArrayAdapter<Message> arrayAdapter;

    public ChatFragment() {
        // Required empty public constructor
    }


    /**
     * Actions to perform at the creation of the fragment
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MessagesManager manager = MessagesManager.getSingleton();
        manager.setChatFragment(this);
        messages = manager.getMessages();
    }


    /**
     * Prepare the layout and set the listListener
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView =  inflater.inflate(R.layout.fragment_chat, container, false);


        ListView listView = (ListView)rootView.findViewById(R.id.chatList);
        arrayAdapter = new ArrayAdapter<>(getActivity(), R.layout.listitem_chat, R.id.listitem_chat_textview, messages);
        listView.setAdapter(arrayAdapter);

        Button send = (Button) rootView.findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = (EditText) rootView.findViewById(R.id.textContent);
                String str = editText.getText().toString();
                MessagesManager messagesManager = MessagesManager.getSingleton();
                messagesManager.sendMessage(str);
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

    /**
     * Notify the list listener that a new message is arrived
     */
    public void update(){
        if (getActivity()!=null) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {arrayAdapter.notifyDataSetChanged();
                }
            });
        }
    }

}
