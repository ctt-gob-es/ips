package es.map.ipsg.bean;

import java.util.Date;

import es.map.ips.model.Convocatoria;
import es.map.ips.model.Especialidad;
import es.map.ips.model.MotivoReduccionTasa;
import es.map.ips.model.Pais;
import es.map.ips.model.Provincia;
import es.map.ips.model.ProvinciaExamen;
import es.map.ips.model.TipoDiscapacidad;
import es.map.ips.model.TituloOficial;

/**
 * El Class SolicitudComunAuxiliarBean.
 */
public class SolicitudComunAuxiliarBean {

	/** el id solicitud. */
	private Long idSolicitud;
    
    /** el provincia. */
    private Provincia provincia;
    
    /** el motivo reduccion tasa. */
    private MotivoReduccionTasa motivoReduccionTasa;
    
    /** el titulo oficial. */
    private TituloOficial tituloOficial;
    
    /** el tipo discapacidad. */
    private TipoDiscapacidad tipoDiscapacidad;
    
    /** el provincia examen. */
    private ProvinciaExamen provinciaExamen;
    
    /** el id tipo pago. */
    private String idTipoPago;
    
    /** el pais. */
    private Pais pais;
    
    /** el especialidad. */
    private Especialidad especialidad;
    
    /** el convocatoria. */
    private Convocatoria convocatoria;
    
    /** el numero solicitud. */
    private String numeroSolicitud;
    
    /** el nif. */
    private String nif;
    
    /** el primer apellido. */
    private String primerApellido;
    
    /** el segundo apellido. */
    private String segundoApellido;
    
    /** el nombre. */
    private String nombre;
    
    /** el fecha nacimiento. */
    private Date fechaNacimiento;
    
    /** el sexo. */
    private Character sexo;
    
    /** el nacionalidad. */
    private String nacionalidad;
    
    /** el email. */
    private String email;
    
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
    
    /** el porcentaje discapacidad. */
    private Short porcentajeDiscapacidad;
    
    /** el reserva discapacidad. */
    private Character reservaDiscapacidad;
    
    /** el detalle discapacidad. */
    private String detalleDiscapacidad;
    
    /** el otros titulos. */
    private String otrosTitulos;
    
    /** el importe. */
    private String importe;
    
    /** el fecha solicitud. */
    private Date fechaSolicitud;
    
    /** el id consentimiento. */
    private Boolean idConsentimiento;
        
    /** el id consentimiento. */
    private Boolean idConsentimientoAEAT;
    
    /** el importe. */
    private String motivoOposicion;
    
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
	 * Obtiene el provincia.
	 *
	 * @return el provincia
	 */
	public Provincia getProvincia() {
		return provincia;
	}
	
