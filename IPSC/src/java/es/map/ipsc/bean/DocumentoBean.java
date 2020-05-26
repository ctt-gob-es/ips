package es.map.ipsc.bean;

import java.util.Date;

import es.map.ips.model.TipoDocumento;


/**
 * El Class DocumentoBean.
 */
public class DocumentoBean {
	
	/** el id. */
	private Long id;
	
	/** el tipo documento. */
	private TipoDocumento tipoDocumento;
	
	/** el id tipo documento. */
	private String idTipoDocumento;
	
	/** el nombre. */
	private String nombre;
	
	/** el fecha creacion. */
	private Date fechaCreacion;
	
	/** el descripcion. */
	private String descripcion;
	
	/** el tamano. */
	private int tamano;
	
	/** el nombre alfresco. */
	private String nombreAlfresco;
	
	/** el id solicitud. */
	private Long idSolicitud;
	
	/** el id convocatoria. */
	private Long idConvocatoria;
	
	/** el id correo electronico. */
	private Long idCorreoElectronico;
	
	/** el contenido documento. */
	private byte[] contenidoDocumento;
	
	/** el extension. */
	private String extension;
	
	/** el entorno. */
	private String entorno;
	
	/** el ubicacion. */
	private String ubicacion;
	
	/** el cd documento. */
	private Integer cdDocumento;
	
	/** el cd tipo documento. */
	private String cdTipoDocumento;
	
	/** el ds nombre documento. */
	private String dsNombreDocumento;
	
	/** el ds tipo documento. */
	private String dsTipoDocumento;
	
	/** el ds tipo documento aux. */
	private String dsTipoDocumentoAux;
	
	/** el ds extracto. */
	private String dsExtracto;
	
	/** el byte contenido. */
	private byte[] byteContenido;
	
	/** el ds ruta documento. */
	private String dsRutaDocumento;
	
	/** el length documento. */
	private Long lengthDocumento;
	
	/** el ds nombre original documento. */
	private String dsNombreOriginalDocumento;
	
	/** el ds extension documento. */
	private String dsExtensionDocumento;
	
	/** el fe alta. */
	private Date feAlta;
	
	/** el es firma. */
	private boolean esFirma;
	
	/** el cd documento firmado. */
	private Integer cdDocumentoFirmado;
	
	/** el hash extracto. */
	private String hashExtracto;
	
	/** el it documento principal. */
	private String itDocumentoPrincipal;
	
	/** el cd entrada. */
	private Integer cdEntrada;
	
	/** el cd salida. */
	private Integer cdSalida;
	
	/** el tl huella digital. */
	private String tlHuellaDigital; 
	
	/** el csv. */
	private String csv;
	
	/**
	 * Obtiene el id tipo documento.
	 *
	 * @return el id tipo documento
	 */
	public String getIdTipoDocumento() {
		return idTipoDocumento;
	}

	/**
	 * Establece el id tipo documento.
	 *
	 * @param idTipoDocumento el nuevo id tipo documento
	 */
	public void setIdTipoDocumento(String idTipoDocumento) {
		this.idTipoDocumento = idTipoDocumento;
	}

	/**
	 * Obtiene el cd documento.
	 *
	 * @return el cd documento
	 */
	public Integer getCdDocumento() {
		return cdDocumento;
	}

	/**
	 * Establece el cd documento.
	 *
	 * @param cdDocumento el nuevo cd documento
	 */
	public void setCdDocumento(Integer cdDocumento) {
		this.cdDocumento = cdDocumento;
	}

	/**
	 * Obtiene el cd tipo documento.
	 *
	 * @return el cd tipo documento
	 */
	public String getCdTipoDocumento() {
		return cdTipoDocumento;
	}

	/**
	 * Establece el cd tipo documento.
	 *
	 * @param cdTipoDocumento el nuevo cd tipo documento
	 */
	public void setCdTipoDocumento(String cdTipoDocumento) {
		this.cdTipoDocumento = cdTipoDocumento;
	}

