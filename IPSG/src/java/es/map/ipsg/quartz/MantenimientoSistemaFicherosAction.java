package es.map.ipsg.quartz;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import es.map.ips.common.spring.ApplicationContextProvider;
import es.map.ips.model.Convocatoria;
import es.map.ips.model.ConvocatoriaQuery;
import es.map.ips.model.DocumentoQuery;
import es.map.ips.model.EstadoSolicitudQuery;
import es.map.ips.model.SolicitudComunQuery;
import es.map.ipsg.bean.DocumentoBean;
import es.map.ipsg.bean.SolicitudBean;
import es.map.ipsg.manager.ConvocatoriasManager;
import es.map.ipsg.manager.DocumentoManager;
import es.map.ipsg.manager.SolicitudesManager;
import es.map.ipsg.util.Constantes;
import es.map.ipsg.util.SistemaFicheros;
import es.map.ipsg.util.UtilesIPSG;


/**
 * El Class MantenimientoSistemaFicherosAction.
 */
public class MantenimientoSistemaFicherosAction {

	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(MantenimientoSistemaFicherosAction.class);

	/** el convocatorias manager. */
	private ConvocatoriasManager convocatoriasManager;
	
	/** el documento manager. */
	private DocumentoManager documentoManager;
	
	/** el solicitudes manager. */
	private SolicitudesManager solicitudesManager;

	/** el properties. */
	private Properties properties;
	
	/** La constante STRING_FICHERO_NO_LOCALIZADO. */
	private static final String STRING_FICHERO_NO_LOCALIZADO = "Fichero no localizado!!! ";

	/**
	 * Execute test.
	 *
	 * @param convocatoriasManager el convocatorias manager
	 * @param documentoManager el documento manager
	 * @param solicitudesManager el solicitudes manager
	 */
	public void executeTest(ConvocatoriasManager convocatoriasManager, DocumentoManager documentoManager, SolicitudesManager solicitudesManager){
		setConvocatoriasManager(convocatoriasManager);
		setDocumentoManager(documentoManager);
		setSolicitudesManager(solicitudesManager);
	}

