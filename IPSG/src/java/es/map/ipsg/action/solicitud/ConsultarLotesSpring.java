package es.map.ipsg.action.solicitud;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import es.map.ips.common.exception.BusinessException;
import es.map.ips.common.model.OrderType;
import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.ApplicationContextProvider;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.MotivoReduccionTasaQuery;
import es.map.ips.model.PagoSolicitudQuery;
import es.map.ips.model.SolComunRegistradasViewQuery;
import es.map.ips.model.SolicitudCcaaQuery;
import es.map.ips.model.SolicitudComun;
import es.map.ips.model.SolicitudComunQuery;
import es.map.ips.model.TipoConsulta;
import es.map.ips.model.TipoConsultaQuery;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.bean.BatchIntermediacionBean;
import es.map.ipsg.bean.BatchNivelRentaBean;
import es.map.ipsg.bean.InformacionLotesBean;
import es.map.ipsg.bean.PagoSolicitudBean;
import es.map.ipsg.bean.SolicitudBean;
import es.map.ipsg.bean.SolicitudCcaaBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.form.BuscarSolicitudesForm;
import es.map.ipsg.manager.BatchIntermediacionManager;
import es.map.ipsg.manager.BatchNivelRentaManager;
import es.map.ipsg.manager.DatosDesempleoManager;
import es.map.ipsg.manager.DatosDiscapacidadManager;
import es.map.ipsg.manager.DatosFNumerosaManager;
import es.map.ipsg.manager.DatosVictimasTerrorismoManager;
import es.map.ipsg.manager.PagoSolicitudManager;
import es.map.ipsg.manager.SolicitudCcaaManager;
import es.map.ipsg.manager.SolicitudesRegistradasManager;
import es.map.ipsg.manager.TipoConsultaManager;
import es.map.ipsg.manager.UsuarioManager;
import es.map.ipsg.util.Constantes;
import es.redsara.intermediacion.scsp.esquemas.ws.peticion.Consentimiento;
import es.redsara.intermediacion.scsp.esquemas.ws.peticion.Funcionario;
import es.redsara.intermediacion.scsp.esquemas.ws.peticion.Procedimiento;
import es.redsara.intermediacion.scsp.esquemas.ws.peticion.Solicitante;

/**
 * El Class ConsultarLotesSpring.
 */
public class ConsultarLotesSpring extends AbstractSpring {
	
	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** La constante MESSAGE_INFO. */
	private static final String MESSAGE_INFO = "ExceptionResources";
	
	/** La constante RESOURCE_INFO. */
	private static final ResourceBundle RESOURCE_INFO = ResourceBundle.getBundle(MESSAGE_INFO);
	
	/** La constante STRING_ERROR. */
	private static final String STRING_ERROR = "error";
	//private static final String PATTERN_DATE = "dd/MM/yyyy";

	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(ConsultarLotesSpring.class);
	
	/** el properties. */
	private Properties properties;
	
	/** el solicitud manager. */
	private SolicitudesRegistradasManager solicitudManager;
	
	/** el solicitud ccaa manager. */
	private SolicitudCcaaManager solicitudCcaaManager;
	
	/** el pago solicitud manager. */
	private PagoSolicitudManager pagoSolicitudManager;
	
	/** el datos F numerosa manager. */
	private DatosFNumerosaManager datosFNumerosaManager;
	
	/** el datos discapacidad manager. */
	private DatosDiscapacidadManager datosDiscapacidadManager;
	
	/** el datos desempleo manager. */
	private DatosDesempleoManager datosDesempleoManager;
	
	/** el usuario manager. */
	private UsuarioManager usuarioManager;
	
	/** el batch intermediacion manager. */
	private BatchIntermediacionManager batchIntermediacionManager;
	
	/** el tipo consulta manager. */
	private TipoConsultaManager tipoConsultaManager;
	
	/** el consulta. */
	private Integer consulta;

	private BatchNivelRentaManager batchNivelRentaManager;
	
