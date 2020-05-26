package es.map.ipsg.quartz;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import org.apache.axis.AxisFault;
import org.apache.log4j.Logger;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import es.map.ips.common.exception.BusinessException;
import es.map.ips.common.spring.ApplicationContextProvider;
import es.map.ips.model.BatchIntermediacionQuery;
import es.map.ips.model.BatchNivelRentaQuery;
import es.map.ips.model.CentroGestor;
import es.map.ips.model.ConvocatoriaQuery;
import es.map.ips.model.PagoSolicitudQuery;
import es.map.ips.model.SolicitudComun;
import es.map.ips.model.SolicitudComunQuery;
import es.map.ips.model.Usuario;
import es.map.ipsg.bean.AlertaBean;
import es.map.ipsg.bean.BatchIntermediacionBean;
import es.map.ipsg.bean.BatchNivelRentaBean;
import es.map.ipsg.bean.ConvocatoriasBean;
import es.map.ipsg.bean.IntermediacionDesempleoBean;
import es.map.ipsg.bean.LogIntermediacionBean;
import es.map.ipsg.bean.LogServicioBean;
import es.map.ipsg.bean.PagoSolicitudBean;
import es.map.ipsg.bean.ParametrosConfiguracionBean;
import es.map.ipsg.bean.SolicitudBean;
import es.map.ipsg.bean.TituloOficialBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.manager.AlertaManager;
import es.map.ipsg.manager.BatchIntermediacionManager;
import es.map.ipsg.manager.BatchNivelRentaManager;
import es.map.ipsg.manager.ConvocatoriasManager;
import es.map.ipsg.manager.DatosDesempleoManager;
//import es.map.ipsg.manager.DatosTitulosManager;
import es.map.ipsg.manager.LogIntermediacionManager;
import es.map.ipsg.manager.LogServicioManager;
import es.map.ipsg.manager.PagoSolicitudManager;
import es.map.ipsg.manager.ParametroConfiguracionManager;
import es.map.ipsg.manager.SolicitudesManager;
import es.map.ipsg.manager.TituloOficialManager;
import es.map.ipsg.manager.UsuarioManager;
import es.map.ipsg.util.Constantes;
import es.redsara.intermediacion.scsp.esquemas.educacion.datosespecificos.DatosTitulacion;
import es.redsara.intermediacion.scsp.esquemas.ws.solicitudRespuesta.Atributos;
import es.redsara.intermediacion.scsp.esquemas.ws.solicitudRespuesta.SolicitudRespuesta;

// - IPS-150 - Creacion Batch consulta respuesta peticion asincrona intermediacion

/**
 * El Class ConsultaDatosRespuestaIntermediacionAction.
 */
public class ConsultaDatosRespuestaIntermediacionAction {
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(ConsultaDatosRespuestaIntermediacionAction.class);

	/** el solicitudes manager. */
	private SolicitudesManager solicitudesManager;
	
	/** el pago solicitud manager. */
	private PagoSolicitudManager pagoSolicitudManager;
	
	/** el parametro configuracion manager. */
	private ParametroConfiguracionManager parametroConfiguracionManager;

/** el titulo oficial manager. */
//	private DatosTitulosManager datosTitulosManager;
	private TituloOficialManager tituloOficialManager;
	
	/** el batch intermediacion manager. */
	private BatchIntermediacionManager batchIntermediacionManager;
	
	/** el log intermediacion manager. */
	private LogIntermediacionManager logIntermediacionManager;
	
	/** el log servicio manager. */
	private LogServicioManager logServicioManager;
	
	/** el alerta manager. */
	private AlertaManager alertaManager;
	
	/** el usuario manager. */
	private UsuarioManager usuarioManager;
	
	private BatchNivelRentaManager batchNivelRentaManager;
	
	private ConvocatoriasManager convocatoriaManager;
	
	private DatosDesempleoManager datosDesempleoManager;
	
	/** el properties. */
	private Properties properties;

	/** La constante STRING_IDPETICIONNUMERO. */
	private static final String STRING_IDPETICIONNUMERO = "id de peticion numero ";
	
	/** La constante STRING_COD_ESTADO. */
	private static final String STRING_COD_ESTADO = "cod.estado ";
	
	/** La constante STRING_NIF. */
	private static final String STRING_NIF = "NIF - ";
	
	/** La constante STRING_NUMEROSOLICITUD. */
	private static final String STRING_NUMEROSOLICITUD = "N� Solicitud - ";
	
	/** La constante STRING_ID_SOLICITUD. */
	private static final String STRING_ID_SOLICITUD = "Id solicitud - ";
	
	/** La constante STRING_SOLICITUD_VERIFICADA_PREVIAMENTE. */
	private static final String STRING_SOLICITUD_VERIFICADA_PREVIAMENTE = "solicitud verificada previamente: ";
	
	/** La constante STRING_SOLICITUD_CIUDADANO_PETICION_NO_ENCONTRADA. */
	private static final String STRING_SOLICITUD_CIUDADANO_PETICION_NO_ENCONTRADA = "Solicitud del ciudadano de la peticion no encontrada\n";
	
	/** La constante STRING_RESPUESTA_CAMBIADA_1_FIN_PETICION. */
	private static final String STRING_RESPUESTA_CAMBIADA_1_FIN_PETICION = "respuesta cambiada a 1 - FIN DE PETICION\n";
	
	/** La constante STRING_ERROR_SOAPFAULT_ERROR_LOG_SERVICIO. */
	private static final String STRING_ERROR_SOAPFAULT_ERROR_LOG_SERVICIO = "error SoapFault - ERROR LOG_SERVICIO";
	
	/** La constante STRING_NO_PUEDE_OBTENER_INFORMACION_ERROR_LOG_INTERMEDIACION. */
	private static final String STRING_NO_PUEDE_OBTENER_INFORMACION_ERROR_LOG_INTERMEDIACION =  " no puede obtener la informacion requerida - ERROR LOG_INTERMEDIACION\n";
	
	/** La constante STRING_SOLICITUD_INTERMEDIACION. */
	private static final String STRING_SOLICITUD_INTERMEDIACION = "La solicitud a intermediacion:";
	
	/** La constante STRING_SVTO_ID_EXPEDIENTE. */
	private static final String STRING_SVTO_ID_EXPEDIENTE = "SVTO_ID_EXPEDIENTE";
	
	/** La constante STRING_ERROR_BUSCAR_SOLICITUD. */
	private static final String STRING_ERROR_BUSCAR_SOLICITUD = "error buscar solicitud";
	
	/** La constante STRING_RESULTADO_COTEJAMIENTO. */
	private static final String STRING_RESULTADO_COTEJAMIENTO = "resultado del cotejamiento: ";
	
	/**
	 * Execute test.
	 *
	 * @param solicitudesManager el solicitudes manager
	 * @param pagoSolicitudManager el pago solicitud manager
	 * @param parametroConfiguracionManager el parametro configuracion manager
	 * @param batchIntermediacionManager el batch intermediacion manager
	 * @param usuarioManager el usuario manager
	 * @param logIntermediacionManager el log intermediacion manager
	 * @param logServicioManager el log servicio manager
	 * @param alertaManager el alerta manager
	 */
	public void executeTest(SolicitudesManager solicitudesManager, PagoSolicitudManager pagoSolicitudManager,
			ParametroConfiguracionManager parametroConfiguracionManager,BatchIntermediacionManager batchIntermediacionManager,
			UsuarioManager usuarioManager, LogIntermediacionManager logIntermediacionManager, LogServicioManager logServicioManager ,
			//DatosTitulosManager datosTitulosManager
			AlertaManager alertaManager){
		
		setSolicitudesManager(solicitudesManager);
		setPagoSolicitudManager(pagoSolicitudManager);
		setParametroConfiguracionManager(parametroConfiguracionManager);
		setBatchIntermediacionManager(batchIntermediacionManager);
		setLogIntermediacionManager(logIntermediacionManager);
		setLogServicioManager(logServicioManager);
		setUsuarioManager(usuarioManager);
//		setDatosTitulosManager(datosTitulosManager);
		setAlertaManager(alertaManager);
	}
	
	/**
	 * Execute test solicitudes.
	 */
	public void executeTestSolicitudes(){
		solicitudesManager = (SolicitudesManager) ApplicationContextProvider.getInstance().getBean("solicitudesManager");
		properties = (Properties) ApplicationContextProvider.getInstance().getBean("propertiesBean");
		
		es.redsara.intermediacion.scsp.esquemas.fnumerosa.ws.respuesta.TransmisionesTransmisionDatos solicitud_transmision = 
				new es.redsara.intermediacion.scsp.esquemas.fnumerosa.ws.respuesta.TransmisionesTransmisionDatos();
		
		es.redsara.intermediacion.scsp.esquemas.ws.respuesta.TransmisionesTransmisionDatosDatosGenericos datosGenericos =
				new es.redsara.intermediacion.scsp.esquemas.ws.respuesta.TransmisionesTransmisionDatosDatosGenericos();
		
		es.redsara.intermediacion.scsp.esquemas.ws.respuesta.Solicitante solicitante = new es.redsara.intermediacion.scsp.esquemas.ws.respuesta.Solicitante();
		es.redsara.intermediacion.scsp.esquemas.ws.respuesta.Titular titular = new es.redsara.intermediacion.scsp.esquemas.ws.respuesta.Titular();
		
		solicitante.setIdExpediente("123456");
		titular.setDocumentacion(null);
		titular.setNombre(null);
		
		datosGenericos.setSolicitante(solicitante);
		datosGenericos.setTitular(titular);
		solicitud_transmision.setDatosGenericos(datosGenericos);
		
		buscaSolicitudComunFNumerosa(solicitud_transmision);
		
	}
	
	/**
	 * Execute.
	 */
	public void execute(){
		
		logger.info("Batch Recepcion consultas - inicio\n");
		
		try {
			try {
				solicitudesManager = (SolicitudesManager) ApplicationContextProvider.getInstance().getBean("solicitudesManager");
				pagoSolicitudManager = (PagoSolicitudManager) ApplicationContextProvider.getInstance().getBean("pagoSolicitudManager");
				parametroConfiguracionManager = (ParametroConfiguracionManager) ApplicationContextProvider.getInstance().getBean("parametroConfiguracionManager");
				batchIntermediacionManager = (BatchIntermediacionManager) ApplicationContextProvider.getInstance().getBean("batchIntermediacionManager");
				batchNivelRentaManager = (BatchNivelRentaManager) ApplicationContextProvider.getInstance().getBean("batchNivelRentaManager");
				logIntermediacionManager = (LogIntermediacionManager) ApplicationContextProvider.getInstance().getBean("logIntermediacionManager");
				logServicioManager = (LogServicioManager) ApplicationContextProvider.getInstance().getBean("logServicioManager");
//				datosTitulosManager = (DatosTitulosManager) ApplicationContextProvider.getInstance().getBean("datosTitulosManager");
				tituloOficialManager = (TituloOficialManager) ApplicationContextProvider.getInstance().getBean("tituloOficialManager");
				alertaManager = (AlertaManager) ApplicationContextProvider.getInstance().getBean("alertaManager");
				usuarioManager = (UsuarioManager) ApplicationContextProvider.getInstance().getBean("usuarioManager");
				datosDesempleoManager = (DatosDesempleoManager) ApplicationContextProvider.getInstance().getBean("datosDesempleoManager");
				convocatoriaManager = (ConvocatoriasManager) ApplicationContextProvider.getInstance().getBean("convocatoriaManager");
				properties = (Properties) ApplicationContextProvider.getInstance().getBean("propertiesBean");
			}catch (Exception e) {
				logger.info("Contexto cargado por ejecucion de Test.",e);
			}
			
			
			// Cogemos todas las consultas de BatchIntermediacion
			BatchIntermediacionQuery batchIntermediacionQuery = new BatchIntermediacionQuery();
			batchIntermediacionQuery.setRespuesta(false);
			
			// Anyadimos todos los resultados en una lista
			ArrayList<BatchIntermediacionBean> arrConsultas = new ArrayList<BatchIntermediacionBean>();
			arrConsultas = batchIntermediacionManager.buscarPeticionesAll(batchIntermediacionQuery);
						
			
			// comprobamos que la lista no es nula ni esta vacia
			// recorremos la lista
			if (arrConsultas != null && !arrConsultas.isEmpty()){
				for (BatchIntermediacionBean registro : arrConsultas) {
					consultaRespuestaIntermediacion(registro);
				}
			}
			
			//NIVEL RENTA INI
			procesarBatchNivelRenta();
			//NIVEL RENTA FIN
		} catch (Exception e) {
			logger.error("Error en execute:",e);
			
		}
		logger.info("Batch Recepcion consultas - fin");
	}

	/**
	 * 
	 * Batch que gestiona las peticiones asincronas de la tabla BATCH_NIVEL_RENTA SOLICITUD_NIVEL_RENTA
	 * 
	 */
	private void procesarBatchNivelRenta() {
		BatchNivelRentaQuery batchNivelRentaQuery = new BatchNivelRentaQuery();
		
		batchNivelRentaQuery.setProcesado(false);
		
		ArrayList<BatchNivelRentaBean> listBatchNR = batchNivelRentaManager.buscarPeticionesAll(batchNivelRentaQuery);
		Date fecha = new Date();
		
		if (listBatchNR != null && listBatchNR.size() > 0) {
			for (BatchNivelRentaBean batchNivelRentaBean : listBatchNR) {
				if (batchNivelRentaBean.getSolicitudes() != null && batchNivelRentaBean.getSolicitudes().size() > 0) {
					try {
						for (SolicitudComun solicitudComun : batchNivelRentaBean.getSolicitudes()) {
							IntermediacionDesempleoBean desempleoBean = null;
							boolean errorWs = false;
							
							SolicitudBean solicitudBean = solicitudesManager.obtenerSolicitud(solicitudComun.getIdSolicitud());
							UsuarioBean usuarioBean = usuarioManager.toUsuarioBean(batchNivelRentaBean.getIdUsuarioConsulta());
							//ConvocatoriasBean convocatoriaBean = convocatoriaManager.toConvocatoriaBean(solicitudBean.getConvocatoria());
							ConvocatoriaQuery convocatoriaQuery = new ConvocatoriaQuery();
							convocatoriaQuery.setId(solicitudBean.getConvocatoria().getId());
							ConvocatoriasBean convocatoriaBean = convocatoriaManager.buscarConvocatoriaById(convocatoriaQuery);
							
							try {

								desempleoBean = datosDesempleoManager.validarDesempleoSalario(convocatoriaBean, solicitudBean, usuarioBean, true, solicitudBean.getCentroGestor());
								
								
								// comprobamos el estado de verificacion actual, obteniendo el campo de desempleo_verificado de la tabla solicitud_comun
								Character verificacion_actual = solicitudBean.getDesempleoVerificado();
								
								// actualizamos el dato de discapacidad_verificado en funcion de la validacion y el estado de la verificacion actual
								if (desempleoBean.isResultadoVerificacionSalario() && (verificacion_actual != Constantes.DESEMPLEO_NO_VERIFICADO)) {
									// seteo a si verificado ('A')
									solicitudBean.setDesempleoVerificado(Constantes.DESEMPLEO_VERIFICADO);
									logger.info(STRING_RESULTADO_COTEJAMIENTO + Constantes.DESEMPLEO_VERIFICADO);
								} else {
									// seteo el campo a no verificado ('R')
									solicitudBean.setDesempleoVerificado(Constantes.DESEMPLEO_NO_VERIFICADO);
									logger.info(STRING_RESULTADO_COTEJAMIENTO + Constantes.DESEMPLEO_NO_VERIFICADO);
								} 
								solicitudesManager.modificarSolicitud(solicitudBean);
							} catch (Exception e) {
								errorWs = true;	
								// log de error por excepcion
								logger.warn(STRING_SOLICITUD_INTERMEDIACION+ solicitudBean.getId() + STRING_NO_PUEDE_OBTENER_INFORMACION_ERROR_LOG_INTERMEDIACION, e);
								logger.error(STRING_SOLICITUD_INTERMEDIACION+ solicitudBean.getId() + STRING_NO_PUEDE_OBTENER_INFORMACION_ERROR_LOG_INTERMEDIACION, e);
								if (e.getMessage() != null) {
									insertarLogIntermediacion(solicitudBean,e.getMessage(),"",batchNivelRentaBean.getIdCodConsulta());
								} else {
									insertarLogIntermediacion(solicitudBean,Constantes.LOG_ERROR_EXCEPCION_NO_ENCONTRADA,"",batchNivelRentaBean.getIdCodConsulta());
								}
							}
							
							if (errorWs) {
								//Si falla por el WS se pone en estado E (Ptd.Error al Comprobar)
								datosDesempleoManager.guardarEstadoFalloWsSolicitud(solicitudBean);
								throw new BusinessException("intermediacion.svdi.errorConexion");
							} else {
								//Si no falla por el WS se pone en estado N (Ptd)
								if (solicitudBean.getId() != null) {
									datosDesempleoManager.guardarEstadoPendiente(solicitudBean.getId());
								}	
							}
						}
						batchNivelRentaBean.setProcesado(true);
						batchNivelRentaBean.setFhFinConsulta(new Date());
						batchNivelRentaManager.actualizarNivelRenta(batchNivelRentaBean, batchNivelRentaBean.getIdConsulta());
//						crearAlertaNR(batchNivelRentaBean,Constantes.ID_TIPO_ALERTA_IMPORTE_DESEMPLEO);
						logger.info(STRING_RESPUESTA_CAMBIADA_1_FIN_PETICION);
					} catch (Exception e) {
						batchNivelRentaBean.setReintentos(batchNivelRentaBean.getReintentos() + 1);
						batchNivelRentaManager.actualizarNivelRenta(batchNivelRentaBean, batchNivelRentaBean.getIdConsulta());
						// si el numero de reintentos llega a 5 se pone la respuesta a 1
						if (batchNivelRentaBean.getReintentos() >= 5) {
							batchNivelRentaBean.setProcesado(true);
							batchNivelRentaBean.setFhFinConsulta(fecha);
							batchNivelRentaManager.actualizarNivelRenta(batchNivelRentaBean, batchNivelRentaBean.getIdConsulta());
						}	
					}

				}
			}
		}
	}
	
