package es.map.ipsg.action.convocatoria;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.security.autentica.authentication.AutenticaAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;

import es.map.ips.common.model.OrderType;
import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.CamposPropiosQuery;
import es.map.ips.model.CentroGestorQuery;
import es.map.ips.model.CuerpoEscalaQuery;
import es.map.ips.model.DatosPropiosConfiguracionQuery;
import es.map.ips.model.DiscapacidadQuery;
import es.map.ips.model.EspecialidadQuery;
import es.map.ips.model.FormaAccesoQuery;
import es.map.ips.model.MinisterioQuery;
import es.map.ips.model.ModeloQuery;
import es.map.ips.model.MotivoReduccionTasaQuery;
import es.map.ips.model.OtrosTitulosQuery;
import es.map.ips.model.PlantillaPropiosQuery;
import es.map.ips.model.ProvinciaExamenQuery;
import es.map.ips.model.TituloOficialQuery;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.bean.CamposPropiosBean;
import es.map.ipsg.bean.CentroGestorBean;
import es.map.ipsg.bean.CuerpoEscalaBean;
import es.map.ipsg.bean.DatosPropiosConfigBean;
import es.map.ipsg.bean.DiscapacidadBean;
import es.map.ipsg.bean.EspecialidadBean;
import es.map.ipsg.bean.FormaAccesoBean;
import es.map.ipsg.bean.MinisterioBean;
import es.map.ipsg.bean.MotivoReduccionTasaBean;
import es.map.ipsg.bean.OtrosTitulosBean;
import es.map.ipsg.bean.PlantillaPropiosBean;
import es.map.ipsg.bean.ProvinciaExamenBean;
import es.map.ipsg.bean.TipoDocumentoBean;
import es.map.ipsg.bean.TituloOficialBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.form.CrearConvocatoriaForm;
import es.map.ipsg.manager.CamposPropiosManager;
import es.map.ipsg.manager.CentroGestorManager;
import es.map.ipsg.manager.ConvocatoriasManager;
import es.map.ipsg.manager.CuerpoEscalaManager;
import es.map.ipsg.manager.DatosPropiosConfiguracionManager;
import es.map.ipsg.manager.DiscapacidadManager;
import es.map.ipsg.manager.EspecialidadManager;
import es.map.ipsg.manager.FormaAccesoManager;
import es.map.ipsg.manager.MinisterioManager;
import es.map.ipsg.manager.MotivoReduccionTasaManager;
import es.map.ipsg.manager.OtrosTitulosManager;
import es.map.ipsg.manager.PlantillaPropiosManager;
import es.map.ipsg.manager.ProvinciaExamenManager;
import es.map.ipsg.manager.TituloOficialManager;
import es.map.ipsg.manager.UsuarioCentrogestorManager;
import es.map.ipsg.manager.UsuarioManager;
import es.map.ipsg.util.Constantes;

/**
 * El Class VerCrearConvocatoriaSpring.
 */
public class VerCrearConvocatoriaSpring extends AbstractSpring {

	/** La constante BUNDLE_RESOURCE. */
	private static final String BUNDLE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle
			.getBundle(BUNDLE_RESOURCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(VerCrearConvocatoriaSpring.class);
	
	/** La constante STRING_ERRORTITULOSSELECCIONADOS. */
	private static final String STRING_ERRORTITULOSSELECCIONADOS = "errorTitulosSeleccionados";
	
	/** La constante CENTRO_GESTOR. */
	private static final Character CENTRO_GESTOR = 'G';
	
	/** La constante STRING_RESOURCEBOUNDLE. */
	private static final String STRING_RESOURCEBOUNDLE = "ResourceBoundle: ";

	/** el convocatorias manager. */
	private ConvocatoriasManager convocatoriasManager;
	
	/** el cuerpo escala manager. */
	private CuerpoEscalaManager cuerpoEscalaManager;
	
	/** el centro gestor manager. */
	private CentroGestorManager centroGestorManager;
	
	/** el forma acceso manager. */
	private FormaAccesoManager formaAccesoManager;
	
	/** el titulo oficial manager. */
	private TituloOficialManager tituloOficialManager;
	
	/** el provincia examen manager. */
	private ProvinciaExamenManager provinciaExamenManager;
	
	/** el motivo reduccion tasa manager. */
	private MotivoReduccionTasaManager motivoReduccionTasaManager;
	
	/** el especialidad manager. */
	private EspecialidadManager especialidadManager;
	
	/** el usuario manager. */
	private UsuarioManager usuarioManager;
	
	/** el ministerios manager. */
	private MinisterioManager ministeriosManager;
	
	/** el otros titulos manager. */
	private OtrosTitulosManager otrosTitulosManager;
	
	/** el discapacidad manager. */
	private DiscapacidadManager discapacidadManager;
	
	/** el plantilla propios manager. */
	private PlantillaPropiosManager plantillaPropiosManager;
	
	/** el properties. */
	private Properties properties;

	/** el campos propios manager. */
	private CamposPropiosManager camposPropiosManager;
	
	/** el datos propios configuracion manager. */
	private DatosPropiosConfiguracionManager datosPropiosConfiguracionManager;	

	/** el usuario centro gestor manager. */
	private UsuarioCentrogestorManager usuarioCentrogestorManager;
	
	/**
	 * Crea una nueva ver crear convocatoria spring.
	 */
	public VerCrearConvocatoriaSpring() {
		try {
			setConvocatoriasManager((ConvocatoriasManager) getBean("convocatoriaManager"));
			setCuerpoEscalaManager((CuerpoEscalaManager) getBean("cuerposEscalaManager"));
			setCentroGestorManager((CentroGestorManager) getBean("centrosGestoresManager"));
			setFormaAccesoManager((FormaAccesoManager) getBean("formasAccesoManager"));
			setTituloOficialManager((TituloOficialManager) getBean("tituloOficialManager"));
			setProvinciaExamenManager((ProvinciaExamenManager) getBean("provinciaExamenManager"));
			setMotivoReduccionTasaManager((MotivoReduccionTasaManager) getBean("motivoReduccionTasaManager"));
			setEspecialidadManager((EspecialidadManager) getBean("especialidadManager"));
			setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
			setMinisteriosManager((MinisterioManager) getBean("ministeriosManager"));
			setOtrosTitulosManager((OtrosTitulosManager) getBean("otrosTitulosManager"));
			setDiscapacidadManager((DiscapacidadManager) getBean("discapacidadManager"));
			setCamposPropiosManager((CamposPropiosManager) getBean("camposPropiosManager"));
			setDatosPropiosConfiguracionManager((DatosPropiosConfiguracionManager) getBean("datosPropiosConfiguracionManager"));
			setPlantillaPropiosManager((PlantillaPropiosManager)getBean("plantillaPropiosManager"));
			setUsuarioCentrogestorManager((UsuarioCentrogestorManager) getBean("usuarioCentrogestorManager"));
			properties = (Properties) getBean("propertiesBean");

		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error VerCrearConvocatoriaSpring(): ",e);
		}
	}


	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		getLogger().debug("VerCrearConvocatoriaSpring -start");
		logger.info("Entra en el Action");
		
		//*********** METO EL PARAMETRO QUE ME INDICA EN QUE MENU ESTOY **********		
		String menu_convocatoria = RESOURCE_BUNDLE.getString("field.menu.convocatorias");
		this.getRequest().getSession().setAttribute("pagActiva", menu_convocatoria);
		String subMenu_convocatoria = RESOURCE_BUNDLE.getString("field.menuLateral.convocatorias.alta");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_convocatoria);
		//****************************************************************** 
		//Comprobar si viene del menu
		String menu = this.getRequest().getParameter("menu");
		if(menu != null && menu.equals("S")){
			limpiarSesion();
		}
		
