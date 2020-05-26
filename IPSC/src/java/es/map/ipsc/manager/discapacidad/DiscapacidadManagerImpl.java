package es.map.ipsc.manager.discapacidad;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import es.map.ips.common.exception.ApplicationException;
import es.map.ips.common.model.OrderType;
import es.map.ips.common.model.SearchResult;
import es.map.ips.dao.DiscapacidadDAO;
import es.map.ips.model.CentroGestor;
import es.map.ips.model.Discapacidad;
import es.map.ips.model.DiscapacidadQuery;
import es.map.ipsc.bean.DiscapacidadBean;



/**
 * El Class DiscapacidadManagerImpl.
 */
public class DiscapacidadManagerImpl implements DiscapacidadManager {

	/** el discapacidad DAO. */
	//Variables
	private DiscapacidadDAO discapacidadDAO;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(DiscapacidadManagerImpl.class);
	
	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.discapacidad.DiscapacidadManager#buscarDiscapacidadCombo(es.map.ips.model.DiscapacidadQuery)
	 */
	public ArrayList<DiscapacidadBean> buscarDiscapacidadCombo(DiscapacidadQuery discapacidadQuery){	
		discapacidadQuery.addOrder(DiscapacidadQuery.DESCRIPCION, OrderType.ASC);
		SearchResult<Discapacidad> discapacidad = buscarDiscapacidad(discapacidadQuery);
		ArrayList<DiscapacidadBean> arrDiscapacidads= new ArrayList<DiscapacidadBean>();
		for(int i=0;i<discapacidad.getResults().size();i++){
			DiscapacidadBean aux;
			aux = toDiscapacidadComboBean(discapacidad.getResults().get(i));
			if(aux != null){
				arrDiscapacidads.add(aux);
			}
		}	
		return arrDiscapacidads;		
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.discapacidad.DiscapacidadManager#buscarDiscapacidadComboVisibilidad(es.map.ips.model.DiscapacidadQuery)
	 */
	public ArrayList<DiscapacidadBean> buscarDiscapacidadComboVisibilidad(DiscapacidadQuery discapacidadQuery){		
		SearchResult<Discapacidad> discapacidad = buscarDiscapacidad(discapacidadQuery);
		ArrayList<DiscapacidadBean> arrDiscapacidads= new ArrayList<DiscapacidadBean>();
		for(int i=0;i<discapacidad.getResults().size();i++){
			//Sólo se mostrarán registros visibles	
			if(discapacidad.getResults().get(i).getVisible() == 'S' || discapacidad.getResults().get(i).getVisible() == 's')
			{	
				DiscapacidadBean aux;
				aux = toDiscapacidadComboBean(discapacidad.getResults().get(i));
				if(aux != null){
					arrDiscapacidads.add(aux);
				}
			}	
		}	
		return arrDiscapacidads;		
	}
	
	
	/**
	 * Buscar discapacidad all.
	 *
	 * @param discapacidadQuery el discapacidad query
	 * @return el array list
	 */
	public ArrayList<DiscapacidadBean> buscarDiscapacidadAll(DiscapacidadQuery discapacidadQuery){		
		//DiscapacidadQuery.setEstado(Constantes.CUERPO_ESCALA_ESTADO_ACTIVO);
		SearchResult<Discapacidad> discapacidad = buscarDiscapacidad(discapacidadQuery);
		int numTotal = 0;
		
		if(discapacidad.getNumResults() != null){
			numTotal = discapacidad.getNumResults();
		}
		
		ArrayList<DiscapacidadBean> arrDiscapacidads= new ArrayList<DiscapacidadBean>();
		
		for(int i=0;i<discapacidad.getResults().size();i++){
			DiscapacidadBean aux;
			aux = toDiscapacidadBean(discapacidad.getResults().get(i),numTotal);
			if(aux != null){
				arrDiscapacidads.add(aux);
			}
		}	
		return arrDiscapacidads;		
	}
	
	
	/**
	 * Buscar discapacidad.
	 *
	 * @param discapacidadQuery el discapacidad query
	 * @return el search result
	 */
	private SearchResult<Discapacidad> buscarDiscapacidad(DiscapacidadQuery discapacidadQuery) {
		ApplicationException.assertNotNull(discapacidadQuery, "DiscapacidadQuery no puede ser null");
		return discapacidadDAO.search(discapacidadQuery);
	}

	/**
	 * To discapacidad bean.
	 *
	 * @param discapacidad el discapacidad
	 * @param numTotal el num total
	 * @return el discapacidad bean
	 */
	public DiscapacidadBean toDiscapacidadBean(Discapacidad discapacidad, int numTotal) {
		logger.info("en toDiscapacidadBean()");
		int id = discapacidad.getIdDiscapacidad();
		String descripcion = discapacidad.getDescripcion();
		
		CentroGestor centroGestor=discapacidad.getCentroGestor();
	
		DiscapacidadBean DiscapacidadBean= new DiscapacidadBean();
		DiscapacidadBean.setDescripcion(descripcion);
		DiscapacidadBean.setId(id);
		DiscapacidadBean.setDesCentroGestor(centroGestor.getDescripcion());
		if(!StringUtils.isEmpty(discapacidad.getVisible())) {
			if(discapacidad.getVisible().equals('S'))
			{
				DiscapacidadBean.setVisibilidad(true);
			}	
			else if(discapacidad.getVisible().equals('N'))
			{
				DiscapacidadBean.setVisibilidad(false);
			}		
		}
		else
		{
			DiscapacidadBean.setVisibilidad(false);
		}
		DiscapacidadBean.setNumTotal(numTotal);

	    return DiscapacidadBean;
	}
	
	/**
	 * To discapacidad combo bean.
	 *
	 * @param discapacidad el discapacidad
	 * @return el discapacidad bean
	 */
	public DiscapacidadBean toDiscapacidadComboBean(Discapacidad discapacidad) {
		int id = discapacidad.getIdDiscapacidad();
		String descripcion = discapacidad.getDescripcion();	
		
		DiscapacidadBean discapacidadBean= new DiscapacidadBean();
		discapacidadBean.setDescripcion(descripcion);
		discapacidadBean.setId(id);

	    return discapacidadBean;
	}

	/**
	 * Obtiene el discapacidad DAO.
	 *
	 * @return el discapacidad DAO
	 */
	public DiscapacidadDAO getDiscapacidadDAO() {
		return discapacidadDAO;
	}

	/**
	 * Establece el discapacidad DAO.
	 *
	 * @param discapacidadDAO el nuevo discapacidad DAO
	 */
	public void setDiscapacidadDAO(DiscapacidadDAO discapacidadDAO) {
		this.discapacidadDAO = discapacidadDAO;
	}

	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.discapacidad.DiscapacidadManager#guardarDiscapacidad(es.map.ipsc.bean.DiscapacidadBean)
	 */
	public Integer guardarDiscapacidad(DiscapacidadBean discapacidadBean){
		Discapacidad discapacidad= toDiscapacidad(discapacidadBean);
		Integer idDiscapacidad = discapacidadDAO.insert(discapacidad);
		return idDiscapacidad;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.discapacidad.DiscapacidadManager#modificarDiscapacidad(es.map.ipsc.bean.DiscapacidadBean)
	 */
	public Boolean modificarDiscapacidad(DiscapacidadBean discapacidadBean){
		Boolean update = false;
		Discapacidad discapacidad = toDiscapacidad(discapacidadBean);
		
		try {
			discapacidadDAO.update(discapacidad);
			update = true;
		} catch (Exception e) {
			logger.error("[DiscapacidadManagerImpl] Error al actualizar la discapacidad con id: "+
		((discapacidad.getIdDiscapacidad() != null)?discapacidad.getIdDiscapacidad().toString():"ID Desconocido"));
		}
		return update;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.discapacidad.DiscapacidadManager#obtenerDiscapacidad(java.lang.Integer)
	 */
	public DiscapacidadBean obtenerDiscapacidad(Integer idDiscapacidad){
		Discapacidad discapacidad = discapacidadDAO.get(idDiscapacidad);
		return toDiscapacidadBean(discapacidad);
	}

	/**
	 * To discapacidad.
	 *
	 * @param discapacidadBean el discapacidad bean
	 * @return el discapacidad
	 */
	public Discapacidad toDiscapacidad(DiscapacidadBean discapacidadBean){
		Discapacidad discapacidad = new Discapacidad();
		
		discapacidad.setIdDiscapacidad(discapacidadBean.getId());
		discapacidad.setDescripcion(discapacidadBean.getDescripcion());
		if(discapacidadBean.getVisibilidad() != null)
		{	
			if(discapacidadBean.getVisibilidad() == true)
			{	
				discapacidad.setVisible('S');
			}
			else if(discapacidadBean.getVisibilidad() == false)
			{	
				discapacidad.setVisible('N');
			}
		}
		else
		{	
			discapacidad.setVisible('N');
		}	
		
		CentroGestor centroGestor = new CentroGestor();
		centroGestor.setIdCentroGestor(discapacidadBean.getIdCentroGestor());
		discapacidad.setCentroGestor(centroGestor);
				
				
		return discapacidad;
	}
	
	/**
	 * To discapacidad bean.
	 *
	 * @param discapacidad el discapacidad
	 * @return el discapacidad bean
	 */
	public DiscapacidadBean toDiscapacidadBean(Discapacidad discapacidad){
		DiscapacidadBean discapacidadBean= new DiscapacidadBean();
		
		discapacidadBean.setId(discapacidad.getIdDiscapacidad());
		discapacidadBean.setDescripcion(discapacidad.getDescripcion());
		
		if(discapacidad.getCentroGestor()!=null){
			discapacidadBean.setIdCentroGestor(discapacidad.getCentroGestor().getIdCentroGestor());
			discapacidadBean.setDesCentroGestor(discapacidad.getCentroGestor().getDescripcion());
		}		
		if(!StringUtils.isEmpty(discapacidad.getVisible())) {
			if(discapacidad.getVisible().equals('S'))
			{
				discapacidadBean.setVisibilidad(true);
			}	
			else if(discapacidad.getVisible().equals('N'))
			{
				discapacidadBean.setVisibilidad(false);
			}		
		}
		else
		{
			discapacidadBean.setVisibilidad(false);
		}
				
		return discapacidadBean;
	}

	/**
	 * Obtiene el logger.
	 *
	 * @return el logger
	 */
	public static Logger getLogger() {
		return logger;
	}

	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.discapacidad.DiscapacidadManager#eliminarDiscapacidad(es.map.ipsc.bean.DiscapacidadBean)
	 */
	@Override
	public Boolean eliminarDiscapacidad(DiscapacidadBean discapacidadBean) {
		Boolean eliminado = false;
		Discapacidad discapacidad = toDiscapacidad(discapacidadBean);
		try {
			discapacidadDAO.delete(discapacidad.getIdDiscapacidad());
			eliminado = true;
		} catch (Exception e) {
			logger.error("[DiscapacidadManagerImpl] Error al eliminar la discapacidad con id: "+
		((discapacidad.getIdDiscapacidad() != null)?discapacidad.getIdDiscapacidad().toString():"ID Desconocido"));
		}
		return eliminado;
	}
	
}	
	
