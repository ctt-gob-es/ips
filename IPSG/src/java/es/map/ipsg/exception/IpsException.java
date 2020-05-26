package es.map.ipsg.exception;


/**
 * El Class IpsException.
 */
public class IpsException extends RuntimeException{
	
	/** La constante serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** el ds titulo. */
	private String dsTitulo;
	
	/** el ds descripcion especifica. */
	private String dsDescripcionEspecifica;
	
	/**
	 * Crea una nueva ips exception.
	 */
	public IpsException() {
		super();
	}
	
	/**
	 * Crea una nueva ips exception.
	 *
	 * @param message el message
	 */
	public IpsException(String message) {
		super(message);
	}
	
	/**
	 * Crea una nueva ips exception.
	 *
	 * @param message el message
	 * @param dsTitulo el ds titulo
	 * @param dsDescripcionEspecifica el ds descripcion especifica
	 */
	public IpsException(String message, String dsTitulo, String dsDescripcionEspecifica) {
		super(message);
		this.dsTitulo = dsTitulo;
		this.dsDescripcionEspecifica = dsDescripcionEspecifica;
	}
	
    /**
     * Crea una nueva ips exception.
     *
     * @param message el message
     * @param cause el cause
     */
    public IpsException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Crea una nueva ips exception.
     *
     * @param cause el cause
     */
    public IpsException(Throwable cause) {
        super(cause);
    }

	/**
	 * Obtiene el ds titulo.
	 *
	 * @return el ds titulo
	 */
	public String getDsTitulo() {
		return dsTitulo;
	}

	/**
	 * Establece el ds titulo.
	 *
	 * @param dsTitulo el nuevo ds titulo
	 */
	public void setDsTitulo(String dsTitulo) {
		this.dsTitulo = dsTitulo;
	}
	
	/**
	 * Obtiene el ds descripcion especifica.
	 *
	 * @return el ds descripcion especifica
	 */
	public String getDsDescripcionEspecifica() {
		return dsDescripcionEspecifica;
	}

	/**
	 * Establece el ds descripcion especifica.
	 *
	 * @param dsDescripcionEspecifica el nuevo ds descripcion especifica
	 */
	public void setDsDescripcionEspecifica(String dsDescripcionEspecifica) {
		this.dsDescripcionEspecifica = dsDescripcionEspecifica;
	}

    
    
}
