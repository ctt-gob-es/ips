package es.map.ipsg.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import es.map.ips.model.Convocatoria;
import es.map.ips.model.CorreoElectronico;
import es.map.ips.model.Documento;
import es.map.ips.model.Especialidad;
import es.map.ips.model.EstadoSolicitud;
import es.map.ips.model.LogSolicitud;
import es.map.ips.model.Pais;
import es.map.ips.model.Provincia;
import es.map.ips.model.ProvinciaExamen;
import es.map.ips.model.TipoDiscapacidad;
import es.map.ips.model.TipoSolicitud;
import es.map.ips.model.TituloOficial;

/**
 * SolicitudBean.
 *
 * @author amartinl
 */
public class SolicitudBean {
	
	/** el id. */
	private Long id;
	
	/** el provincia by id provincia nacimiento. */
	private Provincia provinciaByIdProvinciaNacimiento;
	
	/** el provincia by id provincia examen. */
	private ProvinciaExamen provinciaByIdProvinciaExamen;
	
	/** el provincia by id provincia domicilio. */
	private Provincia provinciaByIdProvinciaDomicilio;
	
	/** el tipo. */
	private TipoSolicitud tipo = new TipoSolicitud();
	
	/** el pais. */
	private Pais pais;
	
	/** el numero solicitud. */
	private String numeroSolicitud = "";
	
	/** el nif. */
	private String nif = "";
	
	/** el nombre. */
	private String nombre = "";
	
	/** el primer apellido. */
	private String primerApellido = "";
	
	/** el segundo apellido. */
	private String segundoApellido = "";
	
	/** el tipo descripcion. */
	private String tipoDescripcion = "";
	
	/** el motivo reduccion. */
	private Integer motivoReduccion;
	
	/** el fecha nacimiento. */
	private Date fechaNacimiento;
	
	/** el sexo. */
	private Character  sexo;
	
	/** el titulo oficial. */
	private TituloOficial tituloOficial;
	
	/** el estado solicitud. */
	private EstadoSolicitud estadoSolicitud = new EstadoSolicitud(); 
	
	/** el edad verificada. */
	private Character edadVerificada;
	
	/** el fecha nacimiento verificada. */
	private Character fechaNacimientoVerificada;
	
	/** el titulo verificado. */
	private Character tituloVerificado;
	
	/** el es desempleo. */
	private boolean esDesempleo;
	
	/** el es discapacidad. */
	private boolean esDiscapacidad;
	
	/** el es F numerosa. */
	private boolean esFNumerosa;
	
	/** el es victima terrorismo. */
	private boolean esVictimaTerrorismo;

	/** el fam numerosa. */
	private String famNumerosa;
	
	/** el Desempleo verificado. */
	private Character DesempleoVerificado;
	
	/** el Fnumerosa verificado. */
	private Character FnumerosaVerificado;
	
	/** el Discapacidad verificado. */
	private Character DiscapacidadVerificado;
	
	/** el Victimas verificado. */
	private Character victimasVerificado;
	
	/** el fecha solicitud. */
	private String fechaSolicitud;
	
	/** el tipo discapacidad. */
	private TipoDiscapacidad tipoDiscapacidad;
	
	/** el especialidad. */
	private Especialidad especialidad;
	
	/** el localidad nacimiento. */
	private String localidadNacimiento;
	
	/** el nacionalidad. */
	private String nacionalidad;
	
	/** el telefono. */
	private String telefono;
	
	/** el email. */
	private String email;
	
	/** el calle domicilio. */
	private String calleDomicilio;
	
	/** el codigo postal domicilio. */
	private String codigoPostalDomicilio;
	
	/** el municipio domicilio. */
	private String municipioDomicilio;
	
	/** el porcentaje discapacidad. */
	private Integer porcentajeDiscapacidad;
	
	/** el reserva discapacidad. */
	private Character  reservaDiscapacidad;
	
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
	
	/** el prueba derecho. */
	private String pruebaDerecho;
	
	/** el prueba idioma. */
	private String pruebaIdioma;
	
	/** el fecha utl actualizacion. */
	private Date fechaUtlActualizacion;
	
	/** el comentarios. */
	private String comentarios;
    
    /** el id consentimiento. */
    private Boolean idConsentimiento;
    
    /** el fecha nacimiento svdi. */
    private Date fechaNacimientoSvdi;
    
    /** el id tipo acceso. */
    private Integer idTipoAcceso;
	
	/** el registro solicitudes. */
	private ArrayList<RegistroSolicitudBean> registroSolicitudes = new ArrayList<RegistroSolicitudBean>();
	
	/** el documentos. */
	private Set<Documento> documentos = new HashSet<Documento>(0);
	
	/** el correo electronicos. */
	private Set<CorreoElectronico> correoElectronicos = new HashSet<CorreoElectronico>(0);
	
	/** el pago solicitudes. */
	private ArrayList<PagoSolicitudBean> pagoSolicitudes = new ArrayList<PagoSolicitudBean>();
	
	/** el log solicitudes. */
	private Set<LogSolicitud> logSolicitudes = new HashSet<LogSolicitud>(0);
	
	/** el listado correos electronico. */
	private ArrayList<CorreoElectronicoBean> listadoCorreosElectronico = new ArrayList<CorreoElectronicoBean>();
	
	/** el id modelo. */
	private String idModelo;
	
	/** el num modelo. */
	private String numModelo;

	/** el ejercicio. */
	//Para el resultado de Solicitudes Registradas
	private String ejercicio = ""; //Ejercicio de la Convocatoria
	
	/** el id convocatoria. */
	private Long idConvocatoria;
	
	/** el fecha boe. */
	private String fechaBoe;
	
	/** el convocatoria. */
	private Convocatoria convocatoria = new Convocatoria();
	
	/** el centro gestor. */
	private String centroGestor = ""; 
	
	/** el siglas centro gestor. */
	private String siglasCentroGestor;
	
	/** el des cuerpo escala. */
	private String codigoCuerpoEscala;
	
