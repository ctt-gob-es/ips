package es.map.ipsg.form;

import java.util.ArrayList;

import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.CamposPropios;
import es.map.ipsg.bean.ModeloBean;
import es.map.ipsg.bean.PlantillaPropiosBean;

/**
 * El Class PlantillaForm.
 */
public class PlantillaForm extends SpringForm {
	
	/** el id. */
	private Long id;
	
	/** el nif. */
	private boolean nif = true;
	
	/** el primer apellido. */
	private boolean primerApellido = true;
	
	/** el segundo apellido. */
	private boolean segundoApellido;
	
	/** el nombre. */
	private boolean nombre = true;
	
	/** el fecha nacimiento. */
	private boolean fechaNacimiento;
	
	/** el sexo. */
	private boolean sexo;
	
	/** el provincia nacimiento. */
	private boolean provinciaNacimiento;
	
	/** el localidad nacimiento. */
	private boolean localidadNacimiento;
	
	/** el nacionalidad. */
	private boolean nacionalidad;
	
	/** el telefono. */
	private boolean telefono;
	
	/** el correo electronico. */
	private boolean correoElectronico = true;
	
	/** el via. */
	private boolean via;
	
	/** el codigo postal. */
	private boolean codigoPostal;
	
	/** el municipio. */
	private boolean municipio;
	
	/** el provincia. */
	private boolean provincia;
	
	/** el pais. */
	private boolean pais;
	
	/** el cuerpo. */
	private boolean cuerpo = true;
	
	/** el especialidad. */
	private boolean especialidad;
	
	/** el formaacceso. */
	private boolean formaacceso = true;
	
	/** el entidad convocante. */
	private boolean entidadConvocante = true;
	
	/** el fecha boe. */
	private boolean fechaBoe = true;
	
	/** el provincia examen. */
	private boolean provinciaExamen;
	
	/** el tipo discapacidad. */
	private boolean tipoDiscapacidad;
	
	/** el porcentaje discapacidad. */
	private boolean porcentajeDiscapacidad;
	
	/** el reserva discapacidad. */
	private boolean reservaDiscapacidad;
	
	/** el detalle discapacidad. */
	private boolean detalleDiscapacidad;
	
	/** el titulos exigidos. */
	private boolean titulosExigidos;
	
	/** el otros titulos. */
	private boolean otrosTitulos;
	
	/** el datos A. */
	private boolean datosA;
	
	/** el datos B. */
	private boolean datosB;
	
	/** el datos C. */
	private boolean datosC;
	
	/** el tipo plantilla. */
	private String tipoPlantilla;
	
	/** el submit. */
	private String submit;
	
	/*INI-TRA042*/
	/** el submit. */
	private String accion;
	/*FIN-TRA042*/
	
	/** el id convocatoria. */
	private Long idConvocatoria;
	
	/** el id centro gestor. */
	private Integer idCentroGestor;
	
	/*INI-TRA042*/
	private String desCentroGestor;
	/*FIN-TRA042*/
	
	/** el codigo ministerio. */
	private boolean codigoMinisterio;
	
	/** el codigo cuerpo escala. */
	private boolean codigoCuerpoEscala;
	
	/** el codigo especialidad. */
	private boolean codigoEspecialidad;
	
	/** el codigo titulo oficial. */
	private boolean codigoTituloOficial;
	
	/** el codigo pais domicilio. */
	private boolean codigoPaisDomicilio;
	
	/** el codigo provincia domicilio. */
	private boolean codigoProvinciaDomicilio;
	
	/** el codigo provincia examen. */
	private boolean codigoProvinciaExamen;
	
	/** el descripcion. */
	private String descripcion;
	
	/** el id campo propio. */
	private String idCampoPropio;
	
	/** el titulo campo. */
	private String tituloCampo;
	
	/** el lista campos propios. */
	private ArrayList<CamposPropios> listaCamposPropios;
	
	/** el lista modelos bean. */
	private ArrayList<ModeloBean> listaModelosBean;
	
