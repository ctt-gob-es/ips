package es.map.ipsg.form;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import es.map.ips.common.spring.SpringErrors;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringMapping;
import es.map.ips.common.spring.SpringMessage;
import es.map.ips.model.CuerpoEscala;
import es.map.ips.model.Tarifa;
import es.map.ipsg.util.Validacion;


/**
 * El Class GrupoForm.
 */
public class GrupoForm extends SpringForm {
	
	/** el id. */
	private Integer id;
	
	/** el codigo. */
	private String codigo;
	
	/** el descripcion. */
	private String descripcion;
	
	/** el estado. */
	private Character estado;
	
	/** el siglas. */
	private String siglas;
	
	/** el tarifas. */
	private Set<Tarifa> tarifas = new HashSet<Tarifa>(0);
	
	/** el cuerpo escalas. */
	private Set<CuerpoEscala> cuerpoEscalas = new HashSet<CuerpoEscala>(0);
	
	/** el visibilidad. */
	private Boolean visibilidad;
	
	/** el campo. */
	private String campo;
	
	/** el direccion. */
	private String direccion;
	
	/** el pagina actual. */
	private String paginaActual;
	
	/** el paginas totales. */
	private String paginasTotales;
	
	/** el submit. */
	private String submit;
	
	/** el num registro. */
	private String numRegistro;
	
	/** el mensaje. */
	private String mensaje;
	
	/** el cambios. */
	private String cambios;
	
	/** el accion. */
	private String accion;
	
	/** el numero pagina ir. */
	private Integer numeroPaginaIr;
	
	/** el pulsa ir. */
	private boolean pulsaIr;
	
	/** La constante STRING_ERRORCODIGO. */
	private static final String STRING_ERRORCODIGO = "errorCodigo";
	
	/**
	 * Obtiene el id.
	 *
	 * @return el id
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * Establece el id.
	 *
	 * @param id el nuevo id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * Obtiene el codigo.
	 *
	 * @return el codigo
	 */
	public String getCodigo() {
		return codigo;
	}
	
	/**
	 * Establece el codigo.
	 *
	 * @param codigo el nuevo codigo
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	/**
	 * Obtiene el descripcion.
	 *
	 * @return el descripcion
	 */
	public String getDescripcion() {
		return (descripcion!=null ? descripcion.trim() : null);
	}
	
	/**
	 * Establece el descripcion.
	 *
	 * @param descripcion el nuevo descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	/**
	 * Obtiene el estado.
	 *
	 * @return el estado
	 */
	public Character getEstado() {
		return estado;
	}
	
	/**
	 * Establece el estado.
	 *
	 * @param estado el nuevo estado
	 */
	public void setEstado(Character estado) {
		this.estado = estado;
	}
	
	/**
	 * Obtiene el tarifas.
	 *
	 * @return el tarifas
	 */
	public Set<Tarifa> getTarifas() {
		return tarifas;
	}
	
	/**
	 * Establece el tarifas.
	 *
	 * @param tarifas el nuevo tarifas
	 */
	public void setTarifas(Set<Tarifa> tarifas) {
		this.tarifas = tarifas;
	}
	
	/**
	 * Obtiene el cuerpo escalas.
	 *
	 * @return el cuerpo escalas
	 */
	public Set<CuerpoEscala> getCuerpoEscalas() {
		return cuerpoEscalas;
	}
	
	/**
	 * Establece el cuerpo escalas.
	 *
	 * @param cuerpoEscalas el nuevo cuerpo escalas
	 */
	public void setCuerpoEscalas(Set<CuerpoEscala> cuerpoEscalas) {
		this.cuerpoEscalas = cuerpoEscalas;
	}
	
	/**
	 * Obtiene el campo.
	 *
	 * @return el campo
	 */
	public String getCampo() {
		return campo;
	}
	
	/**
	 * Establece el campo.
	 *
	 * @param campo el nuevo campo
	 */
	public void setCampo(String campo) {
		this.campo = campo;
	}
	
	/**
	 * Obtiene el direccion.
	 *
	 * @return el direccion
	 */
	public String getDireccion() {
		return direccion;
	}
	
	/**
	 * Establece el direccion.
	 *
	 * @param direccion el nuevo direccion
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	/**
	 * Obtiene el pagina actual.
	 *
	 * @return el pagina actual
	 */
	public String getPaginaActual() {
		return paginaActual;
	}
	
	/**
	 * Establece el pagina actual.
	 *
	 * @param paginaActual el nuevo pagina actual
	 */
	public void setPaginaActual(String paginaActual) {
		this.paginaActual = paginaActual;
	}
	
	/**
	 * Obtiene el paginas totales.
	 *
	 * @return el paginas totales
	 */
	public String getPaginasTotales() {
		return paginasTotales;
	}
	
	/**
	 * Establece el paginas totales.
	 *
	 * @param paginasTotales el nuevo paginas totales
	 */
	public void setPaginasTotales(String paginasTotales) {
		this.paginasTotales = paginasTotales;
	}
	
	/**
	 * Obtiene el submit.
	 *
	 * @return el submit
	 */
	public String getSubmit() {
		return submit;
	}
	
