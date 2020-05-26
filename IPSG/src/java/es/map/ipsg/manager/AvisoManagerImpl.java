package es.map.ipsg.manager;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import es.map.ips.common.exception.ApplicationException;
import es.map.ips.common.model.SearchResult;
import es.map.ips.dao.AvisoDAO;
import es.map.ips.model.Aviso;
import es.map.ips.model.AvisoQuery;
import es.map.ips.model.EstadoAviso;
import es.map.ipsg.bean.AvisoBean;


/**
 *  Clase que implementa el TituloOficialManager.
 *
 * @author amartinl
 */
public class AvisoManagerImpl implements AvisoManager {

	/** el aviso DAO. */
	//Variables
	private AvisoDAO avisoDAO;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(AvisoManagerImpl.class);
	
	
	/**
	 * Buscar avisos all.
	 *
	 * @param alertaQuery el alerta query
	 * @return el list
	 */
	public List<AvisoBean> buscarAvisosAll(AvisoQuery alertaQuery) {
		List<AvisoBean> list = new ArrayList<AvisoBean>();
		SearchResult<Aviso> alertas = avisoDAO.search(alertaQuery);
		int numTotal = 0;

		if(alertas != null){
			numTotal = alertas.getNumResults();
		}

		for(Aviso u:alertas.getResults()){
			AvisoBean alertaBean = new AvisoBean();
			try{
				alertaBean = toAvisoBean(u,numTotal);
			}catch(Exception e){
				logger.error("Error AvisoManagerImpl - buscarAvisosAll- toAvisoBean",e);
			}
			list.add(alertaBean);
		}
		
		return list;
	}

	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.AvisoManager#buscarAvisoCombo(es.map.ips.model.AvisoQuery)
	 */
	public ArrayList<AvisoBean> buscarAvisoCombo(AvisoQuery alertaQuery){
		
		SearchResult<Aviso> aviso = buscarAvisos(alertaQuery);
		ArrayList<AvisoBean> arrAviso = new ArrayList<AvisoBean>();
		for(int i=0;i<aviso.getResults().size();i++){
			AvisoBean aux;
			aux = toAvisoComboBean(aviso.getResults().get(i));
			if(aux != null){
				arrAviso.add(aux);
			}
		}	
		return arrAviso;		
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.AvisoManager#buscarAvisoAll(es.map.ips.model.AvisoQuery)
	 */
	public ArrayList<AvisoBean> buscarAvisoAll(AvisoQuery alertaQuery){	
		System.out.println("Entra en buscarAvisoAll");
		SearchResult<Aviso> aviso = buscarAvisos(alertaQuery);
		int numTotal ;
		if(aviso.getNumResults()==null){
			
			numTotal=0;;
		}else{
			numTotal=aviso.getNumResults();
		}
		ArrayList<AvisoBean> arrAviso = new ArrayList<AvisoBean>();
		for(int i=0;i<aviso.getResults().size();i++){
			AvisoBean aux;
			aux = toAvisoBean(aviso.getResults().get(i),numTotal);
			if(aux != null){
				arrAviso.add(aux);
			}
		}	
		return arrAviso;		
	}
	
	/**
	 * To aviso bean.
	 *
	 * @param avisoAux el aviso aux
	 * @param numTotal el num total
	 * @return el aviso bean
	 */
	private AvisoBean toAvisoBean(Aviso avisoAux, int numTotal) {
		AvisoBean aux = new AvisoBean();
		aux.setIdAviso(avisoAux.getId());
		aux.setIdEstadoAviso(avisoAux.getEstadoAviso().getId());
		aux.setDesEstadoAviso(avisoAux.getEstadoAviso().getDescripcion());
		aux.setTexto(avisoAux.getTexto());
		aux.setFechaInicio(avisoAux.getFechaInicio());
		aux.setFechaFin(avisoAux.getFechaFin());
		

		aux.setNumTotal(numTotal);
		
		return aux;
	}
	
	/**
	 * To aviso combo bean.
	 *
	 * @param avisoAux el aviso aux
	 * @return el aviso bean
	 */
	private AvisoBean toAvisoComboBean(Aviso avisoAux) {
		AvisoBean aux = new AvisoBean();
		aux.setIdAviso(avisoAux.getId());
		aux.setIdEstadoAviso(avisoAux.getEstadoAviso().getId());
		aux.setDesEstadoAviso(avisoAux.getEstadoAviso().getDescripcion());
		aux.setTexto(avisoAux.getTexto());
		aux.setFechaInicio(avisoAux.getFechaInicio());
		aux.setFechaFin(avisoAux.getFechaFin());
		
		return aux;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.AvisoManager#buscarAviso(es.map.ips.model.AvisoQuery)
	 */
	public AvisoBean buscarAviso(AvisoQuery alertaQuery) {
		Aviso aviso;
		aviso = avisoDAO.searchUnique(alertaQuery);
		AvisoBean alertaBean = new AvisoBean();
		try{
			alertaBean = toAvisoBean(aviso,0);
		}catch(Exception e){
			logger.error("Error AvisoManagerImpl - buscarAviso- toAvisoBean",e);
		}
		return alertaBean;
	}


	
	/**
	 * Buscar avisos.
	 *
	 * @param alertaQuery el alerta query
	 * @return el search result
	 */
	private SearchResult<Aviso> buscarAvisos (AvisoQuery alertaQuery) {
		ApplicationException.assertNotNull(alertaQuery, "alertaQuery no puede ser null");
	
		return avisoDAO.search(alertaQuery);
	}

	/**
	 * Obtiene el aviso DAO.
	 *
	 * @return el aviso DAO
	 */
	public AvisoDAO getAvisoDAO() {
		return avisoDAO;
	}

	/**
	 * Establece el aviso DAO.
	 *
	 * @param alertaDAO el nuevo aviso DAO
	 */
	public void setAvisoDAO(AvisoDAO alertaDAO) {
		this.avisoDAO = alertaDAO;
	}

	/**
	 * To aviso.
	 *
	 * @param avisoBean el aviso bean
	 * @return el aviso
	 */
	private Aviso toAviso(AvisoBean avisoBean) {
		Aviso avisoAux = new Aviso();
		EstadoAviso estadoAvisoAux =new EstadoAviso();
		estadoAvisoAux.setId(avisoBean.getIdEstadoAviso());
		if (avisoBean.getIdAviso()!=null){
			avisoAux.setId(avisoBean.getIdAviso());
		}
		avisoAux.setTexto(avisoBean.getTexto());
		avisoAux.setFechaInicio(avisoBean.getFechaInicio());
		avisoAux.setFechaFin(avisoBean.getFechaFin());
		avisoAux.setEstadoAviso(estadoAvisoAux);
		
		
		return avisoAux;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.AvisoManager#guardarAviso(es.map.ipsg.bean.AvisoBean)
	 */
	public int guardarAviso(AvisoBean avisoBean) {
		Aviso aviso;
		aviso = toAviso(avisoBean);
		int idAviso = avisoDAO.insert(aviso);
		
		return idAviso;
	}

	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.AvisoManager#actualizarAviso(es.map.ipsg.bean.AvisoBean)
	 */
	public void actualizarAviso(AvisoBean avisoBean) {
		Aviso aviso;
		aviso = toAviso(avisoBean);
		avisoDAO.update(aviso);
	}

	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.AvisoManager#obtenerAviso(java.lang.Integer)
	 */
	public AvisoBean obtenerAviso(Integer idAviso){
		Aviso alerta = avisoDAO.get(idAviso);
		return toAvisoBean(alerta);
	}
	
	/**
	 * To aviso bean.
	 *
	 * @param aviso el aviso
	 * @return el aviso bean
	 */
	public AvisoBean toAvisoBean(Aviso aviso){
		AvisoBean avisoBean = new AvisoBean();
		EstadoAviso estadoAvisoAux =new EstadoAviso();
		estadoAvisoAux.setId(aviso.getEstadoAviso().getId());
		
		avisoBean.setIdAviso(aviso.getId());
		avisoBean.setTexto(aviso.getTexto());
		avisoBean.setFechaInicio(aviso.getFechaInicio());
		avisoBean.setFechaFin(aviso.getFechaFin());
		avisoBean.setIdEstadoAviso(estadoAvisoAux.getId());
		avisoBean.setDesEstadoAviso(estadoAvisoAux.getDescripcion());
		
		
		return avisoBean;
	}

	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.AvisoManager#modificarAviso(es.map.ipsg.bean.AvisoBean)
	 */
	public void modificarAviso(AvisoBean avisoBean){
		Aviso aviso = toAviso(avisoBean);
		avisoDAO.update(aviso);
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