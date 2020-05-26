package es.map.ipsg.action.modelos;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringErrors;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringMessage;
import es.map.ips.common.spring.SpringMessages;
import es.map.ips.dao.DatosPropiosConfiguracionDAO;
import es.map.ips.model.CamposPropiosQuery;
import es.map.ips.model.DatosPropiosConfiguracionQuery;
import es.map.ipsg.bean.CamposPropiosBean;
import es.map.ipsg.bean.DatosPropiosConfigBean;
import es.map.ipsg.bean.ModeloBean;
import es.map.ipsg.form.ModeloGestion790Form;
import es.map.ipsg.manager.CamposPropiosManager;
import es.map.ipsg.manager.DatosPropiosConfiguracionManager;
import es.map.ipsg.manager.LogGenericoManager;
import es.map.ipsg.manager.ModeloManager;
import es.map.ipsg.manager.UsuarioManager;

/**
 * El Class VerModificarCampoPropioSpring.
 */
public class VerModificarCampoPropioSpring extends AbstractSpring{
	
	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(VerModificarCampoPropioSpring.class);
	

	//Para insertar en el LOG

	
	/** el log generico manager. */
	private LogGenericoManager logGenericoManager;
	
	/** el usuario manager. */
	private UsuarioManager usuarioManager;	
	
	/** el modelo manager. */
	private ModeloManager modeloManager;
	
	/** el campo propio manager. */
	private CamposPropiosManager campoPropioManager;
	
	/** el datos propios configuracion manager. */
	private DatosPropiosConfiguracionManager datosPropiosConfiguracionManager;
	
