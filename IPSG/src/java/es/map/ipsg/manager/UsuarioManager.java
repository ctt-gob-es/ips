package es.map.ipsg.manager;

import java.util.List;

import es.map.ips.common.exception.BusinessException;
import es.map.ips.model.Usuario;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.bean.UsuarioBean;

/**
 * El Interface UsuarioManager.
 */
public interface UsuarioManager {

	/**
	 * Busca Usuarios por criterios de busqueda.
	 *
	 * @param usuarioQuery Criterios de busqueda
	 * @return Resultado de la busqueda
	 */
	public List<UsuarioBean> buscarUsuarios(UsuarioQuery usuarioQuery);
	
	/**
	 * Guardar usuario.
	 *
	 * @param usuarioBean el usuario bean
	 * @return el integer
	 * @throws BusinessException el business exception
	 */
	public Integer guardarUsuario(UsuarioBean usuarioBean) throws BusinessException;
	
	/**
	 * Obtener usuario.
	 *
	 * @param idUsuario el id usuario
	 * @return el usuario bean
	 */
	public UsuarioBean obtenerUsuario(Integer idUsuario);
	
	/**
	 * Modificar usuario.
	 *
	 * @param usuarioBean el usuario bean
	 */
	public void modificarUsuario(UsuarioBean usuarioBean);
	
	/**
	 * Buscar usuario.
	 *
	 * @param usuarioQuery el usuario query
	 * @return el usuario bean
	 */
	public UsuarioBean buscarUsuario(UsuarioQuery usuarioQuery);
	
	/**
	 * To usuarios bean.
	 *
	 * @param usuario el usuario
	 * @param numTotal el num total
	 * @return el usuario bean
	 */
	public UsuarioBean toUsuariosBean(Usuario usuario, int numTotal);
	
	/**
	 * To usuario bean.
	 *
	 * @param usuario el usuario
	 * @return el usuario bean
	 */
	public UsuarioBean toUsuarioBean(Usuario usuario);
	
	/**
	 * To usuario.
	 *
	 * @param usuarioBean el usuario bean
	 * @return el usuario
	 */
	public Usuario toUsuario(UsuarioBean usuarioBean);
	
	/**
	 * Comparar usuarios.
	 *
	 * @param usuarioAnterior el usuario anterior
	 * @param usuarioActual el usuario actual
	 * @return el string
	 */
	public String compararUsuarios(UsuarioBean usuarioAnterior, UsuarioBean usuarioActual);
	
	/**
	 * Comprobar perfil usuario.
	 *
	 * @param idPerfil el id perfil
	 * @return el string
	 */
	public String comprobarPerfilUsuario(Integer idPerfil);
	
	/**
	 * Obtener numero usuarios.
	 *
	 * @param query el query
	 * @return el integer
	 */
	public Integer obtenerNumeroUsuarios(UsuarioQuery query);
}
