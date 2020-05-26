package es.map.ipsg.manager;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import es.map.ips.common.exception.BusinessException;
import es.map.ips.common.model.SearchResult;
import es.map.ips.dao.UsuarioDAO;
import es.map.ips.model.CategoriaQuery;
import es.map.ips.model.CentroGestor;
import es.map.ips.model.Perfil;
import es.map.ips.model.RolQuery;
import es.map.ips.model.Usuario;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.bean.RolBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.util.Constantes;

/**
 * El Class UsuarioManagerImpl.
 */
public class UsuarioManagerImpl implements UsuarioManager {

	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(UsuarioManagerImpl.class);
	
	/** La constante STRING_ARROW. */
	private static final String STRING_ARROW = " --> ";
	
	/** el usuario DAO. */
	private UsuarioDAO usuarioDAO;
	
	/** el rol manager. */
	private RolManager rolManager;
	
	/**
	 * Establece el usuario DAO.
	 *
	 * @param usuarioDAO el nuevo usuario DAO
	 */
	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
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
	 * Obtiene el rol manager.
	 *
	 * @return el rol manager
	 */
	public RolManager getRolManager() {
		return rolManager;
	}

	/**
	 * Establece el rol manager.
	 *
	 * @param rolManager el nuevo rol manager
	 */
	public void setRolManager(RolManager rolManager) {
		this.rolManager = rolManager;
	}	

