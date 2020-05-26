package es.map.ipsg.manager;

import java.util.ArrayList;

import es.map.ips.model.TipoAccesoQuery;
import es.map.ipsg.bean.TipoAccesoBean;


/**
 * El Interface TipoAccesoManager.
 */
public interface TipoAccesoManager {

	/**
	 * Buscar tipo acceso combo.
	 *
	 * @param tipoAccesoQuery el tipo acceso query
	 * @return el array list
	 */
	public ArrayList<TipoAccesoBean> buscarTipoAccesoCombo(TipoAccesoQuery tipoAccesoQuery);

}
