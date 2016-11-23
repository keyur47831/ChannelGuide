package net.whatsbeef.showguide.interfaces;

/**
 * Created by k.
 */
public interface AsyncTaskCallback<T> {

    void onTaskCompletedSuccess(T result);

    void onTaskCompletedFailure(T result);
}