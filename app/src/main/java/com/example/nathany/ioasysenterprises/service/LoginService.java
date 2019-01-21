package com.example.nathany.ioasysenterprises.service;

import java.io.IOException;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginService {

    public static final String BASE_URL = "http://empresas.ioasys.com.br/api/v1/";

    public UserService API() {

        OkHttpClient okHttpClient = new OkHttpClient().newBuilder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();

                Request.Builder builder = originalRequest.newBuilder().header("Authorization",
                        Credentials.basic("email", "password"));

                Request newRequest = builder.build();
                return chain.proceed(newRequest);
            }
        }).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL) //seta endereço da API
                .addConverterFactory(GsonConverterFactory.create()) //conversor de serialização
                .client(okHttpClient) // client usado para requisições
                .build(); //cria instancia do Retrofit

        return retrofit.create(UserService.class);
    }

}
