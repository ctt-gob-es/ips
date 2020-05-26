package es.map.ipsc.manager.especialidades;

import java.util.ArrayList;

import es.map.ips.model.EspecialidadQuery;
import es.map.ipsc.bean.EspecialidadBean;

/**
 * El Interface EspecialidadManager.
 */
public interface EspecialidadManager {

	/**
	 * Buscar especialidades.
	 *
	 * @return el array list
	 */
	ArrayList<EspecialidadBean> buscarEspecialidades();

	/**
	 * Buscar especialidad id.
	 *
	 * @param especialidadQuery el especialidad query
	 * @return el especialidad bean
	 */
	EspecialidadBean buscarEspecialidadId(EspecialidadQuery especialidadQuery);


}
