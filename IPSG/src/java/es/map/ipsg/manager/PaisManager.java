package es.map.ipsg.manager;

import java.util.ArrayList;

import es.map.ips.model.PaisQuery;
import es.map.ipsg.bean.PaisBean;




/**
 * El Interface PaisManager.
 */
public interface PaisManager {
	
	/**
	 * Buscar pais combo.
	 *
	 * @param paisQuery el pais query
	 * @return el array list
	 */
	public ArrayList<PaisBean> buscarPaisCombo(PaisQuery paisQuery);
	
	/**
	 * Buscar pais combo 2.
	 *
	 * @param paisQuery el pais query
	 * @return el array list
	 */
	public ArrayList<PaisBean> buscarPaisCombo2(PaisQuery paisQuery);
	
	/**
	 * Guardar pais.
	 *
	 * @param paisBean el pais bean
	 * @return el integer
	 */
	public Integer guardarPais(PaisBean paisBean);
	
	/**
	 * Modificar pais.
	 *
	 * @param paisBean el pais bean
	 */
	public void modificarPais(PaisBean paisBean);
	
	/**
	 * Buscar pais unique.
	 *
	 * @param paisQuery el pais query
	 * @return el pais bean
	 */
	public PaisBean buscarPaisUnique(PaisQuery paisQuery);

}
