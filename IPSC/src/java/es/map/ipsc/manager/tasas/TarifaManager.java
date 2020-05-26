package es.map.ipsc.manager.tasas;

import es.map.ips.model.TarifaQuery;

/**
 * El Interface TarifaManager.
 */
public interface TarifaManager {
	
	/**
	 * M�todo que obtiene el importe de una tarifa.
	 *
	 * @param tarifaQuery el tarifa query
	 * @return el float
	 */
	Float buscarTarifaGeneral(TarifaQuery tarifaQuery);
	
	/**
	 * M�todo que obtiene el identificador de un grupo correspondiente 
	 * al nivel de una convocatoria.
	 *
	 * @param tarifaQuery el tarifa query
	 * @return el int
	 */
	int buscarGrupo(TarifaQuery tarifaQuery);
}
