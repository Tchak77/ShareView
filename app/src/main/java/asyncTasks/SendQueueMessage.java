package asyncTasks;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SendQueueMessage extends AsyncTask<String, Void, Void> {

    /**
     * This method send a message with the pseudo to the server for a queue
     * @param params ip address, port, queue, pseudo, message
     * @return
     */
    @Override
    protected Void doInBackground(String... params) {
        try{
            URL url = new URL("http://"+params[0]+":"+params[1]+"/"+params[2]);
            String pseudo = params[3];
            String message = params[4];

            HttpURLConnection httpcon = (HttpURLConnection) url.openConnection();
            httpcon.setDoOutput(true);
            httpcon.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            httpcon.setRequestMethod("POST");
            httpcon.connect();

            DataOutputStream os = new DataOutputStream(httpcon.getOutputStream());
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