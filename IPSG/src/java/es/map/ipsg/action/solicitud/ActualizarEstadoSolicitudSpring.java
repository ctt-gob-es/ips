package es.map.ipsg.action.solicitud;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.net.URL;
import java.security.KeyStore.PrivateKeyEntry;
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

import org.apache.log4j.Logger;
import org.apache.spring.Globals;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.util.StringUtils;

import com.lowagie.text.pdf.PdfCopyFields;
import com.lowagie.text.pdf.PdfReader;

import es.gob.afirma.afirma5ServiceInvoker.Afirma5ServiceInvokerFacade;
import es.gob.afirma.signature.SignatureConstants;
import es.gob.afirma.signature.Signer;
import es.gob.afirma.signature.SignersFactory;
import es.gob.afirma.transformers.TransformersConstants;
import es.gob.afirma.transformers.TransformersFacade;
import es.gob.afirma.utils.Base64Coder;
import es.gob.afirma.utils.DSSConstants.DSSTagsRequest;
import es.gob.afirma.utils.GeneralConstants;
import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.ConvocatoriaQuery;
import es.map.ips.model.DocumentoQuery;
import es.map.ips.model.PagoSolicitudQuery;
import es.map.ips.model.SolicitudComun;
import es.map.ips.model.SolicitudComunQuery;
import es.map.ips.model.SolicitudPropiosQuery;
import es.map.ips.model.TipoDocumentoQuery;
import es.map.ipsg.bean.CamposPropiosBean;
import es.map.ipsg.bean.CiudadanoBean;
import es.map.ipsg.bean.ConvocatoriasBean;
import es.map.ipsg.bean.DocumentoBean;
import es.map.ipsg.bean.JustificanteBean;
import es.map.ipsg.bean.LogSolicitudBean;
import es.map.ipsg.bean.PagoSolicitudBean;
import es.map.ipsg.bean.RegistroSolicitudBean;
import es.map.ipsg.bean.SolicitudBean;
import es.map.ipsg.bean.SolicitudPropiosBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.form.ActualizarEstadoSolicitudForm;
import es.map.ipsg.manager.ConvocatoriasManager;
import es.map.ipsg.manager.CorreoElectronicoManager;
import es.map.ipsg.manager.DocumentoManager;
import es.map.ipsg.manager.EstadoSolicitudManager;
import es.map.ipsg.manager.LogSolicitudManager;
import es.map.ipsg.manager.PagoSolicitudManager;
import es.map.ipsg.manager.RegistroSolicitudManager;
import es.map.ipsg.manager.SolicitudesPropiosManager;
import es.map.ipsg.manager.SolicitudesRegistradasManager;
import es.map.ipsg.manager.TipoPagoManager;
import es.map.ipsg.manager.UsuarioManager;
import es.map.ipsg.res.ResourceLocator;
import es.map.ipsg.util.CSVInside;
import es.map.ipsg.util.CSVStorage;
import es.map.ipsg.util.Constantes;
import es.map.ipsg.util.Jasper;
import es.map.ipsg.util.SistemaFicheros;
import es.map.ipsg.util.UtilesIPSG;
import es.mpt.dsic.inside.ws.service.EeUtilService;
import es.mpt.dsic.inside.ws.service.EeUtilServiceProxy;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * El Class ActualizarEstadoSolicitudSpring.
 */
public class ActualizarEstadoSolicitudSpring extends AbstractSpring {

	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** La constante STRING_SIMPLEDATEFORMAT. */
	private static final String STRING_SIMPLEDATEFORMAT = "dd/MM/yyyy";
	
	/** La constante STRING_FULLTIME. */
	private static final String STRING_FULLTIME = "dd/MM/yyyy HH:mm:ss";
	
	/** La constante STRING_FULLTIME. */
	private static final String STRING_REVERTFULLTIME = "yyyy-MM-dd HH:mm:ss";
	
	/** La constante STRING_SUCCESS. */
	private static final String STRING_SUCCESS = "success";
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(ActualizarEstadoSolicitudSpring.class);
	
