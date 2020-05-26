package es.map.ipsg.action.solicitud;

import java.io.IOException;
import java.util.Calendar;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringMessage;
import es.map.ips.common.spring.SpringMessages;
import es.map.ips.model.ConvocatoriaQuery;
import es.map.ips.model.DocumentoQuery;
import es.map.ips.model.EntidadFinanciera;
import es.map.ips.model.PagoSolicitudQuery;
import es.map.ips.model.RegistroSolicitudQuery;
import es.map.ips.model.SolicitudComunQuery;
import es.map.ips.model.TipoDocumentoQuery;
import es.map.ipsg.bean.ConvocatoriasBean;
import es.map.ipsg.bean.DocumentoBean;
import es.map.ipsg.bean.PagoSolicitudBean;
import es.map.ipsg.bean.RegistroSolicitudBean;
import es.map.ipsg.bean.RegistroSolicitudJustificanteBean;
import es.map.ipsg.bean.SolicitudBean;
import es.map.ipsg.clienteWS.buscarRegistroRec3.BuscarRegistrosElectronicosServiceLocator;
import es.map.ipsg.clienteWS.buscarRegistroRec3.BuscarRegistrosElectronicosServiceSoapBindingStub;
import es.map.ipsg.clienteWS.buscarRegistroRec3.CriteriosBusquedaType;
import es.map.ipsg.clienteWS.registroRec3.type.AutenticacionType;
import es.map.ipsg.clienteWS.registroRec3.type.JustificanteType;
import es.map.ipsg.clienteWS.registroRec3.type.RespuestaType;
import es.map.ipsg.form.CrearDocumentoForm;
import es.map.ipsg.manager.ConvocatoriasManager;
import es.map.ipsg.manager.DocumentoManager;
import es.map.ipsg.manager.LogSolicitudManager;
import es.map.ipsg.manager.PagoSolicitudManager;
import es.map.ipsg.manager.RegistroSolicitudManager;
import es.map.ipsg.manager.SolicitudesManager;
import es.map.ipsg.manager.UsuarioManager;
import es.map.ipsg.util.Constantes;
import sun.misc.BASE64Decoder;

/**
 * El Class GenerarDocumentoPdfRecSpring.
 */
public class GenerarDocumentoPdfRecSpring extends AbstractSpring {

	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(GenerarDocumentoPdfSpring.class);
	
