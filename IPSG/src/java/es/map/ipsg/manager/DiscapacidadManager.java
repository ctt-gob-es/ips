package es.map.ipsg.manager;


import java.util.ArrayList;

import es.map.ips.model.DiscapacidadQuery;
import es.map.ipsg.bean.DiscapacidadBean;




/**
 * El Interface DiscapacidadManager.
 */
public interface DiscapacidadManager {

	/**
	 * Buscar discapacidad combo.
	 *
	 * @param discapacidadQuery el discapacidad query
	 * @return el array list
	 */
	public ArrayList<DiscapacidadBean> buscarDiscapacidadCombo(DiscapacidadQuery discapacidadQuery);
	
	/**
	 * Buscar discapacidad combo visibilidad.
	 *
	 * @param discapacidadQuery el discapacidad query
	 * @return el array list
	 */
	public ArrayList<DiscapacidadBean> buscarDiscapacidadComboVisibilidad(DiscapacidadQuery discapacidadQuery);		
	
	/**
	 * Buscar discapacidad all.
	 *
	 * @param discapacidadQuery el discapacidad query
	 * @return el array list
	 */
	public ArrayList<DiscapacidadBean> buscarDiscapacidadAll(DiscapacidadQuery discapacidadQuery);
	
	/**
	 * Guardar discapacidad.
	 *
	 * @param discapacidadBean el discapacidad bean
	 * @return el integer
	 */
	public Integer guardarDiscapacidad(DiscapacidadBean discapacidadBean);
	
	/**
	 * Obtener discapacidad.
	 *
	 * @param idDiscapacidad el id discapacidad
	 * @return el discapacidad bean
	 */
	public DiscapacidadBean obtenerDiscapacidad(Integer idDiscapacidad);
	
	/**
	 * Modificar discapacidad.
	 *
	 * @param discapacidadBean el discapacidad bean
	 * @return el boolean
	 */
	public Boolean modificarDiscapacidad(DiscapacidadBean discapacidadBean);
	
	/**
	 * Eliminar discapacidad.
	 *
	 * @param discapacidadBean el discapacidad bean
	 * @return el boolean
	 */
	public Boolean eliminarDiscapacidad(DiscapacidadBean discapacidadBean);
}
