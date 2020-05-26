package es.map.ipsg.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import es.map.ips.common.spring.SpringErrors;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringMapping;
import es.map.ips.common.spring.SpringMessage;


/**
 * El Class ActualizarContrasenaForm.
 */
public class ActualizarContrasenaForm extends SpringForm {

	/** el id. */
	private String id;
	
	/** el password nueva. */
	private String passwordNueva;
	
	/** el confirmacion password nueva. */
	private String confirmacionPasswordNueva;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(ActualizarContrasenaForm.class);
	
	/**
	 * Obtiene el id.
	 *
	 * @return el id
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Establece el id.
	 *
	 * @param id el nuevo id
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * Obtiene el password nueva.
	 *
	 * @return el password nueva
	 */
	public String getPasswordNueva() {
		return passwordNueva;
	}
	
	/**
	 * Establece el password nueva.
	 *
	 * @param passwordNueva el nuevo password nueva
	 */
	public void setPasswordNueva(String passwordNueva) {
		this.passwordNueva = passwordNueva;
	}
	
	/**
	 * Obtiene el confirmacion password nueva.
	 *
	 * @return el confirmacion password nueva
	 */
	public String getConfirmacionPasswordNueva() {
		return confirmacionPasswordNueva;
	}
	
	/**
	 * Establece el confirmacion password nueva.
	 *
	 * @param confirmacionPasswordNueva el nuevo confirmacion password nueva
	 */
	public void setConfirmacionPasswordNueva(String confirmacionPasswordNueva) {
		this.confirmacionPasswordNueva = confirmacionPasswordNueva;
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.SpringForm#validate(es.map.ips.common.spring.SpringMapping, javax.servlet.http.HttpServletRequest)
	 */
	public SpringErrors validate(SpringMapping mapping,	HttpServletRequest request) {
		logger.info("Validando");
		SpringErrors SpringErrors = new SpringErrors();
		
		if(passwordNueva == null || passwordNueva.equalsIgnoreCase("")){
			request.setAttribute("errorPasswordNueva", "errorPasswordNueva");
			SpringErrors.add("dsErrorNombre", new SpringMessage("usuario.errores.passwordNuevaRequerido"));
		}
		
		if(confirmacionPasswordNueva == null || confirmacionPasswordNueva.equalsIgnoreCase("")){
			request.setAttribute("errorConfirmacionPasswordNueva", "errorConfirmacionPasswordNueva");
			SpringErrors.add("dsErrorConfirmacionPasswordNueva", new SpringMessage("usuario.errores.confirmacionPasswordNuevaRequerido"));
		}
		
		if(confirmacionPasswordNueva != null && !confirmacionPasswordNueva.equals(passwordNueva)){
		
			request.setAttribute("errorEqualPasswordNuevas", "errorEqualPasswordNuevas");
			SpringErrors.add("dsEqualPasswordNuevas", new SpringMessage("usuario.errores.passwordNuevasDistintas"));
				
		}
		
		return SpringErrors;
	}
	
	/**
	 * Obtiene el logger.
	 *
	 * @return el logger
	 */
	public static Logger getLogger() {
		return logger;
	}
	
}
