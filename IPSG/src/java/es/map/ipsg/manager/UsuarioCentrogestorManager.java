package es.map.ipsg.manager;

import java.util.List;

import es.map.ipsg.bean.CentroGestorBean;

/**
 * UsuarioCentrogestorManager.
 */
public interface UsuarioCentrogestorManager {
	
	Long guardarUsuCentroGestor(int idUsuario, int idCentroGestor);

	List<CentroGestorBean> buscarCentrosGestoresByIdUsuario(Integer id);

	List<Integer> buscarUsuariosByIdCg(List<CentroGestorBean> listaCentrosGestores);

	void modificarRelaciones(Integer idUsuario, String idCgForm);
	
}
