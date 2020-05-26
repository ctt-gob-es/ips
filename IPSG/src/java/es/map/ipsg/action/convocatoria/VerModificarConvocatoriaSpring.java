package es.map.ipsg.action.convocatoria;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.util.StringUtils;

import es.map.ips.common.model.OrderType;
import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringMessages;
import es.map.ips.model.CamposPropiosQuery;
import es.map.ips.model.CentroGestorQuery;
import es.map.ips.model.ConvocatoriaQuery;
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
import es.map.ipsg.bean.ModificarConvocatoriaBean;
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
 * El Class VerModificarConvocatoriaSpring.
 *
 * @author everis
 */
public class VerModificarConvocatoriaSpring extends AbstractSpring {
	
	/** La constante BUNDLE_RESOURCE. */
	private static final String BUNDLE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_RESOURCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(VerModificarConvocatoriaSpring.class);
	
	/** La constante STRING_TITULOSSEL. */
	private static final String STRING_TITULOSSEL = "titulosSel";
	
	/** La constante STRING_PROVINCIASSEL. */
	private static final String STRING_PROVINCIASSEL = "provinciasSel";
	
	/** La constante STRING_MOTIVOSSEL. */
	private static final String STRING_MOTIVOSSEL = "motivosSel";
	
	/** La constante STRING_ESPECIALIDADESSEL. */
	private static final String STRING_ESPECIALIDADESSEL = "especialidadesSel";
	
	/** La constante STRING_OTROSTITULOSSEL. */
	private static final String STRING_OTROSTITULOSSEL = "otrosTitulosSel";
	
	/** La constante STRING_DISCAPACIDADSEL. */
	private static final String STRING_DISCAPACIDADSEL = "discapacidadSel";
	
	/** La constante STRING_ESTADO. */
	private static final String STRING_ESTADO = "estado";
	
	/** La constante STRING_DATOSPROPIOSSEL. */
	private static final String STRING_DATOSPROPIOSSEL = "datosPropiosSel";
	
	/** La constante CENTRO_GESTOR. */
	private static final Character CENTRO_GESTOR = 'G';
	
	
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
	
	/** el campos propios manager. */
	private CamposPropiosManager camposPropiosManager;
	
	/** el datos propios configuracion manager. */
	private DatosPropiosConfiguracionManager datosPropiosConfiguracionManager;
	
	/** el plantilla propios manager. */
	private PlantillaPropiosManager plantillaPropiosManager;
	
	/** el usuario centro gestor manager. */
	private UsuarioCentrogestorManager usuarioCentrogestorManager;
	
	/** el properties. */
	private Properties properties;

	/** el titulo sel. */
	private ArrayList<TituloOficialBean> tituloSel= new ArrayList<TituloOficialBean>();
	
	/** el provincia sel. */
	private ArrayList<ProvinciaExamenBean> provinciaSel=new ArrayList<ProvinciaExamenBean>();
	
	/** el motivo sel. */
	private ArrayList<MotivoReduccionTasaBean> motivoSel=new ArrayList<MotivoReduccionTasaBean>();
	
	/** el especialidad sel. */
	private ArrayList<EspecialidadBean> especialidadSel=new ArrayList<EspecialidadBean>();
	
	/** el otros titulos sel. */
	private ArrayList<OtrosTitulosBean> otrosTitulosSel=new ArrayList<OtrosTitulosBean>();
	
	/** el discapacidad sel. */
	private ArrayList<DiscapacidadBean> discapacidadSel=new ArrayList<DiscapacidadBean>();
	
	/** el datos propios sel. */
	private ArrayList<DatosPropiosConfigBean> datosPropiosSel=new ArrayList<DatosPropiosConfigBean>();
	
