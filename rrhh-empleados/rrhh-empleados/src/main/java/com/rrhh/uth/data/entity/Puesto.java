package com.rrhh.uth.data.entity;

public class Puesto {

	private int idpuesto;
    private String nombre;
    private String departamento;

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDepartamento() {
        return departamento;
    }
    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
	public int getIdpuesto() {
		return idpuesto;
	}
	public void setIdpuesto(int idpuesto) {
		this.idpuesto = idpuesto;
	}

}
