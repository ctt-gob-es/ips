package es.map.ipsc.bean;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import es.map.ips.model.Convocatoria;
import es.map.ips.model.Documento;
import es.map.ips.model.Especialidad;
import es.map.ips.model.EstadoSolicitud;
import es.map.ips.model.FuncionarioHabilitado;
import es.map.ips.model.Modelo;
import es.map.ips.model.Pais;
import es.map.ips.model.Provincia;
import es.map.ips.model.ProvinciaExamen;
import es.map.ips.model.TipoDiscapacidad;
import es.map.ips.model.TipoSolicitud;
import es.map.ips.model.TituloOficial;


/**
 * El Class SolicitudBean.
 */
public class SolicitudBean {
	
	/** el id. */
	private Long id;
	
	/** el nombre. */
	private String nombre;
	
	/** el primer apellido. */
	private String primerApellido;
	
	/** el segundo apellido. */
	private String segundoApellido;
	
	/** el titulo oficial. */
	private TituloOficial tituloOficial;
	
	/** el titulo oficial id. */
	private String tituloOficialId;
	
	/** el especialidad. */
	private Especialidad especialidad;
	
	/** el especialidades id. */
	private String especialidadesId;
	
	/** el provincia nacimiento. */
	private Provincia provinciaNacimiento;
	
	/** el tipo solicitud. */
	private TipoSolicitud tipoSolicitud;
	
	/** el pais domicilio. */
	private Pais paisDomicilio;
	
	/** el tipo discapacidad. */
	private TipoDiscapacidad tipoDiscapacidad;
	
	/** el tipo discapacidad id. */
	private String tipoDiscapacidadId;
	
	/** el tipo discapacidad des. */
	private String tipoDiscapacidadDes;
	
	/** el provincia domicilio. */
	private Provincia provinciaDomicilio;
	
	/** el estado solicitud. */
	private EstadoSolicitud estadoSolicitud;
	
	/** el des estado solicitud. */
	private String desEstadoSolicitud;
	
	/** el id estado solicitud. */
	private String idEstadoSolicitud;
	
	/** el provincia examen. */
	private ProvinciaExamen provinciaExamen;
	
	/** el convocatoria. */
	private Convocatoria convocatoria;
	
	/** el numero solicitud. */
	private String numeroSolicitud;
	
	/** el fecha nacimiento. */
	private Date fechaNacimiento;
	
	/** el nif. */
	private String nif;
	
	/** el sexo. */
	private char sexo;
	
	/** el localidad nacimiento. */
	private String localidadNacimiento;
	
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
	
	/** el calle domicilio. */
	private String calleDomicilio;
	
	/** el codigo postal domicilio. */
	private String codigoPostalDomicilio;
	
	/** el municipio domicilio. */
	private String municipioDomicilio;
	
	/** el pais domicilio id. */
	private String paisDomicilioId;
	
	/** el pais domicilio des. */
	private String paisDomicilioDes;
	
	/** el provincia domicilio des. */
	private String provinciaDomicilioDes;
	
	/** el provincia domicilio ids. */
	private String provinciaDomicilioIds;
	
	/** el provincia examen ids. */
	private String provinciaExamenIds;
	
	/** el porcentaje discapacidad. */
	private Long porcentajeDiscapacidad;
	
	/** el reserva discapacidad. */
	private char reservaDiscapacidad;
	
	/** el detalle discapacidad. */
	private String detalleDiscapacidad;
	
	/** el otros titulos. */
	private String otrosTitulos;
	
	/** el datos A. */
	private String datosA;
	
	/** el datos B. */
	private String datosB;
	
	/** el datos C. */
	private String datosC;
	
	/** el fecha utl actualizacion. */
	private Date fechaUtlActualizacion;
	
	/** el fecha nacimiento verificada. */
	private char fechaNacimientoVerificada;
	
	/** el titulo verificado. */
	private char tituloVerificado;
	
