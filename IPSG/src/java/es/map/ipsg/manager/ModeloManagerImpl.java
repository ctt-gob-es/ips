package es.map.ipsg.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import es.map.ips.common.exception.ApplicationException;
import es.map.ips.common.model.OrderType;
import es.map.ips.common.model.SearchResult;
import es.map.ips.dao.ModeloDAO;
import es.map.ips.model.MinisterioQuery;
import es.map.ips.model.Modelo;
import es.map.ips.model.ModeloQuery;
import es.map.ipsg.bean.ModeloBean;

/**
 * El Class ModeloManagerImpl.
 */
public class ModeloManagerImpl implements ModeloManager{
	
	/** el modelo DAO. */
	//Variables
	private ModeloDAO modeloDAO;

	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.ModeloManager#buscarModeloCombo(es.map.ips.model.ModeloQuery)
	 */
	@Override
	public List<ModeloBean> buscarModeloCombo(ModeloQuery modeloQuery) {
		modeloQuery.addOrder(MinisterioQuery.ID, OrderType.ASC);
		SearchResult<Modelo> modelo = buscarModelos(modeloQuery);
		
		ArrayList<ModeloBean> arrModeloBean= new ArrayList<ModeloBean>();
		for(int i=0;i<modelo.getResults().size();i++){
			ModeloBean aux;
			aux = toModeloComboBean(modelo.getResults().get(i));
			if(aux != null){
				arrModeloBean.add(aux);
			}
		}	
		return arrModeloBean;		
	}
	
	/**
	 * Buscar modelos.
	 *
	 * @param modeloQuery el modelo query
	 * @return el search result
	 */
	private SearchResult<Modelo> buscarModelos(ModeloQuery modeloQuery) {
		ApplicationException.assertNotNull(modeloQuery, "cuerpoEscalaQuery no puede ser null");
		return modeloDAO.search(modeloQuery);
	}
	
	/**
	 * To modelo combo bean.
	 *
	 * @param modelo el modelo
	 * @return el modelo bean
	 */
	public ModeloBean toModeloComboBean(Modelo modelo) {
		int id = modelo.getIdModelo();
		String numModelo = modelo.getNumModelo();
		String descripcion = modelo.getDescripcion();
		Date fechaAlta = modelo.getFechaAlta();	
		
		ModeloBean auxModelo = new ModeloBean();
		auxModelo.setId(id);
		auxModelo.setNumModelo(numModelo);
		auxModelo.setDescripcion(descripcion);
		auxModelo.setFechaAlta(fechaAlta);
		
		return auxModelo;
	}
	
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.ModeloManager#buscarModeloComboTodos()
	 */
	public ArrayList<ModeloBean> buscarModeloComboTodos() {
		ModeloQuery modeloQuery = new ModeloQuery();
		modeloQuery.addOrder(ModeloQuery.NUMMODELO, OrderType.ASC);
		SearchResult<Modelo> modelo = modeloDAO.search(modeloQuery);
		ArrayList<ModeloBean> arrModeloBean= new ArrayList<ModeloBean>();
		for(int i=0;i<modelo.getResults().size();i++){
			ModeloBean aux;
			aux = toModeloBean(modelo.getResults().get(i),0);
			if(aux != null){
				arrModeloBean.add(aux);
			}
		}	
		return arrModeloBean;	
	}
	
	/**
	 * To modelo bean.
	 *
	 * @param modelo el modelo
	 * @param numTotal el num total
	 * @return el modelo bean
	 */
	private ModeloBean toModeloBean(Modelo modelo, int numTotal) {		
		
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

		auxModelo.setNumTotal(numTotal);
		
		return auxModelo;
	}
	

	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.ModeloManager#buscarModelosAll(es.map.ips.model.ModeloQuery)
	 */
	@Override
	public ArrayList<ModeloBean> buscarModelosAll(ModeloQuery modeloQuery) {
		modeloQuery.addOrder(ModeloQuery.IDMODELO, OrderType.ASC);
		SearchResult<Modelo> modelos = buscarModelos790(modeloQuery);
		int numTotal = 0;
		if(modelos.getNumResults() != null){
			numTotal = modelos.getNumResults();
		}
		ArrayList<ModeloBean> arrModeloBean= new ArrayList<ModeloBean>();
		for(int i=0;i<modelos.getResults().size();i++){
			ModeloBean aux;
			aux = toModeloBean(modelos.getResults().get(i),numTotal);
			if(aux != null){
				arrModeloBean.add(aux);
			}
		}	
		return arrModeloBean;		
	}
	
	/**
	 * Buscar modelos 790.
	 *
	 * @param modeloQuery el modelo query
	 * @return el search result
	 */
	private SearchResult<Modelo> buscarModelos790(ModeloQuery modeloQuery) {
		ApplicationException.assertNotNull(modeloQuery, "modeloQuery no puede ser null");
		return modeloDAO.search(modeloQuery);
	}
	
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.ModeloManager#guardarModelo(es.map.ipsg.bean.ModeloBean)
	 */
	@Override
	public Integer guardarModelo(ModeloBean modeloBean) {
		Modelo modelo = toModelo(modeloBean);
		Integer idModelo = modeloDAO.insert(modelo);		
		return idModelo;
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
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.ModeloManager#obtenerModelo790ById(java.lang.Integer)
	 */
	@Override
	public ModeloBean obtenerModelo790ById(Integer idModelo) {
		Modelo modelo = modeloDAO.get(idModelo);
		return toModeloBean(modelo,0);
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.ModeloManager#conseguirModelo790ById(java.lang.Integer)
	 */
	public Modelo conseguirModelo790ById(Integer idModelo) {
		Modelo modelo = modeloDAO.get(idModelo);
		return modelo;
	}
	
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.ModeloManager#modificarModelo790(es.map.ipsg.bean.ModeloBean)
	 */
	@Override
	public void modificarModelo790(ModeloBean modeloBean) {
		// TODO Auto-generated method stub
		Modelo modelo =  toModelo(modeloBean);
		modeloDAO.update(modelo);
	}
	
	/**
	 * Obtiene el modelo DAO.
	 *
	 * @return el modelo DAO
	 */
	public ModeloDAO getModeloDAO() {
		return modeloDAO;
	}

	/**
	 * Establece el modelo DAO.
	 *
	 * @param modeloDAO el nuevo modelo DAO
	 */
	public void setModeloDAO(ModeloDAO modeloDAO) {
		this.modeloDAO = modeloDAO;
	}


}
