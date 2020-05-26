package es.map.ipsg.quartz;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import org.apache.log4j.Logger;

import es.map.ips.common.spring.ApplicationContextProvider;
import es.map.ips.model.DocumentoQuery;
import es.map.ips.model.SolicitudComunQuery;
import es.map.ipsg.bean.DocumentoBean;
import es.map.ipsg.bean.SolicitudBean;
import es.map.ipsg.manager.BatchMonitorManager;
import es.map.ipsg.manager.DocumentoManager;
import es.map.ipsg.manager.SolicitudesManager;
import es.map.ipsg.util.Constantes;


/**
 * El Class ProcesoGenerarPdf.
 */
public class ProcesoGenerarPdf {

	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(ProcesoGenerarPdf.class);

	/** el documento manager. */
	private DocumentoManager documentoManager;
	
	/** el solicitudes manager. */
	private SolicitudesManager solicitudesManager;
	
	/** el batch monitor manager. */
	private BatchMonitorManager batchMonitorManager;
	
	/** el properties. */
	private Properties properties;

	/**
	 * Execute test.
	 *
	 * @param documentoManager el documento manager
	 * @param batchMonitorManager el batch monitor manager
	 */
	public void executeTest(DocumentoManager documentoManager, BatchMonitorManager batchMonitorManager){
		setDocumentoManager(documentoManager);
		setBatchMonitorManager(batchMonitorManager);
	}

	/**
	 * Metodo que realiza las siguientes tareas de mantenimiento
	 * 1. Comprobar si existe la ruta y/o documento en el filesystem
	 * 2. Si no existe genera el justificante de registro y lo guarda en el filesystem
	 */
	public void execute() {

		boolean resultado = true;
		StringBuffer desResultado = new StringBuffer();
		String result = null;
		logger.info("Batch Proceso Generacion Justificante de Registro - inicio");

		try {
			batchMonitorManager = (BatchMonitorManager) ApplicationContextProvider.getInstance().getBean("batchMonitorManager");
			documentoManager = (DocumentoManager) ApplicationContextProvider.getInstance().getBean("documentoManager");
			solicitudesManager = (SolicitudesManager) ApplicationContextProvider.getInstance().getBean("solicitudesManager");
			properties = (Properties) ApplicationContextProvider.getInstance().getBean("propertiesBean");
		} catch (Exception e) {
			logger.info("Contexto cargado por ejecucion de Test.");
			logger.error("Contexto cargado por ejecucion de Test.",e);
			resultado = false;
		}
		
		try {
			
			ArrayList<DocumentoBean> documentosList = new ArrayList<DocumentoBean>();
			
			Date fechaDesde = batchMonitorManager.buscarResultado();

			SolicitudComunQuery solicitudQuery = new SolicitudComunQuery();
			solicitudQuery.setFechaSolicitudMin(fechaDesde);
			solicitudQuery.addEstadoSolicitudIn(Constantes.ESTADO_SOLICITUD_REGISTRADO);
			ArrayList<SolicitudBean> arrSolicitud = solicitudesManager.buscarSolicitudCombo(solicitudQuery);
			
			logger.info("Solicitudes encontradas : "+ arrSolicitud.size());
			
			if(arrSolicitud!=null && !arrSolicitud.isEmpty()){
				for(int j=0; j<arrSolicitud.size(); j++){
			
					SolicitudBean solicitudBean = arrSolicitud.get(j);
					
					try{
						documentosList = documentoManager.buscarDocumentoCombo(cargarBusquedaJustificante(solicitudBean.getId()));
					
						if(documentosList!=null && !documentosList.isEmpty()){
							for(int i=0; i<documentosList.size(); i++){
								DocumentoBean docREG = documentosList.get(i);
								
								String nombre = docREG.getNombreAlfresco();
								logger.info("Recuperando el documento: "+nombre);
			
								//Comprobamos que existe el fichero en alfresco
								boolean salidaDoc = false;
								try{
									String ruta = properties.getProperty("sistemaficheros.url.final")+docREG.getUbicacion()+docREG.getNombreAlfresco();
									salidaDoc = documentoManager.existeDocumento(ruta);
			
									//Si no exite documento lo creamos
									if (salidaDoc==false) {
										documentoManager.generarJustificante(docREG.getIdSolicitud().toString());
										
										// Se borra el registro de base de datos
										documentoManager.eliminarDocumentoById(docREG.getId());
									}
								}catch(Exception e){	
									logger.error("Error en la generacion del documento:" + nombre, e);
									desResultado.append("- Error en la generacion del documento:" + nombre);
									resultado = false;
								}
							}
						} else {
							documentoManager.generarJustificante(solicitudBean.getId().toString());
						}
					}catch(Exception e){	
						logger.error("Error en la generacion del documento para la solicitud:" + solicitudBean.getId(), e);
						desResultado.append("- Error en la generacion del documento para la solicitud:" + solicitudBean.getId());
						resultado = false;
					}
				}
			}
			if ( desResultado.toString() != null || !("").equals(desResultado.toString()) )
				desResultado.append("- Se han generado los justificantes de registro");
			
		}catch (Exception e) {
			logger.error("Error prceso generar Pdf:", e);
			desResultado.append("- Error en execute:" + e);
			resultado = false;
		}
		
		Calendar fecha = Calendar.getInstance();
		fecha.set(Calendar.HOUR_OF_DAY, 0);
		fecha.set(Calendar.MINUTE, 0);
		fecha.set(Calendar.SECOND, 0);
		if (desResultado.toString().length()>490) {
			result = desResultado.substring(0, 490);
		}else{
			result = desResultado.toString();
		}
		if (resultado){
			batchMonitorManager.guardarResultado(fecha.getTime(), "OK", result);
		}	else {
			batchMonitorManager.guardarResultado(fecha.getTime(), "ER", result);
		}

		logger.info("Batch Proceso Generacion Justificante de Registro - fin");
		
	}

