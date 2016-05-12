package ir2.esipe.shareview;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class HomeActivity extends AppCompatActivity {


    private String title = "";
    private String pseudo = "";
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
        setContentView(R.layout.activity_home_activity);

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
                            title = input.getText().toString().trim();
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
    }

}
