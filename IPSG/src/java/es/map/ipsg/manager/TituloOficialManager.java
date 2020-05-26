package es.map.ipsg.manager;

import java.util.ArrayList;

import es.map.ips.model.TituloOficialQuery;
import es.map.ipsg.bean.TituloOficialBean;



/**
 * El Interface TituloOficialManager.
 *
 * @author amartinl
 */
public interface TituloOficialManager {
	
	/**
	 * Buscar titulo oficial combo.
	 *
	 * @param tituloOficialQuery el titulo oficial query
	 * @return el array list
	 */
	public ArrayList<TituloOficialBean> buscarTituloOficialCombo(TituloOficialQuery tituloOficialQuery);
	
	/**
	 * Buscar titulo oficial all.
	 *
	 * @param tituloOficialQuery el titulo oficial query
	 * @return el array list
	 */
	public ArrayList<TituloOficialBean> buscarTituloOficialAll(TituloOficialQuery tituloOficialQuery);
	
	/**
	 * Buscar titulo oficial combo visibilidad.
	 *
	 * @param tituloOficialQuery el titulo oficial query
	 * @return el array list
	 */
	public ArrayList<TituloOficialBean> buscarTituloOficialComboVisibilidad(TituloOficialQuery tituloOficialQuery);
	
	/**
	 * Obtener titulo oficial.
	 *
	 * @param idTitulo el id titulo
	 * @return el titulo oficial bean
	 */
	public TituloOficialBean obtenerTituloOficial (Integer idTitulo);
	
	/**
	 * Obtener titulo oficial by codigo.
	 *
	 * @param codigo el codigo
	 * @return el titulo oficial bean
	 */
	public TituloOficialBean obtenerTituloOficialByCodigo(String codigo);
	
	/**
	 * Modificar titulo.
	 *
	 * @param tituloOficialBean el titulo oficial bean
	 */
	public void modificarTitulo (TituloOficialBean tituloOficialBean);
	
	/**
	 * Guardar titulo oficial.
	 *
	 * @param tituloOficialBean el titulo oficial bean
	 * @return el integer
	 */
	public Integer guardarTituloOficial(TituloOficialBean tituloOficialBean);
}
