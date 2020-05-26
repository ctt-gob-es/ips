package es.map.ipsg.form;

import es.map.ips.common.spring.SpringForm;


/**
 * El Class ConsultarPagoForm.
 *
 * @author djimenez
 */
public class ConsultarPagoForm extends SpringForm {
	
	/** el estado actual. */
	private String estadoActual;

	/**
	 * Obtiene el estado actual.
	 *
	 * @return el estado actual
	 */
	public String getEstadoActual() {
		return estadoActual;
	}

	/**
	 * Establece el estado actual.
	 *
	 * @param estadoActual el nuevo estado actual
	 */
	public void setEstadoActual(String estadoActual) {
		this.estadoActual = estadoActual;
	}

	
	
	
}
