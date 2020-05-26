package es.map.ipsg.action.convocatoria;

import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringMessage;
import es.map.ips.common.spring.SpringMessages;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.bean.LogConvocatoriaBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.manager.ConvocatoriasManager;
import es.map.ipsg.manager.LogConvocatoriaManager;
import es.map.ipsg.manager.UsuarioManager;
import es.map.ipsg.util.Constantes;



/**
 * El Class CopiarConvocatoriaSpring.
 */
public class CopiarConvocatoriaSpring extends AbstractSpring {
	
	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** el convocatorias manager. */
	private ConvocatoriasManager convocatoriasManager;
	
	/** el usuario manager. */
	private UsuarioManager usuarioManager;
	
	/** el log convocatoria manager. */
	private LogConvocatoriaManager logConvocatoriaManager;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(CopiarConvocatoriaSpring.class);
	
	/**
	 * Crea una nueva copiar convocatoria spring.
	 */
	public CopiarConvocatoriaSpring() {
		try {
			setConvocatoriasManager((ConvocatoriasManager) getBean("convocatoriaManager"));
			setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
			setLogConvocatoriaManager((LogConvocatoriaManager) getBean("logConvocatoriaManager"));

		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error CopiarConvocatoriaSpring: " ,e);
			
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		
		String menu_convocatoria = RESOURCE_BUNDLE.getString("field.menu.convocatorias");
		this.getRequest().getSession().setAttribute("pagActiva", menu_convocatoria);
		String subMenu_convocatoria = RESOURCE_BUNDLE.getString("field.menuLateral.convocatorias.buscar");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_convocatoria);
		
		getLogger().debug("CopiarConvocatoriaSpring -start");
		logger.info("Entra en el Action Copiar Convocatoria");
		try{
			UsuarioBean usuario = (UsuarioBean) getRequest().getSession().getAttribute("usuario");
			UsuarioBean usuarioBean = recuperarUsuario(usuario.getLogin());
			
			String id = this.getRequest().getParameter("id");
			long idNuevo=0;
			
			try{
				if(id!=null&&!(id.equalsIgnoreCase(""))){
					idNuevo=convocatoriasManager.copiarConvocatoria(Long.parseLong(id));
					logger.info("Id Copia :"+idNuevo);
					generarRegistroLogConvocatoria(usuarioBean,idNuevo);
				}
				setRequestAttribute("idConvocatoria",idNuevo+"");
				setRequestAttribute("mensajeConfirmacion" , RESOURCE_BUNDLE.getString("field.convocatoria.copia.confirmacion"));
			}	 catch (Exception e) {
				logger.error("Error CopiarConvocatoriaSpring -idCopia: " +idNuevo ,e);
				SpringMessages messages = new SpringMessages();
				messages.add("errorImporteNoExiste", new SpringMessage(
						"field.convocatoria.errores.importeNoExiste"));
				saveErrors(this.getRequest(), messages);
				setRequestAttribute("idConvocatoria",id);
			}
			
			
			getLogger().debug("CopiarConvocatoriaSpring -end");
			return "success";
			
		}catch(Exception e){
		logger.warn(e);
		logger.error("Error CopiarConvocatoriaSpring - doExecute: " ,e);
		this.getRequest().setAttribute("descripcionError", e.getMessage());
		return "nosuccess";
		}
	}
	
	/**
	 * Generar registro log convocatoria.
	 *
	 * @param usuarioBean el usuario bean
	 * @param idconvocatoria el idconvocatoria
	 */
	private void generarRegistroLogConvocatoria(UsuarioBean usuarioBean, Long idconvocatoria){
		
		//Cargo los datos en el bean del log generico que se usara para crear el registro en la tabla
		LogConvocatoriaBean logConvocatoriaBean = new LogConvocatoriaBean();
		
		logConvocatoriaBean.setTipoOperacion(Constantes.OPERACION_ALTA);
		logConvocatoriaBean.setDetalle(RESOURCE_BUNDLE.getString("field.logConvocatoria.alta")+idconvocatoria.toString());
		logConvocatoriaBean.setIdConvocatoria(idconvocatoria);
		logConvocatoriaBean.setIdUsuario(usuarioBean.getId());
		
		logConvocatoriaManager.guardarLogConvocatoria(logConvocatoriaBean);
	}
	
	/**
	 * Recuperar usuario.
	 *
	 * @param username el username
	 * @return el usuario bean
	 */
	private UsuarioBean recuperarUsuario(String username){
		UsuarioQuery usuarioQuery = new UsuarioQuery();
		usuarioQuery.setLogin(username);
		UsuarioBean usuarioBean = usuarioManager.buscarUsuario(usuarioQuery);
		return usuarioBean;
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
	 * Obtiene el log convocatoria manager.
	 *
	 * @return el log convocatoria manager
	 */
	public LogConvocatoriaManager getLogConvocatoriaManager() {
		return logConvocatoriaManager;
	}

	/**
	 * Establece el log convocatoria manager.
	 *
	 * @param logConvocatoriaManager el nuevo log convocatoria manager
	 */
	public void setLogConvocatoriaManager(LogConvocatoriaManager logConvocatoriaManager) {
		this.logConvocatoriaManager = logConvocatoriaManager;
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
