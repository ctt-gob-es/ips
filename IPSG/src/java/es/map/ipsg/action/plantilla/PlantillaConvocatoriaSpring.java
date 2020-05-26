package es.map.ipsg.action.plantilla;

import java.util.ArrayList;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import es.map.ips.common.model.OrderType;
import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.CamposPropiosQuery;
import es.map.ips.model.ConvocatoriaQuery;
import es.map.ips.model.ModeloQuery;
import es.map.ips.model.PlantillaPropiosQuery;
import es.map.ipsg.bean.CamposPropiosBean;
import es.map.ipsg.bean.CrearConvocatoriaBean;
import es.map.ipsg.bean.DetalleConvocatoriaBean;
import es.map.ipsg.bean.ModeloBean;
import es.map.ipsg.bean.PlantillaBean;
import es.map.ipsg.bean.PlantillaPropiosBean;
import es.map.ipsg.form.PlantillaForm;
import es.map.ipsg.manager.CamposPropiosManager;
import es.map.ipsg.manager.ConvocatoriasManager;
import es.map.ipsg.manager.PlantillaManager;
import es.map.ipsg.manager.PlantillaPropiosManager;

/**
 * El Class PlantillaConvocatoriaSpring.
 */
public class PlantillaConvocatoriaSpring extends AbstractSpring{
	
	/** el plantilla manager. */
	private PlantillaManager plantillaManager;
	
	/** el plantilla propios manager. */
	private PlantillaPropiosManager plantillaPropiosManager;
	
	/** el campo propio manager. */
	private CamposPropiosManager campoPropioManager;
	
	/** el convocatorias manager. */
	private ConvocatoriasManager convocatoriasManager;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(PlantillaConvocatoriaSpring.class);
	
