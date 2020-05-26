package es.map.ipsg.manager;

import java.util.Date;

/**
 * El Interface BatchMonitorManager.
 */
public interface BatchMonitorManager {

	/**
	 * Buscar resultado.
	 *
	 * @return el date
	 */
	public Date buscarResultado();
	
	/**
	 * Guardar resultado.
	 *
	 * @param fecha el fecha
	 * @param resultado el resultado
	 * @param desResultado el des resultado
	 */
	public void guardarResultado(Date fecha, String resultado, String desResultado);
}
