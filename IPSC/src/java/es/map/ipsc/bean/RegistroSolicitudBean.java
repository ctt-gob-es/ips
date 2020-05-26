package es.map.ipsc.bean;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

/**
 * El Class RegistroSolicitudBean.
 */
public class RegistroSolicitudBean {
	
	/** La constante MAX_FILES. */
	private static final int MAX_FILES = 100;

	/** el nombre. */
	private String nombre;
	
	/** el nif. */
	private String nif;
	
	/** el consentimiento. */
	private Boolean consentimiento;
	
	/** el des consentimiento. */
	private String desConsentimiento;
	
	/** motivo oposicion */
	private String motivoOposicion;
	
	private boolean idConsentimientoAEAT;
	
	private boolean idConsentimiento;
	
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
	
	/** el fecha registro. */
	private Date fechaRegistro;
	
	/** el fecha intento. */
	private Date fechaIntento;
	
	/** el num registro. */
	private String numRegistro;
	
	/** el resultado. */
	private String resultado;
	
	/** el solicitante. */
	private String solicitante;
	
	/** el oficina registro. */
	private String oficinaRegistro;
	
	
	/** el submit. */
	private String submit;
	
	/** el id solicitud. */
	private String idSolicitud;
	
	/** el id convocatoria. */
	private String idConvocatoria;
	
	/** el aniadir documentos. */
	private String aniadirDocumentos;
	
	/** el mostrar tabla. */
	private String mostrarTabla;
	
	/** el cod error. */
	private String codError;
	
	/** el des error. */
	private String desError;
	
	/** el fecha nacimiento. */
	//datos para el HTML
	private String fechaNacimiento;
	
	/** el localidad nacimiento. */
	private String localidadNacimiento;
	
	/** el provincia nacimiento. */
	private String provinciaNacimiento;
	
	/** el nacionalidad. */
	private String nacionalidad;
	
	/** el telefono. */
	private String telefono;
	
	/** el telefono aux. */
	private String telefonoAux;
	
	/** el telefono 1. */
	private String telefono1;
	
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
	
	/** el id log servicio. */
	private int idLogServicio;
	
	/** el documento file firefox. */
	private String[] documentoFileFirefox = new String[MAX_FILES];
	
	/** el rutas documentos. */
	private String[] rutasDocumentos = new String[MAX_FILES];	
	
	/** el documentos ficheros. */
	private String[] documentosFicheros = new String[MAX_FILES];
	
	/** el extracto firefox. */
	private String[] extractoFirefox = new String[MAX_FILES];
		
	/** el documento file. */
	private MultipartFile[] documentoFile = new MultipartFile[MAX_FILES];
	
	/** el tipo doc adjunto. */
	private String[] tipoDocAdjunto = new String[MAX_FILES];	
	
	/** el extracto. */
	private String[] extracto = new String[MAX_FILES];
	
	/** el xml firmado base 64. */
	private String[] xmlFirmadoBase64 = new String[MAX_FILES];
	
	/** el cert firmado base 64. */
	private String[] certFirmadoBase64 = new String[MAX_FILES];
	
	/** el relacion doc firma. */
	private Set<RelacionDocFirmaBean> relacionDocFirma = new HashSet<RelacionDocFirmaBean>();
	
	/** el interesados. */
	private Set<InteresadoBean> interesados = new HashSet<InteresadoBean>(0);
	
	/** el justificante. */
	private DocumentoBean justificante = new DocumentoBean();
	
	/** el extracto registro. */
	private DocumentoBean extractoRegistro = new DocumentoBean();
	
	/** el firma extracto registro. */
	private FirmaBean firmaExtractoRegistro = new FirmaBean();
	
	// Importe grupo A1 a partir del cual calcular el procentaje para Promoción Interna

	
	/**
	 * Obtiene el mostrar tabla.
	 *
	 * @return el mostrar tabla
	 */
	public String getMostrarTabla() {
		return mostrarTabla;
	}
	
	/**
	 * Establece el mostrar tabla.
	 *
	 * @param mostrarTabla el nuevo mostrar tabla
	 */
	public void setMostrarTabla(String mostrarTabla) {
		this.mostrarTabla = mostrarTabla;
	}
	
	/**
	 * Obtiene el fecha intento.
	 *
	 * @return el fecha intento
	 */
	public Date getFechaIntento() {
		return fechaIntento;
	}
	