		/**
		 * Consulta respuesta intermediacion.
		 *
		 * @param registro el registro
		 */
		private void consultaRespuestaIntermediacion(BatchIntermediacionBean registro) {
		
			String url = properties.getProperty("URL");
			
			Atributos atributos = new Atributos();
			atributos.setCodigoCertificado(registro.getIdCodConsulta());
			atributos.setIdPeticion(registro.getIdPeticion());
			atributos.setNumElementos(registro.getNumElementos());
			
			SolicitudRespuesta solicitudRespuesta = new SolicitudRespuesta();
			solicitudRespuesta.setAtributos(atributos);
			
			Date fecha = new Date();
			String idPet = registro.getIdPeticion();
			int reintentos = registro.getReintentos();
			
			
			switch (registro.getTipoConsulta()) {
	
				case 1:
					logger.info(STRING_IDPETICIONNUMERO + idPet);
					
					// creacion del proxy asociado al servicio web de titulacion universitaria
					es.redsara.intermediacion.scsp.esquemas.educacion.ws.ScspwsProxy proxy1 = new es.redsara.intermediacion.scsp.esquemas.educacion.ws.ScspwsProxy(url);
					
					try {
						
						// invocamos  al metodo solicutdRespuesta
						es.redsara.intermediacion.scsp.esquemas.educacion.ws.respuesta.Respuesta respuestaAsincrona1 = proxy1.solicitudRespuesta(solicitudRespuesta);
						
						// obtencion de datos para el log de la respuesta (tabla log_servicio)
						String literal_error_servicio = respuestaAsincrona1.getAtributos().getEstado().getLiteralError();
						String codigo_estado_servicio = respuestaAsincrona1.getAtributos().getEstado().getCodigoEstado();
						Long tiempo_respuesta_servicio = respuestaAsincrona1.getAtributos().getEstado().getTiempoEstimadoRespuesta();
						
						// si el codigo de estado no es 0003 hay error para registrar en log-servicio 0002
						if (literal_error_servicio != null && !codigo_estado_servicio.equals(Constantes.CODIGO_ESTADO_TRAMITADA)) {
							logger.warn(STRING_COD_ESTADO + codigo_estado_servicio + " : solicitud a intermediacion de titulaciones universitarias procesada sin exito - ERROR TABLA LOG_SERVICIO\n");
							insertarLogServicio(literal_error_servicio,codigo_estado_servicio,tiempo_respuesta_servicio);
							// actualizo el numero de reintentos
							registro.setReintentos(reintentos + 1);
							batchIntermediacionManager.actualizarIntermediacion(registro, registro.getIdConsulta());
							// si el numero de reintentos llega a 5 se pone la respuesta a 1
							if (registro.getReintentos() >= 5) {
								registro.setRespuesta(true);
								registro.setFhFinConsulta(fecha);
								batchIntermediacionManager.actualizarIntermediacion(registro, registro.getIdConsulta());
								logger.warn("5 intentos alcanzados en el intento de procesar la peticion de titulaciones universitarias a intermediacion - FIN DE PETICION\n");
								break;
							}
						} else {	
							// si es 0003 la peticion a intermediacion ha sido procesada con exito					
							logger.info("cod.estado 0003: peticion a intermediacion de titulaciones universitarias tramitada con exito - OK LOG_SERVICIO");
							insertarLogServicio(literal_error_servicio,codigo_estado_servicio,tiempo_respuesta_servicio);
							
							// obtenemos la respuesta, es decir, los datos completos de todas las solicitudes
							es.redsara.intermediacion.scsp.esquemas.educacion.ws.respuesta.TransmisionesTransmisionDatos[] datosSolicitudes = respuestaAsincrona1.getTransmisiones().getTransmisionDatos();
							
							// procesamos cada solicitud con intermediacion
							for (es.redsara.intermediacion.scsp.esquemas.educacion.ws.respuesta.TransmisionesTransmisionDatos solicitud_transmision : datosSolicitudes) {
												
								// solicitud comun del ciudadano
								SolicitudBean solicitud_ciudadano = buscaSolicitudComunTitulacionUniv(solicitud_transmision);
								
								if (solicitud_ciudadano != null) {
									logger.info(STRING_NIF + solicitud_ciudadano.getNif());
									logger.info(STRING_NUMEROSOLICITUD + solicitud_ciudadano.getNumeroSolicitud());
									logger.info(STRING_ID_SOLICITUD + solicitud_ciudadano.getId());
									
									// comprobacion si ya se ha verificado previamente la familia numerosa de esa solicitud
									Boolean titulacion_univ_verificado_previo = tituloVerificadoPrevio(solicitud_ciudadano);
										
									if (!titulacion_univ_verificado_previo) {
									
										try {
											// compruebo si hay error devuelto por servicio de intermediacion respecto la consulta por lote de de fnumerosa
											String error_consulta_lote = solicitud_transmision.getDatosEspecificos().getRetorno().getEstado().getLiteralError();
											String cod_estado_lote = solicitud_transmision.getDatosEspecificos().getRetorno().getEstado().getCodigoEstado();
											
											// si el codigo de estado no es 0003 hay error para registrar en log-intermediacion
											if (error_consulta_lote != null && !cod_estado_lote.equals(Constantes.CODIGO_ESTADO_TRAMITADA)) {
												logger.warn(STRING_COD_ESTADO + cod_estado_lote + " : titulo universitario de la solicitud no valido segun intermediacion - ERROR LOG_INTERMEDIACION");
												insertarLogIntermediacion(solicitud_ciudadano, error_consulta_lote, cod_estado_lote, idPet);
												
												// seteo el campo titulo_verificado a no verificado ('R')
												solicitud_ciudadano.setTituloVerificado(Constantes.TITULO_NO_VERIFICADO);
												solicitudesManager.modificarSolicitud(solicitud_ciudadano);
												logger.info(STRING_RESULTADO_COTEJAMIENTO + Constantes.TITULO_NO_VERIFICADO);
											} else {
												// si el codigo de estado es 0003 existe el titulo de familia numerosa y es correcto segun intermediacion
												logger.info(STRING_COD_ESTADO + cod_estado_lote + " : titulo universitario de la solicitud correcto segun intermediacion, se procede a verificar - OK LOG_INTERMEDIACION");
												insertarLogIntermediacion(solicitud_ciudadano, error_consulta_lote, cod_estado_lote, idPet);
												
												// COTEJAMIENTO
																									
												// lista de titulos del ciudadano segun intermediacion
												DatosTitulacion[] titulos_univ_intermediacion = solicitud_transmision.getDatosEspecificos().getRetorno().getListaTitulos();
													
												// cotejamos los datos recibidos con lo que introdujo el usuario 
												Boolean cotejado = validarTituloUniversitario(solicitud_ciudadano, titulos_univ_intermediacion);
																			
												// actualizamos el dato de familiaNumerosa_verificado en funcion de la validacion
												if (cotejado) {
													// seteo el campo titulo_verificado a si verificado ('A')
													solicitud_ciudadano.setTituloVerificado(Constantes.TITULO_VERIFICADO);
													logger.info(STRING_RESULTADO_COTEJAMIENTO + Constantes.TITULO_VERIFICADO);
												} else {
													// seteo el campo titulo_verificado a no verificado ('R')
													solicitud_ciudadano.setTituloVerificado(Constantes.TITULO_NO_VERIFICADO);
													logger.info(STRING_RESULTADO_COTEJAMIENTO + Constantes.TITULO_NO_VERIFICADO);
												}
												solicitudesManager.modificarSolicitud(solicitud_ciudadano);
											}
										} catch (Exception e) {
											// log consulta a intermediacion 'error'
											logger.warn("la solicitud a intermediacion de titulaciones universitarias no puede obtener la informacion requerida - ERROR LOG_INTERMEDIACION\n", e);
											logger.error("la solicitud a intermediacion de titulaciones universitarias no puede obtener la informacion requerida - ERROR LOG_INTERMEDIACION\n", e);
											if (e.getMessage() != null){
												insertarLogIntermediacion(solicitud_ciudadano,e.getMessage(),"",idPet);
											} else {
												insertarLogIntermediacion(solicitud_ciudadano,Constantes.LOG_ERROR_EXCEPCION_NO_ENCONTRADA,"",idPet);
											}
										}
									} else {
										logger.info(STRING_SOLICITUD_VERIFICADA_PREVIAMENTE + Constantes.TITULO_VERIFICADO );
									}
								} else {
									logger.info(STRING_SOLICITUD_CIUDADANO_PETICION_NO_ENCONTRADA);
								}
							}
						// actualizamos la tabla de batch_intermediacion para no volver a preguntar por esa peticion
						registro.setRespuesta(true);
						registro.setFhFinConsulta(fecha);
						batchIntermediacionManager.actualizarIntermediacion(registro, registro.getIdConsulta());
//						crearAlerta(registro,Constantes.ID_TIPO_ALERTA_UNIVERSITARIA);
						logger.info(STRING_RESPUESTA_CAMBIADA_1_FIN_PETICION);
						}
					} catch (RemoteException e) {
						// log de error por SoapFault
						logger.warn(STRING_ERROR_SOAPFAULT_ERROR_LOG_SERVICIO);
						logger.error(STRING_ERROR_SOAPFAULT_ERROR_LOG_SERVICIO,e);
						insertarLogServicioSoapFault(e);
						// actualizo el numero de reintentos
						registro.setReintentos(reintentos + 1);
						batchIntermediacionManager.actualizarIntermediacion(registro, registro.getIdConsulta());
						// si los reintentos igualan o superan a 5 se pone la respuesta a 1 y se para de intentar
						if (registro.getReintentos() >= 5) {
							registro.setRespuesta(true);
							registro.setFhFinConsulta(fecha);
							batchIntermediacionManager.actualizarIntermediacion(registro, registro.getIdConsulta());
							logger.warn("5 intentos alcanzados en el intento de procesar la solicitud de titulaciones universitarias a intermediacion - FIN DE PETICION\n");
							break;
						}
					}
					break;
				
				case 2:
					logger.info(STRING_IDPETICIONNUMERO + idPet);
					
					// creacion del proxy asociado al servicio web de titulacion no universitaria
					es.redsara.intermediacion.scsp.esquemas.educacionnouniv.ws.ScspwsProxy proxy2 = new es.redsara.intermediacion.scsp.esquemas.educacionnouniv.ws.ScspwsProxy(url);
					
					try {
						
						// invocamos  al metodo solicutdRespuesta
						es.redsara.intermediacion.scsp.esquemas.educacionnouniv.ws.respuesta.Respuesta respuestaAsincrona2 = proxy2.solicitudRespuesta(solicitudRespuesta);
						
						// obtencion de datos para el log de la respuesta (tabla log_servicio)
						String literal_error_servicio = respuestaAsincrona2.getAtributos().getEstado().getLiteralError();
						String codigo_estado_servicio = respuestaAsincrona2.getAtributos().getEstado().getCodigoEstado();
						Long tiempo_respuesta_servicio = respuestaAsincrona2.getAtributos().getEstado().getTiempoEstimadoRespuesta();
						
						// si el codigo de estado no es 0003 hay error para registrar en log-servicio 0002
						if (literal_error_servicio != null && !codigo_estado_servicio.equals(Constantes.CODIGO_ESTADO_TRAMITADA)) {
							logger.warn(STRING_COD_ESTADO + codigo_estado_servicio + " : solicitud a intermediacion de titulaciones no universitarias procesada sin exito - ERROR TABLA LOG_SERVICIO\n");
							insertarLogServicio(literal_error_servicio,codigo_estado_servicio,tiempo_respuesta_servicio);
							// actualizo el numero de reintentos
							registro.setReintentos(reintentos + 1);
							batchIntermediacionManager.actualizarIntermediacion(registro, registro.getIdConsulta());
							// si el numero de reintentos llega a 5 se pone la respuesta a 1
							if (registro.getReintentos() >= 5) {
								registro.setRespuesta(true);
								registro.setFhFinConsulta(fecha);
								batchIntermediacionManager.actualizarIntermediacion(registro, registro.getIdConsulta());
								logger.warn("5 intentos alcanzados en el intento de procesar la peticion de titulaciones no universitarias a intermediacion - FIN DE PETICION\n");
								break;
							}
						} else {	
							// si es 0003 la peticion a intermediacion ha sido procesada con exito					
							logger.info("cod.estado 0003: peticion a intermediacion de titulaciones no universitarias tramitada con exito - OK LOG_SERVICIO");
							insertarLogServicio(literal_error_servicio,codigo_estado_servicio,tiempo_respuesta_servicio);
							
							// obtenemos la respuesta, es decir, los datos completos de todas las solicitudes
							es.redsara.intermediacion.scsp.esquemas.educacionnouniv.ws.respuesta.TransmisionDatos[] datosSolicitudes = respuestaAsincrona2.getTransmisiones().getTransmisionDatos();
												
							// procesamos cada solicitud con intermediacion
							for (es.redsara.intermediacion.scsp.esquemas.educacionnouniv.ws.respuesta.TransmisionDatos solicitud_transmision : datosSolicitudes) {
												
								// solicitud comun del ciudadano
								SolicitudBean solicitud_ciudadano = buscaSolicitudComunTitulacionNoUniv(solicitud_transmision);
								
								if (solicitud_ciudadano != null) {
									logger.info(STRING_NIF + solicitud_ciudadano.getNif());
									logger.info(STRING_NUMEROSOLICITUD + solicitud_ciudadano.getNumeroSolicitud());
									logger.info(STRING_ID_SOLICITUD + solicitud_ciudadano.getId());
									
									// comprobacion si ya se ha verificado previamente la familia numerosa de esa solicitud
									Boolean titulacion_no_univ_verificado_previo = tituloVerificadoPrevio(solicitud_ciudadano);
										
									if (!titulacion_no_univ_verificado_previo) {
									
										try {
											// compruebo si hay error devuelto por servicio de intermediacion respecto la consulta por lote de de fnumerosa
											String error_consulta_lote = solicitud_transmision.getDatosEspecificos().getRetorno().getEstado().getLiteralError();
											String cod_estado_lote = solicitud_transmision.getDatosEspecificos().getRetorno().getEstado().getCodigoEstado();
											
											// si el codigo de estado no es 0003 hay error para registrar en log-intermediacion
											if (error_consulta_lote != null && !cod_estado_lote.equals(Constantes.CODIGO_ESTADO_TRAMITADA)) {
												logger.warn(STRING_COD_ESTADO + cod_estado_lote + " : titulo no universitario de la solicitud no valido segun intermediacion - ERROR LOG_INTERMEDIACION");
												insertarLogIntermediacion(solicitud_ciudadano, error_consulta_lote, cod_estado_lote, idPet);
												
												// seteo el campo titulo_verificado a no verificado ('R')
												solicitud_ciudadano.setTituloVerificado(Constantes.TITULO_NO_VERIFICADO);
												solicitudesManager.modificarSolicitud(solicitud_ciudadano);
												logger.info(STRING_RESULTADO_COTEJAMIENTO + Constantes.TITULO_NO_VERIFICADO);
											} else {
												// si el codigo de estado es 0003 existe el titulo de familia numerosa y es correcto segun intermediacion
												logger.info(STRING_COD_ESTADO + cod_estado_lote + " : titulo no universitario de la solicitud correcto segun intermediacion, se procede a verificar - OK LOG_INTERMEDIACION");
												insertarLogIntermediacion(solicitud_ciudadano, error_consulta_lote, cod_estado_lote, idPet);
												
												// COTEJAMIENTO
												
												// lista de titulos del ciudadano segun intermediacion
												es.redsara.intermediacion.scsp.esquemas.educacionnouniv.datosespecificos.DatosTitulacion[] titulos_no_univ_intermediacion = solicitud_transmision.getDatosEspecificos().getRetorno().getListaTitulos();
													
												// cotejamos los datos recibidos con lo que introdujo el usuario 
												Boolean cotejado = validarTituloNoUniversitario(solicitud_ciudadano, titulos_no_univ_intermediacion);
																			
												// actualizamos el dato de familiaNumerosa_verificado en función de la validación
												if (cotejado) {
													// seteo el campo titulo_verificado a si verificado ('A')
													solicitud_ciudadano.setTituloVerificado(Constantes.TITULO_VERIFICADO);
													logger.info(STRING_RESULTADO_COTEJAMIENTO + Constantes.TITULO_VERIFICADO);
												} else {
													// seteo el campo titulo_verificado a no verificado ('R')
													solicitud_ciudadano.setTituloVerificado(Constantes.TITULO_NO_VERIFICADO);
													logger.info(STRING_RESULTADO_COTEJAMIENTO + Constantes.TITULO_NO_VERIFICADO);
												}
												solicitudesManager.modificarSolicitud(solicitud_ciudadano);
											}
										} catch (Exception e) {
											// log consulta a intermediacion 'error'
											logger.warn("la solicitud a intermediacion de titulaciones no universitarias no puede obtener la informacion requerida - ERROR LOG_INTERMEDIACION\n",e);
											logger.error("la solicitud a intermediacion de titulaciones no universitarias no puede obtener la informacion requerida - ERROR LOG_INTERMEDIACION\n",e);
											if (e.getMessage() != null){
												insertarLogIntermediacion(solicitud_ciudadano,e.getMessage(),"",idPet);
											} else {
												insertarLogIntermediacion(solicitud_ciudadano,Constantes.LOG_ERROR_EXCEPCION_NO_ENCONTRADA,"",idPet);
											}
										}
									} else {
										logger.info(STRING_SOLICITUD_VERIFICADA_PREVIAMENTE + Constantes.TITULO_VERIFICADO );
									}
								} else {
									logger.info(STRING_SOLICITUD_CIUDADANO_PETICION_NO_ENCONTRADA);
								}
							}
						// actualizamos la tabla de batch_intermediacion para no volver a preguntar por esa petición
						registro.setRespuesta(true);
						registro.setFhFinConsulta(fecha);
						batchIntermediacionManager.actualizarIntermediacion(registro, registro.getIdConsulta());
//						crearAlerta(registro,Constantes.ID_TIPO_ALERTA_NO_UNIVERSITARIA);
						logger.info(STRING_RESPUESTA_CAMBIADA_1_FIN_PETICION);
						}
					} catch (RemoteException e) {
						// log de error por SoapFault
						logger.warn(STRING_ERROR_SOAPFAULT_ERROR_LOG_SERVICIO);
						logger.error(STRING_ERROR_SOAPFAULT_ERROR_LOG_SERVICIO,e);
						insertarLogServicioSoapFault(e);
						// actualizo el numero de reintentos
						registro.setReintentos(reintentos + 1);
						batchIntermediacionManager.actualizarIntermediacion(registro, registro.getIdConsulta());
						// si los reintentos igualan o superan a 5 se pone la respuesta a 1 y se para de intentar
						if (registro.getReintentos() >= 5) {
							registro.setRespuesta(true);
							registro.setFhFinConsulta(fecha);
							batchIntermediacionManager.actualizarIntermediacion(registro, registro.getIdConsulta());
							logger.warn("5 intentos alcanzados en el intento de procesar la solicitud de titulaciones no universitarias a intermediacion - FIN DE PETICION\n");
							break;
						}
					}
					break;
					
					
				case 3:
					logger.info(STRING_IDPETICIONNUMERO + idPet);
					
					// creacion del proxy asociado al servicio web de titulacion  universitaria anterior a 1990
					es.redsara.intermediacion.scsp.esquemas.educacion1990Univ.ws.ScspwsProxy proxy3 = new es.redsara.intermediacion.scsp.esquemas.educacion1990Univ.ws.ScspwsProxy(url);
					
					try {
						
						// invocamos  al metodo solicutdRespuesta
						es.redsara.intermediacion.scsp.esquemas.educacion1990Univ.ws.respuesta.Respuesta respuestaAsincrona3 = proxy3.solicitudRespuesta(solicitudRespuesta);
						
						// obtencion de datos para el log de la respuesta (tabla log_servicio)
						String literal_error_servicio = respuestaAsincrona3.getAtributos().getEstado().getLiteralError();
						String codigo_estado_servicio = respuestaAsincrona3.getAtributos().getEstado().getCodigoEstado();
						Long tiempo_respuesta_servicio = respuestaAsincrona3.getAtributos().getEstado().getTiempoEstimadoRespuesta();
						
						// si el codigo de estado no es 0003 hay error para registrar en log-servicio 0002
						if (literal_error_servicio != null && !codigo_estado_servicio.equals(Constantes.CODIGO_ESTADO_TRAMITADA)) {
							logger.warn(STRING_COD_ESTADO + codigo_estado_servicio + " : solicitud a intermediacion de titulaciones unviversitarias anteriores a 1990 procesada sin exito - ERROR TABLA LOG_SERVICIO\n");
							insertarLogServicio(literal_error_servicio,codigo_estado_servicio,tiempo_respuesta_servicio);
							// actualizo el numero de reintentos
							registro.setReintentos(reintentos + 1);
							batchIntermediacionManager.actualizarIntermediacion(registro, registro.getIdConsulta());
							// si el numero de reintentos llega a 5 se pone la respuesta a 1
							if (registro.getReintentos() >= 5) {
								registro.setRespuesta(true);
								registro.setFhFinConsulta(fecha);
								batchIntermediacionManager.actualizarIntermediacion(registro, registro.getIdConsulta());
								logger.warn("5 intentos alcanzados en el intento de procesar la peticion de titulaciones universitarias anteriores a 1990 a intermediacion - FIN DE PETICION\n");
								break;
							}
						} else {	
							// si es 0003 la peticion a intermediacion ha sido procesada con exito					
							logger.info("cod.estado 0003: peticion a intermediacion de titulaciones universitarias anteriores a 1990 tramitada con exito - OK LOG_SERVICIO");
							insertarLogServicio(literal_error_servicio,codigo_estado_servicio,tiempo_respuesta_servicio);
							
							// obtenemos la respuesta, es decir, los datos completos de todas las solicitudes
							es.redsara.intermediacion.scsp.esquemas.educacion1990Univ.ws.respuesta.TransmisionesTransmisionDatos[] datosSolicitudes = respuestaAsincrona3.getTransmisiones().getTransmisionDatos();
												
							// procesamos cada solicitud con intermediacion
							for (es.redsara.intermediacion.scsp.esquemas.educacion1990Univ.ws.respuesta.TransmisionesTransmisionDatos solicitud_transmision : datosSolicitudes) {
												
								// solicitud comun del ciudadano
								SolicitudBean solicitud_ciudadano = buscaSolicitudComunTitulacionUniv1990(solicitud_transmision);
								
								if (solicitud_ciudadano != null) {
									logger.info(STRING_NIF + solicitud_ciudadano.getNif());
									logger.info(STRING_NUMEROSOLICITUD + solicitud_ciudadano.getNumeroSolicitud());
									logger.info(STRING_ID_SOLICITUD + solicitud_ciudadano.getId());
									
									// comprobacion si ya se ha verificado previamente la familia numerosa de esa solicitud
									Boolean titulacion_univ_1990_verificado_previo = tituloVerificadoPrevio(solicitud_ciudadano);
										
									if (!titulacion_univ_1990_verificado_previo) {
										try {
											// compruebo si hay error devuelto por servicio de intermediacion respecto la consulta por lote de de fnumerosa
											String error_consulta_lote = solicitud_transmision.getDatosEspecificos().getRetorno().getEstado().getLiteralError();
											String cod_estado_lote = solicitud_transmision.getDatosEspecificos().getRetorno().getEstado().getCodigoEstado();
											
											// si el codigo de estado no es 0003 hay error para registrar en log-intermediacion
											if (error_consulta_lote != null && !cod_estado_lote.equals(Constantes.CODIGO_ESTADO_TRAMITADA)) {
												logger.warn(STRING_COD_ESTADO + cod_estado_lote + " : titulo universitario anterior a 1990 de la solicitud no valido segun intermediacion - ERROR LOG_INTERMEDIACION");
												insertarLogIntermediacion(solicitud_ciudadano, error_consulta_lote, cod_estado_lote, idPet);
												
												// seteo el campo titulo_verificado a no verificado ('R')
												solicitud_ciudadano.setTituloVerificado(Constantes.TITULO_NO_VERIFICADO);
												solicitudesManager.modificarSolicitud(solicitud_ciudadano);
												logger.info(STRING_RESULTADO_COTEJAMIENTO + Constantes.TITULO_NO_VERIFICADO);
											} else {
												// si el codigo de estado es 0003 existe el titulo de familia numerosa y es correcto segun intermediacion
												logger.info(STRING_COD_ESTADO + cod_estado_lote + " : titulo universitario anterior a 1990 de la solicitud correcto segun intermediacion, se procede a verificar - OK LOG_INTERMEDIACION");
												insertarLogIntermediacion(solicitud_ciudadano, error_consulta_lote, cod_estado_lote, idPet);
												
												// COTEJAMIENTO
												
												// lista de titulos del ciudadano segun intermediacion
												es.redsara.intermediacion.scsp.esquemas.educacion1990Univ.datosespecificos.DatosTitulacion[] titulos_univ_1990_intermediacion = solicitud_transmision.getDatosEspecificos().getRetorno().getListaTitulares()[0].getListaTitulos();
													
												// cotejamos los datos recibidos con lo que introdujo el usuario 
												Boolean cotejado = validarTituloUniversitario1990(solicitud_ciudadano, titulos_univ_1990_intermediacion);
																			
												// actualizamos el dato de familiaNumerosa_verificado en función de la validación
												if (cotejado) {
													// seteo el campo titulo_verificado a si verificado ('A')
													solicitud_ciudadano.setTituloVerificado(Constantes.TITULO_VERIFICADO);
													logger.info(STRING_RESULTADO_COTEJAMIENTO + Constantes.TITULO_VERIFICADO);
												} else {
													// seteo el campo titulo_verificado a no verificado ('R')
													solicitud_ciudadano.setTituloVerificado(Constantes.TITULO_NO_VERIFICADO);
													logger.info(STRING_RESULTADO_COTEJAMIENTO + Constantes.TITULO_NO_VERIFICADO);
												}
												solicitudesManager.modificarSolicitud(solicitud_ciudadano);
											}
										} catch (Exception e) {
											// log consulta a intermediacion 'error'
											logger.warn("la solicitud a intermediacion de titulaciones universitarias anteriores a 1990 no puede obtener la informacion requerida - LOG_INTERMEDIACION\n", e);
											logger.error("la solicitud a intermediacion de titulaciones universitarias anteriores a 1990 no puede obtener la informacion requerida - LOG_INTERMEDIACION\n", e);
											if (e.getMessage() != null) {
												insertarLogIntermediacion(solicitud_ciudadano,e.getMessage(),"",idPet);
											} else {
												insertarLogIntermediacion(solicitud_ciudadano,Constantes.LOG_ERROR_EXCEPCION_NO_ENCONTRADA,"",idPet);
											}
										}
									} else {
										logger.info(STRING_SOLICITUD_VERIFICADA_PREVIAMENTE + Constantes.TITULO_VERIFICADO );
									}
								} else {
									logger.info(STRING_SOLICITUD_CIUDADANO_PETICION_NO_ENCONTRADA);
								}
							}
						// actualizamos la tabla de batch_intermediacion para no volver a preguntar por esa petición
						registro.setRespuesta(true);
						registro.setFhFinConsulta(fecha);
						batchIntermediacionManager.actualizarIntermediacion(registro, registro.getIdConsulta());
//						crearAlerta(registro,Constantes.ID_TIPO_ALERTA_UNIVERSITARIA_1990);
						logger.info(STRING_RESPUESTA_CAMBIADA_1_FIN_PETICION);
						}
					} catch (RemoteException e) {
						// log de error por SoapFault
						logger.warn(STRING_ERROR_SOAPFAULT_ERROR_LOG_SERVICIO);
						logger.error(STRING_ERROR_SOAPFAULT_ERROR_LOG_SERVICIO,e);
						insertarLogServicioSoapFault(e);
						// actualizo el numero de reintentos
						registro.setReintentos(reintentos + 1);
						batchIntermediacionManager.actualizarIntermediacion(registro, registro.getIdConsulta());
						// si los reintentos igualan o superan a 5 se pone la respuesta a 1 y se para de intentar
						if (registro.getReintentos() >= 5) {
							registro.setRespuesta(true);
							registro.setFhFinConsulta(fecha);
							batchIntermediacionManager.actualizarIntermediacion(registro, registro.getIdConsulta());
							logger.warn("5 intentos alcanzados en el intento de procesar la solicitud de titulaciones universitarias anteriores a 1990 a intermediacion - FIN DE PETICION\n");
							break;
						}
					}
					break;
					
					
				case 4:
					logger.info(STRING_IDPETICIONNUMERO + idPet);
					
					// creacion del proxy asociado al servicio web de titulacion no universitaria
					es.redsara.intermediacion.scsp.esquemas.educacion1990noUniv.ws.ScspwsProxy proxy4 = new es.redsara.intermediacion.scsp.esquemas.educacion1990noUniv.ws.ScspwsProxy(url);
					
					try {
						
						// invocamos  al metodo solicutdRespuesta
						es.redsara.intermediacion.scsp.esquemas.educacion1990noUniv.ws.respuesta.Respuesta respuestaAsincrona4 = proxy4.solicitudRespuesta(solicitudRespuesta);
						
						// obtencion de datos para el log de la respuesta (tabla log_servicio)
						String literal_error_servicio = respuestaAsincrona4.getAtributos().getEstado().getLiteralError();
						String codigo_estado_servicio = respuestaAsincrona4.getAtributos().getEstado().getCodigoEstado();
						Long tiempo_respuesta_servicio = respuestaAsincrona4.getAtributos().getEstado().getTiempoEstimadoRespuesta();
						
						// si el codigo de estado no es 0003 hay error para registrar en log-servicio 0002
						if (literal_error_servicio != null && !codigo_estado_servicio.equals(Constantes.CODIGO_ESTADO_TRAMITADA)) {
							logger.warn(STRING_COD_ESTADO + codigo_estado_servicio + " : solicitud a intermediacion de titulaciones no universitarias anteriores a 1990 procesada sin exito - ERROR TABLA LOG_SERVICIO\n");
							insertarLogServicio(literal_error_servicio,codigo_estado_servicio,tiempo_respuesta_servicio);
							// actualizo el numero de reintentos
							registro.setReintentos(reintentos + 1);
							batchIntermediacionManager.actualizarIntermediacion(registro, registro.getIdConsulta());
							// si el numero de reintentos llega a 5 se pone la respuesta a 1
							if (registro.getReintentos() >= 5) {
								registro.setRespuesta(true);
								registro.setFhFinConsulta(fecha);
								batchIntermediacionManager.actualizarIntermediacion(registro, registro.getIdConsulta());
								logger.warn("5 intentos alcanzados en el intento de procesar la peticion de titulaciones no universitarias anteriores a 1990 a intermediacion - FIN DE PETICION\n");
								break;
							}
						} else {	
							// si es 0003 la peticion a intermediacion ha sido procesada con exito					
							logger.info("cod.estado 0003: peticion a intermediacion de titulaciones no universitarias anteriores a 1990 tramitada con exito - OK LOG_SERVICIO");
							insertarLogServicio(literal_error_servicio,codigo_estado_servicio,tiempo_respuesta_servicio);
							
							// obtenemos la respuesta, es decir, los datos completos de todas las solicitudes
							es.redsara.intermediacion.scsp.esquemas.educacion1990noUniv.ws.respuesta.TransmisionDatos[] datosSolicitudes = respuestaAsincrona4.getTransmisiones().getTransmisionDatos();
												
							// procesamos cada solicitud con intermediacion
							for (es.redsara.intermediacion.scsp.esquemas.educacion1990noUniv.ws.respuesta.TransmisionDatos solicitud_transmision : datosSolicitudes) {
												
								// solicitud comun del ciudadano
								SolicitudBean solicitud_ciudadano = buscaSolicitudComunTitulacionNoUniv1990(solicitud_transmision);
								
								if (solicitud_ciudadano != null) {
									logger.info(STRING_NIF + solicitud_ciudadano.getNif());
									logger.info(STRING_NUMEROSOLICITUD + solicitud_ciudadano.getNumeroSolicitud());
									logger.info(STRING_ID_SOLICITUD + solicitud_ciudadano.getId());
									
									// comprobacion si ya se ha verificado previamente la familia numerosa de esa solicitud
									Boolean titulacion_no_univ_1990_verificado_previo = tituloVerificadoPrevio(solicitud_ciudadano);
										
									if (!titulacion_no_univ_1990_verificado_previo) {
										
										try {
											// compruebo si hay error devuelto por servicio de intermediacion respecto la consulta por lote de de fnumerosa
											String error_consulta_lote = solicitud_transmision.getDatosEspecificos().getRetorno().getEstado().getLiteralError();
											String cod_estado_lote = solicitud_transmision.getDatosEspecificos().getRetorno().getEstado().getCodigoEstado();
											
											// si el codigo de estado no es 0003 hay error para registrar en log-intermediacion
											if (error_consulta_lote != null && !cod_estado_lote.equals(Constantes.CODIGO_ESTADO_TRAMITADA)) {
												logger.warn(STRING_COD_ESTADO + cod_estado_lote + " : titulo no universitario anterior a 1990 de la solicitud no válido segun intermediacion - ERROR LOG_INTERMEDIACION");
												insertarLogIntermediacion(solicitud_ciudadano, error_consulta_lote, cod_estado_lote, idPet);
												
												// seteo el campo titulo_verificado a no verificado ('R')
												solicitud_ciudadano.setTituloVerificado(Constantes.TITULO_NO_VERIFICADO);
												solicitudesManager.modificarSolicitud(solicitud_ciudadano);
												logger.info(STRING_RESULTADO_COTEJAMIENTO + Constantes.TITULO_NO_VERIFICADO);
											} else {
												// si el codigo de estado es 0003 existe el titulo de familia numerosa y es correcto segun intermediacion
												logger.info(STRING_COD_ESTADO + cod_estado_lote + " : titulo no universitario anterior a 1990 de la solicitud correcto segun intermediacion, se procede a verificar - OK LOG_INTERMEDIACION");
												insertarLogIntermediacion(solicitud_ciudadano, error_consulta_lote, cod_estado_lote, idPet);
												
												// COTEJAMIENTO
																									
												// lista de titulos del ciudadano segun intermediacion
												es.redsara.intermediacion.scsp.esquemas.educacion1990noUniv.datosespecificos.DatosTitulacion[] titulos_univ_1990_intermediacion = solicitud_transmision.getDatosEspecificos().getRetorno().getListaTitulares()[0].getListaTitulos();
													
												// cotejamos los datos recibidos con lo que introdujo el usuario 
												Boolean cotejado = validarTituloNoUniversitario1990(solicitud_ciudadano, titulos_univ_1990_intermediacion);
																			
												// actualizamos el dato de familiaNumerosa_verificado en función de la validación
												if (cotejado) {
													// seteo el campo titulo_verificado a si verificado ('A')
													solicitud_ciudadano.setTituloVerificado(Constantes.TITULO_VERIFICADO);
													logger.info(STRING_RESULTADO_COTEJAMIENTO + Constantes.TITULO_VERIFICADO);
												} else {
													// seteo el campo titulo_verificado a no verificado ('R')
													solicitud_ciudadano.setTituloVerificado(Constantes.TITULO_NO_VERIFICADO);
													logger.info(STRING_RESULTADO_COTEJAMIENTO + Constantes.TITULO_NO_VERIFICADO);
												}
												solicitudesManager.modificarSolicitud(solicitud_ciudadano);
											}
										} catch (Exception e) {
											// log consulta a intermediacion 'error'
											logger.warn("la solicitud a intermediacion de titulaciones no universitarias anteriores a 1990 no puede obtener la informacion requerida - ERROR LOG_INTERMEDIACION\n", e);
											logger.error("la solicitud a intermediacion de titulaciones no universitarias anteriores a 1990 no puede obtener la informacion requerida - ERROR LOG_INTERMEDIACION\n", e);
											if (e.getMessage() != null) {
												insertarLogIntermediacion(solicitud_ciudadano,e.getMessage(),"",idPet);
											} else {
												insertarLogIntermediacion(solicitud_ciudadano,Constantes.LOG_ERROR_EXCEPCION_NO_ENCONTRADA,"",idPet);
											}
										}
									} else {
										logger.info(STRING_SOLICITUD_VERIFICADA_PREVIAMENTE + Constantes.TITULO_VERIFICADO );
									}	
								} else {
									logger.info(STRING_SOLICITUD_CIUDADANO_PETICION_NO_ENCONTRADA);
								}
							}
						// actualizamos la tabla de batch_intermediacion para no volver a preguntar por esa petición
						registro.setRespuesta(true);
						registro.setFhFinConsulta(fecha);
						batchIntermediacionManager.actualizarIntermediacion(registro, registro.getIdConsulta());
//						crearAlerta(registro,Constantes.ID_TIPO_ALERTA_NO_UNIVERSITARIA_1990);
						logger.info(STRING_RESPUESTA_CAMBIADA_1_FIN_PETICION);
						}
					} catch (RemoteException e) {
						// log de error por SoapFault
						logger.warn(STRING_ERROR_SOAPFAULT_ERROR_LOG_SERVICIO);
						logger.error(STRING_ERROR_SOAPFAULT_ERROR_LOG_SERVICIO,e);
						insertarLogServicioSoapFault(e);
						// actualizo el numero de reintentos
						registro.setReintentos(reintentos + 1);
						batchIntermediacionManager.actualizarIntermediacion(registro, registro.getIdConsulta());
						// si los reintentos igualan o superan a 5 se pone la respuesta a 1 y se para de intentar
						if (registro.getReintentos() >= 5) {
							registro.setRespuesta(true);
							registro.setFhFinConsulta(fecha);
							batchIntermediacionManager.actualizarIntermediacion(registro, registro.getIdConsulta());
							logger.warn("5 intentos alcanzados en el intento de procesar la solicitud de titulaciones no universitarias anteriores a 1990 a intermediacion - FIN DE PETICION\n");
							break;
						}
					}
					break;
					
				case 5:
					
					logger.info(STRING_IDPETICIONNUMERO + idPet);
					
					// creacion del proxy asociado al servicio web de familia numerosa
					es.redsara.intermediacion.scsp.esquemas.fnumerosa.ws.ScspwsProxy proxy5 = new es.redsara.intermediacion.scsp.esquemas.fnumerosa.ws.ScspwsProxy(url);
					
					try {
						
						// invocamos  al metodo solicutdRespuesta
						es.redsara.intermediacion.scsp.esquemas.fnumerosa.ws.respuesta.Respuesta respuestaAsincrona5 = proxy5.solicitudRespuesta(solicitudRespuesta);
						
						// obtencion de datos para el log de la respuesta (tabla log_servicio)
						String literal_error_servicio = respuestaAsincrona5.getAtributos().getEstado().getLiteralError();
						String codigo_estado_servicio = respuestaAsincrona5.getAtributos().getEstado().getCodigoEstado();
						Long tiempo_respuesta_servicio = respuestaAsincrona5.getAtributos().getEstado().getTiempoEstimadoRespuesta();
						
						// si el codigo de estado no es 0003 hay error para registrar en log-servicio 0002
						if (literal_error_servicio != null && !codigo_estado_servicio.equals(Constantes.CODIGO_ESTADO_TRAMITADA)) {
							logger.warn(STRING_COD_ESTADO + codigo_estado_servicio + " : solicitud a intermediacion de titulo de familia numerosa procesada sin exito - ERROR TABLA LOG_SERVICIO\n");
							insertarLogServicio(literal_error_servicio,codigo_estado_servicio,tiempo_respuesta_servicio);
							// actualizo el numero de reintentos
							registro.setReintentos(reintentos + 1);
							batchIntermediacionManager.actualizarIntermediacion(registro, registro.getIdConsulta());
							// si el numero de reintentos llega a 5 se pone la respuesta a 1
							if (registro.getReintentos() >= 5) {
								registro.setRespuesta(true);
								registro.setFhFinConsulta(fecha);
								batchIntermediacionManager.actualizarIntermediacion(registro, registro.getIdConsulta());
								logger.warn("5 intentos alcanzados en el intento de procesar la peticion de titulo de familia numerosa a intermediacion - FIN DE PETICION\n");
								break;
							}
						} else {	
							// si es 0003 la peticion a intermediacion ha sido procesada con exito					
							logger.info("cod. estado 0003: peticion a intermediacion de titulo de familia numerosa tramitada con exito - OK LOG_SERVICIO");
							insertarLogServicio(literal_error_servicio,codigo_estado_servicio,tiempo_respuesta_servicio);
							
							// obtenemos la respuesta, es decir, los datos completos de todas las solicitudes
							es.redsara.intermediacion.scsp.esquemas.fnumerosa.ws.respuesta.TransmisionesTransmisionDatos[] datosSolicitudes = respuestaAsincrona5.getTransmisiones().getTransmisionDatos();
							
							// procesamos cada solicitud con intermediacion
							for (es.redsara.intermediacion.scsp.esquemas.fnumerosa.ws.respuesta.TransmisionesTransmisionDatos solicitud_transmision : datosSolicitudes) {
												
								// solicitud comun del ciudadano
								SolicitudBean solicitud_ciudadano = buscaSolicitudComunFNumerosa(solicitud_transmision);
								
								if (solicitud_ciudadano != null) {
									logger.info(STRING_NIF + solicitud_ciudadano.getNif());
									logger.info(STRING_NUMEROSOLICITUD + solicitud_ciudadano.getNumeroSolicitud());
									logger.info(STRING_ID_SOLICITUD + solicitud_ciudadano.getId());
		 
									try {
										// compruebo si hay error devuelto por servicio de intermediacion respecto la consulta por lote de de fnumerosa
										String error_consulta_lote = solicitud_transmision.getDatosEspecificos().getRetorno().getEstado().getLiteralError();
										String cod_estado_lote = solicitud_transmision.getDatosEspecificos().getRetorno().getEstado().getCodigoEstado();
										
										// si el codigo de estado no es 0 hay error para registrar en log-intermediacion
										if (error_consulta_lote != null && !cod_estado_lote.equals(Constantes.CODIGO_ESTADO_EXISTE_DOCUMENTO)) {
											logger.warn(STRING_COD_ESTADO + cod_estado_lote + " : titulo de familia numerosa de la solicitud no valido segun intermediacion - ERROR LOG_INTERMEDIACION");
											insertarLogIntermediacion(solicitud_ciudadano, error_consulta_lote, cod_estado_lote, idPet);
											
											// seteo el campo fnumerosa_verificado a no verificado ('R')
											solicitud_ciudadano.setFnumerosaVerificado(Constantes.FNUMEROSA_NO_VERIFICADO);
											solicitudesManager.modificarSolicitud(solicitud_ciudadano);
											logger.info(STRING_RESULTADO_COTEJAMIENTO + Constantes.FNUMEROSA_NO_VERIFICADO);
										} else {
											// si el codigo de estado es 0 existe el titulo de familia numerosa y es correcto segun intermediacion
											logger.info("cod. estado " + cod_estado_lote + " : titulo de familia numerosa de la solicitud correcto segun intermediacion, se procede a verificar - OK LOG_INTERMEDIACION");
											insertarLogIntermediacion(solicitud_ciudadano, error_consulta_lote, cod_estado_lote, idPet);
											
											// RECEPCION DATOS INTERMEDIACION
											
											// vigencia del titulo de la solicitud (S o N) segun intermediacion
											String titulo_vigente_intermediacion = solicitud_transmision.getDatosEspecificos().getRetorno().getTituloFamiliaNumerosaRetorno().getTituloVigente().getValue();
											
											// categoria de la solicitud (G o E) segun intermediacion
											String categoria_intermediacion = solicitud_transmision.getDatosEspecificos().getRetorno().getTituloFamiliaNumerosaRetorno().getCategoria().getValue();
											
											// COTEJAMIENTO
											
											// comprobacion si ya se ha verificado previamente la familia numerosa de esa solicitud
											Boolean fnumerosa_verificado_previo = fNumerosaVerificadoPrevio(solicitud_ciudadano);
												
											if (!fnumerosa_verificado_previo) {
														
												// cotejamos los datos recibidos con lo que introdujo el usuario 
												Boolean cotejado = validarFamiliaNumerosa(solicitud_ciudadano, titulo_vigente_intermediacion, categoria_intermediacion);
																		
												// actualizamos el dato de familiaNumerosa_verificado en función de la validación
												if (cotejado) {
													// seteo el campo fnumerosa_verificado a si verificado ('A')
													
													solicitud_ciudadano.setFnumerosaVerificado(Constantes.FNUMEROSA_VERIFICADO);
													logger.info(STRING_RESULTADO_COTEJAMIENTO + Constantes.FNUMEROSA_VERIFICADO);
												} else {
													// seteo el campo fnumerosa_verificado a no verificado ('R')
													solicitud_ciudadano.setFnumerosaVerificado(Constantes.FNUMEROSA_NO_VERIFICADO);
													logger.info(STRING_RESULTADO_COTEJAMIENTO + Constantes.FNUMEROSA_NO_VERIFICADO);
												}
												solicitudesManager.modificarSolicitud(solicitud_ciudadano);
											} else {
												logger.info(STRING_SOLICITUD_VERIFICADA_PREVIAMENTE + Constantes.FNUMEROSA_VERIFICADO );
											}
										}
									} catch (Exception e) {
										// log consulta a intermediacion 'error'
										logger.warn("la solicitud a intermediacion del titulo de familia numerosa no puede obtener la informacion requerida - ERROR LOG_INTERMEDIACION\n", e);
										logger.error("la solicitud a intermediacion del titulo de familia numerosa no puede obtener la informacion requerida - ERROR LOG_INTERMEDIACION\n", e);
										if (e.getMessage() != null) {
											insertarLogIntermediacion(solicitud_ciudadano,e.getMessage(),"",idPet);
										} else {
											insertarLogIntermediacion(solicitud_ciudadano,Constantes.LOG_ERROR_EXCEPCION_NO_ENCONTRADA,"",idPet);
										}
										
									}
								} else {
									logger.info(STRING_SOLICITUD_CIUDADANO_PETICION_NO_ENCONTRADA);
								}
							}
						// actualizamos la tabla de batch_intermediacion para no volver a preguntar por esa petición
						registro.setRespuesta(true);
						registro.setFhFinConsulta(fecha);
						batchIntermediacionManager.actualizarIntermediacion(registro, registro.getIdConsulta());
//						crearAlerta(registro,Constantes.ID_TIPO_ALERTA_FNUMEROSA);
						logger.info(STRING_RESPUESTA_CAMBIADA_1_FIN_PETICION);
						}
					} catch (RemoteException e) {
						// log de error por SoapFault
						logger.warn(STRING_ERROR_SOAPFAULT_ERROR_LOG_SERVICIO);
						logger.error(STRING_ERROR_SOAPFAULT_ERROR_LOG_SERVICIO,e);
						insertarLogServicioSoapFault(e);
						// actualizo el numero de reintentos
						registro.setReintentos(reintentos + 1);
						batchIntermediacionManager.actualizarIntermediacion(registro, registro.getIdConsulta());
						// si los reintentos igualan o superan a 5 se pone la respuesta a 1 y se para de intentar
						if (registro.getReintentos() >= 5) {
							registro.setRespuesta(true);
							registro.setFhFinConsulta(fecha);
							batchIntermediacionManager.actualizarIntermediacion(registro, registro.getIdConsulta());
							logger.warn("5 intentos alcanzados en el intento de procesar la solicitud de titulo de familia numerosa a intermediacion - FIN DE PETICION\n");
							break;
						}
					}
					break;
					
					case 6:
					
						logger.info(STRING_IDPETICIONNUMERO + idPet);
							
						// creacion del proxy asociado al servicio web de discapacidad
						es.redsara.intermediacion.scsp.esquemas.discapacidad.ws.ScspwsProxy proxy6 = new es.redsara.intermediacion.scsp.esquemas.discapacidad.ws.ScspwsProxy(url);
								
						try {
							
							// invocacion  del metodo solicutdRespuesta
							es.redsara.intermediacion.scsp.esquemas.discapacidad.ws.respuesta.Respuesta respuestaAsincrona6 = proxy6.solicitudRespuesta(solicitudRespuesta);
											
							// obtencion de datos para el log de la respuesta (tabla log_servicio)
							String literal_error_servicio = respuestaAsincrona6.getAtributos().getEstado().getLiteralError();
							String codigo_estado_servicio = respuestaAsincrona6.getAtributos().getEstado().getCodigoEstado();
							Long tiempo_respuesta_servicio = respuestaAsincrona6.getAtributos().getEstado().getTiempoEstimadoRespuesta();
							
							// si el codigo de estado no es 0003 hay error para registrar en log-servicio 0002
							if (literal_error_servicio != null && !codigo_estado_servicio.equals(Constantes.CODIGO_ESTADO_TRAMITADA)) {
								logger.warn(STRING_COD_ESTADO + codigo_estado_servicio + " : solicitud a intermediacion de discapacidad procesada sin exito - ERROR TABLA LOG_SERVICIO\n");
								insertarLogServicio(literal_error_servicio,codigo_estado_servicio,tiempo_respuesta_servicio);
								// actualizo el numero de reintentos
								registro.setReintentos(reintentos + 1);
								batchIntermediacionManager.actualizarIntermediacion(registro, registro.getIdConsulta());
								// si el numero de reintentos llega a 5 se pone la respuesta a 1
								if (registro.getReintentos() >= 5) {
									registro.setRespuesta(true);
									registro.setFhFinConsulta(fecha);
									batchIntermediacionManager.actualizarIntermediacion(registro, registro.getIdConsulta());
									logger.warn("5 intentos alcanzados en el intento de procesar la peticion de discapacidad a intermediacion - FIN DE PETICION\n");
									break;
								}
							} else {
								// si es 0003 la peticion a intermediacion ha sido procesada con exito
								logger.info("cod. estado 0003: peticion a intermediacion de discapacidad tramitada con exito - OK LOG_SERVICIO");
								insertarLogServicio(literal_error_servicio,codigo_estado_servicio,tiempo_respuesta_servicio);
								
								// obtencion de la respuesta, es decir, los datos completos de todas las solicitudes
								es.redsara.intermediacion.scsp.esquemas.discapacidad.ws.respuesta.TransmisionesTransmisionDatos[] datosSolicitudes = respuestaAsincrona6.getTransmisiones().getTransmisionDatos();
								
								// procesamos cada solicitud con intermediacion
								for (es.redsara.intermediacion.scsp.esquemas.discapacidad.ws.respuesta.TransmisionesTransmisionDatos solicitud_transmision : datosSolicitudes) {
									
									// solicitud comun del ciudadano
									SolicitudBean solicitud_ciudadano = buscaSolicitudComunDiscapacidad(solicitud_transmision);
									
									if (solicitud_ciudadano != null ) {
										logger.info(STRING_NIF + solicitud_ciudadano.getNif());
										logger.info(STRING_NUMEROSOLICITUD + solicitud_ciudadano.getNumeroSolicitud());
										logger.info(STRING_ID_SOLICITUD + solicitud_ciudadano.getId());
										
										try {
																	
											// compruebo si hay error devuelto por servicio de intermediacion respecto la consulta por lote de de discapacidad
											String error_consulta_lote = solicitud_transmision.getDatosEspecificos().getRetorno().getEstado().getLiteralError();
											String cod_estado_lote = solicitud_transmision.getDatosEspecificos().getRetorno().getEstado().getCodigoEstado();
											
											// si el codigo de estado no es 0 hay error para registrar en log-intermediacion
											if (error_consulta_lote != null && !cod_estado_lote.equals(Constantes.CODIGO_ESTADO_EXISTE_DOCUMENTO)) {
												logger.warn(STRING_COD_ESTADO + cod_estado_lote + " : documento de discapacidad de la solicitud no valido segun intermediacion - ERROR LOG_INTERMEDIACION");
												insertarLogIntermediacion(solicitud_ciudadano,error_consulta_lote, cod_estado_lote, idPet);
												
												// seteo el campo discapacidad_verificado a no verificado ('R')
												solicitud_ciudadano.setDiscapacidadVerificado(Constantes.DISCAPACIDAD_NO_VERIFICADO);
												solicitudesManager.modificarSolicitud(solicitud_ciudadano);
												logger.info(STRING_RESULTADO_COTEJAMIENTO + Constantes.DISCAPACIDAD_NO_VERIFICADO);
											} else {
												// si el codigo de estado es 0 existe el documento de discapacidad y es correcto segun intermediacion
												logger.info(STRING_COD_ESTADO + cod_estado_lote + " : documento de discapacidad de la solicitud correcto segun intermediacion, se procede a verificar - OK LOG_INTERMEDIACION");
												insertarLogIntermediacion(solicitud_ciudadano,error_consulta_lote,cod_estado_lote, idPet);
												
												// RECEPCION DATOS INTERMEDIACION
												
												// necesito el grado discapacidad segun intermediacion
												Integer grado_discapacidad_intermediacion = solicitud_transmision.getDatosEspecificos().getRetorno().getCertificadoDatosDiscapacidad().getGradoDiscapacidad();
												
												// COTEJAMIENTO 
												
												// comprobacion si ya se ha verificado previamente la familia numerosa de esa solicitud
												Boolean discapacidad_verificado_previo = discapacidadVerificadoPrevio(solicitud_ciudadano);
													
												if (!discapacidad_verificado_previo) {
													
													// cotejamos los datos recibidos con lo que introdujo el usuario 
													Boolean exencion_aceptada = validarDiscapacidad(grado_discapacidad_intermediacion);
																	
													// actualizamos el dato de discapacidad_verificado en función de la validación
													if (exencion_aceptada) {
														// seteo el campo discapacidad_verificado a si verificado ('A')
														solicitud_ciudadano.setDiscapacidadVerificado(Constantes.DISCAPACIDAD_VERIFICADO);
														logger.info(STRING_RESULTADO_COTEJAMIENTO + Constantes.DISCAPACIDAD_VERIFICADO);
													} else {
														// seteo el campo discapacidad_verificado a no verificado ('R')
														solicitud_ciudadano.setDiscapacidadVerificado(Constantes.DISCAPACIDAD_NO_VERIFICADO);
														logger.info(STRING_RESULTADO_COTEJAMIENTO + Constantes.DISCAPACIDAD_NO_VERIFICADO);
													}
													solicitudesManager.modificarSolicitud(solicitud_ciudadano);
												} else {
													logger.info(STRING_SOLICITUD_VERIFICADA_PREVIAMENTE + Constantes.DISCAPACIDAD_VERIFICADO);
												}
											}
										} catch (Exception e) {
											// log consulta a intermediacion 'error'
											logger.warn("la solicitud a intermediacion de discapacidad no puede obtener la informacion requerida - ERROR LOG_INTERMEDIACION\n", e);
											logger.error("la solicitud a intermediacion de discapacidad no puede obtener la informacion requerida - ERROR LOG_INTERMEDIACION\n", e);
											if (e.getMessage() != null) {
												insertarLogIntermediacion(solicitud_ciudadano,e.getMessage(),"",idPet);
											} else {
												insertarLogIntermediacion(solicitud_ciudadano,Constantes.LOG_ERROR_EXCEPCION_NO_ENCONTRADA,"",idPet);
											}
											
										}
									} else {
										logger.info(STRING_SOLICITUD_CIUDADANO_PETICION_NO_ENCONTRADA);
									}
								}
							// actualizamos la tabla de batch_intermediacion para no volver a preguntar por esa petición
							registro.setRespuesta(true);
							registro.setFhFinConsulta(fecha);
							batchIntermediacionManager.actualizarIntermediacion(registro, registro.getIdConsulta());
//							crearAlerta(registro,Constantes.ID_TIPO_ALERTA_DISCAPACIDAD);
							logger.info(STRING_RESPUESTA_CAMBIADA_1_FIN_PETICION);
							}
						} catch (RemoteException e) {
							// log de error por excepcion 
							logger.warn(STRING_ERROR_SOAPFAULT_ERROR_LOG_SERVICIO);
							logger.error(STRING_ERROR_SOAPFAULT_ERROR_LOG_SERVICIO,e);
							insertarLogServicioSoapFault(e);
							// actualizo el numero de reintentos
							registro.setReintentos(reintentos + 1);
							batchIntermediacionManager.actualizarIntermediacion(registro, registro.getIdConsulta());
							// si los reintentos igualan o superan a 5 se pone la respuesta a 1 y se para de intentar
							if (registro.getReintentos() >= 5) {
								registro.setRespuesta(true);
								registro.setFhFinConsulta(fecha);
								batchIntermediacionManager.actualizarIntermediacion(registro, registro.getIdConsulta());
								logger.warn("5 intentos alcanzados en el intento de procesar la solicitud de discapacidad a intermediacion - FIN DE PETICION\n");
								break;
							}
						}
						break;
					
					case 7:
						
						logger.info(STRING_IDPETICIONNUMERO + idPet);
						
						// creacion del proxy asociado al servicio web de dias de desempleo
						es.redsara.intermediacion.scsp.esquemas.sepe.ws.ScspwsProxy proxy7 = new es.redsara.intermediacion.scsp.esquemas.sepe.ws.ScspwsProxy(url);
								
						try {
							
							// invocacion  del metodo solicutdRespuesta
							es.redsara.intermediacion.scsp.esquemas.sepe.ws.respuesta.Respuesta respuestaAsincrona7 = proxy7.solicitudRespuesta(solicitudRespuesta);
											
							// obtencion de datos para el log de la respuesta (tabla log_servicio)
							String literal_error_servicio = respuestaAsincrona7.getAtributos().getEstado().getLiteralError();
							String codigo_estado_servicio = respuestaAsincrona7.getAtributos().getEstado().getCodigoEstado();
							Long tiempo_respuesta_servicio = respuestaAsincrona7.getAtributos().getEstado().getTiempoEstimadoRespuesta();
							
							// si el codigo de estado no es 0003 hay error para registrar en log-servicio 0002
							if (literal_error_servicio != null && !codigo_estado_servicio.equals(Constantes.CODIGO_ESTADO_TRAMITADA)) {
								logger.warn(STRING_COD_ESTADO + codigo_estado_servicio + " : solicitud a intermediacion de dias de desempleo procesada sin exito - ERROR TABLA LOG_SERVICIO\n");
								insertarLogServicio(literal_error_servicio,codigo_estado_servicio,tiempo_respuesta_servicio);
								// actualizo el numero de reintentos
								registro.setReintentos(reintentos + 1);
								batchIntermediacionManager.actualizarIntermediacion(registro, registro.getIdConsulta());
								// si el numero de reintentos llega a 5 se pone la respuesta a 1
								if (registro.getReintentos() >= 5) {
									registro.setRespuesta(true);
									registro.setFhFinConsulta(fecha);
									batchIntermediacionManager.actualizarIntermediacion(registro, registro.getIdConsulta());
									logger.warn("5 intentos alcanzados en el intento de procesar la peticion de dias de desempleo a intermediacion - FIN DE PETICION\n");
									break;
								}
							} else {
								// si es 0003 la peticion a intermediacion ha sido procesada con exito
								logger.info("cod. estado 0003: peticion a intermediacion de dias de desempleo tramitada con exito - OK LOG_SERVICIO");
								insertarLogServicio(literal_error_servicio,codigo_estado_servicio,tiempo_respuesta_servicio);
								
								// obtencion de la respuesta, es decir, los datos completos de todas las solicitudes
								es.redsara.intermediacion.scsp.esquemas.sepe.ws.respuesta.TransmisionesTransmisionDatos[] datosSolicitudes = respuestaAsincrona7.getTransmisiones().getTransmisionDatos();
								
								// procesamos cada solicitud con intermediacion
								for (es.redsara.intermediacion.scsp.esquemas.sepe.ws.respuesta.TransmisionesTransmisionDatos solicitud_transmision : datosSolicitudes) {
									
									// solicitud comun del ciudadano
									SolicitudBean solicitud_ciudadano = buscaSolicitudComunDiasDesempleo(solicitud_transmision);
									
									if (solicitud_ciudadano != null) {
										logger.info(STRING_NIF + solicitud_ciudadano.getNif());
										logger.info(STRING_NUMEROSOLICITUD + solicitud_ciudadano.getNumeroSolicitud());
										logger.info(STRING_ID_SOLICITUD + solicitud_ciudadano.getId());
										
										try {
																			
											// compruebo si hay error devuelto por servicio de intermediacion respecto la consulta por lote de de dias de desempleo
											String error_consulta_lote = solicitud_transmision.getDatosEspecificos().getRetorno().getEstado().getLiteralError();
											String cod_estado_lote = solicitud_transmision.getDatosEspecificos().getRetorno().getEstado().getCodigoEstado();
											
											// si el codigo de estado no es 0003 hay error para registrar en log-intermediacion
											if (error_consulta_lote != null && !cod_estado_lote.equals(Constantes.CODIGO_ESTADO_TRAMITADA)) {
												logger.warn(STRING_COD_ESTADO + cod_estado_lote + " : documento de dias de desempleo de la solicitud no valido segun intermediacion - ERROR LOG_INTERMEDIACION"); 
												insertarLogIntermediacion(solicitud_ciudadano, error_consulta_lote,cod_estado_lote,idPet);
												
												// seteo el campo desempleo_verificado a no verificado ('R')
												solicitud_ciudadano.setDesempleoVerificado(Constantes.DESEMPLEO_NO_VERIFICADO);
												solicitudesManager.modificarSolicitud(solicitud_ciudadano);
												logger.info(STRING_RESULTADO_COTEJAMIENTO + Constantes.DESEMPLEO_NO_VERIFICADO);
											} else {
												// si el codigo de estado es 0003 existe el documento de discapacidad y es correcto segun intermediacion
												logger.info(STRING_COD_ESTADO + cod_estado_lote + " : dias de desempleo de la solicitud correcto segun intermediacion, se procede a verificar - OK LOG_INTERMEDIACION");
												insertarLogIntermediacion(solicitud_ciudadano,error_consulta_lote,cod_estado_lote, idPet);
												
												// RECEPCION DATOS INTERMEDIACION
												
												// dias inscrito en el paro segun intermediacion				
												Integer dias_demandante_empleo = solicitud_transmision.getDatosEspecificos().getRetorno().getDemandanteEmpleo().getLargaDuracion().getDiasDemandanteEmpleo();
												
												// COTEJAMIENTO
												
												// comprobamos que el ciudadano cumple con los dias de demandante necesarios 
												Boolean dias_aceptados = validarDiasDesempleo(dias_demandante_empleo);
												
												// comprobamos el estado de verificacion actual, obteniendo el campo de desempleo_verificado de la tabla solicitud_comun
												Character verificacion_actual = solicitud_ciudadano.getDesempleoVerificado();
												
												// actualizamos el dato de desempleo_verificado en función de la validación y el estado de la verificacion actual
												if (dias_aceptados && (verificacion_actual != Constantes.DESEMPLEO_NO_VERIFICADO)) {
													// seteo a si verificado ('A')
													solicitud_ciudadano.setDesempleoVerificado(Constantes.DESEMPLEO_VERIFICADO);
													logger.info(STRING_RESULTADO_COTEJAMIENTO + Constantes.DESEMPLEO_VERIFICADO);
												} else {
													// seteo el campo a no verificado ('R')
													solicitud_ciudadano.setDesempleoVerificado(Constantes.DESEMPLEO_NO_VERIFICADO);
													logger.info(STRING_RESULTADO_COTEJAMIENTO + Constantes.DESEMPLEO_NO_VERIFICADO);
												} 
												solicitudesManager.modificarSolicitud(solicitud_ciudadano);
											}
										} catch (Exception e) {
											//log de error por excepcion
											logger.warn(STRING_SOLICITUD_INTERMEDIACION+ solicitud_ciudadano.getId() + STRING_NO_PUEDE_OBTENER_INFORMACION_ERROR_LOG_INTERMEDIACION, e);
											logger.error(STRING_SOLICITUD_INTERMEDIACION+ solicitud_ciudadano.getId() + STRING_NO_PUEDE_OBTENER_INFORMACION_ERROR_LOG_INTERMEDIACION, e);
											if (e.getMessage() != null) {
												insertarLogIntermediacion(solicitud_ciudadano,e.getMessage(),"",idPet);
											} else {
												insertarLogIntermediacion(solicitud_ciudadano,Constantes.LOG_ERROR_EXCEPCION_NO_ENCONTRADA,"",idPet);
											}
										}
									}
								}
								
							// actualizamos la tabla de batch_intermediacion para no volver a preguntar por esa petición
							registro.setRespuesta(true);
							registro.setFhFinConsulta(fecha);
							batchIntermediacionManager.actualizarIntermediacion(registro, registro.getIdConsulta());
//							crearAlerta(registro,Constantes.ID_TIPO_ALERTA_DIAS_DESEMPLEO);
							logger.info(STRING_RESPUESTA_CAMBIADA_1_FIN_PETICION);
							}
						
						} catch (RemoteException e) {
							// log de error por excepcion 
							logger.warn(STRING_ERROR_SOAPFAULT_ERROR_LOG_SERVICIO);
							logger.error(STRING_ERROR_SOAPFAULT_ERROR_LOG_SERVICIO,e);
							insertarLogServicioSoapFault(e);
							// actualizo el numero de reintentos
							registro.setReintentos(reintentos + 1);
							batchIntermediacionManager.actualizarIntermediacion(registro, registro.getIdConsulta());
							// si los reintentos igualan o superan a 5 se pone la respuesta a 1 y se para de intentar
							if (registro.getReintentos() >= 5) {
								registro.setRespuesta(true);
								registro.setFhFinConsulta(fecha);
								batchIntermediacionManager.actualizarIntermediacion(registro, registro.getIdConsulta());
								logger.warn("5 intentos alcanzados en el intento de procesar la solicitud de dias de desempleo a intermediacion - FIN DE PETICION\n");
								break;
							}
						}
						break;
					
					case 8:
						
						//El proceso del importe se hace ahora con el servicio procesarBatchNivelRenta
						break;
					
					case 9:
						logger.info(STRING_IDPETICIONNUMERO + idPet);
						
						// creacion del proxy asociado al servicio web de victimasTerrorismo
						es.redsara.intermediacion.scsp.esquemas.victimasTerrorismo.ws.ScspwsProxy proxy9 = new es.redsara.intermediacion.scsp.esquemas.victimasTerrorismo.ws.ScspwsProxy(url);
								
						try {
							
							// invocacion  del metodo solicutdRespuesta
							es.redsara.intermediacion.scsp.esquemas.victimasTerrorismo.ws.respuesta.Respuesta respuestaAsincrona9 = proxy9.solicitudRespuesta(solicitudRespuesta);
											
							// obtencion de datos para el log de la respuesta (tabla log_servicio)
							String literal_error_servicio = respuestaAsincrona9.getAtributos().getEstado().getLiteralError();
							String codigo_estado_servicio = respuestaAsincrona9.getAtributos().getEstado().getCodigoEstado();
							Long tiempo_respuesta_servicio = respuestaAsincrona9.getAtributos().getEstado().getTiempoEstimadoRespuesta();
							
							// si el codigo de estado no es 0003 hay error para registrar en log-servicio 0002
							if (literal_error_servicio != null && !codigo_estado_servicio.equals(Constantes.CODIGO_ESTADO_TRAMITADA)) {
								logger.warn(STRING_COD_ESTADO + codigo_estado_servicio + " : solicitud a intermediacion de victimas procesada sin exito - ERROR TABLA LOG_SERVICIO\n");
								insertarLogServicio(literal_error_servicio,codigo_estado_servicio,tiempo_respuesta_servicio);
								// actualizo el numero de reintentos
								registro.setReintentos(reintentos + 1);
								batchIntermediacionManager.actualizarIntermediacion(registro, registro.getIdConsulta());
								// si el numero de reintentos llega a 5 se pone la respuesta a 1
								if (registro.getReintentos() >= 5) {
									registro.setRespuesta(true);
									registro.setFhFinConsulta(fecha);
									batchIntermediacionManager.actualizarIntermediacion(registro, registro.getIdConsulta());
									logger.warn("5 intentos alcanzados en el intento de procesar la peticion de victimas a intermediacion - FIN DE PETICION\n");
									break;
								}
							} else {
								// si es 0003 la peticion a intermediacion ha sido procesada con exito
								logger.info("cod. estado 0003: peticion a intermediacion de victimas tramitada con exito - OK LOG_SERVICIO");
								insertarLogServicio(literal_error_servicio,codigo_estado_servicio,tiempo_respuesta_servicio);
								
								// obtencion de la respuesta, es decir, los datos completos de todas las solicitudes
								es.redsara.intermediacion.scsp.esquemas.victimasTerrorismo.ws.respuesta.TransmisionesTransmisionDatos[] datosSolicitudes = respuestaAsincrona9.getTransmisiones().getTransmisionDatos();
								
								// procesamos cada solicitud con intermediacion
								for (es.redsara.intermediacion.scsp.esquemas.victimasTerrorismo.ws.respuesta.TransmisionesTransmisionDatos solicitud_transmision : datosSolicitudes) {
									
									// solicitud comun del ciudadano
									SolicitudBean solicitud_ciudadano = buscaSolicitudComunVictimas(solicitud_transmision);
									
									if (solicitud_ciudadano != null ) {
										logger.info(STRING_NIF + solicitud_ciudadano.getNif());
										logger.info(STRING_NUMEROSOLICITUD + solicitud_ciudadano.getNumeroSolicitud());
										logger.info(STRING_ID_SOLICITUD + solicitud_ciudadano.getId());
										
										try {
																	
											// compruebo si hay error devuelto por servicio de intermediacion respecto la consulta por lote de de discapacidad
											String error_consulta_lote = solicitud_transmision.getDatosEspecificos().getRetorno().getCodigoEstado();
											String cod_estado_lote = solicitud_transmision.getDatosEspecificos().getRetorno().getDescripcion();
											
											// si el codigo de estado no es 0 hay error para registrar en log-intermediacion
											if (error_consulta_lote != null && !cod_estado_lote.equals(Constantes.CODIGO_ESTADO_EXISTE_DOCUMENTO)) {
												logger.warn(STRING_COD_ESTADO + cod_estado_lote + " : documento de discapacidad de la solicitud no valido segun intermediacion - ERROR LOG_INTERMEDIACION");
												insertarLogIntermediacion(solicitud_ciudadano,error_consulta_lote, cod_estado_lote, idPet);
												
												// seteo el campo discapacidad_verificado a no verificado ('R')
												solicitud_ciudadano.setVictimasVerificado(Constantes.VICTIMA_NO_VERIFICADO);
												solicitudesManager.modificarSolicitud(solicitud_ciudadano);
												logger.info(STRING_RESULTADO_COTEJAMIENTO + Constantes.VICTIMA_NO_VERIFICADO);
											} else {
												// si el codigo de estado es 0 existe el documento de discapacidad y es correcto segun intermediacion
												logger.info(STRING_COD_ESTADO + cod_estado_lote + " : documento de discapacidad de la solicitud correcto segun intermediacion, se procede a verificar - OK LOG_INTERMEDIACION");
												insertarLogIntermediacion(solicitud_ciudadano,error_consulta_lote,cod_estado_lote, idPet);

												Integer codigoEstado= Integer.parseInt(solicitud_transmision.getDatosEspecificos().getRetorno().getCodigoEstado());
												
													
												if (codigoEstado == 1) {
														solicitud_ciudadano.setVictimasVerificado(Constantes.VICTIMA_VERIFICADO);
														logger.info(STRING_RESULTADO_COTEJAMIENTO + Constantes.VICTIMA_VERIFICADO);
												} else {
														// seteo el campo victimas_verificado a no verificado ('R')
														solicitud_ciudadano.setVictimasVerificado(Constantes.VICTIMA_NO_VERIFICADO);
														logger.info(STRING_RESULTADO_COTEJAMIENTO + Constantes.VICTIMA_NO_VERIFICADO);
												}
												solicitudesManager.modificarSolicitud(solicitud_ciudadano);
											}
										} catch (Exception e) {
											// log consulta a intermediacion 'error'
											logger.warn("la solicitud a intermediacion de victimas no puede obtener la informacion requerida - ERROR LOG_INTERMEDIACION\n", e);
											logger.error("la solicitud a intermediacion de victimas no puede obtener la informacion requerida - ERROR LOG_INTERMEDIACION\n", e);
											if (e.getMessage() != null) {
												insertarLogIntermediacion(solicitud_ciudadano,e.getMessage(),"",idPet);
											} else {
												insertarLogIntermediacion(solicitud_ciudadano,Constantes.LOG_ERROR_EXCEPCION_NO_ENCONTRADA,"",idPet);
											}
											
										}
									} else {
										logger.info(STRING_SOLICITUD_CIUDADANO_PETICION_NO_ENCONTRADA);
									}
								}
							// actualizamos la tabla de batch_intermediacion para no volver a preguntar por esa petición
							registro.setRespuesta(true);
							registro.setFhFinConsulta(fecha);
							batchIntermediacionManager.actualizarIntermediacion(registro, registro.getIdConsulta());
//							crearAlerta(registro,Constantes.ID_TIPO_ALERTA_DISCAPACIDAD);
							logger.info(STRING_RESPUESTA_CAMBIADA_1_FIN_PETICION);
							}
						} catch (RemoteException e) {
							// log de error por excepcion 
							logger.warn(STRING_ERROR_SOAPFAULT_ERROR_LOG_SERVICIO);
							logger.error(STRING_ERROR_SOAPFAULT_ERROR_LOG_SERVICIO,e);
							insertarLogServicioSoapFault(e);
							// actualizo el numero de reintentos
							registro.setReintentos(reintentos + 1);
							batchIntermediacionManager.actualizarIntermediacion(registro, registro.getIdConsulta());
							// si los reintentos igualan o superan a 5 se pone la respuesta a 1 y se para de intentar
							if (registro.getReintentos() >= 5) {
								registro.setRespuesta(true);
								registro.setFhFinConsulta(fecha);
								batchIntermediacionManager.actualizarIntermediacion(registro, registro.getIdConsulta());
								logger.warn("5 intentos alcanzados en el intento de procesar la solicitud de discapacidad a intermediacion - FIN DE PETICION\n");
								break;
							}
						}
						break;
					default:
					break;
			} 
		}
		
		
		/**
		 * ****** METODOS GENERALES *******.
		 *
		 * @param literalError el literal error
		 * @param codigoEstado el codigo estado
		 * @param tiempoEstimadoRespuesta el tiempo estimado respuesta
		 */	
		
				
		/**
		 * Inserta datos de log en la tabla de log_intermediacion
		 * @param literalError - la descripcion del error
		 * @param codigoEstado - el codigo del error
		 * @param tiempoEstimadoRespuesta - tiempo en el que se espera tener lista la respuesta
		 */
		private void insertarLogServicio(String literalError,String codigoEstado,Long tiempoEstimadoRespuesta){

			LogServicioBean logServicioBean = new LogServicioBean();
						
			logServicioBean.setFecha(new Date());
			logServicioBean.setIdTipoServicio(Constantes.TIPO_SERVICIO_BATCH);
			if (literalError != null && !codigoEstado.equals(Constantes.CODIGO_ESTADO_TRAMITADA)){
				logServicioBean.setResultado(Constantes.REGISTRO_RESULTADO_ERROR);
				logServicioBean.setIdTipoError('F');
				logServicioBean.setTiempoRespuesta(tiempoEstimadoRespuesta);
			} else {
				logServicioBean.setResultado(Constantes.REGISTRO_RESULTADO_OK);
			}
			logServicioBean.setDesTipoError(literalError);
			logServicioBean.setCodigoError(codigoEstado);
			logServicioBean.setPrueba("N");
			
			logServicioManager.guardarLogServicio(logServicioBean);
		}
		
