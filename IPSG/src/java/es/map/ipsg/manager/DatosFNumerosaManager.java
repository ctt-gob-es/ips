package es.map.ipsg.manager;

import java.util.List;

import es.map.ipsg.bean.ConvocatoriasBean;
import es.map.ipsg.bean.IntermediacionFNumerosaBean;
import es.map.ipsg.bean.SolicitudBean;
import es.map.ipsg.bean.SolicitudCcaaAuxiliarBean;
import es.map.ipsg.bean.SolicitudCcaaBean;
import es.map.ipsg.bean.UsuarioBean;
import es.redsara.intermediacion.scsp.esquemas.ws.peticion.Funcionario;
import es.redsara.intermediacion.scsp.esquemas.ws.peticion.Procedimiento;
import es.redsara.intermediacion.scsp.esquemas.ws.peticion.Solicitante;

/**
 * El Interface DatosFNumerosaManager.
 */
public interface DatosFNumerosaManager {
	
	/**
	 * Validar F numerosa.
	 *
	 * @param convocatoriaBean el convocatoria bean
	 * @param solicitudBean el solicitud bean
	 * @param usuarioBean el usuario bean
	 * @param solicitudCcaaBean el solicitud ccaa bean
	 * @return el intermediacion F numerosa bean
	 */
	public IntermediacionFNumerosaBean validarFNumerosa(ConvocatoriasBean convocatoriaBean,SolicitudBean solicitudBean, UsuarioBean usuarioBean, SolicitudCcaaBean solicitudCcaaBean);
	
	/**
	 * Validar F numerosa auxiliar.
	 *
	 * @param convocatoriaBean el convocatoria bean
	 * @param solicitudBean el solicitud bean
	 * @param usuarioBean el usuario bean
	 * @param solicitudCcaaAuxiliarBean el solicitud ccaa auxiliar bean
	 * @return el intermediacion F numerosa bean
	 */
	public IntermediacionFNumerosaBean validarFNumerosaAuxiliar(ConvocatoriasBean convocatoriaBean,SolicitudBean solicitudBean, UsuarioBean usuarioBean, SolicitudCcaaAuxiliarBean solicitudCcaaAuxiliarBean);
	
	/**
	 * Aprobar F numerosa verificado.
	 *
	 * @param idSolicitud el id solicitud
	 */
	public void aprobarFNumerosaVerificado(Long idSolicitud);
	
	/**
	 * Rechazar F numerosa verificado.
	 *
	 * @param idSolicitud el id solicitud
	 */
	public void rechazarFNumerosaVerificado(Long idSolicitud);
	
	/**
	 * Aprobar F numerosa verificado auxiliar.
	 *
	 * @param idSolicitud el id solicitud
	 */
	public void aprobarFNumerosaVerificadoAuxiliar(Long idSolicitud);
	
	/**
	 * Rechazar F numerosa verificado auxiliar.
	 *
	 * @param idSolicitud el id solicitud
	 */
	public void rechazarFNumerosaVerificadoAuxiliar(Long idSolicitud);
	
	/**
	 * Consultar F numerosa asincrona.
	 *
	 * @param funcionario el funcionario
	 * @param procedimiento el procedimiento
	 * @param solicitante el solicitante
	 * @param listaSolicitudesFNumerosa el lista solicitudes F numerosa
	 * @param listaSolicitudesCcaaFNumerosa el lista solicitudes ccaa F numerosa
	 * @param codigoCertificado el codigo certificado
	 * @return el string
	 */
	public String consultarFNumerosaAsincrona(Funcionario funcionario, Procedimiento procedimiento,Solicitante solicitante, List<SolicitudBean> listaSolicitudesFNumerosa,List<SolicitudCcaaBean> listaSolicitudesCcaaFNumerosa, String codigoCertificado);
	
	/**
	 * Guardar estado fallo ws solicitud.
	 *
	 * @param solicitudBean el solicitud bean
	 */
	public void guardarEstadoFalloWsSolicitud(SolicitudBean solicitudBean);
	
	/**
	 * Guardar estado fallo ws listado.
	 *
	 * @param listaSolicitudesFNumerosa el lista solicitudes F numerosa
	 */
	public void guardarEstadoFalloWsListado(List<SolicitudBean> listaSolicitudesFNumerosa);
	
	/**
	 * Guardar estado pendiente.
	 *
	 * @param idSolicitud el id solicitud
	 */
	public void guardarEstadoPendiente(Long idSolicitud);
	
	/**
	 * Guardar estado pendiente list.
	 *
	 * @param listaSolicitudesFNumerosa el lista solicitudes F numerosa
	 */
	public void guardarEstadoPendienteList(List<SolicitudBean> listaSolicitudesFNumerosa);
	
	/**
	 * Guardar estado fallo ws solicitud auxiliar.
	 *
	 * @param solicitudBean el solicitud bean
	 */
	public void guardarEstadoFalloWsSolicitudAuxiliar(SolicitudBean solicitudBean);
	
	/**
	 * Guardar estado pendiente auxiliar.
	 *
	 * @param idSolicitud el id solicitud
	 */
	void guardarEstadoPendienteAuxiliar(Long idSolicitud);
	
	/**
	 * Guardar estado pendiente list aux.
	 *
	 * @param listaSolicitudesFNumerosa el lista solicitudes F numerosa
	 */
	void guardarEstadoPendienteListAux(List<SolicitudBean> listaSolicitudesFNumerosa);
	
	/**
	 * Guardar estado fallo ws aux listado.
	 *
	 * @param listaSolicitudesFNumerosa el lista solicitudes F numerosa
	 */
	public void guardarEstadoFalloWsAuxListado(List<SolicitudBean> listaSolicitudesFNumerosa);
}