	/** el edad verificada. */
	private char edadVerificada;
	
	/** el fecha solicitud. */
	private Date fechaSolicitud;
	
	/** el comentarios. */
	private String comentarios;
	
	/** el correo electronicos. */
	private Set correoElectronicos = new HashSet(0);
	
	/** el documentos. */
	private Set<Documento> documentos = new HashSet<Documento>(0);
	
	/** el registro solicituds. */
	private Set registroSolicituds = new HashSet(0);
	
	/** el log solicituds. */
	private Set logSolicituds = new HashSet(0);
	
	/** el pago solicituds. */
	private Set pagoSolicituds = new HashSet(0);
	
	/** el id convocatoria. */
	private Long idConvocatoria;
	
	/** el des provincia examen. */
	private String desProvinciaExamen;
	
	/** el des titulo oficial. */
	private String desTituloOficial;
	
	/** el des especialidad. */
	private String desEspecialidad;
	
	/** el id consentimiento. */
	private Boolean idConsentimiento;
	
	/** motivo oposicion */
	private String motivoOposicion;
	
	/** el fun habilitado. */
	private Boolean funHabilitado;
	
	/** el funcionario habilitado. */
	private FuncionarioHabilitado funcionarioHabilitado;
	
	/** el id consentimiento. */
	private Boolean idConsentimientoAEAT;
	

	/** el plantilla propios. */
	private List<PlantillaPropiosBean> plantillaPropios;
	
	/** el modelo. */
	private Modelo modelo;
	
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
	 * Obtiene el tipo discapacidad des.
	 *
	 * @return el tipo discapacidad des
	 */
	public String getTipoDiscapacidadDes() {
		return tipoDiscapacidadDes;
	}

	/**
	 * Establece el tipo discapacidad des.
	 *
	 * @param tipoDiscapacidadDes el nuevo tipo discapacidad des
	 */
	public void setTipoDiscapacidadDes(String tipoDiscapacidadDes) {
		this.tipoDiscapacidadDes = tipoDiscapacidadDes;
	}

	/**
	 * Obtiene el pais domicilio des.
	 *
	 * @return el pais domicilio des
	 */
	public String getPaisDomicilioDes() {
		return paisDomicilioDes;
	}

	/**
	 * Establece el pais domicilio des.
	 *
	 * @param paisDomicilioDes el nuevo pais domicilio des
	 */
	public void setPaisDomicilioDes(String paisDomicilioDes) {
		this.paisDomicilioDes = paisDomicilioDes;
	}

	/**
	 * Obtiene el provincia domicilio des.
	 *
	 * @return el provincia domicilio des
	 */
	public String getProvinciaDomicilioDes() {
		return provinciaDomicilioDes;
	}

