package es.map.ipsg.manager;

import java.util.ArrayList;

import es.map.ips.model.SolicitudPropiosAuxiliarQuery;
import es.map.ipsg.bean.CamposPropiosBean;
import es.map.ipsg.bean.SolicitudPropiosAuxiliarBean;

/**
 * El Interface SolicitudPropioAuxiliarManager.
 */
public interface SolicitudPropioAuxiliarManager {
	
	/**
	 * Obtener campos propios auxiliar by id solicitud.
	 *
	 * @param solicitudPropiosAuxiliarQuery el solicitud propios auxiliar query
	 * @return el array list
	 */
	public ArrayList<CamposPropiosBean> obtenerCamposPropiosAuxiliarByIdSolicitud(SolicitudPropiosAuxiliarQuery solicitudPropiosAuxiliarQuery);
	
	/**
	 * Borrar solicitud propio auxiliar.
	 *
	 * @param idSolicitud el id solicitud
	 */
	/*
	 * Eliminamos las solicitudes que contienen el id de Solicitude de la tabla Solicitud Comun Auxiliar
	 */
	public void borrarSolicitudPropioAuxiliar(Long idSolicitud);
	
	/**
	 * Obtener campos propios aux by id solicitud.
	 *
	 * @param solicitudPropiosAuxiliarQuery el solicitud propios auxiliar query
	 * @return el array list
	 */
	public ArrayList<SolicitudPropiosAuxiliarBean> obtenerCamposPropiosAuxByIdSolicitud(
			SolicitudPropiosAuxiliarQuery solicitudPropiosAuxiliarQuery);

}
