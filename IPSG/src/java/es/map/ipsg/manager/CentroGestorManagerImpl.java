package es.map.ipsg.manager;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import es.map.ips.common.exception.ApplicationException;
import es.map.ips.common.model.OrderType;
import es.map.ips.common.model.SearchResult;
import es.map.ips.dao.CentroGestorDAO;
import es.map.ips.dao.ModeloDAO;
import es.map.ips.model.CentroGestor;
import es.map.ips.model.CentroGestorQuery;
import es.map.ips.model.Ministerio;
import es.map.ips.model.Modelo;
import es.map.ipsg.bean.CentroGestorBean;
import es.map.ipsg.util.Constantes;



/**
 * El Class CentroGestorManagerImpl.
 */
public class CentroGestorManagerImpl implements CentroGestorManager {

	/** el centro gestor DAO. */
	//Variables
	private CentroGestorDAO centroGestorDAO;
	
	/** el modelo DAO. */
	private ModeloDAO modeloDAO;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(CentroGestorManagerImpl.class);
	
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.CentroGestorManager#buscarCentroGestorCombo(es.map.ips.model.CentroGestorQuery)
	 */
	public ArrayList<CentroGestorBean> buscarCentroGestorCombo(CentroGestorQuery centroGestorQuery){
		centroGestorQuery.addOrder(CentroGestorQuery.DESCRIPCION, OrderType.ASC);
		centroGestorQuery.setEstado(Constantes.CENTROGESTOR_ESTADO_ACTIVO);
		SearchResult<CentroGestor> centroGestor = buscarCentroGestor(centroGestorQuery);
		ArrayList<CentroGestorBean> arrCentroGestor= new ArrayList<CentroGestorBean>();
		for(int i=0;i<centroGestor.getResults().size();i++){
			CentroGestorBean aux;
			aux = toCentroGestorBean(centroGestor.getResults().get(i),0);
			if(aux != null){
				arrCentroGestor.add(aux);
			}
		}	
		return arrCentroGestor;		
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.CentroGestorManager#buscarCentroGestorComboVisibilidad(es.map.ips.model.CentroGestorQuery)
	 */
	public ArrayList<CentroGestorBean> buscarCentroGestorComboVisibilidad(CentroGestorQuery centroGestorQuery){
		centroGestorQuery.addOrder(CentroGestorQuery.DESCRIPCION, OrderType.ASC);
		centroGestorQuery.setEstado(Constantes.CENTROGESTOR_ESTADO_ACTIVO);
		SearchResult<CentroGestor> centroGestor = buscarCentroGestor(centroGestorQuery);
		ArrayList<CentroGestorBean> arrCentroGestor= new ArrayList<CentroGestorBean>();
		for(int i=0;i<centroGestor.getResults().size();i++){
			//Sólo se mostrarán registros visibles	
			if(centroGestor.getResults().get(i).getVisible() == 'S' || centroGestor.getResults().get(i).getVisible() == 's')
			{	
				CentroGestorBean aux;
				aux = toCentroGestorBean(centroGestor.getResults().get(i),0);
				if(aux != null){
					arrCentroGestor.add(aux);
				}
			}	
		}	
		return arrCentroGestor;		
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.CentroGestorManager#buscarCentroGestorComboTodos()
	 */
	public ArrayList<CentroGestorBean> buscarCentroGestorComboTodos(){		
		CentroGestorQuery centroGestorQuery = new CentroGestorQuery();
		centroGestorQuery.addOrder(CentroGestorQuery.DESCRIPCION, OrderType.ASC);
		SearchResult<CentroGestor> centroGestor = centroGestorDAO.search(centroGestorQuery);
		ArrayList<CentroGestorBean> arrCentroGestor= new ArrayList<CentroGestorBean>();
		for(int i=0;i<centroGestor.getResults().size();i++){
			CentroGestorBean aux;
			aux = toCentroGestorBean(centroGestor.getResults().get(i),0);
			if(aux != null){
				arrCentroGestor.add(aux);
			}
		}	
		return arrCentroGestor;	
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.CentroGestorManager#buscarCentroGestorAll(es.map.ips.model.CentroGestorQuery)
	 */
	public ArrayList<CentroGestorBean> buscarCentroGestorAll(CentroGestorQuery centroGestorQuery){
		centroGestorQuery.setEstado(Constantes.CENTROGESTOR_ESTADO_ACTIVO);
		SearchResult<CentroGestor> centroGestor = buscarCentroGestor(centroGestorQuery);
		int numTotal = 0;
		if(centroGestor.getNumResults() != null){
			numTotal = centroGestor.getNumResults();
		}

		ArrayList<CentroGestorBean> arrCentroGestor= new ArrayList<CentroGestorBean>();
		for(int i=0;i<centroGestor.getResults().size();i++){
			CentroGestorBean aux;
			aux = toCentroGestorBean(centroGestor.getResults().get(i),numTotal);
			if(aux != null){
				arrCentroGestor.add(aux);
			}
		}	
		return arrCentroGestor;		
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.CentroGestorManager#buscarCentrosGestoresAll(es.map.ips.model.CentroGestorQuery)
	 */
	public ArrayList<CentroGestor> buscarCentrosGestoresAll(CentroGestorQuery centroGestorQuery){
		centroGestorQuery.setEstado(Constantes.CENTROGESTOR_ESTADO_ACTIVO);
		SearchResult<CentroGestor> centroGestor = buscarCentroGestor(centroGestorQuery);
		int numTotal = 0;
		if(centroGestor.getNumResults() != null){
			numTotal = centroGestor.getNumResults();
		}

		ArrayList<CentroGestor> arrCentroGestorAux= new ArrayList<CentroGestor>();
		for(int i=0;i<centroGestor.getResults().size();i++){
			CentroGestor aux;
			aux = toCentroGestor(centroGestor.getResults().get(i),numTotal);
			if(aux != null){
				arrCentroGestorAux.add(aux);
			}
		}	
		return arrCentroGestorAux;		
	}
	
	
	
	/**
	 * To centro gestor.
	 *
	 * @param centroGestor el centro gestor
	 * @param numTotal el num total
	 * @return el centro gestor
	 */
	private CentroGestor toCentroGestor(CentroGestor centroGestor, int numTotal) {		
		
		CentroGestor auxCentroGestor = new CentroGestor();
		auxCentroGestor.setIdCentroGestor(centroGestor.getIdCentroGestor());
		auxCentroGestor.setSiglas(centroGestor.getSiglas());
		auxCentroGestor.setDescripcion(centroGestor.getDescripcion());
		auxCentroGestor.setCodigo(centroGestor.getCodigo());		
		auxCentroGestor.setEjercicio(centroGestor.getEjercicio());
		auxCentroGestor.setMinisterio(centroGestor.getMinisterio());
		
		if(centroGestor.getMinisterio() != null){
			auxCentroGestor.setMinisterio(centroGestor.getMinisterio());
		}
		if(centroGestor.getPlantilla() != null)
		{
			auxCentroGestor.setPlantilla(centroGestor.getPlantilla());
		}
		auxCentroGestor.setEstado(centroGestor.getEstado());
		auxCentroGestor.setIdCentroGestorPrevio(centroGestor.getIdCentroGestorPrevio());
		auxCentroGestor.setFechaSustitucion(centroGestor.getFechaSustitucion());
		if(centroGestor.getVisible() != null)
		{	
			if(centroGestor.getVisible().equals('S'))
			{
				auxCentroGestor.setVisible('S');
			}
			else if(centroGestor.getVisible().equals('N'))
			{
				auxCentroGestor.setVisible('N');
			}
		}
		else
		{
			auxCentroGestor.setVisible('N');
		}	
		return auxCentroGestor;
	}
	
	
	
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.CentroGestorManager#buscarCentroGestorUnico(es.map.ips.model.CentroGestorQuery)
	 */
	public CentroGestorBean buscarCentroGestorUnico(CentroGestorQuery centroGestorQuery){
		centroGestorQuery.setEstado(Constantes.CENTROGESTOR_ESTADO_ACTIVO);
		CentroGestor centroGestor = centroGestorDAO.searchUnique(centroGestorQuery);		
		CentroGestorBean centroGestorBean = new CentroGestorBean();
		try{
		centroGestorBean = toCentroGestorBean(centroGestor,0);
		}catch(Exception e){
			logger.warn(e);
			logger.error(" Error CentroGestorManagerImpl - buscarCentroGestorUnico- centroGestor",e);
		}
		return centroGestorBean;
	}
	
	/**
	 * To centro gestor bean.
	 *
	 * @param centroGestor el centro gestor
	 * @param numTotal el num total
	 * @return el centro gestor bean
	 */
	private CentroGestorBean toCentroGestorBean(CentroGestor centroGestor, int numTotal) {		
				
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
		auxCentroGestor.setNumTotal(numTotal);
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
		
		auxCentroGestor.setFechaInicioInhabil(centroGestor.getFechaInicioInhabil());
		auxCentroGestor.setFechaFinInhabil(centroGestor.getFechaFinInhabil());
		
		return auxCentroGestor;
	}
	
	
		
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.CentroGestorManager#guardarCentroGestor(es.map.ipsg.bean.CentroGestorBean)
	 */
	public Integer guardarCentroGestor(CentroGestorBean centroGestorBean){
		CentroGestor centroGestor = toCentroGestor(centroGestorBean);
		Integer idCentroGestor = centroGestorDAO.insert(centroGestor);		
		return idCentroGestor;
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
		
		if(centroGestorBean.getModelo()!=null){
			centroGestor.setModelo(centroGestorBean.getModelo());
		}else{
			//para crear un nuevo modelo
			if(centroGestorBean.getidModelo()!=null){
				Modelo modelo = new Modelo();
				modelo.setIdModelo(centroGestorBean.getidModelo());
				centroGestor.setModelo(modelo);
			}	
		}	
		
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
		
		centroGestor.setFechaInicioInhabil(centroGestorBean.getFechaInicioInhabil());
		centroGestor.setFechaFinInhabil(centroGestorBean.getFechaFinInhabil());	
				
		return centroGestor;
	}
	
	/**
	 * Buscar centro gestor.
	 *
	 * @param centroGestorQuery el centro gestor query
	 * @return el search result
	 */
	private SearchResult<CentroGestor> buscarCentroGestor(CentroGestorQuery centroGestorQuery) {
			ApplicationException.assertNotNull(centroGestorQuery, "centroGestorQuery no puede ser null");
		
		return centroGestorDAO.search(centroGestorQuery);
	}

	/**
	 * Obtiene el centro gestor DAO.
	 *
	 * @return el centro gestor DAO
	 */
	public CentroGestorDAO getCentroGestorDAO() {
		return centroGestorDAO;
	}

	/**
	 * Establece el centro gestor DAO.
	 *
	 * @param centroGestorDAO el nuevo centro gestor DAO
	 */
	public void setCentroGestorDAO(CentroGestorDAO centroGestorDAO) {
		this.centroGestorDAO = centroGestorDAO;
	}

	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.CentroGestorManager#obtenerCentroGestor(java.lang.Integer)
	 */
	public CentroGestorBean obtenerCentroGestor(Integer idCentroGestor){
		CentroGestor centroGestor = centroGestorDAO.get(idCentroGestor);
		return toCentroGestorBean(centroGestor,0);
	}
	
	/**
	 * Modifica el Centro Gestor.
	 *
	 * @param centroGestorBean CentroGestorBean
	 */
	public void modificarCentroGestor (CentroGestorBean  centroGestorBean)
	{
		CentroGestor centroGestor =  toCentroGestor(centroGestorBean);
		centroGestorDAO.update(centroGestor);
	}
	
	/**
	 * Modifica el Centro Gestor y lo desactiva*.
	 *
	 * @param centroGestorBean el centro gestor bean
	 */
	public void modificarEstadoCentroGestor (CentroGestorBean  centroGestorBean){
		CentroGestor centroGestor =  centroGestorDAO.get(centroGestorBean.getId());
		centroGestor.setEstado(centroGestorBean.getEstado());
		centroGestorDAO.update(centroGestor);
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
	
