package es.map.ipsg.action.centrogestor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.MinisterioQuery;
import es.map.ipsg.bean.CentroGestorBean;
import es.map.ipsg.bean.MinisterioBean;
import es.map.ipsg.bean.ModeloBean;
import es.map.ipsg.form.CentroGestorForm;
import es.map.ipsg.manager.CentroGestorManager;
import es.map.ipsg.manager.MinisterioManager;
import es.map.ipsg.manager.ModeloManager;
import es.map.ipsg.manager.PlantillaManager;

/**
 * El Class VerModificarCentroGestorSpring.
 */
public class VerModificarCentroGestorSpring extends AbstractSpring {

	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(VerModificarCentroGestorSpring.class);
	
	/** La constante STRING_DATE_FORMAT. */
	private static final String STRING_DATE_FORMAT = "dd/MM/yyyy";
	
	/** el centro gestor manager. */
	private CentroGestorManager centroGestorManager;
	
	/** el modelo manager. */
	private ModeloManager modeloManager;
	
	/** el ministerio manager. */
	private MinisterioManager ministerioManager;
	
	/** el plantilla manager. */
	private PlantillaManager plantillaManager;
		
	/**
	 * Crea una nueva ver modificar centro gestor spring.
	 */
	public VerModificarCentroGestorSpring() {
		try {
			setMinisterioManager((MinisterioManager) getBean("ministeriosManager"));
			setCentroGestorManager((CentroGestorManager) getBean("centrosGestoresManager"));
			setPlantillaManager((PlantillaManager) getBean("plantillaManager"));
			setModeloManager((ModeloManager)getBean("modelosManager"));
			
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error VerModificarCentroGestorSpring(): ",e);
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		
		String menu_tablaBase = RESOURCE_BUNDLE.getString("field.menu.tablasBase");
		this.getRequest().getSession().setAttribute("pagActiva", menu_tablaBase);
		String subMenu_centroGestor = RESOURCE_BUNDLE.getString("field.menuLateral.tablasBase.centroGestor");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_centroGestor);
		
		getLogger().debug("VerModificarCentroGestorSpring -start");
		String resultado;
		
		CentroGestorForm formulario = (CentroGestorForm) form;
		try{
			MinisterioQuery ministerioQuery = new MinisterioQuery();
			ministerioQuery.setVisible('S');
			List<MinisterioBean> ministerio = ministerioManager.buscarMinisterioCombo(ministerioQuery);
			this.setRequestAttribute("ministerio",ministerio);
			
			String idCentroGestor = this.getRequest().getParameter("id");
			
			if(idCentroGestor!=null){			
				
				CentroGestorBean centroGestorBean = new CentroGestorBean();
				logger.info(formulario.getAccion());
				if(!"Modificar".equals(formulario.getAccion())){
				centroGestorBean = centroGestorManager.obtenerCentroGestor(Integer.valueOf(idCentroGestor));
				//buscamos el modelo asociado
				if(centroGestorBean.getModelo()!=null){
					formulario.setIdModelo(centroGestorBean.getModelo().getIdModelo().toString());
				}
				if(centroGestorBean.getIdMinisterio() != null){
					formulario.setIdMinisterio(centroGestorBean.getIdMinisterio());				
				}
				formulario.setIdPlantilla(centroGestorBean.getPlantilla().getId());
				
				centroGestorBean.setIdPlantilla(centroGestorBean.getPlantilla().getId());
			}else{
				int id = comprobarEntero(formulario.getId());
				centroGestorBean.setId(id);
				centroGestorBean.setMinisterio(formulario.getMinisterio());
				centroGestorBean.setSiglas(formulario.getSiglas());
				centroGestorBean.setDescripcion(formulario.getDescripcion());
				centroGestorBean.setEjercicio(formulario.getEjercicio());
				centroGestorBean.setCodigo(formulario.getCodigo());
				centroGestorBean.setIdCentroGestorSust(formulario.getIdCentroGestorSust());
				centroGestorBean.setIdPlantilla(formulario.getIdPlantilla());
				if(formulario.getVisibilidad() != null){	
					centroGestorBean.setVisibilidad(formulario.getVisibilidad());
				}else{
					centroGestorBean.setVisibilidad(false);
				}				
			}

			formularioSet(centroGestorBean,formulario);
				
			this.setRequestAttribute("centroGestor", centroGestorBean);
			
			List<CentroGestorBean> centroGestor = centroGestorManager.buscarCentroGestorComboTodos();
			List<ModeloBean> modeloBean = modeloManager.buscarModeloComboTodos();
			
			this.setRequestAttribute("centroGestorLista",centroGestor);
			this.setRequestAttribute("modelos",modeloBean);
	
			resultado = "success";
			}else{
				resultado = "error";
			}
			
			return resultado;
			
		}catch(Exception e){
			logger.warn(e);
			logger.error("Error VerModificarCentroGestorSpring - doExecute: ",e);
			this.getRequest().setAttribute("descripcionError", e.getMessage());
			return "nosuccess";
		}	
	}

