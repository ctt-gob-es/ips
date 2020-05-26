package es.map.ipsc.manager.centroGestor;

import java.util.ArrayList;

import es.map.ips.common.model.OrderType;
import es.map.ips.common.model.SearchResult;
import es.map.ips.dao.CentroGestorDAO;
import es.map.ips.model.CentroGestor;
import es.map.ips.model.CentroGestorQuery;
import es.map.ipsc.Constantes;
import es.map.ipsc.bean.CentroGestorBean;


/**
 * El Class CentroGestorManagerImpl.
 */
public class CentroGestorManagerImpl implements CentroGestorManager {

	/** el centro gestor DAO. */
	private CentroGestorDAO centroGestorDAO;
	
	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.centroGestor.CentroGestorManager#buscarCentroGestoresCombo()
	 */
	public ArrayList<CentroGestorBean> buscarCentroGestoresCombo() {
		CentroGestorQuery centroGestorQuery = new CentroGestorQuery();
		centroGestorQuery.setEstado(Constantes.CENTROGESTOR_ESTADO_ACTIVO);
		centroGestorQuery.addOrder(CentroGestorQuery.DESCRIPCION, OrderType.ASC);
		SearchResult<CentroGestor> centroGestor = centroGestorDAO.search(centroGestorQuery);
		ArrayList<CentroGestorBean> arrCentroGestor= new ArrayList<CentroGestorBean>();
		for(int i=0;i<centroGestor.getResults().size();i++){
			// Sólo se mostrarán los registros visibles
			if(centroGestor.getResults().get(i).getVisible()== 'S' || centroGestor.getResults().get(i).getVisible()== 's')
			{	
				CentroGestorBean aux;
				aux = toCentroGestorBean(centroGestor.getResults().get(i),0);
				if(aux != null){
					arrCentroGestor.add(aux);
				}
			}	
		}	
		return arrCentroGestor;	
	}
	
	/**
	 * To centro gestor bean.
	 *
	 * @param centroGestor el centro gestor
	 * @param numTotal el num total
	 * @return el centro gestor bean
	 */
	private CentroGestorBean toCentroGestorBean(CentroGestor centroGestor, int numTotal) {		
		
		CentroGestorBean auxCentroGestor = new CentroGestorBean();
		auxCentroGestor.setId(centroGestor.getIdCentroGestor());
		auxCentroGestor.setSiglas(centroGestor.getSiglas());
		auxCentroGestor.setDescripcion(centroGestor.getDescripcion());
		auxCentroGestor.setCodigo(centroGestor.getCodigo());		
		auxCentroGestor.setEjercicio(centroGestor.getEjercicio());
		auxCentroGestor.setMinisterio(centroGestor.getMinisterio().getSiglas());
		auxCentroGestor.setNumTotal(numTotal);
		
		return auxCentroGestor;
	}
	
	/**
	 * To centro gestor bean.
	 *
	 * @param centroGestor el centro gestor
	 * @return el centro gestor bean
	 */
	private CentroGestorBean toCentroGestorBean(CentroGestor centroGestor) {		
		
		CentroGestorBean auxCentroGestor = new CentroGestorBean();
		auxCentroGestor.setId(centroGestor.getIdCentroGestor());
		auxCentroGestor.setSiglas(centroGestor.getSiglas());
		auxCentroGestor.setDescripcion(centroGestor.getDescripcion());
		auxCentroGestor.setCodigo(centroGestor.getCodigo());		
		auxCentroGestor.setEjercicio(centroGestor.getEjercicio());
		auxCentroGestor.setMinisterio(centroGestor.getMinisterio().getSiglas());
		
		return auxCentroGestor;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.centroGestor.CentroGestorManager#buscarCentroGestorId(es.map.ips.model.CentroGestorQuery)
	 */
	public CentroGestorBean buscarCentroGestorId(
			CentroGestorQuery centroGestorQuery) {
		CentroGestor centroGestorAux = centroGestorDAO.searchUnique(centroGestorQuery);
		if(centroGestorAux == null){
			return null;
		}
		CentroGestorBean centroGestorBean = toCentroGestorBean(centroGestorAux);
		return centroGestorBean;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.centroGestor.CentroGestorManager#buscarCentroGestorCodModel2(es.map.ips.model.CentroGestorQuery)
	 */
	public CentroGestor buscarCentroGestorCodModel2(
			CentroGestorQuery centroGestorQuery) {
		CentroGestor centroGestorAux = centroGestorDAO.searchUnique(centroGestorQuery);
		if(centroGestorAux != null){
			return centroGestorAux;
		}
		return null;
	}

	/**
	 * Obtiene el centro gestor DAO.
	 *
	 * @return el centro gestor DAO
	 */
	public CentroGestorDAO getCentroGestorDAO() {
		return centroGestorDAO;
	}

	/**
	 * Establece el centro gestor DAO.
	 *
	 * @param centroGestorDAO el nuevo centro gestor DAO
	 */
	public void setCentroGestorDAO(CentroGestorDAO centroGestorDAO) {
		this.centroGestorDAO = centroGestorDAO;
	}

	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.centroGestor.CentroGestorManager#buscarCentroGestorCodModel(es.map.ips.model.CentroGestorQuery)
	 */
	public SearchResult<CentroGestor> buscarCentroGestorCodModel(
			CentroGestorQuery centroGestorQuery) {
		SearchResult<CentroGestor> centroGestorAux = centroGestorDAO.search(centroGestorQuery);
		if(centroGestorAux != null){
			return centroGestorAux;
		}
		return null;
	}

}
