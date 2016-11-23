package net.whatsbeef.showguide;

import android.app.Application;

import net.whatsbeef.showguide.interfaces.DataManager;
import net.whatsbeef.showguide.manager.DataManagerImpl;

/**
 * Created by k.
 */
public class ShowListApplication extends Application {
    private static ShowListApplication instance;
    private DataManager dataManager;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        dataManager = DataManagerImpl.getInstance(this);
    }

    public static ShowListApplication getInstance() {
        return ShowListApplication.instance;
    }

    public DataManager getDataManager() {
        return dataManager;
    }
}
