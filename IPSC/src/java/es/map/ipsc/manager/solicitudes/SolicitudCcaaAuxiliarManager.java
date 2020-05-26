package es.map.ipsc.manager.solicitudes;

import es.map.ips.model.SolicitudCcaaAuxiliarQuery;
import es.map.ipsc.bean.Formulario790Bean;
import es.map.ipsc.bean.SolicitudCcaaAuxiliarBean;

/**
 * El Interface SolicitudCcaaAuxiliarManager.
 */
public interface SolicitudCcaaAuxiliarManager {
	
	/**
	 * Guardar solicitud ccaa auxiliar formulario 790.
	 *
	 * @param formulario790Bean el formulario 790 bean
	 * @param idSolicitud el id solicitud
	 */
	public void guardarSolicitudCcaaAuxiliarFormulario790(Formulario790Bean formulario790Bean, Long idSolicitud);

	/**
	 * Obtener solicitud ccaa auxiliar.
	 *
	 * @param solicitudCcaaAuxiliarQuery el solicitud ccaa auxiliar query
	 * @return el solicitud ccaa auxiliar bean
	 */
	public SolicitudCcaaAuxiliarBean obtenerSolicitudCcaaAuxiliar(SolicitudCcaaAuxiliarQuery solicitudCcaaAuxiliarQuery);

}
