package com.gzeinnumer.retrofit.Api;

import com.gzeinnumer.retrofit.Model.ResponModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiRequestBiodata {
    @FormUrlEncoded
    @POST("insert.php")
    Call<ResponModel> sendBiodata(@Field("nama") String nama,
                                  @Field("usia") String usia,
                                  @Field("domisili") String domisili);
    @GET("read.php")
    Call<ResponModel> getBiodata();

    @FormUrlEncoded
    @POST("delete.php")
    Call<ResponModel> sentDeleteData(@Field("id") String id);

    @FormUrlEncoded
    @POST("update.php")
    Call<ResponModel> sentUpdateData(@Field("id") String id,
                                     @Field("nama") String nama,
                                     @Field("usia") String usia,
                                     @Field("domisili") String domisili);
}
