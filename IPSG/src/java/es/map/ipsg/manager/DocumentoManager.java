package es.map.ipsg.manager;

import java.util.ArrayList;
import java.util.Set;

import es.map.ips.model.DocumentoQuery;
import es.map.ipsg.bean.GenerarJustificanteBean;
import es.map.ipsg.bean.DocumentoBean;






/**
 * El Interface DocumentoManager.
 */
public interface DocumentoManager {
	
	/**
	 * Buscar documento combo.
	 *
	 * @param documentoQuery el documento query
	 * @return el array list
	 */
	public ArrayList<DocumentoBean> buscarDocumentoCombo(DocumentoQuery documentoQuery);
	
	/**
	 * Obtener documento.
	 *
	 * @param documentoQuery el documento query
	 * @return el documento bean
	 */
	public DocumentoBean obtenerDocumento(DocumentoQuery documentoQuery);
	
	/**
	 * Insertar documento.
	 *
	 * @param docBean el doc bean
	 * @return el long
	 * @throws Exception el exception
	 */
	public long insertarDocumento(DocumentoBean docBean)throws Exception;
	
	/**
	 * Insertar documento rec.
	 *
	 * @param docBean el doc bean
	 * @param ruta el ruta
	 * @return el long
	 * @throws Exception el exception
	 */
	public long insertarDocumentoRec(DocumentoBean docBean, String ruta)throws Exception;
	
	/**
	 * Insertar documento adjunto.
	 *
	 * @param docBean el doc bean
	 * @return el long
	 * @throws Exception el exception
	 */
	public long insertarDocumentoAdjunto(DocumentoBean docBean)throws Exception;
	
	/**
	 * Insertar documento asociado.
	 *
	 * @param docBean el doc bean
	 * @return el long
	 * @throws Exception el exception
	 */
	public long insertarDocumentoAsociado(DocumentoBean docBean)throws Exception;
	
	/**
	 * Insertar documento csv.
	 *
	 * @param documentoBean el documento bean
	 * @return el long
	 * @throws Exception el exception
	 */
	public long insertarDocumentoCsv(DocumentoBean documentoBean) throws Exception;
	
	/**
	 * Insertar documento CSV justicia.
	 *
	 * @param docBean el doc bean
	 * @return el documento bean
	 * @throws Exception el exception
	 */
	public DocumentoBean insertarDocumentoCSVJusticia(DocumentoBean docBean) throws Exception;
	
	/**
	 * Modificar documento CSV justicia.
	 *
	 * @param docBean el doc bean
	 * @return el documento bean
	 * @throws Exception el exception
	 */
	public DocumentoBean modificarDocumentoCSVJusticia(DocumentoBean docBean) throws Exception;
	
	/**
	 * Borrar documento.
	 *
	 * @param doc el doc
	 * @throws Exception el exception
	 */
	public void borrarDocumento(DocumentoBean doc) throws Exception ;
	
	/**
	 * Eliminar documento by id.
	 *
	 * @param idDocumento el id documento
	 */
	public void eliminarDocumentoById(Long idDocumento);
	
	/**
	 * Modificar documento.
	 *
	 * @param documentoBean el documento bean
	 */
	public void modificarDocumento (DocumentoBean  documentoBean);
	
	/**
	 * Buscar documentos by id solicitud.
	 *
	 * @param documentoQuery el documento query
	 * @return el array list
	 */
	public ArrayList<DocumentoBean> buscarDocumentosByIdSolicitud(DocumentoQuery documentoQuery);
	
	/**
	 * Obtener contenido documento.
	 *
	 * @param documentoBean el documento bean
	 * @return el byte[]
	 * @throws Exception el exception
	 */
	public byte[] obtenerContenidoDocumento(DocumentoBean documentoBean)throws Exception;
	
	/**
	 * Insertar documento firma.
	 *
	 * @param documentoBeanXML el documento bean XML
	 * @return el long
	 */
	public Long insertarDocumentoFirma(DocumentoBean documentoBeanXML);
	
