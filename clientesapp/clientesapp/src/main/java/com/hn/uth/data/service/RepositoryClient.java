package com.hn.uth.data.service;

import java.util.concurrent.TimeUnit;

import com.google.gson.GsonBuilder;
import com.hn.uth.network.BasicAuthInterceptor;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RepositoryClient {
	private Retrofit retrofit;
	private HttpLoggingInterceptor interceptor = null;
	
	public RepositoryClient(String url, Long timeout, String username, String password) {
		this.interceptor = new HttpLoggingInterceptor();
		this.interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
		
		OkHttpClient client = new OkHttpClient.Builder()
				.addInterceptor(interceptor)
				.addInterceptor(new BasicAuthInterceptor(username, password))
				.connectTimeout(timeout, TimeUnit.MILLISECONDS)
				.writeTimeout(timeout, TimeUnit.MILLISECONDS)
				.readTimeout(timeout, TimeUnit.MILLISECONDS)
				.build();
		retrofit = new Retrofit.Builder()
				.client(client)
				.baseUrl(url)
				.addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").create()))
				.build();
	}
	
	public DatabaseRepository getDatabaseService() {
		return retrofit.create(DatabaseRepository.class);
	}

	
}
