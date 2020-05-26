package es.map.ipsg.manager;



import org.apache.log4j.Logger;

import es.map.ips.common.model.SearchResult;
import es.map.ips.dao.SolicitudCorreoDAO;
import es.map.ips.model.SolicitudComunQuery;
import es.map.ips.model.SolicitudCorreo;
import es.map.ips.model.SolicitudCorreoId;
import es.map.ips.model.SolicitudCorreoIdQuery;
import es.map.ips.model.SolicitudCorreoQuery;

/**
 * El Class SolicitudCorreoManagerImpl.
 */
public class SolicitudCorreoManagerImpl implements SolicitudCorreoManager {

	/** el solicitud correo DAO. */
	//private SolicitudCorreoCustomDAO solicitudCorreoCustomDAO;
	private SolicitudCorreoDAO solicitudCorreoDAO;
	
	/** el solicitud comun query. */
	private SolicitudComunQuery solicitudComunQuery;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(SolicitudCorreoManagerImpl.class);	


	/**
	 *  Método que elimina una solicitud dado un id de solicitud.
	 *
	 * @param idSolicitud el id solicitud
	 */
	public void borrarSolicitudCorreo(Long idSolicitud){
		try{
			
			SolicitudCorreoIdQuery solicitudCorreoIdQuery = new SolicitudCorreoIdQuery();
			solicitudCorreoIdQuery.setIdSolicitud(idSolicitud);
			SolicitudCorreoQuery solicitudCorreoQuery = new SolicitudCorreoQuery();
			solicitudCorreoQuery.setId(solicitudCorreoIdQuery);		
			
			// Buscamos todas las solicitudes que contienen ese id de Solicitud
			SearchResult<SolicitudCorreo> solicitudCorreo = solicitudCorreoDAO.search(solicitudCorreoQuery);
			
			SolicitudCorreoId solicitudCorreoId = new  SolicitudCorreoId();
			solicitudCorreoId.setIdSolicitud(idSolicitud);
			
			if(null!=solicitudCorreo && solicitudCorreo.size()>0){
				for (int i = 0; i < solicitudCorreo.size(); i++) {
					// Eliminamos la solicitud
					solicitudCorreoId.setIdCorreoEelectronico(solicitudCorreo.getResults().get(i).getId().getIdCorreoEelectronico());
					solicitudCorreoDAO.delete(solicitudCorreoId);
				}
			}
			
			
		}catch (Exception e){
			logger.error("No se puede eliminar el registro de solicitud_correo con ID_SOLICITUD=" + idSolicitud) ;
			logger.error("Error:" + e);
		}
	}

	/**
	 * Obtiene el solicitud correo DAO.
	 *
	 * @return el solicitud correo DAO
	 */
	public SolicitudCorreoDAO getSolicitudCorreoDAO() {
		return solicitudCorreoDAO;
	}


	/**
	 * Establece el solicitud correo DAO.
	 *
	 * @param solicitudCorreoDAO el nuevo solicitud correo DAO
	 */
	public void setSolicitudCorreoDAO(SolicitudCorreoDAO solicitudCorreoDAO) {
		this.solicitudCorreoDAO = solicitudCorreoDAO;
	}

}
