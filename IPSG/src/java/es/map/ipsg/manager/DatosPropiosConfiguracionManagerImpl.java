package es.map.ipsg.manager;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.log4j.Logger;

import es.map.ips.common.exception.ApplicationException;
import es.map.ips.common.model.SearchResult;
import es.map.ips.dao.ConvocatoriaDAO;
import es.map.ips.dao.DatosPropiosConfiguracionDAO;
import es.map.ips.model.Convocatoria;
import es.map.ips.model.ConvocatoriaQuery;
import es.map.ips.model.DatosPropiosConfiguracion;
import es.map.ips.model.DatosPropiosConfiguracionQuery;
import es.map.ipsg.bean.DatosPropiosConfigBean;



/**
 * El Class DatosPropiosConfiguracionManagerImpl.
 */
public class DatosPropiosConfiguracionManagerImpl implements DatosPropiosConfiguracionManager {

	/** el datos propios configuracion DAO. */
	//Variables
	private DatosPropiosConfiguracionDAO datosPropiosConfiguracionDAO;
	
	/** el convocatoria DAO. */
	private ConvocatoriaDAO convocatoriaDAO;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(DatosPropiosConfiguracionManagerImpl.class);
	
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.DatosPropiosConfiguracionManager#buscarDatosPropiosConfigbyCampo(es.map.ips.model.DatosPropiosConfiguracionQuery)
	 */
	@Override
	public ArrayList<DatosPropiosConfigBean> buscarDatosPropiosConfigbyCampo(DatosPropiosConfiguracionQuery datosPropiosConfigQuery) {
		ArrayList<DatosPropiosConfigBean> arrCamposPropios= new ArrayList<DatosPropiosConfigBean>();
		
		try  {
			SearchResult<DatosPropiosConfiguracion> datosPropiosConf = busquedaDatosPropiosConfigByCampo(datosPropiosConfigQuery);
	
			for(int i=0;i<datosPropiosConf.getResults().size();i++){
				DatosPropiosConfigBean aux;
				aux = toDatosPropiosBean(datosPropiosConf.getResults().get(i));
				if(aux != null){
					arrCamposPropios.add(aux);
				}
			}	
		} catch (Exception e) {
			logger.error("Error al realizar la busqueda en la tabla DatosPropiosConfiguracion",e);
		}

		return arrCamposPropios;		
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.DatosPropiosConfiguracionManager#obtenerDatoPropio(java.lang.Integer)
	 */
	public DatosPropiosConfigBean obtenerDatoPropio(Integer idDesplegable){
		DatosPropiosConfiguracionQuery datosPropiosConfiguracionQuery = new DatosPropiosConfiguracionQuery();
		datosPropiosConfiguracionQuery.setIdDesplegable(idDesplegable);
		DatosPropiosConfiguracion datoPropio = datosPropiosConfiguracionDAO.searchUnique(datosPropiosConfiguracionQuery);
		return toDatosPropiosBean(datoPropio);
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.DatosPropiosConfiguracionManager#obtenerDatoPropioConCampo(java.lang.Integer)
	 */
	public DatosPropiosConfigBean obtenerDatoPropioConCampo(Integer idDesplegable){
		DatosPropiosConfiguracion datoPropio = datosPropiosConfiguracionDAO.get(idDesplegable);
		return toDatosPropiosBean(datoPropio);
	}
	
	/**
	 * To datos propios bean.
	 *
	 * @param datosPropiosConf el datos propios conf
	 * @return el datos propios config bean
	 */
	private DatosPropiosConfigBean toDatosPropiosBean(DatosPropiosConfiguracion datosPropiosConf) {					
		DatosPropiosConfigBean auxDatosPropiosConf = new DatosPropiosConfigBean();
		
		auxDatosPropiosConf.setIdDesplegable(datosPropiosConf.getIdDesplegable().longValue());
		auxDatosPropiosConf.setCampo(datosPropiosConf.getCamposPropios());
		auxDatosPropiosConf.setDescripcion(datosPropiosConf.getDescripcion());
		
		if (datosPropiosConf.getConvocatoria() != null && datosPropiosConf.getConvocatoria().size() > 0) {
			Set<Convocatoria> convocatoria = new HashSet<Convocatoria>();
			Iterator<Convocatoria> it = datosPropiosConf.getConvocatoria().iterator();
			
			while(it.hasNext()){
				logger.info("iterator Convocatorias");
				Convocatoria t=(Convocatoria)it.next();
				ConvocatoriaQuery convocatoriaQuery = new ConvocatoriaQuery();
				convocatoriaQuery.setJoin_estadoConvocatoria(true);
				convocatoriaQuery.setId(t.getId());
				convocatoria.add(convocatoriaDAO.searchUnique(convocatoriaQuery));
			}
			
			auxDatosPropiosConf.setConvocatorias(convocatoria);
		}
		
		return auxDatosPropiosConf;
	}
	
	/**
	 * To datos propios conf.
	 *
	 * @param datosPropios el datos propios
	 * @return el datos propios configuracion
	 */
	private DatosPropiosConfiguracion toDatosPropiosConf(DatosPropiosConfigBean datosPropios) {					
		DatosPropiosConfiguracion auxDatosPropios = new DatosPropiosConfiguracion();
		auxDatosPropios.setIdDesplegable((datosPropios.getIdDesplegable() != null)?datosPropios.getIdDesplegable().intValue():null);
		auxDatosPropios.setCamposPropios(datosPropios.getCampo());
		auxDatosPropios.setDescripcion(datosPropios.getDescripcion());
		return auxDatosPropios;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.DatosPropiosConfiguracionManager#guardarDatosPropiosConf(es.map.ipsg.bean.DatosPropiosConfigBean)
	 */
	@Override
	public Integer guardarDatosPropiosConf(DatosPropiosConfigBean datosPropiosConfBean) {
		DatosPropiosConfiguracion campoPropio = toDatosPropiosConf(datosPropiosConfBean);
		Integer idOpcion= datosPropiosConfiguracionDAO.insert(campoPropio);		
		return idOpcion;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.DatosPropiosConfiguracionManager#actualizarDatosPropiosConf(es.map.ipsg.bean.DatosPropiosConfigBean)
	 */
	@Override
	public Boolean actualizarDatosPropiosConf(DatosPropiosConfigBean datosPropiosConfBean) {
		Boolean update = false;
		DatosPropiosConfiguracion datosPropiosConfiguracion = toDatosPropiosConf(datosPropiosConfBean);
		try {
			datosPropiosConfiguracionDAO.update(datosPropiosConfiguracion);
			update = true;
		} catch (Exception e) {
			logger.error("Error al actualizar el dato propio de configuracion.");
		}
		return update;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.DatosPropiosConfiguracionManager#eliminarDatosPropiosConf(java.lang.Integer)
	 */
	@Override
	public Boolean eliminarDatosPropiosConf(Integer IdDesplegable) {
		Boolean delete = false;
		try {
			datosPropiosConfiguracionDAO.delete(IdDesplegable);
			delete = true;
		} catch (Exception e) {
			logger.error("Error al eliminar el dato propio de configuracion.");
		}
		return delete;
	}
	
	/**
	 * Busqueda datos propios config by campo.
	 *
	 * @param datosPropiosConfigQuery el datos propios config query
	 * @return el search result
	 */
	private SearchResult<DatosPropiosConfiguracion> busquedaDatosPropiosConfigByCampo(DatosPropiosConfiguracionQuery datosPropiosConfigQuery) {
		ApplicationException.assertNotNull(datosPropiosConfigQuery, "datosPropiosConfigQuery no puede ser null");
		return datosPropiosConfiguracionDAO.search(datosPropiosConfigQuery);
	}
	
	/**
	 * Obtiene el datos propios configuracion DAO.
	 *
	 * @return el datos propios configuracion DAO
	 */
	public DatosPropiosConfiguracionDAO getDatosPropiosConfiguracionDAO() {
		return datosPropiosConfiguracionDAO;
	}
	
	/**
	 * Establece el datos propios configuracion DAO.
	 *
	 * @param datosPropiosConfiguracionDAO el nuevo datos propios configuracion DAO
	 */
	public void setDatosPropiosConfiguracionDAO(DatosPropiosConfiguracionDAO datosPropiosConfiguracionDAO) {
		this.datosPropiosConfiguracionDAO = datosPropiosConfiguracionDAO;
	}


	/**
	 * Obtiene el convocatoria DAO.
	 *
	 * @return el convocatoria DAO
	 */
	public ConvocatoriaDAO getConvocatoriaDAO() {
		return convocatoriaDAO;
	}


	/**
	 * Establece el convocatoria DAO.
	 *
	 * @param convocatoriaDAO el nuevo convocatoria DAO
	 */
	public void setConvocatoriaDAO(ConvocatoriaDAO convocatoriaDAO) {
		this.convocatoriaDAO = convocatoriaDAO;
	}
	
}	
	
