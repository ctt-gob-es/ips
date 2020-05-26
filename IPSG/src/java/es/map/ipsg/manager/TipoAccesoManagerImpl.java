package es.map.ipsg.manager;

import java.util.ArrayList;

import es.map.ips.common.exception.ApplicationException;
import es.map.ips.common.model.SearchResult;
import es.map.ips.dao.TipoAccesoDAO;
import es.map.ips.model.TipoAcceso;
import es.map.ips.model.TipoAccesoQuery;
import es.map.ipsg.bean.TipoAccesoBean;



/**
 * El Class TipoAccesoManagerImpl.
 */
public class TipoAccesoManagerImpl implements TipoAccesoManager {

	/** el tipo acceso DAO. */
	//Variables
	private TipoAccesoDAO tipoAccesoDAO;
	
	/** el arr tipo acceso. */
	private ArrayList<TipoAccesoBean> arrTipoAcceso;
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.TipoAccesoManager#buscarTipoAccesoCombo(es.map.ips.model.TipoAccesoQuery)
	 */
	public ArrayList<TipoAccesoBean> buscarTipoAccesoCombo(TipoAccesoQuery tipoAccesoQuery){
		
		SearchResult<TipoAcceso> tipoAcceso= buscarTipoAcceso(tipoAccesoQuery);		
		arrTipoAcceso= new ArrayList<TipoAccesoBean>();
		for(int i=0;i<tipoAcceso.getResults().size();i++){
			TipoAccesoBean aux;
			aux = toTipoAccesoComboBean(tipoAcceso.getResults().get(i));
			if(aux != null){
				arrTipoAcceso.add(aux);
			}
		}	
		return arrTipoAcceso;		
	}

	/**
	 * To tipo acceso combo bean.
	 *
	 * @param tipoAcceso el tipo acceso
	 * @return el tipo acceso bean
	 */
	private TipoAccesoBean toTipoAccesoComboBean(TipoAcceso tipoAcceso) {
		
		TipoAccesoBean auxTipoAcceso = new TipoAccesoBean();
		auxTipoAcceso.setId(tipoAcceso.getId());				
		auxTipoAcceso.setCodigo(tipoAcceso.getCodigo());				
		
		return auxTipoAcceso;
	}
	
	/**
	 * Buscar tipo acceso.
	 *
	 * @param tipoAccesoQuery el tipo acceso query
	 * @return el search result
	 */
	private SearchResult<TipoAcceso> buscarTipoAcceso(TipoAccesoQuery tipoAccesoQuery) {
			ApplicationException.assertNotNull(tipoAccesoQuery, "tipoAccesoQuery no puede ser null");
		
		return tipoAccesoDAO.search(tipoAccesoQuery);
	}

	/**
	 * Obtiene el tipo acceso DAO.
	 *
	 * @return the tipoAccesoDAO
	 */
	public TipoAccesoDAO getTipoAccesoDAO() {
		return tipoAccesoDAO;
	}

	/**
	 * Establece el tipo acceso DAO.
	 *
	 * @param tipoAccesoDAO the tipoAccesoDAO to set
	 */
	public void setTipoAccesoDAO(TipoAccesoDAO tipoAccesoDAO) {
		this.tipoAccesoDAO = tipoAccesoDAO;
	}
}	
	
