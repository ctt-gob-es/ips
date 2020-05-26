package es.map.ipsg.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import es.map.ips.common.spring.SpringErrors;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringMapping;
import es.map.ips.common.spring.SpringMessage;
import es.map.ipsg.action.convocatoria.BuscarConvocatoriasSpring;

/**
 * El Class CambiarContrasenaForm.
 */
public class CambiarContrasenaForm extends SpringForm {

	/** el password actual. */
	private String passwordActual;
	
	/** el password nueva. */
	private String passwordNueva;
	
	/** el confirmacion password nueva. */
	private String confirmacionPasswordNueva;

	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(BuscarConvocatoriasSpring.class);
	
	/**
	 * Obtiene el password actual.
	 *
	 * @return el password actual
	 */
	public String getPasswordActual() {
		return passwordActual;
	}
	
	/**
	 * Establece el password actual.
	 *
	 * @param passwordActual el nuevo password actual
	 */
	public void setPasswordActual(String passwordActual) {
		this.passwordActual = passwordActual;
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
		
		if(passwordActual == null || passwordActual.equalsIgnoreCase("")){
			request.setAttribute("errorPasswordActual", "errorPasswordActual");
			SpringErrors.add("dsPasswordActual", new SpringMessage("usuario.errores.passwordActualRequerido"));
		}
		
		if(passwordNueva == null || passwordNueva.equalsIgnoreCase("")){
			request.setAttribute("errorPasswordNueva", "errorPasswordNueva");
			SpringErrors.add("dsErrorNombre", new SpringMessage("usuario.errores.passwordNuevaRequerido"));
		}
		
		if(confirmacionPasswordNueva == null || confirmacionPasswordNueva.equalsIgnoreCase("")){
			request.setAttribute("errorConfirmacionPasswordNueva", "errorConfirmacionPasswordNueva");
			SpringErrors.add("dsErrorConfirmacionPasswordNueva", new SpringMessage("usuario.errores.confirmacionPasswordNuevaRequerido"));
		}
		
		if(!confirmacionPasswordNueva.equals(passwordNueva)){
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
