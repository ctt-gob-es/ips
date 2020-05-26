package es.map.ipsg.bean;

import java.util.Date;

/**
 * El Class UsuarioAplicacionBean.
 */
public class UsuarioAplicacionBean {

	/** el id. */
	private Integer id;
	
	/** el id perfil. */
	private Integer idPerfil;
	
	/** el nombre. */
	private String nombre;
	
	/** el usuario. */
	private String usuario;
	
	/** el descripcion. */
	private String descripcion;
	
	/** el estado. */
	private Character estado;
	
	/** el des estado. */
	private String desEstado;
	
	/** el fecha. */
	private Date fecha;
	
	/** el num total. */
	private int numTotal;


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
	 * Obtiene el nombre.
	 *
	 * @return el nombre
	 */
	public String getNombre() {
		return nombre;
	}


	/**
	 * Establece el nombre.
	 *
	 * @param nombre el nuevo nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
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
		if (estado != null) {
			if ("A".compareTo(estado.toString()) == 0) {
				setDesEstado("ACTIVO");
			} else if ("D".compareTo(estado.toString()) == 0) {
				setDesEstado("DESACTIVADO");
			} else {
				setDesEstado("");
			}
		} else {
			setDesEstado("");
		}
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
	 * Obtiene el usuario.
	 *
	 * @return el usuario
	 */
	public String getUsuario() {
		return usuario;
	}


	/**
	 * Establece el usuario.
	 *
	 * @param usuario el nuevo usuario
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}


	/**
	 * Obtiene el des estado.
	 *
	 * @return el des estado
	 */
	public String getDesEstado() {
		return desEstado;
	}


	/**
	 * Establece el des estado.
	 *
	 * @param desEstado el nuevo des estado
	 */
	public void setDesEstado(String desEstado) {
		this.desEstado = desEstado;
	}


	/**
	 * Obtiene el id perfil.
	 *
	 * @return el id perfil
	 */
	public Integer getIdPerfil() {
		return idPerfil;
	}


	/**
	 * Establece el id perfil.
	 *
	 * @param idPerfil el nuevo id perfil
	 */
	public void setIdPerfil(Integer idPerfil) {
		this.idPerfil = idPerfil;
	}


	/**
	 * Obtiene el fecha.
	 *
	 * @return el fecha
	 */
	public Date getFecha() {
		return fecha;
	}


	/**
	 * Establece el fecha.
	 *
	 * @param fecha el nuevo fecha
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}	
}
