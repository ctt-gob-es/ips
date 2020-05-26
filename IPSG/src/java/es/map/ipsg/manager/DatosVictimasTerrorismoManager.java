package es.map.ipsg.manager;

import java.util.List;

import es.map.ipsg.bean.IntermediacionVictimasTerrorismoBean;
import es.map.ipsg.bean.SolicitudBean;
import es.redsara.intermediacion.scsp.esquemas.ws.peticion.Procedimiento;
import es.redsara.intermediacion.scsp.esquemas.ws.peticion.Solicitante;

public interface DatosVictimasTerrorismoManager {
	public IntermediacionVictimasTerrorismoBean consultaVictimaTerrorismo(SolicitudBean solicitudBean, String nifFuncionario, String nombreFuncionario, String unidadTramitadora);
	public String consultarVictimasAsincrona(Procedimiento procedimiento,Solicitante solicitantePeticion, List<SolicitudBean> listaSolicitudesVictimas, String codigoCertificado);
	public void aprobarVictimasVerificado(Long idSolicitud);
	public void aprobarVictimasVerificadoAuxiliar(Long idSolicitud);
	public void rechazarVictimasVerificado(Long idSolicitud);
	public void rechazarVictimasVerificadoAuxiliar(Long idSolicitud);
	public void guardarEstadoPendienteList(List<SolicitudBean> listaSolicitudesVictimas);
	public void guardarEstadoPendienteListAux(List<SolicitudBean> listaSolicitudesVictimas);
	public void guardarEstadoPendiente(Long idSolicitud);
	public void guardarEstadoPendienteAuxiliar(Long idSolicitud);
	public void guardarEstadoFalloWsLista(List<SolicitudBean> listaSolicitudesVictima);
}