	private DatosVictimasTerrorismoManager datosVictimasTerrorismoManager;

	
	/**
	 * Crea una nueva consultar lotes spring.
	 */
	public ConsultarLotesSpring() {
		try{
			setSolicitudManager((SolicitudesRegistradasManager) getBean("solicitudesRegistradasManager"));
			setSolicitudCcaaManager((SolicitudCcaaManager) getBean("solicitudCcaaManager"));
			setDatosFNumerosaManager((DatosFNumerosaManager) getBean("datosFNumerosaManager"));
			setDatosDiscapacidadManager((DatosDiscapacidadManager) getBean("datosDiscapacidadManager"));
			setDatosDesempleoManager((DatosDesempleoManager) getBean("datosDesempleoManager"));
			setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
			setBatchIntermediacionManager((BatchIntermediacionManager) getBean("batchIntermediacionManager"));
			setBatchNivelRentaManager((BatchNivelRentaManager) getBean("batchNivelRentaManager"));
			setTipoConsultaManager((TipoConsultaManager) getBean("tipoConsultaManager"));
			setPagoSolicitudManager((PagoSolicitudManager) getBean("pagoSolicitudManager"));
			setDatosVictimasTerrorismoManager((DatosVictimasTerrorismoManager) getBean("datosVictimasTerrorismoManager"));
		}catch(Exception e){
			logger.warn(e);
			logger.error("Error ConsultarLotesSpring:",e);
		}
	}

