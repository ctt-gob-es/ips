package es.egoeveris.firma.impl.afirma.model.parametrosAlmDocumento;

/**
 * El Class ParamDoc.
 */
public class ParamDoc implements java.io.Serializable {


	/** La constante serialVersionUID. */
	private static final long serialVersionUID = -8949438933219415587L;

	/** el id aplicacion. */
	private String idAplicacion;
	
    /** el nombre documento. */
    private String nombreDocumento;
    
    /** el tipo documento. */
    private String tipoDocumento;

    /** el documento. */
    private String documento;
	
	/**
	 * Obtiene el nombre documento.
	 *
	 * @return the nombreDocumento
	 */
	public String getNombreDocumento() {
		return nombreDocumento;
	}

	/**
	 * Establece el nombre documento.
	 *
	 * @param nombreDocumento the nombreDocumento to set
	 */
	public void setNombreDocumento(String nombreDocumento) {
		this.nombreDocumento = nombreDocumento;
	}

	/**
	 * Obtiene el tipo documento.
	 *
	 * @return the tipoDocumento
	 */
	public String getTipoDocumento() {
		return tipoDocumento;
	}

	/**
	 * Establece el tipo documento.
	 *
	 * @param tipoDocumento the tipoDocumento to set
	 */
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	/**
	 * Obtiene el documento.
	 *
	 * @return the documento
	 */
	public String getDocumento() {
		return documento;
	}

	/**
	 * Establece el documento.
	 *
	 * @param documento the documento to set
	 */
	public void setDocumento(String documento) {
		this.documento = documento;
	}

	/**
	 * Obtiene el id aplicacion.
	 *
	 * @return the idAplicacion
	 */
	public String getIdAplicacion() {
		return idAplicacion;
	}

	/**
	 * Establece el id aplicacion.
	 *
	 * @param idAplicacion the idAplicacion to set
	 */
	public void setIdAplicacion(String idAplicacion) {
		this.idAplicacion = idAplicacion;
	}

	

}
