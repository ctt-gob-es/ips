package es.map.ipsg.action.solicitud;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import es.map.ips.common.exception.ApplicationException;
import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.DocumentoQuery;
import es.map.ipsg.bean.DocumentoBean;
import es.map.ipsg.manager.DocumentoManager;
import es.map.ipsg.util.Constantes;
import es.map.ipsg.util.SistemaFicheros;

/**
 * El Class DescargarDocumentoSolicitudSpring.
 */
public class DescargarDocumentoSolicitudSpring extends AbstractSpring {
	
	private static final String SUCCESS = "success";

	/** el documento manager. */
	private DocumentoManager documentoManager;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(DescargarDocumentoSolicitudSpring.class);
	
	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);	
	
	/**
	 * Crea una nueva descargar documento solicitud spring.
	 */
	public DescargarDocumentoSolicitudSpring() {
		try {
			setDocumentoManager((DocumentoManager) getBean("documentoManager"));
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error DescargarDocumentoSolicitudSpring" ,e);
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		getLogger().debug("DescargarDocumentoSolicitudSpring -start");
		HttpServletRequest req = this.getRequest();
		String idSolicitud= req.getParameter("idSol");
		String idDocumento = req.getParameter("id");
		
		final String NOSUCCESS = "nosuccess";
		
		if("undefined".equals(idDocumento)){
			this.setRequestAttribute("errorGeneracion",RESOURCE_BUNDLE.getString("field.errorGeneracionDoc"));
			return NOSUCCESS;
		}
		File zipFile = null;
		try{	
			DocumentoQuery documentoQuery = new DocumentoQuery();

			if(idSolicitud==null||idSolicitud.equals("")) {
				documentoQuery.setId(Long.parseLong(idDocumento));	
			}else{
				documentoQuery.addSolicitudIn(Long.parseLong(idSolicitud));
				documentoQuery.addTipoDocumentoIn(Constantes.TIPO_DOCUMENTO_JUSTIFICANTE_PAGO);
				
				//Correción incidencia, descarga justificantes de pago de solicitudes realizadas en otros idiomas
				documentoQuery.addTipoDocumentoIn(Constantes.TIPO_DOCUMENTO_JUSTIFICANTE_PAGO_PDF_CATALAN);
				documentoQuery.addTipoDocumentoIn(Constantes.TIPO_DOCUMENTO_JUSTIFICANTE_PAGO_PDF_EUSKERA);
				documentoQuery.addTipoDocumentoIn(Constantes.TIPO_DOCUMENTO_JUSTIFICANTE_PAGO_PDF_GALLEGO);
				documentoQuery.addTipoDocumentoIn(Constantes.TIPO_DOCUMENTO_JUSTIFICANTE_PAGO_PDF_VALENCIANO);

				List<DocumentoBean> documentosList;
				documentosList=documentoManager.buscarDocumentoCombo(documentoQuery);
				if (documentosList.size()==0){
					return "sinJustificante";
				}
			}

			DocumentoBean doc = documentoManager.obtenerDocumento(documentoQuery);

			String respuesta = NOSUCCESS;
			ArrayList<DocumentoBean> listaDoc = new ArrayList<DocumentoBean>();
			SistemaFicheros sistemaFicheros = new SistemaFicheros();
			
			if(doc != null) {
				listaDoc.add(doc);
				try {
					zipFile = sistemaFicheros.descargarDocumentosTroceados(listaDoc, "");
				} catch (Exception e) {
					logger.error("El fichero no existe en la ruta indicada: "+ doc.getDsRutaDocumento());
				}
			}
			
			
			if(zipFile != null && zipFile.length() >= 30) {
				//Se descarga el zip con todos los adjuntos y se cierra el zip borrando el archivo temporal
				sistemaFicheros.descargarZipTroceado(zipFile, this.getResponse());
				respuesta = SUCCESS;
				
			} 
	
			if (respuesta.equals(NOSUCCESS)) {
				this.setRequestAttribute("errorGeneracion", RESOURCE_BUNDLE.getString("error.alfresco.descargaDocumento"));
			}
			return respuesta;

		}catch(Exception eGenerico){
			logger.error("Error DescargarDocumentoSolicitudSpring - doExecute" ,eGenerico);
			this.getRequest().setAttribute("descripcionError", RESOURCE_BUNDLE.getString("field.errorGenerico"));
			return "errorGenerico";
		}finally {		
		    if (zipFile.delete()) {
		    	logger.info("Fichero Borrado correctamente de la ruta: "+zipFile.getPath());
		    } else {
		    	logger.error("No se ha podido borrar el fichero de la ruta: "+zipFile.getPath());
		    }
		}
	}


	/**
	 * Enviar salida.
	 *
	 * @param doc el doc
	 * @param salida el salida
	 */
	public void enviarSalida(DocumentoBean doc,byte[] salida) {
		String idAlfresco = doc.getNombreAlfresco();
		javax.servlet.ServletOutputStream stream;
		try {
			stream = this.getResponse().getOutputStream();
			String contentDisposition = "attachment; filename=\"" + idAlfresco	+ "\"";

			HttpServletResponse resp = this.getResponse();
			resp.setContentType("application/octet-stream");
			resp.setHeader("Content-Disposition", contentDisposition);
			stream.write(salida);
			stream.flush();
			stream.close();
		} catch (IOException e) {
			logger.error("Error DescargarDocumentoSolicitudSpring - enviarSalida " ,e);
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
	 * Obtiene el logger.
	 *
	 * @return el logger
	 */
	public static Logger getLogger() {
		return logger;
	}
	
}
