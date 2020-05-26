package es.map.ipsg.manager;


import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import es.map.ips.common.model.SearchResult;
import es.map.ips.dao.UsuarioCentrogestorDAO;
import es.map.ips.dao.UsuarioDAO;
import es.map.ips.model.CentroGestor;
import es.map.ips.model.Usuario;
import es.map.ips.model.UsuarioCentrogestor;
import es.map.ips.model.UsuarioCentrogestorQuery;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.bean.CentroGestorBean;

/**
 *  Clase que implementa el UsuarioCentrogestorManager.
 *
 * @author amartinl
 */ 

public class UsuarioCentrogestorManagerImpl implements UsuarioCentrogestorManager {

	private UsuarioCentrogestorDAO usuarioCentrogestorDAO;

	private UsuarioDAO idUsuarioDAO;

	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(UsuarioCentrogestorManagerImpl.class);

	@Override
	public Long guardarUsuCentroGestor(int idUsuario, int idCentroGestor) {
		UsuarioCentrogestor usuCg = new UsuarioCentrogestor();
		
		Usuario usuario = new Usuario();
		usuario.setId(idUsuario);
		usuCg.setUsuario(usuario);
		
		CentroGestor centroGestor = new CentroGestor();
		centroGestor.setIdCentroGestor(idCentroGestor);
		usuCg.setCentroGestor(centroGestor);
		
		Long id = usuarioCentrogestorDAO.insert(usuCg);
		
		return id;
	}

	@Override
	public List<CentroGestorBean> buscarCentrosGestoresByIdUsuario(Integer id) {
		List<CentroGestorBean> listaCg = new ArrayList<CentroGestorBean>();
		UsuarioCentrogestorQuery query = new UsuarioCentrogestorQuery();
		UsuarioQuery usuario = new UsuarioQuery();
		
		usuario.setId(id);
		query.setUsuario(usuario);
		
		SearchResult<UsuarioCentrogestor> result = usuarioCentrogestorDAO.search(query);
		
		for(UsuarioCentrogestor usuCg: result.getResults()) {
			CentroGestorBean cg = toCentroGestor(usuCg);
			listaCg.add(cg);
		}
		
		return listaCg;
	}
	
	@Override
	public List<Integer> buscarUsuariosByIdCg(List<CentroGestorBean> listaCentrosGestores) {
		List<Integer> idsUsuarios = new ArrayList<Integer>();
		UsuarioCentrogestorQuery query = new UsuarioCentrogestorQuery();
		for(CentroGestorBean cg :listaCentrosGestores) {
			query.addCentroGestorIn(cg.getId());	
		}
		SearchResult <UsuarioCentrogestor> result = usuarioCentrogestorDAO.search(query);
		
		for(UsuarioCentrogestor usuCg: result.getResults()) {
			idsUsuarios.add(usuCg.getUsuario().getId());
		}
		
		return idsUsuarios;
	}
	
	@Override
	public void modificarRelaciones(Integer idUsuario, String idCgForm) {
		try {
			UsuarioCentrogestorQuery query = new UsuarioCentrogestorQuery();
			UsuarioQuery usuarioQuery = new UsuarioQuery();
			usuarioQuery.setId(idUsuario);
			query.setUsuario(usuarioQuery);
			SearchResult<UsuarioCentrogestor> listaUsuCg = usuarioCentrogestorDAO.search(query);
			
			for(UsuarioCentrogestor usuCg: listaUsuCg.getResults()) {
				usuarioCentrogestorDAO.delete(usuCg.getIdRegistro());
			}
			
			for(String idCg: idCgForm.split(",")) {
				UsuarioCentrogestor usuarioCentroGestor = new UsuarioCentrogestor();
				Usuario usuario = new Usuario();
				usuario.setId(idUsuario);
				usuarioCentroGestor.setUsuario(usuario );
				CentroGestor centroGestor = new CentroGestor();
				centroGestor.setIdCentroGestor(Integer.parseInt(idCg));
				usuarioCentroGestor.setCentroGestor(centroGestor);
				usuarioCentrogestorDAO.insert(usuarioCentroGestor);
			}
		} catch (Exception e) {
			logger.error("Se ha producido un error al actualizar las relaciones de Usuario con Centro gestor",e);
		}
	}
	
	private CentroGestorBean toCentroGestor(UsuarioCentrogestor usuCg) {
		CentroGestorBean auxCentroGestor = new CentroGestorBean();
		CentroGestor centroGestor = usuCg.getCentroGestor();
		
		auxCentroGestor.setId(centroGestor .getIdCentroGestor());
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
//		auxCentroGestor.setNumTotal(numTotal);
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
	
	/**
	 * @return the usuarioCentrogestorDAO
	 */
	public UsuarioCentrogestorDAO getUsuarioCentrogestorDAO() {
		return usuarioCentrogestorDAO;
	}

	/**
	 * @param usuarioCentrogestorDAO the usuarioCentrogestorDAO to set
	 */
	public void setUsuarioCentrogestorDAO(UsuarioCentrogestorDAO usuarioCentrogestorDAO) {
		this.usuarioCentrogestorDAO = usuarioCentrogestorDAO;
	}
	
	/**
	 * @return the usuarioCentrogestorDAO
	 */
	public UsuarioDAO getIdUsuarioDAO() {
		return idUsuarioDAO;
	}

	/**
	 * @param usuarioCentrogestorDAO the usuarioCentrogestorDAO to set
	 */
	public void setUsuarioDAO(UsuarioDAO idUsuarioDAO) {
		this.idUsuarioDAO = idUsuarioDAO;
	}

}
