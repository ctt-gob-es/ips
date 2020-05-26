package es.map.ipsg.bean;

import es.map.ips.common.spring.SpringForm;

/**
 * El Class RegistroSolicitudJustificanteBean.
 */
public class RegistroSolicitudJustificanteBean extends SpringForm {
	
	/** La constante MAX_FILES. */
	private static final int MAX_FILES = 20;
	
	/** el nombre. */
	private String nombre;
	
	/** el primer apellido. */
	private String primerApellido;
	
	/** el segundo apellido. */
	private String segundoApellido;
	
	/** el nif. */
	private String nif;
	
	/** el num justificante. */
	private String numJustificante;
	
	/** el ejercicio. */
	private String ejercicio;
	
	/** el ministerio. */
	private String ministerio;
	
	/** el forma pago. */
	private String formaPago;
	
	/** el importe. */
	private String importe;
	
	/** el nrc. */
	private String nrc;
	
	/** el fecha pago. */
	private String fechaPago;
	
	/** el error registro. */
	private String errorRegistro;
	
	
	/** el accion. */
	private String accion;
	
	/** el id solicitud. */
	private String idSolicitud;
	
	/** el id convocatoria. */
	private String idConvocatoria;
	
	/** el aceptar. */
	private String aceptar;
	
	/** el signature. */
	private String signature;
	
	/** el signer cert. */
	private String signerCert;
	
	/** el documento HTML. */
	private String documentoHTML;
	
	/** el documento file firefox. */
	private String[] documentoFileFirefox = new String[MAX_FILES];
	
	/** el rutas documentos. */
	private String[] rutasDocumentos = new String[MAX_FILES];	
	
	/** el documentos ficheros. */
	private String[] documentosFicheros = new String[MAX_FILES];
	
	/** el extracto firefox. */
	private String[] extractoFirefox = new String[MAX_FILES];
		
	/** el documento file. */
	private String[] documentoFile = new String[MAX_FILES];
	
	/** el tipo doc adjunto. */
	private String[] tipoDocAdjunto = new String[MAX_FILES];	
	
	/** el extracto. */
	private String[] extracto = new String[MAX_FILES];
	
	/** el xml firmado base 64. */
	private String[] xmlFirmadoBase64 = new String[MAX_FILES];
	
	/** el cert firmado base 64. */
	private String[] certFirmadoBase64 = new String[MAX_FILES];
	
	/** el tipo documento. */
	private String[] tipoDocumento = new String[MAX_FILES];
	
	/** el extracto registro. */
	private String extractoRegistro;
	
	/** el firma extracto registro. */
	private String firmaExtractoRegistro;	
	
	/** el tl huella digital. */
	private String tlHuellaDigital;
	
	
	/** el fecha nacimiento. */
	private String fechaNacimiento;
	
	/** el localidad nacimiento. */
	private String localidadNacimiento;
	
	/** el provincia nacimiento. */
	private String provinciaNacimiento;
	
	/** el nacionalidad. */
	private String nacionalidad;
	
	/** el telefono. */
	private String telefono;
	
	/** el email. */
	private String email;
	
	/** el calle. */
	private String calle;
	
	/** el cod postal. */
	private String codPostal;
	
	/** el municipio domicilio. */
	private String municipioDomicilio;
	
	/** el provincia domicilio. */
	private String provinciaDomicilio;
	
	/** el pais domicilio. */
	private String paisDomicilio;
	
	/** el tipo discapacidad. */
	private String tipoDiscapacidad;
	
	/** el porcentaje discapacidad. */
	private String porcentajeDiscapacidad;
	
	/** el reserva discapacidad. */
	private String reservaDiscapacidad;
	
	/** el detalle discapacidad. */
	private String detalleDiscapacidad;
	
	/** el datos A. */
	private String datosA;
	
	/** el datos B. */
	private String datosB;
	
	/** el datos C. */
	private String datosC;
	
	/** el fecha solicitud. */
	private String fechaSolicitud;
	
	/** el solicita reduccion. */
	private String solicitaReduccion;
	
	/** el motivo reduccion des. */
	private String motivoReduccionDes;
	
	/** el entidad financiera. */
	private String entidadFinanciera;
	
	/** el tipo pago. */
	private String tipoPago;
	
	/** el des provincia examen. */
	private String desProvinciaExamen;
	
	/** el des titulo oficial. */
	private String desTituloOficial;
	