	/**
	 * Crea una nueva ver modificar convocatoria spring.
	 */
	public VerModificarConvocatoriaSpring() {
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
			logger.error("Error VerModificarConvocatoriaSpring(): ",e);
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
		
		getLogger().debug("VerModificarConvocatoriaSpring -start");
		logger.info("Entra en el Action Modificar Convocatoria");
		logger.info("Action Detalle Convocatoria");
		String codSolicitud = (String) this.getRequest().getParameter("registro");
		CrearConvocatoriaForm formulario;
		formulario = (CrearConvocatoriaForm) form;
		
		//Comprobar si viene del menu
		String menu = this.getRequest().getParameter("menu");
		if(menu != null && menu.equals("S")){
			limpiarSesion();
		}
		
		logger.info("registro: "+codSolicitud);
		
		try{
			logger.info("codSolicitud: "+codSolicitud);
			
			if("".equals(codSolicitud) || codSolicitud == null){
				codSolicitud= (String) this.getRequest().getAttribute("idConvocatoria");
				logger.info("idConvocatoria: "+codSolicitud);
			}
			
			if("".equals(codSolicitud) || codSolicitud == null){
				codSolicitud= formulario.getIdConvocatoria();
				logger.info("idConvocatoria del formulario: "+codSolicitud);
			}
			
			Long idConvocatoria = Long.parseLong(codSolicitud);
			logger.info(idConvocatoria);
			ConvocatoriaQuery convocatoriaQuery = new ConvocatoriaQuery();		
			convocatoriaQuery.setId(idConvocatoria);
			ModificarConvocatoriaBean convocatoria= convocatoriasManager.obtenerModificarConvocatoria(convocatoriaQuery);
			
			//obtenemos el modelo asociado al centroGestor y lo pasamos al formulario
			CentroGestorQuery centroGestorQuery = new CentroGestorQuery();
			centroGestorQuery.setId(convocatoria.getIdCentroGestor());
			CentroGestorBean centroGestorBean = centroGestorManager.buscarCentroGestorUnico(centroGestorQuery);
			String modeloAsociado = centroGestorBean.getModelo().getNumModelo();
			formulario.setModeloAsociado(modeloAsociado);
			setRequestAttribute("modeloAsociado", modeloAsociado);
			checkRolUsuario(formulario);
			
			if(!"alta".equals(formulario.getAccion())){				
				String cuerpoEscala = convocatoria.getIdCuerpoEscala()+"";
				logger.info("Antes de rellenar el formulario");
				
				formulario.setCentroGestor(convocatoria.getIdCentroGestor()+"");
				formulario.setDsCentroGestor(convocatoria.getDsCentroGestor());				
				
				if(cuerpoEscala!=null){
					formulario.setCuerpoEscala(cuerpoEscala);
					formulario.setDsCuerpoEscala(convocatoria.getDsCuerpoEscala());
				}
				
				formulario.setMinisterioConvocante(convocatoria.getIdMinisterioConvocante());
				formulario.setDirigidoA(convocatoria.getDirigidoA());
				formulario.setFechaBoe(convocatoria.getFechaBoe());
				formulario.setFechaInicio(convocatoria.getFechaInicio());
				formulario.setFechaTermino(convocatoria.getFechaTermino());
				formulario.setFechaIniSub(convocatoria.getFechaInicioSub());
				formulario.setFechaFinSub(convocatoria.getFechaFinSub());				
				formulario.setFormaAcceso(convocatoria.getFormaAcceso().getId()+"");
				formulario.setEnlace(convocatoria.getEnlace());
				
				if(convocatoria.getCodigoFormaAcceso() != null){	
					formulario.setCodigoFormaAcceso(convocatoria.getCodigoFormaAcceso());
				}else{
					FormaAccesoQuery formaAccesoQuery = new FormaAccesoQuery();
					formaAccesoQuery.setId(convocatoria.getFormaAcceso().getId());
					ArrayList<FormaAccesoBean> listaF=formaAccesoManager.buscarFormaAccesoAll(formaAccesoQuery);
					formulario.setCodigoFormaAcceso(listaF.get(0).getCodigo());
				}	
				
				formulario.setImporte(convocatoria.getImporte()+"");
				formulario.setNumPlazas(convocatoria.getNumPlazas()+"");
				formulario.setNumPlazasDisc(convocatoria.getNumPlazasDisc()+"");
				formulario.setIdConvocatoria(convocatoria.getIdConvocatoria()+"");
				formulario.setEjercicio(convocatoria.getEjercicio());
				formulario.setPresencial(convocatoria.getPresencial());
			}
						
			ArrayList<TipoDocumentoBean> tipoDocumentoList = new ArrayList<TipoDocumentoBean>();
			TipoDocumentoBean tipoDocumentoBean=new TipoDocumentoBean();
			
			tipoDocumentoBean.setId("C");
			tipoDocumentoBean.setDescripcion(RESOURCE_BUNDLE.getString("field.tipoDocumento.Cualquiera"));
			tipoDocumentoList.add(tipoDocumentoBean);
			
			tipoDocumentoBean=new TipoDocumentoBean();
			tipoDocumentoBean.setId("N");
			tipoDocumentoBean.setDescripcion(RESOURCE_BUNDLE.getString("field.tipoDocumento.Ninguna"));
			tipoDocumentoList.add(tipoDocumentoBean);
			
			tipoDocumentoBean=new TipoDocumentoBean();
			tipoDocumentoBean.setId("R");
			tipoDocumentoBean.setDescripcion(RESOURCE_BUNDLE.getString("field.tipoDocumento.Exencion2"));
			tipoDocumentoList.add(tipoDocumentoBean);
			
			if(!"alta".equals(formulario.getAccion())){
				formulario.setTipoDocumentacion(convocatoria.getTipoDocumentacion()+"");
			}
			
			setRequestAttribute("tipoDocumentoDisp", tipoDocumentoList);
			
			if(!"alta".equals(formulario.getAccion())){
				tituloSel=convocatoria.getTitulos();
				provinciaSel=convocatoria.getProvincias();
				motivoSel=convocatoria.getMotivosExencion();
				especialidadSel=convocatoria.getEspecialidades();
				otrosTitulosSel=convocatoria.getOtrosTitulos();
				discapacidadSel=convocatoria.getDiscapacidades();
				datosPropiosSel=convocatoria.getDatosPropiosConfigBean();
				
				setRequestAttribute(STRING_TITULOSSEL, convocatoria.getTitulos());
				setRequestAttribute(STRING_PROVINCIASSEL, convocatoria.getProvincias());
				setRequestAttribute(STRING_MOTIVOSSEL, convocatoria.getMotivosExencion());
				setRequestAttribute(STRING_ESPECIALIDADESSEL, convocatoria.getEspecialidades());
				//Estado de la convocatoria
				setRequestAttribute(STRING_ESTADO, convocatoria.getEstado());
			}
			
			cargaCombos(formulario,convocatoria, centroGestorBean);
			SpringMessages aux;
			aux=this.getErrors(this.getRequest());
			
			logger.info("TamñanoErrores: "+aux.size());
			getLogger().debug("VerModificarConvocatoriaSpring -end");
			
			return "success";
		
		}catch(Exception e){
			logger.warn(e);
			this.getRequest().setAttribute("descripcionError", e.getMessage());
			logger.error("Error VerModificarConvocatoriaSpring()- doExecute: ",e);
			
			return "nosuccess";
		}
	}