	/**
	 * Obtiene el ds nombre documento.
	 *
	 * @return el ds nombre documento
	 */
	public String getDsNombreDocumento() {
		return dsNombreDocumento;
	}

	/**
	 * Establece el ds nombre documento.
	 *
	 * @param dsNombreDocumento el nuevo ds nombre documento
	 */
	public void setDsNombreDocumento(String dsNombreDocumento) {
		this.dsNombreDocumento = dsNombreDocumento;
	}

	/**
	 * Obtiene el ds tipo documento.
	 *
	 * @return el ds tipo documento
	 */
	public String getDsTipoDocumento() {
		return dsTipoDocumento;
	}

	/**
	 * Establece el ds tipo documento.
	 *
	 * @param dsTipoDocumento el nuevo ds tipo documento
	 */
	public void setDsTipoDocumento(String dsTipoDocumento) {
		this.dsTipoDocumento = dsTipoDocumento;
	}

	/**
	 * Obtiene el ds tipo documento aux.
	 *
	 * @return el ds tipo documento aux
	 */
	public String getDsTipoDocumentoAux() {
		return dsTipoDocumentoAux;
	}

	/**
	 * Establece el ds tipo documento aux.
	 *
	 * @param dsTipoDocumentoAux el nuevo ds tipo documento aux
	 */
	public void setDsTipoDocumentoAux(String dsTipoDocumentoAux) {
		this.dsTipoDocumentoAux = dsTipoDocumentoAux;
	}

	/**
	 * Obtiene el byte contenido.
	 *
	 * @return el byte contenido
	 */
	public byte[] getByteContenido() {
		return byteContenido;
	}

	/**
	 * Establece el byte contenido.
	 *
	 * @param byteContenido el nuevo byte contenido
	 */
	public void setByteContenido(byte[] byteContenido) {
		this.byteContenido = byteContenido;
	}

	/**
	 * Obtiene el ds ruta documento.
	 *
	 * @return el ds ruta documento
	 */
	public String getDsRutaDocumento() {
		return dsRutaDocumento;
	}

	/**
	 * Establece el ds ruta documento.
	 *
	 * @param dsRutaDocumento el nuevo ds ruta documento
	 */
	public void setDsRutaDocumento(String dsRutaDocumento) {
		this.dsRutaDocumento = dsRutaDocumento;
	}

	/**
	 * Obtiene el length documento.
	 *
	 * @return el length documento
	 */
	public Long getLengthDocumento() {
		return lengthDocumento;
	}

	/**
	 * Establece el length documento.
	 *
	 * @param lengthDocumento el nuevo length documento
	 */
	public void setLengthDocumento(Long lengthDocumento) {
		this.lengthDocumento = lengthDocumento;
	}

	/**
	 * Obtiene el ds nombre original documento.
	 *
	 * @return el ds nombre original documento
	 */
	public String getDsNombreOriginalDocumento() {
		return dsNombreOriginalDocumento;
	}

	/**
	 * Establece el ds nombre original documento.
	 *
	 * @param dsNombreOriginalDocumento el nuevo ds nombre original documento
	 */
	public void setDsNombreOriginalDocumento(String dsNombreOriginalDocumento) {
		this.dsNombreOriginalDocumento = dsNombreOriginalDocumento;
	}

	/**
	 * Obtiene el ds extension documento.
	 *
	 * @return el ds extension documento
	 */
	public String getDsExtensionDocumento() {
		return dsExtensionDocumento;
	}

	/**
	 * Establece el ds extension documento.
	 *
	 * @param dsExtensionDocumento el nuevo ds extension documento
	 */
	public void setDsExtensionDocumento(String dsExtensionDocumento) {
		this.dsExtensionDocumento = dsExtensionDocumento;
	}

	/**
	 * Obtiene el fe alta.
	 *
	 * @return el fe alta
	 */
	public Date getFeAlta() {
		return feAlta;
	}

