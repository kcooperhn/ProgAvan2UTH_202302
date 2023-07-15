package com.rrhh.uth.data.controller;

import com.rrhh.uth.data.entity.Puesto;

public interface PuestosInteractor {
	void consultarPuestos();
	void crearNuevoPuesto(Puesto nuevo);
	void actualizarPuesto(Puesto actualizar);
	void eliminarPuesto(Integer id);

}
