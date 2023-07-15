package com.rrhh.uth.data.controller;

import com.rrhh.uth.data.entity.Empleado;

public interface EmployeeInteractor {
	void consultarEmpleados();
	void crearNuevoEmpleado(Empleado nuevo);
	void actualizarEmpleado(Empleado actualizar);
	void eliminarEmpleado(String identidad);
	
	void consultarPuestos();
}
