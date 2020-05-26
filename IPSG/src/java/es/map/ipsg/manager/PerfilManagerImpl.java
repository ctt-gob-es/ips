package es.map.ipsg.manager;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import es.map.ips.common.model.OrderType;
import es.map.ips.dao.PerfilDAO;
import es.map.ips.model.CategoriaQuery;
import es.map.ips.model.Perfil;
import es.map.ips.model.PerfilQuery;
import es.map.ipsg.bean.PerfilBean;

/**
 * El Class PerfilManagerImpl.
 */
public class PerfilManagerImpl implements PerfilManager {

	/** el perfil DAO. */
	private PerfilDAO perfilDAO;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(PerfilManagerImpl.class);
	
	/**
	 * Establece el perfil DAO.
	 *
	 * @param perfilDAO el nuevo perfil DAO
	 */
	public void setPerfilDAO(PerfilDAO perfilDAO) {
		this.perfilDAO = perfilDAO;
	}
	
	/**
	 * Obtiene el perfil DAO.
	 *
	 * @return el perfil DAO
	 */
	public PerfilDAO getPerfilDAO(){
		return perfilDAO;
	}

	/**
	 * Buscar perfiles.
	 *
	 * @param perfilQuery el perfil query
	 * @return el list
	 * @see CategoriaManager#buscarCategorias(CategoriaQuery)
	 */
	public List<PerfilBean> buscarPerfiles(PerfilQuery perfilQuery) {
		perfilQuery.addOrder(PerfilQuery.DESCRIPCION, OrderType.ASC);
		List<PerfilBean> list = new ArrayList<PerfilBean>();
		List<Perfil> perfiles = perfilDAO.search(perfilQuery).getResults();
		
		for(Perfil p : perfiles){
			list.add(toBeanPerfil(p));
		}
		
		return list;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.PerfilManager#buscarPerfil(es.map.ips.model.PerfilQuery)
	 */
	public PerfilBean buscarPerfil(PerfilQuery perfilQuery){
		Perfil perfil = perfilDAO.searchUnique(perfilQuery);
		return toBeanPerfil(perfil);
	}
	
	/**
	 * To bean perfil.
	 *
	 * @param perfil el perfil
	 * @return el perfil bean
	 */
	public PerfilBean toBeanPerfil(Perfil perfil){
		PerfilBean perfilBean = new PerfilBean();
		perfilBean.setId(perfil.getId());
		perfilBean.setDescripcion(perfil.getDescripcion());
		return perfilBean;
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
