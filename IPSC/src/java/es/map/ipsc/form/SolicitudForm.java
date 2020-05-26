package es.map.ipsc.form;


import java.util.List;

import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.Discapacidad;
import es.map.ips.model.OtrosTitulos;
import es.map.ipsc.bean.PlantillaBean;
import es.map.ipsc.bean.PlantillaPropiosBean;


/**
 * El Class SolicitudForm.
 */
public class SolicitudForm extends SpringForm {

	/** el id. */
	private Long id;
	
	/** el id convocatoria. */
	private String idConvocatoria;
	
	
	/** el nif. */
	private String nif;
	
	/** el nif ciudadano. */
	private String nifCiudadano;
	
	/** el num solicitud. */
	private String numSolicitud;
	
	/** el nombre. */
	private String nombre;
	
	/** el primer apellido. */
	private String primerApellido;
	
	/** el segundo apellido. */
	private String segundoApellido;
	
	/** el nif fun habilitado. */
	private String nifFunHabilitado;
	
	/** el nombre fun habilitado. */
	private String nombreFunHabilitado;
	
	/** el primer apellido fun habilitado. */
	private String primerApellidoFunHabilitado;
	
	/** el segundo apellido fun habilitado. */
	private String segundoApellidoFunHabilitado;
	
	/** el dia nacimiento. */
	private String diaNacimiento;
	
	/** el mes nacimiento. */
	private String mesNacimiento;
	
	/** el anio nacimiento. */
	private String anioNacimiento;
	
	/** el email. */
	private String email;
	
	/** el telefono. */
	private String telefono;
	
	/** el telefono aux. */
	private String telefonoAux;
	
	/** el telefono 1. */
	private String telefono1;
	
	/** el sexo. */
	private String sexo;	
	
	/** el provincia nacimiento. */
	private String provinciaNacimiento;
	
	/** el localidad nacimiento. */
	private String localidadNacimiento;
	
	/** el nacionalidad. */
	private String nacionalidad;
	
	/** el fecha nacimiento. */
	private String fechaNacimiento;
	
	/** el pais. */
	private String pais;
	
	/** el provincia domicilio. */
	private String provinciaDomicilio;	
	
	/** el calle domicilio. */
	private String calleDomicilio;
	
	/** el codigo postal domicilio. */
	private String codigoPostalDomicilio;
	
	/** el municipio domicilio. */
	private String municipioDomicilio;
	
	/** el especialidad. */
	private String especialidad;
	
	/** el provincia examen. */
	private String provinciaExamen;	
	
	/** el tipo discapacidad. */
	private String tipoDiscapacidad;
	
	/** el tipo discapacidad minis justicia. */
	private String tipoDiscapacidadMinisJusticia;

	/** el consentimiento. */
	private Boolean consentimiento;
	
	/** el des consentimiento. */
	private String desConsentimiento;

	/** motivo oposicion */
	private String motivoOposicion;
	
	/** el consentimiento. */
	private Boolean idConsentimientoAEAT;
	
	
	/**
	 * Obtiene el tipo discapacidad minis justicia.
	 *
	 * @return el tipo discapacidad minis justicia
	 */
	public String getTipoDiscapacidadMinisJusticia() {
		return tipoDiscapacidadMinisJusticia;
	}
	
	/**
	 * Establece el tipo discapacidad minis justicia.
	 *
	 * @param tipoDiscapacidadMinisJusticia el nuevo tipo discapacidad minis justicia
	 */
	public void setTipoDiscapacidadMinisJusticia(
			String tipoDiscapacidadMinisJusticia) {
		this.tipoDiscapacidadMinisJusticia = tipoDiscapacidadMinisJusticia;
	}
	
	/** el porcentaje discapacidad. */
	private String porcentajeDiscapacidad;
	
	/** el reserva discapacidad. */
	private String reservaDiscapacidad;
	
	/** el detalle discapacidad. */
	private String detalleDiscapacidad;
	
