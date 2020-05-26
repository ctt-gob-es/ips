package es.map.ipsc.manager.tipoDiscapacidad;

import java.util.ArrayList;

import es.map.ips.model.TipoDiscapacidadQuery;
import es.map.ipsc.bean.TipoDiscapacidadBean;

/**
 * El Interface TipoDiscapacidadManager.
 */
public interface TipoDiscapacidadManager {

	/**
	 * Buscar tipos discapacidad.
	 *
	 * @return el array list
	 */
	ArrayList<TipoDiscapacidadBean> buscarTiposDiscapacidad();

	/**
	 * Buscar tipo discapacidad id.
	 *
	 * @param tipoDiscapacidadQuery el tipo discapacidad query
	 * @return el tipo discapacidad bean
	 */
	TipoDiscapacidadBean buscarTipoDiscapacidadId(
			TipoDiscapacidadQuery tipoDiscapacidadQuery);

	/**
	 * Buscar tipos discapacidad.
	 *
	 * @param tipoDiscapacidadQuery el tipo discapacidad query
	 * @return el array list
	 */
	ArrayList<TipoDiscapacidadBean> buscarTiposDiscapacidad(TipoDiscapacidadQuery tipoDiscapacidadQuery);
}