	/**
	 * Establece el fecha intento.
	 *
	 * @param fechaIntento el nuevo fecha intento
	 */
	public void setFechaIntento(Date fechaIntento) {
		this.fechaIntento = fechaIntento;
	}
	
	/**
	 * Obtiene el cod error.
	 *
	 * @return el cod error
	 */
	public String getCodError() {
		return codError;
	}
	
	/**
	 * Establece el cod error.
	 *
	 * @param codError el nuevo cod error
	 */
	public void setCodError(String codError) {
		this.codError = codError;
	}
	
	/**
	 * Obtiene el des error.
	 *
	 * @return el des error
	 */
	public String getDesError() {
		return desError;
	}
	
	/**
	 * Establece el des error.
	 *
	 * @param desError el nuevo des error
	 */
	public void setDesError(String desError) {
		this.desError = desError;
	}
	
	/**
	 * Obtiene el interesados.
	 *
	 * @return el interesados
	 */
	public Set<InteresadoBean> getInteresados() {
		return interesados;
	}
	
	/**
	 * Establece el interesados.
	 *
	 * @param interesados el nuevo interesados
	 */
	public void setInteresados(Set<InteresadoBean> interesados) {
		this.interesados = interesados;
	}
	
	/**
	 * Obtiene el justificante.
	 *
	 * @return el justificante
	 */
	public DocumentoBean getJustificante() {
		return justificante;
	}
	
	/**
	 * Establece el justificante.
	 *
	 * @param justificante el nuevo justificante
	 */
	public void setJustificante(DocumentoBean justificante) {
		this.justificante = justificante;
	}
	
	/**
	 * Obtiene el extracto registro.
	 *
	 * @return el extracto registro
	 */
	public DocumentoBean getExtractoRegistro() {
		return extractoRegistro;
	}
	
	/**
	 * Establece el extracto registro.
	 *
	 * @param extractoRegistro el nuevo extracto registro
	 */
	public void setExtractoRegistro(DocumentoBean extractoRegistro) {
		this.extractoRegistro = extractoRegistro;
	}
	
	/**
	 * Obtiene el firma extracto registro.
	 *
	 * @return el firma extracto registro
	 */
	public FirmaBean getFirmaExtractoRegistro() {
		return firmaExtractoRegistro;
	}
	
	/**
	 * Establece el firma extracto registro.
	 *
	 * @param firmaExtractoRegistro el nuevo firma extracto registro
	 */
	public void setFirmaExtractoRegistro(FirmaBean firmaExtractoRegistro) {
		this.firmaExtractoRegistro = firmaExtractoRegistro;
	}
	
	/**
	 * Obtiene el relacion doc firma.
	 *
	 * @return el relacion doc firma
	 */
	public Set<RelacionDocFirmaBean> getRelacionDocFirma() {
		return relacionDocFirma;
	}
	
