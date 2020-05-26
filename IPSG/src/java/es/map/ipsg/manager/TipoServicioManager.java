package es.map.ipsg.manager;

import java.util.ArrayList;

import es.map.ips.model.TipoServicio;
import es.map.ips.model.TipoServicioQuery;
import es.map.ipsg.bean.TipoServicioBean;




/**
 * TipoServicioManager.
 *
 * @author Djimenezg
 */
public interface TipoServicioManager {
	
	/**
	 * Buscar tipo servicio combo.
	 *
	 * @param tipoServicioQuery el tipo servicio query
	 * @return el array list
	 */
	public ArrayList<TipoServicioBean> buscarTipoServicioCombo(TipoServicioQuery tipoServicioQuery);
	
	/**
	 * Buscar tipo servicio all.
	 *
	 * @param tipoServicioQuery el tipo servicio query
	 * @return el array list
	 */
	public ArrayList<TipoServicioBean> buscarTipoServicioAll(TipoServicioQuery tipoServicioQuery);
	
	/**
	 * Obtener tipo servicio bean.
	 *
	 * @param idTipoServicio el id tipo servicio
	 * @return el tipo servicio bean
	 */
	public TipoServicioBean obtenerTipoServicioBean (Integer idTipoServicio);
	
	/**
	 * Obtener tipo servicio.
	 *
	 * @param idTipoServicio el id tipo servicio
	 * @return el tipo servicio
	 */
	public TipoServicio obtenerTipoServicio (Integer idTipoServicio);
}
