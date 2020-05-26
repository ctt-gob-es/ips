package es.map.ipsc.manager.ministerio;

import java.util.ArrayList;

import es.map.ips.model.MinisterioQuery;
import es.map.ipsc.bean.MinisterioBean;

/**
 * El Interface MinisterioManager.
 */
public interface MinisterioManager {

	/**
	 * Buscar ministerios combo.
	 *
	 * @return el array list
	 */
	ArrayList<MinisterioBean> buscarMinisteriosCombo();

	/**
	 * Buscar ministerio id.
	 *
	 * @param ministerioQuery el ministerio query
	 * @return el ministerio bean
	 */
	MinisterioBean buscarMinisterioId(MinisterioQuery ministerioQuery);

}
