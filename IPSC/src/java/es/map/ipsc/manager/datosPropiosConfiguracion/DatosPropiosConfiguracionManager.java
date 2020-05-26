package es.map.ipsc.manager.datosPropiosConfiguracion;

import java.util.List;

import es.map.ips.model.DatosPropiosConfiguracion;
import es.map.ips.model.DatosPropiosConfiguracionQuery;

/**
 * El Interface DatosPropiosConfiguracionManager.
 */
public interface DatosPropiosConfiguracionManager {
	
	/**
	 * Buscar datos propios configby campo.
	 *
	 * @param datosPropiosConfigQuery el datos propios config query
	 * @return el list
	 */
	List<DatosPropiosConfiguracion> buscarDatosPropiosConfigbyCampo(DatosPropiosConfiguracionQuery datosPropiosConfigQuery);
}
