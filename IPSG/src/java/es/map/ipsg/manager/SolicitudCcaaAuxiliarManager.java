package es.map.ipsg.manager;

import java.util.ArrayList;

import es.map.ips.model.SolicitudCcaaAuxiliarQuery;
import es.map.ipsg.bean.SolicitudCcaaAuxiliarBean;
import es.map.ipsg.bean.SolicitudCcaaBean;

/**
 * El Interface SolicitudCcaaAuxiliarManager.
 */
public interface SolicitudCcaaAuxiliarManager {

	/**
	 * Obtener solicitud ccaa auxiliar by id solicitud.
	 *
	 * @param solicitudCcaaAuxiliarQuery el solicitud ccaa auxiliar query
	 * @return el solicitud ccaa auxiliar bean
	 */
	public SolicitudCcaaAuxiliarBean obtenerSolicitudCcaaAuxiliarByIdSolicitud (SolicitudCcaaAuxiliarQuery solicitudCcaaAuxiliarQuery);
	
	/**
	 * Obtener solicitud ccaa aux by id.
	 *
	 * @param idSolicitudAux el id solicitud aux
	 * @return el solicitud ccaa auxiliar bean
	 */
	public SolicitudCcaaAuxiliarBean obtenerSolicitudCcaaAuxById (Long idSolicitudAux);
	
	/**
	 * Borrar solicitud ccaa auxiliar.
	 *
	 * @param idSolicitud el id solicitud
	 */
	public void borrarSolicitudCcaaAuxiliar(Long idSolicitud);
	
	/**
	 * Buscar solicitud all.
	 *
	 * @param solicitudQuery el solicitud query
	 * @return el array list
	 */
	public ArrayList<SolicitudCcaaBean> buscarSolicitudAll(SolicitudCcaaAuxiliarQuery solicitudQuery);
}
