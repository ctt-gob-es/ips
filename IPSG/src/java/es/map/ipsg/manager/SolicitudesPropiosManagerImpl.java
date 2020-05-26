package es.map.ipsg.manager;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import es.map.ips.common.exception.ApplicationException;
import es.map.ips.common.model.OrderType;
import es.map.ips.common.model.SearchResult;
import es.map.ips.dao.SolicitudPropiosDAO;
import es.map.ips.model.CamposPropios;
import es.map.ips.model.Modelo;
import es.map.ips.model.SolicitudComun;
import es.map.ips.model.SolicitudComunQuery;
import es.map.ips.model.SolicitudPropios;
import es.map.ips.model.SolicitudPropiosQuery;
import es.map.ipsg.bean.CamposPropiosBean;
import es.map.ipsg.bean.SolicitudPropiosBean;

/**
 * El Class SolicitudesPropiosManagerImpl.
 */
public class SolicitudesPropiosManagerImpl implements SolicitudesPropiosManager {

	/** el solicitud propios DAO. */
	private SolicitudPropiosDAO solicitudPropiosDAO;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(SolicitudesPropiosManagerImpl.class);

	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.SolicitudesPropiosManager#obtenerCamposPropiosByIdSolicitud(es.map.ips.model.SolicitudPropiosQuery)
	 */
	@Override
	public ArrayList<SolicitudPropiosBean> obtenerCamposPropiosByIdSolicitud(SolicitudPropiosQuery solicitudPropiosQuery) {
		solicitudPropiosQuery.addOrder(SolicitudPropiosQuery.CAMPOSPROPIOS, OrderType.ASC);
		ArrayList<SolicitudPropiosBean> listaSolicitudPropios = new ArrayList<SolicitudPropiosBean>();

		SearchResult<SolicitudPropios> SolicitudPropios = buscarSolicitud(solicitudPropiosQuery);
		
		for(int i=0; i < SolicitudPropios.getResults().size(); i++)
		{
			SolicitudPropiosBean aux;
			aux = toSolicitudPropioToBean(SolicitudPropios.getResults().get(i));
			if(aux != null){
				listaSolicitudPropios.add(aux);
			}
		}	
		return listaSolicitudPropios;		
	}


	/**
	 * Buscar solicitud.
	 *
	 * @param solicitudPropiosQuery el solicitud propios query
	 * @return el search result
	 */
	private SearchResult<SolicitudPropios> buscarSolicitud(SolicitudPropiosQuery solicitudPropiosQuery) {
		ApplicationException.assertNotNull(solicitudPropiosQuery, "solicitudPropiosQuery no puede ser null");

		return solicitudPropiosDAO.search(solicitudPropiosQuery);
	}
	
	/**
	 * Método de transformación de SolicitudPropios a SolicitudPropiosBean.
	 *
	 * @param solicitudPropios el solicitud propios
	 * @return el solicitud propios bean
	 */
	private SolicitudPropiosBean toSolicitudPropioToBean(SolicitudPropios solicitudPropios) {
		
		SolicitudPropiosBean solicitudPropiosBean = new SolicitudPropiosBean();
		
		if(solicitudPropios.getIdPropio()!=null){
			solicitudPropiosBean.setId(solicitudPropios.getIdPropio());
		}
		
		if(solicitudPropios.getCamposPropios()!=null){
			solicitudPropiosBean.setIdCampo(Long.valueOf(solicitudPropios.getCamposPropios().getIdCampo()));
		}
		if(solicitudPropios.getValor()!=null){
			solicitudPropiosBean.setValor(solicitudPropios.getValor());
		}
		if(solicitudPropios.getCamposPropios()!=null && solicitudPropios.getCamposPropios().getCampo()!=null){
			CamposPropiosBean camposPropiosBean = new CamposPropiosBean();
			camposPropiosBean.setCampo(solicitudPropios.getCamposPropios().getCampo());
			solicitudPropiosBean.setCamposPropiosBean(camposPropiosBean);
		}
		
		return solicitudPropiosBean;
	}
	
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.SolicitudesPropiosManager#guardarSolicitudPropiosBean(es.map.ipsg.bean.SolicitudPropiosBean)
	 */
	@Override
	public Integer guardarSolicitudPropiosBean(SolicitudPropiosBean solicitudPropiosBean) {
		
		SolicitudPropios solicitudPropios=  toSolicitudPropios(solicitudPropiosBean);
		Long idRegistroSolicitud = null;
		try{
			idRegistroSolicitud = solicitudPropiosDAO.insert(solicitudPropios);
		}catch(Exception e){
			logger.error("No se puede guardar la soliciud con ID_REGISTROSOLICITUD:" + idRegistroSolicitud,e);	
		}
		Integer IidRegistroSolicitud = null;
		if(idRegistroSolicitud != null)
		{
			IidRegistroSolicitud = new Integer(idRegistroSolicitud.toString());
		}
		return IidRegistroSolicitud;
	}
	
	
	/**
	 * Método de transformación de SolicitudPropiosBean a SolicitudPropios.
	 *
	 * @param solicitudPropiosBean el solicitud propios bean
	 * @return el solicitud propios
	 */
	private SolicitudPropios toSolicitudPropios(SolicitudPropiosBean solicitudPropiosBean) {
		
		SolicitudPropios solicitudPropios = new SolicitudPropios();
		
		if(solicitudPropiosBean.getId()!=null){
			solicitudPropios.setIdPropio(solicitudPropiosBean.getId());
		}
		
		if(solicitudPropiosBean.getCamposPropiosBean()!=null){
			solicitudPropios.setCamposPropios(toCamposPropios(solicitudPropiosBean.getCamposPropiosBean()));
		}
		if(solicitudPropiosBean.getIdCampo()!=null){
			solicitudPropios.setIdPropio(Long.valueOf(solicitudPropiosBean.getIdCampo()));
		}
		if(solicitudPropiosBean.getIdSolicitud()!=null){
			SolicitudComun solicitudComun = new SolicitudComun();
			solicitudComun.setIdSolicitud(solicitudPropiosBean.getIdSolicitud());
			solicitudPropios.setSolicitudComun(solicitudComun);
		}
		if(solicitudPropiosBean.getValor()!=null){
			solicitudPropios.setValor(solicitudPropiosBean.getValor());
		}
		return solicitudPropios;
	}
	
