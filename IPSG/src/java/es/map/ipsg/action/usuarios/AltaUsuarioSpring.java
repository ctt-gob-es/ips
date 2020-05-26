package es.map.ipsg.action.usuarios;

import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import es.map.ips.common.exception.ApplicationException;
import es.map.ips.common.exception.BusinessException;
import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringMessage;
import es.map.ips.common.spring.SpringMessages;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.bean.LogGenericoBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.form.UsuarioForm;
import es.map.ipsg.manager.CentroGestorManager;
import es.map.ipsg.manager.LogGenericoManager;
import es.map.ipsg.manager.PerfilManager;
import es.map.ipsg.manager.UsuarioCentrogestorManager;
import es.map.ipsg.manager.UsuarioManager;
import es.map.ipsg.util.Constantes;
import es.map.ipsg.util.CryptUtil;

/**
 * El Class AltaUsuarioSpring.
 */
public class AltaUsuarioSpring extends AbstractSpring {

	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(AltaUsuarioSpring.class);
	
	/** La constante STRING_ERROR. */
	private static final String STRING_ERROR = "error";
	
	/** el usuario manager. */
	private UsuarioManager usuarioManager;
	
	/** el perfil manager. */
	private PerfilManager perfilManager;
	
	/** el centro gestor manager. */
	private CentroGestorManager centroGestorManager;
	
	/** el log generico manager. */
	private LogGenericoManager logGenericoManager;
	
	/** el usuario centro gestor manager. */
	private UsuarioCentrogestorManager usuarioCentrogestorManager;
		
	/**
	 * Crea una nueva alta usuario spring.
	 */
	public AltaUsuarioSpring() {
		try {
			setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
			setPerfilManager((PerfilManager) getBean("perfilManager"));
			setCentroGestorManager((CentroGestorManager) getBean("centrosGestoresManager"));
			setLogGenericoManager((LogGenericoManager) getBean("logGenericoManager"));
			setUsuarioCentrogestorManager((UsuarioCentrogestorManager) getBean("usuarioCentrogestorManager"));
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error AltaUsuarioSpring - crear constructor",e);
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		
		String menu_usuario = RESOURCE_BUNDLE.getString("field.menu.usuarios");
		this.getRequest().getSession().setAttribute("pagActiva", menu_usuario);
		String subMenu_usuario = RESOURCE_BUNDLE.getString("field.menuLateral.usuarios.alta");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_usuario);
		
		getLogger().debug("AltaUsuarioSpring -start");
		logger.info("Entra en el Action");
		String resultado = null;
		String mensaje = null;
		
		UsuarioForm formulario = (UsuarioForm) form;
		
		try{
			
		UsuarioBean usuarioBean = new UsuarioBean();
		
		String s_perfil = formulario.getIdPerfil();
		String s_centro = formulario.getIdCentroGestor();
		String s_login = formulario.getLogin();
		String nif = formulario.getNif();
		boolean existeNif = false;
		boolean existeLogin = false;
		existeNif = existeNif(nif);
		try{
			if(!s_perfil.equals(Constantes.PERFIL_RECEPTOR)){
				existeLogin = existeLogin(s_login);
			}
		}catch(ApplicationException ae){
			logger.error("Error AltaUsuarioSpring - error perfil ",ae);
			 resultado = STRING_ERROR;
			 mensaje = ae.getMessage();
		}
		
		if(!STRING_ERROR.equals(resultado)){
			if(!existeNif){
				if(existeLogin){
					logger.info("El Login ya existe");
					
					SpringMessages messages = new SpringMessages();
					messages.add("errorExisteLogin",new SpringMessage("usuario.error.ExisteLogin.a",s_login));
					saveErrors(this.getRequest(),messages);
					
					resultado = "show";
				}else{
					//Alta de Usuario
					logger.info("Dando de Alta el Usuario");
					usuarioBean.setEstado(Constantes.USUARIO_ESTADO_ACTIVO);
					usuarioBean.setNif(formulario.getNif());
					usuarioBean.setNombre(formulario.getNombre());
					usuarioBean.setPrimerApellido(formulario.getPrimerApellido());
					usuarioBean.setSegundoApellido(formulario.getSegundoApellido());
					usuarioBean.setEmail(formulario.getEmail());
					usuarioBean.setTelefono(formulario.getTelefono());
					usuarioBean.setPublicaConvocatorias(formulario.getPublicaConvocatorias());
					usuarioBean.setRecibeCorreosIncidencias(formulario.getRecibeCorreosIncidencias());
					
					usuarioBean.setUltimaActPassword(new Date());
					
					if(s_perfil!=null && !s_perfil.equals("")){
						usuarioBean.setIdPerfil(Integer.valueOf(s_perfil));
					}
					
					if(!s_perfil.equals(Constantes.PERFIL_RECEPTOR)){//El Perfil Receptor no requiere login y password
						
						usuarioBean.setLogin(s_login);
						
						String token = new String();
						// Se crea el token aleatorio Salt
						int i = 0;
						while ( i < Constantes.LONG_MAXIMA_TOKEN)
						{
							int x=48;
							int w=122;
							Character c = (char) ((w - x + 1) * Math.random() +x) ;

							if ( (c >= 'a' && c <='z') || (c >='A' && c <='Z') || (c >= '0' && c <='9') )
							{
								token += c;
								i ++;
							}
						}
								
						usuarioBean.setToken(token);
						// Se concatena Salt+password introducida
						String passSalt = formulario.getPassword().concat(token);
						logger.info("PasswordSalt: "+passSalt);
						String passwordMD5 = new CryptUtil().cifrar(passSalt);
						logger.info("PasswordSaltMD5: "+passwordMD5);
						usuarioBean.setPassword(passwordMD5);
					}				
					
					try{						
						int result = usuarioManager.guardarUsuario(usuarioBean);
						if(!Constantes.PERFIL_ADMINISTRADOR.equals(s_perfil)) {
						/*INI-TRA042*/
						guardarUsuCentroGestor(result, formulario.getIdCg());
						/*FIN-TRA042*/
						}
						if(result>0){
							//Obtengo los datos del usuario que esta logeado en la aplicacion
							UsuarioBean usuarioSession = (UsuarioBean) getRequest().getSession().getAttribute("usuario");
							generarRegistroLogGenerico(usuarioSession.getLogin(),usuarioSession.getNif(),new Long(result));
							resultado="success";
							mensaje = RESOURCE_BUNDLE.getString("field.usuario.altaUsuarioConfirmacion");
						}else{
							resultado=STRING_ERROR;
							mensaje = RESOURCE_BUNDLE.getString("field.usuario.altaUsuarioError");
						}
					}catch(BusinessException be){
						logger.error("Error AltaUsuarioSpring - error guardando usuario",be);
						resultado = STRING_ERROR;
						mensaje = be.getMessage();
					}
				}
			}else{
				SpringMessages messages = new SpringMessages();
				messages.add("errorExisteNif",new SpringMessage("usuario.error.ExisteNif.a",nif));
				saveErrors(this.getRequest(),messages);
				
				resultado = "show";
			}
		}
		
		if(resultado != null){
			String titulo = RESOURCE_BUNDLE.getString("field.usuario.tituloAltaUsuario");
			
			setRequestAttribute("titulo",titulo);
			setRequestAttribute("mensaje",mensaje);
			setRequestAttribute("accion","/spring/buscarUsuarios");
		}		
		getLogger().debug("AltaUsuarioSpring -end");
		return resultado;
						
		}catch (Exception e){
			logger.warn(e);
			this.getRequest().setAttribute("descripcionError", e.getMessage());
			logger.error("Error AltaUsuarioSpring - doExecute",e);
			return "nosuccess";}
	}
	
