package com.india.projectstructure.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.india.projectstructure.Constant;
import com.india.projectstructure.PrefManager;
import com.india.projectstructure.R;
import com.india.projectstructure.baseclasses.AbstractProjectBaseActivity;
import com.india.projectstructure.baseclasses.BaseActivity;

import java.io.IOException;

public class SplashScreenActivity extends AbstractProjectBaseActivity {

    private long SPLASH_TIME_OUT = 4000;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**       Transparent Status Bar */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_splash_screen);
        userId = PrefManager.readSharedPreferencesData(SplashScreenActivity.this, Constant.KEY_USER_ID, "");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!userId.equals("")) {
                    startAnimatedActivity(new Intent(getApplicationContext(), DashboardActivity.class));
                }else {
                    startAnimatedActivity(new Intent(getApplicationContext(), LoginActivity.class));
                }
            }
        }, SPLASH_TIME_OUT);
    }

    private void startAnimatedActivity(Intent intent) {
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
    }
}
