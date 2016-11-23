package net.whatsbeef.showguide.service;

import net.whatsbeef.showguide.interfaces.AsyncTaskCallback;

/**
 * Created by k.
 */
public class ServiceFactory {
    public static DownloadService getDownloadService(AsyncTaskCallback<Object> caller) {
        return DownloadService.getInstance(caller);
    }

    public static ParseService getParseService(AsyncTaskCallback<Object> caller) {
        return ParseService.getInstance(caller);
    }
}
