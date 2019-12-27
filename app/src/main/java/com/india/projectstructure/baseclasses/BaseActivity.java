package com.india.projectstructure.baseclasses;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.core.view.GravityCompat;

import com.google.android.material.navigation.NavigationView;
import com.india.projectstructure.PrefManager;
import com.india.projectstructure.R;
import com.india.projectstructure.activity.DashboardActivity;
import com.india.projectstructure.activity.LoginActivity;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

public class BaseActivity extends AbstractProjectBaseActivity implements NavigationView.OnNavigationItemSelectedListener {
    protected Toolbar toolbar;
    protected DrawerLayout mDrawerLayout;
    private NavigationView navigationView;
    private TextView navHeaderTitle, navHeaderEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        initializationView();

        setNavHeaderTitle();
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void initializationView() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        View headerView = navigationView.getHeaderView(0);
        navHeaderTitle = headerView.findViewById(R.id.nav_title_id);
        navHeaderEmail = headerView.findViewById(R.id.nav_email_id);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void setNavHeaderTitle() {
        navHeaderTitle.setText("Amit Pramanik");
        navHeaderEmail.setText("pramanikamit093@gmail.com");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        // Handle navigation view item clicks here.
        int id = menuItem.getItemId();
        Menu m = navigationView.getMenu();

        if (id == R.id.nav_home) {
            startLeftAnimatedActivity(new Intent(BaseActivity.this, DashboardActivity.class));
        } else if (id == R.id.nav_logout) {
            showLogoutAlertDialog();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void showLogoutAlertDialog() {
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(BaseActivity.this);
        alertDialog.setMessage("Are you sure want to logout?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        /** Clear the sharedPreferences Data */
                        PrefManager.clearSharedPreferencesData(BaseActivity.this);
                        startRightAnimatedActivity(new Intent(BaseActivity.this,LoginActivity.class));
                        Toast.makeText(BaseActivity.this, "Logout Successfully", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

        AlertDialog alert = alertDialog.create();
        alert.show();
    }

    protected void startLeftAnimatedActivity(Intent intent) {
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
    }

    protected void startRightAnimatedActivity(Intent intent) {
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
