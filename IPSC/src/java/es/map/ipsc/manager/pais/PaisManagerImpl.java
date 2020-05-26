package es.map.ipsc.manager.pais;

import java.util.ArrayList;

import es.map.ips.common.model.OrderType;
import es.map.ips.common.model.SearchResult;
import es.map.ips.dao.PaisDAO;
import es.map.ips.model.Pais;
import es.map.ips.model.PaisQuery;
import es.map.ipsc.bean.PaisBean;

/**
 * El Class PaisManagerImpl.
 */
public class PaisManagerImpl implements PaisManager {
	
	/** el pais DAO. */
	private PaisDAO paisDAO;
	
	
	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.pais.PaisManager#buscarPaises()
	 */
	public ArrayList<PaisBean> buscarPaises(){
		PaisQuery paisQuery = new PaisQuery();
		paisQuery.addOrder(PaisQuery.DESCRIPCION, OrderType.ASC);
		SearchResult<Pais> lista= paisDAO.search(paisQuery);
		ArrayList<PaisBean> arrPaises = new ArrayList<PaisBean>();
		
		for(int i=0;i<lista.size();i++){
			arrPaises.add(toPaisBean(lista.getResults().get(i)));
		}	
		
		return arrPaises;
	}

	/**
	 * To pais bean.
	 *
	 * @param pais el pais
	 * @return el pais bean
	 */
	public PaisBean toPaisBean(Pais pais) {
		
		PaisBean result = new PaisBean();
		
		result.setId(pais.getId());
		result.setDescripcion(pais.getDescripcion());
		result.setCodigo(pais.getCodigo());
				
		return result;
	}

	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.pais.PaisManager#buscarPaisId(es.map.ips.model.PaisQuery)
	 */
	public PaisBean buscarPaisId(PaisQuery paisQuery) {
		Pais paisAux = paisDAO.searchUnique(paisQuery);
		PaisBean paisBean = toPaisBean(paisAux);
		if(paisBean == null){
			return null;
		}
		return paisBean;
	}
	
	/**
	 * Obtiene el pais DAO.
	 *
	 * @return el pais DAO
	 */
	public PaisDAO getPaisDAO() {
		return paisDAO;
	}

	/**
	 * Establece el pais DAO.
	 *
	 * @param paisDAO el nuevo pais DAO
	 */
	public void setPaisDAO(PaisDAO paisDAO) {
		this.paisDAO = paisDAO;
	}

	


}
