package com.example.poem.Api;

import com.example.poem.Responce.DeleteResponse;
import com.example.poem.Responce.GetPoetryResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {
    @GET("getpoetry.php")
    Call<GetPoetryResponse> getpoetry();

    @FormUrlEncoded
    @POST("deletepoetry.php")
    Call<DeleteResponse> deletepoetry(@Field("poetry_id") String poetry_id);

//    @POST("addpoetry.php")
//    void Call(DeleteResponse) addpoetry(@Field("poetry") String poetryData)
    @FormUrlEncoded
   @POST("addpoetry.php")
    Call<DeleteResponse> addpoetry(@Field("poetry") String poetryData ,@Field("poet_name") String poet_name);
}
