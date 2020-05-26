package es.map.ipsc.manager.provincia;

import java.util.ArrayList;


import es.map.ips.common.model.OrderType;
import es.map.ips.common.model.SearchResult;
import es.map.ips.dao.ProvinciaDAO;
import es.map.ips.model.Provincia;
import es.map.ips.model.ProvinciaQuery;
import es.map.ipsc.Constantes;
import es.map.ipsc.bean.ProvinciaBean;

/**
 * El Class ProvinciaManagerImpl.
 */
public class ProvinciaManagerImpl implements ProvinciaManager {
	
	/** el provincia DAO. */
	private ProvinciaDAO provinciaDAO;

	
	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.provincia.ProvinciaManager#buscarProvinciasActivasCombo()
	 */
	public ArrayList<ProvinciaBean> buscarProvinciasActivasCombo() {
		ProvinciaQuery provinciaQuery = new ProvinciaQuery();
		provinciaQuery.setEstado(Constantes.PROVINCIA_ESTADO_ACTIVO);
		provinciaQuery.addOrder(ProvinciaQuery.DESCRIPCION, OrderType.ASC);
		provinciaQuery.addIdNotIn(Constantes.PROVINCIA_NO_APLICA);
		
		SearchResult<Provincia> provincia = provinciaDAO.search(provinciaQuery);
		ArrayList<ProvinciaBean> arrProvincia= new ArrayList<ProvinciaBean>();
		for(int i=0;i<provincia.getResults().size();i++){
			// Sólo se mostraran los registros visibles
			if(provincia.getResults().get(i).getVisible() == 'S' || provincia.getResults().get(i).getVisible() == 's')
			{	
				ProvinciaBean aux;
				aux = toProvinciaBean(provincia.getResults().get(i));
				if(aux != null){
					arrProvincia.add(aux);
				}
			}	
		}	
		return arrProvincia;
	}
	
	/**
	 * To provincia bean.
	 *
	 * @param provincia el provincia
	 * @return el provincia bean
	 */
	private ProvinciaBean toProvinciaBean(Provincia provincia) {
		int id = provincia.getId();
		String descripcion = provincia.getDescripcion();
		String codigo = provincia.getCodigo();
		char estado = provincia.getEstado();
		
		ProvinciaBean auxProvincia = new ProvinciaBean();
		auxProvincia.setId(id);
		auxProvincia.setDescripcion(descripcion);
		auxProvincia.setCodigo(codigo);
		auxProvincia.setEstado(estado);
		
		return auxProvincia;
	}

	
	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.provincia.ProvinciaManager#buscarProviciaId(es.map.ips.model.ProvinciaQuery)
	 */
	public ProvinciaBean buscarProviciaId(ProvinciaQuery provinciaQuery) {
		Provincia provinciaAux = provinciaDAO.searchUnique(provinciaQuery);
		if(provinciaAux == null){
			return null;
		}
		ProvinciaBean provinciaBean = toProvinciaBean(provinciaAux);
		if(provinciaBean == null){
			return null;
		}
		return provinciaBean;
	}
	
	/**
	 * Obtiene el provincia DAO.
	 *
	 * @return el provincia DAO
	 */
	public ProvinciaDAO getProvinciaDAO() {
		return provinciaDAO;
	}

	/**
	 * Establece el provincia DAO.
	 *
	 * @param provinciaDAO el nuevo provincia DAO
	 */
	public void setProvinciaDAO(ProvinciaDAO provinciaDAO) {
		this.provinciaDAO = provinciaDAO;
	}


	

	
	
	
}
