package com.hn.uth.controller;

import com.hn.uth.data.entity.ResponseClientes;
import com.hn.uth.data.service.DatabaseRepositoryImpl;
import com.hn.uth.views.clientes.ClientesViewModel;

public class ClientesInteractorImpl implements ClientesInteractor {

	private DatabaseRepositoryImpl modelo;
	private ClientesViewModel vista;
	private String username;
	private String password;
	
	public ClientesInteractorImpl(ClientesViewModel vista) {
		super();
		//NO ES UNA BUENA PRACTICA, SOLO PARA FINES DEMOSTRATIVOS
		username = "clientesapp";
		password = "clientes123";
		this.vista = vista;
		this.modelo = DatabaseRepositoryImpl.getInstance("https://apex.oracle.com", 60000L, username, password);
	}

	

	@Override
	public void consultarClientes() {
		try {
			ResponseClientes respuesta = this.modelo.consultarClientes();
			if(respuesta != null && respuesta.getItems() != null) {
				this.vista.refrescarGridClientes(respuesta.getItems());
			}else {
				this.vista.mostrarMensajeError("Error al consultar los clientes");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
