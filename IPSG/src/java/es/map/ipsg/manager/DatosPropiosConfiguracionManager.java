package es.map.ipsg.manager;

import java.util.ArrayList;

import es.map.ips.model.DatosPropiosConfiguracionQuery;
import es.map.ipsg.bean.DatosPropiosConfigBean;

/**
 * El Interface DatosPropiosConfiguracionManager.
 */
public interface DatosPropiosConfiguracionManager {
	
	/**
	 * Buscar datos propios configby campo.
	 *
	 * @param datosPropiosConfigQuery el datos propios config query
	 * @return el array list
	 */
	ArrayList<DatosPropiosConfigBean> buscarDatosPropiosConfigbyCampo(DatosPropiosConfiguracionQuery datosPropiosConfigQuery);
	
	/**
	 * Guardar datos propios conf.
	 *
	 * @param datosPropiosConfBean el datos propios conf bean
	 * @return el integer
	 */
	Integer guardarDatosPropiosConf(DatosPropiosConfigBean datosPropiosConfBean);
	
	/**
	 * Actualizar datos propios conf.
	 *
	 * @param datosPropiosConfBean el datos propios conf bean
	 * @return el boolean
	 */
	Boolean actualizarDatosPropiosConf(DatosPropiosConfigBean datosPropiosConfBean);
	
	/**
	 * Eliminar datos propios conf.
	 *
	 * @param IdDesplegable el id desplegable
	 * @return el boolean
	 */
	Boolean eliminarDatosPropiosConf(Integer IdDesplegable);
	
	/**
	 * Obtener dato propio.
	 *
	 * @param idDesplegable el id desplegable
	 * @return el datos propios config bean
	 */
	DatosPropiosConfigBean obtenerDatoPropio(Integer idDesplegable);
	
	/**
	 * Obtener dato propio con campo.
	 *
	 * @param idDesplegable el id desplegable
	 * @return el datos propios config bean
	 */
	DatosPropiosConfigBean obtenerDatoPropioConCampo(Integer idDesplegable);
}