	/** el lista plantillas propios bean. */
	private ArrayList<PlantillaPropiosBean> listaPlantillasPropiosBean;
	
	
	/**
	 * Obtiene el lista plantillas propios bean.
	 *
	 * @return el lista plantillas propios bean
	 */
	public ArrayList<PlantillaPropiosBean> getListaPlantillasPropiosBean() {
		return listaPlantillasPropiosBean;
	}
	
	/**
	 * Establece el lista plantillas propios bean.
	 *
	 * @param listaPlantillasPropiosBean el nuevo lista plantillas propios bean
	 */
	public void setListaPlantillasPropiosBean(
			ArrayList<PlantillaPropiosBean> listaPlantillasPropiosBean) {
		this.listaPlantillasPropiosBean = listaPlantillasPropiosBean;
	}
	
	/**
	 * Obtiene el lista modelos bean.
	 *
	 * @return el lista modelos bean
	 */
	public ArrayList<ModeloBean> getListaModelosBean() {
		return listaModelosBean;
	}
	
	/**
	 * Establece el lista modelos bean.
	 *
	 * @param listaModelosBean el nuevo lista modelos bean
	 */
	public void setListaModelosBean(ArrayList<ModeloBean> listaModelosBean) {
		this.listaModelosBean = listaModelosBean;
	}
	
	/**
	 * Obtiene el lista campos propios.
	 *
	 * @return el lista campos propios
	 */
	public ArrayList<CamposPropios> getListaCamposPropios() {
		return listaCamposPropios;
	}
	
	/**
	 * Establece el lista campos propios.
	 *
	 * @param listaCamposPropios el nuevo lista campos propios
	 */
	public void setListaCamposPropios(ArrayList<CamposPropios> listaCamposPropios) {
		this.listaCamposPropios = listaCamposPropios;
	}
	
	/**
	 * Obtiene el id campo propio.
	 *
	 * @return el id campo propio
	 */
	public String getIdCampoPropio() {
		return idCampoPropio;
	}
	
	/**
	 * Establece el id campo propio.
	 *
	 * @param idCampoPropio el nuevo id campo propio
	 */
	public void setIdCampoPropio(String idCampoPropio) {
		this.idCampoPropio = idCampoPropio;
	}
	
	/**
	 * Obtiene el titulo campo.
	 *
	 * @return el titulo campo
	 */
	public String getTituloCampo() {
		return tituloCampo;
	}
	
	/**
	 * Establece el titulo campo.
	 *
	 * @param tituloCampo el nuevo titulo campo
	 */
	public void setTituloCampo(String tituloCampo) {
		this.tituloCampo = tituloCampo;
	}
	
	/**
	 * Obtiene el descripcion.
	 *
	 * @return el descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	
	/**
	 * Establece el descripcion.
	 *
	 * @param descripcion el nuevo descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
	 * Comprueba si es nif.
	 *
	 * @return verdadero, si es nif
	 */
	public boolean isNif() {
		return nif;
	}
	
	/**
	 * Establece el nif.
	 *
	 * @param nif el nuevo nif
	 */
	public void setNif(boolean nif) {
		this.nif = nif;
	}
	
	/**
	 * Comprueba si es primer apellido.
	 *
	 * @return verdadero, si es primer apellido
	 */
	public boolean isPrimerApellido() {
		return primerApellido;
	}
	
	/**
	 * Establece el primer apellido.
	 *
	 * @param primerApellido el nuevo primer apellido
	 */
	public void setPrimerApellido(boolean primerApellido) {
		this.primerApellido = primerApellido;
	}
	
	/**
	 * Comprueba si es segundo apellido.
	 *
	 * @return verdadero, si es segundo apellido
	 */
	public boolean isSegundoApellido() {
		return segundoApellido;
	}
	
	/**
	 * Establece el segundo apellido.
	 *
	 * @param segundoApellido el nuevo segundo apellido
	 */
	public void setSegundoApellido(boolean segundoApellido) {
		this.segundoApellido = segundoApellido;
	}
	
	/**
	 * Comprueba si es nombre.
	 *
	 * @return verdadero, si es nombre
	 */
	public boolean isNombre() {
		return nombre;
	}
	
