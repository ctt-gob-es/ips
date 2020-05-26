package es.map.ipsg.manager;

import java.util.ArrayList;

import es.map.ips.model.LogIntermediacionQuery;
import es.map.ipsg.bean.LogIntermediacionBean;

/**
 * El Interface LogIntermediacionManager.
 */
public interface LogIntermediacionManager {

	/**
	 * Buscar logs all.
	 *
	 * @param logIntermediacionQuery el log intermediacion query
	 * @return el array list
	 */
	public ArrayList<LogIntermediacionBean> buscarLogsAll(LogIntermediacionQuery logIntermediacionQuery);
	
	/**
	 * Actualizar log intermediacion.
	 *
	 * @param logIntermediacion el log intermediacion
	 * @param i el i
	 */
	public void actualizarLogIntermediacion(LogIntermediacionBean logIntermediacion, int i);
	
	/**
	 * Guardar log intermediacion.
	 *
	 * @param logIntermediacionBean el log intermediacion bean
	 */
	public void guardarLogIntermediacion(LogIntermediacionBean logIntermediacionBean);
	
	/**
	 * Borrar solicitud log intermediacion.
	 *
	 * @param idSolicitud el id solicitud
	 */
	public void borrarSolicitudLogIntermediacion(Long idSolicitud);
	
	/**
	 * Guardar log intermediacion Aux.
	 * 
	 * @param logIntermediacionAuxBean
	 */
	public void guardarLogIntermediacionAux(LogIntermediacionBean logIntermediacionAuxBean);
}
