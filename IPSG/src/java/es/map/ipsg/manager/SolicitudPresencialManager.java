package es.map.ipsg.manager;

import java.util.ArrayList;

import es.map.ips.model.SolicitudComunQuery;
import es.map.ipsg.bean.SolicitudBean;




/**
 * SolicitudManager.
 *
 * @author amartinl
 */
public interface SolicitudPresencialManager {
	
	/**
	 * Buscar solicitud combo.
	 *
	 * @param SolicitudQuery el solicitud query
	 * @return el array list
	 */
	public ArrayList<SolicitudBean> buscarSolicitudCombo(SolicitudComunQuery SolicitudQuery);
	
	/**
	 * Buscar solicitud all.
	 *
	 * @param SolicitudQuery el solicitud query
	 * @return el array list
	 */
	public ArrayList<SolicitudBean> buscarSolicitudAll(SolicitudComunQuery SolicitudQuery);
	
	/**
	 * Obtener solicitud.
	 *
	 * @param idSolicitud el id solicitud
	 * @return el solicitud bean
	 */
	public SolicitudBean obtenerSolicitud (Long idSolicitud);
	
	/**
	 * Buscar solicitudes filtro.
	 *
	 * @param solicitudQuery el solicitud query
	 * @return el array list
	 */
	public ArrayList<SolicitudBean> buscarSolicitudesFiltro (SolicitudComunQuery solicitudQuery);
	
	/**
	 * Modificar solicitud registrada.
	 *
	 * @param solicitudBean el solicitud bean
	 */
	public void modificarSolicitudRegistrada (SolicitudBean  solicitudBean);
	
	/**
	 * Buscar solicitud.
	 *
	 * @param idConvocatoria el id convocatoria
	 * @return el array list
	 */
	public ArrayList<SolicitudBean> buscarSolicitud (Long idConvocatoria);
	
	/**
	 * Guardar solicitud presencial.
	 *
	 * @param solicitudBean el solicitud bean
	 * @return el integer
	 */
	public Integer guardarSolicitudPresencial(SolicitudBean solicitudBean);
	
	/**
	 * Existe solicitud presencial.
	 *
	 * @param solicitudBean el solicitud bean
	 * @return verdadero, si tiene exito
	 */
	public boolean existeSolicitudPresencial(SolicitudBean solicitudBean);
}
