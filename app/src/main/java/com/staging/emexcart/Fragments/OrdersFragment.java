package com.staging.emexcart.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.staging.emexcart.R;
import com.staging.emexcart.TempStaticFiles.OrderAdapter;
import com.staging.emexcart.TempStaticFiles.OrderModel;
import com.staging.emexcart.models.order_model.OrderDetails;

public class OrdersFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_orders, container, false);
        setData(view);
        return view;
    }

    private void setData(View root){
        String image = "https://assets.mspimages.in/wp-content/uploads/2019/10/realme.jpg";

        OrderModel[] orderModels = new OrderModel[]{
                new OrderModel( "Samsung A30", image,"Deliverd","123456","12-03-2020","17000"),
                new OrderModel( "I phone 6", image,"Deliverd","123457","15-03-2020","27000"),
                new OrderModel( "Mi Band", image,"Deliverd","123458","18-03-2020","2000"),
                new OrderModel( "Redmi 7", image,"Deliverd","123459","21-03-2020","7000"),
                new OrderModel( "Poco F1", image,"Deliverd","123410","22-03-2020","17000"),
                new OrderModel( "One Plus Nord", image,"Deliverd","123411","26-03-2020","27000"),
                new OrderModel( "K 30 pro", image,"Deliverd","123412","28-03-2020","27000"),
                new OrderModel( "Pixel 4A", image,"Deliverd","123413","29-03-2020","27000"),
                new OrderModel( "S20 ultra", image,"Deliverd","123414","30-03-2020","77000"),
        };

        RecyclerView recyclerView = (RecyclerView)root.findViewById(R.id.recycler_order);
        OrderAdapter adapter = new OrderAdapter(getActivity(),orderModels);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(adapter);
    }
}