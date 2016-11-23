package net.whatsbeef.showguide.manager;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.VisibleForTesting;

import net.whatsbeef.showguide.interfaces.AsyncTaskCallback;
import net.whatsbeef.showguide.interfaces.DataManager;
import net.whatsbeef.showguide.model.ShowTimeModel;
import net.whatsbeef.showguide.service.DownloadService;
import net.whatsbeef.showguide.service.ParseService;
import net.whatsbeef.showguide.service.ServiceFactory;

/**
 * Created by k.
 */
public class DataManagerImpl implements DataManager, AsyncTaskCallback<Object> {
    private Context context;
    private static DataManagerImpl instance;
    private ShowTimeModel showTimeModel;
    private static DownloadService downloadService;
    private static ParseService parseService;
    private Feedback feedback;

    @VisibleForTesting
    static DataManagerImpl getInstance(Context ctx, DownloadService ds, ParseService ps) {
        if (instance == null) {
            instance = new DataManagerImpl(ctx);
        }
        downloadService = ds;
        parseService = ps;
        return instance;
    }

    public static DataManagerImpl getInstance(Context ctx) {
        if (instance == null) {
            instance = new DataManagerImpl(ctx);
        }
        return instance;
    }

    private DataManagerImpl(Context context) {
        this.context = context;
        downloadService = DownloadService.getInstance(this);
        parseService = ParseService.getInstance(this);
    }

    @Override
    public void getShowListData(String url) {


        if (AsyncTask.Status.FINISHED.equals(downloadService.getStatus())) {
            downloadService = ServiceFactory.getDownloadService(this);
        }
        downloadService.execute(url);
    }

    @Override
    public void getShowListData(String url, Feedback feedback) {
        this.feedback = feedback;
        if (AsyncTask.Status.FINISHED.equals(downloadService.getStatus())) {
            downloadService = ServiceFactory.getDownloadService(this);
        }
        downloadService.execute(url);
    }

    @Override
    public void onTaskCompletedSuccess(Object result) {
        if (result instanceof ShowTimeModel) {
            parseService = ServiceFactory.getParseService(this);
            showTimeModel = (ShowTimeModel) result;
            if (feedback != null) {
                feedback.success(result);
            }
        } else {
            //Start parsing in background thread
            parseService.execute((String) result);
        }
    }

    @Override
    public void onTaskCompletedFailure(Object result) {
        feedback.error((String) result);
    }
    @Override
    public ShowTimeModel getShowTimeData()
    {
        return showTimeModel;
    }

}
