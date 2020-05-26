package es.map.ipsc.manager.solicitudes;

import java.util.ArrayList;

import es.map.ips.common.model.OrderType;
import es.map.ips.common.model.SearchResult;
import es.map.ips.dao.EstadoSolicitudDAO;
import es.map.ips.model.EstadoSolicitud;
import es.map.ips.model.EstadoSolicitudQuery;
import es.map.ipsc.Constantes;
import es.map.ipsc.bean.EstadoSolicitudBean;

/**
 * El Class EstadoSolicitudManagerImpl.
 */
public class EstadoSolicitudManagerImpl implements EstadoSolicitudManager {
	
	/** el estado solicitud DAO. */
	private EstadoSolicitudDAO estadoSolicitudDAO;
	
	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.solicitudes.EstadoSolicitudManager#buscarEstadoSolicitudCombo()
	 */
	public ArrayList<EstadoSolicitudBean> buscarEstadoSolicitudCombo() {
		EstadoSolicitudQuery estadoSolicitudQuery = new EstadoSolicitudQuery();
		estadoSolicitudQuery.setTipoSolicitud(Constantes.SOLICITUD_ESTADO_TELEMATICO);
		estadoSolicitudQuery.addIdIn(Constantes.SOLICITUD_ID_NOPAGADO);
		estadoSolicitudQuery.addIdIn(Constantes.SOLICITUD_ID_NOREGISTRADO);
		estadoSolicitudQuery.addIdIn(Constantes.SOLICITUD_ID_REGISTRADO);
		estadoSolicitudQuery.addIdIn(Constantes.SOLICITUD_ID_PENDIENTE_REGISTRO);
		estadoSolicitudQuery.addOrder(EstadoSolicitudQuery.DESCRIPCION, OrderType.ASC);
		SearchResult <EstadoSolicitud> estadosSolicitud = estadoSolicitudDAO.search(estadoSolicitudQuery);
		ArrayList <EstadoSolicitudBean> estadosSolicitudBean= new ArrayList<EstadoSolicitudBean>();
		for(int i=0;i<estadosSolicitud.getResults().size();i++){
			EstadoSolicitudBean aux;
			aux = toEstadoSolicitudBean(estadosSolicitud.getResults().get(i));
			if(aux != null){
				estadosSolicitudBean.add(aux);
			}
		}
		return estadosSolicitudBean;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.solicitudes.EstadoSolicitudManager#buscarEstadoSolicitudDesc(es.map.ips.model.EstadoSolicitudQuery)
	 */
	public EstadoSolicitudBean buscarEstadoSolicitudDesc(
			EstadoSolicitudQuery estadoSolicitudDescripcion) {
		EstadoSolicitud estadosSolicitud = estadoSolicitudDAO.searchUnique(estadoSolicitudDescripcion);
		if(estadosSolicitud == null){
			return null;
		}
		return toEstadoSolicitudBean(estadosSolicitud);
	}
	
	/**
	 * To estado solicitud bean.
	 *
	 * @param estadoSolicitud el estado solicitud
	 * @return el estado solicitud bean
	 */
	public EstadoSolicitudBean toEstadoSolicitudBean(EstadoSolicitud estadoSolicitud) {
		
		EstadoSolicitudBean result = new EstadoSolicitudBean();
		
		result.setId(estadoSolicitud.getId());
		result.setSolicituds(estadoSolicitud.getSolicituds());
		result.setDescripcion(estadoSolicitud.getDescripcion());
		
		return result;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.solicitudes.EstadoSolicitudManager#buscarIdEstadoSolicitud(es.map.ips.model.EstadoSolicitudQuery)
	 */
	public EstadoSolicitudBean buscarIdEstadoSolicitud(EstadoSolicitudQuery estadoSolicitudQuery) {
		EstadoSolicitudBean aux;
		EstadoSolicitud estado;
		estado = estadoSolicitudDAO.searchUnique(estadoSolicitudQuery);
		if(estado == null){
			return null;
		}
		aux = toEstadoSolicitudBean(estado);
		return aux;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.solicitudes.EstadoSolicitudManager#buscarIdEstadoSolicitudModel(es.map.ips.model.EstadoSolicitudQuery)
	 */
	public EstadoSolicitud buscarIdEstadoSolicitudModel(EstadoSolicitudQuery estadoSolicitudQuery) {
		EstadoSolicitud estado;
		estado = estadoSolicitudDAO.searchUnique(estadoSolicitudQuery);
		if(estado == null){
			return null;
		}
		return estado;
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
