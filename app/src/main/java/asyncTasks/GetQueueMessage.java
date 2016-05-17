package asyncTasks;

import android.graphics.Canvas;
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import messages.MessagesManager;
import shapes.ShapesManager;

public class GetQueueMessage extends AsyncTask<String, Void, Void> {

    @Override
    protected Void doInBackground(String... params) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String message = null;
        int i = 0;
        int timeout = 1;
        ShapesManager shapesManager = ShapesManager.getSingleton();
        MessagesManager messagesManager = MessagesManager.getSingleton();
        try {
            do{
                URL url = new URL(params[0]+params[1]+"/"+i+"?timeout="+timeout);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();
                if (urlConnection.getResponseCode()!=HttpURLConnection.HTTP_OK){
                    break;
                }
                InputStream inputStream = urlConnection.getInputStream();
                if (inputStream == null) {
                    // Nothing to do.
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line = reader.readLine();
                if(line == null){
                    return null;
                }

                JSONObject jsonRootObject = new JSONObject(line);
                message = jsonRootObject.getString("message");
                if(message.contains("draw")){
                    shapesManager.JSONparser(message);
                    Canvas canvas = shapesManager.getCanvas();
                    if(canvas!=null){
                        shapesManager.drawShapes(canvas);
                    }
                } else {
                    String pseudo = jsonRootObject.getString("author");
                    messagesManager.JSONparser(pseudo, message);
                }
                if(message!=null){
                    i++;
                }
            }while(true);


        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
