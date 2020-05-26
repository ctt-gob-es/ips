package es.map.ipsg.action.usuarios;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.util.StringUtils;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.CentroGestorQuery;
import es.map.ips.model.PerfilQuery;
import es.map.ips.model.Usuario;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.bean.CentroGestorBean;
import es.map.ipsg.bean.PerfilBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.bean.UsuarioCentrogestorBean;
import es.map.ipsg.form.UsuarioForm;
import es.map.ipsg.manager.CentroGestorManager;
import es.map.ipsg.manager.PerfilManager;
import es.map.ipsg.manager.UsuarioCentrogestorManager;
import es.map.ipsg.manager.UsuarioManager;
import es.map.ipsg.util.Constantes;

/**
 * El Class VerModificarUsuarioSpring.
 */
public class VerModificarUsuarioSpring extends AbstractSpring {

	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(VerModificarUsuarioSpring.class);

	/** el usuario manager. */
	private UsuarioManager usuarioManager;
	
	/** el perfil manager. */
	private PerfilManager perfilManager;
	
	/** el centro gestor manager. */
	private CentroGestorManager centroGestorManager;

	/** el usuario centro gestor manager. */
	private UsuarioCentrogestorManager usuarioCentrogestorManager;
	
	/**
	 * Crea una nueva ver modificar usuario spring.
	 */
	public VerModificarUsuarioSpring() {
		try {
			setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
			setPerfilManager((PerfilManager) getBean("perfilManager"));
			setCentroGestorManager((CentroGestorManager) getBean("centrosGestoresManager"));
			setUsuarioCentrogestorManager((UsuarioCentrogestorManager) getBean("usuarioCentrogestorManager"));
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error VerModificarUsuarioSpring - crear constructor",e);
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		
		String menu_usuario = RESOURCE_BUNDLE.getString("field.menu.usuarios");
		this.getRequest().getSession().setAttribute("pagActiva", menu_usuario);
		String subMenu_usuarios = RESOURCE_BUNDLE.getString("field.menuLateral.usuarios.buscar");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_usuarios);
		
		getLogger().debug("VerModificarUsuarioSpring -start");
		logger.info("Entra en el Action VerModificarUsuarioAction");
		String resultado;
		try{
			String sUsernameLogin = "";
			UsuarioBean usuarioSession = (UsuarioBean) getRequest().getSession().getAttribute("usuario");
			sUsernameLogin = usuarioSession.getLogin();
			/*INI-TRA042*/
			UsuarioQuery usuarioQuery = new UsuarioQuery();
			usuarioQuery.setLogin(sUsernameLogin); //Le pasamos el login del que está logueado
			UsuarioBean usuarioLogin = usuarioManager.buscarUsuario(usuarioQuery);
			Usuario usuario = usuarioManager.toUsuario(usuarioLogin);
			Integer idUsuarioLogin = usuario.getId();
			String sPerfilUsuario = "";
			sPerfilUsuario = comprobarPerfilUsuario(idUsuarioLogin);
			/*FIN-TRA042*/
			
			UsuarioForm formulario = (UsuarioForm) form;
			
			PerfilQuery perfilQuery = new PerfilQuery();
			List<PerfilBean> perfiles = perfilManager.buscarPerfiles(perfilQuery);
			
			CentroGestorQuery centroGestorQuery = new CentroGestorQuery();
			List<CentroGestorBean> centrosGestores = centroGestorManager.buscarCentroGestorCombo(centroGestorQuery);
			
			String idUsuario = this.getRequest().getParameter("id");
			
			if(idUsuario!=null){
				logger.info("Usuario a modificar/consultar: "+idUsuario);
				UsuarioBean usuarioBean = new UsuarioBean();
				if(!"Modificar".equals(formulario.getAccion())){
					usuarioBean = usuarioManager.obtenerUsuario(Integer.valueOf(idUsuario));
					/*INI-TRA042*/
					List<CentroGestorBean> listaCentrosGestores = usuarioCentrogestorManager.buscarCentrosGestoresByIdUsuario(usuarioBean.getId());
					UsuarioCentrogestorBean usuCg = new UsuarioCentrogestorBean();
					usuCg.setCentroGestor(listaCentrosGestores);
					usuarioBean.setUsuCg(usuCg);
					/*FIN-TRA042*/
					logger.info("Usuario: "+usuarioBean.getLogin());
					doExecuteAux(formulario,usuarioBean);
					
				}else{
					int perfil = 0;
					int codUsuario = 0;
					int idCentroGestor = 0;
					try{
						perfil = Integer.parseInt(formulario.getIdPerfil());
					}catch(Exception e){
						logger.error("Error VerModificarUsuarioSpring - parsear perfil"+ perfil,e);
					}
					try{
						codUsuario = Integer.parseInt(formulario.getId());
					}catch(Exception e){
						logger.error("Error VerModificarUsuarioSpring - parsear codUsuario"+ codUsuario,e);
					}
					try{
						idCentroGestor = Integer.parseInt(formulario.getIdCentroGestor());
					}catch(Exception e){
						logger.error("Error VerModificarUsuarioSpring - parsear CentroGestor"+ idCentroGestor,e);
					}
					
				}
				this.setRequestAttribute("usuario", usuarioLogin);				
			}else{
				resultado = "error";
			}
			
			this.setRequestAttribute("centrosGestores",centrosGestores);
			this.setRequestAttribute("perfiles",perfiles);
			setRequestAttribute("sPerfilUsuario", sPerfilUsuario);
			/*INI-TRA042*/
			setRequestAttribute("listaCentrosGestores", formulario.getListaCentrosGestores());
			resultado = "success";
			/*FIN-TRA042*/
			getLogger().debug("VerModificarUsuarioSpring -end");
			return resultado;		
			
		}catch (Exception e){
			logger.warn(e);
			this.getRequest().setAttribute("descripcionError", e.getMessage());
			logger.error("Error VerModificarUsuarioSpring - doExecute",e);
			return "nosuccess";}
	}
		
