package es.map.ipsc.bean;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;

import es.map.ips.model.CentroGestor;
import es.map.ips.model.Convocatoria;


/**
 * El Class PlantillaBean.
 */
public class PlantillaBean {

	/** el id. */
	private Long id;
	
	/** el nif. */
	private Character nif;
	
	/** el primer apellido. */
	private Character primerApellido;
	
	/** el segundo apellido. */
	private Character segundoApellido;
	
	/** el nombre. */
	private Character nombre;
	
	/** el fecha nacimiento. */
	private Character fechaNacimiento;
	
	/** el sexo. */
	private Character sexo;
	
	/** el provincia nacimiento. */
	private Character provinciaNacimiento;
	
	/** el localidad nacimiento. */
	private Character localidadNacimiento;
	
	/** el nacionalidad. */
	private Character nacionalidad;
	
	/** el telefono. */
	private Character telefono;
	
	/** el correo electronico. */
	private Character correoElectronico;
	
	/** el via. */
	private Character via;
	
	/** el codigo postal. */
	private Character codigoPostal;
	
	/** el municipio. */
	private Character municipio;
	
	/** el provincia. */
	private Character provincia;
	
	/** el pais. */
	private Character pais;
	
	/** el cuerpo. */
	private Character cuerpo;
	
	/** el especialidad. */
	private Character especialidad;
	
	/** el formaacceso. */
	private Character formaacceso;
	
	/** el entidad convocante. */
	private Character entidadConvocante;
	
	/** el fecha boe. */
	private Character fechaBoe;
	
	/** el provincia examen. */
	private Character provinciaExamen;
	
	/** el tipo discapacidad. */
	private Character tipoDiscapacidad;
	
	/** el porcentaje discapacidad. */
	private Character porcentajeDiscapacidad;
	
	/** el reserva discapacidad. */
	private Character reservaDiscapacidad;
	
	/** el detalle discapacidad. */
	private Character detalleDiscapacidad;
	
	/** el titulos exigidos. */
	private Character titulosExigidos;
	
	/** el otros titulos. */
	private Character otrosTitulos;
	
	/** el datos A. */
	private Character datosA;
	
	/** el datos B. */
	private Character datosB;
	
	/** el datos C. */
	private Character datosC;
	
	/** el tipo plantilla. */
	private Character tipoPlantilla;
    
    /** el codigo ministerio. */
    private Character codigoMinisterio;
    
    /** el codigo cuerpo escala. */
    private Character codigoCuerpoEscala;
    
    /** el codigo especialidad. */
    private Character codigoEspecialidad;
    
    /** el codigo titulo oficial. */
    private Character codigoTituloOficial;
    
    /** el codigo pais domicilio. */
    private Character codigoPaisDomicilio;
    
    /** el codigo provincia domicilio. */
    private Character codigoProvinciaDomicilio;
    
    /** el codigo provincia examen. */
    private Character codigoProvinciaExamen;
	
	/** el centro gestors. */
	private Set<CentroGestor> centroGestors = new HashSet<CentroGestor>(0);
	
	/** el convocatorias. */
	private Set<Convocatoria> convocatorias = new HashSet<Convocatoria>(0);
	
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
	 * Obtiene el nif.
	 *
	 * @return el nif
	 */
	public Character getNif() {
		return this.nif;
	}

	/**
	 * Establece el nif.
	 *
	 * @param nif el nuevo nif
	 */
	public void setNif(char nif) {
		this.nif = nif;
	}

	/**
	 * Obtiene el primer apellido.
	 *
	 * @return el primer apellido
	 */
	public Character getPrimerApellido() {
		return this.primerApellido;
	}

	/**
	 * Establece el primer apellido.
	 *
	 * @param primerApellido el nuevo primer apellido
	 */
	public void setPrimerApellido(char primerApellido) {
		this.primerApellido = primerApellido;
	}

