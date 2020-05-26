package es.map.ipsc.bean;

import java.util.ArrayList;
import es.map.ipsc.bean.CamposPropiosBean;
import es.map.ipsc.bean.DocumentoBean;


/**
 * El Class JustificanteBean.
 */
public class JustificanteBean {

	/** el num registro. */
	private String numRegistro;
	
	/** el fecha. */
	private String fecha;
	
	/** el hora. */
	private String hora;
	
	/** el oficina. */
	private String oficina;
	
	/** el centro gestor. */
	private String centroGestor;
	
	/** el nif. */
	private String nif;
	
	/** el primer apellido. */
	private String primerApellido;
	
	/** el segundo apellido. */
	private String segundoApellido;
	
	/** el nombre. */
	private String nombre;
	
	/** el provincia nacimiento. */
	private String provinciaNacimiento;
	
	/** el localidad nacimiento. */
	private String localidadNacimiento;
	
	/** el nacionalidad. */
	private String nacionalidad;
	
	/** el telefono. */
	private String telefono;
	
	/** el cuerpo escala. */
	private String cuerpoEscala;
	
	/** el especialidad. */
	private String especialidad;
	
	/** el forma acceso. */
	private String formaAcceso;
	
	/** el ministerio. */
	private String ministerio;
	
	/** el provincia examen. */
	private String provinciaExamen;
	
	/** el porcentaje discapacidad. */
	private String porcentajeDiscapacidad;
	
	/** el titulo oficial. */
	private String tituloOficial;
	
	/** el otros titulos. */
	private String otrosTitulos;
	
	/** el consentimiento. */
	private String consentimiento;
	
	/** el dirigido A. */
	private String dirigidoA;
	
	/** el num documentos. */
	private String numDocumentos;
	
	/** el firma solicitud. */
	private String firmaSolicitud;
	
	/** el firma justificante. */
	private String firmaJustificante;
	
	/** el ruta imagen. */
	private String rutaImagen;
	
	/** el solicitud reg. */
	private String solicitudReg;
	
	/** el extractos. */
	private String extractos;

	// Añadidos para la adaptación del nuevo formato
	/** el lista campos propios. */
	// de justificante de registro, similar al modelo 790.
	private ArrayList <CamposPropiosBean> listaCamposPropios;
	
	/** el codigo tasa. */
	private String codigoTasa;
	
	/** el numero justificante. */
	private String numeroJustificante;
	
	/** el anio convocatoria 1. */
	private String anioConvocatoria1;
	
	/** el anio convocatoria 2. */
	private String anioConvocatoria2;
	
	/** el anio convocatoria 3. */
	private String anioConvocatoria3;
	
	/** el anio convocatoria 4. */
	private String anioConvocatoria4;
	
	/** el dia nacimiento 1. */
	private String diaNacimiento1;
	
	/** el dia nacimiento 2. */
	private String diaNacimiento2;
	
	/** el mes nacimiento 1. */
	private String mesNacimiento1;
	
	/** el mes nacimiento 2. */
	private String mesNacimiento2;
	
	/** el anio nacimiento 1. */
	private String anioNacimiento1;
	
	/** el anio nacimiento 2. */
	private String anioNacimiento2;
	
	/** el sexo hombre. */
	private String sexoHombre;
	
	/** el sexo mujer. */
	private String sexoMujer;
	
	/** el correo electronicos. */
	private String correoElectronicos;
	
	/** el calle domicilio. */
	private String calleDomicilio;
	
	/** el codigo postal domicilio. */
	private String codigoPostalDomicilio;
	
	/** el municipio domicilio. */
	private String municipioDomicilio;
	
	/** el provincia domicilio. */
	private String provinciaDomicilio;
	
	/** el pais. */
	private String pais;
	
	/** el detalle discapacidad. */
	private String detalleDiscapacidad;
	
	/** el motivo oposcion */
	private String motivoOposicion;
	
	/** el dia fecha boe 1. */
	private String diaFechaBoe1;
	
