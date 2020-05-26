package es.map.ipsg.manager;

import java.util.ArrayList;
import java.util.List;

import es.map.ips.model.GrupoQuery;
import es.map.ipsg.bean.GrupoBean;


/**
 * El Interface GrupoManager.
 */
public interface GrupoManager {

	/**
	 * Buscar grupo combo.
	 *
	 * @param grupoQuery el grupo query
	 * @return el array list
	 */
	public ArrayList<GrupoBean> buscarGrupoCombo(GrupoQuery grupoQuery);

	/**
	 * Buscar grupo all.
	 *
	 * @param grupoQuery el grupo query
	 * @return el list
	 */
	public List<GrupoBean> buscarGrupoAll(GrupoQuery grupoQuery);

	/**
	 * Buscar grupo unico.
	 *
	 * @param grupoQuery el grupo query
	 * @return el grupo bean
	 */
	public GrupoBean buscarGrupoUnico(GrupoQuery grupoQuery);

	/**
	 * Guardar grupo.
	 *
	 * @param grupoBean el grupo bean
	 * @return el int
	 */
	public int guardarGrupo(GrupoBean grupoBean);

	/**
	 * Actualizar grupo.
	 *
	 * @param grupoBean el grupo bean
	 */
	public void actualizarGrupo(GrupoBean grupoBean);

}
