package es.map.ipsc.manager.convocatorias;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import org.apache.log4j.Logger;
import es.map.ips.common.model.SearchResult;
import es.map.ips.dao.ConvocatoriaDAO;
import es.map.ips.dao.DocumentoDAO;
import es.map.ips.dao.SolicitudComunDAO;
import es.map.ips.dao.TipoDocumentoDAO;
import es.map.ips.model.Convocatoria;
import es.map.ips.model.Documento;
import es.map.ips.model.DocumentoQuery;
import es.map.ips.model.SolicitudComun;
import es.map.ips.model.SolicitudComunQuery;
import es.map.ips.model.TipoDocumento;
import es.map.ips.model.TipoDocumentoQuery;
import es.map.ipsc.Constantes;
import es.map.ipsc.bean.DocumentoBean;
import es.map.ipsc.utils.SistemaFicheros;

/**
 * El Class DocumentosConvocatoriaManagerImpl.
 *
 * @author everis
 */
public class DocumentosConvocatoriaManagerImpl implements DocumentosConvocatoriaManager {

	/** el tipo documento DAO. */
	private TipoDocumentoDAO tipoDocumentoDAO;
	
	/** el convocatoria DAO. */
	private ConvocatoriaDAO convocatoriaDAO;
	
	/** el solicitud comun DAO. */
	private SolicitudComunDAO solicitudComunDAO;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(DocumentosConvocatoriaManagerImpl.class);
		

	/** el documento DAO. */
	// DAO´s que se utilizan
	private DocumentoDAO documentoDAO;
	
