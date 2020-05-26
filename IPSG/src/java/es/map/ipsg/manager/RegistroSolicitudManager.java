package es.map.ipsg.manager;

import java.util.ArrayList;

import es.map.ips.model.RegistroSolicitud;
import es.map.ips.model.RegistroSolicitudQuery;
import es.map.ipsg.bean.RegistroSolicitudBean;

/**
 * RegistroSolicitudManager.
 *
 * @author amartinl
 */
public interface RegistroSolicitudManager {
	
	/**
	 * Buscar registro solicitud all.
	 *
	 * @param registroSolicitudQuery el registro solicitud query
	 * @return el array list
	 */
	public ArrayList<RegistroSolicitudBean> buscarRegistroSolicitudAll(RegistroSolicitudQuery registroSolicitudQuery);
	
	/**
	 * Guardar registro solicitud.
	 *
	 * @param registroSolicitudBean el registro solicitud bean
	 * @return el integer
	 */
	public Integer guardarRegistroSolicitud(RegistroSolicitudBean registroSolicitudBean);
	
	/**
	 * Almacenar registro solicitud.
	 *
	 * @param registroSolicitud el registro solicitud
	 */
	public void almacenarRegistroSolicitud(RegistroSolicitud registroSolicitud);
	
	/**
	 * Modificar registro solicitud.
	 *
	 * @param registroSolicitudBean el registro solicitud bean
	 */
	public void modificarRegistroSolicitud(RegistroSolicitudBean registroSolicitudBean);
	
	/**
	 * Eliminar registro solicitud.
	 *
	 * @param id el id
	 */
	public void eliminarRegistroSolicitud(Long id);
	
	/**
	 * Buscar registro solicitud by id solicitud.
	 *
	 * @param registroSolicitudQuery el registro solicitud query
	 * @return el registro solicitud bean
	 */
	public RegistroSolicitudBean buscarRegistroSolicitudByIdSolicitud(RegistroSolicitudQuery registroSolicitudQuery);
	
	/**
	 * Buscar registro solicitud by id solicitud crear justificante.
	 *
	 * @param registroSolicitudQuery el registro solicitud query
	 * @return el registro solicitud bean
	 */
	public RegistroSolicitudBean buscarRegistroSolicitudByIdSolicitudCrearJustificante(RegistroSolicitudQuery registroSolicitudQuery);
	
	/**
	 * Borrar solicitud registro.
	 *
	 * @param idSolicitud el id solicitud
	 */
	public void borrarSolicitudRegistro(Long idSolicitud);
}
