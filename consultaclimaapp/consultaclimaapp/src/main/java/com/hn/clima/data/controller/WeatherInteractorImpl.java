package com.hn.clima.data.controller;

import java.io.IOException;
import java.time.LocalDateTime;

import com.hn.clima.data.entity.ClimaData;
import com.hn.clima.data.entity.ResponseWeather;
import com.hn.clima.data.service.WeatherRepositoryImpl;
import com.hn.clima.views.consulta.WeatherViewModel;

public class WeatherInteractorImpl implements WeatherInteractor {

	private WeatherRepositoryImpl modelo;
	private WeatherViewModel vista;
	
	public WeatherInteractorImpl(WeatherViewModel vista) {
		super();
		this.modelo = WeatherRepositoryImpl.getInstance("https://api.openweathermap.org", 800000L);
		this.vista = vista;
	}

	@Override
	public void consultarClima(String lat, String lon) {
		try {
			ResponseWeather respuesta = this.modelo.getWeather(lat, lon);
			this.vista.refrescarGridClima(convertirDatos(respuesta));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	private ClimaData convertirDatos(ResponseWeather respuesta) {
		
		ClimaData datos = new ClimaData();
		if(respuesta != null) {
			datos.setFecha(LocalDateTime.now());
			datos.setPais(respuesta.getSys().getCountry());
			datos.setLugar(respuesta.getName());
			datos.setDescripcion(respuesta.getWeather().get(0).getDescription());
			datos.setHumedad(respuesta.getMain().getHumidity());
			datos.setTemperatura(respuesta.getMain().getTemp());
			datos.setSensaciontermica(respuesta.getMain().getFeels_like());
		}
		return datos;
	}
}
