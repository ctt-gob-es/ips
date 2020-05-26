package es.map.ipsc.manager.tituloOficial;

import java.util.ArrayList;

import es.map.ips.model.TituloOficialQuery;
import es.map.ipsc.bean.TituloOficialBean;

/**
 * El Interface TituloOficialManager.
 */
public interface TituloOficialManager {

	/**
	 * Buscar titulo oficiales combo.
	 *
	 * @return el array list
	 */
	ArrayList<TituloOficialBean> buscarTituloOficialesCombo();

	/**
	 * Buscar titulo oficial id.
	 *
	 * @param tituloOficialQuery el titulo oficial query
	 * @return el titulo oficial bean
	 */
	TituloOficialBean buscarTituloOficialId(
			TituloOficialQuery tituloOficialQuery);


}