	/**
	 * doExecute  de ConsultarTitulosAction
	 * @param form SpringForm
	 * @return resultado   String
	 * @throws Exception Exception
	 */
	protected String doExecute(SpringForm form) throws Exception {

		//*********** METO EL PARAMETRO QUE ME INDICA EN QUE MENU ESTOY **********
		final ResourceBundle RESOURCE_BUNDLE_2 = ResourceBundle.getBundle(MESSAGE_RESOURCE);	
		String menu_solicitudes = RESOURCE_BUNDLE_2.getString("field.menu.solicitudes");
		String sub_menu_activo = (String) this.getRequest().getSession().getAttribute("subMenuActiva");
		this.getRequest().getSession().setAttribute("pagActiva",menu_solicitudes);
		List<InformacionLotesBean> informacionLotesBean = new ArrayList<InformacionLotesBean>();
		String resultado = null;
		String mensaje = null;
		

		//******************************************************************
		try{
			logger.debug("ConsultaLotes -start");
			properties = (Properties) ApplicationContextProvider.getInstance().getBean("propertiesBean");
			//Tomamos el usuario que se ha logueado
			String sUsernameLogin = recuperarUsuario();
			
			
			if (sUsernameLogin.equals(STRING_ERROR)) {
				return sUsernameLogin;
			}

			//Obtenemos el Usuario que está logueado en la aplicación para insertarlo en el log
			UsuarioQuery usuarioQuery = new UsuarioQuery();
			usuarioQuery.setLogin(sUsernameLogin); //Le pasamos el login del que está logueado

			UsuarioBean usuarioBean = usuarioManager.buscarUsuario(usuarioQuery);
			
			//Cogemos el form del jsp
			BuscarSolicitudesForm formulario;
			formulario = (BuscarSolicitudesForm) form;
			String checkBox = formulario.getMarcarTodo();
			
			//Generamos la parte comun que va a ser igual en cada una de las peticiones
			
			Funcionario funcionario = new Funcionario();
			funcionario.setNifFuncionario(usuarioBean.getNif());
			funcionario.setNombreCompletoFuncionario(usuarioBean.getNombreCompleto().toUpperCase());			
			
			Solicitante solicitante = new Solicitante();
			solicitante.setIdentificadorSolicitante(properties.getProperty("SVTO_IDENTIFICADOR_SOLICITANTE"));
			solicitante.setNombreSolicitante(properties.getProperty("SVTO_NOMBRE_SOLICITANTE"));
			solicitante.setUnidadTramitadora(properties.getProperty("SVTO_UNIDAD_TRAMITADORA"));
			solicitante.setFinalidad(properties.getProperty("SVTO_FINALIDAD"));
			solicitante.setConsentimiento(Consentimiento.fromString(properties.getProperty("SVTO_CONSENTIMIENTO")));
			solicitante.setFuncionario(funcionario);
			
			
			Procedimiento procedimiento = new Procedimiento();
			procedimiento.setCodProcedimiento(properties.getProperty("SVTO_COD_PROCEDIMIENTO"));
			procedimiento.setNombreProcedimiento(properties.getProperty("SVTO_NOMBRE_PROCEDIMIENTO"));
			solicitante.setProcedimiento(procedimiento);

			// Recogemos todos los códigos de certificado de todas las consultas
			String codigoFamiliaNumerosa = properties.getProperty("CCAAFN_CODIGO_CERTIFICADO");
			String codigoDiscapacidad = properties.getProperty("CCAADD_CODIGO_CERTIFICADO");
			String codigoDesempleoDias = properties.getProperty("SVDSEPE_CODIGO_CERTIFICADO");
			String codigoVictimas = properties.getProperty("VICTIMAS_CODIGO_CERTIFICADO");
										
			// INI - CPL - IPS-146 - Ordenacion
			String campoOrden =null;
			if(formulario.getCampo()!= null){
			
				try{
					campoOrden=cargarCampos(formulario.getCampo());
				
			// FIN - CPL - IPS-146 - Ordenacion	
				}catch(Exception e){
					logger.error("Error BuscarSolicitudesRegistradasSpring - campoOrden"+ campoOrden,e);
				}			
			}
			// FamiliaNumerosa
			// saco las solicitudes checkeadas por el funcionario para la consulta por lotes, relacionados con la familia numerosa
			List<SolicitudCcaaBean> listaSolicitudesCcaaFNumerosa = crearListaSolicitudesFNumerosa(formulario);
			
			
			// si dicha lista no es null y tiene elementos ...
			if(null != listaSolicitudesCcaaFNumerosa && listaSolicitudesCcaaFNumerosa.size()>0){
				
				// hago una lista de solicitudes genéricas 
				List<SolicitudBean> listaSolicitudesFNumerosa = solicitudManager.buscarSolicitudes(listaSolicitudesCcaaFNumerosa);
				int numElementosFnumerosa = listaSolicitudesFNumerosa.size();
				
				// consumo el web service de familia numerosa , devolviendome un idpeticionfamilia numerosa
				consulta = Constantes.CONSULTA_FNUMEROSA;
				String famNumerosa = datosFNumerosaManager.consultarFNumerosaAsincrona(funcionario,procedimiento,solicitante,listaSolicitudesFNumerosa,listaSolicitudesCcaaFNumerosa,codigoFamiliaNumerosa);			
				// guardo un "ticket"
				if(!StringUtils.isEmpty(famNumerosa)){				
					guardarBatchIntermediacion(famNumerosa,codigoFamiliaNumerosa,usuarioBean,numElementosFnumerosa);
					guardarInformacionLotes(informacionLotesBean,famNumerosa, consulta);
					//Si no falla el WS se pone en estado Ptd
					datosFNumerosaManager.guardarEstadoPendienteList(listaSolicitudesFNumerosa);
				} else {
					//Si falla el WS se pone en estado Ptd (Error al comprobar)
					datosFNumerosaManager.guardarEstadoFalloWsListado(listaSolicitudesFNumerosa);
					logger.error("Se lanza la excepcion de error por consulta por FNumerosa.");
					throw new BusinessException();
				}
							
			}
				
			// Discapacidad
			List<SolicitudCcaaBean> listaSolicitudesCcaaDiscapacidad = crearListaSolicitudesDiscapacidad(formulario);
			
			
			if(null != listaSolicitudesCcaaDiscapacidad && listaSolicitudesCcaaDiscapacidad.size()>0){
				List<SolicitudBean> listaSolicitudesDiscapacidad = solicitudManager.buscarSolicitudes(listaSolicitudesCcaaDiscapacidad);
				int numElementosDiscapacidad = listaSolicitudesDiscapacidad.size();
				consulta = Constantes.CONSULTA_DISCAPACIDAD;
				String discapacidad = datosDiscapacidadManager.consultarDiscapacidadAsincrona(funcionario,procedimiento,solicitante,listaSolicitudesDiscapacidad,listaSolicitudesCcaaDiscapacidad,codigoDiscapacidad);			
				if(!StringUtils.isEmpty(discapacidad)){				
					guardarBatchIntermediacion(discapacidad,codigoDiscapacidad,usuarioBean, numElementosDiscapacidad);
					guardarInformacionLotes(informacionLotesBean,discapacidad,consulta);
					//Si no falla el WS se pone en estado Ptd
					datosDiscapacidadManager.guardarEstadoPendienteList(listaSolicitudesDiscapacidad);
				} else {
					//Si falla el WS se pone en estado Ptd (Error al comprobar)
					datosDiscapacidadManager.guardarEstadoFalloWsLista(listaSolicitudesDiscapacidad);
					logger.error("Se lanza la excepcion de error por consulta por discapacidad.");
					throw new BusinessException();
				}
				
			}
			
			// Desempleo
			SolicitudComunQuery solicitudQueryDesempleo = crearQueryDesempleo(formulario, Integer.valueOf(Constantes.MOTIVO_DESEMPLEO));	
			if(null != solicitudQueryDesempleo){
				List<SolicitudBean> listaSolicitudesDesempleo = solicitudManager.buscarSolicitudAll(solicitudQueryDesempleo);
				int numElementosDesempleo = listaSolicitudesDesempleo.size();

				if(null != listaSolicitudesDesempleo && listaSolicitudesDesempleo.size()>0){
					boolean errorDesempleo = false;
					consulta = Constantes.CONSULTA_DESEMPLEO_DIAS;			
					String desempleoDias = datosDesempleoManager.consultarDesempleoDiasAsincrona(funcionario,procedimiento,solicitante,listaSolicitudesDesempleo,codigoDesempleoDias);			
					if(!StringUtils.isEmpty(desempleoDias)){				
						guardarBatchIntermediacion(desempleoDias,codigoDesempleoDias,usuarioBean, numElementosDesempleo);
						guardarInformacionLotes(informacionLotesBean,desempleoDias,consulta);

					} else {
						errorDesempleo = true;
					}

					//PROCESO IMPORTE NIVEL RENTA INI
					Integer idConsulta = guardarBatchNivelRenta(usuarioBean, listaSolicitudesDesempleo);
					
					if (idConsulta != null && idConsulta > 0) {
						consulta = Constantes.CONSULTA_DESEMPLEO_IMPORTE;
						String codConsultaNV = properties.getProperty("SVDSEPE_COD_CONSULTA")+idConsulta;
						guardarInformacionLotes(informacionLotesBean,codConsultaNV,consulta);
					} else {
						errorDesempleo = true;
					}
					
					if (errorDesempleo) {
						//Si falla el WS se pone en estado Ptd (Error al comprobar)
						datosDesempleoManager.guardarEstadoFalloWs(listaSolicitudesDesempleo);
						logger.error("Se lanza la excepcion de error por consulta por desempleo.");
						throw new BusinessException();
					}
				//PROCESO IMPORTE NIVEL RENTA FIN
				}

			}
			
			//Victimas Terrorismo
			SolicitudComunQuery solicitudQueryVictimas = crearQueryDesempleo(formulario, Integer.valueOf(Constantes.MOTIVO_VICTIMATERRORISMO));
			if(solicitudQueryVictimas != null) {
				List<SolicitudBean> listaSolicitudesVictimas = solicitudManager.buscarSolicitudAll(solicitudQueryVictimas);
				
				if (listaSolicitudesVictimas != null && listaSolicitudesVictimas.size() > 0) {
					int numElementosDiscapacidad = listaSolicitudesVictimas.size();
					String victimas = datosVictimasTerrorismoManager.consultarVictimasAsincrona(procedimiento, solicitante, listaSolicitudesVictimas, codigoVictimas);
					if(!StringUtils.isEmpty(victimas)){		
						consulta = Constantes.CONSULTA_VICTIMAS;
						guardarBatchIntermediacion(victimas,codigoVictimas,usuarioBean, numElementosDiscapacidad);
						guardarInformacionLotes(informacionLotesBean,victimas,consulta);
						//Si no falla el WS se pone en estado Ptd
						datosVictimasTerrorismoManager.guardarEstadoPendienteList(listaSolicitudesVictimas);
					} else {
						//Si falla el WS se pone en estado Ptd (Error al comprobar)
						datosVictimasTerrorismoManager.guardarEstadoFalloWsLista(listaSolicitudesVictimas);
						logger.error("Se lanza la excepcion de error por consulta por victimas terrorismo.");
						throw new BusinessException();
					}
				}
			}
			
		if(informacionLotesBean.size()==0){
			InformacionLotesBean informacionLoteBean = new InformacionLotesBean();
			informacionLoteBean.setIdPeticion("N/A");
			informacionLoteBean.setDescripcionConsulta(RESOURCE_BUNDLE.getString("declaracion.consultaLotes.SinConsulta"));
			informacionLotesBean.add(informacionLoteBean);
			setRequestAttribute("informacionLotesBean", informacionLotesBean);
		}else{
			setRequestAttribute("informacionLotesBean", informacionLotesBean);
		}
		
		if(sub_menu_activo.equals("Solicitudes Registradas")){
			
			resultado = "success";
		}else if(sub_menu_activo.equals("Solicitudes con Incidencias")){
			resultado = "successInci";
		}else {
			resultado = "success";
		}
		
			
						
		}catch (BusinessException ex) {
			logger.warn(ex);
			logger.error("Error ConsultarLotesSpring - error conexion:" ,ex);
			resultado=STRING_ERROR;
			mensaje = RESOURCE_INFO.getString("intermediacion.svdi.errorConexion");
			setRequestAttribute("descripcionError",mensaje);
			guardarInformacionLotes(informacionLotesBean,null,consulta);
		}catch (Exception ex){
			logger.warn(ex);
			logger.error("Error ConsultarLotesSpring:" ,ex);
			resultado = STRING_ERROR;
			mensaje = RESOURCE_BUNDLE.getString("field.errorGenerico");
			setRequestAttribute("descripcionError",mensaje);
			guardarInformacionLotes(informacionLotesBean,null,consulta);
		}
		
		logger.debug("ConsultaLotes -end");
		
		return resultado;

	}

