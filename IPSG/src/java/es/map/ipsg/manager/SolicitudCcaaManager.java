package es.map.ipsg.manager;

import java.util.ArrayList;

import es.map.ips.model.SolicitudCcaa;
import es.map.ips.model.SolicitudCcaaQuery;
import es.map.ipsg.bean.SolicitudCcaaBean;

/**
 * El Interface SolicitudCcaaManager.
 */
public interface SolicitudCcaaManager {

	/**
	 * Obtener solicitud ccaa by id solicitud.
	 *
	 * @param solicitudCcaaQuery el solicitud ccaa query
	 * @return el solicitud ccaa bean
	 */
	public SolicitudCcaaBean obtenerSolicitudCcaaByIdSolicitud (SolicitudCcaaQuery solicitudCcaaQuery);
	
	/**
	 * Guardar solicitud ccaa.
	 *
	 * @param solicitudCcaaBean el solicitud ccaa bean
	 */
	public void guardarSolicitudCcaa(SolicitudCcaaBean solicitudCcaaBean);
	
	/**
	 * Almacenar solicitud ccaa.
	 *
	 * @param solicitudCcaa el solicitud ccaa
	 */
	public void almacenarSolicitudCcaa(SolicitudCcaa solicitudCcaa);
	
	/**
	 * Buscar solicitud ccaa id solicitud.
	 *
	 * @param solicitudCcaaQuery el solicitud ccaa query
	 * @return el solicitud ccaa bean
	 */
	public SolicitudCcaaBean buscarSolicitudCcaaIdSolicitud(SolicitudCcaaQuery solicitudCcaaQuery);
	
	/**
	 * Modificar solicitud ccaa bean.
	 *
	 * @param solicitudCcaaBean el solicitud ccaa bean
	 */
	public void modificarSolicitudCcaaBean(SolicitudCcaaBean solicitudCcaaBean);
	
	/**
	 * Buscar solicitud all.
	 *
	 * @param SolicitudQuery el solicitud query
	 * @return el array list
	 */
	public ArrayList<SolicitudCcaaBean> buscarSolicitudAll(SolicitudCcaaQuery SolicitudQuery);
	
	/**
	 * Borrar solicitud ccaa.
	 *
	 * @param idSolicitud el id solicitud
	 */
	public void borrarSolicitudCcaa(Long idSolicitud);
}
