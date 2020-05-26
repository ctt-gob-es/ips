package es.map.ipsc.manager.grupo;

import java.util.ArrayList;

import es.map.ipsc.bean.GrupoBean;

/**
 * El Interface GrupoManager.
 */
public interface GrupoManager {

	/**
	 * Buscar grupos combo.
	 *
	 * @return el array list
	 */
	ArrayList<GrupoBean> buscarGruposCombo();

	
}
