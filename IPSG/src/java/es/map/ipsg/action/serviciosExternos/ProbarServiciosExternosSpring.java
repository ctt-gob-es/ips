package es.map.ipsg.action.serviciosExternos;


import java.io.Reader;
import java.io.StringReader;
import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.rpc.ServiceException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import ePago.gob.es.schemas.DefaultDatosPagoIn;
import ePago.gob.es.schemas.DefaultDatosPagoOut;
import ePago.gob.es.schemas.PPServiceInterfaceServiceLocator;
import ePago.gob.es.schemas.PPServiceInterfaceSoap11Stub;
import ePago.gob.es.schemas.TiposCargo;
import ePago.gob.es.schemas.TiposDocumento;
import es.egoeveris.firma.impl.afirma.model.parametrosValCertificado.ParamCert;
import es.guadaltel.framework.signer.impl.util.Base64Coder;
import es.map.ips.common.exception.ApplicationException;
import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.util.BeanFormatter;
import es.map.ips.model.TipoDocumento;
import es.map.ips.model.TipoDocumentoQuery;
import es.map.ipsg.bean.DocumentoBean;
import es.map.ipsg.bean.EntidadFinancieraBean;
import es.map.ipsg.bean.LogServicioBean;
import es.map.ipsg.bean.RegistroRecBean;
import es.map.ipsg.bean.TipoDocumentoBean;
import es.map.ipsg.clienteWS.buscarRegistroRec3.BuscarRegistrosElectronicos;
import es.map.ipsg.clienteWS.buscarRegistroRec3.BuscarRegistrosElectronicosProxy;
import es.map.ipsg.clienteWS.buscarRegistroRec3.CriteriosBusquedaType;
import es.map.ipsg.clienteWS.registroRec3.type.AutenticacionType;
import es.map.ipsg.clienteWS.registroRec3.type.JustificanteType;
import es.map.ipsg.clienteWS.registroRec3.type.RespuestaType;
import es.map.ipsg.form.ProbarServiciosExternosForm;
import es.map.ipsg.manager.DocumentoManager;
import es.map.ipsg.manager.EntidadFinancieraManager;
import es.map.ipsg.manager.LogServicioManager;
import es.map.ipsg.manager.SolicitudesManager;
import es.map.ipsg.manager.TipoDocumentoManager;
import es.map.ipsg.manager.UsuarioManager;
import es.map.ipsg.service.ValidarCertificado;
import es.map.ipsg.util.Constantes;
import es.map.ipsg.util.SignDataUtil;

/**
 * El Class ProbarServiciosExternosSpring.
 */
public class ProbarServiciosExternosSpring extends AbstractSpring {

	/** el usuario manager. */
	private UsuarioManager usuarioManager;
	
	/** el log servicio manager. */
	private LogServicioManager logServicioManager;
	
	/** el documento manager. */
	private DocumentoManager documentoManager;
	
	/** el tipo documento manager. */
	private TipoDocumentoManager tipoDocumentoManager;
	
	/** el solicitudes manager. */
	private SolicitudesManager solicitudesManager;
	
	/** el entidad financiera manager. */
	private EntidadFinancieraManager entidadFinancieraManager;
	
	/** el timeout. */
	public static int TIMEOUT = 90000;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(ProbarServiciosExternosSpring.class);
	
	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** el properties. */
	private static Properties properties;
	
	/** La constante STRING_SERVICIO. */
	private static final String STRING_SERVICIO = "servicio";
	

