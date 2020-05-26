package es.map.ipsg.manager;


import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Logger;

import es.map.ips.common.exception.ApplicationException;
import es.map.ips.common.model.SearchResult;
import es.map.ips.dao.LogSolicitudDAO;
import es.map.ips.dao.SolicitudComunDAO;
import es.map.ips.model.EstadoSolicitud;
import es.map.ips.model.LogSolicitud;
import es.map.ips.model.LogSolicitudQuery;
import es.map.ips.model.SolicitudComun;
import es.map.ips.model.SolicitudComunQuery;
import es.map.ipsg.bean.LogSolicitudBean;


/**
 * El Class LogSolicitudManagerImpl.
 */
public class LogSolicitudManagerImpl implements LogSolicitudManager {

	/** el log solicitud DAO. */
	//Variables
	private LogSolicitudDAO logSolicitudDAO;
	
	/** el solicitud comun DAO. */
	private SolicitudComunDAO solicitudComunDAO;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(LogSolicitudManagerImpl.class);
	
	
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.LogSolicitudManager#generarRegistroLogSolicitud(es.map.ipsg.bean.LogSolicitudBean)
	 */
	public void generarRegistroLogSolicitud (LogSolicitudBean logSolicitudBean){
		
		LogSolicitud registroLogSolicitud = null;
		Date date = new Date();
		registroLogSolicitud = toLogSolicitud(logSolicitudBean);
		registroLogSolicitud.setFecha(date);
		
		logSolicitudDAO.insert(registroLogSolicitud);
	}
	
	/**
	 * To log solicitud.
	 *
	 * @param logSolicitudBean el log solicitud bean
	 * @return el log solicitud
	 */
	public LogSolicitud toLogSolicitud(LogSolicitudBean logSolicitudBean){
		LogSolicitud logSolicitud = new LogSolicitud();
		
		if(logSolicitud.getId()!=null){
			logSolicitud.setId(logSolicitud.getId());
		}
		
		logSolicitud.setTipoOperacion(logSolicitudBean.getTipoOperacion());
		logSolicitud.setActor(logSolicitudBean.getActor());
		logSolicitud.setDetalleOperacion(logSolicitudBean.getDetalleOperacion());
		
		SolicitudComun solicitud = new SolicitudComun();
		solicitud.setIdSolicitud(Long.valueOf(logSolicitudBean.getIdSolicitud()));
		
		if(logSolicitudBean.getId_estado_actual()!=null){
			EstadoSolicitud estadoSolicitudActual = new EstadoSolicitud();
			estadoSolicitudActual.setId(logSolicitudBean.getId_estado_actual());
			
			solicitud.setEstadoSolicitud(estadoSolicitudActual);
			logSolicitud.setEstadoSolicitudByIdEstadoActual(estadoSolicitudActual);
		}
		
		if(logSolicitudBean.getId_estado_anterior()!=null){
			//Añadimos los estados de solicitudes
			EstadoSolicitud estadoSolicitudAnterior=new EstadoSolicitud();
			estadoSolicitudAnterior.setId(logSolicitudBean.getId_estado_anterior());
			
			logSolicitud.setEstadoSolicitudByIdEstadoAnterior(estadoSolicitudAnterior);
		}
		
		logSolicitud.setSolicitudComun(solicitud);
		logSolicitud.setActor(logSolicitudBean.getActor());
		logSolicitud.setTipoActor(logSolicitudBean.getTipoActor());		
		
		return logSolicitud;
	}
	
	/**
	 * To log solicitud bean.
	 *
	 * @param logSolicitud el log solicitud
	 * @param numTotal el num total
	 * @return el log solicitud bean
	 */
	public LogSolicitudBean toLogSolicitudBean (LogSolicitud logSolicitud,int numTotal){
		LogSolicitudBean logSolicitudbean;
		logSolicitudbean=toLogSolicitudBean(logSolicitud);
		logSolicitudbean.setNumTotal(numTotal);
		return logSolicitudbean;
	}
	
