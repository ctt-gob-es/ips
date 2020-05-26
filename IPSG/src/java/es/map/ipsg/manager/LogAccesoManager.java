package es.map.ipsg.manager;

import java.util.ArrayList;

import es.map.ips.model.LogAccesoQuery;
import es.map.ipsg.bean.LogAccesoBean;

/**
 * El Interface LogAccesoManager.
 */
public interface LogAccesoManager {
	
	/**
	 * Buscar accesos all.
	 *
	 * @param logAccesoQuery el log acceso query
	 * @return el array list
	 */
	public ArrayList<LogAccesoBean> buscarAccesosAll(LogAccesoQuery logAccesoQuery);
	
	/**
	 * Guardar acceso.
	 *
	 * @param loginBean el login bean
	 * @return el integer
	 */
	public Integer guardarAcceso(LogAccesoBean loginBean);
	
	/**
	 * Actualizar log acceso.
	 *
	 * @param loginBean el login bean
	 */
	public void actualizarLogAcceso(LogAccesoBean loginBean);

}
