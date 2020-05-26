package es.map.ipsg.bean;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.mail.internet.InternetAddress;

import es.map.ips.model.Alerta;
import es.map.ips.model.Documento;
import es.map.ips.model.Incidencia;
import es.map.ips.model.SolicitudComun;


/**
 * El Class CorreoElectronicoBean.
 */
public class CorreoElectronicoBean {
	
	/** el id. */
	private Long id;
	
	/** el de. */
	private String de;
	
	/** el para. */
	private String para;
	
	/** el mensaje. */
	private String mensaje;
	
	/** el asunto. */
	private String asunto;
	
	/** el fecha envio. */
	private Date fechaEnvio;
	
	/** el estado. */
	private Character estado;
	
	/** el id solicitud. */
	private Long[] idSolicitud;
	
	/** el id alertas. */
	private Set<Integer> idAlertas = new HashSet<Integer>(0);
	
	/** el solicituds. */
	private Set<SolicitudComun> solicituds = new HashSet<SolicitudComun>(0);
	
	/** el alertas. */
	private Set<Alerta> alertas = new HashSet<Alerta>(0);
	
	/** el incidencias. */
	private Set<Incidencia> incidencias = new HashSet<Incidencia>(0);
	
	/** el documentos. */
	private Set<Documento> documentos = new HashSet<Documento>(0);
	
	/** el bcc. */
	private InternetAddress [] bcc = new InternetAddress[0];
	
	/** el adjuntos. */
	private ArrayList <byte[]> adjuntos = new ArrayList <byte[]>();
	
	/** el adjuntos file. */
	private ArrayList <File> adjuntosFile = new ArrayList <File>();
	
	
	/**
	 * Obtiene el id.
	 *
	 * @return el id
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * Establece el id.
	 *
	 * @param id el nuevo id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * Obtiene el de.
	 *
	 * @return el de
	 */
	public String getDe() {
		return de;
	}
	
	/**
	 * Establece el de.
	 *
	 * @param de el nuevo de
	 */
	public void setDe(String de) {
		this.de = de;
	}
	
	/**
	 * Obtiene el para.
	 *
	 * @return el para
	 */
	public String getPara() {
		return para;
	}
	
	/**
	 * Establece el para.
	 *
	 * @param para el nuevo para
	 */
	public void setPara(String para) {
		this.para = para;
	}
	
	/**
	 * Obtiene el mensaje.
	 *
	 * @return el mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}
	
	/**
	 * Establece el mensaje.
	 *
	 * @param mensaje el nuevo mensaje
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	/**
	 * Obtiene el asunto.
	 *
	 * @return el asunto
	 */
	public String getAsunto() {
		return asunto;
	}
	
	/**
	 * Establece el asunto.
	 *
	 * @param asunto el nuevo asunto
	 */
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}
	
	/**
	 * Obtiene el fecha envio.
	 *
	 * @return el fecha envio
	 */
	public Date getFechaEnvio() {
		return fechaEnvio;
	}
	
	/**
	 * Establece el fecha envio.
	 *
	 * @param fechaEnvio el nuevo fecha envio
	 */
	public void setFechaEnvio(Date fechaEnvio) {
		this.fechaEnvio = fechaEnvio;
	}
	
	/**
	 * Obtiene el estado.
	 *
	 * @return el estado
	 */
	public Character getEstado() {
		return estado;
	}
	
	/**
	 * Establece el estado.
	 *
	 * @param estado el nuevo estado
	 */
	public void setEstado(Character estado) {
		this.estado = estado;
	}
	
	/**
	 * Obtiene el id solicitud.
	 *
	 * @return the idSolicitud
	 */
	public Long[] getIdSolicitud() {
		return idSolicitud;
	}
	
	/**
	 * Establece el id solicitud.
	 *
	 * @param idSolicitud the idSolicitud to set
	 */
	public void setIdSolicitud(Long[] idSolicitud) {
		this.idSolicitud = idSolicitud;
	}
	
	/**
	 * Obtiene el solicituds.
	 *
	 * @return el solicituds
	 */
	public Set<SolicitudComun> getSolicituds() {
		return solicituds;
	}
	
	/**
	 * Establece el solicituds.
	 *
	 * @param solicituds el nuevo solicituds
	 */
	public void setSolicituds(Set<SolicitudComun> solicituds) {
		this.solicituds = solicituds;
	}
	
	/**
	 * Obtiene el alertas.
	 *
	 * @return el alertas
	 */
	public Set<Alerta> getAlertas() {
		return alertas;
	}
	
	/**
	 * Establece el alertas.
	 *
	 * @param alertas el nuevo alertas
	 */
	public void setAlertas(Set<Alerta> alertas) {
		this.alertas = alertas;
	}
	
	/**
	 * Obtiene el incidencias.
	 *
	 * @return el incidencias
	 */
	public Set<Incidencia> getIncidencias() {
		return incidencias;
	}
	
	/**
	 * Establece el incidencias.
	 *
	 * @param incidencias el nuevo incidencias
	 */
	public void setIncidencias(Set<Incidencia> incidencias) {
		this.incidencias = incidencias;
	}
	
	/**
	 * Obtiene el documentos.
	 *
	 * @return el documentos
	 */
	public Set<Documento> getDocumentos() {
		return documentos;
	}
	
	/**
	 * Establece el documentos.
	 *
	 * @param documentos el nuevo documentos
	 */
	public void setDocumentos(Set<Documento> documentos) {
		this.documentos = documentos;
	}
	
	/**
	 * Obtiene el id alertas.
	 *
	 * @return the idAlertas
	 */
	public Set<Integer> getIdAlertas() {
		return idAlertas;
	}
	
	/**
	 * Establece el id alertas.
	 *
	 * @param idAlertas the idAlertas to set
	 */
	public void setIdAlertas(Set<Integer> idAlertas) {
		this.idAlertas = idAlertas;
	}
	
	/**
	 * Obtiene el bcc.
	 *
	 * @return the bcc
	 */
	public InternetAddress[] getBcc() {
		return bcc;
	}
	
	/**
	 * Establece el bcc.
	 *
	 * @param bcc the bcc to set
	 */
	public void setBcc(InternetAddress[] bcc) {
		this.bcc = bcc;
	}
	
	/**
	 * Obtiene el adjuntos.
	 *
	 * @return the adjuntos
	 */
	public ArrayList<byte[]> getAdjuntos() {
		return adjuntos;
	}
	
	/**
	 * Establece el adjuntos.
	 *
	 * @param adjuntos the adjuntos to set
	 */
	public void setAdjuntos(ArrayList<byte[]> adjuntos) {
		this.adjuntos = adjuntos;
	}
	
	/**
	 * Obtiene el adjuntos file.
	 *
	 * @return the adjuntosFile
	 */
	public ArrayList<File> getAdjuntosFile() {
		return adjuntosFile;
	}
	
	/**
	 * Establece el adjuntos file.
	 *
	 * @param adjuntosFile the adjuntosFile to set
	 */
	public void setAdjuntosFile(ArrayList<File> adjuntosFile) {
		this.adjuntosFile = adjuntosFile;
	}
	
}
