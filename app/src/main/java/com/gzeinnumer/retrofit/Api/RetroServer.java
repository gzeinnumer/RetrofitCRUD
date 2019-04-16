package com.gzeinnumer.retrofit.Api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroServer {
    //private static final String base_url = "http://10.0.2.2/retrofit/";
    private static final String base_url = "http://192.168.0.121/retrofit/";

    private static Retrofit setInit(){
        return new Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static ApiRequestBiodata getInstance(){
        return setInit().create(ApiRequestBiodata.class);
    }
}
