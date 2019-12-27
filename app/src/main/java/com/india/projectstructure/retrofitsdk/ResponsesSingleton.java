package com.india.projectstructure.retrofitsdk;

import com.india.projectstructure.retrofitsdk.response.BannerResponse;

public class ResponsesSingleton {

    private static ResponsesSingleton instance;
    private BannerResponse bannerResponse;

    private ResponsesSingleton() {

    }

    public static ResponsesSingleton getInstance() {
        if (instance == null) {
            instance = new ResponsesSingleton();
        }
        return instance;
    }


    public BannerResponse getBannerResponse() {
        return bannerResponse;
    }

    public void setBannerResponse(BannerResponse bannerResponse) {
        this.bannerResponse = bannerResponse;
    }
}