	/**
	 * Establece el provincia domicilio des.
	 *
	 * @param provinciaDomicilioDes el nuevo provincia domicilio des
	 */
	public void setProvinciaDomicilioDes(String provinciaDomicilioDes) {
		this.provinciaDomicilioDes = provinciaDomicilioDes;
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
	 * Obtiene el titulo oficial id.
	 *
	 * @return el titulo oficial id
	 */
	public String getTituloOficialId() {
		return tituloOficialId;
	}

	/**
	 * Establece el titulo oficial id.
	 *
	 * @param tituloOficialId el nuevo titulo oficial id
	 */
	public void setTituloOficialId(String tituloOficialId) {
		this.tituloOficialId = tituloOficialId;
	}

	/**
	 * Obtiene el tipo discapacidad id.
	 *
	 * @return el tipo discapacidad id
	 */
	public String getTipoDiscapacidadId() {
		return tipoDiscapacidadId;
	}

	/**
	 * Establece el tipo discapacidad id.
	 *
	 * @param tipoDiscapacidadId el nuevo tipo discapacidad id
	 */
	public void setTipoDiscapacidadId(String tipoDiscapacidadId) {
		this.tipoDiscapacidadId = tipoDiscapacidadId;
	}

	/**
	 * Obtiene el especialidades id.
	 *
	 * @return el especialidades id
	 */
	public String getEspecialidadesId() {
		return especialidadesId;
	}

	/**
	 * Establece el especialidades id.
	 *
	 * @param especialidadesId el nuevo especialidades id
	 */
	public void setEspecialidadesId(String especialidadesId) {
		this.especialidadesId = especialidadesId;
	}

	/**
	 * Obtiene el id estado solicitud.
	 *
	 * @return el id estado solicitud
	 */
	public String getIdEstadoSolicitud() {
		return idEstadoSolicitud;
	}

	/**
	 * Establece el id estado solicitud.
	 *
	 * @param idEstadoSolicitud el nuevo id estado solicitud
	 */
	public void setIdEstadoSolicitud(String idEstadoSolicitud) {
		this.idEstadoSolicitud = idEstadoSolicitud;
	}

	/**
	 * Obtiene el provincia examen ids.
	 *
	 * @return el provincia examen ids
	 */
	public String getProvinciaExamenIds() {
		return provinciaExamenIds;
	}

	/**
	 * Establece el provincia examen ids.
	 *
	 * @param provinciaExamenIds el nuevo provincia examen ids
	 */
	public void setProvinciaExamenIds(String provinciaExamenIds) {
		this.provinciaExamenIds = provinciaExamenIds;
	}

	/**
	 * Obtiene el pais domicilio id.
	 *
	 * @return el pais domicilio id
	 */
	public String getPaisDomicilioId() {
		return paisDomicilioId;
	}

	/**
	 * Establece el pais domicilio id.
	 *
	 * @param paisDomicilioId el nuevo pais domicilio id
	 */
	public void setPaisDomicilioId(String paisDomicilioId) {
		this.paisDomicilioId = paisDomicilioId;
	}

	/**
	 * Obtiene el provincia domicilio ids.
	 *
	 * @return el provincia domicilio ids
	 */
	public String getProvinciaDomicilioIds() {
		return provinciaDomicilioIds;
	}

	/**
	 * Establece el provincia domicilio ids.
	 *
	 * @param provinciaDomicilioIds el nuevo provincia domicilio ids
	 */
	public void setProvinciaDomicilioIds(String provinciaDomicilioIds) {
		this.provinciaDomicilioIds = provinciaDomicilioIds;
	}

	/**
	 * Obtiene el des estado solicitud.
	 *
	 * @return el des estado solicitud
	 */
	public String getDesEstadoSolicitud() {
		return desEstadoSolicitud;
	}

	/**
	 * Establece el des estado solicitud.
	 *
	 * @param desEstadoSolicitud el nuevo des estado solicitud
	 */
	public void setDesEstadoSolicitud(String desEstadoSolicitud) {
		this.desEstadoSolicitud = desEstadoSolicitud;
	}

	/**
	 * Obtiene el id.
	 *
	 * @return el id
	 */
	public Long getId() {
		return this.id;
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
	 * Obtiene el titulo oficial.
	 *
	 * @return el titulo oficial
	 */
	public TituloOficial getTituloOficial() {
		return this.tituloOficial;
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
	 * Obtiene el especialidad.
	 *
	 * @return el especialidad
	 */
	public Especialidad getEspecialidad() {
		return this.especialidad;
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
	 * Obtiene el provincia nacimiento.
	 *
	 * @return el provincia nacimiento
	 */
	public Provincia getProvinciaNacimiento() {
		return this.provinciaNacimiento;
	}

	/**
	 * Establece el provincia nacimiento.
	 *
	 * @param provinciaNacimiento el nuevo provincia nacimiento
	 */
	public void setProvinciaNacimiento(Provincia provinciaNacimiento) {
		this.provinciaNacimiento = provinciaNacimiento;
	}

	/**
	 * Obtiene el tipo solicitud.
	 *
	 * @return el tipo solicitud
	 */
	public TipoSolicitud getTipoSolicitud() {
		return this.tipoSolicitud;
	}

	/**
	 * Establece el tipo solicitud.
	 *
	 * @param tipoSolicitud el nuevo tipo solicitud
	 */
	public void setTipoSolicitud(TipoSolicitud tipoSolicitud) {
		this.tipoSolicitud = tipoSolicitud;
	}

	/**
	 * Obtiene el pais domicilio.
	 *
	 * @return el pais domicilio
	 */
	public Pais getPaisDomicilio() {
		return this.paisDomicilio;
	}

	/**
	 * Establece el pais domicilio.
	 *
	 * @param paisDomicilio el nuevo pais domicilio
	 */
	public void setPaisDomicilio(Pais paisDomicilio) {
		this.paisDomicilio = paisDomicilio;
	}

	/**
	 * Obtiene el tipo discapacidad.
	 *
	 * @return el tipo discapacidad
	 */
	public TipoDiscapacidad getTipoDiscapacidad() {
		return this.tipoDiscapacidad;
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
	 * Obtiene el provincia domicilio.
	 *
	 * @return el provincia domicilio
	 */
	public Provincia getProvinciaDomicilio() {
		return this.provinciaDomicilio;
	}

	/**
	 * Establece el provincia domicilio.
	 *
	 * @param provinciaDomicilio el nuevo provincia domicilio
	 */
	public void setProvinciaDomicilio(Provincia provinciaDomicilio) {
		this.provinciaDomicilio = provinciaDomicilio;
	}

	/**
	 * Obtiene el estado solicitud.
	 *
	 * @return el estado solicitud
	 */
	public EstadoSolicitud getEstadoSolicitud() {
		return this.estadoSolicitud;
	}

	/**
	 * Establece el estado solicitud.
	 *
	 * @param estadoSolicitud el nuevo estado solicitud
	 */
	public void setEstadoSolicitud(EstadoSolicitud estadoSolicitud) {
		this.estadoSolicitud = estadoSolicitud;
	}

	/**
	 * Obtiene el provincia examen.
	 *
	 * @return el provincia examen
	 */
	public ProvinciaExamen getProvinciaExamen() {
		return this.provinciaExamen;
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
	 * Obtiene el convocatoria.
	 *
	 * @return el convocatoria
	 */
	public Convocatoria getConvocatoria() {
		return this.convocatoria;
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
		return this.numeroSolicitud;
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
	 * Obtiene el fecha nacimiento.
	 *
	 * @return el fecha nacimiento
	 */
	public Date getFechaNacimiento() {
		return this.fechaNacimiento;
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
	 * Obtiene el nif.
	 *
	 * @return el nif
	 */
	public String getNif() {
		return this.nif;
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
	 * Obtiene el sexo.
	 *
	 * @return el sexo
	 */
	public char getSexo() {
		return this.sexo;
	}

	/**
	 * Establece el sexo.
	 *
	 * @param sexo el nuevo sexo
	 */
	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	/**
	 * Obtiene el localidad nacimiento.
	 *
	 * @return el localidad nacimiento
	 */
	public String getLocalidadNacimiento() {
		return this.localidadNacimiento;
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
	 * Obtiene el nacionalidad.
	 *
	 * @return el nacionalidad
	 */
	public String getNacionalidad() {
		return this.nacionalidad;
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
		return this.telefono;
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
		return this.email;
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
	 * Obtiene el calle domicilio.
	 *
	 * @return el calle domicilio
	 */
	public String getCalleDomicilio() {
		return this.calleDomicilio;
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
		return this.codigoPostalDomicilio;
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
		return this.municipioDomicilio;
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
	public Long getPorcentajeDiscapacidad() {
		return this.porcentajeDiscapacidad;
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
	 * Obtiene el reserva discapacidad.
	 *
	 * @return el reserva discapacidad
	 */
	public char getReservaDiscapacidad() {
		return this.reservaDiscapacidad;
	}

	/**
	 * Establece el reserva discapacidad.
	 *
	 * @param reservaDiscapacidad el nuevo reserva discapacidad
	 */
	public void setReservaDiscapacidad(char reservaDiscapacidad) {
		this.reservaDiscapacidad = reservaDiscapacidad;
	}

	/**
	 * Obtiene el detalle discapacidad.
	 *
	 * @return el detalle discapacidad
	 */
	public String getDetalleDiscapacidad() {
		return this.detalleDiscapacidad;
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
		return this.otrosTitulos;
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
	 * Obtiene el datos A.
	 *
	 * @return el datos A
	 */
	public String getDatosA() {
		return this.datosA;
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
		return this.datosB;
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
		return this.datosC;
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
	 * Obtiene el fecha utl actualizacion.
	 *
	 * @return el fecha utl actualizacion
	 */
	public Date getFechaUtlActualizacion() {
		return this.fechaUtlActualizacion;
	}

	/**
	 * Establece el fecha utl actualizacion.
	 *
	 * @param fechaUtlActualizacion el nuevo fecha utl actualizacion
	 */
	public void setFechaUtlActualizacion(Date fechaUtlActualizacion) {
		this.fechaUtlActualizacion = fechaUtlActualizacion;
	}

	/**
	 * Obtiene el fecha nacimiento verificada.
	 *
	 * @return el fecha nacimiento verificada
	 */
	public char getFechaNacimientoVerificada() {
		return this.fechaNacimientoVerificada;
	}

	/**
	 * Establece el fecha nacimiento verificada.
	 *
	 * @param fechaNacimientoVerificada el nuevo fecha nacimiento verificada
	 */
	public void setFechaNacimientoVerificada(char fechaNacimientoVerificada) {
		this.fechaNacimientoVerificada = fechaNacimientoVerificada;
	}

	/**
	 * Obtiene el titulo verificado.
	 *
	 * @return el titulo verificado
	 */
	public char getTituloVerificado() {
		return this.tituloVerificado;
	}

	/**
	 * Establece el titulo verificado.
	 *
	 * @param tituloVerificado el nuevo titulo verificado
	 */
	public void setTituloVerificado(char tituloVerificado) {
		this.tituloVerificado = tituloVerificado;
	}

	/**
	 * Obtiene el edad verificada.
	 *
	 * @return el edad verificada
	 */
	public char getEdadVerificada() {
		return this.edadVerificada;
	}

	/**
	 * Establece el edad verificada.
	 *
	 * @param edadVerificada el nuevo edad verificada
	 */
	public void setEdadVerificada(char edadVerificada) {
		this.edadVerificada = edadVerificada;
	}

	/**
	 * Obtiene el fecha solicitud.
	 *
	 * @return el fecha solicitud
	 */
	public Date getFechaSolicitud() {
		return this.fechaSolicitud;
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
	 * Obtiene el comentarios.
	 *
	 * @return el comentarios
	 */
	public String getComentarios() {
		return this.comentarios;
	}

	/**
	 * Establece el comentarios.
	 *
	 * @param comentarios el nuevo comentarios
	 */
	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	/**
	 * Obtiene el correo electronicos.
	 *
	 * @return el correo electronicos
	 */
	public Set getCorreoElectronicos() {
		return this.correoElectronicos;
	}

	/**
	 * Establece el correo electronicos.
	 *
	 * @param correoElectronicos el nuevo correo electronicos
	 */
	public void setCorreoElectronicos(Set correoElectronicos) {
		this.correoElectronicos = correoElectronicos;
	}

	/**
	 * Obtiene el documentos.
	 *
	 * @return el documentos
	 */
	public Set<Documento> getDocumentos() {
		return this.documentos;
	}

	/**
	 * Establece el documentos.
	 *
	 * @param documentos el nuevo documentos
	 */
	public void setDocumentos(Set<Documento> documentos) {
		this.documentos = documentos;
	}

	/**
	 * Obtiene el registro solicituds.
	 *
	 * @return el registro solicituds
	 */
	public Set getRegistroSolicituds() {
		return this.registroSolicituds;
	}

	/**
	 * Establece el registro solicituds.
	 *
	 * @param registroSolicituds el nuevo registro solicituds
	 */
	public void setRegistroSolicituds(Set registroSolicituds) {
		this.registroSolicituds = registroSolicituds;
	}

	/**
	 * Obtiene el log solicituds.
	 *
	 * @return el log solicituds
	 */
	public Set getLogSolicituds() {
		return this.logSolicituds;
	}

	/**
	 * Establece el log solicituds.
	 *
	 * @param logSolicituds el nuevo log solicituds
	 */
	public void setLogSolicituds(Set logSolicituds) {
		this.logSolicituds = logSolicituds;
	}

	/**
	 * Obtiene el pago solicituds.
	 *
	 * @return el pago solicituds
	 */
	public Set getPagoSolicituds() {
		return this.pagoSolicituds;
	}

	/**
	 * Establece el pago solicituds.
	 *
	 * @param pagoSolicituds el nuevo pago solicituds
	 */
	public void setPagoSolicituds(Set pagoSolicituds) {
		this.pagoSolicituds = pagoSolicituds;
	}
	
	/**
	 * Obtiene el nombre.
	 *
	 * @return el nombre
	 */
	public String getNombre() {
		return this.nombre;
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
		return this.primerApellido;
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
		return this.segundoApellido;
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
	 * Obtiene el plantilla propios.
	 *
	 * @return el plantilla propios
	 */
	public List<PlantillaPropiosBean> getPlantillaPropios() {
		return plantillaPropios;
	}

	/**
	 * Establece el plantilla propios.
	 *
	 * @param plantillaPropios el nuevo plantilla propios
	 */
	public void setPlantillaPropios(List<PlantillaPropiosBean> plantillaPropios) {
		this.plantillaPropios = plantillaPropios;
	}

	/**
	 * Obtiene el modelo.
	 *
	 * @return el modelo
	 */
	public Modelo getModelo() {
		return modelo;
	}

	/**
	 * Establece el modelo.
	 *
	 * @param modelo el nuevo modelo
	 */
	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
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

	/**
	 * Obtiene el fun habilitado.
	 *
	 * @return el fun habilitado
	 */
	public Boolean getFunHabilitado() {
		return funHabilitado;
	}

	/**
	 * Establece el fun habilitado.
	 *
	 * @param funHabilitado el nuevo fun habilitado
	 */
	public void setFunHabilitado(Boolean funHabilitado) {
		this.funHabilitado = funHabilitado;
	}

	/**
	 * Obtiene el funcionario habilitado.
	 *
	 * @return el funcionario habilitado
	 */
	public FuncionarioHabilitado getFuncionarioHabilitado() {
		return funcionarioHabilitado;
	}

	/**
	 * Establece el funcionario habilitado.
	 *
	 * @param funcionarioHabilitado el nuevo funcionario habilitado
	 */
	public void setFuncionarioHabilitado(FuncionarioHabilitado funcionarioHabilitado) {
		this.funcionarioHabilitado = funcionarioHabilitado;
	}
	
	public String getMotivoOposicion() {
		return motivoOposicion;
	}

	public void setMotivoOposicion(String motivoOposicion) {
		this.motivoOposicion = motivoOposicion;
	}

	public Boolean getIdConsentimientoAEAT() {
		return idConsentimientoAEAT;
	}

	public void setIdConsentimientoAEAT(Boolean idConsentimientoAEAT) {
		this.idConsentimientoAEAT = idConsentimientoAEAT;
	}
	
	
}
