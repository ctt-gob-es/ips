package es.map.ipsc.manager.centroGestor;

import java.util.ArrayList;

import es.map.ips.common.model.SearchResult;
import es.map.ips.model.CentroGestor;
import es.map.ips.model.CentroGestorQuery;
import es.map.ipsc.bean.CentroGestorBean;


/**
 * El Interface CentroGestorManager.
 */
public interface CentroGestorManager {

	/**
	 * Buscar centro gestores combo.
	 *
	 * @return el array list
	 */
	ArrayList<CentroGestorBean> buscarCentroGestoresCombo();

	/**
	 * Buscar centro gestor id.
	 *
	 * @param centroGestorQuery el centro gestor query
	 * @return el centro gestor bean
	 */
	CentroGestorBean buscarCentroGestorId(CentroGestorQuery centroGestorQuery);

	/**
	 * Buscar centro gestor cod model.
	 *
	 * @param centroGestorQuery el centro gestor query
	 * @return el search result
	 */
	SearchResult<CentroGestor> buscarCentroGestorCodModel(CentroGestorQuery centroGestorQuery);
	
	/**
	 * Buscar centro gestor cod model 2.
	 *
	 * @param centroGestorQuery el centro gestor query
	 * @return el centro gestor
	 */
	CentroGestor buscarCentroGestorCodModel2(CentroGestorQuery centroGestorQuery);


}
