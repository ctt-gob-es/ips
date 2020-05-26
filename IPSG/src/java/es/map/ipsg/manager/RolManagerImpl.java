package es.map.ipsg.manager;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import es.map.ips.common.exception.BusinessException;
import es.map.ips.dao.RolDAO;
import es.map.ips.model.Rol;
import es.map.ips.model.RolQuery;
import es.map.ipsg.bean.RolBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.util.Constantes;

/**
 * El Class RolManagerImpl.
 */
public class RolManagerImpl implements RolManager {

	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(RolManagerImpl.class);
	
	/** el rol DAO. */
	private RolDAO rolDAO;

	/**
	 * Obtiene el rol DAO.
	 *
	 * @return el rol DAO
	 */
	public RolDAO getRolDAO() {
		return rolDAO;
	}

	/**
	 * Establece el rol DAO.
	 *
	 * @param rolDAO el nuevo rol DAO
	 */
	public void setRolDAO(RolDAO rolDAO) {
		this.rolDAO = rolDAO;
	}

	/**
	 * Buscar roles.
	 *
	 * @param rolQuery el rol query
	 * @return el list
	 * @see RolManager#buscarRoles(RolQuery)
	 */
	public List<RolBean> buscarRoles(RolQuery rolQuery) {
		List<RolBean> list = new ArrayList<RolBean>();
		logger.info("Entra en buscarRoles");
		List<Rol> roles = rolDAO.search(rolQuery).getResults();
		
		for(Rol r : roles){
			list.add(toBeanRol(r));
		}
		logger.info("Sale de buscarRoles");
		return list;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.RolManager#buscarRol(es.map.ips.model.RolQuery)
	 */
	public RolBean buscarRol(RolQuery rolQuery) {
		logger.info("Entra en buscarRol");
		Rol rol = rolDAO.searchUnique(rolQuery);
		logger.info("Sale de buscarRol");
		return (rol==null ? null : toBeanRol(rol));
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.RolManager#guardarRol(es.map.ipsg.bean.RolBean)
	 */
	public Integer guardarRol(RolBean rolBean){
		logger.info("Entra en guardarRol");
		Rol rol = toRol(rolBean);
		logger.info("Sale de guardarRol");
		return rolDAO.insert(rol);		
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.RolManager#modificarRol(es.map.ipsg.bean.RolBean)
	 */
	public void modificarRol(RolBean rolBean){
		logger.info("Entra en modificarRol");
		Rol rol = toRol(rolBean);
		rolDAO.update(rol);	
		logger.info("Sale de modificarRol");
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.RolManager#eliminarRol(java.lang.Integer)
	 */
	public void eliminarRol(Integer idRol){
		logger.info("Entra en eliminarRol");
		rolDAO.delete(idRol);
		logger.info("Sale de eliminarRol");
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.RolManager#asignarRol(es.map.ipsg.bean.UsuarioBean)
	 */
	public RolBean asignarRol(UsuarioBean usuarioBean){
		logger.info("Entra en asignarRol");
		Rol rol = asociarRol(usuarioBean);
		logger.info("Sale de asignarRol");
		return (rol == null ? null : toBeanRol(rol));
	}	
	
	/**
	 * Asociar rol.
	 *
	 * @param usuarioBean el usuario bean
	 * @return el rol
	 */
	public Rol asociarRol(UsuarioBean usuarioBean){
		Rol rol = null;
		logger.info("Entra en asociarRol");
	
		if(!usuarioBean.getIdPerfil().toString().equals(Constantes.PERFIL_RECEPTOR)){
			rol = obtenerRol(usuarioBean);
			logger.debug("Rol "+rol.getAuthority()+" creado para el perfil "+usuarioBean.getDesPerfil());
			
			if(rol==null){
				throw new BusinessException("error.usuario.alta.rolNulo",usuarioBean.getDesPerfil());			
			}
		}else{
			logger.debug("No se crea Rol para el perfil Receptor");
		}
		logger.info("Sale de asociarRol");
		return rol;
	}
	
	/**
	 * Obtener rol.
	 *
	 * @param usuarioBean el usuario bean
	 * @return el rol
	 */
	public Rol obtenerRol(UsuarioBean usuarioBean){
		logger.info("Entra en obtenerRol");
		String authority;
		int idPerfil = usuarioBean.getIdPerfil();
		Rol rol = null;
		switch(idPerfil){
			case Constantes.PERFIL_CONSULTOR_INT 		: authority=Constantes.ROL_CONSULTOR; 		break;
			case Constantes.PERFIL_SOPORTE_INT 			: authority=Constantes.ROL_SOPORTE; 		break;
			case Constantes.PERFIL_GESTOR_INT 			: authority=Constantes.ROL_GESTOR;			break;
			case Constantes.PERFIL_ADMINISTRADOR_INT 	: authority=Constantes.ROL_ADMINISTRADOR; 	break;
			case Constantes.PERFIL_OPERADOR_INT 		: authority=Constantes.ROL_OPERADOR; 		break;
		    case Constantes.PERFIL_EMPRESA_INT			: authority=Constantes.ROL_EMPRESA; 		break;
		    case Constantes.PERFIL_REGISTRO_INT			: authority=Constantes.ROL_REGISTRO; 		break;
			default										: authority=null;							break;
		}
		
		if(authority!=null){
			rol = new Rol();
			rol.setLogin(usuarioBean.getLogin());
			rol.setAuthority(authority);
		}
		logger.info("Sale de obtenerRol");
		return rol;
	}
	
	/**
	 * To bean rol.
	 *
	 * @param rol el rol
	 * @return el rol bean
	 */
	public RolBean toBeanRol(Rol rol){
		RolBean rolBean = new RolBean();
		rolBean.setId(rol.getId());
		rolBean.setLogin(rol.getLogin());
		rolBean.setAuthority(rol.getAuthority());
		return rolBean;
	}
	
	/**
	 * To rol.
	 *
	 * @param rolBean el rol bean
	 * @return el rol
	 */
	public Rol toRol(RolBean rolBean){
		Rol rol = new Rol();
		rol.setId(rolBean.getId());
		rol.setLogin(rolBean.getLogin());
		rol.setAuthority(rolBean.getAuthority());
		return rol;
	}	
}
