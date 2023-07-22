package com.hn.clima.data.entity;

public class ClimaData {

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
	

}