	/**
	 * Do execute aux.
	 *
	 * @param formulario el formulario
	 * @param usuarioBean el usuario bean
	 */
	private void doExecuteAux(UsuarioForm formulario, UsuarioBean usuarioBean) {
		
		formulario.setIdPerfil(usuarioBean.getIdPerfil().toString());
		formulario.setEstado(usuarioBean.getEstado().toString());
		formulario.setRecibeCorreosIncidencias(usuarioBean.getRecibeCorreosIncidencias());
		formulario.setPublicaConvocatorias(usuarioBean.getPublicaConvocatorias());
		formulario.setNif(!StringUtils.isEmpty(usuarioBean.getNif())?usuarioBean.getNif():"");
		formulario.setNombre(!StringUtils.isEmpty(usuarioBean.getNombre())?usuarioBean.getNombre():"");
		formulario.setPrimerApellido(!StringUtils.isEmpty(usuarioBean.getPrimerApellido())?usuarioBean.getPrimerApellido():"");
		formulario.setSegundoApellido(!StringUtils.isEmpty(usuarioBean.getSegundoApellido())?usuarioBean.getSegundoApellido():"");
		formulario.setEmail(!StringUtils.isEmpty(usuarioBean.getEmail())?usuarioBean.getEmail():"");
		formulario.setTelefono(!StringUtils.isEmpty(usuarioBean.getTelefono())?usuarioBean.getTelefono():"");
		
		/*INI-TRA042*/
		formulario.setListaCentrosGestores(usuarioBean.getUsuCg().getCentroGestor());
		/*FIN-TRA042*/
	}
	
	/**
	 * Comprobar perfil usuario.
	 *
	 * @param idUsuario el id usuario
	 * @return el string
	 */
	private String comprobarPerfilUsuario(Integer idUsuario) {
		String sPerfil = "";
		UsuarioBean usuarioBean;
		
		usuarioBean = usuarioManager.obtenerUsuario(idUsuario);
		if(usuarioBean.getIdPerfil() != null &&   usuarioBean.getIdPerfil().toString().equals(Constantes.PERFIL_CONSULTOR))
		{
			sPerfil = Constantes.ROL_CONSULTOR;
		}
		if(usuarioBean.getIdPerfil() != null &&   usuarioBean.getIdPerfil().toString().equals(Constantes.PERFIL_SOPORTE))
		{
			sPerfil = Constantes.ROL_SOPORTE;
		}
		if(usuarioBean.getIdPerfil() != null && usuarioBean.getIdPerfil().toString().equals(Constantes.PERFIL_GESTOR))
		{
			sPerfil = Constantes.ROL_GESTOR;
		}
		if(usuarioBean.getIdPerfil() != null && usuarioBean.getIdPerfil().toString().equals(Constantes.PERFIL_ADMINISTRADOR))
		{
			sPerfil = Constantes.ROL_ADMINISTRADOR;
		}

		return sPerfil;
	}

	/**
	 * Obtiene el usuario manager.
	 *
	 * @return el usuario manager
	 */
	public UsuarioManager getUsuarioManager() {
		return usuarioManager;
	}

	/**
	 * Establece el usuario manager.
	 *
	 * @param usuarioManager el nuevo usuario manager
	 */
	public void setUsuarioManager(UsuarioManager usuarioManager) {
		this.usuarioManager = usuarioManager;
	}
	
	/**
	 * Obtiene el perfil manager.
	 *
	 * @return el perfil manager
	 */
	public PerfilManager getPerfilManager() {
		return perfilManager;
	}

	/**
	 * Establece el perfil manager.
	 *
	 * @param perfilManager el nuevo perfil manager
	 */
	public void setPerfilManager(PerfilManager perfilManager) {
		this.perfilManager = perfilManager;
	}

	/**
	 * Obtiene el centro gestor manager.
	 *
	 * @return el centro gestor manager
	 */
	public CentroGestorManager getCentroGestorManager() {
		return centroGestorManager;
	}

	/**
	 * Establece el centro gestor manager.
	 *
	 * @param centroGestorManager el nuevo centro gestor manager
	 */
	public void setCentroGestorManager(CentroGestorManager centroGestorManager) {
		this.centroGestorManager = centroGestorManager;
	}

	/**
	 * Obtiene el logger.
	 *
	 * @return el logger
	 */
	public static Logger getLogger() {
		return logger;
	}

	/*INI-TRA042*/
	/**
	 * @return the usuarioCentrogestorManager
	 */
	public UsuarioCentrogestorManager getUsuarioCentrogestorManager() {
		return usuarioCentrogestorManager;
	}

	/**
	 * @param usuarioCentrogestorManager the usuarioCentrogestorManager to set
	 */
	public void setUsuarioCentrogestorManager(UsuarioCentrogestorManager usuarioCentrogestorManager) {
		this.usuarioCentrogestorManager = usuarioCentrogestorManager;
	}
	/*FIN-TRA042*/
}
