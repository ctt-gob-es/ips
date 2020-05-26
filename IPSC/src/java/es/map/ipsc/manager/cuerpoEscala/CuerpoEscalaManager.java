package es.map.ipsc.manager.cuerpoEscala;

import java.util.ArrayList;

import es.map.ips.common.model.SearchResult;
import es.map.ips.model.CuerpoEscala;
import es.map.ips.model.CuerpoEscalaQuery;
import es.map.ips.model.Grupo;
import es.map.ipsc.bean.CuerpoEscalaBean;


/**
 * El Interface CuerpoEscalaManager.
 */
public interface CuerpoEscalaManager {

	/**
	 * Buscar cuerpo escala.
	 *
	 * @return el array list
	 */
	ArrayList<CuerpoEscalaBean> buscarCuerpoEscala();

	/**
	 * Buscar cuerpo escala id.
	 *
	 * @param cuerpoEscalaQuery el cuerpo escala query
	 * @return el search result
	 */
	SearchResult<CuerpoEscala> buscarCuerpoEscalaId(CuerpoEscalaQuery cuerpoEscalaQuery);
	
	/**
	 * Buscar cuerpo escala id 2.
	 *
	 * @param cuerpoEscalaQuery el cuerpo escala query
	 * @return el cuerpo escala bean
	 */
	CuerpoEscalaBean buscarCuerpoEscalaId2(CuerpoEscalaQuery cuerpoEscalaQuery);

	/**
	 * Obtener grupo by id grupo escala.
	 *
	 * @param cuerpoEscalaQuery el cuerpo escala query
	 * @return el grupo
	 */
	Grupo obtenerGrupoByIdGrupoEscala(CuerpoEscalaQuery cuerpoEscalaQuery);
	
}
