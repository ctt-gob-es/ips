package es.map.ipsg.manager;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import es.map.ips.common.exception.ApplicationException;
import es.map.ips.common.model.SearchResult;
import es.map.ips.dao.EstadoAvisoDAO;
import es.map.ips.model.EstadoAviso;
import es.map.ips.model.EstadoAvisoQuery;
import es.map.ipsg.bean.EstadoAvisoBean;




/**
 * El Class EstadoAvisoManagerImpl.
 */
public class EstadoAvisoManagerImpl implements EstadoAvisoManager {

	/** el estado aviso DAO. */
	//Variables
	private EstadoAvisoDAO estadoAvisoDAO;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(EstadoAvisoManagerImpl.class);
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.EstadoAvisoManager#buscarEstadoAvisoCombo(es.map.ips.model.EstadoAvisoQuery)
	 */
	public ArrayList<EstadoAvisoBean> buscarEstadoAvisoCombo(EstadoAvisoQuery estadoAvisoQuery){
		
		SearchResult<EstadoAviso> estadoAviso = buscarEstadoAvisos(estadoAvisoQuery);
		ArrayList<EstadoAvisoBean> arrEstadoAviso= new ArrayList<EstadoAvisoBean>();
		for(int i=0;i<estadoAviso.getResults().size();i++){
			EstadoAvisoBean aux;
			aux = toEstadoAvisoBean(estadoAviso.getResults().get(i));
			if(aux != null){
				arrEstadoAviso.add(aux);
			}
		}	
		return arrEstadoAviso;		
	}
	
	/**
	 * To estado aviso bean.
	 *
	 * @param estadoAviso el estado aviso
	 * @return el estado aviso bean
	 */
	private EstadoAvisoBean toEstadoAvisoBean(EstadoAviso estadoAviso) {
		int id = estadoAviso.getId();
		String descripcion = estadoAviso.getDescripcion();
		
		EstadoAvisoBean auxEstadoAviso = new EstadoAvisoBean();
		auxEstadoAviso.setIdEstadoAviso(id);
		auxEstadoAviso.setDescripcion(descripcion);
		
		return auxEstadoAviso;
	}

	
	/**
	 * Buscar estado avisos.
	 *
	 * @param estadoAvisoQuery el estado aviso query
	 * @return el search result
	 */
	private SearchResult<EstadoAviso> buscarEstadoAvisos(EstadoAvisoQuery estadoAvisoQuery) {
			ApplicationException.assertNotNull(estadoAvisoQuery, "convocatoriaQuery no puede ser null");
		
		return estadoAvisoDAO.search(estadoAvisoQuery);
	}

	/**
	 * Obtiene el estado aviso DAO.
	 *
	 * @return el estado aviso DAO
	 */
	public EstadoAvisoDAO getEstadoAvisoDAO() {
		return estadoAvisoDAO;
	}

	/**
	 * Establece el estado aviso DAO.
	 *
	 * @param estadoAvisoDAO el nuevo estado aviso DAO
	 */
	public void setEstadoAvisoDAO(EstadoAvisoDAO estadoAvisoDAO) {
		this.estadoAvisoDAO = estadoAvisoDAO;
	}


}	
	