	/**
	 * Buscar usuarios.
	 *
	 * @param usuarioQuery el usuario query
	 * @return el list
	 * @see CategoriaManager#buscarCategorias(CategoriaQuery)
	 */
	public List<UsuarioBean> buscarUsuarios(UsuarioQuery usuarioQuery) {
		List<UsuarioBean> list = new ArrayList<UsuarioBean>();
		SearchResult<Usuario> usuarios = null;
		try{
		usuarios = usuarioDAO.search(usuarioQuery);
		}catch(Exception e){
			logger.error("No se ha podido buscar usuarios",e );
		}
		int numTotal = 0;

		if(usuarios != null){
			try{
// TODO Corrección incidencia buscador usuarios				

				if (usuarios.getNumResults() != null){
					numTotal = usuarios.getNumResults();
				}else{
					numTotal=0;
				}
			}catch (Exception e) {
				logger.error("No se ha podido buscar usuarios -numero de usuarios"+ numTotal,e );
				numTotal = 0;
			}
			
		}

		for(Usuario u:usuarios.getResults()){
			list.add(toUsuariosBean(u,numTotal));
		}
		
		return list;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.UsuarioManager#buscarUsuario(es.map.ips.model.UsuarioQuery)
	 */
	public UsuarioBean buscarUsuario(UsuarioQuery usuarioQuery){
		Usuario usuario = usuarioDAO.searchUnique(usuarioQuery);
		return (usuario==null ? null:toUsuarioBean(usuario));
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.UsuarioManager#guardarUsuario(es.map.ipsg.bean.UsuarioBean)
	 */
	public Integer guardarUsuario(UsuarioBean usuarioBean) throws BusinessException{
		Usuario usuario = toUsuario(usuarioBean);
		Integer idUsuario;
		
		idUsuario = usuarioDAO.insert(usuario);
		RolBean rolBean = rolManager.asignarRol(usuarioBean);
		if (rolBean != null) { //es null con perfil receptor
			rolManager.guardarRol(rolBean);
		}
		
		return idUsuario;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.UsuarioManager#obtenerUsuario(java.lang.Integer)
	 */
	public UsuarioBean obtenerUsuario(Integer idUsuario){
		Usuario usuario = usuarioDAO.get(idUsuario);
		return toUsuarioBean(usuario);
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.UsuarioManager#modificarUsuario(es.map.ipsg.bean.UsuarioBean)
	 */
	public void modificarUsuario(UsuarioBean usuarioBean) throws BusinessException{
		System.out.println("entra en modificarUsuario");
		modificarRolActual(usuarioBean);
		Usuario usuario = toUsuario(usuarioBean);		
		usuarioDAO.update(usuario);
		rolManager.asignarRol(usuarioBean);
	}
	
	/**
	 * Modificar rol actual.
	 *
	 * @param usuarioBean el usuario bean
	 * @throws BusinessException el business exception
	 */
	private void modificarRolActual(UsuarioBean usuarioBean) throws BusinessException{
		//Se recupera el usuario actual para validar que perfil tiene
		Usuario usuario = usuarioDAO.get(usuarioBean.getId());
		
		logger.info("El usuario a modificar: "+usuario.getLogin());
		logger.info("Con Perfil:"+usuario.getPerfil().getDescripcion());
		
		Integer idPerfilActual = usuario.getPerfil().getId();
		Integer idPerfilNuevo = usuarioBean.getIdPerfil();
		
		if(idPerfilActual.equals(Constantes.PERFIL_RECEPTOR_INT)){
			if(!idPerfilNuevo.equals(Constantes.PERFIL_RECEPTOR_INT)){
				logger.info("error.usuario.modificarPerfilReceptor");
				//Si el usuario es RECEPTOR, no se puede modificar el perfil
				throw new BusinessException("error.usuario.modificarPerfilReceptor");
			}//Se mantiene el perfil. No se modifica el Rol
		}else{//El Perfil Actual no es RECEPTOR			
			if(!idPerfilActual.equals(idPerfilNuevo)){
				//El Perfil ha cambiado
				//Obtener el Rol Actual
				RolQuery rolQuery = new RolQuery();
				rolQuery.setLogin(usuario.getLogin());
				RolBean rolActual = rolManager.buscarRol(rolQuery);
				
				if(rolActual == null){
					logger.info("error.usuario.rolNoEncontrado");
					throw new BusinessException("error.usuario.rolNoEncontrado");
				}				
				
				if(idPerfilNuevo.equals(Constantes.PERFIL_RECEPTOR_INT)){
					//El Perfil Nuevo es RECEPTOR por lo que se eliminar el rol actual
					rolManager.eliminarRol(rolActual.getId());
					
				}else{
					//Se modifica el rol	
					//Recuperamos el Rol que le corresponde al nuevo perfil
					RolBean rolNuevo = rolManager.asignarRol(usuarioBean);
					
					rolNuevo.setId(rolActual.getId());
					rolManager.modificarRol(rolNuevo);
				}
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.UsuarioManager#toUsuarioBean(es.map.ips.model.Usuario)
	 */
	public UsuarioBean toUsuarioBean(Usuario usuario){
		UsuarioBean usuarioBean = new UsuarioBean();
		
		usuarioBean.setId(usuario.getId());
		usuarioBean.setEstado(usuario.getEstado());
		usuarioBean.setNif(usuario.getNif());
		usuarioBean.setNombre(usuario.getNombre());
		usuarioBean.setPrimerApellido(usuario.getPrimerApellido());
		usuarioBean.setSegundoApellido(usuario.getSegundoApellido());
		usuarioBean.setEmail(usuario.getEmail());
		usuarioBean.setTelefono(usuario.getTelefono());
		usuarioBean.setPublicaConvocatorias((usuario.getPublicaConvocatorias()=='S') ? true : false);
		usuarioBean.setRecibeCorreosIncidencias((usuario.getRecibeCorreosIncidencias()=='S') ? true : false);
		usuarioBean.setIdPerfil(usuario.getPerfil().getId());
		usuarioBean.setDesPerfil(usuario.getPerfil().getDescripcion());
		usuarioBean.setLogin(usuario.getLogin());
		usuarioBean.setPassword(usuario.getPassword());
		usuarioBean.setToken(usuario.getToken());
		usuarioBean.setUltimaActPassword(usuario.getUltimaActPassword());
		usuarioBean.setNombreCompleto(usuario.getNombre()+" "+usuario.getPrimerApellido()+" "+usuario.getSegundoApellido());
		
		return usuarioBean;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.UsuarioManager#toUsuariosBean(es.map.ips.model.Usuario, int)
	 */
	public UsuarioBean toUsuariosBean(Usuario usuario, int numTotal){
		UsuarioBean usuarioBean = new UsuarioBean();
		
		usuarioBean.setId(usuario.getId());
		usuarioBean.setEstado(usuario.getEstado());
		usuarioBean.setNif(usuario.getNif());
		usuarioBean.setNombre(usuario.getNombre());
		usuarioBean.setPrimerApellido(usuario.getPrimerApellido());
		usuarioBean.setSegundoApellido(usuario.getSegundoApellido());
		usuarioBean.setEmail(usuario.getEmail());
		usuarioBean.setTelefono(usuario.getTelefono());
		usuarioBean.setPublicaConvocatorias((usuario.getPublicaConvocatorias()=='S') ? true : false);
		usuarioBean.setRecibeCorreosIncidencias((usuario.getRecibeCorreosIncidencias()=='S') ? true : false);
		usuarioBean.setIdPerfil(usuario.getPerfil().getId());
		usuarioBean.setDesPerfil(usuario.getPerfil().getDescripcion());
		usuarioBean.setLogin(usuario.getLogin());
		usuarioBean.setPassword(usuario.getPassword());
		usuarioBean.setToken(usuario.getToken());
		usuarioBean.setUltimaActPassword(usuario.getUltimaActPassword());
		usuarioBean.setNumTotal(numTotal);
		usuarioBean.setNombreCompleto(usuario.getNombre()+" "+usuario.getPrimerApellido()+" "+usuario.getSegundoApellido());
		
		return usuarioBean;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.UsuarioManager#toUsuario(es.map.ipsg.bean.UsuarioBean)
	 */
	public Usuario toUsuario(UsuarioBean usuarioBean){
		Usuario usuario = null;
		
		if(usuarioBean.getId()!=null && usuarioBean.getId()>0)
			usuario = usuarioDAO.get(usuarioBean.getId());
		else
			usuario = new Usuario();		
		
		usuario.setToken(usuarioBean.getToken()); 
		usuario.setId(usuarioBean.getId());
		usuario.setEstado(usuarioBean.getEstado());
		usuario.setNif(usuarioBean.getNif());
		usuario.setNombre(usuarioBean.getNombre());
		usuario.setPrimerApellido(usuarioBean.getPrimerApellido());
		usuario.setSegundoApellido(usuarioBean.getSegundoApellido());
		usuario.setEmail(usuarioBean.getEmail());
		usuario.setTelefono(usuarioBean.getTelefono());
		usuario.setPublicaConvocatorias(usuarioBean.getPublicaConvocatorias() ? 'S':'N');
		usuario.setRecibeCorreosIncidencias(usuarioBean.getRecibeCorreosIncidencias() ? 'S':'N');
		usuario.setLogin(usuarioBean.getLogin());
		usuario.setPassword(usuarioBean.getPassword());
		usuario.setToken(usuarioBean.getToken());
		usuario.setUltimaActPassword(usuarioBean.getUltimaActPassword());
		
		Perfil perfil = new Perfil();
		perfil.setId(usuarioBean.getIdPerfil());
		usuario.setPerfil(perfil);
				
		return usuario;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.UsuarioManager#compararUsuarios(es.map.ipsg.bean.UsuarioBean, es.map.ipsg.bean.UsuarioBean)
	 */
	public String compararUsuarios(UsuarioBean usuarioAnterior, UsuarioBean usuarioActual){
		StringBuffer cambios = new StringBuffer("");
		boolean cambiado = false;
		
		String idCentroGestorAct = "";
		String idPerfilAnterior = "";
		String nifAnterior = "";
		String nombreAnterior = "";
		String primerApellidoAnterior = "";
		String segundoApellidoAnterior = "";
		String telefonoAnterior = "";
		String emailAnterior = "";
		String estadoAnterior = "";
		boolean recibirAnterior;
		boolean publicarAnterior;
		if (usuarioAnterior.getIdPerfil() != null){
			idPerfilAnterior = usuarioAnterior.getIdPerfil().toString();
		}
		nifAnterior = usuarioAnterior.getNif();
		nombreAnterior = usuarioAnterior.getNombre();
		primerApellidoAnterior = usuarioAnterior.getPrimerApellido();
		segundoApellidoAnterior = usuarioAnterior.getSegundoApellido();
		telefonoAnterior = usuarioAnterior.getTelefono();
		emailAnterior = usuarioAnterior.getEmail();
		estadoAnterior = usuarioAnterior.getEstado().toString();
		recibirAnterior = usuarioAnterior.getRecibeCorreosIncidencias();
		publicarAnterior = usuarioAnterior.getPublicaConvocatorias();

		String idPerfilActual = "";
		String nifActual = "";
		String nombreActual = "";
		String primerApellidoActual = "";
		String segundoApellidoActual = "";
		String telefonoActual = "";
		String emailActual = "";
		String estadoActual = "";
		boolean recibirActual;
		boolean publicarActual;
		if (usuarioActual.getIdPerfil() != null){
			idPerfilActual = usuarioActual.getIdPerfil().toString();
		}
		nifActual = usuarioActual.getNif();
		nombreActual = usuarioActual.getNombre();
		primerApellidoActual = usuarioActual.getPrimerApellido();
		segundoApellidoActual = usuarioActual.getSegundoApellido();
		telefonoActual = usuarioActual.getTelefono();
		emailActual = usuarioActual.getEmail();
		estadoActual = usuarioActual.getEstado().toString();
		recibirActual = usuarioActual.getRecibeCorreosIncidencias();
		publicarActual = usuarioActual.getPublicaConvocatorias();
		
		if (idPerfilAnterior.compareTo(idPerfilActual)!=0){
			if (cambiado){
				cambios.append(", ");
			}
			cambios.append("Perfil: ");
			cambios.append(idPerfilAnterior);
			cambios.append(STRING_ARROW);
			cambios.append(idPerfilActual);
			cambiado = true;
		}
		if (nifAnterior.compareTo(nifActual)!=0){
			if (cambiado){
				cambios.append(", ");
			}
			cambios.append("NIF: ");
			cambios.append(nifAnterior);
			cambios.append(STRING_ARROW);
			cambios.append(nifActual);
			cambiado = true;
		}
		
		if (emailAnterior.compareTo(emailActual)!=0){
			if (cambiado){
				cambios.append(", ");
			}
			cambios.append("Email: ");
			cambios.append(emailAnterior);
			cambios.append(STRING_ARROW);
			cambios.append(emailActual);
			cambiado = true;
		}
		if (estadoAnterior.compareTo(estadoActual)!=0){
			if (cambiado){
				cambios.append(", ");
			}
			cambios.append("Estado: ");
			cambios.append(estadoAnterior);
			cambios.append(STRING_ARROW);
			cambios.append(estadoActual);
			cambiado = true;
		}
		
		
		if (cambiado){
			if(cambios.length()>500)
				cambios.substring(0, 500);
			return cambios.toString();
		}else{
			return "";
		}
	}

	/**
	 * Comprobar perfil usuario.
	 *
	 * @param idPerfil el id perfil
	 * @return el string
	 */
	public String comprobarPerfilUsuario(Integer idPerfil) {
		String sPerfil = "";

		if(idPerfil != null  && idPerfil.equals(Integer.parseInt(Constantes.PERFIL_CONSULTOR))){
			sPerfil = Constantes.ROL_CONSULTOR;
		}
		
		if(idPerfil != null  && idPerfil.equals(Integer.parseInt(Constantes.PERFIL_SOPORTE))){
			sPerfil = Constantes.ROL_SOPORTE;
		}
		
		if(idPerfil != null  && idPerfil.equals(Integer.parseInt(Constantes.PERFIL_GESTOR))){
			sPerfil = Constantes.ROL_GESTOR;
		}
		
		if(idPerfil != null  && idPerfil.equals(Integer.parseInt(Constantes.PERFIL_ADMINISTRADOR))){
			sPerfil = Constantes.ROL_ADMINISTRADOR;
		}

		return sPerfil;
	}

	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.UsuarioManager#obtenerNumeroUsuarios(es.map.ips.model.UsuarioQuery)
	 */
	@Override
	public Integer obtenerNumeroUsuarios(UsuarioQuery query) {
		return usuarioDAO.obtenerNumeroUsuarios(query);
	}
	
}
