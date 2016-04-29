package whitbread.quintas.pt.whitbread.connection;


import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import whitbread.quintas.pt.whitbread.helpers.SystemConstants;
import whitbread.quintas.pt.whitbread.model.VenueRequest;

public class RemoteExecutor {

    private static RemoteExecutor instance;

    private RemoteExecutor(){    }

    public static RemoteExecutor getInstance() {
        if(instance == null)
            instance = new RemoteExecutor();
        return instance;
    }

    /**
     * Execute a GET venues request
     */
    public VenueRequest executeGet(String url) throws IOException {
        URL path = new URL(url);
        VenueRequest venueRequest = null;

        HttpURLConnection connection = (HttpURLConnection) path.openConnection();
        connection.setConnectTimeout(SystemConstants.REMOTE_EXECUTOR_TIMEOUT);

        InputStream input;
        int code = connection.getResponseCode();
        try {
            if(code != SystemConstants.REMOTE_EXECUTER_OK_CODE) {
                input = connection.getErrorStream();
            }
            else {
                input = connection.getInputStream();
            }
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(input, "iso-8859-1"), 8);
                Gson gson = new Gson();
                venueRequest = gson.fromJson(reader, VenueRequest.class);
            } catch (Exception e) {
                Log.e(SystemConstants.LOG_TAG, SystemConstants.LOG_BUFFER_EXCEPTION + e.getMessage());
            }
        }
        finally {
            connection.disconnect();
        }

        return venueRequest;
    }

}
