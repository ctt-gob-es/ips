package es.map.ipsg.manager;

import java.util.List;

import es.map.ips.model.LogServicio;
import es.map.ips.model.LogServicioQuery;
import es.map.ipsg.bean.ConsultarServiciosExternosBean;
import es.map.ipsg.bean.LogServicioBean;




/**
 * El Interface LogServicioManager.
 *
 * @author amartinl
 */
public interface LogServicioManager {
	
	/**
	 * Generar registro log.
	 *
	 * @param LogServicioBean el log servicio bean
	 */
	public void generarRegistroLog(LogServicioBean LogServicioBean);
	
	/**
	 * Guardar log servicio bean.
	 *
	 * @param logServicioBean el log servicio bean
	 * @return el integer
	 */
	public Integer guardarLogServicioBean (LogServicioBean logServicioBean);
	
	/**
	 * Guardar log servicio.
	 *
	 * @param logServicioBean el log servicio bean
	 */
	public void guardarLogServicio(LogServicioBean logServicioBean);
	
	/**
	 * Obtener log errores.
	 *
	 * @param idLogError el id log error
	 * @return el log servicio bean
	 */
	public LogServicioBean obtenerLogErrores(Integer idLogError);
	
	/**
	 * Buscar log errores all.
	 *
	 * @param logServicioQuery el log servicio query
	 * @return el list
	 */
	public List<LogServicioBean> buscarLogErroresAll(LogServicioQuery logServicioQuery);
	
	/**
	 * Consultar estadisticas log servicio.
	 *
	 * @param consultaBean el consulta bean
	 * @return el list
	 */
	public List<LogServicioBean> consultarEstadisticasLogServicio(ConsultarServiciosExternosBean consultaBean);
}
