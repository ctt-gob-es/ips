package es.map.ipsc.bean;

import es.map.ips.model.Comunidades;
import es.map.ips.model.Provincia;
import es.map.ips.model.SolicitudComun;


/**
 * El Class SolicitudCcaaBean.
 */
public class SolicitudCcaaBean {

    /** el id solicitud ccaa. */
    private Long idSolicitudCcaa;
    
    /** el id solicitud. */
    private Long idSolicitud;
    
    /** el solicitud. */
    private SolicitudComun solicitud;
    
    /** el provincia. */
    private Provincia provincia;
    
    /** el id comunidad DD. */
    private String idComunidadDD;
    
    /** el id comunidad FN. */
    private String idComunidadFN;
    
    /** el descripcion. */
    private String descripcion;
	
	/** el comunidad. */
	private Comunidades comunidad;
    
    /** el titulo famnumerosa. */
    private String tituloFamnumerosa;
    
    
    
    
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
	 * Obtiene el id solicitud ccaa.
	 *
	 * @return el id solicitud ccaa
	 */
	public Long getIdSolicitudCcaa() {
		return idSolicitudCcaa;
	}
	
	/**
	 * Establece el id solicitud ccaa.
	 *
	 * @param idSolicitudCcaa el nuevo id solicitud ccaa
	 */
	public void setIdSolicitudCcaa(Long idSolicitudCcaa) {
		this.idSolicitudCcaa = idSolicitudCcaa;
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
	 * @param idSolicitud el nuevo id solicitud
	 */
	public void setIdSolicitud(Long idSolicitud) {
		this.idSolicitud = idSolicitud;
	}
	
	/**
	 * Obtiene el solicitud.
	 *
	 * @return el solicitud
	 */
	public SolicitudComun getSolicitud() {
		return solicitud;
	}
	
	/**
	 * Establece el solicitud.
	 *
	 * @param solicitud el nuevo solicitud
	 */
	public void setSolicitud(SolicitudComun solicitud) {
		this.solicitud = solicitud;
	}
	
	/**
	 * Obtiene el provincia.
	 *
	 * @return el provincia
	 */
	public Provincia getProvincia() {
		return provincia;
	}
	
	/**
	 * Establece el provincia.
	 *
	 * @param provincia el nuevo provincia
	 */
	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}
	
	/**
	 * Obtiene el id comunidad DD.
	 *
	 * @return el id comunidad DD
	 */
	public String getIdComunidadDD() {
		return idComunidadDD;
	}
	
	/**
	 * Establece el id comunidad DD.
	 *
	 * @param idComunidadDD el nuevo id comunidad DD
	 */
	public void setIdComunidadDD(String idComunidadDD) {
		this.idComunidadDD = idComunidadDD;
	}
	
	/**
	 * Obtiene el id comunidad FN.
	 *
	 * @return el id comunidad FN
	 */
	public String getIdComunidadFN() {
		return idComunidadFN;
	}
	
	/**
	 * Establece el id comunidad FN.
	 *
	 * @param idComunidadFN el nuevo id comunidad FN
	 */
	public void setIdComunidadFN(String idComunidadFN) {
		this.idComunidadFN = idComunidadFN;
	}
	
	/**
	 * Obtiene el comunidad.
	 *
	 * @return el comunidad
	 */
	public Comunidades getComunidad() {
		return comunidad;
	}
	
	/**
	 * Establece el comunidad.
	 *
	 * @param comunidad el nuevo comunidad
	 */
	public void setComunidad(Comunidades comunidad) {
		this.comunidad = comunidad;
	}
	
	/**
	 * Obtiene el titulo famnumerosa.
	 *
	 * @return el titulo famnumerosa
	 */
	public String getTituloFamnumerosa() {
		return tituloFamnumerosa;
	}
	
	/**
	 * Establece el titulo famnumerosa.
	 *
	 * @param tituloFamnumerosa el nuevo titulo famnumerosa
	 */
	public void setTituloFamnumerosa(String tituloFamnumerosa) {
		this.tituloFamnumerosa = tituloFamnumerosa;
	}
  
}
    
    
	