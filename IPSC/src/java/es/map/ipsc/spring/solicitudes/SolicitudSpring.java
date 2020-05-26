package es.map.ipsc.spring.solicitudes;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringMessage;
import es.map.ips.common.spring.SpringMessages;
import es.map.ips.model.ConvocatoriaQuery;
import es.map.ips.model.Especialidad;
import es.map.ips.model.EstadoSolicitudQuery;
import es.map.ips.model.FuncionarioHabilitado;
import es.map.ips.model.FuncionarioHabilitadoQuery;
import es.map.ips.model.Modelo;
import es.map.ips.model.Pais;
import es.map.ips.model.ParametrosConfiguracionQuery;
import es.map.ips.model.PlantillaQuery;
import es.map.ips.model.Provincia;
import es.map.ips.model.ProvinciaExamen;
import es.map.ips.model.ProvinciaQuery;
import es.map.ips.model.SolicitudComunQuery;
import es.map.ips.model.TipoDiscapacidad;
import es.map.ips.model.TipoSolicitud;
import es.map.ips.model.TituloOficial;
import es.map.ipsc.Constantes;
import es.map.ipsc.bean.AltaSolicitudBean;
import es.map.ipsc.bean.AuditoriaBean;
import es.map.ipsc.bean.CiudadanoBean;
import es.map.ipsc.bean.ConvocatoriaBean;
import es.map.ipsc.bean.LogSolicitudBean;
import es.map.ipsc.bean.PagoSolicitudBean;
import es.map.ipsc.bean.ParametrosConfiguracionBean;
import es.map.ipsc.bean.PlantillaBean;
import es.map.ipsc.bean.PlantillaPropiosBean;
import es.map.ipsc.bean.ProvinciaBean;
import es.map.ipsc.bean.SolicitudBean;
import es.map.ipsc.bean.SolicitudCcaaBean;
import es.map.ipsc.form.PagoSolicitudForm;
import es.map.ipsc.form.SolicitudForm;
import es.map.ipsc.manager.convocatorias.ConvocatoriasManager;
import es.map.ipsc.manager.funcionarioHabilitado.FuncionarioHabilitadoManager;
import es.map.ipsc.manager.plantilla.PlantillaManager;
import es.map.ipsc.manager.provincia.ProvinciaManager;
import es.map.ipsc.manager.registroAuditoria.RegistroAuditoriaManager;
import es.map.ipsc.manager.solicitudes.EstadoSolicitudManager;
import es.map.ipsc.manager.solicitudes.PagoSolicitudManager;
import es.map.ipsc.manager.solicitudes.SolicitudCcaaManager;
import es.map.ipsc.manager.solicitudes.SolicitudManager;
import es.map.ipsc.manager.solicitudes.SolicitudPropioManager;
import es.map.ipsc.manager.tablaBase.ParametroConfiguracionManager;
import es.map.ipsc.manager.tablaBase.TablaBaseManager;
import es.map.ipsc.spring.AbstractSecureSpring;
import es.map.ipsc.utils.Util;
import es.map.ipsc.utils.Validacion;

/**
 * El Class SolicitudSpring.
 */
public class SolicitudSpring extends AbstractSecureSpring {

	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(SolicitudSpring.class);
	