		/**
		 * Inserta en la tabla log_servicio los errores SoapFault.
		 *
		 * @param exception - la excepcion RemoteException
		 */
		private void insertarLogServicioSoapFault(RemoteException exception) {
			LogServicioBean logServicioBean = new LogServicioBean();
			String codigo_estado = "";
			String literal_error = "error indefinido - " + exception.getMessage();
			
			// recogo de soapFault los valores codigo de estado y la descripcion del error
			if(exception instanceof AxisFault){
				AxisFault excepcionAxis = (AxisFault) exception;
				Element fault[] = excepcionAxis.getFaultDetails();
				
				if (fault != null && fault.length > 0) {
					codigo_estado = localizaValueNodo(fault[0],Constantes.NODO_SOAPFAULT_CODIGO_ESTADO);
					literal_error = localizaValueNodo(fault[0],Constantes.NODO_SOAPFAULT_LITERAL_ERROR);
				}
			}
			
			// hago la operacion de insercion en bb.dd
			logServicioBean.setFecha(new Date());
			logServicioBean.setIdTipoServicio(7);
			logServicioBean.setResultado("ER");
			logServicioBean.setIdTipoError('F');
			logServicioBean.setDesTipoError(literal_error);
			logServicioBean.setCodigoError(codigo_estado);
			logServicioBean.setTiempoRespuesta(null);
			logServicioBean.setPrueba("N");
			
			logServicioManager.guardarLogServicio(logServicioBean);
		}
		
