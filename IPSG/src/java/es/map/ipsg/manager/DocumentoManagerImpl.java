package es.map.ipsg.manager;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.net.URL;
import java.security.KeyStore.PrivateKeyEntry;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Set;


import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.apache.axis.attachments.OctetStream;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;

import com.lowagie.text.pdf.PdfCopyFields;
import com.lowagie.text.pdf.PdfReader;

import es.gob.afirma.signature.SignatureConstants;
import es.gob.afirma.signature.Signer;
import es.gob.afirma.signature.SignersFactory;
import es.gob.afirma.signature.SigningException;
import es.map.ips.common.exception.ApplicationException;
import es.map.ips.common.model.OrderType;
import es.map.ips.common.model.SearchResult;
import es.map.ips.common.spring.ApplicationContextProvider;
import es.map.ips.dao.ConvocatoriaDAO;
import es.map.ips.dao.DocumentoDAO;
import es.map.ips.dao.SolicitudComunDAO;
import es.map.ips.dao.TipoDocumentoDAO;
import es.map.ips.model.Convocatoria;
import es.map.ips.model.ConvocatoriaQuery;
import es.map.ips.model.Documento;
import es.map.ips.model.DocumentoQuery;
import es.map.ips.model.EntidadFinanciera;
import es.map.ips.model.PagoSolicitudQuery;
import es.map.ips.model.Pais;
import es.map.ips.model.Provincia;
import es.map.ips.model.RegistroSolicitudQuery;
import es.map.ips.model.SolicitudComun;
import es.map.ips.model.SolicitudComunQuery;
import es.map.ips.model.SolicitudPropiosQuery;
import es.map.ips.model.TipoDocumento;
import es.map.ips.model.TipoDocumentoQuery;
import es.map.ipsg.bean.GenerarJustificanteBean;
import es.map.ipsg.bean.CamposPropiosBean;
import es.map.ipsg.bean.CiudadanoBean;
import es.map.ipsg.bean.ConvocatoriasBean;
import es.map.ipsg.bean.DocumentoBean;
import es.map.ipsg.bean.JustificanteBean;
import es.map.ipsg.bean.PagoSolicitudBean;
import es.map.ipsg.bean.RegistroSolicitudBean;
import es.map.ipsg.bean.RegistroSolicitudJustificanteBean;
import es.map.ipsg.bean.SolicitudBean;
import es.map.ipsg.bean.SolicitudPropiosBean;
import es.map.ipsg.clienteWS.registroRec3.RegistroType;
import es.map.ipsg.clienteWS.registroRec3.type.DocumentoType;
import es.map.ipsg.clienteWS.registroRec3.type.InteresadoType;
import es.map.ipsg.clienteWS.registroRec3.type.JustificanteType;
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


/**
 * El Class DocumentoManagerImpl.
 */
public class DocumentoManagerImpl implements DocumentoManager {
	
	
	/** el formulario 790 manager. */
	private ConvocatoriasManager convocatoriasManager;
	
	/** el documento DAO. */
	// Variables
	private DocumentoDAO documentoDAO;
	
	/** el tipo documento DAO. */
	private TipoDocumentoDAO tipoDocumentoDAO;
	
	/** el convocatoria DAO. */
	private ConvocatoriaDAO convocatoriaDAO;
	
	/** el solicitud comun DAO. */
	private SolicitudComunDAO solicitudComunDAO;
	
	/** el solicitudes manager. */
	private SolicitudesManager solicitudesManager;
	
	/** el documento manager. */
	private DocumentoManager documentoManager;
	
	/** el registro solicitud manager. */
	private RegistroSolicitudManager registroSolicitudManager;
	
	/** el solicitudes propios manager. */
	private SolicitudesPropiosManager solicitudesPropiosManager;
	
	/** el pago solicitud manager. */
	private PagoSolicitudManager pagoSolicitudManager;
	
	/** el existe gestor documental. */
	private boolean existeGestorDocumental;
	
	/** el space home alfresco. */
	private String spaceHomeAlfresco = "";
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(DocumentoManagerImpl.class);
	
	/** el url gestor. */
	private String urlGestor;
	
	/** el user gestor. */
	private String userGestor;
	
	/** el password gestor. */
	private String passwordGestor;
	
	/** el ruta temporales. */
	private String rutaTemporales;
	
	/** el space oficina registro. */
	private String spaceOficinaRegistro;
	