	/** el des especialidad. */
	private String desEspecialidad;
	
	/** el otros titulos. */
	private String otrosTitulos;
	
	/** el motivo oposcion */
	private String motivoOposicion;
	
	/** el encabezado. */
	private String noConsentimiento;
	
	private String cumplimiento;
	
	private String  ejerce;
	
	private String ejerce2;
	
	private String autorizar;
	
	private String autoriza;
	
	private String noAutorizar;
	
	private String declaracion;
	
	/**
	 * Obtiene el error registro.
	 *
	 * @return el error registro
	 */
	public String getErrorRegistro() {
		return errorRegistro;
	}
	
	/**
	 * Establece el error registro.
	 *
	 * @param errorRegistro el nuevo error registro
	 */
	public void setErrorRegistro(String errorRegistro) {
		this.errorRegistro = errorRegistro;
	}
	
	/**
	 * Obtiene el tipo documento.
	 *
	 * @param i el i
	 * @return el tipo documento
	 */
	public String getTipoDocumento(int i) {
		return tipoDocumento[i];
	}
	
	/**
	 * Obtiene el tipo documento.
	 *
	 * @return el tipo documento
	 */
	public String[] getTipoDocumento() {
		return tipoDocumento;
	}
	
	/**
	 * Establece el tipo documento.
	 *
	 * @param tipoDocumento el nuevo tipo documento
	 */
	public void setTipoDocumento(String[] tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	
	/**
	 * Obtiene el documento HTML.
	 *
	 * @return el documento HTML
	 */
	public String getDocumentoHTML() {
		return documentoHTML;
	}
	
	/**
	 * Establece el documento HTML.
	 *
	 * @param documentoHTML el nuevo documento HTML
	 */
	public void setDocumentoHTML(String documentoHTML) {
		this.documentoHTML = documentoHTML;
	}
	
	/**
	 * Obtiene el signature.
	 *
	 * @return el signature
	 */
	public String getSignature() {
		return signature;
	}
	
	/**
	 * Establece el signature.
	 *
	 * @param signature el nuevo signature
	 */
	public void setSignature(String signature) {
		this.signature = signature;
	}
	
	/**
	 * Obtiene el signer cert.
	 *
	 * @return el signer cert
	 */
	public String getSignerCert() {
		return signerCert;
	}
	
	/**
	 * Establece el signer cert.
	 *
	 * @param signerCert el nuevo signer cert
	 */
	public void setSignerCert(String signerCert) {
		this.signerCert = signerCert;
	}
	
	/**
	 * Obtiene el aceptar.
	 *
	 * @return el aceptar
	 */
	public String getAceptar() {
		return aceptar;
	}
	
	/**
	 * Establece el aceptar.
	 *
	 * @param aceptar el nuevo aceptar
	 */
	public void setAceptar(String aceptar) {
		this.aceptar = aceptar;
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
	 * @param nombre el nuevo nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * Obtiene el primer apellido.
	 *
	 * @return el primer apellido
	 */
	public String getPrimerApellido() {
		return primerApellido;
	}
	
	/**
	 * Establece el primer apellido.
	 *
	 * @param primerApellido el nuevo primer apellido
	 */
	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}
	
	/**
	 * Obtiene el segundo apellido.
	 *
	 * @return el segundo apellido
	 */
	public String getSegundoApellido() {
		return segundoApellido;
	}
	
	/**
	 * Establece el segundo apellido.
	 *
	 * @param segundoApellido el nuevo segundo apellido
	 */
	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}
	
	/**
	 * Obtiene el nif.
	 *
	 * @return el nif
	 */
	public String getNif() {
		return nif;
	}
	
