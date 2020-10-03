package com.staging.emexcart.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);

        navView = findViewById(R.id.nav_view);
        search = findViewById(R.id.search_button);
        signup = findViewById(R.id.btnSignIn);
        toolbarr = findViewById(R.id.toolbarr);
        drawer = findViewById(R.id.drawlayout);


        CallDrawer();
        BottomNav();
        Clicks();

    }

    private void Clicks() {
        search.setOnClickListener(this);
        signup.setOnClickListener(this);
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


    public void loadFragment(Fragment fragment) {
        String backStateName = fragment.getClass().getName();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.nav_host_fragment, fragment);
        fragmentTransaction.addToBackStack(backStateName);
        fragmentTransaction.commit();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_button:
                startActivity(new Intent(getApplicationContext(), ProductListActivity.class));
                break;
            case R.id.btnSignIn:
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                break;
        }
    }
}