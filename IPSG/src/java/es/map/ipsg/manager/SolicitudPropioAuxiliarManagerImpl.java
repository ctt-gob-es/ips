package es.map.ipsg.manager;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import es.map.ips.common.model.OrderType;
import es.map.ips.common.model.SearchResult;
import es.map.ips.dao.SolicitudPropiosAuxiliarDAO;
import es.map.ips.model.CamposPropios;
import es.map.ips.model.SolicitudComunAuxiliarQuery;
import es.map.ips.model.SolicitudPropios;
import es.map.ips.model.SolicitudPropiosAuxiliar;
import es.map.ips.model.SolicitudPropiosAuxiliarQuery;
import es.map.ips.model.SolicitudPropiosQuery;
import es.map.ipsg.bean.CamposPropiosBean;
import es.map.ipsg.bean.SolicitudPropiosAuxiliarBean;
import es.map.ipsg.bean.SolicitudPropiosBean;

/**
 * El Class SolicitudPropioAuxiliarManagerImpl.
 *
 * @author everis
 */
public class SolicitudPropioAuxiliarManagerImpl implements SolicitudPropioAuxiliarManager{
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(SolicitudPropioAuxiliarManagerImpl.class);
	
	/** el solicitud propios auxiliar DAO. */
	private SolicitudPropiosAuxiliarDAO solicitudPropiosAuxiliarDAO;
	
	/**
	 * Obtiene una lista con todos los Campos Propios de una Solicitud dada.
	 *
	 * @param solicitudPropiosAuxiliarQuery el solicitud propios auxiliar query
	 * @return ArrayList<CamposPropiosBean>
	 */

