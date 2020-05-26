package es.map.ipsg.action.ministerio;

import java.util.Date;

import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.util.DateUtil;
import es.map.ips.model.MinisterioQuery;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.bean.LogGenericoBean;
import es.map.ipsg.bean.MinisterioBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.form.MinisterioForm;
import es.map.ipsg.manager.LogGenericoManager;
import es.map.ipsg.manager.MinisterioManager;
import es.map.ipsg.manager.UsuarioManager;
import es.map.ipsg.util.Constantes;


/**
 * El Class AltaMinisteriosSpring.
 */
public class AltaMinisteriosSpring extends AbstractSpring {
	
	/** el ministerio manager. */
	private MinisterioManager ministerioManager;
	
	/** el usuario manager. */
	private UsuarioManager usuarioManager;
	
	/** el log generico manager. */
	private LogGenericoManager logGenericoManager;
	
	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(AltaMinisteriosSpring.class);
		
	/**
	 * Crea una nueva alta ministerios spring.
	 */
	public AltaMinisteriosSpring() {	
		try {
			setMinisterioManager((MinisterioManager) getBean("ministeriosManager"));
			setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
			setLogGenericoManager((LogGenericoManager) getBean("logGenericoManager"));
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error AltaMinisteriosSpring:",e);
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		
		String menu_tablaBase = RESOURCE_BUNDLE.getString("field.menu.tablasBase");
		this.getRequest().getSession().setAttribute("pagActiva", menu_tablaBase);
		String subMenu_ministerio = RESOURCE_BUNDLE.getString("field.menuLateral.tablasBase.ministerio");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_ministerio);
		
		getLogger().debug("AltaMinisteriosSpring -start");
		MinisterioForm formulario = (MinisterioForm) form;		
		try{
			MinisterioBean ministerioBean = new MinisterioBean();
			String busqueda = formulario.getSubmit();
			String titulo = "";
			String mensaje = "";
			if("Guardar".equals(busqueda)){
				String fechaForm = formulario.getFechaSustitucion();

				if(fechaForm != null && !"".equals(fechaForm)){
					Date fecha = DateUtil.parse(fechaForm,"dd/MM/yyyy");
					ministerioBean.setFechaSustitucion(fecha);
				}
				
				ministerioBean.setSiglas(formulario.getSiglas());
				ministerioBean.setCodigo(formulario.getCodigo());
				ministerioBean.setDescripcion(formulario.getDescripcion());
				ministerioBean.setUrl(formulario.getUrl());
				ministerioBean.setIdMinisterioPrevio(formulario.getIdMinisterioPrevio());
				ministerioBean.setVisibilidad(formulario.getVisibilidad());
				
				ministerioBean.setEstado(Constantes.MINISTERIO_ESTADO_ACTIVO);
				
				if(ministerioBean.getCodigo() != null && !"".equals(ministerioBean.getCodigo())){
					
					MinisterioQuery ministerioQuery = new MinisterioQuery();
					ministerioQuery.setCodigo(ministerioBean.getCodigo());
					ministerioQuery.setEstado(Constantes.MINISTERIO_ESTADO_ACTIVO);
								

 					int idMinisterio = ministerioManager.guardarMinisterio(ministerioBean);

					
					UsuarioBean usuarioBean = (UsuarioBean) getRequest().getSession().getAttribute("usuario");
					generarRegistroLogGenerico(usuarioBean.getLogin(),ministerioBean.getDescripcion(),Long.valueOf(idMinisterio));
					formulario.setDescripcion("");
					formulario.setCodigo("");
					formulario.setSiglas("");
					formulario.setUrl("");
					formulario.setVisibilidad(true);
				}	
		}else{
			if("Limpiar".equals(busqueda)){
				formulario.setDescripcion("");
				formulario.setCodigo("");
				formulario.setSiglas("");
				formulario.setUrl("");
			}
		}
			
			if("Guardar".equals(busqueda)){
				mensaje = RESOURCE_BUNDLE.getString("field.ministerio.alta.mensaje");
				titulo = RESOURCE_BUNDLE.getString("field.ministerio.alta.titulo");
				setRequestAttribute("accion","/spring/buscarMinisterios");
				setRequestAttribute("titulo",titulo);
				setRequestAttribute("mensaje",mensaje);
				return "successMensaje";
			}else{
				if("Error".equals(busqueda)){
					mensaje = RESOURCE_BUNDLE.getString("field.ministerio.alta.mensajeError");
					titulo = RESOURCE_BUNDLE.getString("field.ministerio.alta.titulo");
					setRequestAttribute("accion","/spring/altaMinisterio");
					setRequestAttribute("titulo",titulo);
					setRequestAttribute("mensaje",mensaje);
					return "successMensaje";
				}			
			}
			
			getLogger().debug("AltaMinisteriosSpring -end");
			return "success";
		
		}catch(Exception e){
			logger.warn(e);
			this.getRequest().setAttribute("descripcionError", e.getMessage());
			logger.error("Error AltaMinisteriosSpring - doExecute:",e);
			return "nosuccess";
		}
	}

	/**
	 * Generar registro log generico.
	 *
	 * @param username el username
	 * @param ministerioDesc el ministerio desc
	 * @param resultado el resultado
	 */
	public void generarRegistroLogGenerico(String username, String ministerioDesc, Long resultado){
		
		UsuarioQuery usuarioQuery = new UsuarioQuery();
		usuarioQuery.setLogin(username);
		UsuarioBean usuarioBean2 = usuarioManager.buscarUsuario(usuarioQuery);
		
		//Cargo los datos en el bean del log generico que se usara para crear el registro en la tabla
		LogGenericoBean logGenericoBean = new LogGenericoBean();
		logGenericoBean.setTipoFuncionalidad(Constantes.FUNCIONALIDAD_MINISTERIO);
		logGenericoBean.setTipoOperacion(Constantes.OPERACION_ALTA);
		logGenericoBean.setDetalleOperacion(RESOURCE_BUNDLE.getString("field.ministerio.detalleOperacionAlta") + " "  + ministerioDesc);
		logGenericoBean.setIdTablaOrigen(resultado);
		logGenericoBean.setUsuario(usuarioManager.toUsuario(usuarioBean2));
		
		logGenericoManager.generarRegistroLog(logGenericoBean);
	}
	

	
	/**
	 * Obtiene el ministerio manager.
	 *
	 * @return el ministerio manager
	 */
	public MinisterioManager getMinisterioManager() {
		return ministerioManager;
	}

	/**
	 * Establece el ministerio manager.
	 *
	 * @param ministerioManager el nuevo ministerio manager
	 */
	public void setMinisterioManager(MinisterioManager ministerioManager) {
		this.ministerioManager = ministerioManager;
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
