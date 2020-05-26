package es.map.ipsg.action.tarifa;

import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.GrupoQuery;
import es.map.ips.model.TipoAccesoQuery;
import es.map.ipsg.bean.GrupoBean;
import es.map.ipsg.bean.TarifaBean;
import es.map.ipsg.bean.TipoAccesoBean;
import es.map.ipsg.form.TarifaForm;
import es.map.ipsg.manager.GrupoManager;
import es.map.ipsg.manager.TarifaManager;
import es.map.ipsg.manager.TipoAccesoManager;
import es.map.ipsg.manager.UsuarioManager;


/**
 * El Class VerModificarTarifaSpring.
 */
public class VerModificarTarifaSpring extends AbstractSpring {

	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(VerModificarTarifaSpring.class);

	/** el usuario manager. */
	private UsuarioManager usuarioManager;
	
	/** el tarifa manager. */
	private TarifaManager tarifaManager;
	
	/** el grupo manager. */
	private GrupoManager grupoManager;
	
	/** el tipo acceso manager. */
	private TipoAccesoManager tipoAccesoManager;
		
	/**
	 * Crea una nueva ver modificar tarifa spring.
	 */
	public VerModificarTarifaSpring() {
		try {
			setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
			setTarifaManager((TarifaManager) getBean("tarifaManager"));
			setGrupoManager((GrupoManager) getBean("grupoManager"));
			setTipoAccesoManager((TipoAccesoManager) getBean("tipoAccesoManager"));
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error VerModificarTarifaSpring - crear constructor",e);
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		
		String menu_cuerposEscala = RESOURCE_BUNDLE.getString("field.menu.tablasBase");
		this.getRequest().getSession().setAttribute("pagActiva",menu_cuerposEscala);
		String subMenu_tarifa = RESOURCE_BUNDLE.getString("field.menuLateral.tablasBase.tarifa");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_tarifa);
		
		getLogger().debug("VerAltaCuerposEscalaSpring -start");
		String resultado;
	try{
		TarifaForm formulario=(TarifaForm) form;
		
		GrupoQuery grupoQuery = new GrupoQuery();
		List<GrupoBean> grupo = grupoManager.buscarGrupoCombo(grupoQuery);
		
		
		TipoAccesoQuery tipoQuery = new TipoAccesoQuery();
		List<TipoAccesoBean>  tipoAcceso= tipoAccesoManager.buscarTipoAccesoCombo(tipoQuery);
			
						
		String idTarifa = this.getRequest().getParameter("id");
		
		if(idTarifa!=null){
			TarifaBean tarifaBean= new TarifaBean();
			if(!"Modificar".equals(formulario.getAccion())){
				tarifaBean = tarifaManager.obtenerTarifa(Integer.valueOf(idTarifa));
			
				if(tarifaBean.getIdGrupo()!=null){
					formulario.setIdGrupo(tarifaBean.getIdGrupo().toString());
					}
				if(tarifaBean.getIdTipoAcceso()!=null){
					formulario.setIdTipoAcceso(tarifaBean.getIdTipoAcceso().toString());
					}

			}else{
				tarifaBean.setId(formulario.getId());
				tarifaBean.setDescripcion(formulario.getDescripcion());
				tarifaBean.setEjercicio(formulario.getEjercicio());
				tarifaBean.setGrupo(formulario.getGrupo());
				if(formulario.getIdTipoAcceso()!=null && !"".equals(formulario.getIdTipoAcceso()))
					tarifaBean.setIdTipoAcceso(Integer.parseInt(formulario.getIdTipoAcceso()));
				if(formulario.getImporte()!= null &&!"".equals(formulario.getImporte()))
					tarifaBean.setImporte(Float.parseFloat(formulario.getImporte()));
			}			
			this.setRequestAttribute("tarifa", tarifaBean);				
		}else{
			resultado = "error";
		}		
		
		this.setRequestAttribute("grupo",grupo);
		this.setRequestAttribute("tipoAcceso",tipoAcceso);
		
		resultado = "success";
		
		getLogger().debug("VerAltaCuerposEscalaSpring -end");
	
	}catch(Exception eGenerico){
		logger.error("Error VerModificarTarifaSpring - doExecute",eGenerico);
		this.getRequest().setAttribute("descripcionError", RESOURCE_BUNDLE.getString("field.errorGenerico"));
		return "errorGenerico";
	}	
		
		return resultado;
	}

	



	/**
	 * Obtiene el grupo manager.
	 *
	 * @return the grupoManager
	 */
	public GrupoManager getGrupoManager() {
		return grupoManager;
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
	 * Obtiene el usuario manager.
	 *
	 * @return the usuarioManager
	 */
	public UsuarioManager getUsuarioManager() {
		return usuarioManager;
	}

	/**
	 * Obtiene el tarifa manager.
	 *
	 * @return the tarifaManager
	 */
	public TarifaManager getTarifaManager() {
		return tarifaManager;
	}

	/**
	 * Obtiene el tipo acceso.
	 *
	 * @return the tipoAcceso
	 */
	public TipoAccesoManager getTipoAcceso() {
		return tipoAccesoManager;
	}

	/**
	 * Establece el usuario manager.
	 *
	 * @param usuarioManager the usuarioManager to set
	 */
	public void setUsuarioManager(UsuarioManager usuarioManager) {
		this.usuarioManager = usuarioManager;
	}

	/**
	 * Establece el tarifa manager.
	 *
	 * @param tarifaManager the tarifaManager to set
	 */
	public void setTarifaManager(TarifaManager tarifaManager) {
		this.tarifaManager = tarifaManager;
	}

	/**
	 * Establece el grupo manager.
	 *
	 * @param grupoManager the grupoManager to set
	 */
	public void setGrupoManager(GrupoManager grupoManager) {
		this.grupoManager = grupoManager;
	}

	/**
	 * Establece el tipo acceso manager.
	 *
	 * @param tipoAcceso the tipoAcceso to set
	 */
	public void setTipoAccesoManager(TipoAccesoManager tipoAcceso) {
		this.tipoAccesoManager = tipoAcceso;
	}

	
}
