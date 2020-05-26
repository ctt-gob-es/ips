package es.map.ipsg.bean;

import java.util.Date;

/**
 * El Class IntermediacionTituloBean.
 */
public class IntermediacionTituloBean {
	
	/** el universidad. */
	//Datos Centro
	String universidad;
	
	/** el centro. */
	String centro;
	
	/** el nombre carrera. */
	private String nombreCarrera;
    
    /** el tipo titulo. */
    private String tipoTitulo;
    
    /** el fecha finalizacion. */
    private Date fechaFinalizacion;
    
	/**
	 * Obtiene el universidad.
	 *
	 * @return el universidad
	 */
	public String getUniversidad() {
		return universidad;
	}
	
	/**
	 * Establece el universidad.
	 *
	 * @param universidad el nuevo universidad
	 */
	public void setUniversidad(String universidad) {
		this.universidad = universidad;
	}
	
	/**
	 * Obtiene el centro.
	 *
	 * @return el centro
	 */
	public String getCentro() {
		return centro;
	}
	
	/**
	 * Establece el centro.
	 *
	 * @param centro el nuevo centro
	 */
	public void setCentro(String centro) {
		this.centro = centro;
	}
	
	/**
	 * Obtiene el nombre carrera.
	 *
	 * @return el nombre carrera
	 */
	public String getNombreCarrera() {
		return nombreCarrera;
	}
	
	/**
	 * Establece el nombre carrera.
	 *
	 * @param nombreCarrera el nuevo nombre carrera
	 */
	public void setNombreCarrera(String nombreCarrera) {
		this.nombreCarrera = nombreCarrera;
	}
	
	/**
	 * Obtiene el tipo titulo.
	 *
	 * @return el tipo titulo
	 */
	public String getTipoTitulo() {
		return tipoTitulo;
	}
	
	/**
	 * Establece el tipo titulo.
	 *
	 * @param tipoTitulo el nuevo tipo titulo
	 */
	public void setTipoTitulo(String tipoTitulo) {
		this.tipoTitulo = tipoTitulo;
	}
	
	/**
	 * Obtiene el fecha finalizacion.
	 *
	 * @return el fecha finalizacion
	 */
	public Date getFechaFinalizacion() {
		return fechaFinalizacion;
	}
	
	/**
	 * Establece el fecha finalizacion.
	 *
	 * @param fechaFinalizacion el nuevo fecha finalizacion
	 */
	public void setFechaFinalizacion(Date fechaFinalizacion) {
		this.fechaFinalizacion = fechaFinalizacion;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((centro == null) ? 0 : centro.hashCode());
		result = prime
				* result
				+ ((fechaFinalizacion == null) ? 0 : fechaFinalizacion
						.hashCode());
		result = prime * result
				+ ((nombreCarrera == null) ? 0 : nombreCarrera.hashCode());
		result = prime * result
				+ ((tipoTitulo == null) ? 0 : tipoTitulo.hashCode());
		result = prime * result
				+ ((universidad == null) ? 0 : universidad.hashCode());
		return result;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IntermediacionTituloBean other = (IntermediacionTituloBean) obj;
		if (centro == null) {
			if (other.centro != null)
				return false;
		} else if (!centro.equals(other.centro))
			return false;
		if (fechaFinalizacion == null) {
			if (other.fechaFinalizacion != null)
				return false;
		} else if (!fechaFinalizacion.equals(other.fechaFinalizacion))
			return false;
		if (nombreCarrera == null) {
			if (other.nombreCarrera != null)
				return false;
		} else if (!nombreCarrera.equals(other.nombreCarrera))
			return false;
		if (tipoTitulo == null) {
			if (other.tipoTitulo != null)
				return false;
		} else if (!tipoTitulo.equals(other.tipoTitulo))
			return false;
		if (universidad == null) {
			if (other.universidad != null)
				return false;
		} else if (!universidad.equals(other.universidad))
			return false;
		return true;
	}

    
}
