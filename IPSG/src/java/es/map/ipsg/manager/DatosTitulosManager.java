//package es.map.ipsg.manager;
//
//import java.util.List;
//
//import es.map.ipsg.bean.IntermediacionTituloBean;
//import es.map.ipsg.bean.SolicitudBean;
//import es.map.ipsg.bean.UsuarioBean;
//import es.redsara.intermediacion.scsp.esquemas.ws.peticion.Funcionario;
//import es.redsara.intermediacion.scsp.esquemas.ws.peticion.Procedimiento;
//import es.redsara.intermediacion.scsp.esquemas.ws.peticion.Solicitante;
//
//public interface DatosTitulosManager {
//	public List<IntermediacionTituloBean> consultarTitulosUniversitarios(SolicitudBean solicitudBean, UsuarioBean usuario);
//	public List<IntermediacionTituloBean> consultarTitulosNoUniversitarios(SolicitudBean solicitudBean, UsuarioBean usuario);
//	public void aprobarTituloVerificado(Long idSolicitud);
//	public void rechazarTituloVerificado(Long idSolicitud);
//	public String consultarTitulosUniversitariosAsincrona(Funcionario funcionario,Procedimiento procedimiento,Solicitante solicitante,List<SolicitudBean> listaSolicitudesTitulos, String codigoCertificado, Boolean isTitulosUniv);
//	public String consultarTitulosUniversitarios1990Asincrona(Funcionario funcionario,Procedimiento procedimiento,Solicitante solicitante,List<SolicitudBean> listaSolicitudesTitulos, String codigoCertificado, Boolean isTitulosUniv);
//}
