package ir2.esipe.shareview;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetQueues extends AsyncTask<String, Void, List<String>> {

    @Override
    protected List<String> doInBackground(String... params) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        try {
            URL url = new URL(params[0]);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            if (inputStream == null) {
                // Nothing to do.
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));
            List<String> queues = new ArrayList<>();
            String line = reader.readLine();
            if(line == null){
                return null;
            }

            Pattern pattern = Pattern.compile("\"(.[^\\\"]*)\"");
            Matcher matcher = pattern.matcher(line);
            while (matcher.find())
            {
                queues.add(matcher.group(1));
            }
            return queues;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
