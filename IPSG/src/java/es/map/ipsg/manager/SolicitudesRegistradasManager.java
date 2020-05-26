package es.map.ipsg.manager;

import java.util.ArrayList;
import java.util.List;

import es.map.ips.model.SolComunClotesViewQuery;
import es.map.ips.model.SolComunIncidenciasViewQuery;
import es.map.ips.model.SolComunPresencialesViewQuery;
import es.map.ips.model.SolComunRegistradasViewQuery;
import es.map.ips.model.SolicitudComun;
import es.map.ips.model.SolicitudComunQuery;
import es.map.ipsg.bean.SolicitudBean;
import es.map.ipsg.bean.SolicitudCcaaBean;
import es.map.ipsg.bean.SolicitudXmlSigpBean;




/**
 * SolicitudManager.
 *
 * @author amartinl
 */
public interface SolicitudesRegistradasManager {
	
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
	 * Buscar solicitud xml sigp all.
	 *
	 * @param SolicitudQuery el solicitud query
	 * @return el array list
	 */
	public ArrayList<SolicitudXmlSigpBean> buscarSolicitudXmlSigpAll(SolicitudComunQuery SolicitudQuery);
	
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
	 * Eliminar solicitud.
	 *
	 * @param id el id
	 */
	public void eliminarSolicitud(Long id);
	
	/**
	 * To solicitud.
	 *
	 * @param solicitudBean el solicitud bean
	 * @return el solicitud comun
	 */
	public SolicitudComun toSolicitud (SolicitudBean  solicitudBean);
	
	/**
	 * Buscar solicitudes incidencias vista.
	 *
	 * @param solicitudQuery el solicitud query
	 * @return el array list
	 */
	public ArrayList<SolicitudBean> buscarSolicitudesIncidenciasVista (SolComunIncidenciasViewQuery solicitudQuery);
	
	/**
	 * Buscar solicitudes registradas vista.
	 *
	 * @param solicitudQuery el solicitud query
	 * @return el array list
	 */
	public ArrayList<SolicitudBean> buscarSolicitudesRegistradasVista (SolComunRegistradasViewQuery solicitudQuery);
	
	/**
	 * Buscar solicitudes presenciales vista.
	 *
	 * @param solicitudQuery el solicitud query
	 * @return el array list
	 */
	public ArrayList<SolicitudBean> buscarSolicitudesPresencialesVista (SolComunPresencialesViewQuery solicitudQuery);
	
	/**
	 * Buscar solicitudes clotes vista.
	 *
	 * @param solicitudQuery el solicitud query
	 * @return el array list
	 */
	public ArrayList<SolicitudBean> buscarSolicitudesClotesVista (SolComunClotesViewQuery solicitudQuery);
	
	/**
	 * Buscar solicitud xml sigp vista.
	 *
	 * @param solicitudQuery el solicitud query
	 * @return el array list
	 */
	public ArrayList<SolicitudXmlSigpBean> buscarSolicitudXmlSigpVista(SolComunRegistradasViewQuery solicitudQuery);
	
	/**
	 * Buscar solicitudes.
	 *
	 * @param solicitudes el solicitudes
	 * @return el list
	 */
	public List<SolicitudBean> buscarSolicitudes(List<SolicitudCcaaBean> solicitudes);
}
