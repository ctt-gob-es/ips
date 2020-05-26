package es.map.ipsg.bean;

import java.util.Date;


/**
 * El Class UsuarioBean.
 */
public class UsuarioBean {
	
	/** el id. */
	private Integer id;
	
	/** el id centro gestor. */
	private Integer idCentroGestor;
	
	/** el des centro gestor. */
	private String desCentroGestor;
	
	/*INI-TRA042*/
	private UsuarioCentrogestorBean usuCg; 
	/*FIN-TRA042*/
	
	/** el id perfil. */
	private Integer idPerfil;
	
	/** el des perfil. */
	private String desPerfil;
	
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
	
	/** el token. */
	private String token;
	
	/** el ultima act password. */
	private Date ultimaActPassword;
	
	/** el recibe correos incidencias. */
	private boolean recibeCorreosIncidencias;
	
	/** el publica convocatorias. */
	private boolean publicaConvocatorias;
	
	/** el login. */
	private String login;
	
	/** el password. */
	private String password;
	
	/** el confirmacion password. */
	private String confirmacionPassword;
	
	/** el estado. */
	private Character estado;
	
	/** el nombre completo. */
	private String nombreCompleto;
	
	
	/** el num total. */
	private int numTotal;	
	
	/** el num registros. */
	private String numRegistros;
	
	/** el submit. */
	private String submit;
	
	/** el cambios. */
	private String cambios;
	
	/*INI-TRA042*/
	/** el lista CG concat. */
	private String listaCGconcat;
	/*FIN-TRA042*/
	
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
	 * Obtiene el num total.
	 *
	 * @return el num total
	 */
	public int getNumTotal() {
		return numTotal;
	}
	
	/**
	 * Establece el num total.
	 *
	 * @param numTotal el nuevo num total
	 */
	public void setNumTotal(int numTotal) {
		this.numTotal = numTotal;
	}
	
	/**
	 * Obtiene el num registros.
	 *
	 * @return el num registros
	 */
	public String getNumRegistros() {
		return numRegistros;
	}
	
	/**
	 * Establece el num registros.
	 *
	 * @param numRegistros el nuevo num registros
	 */
	public void setNumRegistros(String numRegistros) {
		this.numRegistros = numRegistros;
	}
	
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
	 * Obtiene el id centro gestor.
	 *
	 * @return el id centro gestor
	 */
	public Integer getIdCentroGestor() {
		return idCentroGestor;
	}
	
	/**
	 * Establece el id centro gestor.
	 *
	 * @param idCentroGestor el nuevo id centro gestor
	 */
	public void setIdCentroGestor(Integer idCentroGestor) {
		this.idCentroGestor = idCentroGestor;
	}
	
	/**
	 * Obtiene el id perfil.
	 *
	 * @return el id perfil
	 */
	public Integer getIdPerfil() {
		return idPerfil;
	}
	
	/**
	 * Establece el id perfil.
	 *
	 * @param idPerfil el nuevo id perfil
	 */
	public void setIdPerfil(Integer idPerfil) {
		this.idPerfil = idPerfil;
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
	 * Obtiene el ultima act password.
	 *
	 * @return el ultima act password
	 */
	public Date getUltimaActPassword() {
		return ultimaActPassword;
	}
	
	/**
	 * Establece el ultima act password.
	 *
	 * @param ultimaActPassword el nuevo ultima act password
	 */
	public void setUltimaActPassword(Date ultimaActPassword) {
		this.ultimaActPassword = ultimaActPassword;
	}
	
	/**
	 * Obtiene el recibe correos incidencias.
	 *
	 * @return el recibe correos incidencias
	 */
	public boolean getRecibeCorreosIncidencias() {
		return recibeCorreosIncidencias;
	}
	
	/**
	 * Establece el recibe correos incidencias.
	 *
	 * @param recibeCorreosIncidencias el nuevo recibe correos incidencias
	 */
	public void setRecibeCorreosIncidencias(boolean recibeCorreosIncidencias) {
		this.recibeCorreosIncidencias = recibeCorreosIncidencias;
	}
	
	/**
	 * Obtiene el publica convocatorias.
	 *
	 * @return el publica convocatorias
	 */
	public boolean getPublicaConvocatorias() {
		return publicaConvocatorias;
	}
	
	/**
	 * Establece el publica convocatorias.
	 *
	 * @param publicaConvocatorias el nuevo publica convocatorias
	 */
	public void setPublicaConvocatorias(boolean publicaConvocatorias) {
		this.publicaConvocatorias = publicaConvocatorias;
	}
	
	/**
	 * Obtiene el login.
	 *
	 * @return el login
	 */
	public String getLogin() {
		return login;
	}
	
	/**
	 * Establece el login.
	 *
	 * @param login el nuevo login
	 */
	public void setLogin(String login) {
		this.login = login;
	}
	
	/**
	 * Obtiene el password.
	 *
	 * @return el password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Establece el password.
	 *
	 * @param password el nuevo password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * Obtiene el confirmacion password.
	 *
	 * @return el confirmacion password
	 */
	public String getConfirmacionPassword() {
		return confirmacionPassword;
	}
	
	/**
	 * Establece el confirmacion password.
	 *
	 * @param confirmacionPassword el nuevo confirmacion password
	 */
	public void setConfirmacionPassword(String confirmacionPassword) {
		this.confirmacionPassword = confirmacionPassword;
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
	 * Obtiene el des centro gestor.
	 *
	 * @return el des centro gestor
	 */
	public String getDesCentroGestor() {
		return desCentroGestor;
	}
	
	/**
	 * Establece el des centro gestor.
	 *
	 * @param desCentroGestor el nuevo des centro gestor
	 */
	public void setDesCentroGestor(String desCentroGestor) {
		this.desCentroGestor = desCentroGestor;
	}
	
	/**
	 * Obtiene el des perfil.
	 *
	 * @return el des perfil
	 */
	public String getDesPerfil() {
		return desPerfil;
	}
	
	/**
	 * Establece el des perfil.
	 *
	 * @param desPerfil el nuevo des perfil
	 */
	public void setDesPerfil(String desPerfil) {
		this.desPerfil = desPerfil;
	}
	
	/**
	 * Obtiene el nombre completo.
	 *
	 * @return the nombreCompleto
	 */
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	
	/**
	 * Establece el nombre completo.
	 *
	 * @param nombreCompleto the nombreCompleto to set
	 */
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	
	/**
	 * Obtiene el token.
	 *
	 * @return el token
	 */
	public String getToken() {
		return token;
	}
	
	/**
	 * Establece el token.
	 *
	 * @param token el nuevo token
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/*INI-TRA042*/
	/**
	 * @return the usuCg
	 */
	public UsuarioCentrogestorBean getUsuCg() {
		return usuCg;
	}

	/**
	 * @param usuCg the usuCg to set
	 */
	public void setUsuCg(UsuarioCentrogestorBean usuCg) {
		this.usuCg = usuCg;
	}

	/**
	 * @return the listaCGconcat
	 */
	public String getListaCGconcat() {
		return listaCGconcat;
	}

	/**
	 * @param listaCGconcat the listaCGconcat to set
	 */
	public void setListaCGconcat(String listaCGconcat) {
		this.listaCGconcat = listaCGconcat;
	}
	/*FIN-TRA042*/
	
}
