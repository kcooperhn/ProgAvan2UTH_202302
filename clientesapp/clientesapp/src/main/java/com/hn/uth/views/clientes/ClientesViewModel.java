package com.hn.uth.views.clientes;

import java.util.List;

import com.hn.uth.data.entity.Cliente;

public interface ClientesViewModel {

	void refrescarGridClientes(List<Cliente> clientes);
	void mostrarMensajeError(String mensaje);
}
