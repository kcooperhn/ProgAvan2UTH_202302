package com.hn.uth.data.service;

import com.hn.uth.data.entity.ResponseClientes;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface DatabaseRepository {

	@Headers({
           "Content-Type: application/json",
           "Accept-Charset: utf-8",
           "User-Agent: Retrofit-Clientes-App"
   })
   @GET("/pls/apex/ingenieria_uth/crmservice/clientes")
   Call<ResponseClientes> consultarClientes();
}
