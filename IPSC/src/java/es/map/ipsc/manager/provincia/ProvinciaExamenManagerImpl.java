package es.map.ipsc.manager.provincia;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import es.map.ips.common.model.OrderType;
import es.map.ips.common.model.SearchResult;
import es.map.ips.dao.ConvocatoriaDAO;
import es.map.ips.dao.ProvinciaExamenDAO;
import es.map.ips.model.ProvinciaExamen;
import es.map.ips.model.ProvinciaExamenQuery;
import es.map.ips.model.ProvinciaQuery;
import es.map.ipsc.Constantes;
import es.map.ipsc.bean.ProvinciaExamenBean;

/**
 * El Class ProvinciaExamenManagerImpl.
 */
public class ProvinciaExamenManagerImpl implements ProvinciaExamenManager {
	
	/** el provincia examen DAO. */
	private ProvinciaExamenDAO provinciaExamenDAO;
	
	
	
	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.provincia.ProvinciaExamenManager#buscarProvinciasExamenActivasCombo()
	 */
	public ArrayList<ProvinciaExamenBean> buscarProvinciasExamenActivasCombo() {
		ProvinciaExamenQuery provinciaExamenQuery = new ProvinciaExamenQuery();
		provinciaExamenQuery.setEstado(Constantes.PROVINCIA_ESTADO_ACTIVO);
		provinciaExamenQuery.addOrder(ProvinciaQuery.DESCRIPCION, OrderType.ASC);
		provinciaExamenQuery.addIdNotIn(Constantes.PROVINCIA_NO_APLICA);
		
		SearchResult<ProvinciaExamen> provinciaExamen = provinciaExamenDAO.search(provinciaExamenQuery);
		ArrayList<ProvinciaExamenBean> arrProvincia= new ArrayList<ProvinciaExamenBean>();
		for(int i=0;i<provinciaExamen.getResults().size();i++){
			// Sólo se mostraran los registros visibles
			if(provinciaExamen.getResults().get(i).getVisible() == 'S' || provinciaExamen.getResults().get(i).getVisible() == 's')
			{	
				ProvinciaExamenBean aux;
				aux = toProvinciaExamenBean(provinciaExamen.getResults().get(i));
				if(aux != null){
					arrProvincia.add(aux);
				}
			}	
		}	
		return arrProvincia;
	}
	
	/**
	 * To provincia examen bean.
	 *
	 * @param provinciaExamen el provincia examen
	 * @return el provincia examen bean
	 */
	private ProvinciaExamenBean toProvinciaExamenBean(ProvinciaExamen provinciaExamen) {
		int id = provinciaExamen.getId();
		String descripcion = provinciaExamen.getDescripcion();
		String codigo = provinciaExamen.getCodigo();
		char estado = provinciaExamen.getEstado();
		
		ProvinciaExamenBean auxProvinciaExamen = new ProvinciaExamenBean();
		auxProvinciaExamen.setId(id);
		auxProvinciaExamen.setDescripcion(descripcion);
		auxProvinciaExamen.setCodigo(codigo);
		auxProvinciaExamen.setEstado(estado);
		
		return auxProvinciaExamen;
	}

	
	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.provincia.ProvinciaExamenManager#buscarProviciaExamenId(es.map.ips.model.ProvinciaExamenQuery)
	 */
	public ProvinciaExamenBean buscarProviciaExamenId(ProvinciaExamenQuery provinciaExamenQuery) {
		ProvinciaExamen provinciaExamenAux = provinciaExamenDAO.searchUnique(provinciaExamenQuery);
		if(provinciaExamenAux == null){
			return null;
		}
		ProvinciaExamenBean provinciaExamenBean = toProvinciaExamenBean(provinciaExamenAux);
		if(provinciaExamenBean == null){
			return null;
		}
		return provinciaExamenBean;
	}
	
	/**
	 * Obtiene el provincia examen DAO.
	 *
	 * @return el provincia examen DAO
	 */
	public ProvinciaExamenDAO getProvinciaExamenDAO() {
		return provinciaExamenDAO;
	}

	/**
	 * Establece el provincia examen DAO.
	 *
	 * @param provinciaExamenDAO el nuevo provincia examen DAO
	 */
	public void setProvinciaExamenDAO(ProvinciaExamenDAO provinciaExamenDAO) {
		this.provinciaExamenDAO = provinciaExamenDAO;
	}


	

	
	
	
}
