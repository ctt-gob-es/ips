package es.map.ipsc.bean;

import java.util.ArrayList;

/**
 * El Class BuscarSolicitudesBean.
 */
public class BuscarSolicitudesBean {

	/** el id solicitud. */
	private Long idSolicitud;

	/** el id convocatoria. */
	private Long idConvocatoria;
	
	/** el num justificante. */
	private String numJustificante;
	
	/** el ejercicio solicitud. */
	private String ejercicioSolicitud;
	
	/** el ministerio. */
	private String ministerio;
	
	/** el cuerpo escala. */
	private String cuerpoEscala;
	
	/** el fecha. */
	private String fecha;
	
	/** el estado inscripcion. */
	private String estadoInscripcion;
	
	/** el id estado inscripcion. */
	private Integer idEstadoInscripcion;
	
	/** el eliminar. */
	private boolean eliminar;
	
	/** el submit. */
	private String submit;
	
	/** el num total. */
	private int numTotal;
	
	/** el reanudar. */
	private boolean reanudar;
	
	/** el centro gestor. */
	private String centroGestor;
	
	/** el forma acceso. */
	private String formaAcceso;
	
	/** el grupo. */
	private String grupo;
	
	/** el siglas centro gestor. */
	private String siglasCentroGestor;
	
	/** el num documentos. */
	private long numDocumentos;
	
	/** el documentos. */
	private ArrayList<DocumentoBean> documentos;
	
	/** el justificante. */
	private ArrayList<DocumentoBean> justificante;
	
	/** el modificar. */
	private boolean modificar;
	
	/** el subsanar. */
	private boolean subsanar;
	

	/**
	 * Obtiene el siglas centro gestor.
	 *
	 * @return el siglas centro gestor
	 */
	public String getSiglasCentroGestor() {
		return siglasCentroGestor;
	}

	/**
	 * Establece el siglas centro gestor.
	 *
	 * @param siglasCentroGestor el nuevo siglas centro gestor
	 */
	public void setSiglasCentroGestor(String siglasCentroGestor) {
		this.siglasCentroGestor = siglasCentroGestor;
	}

	/**
	 * Obtiene el centro gestor.
	 *
	 * @return el centro gestor
	 */
	public String getCentroGestor() {
		return centroGestor;
	}

	/**
	 * Establece el centro gestor.
	 *
	 * @param centroGestor el nuevo centro gestor
	 */
	public void setCentroGestor(String centroGestor) {
		this.centroGestor = centroGestor;
	}

	/**
	 * Obtiene el forma acceso.
	 *
	 * @return el forma acceso
	 */
	public String getFormaAcceso() {
		return formaAcceso;
	}

	/**
	 * Establece el forma acceso.
	 *
	 * @param formaAcceso el nuevo forma acceso
	 */
	public void setFormaAcceso(String formaAcceso) {
		this.formaAcceso = formaAcceso;
	}

	/**
	 * Obtiene el grupo.
	 *
	 * @return el grupo
	 */
	public String getGrupo() {
		return grupo;
	}

	/**
	 * Establece el grupo.
	 *
	 * @param grupo el nuevo grupo
	 */
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	/**
	 * Comprueba si es reanudar.
	 *
	 * @return verdadero, si es reanudar
	 */
	public boolean isReanudar() {
		return reanudar;
	}