	/**
	 * Crea una nueva probar servicios externos spring.
	 */
	public ProbarServiciosExternosSpring() {
		try {
			setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
			setLogServicioManager((LogServicioManager) getBean("logServicioManager"));
			setDocumentoManager((DocumentoManager) getBean("documentoManager"));
			setTipoDocumentoManager((TipoDocumentoManager) getBean("tipoDocumentoManager"));
			setSolicitudesManager((SolicitudesManager) getBean("solicitudesManager"));
			setEntidadFinancieraManager((EntidadFinancieraManager) getBean("entidadFinancieraManager"));
			properties = (Properties) getBean("propertiesBean");
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error ProbarServiciosExternosSpring:",e);
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		String menu_alerta = RESOURCE_BUNDLE.getString("field.menu.adminServExt");
		this.getRequest().getSession().setAttribute("pagActiva", menu_alerta);
		String subMenu_ServExt = RESOURCE_BUNDLE.getString("field.menuLateral.serviciosExternos.pruebaServicio");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_ServExt);
		
		getLogger().debug("ProbarServiciosExternosSpring -start");
		logger.info("Entra en el Action ProbarServiciosExternosAction");
		String servicioExterno = null;
		
		try{
			String respuestaWS = "N";		
			//Cogemos el form del jsp
			ProbarServiciosExternosForm formulario;
			formulario = (ProbarServiciosExternosForm) form;
			
			if(formulario.getServicio()==null || formulario.getServicio().equals("")){
			 	formulario.setServicio("0");
			}
			
			if (formulario.getAccion()!=null && formulario.getAccion().equals("Aceptar")){
				respuestaWS="S";
				
				// llamamos al webservice
				int accion=Integer.valueOf(formulario.getServicio());
				long inicio = System.currentTimeMillis();
				
				LogServicioBean logServicioBean = new LogServicioBean();
				
				switch (accion) {
				case Constantes.PRUEBA_SERVICIO_EXTERNO_PASARELA_PAGO:
					servicioExterno="Pasarela de Pagos";
					logServicioBean.setIdTipoServicio(Constantes.TIPO_SERVICIO_PASARELA);
					servicioExternoPasarela(formulario, logServicioBean);
					break;
				case Constantes.PRUEBA_SERVICIO_EXTERNO_REC:
					servicioExterno="REC";
					logServicioBean.setIdTipoServicio(Constantes.TIPO_SERVICIO_REC);
					servicioExternoREC(formulario, logServicioBean);
					break;
				case Constantes.PRUEBA_SERVICIO_EXTERNO_FILESYSTEM:
					servicioExterno="Sistema de Ficheros";
					logServicioBean.setIdTipoServicio(Constantes.TIPO_SERVICIO_ALFRESCO);
					servicioExternoAlfreco(formulario, logServicioBean);
					break;
				case Constantes.PRUEBA_SERVICIO_EXTERNO_EJB:
					servicioExterno="EJB";
					logServicioBean.setIdTipoServicio(Constantes.TIPO_SERVICIO_EJB);
					servicioExternoEJB(formulario, logServicioBean);
					break;
				case Constantes.PRUEBA_SERVICIO_EXTERNO_FIRMA:
					servicioExterno="@firma";
					logServicioBean.setIdTipoServicio(Constantes.TIPO_SERVICIO_AFIRMA);
					servicioExternoCertificado(formulario);
					break;	
				default:
					break;
				}
				long fin = System.currentTimeMillis();
				long tiempoRespuesta = fin - inicio;
				
				logServicioBean.setTiempoRespuesta(tiempoRespuesta);
				logServicioBean.setFecha(new Date());
				logServicioBean.setPrueba("S");
				
				logServicioManager.generarRegistroLog(logServicioBean);
				
				setRequestAttribute("logServicioBean",logServicioBean);
			}else{
				int servicio=Integer.valueOf(formulario.getServicio());
				if(servicio == Constantes.PRUEBA_SERVICIO_EXTERNO_PASARELA_PAGO){
					formulario.setNumeroSolicitud(solicitudesManager.obtenerNumeroSolicitud());
				}
				formulario.setResultado(null);
			}
			TipoDocumentoQuery tipoDocumentoQuery= new TipoDocumentoQuery();
			tipoDocumentoQuery.setIdIsNotNull(true);
			ArrayList<TipoDocumentoBean> arrTipoDocBean;
			arrTipoDocBean=tipoDocumentoManager.buscarTipoDocumentoCombo(tipoDocumentoQuery);
			
			ArrayList<EntidadFinancieraBean> entidadBeanAdeudo = entidadFinancieraManager.buscarEntidadAdeudoCombo();
			ArrayList<EntidadFinancieraBean> entidadBeanTarjeta = entidadFinancieraManager.buscarEntidadTarjetaCombo();
			
			setRequestAttribute("arrTipoDocBean", arrTipoDocBean);
			setRequestAttribute(STRING_SERVICIO, formulario.getServicio().toString());
			setRequestAttribute("respuestaWS", respuestaWS);
			setRequestAttribute("entidadesAdeudo", entidadBeanAdeudo);
			setRequestAttribute("entidadesTarjetas", entidadBeanTarjeta);
			
			getLogger().debug("ProbarServiciosExternosSpring -end");
			formulario.setAccion(null);
		
			return "success";
		
		}catch (Exception e){
			logger.warn(e);
			this.getRequest().setAttribute("descripcionError", "Error al probar el Servicio Externo "+servicioExterno+" "+e.getLocalizedMessage());
			logger.error("Error ProbarServiciosExternosSpring - Error al probar el Servicio Externo:"+ servicioExterno,e);
			return "nosuccess";
		}
	}
	
