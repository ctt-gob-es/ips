package es.map.ipsc.bean;


import es.map.ips.model.Ciudadano;
import es.map.ips.model.CorreoElectronico;
import es.map.ips.model.Usuario;


/**
 * El Class IncidenciasBean.
 */
public class IncidenciasBean {
	
	/** el id. */
	private Long id;
	
	/** el ciudadano. */
	private Ciudadano ciudadano;
	
	/** el correo electronico. */
	private CorreoElectronico correoElectronico;
	
	/** el usuario. */
	private Usuario usuario;
	
	/** el nif. */
	private String nif;
	
	/** el tipo. */
	private String tipo;
	
	/** el fecha envio. */
	private String fechaEnvio;
	
	/** el nombre. */
	private String nombre;
	
	/** el primer apellido. */
	private String primerApellido;
	
	/** el segundo apellido. */
	private String segundoApellido;
	
	/** el num total. */
	private Integer numTotal;
	
	/** el telefono. */
	private String telefono;
	
	/** el email. */
	private String email;
	
	/** el mensaje. */
	private String mensaje;
	
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
	 * @param id el nuevo id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * Obtiene el ciudadano.
	 *
	 * @return el ciudadano
	 */
	public Ciudadano getCiudadano() {
		return ciudadano;
	}
	
	/**
	 * Establece el ciudadano.
	 *
	 * @param ciudadano el nuevo ciudadano
	 */
	public void setCiudadano(Ciudadano ciudadano) {
		this.ciudadano = ciudadano;
	}
	
	/**
	 * Obtiene el correo electronico.
	 *
	 * @return el correo electronico
	 */
	public CorreoElectronico getCorreoElectronico() {
		return correoElectronico;
	}
	
	/**
	 * Establece el correo electronico.
	 *
	 * @param correoElectronico el nuevo correo electronico
	 */
	public void setCorreoElectronico(CorreoElectronico correoElectronico) {
		this.correoElectronico = correoElectronico;
	}
	
	/**
	 * Obtiene el usuario.
	 *
	 * @return el usuario
	 */
	public Usuario getUsuario() {
		return usuario;
	}
	
	/**
	 * Establece el usuario.
	 *
	 * @param usuario el nuevo usuario
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	/**
	 * Obtiene el tipo.
	 *
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}
	
	/**
	 * Establece el tipo.
	 *
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	/**
	 * Obtiene el fecha envio.
	 *
	 * @return the fechaEnvio
	 */
	public String getFechaEnvio() {
		return fechaEnvio;
	}
	
	/**
	 * Establece el fecha envio.
	 *
	 * @param fechaEnvio the fechaEnvio to set
	 */
	public void setFechaEnvio(String fechaEnvio) {
		this.fechaEnvio = fechaEnvio;
	}
		
	
	/**
	 * Obtiene el nif.
	 *
	 * @return the nif
	 */
	public String getNif() {
		return nif;
	}
	
	/**
	 * Establece el nif.
	 *
	 * @param nif the nif to set
	 */
	public void setNif(String nif) {
		this.nif = nif;
	}
	
	/**
	 * Obtiene el nombre.
	 *
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * Establece el nombre.
	 *
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * Obtiene el primer apellido.
	 *
	 * @return primerApellido
	 */
	public String getPrimerApellido() {
		return primerApellido;
	}
	
	/**
	 * Establece el primer apellido.
	 *
	 * @param primerApellido the primerApellido to set
	 */
	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}
	
	/**
	 * Obtiene el segundo apellido.
	 *
	 * @return segundoApellido
	 */
	public String getSegundoApellido() {
		return segundoApellido;
	}
	
	/**
	 * Establece el segundo apellido.
	 *
	 * @param segundoApellido the segundoApellido to set
	 */
	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}
	
	/**
	 * Obtiene el num total.
	 *
	 * @return the numTotal
	 */
	public Integer getNumTotal() {
		return numTotal;
	}
	
	/**
	 * Establece el num total.
	 *
	 * @param numTotal the numTotal to set
	 */
	public void setNumTotal(Integer numTotal) {
		this.numTotal = numTotal;
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
	 * @param telefono el nuevo telefono
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
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
	 * @param email el nuevo email
	 */
	public void setEmail(String email) {
		this.email = email;
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
	
	
}