	private Integer guardarBatchNivelRenta(UsuarioBean usuarioBean, List<SolicitudBean> listaSolicitudesDesempleo) {
		BatchNivelRentaBean batchNivelRentaBean = new BatchNivelRentaBean();
		ArrayList<SolicitudComun> listaSolicitudes = new ArrayList<SolicitudComun>();
		for (SolicitudBean solicitudBean : listaSolicitudesDesempleo) {
			listaSolicitudes.add(solicitudManager.toSolicitud(solicitudBean));
		}
		batchNivelRentaBean.setSolicitudes(listaSolicitudes);
		batchNivelRentaBean.setReintentos(0);
		batchNivelRentaBean.setNumElementos(listaSolicitudes.size());
		batchNivelRentaBean.setIdUsuarioConsulta(usuarioManager.toUsuario(usuarioBean));
		batchNivelRentaBean.setFhInicioConsulta(new Date());
		batchNivelRentaBean.setProcesado(false);
		
		return batchNivelRentaManager.guardarBatchNivelRenta(batchNivelRentaBean);
	}
	
	/**
	 * Recuperar usuario.
	 *
	 * @return el string
	 */
	private String recuperarUsuario() {
		String sUsernameLogin = "";
		try{
			UsuarioBean usuarioBean = (UsuarioBean) getRequest().getSession().getAttribute("usuario");
			sUsernameLogin = usuarioBean.getLogin();
			return sUsernameLogin;
		}catch(Exception e){
			logger.error("Error ConsultarLotesSpring - recuperarUsuarioSesion:"+ sUsernameLogin ,e);
			new BusinessException("exception.recuperarUsuarioSesion");
			return STRING_ERROR;
		}
	}