	/**
	 * Establece el nombre.
	 *
	 * @param nombre el nuevo nombre
	 */
	public void setNombre(boolean nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * Comprueba si es fecha nacimiento.
	 *
	 * @return verdadero, si es fecha nacimiento
	 */
	public boolean isFechaNacimiento() {
		return fechaNacimiento;
	}
	
	/**
	 * Establece el fecha nacimiento.
	 *
	 * @param fechaNacimiento el nuevo fecha nacimiento
	 */
	public void setFechaNacimiento(boolean fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	
	/**
	 * Comprueba si es sexo.
	 *
	 * @return verdadero, si es sexo
	 */
	public boolean isSexo() {
		return sexo;
	}
	
	/**
	 * Establece el sexo.
	 *
	 * @param sexo el nuevo sexo
	 */
	public void setSexo(boolean sexo) {
		this.sexo = sexo;
	}
	
	/**
	 * Comprueba si es provincia nacimiento.
	 *
	 * @return verdadero, si es provincia nacimiento
	 */
	public boolean isProvinciaNacimiento() {
		return provinciaNacimiento;
	}
	
	/**
	 * Establece el provincia nacimiento.
	 *
	 * @param provinciaNacimiento el nuevo provincia nacimiento
	 */
	public void setProvinciaNacimiento(boolean provinciaNacimiento) {
		this.provinciaNacimiento = provinciaNacimiento;
	}
	
	/**
	 * Comprueba si es localidad nacimiento.
	 *
	 * @return verdadero, si es localidad nacimiento
	 */
	public boolean isLocalidadNacimiento() {
		return localidadNacimiento;
	}
	
	/**
	 * Establece el localidad nacimiento.
	 *
	 * @param localidadNacimiento el nuevo localidad nacimiento
	 */
	public void setLocalidadNacimiento(boolean localidadNacimiento) {
		this.localidadNacimiento = localidadNacimiento;
	}
	
	/**
	 * Comprueba si es nacionalidad.
	 *
	 * @return verdadero, si es nacionalidad
	 */
	public boolean isNacionalidad() {
		return nacionalidad;
	}
	
	/**
	 * Establece el nacionalidad.
	 *
	 * @param nacionalidad el nuevo nacionalidad
	 */
	public void setNacionalidad(boolean nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	
	/**
	 * Comprueba si es telefono.
	 *
	 * @return verdadero, si es telefono
	 */
	public boolean isTelefono() {
		return telefono;
	}
	
	/**
	 * Establece el telefono.
	 *
	 * @param telefono el nuevo telefono
	 */
	public void setTelefono(boolean telefono) {
		this.telefono = telefono;
	}
	
	/**
	 * Comprueba si es correo electronico.
	 *
	 * @return verdadero, si es correo electronico
	 */
	public boolean isCorreoElectronico() {
		return correoElectronico;
	}
	
	/**
	 * Establece el correo electronico.
	 *
	 * @param correoElectronico el nuevo correo electronico
	 */
	public void setCorreoElectronico(boolean correoElectronico) {
		this.correoElectronico = correoElectronico;
	}
	
	/**
	 * Comprueba si es via.
	 *
	 * @return verdadero, si es via
	 */
	public boolean isVia() {
		return via;
	}
	
	/**
	 * Establece el via.
	 *
	 * @param via el nuevo via
	 */
	public void setVia(boolean via) {
		this.via = via;
	}
	
	/**
	 * Comprueba si es codigo postal.
	 *
	 * @return verdadero, si es codigo postal
	 */
	public boolean isCodigoPostal() {
		return codigoPostal;
	}
	
	/**
	 * Establece el codigo postal.
	 *
	 * @param codigoPostal el nuevo codigo postal
	 */
	public void setCodigoPostal(boolean codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	
	/**
	 * Comprueba si es municipio.
	 *
	 * @return verdadero, si es municipio
	 */
	public boolean isMunicipio() {
		return municipio;
	}
	
	/**
	 * Establece el municipio.
	 *
	 * @param municipio el nuevo municipio
	 */
	public void setMunicipio(boolean municipio) {
		this.municipio = municipio;
	}
	
	/**
	 * Comprueba si es provincia.
	 *
	 * @return verdadero, si es provincia
	 */
	public boolean isProvincia() {
		return provincia;
	}
	
	/**
	 * Establece el provincia.
	 *
	 * @param provincia el nuevo provincia
	 */
	public void setProvincia(boolean provincia) {
		this.provincia = provincia;
	}
	
	/**
	 * Comprueba si es pais.
	 *
	 * @return verdadero, si es pais
	 */
	public boolean isPais() {
		return pais;
	}
	
	/**
	 * Establece el pais.
	 *
	 * @param pais el nuevo pais
	 */
	public void setPais(boolean pais) {
		this.pais = pais;
	}
	
	/**
	 * Comprueba si es cuerpo.
	 *
	 * @return verdadero, si es cuerpo
	 */
	public boolean isCuerpo() {
		return cuerpo;
	}
	
	/**
	 * Establece el cuerpo.
	 *
	 * @param cuerpo el nuevo cuerpo
	 */
	public void setCuerpo(boolean cuerpo) {
		this.cuerpo = cuerpo;
	}
	
	/**
	 * Comprueba si es especialidad.
	 *
	 * @return verdadero, si es especialidad
	 */
	public boolean isEspecialidad() {
		return especialidad;
	}
	
	/**
	 * Establece el especialidad.
	 *
	 * @param especialidad el nuevo especialidad
	 */
	public void setEspecialidad(boolean especialidad) {
		this.especialidad = especialidad;
	}
	
	/**
	 * Comprueba si es formaacceso.
	 *
	 * @return verdadero, si es formaacceso
	 */
	public boolean isFormaacceso() {
		return formaacceso;
	}
	
	/**
	 * Establece el formaacceso.
	 *
	 * @param formaacceso el nuevo formaacceso
	 */
	public void setFormaacceso(boolean formaacceso) {
		this.formaacceso = formaacceso;
	}
	
	/**
	 * Comprueba si es entidad convocante.
	 *
	 * @return verdadero, si es entidad convocante
	 */
	public boolean isEntidadConvocante() {
		return entidadConvocante;
	}
	
	/**
	 * Establece el entidad convocante.
	 *
	 * @param entidadConvocante el nuevo entidad convocante
	 */
	public void setEntidadConvocante(boolean entidadConvocante) {
		this.entidadConvocante = entidadConvocante;
	}
	
	/**
	 * Comprueba si es fecha boe.
	 *
	 * @return verdadero, si es fecha boe
	 */
	public boolean isFechaBoe() {
		return fechaBoe;
	}
	
	/**
	 * Establece el fecha boe.
	 *
	 * @param fechaBoe el nuevo fecha boe
	 */
	public void setFechaBoe(boolean fechaBoe) {
		this.fechaBoe = fechaBoe;
	}
	
	/**
	 * Comprueba si es provincia examen.
	 *
	 * @return verdadero, si es provincia examen
	 */
	public boolean isProvinciaExamen() {
		return provinciaExamen;
	}
	
	/**
	 * Establece el provincia examen.
	 *
	 * @param provinciaExamen el nuevo provincia examen
	 */
	public void setProvinciaExamen(boolean provinciaExamen) {
		this.provinciaExamen = provinciaExamen;
	}
	
	/**
	 * Comprueba si es tipo discapacidad.
	 *
	 * @return verdadero, si es tipo discapacidad
	 */
	public boolean isTipoDiscapacidad() {
		return tipoDiscapacidad;
	}
	
	/**
	 * Establece el tipo discapacidad.
	 *
	 * @param tipoDiscapacidad el nuevo tipo discapacidad
	 */
	public void setTipoDiscapacidad(boolean tipoDiscapacidad) {
		this.tipoDiscapacidad = tipoDiscapacidad;
	}
	
	/**
	 * Comprueba si es porcentaje discapacidad.
	 *
	 * @return verdadero, si es porcentaje discapacidad
	 */
	public boolean isPorcentajeDiscapacidad() {
		return porcentajeDiscapacidad;
	}
	
	/**
	 * Establece el porcentaje discapacidad.
	 *
	 * @param porcentajeDiscapacidad el nuevo porcentaje discapacidad
	 */
	public void setPorcentajeDiscapacidad(boolean porcentajeDiscapacidad) {
		this.porcentajeDiscapacidad = porcentajeDiscapacidad;
	}
	
	/**
	 * Comprueba si es reserva discapacidad.
	 *
	 * @return verdadero, si es reserva discapacidad
	 */
	public boolean isReservaDiscapacidad() {
		return reservaDiscapacidad;
	}
	
	/**
	 * Establece el reserva discapacidad.
	 *
	 * @param reservaDiscapacidad el nuevo reserva discapacidad
	 */
	public void setReservaDiscapacidad(boolean reservaDiscapacidad) {
		this.reservaDiscapacidad = reservaDiscapacidad;
	}
	
	/**
	 * Comprueba si es detalle discapacidad.
	 *
	 * @return verdadero, si es detalle discapacidad
	 */
	public boolean isDetalleDiscapacidad() {
		return detalleDiscapacidad;
	}
	
	/**
	 * Establece el detalle discapacidad.
	 *
	 * @param detalleDiscapacidad el nuevo detalle discapacidad
	 */
	public void setDetalleDiscapacidad(boolean detalleDiscapacidad) {
		this.detalleDiscapacidad = detalleDiscapacidad;
	}
	
	/**
	 * Comprueba si es titulos exigidos.
	 *
	 * @return verdadero, si es titulos exigidos
	 */
	public boolean isTitulosExigidos() {
		return titulosExigidos;
	}
	
	/**
	 * Establece el titulos exigidos.
	 *
	 * @param titulosExigidos el nuevo titulos exigidos
	 */
	public void setTitulosExigidos(boolean titulosExigidos) {
		this.titulosExigidos = titulosExigidos;
	}
	
	/**
	 * Comprueba si es otros titulos.
	 *
	 * @return verdadero, si es otros titulos
	 */
	public boolean isOtrosTitulos() {
		return otrosTitulos;
	}
	
	/**
	 * Establece el otros titulos.
	 *
	 * @param otrosTitulos el nuevo otros titulos
	 */
	public void setOtrosTitulos(boolean otrosTitulos) {
		this.otrosTitulos = otrosTitulos;
	}
	
	/**
	 * Comprueba si es datos A.
	 *
	 * @return verdadero, si es datos A
	 */
	public boolean isDatosA() {
		return datosA;
	}
	
	/**
	 * Establece el datos A.
	 *
	 * @param datosA el nuevo datos A
	 */
	public void setDatosA(boolean datosA) {
		this.datosA = datosA;
	}
	
	/**
	 * Comprueba si es datos B.
	 *
	 * @return verdadero, si es datos B
	 */
	public boolean isDatosB() {
		return datosB;
	}
	
	/**
	 * Establece el datos B.
	 *
	 * @param datosB el nuevo datos B
	 */
	public void setDatosB(boolean datosB) {
		this.datosB = datosB;
	}
	
	/**
	 * Comprueba si es datos C.
	 *
	 * @return verdadero, si es datos C
	 */
	public boolean isDatosC() {
		return datosC;
	}
	
	/**
	 * Establece el datos C.
	 *
	 * @param datosC el nuevo datos C
	 */
	public void setDatosC(boolean datosC) {
		this.datosC = datosC;
	}
	
	/**
	 * Obtiene el tipo plantilla.
	 *
	 * @return el tipo plantilla
	 */
	public String getTipoPlantilla() {
		return tipoPlantilla;
	}
	
	/**
	 * Establece el tipo plantilla.
	 *
	 * @param tipoPlantilla el nuevo tipo plantilla
	 */
	public void setTipoPlantilla(String tipoPlantilla) {
		this.tipoPlantilla = tipoPlantilla;
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
	public Integer getIdCentroGestor() {
		return idCentroGestor;
	}
	
	/**
	 * Establece el id centro gestor.
	 *
	 * @param idCentroGestor el nuevo id centro gestor
	 */
	public void setIdCentroGestor(Integer idCentroGestor) {
		this.idCentroGestor = idCentroGestor;
	}
	
	/**
	 * Comprueba si es codigo ministerio.
	 *
	 * @return verdadero, si es codigo ministerio
	 */
	public boolean isCodigoMinisterio() {
		return codigoMinisterio;
	}
	
	/**
	 * Establece el codigo ministerio.
	 *
	 * @param codigoMinisterio el nuevo codigo ministerio
	 */
	public void setCodigoMinisterio(boolean codigoMinisterio) {
		this.codigoMinisterio = codigoMinisterio;
	}
	
	/**
	 * Comprueba si es codigo cuerpo escala.
	 *
	 * @return verdadero, si es codigo cuerpo escala
	 */
	public boolean isCodigoCuerpoEscala() {
		return codigoCuerpoEscala;
	}
	
	/**
	 * Establece el codigo cuerpo escala.
	 *
	 * @param codigoCuerpoEscala el nuevo codigo cuerpo escala
	 */
	public void setCodigoCuerpoEscala(boolean codigoCuerpoEscala) {
		this.codigoCuerpoEscala = codigoCuerpoEscala;
	}
	
	/**
	 * Comprueba si es codigo especialidad.
	 *
	 * @return verdadero, si es codigo especialidad
	 */
	public boolean isCodigoEspecialidad() {
		return codigoEspecialidad;
	}
	
	/**
	 * Establece el codigo especialidad.
	 *
	 * @param codigoEspecialidad el nuevo codigo especialidad
	 */
	public void setCodigoEspecialidad(boolean codigoEspecialidad) {
		this.codigoEspecialidad = codigoEspecialidad;
	}
	
	/**
	 * Comprueba si es codigo titulo oficial.
	 *
	 * @return verdadero, si es codigo titulo oficial
	 */
	public boolean isCodigoTituloOficial() {
		return codigoTituloOficial;
	}
	
	/**
	 * Establece el codigo titulo oficial.
	 *
	 * @param codigoTituloOficial el nuevo codigo titulo oficial
	 */
	public void setCodigoTituloOficial(boolean codigoTituloOficial) {
		this.codigoTituloOficial = codigoTituloOficial;
	}
	
	/**
	 * Comprueba si es codigo pais domicilio.
	 *
	 * @return verdadero, si es codigo pais domicilio
	 */
	public boolean isCodigoPaisDomicilio() {
		return codigoPaisDomicilio;
	}
	
	/**
	 * Establece el codigo pais domicilio.
	 *
	 * @param codigoPaisDomicilio el nuevo codigo pais domicilio
	 */
	public void setCodigoPaisDomicilio(boolean codigoPaisDomicilio) {
		this.codigoPaisDomicilio = codigoPaisDomicilio;
	}
	
	/**
	 * Comprueba si es codigo provincia domicilio.
	 *
	 * @return verdadero, si es codigo provincia domicilio
	 */
	public boolean isCodigoProvinciaDomicilio() {
		return codigoProvinciaDomicilio;
	}
	
	/**
	 * Establece el codigo provincia domicilio.
	 *
	 * @param codigoProvinciaDomicilio el nuevo codigo provincia domicilio
	 */
	public void setCodigoProvinciaDomicilio(boolean codigoProvinciaDomicilio) {
		this.codigoProvinciaDomicilio = codigoProvinciaDomicilio;
	}
	
	/**
	 * Comprueba si es codigo provincia examen.
	 *
	 * @return verdadero, si es codigo provincia examen
	 */
	public boolean isCodigoProvinciaExamen() {
		return codigoProvinciaExamen;
	}
	
	/**
	 * Establece el codigo provincia examen.
	 *
	 * @param codigoProvinciaExamen el nuevo codigo provincia examen
	 */
	public void setCodigoProvinciaExamen(boolean codigoProvinciaExamen) {
		this.codigoProvinciaExamen = codigoProvinciaExamen;
	}

	/*INI-TRA042*/
	/**
	 * @return the accion
	 */
	public String getAccion() {
		return accion;
	}

	/**
	 * @param accion the accion to set
	 */
	public void setAccion(String accion) {
		this.accion = accion;
	}

	/**
	 * @return the desCentroGestor
	 */
	public String getDesCentroGestor() {
		return desCentroGestor;
	}

	/**
	 * @param desCentroGestor the desCentroGestor to set
	 */
	public void setDesCentroGestor(String desCentroGestor) {
		this.desCentroGestor = desCentroGestor;
	}
	/*FIN-TRA042*/

}
