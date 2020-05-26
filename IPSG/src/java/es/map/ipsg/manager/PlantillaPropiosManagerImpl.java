package es.map.ipsg.manager;

import java.util.ArrayList;

import es.map.ips.common.model.SearchResult;
import es.map.ips.dao.PlantillaPropiosDAO;
import es.map.ips.model.CamposPropios;
import es.map.ips.model.CentroGestor;
import es.map.ips.model.Convocatoria;
import es.map.ips.model.Ministerio;
import es.map.ips.model.Modelo;
import es.map.ips.model.PlantillaPropios;
import es.map.ips.model.PlantillaPropiosQuery;
import es.map.ipsg.bean.CamposPropiosBean;
import es.map.ipsg.bean.CentroGestorBean;
import es.map.ipsg.bean.ModeloBean;
import es.map.ipsg.bean.PlantillaPropiosBean;

/**
 * El Class PlantillaPropiosManagerImpl.
 */
public class PlantillaPropiosManagerImpl implements PlantillaPropiosManager{
	
	/** el plantilla propios DAO. */
	//Variables
	private PlantillaPropiosDAO plantillaPropiosDAO;

	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.PlantillaPropiosManager#buscarPlantillaPropiosAll(es.map.ips.model.PlantillaPropiosQuery)
	 */
	public ArrayList<PlantillaPropiosBean> buscarPlantillaPropiosAll(PlantillaPropiosQuery plantillaPropiosQuery) {
		SearchResult<PlantillaPropios> plantillaPropios = plantillaPropiosDAO.search(plantillaPropiosQuery);
		ArrayList<PlantillaPropiosBean> arrPlantillaPropiosBean= new ArrayList<PlantillaPropiosBean>();
		if(plantillaPropios != null){
			for(int i=0;i<plantillaPropios.getResults().size();i++){
				PlantillaPropiosBean aux;
				aux = toPlantillaPropiosBeanGestor(plantillaPropios.getResults().get(i));
				if(aux != null){
					arrPlantillaPropiosBean.add(aux);
				}
			}	
		}
		return arrPlantillaPropiosBean;	
	}
	
	/**
	 * To plantilla propios bean gestor.
	 *
	 * @param plantillaPropios el plantilla propios
	 * @return el plantilla propios bean
	 */
	public PlantillaPropiosBean toPlantillaPropiosBeanGestor(PlantillaPropios plantillaPropios){
		PlantillaPropiosBean plantillaPropiosBean = new PlantillaPropiosBean();
		
		plantillaPropiosBean.setId(Long.toString(plantillaPropios.getIdPlantillaPropios()));
		plantillaPropiosBean.setModeloBean(toModeloBean(plantillaPropios.getModelo()));
		plantillaPropiosBean.setTipoPlantilla(plantillaPropios.getTipoPlantilla());
		if(plantillaPropios.getObligatorio()!= null && plantillaPropios.getObligatorio().equals('S')){
			plantillaPropiosBean.setObligatorio(true);
		}else{
			plantillaPropiosBean.setObligatorio(false);
		}
		plantillaPropiosBean.setCampoPropioBean(toCamposPropiosBean(plantillaPropios.getCamposPropios()));
		if(plantillaPropios.getCentroGestor()!=null){
			plantillaPropiosBean.setCentroGestorBean(toCentroGestorBean(plantillaPropios.getCentroGestor()));
		}
		
			
		return plantillaPropiosBean;
	}
	
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.PlantillaPropiosManager#guardarPlantillaPropios(es.map.ipsg.bean.PlantillaPropiosBean)
	 */
	public Long guardarPlantillaPropios(PlantillaPropiosBean plantillaPropiosBean) {
		PlantillaPropios plantillaPropios = toPlantillaPropiosNuevoGestor(plantillaPropiosBean);
		Long idPlantillaPropios = plantillaPropiosDAO.insert(plantillaPropios);
		return idPlantillaPropios;
		
	}
	
	/**
	 * To modelo bean.
	 *
	 * @param modelo el modelo
	 * @return el modelo bean
	 */
	private ModeloBean toModeloBean(Modelo modelo) {		
			
			ModeloBean auxModelo = new ModeloBean();
			auxModelo.setId(modelo.getIdModelo());
			auxModelo.setDescripcion(modelo.getDescripcion());
			auxModelo.setNumModelo(modelo.getNumModelo());
			auxModelo.setCamposPropios(modelo.getCamposPropioses());		
			auxModelo.setCentrosGestores(modelo.getCentroGestors());
			auxModelo.setFechaAlta(modelo.getFechaAlta());
			auxModelo.setSolicitudComun(modelo.getSolicitudComuns());
			
			if(modelo.getCentroGestors() != null){
				auxModelo.setCentrosGestores(modelo.getCentroGestors());
			}
			
			return auxModelo;
		}
	
