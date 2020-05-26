package es.map.ipsg.manager;

import es.map.ipsg.bean.ContadorNumSolicitudBean;

/**
 * El Interface ContadorNumSolicitudManager.
 */
public interface ContadorNumSolicitudManager {
	
	/**
	 * Modificar contador solicitud.
	 *
	 * @param contadorNumSolicitudBean el contador num solicitud bean
	 */
	public void modificarContadorSolicitud (ContadorNumSolicitudBean contadorNumSolicitudBean);
	
	/**
	 * Obtener contador num solicitud.
	 *
	 * @return el contador num solicitud bean
	 */
	public ContadorNumSolicitudBean obtenerContadorNumSolicitud();
}
