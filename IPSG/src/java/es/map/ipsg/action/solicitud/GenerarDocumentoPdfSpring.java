package es.map.ipsg.action.solicitud;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ipsg.bean.DocumentoBean;
import es.map.ipsg.bean.GenerarJustificanteBean;
import es.map.ipsg.clienteWS.registroRec3.RegistroType;
import es.map.ipsg.clienteWS.registroRec3.type.DocumentoType;
import es.map.ipsg.clienteWS.registroRec3.type.InteresadoType;
import es.map.ipsg.clienteWS.registroRec3.type.JustificanteType;
import es.map.ipsg.form.CrearDocumentoForm;
import es.map.ipsg.manager.ConvocatoriasManager;
import es.map.ipsg.manager.DocumentoManager;
import es.map.ipsg.manager.LogSolicitudManager;
import es.map.ipsg.manager.PagoSolicitudManager;
import es.map.ipsg.manager.RegistroSolicitudManager;
import es.map.ipsg.manager.SolicitudesManager;
import es.map.ipsg.manager.SolicitudesPropiosManager;
import es.map.ipsg.manager.UsuarioManager;
import es.map.ipsg.util.UtilesIPSG;
import es.map.ipsg.util.VelocityGenerator;

/**
 * El Class GenerarDocumentoPdfSpring.
 */
public class GenerarDocumentoPdfSpring extends AbstractSpring {

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
	
	/** el solicitudes propios manager. */
	private SolicitudesPropiosManager solicitudesPropiosManager;
	
	/** La constante STRING_ERRORGENERARDOCUMENTOPDF_DOCUMENTOXML. */
	private static final String STRING_ERRORGENERARDOCUMENTOPDF_DOCUMENTOXML = " Error GenerarDocumentoPdfSpring - documentoXML: ";
	
	/** La constante STRING_ERRORDOCUMENTO. */
	private static final String STRING_ERRORDOCUMENTO = "errorDocumento";
	

	/**
	 * Crea una nueva generar documento pdf spring.
	 */
	public GenerarDocumentoPdfSpring() {
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
			logger.error("Error GenerarDocumentoPdfSpring:",e);
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	protected String doExecute(SpringForm form) throws Exception {

		logger.info("Entra en la generacion del justificante");
		
		try{	
			CrearDocumentoForm formulario = (CrearDocumentoForm)form;
			String idRegistro = this.getRequest().getParameter("idRegistro");
			String tipoJustificante = this.getRequest().getParameter("accion");
			
			GenerarJustificanteBean generarJustificanteBean = documentoManager.generarJustificante(idRegistro);
			if (generarJustificanteBean.getMsgError() != null && !("").equals(generarJustificanteBean.getMsgError())) {
				this.getRequest().setAttribute("errorGeneracion", RESOURCE_BUNDLE.getString(generarJustificanteBean.getMsgError()));
				return "errorGenerico";
			}
			
			tipoJustificante="Pdf";

			/*-------------------------FIN CREAR PDF----------------------------------------*/
			if(tipoJustificante.equals("Pdf")){		
				// guardo el array de bytes en el formulario
				formulario.setArrBytes(generarJustificanteBean.getDocCSVFirmaSinbase64());
				formulario.setRegistroSolicitudJustificanteBean(generarJustificanteBean.getRegistroSolicitudJustificante());
				formulario.setTipoJustificante("solicitud");
			}else if(tipoJustificante.equals("Xml")){
				/*-----------------------------SE CREA EL XML---------------------------------------*/
				String justifString = crearJustificanteString(generarJustificanteBean.getJustificanteType(), generarJustificanteBean.getRegistroType(),generarJustificanteBean.getConsentimiento());
				logger.info(justifString);
				Document documentoXML = null;
				try {
					documentoXML = UtilesIPSG.stringToDom(justifString);
				} catch (SAXException e) {  	
					logger.error(STRING_ERRORGENERARDOCUMENTOPDF_DOCUMENTOXML,e);
					return STRING_ERRORDOCUMENTO;
				} catch (ParserConfigurationException e) {
					logger.error(STRING_ERRORGENERARDOCUMENTOPDF_DOCUMENTOXML,e);
					return STRING_ERRORDOCUMENTO;
				} catch (IOException e) {
					logger.error(STRING_ERRORGENERARDOCUMENTOPDF_DOCUMENTOXML,e);
					return STRING_ERRORDOCUMENTO;
				} catch (Exception e){
					logger.error(STRING_ERRORGENERARDOCUMENTOPDF_DOCUMENTOXML,e);
					return STRING_ERRORDOCUMENTO;
				}

				byte[] fileData = decodeBase64(justifString);
				
				// guardo el array de bytes en el formulario
				formulario.setArrBytes(fileData);
				formulario.setRegistroSolicitudJustificanteBean(generarJustificanteBean.getRegistroSolicitudJustificante());
				formulario.setDocumentoXML(documentoXML);
				formulario.setTipoJustificante("Xml");

				/*-------------------------FIN CREAR XML----------------------------------------*/
			}	

			this.setRequestAttribute("idSolicitud",idRegistro );
			this.setRequestAttribute("mensajeRegistro", "Se ha generado de nuevo el justificante");

		}catch(Exception eGenerico){
			eGenerico.printStackTrace();
			logger.error("Error: "+ eGenerico);
			this.getRequest().setAttribute("descripcionError", RESOURCE_BUNDLE.getString("field.errorGenerico"));
			return "errorGenerico";
		}	
		logger.info("Sale de la generacion del justificante- success");
		
		return "success";
	}

	/**
	 * Decode base 64.
	 *
	 * @param cadena el cadena
	 * @return el byte[]
	 */
	private byte[] decodeBase64(String cadena) {
		byte[] resultado = null;
		try {
			resultado = UtilesIPSG.decodeBase64(cadena);
		} catch (Exception e1) {			
			logger.error(" Error GenerarDocumentoPdfSpring - filedata: ",e1);			
		}
		return resultado;
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
			logger.error(" Error GenerarDocumentoPdfSpring - enviarSalida: ",e);
		}
	}

	
	/**
	 * Crear justificante string.
	 *
	 * @param justificante el justificante
	 * @param registroType el registro type
	 * @param consentimiento el consentimiento
	 * @return el string
	 */
	private String crearJustificanteString(JustificanteType justificante,
			RegistroType registroType,String consentimiento) {
		
		Map<String,Object> result = new HashMap<String,Object>();

		//Datos justificante
		result.put("cdOficinaOrigen", isNull(justificante.getCdOficinaOrigen()));
		result.put("cdOrDestino", isNull(justificante.getCdOficinaDestino()));
		result.put("cdTipoAsunto", isNull(justificante.getDsAsunto()));
		result.put("dsAsunto", isNull(justificante.getDsAsunto()));
		result.put("dsOrDestino", isNull(justificante.getDsOficinaDestino()));
		result.put("dsOrOrigen", isNull(justificante.getDsOficinaOrigen()));
		result.put("numRegistro", isNull(justificante.getNmRegistro()));
		result.put("dsFirma", isNull(justificante.getBlJustificante()));

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String strdatePresent = "";
		
		if(justificante.getFeRegistro() != null && !"".equals(justificante.getFeRegistro())){
			strdatePresent = sdf.format(justificante.getFeRegistro());
		}
		result.put("feEntrada", strdatePresent);

		//Solicitud
		result.put("solicitud_cdAsunto", isNull(registroType.getCdAsunto()));
		result.put("solicitud_cdOrOrigen", isNull(registroType.getCdOficinaOrigen()));
		result.put("solicitud_cdTipoTransporte", isNull(registroType.getCdTipoTransporte()));
		result.put("solicitud_cdUgDestino", isNull(registroType.getCdUnidadDestino()));
		result.put("solicitud_cdUgOrigen", isNull(registroType.getCdUnidadOrigen()));
		result.put("solicitud_usuario", isNull(registroType.getTlNombreUsuario()));

		if(consentimiento != null){
			result.put("solicitud_consentimiento", consentimiento);			
		}else{
			result.put("solicitud_consentimiento", "");
		}

		// interesados
		InteresadoType[] interesados = registroType.getInteresados();
		result.put("interesados", createInteresados(interesados));

		// documentos
		DocumentoType[] anexos = registroType.getDocumentos();
		result.put("ficheros", createFicheros(anexos));
		result.put("firmaApunte", "");

		// creamos el XML la primera vez y lo pasamos al procedimiento generar firma del apunte
		String strDocument = VelocityGenerator.generateContent("firmaRegistroIPS", result);
		
		return strDocument;
	}

