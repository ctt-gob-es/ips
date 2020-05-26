package es.map.ipsg.service;

import org.springframework.beans.factory.annotation.Autowired;

import es.map.ips.common.spring.SpringMappingManager;
import es.map.ipsg.junit.AbstractSpringCommonTestCaseIPSG;
import es.map.ipsg.manager.ConvocatoriasManager;
import es.map.ipsg.manager.DocumentoManager;
import es.map.ipsg.manager.SolicitudesManager;
import es.map.ipsg.quartz.MantenimientoSistemaFicherosAction;


/**
 * El Class BatchMantenimientoSistemaFicherosTest.
 */
public class BatchMantenimientoSistemaFicherosTest extends AbstractSpringCommonTestCaseIPSG{

	/** el convocatorias manager. */
	private ConvocatoriasManager convocatoriasManager;
	
	/** el documento manager. */
	private DocumentoManager documentoManager;
	
	/** el solicitudes manager. */
	private SolicitudesManager solicitudesManager;

	/** el spring mapping manager. */
	@Autowired
	SpringMappingManager springMappingManager;
	
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
	}
	
	/**
	 * Test search.
	 */
	public void testSearch() {
		
		try {
			
//			logger.info("Batch Publicacion Convocatorias - inicio");
			
			MantenimientoSistemaFicherosAction automaticasAction = new MantenimientoSistemaFicherosAction();
			automaticasAction.executeTest(convocatoriasManager, documentoManager, solicitudesManager);
			automaticasAction.execute();
			
//			logger.info("Batch Publicacion Convocatorias - fin");
			
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
	
}
