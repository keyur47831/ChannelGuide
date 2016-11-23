package net.whatsbeef.showguide.service;

import android.os.AsyncTask;
import android.util.Log;

import net.whatsbeef.showguide.R;
import net.whatsbeef.showguide.ShowListApplication;
import net.whatsbeef.showguide.interfaces.AsyncTaskCallback;
import net.whatsbeef.showguide.model.ShowTimeModel;
import net.whatsbeef.showguide.utils.Constants;
import net.whatsbeef.showguide.utils.ParseUtil;

import org.json.JSONException;

/**
 * Created by k.
 */
public class ParseService extends AsyncTask<String, String, Object> {

    private static final String TAG = ParseService.class.getName();
    private AsyncTaskCallback<Object> caller;

    public static ParseService getInstance(AsyncTaskCallback<Object> caller) {
        return new ParseService(caller);
    }

    private ParseService(AsyncTaskCallback<Object> caller) {
        this.caller = caller;
    }


    @Override
    protected Object doInBackground(String... strings) {
        String jsonStr = strings[0];
        ShowTimeModel showTimeModel = null;

        try {
            if (!isCancelled())
            showTimeModel = ParseUtil.parseShowData(jsonStr);
        } catch (JSONException e) {
            return e;
        }
        return showTimeModel;
    }

    @Override
    protected void onPostExecute(Object result) {

        if (result instanceof ShowTimeModel) {
            if (!isCancelled())
                caller.onTaskCompletedSuccess(result);
        } else {
            if (!isCancelled())
                caller.onTaskCompletedFailure(ShowListApplication.getInstance().getString(R.string.parse_error));
        }
    }
}
