package es.map.ipsc.bean;

/**
 * El Class PlantillaPropiosBean.
 */
public class PlantillaPropiosBean {
	
	/** el id. */
	private String id;
	
	/** el campo propio bean. */
	private CamposPropiosBean campoPropioBean;
	
	/** el modelo bean. */
	private ModeloBean modeloBean;
	
	/** el centro gestor bean. */
	private CentroGestorBean centroGestorBean;
	
	/** el convocatoria bean. */
	private ConvocatoriaBean convocatoriaBean;
	
	/** el obligatorio. */
	private boolean obligatorio;
	
	/** el tipo plantilla. */
	private Character tipoPlantilla;
	
	/**
	 * Obtiene el id.
	 *
	 * @return el id
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Establece el id.
	 *
	 * @param id el nuevo id
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * Obtiene el campo propio bean.
	 *
	 * @return el campo propio bean
	 */
	public CamposPropiosBean getCampoPropioBean() {
		return campoPropioBean;
	}
	
	/**
	 * Establece el campo propio bean.
	 *
	 * @param campoPropioBean el nuevo campo propio bean
	 */
	public void setCampoPropioBean(CamposPropiosBean campoPropioBean) {
		this.campoPropioBean = campoPropioBean;
	}
	
	/**
	 * Obtiene el modelo bean.
	 *
	 * @return el modelo bean
	 */
	public ModeloBean getModeloBean() {
		return modeloBean;
	}
	
	/**
	 * Establece el modelo bean.
	 *
	 * @param modeloBean el nuevo modelo bean
	 */
	public void setModeloBean(ModeloBean modeloBean) {
		this.modeloBean = modeloBean;
	}
	
	/**
	 * Obtiene el centro gestor bean.
	 *
	 * @return el centro gestor bean
	 */
	public CentroGestorBean getCentroGestorBean() {
		return centroGestorBean;
	}
	
	/**
	 * Establece el centro gestor bean.
	 *
	 * @param centroGestorBean el nuevo centro gestor bean
	 */
	public void setCentroGestorBean(CentroGestorBean centroGestorBean) {
		this.centroGestorBean = centroGestorBean;
	}
	
	/**
	 * Obtiene el convocatoria bean.
	 *
	 * @return el convocatoria bean
	 */
	public ConvocatoriaBean getConvocatoriaBean() {
		return convocatoriaBean;
	}
	
	/**
	 * Establece el convocatoria bean.
	 *
	 * @param convocatoriaBean el nuevo convocatoria bean
	 */
	public void setConvocatoriaBean(ConvocatoriaBean convocatoriaBean) {
		this.convocatoriaBean = convocatoriaBean;
	}
	
	/**
	 * Comprueba si es obligatorio.
	 *
	 * @return verdadero, si es obligatorio
	 */
	public boolean isObligatorio() {
		return obligatorio;
	}
	
	/**
	 * Establece el obligatorio.
	 *
	 * @param obligatorio el nuevo obligatorio
	 */
	public void setObligatorio(boolean obligatorio) {
		this.obligatorio = obligatorio;
	}
	
	/**
	 * Obtiene el tipo plantilla.
	 *
	 * @return el tipo plantilla
	 */
	public Character getTipoPlantilla() {
		return tipoPlantilla;
	}
	
	/**
	 * Establece el tipo plantilla.
	 *
	 * @param tipoPlantilla el nuevo tipo plantilla
	 */
	public void setTipoPlantilla(Character tipoPlantilla) {
		this.tipoPlantilla = tipoPlantilla;
	}
	

	
}
