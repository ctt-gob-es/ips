package es.map.ipsg.manager;

import java.util.ArrayList;

import es.map.ips.model.LogGenerico;
import es.map.ips.model.LogGenericoQuery;
import es.map.ipsg.bean.LogGenericoBean;




/**
 * El Interface LogGenericoManager.
 *
 * @author amartinl
 */
public interface LogGenericoManager {

	/**
	 * Generar registro log.
	 *
	 * @param logGenericoBean el log generico bean
	 */
	public void generarRegistroLog(LogGenericoBean logGenericoBean);
	
	/**
	 * Guardar log generico.
	 *
	 * @param logGenerico el log generico
	 * @return el integer
	 */
	public Integer guardarLogGenerico(LogGenerico logGenerico);
	
	/**
	 * Buscar log generico cierre reapertura.
	 *
	 * @param logGenericoQuery el log generico query
	 * @return el array list
	 */
	public ArrayList<LogGenericoBean> buscarLogGenericoCierreReapertura(LogGenericoQuery logGenericoQuery);
}