	/**
	 * Servicio externo pasarela.
	 *
	 * @param formulario el formulario
	 * @param logServicioBean el log servicio bean
	 */
	private void servicioExternoPasarela(ProbarServiciosExternosForm formulario,LogServicioBean logServicioBean){
		try{
			String sTipoPago = formulario.getTipoPago();
			Integer tipoPago = Integer.valueOf(sTipoPago);
			
			String nif = formulario.getNifNie();
			String nombre = formulario.getNombre();
			String primerApellido = formulario.getPrimerApellido();
			String segundoApellido = formulario.getSegundoApellido();
			String sImporte = formulario.getImporte();
			String codigoBanco = formulario.getEntidadFinanciera();
			String numJustificante = formulario.getNumeroSolicitud();
			String codigoTasa = "001";
			String firma = formulario.getSignature();
			logger.info(firma);
			String origenFirma = formulario.getOrigenFirma().replace("\r\n", "\n");
						
			double dImporte = Double.valueOf(sImporte);

			// setScale is immutable
			int decimalPlaces = 2;
			BigDecimal bd = BigDecimal.valueOf(dImporte);
			bd = bd.setScale(decimalPlaces, BigDecimal.ROUND_HALF_UP);
			double importeActual = bd.doubleValue();
			
			//Objeto de pago
			PPServiceInterfaceServiceLocator locator = new PPServiceInterfaceServiceLocator();
			locator.setPPServiceInterfaceSoap11EndpointAddress(properties.getProperty("direccion.pasarela.ws"));
			DefaultDatosPagoIn dpi = new DefaultDatosPagoIn();
			
			dpi.setDocumentoObligado(nif);
			dpi.setApellido1(primerApellido);
			dpi.setApellido2(segundoApellido);
			dpi.setNombre(nombre);
			dpi.setCodigoBanco(codigoBanco);
			dpi.setTipoDocumentoObligado(TiposDocumento.fromValue(1));
			dpi.setJustificante(numJustificante);
			dpi.setCodigoTasa(codigoTasa);
			dpi.setImporte(importeActual);
			dpi.setFirma1(firma);
			dpi.setOrigenFirma(origenFirma);
			
			if(tipoPago==1){//Tipo de Pago Adeudo
				String numeroCuenta = formulario.getIban() + formulario.getBancoAdeudo() + formulario.getOficina() + formulario.getDc() + formulario.getCuenta() + ("          ");
				dpi.setTipoCargo(TiposCargo.fromValue(0));
				dpi.setCCC(numeroCuenta);
				
			}else{//Tipo de Pago Tarjeta
				String numeroTarjeta = formulario.getNumTarjeta2() + formulario.getNumTarjeta3() + formulario.getNumTarjeta4() + formulario.getNumTarjeta5();
				Calendar date = Calendar.getInstance();
				 
				int CaducidadMes = Integer.parseInt(formulario.getCaducidadTarjeta1());
				if(CaducidadMes > 0) CaducidadMes--; //Utilizando CALENDAR Enero corresponde a 0
				date.set(Calendar.MONTH, CaducidadMes);
				date.set(Calendar.YEAR, /*2000 + */Integer.parseInt(formulario.getCaducidadTarjeta2()));
				
				dpi.setTipoCargo(TiposCargo.fromValue(1));
				dpi.setNumeroTarjeta(numeroTarjeta);
				dpi.setFechaCaducidadTarjeta(date);
				
			}
			 
			int idOrganismo = comprobarPropertyIdOrg();
			
			PPServiceInterfaceSoap11Stub binding = (PPServiceInterfaceSoap11Stub)locator.getPPServiceInterfaceSoap11();
			binding.setTimeout(TIMEOUT);
			logger.info("Lanza llamada al web service");
			logger.info("Datos de Pago enviados a la Pasarela: ");
			logger.info(new BeanFormatter().format(dpi));
			
			DefaultDatosPagoOut defaultDatosPagoOut =  binding.hacerPago(dpi, idOrganismo);
				
			if(defaultDatosPagoOut == null){
				logger.error("Error Físico en Conexion con Pasarela");
				logServicioBean.setResultado(Constantes.RESULTADO_ER);
				logServicioBean.setDesTipoError(RESOURCE_BUNDLE.getString("field.ServiciosExternos.errorDefaultPagoOutNull").substring(0,100));
				logServicioBean.setIdTipoError(Constantes.LOG_ERROR_FISICO.charAt(0));
				
			}else if(defaultDatosPagoOut.isFueCorrecto() == false ){
				logger.error("Error Logico en Conexion con Pasarela: "+defaultDatosPagoOut.getErrorCode());
				String errorCode = defaultDatosPagoOut.getErrorCode();
				String errorDescripcion = defaultDatosPagoOut.getErrorCodeTexto();
				errorDescripcion = (errorDescripcion.length()>100) ? errorDescripcion.substring(0,99) : errorDescripcion;
				
				logServicioBean.setResultado(Constantes.RESULTADO_ER);
				logServicioBean.setCodigoError(errorCode);
				logServicioBean.setDesTipoError(errorDescripcion);
				logServicioBean.setIdTipoError(Constantes.LOG_ERROR_LOGICO.charAt(0));
			}else{
				logger.info("La conexion con la pasarela ha sido exitosa");	
				logServicioBean.setResultado(Constantes.RESULTADO_OK);
				this.setRequestAttribute("defaultDatosPagoOut", defaultDatosPagoOut);
			}				
			
		} catch (ServiceException se) {
			logger.error("Error ProbarServiciosExternosSpring - servicioExternoPasarela",se);
			
			String message = se.getMessage();
			message = (message.length()>100) ? message.substring(0,99) : message;
			
			logServicioBean.setResultado(Constantes.RESULTADO_ER);
			logServicioBean.setDesTipoError(message);
			logServicioBean.setIdTipoError(Constantes.LOG_ERROR_FISICO.charAt(0));
			
		} catch (RemoteException re) {
			logger.error("Error ProbarServiciosExternosSpring - servicioExternoPasarela",re);
			
			String message = re.getMessage();
			message = (message.length()>100) ? message.substring(0,99) : message;
			
			logServicioBean.setResultado(Constantes.RESULTADO_ER);
			logServicioBean.setDesTipoError(message);
			logServicioBean.setIdTipoError(Constantes.LOG_ERROR_FISICO.charAt(0));
		
		}catch(Exception e){
			logger.error(e);
			e.printStackTrace();
			
			String message = e.getMessage();
			message = (message.length()>100) ? message.substring(0,99) : message;
			
			logServicioBean.setResultado(Constantes.RESULTADO_ER);
			logServicioBean.setDesTipoError(message);
			logServicioBean.setIdTipoError(Constantes.LOG_ERROR_FISICO.charAt(0));
		}
	}
	
