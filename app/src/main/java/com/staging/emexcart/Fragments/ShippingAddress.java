package com.staging.emexcart.Fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.staging.emexcart.Network.RestServiceBuilder;
import com.staging.emexcart.R;
import com.staging.emexcart.Utils.Sharedpreference;
import com.staging.emexcart.models.user_model.UserDetails;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShippingAddress extends Fragment implements View.OnClickListener{
    private Sharedpreference sharedpreference;
    private TextView tv_firstname, tv_lastname, tv_company, tv_country, tv_address, tv_pincode, tv_city;
    private EditText et_frstname, et_lastname, et_cmpname, et_country, et_address, et_city, et_postcode;
    private Button bt_update;
    private FloatingActionButton fab_edit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_shipping_address, container, false);
        sharedpreference = new Sharedpreference(getContext());
        initView(root);
        loadData();
        clicks();
        return root;
    }

    private void clicks() {
        fab_edit.setOnClickListener(this);
    }

    private void initView(View root) {
        et_lastname = root.findViewById(R.id.et_lastname);
        et_frstname = root.findViewById(R.id.et_frstname);
        et_cmpname = root.findViewById(R.id.et_cmpname);
        et_country = root.findViewById(R.id.et_country);
        et_address = root.findViewById(R.id.et_address);
        et_city = root.findViewById(R.id.et_city);
        et_postcode = root.findViewById(R.id.et_postcode);

        bt_update = root.findViewById(R.id.bt_update);
        fab_edit = root.findViewById(R.id.fab_edit);

        tv_firstname = root.findViewById(R.id.tv_firstname);
        tv_lastname = root.findViewById(R.id.tv_lastname);
        tv_company = root.findViewById(R.id.tv_company);
        tv_country = root.findViewById(R.id.tv_country);
        tv_address = root.findViewById(R.id.tv_address);
        tv_pincode = root.findViewById(R.id.tv_pincode);
        tv_city = root.findViewById(R.id.tv_city);
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
       set_tv(userDetails);
       set_et(userDetails);
    }

    private void set_et(UserDetails userDetails) {
        et_frstname.setText(userDetails.getShipping().getFirstName());
        et_lastname.setText(userDetails.getShipping().getLastName());
        if (TextUtils.isEmpty(userDetails.getShipping().getCompany())) {
            et_cmpname.setText(userDetails.getShipping().getCompany());

        }
        et_country.setText(userDetails.getShipping().getCountry());
        et_address.setText(userDetails.getShipping().getAddress1());
        et_city.setText(userDetails.getShipping().getCity());
        et_postcode.setText(userDetails.getShipping().getPostcode());
    }

    private void set_tv(UserDetails userDetails) {
        tv_firstname.setText(userDetails.getShipping().getFirstName());
        tv_lastname.setText(userDetails.getShipping().getLastName());
        if (TextUtils.isEmpty(userDetails.getShipping().getCompany())) {
            tv_company.setText(userDetails.getShipping().getCompany());

        }
        tv_country.setText(userDetails.getShipping().getCountry());
        tv_address.setText(userDetails.getShipping().getAddress1());
        tv_city.setText(userDetails.getShipping().getCity());
        tv_pincode.setText(userDetails.getShipping().getPostcode());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fab_edit:
                setVisibility(View.VISIBLE);
        }
    }

    private void setVisibility(int visible) {
        et_lastname.setVisibility(visible);
        et_cmpname.setVisibility(visible);
        et_country.setVisibility(visible);
        et_address.setVisibility(visible);
        et_city.setVisibility(visible);
        et_frstname.setVisibility(visible);
        et_postcode.setVisibility(visible);
        bt_update.setVisibility(visible);
    }
}