	/** el dia fecha boe 2. */
	private String diaFechaBoe2;
	
	/** el mes fecha boe 1. */
	private String mesFechaBoe1;
	
	/** el mes fecha boe 2. */
	private String mesFechaBoe2;
	
	/** el anio fecha boe 1. */
	private String anioFechaBoe1;
	
	/** el anio fecha boe 2. */
	private String anioFechaBoe2;
	
	/** el tipo discapacidad. */
	private String tipoDiscapacidad;
	
	/** el telefono aux. */
	private String telefonoAux;
	
	/** el telefono 1. */
	private String telefono1;
	
	/** el ruta ficheros. */
	private String rutaFicheros;
	
	/** el nombre barcode N justificante. */
	private String nombreBarcodeNJustificante;
	
	/** el ruta logo. */
	private String rutaLogo;
	
	/** el ministerio convocatoria. */
	private String ministerioConvocatoria;
	
	/** el html registrado. */
	private String htmlRegistrado;
	
	/** el html hash. */
	private String htmlHash;
	
	/** el lista documentos. */
	private ArrayList<DocumentoBean> listaDocumentos;
	
	/** el encabezado. */
	private String encabezado;
	
	private String declaracion;
		
	/** el justificante pago bean. */
	private ArrayList<JustificantePagoBean> justificantePagoBean;
	
	/** el encabezado. */
	private String noConsentimiento;
	
	private String cumplimiento;
	
	private String  ejerce;
	
	private String ejerce2;
	
	private String autorizar;
	
	private String autoriza;
	
	private String noAutorizar;
	
	/**
	 * Obtiene el solicitud reg.
	 *
	 * @return el solicitud reg
	 */
	public String getSolicitudReg() {
		return solicitudReg;
	}
	
	/**
	 * Establece el solicitud reg.
	 *
	 * @param solicitudReg el nuevo solicitud reg
	 */
	public void setSolicitudReg(String solicitudReg) {
		this.solicitudReg = solicitudReg;
	}
	
	/**
	 * Obtiene el extractos.
	 *
	 * @return el extractos
	 */
	public String getExtractos() {
		return extractos;
	}
	
	/**
	 * Establece el extractos.
	 *
	 * @param extractos el nuevo extractos
	 */
	public void setExtractos(String extractos) {
		this.extractos = extractos;
	}
	
	/**
	 * Obtiene el ruta imagen.
	 *
	 * @return el ruta imagen
	 */
	public String getRutaImagen() {
		return rutaImagen;
	}
	