		/**
		 * Localiza value nodo.
		 *
		 * @param elemento el elemento
		 * @param nombreNodo el nombre nodo
		 * @return el string
		 */
		public String localizaValueNodo(Element elemento, String nombreNodo) {
			String nodeValue = "";
			NodeList lista = elemento.getChildNodes();
			
			for (int i=0; i<lista.getLength();i++) {
				Node nodo = lista.item(i);
				if (nodo.getNodeName().equals(nombreNodo)) {
					nodeValue = nodo.getTextContent();
					break;
				}
			}
			return nodeValue; 	
		}
		
	
		/**
		 * Inserta datos de log en la tabla log_intermediacion.
		 *
		 * @param solicitudBean - la solicitud comun respecto la que se hace log
		 * @param literalError - el error que se seteara en descripcion
		 * @param codigoEstado el codigo estado
		 * @param idPet - el id de peticion en la tabla batch_intermediacion
		 */
		private void insertarLogIntermediacion(SolicitudBean solicitudBean, String literalError, String codigoEstado, String idPet){

			LogIntermediacionBean logIntermediacionBean = new LogIntermediacionBean();
			
			logIntermediacionBean.setSolicitudComun(solicitudesManager.toSolicitud(solicitudBean));
			logIntermediacionBean.setIdPeticion(idPet);
			logIntermediacionBean.setFecha(new Date());
			if(literalError != null && !(codigoEstado.equals(Constantes.CODIGO_ESTADO_EXISTE_DOCUMENTO) || codigoEstado.equals(Constantes.CODIGO_ESTADO_TRAMITADA))) {
				logIntermediacionBean.setResultado("ER");
			}
			else{
				logIntermediacionBean.setResultado("OK");
			}
			logIntermediacionBean.setDescripcion(literalError);
			
			logIntermediacionManager.guardarLogIntermediacion(logIntermediacionBean);
		}
		

