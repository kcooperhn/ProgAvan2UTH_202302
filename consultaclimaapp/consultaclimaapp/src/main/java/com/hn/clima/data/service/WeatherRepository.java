package com.hn.clima.data.service;


import com.hn.clima.data.entity.ResponseWeather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface WeatherRepository {

	@Headers({
	    "Content-Type: application/json",
	    "Accept-Charset: utf-8",
	    "User-Agent: Retrofit-Sample-App"
	})
	@GET("/data/2.5/weather?appid=3b150054c81c996ed479e6fd43080109&lang=es&units=metric")
	Call<ResponseWeather> consultaClima(@Query("lat") String lat, @Query("lon") String lon);
	

}
