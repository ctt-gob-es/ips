package es.map.ipsg.manager;

import es.map.ips.model.TipoConsulta;
import es.map.ips.model.TipoConsultaQuery;

/**
 * El Interface TipoConsultaManager.
 */
public interface TipoConsultaManager {

	/**
	 * Buscar tipo consulta.
	 *
	 * @param tipoConsultaQuery el tipo consulta query
	 * @return el tipo consulta
	 */
	public TipoConsulta buscarTipoConsulta(TipoConsultaQuery tipoConsultaQuery);

}