	/** el des cuerpo escala. */
	private String desCuerpoEscala;
	
	/** el sol exencion. */
	private boolean solExencion = false; 
	
	/** el num total. */
	private Integer numTotal;	
	
	/** el importe. */
	private Float importe;
	
	/** el justificante pago. */
	private String justificantePago;
	
	/** el fecha pago. */
	private String fechaPago;
	
	/** el id estado solicitud. */
	private Integer idEstadoSolicitud;
	
	/** el desc estado solicitud. */
	private String descEstadoSolicitud;
	
	/** el id tipo. */
	private Integer idTipo;
	
	
	/** el descripcion especialidad. */
	//Para el resultado de Exportar Excel
	private String descripcionEspecialidad;
	
	/** el descripcion id provincia nacimiento. */
	private String descripcionIdProvinciaNacimiento;
	
	/** el descripcion id provincia domicilio. */
	private String descripcionIdProvinciaDomicilio;
	
	/** el descripcion id provincia examen. */
	private String descripcionIdProvinciaExamen;
	
	/** el descripcion tipo discapacidad. */
	private String descripcionTipoDiscapacidad;
	
	/** el descripcion titulo oficial. */
	private String descripcionTituloOficial;
	
	/** el descripcion tipo pago. */
	private String descripcionTipoPago;
	
	/** el nrc. */
	private String nrc;
	
	/** el fecha registro. */
	private String fechaRegistro;
	
	/** el numero registro. */
	private String numeroRegistro;
	
	/** el nacion pais domicilio. */
	private String nacionPaisDomicilio;
	
	/** el admitido. */
	//FUN 3.5 - INI
	private String admitido;
	
	/** el estado PID. */
	private String estadoPID;
	//FUN 3.5 - FIN
	
	/** el des provincia domicilio. */
	//Para el registro en el rec
	private String desProvinciaDomicilio;
	
	/** el fun habilitado. */
	//Para actualizar estado
	private Boolean funHabilitado;
	
	/** el id motivo exencion. */
	private Integer idMotivoExencion;
	
	/** el motivo exencion desc. */
	private String motivoExencionDesc;
	
	/** el comunidad desc. */
	private String comunidadDesc;
	
	/** el titulo FN. */
	private String tituloFN;
	
    /** el id consentimiento. */
    private Boolean idConsentimientoAeat;
    
    /** el importe. */
    private String motivoOposicion;
	
	/**
	 * Obtiene el id modelo.
	 *
	 * @return el id modelo
	 */
	public String getIdModelo() {
		return idModelo;
	}
	
	/**
	 * Establece el id modelo.
	 *
	 * @param idModelo el nuevo id modelo
	 */
	public void setIdModelo(String idModelo) {
		this.idModelo = idModelo;
	}
	
	/**
	 * Obtiene el des provincia domicilio.
	 *
	 * @return desProvinciaDomicilio String
	 */
	public String getDesProvinciaDomicilio() {
		return desProvinciaDomicilio;
	}
	
	/**
	 * Establece el des provincia domicilio.
	 *
	 * @param desProvinciaDomicilio el nuevo des provincia domicilio
	 */
	public void setDesProvinciaDomicilio(String desProvinciaDomicilio) {
		this.desProvinciaDomicilio = desProvinciaDomicilio;
	}
	
	/**
	 * Obtiene el id.
	 *
	 * @return id Integer
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * Establece el id.
	 *
	 * @param id Integer id
	 */
	public void setId(Long id) {
		this.id = id;
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
	 * Obtiene el num total.
	 *
	 * @return numTotal Integer
	 */ 
	public Integer getNumTotal() {
		return numTotal;
	}
	
	/**
	 * Establece el num total.
	 *
	 * @param numTotal Integer
	 */
	public void setNumTotal(Integer numTotal) {
		this.numTotal = numTotal;
	}

/**
 * Obtiene el fecha pago.
 *
 * @return the fechaPago
 */
	public String getFechaPago() {
		return fechaPago;
	}
	
	/**
	 * Establece el fecha pago.
	 *
	 * @param fechaPago the fechaPago to set
	 */
	public void setFechaPago(String fechaPago) {
		this.fechaPago = fechaPago;
	}
	
	/**
	 * Obtiene el nif.
	 *
	 * @return nif String
	 */
	public String getNif() {
		return nif;
	}
	
	/**
	 * Establece el nif.
	 *
	 * @param nif String
	 */
	public void setNif(String nif) {
		this.nif = nif;
	}
	
	/**
	 * Obtiene el nombre.
	 *
	 * @return nombre String
	 */ 
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * Establece el nombre.
	 *
	 * @param nombre String
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * Obtiene el primer apellido.
	 *
	 * @return  primerApellido String
	 */
	public String getPrimerApellido() {
		return primerApellido;
	}
	
	/**
	 * Establece el primer apellido.
	 *
	 * @param primerApellido String
	 */
	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}
	
	/**
	 * Obtiene el segundo apellido.
	 *
	 * @return segundoApellido String
	 */
	public String getSegundoApellido() {
		return segundoApellido;
	}
	
