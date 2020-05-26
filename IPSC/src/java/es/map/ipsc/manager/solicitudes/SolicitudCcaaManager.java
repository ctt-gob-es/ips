package es.map.ipsc.manager.solicitudes;

import es.map.ips.model.SolicitudCcaaQuery;
import es.map.ipsc.bean.SolicitudCcaaBean;

/**
 * El Interface SolicitudCcaaManager.
 */
public interface SolicitudCcaaManager {
	
	 /**
 	 * Guardar solicitud ccaa.
 	 *
 	 * @param solicitudCcaaBean el solicitud ccaa bean
 	 */
 	public void guardarSolicitudCcaa(SolicitudCcaaBean solicitudCcaaBean);
	 
 	/**
 	 * Buscar solicitud ccaa id solicitud.
 	 *
 	 * @param solicitudCcaaQuery el solicitud ccaa query
 	 * @return el solicitud ccaa bean
 	 */
 	public SolicitudCcaaBean buscarSolicitudCcaaIdSolicitud(SolicitudCcaaQuery solicitudCcaaQuery);
}