	/**
	 * Crea una nueva ver modificar campo propio spring.
	 */
	public VerModificarCampoPropioSpring() {
		try {
			setLogGenericoManager((LogGenericoManager) getBean("logGenericoManager"));
			setUsuarioManager ((UsuarioManager) getBean ("usuarioManager"));	
			setModeloManager((ModeloManager) getBean ("modelosManager"));
			setCampoPropioManager((CamposPropiosManager) getBean("camposPropiosManager"));
			setDatosPropiosConfiguracionManager((DatosPropiosConfiguracionManager) getBean("datosPropiosConfiguracionManager"));
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error VerModificarCampoPropioSpring:" ,e);
		}
	}
	
	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		String menu_tablaBase = RESOURCE_BUNDLE.getString("field.menu.tablasBase");
		this.getRequest().getSession().setAttribute("pagActiva", menu_tablaBase);
		String subMenu_centroGestor = RESOURCE_BUNDLE.getString("field.menuLateral.tablasBase.gestionModelos");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_centroGestor);
		
		getLogger().debug("VerModificarCampoPropioSpring -start");
		
		ModeloGestion790Form formulario = (ModeloGestion790Form) form;
		String accion = formulario.getAccion();
		String idCampo = formulario.getId();
		String resultado;
		try{
			
			//buscamos el campo propio a modificar y el modelo asociado
			CamposPropiosQuery camposPropiosQuery = new CamposPropiosQuery();
			camposPropiosQuery.setIdCampo(Integer.parseInt(idCampo));
			List<CamposPropiosBean> camposPropiosBean = campoPropioManager.buscarCamposPropiosbyCampo(camposPropiosQuery);
			CamposPropiosBean campoPropioBean=camposPropiosBean.get(0);
			
			List<DatosPropiosConfigBean> datosPropiosConfBean = new ArrayList<DatosPropiosConfigBean>();
			
			if (campoPropioBean != null && camposPropiosBean.size() > 0) {
				DatosPropiosConfiguracionQuery datosPropiosConfigQuery = new DatosPropiosConfiguracionQuery();
				datosPropiosConfigQuery.addCampoIn(campoPropioBean.getId().intValue());
				datosPropiosConfBean = datosPropiosConfiguracionManager.buscarDatosPropiosConfigbyCampo(datosPropiosConfigQuery);
			}
			
			this.setRequestAttribute("datosPropiosConfBean", datosPropiosConfBean);
			
			
			if("Modificar Campo".equals(accion)){
				if(validarCampos(formulario)){

					campoPropioBean.setTituloCampo(formulario.getTituloCampo());
					campoPropioBean.setTituloCampo_ca(formulario.getTituloCampo_ca());
					campoPropioBean.setTituloCampo_eu(formulario.getTituloCampo_eu());
					campoPropioBean.setTituloCampo_gl(formulario.getTituloCampo_gl());
					campoPropioBean.setTituloCampo_vl(formulario.getTituloCampo_vl());
					campoPropioBean.setDescripcion(formulario.getDescripcion());
					campoPropioBean.setId(Long.valueOf(idCampo));
					campoPropioManager.modificarCampoPropio(campoPropioBean);
					//id del modelo al que esta asociado el campo propio para pasarlo en caso de exito
					String idModelo=campoPropioBean.getIdModelo();

					String mensaje = RESOURCE_BUNDLE.getString("field.gestionModelos790.modificarCampoPropio");
					String titulo = RESOURCE_BUNDLE.getString("field.gestionModelos790.tituloCampoPropio");
					setRequestAttribute("titulo",titulo);
					setRequestAttribute("mensaje",mensaje);
					setRequestAttribute("accion","/spring/verModificarModelosGestion790?id="+idModelo);

					resultado = "exito";
				}else{

					//buscamos el modelo al que esta asociado el anterior campo propio
					String idModelo=campoPropioBean.getIdModelo();
					ModeloBean modeloBean=modeloManager.obtenerModelo790ById(Integer.parseInt((idModelo)));
					campoPropioBean.setIdModelo(idModelo);
					campoPropioBean.setId(Long.valueOf(campoPropioBean.getId()));
					this.setRequestAttribute("camposPropiosBean", campoPropioBean);
					this.setRequestAttribute("modeloBean", modeloBean);
					SpringErrors SpringErrors = new SpringErrors();
					this.getRequest().setAttribute("errorTituloCampoMulti", "errorTituloCampoMulti");
					SpringErrors.add("dserrorTituloCampoMulti", new SpringMessage(
							"field.gestionModelos790.camposPropios.error.obligatoriedad"));
					saveErrors(getRequest(), new SpringMessages(SpringErrors));
					resultado = "success";
				}
			}else{
				
				//buscamos el modelo al que esta asociado el anterior campo propio
				String idModelo=campoPropioBean.getIdModelo();
				ModeloBean modeloBean=modeloManager.obtenerModelo790ById(Integer.parseInt((idModelo)));
				campoPropioBean.setIdModelo(idModelo);
				if (modeloBean != null) {
					formulario.setDescripcion(!StringUtils.isEmpty(campoPropioBean.getDescripcion())?campoPropioBean.getDescripcion():"");	
					formulario.setNumModelo(!StringUtils.isEmpty(modeloBean.getNumModelo())?modeloBean.getNumModelo():"");
					formulario.setTituloCampo(!StringUtils.isEmpty(campoPropioBean.getTituloCampo())?campoPropioBean.getTituloCampo():"");
					formulario.setTituloCampo_ca(!StringUtils.isEmpty(campoPropioBean.getTituloCampo_ca())?campoPropioBean.getTituloCampo_ca():"");
					formulario.setTituloCampo_eu(!StringUtils.isEmpty(campoPropioBean.getTituloCampo_eu())?campoPropioBean.getTituloCampo_eu():"");
					formulario.setTituloCampo_gl(!StringUtils.isEmpty(campoPropioBean.getTituloCampo_gl())?campoPropioBean.getTituloCampo_gl():"");
					formulario.setTituloCampo_vl(!StringUtils.isEmpty(campoPropioBean.getTituloCampo_vl())?campoPropioBean.getTituloCampo_vl():"");
				}
				campoPropioBean.setId(Long.valueOf(campoPropioBean.getId()));
				this.setRequestAttribute("camposPropiosBean", campoPropioBean);
				this.setRequestAttribute("modeloBean", modeloBean);
				resultado = "success";
			}
			
		}catch(Exception e){
			logger.warn(e);
			this.getRequest().setAttribute("descripcionError", e.getMessage());
			logger.error("Error VerModificarCampoPropioSpring - doExecute:" ,e);
			return "nosuccess";
		}
		return resultado;
	}
	
	/**
	 * Validar campos.
	 *
	 * @param formulario el formulario
	 * @return verdadero, si tiene exito
	 */
	private boolean validarCampos(ModeloGestion790Form formulario){
		boolean validos = false;
		
		if(formulario.getTituloCampo()!=null && !formulario.getTituloCampo().equals("")
				&& formulario.getDescripcion()!=null && !formulario.getDescripcion().equals("")
				&& formulario.getTituloCampo_ca()!=null && !formulario.getTituloCampo_ca().equals("")
				&& formulario.getTituloCampo_eu()!=null && !formulario.getTituloCampo_eu().equals("")
				&& formulario.getTituloCampo_gl()!=null && !formulario.getTituloCampo_gl().equals("")
				&& formulario.getTituloCampo_vl()!=null && !formulario.getTituloCampo_vl().equals("")){
			validos = true;
		}
			
		return validos;
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
	 * Obtiene el modelo manager.
	 *
	 * @return el modelo manager
	 */
	public ModeloManager getModeloManager() {
		return modeloManager;
	}

	/**
	 * Establece el modelo manager.
	 *
	 * @param modeloManager el nuevo modelo manager
	 */
	public void setModeloManager(ModeloManager modeloManager) {
		this.modeloManager = modeloManager;
	}

	/**
	 * Obtiene el campo propio manager.
	 *
	 * @return el campo propio manager
	 */
	public CamposPropiosManager getCampoPropioManager() {
		return campoPropioManager;
	}

	/**
	 * Establece el campo propio manager.
	 *
	 * @param campoPropioManager el nuevo campo propio manager
	 */
	public void setCampoPropioManager(CamposPropiosManager campoPropioManager) {
		this.campoPropioManager = campoPropioManager;
	}

	/**
	 * Obtiene el datos propios configuracion manager.
	 *
	 * @return el datos propios configuracion manager
	 */
	public DatosPropiosConfiguracionManager getDatosPropiosConfiguracionManager() {
		return datosPropiosConfiguracionManager;
	}

	/**
	 * Establece el datos propios configuracion manager.
	 *
	 * @param datosPropiosConfiguracionManager el nuevo datos propios configuracion manager
	 */
	public void setDatosPropiosConfiguracionManager(DatosPropiosConfiguracionManager datosPropiosConfiguracionManager) {
		this.datosPropiosConfiguracionManager = datosPropiosConfiguracionManager;
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
