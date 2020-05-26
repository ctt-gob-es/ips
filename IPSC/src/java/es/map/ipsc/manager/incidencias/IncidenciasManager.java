package es.map.ipsc.manager.incidencias;

import java.util.ArrayList;

import es.map.ips.model.IncidenciaQuery;
import es.map.ipsc.bean.CiudadanoBean;
import es.map.ipsc.bean.CorreoElectronicoBean;
import es.map.ipsc.bean.IncidenciasBean;

/**
 * El Interface IncidenciasManager.
 */
public interface IncidenciasManager {

	/**
	 * Guardar incidencias.
	 *
	 * @param auxUsuario el aux usuario
	 * @param correoElectronicoBean el correo electronico bean
	 * @return el long
	 */
	public Long guardarIncidencias(CiudadanoBean auxUsuario, CorreoElectronicoBean correoElectronicoBean);
	
	/**
	 * Buscar incidencias filtro.
	 *
	 * @param incidenciaQuery el incidencia query
	 * @return el array list
	 */
	public ArrayList<IncidenciasBean> buscarIncidenciasFiltro(IncidenciaQuery incidenciaQuery);
	
	/**
	 * Obtener incidencia.
	 *
	 * @param idIncidencia el id incidencia
	 * @return el incidencias bean
	 */
	public IncidenciasBean obtenerIncidencia(Long idIncidencia);

}
