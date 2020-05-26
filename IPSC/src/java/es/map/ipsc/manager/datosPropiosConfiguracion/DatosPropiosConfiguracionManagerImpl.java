package es.map.ipsc.manager.datosPropiosConfiguracion;


import java.util.List;

//import org.apache.log4j.Logger;

import es.map.ips.dao.DatosPropiosConfiguracionDAO;
import es.map.ips.model.DatosPropiosConfiguracion;
import es.map.ips.model.DatosPropiosConfiguracionQuery;



/**
 * El Class DatosPropiosConfiguracionManagerImpl.
 */
public class DatosPropiosConfiguracionManagerImpl implements DatosPropiosConfiguracionManager {

	/** el datos propios configuracion DAO. */
	//Variables
	private DatosPropiosConfiguracionDAO datosPropiosConfiguracionDAO;
//	private static final Logger logger = Logger.getLogger(DatosPropiosConfiguracionManagerImpl.class);
	
	
	/* (non-Javadoc)
 * @see es.map.ipsc.manager.datosPropiosConfiguracion.DatosPropiosConfiguracionManager#buscarDatosPropiosConfigbyCampo(es.map.ips.model.DatosPropiosConfiguracionQuery)
 */
@Override
	public List<DatosPropiosConfiguracion> buscarDatosPropiosConfigbyCampo(
			DatosPropiosConfiguracionQuery datosPropiosConfigQuery) {
		return datosPropiosConfiguracionDAO.search(datosPropiosConfigQuery).getResults();
	}


	/**
	 * Obtiene el datos propios configuracion DAO.
	 *
	 * @return el datos propios configuracion DAO
	 */
	public DatosPropiosConfiguracionDAO getDatosPropiosConfiguracionDAO() {
		return datosPropiosConfiguracionDAO;
	}


	/**
	 * Establece el datos propios configuracion DAO.
	 *
	 * @param datosPropiosConfiguracionDAO el nuevo datos propios configuracion DAO
	 */
	public void setDatosPropiosConfiguracionDAO(DatosPropiosConfiguracionDAO datosPropiosConfiguracionDAO) {
		this.datosPropiosConfiguracionDAO = datosPropiosConfiguracionDAO;
	}
	
	
}	
	
