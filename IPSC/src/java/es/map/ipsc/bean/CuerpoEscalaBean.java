package es.map.ipsc.bean;

import java.util.HashSet;
import java.util.Set;

import es.map.ips.model.Categoria;
import es.map.ips.model.CentroGestor;
import es.map.ips.model.Grupo;


/**
 * El Class CuerpoEscalaBean.
 */
public class CuerpoEscalaBean {
 
	/** el id. */
	private Long id;
	
	/** el grupo. */
	private Grupo grupo;
	
	/** el categoria. */
	private Categoria categoria;
	
	/** el centro gestor. */
	private CentroGestor centroGestor;
	
	/** el codigo. */
	private String codigo;
	
	/** el descripcion. */
	private String descripcion;
	
	/** el estado. */
	private char estado;
	
	/** el convocatorias. */
	private Set convocatorias = new HashSet(0);
	
	/** el especialidads. */
	private Set especialidads = new HashSet(0);
	
	
	
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
	 * Obtiene el grupo.
	 *
	 * @return el grupo
	 */
	public Grupo getGrupo() {
		return grupo;
	}
	
	/**
	 * Establece el grupo.
	 *
	 * @param grupo el nuevo grupo
	 */
	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}
	
	/**
	 * Obtiene el categoria.
	 *
	 * @return el categoria
	 */
	public Categoria getCategoria() {
		return categoria;
	}
	
	/**
	 * Establece el categoria.
	 *
	 * @param categoria el nuevo categoria
	 */
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	/**
	 * Obtiene el centro gestor.
	 *
	 * @return el centro gestor
	 */
	public CentroGestor getCentroGestor() {
		return centroGestor;
	}
	
	/**
	 * Establece el centro gestor.
	 *
	 * @param centroGestor el nuevo centro gestor
	 */
	public void setCentroGestor(CentroGestor centroGestor) {
		this.centroGestor = centroGestor;
	}
	
	/**
	 * Obtiene el codigo.
	 *
	 * @return el codigo
	 */
	public String getCodigo() {
		return codigo;
	}
	
	/**
	 * Establece el codigo.
	 *
	 * @param codigo el nuevo codigo
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
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
	 * Obtiene el estado.
	 *
	 * @return el estado
	 */
	public char getEstado() {
		return estado;
	}
	
	/**
	 * Establece el estado.
	 *
	 * @param estado el nuevo estado
	 */
	public void setEstado(char estado) {
		this.estado = estado;
	}
	
	/**
	 * Obtiene el convocatorias.
	 *
	 * @return el convocatorias
	 */
	public Set getConvocatorias() {
		return convocatorias;
	}
	
	/**
	 * Establece el convocatorias.
	 *
	 * @param convocatorias el nuevo convocatorias
	 */
	public void setConvocatorias(Set convocatorias) {
		this.convocatorias = convocatorias;
	}
	
	/**
	 * Obtiene el especialidads.
	 *
	 * @return el especialidads
	 */
	public Set getEspecialidads() {
		return especialidads;
	}
	
	/**
	 * Establece el especialidads.
	 *
	 * @param especialidads el nuevo especialidads
	 */
	public void setEspecialidads(Set especialidads) {
		this.especialidads = especialidads;
	}


}