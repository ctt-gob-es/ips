package es.map.ipsg.manager;

import java.util.ArrayList;

import es.map.ips.common.exception.ApplicationException;
import es.map.ips.common.model.SearchResult;
import es.map.ips.dao.BatchIntermediacionAuxDAO;
import es.map.ips.model.BatchIntermediacionAux;
import es.map.ips.model.BatchIntermediacionAuxQuery;
import es.map.ipsg.bean.BatchIntermediacionBean;

/**
 * El Class BatchIntermediacionAuxManagerImpl.
 */
public class BatchIntermediacionAuxManagerImpl implements BatchIntermediacionAuxManager {

	/** el batch intermediacion aux DAO. */
	private BatchIntermediacionAuxDAO batchIntermediacionAuxDAO;
	
	
	/**
	 * Buscar peticion.
	 *
	 * @param BatchIntermediacionAuxQuery el batch intermediacion aux query
	 * @return el search result
	 */
	private SearchResult<BatchIntermediacionAux> buscarPeticion(BatchIntermediacionAuxQuery BatchIntermediacionAuxQuery) {
		ApplicationException.assertNotNull(BatchIntermediacionAuxQuery, "BatchIntermediacionAuxQuery no puede ser null");
	
		return batchIntermediacionAuxDAO.search(BatchIntermediacionAuxQuery);
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.BatchIntermediacionAuxManager#buscarPeticionesAll(es.map.ips.model.BatchIntermediacionAuxQuery)
	 */
	public ArrayList<BatchIntermediacionBean> buscarPeticionesAll(BatchIntermediacionAuxQuery BatchIntermediacionAuxQuery){
		SearchResult<BatchIntermediacionAux> peticion = buscarPeticion(BatchIntermediacionAuxQuery);
		
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
	private BatchIntermediacionBean toBatchIntermediacionBean(BatchIntermediacionAux intermediacion){

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
	 * @return el batch intermediacion aux
	 */
	private BatchIntermediacionAux toBatchIntermediacion(BatchIntermediacionBean intermediacionBean){

		BatchIntermediacionAux batchIntermediacion = new BatchIntermediacionAux();
		
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
	 * @see es.map.ipsg.manager.BatchIntermediacionAuxManager#actualizarIntermediacion(es.map.ipsg.bean.BatchIntermediacionBean, int)
	 */
	public void actualizarIntermediacion(BatchIntermediacionBean batchIntermediacionAux, int idConsulta){
		
		
		BatchIntermediacionAux batchIntermediacion = toBatchIntermediacion(batchIntermediacionAux);
		batchIntermediacion.setIdConsulta(idConsulta);
		batchIntermediacionAuxDAO.update(batchIntermediacion);
	}

	/**
	 * Obtiene el batch intermediacion aux DAO.
	 *
	 * @return el batch intermediacion aux DAO
	 */
	public BatchIntermediacionAuxDAO getBatchIntermediacionAuxDAO() {
		return batchIntermediacionAuxDAO;
	}

	/**
	 * Establece el batch intermediacion aux DAO.
	 *
	 * @param batchIntermediacionAuxDAO el nuevo batch intermediacion aux DAO
	 */
	public void setBatchIntermediacionAuxDAO(BatchIntermediacionAuxDAO batchIntermediacionAuxDAO) {
		this.batchIntermediacionAuxDAO = batchIntermediacionAuxDAO;
	}

	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.BatchIntermediacionAuxManager#guardarBatchIntermediacion(es.map.ipsg.bean.BatchIntermediacionBean)
	 */
	@Override
	public void guardarBatchIntermediacion(BatchIntermediacionBean batchIntermediacionBean) {
		
		BatchIntermediacionAux batchIntermediacion = toBatchIntermediacion(batchIntermediacionBean);
		batchIntermediacionAuxDAO.insert(batchIntermediacion);

		
	}

}
