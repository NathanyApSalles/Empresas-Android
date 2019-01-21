package com.example.nathany.ioasysenterprises.service;

import com.example.nathany.ioasysenterprises.model.EnterpriseCatalog;
import com.example.nathany.ioasysenterprises.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface UserService {

    //endpoints
    @POST("users/auth/sign_in")
    Call<User> userLogin(@Body User user);

    @GET("enterprises/")
    Call<EnterpriseCatalog> listEnterprises(@Header("access-token") String token, @Header("client") String client, @Header("uid") String uid);

}
