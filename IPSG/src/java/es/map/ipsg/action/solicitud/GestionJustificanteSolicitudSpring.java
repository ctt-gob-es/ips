package es.map.ipsg.action.solicitud;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.DocumentoQuery;
import es.map.ips.model.SolicitudComunQuery;
import es.map.ipsg.bean.DocumentoBean;
import es.map.ipsg.bean.RegistroSolicitudJustificanteBean;
import es.map.ipsg.form.CrearDocumentoForm;
import es.map.ipsg.manager.ConvocatoriasManager;
import es.map.ipsg.manager.DocumentoManager;
import es.map.ipsg.manager.LogSolicitudManager;
import es.map.ipsg.manager.PagoSolicitudManager;
import es.map.ipsg.manager.RegistroSolicitudManager;
import es.map.ipsg.manager.SolicitudesManager;
import es.map.ipsg.manager.UsuarioManager;
import es.map.ipsg.util.Constantes;
import es.map.ipsg.util.SHA0;
import es.map.ipsg.util.UtilesIPSG;

/**
 * El Class GestionJustificanteSolicitudSpring.
 */
public class GestionJustificanteSolicitudSpring extends AbstractSpring {
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(GestionJustificanteSolicitudSpring.class);
	
	/** La constante BUNDLE_ERROR. */
	private static final String BUNDLE_ERROR = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_ERROR);
	
	/** La constante STRING_ERRORGESTIONJUSTIFICANTESOLICITANTE_GENERARJUSTIFICANTEPDF. */
	private static final String STRING_ERRORGESTIONJUSTIFICANTESOLICITANTE_GENERARJUSTIFICANTEPDF = " Error GestionJustificanteSolicitudSpring - generarJustificantePDF:";
	
	/** La constante STRING_ERRORDESCRIPCION. */
	private static final String STRING_ERRORDESCRIPCION = "errorDescripcion";
	
	/** La constante STRING_CERTIFICADOS_ERROR_GESTORDOCUMENTAL. */
	private static final String STRING_CERTIFICADOS_ERROR_GESTORDOCUMENTAL = "certificados.error.gestorDocumental" ;
	
	/** La constante STRING_ERROR. */
	private static final String STRING_ERROR = "error";
	
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
	
	/**
	 * Crea una nueva gestion justificante solicitud spring.
	 */
	public GestionJustificanteSolicitudSpring() {
		try {				
			setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
			setSolicitudesManager((SolicitudesManager) getBean("solicitudesManager"));
			setDocumentoManager((DocumentoManager) getBean("documentoManager"));
			setRegistroSolicitudManager((RegistroSolicitudManager) getBean("registroSolicitudManager"));
			setLogSolicitudManager((LogSolicitudManager) getBean("logSolicitudManager"));
			setPagoSolicitudManager((PagoSolicitudManager) getBean("pagoSolicitudManager"));
			setConvocatoriasManager((ConvocatoriasManager) getBean("convocatoriaManager"));
		} catch (Exception e) {
			logger.error(" Error GestionJustificanteSolicitudSpring:",e);
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	protected String doExecute(SpringForm form) throws Exception {
		
		logger.info("Entra en la gestion del justificante");
		
		try{
			CrearDocumentoForm formulario = (CrearDocumentoForm)form;
			byte[] pdfasbytes =  formulario.getArrBytes();
			RegistroSolicitudJustificanteBean registroSolicitudJustificanteBean;
			registroSolicitudJustificanteBean = formulario.getRegistroSolicitudJustificanteBean();
			
			/*----------------------- JUSTIFICANTE SOLICITUD ----------------------------*/
			if(formulario.getTipoJustificante().equals("solicitud")){	
				String fileName = "";
				String contentType = "JustificanteRegistroPdfCsv";
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
		        solicitudAux.setIdSolicitud(Long.parseLong(registroSolicitudJustificanteBean.getIdSolicitud()));
		        documentoAuxConsulta.setSolicitudComun(solicitudAux);	        
				docBean.setDsNombreDocumento(nombreAux);
		        docBean.setIdSolicitud(Long.parseLong(registroSolicitudJustificanteBean.getIdSolicitud()));
		        docBean.setEntorno(Constantes.ENTORNO_SOLICITUDES);
		        docBean.setIdTipoDocumento(Integer.toString(Constantes.TIPO_DOCUMENTO_JUSTIFICANTE_REGISTRO_PDF));//27

		        DocumentoBean docAux = generarJustificantePdf(docBean);
		        
		        if (docAux == null) {
		        	return STRING_ERROR;
		        }
		    
		        logger.info("Justificante PDF creado");	
       
		        
		        // se guarda en el formulario la informacion del fichero
		        formulario.setArrBytes(pdfasbytes);
		        formulario.setDocBean(docBean);	
		        	
		        // Se lanza la apertura del fichero	
		        enviarSalida( docBean,fileData);
			}
			
			if(formulario.getTipoJustificante().equals("registro")){	
				String fileName = "";
				String contentType = "JustificanteRegistroRecPdf";
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
		        solicitudAux.setIdSolicitud(Long.parseLong(registroSolicitudJustificanteBean.getIdSolicitud()));
		        documentoAuxConsulta.setSolicitudComun(solicitudAux);	        
				docBean.setDsNombreDocumento(nombreAux);
		        docBean.setIdSolicitud(Long.parseLong(registroSolicitudJustificanteBean.getIdSolicitud()));
		        docBean.setEntorno(Constantes.ENTORNO_SOLICITUDES);
		        docBean.setIdTipoDocumento(Integer.toString(Constantes.TIPO_DOCUMENTO_JUSTIFICANTE_REGISTRO_PDF));//tipo 4

		        DocumentoBean beanAux = generarJustificantePdf(docBean);
		        
		        if (beanAux == null) {
		        	return STRING_ERROR;
		        }
		        
		        try{
		        	beanAux = documentoManager.generarJustificantePDF(docBean);
		        }catch(Exception e){
		        	logger.error(STRING_ERRORGESTIONJUSTIFICANTESOLICITANTE_GENERARJUSTIFICANTEPDF,e);
		        	this.getRequest().setAttribute(STRING_ERRORDESCRIPCION,RESOURCE_BUNDLE.getString(STRING_CERTIFICADOS_ERROR_GESTORDOCUMENTAL));
		        	return STRING_ERROR;
		        }
		        
		        logger.info("Justificante PDF creado");	
		        // se guarda en el formulario la informacion del fichero
		        formulario.setArrBytes(pdfasbytes);
		        formulario.setDocBean(beanAux);	
		        	
		        // Se lanza la apertura del fichero	
		        enviarSalida( docBean,fileData);
			}
			
			/*------------------------JUSTIFICANTE XML----------------------------*/
			else if (formulario.getTipoJustificante().equals("Xml")){	
				Document documentoXML = formulario.getDocumentoXML();
				
		        SHA0 hash = new SHA0();
				String fileName = "";
				String contentType = "JustificanteResgistro";
		        fileName = "/"+contentType+".xml";
		        int fileSize = pdfasbytes.length;
		        
		        int numAux = fileName.lastIndexOf('/');
		        int numExten = fileName.lastIndexOf('.');
		        fileName.substring(numAux+1,numExten);	        
		        String extension=fileName.substring(numExten+1);
		        
				DocumentoBean documentoBeanXML=new DocumentoBean();
				documentoBeanXML.setByteContenido(UtilesIPSG.convertToBytes(documentoXML));
				documentoBeanXML.setContenidoDocumento(documentoBeanXML.getByteContenido());
				documentoBeanXML.setDsNombreDocumento(contentType + "Firma");
				documentoBeanXML.setDsExtensionDocumento("XML");
				documentoBeanXML.setDsExtracto("Firma resumen");
				Calendar cFirma = Calendar.getInstance();
				documentoBeanXML.setFeAlta(cFirma.getTime());
				documentoBeanXML.setFechaCreacion(cFirma.getTime());
				documentoBeanXML.setIdTipoDocumento(Integer.toString(Constantes.TIPO_DOCUMENTO_JUSTIFICANTE_REGISTRO_XML));//5 es XML
				
				try {
					documentoBeanXML.setTlHuellaDigital(hash.getHash(documentoBeanXML.getByteContenido()));
				} catch (NoSuchAlgorithmException e) {			
					logger.error(" Error GestionJustificanteSolicitudSpring - documentoXML:",e);
				}
				
				documentoBeanXML.setIdSolicitud(Long.parseLong(formulario.getIdSolicitud()));
				documentoBeanXML.setEntorno(Constantes.ENTORNO_SOLICITUDES);
		              
		        logger.info("Extension: "+extension);
		        documentoBeanXML.setExtension(documentoBeanXML.getDsExtensionDocumento());
		        documentoBeanXML.setTamano(safeLongToInt(Math.round(fileSize/1024.0)));
		        documentoBeanXML.setNombre(documentoBeanXML.getDsNombreDocumento());
		        documentoBeanXML.setDescripcion(documentoBeanXML.getDsNombreDocumento());
	        	
		        // esto se hará si el usuario pulsa guardar
		        DocumentoBean docAux = generarJustificantePdf(documentoBeanXML);
		        
		        if (docAux == null) {
		        	return STRING_ERROR;
		        }

	        	
	        	logger.info("Justificante XML generado");
		        // se guarda en el formulario la informacion del fichero
		        formulario.setArrBytes(pdfasbytes);
		        formulario.setDocBean(documentoBeanXML);	
		        
		        enviarSalida( documentoBeanXML,documentoBeanXML.getContenidoDocumento());
			} 
	        
		}catch(Exception eGenerico){
			logger.error(" Error GestionJustificanteSolicitudSpring:",eGenerico);
			this.getRequest().setAttribute("descripcionError", RESOURCE_BUNDLE.getString("field.errorGenerico"));
			return "errorGenerico";
		}
		

		logger.info("Sale de la gestión del justificante- success");
		return "success";
	}
	
	/**
	 * Generar justificante pdf.
	 *
	 * @param docBean el doc bean
	 * @return el documento bean
	 */
	private DocumentoBean generarJustificantePdf(DocumentoBean docBean) {
		try{
        	return documentoManager.generarJustificantePDF(docBean);
        }catch(Exception e){
        	logger.error(STRING_ERRORGESTIONJUSTIFICANTESOLICITANTE_GENERARJUSTIFICANTEPDF,e);
        	this.getRequest().setAttribute(STRING_ERRORDESCRIPCION,RESOURCE_BUNDLE.getString(STRING_CERTIFICADOS_ERROR_GESTORDOCUMENTAL));
        	return null;
        }
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
			String contentDisposition = "attachment; filename=\"" + idAlfresco + "\"";

			HttpServletResponse resp = this.getResponse();
			resp.setContentType("application/octet-stream");
			resp.setHeader("Content-Disposition", contentDisposition);
			IOUtils.write(salida,stream);
			
		} catch (IOException e) {
			logger.error(" Error GestionJustificanteSolicitudSpring - enviarSalida:",e);
		}
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
