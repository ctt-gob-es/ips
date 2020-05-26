package es.map.ipsg.manager;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import es.map.ips.common.exception.ApplicationException;
import es.map.ips.common.model.OrderType;
import es.map.ips.common.model.SearchResult;
import es.map.ips.dao.ProvinciaDAO;
import es.map.ips.model.Provincia;
import es.map.ips.model.ProvinciaQuery;
import es.map.ipsg.bean.ProvinciaBean;
import es.map.ipsg.util.Constantes;


/**
 * El Class ProvinciaManagerImpl.
 */
public class ProvinciaManagerImpl implements ProvinciaManager {

	/** el provincia DAO. */
	//Variables
	private ProvinciaDAO provinciaDAO;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(ProvinciaManagerImpl.class);
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.ProvinciaManager#buscarProvinciaCombo(es.map.ips.model.ProvinciaQuery)
	 */
	public ArrayList<ProvinciaBean> buscarProvinciaCombo(ProvinciaQuery provinciaQuery){
		
		provinciaQuery.setEstado(Constantes.PROVINCIA_ESTADO_ACTIVO);
		
		int numTotal = 0;
		
		SearchResult<Provincia> provincia = buscarProvincia(provinciaQuery);
		if(provincia != null && provincia.getNumResults() != null){
			numTotal = provincia.getNumResults();
		}
		ArrayList<ProvinciaBean> arrProvincia= new ArrayList<ProvinciaBean>();
		for(int i=0;i<provincia.getResults().size();i++){
			ProvinciaBean aux;
			aux = toProvinciaBean(provincia.getResults().get(i),numTotal);
			if(aux != null){
				arrProvincia.add(aux);
			}
		}	
		return arrProvincia;		
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.ProvinciaManager#buscarProvinciaComboVisibilidad(es.map.ips.model.ProvinciaQuery)
	 */
	public ArrayList<ProvinciaBean> buscarProvinciaComboVisibilidad(ProvinciaQuery provinciaQuery){
		
		provinciaQuery.setEstado(Constantes.PROVINCIA_ESTADO_ACTIVO);
		
		int numTotal = 0;
		
		SearchResult<Provincia> provincia = buscarProvincia(provinciaQuery);
		if(provincia != null && provincia.getNumResults() != null){
			numTotal = provincia.getNumResults();
		}
		ArrayList<ProvinciaBean> arrProvincia= new ArrayList<ProvinciaBean>();
		for(int i=0;i<provincia.getResults().size();i++){
			//Solo se mostraran registros visibles	
			if(provincia.getResults().get(i).getVisible() == 'S' || provincia.getResults().get(i).getVisible() == 's')
			{	
				ProvinciaBean aux;
				aux = toProvinciaBean(provincia.getResults().get(i),numTotal);
				if(aux != null){
					arrProvincia.add(aux);
				}
			}	
		}	
		return arrProvincia;		
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.ProvinciaManager#buscarProvinciaUnique(es.map.ips.model.ProvinciaQuery)
	 */
	public ProvinciaBean buscarProvinciaUnique(ProvinciaQuery provinciaQuery) {
		provinciaQuery.setEstado(Constantes.PROVINCIA_ESTADO_ACTIVO);
		Provincia provinciaAux = new Provincia();
		try{
			provinciaAux = provinciaDAO.searchUnique(provinciaQuery);
		}catch(Exception e){
			logger.error("Error ProvinciaManagerImpl- buscarProvinciaUnique.",e);
			return null;
		}
		ProvinciaBean provinciaBean = new ProvinciaBean();
		if(provinciaAux == null){
			return null;
		}
		try{
			provinciaBean = toProvinciaBean(provinciaAux);
		}catch(Exception e){
			logger.error("Error ProvinciaManagerImpl- provincia.",e);
		}
		return provinciaBean;
	}
	
	
	/**
	 * To provincia bean.
	 *
	 * @param u el u
	 * @param i el i
	 * @return el provincia bean
	 */
	private ProvinciaBean toProvinciaBean(Provincia u, int i) {
		ProvinciaBean aux = new ProvinciaBean();
		aux.setId(u.getId());
		aux.setDescripcion(u.getDescripcion());
		aux.setCodigo(u.getCodigo());
		aux.setEstado(u.getEstado());
		aux.setNumTotal(i);
		if(u.getVisible() != null && !u.getVisible().equals(""))
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

	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.ProvinciaManager#guardarProvincia(es.map.ipsg.bean.ProvinciaBean)
	 */
	public Integer guardarProvincia(ProvinciaBean provinciaBean){
		Provincia provincia = toProvincia(provinciaBean);
		Integer idProvincia = provinciaDAO.insert(provincia);
		return idProvincia;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.ProvinciaManager#modificarProvincia(es.map.ipsg.bean.ProvinciaBean)
	 */
	public void modificarProvincia(ProvinciaBean provinciaBean){
		Provincia provincia = toProvincia(provinciaBean);
		provinciaDAO.update(provincia);
	}
	
	/**
	 * To provincia.
	 *
	 * @param provincia el provincia
	 * @return el provincia
	 */
	private Provincia toProvincia(ProvinciaBean provincia) {
		Provincia auxProvincia = null;
		
		if(provincia.getId()!=null && provincia.getId()>0)
			auxProvincia = provinciaDAO.get(provincia.getId());
		else
			auxProvincia = new Provincia();
		
		auxProvincia.setId(provincia.getId());
		auxProvincia.setDescripcion(provincia.getDescripcion());
		auxProvincia.setCodigo(provincia.getCodigo());
		auxProvincia.setEstado(provincia.getEstado());
		if(provincia.getVisibilidad() != null)
		{
			if(provincia.getVisibilidad() == true)
			{
				auxProvincia.setVisible('S');
			}	
			else if(provincia.getVisibilidad() == false)
			{
				auxProvincia.setVisible('N');
			}	
		}
		else 
		{
			auxProvincia.setVisible('N');
		}	
		
		
		return auxProvincia;
	}
	
	/**
	 * To provincia bean.
	 *
	 * @param provincia el provincia
	 * @return el provincia bean
	 */
	private ProvinciaBean toProvinciaBean(Provincia provincia) {
		int id = provincia.getId();
		String descripcion = provincia.getDescripcion();
		String codigo = provincia.getCodigo();
		char estado = provincia.getEstado();
		
		ProvinciaBean auxProvincia = new ProvinciaBean();
		auxProvincia.setId(id);
		auxProvincia.setDescripcion(descripcion);
		auxProvincia.setCodigo(codigo);
		auxProvincia.setEstado(estado);
		if(provincia.getVisible() != null && !provincia.getVisible().equals(""))
		{
			if(provincia.getVisible().equals('S'))
			{	
				auxProvincia.setVisibilidad(true);
			}
			else if(provincia.getVisible().equals('N'))
			{	
				auxProvincia.setVisibilidad(false);
			}
		}
		else
		{	
			auxProvincia.setVisibilidad(false);
		}
		return auxProvincia;
	}

	/**
	 * Buscar provincia.
	 *
	 * @param provinciaQuery el provincia query
	 * @return el search result
	 */
	private SearchResult<Provincia> buscarProvincia(ProvinciaQuery provinciaQuery) {
			ApplicationException.assertNotNull(provinciaQuery, "provinciaQuery no puede ser null");
			
			provinciaQuery.addOrder("descripcion", OrderType.ASC);
		return provinciaDAO.search(provinciaQuery);
	}

	/**
	 * Obtiene el provincia DAO.
	 *
	 * @return el provincia DAO
	 */
	public ProvinciaDAO getProvinciaDAO() {
		return provinciaDAO;
	}

	/**
	 * Establece el provincia DAO.
	 *
	 * @param provinciaDAO el nuevo provincia DAO
	 */
	public void setProvinciaDAO(ProvinciaDAO provinciaDAO) {
		this.provinciaDAO = provinciaDAO;
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