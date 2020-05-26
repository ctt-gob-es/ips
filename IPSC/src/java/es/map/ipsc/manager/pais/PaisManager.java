package es.map.ipsc.manager.pais;

import java.util.ArrayList;

import es.map.ips.model.PaisQuery;
import es.map.ipsc.bean.PaisBean;

/**
 * El Interface PaisManager.
 */
public interface PaisManager {

	/**
	 * Buscar paises.
	 *
	 * @return el array list
	 */
	ArrayList<PaisBean> buscarPaises();

	/**
	 * Buscar pais id.
	 *
	 * @param paisQuery el pais query
	 * @return el pais bean
	 */
	PaisBean buscarPaisId(PaisQuery paisQuery);

	
}
