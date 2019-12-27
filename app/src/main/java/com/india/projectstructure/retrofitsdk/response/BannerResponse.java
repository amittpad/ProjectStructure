package com.india.projectstructure.retrofitsdk.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.india.projectstructure.retrofitsdk.model.BannerImage;

import java.util.List;

public class BannerResponse {
    @SerializedName("banner_image")
    @Expose
    private List<BannerImage> bannerImage = null;
    @SerializedName("result")
    @Expose
    private String result;

    public List<BannerImage> getBannerImage() {
        return bannerImage;
    }

    public void setBannerImage(List<BannerImage> bannerImage) {
        this.bannerImage = bannerImage;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
