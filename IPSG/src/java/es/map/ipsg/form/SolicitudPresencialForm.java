package es.map.ipsg.form;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import es.map.ips.common.spring.SpringErrors;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringMapping;
import es.map.ips.common.spring.SpringMessage;
import es.map.ips.model.CentroGestor;
import es.map.ipsg.util.Validacion;

/**
 * Clase BuscarSolicitudesForm.
 *
 * @author amartinl
 */
public class SolicitudPresencialForm extends SpringForm {

	/** el id. */
	private Integer id;
	
	/** el nif. */
	private String nif;
	
	/** el nombre. */
	private String nombre;
	
	/** el primer apellido. */
	private String primerApellido;
	
	/** el segundo apellido. */
	private String segundoApellido;
	
	/** el fecha nacimiento. */
	private Date fechaNacimiento;
	
	/** el sexo. */
	private Character sexo;
	
	/** el calle domicilio. */
	private String calleDomicilio;
	
	/** el codigo postal domicilio. */
	private String codigoPostalDomicilio;
	
	/** el localidad nacimiento. */
	private String localidadNacimiento;
	
	/** el id provincia nacimiento. */
	private Integer idProvinciaNacimiento;
	
	/** el nacionalidad. */
	private String nacionalidad;
	
	/** el telefono. */
	private String telefono;
	
	/** el email. */
	private String email;
	
	/** el municipio domicilio. */
	private String municipioDomicilio;
	
	/** el id provincia domicilio. */
	private Integer idProvinciaDomicilio;
	
	/** el id pais domicilio. */
	private Integer idPaisDomicilio;
	
	/** el numero solicitud. */
	private String numeroSolicitud; //numero Solicitud
	
	/** el fecha solicitada. */
	private String fechaSolicitada;
	
	/** el id especialidad. */
	private Integer idEspecialidad;
	
	/** el id convocatoria. */
	private Long idConvocatoria;
	
	/** el id provincia examen. */
	private Integer idProvinciaExamen;
	
	/** el id tipo discapacidad. */
	private Integer idTipoDiscapacidad;
	
	/** el porcentaje discapacidad. */
	private Integer porcentajeDiscapacidad;
	
	/** el reserva discapacidad. */
	private Character reservaDiscapacidad;
	
	/** el detalle discapacidad. */
	private String detalleDiscapacidad;
	
	/** el id titulo oficial. */
	private Integer idTituloOficial;
	
	/** el otros titulos. */
	private String otrosTitulos;
	
	/** el datos A. */
	private String datosA;
	
	/** el datos B. */
	private String datosB;
	
	/** el datos C. */
	private String datosC;
	
	
	
	
	/** el fecha desde. */
	private String fechaDesde; //Fecha Desde
	
	/** el fecha hasta. */
	private String fechaHasta; //Fecha Hasta
	
	/** el solicitudes sel. */
	private String[] solicitudesSel; //Solicitudes que se han seleccionado
	
	/** el ministerio. */
	private String ministerio;
	
	/** el id ministerio. */
	private Integer idMinisterio;
	
	/** el centro gestor. */
	private CentroGestor centroGestor;
	
	/** el id centro gestor. */
	private String idCentroGestor = "";
	
	/** el cuerpo escala. */
	private String cuerpoEscala;
	
	/** el id cuerpo escala. */
	private Integer idCuerpoEscala;
	
	/** el tipo. */
	private String tipo;
	
	/** el id tipo. */
	private Integer idTipo;
	
	/** el fecha min. */
	private String fechaMin; //Fecha Mínima
	
	/** el fecha max. */
	private String fechaMax; //Fecha Máxima
	
	/** el ck F nacimiento. */
	private String ckFNacimiento; //Fecha Nacimiento Verificada //Indicador
	
	/** el ck F formacion oficial. */
	private String ckFFormacionOficial; //Fecha F.O. Verificada //Indicador
	
	/** el ck edad. */
	private String ckEdad; //Edad Verificada //Indicador
	
	/** el perfil usuario. */
	private String perfilUsuario; //Perfil Usuario
	
