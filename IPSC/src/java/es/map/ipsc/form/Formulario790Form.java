package es.map.ipsc.form;

import java.util.List;

import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.Discapacidad;
import es.map.ips.model.OtrosTitulos;
import es.map.ipsc.bean.CamposPropiosBean;

/**
 * El Class Formulario790Form.
 */
public class Formulario790Form extends SpringForm{
		
	/** La constante serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** el centro gestor. */
	private String centroGestor;
	
	/** el cod centro gestor. */
	private String codCentroGestor;
	
	/** el codigo tasa. */
	private String codigoTasa;
	
	/** el numero justificante. */
	private String numeroJustificante;
	
	/** el anio convocatoria. */
	private Long anioConvocatoria;
	
	/** el nif. */
	private String nif;
	
	/** el nombre. */
	private String nombre;
	
	/** el primer apellido. */
	private String primerApellido;
	
	/** el segundo apellido. */
	private String segundoApellido;
	
	/** el dia nacimiento. */
	private String diaNacimiento;
	
	/** el mes nacimiento. */
	private String mesNacimiento;
	
	/** el anio nacimiento. */
	private String anioNacimiento;
	
	/** el sexo. */
	private String sexo;
	
	/** el nacionalidad. */
	private String nacionalidad;
	
	/** el correo electronicos. */
	private String correoElectronicos;
	
	/** el telefono. */
	private String telefono;
	
	/** el telefono aux. */
	private String telefonoAux;
	
	/** el calle domicilio. */
	private String calleDomicilio;
	
	/** el codigo postal domicilio. */
	private String codigoPostalDomicilio;
	
	/** el municipio domicilio. */
	private String municipioDomicilio;
	
	/** el provincia domicilio. */
	private Long provinciaDomicilio;
	
	/** el pais. */
	private Long pais;
	
	/** el comprobar. */
	private String comprobar;
	
	/** el comunidad defecto. */
	private Long comunidadDefecto;
	
	/** el comunidad FN. */
	private Long comunidadFN;
	
	/** el comunidad DD. */
	private Long comunidadDD;
	
	/** el numero titulo FN. */
	private String numeroTituloFN;
	
	/** el id cuerpo escala. */
	private String idCuerpoEscala;
	
	/** el des cuerpo escala. */
	private String desCuerpoEscala;
	
	/** el id ministerio. */
	private String idMinisterio;
	
	/** el des ministerio. */
	private String desMinisterio;
	
	/** el des ministerio convocante. */
	private String desMinisterioConvocante;
	
	/** el id forma acceso. */
	private String idFormaAcceso;
	
	/** el des forma acceso. */
	private String desFormaAcceso;
	
	/** el id centro gestor. */
	private String idCentroGestor;
	
	/** el des centro gestor. */
	private String desCentroGestor;
	
	/** el especialidad. */
	private Long especialidad;
	
	/** el forma acceso. */
	private String formaAcceso;
	
	/** el ministerio. */
	private String ministerio;
	
	/** el ministerio manual. */
	private String ministerioManual;
	
	/** el centro gestor manual. */
	private String centroGestorManual;
	
	/** el cuerpo escala manual. */
	private String cuerpoEscalaManual;
	
	/** el especialidad manual. */
	private String especialidadManual;
	
	/** el forma acceso manual. */
	private String formaAccesoManual;
	
	/** el titulo manual. */
	private String tituloManual;
	
	/** el ministerio automatico. */
	private String ministerioAutomatico;
	
	/** el Fecha boe. */
	private String FechaBoe;
	
	/** el dia fecha boe. */
	private String diaFechaBoe;
	
	/** el mes fecha boe. */
	private String mesFechaBoe;
	
	/** el anio fecha boe. */
	private String anioFechaBoe;
	
	/** el provincia examen. */
	private Long provinciaExamen;
	
	/** el porcentaje discapacidad. */
	private Long porcentajeDiscapacidad;
	
	/** el tipo discapacidad. */
	private Long tipoDiscapacidad;
	
	/** el reserva discapacidad. */
	private String reservaDiscapacidad;
	
	/** el detalle discapacidad. */
	private String detalleDiscapacidad;
	
	/** el titulo oficial. */
	private Long tituloOficial;
	
	/** el cod titutlo oficial. */
	private String codTitutloOficial;
	
	/** el otros titulos. */
	private String otrosTitulos;

	/** el campo propio 0. */
	private String campoPropio0;
	
	/** el campo propio 1. */
	private String campoPropio1;
	
	/** el campo propio 2. */
	private String campoPropio2;
	
	/** el campo propio 3. */
	private String campoPropio3;
	
	/** el persona firmante. */
	private String personaFirmante;
  	
	  /** el importe solicitud. */
	  private String importeSolicitud;
  	
	  /** el importe solicitud decimal. */
	  private String importeSolicitudDecimal;
	
	/** el forma pago. */
	private String formaPago;
	
	/** el motivo red ex. */
	private String motivoRedEx;
	
	/** el iban. */
	private String iban;
	
	/** el entidad. */
	private String entidad;
	
	/** el oficina. */
	private String oficina;
	
	/** el digito conttrol. */
	private String digitoConttrol;
	
	/** el numero cuenta. */
	private String numeroCuenta;
	