	/**
	 * Carga combos.
	 *
	 * @param formulario el formulario
	 * @param convocatoria el convocatoria
	 * @param centroGestorBean el centro gestor bean
	 */
	private void cargaCombos(CrearConvocatoriaForm formulario, ModificarConvocatoriaBean convocatoria, CentroGestorBean centroGestorBean) {
		
		logger.info("Cargando Combos");
		
		CuerpoEscalaQuery cuerpoEscalaQuery;
		FormaAccesoQuery formaAccesoQuery = new FormaAccesoQuery();
		
		List<FormaAccesoBean> formaAccesoList;
		formaAccesoList = formaAccesoManager.buscarFormaAccesoComboVisibilidad(formaAccesoQuery);
		setRequestAttribute("formasAcceso", formaAccesoList);

		TituloOficialQuery tituloOficialQuery = new TituloOficialQuery();
		List<TituloOficialBean> tituloOficialList;
		tituloOficialList = tituloOficialManager.buscarTituloOficialComboVisibilidad(tituloOficialQuery);
		
		if("alta".equals(formulario.getAccion())){
			ArrayList<TituloOficialBean> titulosEliminar;
			titulosEliminar = buscarTitulosSeleccionados(formulario,tituloOficialList);
			
			//Eliminar de la lista de la izquierda los que habia seleccionado anteriormente
			for(int i=0;i<titulosEliminar.size();i++){
				logger.info(titulosEliminar.get(i));
				tituloOficialList.remove(titulosEliminar.get(i));
			}
		}else{
			//Borramos los seleccionados del combo disponible
			tituloSel = convocatoria.getTitulos();
			
			for(int i=0;i<tituloSel.size();i++){
				boolean noEncontrado=true;
				int j=0;
				
				while(j<tituloOficialList.size()&&noEncontrado){
					int idTituloSel=tituloSel.get(i).getId().intValue();
					int idTituloOficial=tituloOficialList.get(j).getId().intValue();
					
					if(idTituloSel==idTituloOficial){
						noEncontrado=false;
						tituloOficialList.remove(j);
					}else{
						j++;
					}
				}
			}
			setRequestAttribute(STRING_TITULOSSEL, tituloSel);
		}	
		setRequestAttribute("titulosDisp", tituloOficialList);
		
		
		ProvinciaExamenQuery provinciaExamenQuery = new ProvinciaExamenQuery();
		List<ProvinciaExamenBean> provinciasExamenList;
		provinciasExamenList=provinciaExamenManager.buscarProvinciaExamenComboVisibilidad(provinciaExamenQuery);
		
		if("alta".equals(formulario.getAccion())){
			ArrayList<ProvinciaExamenBean> provinciasExamenEliminar;
			provinciasExamenEliminar = buscarProvinciasExamenSeleccionados(formulario,provinciasExamenList);
			
			for(int i=0;i<provinciasExamenEliminar.size();i++){
				logger.info(provinciasExamenEliminar.get(i));
				provinciasExamenList.remove(provinciasExamenEliminar.get(i));
			}
		}else{
			//Borramos los seleccionados del combo disponible
			provinciaSel = convocatoria.getProvincias();
			
			for(int i=0;i<provinciaSel.size();i++){
				boolean noEncontrado=true;
				int j=0;
				
				while(j<provinciasExamenList.size()&&noEncontrado){
					int idProvinciaSel=provinciaSel.get(i).getId().intValue();
					int idProvinciasList=provinciasExamenList.get(j).getId().intValue();
					
					if(idProvinciaSel==idProvinciasList){
						noEncontrado=false;
						provinciasExamenList.remove(j);
					}else{
						j++;
					}
				}
			}
			setRequestAttribute(STRING_PROVINCIASSEL, provinciaSel);	
		}
		setRequestAttribute("provinciasDisp", provinciasExamenList);
		
		MotivoReduccionTasaQuery motivoReduccionTasaQuery= new MotivoReduccionTasaQuery();
		List<MotivoReduccionTasaBean> motivosList;
		motivosList= motivoReduccionTasaManager.buscarMotivoReduccionTasaComboVisibilidad(motivoReduccionTasaQuery);
		
		if("alta".equals(formulario.getAccion())){
			ArrayList<MotivoReduccionTasaBean> motivoReduduccionEliminar;
			motivoReduduccionEliminar = buscarMotivosSeleccionados(formulario,motivosList);
			for(int i=0;i<motivoReduduccionEliminar.size();i++){
				logger.info(motivoReduduccionEliminar.get(i));
				motivosList.remove(motivoReduduccionEliminar.get(i));
			}
		}else{
		
			//Borramos los seleccionados del combo disponible
			motivoSel = convocatoria.getMotivosExencion();
			
			for(int i=0;i<motivoSel.size();i++){
				boolean noEncontrado=true;
				int j=0;
				
				while(j<motivosList.size()&&noEncontrado){
					int idMotivoSel=motivoSel.get(i).getId().intValue();
					int idMotivosList=motivosList.get(j).getId().intValue();
					
					if(idMotivoSel==idMotivosList){
						noEncontrado=false;
						motivosList.remove(j);
					}else{
						j++;
					}
				}
			}
			setRequestAttribute(STRING_MOTIVOSSEL, motivoSel);	
		}
		setRequestAttribute("motivosDisp", motivosList);
		
		
		//Otros Titulos
		if (otrosTitulosSel != null && otrosTitulosSel.size() > 0) {
			formulario.setOtrosTitulosFlag(true);
		}
		
		OtrosTitulosQuery otrosTitulosQuery = new OtrosTitulosQuery();
		List<OtrosTitulosBean> otrosTitulosList;
		
		if (!StringUtils.isEmpty(formulario.getCentroGestor())) {
			CentroGestorQuery centroGestorQ = new CentroGestorQuery();
			centroGestorQ.addIdCentroGestorIn(Integer.parseInt(formulario.getCentroGestor()));
			otrosTitulosQuery.setCentroGestor(centroGestorQ);
		}
		otrosTitulosList = otrosTitulosManager.buscarOtrosTitulosComboVisibilidad(otrosTitulosQuery);
			
		if("alta".equals(formulario.getAccion())){
			ArrayList<OtrosTitulosBean> otrosTitulosEliminar;
			otrosTitulosEliminar = buscarOtrosTitulosSeleccionados(formulario,otrosTitulosList);
			for(int i=0;i<otrosTitulosEliminar.size();i++){
				logger.info(otrosTitulosEliminar.get(i));
				otrosTitulosList.remove(otrosTitulosEliminar.get(i));
			}
		}else{
			
			for(int i=0;i<otrosTitulosSel.size();i++){
				boolean noEncontrado=true;
				int j=0;
				
				while(j<otrosTitulosList.size()&&noEncontrado){
					int idMotivoSel=otrosTitulosSel.get(i).getId().intValue();
					int idMotivosList=otrosTitulosList.get(j).getId().intValue();
					
					if(idMotivoSel==idMotivosList){
						noEncontrado=false;
						otrosTitulosList.remove(j);
					}else{
						j++;
					}
				}
			}
			setRequestAttribute(STRING_OTROSTITULOSSEL, otrosTitulosSel);	
		}
		setRequestAttribute("otrosTitulosDisp", otrosTitulosList);
		
		//Discapacidad
		if (discapacidadSel != null && discapacidadSel.size() > 0) {
			formulario.setDiscapacidadFlag(true);
		}
		
		DiscapacidadQuery discapacidadQuery = new DiscapacidadQuery();
		List<DiscapacidadBean> discapacidadList;
		
		if (!StringUtils.isEmpty(formulario.getCentroGestor())) {
			CentroGestorQuery centroGestorQ = new CentroGestorQuery();
			centroGestorQ.addIdCentroGestorIn(Integer.parseInt(formulario.getCentroGestor()));
			discapacidadQuery.setCentroGestor(centroGestorQ);
		}
		discapacidadList = discapacidadManager.buscarDiscapacidadComboVisibilidad(discapacidadQuery);
		
		if("alta".equals(formulario.getAccion())){
			ArrayList<DiscapacidadBean> discapacidadEliminar;
			discapacidadEliminar = buscarDiscapacidadSeleccionados(formulario,discapacidadList);
			for(int i=0;i<discapacidadEliminar.size();i++){
				logger.info(discapacidadEliminar.get(i));
				discapacidadList.remove(discapacidadEliminar.get(i));
			}
		}else{
			
			for(int i=0;i<discapacidadSel.size();i++){
				boolean noEncontrado=true;
				int j=0;
				
				while(j<discapacidadList.size()&&noEncontrado){
					int idMotivoSel=discapacidadSel.get(i).getId().intValue();
					int idMotivosList=discapacidadList.get(j).getId().intValue();
					
					if(idMotivoSel==idMotivosList){
						noEncontrado=false;
						discapacidadList.remove(j);
					}else{
						j++;
					}
				}
			}
			setRequestAttribute(STRING_DISCAPACIDADSEL, discapacidadSel);	
		}
		setRequestAttribute("discapacidadDisp", discapacidadList);	
		
		
		// INI - Datos Propios
		
		//DatosPropios
		if (datosPropiosSel != null && datosPropiosSel.size() > 0) {
			formulario.setDatosPropiosFlag(true);
		}
		
		/*INI-TRA042*/
		if (convocatoria.getOcultarDatosPropios() != null && convocatoria.getOcultarDatosPropios().equals('S')) {
			formulario.setOcultarDatosPropios(true);
		}
		/*FIN-TRA042*/
		
		CamposPropiosQuery datosPropiosQuery = new CamposPropiosQuery();
		DatosPropiosConfiguracionQuery datosPropiosConfigQuery = new DatosPropiosConfiguracionQuery();
		List<DatosPropiosConfigBean> datosPropiosList = null;
		ArrayList<CamposPropiosBean> camposPropiosList = new ArrayList<CamposPropiosBean>();
		ArrayList<DatosPropiosConfigBean> camposPropiosConfList = new ArrayList<DatosPropiosConfigBean>();
		ArrayList<String> camposDisponibles = new ArrayList<String>();
		
		if (centroGestorBean != null && centroGestorBean.getModelo() != null && centroGestorBean.getModelo().getIdModelo() != null) {
			datosPropiosQuery.addModeloIn(centroGestorBean.getModelo().getIdModelo());
			
			//Se obtienen las plantilla
			ArrayList<PlantillaPropiosBean> plantillasPropias = obtenerPlantillasPropios(formulario, centroGestorBean);
			
			if (plantillasPropias != null && plantillasPropias.size() > 0) {
				for (PlantillaPropiosBean plantillaPropiosBean : plantillasPropias) {
					datosPropiosQuery.addIdIn(plantillaPropiosBean.getCampoPropioBean().getId().intValue());
				}
			}
			
			camposPropiosList = camposPropiosManager.buscarCamposPropiosbyCampo(datosPropiosQuery);
			
			for (CamposPropiosBean camposPropiosBean : camposPropiosList) {
				if (camposPropiosBean.getId() != null && camposPropiosBean.getId() > 0) {
					datosPropiosConfigQuery.addCampoIn(camposPropiosBean.getId().intValue());
				}
			}
			
			datosPropiosConfigQuery.setJoin_campo(true);
			camposPropiosConfList = datosPropiosConfiguracionManager.buscarDatosPropiosConfigbyCampo(datosPropiosConfigQuery);
		}
		
		if (camposPropiosConfList != null && camposPropiosConfList.size() > 0) {
			camposDisponibles = obtenerCamposDisponibles(camposPropiosConfList);
		}
		
		setRequestAttribute("camposDisponibles", camposDisponibles);
		
		if("alta".equals(formulario.getAccion())){
			ArrayList<DatosPropiosConfigBean> DatosPropiosEliminar;
			DatosPropiosEliminar = buscarDatosPropiosConfSeleccionados(formulario,datosPropiosList);
			for(int i=0;i<DatosPropiosEliminar.size();i++){
				logger.info(DatosPropiosEliminar.get(i));
				camposPropiosConfList.remove(DatosPropiosEliminar.get(i));
			}
			/*if(formulario.getDatosPropiosSeleccionados() != null && formulario.getDatosPropiosSeleccionados().length > 0) {
				setRequestAttribute(STRING_DATOSPROPIOSSEL, formulario.getDatosPropiosSeleccionados());
			}*/
		}else{
			
			for(int i=0;i<datosPropiosSel.size();i++){
				boolean noEncontrado=true;
				int j=0;
				
				while(j<camposPropiosConfList.size()&&noEncontrado){
					int idMotivoSel=datosPropiosSel.get(i).getIdDesplegable().intValue();
					int idMotivosList=camposPropiosConfList.get(j).getIdDesplegable().intValue();
					
					if(idMotivoSel==idMotivosList){
						noEncontrado=false;
						datosPropiosSel.get(i).setCampo(camposPropiosConfList.get(j).getCampo());
						camposPropiosConfList.remove(j);
					}else{
						j++;
					}
				}
			}
			setRequestAttribute(STRING_DATOSPROPIOSSEL, datosPropiosSel);	
		}
		setRequestAttribute("datosPropiosConfigDisp", camposPropiosConfList);			
		// FIN - Datos Propios
		
		
		String cuerpoEscala=formulario.getCuerpoEscala();
		logger.info("CuerpoEscala antes del combo especialidad: #"+cuerpoEscala+"#");
		
		if(cuerpoEscala!=null&&!(cuerpoEscala.equalsIgnoreCase(""))){
			EspecialidadQuery especialidadesQuery= new EspecialidadQuery();
			especialidadesQuery.setEstado(Constantes.ESPECIALIDAD_ESTADO_ACTIVO);
			cuerpoEscalaQuery = new CuerpoEscalaQuery();
			cuerpoEscalaQuery.setId(Integer.parseInt(cuerpoEscala));
			especialidadesQuery.setCuerpoEscala(cuerpoEscalaQuery);		
			List<EspecialidadBean> especialidadesList;
			especialidadesList = especialidadManager.buscarEspecialidadComboVisibilidad(especialidadesQuery);
			
			if("alta".equals(formulario.getAccion())){
				
					ArrayList<EspecialidadBean> especilidadesEliminar;
					especilidadesEliminar = buscarEspecialidadesSeleccionados(formulario,especialidadesList);
					
					for(int i=0;i<especilidadesEliminar.size();i++){
						logger.info(especilidadesEliminar.get(i));
						especialidadesList.remove(especilidadesEliminar.get(i));
					}

			}else{
				//Borramos los seleccionados del combo disponible
				especialidadSel = convocatoria.getEspecialidades();
				
				for(int i=0;i<especialidadSel.size();i++){
					boolean noEncontrado=true;
					int j=0;
					
					while(j<especialidadesList.size()&&noEncontrado){
						int idEspecialidadSel=especialidadSel.get(i).getId().intValue();
						int idEspecialidadesList=especialidadesList.get(j).getId().intValue();
						
						if(idEspecialidadSel==idEspecialidadesList){
							noEncontrado=false;
							especialidadesList.remove(j);
						}else{
							j++;
						}
					}
				}
				setRequestAttribute(STRING_ESPECIALIDADESSEL, especialidadSel);	
			}
			setRequestAttribute("especialidadesDisp", especialidadesList);
		}
		
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
	private ArrayList<EspecialidadBean> buscarEspecialidadesSeleccionados(CrearConvocatoriaForm formulario,	List<EspecialidadBean> especialidadesList) {

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

		setRequestAttribute(STRING_ESPECIALIDADESSEL, especialidadAux);

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
		setRequestAttribute(STRING_MOTIVOSSEL, reduccionTasasAux);
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
		ArrayList<ProvinciaExamenBean> provinciaAux = new ArrayList<ProvinciaExamenBean>();

		if(formulario.getProvinciaExamenSeleccionados() != null){
			for(int i=0;i<formulario.getProvinciaExamenSeleccionados().length;i++){
				for(int x=0;x<provinciasExamenList.size();x++){
					if(formulario.getProvinciaExamenSeleccionados()[i].equals(provinciasExamenList.get(x).getId().toString())){
						ProvinciaExamenBean aux = new ProvinciaExamenBean();
						aux.setId(provinciasExamenList.get(x).getId());
						aux.setDescripcion(provinciasExamenList.get(x).getDescripcion());
						provinciaAux.add(aux);
						auxEliminar.add(provinciasExamenList.get(x));
					}
				}		
			}
		}
		setRequestAttribute(STRING_PROVINCIASSEL, provinciaAux);
		return auxEliminar;
	}

	/**
	 * Buscar titulos seleccionados.
	 *
	 * @param formulario el formulario
	 * @param tituloOficialList el titulo oficial list
	 * @return el array list
	 */
	private ArrayList<TituloOficialBean> buscarTitulosSeleccionados(
			CrearConvocatoriaForm formulario,
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
		setRequestAttribute(STRING_TITULOSSEL, tituloOficialAux);
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
			for (String otroTituloSel : formulario.getDiscapacidadSeleccionados()) {
				for (DiscapacidadBean otroTituloBean : discapacidadList) {
					if(otroTituloSel.equals(otroTituloBean.getId().toString())){
						DiscapacidadBean aux = new DiscapacidadBean();
						aux.setId(otroTituloBean.getId());
						aux.setDescripcion(otroTituloBean.getDescripcion());
						discapacidadAux.add(aux);
						auxEliminar.add(otroTituloBean);
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
		
		if(formulario.getDatosPropiosSeleccionados() != null && datosPropiosList != null){
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
	 * Check rol usuario.
	 *
	 * @param form el form
	 */
	/*INI-TRA042*/
	public void checkRolUsuario(CrearConvocatoriaForm form){
		UsuarioBean usuario = (UsuarioBean) getRequest().getSession().getAttribute("usuario");
		String username = usuario.getLogin();

		UsuarioQuery usuarioQuery = new UsuarioQuery();
		usuarioQuery.setLogin(username);
		UsuarioBean usuarioBean = usuarioManager.buscarUsuario(usuarioQuery);
		logger.info("Perfil usuario: "+usuarioBean.getIdPerfil());
		setRequestAttribute("rol", usuarioBean.getIdPerfil());
		
		String sPerfilUsuario = comprobarPerfilUsuario(usuarioBean);
		List<CentroGestorBean> listaCentrosGestores = new ArrayList<CentroGestorBean>();

		if(sPerfilUsuario.equalsIgnoreCase(Constantes.ROL_GESTOR))
		{
			listaCentrosGestores = usuarioCentrogestorManager.buscarCentrosGestoresByIdUsuario(usuarioBean.getId());
			form.setListaCentrosGestores(listaCentrosGestores);
			setRequestAttribute("listaCentrosGestores", listaCentrosGestores);
		}
	}

	/**
	 * Comprobar perfil usuario.
	 *
	 * @param usuarioBean2 el id usuario
	 * @return el string
	 */
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
	
	/*INI-TRA042*/
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
		this.getSession().removeAttribute("errorTitulosSeleccionados");
		this.getSession().removeAttribute("errorFechaBoe");
		this.getSession().removeAttribute("errorFechaInicio");
		this.getSession().removeAttribute("errorFechaFin");
		this.getSession().removeAttribute("org.apache.spring.ERROR");
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
	public void setConvocatoriasManager(ConvocatoriasManager convocatoriasManager) {
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
	public void setTituloOficialManager(TituloOficialManager tituloOficialManager) {
		this.tituloOficialManager = tituloOficialManager;
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