	/** el estado. */
	private String estado;
	
	/** el accion. */
	private String accion;
	
	/** el pagina actual. */
	private String paginaActual;  //PAGINACIÓN
	
	/** el paginas totales. */
	private String paginasTotales;  //PAGINACIÓN
	
	/** el direccion. */
	private String direccion;
	
	/** el num registro. */
	private String numRegistro;  //PAGINACIÓN
	
	/** el submit. */
	private String submit;
	
	/** el campo. */
	private String campo;
	
	/** el cambios. */
	private String cambios; //PAGINACIÓN

	
	/** el anexo. */
	private boolean anexo;
	
	/** el justifica pago. */
	private boolean justificaPago;
	
	/** el registro PDF. */
	private boolean registroPDF;
	
	/** el registro XML. */
	private boolean registroXML;
	
	/** el str anexo. */
	private String strAnexo;
	
	/** el str justifica pago. */
	private String strJustificaPago;
	
	/** el str registro PDF. */
	private String strRegistroPDF;
	
	/** el str registro XML. */
	private String strRegistroXML;
	
	
	/**
	 * Devuelve el NIF.
	 *
	 * @return nif String
	 */
	public String getNif() {
		return nif;
	}
	
	/**
	 * Establece el NIF.
	 *
	 * @param nif String
	 */
	public void setNif(String nif) {
		this.nif = nif;
	}
	
	/**
	 *  
	 * Devuelve el Número de Solicitud .
	 *
	 * @return numSolicitud String
	 */
	public String getNumSolicitud() {
		return numeroSolicitud;
	}
	
	/**
	 * Establece el Número de Solicitud.
	 *
	 * @param numSolicitud String
	 */
	public void setNumSolicitud(String numSolicitud) {
		this.numeroSolicitud = numSolicitud;
	}
	
	/**
	 * Devuelve el Ministerio.
	 *
	 * @return ministerio String
	 */
	public String getMinisterio() {
		return ministerio;
	}
	
	/**
	 * Establece el Ministerio.
	 *
	 * @param ministerio String
	 */
	public void setMinisterio(String ministerio) {
		this.ministerio = ministerio;
	}
	/** 
	 * @return centroGestor CentroGestor
	 */
	public CentroGestor getCentroGestor() {
		return centroGestor;
	}
	
	/**
	 * Establece el centro gestor.
	 *
	 * @param centroGestor CentroGestor
	 */
	public void setCentroGestor(CentroGestor centroGestor) {
		this.centroGestor = centroGestor;
	}
	
	/**
	 * Devuelve el Cuerpo Escala.
	 *
	 * @return cuerpoEscala String
	 */
	public String getCuerpoEscala() {
		return cuerpoEscala;
	}
	
	/**
	 * Establece el Cuerpo Escala.
	 *
	 * @param cuerpoEscala String
	 */
	public void setCuerpoEscala(String cuerpoEscala) {
		this.cuerpoEscala = cuerpoEscala;
	}
	
	/**
	 * Devuelve el Tipo de Solicitud.
	 *
	 * @return idTipo Integer
	 */
	public Integer getIdTipo() {
		return idTipo;
	}
	
	/**
	 * Establece el Tipo de Solicitud.
	 *
	 * @param idTipo Integer
	 */
	public void setIdTipo(Integer idTipo) {
		this.idTipo = idTipo;
	}
	
	/**
	 * Devuelve si está verificada o no la Fecha de Nacimiento.
	 *
	 * @return ckFNacimiento String
	 */
	public String getCkFNacimiento() {
		return ckFNacimiento;
	}
	
	/**
	 * Establece si la Fecha de Nacimiento está verificada.
	 *
	 * @param ckFNacimiento String
	 */
	public void setCkFNacimiento(String ckFNacimiento) {
		this.ckFNacimiento = ckFNacimiento;
	}
	
	/**
	 * Devuelve si está verificada o no la Formación Oficial.
	 *
	 * @return ckFFormacionOficial String
	 */
	public String getCkFFormacionOficial() {
		return ckFFormacionOficial;
	}
	
