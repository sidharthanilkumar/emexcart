package com.staging.emexcart.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.staging.emexcart.Adapter.AllProductAdapter;
import com.staging.emexcart.Adapter.ProductListAdapter;
import com.staging.emexcart.Network.RestServiceBuilder;
import com.staging.emexcart.R;
import com.staging.emexcart.models.product_model.ProductDetails;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductListActivity extends AppCompatActivity {
    private AllProductAdapter allProductAdapter;
    private List<ProductDetails> productDetails;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        initView();
        loadData();
    }

    private void initView() {
        recyclerView = findViewById(R.id.recycler_all);
    }
    private void loadData(){
        Map<String, String> params = new LinkedHashMap<>();
        params.put("on_sale", "true");
        params.put("lang","en");
        RestServiceBuilder.getService(getApplicationContext()).getAllProducts(params).enqueue(new Callback<List<ProductDetails>>() {
            @Override
            public void onResponse(Call<List<ProductDetails>> call, Response<List<ProductDetails>> response) {
                if (response.isSuccessful()) {
                    productDetails = response.body();
                    initRecycler();
                }else {
                    Toast.makeText(getApplicationContext(),response.code()+"",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<ProductDetails>> call, Throwable t) {
                Log.e("woooerror",t+"");

            }
        });
    }
    private void initRecycler(){
        allProductAdapter = new AllProductAdapter(getApplicationContext(),productDetails, new AllProductAdapter.AdapterListner() {
            @Override
            public void onclick(String id) {
                Intent intent = new Intent(getApplicationContext(),ProductDetailsActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(allProductAdapter);
        allProductAdapter.notifyDataSetChanged();
    }

}