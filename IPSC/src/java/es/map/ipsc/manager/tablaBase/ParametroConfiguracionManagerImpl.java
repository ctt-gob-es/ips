package es.map.ipsc.manager.tablaBase;

import org.apache.log4j.Logger;

import es.map.ips.dao.ParametrosConfiguracionDAO;
import es.map.ips.model.ParametrosConfiguracion;
import es.map.ips.model.ParametrosConfiguracionQuery;
import es.map.ipsc.bean.ParametrosConfiguracionBean;

/**
 * El Class ParametroConfiguracionManagerImpl.
 */
public class ParametroConfiguracionManagerImpl implements ParametroConfiguracionManager {
	
	/** el parametros configuracion DAO. */
	private ParametrosConfiguracionDAO parametrosConfiguracionDAO;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(ParametroConfiguracionManagerImpl.class);
	
	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.tablaBase.ParametroConfiguracionManager#buscarParametroConfiguracionUnico(es.map.ips.model.ParametrosConfiguracionQuery)
	 */
	public ParametrosConfiguracionBean buscarParametroConfiguracionUnico(
			ParametrosConfiguracionQuery parametroConfiguracionQuery) {
		
		
		ParametrosConfiguracion parametros = parametrosConfiguracionDAO.searchUnique(parametroConfiguracionQuery);		
		ParametrosConfiguracionBean parametrosConfiguracionBean = new ParametrosConfiguracionBean();
		try{
			parametrosConfiguracionBean = toCentroGestorBean(parametros);
		}catch(Exception e){
			logger.error("Error parametros configuracion",e);
			return null;
		}
		return parametrosConfiguracionBean;
	}
	
	/**
	 * To centro gestor bean.
	 *
	 * @param parametros el parametros
	 * @return el parametros configuracion bean
	 */
	private ParametrosConfiguracionBean toCentroGestorBean(
			ParametrosConfiguracion parametros) {
		ParametrosConfiguracionBean aux = new ParametrosConfiguracionBean();
		aux.setId(parametros.getId());
		aux.setDescripcion(parametros.getDescripcion());
		aux.setNombreCampo(parametros.getNombreCampo());
		aux.setValorCampo(parametros.getValorCampo());
		
		return aux;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.tablaBase.ParametroConfiguracionManager#obtenerParametrosConfiguracion(java.lang.Integer)
	 */
	public ParametrosConfiguracionBean obtenerParametrosConfiguracion(Integer idParametrosConfiguracion){
		ParametrosConfiguracion parametrosConfiguracion = parametrosConfiguracionDAO.get(idParametrosConfiguracion);
		return toParametrosConfiguracionBean(parametrosConfiguracion);
	}
	

	/**
	 * To parametros configuracion bean.
	 *
	 * @param parametrosConfiguracion el parametros configuracion
	 * @return el parametros configuracion bean
	 */
	private ParametrosConfiguracionBean toParametrosConfiguracionBean(ParametrosConfiguracion parametrosConfiguracion){
		ParametrosConfiguracionBean parametrosConfiguracionBean = new ParametrosConfiguracionBean();
		
		parametrosConfiguracionBean.setId(parametrosConfiguracion.getId());
		parametrosConfiguracionBean.setDescripcion(parametrosConfiguracion.getDescripcion());
		parametrosConfiguracionBean.setNombreCampo(parametrosConfiguracion.getNombreCampo());
		parametrosConfiguracionBean.setValorCampo(parametrosConfiguracion.getValorCampo());
		parametrosConfiguracionBean.setVisible(parametrosConfiguracion.getVisible());
		
		return parametrosConfiguracionBean;
	}
	
	/**
	 * Obtiene el parametros configuracion DAO.
	 *
	 * @return el parametros configuracion DAO
	 */
	public ParametrosConfiguracionDAO getParametrosConfiguracionDAO() {
		return parametrosConfiguracionDAO;
	}
	
	/**
	 * Establece el parametros configuracion DAO.
	 *
	 * @param parametrosConfiguracionDAO el nuevo parametros configuracion DAO
	 */
	public void setParametrosConfiguracionDAO(
			ParametrosConfiguracionDAO parametrosConfiguracionDAO) {
		this.parametrosConfiguracionDAO = parametrosConfiguracionDAO;
	}
	
	/**
	 * Obtiene el logger.
	 *
	 * @return el logger
	 */
	public static Logger getLogger() {
		return logger;
	}

	
}	
	
