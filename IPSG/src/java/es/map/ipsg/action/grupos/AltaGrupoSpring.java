package es.map.ipsg.action.grupos;


import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.GrupoQuery;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.bean.GrupoBean;
import es.map.ipsg.bean.LogGenericoBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.form.GrupoForm;
import es.map.ipsg.manager.GrupoManager;
import es.map.ipsg.manager.LogGenericoManager;
import es.map.ipsg.manager.UsuarioManager;
import es.map.ipsg.util.Constantes;


/**
 * El Class AltaGrupoSpring.
 */
public class AltaGrupoSpring extends AbstractSpring {
	
	/** el grupo manager. */
	private GrupoManager grupoManager;
	
	/** el usuario manager. */
	private UsuarioManager usuarioManager;
	
	/** el log generico manager. */
	private LogGenericoManager logGenericoManager;
	
	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(AltaGrupoSpring.class);
	
	/** La constante STRING_FIELDGRUPOALTATITULO. */
	private static final String STRING_FIELDGRUPOALTATITULO = "field.grupo.alta.titulo";
	
	/** La constante STRING_ACCION. */
	private static final String STRING_ACCION = "accion";
	
	/** La constante STRING_TITULO. */
	private static final String STRING_TITULO = "titulo";
	
	/** La constante STRING_MENSAJE. */
	private static final String STRING_MENSAJE = "mensaje";
	
	/** La constante STRING_SUCCESSMENSAJE. */
	private static final String STRING_SUCCESSMENSAJE = "successMensaje";
		
	/**
	 * Crea una nueva alta grupo spring.
	 */
	public AltaGrupoSpring() {
		try {
			setGrupoManager((GrupoManager) getBean("grupoManager"));
			setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
			setLogGenericoManager((LogGenericoManager) getBean("logGenericoManager"));
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error AltaGrupoSpring:",e);
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		
		String menu_tablaBase = RESOURCE_BUNDLE.getString("field.menu.tablasBase");
		this.getRequest().getSession().setAttribute("pagActiva", menu_tablaBase);
		String subMenu_grupo = RESOURCE_BUNDLE.getString("field.menuLateral.tablasBase.grupo");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_grupo);
		
		getLogger().debug("AltaGrupoSpring -start");
		GrupoForm formulario = (GrupoForm) form;		
		GrupoBean grupoBean = new GrupoBean();
		String busqueda = formulario.getSubmit();
		try{
			String titulo = "";
			String mensaje = "";
			if("Guardar".equals(busqueda)){
				grupoBean.setCodigo(formulario.getCodigo());
				grupoBean.setDescripcion(formulario.getDescripcion());
				grupoBean.setEstado(Constantes.MINISTERIO_ESTADO_ACTIVO);
				grupoBean.setSiglas(formulario.getSiglas());
				if(formulario.getVisibilidad() != null)
				{	
					grupoBean.setVisibilidad(formulario.getVisibilidad());
				}
				else
				{
					grupoBean.setVisibilidad(false);
				}	
				if(grupoBean.getCodigo() != null && !"".equals(grupoBean.getCodigo())){
					GrupoQuery grupoQuery = new GrupoQuery();
					grupoQuery.setCodigo(grupoBean.getCodigo());
					grupoQuery.setEstado(Constantes.GRUPO_ESTADO_ACTIVO);
					GrupoBean GrupoAux = null;
					try{
					GrupoAux = grupoManager.buscarGrupoUnico(grupoQuery);
					}catch(Exception e){
						logger.error("Error AltaGrupoSpring - doExecute - buscarGrupoUnico:",e);
						mensaje = RESOURCE_BUNDLE.getString("field.grupo.alta.mensajeError");
						titulo = RESOURCE_BUNDLE.getString(STRING_FIELDGRUPOALTATITULO);
						setRequestAttribute(STRING_ACCION,"/spring/verAltaGrupo");
						setRequestAttribute(STRING_TITULO,titulo);
						setRequestAttribute(STRING_MENSAJE,mensaje);
						return STRING_SUCCESSMENSAJE;
					}
					if(GrupoAux.getId() == null){
						int idMinisterio = grupoManager.guardarGrupo(grupoBean);
						formulario.setDescripcion("");
						formulario.setCodigo("");
						formulario.setSiglas("");
						UsuarioBean usuarioBean = (UsuarioBean) getRequest().getSession().getAttribute("usuario");
						generarRegistroLogGenerico(usuarioBean.getLogin(),grupoBean.getDescripcion(), Long.valueOf(idMinisterio));
					}else{
						mensaje = RESOURCE_BUNDLE.getString("field.grupo.alta.mensajeError");
						titulo = RESOURCE_BUNDLE.getString(STRING_FIELDGRUPOALTATITULO);
						setRequestAttribute(STRING_ACCION,"/spring/verAltaGrupo");
						setRequestAttribute(STRING_TITULO,titulo);
						setRequestAttribute(STRING_MENSAJE,mensaje);
						return STRING_SUCCESSMENSAJE;
					}
				}	
			}else{
				if("Limpiar".equals(busqueda)){
					formulario.setDescripcion("");
					formulario.setCodigo("");
				}
			}
			
			
			if("Guardar".equals(busqueda)){
				mensaje = RESOURCE_BUNDLE.getString("field.grupo.alta.mensaje");
				titulo = RESOURCE_BUNDLE.getString(STRING_FIELDGRUPOALTATITULO);
				setRequestAttribute(STRING_ACCION,"/spring/buscarGrupo");
				setRequestAttribute(STRING_TITULO,titulo);
				setRequestAttribute(STRING_MENSAJE,mensaje);
				return STRING_SUCCESSMENSAJE;
			}		
			
			getLogger().debug("AltaGrupoSpring -end");
			return "success";
		}catch (Exception e){
			logger.warn(e);
			this.getRequest().setAttribute("descripcionError", e.getMessage());
			logger.error("Error AltaGrupoSpring - doExecute:",e);
			return "nosuccess";
		}
	}
	
	/**
	 * Generar registro log generico.
	 *
	 * @param username el username
	 * @param grupoDesc el grupo desc
	 * @param resultado el resultado
	 */
	public void generarRegistroLogGenerico(String username, String grupoDesc, Long resultado){
		
		UsuarioQuery usuarioQuery = new UsuarioQuery();
		usuarioQuery.setLogin(username);
		UsuarioBean usuarioBean2 = usuarioManager.buscarUsuario(usuarioQuery);
		
		//Cargo los datos en el bean del log generico que se usara para crear el registro en la tabla
		LogGenericoBean logGenericoBean = new LogGenericoBean();
		logGenericoBean.setTipoFuncionalidad(Constantes.FUNCIONALIDAD_GRUPO);
		logGenericoBean.setTipoOperacion(Constantes.OPERACION_ALTA);
		logGenericoBean.setDetalleOperacion(RESOURCE_BUNDLE.getString("field.grupo.detalleOperacionAlta") + " "  + grupoDesc);
		logGenericoBean.setIdTablaOrigen(resultado);
		logGenericoBean.setUsuario(usuarioManager.toUsuario(usuarioBean2));
		
		logGenericoManager.generarRegistroLog(logGenericoBean);
	}
	


	/**
	 * Obtiene el grupo manager.
	 *
	 * @return el grupo manager
	 */
	public GrupoManager getGrupoManager() {
		return grupoManager;
	}

	/**
	 * Establece el grupo manager.
	 *
	 * @param grupoManager el nuevo grupo manager
	 */
	public void setGrupoManager(GrupoManager grupoManager) {
		this.grupoManager = grupoManager;
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

}
