package net.whatsbeef.showguide.ui.presenter;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import net.whatsbeef.showguide.R;
import net.whatsbeef.showguide.ShowListApplication;
import net.whatsbeef.showguide.interfaces.DataManager;
import net.whatsbeef.showguide.interfaces.MVPInterface;
import net.whatsbeef.showguide.manager.Feedback;
import net.whatsbeef.showguide.model.ShowTimeModel;
import net.whatsbeef.showguide.ui.activity.ShowListActivity;
import net.whatsbeef.showguide.utils.Constants;
import net.whatsbeef.showguide.utils.GenericParcelable;

import java.util.Locale;

/**
 * Created by k.
 */
public class ShowListPresenter extends Presenter<MVPInterface> implements SwipeRefreshLayout.OnRefreshListener {
    @VisibleForTesting
    DataManager dataManager = ShowListApplication.getInstance().getDataManager();
    private boolean isScrollLoading;
    private boolean isRefreshed = false;
    private boolean isLastPage = false;
    private boolean isLoading = false;
    private Bundle backupData;
    public ShowListPresenter(@NonNull MVPInterface view) {
        super(view);
    }

    public ProgressDialog prepareProgressDialog() {
        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setIndeterminate(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);
        progressDialog.setMessage(getContext().getString(R.string.loading));
        return progressDialog;
    }

    public void onCreate(Bundle data) {
        backupData = data;
    }

    public void onResume() {
        if (backupData != null) {
            GenericParcelable<ShowTimeModel> restoredData = backupData.getParcelable(Constants.RECYCLER_DATA);
            isLastPage = restoredData.getValue().getCount() == restoredData.getValue().getResults().size();
            getView().bindData(restoredData.getValue());
        } else {
            isScrollLoading = false;
            loadShowListData(0);
        }
    }

    public void loadShowListData(int startIndex) {
        if (!isRefreshed)
            getView().showLoading();
        dataManager.getShowListData(String.format(Locale.ENGLISH,Constants.BASE_URL, startIndex), new Feedback<ShowTimeModel>() {

            @Override
            public void success(ShowTimeModel model) {
                if (!isRefreshed)
                    getView().hideLoading();
                else
                    isRefreshed = false;
                populateData(model);
                isLoading = false;


            }

            @Override
            public void error(String errReason) {
                isLoading =false;
                if (!isRefreshed)
                    getView().hideLoading();
                else {
                    isRefreshed = false;
                    getView().onError(null);
                }
                isScrollLoading = false;
                getView().showToastMessage(errReason);
            }
        });
    }

    private void populateData(ShowTimeModel data) {
        if (!isScrollLoading || isRefreshed) {
            isRefreshed=false;
            getView().bindData(data);
        } else {
            isScrollLoading = false;
            getView().updateData(data);
            ShowTimeModel item = ((ShowListActivity) getContext()).getViewModel();
            isLastPage = item.getCount() == item.getResults().size();
        }

    }

    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(Constants.RECYCLER_DATA, new GenericParcelable<>(((ShowListActivity) getContext()).getViewModel()));
    }

    public RecyclerView.OnScrollListener listener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            if (dy > 0) //check for scroll down
            {
                LinearLayoutManager mLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int visibleItemCount = mLayoutManager.getChildCount();
                int totalItemCount = mLayoutManager.getItemCount();
                int firstVisibleItemPosition = mLayoutManager.findFirstVisibleItemPosition();
                if (!isLoading && !isLastPage) {
                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                            && firstVisibleItemPosition >= 0
                            && totalItemCount <= ((ShowListActivity) getContext()).getViewModel().getCount()) {
                        loadShowListData(totalItemCount);
                        isLoading = true;
                        isScrollLoading = true;
                    }
                }
            }

        }
    };

    @Override
    public void onRefresh() {
        isScrollLoading = false;
        isRefreshed = true;
        if(isLastPage)
            isLastPage =false;
        loadShowListData(0);
    }

}
