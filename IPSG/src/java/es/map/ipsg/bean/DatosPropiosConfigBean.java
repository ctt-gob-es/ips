package es.map.ipsg.bean;

import java.util.HashSet;
import java.util.Set;

import es.map.ips.model.CamposPropios;
import es.map.ips.model.Convocatoria;

/**
 * El Class DatosPropiosConfigBean.
 */
public class DatosPropiosConfigBean {

	/** el id desplegable. */
	private Long idDesplegable;
	
	/** el campo. */
	private CamposPropios campo;
	
	/** el descripcion. */
	private String descripcion;
	
	/** el convocatorias. */
	private Set<Convocatoria> convocatorias = new HashSet<Convocatoria>(0);
	
	
	/**
	 * Obtiene el id desplegable.
	 *
	 * @return el id desplegable
	 */
	public Long getIdDesplegable() {
		return idDesplegable;
	}
	
	/**
	 * Establece el id desplegable.
	 *
	 * @param idDesplegable el nuevo id desplegable
	 */
	public void setIdDesplegable(Long idDesplegable) {
		this.idDesplegable = idDesplegable;
	}
	
	/**
	 * Obtiene el campo.
	 *
	 * @return el campo
	 */
	public CamposPropios getCampo() {
		return campo;
	}
	
	/**
	 * Establece el campo.
	 *
	 * @param campo el nuevo campo
	 */
	public void setCampo(CamposPropios campo) {
		this.campo = campo;
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
	
	/**
	 * Obtiene el convocatorias.
	 *
	 * @return el convocatorias
	 */
	public Set<Convocatoria> getConvocatorias() {
		return convocatorias;
	}
	
	/**
	 * Establece el convocatorias.
	 *
	 * @param convocatorias el nuevo convocatorias
	 */
	public void setConvocatorias(Set<Convocatoria> convocatorias) {
		this.convocatorias = convocatorias;
	}

}
