package es.map.ipsg.manager;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import es.map.ips.common.exception.ApplicationException;
import es.map.ips.common.model.SearchResult;
import es.map.ips.dao.EstadoSolicitudDAO;
import es.map.ips.model.EstadoSolicitud;
import es.map.ips.model.EstadoSolicitudQuery;
import es.map.ipsg.bean.EstadoSolicitudBean;

/**
 * El Class EstadoSolicitudManagerImpl.
 */
public class EstadoSolicitudManagerImpl implements EstadoSolicitudManager {

	/** el estado solicitud DAO. */
	//Variables	
	private EstadoSolicitudDAO estadoSolicitudDAO;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(EstadoSolicitudManagerImpl.class);
	
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.EstadoSolicitudManager#buscarEstadoSolicitudById(es.map.ips.model.EstadoSolicitudQuery)
	 */
	public EstadoSolicitudBean buscarEstadoSolicitudById(
			EstadoSolicitudQuery estadoSolicitudQuery) {
		EstadoSolicitud estado = estadoSolicitudDAO.searchUnique(estadoSolicitudQuery);
		if(estado !=  null){
			return toEstadoSolicitudBean(estado);
		}
		return null;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.EstadoSolicitudManager#buscarEstadoSolicitudByIdRegistro(es.map.ips.model.EstadoSolicitudQuery)
	 */
	public EstadoSolicitud buscarEstadoSolicitudByIdRegistro(
			EstadoSolicitudQuery estadoSolicitudQuery) {
		EstadoSolicitud estado = estadoSolicitudDAO.searchUnique(estadoSolicitudQuery);
		return estado;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.EstadoSolicitudManager#buscarEstadoSolicitudCombo(es.map.ips.model.EstadoSolicitudQuery)
	 */
	public ArrayList<EstadoSolicitudBean> buscarEstadoSolicitudCombo(EstadoSolicitudQuery estadoSolicitudQuery){		
		
		SearchResult<EstadoSolicitud> estadoSolicitud = buscarEstadoSolicitud(estadoSolicitudQuery);		
		 ArrayList<EstadoSolicitudBean> arrEstadoSolicitud= new ArrayList<EstadoSolicitudBean>();
		for(int i=0;i<estadoSolicitud.getResults().size();i++){
			EstadoSolicitudBean aux;
			aux = toEstadoSolicitudBean(estadoSolicitud.getResults().get(i),0);
			if(aux != null){
				arrEstadoSolicitud.add(aux);
			}
		}	
		return arrEstadoSolicitud;		
	}

	/**
	 * To estado solicitud bean.
	 *
	 * @param estadoSolicitud el estado solicitud
	 * @param numTotal el num total
	 * @return el estado solicitud bean
	 */
	private EstadoSolicitudBean toEstadoSolicitudBean(EstadoSolicitud estadoSolicitud, int numTotal) {
		
		EstadoSolicitudBean estadoSolicitudBean = new EstadoSolicitudBean();
		
		estadoSolicitudBean.setId(estadoSolicitud.getId());		
		estadoSolicitudBean.setDescripcion(estadoSolicitud.getDescripcion());
		estadoSolicitudBean.setTipoSolicitud(estadoSolicitud.getTipoSolicitud());		
		
		return estadoSolicitudBean;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.EstadoSolicitudManager#obtenerEstadoSolicitud(java.lang.Integer)
	 */
	public EstadoSolicitud obtenerEstadoSolicitud(Integer idEstadoSolicitud ){
		return (estadoSolicitudDAO.get(idEstadoSolicitud));
		 
	}

	/**
	 * Buscar estado solicitud.
	 *
	 * @param estadoSolicitudQuery el estado solicitud query
	 * @return el search result
	 */
	private SearchResult<EstadoSolicitud> buscarEstadoSolicitud(EstadoSolicitudQuery estadoSolicitudQuery) {
			ApplicationException.assertNotNull(estadoSolicitudQuery, "estadoSolicitudQuery no puede ser null");
		
		return estadoSolicitudDAO.search(estadoSolicitudQuery);
	}
	
	/**
	 * To estado solicitud bean.
	 *
	 * @param estadoSolicitud el estado solicitud
	 * @return el estado solicitud bean
	 */
	public EstadoSolicitudBean toEstadoSolicitudBean(EstadoSolicitud estadoSolicitud){
		EstadoSolicitudBean estadoSolicitudBean= new EstadoSolicitudBean();
		
		estadoSolicitudBean.setId(estadoSolicitud.getId());
		estadoSolicitudBean.setDescripcion(estadoSolicitud.getDescripcion());
		if(estadoSolicitudBean.getTipoSolicitud() != null){
			estadoSolicitudBean.setTipoSolicitud(estadoSolicitudBean.getTipoSolicitud());
		}
				
				
		return estadoSolicitudBean;
	}

	/**
	 * Obtiene el estado solicitud DAO.
	 *
	 * @return the estadoSolicitudDAO
	 */
	public EstadoSolicitudDAO getEstadoSolicitudDAO() {
		return estadoSolicitudDAO;
	}

	/**
	 * Establece el estado solicitud DAO.
	 *
	 * @param estadoSolicitudDAO the estadoSolicitudDAO to set
	 */
	public void setEstadoSolicitudDAO(EstadoSolicitudDAO estadoSolicitudDAO) {
		this.estadoSolicitudDAO = estadoSolicitudDAO;
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