	/**
	 * Comprobar property id org.
	 *
	 * @return el int
	 */
	private int comprobarPropertyIdOrg() {
		int idOrganismo = 0;
		try {
			 idOrganismo = Integer.parseInt(properties.getProperty("conf.idOrganismo"));
		 } catch (NumberFormatException e) {
			 logger.error("La propiedad idOrganismo no se encuentra en el fichero " + RESOURCE_BUNDLE + " o no tiene un formato válido.",e);
			 logger.error("Se va a indicar como idOrganismo el valor 0 por defecto.", e);
		 }
		return idOrganismo;
	}
	
	/**
	 * Servicio externo REC.
	 *
	 * @param formulario el formulario
	 * @param logServicioBean el log servicio bean
	 */
	private void servicioExternoREC(ProbarServiciosExternosForm formulario,LogServicioBean logServicioBean){
		try{
			//Llamada al WebService
			String direccionWS = properties.getProperty("conf.url_buscarRegistrosElectronicos");
			BuscarRegistrosElectronicos buscarRegElecWS = new BuscarRegistrosElectronicosProxy(direccionWS);
			String numeroRec = formulario.getNombreDocumentoREC();
					
			//Criterios de busqueda necesarios para el WS
			CriteriosBusquedaType criterios = new CriteriosBusquedaType();
			criterios.setCdOficinaOrigen(properties.getProperty("cdOrOrigen"));
			criterios.setCdTipoRegistro(properties.getProperty("cdTipoAsiento"));
			criterios.setNmRegistro(numeroRec);
			
			logger.info("Documento Buscado:" + formulario.getNombreDocumentoREC());
			
			String idAplicacion = properties.getProperty("idAplicacion");
			String password = properties.getProperty("password");
			
			AutenticacionType autentication = new AutenticacionType();
			autentication.setIdAplicacion(idAplicacion);
			autentication.setPassword(password);
			
			JustificanteType[] resultado = buscarRegElecWS.buscarRegistro(autentication, criterios);
			RespuestaType respuestaType=resultado[0].getRespuesta();
	
			List <RegistroRecBean> listRegistroRec = new ArrayList<RegistroRecBean>();
			boolean hayError = false;
			String key = "";
			
			if (resultado != null && !"".equals(Arrays.toString(resultado)) && resultado.length != 0) {
				if (respuestaType.getCdRespuesta() != null && !"".equals(respuestaType.getCdRespuesta()) && !"00".equals(respuestaType.getCdRespuesta())) {
					
					logger.error("justificanteType.isExisteError() is true");
					if(null!=respuestaType.getDsRespuesta()){	
						logger.error("Mensaje de error: "+respuestaType.getDsRespuesta());
					}
					
					//Se ha producido un error en el WS
					if (respuestaType.getCdRespuesta().equals("01")) {
						key = "field.consultaRec.errores.sinResultados";				
						setRequestAttribute(STRING_SERVICIO, formulario.getServicio().toString());
						hayError = true;
					}else {
						key = "field.consultaRec.errores.falloWS";						
						setRequestAttribute(STRING_SERVICIO, formulario.getServicio().toString());
						hayError = true;
					}					
				}else {
					//Han llegado datos			
					int tamanio = resultado.length;
					
					
					for (int i=0; i<tamanio; i++){
						RegistroRecBean registroRecBean = new RegistroRecBean();
						JustificanteType registro;
						
						registro = resultado[i];
						
						//Seteamos en el bean los campos obtenidos a traves del WS
						if (registro.getFeRegistro() != null) {					
							registroRecBean.setFeRegistro(registro.getFeRegistro());
						}
						registroRecBean.setNumRegistro(registro.getNmRegistro());
						registroRecBean.setCdOficinaOrigen(registro.getCdOficinaOrigen());
						
						//Añadimos a la lista
						listRegistroRec.add(registroRecBean);						
					}				
					setRequestAttribute("resultado", formulario.getResultado());
					setRequestAttribute("registro", listRegistroRec.get(0));
				}
			}else {
				//resultado vacia
				key = "field.consultaRec.errores.sinResultados";
				
				setRequestAttribute(STRING_SERVICIO, formulario.getServicio().toString());
				hayError = true;
			}
			
			if(hayError){
				String message = RESOURCE_BUNDLE.getString(key);
				message = (message.length()>100) ? message.substring(0,99) : message;
				
				logServicioBean.setResultado(Constantes.RESULTADO_ER);
				logServicioBean.setDesTipoError(message);
				logServicioBean.setIdTipoError(Constantes.LOG_ERROR_FISICO.charAt(0));
				
				logger.error(message);
			}else{
				logServicioBean.setResultado(Constantes.RESULTADO_OK);
				formulario.setResultado(Constantes.RESULTADO_OK);
			}
		}catch(Exception e){	
			logger.error("Error ProbarServiciosExternosSpring - servicioExternoREC",e);
			String message = e.getMessage();
			message = (message.length()>100) ? message.substring(0,99) : message;
			
			logServicioBean.setResultado(Constantes.RESULTADO_ER);
			logServicioBean.setDesTipoError(message);
			logServicioBean.setIdTipoError(Constantes.LOG_ERROR_FISICO.charAt(0));
			
			logger.error("Error al conectar con REC",e);
		}
	}
	
