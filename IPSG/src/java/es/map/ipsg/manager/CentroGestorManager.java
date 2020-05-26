package es.map.ipsg.manager;

import java.util.ArrayList;

import es.map.ips.model.CentroGestor;
import es.map.ips.model.CentroGestorQuery;
import es.map.ipsg.bean.CentroGestorBean;


/**
 * El Interface CentroGestorManager.
 */
public interface CentroGestorManager {

	/**
	 * Buscar centro gestor combo.
	 *
	 * @param centroGestorQuery el centro gestor query
	 * @return el array list
	 */
	public ArrayList<CentroGestorBean> buscarCentroGestorCombo(CentroGestorQuery centroGestorQuery);
	
	/**
	 * Buscar centro gestor combo todos.
	 *
	 * @return el array list
	 */
	public ArrayList<CentroGestorBean> buscarCentroGestorComboTodos();
	
	/**
	 * Buscar centro gestor combo visibilidad.
	 *
	 * @param centroGestorQuery el centro gestor query
	 * @return el array list
	 */
	public ArrayList<CentroGestorBean> buscarCentroGestorComboVisibilidad(CentroGestorQuery centroGestorQuery);
	
	/**
	 * Guardar centro gestor.
	 *
	 * @param centroGestorBean el centro gestor bean
	 * @return el integer
	 */
	public Integer guardarCentroGestor(CentroGestorBean centroGestorBean);
	
	/**
	 * Buscar centro gestor all.
	 *
	 * @param centroGestorQuery el centro gestor query
	 * @return el array list
	 */
	public ArrayList<CentroGestorBean> buscarCentroGestorAll(CentroGestorQuery centroGestorQuery);
	
	/**
	 * Buscar centros gestores all.
	 *
	 * @param centroGestorQuery el centro gestor query
	 * @return el array list
	 */
	public ArrayList<CentroGestor> buscarCentrosGestoresAll(CentroGestorQuery centroGestorQuery);
	
	/**
	 * Obtener centro gestor.
	 *
	 * @param idCentroGestor el id centro gestor
	 * @return el centro gestor bean
	 */
	public CentroGestorBean obtenerCentroGestor(Integer idCentroGestor);
	
	/**
	 * Modificar centro gestor.
	 *
	 * @param centroGestorBean el centro gestor bean
	 */
	public void modificarCentroGestor(CentroGestorBean centroGestorBean);
	
	/**
	 * Modificar estado centro gestor.
	 *
	 * @param centroGestorBean el centro gestor bean
	 */
	public void modificarEstadoCentroGestor (CentroGestorBean  centroGestorBean);
	
	/**
	 * Buscar centro gestor unico.
	 *
	 * @param centroGestorQuery el centro gestor query
	 * @return el centro gestor bean
	 */
	public CentroGestorBean buscarCentroGestorUnico(CentroGestorQuery centroGestorQuery);
}