	/** el properties. */
	private static Properties properties;

	/** el solicitud manager. */
	private SolicitudesRegistradasManager solicitudManager;
	
	/** el estado solicitud manager. */
	private EstadoSolicitudManager estadoSolicitudManager;
	
	/** el correo electronico manager. */
	private CorreoElectronicoManager correoElectronicoManager;
	
	/** el usuario manager. */
	private UsuarioManager usuarioManager;
	
	/** el log solicitud manager. */
	private LogSolicitudManager logSolicitudManager;
	
	/** el documento manager. */
	private DocumentoManager documentoManager;
	
	/** el convocatoria manager. */
	private ConvocatoriasManager convocatoriaManager;
	
	/** el pago solicitud manager. */
	private PagoSolicitudManager pagoSolicitudManager;
	
	/** el tipo pago manager. */
	private TipoPagoManager tipoPagoManager;
	
	/** el solicitudes propios manager. */
	private SolicitudesPropiosManager solicitudesPropiosManager;
	
	/** el registro solicitud manager. */
	private RegistroSolicitudManager registroSolicitudManager;

	/**
	 * ActualizarEstadoSolicitudAction.
	 */
	public ActualizarEstadoSolicitudSpring() {
		try{
			setSolicitudManager((SolicitudesRegistradasManager) getBean("solicitudesRegistradasManager"));
			setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
			setLogSolicitudManager((LogSolicitudManager) getBean("logSolicitudManager"));
			setEstadoSolicitudManager((EstadoSolicitudManager) getBean("estadoSolicitudManager"));
			setCorreoElectronicoManager((CorreoElectronicoManager)getBean("correoElectronicoManager"));
			setDocumentoManager((DocumentoManager) getBean("documentoManager"));
			setConvocatoriaManager((ConvocatoriasManager) getBean("convocatoriaManager"));
			setTipoPagoManager((TipoPagoManager) getBean("tipoPagoManager"));
			setPagoSolicitudManager((PagoSolicitudManager)getBean("pagoSolicitudManager"));
			setSolicitudesPropiosManager((SolicitudesPropiosManager) getBean("solicitudesPropiosManager"));
			setRegistroSolicitudManager((RegistroSolicitudManager) getBean("registroSolicitudManager"));
			properties = (Properties) getBean("propertiesBean");
		}catch(Exception e){
			logger.warn(e);
			logger.error("Error ActualizarEstadoSolicitudSpring:",e);
		}
	}

