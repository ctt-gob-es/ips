package es.map.ipsc.manager.solicitudes;

import es.map.ips.model.RegistroSolicitudQuery;
import es.map.ipsc.bean.DetalleRegistroSolicitudBean;
import es.map.ipsc.bean.RegistroSolicitudBean;

/**
 * El Interface RegistroSolicitudManager.
 */
public interface RegistroSolicitudManager {

	/**
	 * Buscar registro solicitud id solicitud.
	 *
	 * @param registroSolicitudQuery el registro solicitud query
	 * @return el detalle registro solicitud bean
	 */
	DetalleRegistroSolicitudBean buscarRegistroSolicitudIdSolicitud(RegistroSolicitudQuery registroSolicitudQuery);

	/**
	 * Guardar registro.
	 *
	 * @param registroSolicitudBean el registro solicitud bean
	 * @return el long
	 */
	Long guardarRegistro(RegistroSolicitudBean registroSolicitudBean);

	/**
	 * Buscar registros duplicados.
	 *
	 * @param registroSolicitudQuery el registro solicitud query
	 * @return el integer
	 */
	Integer buscarRegistrosDuplicados (RegistroSolicitudQuery registroSolicitudQuery);
	
	/**
	 * Metodo que retorna si una solicitud tiene como minimo un registro en 
	 * la tabla REGISTRO_SOLICITUD, indiferentemente si es OK o ER.
	 *
	 * @param registroSolicitudQuery el registro solicitud query
	 * @return Boolean
	 */
	Boolean buscarRegistro (RegistroSolicitudQuery registroSolicitudQuery);
	
}
