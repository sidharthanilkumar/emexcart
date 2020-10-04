package com.staging.emexcart.Fragments;

import android.os.Bundle;

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

public class CartFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_cart_fragemnt, container, false);
        setData(view);
        return view;
    }

    private void setData(View root){
        String image = "https://assets.mspimages.in/wp-content/uploads/2019/10/realme.jpg";

        CartModel[] cartModels = new CartModel[]{
                new CartModel( "Samsung A30","12000", image,"12000"),
                new CartModel( "I phone 6","12000", image,"12000"),
                new CartModel( "Mi Band","12000", image,"12000"),
                new CartModel( "Redmi 7","12000", image,"12000"),
                new CartModel( "Poco F1","12000", image,"12000"),
                new CartModel( "One Plus Nord","12000", image,"12000"),
        };

        RecyclerView recyclerView = (RecyclerView)root.findViewById(R.id.recycler_cartList);
        CartAdapter adapter = new CartAdapter(getActivity(),cartModels);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(adapter);
    }
}