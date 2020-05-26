package es.map.ipsc.manager.provincia;

import java.util.ArrayList;

import es.map.ips.model.ProvinciaExamenQuery;
import es.map.ipsc.bean.ProvinciaExamenBean;

/**
 * El Interface ProvinciaExamenManager.
 */
public interface ProvinciaExamenManager {

	/**
	 * Buscar provincias examen activas combo.
	 *
	 * @return el array list
	 */
	ArrayList<ProvinciaExamenBean> buscarProvinciasExamenActivasCombo();

	/**
	 * Buscar provicia examen id.
	 *
	 * @param provinciaExamenQuery el provincia examen query
	 * @return el provincia examen bean
	 */
	ProvinciaExamenBean buscarProviciaExamenId(ProvinciaExamenQuery provinciaExamenQuery);

}