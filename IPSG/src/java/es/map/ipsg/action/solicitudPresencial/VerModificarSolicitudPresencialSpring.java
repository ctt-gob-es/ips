package es.map.ipsg.action.solicitudPresencial;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;

import es.map.ips.common.model.OrderType;
import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.CamposPropiosQuery;
import es.map.ips.model.ComunidadesQuery;
import es.map.ips.model.Convocatoria;
import es.map.ips.model.ConvocatoriaQuery;
import es.map.ips.model.EntidadFinancieraQuery;
import es.map.ips.model.EspecialidadQuery;
import es.map.ips.model.ModeloQuery;
import es.map.ips.model.MotivoReduccionTasa;
import es.map.ips.model.MotivoReduccionTasaQuery;
import es.map.ips.model.PagoSolicitudQuery;
import es.map.ips.model.PaisQuery;
import es.map.ips.model.PlantillaQuery;
import es.map.ips.model.ProvinciaExamenQuery;
import es.map.ips.model.ProvinciaQuery;
import es.map.ips.model.RegistroSolicitudQuery;
import es.map.ips.model.SolicitudCcaaQuery;
import es.map.ips.model.SolicitudComunQuery;
import es.map.ips.model.SolicitudPropiosQuery;
import es.map.ips.model.TipoDiscapacidadQuery;
import es.map.ips.model.TipoPagoQuery;
import es.map.ips.model.TituloOficialQuery;
import es.map.ipsg.bean.CamposPropiosBean;
import es.map.ipsg.bean.ComunidadesBean;
import es.map.ipsg.bean.ConvocatoriasBean;
import es.map.ipsg.bean.EntidadFinancieraBean;
import es.map.ipsg.bean.EspecialidadBean;
import es.map.ipsg.bean.MotivoExencionTasaBean;
import es.map.ipsg.bean.MotivoReduccionTasaBean;
import es.map.ipsg.bean.PagoSolicitudBean;
import es.map.ipsg.bean.PaisBean;
import es.map.ipsg.bean.PlantillaBean;
import es.map.ipsg.bean.ProvinciaBean;
import es.map.ipsg.bean.ProvinciaExamenBean;
import es.map.ipsg.bean.RegistroSolicitudBean;
import es.map.ipsg.bean.SolicitudBean;
import es.map.ipsg.bean.SolicitudCcaaBean;
import es.map.ipsg.bean.SolicitudPropiosBean;
import es.map.ipsg.bean.TipoDiscapacidadBean;
import es.map.ipsg.bean.TipoPagoBean;
import es.map.ipsg.bean.TituloOficialBean;
import es.map.ipsg.form.AltaSolicitudPresencialForm;
import es.map.ipsg.manager.CamposPropiosManager;
import es.map.ipsg.manager.CentroGestorManager;
import es.map.ipsg.manager.ComunidadesManager;
import es.map.ipsg.manager.ConvocatoriasManager;
import es.map.ipsg.manager.EntidadFinancieraManager;
import es.map.ipsg.manager.EspecialidadManager;
import es.map.ipsg.manager.MotivoReduccionTasaManager;
import es.map.ipsg.manager.PagoSolicitudManager;
import es.map.ipsg.manager.PaisManager;
import es.map.ipsg.manager.PlantillaManager;
import es.map.ipsg.manager.ProvinciaExamenManager;
import es.map.ipsg.manager.ProvinciaManager;
import es.map.ipsg.manager.RegistroSolicitudManager;
import es.map.ipsg.manager.SolicitudCcaaManager;
import es.map.ipsg.manager.SolicitudesPropiosManager;
import es.map.ipsg.manager.SolicitudesRegistradasManager;
import es.map.ipsg.manager.TipoDiscapacidadManager;
import es.map.ipsg.manager.TipoPagoManager;
import es.map.ipsg.manager.TituloOficialManager;
import es.map.ipsg.manager.UsuarioManager;
import es.map.ipsg.util.Constantes;
import es.map.ipsg.util.UtilesIPSG;


/**
 * El Class VerModificarSolicitudPresencialSpring.
 *
 * @author amartinl
 */
public class VerModificarSolicitudPresencialSpring extends AbstractSpring<SpringForm> {

	/** el usuario manager. */
	//Declaracion de Manager
	private UsuarioManager usuarioManager;
	
	/** el centro gestor manager. */
	private CentroGestorManager centroGestorManager;
	
	/** el provincia manager. */
	private ProvinciaManager provinciaManager;
	
	/** el provincia examen manager. */
	private ProvinciaExamenManager provinciaExamenManager;
	
	/** el pais manager. */
	private PaisManager paisManager;
	
	/** el especialidad manager. */
	private EspecialidadManager especialidadManager;
	
	/** el tipo discapacidad manager. */
	private TipoDiscapacidadManager tipoDiscapacidadManager;
	
	/** el titulo oficial manager. */
	private TituloOficialManager tituloOficialManager;
	
	/** el tipo pago manager. */
	private TipoPagoManager tipoPagoManager;
	
	/** el entidad financiera manager. */
	private EntidadFinancieraManager entidadFinancieraManager;
	
	/** el solicitud manager. */
	private SolicitudesRegistradasManager solicitudManager;
	
	/** el pago solicitud manager. */
	private PagoSolicitudManager pagoSolicitudManager;
	
	/** el registro solicitud manager. */
	private RegistroSolicitudManager registroSolicitudManager;
	
	/** el convocatorias manager. */
	private ConvocatoriasManager convocatoriasManager;
	
	/** el plantilla manager. */
	private PlantillaManager plantillaManager;
	
	/** el solicitudes propios manager. */
	private SolicitudesPropiosManager solicitudesPropiosManager;
	
	/** el campo propio manager. */
	private CamposPropiosManager campoPropioManager;
	
	/** el solicitud ccaa manager. */
	private SolicitudCcaaManager solicitudCcaaManager;
	
	/** el comunidades manager. */
	private ComunidadesManager comunidadesManager;
	
	/** el motivo reduccion tasa manager. */
	private MotivoReduccionTasaManager motivoReduccionTasaManager;

	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(VerModificarSolicitudPresencialSpring.class);
	
	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
		
