package es.map.ipsg.manager;

import java.util.ArrayList;

import es.map.ips.model.TipoDiscapacidadQuery;
import es.map.ipsg.bean.TipoDiscapacidadBean;




/**
 * El Interface TipoDiscapacidadManager.
 */
public interface TipoDiscapacidadManager {
	
	/**
	 * Buscar tipo discapacidad combo.
	 *
	 * @param tipoDiscapacidadQuery el tipo discapacidad query
	 * @return el array list
	 */
	public ArrayList<TipoDiscapacidadBean> buscarTipoDiscapacidadCombo(TipoDiscapacidadQuery tipoDiscapacidadQuery);
	
	/**
	 * Guardar tipo discapacidad.
	 *
	 * @param tipoDiscapacidadBean el tipo discapacidad bean
	 * @return el integer
	 */
	public Integer guardarTipoDiscapacidad(TipoDiscapacidadBean tipoDiscapacidadBean);
	
	/**
	 * Modificar tipo discapacidad.
	 *
	 * @param tipoDiscapacidadBean el tipo discapacidad bean
	 */
	public void modificarTipoDiscapacidad(TipoDiscapacidadBean tipoDiscapacidadBean);
	
	/**
	 * Buscar tipo discapacidad unique.
	 *
	 * @param tipoDiscapacidadQuery el tipo discapacidad query
	 * @return el tipo discapacidad bean
	 */
	public TipoDiscapacidadBean buscarTipoDiscapacidadUnique(TipoDiscapacidadQuery tipoDiscapacidadQuery);

}