	/**
	 * To campos propios bean.
	 *
	 * @param camposPropios el campos propios
	 * @return el campos propios bean
	 */
	private CamposPropiosBean toCamposPropiosBean(CamposPropios camposPropios) {					
		CamposPropiosBean auxCamposPropios = new CamposPropiosBean();
		auxCamposPropios.setDescripcion(camposPropios.getDescripcion());
		auxCamposPropios.setIdModelo(Integer.toString(camposPropios.getModelo().getIdModelo()));
		auxCamposPropios.setId(Long.valueOf(Integer.toString(camposPropios.getIdCampo())));
		auxCamposPropios.setTituloCampo(camposPropios.getCampo());
		auxCamposPropios.setCampo(camposPropios.getCampo());
		return auxCamposPropios;
	}
	
	/**
	 * To centro gestor bean.
	 *
	 * @param centroGestor el centro gestor
	 * @return el centro gestor bean
	 */
	private CentroGestorBean toCentroGestorBean(CentroGestor centroGestor) {		
		
		CentroGestorBean auxCentroGestor = new CentroGestorBean();
		auxCentroGestor.setId(centroGestor.getIdCentroGestor());
		auxCentroGestor.setSiglas(centroGestor.getSiglas());
		auxCentroGestor.setDescripcion(centroGestor.getDescripcion());
		auxCentroGestor.setCodigo(centroGestor.getCodigo());		
		auxCentroGestor.setEjercicio(centroGestor.getEjercicio());
		auxCentroGestor.setMinisterio(centroGestor.getMinisterio().getSiglas());
		
		if(centroGestor.getMinisterio() != null){
			auxCentroGestor.setIdMinisterio(centroGestor.getMinisterio().getId());
		}
		if(centroGestor.getPlantilla() != null){
			auxCentroGestor.setPlantilla(centroGestor.getPlantilla());
		}
		auxCentroGestor.setModelo(centroGestor.getModelo());
		auxCentroGestor.setEstado(centroGestor.getEstado());
		auxCentroGestor.setIdCentroGestorSust(centroGestor.getIdCentroGestorPrevio());
		auxCentroGestor.setFechaSustitucion(centroGestor.getFechaSustitucion());
		if(centroGestor.getVisible() != null)
		{	
			if(centroGestor.getVisible().equals('S'))
			{
				auxCentroGestor.setVisibilidad(true);
			}
			else if(centroGestor.getVisible().equals('N'))
			{
				auxCentroGestor.setVisibilidad(false);
			}
		}
		else
		{
			auxCentroGestor.setVisibilidad(false);
		}	
		return auxCentroGestor;
	}
	
	/**
	 * To plantilla propios nuevo gestor.
	 *
	 * @param plantillaPropiosBean el plantilla propios bean
	 * @return el plantilla propios
	 */
	public PlantillaPropios toPlantillaPropiosNuevoGestor(PlantillaPropiosBean plantillaPropiosBean){
		PlantillaPropios plantillaPropios = new PlantillaPropios();
		
		if(plantillaPropiosBean.isObligatorio()){
			plantillaPropios.setObligatorio('S');
		}else{
			plantillaPropios.setObligatorio('N');
		}
		plantillaPropios.setTipoPlantilla(plantillaPropiosBean.getTipoPlantilla());
		
		if(plantillaPropiosBean.getCentroGestorBean()!=null){
			plantillaPropios.setCentroGestor(toCentroGestor(plantillaPropiosBean.getCentroGestorBean()));
		}
		if(plantillaPropiosBean.getModeloBean()!=null){
			plantillaPropios.setModelo(toModelo(plantillaPropiosBean.getModeloBean()));
		}
		if(plantillaPropiosBean.getCampoPropioBean()!=null){
			plantillaPropios.setCamposPropios(toCamposPropios(plantillaPropiosBean.getCampoPropioBean()));
		}
		
		if(plantillaPropiosBean.getConvocatoriaBean()!=null && !"".equals(plantillaPropiosBean.getConvocatoriaBean())){
			Convocatoria convocatoria = new Convocatoria();
			convocatoria.setId(Long.valueOf(plantillaPropiosBean.getConvocatoriaBean().getIdConvocatoria()));
			plantillaPropios.setConvocatoria(convocatoria);
		}
		
		return plantillaPropios;
	}
	
