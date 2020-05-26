package es.map.ipsc.manager.ministerio;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import es.map.ips.common.model.OrderType;
import es.map.ips.common.model.SearchResult;
import es.map.ips.dao.MinisterioDAO;
import es.map.ips.model.Ministerio;
import es.map.ips.model.MinisterioQuery;
import es.map.ipsc.Constantes;
import es.map.ipsc.bean.MinisterioBean;

/**
 * El Class MinisterioManagerImpl.
 */
public class MinisterioManagerImpl implements MinisterioManager {
	
	/** el ministerio DAO. */
	private MinisterioDAO ministerioDAO;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(MinisterioManagerImpl.class);
	
	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.ministerio.MinisterioManager#buscarMinisteriosCombo()
	 */
	public ArrayList<MinisterioBean> buscarMinisteriosCombo() {
		MinisterioQuery ministerioQuery = new MinisterioQuery();
		ministerioQuery.setEstado(Constantes.MINISTERIO_ESTADO_ACTIVO);
		ministerioQuery.setCalculateNumResults(true);
		ministerioQuery.addOrder(MinisterioQuery.DESCRIPCION, OrderType.ASC);
		
		ArrayList<MinisterioBean> list = new ArrayList<MinisterioBean>();
		SearchResult<Ministerio> ministerios = ministerioDAO.search(ministerioQuery);
		int numTotal = 0;
		for(int i=0;i<ministerios.getResults().size();i++){
			// Sólo se mostraran los registros visibles
			if(ministerios.getResults().get(i).getVisible() == 'S' || ministerios.getResults().get(i).getVisible() == 's')
			{
				MinisterioBean ministerioBean = new MinisterioBean();
				if(ministerios.getResults().get(i) != null){
					try{		
						ministerioBean = toMinisterioBean(ministerios.getResults().get(i),numTotal);	
					}catch(Exception e){
						logger.warn(e);
						logger.error("Error cargar ministerio",e);
					}

					list.add(ministerioBean);
				}
			}	
		}
		
		
		return list;
	}
	
	/**
	 * To ministerio bean.
	 *
	 * @param u el u
	 * @param numTotal el num total
	 * @return el ministerio bean
	 */
	private MinisterioBean toMinisterioBean(Ministerio u, int numTotal) {
		MinisterioBean aux = new MinisterioBean();
		
		aux.setId(u.getId());
		aux.setDescripcion(u.getDescripcion());
		aux.setCodigo(u.getCodigo());
		aux.setEstado(u.getEstado());
		aux.setSiglas(u.getSiglas());
		aux.setNumTotal(numTotal);
		aux.setUrl(u.getUrl());
		return aux;
	}
	
	/**
	 * To ministerio bean.
	 *
	 * @param u el u
	 * @return el ministerio bean
	 */
	private MinisterioBean toMinisterioBean(Ministerio u) {
		MinisterioBean aux = new MinisterioBean();
		
		aux.setId(u.getId());
		aux.setDescripcion(u.getDescripcion());
		aux.setCodigo(u.getCodigo());
		aux.setEstado(u.getEstado());
		aux.setSiglas(u.getSiglas());
		aux.setUrl(u.getUrl());
		return aux;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.ministerio.MinisterioManager#buscarMinisterioId(es.map.ips.model.MinisterioQuery)
	 */
	public MinisterioBean buscarMinisterioId(MinisterioQuery ministerioQuery) {
		Ministerio ministerioAux = ministerioDAO.searchUnique(ministerioQuery);
		MinisterioBean ministerioBean = null;
		if(ministerioAux != null){
			ministerioBean = toMinisterioBean(ministerioAux);
		}
		if(ministerioBean == null){
			return null;
		}
		return ministerioBean;
	}

	/**
	 * Obtiene el ministerio DAO.
	 *
	 * @return el ministerio DAO
	 */
	public MinisterioDAO getMinisterioDAO() {
		return ministerioDAO;
	}

	/**
	 * Establece el ministerio DAO.
	 *
	 * @param ministerioDAO el nuevo ministerio DAO
	 */
	public void setMinisterioDAO(MinisterioDAO ministerioDAO) {
		this.ministerioDAO = ministerioDAO;
	}

	

	
	
	
}