	/**
	 * Guardar informacion lotes.
	 *
	 * @param informacionLotesBean el informacion lotes bean
	 * @param idPeticion el id peticion
	 * @param idTipoConsulta el id tipo consulta
	 */
	private void guardarInformacionLotes(
			List<InformacionLotesBean> informacionLotesBean,
			String idPeticion, Integer idTipoConsulta) {
		

		TipoConsultaQuery tipoConsultaQuery = new TipoConsultaQuery();
		tipoConsultaQuery.setIdTipoConsulta(idTipoConsulta);
		TipoConsulta tipoConsulta = tipoConsultaManager.buscarTipoConsulta(tipoConsultaQuery);
		
		InformacionLotesBean infoLotesBean = new InformacionLotesBean();
		
		infoLotesBean.setIdPeticion(idPeticion);
		infoLotesBean.setDescripcionConsulta(tipoConsulta.getDescripcion());
		
		informacionLotesBean.add(infoLotesBean);
				
		// TODO Auto-generated method stub
		
	}

	/**
	 * Guardar batch intermediacion.
	 *
	 * @param idPeticion el id peticion
	 * @param codigoCertificado el codigo certificado
	 * @param usuarioBean el usuario bean
	 * @param numElementos el num elementos
	 */
	private void guardarBatchIntermediacion(String idPeticion, String codigoCertificado, UsuarioBean usuarioBean, int numElementos) {
		
		
		TipoConsultaQuery tipoConsultaQuery = new TipoConsultaQuery();
		tipoConsultaQuery.setCodConsulta(codigoCertificado);
		TipoConsulta idTipoConsulta = tipoConsultaManager.buscarTipoConsulta(tipoConsultaQuery);
				
		BatchIntermediacionBean batchIntermediacionBean = new BatchIntermediacionBean();
		batchIntermediacionBean.setIdPeticion(idPeticion);
		batchIntermediacionBean.setIdTipoConsulta(idTipoConsulta);
		batchIntermediacionBean.setFhInicioConsulta(new Date());
		batchIntermediacionBean.setIdUsuarioConsulta(usuarioManager.toUsuario(usuarioBean));
		batchIntermediacionBean.setRespuesta(false);
		batchIntermediacionBean.setReintentos(0);
		batchIntermediacionBean.setNumElementos(numElementos);
		
		batchIntermediacionManager.guardarBatchIntermediacion(batchIntermediacionBean);
		
		
		// TODO Auto-generated method stub
		
	}

