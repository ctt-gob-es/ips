package es.map.ipsc.manager.solicitudes;

import es.map.ips.common.model.OrderType;
import es.map.ips.common.model.SearchResult;
import es.map.ips.dao.ComunidadesDAO;
import es.map.ips.dao.SolicitudCcaaDAO;
import es.map.ips.dao.SolicitudComunDAO;
import es.map.ips.model.Comunidades;
import es.map.ips.model.ComunidadesQuery;
import es.map.ips.model.SolicitudCcaa;
import es.map.ips.model.SolicitudCcaaQuery;
import es.map.ips.model.SolicitudComun;
import es.map.ips.model.SolicitudComunQuery;
import es.map.ipsc.bean.SolicitudCcaaBean;

/**
 * El Class SolicitudCcaaManagerImpl.
 */
public class SolicitudCcaaManagerImpl implements SolicitudCcaaManager{

	/** el solicitud ccaa DAO. */
	private SolicitudCcaaDAO solicitudCcaaDAO;
	
	/** el solicitud comun DAO. */
	private SolicitudComunDAO solicitudComunDAO;
	
	/** el comunidades DAO. */
	private ComunidadesDAO comunidadesDAO;

	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.solicitudes.SolicitudCcaaManager#guardarSolicitudCcaa(es.map.ipsc.bean.SolicitudCcaaBean)
	 */
	@Override
	public void guardarSolicitudCcaa(SolicitudCcaaBean solicitudCcaaBean) {
		// TODO Auto-generated method stub
		SolicitudCcaa solicitudCcaa = toSolicitudCcaa(solicitudCcaaBean);
		solicitudCcaaDAO.insert(solicitudCcaa);	
	}
	
	/**
	 * To solicitud ccaa.
	 *
	 * @param solicitudCcaaBean el solicitud ccaa bean
	 * @return el solicitud ccaa
	 */
	private SolicitudCcaa toSolicitudCcaa(
			SolicitudCcaaBean solicitudCcaaBean) {
		SolicitudCcaa aux = new SolicitudCcaa();
		SolicitudComunQuery solicitudQuery = new SolicitudComunQuery();
		Long idSolicitud = Long.valueOf(solicitudCcaaBean.getIdSolicitud());
		solicitudQuery.setIdSolicitud(idSolicitud);
		SolicitudComun solicitud = solicitudComunDAO.searchUnique(solicitudQuery);

			if(solicitud != null){
				aux.setSolicitudComun(solicitud);
			}
			if(solicitudCcaaBean.getIdComunidadDD()!=null){
				ComunidadesQuery comunidadQuery = new ComunidadesQuery();				
				Integer idComunidad = Integer.valueOf(solicitudCcaaBean.getIdComunidadDD());
				comunidadQuery.setIdComunidad(idComunidad);
				Comunidades comunidad = comunidadesDAO.searchUnique(comunidadQuery);
				aux.setComunidades(comunidad);
				if(solicitud.getProvinciaByIdProvinciaDomicilio()!=null){
				aux.setProvincia(solicitud.getProvinciaByIdProvinciaDomicilio());
				}				
			}
			if(solicitudCcaaBean.getIdComunidadFN()!=null){
				ComunidadesQuery comunidadQuery = new ComunidadesQuery();				
				Integer idComunidad = Integer.valueOf(solicitudCcaaBean.getIdComunidadFN());
				comunidadQuery.setIdComunidad(idComunidad);
				Comunidades comunidad = comunidadesDAO.searchUnique(comunidadQuery);
				aux.setComunidades(comunidad);
				if(solicitudCcaaBean.getTituloFamnumerosa()!=null && !(solicitudCcaaBean.getTituloFamnumerosa().equals(""))){
					aux.setTituloFamnumerosa(solicitudCcaaBean.getTituloFamnumerosa());
				}
			}

		return aux;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.solicitudes.SolicitudCcaaManager#buscarSolicitudCcaaIdSolicitud(es.map.ips.model.SolicitudCcaaQuery)
	 */
	@Override
	public SolicitudCcaaBean buscarSolicitudCcaaIdSolicitud(
			SolicitudCcaaQuery solicitudCcaaQuery) {
		solicitudCcaaQuery.addOrder(SolicitudCcaaQuery.IDSOLICITUDCCAA, OrderType.DESC);
		SearchResult<SolicitudCcaa> solicitudesCCAA = solicitudCcaaDAO.search(solicitudCcaaQuery);
		
		if(solicitudesCCAA !=null && solicitudesCCAA.getResults() != null && solicitudesCCAA.getResults().size() > 0){
			return toSolicitudBean(solicitudesCCAA.getResults().get(0));
		}
		return null;
	}
	
	
			
	/**
	 * To solicitud bean.
	 *
	 * @param solicitudCcaa el solicitud ccaa
	 * @return el solicitud ccaa bean
	 */
	private SolicitudCcaaBean toSolicitudBean(SolicitudCcaa solicitudCcaa) {
		
		SolicitudCcaaBean solicitudCcaaBean = new SolicitudCcaaBean();
		
		if(solicitudCcaa.getComunidades() !=null){
		solicitudCcaaBean.setComunidad(solicitudCcaa.getComunidades());
		solicitudCcaaBean.setDescripcion(solicitudCcaa.getComunidades().getDescripcion());
		}
		if(solicitudCcaa.getTituloFamnumerosa()!=null){
			solicitudCcaaBean.setTituloFamnumerosa(solicitudCcaa.getTituloFamnumerosa());
		}
		
		// TODO Auto-generated method stub
		return solicitudCcaaBean;
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
	 * Obtiene el solicitud ccaa DAO.
	 *
	 * @return el solicitud ccaa DAO
	 */
	public SolicitudCcaaDAO getSolicitudCcaaDAO() {
		return solicitudCcaaDAO;
	}

	/**
	 * Establece el solicitud ccaa DAO.
	 *
	 * @param solicitudCcaaDAO el nuevo solicitud ccaa DAO
	 */
	public void setSolicitudCcaaDAO(SolicitudCcaaDAO solicitudCcaaDAO) {
		this.solicitudCcaaDAO = solicitudCcaaDAO;
	}

	/**
	 * Obtiene el comunidades DAO.
	 *
	 * @return el comunidades DAO
	 */
	public ComunidadesDAO getComunidadesDAO() {
		return comunidadesDAO;
	}

	/**
	 * Establece el comunidades DAO.
	 *
	 * @param comunidadesDAO el nuevo comunidades DAO
	 */
	public void setComunidadesDAO(ComunidadesDAO comunidadesDAO) {
		this.comunidadesDAO = comunidadesDAO;
	}






}
