package es.map.ipsg.manager;

import java.util.ArrayList;

import es.map.ips.model.LogSolicitudQuery;
import es.map.ipsg.bean.LogSolicitudBean;

/**
 * El Interface LogSolicitudManager.
 */
public interface LogSolicitudManager {


	/**
	 * Generar registro log solicitud.
	 *
	 * @param logSolicitudBean el log solicitud bean
	 */
	public void generarRegistroLogSolicitud (LogSolicitudBean logSolicitudBean);
	
	/**
	 * Obtener log solicitud.
	 *
	 * @param id el id
	 * @return el log solicitud bean
	 */
	public LogSolicitudBean obtenerLogSolicitud(Integer id);
	
	/**
	 * Buscar log solicitud all.
	 *
	 * @param logSolicitudQuery el log solicitud query
	 * @return el array list
	 */
	public ArrayList <LogSolicitudBean> buscarLogSolicitudAll (LogSolicitudQuery logSolicitudQuery);
	
	/**
	 * Insertar log solicitud.
	 *
	 * @param logSolicitudBean el log solicitud bean
	 * @return el integer
	 */
	public Integer insertarLogSolicitud (LogSolicitudBean logSolicitudBean);
	
	/**
	 * Borrar solicitud log.
	 *
	 * @param idSolicitud el id solicitud
	 */
	public void borrarSolicitudLog(Long idSolicitud);
}