	/**
	 * Método que genera un listado de solicitudes con familia numerosa marcado.
	 *
	 * @param form - Formulario de búsqueda de solicitudes
	 * @return - Listado de solicitudes de familia numerosa de CCAA
	 */
	private List<SolicitudCcaaBean> crearListaSolicitudesFNumerosa(BuscarSolicitudesForm form){
		
	SolicitudComunQuery solicitudComunQuery = new SolicitudComunQuery();
	SolicitudComunQuery solicitudComunQuery2 = new SolicitudComunQuery();
	SolicitudCcaaQuery solicitudCcaaQuery = new SolicitudCcaaQuery();
	PagoSolicitudQuery pagoSolicitudQuery = new PagoSolicitudQuery();
	ArrayList<SolicitudCcaaBean> listaSolicitudCcaaBean = new ArrayList<SolicitudCcaaBean>();
	MotivoReduccionTasaQuery motivoReduccionTasaQuery = new MotivoReduccionTasaQuery();
	
		if ("TodoOk".contentEquals(form.getMarcarTodo())) {
			String[] idSolicitudes = form.getSolicitudesSel();
			
			for (String idSolicitud : idSolicitudes) {
				solicitudComunQuery.addIdSolicitudIn(Long.parseLong(idSolicitud));
			}
		}

		solicitudComunQuery.setIdConsentimiento(true);
				
		motivoReduccionTasaQuery.addIdIn(Integer.valueOf(Constantes.MOTIVO_FNUMEROSAGENERAL));
		motivoReduccionTasaQuery.addIdIn(Integer.valueOf(Constantes.MOTIVO_FNUMEROSAESPECIAL));
		
		pagoSolicitudQuery.setSolicitudComun(solicitudComunQuery);
		pagoSolicitudQuery.setMotivoReduccionTasa(motivoReduccionTasaQuery);
		
		
		List<PagoSolicitudBean> lista = pagoSolicitudManager.buscarPagoSolicitudAll(pagoSolicitudQuery);
		
		for (PagoSolicitudBean pagoSolicitudBean : lista) {
			
			solicitudComunQuery2.addIdSolicitudIn(pagoSolicitudBean.getIdSolicitud());
			
		}
		
		if(null != lista && lista.size()>0){
		
		solicitudComunQuery2.addOrder(SolicitudComunQuery.IDSOLICITUD,OrderType.ASC);
		solicitudCcaaQuery.setSolicitudComun(solicitudComunQuery2);
		solicitudCcaaQuery.setComunidadesIsNotNull(true);
		
		listaSolicitudCcaaBean=solicitudCcaaManager.buscarSolicitudAll(solicitudCcaaQuery);
		
		}

		return listaSolicitudCcaaBean;
	}
	
	
	/**
	 * Crear lista solicitudes discapacidad.
	 *
	 * @param form el form
	 * @return el list
	 */
	private List<SolicitudCcaaBean> crearListaSolicitudesDiscapacidad(BuscarSolicitudesForm form){
		
		SolicitudComunQuery solicitudComunQuery = new SolicitudComunQuery();
		SolicitudComunQuery solicitudComunQuery2 = new SolicitudComunQuery();
		SolicitudCcaaQuery solicitudCcaaQuery = new SolicitudCcaaQuery();
		PagoSolicitudQuery pagoSolicitudQuery = new PagoSolicitudQuery();
		MotivoReduccionTasaQuery motivoReduccionTasaQuery = new MotivoReduccionTasaQuery();
		ArrayList<SolicitudCcaaBean> listaSolicitudCcaaBean = new ArrayList<SolicitudCcaaBean>();
		
		if ("TodoOk".contentEquals(form.getMarcarTodo())) {
			String[] idSolicitudes = form.getSolicitudesSel();
			
			for (String idSolicitud : idSolicitudes) {
				
				solicitudComunQuery.addIdSolicitudIn(Long.parseLong(idSolicitud));
			}
		}
			solicitudComunQuery.setIdConsentimiento(true);
					
			motivoReduccionTasaQuery.addIdIn(Integer.valueOf(Constantes.MOTIVO_DISCAPACIDAD));
			
			pagoSolicitudQuery.setSolicitudComun(solicitudComunQuery);
			pagoSolicitudQuery.setMotivoReduccionTasa(motivoReduccionTasaQuery);
			
			
			List<PagoSolicitudBean> lista = pagoSolicitudManager.buscarPagoSolicitudAll(pagoSolicitudQuery);
			
			for (PagoSolicitudBean pagoSolicitudBean : lista) {
				
				solicitudComunQuery2.addIdSolicitudIn(pagoSolicitudBean.getIdSolicitud());
				
			}
			
			if(null != lista && lista.size()>0){
				
				solicitudComunQuery2.addOrder(SolicitudComunQuery.IDSOLICITUD,OrderType.ASC);
				solicitudCcaaQuery.setSolicitudComun(solicitudComunQuery2);
				solicitudCcaaQuery.setComunidadesIsNotNull(true);
				
				listaSolicitudCcaaBean=solicitudCcaaManager.buscarSolicitudAll(solicitudCcaaQuery);
				
				}


			return listaSolicitudCcaaBean;
		}
	
	
	/**
	 * Crear query desempleo.
	 *
	 * @param form el form
	 * @return el solicitud comun query
	 */
	private SolicitudComunQuery crearQueryDesempleo(BuscarSolicitudesForm form, Integer motivo){
		
		SolicitudComunQuery solicitudComunQuery = new SolicitudComunQuery();
		SolicitudComunQuery solicitudComunQuery2 = new SolicitudComunQuery();
		SolicitudComunQuery resultado = null;
		PagoSolicitudQuery pagoSolicitudQuery = new PagoSolicitudQuery();
		MotivoReduccionTasaQuery motivoReduccionTasaQuery = new MotivoReduccionTasaQuery();
		if ("TodoOk".contentEquals(form.getMarcarTodo())) {	
			String[] idSolicitudes = form.getSolicitudesSel();
			
			for (String idSolicitud : idSolicitudes) {
				
				solicitudComunQuery.addIdSolicitudIn(Long.parseLong(idSolicitud));
			}
		}
			solicitudComunQuery.setIdConsentimiento(true);
					
			motivoReduccionTasaQuery.addIdIn(motivo);
			
			pagoSolicitudQuery.setSolicitudComun(solicitudComunQuery);
			pagoSolicitudQuery.setMotivoReduccionTasa(motivoReduccionTasaQuery);
			
			
			List<PagoSolicitudBean> lista = pagoSolicitudManager.buscarPagoSolicitudAll(pagoSolicitudQuery);
			
			if (null != lista && !lista.isEmpty()){

				for (PagoSolicitudBean pagoSolicitudBean : lista) {

					solicitudComunQuery2.addIdSolicitudIn(pagoSolicitudBean.getIdSolicitud());

				}
				solicitudComunQuery2.addOrder(SolicitudComunQuery.IDSOLICITUD,OrderType.ASC);
				
				resultado = solicitudComunQuery2;
			}
				
			
			return resultado;
		}
		
