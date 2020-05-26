package es.map.ipsg.manager;

import java.util.ArrayList;

import es.map.ips.common.exception.ApplicationException;
import es.map.ips.common.model.OrderType;
import es.map.ips.common.model.SearchResult;
import es.map.ips.dao.ComunidadesDAO;
import es.map.ips.model.Comunidades;
import es.map.ips.model.ComunidadesQuery;
import es.map.ipsg.bean.ComunidadesBean;



/**
 * El Class ComunidadesManagerImpl.
 */
public class ComunidadesManagerImpl implements ComunidadesManager {

	/** el comunidades DAO. */
	private ComunidadesDAO comunidadesDAO;
	
    /* (non-Javadoc)
     * @see es.map.ipsg.manager.ComunidadesManager#buscarComunidadesCombo(es.map.ips.model.ComunidadesQuery)
     */
    public ArrayList<ComunidadesBean> buscarComunidadesCombo(ComunidadesQuery comunidadesQuery){
				
		int numTotal = 0;
		
		SearchResult<Comunidades> comunidades = buscarComunidades(comunidadesQuery);
		if(comunidades.size()>0){
			numTotal = comunidades.size();
		}
		ArrayList<ComunidadesBean> arrComunidades = new ArrayList<ComunidadesBean>();
		for(int i=0;i<comunidades.getResults().size();i++){
			ComunidadesBean aux;
			aux = toComunidadesBean(comunidades.getResults().get(i));
			if(aux != null){
				arrComunidades.add(aux);
			}
		}	

		return arrComunidades;		
	}
	
	
	/**
	 * Buscar comunidades.
	 *
	 * @param comunidadesQuery el comunidades query
	 * @return el search result
	 */
	private SearchResult<Comunidades> buscarComunidades(ComunidadesQuery comunidadesQuery) {
		ApplicationException.assertNotNull(comunidadesQuery, "comunidadesQuery no puede ser null");		
		comunidadesQuery.addOrder("descripcion", OrderType.ASC);
	return comunidadesDAO.search(comunidadesQuery);
}
	
	/**
	 * To comunidades bean.
	 *
	 * @param u el u
	 * @return el comunidades bean
	 */
	private ComunidadesBean toComunidadesBean(Comunidades u) {
		ComunidadesBean aux = new ComunidadesBean();
		aux.setIdComunidad(u.getIdComunidad());
		aux.setDescripcion(u.getDescripcion());
		aux.setCodigo(u.getCodigo());	
		aux.setServicioDD(u.isServicioDiscapacidad());
		aux.setServicioFN(u.isServicioFamnumerosa());
		aux.setTituloReq(u.isTituloRequerido());
		return aux;
	}

	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.ComunidadesManager#buscarComunidadesId(es.map.ips.model.ComunidadesQuery)
	 */
	public ComunidadesBean buscarComunidadesId(ComunidadesQuery comunidaesQuery) {
		Comunidades comunidadAux = comunidadesDAO.searchUnique(comunidaesQuery);
		if(comunidadAux == null){
			return null;
		}
		ComunidadesBean comunidadesBean = toComunidadesBean(comunidadAux);
		if(comunidadesBean == null){
			return null;
		}
		return comunidadesBean;
	}
	

	/**
	 * Obtiene el comunidades DAO.
	 *
	 * @return el comunidades DAO
	 */
	public ComunidadesDAO getComunidadesDAO() {
		return comunidadesDAO;
	}

	/**
	 * Establece el comunidades DAO.
	 *
	 * @param comunidadesDAO el nuevo comunidades DAO
	 */
	public void setComunidadesDAO(ComunidadesDAO comunidadesDAO) {
		this.comunidadesDAO = comunidadesDAO;
	}

}
