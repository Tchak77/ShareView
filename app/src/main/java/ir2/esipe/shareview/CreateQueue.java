package ir2.esipe.shareview;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class CreateQueue  extends AsyncTask<String, Void, Integer> {

    @Override
    protected Integer doInBackground(String... params) {
        try{
            URL url = new URL(params[0]);
            JSONObject cred = new JSONObject();
            JSONObject parent=new JSONObject();
            parent.put("author", "me");
            parent.put("message", cred);

            HttpURLConnection httpcon = (HttpURLConnection) url.openConnection();
            httpcon.setDoOutput(true);
            httpcon.setRequestProperty("Content-Type", "application/json");
            httpcon.setRequestProperty("Accept", "application/json");
            httpcon.setRequestMethod("POST");
            httpcon.connect();

            OutputStream os = httpcon.getOutputStream();
            os.write(parent.toString().getBytes("UTF-8"));
            os.close();

            InputStream inputStream = httpcon.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                // Nothing to do.
                return null;
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
            }
            Log.v("[ASSYNCTASK]", buffer.toString());
        }
        catch (Exception e){

        }
        return null;
    }

}