package es.map.ipsg.bean;

import java.util.Date;

/**
 * SolicitudBean.
 *
 * @author amartinl
 */
public class SolicitudXmlSigpBean {
	
	/** el id solicitud. */
	private Long	idSolicitud;
	
	/** el ministerio. */
	//Proceso_Selectivo
	private String  ministerio;
	
	/** el centro gestor. */
	private String  centroGestor;
	
	/** el anio convocatoria. */
	private String	anioConvocatoria;
	
	/** el cuerpo escala. */
	private String	cuerpoEscala;
	
	/** el especialidad. */
	private String 	especialidad;
	
	/** el fec boe. */
	private Date	fecBoe;
	
	/** el entidad convocante. */
	private String	entidadConvocante;
	
	/** el nombre. */
	//Empleado
	private String 	nombre;
	
	/** el apellido 1. */
	private String 	apellido1;
	
	/** el apellido 2. */
	private String 	apellido2;
	
	/** el sexo. */
	private char 	sexo;
	
	/** el fecha nacimiento. */
	private Date 	fechaNacimiento;
	
	/** el tipo documento. */
	private Integer tipoDocumento;
	
	/** el numero documento. */
	private String 	numeroDocumento;
	
	/** el email. */
	private String 	email;
	
	/** el numero telefono fax. */
	private String 	numeroTelefonoFax;
	
	/** el valor direccion. */
	//Direccion
	private String 	valorDireccion;
	
	/** el pais. */
	private String 	pais;
	
	/** el provincia. */
	private String 	provincia;
	
	/** el localidad. */
	private String 	localidad;
	
	/** el num solicitud. */
	//Aspirante
	private String	numSolicitud;
	
	/** el fecha sol. */
	private Date 	fechaSol;
	
	/** el tipo solicitud. */
	//Convocatoria
	private char	tipoSolicitud;
	
	/** el num justificante. */
	private String	numJustificante;
	
	/** el prov examen. */
	private String 	provExamen;
	
	/** el cuerpo asp. */
	private String 	cuerpoAsp;
	
	/** el categ asp. */
	private String 	categAsp;
	
	/** el grup prof asp. */
	private String 	grupProfAsp;
	
	/** el dato A. */
	private String 	datoA;
	
	/** el dato B. */
	private String 	datoB;
	
	/** el dato C. */
	private String 	datoC;
	
	/** el tasas. */
	private Float 	tasas;
	
	/** el reduc tasas. */
	private String	reducTasas;
	
	/** el discapacitado. */
	//Datos Aspirante
	private String	discapacitado;
	
	/** el grado disc. */
	private Integer	gradoDisc;
	
	/** el adaptacion disc. */
	private String	adaptacionDisc;
	
	/** el reser disc. */
	private String	reserDisc;
	
	/** el discap intel. */
	private String	discapIntel;
	
	/** el titulo exigido. */
	private String	tituloExigido;
	
	/** el titulos academicos. */
	private String	titulosAcademicos;
	
	/** el forma acceso. */
	//Admision
	private String	formaAcceso;
	
	
	
	/**
	 * Obtiene el ministerio.
	 *
	 * @return the idMinisterio
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
	 * Obtiene el centro gestor.
	 *
	 * @return the idCentroGestor
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
	 * Obtiene el anio convocatoria.
	 *
	 * @return the anioConvocatoria
	 */
	public String getAnioConvocatoria() {
		return anioConvocatoria;
	}
	
	/**
	 * Establece el anio convocatoria.
	 *
	 * @param anioConvocatoria the anioConvocatoria to set
	 */
	public void setAnioConvocatoria(String anioConvocatoria) {
		this.anioConvocatoria = anioConvocatoria;
	}
	
	/**
	 * Obtiene el cuerpo escala.
	 *
	 * @return the cuerpoEscala
	 */
	public String getCuerpoEscala() {
		return cuerpoEscala;
	}
	
	/**
	 * Establece el cuerpo escala.
	 *
	 * @param cuerpoEscala the cuerpoEscala to set
	 */
	public void setCuerpoEscala(String cuerpoEscala) {
		this.cuerpoEscala = cuerpoEscala;
	}
	
