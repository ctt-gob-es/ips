package es.map.ipsc.manager.usuario;

import java.util.ArrayList;

import es.map.ips.model.UsuarioQuery;
import es.map.ipsc.bean.UsuarioBean;

/**
 * El Interface UsuarioManager.
 */
public interface UsuarioManager {

	/**
	 * Buscar usuarios perfil.
	 *
	 * @param usuarioQuery el usuario query
	 * @return el array list
	 */
	ArrayList<UsuarioBean> buscarUsuariosPerfil(UsuarioQuery usuarioQuery);


	
}
