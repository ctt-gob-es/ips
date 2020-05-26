package es.map.ipsc.manager.formaAcceso;

import java.util.ArrayList;

import es.map.ips.model.FormaAccesoQuery;
import es.map.ipsc.bean.FormaAccesoBean;


/**
 * El Interface FormaAccesoManager.
 */
public interface FormaAccesoManager {

	/**
	 * Buscar forma acceso combo.
	 *
	 * @return el array list
	 */
	ArrayList<FormaAccesoBean> buscarFormaAccesoCombo();

	/**
	 * Buscar forma acceso id.
	 *
	 * @param formaAccesoQuery el forma acceso query
	 * @return el forma acceso bean
	 */
	FormaAccesoBean buscarFormaAccesoId(FormaAccesoQuery formaAccesoQuery);


}
