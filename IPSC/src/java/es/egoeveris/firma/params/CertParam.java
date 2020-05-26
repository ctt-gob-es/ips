package es.egoeveris.firma.params;

import java.io.Serializable;

/**
 * El Class CertParam.
 */
public class CertParam implements Serializable{

	/** La constante serialVersionUID. */
	private static final long serialVersionUID = 7068765183767084213L;

    /** el cert cadena. */
    private String certCadena;
    
    /** el obtener info. */
    private boolean obtenerInfo;
        
    /** el id aplicacion. */
    private String idAplicacion;
    
    /** el ticket. */
    private String ticket;
    
    /**
     * Obtiene el id aplicacion.
     *
     * @return the idaplicacion
     */
	public String getIdAplicacion() {
		return idAplicacion;
	}

	/**
	 * Establece el id aplicacion.
	 *
	 * @param idAplicacion el nuevo id aplicacion
	 */
	public void setIdAplicacion(String idAplicacion) {
		this.idAplicacion = idAplicacion;
	}
        
    /**
     * Obtiene el cert cadena.
     *
     * @return the certCadena
     */
	public String getCertCadena() {
		return certCadena;
	}
    
	/**
	 * Establece el cert cadena.
	 *
	 * @param certCadena the certCadena to set
	 */
	public void setCertCadena(String certCadena) {
		this.certCadena = certCadena;
	}
        
	/**
	 * Obtiene el ticket.
	 *
	 * @return the ticket
	 */
	public String getTicket() {
		return ticket;
	}

	/**
	 * Establece el ticket.
	 *
	 * @param ticket the ticket to set
	 */
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
		
	/**
	 * Obtiene el obtener info.
	 *
	 * @return the obtenerInfo
	 */
	public boolean getObtenerInfo() {
		return obtenerInfo;
	}

	/**
	 * Establece el obtener info.
	 *
	 * @param obtenerInfo the obtenerInfo to set
	 */
	public void setObtenerInfo(boolean obtenerInfo) {
		this.obtenerInfo = obtenerInfo;
	}	
}
