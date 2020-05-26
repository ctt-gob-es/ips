package es.map.ipsc.form;

import javax.servlet.http.HttpServletRequest;

import es.map.ips.common.spring.SpringErrors;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringMapping;
import es.map.ips.common.spring.SpringMessage;
import es.map.ipsc.Constantes;


/**
 * El Class PagoSolicitudForm.
 */
public class PagoSolicitudForm extends SpringForm {
	
	/** La constante serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** el numero solicitud. */
	private String numeroSolicitud;
	
	/** el id. */
	private String id;
	
	/** el id solicitud. */
	private String idSolicitud;
	
	/** el nif. */
	private String nif;
	
	/** el nombre. */
	private String nombre;
	
	/** el apellido 1. */
	private String apellido1;
	
	/** el apellido 2. */
	private String apellido2;
	
	/** el nombre parcial. */
	private String nombreParcial;
	
	/** el num solicitud. */
	private String numSolicitud;
	
	/** el id convocatoria. */
	private String idConvocatoria;
	
	/** el origen firma. */
	private String origenFirma;
	
	/** el provincia DD. */
	private String provinciaDD;	
	
	/** el importe. */
	private Float importe;
	
	/** el forma pago. */
	private String formaPago;
	
	/** el nrc. */
	private String nrc;
	
	/** el nrc ciudadano. */
	private String nrcCiudadano;
	
	/** el motivo. */
	private String motivo;
	
	/** el motivo tarjeta. */
	private String motivoTarjeta;
	
	/** el motivo adeudo. */
	private String motivoAdeudo;
	
	/** el detalle motivo. */
	private String detalleMotivo;
	
	/** el id comunidad FN exento. */
	private String idComunidadFNExento;
	
	/** el id comunidad FN tarjeta. */
	private String idComunidadFNTarjeta;
	
	/** el id comunidad FN cuenta. */
	private String idComunidadFNCuenta;
	
	/** el id comunidad DD. */
	private String idComunidadDD;
	
	/** el id comunidad DD exento. */
	private String idComunidadDDExento;
	
	/** el idtitulo FN exento. */
	private String idtituloFNExento;
	
	/** el idtitulo FN tarjeta. */
	private String idtituloFNTarjeta;
	
	/** el idtitulo FN cuenta. */
	private String idtituloFNCuenta;
	
	/** el titulo FN exento. */
	private String tituloFNExento;
	
	/** el id forma acceso. */
	private String idFormaAcceso;
	
	/** el banco tarjeta. */
	private String bancoTarjeta;
	
	/** el num tarjeta. */
	private String numTarjeta;
	
	/** el num tarjeta 2. */
	private String numTarjeta2;
	
	/** el num tarjeta 3. */
	private String numTarjeta3;
	
	/** el num tarjeta 4. */
	private String numTarjeta4;
	
	/** el num tarjeta 5. */
	private String numTarjeta5;
	
	/** el nif titular. */
	private String nifTitular;
	
	/** el nombre titular. */
	private String nombreTitular;
	
	/** el apellido 1 titular. */
	private String apellido1Titular;
	
	/** el apellido 2 titular. */
	private String apellido2Titular;
	
	/** el caducidad tarjeta 1. */
	private String caducidadTarjeta1;
	
	/** el caducidad tarjeta 2. */
	private String caducidadTarjeta2;
	
	/** el entidad ciudadano. */
	private String entidadCiudadano;
	
	/** el entidad tarjeta. */
	private String entidadTarjeta;
	
	/** el entidad adeudo. */
	private String entidadAdeudo;
	
	/** el reduccion pago. */
	private String reduccionPago;
	

	/** el iban. */
	private String IBAN;
	
	/** el banco adeudo. */
	private String bancoAdeudo;
	
	/** el form pago. */
	private String formPago;
	
	/** el importe original. */
	private Float importeOriginal;
	
	/** el importe actual. */
	private Float importeActual;

	
	/** el submit. */
	private String submit;
	
	/** el accion. */
	private String accion;
	
	/** el prueba. */
	private String[] prueba = new String[5];	
	
	/** el signature. */
	private String signature;
	
	/** el signer cert. */
	private String signerCert;
	
	/** el documento HTML. */
	private String documentoHTML;
	
