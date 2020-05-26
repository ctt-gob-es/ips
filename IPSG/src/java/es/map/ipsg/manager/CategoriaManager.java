package es.map.ipsg.manager;


import java.util.ArrayList;

import es.map.ips.model.CategoriaQuery;
import es.map.ipsg.bean.CategoriaBean;




/**
 * El Interface CategoriaManager.
 */
public interface CategoriaManager {

	/**
	 * Buscar categoria combo.
	 *
	 * @param categoriaQuery el categoria query
	 * @return el array list
	 */
	public ArrayList<CategoriaBean> buscarCategoriaCombo(CategoriaQuery categoriaQuery);
	
	/**
	 * Buscar categoria all.
	 *
	 * @param categoriaQuery el categoria query
	 * @return el array list
	 */
	public ArrayList<CategoriaBean> buscarCategoriaAll(CategoriaQuery categoriaQuery);
	
	/**
	 * Guardar categoria.
	 *
	 * @param categoriaBean el categoria bean
	 * @return el integer
	 */
	public Integer guardarCategoria(CategoriaBean categoriaBean);
	
	/**
	 * Obtener categoria.
	 *
	 * @param idCategoria el id categoria
	 * @return el categoria bean
	 */
	public CategoriaBean obtenerCategoria(Integer idCategoria);
	
	/**
	 * Modificar categoria.
	 *
	 * @param categoriaBean el categoria bean
	 */
	public void modificarCategoria(CategoriaBean categoriaBean);
	
}
