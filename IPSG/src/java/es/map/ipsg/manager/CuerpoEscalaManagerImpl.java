package es.map.ipsg.manager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import org.apache.log4j.Logger;

import es.map.ips.common.exception.ApplicationException;
import es.map.ips.common.model.OrderType;
import es.map.ips.common.model.SearchResult;
import es.map.ips.dao.CuerpoEscalaDAO;
import es.map.ips.model.Categoria;
import es.map.ips.model.CentroGestor;
import es.map.ips.model.CuerpoEscala;
import es.map.ips.model.CuerpoEscalaQuery;
import es.map.ips.model.Especialidad;
import es.map.ips.model.Grupo;
import es.map.ipsg.bean.CuerpoEscalaBean;
import es.map.ipsg.bean.EspecialidadBean;
import es.map.ipsg.util.Constantes;



/**
 * El Class CuerpoEscalaManagerImpl.
 */
public class CuerpoEscalaManagerImpl implements CuerpoEscalaManager {

	/** el cuerpo escala DAO. */
	//Variables
	private CuerpoEscalaDAO cuerpoEscalaDAO;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(CuerpoEscalaManagerImpl.class);
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.CuerpoEscalaManager#buscarCuerposEscalaCombo(es.map.ips.model.CuerpoEscalaQuery)
	 */
	public ArrayList<CuerpoEscalaBean> buscarCuerposEscalaCombo(CuerpoEscalaQuery cuerpoEscalaQuery){	
		cuerpoEscalaQuery.addOrder(CuerpoEscalaQuery.DESCRIPCION, OrderType.ASC);
		cuerpoEscalaQuery.setEstado(Constantes.CUERPO_ESCALA_ESTADO_ACTIVO);
		SearchResult<CuerpoEscala> cuerpoEscala = buscarCuerpoEscala(cuerpoEscalaQuery);
		ArrayList<CuerpoEscalaBean> arrCuerpoEscalas= new ArrayList<CuerpoEscalaBean>();
		for(int i=0;i<cuerpoEscala.getResults().size();i++){
			CuerpoEscalaBean aux;
			aux = toCuerpoEscalaComboBean(cuerpoEscala.getResults().get(i));
			if(aux != null){
				arrCuerpoEscalas.add(aux);
			}
		}	
		return arrCuerpoEscalas;		
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.CuerpoEscalaManager#buscarCuerposEscalaComboVisibilidad(es.map.ips.model.CuerpoEscalaQuery)
	 */
	public ArrayList<CuerpoEscalaBean> buscarCuerposEscalaComboVisibilidad(CuerpoEscalaQuery cuerpoEscalaQuery){		
		cuerpoEscalaQuery.setEstado(Constantes.CUERPO_ESCALA_ESTADO_ACTIVO);
		SearchResult<CuerpoEscala> cuerpoEscala = buscarCuerpoEscala(cuerpoEscalaQuery);
		ArrayList<CuerpoEscalaBean> arrCuerpoEscalas= new ArrayList<CuerpoEscalaBean>();
		for(int i=0;i<cuerpoEscala.getResults().size();i++){
			//Sólo se mostrarán registros visibles	
			if(cuerpoEscala.getResults().get(i).getVisible() == 'S' || cuerpoEscala.getResults().get(i).getVisible() == 's')
			{	
				CuerpoEscalaBean aux;
				aux = toCuerpoEscalaComboBean(cuerpoEscala.getResults().get(i));
				if(aux != null){
					arrCuerpoEscalas.add(aux);
				}
			}	
		}	
		return arrCuerpoEscalas;		
	}
	
	
	/**
	 * Buscar cuerpo escala all.
	 *
	 * @param cuerpoEscalaQuery el cuerpo escala query
	 * @return el array list
	 */
	public ArrayList<CuerpoEscalaBean> buscarCuerpoEscalaAll(CuerpoEscalaQuery cuerpoEscalaQuery){		
		cuerpoEscalaQuery.setEstado(Constantes.CUERPO_ESCALA_ESTADO_ACTIVO);
		SearchResult<CuerpoEscala> cuerpoEscala = buscarCuerpoEscala(cuerpoEscalaQuery);
		int numTotal = 0;
		
		if(cuerpoEscala.getNumResults() != null){
			numTotal = cuerpoEscala.getNumResults();
		}
		
		ArrayList<CuerpoEscalaBean> arrCuerpoEscalas= new ArrayList<CuerpoEscalaBean>();
		
		for(int i=0;i<cuerpoEscala.getResults().size();i++){
			CuerpoEscalaBean aux;
			aux = toCuerpoEscalaBean(cuerpoEscala.getResults().get(i),numTotal);
			if(aux != null){
				arrCuerpoEscalas.add(aux);
			}
		}	
		return arrCuerpoEscalas;		
	}
	
	
	/**
	 * Buscar cuerpo escala.
	 *
	 * @param cuerpoEscalaQuery el cuerpo escala query
	 * @return el search result
	 */
	private SearchResult<CuerpoEscala> buscarCuerpoEscala(CuerpoEscalaQuery cuerpoEscalaQuery) {
			ApplicationException.assertNotNull(cuerpoEscalaQuery, "cuerpoEscalaQuery no puede ser null");
		return cuerpoEscalaDAO.search(cuerpoEscalaQuery);
	}

	/**
	 * To cuerpo escala bean.
	 *
	 * @param cuerpoEscala el cuerpo escala
	 * @param numTotal el num total
	 * @return el cuerpo escala bean
	 */
	public CuerpoEscalaBean toCuerpoEscalaBean(CuerpoEscala cuerpoEscala, int numTotal) {
		logger.info("en toCuerpoEscalaBean()");
		int id = cuerpoEscala.getId();
		String descripcion = cuerpoEscala.getDescripcion();
		String codigo = cuerpoEscala.getCodigo();
		
		Categoria categoria = cuerpoEscala.getCategoria();
		CentroGestor centroGestor=cuerpoEscala.getCentroGestor();
		Grupo grupo=cuerpoEscala.getGrupo();
		
		CuerpoEscalaBean cuerpoEscalaBean= new CuerpoEscalaBean();
		cuerpoEscalaBean.setCodigo(codigo);
		cuerpoEscalaBean.setDescripcion(descripcion);
		cuerpoEscalaBean.setId(id);
		cuerpoEscalaBean.setDesCategoria(categoria.getDescripcion());
		cuerpoEscalaBean.setDesCentroGestor(centroGestor.getDescripcion());
		cuerpoEscalaBean.setDesGrupo(grupo.getDescripcion());
		if(cuerpoEscala.getVisible() != null && !cuerpoEscala.getVisible().equals(""))
		{
			if(cuerpoEscala.getVisible().equals('S'))
			{
				cuerpoEscalaBean.setVisibilidad(true);
			}	
			else if(cuerpoEscala.getVisible().equals('N'))
			{
				cuerpoEscalaBean.setVisibilidad(false);
			}		
		}
		else
		{
			cuerpoEscalaBean.setVisibilidad(false);
		}
		cuerpoEscalaBean.setNumTotal(numTotal);

	    return cuerpoEscalaBean;
	}
	
	/**
	 * To cuerpo escala combo bean.
	 *
	 * @param cuerpoEscala el cuerpo escala
	 * @return el cuerpo escala bean
	 */
	public CuerpoEscalaBean toCuerpoEscalaComboBean(CuerpoEscala cuerpoEscala) {
		int id = cuerpoEscala.getId();
		String descripcion = cuerpoEscala.getDescripcion();
		String codigo = cuerpoEscala.getCodigo();		
		
		CuerpoEscalaBean cuerpoEscalaBean= new CuerpoEscalaBean();
		cuerpoEscalaBean.setCodigo(codigo);
		cuerpoEscalaBean.setDescripcion(descripcion);
		cuerpoEscalaBean.setId(id);

	    return cuerpoEscalaBean;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.CuerpoEscalaManager#obtenerEspecialidadesCuerpoEscala(es.map.ips.model.CuerpoEscalaQuery)
	 */
	public ArrayList<EspecialidadBean> obtenerEspecialidadesCuerpoEscala(CuerpoEscalaQuery cuerpoEscalaQuery){
		ArrayList<EspecialidadBean> arrEspecialidades=new ArrayList<EspecialidadBean>();
		int id=cuerpoEscalaQuery.getId();
		Set<Especialidad> arrEspecialidadesModel=cuerpoEscalaDAO.get(id).getEspecialidads();
		Iterator<Especialidad> it = arrEspecialidadesModel.iterator();
		while(it.hasNext()){
			
			Especialidad e=(Especialidad)it.next();
			arrEspecialidades.add(toEspecialidadBean(e));
		}

		return arrEspecialidades;
	}
	
	/**
	 * To especialidad bean.
	 *
	 * @param especialidad el especialidad
	 * @return el especialidad bean
	 */
	private EspecialidadBean toEspecialidadBean(Especialidad especialidad) {
		EspecialidadBean especialidadBean = new EspecialidadBean();
			especialidadBean.setCodigo(especialidad.getCodigo());
			especialidadBean.setConvocatorias(especialidad.getConvocatorias());
			//CAMBIO
			especialidadBean.setDesCuerpoEscala(especialidad.getCuerpoEscala().getDescripcion());
			especialidadBean.setDescripcion(especialidad.getDescripcion());
			especialidadBean.setEstado(especialidad.getEstado());
			especialidadBean.setId(especialidad.getId());
			especialidadBean.setSolicituds(especialidad.getSolicituds());
			if(especialidad.getVisible() != null && !especialidad.getVisible().equals(""))
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

	/**
	 * Obtiene el cuerpo escala DAO.
	 *
	 * @return el cuerpo escala DAO
	 */
	public CuerpoEscalaDAO getCuerpoEscalaDAO() {
		return cuerpoEscalaDAO;
	}

	/**
	 * Establece el cuerpo escala DAO.
	 *
	 * @param cuerpoEscalaDAO el nuevo cuerpo escala DAO
	 */
	public void setCuerpoEscalaDAO(CuerpoEscalaDAO cuerpoEscalaDAO) {
		this.cuerpoEscalaDAO = cuerpoEscalaDAO;
	}

	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.CuerpoEscalaManager#guardarCuerposEscala(es.map.ipsg.bean.CuerpoEscalaBean)
	 */
	public Integer guardarCuerposEscala(CuerpoEscalaBean cuerpoEscalaBean){
		CuerpoEscala cuerpoEscala= toCuerpoEscala(cuerpoEscalaBean);
		Integer idCuerpoEscala = cuerpoEscalaDAO.insert(cuerpoEscala);
		return idCuerpoEscala;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.CuerpoEscalaManager#modificarCuerposEscala(es.map.ipsg.bean.CuerpoEscalaBean)
	 */
	public void modificarCuerposEscala(CuerpoEscalaBean cuerpoEscalaBean){
		CuerpoEscala cuerpoEscala = toCuerpoEscala(cuerpoEscalaBean);
		cuerpoEscalaDAO.update(cuerpoEscala);
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.CuerpoEscalaManager#obtenerCuerpoEscala(java.lang.Integer)
	 */
	public CuerpoEscalaBean obtenerCuerpoEscala(Integer idCuerpoEscala){
		CuerpoEscala cuerpoEscala = cuerpoEscalaDAO.get(idCuerpoEscala);
		return toCuerpoEscalaBean(cuerpoEscala);
	}

	/**
	 * To cuerpo escala.
	 *
	 * @param cuerpoEscalaBean el cuerpo escala bean
	 * @return el cuerpo escala
	 */
	public CuerpoEscala toCuerpoEscala(CuerpoEscalaBean cuerpoEscalaBean){
		CuerpoEscala cuerpoEscala = new CuerpoEscala();
		
		cuerpoEscala.setId(cuerpoEscalaBean.getId());
		cuerpoEscala.setCodigo(cuerpoEscalaBean.getCodigo());
		cuerpoEscala.setDescripcion(cuerpoEscalaBean.getDescripcion());
		if(cuerpoEscalaBean.getVisibilidad() != null)
		{	
			if(cuerpoEscalaBean.getVisibilidad() == true)
			{	
				cuerpoEscala.setVisible('S');
			}
			else if(cuerpoEscalaBean.getVisibilidad() == false)
			{	
				cuerpoEscala.setVisible('N');
			}
		}
		else
		{	
			cuerpoEscala.setVisible('N');
		}	

		Categoria categoria = new Categoria();
		categoria.setId(cuerpoEscalaBean.getIdCategoria());
		cuerpoEscala.setCategoria(categoria);
		
		Grupo grupo = new Grupo();
		grupo.setId(cuerpoEscalaBean.getIdGrupo());
		cuerpoEscala.setGrupo(grupo);
		
		CentroGestor centroGestor = new CentroGestor();
		centroGestor.setIdCentroGestor(cuerpoEscalaBean.getIdCentroGestor());
		cuerpoEscala.setCentroGestor(centroGestor);
				
		cuerpoEscala.setEstado(cuerpoEscalaBean.getEstado());
				
		return cuerpoEscala;
	}
	
	/**
	 * To cuerpo escala bean.
	 *
	 * @param cuerpoEscala el cuerpo escala
	 * @return el cuerpo escala bean
	 */
	public CuerpoEscalaBean toCuerpoEscalaBean(CuerpoEscala cuerpoEscala){
		CuerpoEscalaBean cuerpoEscalaBean= new CuerpoEscalaBean();
		
		cuerpoEscalaBean.setId(cuerpoEscala.getId());
		cuerpoEscalaBean.setCodigo(cuerpoEscala.getCodigo());
		cuerpoEscalaBean.setDescripcion(cuerpoEscala.getDescripcion());
		
		if(cuerpoEscala.getCentroGestor()!=null){
			cuerpoEscalaBean.setIdCentroGestor(cuerpoEscala.getCentroGestor().getIdCentroGestor());
			cuerpoEscalaBean.setDesCentroGestor(cuerpoEscala.getCentroGestor().getDescripcion());
		}
		if(cuerpoEscala.getGrupo()!=null){
			cuerpoEscalaBean.setIdGrupo(cuerpoEscala.getGrupo().getId());
			cuerpoEscalaBean.setDesGrupo(cuerpoEscala.getCentroGestor().getDescripcion());
		}		
		if(cuerpoEscala.getCategoria()!=null){
			cuerpoEscalaBean.setIdCategoria(cuerpoEscala.getCategoria().getId());
			cuerpoEscalaBean.setDesCategoria(cuerpoEscala.getCategoria().getDescripcion());
		}		
		if(cuerpoEscala.getVisible() != null && !cuerpoEscala.getVisible().equals(""))
		{
			if(cuerpoEscala.getVisible().equals('S'))
			{
				cuerpoEscalaBean.setVisibilidad(true);
			}	
			else if(cuerpoEscala.getVisible().equals('N'))
			{
				cuerpoEscalaBean.setVisibilidad(false);
			}		
		}
		else
		{
			cuerpoEscalaBean.setVisibilidad(false);
		}
				
		return cuerpoEscalaBean;
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
	
