package com.hn.uth.network;

import java.io.IOException;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class BasicAuthInterceptor implements Interceptor {

	private String credenciales;

	public BasicAuthInterceptor(String username, String password) {
		this.credenciales = Credentials.basic(username, password);
	}

	@Override
	public Response intercept(Chain chain) throws IOException {
		Request request = chain.request();
		Request authenticatedRequest = request.newBuilder()
				.header("Authorization", this.credenciales).build();
		
		return chain.proceed(authenticatedRequest);
	}
	
	
}