	/**
	 * Servicio externo alfreco.
	 *
	 * @param formulario el formulario
	 * @param logServicioBean el log servicio bean
	 */
	private void servicioExternoAlfreco(ProbarServiciosExternosForm formulario,LogServicioBean logServicioBean) {
		logger.info("Prueba Alta Documento Alfresco");

		try{
			TipoDocumento tipoDoc= new TipoDocumento();
			tipoDoc.setId(Integer.valueOf(Constantes.TIPO_DOCUMENTO_PRUEBA));
			
			DocumentoBean doc = new DocumentoBean();
			doc.setTipoDocumento(tipoDoc);
			doc.setNombre(formulario.getNombreDocumento());
			doc.setDescripcion(formulario.getNombreDocumento());
			doc.setFechaCreacion(new Date());
			doc.setContenidoDocumento(formulario.getFichero().getBytes());// el fichero cargado del form en bytes[]
			doc.setTamano((int) (formulario.getFichero().getSize()/1024));
			doc.setExtension(formulario.getFichero().getOriginalFilename().substring(formulario.getFichero().getOriginalFilename().lastIndexOf('.')+1, formulario.getFichero().getOriginalFilename().length()));
			doc.setEntorno(Constantes.ENTORNO_PRUEBAS);
			
			String rutaDoc = documentoManager.insertarDocumentoPrueba(doc);
			setRequestAttribute("rutaDocSistemaFicheros", rutaDoc);
			formulario.setRutaFichero(rutaDoc);
			
			formulario.setResultado(Constantes.RESULTADO_OK);
			logServicioBean.setResultado(Constantes.RESULTADO_OK);
		
		}catch(Exception e){
			String message = e.getMessage();
			message = (message.length()>100) ? message.substring(0,99) : message;
			
			logServicioBean.setResultado(Constantes.RESULTADO_ER);
			logServicioBean.setIdTipoError(Constantes.LOG_ERROR_FISICO.charAt(0));
			logServicioBean.setDesTipoError(message);
			logger.error("Error ProbarServiciosExternosSpring - servicioExternoAlfreco",e);		
			logger.error("Error al conectar con el Sistema de Ficheros",e);
		}
		
		setRequestAttribute("resultado", formulario.getResultado());
		setRequestAttribute("docSubidoOK", formulario.getResultado());
	}
	
