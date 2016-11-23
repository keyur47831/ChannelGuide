package net.whatsbeef.showguide.interfaces;


import android.content.Context;
import android.support.v4.app.FragmentManager;

/**
 * Created by k.
 */
public interface MVPInterface {

    Context getContext();

    FragmentManager getActivityFragmentManager();

    void showLoading();

    void hideLoading();

    void showToastMessage(String msg);

    Object getViewModel();

    void bindData(Object data);

    void updateData(Object data);

    void onError(Object data);

}
