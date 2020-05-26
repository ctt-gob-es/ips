package es.map.ipsc.bean;


import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import es.map.ips.model.Incidencia;
import es.map.ips.model.SolicitudComun;


/**
 * El Class AuditoriaBean.
 */
public class AuditoriaBean implements Serializable  {
	
	/** el id solicitud. */
	private Long idSolicitud;
	
	/** el solicitud. */
	private SolicitudComun solicitud;
	
	/** el fecha autenticacion. */
	private Date fechaAutenticacion;
	
	/** el nif. */
	private String nif;
	
	/** el nombre. */
	private String nombre;
	
	/** el primer apellido. */
	private String primerApellido;
	
	/** el segundo apellido. */
	private String segundoApellido;
	
	/** el ip solicitante. */
	private String ipSolicitante;
	
	/** el datos navegador. */
	private String datosNavegador;
	
	/** el proveedor identidad. */
	private String proveedorIdentidad;	
	
	/** el organizacion emisora. */
	private String organizacionEmisora;
	
	/** el numero serie. */
	private String numeroSerie;
	
	/** el respuesta clave. */
	private byte[] respuestaClave;
	
	/** el tipo persona. */
	private String tipoPersona;
	
	
	

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
	 * @param idSolicitud el nuevo id solicitud
	 */
	public void setIdSolicitud(Long idSolicitud) {
		this.idSolicitud = idSolicitud;
	}
	
	/**
	 * Obtiene el solicitud.
	 *
	 * @return el solicitud
	 */
	public SolicitudComun getSolicitud() {
		return solicitud;
	}
	
	/**
	 * Establece el solicitud.
	 *
	 * @param solicitud el nuevo solicitud
	 */
	public void setSolicitud(SolicitudComun solicitud) {
		this.solicitud = solicitud;
	}
	
	/**
	 * Obtiene el fecha autenticacion.
	 *
	 * @return el fecha autenticacion
	 */
	public Date getFechaAutenticacion() {
		return fechaAutenticacion;
	}
	
	/**
	 * Establece el fecha autenticacion.
	 *
	 * @param fechaAutenticacion el nuevo fecha autenticacion
	 */
	public void setFechaAutenticacion(Date fechaAutenticacion) {
		this.fechaAutenticacion = fechaAutenticacion;
	}
	
	/**
	 * Obtiene el nif.
	 *
	 * @return el nif
	 */
	public String getNif() {
		return nif;
	}
	
	/**
	 * Establece el nif.
	 *
	 * @param nif el nuevo nif
	 */
	public void setNif(String nif) {
		this.nif = nif;
	}
	
	/**
	 * Obtiene el nombre.
	 *
	 * @return el nombre
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * Establece el nombre.
	 *
	 * @param nombre el nuevo nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * Obtiene el primer apellido.
	 *
	 * @return el primer apellido
	 */
	public String getPrimerApellido() {
		return primerApellido;
	}
	
	/**
	 * Establece el primer apellido.
	 *
	 * @param primerApellido el nuevo primer apellido
	 */
	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}
	
	/**
	 * Obtiene el segundo apellido.
	 *
	 * @return el segundo apellido
	 */
	public String getSegundoApellido() {
		return segundoApellido;
	}
	
	/**
	 * Establece el segundo apellido.
	 *
	 * @param segundoApellido el nuevo segundo apellido
	 */
	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}
	
	/**
	 * Obtiene el ip solicitante.
	 *
	 * @return el ip solicitante
	 */
	public String getIpSolicitante() {
		return ipSolicitante;
	}
	
	/**
	 * Establece el ip solicitante.
	 *
	 * @param ipSolicitante el nuevo ip solicitante
	 */
	public void setIpSolicitante(String ipSolicitante) {
		this.ipSolicitante = ipSolicitante;
	}
	
	/**
	 * Obtiene el datos navegador.
	 *
	 * @return el datos navegador
	 */
	public String getDatosNavegador() {
		return datosNavegador;
	}
	
	/**
	 * Establece el datos navegador.
	 *
	 * @param datosNavegador el nuevo datos navegador
	 */
	public void setDatosNavegador(String datosNavegador) {
		this.datosNavegador = datosNavegador;
	}
	
	/**
	 * Obtiene el proveedor identidad.
	 *
	 * @return el proveedor identidad
	 */
	public String getProveedorIdentidad() {
		return proveedorIdentidad;
	}
	
	/**
	 * Establece el proveedor identidad.
	 *
	 * @param proveedorIdentidad el nuevo proveedor identidad
	 */
	public void setProveedorIdentidad(String proveedorIdentidad) {
		this.proveedorIdentidad = proveedorIdentidad;
	}
	
	/**
	 * Obtiene el organizacion emisora.
	 *
	 * @return el organizacion emisora
	 */
	public String getOrganizacionEmisora() {
		return organizacionEmisora;
	}
	
	/**
	 * Establece el organizacion emisora.
	 *
	 * @param organizacionEmisora el nuevo organizacion emisora
	 */
	public void setOrganizacionEmisora(String organizacionEmisora) {
		this.organizacionEmisora = organizacionEmisora;
	}
	
	/**
	 * Obtiene el numero serie.
	 *
	 * @return el numero serie
	 */
	public String getNumeroSerie() {
		return numeroSerie;
	}
	
	/**
	 * Establece el numero serie.
	 *
	 * @param numeroSerie el nuevo numero serie
	 */
	public void setNumeroSerie(String numeroSerie) {
		this.numeroSerie = numeroSerie;
	}
	
	/**
	 * Obtiene el respuesta clave.
	 *
	 * @return el respuesta clave
	 */
	public byte[] getRespuestaClave() {
		return respuestaClave;
	}
	
	/**
	 * Establece el respuesta clave.
	 *
	 * @param respuestaClave el nuevo respuesta clave
	 */
	public void setRespuestaClave(byte[] respuestaClave) {
		this.respuestaClave = respuestaClave;
	}
	
	/**
	 * Obtiene el tipo persona.
	 *
	 * @return el tipo persona
	 */
	public String getTipoPersona() {
		return tipoPersona;
	}
	
	/**
	 * Establece el tipo persona.
	 *
	 * @param tipoPersona el nuevo tipo persona
	 */
	public void setTipoPersona(String tipoPersona) {
		this.tipoPersona = tipoPersona;
	}
	
	
	
}
	