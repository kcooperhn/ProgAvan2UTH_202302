package com.rrhh.uth.data.entity;

import java.text.DecimalFormat;
import java.time.LocalTime;

public class Empleado {

    private String nombre;
    private String identidad;
    private Double sueldo;
    private String telefono;
    private String horarioinicio;
    private String horariofin;
    private Integer puesto;
    private String nombrepuesto;

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getIdentidad() {
        return identidad;
    }
    public void setIdentidad(String identidad) {
        this.identidad = identidad;
    }
    public Double getSueldo() {
        return sueldo;
    }
    public void setSueldo(Double sueldo) {
        this.sueldo = sueldo;
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
	
	public Integer getPuesto() {
		return puesto;
	}
	public void setPuesto(Integer puesto) {
		this.puesto = puesto;
	}

   public String consultarNombreYSalario() {
	   DecimalFormat formato = new DecimalFormat("#,###.00");
	   return this.nombre+" -> L. "+formato.format(this.sueldo);
   }
	public String getHorarioinicio() {
		return horarioinicio;
	}
	public void setHorarioinicio(String horarioinicio) {
		this.horarioinicio = horarioinicio;
	}
	public String getHorariofin() {
		return horariofin;
	}
	public void setHorariofin(String horariofin) {
		this.horariofin = horariofin;
	}
	public String getNombrepuesto() {
		return nombrepuesto;
	}
	public void setNombrepuesto(String nombrepuesto) {
		this.nombrepuesto = nombrepuesto;
	}

	
}
