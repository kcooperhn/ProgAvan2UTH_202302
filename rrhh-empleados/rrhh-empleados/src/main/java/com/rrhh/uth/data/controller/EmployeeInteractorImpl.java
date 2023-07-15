package com.rrhh.uth.data.controller;

import java.io.IOException;

import com.rrhh.uth.data.entity.Empleado;
import com.rrhh.uth.data.entity.ResponseEmployee;
import com.rrhh.uth.data.entity.ResponsePuesto;
import com.rrhh.uth.data.service.RRHHRepositoryImpl;
import com.rrhh.uth.views.gestióndeempleados.EmployeeViewModel;

public class EmployeeInteractorImpl implements EmployeeInteractor {

	private RRHHRepositoryImpl modelo;
	private EmployeeViewModel vista;
	
	public EmployeeInteractorImpl(EmployeeViewModel vista) {
		super();
		this.modelo = RRHHRepositoryImpl.getInstance("https://apex.oracle.com/", 800000L);
		this.vista = vista;
	}

	@Override
	public void consultarEmpleados() {
		try {
			ResponseEmployee respuesta = this.modelo.getEmployees();
			this.vista.refrescarGridEmpleados(respuesta.getItems());
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void crearNuevoEmpleado(Empleado nuevo) {
		try {
			boolean respuesta = this.modelo.createEmployee(nuevo);
			this.vista.mostrarMensajeCreacion(respuesta);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void actualizarEmpleado(Empleado actualizar) {
		try {
			boolean respuesta = this.modelo.updateEmployee(actualizar);
			this.vista.mostrarMensajeActualizacion(respuesta);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void consultarPuestos() {
		try {
			ResponsePuesto respuesta = this.modelo.getPuestos();
			this.vista.refrescarComboPuestos(respuesta.getItems());
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void eliminarEmpleado(String identidad) {
		try {
			boolean respuesta = this.modelo.deleteEmployee(identidad);
			this.vista.mostrarMensajeEliminacion(respuesta);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