	/**
	 * Servicio externo EJB.
	 *
	 * @param formulario el formulario
	 * @param logServicioBean el log servicio bean
	 */
	private void servicioExternoEJB(ProbarServiciosExternosForm formulario,LogServicioBean logServicioBean){
		formulario.setNumeroJustificante(solicitudesManager.obtenerNumeroSolicitud());
		
		if (formulario.getNumeroJustificante()!=null){
			logServicioBean.setResultado(Constantes.RESULTADO_OK);		
		}else{
			logServicioBean.setResultado(Constantes.RESULTADO_ER);
			logServicioBean.setIdTipoError(Constantes.LOG_ERROR_FISICO.charAt(0));
			logServicioBean.setDesTipoError(RESOURCE_BUNDLE.getString("field.ServiciosExternos.errorEJB"));
		}
	}
	
	/**
	 * Servicio externo certificado.
	 *
	 * @param formulario el formulario
	 * @return el string
	 */
	private String servicioExternoCertificado(ProbarServiciosExternosForm formulario){
		String respuestaWS = "N";
		String resultado = null;
		String certificado = formulario.getSignature();
		
		try{
			if(certificado!=null){
					logger.debug("Tengo mi certificado y voy a validarlo.");
					logger.debug(certificado);
					
					ParamCert param = new ParamCert();
					Base64Coder coder = new  Base64Coder();
					byte[] aCoder = coder.decodeBase64(certificado.getBytes());
					certificado = SignDataUtil.getInstance().getCertificateFromSignXML(aCoder);
					param.setCertificado(certificado);
					param.setIdAplicacion(properties.getProperty("webservices.idAplicacion"));
					param.setModovalidacion(Integer.valueOf(properties.getProperty("webservices.modoValidacion")));
					param.setObtenerinfo(true);
					
					logger.debug("Antes de validar el certificado");
					ValidarCertificado validarCertificado = new ValidarCertificado();
					resultado = validarCertificado.validar2(param);
					
					if(recuperarDatosCertificado(resultado)){
						formulario.setCertificadoDigital(RESOURCE_BUNDLE.getString("field.ServiciosExternos.certificadoOK"));
							
					}else{
						formulario.setCertificadoDigital(RESOURCE_BUNDLE.getString("field.ServiciosExternos.certificadoError"));
						
					}
					respuestaWS="S";
				}else{
					respuestaWS="S";
					logger.error("El array de certificados es nulo");
				}
			}catch(Exception e){

				logger.error(RESOURCE_BUNDLE.getString("certificados.error.validacion"));
				logger.error("Error ProbarServiciosExternosSpring - servicioExternoCertificado",e);	
				throw new ApplicationException(RESOURCE_BUNDLE.getString("certificados.error.validacion")+" "+e.getMessage());
			}
			return respuestaWS;
	}
	