	/** el space aplicacion. */
	private String spaceAplicacion = "";
	

	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.DocumentoManager#buscarDocumentoCombo(es.map.ips.model.DocumentoQuery)
	 */
	public ArrayList<DocumentoBean> buscarDocumentoCombo(
			DocumentoQuery documentoQuery) {

		SearchResult<Documento> documento = buscarDocumento(documentoQuery);
		ArrayList<DocumentoBean> arrDocumento = new ArrayList<DocumentoBean>();
		for (int i = 0; i < documento.getResults().size(); i++) {
			DocumentoBean aux;
			aux = toDocumentoBean(documento.getResults().get(i));
			if (aux != null) {
				arrDocumento.add(aux);
			}
		}
		return arrDocumento;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.DocumentoManager#buscarDocumentosByIdSolicitud(es.map.ips.model.DocumentoQuery)
	 */
	public ArrayList<DocumentoBean> buscarDocumentosByIdSolicitud(
			DocumentoQuery documentoQuery) {
		SearchResult<Documento> documentos = documentoDAO.search(documentoQuery);
		ArrayList<DocumentoBean> documentosArray = new ArrayList<DocumentoBean>();		
		if(documentos != null){
			for(int i=0;i<documentos.getResults().size();i++){
				DocumentoBean documento;
				documento = toDocumentoBean(documentos.getResults().get(i));
				if(documento != null){
					documentosArray.add(documento);
				}
			}
		}
		return documentosArray;
	}

	/**
	 * Buscar documento.
	 *
	 * @param documentoQuery el documento query
	 * @return el search result
	 */
	private SearchResult<Documento> buscarDocumento(
			DocumentoQuery documentoQuery) {
		ApplicationException.assertNotNull(documentoQuery,
				"documentoQuery no puede ser null");

		return documentoDAO.search(documentoQuery);
	}

	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.DocumentoManager#insertarDocumentoFirma(es.map.ipsg.bean.DocumentoBean)
	 */
	public Long insertarDocumentoFirma(DocumentoBean docBean){
		Documento doc = new Documento();
		doc.setFechaCreacion(docBean.getFechaCreacion());
		doc.setDescripcion(docBean.getDescripcion());
		doc.setNombre(docBean.getNombre());
		SolicitudComunQuery solicitud = new SolicitudComunQuery();
		solicitud.setIdSolicitud(docBean.getIdSolicitud());
		SolicitudComun aux = solicitudComunDAO.searchUnique(solicitud);
		doc.setSolicitudComun(aux);
		doc.setTamano(docBean.getTamano());
        
        String ubicacion = generarUbicacion(docBean.getIdSolicitud());
		docBean.setUbicacion(ubicacion);
		doc.setUbicacion(ubicacion);
		
		TipoDocumento tipDoc;
		
		if(docBean.getIdTipoDocumento() != null && !"".equals(docBean.getIdTipoDocumento())){
			tipDoc = tipoDocumentoDAO.get(Integer.parseInt(docBean.getIdTipoDocumento()));
		}else{
			tipDoc = tipoDocumentoDAO.get(Constantes.TIPO_DOCUMENTO_FIRMA);
		}
		
		doc.setTipoDocumento(tipDoc);
		long idDocumento =0L;
		
		try{
			idDocumento = documentoDAO.insert(doc);			
		}catch(Exception e){
			logger.error(" Error DocumentoManagerImpl - insertarDocumentoFirma - insertar idDocumento:"+idDocumento,e);
			return 0L;
		}
		
		StringBuffer nombreAlfresco = new StringBuffer();
		
		if(docBean.getDsNombreDocumento() != null && !"".equals(docBean.getDsNombreDocumento())){
			nombreAlfresco.append(docBean.getDsNombreDocumento()).append(".").append(docBean.getExtension());
		}
		
		doc = documentoDAO.get(idDocumento);
		doc.setNombreAlfresco(nombreAlfresco.toString());
		docBean.setNombreAlfresco(nombreAlfresco.toString());
		documentoDAO.update(doc);

		SistemaFicheros sistema = new SistemaFicheros();
		
		try{
			sistema.insertarContenido(docBean, null);
		}catch(Exception e){
			logger.error(" Error DocumentoManagerImpl - insertarDocumentoFirma - insertarContenido:",e);
			try{
				documentoDAO.delete(idDocumento);
				}catch(Exception o){
					logger.error(" Error DocumentoManagerImpl - insertarDocumentoFirma - delete idDocumento:"+idDocumento,o);
					return 0L;				
				}
			return 0L;
		}
		return idDocumento;
	}
	
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.DocumentoManager#obtenerContenidoDocumento(es.map.ipsg.bean.DocumentoBean)
	 */
	public byte[] obtenerContenidoDocumento(DocumentoBean documentoBean)  throws Exception{
		
		logger.debug("obtenerContenidoDocumento - start");
		
		 byte[] content = null;
		
		try {
			SistemaFicheros sistemaFicheros = new SistemaFicheros();
			content = sistemaFicheros.obtenerContenido(documentoBean);
		} catch (Exception e1) {
			logger.error(" Error DocumentoManagerImpl -Error obteniendo el documento.",e1);
			
		}
		
		return content;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.DocumentoManager#obtenerContenidoDocumento(es.map.ipsg.bean.DocumentoBean)
	 */
/*	public byte[] obtenerContenidoDocumentoTroceado(HttpServletResponse response, DocumentoBean documentoBean)  throws Exception{
		
		logger.debug("obtenerContenidoDocumento - start");
		
		 byte[] content = null;
		
		try {
			SistemaFicheros sistemaFicheros = new SistemaFicheros();
			content = sistemaFicheros.obtenerContenidoTroceado(response ,documentoBean);
		} catch (Exception e1) {
			logger.error(" Error DocumentoManagerImpl -Error obteniendo el documento.",e1);
			
		}
		
		return content;
	}*/
	
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.DocumentoManager#existeDocumento(java.lang.String)
	 */
	public boolean existeDocumento(String rutaDocumento)  throws Exception{
		
		logger.debug("existenciaDocumento- start");
		
		File fichero = new File(rutaDocumento);
		
		return fichero.exists();			
	}

	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.DocumentoManager#obtenerDocumento(es.map.ips.model.DocumentoQuery)
	 */
	public DocumentoBean obtenerDocumento(DocumentoQuery documentoQuery) {
		logger.info("Obteniendo documento");
		Documento doc = documentoDAO.searchUnique(documentoQuery);
		DocumentoBean bean = toDocumentoBean(doc);

		return bean;
	}

	/**
	 * To documento bean.
	 *
	 * @param d el d
	 * @return el documento bean
	 */
	private DocumentoBean toDocumentoBean(Documento d) {
		DocumentoBean bean = new DocumentoBean();
		bean.setId(d.getId());
		bean.setNombre(d.getNombre());
		bean.setDescripcion(d.getDescripcion());
		bean.setFechaCreacion(d.getFechaCreacion());
		bean.setTamano(d.getTamano());
		bean.setTipoDocumento(d.getTipoDocumento());
		bean.setIdTipoDocumento(String.valueOf(d.getTipoDocumento().getId()));
		bean.setNombreAlfresco(d.getNombreAlfresco());
		bean.setDesTipoDocumento(d.getTipoDocumento().getDescripcion());
		
		if(d.getConvocatoria()!=null){
			bean.setConvocatoria(d.getConvocatoria());
			bean.setIdConvocatoria(d.getConvocatoria().getId());
		}
		
		if(d.getSolicitudComun()!=null){
			bean.setSolicitud(d.getSolicitudComun());
			bean.setIdSolicitud(d.getSolicitudComun().getIdSolicitud());
			bean.setNumSolicitud(d.getSolicitudComun().getNumeroSolicitud());
		}
		
		if(d.getHash() != null){
			bean.setHashExtracto(d.getHash());
		}
		
		if(d.getUbicacion()!=null){
			bean.setUbicacion(d.getUbicacion());
		}
		
		if(d.getCorreoElectronico()!=null){
			bean.setCorreoElectronico(d.getCorreoElectronico());
		}
		
		try{
			String nombreAlfresco = d.getNombreAlfresco();
			int index = nombreAlfresco.lastIndexOf(".");
			String extension = nombreAlfresco.substring(index,nombreAlfresco.length());
			bean.setExtension(extension);
		}catch(Exception e){
			logger.error(" Error DocumentoManagerImpl - No se ha podido recuperar la extension del documento.",e);	
		}
		
		return bean;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.DocumentoManager#insertarDocumentoCsv(es.map.ipsg.bean.DocumentoBean)
	 */
	/* 
	 * Registra en la tabla Documento un justificante pdf despues de haberse procesado por CSVInside
	 * @param docBean - el documento bean a registrar
	 * @return idDocumento - el id de documento con el que se registrara
	 */
	public long insertarDocumentoCsv(DocumentoBean docBean) {
		Documento doc = new Documento();
		
		doc.setNombre(docBean.getNombre());
		doc.setFechaCreacion(docBean.getFechaCreacion());
		
		TipoDocumentoQuery tipoDocumentoQuery = new TipoDocumentoQuery();
		tipoDocumentoQuery.setId(Integer.parseInt(docBean.getIdTipoDocumento()));
		TipoDocumento tipoDocumento = tipoDocumentoDAO.searchUnique(tipoDocumentoQuery);
		doc.setTipoDocumento(tipoDocumento);
		
		doc.setDescripcion(docBean.getDescripcion());
		doc.setTamano(docBean.getTamano());
		
		SolicitudComunQuery solicitud = new SolicitudComunQuery();
		solicitud.setIdSolicitud(docBean.getIdSolicitud());
		SolicitudComun aux = solicitudComunDAO.searchUnique(solicitud);
		doc.setSolicitudComun(aux);
		doc.setUbicacion(docBean.getUbicacion());
		doc.setCsv(docBean.getCsv());
		
		long idDocumento = 0L;
		try {
			idDocumento = documentoDAO.insert(doc);
		} catch(Exception e) {
			logger.info(e);
			logger.error(" Error DocumentoManagerImpl - insertarDocumentoCsv."+ idDocumento,e);
			return 0;
		}
				
		doc = documentoDAO.get(idDocumento);
		doc.setNombreAlfresco(Constantes.NOMBRE_JUSTIFICANTE_REGISTRO_CSV + idDocumento + ".pdf");
		documentoDAO.update(doc);
		
		return idDocumento;
	}

	/**
	 * Inserta Justificante Registro Generado.
	 *
	 * @param docBean el doc bean
	 * @param ruta el ruta
	 * @return el documento bean
	 * @throws Exception el exception
	 */
	public DocumentoBean insertarDocumentoPDF(DocumentoBean docBean, String ruta) throws Exception {

		Documento doc = new Documento();
		doc.setFechaCreacion(docBean.getFechaCreacion());
		doc.setDescripcion(docBean.getDescripcion());
		doc.setNombre(docBean.getNombre());
		SolicitudComunQuery solicitud = new SolicitudComunQuery();
		solicitud.setIdSolicitud(docBean.getIdSolicitud());
		SolicitudComun aux = solicitudComunDAO.searchUnique(solicitud);
		doc.setSolicitudComun(aux);
		doc.setTamano(docBean.getTamano());
		doc.setHash(docBean.getHashExtracto());
        
		String ubicacion = generarUbicacion(docBean.getIdSolicitud());
		docBean.setUbicacion(ubicacion);
		doc.setUbicacion(ubicacion);
		
		TipoDocumentoQuery tipoDocumentoQuery = new TipoDocumentoQuery();
		
		if(docBean.getIdTipoDocumento() != null && !"".equals(docBean.getIdTipoDocumento())){
			tipoDocumentoQuery.setId(Integer.parseInt(docBean.getIdTipoDocumento()));
		}else{
			tipoDocumentoQuery.setId(Constantes.DOCUMENTO_TIPO_FORMULARIO);
		}
		
		TipoDocumento tipoDocumento = tipoDocumentoDAO.searchUnique(tipoDocumentoQuery);
		doc.setTipoDocumento(tipoDocumento);
		
		long idDocumento = 0L;
		
		try{
			idDocumento = documentoDAO.insert(doc);
		}catch(Exception e){
			logger.error(" Error DocumentoManagerImpl - insertarDocumentoPDF."+ idDocumento,e);
			return null;
		}
		
		StringBuffer nombreAlfresco = new StringBuffer();
		
		if(docBean.getDsNombreDocumento() != null && !"".equals(docBean.getDsNombreDocumento())){
			
			// Formulario HTML
			if(Constantes.EXTENSION_FORMULARIO.equals(docBean.getExtension())){
				nombreAlfresco.append(docBean.getDsNombreDocumento())
					.append(".").append(docBean.getExtension());
			}else{
				nombreAlfresco.append(docBean.getDsNombreDocumento()).append("")
					.append(idDocumento).append(".").append(docBean.getExtension());
			}
		}else{
			nombreAlfresco.append(idDocumento).append(".").append(docBean.getExtension());
		}
	
		doc = documentoDAO.get(idDocumento);
		doc.setNombreAlfresco(nombreAlfresco.toString());
		docBean.setNombreAlfresco(nombreAlfresco.toString());
		documentoDAO.update(doc);

		SistemaFicheros ges= new SistemaFicheros();
		
		try{
			ges.insertarContenido(docBean, ruta);
		}catch(Exception e){
			logger.error(" Error DocumentoManagerImpl - sistema de ficheros."+ ges,e);
			try{
				documentoDAO.delete(idDocumento);
			}catch(Exception o){
				logger.error(" Error DocumentoManagerImpl - borrar documento."+ idDocumento,o);
				return null;				
			}
			return null;
		}
		return docBean;
	}	
	

	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.DocumentoManager#guardarDocumentoPDF(es.map.ipsg.bean.DocumentoBean)
	 */
	public Long guardarDocumentoPDF(DocumentoBean docBean) throws Exception {

		// Se comprueba si ya existe un un documento de tipo 4(JUSTIFICANTE_REGISTRO_PDF) asociado a la solicitud
		DocumentoQuery docQuery = new DocumentoQuery();
		
		SolicitudComunQuery solicitudQuery = new SolicitudComunQuery();
		solicitudQuery.setIdSolicitud(docBean.getIdSolicitud());
		docQuery.setSolicitudComun(solicitudQuery);
		
		TipoDocumentoQuery tipoDocQuery = new TipoDocumentoQuery();
		tipoDocQuery.setId(Constantes.TIPO_DOCUMENTO_JUSTIFICANTE_REGISTRO_PDF);
		docQuery.setTipoDocumento(tipoDocQuery);
		
		docQuery.setCalculateNumResults(true);
		SearchResult<Documento> auxDoc = documentoDAO.search(docQuery);
		if(auxDoc != null && auxDoc.getResults().size()>0 )
		{
			// ya existe un documento asociado a la solicitud, se actualizan todos a CADUCADOS
			logger.info("Existen documentos previos asociados a la solicitud ");
			
			TipoDocumento tipoDocCaducado = tipoDocumentoDAO.get(Constantes.TIPO_DOCUMENTO_JUSTIFICANTE_REGISTRO_PDF_CADUCADO);
			
			for(Documento u:auxDoc.getResults())
			{
				Long idReciente= u.getId();
				Documento docActual=documentoDAO.get(idReciente);
				docActual.setTipoDocumento(tipoDocCaducado);
				documentoDAO.update(docActual);
			}
	
		}	
		// Se inserta la nueva tupla con el documento actual	
		logger.info("Comienza guardarDocumentoPDF ");
		Documento doc = new Documento();
		doc.setFechaCreacion(docBean.getFechaCreacion());
		doc.setDescripcion(docBean.getDescripcion());
		doc.setNombre(docBean.getNombre());
		SolicitudComunQuery solicitud = new SolicitudComunQuery();
		solicitud.setIdSolicitud(docBean.getIdSolicitud());
		SolicitudComun aux = solicitudComunDAO.searchUnique(solicitud);
		doc.setSolicitudComun(aux);
		doc.setTamano(docBean.getTamano());

		TipoDocumento tipoDocumento = tipoDocumentoDAO.get(Constantes.TIPO_DOCUMENTO_JUSTIFICANTE_REGISTRO_PDF);
		doc.setTipoDocumento(tipoDocumento);
		
		long idDocumento =0L;

		
		try{
			idDocumento = documentoDAO.insert(doc);
		}catch(Exception e){
			logger.error(" Error DocumentoManagerImpl - guardarDocumentoPDF."+ idDocumento,e);
			return 0L;
		}
		String nombreAlfresco = "";
		
		if(docBean.getDsNombreDocumento() != null && !"".equals(docBean.getDsNombreDocumento())){
			if("html".equals(docBean.getExtension())){
				nombreAlfresco = docBean.getDsNombreDocumento()+"." + docBean.getExtension();
			}else{
				nombreAlfresco = docBean.getDsNombreDocumento()+ "" + idDocumento +"." + docBean.getExtension();
			}
		}else{
			nombreAlfresco = idDocumento + "." + docBean.getExtension();
		}
		
		doc = documentoDAO.get(idDocumento);
		doc.setNombreAlfresco(nombreAlfresco);
		docBean.setNombreAlfresco(nombreAlfresco);
		documentoDAO.update(doc);

		SistemaFicheros sistemaFicheros = new SistemaFicheros();
		
		try{
			sistemaFicheros.insertarContenido(docBean, null);
		}catch(Exception e){
			logger.error(" Error DocumentoManagerImpl - guardarDocumentoPDF-insertar contendo sistema ficheros."+ sistemaFicheros,e);
			documentoDAO.delete(idDocumento);
			return 0L;
		}
		return idDocumento;

	}	
	

	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.DocumentoManager#guardarDocumentoXML(es.map.ipsg.bean.DocumentoBean)
	 */
	public Long guardarDocumentoXML(DocumentoBean docBean) throws Exception {

		// Se comprueba si ya existe un un documento de tipo 5(JUSTIFICANTE_REGISTRO_XML) asociado a la solicitud
		DocumentoQuery docQuery = new DocumentoQuery();
		
		SolicitudComunQuery solicitudQuery = new SolicitudComunQuery();
		solicitudQuery.setIdSolicitud(docBean.getIdSolicitud());
		docQuery.setSolicitudComun(solicitudQuery);
		
		TipoDocumentoQuery tipoDocQuery = new TipoDocumentoQuery();
		tipoDocQuery.setId(Constantes.TIPO_DOCUMENTO_JUSTIFICANTE_REGISTRO_XML);
		docQuery.setTipoDocumento(tipoDocQuery);
		
		docQuery.setCalculateNumResults(true);
		SearchResult<Documento> auxDoc = documentoDAO.search(docQuery);
		if(auxDoc != null && auxDoc.getResults().size()>0 )
		{
			// ya existe un documento asociado a la solicitud, se actualizan todos a CADUCADOS
			logger.info("Existen documentos previos asociados a la solicitud ");
			
			TipoDocumento tipoDocCaducado = tipoDocumentoDAO.get(Constantes.TIPO_DOCUMENTO_JUSTIFICANTE_REGISTRO_XML_CADUCADO);
			
			for(Documento u:auxDoc.getResults())
			{
				Long idReciente= u.getId();
				Documento docActual=documentoDAO.get(idReciente);
				docActual.setTipoDocumento(tipoDocCaducado);
				documentoDAO.update(docActual);
			}
	
		}	
		
		// Se inserta la nueva tupla con el documento actual	
		logger.info("Comienza guardarDocumentoPDF ");
		Documento doc = new Documento();
		doc.setFechaCreacion(docBean.getFechaCreacion());
		doc.setDescripcion(docBean.getDescripcion());
		doc.setNombre(docBean.getNombre());
		SolicitudComunQuery solicitud = new SolicitudComunQuery();
		solicitud.setIdSolicitud(docBean.getIdSolicitud());
		SolicitudComun aux = solicitudComunDAO.searchUnique(solicitud);
		doc.setSolicitudComun(aux);
		doc.setTamano(docBean.getTamano());

		TipoDocumento tipoDocumento = tipoDocumentoDAO.get(Constantes.TIPO_DOCUMENTO_JUSTIFICANTE_REGISTRO_XML);
		doc.setTipoDocumento(tipoDocumento);
		
		long idDocumento =0L;

		
		try{
			idDocumento = documentoDAO.insert(doc);
		}catch(Exception e){
			logger.error(" Error DocumentoManagerImpl - guardarDocumentoXML."+ idDocumento,e);
			return 0L;
		}
		
		String nombreAlfresco = "";
		
		if(docBean.getDsNombreDocumento() != null && !"".equals(docBean.getDsNombreDocumento())){
			if("html".equals(docBean.getExtension())){
				nombreAlfresco = docBean.getDsNombreDocumento()+"." + docBean.getExtension();
			}else{
				nombreAlfresco = docBean.getDsNombreDocumento()+ "" + idDocumento +"." + docBean.getExtension();
			}
		}else{
			nombreAlfresco = idDocumento + "." + docBean.getExtension();
		}
		
		doc = documentoDAO.get(idDocumento);
		doc.setNombreAlfresco(nombreAlfresco);
		docBean.setNombreAlfresco(nombreAlfresco);
		documentoDAO.update(doc);

		SistemaFicheros sistemaFicheros = new SistemaFicheros();
		
		try{
			sistemaFicheros.insertarContenido(docBean, null);
		}catch(Exception e){
			logger.error(" Error DocumentoManagerImpl - guardarDocumentoXML  insertar documento sistema ficheros."+ sistemaFicheros,e);
			documentoDAO.delete(idDocumento);
			return 0L;
		}
		return idDocumento;

	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.DocumentoManager#generarJustificantePDF(es.map.ipsg.bean.DocumentoBean)
	 */
	public DocumentoBean generarJustificantePDF(DocumentoBean docBean) throws Exception {

		Documento doc = new Documento();
		doc.setFechaCreacion(docBean.getFechaCreacion());
		doc.setDescripcion(docBean.getDescripcion());
		doc.setNombre(docBean.getNombre());
		SolicitudComunQuery solicitud = new SolicitudComunQuery();
		solicitud.setIdSolicitud(docBean.getIdSolicitud());
		SolicitudComun aux = solicitudComunDAO.searchUnique(solicitud);
		doc.setSolicitudComun(aux);
		doc.setTamano(docBean.getTamano());

		TipoDocumentoQuery tipoDocumentoQuery = new TipoDocumentoQuery();
		
		if(docBean.getIdTipoDocumento() != null && !"".equals(docBean.getIdTipoDocumento())){
			tipoDocumentoQuery.setId(Integer.parseInt(docBean.getIdTipoDocumento()));
		}else{
			tipoDocumentoQuery.setId(Constantes.TIPO_DOCUMENTO_FORMULARIO);
		}
		
		TipoDocumento tipoDocumento = tipoDocumentoDAO.searchUnique(tipoDocumentoQuery);
		doc.setTipoDocumento(tipoDocumento);
		
		String nombreAlfresco = "";
		
		if(docBean.getDsNombreDocumento() != null && !"".equals(docBean.getDsNombreDocumento())){
			nombreAlfresco = docBean.getDsNombreDocumento()+ ""+"." + docBean.getExtension();	
		}else{
			nombreAlfresco = "Justificante" + "." + docBean.getExtension();
		}
		
		docBean.setNombreAlfresco(nombreAlfresco);

		return docBean;

	}	
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.DocumentoManager#insertarDocumento(es.map.ipsg.bean.DocumentoBean)
	 */
	public long insertarDocumento(DocumentoBean docBean) throws Exception {

		Documento doc = new Documento();
		doc.setFechaCreacion(docBean.getFechaCreacion());
		doc.setDescripcion(docBean.getDescripcion());
		doc.setNombre(docBean.getNombre());
		doc.setTamano(docBean.getTamano());
		doc.setUbicacion(docBean.getUbicacion());

		TipoDocumento tipDoc;
		
		if(docBean.getIdSolicitud()!=null){
			SolicitudComun sol;
			sol = solicitudComunDAO.get(docBean.getIdSolicitud());
			
			if(sol!=null && sol.getIdSolicitud()!=null){
				logger.info("Buscando solicitud: "+sol.getIdSolicitud());
			}
			
			doc.setSolicitudComun(sol);
		}

		Convocatoria con;
		con = convocatoriaDAO.get(docBean.getIdConvocatoria());
		
		if(con!=null && con.getId()!=null){
			logger.info("Buscando convocatoria: "+con.getId());
		}
		
		doc.setConvocatoria(con);

		long idDocumento=0L;
		
		String nombreAlfresco = docBean.getNombre()+".pdf";
		
		if(String.valueOf(Constantes.TIPO_DOCUMENTO_JUSTIFICANTE_REGISTRO_PDF).equals(docBean.getIdTipoDocumento())){
		  docBean.setIdTipoDocumento(docBean.getIdTipoDocumento());
		  tipDoc = tipoDocumentoDAO.get(Constantes.TIPO_DOCUMENTO_JUSTIFICANTE_REGISTRO_PDF);
		  
		  if(tipDoc!=null && tipDoc.getId()!=null){
				logger.info("Buscando tipo documento 4: "+tipDoc.getId());
		  }
		  
		  doc.setTipoDocumento(tipDoc);
		  docBean.setTipoDocumento(tipDoc);
		}else{
			tipDoc = tipoDocumentoDAO.get(Constantes.TIPO_DOCUMENTO_ANEXO_CONVOCATORIA);
			
			if(tipDoc!=null && tipDoc.getId()!=null){
				logger.info("Buscando tipo documento 2: "+tipDoc.getId());
			}
			
			doc.setTipoDocumento(tipDoc);
		}
		
		idDocumento = documentoDAO.insert(doc);
		
		if(!String.valueOf(Constantes.TIPO_DOCUMENTO_JUSTIFICANTE_REGISTRO_PDF).equals(docBean.getIdTipoDocumento())){
			nombreAlfresco =  idDocumento + "." + docBean.getExtension();
		}
		
		doc = documentoDAO.get(idDocumento);
		doc.setNombreAlfresco(nombreAlfresco);
		docBean.setNombreAlfresco(nombreAlfresco);
		documentoDAO.update(doc);

		SistemaFicheros sistemaFicheros = new SistemaFicheros();
		sistemaFicheros .insertarContenido(docBean, null);
		
		return idDocumento;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.DocumentoManager#insertarDocumentoRegistro(es.map.ipsg.bean.DocumentoBean)
	 */
	public long insertarDocumentoRegistro(DocumentoBean docBean) throws Exception {

		Documento doc = new Documento();
		doc.setFechaCreacion(docBean.getFechaCreacion());
		doc.setDescripcion(docBean.getDescripcion());
		doc.setNombre(docBean.getNombre());
		SolicitudComunQuery solicitud = new SolicitudComunQuery();
		solicitud.setIdSolicitud(docBean.getIdSolicitud());
		SolicitudComun aux = solicitudComunDAO.searchUnique(solicitud);
		doc.setSolicitudComun(aux);
		doc.setTamano(docBean.getTamano());
		doc.setHash(docBean.getHashExtracto());
		
		//Buscar convocatoria
		Convocatoria convocatoria;
		ConvocatoriaQuery convocatoriaQuery = new ConvocatoriaQuery();
		convocatoriaQuery.setId(docBean.getIdConvocatoria());
		convocatoria = convocatoriasManager.buscarConvocatoria(convocatoriaQuery);
		doc.setConvocatoria(convocatoria);
		
		Calendar cal = Calendar.getInstance();
        int mes = cal.get(Calendar.MONTH)+1;
        final String separador = File.separator;
		
        StringBuffer rutaDocumento = new StringBuffer();
		rutaDocumento
			.append(cal.get(Calendar.YEAR)).append(separador)
			.append(mes).append(separador)
			.append(cal.get(Calendar.DAY_OF_MONTH)).append(separador)
			.append(cal.get(Calendar.HOUR_OF_DAY)).append(separador)
			.append(docBean.getIdSolicitud()).append(separador);

		docBean.setUbicacion(rutaDocumento.toString());
		doc.setUbicacion(rutaDocumento.toString());
		
			TipoDocumentoQuery tipoDocumentoQuery = new TipoDocumentoQuery();
			if(docBean.getIdTipoDocumento() != null && !"".equals(docBean.getIdTipoDocumento())){
				tipoDocumentoQuery.setId(Integer.parseInt(docBean.getIdTipoDocumento()));
			}else{
				tipoDocumentoQuery.setId(Constantes.DOCUMENTO_TIPO_FORMULARIO);
			}
			TipoDocumento tipoDocumento = tipoDocumentoDAO.searchUnique(tipoDocumentoQuery);
			doc.setTipoDocumento(tipoDocumento);
		
		long idDocumento = 0L;
		try{
			idDocumento = documentoDAO.insert(doc);
		}catch(Exception e){
			logger.error("insertarDocumento - Error:",e);
			
			return 0;
		}
		StringBuffer nombreAlfresco = new StringBuffer();
		
		if(docBean.getDsNombreDocumento() != null && !"".equals(docBean.getDsNombreDocumento())){
			
			// Formulario HTML
			if(Constantes.EXTENSION_FORMULARIO.equals(docBean.getExtension())){
				nombreAlfresco.append(docBean.getDsNombreDocumento())
					.append(".").append(docBean.getExtension());
			}else{
				nombreAlfresco.append(docBean.getDsNombreDocumento()).append("")
					.append(idDocumento).append(".").append(docBean.getExtension());
			}
		}else{
			nombreAlfresco.append(idDocumento).append(".").append(docBean.getExtension());
		}

		
		doc = documentoDAO.get(idDocumento);
		doc.setNombreAlfresco(nombreAlfresco.toString());
		docBean.setNombreAlfresco(nombreAlfresco.toString());
		documentoDAO.update(doc);

		SistemaFicheros ges= new SistemaFicheros();
		try{
			ges.insertarContenido(docBean, null);
		}catch(Exception e){
			logger.error("Insertar contenido - Error:",e);
			try{
				documentoDAO.delete(idDocumento);
			}catch(Exception o){
				logger.error("delete - Error:",o);
				return 0;				
			}
			return 0;
		}
		return idDocumento;

	}
	
	/**
	 * Metodo que inserta el justificante de registro generado en IPSG.
	 *
	 * @param docBean el doc bean
	 * @param ruta el ruta
	 * @return el long
	 * @throws Exception el exception
	 */
	public long insertarDocumentoRec(DocumentoBean docBean, String ruta) throws Exception {

		Documento doc = new Documento();
		doc.setFechaCreacion(docBean.getFechaCreacion());
		doc.setDescripcion(docBean.getDescripcion());
		doc.setNombre(docBean.getNombre());
		SolicitudComunQuery solicitud = new SolicitudComunQuery();
		solicitud.setIdSolicitud(docBean.getIdSolicitud());
		SolicitudComun aux = solicitudComunDAO.searchUnique(solicitud);
		doc.setSolicitudComun(aux);
		doc.setTamano(docBean.getTamano());

		
		TipoDocumentoQuery tipoDocumentoQuery = new TipoDocumentoQuery();
		
		if(docBean.getIdTipoDocumento() != null && !"".equals(docBean.getIdTipoDocumento())){
			tipoDocumentoQuery.setId(Integer.parseInt(docBean.getIdTipoDocumento()));
		}else{
			tipoDocumentoQuery.setId(Constantes.DOCUMENTO_TIPO_FORMULARIO);
		}
		
		TipoDocumento tipoDocumento = tipoDocumentoDAO.searchUnique(tipoDocumentoQuery);
		doc.setTipoDocumento(tipoDocumento);
		
		long idDocumento = 0L;
		try{
			idDocumento = documentoDAO.insert(doc);
		}catch(Exception e){
			logger.error(" Error DocumentoManagerImpl - insertarDocumentoRec."+ idDocumento,e);
			return 0;
		}
		String nombreAlfresco = "";
		
		if(docBean.getDsNombreDocumento() != null && !"".equals(docBean.getDsNombreDocumento())){
			if("html".equals(docBean.getExtension())){
				nombreAlfresco = docBean.getDsNombreDocumento()+"." + docBean.getExtension();
			}else{
				nombreAlfresco = docBean.getDsNombreDocumento()+ "" + idDocumento +"." + docBean.getExtension();
			}
		}else{
			nombreAlfresco = idDocumento + "." + docBean.getExtension();
		}
		doc = documentoDAO.get(idDocumento);
		doc.setNombreAlfresco(nombreAlfresco);
		docBean.setNombreAlfresco(nombreAlfresco);
		documentoDAO.update(doc);

		SistemaFicheros sistemaFicheros = new SistemaFicheros();
		
		try{
			sistemaFicheros .insertarContenido(docBean, ruta);
			// CORREGIR ENVIO RUTA INICIAL
		}catch(Exception e){
			logger.error(" Error DocumentoManagerImpl - insertarDocumentoRec- insertar contenido en sistema de ficheros",e);
			documentoDAO.delete(idDocumento);
			return 0;
		}
		return idDocumento;

	}
	
	/**
	 * Metodo para adjuntar documentos en solicitudes presenciales. - OK
	 *
	 * @param docBean el doc bean
	 * @return el long
	 * @throws Exception el exception
	 */
	public long insertarDocumentoAsociado(DocumentoBean docBean) throws Exception {

		Documento doc = new Documento();
		doc.setFechaCreacion(docBean.getFechaCreacion());
		doc.setDescripcion(docBean.getDescripcion());
		doc.setNombre(docBean.getNombre());
		doc.setTamano(docBean.getTamano());
		doc.setTipoDocumento(docBean.getTipoDocumento());
		
		SolicitudComun sol;
		sol = solicitudComunDAO.get(docBean.getIdSolicitud());
		doc.setSolicitudComun(sol);

		Convocatoria con;
		con = convocatoriaDAO.get(docBean.getIdConvocatoria());
		doc.setConvocatoria(con);

		long idDocumento = documentoDAO.insert(doc);

		String nombreAlfresco = idDocumento + "." + docBean.getExtension();

		doc = documentoDAO.get(idDocumento);
		doc.setNombreAlfresco(nombreAlfresco);
		docBean.setNombreAlfresco(nombreAlfresco);
		
		String ubicacion = generarUbicacion(doc.getSolicitudComun().getIdSolicitud());
		doc.setUbicacion(ubicacion);
		docBean.setUbicacion(ubicacion);
		
		documentoDAO.update(doc);

		SistemaFicheros sistemaFicheros = new SistemaFicheros();
		sistemaFicheros .insertarContenido(docBean, null);

		return idDocumento;

	}	
	

	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.DocumentoManager#insertarDocumentoAdjunto(es.map.ipsg.bean.DocumentoBean)
	 */
	public long insertarDocumentoAdjunto(DocumentoBean docBean) throws Exception {

		Documento doc = new Documento();
		doc.setFechaCreacion(docBean.getFechaCreacion());
		doc.setDescripcion(docBean.getDescripcion());
		doc.setNombre(docBean.getNombre());
		doc.setTamano(docBean.getTamano());

		TipoDocumento tipDoc;
		tipDoc = tipoDocumentoDAO
				.get(Constantes.TIPO_DOCUMENTO_ADJUNTO);
		doc.setTipoDocumento(tipDoc);

		long idDocumento = documentoDAO.insert(doc);

		String nombreAlfresco = idDocumento + "." + docBean.getExtension();

		doc = documentoDAO.get(idDocumento);
		doc.setNombreAlfresco(nombreAlfresco);
		docBean.setNombreAlfresco(nombreAlfresco);
		documentoDAO.update(doc);

		SistemaFicheros sistemaFicheros = new SistemaFicheros();
		sistemaFicheros .insertarContenido(docBean, null);

		return idDocumento;
	}
	
	
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.DocumentoManager#insertarDocumentoCSVJusticia(es.map.ipsg.bean.DocumentoBean)
	 */
	/* 
	 * Metodo para guardar el documento CSV de una empresa
	 * Se almacena en filesystem y se registra en tabla documentos
	 * @param docBean - el documento que viene del Action 
	 * @return  - el id que tendra en la tabla documentos el fichero csv
	 * 
	 */
	public DocumentoBean insertarDocumentoCSVJusticia(DocumentoBean docBean) throws Exception {
		
		// inserto en filesystem el fichero csv y recupero el file generado
		SistemaFicheros sistemaFicheros = new SistemaFicheros();
		File fileCSVOriginal = sistemaFicheros.insertarContenidoCSV(docBean,sistemaFicheros.getUrlFinal());
		docBean.setFichero(fileCSVOriginal);
		
					
		// registro en bb.dd el guardado del csv anterior en la tabla documentos
		Documento doc = new Documento();
		
		doc.setNombre(docBean.getNombre());
		doc.setFechaCreacion(docBean.getFechaCreacion());
		TipoDocumento tipoDocumento = tipoDocumentoDAO.get(Integer.parseInt(docBean.getIdTipoDocumento()));
		doc.setTipoDocumento(tipoDocumento);
		doc.setDescripcion(Constantes.DESCRIPCION_CSV_JUSTICIA_ORIGINAL);
		doc.setTamano(docBean.getTamano());
		doc.setNombreAlfresco(fileCSVOriginal.getName());
		doc.setUbicacion(docBean.getUbicacion());
		
		long idDocumentoOriginal = documentoDAO.insert(doc);
		docBean.setId(idDocumentoOriginal);
		
		return docBean;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.DocumentoManager#modificarDocumentoCSVJusticia(es.map.ipsg.bean.DocumentoBean)
	 */
	/* 
	 * Metodo para modificar el documento CSV de una empresa
	 * 
	 * @param docBean - el documento que viene del Action 
	 * @return idDocumento - el id que tendra en la tabla documentos el fichero csv
	 * 
	 */
	public DocumentoBean modificarDocumentoCSVJusticia(DocumentoBean docBean) throws Exception {
		
		// inserto en filesystem el fichero csv y recupero el file generado
		SistemaFicheros sistemaFicheros = new SistemaFicheros();
		File fileCSVModificado = sistemaFicheros.modificarContenidoCSV(docBean);
		docBean.setFichero(fileCSVModificado);
		docBean.setNombreAlfresco(fileCSVModificado.getName());
								
		// registro en bb.dd el guardado del csv anterior en la tabla documentos
		Documento doc = new Documento();
		
		doc.setNombre(docBean.getNombre());
		doc.setFechaCreacion(docBean.getFechaCreacion());
		TipoDocumento tipoDocumento = tipoDocumentoDAO.get(Integer.parseInt(docBean.getIdTipoDocumento()));
		doc.setTipoDocumento(tipoDocumento);
		doc.setDescripcion(Constantes.DESCRIPCION_CSV_JUSTICIA_MOFICADO);
		doc.setTamano(docBean.getTamano());
		doc.setNombreAlfresco(fileCSVModificado.getName());
		doc.setUbicacion(docBean.getUbicacion());
		
		long idDocumentoModificado = documentoDAO.insert(doc);
		docBean.setId(idDocumentoModificado);
		
		return docBean;
	}
	
	
	/**
	 * Metodo que inserta un documento de prueba en el Sistema de Ficheros de IPS.
	 *
	 * @param docBean el doc bean
	 * @return el string
	 * @throws Exception el exception
	 */
	public String insertarDocumentoPrueba(DocumentoBean docBean) throws Exception {

		Documento doc = new Documento();
		doc.setFechaCreacion(docBean.getFechaCreacion());
		doc.setDescripcion(docBean.getDescripcion());
		doc.setNombre(docBean.getNombre());
		doc.setTamano(docBean.getTamano());

		doc.setTipoDocumento(docBean.getTipoDocumento());

		long idDocumento = documentoDAO.insert(doc);

		String nombreAlfresco = idDocumento+ "." + docBean.getExtension();

		doc = documentoDAO.get(idDocumento);
		doc.setNombreAlfresco(nombreAlfresco);
		docBean.setNombreAlfresco(nombreAlfresco);
		String ubicacion = generarUbicacion(0);
		doc.setUbicacion(ubicacion);
		docBean.setUbicacion(ubicacion);
		documentoDAO.update(doc);

		SistemaFicheros sistemaFicheros = new SistemaFicheros();
		sistemaFicheros .insertarContenido(docBean, null);

		return ubicacion+docBean.getNombreAlfresco();

	}

	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.DocumentoManager#borrarDocumento(es.map.ipsg.bean.DocumentoBean)
	 */
	public void borrarDocumento(DocumentoBean doc) throws Exception {
		
		borrarContenidoGestorDocumental(doc);
		
		documentoDAO.delete(doc.getId());
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.DocumentoManager#borrarDocumentoLecturaEscritura(es.map.ipsg.bean.DocumentoBean)
	 */
	public void borrarDocumentoLecturaEscritura(DocumentoBean doc) throws Exception {
		
//		borrarContenidoLecturaEscrituraGestorDocumental(doc);
		
		documentoDAO.delete(doc.getId());
	}
	
	
	/**
	 * Borrar contenido gestor documental.
	 *
	 * @param doc el doc
	 * @throws Exception el exception
	 */
	public void borrarContenidoGestorDocumental(DocumentoBean doc) throws Exception {
		
		SistemaFicheros sistema = new SistemaFicheros();
		
		try {
			sistema.borrarContenido(doc);
		} catch (Exception e) {
			logger.equals("Error borrando documento");
			logger.error(" Error DocumentoManagerImpl - borrarContenidoGestorDocumental.",e);
		}
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.DocumentoManager#borrarContenidoLecturaEscrituraGestorDocumental(es.map.ipsg.bean.DocumentoBean)
	 */
	public void borrarContenidoLecturaEscrituraGestorDocumental(DocumentoBean doc) throws Exception {
		
		SistemaFicheros sistema = new SistemaFicheros();
		
		try {
			sistema.borrarContenidoLecturaEscritura(doc);
		} catch (Exception e) {
			logger.equals("Error borrando documento");
			logger.error(" Error DocumentoManagerImpl - borrarContenidoGestorDocumental.",e);
		}
	}
	

	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.DocumentoManager#eliminarDocumentoById(java.lang.Long)
	 */
	public void eliminarDocumentoById(Long idDocumento) {
		if(idDocumento>0)
			documentoDAO.delete(idDocumento);
	}

	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.DocumentoManager#buscarDocumentos(es.map.ips.model.DocumentoQuery)
	 */
	public Set<DocumentoBean> buscarDocumentos(DocumentoQuery documentoQuery){
		
		Set<DocumentoBean> listaDocumentos = new HashSet<DocumentoBean>();
		SearchResult<Documento> documento = buscarDocumento(documentoQuery);
		
		for (int i = 0; i < documento.getResults().size(); i++) {
			DocumentoBean aux;
			aux = toDocumentoBean(documento.getResults().get(i));
			if (aux != null) {
				listaDocumentos.add(aux);
			}
		}
		
		return listaDocumentos;
	}

	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.DocumentoManager#modificarDocumento(es.map.ipsg.bean.DocumentoBean)
	 */
	public void modificarDocumento (DocumentoBean  documentoBean){
		
		Documento documento =  toDocumento(documentoBean);
		documentoDAO.update(documento);
	}
	
	/**
	 * Metodo de transformacion de bean a entidad.
	 *
	 * @param documentoBean el documento bean
	 * @return el documento
	 */
	private Documento toDocumento(DocumentoBean documentoBean) {
		
		Documento documento = new Documento();
		
		documento.setId(documentoBean.getId());
		documento.setNombre(documentoBean.getNombre());
		documento.setFechaCreacion(documentoBean.getFechaCreacion());
		
		if(documentoBean.getTipoDocumento()!=null){
			documento.setTipoDocumento(documentoBean.getTipoDocumento());
		}
		
		documento.setDescripcion(documentoBean.getDescripcion());
		documento.setTamano(documentoBean.getTamano());
		documento.setNombreAlfresco(documentoBean.getNombreAlfresco());
		
		if(documentoBean.getSolicitud()!=null){
			documento.setSolicitudComun(documentoBean.getSolicitud());
		}
		
		if(documentoBean.getConvocatoria()!=null){
			documento.setConvocatoria(documentoBean.getConvocatoria());
		}
		
		if(documentoBean.getCorreoElectronico()!=null){
			documento.setCorreoElectronico(documentoBean.getCorreoElectronico());
		}
		
		if(documentoBean.getHashExtracto()!=null){
			documento.setHash(documentoBean.getHashExtracto());
		}
		
		if(documentoBean.getUbicacion()!=null){
			documento.setUbicacion(documentoBean.getUbicacion());
		}
		
		return documento;
	}
	
	
	/**
	 * Metodo que copia todos los ficheros asociados a una solicitud REGISTRADA o 
	 * a una convocatoria FINALIZADA en la ubicacion definitiva con permisos de solo lectura.
	 * Finalmente, si el directorio esta vacio, lo borra.
	 *
	 * @param listaDocumentos el lista documentos
	 * @param rutaEscritura el ruta escritura
	 * @return boolean
	 */
	public boolean copiarFicheros(ArrayList<DocumentoBean> listaDocumentos, String rutaEscritura){

		boolean resultado = false;
		
		// Copiar cada uno de ellos en la ruta definitva
		if(null!=listaDocumentos && listaDocumentos.size()>0){
			
			SistemaFicheros sistemaFicheros = new SistemaFicheros();
			
			for (DocumentoBean documentoBean : listaDocumentos) {
				StringBuffer rutaFichero = new StringBuffer();
				rutaFichero.append(rutaEscritura).append(documentoBean.getUbicacion()).append(documentoBean.getNombreAlfresco());
				
				File fichero = new File(rutaFichero.toString());
				
				if(fichero.exists()){
					if(sistemaFicheros.copiarFichero(documentoBean.getUbicacion(), fichero)){
						// Si el copiado es OK, se borra el fichero
						sistemaFicheros.borrarContenido(documentoBean);
						resultado = true;
					}else{
						logger.error("No se ha copiado el fichero "+documentoBean.getNombreAlfresco());
					}
					
					// Finalmente, si el directorio está vacío, se elimina tambien.
					try{
						File directorio = new File(rutaEscritura + documentoBean.getUbicacion());
						File lista[] = directorio.listFiles();
						if(lista.length == 0){
							directorio.delete();
						}
					}catch (Exception e){
						logger.error(" Error DocumentoManagerImpl-Se ha producido un error al borrar el directorio.",e);					
					}
				}
			}
		}else{
			logger.error("No se ha localizado ningun documento en "+rutaEscritura);
		}
			
		return resultado;
	}
	
	/**
	 * Metodo que genera la ubicacion del documento.
	 *
	 * @param idSolicitud el id solicitud
	 * @return el string
	 */
	private String generarUbicacion(long idSolicitud){
        
		Calendar cal = Calendar.getInstance();
        int mes = cal.get(Calendar.MONTH)+1;
        final String separador = File.separator;
		
		StringBuffer rutaDocumento = new StringBuffer();
		rutaDocumento
			.append(cal.get(Calendar.YEAR)).append(separador)
			.append(mes).append(separador)
			.append(cal.get(Calendar.DAY_OF_MONTH)).append(separador)
			.append(cal.get(Calendar.HOUR_OF_DAY)).append(separador)
			.append(idSolicitud).append(separador);
		
		return rutaDocumento.toString();
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.DocumentoManager#generarJustificante(java.lang.String)
	 */
	public GenerarJustificanteBean generarJustificante(String idRegistro) throws Exception {
		Properties properties;
		properties = (Properties) ApplicationContextProvider.getInstance().getBean("propertiesBean");
		GenerarJustificanteBean generarJustificanteBean = new GenerarJustificanteBean();
		
		ResourceBundle RESOURCE_MESSAGE_ES = ResourceBundle.getBundle("MessageResources");
		
		// Crear RegistroType (para uso posterior)	
		RegistroType registroType =  new RegistroType();
		registroType.setCdAsunto(properties.getProperty("cdAsunto"));
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
		
		// Se evalua el tipo de documento de identidad del solicitante
		String tipoDocIdentidad = "";

		if(Util.esNie(solicitudBean.getNif())){
			tipoDocIdentidad = Constantes.INTERESADO_TIPO_DOCUMENTO_EXTRANJERO;
		}else{
			tipoDocIdentidad = Constantes.INTERESADO_TIPO_DOCUMENTO_NACIONAL;
		}
		
		interesadoAux.setCdTipoDocIndentificativoInteresado(tipoDocIdentidad);
		interesadoAux.setTlNumDocIdentificativoInteresado(solicitudBean.getNif());
		interesadoAux.setTlNombreInteresado(solicitudBean.getNombre());
		
		if(solicitudBean.getPrimerApellido() != null){
			interesadoAux.setTlApellido1Interesado(solicitudBean.getPrimerApellido());
		}else{
			interesadoAux.setTlApellido1Interesado("");
		}
		if(solicitudBean.getSegundoApellido() != null){
			interesadoAux.setTlApellido2Interesado(solicitudBean.getSegundoApellido());
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
		String telefonoAux;
		try{
			int numtelefonoAux = solicitudBean.getTelefono().lastIndexOf("/");
			telefonoAux = solicitudBean.getTelefono().substring(0,numtelefonoAux);
		}catch(Exception e){
			telefonoAux = solicitudBean.getTelefono();
		}

		interesadoAux.setTlTelefonoContactoInteresado(telefonoAux);
		interesadoAux.setTlCorreoElectronicoInteresado(solicitudBean.getEmail());
		Pais pais=solicitudBean.getPais();
		
		// Resolución incidencia país no informado.
		String codigoPais = Constantes.CODIGO_PAIS_DEFECTO;
		if(pais!=null){
			codigoPais = pais.getCodigo();
		}
		
		interesadoAux.setCdPaisInteresado(codigoPais);
		Provincia provincia=solicitudBean.getProvinciaByIdProvinciaDomicilio();
		String codigoProvincia = "";
		
		if(null!=provincia && null!=provincia.getCodigo()){
			codigoProvincia = provincia.getCodigo();
		}
		
		interesadoAux.setCdProvinciaInteresado(codigoProvincia);
		
		if(null!=solicitudBean.getEmail()){
			interesadoAux.setTlDEHInteresado(solicitudBean.getEmail());
		}else{
			interesadoAux.setTlDEHInteresado("");
		}
		
		logger.info("Interesado Creado");
		interesadoType[0] = interesadoAux;
		registroType.setInteresados(interesadoType);
		logger.info("interesadoType y registroType Creado");

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
		String entorno = Constantes.ENTORNO_SOLICITUDES;
		DocumentoType registroDocumentoType[] = new DocumentoType[numRegistros];

		Calendar cal1 = Calendar.getInstance();
		StringBuffer fecha = new StringBuffer();
		fecha.append(cal1.get(Calendar.DATE)).append("/").append(cal1.get(Calendar.MONTH)).append("/")
			.append(cal1.get(Calendar.YEAR)).append(" ").append(cal1.get(Calendar.HOUR)).append(":")
			.append(cal1.get(Calendar.MINUTE)).append(":").append(cal1.get(Calendar.SECOND)).append("");

		SHA0 hash = new SHA0();

		//Agregamos al Registro los documentos que se adjuntaron a la solicitud
		//Para cada documento adjunto, se obtiene la firma generada en el intento de registro
		for(int x=0; x<documentosAdjuntos.size(); x++){
			byte[] salida;
			String idAlfresco = null;
			DocumentoType registroDocumentoAux = new DocumentoType();
			String nombreAlfresco = documentosAdjuntos.get(x).getNombreAlfresco();
			logger.info("NombreDocumento: "+nombreAlfresco);

			try{			
				registroDocumentoAux.setDsNombre(documentosAdjuntos.get(x).getNombreAlfresco());				
				
				salida = this.obtenerContenidoDocumento(documentosAdjuntos.get(x));
				
				String extension = FilenameUtils.getExtension(documentosAdjuntos.get(x).getNombreAlfresco());
				
				if(Constantes.EXTENSION_FORMULARIO.equals(extension)){
					registroDocumentoAux.setDsNombre(documentosAdjuntos.get(x).getNombreAlfresco());
					String nombreHash=documentosAdjuntos.get(x).getNombreAlfresco();
					byte[] byteHash = nombreHash.getBytes();
					String hashFile="";
					try {
						hashFile=hash.getHash(byteHash);
					} catch (NoSuchAlgorithmException e1) {
						e1.printStackTrace();
					}
					byte[] codigo64 = hashFile.getBytes();
					hashFile = IpsUtils.encodeBase64(codigo64);
					registroDocumentoAux.setBlHash(hashFile);
				}else{
					registroDocumentoAux.setDsNombre(documentosAdjuntos.get(x).getNombre()+"."+extension);
					registroDocumentoAux.setCdTipo("01");
					String nombreHash=documentosAdjuntos.get(x).getNombreAlfresco();
					byte[] byteHash = nombreHash.getBytes();
					String hashFile="";
					try {
						hashFile=hash.getHash(byteHash);
					} catch (NoSuchAlgorithmException e1) {
						e1.printStackTrace();
					}
					byte[] codigo64 = hashFile.getBytes();
					hashFile = IpsUtils.encodeBase64(codigo64);
					registroDocumentoAux.setBlHash(hashFile);
				}
				registroDocumentoAux.setBlFirma(UtilesIPSG.encodeBase64(salida));
				registroDocumentoAux.setCdValidez("04");
				registroDocumentoAux.setCdTipo("01");
				registroDocumentoAux.setBlTimeStamp(fecha.toString());
				registroDocumentoAux.setBlDocumento(documentosAdjuntos.get(x).getNombreAlfresco());
				registroDocumentoAux.setCdFirmado("1");
				registroDocumentoAux.setBlValidacionOCSP("s");
				registroDocumentoAux.setDsTipoMIME("s");
				registroDocumentoAux.setBlPKCertificado("");
				registroDocumentoAux.setTlObservaciones("");
				registroDocumentoType[x] = registroDocumentoAux;
				docs[x] = new OctetStream(salida);

			}catch(Exception e){
				logger.error("Error recuperando el documento de firma de la solicitud: "+nombreAlfresco, e);
				e.printStackTrace();
			}
		}

		registroType.setDocumentos(registroDocumentoType);
		logger.info("RegistrosAñadidos");
		JustificanteType justificanteType = new JustificanteType();

		// Se busca el registro anterior de la solicitud
		RegistroSolicitudQuery registroSolicitudQuery = new RegistroSolicitudQuery();
		SolicitudComunQuery solicitud = new SolicitudComunQuery();
		solicitud.setIdSolicitud(Long.parseLong(idRegistro));
		
		// Obtenemos el consentimiento a través del idSolicitud

		registroSolicitudQuery.setSolicitudComun(solicitud);

		// Datos de cabecera
		registroSolicitudQuery.setResultado(Constantes.RESULTADO_OK);
		RegistroSolicitudBean regSolicitud = registroSolicitudManager.buscarRegistroSolicitudByIdSolicitudCrearJustificante(registroSolicitudQuery);
		if(regSolicitud==null){
			generarJustificanteBean.setMsgError(properties.getProperty("field.errorGeneracionSol"));
			return generarJustificanteBean;
		}

		justificanteType.setNmRegistro(regSolicitud.getNumeroRegistro());
		justificanteType.setCdOficinaOrigen(properties.getProperty("cdOrOrigen"));
		justificanteType.setCdOficinaDestino(properties.getProperty("cdOrDestino"));
		justificanteType.setDsOficinaOrigen(properties.getProperty("dsOrOrigen"));
		justificanteType.setDsOficinaDestino(properties.getProperty("dsOrDestino"));
		justificanteType.setCdTipoRegistro(properties.getProperty("cdTipoAsiento"));
		justificanteType.setDsAsunto(properties.getProperty("dsAsunto"));

		Date fechaIntento = regSolicitud.getFechaIntento();
		justificanteType.setFeRegistro(fechaIntento.toString());

		// Se obtiene la firma del REC (Firma del justificante en el PDF)
		DocumentoQuery docQuery = new DocumentoQuery();
		SolicitudComunQuery solQuery = new SolicitudComunQuery();
		solQuery.setIdSolicitud(Long.parseLong(idSolicitud));
		docQuery.setSolicitudComun(solQuery);

		TipoDocumentoQuery tipoDocQuery = new TipoDocumentoQuery();
		tipoDocQuery.setId(Constantes.TIPO_DOCUMENTO_FIRMA_REC);
		docQuery.setTipoDocumento(tipoDocQuery);
		docQuery.addOrder(DocumentoQuery.FECHACREACION,OrderType.DESC);

		//Creamos el justificante PDF y XML
		RegistroSolicitudJustificanteBean registroSolicitudJustificante;
		registroSolicitudJustificante = this.toRegistroCompleto(idRegistro);

		/*-----------------------------SE CREA EL PDF---------------------------------------*/
		JRBeanCollectionDataSource jrBeanCollectionDataSource = null;

		File sourceFile = null;
		String rutaReporte = properties.getProperty("jasper.rutaReport");

		//Creamos los array de bytes de cada documento
		byte[] pdfasbytes = null;

		//Creamos los arrayList para los PDF's
		ArrayList<JustificanteBean> aListPdfPrueba = new ArrayList<JustificanteBean>();

		//Creamos los jasper
		Jasper jasperJustificante = new Jasper();

		//Enruta los jasper	
		String ficheroPdf = properties.getProperty("jasper.JustificanteR");

		//Creamos el bean que guarda los datos del primer jasper
		JustificanteBean justificanteBean;
		
		justificanteBean = this.toJustificanteBean(justificanteType,registroType,registroSolicitudJustificante);
		
		// Logo y encabezado por defecto: 790001
		justificanteBean.setRutaLogo(properties.getProperty("jasper.rutaLogo"));
		justificanteBean.setEncabezado(Constantes.ENCABEZADO_GENERICO);
		
		if(null != solicitudBean.getNumModelo() && solicitudBean.getNumModelo().equals(Constantes.MODELO79007)){
			justificanteBean.setRutaLogo(properties.getProperty("jasper.rutaLogo.justicia"));
			justificanteBean.setEncabezado(Constantes.ENCABEZADO_JUSTICIA);
		}
		
		justificanteBean.setRutaFicheros(rutaReporte);

		String nombreBarcodeAux = String.valueOf(System.currentTimeMillis());
		String extension = properties.getProperty("jasper.extension");
		String imagerNumeroJustificante = properties.getProperty("jasper.imagenNumeroJustificante");
		
		StringBuffer nombreBarcodeNJustificante = new StringBuffer();
		nombreBarcodeNJustificante.append(imagerNumeroJustificante).append("")
			.append(nombreBarcodeAux).append("").append(extension);
				
		justificanteBean.setNombreBarcodeNJustificante(nombreBarcodeNJustificante.toString());

		//Creamos el bean que guarda los datos del segundo jasper
		//Se añaden los bean a la lista
		aListPdfPrueba.add(justificanteBean);

		jrBeanCollectionDataSource = new JRBeanCollectionDataSource((Collection<JustificanteBean>) aListPdfPrueba);

		// Genero el pdf de prueba
		// Obtengo los ficheros implicados en la generacion del pdf
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
		
		byte [] docCsvSignature;
		byte [] docCSVFirmaSinbase64;
		try{
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
				
				byte[] docCsv = CSVInside.generarCopiaCSV(eSignature, ciudadanoSolicitud , properties, csvEeUtilService, codigoCSV, fechaRegistro);
				
				if (docCsv!=null) {
					// firma pdf
					docCsvSignature = signer.sign(docCsv, SignatureConstants.SIGN_ALGORITHM_SHA512WITHRSA, SignatureConstants.SIGN_MODE_IMPLICIT, certificatePrivateKey, null);
					logger.info("Copia pdf con csv incrustado finalizada");
					
/*					byte [] esignaturebase64 = Base64Coder.encodeBase64(docCsvSignature);
					
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
					
					docCSVFirmaSinbase64 = Base64Coder.decodeBase64(docCSVFirma.getBytes());
*/					
					// registro justificante con csv incrustrado en tabla documentos
					long idDocumento = this.registroPdfCsvDocumentos(docCsvSignature, idSolicitud, codigoCSV);
					
					if (idDocumento>0) {				
						// subida justificante a filesystem
						logger.info("Subiendo Justificante a Sistema de ficheros");
						int subida = this.subirDocumentoPdfCSV(docCsvSignature, idDocumento); 
						if(subida == 0){
							logger.info("error subiendo el documento con csv incrustrado al filesystem");
							generarJustificanteBean.setMsgError(properties.getProperty("field.errorCsv.filesystem"));
							return generarJustificanteBean;
						}
						logger.info("Subida PDF a Sistema de ficheros finalizada");
					} else {
						logger.info("error registrando el documento con csv incrustrado en la tabla documentos");
						generarJustificanteBean.setMsgError(properties.getProperty("field.errorCsv.documento"));
						return generarJustificanteBean;
					}
												
					// CSV STORAGE
					String guardado = CSVStorage.guardarDocumento(codigoCSV, docCsvSignature, properties);
					if (!guardado.equals(Constantes.RESULTADO_OK)){
						logger.info("Error almacenando el documento con csv incrustrado en csv storage");
						generarJustificanteBean.setMsgError(properties.getProperty("field.errorCsv.storage"));
						return generarJustificanteBean; 
					}
				} else {
					logger.info("Error obteniendo la copia con el codigo csv incrustrado");
					generarJustificanteBean.setMsgError(properties.getProperty("field.errorCsv.codigo"));
					return generarJustificanteBean;
				}
			} else {
				logger.info("Error obteniendo el codigo csv");
				generarJustificanteBean.setMsgError(properties.getProperty("field.errorCsv.copia"));
				return generarJustificanteBean;
			}
			
		}catch(SigningException e){
			e.printStackTrace();
			logger.error("Error: "+ e);
			generarJustificanteBean.setMsgError(properties.getProperty("field.errorGenerico"));
			return generarJustificanteBean;
			
		}
		
		generarJustificanteBean.setDocCSVFirmaSinbase64(docCsvSignature);
		generarJustificanteBean.setRegistroSolicitudJustificante(registroSolicitudJustificante);
		generarJustificanteBean.setJustificanteType(justificanteType);
		generarJustificanteBean.setRegistroType(registroType);
		generarJustificanteBean.setConsentimiento(justificanteBean.getConsentimiento());
		
		return generarJustificanteBean;
		
	}
	
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.DocumentoManager#generarJustificanteRegistro(java.lang.String)
	 */
	public GenerarJustificanteBean generarJustificanteRegistro(String idRegistro) throws Exception {
		Properties properties;
		properties = (Properties) ApplicationContextProvider.getInstance().getBean("propertiesBean");
		GenerarJustificanteBean generarJustificanteBean = new GenerarJustificanteBean();
		
		// Crear RegistroType (para uso posterior)	
		RegistroType registroType =  new RegistroType();
		registroType.setCdAsunto(properties.getProperty("cdAsunto"));
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
		
		// Se evalua el tipo de documento de identidad del solicitante
		String tipoDocIdentidad = "";

		if(Util.esNie(solicitudBean.getNif())){
			tipoDocIdentidad = Constantes.INTERESADO_TIPO_DOCUMENTO_EXTRANJERO;
		}else{
			tipoDocIdentidad = Constantes.INTERESADO_TIPO_DOCUMENTO_NACIONAL;
		}
		
		interesadoAux.setCdTipoDocIndentificativoInteresado(tipoDocIdentidad);
		interesadoAux.setTlNumDocIdentificativoInteresado(solicitudBean.getNif());
		interesadoAux.setTlNombreInteresado(solicitudBean.getNombre());
		
		if(solicitudBean.getPrimerApellido() != null){
			interesadoAux.setTlApellido1Interesado(solicitudBean.getPrimerApellido());
		}else{
			interesadoAux.setTlApellido1Interesado("");
		}
		if(solicitudBean.getSegundoApellido() != null){
			interesadoAux.setTlApellido2Interesado(solicitudBean.getSegundoApellido());
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
		String telefonoAux;
		try{
			int numtelefonoAux = solicitudBean.getTelefono().lastIndexOf("/");
			telefonoAux = solicitudBean.getTelefono().substring(0,numtelefonoAux);
		}catch(Exception e){
			telefonoAux = solicitudBean.getTelefono();
		}

		interesadoAux.setTlTelefonoContactoInteresado(telefonoAux);
		interesadoAux.setTlCorreoElectronicoInteresado(solicitudBean.getEmail());
		Pais pais=solicitudBean.getPais();
		
		// Resolución incidencia país no informado.
		String codigoPais = Constantes.CODIGO_PAIS_DEFECTO;
		if(pais!=null){
			codigoPais = pais.getCodigo();
		}
		
		interesadoAux.setCdPaisInteresado(codigoPais);
		Provincia provincia=solicitudBean.getProvinciaByIdProvinciaDomicilio();
		String codigoProvincia = "";
		
		if(null!=provincia && null!=provincia.getCodigo()){
			codigoProvincia = provincia.getCodigo();
		}
		
		interesadoAux.setCdProvinciaInteresado(codigoProvincia);
		
		if(null!=solicitudBean.getEmail()){
			interesadoAux.setTlDEHInteresado(solicitudBean.getEmail());
		}else{
			interesadoAux.setTlDEHInteresado("");
		}
		
		logger.info("Interesado Creado");
		interesadoType[0] = interesadoAux;
		registroType.setInteresados(interesadoType);
		logger.info("interesadoType y registroType Creado");

		//Declarar octetStream
		ArrayList<DocumentoBean> documentosAdjuntos;
		SolicitudComunQuery solicitudAux = new SolicitudComunQuery();
		solicitudAux.setIdSolicitud(Long.parseLong(idRegistro));

		TipoDocumentoQuery tipoDocumentoQuery = new TipoDocumentoQuery();
		tipoDocumentoQuery.addIdIn(Constantes.TIPO_DOCUMENTO_ANEXO_SOLICITUD);
		tipoDocumentoQuery.addIdIn(Constantes.TIPO_DOCUMENTO_JUSTIFICANTE_PAGO);
		tipoDocumentoQuery.addIdIn(Constantes.TIPO_DOCUMENTO_JUSTIFICANTE_REGISTRO_PDF);
		tipoDocumentoQuery.addIdIn(Constantes.TIPO_DOCUMENTO_JUSTIFICANTE_REGISTRO_XML);
		tipoDocumentoQuery.addIdIn(Constantes.TIPO_DOCUMENTO_JUSTIFICANTE_REDUCCION_TASA);

		DocumentoQuery documentoQuery = new DocumentoQuery();
		documentoQuery.setSolicitudComun(solicitudAux);
		documentoQuery.setTipoDocumento(tipoDocumentoQuery);

		//Busco los documentos anexados a la solicitud (1) y el formulario html (9)
		documentosAdjuntos=documentoManager.buscarDocumentosByIdSolicitud(documentoQuery);

		logger.info("Numero de documentos a registrar: " + documentosAdjuntos.size());
		int numRegistros = documentosAdjuntos.size();
		OctetStream[] docs = new OctetStream[numRegistros];
		String idSolicitud = idRegistro;
		String entorno = Constantes.ENTORNO_SOLICITUDES;
		DocumentoType registroDocumentoType[] = new DocumentoType[numRegistros];

		Calendar cal1 = Calendar.getInstance();
		StringBuffer fecha = new StringBuffer();
		fecha.append(cal1.get(Calendar.DATE)).append("/").append(cal1.get(Calendar.MONTH)).append("/")
			.append(cal1.get(Calendar.YEAR)).append(" ").append(cal1.get(Calendar.HOUR)).append(":")
			.append(cal1.get(Calendar.MINUTE)).append(":").append(cal1.get(Calendar.SECOND)).append("");

		SHA0 hash = new SHA0();

		//Agregamos al Registro los documentos que se adjuntaron a la solicitud
		//Para cada documento adjunto, se obtiene la firma generada en el intento de registro
		for(int x=0; x<documentosAdjuntos.size(); x++){
			byte[] salida;
			String idAlfresco = null;
			DocumentoType registroDocumentoAux = new DocumentoType();
			String nombreAlfresco = documentosAdjuntos.get(x).getNombreAlfresco();
			logger.info("NombreDocumento: "+nombreAlfresco);

			try{			
				registroDocumentoAux.setDsNombre(documentosAdjuntos.get(x).getNombreAlfresco());				
				
				salida = this.obtenerContenidoDocumento(documentosAdjuntos.get(x));
				
				String extension = FilenameUtils.getExtension(documentosAdjuntos.get(x).getNombreAlfresco());
				
				if(Constantes.EXTENSION_FORMULARIO.equals(extension)){
					registroDocumentoAux.setDsNombre(documentosAdjuntos.get(x).getNombreAlfresco());
					String nombreHash=documentosAdjuntos.get(x).getNombreAlfresco();
					byte[] byteHash = nombreHash.getBytes();
					String hashFile="";
					try {
						hashFile=hash.getHash(byteHash);
					} catch (NoSuchAlgorithmException e1) {
						e1.printStackTrace();
					}
					byte[] codigo64 = hashFile.getBytes();
					hashFile = IpsUtils.encodeBase64(codigo64);
					registroDocumentoAux.setBlHash(hashFile);
				}else{
					registroDocumentoAux.setDsNombre(documentosAdjuntos.get(x).getNombre()+"."+extension);
					registroDocumentoAux.setCdTipo("01");
					String nombreHash=documentosAdjuntos.get(x).getNombreAlfresco();
					byte[] byteHash = nombreHash.getBytes();
					String hashFile="";
					try {
						hashFile=hash.getHash(byteHash);
					} catch (NoSuchAlgorithmException e1) {
						e1.printStackTrace();
					}
					byte[] codigo64 = hashFile.getBytes();
					hashFile = IpsUtils.encodeBase64(codigo64);
					registroDocumentoAux.setBlHash(hashFile);
				}
				registroDocumentoAux.setBlFirma(UtilesIPSG.encodeBase64(salida));
				registroDocumentoAux.setCdValidez("04");
				registroDocumentoAux.setCdTipo("01");
				registroDocumentoAux.setBlTimeStamp(fecha.toString());
				registroDocumentoAux.setBlDocumento(documentosAdjuntos.get(x).getNombreAlfresco());
				registroDocumentoAux.setCdFirmado("1");
				registroDocumentoAux.setBlValidacionOCSP("s");
				registroDocumentoAux.setDsTipoMIME("s");
				registroDocumentoAux.setBlPKCertificado("");
				registroDocumentoAux.setTlObservaciones("");
				registroDocumentoType[x] = registroDocumentoAux;
				docs[x] = new OctetStream(salida);

			}catch(Exception e){
				logger.error("Error recuperando el documento de firma de la solicitud: "+nombreAlfresco, e);
				e.printStackTrace();
			}
		}

		registroType.setDocumentos(registroDocumentoType);
		logger.info("RegistrosAñadidos");
		JustificanteType justificanteType = new JustificanteType();

		// Se busca el registro anterior de la solicitud
		RegistroSolicitudQuery registroSolicitudQuery = new RegistroSolicitudQuery();
		SolicitudComunQuery solicitud = new SolicitudComunQuery();
		solicitud.setIdSolicitud(Long.parseLong(idRegistro));
		
		// Obtenemos el consentimiento a través del idSolicitud

		registroSolicitudQuery.setSolicitudComun(solicitud);

		// Datos de cabecera
		registroSolicitudQuery.setResultado(Constantes.RESULTADO_OK);
		RegistroSolicitudBean regSolicitud = registroSolicitudManager.buscarRegistroSolicitudByIdSolicitudCrearJustificante(registroSolicitudQuery);
		if(regSolicitud==null){
			generarJustificanteBean.setMsgError(properties.getProperty("field.errorGeneracionSol"));
			return generarJustificanteBean;
		}

		justificanteType.setNmRegistro(regSolicitud.getNumeroRegistro());
		justificanteType.setCdOficinaOrigen(properties.getProperty("cdOrOrigen"));
		justificanteType.setCdOficinaDestino(properties.getProperty("cdOrDestino"));
		justificanteType.setDsOficinaOrigen(properties.getProperty("dsOrOrigen"));
		justificanteType.setDsOficinaDestino(properties.getProperty("dsOrDestino"));
		justificanteType.setCdTipoRegistro(properties.getProperty("cdTipoAsiento"));
		justificanteType.setDsAsunto(properties.getProperty("dsAsunto"));

		Date fechaIntento = regSolicitud.getFechaIntento();
		justificanteType.setFeRegistro(fechaIntento.toString());

		// Se obtiene la firma del REC (Firma del justificante en el PDF)
		DocumentoQuery docQuery = new DocumentoQuery();
		SolicitudComunQuery solQuery = new SolicitudComunQuery();
		solQuery.setIdSolicitud(Long.parseLong(idSolicitud));
		docQuery.setSolicitudComun(solQuery);

		TipoDocumentoQuery tipoDocQuery = new TipoDocumentoQuery();
		tipoDocQuery.setId(Constantes.TIPO_DOCUMENTO_FIRMA_REC);
		docQuery.setTipoDocumento(tipoDocQuery);
		docQuery.addOrder(DocumentoQuery.FECHACREACION,OrderType.DESC);

		//Creamos el justificante PDF y XML
		RegistroSolicitudJustificanteBean registroSolicitudJustificante;
		registroSolicitudJustificante = this.toRegistroCompleto(idRegistro);

		/*-----------------------------SE CREA EL PDF---------------------------------------*/
		JRBeanCollectionDataSource jrBeanCollectionDataSource = null;

		File sourceFile = null;
		String rutaReporte = properties.getProperty("jasper.rutaReport");

		//Creamos los array de bytes de cada documento
		byte[] pdfasbytes = null;

		//Creamos los arrayList para los PDF's
		ArrayList<JustificanteBean> aListPdfPrueba = new ArrayList<JustificanteBean>();

		//Creamos los jasper
		Jasper jasperJustificante = new Jasper();

		//Enruta los jasper	
		String ficheroPdf = properties.getProperty("jasper.JustificanteR");

		//Creamos el bean que guarda los datos del primer jasper
		JustificanteBean justificanteBean;
		
		justificanteBean = this.toJustificanteRegistroBean(justificanteType,registroType,registroSolicitudJustificante);
		
		// Logo y encabezado por defecto: 790001
		justificanteBean.setRutaLogo(properties.getProperty("jasper.rutaLogo"));
		justificanteBean.setEncabezado(Constantes.ENCABEZADO_GENERICO);
		
		if(null != solicitudBean.getNumModelo() && solicitudBean.getNumModelo().equals(Constantes.MODELO79007)){
			justificanteBean.setRutaLogo(properties.getProperty("jasper.rutaLogo.justicia"));
			justificanteBean.setEncabezado(Constantes.ENCABEZADO_JUSTICIA);
		}
		
		justificanteBean.setRutaFicheros(rutaReporte);

		String nombreBarcodeAux = String.valueOf(System.currentTimeMillis());
		String extension = properties.getProperty("jasper.extension");
		String imagerNumeroJustificante = properties.getProperty("jasper.imagenNumeroJustificante");
		
		StringBuffer nombreBarcodeNJustificante = new StringBuffer();
		nombreBarcodeNJustificante.append(imagerNumeroJustificante).append("")
			.append(nombreBarcodeAux).append("").append(extension);
				
		justificanteBean.setNombreBarcodeNJustificante(nombreBarcodeNJustificante.toString());

		//Creamos el bean que guarda los datos del segundo jasper
		//Se añaden los bean a la lista
		aListPdfPrueba.add(justificanteBean);

		jrBeanCollectionDataSource = new JRBeanCollectionDataSource((Collection<JustificanteBean>) aListPdfPrueba);

		// Genero el pdf de prueba
		// Obtengo los ficheros implicados en la generacion del pdf
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
		
		byte [] docCsvSignature;
		byte [] docCSVFirmaSinbase64;
		try{
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
				
				byte[] docCsv = CSVInside.generarCopiaCSV(eSignature, ciudadanoSolicitud , properties, csvEeUtilService, codigoCSV, fechaRegistro);
				
				if (docCsv!=null) {
					// firma pdf
					docCsvSignature = signer.sign(docCsv, SignatureConstants.SIGN_ALGORITHM_SHA512WITHRSA, SignatureConstants.SIGN_MODE_IMPLICIT, certificatePrivateKey, null);
					logger.info("Copia pdf con csv incrustado finalizada");

/*					Se deja de utilizar de momento debido a que fallaba mucho esta conexion con la TSA	
					
					byte [] esignaturebase64 = Base64Coder.encodeBase64(docCsvSignature);
					
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
					
					docCSVFirmaSinbase64 = Base64Coder.decodeBase64(docCSVFirma.getBytes());
*/					
					// registro justificante con csv incrustrado en tabla documentos
					long idDocumento = this.registroPdfCsvDocumentos(docCsvSignature, idSolicitud, codigoCSV);
					
					if (idDocumento>0) {				
						// subida justificante a filesystem
						logger.info("Subiendo Justificante a Sistema de ficheros");
						int subida = this.subirDocumentoPdfCSV(docCsvSignature, idDocumento); 
						if(subida == 0){
							logger.info("error subiendo el documento con csv incrustrado al filesystem");
							generarJustificanteBean.setMsgError(properties.getProperty("field.errorCsv.filesystem"));
							return generarJustificanteBean;
						}
						logger.info("Subida PDF a Sistema de ficheros finalizada");
					} else {
						logger.info("error registrando el documento con csv incrustrado en la tabla documentos");
						generarJustificanteBean.setMsgError(properties.getProperty("field.errorCsv.documento"));
						return generarJustificanteBean;
					}
												
					// CSV STORAGE
					String guardado = CSVStorage.guardarDocumento(codigoCSV, docCsvSignature, properties);
					if (!guardado.equals(Constantes.RESULTADO_OK)){
						logger.info("Error almacenando el documento con csv incrustrado en csv storage");
						generarJustificanteBean.setMsgError(properties.getProperty("field.errorCsv.storage"));
						return generarJustificanteBean; 
					}
				} else {
					logger.info("Error obteniendo la copia con el codigo csv incrustrado");
					generarJustificanteBean.setMsgError(properties.getProperty("field.errorCsv.codigo"));
					return generarJustificanteBean;
				}
			} else {
				logger.info("Error obteniendo el codigo csv");
				generarJustificanteBean.setMsgError(properties.getProperty("field.errorCsv.copia"));
				return generarJustificanteBean;
			}
			
		}catch(SigningException e){
			e.printStackTrace();
			logger.error("Error: "+ e);
			generarJustificanteBean.setMsgError(properties.getProperty("field.errorGenerico"));
			return generarJustificanteBean;
			
		}
		
		generarJustificanteBean.setDocCSVFirmaSinbase64(docCsvSignature);
		generarJustificanteBean.setRegistroSolicitudJustificante(registroSolicitudJustificante);
		generarJustificanteBean.setJustificanteType(justificanteType);
		generarJustificanteBean.setRegistroType(registroType);
		generarJustificanteBean.setConsentimiento(justificanteBean.getConsentimiento());
		
		return generarJustificanteBean;
		
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
		aux.setMinisterio(convocatoriaBean.getMinisterio());
		aux.setNombre(solicitud.getNombre());
		aux.setNif(solicitud.getNif());
		aux.setNumJustificante(solicitud.getNumeroSolicitud());
		aux.setEjercicio(solicitud.getEjercicio());
		if(null!=pagoSolicitud){
			aux.setFormaPago(String.valueOf(pagoSolicitud.getTipo()));
			aux.setNrc(pagoSolicitud.getNrc());
			aux.setImporte(String.valueOf(pagoSolicitud.getImporte()));	

		EntidadFinanciera entidadFinanciera = pagoSolicitud.getEntidadFinanciera();
		if(entidadFinanciera!=null){
			aux.setEntidadFinanciera(entidadFinanciera.getDescripcion());
		}
		if(pagoSolicitud.getDesMotivoReduccionTasa()!=null){
			aux.setMotivoReduccionDes(pagoSolicitud.getDesMotivoReduccionTasa());
		}
		if(pagoSolicitud.getFechaIntento()!=null){
			aux.setFechaPago(pagoSolicitud.getFechaIntento().toString());
		}
		if(pagoSolicitud.getTipo()!=null){
			aux.setTipoPago(pagoSolicitud.getTipo().toString());
		}
		}

		return aux;
	}
	
	/**
	 * To justificante bean.
	 *
	 * @param justificanteType el justificante type
	 * @param registroType el registro type
	 * @param registroSolicitudJustificanteBean el registro solicitud justificante bean
	 * @return el justificante bean
	 */
	public JustificanteBean toJustificanteBean(
			JustificanteType justificanteType, RegistroType registroType,
			RegistroSolicitudJustificanteBean registroSolicitudJustificanteBean) {

		JustificanteBean aux = new JustificanteBean();
		ResourceBundle RESOURCE_MESSAGE_ES = ResourceBundle.getBundle("MessageResources");
		//Datos de Registro
		if(justificanteType.getNmRegistro() != null && !"".equals(justificanteType.getNmRegistro())){
			aux.setNumRegistro(justificanteType.getNmRegistro());
		}else{
			aux.setNumRegistro("");
		}
		
		StringBuffer declaracion = new StringBuffer();
		declaracion.append(RESOURCE_MESSAGE_ES.getString("formulario790.solicita"));
		declaracion.append("\n");
		declaracion.append(RESOURCE_MESSAGE_ES.getString("formulario790.declara"));
		declaracion.append("\n");
		declaracion.append(RESOURCE_MESSAGE_ES.getString("formulario790.manifiesta").replaceAll("<br>",""));
//		declaracion.append("\n");
//		declaracion.append(RESOURCE_MESSAGE_ES.getString("formulario790.opone").replaceAll("<br>",""));
//		declaracion.append("\n");
//		declaracion.append(RESOURCE_MESSAGE_ES.getString("formulario790.autoriza"));
		aux.setDeclaracion(declaracion.toString());
		
	
		
		aux.setNoConsentimiento(RESOURCE_MESSAGE_ES.getString("formulario790.presencial.consiente2"));
		aux.setEjerce(RESOURCE_MESSAGE_ES.getString("formulario790.ejerce"));
		aux.setEjerce2(RESOURCE_MESSAGE_ES.getString("formulario790.ejerce2"));
		aux.setAutoriza(RESOURCE_MESSAGE_ES.getString("formulario790.autorizar"));
		aux.setNoAutorizar(RESOURCE_MESSAGE_ES.getString("formulario790.noAutorizar"));
		
		if(justificanteType.getFeRegistro() != null && !"".equals(justificanteType.getFeRegistro())){
			String fechaRegistro=justificanteType.getFeRegistro();

			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");		
			SimpleDateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy");		
			SimpleDateFormat formatter2 = new SimpleDateFormat("HH:mm:ss");		

			try {	 
				Date date = formatter.parse(fechaRegistro);
				aux.setFecha(formatter1.format(date));
				if("00:00:00".equals(formatter2.format(date))){
					aux.setHora("");
				}else{
					aux.setHora(formatter2.format(date));
				}
			} catch (ParseException e) {
				e.printStackTrace();
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

		//Buscar convocatoria
		ConvocatoriasBean convocatoriaBean;
		ConvocatoriaQuery convocatoriaQuery = new ConvocatoriaQuery();
		convocatoriaQuery.setId(Long.parseLong(registroSolicitudJustificanteBean.getIdConvocatoria()));
		convocatoriaBean = convocatoriasManager.buscarConvocatoriaById(convocatoriaQuery);
		
		String enlace = convocatoriaBean.getEnlace();
		if(enlace == null || enlace.isEmpty()) {
			enlace = RESOURCE_MESSAGE_ES.getString("formulario790.enlace.noIndicado");
		}
		
		StringBuffer cumplimiento = new StringBuffer();
		
		cumplimiento.append(RESOURCE_MESSAGE_ES.getString("formulario790.cumplimiento"));
		String error = RESOURCE_MESSAGE_ES.getString("formulario790.cumplimiento")+enlace+RESOURCE_MESSAGE_ES.getString("formulario790.cumplimiento2");
		aux.setCumplimiento(error);
		
		//Buscar solicitud
		SolicitudBean solicitudBean;
		SolicitudComunQuery solicitudQuery = new SolicitudComunQuery();
		solicitudQuery.setIdSolicitud(Long.parseLong(registroSolicitudJustificanteBean.getIdSolicitud()));
		solicitudBean = solicitudesManager.buscarSolicitudById(solicitudQuery);

		//Solicitud
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
		//Identificacion
		if(solicitudBean.getIdConsentimiento() != null){
			if(solicitudBean.getIdConsentimiento() == true){
				aux.setConsentimiento(Constantes.SI);
			}else if(solicitudBean.getIdConsentimiento() == false){
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
		if(solicitudBean.getFechaNacimiento() != null && !"".equals(solicitudBean.getFechaNacimiento())){
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
		
		if(solicitudBean.getMotivoOposicion() != null){
			aux.setMotivoOposicion(solicitudBean.getMotivoOposicion());
		}
		
		if(solicitudBean.getIdConsentimiento() == null || !solicitudBean.getIdConsentimiento() ){
			aux.setConsentimiento("X");
		}else{
			aux.setConsentimiento("");
		}
		
		if(solicitudBean.getIdConsentimientoAeat() == null || !solicitudBean.getIdConsentimientoAeat()){
			aux.setAutorizar("");
		}else{
			aux.setAutorizar("X");
		}
		
		//AutoLiquidacion
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
		// Ministerio/Órgano/Entidad convocante 
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
		if(solicitudBean.getPorcentajeDiscapacidad() != null && !"".equals(solicitudBean.getPorcentajeDiscapacidad())){
			aux.setPorcentajeDiscapacidad(String.valueOf(solicitudBean.getPorcentajeDiscapacidad()));
		}else{
			aux.setPorcentajeDiscapacidad("");
		}
		if(solicitudBean.getTipoDiscapacidad() != null && solicitudBean.getTipoDiscapacidad().getDescripcion() != null){
			aux.setTipoDiscapacidad(solicitudBean.getTipoDiscapacidad().getDescripcion()+"");
			logger.info("Tipo discapacidad: "+solicitudBean.getTipoDiscapacidad().getDescripcion());
		}else{
			aux.setTipoDiscapacidad("");
		}
		if(solicitudBean.getDetalleDiscapacidad() != null && !"".equals(solicitudBean.getDetalleDiscapacidad())){
			aux.setDetalleDiscapacidad(solicitudBean.getDetalleDiscapacidad());
			logger.info("Descripcion Tipo Discapacidad: "+solicitudBean.getDetalleDiscapacidad());
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

		// Documentación anexa
		DocumentoType auxDocType[] = registroType.getDocumentos();
		ArrayList<DocumentoBean>listaDocumentos = new ArrayList<DocumentoBean>();

		// Lista auxiliar con el hash de cada documento para la comprobación con bbdd
		// de no incluir documentos repetidos en el justificante.
		ArrayList<String>listaHashes = new ArrayList<String>();
				
		// Recuperación del formulario html y de los documentos anexos
		if(registroType.getDocumentos()!=null && registroType.getDocumentos().length>0){	
			for (int i=0; i<registroType.getDocumentos().length; i++) {
				if(auxDocType[i]!=null && auxDocType[i].getDsNombre()!=null && !auxDocType[i].getDsNombre().equals("")
						&& auxDocType[i].getDsNombre().endsWith(Constantes.EXTENSION_FORMULARIO)){
					
						
					aux.setHtmlRegistrado(auxDocType[i].getDsNombre());
					aux.setHtmlHash(auxDocType[i].getBlHash());
						
					
				}
			}
			aux.setListaDocumentos(listaDocumentos);
		}

		// Comprobación en bbdd de documentación anexa a la solicitud.
		// Este caso existirá si se almacenaron en Alfresco los documentos 
		// pero el proceso falló en el pago o en el registro.
		SolicitudComunQuery comunQuery = new SolicitudComunQuery();
		comunQuery.setIdSolicitud(solicitudBean.getId());

		DocumentoQuery documentosQuery = new DocumentoQuery();
		documentosQuery.setSolicitudComun(comunQuery);

		TipoDocumentoQuery tipoDocumentoQuery = new TipoDocumentoQuery();
		tipoDocumentoQuery.addIdIn(Constantes.TIPO_DOCUMENTO_ANEXO_SOLICITUD);
		tipoDocumentoQuery.addIdIn(Constantes.TIPO_DOCUMENTO_JUSTIFICANTE_REDUCCION_TASA);
		tipoDocumentoQuery.addIdIn(Constantes.TIPO_DOCUMENTO_ADJUNTO);
		tipoDocumentoQuery.addIdIn(Constantes.TIPO_DOCUMENTO_DISCAPACIDAD);
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
				// Controlar que el documento no sea el mismo que ya ha sido
				// guardado en bbdd a través de la lista de hashes.
				if(!listaHashes.contains(listaDocumentosAux.get(i).getHashExtracto())){
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
			if(registroSolicitudJustificanteBean.getTipoPago().equals(String.valueOf(Constantes.TIPO_PAGO_CUENTA_CODIGO))){
				aux.setTipoPago(Constantes.PAGO_TIPO_ADEUDO);
			}else if(registroSolicitudJustificanteBean.getTipoPago().equals(String.valueOf(Constantes.TIPO_PAGO_TARJETA_CODIGO))){
				aux.setTipoPago(Constantes.PAGO_TIPO_TARJETA);
			}else if(registroSolicitudJustificanteBean.getTipoPago().equals(String.valueOf(Constantes.TIPO_PAGO_EFECTIVO_CODIGO)) 
					&& ("0.0".equals(registroSolicitudJustificanteBean.getImporte()) 
							|| ".0".equals(registroSolicitudJustificanteBean.getImporte())
							||"0.".equals(registroSolicitudJustificanteBean.getImporte()))){
				aux.setTipoPago(Constantes.PAGO_TIPO_EXENTO);
				aux.setFechaPago("");
			}else if(registroSolicitudJustificanteBean.getTipoPago().equals(String.valueOf(Constantes.TIPO_PAGO_EFECTIVO_CODIGO))){
				aux.setTipoPago(Constantes.PAGO_TIPO_EFECTIVO);
			}else if(registroSolicitudJustificanteBean.getTipoPago().equals(String.valueOf(Constantes.TIPO_PAGO_NINGUNO_CODIGO))) {
				aux.setTipoPago(Constantes.PAGO_TIPO_NINGUNO);
				aux.setFechaPago("");				
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
		
		if(registroSolicitudJustificanteBean.getNrc()!=null && !"".equals(registroSolicitudJustificanteBean.getNrc())){
			aux.setNrc(registroSolicitudJustificanteBean.getNrc());
		}else if(registroSolicitudJustificanteBean.getTipoPago().equals(String.valueOf(Constantes.TIPO_PAGO_NINGUNO_CODIGO))) {
			aux.setNrc("");
		}		
		else{
			aux.setNrc("");
		}
		
		if(!("0.0".equals(registroSolicitudJustificanteBean.getImporte()) 
				|| ".0".equals(registroSolicitudJustificanteBean.getImporte())
				||"0.".equals(registroSolicitudJustificanteBean.getImporte()))	
		&&(registroSolicitudJustificanteBean.getFechaPago()!=null && !"".equalsIgnoreCase(registroSolicitudJustificanteBean.getFechaPago()))){
			
			String fechaPago =registroSolicitudJustificanteBean.getFechaPago();		
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");		
			SimpleDateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");	

			try {	 
				Date date = formatter.parse(fechaPago);
				aux.setFechaPago(formatter1.format(date));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if(registroSolicitudJustificanteBean.getEntidadFinanciera()!=null && !"".equals(registroSolicitudJustificanteBean.getEntidadFinanciera())){
			aux.setEntidad(registroSolicitudJustificanteBean.getEntidadFinanciera());
		}else if(registroSolicitudJustificanteBean.getTipoPago().equals(String.valueOf(Constantes.TIPO_PAGO_NINGUNO_CODIGO))) {
			aux.setEntidad("");
		}else{
			aux.setEntidad("");
		}

		return aux;
	}
	
	/**
	 * To justificante registro bean.
	 *
	 * @param justificanteType el justificante type
	 * @param registroType el registro type
	 * @param registroSolicitudJustificanteBean el registro solicitud justificante bean
	 * @return el justificante bean
	 */
	public JustificanteBean toJustificanteRegistroBean(
			JustificanteType justificanteType, RegistroType registroType,
			RegistroSolicitudJustificanteBean registroSolicitudJustificanteBean) {

		JustificanteBean aux = new JustificanteBean();

		//Datos de Registro
		if(justificanteType.getNmRegistro() != null && !"".equals(justificanteType.getNmRegistro())){
			aux.setNumRegistro(justificanteType.getNmRegistro());
		}else{
			aux.setNumRegistro("");
		}

		if(justificanteType.getFeRegistro() != null && !"".equals(justificanteType.getFeRegistro())){
			String fechaRegistro=justificanteType.getFeRegistro();

			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");		
			SimpleDateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy");		
			SimpleDateFormat formatter2 = new SimpleDateFormat("HH:mm:ss");		

			try {	 
				Date date = formatter.parse(fechaRegistro);
				aux.setFecha(formatter1.format(date));
				if("00:00:00".equals(formatter2.format(date))){
					aux.setHora("");
				}else{
					aux.setHora(formatter2.format(date));
				}
			} catch (ParseException e) {
				e.printStackTrace();
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

		//Buscar convocatoria
		ConvocatoriasBean convocatoriaBean;
		ConvocatoriaQuery convocatoriaQuery = new ConvocatoriaQuery();
		convocatoriaQuery.setId(Long.parseLong(registroSolicitudJustificanteBean.getIdConvocatoria()));
		convocatoriaBean = convocatoriasManager.buscarConvocatoriaById(convocatoriaQuery);

		//Buscar solicitud
		SolicitudBean solicitudBean;
		SolicitudComunQuery solicitudQuery = new SolicitudComunQuery();
		solicitudQuery.setIdSolicitud(Long.parseLong(registroSolicitudJustificanteBean.getIdSolicitud()));
		solicitudBean = solicitudesManager.buscarSolicitudById(solicitudQuery);

		//Solicitud
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
		//Identificacion
		if(solicitudBean.getIdConsentimiento() != null){
			if(solicitudBean.getIdConsentimiento() == true){
				aux.setConsentimiento(Constantes.SI);
			}else if(solicitudBean.getIdConsentimiento() == false){
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
		if(solicitudBean.getFechaNacimiento() != null && !"".equals(solicitudBean.getFechaNacimiento())){
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

		//AutoLiquidacion
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
		// Ministerio/Órgano/Entidad convocante 
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
		if(solicitudBean.getPorcentajeDiscapacidad() != null && !"".equals(solicitudBean.getPorcentajeDiscapacidad())){
			aux.setPorcentajeDiscapacidad(String.valueOf(solicitudBean.getPorcentajeDiscapacidad()));
		}else{
			aux.setPorcentajeDiscapacidad("");
		}
		if(solicitudBean.getTipoDiscapacidad() != null && solicitudBean.getTipoDiscapacidad().getDescripcion() != null){
			aux.setTipoDiscapacidad(solicitudBean.getTipoDiscapacidad().getDescripcion()+"");
			logger.info("Tipo discapacidad: "+solicitudBean.getTipoDiscapacidad().getDescripcion());
		}else{
			aux.setTipoDiscapacidad("");
		}
		if(solicitudBean.getDetalleDiscapacidad() != null && !"".equals(solicitudBean.getDetalleDiscapacidad())){
			aux.setDetalleDiscapacidad(solicitudBean.getDetalleDiscapacidad());
			logger.info("Descripcion Tipo Discapacidad: "+solicitudBean.getDetalleDiscapacidad());
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

		// Documentación anexa
		DocumentoType auxDocType[] = registroType.getDocumentos();
		ArrayList<DocumentoBean>listaDocumentos = new ArrayList<DocumentoBean>();

		// Lista auxiliar con el hash de cada documento para la comprobación con bbdd
		// de no incluir documentos repetidos en el justificante.
		ArrayList<String>listaHashes = new ArrayList<String>();
				
		// Recuperación del formulario html y de los documentos anexos
		if(registroType.getDocumentos()!=null && registroType.getDocumentos().length>0){	
			for (int i=0; i<registroType.getDocumentos().length; i++) {
				if(auxDocType[i]!=null && auxDocType[i].getDsNombre()!=null && !auxDocType[i].getDsNombre().equals("")
						&& auxDocType[i].getDsNombre().endsWith(Constantes.EXTENSION_FORMULARIO)){
					
						
					aux.setHtmlRegistrado(auxDocType[i].getDsNombre());
					aux.setHtmlHash(auxDocType[i].getBlHash());
						
					
				}
			}
			aux.setListaDocumentos(listaDocumentos);
		}

		// Comprobación en bbdd de documentación anexa a la solicitud.
		// Este caso existirá si se almacenaron en Alfresco los documentos 
		// pero el proceso falló en el pago o en el registro.
		SolicitudComunQuery comunQuery = new SolicitudComunQuery();
		comunQuery.setIdSolicitud(solicitudBean.getId());

		DocumentoQuery documentosQuery = new DocumentoQuery();
		documentosQuery.setSolicitudComun(comunQuery);

		TipoDocumentoQuery tipoDocumentoQuery = new TipoDocumentoQuery();
		tipoDocumentoQuery.addIdIn(Constantes.TIPO_DOCUMENTO_ANEXO_SOLICITUD);
		tipoDocumentoQuery.addIdIn(Constantes.TIPO_DOCUMENTO_JUSTIFICANTE_PAGO);
		tipoDocumentoQuery.addIdIn(Constantes.TIPO_DOCUMENTO_JUSTIFICANTE_REGISTRO_PDF);
		tipoDocumentoQuery.addIdIn(Constantes.TIPO_DOCUMENTO_JUSTIFICANTE_REGISTRO_XML);
		tipoDocumentoQuery.addIdIn(Constantes.TIPO_DOCUMENTO_JUSTIFICANTE_REDUCCION_TASA);
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
				// Controlar que el documento no sea el mismo que ya ha sido
				// guardado en bbdd a través de la lista de hashes.
				if(!listaHashes.contains(listaDocumentosAux.get(i).getHashExtracto())){
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
			if(registroSolicitudJustificanteBean.getTipoPago().equals(String.valueOf(Constantes.TIPO_PAGO_CUENTA_CODIGO))){
				aux.setTipoPago(Constantes.PAGO_TIPO_ADEUDO);
			}else if(registroSolicitudJustificanteBean.getTipoPago().equals(String.valueOf(Constantes.TIPO_PAGO_TARJETA_CODIGO))){
				aux.setTipoPago(Constantes.PAGO_TIPO_TARJETA);
			}else if(registroSolicitudJustificanteBean.getTipoPago().equals(String.valueOf(Constantes.TIPO_PAGO_EFECTIVO_CODIGO)) 
					&& ("0.0".equals(registroSolicitudJustificanteBean.getImporte()) 
							|| ".0".equals(registroSolicitudJustificanteBean.getImporte())
							||"0.".equals(registroSolicitudJustificanteBean.getImporte()))){
				aux.setTipoPago(Constantes.PAGO_TIPO_EXENTO);
				aux.setFechaPago("");
			}else if(registroSolicitudJustificanteBean.getTipoPago().equals(String.valueOf(Constantes.TIPO_PAGO_EFECTIVO_CODIGO))){
				aux.setTipoPago(Constantes.PAGO_TIPO_EFECTIVO);
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
		
		if(registroSolicitudJustificanteBean.getNrc()!=null && !"".equals(registroSolicitudJustificanteBean.getNrc())){
			aux.setNrc(registroSolicitudJustificanteBean.getNrc());
		}else{
			aux.setNrc("");
		}
		
		if(!("0.0".equals(registroSolicitudJustificanteBean.getImporte()) 
				|| ".0".equals(registroSolicitudJustificanteBean.getImporte())
				||"0.".equals(registroSolicitudJustificanteBean.getImporte()))	
		&&(registroSolicitudJustificanteBean.getFechaPago()!=null && !"".equalsIgnoreCase(registroSolicitudJustificanteBean.getFechaPago()))){
			
			String fechaPago =registroSolicitudJustificanteBean.getFechaPago();		
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");		
			SimpleDateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");	

			try {	 
				Date date = formatter.parse(fechaPago);
				aux.setFechaPago(formatter1.format(date));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if(registroSolicitudJustificanteBean.getEntidadFinanciera()!=null && !"".equals(registroSolicitudJustificanteBean.getEntidadFinanciera())){
			aux.setEntidad(registroSolicitudJustificanteBean.getEntidadFinanciera());
		}else{
			aux.setEntidad("");
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
	public long registroPdfCsvDocumentos(byte[] pdfasbytes, String idSolicitud, String codigoCSV) {

		DocumentoBean docBean = new DocumentoBean(); 
		
		//En ipsg siempre es espanol, entonces no se va a necesitar el locale
		Calendar c = Calendar.getInstance();
		int mes = c.get(Calendar.MONTH)+1;
		int fileSize = pdfasbytes.length;
		final String separador = File.separator;
		
		docBean.setNombre(Constantes.DESCRIPCIONSOLICITUDCSV);
		docBean.setFechaCreacion(c.getTime());
		
		// id tipo de documento		
		docBean.setIdTipoDocumento(String.valueOf(Constantes.JUSTIFICANTE_REGISTRO_ID));
	
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
		}
		return idDocumento;
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
	 * Sube a filesystem el pdf con csv incrustrado.
	 *
	 * @param pdfasbytes - el contenido del fichero
	 * @param idDocumento - el id del documento en base de datos
	 * @return 0 si hay errores, 1 si todo ok
	 */
	public int subirDocumentoPdfCSV(byte[] pdfasbytes, long idDocumento) {
		byte[] fileData = pdfasbytes;
		Properties properties;
		properties = (Properties) ApplicationContextProvider.getInstance().getBean("propertiesBean");
		
		// recupero el documentobean que voy a subir a filesystem
		DocumentoQuery documentoQuery = new DocumentoQuery();
		documentoQuery.setId(idDocumento);
		DocumentoBean docBean = documentoManager.obtenerDocumento(documentoQuery);
		
		if (docBean!=null) {
			// asigno los valores al documento bean necesarios para el metodo estatico de subida a filesystem
			docBean.setUbicacion(docBean.getUbicacion());
			docBean.setNombreAlfresco(docBean.getNombreAlfresco());
			docBean.setContenidoDocumento(fileData);
			
			// Compruebo si ya existe un justificante, de ser asi se borra
			SistemaFicheros ges= new SistemaFicheros();
			ges.comprobarBorrarJustificante(docBean);
			
			// almaceno en filesystem			
			try{
				ges.insertarContenido(docBean, properties.getProperty("sistemaficheros.url.final"));
			} catch(Exception e) {
				logger.info("error insertando en filesystem", e);
				try{
					documentoManager.eliminarDocumentoById(idDocumento);
				}catch(Exception o){
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
	 * Metodo que copia todos los ficheros asociados a una solicitud
	 * en la ubicacion definitiva con permisos de solo lectura.
	 *
	 * @param idSolicitud el id solicitud
	 * @param rutaEscritura el ruta escritura
	 * @return verdadero, si tiene exito
	 */
	public boolean copiarFicheros(String idSolicitud, String rutaEscritura){
		
		// Obtener todos los documentos asociados a la solicitud
		SolicitudComunQuery solicitudComunQuery = new SolicitudComunQuery();
		solicitudComunQuery.setIdSolicitud(Long.parseLong(idSolicitud));
		DocumentoQuery documentoQuery = new DocumentoQuery();
		documentoQuery.setSolicitudComun(solicitudComunQuery);
		ArrayList<DocumentoBean> listaDocumentos = buscarDocumentosConvocatoria(documentoQuery);

		// Copiar cada uno de ellos en la ruta definitva
		if(null!=listaDocumentos && listaDocumentos.size()>0){
			
			SistemaFicheros sistemaFicheros = new SistemaFicheros();
			
			for (DocumentoBean documentoBean : listaDocumentos) {
				StringBuffer rutaFichero = new StringBuffer();
				rutaFichero.append(rutaEscritura).append(documentoBean.getUbicacion()).append(documentoBean.getNombreAlfresco());
				
				File fichero = new File(rutaFichero.toString());
				
				if(fichero.exists()){
					if(sistemaFicheros.copiarFichero(documentoBean.getUbicacion(), fichero)){
						// Si el copiado es OK, se borra el fichero
						sistemaFicheros.borrarContenido(documentoBean);
					}else{
						logger.error("No se ha copiado el fichero "+documentoBean.getNombreAlfresco());
					}
					
					// Finalmente, si el directorio está vacío, se elimina tambien.
					try{
						File directorio = new File(rutaEscritura + documentoBean.getUbicacion());
						File lista[] = directorio.listFiles();
						if(lista != null && lista.length == 0){
							directorio.delete();
						}
					}catch (Exception e){
						logger.error("Se ha producido un error al borrar el directorio.",e);
					}
				}
			}
		}else{
			logger.error("No se ha localizado ningun documento para la solicitud "+idSolicitud);
		}
			
		return true;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.DocumentoManager#buscarDocumentosConvocatoria(es.map.ips.model.DocumentoQuery)
	 */
	public ArrayList<DocumentoBean> buscarDocumentosConvocatoria(
			DocumentoQuery documentosQuery) {
		
		ArrayList<DocumentoBean> listaDocumentos = new ArrayList<DocumentoBean>();
		SearchResult<Documento> documento=documentoDAO.search(documentosQuery);
		for(int i=0;i<documento.getResults().size();i++){
			DocumentoBean aux;
			aux = toDocumentoBean(documento.getResults().get(i));
			if(aux != null){
				listaDocumentos.add(aux);
			}
		}
		
		return listaDocumentos;
	}
	
	/**
	 * metodo que elimina un regitro dado un id de solicitud.
	 *
	 * @param idSolicitud el id solicitud
	 */
	public void borrarSolicitudDocumento(Long idSolicitud){
		try{
			DocumentoQuery documentoQuery = new DocumentoQuery();
			SolicitudComunQuery solicitudComunQuery = new SolicitudComunQuery();
			solicitudComunQuery.setIdSolicitud(idSolicitud);
			documentoQuery.setSolicitudComun(solicitudComunQuery);
			// Buscamos todos los registros que contienen ese id de Solicitud
			SearchResult<Documento> solicitudDocumento = documentoDAO.search(documentoQuery);
			if(solicitudDocumento != null && solicitudDocumento.size()>0){
				for (int i = 0; i < solicitudDocumento.size(); i++) {
					// Eliminamos la solicitud
					Long idDocumentoSolicitud = solicitudDocumento.getResults().get(i).getId();
					documentoDAO.delete(idDocumentoSolicitud);
				}
			}
		}catch (Exception e){
			logger.error("No se puede eliminar el registro de documento con ID_SOLICITUD=" + idSolicitud);
			logger.error("Error: ", e);
		}
	}
	
	
	/**
	 * Obtiene el documento DAO.
	 *
	 * @return el documento DAO
	 */
	public DocumentoDAO getDocumentoDAO() {
		return documentoDAO;
	}

	/**
	 * Establece el documento DAO.
	 *
	 * @param documentoDAO el nuevo documento DAO
	 */
	public void setDocumentoDAO(DocumentoDAO documentoDAO) {
		this.documentoDAO = documentoDAO;
	}

	/**
	 * Obtiene el tipo documento DAO.
	 *
	 * @return el tipo documento DAO
	 */
	public TipoDocumentoDAO getTipoDocumentoDAO() {
		return tipoDocumentoDAO;
	}

	/**
	 * Establece el tipo documento DAO.
	 *
	 * @param tipoDocumentoDAO el nuevo tipo documento DAO
	 */
	public void setTipoDocumentoDAO(TipoDocumentoDAO tipoDocumentoDAO) {
		this.tipoDocumentoDAO = tipoDocumentoDAO;
	}

	/**
	 * Obtiene el convocatoria DAO.
	 *
	 * @return el convocatoria DAO
	 */
	public ConvocatoriaDAO getConvocatoriaDAO() {
		return convocatoriaDAO;
	}

	/**
	 * Establece el convocatoria DAO.
	 *
	 * @param convocatoriaDAO el nuevo convocatoria DAO
	 */
	public void setConvocatoriaDAO(ConvocatoriaDAO convocatoriaDAO) {
		this.convocatoriaDAO = convocatoriaDAO;
	}

	/**
	 * Obtiene el solicitud comun DAO.
	 *
	 * @return el solicitud comun DAO
	 */
	public SolicitudComunDAO getSolicitudComunDAO() {
		return solicitudComunDAO;
	}

	/**
	 * Establece el solicitud comun DAO.
	 *
	 * @param solicitudComunDAO el nuevo solicitud comun DAO
	 */
	public void setSolicitudComunDAO(SolicitudComunDAO solicitudComunDAO) {
		this.solicitudComunDAO = solicitudComunDAO;
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
	 * Comprueba si es existe gestor documental.
	 *
	 * @return verdadero, si es existe gestor documental
	 */
	public boolean isExisteGestorDocumental() {
		return existeGestorDocumental;
	}

	/**
	 * Establece el existe gestor documental.
	 *
	 * @param existeGestorDocumental el nuevo existe gestor documental
	 */
	public void setExisteGestorDocumental(boolean existeGestorDocumental) {
		this.existeGestorDocumental = existeGestorDocumental;
	}

	/**
	 * Obtiene el url gestor.
	 *
	 * @return el url gestor
	 */
	public String getUrlGestor() {
		return urlGestor;
	}

	/**
	 * Establece el url gestor.
	 *
	 * @param urlGestor el nuevo url gestor
	 */
	public void setUrlGestor(String urlGestor) {
		this.urlGestor = urlGestor;
	}

	/**
	 * Obtiene el user gestor.
	 *
	 * @return el user gestor
	 */
	public String getUserGestor() {
		return userGestor;
	}

	/**
	 * Establece el user gestor.
	 *
	 * @param userGestor el nuevo user gestor
	 */
	public void setUserGestor(String userGestor) {
		this.userGestor = userGestor;
	}

	/**
	 * Obtiene el password gestor.
	 *
	 * @return el password gestor
	 */
	public String getPasswordGestor() {
		return passwordGestor;
	}

	/**
	 * Establece el password gestor.
	 *
	 * @param passwordGestor el nuevo password gestor
	 */
	public void setPasswordGestor(String passwordGestor) {
		this.passwordGestor = passwordGestor;
	}

	/**
	 * Obtiene el ruta temporales.
	 *
	 * @return el ruta temporales
	 */
	public String getRutaTemporales() {
		return rutaTemporales;
	}

	/**
	 * Establece el ruta temporales.
	 *
	 * @param rutaTemporales el nuevo ruta temporales
	 */
	public void setRutaTemporales(String rutaTemporales) {
		this.rutaTemporales = rutaTemporales;
	}

	/**
	 * Obtiene el space oficina registro.
	 *
	 * @return el space oficina registro
	 */
	public String getSpaceOficinaRegistro() {
		return spaceOficinaRegistro;
	}

	/**
	 * Establece el space oficina registro.
	 *
	 * @param spaceOficinaRegistro el nuevo space oficina registro
	 */
	public void setSpaceOficinaRegistro(String spaceOficinaRegistro) {
		this.spaceOficinaRegistro = spaceOficinaRegistro;
	}

	/**
	 * Obtiene el space aplicacion.
	 *
	 * @return el space aplicacion
	 */
	public String getSpaceAplicacion() {
		return spaceAplicacion;
	}

	/**
	 * Establece el space aplicacion.
	 *
	 * @param spaceAplicacion el nuevo space aplicacion
	 */
	public void setSpaceAplicacion(String spaceAplicacion) {
		this.spaceAplicacion = spaceAplicacion;
	}

	/**
	 * Obtiene el space home alfresco.
	 *
	 * @return el space home alfresco
	 */
	public String getSpaceHomeAlfresco() {
		return spaceHomeAlfresco;
	}

	/**
	 * Establece el space home alfresco.
	 *
	 * @param spaceHomeAlfresco el nuevo space home alfresco
	 */
	public void setSpaceHomeAlfresco(String spaceHomeAlfresco) {
		this.spaceHomeAlfresco = spaceHomeAlfresco;
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
	 * Obtiene nombre carpeta.
	 *
	 * @param campoOrdenacion el campo ordenacion
	 * @param doc el doc
	 * @return el string
	 */
	public String obtieneNombreCarpeta(String campoOrdenacion, DocumentoBean doc) {
		String ordena = doc.getNumSolicitud();
		String segundoApellido = "";
		String segundoApellidoAux = "";
		if (doc.getSolicitud().getSegundoApellido() != null && 
				!"".equals(doc.getSolicitud().getSegundoApellido())) {
			segundoApellido = doc.getSolicitud().getSegundoApellido() + "_";
			segundoApellidoAux = "_" + doc.getSolicitud().getSegundoApellido();
		}
		if (!"".equals(campoOrdenacion) && campoOrdenacion != null) {
			if (Constantes.CAMPO_ORDENACION_NOMBRE.equals(campoOrdenacion)) {
				ordena = doc.getSolicitud().getNombre() + "_" + 
						doc.getSolicitud().getPrimerApellido() + "_" + segundoApellido +
						doc.getSolicitud().getNif();
			} else if (Constantes.CAMPO_ORDENACION_APELLIDO.equals(campoOrdenacion)) {
				ordena =  doc.getSolicitud().getPrimerApellido() + "_" + segundoApellido +
						doc.getSolicitud().getNombre() + "_" + doc.getSolicitud().getNif();
			} else if (Constantes.CAMPO_ORDENACION_DNI.equals(campoOrdenacion)) {
				ordena =  doc.getSolicitud().getNif() + "_" + doc.getSolicitud().getNombre() + "_" + 
						doc.getSolicitud().getPrimerApellido() + segundoApellidoAux;
			}
		}
		return ordena;
	}
}