	/**
	 * Establece el segundo apellido.
	 *
	 * @param segundoApellido String
	 */
	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}
	
	/**
	 * Obtiene el tipo.
	 *
	 * @return tipo TipoSolicitud
	 */
	public TipoSolicitud getTipo() {
		return tipo;
	}
	
	/**
	 * Establece el tipo.
	 *
	 * @param tipo TipoSolicitud
	 */
	public void setTipo(TipoSolicitud tipo) {
		this.tipo = tipo;
	}
	
	/**
	 * Obtiene el edad verificada.
	 *
	 * @return edadVerificada boolean
	 */
	public Character getEdadVerificada() {
		return edadVerificada;
	}
	
	/**
	 * Establece el edad verificada.
	 *
	 * @param edadVerificada boolean
	 */
	public void setEdadVerificada(Character edadVerificada) {
		this.edadVerificada = edadVerificada;
	}

	/**
	 * Obtiene el ejercicio.
	 *
	 * @return ejercicio String
	 */
	public String getEjercicio() {
		return ejercicio;
	}
	
	/**
	 * Establece el ejercicio.
	 *
	 * @param ejercicio String
	 */
	public void setEjercicio(String ejercicio) {
		this.ejercicio = ejercicio;
	}


	/**
	 * Obtiene el id convocatoria.
	 *
	 * @return idConvocatoria Long
	 */
	public Long getIdConvocatoria() {
		return idConvocatoria;
	}
	
	/**
	 * Establece el id convocatoria.
	 *
	 * @param idConvocatoria Long
	 */
	public void setIdConvocatoria(Long idConvocatoria) {
		this.idConvocatoria = idConvocatoria;
	}
	/** 
	 * @return centroGestor String
	 */
	public String getCentroGestor() {
		return centroGestor;
	}
	
	/**
	 * Establece el centro gestor.
	 *
	 * @param centroGestor String
	 */
	public void setCentroGestor(String centroGestor) {
		this.centroGestor = centroGestor;
	}
	
	/**
	 * Obtiene el sol exencion.
	 *
	 * @return solExencion boolean
	 */
	public boolean getSolExencion() {
		return solExencion;
	}
	
	/**
	 * Establece el sol exencion.
	 *
	 * @param solExencion boolean
	 */
	public void setSolExencion(boolean solExencion) {
		this.solExencion = solExencion;
	}
	
	/**
	 * Obtiene el des sol exencion.
	 *
	 * @return el des sol exencion
	 */
	public String getDesSolExencion() {
		return (solExencion == true ? "Si" : "No");
	}
	
	/**
	 * Obtiene el tipo descripcion.
	 *
	 * @return tipoDescripcion String
	 */
	public String getTipoDescripcion() {
		return tipoDescripcion;
	}
	
	/**
	 * Establece el tipo descripcion.
	 *
	 * @param tipoDescripcion String
	 */
	public void setTipoDescripcion(String tipoDescripcion) {
		this.tipoDescripcion = tipoDescripcion;
	}

	/**
	 * Obtiene el numero solicitud.
	 *
	 * @return numeroSolicitud String
	 */
	public String getNumeroSolicitud() {
		return numeroSolicitud;
	}
	
	/**
	 * Establece el numero solicitud.
	 *
	 * @param numeroSolicitud String
	 */
	public void setNumeroSolicitud(String numeroSolicitud) {
		this.numeroSolicitud = numeroSolicitud;
	}
	
	/**
	 * Obtiene el fecha nacimiento.
	 *
	 * @return fechaNacimiento String
	 */
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	
	/**
	 * Establece el fecha nacimiento.
	 *
	 * @param fechaNacimiento String
	 */
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	
	/**
	 * Obtiene el estado solicitud.
	 *
	 * @return estadoSolicitud EstadoSolicitud
	 */
	public EstadoSolicitud getEstadoSolicitud() {
		return estadoSolicitud;
	}
	/** 
	 * @param estadoSolicitud EstadoSolicitud
	 */
	public void setEstadoSolicitud(EstadoSolicitud estadoSolicitud) {
		this.estadoSolicitud = estadoSolicitud;
	}
	
	/**
	 * Obtiene el convocatoria.
	 *
	 * @return convocatoria Convocatoria
	 */
	public Convocatoria getConvocatoria() {
		return convocatoria;
	}
	
	/**
	 * Establece el convocatoria.
	 *
	 * @param convocatoria Convocatoria
	 */
	public void setConvocatoria(Convocatoria convocatoria) {
		this.convocatoria = convocatoria;
	}
	
	/**
	 * Obtiene el provincia by id provincia nacimiento.
	 *
	 * @return provinciaByIdProvinciaNacimiento Provincia
	 */
	public Provincia getProvinciaByIdProvinciaNacimiento() {
		return provinciaByIdProvinciaNacimiento;
	}
	
	/**
	 * Establece el provincia by id provincia nacimiento.
	 *
	 * @param provinciaByIdProvinciaNacimiento Provincia
	 */
	public void setProvinciaByIdProvinciaNacimiento(
			Provincia provinciaByIdProvinciaNacimiento) {
		this.provinciaByIdProvinciaNacimiento = provinciaByIdProvinciaNacimiento;
	}
	
	/**
	 * Obtiene el provincia by id provincia examen.
	 *
	 * @return provinciaByIdProvinciaExamen Provincia
	 */
	public ProvinciaExamen getProvinciaByIdProvinciaExamen() {
		return provinciaByIdProvinciaExamen;
	}
	/** 
	 * @param provinciaByIdProvinciaExamen Provincia
	 */
	public void setProvinciaByIdProvinciaExamen(
			ProvinciaExamen provinciaByIdProvinciaExamen) {
		this.provinciaByIdProvinciaExamen = provinciaByIdProvinciaExamen;
	}
	
	/**
	 * Obtiene el provincia by id provincia domicilio.
	 *
	 * @return provinciaByIdProvinciaDomicilio Provincia
	 */
	public Provincia getProvinciaByIdProvinciaDomicilio() {
		return provinciaByIdProvinciaDomicilio;
	}
	
	/**
	 * Establece el provincia by id provincia domicilio.
	 *
	 * @param provinciaByIdProvinciaDomicilio Provincia
	 */
	public void setProvinciaByIdProvinciaDomicilio(
			Provincia provinciaByIdProvinciaDomicilio) {
		this.provinciaByIdProvinciaDomicilio = provinciaByIdProvinciaDomicilio;
	}
	/** 
	 * @return pais Pais
	 */
	public Pais getPais() {
		return pais;
	}
	
	/**
	 * Establece el pais.
	 *
	 * @param pais Pais
	 */ 
	public void setPais(Pais pais) {
		this.pais = pais;
	}
	
	/**
	 * Obtiene el sexo.
	 *
	 * @return sexo Character
	 */
	public Character getSexo() {
		return sexo;
	}
	
	/**
	 * Establece el sexo.
	 *
	 * @param sexo Character
	 */
	public void setSexo(Character sexo) {
		this.sexo = sexo;
	}
	
	/**
	 * Obtiene el titulo oficial.
	 *
	 * @return tituloOficial TituloOficial
	 */
	public TituloOficial getTituloOficial() {
		return tituloOficial;
	}
	
	/**
	 * Establece el titulo oficial.
	 *
	 * @param tituloOficial TituloOficial
	 */
	public void setTituloOficial(TituloOficial tituloOficial) {
		this.tituloOficial = tituloOficial;
	}

	/**
	 * Comprueba si es es desempleo.
	 *
	 * @return verdadero, si es es desempleo
	 */
	public boolean isEsDesempleo() {
		return esDesempleo;
	}
	
	/**
	 * Establece el es desempleo.
	 *
	 * @param esDesempleo el nuevo es desempleo
	 */
	public void setEsDesempleo(boolean esDesempleo) {
		this.esDesempleo = esDesempleo;
	}
	
	/**
	 * Comprueba si es es discapacidad.
	 *
	 * @return verdadero, si es es discapacidad
	 */
	public boolean isEsDiscapacidad() {
		return esDiscapacidad;
	}
	
	/**
	 * Establece el es discapacidad.
	 *
	 * @param esDiscapacidad el nuevo es discapacidad
	 */
	public void setEsDiscapacidad(boolean esDiscapacidad) {
		this.esDiscapacidad = esDiscapacidad;
	}
	
	/**
	 * Comprueba si es es F numerosa.
	 *
	 * @return verdadero, si es es F numerosa
	 */
	public boolean isEsFNumerosa() {
		return esFNumerosa;
	}
	
	/**
	 * Establece el es F numerosa.
	 *
	 * @param esFNumerosa el nuevo es F numerosa
	 */
	public void setEsFNumerosa(boolean esFNumerosa) {
		this.esFNumerosa = esFNumerosa;
	}

	/**
	 * Obtiene el fam numerosa.
	 *
	 * @return el fam numerosa
	 */
	public String getFamNumerosa() {
		return famNumerosa;
	}
	
	/**
	 * Establece el fam numerosa.
	 *
	 * @param famNumerosa el nuevo fam numerosa
	 */
	public void setFamNumerosa(String famNumerosa) {
		this.famNumerosa = famNumerosa;
	}
	
	/**
	 * Obtiene el desempleo verificado.
	 *
	 * @return el desempleo verificado
	 */
	public Character getDesempleoVerificado() {
		return DesempleoVerificado;
	}
	
	/**
	 * Establece el desempleo verificado.
	 *
	 * @param desempleoVerificado el nuevo desempleo verificado
	 */
	public void setDesempleoVerificado(Character desempleoVerificado) {
		DesempleoVerificado = desempleoVerificado;
	}
	
	/**
	 * Obtiene el fnumerosa verificado.
	 *
	 * @return el fnumerosa verificado
	 */
	public Character getFnumerosaVerificado() {
		return FnumerosaVerificado;
	}
	
	/**
	 * Establece el fnumerosa verificado.
	 *
	 * @param fnumerosaVerificado el nuevo fnumerosa verificado
	 */
	public void setFnumerosaVerificado(Character fnumerosaVerificado) {
		FnumerosaVerificado = fnumerosaVerificado;
	}
	
	/**
	 * Obtiene el discapacidad verificado.
	 *
	 * @return el discapacidad verificado
	 */
	public Character getDiscapacidadVerificado() {
		return DiscapacidadVerificado;
	}
	
	/**
	 * Establece el discapacidad verificado.
	 *
	 * @param discapacidadVerificado el nuevo discapacidad verificado
	 */
	public void setDiscapacidadVerificado(Character discapacidadVerificado) {
		DiscapacidadVerificado = discapacidadVerificado;
	}
	
	/**
	 * Obtiene el tipo discapacidad.
	 *
	 * @return tipoDiscapacidad TipoDiscapacidad
	 */
	public TipoDiscapacidad getTipoDiscapacidad() {
		return tipoDiscapacidad;
	}
	
	/**
	 * Establece el tipo discapacidad.
	 *
	 * @param tipoDiscapacidad TipoDiscapacidad
	 */
	public void setTipoDiscapacidad(TipoDiscapacidad tipoDiscapacidad) {
		this.tipoDiscapacidad = tipoDiscapacidad;
	}
	
	/**
	 * Obtiene el especialidad.
	 *
	 * @return especialidad Especialidad
	 */
	public Especialidad getEspecialidad() {
		return especialidad;
	}
	
	/**
	 * Establece el especialidad.
	 *
	 * @param especialidad Especialidad
	 */
	public void setEspecialidad(Especialidad especialidad) {
		this.especialidad = especialidad;
	}
	
	/**
	 * Obtiene el localidad nacimiento.
	 *
	 * @return localidadNacimiento String
	 */
	public String getLocalidadNacimiento() {
		return localidadNacimiento;
	}
	
	/**
	 * Establece el localidad nacimiento.
	 *
	 * @param localidadNacimiento String
	 */
	public void setLocalidadNacimiento(String localidadNacimiento) {
		this.localidadNacimiento = localidadNacimiento;
	}
	
	/**
	 * Obtiene el nacionalidad.
	 *
	 * @return  nacionalidad String
	 */
	public String getNacionalidad() {
		return nacionalidad;
	}
	
	/**
	 * Establece el nacionalidad.
	 *
	 * @param nacionalidad String
	 */
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	
	/**
	 * Obtiene el telefono.
	 *
	 * @return telefono String
	 */
	public String getTelefono() {
		return telefono;
	}
	
	/**
	 * Establece el telefono.
	 *
	 * @param telefono String
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	/**
	 * Obtiene el email.
	 *
	 * @return  email String
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Establece el email.
	 *
	 * @param email String
	 */ 
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * Obtiene el calle domicilio.
	 *
	 * @return calleDomicilio String
	 */
	public String getCalleDomicilio() {
		return calleDomicilio;
	}
	
	/**
	 * Establece el calle domicilio.
	 *
	 * @param calleDomicilio String
	 */
	public void setCalleDomicilio(String calleDomicilio) {
		this.calleDomicilio = calleDomicilio;
	}
	
	/**
	 * Obtiene el codigo postal domicilio.
	 *
	 * @return  codigoPostalDomicilio String
	 */
	public String getCodigoPostalDomicilio() {
		return codigoPostalDomicilio;
	}
	
	/**
	 * Establece el codigo postal domicilio.
	 *
	 * @param codigoPostalDomicilio String
	 */
	public void setCodigoPostalDomicilio(String codigoPostalDomicilio) {
		this.codigoPostalDomicilio = codigoPostalDomicilio;
	}
	
	/**
	 * Obtiene el municipio domicilio.
	 *
	 * @return municipioDomicilio String
	 */
	public String getMunicipioDomicilio() {
		return municipioDomicilio;
	}
	
	/**
	 * Establece el municipio domicilio.
	 *
	 * @param municipioDomicilio String
	 */
	public void setMunicipioDomicilio(String municipioDomicilio) {
		this.municipioDomicilio = municipioDomicilio;
	}
	
	/**
	 * Obtiene el porcentaje discapacidad.
	 *
	 * @return porcentajeDiscapacidad Integer
	 */
	public Integer getPorcentajeDiscapacidad() {
		return porcentajeDiscapacidad;
	}
	
	/**
	 * Establece el porcentaje discapacidad.
	 *
	 * @param porcentajeDiscapacidad Integer
	 */
	public void setPorcentajeDiscapacidad(Integer porcentajeDiscapacidad) {
		this.porcentajeDiscapacidad = porcentajeDiscapacidad;
	}
	
	/**
	 * Obtiene el reserva discapacidad.
	 *
	 * @return  reservaDiscapacidad Character
	 */
	public Character getReservaDiscapacidad() {
		return reservaDiscapacidad;
	}
	
	/**
	 * Establece el reserva discapacidad.
	 *
	 * @param reservaDiscapacidad Character
	 */
	public void setReservaDiscapacidad(Character reservaDiscapacidad) {
		this.reservaDiscapacidad = reservaDiscapacidad;
	}
	
	/**
	 * Obtiene el detalle discapacidad.
	 *
	 * @return detalleDiscapacidad String
	 */
	public String getDetalleDiscapacidad() {
		return detalleDiscapacidad;
	}
	
	/**
	 * Establece el detalle discapacidad.
	 *
	 * @param detalleDiscapacidad String
	 */
	public void setDetalleDiscapacidad(String detalleDiscapacidad) {
		this.detalleDiscapacidad = detalleDiscapacidad;
	}
	
	/**
	 * Obtiene el otros titulos.
	 *
	 * @return  otrosTitulos String
	 */
	public String getOtrosTitulos() {
		return otrosTitulos;
	}
	
	/**
	 * Establece el otros titulos.
	 *
	 * @param otrosTitulos String
	 */
	public void setOtrosTitulos(String otrosTitulos) {
		this.otrosTitulos = otrosTitulos;
	}
	
	/**
	 * Obtiene el datos A.
	 *
	 * @return datosA String
	 */
	public String getDatosA() {
		return datosA;
	}
	
	/**
	 * Establece el datos A.
	 *
	 * @param datosA String
	 */
	public void setDatosA(String datosA) {
		this.datosA = datosA;
	}
	
	/**
	 * Obtiene el datos B.
	 *
	 * @return datosB String
	 */
	public String getDatosB() {
		return datosB;
	}
	
	/**
	 * Establece el datos B.
	 *
	 * @param datosB String
	 */
	public void setDatosB(String datosB) {
		this.datosB = datosB;
	}
	
	/**
	 * Obtiene el datos C.
	 *
	 * @return  datosC String
	 */
	public String getDatosC() {
		return datosC;
	}
	
	/**
	 * Establece el datos C.
	 *
	 * @param datosC String
	 */
	public void setDatosC(String datosC) {
		this.datosC = datosC;
	}
	
	/**
	 * Obtiene el fecha utl actualizacion.
	 *
	 * @return fechaUtlActualizacion Date
	 */
	public Date getFechaUtlActualizacion() {
		return fechaUtlActualizacion;
	}
	
	/**
	 * Establece el fecha utl actualizacion.
	 *
	 * @param fechaUtlActualizacion Date
	 */
	public void setFechaUtlActualizacion(Date fechaUtlActualizacion) {
		this.fechaUtlActualizacion = fechaUtlActualizacion;
	}
	
	/**
	 * Obtiene el comentarios.
	 *
	 * @return comentarios String
	 */
	public String getComentarios() {
		return comentarios;
	}
	
	/**
	 * Establece el comentarios.
	 *
	 * @param comentarios String
	 */
	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}
	
	/**
	 * Obtiene el documentos.
	 *
	 * @return  documentos Set<Documento>
	 */
	public Set<Documento> getDocumentos() {
		return documentos;
	}
	
	/**
	 * Establece el documentos.
	 *
	 * @param documentos Set<Documento>
	 */
	public void setDocumentos(Set<Documento> documentos) {
		this.documentos = documentos;
	}
	/** 
	 * @return correoElectronicos Set<CorreoElectronico> 
	 */
	public Set<CorreoElectronico> getCorreoElectronicos() {
		return correoElectronicos;
	}
	
	/**
	 * Establece el correo electronicos.
	 *
	 * @param correoElectronicos Set<CorreoElectronico>
	 */
	public void setCorreoElectronicos(Set<CorreoElectronico> correoElectronicos) {
		this.correoElectronicos = correoElectronicos;
	}
	
	/**
	 * Obtiene el log solicitudes.
	 *
	 * @return logSolicitudes Set<LogSolicitud>
	 */
	public Set<LogSolicitud> getLogSolicitudes() {
		return logSolicitudes;
	}
	
	/**
	 * Establece el log solicitudes.
	 *
	 * @param logSolicitudes Set<LogSolicitud>
	 */
	public void setLogSolicitudes(Set<LogSolicitud> logSolicitudes) {
		this.logSolicitudes = logSolicitudes;
	}
	
	/**
	 * Obtiene el importe.
	 *
	 * @return importe Float
	 */
	public Float getImporte() {
		return importe;
	}
	
	/**
	 * Establece el importe.
	 *
	 * @param importe Float
	 */
	public void setImporte(Float importe) {
		this.importe = importe;
	}
	
	/**
	 * Obtiene el justificante pago.
	 *
	 * @return justificantePago String
	 */
	public String getJustificantePago() {
		return justificantePago;
	}
	
	/**
	 * Establece el justificante pago.
	 *
	 * @param justificantePago String
	 */
	public void setJustificantePago(String justificantePago) {
		this.justificantePago = justificantePago;
	}
	
	/**
	 * Obtiene el id estado solicitud.
	 *
	 * @return the idEstadoSolicitud
	 */
	public Integer getIdEstadoSolicitud() {
		return idEstadoSolicitud;
	}
	
	/**
	 * Establece el id estado solicitud.
	 *
	 * @param idEstadoSolicitud the idEstadoSolicitud to set
	 */
	public void setIdEstadoSolicitud(Integer idEstadoSolicitud) {
		this.idEstadoSolicitud = idEstadoSolicitud;
	}
	
	/**
	 * Obtiene el desc estado solicitud.
	 *
	 * @return the descEstadoSolicitud
	 */
	public String getDescEstadoSolicitud() {
		return descEstadoSolicitud;
	}
	
	/**
	 * Establece el desc estado solicitud.
	 *
	 * @param descEstadoSolicitud the descEstadoSolicitud to set
	 */
	public void setDescEstadoSolicitud(String descEstadoSolicitud) {
		this.descEstadoSolicitud = descEstadoSolicitud;
	}
	
	/**
	 * Obtiene el descripcion especialidad.
	 *
	 * @return  descripcionEspecialidad String
	 */
	public String getDescripcionEspecialidad() {
		return descripcionEspecialidad;
	}
	
	/**
	 * Establece el descripcion especialidad.
	 *
	 * @param descripcionEspecialidad String
	 */
	public void setDescripcionEspecialidad(String descripcionEspecialidad) {
		this.descripcionEspecialidad = descripcionEspecialidad;
	}
	/** 
	 * @return descripcionIdProvinciaNacimiento String
	 */
	public String getDescripcionIdProvinciaNacimiento() {
		return descripcionIdProvinciaNacimiento;
	}
	
	/**
	 * Establece el descripcion id provincia nacimiento.
	 *
	 * @param descripcionIdProvinciaNacimiento String
	 */
	public void setDescripcionIdProvinciaNacimiento(
			String descripcionIdProvinciaNacimiento) {
		this.descripcionIdProvinciaNacimiento = descripcionIdProvinciaNacimiento;
	}
	
	/**
	 * Obtiene el descripcion id provincia domicilio.
	 *
	 * @return descripcionIdProvinciaDomicilio String
	 */
	public String getDescripcionIdProvinciaDomicilio() {
		return descripcionIdProvinciaDomicilio;
	}
	
	/**
	 * Establece el descripcion id provincia domicilio.
	 *
	 * @param descripcionIdProvinciaDomicilio String
	 */
	public void setDescripcionIdProvinciaDomicilio(
			String descripcionIdProvinciaDomicilio) {
		this.descripcionIdProvinciaDomicilio = descripcionIdProvinciaDomicilio;
	}
	
	/**
	 * Obtiene el descripcion id provincia examen.
	 *
	 * @return descripcionIdProvinciaExamen String
	 */
	public String getDescripcionIdProvinciaExamen() {
		return descripcionIdProvinciaExamen;
	}
	
	/**
	 * Establece el descripcion id provincia examen.
	 *
	 * @param descripcionIdProvinciaExamen String
	 */
	public void setDescripcionIdProvinciaExamen(String descripcionIdProvinciaExamen) {
		this.descripcionIdProvinciaExamen = descripcionIdProvinciaExamen;
	}
	
	/**
	 * Obtiene el descripcion tipo discapacidad.
	 *
	 * @return descripcionTipoDiscapacidad String
	 */ 
	public String getDescripcionTipoDiscapacidad() {
		return descripcionTipoDiscapacidad;
	}
	
	/**
	 * Establece el descripcion tipo discapacidad.
	 *
	 * @param descripcionTipoDiscapacidad String
	 */
	public void setDescripcionTipoDiscapacidad(String descripcionTipoDiscapacidad) {
		this.descripcionTipoDiscapacidad = descripcionTipoDiscapacidad;
	}
	
	/**
	 * Obtiene el descripcion titulo oficial.
	 *
	 * @return  descripcionTituloOficial String
	 */
	public String getDescripcionTituloOficial() {
		return descripcionTituloOficial;
	}
	
	/**
	 * Establece el descripcion titulo oficial.
	 *
	 * @param descripcionTituloOficial String
	 */
	public void setDescripcionTituloOficial(String descripcionTituloOficial) {
		this.descripcionTituloOficial = descripcionTituloOficial;
	}
	
	/**
	 * Obtiene el descripcion tipo pago.
	 *
	 * @return  descripcionTipoPago String
	 */ 
	public String getDescripcionTipoPago() {
		return descripcionTipoPago;
	}
	
	/**
	 * Establece el descripcion tipo pago.
	 *
	 * @param descripcionTipoPago String
	 */
	public void setDescripcionTipoPago(String descripcionTipoPago) {
		this.descripcionTipoPago = descripcionTipoPago;
	}
	/** 
	 * @return nrc String
	 */
	public String getNrc() {
		return nrc;
	}
	
	/**
	 * Establece el nrc.
	 *
	 * @param nrc String
	 */
	public void setNrc(String nrc) {
		this.nrc = nrc;
	}
	
	/**
	 * Obtiene el fecha registro.
	 *
	 * @return fechaRegistro String
	 */
	public String getFechaRegistro() {
		return fechaRegistro;
	}
	
	/**
	 * Establece el fecha registro.
	 *
	 * @param fechaRegistro String
	 */
	public void setFechaRegistro(String fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	
	/**
	 * Obtiene el numero registro.
	 *
	 * @return numeroRegistro String
	 */
	public String getNumeroRegistro() {
		return numeroRegistro;
	}
	
	/**
	 * Establece el numero registro.
	 *
	 * @param numeroRegistro String
	 */
	public void setNumeroRegistro(String numeroRegistro) {
		this.numeroRegistro = numeroRegistro;
	}
	
	/**
	 * Obtiene el nacion pais domicilio.
	 *
	 * @return nacionPaisDomicilio String
	 */
	public String getNacionPaisDomicilio() {
		return nacionPaisDomicilio;
	}
	
	/**
	 * Establece el nacion pais domicilio.
	 *
	 * @param nacionPaisDomicilio String
	 */
	public void setNacionPaisDomicilio(String nacionPaisDomicilio) {
		this.nacionPaisDomicilio = nacionPaisDomicilio;
	}
	
	/**
	 * Obtiene el id tipo.
	 *
	 * @return  idTipo Integer
	 */
	public Integer getIdTipo() {
		return idTipo;
	}
	
	/**
	 * Establece el id tipo.
	 *
	 * @param idTipo Integer
	 */
	public void setIdTipo(Integer idTipo) {
		this.idTipo = idTipo;
	}

/**
 * Obtiene el listado correos electronico.
 *
 * @return the listadoCorreosElectronico
 */
	public ArrayList<CorreoElectronicoBean> getListadoCorreosElectronico() {
		return listadoCorreosElectronico;
	}
	
	/**
	 * Establece el listado correos electronico.
	 *
	 * @param listadoCorreosElectronico the listadoCorreosElectronico to set
	 */
	public void setListadoCorreosElectronico(
			ArrayList<CorreoElectronicoBean> listadoCorreosElectronico) {
		this.listadoCorreosElectronico = listadoCorreosElectronico;
	}
		
		/**
		 * Obtiene el pago solicitudes.
		 *
		 * @return pagoSolicitudes PagoSolicitudBean
		 */
	public ArrayList<PagoSolicitudBean> getPagoSolicitudes() {
		return pagoSolicitudes;
	}
	
	/**
	 * Establece el pago solicitudes.
	 *
	 * @param pagoSolicitudes PagoSolicitudBean
	 */
	public void setPagoSolicitudes(ArrayList<PagoSolicitudBean> pagoSolicitudes) {
		this.pagoSolicitudes = pagoSolicitudes;
	}
	
	/**
	 * Obtiene el registro solicitudes.
	 *
	 * @return registroSolicitudes ArrayList<RegistroSolicitudBean>
	 */
	public ArrayList<RegistroSolicitudBean> getRegistroSolicitudes() {
		return registroSolicitudes;
	}
	
	/**
	 * Establece el registro solicitudes.
	 *
	 * @param registroSolicitudes ArrayList<RegistroSolicitudBean>
	 */
	public void setRegistroSolicitudes(
			ArrayList<RegistroSolicitudBean> registroSolicitudes) {
		this.registroSolicitudes = registroSolicitudes;
	}
	
	/**
	 * Establece el des sol exencion.
	 *
	 * @param desSolExencion el nuevo des sol exencion
	 */
	public void setDesSolExencion(String desSolExencion) {  //Metodo vacio
	}
	
	/**
	 * Obtiene el siglas centro gestor.
	 *
	 * @return el siglas centro gestor
	 */
	public String getSiglasCentroGestor() {
		return siglasCentroGestor;
	}
	
	/**
	 * Establece el siglas centro gestor.
	 *
	 * @param siglasCentroGestor el nuevo siglas centro gestor
	 */
	public void setSiglasCentroGestor(String siglasCentroGestor) {
		this.siglasCentroGestor = siglasCentroGestor;
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
	 * Obtiene el id tipo acceso.
	 *
	 * @return el id tipo acceso
	 */
	public Integer getIdTipoAcceso() {
		return idTipoAcceso;
	}
	
	/**
	 * Establece el id tipo acceso.
	 *
	 * @param idTipoAcceso el nuevo id tipo acceso
	 */
	public void setIdTipoAcceso(Integer idTipoAcceso) {
		this.idTipoAcceso = idTipoAcceso;
	}
	
	/**
	 * Obtiene el fecha nacimiento verificada.
	 *
	 * @return el fecha nacimiento verificada
	 */
	public Character getFechaNacimientoVerificada() {
		return fechaNacimientoVerificada;
	}
	
	/**
	 * Establece el fecha nacimiento verificada.
	 *
	 * @param fechaNacimientoVerificada el nuevo fecha nacimiento verificada
	 */
	public void setFechaNacimientoVerificada(Character fechaNacimientoVerificada) {
		this.fechaNacimientoVerificada = fechaNacimientoVerificada;
	}
	
	/**
	 * Obtiene el titulo verificado.
	 *
	 * @return el titulo verificado
	 */
	public Character getTituloVerificado() {
		return tituloVerificado;
	}
	
	/**
	 * Establece el titulo verificado.
	 *
	 * @param tituloVerificado el nuevo titulo verificado
	 */
	public void setTituloVerificado(Character tituloVerificado) {
		this.tituloVerificado = tituloVerificado;
	}
	
	/**
	 * Obtiene el fecha nacimiento svdi.
	 *
	 * @return el fecha nacimiento svdi
	 */
	public Date getFechaNacimientoSvdi() {
		return fechaNacimientoSvdi;
	}
	
	/**
	 * Establece el fecha nacimiento svdi.
	 *
	 * @param fechaNacimientoSvdi el nuevo fecha nacimiento svdi
	 */
	public void setFechaNacimientoSvdi(Date fechaNacimientoSvdi) {
		this.fechaNacimientoSvdi = fechaNacimientoSvdi;
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
	 * Obtiene el fecha boe.
	 *
	 * @return el fecha boe
	 */
	public String getFechaBoe() {
		return fechaBoe;
	}
	
	/**
	 * Establece el fecha boe.
	 *
	 * @param fechaBoe el nuevo fecha boe
	 */
	public void setFechaBoe(String fechaBoe) {
		this.fechaBoe = fechaBoe;
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
	 * Obtiene el admitido.
	 *
	 * @return el admitido
	 */
	public String getAdmitido() {
		return admitido;
	}
	
	/**
	 * Establece el admitido.
	 *
	 * @param admitido el nuevo admitido
	 */
	public void setAdmitido(String admitido) {
		this.admitido = admitido;
	}
	
	/**
	 * Obtiene el estado PID.
	 *
	 * @return el estado PID
	 */
	public String getEstadoPID() {
		return estadoPID;
	}
	
	/**
	 * Establece el estado PID.
	 *
	 * @param estadoPID el nuevo estado PID
	 */
	public void setEstadoPID(String estadoPID) {
		this.estadoPID = estadoPID;
	}
	
	/**
	 * Obtiene el id motivo exencion.
	 *
	 * @return el id motivo exencion
	 */
	public Integer getIdMotivoExencion() {
		return idMotivoExencion;
	}
	
	/**
	 * Establece el id motivo exencion.
	 *
	 * @param idMotivoExencion el nuevo id motivo exencion
	 */
	public void setIdMotivoExencion(Integer idMotivoExencion) {
		this.idMotivoExencion = idMotivoExencion;
	}
	
	/**
	 * Obtiene el motivo exencion desc.
	 *
	 * @return el motivo exencion desc
	 */
	public String getMotivoExencionDesc() {
		return motivoExencionDesc;
	}
	
	/**
	 * Establece el motivo exencion desc.
	 *
	 * @param motivoExencionDesc el nuevo motivo exencion desc
	 */
	public void setMotivoExencionDesc(String motivoExencionDesc) {
		this.motivoExencionDesc = motivoExencionDesc;
	}
	
	/**
	 * Obtiene el comunidad desc.
	 *
	 * @return el comunidad desc
	 */
	public String getComunidadDesc() {
		return comunidadDesc;
	}
	
	/**
	 * Establece el comunidad desc.
	 *
	 * @param comunidadDesc el nuevo comunidad desc
	 */
	public void setComunidadDesc(String comunidadDesc) {
		this.comunidadDesc = comunidadDesc;
	}
	
	/**
	 * Obtiene el titulo FN.
	 *
	 * @return el titulo FN
	 */
	public String getTituloFN() {
		return tituloFN;
	}
	
	/**
	 * Establece el titulo FN.
	 *
	 * @param tituloFN el nuevo titulo FN
	 */
	public void setTituloFN(String tituloFN) {
		this.tituloFN = tituloFN;
	}
	
	/**
	 * Obtiene el motivo reduccion.
	 *
	 * @return el motivo reduccion
	 */
	public Integer getMotivoReduccion() {
		return motivoReduccion;
	}
	
	/**
	 * Establece el motivo reduccion.
	 *
	 * @param motivoReduccion el nuevo motivo reduccion
	 */
	public void setMotivoReduccion(Integer motivoReduccion) {
		this.motivoReduccion = motivoReduccion;
	}
	
	/**
	 * @return the idConsentimientoAeat
	 */
	public Boolean getIdConsentimientoAeat() {
		return idConsentimientoAeat;
	}

	/**
	 * @param idConsentimientoAeat the idConsentimientoAeat to set
	 */
	public void setIdConsentimientoAeat(Boolean idConsentimientoAeat) {
		this.idConsentimientoAeat = idConsentimientoAeat;
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

	/**
	 * Comprueba si es es victima terrorismo.
	 *
	 * @return verdadero, si es es victima terrorismo
	 */
	public boolean isEsVictimaTerrorismo() {
		return esVictimaTerrorismo;
	}
	
	/**
	 * Establece el es victima terrorismo.
	 *
	 * @param esVictimaTerrorismo el nuevo es victima terrorismo
	 */
	public void setEsVictimaTerrorismo(boolean esVictimaTerrorismo) {
		this.esVictimaTerrorismo = esVictimaTerrorismo;
	}

	/**
	 * Obtiene el victimas verificado.
	 *
	 * @return el victimas verificado
	 */
	public Character getVictimasVerificado() {
		return victimasVerificado;
	}

	/**
	 * Establece el victimas verificado.
	 *
	 * @param victimasVerificado el nuevo victimas verificado
	 */
	public void setVictimasVerificado(Character victimasVerificado) {
		this.victimasVerificado = victimasVerificado;
	}

	/**
	 * Obtiene el prueba derecho.
	 *
	 * @return el prueba derecho
	 */
	public String getPruebaDerecho() {
		return pruebaDerecho;
	}

	/**
	 * Establece el prueba derecho.
	 *
	 * @param pruebaDerecho el nuevo prueba derecho
	 */
	public void setPruebaDerecho(String pruebaDerecho) {
		this.pruebaDerecho = pruebaDerecho;
	}

	/**
	 * Obtiene el prueba idioma.
	 *
	 * @return el prueba idioma
	 */
	public String getPruebaIdioma() {
		return pruebaIdioma;
	}

	/**
	 * Establece el prueba idioma.
	 *
	 * @param pruebaIdioma el nuevo prueba idioma
	 */
	public void setPruebaIdioma(String pruebaIdioma) {
		this.pruebaIdioma = pruebaIdioma;
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
}