	/**
	 * doExecute  de ActualizarEstadoSolicitudAction.
	 *
	 * @param form SpringForm
	 * @return resultado   String
	 * @throws Exception Exception
	 */
	protected String doExecute(SpringForm form) throws Exception {

		String menu_solicitudes = RESOURCE_BUNDLE.getString("field.menu.solicitudes");
		this.getRequest().getSession().setAttribute("pagActiva",menu_solicitudes);
		String titulo = RESOURCE_BUNDLE.getString("field.estadoSolicitud.actualizarEstadoSolicitudMantenimientoTitulo");
		String resultado;
		String mensaje= "";

		try{
			getLogger().debug("ActualizarEstadoSolicitudSpring -start");			
			ActualizarEstadoSolicitudForm formulario = (ActualizarEstadoSolicitudForm ) form;
			String idSolicitud = formulario.getIdSolicitud().toString();

			if(!StringUtils.isEmpty(idSolicitud)){
				SolicitudBean solicitudBean  = new SolicitudBean ();
				RegistroSolicitudBean registroSolicitudBean = new RegistroSolicitudBean();
				solicitudBean = solicitudManager.obtenerSolicitud(Long.valueOf(idSolicitud));

				PagoSolicitudBean pagosolicitudBean = new PagoSolicitudBean();
				PagoSolicitudQuery pagoSolicitudQuery = new PagoSolicitudQuery();
				SolicitudComunQuery solicitudQuery = new SolicitudComunQuery();
				solicitudQuery.setIdSolicitud(solicitudBean.getId());
				pagoSolicitudQuery.setSolicitudComun(solicitudQuery);
				pagoSolicitudQuery.setResultado(Constantes.RESULTADO_OK);				
				pagosolicitudBean = pagoSolicitudManager.buscarPagoSolicitudByIdSolicitud(pagoSolicitudQuery);

				
				// Si queremos pasar de estado no pagada a no registrado nos creamos un nuevo registro en pago
				if((solicitudBean.getIdEstadoSolicitud()).equals(Constantes.ESTADO_SOLICITUD_NO_PAGADO) && null==pagosolicitudBean){
					
						//Ese nuevo registro será exento
					PagoSolicitudBean pagoSolicitud = pagoSolicitudExento(solicitudBean);
					   //Guardamos el pago
					pagoSolicitudManager.guardarPagoSolicitud(pagoSolicitud);
					

				}
				
				// Si queremos pasar de estado no registrado a registrado nos creamos un nuevo registro en registro solicitud
				if((solicitudBean.getIdEstadoSolicitud()).equals(Constantes.ESTADO_SOLICITUD_NO_REGISTRADO)){
					
					//Guardamos los valores del registro
					SolicitudComun solicitudEnRegistro = new SolicitudComun();
					solicitudEnRegistro.setIdSolicitud(Long.valueOf(idSolicitud));					
					registroSolicitudBean.setSolicitud(solicitudEnRegistro);					
					//La Fecha del intento del registro va a ser en este momento que se procede al cambio de estado					
					registroSolicitudBean.setResultado(Constantes.REGISTRO_RESULTADO_OK);
					//La Fecha de registro va a ser la misma que fecha de pago y de solicitud
					
					Date date = comprobarFecha(solicitudBean.getFechaSolicitud());
					
					if (date != null) {
						registroSolicitudBean.setFechaRegistro(date);
				    	registroSolicitudBean.setFechaIntento(date);
					}
					
					registroSolicitudBean.setSolicitante(Constantes.TIPO_PLANTILLA_ADMINISTRADOR);
					registroSolicitudBean.setIdSolicitud(Long.valueOf(idSolicitud));
					
					registroSolicitudManager.guardarRegistroSolicitud(registroSolicitudBean);
				
				}
				
				//Obtenemos el estado
				formulario.setEstadoActual(solicitudBean.getEstadoSolicitud().getDescripcion());

				//Asignamos el id del nuevo estado al objeto solicitudBean									
				solicitudBean.setEstadoSolicitud(estadoSolicitudManager.obtenerEstadoSolicitud(formulario.getIdNuevoEstado()));
				
				//Actualizamos el estado
				formulario.setNuevoEstado(solicitudBean.getEstadoSolicitud().getDescripcion());
				
				UsuarioBean usuarioBean = (UsuarioBean) getRequest().getSession().getAttribute("usuario");
				generarRegistroLogSolicitud(formulario,usuarioBean.getLogin(), solicitudBean);

				// Fecha actualización
				solicitudBean.setFechaUtlActualizacion(new Date());
				//Almacenamos la actualización del estado
				solicitudManager.modificarSolicitudRegistrada(solicitudBean);

				// TODO Generar justificante de registro únicamente cuando se actualice a estado REGISTRADO (3)
				if(null != formulario.getIdNuevoEstado() && formulario.getIdNuevoEstado().equals(Constantes.ESTADO_SOLICITUD_REGISTRADO)){
					Date date1 = new Date();
					Date date2 = new Date();
					logger.info("Creando Justificante PDF");
					date1 = new Date();
					crearPdf(solicitudBean,registroSolicitudBean);
					date2 = new Date();
					logger.warn("Justificante PDF end: "+(date2.getTime()-date1.getTime()));
				}

				resultado = STRING_SUCCESS;
				mensaje = RESOURCE_BUNDLE.getString("field.estadoSolicitud.actualizacionEstadoCorrecta");
			}else{
				resultado = STRING_SUCCESS;
				mensaje = RESOURCE_BUNDLE.getString("field.estadoSolicitud.actualizarEstadoSolicitudError");
			}

			setRequestAttribute("titulo",titulo);
			setRequestAttribute("mensaje",mensaje);
			this.setRequestAttribute("scroll", formulario.getSubmit());
			getLogger().debug("ActualizarEstadoSolicitudSpring -end");
			return resultado;
		}catch (Exception ex){
			logger.error("Error actualizando estado: "+ex);
			logger.error("Error ActualizarEstadoSolicitudSpring - doExecute:",ex);
			mensaje = RESOURCE_BUNDLE.getString("field.estadoSolicitud.actualizarEstadoSolicitudError");			

		}finally{			
			setRequestAttribute("titulo",titulo);
			setRequestAttribute("mensaje",mensaje);

			return STRING_SUCCESS;
		}
	}
	
