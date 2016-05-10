package ir2.esipe.shareview;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import fragments.BoardFragment;
import fragments.ChatFragment;
import fragments.OptionsFragment;
import fragments.UsersFragment;

public class MainActivity extends AppCompatActivity implements OptionsFragment.OnFragmentInteractionListener, BoardFragment.OnFragmentInteractionListener, UsersFragment.OnFragmentInteractionListener, ChatFragment.OnFragmentInteractionListener{

    private String title;
    private FragmentManager fragmentManager;
    private BoardFragment boardFragment;
    private OptionsFragment optionsFragment;
    private ChatFragment chatFragment;
    private UsersFragment usersFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boardFragment = new BoardFragment();
        optionsFragment = new OptionsFragment();
        chatFragment = new ChatFragment();
        usersFragment = new UsersFragment();

        fragmentManager = getFragmentManager();


        fragmentManager.beginTransaction()
                .add(R.id.fragment_container, new BoardFragment())
                .commit();

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        title = getIntent().getStringExtra("title");
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);


        TextView toolbarOption = (TextView)findViewById(R.id.toolbarOption);
        toolbarOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragment_container, optionsFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        TextView toolbarBoard = (TextView) findViewById(R.id.toolbarBoard);
        toolbarBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragment_container, boardFragment);
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });

        TextView toolbarChat = (TextView) findViewById(R.id.toolbarChat);
        toolbarChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragment_container, chatFragment);
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });

        TextView toolbarUser = (TextView) findViewById(R.id.toolbarUsers);
        toolbarUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragment_container, usersFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
