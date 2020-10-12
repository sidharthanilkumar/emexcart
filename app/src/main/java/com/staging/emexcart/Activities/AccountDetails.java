package com.staging.emexcart.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.staging.emexcart.Network.RestServiceBuilder;
import com.staging.emexcart.R;
import com.staging.emexcart.Utils.Sharedpreference;
import com.staging.emexcart.models.user_model.UserDetails;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountDetails extends AppCompatActivity {
    Sharedpreference sharedpreference;
    TextView tvFirstName, tvLastName, tvDisplayName, tvEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_details);
        sharedpreference = new Sharedpreference(getApplicationContext());
        initView();
        loadData();
    }

    private void initView() {
        tvFirstName = findViewById(R.id.editTextTextPersonName);
        tvLastName = findViewById(R.id.editTextTextPersonName2);
        tvDisplayName = findViewById(R.id.editTextTextPersonName3);
        tvEmail = findViewById(R.id.editTextTextPersonName4);
    }

    private void loadData() {
        RestServiceBuilder.getService(getApplicationContext()).getUserDetails(sharedpreference.getUserID()).enqueue(new Callback<UserDetails>() {
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

    private void initValues(UserDetails body) {
      tvFirstName.setText(body.getFirstName());
      tvLastName.setText(body.getLastName());
      tvEmail.setText(body.getEmail());
      tvDisplayName.setText(body.getDisplay_name());

    }
}