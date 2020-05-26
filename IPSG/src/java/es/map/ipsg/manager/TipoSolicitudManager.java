package es.map.ipsg.manager;

import java.util.ArrayList;

import es.map.ips.model.TipoSolicitud;
import es.map.ips.model.TipoSolicitudQuery;
import es.map.ipsg.bean.TipoSolicitudBean;




/**
 * TipoSolicitudManager.
 *
 * @author amartinl
 */
public interface TipoSolicitudManager {
	
	/**
	 * Buscar tipo solicitud combo.
	 *
	 * @param tipoSolicitudQuery el tipo solicitud query
	 * @return el array list
	 */
	public ArrayList<TipoSolicitudBean> buscarTipoSolicitudCombo(TipoSolicitudQuery tipoSolicitudQuery);
	
	/**
	 * Buscar tipo solicitud all.
	 *
	 * @param tipoSolicitudQuery el tipo solicitud query
	 * @return el array list
	 */
	public ArrayList<TipoSolicitudBean> buscarTipoSolicitudAll(TipoSolicitudQuery tipoSolicitudQuery);
	
	/**
	 * Obtener tipo solicitud.
	 *
	 * @param idTipoSolicitud el id tipo solicitud
	 * @return el tipo solicitud bean
	 */
	public TipoSolicitudBean obtenerTipoSolicitud(Integer idTipoSolicitud);
	
	/**
	 * Conseguir tipo solicitud.
	 *
	 * @param idTipoSolicitud el id tipo solicitud
	 * @return el tipo solicitud
	 */
	public TipoSolicitud conseguirTipoSolicitud(Integer idTipoSolicitud);
}
