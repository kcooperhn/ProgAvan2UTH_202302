package com.hn.uth.data.service;

import java.io.IOException;

import org.springframework.context.annotation.Bean;

import com.hn.uth.data.entity.ResponseClientes;

import retrofit2.Call;
import retrofit2.Response;

public class DatabaseRepositoryImpl {

	private static DatabaseRepositoryImpl instance;
	private RepositoryClient client;

    private DatabaseRepositoryImpl(String url, Long timeout, String username, String password) {
        this.client = new RepositoryClient(url, timeout, username, password);
    }

    @Bean
    public static DatabaseRepositoryImpl getInstance(String url, Long timeout, String username, String password) {
        if(instance == null) {
            synchronized (DatabaseRepositoryImpl.class) {
                if(instance == null) {
                    instance = new DatabaseRepositoryImpl(url, timeout, username, password);
                }
            }
        }
        return instance;
    }
    
    public ResponseClientes consultarClientes() throws IOException {
    	Call<ResponseClientes> call = client.getDatabaseService().consultarClientes();
    	Response<ResponseClientes> response = call.execute();
    	if(response.isSuccessful()) {
    		return response.body();
    	}else {
    		return null;
    	}
    	
    }
}
