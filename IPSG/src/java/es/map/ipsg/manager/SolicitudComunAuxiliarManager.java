package es.map.ipsg.manager;

import java.util.ArrayList;
import java.util.List;

import es.map.ips.model.SolComunAuxiliarViewQuery;
import es.map.ips.model.SolicitudComun;
import es.map.ips.model.SolicitudComunAuxiliar;
import es.map.ips.model.SolicitudComunAuxiliarQuery;
import es.map.ipsg.bean.SolicitudBean;
import es.map.ipsg.bean.SolicitudCcaaBean;
import es.map.ipsg.bean.SolicitudComunAuxiliarBean;

/**
 * El Interface SolicitudComunAuxiliarManager.
 */
public interface SolicitudComunAuxiliarManager {
	
	/**
	 * Buscar solicitud comun auxiliar by id.
	 *
	 * @param solicitudComunAuxiliarQuery el solicitud comun auxiliar query
	 * @return el solicitud comun auxiliar bean
	 */
	public SolicitudComunAuxiliarBean buscarSolicitudComunAuxiliarById(SolicitudComunAuxiliarQuery solicitudComunAuxiliarQuery);
	
	/**
	 * Buscar solicitud comun auxiliar by fecha solicitud.
	 *
	 * @param solicitudComunAuxiliarQuery el solicitud comun auxiliar query
	 * @return el array list
	 */
	public ArrayList<Long> buscarSolicitudComunAuxiliarByFechaSolicitud(SolicitudComunAuxiliarQuery solicitudComunAuxiliarQuery);
	
	/**
	 * Buscar solicitudes auxiliar vista.
	 *
	 * @param solicitudQuery el solicitud query
	 * @return el array list
	 */
	public ArrayList<SolicitudBean> buscarSolicitudesAuxiliarVista (SolComunAuxiliarViewQuery solicitudQuery);
	
	/**
	 * Obtener solicitud.
	 *
	 * @param idSolicitud el id solicitud
	 * @return el solicitud bean
	 */
	public SolicitudBean obtenerSolicitud (Long idSolicitud);
	
	/**
	 * Obtener solicitud.
	 *
	 * @param idSolicitud el id solicitud
	 * @return el solicitud bean
	 */
	public SolicitudBean obtenerSolicitudQuery (SolicitudComunAuxiliarQuery solicitudComunAuxiliarQuery);
	
	/**
	 * Borrar solicitud comun auxiliar.
	 *
	 * @param idSolicitud el id solicitud
	 */
	/*
	 * Eliminamos las solicitudes que contienen el id de Solicitud de la tabla Solicitud Comun Auxiliar
	 */
	public void borrarSolicitudComunAuxiliar(Long idSolicitud);
	
	/**
	 * Buscar solicitudes.
	 *
	 * @param solicitudes el solicitudes
	 * @return el list
	 */
	public List<SolicitudBean> buscarSolicitudes(List<SolicitudCcaaBean> solicitudes);
	
	/**
	 * Buscar solicitudes all.
	 *
	 * @param solicitudComunQuery el solicitud comun query
	 * @return el list
	 */
	public List<SolicitudBean> buscarSolicitudesAll(SolicitudComunAuxiliarQuery solicitudComunQuery);
	
	/**
	 * modificarSolicitud
	 * 
	 * @param solicitudBean
	 */
	public void modificarSolicitud (SolicitudBean  solicitudBean);

	/**
	 * 
	 * convertir solicitud comun a solicitud comun auxiliar
	 * 
	 * @param solicitudBean
	 * @return
	 */
	public SolicitudComun toSolicitudComun(SolicitudBean solicitudBean);
	
	/**
	 * 
	 * 
	 * @param solicitudBean
	 * @return
	 */
	public SolicitudComunAuxiliar toSolicitud (SolicitudBean  solicitudBean);
}
