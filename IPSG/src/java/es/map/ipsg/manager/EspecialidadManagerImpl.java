package es.map.ipsg.manager;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import es.map.ips.common.exception.ApplicationException;
import es.map.ips.common.model.SearchResult;
import es.map.ips.dao.EspecialidadDAO;
import es.map.ips.model.CuerpoEscala;
import es.map.ips.model.Especialidad;
import es.map.ips.model.EspecialidadQuery;
import es.map.ipsg.bean.EspecialidadBean;
import es.map.ipsg.util.Constantes;


/**
 * El Class EspecialidadManagerImpl.
 */
public class EspecialidadManagerImpl implements EspecialidadManager {

	/** el especialidad DAO. */
	//Variables
	private EspecialidadDAO especialidadDAO;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(EspecialidadManagerImpl.class);
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.EspecialidadManager#buscarEspecialidadCombo(es.map.ips.model.EspecialidadQuery)
	 */
	public ArrayList<EspecialidadBean> buscarEspecialidadCombo(EspecialidadQuery especialidadQuery){
		especialidadQuery.setEstado(Constantes.ESPECIALIDAD_ESTADO_ACTIVO);
		SearchResult<Especialidad> especialidad = buscarEspecialidad(especialidadQuery);
		ArrayList<EspecialidadBean> arrEspecialidad= new ArrayList<EspecialidadBean>();
		for(int i=0;i<especialidad.getResults().size();i++){
			EspecialidadBean aux;
			aux = toEspecialidadComboBean(especialidad.getResults().get(i));
			if(aux != null){
				arrEspecialidad.add(aux);
			}
		}	
		return arrEspecialidad;		
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.EspecialidadManager#buscarEspecialidadComboVisibilidad(es.map.ips.model.EspecialidadQuery)
	 */
	public ArrayList<EspecialidadBean> buscarEspecialidadComboVisibilidad(EspecialidadQuery especialidadQuery){
		especialidadQuery.setEstado(Constantes.ESPECIALIDAD_ESTADO_ACTIVO);
		SearchResult<Especialidad> especialidad = buscarEspecialidad(especialidadQuery);
		ArrayList<EspecialidadBean> arrEspecialidad= new ArrayList<EspecialidadBean>();
		for(int i=0;i<especialidad.getResults().size();i++){
			//Solo se mostraran registros visibles	
			if(especialidad.getResults().get(i).getVisible() == 'S' || especialidad.getResults().get(i).getVisible() == 's')
			{	
				EspecialidadBean aux;
				aux = toEspecialidadComboBean(especialidad.getResults().get(i));
				if(aux != null){
					arrEspecialidad.add(aux);
				}
			}	
		}	
		return arrEspecialidad;		
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.EspecialidadManager#buscarEspecialidadAll(es.map.ips.model.EspecialidadQuery)
	 */
	public ArrayList<EspecialidadBean> buscarEspecialidadAll(EspecialidadQuery especialidadQuery){		
		especialidadQuery.setEstado(Constantes.ESPECIALIDAD_ESTADO_ACTIVO);
		SearchResult<Especialidad> especialidad = buscarEspecialidad(especialidadQuery);
		int numTotal = 0;
		if (especialidad.getNumResults() !=null){
			numTotal = especialidad.getNumResults();
		}
		ArrayList<EspecialidadBean> arrEspecialidad= new ArrayList<EspecialidadBean>();
		for(int i=0;i<especialidad.getResults().size();i++){
			EspecialidadBean aux;
			aux = toEspecialidadBean(especialidad.getResults().get(i),numTotal);
			if(aux != null){
				arrEspecialidad.add(aux);
			}
		}	
		return arrEspecialidad;		
	}
	
	/**
	 * To especialidad bean.
	 *
	 * @param especialidad el especialidad
	 * @param numTotal el num total
	 * @return el especialidad bean
	 */
	private EspecialidadBean toEspecialidadBean(Especialidad especialidad, int numTotal) {
		int id = especialidad.getId();
		String descripcion = especialidad.getDescripcion();
		String codigo = especialidad.getCodigo();
		CuerpoEscala cuerpoEscala = especialidad.getCuerpoEscala();
		
		EspecialidadBean auxEspecialidad = new EspecialidadBean();
		auxEspecialidad.setId(id);
		auxEspecialidad.setDescripcion(descripcion);
		auxEspecialidad.setCodigo(codigo);
		////CAMBIO
		auxEspecialidad.setDesCuerpoEscala(cuerpoEscala.getDescripcion());
		
		auxEspecialidad.setNumTotal(numTotal);
		
		if(especialidad.getVisible() != null)
		{	
			if(especialidad.getVisible().equals('S'))
			{
				auxEspecialidad.setVisibilidad(true);
			}	
			else if(especialidad.getVisible().equals('N'))
			{
				auxEspecialidad.setVisibilidad(false);
			}	
		}
		else
		{
			auxEspecialidad.setVisibilidad(false);
		}	
		
		return auxEspecialidad;
	}
	
	/**
	 * To especialidad combo bean.
	 *
	 * @param especialidad el especialidad
	 * @return el especialidad bean
	 */
	private EspecialidadBean toEspecialidadComboBean(Especialidad especialidad) {
		int id = especialidad.getId();
		String descripcion = especialidad.getDescripcion();
		String codigo = especialidad.getCodigo();
		
		EspecialidadBean auxEspecialidad = new EspecialidadBean();
		auxEspecialidad.setId(id);
		auxEspecialidad.setDescripcion(descripcion);
		auxEspecialidad.setCodigo(codigo);
		
		
		
		
		return auxEspecialidad;
	}

	/**
	 * Buscar especialidad.
	 *
	 * @param especialidadQuery el especialidad query
	 * @return el search result
	 */
	private SearchResult<Especialidad> buscarEspecialidad(EspecialidadQuery especialidadQuery) {
			ApplicationException.assertNotNull(especialidadQuery, "especialidadQuery no puede ser null");
		
		return especialidadDAO.search(especialidadQuery);
	}

	/**
	 * Obtiene el especialidad DAO.
	 *
	 * @return el especialidad DAO
	 */
	public EspecialidadDAO getEspecialidadDAO() {
		return especialidadDAO;
	}

	/**
	 * Establece el especialidad DAO.
	 *
	 * @param especialidadDAO el nuevo especialidad DAO
	 */
	public void setEspecialidadDAO(EspecialidadDAO especialidadDAO) {
		this.especialidadDAO = especialidadDAO;
	}

	/**
	 * To especialidad.
	 *
	 * @param especialidadBean el especialidad bean
	 * @return el especialidad
	 */
	public Especialidad toEspecialidad(EspecialidadBean especialidadBean){
		Especialidad especialidad = null;
		
		if(especialidadBean.getId()!=null && especialidadBean.getId()>0)
			especialidad = especialidadDAO.get(especialidadBean.getId());
		else
			especialidad = new Especialidad();
		
		especialidad.setId(especialidadBean.getId());
		especialidad.setCodigo(especialidadBean.getCodigo());
		especialidad.setDescripcion(especialidadBean.getDescripcion());
		
		CuerpoEscala cuerpoEscala = new CuerpoEscala();
		cuerpoEscala.setId(especialidadBean.getIdCuerpoEscala());
		especialidad.setCuerpoEscala(cuerpoEscala);
		
		especialidad.setEstado(especialidadBean.getEstado());
		
		if(especialidadBean.getVisibilidad() != null)
		{
			if(especialidadBean.getVisibilidad() == true)
			{	
				especialidad.setVisible('S');
			}
			else if(especialidadBean.getVisibilidad() == false)
			{
				especialidad.setVisible('N');
			}	
		}
		else
		{
			especialidad.setVisible('N');
		}
		return especialidad;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.EspecialidadManager#guardarEspecialidad(es.map.ipsg.bean.EspecialidadBean)
	 */
	public Integer guardarEspecialidad(EspecialidadBean especialidadBean){
		Especialidad especialidad = toEspecialidad(especialidadBean);
		Integer idEspecialidad = especialidadDAO.insert(especialidad);		
		return idEspecialidad;
	}

	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.EspecialidadManager#obtenerEspecialidad(java.lang.Integer)
	 */
	public EspecialidadBean obtenerEspecialidad(Integer idEspecialidad){
		Especialidad especialidad = especialidadDAO.get(idEspecialidad);
		return toEspecialidadBean(especialidad);
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.EspecialidadManager#buscarEspecialidadUnique(es.map.ips.model.EspecialidadQuery)
	 */
	public EspecialidadBean buscarEspecialidadUnique(
			EspecialidadQuery especialidadQuery) {
		Especialidad especialidad = especialidadDAO.searchUnique(especialidadQuery);
		if(especialidad != null){
			return toEspecialidadBean(especialidad);
		}
		return null;
	}
	
	/**
	 * To especialidad bean.
	 *
	 * @param especialidad el especialidad
	 * @return el especialidad bean
	 */
	public EspecialidadBean toEspecialidadBean(Especialidad especialidad){
		EspecialidadBean especialidadBean = new EspecialidadBean();
		
		especialidadBean.setId(especialidad.getId());
		especialidadBean.setCodigo(especialidad.getCodigo());
		especialidadBean.setDescripcion(especialidad.getDescripcion());
		especialidadBean.setEstado(especialidad.getEstado());
		
		if(especialidad.getCuerpoEscala()!=null){
			especialidadBean.setIdCuerpoEscala(especialidad.getCuerpoEscala().getId());
			especialidadBean.setDesCuerpoEscala(especialidad.getCuerpoEscala().getDescripcion());
		}
		
		if(especialidad.getVisible() != null)
		{	
			if(especialidad.getVisible().equals('S'))
			{
				especialidadBean.setVisibilidad(true);
			}	
			else if(especialidad.getVisible().equals('N'))
			{
				especialidadBean.setVisibilidad(false);
			}	
		}
		else
		{
			especialidadBean.setVisibilidad(false);
		}	
		return especialidadBean;
	}

	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.EspecialidadManager#modificarEspecialidad(es.map.ipsg.bean.EspecialidadBean)
	 */
	public void modificarEspecialidad(EspecialidadBean especialidadBean){
		Especialidad especialidad = toEspecialidad(especialidadBean);
		especialidadDAO.update(especialidad);
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