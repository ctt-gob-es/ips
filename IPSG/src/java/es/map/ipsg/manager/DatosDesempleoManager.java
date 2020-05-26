package es.map.ipsg.manager;


import java.util.List;

import es.map.ipsg.bean.ConvocatoriasBean;
import es.map.ipsg.bean.IntermediacionDesempleoBean;
import es.map.ipsg.bean.SolicitudBean;
import es.map.ipsg.bean.UsuarioBean;
import es.redsara.intermediacion.scsp.esquemas.ws.peticion.Funcionario;
import es.redsara.intermediacion.scsp.esquemas.ws.peticion.Procedimiento;
import es.redsara.intermediacion.scsp.esquemas.ws.peticion.Solicitante;

/**
 * El Interface DatosDesempleoManager.
 */
public interface DatosDesempleoManager {
	
	/**
	 * Validar desempleo salario.
	 *
	 * @param convocatoriaBean el convocatoria bean
	 * @param solicitudBean el solicitud bean
	 * @param usuarioBean el usuario bean
	 * @param b 
	 * @param string 
	 * @return el intermediacion desempleo bean
	 */
	public IntermediacionDesempleoBean validarDesempleoSalario(ConvocatoriasBean convocatoriaBean,SolicitudBean solicitudBean, UsuarioBean usuarioBean, boolean b, String string);
	
	/**
	 * Aprobar desempleo verificado.
	 *
	 * @param idSolicitud el id solicitud
	 */
	public void aprobarDesempleoVerificado(Long idSolicitud);
	
	/**
	 * Aprobar desempleo verificado auxiliar.
	 *
	 * @param idSolicitud el id solicitud
	 */
	public void aprobarDesempleoVerificadoAuxiliar(Long idSolicitud);
	
	/**
	 * Rechazar desempleo verificado.
	 *
	 * @param idSolicitud el id solicitud
	 */
	public void rechazarDesempleoVerificado(Long idSolicitud);
	
	/**
	 * Rechazar desempleo verificado auxiliar.
	 *
	 * @param idSolicitud el id solicitud
	 */
	public void rechazarDesempleoVerificadoAuxiliar(Long idSolicitud);
	
	/**
	 * Consultar desempleo dias asincrona.
	 *
	 * @param funcionario el funcionario
	 * @param procedimiento el procedimiento
	 * @param solicitante el solicitante
	 * @param listaSolicitudesDesempleo el lista solicitudes desempleo
	 * @param codigoCertificado el codigo certificado
	 * @return el string
	 */
	public String consultarDesempleoDiasAsincrona(Funcionario funcionario,Procedimiento procedimiento,Solicitante solicitante,List<SolicitudBean> listaSolicitudesDesempleo, String codigoCertificado);
	
	/**
	 * Consultar desempleo importe asincrona.
	 *
	 * @param funcionario el funcionario
	 * @param procedimiento el procedimiento
	 * @param solicitante el solicitante
	 * @param listaSolicitudesDesempleo el lista solicitudes desempleo
	 * @param codigoCertificado el codigo certificado
	 * @return el string
	 */
	public String consultarDesempleoImporteAsincrona(Funcionario funcionario,Procedimiento procedimiento,Solicitante solicitante,List<SolicitudBean> listaSolicitudesDesempleo, String codigoCertificado);
	
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
	 * Guardar estado fallo ws.
	 *
	 * @param listaSolicitudesDesempleo el lista solicitudes desempleo
	 */
	public void guardarEstadoFalloWs(List<SolicitudBean> listaSolicitudesDesempleo);
	
	/**
	 * Guardar estado pendiente.
	 *
	 * @param idSolicitud el id solicitud
	 */
	public void guardarEstadoPendiente(Long idSolicitud);
	
	/**
	 * Guardar estado pendiente list.
	 *
	 * @param listaSolicitudesDesempleo el lista solicitudes desempleo
	 */
	public void guardarEstadoPendienteList(List<SolicitudBean> listaSolicitudesDesempleo);
	
	/**
	 * Guardar estado pendiente auxiliar.
	 *
	 * @param idSolicitud el id solicitud
	 */
	void guardarEstadoPendienteAuxiliar(Long idSolicitud);
	
	/**
	 * Guardar estado fallo ws aux.
	 *
	 * @param listaSolicitudesDesempleo el lista solicitudes desempleo
	 */
	public void guardarEstadoFalloWsAux(List<SolicitudBean> listaSolicitudesDesempleo);
	
	/**
	 * Guardar estado pendiente list aux.
	 *
	 * @param listaSolicitudesDesempleo el lista solicitudes desempleo
	 */
	public void guardarEstadoPendienteListAux(List<SolicitudBean> listaSolicitudesDesempleo);
}