	public ArrayList<CamposPropiosBean> obtenerCamposPropiosAuxiliarByIdSolicitud(SolicitudPropiosAuxiliarQuery solicitudPropiosAuxiliarQuery) {
		solicitudPropiosAuxiliarQuery.addOrder(SolicitudPropiosAuxiliarQuery.CAMPOSPROPIOS, OrderType.ASC);
		ArrayList<CamposPropiosBean> listaSolicitudPropios = new ArrayList<CamposPropiosBean>();

		SearchResult<SolicitudPropiosAuxiliar> solicitudPropiosAuxiliar = buscarSolicitud(solicitudPropiosAuxiliarQuery);
		
		if(solicitudPropiosAuxiliar.size() > 0){	
			for(int i=0; i < solicitudPropiosAuxiliar.getResults().size(); i++)
			{
				CamposPropiosBean aux;
				aux = toCamposPropiosBean(solicitudPropiosAuxiliar.getResults().get(i));
				if(aux != null){
					listaSolicitudPropios.add(aux);
				}
			}
		}else{
			return null;
		}
		return listaSolicitudPropios;		
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.SolicitudPropioAuxiliarManager#obtenerCamposPropiosAuxByIdSolicitud(es.map.ips.model.SolicitudPropiosAuxiliarQuery)
	 */
	public ArrayList<SolicitudPropiosAuxiliarBean> obtenerCamposPropiosAuxByIdSolicitud(SolicitudPropiosAuxiliarQuery solicitudPropiosAuxiliarQuery) {
		solicitudPropiosAuxiliarQuery.addOrder(SolicitudPropiosAuxiliarQuery.CAMPOSPROPIOS, OrderType.ASC);
		ArrayList<SolicitudPropiosAuxiliarBean> listaSolicitudPropiosAuxiliar = new ArrayList<SolicitudPropiosAuxiliarBean>();

		SearchResult<SolicitudPropiosAuxiliar> SolicitudPropiosAuxiliar = buscarSolicitud(solicitudPropiosAuxiliarQuery);
		
		for(int i=0; i < SolicitudPropiosAuxiliar.getResults().size(); i++)
		{
			SolicitudPropiosAuxiliarBean aux;
			aux = toSolicitudPropioAuxiliarToBean(SolicitudPropiosAuxiliar.getResults().get(i));
			if(aux != null){
				listaSolicitudPropiosAuxiliar.add(aux);
			}
		}	
		return listaSolicitudPropiosAuxiliar;		
	}
	
	/**
	 * Busca una solicitud en la tabla de Solicitud Propios Auxiliar.
	 *
	 * @param solicitudPropiosAuxiliarQuery el solicitud propios auxiliar query
	 * @return SearchResult<SolicitudPropiosAuxiliar>
	 */
	private SearchResult<SolicitudPropiosAuxiliar> buscarSolicitud(SolicitudPropiosAuxiliarQuery solicitudPropiosAuxiliarQuery) {
	
		return solicitudPropiosAuxiliarDAO.search(solicitudPropiosAuxiliarQuery);
	}
	
	/**
	 * Método de transformación de SolicitudPropiosAuxiliar a CamposPropiosBean.
	 *
	 * @param solicitudPropiosAuxiliar el solicitud propios auxiliar
	 * @return CamposPropiosBean
	 */
	private CamposPropiosBean toCamposPropiosBean(SolicitudPropiosAuxiliar solicitudPropiosAuxiliar) {
		
		CamposPropiosBean camposPropiosBean = new CamposPropiosBean();
		
		if(solicitudPropiosAuxiliar.getCamposPropios()!=null && solicitudPropiosAuxiliar.getCamposPropios().getCampo()!=null){
	
			camposPropiosBean= toCamposPropiosBean(solicitudPropiosAuxiliar.getCamposPropios());
			camposPropiosBean.setValorVista(solicitudPropiosAuxiliar.getValor());
		}
						
		return camposPropiosBean;
	}
	
	/**
	 * Método de transformación de CamposPropios a CamposPropiosBean.
	 *
	 * @param camposPropios el campos propios
	 * @return CamposPropiosBean
	 */
	
	private CamposPropiosBean toCamposPropiosBean(CamposPropios camposPropios) {
	CamposPropiosBean auxCamposPropios = new CamposPropiosBean();
	auxCamposPropios.setIdModelo(Integer.toString(camposPropios.getModelo().getIdModelo()));
	auxCamposPropios.setCampo(camposPropios.getCampo());
	auxCamposPropios.setId(Long.valueOf(Integer.toString(camposPropios.getIdCampo())));
	auxCamposPropios.setTituloCampo(camposPropios.getCampo());
	auxCamposPropios.setDescripcion(camposPropios.getDescripcion());
	auxCamposPropios.setTituloCampo_ca(camposPropios.getCampo_ca());
	auxCamposPropios.setTituloCampo_eu(camposPropios.getCampo_eu());
	auxCamposPropios.setTituloCampo_gl(camposPropios.getCampo_gl());
	auxCamposPropios.setTituloCampo_vl(camposPropios.getCampo_vl());
	return auxCamposPropios;
	}
	
	/**
	 *  Método que elimina una solicitud auxiliar dado un id de solicitud.
	 *
	 * @param idSolicitud el id solicitud
	 */
	public void borrarSolicitudPropioAuxiliar(Long idSolicitud){
		try{
			SolicitudPropiosAuxiliarQuery solicitudQuery = new SolicitudPropiosAuxiliarQuery();
			SolicitudComunAuxiliarQuery solicitudComunAuxiliarQuery = new SolicitudComunAuxiliarQuery();
			solicitudComunAuxiliarQuery.setIdSolicitudAuxiliar(idSolicitud);
			solicitudQuery.setSolicitudComunAuxiliar(solicitudComunAuxiliarQuery);
			// Buscamos todas las solicitudes que contienen ese id de Solicitud
			SearchResult<SolicitudPropiosAuxiliar> solicitudPropioAuxiliar = solicitudPropiosAuxiliarDAO.search(solicitudQuery);
			if(null!=solicitudPropioAuxiliar || solicitudPropioAuxiliar.size()>0){
				for (int i = 0; i < solicitudPropioAuxiliar.size(); i++) {
					// Eliminamos la solicitud
					solicitudPropiosAuxiliarDAO.delete(solicitudPropioAuxiliar.getResults().get(i).getIdPropioAux());
				}
			}
		}catch (Exception e){
			logger.error("No se puede eliminar solicitud propios auxiliar con ID_SOLICITUD" + idSolicitud,e);
		}
	}
	
	/**
	 * Método de transformación de SolicitudPropios a SolicitudPropiosBean.
	 *
	 * @param solicitudPropiosAuxiliar el solicitud propios auxiliar
	 * @return el solicitud propios auxiliar bean
	 */
	private SolicitudPropiosAuxiliarBean toSolicitudPropioAuxiliarToBean(SolicitudPropiosAuxiliar solicitudPropiosAuxiliar) {
		
		SolicitudPropiosAuxiliarBean solicitudPropiosAuxiliarBean = new SolicitudPropiosAuxiliarBean();
		
		if(solicitudPropiosAuxiliar.getIdPropioAux()!=null){
			solicitudPropiosAuxiliarBean.setId(solicitudPropiosAuxiliar.getIdPropioAux());
		}
		
		if(solicitudPropiosAuxiliar.getCamposPropios()!=null){
			solicitudPropiosAuxiliarBean.setIdCampo(Long.valueOf(solicitudPropiosAuxiliar.getCamposPropios().getIdCampo()));
		}
		if(solicitudPropiosAuxiliar.getValor()!=null){
			solicitudPropiosAuxiliarBean.setValor(solicitudPropiosAuxiliar.getValor());
		}
		if(solicitudPropiosAuxiliar.getCamposPropios()!=null && solicitudPropiosAuxiliar.getCamposPropios().getCampo()!=null){
			CamposPropiosBean camposPropiosBean = new CamposPropiosBean();
			camposPropiosBean.setCampo(solicitudPropiosAuxiliar.getCamposPropios().getCampo());
			solicitudPropiosAuxiliarBean.setCamposPropiosBean(camposPropiosBean);
		}
		
		return solicitudPropiosAuxiliarBean;
	}

	/**
	 * Obtiene el solicitud propios auxiliar DAO.
	 *
	 * @return el solicitud propios auxiliar DAO
	 */
	public SolicitudPropiosAuxiliarDAO getSolicitudPropiosAuxiliarDAO() {
		return solicitudPropiosAuxiliarDAO;
	}

	/**
	 * Establece el solicitud propios auxiliar DAO.
	 *
	 * @param solicitudPropiosAuxiliarDAO el nuevo solicitud propios auxiliar DAO
	 */
	public void setSolicitudPropiosAuxiliarDAO(
			SolicitudPropiosAuxiliarDAO solicitudPropiosAuxiliarDAO) {
		this.solicitudPropiosAuxiliarDAO = solicitudPropiosAuxiliarDAO;
	}
}
