package es.egoeveris.firma.impl.afirma.model.parametrosValCertificado;


/**
 * El Class ParamCert.
 */
public class ParamCert implements java.io.Serializable {

	/** La constante serialVersionUID. */
	private static final long serialVersionUID = -1174377310716521168L;


	/** el certificado. */
	private String certificado;
	
    /** el id aplicacion. */
    private String idAplicacion;

    /** el modovalidacion. */
    private Integer modovalidacion;
    
    /** el obtenerinfo. */
    private Boolean obtenerinfo;


	/**
	 * Obtiene el certificado.
	 *
	 * @return the certificado
	 */
	public String getCertificado() {
		return certificado;
	}

	/**
	 * Establece el certificado.
	 *
	 * @param certificado the certificado to set
	 */
	public void setCertificado(String certificado) {
		this.certificado = certificado;
	}

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
	 * Obtiene el modovalidacion.
	 *
	 * @return the modovalidacion
	 */
	public Integer getModovalidacion() {
		return modovalidacion;
	}

	/**
	 * Establece el modovalidacion.
	 *
	 * @param modovalidacion the modovalidacion to set
	 */
	public void setModovalidacion(Integer modovalidacion) {
		this.modovalidacion = modovalidacion;
	}

	/**
	 * Obtiene el obtenerinfo.
	 *
	 * @return the obtenerinfo
	 */
	public Boolean getObtenerinfo() {
		return obtenerinfo;
	}

	/**
	 * Establece el obtenerinfo.
	 *
	 * @param obtenerinfo the obtenerinfo to set
	 */
	public void setObtenerinfo(Boolean obtenerinfo) {
		this.obtenerinfo = obtenerinfo;
	}

	

}
