package es.map.ipsg.manager;

import es.map.ips.common.model.SearchResult;
import es.map.ips.dao.ContadorNumSolicitudDAO;
import es.map.ips.model.ContadorNumSolicitud;
import es.map.ips.model.ContadorNumSolicitudQuery;
import es.map.ipsg.bean.ContadorNumSolicitudBean;

/**
 * El Class ContadorNumSolicitudManagerImpl.
 */
public class ContadorNumSolicitudManagerImpl implements
		ContadorNumSolicitudManager {
	
	/** el contador num solicitud DAO. */
	private ContadorNumSolicitudDAO contadorNumSolicitudDAO;

	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.ContadorNumSolicitudManager#modificarContadorSolicitud(es.map.ipsg.bean.ContadorNumSolicitudBean)
	 */
	public void modificarContadorSolicitud(ContadorNumSolicitudBean contadorNumSolicitudBean) {
		ContadorNumSolicitud contadorNumSolicitud = toContadorNumSolicitud(contadorNumSolicitudBean);
		contadorNumSolicitudDAO.update(contadorNumSolicitud);
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.ContadorNumSolicitudManager#obtenerContadorNumSolicitud()
	 */
	public ContadorNumSolicitudBean obtenerContadorNumSolicitud(){
		SearchResult<ContadorNumSolicitud> contadorNumSolicitud = buscarContadorNumSolicitud();
		ContadorNumSolicitudBean contadorBean = new ContadorNumSolicitudBean();
		
		if(contadorNumSolicitud!=null){
			contadorBean= toContadorNumSolicitudBean(contadorNumSolicitud.getResults().get(0));
		}
		
		return contadorBean;
		
	}
	
	/**
	 * Buscar contador num solicitud.
	 *
	 * @return el search result
	 */
	private SearchResult<ContadorNumSolicitud> buscarContadorNumSolicitud(){
		ContadorNumSolicitudQuery contadorNumSolicitudQuery = new ContadorNumSolicitudQuery();
		
		
		contadorNumSolicitudQuery.setId(Integer.parseInt("1"));
		return contadorNumSolicitudDAO.search(contadorNumSolicitudQuery);
	}
	
	/**
	 * To contador num solicitud.
	 *
	 * @param contadorNumSolicitudBean el contador num solicitud bean
	 * @return el contador num solicitud
	 */
	private ContadorNumSolicitud toContadorNumSolicitud(ContadorNumSolicitudBean contadorNumSolicitudBean){
		ContadorNumSolicitud aux = new ContadorNumSolicitud();
		
		aux.setCdmodelo(contadorNumSolicitudBean.getCdmodelo());
		aux.setCdprovincia(contadorNumSolicitudBean.getCdprovincia());
		aux.setCdtasa(contadorNumSolicitudBean.getCdtasa());
		aux.setId(contadorNumSolicitudBean.getId());
		aux.setMaximo(contadorNumSolicitudBean.getMaximo());
		aux.setMinimo(contadorNumSolicitudBean.getMinimo());
		aux.setIncremento(contadorNumSolicitudBean.getIncremento());
		aux.setModulo(contadorNumSolicitudBean.getModulo());
		aux.setMatriz(contadorNumSolicitudBean.getMatriz());
		aux.setContador(contadorNumSolicitudBean.getContador());
		
		return aux;
	}
	
	/**
	 * To contador num solicitud bean.
	 *
	 * @param contadorNumSolicitud el contador num solicitud
	 * @return el contador num solicitud bean
	 */
	private ContadorNumSolicitudBean toContadorNumSolicitudBean(ContadorNumSolicitud contadorNumSolicitud){
		ContadorNumSolicitudBean aux = new ContadorNumSolicitudBean();
		
		aux.setCdmodelo(contadorNumSolicitud.getCdmodelo());
		aux.setCdprovincia(contadorNumSolicitud.getCdprovincia());
		aux.setCdtasa(contadorNumSolicitud.getCdtasa());
		aux.setId(contadorNumSolicitud.getId());
		aux.setMaximo(contadorNumSolicitud.getMaximo());
		aux.setMinimo(contadorNumSolicitud.getMinimo());
		aux.setIncremento(contadorNumSolicitud.getIncremento());
		aux.setModulo(contadorNumSolicitud.getModulo());
		aux.setMatriz(contadorNumSolicitud.getMatriz());
		aux.setContador(contadorNumSolicitud.getContador());
		
		return aux;
	}

	/**
	 * Obtiene el contador num solicitud DAO.
	 *
	 * @return el contador num solicitud DAO
	 */
	public ContadorNumSolicitudDAO getContadorNumSolicitudDAO() {
		return contadorNumSolicitudDAO;
	}

	/**
	 * Establece el contador num solicitud DAO.
	 *
	 * @param contadorNumSolicitudDAO el nuevo contador num solicitud DAO
	 */
	public void setContadorNumSolicitudDAO(ContadorNumSolicitudDAO contadorNumSolicitudDAO) {
		this.contadorNumSolicitudDAO = contadorNumSolicitudDAO;
	}
	
	

}