		/**
		 * Comprueba si se ha verificado previamente alguna vez el titulo universitario de esa solicitud comun (constate N o R).
		 *
		 * @param solicitudBean - la solicitud comun del ciudadano
		 * @return respuesta - Booleano con la respuesta
		 */
		private Boolean tituloVerificadoPrevio(SolicitudBean solicitudBean) {
			Boolean respuesta = false;
						
			// obtengo el campo fnumerosa_verificado de la tabla solicitud_comun
			Character titulo_verificado = solicitudBean.getTituloVerificado();
			
			// obtengo una respuesta positiva o negativa de verificacion previa
			if (titulo_verificado != null) {
				if (titulo_verificado.charValue() == Constantes.TITULO_VERIFICADO) {
					respuesta = true;
				} 
			}
			return respuesta;
		}
		

		/**
		 * Crea una alerta pop-up cuando el funcionario acceda a gestion
		 * Dicha alerte le comunica que su consulta por lotes ha sido procesada y finalizada.
		 *
		 * @param registro - el registro de la peticion de batch_intermediacion sobre la que se genera la alerta
		 * @param tipoAlerta el tipo alerta
		 */
//		private void crearAlerta(BatchIntermediacionBean registro, int tipoAlerta) {
//			
//			AlertaBean alertaBean = new AlertaBean();
//			
//			alertaBean.setIdTipo(tipoAlerta);
//			alertaBean.setIdModo(1);
//			alertaBean.setEstado(Constantes.ALERTA_ESTADO_ACTIVO);
//			Usuario usuario = registro.getIdUsuarioConsulta();
//			CentroGestor centroGestor = usuario.getCentroGestor();
//			if (centroGestor != null) {
//				alertaBean.setIdCentroGestor(centroGestor.getIdCentroGestor());	
//			}
//			alertaBean.setIdPerfil(registro.getIdUsuarioConsulta().getPerfil().getId());
//			alertaBean.setIdPeticion(registro.getIdPeticion());
//			
//			alertaManager.guardarAlerta(alertaBean);
//		}
		