	/**
	 * Establece el fe alta.
	 *
	 * @param feAlta el nuevo fe alta
	 */
	public void setFeAlta(Date feAlta) {
		this.feAlta = feAlta;
	}

	/**
	 * Comprueba si es es firma.
	 *
	 * @return verdadero, si es es firma
	 */
	public boolean isEsFirma() {
		return esFirma;
	}

	/**
	 * Establece el es firma.
	 *
	 * @param esFirma el nuevo es firma
	 */
	public void setEsFirma(boolean esFirma) {
		this.esFirma = esFirma;
	}

	/**
	 * Obtiene el cd documento firmado.
	 *
	 * @return el cd documento firmado
	 */
	public Integer getCdDocumentoFirmado() {
		return cdDocumentoFirmado;
	}

	/**
	 * Establece el cd documento firmado.
	 *
	 * @param cdDocumentoFirmado el nuevo cd documento firmado
	 */
	public void setCdDocumentoFirmado(Integer cdDocumentoFirmado) {
		this.cdDocumentoFirmado = cdDocumentoFirmado;
	}

	/**
	 * Obtiene el hash extracto.
	 *
	 * @return el hash extracto
	 */
	public String getHashExtracto() {
		return hashExtracto;
	}

	/**
	 * Establece el hash extracto.
	 *
	 * @param hashExtracto el nuevo hash extracto
	 */
	public void setHashExtracto(String hashExtracto) {
		this.hashExtracto = hashExtracto;
	}

	/**
	 * Obtiene el it documento principal.
	 *
	 * @return el it documento principal
	 */
	public String getItDocumentoPrincipal() {
		return itDocumentoPrincipal;
	}

	/**
	 * Establece el it documento principal.
	 *
	 * @param itDocumentoPrincipal el nuevo it documento principal
	 */
	public void setItDocumentoPrincipal(String itDocumentoPrincipal) {
		this.itDocumentoPrincipal = itDocumentoPrincipal;
	}

	/**
	 * Obtiene el cd entrada.
	 *
	 * @return el cd entrada
	 */
	public Integer getCdEntrada() {
		return cdEntrada;
	}

	/**
	 * Establece el cd entrada.
	 *
	 * @param cdEntrada el nuevo cd entrada
	 */
	public void setCdEntrada(Integer cdEntrada) {
		this.cdEntrada = cdEntrada;
	}

	/**
	 * Obtiene el cd salida.
	 *
	 * @return el cd salida
	 */
	public Integer getCdSalida() {
		return cdSalida;
	}

	/**
	 * Establece el cd salida.
	 *
	 * @param cdSalida el nuevo cd salida
	 */
	public void setCdSalida(Integer cdSalida) {
		this.cdSalida = cdSalida;
	}

	/**
	 * Obtiene el tl huella digital.
	 *
	 * @return el tl huella digital
	 */
	public String getTlHuellaDigital() {
		return tlHuellaDigital;
	}

	/**
	 * Establece el tl huella digital.
	 *
	 * @param tlHuellaDigital el nuevo tl huella digital
	 */
	public void setTlHuellaDigital(String tlHuellaDigital) {
		this.tlHuellaDigital = tlHuellaDigital;
	}

	/**
	 * Obtiene el ds extracto.
	 *
	 * @return el ds extracto
	 */
	public String getDsExtracto() {
		return dsExtracto;
	}

	/**
	 * Establece el ds extracto.
	 *
	 * @param dsExtracto el nuevo ds extracto
	 */
	public void setDsExtracto(String dsExtracto) {
		this.dsExtracto = dsExtracto;
	}

	/**
	 * Obtiene el id.
	 *
	 * @return el id
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * Establece el id.
	 *
	 * @param pId el nuevo id
	 */
	public void setId(Long pId) {
		this.id = pId;
	}
	
	/**
	 * Obtiene el contenido documento.
	 *
	 * @return el contenido documento
	 */
	public byte[] getContenidoDocumento() {
		return contenidoDocumento;
	}


