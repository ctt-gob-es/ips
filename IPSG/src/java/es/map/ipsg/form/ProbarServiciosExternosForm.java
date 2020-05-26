package es.map.ipsg.form;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import es.map.ips.common.spring.SpringErrors;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringMapping;
import es.map.ips.common.spring.SpringMessage;
import es.map.ipsg.util.Constantes;
import es.map.ipsg.util.Validacion;

/**
 * El Class ProbarServiciosExternosForm.
 */
public class ProbarServiciosExternosForm extends SpringForm {

	/** La constante serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** el servicio. */
	//Generales
	private String servicio;
	
	/** el accion. */
	private String accion;
	
	/** el resultado. */
	private String resultado;
	

	/** el nif nie. */
	//Pasarela de pago
	private String nifNie;
	
	/** el nombre. */
	private String nombre;
	
	/** el primer apellido. */
	private String primerApellido;
	
	/** el segundo apellido. */
	private String segundoApellido;
	
	/** el importe. */
	private String importe;
	
	/** el entidad financiera. */
	private String entidadFinanciera;
	
	/** el tipo pago. */
	private String tipoPago;
	
	/** el caducidad tarjeta 1. */
	private String caducidadTarjeta1;
	
	/** el caducidad tarjeta 2. */
	private String caducidadTarjeta2;
	
	/** el iban. */
	private String iban;
	
	/** el banco adeudo. */
	private String bancoAdeudo;
	
	/** el oficina. */
	private String oficina;
	
	/** el dc. */
	private String dc;
	
	/** el cuenta. */
	private String cuenta;
	
	/** el num tarjeta 2. */
	private String numTarjeta2;
	
	/** el num tarjeta 3. */
	private String numTarjeta3;
	
	/** el num tarjeta 4. */
	private String numTarjeta4;
	
	/** el num tarjeta 5. */
	private String numTarjeta5;
	
	/** el numero solicitud. */
	private String numeroSolicitud;
	
	/** el signature. */
	private String signature;
	
	/** el signer cert. */
	private String signerCert;
	
	/** el origen firma. */
	private String origenFirma;
	
	/** el nombre documento REC. */
	//REC
	private String nombreDocumentoREC;
	
	/** el cd oficina origen. */
	private String cdOficinaOrigen;
	
	/** el fe registro. */
	private String feRegistro;
	
	/** el nom documento. */
	private String nomDocumento;
	
	/** el certificado digital. */
	//@firma
	private String certificadoDigital;
	
	/** el numero justificante. */
	//EJB
	private String numeroJustificante;
	
	/** el tipo documento. */
	//Sistema de Ficheros
	private String tipoDocumento;
	
	/** el nombre documento. */
	private String nombreDocumento;
	
	/** el fichero. */
	private MultipartFile fichero;
	
	/** el ruta fichero. */
	private String rutaFichero;
	
	/** La constante STRING_ERRORSERVICIO. */
	private static final String STRING_ERRORSERVICIO = "errorServicio";
	
	/** La constante STRING_ERRORFICHERO. */
	private static final String STRING_ERRORFICHERO = "errorFichero";
	
	/** La constante STRING_ERRORNOMBREDOCUMENTOALFRESCO. */
	private static final String STRING_ERRORNOMBREDOCUMENTOALFRESCO = "errorNombreDocumentoAlfresco";
	
	/**
	 * Obtiene el servicio.
	 *
	 * @return el servicio
	 */
	//Get- Set
	public String getServicio() {
		return servicio;
	}
	
