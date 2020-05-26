package es.map.ipsg.bean;

import java.util.HashSet;
import java.util.Set;

import es.map.ips.model.Convocatoria;
import es.map.ips.model.Especialidad;

/**
 * El Class CuerpoEscalaBean.
 */
public class CuerpoEscalaBean {
	
	/** el id. */
	private Integer id;
	
	/** el grupo. */
	private String grupo;
	
	/** el centro gestor. */
	private String centroGestor;
	
	/** el categoria. */
	private String categoria;
	
	/** el codigo. */
	private String codigo;
	
	/** el descripcion. */
	private String descripcion;
	
	/** el estado. */
	private Character estado;
	
	/** el convocatorias. */
	private Set<Convocatoria> convocatorias = new HashSet<Convocatoria>(0);
	
	/** el especialidads. */
	private Set<Especialidad> especialidads = new HashSet<Especialidad>(0);
	
	/** el eliminar. */
	private boolean eliminar;
	
	/** el modificar. */
	private boolean modificar;
	
	/** el detalle. */
	private boolean detalle;
	
	/** el num total. */
	private int numTotal;
	
	/** el num registros. */
	private String numRegistros;
	
	/** el id centro gestor. */
	private Integer idCentroGestor;
	
	/** el des centro gestor. */
	private String desCentroGestor;
	
	/** el id grupo. */
	private Integer idGrupo;
	
	/** el des grupo. */
	private String desGrupo;
	
	/** el id categoria. */
	private Integer idCategoria;
	
	/** el des categoria. */
	private String desCategoria;
	
	/** el visibilidad. */
	private Boolean visibilidad;
	



	/**
	 * Obtiene el num registros.
	 *
	 * @return el num registros
	 */
	public String getNumRegistros() {
		return numRegistros;
	}

	/**
	 * Establece el num registros.
	 *
	 * @param numRegistros el nuevo num registros
	 */
	public void setNumRegistros(String numRegistros) {
		this.numRegistros = numRegistros;
	}
	
	/**
	 * Obtiene el id.
	 *
	 * @return el id
	 */
	public Integer getId() {
		return id;
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
	 * Obtiene el grupo.
	 *
	 * @return the grupo
	 */
	public String getGrupo() {
		return grupo;
	}
	
	/**
	 * Establece el grupo.
	 *
	 * @param grupo the grupo to set
	 */
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}
	
	/**
	 * Obtiene el centro gestor.
	 *
	 * @return the centroGestor
	 */
	public String getCentroGestor() {
		return centroGestor;
	}
	
	/**
	 * Establece el centro gestor.
	 *
	 * @param centroGestor the centroGestor to set
	 */
	public void setCentroGestor(String centroGestor) {
		this.centroGestor = centroGestor;
	}
	
	/**
	 * Obtiene el categoria.
	 *
	 * @return the categoria
	 */
	public String getCategoria() {
		return categoria;
	}
	
	/**
	 * Establece el categoria.
	 *
	 * @param categoria the categoria to set
	 */
	public void setCategoria(String categoria) {
		this.categoria = categoria;
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
	
	/**
	 * Obtiene el especialidads.
	 *
	 * @return el especialidads
	 */
	public Set<Especialidad> getEspecialidads() {
		return especialidads;
	}
	
	/**
	 * Establece el especialidads.
	 *
	 * @param especialidads el nuevo especialidads
	 */
	public void setEspecialidads(Set<Especialidad> especialidads) {
		this.especialidads = especialidads;
	}
	
	/**
	 * Comprueba si es modificar.
	 *
	 * @return verdadero, si es modificar
	 */
	public boolean isModificar() {
		return modificar;
	}

	/**
	 * Establece el modificar.
	 *
	 * @param modificar el nuevo modificar
	 */
	public void setModificar(boolean modificar) {
		this.modificar = modificar;
	}

	/**
	 * Comprueba si es detalle.
	 *
	 * @return verdadero, si es detalle
	 */
	public boolean isDetalle() {
		return detalle;
	}

	/**
	 * Establece el detalle.
	 *
	 * @param detalle el nuevo detalle
	 */
	public void setDetalle(boolean detalle) {
		this.detalle = detalle;
	}

	/**
	 * Comprueba si es eliminar.
	 *
	 * @return verdadero, si es eliminar
	 */
	public boolean isEliminar() {
		return eliminar;
	}

	/**
	 * Establece el eliminar.
	 *
	 * @param eliminar el nuevo eliminar
	 */
	public void setEliminar(boolean eliminar) {
		this.eliminar = eliminar;
	}
	
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
	 * Obtiene el id centro gestor.
	 *
	 * @return the idCentroGestor
	 */
	public Integer getIdCentroGestor() {
		return idCentroGestor;
	}

	/**
	 * Obtiene el des centro gestor.
	 *
	 * @return the desCentroGestor
	 */
	public String getDesCentroGestor() {
		return desCentroGestor;
	}

	/**
	 * Establece el des centro gestor.
	 *
	 * @param desCentroGestor the desCentroGestor to set
	 */
	public void setDesCentroGestor(String desCentroGestor) {
		this.desCentroGestor = desCentroGestor;
	}

	/**
	 * Obtiene el des grupo.
	 *
	 * @return the desGrupo
	 */
	public String getDesGrupo() {
		return desGrupo;
	}

	/**
	 * Establece el des grupo.
	 *
	 * @param desGrupo the desGrupo to set
	 */
	public void setDesGrupo(String desGrupo) {
		this.desGrupo = desGrupo;
	}

	/**
	 * Obtiene el des categoria.
	 *
	 * @return the desCategoria
	 */
	public String getDesCategoria() {
		return desCategoria;
	}

	/**
	 * Establece el des categoria.
	 *
	 * @param desCategoria the desCategoria to set
	 */
	public void setDesCategoria(String desCategoria) {
		this.desCategoria = desCategoria;
	}

	/**
	 * Establece el id centro gestor.
	 *
	 * @param idCentroGestor the idCentroGestor to set
	 */
	public void setIdCentroGestor(Integer idCentroGestor) {
		this.idCentroGestor = idCentroGestor;
	}

	/**
	 * Obtiene el id grupo.
	 *
	 * @return the idGrupo
	 */
	public Integer getIdGrupo() {
		return idGrupo;
	}

	/**
	 * Establece el id grupo.
	 *
	 * @param idGrupo the idGrupo to set
	 */
	public void setIdGrupo(Integer idGrupo) {
		this.idGrupo = idGrupo;
	}

	/**
	 * Obtiene el id categoria.
	 *
	 * @return the idCategoria
	 */
	public Integer getIdCategoria() {
		return idCategoria;
	}

	/**
	 * Establece el id categoria.
	 *
	 * @param idCategoria the idCategoria to set
	 */
	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
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