	/**
	 * Insertar documento PDF.
	 *
	 * @param docBean el doc bean
	 * @param ruta el ruta
	 * @return el documento bean
	 * @throws Exception el exception
	 */
	public DocumentoBean insertarDocumentoPDF(DocumentoBean docBean, String ruta)throws Exception ;
	
	/**
	 * Insertar documento prueba.
	 *
	 * @param docBean el doc bean
	 * @return el string
	 * @throws Exception el exception
	 */
	public String insertarDocumentoPrueba(DocumentoBean docBean) throws Exception;
	
	/**
	 * Generar justificante PDF.
	 *
	 * @param docBean el doc bean
	 * @return el documento bean
	 * @throws Exception el exception
	 */
	public DocumentoBean generarJustificantePDF(DocumentoBean docBean) throws Exception;
	
	/**
	 * Guardar documento PDF.
	 *
	 * @param docBean el doc bean
	 * @return el long
	 * @throws Exception el exception
	 */
	public Long guardarDocumentoPDF(DocumentoBean docBean) throws Exception;
	
	/**
	 * Guardar documento XML.
	 *
	 * @param docBean el doc bean
	 * @return el long
	 * @throws Exception el exception
	 */
	public Long guardarDocumentoXML(DocumentoBean docBean) throws Exception;
	
	/**
	 * Existe documento.
	 *
	 * @param rutaDocumento el ruta documento
	 * @return verdadero, si tiene exito
	 * @throws Exception el exception
	 */
	boolean existeDocumento(String rutaDocumento)  throws Exception;
	
	/**
	 * Copiar ficheros.
	 *
	 * @param listaDocumentos el lista documentos
	 * @param rutaEscritura el ruta escritura
	 * @return verdadero, si tiene exito
	 */
	public boolean copiarFicheros(ArrayList<DocumentoBean> listaDocumentos, String rutaEscritura);
	
	/**
	 * Buscar documentos.
	 *
	 * @param documentoQuery el documento query
	 * @return el establece el
	 */
	public Set<DocumentoBean> buscarDocumentos(DocumentoQuery documentoQuery);
	
	/**
	 * Generar justificante.
	 *
	 * @param idRegistro el id registro
	 * @return el generar justificante bean
	 * @throws Exception el exception
	 */
	public GenerarJustificanteBean generarJustificante(String idRegistro) throws Exception;
	
	/**
	 * Generar justificante registro.
	 *
	 * @param idRegistro el id registro
	 * @return el generar justificante bean
	 * @throws Exception el exception
	 */
	public GenerarJustificanteBean generarJustificanteRegistro(String idRegistro) throws Exception;
	
	/**
	 * Insertar documento registro.
	 *
	 * @param docBean el doc bean
	 * @return el long
	 * @throws Exception el exception
	 */
	public long insertarDocumentoRegistro(DocumentoBean docBean) throws Exception;
	
	/**
	 * Copiar ficheros.
	 *
	 * @param idSolicitud el id solicitud
	 * @param rutaEscritura el ruta escritura
	 * @return verdadero, si tiene exito
	 */
	public boolean copiarFicheros(String idSolicitud, String rutaEscritura);
	
	/**
	 * Buscar documentos convocatoria.
	 *
	 * @param documentosQuery el documentos query
	 * @return el array list
	 */
	public ArrayList<DocumentoBean> buscarDocumentosConvocatoria(DocumentoQuery documentosQuery);
	
	/**
	 * Borrar documento lectura escritura.
	 *
	 * @param doc el doc
	 * @throws Exception el exception
	 */
	public void borrarDocumentoLecturaEscritura(DocumentoBean doc) throws Exception;
	
	/**
	 * Borrar contenido lectura escritura gestor documental.
	 *
	 * @param doc el doc
	 * @throws Exception el exception
	 */
	public void borrarContenidoLecturaEscrituraGestorDocumental(DocumentoBean doc) throws Exception;
	
	/**
	 * Borrar solicitud documento.
	 *
	 * @param idSolicitud el id solicitud
	 */
	public void borrarSolicitudDocumento(Long idSolicitud);
	
	public String obtieneNombreCarpeta(String campoOrdenacion, DocumentoBean doc);
}
