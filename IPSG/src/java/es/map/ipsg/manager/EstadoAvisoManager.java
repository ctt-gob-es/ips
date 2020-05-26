package es.map.ipsg.manager;

import java.util.ArrayList;

import es.map.ips.model.EstadoAvisoQuery;
import es.map.ipsg.bean.EstadoAvisoBean;



/**
 * El Interface EstadoAvisoManager.
 */
public interface EstadoAvisoManager {

	/**
	 * Buscar estado aviso combo.
	 *
	 * @param estadoAvisoQuery el estado aviso query
	 * @return el array list
	 */
	public ArrayList<EstadoAvisoBean> buscarEstadoAvisoCombo(EstadoAvisoQuery estadoAvisoQuery);


}
