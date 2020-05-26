package es.map.ipsc.manager.logs;

import es.map.ips.model.TipoServicio;
import es.map.ips.model.TipoServicioQuery;

/**
 * El Interface TipoServicioManager.
 */
public interface TipoServicioManager {

	/**
	 * Buscar tipo servicio unique.
	 *
	 * @param tipoServicioQuery el tipo servicio query
	 * @return el tipo servicio
	 */
	TipoServicio buscarTipoServicioUnique(TipoServicioQuery tipoServicioQuery);



	
}
