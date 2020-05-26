package es.map.ipsg.service;

import org.apache.log4j.Logger;

import es.map.ipsg.junit.AbstractSpringCommonTestCaseIPSG;
import es.map.ipsg.manager.ConvocatoriasManager;
import es.map.ipsg.manager.DocumentoManager;
import es.map.ipsg.quartz.PublicacionConvocatoriasAutomaticasAction;

/**
 * El Class BatchPublicacionConvocatoriasTest.
 */
public class BatchPublicacionConvocatoriasTest extends AbstractSpringCommonTestCaseIPSG{

	/** el convocatorias manager. */
	private ConvocatoriasManager convocatoriasManager;
	
	/** el documento manager. */
	private DocumentoManager documentoManager;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(BatchPublicacionConvocatoriasTest.class);


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
	}
	
	/**
	 * Test search.
	 */
	public void testSearch() {
		
		try {
			
			logger.info("Batch Publicacion Convocatorias - inicio");
			
			PublicacionConvocatoriasAutomaticasAction automaticasAction = new PublicacionConvocatoriasAutomaticasAction();
			automaticasAction.executeTest(convocatoriasManager, documentoManager);
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
	
}
