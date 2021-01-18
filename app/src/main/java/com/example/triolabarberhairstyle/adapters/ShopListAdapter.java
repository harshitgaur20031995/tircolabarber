package com.example.triolabarberhairstyle.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.triolabarberhairstyle.databinding.ShopRowBinding;
import com.example.triolabarberhairstyle.models.Product;
import com.example.triolabarberhairstyle.repositories.ShopRepo;
import com.example.triolabarberhairstyle.viewmodels.ShopViewModel;
import com.google.protobuf.Internal;

import java.util.List;

public class ShopListAdapter extends ListAdapter<Product, ShopListAdapter.shopViewHolder> {

    ShopInterface shopInterface;

    public ShopListAdapter(ShopInterface shopInterface) {
        super(Product.itemCallback);
        this.shopInterface = shopInterface;
    }

    @NonNull
    @Override
    public shopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ShopRowBinding shopRowBinding = ShopRowBinding.inflate(layoutInflater, parent, false);
        shopRowBinding.setShopInterface(shopInterface);
        return new shopViewHolder(shopRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull shopViewHolder holder, int position) {
        Product product = getItem(position);
        holder.shopRowBinding.setProduct(product);
        holder.shopRowBinding.executePendingBindings();
    }

    class shopViewHolder extends RecyclerView.ViewHolder {
        ShopRowBinding shopRowBinding;

        public shopViewHolder(ShopRowBinding binding) {
            super(binding.getRoot());
            this.shopRowBinding = binding;
        }
    }

    public interface ShopInterface {
        void addItem(Product product);

        void onItemClick(Product product);
    }

}