	/**
	 * Existe nif.
	 *
	 * @param nif el nif
	 * @return verdadero, si tiene exito
	 */
	private boolean existeNif(String nif) {
		
		UsuarioQuery usuarioQuery = new UsuarioQuery();
		usuarioQuery.setNif(nif);
		ArrayList<UsuarioBean> usuAux = (ArrayList<UsuarioBean>) usuarioManager.buscarUsuarios(usuarioQuery);
		if (usuAux.size()>0){
			return true;
		}
		return false;
	}

	/**
	 * Generar registro log generico.
	 *
	 * @param username el username
	 * @param nif el nif
	 * @param resultado el resultado
	 */
	public void generarRegistroLogGenerico(String username, String nif, Long resultado){
		
		UsuarioQuery usuarioQuery = new UsuarioQuery();
		usuarioQuery.setLogin(username);
		UsuarioBean usuarioBean2 = usuarioManager.buscarUsuario(usuarioQuery);
		
		//Cargo los datos en el bean del log generico que se usara para crear el registro en la tabla
		LogGenericoBean logGenericoBean = new LogGenericoBean();
		logGenericoBean.setTipoFuncionalidad(Constantes.FUNCIONALIDAD_USUARIO);
		logGenericoBean.setTipoOperacion(Constantes.OPERACION_ALTA);
		logGenericoBean.setDetalleOperacion(RESOURCE_BUNDLE.getString("field.usuario.detalleOperacionAlta") + " "  + nif);
		logGenericoBean.setIdTablaOrigen(resultado);
		logGenericoBean.setUsuario(usuarioManager.toUsuario(usuarioBean2));
		
		logGenericoManager.generarRegistroLog(logGenericoBean);
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
	 * Existe login.
	 *
	 * @param login el login
	 * @return verdadero, si tiene exito
	 */
	public boolean existeLogin(String login){
		ApplicationException.assertNotNull(login, "login no puede ser null");
		UsuarioQuery usuarioQuery = new UsuarioQuery();
		usuarioQuery.setLogin(login);
		
		UsuarioBean usuarioBean = usuarioManager.buscarUsuario(usuarioQuery);
		return usuarioBean==null ? false:true;
	}
	
	/*INI-TRA042*/
	/**
	 * Guardar Usu Centro Gestor .
	 *
	 * @param result el result
	 * @param idCg el idCg
	 */
	
	private void guardarUsuCentroGestor(int result, String idCg) {
		if(idCg != null) {
			String[] listaIdsCg = idCg.split(",");
			if(listaIdsCg != null) {
			for(int i=0; i<listaIdsCg.length;i++) {
				usuarioCentrogestorManager.guardarUsuCentroGestor(result, Integer.parseInt(listaIdsCg[i]));
			}
			}
		}	
	}
	
	/*FIN-TRA042*/
	
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
	 * Obtiene el log generico manager.
	 *
	 * @return el log generico manager
	 */
	public LogGenericoManager getLogGenericoManager() {
		return logGenericoManager;
	}

	/**
	 * Establece el log generico manager.
	 *
	 * @param logGenericoManager el nuevo log generico manager
	 */
	public void setLogGenericoManager(LogGenericoManager logGenericoManager) {
		this.logGenericoManager = logGenericoManager;
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
