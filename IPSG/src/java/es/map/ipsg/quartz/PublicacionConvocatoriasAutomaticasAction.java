package es.map.ipsg.quartz;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import org.apache.log4j.Logger;

import es.map.ips.common.spring.ApplicationContextProvider;
import es.map.ips.model.ConvocatoriaQuery;
import es.map.ips.model.DocumentoQuery;
import es.map.ips.model.EstadoConvocatoriaQuery;
import es.map.ipsg.action.convocatoria.ActualizarEstadoConvocatoriaSpring;
import es.map.ipsg.bean.ConvocatoriasBean;
import es.map.ipsg.bean.DocumentoBean;
import es.map.ipsg.manager.ConvocatoriasManager;
import es.map.ipsg.manager.DocumentoManager;
import es.map.ipsg.manager.UsuarioManager;
import es.map.ipsg.util.Constantes;
import es.map.ipsg.util.UtilesIPSG;


/**
 * El Class PublicacionConvocatoriasAutomaticasAction.
 */
public class PublicacionConvocatoriasAutomaticasAction {
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(PublicacionConvocatoriasAutomaticasAction.class);
	
	/** el convocatorias manager. */
	private ConvocatoriasManager convocatoriasManager;
	
	/** el documento manager. */
	private DocumentoManager documentoManager;
	
	/** el properties. */
	private Properties properties;
	
	/**
	 * Execute test.
	 *
	 * @param convocatoriasManager el convocatorias manager
	 * @param documentoManager el documento manager
	 */
	public void executeTest(ConvocatoriasManager convocatoriasManager, DocumentoManager documentoManager){
		setConvocatoriasManager(convocatoriasManager);
		setDocumentoManager(documentoManager);
	}
	
