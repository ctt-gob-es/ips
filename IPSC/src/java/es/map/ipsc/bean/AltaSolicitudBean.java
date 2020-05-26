package es.map.ipsc.bean;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import es.map.ips.model.Especialidad;
import es.map.ips.model.ProvinciaExamen;
import es.map.ips.model.TipoDiscapacidad;
import es.map.ips.model.TituloOficial;

/**
 * El Class AltaSolicitudBean.
 */
public class AltaSolicitudBean {
	
	/** el id convocatoria. */
	private String idConvocatoria;
	
	/** el des centro gestor. */
	//Convocatoria
	private String desCentroGestor;
	
	/** el num solicitud. */
	private String numSolicitud;
	
	/** el ejercicio. */
	private String ejercicio;
	
	/** el provincia examen. */
	private String provinciaExamen;
	
	/** el nif. */
	//Personales
	private String nif;
	
	/** el nombre. */
	private String nombre;
	
	/** el primer apellido. */
	private String primerApellido;
	
	/** el segundo apellido. */
	private String segundoApellido;
	
	/** el email. */
	private String email;
	
	/** el fecha nacimiento. */
	private String fechaNacimiento;
	
	/** el nacionalidad. */
	private String nacionalidad;
	
	/** el sexo. */
	private String sexo;
	
	/** el calle domicilio. */
	//Direccion
	private String calleDomicilio;
	
	/** el codigo postal domicilio. */
	private String codigoPostalDomicilio;
	
	/** el municipio domicilio. */
	private String municipioDomicilio;
	
	/** el provincia domicilio. */
	private String provinciaDomicilio;
	
	/** el pais domicilio. */
	private String paisDomicilio;
	
	/** el telefono. */
	private String telefono;
	
	/** el telefono aux. */
	private String telefonoAux;
	
	/** el des cuerpo escala. */
	//Convocatoria
	private String desCuerpoEscala;
	
	/** el especialidades. */
	private List<Especialidad> especialidades = new ArrayList<Especialidad>(0);
	
	/** el especialidad 79007. */
	private String especialidad79007;
	
	/** el especialidad. */
	private String especialidad;
	
	/** el des forma acceso. */
	private String desFormaAcceso;
	
	/** el id forma acceso. */
	private String idFormaAcceso;
	
	/** el des ministerio. */
	private String desMinisterio;
	
	/** el fecha BOE. */
	private String fechaBOE;
	
	/** el provincias examen. */
	private List<ProvinciaExamen> provinciasExamen = new ArrayList<ProvinciaExamen>(0);
	
	/** el prov examen unica. */
	private Integer provExamenUnica = 0;
	
	/** el provexamen 79007. */
	private String provexamen79007;
	
	/** el importe. */
	private String importe;
	
	/** el importe original. */
	private String importeOriginal;
	
	/** el importe pagado. */
	private String importePagado;


	
	/** el tipos discapacidad. */
	private Set<TipoDiscapacidad> tiposDiscapacidad = new HashSet<TipoDiscapacidad>(0);
	
	/** el porcentaje discapacidad. */
	private String porcentajeDiscapacidad;
	
	/** el reserva discapacidad. */
	private String reservaDiscapacidad;
	
	/** el detalle discapacidad. */
	private String detalleDiscapacidad;
	
	/** el titulos oficiales. */
	//Titulos
	private List<TituloOficial> titulosOficiales = new ArrayList<TituloOficial>(0);
	
	/** el otros titulos. */
	private String otrosTitulos;
	
	/** el datos A. */
	//Bases
	private String datosA;
	
	/** el datos B. */
	private String datosB;
	
	/** el datos C. */
	private String datosC;
	
	/** el modelo 79007. */
	private Integer modelo79007=0;
	
	/** el sec judiciales. */
	private Integer secJudiciales=0;
	
	/** el plantilla propios. */
	private List<PlantillaPropiosBean> plantillaPropios;
	
	/** el id comunidad DD exento. */
	private String idComunidadDDExento;
	
	/** el tipo discapacidad. */
	private String tipoDiscapacidad;
	
