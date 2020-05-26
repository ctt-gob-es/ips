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
import es.map.ips.model.SolComunRegistradasViewQuery;
import es.map.ips.model.SolicitudCcaaAuxiliarQuery;
import es.map.ips.model.SolicitudComunAuxiliar;
import es.map.ips.model.SolicitudComunAuxiliarQuery;
import es.map.ips.model.TipoConsulta;
import es.map.ips.model.TipoConsultaQuery;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.bean.BatchIntermediacionBean;
import es.map.ipsg.bean.BatchNivelRentaAuxBean;
import es.map.ipsg.bean.InformacionLotesBean;
import es.map.ipsg.bean.SolicitudBean;
import es.map.ipsg.bean.SolicitudCcaaBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.form.BuscarSolicitudesForm;
import es.map.ipsg.manager.BatchIntermediacionAuxManager;
import es.map.ipsg.manager.BatchNivelRentaAuxManager;
import es.map.ipsg.manager.DatosDesempleoManager;
import es.map.ipsg.manager.DatosDiscapacidadManager;
import es.map.ipsg.manager.DatosFNumerosaManager;
import es.map.ipsg.manager.DatosVictimasTerrorismoManager;
import es.map.ipsg.manager.SolicitudCcaaAuxiliarManager;
import es.map.ipsg.manager.SolicitudComunAuxiliarManager;
import es.map.ipsg.manager.TipoConsultaManager;
import es.map.ipsg.manager.UsuarioManager;
import es.map.ipsg.util.Constantes;
import es.redsara.intermediacion.scsp.esquemas.ws.peticion.Consentimiento;
import es.redsara.intermediacion.scsp.esquemas.ws.peticion.Funcionario;
import es.redsara.intermediacion.scsp.esquemas.ws.peticion.Procedimiento;
import es.redsara.intermediacion.scsp.esquemas.ws.peticion.Solicitante;

/**
 * El Class ConsultarLotesAuxSpring.
 */
public class ConsultarLotesAuxSpring extends AbstractSpring {
	
	/** La constante DESEMPLEO. */
	private static final String DESEMPLEO = "DESEMPLEO";
	
	/** La constante DISCAPACIDAD. */
	private static final String DISCAPACIDAD = "DISCAPACIDAD";
	
	/** La constante FN. */
	private static final String FN = "FN";
	
	/** La constante DISCAPACIDAD. */
	private static final String VVTERRORISMO = "VVTERRORISMO";
	
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

	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(ConsultarLotesAuxSpring.class);
	
	/** el properties. */
	private Properties properties;
	
	/** el solicitud ccaa aux manager. */
	private SolicitudCcaaAuxiliarManager solicitudCcaaAuxManager;
	
	/** el solicitud comun aux manager. */
	private SolicitudComunAuxiliarManager solicitudComunAuxManager;
	
	/** el datos F numerosa manager. */
	private DatosFNumerosaManager datosFNumerosaManager;
	
	/** el datos discapacidad manager. */
	private DatosDiscapacidadManager datosDiscapacidadManager;
	
	/** el datos desempleo manager. */
	private DatosDesempleoManager datosDesempleoManager;
	
	/** el usuario manager. */
	private UsuarioManager usuarioManager;
	
	/** el batch intermediacion aux manager. */
	private BatchIntermediacionAuxManager batchIntermediacionAuxManager;
	
	/** el tipo consulta manager. */
	private TipoConsultaManager tipoConsultaManager;
	
	private BatchNivelRentaAuxManager batchNivelRentaAuxManager;
	
	/** el consulta. */
	private Integer consulta;

