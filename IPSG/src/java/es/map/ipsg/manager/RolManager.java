package es.map.ipsg.manager;

import java.util.List;

import es.map.ips.model.RolQuery;
import es.map.ipsg.bean.RolBean;
import es.map.ipsg.bean.UsuarioBean;

/**
 * El Interface RolManager.
 */
public interface RolManager {
	
	/**
	 * Guardar rol.
	 *
	 * @param rolBean el rol bean
	 * @return el integer
	 */
	public Integer guardarRol(RolBean rolBean);
	
	/**
	 * Modificar rol.
	 *
	 * @param rolBean el rol bean
	 */
	public void modificarRol(RolBean rolBean);
	
	/**
	 * Eliminar rol.
	 *
	 * @param idRol el id rol
	 */
	public void eliminarRol(Integer idRol);
	
	/**
	 * Buscar roles.
	 *
	 * @param rolQuery el rol query
	 * @return el list
	 */
	public List<RolBean> buscarRoles(RolQuery rolQuery);
	
	/**
	 * Buscar rol.
	 *
	 * @param rolQuery el rol query
	 * @return el rol bean
	 */
	public RolBean buscarRol(RolQuery rolQuery);
	
	/**
	 * Asignar rol.
	 *
	 * @param usuarioBean el usuario bean
	 * @return el rol bean
	 */
	public RolBean asignarRol(UsuarioBean usuarioBean);
}
