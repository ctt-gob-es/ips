package es.map.ipsc.manager.provincia;



import java.util.ArrayList;

import es.map.ips.model.ProvinciaQuery;
import es.map.ipsc.bean.ProvinciaBean;

/**
 * El Interface ProvinciaManager.
 */
public interface ProvinciaManager {

	/**
	 * Buscar provincias activas combo.
	 *
	 * @return el array list
	 */
	ArrayList<ProvinciaBean> buscarProvinciasActivasCombo();

	/**
	 * Buscar provicia id.
	 *
	 * @param provinciaQuery el provincia query
	 * @return el provincia bean
	 */
	ProvinciaBean buscarProviciaId(ProvinciaQuery provinciaQuery);

}