	/**
	 * To documento bean.
	 *
	 * @param d el d
	 * @return el documento bean
	 */
	private DocumentoBean toDocumentoBean(Documento d){
		DocumentoBean bean= new DocumentoBean();
		bean.setId(d.getId());
		bean.setNombre(d.getNombre());
		bean.setDescripcion(d.getDescripcion());
		bean.setFechaCreacion(d.getFechaCreacion());
		bean.setTamano(d.getTamano());
		bean.setTipoDocumento(d.getTipoDocumento());
		bean.setDsTipoDocumento(d.getTipoDocumento().getDescripcion());
		bean.setNombreAlfresco(d.getNombreAlfresco());
		bean.setUbicacion(d.getUbicacion());
		if(d.getSolicitudComun() != null){
			bean.setIdSolicitud(d.getSolicitudComun().getIdSolicitud());
		}
		if(d.getConvocatoria() != null){
			bean.setIdConvocatoria(d.getConvocatoria().getId());
		}
		if(d.getHash() != null){
			bean.setHashExtracto(d.getHash());
		}
		
		return bean;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.convocatorias.DocumentosConvocatoriaManager#eliminarDocumentosByIdSolicitud(java.lang.String)
	 */
	public void eliminarDocumentosByIdSolicitud(String idSolicitud) {
		SolicitudComunQuery solicitudComunQuery = new SolicitudComunQuery();
		solicitudComunQuery.setIdSolicitud(Long.parseLong(idSolicitud));
		DocumentoQuery documentoQuery = new DocumentoQuery();
		documentoQuery.setSolicitudComun(solicitudComunQuery);
		SearchResult<Documento> documentos=documentoDAO.search(documentoQuery);

		SistemaFicheros sistema= new SistemaFicheros();
		
		if(documentos != null){
			for(int i=0;i<documentos.getResults().size();i++){
				documentoDAO.delete(documentos.getResults().get(i).getId());
				if(documentos.getResults().get(i).getUbicacion()!=null){
					sistema.borrarDirectorio(documentos.getResults().get(i).getUbicacion()); 			
					logger.info("Se ha eliminado la carpeta del sistema de ficheros");
				}
			}
		}
		
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.convocatorias.DocumentosConvocatoriaManager#eliminarDocumentoById(java.lang.Long)
	 */
	public void eliminarDocumentoById(Long idDocumento) {
			if(idDocumento>0)
				documentoDAO.delete(idDocumento);
				
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.convocatorias.DocumentosConvocatoriaManager#buscarDocumentosConvocatoria(es.map.ips.model.DocumentoQuery)
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
	
	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.convocatorias.DocumentosConvocatoriaManager#insertarDocumento(es.map.ipsc.bean.DocumentoBean)
	 */
	public long insertarDocumento(DocumentoBean docBean) throws Exception {

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
			ges.insertarContenido(docBean);
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
	
	
	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.convocatorias.DocumentosConvocatoriaManager#insertarDocumentoCsv(es.map.ipsc.bean.DocumentoBean)
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
			logger.error("InsertarDocumentoCsv - Error:",e);
			return 0;
		}
				
		doc = documentoDAO.get(idDocumento);
		doc.setNombreAlfresco(Constantes.NOMBRE_JUSTIFICANTE_REGISTRO_CSV + idDocumento + ".pdf");
		documentoDAO.update(doc);
		
		return idDocumento;
	}
	
	/**
	 * Metodo que inserta en el sistema de ficheros y en bbdd el formulario firmado.
	 *
	 * @param docBean el doc bean
	 * @return el long
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

		Calendar cal = Calendar.getInstance();
        int mes = cal.get(Calendar.MONTH)+1;
        final String separador = File.separator;
        
		StringBuffer rutaDocumento = new StringBuffer();
		
		rutaDocumento.append(cal.get(Calendar.YEAR)).append(separador)
			.append(mes).append(separador)
			.append(cal.get(Calendar.DAY_OF_MONTH)).append(separador)
			.append(cal.get(Calendar.HOUR_OF_DAY)).append(separador)
			.append(docBean.getIdSolicitud()).append(separador);

		docBean.setUbicacion(rutaDocumento.toString());
		doc.setUbicacion(rutaDocumento.toString());
		
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
			logger.error("insertarDocumentoFirma - Error:",e);
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
			sistema.insertarContenido(docBean);
		}catch(Exception e){
			logger.info(e);
			logger.error("insertarDocumentoFirma - Error:",e);
			try{
				documentoDAO.delete(idDocumento);
				}catch(Exception o){
					logger.error("insertarDocumentoFirma - delete:",e);
					return 0L;				
				}
			return 0L;
		}
		return idDocumento;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.convocatorias.DocumentosConvocatoriaManager#obtenerDocumento(es.map.ips.model.DocumentoQuery)
	 */
	public DocumentoBean obtenerDocumento(DocumentoQuery documentoQuery) {
		Documento doc = documentoDAO.searchUnique(documentoQuery);
		if(doc==null){
			return null;
		}
		
		DocumentoBean bean = toDocumentoBean(doc);

		if(bean == null){
			return null;
		}
		return bean;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.convocatorias.DocumentosConvocatoriaManager#borrarDocumento(es.map.ipsc.bean.DocumentoBean)
	 */
	public void borrarDocumento(DocumentoBean doc) throws Exception {
		SistemaFicheros sistema = new SistemaFicheros();
		sistema.borrarContenido(doc);
		
		documentoDAO.delete(doc.getId());
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.convocatorias.DocumentosConvocatoriaManager#obtenerEstado(es.map.ips.model.DocumentoQuery)
	 */
	public DocumentoBean obtenerEstado(DocumentoQuery documentoQuery) {
		Documento minis = documentoDAO.searchUnique(documentoQuery);
		DocumentoBean bean;
		if(minis != null){
			bean = toEstadoBean(minis);
			if(bean == null){
				return null;
			}
		}else{
			return null;
		}
		return bean;
	}
	
	/**
	 * To estado bean.
	 *
	 * @param documento el documento
	 * @return el documento bean
	 */
	public DocumentoBean toEstadoBean(Documento documento) {

		DocumentoBean result = new DocumentoBean();

		result.setId(documento.getId());
		result.setNombre(documento.getNombre());
		result.setDescripcion(documento.getDescripcion());
		result.setNombreAlfresco(documento.getNombreAlfresco());
		result.setTamano(documento.getTamano());
		result.setTipoDocumento(documento.getTipoDocumento());
		result.setFechaCreacion(documento.getFechaCreacion());
		result.setUbicacion(documento.getUbicacion());
		
		if(documento.getConvocatoria()!=null)
			result.setIdConvocatoria(documento.getConvocatoria().getId());
		
		if(documento.getSolicitudComun()!=null)
			result.setIdSolicitud(documento.getSolicitudComun().getIdSolicitud());
		
		if(documento.getCorreoElectronico()!=null)
			result.setIdCorreoElectronico(documento.getCorreoElectronico().getId());
		
		return result;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.convocatorias.DocumentosConvocatoriaManager#ConvocatoriaDocumentoAbierta(es.map.ips.model.DocumentoQuery)
	 */
	public boolean ConvocatoriaDocumentoAbierta(DocumentoQuery documentoQuery) {
		Documento minis = documentoDAO.searchUnique(documentoQuery);
		Boolean solicitudBoolean = false;
		SolicitudComun solicitud = minis.getSolicitudComun();
		
		if (solicitud != null) {
			solicitudBoolean = true;
		}
		Convocatoria convocatoria = minis.getConvocatoria();
		
		if(convocatoria != null){
			solicitudBoolean = true;
		}
		
		return solicitudBoolean;
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
						if(lista.length == 0){
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
	 * @see es.map.ipsc.manager.convocatorias.DocumentosConvocatoriaManager#migrarDocumentosAdjuntos(long, long)
	 */
	@Override
	public void migrarDocumentosAdjuntos(long idSolicitudOld, long idSolicitudNew) {
		SolicitudComunQuery solicitudComunQuery = new SolicitudComunQuery();
		solicitudComunQuery.setIdSolicitud(idSolicitudOld);
		DocumentoQuery documentoQuery = new DocumentoQuery();
		documentoQuery.setSolicitudComun(solicitudComunQuery);
		TipoDocumentoQuery tipoDocumentoQuery = new TipoDocumentoQuery();
		tipoDocumentoQuery.addIdIn(1);
		tipoDocumentoQuery.addIdIn(6);
		tipoDocumentoQuery.addIdIn(10);
		documentoQuery.setTipoDocumento(tipoDocumentoQuery);
		
		SearchResult<Documento> listaDoc = documentoDAO.search(documentoQuery);

		solicitudComunQuery.setIdSolicitud(idSolicitudNew);
		SolicitudComun solicitudComun = solicitudComunDAO.searchUnique(solicitudComunQuery);
		//Si encontramos adjuntos actualizamos
		if (listaDoc != null && listaDoc.size() > 0 && solicitudComun != null) {
			logger.info("Se van a migrar los adjuntos de la solicitud "+idSolicitudOld+" a la solicitud "+idSolicitudNew);
			for (Documento doc : listaDoc.getResults()) {
				doc.setSolicitudComun(solicitudComun);
				documentoDAO.update(doc);
			}
		}
		logger.info("fin de la migracion de los adjuntos");
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
	 * Obtiene el logger.
	 *
	 * @return el logger
	 */
	public static Logger getLogger() {
		return logger;
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

}
