package es.map.ipsg.manager;


import java.util.ArrayList;

import org.apache.log4j.Logger;

import es.map.ips.common.exception.ApplicationException;
import es.map.ips.common.model.SearchResult;
import es.map.ips.dao.CiudadanoDAO;
import es.map.ips.model.Ciudadano;
import es.map.ips.model.CiudadanoQuery;
import es.map.ipsg.bean.CiudadanoBean;


/**
 * El Class CiudadanoManagerImpl.
 */
public class CiudadanoManagerImpl implements CiudadanoManager {

	/** el ciudadano DAO. */
	//Variables
	private CiudadanoDAO ciudadanoDAO;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(CiudadanoManagerImpl.class);
	
	
	
	/**
	 * To ciudadano.
	 *
	 * @param ciudadanoBean el ciudadano bean
	 * @return el ciudadano
	 */
	public Ciudadano toCiudadano(CiudadanoBean ciudadanoBean){
		
		Ciudadano ciudadano = new Ciudadano();
		
		ciudadano.setId(ciudadano.getId());
		ciudadano.setNif(ciudadanoBean.getNif());
		ciudadano.setEmail(ciudadanoBean.getEmail());
		ciudadano.setNombre(ciudadanoBean.getNombre());
		ciudadano.setPrimerApellido(ciudadanoBean.getPrimerApellido());
		ciudadano.setSegundoApellido(ciudadanoBean.getSegundoApellido());
		
		return ciudadano;
	}
	
	/**
	 * To ciudadano bean.
	 *
	 * @param ciudadano el ciudadano
	 * @param numTotal el num total
	 * @return el ciudadano bean
	 */
	public CiudadanoBean toCiudadanoBean (Ciudadano ciudadano,int numTotal){
		CiudadanoBean ciudadanobean;
		ciudadanobean=toCiudadanoBean(ciudadano);
		ciudadanobean.setNumTotal(numTotal);
		return ciudadanobean;
	}
	
	/**
	 * To ciudadano bean.
	 *
	 * @param ciudadano el ciudadano
	 * @return el ciudadano bean
	 */
	public CiudadanoBean toCiudadanoBean (Ciudadano ciudadano){
		CiudadanoBean ciudadanobean = new CiudadanoBean();
		ciudadanobean.setId(ciudadano.getId());
		ciudadanobean.setNif(ciudadano.getNif());
		ciudadanobean.setEmail(ciudadano.getEmail());
		ciudadanobean.setNombre(ciudadano.getNombre());
		ciudadanobean.setPrimerApellido(ciudadano.getPrimerApellido());
		ciudadanobean.setSegundoApellido(ciudadano.getSegundoApellido());
		ciudadanobean.setNombreCompleto(ciudadano.getNombre()+" "+ciudadano.getPrimerApellido()+" "+ciudadano.getSegundoApellido());
		return ciudadanobean;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.CiudadanoManager#obtenerCiudadano(java.lang.Long)
	 */
	public CiudadanoBean obtenerCiudadano(Long id){
		Ciudadano ciudadano = ciudadanoDAO.get(id);
		return toCiudadanoBean(ciudadano);
	}

	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.CiudadanoManager#buscarCiudadanoAll(es.map.ips.model.CiudadanoQuery)
	 */
	public  ArrayList <CiudadanoBean> buscarCiudadanoAll (CiudadanoQuery ciudadanoQuery) {
		ArrayList<CiudadanoBean> arrCiudadano; 
		SearchResult<Ciudadano> ciudadano = buscarCiudadanos(ciudadanoQuery);
		int numTotal ;
		if(ciudadano.getNumResults()==null){
			
			numTotal=0;
		}else{
			numTotal=ciudadano.getNumResults();
		}
		arrCiudadano = new ArrayList<CiudadanoBean>();
		for(int i=0;i<ciudadano.getResults().size();i++){
			CiudadanoBean aux;
			aux = toCiudadanoBean(ciudadano.getResults().get(i),numTotal);
			if(aux != null){
				arrCiudadano.add(aux);
			}
		}	
		return arrCiudadano;	
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.CiudadanoManager#buscarCiudadanos(es.map.ips.model.CiudadanoQuery)
	 */
	public  SearchResult<Ciudadano> buscarCiudadanos(CiudadanoQuery ciudadanoQuery) {
		ApplicationException.assertNotNull(ciudadanoQuery, "ciudadanoQuery no puede ser null");
	
		return ciudadanoDAO.search(ciudadanoQuery);
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.CiudadanoManager#buscarCiudadano(es.map.ips.model.CiudadanoQuery)
	 */
	public  CiudadanoBean buscarCiudadano(CiudadanoQuery ciudadanoQuery) {
		ApplicationException.assertNotNull(ciudadanoQuery, "ciudadanoQuery no puede ser null");
	
		return toCiudadanoBean(ciudadanoDAO.searchUnique(ciudadanoQuery));
	}
	
	/**
	 * Obtiene el logger.
	 *
	 * @return el logger
	 */
	public static Logger getLogger() {
		return logger;
	}

	/**
	 * Obtiene el ciudadano DAO.
	 *
	 * @return the ciudadanoDAO
	 */
	public CiudadanoDAO getCiudadanoDAO() {
		return ciudadanoDAO;
	}

	/**
	 * Establece el ciudadano DAO.
	 *
	 * @param ciudadanoDAO the ciudadanoDAO to set
	 */
	public void setCiudadanoDAO(CiudadanoDAO ciudadanoDAO) {
		this.ciudadanoDAO = ciudadanoDAO;
	}

	
	

}	