package es.map.ipsg.manager;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import es.map.ips.common.exception.ApplicationException;
import es.map.ips.common.model.SearchResult;
import es.map.ips.dao.LogIntermediacionAuxDAO;
import es.map.ips.dao.LogIntermediacionDAO;
import es.map.ips.model.LogIntermediacion;
import es.map.ips.model.LogIntermediacionAux;
import es.map.ips.model.LogIntermediacionQuery;
import es.map.ips.model.SolicitudComunQuery;
import es.map.ipsg.bean.LogIntermediacionBean;

/**
 * El Class LogIntermediacionManagerImpl.
 */
public class LogIntermediacionManagerImpl implements LogIntermediacionManager{

	/** el log intermediacion DAO. */
	private LogIntermediacionDAO logIntermediacionDAO;
	
	/** el log intermediacion DAO. */
	private LogIntermediacionAuxDAO logIntermediacionAuxDAO;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(LogIntermediacionManagerImpl.class);
	
	
	/**
	 * Buscar log.
	 *
	 * @param logIntermediacionQuery el log intermediacion query
	 * @return el search result
	 */
	private SearchResult<LogIntermediacion> buscarLog(LogIntermediacionQuery logIntermediacionQuery) {
		ApplicationException.assertNotNull(logIntermediacionQuery, "LogIntermediacionQuery no puede ser null");
	
		return logIntermediacionDAO.search(logIntermediacionQuery);
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.LogIntermediacionManager#buscarLogsAll(es.map.ips.model.LogIntermediacionQuery)
	 */
	public ArrayList<LogIntermediacionBean> buscarLogsAll(LogIntermediacionQuery logIntermediacionQuery){
		SearchResult<LogIntermediacion> peticion = buscarLog(logIntermediacionQuery);
		
		ArrayList<LogIntermediacionBean> arrLogsIntermediacion = new ArrayList<LogIntermediacionBean>();
		
		for(int i=0;i<peticion.getResults().size();i++){
			LogIntermediacionBean aux;
			aux = toLogIntermediacionBean(peticion.getResults().get(i));
			if(aux != null){
				arrLogsIntermediacion.add(aux);
			}
		}	
		return arrLogsIntermediacion;		
	}
	
	/**
	 * To log intermediacion bean.
	 *
	 * @param logIntermediacion el log intermediacion
	 * @return el log intermediacion bean
	 */
	private LogIntermediacionBean toLogIntermediacionBean(LogIntermediacion logIntermediacion){

		LogIntermediacionBean auxLogIntermediacion = new LogIntermediacionBean();
		
		if(logIntermediacion.getIdLogIntermediacion() != null){
			auxLogIntermediacion.setIdLogIntermediacion(logIntermediacion.getIdLogIntermediacion());
		}
		if(logIntermediacion.getIdPeticion() != null){
			auxLogIntermediacion.setIdPeticion(logIntermediacion.getIdPeticion());
		}
		
		auxLogIntermediacion.setDescripcion(logIntermediacion.getDescripcion());
		auxLogIntermediacion.setFecha(logIntermediacion.getFecha());
		auxLogIntermediacion.setResultado(logIntermediacion.getResultado());
		auxLogIntermediacion.setSolicitudComun(logIntermediacion.getSolicitudComun());
		
		
		return auxLogIntermediacion;
		
	}
	
	/**
	 * To log intermediacion.
	 *
	 * @param logIntermediacionBean el log intermediacion bean
	 * @return el log intermediacion
	 */
	private LogIntermediacion toLogIntermediacion(LogIntermediacionBean logIntermediacionBean){

		LogIntermediacion logIntermediacion = new LogIntermediacion();
		
		if(logIntermediacionBean.getIdLogIntermediacion() != null){
			logIntermediacion.setIdLogIntermediacion(logIntermediacion.getIdLogIntermediacion());
		}
		if(logIntermediacionBean.getIdPeticion() != null){
			logIntermediacion.setIdPeticion(logIntermediacionBean.getIdPeticion());
		}
		
		logIntermediacion.setDescripcion(logIntermediacionBean.getDescripcion());
		logIntermediacion.setFecha(logIntermediacionBean.getFecha());
		logIntermediacion.setResultado(logIntermediacionBean.getResultado());
		logIntermediacion.setSolicitudComun(logIntermediacionBean.getSolicitudComun());
		
		return logIntermediacion;
		
	}
	
	/**
	 * To log intermediacion.
	 *
	 * @param logIntermediacionBean el log intermediacion bean
	 * @return el log intermediacion
	 */
	private LogIntermediacionAux toLogIntermediacionAux(LogIntermediacionBean logIntermediacionBean){

		LogIntermediacionAux logIntermediacionAux = new LogIntermediacionAux();
		
		if(logIntermediacionBean.getIdLogIntermediacion() != null){
			logIntermediacionAux.setIdLogIntermediacionAux(logIntermediacionAux.getIdLogIntermediacionAux());
		}
		if(logIntermediacionBean.getIdPeticion() != null){
			logIntermediacionAux.setIdPeticion(logIntermediacionBean.getIdPeticion());
		}
		
		logIntermediacionAux.setDescripcion(logIntermediacionBean.getDescripcion());
		logIntermediacionAux.setFecha(logIntermediacionBean.getFecha());
		logIntermediacionAux.setResultado(logIntermediacionBean.getResultado());
		logIntermediacionAux.setSolicitudComunAuxiliar(logIntermediacionBean.getSolicitudComunAuxiliar());
		
		return logIntermediacionAux;
		
	}	
	
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.LogIntermediacionManager#actualizarLogIntermediacion(es.map.ipsg.bean.LogIntermediacionBean, int)
	 */
	public void actualizarLogIntermediacion(LogIntermediacionBean logIntermediacionAux, int idLog){
		
		
		LogIntermediacion logIntermediacion = toLogIntermediacion(logIntermediacionAux);
		logIntermediacion.setIdLogIntermediacion(idLog);
		logIntermediacionDAO.update(logIntermediacion);
	}
	
	/**
	 *  Método que elimina un regitro dado un id de solicitud.
	 *
	 * @param idSolicitud el id solicitud
	 */
	public void borrarSolicitudLogIntermediacion(Long idSolicitud){
		try{
			LogIntermediacionQuery logIntermediacionQuery = new LogIntermediacionQuery();
			SolicitudComunQuery solicitudComunQuery = new SolicitudComunQuery();
			solicitudComunQuery.setIdSolicitud(idSolicitud);
			logIntermediacionQuery.setSolicitudComun(solicitudComunQuery);
			// Buscamos todos los registros que contienen ese id de Solicitud
			SearchResult<LogIntermediacion> solicitudLogIntermeciacion = logIntermediacionDAO.search(logIntermediacionQuery);
			if(solicitudLogIntermeciacion != null && solicitudLogIntermeciacion.size()>0){
				for (int i = 0; i < solicitudLogIntermeciacion.size(); i++) {
					// Eliminamos la solicitud
					Integer idLogIntermediacion = solicitudLogIntermeciacion.getResults().get(i).getIdLogIntermediacion();
					logIntermediacionDAO.delete(idLogIntermediacion);
				}
			}
		}catch (Exception e){
			logger.error("No se puede eliminar el registro de log_intermediacion con ID_SOLICITUD=" + idSolicitud);
			logger.error("Error: ", e);
		}
	}
	
	
	
	/**
	 * Obtiene el log intermediacion DAO.
	 *
	 * @return el log intermediacion DAO
	 */
	public LogIntermediacionDAO getLogIntermediacionDAO() {
		return logIntermediacionDAO;
	}

	/**
	 * Establece el log intermediacion DAO.
	 *
	 * @param logIntermediacionDAO el nuevo log intermediacion DAO
	 */
	public void setLogIntermediacionDAO(LogIntermediacionDAO logIntermediacionDAO) {
		this.logIntermediacionDAO = logIntermediacionDAO;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.LogIntermediacionManager#guardarLogIntermediacion(es.map.ipsg.bean.LogIntermediacionBean)
	 */
	//@Override
	public void guardarLogIntermediacion(LogIntermediacionBean logIntermediacionBean) {
		
		LogIntermediacion logIntermediacion = toLogIntermediacion(logIntermediacionBean);
		logIntermediacionDAO.insert(logIntermediacion);

		
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.LogIntermediacionManager#guardarLogIntermediacion(es.map.ipsg.bean.LogIntermediacionBean)
	 */
	//@Override
	public void guardarLogIntermediacionAux(LogIntermediacionBean logIntermediacionAuxBean) {
		
		LogIntermediacionAux logIntermediacionAux = toLogIntermediacionAux(logIntermediacionAuxBean);
		logIntermediacionAuxDAO.insert(logIntermediacionAux);

		
	}

	public LogIntermediacionAuxDAO getLogIntermediacionAuxDAO() {
		return logIntermediacionAuxDAO;
	}

	public void setLogIntermediacionAuxDAO(LogIntermediacionAuxDAO logIntermediacionAuxDAO) {
		this.logIntermediacionAuxDAO = logIntermediacionAuxDAO;
	}
	
	
}