	/**
	 * Cargar campos.
	 *
	 * @param campo el campo
	 * @return el string
	 */
	private String cargarCampos(String campo) {
		String auxCampo=null;
		int codCampo = 0;
		
		
		if (campo != null && !"".equals(campo) && !campo.equals("null")){

			try{
				codCampo = Integer.parseInt(campo);
			}catch(Exception e){
				logger.error("Error BuscarSolicitudesRegistradasSpring - cargarcampos - parsear campo"+ codCampo,e);
			}
				switch(codCampo){
				//Los campos de ordenacion del jsp
					case 1:auxCampo = SolComunRegistradasViewQuery.NUMEROSOLICITUD;break;
					case 2:auxCampo = SolComunRegistradasViewQuery.NIF;break;
					case 3:auxCampo = SolComunRegistradasViewQuery.NOMBRE;break;
					case 4:auxCampo = SolComunRegistradasViewQuery.PRIMERAPELLIDO;break;	
					case 5:auxCampo = SolComunRegistradasViewQuery.SEGUNDOAPELLIDO;break;
					case 6:auxCampo = SolComunRegistradasViewQuery.EJERCICIOCONVOCATORIA;break;
					case 7:auxCampo = SolComunRegistradasViewQuery.IDCENTROGESTOR;break;
					case 8:auxCampo = SolComunRegistradasViewQuery.IDCONVOCATORIA;break;
					case 9:auxCampo = SolComunRegistradasViewQuery.DESCTIPOSOLICITUD;break;
					case 10:auxCampo = SolComunRegistradasViewQuery.IDCONSENTIMIENTO;break;
					case 11:auxCampo = SolComunRegistradasViewQuery.EDADVERIFICADA;break;
					case 12:auxCampo = SolComunRegistradasViewQuery.FECHANACIMIENTOVERIFICADA;break;
					case 13:auxCampo = SolComunRegistradasViewQuery.TITULOVERIFICADO;break;
					case 14:auxCampo = SolComunRegistradasViewQuery.DESEMPLEOVERIFICADO;break;
					case 15:auxCampo = SolComunRegistradasViewQuery.IDESPECIALIDAD;break;
					case 16:auxCampo = SolComunRegistradasViewQuery.FNUMEROSAVERIFICADO;break;
					case 17:auxCampo = SolComunRegistradasViewQuery.DISCAPACIDADVERIFICADO;break;
					default:break;
					}
				
			}
		return auxCampo;
	}	
	
	