	/**
	 * To log solicitud bean.
	 *
	 * @param logSolicitud el log solicitud
	 * @return el log solicitud bean
	 */
	public LogSolicitudBean toLogSolicitudBean (LogSolicitud logSolicitud){
		LogSolicitudBean logSolicitudbean = new LogSolicitudBean();
		logSolicitudbean.setId(logSolicitud.getId());
		if(logSolicitud.getSolicitudComun()!=null){
			logSolicitudbean.setNumeroJustificante(logSolicitud.getSolicitudComun().getNumeroSolicitud());
			logSolicitudbean.setDniCiudadano(logSolicitud.getSolicitudComun().getNif());
			logSolicitudbean.setIdSolicitud(logSolicitud.getSolicitudComun().getIdSolicitud());
			if(logSolicitud.getSolicitudComun().getConvocatoria()!=null){
				logSolicitudbean.setIdConvocatoria(logSolicitud.getSolicitudComun().getConvocatoria().getId());
				logSolicitudbean.setDesCentroGestor(logSolicitud.getSolicitudComun().getConvocatoria().getCuerpoEscala().getCentroGestor().getDescripcion());
				logSolicitudbean.setDesCuerpoEscala(logSolicitud.getSolicitudComun().getConvocatoria().getCuerpoEscala().getDescripcion());
			}
		}
		logSolicitudbean.setActor(logSolicitud.getActor());
		logSolicitudbean.setTipoActor(logSolicitud.getTipoActor());
		logSolicitudbean.setDetalleOperacion(logSolicitud.getDetalleOperacion());
		logSolicitudbean.setDniCiudadano(logSolicitud.getSolicitudComun().getNif());
		logSolicitudbean.setIdConvocatoria(logSolicitud.getSolicitudComun().getConvocatoria().getId());		
		if(logSolicitud.getEstadoSolicitudByIdEstadoActual()!=null){		
			logSolicitudbean.setEstadoActual(logSolicitud.getEstadoSolicitudByIdEstadoActual().getDescripcion());
		}
		if(logSolicitud.getEstadoSolicitudByIdEstadoAnterior()!=null){
			logSolicitudbean.setEstadoAnterior(logSolicitud.getEstadoSolicitudByIdEstadoAnterior().getDescripcion());
		}
		logSolicitudbean.setFecha(logSolicitud.getFecha());
		return logSolicitudbean;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.LogSolicitudManager#obtenerLogSolicitud(java.lang.Integer)
	 */
	public LogSolicitudBean obtenerLogSolicitud(Integer id){
		LogSolicitud logSolicitud = logSolicitudDAO.get(id);
		return toLogSolicitudBean(logSolicitud);
	}

	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.LogSolicitudManager#buscarLogSolicitudAll(es.map.ips.model.LogSolicitudQuery)
	 */
	public ArrayList<LogSolicitudBean> buscarLogSolicitudAll(LogSolicitudQuery logSolicitudQuery) {
		ArrayList<LogSolicitudBean> arrLogSolicitud; 
		SearchResult<LogSolicitud> logSolicitud =logSolicitudDAO.search(logSolicitudQuery);
		int numTotal ;
		if(logSolicitud.getNumResults()==null ){
				numTotal=0;	
		}else{
			numTotal=logSolicitud.getNumResults();
		}
		arrLogSolicitud = new ArrayList<LogSolicitudBean>();
		for(int i=0;i<logSolicitud.getResults().size();i++){
			LogSolicitudBean aux;
			aux = toLogSolicitudBean(logSolicitud.getResults().get(i),numTotal);
			if(aux != null){
				arrLogSolicitud.add(aux);
			}
		}	
		return arrLogSolicitud;	
	}
	
	/**
	 * Buscar log solicitud.
	 *
	 * @param logSolicitudQuery el log solicitud query
	 * @return el search result
	 */
	private SearchResult<LogSolicitud> buscarLogSolicitud (LogSolicitudQuery logSolicitudQuery) {
		ApplicationException.assertNotNull(logSolicitudQuery, "logSolicitudQuery no puede ser null");
	
		return logSolicitudDAO.search(logSolicitudQuery);
	}

	/**
	 * Bean to log solicitud.
	 *
	 * @param logSolicitudBean el log solicitud bean
	 * @return el log solicitud
	 */
	private LogSolicitud beanToLogSolicitud (LogSolicitudBean logSolicitudBean) {
		
		logger.info("beanToLogSolicitud - start");
		LogSolicitud logSolicitud = new LogSolicitud();
		

		logSolicitud.setFecha(logSolicitudBean.getFecha());
		logSolicitud.setTipoOperacion(logSolicitudBean.getTipoOperacion());
		logSolicitud.setActor(logSolicitudBean.getActor());		
		logSolicitud.setDetalleOperacion(logSolicitudBean.getDetalleOperacion());
		
		EstadoSolicitud estadoAnterior = new EstadoSolicitud();
		estadoAnterior.setId(Integer.parseInt(logSolicitudBean.getIdEstadoAnterior()));
		logSolicitud.setEstadoSolicitudByIdEstadoAnterior(estadoAnterior );
		
		EstadoSolicitud estadoActual = new EstadoSolicitud();
		estadoActual.setId(Integer.parseInt(logSolicitudBean.getIdEstadoActual()));
		logSolicitud.setEstadoSolicitudByIdEstadoActual(estadoActual );
		
		logSolicitud.setTipoActor(logSolicitudBean.getTipoActor());
		
		//Obtener los datos de Solicitud
		Long idSolicitud = Long.valueOf(logSolicitudBean.getIdSolicitud());
		SolicitudComun solicitud = solicitudComunDAO.get(idSolicitud);
		logSolicitud.setSolicitudComun(solicitud);
		
		logger.info("beanToLogSolicitud - end");
		return logSolicitud;
	}
	
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.LogSolicitudManager#insertarLogSolicitud(es.map.ipsg.bean.LogSolicitudBean)
	 */
	public Integer insertarLogSolicitud (LogSolicitudBean logSolicitudBean) {
		logger.info("insertarLogSolicitud - start");
		
		LogSolicitud logSolicitud;
		logSolicitud = this.beanToLogSolicitud(logSolicitudBean);
		
		Integer idLogGenerico = logSolicitudDAO.insert(logSolicitud);
		
		logger.info("insertarLogSolicitud - end");
		return idLogGenerico;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.LogSolicitudManager#borrarSolicitudLog(java.lang.Long)
	 */
	public void borrarSolicitudLog(Long idSolicitud){
		try{
			LogSolicitudQuery logQuery = new LogSolicitudQuery();
			SolicitudComunQuery solicitudComunQuery = new SolicitudComunQuery();
			solicitudComunQuery.setIdSolicitud(idSolicitud);
			logQuery.setSolicitudComun(solicitudComunQuery);
			// Buscamos todos los registros que contienen ese id de Solicitud
			SearchResult<LogSolicitud> solicitudLog = logSolicitudDAO.search(logQuery);
			if(solicitudLog != null && solicitudLog.size()>0){
				for (int i = 0; i < solicitudLog.size(); i++) {
					// Eliminamos la solicitud
					Integer idLogSolicitud = solicitudLog.getResults().get(i).getId();
					logSolicitudDAO.delete(idLogSolicitud);
				}
			}
		}catch (Exception e){
			logger.error("No se puede eliminar el registro de log_solicitud con ID_SOLICITUD=" + idSolicitud);
			logger.error("Error: ", e);
		}
	}
	
	
	/**
	 * Obtiene el logger.
	 *
	 * @return el logger
	 */
	public static Logger getLogger() {
		return logger;
	}

	/**
	 * Obtiene el log solicitud DAO.
	 *
	 * @return the logSolicitudDAO
	 */
	public LogSolicitudDAO getLogSolicitudDAO() {
		return logSolicitudDAO;
	}

	/**
	 * Establece el log solicitud DAO.
	 *
	 * @param logSolicitudDAO the logSolicitudDAO to set
	 */
	public void setLogSolicitudDAO(LogSolicitudDAO logSolicitudDAO) {
		this.logSolicitudDAO = logSolicitudDAO;
	}

	/**
	 * Obtiene el solicitud comun DAO.
	 *
	 * @return el solicitud comun DAO
	 */
	public SolicitudComunDAO getSolicitudComunDAO() {
		return solicitudComunDAO;
	}

	/**
	 * Establece el solicitud comun DAO.
	 *
	 * @param solicitudComunDAO el nuevo solicitud comun DAO
	 */
	public void setSolicitudComunDAO(SolicitudComunDAO solicitudComunDAO) {
		this.solicitudComunDAO = solicitudComunDAO;
	}
}	