	/**
	 * Obtiene el segundo apellido.
	 *
	 * @return el segundo apellido
	 */
	public Character getSegundoApellido() {
		return this.segundoApellido;
	}

	/**
	 * Establece el segundo apellido.
	 *
	 * @param segundoApellido el nuevo segundo apellido
	 */
	public void setSegundoApellido(char segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	/**
	 * Obtiene el nombre.
	 *
	 * @return el nombre
	 */
	public Character getNombre() {
		return this.nombre;
	}

	/**
	 * Establece el nombre.
	 *
	 * @param nombre el nuevo nombre
	 */
	public void setNombre(char nombre) {
		this.nombre = nombre;
	}

	/**
	 * Obtiene el fecha nacimiento.
	 *
	 * @return el fecha nacimiento
	 */
	public Character getFechaNacimiento() {
		return this.fechaNacimiento;
	}

	/**
	 * Establece el fecha nacimiento.
	 *
	 * @param fechaNacimiento el nuevo fecha nacimiento
	 */
	public void setFechaNacimiento(char fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	/**
	 * Obtiene el sexo.
	 *
	 * @return el sexo
	 */
	public Character getSexo() {
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
	 * Obtiene el provincia nacimiento.
	 *
	 * @return el provincia nacimiento
	 */
	public Character getProvinciaNacimiento() {
		return this.provinciaNacimiento;
	}

	/**
	 * Establece el provincia nacimiento.
	 *
	 * @param provinciaNacimiento el nuevo provincia nacimiento
	 */
	public void setProvinciaNacimiento(char provinciaNacimiento) {
		this.provinciaNacimiento = provinciaNacimiento;
	}

	/**
	 * Obtiene el localidad nacimiento.
	 *
	 * @return el localidad nacimiento
	 */
	public Character getLocalidadNacimiento() {
		return this.localidadNacimiento;
	}

	/**
	 * Establece el localidad nacimiento.
	 *
	 * @param localidadNacimiento el nuevo localidad nacimiento
	 */
	public void setLocalidadNacimiento(char localidadNacimiento) {
		this.localidadNacimiento = localidadNacimiento;
	}

	/**
	 * Obtiene el nacionalidad.
	 *
	 * @return el nacionalidad
	 */
	public Character getNacionalidad() {
		return this.nacionalidad;
	}

	/**
	 * Establece el nacionalidad.
	 *
	 * @param nacionalidad el nuevo nacionalidad
	 */
	public void setNacionalidad(char nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	/**
	 * Obtiene el telefono.
	 *
	 * @return el telefono
	 */
	public Character getTelefono() {
		return this.telefono;
	}

	/**
	 * Establece el telefono.
	 *
	 * @param telefono el nuevo telefono
	 */
	public void setTelefono(char telefono) {
		this.telefono = telefono;
	}

	/**
	 * Obtiene el correo electronico.
	 *
	 * @return el correo electronico
	 */
	public Character getCorreoElectronico() {
		return this.correoElectronico;
	}

	/**
	 * Establece el correo electronico.
	 *
	 * @param correoElectronico el nuevo correo electronico
	 */
	public void setCorreoElectronico(char correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	/**
	 * Obtiene el via.
	 *
	 * @return el via
	 */
	public Character getVia() {
		return this.via;
	}

	/**
	 * Establece el via.
	 *
	 * @param via el nuevo via
	 */
	public void setVia(char via) {
		this.via = via;
	}

	/**
	 * Obtiene el codigo postal.
	 *
	 * @return el codigo postal
	 */
	public Character getCodigoPostal() {
		return this.codigoPostal;
	}

	/**
	 * Establece el codigo postal.
	 *
	 * @param codigoPostal el nuevo codigo postal
	 */
	public void setCodigoPostal(char codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	/**
	 * Obtiene el municipio.
	 *
	 * @return el municipio
	 */
	public Character getMunicipio() {
		return this.municipio;
	}

	/**
	 * Establece el municipio.
	 *
	 * @param municipio el nuevo municipio
	 */
	public void setMunicipio(char municipio) {
		this.municipio = municipio;
	}

	/**
	 * Obtiene el provincia.
	 *
	 * @return el provincia
	 */
	public Character getProvincia() {
		return this.provincia;
	}

	/**
	 * Establece el provincia.
	 *
	 * @param provincia el nuevo provincia
	 */
	public void setProvincia(char provincia) {
		this.provincia = provincia;
	}

	/**
	 * Obtiene el pais.
	 *
	 * @return el pais
	 */
	public Character getPais() {
		return this.pais;
	}

	/**
	 * Establece el pais.
	 *
	 * @param pais el nuevo pais
	 */
	public void setPais(char pais) {
		this.pais = pais;
	}

	/**
	 * Obtiene el cuerpo.
	 *
	 * @return el cuerpo
	 */
	public Character getCuerpo() {
		return this.cuerpo;
	}

	/**
	 * Establece el cuerpo.
	 *
	 * @param cuerpo el nuevo cuerpo
	 */
	public void setCuerpo(char cuerpo) {
		this.cuerpo = cuerpo;
	}

	/**
	 * Obtiene el especialidad.
	 *
	 * @return el especialidad
	 */
	public Character getEspecialidad() {
		return this.especialidad;
	}

	/**
	 * Establece el especialidad.
	 *
	 * @param especialidad el nuevo especialidad
	 */
	public void setEspecialidad(char especialidad) {
		this.especialidad = especialidad;
	}

	/**
	 * Obtiene el formaacceso.
	 *
	 * @return el formaacceso
	 */
	public Character getFormaacceso() {
		return this.formaacceso;
	}

	/**
	 * Establece el formaacceso.
	 *
	 * @param formaacceso el nuevo formaacceso
	 */
	public void setFormaacceso(char formaacceso) {
		this.formaacceso = formaacceso;
	}

	/**
	 * Obtiene el entidad convocante.
	 *
	 * @return el entidad convocante
	 */
	public Character getEntidadConvocante() {
		return this.entidadConvocante;
	}

	/**
	 * Establece el entidad convocante.
	 *
	 * @param entidadConvocante el nuevo entidad convocante
	 */
	public void setEntidadConvocante(char entidadConvocante) {
		this.entidadConvocante = entidadConvocante;
	}

	/**
	 * Obtiene el fecha boe.
	 *
	 * @return el fecha boe
	 */
	public Character getFechaBoe() {
		return this.fechaBoe;
	}

	/**
	 * Establece el fecha boe.
	 *
	 * @param fechaBoe el nuevo fecha boe
	 */
	public void setFechaBoe(char fechaBoe) {
		this.fechaBoe = fechaBoe;
	}

	/**
	 * Obtiene el provincia examen.
	 *
	 * @return el provincia examen
	 */
	public Character getProvinciaExamen() {
		return this.provinciaExamen;
	}

	/**
	 * Establece el provincia examen.
	 *
	 * @param provinciaExamen el nuevo provincia examen
	 */
	public void setProvinciaExamen(char provinciaExamen) {
		this.provinciaExamen = provinciaExamen;
	}

	/**
	 * Obtiene el tipo discapacidad.
	 *
	 * @return el tipo discapacidad
	 */
	public Character getTipoDiscapacidad() {
		return this.tipoDiscapacidad;
	}

	/**
	 * Establece el tipo discapacidad.
	 *
	 * @param tipoDiscapacidad el nuevo tipo discapacidad
	 */
	public void setTipoDiscapacidad(char tipoDiscapacidad) {
		this.tipoDiscapacidad = tipoDiscapacidad;
	}

	/**
	 * Obtiene el porcentaje discapacidad.
	 *
	 * @return el porcentaje discapacidad
	 */
	public Character getPorcentajeDiscapacidad() {
		return this.porcentajeDiscapacidad;
	}

	/**
	 * Establece el porcentaje discapacidad.
	 *
	 * @param porcentajeDiscapacidad el nuevo porcentaje discapacidad
	 */
	public void setPorcentajeDiscapacidad(char porcentajeDiscapacidad) {
		this.porcentajeDiscapacidad = porcentajeDiscapacidad;
	}

	/**
	 * Obtiene el reserva discapacidad.
	 *
	 * @return el reserva discapacidad
	 */
	public Character getReservaDiscapacidad() {
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
	public Character getDetalleDiscapacidad() {
		return this.detalleDiscapacidad;
	}

	/**
	 * Establece el detalle discapacidad.
	 *
	 * @param detalleDiscapacidad el nuevo detalle discapacidad
	 */
	public void setDetalleDiscapacidad(char detalleDiscapacidad) {
		this.detalleDiscapacidad = detalleDiscapacidad;
	}

	/**
	 * Obtiene el titulos exigidos.
	 *
	 * @return el titulos exigidos
	 */
	public Character getTitulosExigidos() {
		return this.titulosExigidos;
	}

	/**
	 * Establece el titulos exigidos.
	 *
	 * @param titulosExigidos el nuevo titulos exigidos
	 */
	public void setTitulosExigidos(char titulosExigidos) {
		this.titulosExigidos = titulosExigidos;
	}

	/**
	 * Obtiene el otros titulos.
	 *
	 * @return el otros titulos
	 */
	public Character getOtrosTitulos() {
		return this.otrosTitulos;
	}

	/**
	 * Establece el otros titulos.
	 *
	 * @param otrosTitulos el nuevo otros titulos
	 */
	public void setOtrosTitulos(char otrosTitulos) {
		this.otrosTitulos = otrosTitulos;
	}

	/**
	 * Obtiene el datos A.
	 *
	 * @return el datos A
	 */
	public Character getDatosA() {
		return this.datosA;
	}

	/**
	 * Establece el datos A.
	 *
	 * @param datosA el nuevo datos A
	 */
	public void setDatosA(char datosA) {
		this.datosA = datosA;
	}

	/**
	 * Obtiene el datos B.
	 *
	 * @return el datos B
	 */
	public Character getDatosB() {
		return this.datosB;
	}

	/**
	 * Establece el datos B.
	 *
	 * @param datosB el nuevo datos B
	 */
	public void setDatosB(char datosB) {
		this.datosB = datosB;
	}

	/**
	 * Obtiene el datos C.
	 *
	 * @return el datos C
	 */
	public Character getDatosC() {
		return this.datosC;
	}

	/**
	 * Establece el datos C.
	 *
	 * @param datosC el nuevo datos C
	 */
	public void setDatosC(char datosC) {
		this.datosC = datosC;
	}

	/**
	 * Obtiene el tipo plantilla.
	 *
	 * @return el tipo plantilla
	 */
	@Column(name = "TIPO_PLANTILLA", nullable = false, length = 1)
	public Character getTipoPlantilla() {
		return this.tipoPlantilla;
	}

	/**
	 * Establece el tipo plantilla.
	 *
	 * @param tipoPlantilla el nuevo tipo plantilla
	 */
	public void setTipoPlantilla(char tipoPlantilla) {
		this.tipoPlantilla = tipoPlantilla;
	}

	/**
	 * Obtiene el centro gestors.
	 *
	 * @return el centro gestors
	 */
	public Set<CentroGestor> getCentroGestors() {
		return this.centroGestors;
	}

	/**
	 * Establece el centro gestors.
	 *
	 * @param centroGestors el nuevo centro gestors
	 */
	public void setCentroGestors(Set<CentroGestor> centroGestors) {
		this.centroGestors = centroGestors;
	}

	/**
	 * Obtiene el convocatorias.
	 *
	 * @return el convocatorias
	 */
	public Set<Convocatoria> getConvocatorias() {
		return this.convocatorias;
	}

	/**
	 * Establece el convocatorias.
	 *
	 * @param convocatorias el nuevo convocatorias
	 */
	public void setConvocatorias(Set<Convocatoria> convocatorias) {
		this.convocatorias = convocatorias;
	}

	/**
	 * Obtiene el codigo ministerio.
	 *
	 * @return el codigo ministerio
	 */
	public Character getCodigoMinisterio() {
		return codigoMinisterio;
	}

	/**
	 * Establece el codigo ministerio.
	 *
	 * @param codigoMinisterio el nuevo codigo ministerio
	 */
	public void setCodigoMinisterio(Character codigoMinisterio) {
		this.codigoMinisterio = codigoMinisterio;
	}

	/**
	 * Obtiene el codigo cuerpo escala.
	 *
	 * @return el codigo cuerpo escala
	 */
	public Character getCodigoCuerpoEscala() {
		return codigoCuerpoEscala;
	}

	/**
	 * Establece el codigo cuerpo escala.
	 *
	 * @param codigoCuerpoEscala el nuevo codigo cuerpo escala
	 */
	public void setCodigoCuerpoEscala(Character codigoCuerpoEscala) {
		this.codigoCuerpoEscala = codigoCuerpoEscala;
	}

	/**
	 * Obtiene el codigo especialidad.
	 *
	 * @return el codigo especialidad
	 */
	public Character getCodigoEspecialidad() {
		return codigoEspecialidad;
	}

	/**
	 * Establece el codigo especialidad.
	 *
	 * @param codigoEspecialidad el nuevo codigo especialidad
	 */
	public void setCodigoEspecialidad(Character codigoEspecialidad) {
		this.codigoEspecialidad = codigoEspecialidad;
	}

	/**
	 * Obtiene el codigo titulo oficial.
	 *
	 * @return el codigo titulo oficial
	 */
	public Character getCodigoTituloOficial() {
		return codigoTituloOficial;
	}

	/**
	 * Establece el codigo titulo oficial.
	 *
	 * @param codigoTituloOficial el nuevo codigo titulo oficial
	 */
	public void setCodigoTituloOficial(Character codigoTituloOficial) {
		this.codigoTituloOficial = codigoTituloOficial;
	}

	/**
	 * Obtiene el codigo pais domicilio.
	 *
	 * @return el codigo pais domicilio
	 */
	public Character getCodigoPaisDomicilio() {
		return codigoPaisDomicilio;
	}

	/**
	 * Establece el codigo pais domicilio.
	 *
	 * @param codigoPaisDomicilio el nuevo codigo pais domicilio
	 */
	public void setCodigoPaisDomicilio(Character codigoPaisDomicilio) {
		this.codigoPaisDomicilio = codigoPaisDomicilio;
	}

	/**
	 * Obtiene el codigo provincia domicilio.
	 *
	 * @return el codigo provincia domicilio
	 */
	public Character getCodigoProvinciaDomicilio() {
		return codigoProvinciaDomicilio;
	}

	/**
	 * Establece el codigo provincia domicilio.
	 *
	 * @param codigoProvinciaDomicilio el nuevo codigo provincia domicilio
	 */
	public void setCodigoProvinciaDomicilio(Character codigoProvinciaDomicilio) {
		this.codigoProvinciaDomicilio = codigoProvinciaDomicilio;
	}

	/**
	 * Obtiene el codigo provincia examen.
	 *
	 * @return el codigo provincia examen
	 */
	public Character getCodigoProvinciaExamen() {
		return codigoProvinciaExamen;
	}

	/**
	 * Establece el codigo provincia examen.
	 *
	 * @param codigoProvinciaExamen el nuevo codigo provincia examen
	 */
	public void setCodigoProvinciaExamen(Character codigoProvinciaExamen) {
		this.codigoProvinciaExamen = codigoProvinciaExamen;
	}

}
