package com.staging.emexcart.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.staging.emexcart.Network.RestServiceBuilder;
import com.staging.emexcart.R;
import com.staging.emexcart.Utils.Sharedpreference;
import com.staging.emexcart.models.user_model.UserDetails;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BillingAddress extends Fragment {
    Sharedpreference sharedpreference;
    TextView tvfirstName,tvlastName,tvcompanyName,tvcountry,tvstreet,tvzip,tvphone,tvemail,tvCity;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View  view = inflater.inflate(R.layout.fragment_billing_address, container, false);
        sharedpreference = new Sharedpreference(getContext());
        initView(view);
        loadData();
        return view;
    }

    private void initView(View root) {
        tvfirstName = root.findViewById(R.id.editTextTextPersonName9);
         tvlastName= root.findViewById(R.id.editTextTextPersonName10);
        tvcompanyName = root.findViewById(R.id.editTextTextPersonName11);
        tvcountry = root.findViewById(R.id.editTextTextPersonName12);
        tvstreet = root.findViewById(R.id.editTextTextPersonName13);
        tvCity = root.findViewById(R.id.editTextTextPersonName14);
        tvzip = root.findViewById(R.id.editTextTextPersonName15);
        tvphone = root.findViewById(R.id.editTextTextPersonName16);
        tvemail = root.findViewById(R.id.editTextTextPersonName17);
    }

    private void loadData(){
        RestServiceBuilder.getService(getContext()).getUserDetails(sharedpreference.getUserID()).enqueue(new Callback<UserDetails>() {
            @Override
            public void onResponse(Call<UserDetails> call, Response<UserDetails> response) {
                if(response.isSuccessful()){
                    Log.e("MSG",response.body().getEmail());
                    initValues(response.body());
                }
                Log.e("MSG",response.code()+"");

            }

            @Override
            public void onFailure(Call<UserDetails> call, Throwable t) {
                Log.e("MSG",t+"");

            }
        });
    }

    private void initValues(UserDetails userDetails){
        tvfirstName.setText(userDetails.getBilling().getFirstName());
        tvlastName.setText(userDetails.getBilling().getLastName());
        if (TextUtils.isEmpty(userDetails.getBilling().getCompany())){
            tvcompanyName.setText(userDetails.getBilling().getCompany());

        }
        tvcountry.setText(userDetails.getBilling().getCountry());
        tvstreet.setText(userDetails.getBilling().getAddress1());
        tvCity.setText(userDetails.getBilling().getCity());
        tvzip.setText(userDetails.getBilling().getPostcode());
        tvphone.setText(userDetails.getBilling().getPhone());
        tvemail.setText(userDetails.getBilling().getEmail());
    }
}