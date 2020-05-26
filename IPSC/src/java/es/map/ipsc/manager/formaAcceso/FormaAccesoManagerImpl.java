package es.map.ipsc.manager.formaAcceso;

import java.util.ArrayList;

import es.map.ips.common.model.OrderType;
import es.map.ips.common.model.SearchResult;
import es.map.ips.dao.FormaAccesoDAO;
import es.map.ips.model.FormaAcceso;
import es.map.ips.model.FormaAccesoQuery;
import es.map.ipsc.Constantes;
import es.map.ipsc.bean.FormaAccesoBean;

/**
 * El Class FormaAccesoManagerImpl.
 */
public class FormaAccesoManagerImpl implements FormaAccesoManager {
	
	/** el forma acceso DAO. */
	private FormaAccesoDAO formaAccesoDAO;
	
	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.formaAcceso.FormaAccesoManager#buscarFormaAccesoCombo()
	 */
	public ArrayList<FormaAccesoBean> buscarFormaAccesoCombo() {
		FormaAccesoQuery formaAccesoQuery = new FormaAccesoQuery();
		formaAccesoQuery.setEstado(Constantes.FORMAACCESO_ESTADO_ACTIVO);
		formaAccesoQuery.addOrder(FormaAccesoQuery.DESCRIPCION, OrderType.ASC);
		SearchResult<FormaAcceso> formaAcceso = formaAccesoDAO.search(formaAccesoQuery);
		ArrayList<FormaAccesoBean> arrFormaAcceso= new ArrayList<FormaAccesoBean>();
		for(int i=0;i<formaAcceso.getResults().size();i++){
			// Sólo se mostrarán los registros visibles
			if(formaAcceso.getResults().get(i).getVisible() == 'S' || formaAcceso.getResults().get(i).getVisible() == 's')
			{
				FormaAccesoBean aux;
				aux = toFormaAccesoBean(formaAcceso.getResults().get(i));
				if(aux != null){
					arrFormaAcceso.add(aux);
				}
			}
		}	
		return arrFormaAcceso;
	}
	
	/**
	 * To forma acceso bean.
	 *
	 * @param formaAcceso el forma acceso
	 * @return el forma acceso bean
	 */
	private FormaAccesoBean toFormaAccesoBean(FormaAcceso formaAcceso) {
		int id = formaAcceso.getId();
		String descripcion = formaAcceso.getDescripcion();
		
		FormaAccesoBean auxFormaAcceso = new FormaAccesoBean();
		auxFormaAcceso.setId(id);
		auxFormaAcceso.setDescripcion(descripcion);
		auxFormaAcceso.setCodigo(formaAcceso.getCodigo());
		
		return auxFormaAcceso;
	}

	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.formaAcceso.FormaAccesoManager#buscarFormaAccesoId(es.map.ips.model.FormaAccesoQuery)
	 */
	public FormaAccesoBean buscarFormaAccesoId(FormaAccesoQuery formaAccesoQuery) {
		FormaAcceso formaAccesoAux = formaAccesoDAO.searchUnique(formaAccesoQuery);
		FormaAccesoBean formaAccesoBean = toFormaAccesoBean(formaAccesoAux);
		if(formaAccesoBean == null){
			return null;
		}
		return formaAccesoBean;
	}
	
	/**
	 * Obtiene el forma acceso DAO.
	 *
	 * @return el forma acceso DAO
	 */
	public FormaAccesoDAO getFormaAccesoDAO() {
		return formaAccesoDAO;
	}

	/**
	 * Establece el forma acceso DAO.
	 *
	 * @param formaAccesoDAO el nuevo forma acceso DAO
	 */
	public void setFormaAccesoDAO(FormaAccesoDAO formaAccesoDAO) {
		this.formaAccesoDAO = formaAccesoDAO;
	}

	
	
	
}
