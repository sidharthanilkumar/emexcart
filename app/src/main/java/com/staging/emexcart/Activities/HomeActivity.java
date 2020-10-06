package com.staging.emexcart.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.staging.emexcart.R;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    private RelativeLayout search;
    private Button signup;
    private BottomNavigationView navView;
    private AppBarConfiguration appBarConfiguration;
    private Toolbar toolbarr;
    private DrawerLayout drawer;
    private TextView nv_login, nv_myorders, nv_address, nv_acdetails, nv_changepsw, nv_logout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);

        Initial();
        CallDrawer();
        BottomNav();
        Clicks();

    }

    private void Initial() {
        navView = findViewById(R.id.nav_view);
        search = findViewById(R.id.search_button);
        signup = findViewById(R.id.btnSignIn);
        toolbarr = findViewById(R.id.toolbarr);
        drawer = findViewById(R.id.drawlayout);
        nv_login = findViewById(R.id.nv_login);
        nv_myorders = findViewById(R.id.nv_myorders);
        nv_address = findViewById(R.id.nv_address);
        nv_acdetails = findViewById(R.id.nv_acdetails);
        nv_changepsw = findViewById(R.id.nv_changepsw);
        nv_logout = findViewById(R.id.nv_logout);
    }

    private void Clicks() {
        search.setOnClickListener(this);
        signup.setOnClickListener(this);
        nv_login.setOnClickListener(this);
        nv_myorders.setOnClickListener(this);
        nv_acdetails.setOnClickListener(this);
        nv_address.setOnClickListener(this);
        nv_changepsw.setOnClickListener(this);
        nv_logout.setOnClickListener(this);

    }

    private void BottomNav() {
        appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_all_profile, R.id.navigation_mailbox, R.id.navigation_matches, R.id.navigation_mypofile)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(navView, navController);
    }

    private void CallDrawer() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbarr, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.orange));
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_button:
                goTo(ProductDetailsActivity.class);
                break;
            case R.id.btnSignIn:
                goTo(LoginActivity.class);
                break;
            case R.id.nv_login:
                goTo(LoginActivity.class);
                break;
            case R.id.nv_myorders:
//                goTo();
                break;
            case R.id.nv_address:
                goTo(AddressActivity.class);
                break;
            case R.id.nv_acdetails:
                goTo(AccountDetails.class);
                break;
            case R.id.nv_changepsw:
                goTo(ChangePassword.class);
                break;
            case R.id.nv_logout:
//                goTo();
                break;

        }
    }

    private void goTo(Class name) {
        startActivity(new Intent(getApplicationContext(), name));

    }
}