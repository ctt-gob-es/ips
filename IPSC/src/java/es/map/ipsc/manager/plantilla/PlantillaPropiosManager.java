package es.map.ipsc.manager.plantilla;

import java.util.ArrayList;
import java.util.List;

import es.map.ips.model.PlantillaPropiosQuery;
import es.map.ipsc.bean.PlantillaBean;
import es.map.ipsc.bean.PlantillaPropiosBean;

/**
 * El Interface PlantillaPropiosManager.
 */
public interface PlantillaPropiosManager {

	/**
	 * Buscar plantilla propios all.
	 *
	 * @param plantillaPropiosQuery el plantilla propios query
	 * @return el array list
	 */
	ArrayList<PlantillaPropiosBean> buscarPlantillaPropiosAll(PlantillaPropiosQuery plantillaPropiosQuery);

	/**
	 * Guardar plantilla propios.
	 *
	 * @param plantillaPropiosBean el plantilla propios bean
	 * @return el long
	 */
	Long guardarPlantillaPropios(PlantillaPropiosBean plantillaPropiosBean);

	/**
	 * Borrar plantillas propios.
	 *
	 * @param plantillaPropiosQuery el plantilla propios query
	 */
	void borrarPlantillasPropios(PlantillaPropiosQuery plantillaPropiosQuery);

	/**
	 * Obtener plantilla propios by id.
	 */
	void obtenerPlantillaPropiosById();

	/**
	 * Obtener plantilla propios by id.
	 *
	 * @param plantillaPropiosQuery el plantilla propios query
	 * @return el array list
	 */
	ArrayList<PlantillaPropiosBean> obtenerPlantillaPropiosById(PlantillaPropiosQuery plantillaPropiosQuery);

	/**
	 * Actualizar plantilla propios.
	 *
	 * @param plantillaPropiosBean el plantilla propios bean
	 */
	void actualizarPlantillaPropios(PlantillaPropiosBean plantillaPropiosBean);

	/**
	 * Buscar plantilla propios.
	 *
	 * @param plantillaQuery el plantilla query
	 * @return el list
	 */
	List<PlantillaPropiosBean> buscarPlantillaPropios(PlantillaPropiosQuery plantillaQuery);

}
