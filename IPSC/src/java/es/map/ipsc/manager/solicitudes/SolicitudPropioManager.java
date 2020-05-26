package es.map.ipsc.manager.solicitudes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import es.map.ips.model.SolicitudPropiosQuery;
import es.map.ipsc.bean.PlantillaPropiosBean;
import es.map.ipsc.bean.SolicitudPropiosBean;

/**
 * El Interface SolicitudPropioManager.
 */
public interface SolicitudPropioManager {
	
	
	/**
	 * Guardar campos solicitud.
	 *
	 * @param plantillas el plantillas
	 * @param idSolicitud el id solicitud
	 * @return el boolean
	 */
	public Boolean guardarCamposSolicitud(HashSet<PlantillaPropiosBean> plantillas, Long idSolicitud);
	
	/**
	 * Obtener campos propios by id solicitud.
	 *
	 * @param solicitudPropiosQuery el solicitud propios query
	 * @return el array list
	 */
	public ArrayList<SolicitudPropiosBean> obtenerCamposPropiosByIdSolicitud(SolicitudPropiosQuery solicitudPropiosQuery);
	
	/**
	 * Guardar campos solicitud modif.
	 *
	 * @param plantillas el plantillas
	 * @param idSolicitud el id solicitud
	 * @return el boolean
	 */
	public Boolean guardarCamposSolicitudModif(HashSet<PlantillaPropiosBean> plantillas, Long idSolicitud);
	
}
