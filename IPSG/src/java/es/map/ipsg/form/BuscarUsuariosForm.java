package es.map.ipsg.form;

import es.map.ips.common.spring.SpringForm;

/**
 * El Class BuscarUsuariosForm.
 */
public class BuscarUsuariosForm  extends SpringForm {
	
	/** el estado. */
	private String estado;
	
	/** el id perfil. */
	private String idPerfil;
	
	/** el id centro gestor. */
	private String idCentroGestor;
	
	/** el ds centro gestor. */
	private String dsCentroGestor;
	
	/** el nif. */
	private String nif;//añadido
	
	/** el login. */
	private String login;//añadido
	
	/** el email. */
	private String email;//añadido
	
	/** el accion. */
	private String accion;
	
	/** el campo. */
	private String campo;
	
	/** el direccion. */
	private String direccion;
	
	
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
	 * Obtiene el estado.
	 *
	 * @return el estado
	 */
	public String getEstado() {
		return estado;
	}
	
	/**
	 * Establece el estado.
	 *
	 * @param estado el nuevo estado
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	/**
	 * Obtiene el id perfil.
	 *
	 * @return el id perfil
	 */
	public String getIdPerfil() {
		return idPerfil;
	}
	
	/**
	 * Establece el id perfil.
	 *
	 * @param idPerfil el nuevo id perfil
	 */
	public void setIdPerfil(String idPerfil) {
		this.idPerfil = idPerfil;
	}
	
	/**
	 * Obtiene el id centro gestor.
	 *
	 * @return el id centro gestor
	 */
	public String getIdCentroGestor() {
		return idCentroGestor;
	}
	
	/**
	 * Establece el id centro gestor.
	 *
	 * @param idCentroGestor el nuevo id centro gestor
	 */
	public void setIdCentroGestor(String idCentroGestor) {
		this.idCentroGestor = idCentroGestor;
	}
	
	/**
	 * Obtiene el nif.
	 *
	 * @return el nif
	 */
	public String getNif() {//añadido
		return (nif!=null ? nif.trim():null);
	}
	
	/**
	 * Establece el nif.
	 *
	 * @param nif el nuevo nif
	 */
	public void setNif(String nif) {//añadido
		this.nif = nif;
	}
	
	/**
	 * Obtiene el login.
	 *
	 * @return el login
	 */
	public String getLogin() {//añadido
		return login;
	}
	
	/**
	 * Establece el login.
	 *
	 * @param login el nuevo login
	 */
	public void setLogin(String login) {//añadido
		this.login = login;
	}
	
	/**
	 * Obtiene el email.
	 *
	 * @return el email
	 */
	public String getEmail() {//añadido
		return email;
	}
	
	/**
	 * Establece el email.
	 *
	 * @param email el nuevo email
	 */
	public void setEmail(String email) {//añadido
		this.email = email;
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
	 * Obtiene el ds centro gestor.
	 *
	 * @return el ds centro gestor
	 */
	public String getDsCentroGestor() {
		return dsCentroGestor;
	}
	
	/**
	 * Establece el ds centro gestor.
	 *
	 * @param dsCentroGestor el nuevo ds centro gestor
	 */
	public void setDsCentroGestor(String dsCentroGestor) {
		this.dsCentroGestor = dsCentroGestor;
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