	/** el titulo. */
	private String titulo;
	
	/** el otros titulos. */
	private String otrosTitulos;
	
	/** el datos A. */
	private String datosA;
	
	/** el datos B. */
	private String datosB;
	
	/** el datos C. */
	private String datosC;
	
	/** el action. */
	private String action;

	/** el plantilla. */
	private PlantillaBean plantilla;
	
	/** el plantilla nombre. */
	private String plantillaNombre;
	
	/** el porcentaje min discapacidad. */
	private String porcentajeMinDiscapacidad;
	
	/** el importe original. */
	private String importeOriginal;
	
	/** el importe actual. */
	private String importeActual;
	
	/** el id consentimiento. */
	private Boolean idConsentimiento;
	
	/** el id ministerio. */
	private String idMinisterio;
	
	/** el codigo min justicia. */
	private String codigoMinJusticia;
	
	/** el siglas centro gestor. */
	private String siglasCentroGestor;
	
	/** el siglas centro gestor justicia. */
	private String siglasCentroGestorJusticia;
	
	/** el id comunidad DD exento. */
	private String idComunidadDDExento;
	
	/** el id solicitud. */
	private String idSolicitud;
	
	/** el id motivo reduccion. */
	private String idMotivoReduccion;
	
	/** el id comunidad FN exento. */
	private String idComunidadFNExento;
	
	/** el importe pagado. */
	private String importePagado;

	/** el plantilla propios. */
	private List<PlantillaPropiosBean> plantillaPropios;
	
	/** el otros titulos list. */
	private List<OtrosTitulos> otrosTitulosList;
	
	/** el discapacidad list. */
	private List<Discapacidad> discapacidadList;
	
	/** el pago inicial modif. */
	private String pagoInicialModif;
	
	/** el id solicitud inicial. */
	private String idSolicitudInicial;
	
	private boolean autorizar;
	
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
	 * Obtiene el plantilla nombre.
	 *
	 * @return el plantilla nombre
	 */
	public String getPlantillaNombre() {
		return plantillaNombre;
	}
	
	/**
	 * Establece el plantilla nombre.
	 *
	 * @param plantillaNombre el nuevo plantilla nombre
	 */
	public void setPlantillaNombre(String plantillaNombre) {
		this.plantillaNombre = plantillaNombre;
	}
	
	/**
	 * Obtiene el plantilla.
	 *
	 * @return el plantilla
	 */
	public PlantillaBean getPlantilla() {
		return plantilla;
	}
	
