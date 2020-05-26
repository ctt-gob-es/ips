package es.map.ipsg.action.convocatoria;


import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.CentroGestorQuery;
import es.map.ips.model.TarifaQuery;
import es.map.ipsg.bean.CentroGestorBean;
import es.map.ipsg.form.CrearConvocatoriaForm;
import es.map.ipsg.manager.CentroGestorManager;
import es.map.ipsg.manager.ConvocatoriasManager;
import es.map.ipsg.manager.CuerpoEscalaManager;
import es.map.ipsg.manager.FormaAccesoManager;
import es.map.ipsg.manager.TituloOficialManager;

/**
 * El Class ObtenerTarifaConvocatoriaSpring.
 */
public class ObtenerTarifaConvocatoriaSpring extends AbstractSpring {

	/** La constante BUNDLE_MESSAGES. */
	private static final String BUNDLE_MESSAGES = "MessageResources";
	
	/** La constante RESOURCE_MESSAGES. */
	private static final ResourceBundle RESOURCE_MESSAGES = ResourceBundle
			.getBundle(BUNDLE_MESSAGES);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(ObtenerTarifaConvocatoriaSpring.class);
	
	/** el convocatorias manager. */
	private ConvocatoriasManager convocatoriasManager;
	
	/** el cuerpo escala manager. */
	private CuerpoEscalaManager cuerpoEscalaManager;
	
	/** el centro gestor manager. */
	private CentroGestorManager centroGestorManager;
	
	/** el forma acceso manager. */
	private FormaAccesoManager formaAccesoManager;
	
	/** el titulo oficial manager. */
	private TituloOficialManager tituloOficialManager;




	/**
	 * Crea una nueva obtener tarifa convocatoria spring.
	 */
	public ObtenerTarifaConvocatoriaSpring() {
		try {
			setConvocatoriasManager((ConvocatoriasManager) getBean("convocatoriaManager"));
			setCuerpoEscalaManager((CuerpoEscalaManager) getBean("cuerposEscalaManager"));
			setCentroGestorManager((CentroGestorManager) getBean("centrosGestoresManager"));
			setFormaAccesoManager((FormaAccesoManager) getBean("formasAccesoManager"));
			setTituloOficialManager((TituloOficialManager) getBean("tituloOficialManager"));

		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error ObtenerTarifaConvocatoriaSpring(): ",e);
		}
	
	}

	/**
	 * Obtiene el titulo oficial manager.
	 *
	 * @return el titulo oficial manager
	 */
	public TituloOficialManager getTituloOficialManager() {
		return tituloOficialManager;
	}

	/**
	 * Establece el titulo oficial manager.
	 *
	 * @param tituloOficialManager el nuevo titulo oficial manager
	 */
	public void setTituloOficialManager(
			TituloOficialManager tituloOficialManager) {
		this.tituloOficialManager = tituloOficialManager;
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		
		String menu_convocatoria = RESOURCE_MESSAGES.getString("field.menu.convocatorias");
		this.getRequest().getSession().setAttribute("pagActiva", menu_convocatoria);
		String subMenu_convocatoria = RESOURCE_MESSAGES.getString("field.menuLateral.convocatorias.buscar");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_convocatoria);
		
		getLogger().debug("ObtenerTarifaConvocatoriaSpring -start");
		logger.info("Entra en el Action Obtener Tarifa");
		CrearConvocatoriaForm formulario;
		formulario = (CrearConvocatoriaForm) form;

		try{
			String accion = formulario.getAccion();
			logger.info("Accion: "+accion);
			
				CentroGestorQuery centroGestorQuery = new CentroGestorQuery();
				centroGestorQuery.setId(Integer.valueOf(formulario.getCentroGestor()));
				
				CentroGestorBean centroGestor = centroGestorManager.buscarCentroGestorUnico(centroGestorQuery);
				
				
				String ejercicio = centroGestor.getEjercicio();
				
				String cuerpoEscala = formulario.getCuerpoEscala();
				String formaAcceso= formulario.getFormaAcceso();
				
				logger.info("Ejercicio: "+ejercicio);
				logger.info("CuerpoEscala: "+cuerpoEscala);
				logger.info("FormaAcceso: "+formaAcceso);
				
				int cuerpoEscalaId=Integer.parseInt(cuerpoEscala);
			
				int formaAccesoId=Integer.parseInt(formaAcceso);
				
				TarifaQuery tarifaQuery = new TarifaQuery();
				tarifaQuery.setEjercicio(ejercicio);
				formulario.setImporte(convocatoriasManager.buscarImporte(cuerpoEscalaId, formaAccesoId, tarifaQuery));
				
	
			
	
			getLogger().debug("ObtenerTarifaConvocatoriaSpring -end");
			return "success";
			
		}catch(Exception e){
			logger.warn(e);
			this.getRequest().setAttribute("descripcionError", e.getMessage());
			logger.error("Error ObtenerTarifaConvocatoriaSpring() - doExecute: ",e);
			return "nosuccess";
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
	public void setConvocatoriasManager(
			ConvocatoriasManager convocatoriasManager) {
		this.convocatoriasManager = convocatoriasManager;
	}

	/**
	 * Obtiene el cuerpo escala manager.
	 *
	 * @return el cuerpo escala manager
	 */
	public CuerpoEscalaManager getCuerpoEscalaManager() {
		return cuerpoEscalaManager;
	}

	/**
	 * Establece el cuerpo escala manager.
	 *
	 * @param cuerpoEscalaManager el nuevo cuerpo escala manager
	 */
	public void setCuerpoEscalaManager(CuerpoEscalaManager cuerpoEscalaManager) {
		this.cuerpoEscalaManager = cuerpoEscalaManager;
	}

	/**
	 * Establece el centro gestor manager.
	 *
	 * @param centroGestorManager el nuevo centro gestor manager
	 */
	public void setCentroGestorManager(CentroGestorManager centroGestorManager) {
		this.centroGestorManager = centroGestorManager;
	}

	/**
	 * Obtiene el centro gestor manager.
	 *
	 * @return el centro gestor manager
	 */
	public CentroGestorManager getCentroGestorManager() {
		return centroGestorManager;
	}

	/**
	 * Obtiene el forma acceso manager.
	 *
	 * @return el forma acceso manager
	 */
	public FormaAccesoManager getFormaAccesoManager() {
		return formaAccesoManager;
	}

	/**
	 * Establece el forma acceso manager.
	 *
	 * @param formaAccesoManager el nuevo forma acceso manager
	 */
	public void setFormaAccesoManager(FormaAccesoManager formaAccesoManager) {
		this.formaAccesoManager = formaAccesoManager;
	}

	/**
	 * Obtiene el logger.
	 *
	 * @return el logger
	 */
	public static Logger getLogger() {
		return logger;
	}

}
