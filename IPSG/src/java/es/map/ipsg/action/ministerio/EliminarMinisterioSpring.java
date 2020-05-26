package es.map.ipsg.action.ministerio;

import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringMessage;
import es.map.ips.common.spring.SpringMessages;
import es.map.ips.model.CentroGestorQuery;
import es.map.ips.model.MinisterioQuery;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.bean.CentroGestorBean;
import es.map.ipsg.bean.LogGenericoBean;
import es.map.ipsg.bean.MinisterioBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.form.MinisterioForm;
import es.map.ipsg.manager.CentroGestorManager;
import es.map.ipsg.manager.LogGenericoManager;
import es.map.ipsg.manager.MinisterioManager;
import es.map.ipsg.manager.UsuarioManager;
import es.map.ipsg.util.Constantes;

/**
 * El Class EliminarMinisterioSpring.
 */
public class EliminarMinisterioSpring extends AbstractSpring {

	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(EliminarMinisterioSpring.class);
	
	/** el ministerio manager. */
	private MinisterioManager ministerioManager;
	
	/** el centro gestor manager. */
	private CentroGestorManager centroGestorManager;
	
	/** el usuario manager. */
	private UsuarioManager usuarioManager;
	
	/** el log generico manager. */
	private LogGenericoManager logGenericoManager;
	
	/**
	 * Crea una nueva eliminar ministerio spring.
	 */
	public EliminarMinisterioSpring() {
		try {
			setMinisterioManager((MinisterioManager) getBean("ministeriosManager"));
			setCentroGestorManager((CentroGestorManager) getBean("centrosGestoresManager"));
			setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
			setLogGenericoManager((LogGenericoManager) getBean("logGenericoManager"));
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error EliminarMinisterioSpring:",e);
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
		
		getLogger().debug("EliminarMinisterioSpring -start");		
		try{
			logger.info("Entra en el Action");
			String ministerio = this.getRequest().getParameter("Ministerio").trim();
			int codMinisterio = Integer.parseInt(ministerio);
			
			MinisterioQuery ministerioQuery = new MinisterioQuery();
			ministerioQuery.setId(codMinisterio);
			MinisterioBean ministerioBean = ministerioManager.buscarMinisterio(ministerioQuery);
			ministerioBean.setEstado(Constantes.MINISTERIO_ESTADO_DESACTIVADO);
			CentroGestorQuery centroGestorQuery = new CentroGestorQuery();
			centroGestorQuery.setMinisterio(ministerioQuery);
			centroGestorQuery.setEstado(Constantes.CENTROGESTOR_ESTADO_ACTIVO);
			List<CentroGestorBean> centroGestorAux = null;
			centroGestorAux = centroGestorManager.buscarCentroGestorAll(centroGestorQuery);
			if(centroGestorAux.size() == 0){
					try{
						ministerioManager.actualizarMinisterio(ministerioBean);
						UsuarioBean usuarioBean = (UsuarioBean) getRequest().getSession().getAttribute("usuario");
						generarRegistroLogGenerico(usuarioBean.getLogin(), Long.valueOf(ministerioBean.getId()));
					}catch(Exception e){
						logger.error("Error EliminarMinisterioSpring:",e);
						SpringMessages messages = new SpringMessages();
						messages.add("errorEliminarMinisterio",new SpringMessage("field.grupo.eliminar.mensaje"));
						saveErrors(this.getRequest(),messages);
					}
			}else{
				String centros="";
				for(int i=0; i<centroGestorAux.size();i++){
					centros = centros + centroGestorAux.get(i).getId();
					if(centroGestorAux.size() > i+1){
						centros = centros + ",";
					}
				}
				SpringMessages messages = new SpringMessages();
				messages.add("errorEliminarMinisterio",new SpringMessage("field.ministerio.eliminar.centroGestor", centros));
				saveErrors(this.getRequest(),messages);
			}
			getLogger().debug("EliminarMinisterioSpring -end");
			return "success";
		}catch(Exception e){
			logger.warn(e);
			this.getRequest().setAttribute("descripcionError", e.getMessage());
			logger.error("Error EliminarMinisterioSpring - doExecute:",e);
			return "nosuccess";
		}
	}

	/**
	 * Generar registro log generico.
	 *
	 * @param username el username
	 * @param idMinisterio el id ministerio
	 */
	public void generarRegistroLogGenerico(String username, Long idMinisterio){
		
		UsuarioQuery usuarioQuery = new UsuarioQuery();
		usuarioQuery.setLogin(username);
		UsuarioBean usuarioBean2 = usuarioManager.buscarUsuario(usuarioQuery);
		
		//Cargo los datos en el bean del log generico que se usara para crear el registro en la tabla
		LogGenericoBean logGenericoBean = new LogGenericoBean();
		logGenericoBean.setTipoFuncionalidad(Constantes.FUNCIONALIDAD_MINISTERIO);
		logGenericoBean.setTipoOperacion(Constantes.OPERACION_BAJA);
		logGenericoBean.setDetalleOperacion(RESOURCE_BUNDLE.getString("field.ministerio.detalleOperacionBaja") + " "  + idMinisterio.toString());
		logGenericoBean.setIdTablaOrigen(idMinisterio);
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
