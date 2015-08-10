package com.example.fridge.webservice;

import com.example.fridge.UserDetails;
import com.example.fridge.UserProductsDetails;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;

public interface RESTfulService {

    String ENDPOINT = "http://52.10.2.170";

    String AuthorizationHeader = "Accept";


    @POST("/Register")
    void registerAccount(@Body ArrayList<String> register, Callback<ServiceResponse> responseCallback);

    @POST("/Auth")
    void loginAccount(@Body UserDetails user, Callback<UserProductsDetails> responseCallback);

    @POST("/GetMissings")
    void getMissings(@Body UserDetails user, Callback<UserProductsDetails> responseCallback);

     /*
    @FormUrlEncoded
    @POST("/accounts/{user}/verify")
    void verifyAccount(@Path("user") String accountName, @Field("verifyCode") String verifyCode, Callback<ServiceResponse> responseCallback);

    @GET("/accounts/sync")
    List<String> getSyncContacts(@Query("syncMarker") long syncMarker, @Query("offset") Integer offset, @Query("limit") Integer limit);


    @FormUrlEncoded
    @POST("/gcm/register")
    ServiceResponse registerGCM(@Field("gcmID") String GCMRegID);

    @POST("/gcm/verify")
    void verifyGCMIDInServer(Callback<ServiceResponse> responseCallback);

    @POST("/messages/location")
    void sendLocation(@Body LocationMessage message, Callback<ServiceResponse> responseCallback);


    @FormUrlEncoded
    @POST("gcm/register")
    void registerGCM(@Header(AuthorizationHeader) String apiKey,  @Field("gcmID") String GCMRegID, Callback<ServiceResponse> responseCallback);

    @POST("/gcm/verify")
    void verifyGCMIDInServer(@Header(AuthorizationHeader) String apiKey,  Callback<ServiceResponse> responseCallback);

    @POST("/messages/location")
    void sendLocation(@Header(AuthorizationHeader) String apiKey, @Body LocationMessage message, Callback<ServiceResponse> responseCallback);
    */
}
