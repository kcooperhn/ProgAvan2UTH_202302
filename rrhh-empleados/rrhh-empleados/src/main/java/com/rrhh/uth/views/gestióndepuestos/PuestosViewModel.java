package com.rrhh.uth.views.gestióndepuestos;

import java.util.List;

import com.rrhh.uth.data.entity.Puesto;

public interface PuestosViewModel {
	void refrescarGridPuestos(List<Puesto> puestos);
	void mostrarMensajeCreacion(boolean exito);
	void mostrarMensajeActualizacion(boolean exito);
	void mostrarMensajeEliminacion(boolean exito);
	
}
