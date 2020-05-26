package es.map.ipsg.bean;

import java.util.HashSet;
import java.util.Set;

import es.map.ips.model.FormaAcceso;
import es.map.ips.model.Tarifa;

/**
 * El Class TipoAccesoBean.
 */
public class TipoAccesoBean {

	/** el id. */
	private Integer id;
	
	/** el codigo. */
	private Character codigo;
	
	/** el forma accesos. */
	private Set<FormaAcceso> formaAccesos = new HashSet<FormaAcceso>(0);
	
	/** el tarifas. */
	private Set<Tarifa> tarifas = new HashSet<Tarifa>(0);
	
	/** el visibilidad. */
	private Boolean visibilidad;
	
	/**
	 * Obtiene el id.
	 *
	 * @return el id
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * Establece el id.
	 *
	 * @param id el nuevo id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Obtiene el codigo.
	 *
	 * @return el codigo
	 */
	public Character getCodigo() {
		return this.codigo;
	}

	/**
	 * Establece el codigo.
	 *
	 * @param codigo el nuevo codigo
	 */
	public void setCodigo(Character codigo) {
		this.codigo = codigo;
	}

	/**
	 * Obtiene el forma accesos.
	 *
	 * @return el forma accesos
	 */
	public Set<FormaAcceso> getFormaAccesos() {
		return this.formaAccesos;
	}

	/**
	 * Establece el forma accesos.
	 *
	 * @param formaAccesos el nuevo forma accesos
	 */
	public void setFormaAccesos(Set<FormaAcceso> formaAccesos) {
		this.formaAccesos = formaAccesos;
	}
	
	/**
	 * Obtiene el tarifas.
	 *
	 * @return el tarifas
	 */
	public Set<Tarifa> getTarifas() {
		return this.tarifas;
	}

	/**
	 * Establece el tarifas.
	 *
	 * @param tarifas el nuevo tarifas
	 */
	public void setTarifas(Set<Tarifa> tarifas) {
		this.tarifas = tarifas;
	}

	/**
	 * Obtiene el visibilidad.
	 *
	 * @return el visibilidad
	 */
	public Boolean getVisibilidad() {
		return visibilidad;
	}

	/**
	 * Establece el visibilidad.
	 *
	 * @param visibilidad el nuevo visibilidad
	 */
	public void setVisibilidad(Boolean visibilidad) {
		this.visibilidad = visibilidad;
	}

}