	private DatosVictimasTerrorismoManager datosVictimasTerrorismoManager;


	
	/**
	 * Crea una nueva consultar lotes aux spring.
	 */
	public ConsultarLotesAuxSpring() {
		try{
			setSolicitudComunAuxManager((SolicitudComunAuxiliarManager) getBean("solicitudComunAuxiliarManager"));
			setSolicitudCcaaAuxManager((SolicitudCcaaAuxiliarManager) getBean("solicitudCcaaAuxiliarManager"));
			setDatosFNumerosaManager((DatosFNumerosaManager) getBean("datosFNumerosaManager"));
			setDatosDiscapacidadManager((DatosDiscapacidadManager) getBean("datosDiscapacidadManager"));
			setDatosDesempleoManager((DatosDesempleoManager) getBean("datosDesempleoManager"));
			setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
			setBatchIntermediacionAuxManager((BatchIntermediacionAuxManager) getBean("batchIntermediacionAuxManager"));
			setTipoConsultaManager((TipoConsultaManager) getBean("tipoConsultaManager"));
			setBatchNivelRentaAuxManager((BatchNivelRentaAuxManager) getBean("batchNivelRentaAuxManager"));
			setDatosVictimasTerrorismoManager((DatosVictimasTerrorismoManager) getBean("datosVictimasTerrorismoManager"));
		}catch(Exception e){
			logger.warn(e);
			logger.error("Error ConsultarLotesAuxSpring:",e);
		}
	}

