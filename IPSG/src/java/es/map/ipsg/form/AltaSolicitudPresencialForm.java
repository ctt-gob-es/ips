package es.map.ipsg.form;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import es.map.ips.common.spring.SpringErrors;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringMapping;
import es.map.ips.common.spring.SpringMessage;
import es.map.ipsg.bean.CamposPropiosBean;
import es.map.ipsg.bean.DocumentoSolicitudPresencialBean;
import es.map.ipsg.bean.PlantillaPropiosBean;
import es.map.ipsg.bean.SolicitudPropiosBean;
import es.map.ipsg.util.Constantes;
import es.map.ipsg.util.Validacion;

/**
 * AltaSolicitudPresencialForm.
 */
public class AltaSolicitudPresencialForm extends SpringForm {

	/** el id. */
	private String id;
	
	/** el datos solicitud. */
	private String datosSolicitud;
	
	/** el perfil usuario. */
	private String perfilUsuario;
	
	/** el menu. */
	private String menu;
	
	/** el accion. */
	private String accion;
	
	/** el cambios. */
	private String cambios;
	
	/** el submit. */
	private String submit;
	
	/** el requiere titulo. */
	private Boolean requiereTitulo;
	
	//Datos de la convocatoria
	
	/** el numero solicitud. */
	private String numeroSolicitud;
	
	/** el id convocatoria. */
	private String idConvocatoria;
	
	/** el ds convocatoria. */
	private String dsConvocatoria;
	
	/** el justificante pago. */
	private String justificantePago;
	
	/** el nif. */
	//Datos Personales
	private String nif;
	
	/** el nombre. */
	private String nombre;
	
	/** el primer apellido. */
	private String primerApellido;
	
	/** el segundo apellido. */
	private String segundoApellido;
	
	/** el fecha nacimiento. */
	private String fechaNacimiento;
	
	/** el localidad nacimiento. */
	private String localidadNacimiento;
	
	/** el nacionalidad. */
	private String nacionalidad;
	
	/** el id provincia nacimiento. */
	private String idProvinciaNacimiento;
	
	/** el sexo. */
	private String  sexo;
	
	/** el email. */
	private String email;
	
	/** el calle domicilio. */
	//Datos del Domicilio
	private String calleDomicilio;
	
	/** el municipio domicilio. */
	private String municipioDomicilio;
	
	/** el id pais. */
	private String idPais;
	
	/** el id provincia domicilio. */
	private String idProvinciaDomicilio;
	
	/** el telefono 1. */
	private String telefono1;
	
	/** el telefono 2. */
	private String telefono2; 
	
	/** el codigo postal. */
	private String codigoPostal;
	
	/** el id especialidad. */
	//Datos de la Solicitud
	private String idEspecialidad;
	
	/** el id provincia examen. */
	private String idProvinciaExamen;
	
	/** el fecha solicitud. */
	private String fechaSolicitud;
	
	/** el ejercicio. */
	private String ejercicio; 
	
	/** el id tipo discapacidad. */
	private String idTipoDiscapacidad;
	
	/** el porcentaje discapacidad. */
	private String porcentajeDiscapacidad;
	
	/** el ck reserva discapacidad. */
	private Boolean  ckReservaDiscapacidad;
	
	/** el adaptacion discapacidad. */
	private String adaptacionDiscapacidad;
	
	/** el id titulo. */
	//Datos de Titulos
	private String idTitulo;
	
	/** el otros titulos. */
	private String otrosTitulos;
	
	/** el datos A. */
	//Datos a Consignar
	private String datosA;
	
	/** el datos B. */
	private String datosB;
	
	/** el datos C. */
	private String datosC;
	
	/** el lista campos propios bean. */
	ArrayList<CamposPropiosBean> listaCamposPropiosBean;
	
	/** el lista plantilla propios bean. */
	private ArrayList<PlantillaPropiosBean> listaPlantillaPropiosBean;
	
	/** el lista solicitud propios bean. */
	private ArrayList<SolicitudPropiosBean> listaSolicitudPropiosBean;

	/** el id tipo pago. */
	//Datos de Pago
	private String idTipoPago;
	
	/** el id pago solicitud. */
	private String idPagoSolicitud;
	
	/** el fecha pago. */
	private String fechaPago;
	
	/** el importe. */
	private String importe;
	
	/** el importe decimal. */
	private String importeDecimal;
	
	/** el nrc pago. */
	private String nrcPago;
	
	/** el id entidad bancaria. */
	private String idEntidadBancaria;
	
	/** el id motivos ex. */
	private String idMotivosEx;
	
	/** el id motivos red. */
	private String idMotivosRed;
	
	/** el comunidad FN. */
	private String comunidadFN;
	
	/** el comunidad DD. */
	private String comunidadDD;
	
	/** el numero titulo FN. */
	private String numeroTituloFN;
	
	/** el id registro solicitud. */
	//Datos de Registro
	private String idRegistroSolicitud;	
	
	/** el numero registro. */
	private String numeroRegistro;
	
	/** el fecha registro. */
	private String fechaRegistro;
	
	/** el oficina registro. */
	private String oficinaRegistro;
	
	/** el ck consentimiento. */
	// Datos de consentimiento
	private Boolean ckConsentimiento;
	
	/** el Ob nif. */
	// Datos obligatorios
	private Character ObNif;
	
	/** el Ob primer apellido. */
	private Character ObPrimerApellido;
	
	/** el Ob segundo apellido. */
	private Character ObSegundoApellido;
	
	/** el Ob nombre. */
	private Character ObNombre;
	
	/** el Ob fecha nacimiento. */
	private Character ObFechaNacimiento;
	
	/** el Ob sexo. */
	private Character ObSexo;
	
	/** el Ob nacionalidad. */
	private Character ObNacionalidad;
	
	/** el Ob correo electronico. */
	private Character ObCorreoElectronico;
	
	/** el Ob provincia. */
	private Character ObProvincia;
	
	/** el Ob pais. */
	private Character ObPais;
	
	/** el Ob telefono. */
	private Character ObTelefono;
	
	/** el Ob via. */
	private Character ObVia;
	
	/** el Ob municipio. */
	private Character ObMunicipio;
	
	/** el Ob especialidad. */
	private Character ObEspecialidad;
	
	/** el Ob provincia examen. */
	private Character ObProvinciaExamen;
	
	/** el Ob tipo discapacidad. */
	private Character ObTipoDiscapacidad;
	
	/** el Ob porcentaje discapacidad. */
	private Character ObPorcentajeDiscapacidad;
	
	/** el Ob reserva discapacidad. */
	private Character ObReservaDiscapacidad;
	
	/** el Ob detalle discapacidad. */
	private Character ObDetalleDiscapacidad;
	
	/** el Ob titulos exigidos. */
	private Character ObTitulosExigidos;
	
	/** el Ob otros titulos. */
	private Character ObOtrosTitulos;
	
	/** el Ob datos A. */
	private Character ObDatosA;
	
	/** el Ob datos B. */
	private Character ObDatosB;
	
	/** el Ob datos C. */
	private Character ObDatosC;
	
	/** La constante STRING_ERRORIDCONVOCATORIA. */
	private static final String STRING_ERRORIDCONVOCATORIA = "errorIdConvocatoria";
	
	/** La constante STRING_DSERRORNIFOB. */
	private static final String STRING_DSERRORNIFOB = "dsErrorNifOb";
	
	/** La constante STRING_ERRORTELEFONO. */
	private static final String STRING_ERRORTELEFONO = "errorTelefono";
	
