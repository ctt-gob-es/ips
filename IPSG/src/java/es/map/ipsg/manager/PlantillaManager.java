package es.map.ipsg.manager;

import java.util.ArrayList;

import es.map.ips.model.Plantilla;
import es.map.ips.model.PlantillaQuery;
import es.map.ipsg.bean.PlantillaBean;

/**
 * El Interface PlantillaManager.
 */
public interface PlantillaManager {
		
	/**
	 * Buscar plantilla all.
	 *
	 * @param plantillaQuery el plantilla query
	 * @return el array list
	 */
	public ArrayList<PlantillaBean> buscarPlantillaAll(PlantillaQuery plantillaQuery);
	
	/**
	 * To plantilla.
	 *
	 * @param plantillaBean el plantilla bean
	 * @return el plantilla
	 */
	public Plantilla toPlantilla (PlantillaBean plantillaBean);
	
	/**
	 * Guardar plantilla.
	 *
	 * @param plantillaBean el plantilla bean
	 * @return el long
	 */
	public Long guardarPlantilla(PlantillaBean plantillaBean);
	
	/**
	 * Obtener plantilla.
	 *
	 * @param idPlantilla el id plantilla
	 * @return el plantilla bean
	 */
	public PlantillaBean obtenerPlantilla(Long idPlantilla);
	
	/**
	 * Actualizar plantilla.
	 *
	 * @param plantillaBean el plantilla bean
	 */
	public void actualizarPlantilla(PlantillaBean plantillaBean);
	
	/**
	 * Buscar plantilla.
	 *
	 * @param plantillaQuery el plantilla query
	 * @return el plantilla bean
	 */
	public PlantillaBean buscarPlantilla(PlantillaQuery plantillaQuery);
}
