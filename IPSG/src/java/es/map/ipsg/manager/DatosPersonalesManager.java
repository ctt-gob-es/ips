package es.map.ipsg.manager;

import es.map.ipsg.bean.IntermediacionIdentidadBean;
import es.map.ipsg.bean.SolicitudBean;
import es.map.ipsg.bean.UsuarioBean;

/**
 * El Interface DatosPersonalesManager.
 */
public interface DatosPersonalesManager {
	
	/**
	 * Validar fecha nacimiento.
	 *
	 * @param solicitudBean el solicitud bean
	 * @param usuarioBean el usuario bean
	 * @return el intermediacion identidad bean
	 */
	public IntermediacionIdentidadBean validarFechaNacimiento(SolicitudBean solicitudBean, UsuarioBean usuarioBean);
	
	/**
	 * Validar fecha nacimiento auxiliar.
	 *
	 * @param solicitudBean el solicitud bean
	 * @param usuarioBean el usuario bean
	 * @return el intermediacion identidad bean
	 */
	public IntermediacionIdentidadBean validarFechaNacimientoAuxiliar(SolicitudBean solicitudBean, UsuarioBean usuarioBean);
}