	/**
	 * Crea una nueva ver modificar solicitud presencial spring.
	 */
	public VerModificarSolicitudPresencialSpring() {
		try {
				setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
				setCentroGestorManager((CentroGestorManager) getBean("centrosGestoresManager"));
				setProvinciaManager((ProvinciaManager) getBean("provinciaManager"));
				setProvinciaExamenManager((ProvinciaExamenManager) getBean("provinciaExamenManager"));
				setPaisManager((PaisManager) getBean("paisManager"));
				setEspecialidadManager((EspecialidadManager) getBean("especialidadManager"));
				setTipoDiscapacidadManager((TipoDiscapacidadManager) getBean("tipoDiscapacidadManager"));
				setTituloOficialManager( (TituloOficialManager) getBean("tituloOficialManager"));
				setTipoPagoManager((TipoPagoManager) getBean("tipoPagoManager"));
				setEntidadFinancieraManager((EntidadFinancieraManager) getBean("entidadFinancieraManager"));
				setSolicitudesRegistradaManager((SolicitudesRegistradasManager) getBean("solicitudesRegistradasManager"));
				setPagoSolicitudManager((PagoSolicitudManager) getBean("pagoSolicitudManager"));
				setRegistroSolicitudManager((RegistroSolicitudManager) getBean("registroSolicitudManager"));
				setConvocatoriasManager((ConvocatoriasManager) getBean("convocatoriaManager"));
				setPlantillaManager((PlantillaManager) getBean("plantillaManager"));
				setSolicitudesPropiosManager((SolicitudesPropiosManager)getBean("solicitudesPropiosManager"));
				setCampoPropioManager((CamposPropiosManager)getBean("camposPropiosManager"));
				setSolicitudCcaaManager((SolicitudCcaaManager)getBean("solicitudCcaaManager"));
				setComunidadesManager((ComunidadesManager)getBean("comunidadesManager"));
				setMotivoReduccionTasaManager((MotivoReduccionTasaManager)getBean("motivoReduccionTasaManager"));
				
			} catch (Exception e) {
				logger.error("Error VerModificarSolicitudPresencialSpring- crear constructor",e);
		}		
	}

	/**
	 * Do execute.
	 *
	 * @param form  SpringForm
	 * @return resultado
	 * @throws Exception Exception
	 */
	protected String doExecute(SpringForm form) throws Exception {
	
		//*********** METO EL PARAMETRO QUE ME INDICA EN QUE MENU ESTOY **********	
		String menu_solicitudPresencial = RESOURCE_BUNDLE.getString("field.menu.solicPresenciales");
		this.getRequest().getSession().setAttribute("pagActiva", menu_solicitudPresencial);		
		String subMenu_SolPresen = RESOURCE_BUNDLE.getString("field.menuLateral.solicitudesPresenciales.buscar");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_SolPresen);
		//******************************************************************
		String idPagoEfectivo = String.valueOf(Constantes.TIPO_PAGO_EFECTIVO_INTEGER);
		this.getRequest().setAttribute("idTipoPagoEfectivo", idPagoEfectivo);
		
