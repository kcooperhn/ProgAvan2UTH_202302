package com.hn.clima.data.service;

import java.io.IOException;

import com.hn.clima.data.entity.ResponseWeather;

import retrofit2.Call;
import retrofit2.Response;

public class WeatherRepositoryImpl {

	private static WeatherRepositoryImpl instance;
	private RepositoryClient client;
	
	private WeatherRepositoryImpl(String url, Long timeout) {
		this.client = new RepositoryClient(url, timeout);
	}
	
	//IMPLEMENTANDO PATRÓN SINGLETON
	public static WeatherRepositoryImpl getInstance(String url, Long timeout) {
		if(instance == null) {
			synchronized (WeatherRepositoryImpl.class) {
				if(instance == null) {
					instance = new WeatherRepositoryImpl(url, timeout);
				}
			}
		}
		return instance;
	}
	
	public ResponseWeather getWeather(String lat, String lon) throws IOException {
		Call<ResponseWeather> call = client.getDatabaseService().consultaClima(lat, lon);
		Response<ResponseWeather> response = call.execute(); //AQUI ES DONDE SE CONSULTA A LA URL DE LA BASE DE DATOS
		if(response.isSuccessful()){
			return response.body();
		}else {
			return null;
		}
	}
}
