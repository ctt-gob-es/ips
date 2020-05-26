package es.map.ipsg.form;

import es.map.ips.common.spring.SpringForm;


/**
 * El Class CertificadoForm.
 *
 * @author djimenez
 */
public class CertificadoForm extends SpringForm {

	/** La constante serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** el j username. */
	private String j_username;
	
	/** el password field. */
	private String passwordField;
	
	/** el nuevo certif. */
	private String nuevoCertif;
	
	/**
	 * Obtiene el nuevo certif.
	 *
	 * @return el nuevo certif
	 */
	public String getNuevoCertif() {
		return nuevoCertif;
	}
	
	/**
	 * Establece el nuevo certif.
	 *
	 * @param nuevoCertif el nuevo nuevo certif
	 */
	public void setNuevoCertif(String nuevoCertif) {
		this.nuevoCertif = nuevoCertif;
	}
	
	/**
	 * Obtiene el j username.
	 *
	 * @return el j username
	 */
	public String getJ_username() {
		return j_username;
	}
	
	/**
	 * Establece el j username.
	 *
	 * @param j_username el nuevo j username
	 */
	public void setJ_username(String j_username) {
		this.j_username = j_username;
	}
	
	/**
	 * Obtiene el password field.
	 *
	 * @return el password field
	 */
	public String getPasswordField() {
		return passwordField;
	}
	
	/**
	 * Establece el password field.
	 *
	 * @param passwordField el nuevo password field
	 */
	public void setPasswordField(String passwordField) {
		this.passwordField = passwordField;
	}	
}
