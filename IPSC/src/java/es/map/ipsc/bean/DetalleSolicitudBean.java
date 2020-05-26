package es.map.ipsc.bean;

import java.util.List;



/**
 * El Class DetalleSolicitudBean.
 */
public class DetalleSolicitudBean {
	
	/** el registro. */
	private String registro;
	
	/** el centro gestor. */
	//Datos de la convocatoria
	private String centroGestor;
	
	/** el num justificante. */
	private String numJustificante;
	
	/** el ejercicio solicitud. */
	private String ejercicioSolicitud;
	
	/** el consentimiento. */
	private String consentimiento;
	
	/** el motivo oposicion. */
	private String motivoOposicion;
	
/** el nombre. */
//Datos del usuario
	private String nombre;
	
	/** el primer apellido. */
	private String primerApellido;
	
	/** el segundo apellido. */
	private String segundoApellido;
	
	/** el nombre completo. */
	private String nombreCompleto;
	
	/** el fecha nacimiento. */
	private String fechaNacimiento;
	
	/** el nif. */
	private String nif;
	
	/** el sexo. */
	private char sexo;
	
	/** el provincia nacimiento. */
	private String provinciaNacimiento;
	
	/** el localidad nacimiento. */
	private String localidadNacimiento;
	
	/** el nacionalidad. */
	private String nacionalidad;
	
/** el calle domicilio. */
//Datos del domicilio
	private String calleDomicilio;
	
	/** el cod postal domicilio. */
	private String codPostalDomicilio;
	
	/** el municipio domicilio. */
	private String municipioDomicilio;
	
	/** el provincia domicilio. */
	private String provinciaDomicilio;
	
	/** el nacion domicilio. */
	private String nacionDomicilio;
	
	/** el telefono. */
	private String telefono;
	
	/** el telefono aux. */
	private String telefonoAux;
	
	/** el telefono 1. */
	private String telefono1;
	
	/** el correo electronico. */
	private String correoElectronico;
	
/** el grupo. */
//Datos de la convocatoria
	private String grupo;
	
	/** el cuerpo. */
	private String cuerpo;
	
	/** el especialidad. */
	private String especialidad;
	
	/** el forma acceso. */
	private String formaAcceso;
	
	/** el ministerio. */
	private String ministerio;
	
	/** el fecha BOE. */
	private String fechaBOE;
	
	/** el provincia examen. */
	private String provinciaExamen;
	
	/** el porcentaje discapacidad. */
	private String porcentajeDiscapacidad;
	
	/** el discapacidad. */
	private String discapacidad;
	
	/** el reserva discapacitados. */
	private char reservaDiscapacitados;
	
	/** el adaptacion discapacidad. */
	private Long adaptacionDiscapacidad;
	
	/** el motivo discapacidad. */
	private String motivoDiscapacidad;
	
	/** el importe. */
	private Float importe;
	
	/** el dirigido A. */
	private String dirigidoA;
	
/** el titulos. */
//Titulos academicos oficiales
	private String titulos;
	
	/** el otros titulos. */
	private String otrosTitulos;
	
/** el datos A. */
//Datos a consignar
	private String datosA;
	
	/** el datos B. */
	private String datosB;
	
	/** el datos C. */
	private String datosC;
	
	/** el solicitud propios bean. */
	private List<SolicitudPropiosBean> solicitudPropiosBean;
	
	/** el estado solicitud. */
	//Otros datos
	private String estadoSolicitud;
	
	/** el id estado solicitud. */
	private Integer idEstadoSolicitud;
	
	/** el id motivo reduccion tasa. */
	private Integer idMotivoReduccionTasa;
	
	/** el id comunidad FN. */
	// comunidades reconocimiento reduccion tasa
	private Integer idComunidadFN;
	
	/** el id comunidad DD. */
	private Integer idComunidadDD;
	
	/** el titulo familia numerosa. */
	private String tituloFamiliaNumerosa;
	
	

	/**
	 * Obtiene el estado solicitud.
	 *
	 * @return el estado solicitud
	 */
	public String getEstadoSolicitud() {
		return estadoSolicitud;
	}
	
	/**
	 * Establece el estado solicitud.
	 *
	 * @param estadoSolicitud el nuevo estado solicitud
	 */
	public void setEstadoSolicitud(String estadoSolicitud) {
		this.estadoSolicitud = estadoSolicitud;
	}
	
	/**
	 * Obtiene el nombre completo.
	 *
	 * @return el nombre completo
	 */
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	
	/**
	 * Establece el nombre completo.
	 *
	 * @param nombreCompleto el nuevo nombre completo
	 */
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	
	/**
	 * Obtiene el dirigido A.
	 *
	 * @return el dirigido A
	 */
	public String getDirigidoA() {
		return dirigidoA;
	}
	
