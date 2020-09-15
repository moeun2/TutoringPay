package com.pay.tutoring.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private final static String BASE_URL ="http://101.101.217.108:3000" ;
    private final static String COOCON_URL = "https://dev.checkpay.co.kr";
    private static Retrofit retrofit = null;

    private RetrofitClient() {
    }

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL) // 요청을 보낼 base url을 설정한다.
                    .addConverterFactory(GsonConverterFactory.create()) // JSON 파싱을 위한 GsonConverterFactory를 추가한다.
                    .build();
        }
        return retrofit;
    }

    public static Retrofit getCooConClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(COOCON_URL) // 요청을 보낼 base url을 설정한다.
                    .addConverterFactory(GsonConverterFactory.create()) // JSON 파싱을 위한 GsonConverterFactory를 추가한다.
                    .build();
        }

        return retrofit;
    }
}
