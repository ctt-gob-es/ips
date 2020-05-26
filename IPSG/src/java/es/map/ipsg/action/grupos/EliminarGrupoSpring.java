package es.map.ipsg.action.grupos;

import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringMessage;
import es.map.ips.common.spring.SpringMessages;
import es.map.ips.model.CuerpoEscalaQuery;
import es.map.ips.model.GrupoQuery;
import es.map.ips.model.TarifaQuery;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.bean.CuerpoEscalaBean;
import es.map.ipsg.bean.GrupoBean;
import es.map.ipsg.bean.LogGenericoBean;
import es.map.ipsg.bean.TarifaBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.manager.CuerpoEscalaManager;
import es.map.ipsg.manager.GrupoManager;
import es.map.ipsg.manager.LogGenericoManager;
import es.map.ipsg.manager.TarifaManager;
import es.map.ipsg.manager.UsuarioManager;
import es.map.ipsg.util.Constantes;

/**
 * El Class EliminarGrupoSpring.
 */
public class EliminarGrupoSpring extends AbstractSpring {

	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(EliminarGrupoSpring.class);
	
	/** el cuerpos escala manager. */
	private CuerpoEscalaManager cuerposEscalaManager;
	
	/** el tarifa manager. */
	private TarifaManager tarifaManager;
	
	/** el grupo manager. */
	private GrupoManager grupoManager;
	
	/** el usuario manager. */
	private UsuarioManager usuarioManager;
	
	/** el log generico manager. */
	private LogGenericoManager logGenericoManager;
	

