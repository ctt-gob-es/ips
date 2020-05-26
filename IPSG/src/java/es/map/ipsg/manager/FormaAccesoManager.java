package es.map.ipsg.manager;

import java.util.ArrayList;

import es.map.ips.model.FormaAccesoQuery;
import es.map.ipsg.bean.FormaAccesoBean;


/**
 * El Interface FormaAccesoManager.
 */
public interface FormaAccesoManager {

	/**
	 * Buscar forma acceso combo.
	 *
	 * @param formaAccesoQuery el forma acceso query
	 * @return el array list
	 */
	public ArrayList<FormaAccesoBean> buscarFormaAccesoCombo(FormaAccesoQuery formaAccesoQuery);
	
	/**
	 * Buscar forma acceso all.
	 *
	 * @param formaAccesoQuery el forma acceso query
	 * @return el array list
	 */
	public ArrayList<FormaAccesoBean> buscarFormaAccesoAll(FormaAccesoQuery formaAccesoQuery);
	
	/**
	 * Buscar forma acceso combo visibilidad.
	 *
	 * @param formaAccesoQuery el forma acceso query
	 * @return el array list
	 */
	public ArrayList<FormaAccesoBean> buscarFormaAccesoComboVisibilidad(FormaAccesoQuery formaAccesoQuery);
	
	/**
	 * Guardar forma acceso.
	 *
	 * @param formaAccesoBean el forma acceso bean
	 * @return el integer
	 */
	public Integer guardarFormaAcceso(FormaAccesoBean formaAccesoBean);
	
	/**
	 * Obtener forma acceso.
	 *
	 * @param idFormaAcceso el id forma acceso
	 * @return el forma acceso bean
	 */
	public FormaAccesoBean obtenerFormaAcceso (Integer idFormaAcceso);
	
	/**
	 * Modificar forma acceso.
	 *
	 * @param formaAccesoBean el forma acceso bean
	 */
	public void modificarFormaAcceso(FormaAccesoBean formaAccesoBean);
}
