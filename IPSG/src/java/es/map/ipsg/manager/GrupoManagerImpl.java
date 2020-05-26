package es.map.ipsg.manager;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import es.map.ips.common.exception.ApplicationException;
import es.map.ips.common.model.OrderType;
import es.map.ips.common.model.SearchResult;
import es.map.ips.dao.GrupoDAO;
import es.map.ips.model.Grupo;
import es.map.ips.model.GrupoQuery;
import es.map.ipsg.bean.GrupoBean;
import es.map.ipsg.util.Constantes;




/**
 * El Class GrupoManagerImpl.
 */
public class GrupoManagerImpl implements GrupoManager {

	/** el grupo DAO. */
	//Variables
	private GrupoDAO grupoDAO;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(GrupoManagerImpl.class);
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.GrupoManager#buscarGrupoCombo(es.map.ips.model.GrupoQuery)
	 */
	public ArrayList<GrupoBean> buscarGrupoCombo(GrupoQuery grupoQuery){
		grupoQuery.setEstado(Constantes.GRUPO_ESTADO_ACTIVO);
		grupoQuery.addOrder(GrupoQuery.DESCRIPCION,OrderType.ASC);
		SearchResult<Grupo> grupo = buscarGrupo(grupoQuery);
		ArrayList<GrupoBean> arrCentroGestor= new ArrayList<GrupoBean>();
		for(int i=0;i<grupo.getResults().size();i++){
		// Sólo se mostrarán los registros visibles
			
			
				GrupoBean aux;
				aux = toCentroGestorBean(grupo.getResults().get(i));
				if(aux != null){
					arrCentroGestor.add(aux);
				}
			
		}	
		return arrCentroGestor;		
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.GrupoManager#buscarGrupoAll(es.map.ips.model.GrupoQuery)
	 */
	public List<GrupoBean> buscarGrupoAll(GrupoQuery grupoQuery) {
		grupoQuery.setEstado(Constantes.GRUPO_ESTADO_ACTIVO);
		SearchResult<Grupo> grupo = buscarGrupo(grupoQuery);
		ArrayList<GrupoBean> arrCentroGestor= new ArrayList<GrupoBean>();
		int numTotal= 0;
		if(grupo.getNumResults() != null){
			numTotal = grupo.getNumResults();
		}
		for(int i=0;i<grupo.getResults().size();i++){
			GrupoBean aux;
			aux = toCentroGestorBean(grupo.getResults().get(i),numTotal);
			if(aux != null){
				arrCentroGestor.add(aux);
			}
		}	
		return arrCentroGestor;		
	}
	
	/**
	 * To centro gestor bean.
	 *
	 * @param grupo el grupo
	 * @param numTotal el num total
	 * @return el grupo bean
	 */
	private GrupoBean toCentroGestorBean(Grupo grupo, int numTotal) {
		int id = grupo.getId();
		String descripcion = grupo.getDescripcion();
		String codigo = grupo.getCodigo();
		char estado = grupo.getEstado();
		String siglas = grupo.getSiglas();
		
		
		GrupoBean auxGrupo = new GrupoBean();
		auxGrupo.setId(id);
		auxGrupo.setDescripcion(descripcion);
		auxGrupo.setCodigo(codigo);
		auxGrupo.setEstado(estado);
		auxGrupo.setNumTotal(numTotal);
		auxGrupo.setSiglas(siglas);
		
		if(grupo.getVisible() != null)
		{	
			if(grupo.getVisible().equals('S'))
			{
				auxGrupo.setVisibilidad(true);
			}	
			else if(grupo.getVisible().equals('N'))
			{
				auxGrupo.setVisibilidad(false);
			}	
		}
		else
		{
			auxGrupo.setVisibilidad(false);
		}	
		return auxGrupo;
	}

	/**
	 * To centro gestor bean.
	 *
	 * @param grupo el grupo
	 * @return el grupo bean
	 */
	private GrupoBean toCentroGestorBean(Grupo grupo) {
		int id = grupo.getId();
		String descripcion = grupo.getDescripcion();
		String codigo = grupo.getCodigo();
		char estado = grupo.getEstado();
		String siglas = grupo.getSiglas();
		
		
		GrupoBean auxGrupo = new GrupoBean();
		auxGrupo.setId(id);
		auxGrupo.setDescripcion(descripcion);
		auxGrupo.setCodigo(codigo);
		auxGrupo.setEstado(estado);
		auxGrupo.setSiglas(siglas);
		
		if(grupo.getVisible() != null)
		{	
			if(grupo.getVisible().equals('S'))
			{
				auxGrupo.setVisibilidad(true);
			}	
			else if(grupo.getVisible().equals('N'))
			{
				auxGrupo.setVisibilidad(false);
			}	
		}
		else
		{
			auxGrupo.setVisibilidad(false);
		}	
		
		return auxGrupo;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.GrupoManager#buscarGrupoUnico(es.map.ips.model.GrupoQuery)
	 */
	public GrupoBean buscarGrupoUnico(GrupoQuery grupoQuery) {
		grupoQuery.setEstado(Constantes.GRUPO_ESTADO_ACTIVO);
		Grupo grupoAux;
		grupoAux = grupoDAO.searchUnique(grupoQuery);
		GrupoBean grupoBean = new GrupoBean();
		try{
			grupoBean = toGrupoBean(grupoAux,0);
		}catch(Exception e){
			logger.error("Error GrupoManagerImpl - buscarGrupoUnico.",e);
		}
		return grupoBean;
	}

	/**
	 * To grupo bean.
	 *
	 * @param u el u
	 * @param numTotal el num total
	 * @return el grupo bean
	 */
	private GrupoBean toGrupoBean(Grupo u, int numTotal) {
		GrupoBean aux = new GrupoBean();
		aux.setId(u.getId());
		aux.setDescripcion(u.getDescripcion());
		aux.setCodigo(u.getCodigo());
		aux.setEstado(u.getEstado());
		aux.setNumTotal(numTotal);
		aux.setSiglas(u.getSiglas());
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

	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.GrupoManager#guardarGrupo(es.map.ipsg.bean.GrupoBean)
	 */
	public int guardarGrupo(GrupoBean grupoBean) {
		Grupo grupo;
		grupo = toGrupo(grupoBean);
		int idMinisterio = grupoDAO.insert(grupo);
		
		return idMinisterio;
	}
	

	/**
	 * To grupo.
	 *
	 * @param grupoBean el grupo bean
	 * @return el grupo
	 */
	private Grupo toGrupo(GrupoBean grupoBean) {
		Grupo grupoAux = new Grupo();
		grupoAux.setId(grupoBean.getId());
		grupoAux.setCodigo(grupoBean.getCodigo());
		grupoAux.setDescripcion(grupoBean.getDescripcion());
		grupoAux.setEstado(grupoBean.getEstado());
		grupoAux.setSiglas(grupoBean.getSiglas());
		if(grupoBean.getVisibilidad() != null)
		{
			if(grupoBean.getVisibilidad() == true)
			{	
				grupoAux.setVisible('S');
			}
			else if(grupoBean.getVisibilidad() == false)
			{
				grupoAux.setVisible('N');
			}	
		}
		else
		{
			grupoAux.setVisible('N');
		}
		return grupoAux;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.GrupoManager#actualizarGrupo(es.map.ipsg.bean.GrupoBean)
	 */
	public void actualizarGrupo(GrupoBean grupoBean) {
		Grupo grupo;
		grupo = toMinisterio(grupoBean);
		grupoDAO.update(grupo);
		
	}

	/**
	 * To ministerio.
	 *
	 * @param grupoBean el grupo bean
	 * @return el grupo
	 */
	private Grupo toMinisterio(GrupoBean grupoBean) {
		Grupo grupoAux = new Grupo();
		grupoAux.setId(grupoBean.getId());
		grupoAux.setCodigo(grupoBean.getCodigo());
		grupoAux.setDescripcion(grupoBean.getDescripcion());
		grupoAux.setEstado(grupoBean.getEstado());
		grupoAux.setSiglas(grupoBean.getSiglas());
		if(grupoBean.getVisibilidad() != null)
		{
			if(grupoBean.getVisibilidad() == true)
			{	
				grupoAux.setVisible('S');
			}
			else if(grupoBean.getVisibilidad() == false)
			{
				grupoAux.setVisible('N');
			}	
		}
		else
		{
			grupoAux.setVisible('N');
		}
		return grupoAux;
	}

	/**
	 * Buscar grupo.
	 *
	 * @param grupoQuery el grupo query
	 * @return el search result
	 */
	private SearchResult<Grupo> buscarGrupo(GrupoQuery grupoQuery) {
			ApplicationException.assertNotNull(grupoQuery, "convocatoriaQuery no puede ser null");
		
		return grupoDAO.search(grupoQuery);
	}

	/**
	 * Obtiene el grupo DAO.
	 *
	 * @return el grupo DAO
	 */
	public GrupoDAO getGrupoDAO() {
		return grupoDAO;
	}

	/**
	 * Establece el grupo DAO.
	 *
	 * @param grupoDAO el nuevo grupo DAO
	 */
	public void setGrupoDAO(GrupoDAO grupoDAO) {
		this.grupoDAO = grupoDAO;
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
	
