package es.map.ipsc.manager.logs;


import es.map.ips.dao.TipoServicioDAO;
import es.map.ips.model.TipoServicio;
import es.map.ips.model.TipoServicioQuery;

/**
 * El Class TipoServicioManagerImpl.
 */
public class TipoServicioManagerImpl implements TipoServicioManager {
	
	/** el tipo servicio DAO. */
	private TipoServicioDAO tipoServicioDAO;
	
	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.logs.TipoServicioManager#buscarTipoServicioUnique(es.map.ips.model.TipoServicioQuery)
	 */
	public TipoServicio buscarTipoServicioUnique(
			TipoServicioQuery tipoServicioQuery) {
		TipoServicio tipoServicio = tipoServicioDAO.searchUnique(tipoServicioQuery);
		if(tipoServicio != null){
			return tipoServicio;
		}
		return null;
	}

	/**
	 * Obtiene el tipo servicio DAO.
	 *
	 * @return el tipo servicio DAO
	 */
	public TipoServicioDAO getTipoServicioDAO() {
		return tipoServicioDAO;
	}

	/**
	 * Establece el tipo servicio DAO.
	 *
	 * @param tipoServicioDAO el nuevo tipo servicio DAO
	 */
	public void setTipoServicioDAO(TipoServicioDAO tipoServicioDAO) {
		this.tipoServicioDAO = tipoServicioDAO;
	}

	
}