	/**
	 * Establece si la Fecha de Formación Oficial está verificada.
	 *
	 * @param ckFFormacionOficial String
	 */
	public void setCkFFormacionOficial(String ckFFormacionOficial) {
		this.ckFFormacionOficial = ckFFormacionOficial;
	}
	
	/**
	 * Obtiene el submit.
	 *
	 * @return submit
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
	 * Obtiene el campo.
	 *
	 * @return campo String
	 */
	public String getCampo() {
		return campo;
	}
	
	/**
	 * Establece el campo.
	 *
	 * @param campo String
	 */
	public void setCampo(String campo) {
		this.campo = campo;
	}
	
	/**
	 * Obtiene el direccion.
	 *
	 * @return direccion String
	 */
	public String getDireccion() {
		return direccion;
	}
	
	/**
	 * Establece la dirección .
	 *
	 * @param direccion String
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * Obtiene el pagina actual.
	 *
	 * @return paginaActual String
	 */
	public String getPaginaActual() {
		return paginaActual;
	}
	
	/**
	 * Establece e la pagina actual.
	 *
	 * @param paginaActual String
	 */
	public void setPaginaActual(String paginaActual) {
		this.paginaActual = paginaActual;
	}
	
	/**
	 * Devuelve las páginas Totales.
	 *
	 * @return paginasTotales String
	 */
	public String getPaginasTotales() {
		return paginasTotales;
	}
	
	/**
	 * Establece la pagina total.
	 *
	 * @param paginasTotales String
	 */
	public void setPaginasTotales(String paginasTotales) {
		this.paginasTotales = paginasTotales;
	}
	
	/**
	 * Devuelve el numRegistro.
	 *
	 * @return numRegistro String
	 */
	public String getNumRegistro() {
		return numRegistro;
	}
	
	/**
	 * Establece el NumRegistro.
	 *
	 * @param numRegistro String
	 */
	public void setNumRegistro(String numRegistro) {
		this.numRegistro = numRegistro;
	}
	
	/**
	 * Obtiene el fecha desde.
	 *
	 * @return fechaDesde String
	 */
	public String getFechaDesde() {
		return fechaDesde;
	}
	
