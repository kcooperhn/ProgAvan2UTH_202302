package com.rrhh.uth.views.gestióndeempleados;

import java.util.List;

import com.rrhh.uth.data.entity.Empleado;
import com.rrhh.uth.data.entity.Puesto;

public interface EmployeeViewModel {
	void refrescarGridEmpleados(List<Empleado> empleados);
	void mostrarMensajeCreacion(boolean exito);
	void mostrarMensajeActualizacion(boolean exito);
	void mostrarMensajeEliminacion(boolean exito);
	
	void refrescarComboPuestos(List<Puesto> puestos);
}