	/** La constante BUNDLE_ERROR. */
	private static final String BUNDLE_ERROR = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_ERROR);
	
	/** La constante STRING_PLANTILLA. */
	private static final String STRING_PLANTILLA = "plantilla";
	
	/** La constante STRING_ERROR_DESCRIPCION. */
	private static final String STRING_ERROR_DESCRIPCION = "errorDescripcion";
	
	/** La constante STRING_ERROR_USUARIO. */
	private static final String STRING_ERROR_USUARIO = "errorUsuario";
	
	/** La constante STRING_ERROR. */
	private static final String STRING_ERROR = "Error";
	
	/** La constante STRING_FIELD_SOLICITUD_ALTA_VALIDATE_ERRORNIF. */
	private static final String STRING_FIELD_SOLICITUD_ALTA_VALIDATE_ERRORNIF = "field.solicitud.alta.validate.errorNif";
	
	/** La constante STRING_FIELD_SOLICITUD_ALTA_VALIDATE_FECHANACIMIENTO. */
	private static final String STRING_FIELD_SOLICITUD_ALTA_VALIDATE_FECHANACIMIENTO = "field.solicitud.alta.validate.fechaNacimiento";
	
	/** La constante STRING_DS_ERROR_FECHA_NACIMIENTO. */
	private static final String STRING_DS_ERROR_FECHA_NACIMIENTO = "dsErrorFechaNacimiento";
	
	/** La constante STRING_DS_ERROR_NACIONALIDAD. */
	private static final String STRING_DS_ERROR_NACIONALIDAD = "dsErrorNacionalidad";
	
	/** La constante STRING_DS_ERROR_TELEFONO. */
	private static final String STRING_DS_ERROR_TELEFONO = "dsErrorTelefono";
	
	/** La constante STRING_DS_ERROR_PROVINCIA_DOMICILIO_FORMATO. */
	private static final String STRING_DS_ERROR_PROVINCIA_DOMICILIO_FORMATO = "dsErrorProvinciaDomicilioFormato";
	
	/** La constante STRING_FIELD_SOLICITUD_ALTA_VALIDATE_ERRORPROVINCIA. */
	private static final String STRING_FIELD_SOLICITUD_ALTA_VALIDATE_ERRORPROVINCIA = "field.solicitud.alta.validate.errorProvincia";
	
	/** La constante STRING_FIELD_SOLICITUD_ALTA_ERRORFORMATOPORCENTAJEDISCAPACIDAD. */
	private static final String STRING_FIELD_SOLICITUD_ALTA_ERRORFORMATOPORCENTAJEDISCAPACIDAD = "field.solicitud.alta.errorFormatoPorcentajeDiscapacidad";
	
	/** La constante STRING_DS_ERROR_PORCENTAJE. */
	private static final String STRING_DS_ERROR_PORCENTAJE = "dsErrorPorcentaje";
	

	/** el solicitudes manager. */
	private SolicitudManager solicitudesManager;
	
	/** el convocatoria manager. */
	private ConvocatoriasManager convocatoriaManager;
	
	/** el plantilla manager. */
	private PlantillaManager plantillaManager;
	
	/** el estado solicitud manager. */
	private EstadoSolicitudManager estadoSolicitudManager;
	
	/** el tabla base manager. */
	private TablaBaseManager tablaBaseManager;
	
	/** el parametro configuracion manager. */
	private ParametroConfiguracionManager parametroConfiguracionManager;
	
	/** el pago solicitudes manager. */
	private PagoSolicitudManager pagoSolicitudesManager;
	
	/** el provincia manager. */
	private ProvinciaManager provinciaManager;
	
	/** el solicitud propio manager. */
	private SolicitudPropioManager solicitudPropioManager;
	
	/** el registro auditoria manager. */
	private RegistroAuditoriaManager registroAuditoriaManager;
	
	/** el funcionario habilitado manager. */
	private FuncionarioHabilitadoManager funcionarioHabilitadoManager;
	
	/** el solicitud ccaa manager. */
	private SolicitudCcaaManager solicitudCcaaManager;
	
	/** el properties. */
	private static Properties properties;



	/**
	 * Crea una nueva solicitud spring.
	 */
	public SolicitudSpring() {
		try{
			setSolicitudesManager((SolicitudManager) getBean("solicitudesManager"));
			setConvocatoriaManager((ConvocatoriasManager) getBean("convocatoriaManager"));
			setPlantillaManager((PlantillaManager) getBean("plantillaManager"));
			setEstadoSolicitudManager((EstadoSolicitudManager) getBean("estadoSolicitudManager"));
			setTablaBaseManager((TablaBaseManager) getBean("tablasBaseManager"));
			setParametroConfiguracionManager((ParametroConfiguracionManager) getBean("parametroConfiguracionManager"));
			setPagoSolicitudesManager((PagoSolicitudManager) getBean("pagoSolicitudesManager"));
			setProvinciaManager((ProvinciaManager) getBean("provinciaManager"));
			setSolicitudPropioManager((SolicitudPropioManager) getBean("solicitudPropioManager"));
			setRegistroAuditoriaManager((RegistroAuditoriaManager) getBean("registroAuditoriaManager"));
			setFuncionarioHabilitadoManager((FuncionarioHabilitadoManager) getBean("funcionarioHabilitadoManager"));
			setSolicitudCcaaManager((SolicitudCcaaManager) getBean("solicitudCcaaManager"));
			properties = (Properties) getBean("propertiesBean");
		}catch(Exception e){
			logger.warn(e);
			logger.error("Error  Solicitud: ",e);
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ipsc.spring.AbstractSecureSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {

		SolicitudForm solicitudForm = (SolicitudForm) form; 
		
		String action = "Guardar";

		ConvocatoriaBean convocatoriaBean = convocatoriaManager.recuperarConvocatoria(Long.parseLong(solicitudForm.getIdConvocatoria()));

		List<PlantillaPropiosBean> listaPlantilla = new ArrayList<PlantillaPropiosBean>();

		for (PlantillaPropiosBean plantilla : convocatoriaBean.getPlantillaPropios()) {
			if(this.getRequest().getParameter(STRING_PLANTILLA+plantilla.getCampoPropioBean().getId())!=null)
			{
				PlantillaPropiosBean plantillaPropio = new PlantillaPropiosBean();
				plantillaPropio.setCampoPropioBean(plantilla.getCampoPropioBean());
				plantillaPropio.setConvocatoriaBean(plantilla.getConvocatoriaBean());
				plantillaPropio.setCentroGestorBean(plantilla.getCentroGestorBean());
				plantillaPropio.setId(plantilla.getId());
				plantillaPropio.setObligatorio(plantilla.isObligatorio());
				plantillaPropio.setTipoPlantilla(plantilla.getTipoPlantilla());
				plantillaPropio.setModeloBean(plantilla.getModeloBean());	
				if(this.getRequest().getParameter("ajax")!=null)
					plantillaPropio.getCampoPropioBean().setValorVista(codificarCadenaAjax(this.getRequest().getParameter(STRING_PLANTILLA+plantilla.getCampoPropioBean().getId())));
				else
					plantillaPropio.getCampoPropioBean().setValorVista(this.getRequest().getParameter(STRING_PLANTILLA+plantilla.getCampoPropioBean().getId()));

				listaPlantilla.add(plantillaPropio);
			}
		} 
		solicitudForm.setPlantillaPropios(listaPlantilla);

		if(this.getRequest().getParameter("ajax")!=null){
			String[] result = new String[2];
			result=Util.comprobarAppellidoCompuesto(solicitudForm.getNombre(),solicitudForm.getPrimerApellido());
			solicitudForm.setCalleDomicilio(codificarCadenaAjax(solicitudForm.getCalleDomicilio()));
			solicitudForm.setEmail(codificarCadenaAjax(solicitudForm.getEmail()));
			solicitudForm.setLocalidadNacimiento(codificarCadenaAjax(solicitudForm.getLocalidadNacimiento()));
			solicitudForm.setMunicipioDomicilio(codificarCadenaAjax(solicitudForm.getMunicipioDomicilio()));
			solicitudForm.setNacionalidad(codificarCadenaAjax(solicitudForm.getNacionalidad()));
			solicitudForm.setNombre(codificarCadenaAjax(result[0]));
			solicitudForm.setPrimerApellido(codificarCadenaAjax(result[1]));
			solicitudForm.setSegundoApellido(codificarCadenaAjax(solicitudForm.getSegundoApellido()));
			solicitudForm.setDetalleDiscapacidad(codificarCadenaAjax(solicitudForm.getDetalleDiscapacidad()));
			solicitudForm.setDesConsentimiento(codificarCadenaAjax(solicitudForm.getDesConsentimiento()));
			solicitudForm.setOtrosTitulos(codificarCadenaAjax(solicitudForm.getOtrosTitulos()));
			solicitudForm.setMotivoOposicion(codificarCadenaAjax(solicitudForm.getMotivoOposicion()));
			if(solicitudForm.getIdConsentimiento() != null || !solicitudForm.getIdConsentimiento()){
				solicitudForm.setConsentimiento(true);
			}else{
				solicitudForm.setConsentimiento(false);
			}
		}
		String resultado="novalidate";
		Long id = 0L;

		//Variable para pruebas de estress
		String convocatoriaRepetida_Unico = properties.getProperty("altaSolicitud.inscripcionMultiple_Unica");

		//Comprobar si el usuario esta en la sesion
		CiudadanoBean usuActual = null;
		if(convocatoriaRepetida_Unico.equals("U")){
			usuActual = (CiudadanoBean) this.getRequest().getSession().getAttribute("usuarioClave");
		}else{
			usuActual = new CiudadanoBean();
			usuActual.setNif("12345678Z");
		}
		if(usuActual == null){
			this.getRequest().setAttribute(STRING_ERROR_DESCRIPCION,RESOURCE_BUNDLE.getString("solicitud.error.usuario"));
			return STRING_ERROR_USUARIO;
		}else{
			if(usuActual.getNif() == null){
				this.getRequest().setAttribute(STRING_ERROR_DESCRIPCION,RESOURCE_BUNDLE.getString("solicitud.error.usuario"));
				return STRING_ERROR_USUARIO;
			}
		}
		
		// si se trata de un F.H el nif que corresponde es el value del input del nif del ciudadano
		if (usuActual.getTipoPersona().equals(Constantes.TIPO_PERSONA_FUNCIONARIO_HABILITADO)) {
			solicitudForm.setNif(solicitudForm.getNifCiudadano());
		}
		
		logger.info("nifForm: "+solicitudForm.getNif());
		
		if (!usuActual.getTipoPersona().equals(Constantes.TIPO_PERSONA_FUNCIONARIO_HABILITADO) && !usuActual.getNif().equals(solicitudForm.getNif())) {
		
			this.getRequest().setAttribute(STRING_ERROR_DESCRIPCION,RESOURCE_BUNDLE.getString("solicitud.error.usuarioSolicitudError"));
			return STRING_ERROR_USUARIO;
		
		}
			

		if (action.equals("Guardar")){

			if(solicitudForm.getIdConvocatoria() != null){
				//Recupera la convocatoria
				PlantillaQuery plantillaQuery = new PlantillaQuery();
				plantillaQuery.setId(convocatoriaBean.getIdPlantilla());

				String comprobar = properties.getProperty("conf.validacionInscripcion");				

				if(convocatoriaRepetida_Unico.equals("U")){

					if(comprobar!=null && !comprobar.equals("false")){
						//Comprobar si existe una solicitud de la convocatoria con el mismo nif
						ConvocatoriaQuery convocatoriaQueryComprobar = new ConvocatoriaQuery();
						convocatoriaQueryComprobar.setId(Long.parseLong(solicitudForm.getIdConvocatoria()));
						SolicitudComunQuery solicitudQueryComprobar = new SolicitudComunQuery();
						solicitudQueryComprobar.setNif(usuActual.getNif());
						if (usuActual.getTipoPersona().equals(Constantes.TIPO_PERSONA_FUNCIONARIO_HABILITADO)) {
							solicitudQueryComprobar.setNif(solicitudForm.getNif());
						}
						solicitudQueryComprobar.setConvocatoria(convocatoriaQueryComprobar);
						solicitudQueryComprobar.addEstadoSolicitudIn(Constantes.SOLICITUD_ID_NOPAGADO);
						solicitudQueryComprobar.addEstadoSolicitudIn(Constantes.SOLICITUD_ID_NOREGISTRADO);
						solicitudQueryComprobar.addEstadoSolicitudIn(Constantes.SOLICITUD_ID_REGISTRADO);
						solicitudQueryComprobar.addEstadoSolicitudIn(Constantes.SOLICITUD_ID_PENDIENTE_REGISTRO);
						solicitudQueryComprobar.addEstadoSolicitudIn(Constantes.SOLICITUD_ID_PROCESO_PAGO);
						solicitudQueryComprobar.addEstadoSolicitudIn(Constantes.SOLICITUD_ID_PROCESO_REGISTRO);				
						SolicitudBean solicitud = solicitudesManager.buscarRegistroSolicitud(solicitudQueryComprobar);

						if(solicitud != null && solicitudForm.getPagoInicialModif() == null){
							//Ya se encuentra una solicitud para ese usuario
							logger.info("Existe una solicitud para esa convocatoria registrada con ese usuario");
							if(solicitud.getIdEstadoSolicitud().equals(Constantes.SOLICITUD_ID_NOPAGADO_STRING) ||
									solicitud.getIdEstadoSolicitud().equals(Constantes.SOLICITUD_ID_NOREGISTRADO_STRING) || 
									solicitud.getIdEstadoSolicitud().equals(Constantes.SOLICITUD_ID_REGISTRADO_STRING) ||
									solicitud.getIdEstadoSolicitud().equals(Constantes.SOLICITUD_ID_PENDIENTE_REGISTRO_STRING)){
								this.setRequestAttribute("registro", solicitud.getId().toString());
								logger.info("Reanudar Solicitud");
								return "reanudarSolicitud";

							}else{
								this.getRequest().setAttribute(STRING_ERROR_DESCRIPCION,RESOURCE_BUNDLE.getString("solicitud.error.duplicado"));
								return "error";
							}
						}
					}else{
						logger.info("No se valida duplicidad de solicitudes de un mismo NIF para una convocatoria");
					}
				}

				//Recupera la plantilla de la convocatoria
				PlantillaBean plantillaBean = plantillaManager.buscarPlantillaById(plantillaQuery);


				//Comprueba si los datos introducidos son los correctos para la plantilla
				SolicitudBean solicitudBean = toSolicitudBean(solicitudForm,plantillaBean);
				if(solicitudBean == null){
					AltaSolicitudBean altaSolicitudBean = new AltaSolicitudBean();
					altaSolicitudBean.setImporteOriginal(solicitudForm.getImporteOriginal());
					altaSolicitudBean.setNumSolicitud(solicitudForm.getNumSolicitud());
					this.setRequestAttribute("altaSolicitud", altaSolicitudBean);
					//Si no es correcto redireeciona a la pagina de alta
					return "novalidate";
				}
				
				// se añade los campos relativos al F.H
				if (usuActual.getTipoPersona().equals(Constantes.TIPO_PERSONA_FUNCIONARIO_HABILITADO)) {
					// campo boolean es F.H
					solicitudBean.setFunHabilitado(Constantes.TIPO_PERSONA_FUNCIONARIO_HABILITADO_SI);
					
					// campo F.H -> se obtiene el F.H de base de datos para setearlo a la solicitud comun
					FuncionarioHabilitadoQuery funcionarioHabilitadoQuery = new FuncionarioHabilitadoQuery();
					funcionarioHabilitadoQuery.setNif(usuActual.getNif());
					FuncionarioHabilitado funcionarioHabilitado = funcionarioHabilitadoManager.obtenerFuncionario(funcionarioHabilitadoQuery);
					
					// si no esta en bb.dd el funcionario habilitado se le registra
					if (funcionarioHabilitado == null) {
						funcionarioHabilitado = new FuncionarioHabilitado();
						funcionarioHabilitado.setNif(usuActual.getNif());
						funcionarioHabilitado.setNombre(usuActual.getNombre());
						funcionarioHabilitado.setPrimerApellido(usuActual.getPrimerApellido());
						funcionarioHabilitado.setSegundoApellido(usuActual.getSegundoApellido());
						
						funcionarioHabilitadoManager.guardarFuncionario(funcionarioHabilitado);
					}
					solicitudBean.setFuncionarioHabilitado(funcionarioHabilitado);
					
					// nombre y apellidos del ciudadano inscrito deben registrarse en mayuscula 
					solicitudBean.setNombre(solicitudBean.getNombre().toUpperCase());
					solicitudBean.setPrimerApellido(solicitudBean.getPrimerApellido().toUpperCase());
					solicitudBean.setSegundoApellido(solicitudBean.getSegundoApellido().toUpperCase());
					
				} else {
					solicitudBean.setFunHabilitado(Constantes.TIPO_PERSONA_FUNCIONARIO_HABILITADO_NO);
				}
								
				solicitudForm.setIdConsentimiento(solicitudBean.getIdConsentimiento());

				solicitudForm.setMotivoOposicion(solicitudBean.getMotivoOposicion());
				
				// Agregar modelo de convocatoria asociado a la solicitud
				if(convocatoriaBean.getNumModelo()!=null && !convocatoriaBean.getNumModelo().equals("")){
					logger.info("1.Solicitud-Modelo: "+convocatoriaBean.getNumModelo());
					Modelo modelo = new Modelo();
					modelo.setNumModelo(convocatoriaBean.getNumModelo());
					solicitudBean.setModelo(modelo);
				}
				try{
					//Guarda la solicitud
					id = solicitudesManager.guardarSolicitud(solicitudBean);

					if(id!=null){
						// Guardamos los campos propios de la solicitud
						HashSet<PlantillaPropiosBean> camposPropios = new HashSet<PlantillaPropiosBean> (solicitudBean.getPlantillaPropios());
						solicitudPropioManager.guardarCamposSolicitudModif(camposPropios, id);
						
						// Guardamos los datos en registro auditoria
						AuditoriaBean auditoriaBean = new AuditoriaBean();
						auditoriaBean.setNif(usuActual.getNif());
						auditoriaBean.setNombre(usuActual.getNombre());
						auditoriaBean.setPrimerApellido(usuActual.getPrimerApellido());
						auditoriaBean.setSegundoApellido(usuActual.getSegundoApellido());
						auditoriaBean.setIdSolicitud(id);	
						auditoriaBean.setFechaAutenticacion(new Date());				
						
						if(usuActual.getNumeroSerie()!=null){
							auditoriaBean.setNumeroSerie(usuActual.getNumeroSerie());
						}else{
							auditoriaBean.setNumeroSerie(" ");
						}
						
						if (usuActual.getTipoPersona().equals(Constantes.TIPO_PERSONA_FUNCIONARIO_HABILITADO)){
							auditoriaBean.setTipoPersona(Constantes.TIPO_PERSONA_FUNCIONARIO_HABILITADO);
						} else {
							auditoriaBean.setTipoPersona(Constantes.TIPO_PERSONA_CIUDADANO);
						}
						
						try{
							// Guardamos el registro
							registroAuditoriaManager.guardarRegistroAuditoria(auditoriaBean);
						}catch(Exception e){
							logger.error("Error al insertar el registro en la tabla auditoria",e);	
						}
					}

					this.setRequestAttribute("id",String.valueOf(id));
					this.setRequestAttribute("idConvocatoria",String.valueOf(solicitudBean.getIdConvocatoria()));
					
				}catch(Exception e){
					this.getRequest().setAttribute(STRING_ERROR_DESCRIPCION,RESOURCE_BUNDLE.getString("solicitud.error.alta.eror"));
					logger.error("Error alta solicitud: "+ solicitudBean.getIdConvocatoria(),e);
					return "error";
				}
				
				logger.info("idGuardada: "+id);
				EstadoSolicitudQuery estadoSolicitudQuery = new EstadoSolicitudQuery();
				estadoSolicitudQuery.setId(Constantes.SOLICITUD_ID_NOPAGADO);				
				SolicitudComunQuery solicitudQuery = new SolicitudComunQuery();
				solicitudQuery.setIdSolicitud(id);
				
				//Actualiza el estado solicitud
				Long idEstado = solicitudesManager.actualizarEstadoSolicitud(solicitudQuery,estadoSolicitudQuery);
				logger.info("1.SolicitudAction-Estado actualizado: "+idEstado);
				
				LogSolicitudBean logSolicitudBean = new LogSolicitudBean();
				logSolicitudBean.setActor(usuActual.getNif());
				if (usuActual.getTipoPersona().equals(Constantes.TIPO_PERSONA_FUNCIONARIO_HABILITADO)) {
					logSolicitudBean.setTipoActor(Constantes.TIPO_ACTOR_FUNCIONARIO_HABILITADO);
				}
				Date fechaAux =new Date();
				logSolicitudBean.setFecha(fechaAux);
				logSolicitudBean.setTipoOperacion(Constantes.LOG_SOLICITUD_ALTA);
				logSolicitudBean.setDetalleOperacion(RESOURCE_BUNDLE.getString("logSolicitud.detalleAlta"));
				logSolicitudBean.setIdSolicitud(String.valueOf(id));
				logSolicitudBean.setIdEstadoActual(String.valueOf(Constantes.LOG_SOLICITUD_ESTADO_NOPAGADO));
				//Guarda el log
				Integer idLog = tablaBaseManager.insertarLogSolicitud(logSolicitudBean);
				if(idLog == null){
					logger.error(RESOURCE_BUNDLE.getString("logSolicitud.error"));
				}
				resultado =  "success";
			}

			logger.info("Guardando datos de CCAA");
			if(solicitudForm.getIdComunidadDDExento() != null && !solicitudForm.getIdComunidadDDExento().isEmpty() && !solicitudForm.getIdComunidadDDExento().equals("0")){
				SolicitudBean solicitud;
				SolicitudComunQuery solicitudComunQuery = new SolicitudComunQuery();
				solicitudComunQuery.setNumeroSolicitud(solicitudForm.getNumSolicitud());
				solicitudComunQuery.setNif(solicitudForm.getNif());
				solicitud = solicitudesManager.buscarSolicitudById(solicitudComunQuery);
				SolicitudCcaaBean solicitudCcaaBean = new SolicitudCcaaBean();
				solicitudCcaaBean.setIdSolicitud(solicitud.getId());
				solicitudCcaaBean.setIdComunidadDD(solicitudForm.getIdComunidadDDExento());
				solicitudCcaaBean.setTituloFamnumerosa(solicitudForm.getTitulo());
				solicitudCcaaManager.guardarSolicitudCcaa(solicitudCcaaBean);
			}
		}

		return resultado;
	}


	/**
	 * Obtiene el solicitudes manager.
	 *
	 * @return el solicitudes manager
	 */
	public SolicitudManager getSolicitudesManager() {
		return solicitudesManager;
	}

	/**
	 * Establece el solicitudes manager.
	 *
	 * @param solicitudesManager el nuevo solicitudes manager
	 */
	public void setSolicitudesManager(SolicitudManager solicitudesManager) {
		this.solicitudesManager = solicitudesManager;
	}


	/**
	 * To solicitud bean.
	 *
	 * @param solicitud el solicitud
	 * @param plantillaBean el plantilla bean
	 * @return el solicitud bean
	 */
	public SolicitudBean toSolicitudBean(SolicitudForm solicitud, PlantillaBean plantillaBean) {

		SpringMessages messages = new SpringMessages();
		SimpleDateFormat formatoFecha= new SimpleDateFormat("dd/MM/yyyy");
		SolicitudBean result = new SolicitudBean();
		Validacion validacion = new Validacion();
		String resultado = Constantes.RESULTADO_OK; 
		result.setIdConvocatoria(Long.valueOf(solicitud.getIdConvocatoria()));
		String num = solicitud.getNumSolicitud();


		String siglasCentroGestorJusticia = "";
		String siglasCentroGestor = this.getRequest().getParameter("siglasCentroGestor");

		
		this.getRequest().getSession().setAttribute("sCG", siglasCentroGestor); 
		this.getRequest().getSession().setAttribute("sCGJ", siglasCentroGestorJusticia); 

		if(num != null && !"".equals(num)){
			result.setNumeroSolicitud(solicitud.getNumSolicitud());
		}else{
			messages.add("dsErrorNumeroJustificante", new SpringMessage("field.solicitud.alta.errorNumeroJustificante"));
			resultado = STRING_ERROR;
		}
		if(plantillaBean.getNif() == Constantes.PLANTILLA_SI){
			if(solicitud.getNif() != null && !"".equals(solicitud.getNif())){
				String letraNif = String.valueOf(solicitud.getNif().charAt(0));
				if(letraNif.equals("X") || letraNif.equals("Y") || letraNif.equals("Z")){
					boolean validateNie = validacion.nieValidate(solicitud.getNif());
					if(validateNie == false){
						messages.add("dsErrorNieFormato", new SpringMessage(STRING_FIELD_SOLICITUD_ALTA_VALIDATE_ERRORNIF));
						resultado = STRING_ERROR;
					}else{
						result.setNif(solicitud.getNif());
					}
				}else{
					boolean validateNif = validacion.nifValidate(solicitud.getNif());
					if(validateNif == false){
						messages.add("dsErrorNifFormato", new SpringMessage(STRING_FIELD_SOLICITUD_ALTA_VALIDATE_ERRORNIF));
						resultado = STRING_ERROR;
					}else{
						result.setNif(solicitud.getNif());
					}
				}
			}else{
				messages.add("dsErrorNif", new SpringMessage("field.solicitud.alta.errorNif"));
				resultado = STRING_ERROR;
			}
		}else{
			if(solicitud.getNif() != null && !"".equals(solicitud.getNif())){
				String letraNif = String.valueOf(solicitud.getNif().charAt(0));
				if(letraNif.equals("X") || letraNif.equals("Y")){
					boolean validateNie = validacion.nieValidate(solicitud.getNif());
					if(validateNie == false){
						messages.add("dsErrorNieFormato", new SpringMessage(STRING_FIELD_SOLICITUD_ALTA_VALIDATE_ERRORNIF));
						resultado = STRING_ERROR;
					}else{
						result.setNif(solicitud.getNif());
					}
				}else{
					boolean validateNif = validacion.nifValidate(solicitud.getNif());
					if(validateNif == false){
						messages.add("dsErrorNifFormato", new SpringMessage(STRING_FIELD_SOLICITUD_ALTA_VALIDATE_ERRORNIF));
						resultado = STRING_ERROR;
					}else{
						result.setNif(solicitud.getNif());
					}
				}
			}
		}
		if(plantillaBean.getNombre() == Constantes.PLANTILLA_SI){
			if(solicitud.getNombre() != null && !"".equals(solicitud.getNombre())){
				boolean validateNombre = validacion.validateNombre(solicitud.getNombre());
				if(validateNombre == true){
					messages.add("dsErrorNombreFormato", new SpringMessage(
							"field.solicitud.alta.validate.errorNombre"));
					resultado = STRING_ERROR;
				}else{
					result.setNombre(solicitud.getNombre());
				}
			}else{
				messages.add("dsErrorNombre", new SpringMessage("field.solicitud.alta.errorNombre"));
				resultado = STRING_ERROR;
			}
		}else{
			if(solicitud.getNombre() != null && !"".equals(solicitud.getNombre())){
				boolean validateNombre = validacion.validateNombre(solicitud.getNombre());
				if(validateNombre == true){
					messages.add("dsErrorNombreFormato", new SpringMessage("field.solicitud.alta.validate.errorNombre"));
					resultado = STRING_ERROR;
				}else{
					result.setNombre(solicitud.getNombre());
				}
			}
		}
		if(plantillaBean.getPrimerApellido() == Constantes.PLANTILLA_SI){
			if(solicitud.getPrimerApellido() != null && !"".equals(solicitud.getPrimerApellido())){
				boolean validatePrimerApellido = validacion.validateApellido(solicitud.getPrimerApellido());
				if(validatePrimerApellido == true){
					messages.add("dsErrorPrimerApellidoFormato", new SpringMessage("field.solicitud.alta.validate.errorPrimerApellido"));
					resultado = STRING_ERROR;
				}else{
					result.setPrimerApellido(solicitud.getPrimerApellido());
				}
			}else{
				messages.add("dsErrorPrimerApellido", new SpringMessage("field.solicitud.alta.errorPrimerApellido"));
				resultado = STRING_ERROR;
			}
		}else{
			if(solicitud.getPrimerApellido() != null && !"".equals(solicitud.getPrimerApellido())){
				boolean validatePrimerApellido = validacion.validateApellido(solicitud.getPrimerApellido());
				if(validatePrimerApellido == true){
					messages.add("dsErrorPrimerApellidoFormato", new SpringMessage("field.solicitud.alta.validate.errorPrimerApellido"));
					resultado = STRING_ERROR;
				}else{
					result.setPrimerApellido(solicitud.getPrimerApellido());
				}
			}
		}
		if(plantillaBean.getSegundoApellido() == Constantes.PLANTILLA_SI){
			if(solicitud.getSegundoApellido() != null && !"".equals(solicitud.getSegundoApellido())){
				boolean validateSegundoApellido = validacion.validateApellido(solicitud.getSegundoApellido());
				if(validateSegundoApellido == true){
					messages.add("dsErrorSegundoApellidoFormato", new SpringMessage("field.solicitud.alta.validate.errorSegundoApellido"));
					resultado = STRING_ERROR;
				}else{
					result.setSegundoApellido(solicitud.getSegundoApellido());
				}
			}else{
				messages.add("dsErrorSegundoApellido", new SpringMessage("field.solicitud.alta.errorSegundoApellido"));
				resultado = STRING_ERROR;
			}
		}else{
			boolean validateSegundoApellido = validacion.validateApellido(solicitud.getSegundoApellido());
			if(validateSegundoApellido == true){
				messages.add("dsErrorSegundoApellidoFormato", new SpringMessage("field.solicitud.alta.validate.errorSegundoApellido"));
				resultado = STRING_ERROR;
			}else{
				result.setSegundoApellido(solicitud.getSegundoApellido());
			}
		}
		
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy"); 
		Date fechaNac;
		
		if(plantillaBean.getFechaNacimiento() == Constantes.PLANTILLA_SI){
			if(solicitud.getFechaNacimiento() != null && !"".equals(solicitud.getFechaNacimiento())){
				try {			

					boolean validateFechaAux = validacion.isFechaValida(solicitud.getFechaNacimiento());
					if(validateFechaAux == false){
						messages.add(STRING_DS_ERROR_FECHA_NACIMIENTO, new SpringMessage(STRING_FIELD_SOLICITUD_ALTA_VALIDATE_FECHANACIMIENTO));
						resultado = STRING_ERROR;
					}else{
						fechaNac = df.parse(solicitud.getFechaNacimiento());
						result.setFechaNacimiento(fechaNac);
					}
				} catch (ParseException e1) {
					logger.error("Error  parsear fecha nacimiento "+ solicitud.getFechaNacimiento(),e1);
					messages.add(STRING_DS_ERROR_FECHA_NACIMIENTO, new SpringMessage(STRING_FIELD_SOLICITUD_ALTA_VALIDATE_FECHANACIMIENTO));
					resultado = STRING_ERROR;
				}	
			}else{
				messages.add(STRING_DS_ERROR_FECHA_NACIMIENTO, new SpringMessage("field.solicitud.alta.errorFechaNacimiento"));
				resultado = STRING_ERROR;
			}
		}else{
			if(solicitud.getFechaNacimiento() != null && !"".equals(solicitud.getFechaNacimiento())){
				try {

					boolean validateFechaAux = validacion.isFechaValida(solicitud.getFechaNacimiento());
					if(validateFechaAux == false){
						messages.add(STRING_DS_ERROR_FECHA_NACIMIENTO, new SpringMessage(STRING_FIELD_SOLICITUD_ALTA_VALIDATE_FECHANACIMIENTO));
						resultado = STRING_ERROR;
					}else{
						fechaNac = df.parse(solicitud.getFechaNacimiento());
						result.setFechaNacimiento(fechaNac);
					}
				} catch (ParseException e1) {
					logger.error("Error  parsear fecha nacimiento "+ solicitud.getFechaNacimiento(),e1);
					messages.add(STRING_DS_ERROR_FECHA_NACIMIENTO, new SpringMessage(STRING_FIELD_SOLICITUD_ALTA_VALIDATE_FECHANACIMIENTO));
					resultado = STRING_ERROR;
				}	
			}
		}
		if(plantillaBean.getSexo() == Constantes.PLANTILLA_SI){
			if(solicitud.getSexo() != null && !"".equals(solicitud.getSexo())){
				result.setSexo(solicitud.getSexo().charAt(0));
			}else{
				messages.add("dsErrorSexo", new SpringMessage("field.solicitud.alta.errorSexo"));
				resultado = STRING_ERROR;
			}
		}else{
			if(solicitud.getSexo() != null && !"".equals(solicitud.getSexo())){
				result.setSexo(solicitud.getSexo().charAt(0));
			}
		}
		if(plantillaBean.getNacionalidad() == Constantes.PLANTILLA_SI){
			if(solicitud.getNacionalidad() != null && !"".equals(solicitud.getNacionalidad())){
				boolean validateNacionalidad = validacion.validateDescripcion(solicitud.getNacionalidad());
				if(validateNacionalidad == true){
					messages.add(STRING_DS_ERROR_NACIONALIDAD, new SpringMessage("field.solicitud.alta.validate.errorNacionalidad"));
					resultado = STRING_ERROR;
				}else{
					result.setNacionalidad(solicitud.getNacionalidad());
				}
			}else{
				messages.add(STRING_DS_ERROR_NACIONALIDAD, new SpringMessage("field.solicitud.alta.errorNacionalidad"));
				resultado = STRING_ERROR;
			}
		}else{
			if(solicitud.getNacionalidad() != null && !"".equals(solicitud.getNacionalidad())){
				boolean validateNacionalidad = validacion.validateDescripcion(solicitud.getNacionalidad());
				if(validateNacionalidad == true){
					messages.add(STRING_DS_ERROR_NACIONALIDAD, new SpringMessage("field.solicitud.alta.validate.errorNacionalidad"));
					resultado = STRING_ERROR;
				}else{
					result.setNacionalidad(solicitud.getNacionalidad());
				}
			}
		}
		
		TipoSolicitud tipo = new TipoSolicitud();
		tipo.setId(2);
		result.setTipoSolicitud(tipo);
		String telefono = "";
		
		if(plantillaBean.getTelefono() == Constantes.PLANTILLA_SI){
			if(solicitud.getTelefono() != null && !"".equals(solicitud.getTelefono())){
				if(solicitud.getTelefono().length()==9){
					boolean validateTelefono = validacion.validateTelefono(solicitud.getTelefono());
					boolean validateTelefonoAux = validacion.validateTelefono(solicitud.getTelefonoAux());
					if(validateTelefono == true){
						messages.add("dsErrorTelefonoFormato", new SpringMessage("field.solicitud.alta.validate.errorTelefono"));
						resultado = STRING_ERROR;				
					}else{					
						telefono = solicitud.getTelefono();
					}
				}else{
					messages.add(STRING_DS_ERROR_TELEFONO, new SpringMessage("field.solicitud.alta.errorTelefono"));
					resultado = STRING_ERROR;
				}
			}else{
				messages.add(STRING_DS_ERROR_TELEFONO, new SpringMessage("field.solicitud.alta.errorTelefono"));
				resultado = STRING_ERROR;
			}
		}else{
			if(solicitud.getTelefono() != null && !"".equals(solicitud.getTelefono())){
				boolean validateTelefono = validacion.validateTelefono(solicitud.getTelefono());
				if(validateTelefono == true){
					messages.add("dsErrorTelefonoFormato", new SpringMessage("field.solicitud.alta.validate.errorTelefono"));
					resultado = STRING_ERROR;				
				}else{					
					telefono = solicitud.getTelefono();
				}
			}
		}
		String telefonoAux = "";
		if(solicitud.getTelefonoAux() != null && !"".equals(solicitud.getTelefonoAux())){
			if(solicitud.getTelefonoAux().length()!=9){
				messages.add(STRING_DS_ERROR_TELEFONO, new SpringMessage("field.solicitud.alta.validate.errorTelefono2"));
				resultado = STRING_ERROR;							
			}else{
				boolean validateTelefonoAux = validacion.validateTelefono(solicitud.getTelefonoAux());
				if(validateTelefonoAux == true){
					messages.add("dsErrorTelefonoAuxFormato", new SpringMessage("field.solicitud.alta.validate.errorTelefono2"));
					resultado = STRING_ERROR;
				}else{
					telefonoAux = solicitud.getTelefonoAux();
				}
			}
		}
		String telefonoCompleto = telefono;
		if(telefonoAux != null && !"".equals(telefonoAux)){
			telefonoCompleto = telefonoCompleto + "/" + telefonoAux;
		}
		result.setTelefono(telefonoCompleto);
		result.setTelefonoAux(telefonoAux);
		result.setTelefono1(telefono);
		if(plantillaBean.getVia() == Constantes.PLANTILLA_SI){
			if(solicitud.getCalleDomicilio() != null && !"".equals(solicitud.getCalleDomicilio())){
				boolean validateDireccion = validacion.validateDireccion(solicitud.getCalleDomicilio());
				if(validateDireccion == true){
					messages.add("dsErrorViaFormato", new SpringMessage("field.solicitud.alta.validate.errorVia"));
					resultado = STRING_ERROR;
				}else{
					result.setCalleDomicilio(solicitud.getCalleDomicilio());
				}
			}else{
				messages.add("dsErrorVia", new SpringMessage("field.solicitud.alta.errorVia"));
				resultado = STRING_ERROR;
			}
		}else{
			if(solicitud.getCalleDomicilio() != null && !"".equals(solicitud.getCalleDomicilio())){
				boolean validateDireccion = validacion.validateDireccion(solicitud.getCalleDomicilio());
				if(validateDireccion == true){
					messages.add("dsErrorViaFormato", new SpringMessage("field.solicitud.alta.validate.errorVia"));
					resultado = STRING_ERROR;
				}else{
					result.setCalleDomicilio(solicitud.getCalleDomicilio());
				}
			}
		}
		if(plantillaBean.getCodigoPostal() == Constantes.PLANTILLA_SI || plantillaBean.getCodigoPostal() == Constantes.PLANTILLA_NO){
			if(!solicitud.getPais().equals("1")){
				result.setCodigoPostalDomicilio(solicitud.getCodigoPostalDomicilio());
			}else if(solicitud.getPais().equals("1") && solicitud.getCodigoPostalDomicilio() != null && !"".equals(solicitud.getCodigoPostalDomicilio())){
				
				//Este campo sólo se validará si está relleno
				
					boolean validateCodPostal = validacion.validateNumero(solicitud.getCodigoPostalDomicilio());
					if(validateCodPostal == true){
						messages.add("dsErrorCodPostalFormato", new SpringMessage("field.solicitud.alta.validate.errorCodPostal"));
						resultado = STRING_ERROR;
					}else{					
						String digitosProvincia =  solicitud.getCodigoPostalDomicilio().substring(0, 2);
						ProvinciaQuery provinciaQuery = new ProvinciaQuery();
						provinciaQuery.setCodigo(digitosProvincia);
						provinciaQuery.setId(Integer.parseInt(solicitud.getProvinciaDomicilio()));

						ProvinciaBean provincia = provinciaManager.buscarProviciaId(provinciaQuery);

						if(provincia != null){
							result.setCodigoPostalDomicilio(solicitud.getCodigoPostalDomicilio());
						}else{
							messages.add("dsErrorCodPostal", new SpringMessage("field.formulario790.javascript.codigoPostalError"));
							resultado = STRING_ERROR;
						}
					}
				
			}
		}
		

		if(plantillaBean.getPais() == Constantes.PLANTILLA_SI){
			if(solicitud.getPais() != null && !"".equals(solicitud.getPais())
					&& !"0".equals(solicitud.getPais())){
				result.setPaisDomicilioId(solicitud.getPais());
			}else{
				messages.add("dsErrorPaisDomicilio", new SpringMessage("field.solicitud.alta.errorPais"));
				resultado = STRING_ERROR;
			}
		}else{
			if(solicitud.getPais() != null && !"".equals(solicitud.getPais())
					&& !"0".equals(solicitud.getPais())){
				result.setPaisDomicilioId(solicitud.getPais());
			}
		}

		if(plantillaBean.getProvincia() == Constantes.PLANTILLA_SI){
			if(solicitud.getPais() == "1"){
				if(solicitud.getProvinciaDomicilio() != null && !"".equals(solicitud.getProvinciaDomicilio())
						&& !"0".equals(solicitud.getProvinciaDomicilio())){
					boolean validateProvincia = validacion.validateDescripcion(solicitud.getProvinciaDomicilio());
					if(validateProvincia == true){
						messages.add(STRING_DS_ERROR_PROVINCIA_DOMICILIO_FORMATO, new SpringMessage(STRING_FIELD_SOLICITUD_ALTA_VALIDATE_ERRORPROVINCIA));
						resultado = STRING_ERROR;
					}else{
						result.setProvinciaDomicilioIds(solicitud.getProvinciaDomicilio());
					}				
				}else{
					messages.add("dsErrorProvinciaDomicilio", new SpringMessage("field.solicitud.alta.errorProvincia"));
					resultado = STRING_ERROR;
				}
			}else{
				if(solicitud.getProvinciaDomicilio() != null && !"".equals(solicitud.getProvinciaDomicilio())
						&& !"0".equals(solicitud.getProvinciaDomicilio())){
					boolean validateProvincia = validacion.validateDescripcion(solicitud.getProvinciaDomicilio());
					if(validateProvincia == true){
						messages.add(STRING_DS_ERROR_PROVINCIA_DOMICILIO_FORMATO, new SpringMessage(STRING_FIELD_SOLICITUD_ALTA_VALIDATE_ERRORPROVINCIA));
						resultado = STRING_ERROR;
					}else{
						result.setProvinciaDomicilioIds(solicitud.getProvinciaDomicilio());
					}
				}
			}
		}else{
			if(solicitud.getProvinciaDomicilio() != null && !"".equals(solicitud.getProvinciaDomicilio())){
				boolean validateProvincia = validacion.validateDescripcion(solicitud.getProvinciaDomicilio());
				if(validateProvincia == true){
					messages.add(STRING_DS_ERROR_PROVINCIA_DOMICILIO_FORMATO, new SpringMessage(STRING_FIELD_SOLICITUD_ALTA_VALIDATE_ERRORPROVINCIA));
					resultado = STRING_ERROR;
				}else{
					result.setProvinciaDomicilioIds(solicitud.getProvinciaDomicilio());
				}
			}
		}

		if(plantillaBean.getProvinciaExamen() == Constantes.PLANTILLA_SI){
			if(solicitud.getProvinciaExamen() != null && !"".equals(solicitud.getProvinciaExamen())
					&& !"0".equals(solicitud.getProvinciaExamen())){
				boolean validateProvincia = validacion.validateDescripcion(solicitud.getProvinciaExamen());
				if(validateProvincia == true){
					messages.add("dsErrorProvinciaExamenFormato", new SpringMessage(STRING_FIELD_SOLICITUD_ALTA_VALIDATE_ERRORPROVINCIA));
					resultado = STRING_ERROR;
				}else{
					result.setProvinciaExamenIds(solicitud.getProvinciaExamen());
				}
			}else{
				messages.add("dsErrorProvinciaExamen", new SpringMessage("field.solicitud.alta.errorProvinciaExamen"));
				resultado = STRING_ERROR;
			}
		}else{
			if(solicitud.getProvinciaExamen() != null && !"".equals(solicitud.getProvinciaExamen())
					&& !"0".equals(solicitud.getProvinciaExamen())){
				boolean validateProvincia = validacion.validateDescripcion(solicitud.getProvinciaExamen());
				if(validateProvincia == true){
					messages.add("dsErrorProvinciaExamenFormato", new SpringMessage(STRING_FIELD_SOLICITUD_ALTA_VALIDATE_ERRORPROVINCIA));
					resultado = STRING_ERROR;
				}else{
					result.setProvinciaExamenIds(solicitud.getProvinciaExamen());
				}
			}
		}

		if(plantillaBean.getMunicipio() == Constantes.PLANTILLA_SI){
			if(solicitud.getMunicipioDomicilio() != null && !"".equals(solicitud.getMunicipioDomicilio())){
				boolean validateMunicipio = validacion.validateDescripcion(solicitud.getMunicipioDomicilio());
				if(validateMunicipio == true){
					messages.add("dsErrorMunicipioFormato", new SpringMessage("field.solicitud.alta.validate.errorMunicipio"));
					resultado = STRING_ERROR;
				}else{
					result.setMunicipioDomicilio(solicitud.getMunicipioDomicilio());
				}
			}else{
				messages.add("dsErrorMunicipio", new SpringMessage("field.solicitud.alta.errorMunicipio"));
				resultado = STRING_ERROR;
			}
		}else{
			if(solicitud.getMunicipioDomicilio() != null && !"".equals(solicitud.getMunicipioDomicilio())){
				boolean validateMunicipio = validacion.validateDescripcion(solicitud.getMunicipioDomicilio());
				if(validateMunicipio == true){
					messages.add("dsErrorMunicipioFormato", new SpringMessage("field.solicitud.alta.validate.errorMunicipio"));
					resultado = STRING_ERROR;
				}else{
					result.setMunicipioDomicilio(solicitud.getMunicipioDomicilio());
				}
			}
		}
		Date fechaActualizacion = new Date();
		result.setFechaUtlActualizacion(fechaActualizacion);
		result.setFechaSolicitud(fechaActualizacion);

		if(plantillaBean.getCorreoElectronico() == Constantes.PLANTILLA_SI){
			if(solicitud.getEmail() != null && !"".equals(solicitud.getEmail())){
				boolean validateEmail = validacion.validateEmail(solicitud.getEmail());
				if(validateEmail == true){
					messages.add("dsErrorEmail", new SpringMessage("field.solicitud.alta.validate.errorEmail"));
					resultado = STRING_ERROR;
				}else{
					result.setEmail(solicitud.getEmail());
				}
			}else{
				messages.add("dsErrorCorreoElectronico", new SpringMessage("field.solicitud.alta.errorCorreoElectronico"));
				resultado = STRING_ERROR;
			}
		}else{
			if(solicitud.getEmail() != null && !"".equals(solicitud.getEmail()) && solicitud.getEmail() != null && !"".equals(solicitud.getEmail())){
				
					boolean validateEmail = validacion.validateEmail(solicitud.getEmail());
					if(validateEmail == true){
						messages.add("dsErrorEmail", new SpringMessage("field.solicitud.alta.validate.errorEmail"));
						resultado = STRING_ERROR;
					}else{
						result.setEmail(solicitud.getEmail());
					}
				
			}
		}

		if(plantillaBean.getEspecialidad() == Constantes.PLANTILLA_SI){
			if(solicitud.getEspecialidad() != null && !"".equals(solicitud.getEspecialidad())
					&& !"0".equals(solicitud.getEspecialidad())){				
				result.setEspecialidadesId(solicitud.getEspecialidad());
			}else{
				messages.add("dsErrorEspecialidad", new SpringMessage("field.solicitud.alta.errorEspecialidad"));
				resultado = STRING_ERROR;
			}
		}else{
			if(solicitud.getEspecialidad() != null && !"".equals(solicitud.getEspecialidad())
					&& !"0".equals(solicitud.getEspecialidad())){
				result.setEspecialidadesId(solicitud.getEspecialidad());
			}
		}

		if(plantillaBean.getOtrosTitulos() == Constantes.PLANTILLA_SI){
			if(solicitud.getOtrosTitulos() != null && !"".equals(solicitud.getOtrosTitulos())
					&& !"0".equals(solicitud.getOtrosTitulos())){
				result.setOtrosTitulos(solicitud.getOtrosTitulos());
			}else{
				messages.add("dsErrorOtrosTitulos", new SpringMessage("field.solicitud.alta.errorOtrosTitulos"));
				resultado = STRING_ERROR;
			}
		}else{
			if(solicitud.getOtrosTitulos() != null && !"".equals(solicitud.getOtrosTitulos())
					&& !"0".equals(solicitud.getOtrosTitulos())){
				result.setOtrosTitulos(solicitud.getOtrosTitulos());
			}
		}

		if(plantillaBean.getTitulosExigidos() == Constantes.PLANTILLA_SI){
			if(solicitud.getTitulo() != null && !"".equals(solicitud.getTitulo())
					&& !"0".equals(solicitud.getTitulo())){
				result.setTituloOficialId(solicitud.getTitulo());
			}else{
				messages.add("dsErrorTituloExigido", new SpringMessage("field.solicitud.alta.errorTituloExigido"));
				resultado = STRING_ERROR;
			}
		}else{
			if(solicitud.getTitulo() != null && !"".equals(solicitud.getTitulo())
					&& !"0".equals(solicitud.getTitulo())){
				result.setTituloOficialId(solicitud.getTitulo());
			}
		}

		Long auxPorcentaje = 0L;
		if(plantillaBean.getPorcentajeDiscapacidad() == Constantes.PLANTILLA_SI){
			if(solicitud.getPorcentajeDiscapacidad() != null && !"".equals(solicitud.getPorcentajeDiscapacidad())){
				try{
					auxPorcentaje = Long.valueOf(solicitud.getPorcentajeDiscapacidad());

					if (auxPorcentaje.intValue()<0 || auxPorcentaje.intValue()>100){
						messages.add("dsErrorPorcentajeDiscapacidad", new SpringMessage(STRING_FIELD_SOLICITUD_ALTA_ERRORFORMATOPORCENTAJEDISCAPACIDAD));
						resultado = STRING_ERROR;
					}

					result.setPorcentajeDiscapacidad(auxPorcentaje);
					
				}catch(Exception e){
					logger.error("Error  parsear porcentaje discapacidad ",e);
					messages.add(STRING_DS_ERROR_PORCENTAJE, new SpringMessage(STRING_FIELD_SOLICITUD_ALTA_ERRORFORMATOPORCENTAJEDISCAPACIDAD));
					resultado = STRING_ERROR;
				}
			}else{
				messages.add(STRING_DS_ERROR_PORCENTAJE, new SpringMessage("field.solicitud.alta.errorPorcentajeDiscapacidad"));
				resultado = STRING_ERROR;
			}
		}else{
			if(solicitud.getPorcentajeDiscapacidad() != null && !"".equals(solicitud.getPorcentajeDiscapacidad())){
				try{
					auxPorcentaje = Long.valueOf(solicitud.getPorcentajeDiscapacidad());
					if (auxPorcentaje.intValue()<0 || auxPorcentaje.intValue()>100){
						messages.add("dsErrorPorcentajeDiscapacidad", new SpringMessage(STRING_FIELD_SOLICITUD_ALTA_ERRORFORMATOPORCENTAJEDISCAPACIDAD));
						resultado = STRING_ERROR;
					}

					result.setPorcentajeDiscapacidad(auxPorcentaje);
					
				}catch(Exception e){
					logger.error("Error  parsear porcentaje discapacidad ",e);
					messages.add(STRING_DS_ERROR_PORCENTAJE, new SpringMessage(STRING_FIELD_SOLICITUD_ALTA_ERRORFORMATOPORCENTAJEDISCAPACIDAD));
					resultado = STRING_ERROR;
				}
			}
		}
		result.setReservaDiscapacidad(((solicitud.getReservaDiscapacidad())==null) ? Constantes.PLANTILLA_NO:Constantes.PLANTILLA_SI);
		
		if(plantillaBean.getDetalleDiscapacidad() == Constantes.PLANTILLA_SI){
			if(auxPorcentaje!=0L && solicitud.getDetalleDiscapacidad() != null && !"".equals(solicitud.getDetalleDiscapacidad())){
				result.setDetalleDiscapacidad(solicitud.getDetalleDiscapacidad());
			}else{
				messages.add("dsErrorDetalleDiscapacidad", new SpringMessage("field.solicitud.alta.errorDetalleDiscapacidad"));
				resultado = STRING_ERROR;
			}
		}else{
			if(auxPorcentaje!=0L && solicitud.getDetalleDiscapacidad() != null && !"".equals(solicitud.getDetalleDiscapacidad())){
				result.setDetalleDiscapacidad(solicitud.getDetalleDiscapacidad());
			}
		}

		if(plantillaBean.getTipoDiscapacidad() == Constantes.PLANTILLA_SI){
			if(solicitud.getTipoDiscapacidad() != null && !"".equals(solicitud.getTipoDiscapacidad())){
				result.setTipoDiscapacidadId(solicitud.getTipoDiscapacidad());
				result.setReservaDiscapacidad(Constantes.PLANTILLA_SI);
			}else{
				messages.add("dsErrorTipoDiscapacidad", new SpringMessage("field.solicitud.alta.errorTipoDiscapacidad"));
				resultado = STRING_ERROR;
			}
		}else{
			if(!siglasCentroGestor.equals("")){

				if(solicitud.getTipoDiscapacidad() != null && !"".equals(solicitud.getTipoDiscapacidad())
						&& !"0".equals(solicitud.getTipoDiscapacidad())){
					result.setTipoDiscapacidadId(solicitud.getTipoDiscapacidad());
					result.setReservaDiscapacidad(Constantes.PLANTILLA_SI);
				}else{
					result.setTipoDiscapacidadId(solicitud.getTipoDiscapacidad());
					result.setReservaDiscapacidad(Constantes.PLANTILLA_NO);
				}
			}else if (siglasCentroGestor.equals("")){

				if(solicitud.getTipoDiscapacidad() != null && !"".equals(solicitud.getTipoDiscapacidad()) && !"0".equals(solicitud.getTipoDiscapacidad()) && "S".equals(solicitud.getTipoDiscapacidad())){
					result.setTipoDiscapacidadId("1");
					solicitud.setTipoDiscapacidad("1");				
					result.setReservaDiscapacidad(Constantes.PLANTILLA_SI);

				}else if(solicitud.getTipoDiscapacidad() != null && !"".equals(solicitud.getTipoDiscapacidad()) && !"0".equals(solicitud.getTipoDiscapacidad()) && "N".equals(solicitud.getTipoDiscapacidad())){
					result.setTipoDiscapacidadId("");
					solicitud.setTipoDiscapacidad("");
					result.setReservaDiscapacidad(Constantes.PLANTILLA_NO);					
				}else{
					result.setTipoDiscapacidadId(solicitud.getTipoDiscapacidad());
					result.setReservaDiscapacidad(Constantes.PLANTILLA_NO);
				}		
			}
		}

		// Combo Tipo Discapacidad para 790007
		if(solicitud.getNumSolicitud().startsWith(Constantes.MODELO79007) && null!=solicitud.getTipoDiscapacidad()){
			if(solicitud.getTipoDiscapacidad().equals(Constantes.TIPO_DISCAPACIDAD_SI)){
				result.setReservaDiscapacidad(Constantes.PLANTILLA_SI);
			}else if(solicitud.getTipoDiscapacidad().equals(Constantes.TIPO_DISCAPACIDAD_NO)){
				result.setReservaDiscapacidad(Constantes.PLANTILLA_NO);
			}		
		}	
		
		boolean validarSolicitudes=true;
		for (PlantillaPropiosBean plantillas : solicitud.getPlantillaPropios()) {
			if(plantillas.isObligatorio() && (plantillas.getCampoPropioBean().getValorVista()==null || plantillas.getCampoPropioBean().getValorVista().equals(""))){
				
					resultado = STRING_ERROR;
					validarSolicitudes=false;
				
			}
		}	

		if(validarSolicitudes)
			result.setPlantillaPropios(solicitud.getPlantillaPropios());
		else
			messages.add("dsErrorPlantillas", new SpringMessage("field.solicitud.alta.errorDatosConsignar"));

		if(solicitud.getProvinciaDomicilio() != null){
			boolean validateProvincia = validacion.validateDescripcion(solicitud.getProvinciaDomicilio());
			if(validateProvincia == true){
				messages.add(STRING_DS_ERROR_PROVINCIA_DOMICILIO_FORMATO, new SpringMessage(STRING_FIELD_SOLICITUD_ALTA_VALIDATE_ERRORPROVINCIA));
				resultado = STRING_ERROR;
			}
		}

		// consentimiento para los datos personales
		if(solicitud.getIdConsentimiento() != null){
			result.setIdConsentimiento((solicitud.getIdConsentimiento()));
		}
			
		if(solicitud.getMotivoOposicion() != null && !"".equals(solicitud.getMotivoOposicion())){
			result.setMotivoOposicion((solicitud.getMotivoOposicion()));
		}
		
		if(solicitud.getIdConsentimientoAEAT() != null){
			result.setIdConsentimientoAEAT(solicitud.getIdConsentimientoAEAT());
		}
		
		cargarCombos(result, solicitud);
		
		if(STRING_ERROR.equals(resultado)){
			saveErrors(this.getRequest(), messages);
			return null;
		}
		
		
		return result;
	}

	/**
	 * Cargar combos.
	 *
	 * @param result el result
	 * @param solicitud el solicitud
	 */
	private void cargarCombos(SolicitudBean result, SolicitudForm solicitud) {
		Especialidad especialidad = new Especialidad();
		int idEspecialidad = 0;
		
		try{
			if(null!=solicitud.getEspecialidad()){
				idEspecialidad = Integer.parseInt(solicitud.getEspecialidad());
			}
		}catch(Exception e){
			logger.error("Error  parsear especialidad ",e);
		}
		especialidad.setId(idEspecialidad);
		
		if(idEspecialidad != 0){
			result.setEspecialidad(especialidad);
		}
		TipoDiscapacidad tipoDiscapacidad = new TipoDiscapacidad();
		int idTipoDiscapacidad = 0;
		
		try{
			if(solicitud.getTipoDiscapacidad() != null){
				idTipoDiscapacidad = Integer.parseInt(solicitud.getTipoDiscapacidad());
			}
		}catch(Exception e){
			logger.info(" Error -No se ha podido pasar el tipo de discapacidad a entero",e);
		}
		tipoDiscapacidad.setId(idTipoDiscapacidad);
		
		if(idTipoDiscapacidad != 0){
			result.setTipoDiscapacidad(tipoDiscapacidad);
		}
		TituloOficial tituloOficial = new TituloOficial();
		int idTituloOficial = 0;
		
		try{
			idTituloOficial = Integer.parseInt(solicitud.getTitulo());
		}catch(Exception e){
			logger.error("Error  parsear titulo oficial ",e);
		}
		tituloOficial.setId(idTituloOficial);
		if(idTituloOficial != 0){
			result.setTituloOficial(tituloOficial);
		}
		
		Provincia provinciaDomicilio = new Provincia();
		int idProvinciaDomicilio = 0;
		
		if(solicitud.getProvinciaDomicilio() != null){
		try{
			idProvinciaDomicilio = Integer.parseInt(solicitud.getProvinciaDomicilio());
		}catch(Exception e){
			logger.error("Error  parsear  provincia ",e);
		}
		}
		provinciaDomicilio.setId(idProvinciaDomicilio);
		
		if(idProvinciaDomicilio != 0){
			result.setProvinciaDomicilio(provinciaDomicilio);
		}
		ProvinciaExamen provinciaExamen = new ProvinciaExamen();
		int idProvinciaExamen = 0;
		
		try{
			idProvinciaExamen = Integer.parseInt(solicitud.getProvinciaExamen());
		}catch(Exception e){
			logger.error("Error  parsear provincia examen ",e);
		}
		provinciaExamen.setId(idProvinciaExamen);
		
		if(idProvinciaExamen != 0){
			result.setProvinciaExamen(provinciaExamen);
		}
		Pais pais = new Pais();
		int idPais = 0;
		
		try{
			idPais = Integer.parseInt(solicitud.getPais());
		}catch(Exception e){
			logger.error("Error  parsear pais ",e);
		}
		pais.setId(idPais);
		if(idPais != 0){
			result.setPaisDomicilio(pais);
		}
	}

	/**
	 * Codificar cadena ajax.
	 *
	 * @param cadena el cadena
	 * @return el string
	 */
	public static String codificarCadenaAjax(String cadena){
		String resultado = null;

		if(cadena!=null){
			try {
				resultado = new String(cadena.getBytes("ISO-8859-1"),"UTF-8");
			} catch (UnsupportedEncodingException e) {
				logger.error("Error  codificar cadena ajax ",e);
			}
		}

		return resultado;
	}

	

	
	
	/**
	 * Metodo que guarda los datos relacionados de cada solicitud a su CCAA para funcionario habilitado.
	 *
	 * @param pagoSolicitudForm el pago solicitud form
	 * @param solicitud el solicitud
	 */
	private void guardarSolicitudCcaaFuncionario(PagoSolicitudForm pagoSolicitudForm, SolicitudBean solicitud){
	
		SolicitudCcaaBean  solicitudCcaaBean = null;
						
		if(solicitud.getId()!=null && (pagoSolicitudForm.getMotivo()!=null || pagoSolicitudForm.getMotivoTarjeta() !=null || pagoSolicitudForm.getMotivoAdeudo()!=null )) {
			
			if (Integer.valueOf(pagoSolicitudForm.getMotivo())==1 || Integer.valueOf(pagoSolicitudForm.getMotivo())==3 || Integer.valueOf(pagoSolicitudForm.getMotivo())==5
					|| Integer.valueOf(pagoSolicitudForm.getMotivoAdeudo())==1 || Integer.valueOf(pagoSolicitudForm.getMotivoAdeudo())==3 || Integer.valueOf(pagoSolicitudForm.getMotivoAdeudo())==5
					|| Integer.valueOf(pagoSolicitudForm.getMotivoTarjeta())==1 || Integer.valueOf(pagoSolicitudForm.getMotivoTarjeta())==3 || Integer.valueOf(pagoSolicitudForm.getMotivoTarjeta())==5){
			
				solicitudCcaaBean = new SolicitudCcaaBean();
				solicitudCcaaBean.setIdSolicitud(solicitud.getId());
							
				if(Integer.valueOf(pagoSolicitudForm.getMotivo())==1 || Integer.valueOf(pagoSolicitudForm.getMotivoAdeudo())==1 || Integer.valueOf(pagoSolicitudForm.getMotivoTarjeta())==1){
					if(pagoSolicitudForm.getIdComunidadDDExento()!=null && !"".equals(pagoSolicitudForm.getIdComunidadDDExento())){
						solicitudCcaaBean.setIdComunidadDD(pagoSolicitudForm.getIdComunidadDDExento());
					}
				} else if (Integer.valueOf(pagoSolicitudForm.getMotivo())==3 || Integer.valueOf(pagoSolicitudForm.getMotivo())==5
						|| (Integer.valueOf(pagoSolicitudForm.getMotivoAdeudo())==3 || Integer.valueOf(pagoSolicitudForm.getMotivoAdeudo())==5)
						|| (Integer.valueOf(pagoSolicitudForm.getMotivoTarjeta())==3 || Integer.valueOf(pagoSolicitudForm.getMotivoTarjeta())==5)) {
					if(pagoSolicitudForm.getIdComunidadFNExento()!=null && !"".equals(pagoSolicitudForm.getIdComunidadFNExento())){
						solicitudCcaaBean.setIdComunidadFN(pagoSolicitudForm.getIdComunidadFNExento());
					}
				}
			}
		}
		
		if(pagoSolicitudForm.getTituloFNExento() !=null && !"".equals(pagoSolicitudForm.getTituloFNExento())){
			solicitudCcaaBean.setTituloFamnumerosa(pagoSolicitudForm.getTituloFNExento());
		}
	
		if(null != solicitudCcaaBean){
			solicitudCcaaManager.guardarSolicitudCcaa(solicitudCcaaBean);
		}
	}
	
	
	/**
	 * Obtiene el convocatoria manager.
	 *
	 * @return el convocatoria manager
	 */
	public ConvocatoriasManager getConvocatoriaManager() {
		return convocatoriaManager;
	}

	/**
	 * Establece el convocatoria manager.
	 *
	 * @param convocatoriaManager el nuevo convocatoria manager
	 */
	public void setConvocatoriaManager(ConvocatoriasManager convocatoriaManager) {
		this.convocatoriaManager = convocatoriaManager;
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
	 * Obtiene el estado solicitud manager.
	 *
	 * @return el estado solicitud manager
	 */
	public EstadoSolicitudManager getEstadoSolicitudManager() {
		return estadoSolicitudManager;
	}

	/**
	 * Establece el estado solicitud manager.
	 *
	 * @param estadoSolicitudManager el nuevo estado solicitud manager
	 */
	public void setEstadoSolicitudManager(
			EstadoSolicitudManager estadoSolicitudManager) {
		this.estadoSolicitudManager = estadoSolicitudManager;
	}

	/**
	 * Obtiene el tabla base manager.
	 *
	 * @return el tabla base manager
	 */
	public TablaBaseManager getTablaBaseManager() {
		return tablaBaseManager;
	}

	/**
	 * Establece el tabla base manager.
	 *
	 * @param tablaBaseManager el nuevo tabla base manager
	 */
	public void setTablaBaseManager(TablaBaseManager tablaBaseManager) {
		this.tablaBaseManager = tablaBaseManager;
	}

	/**
	 * Obtiene el parametro configuracion manager.
	 *
	 * @return el parametro configuracion manager
	 */
	public ParametroConfiguracionManager getParametroConfiguracionManager() {
		return parametroConfiguracionManager;
	}

	/**
	 * Establece el parametro configuracion manager.
	 *
	 * @param parametroConfiguracionManager el nuevo parametro configuracion manager
	 */
	public void setParametroConfiguracionManager(
			ParametroConfiguracionManager parametroConfiguracionManager) {
		this.parametroConfiguracionManager = parametroConfiguracionManager;
	}

	/**
	 * Obtiene el pago solicitudes manager.
	 *
	 * @return el pago solicitudes manager
	 */
	public PagoSolicitudManager getPagoSolicitudesManager() {
		return pagoSolicitudesManager;
	}

	/**
	 * Establece el pago solicitudes manager.
	 *
	 * @param pagoSolicitudesManager el nuevo pago solicitudes manager
	 */
	public void setPagoSolicitudesManager(
			PagoSolicitudManager pagoSolicitudesManager) {
		this.pagoSolicitudesManager = pagoSolicitudesManager;
	}

	/**
	 * Obtiene el provincia manager.
	 *
	 * @return el provincia manager
	 */
	public ProvinciaManager getProvinciaManager() {
		return provinciaManager;
	}

	/**
	 * Establece el provincia manager.
	 *
	 * @param provinciaManager el nuevo provincia manager
	 */
	public void setProvinciaManager(ProvinciaManager provinciaManager) {
		this.provinciaManager = provinciaManager;
	}

	/**
	 * Obtiene el solicitud propio manager.
	 *
	 * @return el solicitud propio manager
	 */
	public SolicitudPropioManager getSolicitudPropioManager() {
		return solicitudPropioManager;
	}

	/**
	 * Establece el solicitud propio manager.
	 *
	 * @param solicitudPropioManager el nuevo solicitud propio manager
	 */
	public void setSolicitudPropioManager(SolicitudPropioManager solicitudPropioManager) {
		this.solicitudPropioManager = solicitudPropioManager;
	}

	/**
	 * Obtiene el registro auditoria manager.
	 *
	 * @return el registro auditoria manager
	 */
	public RegistroAuditoriaManager getRegistroAuditoriaManager() {
		return registroAuditoriaManager;
	}

	/**
	 * Establece el registro auditoria manager.
	 *
	 * @param registroAuditoriaManager el nuevo registro auditoria manager
	 */
	public void setRegistroAuditoriaManager(
			RegistroAuditoriaManager registroAuditoriaManager) {
		this.registroAuditoriaManager = registroAuditoriaManager;
	}

	/**
	 * Obtiene el funcionario habilitado manager.
	 *
	 * @return el funcionario habilitado manager
	 */
	public FuncionarioHabilitadoManager getFuncionarioHabilitadoManager() {
		return funcionarioHabilitadoManager;
	}

	/**
	 * Establece el funcionario habilitado manager.
	 *
	 * @param funcionarioHabilitadoManager el nuevo funcionario habilitado manager
	 */
	public void setFuncionarioHabilitadoManager(FuncionarioHabilitadoManager funcionarioHabilitadoManager) {
		this.funcionarioHabilitadoManager = funcionarioHabilitadoManager;
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
	public void setSolicitudCcaaManager(SolicitudCcaaManager solicitudCcaaManager) {
		this.solicitudCcaaManager = solicitudCcaaManager;
	}
	
	
}
