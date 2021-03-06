package es.map.ipsg.manager;

import java.util.ArrayList;

import es.map.ips.model.BatchIntermediacionAuxQuery;
import es.map.ipsg.bean.BatchIntermediacionBean;

/**
 * El Interface BatchIntermediacionAuxManager.
 */
public interface BatchIntermediacionAuxManager {

	/**
	 * Buscar peticiones all.
	 *
	 * @param batchIntermediacionQuery el batch intermediacion query
	 * @return el array list
	 */
	public ArrayList<BatchIntermediacionBean> buscarPeticionesAll(BatchIntermediacionAuxQuery batchIntermediacionQuery);
	
	/**
	 * Actualizar intermediacion.
	 *
	 * @param batchIntermediacion el batch intermediacion
	 * @param i el i
	 */
	public void actualizarIntermediacion(BatchIntermediacionBean batchIntermediacion, int i);
	
	/**
	 * Guardar batch intermediacion.
	 *
	 * @param batchIntermediacionBean el batch intermediacion bean
	 */
	public void guardarBatchIntermediacion(BatchIntermediacionBean batchIntermediacionBean);
}
