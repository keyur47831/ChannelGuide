package net.whatsbeef.showguide.ui.activity;


import android.app.ProgressDialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import net.whatsbeef.showguide.R;
import net.whatsbeef.showguide.databinding.ActivityShowListBinding;
import net.whatsbeef.showguide.interfaces.MVPInterface;
import net.whatsbeef.showguide.model.Result;
import net.whatsbeef.showguide.model.ShowTimeModel;
import net.whatsbeef.showguide.ui.adapter.ShowListAdapter;
import net.whatsbeef.showguide.ui.presenter.ShowListPresenter;

/**
 * Created by k.
 */
public class ShowListActivity extends PresentableActivity<ShowListPresenter> implements MVPInterface {

    ProgressDialog progressDialog;
    ShowTimeModel model;
    RecyclerView recyclerView;
    ActivityShowListBinding binding;
    LinearLayoutManager linearLayoutManager;
    SwipeRefreshLayout swipeRefreshLayout;
    ShowListAdapter<Result> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_show_list);
        setSupportActionBar(binding.toolbar);
        binding.setPresenter(getPresenter());
        recyclerView = binding.listView;
        swipeRefreshLayout = binding.swipeLayout;
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        swipeRefreshLayout.setOnRefreshListener(getPresenter());
        progressDialog = getPresenter().prepareProgressDialog();

    }

    @Override
    @NonNull
    protected ShowListPresenter createPresenter() {
        return new ShowListPresenter(this);

    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public ShowTimeModel getViewModel() {
        return model;
    }

    @Override
    public void showLoading() {
        progressDialog.show();
    }

    @Override
    public void hideLoading() {
        progressDialog.cancel();
    }

    @Override
    public void showToastMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void bindData(Object obj) {
        if (obj instanceof ShowTimeModel) {
            if (swipeRefreshLayout != null && swipeRefreshLayout.isRefreshing())
                swipeRefreshLayout.setRefreshing(false);
            init((ShowTimeModel) obj);
        }

    }

    @Override
    public void updateData(Object obj) {
        if (obj instanceof ShowTimeModel) {
            if (swipeRefreshLayout != null && swipeRefreshLayout.isRefreshing())
                swipeRefreshLayout.setRefreshing(false);
            ShowTimeModel data = (ShowTimeModel) obj;
            model.getResults().addAll(model.getResults().size() - 1, data.getResults());
            adapter.addData(model.getResults());
        }

    }

    private void init(ShowTimeModel data) {
        model = data;
        adapter = new ShowListAdapter<>(model.getResults());
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(getPresenter().listener);


    }

    @Override
    public FragmentManager getActivityFragmentManager() {
        return getSupportFragmentManager();
    }

    @Override
    public void onError(Object data) {
        if (swipeRefreshLayout.isRefreshing())
            swipeRefreshLayout.setRefreshing(false);
    }

}
