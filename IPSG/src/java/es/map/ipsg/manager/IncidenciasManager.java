package es.map.ipsg.manager;

import java.util.ArrayList;

import es.map.ips.model.IncidenciaQuery;
import es.map.ipsg.bean.CorreoElectronicoBean;
import es.map.ipsg.bean.IncidenciasBean;
import es.map.ipsg.bean.UsuarioBean;


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
	public Long guardarIncidencias(UsuarioBean auxUsuario, CorreoElectronicoBean correoElectronicoBean);
	
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
