package es.map.ipsg.manager;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import es.map.ips.common.model.OrderType;
import es.map.ips.common.model.SearchResult;
import es.map.ips.dao.SolicitudCcaaAuxiliarDAO;
import es.map.ips.model.SolicitudCcaaAuxiliar;
import es.map.ips.model.SolicitudCcaaAuxiliarQuery;
import es.map.ips.model.SolicitudComunAuxiliarQuery;
import es.map.ipsg.bean.SolicitudCcaaAuxiliarBean;
import es.map.ipsg.bean.SolicitudCcaaBean;

/**
 * El Class SolicitudCcaaAuxiliarManagerImpl.
 */
public class SolicitudCcaaAuxiliarManagerImpl implements SolicitudCcaaAuxiliarManager {

	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(SolicitudCcaaAuxiliarManagerImpl.class);
	
	/** el solicitud ccaa auxiliar DAO. */
	private SolicitudCcaaAuxiliarDAO solicitudCcaaAuxiliarDAO;
	
	/**
	 * Obtiene el ID y la descripcion de un  de Solicitud pasandole el ID.
	 *
	 * @param solicitudCcaaAuxiliarQuery el solicitud ccaa auxiliar query
	 * @return SolicitudBean Solicitud
	 */
	public SolicitudCcaaAuxiliarBean obtenerSolicitudCcaaAuxiliarByIdSolicitud (SolicitudCcaaAuxiliarQuery solicitudCcaaAuxiliarQuery) {
		SearchResult<SolicitudCcaaAuxiliar> solicitudCcaaAuxiliar = solicitudCcaaAuxiliarDAO.search(solicitudCcaaAuxiliarQuery);
		SolicitudCcaaAuxiliarBean solicitudCcaaAuxiliarBean = new SolicitudCcaaAuxiliarBean();
		for(int i=0;i<solicitudCcaaAuxiliar.getResults().size();i++){			
			solicitudCcaaAuxiliarBean= toSolicitudCcaaAuxiliarBean(solicitudCcaaAuxiliar.getResults().get(i));
		}
		return solicitudCcaaAuxiliarBean;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.SolicitudCcaaAuxiliarManager#obtenerSolicitudCcaaAuxById(java.lang.Long)
	 */
	public SolicitudCcaaAuxiliarBean obtenerSolicitudCcaaAuxById (Long idSolicitudAux) {
		SolicitudCcaaAuxiliarQuery solicitudCcaaAuxiliarQuery = new SolicitudCcaaAuxiliarQuery();
		SolicitudComunAuxiliarQuery solicitudComunAuxiliarQuery = new SolicitudComunAuxiliarQuery();
		solicitudComunAuxiliarQuery.setIdSolicitudAuxiliar(idSolicitudAux);
		solicitudCcaaAuxiliarQuery.setSolicitudComunAuxiliar(solicitudComunAuxiliarQuery);
		solicitudCcaaAuxiliarQuery.setJoin_comunidades(true);
		solicitudCcaaAuxiliarQuery.addOrder(SolicitudCcaaAuxiliarQuery.IDSOLICITUDCCAAAUXILIAR, OrderType.ASC);
		
		SearchResult<SolicitudCcaaAuxiliar> solicitudCcaaAuxiliar = solicitudCcaaAuxiliarDAO.search(solicitudCcaaAuxiliarQuery);
		SolicitudCcaaAuxiliarBean solicitudCcaaAuxiliarBean = new SolicitudCcaaAuxiliarBean();
		if (solicitudCcaaAuxiliar != null && solicitudCcaaAuxiliar.getResults() != null && solicitudCcaaAuxiliar.getResults().size() > 0) {
			solicitudCcaaAuxiliarBean= toSolicitudCcaaAuxiliarBean(solicitudCcaaAuxiliar.getResults().get(0));
		}
		return solicitudCcaaAuxiliarBean;
	}
		

	/**
	 * To solicitud ccaa auxiliar bean.
	 *
	 * @param solicitudCcaaAuxiliar el solicitud ccaa auxiliar
	 * @return el solicitud ccaa auxiliar bean
	 */
	public SolicitudCcaaAuxiliarBean toSolicitudCcaaAuxiliarBean(SolicitudCcaaAuxiliar solicitudCcaaAuxiliar){
		SolicitudCcaaAuxiliarBean solicitudCcaaAuxiliarBean = new SolicitudCcaaAuxiliarBean();

		if(solicitudCcaaAuxiliar.getSolicitudComunAuxiliar() != null)
		{	
			solicitudCcaaAuxiliarBean.setIdSolicitud(solicitudCcaaAuxiliar.getSolicitudComunAuxiliar().getIdSolicitudAuxiliar());
			solicitudCcaaAuxiliarBean.setSolicitud(solicitudCcaaAuxiliar.getSolicitudComunAuxiliar());
		}	
		if(solicitudCcaaAuxiliar.getComunidades() !=null){
			solicitudCcaaAuxiliarBean.setComunidad(solicitudCcaaAuxiliar.getComunidades());
			solicitudCcaaAuxiliarBean.setIdComunidad(String.valueOf(solicitudCcaaAuxiliar.getComunidades().getIdComunidad()));
			solicitudCcaaAuxiliarBean.setCodigoComunidad(solicitudCcaaAuxiliar.getComunidades().getCodigo());
		}
		if(solicitudCcaaAuxiliar.getProvincia() !=null){
			solicitudCcaaAuxiliarBean.setProvincia(solicitudCcaaAuxiliar.getProvincia());
			solicitudCcaaAuxiliarBean.setCodigoProvincia(solicitudCcaaAuxiliar.getProvincia().getCodigo());
		}
		if(solicitudCcaaAuxiliar.getTituloFamnumerosa() !=null){
			solicitudCcaaAuxiliarBean.setTituloFamnumerosa(solicitudCcaaAuxiliar.getTituloFamnumerosa());
		}				
		return solicitudCcaaAuxiliarBean;
	}
	
	/**
	 * To solicitud ccaa bean.
	 *
	 * @param solicitudCcaa el solicitud ccaa
	 * @return el solicitud ccaa bean
	 */
	public SolicitudCcaaBean toSolicitudCcaaBean(SolicitudCcaaAuxiliar solicitudCcaa){
		SolicitudCcaaBean solicitudCcaaBean = new SolicitudCcaaBean();
		
		solicitudCcaaBean.setIdSolicitudCcaa(solicitudCcaa.getIdSolicitudCcaaAuxiliar());

		if(solicitudCcaa.getSolicitudComunAuxiliar() != null)
		{	
			solicitudCcaaBean.setIdSolicitud(solicitudCcaa.getSolicitudComunAuxiliar().getIdSolicitudAuxiliar());
			//solicitudCcaaBean.setSolicitud(solicitudCcaa.getSolicitudComunAuxiliar());
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
	 *  Metodo que elimina una solicitud auxiliar dado un id de solicitud.
	 *
	 * @param idSolicitud el id solicitud
	 */
	
	public void borrarSolicitudCcaaAuxiliar(Long idSolicitud){
		try{
		SolicitudCcaaAuxiliarQuery solicitudQuery = new SolicitudCcaaAuxiliarQuery();
		SolicitudComunAuxiliarQuery solicitudComunAuxiliarQuery = new SolicitudComunAuxiliarQuery();
		solicitudComunAuxiliarQuery.setIdSolicitudAuxiliar(idSolicitud);
		solicitudQuery.setSolicitudComunAuxiliar(solicitudComunAuxiliarQuery);
		// Buscamos la solicitud de CCAA auxiliar que contiene el id solicitude de la solicitud comun
		SolicitudCcaaAuxiliar solicitudCcaaAuxiliar = solicitudCcaaAuxiliarDAO.searchUnique(solicitudQuery);
		// Eliminamos la solicitud
		solicitudCcaaAuxiliarDAO.delete(solicitudCcaaAuxiliar.getIdSolicitudCcaaAuxiliar());
		}catch (Exception e){
			logger.error("No se puede eliminar solicitud de CCAA Auxiliar con ID_SOLICITUD" + idSolicitud,e);	
		}
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.SolicitudCcaaAuxiliarManager#buscarSolicitudAll(es.map.ips.model.SolicitudCcaaAuxiliarQuery)
	 */
	@Override
	public ArrayList<SolicitudCcaaBean> buscarSolicitudAll(SolicitudCcaaAuxiliarQuery solicitudQuery) {
	
		   SearchResult<SolicitudCcaaAuxiliar> solicitudAuxCcaa = solicitudCcaaAuxiliarDAO.search(solicitudQuery);

			ArrayList<SolicitudCcaaBean> arrSolicitud = new ArrayList<SolicitudCcaaBean>();
			for(int i=0;i<solicitudAuxCcaa.getResults().size();i++){
				SolicitudCcaaBean aux;
				aux = toSolicitudCcaaBean(solicitudAuxCcaa.getResults().get(i));
				if(aux != null){
					arrSolicitud.add(aux);
				}
			}	
			return arrSolicitud;		
	}

	/**
	 * Obtiene el solicitud ccaa auxiliar DAO.
	 *
	 * @return el solicitud ccaa auxiliar DAO
	 */
	public SolicitudCcaaAuxiliarDAO getSolicitudCcaaAuxiliarDAO() {
		return solicitudCcaaAuxiliarDAO;
	}

	/**
	 * Establece el solicitud ccaa auxiliar DAO.
	 *
	 * @param solicitudCcaaAuxiliarDAO el nuevo solicitud ccaa auxiliar DAO
	 */
	public void setSolicitudCcaaAuxiliarDAO(SolicitudCcaaAuxiliarDAO solicitudCcaaAuxiliarDAO) {
		this.solicitudCcaaAuxiliarDAO = solicitudCcaaAuxiliarDAO;
	}
	
}
