package es.map.ipsg.bean;

import es.map.ips.model.Comunidades;
import es.map.ips.model.Provincia;
import es.map.ips.model.SolicitudComunAuxiliar;


/**
 * El Class SolicitudCcaaAuxiliarBean.
 */
public class SolicitudCcaaAuxiliarBean {

    /** el id solicitud ccaa. */
    private Long idSolicitudCcaa;
    
    /** el id solicitud. */
    private Long idSolicitud;
    
    /** el solicitud. */
    private SolicitudComunAuxiliar solicitud;
    
    /** el provincia. */
    private Provincia provincia;
    
    /** el codigo provincia. */
    private String codigoProvincia;
    
    /** el id comunidad. */
    private String idComunidad;
    
    /** el codigo comunidad. */
    private String codigoComunidad;
    
    /** el comunidad. */
    private Comunidades comunidad;
    
    /** el titulo famnumerosa. */
    private String tituloFamnumerosa;
    
      
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
	public SolicitudComunAuxiliar getSolicitud() {
		return solicitud;
	}
	
	/**
	 * Establece el solicitud.
	 *
	 * @param solicitud el nuevo solicitud
	 */
	public void setSolicitud(SolicitudComunAuxiliar solicitud) {
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
	 * Obtiene el id comunidad.
	 *
	 * @return el id comunidad
	 */
	public String getIdComunidad() {
		return idComunidad;
	}
	
	/**
	 * Establece el id comunidad.
	 *
	 * @param idComunidad el nuevo id comunidad
	 */
	public void setIdComunidad(String idComunidad) {
		this.idComunidad = idComunidad;
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
	
	/**
	 * Obtiene el codigo comunidad.
	 *
	 * @return el codigo comunidad
	 */
	public String getCodigoComunidad() {
		return codigoComunidad;
	}
	
	/**
	 * Establece el codigo comunidad.
	 *
	 * @param codigoComunidad el nuevo codigo comunidad
	 */
	public void setCodigoComunidad(String codigoComunidad) {
		this.codigoComunidad = codigoComunidad;
	}
	
	/**
	 * Obtiene el codigo provincia.
	 *
	 * @return el codigo provincia
	 */
	public String getCodigoProvincia() {
		return codigoProvincia;
	}
	
	/**
	 * Establece el codigo provincia.
	 *
	 * @param codigoProvincia el nuevo codigo provincia
	 */
	public void setCodigoProvincia(String codigoProvincia) {
		this.codigoProvincia = codigoProvincia;
	}
   
}
    
    
	