package com.example.triolabarberhairstyle;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.DividerItemDecoration;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.triolabarberhairstyle.adapters.ShopListAdapter;
import com.example.triolabarberhairstyle.databinding.FragmentShopBinding;
import com.example.triolabarberhairstyle.models.Product;
import com.example.triolabarberhairstyle.viewmodels.ShopViewModel;
import com.google.android.material.snackbar.Snackbar;
import com.google.api.LogDescriptor;

import java.util.List;

public class ShopFragment extends Fragment implements ShopListAdapter.ShopInterface {

    private static final String TAG = "ShopFragment";
    FragmentShopBinding fragmentShopBinding;
    private ShopListAdapter shopListAdapter;
    private ShopViewModel shopViewModel;
    private NavController navController;

    public ShopFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentShopBinding = FragmentShopBinding.inflate(inflater, container, false);
        return fragmentShopBinding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        shopListAdapter = new ShopListAdapter(this);
        fragmentShopBinding.ShopRec.setAdapter(shopListAdapter);
        fragmentShopBinding.ShopRec.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));
        fragmentShopBinding.ShopRec.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.HORIZONTAL));

        shopViewModel = new ViewModelProvider(requireActivity()).get(ShopViewModel.class);
        shopViewModel.getProducts().observe(getViewLifecycleOwner(), new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                shopListAdapter.submitList(products);
            }
        });
        navController = Navigation.findNavController(view);
    }

    @Override
    public void addItem(Product product) {
        //    Log.d("addItem: " + product.toString());
        boolean isAdded = shopViewModel.addItemToCart(product);
        if (isAdded) {
            Snackbar.make(requireView(), product.getName() + " Aggiunto al carrello.", Snackbar.LENGTH_LONG)
                    .setAction("Checkout", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            navController.navigate(R.id.action_shopFragment_to_cartFragment);
                        }
                    })
                    .show();
        } else {
            Snackbar.make(requireView(), "Raggiunta quantità massima nel carrello.", Snackbar.LENGTH_LONG)
                    .show();
        }
    }

    @Override
    public void onItemClick(Product product) {
        Log.d(TAG, "onItemClick: " + product.toString());
        shopViewModel.setProduct(product);
        navController.navigate(R.id.action_shopFragment_to_productDetailFragment);

    }
}