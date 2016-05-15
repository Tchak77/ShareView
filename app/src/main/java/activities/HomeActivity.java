package activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;
import java.util.concurrent.ExecutionException;

import asyncTasks.GetQueueMessage;
import asyncTasks.GetQueues;
import asyncTasks.SendQueueMessage;

public class HomeActivity extends AppCompatActivity {


    private String title = "";
    private String pseudo = "";
    private String address_ip;
    private String port;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
        builder.setTitle(R.string.pseudo);
        final EditText input = new EditText(HomeActivity.this);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //NOTHING TO DO
            }
        });

        builder.setView(input);
        final AlertDialog dialog = builder.create();
        dialog.show();

        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!input.getText().toString().trim().isEmpty()){
                    pseudo = input.getText().toString().trim();
                    createMenuView();
                    dialog.dismiss();
                }
            }
        });
    }

    private void createMenuView() {
        setContentView(R.layout.home_activity);

        Button newBoardBtn = (Button) findViewById(R.id.newBoardBtn);
        Button joinBoardBtn = (Button) findViewById(R.id.joinBoardBtn);


        if(newBoardBtn != null){
            newBoardBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
                builder.setTitle(R.string.title);
                final EditText input = new EditText(HomeActivity.this);
                builder.setView(input);

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //NOTHING TO DO
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                final AlertDialog titleDialog = builder.create();
                titleDialog.show();

                titleDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!input.getText().toString().trim().isEmpty()) {
                            EditText ipaddress = (EditText) findViewById(R.id.ipaddress);
                            EditText porttxt = (EditText) findViewById(R.id.port);


                            title = input.getText().toString().trim();
                            SendQueueMessage sendQueueMessage = new SendQueueMessage();
                            address_ip = ipaddress.getText().toString();
                            port = porttxt.getText().toString();
                            sendQueueMessage.execute("http://"+address_ip+":"+port+"/"+title, pseudo, "{\\\"admin\\\" : \\\"join\\\"}");
                            Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                            intent.putExtra("title", title);
                            intent.putExtra("pseudo", pseudo);
                            startActivity(intent);
                            titleDialog.dismiss();
                        }
                    }
                });
                }
            });
        }

        if (joinBoardBtn != null) {
            joinBoardBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EditText ipaddress = (EditText) findViewById(R.id.ipaddress);
                    EditText porttxt = (EditText) findViewById(R.id.port);
                    address_ip = ipaddress.getText().toString();
                    port = porttxt.getText().toString();
                    Log.v("toto",address_ip+":"+port);
                    GetQueues getTab = new GetQueues();
                    try {
                        final List<String> queues = getTab.execute("http://" + address_ip + ":" + port + "/").get();

                        if (queues != null) {
                            //Display the queues
                            setContentView(R.layout.list_queues);
                            ListView listView = (ListView) findViewById(R.id.list_queues);
                            ArrayAdapter arrayAdapter = new ArrayAdapter<>(HomeActivity.this, R.layout.list_item_queues, R.id.listitem_queues_textview, queues.toArray());
                            listView.setAdapter(arrayAdapter);

                            //Pick the queue
                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    String boardName = queues.get(position);
                                    GetQueueMessage getQueueMessage = new GetQueueMessage();
                                    getQueueMessage.execute("http://" + address_ip + ":" + port + "/",boardName);
                                }
                            });
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

}