	/** La constante STRING_ERRORIMPORTE. */
	private static final String STRING_ERRORIMPORTE = "errorImporte";
	
	/** La constante STRING_DSERRORPORCENTAJEDISCAPACIDAD. */
	private static final String STRING_DSERRORPORCENTAJEDISCAPACIDAD = "dsErrorPorcentajeDiscapacidad";
	
	/** La constante STRING_DSERRORNUMEROJUSTIFICANTE. */
	private static final String STRING_DSERRORNUMEROJUSTIFICANTE = "dsErrorNumeroJustificante";

	/** el file. */
	private MultipartFile[] file;
	
	/** el datos fichero. */
	private String[] datosFichero;
	
	/** el num ficheros. */
	private Integer numFicheros;
	
	/** el documento HTML. */
	private String documentoHTML;
	
	/** el documento solicitud presencial bean. */
	private DocumentoSolicitudPresencialBean[] documentoSolicitudPresencialBean;
	
	/** el lista text areas. */
	private CamposPropiosBean[] listaTextAreas;
	
	/**
	 * Obtiene el lista plantilla propios bean.
	 *
	 * @return el lista plantilla propios bean
	 */
	public ArrayList<PlantillaPropiosBean> getListaPlantillaPropiosBean() {
		return listaPlantillaPropiosBean;
	}

	/**
	 * Establece el lista plantilla propios bean.
	 *
	 * @param listaPlantillaPropiosBean el nuevo lista plantilla propios bean
	 */
	public void setListaPlantillaPropiosBean(
			ArrayList<PlantillaPropiosBean> listaPlantillaPropiosBean) {
		this.listaPlantillaPropiosBean = listaPlantillaPropiosBean;
	}

	/**
	 * Obtiene el lista solicitud propios bean.
	 *
	 * @return el lista solicitud propios bean
	 */
	public ArrayList<SolicitudPropiosBean> getListaSolicitudPropiosBean() {
		return listaSolicitudPropiosBean;
	}

	/**
	 * Establece el lista solicitud propios bean.
	 *
	 * @param listaSolicitudPropiosBean el nuevo lista solicitud propios bean
	 */
	public void setListaSolicitudPropiosBean(
			ArrayList<SolicitudPropiosBean> listaSolicitudPropiosBean) {
		this.listaSolicitudPropiosBean = listaSolicitudPropiosBean;
	}
	
	/**
	 * Obtiene el lista campos propios bean.
	 *
	 * @return el lista campos propios bean
	 */
	public ArrayList<CamposPropiosBean> getListaCamposPropiosBean() {
		return listaCamposPropiosBean;
	}

	/**
	 * Establece el lista campos propios bean.
	 *
	 * @param listaCamposPropiosBean el nuevo lista campos propios bean
	 */
	public void setListaCamposPropiosBean(
			ArrayList<CamposPropiosBean> listaCamposPropiosBean) {
		this.listaCamposPropiosBean = listaCamposPropiosBean;
	}
	
	
	/**
	 * Obtiene el codigo postal.
	 *
	 * @return el codigo postal
	 */
	public String getCodigoPostal() {
		return codigoPostal;
	}

	/**
	 * Establece el codigo postal.
	 *
	 * @param codigoPostal el nuevo codigo postal
	 */
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	/**
	 * Obtiene el datos solicitud.
	 *
	 * @return datosSolicitud String
	 */
	public String getDatosSolicitud() {
		return datosSolicitud;
	}

	/**
	 * Establece el datos solicitud.
	 *
	 * @param datosSolicitud String
	 */
	public void setDatosSolicitud(String datosSolicitud) {
		this.datosSolicitud = datosSolicitud;
	}

	/**
	 * Obtiene el submit.
	 *
	 * @return  submit String
	 */
	public String getSubmit() {
		return submit;
	}

	/**
	 * Establece el submit.
	 *
	 * @param submit String
	 */
	public void setSubmit(String submit) {
		this.submit = submit;
	}

	/**
	 * Obtiene el perfil usuario.
	 *
	 * @return  perfilUsuario String
	 */
	public String getPerfilUsuario() {
		return perfilUsuario;
	}

	/**
	 * Establece el perfil usuario.
	 *
	 * @param perfilUsuario String
	 */
	public void setPerfilUsuario(String perfilUsuario) {
		this.perfilUsuario = perfilUsuario;
	}

	/**
	 * Obtiene el accion.
	 *
	 * @return accion String
	 */
	public String getAccion() {
		return accion;
	}

	/**
	 * Establece el accion.
	 *
	 * @param accion String
	 */
	public void setAccion(String accion) {
		this.accion = accion;
	}



	/**
	 * Obtiene el id convocatoria.
	 *
	 * @return idConvocatoria Long
	 */
	public String getIdConvocatoria() {
		return idConvocatoria;
	}

