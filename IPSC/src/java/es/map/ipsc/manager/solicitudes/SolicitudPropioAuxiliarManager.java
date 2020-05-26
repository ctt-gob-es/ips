package es.map.ipsc.manager.solicitudes;

import java.util.ArrayList;

import es.map.ips.model.SolicitudPropiosAuxiliarQuery;
import es.map.ips.model.SolicitudPropiosQuery;
import es.map.ipsc.bean.CamposPropiosBean;
import es.map.ipsc.bean.SolicitudPropiosBean;

/**
 * El Interface SolicitudPropioAuxiliarManager.
 */
public interface SolicitudPropioAuxiliarManager {
	
	/**
	 * Guardar solicitud propio auxiliar.
	 *
	 * @param listaCamposPropios el lista campos propios
	 * @param idSolicitud el id solicitud
	 */
	public void guardarSolicitudPropioAuxiliar(ArrayList<CamposPropiosBean> listaCamposPropios,Long idSolicitud);
	
	/**
	 * Obtener solicitud propios by id solicitud auxiliar.
	 *
	 * @param solicitudPropiosAuxiliarQuery el solicitud propios auxiliar query
	 * @return el array list
	 */
	public ArrayList<SolicitudPropiosBean> obtenerSolicitudPropiosByIdSolicitudAuxiliar(SolicitudPropiosAuxiliarQuery solicitudPropiosAuxiliarQuery);
}
