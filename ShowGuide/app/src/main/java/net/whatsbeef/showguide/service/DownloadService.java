package net.whatsbeef.showguide.service;

import android.os.AsyncTask;


import net.whatsbeef.showguide.R;
import net.whatsbeef.showguide.ShowListApplication;
import net.whatsbeef.showguide.interfaces.AsyncTaskCallback;
import net.whatsbeef.showguide.utils.AppUtil;
import net.whatsbeef.showguide.utils.Constants;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by k.
 */
public class DownloadService extends AsyncTask<String, String, Object> {

    private  AsyncTaskCallback<Object> caller;

    public static DownloadService getInstance(AsyncTaskCallback<Object> caller) {
        return new DownloadService(caller);
    }

    private DownloadService(AsyncTaskCallback<Object> caller) {
        this.caller = caller;
    }

    @Override
    protected Object doInBackground(String... strings) {
        String url = strings[0];
        String responseStr;

        try {
            responseStr = downloadData(url);
        } catch (IOException e) {
            return e;
        }
        return responseStr;
    }

    @Override
    protected void onPostExecute(Object result) {
        if (result instanceof String) {
            if (!isCancelled())
                caller.onTaskCompletedSuccess(result);
        } else {
            if (!isCancelled())
                caller.onTaskCompletedFailure(ShowListApplication.getInstance().getString(R.string.download_error));
        }
    }

    private String downloadData(String requestUrl) throws IOException {
        InputStream inputStream;
        HttpURLConnection urlConnection;
        String resultStr = null;

        /* forming th java.net.URL object */
        URL url = new URL(requestUrl);
        urlConnection = (HttpURLConnection) url.openConnection();

        /* optional request header */
        urlConnection.setRequestProperty("Content-Type", "application/json");

        /* optional request header */
        urlConnection.setRequestProperty("Accept", "application/json");

        /* for Get request */
        urlConnection.setRequestMethod("GET");
        int statusCode = urlConnection.getResponseCode();

        /* 200 represents HTTP OK */
        if (statusCode == 200) {
            inputStream = new BufferedInputStream(urlConnection.getInputStream());
            resultStr = AppUtil.convertInputStreamToString(inputStream);
        }
        return resultStr;
    }

}