	/**
	 * Establece el fecha desde.
	 *
	 * @param fechaDesde String
	 */
	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}
	
	/**
	 * Obtiene el fecha hasta.
	 *
	 * @return fechaHasta String
	 */
	public String getFechaHasta() {
		return fechaHasta;
	}
	/** 
	 * @param fechaHasta String
	 */
	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
	}
	
	/**
	 * Obtiene el id ministerio.
	 *
	 * @return idMinisterio Integer
	 */
	public Integer getIdMinisterio() {
		return idMinisterio;
	}
	
	/**
	 * Establece el id ministerio.
	 *
	 * @param idMinisterio Integer
	 */
	public void setIdMinisterio(Integer idMinisterio) {
		this.idMinisterio = idMinisterio;
	}
	
	/**
	 * Obtiene el id centro gestor.
	 *
	 * @return idCentroGestor String
	 */ 
	public String getIdCentroGestor() {
		return idCentroGestor;
	}
	
	/**
	 * Establece el id centro gestor.
	 *
	 * @param idCentroGestor String
	 */
	public void setIdCentroGestor(String idCentroGestor) {
		this.idCentroGestor = idCentroGestor;
	}
	
	/**
	 * Obtiene el id cuerpo escala.
	 *
	 * @return idCuerpoEscala Integer
	 */
	public Integer getIdCuerpoEscala() {
		return idCuerpoEscala;
	}
	
	/**
	 * Establece el id cuerpo escala.
	 *
	 * @param idCuerpoEscala Integer
	 */
	public void setIdCuerpoEscala(Integer idCuerpoEscala) {
		this.idCuerpoEscala = idCuerpoEscala;
	}
	
	/**
	 * Obtiene el tipo.
	 *
	 * @return tipo String
	 */
	public String getTipo() {
		return tipo;
	}
	
	/**
	 * Establece el tipo.
	 *
	 * @param tipo String
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
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
	 * Obtiene el ck edad.
	 *
	 * @return ckEdad String
	 */
	public String getCkEdad() {
		return ckEdad;
	}
	
	/**
	 * Establece el ck edad.
	 *
	 * @param ckEdad String
	 */
	public void setCkEdad(String ckEdad) {
		this.ckEdad = ckEdad;
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
	 * Obtiene el solicitudes sel.
	 *
	 * @return solicitudesSel String[]
	 */
	public String[] getSolicitudesSel() {
		return solicitudesSel;
	}
	
	/**
	 * Establece el solicitudes sel.
	 *
	 * @param solicitudesSel String[]
	 */
	public void setSolicitudesSel(String[] solicitudesSel) {
		this.solicitudesSel = solicitudesSel;
	}

	/**
	 * Obtiene el fecha min.
	 *
	 * @return fechaMin String
	 */
	public String getFechaMin() {
		return fechaMin;
	}
	
	/**
	 * Establece el fecha min.
	 *
	 * @param fechaMin  String
	 */
	public void setFechaMin(String fechaMin) {
		this.fechaMin = fechaMin;
	}
	
	/**
	 * Obtiene el fecha max.
	 *
	 * @return  fechaMax String
	 */
	public String getFechaMax() {
		return fechaMax;
	}
	
	/**
	 * Establece el fecha max.
	 *
	 * @param fechaMax String
	 */
	public void setFechaMax(String fechaMax) {
		this.fechaMax = fechaMax;
	}
	
	/**
	 * Obtiene el estado.
	 *
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}
	
	/**
	 * Establece el estado.
	 *
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	/**
	 * Obtiene el perfil usuario.
	 *
	 * @return perfilUsuario String
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
	 * Validaciones del formulario de solicitudes.
	 *
	 * @param mapping ActionMapping
	 * @param request HttpServletRequest
	 * @return SpringErrors SpringErrors
	 */
	public SpringErrors validate(SpringMapping mapping,	HttpServletRequest request) {
		SpringErrors SpringErrors = new SpringErrors();
		Validacion validacion = new Validacion();
		SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
		Date dFechaDesde = null;
		Date dFechaHasta = null;

		if("GUARDAR".equalsIgnoreCase(accion) || "MODIFICAR".equalsIgnoreCase(accion) || "BUSCAR".equals(accion))
		{
			if(cuerpoEscala != null && !cuerpoEscala.equals("") && !validacion.isNumeric(cuerpoEscala))
			{
				request.setAttribute("errorIdCuerpoEscala", "errorIdCuerpoEscala");
				SpringErrors.add("dsErrorIdCuerpoEscala", new SpringMessage("field.solicitud.errores.idCuerpoEscalaNoNumero"));
			}
			if(idCentroGestor != null &&  !idCentroGestor.equals("") && !validacion.isNumeric(idCentroGestor))
			{
				request.setAttribute("errorIdCentroGestor", "errorIdCentroGestor");
				SpringErrors.add("dsErrorIdCuerpoEscala", new SpringMessage("field.solicitud.errores.idCentroGestorNoNumero"));
			}
			if(fechaDesde != null && !fechaDesde.equalsIgnoreCase("") && !validacion.isFechaValida(fechaDesde))
			{

				request.setAttribute("errorFechaDesde", "errorFechaDesde");
				SpringErrors.add("dsErrorFechaDesde", new SpringMessage("field.solicitud.errores.fechaDesdeNoValido"));
			}	
			if(fechaHasta != null && !fechaHasta.equalsIgnoreCase("") && !validacion.isFechaValida(fechaHasta))
			{

				request.setAttribute("errorFechaHasta", "errorFechaHasta");
				SpringErrors.add("dsErrorFechaHasta", new SpringMessage("field.solicitud.errores.fechaHastaNoValido"));
			}
			
			
			if(fechaDesde != null && fechaHasta!= null && validacion.isFechaValida(fechaDesde) && validacion.isFechaValida(fechaHasta))
			{
				try
				{
					dFechaDesde = formatoDelTexto.parse(fechaDesde);
					dFechaHasta = formatoDelTexto.parse(fechaHasta);
					if (dFechaDesde!=null && dFechaHasta !=null && dFechaDesde.after(dFechaHasta))
					{
						
						
						
							request.setAttribute("errorFechaMayorDesde", "errorFechaMayorDesde");
							SpringErrors.add("dsErrorFechaMayorDesde", new SpringMessage(
									"field.solicitudRegistrada.errores.fechaMayorDesde"));
						
					}
					
				}catch (ParseException ex) {
					ex.printStackTrace();
				}
			}
			
			if (email != null && !email.equalsIgnoreCase("") && !validacion.validateEmail(email))
			{
				
								
					request.setAttribute("errorEmail", "errorEmail");
					SpringErrors.add("dsErrorEmail", new SpringMessage("field.solicitudRegistrada.errores.Email"));
				
			}			

				
		}
		return SpringErrors;
	}
	
	/**
	 * Comprueba si es anexo.
	 *
	 * @return the anexo
	 */
	public boolean isAnexo() {
		return anexo;
	}
	
	/**
	 * Comprueba si es justifica pago.
	 *
	 * @return the justificaPago
	 */
	public boolean isJustificaPago() {
		return justificaPago;
	}
	
	/**
	 * Comprueba si es registro PDF.
	 *
	 * @return the registroPDF
	 */
	public boolean isRegistroPDF() {
		return registroPDF;
	}
	
	/**
	 * Comprueba si es registro XML.
	 *
	 * @return the registroXML
	 */
	public boolean isRegistroXML() {
		return registroXML;
	}
	
	/**
	 * Establece el anexo.
	 *
	 * @param anexo the anexo to set
	 */
	public void setAnexo(boolean anexo) {
		this.anexo = anexo;
	}
	
	/**
	 * Establece el justifica pago.
	 *
	 * @param justificaPago the justificaPago to set
	 */
	public void setJustificaPago(boolean justificaPago) {
		this.justificaPago = justificaPago;
	}
	
	/**
	 * Establece el registro PDF.
	 *
	 * @param registroPDF the registroPDF to set
	 */
	public void setRegistroPDF(boolean registroPDF) {
		this.registroPDF = registroPDF;
	}
	
	/**
	 * Establece el registro XML.
	 *
	 * @param registroXML the registroXML to set
	 */
	public void setRegistroXML(boolean registroXML) {
		this.registroXML = registroXML;
	}
	
	/**
	 * Obtiene el str anexo.
	 *
	 * @return the strAnexo
	 */
	public String getStrAnexo() {
		return strAnexo;
	}
	
	/**
	 * Establece el str anexo.
	 *
	 * @param strAnexo the strAnexo to set
	 */
	public void setStrAnexo(String strAnexo) {
		this.strAnexo = strAnexo;
	}
	
	/**
	 * Obtiene el str justifica pago.
	 *
	 * @return the strJustificaPago
	 */
	public String getStrJustificaPago() {
		return strJustificaPago;
	}
	
	/**
	 * Obtiene el str registro PDF.
	 *
	 * @return the strRegistroPDF
	 */
	public String getStrRegistroPDF() {
		return strRegistroPDF;
	}
	
	/**
	 * Obtiene el str registro XML.
	 *
	 * @return the strRegistroXML
	 */
	public String getStrRegistroXML() {
		return strRegistroXML;
	}
	
	/**
	 * Establece el str justifica pago.
	 *
	 * @param strJustificaPago the strJustificaPago to set
	 */
	public void setStrJustificaPago(String strJustificaPago) {
		this.strJustificaPago = strJustificaPago;
	}
	
	/**
	 * Establece el str registro PDF.
	 *
	 * @param strRegistroPDF the strRegistroPDF to set
	 */
	public void setStrRegistroPDF(String strRegistroPDF) {
		this.strRegistroPDF = strRegistroPDF;
	}
	
	/**
	 * Establece el str registro XML.
	 *
	 * @param strRegistroXML the strRegistroXML to set
	 */
	public void setStrRegistroXML(String strRegistroXML) {
		this.strRegistroXML = strRegistroXML;
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
	 * Obtiene el nombre.
	 *
	 * @return el nombre
	 */
	public String getNombre() {
		return nombre;
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
	 * Obtiene el primer apellido.
	 *
	 * @return el primer apellido
	 */
	public String getPrimerApellido() {
		return primerApellido;
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
	 * Obtiene el segundo apellido.
	 *
	 * @return el segundo apellido
	 */
	public String getSegundoApellido() {
		return segundoApellido;
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
	 * Obtiene el telefono.
	 *
	 * @return el telefono
	 */
	public String getTelefono() {
		return telefono;
	}
	
	/**
	 * Establece el id.
	 *
	 * @param id el nuevo id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * Obtiene el id.
	 *
	 * @return el id
	 */
	public Integer getId() {
		return id;
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
	 * Obtiene el fecha nacimiento.
	 *
	 * @return el fecha nacimiento
	 */
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	
	/**
	 * Establece el sexo.
	 *
	 * @param sexo el nuevo sexo
	 */
	public void setSexo(Character sexo) {
		this.sexo = sexo;
	}
	
	/**
	 * Obtiene el sexo.
	 *
	 * @return el sexo
	 */
	public Character getSexo() {
		return sexo;
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
	 * Obtiene el calle domicilio.
	 *
	 * @return el calle domicilio
	 */
	public String getCalleDomicilio() {
		return calleDomicilio;
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
	 * Obtiene el codigo postal domicilio.
	 *
	 * @return el codigo postal domicilio
	 */
	public String getCodigoPostalDomicilio() {
		return codigoPostalDomicilio;
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
	 * Obtiene el localidad nacimiento.
	 *
	 * @return el localidad nacimiento
	 */
	public String getLocalidadNacimiento() {
		return localidadNacimiento;
	}
	
	/**
	 * Establece el id provincia nacimiento.
	 *
	 * @param idProvinciaNacimiento el nuevo id provincia nacimiento
	 */
	public void setIdProvinciaNacimiento(Integer idProvinciaNacimiento) {
		this.idProvinciaNacimiento = idProvinciaNacimiento;
	}
	
	/**
	 * Obtiene el id provincia nacimiento.
	 *
	 * @return el id provincia nacimiento
	 */
	public Integer getIdProvinciaNacimiento() {
		return idProvinciaNacimiento;
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
	 * Obtiene el nacionalidad.
	 *
	 * @return el nacionalidad
	 */
	public String getNacionalidad() {
		return nacionalidad;
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
	 * Obtiene el municipio domicilio.
	 *
	 * @return el municipio domicilio
	 */
	public String getMunicipioDomicilio() {
		return municipioDomicilio;
	}
	
	/**
	 * Establece el id provincia domicilio.
	 *
	 * @param idProvinciaDomicilio el nuevo id provincia domicilio
	 */
	public void setIdProvinciaDomicilio(Integer idProvinciaDomicilio) {
		this.idProvinciaDomicilio = idProvinciaDomicilio;
	}
	
	/**
	 * Obtiene el id provincia domicilio.
	 *
	 * @return el id provincia domicilio
	 */
	public Integer getIdProvinciaDomicilio() {
		return idProvinciaDomicilio;
	}
	
	/**
	 * Establece el id pais domicilio.
	 *
	 * @param idPaisDomicilio el nuevo id pais domicilio
	 */
	public void setIdPaisDomicilio(Integer idPaisDomicilio) {
		this.idPaisDomicilio = idPaisDomicilio;
	}
	
	/**
	 * Obtiene el id pais domicilio.
	 *
	 * @return el id pais domicilio
	 */
	public Integer getIdPaisDomicilio() {
		return idPaisDomicilio;
	}
	
	/**
	 * Establece el fecha solicitada.
	 *
	 * @param fechaSolicitada el nuevo fecha solicitada
	 */
	public void setFechaSolicitada(String fechaSolicitada) {
		this.fechaSolicitada = fechaSolicitada;
	}
	
	/**
	 * Obtiene el fecha solicitada.
	 *
	 * @return el fecha solicitada
	 */
	public String getFechaSolicitada() {
		return fechaSolicitada;
	}
	
	/**
	 * Establece el id especialidad.
	 *
	 * @param idEspecialidad el nuevo id especialidad
	 */
	public void setIdEspecialidad(Integer idEspecialidad) {
		this.idEspecialidad = idEspecialidad;
	}
	
	/**
	 * Obtiene el id especialidad.
	 *
	 * @return el id especialidad
	 */
	public Integer getIdEspecialidad() {
		return idEspecialidad;
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
	 * Obtiene el id convocatoria.
	 *
	 * @return el id convocatoria
	 */
	public Long getIdConvocatoria() {
		return idConvocatoria;
	}
	
	/**
	 * Establece el id provincia examen.
	 *
	 * @param idProvinciaExamen el nuevo id provincia examen
	 */
	public void setIdProvinciaExamen(Integer idProvinciaExamen) {
		this.idProvinciaExamen = idProvinciaExamen;
	}
	
	/**
	 * Obtiene el id provincia examen.
	 *
	 * @return el id provincia examen
	 */
	public Integer getIdProvinciaExamen() {
		return idProvinciaExamen;
	}
	
	/**
	 * Establece el id tipo discapacidad.
	 *
	 * @param idTipoDiscapacidad el nuevo id tipo discapacidad
	 */
	public void setIdTipoDiscapacidad(Integer idTipoDiscapacidad) {
		this.idTipoDiscapacidad = idTipoDiscapacidad;
	}
	
	/**
	 * Obtiene el id tipo discapacidad.
	 *
	 * @return el id tipo discapacidad
	 */
	public Integer getIdTipoDiscapacidad() {
		return idTipoDiscapacidad;
	}
	
	/**
	 * Establece el porcentaje discapacidad.
	 *
	 * @param porcentajeDiscapacidad el nuevo porcentaje discapacidad
	 */
	public void setPorcentajeDiscapacidad(Integer porcentajeDiscapacidad) {
		this.porcentajeDiscapacidad = porcentajeDiscapacidad;
	}
	
	/**
	 * Obtiene el porcentaje discapacidad.
	 *
	 * @return el porcentaje discapacidad
	 */
	public Integer getPorcentajeDiscapacidad() {
		return porcentajeDiscapacidad;
	}
	
	/**
	 * Establece el reserva discapacidad.
	 *
	 * @param reservaDiscapacidad el nuevo reserva discapacidad
	 */
	public void setReservaDiscapacidad(Character reservaDiscapacidad) {
		this.reservaDiscapacidad = reservaDiscapacidad;
	}
	
	/**
	 * Obtiene el reserva discapacidad.
	 *
	 * @return el reserva discapacidad
	 */
	public Character getReservaDiscapacidad() {
		return reservaDiscapacidad;
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
	 * Obtiene el detalle discapacidad.
	 *
	 * @return el detalle discapacidad
	 */
	public String getDetalleDiscapacidad() {
		return detalleDiscapacidad;
	}
	
	/**
	 * Establece el id titulo oficial.
	 *
	 * @param idTituloOficial el nuevo id titulo oficial
	 */
	public void setIdTituloOficial(Integer idTituloOficial) {
		this.idTituloOficial = idTituloOficial;
	}
	
	/**
	 * Obtiene el id titulo oficial.
	 *
	 * @return el id titulo oficial
	 */
	public Integer getIdTituloOficial() {
		return idTituloOficial;
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
	 * Obtiene el otros titulos.
	 *
	 * @return el otros titulos
	 */
	public String getOtrosTitulos() {
		return otrosTitulos;
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
	 * Obtiene el datos A.
	 *
	 * @return el datos A
	 */
	public String getDatosA() {
		return datosA;
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
	 * Obtiene el datos B.
	 *
	 * @return el datos B
	 */
	public String getDatosB() {
		return datosB;
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
	 * Obtiene el datos C.
	 *
	 * @return el datos C
	 */
	public String getDatosC() {
		return datosC;
	}
}

