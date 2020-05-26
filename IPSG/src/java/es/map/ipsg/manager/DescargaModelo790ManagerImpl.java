package es.map.ipsg.manager;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import es.map.ips.common.exception.ApplicationException;
import es.map.ips.common.model.SearchResult;
import es.map.ips.dao.DescargaModelo790DAO;
import es.map.ips.model.DescargaModelo790;
import es.map.ips.model.DescargaModelo790Query;
import es.map.ipsg.bean.DescargaModelo790Bean;


/**
 *  Clase que implementa el ConvocatoriasViewManager.
 *
 * @author amartinl
 */
public class DescargaModelo790ManagerImpl implements DescargaModelo790Manager {

	/** el descarga modelo 790 DAO. */
	//Variables
	private DescargaModelo790DAO descargaModelo790DAO;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(DescargaModelo790ManagerImpl.class);
	
	/**
	 * Buscar descarga modelo 790 combo.
	 *
	 * @param descargaModelo790Query DescargaModelo790Query
	 * @return arrDescargaModelo790 ArrayList
	 */
	public ArrayList<DescargaModelo790Bean> buscarDescargaModelo790Combo(DescargaModelo790Query descargaModelo790Query){
		SearchResult<DescargaModelo790> descargaModelo790 = buscarDescargaModelo790(descargaModelo790Query);
		ArrayList<DescargaModelo790Bean> arrDescargaModelo790= new ArrayList<DescargaModelo790Bean>();
		for(int i=0;i<descargaModelo790.getResults().size();i++){
			DescargaModelo790Bean aux;
			aux = toDescargaModelo790Bean(descargaModelo790.getResults().get(i));
			if(aux != null){
				arrDescargaModelo790.add(aux);
			}
		}	
		return arrDescargaModelo790;		
	}

	/**
	 * Buscar descarga modelo 790.
	 *
	 * @param descargaModelo790Query el descarga modelo 790 query
	 * @return el search result
	 */
	private SearchResult<DescargaModelo790> buscarDescargaModelo790(DescargaModelo790Query descargaModelo790Query) {
			ApplicationException.assertNotNull(descargaModelo790Query, "descargaModelo790Query no puede ser null");
		
		return descargaModelo790DAO.search(descargaModelo790Query);
	}

	
	/**
	 * Buscar descarga modelo 790 all.
	 *
	 * @param descargaModelo790Query DescargaModelo790Query
	 * @return arrDescargaModelo790 ArrayList
	 */
	public ArrayList<DescargaModelo790Bean> buscarDescargaModelo790All(DescargaModelo790Query descargaModelo790Query){		
		SearchResult<DescargaModelo790> descargaModelo790 = buscarDescargaModelo790(descargaModelo790Query);
		Integer numTotal = descargaModelo790.getNumResults();
		int iNumTotal = 0;
		if (numTotal != null){
			iNumTotal = numTotal.intValue();
		}
		ArrayList<DescargaModelo790Bean> arrDescargaModelo790 = new ArrayList<DescargaModelo790Bean>();
		for(int i=0;i<descargaModelo790.getResults().size();i++){
			DescargaModelo790Bean aux;
			aux = toDescargaModelo790Bean(descargaModelo790.getResults().get(i), iNumTotal);
			if(aux != null){
				arrDescargaModelo790.add(aux);
			}
		}	
		return arrDescargaModelo790;		
	}
	
	/**
	 * To descarga modelo 790 bean.
	 *
	 * @param descargaModelo790 el descarga modelo 790
	 * @return el descarga modelo 790 bean
	 */
	private DescargaModelo790Bean toDescargaModelo790Bean(DescargaModelo790 descargaModelo790) {
		
		DescargaModelo790Bean auxDescargaModelo790Bean = new DescargaModelo790Bean();
		
		if(descargaModelo790.getConvocatoria()!= null)
		{
			auxDescargaModelo790Bean.setConvocatoria(descargaModelo790.getConvocatoria());
		}
		if(descargaModelo790.getFecha()!= null)
		{
			auxDescargaModelo790Bean.setFecha(descargaModelo790.getFecha());
		}
		if(descargaModelo790.getId() != null)
		{
			auxDescargaModelo790Bean.setId(descargaModelo790.getId());
		}
		
		return auxDescargaModelo790Bean;
	}
	
	
	/**
	 * To descarga modelo 790 bean.
	 *
	 * @param descargaModelo790 el descarga modelo 790
	 * @param numTotal el num total
	 * @return el descarga modelo 790 bean
	 */
	private DescargaModelo790Bean toDescargaModelo790Bean(DescargaModelo790 descargaModelo790, int numTotal) {
	
		
		DescargaModelo790Bean auxDescargaModelo790Bean = new DescargaModelo790Bean();
		
		if(descargaModelo790.getConvocatoria() != null)
		{
			auxDescargaModelo790Bean.setConvocatoria(descargaModelo790.getConvocatoria());
		}
		if(descargaModelo790.getFecha() != null)
		{
			auxDescargaModelo790Bean.setFecha(descargaModelo790.getFecha());
		}
		if(descargaModelo790.getId() != null)
		{
			auxDescargaModelo790Bean.setId(descargaModelo790.getId());
		}
		
		return auxDescargaModelo790Bean;
	}

	/**
	 * Obtiene el descarga modelo 790 DAO.
	 *
	 * @return descargaModelo790DAO DescargaModelo790DAO
	 */
	public DescargaModelo790DAO getDescargaModelo790DAO() {
		return descargaModelo790DAO;
	}

	/**
	 * Establece el descarga modelo 790 DAO.
	 *
	 * @param descargaModelo790DAO DescargaModelo790DAO
	 */
	public void setDescargaModelo790DAO(DescargaModelo790DAO descargaModelo790DAO) {
		this.descargaModelo790DAO = descargaModelo790DAO;
	}


	/**
	 * Obtiene el logger.
	 *
	 * @return logger Logger
	 */
	public static Logger getLogger() {
		return logger;
	}

}