	/** el titulo. */
	private String titulo;
	
	/** el id motivo reduccion. */
	private String idMotivoReduccion;
	
	/** el lista campos propios. */
	ArrayList<CamposPropiosBean> listaCamposPropios;
	
	/** el id comunidad FN exento. */
	private String idComunidadFNExento;
	
	/** el titulo fanumerosa. */
	private String tituloFanumerosa;
	
	/** el pago inicial modif. */
	// Campo que indica si el pago se puede modificar
	private String pagoInicialModif;
	
	/** el id solicitud inicial. */
	private String idSolicitudInicial;
	
	/** el id consentimiento. */
	private Boolean idConsentimiento;
	
	/** motivo oposicion */
	private String motivoOposicion;
	
	/** el idConsentimientoAEAT.*/
	private Boolean idConsentimientoAEAT;
	
	/** enlace convocatoria **/
	private String enlace;
	
	/**
	 * Obtiene el importe original.
	 *
	 * @return el importe original
	 */
	public String getImporteOriginal() {
		return importeOriginal;
	}
	
	/**
	 * Establece el importe original.
	 *
	 * @param importeOriginal el nuevo importe original
	 */
	public void setImporteOriginal(String importeOriginal) {
		this.importeOriginal = importeOriginal;
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
	 * @param fechaBOE el nuevo fecha BOE
	 */
	public void setFechaBOE(String fechaBOE) {
		this.fechaBOE = fechaBOE;
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
	 * Obtiene el tipos discapacidad.
	 *
	 * @return el tipos discapacidad
	 */
	public Set<TipoDiscapacidad> getTiposDiscapacidad() {
		return tiposDiscapacidad;
	}
	
	/**
	 * Establece el tipos discapacidad.
	 *
	 * @param tiposDiscapacidad el nuevo tipos discapacidad
	 */
	public void setTiposDiscapacidad(Set<TipoDiscapacidad> tiposDiscapacidad) {
		this.tiposDiscapacidad = tiposDiscapacidad;
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
	 * Establece el especialidades.
	 *
	 * @param especialidades el nuevo especialidades
	 */
	public void setEspecialidades(List<Especialidad> especialidades) {
		this.especialidades = especialidades;
	}
	
	/**
	 * Establece el provincias examen.
	 *
	 * @param provinciasExamen el nuevo provincias examen
	 */
	public void setProvinciasExamen(List<ProvinciaExamen> provinciasExamen) {
		this.provinciasExamen = provinciasExamen;
	}
	
	/**
	 * Establece el titulos oficiales.
	 *
	 * @param titulosOficiales el nuevo titulos oficiales
	 */
	public void setTitulosOficiales(List<TituloOficial> titulosOficiales) {
		this.titulosOficiales = titulosOficiales;
	}
	
	/**
	 * Obtiene el especialidades.
	 *
	 * @return el especialidades
	 */
	public List<Especialidad> getEspecialidades() {
		return especialidades;
	}
	
	/**
	 * Obtiene el provincias examen.
	 *
	 * @return el provincias examen
	 */
	public List<ProvinciaExamen> getProvinciasExamen() {
		return provinciasExamen;
	}
	
	/**
	 * Obtiene el titulos oficiales.
	 *
	 * @return el titulos oficiales
	 */
	public List<TituloOficial> getTitulosOficiales() {
		return titulosOficiales;
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
	 * Obtiene el especialidad 79007.
	 *
	 * @return el especialidad 79007
	 */
	public String getEspecialidad79007() {
		return especialidad79007;
	}
	
	/**
	 * Establece el especialidad 79007.
	 *
	 * @param especialidad79007 el nuevo especialidad 79007
	 */
	public void setEspecialidad79007(String especialidad79007) {
		this.especialidad79007 = especialidad79007;
	}
	
	/**
	 * Obtiene el provexamen 79007.
	 *
	 * @return el provexamen 79007
	 */
	public String getProvexamen79007() {
		return provexamen79007;
	}
	
	/**
	 * Establece el provexamen 79007.
	 *
	 * @param provexamen79007 el nuevo provexamen 79007
	 */
	public void setProvexamen79007(String provexamen79007) {
		this.provexamen79007 = provexamen79007;
	}
	
	/**
	 * Obtiene el modelo 79007.
	 *
	 * @return el modelo 79007
	 */
	public Integer getModelo79007() {
		return modelo79007;
	}
	
	/**
	 * Establece el modelo 79007.
	 *
	 * @param modelo79007 el nuevo modelo 79007
	 */
	public void setModelo79007(Integer modelo79007) {
		this.modelo79007 = modelo79007;
	}
	
	/**
	 * Obtiene el sec judiciales.
	 *
	 * @return el sec judiciales
	 */
	public Integer getSecJudiciales() {
		return secJudiciales;
	}
	
	/**
	 * Establece el sec judiciales.
	 *
	 * @param secJudiciales el nuevo sec judiciales
	 */
	public void setSecJudiciales(Integer secJudiciales) {
		this.secJudiciales = secJudiciales;
	}
	
	/**
	 * Obtiene el prov examen unica.
	 *
	 * @return el prov examen unica
	 */
	public Integer getProvExamenUnica() {
		return provExamenUnica;
	}
	
	/**
	 * Establece el prov examen unica.
	 *
	 * @param provExamenUnica el nuevo prov examen unica
	 */
	public void setProvExamenUnica(Integer provExamenUnica) {
		this.provExamenUnica = provExamenUnica;
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
	 * @param especialidad el nuevo especialidad
	 */
	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
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
	 * @param provinciaExamen el nuevo provincia examen
	 */
	public void setProvinciaExamen(String provinciaExamen) {
		this.provinciaExamen = provinciaExamen;
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
	 * Obtiene el titulo.
	 *
	 * @return el titulo
	 */
	public String getTitulo() {
		return titulo;
	}
	
	/**
	 * Establece el titulo.
	 *
	 * @param titulo el nuevo titulo
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	/**
	 * Obtiene el id motivo reduccion.
	 *
	 * @return el id motivo reduccion
	 */
	public String getIdMotivoReduccion() {
		return idMotivoReduccion;
	}
	
	/**
	 * Establece el id motivo reduccion.
	 *
	 * @param idMotivoReduccion el nuevo id motivo reduccion
	 */
	public void setIdMotivoReduccion(String idMotivoReduccion) {
		this.idMotivoReduccion = idMotivoReduccion;
	}
	
	/**
	 * Obtiene el lista campos propios.
	 *
	 * @return el lista campos propios
	 */
	public ArrayList<CamposPropiosBean> getListaCamposPropios() {
		return listaCamposPropios;
	}
	
	/**
	 * Establece el lista campos propios.
	 *
	 * @param listaCamposPropios el nuevo lista campos propios
	 */
	public void setListaCamposPropios(ArrayList<CamposPropiosBean> listaCamposPropios) {
		this.listaCamposPropios = listaCamposPropios;
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
	 * Obtiene el importe pagado.
	 *
	 * @return el importe pagado
	 */
	public String getImportePagado() {
		return importePagado;
	}
	
	/**
	 * Establece el importe pagado.
	 *
	 * @param importePagado el nuevo importe pagado
	 */
	public void setImportePagado(String importePagado) {
		this.importePagado = importePagado;
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
	 * Obtiene el titulo fanumerosa.
	 *
	 * @return el titulo fanumerosa
	 */
	public String getTituloFanumerosa() {
		return tituloFanumerosa;
	}
	
	/**
	 * Establece el titulo fanumerosa.
	 *
	 * @param tituloFanumerosa el nuevo titulo fanumerosa
	 */
	public void setTituloFanumerosa(String tituloFanumerosa) {
		this.tituloFanumerosa = tituloFanumerosa;
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

	/**
	 * @return the enlace
	 */
	public String getEnlace() {
		return enlace;
	}

	/**
	 * @param enlace the enlace to set
	 */
	public void setEnlace(String enlace) {
		this.enlace = enlace;
	}
	
	
}
