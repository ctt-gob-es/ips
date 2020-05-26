package es.map.ipsg.bean;

import java.util.Date;
import java.util.List;

import es.map.ips.model.SolicitudComun;
import es.map.ips.model.Usuario;

public class BatchNivelRentaBean {

	private Integer idConsulta;
	private String idCodConsulta;
	private Boolean procesado;
	private Date fhInicioConsulta;
	private Date fhFinConsulta;
	private Integer reintentos;
	private Integer numElementos;
	private Usuario idUsuarioConsulta;
	private List<SolicitudComun> solicitudes;
	
	public Integer getIdConsulta() {
		return idConsulta;
	}
	public void setIdConsulta(Integer idConsulta) {
		this.idConsulta = idConsulta;
	}
	public Boolean getProcesado() {
		return procesado;
	}
	public void setProcesado(Boolean procesado) {
		this.procesado = procesado;
	}
	public Date getFhInicioConsulta() {
		return fhInicioConsulta;
	}
	public void setFhInicioConsulta(Date fhInicioConsulta) {
		this.fhInicioConsulta = fhInicioConsulta;
	}
	public Date getFhFinConsulta() {
		return fhFinConsulta;
	}
	public void setFhFinConsulta(Date fhFinConsulta) {
		this.fhFinConsulta = fhFinConsulta;
	}
	public Integer getReintentos() {
		return reintentos;
	}
	public void setReintentos(Integer reintentos) {
		this.reintentos = reintentos;
	}
	
	public Integer getNumElementos() {
		return numElementos;
	}
	public void setNumElementos(Integer numElementos) {
		this.numElementos = numElementos;
	}
	public Usuario getIdUsuarioConsulta() {
		return idUsuarioConsulta;
	}
	public void setIdUsuarioConsulta(Usuario idUsuarioConsulta) {
		this.idUsuarioConsulta = idUsuarioConsulta;
	}
	public String getIdCodConsulta() {
		return idCodConsulta;
	}
	public void setIdCodConsulta(String idCodConsulta) {
		this.idCodConsulta = idCodConsulta;
	}
	public List<SolicitudComun> getSolicitudes() {
		return solicitudes;
	}
	public void setSolicitudes(List<SolicitudComun> solicitudes) {
		this.solicitudes = solicitudes;
	}
	
}