	/** el aceptar. */
	private String aceptar;
	
	/** el firma pago. */
	private String firmaPago;
	
	/** el firma doc. */
	private String firmaDoc;
	
	/** el firma cert. */
	private String firmaCert;
	
	/** el codigo tasa. */
	private String codigoTasa;
	
	/** el oficina. */
	private String oficina;
	
	/** el dc. */
	private String dc;
	
	/** el cuenta. */
	private String cuenta;
	
	/** el tipo cargo. */
	private String tipoCargo;
	
	/** el cod banco. */
	private String codBanco;
	
	/** el tipo documento. */
	private String tipoDocumento;
	
	/** el esta en pago. */
	private Boolean estaEnPago;
	
	/** el pago inicial modif. */
	private String pagoInicialModif;
	
	/** el id solicitud inicial. */
	private String idSolicitudInicial;
	
	/**
	 * Obtiene el firma doc.
	 *
	 * @return el firma doc
	 */
	public String getFirmaDoc() {
		return firmaDoc;
	}
	
	/**
	 * Establece el firma doc.
	 *
	 * @param firmaDoc el nuevo firma doc
	 */
	public void setFirmaDoc(String firmaDoc) {
		this.firmaDoc = firmaDoc;
	}
	
	/**
	 * Obtiene el firma cert.
	 *
	 * @return el firma cert
	 */
	public String getFirmaCert() {
		return firmaCert;
	}
	
	/**
	 * Establece el firma cert.
	 *
	 * @param firmaCert el nuevo firma cert
	 */
	public void setFirmaCert(String firmaCert) {
		this.firmaCert = firmaCert;
	}
	
	/**
	 * Obtiene el firma pago.
	 *
	 * @return el firma pago
	 */
	public String getFirmaPago() {
		return firmaPago;
	}
	
