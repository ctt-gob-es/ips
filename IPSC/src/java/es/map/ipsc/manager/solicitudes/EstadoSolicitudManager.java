package es.map.ipsc.manager.solicitudes;

import java.util.ArrayList;

import es.map.ips.model.EstadoSolicitud;
import es.map.ips.model.EstadoSolicitudQuery;
import es.map.ipsc.bean.EstadoSolicitudBean;

/**
 * El Interface EstadoSolicitudManager.
 */
public interface EstadoSolicitudManager {

	/**
	 * Buscar estado solicitud combo.
	 *
	 * @return el array list
	 */
	ArrayList<EstadoSolicitudBean> buscarEstadoSolicitudCombo();

	/**
	 * Buscar id estado solicitud.
	 *
	 * @param estadoSolicitudQuery el estado solicitud query
	 * @return el estado solicitud bean
	 */
	EstadoSolicitudBean buscarIdEstadoSolicitud(EstadoSolicitudQuery estadoSolicitudQuery);
	
	/**
	 * Buscar id estado solicitud model.
	 *
	 * @param estadoSolicitudQuery el estado solicitud query
	 * @return el estado solicitud
	 */
	EstadoSolicitud buscarIdEstadoSolicitudModel(EstadoSolicitudQuery estadoSolicitudQuery);

	/**
	 * Buscar estado solicitud desc.
	 *
	 * @param estadoSolicitudDescripcion el estado solicitud descripcion
	 * @return el estado solicitud bean
	 */
	EstadoSolicitudBean buscarEstadoSolicitudDesc(EstadoSolicitudQuery estadoSolicitudDescripcion);

}
