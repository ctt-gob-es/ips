package es.map.ipsg.service;

import org.apache.log4j.Logger;

import es.map.ipsg.junit.AbstractSpringCommonTestCaseIPSG;
import es.map.ipsg.manager.ConvocatoriasManager;
import es.map.ipsg.manager.DocumentoManager;
import es.map.ipsg.manager.ParametroConfiguracionManager;
import es.map.ipsg.manager.SolicitudesManager;
import es.map.ipsg.quartz.ProcesoMigracionAlfresco;


/**
 * El Class ProcesoMigracionAlfrescoTest.
 */
public class ProcesoMigracionAlfrescoTest extends AbstractSpringCommonTestCaseIPSG{

	/** el convocatorias manager. */
	private ConvocatoriasManager convocatoriasManager;
	
	/** el documento manager. */
	private DocumentoManager documentoManager;
	
	/** el solicitudes manager. */
	private SolicitudesManager solicitudesManager;
	
	/** el parametro configuracion manager. */
	private ParametroConfiguracionManager parametroConfiguracionManager;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(ProcesoMigracionAlfrescoTest.class);
	
	/**
	 * Carga de los datos para el Junit.
	 *
	 * @throws Exception el exception
	 */
	@SuppressWarnings("deprecation")
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		convocatoriasManager = (ConvocatoriasManager) getBean("convocatoriaManager");
		documentoManager = (DocumentoManager) getBean("documentoManager");
		solicitudesManager = (SolicitudesManager) getBean("solicitudesManager");
		parametroConfiguracionManager = (ParametroConfiguracionManager) getBean("parametroConfiguracionManager");
	}
	
	/**
	 * Test search.
	 */
	public void testSearch() {
		
		try {
			
			logger.info("Batch Publicacion Convocatorias - inicio");
			
			ProcesoMigracionAlfresco automaticasAction = new ProcesoMigracionAlfresco();
			automaticasAction.execute();
			
			logger.info("Batch Publicacion Convocatorias - fin");
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
			fail();
		}
		
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

	/**
	 * Obtiene el parametro configuracion manager.
	 *
	 * @return el parametro configuracion manager
	 */
	public ParametroConfiguracionManager getParametroConfiguracionManager() {
		return parametroConfiguracionManager;
	}

	/**
	 * Establece el parametro configuracion manager.
	 *
	 * @param parametroConfiguracionManager el nuevo parametro configuracion manager
	 */
	public void setParametroConfiguracionManager(
			ParametroConfiguracionManager parametroConfiguracionManager) {
		this.parametroConfiguracionManager = parametroConfiguracionManager;
	}	
	
}
