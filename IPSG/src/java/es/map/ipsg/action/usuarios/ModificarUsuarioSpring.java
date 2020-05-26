package es.map.ipsg.action.usuarios;

import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import es.map.ips.common.exception.BusinessException;
import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringMessage;
import es.map.ips.common.spring.SpringMessages;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.bean.LogGenericoBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.form.UsuarioForm;
import es.map.ipsg.manager.LogGenericoManager;
import es.map.ipsg.manager.UsuarioCentrogestorManager;
import es.map.ipsg.manager.UsuarioManager;
import es.map.ipsg.util.Constantes;

/**
 * El Class ModificarUsuarioSpring.
 */
@SuppressWarnings("rawtypes")
public class ModificarUsuarioSpring extends AbstractSpring {

	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(ModificarUsuarioSpring.class);

	/** el usuario manager. */
	private UsuarioManager usuarioManager;
	
	/** el log generico manager. */
	private LogGenericoManager logGenericoManager;
		
	/** el usuario centro gestor manager. */
	private UsuarioCentrogestorManager usuarioCentrogestorManager;
	
	/**
	 * Crea una nueva modificar usuario spring.
	 */
	public ModificarUsuarioSpring() {
		try {
			setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
			setLogGenericoManager((LogGenericoManager) getBean("logGenericoManager"));
			setUsuarioCentrogestorManager((UsuarioCentrogestorManager) getBean("usuarioCentrogestorManager"));
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error ModificarUsuarioSpring - crear constructor",e);
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
		
		getLogger().debug("ModificarUsuarioSpring -start");
		logger.info("Entra en el Action ModificarUsuarioAction");
		String resultado;
		try{
			UsuarioForm formulario = (UsuarioForm) form;
		String idUsuario = formulario.getId();
		String nif = formulario.getNif();
		boolean existeNif = false;
		existeNif = existeNif(nif,formulario);
		
		if(!existeNif){
			if(idUsuario!=null){//Modificacion de Usuario
				UsuarioQuery usuarioQuery = new UsuarioQuery();
				usuarioQuery.setId(Integer.valueOf(formulario.getId()));
				UsuarioBean usuarioBeanAnterior = usuarioManager.buscarUsuario(usuarioQuery);
				
				UsuarioBean usuarioBean = new UsuarioBean();
				
				usuarioBean.setId(Integer.valueOf(formulario.getId()));
				usuarioBean.setEstado(formulario.getEstado().charAt(0));
				usuarioBean.setNif(formulario.getNif());
				usuarioBean.setNombre(formulario.getNombre());
				usuarioBean.setPrimerApellido(formulario.getPrimerApellido());
				usuarioBean.setSegundoApellido(formulario.getSegundoApellido());
				usuarioBean.setEmail(formulario.getEmail());
				usuarioBean.setTelefono(formulario.getTelefono());
				usuarioBean.setPublicaConvocatorias(formulario.getPublicaConvocatorias());
				usuarioBean.setRecibeCorreosIncidencias(formulario.getRecibeCorreosIncidencias());
				usuarioBean.setIdPerfil(Integer.valueOf(formulario.getIdPerfil()));
				usuarioBean.setLogin(formulario.getLogin());
				
				usuarioBean.setUltimaActPassword(new Date());				


				try{
					/*INI-TRA042*/
					String cambios = usuarioManager.compararUsuarios(usuarioBeanAnterior,usuarioBean);
				
					UsuarioBean usuarioAntiguo = usuarioManager.obtenerUsuario(Integer.valueOf(formulario.getId()));
					
					usuarioBean.setLogin(!StringUtils.isEmpty(usuarioAntiguo.getLogin())?usuarioAntiguo.getLogin():"");
					usuarioBean.setPassword(usuarioAntiguo.getPassword());
					usuarioBean.setToken(usuarioAntiguo.getToken());
					usuarioManager.modificarUsuario(usuarioBean);
					
					usuarioCentrogestorManager.modificarRelaciones(usuarioBean.getId(),formulario.getIdCg());					
					/*FIN-TRA042*/
					String mensaje = RESOURCE_BUNDLE.getString("field.usuario.modificarUsuarioConfirmacion");
					String titulo = RESOURCE_BUNDLE.getString("field.usuario.tituloMantenimientoUsuario");			
					
					setRequestAttribute("titulo",titulo);
					setRequestAttribute("mensaje",mensaje);
					setRequestAttribute("accion","/spring/buscarUsuarios");
					
					//Obtengo los datos del usuario que esta logeado en la aplicacion
					UsuarioBean usuarioSession = (UsuarioBean) getRequest().getSession().getAttribute("usuario");
					generarRegistroLogGenerico(usuarioSession.getLogin(),cambios,new Long(usuarioBean.getId()));
				
					resultado = "success";
				}catch(BusinessException be){
					logger.error("Error ModificarUsuarioSpring - obtener usuario a modificar",be);
					SpringMessages messages = new SpringMessages();
					messages.add("errorModificarUsuario",new SpringMessage(be.getKey()));
					saveErrors(this.getRequest(),messages);
					
					resultado = "show";
				}
			}else{
				resultado = "error";
			}
		}else{
			SpringMessages messages = new SpringMessages();
			messages.add("errorExisteNif",new SpringMessage("usuario.error.ExisteNif",nif));
			saveErrors(this.getRequest(),messages);
			
			resultado = "show";
		}
		logger.info("resultado: "+resultado);
		getLogger().debug("ModificarUsuarioSpring -end");
		return resultado;
			
		}catch (Exception e){
			logger.warn(e);
			this.getRequest().setAttribute("descripcionError", e.getMessage());
			logger.error("Error ModificarUsuarioSpring - doExecute ",e);
			return "nosuccess";}
	}
	
	/**
	 * Existe nif.
	 *
	 * @param nif el nif
	 * @param formulario el formulario
	 * @return verdadero, si tiene exito
	 */
	private boolean existeNif(String nif, UsuarioForm formulario) {
		
		UsuarioQuery usuarioQuery = new UsuarioQuery();
		usuarioQuery.setNif(nif);
		ArrayList<UsuarioBean> usuAux = (ArrayList<UsuarioBean>) usuarioManager.buscarUsuarios(usuarioQuery);
		if (usuAux!= null && usuAux.size()>0){
			for(int i=0;i<usuAux.size();i++){
				if(!usuAux.get(i).getId().toString().equals(formulario.getId())){
					return true;
				}
			}
		}
			
		return false;
	}
	
	/**
	 * Generar registro log generico.
	 *
	 * @param username el username
	 * @param cambios el cambios
	 * @param idUsuario el id usuario
	 */
	public void generarRegistroLogGenerico(String username, String cambios, Long idUsuario){
		
		UsuarioQuery usuarioQuery = new UsuarioQuery();
		usuarioQuery.setLogin(username);
		UsuarioBean usuarioBean2 = usuarioManager.buscarUsuario(usuarioQuery);
		
		//Cargo los datos en el bean del log generico que se usara para crear el registro en la tabla
		LogGenericoBean logGenericoBean = new LogGenericoBean();
		logGenericoBean.setTipoFuncionalidad(Constantes.FUNCIONALIDAD_USUARIO);
		logGenericoBean.setTipoOperacion(Constantes.OPERACION_MODIFICACION);
		if (cambios.compareTo("")!=0){
			logGenericoBean.setDetalleOperacion(cambios);
		}else{
			logGenericoBean.setDetalleOperacion(RESOURCE_BUNDLE.getString("field.usuario.detalleOperacionNoModificacion"));
		}
		logGenericoBean.setIdTablaOrigen(idUsuario);
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
	 * Establece el usuario manager.
	 *
	 * @param usuarioManager el nuevo usuario manager
	 */
	public void setUsuarioManager(UsuarioManager usuarioManager) {
		this.usuarioManager = usuarioManager;
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
