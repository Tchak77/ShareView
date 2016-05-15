package ir2.esipe.shareview;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SendQueueMessage extends AsyncTask<String, Void, Void> {

    @Override
    protected Void doInBackground(String... params) {
        try{
            URL url = new URL(params[0]);
            String pseudo = params[1];
            String message = params[2];

            HttpURLConnection httpcon = (HttpURLConnection) url.openConnection();
            httpcon.setDoOutput(true);
            httpcon.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            httpcon.setRequestMethod("POST");
            httpcon.connect();

            DataOutputStream os = new DataOutputStream(httpcon.getOutputStream());
            Log.v("TEST","author="+pseudo+"&message=\""+message+"\"");
            os.write(("author="+pseudo+"&message=\""+message+"\"").getBytes("UTF-8"));
            os.close();

            InputStream inputStream = httpcon.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                // Nothing to do.
                return null;
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line = reader.readLine();
            if(line == null){
                return null;
            }
        }
        catch (Exception e){

        }
        return null;
    }

}