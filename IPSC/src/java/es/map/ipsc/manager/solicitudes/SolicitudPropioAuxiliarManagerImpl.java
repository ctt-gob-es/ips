package es.map.ipsc.manager.solicitudes;

import java.util.ArrayList;

import es.map.ips.common.model.SearchResult;
import es.map.ips.dao.SolicitudPropiosAuxiliarDAO;
import es.map.ips.model.CamposPropios;
import es.map.ips.model.Modelo;
import es.map.ips.model.SolicitudComunAuxiliar;
import es.map.ips.model.SolicitudPropiosAuxiliar;
import es.map.ips.model.SolicitudPropiosAuxiliarQuery;
import es.map.ipsc.bean.CamposPropiosBean;
import es.map.ipsc.bean.SolicitudPropiosBean;

/**
 * El Class SolicitudPropioAuxiliarManagerImpl.
 */
public class SolicitudPropioAuxiliarManagerImpl implements SolicitudPropioAuxiliarManager{
	
	/** el solicitud propios auxiliar DAO. */
	private SolicitudPropiosAuxiliarDAO solicitudPropiosAuxiliarDAO;

	
	/**
	 * Guarda una los datos propios de una solicitud en la tabla de Solicitud Propios Auxiliar.
	 *
	 * @param listaCamposPropios el lista campos propios
	 * @param idSolicitud el id solicitud
	 * @return IidPagoSolicitud Integer
	 */
	public void guardarSolicitudPropioAuxiliar(ArrayList<CamposPropiosBean> listaCamposPropios,Long idSolicitud) {
		
		SolicitudComunAuxiliar solicitudComunAuxiliar = new SolicitudComunAuxiliar();
		solicitudComunAuxiliar.setIdSolicitudAuxiliar(idSolicitud);
		
		for (CamposPropiosBean campoPropio : listaCamposPropios) {
			SolicitudPropiosAuxiliar entity = new SolicitudPropiosAuxiliar();
			entity.setSolicitudComunAuxiliar(solicitudComunAuxiliar);
			entity.setCamposPropios(toCamposPropios(campoPropio));
			entity.setValor(campoPropio.getValorVista());
			solicitudPropiosAuxiliarDAO.insert(entity);
		}
			
	}

	/**
	 * Pasa de CamposPropiosBean a CamposPropios.
	 *
	 * @param camposPropiosBean el campos propios bean
	 * @return CamposPropios
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
	 * @see es.map.ipsc.manager.solicitudes.SolicitudPropioAuxiliarManager#obtenerSolicitudPropiosByIdSolicitudAuxiliar(es.map.ips.model.SolicitudPropiosAuxiliarQuery)
	 */
	@Override
	public ArrayList<SolicitudPropiosBean> obtenerSolicitudPropiosByIdSolicitudAuxiliar(SolicitudPropiosAuxiliarQuery solicitudPropiosAuxiliarQuery) {
	
		// listado de retorno de solicitudes propios bean
		ArrayList<SolicitudPropiosBean> listaSolicitudPropiosAuxiliar = new ArrayList<SolicitudPropiosBean>();
		// busqueda de registros en tabla SOLICITUD_PROPIOS_AUXILIAR
		SearchResult<SolicitudPropiosAuxiliar> solicitudPropiosAuxiliar = solicitudPropiosAuxiliarDAO.search(solicitudPropiosAuxiliarQuery);
		
		for(int i=0; i < solicitudPropiosAuxiliar.getResults().size(); i++)
		{
			SolicitudPropiosBean aux;
			// transformacion a bean
			aux = toSolicitudPropioToBean(solicitudPropiosAuxiliar.getResults().get(i));
			if(aux != null){
				listaSolicitudPropiosAuxiliar.add(aux);
			}
			
		}	
		return listaSolicitudPropiosAuxiliar;		
	}
	
	/**
	 * To solicitud propio to bean.
	 *
	 * @param solicitudPropiosAuxiliar el solicitud propios auxiliar
	 * @return el solicitud propios bean
	 */
	private SolicitudPropiosBean toSolicitudPropioToBean(SolicitudPropiosAuxiliar solicitudPropiosAuxiliar) {
		
		SolicitudPropiosBean solicitudPropiosBean = new SolicitudPropiosBean();
		
		if(solicitudPropiosAuxiliar.getCamposPropios()!=null){
			solicitudPropiosBean.setIdCampo(Long.valueOf(solicitudPropiosAuxiliar.getCamposPropios().getIdCampo()));
		}
		if(solicitudPropiosAuxiliar.getValor()!=null){
			solicitudPropiosBean.setValor(solicitudPropiosAuxiliar.getValor());
		}
		if(solicitudPropiosAuxiliar.getCamposPropios()!=null && solicitudPropiosAuxiliar.getCamposPropios().getCampo()!=null){
			CamposPropiosBean camposPropiosBean = new CamposPropiosBean();
			camposPropiosBean.setCampo(solicitudPropiosAuxiliar.getCamposPropios().getCampo());
			solicitudPropiosBean.setCamposPropiosBean(camposPropiosBean);
		}
		
		return solicitudPropiosBean;
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
