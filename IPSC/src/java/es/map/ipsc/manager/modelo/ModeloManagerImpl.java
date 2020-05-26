package es.map.ipsc.manager.modelo;

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
import es.map.ipsc.bean.ModeloBean;

/**
 * El Class ModeloManagerImpl.
 */
public class ModeloManagerImpl implements ModeloManager{
	
	/** el modelo DAO. */
	//Variables
	private ModeloDAO modeloDAO;
	
	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.modelo.ModeloManager#buscarModeloCombo(es.map.ips.model.ModeloQuery)
	 */
	@Override
	public List<ModeloBean> buscarModeloCombo(ModeloQuery modeloQuery) {
		modeloQuery.addOrder(MinisterioQuery.DESCRIPCION, OrderType.ASC);
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
	 * @see es.map.ipsc.manager.modelo.ModeloManager#buscarModeloComboTodos()
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
	 * @see es.map.ipsc.manager.modelo.ModeloManager#buscarModelosAll(es.map.ips.model.ModeloQuery)
	 */
	@Override
	public List<ModeloBean> buscarModelosAll(ModeloQuery modeloQuery) {
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
	 * @see es.map.ipsc.manager.modelo.ModeloManager#buscarModeloByNumModelo(es.map.ips.model.ModeloQuery)
	 */
	@Override
	public Modelo buscarModeloByNumModelo(ModeloQuery modeloQuery) {
		return modeloDAO.searchUnique(modeloQuery); 
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
