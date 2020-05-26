package es.map.ipsg.manager;

import java.util.ArrayList;

import es.map.ips.model.ParametrosConfiguracionQuery;
import es.map.ipsg.bean.ParametrosConfiguracionBean;



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
	 * Buscar parametros configuracion all.
	 *
	 * @param parametrosConfiguracionQuery el parametros configuracion query
	 * @return el array list
	 */
	public ArrayList<ParametrosConfiguracionBean> buscarParametrosConfiguracionAll(ParametrosConfiguracionQuery parametrosConfiguracionQuery);
	
	/**
	 * Guardar parametro configuracion.
	 *
	 * @param parametrosConfiguracionBean el parametros configuracion bean
	 * @return el integer
	 */
	public Integer guardarParametroConfiguracion(ParametrosConfiguracionBean parametrosConfiguracionBean);
	
	/**
	 * Modificar parametro configuracion.
	 *
	 * @param parametrosConfiguracionBean el parametros configuracion bean
	 */
	public void modificarParametroConfiguracion (ParametrosConfiguracionBean  parametrosConfiguracionBean);
	
	/**
	 * Obtener parametros configuracion.
	 *
	 * @param idParametrosConfiguracion el id parametros configuracion
	 * @return el parametros configuracion bean
	 */
	public ParametrosConfiguracionBean obtenerParametrosConfiguracion(Integer idParametrosConfiguracion);
}
