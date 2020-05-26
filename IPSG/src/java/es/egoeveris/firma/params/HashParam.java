package es.egoeveris.firma.params;

import java.io.Serializable;

/**
 * El Class HashParam.
 */
public class HashParam  implements Serializable{
	
	/** La constante serialVersionUID. */
	private static final long serialVersionUID = 6362402499938324054L;

	/** el algoritmo hash. */
	private String algoritmoHash;

	/** el datos. */
	private byte[] datos;
	
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
 	 * Obtiene el algoritmo hash.
 	 *
 	 * @return the algoritmoHash
 	 */
	public String getAlgoritmoHash() {
		return algoritmoHash;
	}

	/**
	 * Establece el algoritmo hash.
	 *
	 * @param algoritmoHash the algoritmoHash to set
	 */
	public void setAlgoritmoHash(String algoritmoHash) {
		this.algoritmoHash = algoritmoHash;
	}
       
        
	/**
	 * Obtiene el datos.
	 *
	 * @return the datos
	 */
	public byte[] getDatos() {
		return datos;
	}

	/**
	 * Establece el datos.
	 *
	 * @param datos the datos to set
	 */
	public void setDatos(byte[] datos) {
		this.datos = datos;
	}
}