	/**
	 * Establece el provincia.
	 *
	 * @param provincia el nuevo provincia
	 */
	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}
	
	/**
	 * Obtiene el motivo reduccion tasa.
	 *
	 * @return el motivo reduccion tasa
	 */
	public MotivoReduccionTasa getMotivoReduccionTasa() {
		return motivoReduccionTasa;
	}
	
	/**
	 * Establece el motivo reduccion tasa.
	 *
	 * @param motivoReduccionTasa el nuevo motivo reduccion tasa
	 */
	public void setMotivoReduccionTasa(MotivoReduccionTasa motivoReduccionTasa) {
		this.motivoReduccionTasa = motivoReduccionTasa;
	}
	
	/**
	 * Obtiene el titulo oficial.
	 *
	 * @return el titulo oficial
	 */
	public TituloOficial getTituloOficial() {
		return tituloOficial;
	}
	
	/**
	 * Establece el titulo oficial.
	 *
	 * @param tituloOficial el nuevo titulo oficial
	 */
	public void setTituloOficial(TituloOficial tituloOficial) {
		this.tituloOficial = tituloOficial;
	}
	
	/**
	 * Obtiene el tipo discapacidad.
	 *
	 * @return el tipo discapacidad
	 */
	public TipoDiscapacidad getTipoDiscapacidad() {
		return tipoDiscapacidad;
	}
	
	/**
	 * Establece el tipo discapacidad.
	 *
	 * @param tipoDiscapacidad el nuevo tipo discapacidad
	 */
	public void setTipoDiscapacidad(TipoDiscapacidad tipoDiscapacidad) {
		this.tipoDiscapacidad = tipoDiscapacidad;
	}
	
	/**
	 * Obtiene el provincia examen.
	 *
	 * @return el provincia examen
	 */
	public ProvinciaExamen getProvinciaExamen() {
		return provinciaExamen;
	}
	
	/**
	 * Establece el provincia examen.
	 *
	 * @param provinciaExamen el nuevo provincia examen
	 */
	public void setProvinciaExamen(ProvinciaExamen provinciaExamen) {
		this.provinciaExamen = provinciaExamen;
	}

	/**
	 * Obtiene el id tipo pago.
	 *
	 * @return el id tipo pago
	 */
	public String getIdTipoPago() {
		return idTipoPago;
	}
	
	/**
	 * Establece el id tipo pago.
	 *
	 * @param idTipoPago el nuevo id tipo pago
	 */
	public void setIdTipoPago(String idTipoPago) {
		this.idTipoPago = idTipoPago;
	}
	
	/**
	 * Obtiene el pais.
	 *
	 * @return el pais
	 */
	public Pais getPais() {
		return pais;
	}
	
	/**
	 * Establece el pais.
	 *
	 * @param pais el nuevo pais
	 */
	public void setPais(Pais pais) {
		this.pais = pais;
	}
	
	/**
	 * Obtiene el especialidad.
	 *
	 * @return el especialidad
	 */
	public Especialidad getEspecialidad() {
		return especialidad;
	}
	
	/**
	 * Establece el especialidad.
	 *
	 * @param especialidad el nuevo especialidad
	 */
	public void setEspecialidad(Especialidad especialidad) {
		this.especialidad = especialidad;
	}
	
	/**
	 * Obtiene el convocatoria.
	 *
	 * @return el convocatoria
	 */
	public Convocatoria getConvocatoria() {
		return convocatoria;
	}
	
	/**
	 * Establece el convocatoria.
	 *
	 * @param convocatoria el nuevo convocatoria
	 */
	public void setConvocatoria(Convocatoria convocatoria) {
		this.convocatoria = convocatoria;
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
	 * Obtiene el fecha nacimiento.
	 *
	 * @return el fecha nacimiento
	 */
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	
	/**
	 * Establece el fecha nacimiento.
	 *
	 * @param fechaNacimiento el nuevo fecha nacimiento
	 */
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	
	/**
	 * Obtiene el sexo.
	 *
	 * @return el sexo
	 */
	public Character getSexo() {
		return sexo;
	}
	
	/**
	 * Establece el sexo.
	 *
	 * @param sexo el nuevo sexo
	 */
	public void setSexo(Character sexo) {
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
	 * Obtiene el porcentaje discapacidad.
	 *
	 * @return el porcentaje discapacidad
	 */
	public Short getPorcentajeDiscapacidad() {
		return porcentajeDiscapacidad;
	}
	
	/**
	 * Establece el porcentaje discapacidad.
	 *
	 * @param porcentajeDiscapacidad el nuevo porcentaje discapacidad
	 */
	public void setPorcentajeDiscapacidad(Short porcentajeDiscapacidad) {
		this.porcentajeDiscapacidad = porcentajeDiscapacidad;
	}
	
	/**
	 * Obtiene el reserva discapacidad.
	 *
	 * @return el reserva discapacidad
	 */
	public Character getReservaDiscapacidad() {
		return reservaDiscapacidad;
	}
	
	/**
	 * Establece el reserva discapacidad.
	 *
	 * @param reservaDiscapacidad el nuevo reserva discapacidad
	 */
	public void setReservaDiscapacidad(Character reservaDiscapacidad) {
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
	 * Obtiene el fecha solicitud.
	 *
	 * @return el fecha solicitud
	 */
	public Date getFechaSolicitud() {
		return fechaSolicitud;
	}
	
	/**
	 * Establece el fecha solicitud.
	 *
	 * @param fechaSolicitud el nuevo fecha solicitud
	 */
	public void setFechaSolicitud(Date fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}
	
	/**
	 * Obtiene el id consentimiento.
	 *
	 * @return el id consentimiento
	 */
	public Boolean getIdConsentimiento() {
		return idConsentimiento;
	}
	
	/**
	 * Establece el id consentimiento.
	 *
	 * @param idConsentimiento el nuevo id consentimiento
	 */
	public void setIdConsentimiento(Boolean idConsentimiento) {
		this.idConsentimiento = idConsentimiento;
	}

	/**
	 * @return the idConsentimientoAEAT
	 */
	public Boolean getIdConsentimientoAEAT() {
		return idConsentimientoAEAT;
	}

	/**
	 * @param idConsentimientoAEAT the idConsentimientoAEAT to set
	 */
	public void setIdConsentimientoAEAT(Boolean idConsentimientoAEAT) {
		this.idConsentimientoAEAT = idConsentimientoAEAT;
	}

	/**
	 * @return the motivoOposicion
	 */
	public String getMotivoOposicion() {
		return motivoOposicion;
	}

	/**
	 * @param motivoOposicion the motivoOposicion to set
	 */
	public void setMotivoOposicion(String motivoOposicion) {
		this.motivoOposicion = motivoOposicion;
	}

}
