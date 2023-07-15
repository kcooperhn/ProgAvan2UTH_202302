package com.rrhh.uth.views.gestióndepuestos;

import com.rrhh.uth.data.controller.PuestosInteractor;
import com.rrhh.uth.data.controller.PuestosInteractorImpl;
import com.rrhh.uth.data.entity.Departamento;
import com.rrhh.uth.data.entity.Puesto;
import com.rrhh.uth.views.MainLayout;
import com.rrhh.uth.views.gestióndeempleados.GestióndeEmpleadosView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.grid.contextmenu.GridContextMenu;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.springframework.orm.ObjectOptimisticLockingFailureException;

@PageTitle("Gestión de Puestos")
@Route(value = "gestion-puestos/:puestoID?/:action?(edit)", layout = MainLayout.class)
public class GestióndePuestosView extends Div implements BeforeEnterObserver, PuestosViewModel {

    private final String PUESTO_ID = "puestoID";
    private final String PUESTO_EDIT_ROUTE_TEMPLATE = "gestion-puestos/%s/edit";

    private final Grid<Puesto> grid = new Grid<>(Puesto.class, false);

    private TextField nombre;
    private ComboBox<Departamento> departamento;

    private final Button cancel = new Button("Cancelar");
    private final Button save = new Button("Guardar");

    private Puesto puesto;
    private List<Puesto> puestos;
    private List<Departamento> listado;
    private PuestosInteractor controlador;

    public GestióndePuestosView() {
        addClassNames("gestiónde-puestos-view");
        this.puestos = new ArrayList<>();
        this.controlador = new PuestosInteractorImpl(this);

        // Create UI
        SplitLayout splitLayout = new SplitLayout();

        createGridLayout(splitLayout);
        createEditorLayout(splitLayout);

        add(splitLayout);

        // Configure Grid
        grid.addColumn("nombre").setAutoWidth(true);
        grid.addColumn("departamento").setAutoWidth(true);
        /*grid.setItems(query -> puestoService.list(
                PageRequest.of(query.getPage(), query.getPageSize(), VaadinSpringDataHelpers.toSpringDataSort(query)))
                .stream());*/
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
        
        GridContextMenu<Puesto> menu = grid.addContextMenu();
        menu.addItem("Eliminar", event -> {
        	ConfirmDialog dialog = new ConfirmDialog();
        	dialog.setHeader("Confirmar Eliminación de "+event.getItem().get().getNombre());
        	dialog.setText(
        	        "¿Confirmas que deseas eliminar el puesto seleccionado?");

        	dialog.setCancelable(true);

        	dialog.setConfirmText("Eliminar");
        	dialog.setConfirmButtonTheme("error primary");
        	dialog.addConfirmListener(event2 -> {
        		this.controlador.eliminarPuesto(event.getItem().get().getIdpuesto());
        	});
        	dialog.open();
        });

        // when a row is selected or deselected, populate form
        grid.asSingleSelect().addValueChangeListener(event -> {
            if (event.getValue() != null) {
                UI.getCurrent().navigate(String.format(PUESTO_EDIT_ROUTE_TEMPLATE, event.getValue().getIdpuesto()));
            } else {
                clearForm();
                UI.getCurrent().navigate(GestióndePuestosView.class);
            }
        });
        
        this.controlador.consultarPuestos();

        cancel.addClickListener(e -> {
            clearForm();
            refreshGrid();
        });

        save.addClickListener(e -> {
            try {
            	if (this.puesto == null) {
                    //ESTOY CREANDO UNO NUEVO
                	this.puesto = new Puesto();
                	this.puesto.setNombre(this.nombre.getValue());
                	this.puesto.setDepartamento(this.departamento.getValue().getNombre());
                	this.controlador.crearNuevoPuesto(puesto);
                }else {
                	//ESTOY EDITANDO UNO EXISTENTE
                	this.puesto.setNombre(this.nombre.getValue());
                	this.puesto.setDepartamento(this.departamento.getValue().getNombre());
                	this.controlador.actualizarPuesto(puesto);
                }
                clearForm();
                refreshGrid();
                UI.getCurrent().navigate(GestióndePuestosView.class);
            } catch (ObjectOptimisticLockingFailureException exception) {
                Notification n = Notification.show(
                        "Error updating the data. Somebody else has updated the record while you were making changes.");
                n.setPosition(Position.MIDDLE);
                n.addThemeVariants(NotificationVariant.LUMO_ERROR);
            }
        });
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        Optional<Long> puestoId = event.getRouteParameters().get(PUESTO_ID).map(Long::parseLong);
        boolean encontrado = false;
        if (puestoId.isPresent()) {
        	for(Puesto e: this.puestos) {
            	if(e.getIdpuesto() == puestoId.get()) {
            		populateForm(e);
            		encontrado = true;
            		break;
            	}
            }
            if (!encontrado) {
            	Notification.show(String.format("El puesto con id = %s", puestoId.get()+" no fue encontrado"),
                        3000, Notification.Position.BOTTOM_START);
                // when a row is selected but the data is no longer available,
                // refresh grid
                refreshGrid();
                event.forwardTo(GestióndeEmpleadosView.class);
            }
        }
    }

