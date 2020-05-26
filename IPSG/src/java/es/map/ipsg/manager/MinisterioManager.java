package es.map.ipsg.manager;

import java.util.ArrayList;
import java.util.List;

import es.map.ips.model.MinisterioQuery;
import es.map.ipsg.bean.MinisterioBean;

/**
 * El Interface MinisterioManager.
 */
public interface MinisterioManager {
	
	/**
	 * Buscar ministerios all.
	 *
	 * @param ministerioQuery el ministerio query
	 * @return el list
	 */
	List<MinisterioBean> buscarMinisteriosAll(MinisterioQuery ministerioQuery);
	
	/**
	 * Buscar ministerio combo.
	 *
	 * @param ministerioQuery el ministerio query
	 * @return el array list
	 */
	public ArrayList<MinisterioBean> buscarMinisterioCombo(MinisterioQuery ministerioQuery);
	
	/**
	 * Buscar ministerio combo todos.
	 *
	 * @return el array list
	 */
	public ArrayList<MinisterioBean> buscarMinisterioComboTodos();
	
	/**
	 * Guardar ministerio.
	 *
	 * @param ministerioBean el ministerio bean
	 * @return el int
	 */
	int guardarMinisterio(MinisterioBean ministerioBean);
	
	/**
	 * Buscar ministerio all.
	 *
	 * @param ministerioQuery el ministerio query
	 * @return el array list
	 */
	public ArrayList<MinisterioBean> buscarMinisterioAll(MinisterioQuery ministerioQuery);
	
	/**
	 * Buscar ministerio.
	 *
	 * @param ministerioQuery el ministerio query
	 * @return el ministerio bean
	 */
	MinisterioBean buscarMinisterio(MinisterioQuery ministerioQuery);
	
	/**
	 * Obtener ministerio.
	 *
	 * @param idMinisterio el id ministerio
	 * @return el ministerio bean
	 */
	public MinisterioBean obtenerMinisterio(Integer idMinisterio);
	
	/**
	 * Modificar ministerio.
	 *
	 * @param ministerioBean el ministerio bean
	 */
	public void modificarMinisterio(MinisterioBean ministerioBean);
	
	/**
	 * Actualizar ministerio.
	 *
	 * @param ministerioBean el ministerio bean
	 */
	void actualizarMinisterio(MinisterioBean ministerioBean);
}
