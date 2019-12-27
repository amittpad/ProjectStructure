package com.india.projectstructure.retrofitsdk;

import com.india.projectstructure.retrofitsdk.response.BannerResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIInterface {
    @GET("12bzic")
    Call<BannerResponse> getBannerResponse();

//    @FormUrlEncoded
//    @POST("registration")
//    Call<RegistrationResponse> getRegistrationResponse(
//            @Field("first_name") String firstName,
//            @Field("last_name") String lastName,
//            @Field("email") String email,
//            @Field("password") String password,
//            @Field("phone") String phone,
//            @Field("state") String state,
//            @Field("city") String city,
//            @Field("address1") String address1);

}

