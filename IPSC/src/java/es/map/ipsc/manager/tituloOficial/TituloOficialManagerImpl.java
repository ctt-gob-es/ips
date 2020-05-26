package es.map.ipsc.manager.tituloOficial;

import java.util.ArrayList;

import es.map.ips.common.model.OrderType;
import es.map.ips.common.model.SearchResult;
import es.map.ips.dao.TituloOficialDAO;
import es.map.ips.model.TituloOficial;
import es.map.ips.model.TituloOficialQuery;
import es.map.ipsc.Constantes;
import es.map.ipsc.bean.TituloOficialBean;

/**
 * El Class TituloOficialManagerImpl.
 */
public class TituloOficialManagerImpl implements TituloOficialManager {
	
	/** el titulo oficial DAO. */
	private TituloOficialDAO tituloOficialDAO;
	
	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.tituloOficial.TituloOficialManager#buscarTituloOficialesCombo()
	 */
	public ArrayList<TituloOficialBean> buscarTituloOficialesCombo() {
		TituloOficialQuery tituloOficialQuery = new TituloOficialQuery();
		tituloOficialQuery.setEstado(Constantes.TITULO_ESTADO_ACTIVO);
		tituloOficialQuery.addOrder(TituloOficialQuery.DESCRIPCION, OrderType.ASC);
		SearchResult<TituloOficial> tituloOficial = tituloOficialDAO.search(tituloOficialQuery);
		ArrayList<TituloOficialBean> arrTituloOficial= new ArrayList<TituloOficialBean>();
		for(int i=0;i<tituloOficial.getResults().size();i++){
			// Sólo se mostrarán los registros visibles
			if(tituloOficial.getResults().get(i).getVisible() == 'S' || tituloOficial.getResults().get(i).getVisible() == 's')
			{	
				TituloOficialBean aux;
				aux = toTituloOficialComboBean(tituloOficial.getResults().get(i));
				if(aux != null){
					arrTituloOficial.add(aux);
				}
			}	
		}	
		return arrTituloOficial;
	}
	
	/**
	 * To titulo oficial combo bean.
	 *
	 * @param tituloOficial el titulo oficial
	 * @return el titulo oficial bean
	 */
	private TituloOficialBean toTituloOficialComboBean(TituloOficial tituloOficial) {
		int id = tituloOficial.getId();
		String descripcion = tituloOficial.getDescripcion();
		
		TituloOficialBean auxTituloOficial = new TituloOficialBean();
		auxTituloOficial.setId(id);
		auxTituloOficial.setDescripcion(descripcion);
		if(tituloOficial.getCodigo() != null){
			auxTituloOficial.setCodigo(tituloOficial.getCodigo());
		}
		
		return auxTituloOficial;
	}

	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.tituloOficial.TituloOficialManager#buscarTituloOficialId(es.map.ips.model.TituloOficialQuery)
	 */
	public TituloOficialBean buscarTituloOficialId(
			TituloOficialQuery tituloOficialQuery) {
		TituloOficial tituloAux = tituloOficialDAO.searchUnique(tituloOficialQuery);
		TituloOficialBean tituloBean = toTituloOficialComboBean(tituloAux);
		if(tituloBean == null){
			return null;
		}
		return tituloBean;
	}
	
	/**
	 * Obtiene el titulo oficial DAO.
	 *
	 * @return el titulo oficial DAO
	 */
	public TituloOficialDAO getTituloOficialDAO() {
		return tituloOficialDAO;
	}

	/**
	 * Establece el titulo oficial DAO.
	 *
	 * @param tituloOficialDAO el nuevo titulo oficial DAO
	 */
	public void setTituloOficialDAO(TituloOficialDAO tituloOficialDAO) {
		this.tituloOficialDAO = tituloOficialDAO;
	}

	
	
	
}