		/**
		 * Crea una alerta pop-up cuando el funcionario acceda a gestion
		 * Dicha alerte le comunica que su consulta por lotes ha sido procesada y finalizada
		 * @param registro - el registro de la peticion de batch_intermediacion sobre la que se genera la alerta
		 */
//		private void crearAlertaNR(BatchNivelRentaBean registro, int tipoAlerta) {
//			
//			AlertaBean alertaBean = new AlertaBean();
//			
//			alertaBean.setIdTipo(tipoAlerta);
//			alertaBean.setIdModo(1);
//			alertaBean.setEstado(Constantes.ALERTA_ESTADO_ACTIVO);
//			Usuario usuario = registro.getIdUsuarioConsulta();
//			CentroGestor centroGestor = usuario.getCentroGestor();
//			if (centroGestor != null) {
//				alertaBean.setIdCentroGestor(centroGestor.getIdCentroGestor());	
//			}
//			alertaBean.setIdPerfil(registro.getIdUsuarioConsulta().getPerfil().getId());
//			alertaBean.setIdPeticion(properties.getProperty("SVDSEPE_COD_CONSULTA")+registro.getIdConsulta());
//			
//			alertaManager.guardarAlerta(alertaBean);
//		}
		
		/**
		 * ****** METODOS TITULOS UNIVERSITARIOS *******.
		 *
		 * @param solicitudTransmision el solicitud transmision
		 * @return el solicitud bean
		 */
			
				
		/**
		 * Busca en la tabla de la bbdd solicitud_comun la solicitud que proviene de transmision
		 * Cuando el expediente sea 123456 solo buscara por nif y nombre por lo que podra encontrar mas de una solicitud,
		 * en este caso se tratara como una excepcion de hibernate
		 * @param solicitudTransmision - la solicitud de la transmision
		 * @return solicitudBean - la solicitud comun buscada en bbdd
		 */
		private SolicitudBean buscaSolicitudComunTitulacionUniv(es.redsara.intermediacion.scsp.esquemas.educacion.ws.respuesta.TransmisionesTransmisionDatos solicitudTransmision) {
			
			SolicitudBean solicitudBean = null;
			
			// creo un objeto query para filtrar la consulta
			SolicitudComunQuery solicitudQuery = new SolicitudComunQuery();
						
			// obtengo la solicitud del ciudadano, su nif y su nombre
			String id_expediente  = solicitudTransmision.getDatosGenericos().getSolicitante().getIdExpediente();
			String nif = solicitudTransmision.getDatosGenericos().getTitular().getDocumentacion();
						
			// monto el filtro de la consulta
			solicitudQuery.setNif(nif);
			// compruebo que el id_expediente obtenido no sea el 123456 para no hacer la busqueda por ese
			if (!id_expediente.equals(properties.getProperty(STRING_SVTO_ID_EXPEDIENTE))) {
				solicitudQuery.setNumeroSolicitud(id_expediente);
			} 
			
			// busco la solicitud del ciudadano en la tabla solicitud_comun de la bb.dd
			try {
				solicitudBean = solicitudesManager.buscarSolicitudById(solicitudQuery);
			} catch (Exception e) {
				logger.error(STRING_ERROR_BUSCAR_SOLICITUD+ solicitudBean.getNumeroSolicitud(),e); 
			} 
			return solicitudBean;
		}
		
				
		/**
		 * Valida un titulo universitario
		 * Valida que el cogido general del titulo del ciudadano sea el mismo que el codigo general de interemediacion para cotejar positivamente el titulo.
		 *
		 * @param solicitudBean - la solicitud comun del ciudadano
		 * @param titulosUnivIntermediacion - los titulos del ciudadano que devuelve intermediacion
		 * @return validacion - ha sido o no valido el titulo
		 */
		private Boolean validarTituloUniversitario(SolicitudBean solicitudBean,DatosTitulacion[] titulosUnivIntermediacion) {
			Boolean validacion = false;
			String codigo_solicitud = solicitudBean.getTituloOficial().getCodigo();	// codigo en la solicitud
			
			if (codigo_solicitud != null) {
				TituloOficialBean titulo_solicitud = tituloOficialManager.obtenerTituloOficialByCodigo(codigo_solicitud);
				if (titulo_solicitud != null) {
					String codigo_general_solicitud = titulo_solicitud.getCodGeneral();	// codigo campo codigo_general tabla titulo_oficial
					if (codigo_general_solicitud != null) {
						for (DatosTitulacion datosTitulacion : titulosUnivIntermediacion) {
							String codigo_intermediacion = datosTitulacion.getDatosTitulo().getCodigoTitulacion(); // codigo de intermediacion
							// se localiza el codigo determinante (codigo campo codigo_general) para cotejar
							TituloOficialBean tituloOficial = tituloOficialManager.obtenerTituloOficialByCodigo(codigo_intermediacion);
							if (tituloOficial!=null) {
								String codigo_general_intermediacion = tituloOficial.getCodGeneral();
								if (codigo_general_intermediacion != null && codigo_general_solicitud.equals(codigo_general_intermediacion)) {
									validacion = true;
									break;
								}
							}
						}
					}
				}
			}
			return validacion;
		}
				
