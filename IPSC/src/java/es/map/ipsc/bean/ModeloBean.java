package es.map.ipsc.bean;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import es.map.ips.model.Alerta;
import es.map.ips.model.CamposPropios;
import es.map.ips.model.CentroGestor;
import es.map.ips.model.CuerpoEscala;
import es.map.ips.model.SolicitudComun;
import es.map.ips.model.Usuario;


/**
 * El Class ModeloBean.
 */
public class ModeloBean {
	
	/** el id. */
	private int id;
	
	/** el num modelo. */
	private String numModelo;
	
	/** el descripcion. */
	private String descripcion;
	
	/** el fecha alta. */
	private Date fechaAlta;	
	
	/** el campos propios. */
	private Set<CamposPropios> camposPropios = new HashSet<CamposPropios>(0);
	
	/** el centros gestores. */
	private Set<CentroGestor> centrosGestores = new HashSet<CentroGestor>(0);
	
	/** el solicitud comun. */
	private Set<SolicitudComun> solicitudComun = new HashSet<SolicitudComun>(0);
	
	/** el num total. */
	private int numTotal;
		
	
	/**
	 * Obtiene el num total.
	 *
	 * @return el num total
	 */
	public int getNumTotal() {
		return numTotal;
	}
	
	/**
	 * Establece el num total.
	 *
	 * @param numTotal el nuevo num total
	 */
	public void setNumTotal(int numTotal) {
		this.numTotal = numTotal;
	}
	
	/**
	 * Obtiene el fecha alta.
	 *
	 * @return el fecha alta
	 */
	public Date getFechaAlta() {
		return fechaAlta;
	}
	
	/**
	 * Establece el fecha alta.
	 *
	 * @param fechaAlta el nuevo fecha alta
	 */
	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	
	/**
	 * Obtiene el campos propios.
	 *
	 * @return el campos propios
	 */
	public Set<CamposPropios> getCamposPropios() {
		return camposPropios;
	}
	
	/**
	 * Establece el campos propios.
	 *
	 * @param camposPropios el nuevo campos propios
	 */
	public void setCamposPropios(Set<CamposPropios> camposPropios) {
		this.camposPropios = camposPropios;
	}
	
	/**
	 * Obtiene el centros gestores.
	 *
	 * @return el centros gestores
	 */
	public Set<CentroGestor> getCentrosGestores() {
		return centrosGestores;
	}
	
	/**
	 * Establece el centros gestores.
	 *
	 * @param centrosGestores el nuevo centros gestores
	 */
	public void setCentrosGestores(Set<CentroGestor> centrosGestores) {
		this.centrosGestores = centrosGestores;
	}
	
	/**
	 * Obtiene el solicitud comun.
	 *
	 * @return el solicitud comun
	 */
	public Set<SolicitudComun> getSolicitudComun() {
		return solicitudComun;
	}
	
	/**
	 * Establece el solicitud comun.
	 *
	 * @param solicitudComun el nuevo solicitud comun
	 */
	public void setSolicitudComun(Set<SolicitudComun> solicitudComun) {
		this.solicitudComun = solicitudComun;
	}
	
	/**
	 * Obtiene el id.
	 *
	 * @return el id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Establece el id.
	 *
	 * @param id el nuevo id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Obtiene el num modelo.
	 *
	 * @return el num modelo
	 */
	public String getNumModelo() {
		return numModelo;
	}
	
	/**
	 * Establece el num modelo.
	 *
	 * @param numModelo el nuevo num modelo
	 */
	public void setNumModelo(String numModelo) {
		this.numModelo = numModelo;
	}
	
	/**
	 * Obtiene el descripcion.
	 *
	 * @return el descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	
	/**
	 * Establece el descripcion.
	 *
	 * @param descripcion el nuevo descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}
