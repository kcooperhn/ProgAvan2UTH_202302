package com.rrhh.uth.data.service;


import com.rrhh.uth.data.entity.Empleado;
import com.rrhh.uth.data.entity.Puesto;
import com.rrhh.uth.data.entity.ResponseEmployee;
import com.rrhh.uth.data.entity.ResponsePuesto;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface RRHHRepository {

	@Headers({
	    "Content-Type: application/json",
	    "Accept-Charset: utf-8",
	    "User-Agent: Retrofit-Sample-App"
	})
	@GET("/pls/apex/ingenieria_uth/rrhh/trabajadores")
	Call<ResponseEmployee> obtenerEmpleados();
	
	@Headers({
	    "Content-Type: application/json",
	    "Accept-Charset: utf-8",
	    "User-Agent: Retrofit-Sample-App"
	})
	@POST("/pls/apex/ingenieria_uth/rrhh/trabajadores")
	Call<ResponseBody> crearEmpleado(@Body Empleado nuevo);
	
	@Headers({
	    "Content-Type: application/json",
	    "Accept-Charset: utf-8",
	    "User-Agent: Retrofit-Sample-App"
	})
	@PUT("/pls/apex/ingenieria_uth/rrhh/trabajadores")
	Call<ResponseBody> actualizarEmpleado(@Body Empleado actualizar);
	
	
	@Headers({
	    "Content-Type: application/json",
	    "Accept-Charset: utf-8",
	    "User-Agent: Retrofit-Sample-App"
	})
	@GET("/pls/apex/ingenieria_uth/rrhh/puestos")
	Call<ResponsePuesto> obtenerPuestos();
	
	@Headers({
	    "Accept-Charset: utf-8",
	    "User-Agent: Retrofit-Sample-App"
	})
	@DELETE("/pls/apex/ingenieria_uth/rrhh/trabajadores")
	Call<ResponseBody> eliminarEmpleado(@Query("id") String identidad);
	
	
	@Headers({
	    "Content-Type: application/json",
	    "Accept-Charset: utf-8",
	    "User-Agent: Retrofit-Sample-App"
	})
	@POST("/pls/apex/ingenieria_uth/rrhh/puestos")
	Call<ResponseBody> crearPuesto(@Body Puesto nuevo);
	
	@Headers({
	    "Content-Type: application/json",
	    "Accept-Charset: utf-8",
	    "User-Agent: Retrofit-Sample-App"
	})
	@PUT("/pls/apex/ingenieria_uth/rrhh/puestos")
	Call<ResponseBody> actualizarPuesto(@Body Puesto actualizar);
	
	
	@Headers({
	    "Accept-Charset: utf-8",
	    "User-Agent: Retrofit-Sample-App"
	})
	@DELETE("/pls/apex/ingenieria_uth/rrhh/puestos")
	Call<ResponseBody> eliminarPuesto(@Query("id") Integer id);
}
