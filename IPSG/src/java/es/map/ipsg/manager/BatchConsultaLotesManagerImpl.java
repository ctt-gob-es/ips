package es.map.ipsg.manager;

import java.util.Date;

import es.map.ips.common.model.OrderType;
import es.map.ips.common.model.SearchResult;
import es.map.ips.dao.BatchConsultaLotesDAO;
import es.map.ips.model.BatchConsultaLotes;
import es.map.ips.model.BatchConsultaLotesQuery;
import es.map.ipsg.util.Constantes;


/**
 * El Class BatchConsultaLotesManagerImpl.
 */
public class BatchConsultaLotesManagerImpl implements BatchConsultaLotesManager {

	/** el batch consulta lotes DAO. */
	private BatchConsultaLotesDAO batchConsultaLotesDAO;
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.BatchConsultaLotesManager#buscarResultado()
	 */
	public Date buscarResultado() {
		
		Date fechaDesde = null;
		
		BatchConsultaLotesQuery BatchConsultaLotesQuery = new BatchConsultaLotesQuery();
		BatchConsultaLotesQuery.setResultado(Constantes.MONITOR_RESULTADO_OK);
		BatchConsultaLotesQuery.addOrder("fechaUltimaEjecucion", OrderType.DESC);
		
		SearchResult<BatchConsultaLotes> listBatchConsultaLotes = batchConsultaLotesDAO.search(BatchConsultaLotesQuery);
		
		//Ejecucion desde el ultimo resultado OK
		if (listBatchConsultaLotes!=null && listBatchConsultaLotes.getResults().size()>0 ) {
			BatchConsultaLotes BatchConsultaLotes = listBatchConsultaLotes.getResults().get(0);
		
			if(BatchConsultaLotes.getResultado() != null  && !("").equals(BatchConsultaLotes.getResultado())){
				fechaDesde = BatchConsultaLotes.getFechaUltimaEjecucion();
			} 
		}
		return fechaDesde;
	}

	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.BatchConsultaLotesManager#guardarResultado(java.util.Date, java.lang.String, java.lang.String)
	 */
	public void guardarResultado(Date fecha, String resultado, String desResultado) {
		
		BatchConsultaLotes resultadoProceso = new BatchConsultaLotes();
		resultadoProceso.setFechaUltimaEjecucion(fecha);
		resultadoProceso.setResultado(resultado);
		resultadoProceso.setDesResultado(desResultado);
		
		batchConsultaLotesDAO.insert(resultadoProceso);
	}
	
	
	/**
	 * Obtiene el batch consulta lotes DAO.
	 *
	 * @return el batch consulta lotes DAO
	 */
	public BatchConsultaLotesDAO getBatchConsultaLotesDAO() {
		return batchConsultaLotesDAO;
	}

	/**
	 * Establece el batch consulta lotes DAO.
	 *
	 * @param batchConsultaLotesDAO el nuevo batch consulta lotes DAO
	 */
	public void setBatchConsultaLotesDAO(
			BatchConsultaLotesDAO batchConsultaLotesDAO) {
		this.batchConsultaLotesDAO = batchConsultaLotesDAO;
	}
		


}