	/**
	 * Metodo que realiza las siguientes tareas de mantenimiento
	 * 1. Mover documentos de convocatorias finalizadas a la ubicación definitiva de SOLO-LECTURA
	 * 2. Mover documentos de convocatorias en estado 2-CERRADA, 4-CONFIGURACION, 6-ELIMINADA, 7-APROBADA y 8-DESACTIVADA 
	 *    a la ubicación definitiva de SOLO-LECTURA tras un periodo de 90 días desde su fecha de fin.
	 * 3. Mover documentos de solicitudes presenciales tras 90 días después del alta del mismo en IPS
	 * 4. Mover documentos de solicitudes online registradas a la ubicación definitiva de SOLO-LECTURA
	 * 5. Vaciar directorio de ESCRITURA en caso de no contener ningún fichero.
	 */
	public void execute() {

		logger.info("Batch Mantenimiento Sistema Ficheros - inicio");

		try {
			try {
				convocatoriasManager = (ConvocatoriasManager) ApplicationContextProvider.getInstance().getBean("convocatoriaManager");
				documentoManager = (DocumentoManager) ApplicationContextProvider.getInstance().getBean("documentoManager");
				solicitudesManager = (SolicitudesManager) ApplicationContextProvider.getInstance().getBean("solicitudesManager");
				properties = (Properties) ApplicationContextProvider.getInstance().getBean("propertiesBean");
			} catch (Exception e) {
				logger.info("Contexto cargado por ejecucion de Test.");
				logger.error("Contexto cargado por ejecucion de Test.",e);
			}

			SistemaFicheros sistemaFicheros = new SistemaFicheros();
			
			// Evaluar el tamaño del directorio de escritura
			String rutaDirectorioEscritura = properties.getProperty("sistemaficheros.url.escritura");
			File directorioEscritura = new File(rutaDirectorioEscritura);
			
			if(FileUtils.sizeOfDirectory(directorioEscritura)>0){
				
				logger.info("Tamanho del directorio: "+FileUtils.sizeOfDirectory(directorioEscritura)+ " bytes");
				
				DocumentoQuery documentoQuery = new DocumentoQuery();
				
				// Recorrer recursivamente el directorio de escritura
				Collection<File> listaFicheros = FileUtils.listFiles(directorioEscritura, null, true);
				
				for (File file : listaFicheros) {
					documentoQuery.addNombreAlfrescoIn(file.getName());
				}
					
				ArrayList<DocumentoBean> listaDocumentosBean = documentoManager.buscarDocumentoCombo(documentoQuery);
				
				for (DocumentoBean documentoBean : listaDocumentosBean) {
					
					try {
						logger.info("Tratando documento con ID: "+documentoBean.getId());
						
						int tipoDocumento = Integer.parseInt(documentoBean.getIdTipoDocumento());
						
						// Si el documento es de CONVOCATORIA
						if(tipoDocumento == Constantes.TIPO_DOCUMENTO_ANEXO_CONVOCATORIA && Long.valueOf(documentoBean.getIdConvocatoria())!=null){
							
							ConvocatoriaQuery convocatoriaQuery = new ConvocatoriaQuery();
							convocatoriaQuery.setId(documentoBean.getIdConvocatoria());
							Convocatoria convocatoria = convocatoriasManager.buscarConvocatoria(convocatoriaQuery);
							int estadoConvocatoria = convocatoria.getEstadoConvocatoria().getId();
							
							// 1. Si el estado de la convocatoria es FINALIZADA, se mueve al directorio de Solo-Lectura
							if(estadoConvocatoria == Constantes.ESTADO_CONVOCATORIA_FINALIZADO){
								File fichero = localizarFichero(documentoBean.getNombreAlfresco(), listaFicheros);
								if(null!=fichero){
									sistemaFicheros.copiarFichero(documentoBean.getUbicacion(), fichero);
									logger.info("Fichero de CONVOCATORIA FINALIZADA copiado a ubicacion defintiva");
									sistemaFicheros.borrarContenido(documentoBean);
									logger.info("Fichero de CONVOCATORIA borrado correctamente.");
								}else{
									logger.error(STRING_FICHERO_NO_LOCALIZADO+documentoBean.getNombreAlfresco());
								}
								// 2. Si el estado de la convocatoria es 2-CERRADA, 4-CONFIGURACION, 6-ELIMINADA, 7-APROBADA y 8-DESACTIVADA
							}else if(estadoConvocatoria == Constantes.ESTADO_CONVOCATORIA_CERRADO || estadoConvocatoria == Constantes.ESTADO_CONVOCATORIA_CONFIGURACION
									|| estadoConvocatoria == Constantes.ESTADO_CONVOCATORIA_ELIMINADO || estadoConvocatoria == Constantes.ESTADO_CONVOCATORIA_APROBADO 
									|| estadoConvocatoria == Constantes.ESTADO_CONVOCATORIA_DESACTIVADO){
								
								Date fechaActual = UtilesIPSG.obtenerFechaActual();
								Date fechaDoc = UtilesIPSG.sumarRestarDiasFecha(convocatoria.getFechaFin(), Constantes.NUM_DIAS_DOC_READONLY);
								
								// Si han pasado mas de 90 dias desde la fecha de fin de la convocatoria
								if(fechaActual.getTime() > fechaDoc.getTime()){
									File fichero = localizarFichero(documentoBean.getNombreAlfresco(), listaFicheros);
									if(null!=fichero){
										sistemaFicheros.copiarFichero(documentoBean.getUbicacion(), fichero);
										logger.info("Fichero de CONVOCATORIA copiado a ubicacion defintiva");
										sistemaFicheros.borrarContenido(documentoBean);
										logger.info("Fichero de CONVOCATORIA borrado correctamente.");
									}else{
										logger.error(STRING_FICHERO_NO_LOCALIZADO+documentoBean.getNombreAlfresco());
									}
								}
								
							}else{
								logger.info("El documento pertenece a una convocatoria no finalizada");
							}

							// Documento anexo a SOLICITUD
						}else{

							if(documentoBean!=null && documentoBean.getIdSolicitud()!=null){
								// Recuperar datos de la solicitud registrada correspondientes al documento
								SolicitudComunQuery solicitudQuery = new SolicitudComunQuery();
								solicitudQuery.setIdSolicitud(documentoBean.getIdSolicitud());
								EstadoSolicitudQuery estadoSolicitudQuery = new EstadoSolicitudQuery();
								estadoSolicitudQuery.setId(3);
								solicitudQuery.setEstadoSolicitud(estadoSolicitudQuery);
								SolicitudBean solicitudBean = solicitudesManager.buscarSolicitudById(solicitudQuery);
								
								if(solicitudBean != null){
									// 3. Solicitud PRESENCIAL
									if(solicitudBean.getIdTipo() == Constantes.TIPO_SOLICITUD_PRESENCIAL){
										
										Date fechaActual = UtilesIPSG.obtenerFechaActual();
										Date fechaDoc = UtilesIPSG.sumarRestarDiasFecha(documentoBean.getFechaCreacion(), Constantes.NUM_DIAS_DOC_READONLY);
										
										// Si han pasado mas de 90 dias desde la fecha de alta del documento
										if(fechaActual.getTime() > fechaDoc.getTime()){
											File fichero = localizarFichero(documentoBean.getNombreAlfresco(), listaFicheros);
											if(null!=fichero){
												sistemaFicheros.copiarFichero(documentoBean.getUbicacion(), fichero);
												logger.info("Fichero de SOLICITUD PRESENCIAL copiado a ubicacion defintiva");
												sistemaFicheros.borrarContenido(documentoBean);
												logger.info("Fichero de SOLICITUD PRESENCIAL borrado correctamente.");
											}else{
												logger.error(STRING_FICHERO_NO_LOCALIZADO+documentoBean.getNombreAlfresco());
											}
										}
										
										// 4. Solicitud ONLINE y documentos asociados
									}else{
										if(solicitudBean.getIdEstadoSolicitud()==Constantes.ESTADO_SOLICITUD_REGISTRADO){
											File fichero = localizarFichero(documentoBean.getNombreAlfresco(), listaFicheros);
											if(null!=fichero){
												sistemaFicheros.copiarFichero(documentoBean.getUbicacion(), fichero);
												logger.info("Fichero de SOLICITUD ONLINE copiado a ubicacion defintiva");
												sistemaFicheros.borrarContenido(documentoBean);
												logger.info("Fichero de SOLICITUD ONLINE borrado correctamente.");
											}else{
												logger.error(STRING_FICHERO_NO_LOCALIZADO+documentoBean.getNombreAlfresco());
											}
										}
									}
								}else{
									logger.error("No se encuentra el documento con ID: "+documentoBean.getId());
								}
							}else{
								logger.error("Documento sin Solicitud asociada");
							}
						}
					} catch (Exception e) {
						logger.error("Error procesando fichero con ID: "+ documentoBean.getId(),e);
						
					}
				}
				
				// 5. Eliminar todas las carpetas contenidas en el directorio de ESCRITURA en caso de no contener ningún fichero.
			}else{
				logger.info("No existe ningún fichero en el directorio de ESCRITURA");
				// Vaciamos el directorio.
				FileUtils.deleteDirectory(directorioEscritura);
			}


		}catch (Exception e) {
			logger.error("Error en mantenimiento de Sistemas de ficheros",e);
			
		}

		logger.info("Batch Mantenimiento Sistema Ficheros - fin");
	}

	
	/**
	 * Método que localiza un fichero de una lista a traves de su nombre.
	 * @param nombreAlfresco String
	 * @param listaFicheros Collection<File>
	 * @return fichero File
	 */
	private File localizarFichero(String nombreAlfresco, Collection<File> listaFicheros) {
			
		for (File file : listaFicheros) {
			if(file.getName().equals(nombreAlfresco)){
				return file;
			}
		}
		return null;
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

}