	/**
	 * Establece el nif.
	 *
	 * @param nif el nuevo nif
	 */
	public void setNif(String nif) {
		this.nif = nif;
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
	 * @param numJustificante el nuevo num justificante
	 */
	public void setNumJustificante(String numJustificante) {
		this.numJustificante = numJustificante;
	}
	
	/**
	 * Obtiene el ejercicio.
	 *
	 * @return el ejercicio
	 */
	public String getEjercicio() {
		return ejercicio;
	}
	
	/**
	 * Establece el ejercicio.
	 *
	 * @param ejercicio el nuevo ejercicio
	 */
	public void setEjercicio(String ejercicio) {
		this.ejercicio = ejercicio;
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
	 * @param ministerio el nuevo ministerio
	 */
	public void setMinisterio(String ministerio) {
		this.ministerio = ministerio;
	}
	
	/**
	 * Obtiene el forma pago.
	 *
	 * @return el forma pago
	 */
	public String getFormaPago() {
		return formaPago;
	}
	
	/**
	 * Establece el forma pago.
	 *
	 * @param formaPago el nuevo forma pago
	 */
	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}
	
	/**
	 * Obtiene el importe.
	 *
	 * @return el importe
	 */
	public String getImporte() {
		return importe;
	}
	
	/**
	 * Establece el importe.
	 *
	 * @param importe el nuevo importe
	 */
	public void setImporte(String importe) {
		this.importe = importe;
	}
	
	/**
	 * Obtiene el nrc.
	 *
	 * @return el nrc
	 */
	public String getNrc() {
		return nrc;
	}
	
	/**
	 * Establece el nrc.
	 *
	 * @param nrc el nuevo nrc
	 */
	public void setNrc(String nrc) {
		this.nrc = nrc;
	}
	
	/**
	 * Obtiene el fecha pago.
	 *
	 * @return el fecha pago
	 */
	public String getFechaPago() {
		return fechaPago;
	}
	
	/**
	 * Establece el fecha pago.
	 *
	 * @param fechaPago el nuevo fecha pago
	 */
	public void setFechaPago(String fechaPago) {
		this.fechaPago = fechaPago;
	}
	
	/**
	 * Obtiene el accion.
	 *
	 * @return el accion
	 */
	public String getAccion() {
		return accion;
	}
	
	/**
	 * Establece el accion.
	 *
	 * @param accion el nuevo accion
	 */
	public void setAccion(String accion) {
		this.accion = accion;
	}
	
	/**
	 * Obtiene el id solicitud.
	 *
	 * @return el id solicitud
	 */
	public String getIdSolicitud() {
		return idSolicitud;
	}
	
	/**
	 * Establece el id solicitud.
	 *
	 * @param idSolicitud el nuevo id solicitud
	 */
	public void setIdSolicitud(String idSolicitud) {
		this.idSolicitud = idSolicitud;
	}
	
	/**
	 * Obtiene el id convocatoria.
	 *
	 * @return el id convocatoria
	 */
	public String getIdConvocatoria() {
		return idConvocatoria;
	}
	
	/**
	 * Establece el id convocatoria.
	 *
	 * @param idConvocatoria el nuevo id convocatoria
	 */
	public void setIdConvocatoria(String idConvocatoria) {
		this.idConvocatoria = idConvocatoria;
	}
	
	/**
	 * Obtiene el documento file firefox.
	 *
	 * @return el documento file firefox
	 */
	public String[] getDocumentoFileFirefox() {
		return documentoFileFirefox;
	}	
	
	/**
	 * Obtiene el documento file firefox.
	 *
	 * @param i el i
	 * @return el documento file firefox
	 */
	public String getDocumentoFileFirefox(int i) {
		return documentoFileFirefox[i];
	}	
	
	/**
	 * Establece el documento file firefox.
	 *
	 * @param documentoFileFirefox el nuevo documento file firefox
	 */
	public void setDocumentoFileFirefox(String[] documentoFileFirefox) {
		this.documentoFileFirefox = documentoFileFirefox;
	}
	
	/**
	 * Obtiene el rutas documentos.
	 *
	 * @return el rutas documentos
	 */
	public String[] getRutasDocumentos() {
		return rutasDocumentos;
	}
	
	/**
	 * Obtiene el rutas documentos.
	 *
	 * @param i el i
	 * @return el rutas documentos
	 */
	public String getRutasDocumentos(int i) {
		return rutasDocumentos[i];
	}
	
	/**
	 * Establece el rutas documentos.
	 *
	 * @param rutasDocumentos el nuevo rutas documentos
	 */
	public void setRutasDocumentos(String[] rutasDocumentos) {
		this.rutasDocumentos = rutasDocumentos;
	}
	
	/**
	 * Obtiene el documentos ficheros.
	 *
	 * @return el documentos ficheros
	 */
	public String[] getDocumentosFicheros() {
		return documentosFicheros;
	}
	
	/**
	 * Obtiene el documentos ficheros.
	 *
	 * @param i el i
	 * @return el documentos ficheros
	 */
	public String getDocumentosFicheros(int i) {
		return documentosFicheros[i];
	}
	
	/**
	 * Establece el documentos ficheros.
	 *
	 * @param documentosFicheros el nuevo documentos ficheros
	 */
	public void setDocumentosFicheros(String[] documentosFicheros) {
		this.documentosFicheros = documentosFicheros;
	}
	
	/**
	 * Obtiene el extracto firefox.
	 *
	 * @return el extracto firefox
	 */
	public String[] getExtractoFirefox() {
		return extractoFirefox;
	}
	
	/**
	 * Obtiene el extracto firefox.
	 *
	 * @param i el i
	 * @return el extracto firefox
	 */
	public String getExtractoFirefox(int i) {
		return extractoFirefox[i];
	}
	
	/**
	 * Establece el extracto firefox.
	 *
	 * @param extractoFirefox el nuevo extracto firefox
	 */
	public void setExtractoFirefox(String[] extractoFirefox) {
		this.extractoFirefox = extractoFirefox;
	}
	
	/**
	 * Obtiene el documento file.
	 *
	 * @return el documento file
	 */
	public String[] getDocumentoFile() {
		return documentoFile;
	}
	
	/**
	 * Obtiene el documento file.
	 *
	 * @param i el i
	 * @return el documento file
	 */
	public String getDocumentoFile(int i) {
		return documentoFile[i];
	}
	
	/**
	 * Establece el documento file.
	 *
	 * @param documentoFile el nuevo documento file
	 */
	public void setDocumentoFile(String[] documentoFile) {
		this.documentoFile = documentoFile;
	}
	
	/**
	 * Obtiene el tipo doc adjunto.
	 *
	 * @return el tipo doc adjunto
	 */
	public String[] getTipoDocAdjunto() {
		return tipoDocAdjunto;
	}
	
	/**
	 * Obtiene el tipo doc adjunto.
	 *
	 * @param i el i
	 * @return el tipo doc adjunto
	 */
	public String getTipoDocAdjunto(int i) {
		return tipoDocAdjunto[i];
	}
	
	/**
	 * Establece el tipo doc adjunto.
	 *
	 * @param tipoDocAdjunto el nuevo tipo doc adjunto
	 */
	public void setTipoDocAdjunto(String[] tipoDocAdjunto) {
		this.tipoDocAdjunto = tipoDocAdjunto;
	}
	
	/**
	 * Obtiene el extracto.
	 *
	 * @return el extracto
	 */
	public String[] getExtracto() {
		return extracto;
	}
	
	/**
	 * Obtiene el extracto.
	 *
	 * @param i el i
	 * @return el extracto
	 */
	public String getExtracto(int i) {
		return extracto[i];
	}
	
	/**
	 * Establece el extracto.
	 *
	 * @param extracto el nuevo extracto
	 */
	public void setExtracto(String[] extracto) {
		this.extracto = extracto;
	}
	
	/**
	 * Obtiene el xml firmado base 64.
	 *
	 * @return el xml firmado base 64
	 */
	public String[] getXmlFirmadoBase64() {
		return xmlFirmadoBase64;
	}
	
	/**
	 * Obtiene el xml firmado base 64.
	 *
	 * @param i el i
	 * @return el xml firmado base 64
	 */
	public String getXmlFirmadoBase64(int i) {
		return xmlFirmadoBase64[i];
	}
	
	/**
	 * Establece el xml firmado base 64.
	 *
	 * @param xmlFirmadoBase64 el nuevo xml firmado base 64
	 */
	public void setXmlFirmadoBase64(String[] xmlFirmadoBase64) {
		this.xmlFirmadoBase64 = xmlFirmadoBase64;
	}
	
	/**
	 * Obtiene el cert firmado base 64.
	 *
	 * @return el cert firmado base 64
	 */
	public String[] getCertFirmadoBase64() {
		return certFirmadoBase64;
	}
	
	/**
	 * Obtiene el cert firmado base 64.
	 *
	 * @param i el i
	 * @return el cert firmado base 64
	 */
	public String getCertFirmadoBase64(int i) {
		return certFirmadoBase64[i];
	}
	
	/**
	 * Establece el cert firmado base 64.
	 *
	 * @param certFirmadoBase64 el nuevo cert firmado base 64
	 */
	public void setCertFirmadoBase64(String[] certFirmadoBase64) {
		this.certFirmadoBase64 = certFirmadoBase64;
	}
	
	/**
	 * Obtiene el extracto registro.
	 *
	 * @return el extracto registro
	 */
	public String getExtractoRegistro() {
		return extractoRegistro;
	}
	
	/**
	 * Establece el extracto registro.
	 *
	 * @param extractoRegistro el nuevo extracto registro
	 */
	public void setExtractoRegistro(String extractoRegistro) {
		this.extractoRegistro = extractoRegistro;
	}
	
	/**
	 * Obtiene el firma extracto registro.
	 *
	 * @return el firma extracto registro
	 */
	public String getFirmaExtractoRegistro() {
		return firmaExtractoRegistro;
	}
	
	/**
	 * Establece el firma extracto registro.
	 *
	 * @param firmaExtractoRegistro el nuevo firma extracto registro
	 */
	public void setFirmaExtractoRegistro(String firmaExtractoRegistro) {
		this.firmaExtractoRegistro = firmaExtractoRegistro;
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
	 * Obtiene el fecha nacimiento.
	 *
	 * @return el fecha nacimiento
	 */
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	
	/**
	 * Establece el fecha nacimiento.
	 *
	 * @param fechaNacimiento el nuevo fecha nacimiento
	 */
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	
	/**
	 * Obtiene el localidad nacimiento.
	 *
	 * @return el localidad nacimiento
	 */
	public String getLocalidadNacimiento() {
		return localidadNacimiento;
	}
	
	/**
	 * Establece el localidad nacimiento.
	 *
	 * @param localidadNacimiento el nuevo localidad nacimiento
	 */
	public void setLocalidadNacimiento(String localidadNacimiento) {
		this.localidadNacimiento = localidadNacimiento;
	}
	
	/**
	 * Obtiene el provincia nacimiento.
	 *
	 * @return el provincia nacimiento
	 */
	public String getProvinciaNacimiento() {
		return provinciaNacimiento;
	}
	
	/**
	 * Establece el provincia nacimiento.
	 *
	 * @param provinciaNacimiento el nuevo provincia nacimiento
	 */
	public void setProvinciaNacimiento(String provinciaNacimiento) {
		this.provinciaNacimiento = provinciaNacimiento;
	}
	
	/**
	 * Obtiene el nacionalidad.
	 *
	 * @return el nacionalidad
	 */
	public String getNacionalidad() {
		return nacionalidad;
	}
	
	/**
	 * Establece el nacionalidad.
	 *
	 * @param nacionalidad el nuevo nacionalidad
	 */
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	
	/**
	 * Obtiene el telefono.
	 *
	 * @return el telefono
	 */
	public String getTelefono() {
		return telefono;
	}
	
	/**
	 * Establece el telefono.
	 *
	 * @param telefono el nuevo telefono
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	/**
	 * Obtiene el email.
	 *
	 * @return el email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Establece el email.
	 *
	 * @param email el nuevo email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * Obtiene el calle.
	 *
	 * @return el calle
	 */
	public String getCalle() {
		return calle;
	}
	
	/**
	 * Establece el calle.
	 *
	 * @param calle el nuevo calle
	 */
	public void setCalle(String calle) {
		this.calle = calle;
	}
	
	/**
	 * Obtiene el cod postal.
	 *
	 * @return el cod postal
	 */
	public String getCodPostal() {
		return codPostal;
	}
	
	/**
	 * Establece el cod postal.
	 *
	 * @param codPostal el nuevo cod postal
	 */
	public void setCodPostal(String codPostal) {
		this.codPostal = codPostal;
	}
	
	/**
	 * Obtiene el municipio domicilio.
	 *
	 * @return el municipio domicilio
	 */
	public String getMunicipioDomicilio() {
		return municipioDomicilio;
	}
	
	/**
	 * Establece el municipio domicilio.
	 *
	 * @param municipioDomicilio el nuevo municipio domicilio
	 */
	public void setMunicipioDomicilio(String municipioDomicilio) {
		this.municipioDomicilio = municipioDomicilio;
	}
	
	/**
	 * Obtiene el provincia domicilio.
	 *
	 * @return el provincia domicilio
	 */
	public String getProvinciaDomicilio() {
		return provinciaDomicilio;
	}
	
	/**
	 * Establece el provincia domicilio.
	 *
	 * @param provinciaDomicilio el nuevo provincia domicilio
	 */
	public void setProvinciaDomicilio(String provinciaDomicilio) {
		this.provinciaDomicilio = provinciaDomicilio;
	}
	
	/**
	 * Obtiene el pais domicilio.
	 *
	 * @return el pais domicilio
	 */
	public String getPaisDomicilio() {
		return paisDomicilio;
	}
	
	/**
	 * Establece el pais domicilio.
	 *
	 * @param paisDomicilio el nuevo pais domicilio
	 */
	public void setPaisDomicilio(String paisDomicilio) {
		this.paisDomicilio = paisDomicilio;
	}
	
	/**
	 * Obtiene el tipo discapacidad.
	 *
	 * @return el tipo discapacidad
	 */
	public String getTipoDiscapacidad() {
		return tipoDiscapacidad;
	}
	
	/**
	 * Establece el tipo discapacidad.
	 *
	 * @param tipoDiscapacidad el nuevo tipo discapacidad
	 */
	public void setTipoDiscapacidad(String tipoDiscapacidad) {
		this.tipoDiscapacidad = tipoDiscapacidad;
	}
	
	/**
	 * Obtiene el porcentaje discapacidad.
	 *
	 * @return el porcentaje discapacidad
	 */
	public String getPorcentajeDiscapacidad() {
		return porcentajeDiscapacidad;
	}
	
	/**
	 * Establece el porcentaje discapacidad.
	 *
	 * @param porcentajeDiscapacidad el nuevo porcentaje discapacidad
	 */
	public void setPorcentajeDiscapacidad(String porcentajeDiscapacidad) {
		this.porcentajeDiscapacidad = porcentajeDiscapacidad;
	}
	
	/**
	 * Obtiene el reserva discapacidad.
	 *
	 * @return el reserva discapacidad
	 */
	public String getReservaDiscapacidad() {
		return reservaDiscapacidad;
	}
	
	/**
	 * Establece el reserva discapacidad.
	 *
	 * @param reservaDiscapacidad el nuevo reserva discapacidad
	 */
	public void setReservaDiscapacidad(String reservaDiscapacidad) {
		this.reservaDiscapacidad = reservaDiscapacidad;
	}
	
	/**
	 * Obtiene el detalle discapacidad.
	 *
	 * @return el detalle discapacidad
	 */
	public String getDetalleDiscapacidad() {
		return detalleDiscapacidad;
	}
	
	/**
	 * Establece el detalle discapacidad.
	 *
	 * @param detalleDiscapacidad el nuevo detalle discapacidad
	 */
	public void setDetalleDiscapacidad(String detalleDiscapacidad) {
		this.detalleDiscapacidad = detalleDiscapacidad;
	}
	
	/**
	 * Obtiene el datos A.
	 *
	 * @return el datos A
	 */
	public String getDatosA() {
		return datosA;
	}
	
	/**
	 * Establece el datos A.
	 *
	 * @param datosA el nuevo datos A
	 */
	public void setDatosA(String datosA) {
		this.datosA = datosA;
	}
	
	/**
	 * Obtiene el datos B.
	 *
	 * @return el datos B
	 */
	public String getDatosB() {
		return datosB;
	}
	
	/**
	 * Establece el datos B.
	 *
	 * @param datosB el nuevo datos B
	 */
	public void setDatosB(String datosB) {
		this.datosB = datosB;
	}
	
	/**
	 * Obtiene el datos C.
	 *
	 * @return el datos C
	 */
	public String getDatosC() {
		return datosC;
	}
	
	/**
	 * Establece el datos C.
	 *
	 * @param datosC el nuevo datos C
	 */
	public void setDatosC(String datosC) {
		this.datosC = datosC;
	}
	
	/**
	 * Obtiene el fecha solicitud.
	 *
	 * @return el fecha solicitud
	 */
	public String getFechaSolicitud() {
		return fechaSolicitud;
	}
	
	/**
	 * Establece el fecha solicitud.
	 *
	 * @param fechaSolicitud el nuevo fecha solicitud
	 */
	public void setFechaSolicitud(String fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}
	
	/**
	 * Obtiene el solicita reduccion.
	 *
	 * @return el solicita reduccion
	 */
	public String getSolicitaReduccion() {
		return solicitaReduccion;
	}
	
	/**
	 * Establece el solicita reduccion.
	 *
	 * @param solicitaReduccion el nuevo solicita reduccion
	 */
	public void setSolicitaReduccion(String solicitaReduccion) {
		this.solicitaReduccion = solicitaReduccion;
	}
	
	/**
	 * Obtiene el motivo reduccion des.
	 *
	 * @return el motivo reduccion des
	 */
	public String getMotivoReduccionDes() {
		return motivoReduccionDes;
	}
	
	/**
	 * Establece el motivo reduccion des.
	 *
	 * @param motivoReduccionDes el nuevo motivo reduccion des
	 */
	public void setMotivoReduccionDes(String motivoReduccionDes) {
		this.motivoReduccionDes = motivoReduccionDes;
	}
	
	/**
	 * Obtiene el entidad financiera.
	 *
	 * @return el entidad financiera
	 */
	public String getEntidadFinanciera() {
		return entidadFinanciera;
	}
	
	/**
	 * Establece el entidad financiera.
	 *
	 * @param entidadFinanciera el nuevo entidad financiera
	 */
	public void setEntidadFinanciera(String entidadFinanciera) {
		this.entidadFinanciera = entidadFinanciera;
	}
	
	/**
	 * Obtiene el tipo pago.
	 *
	 * @return el tipo pago
	 */
	public String getTipoPago() {
		return tipoPago;
	}
	
	/**
	 * Establece el tipo pago.
	 *
	 * @param tipoPago el nuevo tipo pago
	 */
	public void setTipoPago(String tipoPago) {
		this.tipoPago = tipoPago;
	}
	
	/**
	 * Obtiene el des provincia examen.
	 *
	 * @return el des provincia examen
	 */
	public String getDesProvinciaExamen() {
		return desProvinciaExamen;
	}
	
	/**
	 * Establece el des provincia examen.
	 *
	 * @param desProvinciaExamen el nuevo des provincia examen
	 */
	public void setDesProvinciaExamen(String desProvinciaExamen) {
		this.desProvinciaExamen = desProvinciaExamen;
	}
	
	/**
	 * Obtiene el des titulo oficial.
	 *
	 * @return el des titulo oficial
	 */
	public String getDesTituloOficial() {
		return desTituloOficial;
	}
	
	/**
	 * Establece el des titulo oficial.
	 *
	 * @param desTituloOficial el nuevo des titulo oficial
	 */
	public void setDesTituloOficial(String desTituloOficial) {
		this.desTituloOficial = desTituloOficial;
	}
	
	/**
	 * Obtiene el des especialidad.
	 *
	 * @return el des especialidad
	 */
	public String getDesEspecialidad() {
		return desEspecialidad;
	}
	
	/**
	 * Establece el des especialidad.
	 *
	 * @param desEspecialidad el nuevo des especialidad
	 */
	public void setDesEspecialidad(String desEspecialidad) {
		this.desEspecialidad = desEspecialidad;
	}
	
	/**
	 * Obtiene el otros titulos.
	 *
	 * @return el otros titulos
	 */
	public String getOtrosTitulos() {
		return otrosTitulos;
	}
	
	/**
	 * Establece el otros titulos.
	 *
	 * @param otrosTitulos el nuevo otros titulos
	 */
	public void setOtrosTitulos(String otrosTitulos) {
		this.otrosTitulos = otrosTitulos;
	}

	public String getMotivoOposicion() {
		return motivoOposicion;
	}

	public void setMotivoOposicion(String motivoOposicion) {
		this.motivoOposicion = motivoOposicion;
	}

	public String getNoConsentimiento() {
		return noConsentimiento;
	}

	public void setNoConsentimiento(String noConsentimiento) {
		this.noConsentimiento = noConsentimiento;
	}

	public String getCumplimiento() {
		return cumplimiento;
	}

	public void setCumplimiento(String cumplimiento) {
		this.cumplimiento = cumplimiento;
	}

	public String getEjerce() {
		return ejerce;
	}

	public void setEjerce(String ejerce) {
		this.ejerce = ejerce;
	}

	public String getEjerce2() {
		return ejerce2;
	}

	public void setEjerce2(String ejerce2) {
		this.ejerce2 = ejerce2;
	}

	public String getAutorizar() {
		return autorizar;
	}

	public void setAutorizar(String autorizar) {
		this.autorizar = autorizar;
	}

	public String getAutoriza() {
		return autoriza;
	}

	public void setAutoriza(String autoriza) {
		this.autoriza = autoriza;
	}

	public String getNoAutorizar() {
		return noAutorizar;
	}

	public void setNoAutorizar(String noAutorizar) {
		this.noAutorizar = noAutorizar;
	}

	public String getDeclaracion() {
		return declaracion;
	}

	public void setDeclaracion(String declaracion) {
		this.declaracion = declaracion;
	}	
	
	
	
	
}