	/**
	 * To campos propios.
	 *
	 * @param camposPropiosBean el campos propios bean
	 * @return el campos propios
	 */
	private CamposPropios toCamposPropios(CamposPropiosBean camposPropiosBean) {
		CamposPropios camposPropios = new CamposPropios();
		if(camposPropiosBean.getId()!=null){
			camposPropios.setIdCampo(camposPropiosBean.getId().intValue());
		}
		if(camposPropiosBean.getIdModelo()!=null){
			Modelo modelo = new Modelo();
			modelo.setIdModelo(Integer.parseInt(camposPropiosBean.getIdModelo()));
			camposPropios.setModelo(modelo);
		}
		if(camposPropiosBean.getCampo()!=null){
			camposPropios.setCampo(camposPropiosBean.getCampo());
		}
		if(camposPropiosBean.getDescripcion()!=null){
			camposPropios.setDescripcion(camposPropiosBean.getDescripcion());
		}
		return camposPropios;
	}
	
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.SolicitudesPropiosManager#modificarSolicitudPropiosBean(es.map.ipsg.bean.SolicitudPropiosBean)
	 */
	@Override
	public void modificarSolicitudPropiosBean(SolicitudPropiosBean solicitudPropiosBean) {
		// TODO Auto-generated method stub
		SolicitudPropios solicitud =  toSolicitudPropios(solicitudPropiosBean);
		solicitud.setIdPropio(solicitudPropiosBean.getId());
		solicitudPropiosDAO.update(solicitud);
	}


	/**
	 * Obtiene el solicitud propios DAO.
	 *
	 * @return el solicitud propios DAO
	 */
	public SolicitudPropiosDAO getSolicitudPropiosDAO() {
		return solicitudPropiosDAO;
	}


	/**
	 * Establece el solicitud propios DAO.
	 *
	 * @param solicitudPropiosDAO el nuevo solicitud propios DAO
	 */
	public void setSolicitudPropiosDAO(SolicitudPropiosDAO solicitudPropiosDAO) {
		this.solicitudPropiosDAO = solicitudPropiosDAO;
	}


	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.SolicitudesPropiosManager#borrarSolicitudPropio(java.lang.Long)
	 */
	@Override
	public void borrarSolicitudPropio(Long idSolicitud) {
		try{
			SolicitudPropiosQuery solicitudQuery = new SolicitudPropiosQuery();
			SolicitudComunQuery solicitudComunQuery = new SolicitudComunQuery();
			solicitudComunQuery.setIdSolicitud(idSolicitud);
			solicitudQuery.setSolicitudComun(solicitudComunQuery);
			// Buscamos todas las solicitudes que contienen ese id de Solicitud
			SearchResult<SolicitudPropios> solicitudPropio = solicitudPropiosDAO.search(solicitudQuery);
			if(null!=solicitudPropio && solicitudPropio.size()>0){
				for (int i = 0; i < solicitudPropio.size(); i++) {
					// Eliminamos la solicitud
					solicitudPropiosDAO.delete(solicitudPropio.getResults().get(i).getIdPropio());
				}
			}
		}catch (Exception e){
			logger.error("No se puede eliminar el registro de solicitud_propios con ID_SOLICITUD=" + idSolicitud);
			logger.error("Error: ", e);
		}
	}


}
