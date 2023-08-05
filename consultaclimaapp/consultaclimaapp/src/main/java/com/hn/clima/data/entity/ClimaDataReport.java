package com.hn.clima.data.entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class ClimaDataReport implements JRDataSource {
	
	private List<ClimaData> datos;
	private int counter = -1;
	private int maxCounter = 0;

	public void setData(List<ClimaData> datos) {
		this.datos = datos;
		this.maxCounter = this.datos.size() -1;
	}

	public List<ClimaData> getDatos() {
		return datos;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	public int getMaxCounter() {
		return maxCounter;
	}

	public void setMaxCounter(int maxCounter) {
		this.maxCounter = maxCounter;
	}

	@Override
	public boolean next() throws JRException {
		if(counter < maxCounter) {
			counter++;
			return true;
		}
		return false;
	}

	@Override
	public Object getFieldValue(JRField jrField) throws JRException {
		if("FECHA".equals(jrField.getName())) {
			Locale locale = new Locale("es","HN");
			DateTimeFormatter format = DateTimeFormatter.ofPattern("EEE dd-MM-yy hh:mm a", locale);
			return format.format(datos.get(counter).getFecha());
		}else if("PAIS".equals(jrField.getName())) {
			return datos.get(counter).getPais();
		}else if("CIUDAD".equals(jrField.getName())) {
			return datos.get(counter).getLugar();
		}else if("DESCRIPCION".equals(jrField.getName())) {
			return datos.get(counter).getDescripcion();
		}else if("TEMPERATURA".equals(jrField.getName())) {
			return datos.get(counter).getTemperatura()+" °C";
		}else if("HUMEDAD".equals(jrField.getName())) {
			return datos.get(counter).getHumedad() + " %";
		}else if("SENSACION".equals(jrField.getName())) {
			return datos.get(counter).getSensaciontermica()+" °C";
		}
		return "";
	}
	
	
}