	/**
	 * Establece el relacion doc firma.
	 *
	 * @param relacionDocFirma el nuevo relacion doc firma
	 */
	public void setRelacionDocFirma(Set<RelacionDocFirmaBean> relacionDocFirma) {
		this.relacionDocFirma = relacionDocFirma;
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
	 * Obtiene el aniadir documentos.
	 *
	 * @return el aniadir documentos
	 */
	public String getAniadirDocumentos() {
		return aniadirDocumentos;
	}
	
	/**
	 * Establece el aniadir documentos.
	 *
	 * @param aniadirDocumentos el nuevo aniadir documentos
	 */
	public void setAniadirDocumentos(String aniadirDocumentos) {
		this.aniadirDocumentos = aniadirDocumentos;
	}
	
	/**
	 * Obtiene el num registro.
	 *
	 * @return el num registro
	 */
	public String getNumRegistro() {
		return numRegistro;
	}
	
	/**
	 * Establece el num registro.
	 *
	 * @param numRegistro el nuevo num registro
	 */
	public void setNumRegistro(String numRegistro) {
		this.numRegistro = numRegistro;
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
	 * Obtiene el solicitante.
	 *
	 * @return el solicitante
	 */
	public String getSolicitante() {
		return solicitante;
	}
	
	/**
	 * Establece el solicitante.
	 *
	 * @param solicitante el nuevo solicitante
	 */
	public void setSolicitante(String solicitante) {
		this.solicitante = solicitante;
	}
	
	/**
	 * Obtiene el oficina registro.
	 *
	 * @return el oficina registro
	 */
	public String getOficinaRegistro() {
		return oficinaRegistro;
	}
	
	/**
	 * Establece el oficina registro.
	 *
	 * @param oficinaRegistro el nuevo oficina registro
	 */
	public void setOficinaRegistro(String oficinaRegistro) {
		this.oficinaRegistro = oficinaRegistro;
	}
	
	/**
	 * Obtiene el fecha registro.
	 *
	 * @return el fecha registro
	 */
	public Date getFechaRegistro() {
		return fechaRegistro;
	}
	
	/**
	 * Establece el fecha registro.
	 *
	 * @param fechaRegistro el nuevo fecha registro
	 */
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
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
	
	
	//Accedentes y mutadores html
	
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
	 * Obtiene el documento file firefox.
	 *
	 * @return el documento file firefox
	 */
	public String[] getDocumentoFileFirefox() {
		return documentoFileFirefox;
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
	public MultipartFile[] getDocumentoFile() {
		return documentoFile;
	}
	
	/**
	 * Establece el documento file.
	 *
	 * @param documentoFile el nuevo documento file
	 */
	public void setDocumentoFile(MultipartFile[] documentoFile) {
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
	 * Establece el cert firmado base 64.
	 *
	 * @param certFirmadoBase64 el nuevo cert firmado base 64
	 */
	public void setCertFirmadoBase64(String[] certFirmadoBase64) {
		this.certFirmadoBase64 = certFirmadoBase64;
	}
	
	/**
	 * Obtiene el id log servicio.
	 *
	 * @return el id log servicio
	 */
	public int getIdLogServicio() {
		return idLogServicio;
	}
	
	/**
	 * Establece el id log servicio.
	 *
	 * @param idLogServicio el nuevo id log servicio
	 */
	public void setIdLogServicio(int idLogServicio) {
		this.idLogServicio = idLogServicio;
	}
	
	/**
	 * Obtiene el consentimiento.
	 *
	 * @return el consentimiento
	 */
	public Boolean getConsentimiento() {
		return consentimiento;
	}
	
	/**
	 * Establece el consentimiento.
	 *
	 * @param consentimiento el nuevo consentimiento
	 */
	public void setConsentimiento(Boolean consentimiento) {
		this.consentimiento = consentimiento;
	}
	
	/**
	 * Obtiene el des consentimiento.
	 *
	 * @return el des consentimiento
	 */
	public String getDesConsentimiento() {
		return desConsentimiento;
	}
	
	/**
	 * Establece el des consentimiento.
	 *
	 * @param desConsentimiento el nuevo des consentimiento
	 */
	public void setDesConsentimiento(String desConsentimiento) {
		this.desConsentimiento = desConsentimiento;
	}
	
	/**
	 * Obtiene el telefono aux.
	 *
	 * @return el telefono aux
	 */
	public String getTelefonoAux() {
		return telefonoAux;
	}
	
	/**
	 * Establece el telefono aux.
	 *
	 * @param telefonoAux el nuevo telefono aux
	 */
	public void setTelefonoAux(String telefonoAux) {
		this.telefonoAux = telefonoAux;
	}
	
	/**
	 * Obtiene el telefono 1.
	 *
	 * @return el telefono 1
	 */
	public String getTelefono1() {
		return telefono1;
	}
	
	/**
	 * Establece el telefono 1.
	 *
	 * @param telefono1 el nuevo telefono 1
	 */
	public void setTelefono1(String telefono1) {
		this.telefono1 = telefono1;
	}

	public String getMotivoOposicion() {
		return motivoOposicion;
	}

	public void setMotivoOposicion(String motivoOposicion) {
		this.motivoOposicion = motivoOposicion;
	}

	public boolean getIdConsentimientoAEAT() {
		return idConsentimientoAEAT;
	}

	public void setIdConsentimientoAEAT(boolean idConsentimientoAEAT) {
		this.idConsentimientoAEAT = idConsentimientoAEAT;
	}

	public boolean isIdConsentimiento() {
		return idConsentimiento;
	}

	public void setIdConsentimiento(boolean idConsentimiento) {
		this.idConsentimiento = idConsentimiento;
	}
}
