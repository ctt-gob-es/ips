package es.map.ipsc.manager.solicitudes;

import java.util.ArrayList;
import java.util.List;
import es.map.ips.model.EstadoSolicitudQuery;
import es.map.ips.model.SolComunViewQuery;
import es.map.ips.model.SolicitudComunQuery;
import es.map.ipsc.bean.BuscarSolicitudesBean;
import es.map.ipsc.bean.CiudadanoBean;
import es.map.ipsc.bean.DetalleSolicitudBean;
import es.map.ipsc.bean.SolicitudBean;

/**
 * El Interface SolicitudManager.
 */
public interface SolicitudManager {

	/**
	 * Obtener numero solicitud.
	 *
	 * @param is007 el is 007
	 * @return el string
	 */
	String obtenerNumeroSolicitud(boolean is007);

	/**
	 * Detalle solicitud.
	 *
	 * @param solicitudQuery el solicitud query
	 * @return el detalle solicitud bean
	 */
	DetalleSolicitudBean detalleSolicitud(SolicitudComunQuery solicitudQuery);

	/**
	 * Buscar solicitudes all.
	 *
	 * @param solicitudQuery el solicitud query
	 * @return el array list
	 */
	ArrayList<BuscarSolicitudesBean> buscarSolicitudesAll(
			SolComunViewQuery solicitudQuery);
	
	/**
	 * Buscar solicitudes all.
	 *
	 * @param solicitudQuery el solicitud query
	 * @return el array list
	 */
	ArrayList<BuscarSolicitudesBean> buscarSolicitudesAll(
			SolicitudComunQuery solicitudQuery);

	/**
	 * Actualizar estado solicitud.
	 *
	 * @param solicitudQuery el solicitud query
	 * @param estadoSolicitudQuery el estado solicitud query
	 * @return el long
	 */
	Long actualizarEstadoSolicitud(SolicitudComunQuery solicitudQuery, EstadoSolicitudQuery estadoSolicitudQuery);

	/**
	 * Buscar ultima solicitud.
	 *
	 * @param ciudadanoBean el ciudadano bean
	 * @return el solicitud bean
	 */
	SolicitudBean buscarUltimaSolicitud(CiudadanoBean ciudadanoBean);
	
	/**
	 * Buscar ultima solicitud 2.
	 *
	 * @param ciudadanoBean el ciudadano bean
	 * @return el solicitud bean
	 */
	SolicitudBean buscarUltimaSolicitud2(CiudadanoBean ciudadanoBean);

	/**
	 * Guardar solicitud.
	 *
	 * @param solicitudBean el solicitud bean
	 * @return el long
	 */
	Long guardarSolicitud(SolicitudBean solicitudBean);

	/**
	 * Buscar solicitud by id.
	 *
	 * @param solicitudQuery el solicitud query
	 * @return el solicitud bean
	 */
	SolicitudBean buscarSolicitudById(SolicitudComunQuery solicitudQuery);

	/**
	 * Buscar registro solicitud.
	 *
	 * @param solicitudQuery el solicitud query
	 * @return el solicitud bean
	 */
	SolicitudBean buscarRegistroSolicitud(SolicitudComunQuery solicitudQuery);

	/**
	 * Buscar solicitudes cerradas all.
	 *
	 * @param solicitudQuery el solicitud query
	 * @return el list
	 */
	List<BuscarSolicitudesBean> buscarSolicitudesCerradasAll(
			SolComunViewQuery solicitudQuery);

	/**
	 * Obtener numero solicitud.
	 *
	 * @param is007 el is 007
	 * @param modelo el modelo
	 * @return el string
	 */
	String obtenerNumeroSolicitud(boolean is007, String modelo);

	/**
	 * Existe numero solicitud.
	 *
	 * @param nSolicitud el n solicitud
	 * @return verdadero, si tiene exito
	 */
	boolean existeNumeroSolicitud(String nSolicitud);

}