	/**
	 * Establece el plantilla.
	 *
	 * @param plantilla el nuevo plantilla
	 */
	public void setPlantilla(PlantillaBean plantilla) {
		this.plantilla = plantilla;
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
	 * Establece el nif.
	 *
	 * @param nif el nuevo nif
	 */
	public void setNif(String nif) {
		this.nif = nif;
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
	 * Obtiene el nif ciudadano.
	 *
	 * @return el nif ciudadano
	 */
	public String getNifCiudadano() {
		return nifCiudadano;
	}
	
	/**
	 * Establece el nif ciudadano.
	 *
	 * @param nifCiudadano el nuevo nif ciudadano
	 */
	public void setNifCiudadano(String nifCiudadano) {
		this.nifCiudadano = nifCiudadano;
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
	 * Obtiene el nif fun habilitado.
	 *
	 * @return el nif fun habilitado
	 */
	public String getNifFunHabilitado() {
		return nifFunHabilitado;
	}
	
	/**
	 * Establece el nif fun habilitado.
	 *
	 * @param nifFunHabilitado el nuevo nif fun habilitado
	 */
	public void setNifFunHabilitado(String nifFunHabilitado) {
		this.nifFunHabilitado = nifFunHabilitado;
	}
	
	/**
	 * Obtiene el nombre fun habilitado.
	 *
	 * @return el nombre fun habilitado
	 */
	public String getNombreFunHabilitado() {
		return nombreFunHabilitado;
	}
	
	/**
	 * Establece el nombre fun habilitado.
	 *
	 * @param nombreFunHabilitado el nuevo nombre fun habilitado
	 */
	public void setNombreFunHabilitado(String nombreFunHabilitado) {
		this.nombreFunHabilitado = nombreFunHabilitado;
	}
	
	/**
	 * Obtiene el primer apellido fun habilitado.
	 *
	 * @return el primer apellido fun habilitado
	 */
	public String getPrimerApellidoFunHabilitado() {
		return primerApellidoFunHabilitado;
	}
	
	/**
	 * Establece el primer apellido fun habilitado.
	 *
	 * @param primerApellidoFunHabilitado el nuevo primer apellido fun habilitado
	 */
	public void setPrimerApellidoFunHabilitado(String primerApellidoFunHabilitado) {
		this.primerApellidoFunHabilitado = primerApellidoFunHabilitado;
	}
	
	/**
	 * Obtiene el segundo apellido fun habilitado.
	 *
	 * @return el segundo apellido fun habilitado
	 */
	public String getSegundoApellidoFunHabilitado() {
		return segundoApellidoFunHabilitado;
	}
	
	/**
	 * Establece el segundo apellido fun habilitado.
	 *
	 * @param segundoApellidoFunHabilitado el nuevo segundo apellido fun habilitado
	 */
	public void setSegundoApellidoFunHabilitado(String segundoApellidoFunHabilitado) {
		this.segundoApellidoFunHabilitado = segundoApellidoFunHabilitado;
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
	 * Obtiene el pais.
	 *
	 * @return el pais
	 */
	public String getPais() {
		return pais;
	}
	
	/**
	 * Establece el pais.
	 *
	 * @param pais el nuevo pais
	 */
	public void setPais(String pais) {
		this.pais = pais;
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
	 * Obtiene el action.
	 *
	 * @return el action
	 */
	public String getAction() {
		return action;
	}
	
	/**
	 * Establece el action.
	 *
	 * @param action el nuevo action
	 */
	public void setAction(String action) {
		this.action = action;
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
	 * Obtiene el codigo min justicia.
	 *
	 * @return el codigo min justicia
	 */
	public String getCodigoMinJusticia() {
		return codigoMinJusticia;
	}
	
	/**
	 * Establece el codigo min justicia.
	 *
	 * @param codigoMinJusticia el nuevo codigo min justicia
	 */
	public void setCodigoMinJusticia(String codigoMinJusticia) {
		this.codigoMinJusticia = codigoMinJusticia;
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
	 * Obtiene el siglas centro gestor justicia.
	 *
	 * @return el siglas centro gestor justicia
	 */
	public String getSiglasCentroGestorJusticia() {
		return siglasCentroGestorJusticia;
	}
	
	/**
	 * Establece el siglas centro gestor justicia.
	 *
	 * @param siglasCentroGestorJusticia el nuevo siglas centro gestor justicia
	 */
	public void setSiglasCentroGestorJusticia(String siglasCentroGestorJusticia) {
		this.siglasCentroGestorJusticia = siglasCentroGestorJusticia;
	}
	
	/**
	 * Obtiene el importe actual.
	 *
	 * @return el importe actual
	 */
	public String getImporteActual() {
		return importeActual;
	}
	
	/**
	 * Establece el importe actual.
	 *
	 * @param importeActual el nuevo importe actual
	 */
	public void setImporteActual(String importeActual) {
		this.importeActual = importeActual;
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

	public boolean isAutorizar() {
		return autorizar;
	}

	public void setAutorizar(boolean autorizar) {
		this.autorizar = autorizar;
	}

	public Boolean getIdConsentimientoAEAT() {
		return idConsentimientoAEAT;
	}

	public void setIdConsentimientoAEAT(Boolean idConsentimientoAEAT) {
		this.idConsentimientoAEAT = idConsentimientoAEAT;
	}
}
