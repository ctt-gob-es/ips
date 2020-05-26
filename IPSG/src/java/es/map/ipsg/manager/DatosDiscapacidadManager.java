package es.map.ipsg.manager;


import java.util.List;

import es.map.ipsg.bean.ConvocatoriasBean;
import es.map.ipsg.bean.IntermediacionDiscapacidadBean;
import es.map.ipsg.bean.SolicitudBean;
import es.map.ipsg.bean.SolicitudCcaaAuxiliarBean;
import es.map.ipsg.bean.SolicitudCcaaBean;
import es.map.ipsg.bean.UsuarioBean;
import es.redsara.intermediacion.scsp.esquemas.ws.peticion.Funcionario;
import es.redsara.intermediacion.scsp.esquemas.ws.peticion.Procedimiento;
import es.redsara.intermediacion.scsp.esquemas.ws.peticion.Solicitante;

/**
 * El Interface DatosDiscapacidadManager.
 */
public interface DatosDiscapacidadManager {
	
	/**
	 * Validar discapacidad.
	 *
	 * @param convocatoriaBean el convocatoria bean
	 * @param solicitudBean el solicitud bean
	 * @param usuarioBean el usuario bean
	 * @param solicitudCcaaBean el solicitud ccaa bean
	 * @return el intermediacion discapacidad bean
	 */
	public IntermediacionDiscapacidadBean validarDiscapacidad(ConvocatoriasBean convocatoriaBean,SolicitudBean solicitudBean, UsuarioBean usuarioBean,SolicitudCcaaBean solicitudCcaaBean, String unidadTramitadora);
	
	/**
	 * Validar discapacidad auxiliar.
	 *
	 * @param convocatoriaBean el convocatoria bean
	 * @param solicitudBean el solicitud bean
	 * @param usuarioBean el usuario bean
	 * @param solicitudCcaaBean el solicitud ccaa bean
	 * @return el intermediacion discapacidad bean
	 */
	public IntermediacionDiscapacidadBean validarDiscapacidadAuxiliar(ConvocatoriasBean convocatoriaBean,SolicitudBean solicitudBean, UsuarioBean usuarioBean,SolicitudCcaaAuxiliarBean solicitudCcaaBean, String unidadTramitadora);
	
	/**
	 * Aprobar discapacidad verificado.
	 *
	 * @param idSolicitud el id solicitud
	 */
	public void aprobarDiscapacidadVerificado(Long idSolicitud);
	
	/**
	 * Rechazar discapacidad verificado.
	 *
	 * @param idSolicitud el id solicitud
	 */
	public void rechazarDiscapacidadVerificado(Long idSolicitud);
	
	/**
	 * Aprobar discapacidad verificado auxiliar.
	 *
	 * @param idSolicitud el id solicitud
	 */
	public void aprobarDiscapacidadVerificadoAuxiliar(Long idSolicitud);
	
	/**
	 * Rechazar discapacidad verificado auxiliar.
	 *
	 * @param idSolicitud el id solicitud
	 */
	public void rechazarDiscapacidadVerificadoAuxiliar(Long idSolicitud);
	
	/**
	 * Consultar discapacidad asincrona.
	 *
	 * @param funcionario el funcionario
	 * @param procedimiento el procedimiento
	 * @param solicitante el solicitante
	 * @param listaSolicitudesDiscapacidad el lista solicitudes discapacidad
	 * @param listaSolicitudesCcaaDiscapacidad el lista solicitudes ccaa discapacidad
	 * @param codigoCertificado el codigo certificado
	 * @return el string
	 */
	public String consultarDiscapacidadAsincrona(Funcionario funcionario, Procedimiento procedimiento,Solicitante solicitante, List<SolicitudBean> listaSolicitudesDiscapacidad, List<SolicitudCcaaBean> listaSolicitudesCcaaDiscapacidad, String codigoCertificado);
	
	/**
	 * Guardar estado fallo ws solicitud.
	 *
	 * @param solicitudBean el solicitud bean
	 */
	public void guardarEstadoFalloWsSolicitud(SolicitudBean solicitudBean);
	
	/**
	 * Guardar estado fallo ws solicitud auxiliar.
	 *
	 * @param solicitudBean el solicitud bean
	 */
	public void guardarEstadoFalloWsSolicitudAuxiliar(SolicitudBean solicitudBean);
	
	/**
	 * Guardar estado fallo ws lista.
	 *
	 * @param listaSolicitudesDiscapacidad el lista solicitudes discapacidad
	 */
	public void guardarEstadoFalloWsLista(List<SolicitudBean> listaSolicitudesDiscapacidad);
	
	/**
	 * Guardar estado pendiente list.
	 *
	 * @param listaSolicitudesDiscapacidad el lista solicitudes discapacidad
	 */
	public void guardarEstadoPendienteList(List<SolicitudBean> listaSolicitudesDiscapacidad);
	
	/**
	 * Guardar estado pendiente.
	 *
	 * @param idSolicitud el id solicitud
	 */
	public void guardarEstadoPendiente(Long idSolicitud);
	
	/**
	 * Guardar estado pendiente auxiliar.
	 *
	 * @param idSolicitud el id solicitud
	 */
	public void guardarEstadoPendienteAuxiliar(Long idSolicitud);
	
	/**
	 * Guardar estado pendiente list aux.
	 *
	 * @param listaSolicitudesDiscapacidad el lista solicitudes discapacidad
	 */
	public void guardarEstadoPendienteListAux(List<SolicitudBean> listaSolicitudesDiscapacidad);
	
	/**
	 * Guardar estado fallo ws aux lista.
	 *
	 * @param listaSolicitudesDiscapacidad el lista solicitudes discapacidad
	 */
	public void guardarEstadoFalloWsAuxLista(List<SolicitudBean> listaSolicitudesDiscapacidad);
}