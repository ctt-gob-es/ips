package es.map.ipsc.form;

import es.map.ips.common.spring.SpringForm;

/**
 * El Class BuscarSolicitudesForm.
 */
public class BuscarSolicitudesForm extends SpringForm {

	/** el campo. */
	private String campo;
	
	/** el direccion. */
	private String direccion;
	
	/** el lugar. */
	private String lugar;
	
	/** el submit. */
	private String submit;
	
	/** el id solicitud. */
	private Long idSolicitud;
	
	/** el num justificante. */
	private String numJustificante;
	
	/** el ejercicio solicitud. */
	private String ejercicioSolicitud;
	
	/** el ministerio. */
	private String ministerio;
	
	/** el cuerpo escala. */
	private String cuerpoEscala;
	
	/** el fecha. */
	private String fecha;
	
	/** el estado inscripcion. */
	private String estadoInscripcion;
	
    /** el num registro. */
    private String numRegistro;
    
    /** el pagina actual. */
    private String paginaActual;	
	
	/** el paginas totales. */
	private String paginasTotales;
	
	/** el cambios. */
	private String cambios;
	
	/** el numero pagina ir. */
	private Integer numeroPaginaIr;
	
	/** el pulsa ir. */
	private boolean pulsaIr;

	/** el llamada. */
	private String llamada;
	
	/** el buscador nif input. */
	private String buscadorNifInput;
	

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
	 * @param pPaginaActual el nuevo pagina actual
	 */
	public void setPaginaActual(String pPaginaActual) {
		this.paginaActual = pPaginaActual;
	}

	/**
	 * Obtiene el id solicitud.
	 *
	 * @return el id solicitud
	 */
	public Long getIdSolicitud() {
		return idSolicitud;
	}

	/**
	 * Establece el id solicitud.
	 *
	 * @param pIdSolicitud el nuevo id solicitud
	 */
	public void setIdSolicitud(Long pIdSolicitud) {
		this.idSolicitud = pIdSolicitud;
	}

	/**
	 * Obtiene el num justificante.
	 *
	 * @return el num justificante
	 */
	public String getNumJustificante() {
		return numJustificante;
	}

	/**
	 * Establece el num justificante.
	 *
	 * @param pNumJustificante el nuevo num justificante
	 */
	public void setNumJustificante(String pNumJustificante) {
		this.numJustificante = pNumJustificante;
	}

	/**
	 * Obtiene el ejercicio solicitud.
	 *
	 * @return el ejercicio solicitud
	 */
	public String getEjercicioSolicitud() {
		return ejercicioSolicitud;
	}

	/**
	 * Establece el ejercicio solicitud.
	 *
	 * @param pEjercicioSolicitud el nuevo ejercicio solicitud
	 */
	public void setEjercicioSolicitud(String pEjercicioSolicitud) {
		this.ejercicioSolicitud = pEjercicioSolicitud;
	}

	/**
	 * Obtiene el ministerio.
	 *
	 * @return el ministerio
	 */
	public String getMinisterio() {
		return ministerio;
	}

	/**
	 * Establece el ministerio.
	 *
	 * @param pMinisterio el nuevo ministerio
	 */
	public void setMinisterio(String pMinisterio) {
		this.ministerio = pMinisterio;
	}

	/**
	 * Obtiene el cuerpo escala.
	 *
	 * @return el cuerpo escala
	 */
	public String getCuerpoEscala() {
		return cuerpoEscala;
	}

	/**
	 * Establece el cuerpo escala.
	 *
	 * @param pCuerpoEscala el nuevo cuerpo escala
	 */
	public void setCuerpoEscala(String pCuerpoEscala) {
		this.cuerpoEscala = pCuerpoEscala;
	}

	/**
	 * Obtiene el fecha.
	 *
	 * @return el fecha
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * Establece el fecha.
	 *
	 * @param pFecha el nuevo fecha
	 */
	public void setFecha(String pFecha) {
		this.fecha = pFecha;
	}

	/**
	 * Obtiene el estado inscripcion.
	 *
	 * @return el estado inscripcion
	 */
	public String getEstadoInscripcion() {
		return estadoInscripcion;
	}

	/**
	 * Establece el estado inscripcion.
	 *
	 * @param pEstadoInscripcion el nuevo estado inscripcion
	 */
	public void setEstadoInscripcion(String pEstadoInscripcion) {
		this.estadoInscripcion = pEstadoInscripcion;
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
	 * @param pCampo el nuevo campo
	 */
	public void setCampo(String pCampo) {
		this.campo = pCampo;
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
	 * @param pDireccion el nuevo direccion
	 */
	public void setDireccion(String pDireccion) {
		this.direccion = pDireccion;
	}

	/**
	 * Obtiene el lugar.
	 *
	 * @return el lugar
	 */
	public String getLugar() {
		return lugar;
	}

	/**
	 * Establece el lugar.
	 *
	 * @param pLugar el nuevo lugar
	 */
	public void setLugar(String pLugar) {
		this.lugar = pLugar;
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
	 * @param pSubmit el nuevo submit
	 */
	public void setSubmit(String pSubmit) {
		this.submit = pSubmit;
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
	 * Obtiene el llamada.
	 *
	 * @return el llamada
	 */
	public String getLlamada() {
		return llamada;
	}

	/**
	 * Establece el llamada.
	 *
	 * @param llamada el nuevo llamada
	 */
	public void setLlamada(String llamada) {
		this.llamada = llamada;
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
	 * Obtiene el buscador nif input.
	 *
	 * @return el buscador nif input
	 */
	public String getBuscadorNifInput() {
		return buscadorNifInput;
	}

	/**
	 * Establece el buscador nif input.
	 *
	 * @param buscadorNifInput el nuevo buscador nif input
	 */
	public void setBuscadorNifInput(String buscadorNifInput) {
		this.buscadorNifInput = buscadorNifInput;
	}
	
	
	

}