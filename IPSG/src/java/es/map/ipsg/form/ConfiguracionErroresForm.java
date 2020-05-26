package es.map.ipsg.form;

import javax.servlet.http.HttpServletRequest;

import es.map.ips.common.spring.SpringErrors;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringMapping;
import es.map.ips.common.spring.SpringMessage;

public class ConfiguracionErroresForm extends SpringForm {
	
	private String id;
	private String descripcion;
	private Boolean visibilidad;
	
	private String submit;	
	private String accion;
	private String cambios;
	private String paginaActual;	
	private String paginasTotales;
	private String campo;	
	private String direccion;
	private String numRegistro;
	private boolean eliminar;	
	private boolean modificar;	
	private boolean actualizacion;	
	
	private Integer numeroPaginaIr;
	private boolean pulsaIr;
	
	public String getCambios() {
		return cambios;
	}
	public void setCambios(String cambios) {
		this.cambios = cambios;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDescripcion() {
		return (descripcion!=null ? descripcion.trim() : null);
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String getAccion() {
		return accion;
	}
	public void setAccion(String accion) {
		this.accion = accion;
	}

	public void setVisibilidad(Boolean visibilidad) {
		this.visibilidad = visibilidad;		
	}
	public Boolean getVisibilidad() {
		return visibilidad;
	}

	public void setSubmit(String submit) {
		this.submit = submit;
	}
	
	public String getSubmit() {
		return submit;
	}
	
	public void setPaginaActual(String paginaActual) {
		this.paginaActual = paginaActual;
	}
	
	public String getPaginaActual() {
		return paginaActual;
	}
	
	public void setCampo(String campo) {
		this.campo = campo;
	}
	
	public String getCampo() {
		return campo;
	}
	
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public String getDireccion() {
		return direccion;
	}
	
	public void setNumRegistro(String numRegistro) {
		this.numRegistro = numRegistro;
	}
	
	public String getNumRegistro() {
		return numRegistro;
	}
	
	public void setEliminar(boolean eliminar) {
		this.eliminar = eliminar;
	}
	
	public boolean isEliminar() {
		return eliminar;
	}
	
	public void setModificar(boolean modificar) {
		this.modificar = modificar;
	}
	public boolean isModificar() {
		return modificar;
	}
	
	public void setActualizacion(boolean actualizacion) {
		this.actualizacion = actualizacion;
	}
	
	public boolean isActualizacion() {
		return actualizacion;
	}
	
	public void setPaginasTotales(String paginasTotales) {
		this.paginasTotales = paginasTotales;
	}
	
	public String getPaginasTotales() {
		return paginasTotales;
	}
		
	public SpringErrors validate(SpringMapping mapping,	HttpServletRequest request) {
		SpringErrors SpringErrors = new SpringErrors();
		
		if("GUARDAR".equalsIgnoreCase(accion) || "MODIFICAR".equalsIgnoreCase(accion)){		
			
			if(descripcion == null || descripcion.equalsIgnoreCase("")){				
				request.setAttribute("errorDescripcion", "errorDescripcion");
				SpringErrors.add("dsErrorDescripcion", new SpringMessage("field.configuracionErrores.errores.descripcionRequerido"));
			}
			
		}
		
		return SpringErrors;
	}
	public Integer getNumeroPaginaIr() {
		return numeroPaginaIr;
	}
	public void setNumeroPaginaIr(Integer numeroPaginaIr) {
		this.numeroPaginaIr = numeroPaginaIr;
	}
	public boolean isPulsaIr() {
		return pulsaIr;
	}
	public void setPulsaIr(boolean pulsaIr) {
		this.pulsaIr = pulsaIr;
	}

}
