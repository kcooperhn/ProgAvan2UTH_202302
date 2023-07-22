package com.hn.clima.data.entity;

import java.util.ArrayList;
import java.util.List;


public class ResponseWeather {
	private String name;
	private List<Weather> weather;
	private Main main;
	private Sys sys;
	
	public ResponseWeather() {
		super();
		this.name = "";
		this.weather = new ArrayList<>();
		this.weather.add(new Weather());
		this.main = new Main();
		this.sys = new Sys();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Weather> getWeather() {
		weather = (weather == null || weather.isEmpty())? new ArrayList<>(): weather;
		return weather;
	}
	public void setWeather(List<Weather> weather) {
		this.weather = weather;
	}
	public Main getMain() {
		main = main == null? new Main(): main;
		return main;
	}
	public void setMain(Main main) {
		this.main = main;
	}
	public Sys getSys() {
		sys = sys == null? new Sys(): sys;
		return sys;
	}
	public void setSys(Sys sys) {
		this.sys = sys;
	}

}
