package com.rrhh.uth.data.service;

import java.io.IOException;

import com.rrhh.uth.data.entity.Empleado;
import com.rrhh.uth.data.entity.Puesto;
import com.rrhh.uth.data.entity.ResponseEmployee;
import com.rrhh.uth.data.entity.ResponsePuesto;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;



public class RRHHRepositoryImpl {

	private static RRHHRepositoryImpl instance;
	private RepositoryClient client;
	
	private RRHHRepositoryImpl(String url, Long timeout) {
		this.client = new RepositoryClient(url, timeout);
	}
	
	//IMPLEMENTANDO PATRÓN SINGLETON
	public static RRHHRepositoryImpl getInstance(String url, Long timeout) {
		if(instance == null) {
			synchronized (RRHHRepositoryImpl.class) {
				if(instance == null) {
					instance = new RRHHRepositoryImpl(url, timeout);
				}
			}
		}
		return instance;
	}
	
	public ResponseEmployee getEmployees() throws IOException {
		Call<ResponseEmployee> call = client.getDatabaseService().obtenerEmpleados();
		Response<ResponseEmployee> response = call.execute(); //AQUI ES DONDE SE CONSULTA A LA URL DE LA BASE DE DATOS
		if(response.isSuccessful()){
			return response.body();
		}else {
			return null;
		}
	}
	
	public boolean createEmployee(Empleado nuevo) throws IOException {
		Call<ResponseBody> call = client.getDatabaseService().crearEmpleado(nuevo);
		Response<ResponseBody> response = call.execute(); //AQUI ES DONDE SE CONSULTA A LA URL DE LA BASE DE DATOS
		return response.isSuccessful();
	}
	
	public boolean updateEmployee(Empleado actualizar) throws IOException {
		Call<ResponseBody> call = client.getDatabaseService().actualizarEmpleado(actualizar);
		Response<ResponseBody> response = call.execute(); //AQUI ES DONDE SE CONSULTA A LA URL DE LA BASE DE DATOS
		return response.isSuccessful();
	}
	
	public ResponsePuesto getPuestos() throws IOException {
		Call<ResponsePuesto> call = client.getDatabaseService().obtenerPuestos();
		Response<ResponsePuesto> response = call.execute(); //AQUI ES DONDE SE CONSULTA A LA URL DE LA BASE DE DATOS
		if(response.isSuccessful()){
			return response.body();
		}else {
			return null;
		}
	}
	
	public boolean deleteEmployee(String identidad) throws IOException {
		Call<ResponseBody> call = client.getDatabaseService().eliminarEmpleado(identidad);
		Response<ResponseBody> response = call.execute(); //AQUI ES DONDE SE CONSULTA A LA URL DE LA BASE DE DATOS
		return response.isSuccessful();
	}
	
	
	public boolean createPuesto(Puesto nuevo) throws IOException {
		Call<ResponseBody> call = client.getDatabaseService().crearPuesto(nuevo);
		Response<ResponseBody> response = call.execute(); //AQUI ES DONDE SE CONSULTA A LA URL DE LA BASE DE DATOS
		return response.isSuccessful();
	}
	
	public boolean updatePuesto(Puesto actualizar) throws IOException {
		Call<ResponseBody> call = client.getDatabaseService().actualizarPuesto(actualizar);
		Response<ResponseBody> response = call.execute(); //AQUI ES DONDE SE CONSULTA A LA URL DE LA BASE DE DATOS
		return response.isSuccessful();
	}
	
	public boolean deletePuesto(Integer id) throws IOException {
		Call<ResponseBody> call = client.getDatabaseService().eliminarPuesto(id);
		Response<ResponseBody> response = call.execute(); //AQUI ES DONDE SE CONSULTA A LA URL DE LA BASE DE DATOS
		return response.isSuccessful();
	}
}
