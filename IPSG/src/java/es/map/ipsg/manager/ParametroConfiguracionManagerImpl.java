package es.map.ipsg.manager;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import es.map.ips.common.exception.ApplicationException;
import es.map.ips.common.model.SearchResult;
import es.map.ips.dao.ParametrosConfiguracionDAO;
import es.map.ips.model.ParametrosConfiguracion;
import es.map.ips.model.ParametrosConfiguracionQuery;
import es.map.ipsg.bean.ParametrosConfiguracionBean;


/**
 * El Class ParametroConfiguracionManagerImpl.
 */
public class ParametroConfiguracionManagerImpl implements ParametroConfiguracionManager {
	
	/** el parametros configuracion DAO. */
	private ParametrosConfiguracionDAO parametrosConfiguracionDAO;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(ParametroConfiguracionManagerImpl.class);
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.ParametroConfiguracionManager#buscarParametroConfiguracionUnico(es.map.ips.model.ParametrosConfiguracionQuery)
	 */
	public ParametrosConfiguracionBean buscarParametroConfiguracionUnico(
			ParametrosConfiguracionQuery parametroConfiguracionQuery) {
		
		
		ParametrosConfiguracion parametros = parametrosConfiguracionDAO.searchUnique(parametroConfiguracionQuery);		
		ParametrosConfiguracionBean parametrosConfiguracionBean = new ParametrosConfiguracionBean();
		try{
			parametrosConfiguracionBean = toParametrosConfiguracionBean(parametros);
		}catch(Exception e){
			logger.error("Error ParametroConfiguracionManagerImpl- buscarParametroConfiguracionUnico.",e);
		}
		return parametrosConfiguracionBean;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.ParametroConfiguracionManager#buscarParametrosConfiguracionAll(es.map.ips.model.ParametrosConfiguracionQuery)
	 */
	public ArrayList<ParametrosConfiguracionBean> buscarParametrosConfiguracionAll(ParametrosConfiguracionQuery parametrosConfiguracionQuery){
		
		SearchResult<ParametrosConfiguracion> parametrosConfiguracion = buscarParametroConfiguracion(parametrosConfiguracionQuery);

		ArrayList<ParametrosConfiguracionBean>arrParametrosConfiguracion= new ArrayList<ParametrosConfiguracionBean>();
		for(int i=0;i<parametrosConfiguracion.getResults().size();i++){
			ParametrosConfiguracionBean aux;
			aux = toParametrosConfiguracionBean(parametrosConfiguracion.getResults().get(i));
			if(aux != null){
				arrParametrosConfiguracion.add(aux);
			}
		}	
		return arrParametrosConfiguracion;		
	}
	
	/**
	 * Buscar parametro configuracion.
	 *
	 * @param parametrosConfiguracionQuery el parametros configuracion query
	 * @return el search result
	 */
	private SearchResult<ParametrosConfiguracion> buscarParametroConfiguracion(ParametrosConfiguracionQuery parametrosConfiguracionQuery) {
		ApplicationException.assertNotNull(parametrosConfiguracionQuery, "parametrosConfiguracionQuery no puede ser null");
	
		return parametrosConfiguracionDAO.search(parametrosConfiguracionQuery);
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.ParametroConfiguracionManager#guardarParametroConfiguracion(es.map.ipsg.bean.ParametrosConfiguracionBean)
	 */
	public Integer guardarParametroConfiguracion(ParametrosConfiguracionBean parametrosConfiguracionBean){
		ParametrosConfiguracion parametrosConfiguracion= toParametrosConfiguracion(parametrosConfiguracionBean);
		Integer idParametrosConfiguracion = parametrosConfiguracionDAO.insert(parametrosConfiguracion);		
		return idParametrosConfiguracion;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.ParametroConfiguracionManager#modificarParametroConfiguracion(es.map.ipsg.bean.ParametrosConfiguracionBean)
	 */
	public void modificarParametroConfiguracion (ParametrosConfiguracionBean  parametrosConfiguracionBean){
		ParametrosConfiguracion parametrosConfiguracion =  toParametrosConfiguracion(parametrosConfiguracionBean);
		parametrosConfiguracionDAO.update(parametrosConfiguracion);
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.ParametroConfiguracionManager#obtenerParametrosConfiguracion(java.lang.Integer)
	 */
	public ParametrosConfiguracionBean obtenerParametrosConfiguracion(Integer idParametrosConfiguracion){
		ParametrosConfiguracion parametrosConfiguracion = parametrosConfiguracionDAO.get(idParametrosConfiguracion);
		return toParametrosConfiguracionBean(parametrosConfiguracion);
	}
	
	/**
	 * To parametros configuracion bean.
	 *
	 * @param parametros el parametros
	 * @return el parametros configuracion bean
	 */
	private ParametrosConfiguracionBean toParametrosConfiguracionBean(
			ParametrosConfiguracion parametros) {
		ParametrosConfiguracionBean aux = new ParametrosConfiguracionBean();
		aux.setId(parametros.getId());
		aux.setDescripcion(parametros.getDescripcion());
		aux.setNombreCampo(parametros.getNombreCampo());
		aux.setValorCampo(parametros.getValorCampo());
		aux.setVisible(parametros.getVisible());
		
		return aux;
	}
	
	/**
	 * To parametros configuracion.
	 *
	 * @param parametrosConfiguracionBean el parametros configuracion bean
	 * @return el parametros configuracion
	 */
	private ParametrosConfiguracion toParametrosConfiguracion(ParametrosConfiguracionBean parametrosConfiguracionBean){
		ParametrosConfiguracion parametrosConfiguracion = new ParametrosConfiguracion();
		
		parametrosConfiguracion.setId(parametrosConfiguracionBean.getId());
		parametrosConfiguracion.setDescripcion(parametrosConfiguracionBean.getDescripcion());
		parametrosConfiguracion.setNombreCampo(parametrosConfiguracionBean.getNombreCampo());
		parametrosConfiguracion.setValorCampo(parametrosConfiguracionBean.getValorCampo());
		parametrosConfiguracion.setVisible(parametrosConfiguracionBean.getVisible());
		
		return parametrosConfiguracion;
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
	
