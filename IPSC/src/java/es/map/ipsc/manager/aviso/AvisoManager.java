package es.map.ipsc.manager.aviso;

import java.util.ArrayList;

import es.map.ips.model.AvisoQuery;
import es.map.ipsc.bean.AvisoBean;


/**
 * El Interface AvisoManager.
 */
public interface AvisoManager {

	/**
	 * Buscar avisos.
	 *
	 * @param avisoQuery el aviso query
	 * @return el array list
	 */
	ArrayList<AvisoBean> buscarAvisos(AvisoQuery avisoQuery);

	
}
