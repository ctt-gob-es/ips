package es.map.ipsg.manager;

import java.util.ArrayList;

import es.map.ips.model.LogConvocatoriaQuery;
import es.map.ipsg.bean.LogConvocatoriaBean;


/**
 * El Interface LogConvocatoriaManager.
 */
public interface LogConvocatoriaManager {
	
	/**
	 * Buscar convocatoria all.
	 *
	 * @param logConvocatoriaQuery el log convocatoria query
	 * @return el array list
	 */
	public ArrayList<LogConvocatoriaBean> buscarConvocatoriaAll(LogConvocatoriaQuery logConvocatoriaQuery);
	
	/**
	 * Guardar log convocatoria.
	 *
	 * @param logConvocatoriaBean el log convocatoria bean
	 * @return el integer
	 */
	public Integer guardarLogConvocatoria(LogConvocatoriaBean logConvocatoriaBean);
}