		/**
		 * ****** METODOS TITULOS NO UNIVERSITARIOS *******.
		 *
		 * @param solicitudTransmision el solicitud transmision
		 * @return el solicitud bean
		 */
		
		/**
		 * Busca en la tabla de la bbdd solicitud_comun la solicitud que proviene de transmision
		 * Cuando el expediente sea 123456 solo buscara por nif y nombre por lo que podra encontrar mas de una solicitud,
		 * en este caso se tratará como una excepcion de hibernate
		 * @param solicitudTransmision - la solicitud de la transmision
		 * @return solicitudBean - la solicitud comun buscada en bbdd
		 */
		private SolicitudBean buscaSolicitudComunTitulacionNoUniv(es.redsara.intermediacion.scsp.esquemas.educacionnouniv.ws.respuesta.TransmisionDatos solicitudTransmision) {
			
			SolicitudBean solicitudBean = null;
			
			// creo un objeto query para filtrar la consulta
			SolicitudComunQuery solicitudQuery = new SolicitudComunQuery();
						
			// obtengo la solicitud del ciudadano, su nif y su nombre
			String id_expediente  = solicitudTransmision.getDatosGenericos().getSolicitante().getIdExpediente();
			String nif = solicitudTransmision.getDatosGenericos().getTitular().getDocumentacion();
						
			// monto el filtro de la consulta
			solicitudQuery.setNif(nif);
			// compruebo que el id_expediente obtenido no sea el 123456 para no hacer la busqueda por ese
			if (!id_expediente.equals(properties.getProperty(STRING_SVTO_ID_EXPEDIENTE))) {
				solicitudQuery.setNumeroSolicitud(id_expediente);
			} 
			
			// busco la solicitud del ciudadano en la tabla solicitud_comun de la bb.dd
			try {
				solicitudBean = solicitudesManager.buscarSolicitudById(solicitudQuery);
			} catch (Exception e) {
				logger.error(STRING_ERROR_BUSCAR_SOLICITUD+ solicitudBean.getNumeroSolicitud(),e); 
			} 
			return solicitudBean;
		}
		
		/**
		 * Valida un titulo no universitario
		 * Valida que el cogido general del titulo del ciudadano sea el mismo que el codigo general de interemediacion para cotejar positivamente el titulo.
		 *
		 * @param solicitudBean - la solicitud comun del ciudadano
		 * @param titulosNoUnivIntermediacion el titulos no univ intermediacion
		 * @return el boolean
		 */
		private Boolean validarTituloNoUniversitario(SolicitudBean solicitudBean, es.redsara.intermediacion.scsp.esquemas.educacionnouniv.datosespecificos.DatosTitulacion[] titulosNoUnivIntermediacion) {
			Boolean validacion = false;
			String codigo_solicitud = solicitudBean.getTituloOficial().getCodigo();	// codigo en la solicitud
			
			if (codigo_solicitud != null) {
				TituloOficialBean titulo_solicitud = tituloOficialManager.obtenerTituloOficialByCodigo(codigo_solicitud);
				if (titulo_solicitud != null) {
					String codigo_general_solicitud = titulo_solicitud.getCodGeneral();	// codigo campo codigo_general tabla titulo_oficial
					if (codigo_general_solicitud != null) {
						for (es.redsara.intermediacion.scsp.esquemas.educacionnouniv.datosespecificos.DatosTitulacion datosTitulacion : titulosNoUnivIntermediacion) {
							String codigo_intermediacion = datosTitulacion.getDatosTitulo().getCodigoTitulacion(); // codigo de intermediacion
							// se localiza el codigo determinante (codigo campo codigo_general) para cotejar
							TituloOficialBean tituloOficial = tituloOficialManager.obtenerTituloOficialByCodigo(codigo_intermediacion);
							if (tituloOficial != null) {
								String codigo_general_intermediacion = tituloOficial.getCodGeneral();
								if (codigo_general_intermediacion != null && codigo_general_solicitud.equals(codigo_general_intermediacion)) {
									validacion = true;
									break;
								}
							}
						}
					}
				}
			}
			return validacion;
		}
	
		
		/**
		 * ****** METODOS TITULOS UNIVERSITARIOS ANTERIORES A 1990 *******.
		 *
		 * @param solicitudTransmision el solicitud transmision
		 * @return el solicitud bean
		 */
		
		/**
		 * Busca en la tabla de la bbdd solicitud_comun la solicitud que proviene de transmision
		 * Cuando el expediente sea 123456 solo buscara por nif y nombre por lo que podra encontrar mas de una solicitud,
		 * en este caso se tratara como una excepcion de hibernate
		 * @param solicitudTransmision - la solicitud de la transmision
		 * @return solicitudBean - la solicitud comun buscada en bbdd
		 */
		private SolicitudBean buscaSolicitudComunTitulacionUniv1990(es.redsara.intermediacion.scsp.esquemas.educacion1990Univ.ws.respuesta.TransmisionesTransmisionDatos solicitudTransmision) {
			
			SolicitudBean solicitudBean = null;
			
			// creo un objeto query para filtrar la consulta
			SolicitudComunQuery solicitudQuery = new SolicitudComunQuery();
						
			// obtengo la solicitud del ciudadano, su nif y su nombre
			String id_expediente  = solicitudTransmision.getDatosGenericos().getSolicitante().getIdExpediente();
			String nif = solicitudTransmision.getDatosGenericos().getTitular().getDocumentacion();
						
			// monto el filtro de la consulta
			solicitudQuery.setNif(nif);
			// compruebo que el id_expediente obtenido no sea el 123456 para no hacer la busqueda por ese
			if (!id_expediente.equals(properties.getProperty(STRING_SVTO_ID_EXPEDIENTE))) {
				solicitudQuery.setNumeroSolicitud(id_expediente);
			} 
			
			// busco la solicitud del ciudadano en la tabla solicitud_comun de la bb.dd
			try {
				solicitudBean = solicitudesManager.buscarSolicitudById(solicitudQuery);
			} catch (Exception e) {
				logger.error(STRING_ERROR_BUSCAR_SOLICITUD+ solicitudBean.getNumeroSolicitud(),e); 
			} 
			return solicitudBean;
		}
		
		/**
		 * Valida un titulo universitario anterior a 1990
		 * Valida que el cogido general del titulo del ciudadano sea el mismo que el codigo general de interemediacion para cotejar positivamente el titulo.
		 *
		 * @param solicitudBean - la solicitud comun del ciudadano
		 * @param titulosUnivIntermediacion1990 el titulos univ intermediacion 1990
		 * @return el boolean
		 */
		private Boolean validarTituloUniversitario1990(SolicitudBean solicitudBean, es.redsara.intermediacion.scsp.esquemas.educacion1990Univ.datosespecificos.DatosTitulacion[] titulosUnivIntermediacion1990) {
			Boolean validacion = false;
			String codigo_solicitud = solicitudBean.getTituloOficial().getCodigo();	// codigo en la solicitud
			
			if (codigo_solicitud != null) {
				TituloOficialBean titulo_solicitud = tituloOficialManager.obtenerTituloOficialByCodigo(codigo_solicitud);
				if (titulo_solicitud != null) {
					String codigo_general_solicitud = titulo_solicitud.getCodGeneral();	// codigo campo codigo_general tabla titulo_oficial
					if (codigo_general_solicitud != null) {
						for (es.redsara.intermediacion.scsp.esquemas.educacion1990Univ.datosespecificos.DatosTitulacion datosTitulacion : titulosUnivIntermediacion1990) {
							String codigo_intermediacion = datosTitulacion.getDatosTitulo().getCodigoTitulacion(); // codigo de intermediacion
							// se localiza el codigo determinante (codigo campo codigo_general) para cotejar
							TituloOficialBean tituloOficial = tituloOficialManager.obtenerTituloOficialByCodigo(codigo_intermediacion);
							if (tituloOficial!=null) {
								String codigo_general_intermediacion = tituloOficial.getCodGeneral();
								if (codigo_general_intermediacion != null && codigo_general_solicitud.equals(codigo_general_intermediacion)) {
									validacion = true;
									break;
								}
							}
						}
					}
				}
			}
			return validacion;
		}
		
		
		/**
		 * ****** METODOS TITULOS NO UNIVERSITARIOS ANTERIORES A 1990 *******.
		 *
		 * @param solicitudTransmision el solicitud transmision
		 * @return el solicitud bean
		 */
		
		/**
		 * Busca en la tabla de la bbdd solicitud_comun la solicitud que proviene de transmision
		 * Cuando el expediente sea 123456 solo buscara por nif y nombre por lo que podra encontrar mas de una solicitud,
		 * en este caso se tratará como una excepcion de hibernate
		 * @param solicitudTransmision - la solicitud de la transmision
		 * @return solicitudBean - la solicitud comun buscada en bbdd
		 */
		private SolicitudBean buscaSolicitudComunTitulacionNoUniv1990(es.redsara.intermediacion.scsp.esquemas.educacion1990noUniv.ws.respuesta.TransmisionDatos solicitudTransmision) {
			
			SolicitudBean solicitudBean = null;
			
			// creo un objeto query para filtrar la consulta
			SolicitudComunQuery solicitudQuery = new SolicitudComunQuery();
						
			// obtengo la solicitud del ciudadano, su nif y su nombre
			String id_expediente  = solicitudTransmision.getDatosGenericos().getSolicitante().getIdExpediente();
			String nif = solicitudTransmision.getDatosGenericos().getTitular().getDocumentacion();
						
			// monto el filtro de la consulta
			solicitudQuery.setNif(nif);
			// compruebo que el id_expediente obtenido no sea el 123456 para no hacer la busqueda por ese
			if (!id_expediente.equals(properties.getProperty(STRING_SVTO_ID_EXPEDIENTE))) {
				solicitudQuery.setNumeroSolicitud(id_expediente);
			} 
			
			// busco la solicitud del ciudadano en la tabla solicitud_comun de la bb.dd
			try {
				solicitudBean = solicitudesManager.buscarSolicitudById(solicitudQuery);
			} catch (Exception e) {
				logger.error(STRING_ERROR_BUSCAR_SOLICITUD+ solicitudBean.getNumeroSolicitud(),e); 
			} 
			return solicitudBean;
		}
		
		/**
		 * Valida un titulo universitario anterior a 1990
		 * Valida que el cogido general del titulo del ciudadano sea el mismo que el codigo general de interemediacion para cotejar positivamente el titulo.
		 *
		 * @param solicitudBean - la solicitud comun del ciudadano
		 * @param titulosNoUnivIntermediacion1990 el titulos no univ intermediacion 1990
		 * @return el boolean
		 */
		private Boolean validarTituloNoUniversitario1990(SolicitudBean solicitudBean, es.redsara.intermediacion.scsp.esquemas.educacion1990noUniv.datosespecificos.DatosTitulacion[] titulosNoUnivIntermediacion1990) {
			Boolean validacion = false;
			String codigo_solicitud = solicitudBean.getTituloOficial().getCodigo();	// codigo en la solicitud
						
			if (codigo_solicitud != null) {
				TituloOficialBean titulo_solicitud = tituloOficialManager.obtenerTituloOficialByCodigo(codigo_solicitud);
				if (titulo_solicitud != null) {
					String codigo_general_solicitud = titulo_solicitud.getCodGeneral();	// codigo campo codigo_general tabla titulo_oficial
					if (codigo_general_solicitud != null) {
						for (es.redsara.intermediacion.scsp.esquemas.educacion1990noUniv.datosespecificos.DatosTitulacion datosTitulacion : titulosNoUnivIntermediacion1990) {
							String codigo_intermediacion = datosTitulacion.getDatosTitulo().getCodigoTitulacion(); // codigo de intermediacion
							// se localiza el codigo determinante (codigo campo codigo_general) para cotejar
							TituloOficialBean tituloOficial = tituloOficialManager.obtenerTituloOficialByCodigo(codigo_intermediacion);
							if (tituloOficial != null) {
								String codigo_general_intermediacion = tituloOficial.getCodGeneral();
								if (codigo_general_intermediacion != null && codigo_general_solicitud.equals(codigo_general_intermediacion)) {
										validacion = true;
										break;
								}
							}
						}
					}
				}
			}
			return validacion;
		}
		
		
		
		/**
		 * ****** METODOS FAMILIA NUMEROSA *******.
		 *
		 * @param solicitudTransmision el solicitud transmision
		 * @return el solicitud bean
		 */	
		
		/**
		 * Busca en la tabla de la bbdd solicitud_comun la solicitud que proviene de transmision
		 * Cuando el expediente sea 123456 solo buscara por nif y nombre por lo que podra encontrar mas de una solicitud,
		 * en este caso se tratara como una excepcion de hibernate
		 * @param solicitudTransmision - la solicitud de la transmision
		 * @return solicitudBean - la solicitud comun buscada en bbdd
		 */
		private SolicitudBean buscaSolicitudComunFNumerosa(es.redsara.intermediacion.scsp.esquemas.fnumerosa.ws.respuesta.TransmisionesTransmisionDatos solicitudTransmision) {
			
			SolicitudBean solicitudBean = null;
			
			// creo un objeto query para filtrar la consulta
			SolicitudComunQuery solicitudQuery = new SolicitudComunQuery();
						
			// obtengo la solicitud del ciudadano, su nif y su nombre
			String id_expediente  = solicitudTransmision.getDatosGenericos().getSolicitante().getIdExpediente();
			String nif = solicitudTransmision.getDatosGenericos().getTitular().getDocumentacion();
						
			// monto el filtro de la consulta
			solicitudQuery.setNif(nif);
			// compruebo que el id_expediente obtenido no sea el 123456 para no hacer la busqueda por ese
			if (!id_expediente.equals(properties.getProperty(STRING_SVTO_ID_EXPEDIENTE))) {
				solicitudQuery.setNumeroSolicitud(id_expediente);
			} 
			
			// busco la solicitud del ciudadano en la tabla solicitud_comun de la bb.dd
			try {
				solicitudBean = solicitudesManager.buscarSolicitudById(solicitudQuery);
			} catch (Exception e) {
				logger.error(STRING_ERROR_BUSCAR_SOLICITUD+ solicitudBean.getNumeroSolicitud(),e); 
			} 
			return solicitudBean;
		}
		
		
		/**
		 * Comprueba si se ha verificado previamente alguna vez la familia numerosa de esa solicitud comun (constate N o R).
		 *
		 * @param solicitudBean - la solicitud comun del ciudadano
		 * @return respuesta - Booleano con la respuesta
		 */
		private Boolean fNumerosaVerificadoPrevio(SolicitudBean solicitudBean) {
			Boolean respuesta = false;
						
			// obtengo el campo fnumerosa_verificado de la tabla solicitud_comun
			Character fnumerosa_verificado = solicitudBean.getFnumerosaVerificado();
			
			// obtengo una respuesta positiva o negativa de verificacion previa
			if (fnumerosa_verificado != null) {
				if (fnumerosa_verificado.charValue() == Constantes.FNUMEROSA_VERIFICADO) {
					respuesta = true;
				} 
			}
			return respuesta;
		}
		
