package es.map.ipsg.action.solicitud;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.net.URL;
import java.security.KeyStore.PrivateKeyEntry;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.axis.attachments.OctetStream;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.apache.spring.Globals;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import com.lowagie.text.pdf.PdfCopyFields;
import com.lowagie.text.pdf.PdfReader;

import es.gob.afirma.afirma5ServiceInvoker.Afirma5ServiceInvokerFacade;
import es.gob.afirma.signature.SignatureConstants;
import es.gob.afirma.signature.Signer;
import es.gob.afirma.signature.SignersFactory;
import es.gob.afirma.signature.SigningException;
import es.gob.afirma.transformers.TransformersConstants;
import es.gob.afirma.transformers.TransformersFacade;
import es.gob.afirma.utils.Base64Coder;
import es.gob.afirma.utils.DSSConstants.DSSTagsRequest;
import es.gob.afirma.utils.GeneralConstants;
import es.map.ips.common.model.OrderType;
import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.ConvocatoriaQuery;
import es.map.ips.model.DocumentoQuery;
import es.map.ips.model.EntidadFinanciera;
import es.map.ips.model.PagoSolicitudQuery;
import es.map.ips.model.Pais;
import es.map.ips.model.Provincia;
import es.map.ips.model.RegistroSolicitudQuery;
import es.map.ips.model.SolicitudComunQuery;
import es.map.ips.model.SolicitudPropiosQuery;
import es.map.ips.model.TipoDocumento;
import es.map.ips.model.TipoDocumentoQuery;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.bean.CamposPropiosBean;
import es.map.ipsg.bean.CiudadanoBean;
import es.map.ipsg.bean.ConvocatoriasBean;
import es.map.ipsg.bean.DocumentoBean;
import es.map.ipsg.bean.JustificanteBean;
import es.map.ipsg.bean.LogSolicitudBean;
import es.map.ipsg.bean.PagoSolicitudBean;
import es.map.ipsg.bean.RegistroSolicitudBean;
import es.map.ipsg.bean.RegistroSolicitudJustificanteBean;
import es.map.ipsg.bean.SolicitudBean;
import es.map.ipsg.bean.SolicitudPropiosBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.clienteWS.registroRec3.RegistrarElectronicosService;
import es.map.ipsg.clienteWS.registroRec3.RegistrarElectronicosServiceProxy;
import es.map.ipsg.clienteWS.registroRec3.RegistrarServiceLocator;
import es.map.ipsg.clienteWS.registroRec3.RegistrarServiceSoapBindingStub;
import es.map.ipsg.clienteWS.registroRec3.RegistroType;
import es.map.ipsg.clienteWS.registroRec3.type.AutenticacionType;
import es.map.ipsg.clienteWS.registroRec3.type.DocumentoType;
import es.map.ipsg.clienteWS.registroRec3.type.InteresadoType;
import es.map.ipsg.clienteWS.registroRec3.type.JustificanteType;
import es.map.ipsg.clienteWS.registroRec3.type.RespuestaType;
import es.map.ipsg.manager.ConvocatoriasManager;
import es.map.ipsg.manager.DocumentoManager;
import es.map.ipsg.manager.LogSolicitudManager;
import es.map.ipsg.manager.PagoSolicitudManager;
import es.map.ipsg.manager.RegistroSolicitudManager;
import es.map.ipsg.manager.SolicitudesManager;
import es.map.ipsg.manager.SolicitudesPropiosManager;
import es.map.ipsg.manager.UsuarioManager;
import es.map.ipsg.res.ResourceLocator;
import es.map.ipsg.util.CSVInside;
import es.map.ipsg.util.CSVStorage;
import es.map.ipsg.util.Constantes;
import es.map.ipsg.util.IpsUtils;
import es.map.ipsg.util.Jasper;
import es.map.ipsg.util.SHA0;
import es.map.ipsg.util.SistemaFicheros;
import es.map.ipsg.util.Util;
import es.map.ipsg.util.UtilesIPSG;
import es.mpt.dsic.inside.ws.service.EeUtilService;
import es.mpt.dsic.inside.ws.service.EeUtilServiceProxy;
import es.redsara.misim.misim_bus_webapp.EnvioMensajesServiceWSBindingPortType;
import es.redsara.misim.misim_bus_webapp.EnvioMensajesServiceWSBindingPortTypeProxy;
import es.redsara.misim.misim_bus_webapp.peticion.Adjunto;
import es.redsara.misim.misim_bus_webapp.peticion.DestinatarioMail;
import es.redsara.misim.misim_bus_webapp.peticion.Destinatarios;
import es.redsara.misim.misim_bus_webapp.peticion.MensajeEmail;
import es.redsara.misim.misim_bus_webapp.peticion.Mensajes;
import es.redsara.misim.misim_bus_webapp.peticion.Peticion;
import es.redsara.misim.misim_bus_webapp.respuesta.Respuesta;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * El Class ReintentarRegistroSpring.
 */
public class ReintentarRegistroSpring extends AbstractSpring {

	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(ReintentarRegistroSpring.class);
	
	/** el registrar ws. */
	private RegistrarElectronicosService registrarWs;
	
