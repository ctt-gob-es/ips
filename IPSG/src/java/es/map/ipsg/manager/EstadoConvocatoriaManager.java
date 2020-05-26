package es.map.ipsg.manager;

import java.util.ArrayList;

import es.map.ips.model.EstadoConvocatoriaQuery;
import es.map.ipsg.bean.EstadoConvocatoriaBean;



/**
 * El Interface EstadoConvocatoriaManager.
 */
public interface EstadoConvocatoriaManager {

	/**
	 * Buscar estado convocatoria combo.
	 *
	 * @param estadoQuery el estado query
	 * @return el array list
	 */
	public ArrayList<EstadoConvocatoriaBean> buscarEstadoConvocatoriaCombo(EstadoConvocatoriaQuery estadoQuery);


}
