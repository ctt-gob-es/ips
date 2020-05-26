package es.map.ipsg.action.modelos;

import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringMessage;
import es.map.ips.common.spring.SpringMessages;
import es.map.ips.model.Convocatoria;
import es.map.ips.model.DatosPropiosConfiguracionQuery;
import es.map.ipsg.action.centrogestor.ModificarCentroGestorSpring;
import es.map.ipsg.bean.DatosPropiosConfigBean;
import es.map.ipsg.form.DatosPropiosConfForm;
import es.map.ipsg.manager.DatosPropiosConfiguracionManager;

/**
 * El Class ModificarDatosPropiosConfSpring.
 */
public class ModificarDatosPropiosConfSpring extends AbstractSpring{
	
	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(ModificarCentroGestorSpring.class);
	//Para insertar en el LOG
	
	
	/** La constante STRING_ERROR. */
	private static final String STRING_ERROR = "nosuccess";
	
	
	/** el datos propios manager. */
	private DatosPropiosConfiguracionManager datosPropiosManager;

	/**
	 * Crea una nueva modificar datos propios conf spring.
	 */
	public ModificarDatosPropiosConfSpring() {
		try {
			setDatosPropiosManager((DatosPropiosConfiguracionManager) getBean("datosPropiosConfiguracionManager"));
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error ModificarModelo790Spring:" ,e);
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
		
		getLogger().debug("ModificarModelo790Spring -start");
		String resultado = STRING_ERROR;
		
		DatosPropiosConfForm formulario = (DatosPropiosConfForm) form;
		DatosPropiosConfigBean datosPropiosConfBean = new DatosPropiosConfigBean();
		DatosPropiosConfiguracionQuery datosPropiosConfigQuery = new DatosPropiosConfiguracionQuery();
		
		String accion = formulario.getAccion();
		try{	
			//detectamos si se ha pulsado en "Cancelar", "Añadir Campo" o "modificar"
			if("VOLVER".equalsIgnoreCase(accion)){
				resultado = "success";
			} else if("MODIFICAR CAMPO".equalsIgnoreCase(accion) && this.getRequest().getAttribute("org.apache.spring.ERROR") == null){	
				datosPropiosConfigQuery.addIdDesplegableIn(Integer.parseInt(formulario.getIdDesplegable()));
				List<DatosPropiosConfigBean> listDatosPropiosConfBean = datosPropiosManager.buscarDatosPropiosConfigbyCampo(datosPropiosConfigQuery);

				if (listDatosPropiosConfBean != null && listDatosPropiosConfBean.size() > 0) {
					datosPropiosConfBean.setIdDesplegable(listDatosPropiosConfBean.get(0).getIdDesplegable());
					datosPropiosConfBean.setCampo(listDatosPropiosConfBean.get(0).getCampo());
					datosPropiosConfBean.setDescripcion(formulario.getDescripcion());
					
				}
					
				if (datosPropiosManager.actualizarDatosPropiosConf(datosPropiosConfBean)){
					resultado = exitoDatosPropiosConf(formulario, "field.camposPropios.modificarCampoPropio");
				}else{
					resultado = errorDatosPropiosConf(formulario, "field.camposPropios.modificarDatoPropio.mensajeError");
				}
			} else if("ELIMINAR".equalsIgnoreCase(accion) && formulario.getIdDesplegable() != null 
					&& this.getRequest().getAttribute("org.apache.spring.ERROR") == null){ 
				Integer idDesplegable = 0;
				DatosPropiosConfigBean datosPropiosConfigBean = null;
				try {
					idDesplegable = Integer.parseInt(formulario.getIdDesplegable());
					datosPropiosConfigBean = datosPropiosManager.obtenerDatoPropio(idDesplegable);
				} catch (NumberFormatException e) {
					logger.error("Error al eliminar el idDesplegable sin formato numerico.");
					resultado = errorDatosPropiosConf(formulario, "field.camposPropios.eliminarDatoPropio.mensajeError");
				}
				
				if (datosPropiosConfigBean.getConvocatorias() != null && datosPropiosConfigBean.getConvocatorias().size() > 0) {
					//Si existen convocatorias asociadas a un dato propio
					SpringMessages errors = new SpringMessages();
					SpringMessage error = new SpringMessage("field.camposPropios.errores.convocatoria1",datosPropiosConfigBean.getDescripcion());
					errors.add("errorConvocatoria",error);
					
					
					
					Iterator<Convocatoria> convocatorias = datosPropiosConfigBean.getConvocatorias().iterator();
					while (convocatorias.hasNext()) {
						Convocatoria convocatoria = convocatorias.next();
						if (convocatoria != null) {
							error= new SpringMessage("field.camposPropios.errores.convocatoria3",convocatoria.getId(), convocatoria.getEjercicio(), convocatoria.getEstadoConvocatoria().getDescripcion());
							errors.add("errorConvocatoria1",error);
						}
					}
					
					this.setRequestAttribute("org.apache.spring.ERROR", errors);
					
					return "errorEliminar";
				} else if (datosPropiosManager.eliminarDatosPropiosConf(idDesplegable)) {
					resultado = exitoDatosPropiosConf(formulario, "field.camposPropios.eliminarCampoPropio");
				} else {
					resultado = errorDatosPropiosConf(formulario, "field.camposPropios.eliminarDatoPropio.mensajeError");
				}
			} else{
				datosPropiosConfigQuery.addIdDesplegableIn(Integer.parseInt(formulario.getId()));
				List<DatosPropiosConfigBean> listDatosPropiosConfBean = datosPropiosManager.buscarDatosPropiosConfigbyCampo(datosPropiosConfigQuery);
				
				if (listDatosPropiosConfBean != null && listDatosPropiosConfBean.size() > 0) {
					formulario.setId(listDatosPropiosConfBean.get(0).getCampo().getIdCampo().toString());
					formulario.setIdDesplegable(listDatosPropiosConfBean.get(0).getIdDesplegable().toString());
					formulario.setDescripcion(listDatosPropiosConfBean.get(0).getDescripcion());
				}
				
				resultado =  "success";
			}
	
			return resultado;
		}catch(Exception e){
			logger.warn(e);
			this.getRequest().setAttribute("descripcionError", e.getMessage());
			logger.error("Error ModificarModelo790Spring - doExecute:" ,e);
			return "nosuccess";
		}
	}

	/**
	 * Exito datos propios conf.
	 *
	 * @param formulario el formulario
	 * @param mensajeResource el mensaje resource
	 * @return el string
	 */
	private String exitoDatosPropiosConf(DatosPropiosConfForm formulario, String mensajeResource) {
		String resultado;
		resultado = "crearPropio";
		String mensaje = RESOURCE_BUNDLE.getString(mensajeResource);
		String titulo = RESOURCE_BUNDLE.getString("field.gestionModelos790.tituloCampoPropio");
		setRequestAttribute("titulo",titulo);
		setRequestAttribute("mensaje",mensaje);
		setRequestAttribute("accion","/spring/verModificarCampoPropio?id="+formulario.getId());
		return resultado;
	}

	/**
	 * Error datos propios conf.
	 *
	 * @param formulario el formulario
	 * @param mensajeResource el mensaje resource
	 * @return el string
	 */
	private String errorDatosPropiosConf(DatosPropiosConfForm formulario, String mensajeResource) {
		String resultado;
		resultado = STRING_ERROR;
		String mensaje = RESOURCE_BUNDLE.getString(mensajeResource);
		String titulo = RESOURCE_BUNDLE.getString("field.gestionModelos790.tituloCampoPropio");
		setRequestAttribute("titulo",titulo);
		setRequestAttribute("mensaje",mensaje);
		setRequestAttribute("accion","/spring/verModificarCampoPropio?id="+formulario.getId());
		return resultado;
	}

	/**
	 * Obtiene el datos propios manager.
	 *
	 * @return el datos propios manager
	 */
	public DatosPropiosConfiguracionManager getDatosPropiosManager() {
		return datosPropiosManager;
	}

	/**
	 * Establece el datos propios manager.
	 *
	 * @param datosPropiosManager el nuevo datos propios manager
	 */
	public void setDatosPropiosManager(DatosPropiosConfiguracionManager datosPropiosManager) {
		this.datosPropiosManager = datosPropiosManager;
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
