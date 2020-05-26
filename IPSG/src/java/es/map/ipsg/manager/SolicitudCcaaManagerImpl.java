package es.map.ipsg.manager;

import java.util.ArrayList;

import org.apache.log4j.Logger;

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
import es.map.ipsg.bean.SolicitudCcaaBean;


/**
 * El Class SolicitudCcaaManagerImpl.
 */
public class SolicitudCcaaManagerImpl implements SolicitudCcaaManager {

	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(SolicitudCcaaManagerImpl.class);
	
	/** el solicitud ccaa DAO. */
	private SolicitudCcaaDAO solicitudCcaaDAO;	
	
	/** el solicitud comun DAO. */
	private SolicitudComunDAO solicitudComunDAO;
	
	/** el comunidades DAO. */
	private ComunidadesDAO comunidadesDAO;

	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.SolicitudCcaaManager#guardarSolicitudCcaa(es.map.ipsg.bean.SolicitudCcaaBean)
	 */
	@Override
	public void guardarSolicitudCcaa(SolicitudCcaaBean solicitudCcaaBean) {
		// TODO Auto-generated method stub
		SolicitudCcaa solicitudCcaa = toSolicitudCcaa(solicitudCcaaBean);
		solicitudCcaaDAO.insert(solicitudCcaa);	
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.SolicitudCcaaManager#almacenarSolicitudCcaa(es.map.ips.model.SolicitudCcaa)
	 */
	public void almacenarSolicitudCcaa(SolicitudCcaa solicitudCcaa) {
		solicitudCcaaDAO.insert(solicitudCcaa);	
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.SolicitudCcaaManager#buscarSolicitudAll(es.map.ips.model.SolicitudCcaaQuery)
	 */
	@Override
	public ArrayList<SolicitudCcaaBean> buscarSolicitudAll(
			SolicitudCcaaQuery solicitudQuery) {
	
		   SearchResult<SolicitudCcaa> solicitudCcaa = solicitudCcaaDAO.search(solicitudQuery);

			ArrayList<SolicitudCcaaBean> arrSolicitud = new ArrayList<SolicitudCcaaBean>();
			for(int i=0;i<solicitudCcaa.getResults().size();i++){
				SolicitudCcaaBean aux;
				aux = toSolicitudCcaaBean(solicitudCcaa.getResults().get(i));
				if(aux != null){
					arrSolicitud.add(aux);
				}
			}	
			return arrSolicitud;		
		}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.SolicitudCcaaManager#modificarSolicitudCcaaBean(es.map.ipsg.bean.SolicitudCcaaBean)
	 */
	@Override
	public void modificarSolicitudCcaaBean(SolicitudCcaaBean solicitudCcaaBean) {
		// TODO Auto-generated method stub
		SolicitudCcaa solicitudCcaa =  toSolicitudCcaa(solicitudCcaaBean);
		solicitudCcaa.setIdSolicitudCcaa(solicitudCcaaBean.getIdSolicitudCcaa());
		solicitudCcaaDAO.update(solicitudCcaa);
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
	 * @see es.map.ipsg.manager.SolicitudCcaaManager#buscarSolicitudCcaaIdSolicitud(es.map.ips.model.SolicitudCcaaQuery)
	 */
	@Override
	public SolicitudCcaaBean buscarSolicitudCcaaIdSolicitud(
			SolicitudCcaaQuery solicitudCcaaQuery) {
		SolicitudCcaa solicitudCcaa = solicitudCcaaDAO.searchUnique(solicitudCcaaQuery);
		if(solicitudCcaa !=null){
			return toSolicitudBean(solicitudCcaa);
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
	 * Obtiene el ID y la descripción de un  de Solicitud pasándole el ID.
	 *
	 * @param solicitudCcaaQuery el solicitud ccaa query
	 * @return SolicitudBean Solicitud
	 */
	public SolicitudCcaaBean obtenerSolicitudCcaaByIdSolicitud (SolicitudCcaaQuery solicitudCcaaQuery) {
		SearchResult<SolicitudCcaa> solicitudCcaa = solicitudCcaaDAO.search(solicitudCcaaQuery);
		SolicitudCcaaBean solicitudCcaaBean = new SolicitudCcaaBean();
		for(int i=0;i<solicitudCcaa.getResults().size();i++){			
			solicitudCcaaBean= toSolicitudCcaaBean(solicitudCcaa.getResults().get(i));
		}
		return solicitudCcaaBean;
	}
		

	/**
	 * To solicitud ccaa bean.
	 *
	 * @param solicitudCcaa el solicitud ccaa
	 * @return el solicitud ccaa bean
	 */
	public SolicitudCcaaBean toSolicitudCcaaBean(SolicitudCcaa solicitudCcaa){
		SolicitudCcaaBean solicitudCcaaBean = new SolicitudCcaaBean();
		
		solicitudCcaaBean.setIdSolicitudCcaa(solicitudCcaa.getIdSolicitudCcaa());

		if(solicitudCcaa.getSolicitudComun() != null)
		{	
			solicitudCcaaBean.setIdSolicitud(solicitudCcaa.getSolicitudComun().getIdSolicitud());
			solicitudCcaaBean.setSolicitud(solicitudCcaa.getSolicitudComun());
		}	
		if(solicitudCcaa.getComunidades() !=null){
			solicitudCcaaBean.setComunidad(solicitudCcaa.getComunidades());
			solicitudCcaaBean.setIdComunidad(String.valueOf(solicitudCcaa.getComunidades().getIdComunidad()));
			solicitudCcaaBean.setCodigoComunidad(solicitudCcaa.getComunidades().getCodigo());
		}
		if(solicitudCcaa.getProvincia() !=null){
			solicitudCcaaBean.setProvincia(solicitudCcaa.getProvincia());
			solicitudCcaaBean.setCodigoProvincia(solicitudCcaa.getProvincia().getCodigo());
		}
		if(solicitudCcaa.getTituloFamnumerosa() !=null){
			solicitudCcaaBean.setTituloFamnumerosa(solicitudCcaa.getTituloFamnumerosa());
		}				
		return solicitudCcaaBean;
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

	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.SolicitudCcaaManager#borrarSolicitudCcaa(java.lang.Long)
	 */
	@Override
	public void borrarSolicitudCcaa(Long idSolicitud) {
		try{
			SolicitudCcaaQuery solicitudQuery = new SolicitudCcaaQuery();
			SolicitudComunQuery solicitudComunQuery = new SolicitudComunQuery();
			solicitudComunQuery.setIdSolicitud(idSolicitud);
			solicitudQuery.setSolicitudComun(solicitudComunQuery);
			// Buscamos todos los registros que contienen ese id de Solicitud
			SearchResult<SolicitudCcaa> solicitudCcaa = solicitudCcaaDAO.search(solicitudQuery);
			if(null!=solicitudCcaa && solicitudCcaa.size()>0){
				for (int i = 0; i < solicitudCcaa.size(); i++) {
					// Eliminamos la solicitud
					solicitudCcaaDAO.delete(solicitudCcaa.getResults().get(i).getIdSolicitudCcaa().intValue());
				}
			}
		}catch (Exception e){
			logger.error("No se puede eliminar el registro de solicitud_ccaa con ID_SOLICITUD=" + idSolicitud);
			logger.error("Error: ", e);
		}
	}


	
}
