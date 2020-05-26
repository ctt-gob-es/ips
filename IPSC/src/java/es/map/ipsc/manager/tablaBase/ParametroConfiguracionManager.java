package es.map.ipsc.manager.tablaBase;

import es.map.ips.model.ParametrosConfiguracionQuery;
import es.map.ipsc.bean.ParametrosConfiguracionBean;


/**
 * El Interface ParametroConfiguracionManager.
 */
public interface ParametroConfiguracionManager {

	/**
	 * Buscar parametro configuracion unico.
	 *
	 * @param parametroConfiguracionQuery el parametro configuracion query
	 * @return el parametros configuracion bean
	 */
	public ParametrosConfiguracionBean buscarParametroConfiguracionUnico(ParametrosConfiguracionQuery parametroConfiguracionQuery);
	
	/**
	 * Obtener parametros configuracion.
	 *
	 * @param idParametrosConfiguracion el id parametros configuracion
	 * @return el parametros configuracion bean
	 */
	public ParametrosConfiguracionBean obtenerParametrosConfiguracion(Integer idParametrosConfiguracion);

}
