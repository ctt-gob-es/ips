package es.map.ipsc.manager.otrosTitulos;


import java.util.ArrayList;

import es.map.ips.model.OtrosTitulosQuery;
import es.map.ipsc.bean.OtrosTitulosBean;



/**
 * El Interface OtrosTitulosManager.
 */
public interface OtrosTitulosManager {

	/**
	 * Buscar otros titulos combo.
	 *
	 * @param otrosTitulosQuery el otros titulos query
	 * @return el array list
	 */
	public ArrayList<OtrosTitulosBean> buscarOtrosTitulosCombo(OtrosTitulosQuery otrosTitulosQuery);
	
	/**
	 * Buscar otros titulos combo visibilidad.
	 *
	 * @param otrosTitulosQuery el otros titulos query
	 * @return el array list
	 */
	public ArrayList<OtrosTitulosBean> buscarOtrosTitulosComboVisibilidad(OtrosTitulosQuery otrosTitulosQuery);		
	
	/**
	 * Buscar otros titulos all.
	 *
	 * @param otrosTitulosQuery el otros titulos query
	 * @return el array list
	 */
	public ArrayList<OtrosTitulosBean> buscarOtrosTitulosAll(OtrosTitulosQuery otrosTitulosQuery);
	
	/**
	 * Guardar otros titulos.
	 *
	 * @param otrosTitulosBean el otros titulos bean
	 * @return el integer
	 */
	public Integer guardarOtrosTitulos(OtrosTitulosBean otrosTitulosBean);
	
	/**
	 * Obtener otro titulo.
	 *
	 * @param idOtrotitulo el id otrotitulo
	 * @return el otros titulos bean
	 */
	public OtrosTitulosBean obtenerOtroTitulo(Integer idOtrotitulo);
	
	/**
	 * Modificar otros titulos.
	 *
	 * @param otrosTitulosBean el otros titulos bean
	 * @return el boolean
	 */
	public Boolean modificarOtrosTitulos(OtrosTitulosBean otrosTitulosBean);
	
	/**
	 * Eliminar otro titulo.
	 *
	 * @param otrosTitulosBean el otros titulos bean
	 * @return el boolean
	 */
	public Boolean eliminarOtroTitulo(OtrosTitulosBean otrosTitulosBean);
}
