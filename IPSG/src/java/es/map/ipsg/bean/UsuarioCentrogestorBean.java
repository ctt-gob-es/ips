package es.map.ipsg.bean;

import java.util.List;

// Generated 21-may-2014 11:23:18 by Hibernate Tools 3.4.0.CR1

public class UsuarioCentrogestorBean{

	/** el id solicitud. */
	private Integer idRegistro;
	
	/** el provincia by id provincia domicilio. */
	private Integer idUsuario;

	/** el provincia by id provincia nacimiento. */
	private List<CentroGestorBean> centroGestor;

	/**
	 * @return the idRegistro
	 */
	public Integer getIdRegistro() {
		return idRegistro;
	}

	/**
	 * @param idRegistro the idRegistro to set
	 */
	public void setIdRegistro(Integer idRegistro) {
		this.idRegistro = idRegistro;
	}

	/**
	 * @return the idUsuario
	 */
	public Integer getIdUsuario() {
		return idUsuario;
	}

	/**
	 * @param idUsuario the idUsuario to set
	 */
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	/**
	 * @return the centroGestor
	 */
	public List<CentroGestorBean> getCentroGestor() {
		return centroGestor;
	}

	/**
	 * @param centroGestor the centroGestor to set
	 */
	public void setCentroGestor(List<CentroGestorBean> centroGestor) {
		this.centroGestor = centroGestor;
	}
	

}