	/**
	 * Establece el id convocatoria.
	 *
	 * @param idConvocatoria Long
	 */
	public void setIdConvocatoria(String idConvocatoria) {
		this.idConvocatoria = idConvocatoria;
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
	 * @return primerApellido String
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
	 * Obtiene el fecha nacimiento.
	 *
	 * @return fechaNacimiento String
	 */
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	/**
	 * Establece el fecha nacimiento.
	 *
	 * @param fechaNacimiento String
	 */
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
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
	 * @return nacionalidad String
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
	 * Obtiene el sexo.
	 *
	 * @return sexo String
	 */
	public String getSexo() {
		return sexo;
	}

	/**
	 * Establece el sexo.
	 *
	 * @param sexo String
	 */
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	/**
	 * Obtiene el email.
	 *
	 * @return email String
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
	 * @return  calleDomicilio String
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
	 * Obtiene el telefono 1.
	 *
	 * @return telefono String
	 */
	public String getTelefono1() {
		return telefono1;
	}

	/**
	 * Establece el telefono 1.
	 *
	 * @param telefono String
	 */
	public void setTelefono1(String telefono) {
		this.telefono1 = telefono;
	}
	
	

	/**
	 * Obtiene el telefono 2.
	 *
	 * @return el telefono 2
	 */
	public String getTelefono2() {
		return telefono2;
	}

	/**
	 * Establece el telefono 2.
	 *
	 * @param telefono2 el nuevo telefono 2
	 */
	public void setTelefono2(String telefono2) {
		this.telefono2 = telefono2;
	}

	/**
	 * Obtiene el fecha solicitud.
	 *
	 * @return fechaSolicitud String
	 */
	public String getFechaSolicitud() {
		return fechaSolicitud;
	}

	/**
	 * Establece el fecha solicitud.
	 *
	 * @param fechaSolicitud String
	 */
	public void setFechaSolicitud(String fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
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
	 * @return porcentajeDiscapacidad Integer
	 */
	public String getPorcentajeDiscapacidad() {
		return porcentajeDiscapacidad;
	}

	/**
	 * Establece el porcentaje discapacidad.
	 *
	 * @param porcentajeDiscapacidad Integer
	 */
	public void setPorcentajeDiscapacidad(String porcentajeDiscapacidad) {
		this.porcentajeDiscapacidad = porcentajeDiscapacidad;
	}

	/**
	 * Obtiene el adaptacion discapacidad.
	 *
	 * @return  adaptacionDiscapacidad String
	 */
	public String getAdaptacionDiscapacidad() {
		return adaptacionDiscapacidad;
	}

	/**
	 * Establece el adaptacion discapacidad.
	 *
	 * @param adaptacionDiscapacidad String
	 */
	public void setAdaptacionDiscapacidad(String adaptacionDiscapacidad) {
		this.adaptacionDiscapacidad = adaptacionDiscapacidad;
	}

	/**
	 * Obtiene el otros titulos.
	 *
	 * @return otrosTitulos String
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
	 * @return datosC String
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
		 * Obtiene el cambios.
		 *
		 * @return cambios String
		 */
	public String getCambios() {
		return cambios;
	}

	/**
	 * Establece el cambios.
	 *
	 * @param cambios String
	 */
	public void setCambios(String cambios) {
		this.cambios = cambios;
	}

	/**
	 * Obtiene el id pais.
	 *
	 * @return idPais String
	 */
	public String getIdPais() {
		return idPais;
	}

	/**
	 * Establece el id pais.
	 *
	 * @param idPais String
	 */
	public void setIdPais(String idPais) {
		this.idPais = idPais;
	}

	/** 
	 * @return idProvinciaNacimiento String
	 */
	public String getIdProvinciaNacimiento() {
		return idProvinciaNacimiento;
	}

	/**
	 * Establece el id provincia nacimiento.
	 *
	 * @param idProvinciaNacimiento String
	 */
	public void setIdProvinciaNacimiento(String idProvinciaNacimiento) {
		this.idProvinciaNacimiento = idProvinciaNacimiento;
	}

	/**
	 * Obtiene el id provincia domicilio.
	 *
	 * @return idProvinciaDomicilio String
	 */
	public String getIdProvinciaDomicilio() {
		return idProvinciaDomicilio;
	}

	/**
	 * Establece el id provincia domicilio.
	 *
	 * @param idProvinciaDomicilio String
	 */
	public void setIdProvinciaDomicilio(String idProvinciaDomicilio) {
		this.idProvinciaDomicilio = idProvinciaDomicilio;
	}

	/**
	 * Obtiene el id provincia examen.
	 *
	 * @return idProvinciaExamen String
	 */
	public String getIdProvinciaExamen() {
		return idProvinciaExamen;
	}

	/**
	 * Establece el id provincia examen.
	 *
	 * @param idProvinciaExamen String
	 */
	public void setIdProvinciaExamen(String idProvinciaExamen) {
		this.idProvinciaExamen = idProvinciaExamen;
	}

	/**
	 * Obtiene el id especialidad.
	 *
	 * @return idEspecialidad String
	 */
	public String getIdEspecialidad() {
		return idEspecialidad;
	}

	/**
	 * Establece el id especialidad.
	 *
	 * @param idEspecialidad String
	 */
	public void setIdEspecialidad(String idEspecialidad) {
		this.idEspecialidad = idEspecialidad;
	}

	/**
	 * Obtiene el id tipo discapacidad.
	 *
	 * @return idTipoDiscapacidad String
	 */
	public String getIdTipoDiscapacidad() {
		return idTipoDiscapacidad;
	}

	/**
	 * Establece el id tipo discapacidad.
	 *
	 * @param idTipoDiscapacidad String
	 */
	public void setIdTipoDiscapacidad(String idTipoDiscapacidad) {
		this.idTipoDiscapacidad = idTipoDiscapacidad;
	}



	/**
	 * Obtiene el id titulo.
	 *
	 * @return idTitulo String
	 */ 
	public String getIdTitulo() {
		return idTitulo;
	}

	/**
	 * Establece el id titulo.
	 *
	 * @param idTitulo String
	 */
	public void setIdTitulo(String idTitulo) {
		this.idTitulo = idTitulo;
	}

	/**
	 * Obtiene el id tipo pago.
	 *
	 * @return idTipoPago String
	 */
	public String getIdTipoPago() {
		return idTipoPago;
	}

	/**
	 * Establece el id tipo pago.
	 *
	 * @param idTipoPago String
	 */
	public void setIdTipoPago(String idTipoPago) {
		this.idTipoPago = idTipoPago;
	}

	/**
	 * Obtiene el id entidad bancaria.
	 *
	 * @return idEntidadBancaria String
	 */
	public String getIdEntidadBancaria() {
		return idEntidadBancaria;
	}

	/** 
	 * @param idEntidadBancaria String
	 */
	public void setIdEntidadBancaria(String idEntidadBancaria) {
		this.idEntidadBancaria = idEntidadBancaria;
	}

	/**
	 * Obtiene el fecha pago.
	 *
	 * @return fechaPago String
	 */
	public String getFechaPago() {
		return fechaPago;
	}

	/**
	 * Establece el fecha pago.
	 *
	 * @param fechaPago String
	 */
	public void setFechaPago(String fechaPago) {
		this.fechaPago = fechaPago;
	}

	/**
	 * Obtiene el importe.
	 *
	 * @return importe String
	 */
	public String getImporte() {
		return importe;
	}

	/**
	 * Establece el importe.
	 *
	 * @param importe String
	 */
	public void setImporte(String importe) {
		this.importe = importe;
	}
	
	
	

	/**
	 * Obtiene el importe decimal.
	 *
	 * @return el importe decimal
	 */
	public String getImporteDecimal() {
		return importeDecimal;
	}

	/**
	 * Establece el importe decimal.
	 *
	 * @param importeDecimal el nuevo importe decimal
	 */
	public void setImporteDecimal(String importeDecimal) {
		this.importeDecimal = importeDecimal;
	}

	/**
	 * Obtiene el nrc pago.
	 *
	 * @return String
	 */
	public String getNrcPago() {
		return nrcPago;
	}

	/**
	 * Establece el nrc pago.
	 *
	 * @param nrcPago String
	 */
	public void setNrcPago(String nrcPago) {
		this.nrcPago = nrcPago;
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
	 * Obtiene el menu.
	 *
	 * @return  menu String
	 */
	public String getMenu() {
		return menu;
	}

	/**
	 * Establece el menu.
	 *
	 * @param menu String
	 */
	public void setMenu(String menu) {
		this.menu = menu;
	}
	
	/**
	 * Obtiene el oficina registro.
	 *
	 * @return oficinaRegistro String
	 */ 
	public String getOficinaRegistro() {
		return oficinaRegistro;
	}

	/**
	 * Establece el oficina registro.
	 *
	 * @param oficinaRegistro String
	 */
	public void setOficinaRegistro(String oficinaRegistro) {
		this.oficinaRegistro = oficinaRegistro;
	}
	
	/**
	 * Validaciones del formulario de solicitudes.
	 *
	 * @param mapping ActionMapping
	 * @param request HttpServletRequest
	 * @return SpringErrors SpringErrors
	 */
	public SpringErrors validate(SpringMapping mapping,	HttpServletRequest request) {
		SpringErrors SpringErrors = new SpringErrors();
		Validacion validacion = new Validacion();

		String idPagoEfectivo = String.valueOf(Constantes.TIPO_PAGO_EFECTIVO_INTEGER);
		request.setAttribute("idTipoPagoN", idPagoEfectivo);
		
		// obtencion de atributos datosA, datosB y datosC
		// ! OJO ! obtenidos de forma estatica, hay que estudiar la posibilidad de forma dinamica
		if( listaCamposPropiosBean!=null && !listaCamposPropiosBean.isEmpty()) {
			for(int i=0;i<listaCamposPropiosBean.size();i++) {
				Long idCampo = listaCamposPropiosBean.get(i).getId();
				String valorVista = listaCamposPropiosBean.get(i).getValorVista();
				if (idCampo!=null) {
					Integer idCampoInt = (int) (long) idCampo;
					if (valorVista!=null) {
						switch (idCampoInt) {
						case 1:
							this.datosA = valorVista;
							break;
						case 2:
							this.datosB = valorVista;
							break;
						case 3:
							this.datosC = valorVista;
							break;
						default:
							break;
						}
					}
				}
			}
		}

		if("GUARDAR".equalsIgnoreCase(accion) || "MODIFICAR".equalsIgnoreCase(accion) || "BUSCAR".equals(accion))
		{
			//Es un campo Obligatorio 
			if(idConvocatoria == null || idConvocatoria.equals(""))
			{
				request.setAttribute(STRING_ERRORIDCONVOCATORIA, STRING_ERRORIDCONVOCATORIA);
				SpringErrors.add("dsErrorIdConvocatoria", new SpringMessage("field.solicitudPresencial.error.convocatoriaRequerido"));

			}else
			{
				if(!validacion.isNumeric(idConvocatoria))
				{
					request.setAttribute(STRING_ERRORIDCONVOCATORIA, STRING_ERRORIDCONVOCATORIA);
					SpringErrors.add("dsErrorIdConvocatoria", new SpringMessage("field.solicitudPresencial.error.idConvocatoria"));
				}	
				else
				{
					// VALIDACION CAMPOS OBLIGATORIOS POR CONVOCATORIA

					if((ObNif != null) && (ObNif.equals('S'))){
						if(nif == null || nif.equals("")){
							SpringErrors.add(STRING_DSERRORNIFOB, new SpringMessage("field.solicitudPresencial.error.nifOb"));
						}else{
							String letranie = nif.substring(0,1);
							if("X".equals(letranie) || "Y".equals(letranie) || "Z".equals(letranie)){
								boolean result = validacion.nieValidate(nif);
								if(result == false){	
									SpringErrors.add(STRING_DSERRORNIFOB, new SpringMessage("usuario.errors.dni1"));
								}
							}else{
								boolean  result = validacion.nifValidate(nif);
								if(result == false){
									SpringErrors.add(STRING_DSERRORNIFOB, new SpringMessage("usuario.errors.dni2"));
								}
							}
						}
					}
					if((ObNombre != null) && (ObNombre.equals('S')) && (nombre == null || nombre.equals(""))){
						
						SpringErrors.add("dsNombreOb", new SpringMessage("field.solicitudPresencial.error.nombreOb"));
						
					}
					if((ObPrimerApellido != null) && (ObPrimerApellido.equals('S')) && (primerApellido == null || primerApellido.equals(""))){
						
							SpringErrors.add("dsPrimerApellidoOb", new SpringMessage("field.solicitudPresencial.error.primerApellidoOb"));
						
					}	
					if((ObSegundoApellido != null) && (ObSegundoApellido.equals('S')) && (segundoApellido == null || segundoApellido.equals(""))){
						
							SpringErrors.add("dsSegundoApellidoOb", new SpringMessage("field.solicitudPresencial.error.segundoApellidoOb"));
						
					}

					if((ObFechaNacimiento != null) && (ObFechaNacimiento.equals('S')) && (fechaNacimiento == null || fechaNacimiento.equals(""))){
						
							SpringErrors.add("dsErrorfechaNacimientoOb", new SpringMessage("field.solicitudPresencial.error.fechaNacOb"));
						
					}	

					if((ObNacionalidad != null) && (ObNacionalidad.equals('S')) && (nacionalidad == null || nacionalidad.equals(""))){
						
							SpringErrors.add("dsNacionalidadOb", new SpringMessage("field.solicitudPresencial.error.nacionalidadOb"));
						
					}

					if((ObSexo != null) && (ObSexo.equals('S')) && (sexo == null || sexo.equals(""))){
						
							SpringErrors.add("dsSexoOb", new SpringMessage("field.solicitudPresencial.error.sexoOb"));
						
					}	

					if((ObCorreoElectronico != null) && (ObCorreoElectronico.equals('S')) && (email == null || email.equals(""))){
						
							SpringErrors.add("dsCorreoOb", new SpringMessage("field.solicitudPresencial.error.correoOb"));
						
					}	
					if((ObVia != null) && (ObVia.equals('S')) && (calleDomicilio == null || calleDomicilio.equals(""))){
						
							SpringErrors.add("dsViaOb", new SpringMessage("field.solicitudPresencial.error.viaOb"));
						
					}	
					if((ObMunicipio != null) && (ObMunicipio.equals('S')) && (municipioDomicilio == null || municipioDomicilio.equals(""))){
						
							SpringErrors.add("dsmunicipioOb", new SpringMessage("field.solicitudPresencial.error.municipioOb"));
						
					}
					if((ObTelefono != null) && (ObTelefono.equals('S')) && ((telefono1 == null || telefono1.equals("")) && (telefono2 == null || telefono2.equals("")))){
						// si ambos telefonos estan vacios y dicho campo es obligatorio-> error
						
						
							SpringErrors.add("dsTelefonoOb", new SpringMessage("field.solicitudPresencial.error.telefonoOb"));
						
					}
					if((ObPais != null) && (ObPais.equals('S')) && (idPais == null || idPais.equals(""))){
						
							SpringErrors.add("dsPaisOb", new SpringMessage("field.solicitudPresencial.error.paisOb"));
						
					}
					if((ObProvincia != null) && (ObProvincia.equals('S')) && ((idProvinciaDomicilio == null || idProvinciaDomicilio.equals("")) && (idPais != null && idPais.equals("1")))){
						
							SpringErrors.add("dsProvDomicilioOb", new SpringMessage("field.solicitudPresencial.error.provinciaDomicilioOb"));
						
					}	

					if((ObEspecialidad != null) && (ObEspecialidad.equals('S')) && (idEspecialidad == null || idEspecialidad.equals(""))){
						
							SpringErrors.add("dsEspecialidadOb", new SpringMessage("field.solicitudPresencial.error.especialidadOb"));
						
					}	
					if((ObProvinciaExamen != null) && (ObProvinciaExamen.equals('S')) && (idProvinciaExamen == null || idProvinciaExamen.equals(""))){
						
							SpringErrors.add("dsProvinciaExamenOb", new SpringMessage("field.solicitudPresencial.error.provinciaExamenOb"));
						
					}
					if((ObTipoDiscapacidad != null) && (ObTipoDiscapacidad.equals('S')) && (idTipoDiscapacidad == null || idTipoDiscapacidad.equals(""))){
						
							SpringErrors.add("dsTipoDiscapacidadOb", new SpringMessage("field.solicitudPresencial.error.tipoDiscapacidadOb"));
						
					}
					if((ObPorcentajeDiscapacidad != null) && (ObPorcentajeDiscapacidad.equals('S')) && (porcentajeDiscapacidad == null || porcentajeDiscapacidad.equals(""))){
						
							SpringErrors.add("dsPorcentajeDicapacidadOb", new SpringMessage("field.solicitudPresencial.error.porcentajeDiscapacidadOb"));
						
					}

					if((ObReservaDiscapacidad != null) && (ObReservaDiscapacidad.equals('S')) && (ckReservaDiscapacidad == false)){
						
							SpringErrors.add("dsReservaDiscapOb", new SpringMessage("field.solicitudPresencial.error.reservaDiscapacidadOb"));
						
					}

					if((ObDetalleDiscapacidad != null) && (ObDetalleDiscapacidad.equals('S')) && (adaptacionDiscapacidad == null || adaptacionDiscapacidad.equals(""))){
						
							SpringErrors.add("dsDetalleDiscapOb", new SpringMessage("field.solicitudPresencial.error.detalleDiscapacidadOb"));
						
					}

					if((ObTitulosExigidos != null) && (ObTitulosExigidos.equals('S')) && (idTitulo == null || idTitulo.equals(""))){
						
							SpringErrors.add("dsTitulosExOb", new SpringMessage("field.solicitudPresencial.error.titulosExigidosOb"));
						
					}

					if((ObOtrosTitulos != null) && (ObOtrosTitulos.equals('S')) && (otrosTitulos == null || otrosTitulos.equals(""))){
						
							SpringErrors.add("dsOtrosTitulosOb", new SpringMessage("field.solicitudPresencial.error.otrosTitulosOb"));
						
					}

					if((ObDatosA != null) && (ObDatosA.equals('S')) && (datosA == null || datosA.equals(""))){
						
							SpringErrors.add("dsDatosAOb", new SpringMessage("field.solicitudPresencial.error.datosAOb"));
						
					}

					if((ObDatosB != null) && (ObDatosB.equals('S')) && (datosB == null || datosB.equals(""))){
						
							SpringErrors.add("dsDatosBOb", new SpringMessage("field.solicitudPresencial.error.datosBOb"));
						
					}

					if((ObDatosC != null) && (ObDatosC.equals('S')) && (datosC == null || datosC.equals(""))){
						
							SpringErrors.add("dsDatosCOb", new SpringMessage("field.solicitudPresencial.error.datosCOb"));
						
					}

				}	
			}


			// TODOS LOS CAMPOS DEL REGISTRO SON OBLIGATORIOS, EXCEPTO EL NUMERO DE REGISTRO Y LA OFICINA		
	
			if(fechaRegistro == null || fechaRegistro.equals("")){
				SpringErrors.add("dsFechaRegOb", new SpringMessage("field.solicitudPresencial.error.fechaRegOb"));
			}


			//Validamos los formatos de los campos
			if(fechaNacimiento != null && !fechaNacimiento.equalsIgnoreCase("") && !validacion.isFechaValida(fechaNacimiento))
			{
				SpringErrors.add("dsErrorfechaNacimiento", new SpringMessage("field.solicitudPresencial.error.fechaNacimiento"));
			}	
			
			if(telefono1 != null && !telefono1.equals("") && !validacion.isNumeric(telefono1))
			{
				request.setAttribute(STRING_ERRORTELEFONO, STRING_ERRORTELEFONO);
				SpringErrors.add("dsErrorTelefono", new SpringMessage("field.solicitudPresencial.error.telefono"));
			}
			if(telefono2 != null && !telefono2.equals("") && !validacion.isNumeric(telefono2))
			{
				request.setAttribute(STRING_ERRORTELEFONO, STRING_ERRORTELEFONO);
				SpringErrors.add("dsErrorTelefono", new SpringMessage("field.solicitudPresencial.error.telefono"));
			}
			if(ejercicio != null && !ejercicio.equals("") && !validacion.isNumeric(ejercicio))
			{
				request.setAttribute("errorEjercicio", "errorEjercicio");
				SpringErrors.add("dsErrorEjercicio", new SpringMessage("field.solicitudPresencial.error.ejercicio"));
			}
			if(fechaPago != null && !fechaPago.equals("") && !validacion.isFechaValida(fechaPago))
			{
				request.setAttribute("errorfechaPago", "errorfechaPago");
				SpringErrors.add("dsErrorfechaPago", new SpringMessage("field.solicitudPresencial.error.fechaPago"));

			}
			else if(fechaPago != null && !fechaPago.equals(""))
			{
				try{
					SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
					Date dFechaPago = formatoDelTexto.parse(fechaPago);
					Date fechaHoy = new Date();


					if(dFechaPago!=null && dFechaPago.after(fechaHoy)){
						
							request.setAttribute("errorfechaPagoMayor", "errorfechaPagoMayor");
							SpringErrors.add("dsErrorfechaPagoMayor", new SpringMessage("field.solicitudPresencial.error.fechaPagoMayor"));
						
					}
				}
				catch (Exception ex) {
				}
			}

			if(fechaSolicitud != null && !fechaSolicitud.equals("") && !validacion.isFechaValida(fechaSolicitud))
			{
				request.setAttribute("errorfechaSolicitud", "errorfechaSolicitud");
				SpringErrors.add("dsErrorfechaSolicitud", new SpringMessage("field.solicitudPresencial.error.fechaSolicitud"));

			}
			if(fechaRegistro != null && !fechaRegistro.equals("") && !validacion.isFechaValida(fechaRegistro))
			{	
				request.setAttribute("errorfechaRegistro", "errorfechaRegistro");
				SpringErrors.add("dsErrorfechaRegistro", new SpringMessage("field.solicitudPresencial.error.fechaRegistro"));
			}
			else if(fechaRegistro != null && !fechaRegistro.equals(""))
			{
				try{
					SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
					Date dFechaRegistro = formatoDelTexto.parse(fechaRegistro);
					Date fechaHoy = new Date();

					if(dFechaRegistro!=null && dFechaRegistro.after(fechaHoy)){
						
							request.setAttribute("errorfechaRegistroMayor", "errorfechaRegistroMayor");
							SpringErrors.add("dsErrorfechaRegistroMayor", new SpringMessage("field.solicitudPresencial.error.fechaRegistroMayor"));
						
					}
				}
				catch (Exception ex) {
				}
			}	

			if(email != null && !email.equals("") && validacion.validateEmail(email))
			{
				request.setAttribute("errorEmail", "errorEmail");
				SpringErrors.add("dsErrorEmail", new SpringMessage("field.solicitudPresencial.error.emailFormato"));
			}
			if(!validacion.validateTamanioTextArea(adaptacionDiscapacidad, 200))
			{
				request.setAttribute("errorAdaptacion", "errorAdaptacion");
				SpringErrors.add("dsErrorAdaptacion", new SpringMessage("field.solicitudPresencial.error.tamanoAdaptacion"));
			}
			if(!validacion.validateTamanioTextArea(otrosTitulos, 200))
			{
				request.setAttribute("errorOtrosTitulos", "errorOtrosTitulos");
				SpringErrors.add("dsErrorOtrosTitulos", new SpringMessage("field.solicitudPresencial.error.tamanoOtrosTitulos"));
			}


			if( (idTipoPago != null && !idTipoPago.equals("")) || (fechaPago != null && !fechaPago.equals(""))
					|| (importe != null && !importe.equals("")) || (importeDecimal != null && !importeDecimal.equals(""))   ||(idEntidadBancaria != null && !idEntidadBancaria.equals("")) || (nrcPago != null && !nrcPago.equals("")))
			{


				if(idTipoPago == null || idTipoPago.equals(""))
				{
					request.setAttribute("errorTipoPago", "errorTipoPago");
					SpringErrors.add("dsErrorTipoPago", new SpringMessage("field.solicitudPresencial.error.tipoPagoObligatorio"));

				}
				if (!(idTipoPago.equals(Character.toString(Constantes.TIPO_PAGO_EXENTO_INT))) && (fechaPago == null || fechaPago.equals(""))){
					
					
						request.setAttribute("errorFechaPago", "errorFechaPago");
						SpringErrors.add("dsErrorFechaPago", new SpringMessage("field.solicitudPresencial.error.fechaPagoObligatorio"));

					
				}
				if(importe == null || importe.equals(""))
				{
					request.setAttribute(STRING_ERRORIMPORTE, STRING_ERRORIMPORTE);
					SpringErrors.add("dsErrorImporte", new SpringMessage("field.solicitudPresencial.error.importeObligatorio"));

				}

				if(idTipoPago.equals(String.valueOf(Constantes.TIPO_EXENTO_INTEGER)) && (importe==null ||importe.equals("0")||importe.equals("")) && (importeDecimal == null || importeDecimal.equals("0") || importeDecimal.equals("00")||importeDecimal.equals("")) && (idMotivosEx==null || idMotivosEx.equals(""))){
					
						request.setAttribute("errorMotivoExencion", "errorMotivoExencion");
						SpringErrors.add("dsErrorMotivoExencion", new SpringMessage("field.solicitudPresencial.error.motivoExencionobligatorio"));
					

				}

				// TODO Incidencia: condicion incorrecta con importeDecimal en la modificacion de solicitud presencial.
				if(!"MODIFICAR".equalsIgnoreCase(accion) && (importeDecimal == null || importeDecimal.equals(""))){
					
					
						request.setAttribute(STRING_ERRORIMPORTE, STRING_ERRORIMPORTE);
						SpringErrors.add("dsErrorImporte", new SpringMessage("field.solicitudPresencial.error.importeDecimalObligatorio"));					
					
				}

				String idTipoPagoN = idTipoPago;
				if((!idTipoPagoN.equals(""))&&(!idTipoPagoN.equals(String.valueOf(Constantes.TIPO_PAGO_EFECTIVO_INTEGER)))&&(!idTipoPagoN.equals(String.valueOf(Constantes.TIPO_PAGO_NINGUNO_INTEGER)))&&
						(!idTipoPagoN.equals(String.valueOf(Constantes.TIPO_EXENTO_INTEGER))) && (idEntidadBancaria == null || idEntidadBancaria.equals("") )){
					
					
						request.setAttribute("errorEntidadBancaria", "errorEntidadBancaria");
						SpringErrors.add("dsErrorEntidadBancaria", new SpringMessage("field.solicitudPresencial.error.entidadObligatorio"));

												 
				}

			}


			if(requiereTitulo == true && (numeroTituloFN == null || numeroTituloFN.equals(""))){
				
				
					request.setAttribute("errorNTitulo", "errorNtitulo");
					SpringErrors.add("dsErrorNTitulo", new SpringMessage("field.solicitudPresencial.error.numeroTituloFNObligatorio"));
				

			}
			// AHORA, SE DEBE INFORMAR uNICAMENTE LA FECHA DE REGISTRO, EL RESTO SE COGE POR DEFECTO

			if(porcentajeDiscapacidad != null && !porcentajeDiscapacidad.equalsIgnoreCase("")){

				if(!validacion.isNumeric(porcentajeDiscapacidad)){
					request.setAttribute(STRING_DSERRORPORCENTAJEDISCAPACIDAD, STRING_DSERRORPORCENTAJEDISCAPACIDAD);
					SpringErrors.add(STRING_DSERRORPORCENTAJEDISCAPACIDAD, new SpringMessage("field.solicitudPresencial.error.porcentajeDiscapacidad"));
				}else{
					Integer intPorcentaje=Integer.parseInt(porcentajeDiscapacidad);
					if (intPorcentaje.intValue()<0 || intPorcentaje.intValue()>100){
						request.setAttribute(STRING_DSERRORPORCENTAJEDISCAPACIDAD, STRING_DSERRORPORCENTAJEDISCAPACIDAD);
						SpringErrors.add(STRING_DSERRORPORCENTAJEDISCAPACIDAD, new SpringMessage("field.solicitudPresencial.error.porcentajeDiscapacidad"));
					}
				}
			}

			if(numeroSolicitud ==null || numeroSolicitud.equalsIgnoreCase("")){
				request.setAttribute(STRING_DSERRORNUMEROJUSTIFICANTE, STRING_DSERRORNUMEROJUSTIFICANTE);
				SpringErrors.add(STRING_DSERRORNUMEROJUSTIFICANTE, new SpringMessage("field.solicitudPresencial.error.numeroJustificante"));
			}


		}
		return SpringErrors;
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
	 * Obtiene el numero solicitud.
	 *
	 * @return el numero solicitud
	 */
	public String getNumeroSolicitud() {
		return numeroSolicitud;
	}

	/**
	 * Establece el id.
	 *
	 * @param id el nuevo id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Obtiene el id.
	 *
	 * @return el id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Establece el id pago solicitud.
	 *
	 * @param idPagoSolicitud el nuevo id pago solicitud
	 */
	public void setIdPagoSolicitud(String idPagoSolicitud) {
		this.idPagoSolicitud = idPagoSolicitud;
	}

	/**
	 * Obtiene el id pago solicitud.
	 *
	 * @return el id pago solicitud
	 */
	public String getIdPagoSolicitud() {
		return idPagoSolicitud;
	}

	/**
	 * Establece el id registro solicitud.
	 *
	 * @param idRegistroSolicitud el nuevo id registro solicitud
	 */
	public void setIdRegistroSolicitud(String idRegistroSolicitud) {
		this.idRegistroSolicitud = idRegistroSolicitud;
	}

	/**
	 * Obtiene el id registro solicitud.
	 *
	 * @return el id registro solicitud
	 */
	public String getIdRegistroSolicitud() {
		return idRegistroSolicitud;
	}

	/**
	 * Obtiene el ds convocatoria.
	 *
	 * @return el ds convocatoria
	 */
	public String getDsConvocatoria() {
		return dsConvocatoria;
	}

	/**
	 * Establece el ds convocatoria.
	 *
	 * @param dsConvocatoria el nuevo ds convocatoria
	 */
	public void setDsConvocatoria(String dsConvocatoria) {
		this.dsConvocatoria = dsConvocatoria;
	}

	/**
	 * Obtiene el ck consentimiento.
	 *
	 * @return el ck consentimiento
	 */
	public Boolean getCkConsentimiento() {
		return ckConsentimiento;
	}

	/**
	 * Establece el ck consentimiento.
	 *
	 * @param ckConsentimiento el nuevo ck consentimiento
	 */
	public void setCkConsentimiento(Boolean ckConsentimiento) {
		this.ckConsentimiento = ckConsentimiento;
	}

	/**
	 * Obtiene el ob nif.
	 *
	 * @return el ob nif
	 */
	public Character getObNif() {
		return ObNif;
	}

	/**
	 * Establece el ob nif.
	 *
	 * @param obNif el nuevo ob nif
	 */
	public void setObNif(Character obNif) {
		ObNif = obNif;
	}

	/**
	 * Obtiene el ob primer apellido.
	 *
	 * @return el ob primer apellido
	 */
	public Character getObPrimerApellido() {
		return ObPrimerApellido;
	}

	/**
	 * Establece el ob primer apellido.
	 *
	 * @param obPrimerApellido el nuevo ob primer apellido
	 */
	public void setObPrimerApellido(Character obPrimerApellido) {
		ObPrimerApellido = obPrimerApellido;
	}

	/**
	 * Obtiene el ob segundo apellido.
	 *
	 * @return el ob segundo apellido
	 */
	public Character getObSegundoApellido() {
		return ObSegundoApellido;
	}

	/**
	 * Establece el ob segundo apellido.
	 *
	 * @param obSegundoApellido el nuevo ob segundo apellido
	 */
	public void setObSegundoApellido(Character obSegundoApellido) {
		ObSegundoApellido = obSegundoApellido;
	}

	/**
	 * Obtiene el ob nombre.
	 *
	 * @return el ob nombre
	 */
	public Character getObNombre() {
		return ObNombre;
	}

	/**
	 * Establece el ob nombre.
	 *
	 * @param obNombre el nuevo ob nombre
	 */
	public void setObNombre(Character obNombre) {
		ObNombre = obNombre;
	}

	/**
	 * Obtiene el ob fecha nacimiento.
	 *
	 * @return el ob fecha nacimiento
	 */
	public Character getObFechaNacimiento() {
		return ObFechaNacimiento;
	}

	/**
	 * Establece el ob fecha nacimiento.
	 *
	 * @param obFechaNacimiento el nuevo ob fecha nacimiento
	 */
	public void setObFechaNacimiento(Character obFechaNacimiento) {
		ObFechaNacimiento = obFechaNacimiento;
	}

	/**
	 * Obtiene el ob sexo.
	 *
	 * @return el ob sexo
	 */
	public Character getObSexo() {
		return ObSexo;
	}

	/**
	 * Establece el ob sexo.
	 *
	 * @param obSexo el nuevo ob sexo
	 */
	public void setObSexo(Character obSexo) {
		ObSexo = obSexo;
	}

	/**
	 * Obtiene el ob nacionalidad.
	 *
	 * @return el ob nacionalidad
	 */
	public Character getObNacionalidad() {
		return ObNacionalidad;
	}

	/**
	 * Establece el ob nacionalidad.
	 *
	 * @param obNacionalidad el nuevo ob nacionalidad
	 */
	public void setObNacionalidad(Character obNacionalidad) {
		ObNacionalidad = obNacionalidad;
	}

	/**
	 * Obtiene el ob correo electronico.
	 *
	 * @return el ob correo electronico
	 */
	public Character getObCorreoElectronico() {
		return ObCorreoElectronico;
	}

	/**
	 * Establece el ob correo electronico.
	 *
	 * @param obCorreoElectronico el nuevo ob correo electronico
	 */
	public void setObCorreoElectronico(Character obCorreoElectronico) {
		ObCorreoElectronico = obCorreoElectronico;
	}


	/**
	 * Obtiene el ob pais.
	 *
	 * @return el ob pais
	 */
	public Character getObPais() {
		return ObPais;
	}

	/**
	 * Establece el ob pais.
	 *
	 * @param obPais el nuevo ob pais
	 */
	public void setObPais(Character obPais) {
		ObPais = obPais;
	}

	/**
	 * Obtiene el ob telefono.
	 *
	 * @return el ob telefono
	 */
	public Character getObTelefono() {
		return ObTelefono;
	}

	/**
	 * Establece el ob telefono.
	 *
	 * @param obTelefono el nuevo ob telefono
	 */
	public void setObTelefono(Character obTelefono) {
		ObTelefono = obTelefono;
	}

	/**
	 * Obtiene el ob via.
	 *
	 * @return el ob via
	 */
	public Character getObVia() {
		return ObVia;
	}

	/**
	 * Establece el ob via.
	 *
	 * @param obVia el nuevo ob via
	 */
	public void setObVia(Character obVia) {
		ObVia = obVia;
	}

	/**
	 * Obtiene el ob municipio.
	 *
	 * @return el ob municipio
	 */
	public Character getObMunicipio() {
		return ObMunicipio;
	}

	/**
	 * Establece el ob municipio.
	 *
	 * @param obMunicipio el nuevo ob municipio
	 */
	public void setObMunicipio(Character obMunicipio) {
		ObMunicipio = obMunicipio;
	}

	/**
	 * Obtiene el ob especialidad.
	 *
	 * @return el ob especialidad
	 */
	public Character getObEspecialidad() {
		return ObEspecialidad;
	}

	/**
	 * Establece el ob especialidad.
	 *
	 * @param obEspecialidad el nuevo ob especialidad
	 */
	public void setObEspecialidad(Character obEspecialidad) {
		ObEspecialidad = obEspecialidad;
	}

	/**
	 * Obtiene el ob provincia examen.
	 *
	 * @return el ob provincia examen
	 */
	public Character getObProvinciaExamen() {
		return ObProvinciaExamen;
	}

	/**
	 * Establece el ob provincia examen.
	 *
	 * @param obProvinciaExamen el nuevo ob provincia examen
	 */
	public void setObProvinciaExamen(Character obProvinciaExamen) {
		ObProvinciaExamen = obProvinciaExamen;
	}

	/**
	 * Obtiene el ob tipo discapacidad.
	 *
	 * @return el ob tipo discapacidad
	 */
	public Character getObTipoDiscapacidad() {
		return ObTipoDiscapacidad;
	}

	/**
	 * Establece el ob tipo discapacidad.
	 *
	 * @param obTipoDiscapacidad el nuevo ob tipo discapacidad
	 */
	public void setObTipoDiscapacidad(Character obTipoDiscapacidad) {
		ObTipoDiscapacidad = obTipoDiscapacidad;
	}

	/**
	 * Obtiene el ob porcentaje discapacidad.
	 *
	 * @return el ob porcentaje discapacidad
	 */
	public Character getObPorcentajeDiscapacidad() {
		return ObPorcentajeDiscapacidad;
	}

	/**
	 * Establece el ob porcentaje discapacidad.
	 *
	 * @param obPorcentajeDiscapacidad el nuevo ob porcentaje discapacidad
	 */
	public void setObPorcentajeDiscapacidad(Character obPorcentajeDiscapacidad) {
		ObPorcentajeDiscapacidad = obPorcentajeDiscapacidad;
	}

	/**
	 * Obtiene el ob reserva discapacidad.
	 *
	 * @return el ob reserva discapacidad
	 */
	public Character getObReservaDiscapacidad() {
		return ObReservaDiscapacidad;
	}

	/**
	 * Establece el ob reserva discapacidad.
	 *
	 * @param obReservaDiscapacidad el nuevo ob reserva discapacidad
	 */
	public void setObReservaDiscapacidad(Character obReservaDiscapacidad) {
		ObReservaDiscapacidad = obReservaDiscapacidad;
	}

	/**
	 * Obtiene el ob detalle discapacidad.
	 *
	 * @return el ob detalle discapacidad
	 */
	public Character getObDetalleDiscapacidad() {
		return ObDetalleDiscapacidad;
	}

	/**
	 * Establece el ob detalle discapacidad.
	 *
	 * @param obDetalleDiscapacidad el nuevo ob detalle discapacidad
	 */
	public void setObDetalleDiscapacidad(Character obDetalleDiscapacidad) {
		ObDetalleDiscapacidad = obDetalleDiscapacidad;
	}

	/**
	 * Obtiene el ob titulos exigidos.
	 *
	 * @return el ob titulos exigidos
	 */
	public Character getObTitulosExigidos() {
		return ObTitulosExigidos;
	}

	/**
	 * Establece el ob titulos exigidos.
	 *
	 * @param obTitulosExigidos el nuevo ob titulos exigidos
	 */
	public void setObTitulosExigidos(Character obTitulosExigidos) {
		ObTitulosExigidos = obTitulosExigidos;
	}

	/**
	 * Obtiene el ob otros titulos.
	 *
	 * @return el ob otros titulos
	 */
	public Character getObOtrosTitulos() {
		return ObOtrosTitulos;
	}

	/**
	 * Establece el ob otros titulos.
	 *
	 * @param obOtrosTitulos el nuevo ob otros titulos
	 */
	public void setObOtrosTitulos(Character obOtrosTitulos) {
		ObOtrosTitulos = obOtrosTitulos;
	}

	/**
	 * Obtiene el ob datos A.
	 *
	 * @return el ob datos A
	 */
	public Character getObDatosA() {
		return ObDatosA;
	}

	/**
	 * Establece el ob datos A.
	 *
	 * @param obDatosA el nuevo ob datos A
	 */
	public void setObDatosA(Character obDatosA) {
		ObDatosA = obDatosA;
	}

	/**
	 * Obtiene el ob datos B.
	 *
	 * @return el ob datos B
	 */
	public Character getObDatosB() {
		return ObDatosB;
	}

	/**
	 * Establece el ob datos B.
	 *
	 * @param obDatosB el nuevo ob datos B
	 */
	public void setObDatosB(Character obDatosB) {
		ObDatosB = obDatosB;
	}

	/**
	 * Obtiene el ob datos C.
	 *
	 * @return el ob datos C
	 */
	public Character getObDatosC() {
		return ObDatosC;
	}

	/**
	 * Establece el ob datos C.
	 *
	 * @param obDatosC el nuevo ob datos C
	 */
	public void setObDatosC(Character obDatosC) {
		ObDatosC = obDatosC;
	}

	/**
	 * Obtiene el ob provincia.
	 *
	 * @return el ob provincia
	 */
	public Character getObProvincia() {
		return ObProvincia;
	}

	/**
	 * Establece el ob provincia.
	 *
	 * @param obProvincia el nuevo ob provincia
	 */
	public void setObProvincia(Character obProvincia) {
		ObProvincia = obProvincia;
	}

	/**
	 * Obtiene el id motivos ex.
	 *
	 * @return el id motivos ex
	 */
	public String getIdMotivosEx() {
		return idMotivosEx;
	}

	/**
	 * Establece el id motivos ex.
	 *
	 * @param idMotivosEx el nuevo id motivos ex
	 */
	public void setIdMotivosEx(String idMotivosEx) {
		this.idMotivosEx = idMotivosEx;
	}

	/**
	 * Obtiene el id motivos red.
	 *
	 * @return el id motivos red
	 */
	public String getIdMotivosRed() {
		return idMotivosRed;
	}

	/**
	 * Establece el id motivos red.
	 *
	 * @param idMotivosRed el nuevo id motivos red
	 */
	public void setIdMotivosRed(String idMotivosRed) {
		this.idMotivosRed = idMotivosRed;
	}

	/**
	 * Obtiene el numero titulo FN.
	 *
	 * @return el numero titulo FN
	 */
	public String getNumeroTituloFN() {
		return numeroTituloFN;
	}

	/**
	 * Establece el numero titulo FN.
	 *
	 * @param numeroTituloFN el nuevo numero titulo FN
	 */
	public void setNumeroTituloFN(String numeroTituloFN) {
		this.numeroTituloFN = numeroTituloFN;
	}

	/**
	 * Obtiene el comunidad FN.
	 *
	 * @return el comunidad FN
	 */
	public String getComunidadFN() {
		return comunidadFN;
	}

	/**
	 * Establece el comunidad FN.
	 *
	 * @param comunidadFN el nuevo comunidad FN
	 */
	public void setComunidadFN(String comunidadFN) {
		this.comunidadFN = comunidadFN;
	}

	/**
	 * Obtiene el comunidad DD.
	 *
	 * @return el comunidad DD
	 */
	public String getComunidadDD() {
		return comunidadDD;
	}

	/**
	 * Establece el comunidad DD.
	 *
	 * @param comunidadDD el nuevo comunidad DD
	 */
	public void setComunidadDD(String comunidadDD) {
		this.comunidadDD = comunidadDD;
	}

	/**
	 * Obtiene el requiere titulo.
	 *
	 * @return el requiere titulo
	 */
	public Boolean getRequiereTitulo() {
		return requiereTitulo;
	}

	/**
	 * Establece el requiere titulo.
	 *
	 * @param requiereTitulo el nuevo requiere titulo
	 */
	public void setRequiereTitulo(Boolean requiereTitulo) {
		this.requiereTitulo = requiereTitulo;
	}

	/**
	 * Obtiene el ck reserva discapacidad.
	 *
	 * @return el ck reserva discapacidad
	 */
	public Boolean getCkReservaDiscapacidad() {
		return ckReservaDiscapacidad;
	}
	
	/**
	 * Comprueba si es ck reserva discapacidad.
	 *
	 * @return verdadero, si es ck reserva discapacidad
	 */
	public boolean isCkReservaDiscapacidad() {
		return ckReservaDiscapacidad;
	}

	/**
	 * Establece el ck reserva discapacidad.
	 *
	 * @param ckReservaDiscapacidad el nuevo ck reserva discapacidad
	 */
	public void setCkReservaDiscapacidad(Boolean ckReservaDiscapacidad) {
		this.ckReservaDiscapacidad = ckReservaDiscapacidad;
	}

	/**
	 * Obtiene el file.
	 *
	 * @return el file
	 */
	public MultipartFile[] getFile() {
		return file;
	}

	/**
	 * Establece el file.
	 *
	 * @param file el nuevo file
	 */
	public void setFile(MultipartFile[] file) {
		this.file = file;
	}

	/**
	 * Obtiene el num ficheros.
	 *
	 * @return el num ficheros
	 */
	public Integer getNumFicheros() {
		return numFicheros;
	}

	/**
	 * Establece el num ficheros.
	 *
	 * @param numFicheros el nuevo num ficheros
	 */
	public void setNumFicheros(Integer numFicheros) {
		this.numFicheros = numFicheros;
	}

	/**
	 * Obtiene el documento HTML.
	 *
	 * @return el documento HTML
	 */
	public String getDocumentoHTML() {
		return documentoHTML;
	}

	/**
	 * Establece el documento HTML.
	 *
	 * @param documentoHTML el nuevo documento HTML
	 */
	public void setDocumentoHTML(String documentoHTML) {
		this.documentoHTML = documentoHTML;
	}

	/**
	 * Obtiene el datos fichero.
	 *
	 * @return el datos fichero
	 */
	public String[] getDatosFichero() {
		return datosFichero;
	}

	/**
	 * Establece el datos fichero.
	 *
	 * @param datosFichero el nuevo datos fichero
	 */
	public void setDatosFichero(String[] datosFichero) {
		this.datosFichero = datosFichero;
	}

	/**
	 * Obtiene el documento solicitud presencial bean.
	 *
	 * @return el documento solicitud presencial bean
	 */
	public DocumentoSolicitudPresencialBean[] getDocumentoSolicitudPresencialBean() {
		return documentoSolicitudPresencialBean;
	}

	/**
	 * Establece el documento solicitud presencial bean.
	 *
	 * @param documentoSolicitudPresencialBean el nuevo documento solicitud presencial bean
	 */
	public void setDocumentoSolicitudPresencialBean(DocumentoSolicitudPresencialBean[] documentoSolicitudPresencialBean) {
		this.documentoSolicitudPresencialBean = documentoSolicitudPresencialBean;
	}

	/**
	 * Obtiene el lista text areas.
	 *
	 * @return el lista text areas
	 */
	public CamposPropiosBean[] getListaTextAreas() {
		return listaTextAreas;
	}

	/**
	 * Establece el lista text areas.
	 *
	 * @param listaTextAreas el nuevo lista text areas
	 */
	public void setListaTextAreas(CamposPropiosBean[] listaTextAreas) {
		this.listaTextAreas = listaTextAreas;
	}





}

