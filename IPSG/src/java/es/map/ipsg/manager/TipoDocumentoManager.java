package es.map.ipsg.manager;

import java.util.ArrayList;

import es.map.ips.model.TipoDocumentoQuery;
import es.map.ipsg.bean.TipoDocumentoBean;

/**
 * El Interface TipoDocumentoManager.
 */
public interface TipoDocumentoManager {

	/**
	 * Buscar tipo documento combo.
	 *
	 * @param tipoDocumentoQuery el tipo documento query
	 * @return el array list
	 */
	public ArrayList<TipoDocumentoBean> buscarTipoDocumentoCombo(TipoDocumentoQuery tipoDocumentoQuery);

}
