package com.staging.emexcart.ViewPagerUi;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.staging.emexcart.Activities.ProductDetailsActivity;
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


public class FeaturedProducts extends Fragment {
    private RecyclerView recyclerView;
    private ProductListAdapter adapter;
    private List<ProductDetails> productDetails;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_featured, container, false);
        initView(root);
        loadData();
//        initRecycler();
        return root;
    }

    private void initView(View root) {
        recyclerView = root.findViewById(R.id.recycler_premium);
    }

    private void loadData() {
        try {
            Map<String, String> params = new LinkedHashMap<>();
            params.put("featured", "true");
            params.put("lang","en");
            RestServiceBuilder.getService(getContext()).getAllProducts(params).enqueue(new Callback<List<ProductDetails>>() {
                @Override
                public void onResponse(Call<List<ProductDetails>> call, Response<List<ProductDetails>> response) {
                    if (response.isSuccessful()){
                        productDetails =response.body();
                        initRecycler();
                    }
                    else {
                        Toast.makeText(getActivity(),response.code()+"",Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<List<ProductDetails>> call, Throwable t) {
                    Toast.makeText(getActivity(),t+"",Toast.LENGTH_LONG).show();
                    Log.e("woooerror",t+"");
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void initRecycler() {
        adapter = new ProductListAdapter(getActivity(),productDetails, new ProductListAdapter.AdapterListner() {
            @Override
            public void onclick(String user_id) {
                Intent intent = new Intent(getActivity(), ProductDetailsActivity.class);
                intent.putExtra("id",user_id);
                startActivity(intent);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void addProducts(List<ProductDetails> productList) {

        // Add Products to mostLikedProductList
        if (productList.size() > 0) {
            productDetails.addAll(productList);
        }
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getStatus() != null && !"publish".equalsIgnoreCase(productList.get(i).getStatus())) {
                for (int j = 0; j < productDetails.size(); j++) {
                    if (productList.get(i).getId() == productDetails.get(j).getId()) {
                        productDetails.remove(j);
                    }
                }
            }
        }
        adapter.notifyDataSetChanged();
        // Change the Visibility of emptyRecord Text based on ProductList's Size
//        if (productAdapter.getItemCount() == 0) {
//            emptyRecord.setVisibility(View.VISIBLE);
//        } else {
//            emptyRecord.setVisibility(View.GONE);
//        }

    }

}