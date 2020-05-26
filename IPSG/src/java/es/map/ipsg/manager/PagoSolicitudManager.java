package es.map.ipsg.manager;

import java.util.ArrayList;

import es.map.ips.model.PagoSolicitud;
import es.map.ips.model.PagoSolicitudQuery;
import es.map.ipsg.bean.PagoSolicitudBean;
import es.map.ipsg.bean.SolicitudBean;




/**
 * PagoSolicitudManager.
 *
 * @author amartinl
 */
public interface PagoSolicitudManager {
	
	/**
	 * Completa datos pago solicitud.
	 *
	 * @param pagoSolicitudQuery el pago solicitud query
	 * @param solicitudBean el solicitud bean
	 * @return el solicitud bean
	 * @throws Exception el exception
	 */
	public SolicitudBean completaDatosPagoSolicitud (PagoSolicitudQuery pagoSolicitudQuery, SolicitudBean solicitudBean) throws Exception;
	
	/**
	 * Buscar pago solicitud all.
	 *
	 * @param pagoSolicitudQuery el pago solicitud query
	 * @return el array list
	 */
	public ArrayList<PagoSolicitudBean> buscarPagoSolicitudAll(PagoSolicitudQuery pagoSolicitudQuery);
	
	/**
	 * Guardar pago solicitud bean.
	 *
	 * @param pagoSolicitudBean el pago solicitud bean
	 * @return el integer
	 */
	public Integer guardarPagoSolicitudBean(PagoSolicitudBean pagoSolicitudBean);
	
	/**
	 * Guardar pago solicitud.
	 *
	 * @param pagoSolicitudBean el pago solicitud bean
	 */
	public void guardarPagoSolicitud(PagoSolicitudBean pagoSolicitudBean);
	
	/**
	 * Almacenar pago solicitud.
	 *
	 * @param pagoSolicitud el pago solicitud
	 */
	public void almacenarPagoSolicitud(PagoSolicitud pagoSolicitud);
	
	/**
	 * Modificar pago solicitud bean.
	 *
	 * @param pagoSolicitudBean el pago solicitud bean
	 */
	public void modificarPagoSolicitudBean(PagoSolicitudBean pagoSolicitudBean);
	
	/**
	 * Eliminar pago solicitud.
	 *
	 * @param id el id
	 */
	public void eliminarPagoSolicitud(Long id);
	
	/**
	 * Buscar pago solicitud by id solicitud.
	 *
	 * @param pagoSolicitudQuery el pago solicitud query
	 * @return el pago solicitud bean
	 */
	public PagoSolicitudBean buscarPagoSolicitudByIdSolicitud(PagoSolicitudQuery pagoSolicitudQuery);
	
	/**
	 * Buscar ultimo pago solicitud by id solicitud.
	 *
	 * @param pagoSolicitudQuery el pago solicitud query
	 * @return el pago solicitud bean
	 */
	public PagoSolicitudBean buscarUltimoPagoSolicitudByIdSolicitud(PagoSolicitudQuery pagoSolicitudQuery);
	
	/**
	 * Guardar pago solicitud consulta.
	 *
	 * @param pagoSolicitudBean el pago solicitud bean
	 */
	public void guardarPagoSolicitudConsulta(PagoSolicitudBean pagoSolicitudBean);
	
	/**
	 * Buscar ultimo pago solicitud id solicitu OK.
	 *
	 * @param pagoSolicitudQuery el pago solicitud query
	 * @return el pago solicitud bean
	 */
	PagoSolicitudBean buscarUltimoPagoSolicitudIdSolicituOK(PagoSolicitudQuery pagoSolicitudQuery);
	
	/**
	 * Buscar pago solicitud id solicitu pruebas.
	 *
	 * @param pagoSolicitudQuery el pago solicitud query
	 * @return el pago solicitud bean
	 */
	PagoSolicitudBean buscarPagoSolicitudIdSolicituPruebas(PagoSolicitudQuery pagoSolicitudQuery);
	
	/**
	 * Borrar solicitud pago.
	 *
	 * @param idSolicitud el id solicitud
	 */
	public void borrarSolicitudPago(Long idSolicitud);
}
