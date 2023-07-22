package com.hn.clima.data.entity;

public class Main {
	private Double temp;
	private Integer humidity;
	private Double feels_like;
	
	public Main() {
		this.temp = 0.0;
		this.humidity = 0;
		this.feels_like = 0.0;
	}
	
	public Double getTemp() {
		return temp;
	}
	public void setTemp(Double temp) {
		this.temp = temp;
	}
	public Integer getHumidity() {
		return humidity;
	}
	public void setHumidity(Integer humidity) {
		this.humidity = humidity;
	}

	public Double getFeels_like() {
		return feels_like;
	}

	public void setFeels_like(Double feels_like) {
		this.feels_like = feels_like;
	}
	
	
}