	/** La constante BUNDLE_ERROR. */
	private static final String BUNDLE_ERROR = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_ERROR);
	
	/** La constante STRING_CDASUNTO. */
	private static final String STRING_CDASUNTO = "cdAsunto";
	
	/** La constante STRING_MENSAJEREGISTRO. */
	private static final String STRING_MENSAJEREGISTRO = "mensajeRegistro";
	
	/** La constante STRING_SUCCESS. */
	private static final String STRING_SUCCESS = "success";
	
	/** La constante STRING_SIMPLEDATEFORMAT. */
	private static final String STRING_SIMPLEDATEFORMAT = "dd/MM/yyyy HH:mm:ss";
	
	/** el properties. */
	private static Properties properties;
	
	/** el usuario manager. */
	private UsuarioManager usuarioManager;
	
	/** el solicitudes manager. */
	private SolicitudesManager solicitudesManager;
	
	/** el pago solicitud manager. */
	private PagoSolicitudManager pagoSolicitudManager;
	
	/** el documento manager. */
	private DocumentoManager documentoManager;
	
	/** el registro solicitud manager. */
	private RegistroSolicitudManager registroSolicitudManager;
	
	/** el log solicitud manager. */
	private LogSolicitudManager logSolicitudManager;
	
	/** el convocatorias manager. */
	private ConvocatoriasManager convocatoriasManager;
	
	/** La constante TIMEOUT. */
	private static final int TIMEOUT = 30000;
	
	/** el solicitudes propios manager. */
	private SolicitudesPropiosManager solicitudesPropiosManager;

	/**
	 * Crea una nueva reintentar registro spring.
	 */
	public ReintentarRegistroSpring() {
		try {				
			setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
			setSolicitudesManager((SolicitudesManager) getBean("solicitudesManager"));
			setDocumentoManager((DocumentoManager) getBean("documentoManager"));
			setRegistroSolicitudManager((RegistroSolicitudManager) getBean("registroSolicitudManager"));
			setLogSolicitudManager((LogSolicitudManager) getBean("logSolicitudManager"));
			setPagoSolicitudManager((PagoSolicitudManager) getBean("pagoSolicitudManager"));
			setConvocatoriasManager((ConvocatoriasManager) getBean("convocatoriaManager"));
			setSolicitudesPropiosManager((SolicitudesPropiosManager) getBean("solicitudesPropiosManager"));
			properties = (Properties) getBean("propertiesBean");
		} catch (Exception e) {
			logger.error(" Error ReintentarRegistroSpring: ",e);
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	protected String doExecute(SpringForm form) throws Exception {

		String menu_solicitudes = RESOURCE_BUNDLE.getString("field.menu.solicitudes");
		this.getRequest().getSession().setAttribute("pagActiva", menu_solicitudes);
		try{	
			String idRegistro = this.getRequest().getParameter("idRegistro");
			registrarWs = new RegistrarElectronicosServiceProxy(properties.getProperty("envioRegistro.url"));
			logger.info("Entra en el reintento de registro");
			
			//Crear RegistroType	
			RegistroType registroType =  new RegistroType();
			registroType.setCdAsunto(properties.getProperty(STRING_CDASUNTO));
			registroType.setCdOficinaOrigen(properties.getProperty("cdOrOrigen"));
			registroType.setCdUnidadDestino(properties.getProperty("cdUgDestino"));
			logger.info("RegistroType Creado");
			
			//Insertamos el interesado
			InteresadoType interesadoType[] = new InteresadoType[1];
			InteresadoType interesadoAux = new InteresadoType();

			//Buscar solicitud
			SolicitudBean solicitudBean;
			SolicitudComunQuery solicitudQuery = new SolicitudComunQuery();
			solicitudQuery.setIdSolicitud(Long.parseLong(idRegistro));
			solicitudBean = solicitudesManager.buscarSolicitudById(solicitudQuery);
			
			// Nombre
			if(solicitudBean.getNombre() != null){
				if(solicitudBean.getNombre().length()<Constantes.LONGITUD_MAX_CAMPO_REC){
					interesadoAux.setTlNombreInteresado(solicitudBean.getNombre());
				}else{
					interesadoAux.setTlNombreInteresado(solicitudBean.getNombre().substring(0,Constantes.LONGITUD_MAX_CAMPO_REC-1));
				}	
			}else{
				interesadoAux.setTlNombreInteresado(" ");
			}
			
			// Apellido 1
			if(solicitudBean.getPrimerApellido() != null){
				if(solicitudBean.getPrimerApellido().length()<Constantes.LONGITUD_MAX_CAMPO_REC){
					interesadoAux.setTlApellido1Interesado(solicitudBean.getPrimerApellido());
				}else{
					interesadoAux.setTlApellido1Interesado(solicitudBean.getPrimerApellido().substring(0,Constantes.LONGITUD_MAX_CAMPO_REC-1));
				}
			}else{
				interesadoAux.setTlApellido1Interesado(" ");
			}
			
			// Apellido 2
			if(solicitudBean.getSegundoApellido() != null){
				if(solicitudBean.getSegundoApellido().length()<Constantes.LONGITUD_MAX_CAMPO_REC){
					interesadoAux.setTlApellido2Interesado(solicitudBean.getSegundoApellido());
				}else{
					interesadoAux.setTlApellido2Interesado(solicitudBean.getSegundoApellido().substring(0,Constantes.LONGITUD_MAX_CAMPO_REC-1));
				}
			}else{
				interesadoAux.setTlApellido2Interesado("");
			}
			
			if(solicitudBean.getCalleDomicilio() != null){
				interesadoAux.setTlDireccionInteresado(solicitudBean.getCalleDomicilio());
			}else{
				interesadoAux.setTlDireccionInteresado("");
			}
			
			if(solicitudBean.getCodigoPostalDomicilio() != null){
				interesadoAux.setCdCodigoPostalInteresado(solicitudBean.getCodigoPostalDomicilio());
			}else{
				interesadoAux.setCdCodigoPostalInteresado("");
			}
			
			if(solicitudBean.getDesProvinciaDomicilio()!= null){
				interesadoAux.setCdProvinciaInteresado(solicitudBean.getDesProvinciaDomicilio());
			}else{
				interesadoAux.setCdProvinciaInteresado("");
			}
			
			if(solicitudBean.getMunicipioDomicilio() != null){
				interesadoAux.setCdMunicipioInteresado(solicitudBean.getMunicipioDomicilio());
			}else{
				interesadoAux.setCdMunicipioInteresado("");
			}
			
			int numtelefonoAux = solicitudBean.getTelefono().lastIndexOf('/');
			String telefonoAux = comprobarTelefono(solicitudBean.getTelefono());
			
			interesadoAux.setTlTelefonoContactoInteresado(telefonoAux);
			registroType.setInteresados(interesadoType);
			registroType.setCdDocumentacionFisicaSoportes("03");
			
			// Se evalua el tipo de documento de identidad del solicitante
			String tipoDocIdentidad = "";
			
			if(Util.esNie(solicitudBean.getNif())){
				tipoDocIdentidad = Constantes.INTERESADO_TIPO_DOCUMENTO_EXTRANJERO;
			}else{
				tipoDocIdentidad = Constantes.INTERESADO_TIPO_DOCUMENTO_NACIONAL;
			}
			
			interesadoAux.setCdTipoDocIndentificativoInteresado(tipoDocIdentidad);
			interesadoAux.setTlNumDocIdentificativoInteresado(solicitudBean.getNif());
			interesadoAux.setTlCorreoElectronicoInteresado(solicitudBean.getEmail());

			// Corrección Incidencia ciudadano con domicilio extranjero sin provincia.
			Pais pais=solicitudBean.getPais();
			String codigoPais="";
			
			if(null!=pais){
				codigoPais=pais.getCodigo();
			}
			
			Provincia provincia=solicitudBean.getProvinciaByIdProvinciaDomicilio();
			String codigoProvincia="";
			
			if(null!=provincia){
				codigoProvincia=provincia.getCodigo();
			}
			
			interesadoAux.setCdPaisInteresado(codigoPais);
			interesadoAux.setCdProvinciaInteresado(codigoProvincia);
			interesadoAux.setCdMunicipioInteresado("");
			interesadoAux.setTlDEHInteresado(solicitudBean.getEmail());
			interesadoAux.setCdCanalPreferenteInteresado("02");		
			interesadoAux.setTlObservaciones("ninguna");
			interesadoType[0] = interesadoAux;
			logger.info("Interesado Creado");

			//Declarar octetStream
			ArrayList<DocumentoBean> documentosAdjuntos;
			SolicitudComunQuery solicitudAux = new SolicitudComunQuery();
			solicitudAux.setIdSolicitud(Long.parseLong(idRegistro));

			TipoDocumentoQuery tipoDocumentoQuery = new TipoDocumentoQuery();
			tipoDocumentoQuery.addIdIn(Constantes.TIPO_DOCUMENTO_ANEXO_SOLICITUD);
			tipoDocumentoQuery.addIdIn(Constantes.TIPO_DOCUMENTO_FORMULARIO);
			tipoDocumentoQuery.addIdIn(Constantes.TIPO_DOCUMENTO_JUSTIFICANTE_REDUCCION_TASA);
			tipoDocumentoQuery.addIdIn(Constantes.TIPO_DOCUMENTO_DISCAPACIDAD);

			DocumentoQuery documentoQuery = new DocumentoQuery();
			documentoQuery.setSolicitudComun(solicitudAux);
			documentoQuery.setTipoDocumento(tipoDocumentoQuery);

			//Busco los documentos anexados a la solicitud (1) y el formulario html (9)
			documentosAdjuntos=documentoManager.buscarDocumentosByIdSolicitud(documentoQuery);

			logger.info("Numero de documentos a registrar: " + documentosAdjuntos.size());
			int numRegistros = documentosAdjuntos.size();
			OctetStream[] docs = new OctetStream[numRegistros];
			String idSolicitud = idRegistro;
			
			
			DocumentoType registroDocumentoType[] = new DocumentoType[1];

			Calendar cal1 = Calendar.getInstance();
			
			StringBuilder fecha = new StringBuilder();
			fecha.append(cal1.get(Calendar.DATE)).append("").append("/")
				.append(cal1.get(Calendar.MONTH)).append("").append("/")
				.append(cal1.get(Calendar.YEAR)).append("").append(" ")
				.append(cal1.get(Calendar.HOUR)).append("").append(":")
				.append(cal1.get(Calendar.MINUTE)).append("").append(":")
				.append(cal1.get(Calendar.SECOND)).append("");
			
			SHA0 hash = new SHA0();
			//Agregamos al Registro los documentos que se adjuntaron a la solicitud(solo el html)
			//Para cada documento adjunto, se obtiene la firma generada en el intento de registro
			int i=0;
			for(int x=0;x<documentosAdjuntos.size();x++){
				byte[] salida;
				String idAlfresco = null;
				DocumentoType registroDocumentoAux = new DocumentoType();
				DocumentoBean ficheroHash=documentosAdjuntos.get(x);
				TipoDocumento tipoDocumento=ficheroHash.getTipoDocumento();
				tipoDocumento.getDescripcion();
				String nombreAlfresco = documentosAdjuntos.get(x).getNombreAlfresco();
				logger.info("NombreDocumento: "+nombreAlfresco);
				int numNombre = nombreAlfresco.lastIndexOf('.');
				String nombre = nombreAlfresco.substring(0,numNombre);

				try{
					logger.info("Recuperando el documento: "+nombreAlfresco);
					
					// TODO ALFRESCO
					byte[] salidaDoc = documentoManager.obtenerContenidoDocumento(ficheroHash);
					
					String extension = FilenameUtils.getExtension(documentosAdjuntos.get(x).getNombreAlfresco());
					if("html".equals(extension)){
						
						idAlfresco = nombre + "Firma.XML";
						logger.info("Recuperando la firma del documento: "+idAlfresco);								
						
						// TODO ALFRESCO
						salida = documentoManager.obtenerContenidoDocumento(ficheroHash);
						
						registroDocumentoAux.setDsNombre(documentosAdjuntos.get(x).getNombreAlfresco());
						String nombreHash=documentosAdjuntos.get(x).getNombreAlfresco();
						byte[] byteHash = nombreHash.getBytes();
						String hashFile=obtenerHash(hash, byteHash, 0);

						byte[] codigo64 = hashFile.getBytes();
						hashFile = IpsUtils.encodeBase64(codigo64);
						registroDocumentoAux.setBlFirma(UtilesIPSG.encodeBase64(salida));
						registroDocumentoAux.setCdValidez("04");
						registroDocumentoAux.setCdTipo("01");
						registroDocumentoAux.setBlTimeStamp(fecha.toString());
						registroDocumentoAux.setBlDocumento(documentosAdjuntos.get(x).getNombreAlfresco());
						registroDocumentoAux.setCdFirmado("1");
						registroDocumentoAux.setBlValidacionOCSP("s");
						registroDocumentoAux.setDsTipoMIME("s");
						registroDocumentoAux.setBlPKCertificado("");
						registroDocumentoAux.setBlHash(hashFile);
						registroDocumentoAux.setTlObservaciones("");
						registroDocumentoType[i] = registroDocumentoAux;
						docs[i] = new OctetStream(salidaDoc);
						i++;
					}		
				}catch(Exception e){
					logger.error("Error  ReintentarRegistroSpring - error recuperando el documento de firma de la solicitud: "+idAlfresco,e);
					
				}
			}
			registroType.setDocumentos(registroDocumentoType);
			registroType.setCdTipoRegistro("0");
			registroType.setTlResumen(properties.getProperty(STRING_CDASUNTO));
			// Cliente REC3 ligero
			registroType.setFlJustificante("0");
			logger.info("RegistrosAñadidos");

			JustificanteType justificanteType;
			RespuestaType respuestaType = new RespuestaType();
			try{
				logger.info("Se va a llamar al REC");
				String idAplicacion = properties.getProperty("idAplicacion");
				String password = properties.getProperty("password");

				AutenticacionType autentication = new AutenticacionType();
				autentication.setIdAplicacion(idAplicacion);
				autentication.setPassword(password);

				RegistrarServiceLocator locator = new RegistrarServiceLocator();
				locator.setRegistrarServiceEndpointAddress(properties.getProperty("envioRegistro.url"));

				RegistrarServiceSoapBindingStub binding = (RegistrarServiceSoapBindingStub) locator.getRegistrarService();
				binding.setTimeout(TIMEOUT);

				justificanteType = registrarWs.registrar(autentication, registroType);
				respuestaType = justificanteType.getRespuesta();
			}catch(Exception e){
				logger.error("Error ReintentarRegistroSpring- No se ha podido conectar con el rec: ",e);
				
				this.setRequestAttribute(STRING_MENSAJEREGISTRO, "No se ha podido conectar con el REC");
				return STRING_SUCCESS;
			}
			
			if(!"00".equals(respuestaType.getCdRespuesta())){
				logger.error("Se ha producido un error");
				logger.error("Error: "+respuestaType.getDsRespuesta());
				this.setRequestAttribute(STRING_MENSAJEREGISTRO, "Se ha producido un error durante el registro");
				return STRING_SUCCESS;
			}
			
			if(justificanteType.getNmRegistro() == null){
				logger.error("El justificante es null");
				this.setRequestAttribute(STRING_MENSAJEREGISTRO, "Se ha producido un error durante el registro");
				return STRING_SUCCESS;
			}

			SolicitudComunQuery solicitudQueryAux = new SolicitudComunQuery();
			solicitudQueryAux.setIdSolicitud(Long.parseLong(idRegistro));

			RegistroSolicitudQuery registroSolicitudQuery = new RegistroSolicitudQuery();
			registroSolicitudQuery.setSolicitudComun(solicitudQueryAux);
			registroSolicitudQuery.addOrder(RegistroSolicitudQuery.ID, OrderType.DESC);
			List<RegistroSolicitudBean> registros = registroSolicitudManager.buscarRegistroSolicitudAll(registroSolicitudQuery);

			if(registros!=null && registros.size()>0){
				RegistroSolicitudBean registroSolicitudBean = registros.get(0);

				RegistroSolicitudBean registroSolicitudBeanInsertar = new RegistroSolicitudBean();
				// En vez de actualizar la tupla en la tabla de registro solicitud, se crea una nueva
				registroSolicitudBeanInsertar.setNumeroRegistro(justificanteType.getNmRegistro());
				Date date = new SimpleDateFormat(STRING_SIMPLEDATEFORMAT, Locale.ENGLISH).parse(justificanteType.getFeRegistro());
				registroSolicitudBeanInsertar.setFechaIntento(date);
				registroSolicitudBeanInsertar.setFechaRegistro(date);
				registroSolicitudBeanInsertar.setSolicitante(Constantes.REGISTRO_SOLICITANTE_CHAR);
				registroSolicitudBeanInsertar.setCodigoError("");
				registroSolicitudBeanInsertar.setDescripcionError("");
				registroSolicitudBeanInsertar.setIdSolicitud(Long.parseLong(idRegistro));
				registroSolicitudBeanInsertar.setOficinaRegistro(justificanteType.getCdOficinaOrigen());
				registroSolicitudBeanInsertar.setResultado(Constantes.REGISTRO_RESULTADO_OK);

				registroSolicitudManager.guardarRegistroSolicitud(registroSolicitudBeanInsertar);

				SolicitudBean solicitudBeanAux;
				solicitudBeanAux = solicitudesManager.buscarSolicitudById(solicitudQueryAux);
				solicitudBeanAux.setFechaUtlActualizacion(new Date());


				// Guardar el log de la actualizacion.
				// Se recupera el usuario de gestion.
				UsuarioBean usuarioSession = (UsuarioBean) getRequest().getSession().getAttribute("usuario");
				String username = usuarioSession.getLogin();
				UsuarioQuery usuarioQuery = new UsuarioQuery();
				usuarioQuery.setLogin(username);
				UsuarioBean usuarioBean = usuarioManager.buscarUsuario(usuarioQuery);
				
				LogSolicitudBean logSolicitudBean = new LogSolicitudBean();
				logSolicitudBean.setActor(usuarioBean.getNif());
				Date fechaAux =new Date();
				logSolicitudBean.setFecha(fechaAux);
				logSolicitudBean.setTipoOperacion(Constantes.LOG_SOLICITUD_CAMBIO_ESTADO.charAt(0));
				logSolicitudBean.setDetalleOperacion(RESOURCE_BUNDLE.getString("logSolicitud.detalleModificacion"));
				logSolicitudBean.setIdSolicitud(Long.parseLong(idRegistro));
				logSolicitudBean.setIdEstadoActual(Constantes.LOG_SOLICITUD_ESTADO_REGISTRADO);
				logSolicitudBean.setIdEstadoAnterior(String.valueOf(solicitudBeanAux.getIdEstadoSolicitud()));
				logSolicitudBean.setTipoActor(Constantes.LOG_TIPO_ACTOR_USUARIO_CHAR);
				
				//Se inserta en la tabla de log
				Integer idLog=insertarLogSolicitud(logSolicitudBean);

				if(idLog == null){
					logger.error(RESOURCE_BUNDLE.getString("logSolicitud.error"));
				}

				//Creamos el justificantePDF y el registro de solicitud PDF //XML
				RegistroSolicitudJustificanteBean registroSolicitudJustificante;
				registroSolicitudJustificante = toRegistroCompleto(idRegistro);

				//Añadimos los documentos adjuntados y el html al registroType para crear el pdf solicitud
				DocumentoType registroDocumentoType2[] = new DocumentoType[numRegistros];
				
				//metodo para añadir los documentos adjuntos al registroType y sacarlos en el justificante solicitud
				for(int x=0;x<documentosAdjuntos.size();x++){
					byte[] salida;
					String idAlfresco = null;
					DocumentoType registroDocumentoAux = new DocumentoType();
					DocumentoBean ficheroHash=documentosAdjuntos.get(x);
					TipoDocumento tipoDocumento=ficheroHash.getTipoDocumento();
					tipoDocumento.getDescripcion();
					String nombreAlfresco = documentosAdjuntos.get(x).getNombreAlfresco();
					logger.info("NombreDocumento: "+nombreAlfresco);
					int numNombre = nombreAlfresco.lastIndexOf('.');
					String nombre = nombreAlfresco.substring(0,numNombre);

					try{
						logger.info("Recuperando el documento: "+nombreAlfresco);
						
						// TODO ALFRESCO
						byte[] salidaDoc = documentoManager.obtenerContenidoDocumento(ficheroHash);	
						
						String extension = FilenameUtils.getExtension(documentosAdjuntos.get(x).getNombreAlfresco());
						if("html".equals(extension)){
							
							idAlfresco = nombre + "Firma.XML";
							logger.info("Recuperando la firma del documento: "+idAlfresco);								
							
							// TODO ALFRESCO
							salida = documentoManager.obtenerContenidoDocumento(ficheroHash);
							
							registroDocumentoAux.setCdTipo("02");
							registroDocumentoAux.setDsNombre(documentosAdjuntos.get(x).getNombreAlfresco());
							String nombreHash=documentosAdjuntos.get(x).getNombreAlfresco();
							byte[] byteHash = nombreHash.getBytes();
							String hashFile=obtenerHash(hash, byteHash,1);
							byte[] codigo64 = hashFile.getBytes();
							hashFile = IpsUtils.encodeBase64(codigo64);
							registroDocumentoAux.setBlHash(hashFile);
							registroDocumentoAux.setBlFirma(UtilesIPSG.encodeBase64(salida));
						}else{
							registroDocumentoAux.setDsNombre(documentosAdjuntos.get(x).getNombre()+"."+extension);
							registroDocumentoAux.setCdTipo("01");
							String nombreHash=documentosAdjuntos.get(x).getNombreAlfresco();
							byte[] byteHash = nombreHash.getBytes();
							String hashFile=obtenerHash(hash, byteHash, 1);
							byte[] codigo64 = hashFile.getBytes();
							hashFile = IpsUtils.encodeBase64(codigo64);
							registroDocumentoAux.setBlHash(hashFile);
						}	

						registroDocumentoAux.setCdValidez("04");
						registroDocumentoAux.setBlTimeStamp(fecha.toString());
						registroDocumentoAux.setBlDocumento(documentosAdjuntos.get(x).getNombreAlfresco());
						registroDocumentoAux.setCdFirmado("1");
						registroDocumentoAux.setBlValidacionOCSP("s");
						registroDocumentoAux.setDsTipoMIME("s");
						registroDocumentoAux.setBlPKCertificado("");
						registroDocumentoAux.setTlObservaciones("");
						registroDocumentoType2[x] = registroDocumentoAux;
						docs[x] = new OctetStream(salidaDoc);	
					}catch(Exception e){
						logger.error(" error ReintentarRegistroSpring - Error recuperando el documento de firma de la solicitud: "+idAlfresco,e);
						
					}
				}

				registroType.setDocumentos(registroDocumentoType2);
				registroType.setCdTipoRegistro("0");
				registroType.setTlResumen(properties.getProperty(STRING_CDASUNTO));
				logger.info("RegistrosAñadidos");

				int resultado = crearPdf(justificanteType,registroType,registroSolicitudJustificante);//pdf solicitud
				
				if (resultado == 0) {
					// algo ha fallado y salimos con el mensaje de error que se introdujo en el metodo de crearPdf
					return STRING_SUCCESS;
				} else {
					// todo ok
					this.setRequestAttribute(STRING_MENSAJEREGISTRO, "Se ha producido el registro de la solicitud");
				
				// se actualiza el estado de la solicitud de (5) -> pdte.regustro  a (3) -> registrado
					actualizarEstadoSolicitud(solicitudBeanAux, registroSolicitudBean, 3);
					
					// Finalmente, se mueven los documentos de la solicitud a su ubicación definitva de Solo Lectura
					ArrayList<DocumentoBean> listaDocumentosBean;
					listaDocumentosBean = recuperarDocumentosSolicitud(idSolicitud);
					documentoManager.copiarFicheros(listaDocumentosBean , properties.getProperty("sistemaficheros.url.escritura"));
				}
											
				// Finalmente, se mueven los documentos de la solicitud a su ubicación definitva de Solo Lectura
				ArrayList<DocumentoBean> listaDocumentosBean;
				listaDocumentosBean = recuperarDocumentosSolicitud(idSolicitud);
				documentoManager.copiarFicheros(listaDocumentosBean , properties.getProperty("sistemaficheros.url.escritura"));
			}else{
				logger.error("No se han encontrado registros para la solicitud: "+idSolicitud);
				this.getRequest().setAttribute("descripcionError", RESOURCE_BUNDLE.getString("field.errorGenerico"));
				return "errorGenerico";
			}

		}catch(Exception eGenerico){
			logger.error(" error ReintentarRegistroSpring: ",eGenerico);
			this.getRequest().setAttribute("descripcionError", RESOURCE_BUNDLE.getString("field.errorGenerico"));
			return "errorGenerico";
		}	
		return STRING_SUCCESS;
	}
	
	/**
	 * Actualizar estado solicitud.
	 *
	 * @param solicitudBean el solicitud bean
	 * @param registroSolicitudBean el registro solicitud bean
	 * @param persona el persona
	 */
	private void actualizarEstadoSolicitud(SolicitudBean solicitudBean, RegistroSolicitudBean registroSolicitudBean, int persona) {
		try {
			solicitudesManager.actualizarEstadoSolicitud(solicitudBean, persona);
		}catch(Exception e){
			logger.error(" error ReintentarRegistroSpring - Error actualizando estado  de la solicitud: "+solicitudBean,e);
			registroSolicitudBean.setResultado(Constantes.REGISTRO_RESULTADO_ERROR);
			registroSolicitudManager.modificarRegistroSolicitud(registroSolicitudBean);
			solicitudesManager.actualizarEstadoSolicitud(solicitudBean,solicitudBean.getIdEstadoSolicitud());
		}
	}
	
	/**
	 * Insertar log solicitud.
	 *
	 * @param logSolicitudBean el log solicitud bean
	 * @return el integer
	 */
	private Integer insertarLogSolicitud(LogSolicitudBean logSolicitudBean) {
		Integer idLog=null;
		try{
			idLog = logSolicitudManager.insertarLogSolicitud(logSolicitudBean);
		}catch(Exception e){
			logger.error(" Error ReintentarRegistroSpring - No se ha podido insertar el registro en el log"+ idLog,e);
		}
		return idLog;
	}
	
	/**
	 * Obtener hash.
	 *
	 * @param hash el hash
	 * @param byteHash el byte hash
	 * @param mensajeError el mensaje error
	 * @return el string
	 */
	private String obtenerHash(SHA0 hash, byte[] byteHash, int mensajeError) {
		String hashFile = "";
		try {
			hashFile=hash.getHash(byteHash);
		} catch (NoSuchAlgorithmException e1) {
			switch (mensajeError) {
			case 0: 
				logger.error("Error ReintentarRegistroSpring - Error recuperar firma del documento: " ,e1);
				break;
			case 1:
				logger.error("Error ReintentarRegistroSpring - Error al recuperar el documento: " ,e1);
				break;
			default:
				break;
			}			
			
		}
		return hashFile;
	}
	
	/**
	 * Comprobar telefono.
	 *
	 * @param numero el numero
	 * @return el string
	 */
	private String comprobarTelefono(String numero) {
		String telefonoAux;
		try{
			int numtelefonoAux = numero.lastIndexOf('/');
			telefonoAux = numero.substring(0,numtelefonoAux);
		}catch(Exception e){
			logger.error("Error ReintentarRegistroSpring - error numero telefono : " ,e);
			telefonoAux = numero;
		}
		return telefonoAux;
	}

	/**
	 * To registro completo.
	 *
	 * @param idRegistro el id registro
	 * @return el registro solicitud justificante bean
	 */
	private RegistroSolicitudJustificanteBean toRegistroCompleto(
			String idRegistro) {

		//Obtengo los datos de solicitud
		SolicitudComunQuery solicitudQuery = new SolicitudComunQuery();
		Long idSolicitud = Long.parseLong(idRegistro);
		solicitudQuery.setIdSolicitud(idSolicitud);
		SolicitudBean solicitud;
		solicitud = solicitudesManager.buscarSolicitudById(solicitudQuery);

		//Obtengo los datos de la convocatoria
		ConvocatoriaQuery convocatoriaQuery = new ConvocatoriaQuery();
		convocatoriaQuery.setId(solicitud.getIdConvocatoria());
		ConvocatoriasBean convocatoriaBean;
		convocatoriaBean = convocatoriasManager.buscarConvocatoriaById(convocatoriaQuery);

		//Obtengo los datos del pagoSolicitud
		PagoSolicitudQuery pagoSolicitudQuery = new PagoSolicitudQuery();
		pagoSolicitudQuery.setSolicitudComun(solicitudQuery);
		pagoSolicitudQuery.setResultado(Constantes.RESULTADO_OK);
		PagoSolicitudBean pagoSolicitud;
		pagoSolicitud = pagoSolicitudManager.buscarPagoSolicitudByIdSolicitud(pagoSolicitudQuery);

		//Obtengo los datos del registroSolicitud
		RegistroSolicitudQuery registroSolicitudQuery = new RegistroSolicitudQuery();
		registroSolicitudQuery.setSolicitudComun(solicitudQuery);
		registroSolicitudQuery.setResultado(Constantes.RESULTADO_OK);

		//Crear el bean del justificante
		RegistroSolicitudJustificanteBean aux = new RegistroSolicitudJustificanteBean();
		aux.setIdSolicitud(idRegistro);
		aux.setIdConvocatoria(convocatoriaBean.getIdConvocatoria());
		aux.setNombre(solicitud.getNombre());
		aux.setPrimerApellido(solicitud.getPrimerApellido());
		aux.setSegundoApellido(solicitud.getSegundoApellido());
		aux.setNif(solicitud.getNif());
		aux.setEmail(solicitud.getEmail());
		aux.setNumJustificante(solicitud.getNumeroSolicitud());
		aux.setEjercicio(solicitud.getEjercicio());
		aux.setMinisterio(convocatoriaBean.getMinisterio());
		aux.setFormaPago(String.valueOf(pagoSolicitud.getTipo()));
		aux.setNrc(pagoSolicitud.getNrc());
		aux.setImporte(String.valueOf(pagoSolicitud.getImporte()));

		EntidadFinanciera entidadFinanciera = pagoSolicitud.getEntidadFinanciera();
		if(entidadFinanciera!=null){
			aux.setEntidadFinanciera(entidadFinanciera.getDescripcion());
		}

		return aux;
	}

	/**
	 * Crear pdf.
	 *
	 * @param justificanteType el justificante type
	 * @param registroType el registro type
	 * @param registroSolicitudJustificanteBean el registro solicitud justificante bean
	 * @return el int
	 * @throws Exception el exception
	 */
	private int crearPdf(JustificanteType justificanteType,RegistroType registroType,
			RegistroSolicitudJustificanteBean registroSolicitudJustificanteBean) throws Exception {

		JRBeanCollectionDataSource jrBeanCollectionDataSource = null;
		File sourceFile = null;

		//Creamos los array de bytes de cada documento
		byte[] pdfasbytes = null;

		//Creamos los arrayList para los PDF's
		ArrayList <JustificanteBean> aListPdfPrueba = new ArrayList<JustificanteBean>();

		//Creamos los jasper
		Jasper jasperJustificante = new Jasper();

		//Enruta los jasper	
		String ficheroPdf = properties.getProperty("jasper.JustificanteR");

		//Creamos el bean que guarda los datos del primer jasper
		JustificanteBean justificanteBean;
		justificanteBean = toJustificanteBean(justificanteType,registroType,registroSolicitudJustificanteBean);
		
		// Logo y encabezado por defecto: 790001
		justificanteBean.setRutaLogo(properties.getProperty("jasper.rutaLogo"));
		justificanteBean.setEncabezado(Constantes.ENCABEZADO_GENERICO);

		String idMJUST = properties.getProperty("jasper.justicia.id");
		String[] idMJUSTAux = idMJUST.split(";"); 
		List<String> listaMinisterio = Arrays.asList(idMJUSTAux);
			
		if(null!=justificanteBean.getMinisterio()){
			for(int i=0; i<listaMinisterio.size(); i++){
				if (justificanteBean.getMinisterio().equals(listaMinisterio.get(i))){
					justificanteBean.setRutaLogo(properties.getProperty("jasper.rutaLogo.justicia"));
					justificanteBean.setEncabezado(Constantes.ENCABEZADO_JUSTICIA);
					break;
				}
			}				
		}
		
//		if(null != justificanteBean.getMinisterio() && justificanteBean.getMinisterio().equals(idMJUST)){
//			justificanteBean.setRutaLogo(properties.getProperty("jasper.rutaLogo.justicia"));
//			justificanteBean.setEncabezado(Constantes.ENCABEZADO_JUSTICIA);
//		}
		
		//Se añaden los bean a la lista
		aListPdfPrueba.add(justificanteBean);

		jrBeanCollectionDataSource = new JRBeanCollectionDataSource((Collection<JustificanteBean>) aListPdfPrueba);

		URL url = ResourceLocator.getInstance().getJasperTemplate(ficheroPdf);			
		String rutaFicheroJasper="";
		if(url!=null){
			rutaFicheroJasper = url.getFile();
		}
		sourceFile = new File(rutaFicheroJasper);

		// Cargo el informe compilado en el objeto jasperreport
		pdfasbytes = jasperJustificante.generarPDF(jrBeanCollectionDataSource, sourceFile, "");

		ByteArrayOutputStream pdfCompuesto = new ByteArrayOutputStream();
		PdfReader reader1 = new PdfReader(pdfasbytes);
		PdfCopyFields copy = new PdfCopyFields(pdfCompuesto);

		copy.addDocument(reader1);
		copy.close();
		
		try{
			//añadimos la firma al documento
			Signer signer = SignersFactory.getInstance().getSigner(SignatureConstants.SIGN_FORMAT_PADES);
			PrivateKeyEntry certificatePrivateKey = UtilesIPSG.getCertificatePrivateKey();
			byte [] eSignature = signer.sign(pdfCompuesto.toByteArray(), SignatureConstants.SIGN_ALGORITHM_SHA512WITHRSA, SignatureConstants.SIGN_MODE_IMPLICIT, certificatePrivateKey, null);
			
			// CSV INSIDE
			
			// generacion codigo CSV
			EeUtilService csvEeUtilService = new EeUtilServiceProxy(properties.getProperty("url.ws.csv.eeutil"));
			String codigoCSV = es.map.ipsg.util.CSVInside.generarCodigoCSV(eSignature, properties, csvEeUtilService);
			
			if (codigoCSV!=null && !"".equals(codigoCSV)) {
				logger.info("Codigo CSV generado por CSVInside: " + codigoCSV);
				
				// generacion copia CSV con codigo incrustrado
				Calendar c = Calendar.getInstance();
				Date fechaRegistro = c.getTime();
				CiudadanoBean ciudadanoSolicitud = new CiudadanoBean();
				ciudadanoSolicitud.setNif(registroSolicitudJustificanteBean.getNif());
				ciudadanoSolicitud.setNombre(registroSolicitudJustificanteBean.getNombre());
				ciudadanoSolicitud.setPrimerApellido(registroSolicitudJustificanteBean.getPrimerApellido());
				ciudadanoSolicitud.setSegundoApellido(registroSolicitudJustificanteBean.getSegundoApellido());
				
				byte[] docCsv =
						CSVInside.generarCopiaCSV(eSignature,ciudadanoSolicitud,properties,csvEeUtilService,codigoCSV,fechaRegistro);
				
				if (docCsv!=null) {
					// firma pdf
					byte [] docCsvSignature =
							signer.sign(docCsv, SignatureConstants.SIGN_ALGORITHM_SHA512WITHRSA,
									    SignatureConstants.SIGN_MODE_IMPLICIT, certificatePrivateKey, null);
					logger.info("Copia pdf con csv incrustado finalizada");
					
/*					byte[] esignaturebase64 = Base64Coder.encodeBase64(docCsvSignature);
					
					//Se añade el sello del timpo (firma PADES LTV)
					Map<String, Object> inParams = new HashMap<String, Object>();		
					inParams.put(DSSTagsRequest.CLAIMED_IDENTITY, properties.getProperty("webservices.idAplicacion"));
					inParams.put(DSSTagsRequest.IGNORE_GRACE_PERIOD, "true"); 
					inParams.put(DSSTagsRequest.RETURN_UPDATED_SIGNATURE_ATR_TYPE, "urn:afirma:dss:1.0:profile:XSS:PAdES:1.1.2:forms:LTV");
					inParams.put(DSSTagsRequest.SIGNATURE_BASE64, new String(esignaturebase64));
				
					String xmlInput = TransformersFacade.getInstance().generateXml(
							inParams, 
							GeneralConstants.DSS_AFIRMA_VERIFY_REQUEST, 
							GeneralConstants.DSS_AFIRMA_VERIFY_METHOD, 
							TransformersConstants.VERSION_10);
					
					String xmlOutput = Afirma5ServiceInvokerFacade.getInstance().invokeService(
							xmlInput, 
							GeneralConstants.DSS_AFIRMA_VERIFY_REQUEST, 
							GeneralConstants.DSS_AFIRMA_VERIFY_METHOD, 
							properties.getProperty("webservices.idAplicacion"));
					
					Map<String, Object>  docCSVFirmado = TransformersFacade.getInstance().parseResponse(
							xmlOutput, 
							GeneralConstants.DSS_AFIRMA_VERIFY_REQUEST, 
							GeneralConstants.DSS_AFIRMA_VERIFY_METHOD, 
							TransformersConstants.VERSION_10);
					
					String docCSVFirma = String.valueOf(docCSVFirmado.get(properties.getProperty("respuesta.integra.updatedSignature")));
					logger.info("Firma con sello de tiempo finalizada");	
					
					byte[] docCSVFirmaSinbase64 = Base64Coder.decodeBase64(docCSVFirma.getBytes());
*/					
					// registro justificante con csv incrustrado en tabla documentos
					String idSolicitud = registroSolicitudJustificanteBean.getIdSolicitud();
					long idDocumento = registroPdfCsvDocumentos(docCsvSignature, idSolicitud, codigoCSV);
					
					if (idDocumento>0) {
						// subida justificante a filesystem
						logger.info("Subiendo Justificante a Sistema de ficheros");
						int subida = subirDocumentoPdfCSV(docCsvSignature, idDocumento); 
						if(subida == 0){
							logger.info("error subiendo el documento con csv incrustrado al filesystem");
							this.getRequest().setAttribute(STRING_MENSAJEREGISTRO, RESOURCE_BUNDLE.getString("field.errorCsv.filesystem"));
							return 0;
						}
						logger.info("Subida PDF a Sistema de ficheros finalizada");
					} else {
						logger.info("error registrando el documento con csv incrustrado en la tabla documentos");
						this.getRequest().setAttribute(STRING_MENSAJEREGISTRO, RESOURCE_BUNDLE.getString("field.errorCsv.documento"));
						return 0;
					}
					
					// CSV STORAGE
					String guardado = CSVStorage.guardarDocumento(codigoCSV, docCsvSignature, properties);
					if (!guardado.equals(Constantes.RESULTADO_OK)){
						this.getRequest().setAttribute(STRING_MENSAJEREGISTRO, RESOURCE_BUNDLE.getString("field.errorCsv.storage"));
						return 0;
					}
				} else {
					logger.info("Error obteniendo la copia con el codigo csv incrustrado");
					this.getRequest().setAttribute(STRING_MENSAJEREGISTRO, RESOURCE_BUNDLE.getString("field.errorCsv.codigo"));
					return 0;
				}
			} else {
				logger.info("Error obteniendo el codigo csv");
				this.getRequest().setAttribute(STRING_MENSAJEREGISTRO, RESOURCE_BUNDLE.getString("field.errorCsv.copia"));
				return 0;
			}
		
				
		} catch (SigningException e){
			logger.info("Error firmando documento");
			logger.error("Error ReintentarRegistroSpring - Error firmando documento: ",e);
			return 0;
		}
		return 1;
	}
	
	
	/**
	 * Registra el pdf con csv incrustrado en la tabla documentos de bb.dd
	 *
	 * @param pdfasbytes - el contenido del fichero
	 * @param idSolicitud el id solicitud
	 * @param codigoCSV - un codigo CSV
	 * @return idDocumento - el id con el que se inserta el documento en la bb.dd
	 */
	private long registroPdfCsvDocumentos(byte[] pdfasbytes, String idSolicitud, String codigoCSV) {

		DocumentoBean docBean = new DocumentoBean(); 
		Locale locale = (Locale) this.getRequest().getSession().getAttribute(Globals.LOCALE_KEY);
		String localeName = locale.toString();
		Calendar c = Calendar.getInstance();
		int mes = c.get(Calendar.MONTH)+1;
		int fileSize = pdfasbytes.length;
		final String separador = File.separator;
		
		docBean.setNombre(Constantes.DESCRIPCIONSOLICITUDCSV);
		docBean.setFechaCreacion(c.getTime());
		
		// id tipo de documento
		docBean.setIdTipoDocumento(String.valueOf(Constantes.JUSTIFICANTE_REGISTRO_ID));
		if (localeName.equals(Constantes.CATALAN)) {
			docBean.setIdTipoDocumento(Constantes.JUSTIFICANTE_REGISTRO_ID_CATALAN);		             
		}else if (localeName.equals(Constantes.EUSKERA)) {
			docBean.setIdTipoDocumento(Constantes.JUSTIFICANTE_REGISTRO_ID_EUSKERA);	             
		}else if (localeName.equals(Constantes.GALLEGO)) {
			docBean.setIdTipoDocumento(Constantes.JUSTIFICANTE_REGISTRO_ID_GALLEGO);	             
		}else if (localeName.equals(Constantes.VALENCIANO)) {
			docBean.setIdTipoDocumento(Constantes.JUSTIFICANTE_REGISTRO_ID_VALENCIANO);
		}
		docBean.setDescripcion(Constantes.DESCRIPCIONSOLICITUDCSV + ".PDF");
		docBean.setTamano(safeLongToInt(Math.round(fileSize/1024.0)));
		docBean.setIdSolicitud(Long.parseLong(idSolicitud));
		
		// ubicacion
		StringBuilder rutaDocumento = new StringBuilder();
		rutaDocumento
				.append(c.get(Calendar.YEAR)).append(separador)
				.append(mes).append(separador)
				.append(c.get(Calendar.DAY_OF_MONTH)).append(separador)
				.append(c.get(Calendar.HOUR_OF_DAY)).append(separador)
				.append(docBean.getIdSolicitud()).append(separador);
	    docBean.setUbicacion(rutaDocumento.toString());
		docBean.setCsv(codigoCSV);
				
		long idDocumento = 0L;
		try{
			idDocumento = documentoManager.insertarDocumentoCsv(docBean);
			logger.info("Id Justificante con CSV incrustrado registrado: "+ idDocumento);
		} catch(Exception e){
			logger.info("error en la insercion", e);
			logger.error("Error ReintentarRegistroSpring - Error en la insercion: ",e);
		}
		return idDocumento;
	}
		
	/**
	 * Sube a filesystem el pdf con csv incrustrado.
	 *
	 * @param pdfasbytes - el contenido del fichero
	 * @param idDocumento - el id del documento en base de datos
	 * @return 0 si hay errores, 1 si todo ok
	 */
	private int subirDocumentoPdfCSV(byte[] pdfasbytes, long idDocumento) {
		byte[] fileData = pdfasbytes;
		
		// recupero el documentobean que voy a subir a filesystem
		DocumentoQuery documentoQuery = new DocumentoQuery();
		documentoQuery.setId(idDocumento);
		DocumentoBean docBean = documentoManager.obtenerDocumento(documentoQuery);
		
		if (docBean!=null) {
			// asigno los valores al documento bean necesarios para el metodo estatico de subida a filesystem
			docBean.setUbicacion(docBean.getUbicacion());
			docBean.setNombreAlfresco(docBean.getNombreAlfresco());
			docBean.setContenidoDocumento(fileData);
			
			// almaceno en filesystem
			SistemaFicheros ges= new SistemaFicheros();
			try{
				ges.insertarContenido(docBean, properties.getProperty("sistemaficheros.url.final"));
			} catch(Exception e) {
				logger.info("error insertando en filesystem", e);
				logger.error("Error ReintentarRegistroSpring - Error insertando en filesystem: ",e);
				try{
					documentoManager.eliminarDocumentoById(idDocumento);
				}catch(Exception o){
					logger.error("Error ReintentarRegistroSpring - Error eliminar documento: ",o);
					return 0;				
				}
				return 0;
			}
			return 1;	
		} else {
			logger.info("error insertando en filesystem");
			return 0;
		}
	}
	




	/**
	 * To justificante bean.
	 *
	 * @param justificanteType el justificante type
	 * @param registroType el registro type
	 * @param registroSolicitudJustificanteBean el registro solicitud justificante bean
	 * @return el justificante bean
	 */
	private JustificanteBean toJustificanteBean(
			JustificanteType justificanteType, RegistroType registroType,
			RegistroSolicitudJustificanteBean registroSolicitudJustificanteBean) {

		JustificanteBean aux = new JustificanteBean();

		// Datos de Registro
		if(justificanteType.getNmRegistro() != null && !"".equals(justificanteType.getNmRegistro())){
			aux.setNumRegistro(justificanteType.getNmRegistro());
		}else{
			aux.setNumRegistro("");
		}
		if(justificanteType.getFeRegistro() != null && !"".equals(justificanteType.getFeRegistro())){
			String fechaRegistro=justificanteType.getFeRegistro();	
			SimpleDateFormat formatter = new SimpleDateFormat(STRING_SIMPLEDATEFORMAT);
			SimpleDateFormat formatter1 = new SimpleDateFormat(STRING_SIMPLEDATEFORMAT);		
			SimpleDateFormat formatter2 = new SimpleDateFormat("HH:mm:ss");		

			try {	 
				Date date = formatter.parse(fechaRegistro);
				aux.setFecha(formatter1.format(date));
				aux.setHora(formatter2.format(date));
			} catch (ParseException e) {
				logger.error("Error ReintentarRegistroSpring - toJustificanteBean - parsear fecharegistro : "+fechaRegistro ,e);
			}

		}else{
			aux.setFecha("");
			aux.setHora("");
		}

		if(justificanteType.getDsOficinaOrigen() != null && !"".equals(justificanteType.getDsOficinaOrigen())){
			aux.setOficina(justificanteType.getDsOficinaOrigen());
		}else{
			aux.setOficina("");
		}

		// Buscar convocatoria
		ConvocatoriasBean convocatoriaBean;
		ConvocatoriaQuery convocatoriaQuery = new ConvocatoriaQuery();
		convocatoriaQuery.setId(Long.parseLong(registroSolicitudJustificanteBean.getIdConvocatoria()));
		convocatoriaBean = convocatoriasManager.buscarConvocatoriaById(convocatoriaQuery);

		// Buscar solicitud
		SolicitudBean solicitudBean;
		SolicitudComunQuery solicitudQuery = new SolicitudComunQuery();
		solicitudQuery.setIdSolicitud(Long.parseLong(registroSolicitudJustificanteBean.getIdSolicitud()));
		solicitudBean = solicitudesManager.buscarSolicitudById(solicitudQuery);

		// Solicitud
		if(convocatoriaBean.getMinisterio() != null && !"".equals(convocatoriaBean.getMinisterio())){
			aux.setMinisterio(convocatoriaBean.getMinisterio());
		}else{
			aux.setMinisterio("");
		}
		if(convocatoriaBean.getCentroGestor() != null && !"".equals(convocatoriaBean.getCentroGestor())){
			aux.setCentroGestor(convocatoriaBean.getCentroGestor());
		}else{
			aux.setCentroGestor("");
		}
		if(solicitudBean.getNumeroSolicitud() != null && !"".equals(solicitudBean.getNumeroSolicitud())){
			aux.setNumeroJustificante(solicitudBean.getNumeroSolicitud());
			aux.setCodigoTasa(solicitudBean.getNumeroSolicitud().substring(3, 6));
		}else{
			aux.setNumeroJustificante("");
			aux.setCodigoTasa("");
		}
		if(solicitudBean.getEjercicio() != null && !"".equals(solicitudBean.getEjercicio())){
			aux.setAnioConvocatoria1(solicitudBean.getEjercicio().substring(0, 1));
			aux.setAnioConvocatoria2(solicitudBean.getEjercicio().substring(1, 2));
			aux.setAnioConvocatoria3(solicitudBean.getEjercicio().substring(2, 3));
			aux.setAnioConvocatoria4(solicitudBean.getEjercicio().substring(3));
		}else{
			aux.setAnioConvocatoria1("");
			aux.setAnioConvocatoria2("");
			aux.setAnioConvocatoria3("");
			aux.setAnioConvocatoria4("");
		}

		// Identificacion
		if(solicitudBean.getIdConsentimiento() != null){
			if(solicitudBean.getIdConsentimiento() == true)
			{
				aux.setConsentimiento(Constantes.SI);
			}	
			else if(solicitudBean.getIdConsentimiento() == false)
			{
				aux.setConsentimiento(Constantes.NO);
			} 
		}else{
			aux.setConsentimiento("");
		}
		if(solicitudBean.getNif() != null && !"".equals(solicitudBean.getNif())){
			aux.setNif(solicitudBean.getNif());
		}else{
			aux.setNif("");
		}
		if(solicitudBean.getPrimerApellido() != null && !"".equals(solicitudBean.getPrimerApellido())){
			aux.setPrimerApellido(solicitudBean.getPrimerApellido());
		}else{
			aux.setPrimerApellido("");
		}
		if(solicitudBean.getSegundoApellido() != null && !"".equals(solicitudBean.getSegundoApellido())){
			aux.setSegundoApellido(solicitudBean.getSegundoApellido());
		}else{
			aux.setSegundoApellido("");
		}
		if(solicitudBean.getNombre() != null && !"".equals(solicitudBean.getNombre())){
			aux.setNombre(solicitudBean.getNombre());
		}else{
			aux.setNombre("");
		}
		if(solicitudBean.getFechaNacimiento() != null && !"".equals(solicitudBean.getFechaNacimiento().clone().toString())){
			SimpleDateFormat formatoFechaNac = new SimpleDateFormat("dd/MM/yyyy");
			String fechaNac = formatoFechaNac.format(solicitudBean.getFechaNacimiento());
			aux.setDiaNacimiento1(fechaNac.substring(0, 1));
			aux.setDiaNacimiento2(fechaNac.substring(1, 2));
			aux.setMesNacimiento1(fechaNac.substring(3, 4));
			aux.setMesNacimiento2(fechaNac.substring(4, 5));
			aux.setAnioNacimiento1(fechaNac.substring(8, 9));
			aux.setAnioNacimiento2(fechaNac.substring(9, 10));
		}else{
			aux.setDiaNacimiento1("");
			aux.setDiaNacimiento2("");
			aux.setMesNacimiento1("");
			aux.setMesNacimiento2("");
			aux.setAnioNacimiento1("");
			aux.setAnioNacimiento2("");
		}
		if(solicitudBean.getSexo() != ' '){
			if(Constantes.SEXO_HOMBRE.equals(solicitudBean.getSexo().toString())){
				aux.setSexoHombre("X");
				aux.setSexoMujer("");
			}else{
				aux.setSexoHombre("");
				aux.setSexoMujer("X");
			}
		}else{
			aux.setSexoHombre("");
			aux.setSexoMujer("");
		}
		if(solicitudBean.getNacionalidad() != null && !"".equals(solicitudBean.getNacionalidad())){
			aux.setNacionalidad(solicitudBean.getNacionalidad());
		}else{
			aux.setNacionalidad("");
		}
		if(solicitudBean.getEmail() != null && !"".equals(solicitudBean.getEmail())){
			aux.setCorreoElectronicos(solicitudBean.getEmail());
		}else{
			aux.setCorreoElectronicos("");
		}
		if(solicitudBean.getTelefono() != null && !"".equals(solicitudBean.getTelefono())){
			if(solicitudBean.getTelefono().length()>10){
				aux.setTelefono(solicitudBean.getTelefono().substring(0, 9));
				aux.setTelefonoAux(solicitudBean.getTelefono().substring(10));
			}else{
				aux.setTelefono(solicitudBean.getTelefono());
				aux.setTelefonoAux("");
			}
		}else{
			aux.setTelefono("");
			aux.setTelefonoAux("");
		}

		if(solicitudBean.getCalleDomicilio() != null && !"".equals(solicitudBean.getCalleDomicilio())){
			aux.setCalleDomicilio(solicitudBean.getCalleDomicilio());
		}else{
			aux.setCalleDomicilio("");
		}
		if(solicitudBean.getCodigoPostalDomicilio() != null && !"".equals(solicitudBean.getCodigoPostalDomicilio())){
			aux.setCodigoPostalDomicilio(solicitudBean.getCodigoPostalDomicilio());
		}else{
			aux.setCodigoPostalDomicilio("");
		}
		if(solicitudBean.getMunicipioDomicilio() != null && !"".equals(solicitudBean.getMunicipioDomicilio())){
			aux.setMunicipioDomicilio(solicitudBean.getMunicipioDomicilio());
		}else{
			aux.setMunicipioDomicilio("");
		}
		if(solicitudBean.getDescripcionIdProvinciaDomicilio() != null && !"".equals(solicitudBean.getDescripcionIdProvinciaDomicilio())){
			aux.setProvinciaDomicilio(solicitudBean.getDescripcionIdProvinciaDomicilio());
		}else{
			aux.setProvinciaDomicilio("");
		}
		if(solicitudBean.getNacionPaisDomicilio() != null && !"".equals(solicitudBean.getNacionPaisDomicilio())){
			aux.setPais(solicitudBean.getNacionPaisDomicilio());
		}else{
			aux.setPais("");
		}

		// AutoLiquidacion
		if(convocatoriaBean.getCuerpoEscala() != null && !"".equals(convocatoriaBean.getCuerpoEscala())){
			aux.setCuerpoEscala(convocatoriaBean.getCuerpoEscala());
		}else{
			aux.setCuerpoEscala("");
		}
		if(solicitudBean.getDescripcionEspecialidad() != null && !"".equals(solicitudBean.getDescripcionEspecialidad())){
			aux.setEspecialidad(solicitudBean.getDescripcionEspecialidad());
		}else{
			aux.setEspecialidad("");
		}
		if(convocatoriaBean.getFormaAcceso() != null && !"".equals(convocatoriaBean.getFormaAcceso())){
			aux.setFormaAcceso(convocatoriaBean.getFormaAcceso());
		}else{
			aux.setFormaAcceso("");
		}
		//Ministerio Convocatoria
		if(convocatoriaBean.getMinisterioConvocatoria() != null && !"".equals(convocatoriaBean.getMinisterioConvocatoria())){
			aux.setMinisterioConvocatoria(convocatoriaBean.getMinisterioConvocatoria());
		}else{
			if(convocatoriaBean.getMinisterio() != null && !"".equals(convocatoriaBean.getMinisterio())){
				aux.setMinisterioConvocatoria(convocatoriaBean.getMinisterio());
			}else{
			aux.setMinisterioConvocatoria("");
			}
		}
			
		if(convocatoriaBean.getFechaBoe() != null && !"".equals(convocatoriaBean.getFechaBoe())){
			String fechaBoe = convocatoriaBean.getFechaBoe();
			aux.setDiaFechaBoe1(fechaBoe.substring(0, 1));
			aux.setDiaFechaBoe2(fechaBoe.substring(1, 2));
			aux.setMesFechaBoe1(fechaBoe.substring(3, 4));
			aux.setMesFechaBoe2(fechaBoe.substring(4, 5));
			aux.setAnioFechaBoe1(fechaBoe.substring(8, 9));
			aux.setAnioFechaBoe2(fechaBoe.substring(9, 10));
		}else{
			aux.setDiaFechaBoe1("");
			aux.setDiaFechaBoe2("");
			aux.setMesFechaBoe1("");
			aux.setMesFechaBoe2("");
			aux.setAnioFechaBoe1("");
			aux.setAnioFechaBoe1("");
		}
		if(solicitudBean.getDescripcionIdProvinciaExamen() != null && !"".equals(solicitudBean.getDescripcionIdProvinciaExamen())){
			aux.setProvinciaExamen(solicitudBean.getDescripcionIdProvinciaExamen());
		}else{
			aux.setProvinciaExamen("");
		}
		if(solicitudBean.getPorcentajeDiscapacidad() != null && !"".equals(solicitudBean.getPorcentajeDiscapacidad().toString())){
			aux.setPorcentajeDiscapacidad(String.valueOf(solicitudBean.getPorcentajeDiscapacidad()));
		}else{
			aux.setPorcentajeDiscapacidad("");
		}
		if(solicitudBean.getReservaDiscapacidad() != null && solicitudBean.getReservaDiscapacidad() != ' ' ){
			aux.setTipoDiscapacidad(solicitudBean.getReservaDiscapacidad()+"");
		}else{
			aux.setTipoDiscapacidad("");
		}
		if(solicitudBean.getDetalleDiscapacidad() != null && !"".equals(solicitudBean.getDetalleDiscapacidad())){
			aux.setDetalleDiscapacidad(solicitudBean.getDetalleDiscapacidad());
		}else{
			aux.setDetalleDiscapacidad("");
		}
		
		if(null != solicitudBean.getDescripcionTipoDiscapacidad()){
			aux.setDetalleDiscapacidad(solicitudBean.getDescripcionTipoDiscapacidad());
		}
		
		if(solicitudBean.getDescripcionTituloOficial() != null && !"".equals(solicitudBean.getDescripcionTituloOficial())){
			aux.setTituloOficial(solicitudBean.getDescripcionTituloOficial());
		}else{
			aux.setTituloOficial("");
		}
		if(solicitudBean.getOtrosTitulos() != null && !"".equals(solicitudBean.getOtrosTitulos())){
			aux.setOtrosTitulos(solicitudBean.getOtrosTitulos());
		}else{
			aux.setOtrosTitulos("");
		}

		// Campos propios
		SolicitudComunQuery solicitudComunQuery = new SolicitudComunQuery();
		solicitudComunQuery.setIdSolicitud(solicitudBean.getId());
		SolicitudPropiosQuery solicitudPropiosQuery = new SolicitudPropiosQuery();
		solicitudPropiosQuery.setSolicitudComun((solicitudComunQuery));
		ArrayList<SolicitudPropiosBean> listaSolicitudPropios = solicitudesPropiosManager.obtenerCamposPropiosByIdSolicitud(solicitudPropiosQuery);

		ArrayList<CamposPropiosBean> listaCamposPropios = new ArrayList<CamposPropiosBean>();

		for (SolicitudPropiosBean solicitudPropiosBean : listaSolicitudPropios) {
			CamposPropiosBean camposPropiosBean = new CamposPropiosBean();
			camposPropiosBean.setCampo(solicitudPropiosBean.getCamposPropiosBean().getCampo());
			camposPropiosBean.setValorVista(solicitudPropiosBean.getValor());
			listaCamposPropios.add(camposPropiosBean);
		}

		aux.setListaCamposPropios(listaCamposPropios);

		// Documentación asociada
		ArrayList<DocumentoBean>listaDocumentos = new ArrayList<DocumentoBean>();

		SolicitudComunQuery comunQuery = new SolicitudComunQuery();
		comunQuery.setIdSolicitud(solicitudBean.getId());

		DocumentoQuery documentosQuery = new DocumentoQuery();
		documentosQuery.setSolicitudComun(comunQuery);

		TipoDocumentoQuery tipoDocumentoQuery = new TipoDocumentoQuery();
		tipoDocumentoQuery.addIdIn(Constantes.TIPO_DOCUMENTO_ANEXO_SOLICITUD);
		tipoDocumentoQuery.addIdIn(Constantes.TIPO_DOCUMENTO_JUSTIFICANTE_REDUCCION_TASA);
		tipoDocumentoQuery.addIdIn(Constantes.TIPO_DOCUMENTO_ADJUNTO);
		tipoDocumentoQuery.addIdIn(Constantes.TIPO_DOCUMENTO_DISCAPACIDAD);
		tipoDocumentoQuery.addIdIn(Constantes.TIPO_DOCUMENTO_FORMULARIO);
		documentosQuery.setTipoDocumento(tipoDocumentoQuery);

		ArrayList<DocumentoBean>listaDocumentosAux = documentoManager.buscarDocumentosByIdSolicitud(documentosQuery);

		// Si no hay documentos anexos, generamos uno vacío para mejorar la apariencia del pdf.
		if(listaDocumentosAux.isEmpty() || (aux.getListaDocumentos()==null)){
			DocumentoBean docAnexo = new DocumentoBean();
			docAnexo.setNombre(Constantes.SIN_DOCUMENTOS_ANEXOS);
			docAnexo.setHashExtracto(Constantes.SIN_DOCUMENTOS_ANEXOS);
			listaDocumentos.add(docAnexo);
		}else{
			for(int i=0; i<listaDocumentosAux.size(); i++){		
				// Formulario html
				if(listaDocumentosAux.get(i).getExtension().endsWith(Constantes.EXTENSION_FORMULARIO)){
					aux.setHtmlRegistrado(listaDocumentosAux.get(i).getNombreAlfresco());
					aux.setHtmlHash(listaDocumentosAux.get(i).getHashExtracto());
					// Docs anexos
				}else{
					DocumentoBean docAnexo = new DocumentoBean();
					docAnexo.setNombre(listaDocumentosAux.get(i).getNombre());
					docAnexo.setHashExtracto(listaDocumentosAux.get(i).getHashExtracto());
					listaDocumentos.add(docAnexo);
				}
			}
		}

		aux.setListaDocumentos(listaDocumentos);

		// Datos del pago
		if(registroSolicitudJustificanteBean.getImporte()!=null && !"".equals(registroSolicitudJustificanteBean.getImporte())){
			aux.setImporte(registroSolicitudJustificanteBean.getImporte());
		}else{
			aux.setImporte("");
		}
		if(registroSolicitudJustificanteBean.getTipoPago()!=null && !"".equals(registroSolicitudJustificanteBean.getTipoPago())){
			if(registroSolicitudJustificanteBean.getFormaPago().equals("C")){
				aux.setTipoPago(Constantes.PAGO_TIPO_ADEUDO);
			}
			else if(registroSolicitudJustificanteBean.getFormaPago().equals("T")){
				aux.setTipoPago(Constantes.PAGO_TIPO_TARJETA);
			}
			else if(registroSolicitudJustificanteBean.getFormaPago().equals("E")){
				aux.setTipoPago(Constantes.PAGO_TIPO_EXENTO);
			}else{
				aux.setTipoPago("");
			}
		}else{
			aux.setTipoPago("");
		}
		if(registroSolicitudJustificanteBean.getMotivoReduccionDes()!=null && !"".equals(registroSolicitudJustificanteBean.getMotivoReduccionDes())){
			aux.setCausaExencion(registroSolicitudJustificanteBean.getMotivoReduccionDes());
		}else{
			aux.setCausaExencion("");
		}
		if(registroSolicitudJustificanteBean.getFechaPago()!=null && !"".equalsIgnoreCase(registroSolicitudJustificanteBean.getFechaPago())){
			aux.setFechaPago(registroSolicitudJustificanteBean.getFechaPago());
		}else{
			aux.setFechaPago(Constantes.NO_APLICA);
		}
		if(registroSolicitudJustificanteBean.getNrc()!=null && !"".equals(registroSolicitudJustificanteBean.getNrc())){
			aux.setNrc(registroSolicitudJustificanteBean.getNrc());
		}else{
			aux.setNrc(Constantes.NO_APLICA);
		}
		if(registroSolicitudJustificanteBean.getEntidadFinanciera()!=null && !"".equals(registroSolicitudJustificanteBean.getEntidadFinanciera())){
			aux.setEntidad(registroSolicitudJustificanteBean.getEntidadFinanciera());
		}else{
			aux.setEntidad(Constantes.NO_APLICA);
		}

		if(null != solicitudBean.getNumModelo()){
			aux.setModelo(solicitudBean.getNumModelo());
		}
		
		return aux;
	}

	/**
	 * Recuperar documentos solicitud.
	 *
	 * @param idSolicitud el id solicitud
	 * @return el array list
	 */
	private ArrayList<DocumentoBean> recuperarDocumentosSolicitud(String idSolicitud){
		// Obtener todos los documentos asociados a la solicitud
		SolicitudComunQuery solicitudComunQuery = new SolicitudComunQuery();
		solicitudComunQuery.setIdSolicitud(Long.parseLong(idSolicitud));
		DocumentoQuery documentoQuery = new DocumentoQuery();
		documentoQuery.setSolicitudComun(solicitudComunQuery);
		ArrayList<DocumentoBean> listaDocumentos = documentoManager.buscarDocumentoCombo(documentoQuery); 
		
		return listaDocumentos;
	}
	
	/**
	 * Safe long to int.
	 *
	 * @param l el l
	 * @return el int
	 */
	public static int safeLongToInt(long l) {
		if (l < Integer.MIN_VALUE || l > Integer.MAX_VALUE) {
			throw new IllegalArgumentException
			(l + " cannot be cast to int without changing its value.");
		}
		return (int) l;
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
	 * Obtiene el documento manager.
	 *
	 * @return el documento manager
	 */
	public DocumentoManager getDocumentoManager() {
		return documentoManager;
	}

	/**
	 * Establece el documento manager.
	 *
	 * @param documentoManager el nuevo documento manager
	 */
	public void setDocumentoManager(DocumentoManager documentoManager) {
		this.documentoManager = documentoManager;
	}

	/**
	 * Obtiene el registro solicitud manager.
	 *
	 * @return el registro solicitud manager
	 */
	public RegistroSolicitudManager getRegistroSolicitudManager() {
		return registroSolicitudManager;
	}

	/**
	 * Establece el registro solicitud manager.
	 *
	 * @param registroSolicitudManager el nuevo registro solicitud manager
	 */
	public void setRegistroSolicitudManager(
			RegistroSolicitudManager registroSolicitudManager) {
		this.registroSolicitudManager = registroSolicitudManager;
	}

	/**
	 * Obtiene el log solicitud manager.
	 *
	 * @return el log solicitud manager
	 */
	public LogSolicitudManager getLogSolicitudManager() {
		return logSolicitudManager;
	}

	/**
	 * Establece el log solicitud manager.
	 *
	 * @param logSolicitudManager el nuevo log solicitud manager
	 */
	public void setLogSolicitudManager(LogSolicitudManager logSolicitudManager) {
		this.logSolicitudManager = logSolicitudManager;
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

}
