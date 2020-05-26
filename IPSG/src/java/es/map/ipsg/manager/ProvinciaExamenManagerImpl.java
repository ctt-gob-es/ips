package es.map.ipsg.manager;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import es.map.ips.common.exception.ApplicationException;
import es.map.ips.common.model.OrderType;
import es.map.ips.common.model.SearchResult;
import es.map.ips.dao.ProvinciaExamenDAO;
import es.map.ips.model.ProvinciaExamen;
import es.map.ips.model.ProvinciaExamenQuery;
import es.map.ipsg.bean.ProvinciaExamenBean;
import es.map.ipsg.util.Constantes;

/**
 * El Class ProvinciaExamenManagerImpl.
 */
public class ProvinciaExamenManagerImpl implements ProvinciaExamenManager {

	/** el provincia examen DAO. */
	//Variables
	private ProvinciaExamenDAO provinciaExamenDAO;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(ProvinciaExamenManagerImpl.class);
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.ProvinciaExamenManager#buscarProvinciaExamenCombo(es.map.ips.model.ProvinciaExamenQuery)
	 */
	public ArrayList<ProvinciaExamenBean> buscarProvinciaExamenCombo(ProvinciaExamenQuery provinciaExamenQuery){
		
		provinciaExamenQuery.setEstado(Constantes.PROVINCIA_ESTADO_ACTIVO);
		
		int numTotal = 0;
		
		SearchResult<ProvinciaExamen> provinciaexamen = buscarProvinciaExamen(provinciaExamenQuery);
		if(provinciaexamen != null && provinciaexamen.getNumResults() != null){
			numTotal = provinciaexamen.getNumResults();
		}
		ArrayList<ProvinciaExamenBean> arrProvincia= new ArrayList<ProvinciaExamenBean>();
		for(int i=0;i<provinciaexamen.getResults().size();i++){
			ProvinciaExamenBean aux;
			aux = toProvinciaExamenBean(provinciaexamen.getResults().get(i),numTotal);
			if(aux != null){
				arrProvincia.add(aux);
			}
		}	
		return arrProvincia;		
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.ProvinciaExamenManager#buscarProvinciaExamenComboVisibilidad(es.map.ips.model.ProvinciaExamenQuery)
	 */
	public ArrayList<ProvinciaExamenBean> buscarProvinciaExamenComboVisibilidad(ProvinciaExamenQuery provinciaExamenQuery){
		
		provinciaExamenQuery.setEstado(Constantes.PROVINCIA_ESTADO_ACTIVO);
		
		int numTotal = 0;
		
		SearchResult<ProvinciaExamen> provinciaExamen = buscarProvinciaExamen(provinciaExamenQuery);
		if(provinciaExamen != null && provinciaExamen.getNumResults() != null){
			numTotal = provinciaExamen.getNumResults();
		}
		ArrayList<ProvinciaExamenBean> arrProvincia= new ArrayList<ProvinciaExamenBean>();
		for(int i=0;i<provinciaExamen.getResults().size();i++){
			//Sólo se mostrarán registros visibles	
			if(provinciaExamen.getResults().get(i).getVisible() == 'S' || provinciaExamen.getResults().get(i).getVisible() == 's')
			{	
				ProvinciaExamenBean aux;
				aux = toProvinciaExamenBean(provinciaExamen.getResults().get(i),numTotal);
				if(aux != null){
					arrProvincia.add(aux);
				}
			}	
		}	
		return arrProvincia;		
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.ProvinciaExamenManager#buscarProvinciaExamenUnique(es.map.ips.model.ProvinciaExamenQuery)
	 */
	public ProvinciaExamenBean buscarProvinciaExamenUnique(ProvinciaExamenQuery provinciaExamenQuery) {
		provinciaExamenQuery.setEstado(Constantes.PROVINCIA_ESTADO_ACTIVO);
		ProvinciaExamen provinciaAux = new ProvinciaExamen();
		try{
			provinciaAux = provinciaExamenDAO.searchUnique(provinciaExamenQuery);
		}catch(Exception e){
			logger.error("Error ProvinciaExamenManagerImpl- buscarProvinciaExamenUnique.",e);
			return null;
		}
		ProvinciaExamenBean provinciaExamenBean = new ProvinciaExamenBean();
		if(provinciaAux == null){
			return null;
		}
		try{
			provinciaExamenBean = toProvinciaExamenBean(provinciaAux);
		}catch(Exception e){
			logger.error("Error ProvinciaExamenManagerImpl- provincia examen.",e);
		}
		return provinciaExamenBean;
	}
	
	
	/**
	 * To provincia examen bean.
	 *
	 * @param u el u
	 * @param i el i
	 * @return el provincia examen bean
	 */
	private ProvinciaExamenBean toProvinciaExamenBean(ProvinciaExamen u, int i) {
		ProvinciaExamenBean aux = new ProvinciaExamenBean();
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
	 * @see es.map.ipsg.manager.ProvinciaExamenManager#guardarProvinciaExamen(es.map.ipsg.bean.ProvinciaExamenBean)
	 */
	public Integer guardarProvinciaExamen(ProvinciaExamenBean provinciaExamenBean){
		ProvinciaExamen provinciaExamen = toProvinciaExamen(provinciaExamenBean);
		Integer idProvinciaExamen = provinciaExamenDAO.insert(provinciaExamen);
		return idProvinciaExamen;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.ProvinciaExamenManager#modificarProvinciaExamen(es.map.ipsg.bean.ProvinciaExamenBean)
	 */
	public void modificarProvinciaExamen(ProvinciaExamenBean provinciaExamenBean){
		ProvinciaExamen provinciaExamen = toProvinciaExamen(provinciaExamenBean);
		provinciaExamenDAO.update(provinciaExamen);
	}
	
	/**
	 * To provincia examen.
	 *
	 * @param provinciaExamen el provincia examen
	 * @return el provincia examen
	 */
	private ProvinciaExamen toProvinciaExamen(ProvinciaExamenBean provinciaExamen) {
		ProvinciaExamen auxProvinciaExamen = null;
		
		if(provinciaExamen.getId()!=null && provinciaExamen.getId()>0)
			auxProvinciaExamen = provinciaExamenDAO.get(provinciaExamen.getId());
		else
			auxProvinciaExamen = new ProvinciaExamen();
		
		auxProvinciaExamen.setId(provinciaExamen.getId());
		auxProvinciaExamen.setDescripcion(provinciaExamen.getDescripcion());
		auxProvinciaExamen.setCodigo(provinciaExamen.getCodigo());
		auxProvinciaExamen.setEstado(provinciaExamen.getEstado());
		if(provinciaExamen.getVisibilidad() != null)
		{
			if(provinciaExamen.getVisibilidad() == true)
			{
				auxProvinciaExamen.setVisible('S');
			}	
			else if(provinciaExamen.getVisibilidad() == false)
			{
				auxProvinciaExamen.setVisible('N');
			}	
		}
		else 
		{
			auxProvinciaExamen.setVisible('N');
		}	
		
		
		return auxProvinciaExamen;
	}
	
	/**
	 * To provincia examen bean.
	 *
	 * @param provincia el provincia
	 * @return el provincia examen bean
	 */
	private ProvinciaExamenBean toProvinciaExamenBean(ProvinciaExamen provincia) {
		int id = provincia.getId();
		String descripcion = provincia.getDescripcion();
		String codigo = provincia.getCodigo();
		char estado = provincia.getEstado();
		
		ProvinciaExamenBean auxProvincia = new ProvinciaExamenBean();
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
	 * Buscar provincia examen.
	 *
	 * @param provinciaExamenQuery el provincia examen query
	 * @return el search result
	 */
	private SearchResult<ProvinciaExamen> buscarProvinciaExamen(ProvinciaExamenQuery provinciaExamenQuery) {
			ApplicationException.assertNotNull(provinciaExamenQuery, "provinciaQuery no puede ser null");
			
			provinciaExamenQuery.addOrder("descripcion", OrderType.ASC);
		return provinciaExamenDAO.search(provinciaExamenQuery);
	}

	/**
	 * Obtiene el provincia examen DAO.
	 *
	 * @return el provincia examen DAO
	 */
	public ProvinciaExamenDAO getProvinciaExamenDAO() {
		return provinciaExamenDAO;
	}

	/**
	 * Establece el provincia examen DAO.
	 *
	 * @param provinciaExamenDAO el nuevo provincia examen DAO
	 */
	public void setProvinciaExamenDAO(ProvinciaExamenDAO provinciaExamenDAO) {
		this.provinciaExamenDAO = provinciaExamenDAO;
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