	/**
	 * Establece el ruta imagen.
	 *
	 * @param rutaImagen el nuevo ruta imagen
	 */
	public void setRutaImagen(String rutaImagen) {
		this.rutaImagen = rutaImagen;
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
	 * Obtiene el num documentos.
	 *
	 * @return el num documentos
	 */
	public String getNumDocumentos() {
		return numDocumentos;
	}
	
	/**
	 * Establece el num documentos.
	 *
	 * @param numDocumentos el nuevo num documentos
	 */
	public void setNumDocumentos(String numDocumentos) {
		this.numDocumentos = numDocumentos;
	}
	
	/**
	 * Obtiene el firma solicitud.
	 *
	 * @return el firma solicitud
	 */
	public String getFirmaSolicitud() {
		return firmaSolicitud;
	}
	
	/**
	 * Establece el firma solicitud.
	 *
	 * @param firmaSolicitud el nuevo firma solicitud
	 */
	public void setFirmaSolicitud(String firmaSolicitud) {
		this.firmaSolicitud = firmaSolicitud;
	}
	
	/**
	 * Obtiene el firma justificante.
	 *
	 * @return el firma justificante
	 */
	public String getFirmaJustificante() {
		return firmaJustificante;
	}
	
	/**
	 * Establece el firma justificante.
	 *
	 * @param firmaJustificante el nuevo firma justificante
	 */
	public void setFirmaJustificante(String firmaJustificante) {
		this.firmaJustificante = firmaJustificante;
	}
	
	/**
	 * Obtiene el num registro.
	 *
	 * @return el num registro
	 */
	public String getNumRegistro() {
		return numRegistro;
	}
	
	/**
	 * Establece el num registro.
	 *
	 * @param numRegistro el nuevo num registro
	 */
	public void setNumRegistro(String numRegistro) {
		this.numRegistro = numRegistro;
	}
	
	/**
	 * Obtiene el fecha.
	 *
	 * @return el fecha
	 */
	public String getFecha() {
		return fecha;
	}
	
	/**
	 * Establece el fecha.
	 *
	 * @param fecha el nuevo fecha
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	/**
	 * Obtiene el hora.
	 *
	 * @return el hora
	 */
	public String getHora() {
		return hora;
	}
	
	/**
	 * Establece el hora.
	 *
	 * @param hora el nuevo hora
	 */
	public void setHora(String hora) {
		this.hora = hora;
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
	 * Obtiene el titulo oficial.
	 *
	 * @return el titulo oficial
	 */
	public String getTituloOficial() {
		return tituloOficial;
	}
	
	/**
	 * Establece el titulo oficial.
	 *
	 * @param tituloOficial el nuevo titulo oficial
	 */
	public void setTituloOficial(String tituloOficial) {
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
	public void setListaCamposPropios(
			ArrayList<CamposPropiosBean> listaCamposPropios) {
		this.listaCamposPropios = listaCamposPropios;
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
	 * Obtiene el anio convocatoria 1.
	 *
	 * @return el anio convocatoria 1
	 */
	public String getAnioConvocatoria1() {
		return anioConvocatoria1;
	}
	
	/**
	 * Establece el anio convocatoria 1.
	 *
	 * @param anioConvocatoria1 el nuevo anio convocatoria 1
	 */
	public void setAnioConvocatoria1(String anioConvocatoria1) {
		this.anioConvocatoria1 = anioConvocatoria1;
	}
	
	/**
	 * Obtiene el anio convocatoria 2.
	 *
	 * @return el anio convocatoria 2
	 */
	public String getAnioConvocatoria2() {
		return anioConvocatoria2;
	}
	
	/**
	 * Establece el anio convocatoria 2.
	 *
	 * @param anioConvocatoria2 el nuevo anio convocatoria 2
	 */
	public void setAnioConvocatoria2(String anioConvocatoria2) {
		this.anioConvocatoria2 = anioConvocatoria2;
	}
	
	/**
	 * Obtiene el anio convocatoria 3.
	 *
	 * @return el anio convocatoria 3
	 */
	public String getAnioConvocatoria3() {
		return anioConvocatoria3;
	}
	
	/**
	 * Establece el anio convocatoria 3.
	 *
	 * @param anioConvocatoria3 el nuevo anio convocatoria 3
	 */
	public void setAnioConvocatoria3(String anioConvocatoria3) {
		this.anioConvocatoria3 = anioConvocatoria3;
	}
	
	/**
	 * Obtiene el anio convocatoria 4.
	 *
	 * @return el anio convocatoria 4
	 */
	public String getAnioConvocatoria4() {
		return anioConvocatoria4;
	}
	
	/**
	 * Establece el anio convocatoria 4.
	 *
	 * @param anioConvocatoria4 el nuevo anio convocatoria 4
	 */
	public void setAnioConvocatoria4(String anioConvocatoria4) {
		this.anioConvocatoria4 = anioConvocatoria4;
	}
	
	/**
	 * Obtiene el dia nacimiento 1.
	 *
	 * @return el dia nacimiento 1
	 */
	public String getDiaNacimiento1() {
		return diaNacimiento1;
	}
	
	/**
	 * Establece el dia nacimiento 1.
	 *
	 * @param diaNacimiento1 el nuevo dia nacimiento 1
	 */
	public void setDiaNacimiento1(String diaNacimiento1) {
		this.diaNacimiento1 = diaNacimiento1;
	}
	
	/**
	 * Obtiene el dia nacimiento 2.
	 *
	 * @return el dia nacimiento 2
	 */
	public String getDiaNacimiento2() {
		return diaNacimiento2;
	}
	
	/**
	 * Establece el dia nacimiento 2.
	 *
	 * @param diaNacimiento2 el nuevo dia nacimiento 2
	 */
	public void setDiaNacimiento2(String diaNacimiento2) {
		this.diaNacimiento2 = diaNacimiento2;
	}
	
	/**
	 * Obtiene el mes nacimiento 1.
	 *
	 * @return el mes nacimiento 1
	 */
	public String getMesNacimiento1() {
		return mesNacimiento1;
	}
	
	/**
	 * Establece el mes nacimiento 1.
	 *
	 * @param mesNacimiento1 el nuevo mes nacimiento 1
	 */
	public void setMesNacimiento1(String mesNacimiento1) {
		this.mesNacimiento1 = mesNacimiento1;
	}
	
	/**
	 * Obtiene el mes nacimiento 2.
	 *
	 * @return el mes nacimiento 2
	 */
	public String getMesNacimiento2() {
		return mesNacimiento2;
	}
	
	/**
	 * Establece el mes nacimiento 2.
	 *
	 * @param mesNacimiento2 el nuevo mes nacimiento 2
	 */
	public void setMesNacimiento2(String mesNacimiento2) {
		this.mesNacimiento2 = mesNacimiento2;
	}
	
	/**
	 * Obtiene el anio nacimiento 1.
	 *
	 * @return el anio nacimiento 1
	 */
	public String getAnioNacimiento1() {
		return anioNacimiento1;
	}
	
	/**
	 * Establece el anio nacimiento 1.
	 *
	 * @param anioNacimiento1 el nuevo anio nacimiento 1
	 */
	public void setAnioNacimiento1(String anioNacimiento1) {
		this.anioNacimiento1 = anioNacimiento1;
	}
	
	/**
	 * Obtiene el anio nacimiento 2.
	 *
	 * @return el anio nacimiento 2
	 */
	public String getAnioNacimiento2() {
		return anioNacimiento2;
	}
	
	/**
	 * Establece el anio nacimiento 2.
	 *
	 * @param anioNacimiento2 el nuevo anio nacimiento 2
	 */
	public void setAnioNacimiento2(String anioNacimiento2) {
		this.anioNacimiento2 = anioNacimiento2;
	}
	
	/**
	 * Obtiene el sexo hombre.
	 *
	 * @return el sexo hombre
	 */
	public String getSexoHombre() {
		return sexoHombre;
	}
	
	/**
	 * Establece el sexo hombre.
	 *
	 * @param sexoHombre el nuevo sexo hombre
	 */
	public void setSexoHombre(String sexoHombre) {
		this.sexoHombre = sexoHombre;
	}
	
	/**
	 * Obtiene el sexo mujer.
	 *
	 * @return el sexo mujer
	 */
	public String getSexoMujer() {
		return sexoMujer;
	}
	
	/**
	 * Establece el sexo mujer.
	 *
	 * @param sexoMujer el nuevo sexo mujer
	 */
	public void setSexoMujer(String sexoMujer) {
		this.sexoMujer = sexoMujer;
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
	 * Obtiene el dia fecha boe 1.
	 *
	 * @return el dia fecha boe 1
	 */
	public String getDiaFechaBoe1() {
		return diaFechaBoe1;
	}
	
	/**
	 * Establece el dia fecha boe 1.
	 *
	 * @param diaFechaBoe1 el nuevo dia fecha boe 1
	 */
	public void setDiaFechaBoe1(String diaFechaBoe1) {
		this.diaFechaBoe1 = diaFechaBoe1;
	}
	
	/**
	 * Obtiene el dia fecha boe 2.
	 *
	 * @return el dia fecha boe 2
	 */
	public String getDiaFechaBoe2() {
		return diaFechaBoe2;
	}
	
	/**
	 * Establece el dia fecha boe 2.
	 *
	 * @param diaFechaBoe2 el nuevo dia fecha boe 2
	 */
	public void setDiaFechaBoe2(String diaFechaBoe2) {
		this.diaFechaBoe2 = diaFechaBoe2;
	}
	
	/**
	 * Obtiene el mes fecha boe 1.
	 *
	 * @return el mes fecha boe 1
	 */
	public String getMesFechaBoe1() {
		return mesFechaBoe1;
	}
	
	/**
	 * Establece el mes fecha boe 1.
	 *
	 * @param mesFechaBoe1 el nuevo mes fecha boe 1
	 */
	public void setMesFechaBoe1(String mesFechaBoe1) {
		this.mesFechaBoe1 = mesFechaBoe1;
	}
	
	/**
	 * Obtiene el mes fecha boe 2.
	 *
	 * @return el mes fecha boe 2
	 */
	public String getMesFechaBoe2() {
		return mesFechaBoe2;
	}
	
	/**
	 * Establece el mes fecha boe 2.
	 *
	 * @param mesFechaBoe2 el nuevo mes fecha boe 2
	 */
	public void setMesFechaBoe2(String mesFechaBoe2) {
		this.mesFechaBoe2 = mesFechaBoe2;
	}
	
	/**
	 * Obtiene el anio fecha boe 1.
	 *
	 * @return el anio fecha boe 1
	 */
	public String getAnioFechaBoe1() {
		return anioFechaBoe1;
	}
	
	/**
	 * Establece el anio fecha boe 1.
	 *
	 * @param anioFechaBoe1 el nuevo anio fecha boe 1
	 */
	public void setAnioFechaBoe1(String anioFechaBoe1) {
		this.anioFechaBoe1 = anioFechaBoe1;
	}
	
	/**
	 * Obtiene el anio fecha boe 2.
	 *
	 * @return el anio fecha boe 2
	 */
	public String getAnioFechaBoe2() {
		return anioFechaBoe2;
	}
	
	/**
	 * Establece el anio fecha boe 2.
	 *
	 * @param anioFechaBoe2 el nuevo anio fecha boe 2
	 */
	public void setAnioFechaBoe2(String anioFechaBoe2) {
		this.anioFechaBoe2 = anioFechaBoe2;
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
	 * Obtiene el ruta ficheros.
	 *
	 * @return el ruta ficheros
	 */
	public String getRutaFicheros() {
		return rutaFicheros;
	}
	
	/**
	 * Establece el ruta ficheros.
	 *
	 * @param rutaFicheros el nuevo ruta ficheros
	 */
	public void setRutaFicheros(String rutaFicheros) {
		this.rutaFicheros = rutaFicheros;
	}
	
	/**
	 * Obtiene el nombre barcode N justificante.
	 *
	 * @return el nombre barcode N justificante
	 */
	public String getNombreBarcodeNJustificante() {
		return nombreBarcodeNJustificante;
	}
	
	/**
	 * Establece el nombre barcode N justificante.
	 *
	 * @param nombreBarcodeNJustificante el nuevo nombre barcode N justificante
	 */
	public void setNombreBarcodeNJustificante(String nombreBarcodeNJustificante) {
		this.nombreBarcodeNJustificante = nombreBarcodeNJustificante;
	}
	
	/**
	 * Obtiene el ruta logo.
	 *
	 * @return el ruta logo
	 */
	public String getRutaLogo() {
		return rutaLogo;
	}
	
	/**
	 * Establece el ruta logo.
	 *
	 * @param rutaLogo el nuevo ruta logo
	 */
	public void setRutaLogo(String rutaLogo) {
		this.rutaLogo = rutaLogo;
	}
	
	/**
	 * Obtiene el ministerio convocatoria.
	 *
	 * @return el ministerio convocatoria
	 */
	public String getMinisterioConvocatoria() {
		return ministerioConvocatoria;
	}
	
	/**
	 * Establece el ministerio convocatoria.
	 *
	 * @param ministerioConvocatoria el nuevo ministerio convocatoria
	 */
	public void setMinisterioConvocatoria(String ministerioConvocatoria) {
		this.ministerioConvocatoria = ministerioConvocatoria;
	}
	
	/**
	 * Obtiene el html registrado.
	 *
	 * @return el html registrado
	 */
	public String getHtmlRegistrado() {
		return htmlRegistrado;
	}
	
	/**
	 * Establece el html registrado.
	 *
	 * @param htmlRegistrado el nuevo html registrado
	 */
	public void setHtmlRegistrado(String htmlRegistrado) {
		this.htmlRegistrado = htmlRegistrado;
	}
	
	/**
	 * Obtiene el html hash.
	 *
	 * @return el html hash
	 */
	public String getHtmlHash() {
		return htmlHash;
	}
	
	/**
	 * Establece el html hash.
	 *
	 * @param htmlHash el nuevo html hash
	 */
	public void setHtmlHash(String htmlHash) {
		this.htmlHash = htmlHash;
	}
	
	/**
	 * Obtiene el lista documentos.
	 *
	 * @return el lista documentos
	 */
	public ArrayList<DocumentoBean> getListaDocumentos() {
		return listaDocumentos;
	}
	
	/**
	 * Establece el lista documentos.
	 *
	 * @param listaDocumentos el nuevo lista documentos
	 */
	public void setListaDocumentos(ArrayList<DocumentoBean> listaDocumentos) {
		this.listaDocumentos = listaDocumentos;
	}
	
	/**
	 * Obtiene el encabezado.
	 *
	 * @return el encabezado
	 */
	public String getEncabezado() {
		return encabezado;
	}
	
	/**
	 * Establece el encabezado.
	 *
	 * @param encabezado el nuevo encabezado
	 */
	public void setEncabezado(String encabezado) {
		this.encabezado = encabezado;
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
	 * Obtiene el justificante pago bean.
	 *
	 * @return el justificante pago bean
	 */
	public ArrayList<JustificantePagoBean> getJustificantePagoBean() {
		return justificantePagoBean;
	}
	
	/**
	 * Establece el justificante pago bean.
	 *
	 * @param justificantePagoBean el nuevo justificante pago bean
	 */
	public void setJustificantePagoBean(ArrayList<JustificantePagoBean> justificantePagoBean) {
		this.justificantePagoBean = justificantePagoBean;
	}

	public String getNoConsentimiento() {
		return noConsentimiento;
	}

	public void setNoConsentimiento(String noConsentimiento) {
		this.noConsentimiento = noConsentimiento;
	}

	public String getMotivoOposicion() {
		return motivoOposicion;
	}

	public void setMotivoOposicion(String motivoOposicion) {
		this.motivoOposicion = motivoOposicion;
	}

	public String getDeclaracion() {
		return declaracion;
	}

	public void setDeclaracion(String declaracion) {
		this.declaracion = declaracion;
	}

	public String getCumplimiento() {
		return cumplimiento;
	}

	public void setCumplimiento(String cumplimiento) {
		this.cumplimiento = cumplimiento;
	}

	public String getEjerce() {
		return ejerce;
	}

	public void setEjerce(String ejerce) {
		this.ejerce = ejerce;
	}

	public String getEjerce2() {
		return ejerce2;
	}

	public void setEjerce2(String ejerce2) {
		this.ejerce2 = ejerce2;
	}

	public String getAutorizar() {
		return autorizar;
	}

	public void setAutorizar(String autorizar) {
		this.autorizar = autorizar;
	}

	public String getAutoriza() {
		return autoriza;
	}

	public void setAutoriza(String autoriza) {
		this.autoriza = autoriza;
	}

	public String getNoAutorizar() {
		return noAutorizar;
	}

	public void setNoAutorizar(String noAutorizar) {
		this.noAutorizar = noAutorizar;
	}
	
}
