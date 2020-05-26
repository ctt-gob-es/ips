package es.map.ipsg.manager;

import java.util.ArrayList;

import es.map.ips.model.CamposPropios;
import es.map.ips.model.CamposPropiosQuery;
import es.map.ipsg.bean.CamposPropiosBean;

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
	 * Guardar campo propio.
	 *
	 * @param campoPropioBean el campo propio bean
	 * @return el integer
	 */
	Integer guardarCampoPropio(CamposPropiosBean campoPropioBean);

	/**
	 * Buscar campos propiosby campo.
	 *
	 * @param camposPropiosQuery el campos propios query
	 * @return el array list
	 */
	ArrayList<CamposPropiosBean> buscarCamposPropiosbyCampo(CamposPropiosQuery camposPropiosQuery);

	/**
	 * Modificar campo propio.
	 *
	 * @param campoPropioBean el campo propio bean
	 */
	void modificarCampoPropio(CamposPropiosBean campoPropioBean);

	/**
	 * Buscar campos propios unico.
	 *
	 * @param camposPropiosQuery el campos propios query
	 * @return el campos propios bean
	 */
	CamposPropiosBean buscarCamposPropiosUnico(CamposPropiosQuery camposPropiosQuery);

	/**
	 * Busca campos propios unico.
	 *
	 * @param camposPropiosQuery el campos propios query
	 * @return el campos propios
	 */
	CamposPropios buscaCamposPropiosUnico(CamposPropiosQuery camposPropiosQuery);
}
