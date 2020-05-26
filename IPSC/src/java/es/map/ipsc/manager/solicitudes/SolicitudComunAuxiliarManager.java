package es.map.ipsc.manager.solicitudes;

import java.util.List;

import es.map.ips.model.SolicitudComunAuxiliarQuery;
import es.map.ipsc.bean.DetalleSolicitudBean;
import es.map.ipsc.bean.Formulario790Bean;

/**
 * El Interface SolicitudComunAuxiliarManager.
 */
public interface SolicitudComunAuxiliarManager {
	
	/**
	 * Guardar solicitud formulario 790.
	 *
	 * @param formulario790Bean el formulario 790 bean
	 * @return el long
	 */
	public Long guardarSolicitudFormulario790(Formulario790Bean formulario790Bean);

	/**
	 * Detalle solicitudes bean.
	 *
	 * @param solicitudComunAuxiliarQuery el solicitud comun auxiliar query
	 * @return el list
	 */
	public List<DetalleSolicitudBean> detalleSolicitudesBean(SolicitudComunAuxiliarQuery solicitudComunAuxiliarQuery);
	
	/**
	 * Buscar solicitud bean.
	 *
	 * @param solicitudComunAuxiliarQuery el solicitud comun auxiliar query
	 * @return el detalle solicitud bean
	 */
	public DetalleSolicitudBean buscarSolicitudBean(SolicitudComunAuxiliarQuery solicitudComunAuxiliarQuery);
}