	/**
	 * Obtiene el especialidad.
	 *
	 * @return the idEspecialidad
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
	 * Obtiene el fec boe.
	 *
	 * @return the fecBoe
	 */
	public Date getFecBoe() {
		return fecBoe;
	}
	
	/**
	 * Establece el fec boe.
	 *
	 * @param fecBoe the fecBoe to set
	 */
	public void setFecBoe(Date fecBoe) {
		this.fecBoe = fecBoe;
	}
	
	/**
	 * Obtiene el entidad convocante.
	 *
	 * @return the entidadConvocante
	 */
	public String getEntidadConvocante() {
		return entidadConvocante;
	}
	
	/**
	 * Establece el entidad convocante.
	 *
	 * @param entidadConvocante the entidadConvocante to set
	 */
	public void setEntidadConvocante(String entidadConvocante) {
		this.entidadConvocante = entidadConvocante;
	}
	
	/**
	 * Obtiene el nombre.
	 *
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * Establece el nombre.
	 *
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * Obtiene el apellido 1.
	 *
	 * @return the apellido1
	 */
	public String getApellido1() {
		return apellido1;
	}
	
	/**
	 * Establece el apellido 1.
	 *
	 * @param apellido1 the apellido1 to set
	 */
	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}
	
	/**
	 * Obtiene el apellido 2.
	 *
	 * @return the apellido2
	 */
	public String getApellido2() {
		return apellido2;
	}
	
	/**
	 * Establece el apellido 2.
	 *
	 * @param apellido2 the apellido2 to set
	 */
	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}
	
	/**
	 * Obtiene el sexo.
	 *
	 * @return the sexo
	 */
	public char getSexo() {
		return sexo;
	}
	
	/**
	 * Establece el sexo.
	 *
	 * @param sexo the sexo to set
	 */
	public void setSexo(char sexo) {
		this.sexo = sexo;
	}
	
	/**
	 * Obtiene el fecha nacimiento.
	 *
	 * @return the fechaNacimiento
	 */
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	
	/**
	 * Establece el fecha nacimiento.
	 *
	 * @param fechaNacimiento the fechaNacimiento to set
	 */
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	
	/**
	 * Obtiene el tipo documento.
	 *
	 * @return the tipoDocumento
	 */
	public Integer getTipoDocumento() {
		return tipoDocumento;
	}
	
	/**
	 * Establece el tipo documento.
	 *
	 * @param tipoDocumento the tipoDocumento to set
	 */
	public void setTipoDocumento(Integer tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	
	/**
	 * Obtiene el numero documento.
	 *
	 * @return the numeroDocumento
	 */
	public String getNumeroDocumento() {
		return numeroDocumento;
	}
	
	/**
	 * Establece el numero documento.
	 *
	 * @param numeroDocumento the numeroDocumento to set
	 */
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}
	
	/**
	 * Obtiene el email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Establece el email.
	 *
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * Obtiene el numero telefono fax.
	 *
	 * @return the numeroTelefonoFax
	 */
	public String getNumeroTelefonoFax() {
		return numeroTelefonoFax;
	}
	
	/**
	 * Establece el numero telefono fax.
	 *
	 * @param numeroTelefonoFax the numeroTelefonoFax to set
	 */
	public void setNumeroTelefonoFax(String numeroTelefonoFax) {
		this.numeroTelefonoFax = numeroTelefonoFax;
	}
	
	/**
	 * Obtiene el valor direccion.
	 *
	 * @return the valorDireccion
	 */
	public String getValorDireccion() {
		return valorDireccion;
	}
	
	/**
	 * Establece el valor direccion.
	 *
	 * @param valorDireccion the valorDireccion to set
	 */
	public void setValorDireccion(String valorDireccion) {
		this.valorDireccion = valorDireccion;
	}
	
	/**
	 * Obtiene el pais.
	 *
	 * @return the pais
	 */
	public String getPais() {
		return pais;
	}
	
	/**
	 * Establece el pais.
	 *
	 * @param pais the pais to set
	 */
	public void setPais(String pais) {
		this.pais = pais;
	}
	
	/**
	 * Obtiene el provincia.
	 *
	 * @return the provincia
	 */
	public String getProvincia() {
		return provincia;
	}
	
	/**
	 * Establece el provincia.
	 *
	 * @param provincia el nuevo provincia
	 */
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	
	/**
	 * Obtiene el localidad.
	 *
	 * @return the localidad
	 */
	public String getLocalidad() {
		return localidad;
	}
	
	/**
	 * Establece el localidad.
	 *
	 * @param localidad the localidad to set
	 */
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	
	/**
	 * Obtiene el num solicitud.
	 *
	 * @return the numSolicitud
	 */
	public String getNumSolicitud() {
		return numSolicitud;
	}
	
	/**
	 * Establece el num solicitud.
	 *
	 * @param numSolicitud the numSolicitud to set
	 */
	public void setNumSolicitud(String numSolicitud) {
		this.numSolicitud = numSolicitud;
	}
	
	/**
	 * Obtiene el fecha sol.
	 *
	 * @return the fechaSol
	 */
	public Date getFechaSol() {
		return fechaSol;
	}
	
	/**
	 * Establece el fecha sol.
	 *
	 * @param fechaSol the fechaSol to set
	 */
	public void setFechaSol(Date fechaSol) {
		this.fechaSol = fechaSol;
	}
	
	/**
	 * Obtiene el tipo solicitud.
	 *
	 * @return the tipoSolicitud
	 */
	public char getTipoSolicitud() {
		return tipoSolicitud;
	}
	
	/**
	 * Establece el tipo solicitud.
	 *
	 * @param tipoSolicitud the tipoSolicitud to set
	 */
	public void setTipoSolicitud(char tipoSolicitud) {
		this.tipoSolicitud = tipoSolicitud;
	}
	
	/**
	 * Obtiene el num justificante.
	 *
	 * @return the numJustificante
	 */
	public String getNumJustificante() {
		return numJustificante;
	}
	
	/**
	 * Establece el num justificante.
	 *
	 * @param numJustificante the numJustificante to set
	 */
	public void setNumJustificante(String numJustificante) {
		this.numJustificante = numJustificante;
	}
	
	/**
	 * Obtiene el prov examen.
	 *
	 * @return the provExamen
	 */
	public String getProvExamen() {
		return provExamen;
	}
	
	/**
	 * Establece el prov examen.
	 *
	 * @param provExamen the provExamen to set
	 */
	public void setProvExamen(String provExamen) {
		this.provExamen = provExamen;
	}
	
	/**
	 * Obtiene el cuerpo asp.
	 *
	 * @return the cuerpoAsp
	 */
	public String getCuerpoAsp() {
		return cuerpoAsp;
	}
	
	/**
	 * Establece el cuerpo asp.
	 *
	 * @param cuerpoAsp the cuerpoAsp to set
	 */
	public void setCuerpoAsp(String cuerpoAsp) {
		this.cuerpoAsp = cuerpoAsp;
	}
	
	/**
	 * Obtiene el categ asp.
	 *
	 * @return the categAsp
	 */
	public String getCategAsp() {
		return categAsp;
	}
	
	/**
	 * Establece el categ asp.
	 *
	 * @param categAsp the categAsp to set
	 */
	public void setCategAsp(String categAsp) {
		this.categAsp = categAsp;
	}
	
	/**
	 * Obtiene el grup prof asp.
	 *
	 * @return the grupProfAsp
	 */
	public String getGrupProfAsp() {
		return grupProfAsp;
	}
	
	/**
	 * Establece el grup prof asp.
	 *
	 * @param grupProfAsp the grupProfAsp to set
	 */
	public void setGrupProfAsp(String grupProfAsp) {
		this.grupProfAsp = grupProfAsp;
	}
	
	/**
	 * Obtiene el dato A.
	 *
	 * @return the datoA
	 */
	public String getDatoA() {
		return datoA;
	}
	
	/**
	 * Establece el dato A.
	 *
	 * @param datoA the datoA to set
	 */
	public void setDatoA(String datoA) {
		this.datoA = datoA;
	}
	
	/**
	 * Obtiene el dato B.
	 *
	 * @return the datoB
	 */
	public String getDatoB() {
		return datoB;
	}
	
	/**
	 * Establece el dato B.
	 *
	 * @param datoB the datoB to set
	 */
	public void setDatoB(String datoB) {
		this.datoB = datoB;
	}
	
	/**
	 * Obtiene el dato C.
	 *
	 * @return the datoC
	 */
	public String getDatoC() {
		return datoC;
	}
	
	/**
	 * Establece el dato C.
	 *
	 * @param datoC the datoC to set
	 */
	public void setDatoC(String datoC) {
		this.datoC = datoC;
	}
	
	/**
	 * Obtiene el tasas.
	 *
	 * @return the tasas
	 */
	public Float getTasas() {
		return tasas;
	}
	
	/**
	 * Establece el tasas.
	 *
	 * @param tasas the tasas to set
	 */
	public void setTasas(Float tasas) {
		this.tasas = tasas;
	}
	
	/**
	 * Obtiene el reduc tasas.
	 *
	 * @return the reducTasas
	 */
	public String getReducTasas() {
		return reducTasas;
	}
	
	/**
	 * Establece el reduc tasas.
	 *
	 * @param reducTasas the reducTasas to set
	 */
	public void setReducTasas(String reducTasas) {
		this.reducTasas = reducTasas;
	}
	
	/**
	 * Obtiene el discapacitado.
	 *
	 * @return the idTipoDiscapacidad
	 */
	public String getDiscapacitado() {
		return discapacitado;
	}
	
	/**
	 * Establece el discapacitado.
	 *
	 * @param discapacitado el nuevo discapacitado
	 */
	public void setDiscapacitado(String discapacitado) {
		this.discapacitado = discapacitado;
	}
	
	/**
	 * Obtiene el grado disc.
	 *
	 * @return the gradoDisc
	 */
	public Integer getGradoDisc() {
		return gradoDisc;
	}
	
	/**
	 * Establece el grado disc.
	 *
	 * @param gradoDisc the gradoDisc to set
	 */
	public void setGradoDisc(Integer gradoDisc) {
		this.gradoDisc = gradoDisc;
	}
	
	/**
	 * Obtiene el adaptacion disc.
	 *
	 * @return the adaptacionDisc
	 */
	public String getAdaptacionDisc() {
		return adaptacionDisc;
	}
	
	/**
	 * Establece el adaptacion disc.
	 *
	 * @param adaptacionDisc the adaptacionDisc to set
	 */
	public void setAdaptacionDisc(String adaptacionDisc) {
		this.adaptacionDisc = adaptacionDisc;
	}
	
	/**
	 * Obtiene el reser disc.
	 *
	 * @return the reserDisc
	 */
	public String getReserDisc() {
		return reserDisc;
	}
	
	/**
	 * Establece el reser disc.
	 *
	 * @param reserDisc the reserDisc to set
	 */
	public void setReserDisc(String reserDisc) {
		this.reserDisc = reserDisc;
	}
	
	/**
	 * Obtiene el discap intel.
	 *
	 * @return the discapIntel
	 */
	public String getDiscapIntel() {
		return discapIntel;
	}
	
	/**
	 * Establece el discap intel.
	 *
	 * @param discapIntel the discapIntel to set
	 */
	public void setDiscapIntel(String discapIntel) {
		this.discapIntel = discapIntel;
	}
	
	/**
	 * Obtiene el titulo exigido.
	 *
	 * @return the tituloExigido
	 */
	public String getTituloExigido() {
		return tituloExigido;
	}
	
	/**
	 * Establece el titulo exigido.
	 *
	 * @param tituloExigido the tituloExigido to set
	 */
	public void setTituloExigido(String tituloExigido) {
		this.tituloExigido = tituloExigido;
	}
	
	/**
	 * Obtiene el titulos academicos.
	 *
	 * @return the titulosAcademicos
	 */
	public String getTitulosAcademicos() {
		return titulosAcademicos;
	}
	
	/**
	 * Establece el titulos academicos.
	 *
	 * @param titulosAcademicos the titulosAcademicos to set
	 */
	public void setTitulosAcademicos(String titulosAcademicos) {
		this.titulosAcademicos = titulosAcademicos;
	}
	
	/**
	 * Obtiene el forma acceso.
	 *
	 * @return the idFormaAcceso
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
	 * Obtiene el id solicitud.
	 *
	 * @return the idSolicitud
	 */
	public Long getIdSolicitud() {
		return idSolicitud;
	}
	
	/**
	 * Establece el id solicitud.
	 *
	 * @param idSolicitud the idSolicitud to set
	 */
	public void setIdSolicitud(Long idSolicitud) {
		this.idSolicitud = idSolicitud;
	}
	
	

}