	/**
	 * Establece el submit.
	 *
	 * @param submit el nuevo submit
	 */
	public void setSubmit(String submit) {
		this.submit = submit;
	}
	
	/**
	 * Obtiene el num registro.
	 *
	 * @return el num registro
	 */
	public String getNumRegistro() {
		return numRegistro;
	}
	
	/**
	 * Establece el num registro.
	 *
	 * @param numRegistro el nuevo num registro
	 */
	public void setNumRegistro(String numRegistro) {
		this.numRegistro = numRegistro;
	}
	
	/**
	 * Obtiene el mensaje.
	 *
	 * @return el mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}
	
	/**
	 * Establece el mensaje.
	 *
	 * @param mensaje el nuevo mensaje
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	

	/**
	 * Obtiene el cambios.
	 *
	 * @return el cambios
	 */
	public String getCambios() {
		return cambios;
	}
	
	/**
	 * Establece el cambios.
	 *
	 * @param cambios el nuevo cambios
	 */
	public void setCambios(String cambios) {
		this.cambios = cambios;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.SpringForm#validate(es.map.ips.common.spring.SpringMapping, javax.servlet.http.HttpServletRequest)
	 */
	public SpringErrors validate(SpringMapping mapping,	HttpServletRequest request) {
		SpringErrors SpringErrors = new SpringErrors();
		Validacion validacion;
		
		if(descripcion != null && !"".equals(descripcion)){
			descripcion = descripcion.trim();
		}
		if(codigo != null && !"".equals(codigo)){
			codigo = codigo.trim();
		}
		if(siglas != null && !"".equals(siglas)){
			siglas = siglas.trim();
		}		
		
		if("Modificar".equalsIgnoreCase(submit) || "Guardar".equals(submit)){	
			validateAux(request,SpringErrors);
			
		}
		return SpringErrors;
	}
	
	/**
	 * Validate aux.
	 *
	 * @param request el request
	 * @param SpringErrors el spring errors
	 */
	public void validateAux(HttpServletRequest request,SpringErrors SpringErrors) {
		if(descripcion == null || descripcion.equalsIgnoreCase("")){
			request.setAttribute("errorDescripcion", "errorDescripcion");
			SpringErrors.add("dsErrorDescripcion", new SpringMessage("field.ministerio.errorDescripcion"));
		}
		if(codigo == null || codigo.equalsIgnoreCase("")){
			request.setAttribute(STRING_ERRORCODIGO, STRING_ERRORCODIGO);
			SpringErrors.add("dsErrorCodigo", new SpringMessage("field.ministerio.errorCodigo"));
		} else if (codigo.length() > 10) {
			request.setAttribute(STRING_ERRORCODIGO, STRING_ERRORCODIGO);
			SpringErrors.add("dsErrorCodigo", new SpringMessage("field.grupo.errorLongitudCodigo"));
		}
		if(siglas == null || siglas.equalsIgnoreCase("")){
			request.setAttribute("errorSiglas", "errorSiglas");
			SpringErrors.add("dsErrorSiglas", new SpringMessage("field.ministerio.errorSiglas"));
		}			
	}
	
	/**
	 * Establece el siglas.
	 *
	 * @param siglas el nuevo siglas
	 */
	public void setSiglas(String siglas) {
		this.siglas = siglas;
	}
	
	/**
	 * Obtiene el siglas.
	 *
	 * @return el siglas
	 */
	public String getSiglas() {
		return siglas;
	}
	
	/**
	 * Obtiene el visibilidad.
	 *
	 * @return el visibilidad
	 */
	public Boolean getVisibilidad() {
		return visibilidad;
	}
	
	/**
	 * Establece el visibilidad.
	 *
	 * @param visibilidad el nuevo visibilidad
	 */
	public void setVisibilidad(Boolean visibilidad) {
		this.visibilidad = visibilidad;
	}
	
	/**
	 * Obtiene el numero pagina ir.
	 *
	 * @return el numero pagina ir
	 */
	public Integer getNumeroPaginaIr() {
		return numeroPaginaIr;
	}
	
	/**
	 * Establece el numero pagina ir.
	 *
	 * @param numeroPaginaIr el nuevo numero pagina ir
	 */
	public void setNumeroPaginaIr(Integer numeroPaginaIr) {
		this.numeroPaginaIr = numeroPaginaIr;
	}
	
	/**
	 * Comprueba si es pulsa ir.
	 *
	 * @return verdadero, si es pulsa ir
	 */
	public boolean isPulsaIr() {
		return pulsaIr;
	}
	
	/**
	 * Establece el pulsa ir.
	 *
	 * @param pulsaIr el nuevo pulsa ir
	 */
	public void setPulsaIr(boolean pulsaIr) {
		this.pulsaIr = pulsaIr;
	}
	
	/**
	 * Obtiene el accion.
	 *
	 * @return el accion
	 */
	public String getAccion() {
		return accion;
	}
	
	/**
	 * Establece el accion.
	 *
	 * @param accion el nuevo accion
	 */
	public void setAccion(String accion) {
		this.accion = accion;
	}

}