	/** el lugar firma. */
	private String lugarFirma;
	
	/** el fecha firma. */
	private String fechaFirma;
	
	/** el id. */
	private Long id;
	
	/** el cod forma acceso. */
	private String codFormaAcceso;
	
	/** el cuerpo escala. */
	private String cuerpoEscala;
	
	/** el codigo provincia domicilio. */
	private String codigoProvinciaDomicilio;
	
	/** el codigo pais domicilio. */
	private String codigoPaisDomicilio;
	
	/** el codigo cuerpo escala. */
	private String codigoCuerpoEscala;
	
	/** el codigo especialidad. */
	private String codigoEspecialidad;
	
	/** el codigo ministerio. */
	private String codigoMinisterio;
	
	/** el codigo provincia examen. */
	private String codigoProvinciaExamen;
	
	/** el codigo titulo exigido. */
	private String codigoTituloExigido;
	
	/** el codigo titulo otros. */
	private String codigoTituloOtros;
	
	/** el codigo municipio. */
	private String codigoMunicipio;
	
	/** el codigo nacionalidad. */
	private String codigoNacionalidad;
	
	/** el consentimiento. */
	private Boolean consentimiento;
	
	/** motivo oposicion */
	private String motivoOposicion;
	
	/** el id convocatoria. */
	private Long idConvocatoria;
	
	/** el porcentaje min discapacidad. */
	private String porcentajeMinDiscapacidad;
	
	/** el num modelo. */
	private String numModelo;
	
	/** el secretario jud. */
	private boolean secretarioJud;
	
	/** el codigo cuerpo escala aux. */
	private String codigoCuerpoEscalaAux;
	
	/** el campos propios. */
	private List<CamposPropiosBean> camposPropios;
	
	/** el otros titulos list. */
	private List<OtrosTitulos> otrosTitulosList;
	
	/** el discapacidad list. */
	private List<Discapacidad> discapacidadList;
	
	/** el subsanar. */
	private boolean subsanar;
	
	/** el autorizar. */
	private boolean autorizar;
	
	private String enlace;
	
	/** el ocultar datos propios. */
	private Character ocultarDatosPropios;
	
	/**
	 * Obtiene el centro gestor manual.
	 *
	 * @return el centro gestor manual
	 */
	public String getCentroGestorManual() {
		return centroGestorManual;
	}
	
	/**
	 * Establece el centro gestor manual.
	 *
	 * @param centroGestorManual el nuevo centro gestor manual
	 */
	public void setCentroGestorManual(String centroGestorManual) {
		this.centroGestorManual = centroGestorManual;
	}
	
	/**
	 * Obtiene el cuerpo escala manual.
	 *
	 * @return el cuerpo escala manual
	 */
	public String getCuerpoEscalaManual() {
		return cuerpoEscalaManual;
	}
	
	/**
	 * Establece el cuerpo escala manual.
	 *
	 * @param cuerpoEscalaManual el nuevo cuerpo escala manual
	 */
	public void setCuerpoEscalaManual(String cuerpoEscalaManual) {
		this.cuerpoEscalaManual = cuerpoEscalaManual;
	}
	
	/**
	 * Obtiene el especialidad manual.
	 *
	 * @return el especialidad manual
	 */
	public String getEspecialidadManual() {
		return especialidadManual;
	}
	
	/**
	 * Establece el especialidad manual.
	 *
	 * @param especialidadManual el nuevo especialidad manual
	 */
	public void setEspecialidadManual(String especialidadManual) {
		this.especialidadManual = especialidadManual;
	}
	
	/**
	 * Obtiene el ministerio automatico.
	 *
	 * @return el ministerio automatico
	 */
	public String getMinisterioAutomatico() {
		return ministerioAutomatico;
	}
	
	/**
	 * Establece el ministerio automatico.
	 *
	 * @param ministerioAutomatico el nuevo ministerio automatico
	 */
	public void setMinisterioAutomatico(String ministerioAutomatico) {
		this.ministerioAutomatico = ministerioAutomatico;
	}
	
	/**
	 * Obtiene el ministerio manual.
	 *
	 * @return el ministerio manual
	 */
	public String getMinisterioManual() {
		return ministerioManual;
	}
	
	/**
	 * Establece el ministerio manual.
	 *
	 * @param ministerioManual el nuevo ministerio manual
	 */
	public void setMinisterioManual(String ministerioManual) {
		this.ministerioManual = ministerioManual;
	}
	
	/**
	 * Obtiene el forma acceso manual.
	 *
	 * @return el forma acceso manual
	 */
	public String getFormaAccesoManual() {
		return formaAccesoManual;
	}
	
	/**
	 * Establece el forma acceso manual.
	 *
	 * @param formaAccesoManual el nuevo forma acceso manual
	 */
	public void setFormaAccesoManual(String formaAccesoManual) {
		this.formaAccesoManual = formaAccesoManual;
	}
	
	/**
	 * Obtiene el titulo manual.
	 *
	 * @return el titulo manual
	 */
	public String getTituloManual() {
		return tituloManual;
	}
	
	/**
	 * Establece el titulo manual.
	 *
	 * @param tituloManual el nuevo titulo manual
	 */
	public void setTituloManual(String tituloManual) {
		this.tituloManual = tituloManual;
	}
	
