package es.map.ipsg.bean;

import java.util.ArrayList;

/**
 * PaisBean.
 *
 * @author amartinl
 */
public class PaisBean {
	
	/** el id. */
	private Integer id;
	
	/** el descripcion. */
	private String descripcion;
	
	/** el codigo. */
	private String codigo;
	
	/** el solicitudes. */
	private ArrayList<SolicitudBean> solicitudes = new ArrayList<SolicitudBean>();
	
	
	/**
	 * Obtiene el id.
	 *
	 * @return id Integer
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * Establece el id.
	 *
	 * @param id Integer
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * Obtiene el descripcion.
	 *
	 * @return descripcion String
	 */
	public String getDescripcion() {
		return descripcion;
	}
	
	/**
	 * Establece el descripcion.
	 *
	 * @param descripcion String
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	/**
	 * Obtiene el codigo.
	 *
	 * @return  codigo String
	 */
	public String getCodigo() {
		return codigo;
	}
	
	/**
	 * Establece el codigo.
	 *
	 * @param codigo String
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	/**
	 * Obtiene el solicitudes.
	 *
	 * @return solicitudes ArrayList<SolicitudBean>
	 */
	public ArrayList<SolicitudBean> getSolicitudes() {
		return solicitudes;
	}
	
	/**
	 * Establece el solicitudes.
	 *
	 * @param solicitudes ArrayList<SolicitudBean>
	 */
	public void setSolicitudes(ArrayList<SolicitudBean> solicitudes) {
		this.solicitudes = solicitudes;
	}

	
}
