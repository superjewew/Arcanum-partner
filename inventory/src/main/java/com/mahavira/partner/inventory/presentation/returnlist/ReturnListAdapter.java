package com.mahavira.partner.inventory.presentation.returnlist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.mahavira.partner.base.entity.Boardgame;
import com.mahavira.partner.inventory.databinding.ItemReturnListBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by norman on 27/07/18.
 *
 */

public class ReturnListAdapter extends RecyclerView.Adapter<ReturnListAdapter.ReturnListViewHolder> {

    private List<Boardgame> mProducts = new ArrayList<>();

    private Context mContext;

    ReturnListViewModel mViewModel;

    ReturnListAdapter(Context context, ReturnListViewModel viewModel) {
        mContext = context;
        mViewModel = viewModel;
    }

    @NonNull
    @Override
    public ReturnListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);

        ItemReturnListBinding binding = ItemReturnListBinding.inflate(inflater, parent, false);

        return new ReturnListViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ReturnListViewHolder holder, int position) {
        Boardgame product = mProducts.get(position);
        holder.bind(product);
    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }

    void replaceProducts(List<Boardgame> products) {
        mProducts = products;
        notifyDataSetChanged();
    }

    class ReturnListViewHolder extends RecyclerView.ViewHolder {

        private ItemReturnListBinding mBinding;

        ReturnListViewHolder(ItemReturnListBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        void bind(Boardgame product) {
            ItemClickListener listener = product1 -> mViewModel.getProductClickedEvent().setValue(product1);
            mBinding.setProduct(product);
            mBinding.setClickListener(listener);
            mBinding.executePendingBindings();
        }
    }

    public interface ItemClickListener {
        void onItemClicked(Boardgame product);
    }
}
