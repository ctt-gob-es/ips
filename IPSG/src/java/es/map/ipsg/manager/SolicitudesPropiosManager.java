package es.map.ipsg.manager;

import java.util.ArrayList;

import es.map.ips.model.SolicitudPropiosQuery;
import es.map.ipsg.bean.SolicitudPropiosBean;

/**
 * El Interface SolicitudesPropiosManager.
 */
public interface SolicitudesPropiosManager {

	/**
	 * Método que obtiene los campos propios asociados a una solicitud.
	 *
	 * @param solicitudPropiosQuery el solicitud propios query
	 * @return el array list
	 */
	public ArrayList<SolicitudPropiosBean> obtenerCamposPropiosByIdSolicitud(SolicitudPropiosQuery solicitudPropiosQuery);
	
	/**
	 * Guardar solicitud propios bean.
	 *
	 * @param solicitudPropiosBean el solicitud propios bean
	 * @return el integer
	 */
	public Integer guardarSolicitudPropiosBean(SolicitudPropiosBean solicitudPropiosBean);
	
	/**
	 * Modificar solicitud propios bean.
	 *
	 * @param solicitudPropiosBean el solicitud propios bean
	 */
	public void modificarSolicitudPropiosBean(SolicitudPropiosBean solicitudPropiosBean);
	
	/**
	 * Borrar solicitud propio.
	 *
	 * @param idSolicitud el id solicitud
	 */
	public void borrarSolicitudPropio(Long idSolicitud);
}
