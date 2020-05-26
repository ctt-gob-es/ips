package es.map.ipsc.manager.usuario;

import java.util.ArrayList;

import es.map.ips.common.model.SearchResult;
import es.map.ips.dao.UsuarioDAO;
import es.map.ips.model.Usuario;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsc.bean.UsuarioBean;

/**
 * El Class UsuarioManagerImpl.
 */
public class UsuarioManagerImpl implements UsuarioManager {
	
	/** el usuario DAO. */
	private UsuarioDAO usuarioDAO;
	
	
	
	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.usuario.UsuarioManager#buscarUsuariosPerfil(es.map.ips.model.UsuarioQuery)
	 */
	public ArrayList<UsuarioBean> buscarUsuariosPerfil(UsuarioQuery usuarioQuery) {
		SearchResult<Usuario> usuarios = usuarioDAO.search(usuarioQuery);
		ArrayList<UsuarioBean> arrayUsuarios = new ArrayList<UsuarioBean>();
		for(int i=0;i<usuarios.getResults().size();i++){
			UsuarioBean usuAux;
			usuAux = toUsuarioBean(usuarios.getResults().get(i));
			if(usuAux != null){
				arrayUsuarios.add(usuAux);
			}
		}		
		return arrayUsuarios;
	}


	

	/**
	 * To usuario bean.
	 *
	 * @param usuario el usuario
	 * @return el usuario bean
	 */
	private UsuarioBean toUsuarioBean(Usuario usuario) {
		UsuarioBean aux = new UsuarioBean();
		if(usuario.getNif() != null){
			aux.setNif(usuario.getNif());
		}
		if(usuario.getNombre() != null){
			aux.setNombre(usuario.getNombre());
		}
		if(usuario.getPrimerApellido() != null){
			aux.setPrimerApellido(usuario.getPrimerApellido());
		}
		if(usuario.getSegundoApellido() != null){
			aux.setSegundoApellido(usuario.getSegundoApellido());
		}
		if(usuario.getTelefono() != null){
			aux.setTelefono(usuario.getTelefono());
		}
		if(usuario.getEmail() != null){
			aux.setEmail(usuario.getEmail());
		}
		if(usuario.getPerfil() != null){
			aux.setIdPerfil(String.valueOf(usuario.getPerfil().getId()));
		}
//		if(usuario.getCentroGestor() != null){
//			aux.setIdCentroGestor(String.valueOf(usuario.getCentroGestor().getIdCentroGestor()));
//		}
		return aux;
	}




	/**
	 * Obtiene el usuario DAO.
	 *
	 * @return el usuario DAO
	 */
	public UsuarioDAO getUsuarioDAO() {
		return usuarioDAO;
	}

	/**
	 * Establece el usuario DAO.
	 *
	 * @param usuarioDAO el nuevo usuario DAO
	 */
	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}
	
}
