package es.map.ipsc.manager.plantilla;

import es.map.ips.model.PlantillaQuery;
import es.map.ipsc.bean.PlantillaBean;


/**
 * El Interface PlantillaManager.
 */
public interface PlantillaManager {

	/**
	 * Buscar plantilla by id.
	 *
	 * @param plantillaQuery el plantilla query
	 * @return el plantilla bean
	 */
	PlantillaBean buscarPlantillaById(PlantillaQuery plantillaQuery);
	
	/**
	 * Buscar plantilla.
	 *
	 * @param plantillaQuery el plantilla query
	 * @return el plantilla bean
	 */
	PlantillaBean buscarPlantilla(PlantillaQuery plantillaQuery);

	
}
