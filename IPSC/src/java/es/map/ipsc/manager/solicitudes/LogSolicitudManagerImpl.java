package es.map.ipsc.manager.solicitudes;

import es.map.ips.dao.EstadoSolicitudDAO;
import es.map.ips.dao.LogSolicitudDAO;
import es.map.ips.dao.SolicitudComunDAO;

import es.map.ips.model.EstadoSolicitud;
import es.map.ips.model.EstadoSolicitudQuery;
import es.map.ips.model.LogSolicitud;
import es.map.ips.model.SolicitudComun;
import es.map.ips.model.SolicitudComunQuery;

import es.map.ipsc.Constantes;
import es.map.ipsc.bean.LogSolicitudBean;

/**
 * El Class LogSolicitudManagerImpl.
 */
public class LogSolicitudManagerImpl implements LogSolicitudManager {

	/** el log solicitud DAO. */
	private LogSolicitudDAO logSolicitudDAO;
	
	/** el solicitud comun DAO. */
	private SolicitudComunDAO solicitudComunDAO;
	
	/** el estado solicitud DAO. */
	private EstadoSolicitudDAO estadoSolicitudDAO;
	
	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.solicitudes.LogSolicitudManager#actualizarLogSolicitud(es.map.ipsc.bean.LogSolicitudBean)
	 */
	public Integer actualizarLogSolicitud(LogSolicitudBean logSolicitudBean) {
		
		LogSolicitud logSOlicitud = toLogSolicitud(logSolicitudBean);
		Integer id = logSolicitudDAO.insert(logSOlicitud);
		if(id != null){
			return id;
		}
		return null;
	}

	/**
	 * To log solicitud.
	 *
	 * @param logSolicitudBean el log solicitud bean
	 * @return el log solicitud
	 */
	private LogSolicitud toLogSolicitud(LogSolicitudBean logSolicitudBean) {
		LogSolicitud aux = new LogSolicitud();
		if(logSolicitudBean.getFecha() != null){
			aux.setFecha(logSolicitudBean.getFecha());
		}
		if(logSolicitudBean.getActor() != null){
			aux.setActor(logSolicitudBean.getActor());
		}
		if(logSolicitudBean.getDetalleOperacion() != null){
			aux.setDetalleOperacion(logSolicitudBean.getDetalleOperacion());
		}
		SolicitudComunQuery solicitudQuery = new SolicitudComunQuery();
		solicitudQuery.setIdSolicitud(Long.parseLong(logSolicitudBean.getIdSolicitud()));
		SolicitudComun solicitud = solicitudComunDAO.searchUnique(solicitudQuery);
		if(solicitud != null){
			aux.setSolicitudComun(solicitud);
		}
		if(logSolicitudBean.getTipoOperacion().charAt(0) != ' '){
			aux.setTipoOperacion(logSolicitudBean.getTipoOperacion().charAt(0));
		}
		if(logSolicitudBean.getIdEstadoActual() != null){
			EstadoSolicitudQuery estadoSolicitudQuery = new EstadoSolicitudQuery();
			estadoSolicitudQuery.setId(Integer.parseInt(logSolicitudBean.getIdEstadoActual()));
			EstadoSolicitud auxEstadoActual = estadoSolicitudDAO.searchUnique(estadoSolicitudQuery);
			if(auxEstadoActual != null){
				aux.setEstadoSolicitudByIdEstadoActual(auxEstadoActual);
			}
		}
		if(logSolicitudBean.getIdEstadoAnterior() != null){
			EstadoSolicitudQuery estadoSolicitudQueryAnterior = new EstadoSolicitudQuery();
			estadoSolicitudQueryAnterior.setId(Integer.parseInt(logSolicitudBean.getIdEstadoAnterior()));
			EstadoSolicitud auxEstadoAnterior = estadoSolicitudDAO.searchUnique(estadoSolicitudQueryAnterior);
			if(auxEstadoAnterior != null){
				aux.setEstadoSolicitudByIdEstadoAnterior(auxEstadoAnterior);
			}
		}
		
		aux.setTipoActor(Constantes.LOG_TIPO_ACTOR.charAt(0));
		
		if (logSolicitudBean.getTipoActor()!=null && logSolicitudBean.getTipoActor().equals(Constantes.TIPO_ACTOR_FUNCIONARIO_HABILITADO)) {
			aux.setTipoActor(Constantes.TIPO_ACTOR_FUNCIONARIO_HABILITADO);
		}
		
		return aux;
	}

	/**
	 * Obtiene el log solicitud DAO.
	 *
	 * @return el log solicitud DAO
	 */
	public LogSolicitudDAO getLogSolicitudDAO() {
		return logSolicitudDAO;
	}

	/**
	 * Establece el log solicitud DAO.
	 *
	 * @param logSolicitudDAO el nuevo log solicitud DAO
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

	/**
	 * Obtiene el estado solicitud DAO.
	 *
	 * @return el estado solicitud DAO
	 */
	public EstadoSolicitudDAO getEstadoSolicitudDAO() {
		return estadoSolicitudDAO;
	}

	/**
	 * Establece el estado solicitud DAO.
	 *
	 * @param estadoSolicitudDAO el nuevo estado solicitud DAO
	 */
	public void setEstadoSolicitudDAO(EstadoSolicitudDAO estadoSolicitudDAO) {
		this.estadoSolicitudDAO = estadoSolicitudDAO;
	}

	
}