	/**
	 * To campos propios.
	 *
	 * @param campoPropioBean el campo propio bean
	 * @return el campos propios
	 */
	public CamposPropios toCamposPropios(CamposPropiosBean campoPropioBean){
		CamposPropios campoPropio = new CamposPropios();
		
		if(campoPropioBean.getIdModelo()!=null){
			Modelo modelo = new Modelo();
			modelo.setIdModelo(Integer.parseInt(campoPropioBean.getIdModelo()));
			campoPropio.setModelo(modelo);
		}
		campoPropio.setCampo(campoPropioBean.getTituloCampo());
		campoPropio.setDescripcion(campoPropioBean.getDescripcion());
		if(campoPropioBean.getId()!=null){
			campoPropio.setIdCampo(Integer.valueOf(String.valueOf(campoPropioBean.getId())));
		}
		return campoPropio;
	}
	
	/**
	 * To modelo.
	 *
	 * @param modeloBean el modelo bean
	 * @return el modelo
	 */
	public Modelo toModelo(ModeloBean modeloBean){
		Modelo modelo = new Modelo();
		//cogemos el id par modificar el modelo. En crear modelo no se setea el id
		Integer id=modeloBean.getId();
		if(id!=null){
			modelo.setIdModelo(modeloBean.getId());
		}
		modelo.setDescripcion(modeloBean.getDescripcion());
		modelo.setNumModelo(modeloBean.getNumModelo());
		//cogemos la fecha para crear el modelo. En modifica el modelo no se setea
		if(modeloBean.getFechaAlta()!=null){
			modelo.setFechaAlta(modeloBean.getFechaAlta());
		}
		return modelo;
	}
	
