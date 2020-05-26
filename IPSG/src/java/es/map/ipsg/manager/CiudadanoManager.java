package es.map.ipsg.manager;

import java.util.ArrayList;

import es.map.ips.common.model.SearchResult;
import es.map.ips.model.Ciudadano;
import es.map.ips.model.CiudadanoQuery;
import es.map.ipsg.bean.CiudadanoBean;

/**
 * El Interface CiudadanoManager.
 */
public interface CiudadanoManager {


	/**
	 * Obtener ciudadano.
	 *
	 * @param id el id
	 * @return el ciudadano bean
	 */
	public CiudadanoBean obtenerCiudadano(Long id);
	
	/**
	 * Buscar ciudadano all.
	 *
	 * @param ciudadanoQuery el ciudadano query
	 * @return el array list
	 */
	public ArrayList <CiudadanoBean> buscarCiudadanoAll (CiudadanoQuery ciudadanoQuery);
	
	/**
	 * Buscar ciudadanos.
	 *
	 * @param ciudadanoQuery el ciudadano query
	 * @return el search result
	 */
	public SearchResult <Ciudadano> buscarCiudadanos (CiudadanoQuery ciudadanoQuery);
	
	/**
	 * Buscar ciudadano.
	 *
	 * @param ciudadanoQuery el ciudadano query
	 * @return el ciudadano bean
	 */
	public CiudadanoBean buscarCiudadano (CiudadanoQuery ciudadanoQuery);
}