	/** La constante BUNDLE_RESOURCE. */
	private static final String BUNDLE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_RESOURCE);	
	
	/** La constante CONVOCATORIA. */
	private static final Character CONVOCATORIA = 'C';
	
	/**
	 * Crea una nueva plantilla convocatoria spring.
	 */
	public PlantillaConvocatoriaSpring(){
			try {
				setPlantillaManager((PlantillaManager) getBean("plantillaManager"));
				setConvocatoriaManager((ConvocatoriasManager) getBean("convocatoriaManager"));
				setPlantillaPropiosManager((PlantillaPropiosManager)getBean("plantillaPropiosManager"));
				setCampoPropioManager((CamposPropiosManager)getBean("camposPropiosManager"));
			} catch (Exception e) {
				logger.warn(e);
				logger.error("Error PlantillaConvocatoriaSpring:",e);
			}
	}
	
	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		
		String menu_convocatoria = RESOURCE_BUNDLE.getString("field.menu.convocatorias");
		this.getRequest().getSession().setAttribute("pagActiva", menu_convocatoria);
		
		getLogger().debug("PlantillaConvocatoriaSpring -start");
		try {
			Long idConvocatoria, idPlantilla;
			DetalleConvocatoriaBean detalleConvocatoria;
			PlantillaBean plantillaBean;
			PlantillaForm formulario = (PlantillaForm) form;
			ConvocatoriaQuery convocatoriaQuery = new ConvocatoriaQuery();
			String submit = formulario.getSubmit();
			String[] listaIDs = this.getRequest().getParameterValues("listacheckbox");
			String idModelo;
			String idCampoPropio;
			String cadena;
			/*BUSCO LOS DETALLES DE LA CONVOCATORIA A PARTIR DE SU ID*/
			idConvocatoria = Long.valueOf(this.getRequest().getParameter("idConvocatoria"));
			
			if("Guardar".equals(submit)){
				
				convocatoriaQuery.setId(idConvocatoria);
				detalleConvocatoria = convocatoriasManager.detalleConvocatoria(convocatoriaQuery);
				/*BUSCO EL ID DE LA PLANTILLA Y OBTENGO EL BEAN*/
				idPlantilla = detalleConvocatoria.getIdPlantilla();
				plantillaBean = plantillaManager.obtenerPlantilla(idPlantilla);
				
				//siempre reseteamos los valores para el correcto funcionamiento
				PlantillaPropiosBean plantillaPropiosBean;
				ConvocatoriaQuery conv = new ConvocatoriaQuery();
				conv.setId(idConvocatoria);
				PlantillaPropiosQuery plantillaPropiosQuery = new PlantillaPropiosQuery();
				plantillaPropiosQuery.setConvocatoria(conv);
				ArrayList<PlantillaPropiosBean> listaPlantillaPropiosBean=plantillaPropiosManager.obtenerPlantillaPropiosById(plantillaPropiosQuery);
				plantillaBean.setListaPlantillaPropiosBean(listaPlantillaPropiosBean);
				
				if(listaPlantillaPropiosBean!=null && listaPlantillaPropiosBean.size()>0){
					//actualizamos con obligatoriedad false para todos
					plantillaPropiosBean = new PlantillaPropiosBean();
					for(int i=0;i<listaPlantillaPropiosBean.size();i++){
						plantillaPropiosBean.setId(listaPlantillaPropiosBean.get(i).getId());
						plantillaPropiosBean.setCampoPropioBean(listaPlantillaPropiosBean.get(i).getCampoPropioBean());
						plantillaPropiosBean.setCentroGestorBean(listaPlantillaPropiosBean.get(i).getCentroGestorBean());
						plantillaPropiosBean.setModeloBean(listaPlantillaPropiosBean.get(i).getModeloBean());
						plantillaPropiosBean.setObligatorio(false);
						plantillaPropiosBean.setTipoPlantilla(CONVOCATORIA);
						CrearConvocatoriaBean convBean = new CrearConvocatoriaBean();
						convBean.setIdConvocatoria(idConvocatoria);
						plantillaPropiosBean.setConvocatoriaBean(convBean);
						plantillaPropiosManager.actualizarPlantillaPropios(plantillaPropiosBean);
					}
				}
				
				
				//Si hay alguno campo propio chequeado guardamos los campos propios del gestor
				if(listaIDs!=null){
					for(int i=0;i<listaIDs.length;i++){		
						cadena=listaIDs[i];
						int tam = cadena.length();
						int position=cadena.indexOf('_');
						idModelo=cadena.substring(0, position);
						idCampoPropio=cadena.substring(position+1,tam);
						
						plantillaPropiosBean = new PlantillaPropiosBean();
						plantillaPropiosBean.setTipoPlantilla(CONVOCATORIA);
						plantillaPropiosBean.setObligatorio(true);
						ModeloBean modeloBean = new ModeloBean();
						modeloBean.setId(Integer.parseInt(idModelo));
						plantillaPropiosBean.setModeloBean(modeloBean);
						CrearConvocatoriaBean convBean = new CrearConvocatoriaBean();
						convBean.setIdConvocatoria(idConvocatoria);
						plantillaPropiosBean.setConvocatoriaBean(convBean);
						
						//antes de actualizar buscamos que ID tienen para no duplicar valores en BBDD
						plantillaPropiosQuery = new PlantillaPropiosQuery();
						CamposPropiosQuery camposPropiosQuery = new CamposPropiosQuery();
						camposPropiosQuery.setIdCampo(Integer.parseInt(idCampoPropio));
						plantillaPropiosQuery.setCamposPropios(camposPropiosQuery);
						ModeloQuery modeloQuery = new ModeloQuery();
						modeloQuery.setIdModelo(Integer.parseInt(idModelo));
						plantillaPropiosQuery.setModelo(modeloQuery);
						plantillaPropiosQuery.setTipoPlantilla(CONVOCATORIA);
						ConvocatoriaQuery convQuery = new ConvocatoriaQuery();
						convQuery.setId(idConvocatoria);
						plantillaPropiosQuery.setConvocatoria(convQuery);
						listaPlantillaPropiosBean=plantillaPropiosManager.obtenerPlantillaPropiosById(plantillaPropiosQuery);
						
						plantillaBean.setListaPlantillaPropiosBean(listaPlantillaPropiosBean);
						plantillaPropiosBean.setId(listaPlantillaPropiosBean.get(0).getId());
						plantillaPropiosBean.setCentroGestorBean(listaPlantillaPropiosBean.get(0).getCentroGestorBean());
						
						CamposPropiosBean campoPropioBean = new CamposPropiosBean();
						campoPropioBean.setId(Long.valueOf(Integer.parseInt(idCampoPropio)));
						plantillaPropiosBean.setCampoPropioBean(campoPropioBean);
						plantillaPropiosBean.setObligatorio(true);
						plantillaPropiosManager.actualizarPlantillaPropios(plantillaPropiosBean);
					}
				}else{
					//si no hay ninguno chequeado resteamos todos los campos propios de la convocatoria a false
					/*Busco los campos propios de la convocatoria una vez han sido actualizados*/
					
					conv = new ConvocatoriaQuery();
					conv.setId(idConvocatoria);
					plantillaPropiosQuery = new PlantillaPropiosQuery();
					plantillaPropiosQuery.setConvocatoria(conv);
					listaPlantillaPropiosBean=plantillaPropiosManager.obtenerPlantillaPropiosById(plantillaPropiosQuery);
					plantillaBean.setListaPlantillaPropiosBean(listaPlantillaPropiosBean);
					
					if(listaPlantillaPropiosBean!=null && listaPlantillaPropiosBean.size()>0){
						//actualizamos con obligatoriedad false para todos
						plantillaPropiosBean = new PlantillaPropiosBean();
						for(int i=0;i<listaPlantillaPropiosBean.size();i++){
							plantillaPropiosBean.setId(listaPlantillaPropiosBean.get(i).getId());
							plantillaPropiosBean.setCampoPropioBean(listaPlantillaPropiosBean.get(i).getCampoPropioBean());
							plantillaPropiosBean.setCentroGestorBean(listaPlantillaPropiosBean.get(i).getCentroGestorBean());
							plantillaPropiosBean.setModeloBean(listaPlantillaPropiosBean.get(i).getModeloBean());
							plantillaPropiosBean.setObligatorio(false);
							plantillaPropiosBean.setTipoPlantilla(CONVOCATORIA);
							CrearConvocatoriaBean convBean = new CrearConvocatoriaBean();
							convBean.setIdConvocatoria(idConvocatoria);
							plantillaPropiosBean.setConvocatoriaBean(convBean);
							plantillaPropiosManager.actualizarPlantillaPropios(plantillaPropiosBean);
						}
					}
				}
				
				/*Busco los campos propios de la convocatoria una vez han sido actualizados*/
				conv = new ConvocatoriaQuery();
				conv.setId(idConvocatoria);
				plantillaPropiosQuery = new PlantillaPropiosQuery();
				plantillaPropiosQuery.setConvocatoria(conv);
				listaPlantillaPropiosBean=plantillaPropiosManager.obtenerPlantillaPropiosById(plantillaPropiosQuery);
				plantillaBean.setListaPlantillaPropiosBean(listaPlantillaPropiosBean);
				
				formulario =(PlantillaForm) form;
				formulario = this.cargarFormulario(formulario, plantillaBean);
				formulario.setIdConvocatoria(idConvocatoria);
				setRequestAttribute("listaPlantillaPropiosBean", listaPlantillaPropiosBean);
				getLogger().debug("PlantillaConvocatoriaSpring -end");
				return "success";
			}
			
			if (idConvocatoria!=null) {
				convocatoriaQuery.setId(idConvocatoria);
				detalleConvocatoria = convocatoriasManager.detalleConvocatoria(convocatoriaQuery);
				/*BUSCO EL ID DE LA PLANTILLA Y OBTENGO EL BEAN*/
				idPlantilla = detalleConvocatoria.getIdPlantilla();
				plantillaBean = plantillaManager.obtenerPlantilla(idPlantilla);
				
				/*Busco los campos propios de la convocatoria*/
				ConvocatoriaQuery conv = new ConvocatoriaQuery();
				conv.setId(idConvocatoria);
				PlantillaPropiosQuery plantillaPropiosQuery = new PlantillaPropiosQuery();
				plantillaPropiosQuery.setConvocatoria(conv);
				plantillaPropiosQuery.addOrder(PlantillaPropiosQuery.CAMPOSPROPIOS,OrderType.ASC);
				ArrayList<PlantillaPropiosBean> listaPlantillaPropiosBean=plantillaPropiosManager.obtenerPlantillaPropiosById(plantillaPropiosQuery);
				plantillaBean.setListaPlantillaPropiosBean(listaPlantillaPropiosBean);

				
				/*CARGO EN EL FORMULARIO LA PLANTILLA*/
				formulario = this.cargarFormulario(formulario, plantillaBean);
				formulario.setIdConvocatoria(idConvocatoria);
				limpiarSesion();
				setRequestAttribute("listaPlantillaPropiosBean", listaPlantillaPropiosBean);
				getLogger().debug("PlantillaConvocatoriaSpring -end");
				return "success";
			}else{
				logger.warn("idConvocatoria null");
				this.getRequest().setAttribute("descripcionError", RESOURCE_BUNDLE.getString("field.plantilla.idConvocatoriaNull"));
				return "nosucces";				
			}
		} catch (Exception e) {
			logger.warn(e);
			this.getRequest().setAttribute("descripcionError", e.getMessage());
			logger.error("Error PlantillaConvocatoriaSpring - doExercute:",e);
			return "nosucces";
		}
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
	 * Cargar formulario.
	 *
	 * @param formulario el formulario
	 * @param plantilla el plantilla
	 * @return el plantilla form
	 */
	private PlantillaForm cargarFormulario(PlantillaForm formulario, PlantillaBean plantilla) {
		
		formulario.setId(plantilla.getId());
		formulario.setNif('S'==plantilla.getNif()?true:false);
		formulario.setPrimerApellido('S'==plantilla.getPrimerApellido()?true:false);
		formulario.setSegundoApellido('S'==plantilla.getSegundoApellido()?true:false);
		formulario.setNombre('S'==plantilla.getNombre()?true:false);
		formulario.setFechaNacimiento('S'==plantilla.getFechaNacimiento()?true:false);
		formulario.setSexo('S'==plantilla.getSexo()?true:false);
		formulario.setNacionalidad('S'==plantilla.getNacionalidad()?true:false);
		formulario.setCorreoElectronico('S'==plantilla.getCorreoElectronico()?true:false);
		formulario.setTelefono('S'==plantilla.getTelefono()?true:false);
		formulario.setVia('S'==plantilla.getVia()?true:false);
		formulario.setCodigoPostal('S'==plantilla.getCodigoPostal()?true:false);
		formulario.setMunicipio('S'==plantilla.getMunicipio()?true:false);
		formulario.setProvincia('S'==plantilla.getProvincia()?true:false);
		formulario.setPais('S'==plantilla.getPais()?true:false);
		formulario.setCuerpo('S'==plantilla.getCuerpo()?true:false);
		formulario.setEspecialidad('S'==plantilla.getEspecialidad()?true:false);
		formulario.setFormaacceso('S'==plantilla.getFormaacceso()?true:false);
		formulario.setEntidadConvocante('S'==plantilla.getEntidadConvocante()?true:false);
		formulario.setFechaBoe('S'==plantilla.getFechaBoe()?true:false);
		formulario.setProvinciaExamen('S'==plantilla.getProvinciaExamen()?true:false);
		formulario.setPorcentajeDiscapacidad('S'==plantilla.getPorcentajeDiscapacidad()?true:false);
		formulario.setTipoDiscapacidad('S'==plantilla.getTipoDiscapacidad()?true:false);
		formulario.setDetalleDiscapacidad('S'==plantilla.getDetalleDiscapacidad()?true:false);
		formulario.setTitulosExigidos('S'==plantilla.getTitulosExigidos()?true:false);
		formulario.setOtrosTitulos('S'==plantilla.getOtrosTitulos()?true:false);
		formulario.setDatosA('S'==plantilla.getDatosA()?true:false);
		formulario.setDatosB('S'==plantilla.getDatosB()?true:false);
		formulario.setDatosC('S'==plantilla.getDatosC()?true:false);
		formulario.setReservaDiscapacidad('S'==plantilla.getReservaDiscapacidad()?true:false);
		formulario.setCodigoCuerpoEscala('S'==plantilla.getCodigoCuerpoEscala()?true:false);
		formulario.setCodigoEspecialidad('S'==plantilla.getCodigoEspecialidad()?true:false);
		formulario.setCodigoMinisterio('S'==plantilla.getCodigoMinisterio()?true:false);
		formulario.setCodigoPaisDomicilio('S'==plantilla.getCodigoPaisDomicilio()?true:false);
		formulario.setCodigoProvinciaDomicilio('S'==plantilla.getCodigoProvinciaDomicilio()?true:false);
		formulario.setCodigoProvinciaExamen('S'==plantilla.getCodigoProvinciaExamen()?true:false);
		formulario.setCodigoTituloOficial('S'==plantilla.getCodigoTituloOficial()?true:false);
		
		return formulario;
	}
	
	/**
	 * Obtiene el plantilla manager.
	 *
	 * @return el plantilla manager
	 */
	public PlantillaManager getPlantillaManager() {
		return plantillaManager;
	}

	/**
	 * Establece el plantilla manager.
	 *
	 * @param plantillaManager el nuevo plantilla manager
	 */
	public void setPlantillaManager(PlantillaManager plantillaManager) {
		this.plantillaManager = plantillaManager;
	}
	
	/**
	 * Obtiene el convocatoria manager.
	 *
	 * @return el convocatoria manager
	 */
	public ConvocatoriasManager getConvocatoriaManager() {
		return convocatoriasManager;
	}

	/**
	 * Establece el convocatoria manager.
	 *
	 * @param convocatoriaManager el nuevo convocatoria manager
	 */
	public void setConvocatoriaManager(ConvocatoriasManager convocatoriaManager) {
		this.convocatoriasManager = convocatoriaManager;
	}
	
	/**
	 * Obtiene el plantilla propios manager.
	 *
	 * @return el plantilla propios manager
	 */
	public PlantillaPropiosManager getPlantillaPropiosManager() {
		return plantillaPropiosManager;
	}

	/**
	 * Establece el plantilla propios manager.
	 *
	 * @param plantillaPropiosManager el nuevo plantilla propios manager
	 */
	public void setPlantillaPropiosManager(
			PlantillaPropiosManager plantillaPropiosManager) {
		this.plantillaPropiosManager = plantillaPropiosManager;
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
	 * Limpiar sesion.
	 */
	private void limpiarSesion() {
		this.getSession().removeAttribute("org.apache.spring.ERROR");
	}
}