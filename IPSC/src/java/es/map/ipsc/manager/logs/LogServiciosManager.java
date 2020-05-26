package es.map.ipsc.manager.logs;

import es.map.ipsc.bean.LogServicioBean;

/**
 * El Interface LogServiciosManager.
 */
public interface LogServiciosManager {

	/**
	 * Insertar log.
	 *
	 * @param logServicioBean el log servicio bean
	 * @return el int
	 */
	int insertarLog(LogServicioBean logServicioBean);


	
}
