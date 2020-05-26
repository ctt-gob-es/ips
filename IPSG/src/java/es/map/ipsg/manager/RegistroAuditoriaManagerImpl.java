package es.map.ipsg.manager;

import org.apache.log4j.Logger;

import es.map.ips.common.model.SearchResult;
import es.map.ips.dao.RegistroAuditoriaDAO;
import es.map.ips.model.RegistroAuditoria;
import es.map.ips.model.RegistroAuditoriaQuery;
import es.map.ips.model.SolicitudComunQuery;

/**
 * El Class RegistroAuditoriaManagerImpl.
 */
public class RegistroAuditoriaManagerImpl implements RegistroAuditoriaManager {
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(RegistroAuditoriaManagerImpl.class);
	
	/** el registro auditoria DAO. */
	private RegistroAuditoriaDAO registroAuditoriaDAO;

	/**
	 *  Método que elimina un regitro dado un id de solicitud.
	 *
	 * @param idSolicitud el id solicitud
	 */
	public void borrarSolicitudAuditoria(Long idSolicitud){
		try{
			RegistroAuditoriaQuery registroAuditoriaQuery = new RegistroAuditoriaQuery();
			SolicitudComunQuery solicitudComunQuery = new SolicitudComunQuery();
			solicitudComunQuery.setIdSolicitud(idSolicitud);
			registroAuditoriaQuery.setSolicitudComun(solicitudComunQuery);
			// Buscamos todos los registros que contienen ese id de Solicitud
			SearchResult<RegistroAuditoria> registroAuditoria = registroAuditoriaDAO.search(registroAuditoriaQuery);
			if(registroAuditoria != null && registroAuditoria.size()>0){
				for (int i = 0; i < registroAuditoria.size(); i++) {
					// Eliminamos la solicitud
					Long idPagoSolicitud = registroAuditoria.getResults().get(i).getIdRegistroAud();
					registroAuditoriaDAO.delete(idPagoSolicitud);
				}
			}
		}catch (Exception e){
			logger.error("No se puede eliminar el registro de registro_auditoria con ID_SOLICITUD=" + idSolicitud);
			logger.error("Error: " + e);
		}
	}

	/**
	 * Obtiene el registro auditoria DAO.
	 *
	 * @return el registro auditoria DAO
	 */
	public RegistroAuditoriaDAO getRegistroAuditoriaDAO() {
		return registroAuditoriaDAO;
	}

	/**
	 * Establece el registro auditoria DAO.
	 *
	 * @param registroAuditoriaDAO el nuevo registro auditoria DAO
	 */
	public void setRegistroAuditoriaDAO(RegistroAuditoriaDAO registroAuditoriaDAO) {
		this.registroAuditoriaDAO = registroAuditoriaDAO;
	}
}
