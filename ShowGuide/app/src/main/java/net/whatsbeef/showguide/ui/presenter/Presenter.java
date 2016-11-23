package net.whatsbeef.showguide.ui.presenter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.view.MenuItem;
import android.view.View;

import net.whatsbeef.showguide.interfaces.MVPInterface;

/**
 * Created by k.
 */
public abstract class Presenter<V extends MVPInterface> {
    private V mView;

    public Presenter(@NonNull V view) throws IllegalArgumentException {
        mView = view;

    }

    @NonNull
    protected V getView() {
        return mView;
    }


    protected Context getContext() {
        return mView.getContext();
    }

    protected FragmentManager getActivityFragmentManager() {
        return mView.getActivityFragmentManager();
    }


    public void onCreate(Bundle savedInstanceState) {
    }

    public void onSaveInstanceState(Bundle savedInstanceState) {
    }

    public void onDestroy() {
    }

    public void onStart() {
    }

    public void onResume() {
    }

    public void onPause() {
    }

    public void onStop() {
    }

    public void onBackPressed() {
    }

    public void onOptionsItemSelected(MenuItem item) {

    }

    public void onClick(View view) {

    }

    public void onRestoreInstanceState(Bundle in) {
    }
}
