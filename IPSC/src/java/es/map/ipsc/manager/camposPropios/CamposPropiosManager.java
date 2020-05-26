package es.map.ipsc.manager.camposPropios;

import java.util.ArrayList;

import es.map.ips.model.CamposPropiosQuery;
import es.map.ipsc.bean.CamposPropiosBean;

/**
 * El Interface CamposPropiosManager.
 */
public interface CamposPropiosManager {
	
	/**
	 * Buscar campos propiosby modelo.
	 *
	 * @param camposPropiosQuery el campos propios query
	 * @return el array list
	 */
	ArrayList<CamposPropiosBean> buscarCamposPropiosbyModelo(CamposPropiosQuery camposPropiosQuery);
	
	/**
	 * Buscar campo propio bean by id.
	 *
	 * @param camposPropiosQuery el campos propios query
	 * @return el campos propios bean
	 */
	CamposPropiosBean buscarCampoPropioBeanById(CamposPropiosQuery camposPropiosQuery);

}
