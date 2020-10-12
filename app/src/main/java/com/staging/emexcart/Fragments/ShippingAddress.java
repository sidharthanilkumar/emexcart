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
import com.staging.emexcart.ViewPagerAdapter.ViewPagerAddressAdapter;
import com.staging.emexcart.models.user_model.UserDetails;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShippingAddress extends Fragment {
    Sharedpreference sharedpreference;
    TextView tvfirstName, tvlastName, tvcompanyName, tvcountry, tvstreet, tvzip, tvphone, tvemail, tvCity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_shipping_address, container, false);
        sharedpreference = new Sharedpreference(getContext());
        initView(root);
        loadData();
        return root;
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


    private void loadData() {
        RestServiceBuilder.getService(getContext()).getUserDetails(sharedpreference.getUserID()).enqueue(new Callback<UserDetails>() {
            @Override
            public void onResponse(Call<UserDetails> call, Response<UserDetails> response) {
                if (response.isSuccessful()) {
                    Log.e("MSG", response.body().getEmail());
                    initValues(response.body());
                }
                Log.e("MSG", response.code() + "");

            }

            @Override
            public void onFailure(Call<UserDetails> call, Throwable t) {
                Log.e("MSG", t + "");

            }
        });
    }

    private void initValues(UserDetails userDetails) {
        tvfirstName.setText(userDetails.getShipping().getFirstName());
        tvlastName.setText(userDetails.getShipping().getLastName());
        if (TextUtils.isEmpty(userDetails.getShipping().getCompany())){
            tvcompanyName.setText(userDetails.getShipping().getCompany());

        }
        tvcountry.setText(userDetails.getShipping().getCountry());
        tvstreet.setText(userDetails.getShipping().getAddress1());
        tvCity.setText(userDetails.getShipping().getCity());
        tvzip.setText(userDetails.getShipping().getPostcode());
    }
}