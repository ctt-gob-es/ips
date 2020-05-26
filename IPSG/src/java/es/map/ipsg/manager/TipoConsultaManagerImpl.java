package es.map.ipsg.manager;

import es.map.ips.dao.TipoConsultaDAO;
import es.map.ips.model.TipoConsulta;
import es.map.ips.model.TipoConsultaQuery;


/**
 * El Class TipoConsultaManagerImpl.
 */
public class TipoConsultaManagerImpl implements TipoConsultaManager {

	/** el tipo consulta DAO. */
	private TipoConsultaDAO tipoConsultaDAO;
	
	/** el id tipo consulta. */
	private Integer idTipoConsulta= null;

	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.TipoConsultaManager#buscarTipoConsulta(es.map.ips.model.TipoConsultaQuery)
	 */
	@Override
	public TipoConsulta buscarTipoConsulta(TipoConsultaQuery tipoConsultaQuery) {
		
		TipoConsulta tipoConsulta = null;

		
		if(tipoConsultaQuery != null){	
			tipoConsulta = tipoConsultaDAO.searchUnique(tipoConsultaQuery);
		}
				
		// TODO Auto-generated method stub
		return tipoConsulta;
	}

	/**
	 * Obtiene el tipo consulta DAO.
	 *
	 * @return el tipo consulta DAO
	 */
	public TipoConsultaDAO getTipoConsultaDAO() {
		return tipoConsultaDAO;
	}

	/**
	 * Establece el tipo consulta DAO.
	 *
	 * @param tipoConsultaDAO el nuevo tipo consulta DAO
	 */
	public void setTipoConsultaDAO(TipoConsultaDAO tipoConsultaDAO) {
		this.tipoConsultaDAO = tipoConsultaDAO;
	}
	
	
}