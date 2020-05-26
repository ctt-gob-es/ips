package es.map.ipsg.manager;

import java.util.List;

import es.map.ips.model.PerfilQuery;
import es.map.ipsg.bean.PerfilBean;

/**
 * El Interface PerfilManager.
 */
public interface PerfilManager {
	
	/**
	 * Busca Usuarios por criterios de b�squeda.
	 *
	 * @param perfilQuery el perfil query
	 * @return Resultado de la b�squeda
	 */
	public List<PerfilBean> buscarPerfiles(PerfilQuery perfilQuery);
	
	/**
	 * Buscar perfil.
	 *
	 * @param perfilQuery el perfil query
	 * @return el perfil bean
	 */
	public PerfilBean buscarPerfil(PerfilQuery perfilQuery);
}