	/**
	 * Comprobar entero.
	 *
	 * @param numero el numero
	 * @return el int
	 */
	private int comprobarEntero(String numero) {
		int resultado = 0;
		try{
			resultado = Integer.parseInt(numero);
		}catch(Exception e){
			logger.error("Error comprobarEntero():",e );
		}
		return resultado;
	}
	
	/**
	 * Formulario set.
	 *
	 * @param centroGestorBean el centro gestor bean
	 * @param formulario el formulario
	 */
	private void formularioSet(CentroGestorBean centroGestorBean, CentroGestorForm formulario) {
		if(centroGestorBean.getFechaInicioInhabil()!=null && !"".equals(centroGestorBean.getFechaInicioInhabil().toString())){
			SimpleDateFormat formatoFecha = new SimpleDateFormat(STRING_DATE_FORMAT);
			Date fechaS = centroGestorBean.getFechaInicioInhabil();					
			String fechaSust = formatoFecha.format(fechaS);					
			formulario.setFechaInicioInhabil(fechaSust);
		}
		
		if(centroGestorBean.getFechaFinInhabil()!=null && !"".equals(centroGestorBean.getFechaFinInhabil().toString())){
			SimpleDateFormat formatoFecha = new SimpleDateFormat(STRING_DATE_FORMAT);
			Date fechaS = centroGestorBean.getFechaFinInhabil();					
			String fechaSust = formatoFecha.format(fechaS);					
			formulario.setFechaFinInhabil(fechaSust);
		}
			
		if(centroGestorBean.getFechaSustitucion()!=null && !"".equals(centroGestorBean.getFechaSustitucion().toString())){
			SimpleDateFormat formatoFecha = new SimpleDateFormat(STRING_DATE_FORMAT);
			Date fechaS = centroGestorBean.getFechaSustitucion();					
			String fechaSust = formatoFecha.format(fechaS);					
			formulario.setFechaSustitucion(fechaSust);
		}
		
		formulario.setDescripcion((!StringUtils.isEmpty(centroGestorBean.getDescripcion()))?centroGestorBean.getDescripcion():"");
		formulario.setEjercicio((!StringUtils.isEmpty(centroGestorBean.getEjercicio()))?centroGestorBean.getEjercicio():"");
		formulario.setCodigo((!StringUtils.isEmpty(centroGestorBean.getCodigo()))?centroGestorBean.getCodigo():"");
		formulario.setSiglas((!StringUtils.isEmpty(centroGestorBean.getSiglas()))?centroGestorBean.getSiglas():"");
		formulario.setVisibilidad((centroGestorBean.getVisibilidad())?true:false);
		formulario.setIdCentroGestorSust((centroGestorBean.getIdCentroGestorSust() != null)?centroGestorBean.getIdCentroGestorSust():0);
	}
	
	/**
	 * Obtiene el centro gestor manager.
	 *
	 * @return the centroGestorManager
	 */
	public CentroGestorManager getCentroGestorManager() {
		return centroGestorManager;
	}

	/**
	 * Establece el centro gestor manager.
	 *
	 * @param centroGestorManager the centroGestorManager to set
	 */
	public void setCentroGestorManager(CentroGestorManager centroGestorManager) {
		this.centroGestorManager = centroGestorManager;
	}

	/**
	 * Obtiene el ministerio manager.
	 *
	 * @return the ministerioManager
	 */
	public MinisterioManager getMinisterioManager() {
		return ministerioManager;
	}

	/**
	 * Establece el ministerio manager.
	 *
	 * @param ministerioManager the ministerioManager to set
	 */
	public void setMinisterioManager(MinisterioManager ministerioManager) {
		this.ministerioManager = ministerioManager;
	}

	/**
	 * Obtiene el plantilla manager.
	 *
	 * @return the plantillaManager
	 */
	public PlantillaManager getPlantillaManager() {
		return plantillaManager;
	}

	/**
	 * Establece el plantilla manager.
	 *
	 * @param plantillaManager the plantillaManager to set
	 */
	public void setPlantillaManager(PlantillaManager plantillaManager) {
		this.plantillaManager = plantillaManager;
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
	 * Modelo manager.
	 *
	 * @return the centroGestorManager
	 */
	public ModeloManager modeloManager() {
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

	

}
