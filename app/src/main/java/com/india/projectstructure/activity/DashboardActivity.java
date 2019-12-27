package com.india.projectstructure.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.india.projectstructure.ProgressDialog;
import com.india.projectstructure.R;
import com.india.projectstructure.baseclasses.BaseActivity;
import com.india.projectstructure.retrofitsdk.APIClient;
import com.india.projectstructure.retrofitsdk.APIInterface;
import com.india.projectstructure.retrofitsdk.ResponsesSingleton;
import com.india.projectstructure.retrofitsdk.model.BannerImage;
import com.india.projectstructure.retrofitsdk.response.BannerResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardActivity extends BaseActivity {

    boolean doubleBackToExitPressedOnce = false;
    private ArrayList<BannerImage> bannerImageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_dashboard, null, false);
        mDrawerLayout.addView(contentView, 0);

        loadBannerFromServer();
    }

    private void loadBannerFromServer() {
        ProgressDialog.getInstance().show(DashboardActivity.this);
        APIInterface service = new APIClient.Builder().build(DashboardActivity.this).getAPIInterface();
        service.getBannerResponse().enqueue(new Callback<BannerResponse>() {
            @Override
            public void onResponse(Call<BannerResponse> call, Response<BannerResponse> response) {
                bannerImageList = new ArrayList<>();
                if (response.isSuccessful()) {
                    ProgressDialog.getInstance().dismiss();
                    if (response.body() != null && response.body().getResult().equalsIgnoreCase("success")) {
                        Toast.makeText(DashboardActivity.this, "Success", Toast.LENGTH_SHORT).show();

                        for (int i = 0; i < response.body().getBannerImage().size(); i++) {
                            String mId = response.body().getBannerImage().get(i).getId();
                            Log.e("ID", "-->" + mId);
                            String mImage = response.body().getBannerImage().get(i).getImage();
                            Log.e("Url", "-->" + mImage);

                            BannerImage bannerImage = new BannerImage();
                            bannerImage.setId(mId);
                            bannerImage.setImage(mImage);
                            bannerImageList.add(bannerImage);

                            //OR
                          //  ResponsesSingleton.getInstance().setBannerResponse(response.body());
                        }
                        //addBannerRecyclerView();
                    }
                }
            }

            @Override
            public void onFailure(Call<BannerResponse> call, Throwable t) {
                Toast.makeText(DashboardActivity.this, "Could not connect internet", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }
}
