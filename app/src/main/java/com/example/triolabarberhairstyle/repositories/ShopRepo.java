package com.example.triolabarberhairstyle.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.triolabarberhairstyle.models.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ShopRepo {
    private MutableLiveData<List<Product>> mutableProductList;

    public LiveData<List<Product>> getProducts(){
        if(mutableProductList == null){
            mutableProductList = new MutableLiveData<>();
            loadProducts();
        }
        return mutableProductList;
    }

    private void loadProducts(){
        List<Product> productList = new ArrayList<>();
        productList.add(new Product(UUID.randomUUID().toString(), "Shampoo per barba e capelli", 9.99, true, "https://i.ibb.co/vQQbynt/IMG-20201216-164426.jpg" ));
        productList.add(new Product(UUID.randomUUID().toString(), "Shampoo Dermo Calm", 9.99, true, "https://i.ibb.co/HKw26Y2/IMG-20201216-164917.jpg"));
        productList.add(new Product(UUID.randomUUID().toString(), "Shampoo Sebo Balance", 9.99, true, "https://i.ibb.co/HKw26Y2/IMG-20201216-164917.jpg"));
        productList.add(new Product(UUID.randomUUID().toString(), "Shampoo Anti Dandruff", 9.99, false, "https://i.ibb.co/HKw26Y2/IMG-20201216-164917.jpg"));
        productList.add(new Product(UUID.randomUUID().toString(), "Olio Barba", 9.99, true, "https://i.ibb.co/PQ3wr0y/IMG-20201216-164709.jpg"));
        productList.add(new Product(UUID.randomUUID().toString(), "Shampoo Barba", 9.99, true, "https://i.ibb.co/PQ3wr0y/IMG-20201216-164709.jpg"));
        productList.add(new Product(UUID.randomUUID().toString(), "Balsamo Barba", 9.99, true, "https://i.ibb.co/PQ3wr0y/IMG-20201216-164709.jpg"));
        mutableProductList.setValue(productList);
    }
}
