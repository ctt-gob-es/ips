package es.map.ipsg.manager;

import java.util.List;

import es.map.ips.model.TarifaQuery;
import es.map.ipsg.bean.TarifaBean;


/**
 * El Interface TarifaManager.
 */
public interface TarifaManager {

	/**
	 * Buscar tarifa all.
	 *
	 * @param tarifaQuery el tarifa query
	 * @return el list
	 */
	List<TarifaBean> buscarTarifaAll(TarifaQuery tarifaQuery);
	
	/**
	 * Buscar tarifa all num total.
	 *
	 * @param tarifaQuery el tarifa query
	 * @return el list
	 */
	List<TarifaBean> buscarTarifaAllNumTotal(TarifaQuery tarifaQuery);
	
	/**
	 * Guardar tarifa.
	 *
	 * @param tarifaBean el tarifa bean
	 * @return el integer
	 */
	public Integer guardarTarifa(TarifaBean tarifaBean);
	
	/**
	 * Modificar tarifa.
	 *
	 * @param tarifaBean el tarifa bean
	 */
	public void modificarTarifa(TarifaBean tarifaBean);
	
	/**
	 * Obtener tarifa.
	 *
	 * @param idTarifa el id tarifa
	 * @return el tarifa bean
	 */
	public TarifaBean obtenerTarifa(Integer idTarifa);


}
