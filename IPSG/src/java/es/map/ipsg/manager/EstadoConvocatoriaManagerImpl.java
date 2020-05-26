package es.map.ipsg.manager;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import es.map.ips.common.exception.ApplicationException;
import es.map.ips.common.model.SearchResult;
import es.map.ips.dao.EstadoConvocatoriaDAO;
import es.map.ips.model.EstadoConvocatoria;
import es.map.ips.model.EstadoConvocatoriaQuery;
import es.map.ipsg.bean.EstadoConvocatoriaBean;




/**
 * El Class EstadoConvocatoriaManagerImpl.
 */
public class EstadoConvocatoriaManagerImpl implements EstadoConvocatoriaManager {

	/** el estado convocatoria DAO. */
	//Variables
	private EstadoConvocatoriaDAO estadoConvocatoriaDAO;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(EstadoConvocatoriaManagerImpl.class);
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.EstadoConvocatoriaManager#buscarEstadoConvocatoriaCombo(es.map.ips.model.EstadoConvocatoriaQuery)
	 */
	public ArrayList<EstadoConvocatoriaBean> buscarEstadoConvocatoriaCombo(EstadoConvocatoriaQuery estadoConvocatoriaQuery){
		
		SearchResult<EstadoConvocatoria> estadoConvocatoria = buscarEstadoConvocatorias(estadoConvocatoriaQuery);
		ArrayList<EstadoConvocatoriaBean> arrEstadoConvocatoria= new ArrayList<EstadoConvocatoriaBean>();
		for(int i=0;i<estadoConvocatoria.getResults().size();i++){
			EstadoConvocatoriaBean aux;
			aux = toEstadoConvocatoriaBean(estadoConvocatoria.getResults().get(i));
			if(aux != null){
				arrEstadoConvocatoria.add(aux);
			}
		}	
		return arrEstadoConvocatoria;		
	}
	
	/**
	 * To estado convocatoria bean.
	 *
	 * @param estadoConvocatoria el estado convocatoria
	 * @return el estado convocatoria bean
	 */
	private EstadoConvocatoriaBean toEstadoConvocatoriaBean(EstadoConvocatoria estadoConvocatoria) {
		int id = estadoConvocatoria.getId();
		String descripcion = estadoConvocatoria.getDescripcion();
		
		EstadoConvocatoriaBean auxEstadoConvocatoria = new EstadoConvocatoriaBean();
		auxEstadoConvocatoria.setId(id);
		auxEstadoConvocatoria.setDescripcion(descripcion);
		
		return auxEstadoConvocatoria;
	}

	
	/**
	 * Buscar estado convocatorias.
	 *
	 * @param estadoConvocatoriaQuery el estado convocatoria query
	 * @return el search result
	 */
	private SearchResult<EstadoConvocatoria> buscarEstadoConvocatorias(EstadoConvocatoriaQuery estadoConvocatoriaQuery) {
			ApplicationException.assertNotNull(estadoConvocatoriaQuery, "convocatoriaQuery no puede ser null");
		
		return estadoConvocatoriaDAO.search(estadoConvocatoriaQuery);
	}

	/**
	 * Obtiene el estado convocatoria DAO.
	 *
	 * @return el estado convocatoria DAO
	 */
	public EstadoConvocatoriaDAO getEstadoConvocatoriaDAO() {
		return estadoConvocatoriaDAO;
	}

	/**
	 * Establece el estado convocatoria DAO.
	 *
	 * @param estadoConvocatoriaDAO el nuevo estado convocatoria DAO
	 */
	public void setEstadoConvocatoriaDAO(EstadoConvocatoriaDAO estadoConvocatoriaDAO) {
		this.estadoConvocatoriaDAO = estadoConvocatoriaDAO;
	}


}	
	