	/**
	 * Recuperar datos certificado.
	 *
	 * @param resultado el resultado
	 * @return verdadero, si tiene exito
	 * @throws ApplicationException el application exception
	 */
	public boolean recuperarDatosCertificado(String resultado) throws ApplicationException{
		
		try{
			logger.debug("Recuperando Certificados del Ciudadano");
			Reader validacionCertificado = new StringReader(resultado);
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			Document XMLDoc = factory.newDocumentBuilder().parse(new InputSource(validacionCertificado));
			XPath xpath = XPathFactory.newInstance().newXPath();
			
			String tipoPersona = xpath.evaluate("/mensajeSalida/respuesta/ResultadoProcesamiento/InfoCertificado/Campo[idCampo=\"clasificacion\"]/valorCampo", XMLDoc);
			logger.debug("Tipo de Certificado: "+tipoPersona);
			/*
			 * Campo clasificación
			 * 0 = Persona física
			 * 1 = Persona jurídica
			 */	
			
			if(tipoPersona != null && tipoPersona.equals("1")){
				logger.error(RESOURCE_BUNDLE.getString("certificados.error.juridico"));
				throw new ApplicationException("certificados.error.juridico");
				
			}
			else if(tipoPersona != null && tipoPersona.equals("0")){		
				return true;
			}
			else{
				return false;
			}
			
		}catch(Exception e){
			logger.error(RESOURCE_BUNDLE.getString("field.ServiciosExternos.errorRecuperarDatos"));
			logger.error("Error ProbarServiciosExternosSpring - recuperarDatosCertificado",e);	
			throw new ApplicationException(RESOURCE_BUNDLE.getString("field.ServiciosExternos.errorRecuperarDatos"));
		}
	}
	
	/**
	 * Obtiene el documentos manager.
	 *
	 * @return el documentos manager
	 */
	public DocumentoManager getDocumentosManager() {
		return documentoManager;
	}

	/**
	 * Establece el documento manager.
	 *
	 * @param pDocumentosManager el nuevo documento manager
	 */
	public void setDocumentoManager(DocumentoManager pDocumentosManager) {
		this.documentoManager = pDocumentosManager;
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
	 * Obtiene el log servicio manager.
	 *
	 * @return el log servicio manager
	 */
	public LogServicioManager getLogServicioManager(){
		return logServicioManager;
	}
	
	/**
	 * Establece el log servicio manager.
	 *
	 * @param logServicioManager el nuevo log servicio manager
	 */
	public void setLogServicioManager(LogServicioManager logServicioManager){
		this.logServicioManager = logServicioManager;
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
	 * Obtiene el tipo documento manager.
	 *
	 * @return el tipo documento manager
	 */
	public TipoDocumentoManager getTipoDocumentoManager() {
		return tipoDocumentoManager;
	}

	/**
	 * Establece el tipo documento manager.
	 *
	 * @param tipoDocumentoManager el nuevo tipo documento manager
	 */
	public void setTipoDocumentoManager(TipoDocumentoManager tipoDocumentoManager) {
		this.tipoDocumentoManager = tipoDocumentoManager;
	}
	
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
	 * Obtiene el entidad financiera manager.
	 *
	 * @return el entidad financiera manager
	 */
	public EntidadFinancieraManager getEntidadFinancieraManager() {
		return entidadFinancieraManager;
	}

	/**
	 * Establece el entidad financiera manager.
	 *
	 * @param entidadFinancieraManager el nuevo entidad financiera manager
	 */
	public void setEntidadFinancieraManager(EntidadFinancieraManager entidadFinancieraManager) {
		this.entidadFinancieraManager = entidadFinancieraManager;
	}	

}
