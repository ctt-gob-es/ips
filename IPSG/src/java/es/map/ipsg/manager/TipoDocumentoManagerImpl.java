package es.map.ipsg.manager;

import java.util.ArrayList;

import es.map.ips.common.exception.ApplicationException;
import es.map.ips.common.model.SearchResult;
import es.map.ips.dao.TipoDocumentoDAO;
import es.map.ips.model.TipoDocumento;
import es.map.ips.model.TipoDocumentoQuery;
import es.map.ipsg.bean.TipoDocumentoBean;


/**
 * El Class TipoDocumentoManagerImpl.
 */
public class TipoDocumentoManagerImpl implements TipoDocumentoManager {

	/** el tipo documento DAO. */
	//Variables
	private TipoDocumentoDAO tipoDocumentoDAO;
	
	/** el arr tipo documento. */
	private ArrayList<TipoDocumentoBean> arrTipoDocumento;
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.TipoDocumentoManager#buscarTipoDocumentoCombo(es.map.ips.model.TipoDocumentoQuery)
	 */
	public ArrayList<TipoDocumentoBean> buscarTipoDocumentoCombo(TipoDocumentoQuery tipoDocumentoQuery){
		
		SearchResult<TipoDocumento> tipoDocumento = buscarTipoDocumento(tipoDocumentoQuery);		
		arrTipoDocumento= new ArrayList<TipoDocumentoBean>();
		for(int i=0;i<tipoDocumento.getResults().size();i++){
			TipoDocumentoBean aux;
			aux = toTipoDocumentoComboBean(tipoDocumento.getResults().get(i));
			if(aux != null){
				arrTipoDocumento.add(aux);
			}
		}	
		return arrTipoDocumento;		
	}

	/**
	 * To tipo documento combo bean.
	 *
	 * @param tipoDocumento el tipo documento
	 * @return el tipo documento bean
	 */
	private TipoDocumentoBean toTipoDocumentoComboBean(TipoDocumento tipoDocumento) {
		
		TipoDocumentoBean auxTipoDocumento = new TipoDocumentoBean();
		auxTipoDocumento.setId(tipoDocumento.getId().toString());				
		auxTipoDocumento.setCodigo(tipoDocumento.getCodigo());
		auxTipoDocumento.setDescripcion(tipoDocumento.getDescripcion());
		
		return auxTipoDocumento;
	}
	
	/**
	 * Buscar tipo documento.
	 *
	 * @param tipoDocumentoQuery el tipo documento query
	 * @return el search result
	 */
	private SearchResult<TipoDocumento> buscarTipoDocumento(TipoDocumentoQuery tipoDocumentoQuery) {
			ApplicationException.assertNotNull(tipoDocumentoQuery, "tipoDocumentoQuery no puede ser null");
		
		return tipoDocumentoDAO.search(tipoDocumentoQuery);
	}

	/**
	 * Obtiene el tipo documento DAO.
	 *
	 * @return the tipoAccesoDAO
	 */
	public TipoDocumentoDAO getTipoDocumentoDAO() {
		return tipoDocumentoDAO;
	}

	/**
	 * Establece el tipo documento DAO.
	 *
	 * @param tipoDocumentoDAO el nuevo tipo documento DAO
	 */
	public void setTipoDocumentoDAO(TipoDocumentoDAO tipoDocumentoDAO) {
		this.tipoDocumentoDAO = tipoDocumentoDAO;
	}
}	
	