	/**
	 * Execute.
	 */
	public void execute() {
		
		logger.info("Batch Publicacion Convocatorias - inicio");
		
		try {
			try {
				convocatoriasManager = (ConvocatoriasManager) ApplicationContextProvider.getInstance().getBean("convocatoriaManager");
				documentoManager = (DocumentoManager) ApplicationContextProvider.getInstance().getBean("documentoManager");
				properties = (Properties) ApplicationContextProvider.getInstance().getBean("propertiesBean");
			} catch (Exception e) {
				logger.info("Contexto cargado por ejecucion de Test.",e);
			}
			ActualizarEstadoConvocatoriaSpring actualizarEstado = new ActualizarEstadoConvocatoriaSpring();
			Integer idUsuarioAutomático = actualizarEstado.recuperaIdUsuarioAutomatico();
			
			//Creamos la query para obtener las convocatorias de estado Aprobado(7)
			ConvocatoriaQuery convocatoriaQuery = new ConvocatoriaQuery();
			EstadoConvocatoriaQuery estadoConvocatoria = new EstadoConvocatoriaQuery();
			estadoConvocatoria.setId(Constantes.ESTADO_CONVOCATORIA_APROBADO);
			convocatoriaQuery.setEstadoConvocatoria(estadoConvocatoria);
			
			//Obtenemos la lista de las convocatorias de estado Aprobado
			ArrayList<ConvocatoriasBean> listaConvocatoriasTotal = new ArrayList<ConvocatoriasBean>();
			listaConvocatoriasTotal = convocatoriasManager.buscarConvocatoriasAll(convocatoriaQuery);
			
			if (listaConvocatoriasTotal != null && !listaConvocatoriasTotal.isEmpty()) {

				//Filtramos las convocatorias por (FechaInicio < FechaActual < FechaFin)
				ArrayList<ConvocatoriasBean> listaConvocatorias = new ArrayList<ConvocatoriasBean>();
				String formato = "dd/MM/yyyy";
				SimpleDateFormat sdf = new SimpleDateFormat(formato);
				
				for (int j=0; j< listaConvocatoriasTotal.size(); j++) {
					String fechaInicio = listaConvocatoriasTotal.get(j).getFechaInicio();
					String fechaFin = listaConvocatoriasTotal.get(j).getFechaFin();
					
					//Fecha de inicio y fecha de fin vienen completas
					if (fechaInicio != null && fechaFin != null && !"".equals(fechaInicio) && !"".equals(fechaFin)) {
						Date fechaInicioAux = sdf.parse(fechaInicio);
						Date fechaFinAux = sdf.parse(fechaFin);
						Date fechaActual = UtilesIPSG.obtenerFechaActual();
						
						if ((fechaActual.after(fechaInicioAux) || fechaActual.equals(fechaInicioAux)) 
								&& (fechaActual.before(fechaFinAux) || fechaActual.equals(fechaFinAux))) {
	
							listaConvocatorias.add(listaConvocatoriasTotal.get(j));
						}						
					}					
				}
				
				//Cambiamos el estado de la lista obtenida a convocatorias de estado Publicado(5)
				if (listaConvocatorias != null && !listaConvocatorias.isEmpty()) {
					for (int i=0; i< listaConvocatorias.size(); i++) {
						ConvocatoriasBean convocatoriaBean = new ConvocatoriasBean();
						convocatoriaBean = listaConvocatorias.get(i);				
						
						ConvocatoriaQuery convocatoriaQueryAux = new ConvocatoriaQuery();
						convocatoriaQueryAux.setId(Long.valueOf(convocatoriaBean.getIdConvocatoria()));
						EstadoConvocatoriaQuery estadoConvocatoriaAux = new EstadoConvocatoriaQuery();
						estadoConvocatoriaAux.setId(Constantes.ESTADO_CONVOCATORIA_PUBLICADO);						
						
						convocatoriasManager.actualizarEstado(convocatoriaQueryAux, estadoConvocatoriaAux);
						actualizarEstado.generarRegistroLogGenerico(idUsuarioAutomático, Constantes.ESTADO_CONVOCATORIA_APROBADO, 
								Constantes.ESTADO_CONVOCATORIA_PUBLICADO, Long.valueOf(convocatoriaBean.getIdConvocatoria()));
						
					}
				}
			}

			
			/*
			 * Ahora cambiamos las convocatorias publicados con fecha actual superior a 
			 * la fechaFin del estado publicado al estado finalizado
			 */

			//Creamos la query para obtener las convocatorias de estado Publicado(5)
			ConvocatoriaQuery convocatoriaQueryPub = new ConvocatoriaQuery();
			EstadoConvocatoriaQuery estadoConvocatoriaPub = new EstadoConvocatoriaQuery();
			estadoConvocatoriaPub.setId(Constantes.ESTADO_CONVOCATORIA_PUBLICADO);
			convocatoriaQueryPub.setEstadoConvocatoria(estadoConvocatoriaPub);
			
			//Obtenemos la lista de las convocatorias de estado Publicado
			ArrayList<ConvocatoriasBean> listaConvocatoriasPub = new ArrayList<ConvocatoriasBean>();
			listaConvocatoriasPub = convocatoriasManager.buscarConvocatoriasAll(convocatoriaQueryPub);
			
			if (listaConvocatoriasPub != null && !listaConvocatoriasPub.isEmpty()) {
				
				//Filtramos las convocatorias por (FechaActual > FechaFin)
				ArrayList<ConvocatoriasBean> listaConvocatoriasFinalizadas = new ArrayList<ConvocatoriasBean>();
				String formato = "dd/MM/yyyy";
				SimpleDateFormat sdf = new SimpleDateFormat(formato);
				
				for (int j=0; j< listaConvocatoriasPub.size(); j++) {
					String fechaFin = listaConvocatoriasPub.get(j).getFechaFin();
					
					//fecha de fin vienen completas
					if (fechaFin != null && !"".equals(fechaFin)) {						
						Date fechaFinAux = sdf.parse(fechaFin);
						Date fechaActual = UtilesIPSG.obtenerFechaActual();
						
						if (fechaActual.after(fechaFinAux)) {
							
							listaConvocatoriasFinalizadas.add(listaConvocatoriasPub.get(j));
						}						
					}						
				}
				
				//Cambiamos el estado de la lista obtenida a convocatorias de estado Finalizado(3)
				if (listaConvocatoriasFinalizadas != null && !listaConvocatoriasFinalizadas.isEmpty()) {
					for (int i=0; i< listaConvocatoriasFinalizadas.size(); i++) {
						ConvocatoriasBean convocatoriaBean = new ConvocatoriasBean();
						convocatoriaBean = listaConvocatoriasFinalizadas.get(i);				
						
						ConvocatoriaQuery convocatoriaQueryAux = new ConvocatoriaQuery();
						convocatoriaQueryAux.setId(Long.valueOf(convocatoriaBean.getIdConvocatoria()));
						EstadoConvocatoriaQuery estadoConvocatoriaAux = new EstadoConvocatoriaQuery();
						estadoConvocatoriaAux.setId(Constantes.ESTADO_CONVOCATORIA_FINALIZADO);						
						
						convocatoriasManager.actualizarEstado(convocatoriaQueryAux, estadoConvocatoriaAux);
						actualizarEstado.generarRegistroLogGenerico(idUsuarioAutomático, Constantes.ESTADO_CONVOCATORIA_PUBLICADO, 
								Constantes.ESTADO_CONVOCATORIA_FINALIZADO, Long.valueOf(convocatoriaBean.getIdConvocatoria()));
						
						// Migracion de Alfresco a FileSystem
						// Mover documentos asociados a la convocatoria a su ubicacion definitiva de Solo Lectura
						// Cuando el directorio de escritura queda vacio, lo borra.
						ArrayList<DocumentoBean> listaDocumentos = new ArrayList<DocumentoBean>();
						DocumentoQuery documentoQuery = new DocumentoQuery();
						documentoQuery.addConvocatoriaIn(Long.parseLong(convocatoriaBean.getIdConvocatoria()));
						listaDocumentos = documentoManager.buscarDocumentoCombo(documentoQuery);
						documentoManager.copiarFicheros(listaDocumentos, properties.getProperty("sistemaficheros.url.escritura"));
					}
				}
			}
			
		}catch (Exception e) {
			logger.error("Error en publicacion convocatorias automaticas:", e);
			
		}
		
		logger.info("Batch Publicacion Convocatorias - fin");
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

}