	/** La constante BUNDLE_ERROR. */
	private static final String BUNDLE_ERROR = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_ERROR);
	
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
	
	/** La constante STRING_SUCCESS. */
	private static final String STRING_SUCCESS = "success";

	/**
	 * Crea una nueva generar documento pdf rec spring.
	 */
	public GenerarDocumentoPdfRecSpring() {
		try {				
			setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
			setSolicitudesManager((SolicitudesManager) getBean("solicitudesManager"));
			setDocumentoManager((DocumentoManager) getBean("documentoManager"));
			setRegistroSolicitudManager((RegistroSolicitudManager) getBean("registroSolicitudManager"));
			setLogSolicitudManager((LogSolicitudManager) getBean("logSolicitudManager"));
			setPagoSolicitudManager((PagoSolicitudManager) getBean("pagoSolicitudManager"));
			setConvocatoriasManager((ConvocatoriasManager) getBean("convocatoriaManager"));
			properties = (Properties) getBean("propertiesBean");
		} catch (Exception e) {
			logger.error("Error GenerarDocumentoPdfRecSpring:",e);
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	protected String doExecute(SpringForm form) throws Exception {

		try{	
			CrearDocumentoForm formulario = (CrearDocumentoForm)form;
			String idRegistro = this.getRequest().getParameter("idRegistro");
			String idSolicitud = idRegistro;

			logger.info("Entra en la generacion del justificante");

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
			documentoManager.buscarDocumentosByIdSolicitud(documentoQuery);

			// Se busca el registro anterior de la solicitud
			RegistroSolicitudQuery registroSolicitudQuery = new RegistroSolicitudQuery();
			SolicitudComunQuery solicitud = new SolicitudComunQuery();
			solicitud.setIdSolicitud(Long.parseLong(idRegistro));

			// Obtenemos el consentimiento a través del idSolicitud
			SolicitudBean solBean = solicitudesManager.buscarSolicitudById(solicitud);
			long idConvocatoria=solBean.getIdConvocatoria();
			registroSolicitudQuery.setSolicitudComun(solicitud);

			// Datos de cabecera
			registroSolicitudQuery.setResultado(Constantes.RESULTADO_OK);
			RegistroSolicitudBean regSolicitud = registroSolicitudManager.buscarRegistroSolicitudByIdSolicitudCrearJustificante(registroSolicitudQuery);

			String numRegistro=regSolicitud.getNumeroRegistro();

			try {		
				//Llamada al WebService
				//Criterios de busqueda necesarios para el WS

				CriteriosBusquedaType criterios = new CriteriosBusquedaType();
				criterios.setCdAsunto(properties.getProperty("dsAsunto"));;
				criterios.setCdOficinaOrigen(properties.getProperty("cdOrOrigen"));
				criterios.setNmRegistro(numRegistro);
				logger.info("Registro Buscado:" + numRegistro);

				String idAplicacion = properties.getProperty("idAplicacion");
				String password = properties.getProperty("password");

				AutenticacionType autentication = new AutenticacionType();
				autentication.setIdAplicacion(idAplicacion);
				autentication.setPassword(password);

				BuscarRegistrosElectronicosServiceLocator locator = new BuscarRegistrosElectronicosServiceLocator();
				locator.setBuscarRegistrosElectronicosServiceEndpointAddress(properties.getProperty("conf.url_buscarRegistrosElectronicos"));

				BuscarRegistrosElectronicosServiceSoapBindingStub binding = (BuscarRegistrosElectronicosServiceSoapBindingStub) locator.getBuscarRegistrosElectronicosService();
				binding.setTimeout(TIMEOUT);

				JustificanteType[] resultado = binding.buscarRegistro(autentication, criterios);
				RespuestaType respuestaType=resultado[0].getRespuesta();

				RegistroSolicitudJustificanteBean registroSolicitudJustificante;
				registroSolicitudJustificante = toRegistroCompleto(idRegistro);

				// Sin error
				if("00".equals(respuestaType.getCdRespuesta())){
					String encodedBytes = resultado[0].getBlJustificante();//Justificante de registro codificado
					BASE64Decoder decoder = new BASE64Decoder();
					if(encodedBytes!=null){
						byte[] decodedBytes = decoder.decodeBuffer(encodedBytes);
						subirDocumentoPdf(decodedBytes,idSolicitud,idConvocatoria);

						// Guardo el array de bytes en el formulario
						formulario.setArrBytes(decodedBytes);
						formulario.setRegistroSolicitudJustificanteBean(registroSolicitudJustificante);
						formulario.setTipoJustificante("registro");
					}
				}

				// Error
				if("01".equals(respuestaType.getCdRespuesta())){
					logger.error("justificanteType.isExisteError() is true");
					if(null!=respuestaType.getDsRespuesta()){	
						logger.error("Mensaje de error: "+respuestaType.getDsRespuesta());
					}
					this.setRequestAttribute("errorGeneracion",RESOURCE_BUNDLE.getString("field.errorGeneracionREC"));
				}


			}catch (Exception e) {
				logger.error("Error GenerarDocumentoPdfRecSpring - Error en llamada a WebService buscarRegistrosElectronicos:",e);
				SpringMessages errors = new SpringMessages();					
				SpringMessage error = new SpringMessage("field.consultaRec.errores.falloWS");
				errors.add("errorCodigo",error);
				saveErrors(this.getRequest().getSession(),errors);
				return STRING_SUCCESS;
			}	

			this.setRequestAttribute("idSolicitud",idRegistro );
			this.setRequestAttribute("mensajeRegistro", "Se ha generado de nuevo el justificante");

		}catch(Exception eGenerico){
			logger.error("Error GenerarDocumentoPdfRecSpring :",eGenerico);
			this.getRequest().setAttribute("descripcionError", RESOURCE_BUNDLE.getString("field.errorGenerico"));
			return "errorGenerico";
		}	
		logger.info("Sale de la generacion del justificante- success");
		return STRING_SUCCESS;
	}

	/**
	 * Subir documento pdf.
	 *
	 * @param pdfasbytes el pdfasbytes
	 * @param idSolicitud el id solicitud
	 * @param idConvocatoria el id convocatoria
	 * @return el int
	 */
	private int subirDocumentoPdf(byte[] pdfasbytes,String idSolicitud,long idConvocatoria) {

		String fileName = "";
		String contentType="JustificanteRegistroRecPdf";
		logger.info("Entra Rec PDF");
		fileName = "/"+contentType+".pdf";
		byte[] fileData = pdfasbytes;
		int fileSize = fileData.length;

		DocumentoBean docBean= new DocumentoBean();
		//Asigno los valores al bean
		docBean.setContenidoDocumento(fileData);		        

		int numAux = fileName.lastIndexOf('/');
		int numExten = fileName.lastIndexOf('.');
		String nombreAux = fileName.substring(numAux+1,numExten);	        

		String extension=fileName.substring(numExten+1);
		docBean.setExtension(extension);
		docBean.setTamano(safeLongToInt(Math.round(fileSize/1024.0)));
		docBean.setNombre(nombreAux);
		docBean.setDescripcion(contentType);

		Calendar c = Calendar.getInstance();
		docBean.setFechaCreacion(c.getTime());

		DocumentoQuery documentoAuxConsulta = new DocumentoQuery();
		SolicitudComunQuery solicitudAux = new SolicitudComunQuery();
		solicitudAux.setIdSolicitud(Long.parseLong(idSolicitud));
		documentoAuxConsulta.setSolicitudComun(solicitudAux);       
		docBean.setDsNombreDocumento(nombreAux);
		docBean.setIdSolicitud(Long.parseLong(idSolicitud));
		docBean.setIdConvocatoria(idConvocatoria);
		docBean.setEntorno(Constantes.ENTORNO_SOLICITUDES);
		docBean.setIdTipoDocumento(Integer.toString(Constantes.TIPO_DOCUMENTO_JUSTIFICANTE_REGISTRO_PDF));     

		try{
			documentoManager.insertarDocumentoRec(docBean, properties.getProperty("sistemaficheros.url.final"));		        		
		}catch(Exception e){
			logger.error("Error GenerarDocumentoPdfRecSpring - subirDocumentoPdf:",e);   			
			this.getRequest().setAttribute("errorDescripcion",RESOURCE_BUNDLE.getString("certificados.error.gestorDocumental"));
			return 0;
		}
		return 1;
	}

	/**
	 * Enviar salida.
	 *
	 * @param doc el doc
	 * @param salida el salida
	 */
	public void enviarSalida(DocumentoBean doc, byte[] salida) {
		String idAlfresco = doc.getNombreAlfresco();
		javax.servlet.ServletOutputStream stream;
		try {
			stream = this.getResponse().getOutputStream();
			String contentDisposition = "attachment; filename=\"" + idAlfresco
					+ "\"";

			HttpServletResponse resp = this.getResponse();
			resp.setContentType("application/octet-stream");
			resp.setHeader("Content-Disposition", contentDisposition);
			IOUtils.write(salida,stream);
		} catch (IOException e) {
			logger.error("Error GenerarDocumentoPdfRecSpring - enviarSalida:",e);
		}

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

		//Crear el bean del justificante
		RegistroSolicitudJustificanteBean aux = new RegistroSolicitudJustificanteBean();
		aux.setIdSolicitud(idRegistro);
		aux.setIdConvocatoria(convocatoriaBean.getIdConvocatoria());
		aux.setNombre(solicitud.getNombre());
		aux.setNif(solicitud.getNif());
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
}
