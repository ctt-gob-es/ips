package es.map.ipsc.manager.entidadFinanciera;

import java.util.ArrayList;

import es.map.ips.model.EntidadFinanciera;
import es.map.ips.model.EntidadFinancieraQuery;
import es.map.ipsc.bean.EntidadBean;


/**
 * El Interface EntidadFinancieraManager.
 */
public interface EntidadFinancieraManager {

	/**
	 * Buscar entidad combo.
	 *
	 * @return el array list
	 */
	ArrayList<EntidadBean> buscarEntidadCombo();

	/**
	 * Buscar entidad by id.
	 *
	 * @param entidadFinancieraQuery el entidad financiera query
	 * @return el entidad financiera
	 */
	EntidadFinanciera buscarEntidadById(
			EntidadFinancieraQuery entidadFinancieraQuery);

	/**
	 * Buscar entidad adeudo combo.
	 *
	 * @return el array list
	 */
	ArrayList<EntidadBean> buscarEntidadAdeudoCombo();

	/**
	 * Buscar entidad tarjeta combo.
	 *
	 * @return el array list
	 */
	ArrayList<EntidadBean> buscarEntidadTarjetaCombo();


	
}