		logger.info("VerModificarSolicitudPresencial -start");
	try{
		AltaSolicitudPresencialForm formulario;
		formulario = (AltaSolicitudPresencialForm) form;
		
		limpiarCamposFomulario(formulario);
		// Cargamos los combos.
		cargarCombos();
		
		// Leemos nuestra solicitud y recuperar los valores.
		List<SolicitudBean> listSolicitudBean;		
		SolicitudComunQuery solicitudQuery;
		SolicitudCcaaQuery solicitudCcaaQuery;
		SolicitudBean solicitudBean;

		
		
		String validate = this.getRequest().getParameter("validate");
		
		// Buscamos la entidad financiera segun su id
		String idSolicitud = this.getRequest().getParameter("id");
		
		//Buscamos el valor de los campos propios de la Solicitud
		SolicitudPropiosQuery solicitudPropiosQuery = new SolicitudPropiosQuery();
		SolicitudComunQuery solicitudComunQuery = new SolicitudComunQuery();
		solicitudComunQuery.setIdSolicitud(Long.valueOf(idSolicitud));
		solicitudPropiosQuery.setSolicitudComun(solicitudComunQuery);
		ArrayList<SolicitudPropiosBean> listaSolicitudPropiosBean = 
				solicitudesPropiosManager.obtenerCamposPropiosByIdSolicitud(solicitudPropiosQuery);
		if(listaSolicitudPropiosBean!=null){
			setRequestAttribute("listaSolicitudPropiosBean",listaSolicitudPropiosBean);
		}
		
		ConvocatoriasBean convocatoriaBean;
		
		
		if(idSolicitud !=null && !idSolicitud.equals("")){
			solicitudQuery = new SolicitudComunQuery();
			solicitudCcaaQuery = new SolicitudCcaaQuery();
			solicitudQuery.setIdSolicitud(Long.valueOf(idSolicitud));	
			solicitudCcaaQuery.setSolicitudComun(solicitudQuery);	
			
			// Cargamos los datos del formulario
			listSolicitudBean = solicitudManager.buscarSolicitudAll(solicitudQuery);
			SolicitudCcaaBean solicitudCcaaBean = solicitudCcaaManager.buscarSolicitudCcaaIdSolicitud(solicitudCcaaQuery);
			
			if(listSolicitudBean!=null && listSolicitudBean.size()>0){
				// Obtenermos la solicitud buscada
				solicitudBean = listSolicitudBean.get(0);
			
				if(validate == null || !validate.equals("S")){			
						cargarCamposFormulario(formulario, solicitudBean,solicitudCcaaBean);
				}
			
				//Buscamos los campos propios del modelo
				ArrayList<CamposPropiosBean> listaCamposPropiosBean = new ArrayList<CamposPropiosBean>();
				if (Integer.toString(Constantes.ID_MODELO_790007).equals(solicitudBean.getIdModelo())){
					CamposPropiosQuery camposPropiosQuery = new CamposPropiosQuery();
					int numCamposPropios = listaSolicitudPropiosBean.size();
					if (numCamposPropios > 0){
						for (int i= 0; i < numCamposPropios; i++){
							Integer idCampoQuery = (int)(long)listaSolicitudPropiosBean.get(i).getIdCampo();
							camposPropiosQuery.setIdCampo(idCampoQuery);
							camposPropiosQuery.addOrder(CamposPropiosQuery.IDCAMPO, OrderType.ASC);
							CamposPropiosBean campoi = campoPropioManager.buscarCamposPropiosbyCampo(camposPropiosQuery).get(0);
							listaCamposPropiosBean.add(i, campoi);
						}
					}
				}else{
					CamposPropiosQuery camposPropiosQuery = new CamposPropiosQuery();
					ModeloQuery modeloQuery = new ModeloQuery();
					if(null != solicitudBean.getIdModelo()){
					modeloQuery.setIdModelo(Integer.parseInt(solicitudBean.getIdModelo()));
					camposPropiosQuery.setModelo(modeloQuery);
					camposPropiosQuery.addOrder(CamposPropiosQuery.IDCAMPO, OrderType.ASC);				
					listaCamposPropiosBean = campoPropioManager.buscarCamposPropiosbyModelo(camposPropiosQuery);	
					}
				}
				
				
			
				if(listaCamposPropiosBean!=null){
					setRequestAttribute("listaCamposPropiosBean", listaCamposPropiosBean);
				}
				
				// Cargamos los valores en el formulario que se van a mostrar en la pagina
				ConvocatoriaQuery convocatoriaQuery = new ConvocatoriaQuery();
				convocatoriaQuery.setId(solicitudBean.getIdConvocatoria());
				convocatoriaBean=convocatoriasManager.buscarConvocatoriaById(convocatoriaQuery);
				PlantillaQuery plantillaQuery = new PlantillaQuery();
				plantillaQuery.setId(convocatoriaBean.getIdPlantilla());
				PlantillaBean plantilla = plantillaManager.buscarPlantilla(plantillaQuery);
				if(listaCamposPropiosBean!=null){
					plantilla.setListaCamposPropiosBean(listaCamposPropiosBean);
				}
				if(listaSolicitudPropiosBean!=null){
					plantilla.setListaSolicitudPropiosBean(listaSolicitudPropiosBean);
				}
				
				if(plantilla != null){
					formulario.setListaCamposPropiosBean(plantilla.getListaCamposPropiosBean());
					formulario.setListaSolicitudPropiosBean(plantilla.getListaSolicitudPropiosBean());
					formulario.setIdConvocatoria(solicitudBean.getIdConvocatoria().toString());	
					formulario.setDsConvocatoria(convocatoriaBean.getCuerpoEscala());
					formulario.setObCorreoElectronico(plantilla.getCorreoElectronico());
					formulario.setObDatosA(plantilla.getDatosA());
					formulario.setObDatosB(plantilla.getDatosB());
					formulario.setObDatosC(plantilla.getDatosC());
					formulario.setObDetalleDiscapacidad(plantilla.getDetalleDiscapacidad());
					formulario.setObEspecialidad(plantilla.getEspecialidad());
					formulario.setObFechaNacimiento(plantilla.getFechaNacimiento());
					formulario.setObMunicipio(plantilla.getMunicipio());
					formulario.setObNacionalidad(plantilla.getNacionalidad());
					formulario.setObNif(plantilla.getNif());
					formulario.setObNombre(plantilla.getNombre());
					formulario.setObOtrosTitulos(plantilla.getOtrosTitulos());
					formulario.setObPais(plantilla.getPais());
					formulario.setObPorcentajeDiscapacidad(plantilla.getPorcentajeDiscapacidad());
					formulario.setObPrimerApellido(plantilla.getPrimerApellido());
					formulario.setObProvinciaExamen(plantilla.getProvinciaExamen());
					formulario.setObProvincia(plantilla.getProvincia());
					formulario.setObReservaDiscapacidad(plantilla.getReservaDiscapacidad());
					formulario.setObSegundoApellido(plantilla.getSegundoApellido());
					formulario.setObSexo(plantilla.getSexo());
					formulario.setObTelefono(plantilla.getTelefono());
					formulario.setObTipoDiscapacidad(plantilla.getTipoDiscapacidad());
					formulario.setObTitulosExigidos(plantilla.getTitulosExigidos());
					formulario.setObVia(plantilla.getVia());
					
					
					setRequestAttribute("plantilla",plantilla);
				
					form = formulario; 
	                // Obtenemos los motivos de reduccion de tasa y de exencion y lo enviamos por la request para el jsp

					if(convocatoriaBean.getMotivoReduccionTasasIncompleto() != null){
						this.setRequestAttribute("motivos", convocatoriaBean.getMotivoReduccionTasasIncompleto());
						
					}else{
						ArrayList<MotivoReduccionTasa> arrayTasas = new ArrayList<MotivoReduccionTasa>();
						this.setRequestAttribute("motivos", arrayTasas);
					}				
					ArrayList<MotivoExencionTasaBean> arrayExenBean;
					arrayExenBean=cargarMotivosExencion(convocatoriaBean.getMotivoReduccionTasasCompleto());
					
					if(arrayExenBean != null){						
						this.setRequestAttribute("motivosCompletos", arrayExenBean);

					}else{
						ArrayList<MotivoExencionTasaBean> arrayExenBean2 = new ArrayList<MotivoExencionTasaBean>();
						this.setRequestAttribute("motivosCompletos", arrayExenBean2);
					}
				}
		
			}
		}
		
		logger.info("VerModificarSolicitudPresencial -end");
		
	}catch(Exception eGenerico){
		logger.error("Error VerModificarSolicitudPresencialSpring- doExecute",eGenerico);
		this.getRequest().setAttribute("descripcionError", RESOURCE_BUNDLE.getString("field.errorGenerico"));
		return "errorGenerico";
	}	
		
