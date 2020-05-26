package es.map.ipsc.manager.logs;

import java.util.Date;

import org.apache.log4j.Logger;

import es.map.ips.dao.LogServicioDAO;
import es.map.ips.model.LogServicio;
import es.map.ips.model.TipoServicio;
import es.map.ips.model.TipoServicioQuery;
import es.map.ipsc.bean.LogServicioBean;

/**
 * El Class LogServiciosManagerImpl.
 */
public class LogServiciosManagerImpl implements LogServiciosManager {

	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(LogServiciosManagerImpl.class);
	
	/** el tipo servicio manager. */
	private TipoServicioManager tipoServicioManager;
	
	/** el log servicio DAO. */
	private LogServicioDAO logServicioDAO;
	
	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.logs.LogServiciosManager#insertarLog(es.map.ipsc.bean.LogServicioBean)
	 */
	public int insertarLog(LogServicioBean logServicioBean) {
		LogServicio logServicio;
		logServicio = toLogServicio(logServicioBean);
		int result = 0;
		if(logServicio != null){
			result = logServicioDAO.insert(logServicio);
			logger.info("Insertado en la tabla logServicio");
		}
		return result;
	}

	/**
	 * To log servicio.
	 *
	 * @param logServicioBean el log servicio bean
	 * @return el log servicio
	 */
	private LogServicio toLogServicio(LogServicioBean logServicioBean) {
		LogServicio aux = new LogServicio();
		if(logServicioBean.getFecha() != null){
			aux.setFecha(logServicioBean.getFecha());
		}else{
			Date fechaHoy = new Date();
			aux.setFecha(fechaHoy);
		}
		TipoServicio tipoServicio;
		TipoServicioQuery tipoServicioQuery = new TipoServicioQuery();
		tipoServicioQuery.setId(Integer.parseInt(logServicioBean.getIdTipoServicio()));
		tipoServicio = tipoServicioManager.buscarTipoServicioUnique(tipoServicioQuery);
		if(tipoServicio != null){
			aux.setTipoServicio(tipoServicio);
		}else{
			return null;
		}
		if(logServicioBean.getResultado() != null){
			aux.setResultado(logServicioBean.getResultado());
		}
		if(logServicioBean.getTipoError() != null){
			aux.setTipoError(logServicioBean.getTipoError().charAt(0));
		}
		if(logServicioBean.getTiempoRespuesta() != null){
			aux.setTiempoRespuesta(logServicioBean.getTiempoRespuesta());
		}
		if(logServicioBean.getPrueba() != null){
			aux.setPruebaServicio(logServicioBean.getPrueba().charAt(0));
		}else{
			return null;
		}
		
		aux.setCodigoError(logServicioBean.getCodError());
		aux.setDescripcionError(logServicioBean.getDesError());
		
		return aux;
	}

	/**
	 * Obtiene el tipo servicio manager.
	 *
	 * @return el tipo servicio manager
	 */
	public TipoServicioManager getTipoServicioManager() {
		return tipoServicioManager;
	}

	/**
	 * Establece el tipo servicio manager.
	 *
	 * @param tipoServicioManager el nuevo tipo servicio manager
	 */
	public void setTipoServicioManager(TipoServicioManager tipoServicioManager) {
		this.tipoServicioManager = tipoServicioManager;
	}

	/**
	 * Obtiene el log servicio DAO.
	 *
	 * @return el log servicio DAO
	 */
	public LogServicioDAO getLogServicioDAO() {
		return logServicioDAO;
	}

	/**
	 * Establece el log servicio DAO.
	 *
	 * @param logServicioDAO el nuevo log servicio DAO
	 */
	public void setLogServicioDAO(LogServicioDAO logServicioDAO) {
		this.logServicioDAO = logServicioDAO;
	}
	
}
