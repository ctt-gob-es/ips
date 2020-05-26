package es.map.ipsc.bean;

import es.map.ips.model.Comunidades;
import es.map.ips.model.Provincia;
import es.map.ips.model.SolicitudComunAuxiliar;

/**
 * El Class SolicitudCcaaAuxiliarBean.
 */
public class SolicitudCcaaAuxiliarBean {

	/** el id solicitud ccaa. */
	private Long idSolicitudCcaa;
    
    /** el solicitud. */
    private SolicitudComunAuxiliar solicitud;
    
    /** el comunidad. */
    private Comunidades comunidad;
    
    /** el provincia. */
    private Provincia provincia;
    
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
