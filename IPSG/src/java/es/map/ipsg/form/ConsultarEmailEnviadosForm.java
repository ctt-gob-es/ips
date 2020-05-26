package es.map.ipsg.form;

import es.map.ips.common.spring.SpringForm;

/**
 * El Class ConsultarEmailEnviadosForm.
 */
public class ConsultarEmailEnviadosForm extends SpringForm {
	
	/** el id solicitud. */
	private String idSolicitud;
	
	/** el id documentos. */
	private String[] idDocumentos;
	
	/** el entorno. */
	private String entorno;
	
	/** el solicitudes sel. */
	private String[] solicitudesSel;

	/** el accion. */
	private String accion;
	
	/** el pagina actual. */
	private String paginaActual;  //PAGINACIÓN
	
	/** el paginas totales. */
	private String paginasTotales;  //PAGINACIÓN
	
	/** el direccion. */
	private String direccion;
	
	/** el num registro. */
	private String numRegistro;  //PAGINACIÓN
	
	/** el submit. */
	private String submit;
	
	/** el campo. */
	private String campo;
	
	/** el cambios. */
	private String cambios; //PAGINACIÓN

	/**
	 * Obtiene el id solicitud.
	 *
	 * @return the idSolicitud
	 */
	public String getIdSolicitud() {
		return idSolicitud;
	}


	/**
	 * Establece el id solicitud.
	 *
	 * @param idSolicitud the idSolicitud to set
	 */
	public void setIdSolicitud(String idSolicitud) {
		this.idSolicitud = idSolicitud;
	}


	/**
	 * Obtiene el id documentos.
	 *
	 * @return the idDocumentos
	 */
	public String[] getIdDocumentos() {
		return idDocumentos;
	}


	/**
	 * Establece el id documentos.
	 *
	 * @param idDocumentos the idDocumentos to set
	 */
	public void setIdDocumentos(String[] idDocumentos) {
		this.idDocumentos = idDocumentos;
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
	 * Obtiene el entorno.
	 *
	 * @return the entorno
	 */
	public String getEntorno() {
		return entorno;
	}


	/**
	 * Establece el entorno.
	 *
	 * @param entorno the entorno to set
	 */
	public void setEntorno(String entorno) {
		this.entorno = entorno;
	}


	/**
	 * Obtiene el solicitudes sel.
	 *
	 * @return the solicitudesSel
	 */
	public String[] getSolicitudesSel() {
		return solicitudesSel;
	}


	/**
	 * Establece el solicitudes sel.
	 *
	 * @param solicitudesSel the solicitudesSel to set
	 */
	public void setSolicitudesSel(String[] solicitudesSel) {
		this.solicitudesSel = solicitudesSel;
	}	
}