		/**
		 * Coteja una solicitud, es decir que los datos de la solicitud de transmision de intermediacion
		 * coindiden con los datos que introdujo el ciudadano en su solicitud.
		 *
		 * @param solicitudBean - la solicitud comun
		 * @param vigenciaIntermediacion el vigencia intermediacion
		 * @param categoriaIntermediacion el categoria intermediacion
		 * @return cotejado - Booleano con la respuesta de cotejacion
		 */
		private Boolean validarFamiliaNumerosa(SolicitudBean solicitudBean,String vigenciaIntermediacion,String categoriaIntermediacion) {
			
			Boolean cotejado = false;
			String categoria_solicitud = "";
			int id_categoria_solicitud = 0;
			
			SolicitudComunQuery solicitudQuery = new SolicitudComunQuery();
			PagoSolicitudQuery pagoSolicitudQuery = new PagoSolicitudQuery();
			
			// monto el filtro de la consulta
			solicitudQuery.setNumeroSolicitud(solicitudBean.getNumeroSolicitud());
			solicitudQuery.setNif(solicitudBean.getNif());
			solicitudQuery.setNombre(solicitudBean.getNombre());
			pagoSolicitudQuery.setSolicitudComun(solicitudQuery);
				
			// obtengo la categoria de familia numerosa de la solicitud a traves del motivo de reduccion de tasas
			PagoSolicitudBean pagoSolicitudBean = pagoSolicitudManager.buscarPagoSolicitudByIdSolicitud(pagoSolicitudQuery);
			id_categoria_solicitud = pagoSolicitudBean.getMotivoReduccionTasa().getId();
			if (id_categoria_solicitud == 3) {
				categoria_solicitud = Constantes.CATEGORIA_FAMILIANUMEROSA_E;
			} else if (id_categoria_solicitud == 5)  {
				categoria_solicitud = Constantes.CATEGORIA_FAMILIANUMEROSA_G;
			}
			
			// cotejamiento
			if (categoriaIntermediacion.equals(categoria_solicitud) && vigenciaIntermediacion.equals("S")) {
				cotejado = true;
			} else {
				cotejado = false;
			}
			return cotejado;
		}
		
		
		/**
		 * ****** METODOS DISCAPACIDAD *******.
		 *
		 * @param solicitudTransmision el solicitud transmision
		 * @return el solicitud bean
		 */	
		
		
		/**
		 * Busca en la tabla de la bbdd solicitud_comun la solicitud que proviene de transmision
		 * Cuando el expediente sea 123456 solo buscara por nif y nombre por lo que podra encontrar mas de una solicitud,
		 * en este caso se tratara como una excepcion de hibernate
		 * @param solicitudTransmision - la solicitud de la transmision
		 * @return solicitudBean - la solicitud comun buscada en bbdd
		 */
		private SolicitudBean buscaSolicitudComunDiscapacidad(es.redsara.intermediacion.scsp.esquemas.discapacidad.ws.respuesta.TransmisionesTransmisionDatos solicitudTransmision) {
			
			SolicitudBean solicitudBean = null;
			
			// creo un objeto query para filtrar la consulta
			SolicitudComunQuery solicitudQuery = new SolicitudComunQuery();
						
			// obtengo la solicitud del ciudadano, su nif y su nombre
			String id_expediente  = solicitudTransmision.getDatosGenericos().getSolicitante().getIdExpediente();
			String nif = solicitudTransmision.getDatosGenericos().getTitular().getDocumentacion();
						
			// monto el filtro de la consulta
			solicitudQuery.setNif(nif);
			// compruebo que el id_expediente obtenido no sea el 123456 para no hacer la busqueda por ese
			if (!id_expediente.equals(properties.getProperty(STRING_SVTO_ID_EXPEDIENTE))) {
				solicitudQuery.setNumeroSolicitud(id_expediente);
			} 
			
			// busco la solicitud del ciudadano en la tabla solicitud_comun de la bb.dd
			try {
				solicitudBean = solicitudesManager.buscarSolicitudById(solicitudQuery);
			} catch (Exception e) {
				logger.error(STRING_ERROR_BUSCAR_SOLICITUD+ solicitudBean.getNumeroSolicitud(),e); 
			} 
			return solicitudBean;
		}
		
		private SolicitudBean buscaSolicitudComunVictimas(es.redsara.intermediacion.scsp.esquemas.victimasTerrorismo.ws.respuesta.TransmisionesTransmisionDatos solicitudTransmision) {
			
			SolicitudBean solicitudBean = null;
			
			// creo un objeto query para filtrar la consulta
			SolicitudComunQuery solicitudQuery = new SolicitudComunQuery();
						
			// obtengo la solicitud del ciudadano, su nif y su nombre
			String id_expediente  = solicitudTransmision.getDatosGenericos().getSolicitante().getIdExpediente();
			String nif = solicitudTransmision.getDatosGenericos().getTitular().getDocumentacion();
						
			// monto el filtro de la consulta
			solicitudQuery.setNif(nif);
			// compruebo que el id_expediente obtenido no sea el 123456 para no hacer la busqueda por ese
			if (!id_expediente.equals(properties.getProperty("VICTIMAS_ID_EXPEDIENTE"))) {
				solicitudQuery.setNumeroSolicitud(id_expediente);
			} 
			
			// busco la solicitud del ciudadano en la tabla solicitud_comun de la bb.dd
			try {
				solicitudBean = solicitudesManager.buscarSolicitudById(solicitudQuery);
			} catch (Exception e) {
				logger.error(STRING_ERROR_BUSCAR_SOLICITUD+ solicitudBean.getNumeroSolicitud(),e); 
			} 
			return solicitudBean;
		}

		
		/**
		 * Comprueba si se ha verificado previamente alguna vez la discapacidad de esa solicitud comun (constante N o R).
		 *
		 * @param solicitudBean - la solicitud comun del ciudadano
		 * @return respuesta - Booleano con la respuesta
		 */
		private Boolean discapacidadVerificadoPrevio(SolicitudBean solicitudBean) {
			Boolean respuesta = false;
					
			// obtengo el campo fnumerosa_verificado
			Character discapacidad_verificado = solicitudBean.getDiscapacidadVerificado();
						
			if (discapacidad_verificado != null) {
				if (discapacidad_verificado.charValue() == Constantes.DISCAPACIDAD_VERIFICADO) {
					respuesta = true;
				}
			}
			return respuesta;
		}
		
		/**
		 * Valida que una solicitud tendra exencion de tasas por discapacidad.
		 *
		 * @param gradoDiscapacidadIntermediacion - el grado de discapacidad que posee intermediacion
		 * @return exencion - Booleano con la respuesta si la el cidadano tendra exencion de tasas por discapacidad o no
		 */
		private Boolean validarDiscapacidad(Integer gradoDiscapacidadIntermediacion) {
			
			ParametrosConfiguracionBean parametrosConfiguracionBean = null;
			int valor_campo_configuracion = 0;
			Boolean exencion = false;
			
			// se comprueba que el grado de discapacidad del ciudadano es mayor que la discapacidad base (ahora 33%)
			// el dato de la discapacidad-base es un registro de la tabla parametros-configuracion
			
			// busco en la tabla parametros_configuracion el registro que tiene el id de porcentaje exento
			// el id esta definido en la clase Constantes.java
			parametrosConfiguracionBean = parametroConfiguracionManager.obtenerParametrosConfiguracion(Constantes.PARAMETRO_CONFIGURACION_ID_PORCENTAJE_EXENTO);
			// paso a entero el valor que tiene el campo valor_campo de esa tabla
			valor_campo_configuracion = Integer.parseInt(parametrosConfiguracionBean.getValorCampo());
			
			// si el dato del grado de intermediacion es superior al grado-base establecido en la tabla parametros_configuracion
			if (gradoDiscapacidadIntermediacion >= valor_campo_configuracion) {
				exencion = true;
			} else {
				exencion = false;
			}
			return exencion;
		}
		
		
		/**
		 * ****** METODOS DESEMPLEO *******.
		 *
		 * @param solicitudTransmision el solicitud transmision
		 * @return el solicitud bean
		 */	
		
		/**
		 * Busca en la tabla de la bbdd solicitud_comun la solicitud que proviene de transmision
		 * Cuando el expediente sea 123456 solo buscara por nif y nombre por lo que podra encontrar mas de una solicitud,
		 * en este caso se tratara como una excepcion de hibernate
		 * @param solicitudTransmision - la solicitud de la transmision
		 * @return solicitudBean - la solicitud comun buscada en bbdd
		 */
		private SolicitudBean buscaSolicitudComunDiasDesempleo(es.redsara.intermediacion.scsp.esquemas.sepe.ws.respuesta.TransmisionesTransmisionDatos solicitudTransmision) {
			
			SolicitudBean solicitudBean = null;
			
			// creo un objeto query para filtrar la consulta
			SolicitudComunQuery solicitudQuery = new SolicitudComunQuery();
						
			// obtengo la solicitud del ciudadano, su nif y su nombre
			String id_expediente  = solicitudTransmision.getDatosGenericos().getSolicitante().getIdExpediente();
			String nif = solicitudTransmision.getDatosGenericos().getTitular().getDocumentacion();
						
			// monto el filtro de la consulta
			solicitudQuery.setNif(nif);
			// compruebo que el id_expediente obtenido no sea el 123456 para no hacer la busqueda por ese
			if (!id_expediente.equals(properties.getProperty(STRING_SVTO_ID_EXPEDIENTE))) {
				solicitudQuery.setNumeroSolicitud(id_expediente);
			} 
			
			// busco la solicitud del ciudadano en la tabla solicitud_comun de la bb.dd
			try {
				solicitudBean = solicitudesManager.buscarSolicitudById(solicitudQuery);
			} catch (Exception e) {
				logger.error(STRING_ERROR_BUSCAR_SOLICITUD+ solicitudBean.getNumeroSolicitud(),e); 
			} 
			return solicitudBean;
		}
		
		/**
		 * Busca en la tabla de la bbdd solicitud_comun la solicitud que proviene de transmision
		 * Cuando el expediente sea 123456 solo buscara por nif y nombre por lo que podra encontrar mas de una solicitud,
		 * en este caso se tratara como una excepcion de hibernate.
		 *
		 * @param solicitudTransmision - la solicitud de la transmision
		 * @return solicitudBean - la solicitud comun buscada en bbdd
		 */
//		private SolicitudBean buscaSolicitudComunImporteDesempleo(es.redsara.intermediacion.scsp.esquemas.importe.ws.respuesta.TransmisionesTransmisionDatos solicitudTransmision) {
//			
//			SolicitudBean solicitudBean = null;
//			
//			// creo un objeto query para filtrar la consulta
//			SolicitudComunQuery solicitudQuery = new SolicitudComunQuery();
//						
//			// obtengo la solicitud del ciudadano, su nif y su nombre
//			String id_expediente  = solicitudTransmision.getDatosGenericos().getSolicitante().getIdExpediente();
//			String nif = solicitudTransmision.getDatosGenericos().getTitular().getDocumentacion();
//						
//			// monto el filtro de la consulta
//			solicitudQuery.setNif(nif);
//			// compruebo que el id_expediente obtenido no sea el 123456 para no hacer la busqueda por ese
//			if (!id_expediente.equals(properties.getProperty(STRING_SVTO_ID_EXPEDIENTE))) {
//				solicitudQuery.setNumeroSolicitud(id_expediente);
//			} 
//			
//			// busco la solicitud del ciudadano en la tabla solicitud_comun de la bb.dd
//			try {
//				solicitudBean = solicitudesManager.buscarSolicitudById(solicitudQuery);
//			} catch (Exception e) {
//				logger.error(STRING_ERROR_BUSCAR_SOLICITUD+ solicitudBean.getNumeroSolicitud(),e); 
//			} 
//			return solicitudBean;
//		}
		
		
		/**
		 * Valida que el n� de dias como demandante de empleo superan los 30 dias.
		 *
		 * @param diasDesempleoIntermediacion el dias desempleo intermediacion
		 * @return dias_validos - Booleano con la respuesta de cotejacion
		 */
		private Boolean validarDiasDesempleo(Integer diasDesempleoIntermediacion) {
			
			Boolean dias_validos = false;
						
			// validacion
			if ( diasDesempleoIntermediacion >= 30) {
				dias_validos = true;
			}
			return dias_validos;
		}
		
		/**
		 * Validar importe desempleo.
		 *
		 * @param importeCobradoDemandante el importe cobrado demandante
		 * @return el boolean
		 */
//		private Boolean validarImporteDesempleo(String importeCobradoDemandante) {
//			
//			Boolean importe_valido = false;
//			Float importe_cobrado_demandante = 0f;
//			Float valor_campo_configuracion = 0f;
//			ParametrosConfiguracionBean parametrosConfiguracionBean = null;
//			
//			// se convierte de String a Float el dato de importe de intermediacion
//			importe_cobrado_demandante= Float.parseFloat(importeCobradoDemandante.replaceAll("\\.","").replaceAll("\\,","."));
//			
//			// se comprueba que el salario que ha estado percibiendo el ciudadano no haya superarado el salario minimo interprofesional
//			// el dato de salario minimo interprofesional es un registro de la tabla parametros-configuracion
//			
//			// busco en la tabla parametros_configuracion el registro que tiene el id de salario minimo interprofesional
//			// el id esta definido en la clase Constantes.java
//			parametrosConfiguracionBean = parametroConfiguracionManager.obtenerParametrosConfiguracion(Constantes.PARAMETRO_CONFIGURACION_ID_SALARIO_MINIMO);
//			
//			if (parametrosConfiguracionBean != null) {
//				// paso a Float el valor String que tiene el campo valor_campo de esa tabla
//				valor_campo_configuracion = Float.parseFloat(parametrosConfiguracionBean.getValorCampo());
//				
//				if (importe_cobrado_demandante <= valor_campo_configuracion) {
//					importe_valido = true;
//				} 
//			}
//			return importe_valido;
//		}
			
			
		/**
		 * Obtiene el solicitudes manager.
		 *
		 * @return el solicitudes manager
		 */
		public SolicitudesManager getSolicitudesManager() {
			return solicitudesManager;
		}
	
		/**
		 * Establece el solicitudes manager.
		 *
		 * @param solicitudesManager el nuevo solicitudes manager
		 */
		public void setSolicitudesManager(SolicitudesManager solicitudesManager) {
			this.solicitudesManager = solicitudesManager;
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
		public void setParametroConfiguracionManager(ParametroConfiguracionManager parametroConfiguracionManager) {
			this.parametroConfiguracionManager = parametroConfiguracionManager;
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
	
//		public DatosTitulosManager getDatosTitulosManager() {
//			return datosTitulosManager;
//		}
//	
//		public void setDatosTitulosManager(DatosTitulosManager datosTitulosManager) {
//			this.datosTitulosManager = datosTitulosManager;
//		}
	
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
		 * Obtiene el alerta manager.
		 *
		 * @return el alerta manager
		 */
		public AlertaManager getAlertaManager() {
			return alertaManager;
		}
	
		/**
		 * Establece el alerta manager.
		 *
		 * @param alertaManager el nuevo alerta manager
		 */
		public void setAlertaManager(AlertaManager alertaManager) {
			this.alertaManager = alertaManager;
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
		 * Obtiene el log intermdiacion manger.
		 *
		 * @return el log intermdiacion manger
		 */
		public LogIntermediacionManager getLogIntermdiacionManger() {
			return logIntermediacionManager;
		}
	
		/**
		 * Obtiene el log intermediacion manager.
		 *
		 * @return el log intermediacion manager
		 */
		public LogIntermediacionManager getLogIntermediacionManager() {
			return logIntermediacionManager;
		}
		
		/**
		 * Establece el log intermediacion manager.
		 *
		 * @param logIntermediacionManager el nuevo log intermediacion manager
		 */
		public void setLogIntermediacionManager(
				LogIntermediacionManager logIntermediacionManager) {
			this.logIntermediacionManager = logIntermediacionManager;
		}
	
		/**
		 * Obtiene el log servicio manager.
		 *
		 * @return el log servicio manager
		 */
		public LogServicioManager getLogServicioManager() {
			return logServicioManager;
		}
	
		/**
		 * Establece el log servicio manager.
		 *
		 * @param logServicioManager el nuevo log servicio manager
		 */
		public void setLogServicioManager(LogServicioManager logServicioManager) {
			this.logServicioManager = logServicioManager;
		}
	
		public ConvocatoriasManager getConvocatoriaManager() {
			return convocatoriaManager;
		}

		public void setConvocatoriaManager(ConvocatoriasManager convocatoriaManager) {
			this.convocatoriaManager = convocatoriaManager;
		}
	
}
