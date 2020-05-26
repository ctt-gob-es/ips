package es.map.ipsg.manager;

import java.util.ArrayList;

import es.map.ips.model.EspecialidadQuery;
import es.map.ipsg.bean.EspecialidadBean;

/**
 * El Interface EspecialidadManager.
 */
public interface EspecialidadManager {
	
	/**
	 * Buscar especialidad combo.
	 *
	 * @param especialidadQuery el especialidad query
	 * @return el array list
	 */
	public ArrayList<EspecialidadBean> buscarEspecialidadCombo(EspecialidadQuery especialidadQuery);
	
	/**
	 * Buscar especialidad combo visibilidad.
	 *
	 * @param especialidadQuery el especialidad query
	 * @return el array list
	 */
	public ArrayList<EspecialidadBean> buscarEspecialidadComboVisibilidad(EspecialidadQuery especialidadQuery);
	
	/**
	 * Guardar especialidad.
	 *
	 * @param especialidadBean el especialidad bean
	 * @return el integer
	 */
	public Integer guardarEspecialidad(EspecialidadBean especialidadBean);
	
	/**
	 * Buscar especialidad all.
	 *
	 * @param especialidadQuery el especialidad query
	 * @return el array list
	 */
	public ArrayList<EspecialidadBean> buscarEspecialidadAll(EspecialidadQuery especialidadQuery);
	
	/**
	 * Obtener especialidad.
	 *
	 * @param idEspecialidad el id especialidad
	 * @return el especialidad bean
	 */
	public EspecialidadBean obtenerEspecialidad(Integer idEspecialidad);
	
	/**
	 * Modificar especialidad.
	 *
	 * @param especialidadBean el especialidad bean
	 */
	public void modificarEspecialidad(EspecialidadBean especialidadBean);
	
	/**
	 * Buscar especialidad unique.
	 *
	 * @param especialidadQuery el especialidad query
	 * @return el especialidad bean
	 */
	public EspecialidadBean buscarEspecialidadUnique(
			EspecialidadQuery especialidadQuery);
}