	/**
	 * Crea el interesados.
	 *
	 * @param interesados el interesados
	 * @return el list
	 */
	private List<Map<String,String>> createInteresados(InteresadoType[] interesados) {
		List<Map<String,String>> result = new ArrayList<Map<String,String>>();
		if(interesados != null && interesados.length>0){
			for (int i = 0; i < interesados.length; i++) {
				Map<String,String> map = new HashMap<String,String>();
				map.put("interesado_cdTipoIdentificador", isNull(interesados[i].getCdTipoDocIndentificativoInteresado()));
				map.put("interesado_dsApellido1", isNull(interesados[i].getTlApellido1Interesado()));
				map.put("interesado_dsApellido2", isNull(interesados[i].getTlApellido2Interesado()));
				map.put("interesado_dsCodigoPostal", isNull(interesados[i].getCdCodigoPostalInteresado()));
				map.put("interesado_dsCorreoElectronico", isNull(interesados[i].getTlCorreoElectronicoInteresado()));
				map.put("interesado_dsLocalidad", isNull(interesados[i].getCdMunicipioInteresado()));
				map.put("interesado_dsNombre", isNull(interesados[i].getTlNombreInteresado()));
				map.put("interesado_dsNombreVia", isNull(interesados[i].getTlDireccionInteresado()));
				map.put("interesado_dsNumIdentificador", isNull(interesados[i].getTlNumDocIdentificativoInteresado()));
				map.put("interesado_dsProvincia", isNull(interesados[i].getCdProvinciaInteresado()));
				map.put("interesado_dsRazonSocial", isNull(interesados[i].getTlRazonSocialInteresado()));
				map.put("interesado_dsTelefonoContacto", isNull(interesados[i].getTlTelefonoContactoInteresado()));

				result.add(map);
			}
		}
		return result;
	}

	/**
	 * Crea el ficheros.
	 *
	 * @param anexos el anexos
	 * @return el list
	 */
	private List<Map<String,String>> createFicheros(DocumentoType[] anexos) {
		List<Map<String,String>> result = new ArrayList<Map<String,String>>();

		if(anexos != null && anexos.length>0){
			for (int i = 0; i < anexos.length; i++) {
				if(anexos[i] != null)
				{	
					Map<String,String> map = new HashMap<String,String>();
					map.put("nombreFichero", isNull(anexos[i].getDsNombre()));
					map.put("firmaFichero", "");
					result.add(map);
				}	
			}
		}
		return result;
	}


	/**
	 * Comprueba si es null.
	 *
	 * @param dato el dato
	 * @return el string
	 */
	private String isNull (String dato){
		if (dato == null)
			return "";
		else
			return dato;

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
