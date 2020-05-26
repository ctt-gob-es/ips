package es.map.ipsg.manager;

import java.util.List;

import es.map.ips.model.*;
import es.map.ipsg.bean.*;

/**
 * El Interface UsuarioAplicacionManager.
 */
public interface UsuarioAplicacionManager {
	
	/**
	 * Buscar usuarios aplicacion.
	 *
	 * @param usuarioAplicacionQuery el usuario aplicacion query
	 * @return el list
	 */
	public List<UsuarioAplicacionBean> buscarUsuariosAplicacion(UsuarioAplicacionQuery usuarioAplicacionQuery);
	
	/**
	 * Modificar usuario.
	 *
	 * @param usuarioAplicacionBean el usuario aplicacion bean
	 */
	public void modificarUsuario(UsuarioAplicacionBean usuarioAplicacionBean);
	
	/**
	 * Buscar usuarios aplicacion bean.
	 *
	 * @param usuarioAplicacionQuery el usuario aplicacion query
	 * @return el usuario aplicacion bean
	 */
	public UsuarioAplicacionBean buscarUsuariosAplicacionBean(UsuarioAplicacionQuery usuarioAplicacionQuery);
	
	/**
	 * To usuario aplicacion bean.
	 *
	 * @param usuarioAplicacion el usuario aplicacion
	 * @return el usuario aplicacion bean
	 */
	public UsuarioAplicacionBean toUsuarioAplicacionBean(UsuarioAplicacion usuarioAplicacion);
	
	/**
	 * Obtener usuario aplicacion.
	 *
	 * @param idUsuarioAplicacion el id usuario aplicacion
	 * @return el usuario aplicacion bean
	 */
	public UsuarioAplicacionBean obtenerUsuarioAplicacion(Integer idUsuarioAplicacion);
	
	/**
	 * Buscar aplicacion.
	 *
	 * @param usuarioAplicacionQuery el usuario aplicacion query
	 * @return el usuario aplicacion bean
	 */
	public UsuarioAplicacionBean buscarAplicacion(UsuarioAplicacionQuery usuarioAplicacionQuery);
	
	/**
	 * To aplicacion.
	 *
	 * @param usuarioAplicacionBean el usuario aplicacion bean
	 * @return el usuario aplicacion
	 */
	public UsuarioAplicacion toAplicacion(UsuarioAplicacionBean usuarioAplicacionBean);
	
	/**
	 * Comaparar usuarios aplicacion.
	 *
	 * @param usuarioAplicacionBeanAnteriror el usuario aplicacion bean anteriror
	 * @param usuarioAplicacionBean el usuario aplicacion bean
	 * @return el string
	 */
	public String comapararUsuariosAplicacion(UsuarioAplicacionBean usuarioAplicacionBeanAnteriror,
			UsuarioAplicacionBean usuarioAplicacionBean);
	
	/**
	 * Guardar usuario aplicacion.
	 *
	 * @param usuarioAplicacionBean el usuario aplicacion bean
	 * @return el int
	 */
	public int guardarUsuarioAplicacion(UsuarioAplicacionBean usuarioAplicacionBean);
}
