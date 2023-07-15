package com.rrhh.uth.data.controller;

import java.io.IOException;

import com.rrhh.uth.data.entity.Puesto;
import com.rrhh.uth.data.entity.ResponsePuesto;
import com.rrhh.uth.data.service.RRHHRepositoryImpl;
import com.rrhh.uth.views.gestióndepuestos.PuestosViewModel;

public class PuestosInteractorImpl implements PuestosInteractor {

	private RRHHRepositoryImpl modelo;
	private PuestosViewModel vista;
	
	public PuestosInteractorImpl(PuestosViewModel vista) {
		super();
		this.modelo = RRHHRepositoryImpl.getInstance("https://apex.oracle.com/", 800000L);
		this.vista = vista;
	}

	@Override
	public void consultarPuestos() {
		try {
			ResponsePuesto respuesta = this.modelo.getPuestos();
			this.vista.refrescarGridPuestos(respuesta.getItems());
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void crearNuevoPuesto(Puesto nuevo) {
		try {
			boolean respuesta = this.modelo.createPuesto(nuevo);
			this.vista.mostrarMensajeCreacion(respuesta);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actualizarPuesto(Puesto actualizar) {
		try {
			boolean respuesta = this.modelo.updatePuesto(actualizar);
			this.vista.mostrarMensajeActualizacion(respuesta);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void eliminarPuesto(Integer id) {
		try {
			boolean respuesta = this.modelo.deletePuesto(id);
			this.vista.mostrarMensajeEliminacion(respuesta);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