	/**
	 * doExecute  de ConsultarTitulosAction.
	 *
	 * @param form SpringForm
	 * @return resultado   String
	 * @throws Exception Exception
	 */
	protected String doExecute(SpringForm form) throws Exception {

		//*********** METO EL PARAMETRO QUE ME INDICA EN QUE MENU ESTOY **********
		final ResourceBundle RESOURCE_BUNDLE_2 = ResourceBundle.getBundle(MESSAGE_RESOURCE);	
		String menu_solicitudes = RESOURCE_BUNDLE_2.getString("field.menu.solicitudes");
		//String sub_menu_activo = (String) this.getRequest().getSession().getAttribute("subMenuActiva");
		this.getRequest().getSession().setAttribute("pagActiva",menu_solicitudes);
		List<InformacionLotesBean> informacionLotesBean = new ArrayList<InformacionLotesBean>();
		String resultado = null;
		String mensaje = null;
		

		//******************************************************************
		try{
			logger.debug("ConsultaLotesAuxiliar -start");
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
			String codigoVVTerrorismo = properties.getProperty("VICTIMAS_CODIGO_CERTIFICADO");

										
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
			List<SolicitudCcaaBean> listaSolicitudesCcaaFNumerosa = crearListaSolicitudes(formulario, FN);
			
			
			// si dicha lista no es null y tiene elementos ...
			if(null != listaSolicitudesCcaaFNumerosa && listaSolicitudesCcaaFNumerosa.size()>0){
				
				// hago una lista de solicitudes genéricas 
				List<SolicitudBean> listaSolicitudesFNumerosa = solicitudComunAuxManager.buscarSolicitudes(listaSolicitudesCcaaFNumerosa);
				int numElementosFnumerosa = listaSolicitudesFNumerosa.size();
				
				// consumo el web service de familia numerosa , devolviendome un idpeticionfamilia numerosa
				consulta = Constantes.CONSULTA_FNUMEROSA;
				String famNumerosa = datosFNumerosaManager.consultarFNumerosaAsincrona(funcionario,procedimiento,solicitante,listaSolicitudesFNumerosa,listaSolicitudesCcaaFNumerosa,codigoFamiliaNumerosa);			
				// guardo un "ticket"
				if(!StringUtils.isEmpty(famNumerosa)){				
					guardarBatchIntermediacion(famNumerosa,codigoFamiliaNumerosa,usuarioBean,numElementosFnumerosa);
					guardarInformacionLotes(informacionLotesBean,famNumerosa, consulta);
					//Si no falla el WS se pone en estado Ptd
					datosFNumerosaManager.guardarEstadoPendienteListAux(listaSolicitudesFNumerosa);
				} else {
					//Si falla el WS se pone en estado Ptd (Error al comprobar)
					datosFNumerosaManager.guardarEstadoFalloWsAuxListado(listaSolicitudesFNumerosa);
					logger.error("Se lanza la excepcion de error por consulta por FNumerosa.");
					throw new BusinessException();
				}
							
			}
				
			// Discapacidad
			List<SolicitudCcaaBean> listaSolicitudesCcaaDiscapacidad = crearListaSolicitudes(formulario, DISCAPACIDAD);
			
			
			if(null != listaSolicitudesCcaaDiscapacidad && listaSolicitudesCcaaDiscapacidad.size()>0){
				List<SolicitudBean> listaSolicitudesDiscapacidad = solicitudComunAuxManager.buscarSolicitudes(listaSolicitudesCcaaDiscapacidad);
				int numElementosDiscapacidad = listaSolicitudesDiscapacidad.size();
				consulta = Constantes.CONSULTA_DISCAPACIDAD;
				String discapacidad = datosDiscapacidadManager.consultarDiscapacidadAsincrona(funcionario,procedimiento,solicitante,listaSolicitudesDiscapacidad,listaSolicitudesCcaaDiscapacidad,codigoDiscapacidad);			
				if(!StringUtils.isEmpty(discapacidad)){				
					guardarBatchIntermediacion(discapacidad,codigoDiscapacidad,usuarioBean, numElementosDiscapacidad);
					guardarInformacionLotes(informacionLotesBean,discapacidad,consulta);
					//Si no falla el WS se pone en estado Ptd
					datosDiscapacidadManager.guardarEstadoPendienteListAux(listaSolicitudesDiscapacidad);
				} else {
					//Si falla el WS se pone en estado Ptd (Error al comprobar)
					datosDiscapacidadManager.guardarEstadoFalloWsAuxLista(listaSolicitudesDiscapacidad);
					logger.error("Se lanza la excepcion de error por consulta por discapacidad.");
					throw new BusinessException();
				}
				
			}
			
			// Desempleo
			List<SolicitudCcaaBean> listaSolicitudesCcaaDesempleo = crearListaSolicitudes(formulario, DESEMPLEO);
			
			
			if(null != listaSolicitudesCcaaDesempleo && listaSolicitudesCcaaDesempleo.size() > 0){
				List<SolicitudBean> listaSolicitudesDesempleo = solicitudComunAuxManager.buscarSolicitudes(listaSolicitudesCcaaDesempleo);
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
					Integer idConsulta = guardarBatchNivelRentaAux(usuarioBean, listaSolicitudesDesempleo);
					
					if (idConsulta != null && idConsulta > 0) {
						consulta = Constantes.CONSULTA_DESEMPLEO_IMPORTE;
						String codConsultaNV = properties.getProperty("SVDSEPE_COD_CONSULTA")+idConsulta;
						guardarInformacionLotes(informacionLotesBean,codConsultaNV,consulta);
					} else {
						errorDesempleo = true;
					}
					
					if (errorDesempleo) {
						//Si falla el WS se pone en estado Ptd (Error al comprobar)
						datosDesempleoManager.guardarEstadoFalloWsAux(listaSolicitudesDesempleo);
						logger.error("Se lanza la excepcion de error por consulta por desempleo.");
						throw new BusinessException();
					}
				//PROCESO IMPORTE NIVEL RENTA FIN
				}

			}
			
			//Victimas Terrorismo
			
			List<SolicitudCcaaBean> listaSolicitudesVictimas = crearListaSolicitudes(formulario, VVTERRORISMO);
			
			if(null != listaSolicitudesVictimas && listaSolicitudesVictimas.size()>0){
				List<SolicitudBean> listaSolicitudesVVTerrorismo = solicitudComunAuxManager.buscarSolicitudes(listaSolicitudesVictimas);
				int numElementosTerrorismo = listaSolicitudesVVTerrorismo.size();
				consulta = Constantes.CONSULTA_VICTIMAS;
				
				
				String vVTerrorismo = datosVictimasTerrorismoManager.consultarVictimasAsincrona(procedimiento,solicitante,listaSolicitudesVVTerrorismo,codigoVVTerrorismo);			
				if(!StringUtils.isEmpty(vVTerrorismo)){				
					guardarBatchIntermediacion(vVTerrorismo,codigoDiscapacidad,usuarioBean, numElementosTerrorismo);
					guardarInformacionLotes(informacionLotesBean,vVTerrorismo,consulta);
					//Si no falla el WS se pone en estado Ptd
					datosVictimasTerrorismoManager.guardarEstadoPendienteListAux(listaSolicitudesVVTerrorismo);
				} 
			}
			
			
		//Sin no es posible consultar las solicitudes seleccionadas	
		if(informacionLotesBean.size()==0){
			InformacionLotesBean informacionLoteBean = new InformacionLotesBean();
			informacionLoteBean.setIdPeticion("N/A");
			informacionLoteBean.setDescripcionConsulta(RESOURCE_BUNDLE.getString("declaracion.consultaLotes.SinConsulta"));
			informacionLotesBean.add(informacionLoteBean);
			setRequestAttribute("informacionLotesBean", informacionLotesBean);
		}else{
			setRequestAttribute("informacionLotesBean", informacionLotesBean);
		}
		
		resultado = "success";
		
			
						
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
		
		logger.debug("ConsultaLotesAuxiliar -end");
		
		return resultado;

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
		
		batchIntermediacionAuxManager.guardarBatchIntermediacion(batchIntermediacionBean);
		
		
		// TODO Auto-generated method stub
		
	}