	/**
	 * Establece el dirigido A.
	 *
	 * @param dirigidoA el nuevo dirigido A
	 */
	public void setDirigidoA(String dirigidoA) {
		this.dirigidoA = dirigidoA;
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
	 * Obtiene el grupo.
	 *
	 * @return el grupo
	 */
	public String getGrupo() {
		return grupo;
	}
	
	/**
	 * Establece el grupo.
	 *
	 * @param grupo el nuevo grupo
	 */
	public void setGrupo(String grupo) {
		this.grupo = grupo;
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
	 * Obtiene el correo electronico.
	 *
	 * @return el correo electronico
	 */
	public String getCorreoElectronico() {
		return correoElectronico;
	}
	
	/**
	 * Establece el correo electronico.
	 *
	 * @param correoElectronico el nuevo correo electronico
	 */
	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}
	
	/**
	 * Obtiene el registro.
	 *
	 * @return el registro
	 */
	public String getRegistro() {
		return registro;
	}
	
	/**
	 * Establece el registro.
	 *
	 * @param registro el nuevo registro
	 */
	public void setRegistro(String registro) {
		this.registro = registro;
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
	 * @param pCentroGestor el nuevo centro gestor
	 */
	public void setCentroGestor(String pCentroGestor) {
		this.centroGestor = pCentroGestor;
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
	 * @param pNumJustificante el nuevo num justificante
	 */
	public void setNumJustificante(String pNumJustificante) {
		this.numJustificante = pNumJustificante;
	}
	
	/**
	 * Obtiene el ejercicio solicitud.
	 *
	 * @return el ejercicio solicitud
	 */
	public String getEjercicioSolicitud() {
		return ejercicioSolicitud;
	}
	
	/**
	 * Establece el ejercicio solicitud.
	 *
	 * @param pEjercicioSolicitud el nuevo ejercicio solicitud
	 */
	public void setEjercicioSolicitud(String pEjercicioSolicitud) {
		this.ejercicioSolicitud = pEjercicioSolicitud;
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
	 * @param pPrimerApellido el nuevo primer apellido
	 */
	public void setPrimerApellido(String pPrimerApellido) {
		this.primerApellido = pPrimerApellido;
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
	 * @param pSegundoApellido el nuevo segundo apellido
	 */
	public void setSegundoApellido(String pSegundoApellido) {
		this.segundoApellido = pSegundoApellido;
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
	 * @param pFechaNacimiento el nuevo fecha nacimiento
	 */
	public void setFechaNacimiento(String pFechaNacimiento) {
		this.fechaNacimiento = pFechaNacimiento;
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
	 * @param pNif el nuevo nif
	 */
	public void setNif(String pNif) {
		this.nif = pNif;
	}
	
	/**
	 * Obtiene el sexo.
	 *
	 * @return el sexo
	 */
	public char getSexo() {
		return sexo;
	}
	
	/**
	 * Establece el sexo.
	 *
	 * @param pSexo el nuevo sexo
	 */
	public void setSexo(char pSexo) {
		this.sexo = pSexo;
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
	 * @param pProvinciaNacimiento el nuevo provincia nacimiento
	 */
	public void setProvinciaNacimiento(String pProvinciaNacimiento) {
		this.provinciaNacimiento = pProvinciaNacimiento;
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
	 * @param pLocalidadNacimiento el nuevo localidad nacimiento
	 */
	public void setLocalidadNacimiento(String pLocalidadNacimiento) {
		this.localidadNacimiento = pLocalidadNacimiento;
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
	 * @param pCalleDomicilio el nuevo calle domicilio
	 */
	public void setCalleDomicilio(String pCalleDomicilio) {
		this.calleDomicilio = pCalleDomicilio;
	}
	
	/**
	 * Obtiene el cod postal domicilio.
	 *
	 * @return el cod postal domicilio
	 */
	public String getCodPostalDomicilio() {
		return codPostalDomicilio;
	}
	
	/**
	 * Establece el cod postal domicilio.
	 *
	 * @param pCodPostalDomicilio el nuevo cod postal domicilio
	 */
	public void setCodPostalDomicilio(String pCodPostalDomicilio) {
		this.codPostalDomicilio = pCodPostalDomicilio;
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
	 * @param pMunicipioDomicilio el nuevo municipio domicilio
	 */
	public void setMunicipioDomicilio(String pMunicipioDomicilio) {
		this.municipioDomicilio = pMunicipioDomicilio;
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
	 * @param pProvinciaDomicilio el nuevo provincia domicilio
	 */
	public void setProvinciaDomicilio(String pProvinciaDomicilio) {
		this.provinciaDomicilio = pProvinciaDomicilio;
	}
	
	/**
	 * Obtiene el nacion domicilio.
	 *
	 * @return el nacion domicilio
	 */
	public String getNacionDomicilio() {
		return nacionDomicilio;
	}
	
	/**
	 * Establece el nacion domicilio.
	 *
	 * @param pNacionDomicilio el nuevo nacion domicilio
	 */
	public void setNacionDomicilio(String pNacionDomicilio) {
		this.nacionDomicilio = pNacionDomicilio;
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
	 * @param pTelefono el nuevo telefono
	 */
	public void setTelefono(String pTelefono) {
		this.telefono = pTelefono;
	}
	
	/**
	 * Obtiene el cuerpo.
	 *
	 * @return el cuerpo
	 */
	public String getCuerpo() {
		return cuerpo;
	}
	
	/**
	 * Establece el cuerpo.
	 *
	 * @param pCuerpo el nuevo cuerpo
	 */
	public void setCuerpo(String pCuerpo) {
		this.cuerpo = pCuerpo;
	}
	
	/**
	 * Obtiene el especialidad.
	 *
	 * @return el especialidad
	 */
	public String getEspecialidad() {
		return especialidad;
	}
	
	/**
	 * Establece el especialidad.
	 *
	 * @param pEspecialidad el nuevo especialidad
	 */
	public void setEspecialidad(String pEspecialidad) {
		this.especialidad = pEspecialidad;
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
	 * @param pFormaAcceso el nuevo forma acceso
	 */
	public void setFormaAcceso(String pFormaAcceso) {
		this.formaAcceso = pFormaAcceso;
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
	 * @param pMinisterio el nuevo ministerio
	 */
	public void setMinisterio(String pMinisterio) {
		this.ministerio = pMinisterio;
	}
	
	/**
	 * Obtiene el fecha BOE.
	 *
	 * @return el fecha BOE
	 */
	public String getFechaBOE() {
		return fechaBOE;
	}
	
	/**
	 * Establece el fecha BOE.
	 *
	 * @param pFechaBOE el nuevo fecha BOE
	 */
	public void setFechaBOE(String pFechaBOE) {
		this.fechaBOE = pFechaBOE;
	}
	
	/**
	 * Obtiene el provincia examen.
	 *
	 * @return el provincia examen
	 */
	public String getProvinciaExamen() {
		return provinciaExamen;
	}
	
	/**
	 * Establece el provincia examen.
	 *
	 * @param pProvinciaExamen el nuevo provincia examen
	 */
	public void setProvinciaExamen(String pProvinciaExamen) {
		this.provinciaExamen = pProvinciaExamen;
	}
	
	/**
	 * Obtiene el reserva discapacitados.
	 *
	 * @return el reserva discapacitados
	 */
	public char getReservaDiscapacitados() {
		return reservaDiscapacitados;
	}
	
	/**
	 * Establece el reserva discapacitados.
	 *
	 * @param pReservaDiscapacitados el nuevo reserva discapacitados
	 */
	public void setReservaDiscapacitados(char pReservaDiscapacitados) {
		this.reservaDiscapacitados = pReservaDiscapacitados;
	}
	
	/**
	 * Obtiene el titulos.
	 *
	 * @return el titulos
	 */
	public String getTitulos() {
		return titulos;
	}
	
	/**
	 * Establece el titulos.
	 *
	 * @param pTitulos el nuevo titulos
	 */
	public void setTitulos(String pTitulos) {
		this.titulos = pTitulos;
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
	 * @param pOtrosTitulos el nuevo otros titulos
	 */
	public void setOtrosTitulos(String pOtrosTitulos) {
		this.otrosTitulos = pOtrosTitulos;
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
	 * @param pDatosA el nuevo datos A
	 */
	public void setDatosA(String pDatosA) {
		this.datosA = pDatosA;
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
	 * @param pDatosB el nuevo datos B
	 */
	public void setDatosB(String pDatosB) {
		this.datosB = pDatosB;
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
	 * @param pDatosC el nuevo datos C
	 */
	public void setDatosC(String pDatosC) {
		this.datosC = pDatosC;
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
	 * Obtiene el discapacidad.
	 *
	 * @return el discapacidad
	 */
	public String getDiscapacidad() {
		return discapacidad;
	}
	
	/**
	 * Establece el discapacidad.
	 *
	 * @param pDiscapacidad el nuevo discapacidad
	 */
	public void setDiscapacidad(String pDiscapacidad) {
		this.discapacidad = pDiscapacidad;
	}
	
	/**
	 * Obtiene el adaptacion discapacidad.
	 *
	 * @return el adaptacion discapacidad
	 */
	public Long getAdaptacionDiscapacidad() {
		return adaptacionDiscapacidad;
	}
	
	/**
	 * Establece el adaptacion discapacidad.
	 *
	 * @param pAdaptacionDiscapacidad el nuevo adaptacion discapacidad
	 */
	public void setAdaptacionDiscapacidad(Long pAdaptacionDiscapacidad) {
		this.adaptacionDiscapacidad = pAdaptacionDiscapacidad;
	}
	
	/**
	 * Obtiene el motivo discapacidad.
	 *
	 * @return el motivo discapacidad
	 */
	public String getMotivoDiscapacidad() {
		return motivoDiscapacidad;
	}
	
	/**
	 * Establece el motivo discapacidad.
	 *
	 * @param pMotivoDiscapacidad el nuevo motivo discapacidad
	 */
	public void setMotivoDiscapacidad(String pMotivoDiscapacidad) {
		this.motivoDiscapacidad = pMotivoDiscapacidad;
	}
	
	/**
	 * Obtiene el id estado solicitud.
	 *
	 * @return el id estado solicitud
	 */
	public Integer getIdEstadoSolicitud() {
		return idEstadoSolicitud;
	}
	
	/**
	 * Establece el id estado solicitud.
	 *
	 * @param idEstadoSolicitud el nuevo id estado solicitud
	 */
	public void setIdEstadoSolicitud(Integer idEstadoSolicitud) {
		this.idEstadoSolicitud = idEstadoSolicitud;
	}
	
	/**
	 * Obtiene el consentimiento.
	 *
	 * @return el consentimiento
	 */
	public String getConsentimiento() {
		return consentimiento;
	}
	
	/**
	 * Establece el consentimiento.
	 *
	 * @param consentimiento el nuevo consentimiento
	 */
	public void setConsentimiento(String consentimiento) {
		this.consentimiento = consentimiento;
	}
	
	/**
	 * Obtiene el solicitud propios bean.
	 *
	 * @return el solicitud propios bean
	 */
	public List<SolicitudPropiosBean> getSolicitudPropiosBean() {
		return solicitudPropiosBean;
	}
	
	/**
	 * Establece el solicitud propios bean.
	 *
	 * @param solicitudPropiosBean el nuevo solicitud propios bean
	 */
	public void setSolicitudPropiosBean(List<SolicitudPropiosBean> solicitudPropiosBean) {
		this.solicitudPropiosBean = solicitudPropiosBean;
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
	
	/**
	 * Obtiene el id motivo reduccion tasa.
	 *
	 * @return el id motivo reduccion tasa
	 */
	public Integer getIdMotivoReduccionTasa() {
		return idMotivoReduccionTasa;
	}
	
	/**
	 * Establece el id motivo reduccion tasa.
	 *
	 * @param idMotivoReduccion el nuevo id motivo reduccion tasa
	 */
	public void setIdMotivoReduccionTasa(Integer idMotivoReduccion) {
		this.idMotivoReduccionTasa = idMotivoReduccion;
	}
	
	/**
	 * Obtiene el id comunidad FN.
	 *
	 * @return el id comunidad FN
	 */
	public Integer getIdComunidadFN() {
		return idComunidadFN;
	}
	
	/**
	 * Establece el id comunidad FN.
	 *
	 * @param idComunidadFN el nuevo id comunidad FN
	 */
	public void setIdComunidadFN(Integer idComunidadFN) {
		this.idComunidadFN = idComunidadFN;
	}
	
	/**
	 * Obtiene el id comunidad DD.
	 *
	 * @return el id comunidad DD
	 */
	public Integer getIdComunidadDD() {
		return idComunidadDD;
	}
	
	/**
	 * Establece el id comunidad DD.
	 *
	 * @param idComunidadDD el nuevo id comunidad DD
	 */
	public void setIdComunidadDD(Integer idComunidadDD) {
		this.idComunidadDD = idComunidadDD;
	}
	
	/**
	 * Obtiene el titulo familia numerosa.
	 *
	 * @return el titulo familia numerosa
	 */
	public String getTituloFamiliaNumerosa() {
		return tituloFamiliaNumerosa;
	}
	
	/**
	 * Establece el titulo familia numerosa.
	 *
	 * @param tituloFamiliaNumerosa el nuevo titulo familia numerosa
	 */
	public void setTituloFamiliaNumerosa(String tituloFamiliaNumerosa) {
		this.tituloFamiliaNumerosa = tituloFamiliaNumerosa;
	}

	/**
	 * Establece el motivoOposicion.
	 *
	 * @param motivoOposicion
	 */
	public String getMotivoOposicion() {
		return motivoOposicion;
	}

	public void setMotivoOposicion(String motivoOposicion) {
		this.motivoOposicion = motivoOposicion;
	}
}