	/**
	 * To centro gestor.
	 *
	 * @param centroGestorBean el centro gestor bean
	 * @return el centro gestor
	 */
	public CentroGestor toCentroGestor(CentroGestorBean centroGestorBean){
		CentroGestor centroGestor = new CentroGestor();
		
		centroGestor.setIdCentroGestor(centroGestorBean.getId());		
		centroGestor.setSiglas(centroGestorBean.getSiglas());
		centroGestor.setDescripcion(centroGestorBean.getDescripcion());
		centroGestor.setEjercicio(centroGestorBean.getEjercicio());
		centroGestor.setCodigo(centroGestorBean.getCodigo());
		centroGestor.setModelo(centroGestorBean.getModelo());
		
		Ministerio ministerio = new Ministerio();
		ministerio.setId(centroGestorBean.getIdMinisterio());
		centroGestor.setMinisterio(ministerio);
		
		centroGestor.setPlantilla(centroGestorBean.getPlantilla());
		
		centroGestor.setEstado(centroGestorBean.getEstado());
		
		if(centroGestorBean.getIdCentroGestorSust()==null || centroGestorBean.getIdCentroGestorSust().equals(0)){
			centroGestor.setIdCentroGestorPrevio(null);
			centroGestor.setFechaSustitucion(null);
		}else{
			centroGestor.setIdCentroGestorPrevio(centroGestorBean.getIdCentroGestorSust());
			centroGestor.setFechaSustitucion(centroGestorBean.getFechaSustitucion());
		}
		if(centroGestorBean.getVisibilidad() != null){
			if(centroGestorBean.getVisibilidad() == true)
			{
				centroGestor.setVisible('S');
			}
			else if(centroGestorBean.getVisibilidad() == false)
			{
				centroGestor.setVisible('N');
			}
		}else{
			centroGestor.setVisible('N');
		}	
				
		return centroGestor;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.PlantillaPropiosManager#borrarPlantillasPropios(es.map.ips.model.PlantillaPropiosQuery)
	 */
	public void borrarPlantillasPropios(PlantillaPropiosQuery plantillaPropiosQuery) {
		// TODO Auto-generated method stub
		plantillaPropiosDAO.delete(plantillaPropiosQuery.getIdPlantillaPropios());
	}
	
	
	/**
	 * To plantilla propios.
	 *
	 * @param plantillaPropiosBean el plantilla propios bean
	 * @return el plantilla propios
	 */
	public PlantillaPropios toPlantillaPropios(PlantillaPropiosBean plantillaPropiosBean){
		PlantillaPropios plantillaPropios = new PlantillaPropios();
		
		if(plantillaPropiosBean.getId()!=null){
			plantillaPropios.setIdPlantillaPropios(Long.valueOf(plantillaPropiosBean.getId()));
		}
		if(plantillaPropiosBean.isObligatorio()){
			plantillaPropios.setObligatorio('S');
		}else{
			plantillaPropios.setObligatorio('N');
		}
		plantillaPropios.setTipoPlantilla(plantillaPropiosBean.getTipoPlantilla());
		
		if(plantillaPropiosBean.getConvocatoriaBean()!=null){
			Convocatoria conv = new Convocatoria();
			conv.setId(Long.valueOf(plantillaPropiosBean.getConvocatoriaBean().getIdConvocatoria()));
			plantillaPropios.setConvocatoria(conv);
		}
		
		if(plantillaPropiosBean.getCentroGestorBean()!=null){
			CentroGestor centroGestor = new CentroGestor();
			centroGestor.setIdCentroGestor((plantillaPropiosBean.getCentroGestorBean().getId()));
			plantillaPropios.setCentroGestor(centroGestor);
		}
		
		if(plantillaPropiosBean.getModeloBean()!=null){
			Modelo modelo = new Modelo();
			modelo.setIdModelo(plantillaPropiosBean.getModeloBean().getId());
			plantillaPropios.setModelo(modelo);
		}
		
		if(plantillaPropiosBean.getCampoPropioBean()!=null){
			CamposPropios camposPropios = new CamposPropios();
			camposPropios.setIdCampo(Integer.parseInt(String.valueOf(plantillaPropiosBean.getCampoPropioBean().getId())));
			plantillaPropios.setCamposPropios(camposPropios);
		}
		
		return plantillaPropios;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.PlantillaPropiosManager#obtenerPlantillaPropiosById(es.map.ips.model.PlantillaPropiosQuery)
	 */
	@Override
	public ArrayList<PlantillaPropiosBean> obtenerPlantillaPropiosById(PlantillaPropiosQuery plantillaPropiosQuery) {
		SearchResult<PlantillaPropios> plantillaPropios = plantillaPropiosDAO.search(plantillaPropiosQuery);
		
		ArrayList<PlantillaPropiosBean> arrPlantillaPropiosBean= new ArrayList<PlantillaPropiosBean>();
		if(plantillaPropios != null){
			for(int i=0;i<plantillaPropios.getResults().size();i++){
				PlantillaPropiosBean aux;
				aux = toPlantillaPropiosBeanConvocatoria(plantillaPropios.getResults().get(i));
				if(aux != null){
					arrPlantillaPropiosBean.add(aux);
				}
			}	
		}
		return arrPlantillaPropiosBean;	
	}
	
	/**
	 * To plantilla propios bean convocatoria.
	 *
	 * @param plantillaPropios el plantilla propios
	 * @return el plantilla propios bean
	 */
	public PlantillaPropiosBean toPlantillaPropiosBeanConvocatoria(PlantillaPropios plantillaPropios){
		PlantillaPropiosBean plantillaPropiosBean = new PlantillaPropiosBean();
		
		plantillaPropiosBean.setId(Long.toString(plantillaPropios.getIdPlantillaPropios()));

		plantillaPropiosBean.setModeloBean(toModeloBean(plantillaPropios.getModelo()));
		plantillaPropiosBean.setTipoPlantilla(plantillaPropios.getTipoPlantilla());
		if(plantillaPropios.getObligatorio()!=null && plantillaPropios.getObligatorio().equals('S')){
			plantillaPropiosBean.setObligatorio(true);
		}else{
			plantillaPropiosBean.setObligatorio(false);
		}
		if(plantillaPropios.getCentroGestor()!=null){
			plantillaPropiosBean.setCentroGestorBean(toCentroGestorBean(plantillaPropios.getCentroGestor()));
		}
		plantillaPropiosBean.setCampoPropioBean(toCamposPropiosBean(plantillaPropios.getCamposPropios()));
		plantillaPropiosBean.setTipoPlantilla(plantillaPropios.getTipoPlantilla());
			
		return plantillaPropiosBean;
	}
	
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.PlantillaPropiosManager#actualizarPlantillaPropios(es.map.ipsg.bean.PlantillaPropiosBean)
	 */
	@Override
	public void actualizarPlantillaPropios(PlantillaPropiosBean plantillaPropiosBean) {
		PlantillaPropios plantillaPropios = toPlantillaPropios(plantillaPropiosBean);
		plantillaPropiosDAO.update(plantillaPropios);
	}
	
	
	
	/**
	 * Obtiene el plantilla propios DAO.
	 *
	 * @return el plantilla propios DAO
	 */
	public PlantillaPropiosDAO getPlantillaPropiosDAO() {
		return plantillaPropiosDAO;
	}

	/**
	 * Establece el plantilla propios DAO.
	 *
	 * @param plantillaPropiosDAO el nuevo plantilla propios DAO
	 */
	public void setPlantillaPropiosDAO(PlantillaPropiosDAO plantillaPropiosDAO) {
		this.plantillaPropiosDAO = plantillaPropiosDAO;
	}

	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.PlantillaPropiosManager#obtenerPlantillaPropiosById()
	 */
	@Override
	public void obtenerPlantillaPropiosById() {
		// TODO Auto-generated method stub
		
	}


}