	/**
	 * Comprobar fecha.
	 *
	 * @param fecha el fecha
	 * @return el date
	 */
	private Date comprobarFecha(String fecha) {
		Date date = null;
		Boolean validarFecha = false; 
		
		try {
			date = new SimpleDateFormat(STRING_FULLTIME).parse(fecha);				
		} catch (ParseException e) {
			logger.error("Error ActualizarEstadoSolicitudSpring - comprobarFecha() - Error al parsear fechaSolicitud:"+ fecha);
			validarFecha = true;
		}
		
		if (validarFecha) {
			try {
				date = new SimpleDateFormat(STRING_REVERTFULLTIME).parse(fecha);
			} catch (ParseException e) {
				logger.error("Error ActualizarEstadoSolicitudSpring - comprobarFecha() - Error al parsear fechaSolicitud:"+ fecha,e);
			}
		} else {
			logger.error("Error ActualizarEstadoSolicitudSpring - pagoSolicitudExento - no ha sido posible parsear fechasolicitud:"+ fecha);
		}
		
		return date;
	}

	/**
	 * Pago solicitud exento.
	 *
	 * @param solicitudBean el solicitud bean
	 * @return el pago solicitud bean
	 */
	private PagoSolicitudBean pagoSolicitudExento(SolicitudBean solicitudBean) {
		PagoSolicitudBean pagoSolicitudBean = new PagoSolicitudBean();
		
		pagoSolicitudBean.setIdSolicitud(solicitudBean.getId());		
		pagoSolicitudBean.setTipo(Constantes.TIPO_PAGO_EFECTIVO_INT);
		pagoSolicitudBean.setImporte(0f);
		pagoSolicitudBean.setResultado(Constantes.RESULTADO_OK);
		pagoSolicitudBean.setSolicitaReduccion(Constantes.REDUCCION_NO);
		
		pagoSolicitudBean.setFechaIntento(comprobarFecha(solicitudBean.getFechaSolicitud()));
		
		return pagoSolicitudBean;
	}

