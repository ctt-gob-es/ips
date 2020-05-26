package es.map.ipsg.action.convocatoria;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.ConvocatoriaQuery;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.bean.DetalleConvocatoriaBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.manager.ConvocatoriasManager;
import es.map.ipsg.manager.UsuarioManager;


/**
 * El Class DetalleConvocatoriaSpring.
 */
public class DetalleConvocatoriaSpring extends AbstractSpring {

	/** el usuario manager. */
	private UsuarioManager usuarioManager;
	
	/** el convocatorias manager. */
	private ConvocatoriasManager convocatoriasManager;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(DetalleConvocatoriaSpring.class);

	/**
	 * Crea una nueva detalle convocatoria spring.
	 */
	public DetalleConvocatoriaSpring() {
		try {
			setConvocatoriasManager((ConvocatoriasManager) getBean("convocatoriaManager"));
			setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error DetalleConvocatoriaSpring() ",e);
		}

	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		getLogger().debug("DetalleConvocatoriaSpring -start");
		logger.info("Action Detalle Convocatoria");
		String codSolicitud = (String) this.getRequest().getParameter("registro");
		logger.info("registro: "+codSolicitud);
		
		try{
			checkRolUsuario();
			Boolean actualizacion = (Boolean) this.getRequest().getAttribute("actualizacion");
			String detalleError = (String) this.getRequest().getAttribute("error");
			logger.info("codSolicitud: "+codSolicitud);
			if("".equals(codSolicitud) || codSolicitud == null){
				codSolicitud= (String) this.getRequest().getAttribute("idConvocatoria");
				logger.info("idConvocatoria: "+codSolicitud);
			}
			Long idConvocatoria = Long.parseLong(codSolicitud);
			logger.info("IdConvocatoria: "+idConvocatoria);
			ConvocatoriaQuery convocatoriaQuery = new ConvocatoriaQuery();		
			convocatoriaQuery.setId(idConvocatoria);
			DetalleConvocatoriaBean convocatoria= convocatoriasManager.detalleConvocatoria(convocatoriaQuery);
			if(actualizacion != null){
				convocatoria.setActualizar(actualizacion);
				convocatoria.setDetalleError(detalleError);
			}

			setRequestAttribute("convocatoria", convocatoria);

			
			getLogger().debug("DetalleConvocatoriaSpring -end");
			return "success";
		}catch(Exception e){
			logger.error("Error DetalleConvocatoriaSpring- doExecute ",e);
			return "nosuccess";
		}
	}



	/**
	 * Obtiene el convocatorias manager.
	 *
	 * @return el convocatorias manager
	 */
	public ConvocatoriasManager getConvocatoriasManager() {
		return convocatoriasManager;
	}

	/**
	 * Establece el convocatorias manager.
	 *
	 * @param convocatoriasManager el nuevo convocatorias manager
	 */
	public void setConvocatoriasManager(ConvocatoriasManager convocatoriasManager) {
		this.convocatoriasManager = convocatoriasManager;
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
	 * Obtiene el logger.
	 *
	 * @return el logger
	 */
	public static Logger getLogger() {
		return logger;
	}
	
	/**
	 * Check rol usuario.
	 */
	public void checkRolUsuario(){
		UsuarioBean usuario = (UsuarioBean) getRequest().getSession().getAttribute("usuario");
		String username = usuario.getLogin();

		UsuarioQuery usuarioQuery = new UsuarioQuery();
		usuarioQuery.setLogin(username);
		UsuarioBean usuarioBean = usuarioManager.buscarUsuario(usuarioQuery);
		logger.info("Publica convocatorias: "+usuarioBean.getPublicaConvocatorias());
		setRequestAttribute("publica", ((Boolean) usuarioBean.getPublicaConvocatorias()).toString());
		setRequestAttribute("perfil", usuarioBean.getIdPerfil().toString());
	}


}