	/**
	 * Crea una nueva eliminar grupo spring.
	 */
	public EliminarGrupoSpring() {
		try {
			setCuerposEscalaManager((CuerpoEscalaManager) getBean("cuerposEscalaManager"));
			setTarifaManager((TarifaManager) getBean("tarifaManager"));
			setGrupoManager((GrupoManager) getBean("grupoManager"));
			setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
			setLogGenericoManager((LogGenericoManager) getBean("logGenericoManager"));
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error EliminarGrupoSpring:",e);
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
		
		getLogger().debug("EliminarGrupoSpring -start");	
		
		logger.info("Entra en el Action");
		try{
			String grupo = this.getRequest().getParameter("grupo").trim();
			int codMinisterio = Integer.parseInt(grupo);
			GrupoQuery grupoQuery = new GrupoQuery();
			grupoQuery.setId(codMinisterio);
			CuerpoEscalaQuery cuerpoEscalaQuery = new CuerpoEscalaQuery();
			cuerpoEscalaQuery.setGrupo(grupoQuery);
			cuerpoEscalaQuery.setEstado(Constantes.CUERPO_ESCALA_ESTADO_ACTIVO);
			List<CuerpoEscalaBean> cuerpoEscalaBean = null;
			cuerpoEscalaBean = cuerposEscalaManager.buscarCuerpoEscalaAll(cuerpoEscalaQuery);
			String resultado = "apto";
				if(cuerpoEscalaBean.size()>0){
					String cuerpoEscala="";
					for(int i=0; i<cuerpoEscalaBean.size();i++){
						cuerpoEscala = cuerpoEscala + cuerpoEscalaBean.get(i).getId();
						if(cuerpoEscalaBean.size() > i+1){
							cuerpoEscala = cuerpoEscala + ",";
						}
					}
					SpringMessages messages = new SpringMessages();
					messages.add("errorEliminarGrupoCuerpoEscala",new SpringMessage("field.grupo.eliminar.cuerpoEscala", cuerpoEscala));
					saveErrors(this.getRequest(),messages);
					resultado = "noapto";
				}
					TarifaQuery tarifaQuery = new TarifaQuery();
					tarifaQuery.setGrupo(grupoQuery);
					tarifaQuery.setEstado(Constantes.TARIFA_ESTADO_ACTIVO);
					List<TarifaBean> tarifaBean = null;
					tarifaBean = tarifaManager.buscarTarifaAll(tarifaQuery);
				if(tarifaBean.size()>0){
					String tarifas="";
					for(int i=0; i<tarifaBean.size();i++){
						tarifas = tarifas + tarifaBean.get(i).getId();
						if(tarifaBean.size() > i+1){
							tarifas = tarifas + ",";
						}			
					}
					SpringMessages messages = new SpringMessages();
					messages.add("errorEliminarGrupoTarifa",new SpringMessage("field.grupo.eliminar.tarifas", tarifas));
					saveErrors(this.getRequest(),messages);
					resultado = "noapto";
					
				}
				if("apto".equals(resultado)){
					aptoEqualsResultado(grupoQuery);					
				}
				getLogger().debug("EliminarGrupoSpring -end");
				return "success";
		}catch (Exception e){
			logger.warn(e);
			this.getRequest().setAttribute("descripcionError", e.getMessage());
			logger.error("Error EliminarGrupoSpring - doExecute:",e);
			return "nosuccess";
		}
	}
	
	/**
	 * Apto equals resultado.
	 *
	 * @param grupoQuery el grupo query
	 */
	private void aptoEqualsResultado(GrupoQuery grupoQuery) {
		
		GrupoBean grupoBean;
		grupoBean = grupoManager.buscarGrupoUnico(grupoQuery);
		grupoBean.setEstado(Constantes.GRUPO_ESTADO_INACTIVO);
		try{
			grupoManager.actualizarGrupo(grupoBean);
			UsuarioBean usuarioBean = (UsuarioBean) getRequest().getSession().getAttribute("usuario");
			generarRegistroLogGenerico(usuarioBean.getLogin(), Long.valueOf(grupoBean.getId()));
		}catch(Exception e){
			logger.error("Error EliminarGrupoSpring - actualizarGrupo:",e);
			SpringMessages messages = new SpringMessages();
			messages.add("errorEliminarGrupo",new SpringMessage("field.grupo.eliminar.mensaje"));
			saveErrors(this.getRequest(),messages);
		}
	}
	
	/**
	 * Generar registro log generico.
	 *
	 * @param username el username
	 * @param idGrupo el id grupo
	 */
	public void generarRegistroLogGenerico(String username, Long idGrupo){
		
		UsuarioQuery usuarioQuery = new UsuarioQuery();
		usuarioQuery.setLogin(username);
		UsuarioBean usuarioBean2 = usuarioManager.buscarUsuario(usuarioQuery);
		
		//Cargo los datos en el bean del log generico que se usara para crear el registro en la tabla
		LogGenericoBean logGenericoBean = new LogGenericoBean();
		logGenericoBean.setTipoFuncionalidad(Constantes.FUNCIONALIDAD_GRUPO);
		logGenericoBean.setTipoOperacion(Constantes.OPERACION_BAJA);
		logGenericoBean.setDetalleOperacion(RESOURCE_BUNDLE.getString("field.grupo.detalleOperacionBaja") + " "  + idGrupo.toString());
		logGenericoBean.setIdTablaOrigen(idGrupo);
		logGenericoBean.setUsuario(usuarioManager.toUsuario(usuarioBean2));
		
		logGenericoManager.generarRegistroLog(logGenericoBean);
	}

	/**
	 * Obtiene el cuerpos escala manager.
	 *
	 * @return el cuerpos escala manager
	 */
	public CuerpoEscalaManager getCuerposEscalaManager() {
		return cuerposEscalaManager;
	}

	/**
	 * Establece el cuerpos escala manager.
	 *
	 * @param cuerpoEscalaManager el nuevo cuerpos escala manager
	 */
	public void setCuerposEscalaManager(CuerpoEscalaManager cuerpoEscalaManager) {
		this.cuerposEscalaManager = cuerpoEscalaManager;
	}

	/**
	 * Obtiene el tarifa manager.
	 *
	 * @return el tarifa manager
	 */
	public TarifaManager getTarifaManager() {
		return tarifaManager;
	}

	/**
	 * Establece el tarifa manager.
	 *
	 * @param tarifaManager el nuevo tarifa manager
	 */
	public void setTarifaManager(TarifaManager tarifaManager) {
		this.tarifaManager = tarifaManager;
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