	/**
	 * Establece el reanudar.
	 *
	 * @param reanudar el nuevo reanudar
	 */
	public void setReanudar(boolean reanudar) {
		this.reanudar = reanudar;
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
	 * Obtiene el submit.
	 *
	 * @return el submit
	 */
	public String getSubmit() {
		return submit;
	}

	/**
	 * Establece el submit.
	 *
	 * @param pSubmit el nuevo submit
	 */
	public void setSubmit(String pSubmit) {
		this.submit = pSubmit;
	}

	/**
	 * Obtiene el id solicitud.
	 *
	 * @return el id solicitud
	 */
	public Long getIdSolicitud() {
		return idSolicitud;
	}

	/**
	 * Establece el id solicitud.
	 *
	 * @param pIdSolicitud el nuevo id solicitud
	 */
	public void setIdSolicitud(Long pIdSolicitud) {
		this.idSolicitud = pIdSolicitud;
	}

	/**
	 * Obtiene el num justificante.
	 *
	 * @return el num justificante
	 */
	public String getNumJustificante() {
		return numJustificante;
	}

	/**
	 * Establece el num justificante.
	 *
	 * @param pNumJustificante el nuevo num justificante
	 */
	public void setNumJustificante(String pNumJustificante) {
		this.numJustificante = pNumJustificante;
	}

	/**
	 * Obtiene el ejercicio solicitud.
	 *
	 * @return el ejercicio solicitud
	 */
	public String getEjercicioSolicitud() {
		return ejercicioSolicitud;
	}

	/**
	 * Establece el ejercicio solicitud.
	 *
	 * @param pEjercicioSolicitud el nuevo ejercicio solicitud
	 */
	public void setEjercicioSolicitud(String pEjercicioSolicitud) {
		this.ejercicioSolicitud = pEjercicioSolicitud;
	}

	/**
	 * Obtiene el ministerio.
	 *
	 * @return el ministerio
	 */
	public String getMinisterio() {
		return ministerio;
	}

	/**
	 * Establece el ministerio.
	 *
	 * @param pMinisterio el nuevo ministerio
	 */
	public void setMinisterio(String pMinisterio) {
		this.ministerio = pMinisterio;
	}

	/**
	 * Obtiene el cuerpo escala.
	 *
	 * @return el cuerpo escala
	 */
	public String getCuerpoEscala() {
		return cuerpoEscala;
	}

	/**
	 * Establece el cuerpo escala.
	 *
	 * @param pCuerpoEscala el nuevo cuerpo escala
	 */
	public void setCuerpoEscala(String pCuerpoEscala) {
		this.cuerpoEscala = pCuerpoEscala;
	}

	/**
	 * Obtiene el fecha.
	 *
	 * @return el fecha
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * Establece el fecha.
	 *
	 * @param pFecha el nuevo fecha
	 */
	public void setFecha(String pFecha) {
		this.fecha = pFecha;
	}

	/**
	 * Obtiene el estado inscripcion.
	 *
	 * @return el estado inscripcion
	 */
	public String getEstadoInscripcion() {
		return estadoInscripcion;
	}

	/**
	 * Establece el estado inscripcion.
	 *
	 * @param pEstadoInscripcion el nuevo estado inscripcion
	 */
	public void setEstadoInscripcion(String pEstadoInscripcion) {
		this.estadoInscripcion = pEstadoInscripcion;
	}
	
	/**
	 * Obtiene el id estado inscripcion.
	 *
	 * @return el id estado inscripcion
	 */
	public Integer getIdEstadoInscripcion() {
		return idEstadoInscripcion;
	}

	/**
	 * Establece el id estado inscripcion.
	 *
	 * @param idEstadoInscripcion el nuevo id estado inscripcion
	 */
	public void setIdEstadoInscripcion(Integer idEstadoInscripcion) {
		this.idEstadoInscripcion = idEstadoInscripcion;
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
	 * @param pEliminar el nuevo eliminar
	 */
	public void setEliminar(boolean pEliminar) {
		this.eliminar = pEliminar;
	}

	/**
	 * Obtiene el id convocatoria.
	 *
	 * @return el id convocatoria
	 */
	public Long getIdConvocatoria() {
		return idConvocatoria;
	}

	/**
	 * Establece el id convocatoria.
	 *
	 * @param idConvocatoria el nuevo id convocatoria
	 */
	public void setIdConvocatoria(Long idConvocatoria) {
		this.idConvocatoria = idConvocatoria;
	}
		
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idConvocatoria == null) ? 0 : idConvocatoria.hashCode());
		result = prime * result
				+ ((idSolicitud == null) ? 0 : idSolicitud.hashCode());
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
		BuscarSolicitudesBean other = (BuscarSolicitudesBean) obj;
		if (idConvocatoria == null) {
			if (other.idConvocatoria != null)
				return false;
		} else if (!idConvocatoria.equals(other.idConvocatoria))
			return false;
		if (idSolicitud == null) {
			if (other.idSolicitud != null)
				return false;
		} else if (!idSolicitud.equals(other.idSolicitud))
			return false;
		return true;
	}
	
	/**
	 * Obtiene el num documentos.
	 *
	 * @return el num documentos
	 */
	public long getNumDocumentos() {
		return numDocumentos;
	}
	
	/**
	 * Establece el num documentos.
	 *
	 * @param numDocumentos el nuevo num documentos
	 */
	public void setNumDocumentos(long numDocumentos) {
		this.numDocumentos = numDocumentos;
	}
	
	/**
	 * Obtiene el documentos.
	 *
	 * @return el documentos
	 */
	public ArrayList<DocumentoBean> getDocumentos() {
		return documentos;
	}
	
	/**
	 * Establece el documentos.
	 *
	 * @param documentos el nuevo documentos
	 */
	public void setDocumentos(ArrayList<DocumentoBean> documentos) {
		this.documentos = documentos;
	}
	
	/**
	 * Obtiene el justificante.
	 *
	 * @return el justificante
	 */
	public ArrayList<DocumentoBean> getJustificante() {
		return justificante;
	}
	
	/**
	 * Establece el justificante.
	 *
	 * @param justificante el nuevo justificante
	 */
	public void setJustificante(ArrayList<DocumentoBean> justificante) {
		this.justificante = justificante;
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
	 * Comprueba si es subsanar.
	 *
	 * @return verdadero, si es subsanar
	 */
	public boolean isSubsanar() {
		return subsanar;
	}

	/**
	 * Establece el subsanar.
	 *
	 * @param subsanar el nuevo subsanar
	 */
	public void setSubsanar(boolean subsanar) {
		this.subsanar = subsanar;
	}
	
	
}