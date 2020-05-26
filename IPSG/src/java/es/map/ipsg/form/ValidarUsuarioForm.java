package es.map.ipsg.form;

import org.apache.log4j.Logger;

import es.map.ips.common.spring.SpringForm;

/**
 * El Class ValidarUsuarioForm.
 */
public class ValidarUsuarioForm extends SpringForm {

	/** el usuario. */
	private String usuario;	
	
	/** el password. */
	private String password;
	
	/** el password encriptada. */
	private String passwordEncriptada;
	
	/** el token. */
	private String token;
	
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(ValidarUsuarioForm.class);
	
	
	/**
	 * Obtiene el logger.
	 *
	 * @return el logger
	 */
	public static Logger getLogger() {
		return logger;
	}


	/**
	 * Obtiene el usuario.
	 *
	 * @return el usuario
	 */
	public String getUsuario() {
		return usuario;
	}


	/**
	 * Establece el usuario.
	 *
	 * @param usuario el nuevo usuario
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
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


	/**
	 * Obtiene el password encriptada.
	 *
	 * @return el password encriptada
	 */
	public String getPasswordEncriptada() {
		return passwordEncriptada;
	}


	/**
	 * Establece el password encriptada.
	 *
	 * @param passwordEncriptada el nuevo password encriptada
	 */
	public void setPasswordEncriptada(String passwordEncriptada) {
		this.passwordEncriptada = passwordEncriptada;
	}

	
}
