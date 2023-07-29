package com.hn.clima.data.entity;

import java.time.LocalDateTime;

public class ClimaData {

	private LocalDateTime fecha;
    private String descripcion;
    private Double temperatura;
    private Integer humedad;
    private Double sensaciontermica;
    private String pais;
    private String lugar;
    
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Double getTemperatura() {
		return temperatura;
	}
	public void setTemperatura(Double temperatura) {
		this.temperatura = temperatura;
	}
	public Integer getHumedad() {
		return humedad;
	}
	public void setHumedad(Integer humedad) {
		this.humedad = humedad;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public String getLugar() {
		return lugar;
	}
	public void setLugar(String lugar) {
		this.lugar = lugar;
	}
	public Double getSensaciontermica() {
		return sensaciontermica;
	}
	public void setSensaciontermica(Double sensaciontermica) {
		this.sensaciontermica = sensaciontermica;
	}
	public LocalDateTime getFecha() {
		return fecha;
	}
	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}
	

}