		CrearConvocatoriaForm formulario;
		formulario = (CrearConvocatoriaForm) form;
		try {
			//Seteamos el modeloAsociado por si hay error para volver a mostrarlo en el formulario
			
			checkRolUsuario(formulario);
			cargaCombos(formulario);

			ArrayList<TituloOficialBean> tituloSel = new ArrayList<TituloOficialBean>();
			ArrayList<ProvinciaExamenBean> provinciaSel = new ArrayList<ProvinciaExamenBean>();
			ArrayList<MotivoReduccionTasaBean> motivoSel = new ArrayList<MotivoReduccionTasaBean>();
			if(!"alta".equals(formulario.getAccion())){
				setRequestAttribute("titulosSel", tituloSel);
				setRequestAttribute("provinciasSel", provinciaSel);
				setRequestAttribute("motivosSel", motivoSel);
			}
			
			//Seteamos el campo año del Boe con el año actual
			Calendar calendario = Calendar.getInstance();
			int anio = calendario.get(Calendar.YEAR);			
			
			formulario.setAnioBoe(String.valueOf(anio));
			
			
			getLogger().debug("VerCrearConvocatoriaSpring -end");
			return "success";
		
		} catch (Exception e) {
			logger.warn(e);
			this.getRequest().setAttribute("descripcionError", e.getMessage());
			logger.error("Error VerCrearConvocatoriaSpring() - doExecute - Crear convocatoria: ",e);
			return "nosuccess";
		}
	}

	/**
	 * Limpiar sesion.
	 */
	private void limpiarSesion() {
		this.getSession().removeAttribute("errorCentroGestor");
		this.getSession().removeAttribute("errorCuerpoEscala");
		this.getSession().removeAttribute("errorNumPlazas");
		this.getSession().removeAttribute("errorNumPlazasNumerico");
		this.getSession().removeAttribute("errorNumPlazasDisc");
		this.getSession().removeAttribute("errorNumPlazasDiscNumerico");
		this.getSession().removeAttribute("errorNumPlazas<numPlazasDisc");
		this.getSession().removeAttribute("errorFormaAcceso");
		this.getSession().removeAttribute("errorImporte");
		this.getSession().removeAttribute("errorDirigidoA");
		this.getSession().removeAttribute("errorTipoDocumentacion");
		this.getSession().removeAttribute(STRING_ERRORTITULOSSELECCIONADOS);
		this.getSession().removeAttribute("errorFechaBoe");
		this.getSession().removeAttribute("errorFechaInicio");
		this.getSession().removeAttribute("errorFechaFin");
		this.getSession().removeAttribute("org.apache.spring.ERROR");
	}

	/**
	 * Carga combos.
	 *
	 * @param formulario el formulario
	 */
	private void cargaCombos(CrearConvocatoriaForm formulario) {
		CentroGestorQuery centroGestorQuery = new CentroGestorQuery();
		List<CentroGestorBean> centroGestorList;
		centroGestorList = centroGestorManager
				.buscarCentroGestorComboVisibilidad(centroGestorQuery);
		setRequestAttribute("centrosGestores", centroGestorList);

		CuerpoEscalaQuery cuerpoEscalaQuery = new CuerpoEscalaQuery();
		List<CuerpoEscalaBean> cuerpoEscalaList;
		cuerpoEscalaList = cuerpoEscalaManager
				.buscarCuerposEscalaComboVisibilidad(cuerpoEscalaQuery);
		setRequestAttribute("cuerposEscalas", cuerpoEscalaList);

		FormaAccesoQuery formaAccesoQuery = new FormaAccesoQuery();
		List<FormaAccesoBean> formaAccesoList;
		formaAccesoList = formaAccesoManager
				.buscarFormaAccesoComboVisibilidad(formaAccesoQuery);
		setRequestAttribute("formasAcceso", formaAccesoList);

		TituloOficialQuery tituloOficialQuery = new TituloOficialQuery();
		List<TituloOficialBean> tituloOficialList;
		tituloOficialList = tituloOficialManager
				.buscarTituloOficialComboVisibilidad(tituloOficialQuery);
		
		if("alta".equals(formulario.getAccion())){
			ArrayList<TituloOficialBean> titulosEliminar;
			titulosEliminar = buscarTitulosSeleccionados(formulario,tituloOficialList);
			//Eliminar de la lista de la izquierda los que habia seleccionado anteriormente
			for(int i=0;i<titulosEliminar.size();i++){
				logger.info(titulosEliminar.get(i));
				tituloOficialList.remove(titulosEliminar.get(i));
			}
		}
		
		setRequestAttribute("titulosDisp", tituloOficialList);

		//INI Otros Titulos
		OtrosTitulosQuery otrosTitulosQuery = new OtrosTitulosQuery();
		List<OtrosTitulosBean> otrosTitulosList;
		
		if (!StringUtils.isEmpty(formulario.getCentroGestor())) {
			CentroGestorQuery centroGestorQ = new CentroGestorQuery();
			centroGestorQ.addIdCentroGestorIn(Integer.parseInt(formulario.getCentroGestor()));
			otrosTitulosQuery.setCentroGestor(centroGestorQ);
		}
		otrosTitulosList = otrosTitulosManager.buscarOtrosTitulosComboVisibilidad(otrosTitulosQuery);
		
		if("alta".equals(formulario.getAccion())){
			ArrayList<OtrosTitulosBean> titulosEliminar;
			titulosEliminar = buscarOtrosTitulosSeleccionados(formulario, otrosTitulosList);
			//Eliminar de la lista de la izquierda los que habia seleccionado anteriormente
			for(int i=0;i<titulosEliminar.size();i++){
				logger.info(titulosEliminar.get(i));
				otrosTitulosList.remove(titulosEliminar.get(i));
			}
		}
		
		setRequestAttribute("otrosTitulosDisp", otrosTitulosList);
		//FIN Otros Titulos
		
		
		//INI discapacidad
		DiscapacidadQuery discapacidadQuery = new DiscapacidadQuery();
		List<DiscapacidadBean> discapacidadList;
		
		if (!StringUtils.isEmpty(formulario.getCentroGestor())) {
			CentroGestorQuery centroGestorQ = new CentroGestorQuery();
			centroGestorQ.addIdCentroGestorIn(Integer.parseInt(formulario.getCentroGestor()));
			discapacidadQuery.setCentroGestor(centroGestorQ);
		}
		discapacidadList = discapacidadManager.buscarDiscapacidadComboVisibilidad(discapacidadQuery);
		
		if("alta".equals(formulario.getAccion())){
			ArrayList<DiscapacidadBean> titulosEliminar;
			titulosEliminar = buscarDiscapacidadSeleccionados(formulario, discapacidadList);
			//Eliminar de la lista de la izquierda los que habia seleccionado anteriormente
			for(int i=0;i<titulosEliminar.size();i++){
				logger.info(titulosEliminar.get(i));
				discapacidadList.remove(titulosEliminar.get(i));
			}
		}
		
		setRequestAttribute("discapacidadDisp", discapacidadList);
		//FIN Discapacidad	
		
		//INI Datos Propios
		
		CentroGestorBean centroGestorBean = null;
		if (!StringUtils.isEmpty(formulario.getCentroGestor())) {
			centroGestorBean = obtenerCentroGestor(formulario);
		}
		//Lista de Campos Propios
		ArrayList<DatosPropiosConfigBean> datosPropiosConfigList = obtenerListaCamposPropios(formulario, centroGestorBean);
		ArrayList<String> camposDisponibles = new ArrayList<String>();
		
		
		if (datosPropiosConfigList != null && datosPropiosConfigList.size() > 0) {
			camposDisponibles = obtenerCamposDisponibles(datosPropiosConfigList);
		}
		
		setRequestAttribute("camposDisponibles", camposDisponibles);	
		
		if("alta".equals(formulario.getAccion())){
			ArrayList<DatosPropiosConfigBean> datosPropiosEliminar;
			datosPropiosEliminar = buscarDatosPropiosConfSeleccionados(formulario, datosPropiosConfigList);
			//Eliminar de la lista de la izquierda los que habia seleccionado anteriormente
			for(int i=0;i<datosPropiosEliminar.size();i++){
				logger.info(datosPropiosEliminar.get(i));
				datosPropiosConfigList.remove(datosPropiosEliminar.get(i));
			}
		}	
	
		setRequestAttribute("datosPropiosConfigDisp", datosPropiosConfigList);
		//FIN Datos Propios			
		
		ProvinciaExamenQuery provinciaExamenQuery = new ProvinciaExamenQuery();
		List<ProvinciaExamenBean> provinciasExamenList;
		provinciasExamenList = provinciaExamenManager.buscarProvinciaExamenComboVisibilidad(provinciaExamenQuery);
		
		if("alta".equals(formulario.getAccion())){
			ArrayList<ProvinciaExamenBean> provinciasExamenEliminar;
			provinciasExamenEliminar = buscarProvinciasExamenSeleccionados(formulario,provinciasExamenList);
			for(int i=0;i<provinciasExamenEliminar.size();i++){
				logger.info(provinciasExamenEliminar.get(i));
				provinciasExamenList.remove(provinciasExamenEliminar.get(i));
			}
		}
		
		setRequestAttribute("provinciasDisp", provinciasExamenList);

		MotivoReduccionTasaQuery motivoReduccionTasaQuery = new MotivoReduccionTasaQuery();
		List<MotivoReduccionTasaBean> motivosList;
		motivosList = motivoReduccionTasaManager
				.buscarMotivoReduccionTasaComboVisibilidad(motivoReduccionTasaQuery);
		
		if("alta".equals(formulario.getAccion())){
			ArrayList<MotivoReduccionTasaBean> motivoReduduccionEliminar;
			motivoReduduccionEliminar = buscarMotivosSeleccionados(formulario,motivosList);
			for(int i=0;i<motivoReduduccionEliminar.size();i++){
				logger.info(motivoReduduccionEliminar.get(i));
				motivosList.remove(motivoReduduccionEliminar.get(i));
			}
		}
		
		setRequestAttribute("motivosDisp", motivosList);

		EspecialidadQuery especialidadQuery = new EspecialidadQuery();
		List<EspecialidadBean> especialidadesList;
		CuerpoEscalaQuery cuerpoEscalaAux = new CuerpoEscalaQuery();
		int codCuerpoEscala = 0;
		try{
			codCuerpoEscala = Integer.parseInt(formulario.getCuerpoEscala());
		}catch(Exception e){
			logger.error("Error VerCrearConvocatoriaSpring()-Parsear cuerpoEscala: "+ codCuerpoEscala,e);
		}
		cuerpoEscalaAux.setId(codCuerpoEscala);
		especialidadQuery.setCuerpoEscala(cuerpoEscalaAux);
		especialidadesList = especialidadManager.buscarEspecialidadComboVisibilidad(especialidadQuery);
		if("alta".equals(formulario.getAccion()) && formulario.getCuerpoEscala() != null && !"".equals(formulario.getCuerpoEscala())){
			ArrayList<EspecialidadBean> especialidadEliminar;
			especialidadEliminar = buscarEspecialidadesSeleccionados(formulario,especialidadesList);
			for(int i=0;i<especialidadEliminar.size();i++){
				logger.info(especialidadEliminar.get(i));
				especialidadesList.remove(especialidadEliminar.get(i));
			}
			setRequestAttribute("especialidadesDisp", especialidadesList);
		}
		
		

		ArrayList<TipoDocumentoBean> tipoDocumentoList = new ArrayList<TipoDocumentoBean>();
		TipoDocumentoBean tipoDocumentoBean = new TipoDocumentoBean();

		tipoDocumentoBean.setId("C");
		tipoDocumentoBean.setDescripcion(RESOURCE_BUNDLE
				.getString("field.tipoDocumento.Cualquiera"));
		tipoDocumentoList.add(tipoDocumentoBean);

		tipoDocumentoBean = new TipoDocumentoBean();
		tipoDocumentoBean.setId("R");
		tipoDocumentoBean.setDescripcion(RESOURCE_BUNDLE
				.getString("field.tipoDocumento.Exencion2"));
		tipoDocumentoList.add(tipoDocumentoBean);
		
		tipoDocumentoBean = new TipoDocumentoBean();
		tipoDocumentoBean.setId("N");
		tipoDocumentoBean.setDescripcion(RESOURCE_BUNDLE
				.getString("field.tipoDocumento.Ninguna"));
		tipoDocumentoList.add(tipoDocumentoBean);


		
		logger.info(STRING_RESOURCEBOUNDLE
				+ RESOURCE_BUNDLE.getString("field.tipoDocumento.Cualquiera"));
		logger.info(STRING_RESOURCEBOUNDLE
				+ RESOURCE_BUNDLE.getString("field.tipoDocumento.Ninguna"));
		logger.info(STRING_RESOURCEBOUNDLE
				+ RESOURCE_BUNDLE.getString("field.tipoDocumento.Exencion"));
		logger.info("Antes de rellenar los Atributos");
		setRequestAttribute("tipoDocumentoDisp", tipoDocumentoList);
		
		
		//Lista siglas ministerios Boe
		List<MinisterioBean> ministerioList;
		MinisterioQuery ministerioQuery = new MinisterioQuery();
		ministerioList = ministeriosManager.buscarMinisterioCombo(ministerioQuery);
		
		setRequestAttribute("ministerioList", ministerioList);
		

	}
	
	/**
	 * Buscar especialidades seleccionados.
	 *
	 * @param formulario el formulario
	 * @param especialidadesList el especialidades list
	 * @return el array list
	 */
	private ArrayList<EspecialidadBean> buscarEspecialidadesSeleccionados(
			CrearConvocatoriaForm formulario,
			List<EspecialidadBean> especialidadesList) {
		
		ArrayList<EspecialidadBean> auxEliminar = new ArrayList<EspecialidadBean>();
		ArrayList<EspecialidadBean> especialidadAux = new ArrayList<EspecialidadBean>();
		if(formulario.getEspecialidadesSeleccionados() != null){
			for(int i=0;i<formulario.getEspecialidadesSeleccionados().length;i++){
				for(int x=0;x<especialidadesList.size();x++){
					if(formulario.getEspecialidadesSeleccionados()[i].equals(especialidadesList.get(x).getId().toString())){
						EspecialidadBean aux = new EspecialidadBean();
						aux.setId(especialidadesList.get(x).getId());
						aux.setDescripcion(especialidadesList.get(x).getDescripcion());
						especialidadAux.add(aux);
						auxEliminar.add(especialidadesList.get(x));
					}
				}		
			}
		}
		setRequestAttribute("especialidadesSel", especialidadAux);
		return auxEliminar;
	}

	/**
	 * Buscar motivos seleccionados.
	 *
	 * @param formulario el formulario
	 * @param motivosList el motivos list
	 * @return el array list
	 */
	private ArrayList<MotivoReduccionTasaBean> buscarMotivosSeleccionados(
			CrearConvocatoriaForm formulario,
			List<MotivoReduccionTasaBean> motivosList) {
		
		ArrayList<MotivoReduccionTasaBean> auxEliminar = new ArrayList<MotivoReduccionTasaBean>();
		ArrayList<MotivoReduccionTasaBean> reduccionTasasAux = new ArrayList<MotivoReduccionTasaBean>();
		if(formulario.getMotivosExencionSeleccionados() != null){
			for(int i=0;i<formulario.getMotivosExencionSeleccionados().length;i++){
				for(int x=0;x<motivosList.size();x++){
					if(formulario.getMotivosExencionSeleccionados()[i].equals(motivosList.get(x).getId().toString())){
						MotivoReduccionTasaBean aux = new MotivoReduccionTasaBean();
						aux.setId(motivosList.get(x).getId());
						aux.setDescripcion(motivosList.get(x).getDescripcion());
						reduccionTasasAux.add(aux);
						auxEliminar.add(motivosList.get(x));
					}
				}		
			}
		}
		setRequestAttribute("motivosSel", reduccionTasasAux);
		return auxEliminar;
	}

	/**
	 * Buscar provincias examen seleccionados.
	 *
	 * @param formulario el formulario
	 * @param provinciasExamenList el provincias examen list
	 * @return el array list
	 */
	private ArrayList<ProvinciaExamenBean> buscarProvinciasExamenSeleccionados(
			CrearConvocatoriaForm formulario, List<ProvinciaExamenBean> provinciasExamenList) {
		ArrayList<ProvinciaExamenBean> auxEliminar = new ArrayList<ProvinciaExamenBean>();
		ArrayList<ProvinciaExamenBean> provinciaExamenAux = new ArrayList<ProvinciaExamenBean>();
		if(formulario.getProvinciaExamenSeleccionados() != null){
			for(int i=0;i<formulario.getProvinciaExamenSeleccionados().length;i++){
				for(int x=0;x<provinciasExamenList.size();x++){
					if(formulario.getProvinciaExamenSeleccionados()[i].equals(provinciasExamenList.get(x).getId().toString())){
						ProvinciaExamenBean aux = new ProvinciaExamenBean();
						aux.setId(provinciasExamenList.get(x).getId());
						aux.setDescripcion(provinciasExamenList.get(x).getDescripcion());
						provinciaExamenAux.add(aux);
						auxEliminar.add(provinciasExamenList.get(x));
					}
				}		
			}
		}
		setRequestAttribute("provinciasSel", provinciaExamenAux);
		return auxEliminar;
	}

	/**
	 * Buscar titulos seleccionados.
	 *
	 * @param formulario el formulario
	 * @param tituloOficialList el titulo oficial list
	 * @return el array list
	 */
	private ArrayList<TituloOficialBean> buscarTitulosSeleccionados(CrearConvocatoriaForm formulario,
			List<TituloOficialBean> tituloOficialList) {
		ArrayList<TituloOficialBean> auxEliminar = new ArrayList<TituloOficialBean>();
		ArrayList<TituloOficialBean> tituloOficialAux = new ArrayList<TituloOficialBean>();
		
		String[] titulos=formulario.getTitulosSeleccionadosInput().split(",");
		if(titulos!=null && titulos.length>0 && !formulario.getTitulosSeleccionadosInput().isEmpty()){
			for(int i=0;i<formulario.getTitulosSeleccionadosInput().split(",").length;i++){
				for(int x=0;x<tituloOficialList.size();x++){
					if(formulario.getTitulosSeleccionadosInput().split(",")[i].equals(tituloOficialList.get(x).getId().toString())){
						TituloOficialBean aux = new TituloOficialBean();
						aux.setId(tituloOficialList.get(x).getId());
						aux.setDescripcion(tituloOficialList.get(x).getDescripcion());
						tituloOficialAux.add(aux);
						auxEliminar.add(tituloOficialList.get(x));
					}
				}		
			}
		}
		setRequestAttribute("titulosSel", tituloOficialAux);
		return auxEliminar;
	}

	
	/**
	 * Busqueda de otros titulos segun el perfil GESTOR o ADMINISTRADOR.
	 *
	 * @param formulario el formulario
	 * @param otrosTitulosList el otros titulos list
	 * @return el array list
	 */
	private ArrayList<OtrosTitulosBean> buscarOtrosTitulosSeleccionados(CrearConvocatoriaForm formulario,
			List<OtrosTitulosBean> otrosTitulosList) {
		ArrayList<OtrosTitulosBean> auxEliminar = new ArrayList<OtrosTitulosBean>();
		ArrayList<OtrosTitulosBean> otrosTitulosAux = new ArrayList<OtrosTitulosBean>();
		
		if(formulario.getOtrosTitulosSeleccionados() != null){
			for (String otroTituloSel : formulario.getOtrosTitulosSeleccionados()) {
				for (OtrosTitulosBean otroTituloBean : otrosTitulosList) {
					if(otroTituloSel.equals(otroTituloBean.getId().toString())){
						OtrosTitulosBean aux = new OtrosTitulosBean();
						aux.setId(otroTituloBean.getId());
						aux.setDescripcion(otroTituloBean.getDescripcion());
						otrosTitulosAux.add(aux);
						auxEliminar.add(otroTituloBean);
					}
				}
			}
		}
		
		setRequestAttribute("otrosTitulosSel", otrosTitulosAux);
		return auxEliminar;
	}	
	
	/**
	 * Busqueda de Discapacidad segun el perfil GESTOR o ADMINISTRADOR.
	 *
	 * @param formulario el formulario
	 * @param discapacidadList el discapacidad list
	 * @return el array list
	 */
	private ArrayList<DiscapacidadBean> buscarDiscapacidadSeleccionados(CrearConvocatoriaForm formulario,
			List<DiscapacidadBean> discapacidadList) {
		ArrayList<DiscapacidadBean> auxEliminar = new ArrayList<DiscapacidadBean>();
		ArrayList<DiscapacidadBean> discapacidadAux = new ArrayList<DiscapacidadBean>();
		
		if(formulario.getDiscapacidadSeleccionados() != null){
			for (String discapacidadSel : formulario.getDiscapacidadSeleccionados()) {
				for (DiscapacidadBean discapacidadBean : discapacidadList) {
					if(discapacidadSel.equals(discapacidadBean.getId().toString())){
						DiscapacidadBean aux = new DiscapacidadBean();
						aux.setId(discapacidadBean.getId());
						aux.setDescripcion(discapacidadBean.getDescripcion());
						discapacidadAux.add(aux);
						auxEliminar.add(discapacidadBean);
					}
				}
			}
		}
		
		setRequestAttribute("discapacidadSel", discapacidadAux);
		return auxEliminar;
	}	
	
	/**
	 * Busqueda de Datos Propios segun el perfil GESTOR o ADMINISTRADOR.
	 *
	 * @param formulario el formulario
	 * @param datosPropiosList el datos propios list
	 * @return el array list
	 */
	private ArrayList<DatosPropiosConfigBean> buscarDatosPropiosConfSeleccionados(CrearConvocatoriaForm formulario,
			List<DatosPropiosConfigBean> datosPropiosList) {
		ArrayList<DatosPropiosConfigBean> auxEliminar = new ArrayList<DatosPropiosConfigBean>();
		ArrayList<DatosPropiosConfigBean> datosPropiosAux = new ArrayList<DatosPropiosConfigBean>();
		
		if(formulario.getDatosPropiosSeleccionados() != null){
			for (String datosPropiosSel : formulario.getDatosPropiosSeleccionados()) {
				for (DatosPropiosConfigBean datosPropiosBean : datosPropiosList) {
					if(datosPropiosSel.equals(datosPropiosBean.getIdDesplegable().toString())){
						DatosPropiosConfigBean aux = new DatosPropiosConfigBean();
						aux.setIdDesplegable(datosPropiosBean.getIdDesplegable());
						aux.setDescripcion(datosPropiosBean.getDescripcion());
						aux.setCampo(datosPropiosBean.getCampo());
						datosPropiosAux.add(aux);
						auxEliminar.add(datosPropiosBean);
					}
				}
			}
		}
		
		setRequestAttribute("datosPropiosSel", datosPropiosAux);
		return auxEliminar;
	}	

	/**
	 * Check rol usuario.
	 *
	 * @param form el form
	 */
	public void checkRolUsuario(CrearConvocatoriaForm form){
		SecurityContext context = (SecurityContext) this.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
		SecurityContextHolder.getContext().setAuthentication(context.getAuthentication());
		
		AutenticaAuthenticationToken user = (AutenticaAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

		UsuarioQuery usuarioQuery = new UsuarioQuery();
		usuarioQuery.setNif(user.getIdentificador());
		UsuarioBean usuarioBean = usuarioManager.buscarUsuario(usuarioQuery);
		logger.info("Perfil usuario: "+usuarioBean.getIdPerfil());
		setRequestAttribute("rol", usuarioBean.getIdPerfil());
		
		/*INI-TRA042*/
		String sPerfilUsuario = comprobarPerfilUsuario(usuarioBean);
		List<CentroGestorBean> listaCentrosGestores = new ArrayList<CentroGestorBean>();

		if(sPerfilUsuario.equalsIgnoreCase(Constantes.ROL_GESTOR))
		{
			listaCentrosGestores = usuarioCentrogestorManager.buscarCentrosGestoresByIdUsuario(usuarioBean.getId());
			form.setListaCentrosGestores(listaCentrosGestores);
		}
		setRequestAttribute("listaCentrosGestores", listaCentrosGestores);
		/*FIN-TRA042*/
		
		if(usuarioBean.getIdPerfil()==Constantes.PERFIL_GESTOR_INT){
			//Comprobamos si tenemos siglas
			form.setMinisterioSiglasAux("");
	    	form.setMinisterioConvocante("");
		}
	}
	
	/**
	 * Obtener centro gestor.
	 *
	 * @param formulario el formulario
	 * @return el centro gestor bean
	 */
	private CentroGestorBean obtenerCentroGestor(CrearConvocatoriaForm formulario) {
		CentroGestorQuery centroGestorQuery = new CentroGestorQuery();
		centroGestorQuery.setId(Integer.valueOf(formulario.getCentroGestor()));
		return  centroGestorManager.buscarCentroGestorUnico(centroGestorQuery);
	}	
	
	/**
	 * Obtener lista campos propios.
	 *
	 * @param formulario el formulario
	 * @param centroGestorBean el centro gestor bean
	 * @return el array list
	 */
	private ArrayList<DatosPropiosConfigBean> obtenerListaCamposPropios(CrearConvocatoriaForm formulario,
			CentroGestorBean centroGestorBean) {
		ArrayList<DatosPropiosConfigBean> datosPropiosConfigList = null;
		ArrayList<CamposPropiosBean> camposPropiosList = null;
		CamposPropiosQuery camposPropiosQuery = new CamposPropiosQuery();
		DatosPropiosConfiguracionQuery datosPropiosConfigQuery = new DatosPropiosConfiguracionQuery();
		
		if (centroGestorBean != null && centroGestorBean.getModelo() != null && centroGestorBean.getModelo().getIdModelo() != null) {
			camposPropiosQuery.addModeloIn(centroGestorBean.getModelo().getIdModelo());
			
			//Se obtienen las plantilla
			ArrayList<PlantillaPropiosBean> plantillasPropias = obtenerPlantillasPropios(formulario, centroGestorBean);
			
			if (plantillasPropias != null && plantillasPropias.size() > 0) {
				for (PlantillaPropiosBean plantillaPropiosBean : plantillasPropias) {
					camposPropiosQuery.addIdIn(plantillaPropiosBean.getCampoPropioBean().getId().intValue());
				}
			}
			
			camposPropiosList = camposPropiosManager.buscarCamposPropiosbyCampo(camposPropiosQuery);
			
			for (CamposPropiosBean camposPropiosBean : camposPropiosList) {
				if (camposPropiosBean.getId() != null && camposPropiosBean.getId() > 0) {
					datosPropiosConfigQuery.addCampoIn(camposPropiosBean.getId().intValue());
				}
			}
			
			datosPropiosConfigQuery.setJoin_campo(true);
			datosPropiosConfigList = datosPropiosConfiguracionManager.buscarDatosPropiosConfigbyCampo(datosPropiosConfigQuery);
		}
		
		
		return datosPropiosConfigList;
	}

	/**
	 * Obtener plantillas propios.
	 *
	 * @param formulario el formulario
	 * @param centroGestorBean el centro gestor bean
	 * @return el array list
	 */
	private ArrayList<PlantillaPropiosBean> obtenerPlantillasPropios(CrearConvocatoriaForm formulario, CentroGestorBean centroGestorBean) {
		PlantillaPropiosQuery plantillasPropiosQuery = new PlantillaPropiosQuery();
		plantillasPropiosQuery.setTipoPlantilla(CENTRO_GESTOR);
		ModeloQuery modeloQuery = new ModeloQuery();
		modeloQuery.setIdModelo(centroGestorBean.getModelo().getIdModelo());
		plantillasPropiosQuery.setModelo(modeloQuery);
		CentroGestorQuery centroGestorQueryAux = new CentroGestorQuery();
		centroGestorQueryAux.setId(centroGestorBean.getId());
		plantillasPropiosQuery.setCentroGestor(centroGestorQueryAux);
		plantillasPropiosQuery.addOrder("id", OrderType.ASC);
		ArrayList<PlantillaPropiosBean> plantillasPropias = plantillaPropiosManager.buscarPlantillaPropiosAll(plantillasPropiosQuery);
		
		if(centroGestorBean.getModelo().getNumModelo().equals(Constantes.MODELO79007)){
			plantillasPropias = obtenerPlantillaFinal(formulario.getCuerpoEscala(), plantillasPropias);
		}
		return plantillasPropias;
	}	
	
	/**
	 * Obtener campos disponibles.
	 *
	 * @param datosPropiosConfigList el datos propios config list
	 * @return el array list
	 */
	private ArrayList<String> obtenerCamposDisponibles(ArrayList<DatosPropiosConfigBean> datosPropiosConfigList) {
		ArrayList<String> camposDisponibles = new ArrayList<String>();
		
		for (DatosPropiosConfigBean datoPropioBean : datosPropiosConfigList) {
			if (datoPropioBean.getCampo() != null && !camposDisponibles.contains(datoPropioBean.getCampo().getCampo())) {
				camposDisponibles.add(datoPropioBean.getCampo().getCampo());
			}
		}
		return camposDisponibles;
	}
	
	/**
	 * Obtener plantilla final.
	 *
	 * @param idCuerpoEscala el id cuerpo escala
	 * @param plantillasPropias el plantillas propias
	 * @return el array list
	 */
	private ArrayList<PlantillaPropiosBean> obtenerPlantillaFinal(String idCuerpoEscala,
			ArrayList<PlantillaPropiosBean> plantillasPropias) {
		// Adaptaciones Secretarios Judiciales (solo campos Datos A y Datos B)
		CuerpoEscalaBean cuerpoEscalaBean = new CuerpoEscalaBean(); 

		if(!StringUtils.isEmpty(idCuerpoEscala)){
			cuerpoEscalaBean = cuerpoEscalaManager.obtenerCuerpoEscala(Integer.parseInt(idCuerpoEscala));
		}
		
		ArrayList<String> codSecretarios = new ArrayList<String>(Arrays.asList(properties.getProperty("codigos.secretarios.judiciales").split(",")));	
		ArrayList<String> codCamposSecretario = new ArrayList<String>(Arrays.asList(properties.getProperty("codigos.modelo007.pruebaOptativa").split(",")));
		
		ArrayList<PlantillaPropiosBean> plantillasEliminarPrueba = new ArrayList<PlantillaPropiosBean>();
		ArrayList<PlantillaPropiosBean> plantillasEliminarDatos = new ArrayList<PlantillaPropiosBean>();
		for (PlantillaPropiosBean plantillaBean : plantillasPropias) {
			if (codCamposSecretario.contains(plantillaBean.getCampoPropioBean().getId().toString())) {
				plantillasEliminarPrueba.add(plantillaBean);
			} else {
				plantillasEliminarDatos.add(plantillaBean);
			}
		}
		
		//Si no es secretario se cargan del modelo 790007 solo los datos asociados 1-Prueba Optativa Idioma y 2-Prueba Derecho Foral
		if (!StringUtils.isEmpty(cuerpoEscalaBean.getCodigo()) && !codSecretarios.contains(cuerpoEscalaBean.getCodigo())) {
			for (PlantillaPropiosBean plantillaPrueba : plantillasEliminarDatos) {
				plantillasPropias.remove(plantillaPrueba);
			}
		//Si es secretario se cargan del modelo 790007 solo los datos asociados 1-Prueba Optativa Idioma y 2-Prueba Derecho Foral	
		}else{
			for (PlantillaPropiosBean plantillaDatos : plantillasEliminarPrueba) {
				plantillasPropias.remove(plantillaDatos);
			}
		}
		return plantillasPropias;
	}

	/**
	 * Comprobar perfil usuario.
	 *
	 * @param usuarioBean2 el id usuario
	 * @return el string
	 */
	/*INI-TRA042*/
	private String comprobarPerfilUsuario(UsuarioBean usuarioBean) {
		String sPerfil = "";
		
		if(usuarioBean.getIdPerfil() != null &&   usuarioBean.getIdPerfil().toString().equals(Constantes.PERFIL_CONSULTOR))
		{
			sPerfil = Constantes.ROL_CONSULTOR;
		}
		if(usuarioBean.getIdPerfil() != null &&   usuarioBean.getIdPerfil().toString().equals(Constantes.PERFIL_SOPORTE))
		{
			sPerfil = Constantes.ROL_SOPORTE;
		}
		if(usuarioBean.getIdPerfil() != null && usuarioBean.getIdPerfil().toString().equals(Constantes.PERFIL_GESTOR))
		{
			sPerfil = Constantes.ROL_GESTOR;
		}
		if(usuarioBean.getIdPerfil() != null && usuarioBean.getIdPerfil().toString().equals(Constantes.PERFIL_ADMINISTRADOR))
		{
			sPerfil = Constantes.ROL_ADMINISTRADOR;
		}

		return sPerfil;

	}
	/*FIN-TRA042*/
	
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
	public void setConvocatoriasManager(
			ConvocatoriasManager convocatoriasManager) {
		this.convocatoriasManager = convocatoriasManager;
	}

	/**
	 * Obtiene el cuerpo escala manager.
	 *
	 * @return el cuerpo escala manager
	 */
	public CuerpoEscalaManager getCuerpoEscalaManager() {
		return cuerpoEscalaManager;
	}

	/**
	 * Establece el cuerpo escala manager.
	 *
	 * @param cuerpoEscalaManager el nuevo cuerpo escala manager
	 */
	public void setCuerpoEscalaManager(CuerpoEscalaManager cuerpoEscalaManager) {
		this.cuerpoEscalaManager = cuerpoEscalaManager;
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
	 * Obtiene el centro gestor manager.
	 *
	 * @return el centro gestor manager
	 */
	public CentroGestorManager getCentroGestorManager() {
		return centroGestorManager;
	}

	/**
	 * Obtiene el forma acceso manager.
	 *
	 * @return el forma acceso manager
	 */
	public FormaAccesoManager getFormaAccesoManager() {
		return formaAccesoManager;
	}

	/**
	 * Establece el forma acceso manager.
	 *
	 * @param formaAccesoManager el nuevo forma acceso manager
	 */
	public void setFormaAccesoManager(FormaAccesoManager formaAccesoManager) {
		this.formaAccesoManager = formaAccesoManager;
	}


	
	/**
	 * Obtiene el provincia examen manager.
	 *
	 * @return el provincia examen manager
	 */
	public ProvinciaExamenManager getProvinciaExamenManager() {
		return provinciaExamenManager;
	}

	/**
	 * Establece el provincia examen manager.
	 *
	 * @param provinciaExamenManager el nuevo provincia examen manager
	 */
	public void setProvinciaExamenManager(
			ProvinciaExamenManager provinciaExamenManager) {
		this.provinciaExamenManager = provinciaExamenManager;
	}
	
	/**
	 * Obtiene el motivo reduccion tasa manager.
	 *
	 * @return el motivo reduccion tasa manager
	 */
	public MotivoReduccionTasaManager getMotivoReduccionTasaManager() {
		return motivoReduccionTasaManager;
	}

	/**
	 * Establece el motivo reduccion tasa manager.
	 *
	 * @param motivoReduccionTasaManager el nuevo motivo reduccion tasa manager
	 */
	public void setMotivoReduccionTasaManager(
			MotivoReduccionTasaManager motivoReduccionTasaManager) {
		this.motivoReduccionTasaManager = motivoReduccionTasaManager;
	}

	/**
	 * Obtiene el especialidad manager.
	 *
	 * @return el especialidad manager
	 */
	public EspecialidadManager getEspecialidadManager() {
		return especialidadManager;
	}

	/**
	 * Establece el especialidad manager.
	 *
	 * @param especialidadManager el nuevo especialidad manager
	 */
	public void setEspecialidadManager(EspecialidadManager especialidadManager) {
		this.especialidadManager = especialidadManager;
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
	 * Obtiene el ministerios manager.
	 *
	 * @return el ministerios manager
	 */
	public MinisterioManager getMinisteriosManager() {
		return ministeriosManager;
	}

	/**
	 * Establece el ministerios manager.
	 *
	 * @param ministeriosManager el nuevo ministerios manager
	 */
	public void setMinisteriosManager(MinisterioManager ministeriosManager) {
		this.ministeriosManager = ministeriosManager;
	}
	
	/**
	 * Obtiene el otros titulos manager.
	 *
	 * @return el otros titulos manager
	 */
	public OtrosTitulosManager getOtrosTitulosManager() {
		return otrosTitulosManager;
	}

	/**
	 * Establece el otros titulos manager.
	 *
	 * @param otrosTitulosManager el nuevo otros titulos manager
	 */
	public void setOtrosTitulosManager(OtrosTitulosManager otrosTitulosManager) {
		this.otrosTitulosManager = otrosTitulosManager;
	}

	/**
	 * Obtiene el discapacidad manager.
	 *
	 * @return el discapacidad manager
	 */
	public DiscapacidadManager getDiscapacidadManager() {
		return discapacidadManager;
	}

	/**
	 * Establece el discapacidad manager.
	 *
	 * @param discapacidadManager el nuevo discapacidad manager
	 */
	public void setDiscapacidadManager(DiscapacidadManager discapacidadManager) {
		this.discapacidadManager = discapacidadManager;
	}
	
	/**
	 * Obtiene el campos propios manager.
	 *
	 * @return el campos propios manager
	 */
	public CamposPropiosManager getCamposPropiosManager() {
		return camposPropiosManager;
	}

	/**
	 * Establece el campos propios manager.
	 *
	 * @param camposPropiosManager el nuevo campos propios manager
	 */
	public void setCamposPropiosManager(CamposPropiosManager camposPropiosManager) {
		this.camposPropiosManager = camposPropiosManager;
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
	public void setPlantillaPropiosManager(PlantillaPropiosManager plantillaPropiosManager) {
		this.plantillaPropiosManager = plantillaPropiosManager;
	}

	/*INI-TRA042*/	
	/**
	 * Obtiene el titulo oficial manager.
	 *
	 * @return el titulo oficial manager
	 */
	public TituloOficialManager getTituloOficialManager() {
		return tituloOficialManager;
	}

	/**
	 * Establece el titulo oficial manager.
	 *
	 * @param tituloOficialManager el nuevo titulo oficial manager
	 */
	public void setTituloOficialManager(
			TituloOficialManager tituloOficialManager) {
		this.tituloOficialManager = tituloOficialManager;
	}


	/**
	 * @return the usuarioCentrogestorManager
	 */
	public UsuarioCentrogestorManager getUsuarioCentrogestorManager() {
		return usuarioCentrogestorManager;
	}


	/**
	 * @param usuarioCentrogestorManager the usuarioCentrogestorManager to set
	 */
	public void setUsuarioCentrogestorManager(UsuarioCentrogestorManager usuarioCentrogestorManager) {
		this.usuarioCentrogestorManager = usuarioCentrogestorManager;
	}
	/*FIN-TRA042*/	
	
}
