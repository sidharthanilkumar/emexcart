package com.staging.emexcart.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textfield.TextInputEditText;
import com.staging.emexcart.Network.RestServiceBuilder;
import com.staging.emexcart.R;
import com.staging.emexcart.models.user_model.UserData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    LinearLayout signup;
    TextInputEditText email, password;
    Button signIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initController();
    }

    private void initView() {
        signup = findViewById(R.id.btnSignUp);
        email = findViewById(R.id.edtEmail);
        password = findViewById(R.id.edtPassword);
        signIn = findViewById(R.id.btnSignIn);
    }


    private void initController() {
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegistrationActivity.class);
                startActivity(intent);
            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emails = email.getText().toString();
                String passwords = password.getText().toString();
                RestServiceBuilder.getService(getApplicationContext()).processLogin(emails,passwords).enqueue(new Callback<UserData>() {
                    @Override
                    public void onResponse(Call<UserData> call, Response<UserData> response) {
                        if (response.isSuccessful()){
                            Toast.makeText(getApplicationContext(),response.code()+"",Toast.LENGTH_LONG).show();
                        }else {
                            Toast.makeText(getApplicationContext(),response.code()+"",Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<UserData> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),t+"",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

    private void loginClick(){

    }


}