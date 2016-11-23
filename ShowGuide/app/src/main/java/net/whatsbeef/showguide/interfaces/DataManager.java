package net.whatsbeef.showguide.interfaces;

import net.whatsbeef.showguide.manager.Feedback;
import net.whatsbeef.showguide.model.ShowTimeModel;

/**
 * Created by k.
 */
public interface DataManager {
    void getShowListData(String url);

    void getShowListData(String url, Feedback feedback);

    ShowTimeModel getShowTimeData();
}
