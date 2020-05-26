package es.map.ipsg.manager;

import java.util.Date;

import es.map.ips.common.model.OrderType;
import es.map.ips.common.model.SearchResult;
import es.map.ips.dao.BatchMonitorDAO;
import es.map.ips.model.BatchMonitor;
import es.map.ips.model.BatchMonitorQuery;
import es.map.ipsg.util.Constantes;


/**
 * El Class BatchMonitorManagerImpl.
 */
public class BatchMonitorManagerImpl implements BatchMonitorManager {

	/** el batch monitor DAO. */
	private BatchMonitorDAO batchMonitorDAO;
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.BatchMonitorManager#buscarResultado()
	 */
	public Date buscarResultado() {
		
		Date fechaDesde = null;
		
		BatchMonitorQuery batchMonitorQuery = new BatchMonitorQuery();
		batchMonitorQuery.setResultado(Constantes.MONITOR_RESULTADO_OK);
		batchMonitorQuery.addOrder("fecha", OrderType.DESC);
		
		SearchResult<BatchMonitor> listBatchMonitor = batchMonitorDAO.search(batchMonitorQuery);
		
		//Ejecucion desde el ultimo resultado OK
		if (listBatchMonitor!=null && listBatchMonitor.getResults().size()>0 ) {
			BatchMonitor batchMonitor = listBatchMonitor.getResults().get(0);
		
			if(batchMonitor.getResultado() != null  && !("").equals(batchMonitor.getResultado())){
				fechaDesde = batchMonitor.getFecha();				
			} 
		}
		return fechaDesde;
	}

	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.BatchMonitorManager#guardarResultado(java.util.Date, java.lang.String, java.lang.String)
	 */
	public void guardarResultado(Date fecha, String resultado, String desResultado) {
		
		BatchMonitor resultadoProceso = new BatchMonitor();
		resultadoProceso.setFecha(fecha);
		resultadoProceso.setResultado(resultado);
		resultadoProceso.setDesResultado(desResultado);
		
		batchMonitorDAO.insert(resultadoProceso);
	}
	
	
	/**
	 * Obtiene el batch monitor DAO.
	 *
	 * @return el batch monitor DAO
	 */
	public BatchMonitorDAO getBatchMonitorDAO() {
		return batchMonitorDAO;
	}

	/**
	 * Establece el batch monitor DAO.
	 *
	 * @param batchMonitorDAO el nuevo batch monitor DAO
	 */
	public void setBatchMonitorDAO(
			BatchMonitorDAO batchMonitorDAO) {
		this.batchMonitorDAO = batchMonitorDAO;
	}
		


}
