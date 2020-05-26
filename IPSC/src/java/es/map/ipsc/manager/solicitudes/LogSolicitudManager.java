package es.map.ipsc.manager.solicitudes;

import es.map.ipsc.bean.LogSolicitudBean;

/**
 * El Interface LogSolicitudManager.
 */
public interface LogSolicitudManager {

	/**
	 * Actualizar log solicitud.
	 *
	 * @param logSolicitudBean el log solicitud bean
	 * @return el integer
	 */
	Integer actualizarLogSolicitud(LogSolicitudBean logSolicitudBean);


	
}
