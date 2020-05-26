package es.map.ipsc.manager.convocatorias;

import java.util.ArrayList;

import es.map.ips.model.DocumentoQuery;
import es.map.ipsc.bean.DocumentoBean;


/**
 * El Interface DocumentosConvocatoriaManager.
 */
public interface DocumentosConvocatoriaManager {


	/**
	 * Buscar documentos convocatoria.
	 *
	 * @param documentosQuery el documentos query
	 * @return el array list
	 */
	public ArrayList<DocumentoBean> buscarDocumentosConvocatoria(DocumentoQuery documentosQuery);

	/**
	 * Obtener estado.
	 *
	 * @param documentoQuery el documento query
	 * @return el documento bean
	 */
	public DocumentoBean obtenerEstado(DocumentoQuery documentoQuery);

	/**
	 * Convocatoria documento abierta.
	 *
	 * @param documentoQuery el documento query
	 * @return verdadero, si tiene exito
	 */
	public boolean ConvocatoriaDocumentoAbierta(DocumentoQuery documentoQuery);

	/**
	 * Insertar documento.
	 *
	 * @param docBean el doc bean
	 * @return el long
	 * @throws Exception el exception
	 */
	public long insertarDocumento(DocumentoBean docBean)throws Exception;
	
	/**
	 * Insertar documento csv.
	 *
	 * @param docBean el doc bean
	 * @return el long
	 * @throws Exception el exception
	 */
	public long insertarDocumentoCsv(DocumentoBean docBean) throws Exception;

	/**
	 * Borrar documento.
	 *
	 * @param doc el doc
	 * @throws Exception el exception
	 */
	public void borrarDocumento(DocumentoBean doc) throws Exception ;
	
	/**
	 * Obtener documento.
	 *
	 * @param documentoQuery el documento query
	 * @return el documento bean
	 */
	public DocumentoBean obtenerDocumento(DocumentoQuery documentoQuery);

	/**
	 * Insertar documento firma.
	 *
	 * @param documentoBeanXML el documento bean XML
	 * @return el long
	 */
	public Long insertarDocumentoFirma(DocumentoBean documentoBeanXML);

	/**
	 * Eliminar documentos by id solicitud.
	 *
	 * @param string el string
	 */
	public void eliminarDocumentosByIdSolicitud(String string);

	/**
	 * Eliminar documento by id.
	 *
	 * @param idDocumento el id documento
	 */
	public void eliminarDocumentoById(Long idDocumento);
	
	/**
	 * Copiar ficheros.
	 *
	 * @param idSolicitud el id solicitud
	 * @param directorioEscritura el directorio escritura
	 * @return verdadero, si tiene exito
	 */
	public boolean copiarFicheros(String idSolicitud, String directorioEscritura);

	/**
	 * Migrar documentos adjuntos.
	 *
	 * @param idSolicitudOld el id solicitud old
	 * @param idSolicitudNew el id solicitud new
	 */
	public void migrarDocumentosAdjuntos(long idSolicitudOld, long idSolicitudNew);
}
