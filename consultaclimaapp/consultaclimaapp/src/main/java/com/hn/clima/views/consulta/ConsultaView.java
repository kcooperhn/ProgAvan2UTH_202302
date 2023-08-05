package com.hn.clima.views.consulta;

import com.hn.clima.data.controller.WeatherInteractor;
import com.hn.clima.data.controller.WeatherInteractorImpl;
import com.hn.clima.data.entity.ClimaData;
import com.hn.clima.data.entity.ClimaDataReport;
import com.hn.clima.data.service.ReportGenerator;
import com.hn.clima.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.grid.contextmenu.GridContextMenu;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.theme.lumo.LumoUtility;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@PageTitle("Consulta")
@Route(value = "consulta", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
@Uses(Icon.class)
public class ConsultaView extends Div implements WeatherViewModel {

    private final TextField lat = new TextField("Latitud");
    private final TextField lon = new TextField("Longitud");
    private Grid<ClimaData> grid;
    private List<ClimaData> datos;
    private RadioButtonGroup<String> radioGroup;
    private WeatherInteractor controlador;

    public ConsultaView() {
        setSizeFull();
        addClassNames("consulta-view");
        this.controlador = new WeatherInteractorImpl(this);
        this.datos = new ArrayList<>();
        VerticalLayout layout = new VerticalLayout(createMobileFilters(), createFilters(), createGrid());
        layout.setSizeFull();
        layout.setPadding(false);
        layout.setSpacing(false);
        add(layout);
    }

    private HorizontalLayout createMobileFilters() {
        // Mobile version
        HorizontalLayout mobileFilters = new HorizontalLayout();
        mobileFilters.setWidthFull();
        mobileFilters.addClassNames(LumoUtility.Padding.MEDIUM, LumoUtility.BoxSizing.BORDER,
                LumoUtility.AlignItems.CENTER);
        mobileFilters.addClassName("mobile-filters");

        Icon mobileIcon = new Icon("lumo", "plus");
        Span filtersHeading = new Span("Filters");
        mobileFilters.add(mobileIcon, filtersHeading);
        mobileFilters.setFlexGrow(1, filtersHeading);
        mobileFilters.addClickListener(e -> {
        });
        
        return mobileFilters;
    }
    
    private VerticalLayout createFilters() {
        setWidthFull();
        addClassName("filter-layout");
        addClassNames(LumoUtility.Padding.Horizontal.LARGE, LumoUtility.Padding.Vertical.LARGE,
                LumoUtility.BoxSizing.BORDER);
        lat.setPlaceholder("Latitud de la posición GPS");
        lon.setPlaceholder("Longitud de la posición GPS");
        lat.setWidthFull();
        lon.setWidthFull();

        // Action buttons
        Button resetBtn = new Button("Resetear");
        resetBtn.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        resetBtn.addClickListener(e -> {
            lat.clear();
            lon.clear();
            grid.setItems(new ArrayList<>());
            this.datos.clear();
        });
        Button searchBtn = new Button("Consultar");
        searchBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        searchBtn.addClickListener(e -> {
        	String latitud = this.lat.getValue();
        	String longitud = this.lon.getValue();
        	
        	if(validData(latitud, longitud)) {
        		latitud = latitud.trim();
        		longitud = longitud.trim();
        		this.controlador.consultarClima(latitud, longitud);
        		lat.clear();
        		lon.clear();
        	}else {
        		Dialog dialog = new Dialog();

        		dialog.setHeaderTitle("Consulta Invalida");

        		VerticalLayout dialogLayout = createDialogLayout();
        		dialog.add(dialogLayout);

        		Button cancelButton = new Button("Cerrar");
        		cancelButton.addClickListener(ev -> dialog.close());
        		dialog.getFooter().add(cancelButton);

        		add(dialog);
        		dialog.open();
        	}
        	
        });
        
        radioGroup = new RadioButtonGroup<>();
        radioGroup.setLabel("Autoriza");
        radioGroup.setItems("Gerente", "SubGerente", "Jefe");
        radioGroup.setValue("Gerente");

        Div actions = new Div(resetBtn, searchBtn);
        actions.addClassName(LumoUtility.Gap.SMALL);
        actions.addClassName("actions");

        VerticalLayout layout = new VerticalLayout();
        HorizontalLayout l2 = new HorizontalLayout(lat, lon);
        l2.setWidthFull();
        layout.add(l2,actions, radioGroup);
        layout.setPadding(false);
        layout.setSpacing(false);
        return layout;
    }
    
    private boolean validData(String latitud, String longitud) {
		boolean invalid = (latitud == null || "".equals(latitud)) 
				|| (longitud == null || "".equals(longitud));
		return !invalid;
	}

	private static VerticalLayout createDialogLayout() {
        
        Span denied = new Span("Latitud / longitud invalida");
        denied.getElement().getThemeList().add("badge error");

        VerticalLayout dialogLayout = new VerticalLayout(denied);
        dialogLayout.setPadding(false);
        dialogLayout.setSpacing(false);
        dialogLayout.getStyle().set("width", "18rem").set("max-width", "100%");

        return dialogLayout;
    }
    
    private Component createGrid() {
        grid = new Grid<>(ClimaData.class, false);
        grid.addColumn("fecha").setAutoWidth(true);
        grid.addColumn("lugar").setAutoWidth(true);
        grid.addColumn("pais").setAutoWidth(true);
        grid.addColumn("descripcion").setAutoWidth(true);
        grid.addColumn("temperatura").setAutoWidth(true).setTextAlign(ColumnTextAlign.CENTER);
        grid.addColumn("humedad").setAutoWidth(true).setTextAlign(ColumnTextAlign.CENTER);
        grid.addColumn("sensaciontermica").setAutoWidth(true).setHeader("Sensación Térmica").setTextAlign(ColumnTextAlign.CENTER);

        GridContextMenu<ClimaData> menu = grid.addContextMenu();
        menu.addItem("Generar Reporte", event -> {
        	if(this.datos.isEmpty()) {
        		Notification.show("No hay datos para generar el reporte");
        	}else {
        		Notification.show("Generando reporte PDF...");
            	generarReporteClima();
        	}
        });
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
        grid.addClassNames(LumoUtility.Border.TOP, LumoUtility.BorderColor.CONTRAST_10);

        return grid;
    }

    private void generarReporteClima() {
    	ReportGenerator generador = new ReportGenerator();
    	Map<String, Object> parametros = new HashMap<>();
    	parametros.put("NOMBRE_LOGO", "ClimaApp");
    	parametros.put("LOGO_DIR", "weather.png");
    	
    	String autorizacion = radioGroup.getValue();
    	if("gerente".equalsIgnoreCase(autorizacion)) {
    		parametros.put("FIRMA_DIR", "firma_1.png");
    	}else if("subgerente".equalsIgnoreCase(autorizacion)) {
    		parametros.put("FIRMA_DIR", "firma_2.png");
    	}else {
    		parametros.put("FIRMA_DIR", "firma_3.png");
    	}
    	
    	
		ClimaDataReport datasource = new ClimaDataReport();
		datasource.setData(datos);
		boolean generado = generador.generarReportePDF("reporte_clima_actual", parametros, datasource);
		if(generado) {
			String ubicacion = generador.getUbicacion();
			Anchor url = new Anchor(ubicacion, "Abrir reporte PDF");
			url.setTarget("_blank");
			
			Notification notificacion = new Notification(url);
			notificacion.setDuration(20000);
			notificacion.open();
		}else {
			Notification notificacion = new Notification("Ocurrió un problema al generar el reporte");
			notificacion.addThemeVariants(NotificationVariant.LUMO_ERROR);
			notificacion.setDuration(10000);
			notificacion.open();
		}
	}

	private void refreshGrid() {
        grid.getDataProvider().refreshAll();
    }

	@Override
	public void refrescarGridClima(ClimaData data) {
		datos.add(data);
		Collection<ClimaData> items = datos;
		grid.setItems(items);
	}

}
