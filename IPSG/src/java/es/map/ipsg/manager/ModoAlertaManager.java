package es.map.ipsg.manager;

import java.util.ArrayList;

import es.map.ips.model.ModoAlertaQuery;
import es.map.ipsg.bean.ModoAlertaBean;




/**
 * El Interface ModoAlertaManager.
 *
 * @author amartinl
 */
public interface ModoAlertaManager {
	
	/**
	 * Buscar modo alerta all.
	 *
	 * @param modoAlertaQuery el modo alerta query
	 * @return el array list
	 */
	public ArrayList<ModoAlertaBean> buscarModoAlertaAll(ModoAlertaQuery modoAlertaQuery);
	
	/**
	 * Buscar modo alerta combo.
	 *
	 * @param modoAlertaQuery el modo alerta query
	 * @return el array list
	 */
	public ArrayList<ModoAlertaBean> buscarModoAlertaCombo(ModoAlertaQuery modoAlertaQuery);
	
	/**
	 * Buscar modo alerta.
	 *
	 * @param modoAlertaQuery el modo alerta query
	 * @return el modo alerta bean
	 */
	ModoAlertaBean buscarModoAlerta(ModoAlertaQuery modoAlertaQuery);
	
	/**
	 * Obtener modo alerta.
	 *
	 * @param idModoAlerta el id modo alerta
	 * @return el modo alerta query
	 */
	public ModoAlertaQuery obtenerModoAlerta(Integer idModoAlerta);
}
