package es.map.ipsc.manager.comunidades;

import java.util.ArrayList;

import es.map.ips.common.exception.ApplicationException;
import es.map.ips.common.model.OrderType;
import es.map.ips.common.model.SearchResult;
import es.map.ips.dao.ComunidadesDAO;
import es.map.ips.model.Comunidades;
import es.map.ips.model.ComunidadesQuery;
import es.map.ips.model.Provincia;
import es.map.ips.model.ProvinciaQuery;
import es.map.ipsc.bean.ComunidadesBean;
import es.map.ipsc.bean.ProvinciaBean;


/**
 * El Class ComunidadesManagerImpl.
 */
public class ComunidadesManagerImpl implements ComunidadesManager {

	/** el comunidades DAO. */
	private ComunidadesDAO comunidadesDAO;
	
    /* (non-Javadoc)
     * @see es.map.ipsc.manager.comunidades.ComunidadesManager#buscarComunidadesCombo(es.map.ips.model.ComunidadesQuery)
     */
    public ArrayList<ComunidadesBean> buscarComunidadesCombo(ComunidadesQuery comunidadesQuery){
				
		
		
		SearchResult<Comunidades> comunidades = buscarComunidades(comunidadesQuery);

		ArrayList<ComunidadesBean> arrComunidades = new ArrayList<ComunidadesBean>();
		for(int i=0;i<comunidades.getResults().size();i++){
			ComunidadesBean aux = toComunidadesBean(comunidades.getResults().get(i));
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
	 * @see es.map.ipsc.manager.comunidades.ComunidadesManager#buscarComunidadesId(es.map.ips.model.ComunidadesQuery)
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
