package es.map.ipsg.manager;

import java.util.ArrayList;

import es.map.ips.model.TipoAlertaQuery;
import es.map.ipsg.bean.TipoAlertaBean;




/**
 * El Interface TipoAlertaManager.
 *
 * @author djimenezg
 */
public interface TipoAlertaManager {
	
	/**
	 * Buscar tipo alerta all.
	 *
	 * @param tipoAlertaQuery el tipo alerta query
	 * @return el array list
	 */
	public ArrayList<TipoAlertaBean> buscarTipoAlertaAll(TipoAlertaQuery tipoAlertaQuery);
	
	/**
	 * Buscar tipo alerta combo.
	 *
	 * @param tipoAlertaQuery el tipo alerta query
	 * @return el array list
	 */
	public ArrayList<TipoAlertaBean> buscarTipoAlertaCombo(TipoAlertaQuery tipoAlertaQuery);
	
	/**
	 * Buscar tipo alerta.
	 *
	 * @param tipoAlertaQuery el tipo alerta query
	 * @return el tipo alerta bean
	 */
	TipoAlertaBean buscarTipoAlerta(TipoAlertaQuery tipoAlertaQuery);
	
	/**
	 * Obtener tipo alerta.
	 *
	 * @param idTipoAlerta el id tipo alerta
	 * @return el tipo alerta query
	 */
	public TipoAlertaQuery obtenerTipoAlerta(Integer idTipoAlerta);
}
