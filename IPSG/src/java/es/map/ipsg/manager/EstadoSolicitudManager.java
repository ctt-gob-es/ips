package es.map.ipsg.manager;

import java.util.ArrayList;

import es.map.ips.model.EstadoSolicitud;
import es.map.ips.model.EstadoSolicitudQuery;
import es.map.ipsg.bean.EstadoSolicitudBean;


/**
 * El Interface EstadoSolicitudManager.
 */
public interface EstadoSolicitudManager {
	
	/**
	 * Buscar estado solicitud combo.
	 *
	 * @param estadoSolicitudQuery el estado solicitud query
	 * @return el array list
	 */
	public ArrayList<EstadoSolicitudBean> buscarEstadoSolicitudCombo(EstadoSolicitudQuery estadoSolicitudQuery);
	
	/**
	 * Buscar estado solicitud by id registro.
	 *
	 * @param estadoSolicitudQuery el estado solicitud query
	 * @return el estado solicitud
	 */
	public EstadoSolicitud buscarEstadoSolicitudByIdRegistro(
			EstadoSolicitudQuery estadoSolicitudQuery) ;
	
	/**
	 * Obtener estado solicitud.
	 *
	 * @param idEstadoSolicitud el id estado solicitud
	 * @return el estado solicitud
	 */
	public EstadoSolicitud obtenerEstadoSolicitud(Integer idEstadoSolicitud );
	
	/**
	 * Buscar estado solicitud by id.
	 *
	 * @param estadoSolicitudQuery el estado solicitud query
	 * @return el estado solicitud bean
	 */
	public EstadoSolicitudBean buscarEstadoSolicitudById(
			EstadoSolicitudQuery estadoSolicitudQuery);
}
