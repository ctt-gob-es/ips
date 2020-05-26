package es.map.ipsc.bean;


import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import es.map.ips.model.Incidencia;


/**
 * El Class CiudadanoBean.
 */
public class CiudadanoBean implements Serializable  {
	
	/** el id. */
	private Long id;
	
	/** el tipo autenticacion. */
	private String tipoAutenticacion;
	
	/** el nif. */
	private String nif;
	
	/** el nombre. */
	private String nombre;
	
	/** el primer apellido. */
	private String primerApellido;
	
	/** el segundo apellido. */
	private String segundoApellido;
	
	/** el telefono. */
	private String telefono;
	
	/** el email. */
	private String email;
	
	/** el incidencias. */
	private transient Set incidencias = new HashSet(0);
	
	/** el token usuario. */
	private String tokenUsuario;
	
	/** el aleatorio. */
	private int aleatorio;
	
	/** el organizacion emisora. */
	private String organizacionEmisora;
	
	/** el numero serie. */
	private String numeroSerie;
	
	/** el id emisor. */
	private String idEmisor;
	
	/** el tipo persona. */
	private String tipoPersona;

	

	/**
	 * Obtiene el id.
	 *
	 * @return el id
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * Establece el id.
	 *
	 * @param pId el nuevo id
	 */
	public void setId(Long pId) {
		this.id = pId;
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
	 * @param pNif el nuevo nif
	 */
	public void setNif(String pNif) {
		this.nif = pNif;
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
	 * @param pNombre el nuevo nombre
	 */
	public void setNombre(String pNombre) {
		this.nombre = pNombre;
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
	 * @param pPrimerApellido el nuevo primer apellido
	 */
	public void setPrimerApellido(String pPrimerApellido) {
		this.primerApellido = pPrimerApellido;
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
	 * @param pSegundoApellido el nuevo segundo apellido
	 */
	public void setSegundoApellido(String pSegundoApellido) {
		this.segundoApellido = pSegundoApellido;
	}
	
	/**
	 * Obtiene el telefono.
	 *
	 * @return el telefono
	 */
	public String getTelefono() {
		return telefono;
	}
	
	/**
	 * Establece el telefono.
	 *
	 * @param pTelefono el nuevo telefono
	 */
	public void setTelefono(String pTelefono) {
		this.telefono = pTelefono;
	}
	
	/**
	 * Obtiene el email.
	 *
	 * @return el email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Establece el email.
	 *
	 * @param pEmail el nuevo email
	 */
	public void setEmail(String pEmail) {
		this.email = pEmail;
	}

	/**
	 * Obtiene el token usuario.
	 *
	 * @return el token usuario
	 */
	public String getTokenUsuario() {
		return tokenUsuario;
	}
	
	/**
	 * Establece el token usuario.
	 *
	 * @param pTokenUsuario el nuevo token usuario
	 */
	public void setTokenUsuario(String pTokenUsuario) {
		this.tokenUsuario = pTokenUsuario;
	}
	
	/**
	 * Obtiene el aleatorio.
	 *
	 * @return el aleatorio
	 */
	public int getAleatorio() {
		return aleatorio;
	}
	
	/**
	 * Establece el aleatorio.
	 *
	 * @param pAleatorio el nuevo aleatorio
	 */
	public void setAleatorio(int pAleatorio) {
		this.aleatorio = pAleatorio;
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
	 * Obtiene el id emisor.
	 *
	 * @return el id emisor
	 */
	public String getIdEmisor() {
		return idEmisor;
	}
	
	/**
	 * Establece el id emisor.
	 *
	 * @param idEmisor el nuevo id emisor
	 */
	public void setIdEmisor(String idEmisor) {
		this.idEmisor = idEmisor;
	}
	
	/**
	 * Obtiene el incidencias.
	 *
	 * @return el incidencias
	 */
	public Set getIncidencias() {
		return incidencias;
	}
	
	/**
	 * Establece el incidencias.
	 *
	 * @param incidencias el nuevo incidencias
	 */
	public void setIncidencias(Set incidencias) {
		this.incidencias = incidencias;
	}
	
	/**
	 * Obtiene el tipo autenticacion.
	 *
	 * @return el tipo autenticacion
	 */
	public String getTipoAutenticacion() {
		return tipoAutenticacion;
	}
	
	/**
	 * Establece el tipo autenticacion.
	 *
	 * @param tipoAutenticacion el nuevo tipo autenticacion
	 */
	public void setTipoAutenticacion(String tipoAutenticacion) {
		this.tipoAutenticacion = tipoAutenticacion;
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
	