package es.map.ipsg.manager;

import java.util.ArrayList;

import es.map.ips.model.ProvinciaQuery;
import es.map.ipsg.bean.ProvinciaBean;

/**
 * El Interface ProvinciaManager.
 */
public interface ProvinciaManager {
	
	/**
	 * Buscar provincia combo.
	 *
	 * @param provinciaQuery el provincia query
	 * @return el array list
	 */
	public ArrayList<ProvinciaBean> buscarProvinciaCombo(ProvinciaQuery provinciaQuery);
	
	/**
	 * Buscar provincia combo visibilidad.
	 *
	 * @param provinciaQuery el provincia query
	 * @return el array list
	 */
	public ArrayList<ProvinciaBean> buscarProvinciaComboVisibilidad(ProvinciaQuery provinciaQuery);
	
	/**
	 * Guardar provincia.
	 *
	 * @param provinciaBean el provincia bean
	 * @return el integer
	 */
	public Integer guardarProvincia(ProvinciaBean provinciaBean);
	
	/**
	 * Modificar provincia.
	 *
	 * @param provinciaBean el provincia bean
	 */
	public void modificarProvincia(ProvinciaBean provinciaBean);
	
	/**
	 * Buscar provincia unique.
	 *
	 * @param provinciaQuery el provincia query
	 * @return el provincia bean
	 */
	public ProvinciaBean buscarProvinciaUnique(ProvinciaQuery provinciaQuery);

}
