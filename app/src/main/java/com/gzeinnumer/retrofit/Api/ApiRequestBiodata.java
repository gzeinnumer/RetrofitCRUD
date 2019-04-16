package com.gzeinnumer.retrofit.Api;

import com.gzeinnumer.retrofit.Model.ResponseDeleteData;
import com.gzeinnumer.retrofit.Model.ResponseInsertData;
import com.gzeinnumer.retrofit.Model.ResponseReadData;
import com.gzeinnumer.retrofit.Model.ResponseUpdateData;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiRequestBiodata {
    @FormUrlEncoded
    @POST("insert.php")
    Call<ResponseInsertData> sendBiodata(@Field("nama") String nama,
                                         @Field("nim") String usia,
                                         @Field("jurusan") String jurusan,
                                         @Field("prodi") String prodi,
                                         @Field("alamat") String alamat);
    @GET("read.php")
    Call<ResponseReadData> getBiodata();

    @FormUrlEncoded
    @POST("delete.php")
    Call<ResponseDeleteData> sentDeleteData(@Field("id") String id);

    @FormUrlEncoded
    @POST("update.php")
    Call<ResponseUpdateData> sentUpdateData(@Field("id") String id,
                                            @Field("nama") String nama,
                                            @Field("nim") String usia,
                                            @Field("jurusan") String jurusan,
                                            @Field("prodi") String prodi,
                                            @Field("alamat") String alamat);
}
