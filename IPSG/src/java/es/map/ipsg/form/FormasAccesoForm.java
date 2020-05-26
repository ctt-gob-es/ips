package es.map.ipsg.form;

import javax.servlet.http.HttpServletRequest;

import es.map.ips.common.spring.SpringErrors;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringMapping;
import es.map.ips.common.spring.SpringMessage;
import es.map.ips.model.TipoAcceso;
import es.map.ipsg.util.Validacion;

/**
 * El Class FormasAccesoForm.
 */
public class FormasAccesoForm extends SpringForm {

	/** el id. */
	private String id;
	
	/** el des tipo acceso. */
	private String desTipoAcceso;
	
	/** el id tipo acceso. */
	private Integer idTipoAcceso;
	
	/** el tipo acceso. */
	private TipoAcceso tipoAcceso;
	
	/** el codigo. */
	private String codigo;
	
	/** el descripcion. */
	private String descripcion;		
	
	/** el accion. */
	private String accion;
	
	/** el estado. */
	private Character estado;
	
	/** el submit. */
	private String submit;
	
	/** el parametro. */
	private String parametro;
	
	/** el visibilidad. */
	private Boolean visibilidad;
	
	/** el pagina actual. */
	private String paginaActual;
	
	/** el paginas totales. */
	private String paginasTotales;
	
	/** el num registro. */
	private String numRegistro;
	
	/** el campo. */
	private String campo;
	
	/** el direccion. */
	private String direccion;
	
	/** el cambios. */
	private String cambios;
	
	/** el numero pagina ir. */
	private Integer numeroPaginaIr;
	
	/** el pulsa ir. */
	private boolean pulsaIr;
	
	/** La constante STRING_ERRORCODIGO. */
	private static final String STRING_ERRORCODIGO = "errorCodigo";
	
	/** La constante STRING_DSERRORCODIGO. */
	private static final String STRING_DSERRORCODIGO = "dsErrorCodigo";
	
	
	
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

	/**
	 * Obtiene el id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Establece el id.
	 *
	 * @param pid el nuevo id
	 */
	public void setId(String pid) {
		this.id = pid;
	}	

	/**
	 * Obtiene el codigo.
	 *
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * Establece el codigo.
	 *
	 * @param pcodigo el nuevo codigo
	 */
	public void setCodigo(String pcodigo) {
		this.codigo = pcodigo;
	}

	/**
	 * Obtiene el descripcion.
	 *
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return (descripcion!=null ? descripcion.trim() : null);
	}

	/**
	 * Establece el descripcion.
	 *
	 * @param pdescripcion el nuevo descripcion
	 */
	public void setDescripcion(String pdescripcion) {
		this.descripcion = pdescripcion;
	}

	/**
	 * Obtiene el accion.
	 *
	 * @return the accion
	 */
	public String getAccion() {
		return accion;
	}

	/**
	 * Establece el accion.
	 *
	 * @param accion the accion to set
	 */
	public void setAccion(String accion) {
		this.accion = accion;
	}

	/**
	 * Obtiene el parametro.
	 *
	 * @return the parametro
	 */
	public String getParametro() {
		return parametro;
	}

	/**
	 * Establece el parametro.
	 *
	 * @param parametro the parametro to set
	 */
	public void setParametro(String parametro) {
		this.parametro = parametro;
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
	 * Obtiene el des tipo acceso.
	 *
	 * @return the desTipoAcceso
	 */
	public String getDesTipoAcceso() {
		return desTipoAcceso;
	}

	/**
	 * Establece el des tipo acceso.
	 *
	 * @param desTipoAcceso the desTipoAcceso to set
	 */
	public void setDesTipoAcceso(String desTipoAcceso) {
		this.desTipoAcceso = desTipoAcceso;
	}

	/**
	 * Obtiene el id tipo acceso.
	 *
	 * @return the idTipoAcceso
	 */
	public Integer getIdTipoAcceso() {
		return idTipoAcceso;
	}

	/**
	 * Establece el id tipo acceso.
	 *
	 * @param idTipoAcceso the idTipoAcceso to set
	 */
	public void setIdTipoAcceso(Integer idTipoAcceso) {
		this.idTipoAcceso = idTipoAcceso;
	}

	/**
	 * Obtiene el tipo acceso.
	 *
	 * @return the tipoAcceso
	 */
	public TipoAcceso getTipoAcceso() {
		return tipoAcceso;
	}

	/**
	 * Establece el tipo acceso.
	 *
	 * @param tipoAcceso the tipoAcceso to set
	 */
	public void setTipoAcceso(TipoAcceso tipoAcceso) {
		this.tipoAcceso = tipoAcceso;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.SpringForm#validate(es.map.ips.common.spring.SpringMapping, javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public SpringErrors validate(SpringMapping mapping,	HttpServletRequest request) {
		SpringErrors SpringErrors = new SpringErrors();
		Validacion validacion;
		
		if("GUARDAR".equalsIgnoreCase(accion) || "MODIFICAR".equalsIgnoreCase(accion)){			
			if(descripcion == null || descripcion.equalsIgnoreCase("")){
				
				request.setAttribute("errorDescripcion", "errorDescripcion");
				SpringErrors.add("dsErrorDescripcion", new SpringMessage("field.formasAcceso.errores.descripcionRequerido"));
			}
			
			validateAux(request,SpringErrors);			

			if (idTipoAcceso == null || idTipoAcceso.intValue() == 0){
				request.setAttribute("errorIdTipoAcceso", "errorIdTipoAcceso");
				SpringErrors.add("dsErrorIdTipoAcceso", new SpringMessage("field.formasAcceso.errores.tipoAccesoRequerido"));
			}
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
		
		if(codigo== null || codigo.equalsIgnoreCase("")){
			
			request.setAttribute(STRING_ERRORCODIGO, STRING_ERRORCODIGO);
			SpringErrors.add(STRING_DSERRORCODIGO, new SpringMessage("field.formasAcceso.errores.codigoRequerido"));
		}else
		{				
			if (codigo.contains(" ")){
				request.setAttribute(STRING_ERRORCODIGO, STRING_ERRORCODIGO);
				SpringErrors.add(STRING_DSERRORCODIGO, new SpringMessage("field.formasAcceso.errores.codigoBlanc"));						
			}else{					
				if(codigo.length() > 5){
					request.setAttribute(STRING_ERRORCODIGO, STRING_ERRORCODIGO);
					SpringErrors.add(STRING_DSERRORCODIGO, new SpringMessage("field.formasAcceso.errores.codigoFormato"));
				}					
			}				
		}
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
