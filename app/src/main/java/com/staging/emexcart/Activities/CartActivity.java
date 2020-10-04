package com.staging.emexcart.Activities;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.staging.emexcart.R;
import com.staging.emexcart.TempStaticFiles.CartAdapter;
import com.staging.emexcart.TempStaticFiles.CartModel;
import com.staging.emexcart.TempStaticFiles.OrderAdapter;
import com.staging.emexcart.TempStaticFiles.OrderModel;

public class CartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_cart_fragemnt);
        setData();

    }
    private void setData(){
        String image = "https://img.republicworld.com/republic-prod/stories/images/15910331945ed53d6a9981c.jpeg";

        CartModel[] cartModels = new CartModel[]{
                new CartModel( "Samsung A30","12000", image,"12000"),
                new CartModel( "I phone 6","12000", image,"12000"),
                new CartModel( "Mi Band","12000", image,"12000"),
                new CartModel( "Redmi 7","12000", image,"12000"),
        };

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycler_cartList);
        CartAdapter adapter = new CartAdapter(getApplicationContext(),cartModels);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(adapter);
    }
}