	/**
	 * Establece el firma pago.
	 *
	 * @param firmaPago el nuevo firma pago
	 */
	public void setFirmaPago(String firmaPago) {
		this.firmaPago = firmaPago;
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
	 * Obtiene el prueba.
	 *
	 * @return el prueba
	 */
	public String[] getPrueba() {
		return prueba;
	}
	
	/**
	 * Establece el prueba.
	 *
	 * @param prueba el nuevo prueba
	 */
	public void setPrueba(String[] prueba) {
		this.prueba = prueba;
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
	 * Obtiene el detalle motivo.
	 *
	 * @return el detalle motivo
	 */
	public String getDetalleMotivo() {
		return detalleMotivo;
	}
	
	/**
	 * Establece el detalle motivo.
	 *
	 * @param detalleMotivo el nuevo detalle motivo
	 */
	public void setDetalleMotivo(String detalleMotivo) {
		this.detalleMotivo = detalleMotivo;
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
	 * Obtiene el importe actual.
	 *
	 * @return el importe actual
	 */
	public Float getImporteActual() {
		return importeActual;
	}
	
	/**
	 * Establece el importe actual.
	 *
	 * @param importeActual el nuevo importe actual
	 */
	public void setImporteActual(Float importeActual) {
		this.importeActual = importeActual;
	}
	
	/**
	 * Obtiene el importe original.
	 *
	 * @return el importe original
	 */
	public Float getImporteOriginal() {
		return importeOriginal;
	}
	
	/**
	 * Establece el importe original.
	 *
	 * @param importeOriginal el nuevo importe original
	 */
	public void setImporteOriginal(Float importeOriginal) {
		this.importeOriginal = importeOriginal;
	}
	
	/**
	 * Obtiene el form pago.
	 *
	 * @return el form pago
	 */
	public String getFormPago() {
		return formPago;
	}
	
	/**
	 * Establece el form pago.
	 *
	 * @param formPago el nuevo form pago
	 */
	public void setFormPago(String formPago) {
		this.formPago = formPago;
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
	 * Obtiene el nrc ciudadano.
	 *
	 * @return el nrc ciudadano
	 */
	public String getNrcCiudadano() {
		return nrcCiudadano;
	}
	
	/**
	 * Establece el nrc ciudadano.
	 *
	 * @param nrcCiudadano el nuevo nrc ciudadano
	 */
	public void setNrcCiudadano(String nrcCiudadano) {
		this.nrcCiudadano = nrcCiudadano;
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
	 * Obtiene el id.
	 *
	 * @return el id
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Establece el id.
	 *
	 * @param id el nuevo id
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * Obtiene el num tarjeta 2.
	 *
	 * @return el num tarjeta 2
	 */
	public String getNumTarjeta2() {
		return numTarjeta2;
	}
	
	/**
	 * Establece el num tarjeta 2.
	 *
	 * @param numTarjeta2 el nuevo num tarjeta 2
	 */
	public void setNumTarjeta2(String numTarjeta2) {
		this.numTarjeta2 = numTarjeta2;
	}
	
	/**
	 * Obtiene el num tarjeta 3.
	 *
	 * @return el num tarjeta 3
	 */
	public String getNumTarjeta3() {
		return numTarjeta3;
	}
	
	/**
	 * Establece el num tarjeta 3.
	 *
	 * @param numTarjeta3 el nuevo num tarjeta 3
	 */
	public void setNumTarjeta3(String numTarjeta3) {
		this.numTarjeta3 = numTarjeta3;
	}
	
	/**
	 * Obtiene el num tarjeta 4.
	 *
	 * @return el num tarjeta 4
	 */
	public String getNumTarjeta4() {
		return numTarjeta4;
	}
	
	/**
	 * Establece el num tarjeta 4.
	 *
	 * @param numTarjeta4 el nuevo num tarjeta 4
	 */
	public void setNumTarjeta4(String numTarjeta4) {
		this.numTarjeta4 = numTarjeta4;
	}
	
	/**
	 * Obtiene el num tarjeta 5.
	 *
	 * @return el num tarjeta 5
	 */
	public String getNumTarjeta5() {
		return numTarjeta5;
	}
	
	/**
	 * Establece el num tarjeta 5.
	 *
	 * @param numTarjeta5 el nuevo num tarjeta 5
	 */
	public void setNumTarjeta5(String numTarjeta5) {
		this.numTarjeta5 = numTarjeta5;
	}	
	
	/**
	 * Obtiene el caducidad tarjeta 1.
	 *
	 * @return el caducidad tarjeta 1
	 */
	public String getCaducidadTarjeta1() {
		return caducidadTarjeta1;
	}
	
	/**
	 * Establece el caducidad tarjeta 1.
	 *
	 * @param caducidadTarjeta1 el nuevo caducidad tarjeta 1
	 */
	public void setCaducidadTarjeta1(String caducidadTarjeta1) {
		this.caducidadTarjeta1 = caducidadTarjeta1;
	}
	
	/**
	 * Obtiene el caducidad tarjeta 2.
	 *
	 * @return el caducidad tarjeta 2
	 */
	public String getCaducidadTarjeta2() {
		return caducidadTarjeta2;
	}
	
	/**
	 * Establece el caducidad tarjeta 2.
	 *
	 * @param caducidadTarjeta2 el nuevo caducidad tarjeta 2
	 */
	public void setCaducidadTarjeta2(String caducidadTarjeta2) {
		this.caducidadTarjeta2 = caducidadTarjeta2;
	}
	
	/**
	 * Obtiene el num solicitud.
	 *
	 * @return el num solicitud
	 */
	public String getNumSolicitud() {
		return numSolicitud;
	}
	
	/**
	 * Establece el num solicitud.
	 *
	 * @param numSolicitud el nuevo num solicitud
	 */
	public void setNumSolicitud(String numSolicitud) {
		this.numSolicitud = numSolicitud;
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
	 * Obtiene el submit.
	 *
	 * @return el submit
	 */
	public String getSubmit() {
		return submit;
	}
	
	/**
	 * Establece el submit.
	 *
	 * @param submit el nuevo submit
	 */
	public void setSubmit(String submit) {
		this.submit = submit;
	}
	
	/**
	 * Obtiene el numero solicitud.
	 *
	 * @return el numero solicitud
	 */
	public String getNumeroSolicitud() {
		return numeroSolicitud;
	}
	
	/**
	 * Establece el numero solicitud.
	 *
	 * @param numeroSolicitud el nuevo numero solicitud
	 */
	public void setNumeroSolicitud(String numeroSolicitud) {
		this.numeroSolicitud = numeroSolicitud;
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
	 * Obtiene el importe.
	 *
	 * @return el importe
	 */
	public Float getImporte() {
		return importe;
	}
	
	/**
	 * Establece el importe.
	 *
	 * @param importe el nuevo importe
	 */
	public void setImporte(Float importe) {
		this.importe = importe;
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
	 * Obtiene el motivo.
	 *
	 * @return el motivo
	 */
	public String getMotivo() {
		return motivo;
	}
	
	/**
	 * Establece el motivo.
	 *
	 * @param motivo el nuevo motivo
	 */
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	
	/**
	 * Obtiene el entidad ciudadano.
	 *
	 * @return el entidad ciudadano
	 */
	public String getEntidadCiudadano() {
		return entidadCiudadano;
	}
	
	/**
	 * Establece el entidad ciudadano.
	 *
	 * @param entidadCiudadano el nuevo entidad ciudadano
	 */
	public void setEntidadCiudadano(String entidadCiudadano) {
		this.entidadCiudadano = entidadCiudadano;
	}
	
	/**
	 * Obtiene el entidad tarjeta.
	 *
	 * @return el entidad tarjeta
	 */
	public String getEntidadTarjeta() {
		return entidadTarjeta;
	}
	
	/**
	 * Establece el entidad tarjeta.
	 *
	 * @param entidadTarjeta el nuevo entidad tarjeta
	 */
	public void setEntidadTarjeta(String entidadTarjeta) {
		this.entidadTarjeta = entidadTarjeta;
	}
	
	/**
	 * Obtiene el entidad adeudo.
	 *
	 * @return el entidad adeudo
	 */
	public String getEntidadAdeudo() {
		return entidadAdeudo;
	}
	
	/**
	 * Establece el entidad adeudo.
	 *
	 * @param entidadAdeudo el nuevo entidad adeudo
	 */
	public void setEntidadAdeudo(String entidadAdeudo) {
		this.entidadAdeudo = entidadAdeudo;
	}
	
	/**
	 * Obtiene el reduccion pago.
	 *
	 * @return el reduccion pago
	 */
	public String getReduccionPago() {
		return reduccionPago;
	}
	
	/**
	 * Establece el reduccion pago.
	 *
	 * @param reduccionPago el nuevo reduccion pago
	 */
	public void setReduccionPago(String reduccionPago) {
		this.reduccionPago = reduccionPago;
	}
	
	/**
	 * Obtiene el banco adeudo.
	 *
	 * @return el banco adeudo
	 */
	public String getBancoAdeudo() {
		return bancoAdeudo;
	}
	
	/**
	 * Establece el banco adeudo.
	 *
	 * @param bancoAdeudo el nuevo banco adeudo
	 */
	public void setBancoAdeudo(String bancoAdeudo) {
		this.bancoAdeudo = bancoAdeudo;
	}
	
	/**
	 * Obtiene el banco tarjeta.
	 *
	 * @return el banco tarjeta
	 */
	public String getBancoTarjeta() {
		return bancoTarjeta;
	}
	
	/**
	 * Establece el banco tarjeta.
	 *
	 * @param bancoTarjeta el nuevo banco tarjeta
	 */
	public void setBancoTarjeta(String bancoTarjeta) {
		this.bancoTarjeta = bancoTarjeta;
	}	
	
	/**
	 * Obtiene el motivo tarjeta.
	 *
	 * @return el motivo tarjeta
	 */
	public String getMotivoTarjeta() {
		return motivoTarjeta;
	}
	
	/**
	 * Establece el motivo tarjeta.
	 *
	 * @param motivoTarjeta el nuevo motivo tarjeta
	 */
	public void setMotivoTarjeta(String motivoTarjeta) {
		this.motivoTarjeta = motivoTarjeta;
	}
	
	/**
	 * Obtiene el motivo adeudo.
	 *
	 * @return el motivo adeudo
	 */
	public String getMotivoAdeudo() {
		return motivoAdeudo;
	}
	
	/**
	 * Establece el motivo adeudo.
	 *
	 * @param motivoAdeudo el nuevo motivo adeudo
	 */
	public void setMotivoAdeudo(String motivoAdeudo) {
		this.motivoAdeudo = motivoAdeudo;
	}
	
	/**
	 * Obtiene el apellido 1.
	 *
	 * @return el apellido 1
	 */
	public String getApellido1() {
		return apellido1;
	}
	
	/**
	 * Establece el apellido 1.
	 *
	 * @param apellido1 el nuevo apellido 1
	 */
	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}
	
	/**
	 * Obtiene el apellido 2.
	 *
	 * @return el apellido 2
	 */
	public String getApellido2() {
		return apellido2;
	}
	
	/**
	 * Establece el apellido 2.
	 *
	 * @param apellido2 el nuevo apellido 2
	 */
	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}
	
	/**
	 * Obtiene el origen firma.
	 *
	 * @return el origen firma
	 */
	public String getOrigenFirma() {
		return origenFirma;
	}
	
	/**
	 * Establece el origen firma.
	 *
	 * @param origenFirma el nuevo origen firma
	 */
	public void setOrigenFirma(String origenFirma) {
		this.origenFirma = origenFirma;
	}
	
	/**
	 * Obtiene el nombre parcial.
	 *
	 * @return el nombre parcial
	 */
	public String getNombreParcial() {
		return nombreParcial;
	}
	
	/**
	 * Establece el nombre parcial.
	 *
	 * @param nombreParcial el nuevo nombre parcial
	 */
	public void setNombreParcial(String nombreParcial) {
		this.nombreParcial = nombreParcial;
	}
	
	/**
	 * Obtiene el id forma acceso.
	 *
	 * @return el id forma acceso
	 */
	public String getIdFormaAcceso() {
		return idFormaAcceso;
	}
	
	/**
	 * Establece el id forma acceso.
	 *
	 * @param idFormaAcceso el nuevo id forma acceso
	 */
	public void setIdFormaAcceso(String idFormaAcceso) {
		this.idFormaAcceso = idFormaAcceso;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.SpringForm#validate(es.map.ips.common.spring.SpringMapping, javax.servlet.http.HttpServletRequest)
	 */
	public SpringErrors validate(SpringMapping mapping,	HttpServletRequest request) {
		SpringErrors actionErrors = new SpringErrors();
				
		if("Guardar".equals(accion)){
			if(Constantes.FORMA_PAGO_EXENTO_S.equals(formPago)){
				if(motivo == null || "".equals(motivo) || "0".equals(motivo)){
					request.setAttribute("errorMotivo", "errorMotivo");
					actionErrors.add("dsErrorMotivo", new SpringMessage("field.form.pagoSolicitud.error.motivoExento"));
				}
			}else{
				if(Constantes.FORMA_PAGO_ADEUDO_S.equals(formPago)){
					Boolean codAdeudo = false;
					if(bancoAdeudo == null || "".equals(bancoAdeudo) || "0".equals(bancoAdeudo)
							|| bancoAdeudo.length()<4){
						codAdeudo = true;
					}
					if(IBAN == null || "".equals(IBAN) || "0".equals(IBAN)
							|| IBAN.length()<24){
						codAdeudo = true;
					}
					
					if(codAdeudo == true){
						request.setAttribute("errorCuenta", "errorCuenta");
						actionErrors.add("dsErrorCuenta", new SpringMessage("field.form.pagoSolicitud.error.cuenta"));
					}
					if(entidadAdeudo == null || "".equals(entidadAdeudo) || "0".equals(entidadAdeudo)){
						request.setAttribute("errorEntidadAdeudo", "errorEntidadAdeudo");
						actionErrors.add("dsErrorEntidadAdeudo", new SpringMessage("field.form.pagoSolicitud.error.entidadAdeudo"));
					}		
				}else{
					if(Constantes.FORMA_PAGO_TARJETA_S.equals(formPago)){
						Boolean numTarjeta= false;
						Boolean caducidad = false;

						if(numTarjeta2 == null || "".equals(numTarjeta2) || "0".equals(numTarjeta2)
								|| numTarjeta2.length()<4){
							numTarjeta = true;
						}
						if(numTarjeta3 == null || "".equals(numTarjeta3) || "0".equals(numTarjeta3)
								|| numTarjeta3.length()<4){
							numTarjeta = true;
						}
						if(numTarjeta4 == null || "".equals(numTarjeta4) || "0".equals(numTarjeta4)
								|| numTarjeta4.length()<4){
							numTarjeta = true;
						}
						if(numTarjeta5 == null || "".equals(numTarjeta5) || "0".equals(numTarjeta5)
								|| numTarjeta5.length()<4){
							numTarjeta = true;							
						}
						if(numTarjeta == true){
							request.setAttribute("errorNumTarjeta", "errorNumTarjeta");
							actionErrors.add("dsErrorNumTarjeta", new SpringMessage("field.form.pagoSolicitud.error.numeroTarjeta"));
						}
						if(caducidadTarjeta1 == null || "".equals(caducidadTarjeta1) || "0".equals(caducidadTarjeta1)
								|| caducidadTarjeta1.length()<2){
							caducidad = true;
						}
						if(caducidadTarjeta2 == null || "".equals(caducidadTarjeta2) || "0".equals(caducidadTarjeta2)
								|| caducidadTarjeta2.length()<4){
							caducidad = true;							
						}
						if(caducidad == true){
							request.setAttribute("errorCaducidadTarjeta", "errorCaducidadTarjeta");
							actionErrors.add("dsErrorCaducidadTarjeta", new SpringMessage("field.form.pagoSolicitud.error.caducidadTarjeta"));
						}
						if(entidadTarjeta == null || "".equals(entidadTarjeta) || "0".equals(entidadTarjeta)){
							request.setAttribute("errorEntidadTarjeta", "errorEntidadTarjeta");
							actionErrors.add("dsErrorEntidadTarjeta", new SpringMessage("field.form.pagoSolicitud.error.entidadTarjeta"));
						}				
					}
				}
			}
				
			
		}
		return actionErrors;
		
	}
	
	/**
	 * Obtiene el esta en pago.
	 *
	 * @return el esta en pago
	 */
	public Boolean getEstaEnPago() {
		return estaEnPago;
	}
	
	/**
	 * Establece el esta en pago.
	 *
	 * @param estaEnPago el nuevo esta en pago
	 */
	public void setEstaEnPago(Boolean estaEnPago) {
		this.estaEnPago = estaEnPago;
	}
	
	/**
	 * Obtiene el iban.
	 *
	 * @return el iban
	 */
	public String getIBAN() {
		return IBAN;
	}
	
	/**
	 * Establece el iban.
	 *
	 * @param iBAN el nuevo iban
	 */
	public void setIBAN(String iBAN) {
		IBAN = iBAN;
	}
	
	/**
	 * Obtiene el serialversionuid.
	 *
	 * @return el serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
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
	 * Obtiene el provincia DD.
	 *
	 * @return el provincia DD
	 */
	public String getProvinciaDD() {
		return provinciaDD;
	}
	
	/**
	 * Establece el provincia DD.
	 *
	 * @param provinciaDD el nuevo provincia DD
	 */
	public void setProvinciaDD(String provinciaDD) {
		this.provinciaDD = provinciaDD;
	}
	
	/**
	 * Obtiene el id comunidad FN exento.
	 *
	 * @return el id comunidad FN exento
	 */
	public String getIdComunidadFNExento() {
		return idComunidadFNExento;
	}
	
	/**
	 * Establece el id comunidad FN exento.
	 *
	 * @param idComunidadFNExento el nuevo id comunidad FN exento
	 */
	public void setIdComunidadFNExento(String idComunidadFNExento) {
		this.idComunidadFNExento = idComunidadFNExento;
	}
	
	/**
	 * Obtiene el id comunidad FN tarjeta.
	 *
	 * @return el id comunidad FN tarjeta
	 */
	public String getIdComunidadFNTarjeta() {
		return idComunidadFNTarjeta;
	}
	
	/**
	 * Establece el id comunidad FN tarjeta.
	 *
	 * @param idComunidadFNTarjeta el nuevo id comunidad FN tarjeta
	 */
	public void setIdComunidadFNTarjeta(String idComunidadFNTarjeta) {
		this.idComunidadFNTarjeta = idComunidadFNTarjeta;
	}
	
	/**
	 * Obtiene el id comunidad FN cuenta.
	 *
	 * @return el id comunidad FN cuenta
	 */
	public String getIdComunidadFNCuenta() {
		return idComunidadFNCuenta;
	}
	
	/**
	 * Establece el id comunidad FN cuenta.
	 *
	 * @param idComunidadFNCuenta el nuevo id comunidad FN cuenta
	 */
	public void setIdComunidadFNCuenta(String idComunidadFNCuenta) {
		this.idComunidadFNCuenta = idComunidadFNCuenta;
	}
	
	/**
	 * Obtiene el id comunidad DD exento.
	 *
	 * @return el id comunidad DD exento
	 */
	public String getIdComunidadDDExento() {
		return idComunidadDDExento;
	}
	
	/**
	 * Establece el id comunidad DD exento.
	 *
	 * @param idComunidadDDExento el nuevo id comunidad DD exento
	 */
	public void setIdComunidadDDExento(String idComunidadDDExento) {
		this.idComunidadDDExento = idComunidadDDExento;
	}
	
	/**
	 * Obtiene el idtitulo FN exento.
	 *
	 * @return el idtitulo FN exento
	 */
	public String getIdtituloFNExento() {
		return idtituloFNExento;
	}
	
	/**
	 * Establece el idtitulo FN exento.
	 *
	 * @param idtituloFNExento el nuevo idtitulo FN exento
	 */
	public void setIdtituloFNExento(String idtituloFNExento) {
		this.idtituloFNExento = idtituloFNExento;
	}
	
	/**
	 * Obtiene el idtitulo FN tarjeta.
	 *
	 * @return el idtitulo FN tarjeta
	 */
	public String getIdtituloFNTarjeta() {
		return idtituloFNTarjeta;
	}
	
	/**
	 * Establece el idtitulo FN tarjeta.
	 *
	 * @param idtituloFNTarjeta el nuevo idtitulo FN tarjeta
	 */
	public void setIdtituloFNTarjeta(String idtituloFNTarjeta) {
		this.idtituloFNTarjeta = idtituloFNTarjeta;
	}
	
	/**
	 * Obtiene el idtitulo FN cuenta.
	 *
	 * @return el idtitulo FN cuenta
	 */
	public String getIdtituloFNCuenta() {
		return idtituloFNCuenta;
	}
	
	/**
	 * Establece el idtitulo FN cuenta.
	 *
	 * @param idtituloFNCuenta el nuevo idtitulo FN cuenta
	 */
	public void setIdtituloFNCuenta(String idtituloFNCuenta) {
		this.idtituloFNCuenta = idtituloFNCuenta;
	}
	
	/**
	 * Obtiene el titulo FN exento.
	 *
	 * @return el titulo FN exento
	 */
	public String getTituloFNExento() {
		return tituloFNExento;
	}
	
	/**
	 * Establece el titulo FN exento.
	 *
	 * @param tituloFNExento el nuevo titulo FN exento
	 */
	public void setTituloFNExento(String tituloFNExento) {
		this.tituloFNExento = tituloFNExento;
	}
	
	/**
	 * Obtiene el codigo tasa.
	 *
	 * @return el codigo tasa
	 */
	public String getCodigoTasa() {
		return codigoTasa;
	}
	
	/**
	 * Establece el codigo tasa.
	 *
	 * @param codigoTasa el nuevo codigo tasa
	 */
	public void setCodigoTasa(String codigoTasa) {
		this.codigoTasa = codigoTasa;
	}
	
	/**
	 * Obtiene el oficina.
	 *
	 * @return el oficina
	 */
	public String getOficina() {
		return oficina;
	}
	
	/**
	 * Establece el oficina.
	 *
	 * @param oficina el nuevo oficina
	 */
	public void setOficina(String oficina) {
		this.oficina = oficina;
	}
	
	/**
	 * Obtiene el dc.
	 *
	 * @return el dc
	 */
	public String getDc() {
		return dc;
	}
	
	/**
	 * Establece el dc.
	 *
	 * @param dc el nuevo dc
	 */
	public void setDc(String dc) {
		this.dc = dc;
	}
	
	/**
	 * Obtiene el cuenta.
	 *
	 * @return el cuenta
	 */
	public String getCuenta() {
		return cuenta;
	}
	
	/**
	 * Establece el cuenta.
	 *
	 * @param cuenta el nuevo cuenta
	 */
	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}
	
	/**
	 * Obtiene el tipo cargo.
	 *
	 * @return el tipo cargo
	 */
	public String getTipoCargo() {
		return tipoCargo;
	}
	
	/**
	 * Establece el tipo cargo.
	 *
	 * @param tipoCargo el nuevo tipo cargo
	 */
	public void setTipoCargo(String tipoCargo) {
		this.tipoCargo = tipoCargo;
	}
	
	/**
	 * Obtiene el cod banco.
	 *
	 * @return el cod banco
	 */
	public String getCodBanco() {
		return codBanco;
	}
	
	/**
	 * Establece el cod banco.
	 *
	 * @param codBanco el nuevo cod banco
	 */
	public void setCodBanco(String codBanco) {
		this.codBanco = codBanco;
	}
	
	/**
	 * Obtiene el tipo documento.
	 *
	 * @return el tipo documento
	 */
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	
	/**
	 * Establece el tipo documento.
	 *
	 * @param tipoDocumento el nuevo tipo documento
	 */
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	
	/**
	 * Obtiene el num tarjeta.
	 *
	 * @return el num tarjeta
	 */
	public String getNumTarjeta() {
		return numTarjeta;
	}
	
	/**
	 * Establece el num tarjeta.
	 *
	 * @param numTarjeta el nuevo num tarjeta
	 */
	public void setNumTarjeta(String numTarjeta) {
		this.numTarjeta = numTarjeta;
	}
	
	/**
	 * Obtiene el pago inicial modif.
	 *
	 * @return el pago inicial modif
	 */
	public String getPagoInicialModif() {
		return pagoInicialModif;
	}
	
	/**
	 * Establece el pago inicial modif.
	 *
	 * @param pagoInicialModif el nuevo pago inicial modif
	 */
	public void setPagoInicialModif(String pagoInicialModif) {
		this.pagoInicialModif = pagoInicialModif;
	}
	
	/**
	 * Obtiene el id solicitud inicial.
	 *
	 * @return el id solicitud inicial
	 */
	public String getIdSolicitudInicial() {
		return idSolicitudInicial;
	}
	
	/**
	 * Establece el id solicitud inicial.
	 *
	 * @param idSolicitudInicial el nuevo id solicitud inicial
	 */
	public void setIdSolicitudInicial(String idSolicitudInicial) {
		this.idSolicitudInicial = idSolicitudInicial;
	}
	
	/**
	 * Obtiene el nif titular.
	 *
	 * @return el nif titular
	 */
	public String getNifTitular() {
		return nifTitular;
	}
	
	/**
	 * Establece el nif titular.
	 *
	 * @param nifTitular el nuevo nif titular
	 */
	public void setNifTitular(String nifTitular) {
		this.nifTitular = nifTitular;
	}
	
	/**
	 * Obtiene el nombre titular.
	 *
	 * @return el nombre titular
	 */
	public String getNombreTitular() {
		return nombreTitular;
	}
	
	/**
	 * Establece el nombre titular.
	 *
	 * @param nombreTitular el nuevo nombre titular
	 */
	public void setNombreTitular(String nombreTitular) {
		this.nombreTitular = nombreTitular;
	}
	
	/**
	 * Obtiene el apellido 1 titular.
	 *
	 * @return el apellido 1 titular
	 */
	public String getApellido1Titular() {
		return apellido1Titular;
	}
	
	/**
	 * Establece el apellido 1 titular.
	 *
	 * @param apellido1Titular el nuevo apellido 1 titular
	 */
	public void setApellido1Titular(String apellido1Titular) {
		this.apellido1Titular = apellido1Titular;
	}
	
	/**
	 * Obtiene el apellido 2 titular.
	 *
	 * @return el apellido 2 titular
	 */
	public String getApellido2Titular() {
		return apellido2Titular;
	}
	
	/**
	 * Establece el apellido 2 titular.
	 *
	 * @param apellido2Titular el nuevo apellido 2 titular
	 */
	public void setApellido2Titular(String apellido2Titular) {
		this.apellido2Titular = apellido2Titular;
	}
	
	
}
