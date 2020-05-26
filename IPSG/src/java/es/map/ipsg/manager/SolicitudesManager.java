package es.map.ipsg.manager;

import java.util.ArrayList;
import java.util.Date;

import es.map.ips.model.EstadoSolicitud;
import es.map.ips.model.EstadoSolicitudQuery;
import es.map.ips.model.Modelo;
import es.map.ips.model.SolicitudComun;
import es.map.ips.model.SolicitudComunQuery;
import es.map.ips.model.TipoSolicitud;
import es.map.ipsg.bean.DetalleRegistroPagoBean;
import es.map.ipsg.bean.DetalleSolicitudBean;
import es.map.ipsg.bean.SolicitudBean;
import es.map.ipsg.bean.SolicitudComunAuxiliarBean;




/**
 * SolicitudManager.
 *
 * @author amartinl
 */
public interface SolicitudesManager {
	
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
	 * Insertar solicitud comun auxiliar.
	 *
	 * @param solicitudComunAuxiliarBean el solicitud comun auxiliar bean
	 * @param estadoSolicitud el estado solicitud
	 * @param tipoSolicitud el tipo solicitud
	 * @param dateRegistroSolicitud el date registro solicitud
	 * @param modelo el modelo
	 * @param isMod el is mod
	 * @return el solicitud comun
	 */
	public SolicitudComun insertarSolicitudComunAuxiliar(SolicitudComunAuxiliarBean solicitudComunAuxiliarBean,EstadoSolicitud estadoSolicitud,TipoSolicitud tipoSolicitud,Date dateRegistroSolicitud,Modelo modelo, boolean isMod );
	
	/**
	 * Modificar solicitud.
	 *
	 * @param solicitudBean el solicitud bean
	 */
	public void modificarSolicitud (SolicitudBean  solicitudBean);
	
	/**
	 * Buscar solicitud.
	 *
	 * @param idConvocatoria el id convocatoria
	 * @return el array list
	 */
	public ArrayList<SolicitudBean> buscarSolicitud (Long idConvocatoria);
	
	/**
	 * Buscar solicitud by id.
	 *
	 * @param solicitudQuery el solicitud query
	 * @return el solicitud bean
	 */
	public SolicitudBean buscarSolicitudById(SolicitudComunQuery solicitudQuery);
	
	/**
	 * Actualizar estado solicitud.
	 *
	 * @param solicitudBeanAux el solicitud bean aux
	 * @param i el i
	 */
	public void actualizarEstadoSolicitud(SolicitudBean solicitudBeanAux, int i);
	
	/**
	 * Actualizar estado solicitud registro.
	 *
	 * @param solicitudQuery el solicitud query
	 * @param estadoSolicitudQuery el estado solicitud query
	 * @return el long
	 */
	public Long actualizarEstadoSolicitudRegistro(SolicitudComunQuery solicitudQuery,EstadoSolicitudQuery estadoSolicitudQuery);
	
	/**
	 * Obtener numero solicitud.
	 *
	 * @return el string
	 */
	public String obtenerNumeroSolicitud();
	
	/**
	 * Buscar log por numero solicitud Y nif.
	 *
	 * @param nSolicitud el n solicitud
	 * @param nif el nif
	 * @return el array list
	 */
	public ArrayList<Integer> buscarLogPorNumeroSolicitudYNif (String nSolicitud, String nif);
	
	/**
	 * Obtener detalle registro pago.
	 *
	 * @param idSolicitud el id solicitud
	 * @return el detalle registro pago bean
	 */
	public DetalleRegistroPagoBean obtenerDetalleRegistroPago(Long idSolicitud);
	
	/**
	 * Detalle solicitud.
	 *
	 * @param solicitudQuery el solicitud query
	 * @return el detalle solicitud bean
	 */
	public DetalleSolicitudBean detalleSolicitud(SolicitudComunQuery solicitudQuery);
	
	/**
	 * Obtener numero solicitud.
	 *
	 * @param modelo el modelo
	 * @return el string
	 */
	public String obtenerNumeroSolicitud(String modelo);
	
	/**
	 * To solicitud.
	 *
	 * @param solicitudBean el solicitud bean
	 * @return el solicitud comun
	 */
	public SolicitudComun toSolicitud(SolicitudBean solicitudBean);
	
	public SolicitudBean toSolicitudBean(SolicitudComun solicitudComun, int numTotal);

	/**
	 * Buscar solicitud comun by fecha solicitud.
	 *
	 * @param solicitudComunQuery el solicitud comun query
	 * @return el array list
	 */
	public ArrayList<Long> buscarSolicitudComunByFechaSolicitud(SolicitudComunQuery solicitudComunQuery);
	

	/**
	 * Borrar solicitud comun.
	 *
	 * @param idSolicitud el id solicitud
	 */
	public void borrarSolicitudComun(Long idSolicitud);
}
