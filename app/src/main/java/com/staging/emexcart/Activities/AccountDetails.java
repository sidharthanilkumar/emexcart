package com.staging.emexcart.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.staging.emexcart.Network.RestServiceBuilder;
import com.staging.emexcart.R;
import com.staging.emexcart.Utils.Sharedpreference;
import com.staging.emexcart.models.user_model.UserDetails;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountDetails extends AppCompatActivity implements View.OnClickListener {
    private Sharedpreference sharedpreference;
    private TextView tvFirstName, tvLastName, tvDisplayName, tvEmail;
    private TextView tv_email,tv_firstname,tv_lastname,tv_dispname;
    private EditText et_displayname,et_email,et_firstname,et_name;
    private Button bt_update;
    private FloatingActionButton fab_edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_details);
        sharedpreference = new Sharedpreference(getApplicationContext());
        initView();
        clicks();
        loadData();
    }

    private void clicks() {
        fab_edit.setOnClickListener(this);
    }

    private void initView() {
        tvFirstName = findViewById(R.id.tv_firstname);
        tvLastName = findViewById(R.id.tv_lastname);
        tvDisplayName = findViewById(R.id.tv_dispname);
        tv_email = findViewById(R.id.tv_email);
        fab_edit = findViewById(R.id.fab_edit);
        bt_update = findViewById(R.id.bt_update);
        et_firstname = findViewById(R.id.et_firstname);
        et_email = findViewById(R.id.et_email);
        et_displayname = findViewById(R.id.et_displayname);
        tv_dispname = findViewById(R.id.tv_dispname);
        tv_lastname = findViewById(R.id.tv_lastname);
        tv_firstname = findViewById(R.id.tv_firstname);
        et_name = findViewById(R.id.et_lastname);

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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fab_edit:
                setVisibility(View.VISIBLE);
        }
    }

    private void setVisibility(int visible) {
        et_name.setVisibility(visible);
        et_firstname.setVisibility(visible);
        et_email.setVisibility(visible);
        et_displayname.setVisibility(visible);
        bt_update.setVisibility(visible);
    }
}