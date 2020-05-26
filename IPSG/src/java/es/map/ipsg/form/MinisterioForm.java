package es.map.ipsg.form;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import es.map.ips.common.spring.SpringErrors;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringMapping;
import es.map.ips.common.spring.SpringMessage;
import es.map.ips.model.CentroGestor;
import es.map.ipsg.util.Validacion;

/**
 * El Class MinisterioForm.
 */
public class MinisterioForm extends SpringForm {
	
	/** el id. */
	private Integer id;
	
	/** el siglas. */
	private String siglas;
	
	/** el descripcion. */
	private String descripcion;
	
	/** el codigo. */
	private String codigo;
	
	/** el url. */
	private String url;
	
	/** el estado. */
	private Character estado;
	
	/** el centro gestors. */
	private Set<CentroGestor> centroGestors = new HashSet<CentroGestor>(0);
	
	/** el id ministerio previo. */
	private Integer idMinisterioPrevio;
	
	/** el fecha sustitucion. */
	private String fechaSustitucion;
	
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
	
	/** el visibilidad. */
	private Boolean visibilidad;
	
	/** el numero pagina ir. */
	private Integer numeroPaginaIr;
	
	/** el pulsa ir. */
	private boolean pulsaIr;
	
	/** La constante STRING_ERRORCODIGO. */
	private static final String STRING_ERRORCODIGO = "errorCodigo";
	
	/** La constante STRING_ERRORMINISTERIOSUST. */
	private static final String STRING_ERRORMINISTERIOSUST = "errorMinisterioSust";
	 
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
	 * Obtiene el siglas.
	 *
	 * @return el siglas
	 */
	public String getSiglas() {
		return siglas;
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
	 * Obtiene el url.
	 *
	 * @return el url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Establece el url.
	 *
	 * @param url el nuevo url
	 */
	public void setUrl(String url) {
		this.url = url;
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
	 * Obtiene el centro gestors.
	 *
	 * @return el centro gestors
	 */
	public Set<CentroGestor> getCentroGestors() {
		return centroGestors;
	}

	/**
	 * Establece el centro gestors.
	 *
	 * @param centroGestors el nuevo centro gestors
	 */
	public void setCentroGestors(Set<CentroGestor> centroGestors) {
		this.centroGestors = centroGestors;
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
	public SpringErrors validate(SpringMapping mapping,
			HttpServletRequest request) {
		SpringErrors SpringErrors = new SpringErrors();
		Validacion validacion = new Validacion();
		if (siglas != null && !"".equals(siglas)) {
			siglas = siglas.trim();
		}
		if (descripcion != null && !"".equals(descripcion)) {
			descripcion = descripcion.trim();
		}
		if (codigo != null && !"".equals(codigo)) {
			codigo = codigo.trim();
		}
		if (url != null && !"".equals(url)) {
			url = url.trim();
		}
		
		validateAux(request,SpringErrors,validacion);
		
		validateAux2(request,SpringErrors);
		
		
		return SpringErrors;
	}

	/**
	 * Validate aux 2.
	 *
	 * @param request el request
	 * @param SpringErrors el spring errors
	 */
	private void validateAux2(HttpServletRequest request, SpringErrors SpringErrors) {
		
		if ("Modificar".equalsIgnoreCase(submit) || "Guardar".equals(submit)) {
			if (siglas == null || siglas.equalsIgnoreCase("")) {
				request.setAttribute("errorSiglas", "errorSiglas");
				SpringErrors.add("dsErrorSiglas", new SpringMessage(
						"field.ministerio.errorSiglas"));
			}
			if (descripcion == null || descripcion.equalsIgnoreCase("")) {
				request.setAttribute("errorDescripcion", "errorDescripcion");
				SpringErrors.add("dsErrorDescripcion", new SpringMessage(
						"field.ministerio.errorDescripcion"));
			}
			if (codigo == null || codigo.equalsIgnoreCase("")) {
				request.setAttribute(STRING_ERRORCODIGO, STRING_ERRORCODIGO);
				SpringErrors.add("dsErrorCodigo", new SpringMessage(
						"field.ministerio.errorCodigo"));
			} else if (codigo.length() > 5) {
				request.setAttribute(STRING_ERRORCODIGO, STRING_ERRORCODIGO);
				SpringErrors.add("dsErrorCodigo", new SpringMessage(
						"field.ministerio.errorLongitudCodigo"));
			}
			if (url == null || url.equalsIgnoreCase("")) {
				request.setAttribute("errorUrl", "errorUrl");
				SpringErrors.add("dsErrorUrl", new SpringMessage(
						"field.ministerio.errorUrl"));
			}
		}
	}
	
	/**
	 * Validate aux.
	 *
	 * @param request el request
	 * @param SpringErrors el spring errors
	 * @param validacion el validacion
	 */
	private void validateAux(HttpServletRequest request, SpringErrors SpringErrors, Validacion validacion) {
		
		if(fechaSustitucion != null && !"".equals(fechaSustitucion)){
			if(idMinisterioPrevio == null || idMinisterioPrevio == 0){
				request.setAttribute(STRING_ERRORMINISTERIOSUST, STRING_ERRORMINISTERIOSUST);
				SpringErrors.add("dsErrorMinisterioSust",new SpringMessage("field.ministerio.errorMinisterioSustitucion"));
			}else{
				if(!validacion.isFechaValida(fechaSustitucion)){
					request.setAttribute("errorFechaSust", "errorFechaSust");
					SpringErrors.add("dsErrorFechaSust",new SpringMessage("field.ministerio.errorFechaSustitucion"));
				}
			}
		}else{
			if(idMinisterioPrevio != null && idMinisterioPrevio != 0){
				request.setAttribute(STRING_ERRORMINISTERIOSUST, STRING_ERRORMINISTERIOSUST);
				SpringErrors.add("dsErrorMinisterioSust",new SpringMessage("field.ministerio.errorFechaSustitucion2"));
			}
		}
	}
	
	/**
	 * Obtiene el id ministerio previo.
	 *
	 * @return el id ministerio previo
	 */
	public Integer getIdMinisterioPrevio() {
		return idMinisterioPrevio;
	}

	/**
	 * Establece el id ministerio previo.
	 *
	 * @param idMinisterioPrevio el nuevo id ministerio previo
	 */
	public void setIdMinisterioPrevio(Integer idMinisterioPrevio) {
		this.idMinisterioPrevio = idMinisterioPrevio;
	}

	/**
	 * Obtiene el fecha sustitucion.
	 *
	 * @return el fecha sustitucion
	 */
	public String getFechaSustitucion() {
		return fechaSustitucion;
	}

	/**
	 * Establece el fecha sustitucion.
	 *
	 * @param fechaSustitucion el nuevo fecha sustitucion
	 */
	public void setFechaSustitucion(String fechaSustitucion) {
		this.fechaSustitucion = fechaSustitucion;
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

}