	/**
	 * Establece el servicio.
	 *
	 * @param servicio el nuevo servicio
	 */
	public void setServicio(String servicio) {
		this.servicio = servicio;
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
	 * Obtiene el resultado.
	 *
	 * @return el resultado
	 */
	public String getResultado() {
		return resultado;
	}
	
	/**
	 * Establece el resultado.
	 *
	 * @param resultado el nuevo resultado
	 */
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
	
	/**
	 * Obtiene el nif nie.
	 *
	 * @return el nif nie
	 */
	public String getNifNie() {
		return nifNie;
	}
	
	/**
	 * Establece el nif nie.
	 *
	 * @param nifNie el nuevo nif nie
	 */
	public void setNifNie(String nifNie) {
		this.nifNie = nifNie;
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
	 * Obtiene el iban.
	 *
	 * @return el iban
	 */
	public String getIban() {
		return iban;
	}
	
	/**
	 * Establece el iban.
	 *
	 * @param iban el nuevo iban
	 */
	public void setIban(String iban) {
		this.iban = iban;
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
	 * Obtiene el nombre documento REC.
	 *
	 * @return el nombre documento REC
	 */
	public String getNombreDocumentoREC() {
		return nombreDocumentoREC;
	}
	
	/**
	 * Establece el nombre documento REC.
	 *
	 * @param numeroRegistro el nuevo nombre documento REC
	 */
	public void setNombreDocumentoREC(String numeroRegistro) {
		this.nombreDocumentoREC = numeroRegistro;
	}
	
	/**
	 * Obtiene el cd oficina origen.
	 *
	 * @return el cd oficina origen
	 */
	public String getCdOficinaOrigen() {
		return cdOficinaOrigen;
	}
	
	/**
	 * Establece el cd oficina origen.
	 *
	 * @param cdOficinaOrigen el nuevo cd oficina origen
	 */
	public void setCdOficinaOrigen(String cdOficinaOrigen) {
		this.cdOficinaOrigen = cdOficinaOrigen;
	}
	
	/**
	 * Obtiene el fe registro.
	 *
	 * @return el fe registro
	 */
	public String getFeRegistro() {
		return feRegistro;
	}
	
	/**
	 * Establece el fe registro.
	 *
	 * @param feRegistro el nuevo fe registro
	 */
	public void setFeRegistro(String feRegistro) {
		this.feRegistro = feRegistro;
	}
	
	/**
	 * Obtiene el nom documento.
	 *
	 * @return el nom documento
	 */
	public String getNomDocumento() {
		return nomDocumento;
	}
	
	/**
	 * Establece el nom documento.
	 *
	 * @param nomDocumento el nuevo nom documento
	 */
	public void setNomDocumento(String nomDocumento) {
		this.nomDocumento = nomDocumento;
	}
	
	/**
	 * Obtiene el certificado digital.
	 *
	 * @return el certificado digital
	 */
	public String getCertificadoDigital() {
		return certificadoDigital;
	}
	
	/**
	 * Establece el certificado digital.
	 *
	 * @param certificadoDigital el nuevo certificado digital
	 */
	public void setCertificadoDigital(String certificadoDigital) {
		this.certificadoDigital = certificadoDigital;
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
	 * Obtiene el nombre documento.
	 *
	 * @return el nombre documento
	 */
	public String getNombreDocumento() {
		return nombreDocumento;
	}
	
	/**
	 * Establece el nombre documento.
	 *
	 * @param nombreDocumento el nuevo nombre documento
	 */
	public void setNombreDocumento(String nombreDocumento) {
		this.nombreDocumento = nombreDocumento;
	}
	
	/**
	 * Obtiene el fichero.
	 *
	 * @return el fichero
	 */
	public MultipartFile getFichero() {
		return fichero;
	}
	
	/**
	 * Establece el fichero.
	 *
	 * @param fichero el nuevo fichero
	 */
	public void setFichero(MultipartFile fichero) {
		this.fichero = fichero;
	}
	
	/**
	 * Obtiene el ruta fichero.
	 *
	 * @return el ruta fichero
	 */
	public String getRutaFichero() {
		return rutaFichero;
	}
	
	/**
	 * Establece el ruta fichero.
	 *
	 * @param rutaFichero el nuevo ruta fichero
	 */
	public void setRutaFichero(String rutaFichero) {
		this.rutaFichero = rutaFichero;
	}
	
	/**
	 * Obtiene el numero justificante.
	 *
	 * @return el numero justificante
	 */
	public String getNumeroJustificante() {
		return numeroJustificante;
	}
	
	/**
	 * Establece el numero justificante.
	 *
	 * @param numeroJustificante el nuevo numero justificante
	 */
	public void setNumeroJustificante(String numeroJustificante) {
		this.numeroJustificante = numeroJustificante;
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
	
	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.SpringForm#validate(es.map.ips.common.spring.SpringMapping, javax.servlet.http.HttpServletRequest)
	 */
	public SpringErrors validate(SpringMapping mapping,	HttpServletRequest request) {
		SpringErrors SpringErrors = new SpringErrors();
		Validacion validacion;
			if(accion!=null && accion.equals("Aceptar") && request.getAttribute(STRING_ERRORSERVICIO) == null
					&& request.getAttribute(STRING_ERRORFICHERO) == null && request.getAttribute("errorNombreDocumentoREC") == null
					&& request.getAttribute(STRING_ERRORNOMBREDOCUMENTOALFRESCO) == null){
				if(servicio.equals(String.valueOf(Constantes.PRUEBA_SERVICIO_EXTERNO_REC))&& !resultado.equals(Constantes.REGISTRO_RESULTADO_OK)){
					if(nombreDocumentoREC==null || nombreDocumentoREC.equals("")){
						request.setAttribute("errorNombreDocumentoREC", "errornnombreDocumentoREC");
						SpringErrors.add("dsErrorNombreDocumentoREC", new SpringMessage("field.ServiciosExternos.errorNombreDocumentoREC"));
						accion = null;
					}
				}
				validateAux(request,SpringErrors);
			} else {
				accion = null;
			}
		
		return SpringErrors;
	}
	
	/**
	 * Validate aux.
	 *
	 * @param request el request
	 * @param SpringErrors el spring errors
	 */
	public void validateAux(HttpServletRequest request,SpringErrors SpringErrors) {
		
		if(servicio.equals(String.valueOf(Constantes.PRUEBA_SERVICIO_EXTERNO_FILESYSTEM))&& !resultado.equals(Constantes.REGISTRO_RESULTADO_OK)){
			if(nombreDocumento==null || nombreDocumento.equals("")){
				request.setAttribute(STRING_ERRORNOMBREDOCUMENTOALFRESCO, STRING_ERRORNOMBREDOCUMENTOALFRESCO);
				SpringErrors.add("dsErrorNombreDocumentoAlfresco", new SpringMessage("field.ServiciosExternos.errorNombreDocumentoAlfresco"));
				accion = null;
			}
			if(fichero!=null && (fichero.getName()==null || fichero.getName().equals("") )){				
					request.setAttribute(STRING_ERRORFICHERO, STRING_ERRORFICHERO);
					SpringErrors.add("dsErrorFichero", new SpringMessage("field.ServiciosExternos.errorFichero"));
					accion = null;				
			}
		}
		if(servicio == null || servicio.equals("0")){
			request.setAttribute(STRING_ERRORSERVICIO, STRING_ERRORSERVICIO);
			SpringErrors.add("dsErrorServicio", new SpringMessage("field.ServiciosExternos.servicioNull"));
			accion = null;
		}
	}
}
