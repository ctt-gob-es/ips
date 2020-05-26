package es.map.ipsg.manager;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import es.map.ips.common.exception.ApplicationException;
import es.map.ips.common.model.OrderType;
import es.map.ips.common.model.SearchResult;
import es.map.ips.dao.MinisterioDAO;
import es.map.ips.model.Ministerio;
import es.map.ips.model.MinisterioQuery;
import es.map.ipsg.bean.MinisterioBean;
import es.map.ipsg.util.Constantes;


/**
 * El Class MinisterioManagerImpl.
 */
public class MinisterioManagerImpl implements MinisterioManager {

	/** el ministerio DAO. */
	//Variables
	private MinisterioDAO ministerioDAO;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(MinisterioManagerImpl.class);
	
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.MinisterioManager#buscarMinisteriosAll(es.map.ips.model.MinisterioQuery)
	 */
	public List<MinisterioBean> buscarMinisteriosAll(
			MinisterioQuery ministerioQuery) {
		ministerioQuery.setEstado(Constantes.MINISTERIO_ESTADO_ACTIVO);
		List<MinisterioBean> list = new ArrayList<MinisterioBean>();
		SearchResult<Ministerio> ministerios = ministerioDAO.search(ministerioQuery);
		int numTotal = 0;

		if(ministerios != null){
			numTotal = ministerios.getNumResults();
		}

		for(Ministerio u:ministerios.getResults()){
			MinisterioBean ministerioBean = new MinisterioBean();
			try{
				ministerioBean = toMinisterioBean(u,numTotal);
			}catch(Exception e){
				logger.error("Error MinisterioManagerImpl - buscarMinisteriosAll.",e);
			}
			list.add(ministerioBean);
		}
		
		return list;
	}
	
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.MinisterioManager#buscarMinisterioCombo(es.map.ips.model.MinisterioQuery)
	 */
	public ArrayList<MinisterioBean> buscarMinisterioCombo(MinisterioQuery ministerioQuery){
		ministerioQuery.setEstado(Constantes.MINISTERIO_ESTADO_ACTIVO);
		ministerioQuery.addOrder(MinisterioQuery.DESCRIPCION, OrderType.ASC);
		SearchResult<Ministerio> ministerio = buscarMinisterios(ministerioQuery);
		
		ArrayList<MinisterioBean> arrMinisterio= new ArrayList<MinisterioBean>();
		for(int i=0;i<ministerio.getResults().size();i++){
			MinisterioBean aux;
			aux = toMinisterioComboBean(ministerio.getResults().get(i));
			if(aux != null){
				arrMinisterio.add(aux);
			}
		}	
		return arrMinisterio;		
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.MinisterioManager#buscarMinisterioComboTodos()
	 */
	public ArrayList<MinisterioBean> buscarMinisterioComboTodos(){
		MinisterioQuery ministerioQuery = new MinisterioQuery();
		ministerioQuery.addOrder(MinisterioQuery.DESCRIPCION, OrderType.ASC);
		ministerioQuery.setCalculateNumResults(true);
		ArrayList<MinisterioBean> list = new ArrayList<MinisterioBean>();
		SearchResult<Ministerio> ministerios = ministerioDAO.search(ministerioQuery);
		int numTotal = 0;
		for(int i=0;i<ministerios.getResults().size();i++) {
			MinisterioBean ministerioBean = new MinisterioBean();
			if(ministerios.getResults().get(i)!=null){
				try{
					ministerioBean = toMinisterioBean(ministerios.getResults().get(i),numTotal);
				}catch(Exception e){
					logger.warn(e);
					logger.error("Error MinisterioManagerImpl - buscarMinisterioComboTodos.",e);
				}
				list.add(ministerioBean);
			}
		}
		return list;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.MinisterioManager#buscarMinisterioAll(es.map.ips.model.MinisterioQuery)
	 */
	public ArrayList<MinisterioBean> buscarMinisterioAll(MinisterioQuery ministerioQuery){
		ministerioQuery.setEstado(Constantes.MINISTERIO_ESTADO_ACTIVO);
		SearchResult<Ministerio> ministerio = buscarMinisterios(ministerioQuery);
		int numTotal = ministerio.getNumResults();
		ArrayList<MinisterioBean> arrMinisterio= new ArrayList<MinisterioBean>();
		for(int i=0;i<ministerio.getResults().size();i++){
			MinisterioBean aux;
			aux = toMinisterioBean(ministerio.getResults().get(i),numTotal);
			if(aux != null){
				arrMinisterio.add(aux);
			}
		}	
		return arrMinisterio;		
	}
	
	/**
	 * To ministerio bean.
	 *
	 * @param u el u
	 * @param numTotal el num total
	 * @return el ministerio bean
	 */
	private MinisterioBean toMinisterioBean(Ministerio u, int numTotal) {
		MinisterioBean aux = new MinisterioBean();
		aux.setId(u.getId());
		aux.setDescripcion(u.getDescripcion());
		aux.setCodigo(u.getCodigo());
		aux.setEstado(u.getEstado());
		aux.setSiglas(u.getSiglas());
		aux.setNumTotal(numTotal);
		aux.setUrl(u.getUrl());
		aux.setIdMinisterioPrevio(u.getIdMinisterioPrevio());
		aux.setFechaSustitucion(u.getFechaSustitucion());
		if(u.getVisible() != null)
		{	
			if(u.getVisible().equals('S'))
			{
				aux.setVisibilidad(true);
			}	
			else if(u.getVisible().equals('N'))
			{
				aux.setVisibilidad(false);
			}	
		}
		else
		{
			aux.setVisibilidad(false);
		}	
			
		return aux;
	}
	
	/**
	 * To ministerio combo bean.
	 *
	 * @param ministerio el ministerio
	 * @return el ministerio bean
	 */
	public MinisterioBean toMinisterioComboBean(Ministerio ministerio) {
		int id = ministerio.getId();
		String siglas = ministerio.getSiglas();
		String descripcion = ministerio.getDescripcion();
		String codigo = ministerio.getCodigo();		
		
		MinisterioBean auxMinisterio = new MinisterioBean();
		auxMinisterio.setId(id);
		auxMinisterio.setSiglas(siglas);
		auxMinisterio.setDescripcion(descripcion);
		auxMinisterio.setCodigo(codigo);
		auxMinisterio.setFechaSustitucion(ministerio.getFechaSustitucion());
		
		return auxMinisterio;
	}

	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.MinisterioManager#buscarMinisterio(es.map.ips.model.MinisterioQuery)
	 */
	public MinisterioBean buscarMinisterio(MinisterioQuery ministerioQuery) {
		ministerioQuery.setEstado(Constantes.MINISTERIO_ESTADO_ACTIVO);
		Ministerio ministerio = new Ministerio();
		MinisterioBean ministerioBean = new MinisterioBean();
		try{
			ministerio = ministerioDAO.searchUnique(ministerioQuery);
			ministerioBean = new MinisterioBean();	
			ministerioBean = toMinisterioBean(ministerio,0);
		}catch(Exception e){
			logger.error("Error MinisterioManagerImpl - buscarMinisterio.",e);
		}
		return ministerioBean;
	}


	
	
	/**
	 * Buscar ministerios.
	 *
	 * @param ministerioQuery el ministerio query
	 * @return el search result
	 */
	private SearchResult<Ministerio> buscarMinisterios(MinisterioQuery ministerioQuery) {
		ApplicationException.assertNotNull(ministerioQuery, "cuerpoEscalaQuery no puede ser null");
	return ministerioDAO.search(ministerioQuery);
}

	
	/**
	 * Obtiene el ministerio DAO.
	 *
	 * @return el ministerio DAO
	 */
	public MinisterioDAO getMinisterioDAO() {
		return ministerioDAO;
	}

	/**
	 * Establece el ministerio DAO.
	 *
	 * @param ministerioDAO el nuevo ministerio DAO
	 */
	public void setMinisterioDAO(MinisterioDAO ministerioDAO) {
		this.ministerioDAO = ministerioDAO;
	}

	/**
	 * To ministerio.
	 *
	 * @param ministerioBean el ministerio bean
	 * @return el ministerio
	 */
	private Ministerio toMinisterio(MinisterioBean ministerioBean) {
		Ministerio ministerioAux = new Ministerio();
		ministerioAux.setId(ministerioBean.getId());
		ministerioAux.setSiglas(ministerioBean.getSiglas());
		ministerioAux.setCodigo(ministerioBean.getCodigo());
		ministerioAux.setDescripcion(ministerioBean.getDescripcion());
		ministerioAux.setUrl(ministerioBean.getUrl());
		ministerioAux.setEstado(ministerioBean.getEstado());
		if(ministerioBean.getIdMinisterioPrevio()!=null){			
			ministerioAux.setIdMinisterioPrevio(ministerioBean.getIdMinisterioPrevio());
			ministerioAux.setFechaSustitucion(ministerioBean.getFechaSustitucion());
		}
		if(ministerioBean.getVisibilidad() != null)
		{
			if(ministerioBean.getVisibilidad() == true)
			{	
				ministerioAux.setVisible('S');
			}
			else if(ministerioBean.getVisibilidad() == false)
			{
				ministerioAux.setVisible('N');
			}	
		}
		else
		{
			ministerioAux.setVisible('N');
		}
		return ministerioAux;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.MinisterioManager#guardarMinisterio(es.map.ipsg.bean.MinisterioBean)
	 */
	public int guardarMinisterio(MinisterioBean ministerioBean) {
		Ministerio ministerio;
		ministerio = toMinisterio(ministerioBean);
		int idMinisterio = ministerioDAO.insert(ministerio);
		
		return idMinisterio;
	}

/* (non-Javadoc)
 * @see es.map.ipsg.manager.MinisterioManager#actualizarMinisterio(es.map.ipsg.bean.MinisterioBean)
 */
public void actualizarMinisterio(MinisterioBean ministerioBean) {
		Ministerio ministerio;
		ministerio = toMinisterio(ministerioBean);
		ministerioDAO.update(ministerio);
	}

	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.MinisterioManager#obtenerMinisterio(java.lang.Integer)
	 */
	public MinisterioBean obtenerMinisterio(Integer idMinisterio){
		Ministerio ministerio = ministerioDAO.get(idMinisterio);
		return toMinisterioBean(ministerio);
	}
	
	/**
	 * To ministerio bean.
	 *
	 * @param ministerio el ministerio
	 * @return el ministerio bean
	 */
	public MinisterioBean toMinisterioBean(Ministerio ministerio){
		MinisterioBean ministerioBean = new MinisterioBean();
		
		ministerioBean.setId(ministerio.getId());
		ministerioBean.setCodigo(ministerio.getCodigo());
		ministerioBean.setSiglas(ministerio.getSiglas());
		ministerioBean.setDescripcion(ministerio.getDescripcion());
		ministerioBean.setEstado(ministerio.getEstado());
		if(ministerio.getVisible() != null && !ministerio.getVisible().equals(""))
		{	
			if(ministerio.getVisible().equals('S'))
			{
				ministerioBean.setVisibilidad(true);
			}	
			else if(ministerio.getVisible().equals('N'))
			{
				ministerioBean.setVisibilidad(false);
			}
		}
		else
		{
			ministerioBean.setVisibilidad(false);
		}
			
		
		
		return ministerioBean;
	}

	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.MinisterioManager#modificarMinisterio(es.map.ipsg.bean.MinisterioBean)
	 */
	public void modificarMinisterio(MinisterioBean ministerioBean){
		Ministerio ministerio = toMinisterio(ministerioBean);
		ministerioDAO.update(ministerio);
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
