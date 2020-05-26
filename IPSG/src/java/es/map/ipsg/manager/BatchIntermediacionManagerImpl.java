package es.map.ipsg.manager;

import java.util.ArrayList;

import es.map.ips.common.exception.ApplicationException;
import es.map.ips.common.model.SearchResult;
import es.map.ips.dao.BatchIntermediacionDAO;
import es.map.ips.model.BatchIntermediacion;
import es.map.ips.model.BatchIntermediacionQuery;
import es.map.ipsg.bean.BatchIntermediacionBean;


/**
 * El Class BatchIntermediacionManagerImpl.
 */
public class BatchIntermediacionManagerImpl implements BatchIntermediacionManager {

	/** el batch intermediacion DAO. */
	private BatchIntermediacionDAO batchIntermediacionDAO;
	
	
	/**
	 * Buscar peticion.
	 *
	 * @param batchIntermediacionQuery el batch intermediacion query
	 * @return el search result
	 */
	private SearchResult<BatchIntermediacion> buscarPeticion(BatchIntermediacionQuery batchIntermediacionQuery) {
		ApplicationException.assertNotNull(batchIntermediacionQuery, "BatchIntermediacionQuery no puede ser null");
	
		return batchIntermediacionDAO.search(batchIntermediacionQuery);
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.BatchIntermediacionManager#buscarPeticionesAll(es.map.ips.model.BatchIntermediacionQuery)
	 */
	public ArrayList<BatchIntermediacionBean> buscarPeticionesAll(BatchIntermediacionQuery batchIntermediacionQuery){
		SearchResult<BatchIntermediacion> peticion = buscarPeticion(batchIntermediacionQuery);
		
		ArrayList<BatchIntermediacionBean> arrPeticionesIntermediacion = new ArrayList<BatchIntermediacionBean>();
		
		for(int i=0;i<peticion.size();i++){
			BatchIntermediacionBean aux;
			aux = toBatchIntermediacionBean(peticion.getResults().get(i));
			if(aux != null){
				arrPeticionesIntermediacion.add(aux);
			}
		}	
		return arrPeticionesIntermediacion;		
	}
	
	/**
	 * To batch intermediacion bean.
	 *
	 * @param intermediacion el intermediacion
	 * @return el batch intermediacion bean
	 */
	private BatchIntermediacionBean toBatchIntermediacionBean(BatchIntermediacion intermediacion){

		BatchIntermediacionBean auxIntermediacion = new BatchIntermediacionBean();
		
		if(intermediacion.getIdConsulta() != null){
			auxIntermediacion.setIdConsulta(intermediacion.getIdConsulta());
		}
		if(intermediacion.getIdPeticion() != null){
			auxIntermediacion.setIdPeticion(intermediacion.getIdPeticion());
		}
		
		if(intermediacion.getTipoConsulta() != null && intermediacion.getTipoConsulta().getIdTipoConsulta() !=null){
		auxIntermediacion.setIdTipoConsulta(intermediacion.getTipoConsulta());
		auxIntermediacion.setTipoConsulta(intermediacion.getTipoConsulta().getIdTipoConsulta());
		}
		if(intermediacion.getTipoConsulta().getCodConsulta() != null){
			auxIntermediacion.setIdCodConsulta(intermediacion.getTipoConsulta().getCodConsulta());
		}
		auxIntermediacion.setIdUsuarioConsulta(intermediacion.getUsuario());
		auxIntermediacion.setFhInicioConsulta(intermediacion.getFhInicioConsulta());
		auxIntermediacion.setFhFinConsulta(intermediacion.getFhFinConsulta());
		auxIntermediacion.setReintentos(intermediacion.getReintentos());
		auxIntermediacion.setRespuesta(intermediacion.isRespuesta());
		auxIntermediacion.setNumElementos(intermediacion.getNumElementos());
		
		//Traemos la informacion completa lazy
		intermediacion.getUsuario().getPerfil();
		
		return auxIntermediacion;
		
	}
	
	/**
	 * To batch intermediacion.
	 *
	 * @param intermediacionBean el intermediacion bean
	 * @return el batch intermediacion
	 */
	private BatchIntermediacion toBatchIntermediacion(BatchIntermediacionBean intermediacionBean){

		BatchIntermediacion batchIntermediacion = new BatchIntermediacion();
		
		if(intermediacionBean.getIdConsulta() != null){
			batchIntermediacion.setIdConsulta(intermediacionBean.getIdConsulta());
		}
		if(intermediacionBean.getIdPeticion() != null){
			batchIntermediacion.setIdPeticion(intermediacionBean.getIdPeticion());
		}
		
		batchIntermediacion.setTipoConsulta(intermediacionBean.getIdTipoConsulta());	
		batchIntermediacion.setUsuario(intermediacionBean.getIdUsuarioConsulta());
		batchIntermediacion.setFhInicioConsulta(intermediacionBean.getFhInicioConsulta());
		batchIntermediacion.setFhFinConsulta(intermediacionBean.getFhFinConsulta());
		batchIntermediacion.setReintentos(intermediacionBean.getReintentos());
		batchIntermediacion.setRespuesta(intermediacionBean.getRespuesta());
		batchIntermediacion.setNumElementos(intermediacionBean.getNumElementos());
		
		return batchIntermediacion;
		
	}
	
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.BatchIntermediacionManager#actualizarIntermediacion(es.map.ipsg.bean.BatchIntermediacionBean, int)
	 */
	public void actualizarIntermediacion(BatchIntermediacionBean batchIntermediacionAux, int idConsulta){
		
		
		BatchIntermediacion batchIntermediacion = toBatchIntermediacion(batchIntermediacionAux);
		batchIntermediacion.setIdConsulta(idConsulta);
		batchIntermediacionDAO.update(batchIntermediacion);
	}

	/**
	 * Obtiene el batch intermediacion DAO.
	 *
	 * @return el batch intermediacion DAO
	 */
	public BatchIntermediacionDAO getBatchIntermediacionDAO() {
		return batchIntermediacionDAO;
	}

	/**
	 * Establece el batch intermediacion DAO.
	 *
	 * @param batchIntermediacionDAO el nuevo batch intermediacion DAO
	 */
	public void setBatchIntermediacionDAO(
			BatchIntermediacionDAO batchIntermediacionDAO) {
		this.batchIntermediacionDAO = batchIntermediacionDAO;
	}
	
	

	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.BatchIntermediacionManager#guardarBatchIntermediacion(es.map.ipsg.bean.BatchIntermediacionBean)
	 */
	@Override
	public void guardarBatchIntermediacion(BatchIntermediacionBean batchIntermediacionBean) {
		
		BatchIntermediacion batchIntermediacion = toBatchIntermediacion(batchIntermediacionBean);
		batchIntermediacionDAO.insert(batchIntermediacion);

		
	}

}