	/**
	 * Obtiene el solicitud manager.
	 *
	 * @return el solicitud manager
	 */
	public SolicitudesRegistradasManager getSolicitudManager() {
		return solicitudManager;
	}

	/**
	 * Establece el solicitud manager.
	 *
	 * @param solicitudManager el nuevo solicitud manager
	 */
	public void setSolicitudManager(SolicitudesRegistradasManager solicitudManager) {
		this.solicitudManager = solicitudManager;
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
	 * Obtiene el batch intermediacion manager.
	 *
	 * @return el batch intermediacion manager
	 */
	public BatchIntermediacionManager getBatchIntermediacionManager() {
		return batchIntermediacionManager;
	}

	/**
	 * Establece el batch intermediacion manager.
	 *
	 * @param batchIntermediacionManager el nuevo batch intermediacion manager
	 */
	public void setBatchIntermediacionManager(
			BatchIntermediacionManager batchIntermediacionManager) {
		this.batchIntermediacionManager = batchIntermediacionManager;
	}

	/**
	 * Obtiene el tipo consulta manager.
	 *
	 * @return el tipo consulta manager
	 */
	public TipoConsultaManager getTipoConsultaManager() {
		return tipoConsultaManager;
	}

	/**
	 * Establece el tipo consulta manager.
	 *
	 * @param tipoConsultaManager el nuevo tipo consulta manager
	 */
	public void setTipoConsultaManager(TipoConsultaManager tipoConsultaManager) {
		this.tipoConsultaManager = tipoConsultaManager;
	}

	/**
	 * Obtiene el datos F numerosa manager.
	 *
	 * @return el datos F numerosa manager
	 */
	public DatosFNumerosaManager getDatosFNumerosaManager() {
		return datosFNumerosaManager;
	}

	/**
	 * Establece el datos F numerosa manager.
	 *
	 * @param datosFNumerosaManager el nuevo datos F numerosa manager
	 */
	public void setDatosFNumerosaManager(DatosFNumerosaManager datosFNumerosaManager) {
		this.datosFNumerosaManager = datosFNumerosaManager;
	}

	/**
	 * Obtiene el pago solicitud manager.
	 *
	 * @return el pago solicitud manager
	 */
	public PagoSolicitudManager getPagoSolicitudManager() {
		return pagoSolicitudManager;
	}

	/**
	 * Establece el pago solicitud manager.
	 *
	 * @param pagoSolicitudManager el nuevo pago solicitud manager
	 */
	public void setPagoSolicitudManager(PagoSolicitudManager pagoSolicitudManager) {
		this.pagoSolicitudManager = pagoSolicitudManager;
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

	/**
	 * Obtiene el datos discapacidad manager.
	 *
	 * @return el datos discapacidad manager
	 */
	public DatosDiscapacidadManager getDatosDiscapacidadManager() {
		return datosDiscapacidadManager;
	}

	/**
	 * Establece el datos discapacidad manager.
	 *
	 * @param datosDiscapacidadManager el nuevo datos discapacidad manager
	 */
	public void setDatosDiscapacidadManager(
			DatosDiscapacidadManager datosDiscapacidadManager) {
		this.datosDiscapacidadManager = datosDiscapacidadManager;
	}

	/**
	 * Obtiene el datos desempleo manager.
	 *
	 * @return el datos desempleo manager
	 */
	public DatosDesempleoManager getDatosDesempleoManager() {
		return datosDesempleoManager;
	}

	/**
	 * Establece el datos desempleo manager.
	 *
	 * @param datosDesempleoManager el nuevo datos desempleo manager
	 */
	public void setDatosDesempleoManager(DatosDesempleoManager datosDesempleoManager) {
		this.datosDesempleoManager = datosDesempleoManager;
	}

	public BatchNivelRentaManager getBatchNivelRentaManager() {
		return batchNivelRentaManager;
	}

	public void setBatchNivelRentaManager(BatchNivelRentaManager batchNivelRentaManager) {
		this.batchNivelRentaManager = batchNivelRentaManager;
	}

	public DatosVictimasTerrorismoManager getDatosVictimasTerrorismoManager() {
		return datosVictimasTerrorismoManager;
	}

	public void setDatosVictimasTerrorismoManager(DatosVictimasTerrorismoManager datosVictimasTerrorismoManager) {
		this.datosVictimasTerrorismoManager = datosVictimasTerrorismoManager;
	}

}