	/**
	 * Establece el contenido documento.
	 *
	 * @param contenidoDocumento el nuevo contenido documento
	 */
	public void setContenidoDocumento(byte[] contenidoDocumento) {
		this.contenidoDocumento = contenidoDocumento;
	}


	/**
	 * Obtiene el extension.
	 *
	 * @return el extension
	 */
	public String getExtension() {
		return extension;
	}


	/**
	 * Establece el extension.
	 *
	 * @param extension el nuevo extension
	 */
	public void setExtension(String extension) {
		this.extension = extension;
	}


	/**
	 * Obtiene el entorno.
	 *
	 * @return el entorno
	 */
	public String getEntorno() {
		return entorno;
	}


	/**
	 * Establece el entorno.
	 *
	 * @param entorno el nuevo entorno
	 */
	public void setEntorno(String entorno) {
		this.entorno = entorno;
	}


	
	/**
	 * Obtiene el tipo documento.
	 *
	 * @return el tipo documento
	 */
	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}
	
	/**
	 * Establece el tipo documento.
	 *
	 * @param pTipoDocumento el nuevo tipo documento
	 */
	public void setTipoDocumento(TipoDocumento pTipoDocumento) {
		this.tipoDocumento = pTipoDocumento;
	}
	
	/**
	 * Obtiene el nombre.
	 *
	 * @return el nombre
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * Establece el nombre.
	 *
	 * @param pNombre el nuevo nombre
	 */
	public void setNombre(String pNombre) {
		this.nombre = pNombre;
	}
	
	/**
	 * Obtiene el fecha creacion.
	 *
	 * @return el fecha creacion
	 */
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	
	/**
	 * Establece el fecha creacion.
	 *
	 * @param pFechaCreacion el nuevo fecha creacion
	 */
	public void setFechaCreacion(Date pFechaCreacion) {
		this.fechaCreacion = pFechaCreacion;
	}
	
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
	 * @param pDescripcion el nuevo descripcion
	 */
	public void setDescripcion(String pDescripcion) {
		this.descripcion = pDescripcion;
	}
	
	/**
	 * Obtiene el tamano.
	 *
	 * @return el tamano
	 */
	public int getTamano() {
		return tamano;
	}
	
	/**
	 * Establece el tamano.
	 *
	 * @param pTamano el nuevo tamano
	 */
	public void setTamano(int pTamano) {
		this.tamano = pTamano;
	}
	
	/**
	 * Obtiene el nombre alfresco.
	 *
	 * @return el nombre alfresco
	 */
	public String getNombreAlfresco() {
		return nombreAlfresco;
	}
	
	/**
	 * Establece el nombre alfresco.
	 *
	 * @param pNombreAlfresco el nuevo nombre alfresco
	 */
	public void setNombreAlfresco(String pNombreAlfresco) {
		this.nombreAlfresco = pNombreAlfresco;
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


	/**
	 * Obtiene el id correo electronico.
	 *
	 * @return el id correo electronico
	 */
	public Long getIdCorreoElectronico() {
		return idCorreoElectronico;
	}


	/**
	 * Establece el id correo electronico.
	 *
	 * @param idCorreoElectronico el nuevo id correo electronico
	 */
	public void setIdCorreoElectronico(Long idCorreoElectronico) {
		this.idCorreoElectronico = idCorreoElectronico;
	}

	/**
	 * Obtiene el ubicacion.
	 *
	 * @return el ubicacion
	 */
	public String getUbicacion() {
		return ubicacion;
	}

	/**
	 * Establece el ubicacion.
	 *
	 * @param ubicacion el nuevo ubicacion
	 */
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	/**
	 * Obtiene el csv.
	 *
	 * @return el csv
	 */
	public String getCsv() {
		return csv;
	}

	/**
	 * Establece el csv.
	 *
	 * @param csv el nuevo csv
	 */
	public void setCsv(String csv) {
		this.csv = csv;
	}
	
	

}