	/**
	 * Cargar busqueda justificante.
	 *
	 * @param idSolicitud el id solicitud
	 * @return el documento query
	 */
	private DocumentoQuery cargarBusquedaJustificante(Long idSolicitud) {
		
		DocumentoQuery documentoQuery = new DocumentoQuery();
		documentoQuery.addSolicitudIn(idSolicitud);
		Integer tpDocumento = Constantes.TIPO_DOCUMENTO_JUSTIFICANTE_REGISTRO_PDF;
		documentoQuery.addTipoDocumentoIn(tpDocumento);
		tpDocumento = Constantes.TIPO_DOCUMENTO_JUSTIFICANTE_REGISTRO_PDF_CATALAN;
		documentoQuery.addTipoDocumentoIn(tpDocumento);
		tpDocumento = Constantes.TIPO_DOCUMENTO_JUSTIFICANTE_REGISTRO_PDF_EUSKERA;
		documentoQuery.addTipoDocumentoIn(tpDocumento);
		tpDocumento = Constantes.TIPO_DOCUMENTO_JUSTIFICANTE_REGISTRO_PDF_GALLEGO;
		documentoQuery.addTipoDocumentoIn(tpDocumento);
		tpDocumento = Constantes.TIPO_DOCUMENTO_JUSTIFICANTE_REGISTRO_PDF_VALENCIANO;
		documentoQuery.addTipoDocumentoIn(tpDocumento);
		
		return documentoQuery;
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
	 * Obtiene el batch monitor manager.
	 *
	 * @return el batch monitor manager
	 */
	public BatchMonitorManager getBatchMonitorManager() {
		return batchMonitorManager;
	}

	/**
	 * Establece el batch monitor manager.
	 *
	 * @param batchMonitorManager el nuevo batch monitor manager
	 */
	public void setBatchMonitorManager(BatchMonitorManager batchMonitorManager) {
		this.batchMonitorManager = batchMonitorManager;
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
