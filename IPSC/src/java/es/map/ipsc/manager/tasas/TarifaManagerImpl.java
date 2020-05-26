package es.map.ipsc.manager.tasas;

import org.apache.log4j.Logger;
import es.map.ips.dao.TarifaDAO;
import es.map.ips.model.Tarifa;
import es.map.ips.model.TarifaQuery;

/**
 * El Class TarifaManagerImpl.
 */
public class TarifaManagerImpl implements TarifaManager {
	
	
	/** el tarifa DAO. */
	private TarifaDAO tarifaDAO;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(TarifaManagerImpl.class);

	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.tasas.TarifaManager#buscarTarifaGeneral(es.map.ips.model.TarifaQuery)
	 */
	@Override
	public Float buscarTarifaGeneral(TarifaQuery tarifaQuery) {
		Tarifa tarifa = tarifaDAO.searchUnique(tarifaQuery);
		return tarifa.getImporte();
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.tasas.TarifaManager#buscarGrupo(es.map.ips.model.TarifaQuery)
	 */
	@Override
	public int buscarGrupo(TarifaQuery tarifaQuery) {
		Tarifa tarifa = tarifaDAO.searchUnique(tarifaQuery);
		return tarifa.getGrupo().getId();
	}
	
	/**
	 * Obtiene el tarifa DAO.
	 *
	 * @return el tarifa DAO
	 */
	public TarifaDAO getTarifaDAO() {
		return tarifaDAO;
	}

	/**
	 * Establece el tarifa DAO.
	 *
	 * @param tarifaDAO el nuevo tarifa DAO
	 */
	public void setTarifaDAO(TarifaDAO tarifaDAO) {
		this.tarifaDAO = tarifaDAO;
	}

	/**
	 * Obtiene el logger.
	 *
	 * @return el logger
	 */
	public static Logger getLogger() {
		return logger;
	}

}	
	