	/**
	 * Obtiene el porcentaje min discapacidad.
	 *
	 * @return el porcentaje min discapacidad
	 */
	public String getPorcentajeMinDiscapacidad() {
		return porcentajeMinDiscapacidad;
	}
	
	/**
	 * Establece el porcentaje min discapacidad.
	 *
	 * @param porcentajeMinDiscapacidad el nuevo porcentaje min discapacidad
	 */
	public void setPorcentajeMinDiscapacidad(String porcentajeMinDiscapacidad) {
		this.porcentajeMinDiscapacidad = porcentajeMinDiscapacidad;
	}
	
	/**
	 * Obtiene el cod titutlo oficial.
	 *
	 * @return el cod titutlo oficial
	 */
	public String getCodTitutloOficial() {
		return codTitutloOficial;
	}
	
	/**
	 * Establece el cod titutlo oficial.
	 *
	 * @param codTitutloOficial el nuevo cod titutlo oficial
	 */
	public void setCodTitutloOficial(String codTitutloOficial) {
		this.codTitutloOficial = codTitutloOficial;
	}
	
	/**
	 * Obtiene el cod centro gestor.
	 *
	 * @return el cod centro gestor
	 */
	public String getCodCentroGestor() {
		return codCentroGestor;
	}
	
	/**
	 * Establece el cod centro gestor.
	 *
	 * @param codCentroGestor el nuevo cod centro gestor
	 */
	public void setCodCentroGestor(String codCentroGestor) {
		this.codCentroGestor = codCentroGestor;
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
	 * Obtiene el cuerpo escala.
	 *
	 * @return el cuerpo escala
	 */
	public String getCuerpoEscala() {
		return cuerpoEscala;
	}
	
	/**
	 * Establece el cuerpo escala.
	 *
	 * @param cuerpoEscala el nuevo cuerpo escala
	 */
	public void setCuerpoEscala(String cuerpoEscala) {
		this.cuerpoEscala = cuerpoEscala;
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
	 * Obtiene el id centro gestor.
	 *
	 * @return el id centro gestor
	 */
	public String getIdCentroGestor() {
		return idCentroGestor;
	}
	
	/**
	 * Establece el id centro gestor.
	 *
	 * @param idCentroGestor el nuevo id centro gestor
	 */
	public void setIdCentroGestor(String idCentroGestor) {
		this.idCentroGestor = idCentroGestor;
	}
	
	/**
	 * Obtiene el des centro gestor.
	 *
	 * @return el des centro gestor
	 */
	public String getDesCentroGestor() {
		return desCentroGestor;
	}
	
	/**
	 * Establece el des centro gestor.
	 *
	 * @param desCentroGestor el nuevo des centro gestor
	 */
	public void setDesCentroGestor(String desCentroGestor) {
		this.desCentroGestor = desCentroGestor;
	}
	
	/**
	 * Obtiene el id ministerio.
	 *
	 * @return el id ministerio
	 */
	public String getIdMinisterio() {
		return idMinisterio;
	}
	
	/**
	 * Establece el id ministerio.
	 *
	 * @param idMinisterio el nuevo id ministerio
	 */
	public void setIdMinisterio(String idMinisterio) {
		this.idMinisterio = idMinisterio;
	}
	
	/**
	 * Obtiene el des ministerio.
	 *
	 * @return el des ministerio
	 */
	public String getDesMinisterio() {
		return desMinisterio;
	}
	
	/**
	 * Establece el des ministerio.
	 *
	 * @param desMinisterio el nuevo des ministerio
	 */
	public void setDesMinisterio(String desMinisterio) {
		this.desMinisterio = desMinisterio;
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
	
	/**
	 * Obtiene el des forma acceso.
	 *
	 * @return el des forma acceso
	 */
	public String getDesFormaAcceso() {
		return desFormaAcceso;
	}
	
	/**
	 * Establece el des forma acceso.
	 *
	 * @param desFormaAcceso el nuevo des forma acceso
	 */
	public void setDesFormaAcceso(String desFormaAcceso) {
		this.desFormaAcceso = desFormaAcceso;
	}
	
	/**
	 * Obtiene el des cuerpo escala.
	 *
	 * @return el des cuerpo escala
	 */
	public String getDesCuerpoEscala() {
		return desCuerpoEscala;
	}
	
	/**
	 * Establece el des cuerpo escala.
	 *
	 * @param desCuerpoEscala el nuevo des cuerpo escala
	 */
	public void setDesCuerpoEscala(String desCuerpoEscala) {
		this.desCuerpoEscala = desCuerpoEscala;
	}
	
	/**
	 * Obtiene el cod forma acceso.
	 *
	 * @return el cod forma acceso
	 */
	public String getCodFormaAcceso() {
		return codFormaAcceso;
	}
	
	/**
	 * Establece el cod forma acceso.
	 *
	 * @param codFormaAcceso el nuevo cod forma acceso
	 */
	public void setCodFormaAcceso(String codFormaAcceso) {
		this.codFormaAcceso = codFormaAcceso;
	}
	
	/**
	 * Obtiene el centro gestor.
	 *
	 * @return el centro gestor
	 */
	public String getCentroGestor() {
		return centroGestor;
	}
	
	/**
	 * Establece el centro gestor.
	 *
	 * @param centroGestor el nuevo centro gestor
	 */
	public void setCentroGestor(String centroGestor) {
		this.centroGestor = centroGestor;
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
	 * Obtiene el anio convocatoria.
	 *
	 * @return el anio convocatoria
	 */
	public Long getAnioConvocatoria() {
		return anioConvocatoria;
	}
	
	/**
	 * Establece el anio convocatoria.
	 *
	 * @param anioConvocatoria el nuevo anio convocatoria
	 */
	public void setAnioConvocatoria(Long anioConvocatoria) {
		this.anioConvocatoria = anioConvocatoria;
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
	 * Obtiene el dia nacimiento.
	 *
	 * @return el dia nacimiento
	 */
	public String getDiaNacimiento() {
		return diaNacimiento;
	}
	
	/**
	 * Establece el dia nacimiento.
	 *
	 * @param diaNacimiento el nuevo dia nacimiento
	 */
	public void setDiaNacimiento(String diaNacimiento) {
		this.diaNacimiento = diaNacimiento;
	}
	
	/**
	 * Obtiene el mes nacimiento.
	 *
	 * @return el mes nacimiento
	 */
	public String getMesNacimiento() {
		return mesNacimiento;
	}
	
	/**
	 * Establece el mes nacimiento.
	 *
	 * @param mesNacimiento el nuevo mes nacimiento
	 */
	public void setMesNacimiento(String mesNacimiento) {
		this.mesNacimiento = mesNacimiento;
	}
	
	/**
	 * Obtiene el anio nacimiento.
	 *
	 * @return el anio nacimiento
	 */
	public String getAnioNacimiento() {
		return anioNacimiento;
	}
	
	/**
	 * Establece el anio nacimiento.
	 *
	 * @param anioNacimiento el nuevo anio nacimiento
	 */
	public void setAnioNacimiento(String anioNacimiento) {
		this.anioNacimiento = anioNacimiento;
	}
	
	/**
	 * Obtiene el sexo.
	 *
	 * @return el sexo
	 */
	public String getSexo() {
		return sexo;
	}
	
	/**
	 * Establece el sexo.
	 *
	 * @param sexo el nuevo sexo
	 */
	public void setSexo(String sexo) {
		this.sexo = sexo;
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
	 * Obtiene el correo electronicos.
	 *
	 * @return el correo electronicos
	 */
	public String getCorreoElectronicos() {
		return correoElectronicos;
	}
	
	/**
	 * Establece el correo electronicos.
	 *
	 * @param correoElectronicos el nuevo correo electronicos
	 */
	public void setCorreoElectronicos(String correoElectronicos) {
		this.correoElectronicos = correoElectronicos;
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
	 * Obtiene el calle domicilio.
	 *
	 * @return el calle domicilio
	 */
	public String getCalleDomicilio() {
		return calleDomicilio;
	}
	
	/**
	 * Establece el calle domicilio.
	 *
	 * @param calleDomicilio el nuevo calle domicilio
	 */
	public void setCalleDomicilio(String calleDomicilio) {
		this.calleDomicilio = calleDomicilio;
	}
	
	/**
	 * Obtiene el codigo postal domicilio.
	 *
	 * @return el codigo postal domicilio
	 */
	public String getCodigoPostalDomicilio() {
		return codigoPostalDomicilio;
	}
	
	/**
	 * Establece el codigo postal domicilio.
	 *
	 * @param codigoPostalDomicilio el nuevo codigo postal domicilio
	 */
	public void setCodigoPostalDomicilio(String codigoPostalDomicilio) {
		this.codigoPostalDomicilio = codigoPostalDomicilio;
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
	public Long getProvinciaDomicilio() {
		return provinciaDomicilio;
	}
	
	/**
	 * Establece el provincia domicilio.
	 *
	 * @param provinciaDomicilio el nuevo provincia domicilio
	 */
	public void setProvinciaDomicilio(Long provinciaDomicilio) {
		this.provinciaDomicilio = provinciaDomicilio;
	}
	
	/**
	 * Obtiene el pais.
	 *
	 * @return el pais
	 */
	public Long getPais() {
		return pais;
	}
	
	/**
	 * Establece el pais.
	 *
	 * @param pais el nuevo pais
	 */
	public void setPais(Long pais) {
		this.pais = pais;
	}
	
	/**
	 * Obtiene el comprobar.
	 *
	 * @return el comprobar
	 */
	public String getComprobar() {
		return comprobar;
	}
	
	/**
	 * Establece el comprobar.
	 *
	 * @param comprobar el nuevo comprobar
	 */
	public void setComprobar(String comprobar) {
		this.comprobar = comprobar;
	}
	
	/**
	 * Obtiene el id cuerpo escala.
	 *
	 * @return el id cuerpo escala
	 */
	public String getIdCuerpoEscala() {
		return idCuerpoEscala;
	}
	
	/**
	 * Establece el id cuerpo escala.
	 *
	 * @param cuerpoEscala el nuevo id cuerpo escala
	 */
	public void setIdCuerpoEscala(String cuerpoEscala) {
		this.idCuerpoEscala = cuerpoEscala;
	}
	
	/**
	 * Obtiene el especialidad.
	 *
	 * @return el especialidad
	 */
	public Long getEspecialidad() {
		return especialidad;
	}
	
	/**
	 * Establece el especialidad.
	 *
	 * @param especialidad el nuevo especialidad
	 */
	public void setEspecialidad(Long especialidad) {
		this.especialidad = especialidad;
	}
	
	/**
	 * Obtiene el forma acceso.
	 *
	 * @return el forma acceso
	 */
	public String getFormaAcceso() {
		return formaAcceso;
	}
	
	/**
	 * Establece el forma acceso.
	 *
	 * @param formaAcceso el nuevo forma acceso
	 */
	public void setFormaAcceso(String formaAcceso) {
		this.formaAcceso = formaAcceso;
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
	 * Obtiene el fecha boe.
	 *
	 * @return el fecha boe
	 */
	public String getFechaBoe() {
		return FechaBoe;
	}
	
	/**
	 * Establece el fecha boe.
	 *
	 * @param fechaBoe el nuevo fecha boe
	 */
	public void setFechaBoe(String fechaBoe) {
		FechaBoe = fechaBoe;
	}
	
	/**
	 * Obtiene el dia fecha boe.
	 *
	 * @return el dia fecha boe
	 */
	public String getDiaFechaBoe() {
		return diaFechaBoe;
	}
	
	/**
	 * Establece el dia fecha boe.
	 *
	 * @param diaFechaBoe el nuevo dia fecha boe
	 */
	public void setDiaFechaBoe(String diaFechaBoe) {
		this.diaFechaBoe = diaFechaBoe;
	}
	
	/**
	 * Obtiene el mes fecha boe.
	 *
	 * @return el mes fecha boe
	 */
	public String getMesFechaBoe() {
		return mesFechaBoe;
	}
	
	/**
	 * Establece el mes fecha boe.
	 *
	 * @param mesFechaBoe el nuevo mes fecha boe
	 */
	public void setMesFechaBoe(String mesFechaBoe) {
		this.mesFechaBoe = mesFechaBoe;
	}
	
	/**
	 * Obtiene el anio fecha boe.
	 *
	 * @return el anio fecha boe
	 */
	public String getAnioFechaBoe() {
		return anioFechaBoe;
	}
	
	/**
	 * Establece el anio fecha boe.
	 *
	 * @param anioFechaBoe el nuevo anio fecha boe
	 */
	public void setAnioFechaBoe(String anioFechaBoe) {
		this.anioFechaBoe = anioFechaBoe;
	}
	
	/**
	 * Obtiene el provincia examen.
	 *
	 * @return el provincia examen
	 */
	public Long getProvinciaExamen() {
		return provinciaExamen;
	}
	
	/**
	 * Establece el provincia examen.
	 *
	 * @param provinciaExamen el nuevo provincia examen
	 */
	public void setProvinciaExamen(Long provinciaExamen) {
		this.provinciaExamen = provinciaExamen;
	}
	
	/**
	 * Obtiene el porcentaje discapacidad.
	 *
	 * @return el porcentaje discapacidad
	 */
	public Long getPorcentajeDiscapacidad() {
		return porcentajeDiscapacidad;
	}
	
	/**
	 * Establece el porcentaje discapacidad.
	 *
	 * @param porcentajeDiscapacidad el nuevo porcentaje discapacidad
	 */
	public void setPorcentajeDiscapacidad(Long porcentajeDiscapacidad) {
		this.porcentajeDiscapacidad = porcentajeDiscapacidad;
	}
	
	/**
	 * Obtiene el tipo discapacidad.
	 *
	 * @return el tipo discapacidad
	 */
	public Long getTipoDiscapacidad() {
		return tipoDiscapacidad;
	}
	
	/**
	 * Establece el tipo discapacidad.
	 *
	 * @param tipoDiscapacidad el nuevo tipo discapacidad
	 */
	public void setTipoDiscapacidad(Long tipoDiscapacidad) {
		this.tipoDiscapacidad = tipoDiscapacidad;
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
	 * Obtiene el titulo oficial.
	 *
	 * @return el titulo oficial
	 */
	public Long getTituloOficial() {
		return tituloOficial;
	}
	
	/**
	 * Establece el titulo oficial.
	 *
	 * @param tituloOficial el nuevo titulo oficial
	 */
	public void setTituloOficial(Long tituloOficial) {
		this.tituloOficial = tituloOficial;
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
	 * Obtiene el persona firmante.
	 *
	 * @return el persona firmante
	 */
	public String getPersonaFirmante() {
		return personaFirmante;
	}
	
	/**
	 * Establece el persona firmante.
	 *
	 * @param personaFirmante el nuevo persona firmante
	 */
	public void setPersonaFirmante(String personaFirmante) {
		this.personaFirmante = personaFirmante;
	}
	
	/**
	 * Obtiene el importe solicitud.
	 *
	 * @return el importe solicitud
	 */
	public String getImporteSolicitud() {
		return importeSolicitud;
	}
	
	/**
	 * Establece el importe solicitud.
	 *
	 * @param importeSolicitud el nuevo importe solicitud
	 */
	public void setImporteSolicitud(String importeSolicitud) {
		this.importeSolicitud = importeSolicitud;
	}
	
	/**
	 * Obtiene el importe solicitud decimal.
	 *
	 * @return el importe solicitud decimal
	 */
	public String getImporteSolicitudDecimal() {
		return importeSolicitudDecimal;
	}
	
	/**
	 * Establece el importe solicitud decimal.
	 *
	 * @param importeSolicitudDecimal el nuevo importe solicitud decimal
	 */
	public void setImporteSolicitudDecimal(String importeSolicitudDecimal) {
		this.importeSolicitudDecimal = importeSolicitudDecimal;
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
	 * Obtiene el entidad.
	 *
	 * @return el entidad
	 */
	public String getEntidad() {
		return entidad;
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
	 * Establece el entidad.
	 *
	 * @param entidad el nuevo entidad
	 */
	public void setEntidad(String entidad) {
		this.entidad = entidad;
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
	 * Obtiene el digito conttrol.
	 *
	 * @return el digito conttrol
	 */
	public String getDigitoConttrol() {
		return digitoConttrol;
	}
	
	/**
	 * Establece el digito conttrol.
	 *
	 * @param digitoConttrol el nuevo digito conttrol
	 */
	public void setDigitoConttrol(String digitoConttrol) {
		this.digitoConttrol = digitoConttrol;
	}
	
	/**
	 * Obtiene el numero cuenta.
	 *
	 * @return el numero cuenta
	 */
	public String getNumeroCuenta() {
		return numeroCuenta;
	}
	
	/**
	 * Establece el numero cuenta.
	 *
	 * @param numeroCuenta el nuevo numero cuenta
	 */
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
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
	 * Obtiene el lugar firma.
	 *
	 * @return el lugar firma
	 */
	public String getLugarFirma() {
		return lugarFirma;
	}
	
	/**
	 * Establece el lugar firma.
	 *
	 * @param lugarFirma el nuevo lugar firma
	 */
	public void setLugarFirma(String lugarFirma) {
		this.lugarFirma = lugarFirma;
	}
	
	/**
	 * Obtiene el fecha firma.
	 *
	 * @return el fecha firma
	 */
	public String getFechaFirma() {
		return fechaFirma;
	}
	
	/**
	 * Establece el fecha firma.
	 *
	 * @param fechaFirma el nuevo fecha firma
	 */
	public void setFechaFirma(String fechaFirma) {
		this.fechaFirma = fechaFirma;
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
	 * @param id el nuevo id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * Obtiene el codigo provincia domicilio.
	 *
	 * @return el codigo provincia domicilio
	 */
	public String getCodigoProvinciaDomicilio() {
		return codigoProvinciaDomicilio;
	}
	
	/**
	 * Establece el codigo provincia domicilio.
	 *
	 * @param codigoProvinciaDomicilio el nuevo codigo provincia domicilio
	 */
	public void setCodigoProvinciaDomicilio(String codigoProvinciaDomicilio) {
		this.codigoProvinciaDomicilio = codigoProvinciaDomicilio;
	}
	
	/**
	 * Obtiene el codigo pais domicilio.
	 *
	 * @return el codigo pais domicilio
	 */
	public String getCodigoPaisDomicilio() {
		return codigoPaisDomicilio;
	}
	
	/**
	 * Establece el codigo pais domicilio.
	 *
	 * @param codigoPaisDomicilio el nuevo codigo pais domicilio
	 */
	public void setCodigoPaisDomicilio(String codigoPaisDomicilio) {
		this.codigoPaisDomicilio = codigoPaisDomicilio;
	}
	
	/**
	 * Obtiene el codigo cuerpo escala.
	 *
	 * @return el codigo cuerpo escala
	 */
	public String getCodigoCuerpoEscala() {
		return codigoCuerpoEscala;
	}
	
	/**
	 * Establece el codigo cuerpo escala.
	 *
	 * @param codigoCuerpoEscala el nuevo codigo cuerpo escala
	 */
	public void setCodigoCuerpoEscala(String codigoCuerpoEscala) {
		this.codigoCuerpoEscala = codigoCuerpoEscala;
	}
	
	/**
	 * Obtiene el codigo especialidad.
	 *
	 * @return el codigo especialidad
	 */
	public String getCodigoEspecialidad() {
		return codigoEspecialidad;
	}
	
	/**
	 * Establece el codigo especialidad.
	 *
	 * @param codigoEspecialidad el nuevo codigo especialidad
	 */
	public void setCodigoEspecialidad(String codigoEspecialidad) {
		this.codigoEspecialidad = codigoEspecialidad;
	}
	
	/**
	 * Obtiene el codigo ministerio.
	 *
	 * @return el codigo ministerio
	 */
	public String getCodigoMinisterio() {
		return codigoMinisterio;
	}
	
	/**
	 * Establece el codigo ministerio.
	 *
	 * @param codigoMinisterio el nuevo codigo ministerio
	 */
	public void setCodigoMinisterio(String codigoMinisterio) {
		this.codigoMinisterio = codigoMinisterio;
	}
	
	/**
	 * Obtiene el codigo provincia examen.
	 *
	 * @return el codigo provincia examen
	 */
	public String getCodigoProvinciaExamen() {
		return codigoProvinciaExamen;
	}
	
	/**
	 * Establece el codigo provincia examen.
	 *
	 * @param codigoProvinciaExamen el nuevo codigo provincia examen
	 */
	public void setCodigoProvinciaExamen(String codigoProvinciaExamen) {
		this.codigoProvinciaExamen = codigoProvinciaExamen;
	}
	
	/**
	 * Obtiene el codigo titulo exigido.
	 *
	 * @return el codigo titulo exigido
	 */
	public String getCodigoTituloExigido() {
		return codigoTituloExigido;
	}
	
	/**
	 * Establece el codigo titulo exigido.
	 *
	 * @param codigoTituloExigido el nuevo codigo titulo exigido
	 */
	public void setCodigoTituloExigido(String codigoTituloExigido) {
		this.codigoTituloExigido = codigoTituloExigido;
	}
	
	/**
	 * Obtiene el codigo titulo otros.
	 *
	 * @return el codigo titulo otros
	 */
	public String getCodigoTituloOtros() {
		return codigoTituloOtros;
	}
	
	/**
	 * Establece el codigo titulo otros.
	 *
	 * @param codigoTituloOtros el nuevo codigo titulo otros
	 */
	public void setCodigoTituloOtros(String codigoTituloOtros) {
		this.codigoTituloOtros = codigoTituloOtros;
	}
	
	/**
	 * Obtiene el codigo municipio.
	 *
	 * @return el codigo municipio
	 */
	public String getCodigoMunicipio() {
		return codigoMunicipio;
	}
	
	/**
	 * Establece el codigo municipio.
	 *
	 * @param codigoMunicipio el nuevo codigo municipio
	 */
	public void setCodigoMunicipio(String codigoMunicipio) {
		this.codigoMunicipio = codigoMunicipio;
	}
	
	/**
	 * Obtiene el codigo nacionalidad.
	 *
	 * @return el codigo nacionalidad
	 */
	public String getCodigoNacionalidad() {
		return codigoNacionalidad;
	}
	
	/**
	 * Establece el codigo nacionalidad.
	 *
	 * @param codigoNacionalidad el nuevo codigo nacionalidad
	 */
	public void setCodigoNacionalidad(String codigoNacionalidad) {
		this.codigoNacionalidad = codigoNacionalidad;
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
	 * Obtiene el num modelo.
	 *
	 * @return el num modelo
	 */
	public String getNumModelo() {
		return numModelo;
	}
	
	/**
	 * Establece el num modelo.
	 *
	 * @param numModelo el nuevo num modelo
	 */
	public void setNumModelo(String numModelo) {
		this.numModelo = numModelo;
	}
	
	/**
	 * Obtiene el campo propio 0.
	 *
	 * @return el campo propio 0
	 */
	public String getCampoPropio0() {
		return campoPropio0;
	}
	
	/**
	 * Establece el campo propio 0.
	 *
	 * @param campoPropio0 el nuevo campo propio 0
	 */
	public void setCampoPropio0(String campoPropio0) {
		this.campoPropio0 = campoPropio0;
	}
	
	/**
	 * Obtiene el campo propio 1.
	 *
	 * @return el campo propio 1
	 */
	public String getCampoPropio1() {
		return campoPropio1;
	}
	
	/**
	 * Establece el campo propio 1.
	 *
	 * @param campoPropio1 el nuevo campo propio 1
	 */
	public void setCampoPropio1(String campoPropio1) {
		this.campoPropio1 = campoPropio1;
	}
	
	/**
	 * Obtiene el campo propio 2.
	 *
	 * @return el campo propio 2
	 */
	public String getCampoPropio2() {
		return campoPropio2;
	}
	
	/**
	 * Establece el campo propio 2.
	 *
	 * @param campoPropio2 el nuevo campo propio 2
	 */
	public void setCampoPropio2(String campoPropio2) {
		this.campoPropio2 = campoPropio2;
	}
	
	/**
	 * Obtiene el campo propio 3.
	 *
	 * @return el campo propio 3
	 */
	public String getCampoPropio3() {
		return campoPropio3;
	}
	
	/**
	 * Establece el campo propio 3.
	 *
	 * @param campoPropio3 el nuevo campo propio 3
	 */
	public void setCampoPropio3(String campoPropio3) {
		this.campoPropio3 = campoPropio3;
	}
	
	/**
	 * Comprueba si es secretario jud.
	 *
	 * @return verdadero, si es secretario jud
	 */
	public boolean isSecretarioJud() {
		return secretarioJud;
	}
	
	/**
	 * Establece el secretario jud.
	 *
	 * @param secretarioJud el nuevo secretario jud
	 */
	public void setSecretarioJud(boolean secretarioJud) {
		this.secretarioJud = secretarioJud;
	}
	
	/**
	 * Obtiene el codigo cuerpo escala aux.
	 *
	 * @return el codigo cuerpo escala aux
	 */
	public String getCodigoCuerpoEscalaAux() {
		return codigoCuerpoEscalaAux;
	}
	
	/**
	 * Establece el codigo cuerpo escala aux.
	 *
	 * @param codigoCuerpoEscalaAux el nuevo codigo cuerpo escala aux
	 */
	public void setCodigoCuerpoEscalaAux(String codigoCuerpoEscalaAux) {
		this.codigoCuerpoEscalaAux = codigoCuerpoEscalaAux;
	}
	
	/**
	 * Obtiene el campos propios.
	 *
	 * @return el campos propios
	 */
	public List<CamposPropiosBean> getCamposPropios() {
		return camposPropios;
	}
	
	/**
	 * Establece el campos propios.
	 *
	 * @param camposPropios el nuevo campos propios
	 */
	public void setCamposPropios(List<CamposPropiosBean> camposPropios) {
		this.camposPropios = camposPropios;
	}
	
	/**
	 * Obtiene el des ministerio convocante.
	 *
	 * @return el des ministerio convocante
	 */
	public String getDesMinisterioConvocante() {
		return desMinisterioConvocante;
	}
	
	/**
	 * Establece el des ministerio convocante.
	 *
	 * @param desMinisterioConvocante el nuevo des ministerio convocante
	 */
	public void setDesMinisterioConvocante(String desMinisterioConvocante) {
		this.desMinisterioConvocante = desMinisterioConvocante;
	}
	
	/**
	 * Obtiene el motivo red ex.
	 *
	 * @return el motivo red ex
	 */
	public String getMotivoRedEx() {
		return motivoRedEx;
	}
	
	/**
	 * Establece el motivo red ex.
	 *
	 * @param motivoRedEx el nuevo motivo red ex
	 */
	public void setMotivoRedEx(String motivoRedEx) {
		this.motivoRedEx = motivoRedEx;
	}

	/**
	 * Obtiene el numero titulo FN.
	 *
	 * @return el numero titulo FN
	 */
	public String getNumeroTituloFN() {
		return numeroTituloFN;
	}
	
	/**
	 * Establece el numero titulo FN.
	 *
	 * @param numeroTituloFN el nuevo numero titulo FN
	 */
	public void setNumeroTituloFN(String numeroTituloFN) {
		this.numeroTituloFN = numeroTituloFN;
	}
	
	/**
	 * Obtiene el comunidad defecto.
	 *
	 * @return el comunidad defecto
	 */
	public Long getComunidadDefecto() {
		return comunidadDefecto;
	}
	
	/**
	 * Establece el comunidad defecto.
	 *
	 * @param comunidadDefecto el nuevo comunidad defecto
	 */
	public void setComunidadDefecto(Long comunidadDefecto) {
		this.comunidadDefecto = comunidadDefecto;
	}
	
	/**
	 * Obtiene el comunidad FN.
	 *
	 * @return el comunidad FN
	 */
	public Long getComunidadFN() {
		return comunidadFN;
	}
	
	/**
	 * Establece el comunidad FN.
	 *
	 * @param comunidadFN el nuevo comunidad FN
	 */
	public void setComunidadFN(Long comunidadFN) {
		this.comunidadFN = comunidadFN;
	}
	
	/**
	 * Obtiene el comunidad DD.
	 *
	 * @return el comunidad DD
	 */
	public Long getComunidadDD() {
		return comunidadDD;
	}
	
	/**
	 * Establece el comunidad DD.
	 *
	 * @param comunidadDD el nuevo comunidad DD
	 */
	public void setComunidadDD(Long comunidadDD) {
		this.comunidadDD = comunidadDD;
	}
	
	/**
	 * Comprueba si es subsanar.
	 *
	 * @return verdadero, si es subsanar
	 */
	public boolean isSubsanar() {
		return subsanar;
	}
	
	/**
	 * Establece el subsanar.
	 *
	 * @param subsanar el nuevo subsanar
	 */
	public void setSubsanar(boolean subsanar) {
		this.subsanar = subsanar;
	}
	
	/**
	 * Obtiene el otros titulos list.
	 *
	 * @return el otros titulos list
	 */
	public List<OtrosTitulos> getOtrosTitulosList() {
		return otrosTitulosList;
	}
	
	/**
	 * Establece el otros titulos list.
	 *
	 * @param otrosTitulosList el nuevo otros titulos list
	 */
	public void setOtrosTitulosList(List<OtrosTitulos> otrosTitulosList) {
		this.otrosTitulosList = otrosTitulosList;
	}
	
	/**
	 * Obtiene el discapacidad list.
	 *
	 * @return el discapacidad list
	 */
	public List<Discapacidad> getDiscapacidadList() {
		return discapacidadList;
	}
	
	/**
	 * Establece el discapacidad list.
	 *
	 * @param discapacidadList el nuevo discapacidad list
	 */
	public void setDiscapacidadList(List<Discapacidad> discapacidadList) {
		this.discapacidadList = discapacidadList;
	}
	
	public String getMotivoOposicion() {
		return motivoOposicion;
	}

	public void setMotivoOposicion(String motivoOposicion) {
		this.motivoOposicion = motivoOposicion;
	}

	/**
	 * Obtiene el ocultar datos propios.
	 *
	 * @return el ocultar datos propios
	 */
	public Character getOcultarDatosPropios() {
		return ocultarDatosPropios;
	}

	/**
	 * Establece el ocultar datos propios.
	 *
	 * @param ocultarDatosPropios el nuevo ocultar datos propios
	 */
	public void setOcultarDatosPropios(Character ocultarDatosPropios) {
		this.ocultarDatosPropios = ocultarDatosPropios;
	}
	
	public boolean isAutorizar() {
		return autorizar;
	}

	public void setAutorizar(boolean autorizar) {
		this.autorizar = autorizar;
	}
	
	public String getEnlace() {
		return enlace;
	}

	public void setEnlace(String enlace) {
		this.enlace = enlace;
	}
}
	
