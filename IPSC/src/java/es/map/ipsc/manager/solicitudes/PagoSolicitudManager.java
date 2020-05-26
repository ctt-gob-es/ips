package es.map.ipsc.manager.solicitudes;

import java.util.ArrayList;

import es.map.ips.model.PagoSolicitud;
import es.map.ips.model.PagoSolicitudQuery;
import es.map.ipsc.bean.DetallePagoSolicitudBean;
import es.map.ipsc.bean.PagoSolicitudBean;

/**
 * El Interface PagoSolicitudManager.
 */
public interface PagoSolicitudManager {

	/**
	 * Buscar pago solicitud.
	 *
	 * @param pagoSolicitudQuery el pago solicitud query
	 * @return el array list
	 */
	ArrayList<PagoSolicitudBean> buscarPagoSolicitud(PagoSolicitudQuery pagoSolicitudQuery);
	
	/**
	 * Buscar detalle pago solicitud.
	 *
	 * @param pagoSolicitudQuery el pago solicitud query
	 * @return el array list
	 */
	ArrayList<DetallePagoSolicitudBean> buscarDetallePagoSolicitud(PagoSolicitudQuery pagoSolicitudQuery);
	
	/**
	 * Metodo que retorna el un pago con la entidad como tipo
	 * la tabla PAGO_SOLICITUD, indiferentemente si es OK o ER.
	 *
	 * @param pagoSolicitudQuery el pago solicitud query
	 * @return PagoSolicitud
	 */
	ArrayList<PagoSolicitud> buscarPagoSol(PagoSolicitudQuery pagoSolicitudQuery);

	/**
	 * Buscar pago solicitud id solicitu.
	 *
	 * @param pagoSolicitudQuery el pago solicitud query
	 * @return el detalle pago solicitud bean
	 */
	DetallePagoSolicitudBean buscarPagoSolicitudIdSolicitu(PagoSolicitudQuery pagoSolicitudQuery);
	
	/**
	 * Buscar pago solicitud id solicitu pruebas.
	 *
	 * @param pagoSolicitudQuery el pago solicitud query
	 * @return el detalle pago solicitud bean
	 */
	DetallePagoSolicitudBean buscarPagoSolicitudIdSolicituPruebas(PagoSolicitudQuery pagoSolicitudQuery);
	
	/**
	 * Metodo que retorna el ultimo pago insertado de una solicitud
	 * la tabla PAGO_SOLICITUD, indiferentemente si es OK o ER.
	 *
	 * @param pagoSolicitudQuery el pago solicitud query
	 * @return DetallePagoSolicitudBean
	 */
	
	DetallePagoSolicitudBean buscarUltimoPagoSolicitudIdSolicitud(PagoSolicitudQuery pagoSolicitudQuery);
	
	/**
	 * Buscar ultimo pago solicitud id solicitu OK.
	 *
	 * @param pagoSolicitudQuery el pago solicitud query
	 * @return el detalle pago solicitud bean
	 */
	DetallePagoSolicitudBean buscarUltimoPagoSolicitudIdSolicituOK(PagoSolicitudQuery pagoSolicitudQuery);

	/**
	 * Guardar pago solicitud.
	 *
	 * @param pagoSolicitudBean el pago solicitud bean
	 */
	void guardarPagoSolicitud(PagoSolicitudBean pagoSolicitudBean);
	
	/**
	 * Guardar pago solicitud modif.
	 *
	 * @param pagoSolicitudBean el pago solicitud bean
	 */
	void guardarPagoSolicitudModif(PagoSolicitudBean pagoSolicitudBean);

	/**
	 * Actualizar estado solicitud.
	 *
	 * @param pagoSolicitudActualizar el pago solicitud actualizar
	 */
	void actualizarEstadoSolicitud(PagoSolicitudBean pagoSolicitudActualizar);
	
	/**
	 * Metodo que retorna si una solicitud tiene como minimo un registro en 
	 * la tabla PAGO_SOLICITUD, indiferentemente si es OK o ER.
	 *
	 * @param pagoSolicitudQuery el pago solicitud query
	 * @return Boolean
	 */
	Boolean buscarPago(PagoSolicitudQuery pagoSolicitudQuery);

}
