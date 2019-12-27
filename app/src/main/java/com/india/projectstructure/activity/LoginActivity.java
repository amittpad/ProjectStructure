package com.india.projectstructure.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.india.projectstructure.Constant;
import com.india.projectstructure.PrefManager;
import com.india.projectstructure.R;
import com.india.projectstructure.baseclasses.AbstractProjectBaseActivity;

public class LoginActivity extends AbstractProjectBaseActivity implements View.OnClickListener {
    private EditText etEmail, etPassword;
    private String mEmail, mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**    Transparent Status Bar */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        setContentView(R.layout.activity_login);
        initializationView();
        findViewById(R.id.btn_login).setOnClickListener(this);

    }

    private void initializationView() {
        etEmail = findViewById(R.id.et_email_id);
        etPassword = findViewById(R.id.et_password_id);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn_login:
                submitLoginData();
                break;
            default:
                Toast.makeText(this, "Click Failed", Toast.LENGTH_SHORT).show();
        }
    }

    private void submitLoginData() {
        mEmail = etEmail.getText().toString().trim();
        mPassword = etPassword.getText().toString().trim();
        if (mEmail.equalsIgnoreCase("test@gmail.com") && mPassword.equalsIgnoreCase("12345")) {

            /**To save the login data in sharedPreferences */
            PrefManager.saveSharedPreferencesData(LoginActivity.this, Constant.KEY_USER_ID, "1");
            PrefManager.saveSharedPreferencesData(LoginActivity.this, Constant.KEY_USER_EMAIL, mEmail);
            PrefManager.saveSharedPreferencesData(LoginActivity.this, Constant.KEY_USER_PASSWORD, mPassword);
            startLeftAnimatedActivity(new Intent(LoginActivity.this, DashboardActivity.class));
            Toast.makeText(this, "Login Successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show();
        }

    }

    private void startLeftAnimatedActivity(Intent intent) {
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
    }
}