	/**
	 * Método que genera un listado de solicitudes con familia numerosa marcado.
	 *
	 * @param form - Formulario de búsqueda de solicitudes
	 * @param tipo el tipo
	 * @return - Listado de solicitudes de familia numerosa de CCAA
	 */
	private List<SolicitudCcaaBean> crearListaSolicitudes(BuscarSolicitudesForm form, String tipo){
		
	SolicitudComunAuxiliarQuery solicitudComunAuxQuery = new SolicitudComunAuxiliarQuery();
	SolicitudCcaaAuxiliarQuery solicitudCcaaAuxQuery = new SolicitudCcaaAuxiliarQuery();

	ArrayList<SolicitudCcaaBean> listaSolicitudCcaaBean = new ArrayList<SolicitudCcaaBean>();
	MotivoReduccionTasaQuery motivoReduccionTasaQuery = new MotivoReduccionTasaQuery();
		
		if ("TodoOk".contentEquals(form.getMarcarTodo())) {
			String[] idSolicitudes = form.getSolicitudesSel();
			
			for (String idSolicitud : idSolicitudes) {
				solicitudComunAuxQuery.addIdSolicitudAuxiliarIn(Long.parseLong(idSolicitud));
			}
		}

		solicitudComunAuxQuery.setIdConsentimiento(true);
		
		if (tipo.equals(FN)) {
			motivoReduccionTasaQuery.addIdIn(Integer.valueOf(Constantes.MOTIVO_FNUMEROSAGENERAL));
			motivoReduccionTasaQuery.addIdIn(Integer.valueOf(Constantes.MOTIVO_FNUMEROSAESPECIAL));
		} else if (tipo.equals(DISCAPACIDAD)) {
			motivoReduccionTasaQuery.addIdIn(Integer.valueOf(Constantes.MOTIVO_DISCAPACIDAD));
		} else if(tipo.equals(DESEMPLEO)){
			motivoReduccionTasaQuery.addIdIn(Integer.valueOf(Constantes.MOTIVO_DESEMPLEO));
		} else {
			motivoReduccionTasaQuery.addIdIn(Integer.valueOf(Constantes.MOTIVO_VICTIMATERRORISMO));
		}

		solicitudComunAuxQuery.setMotivoReduccionTasaQuery(motivoReduccionTasaQuery);
		solicitudComunAuxQuery.addOrder(SolicitudComunAuxiliarQuery.IDSOLICITUDAUXILIAR,OrderType.ASC);
		solicitudComunAuxQuery.setJoin_motivoExencion(true);
		
		List<Long> idSolAuxList = solicitudComunAuxManager.buscarSolicitudComunAuxiliarByFechaSolicitud(solicitudComunAuxQuery);
		
		//solicitudCcaaAuxQuery.setSolicitudComunAuxiliar(solicitudComunAuxQuery);
		
		if (idSolAuxList != null && idSolAuxList.size() > 0) {
			for (Long idAux : idSolAuxList) {
				solicitudCcaaAuxQuery.addSolicitudComunAuxiliarIn(idAux);
			}
			
			//solicitudCcaaAuxQuery.setComunidadesIsNotNull(true);
			
			listaSolicitudCcaaBean=solicitudCcaaAuxManager.buscarSolicitudAll(solicitudCcaaAuxQuery);
		}


		return listaSolicitudCcaaBean;
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
	
	private Integer guardarBatchNivelRentaAux(UsuarioBean usuarioBean, List<SolicitudBean> listaSolicitudesDesempleo) {
		BatchNivelRentaAuxBean batchNivelRentaAuxBean = new BatchNivelRentaAuxBean();
		ArrayList<SolicitudComunAuxiliar> listaSolicitudes = new ArrayList<SolicitudComunAuxiliar>();
		for (SolicitudBean solicitudBean : listaSolicitudesDesempleo) {
			listaSolicitudes.add(solicitudComunAuxManager.toSolicitud(solicitudBean));
		}
		batchNivelRentaAuxBean.setSolicitudesAuxiliares(listaSolicitudes);
		batchNivelRentaAuxBean.setReintentos(0);
		batchNivelRentaAuxBean.setNumElementos(listaSolicitudes.size());
		batchNivelRentaAuxBean.setIdUsuarioConsulta(usuarioManager.toUsuario(usuarioBean));
		batchNivelRentaAuxBean.setFhInicioConsulta(new Date());
		batchNivelRentaAuxBean.setProcesado(false);
		
		return batchNivelRentaAuxManager.guardarBatchNivelRentaAux(batchNivelRentaAuxBean);
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
	 * Obtiene el batch intermediacion aux manager.
	 *
	 * @return el batch intermediacion aux manager
	 */
	public BatchIntermediacionAuxManager getBatchIntermediacionAuxManager() {
		return batchIntermediacionAuxManager;
	}

	/**
	 * Establece el batch intermediacion aux manager.
	 *
	 * @param batchIntermediacionAuxManager el nuevo batch intermediacion aux manager
	 */
	public void setBatchIntermediacionAuxManager(BatchIntermediacionAuxManager batchIntermediacionAuxManager) {
		this.batchIntermediacionAuxManager = batchIntermediacionAuxManager;
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

	/**
	 * Obtiene el solicitud ccaa aux manager.
	 *
	 * @return el solicitud ccaa aux manager
	 */
	public SolicitudCcaaAuxiliarManager getSolicitudCcaaAuxManager() {
		return solicitudCcaaAuxManager;
	}

	/**
	 * Establece el solicitud ccaa aux manager.
	 *
	 * @param solicitudCcaaAuxManager el nuevo solicitud ccaa aux manager
	 */
	public void setSolicitudCcaaAuxManager(SolicitudCcaaAuxiliarManager solicitudCcaaAuxManager) {
		this.solicitudCcaaAuxManager = solicitudCcaaAuxManager;
	}

	/**
	 * Obtiene el solicitud comun aux manager.
	 *
	 * @return el solicitud comun aux manager
	 */
	public SolicitudComunAuxiliarManager getSolicitudComunAuxManager() {
		return solicitudComunAuxManager;
	}

	/**
	 * Establece el solicitud comun aux manager.
	 *
	 * @param solicitudComunAuxManager el nuevo solicitud comun aux manager
	 */
	public void setSolicitudComunAuxManager(SolicitudComunAuxiliarManager solicitudComunAuxManager) {
		this.solicitudComunAuxManager = solicitudComunAuxManager;
	}

	public BatchNivelRentaAuxManager getBatchNivelRentaAuxManager() {
		return batchNivelRentaAuxManager;
	}

	public void setBatchNivelRentaAuxManager(BatchNivelRentaAuxManager batchNivelRentaAuxManager) {
		this.batchNivelRentaAuxManager = batchNivelRentaAuxManager;
	}
	
	public void setDatosVictimasTerrorismoManager(DatosVictimasTerrorismoManager datosVictimasTerrorismoManager) {
		this.datosVictimasTerrorismoManager = datosVictimasTerrorismoManager;
	}

}