    private void createEditorLayout(SplitLayout splitLayout) {
        Div editorLayoutDiv = new Div();
        editorLayoutDiv.setClassName("editor-layout");

        Div editorDiv = new Div();
        editorDiv.setClassName("editor");
        editorLayoutDiv.add(editorDiv);
        

        FormLayout formLayout = new FormLayout();
        nombre = new TextField("Nombre");
        departamento = new ComboBox<>("Departamento");
        departamento.setItems(generarDepartamentosPrueba());
        departamento.setItemLabelGenerator(Departamento::getNombre);
        
        formLayout.add(nombre, departamento);

        editorDiv.add(formLayout);
        createButtonLayout(editorLayoutDiv);

        splitLayout.addToSecondary(editorLayoutDiv);
    }

   

	private Collection<Departamento> generarDepartamentosPrueba() {
		listado = new ArrayList<>();
		Departamento contabilidad = new Departamento();
		contabilidad.setId(1L);
		contabilidad.setNombre("Contabilidad");
		contabilidad.setUbicacion("Oficina 5C");
		
		Departamento sistemas = new Departamento();
		sistemas.setId(2L);
		sistemas.setNombre("Sistemas");
		sistemas.setUbicacion("Oficina 1b");
		
		Departamento gerencia = new Departamento();
		gerencia.setId(3L);
		gerencia.setNombre("Gerencia General");
		gerencia.setUbicacion("Oficina 1a");
		
		Departamento recursushumanos = new Departamento();
		recursushumanos.setId(4L);
		recursushumanos.setNombre("Recursos Humanos");
		recursushumanos.setUbicacion("oficina 2c");
		
		Departamento mantenimiento = new Departamento();
		mantenimiento.setId(5L);
		mantenimiento.setNombre("Mantenimiento");
		mantenimiento.setUbicacion("oficina 1d");
		
		Departamento it = new Departamento();
		it.setId(6L);
		it.setNombre("IT");
		it.setUbicacion("oficina 3a");
		
		listado.add(contabilidad);
		listado.add(sistemas);
		listado.add(gerencia);
		listado.add(recursushumanos);
		listado.add(mantenimiento);
		listado.add(it);
		
		return listado;
	}

	private void createButtonLayout(Div editorLayoutDiv) {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.setClassName("button-layout");
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonLayout.add(save, cancel);
        editorLayoutDiv.add(buttonLayout);
    }

    private void createGridLayout(SplitLayout splitLayout) {
        Div wrapper = new Div();
        wrapper.setClassName("grid-wrapper");
        splitLayout.addToPrimary(wrapper);
        wrapper.add(grid);
    }

    private void refreshGrid() {
        grid.select(null);
        grid.getDataProvider().refreshAll();
    }

    private void clearForm() {
        populateForm(null);
    }

    private void populateForm(Puesto value) {
        this.puesto = value;
    	if(value == null) {
    		this.nombre.setValue("");
    		this.departamento.setValue(null);
    	}else {
    		this.nombre.setValue(value.getNombre());    		
    		this.departamento.setValue(buscarDepartamentoSeleccionado(value.getDepartamento()));
    	}
    }

	private Departamento buscarDepartamentoSeleccionado(String departamento) {
		Departamento seleccionado = new Departamento();
		for (Departamento p : listado) {
			if(p.getNombre().equalsIgnoreCase(departamento)) {
				seleccionado = p;
				break;
			}
		}
		return seleccionado;
	}

	@Override
	public void refrescarGridPuestos(List<Puesto> puestos) {
		Collection<Puesto> items = puestos;
		grid.setItems(items);
		this.puestos = puestos;
	}

	@Override
	public void mostrarMensajeCreacion(boolean exito) {
		String mensajeMostrar = "Puesto creado exitosamente!";
		if(!exito) {
			mensajeMostrar = "Puesto no pudo ser creado :(";
		}
		Notification.show(mensajeMostrar);
		this.controlador.consultarPuestos();
	}

	@Override
	public void mostrarMensajeActualizacion(boolean exito) {
		String mensajeMostrar = "Puesto actualizado exitosamente!";
		if(!exito) {
			mensajeMostrar = "Puesto no pudo ser actualizado :(";
		}
		Notification.show(mensajeMostrar);
	}

	@Override
	public void mostrarMensajeEliminacion(boolean exito) {
		String mensajeMostrar = "Puesto eliminado exitosamente!";
		if(!exito) {
			mensajeMostrar = "Puesto no pudo ser eliminado :(";
		}
		Notification.show(mensajeMostrar);
		this.controlador.consultarPuestos();
		
	}
}
