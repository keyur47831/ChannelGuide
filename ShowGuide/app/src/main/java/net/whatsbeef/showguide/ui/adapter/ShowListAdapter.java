package net.whatsbeef.showguide.ui.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.whatsbeef.showguide.BR;
import net.whatsbeef.showguide.R;
import net.whatsbeef.showguide.model.Result;

import java.util.List;

/**
 * Created by k.
 */
public class ShowListAdapter<T extends Result> extends RecyclerView.Adapter<ShowListAdapter.ShowListViewHolder> {
    List<T> dataList;

    public class ShowListViewHolder extends RecyclerView.ViewHolder {
        private ViewDataBinding binding;

        public ShowListViewHolder(View view) {
            super(view);
            binding = DataBindingUtil.bind(view);
        }

        public ViewDataBinding getBinding() {
            return binding;
        }
    }

    public ShowListAdapter(List<T> dataList) {
        this.dataList = dataList;
    }

    public void addData(List<T> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    @Override
    public ShowListAdapter.ShowListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_show_list, parent, false);
        return new ShowListViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ShowListAdapter.ShowListViewHolder holder, int position) {
        T data = dataList.get(position);
        holder.getBinding().setVariable(BR.viewModel, data);
        holder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