		return "success";
	}

	/**
	 * Limpiar campos fomulario.
	 *
	 * @param formulario el formulario
	 */
	private void limpiarCamposFomulario(AltaSolicitudPresencialForm formulario) {
		
		//Datos Convocatoria
		formulario.setIdConvocatoria("");
		formulario.setJustificantePago("");
		formulario.setDsConvocatoria("");
		
		//Datos Personales
		formulario.setNif("");
		formulario.setNombre("");
		formulario.setPrimerApellido("");
		formulario.setSegundoApellido("");
		formulario.setFechaNacimiento("");
		formulario.setLocalidadNacimiento("");
		formulario.setNacionalidad("");
		formulario.setIdProvinciaNacimiento("");
		formulario.setSexo("");
		formulario.setEmail("");
		formulario.setCkConsentimiento(true);
		
		//Datos del Domicilio
		formulario.setCalleDomicilio("");
		formulario.setMunicipioDomicilio("");
		formulario.setIdPais("");
		formulario.setIdProvinciaDomicilio("");
		formulario.setTelefono1("");
		formulario.setTelefono2("");
		formulario.setCodigoPostal("");
				
		//Datos de la Solicitud
		formulario.setNumeroSolicitud("");
		formulario.setIdEspecialidad("");
		formulario.setIdProvinciaExamen("");
		formulario.setFechaSolicitud("");
		formulario.setEjercicio("");
		formulario.setIdTipoDiscapacidad("");
		formulario.setPorcentajeDiscapacidad("");
		formulario.setCkReservaDiscapacidad(false);
		formulario.setAdaptacionDiscapacidad("");
		
		//Datos de Titulos
		formulario.setIdTitulo("");
		formulario.setOtrosTitulos("");

				
		//Datos a Consignar
		formulario.setDatosA("");
		formulario.setDatosB("");
		formulario.setDatosC("");
				
		//Datos de Pago
		formulario.setIdTipoPago("");
		formulario.setIdMotivosEx("");
		formulario.setIdMotivosRed("");
		formulario.setComunidadDD("");
		formulario.setComunidadFN("");
		formulario.setNumeroTituloFN("");
		formulario.setFechaPago("");
		formulario.setImporte("");
		formulario.setImporteDecimal("");
		formulario.setNrcPago("");
			
		//Datos de Registro
		formulario.setIdEntidadBancaria("");
		formulario.setNumeroRegistro("");
		formulario.setFechaRegistro("");
		formulario.setOficinaRegistro("");
		
		// TODO Auto-generated method stub
		
	}

	/**
	 * Cargar campos formulario.
	 *
	 * @param formulario el formulario
	 * @param solicitudBean el solicitud bean
	 * @param solicitudCcaaBean el solicitud ccaa bean
	 */
	private void cargarCamposFormulario(AltaSolicitudPresencialForm formulario, SolicitudBean solicitudBean, SolicitudCcaaBean solicitudCcaaBean)
	{
		UtilesIPSG utilesIPSG = new UtilesIPSG();
		
		//Datos Convocatoria
		if (solicitudBean.getConvocatoria() != null && solicitudBean.getConvocatoria().getId() != null) {
			formulario.setIdConvocatoria(solicitudBean.getConvocatoria().getId().toString());
			ConvocatoriaQuery convocatoriaQuery = new ConvocatoriaQuery();
			convocatoriaQuery.setId(solicitudBean.getConvocatoria().getId());
			Convocatoria convocatorias;
			convocatorias = convocatoriasManager.buscarConvocatoria(convocatoriaQuery);
			
			ConvocatoriasBean convBean =  convocatoriasManager.buscarConvocatoriaById(convocatoriaQuery);
			
			if(convBean!=null){
				String dsConvocatoria = convBean.getCuerpoEscala()+" - "+convBean.getEjercicio();
				formulario.setDsConvocatoria(dsConvocatoria);
			}
			
			
			
			
			formulario.setEjercicio(convocatorias.getEjercicio());
			solicitudBean.setEjercicio(convocatorias.getEjercicio());
		}
		formulario.setNumeroSolicitud(solicitudBean.getNumeroSolicitud());
		
		//Datos Personales
		formulario.setNif(solicitudBean.getNif());
		formulario.setNombre(solicitudBean.getNombre());
		formulario.setPrimerApellido(solicitudBean.getPrimerApellido());
		formulario.setSegundoApellido(solicitudBean.getSegundoApellido());
		formulario.setFechaNacimiento(utilesIPSG.dateToString(solicitudBean.getFechaNacimiento()));
		formulario.setLocalidadNacimiento(solicitudBean.getLocalidadNacimiento());
		formulario.setNacionalidad(solicitudBean.getNacionalidad());
		if (solicitudBean.getProvinciaByIdProvinciaNacimiento() != null && solicitudBean.getProvinciaByIdProvinciaNacimiento().getId() != null)
		{
			formulario.setIdProvinciaNacimiento(solicitudBean.getProvinciaByIdProvinciaNacimiento().getId().toString());
		}
		if (solicitudBean.getSexo() != null)
		{
			formulario.setSexo(solicitudBean.getSexo().toString());
		}
		formulario.setEmail(solicitudBean.getEmail());
		
		formulario.setCkConsentimiento(solicitudBean.getIdConsentimiento());
		
		//Datos del Domicilio
		formulario.setCalleDomicilio(solicitudBean.getCalleDomicilio());
		formulario.setMunicipioDomicilio(solicitudBean.getMunicipioDomicilio());
		if (solicitudBean.getPais() != null && solicitudBean.getPais().getId() != null)
		{
			formulario.setIdPais(solicitudBean.getPais().getId().toString());
		}
		if (solicitudBean.getProvinciaByIdProvinciaDomicilio() != null && solicitudBean.getProvinciaByIdProvinciaDomicilio().getId() != null)
		{
			formulario.setIdProvinciaDomicilio(solicitudBean.getProvinciaByIdProvinciaDomicilio().getId().toString());
		}
		
		if(solicitudBean.getTelefono()!=null && !solicitudBean.getTelefono().equals("")){
			StringTokenizer telefonoToke = new StringTokenizer(solicitudBean.getTelefono(),"-");
		
			try{
				formulario.setTelefono1(telefonoToke.nextToken());
			
				if(telefonoToke.hasMoreTokens())
					formulario.setTelefono2(telefonoToke.nextToken());
			}catch(NoSuchElementException nse){
				
				logger.error("Error VerModificarSolicitudPresencialSpring - Error al parsear los telefonos",nse);
			}
		}	
	
		formulario.setCodigoPostal(solicitudBean.getCodigoPostalDomicilio());
		
		//Datos de la Solicitud
		if (solicitudBean.getEspecialidad() != null && solicitudBean.getEspecialidad().getId() != null)
		{
			formulario.setIdEspecialidad(solicitudBean.getEspecialidad().getId().toString());
		}
		if (solicitudBean.getProvinciaByIdProvinciaExamen() != null && solicitudBean.getProvinciaByIdProvinciaExamen().getId() != null)
		{
			formulario.setIdProvinciaExamen(solicitudBean.getProvinciaByIdProvinciaExamen().getId().toString());
		}
		formulario.setFechaSolicitud(solicitudBean.getFechaSolicitud());
		formulario.setEjercicio(solicitudBean.getEjercicio());
		if (solicitudBean.getTipoDiscapacidad() != null && solicitudBean.getTipoDiscapacidad().getId() != null)
		{
			formulario.setIdTipoDiscapacidad(solicitudBean.getTipoDiscapacidad().getId().toString());
		}
		if (solicitudBean.getPorcentajeDiscapacidad() != null)
		{
			formulario.setPorcentajeDiscapacidad(solicitudBean.getPorcentajeDiscapacidad().toString());
		}
		if (solicitudBean.getReservaDiscapacidad() != null)
		{
			formulario.setCkReservaDiscapacidad(solicitudBean.getReservaDiscapacidad() == 'S' ? true : false);
		}
		formulario.setAdaptacionDiscapacidad(solicitudBean.getDetalleDiscapacidad());
		
		//Datos de Titulos
		if (solicitudBean.getTituloOficial() != null && solicitudBean.getTituloOficial().getId() != null)
		{
			formulario.setIdTitulo(solicitudBean.getTituloOficial().getId().toString());
		}
		formulario.setOtrosTitulos(solicitudBean.getOtrosTitulos());
				
		//Datos a Consignar
		formulario.setDatosA(solicitudBean.getDatosA());
		formulario.setDatosB(solicitudBean.getDatosB());
		formulario.setDatosC(solicitudBean.getDatosC());
				
		//Datos de Pago
		PagoSolicitudQuery pagoSolicitudQuery = new PagoSolicitudQuery();
		SolicitudComunQuery solicitudQuery = new SolicitudComunQuery();
		List<PagoSolicitudBean> listPagoSolicitudBean;
		PagoSolicitudBean pagoSolicitudBean;
		solicitudQuery.setIdSolicitud(solicitudBean.getId());
		pagoSolicitudQuery.setSolicitudComun(solicitudQuery);
		listPagoSolicitudBean = pagoSolicitudManager.buscarPagoSolicitudAll(pagoSolicitudQuery);
		if (listPagoSolicitudBean.size() > 0)
		{
			pagoSolicitudBean = listPagoSolicitudBean.get(0);
			
			// Buscamos el tipo de pago
			List<TipoPagoBean> listTipoPagoBean;
			TipoPagoQuery tipoPagoQuery = new TipoPagoQuery();			
			tipoPagoQuery.setCodigo(pagoSolicitudBean.getTipo());
			
			listTipoPagoBean = tipoPagoManager.buscarTipoPagoCombo(tipoPagoQuery);
			formulario.setIdPagoSolicitud(listPagoSolicitudBean.get(0).getId().toString());
			
			formulario.setImporte(pagoSolicitudBean.getImporte().toString());
			if((("0.0".equals(formulario.getImporte()))
					||("0.".equals(formulario.getImporte()))
					||(".0".equals(formulario.getImporte())))&& pagoSolicitudBean.getTipo().equals(Constantes.TIPO_PAGO_EFECTIVO_CODIGO)){
				formulario.setIdTipoPago(Constantes.TIPO_EXENTO_INTEGER.toString());
				if(pagoSolicitudBean.getMotivoReduccionTasa()!=null)
				formulario.setIdMotivosEx(pagoSolicitudBean.getMotivoReduccionTasa().getId().toString());	
				formulario.setFechaPago("");
			}else{
				formulario.setIdTipoPago(listTipoPagoBean.get(0).getId().toString());
				formulario.setFechaPago(utilesIPSG.dateToString(pagoSolicitudBean.getFechaIntento()));
				if((pagoSolicitudBean.getMotivoReduccionTasa()) != null){
				formulario.setIdMotivosRed(pagoSolicitudBean.getMotivoReduccionTasa().getId().toString());
				}
			}
			formulario.setNrcPago(pagoSolicitudBean.getNrc());
			if (pagoSolicitudBean.getEntidadFinanciera() != null)
			{
				formulario.setIdEntidadBancaria(pagoSolicitudBean.getEntidadFinanciera().getId().toString());
			}
			
			//Datos de Comunidad Autonoma
			
			if(solicitudCcaaBean !=null){
				
				
				
				if (solicitudCcaaBean.getComunidad() != null && solicitudCcaaBean.getComunidad().getIdComunidad() != null && (Constantes.MOTIVO_DISCAPACIDAD).equals(pagoSolicitudBean.getMotivoReduccionTasa().getId().toString()))
				{
					formulario.setComunidadDD(solicitudCcaaBean.getComunidad().getIdComunidad().toString());
					
				}
				
				else if (solicitudCcaaBean.getComunidad() != null && solicitudCcaaBean.getComunidad().getIdComunidad() != null && (((Constantes.MOTIVO_FNUMEROSAESPECIAL).equals(pagoSolicitudBean.getMotivoReduccionTasa().getId().toString())) || ((Constantes.MOTIVO_FNUMEROSAGENERAL).equals(pagoSolicitudBean.getMotivoReduccionTasa().getId().toString()))))
				{
					formulario.setComunidadFN(solicitudCcaaBean.getComunidad().getIdComunidad().toString());
				}
				
				if(solicitudCcaaBean.getTituloFamnumerosa() != null){
					formulario.setNumeroTituloFN(solicitudCcaaBean.getTituloFamnumerosa());
				}
			
			}
			
			
			
		}
		else
		{
			formulario.setIdPagoSolicitud("");
		}	
		

		// Datos de Registro
		// Recuperamos los datos del Registro de Solicitud para anyadirlos al formulario
		RegistroSolicitudQuery registroSolicitudQuery = new RegistroSolicitudQuery();
		List<RegistroSolicitudBean> listRegistroSolicitudBean;
		RegistroSolicitudBean registroSolicitudBean;
		
		solicitudQuery.setIdSolicitud(solicitudBean.getId());
		registroSolicitudQuery.setSolicitudComun(solicitudQuery);
		listRegistroSolicitudBean = registroSolicitudManager.buscarRegistroSolicitudAll(registroSolicitudQuery);
		
		if (listRegistroSolicitudBean.size() > 0) {
		
			registroSolicitudBean = listRegistroSolicitudBean.get(0);
			
			formulario.setIdRegistroSolicitud(registroSolicitudBean.getId().toString());
			formulario.setNumeroRegistro(registroSolicitudBean.getNumeroRegistro());
			formulario.setFechaRegistro(utilesIPSG.dateToString(registroSolicitudBean.getFechaRegistro()));
			formulario.setOficinaRegistro(registroSolicitudBean.getOficinaRegistro());
		}
		else
		{
			formulario.setIdRegistroSolicitud("");
		}	
		
	}
	
	/**
	 * Cargar combos.
	 */
	private void cargarCombos ()
	{
		ProvinciaQuery provinciaQuery = new ProvinciaQuery();
		ProvinciaExamenQuery provinciaExamenQuery = new ProvinciaExamenQuery();
		ArrayList<ProvinciaBean> lProvinciaBean;
		ArrayList<ProvinciaExamenBean> lProvinciaExamenBean;
		provinciaQuery.setEstado(Constantes.PROVINCIA_ESTADO_ACTIVO);
		provinciaExamenQuery.setEstado(Constantes.PROVINCIA_ESTADO_ACTIVO);
		lProvinciaBean = provinciaManager.buscarProvinciaCombo(provinciaQuery);
		lProvinciaExamenBean = provinciaExamenManager.buscarProvinciaExamenCombo(provinciaExamenQuery);
		
		PaisQuery paisQuery = new PaisQuery();
		paisQuery.addOrder(PaisQuery.DESCRIPCION, OrderType.ASC);
		ArrayList<PaisBean> lPaisBean;
		lPaisBean = paisManager.buscarPaisCombo2(paisQuery);
		
		EspecialidadQuery especialidadQuery = new EspecialidadQuery();
		ArrayList<EspecialidadBean> lEspecialidadBean;
		especialidadQuery.setEstado(Constantes.ESPECIALIDAD_ESTADO_ACTIVO);
		especialidadQuery.addOrder(EspecialidadQuery.DESCRIPCION, OrderType.ASC);
		lEspecialidadBean = especialidadManager.buscarEspecialidadCombo(especialidadQuery);
		
		TipoDiscapacidadQuery tipoDiscapacidadQuery = new TipoDiscapacidadQuery();
		ArrayList<TipoDiscapacidadBean> lTipoDiscapacidadBean;
		lTipoDiscapacidadBean = tipoDiscapacidadManager.buscarTipoDiscapacidadCombo(tipoDiscapacidadQuery);
		
		TituloOficialQuery tituloOficialQuery = new TituloOficialQuery();
		ArrayList<TituloOficialBean> lTituloOficialBean;
		tituloOficialQuery.setEstado(Constantes.TITULOOFICIAL_ESTADO_ACTIVO);
		tituloOficialQuery.addOrder(TituloOficialQuery.DESCRIPCION, OrderType.ASC);
		lTituloOficialBean = tituloOficialManager.buscarTituloOficialCombo(tituloOficialQuery);
		 
		TipoPagoQuery tipoPagoQuery = new TipoPagoQuery();
		tipoPagoQuery.addIdIn(Constantes.TIPO_PAGO_CUENTA_INTEGER);
		tipoPagoQuery.addIdIn(Constantes.TIPO_PAGO_TARJETA_INTEGER);
		tipoPagoQuery.addIdIn(Constantes.TIPO_PAGO_EFECTIVO_INTEGER);
		
		ArrayList<TipoPagoBean> lTipoPagoBean;
		lTipoPagoBean = tipoPagoManager.buscarTipoPagoCombo(tipoPagoQuery);
		// Peticion incluir Tipo de pago
		TipoPagoBean tipoPagoExento = new TipoPagoBean();
		tipoPagoExento.setId(0);
		tipoPagoExento.setDescripcion("EXENTO");
		tipoPagoExento.setCodigo(Constantes.TIPO_PAGO_EXENTO_CODIGO);
		lTipoPagoBean.add(tipoPagoExento);
		setRequestAttribute("tipoPagos", lTipoPagoBean);
		
		ArrayList<ComunidadesBean> comunidadesFN;
		ComunidadesQuery comunidadesQueryFN = new ComunidadesQuery();
		comunidadesQueryFN.setServicioFamnumerosa(true);
		comunidadesFN = comunidadesManager.buscarComunidadesCombo(comunidadesQueryFN);
		setRequestAttribute("listcomunidadesFN", comunidadesFN);
		
		// Pasamos al formulario la lista de comunidades autonomas que requieren el numero de titulo familia numerosa		
		ArrayList<ComunidadesBean> comunidadesReqTitulo;
		ComunidadesQuery comunidadesQueryReqTitulo = new ComunidadesQuery();
		comunidadesQueryReqTitulo.setTituloRequerido(true);
		comunidadesReqTitulo = comunidadesManager.buscarComunidadesCombo(comunidadesQueryReqTitulo);

		String comuReqTitulo="##";
		for (ComunidadesBean comunidad : comunidadesReqTitulo) {
			comuReqTitulo=comuReqTitulo+comunidad.getIdComunidad()+"##";
		} 
		this.setRequestAttribute("comunidadesReqTitulo", comuReqTitulo);

		ArrayList<ComunidadesBean> comunidadesDiscapacidad;
		ComunidadesQuery comunidadesQueryDD = new ComunidadesQuery();
		comunidadesQueryDD.setServicioDiscapacidad(true);
		comunidadesDiscapacidad = comunidadesManager.buscarComunidadesCombo(comunidadesQueryDD);
		setRequestAttribute("listcomunidadesDiscapacidad", comunidadesDiscapacidad);
				
		EntidadFinancieraQuery entidadFinancieraQuery = new EntidadFinancieraQuery();
		ArrayList<EntidadFinancieraBean> lEntidadFinancieraBean;
		entidadFinancieraQuery.setEstado(Constantes.ENTIDAD_FINANCIERA_ESTADO_ACTIVO);
		paisQuery.addOrder(EntidadFinancieraQuery.DESCRIPCION, OrderType.ASC);
		lEntidadFinancieraBean = entidadFinancieraManager.buscarEntidadFinancieraCombo(entidadFinancieraQuery);
		
		setRequestAttribute("entidadesBancarias", lEntidadFinancieraBean);
		setRequestAttribute("tipoPagos", lTipoPagoBean);
		setRequestAttribute("titulos", lTituloOficialBean);
		setRequestAttribute("tipoDiscapacidades", lTipoDiscapacidadBean);
		setRequestAttribute("especialidades", lEspecialidadBean);
		setRequestAttribute("provincias", lProvinciaBean);
		setRequestAttribute("provinciasExamen", lProvinciaExamenBean);
		setRequestAttribute("paises", lPaisBean);
		
	}
	
	/**
	 * Metodo que transforma una lista que contiene los motivos de reduccion completos en una lista de motivos de exencion.
	 *
	 * @param motivoReduccionTasasCompleto el motivo reduccion tasas completo
	 * @return ArrayList<MotivoExencionTasaBean>
	 */
	/**
	 * Metodo que transforma una lista que contiene los motivos de reduccion completos en una lista de motivos de exencion
	 * @param motivoReduccionTasasCompleto
	 * @return ArrayList<MotivoExencionTasaBean>
	 */
	private ArrayList<MotivoExencionTasaBean> cargarMotivosExencion(ArrayList<MotivoReduccionTasa> motivoReduccionTasasCompleto) {
	
		ArrayList<MotivoExencionTasaBean> arrayExenBean = new ArrayList<MotivoExencionTasaBean>();
		Boolean tieneDiscapacidad=false;
		if(motivoReduccionTasasCompleto != null){
			for(int i=0;i<motivoReduccionTasasCompleto.size();i++){
				if(motivoReduccionTasasCompleto.get(i).getId().toString().equals(Constantes.MOTIVO_DISCAPACIDAD)){
					tieneDiscapacidad=true;
				}
				MotivoExencionTasaBean motivoExencionTasaBean= toMotivoExencionTasaBean(motivoReduccionTasasCompleto.get(i));
				arrayExenBean.add(motivoExencionTasaBean);			
				}
		}
		//Si no se encuentra la discapacidad en los motivos de exencion en la convocatoria o no tiene motivos de exencion lo volcamos por defecto
		if(motivoReduccionTasasCompleto == null || tieneDiscapacidad==false){
			MotivoReduccionTasaQuery motivoReduccionTasaQuery = new MotivoReduccionTasaQuery();
			motivoReduccionTasaQuery.setId(Integer.valueOf(Constantes.MOTIVO_DISCAPACIDAD));
			ArrayList<MotivoReduccionTasaBean> arrayMotivoTasaBean = motivoReduccionTasaManager.buscarMotivoReduccionTasaAll(motivoReduccionTasaQuery);
			MotivoReduccionTasa motivoBean=motivoReduccionTasaManager.toMotivoReduccionTasa(arrayMotivoTasaBean.get(0));
			MotivoExencionTasaBean motivoExencionTasaBean= toMotivoExencionTasaBean(motivoBean);
			arrayExenBean.add(motivoExencionTasaBean);
			
		}
						
		return arrayExenBean;
	
}

	/**
	 * To motivo exencion tasa bean.
	 *
	 * @param motivoBean el motivo bean
	 * @return el motivo exencion tasa bean
	 */
	private MotivoExencionTasaBean toMotivoExencionTasaBean(MotivoReduccionTasa motivoBean) {
		
		MotivoExencionTasaBean motivoExencionTasaBean =new MotivoExencionTasaBean();
		motivoExencionTasaBean.setIdMotivoEx(motivoBean.getId());
		motivoExencionTasaBean.setDescripcionMoEx(motivoBean.getDescripcion());
		motivoExencionTasaBean.setTextoExplicativo(motivoBean.getTextoExplicativo());
		motivoExencionTasaBean.setPorcentajeDescuento(motivoBean.getPorcentajeDescuento());
		motivoExencionTasaBean.setEstadoMoEx(motivoBean.getEstado());
		motivoExencionTasaBean.setCodigoMoEx(motivoBean.getCodigo());
		motivoExencionTasaBean.setConvocatoriasMoEx(motivoBean.getConvocatorias());
		motivoExencionTasaBean.setPagoSolicitudsMoEx(motivoBean.getPagoSolicituds());
		
		return motivoExencionTasaBean;
	}
	
	/**
	 * Obtiene el usuario manager.
	 *
	 * @return usuarioManager UsuarioManager
	 */
	public UsuarioManager getUsuarioManager() {
		return usuarioManager;
	}

	/**
	 * Establece el usuario manager.
	 *
	 * @param usuarioManager UsuarioManager
	 */
	public void setUsuarioManager(UsuarioManager usuarioManager) {
		this.usuarioManager = usuarioManager;
	}

	/**
	 * Obtiene el centro gestor manager.
	 *
	 * @return centroGestorManager CentroGestorManager
	 */
	public CentroGestorManager getCentroGestorManager() {
		return centroGestorManager;
	}

	/**
	 * Establece el centro gestor manager.
	 *
	 * @param centroGestorManager CentroGestorManager
	 */
	public void setCentroGestorManager(CentroGestorManager centroGestorManager) {
		this.centroGestorManager = centroGestorManager;
	}

	/**
	 * Obtiene el provincia manager.
	 *
	 * @return provinciaManager ProvinciaManager
	 */
	public ProvinciaManager getProvinciaManager() {
		return provinciaManager;
	}

	/**
	 * Establece el provincia manager.
	 *
	 * @param provinciaManager ProvinciaManager
	 */
	public void setProvinciaManager(ProvinciaManager provinciaManager) {
		this.provinciaManager = provinciaManager;
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
	 * Obtiene el pais manager.
	 *
	 * @return paisManager PaisManager
	 */
	public PaisManager getPaisManager() {
		return paisManager;
	}

	/**
	 * Establece el pais manager.
	 *
	 * @param paisManager PaisManager
	 */
	public void setPaisManager(PaisManager paisManager) {
		this.paisManager = paisManager;
	}

	/**
	 * Obtiene el especialidad manager.
	 *
	 * @return especialidadManager EspecialidadManager
	 */
	public EspecialidadManager getEspecialidadManager() {
		return especialidadManager;
	}

	/**
	 * Establece el especialidad manager.
	 *
	 * @param especialidadManager EspecialidadManager
	 */
	public void setEspecialidadManager(EspecialidadManager especialidadManager) {
		this.especialidadManager = especialidadManager;
	}

	/**
	 * Obtiene el tipo discapacidad manager.
	 *
	 * @return tipoDiscapacidadManager TipoDiscapacidadManager
	 */
	public TipoDiscapacidadManager getTipoDiscapacidadManager() {
		return tipoDiscapacidadManager;
	}

	/**
	 * Establece el tipo discapacidad manager.
	 *
	 * @param tipoDiscapacidadManager TipoDiscapacidadManager
	 */
	public void setTipoDiscapacidadManager(
			TipoDiscapacidadManager tipoDiscapacidadManager) {
		this.tipoDiscapacidadManager = tipoDiscapacidadManager;
	}

	/**
	 * Obtiene el titulo oficial manager.
	 *
	 * @return tituloOficialManager TituloOficialManager
	 */
	public TituloOficialManager getTituloOficialManager() {
		return tituloOficialManager;
	}

	/**
	 * Establece el titulo oficial manager.
	 *
	 * @param tituloOficialManager TituloOficialManager
	 */
	public void setTituloOficialManager(TituloOficialManager tituloOficialManager) {
		this.tituloOficialManager = tituloOficialManager;
	}

	/**
	 * Obtiene el tipo pago manager.
	 *
	 * @return tipoPagoManager TipoPagoManager
	 */
	public TipoPagoManager getTipoPagoManager() {
		return tipoPagoManager;
	}

	/**
	 * Establece el tipo pago manager.
	 *
	 * @param tipoPagoManager TipoPagoManager
	 */
	public void setTipoPagoManager(TipoPagoManager tipoPagoManager) {
		this.tipoPagoManager = tipoPagoManager;
	}

	/**
	 * Obtiene el entidad financiera manager.
	 *
	 * @return entidadFinancieraManager EntidadFinancieraManager
	 */
	public EntidadFinancieraManager getEntidadFinancieraManager() {
		return entidadFinancieraManager;
	}

	/**
	 * Establece el entidad financiera manager.
	 *
	 * @param entidadFinancieraManager EntidadFinancieraManager
	 */
	public void setEntidadFinancieraManager(EntidadFinancieraManager entidadFinancieraManager) {
		this.entidadFinancieraManager = entidadFinancieraManager;
	}
	
	/**
	 * Obtiene el solicitudes registradas manager.
	 *
	 * @return solicitudManager SolicitudesRegistradasManager
	 */
	public SolicitudesRegistradasManager getSolicitudesRegistradasManager() {
		return solicitudManager;
	}

	/**
	 * Establece el solicitudes registrada manager.
	 *
	 * @param solicitudManager SolicitudesRegistradasManager
	 */
	public void setSolicitudesRegistradaManager(SolicitudesRegistradasManager solicitudManager) {
		this.solicitudManager = solicitudManager;
	}
	
	/**
	 * Obtiene el pago solicitud manager.
	 *
	 * @return pagoSolicitudManager PagoSolicitudManager
	 */
	public PagoSolicitudManager getPagoSolicitudManager() {
		return pagoSolicitudManager;
	}

	/**
	 * Establece el pago solicitud manager.
	 *
	 * @param pagoSolicitudManager PagoSolicitudManager
	 */
	public void setPagoSolicitudManager(PagoSolicitudManager pagoSolicitudManager) {
		this.pagoSolicitudManager = pagoSolicitudManager;
	}
	
	/**
	 * Obtiene el registro solicitud manager.
	 *
	 * @return registroSolicitudManager PagoSolicitudManager
	 */
	public RegistroSolicitudManager getRegistroSolicitudManager() {
		return registroSolicitudManager;
	}

	/**
	 * Establece el registro solicitud manager.
	 *
	 * @param registroSolicitudManager RegistroSolicitudManager
	 */
	public void setRegistroSolicitudManager(RegistroSolicitudManager registroSolicitudManager) {
		this.registroSolicitudManager = registroSolicitudManager;
	}
	
	/**
	 * Obtiene el convocatorias manager.
	 *
	 * @return convocatoriasManager ConvocatoriasManager
	 */
	public ConvocatoriasManager getConvocatoriasManager() {
		return convocatoriasManager;
	}

	/**
	 * Establece el convocatorias manager.
	 *
	 * @param convocatoriasManager ConvocatoriasManager
	 */
	public void setConvocatoriasManager(ConvocatoriasManager convocatoriasManager) {
		this.convocatoriasManager = convocatoriasManager;
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
	 * Obtiene el solicitudes propios manager.
	 *
	 * @return el solicitudes propios manager
	 */
	public SolicitudesPropiosManager getSolicitudesPropiosManager() {
		return solicitudesPropiosManager;
	}

	/**
	 * Establece el solicitudes propios manager.
	 *
	 * @param solicitudesPropiosManager el nuevo solicitudes propios manager
	 */
	public void setSolicitudesPropiosManager(
			SolicitudesPropiosManager solicitudesPropiosManager) {
		this.solicitudesPropiosManager = solicitudesPropiosManager;
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
	 * Obtiene el solicitud ccaa manager.
	 *
	 * @return el solicitud ccaa manager
	 */
	public SolicitudCcaaManager getSolicitudCcaaManager() {
		return solicitudCcaaManager;
	}

	/**
	 * Establece el solicitud ccaa manager.
	 *
	 * @param solicitudCcaaManager el nuevo solicitud ccaa manager
	 */
	public void setSolicitudCcaaManager(
			SolicitudCcaaManager solicitudCcaaManager) {
		this.solicitudCcaaManager = solicitudCcaaManager;
	}

	/**
	 * Obtiene el comunidades manager.
	 *
	 * @return el comunidades manager
	 */
	public ComunidadesManager getComunidadesManager() {
		return comunidadesManager;
	}

	/**
	 * Establece el comunidades manager.
	 *
	 * @param comunidadesManager el nuevo comunidades manager
	 */
	public void setComunidadesManager(ComunidadesManager comunidadesManager) {
		this.comunidadesManager = comunidadesManager;
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
	
}