	/**
	 * Crear pdf.
	 *
	 * @param solicitudBean el solicitud bean
	 * @param registroBean el registro bean
	 * @return el int
	 * @throws Exception el exception
	 */
	private int crearPdf(SolicitudBean solicitudBean, RegistroSolicitudBean registroBean) throws Exception {

		JRBeanCollectionDataSource jrBeanCollectionDataSource = null;
		File sourceFile = null;
		

		//Creamos los array de bytes de cada documento
		byte[] pdfasbytes = null;

		//Creamos los arrayList para los PDF's
		ArrayList <JustificanteBean> aListPdfPrueba = new ArrayList<JustificanteBean>();


		//Creamos los jasper
		Jasper jasperJustificante = new Jasper();		

		//Enruta los jasper	
		String ficheroPdf = "";
		ficheroPdf = properties.getProperty("jasper.JustificanteR");

		//Creamos el bean que guarda los datos del primer jasper
		JustificanteBean justificanteBean;
		justificanteBean = toJustificanteBean(solicitudBean, registroBean);

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

		jrBeanCollectionDataSource = new JRBeanCollectionDataSource(
				(Collection<JustificanteBean>) aListPdfPrueba);

		// Genero el pdf de prueba
		// Obtengo los ficheros implicados en la generacion del pdf
		URL url = ResourceLocator.getInstance().getJasperTemplate(ficheroPdf);			
		String rutaFicheroJasper="";
		if(url!=null){
			rutaFicheroJasper = url.getFile();
		}

		sourceFile = new File(rutaFicheroJasper);

		// Cargo el informe compilado en el objeto jasperreport
		pdfasbytes = jasperJustificante.generarPDF(
				jrBeanCollectionDataSource, sourceFile, "");

		ByteArrayOutputStream pdfCompuesto = new ByteArrayOutputStream();
		PdfReader reader1 = new PdfReader(pdfasbytes);
		PdfCopyFields copy = new PdfCopyFields(pdfCompuesto);

		copy.addDocument(reader1);
		copy.close();

		// Añadimos la firma al documento
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
			ciudadanoSolicitud.setNif(solicitudBean.getNif());
			ciudadanoSolicitud.setNombre(solicitudBean.getNombre());
			ciudadanoSolicitud.setPrimerApellido(solicitudBean.getPrimerApellido());
			ciudadanoSolicitud.setSegundoApellido(solicitudBean.getSegundoApellido());
			byte[] docCsv = CSVInside.generarCopiaCSV(eSignature, ciudadanoSolicitud, properties, csvEeUtilService, codigoCSV, fechaRegistro);
			//devuelve el documento con CSV y sin firmar
			
			if (docCsv!=null) {
				//Se firma pdf
				byte [] docCsvSignature = signer.sign(docCsv, SignatureConstants.SIGN_ALGORITHM_SHA512WITHRSA, SignatureConstants.SIGN_MODE_IMPLICIT, certificatePrivateKey, null);
				logger.info("Copia pdf con csv incrustado finalizada");
				
/*				byte[] esignaturebase64 = Base64Coder.encodeBase64(docCsvSignature);
				
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
				Long idSolicitud = solicitudBean.getId();
				long idDocumento = registroPdfCsvDocumentos(docCsvSignature, idSolicitud, codigoCSV);
				
				if (idDocumento>0) {
					// subida justificante a filesystem
					logger.info("Subiendo Justificante a Sistema de ficheros");
					int subida = subirDocumentoPdfCSV(docCsvSignature, idDocumento); 
					if(subida == 0){
						return 0;
					}
					logger.info("Subida PDF a Sistema de ficheros finalizada");
				} else {
					return 0;
				}
				
				// CSV STORAGE
				String guardado = CSVStorage.guardarDocumento(codigoCSV, docCsvSignature, properties);
				if (!guardado.equals(Constantes.RESULTADO_OK)){
					return 0;
				}
			} else {
				return 0;
			}
		} else {
			return 0;
		}

		return 1;
	}

	/**
	 * To justificante bean.
	 *
	 * @param solicitudBean el solicitud bean
	 * @param registroBean el registro bean
	 * @return el justificante bean
	 */
	private JustificanteBean toJustificanteBean(SolicitudBean solicitudBean, RegistroSolicitudBean registroBean) {

		JustificanteBean aux = new JustificanteBean();
		aux.setOficina("");

		// DatosRegistro
		if(registroBean.getNumeroRegistro() != null && !"".equals(registroBean.getNumeroRegistro())){
			aux.setNumRegistro(registroBean.getNumeroRegistro());
		}else{
			aux.setNumRegistro("");
		}
		
		if(registroBean.getFechaRegistro() != null && !"".equals(registroBean.getFechaRegistro().toString())){
			SimpleDateFormat formatoFecha = new SimpleDateFormat(STRING_SIMPLEDATEFORMAT);		
			String fechaString = formatoFecha.format(registroBean.getFechaRegistro());
			aux.setFecha(fechaString);
			aux.setHora("");
		}else if(solicitudBean.getFechaSolicitud()!=null){
			aux.setFecha(solicitudBean.getFechaSolicitud());
		}else{
			aux.setFecha("");
		}

		aux.setHora("");
		
		// Buscar convocatoria
		ConvocatoriasBean convocatoriaBean;
		ConvocatoriaQuery convocatoriaQuery = new ConvocatoriaQuery();
		convocatoriaQuery.setId(solicitudBean.getIdConvocatoria());
		convocatoriaBean = convocatoriaManager.buscarConvocatoriaById(convocatoriaQuery) ;

		// Solicitud
		if(solicitudBean.getCentroGestor() != null && !"".equals(solicitudBean.getCentroGestor())){
			aux.setCentroGestor(solicitudBean.getCentroGestor());
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
		if(solicitudBean.getEmail() != null && !"".equals(solicitudBean.getEmail())){
			aux.setCorreoElectronicos(solicitudBean.getEmail());
		}else{
			aux.setCorreoElectronicos("");
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
		if(solicitudBean.getFechaNacimiento() != null && !"".equals(solicitudBean.getFechaNacimiento().toString())){
			SimpleDateFormat formatoFechaNac = new SimpleDateFormat(STRING_SIMPLEDATEFORMAT);
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
		if(solicitudBean.getDesCuerpoEscala() != null && !"".equals(solicitudBean.getDesCuerpoEscala())){
			aux.setCuerpoEscala(solicitudBean.getDesCuerpoEscala());
		}else{
			aux.setCuerpoEscala("");
		}
		if(solicitudBean.getEspecialidad() != null && !"".equals(solicitudBean.getEspecialidad())){
			aux.setEspecialidad(solicitudBean.getDescripcionEspecialidad());
		}else{
			aux.setEspecialidad("");
		}
		if(convocatoriaBean.getFormaAcceso() != null && !"".equals(convocatoriaBean.getFormaAcceso())){
			aux.setFormaAcceso(convocatoriaBean.getFormaAcceso());
		}else{
			aux.setFormaAcceso("");
		}
		//Ministerio
		if(convocatoriaBean.getMinisterio() != null && !"".equals(convocatoriaBean.getMinisterio())){
			aux.setMinisterio(convocatoriaBean.getMinisterio());
		}else{
			aux.setMinisterio("");
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
		PagoSolicitudBean pagosolicitudBean;
		PagoSolicitudQuery pagoSolicitudQuery = new PagoSolicitudQuery();
		SolicitudComunQuery solicitudQuery = new SolicitudComunQuery();
		solicitudQuery.setIdSolicitud(solicitudBean.getId());
		pagoSolicitudQuery.setSolicitudComun(solicitudQuery);
		pagoSolicitudQuery.setResultado(Constantes.RESULTADO_OK);
		pagosolicitudBean = pagoSolicitudManager.buscarPagoSolicitudByIdSolicitud(pagoSolicitudQuery); 

		if(pagosolicitudBean!=null && pagosolicitudBean.getImporte()!=null){
			aux.setImporte(pagosolicitudBean.getImporte().toString());
		}else{
			aux.setImporte("");
		}

		if(pagosolicitudBean.getTipo()!=null && !"".equals(pagosolicitudBean.getTipo().toString())){
			if(pagosolicitudBean.getTipo() == 'C'){
				aux.setTipoPago("CUENTA CORRIENTE");
			}
			else if(pagosolicitudBean.getTipo()=='T'){
				aux.setTipoPago("TARJETA CREDITO");			
			}
			else if(pagosolicitudBean.getTipo()=='E' && ("0.0".equals(pagosolicitudBean.getImporte().toString()) 
						|| ".0".equals(pagosolicitudBean.getImporte().toString())
						||"0.".equals(pagosolicitudBean.getImporte().toString()))){
				
				aux.setTipoPago(Constantes.PAGO_TIPO_EXENTO.toUpperCase());
			}
			else if(pagosolicitudBean.getTipo()=='E'){
				aux.setTipoPago("EFECTIVO");
			}
			else if(pagosolicitudBean.getTipo()=='A'){
				aux.setTipoPago("TODOS");
			}else{
				aux.setTipoPago("");
			}

		}else{
			aux.setTipoPago(Constantes.NO_APLICA);
		}
		if(pagosolicitudBean.getDesMotivoReduccionTasa()!=null && !"".equals(pagosolicitudBean.getDesMotivoReduccionTasa())){
			aux.setCausaExencion(pagosolicitudBean.getDesMotivoReduccionTasa());
		}else{
			aux.setCausaExencion("");
		}
		if(pagosolicitudBean.getFechaIntento()!=null){
			SimpleDateFormat formatoFecha = new SimpleDateFormat(STRING_SIMPLEDATEFORMAT);
			String fecha = formatoFecha.format(pagosolicitudBean.getFechaIntento());
			aux.setFechaPago(fecha);
		}else{
			aux.setFechaPago(Constantes.NO_APLICA);
		}
		if(pagosolicitudBean.getNrc()!=null && !"".equals(pagosolicitudBean.getNrc())){
			aux.setNrc(pagosolicitudBean.getNrc());
		}else{
			aux.setNrc(Constantes.NO_APLICA);
		}
		if(pagosolicitudBean.getDesEntidadFinanciera()!=null && !"".equals(pagosolicitudBean.getDesEntidadFinanciera())){
			aux.setEntidad(pagosolicitudBean.getDesEntidadFinanciera());
		}else{
			aux.setEntidad(Constantes.NO_APLICA);
		}
		
		if(null != solicitudBean.getNumModelo()){
			aux.setModelo(solicitudBean.getNumModelo());
		}

	return aux;
	}
	
	/**
	 * Registra el pdf con csv incrustrado en la tabla documentos de bb.dd
	 *
	 * @param pdfasbytes - el contenido del fichero
	 * @param idSolicitud el id solicitud
	 * @param codigoCSV - un codigo CSV
	 * @return idDocumento - el id con el que se inserta el documento en la bb.dd
	 */
	private long registroPdfCsvDocumentos(byte[] pdfasbytes, Long idSolicitud, String codigoCSV) {

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
		docBean.setIdSolicitud(idSolicitud);
		
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
			logger.info(" Error ActualizarEstadoSolicitudSpring - error en la insercion", e);
			
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
				logger.error("Error ActualizarEstadoSolicitudSpring - subirDocumentoPdfCSV -  insertando en filesystem:",e);
				try{
					documentoManager.eliminarDocumentoById(idDocumento);
				}catch(Exception o){
					logger.error("Error ActualizarEstadoSolicitudSpring - subirDocumentoPdfCSV- eliminando documento:",o);
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
	 * Generar registro log solicitud.
	 *
	 * @param formulario el formulario
	 * @param username el username
	 * @param solicitudBean el solicitud bean
	 */
	public void generarRegistroLogSolicitud(ActualizarEstadoSolicitudForm formulario,String username, SolicitudBean solicitudBean){

		//Cargo los datos en el bean del log solicitudes que se usara para crear el registro en la tabla
		LogSolicitudBean logSolicitudBean = new LogSolicitudBean();

		logSolicitudBean.setTipoOperacion(Constantes.OPERACION_ACTUALIZAR_ESTADO_SOLICITUD);
		logSolicitudBean.setTipoActor(Constantes.OPERACION_ACTUALIZAR_ESTADO_SOLICITUD_USUARIO);
		logSolicitudBean.setActor(username);

		StringBuilder sbDetalle = new StringBuilder();
		sbDetalle.append(RESOURCE_BUNDLE.getString("field.solicitud.detalleOperacionActualizarEstadoPrimeraParte"))
				.append(" ").append(formulario.getEstadoActual())
				.append(RESOURCE_BUNDLE.getString("field.solicitud.detalleOperacionActualizarEstadoSegundaParte"))
				.append(" ").append(formulario.getNuevoEstado())
				.append(RESOURCE_BUNDLE.getString("field.solicitud.detalleOperacionActualizarEstadoTerceraParte"))
				.append(" ").append(formulario.getIdSolicitud());
		logSolicitudBean.setDetalleOperacion(sbDetalle.toString());
		
		
		logSolicitudBean.setIdSolicitud(formulario.getIdSolicitud());

		//añadimos los estados de solicitud
		logSolicitudBean.setId_estado_anterior(solicitudBean.getIdEstadoSolicitud());
		logSolicitudBean.setId_estado_actual(Integer.valueOf(formulario.getIdNuevoEstado()));

		logSolicitudManager.generarRegistroLogSolicitud(logSolicitudBean);		
	}

	/**
	 * Obtiene el solicitud manager.
	 *
	 * @return the solicitudManager
	 */
	public SolicitudesRegistradasManager getSolicitudManager() {
		return solicitudManager;
	}

	/**
	 * Establece el solicitud manager.
	 *
	 * @param solicitudManager the solicitudManager to set
	 */
	public void setSolicitudManager(SolicitudesRegistradasManager solicitudManager) {
		this.solicitudManager = solicitudManager;
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
	 * Obtiene el usuario manager.
	 *
	 * @return the usuarioManager
	 */
	public UsuarioManager getUsuarioManager() {
		return usuarioManager;
	}

	/**
	 * Establece el usuario manager.
	 *
	 * @param usuarioManager the usuarioManager to set
	 */
	public void setUsuarioManager(UsuarioManager usuarioManager) {
		this.usuarioManager = usuarioManager;
	}

	/**
	 * Obtiene el log solicitud manager.
	 *
	 * @return the logSolicitudManager
	 */
	public LogSolicitudManager getLogSolicitudManager() {
		return logSolicitudManager;
	}

	/**
	 * Establece el log solicitud manager.
	 *
	 * @param logSolicitudManager the logSolicitudManager to set
	 */
	public void setLogSolicitudManager(LogSolicitudManager logSolicitudManager) {
		this.logSolicitudManager = logSolicitudManager;
	}

	/**
	 * Obtiene el estado solicitud manager.
	 *
	 * @return the estadoSolicitudManager
	 */
	public EstadoSolicitudManager getEstadoSolicitudManager() {
		return estadoSolicitudManager;
	}

	/**
	 * Establece el estado solicitud manager.
	 *
	 * @param estadoSolicitudManager the estadoSolicitudManager to set
	 */
	public void setEstadoSolicitudManager(
			EstadoSolicitudManager estadoSolicitudManager) {
		this.estadoSolicitudManager = estadoSolicitudManager;
	}

	/**
	 * Obtiene el correo electronico manager.
	 *
	 * @return the correoElectronicoManager
	 */
	public CorreoElectronicoManager getCorreoElectronicoManager() {
		return correoElectronicoManager;
	}

	/**
	 * Establece el correo electronico manager.
	 *
	 * @param correoElectronicoManager the correoElectronicoManager to set
	 */
	public void setCorreoElectronicoManager(CorreoElectronicoManager correoElectronicoManager) {
		this.correoElectronicoManager = correoElectronicoManager;
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
	 * Obtiene el tipo pago manager.
	 *
	 * @return el tipo pago manager
	 */
	public TipoPagoManager getTipoPagoManager() {
		return tipoPagoManager;
	}

	/**
	 * Establece el tipo pago manager.
	 *
	 * @param tipoPagoManager el nuevo tipo pago manager
	 */
	public void setTipoPagoManager(TipoPagoManager tipoPagoManager) {
		this.tipoPagoManager = tipoPagoManager;
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
	public void setRegistroSolicitudManager(RegistroSolicitudManager registroSolicitudManager) {
		this.registroSolicitudManager = registroSolicitudManager;
	}

}
