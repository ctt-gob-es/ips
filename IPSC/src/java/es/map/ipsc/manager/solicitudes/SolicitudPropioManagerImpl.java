package es.map.ipsc.manager.solicitudes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import es.map.ips.common.exception.ApplicationException;
import es.map.ips.common.model.SearchResult;
import es.map.ips.dao.SolicitudPropiosDAO;
import es.map.ips.model.CamposPropios;
import es.map.ips.model.Modelo;
import es.map.ips.model.PlantillaPropios;
import es.map.ips.model.SolicitudComun;
import es.map.ips.model.SolicitudComunQuery;
import es.map.ips.model.SolicitudPropios;
import es.map.ips.model.SolicitudPropiosQuery;
import es.map.ipsc.bean.CamposPropiosBean;
import es.map.ipsc.bean.PlantillaPropiosBean;
import es.map.ipsc.bean.SolicitudPropiosBean;

/**
 * El Class SolicitudPropioManagerImpl.
 */
public class SolicitudPropioManagerImpl implements SolicitudPropioManager {
	

	/** el solicitud propios DAO. */
	private SolicitudPropiosDAO solicitudPropiosDAO;
	  		
	
	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.solicitudes.SolicitudPropioManager#guardarCamposSolicitud(java.util.HashSet, java.lang.Long)
	 */
	public Boolean guardarCamposSolicitud(HashSet<PlantillaPropiosBean> plantillas, Long idSolicitud){
		
		SolicitudComun solicitudComun = new SolicitudComun();
		solicitudComun.setIdSolicitud(idSolicitud);
		
		for (PlantillaPropiosBean plantilla : plantillas) {
			SolicitudPropios entity = new SolicitudPropios();
			entity.setSolicitudComun(solicitudComun);
			entity.setCamposPropios(toCamposPropios(plantilla.getCampoPropioBean()));
			entity.setValor(plantilla.getCampoPropioBean().getValorVista());
			
			
			
		}	
		
		return true;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.solicitudes.SolicitudPropioManager#guardarCamposSolicitudModif(java.util.HashSet, java.lang.Long)
	 */
	public Boolean guardarCamposSolicitudModif(HashSet<PlantillaPropiosBean> plantillas, Long idSolicitud){
		
		SolicitudComun solicitudComun = new SolicitudComun();
		solicitudComun.setIdSolicitud(idSolicitud);
		
		for (PlantillaPropiosBean plantilla : plantillas) {
			SolicitudPropios entity = new SolicitudPropios();
			entity.setSolicitudComun(solicitudComun);
			entity.setCamposPropios(toCamposPropios(plantilla.getCampoPropioBean()));
			entity.setValor(plantilla.getCampoPropioBean().getValorVista());
			
			solicitudPropiosDAO.insert(entity);
			
		}	
		
		
		
		return true;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.solicitudes.SolicitudPropioManager#obtenerCamposPropiosByIdSolicitud(es.map.ips.model.SolicitudPropiosQuery)
	 */
	@Override
	public ArrayList<SolicitudPropiosBean> obtenerCamposPropiosByIdSolicitud(SolicitudPropiosQuery solicitudPropiosQuery) {

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
	
	/**
	 * To campos propios.
	 *
	 * @param camposBean el campos bean
	 * @return el campos propios
	 */
	private CamposPropios toCamposPropios(CamposPropiosBean camposBean){
		CamposPropios camposPropios = new CamposPropios();
		camposPropios.setCampo(camposBean.getCampo());
		camposPropios.setDescripcion(camposBean.getDescripcion());
		camposPropios.setIdCampo(camposBean.getId().intValue());
		Modelo modelo = new Modelo();
		modelo.setIdModelo(Integer.parseInt(camposBean.getIdModelo()));
		camposPropios.setModelo(modelo);		
		return camposPropios;
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

}
