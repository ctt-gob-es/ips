package es.map.ipsc.bean;

import ePago.gob.es.schemas.TiposCargo;
import ePago.gob.es.schemas.TiposDocumento;


/**
 * El Class DetallePasarelaBean.
 */
public class DetallePasarelaBean {
	
	/** el id solicitud. */
	private String idSolicitud;
	
	/** el numero solicitud. */
	private String numeroSolicitud;
	
	/** el codigo tasa. */
	private String codigoTasa;
	
	/** el importe. */
	private String importe;
	
	/** el tipo documento. */
	private TiposDocumento tipoDocumento;
	
	/** el nif. */
	private String nif;
	
	/** el nombre. */
	private String nombre;
	
	/** el apellido 1. */
	private String apellido1;
	
	/** el apellido 2. */
	private String apellido2;
	
	/** el forma pago. */
	private String formaPago;
	
	/** el banco tarjeta. */
	//Tarjeta
	private String bancoTarjeta;
	
	/** el num tarjeta 2. */
	private String numTarjeta2;
	
	/** el num tarjeta 3. */
	private String numTarjeta3;
	
	/** el num tarjeta 4. */
	private String numTarjeta4;
	
	/** el caducidad tarjeta 1. */
	private String caducidadTarjeta1;
	
	/** el caducidad tarjeta 2. */
	private String caducidadTarjeta2;
	
	/** el banco adeudo. */
	//Adeudo
	private String bancoAdeudo;
	
	/** el oficina. */
	private String oficina;
	
	/** el dc. */
	private String dc;
	
	/** el cuenta. */
	private String cuenta;
	
	
	/** el tipo cargo. */
	private TiposCargo tipoCargo;
	
	/** el cod banco. */
	private String codBanco;
	
	/** el origen firma. */
	//Firma
	private String origenFirma;
	
	/** el signature. */
	private String signature;
	
	/** el signer cert. */
	private String signerCert;
	
	/** el fecha operacion. */
	//Fecha
	private String fechaOperacion;
	
	
	/**
	 * Obtiene el fecha operacion.
	 *
	 * @return el fecha operacion
	 */
	public String getFechaOperacion() {
		return fechaOperacion;
	}
	
	/**
	 * Establece el fecha operacion.
	 *
	 * @param fechaOperacion el nuevo fecha operacion
	 */
	public void setFechaOperacion(String fechaOperacion) {
		this.fechaOperacion = fechaOperacion;
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
	 * Obtiene el tipo documento.
	 *
	 * @return el tipo documento
	 */
	public TiposDocumento getTipoDocumento() {
		return tipoDocumento;
	}
	
	/**
	 * Establece el tipo documento.
	 *
	 * @param tipoDocumento el nuevo tipo documento
	 */
	public void setTipoDocumento(TiposDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
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
	 * Obtiene el tipo cargo.
	 *
	 * @return el tipo cargo
	 */
	public TiposCargo getTipoCargo() {
		return tipoCargo;
	}
	
	/**
	 * Establece el tipo cargo.
	 *
	 * @param tipoCargo el nuevo tipo cargo
	 */
	public void setTipoCargo(TiposCargo tipoCargo) {
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
	
	
	
	
	
}
