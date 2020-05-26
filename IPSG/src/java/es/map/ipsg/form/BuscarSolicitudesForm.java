package es.map.ipsg.form;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import es.map.ips.common.spring.SpringErrors;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringMapping;
import es.map.ips.common.spring.SpringMessage;
import es.map.ips.model.CentroGestor;
import es.map.ipsg.bean.CentroGestorBean;
import es.map.ipsg.util.Validacion;

/**
 * Clase BuscarSolicitudesForm.
 *
 * @author amartinl
 */
public class BuscarSolicitudesForm extends SpringForm {

	/** La constante serialVersionUID. */
	private static final long serialVersionUID = -8661073916196465993L;
	
	/** el nif. */
	private String nif;
	
	/** el numero solicitud. */
	private String numeroSolicitud; //numero Solicitud
	
	/** el solicitudes sel. */
	private String[] solicitudesSel; //Solicitudes que se han seleccionado
	
	/** el ministerio. */
	private String ministerio;
	
	/** el id ministerio. */
	private Integer idMinisterio;
	
	/** el modelo. */
	private String modelo;
	
	/** el id modelo. */
	private Integer idModelo;
	
	/** el centro gestor. */
	private CentroGestor centroGestor;
	
	/** el id centro gestor. */
	private String idCentroGestor;
	
	/** el ds centro gestor. */
	private String dsCentroGestor = "";
	
	/** el cuerpo escala. */
	private String cuerpoEscala;
	
	/** el ds cuerpo escala. */
	private String dsCuerpoEscala;
	
	/** el id cuerpo escala. */
	private Integer idCuerpoEscala;
	
	/** el id especialidad. */
	private String idEspecialidad;
	
	/** el ds especialidad. */
	private String dsEspecialidad;
	
	/** el tipo. */
	private String tipo;
	
	/** el id tipo. */
	private Integer idTipo;
	
	/** el fecha desde. */
	private String fechaDesde; //Fecha Desde
	
	/** el fecha hasta. */
	private String fechaHasta; //Fecha Hasta
	
	/** el fecha min. */
	private String fechaMin; //Fecha Mínima
	
	/** el fecha max. */
	private String fechaMax; //Fecha Máxima
	
	/** el ck F nacimiento. */
	private String ckFNacimiento; //Fecha Nacimiento Verificada //Indicador
	
	/** el ck F formacion oficial. */
	private String ckFFormacionOficial; //Fecha F.O. Verificada //Indicador
	
	/** el ck edad. */
	//private String ckDesempleo; //Desempleo Verificada //Indicador
	private String ckEdad; //Edad Verificada //Indicador
	
	/** el perfil usuario. */
	private String perfilUsuario; //Perfil Usuario
	
	/** el estado. */
	private String estado;
	
	/** el id tipo acceso. */
	private Integer idTipoAcceso;
	
	/** el sol exencion. */
	private String solExencion;
	
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
	
	/** el campo ordenacion. */
	private String campoOrdenacion;
	
	/** el numero pagina ir. */
	private Integer numeroPaginaIr;
	
	/** el pulsa ir. */
	private boolean pulsaIr;
	
	/** el marcar todo. */
	//Para indicar si se ha marcado todo
	private String marcarTodo;
	
	/** el id estado. */
	private Integer idEstado;
	
	/** el id estado convoc. */
	private Integer idEstadoConvoc;
	
	/** el id convocatoria. */
	private Integer idConvocatoria;
	
	/*INI-TRA042*/
	/** el id cg. */
	private String idCg;
	
	/** el lista centros gestores. */
	private List<CentroGestorBean> listaCentrosGestores = new ArrayList<CentroGestorBean>();
	/*FIN-TRA042*/
	
	/**
	 * Devuelve el NIF.
	 *
	 * @return nif String
	 */
	public String getNif() {
		return (nif!=null ? nif.trim():null);
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
		return (numeroSolicitud!=null ? numeroSolicitud.trim() : null);
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
	 * Obtiene el modelo.
	 *
	 * @return el modelo
	 */
	public String getModelo() {
		return modelo;
	}
	
	/**
	 * Establece el modelo.
	 *
	 * @param modelo el nuevo modelo
	 */
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	
	/**
	 * Obtiene el id modelo.
	 *
	 * @return el id modelo
	 */
	public Integer getIdModelo() {
		return idModelo;
	}
	
	/**
	 * Establece el id modelo.
	 *
	 * @param idModelo el nuevo id modelo
	 */
	public void setIdModelo(Integer idModelo) {
		this.idModelo = idModelo;
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
	 * Obtiene el sol exencion.
	 *
	 * @return el sol exencion
	 */
	public String getSolExencion() {
		return solExencion;
	}
	
	/**
	 * Establece el sol exencion.
	 *
	 * @param solExencion el nuevo sol exencion
	 */
	public void setSolExencion(String solExencion) {
		this.solExencion = solExencion;
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
	 * Obtiene el id especialidad.
	 *
	 * @return el id especialidad
	 */
	public String getIdEspecialidad() {
		return idEspecialidad;
	}
	
	/**
	 * Establece el id especialidad.
	 *
	 * @param idEspecialidad el nuevo id especialidad
	 */
	public void setIdEspecialidad(String idEspecialidad) {
		this.idEspecialidad = idEspecialidad;
	}
	
	/**
	 * Obtiene el ds especialidad.
	 *
	 * @return el ds especialidad
	 */
	public String getDsEspecialidad() {
		return dsEspecialidad;
	}
	
	/**
	 * Establece el ds especialidad.
	 *
	 * @param dsEspecialidad el nuevo ds especialidad
	 */
	public void setDsEspecialidad(String dsEspecialidad) {
		this.dsEspecialidad = dsEspecialidad;
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
			validateAux(request,SpringErrors,validacion);
			
			
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
			
				
		}
		return SpringErrors;
	}
	
	/**
	 * Validate aux.
	 *
	 * @param request el request
	 * @param SpringErrors el spring errors
	 * @param validacion el validacion
	 */
	public void validateAux(HttpServletRequest request, SpringErrors SpringErrors, Validacion validacion) {
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
	 * Obtiene el ds centro gestor.
	 *
	 * @return el ds centro gestor
	 */
	public String getDsCentroGestor() {
		return dsCentroGestor;
	}
	
	/**
	 * Establece el ds centro gestor.
	 *
	 * @param dsCentroGestor el nuevo ds centro gestor
	 */
	public void setDsCentroGestor(String dsCentroGestor) {
		this.dsCentroGestor = dsCentroGestor;
	}
	
	/**
	 * Obtiene el ds cuerpo escala.
	 *
	 * @return el ds cuerpo escala
	 */
	public String getDsCuerpoEscala() {
		return dsCuerpoEscala;
	}
	
	/**
	 * Establece el ds cuerpo escala.
	 *
	 * @param dsCuerpoEscala el nuevo ds cuerpo escala
	 */
	public void setDsCuerpoEscala(String dsCuerpoEscala) {
		this.dsCuerpoEscala = dsCuerpoEscala;
	}
	
	/**
	 * Obtiene el marcar todo.
	 *
	 * @return el marcar todo
	 */
	public String getMarcarTodo() {
		return marcarTodo;
	}
	
	/**
	 * Establece el marcar todo.
	 *
	 * @param marcarTodo el nuevo marcar todo
	 */
	public void setMarcarTodo(String marcarTodo) {
		this.marcarTodo = marcarTodo;
	}
	
	/**
	 * Obtiene el id estado.
	 *
	 * @return el id estado
	 */
	public Integer getIdEstado() {
		return idEstado;
	}
	
	/**
	 * Establece el id estado.
	 *
	 * @param idEstado el nuevo id estado
	 */
	public void setIdEstado(Integer idEstado) {
		this.idEstado = idEstado;
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
	 * Obtiene el numero pagina ir.
	 *
	 * @return el numero pagina ir
	 */
	public Integer getNumeroPaginaIr() {
		return numeroPaginaIr;
	}
	
	/**
	 * Establece el numero pagina ir.
	 *
	 * @param numeroPaginaIr el nuevo numero pagina ir
	 */
	public void setNumeroPaginaIr(Integer numeroPaginaIr) {
		this.numeroPaginaIr = numeroPaginaIr;
	}
	
	/**
	 * Comprueba si es pulsa ir.
	 *
	 * @return verdadero, si es pulsa ir
	 */
	public boolean isPulsaIr() {
		return pulsaIr;
	}
	
	/**
	 * Establece el pulsa ir.
	 *
	 * @param pulsaIr el nuevo pulsa ir
	 */
	public void setPulsaIr(boolean pulsaIr) {
		this.pulsaIr = pulsaIr;
	}
	
	/**
	 * Obtiene el id estado convoc.
	 *
	 * @return el id estado convoc
	 */
	public Integer getIdEstadoConvoc() {
		return idEstadoConvoc;
	}
	
	/**
	 * Establece el id estado convoc.
	 *
	 * @param idEstadoConvoc el nuevo id estado convoc
	 */
	public void setIdEstadoConvoc(Integer idEstadoConvoc) {
		this.idEstadoConvoc = idEstadoConvoc;
	}
	
	/**
	 * Obtiene el id convocatoria.
	 *
	 * @return el id convocatoria
	 */
	public Integer getIdConvocatoria() {
		return idConvocatoria;
	}
	
	/**
	 * Establece el id convocatoria.
	 *
	 * @param idConvocatoria el nuevo id convocatoria
	 */
	public void setIdConvocatoria(Integer idConvocatoria) {
		this.idConvocatoria = idConvocatoria;
	}
	
	/**
	 * Obtiene el campo ordenacion.
	 *
	 * @return el campo ordenacion
	 */
	public String getCampoOrdenacion() {
		return campoOrdenacion;
	}
	
	/**
	 * Establece el campo ordenacion.
	 *
	 * @param campoOrdenacion el nuevo campo ordenacion
	 */
	public void setCampoOrdenacion(String campoOrdenacion) {
		this.campoOrdenacion = campoOrdenacion;
	}

	/*INI-TRA042*/
	/**
	 * @return the idCg
	 */
	public String getIdCg() {
		return idCg;
	}

	/**
	 * @param idCg the idCg to set
	 */
	public void setIdCg(String idCg) {
		this.idCg = idCg;
	}

	/**
	 * @return the listaCentrosGestores
	 */
	public List<CentroGestorBean> getListaCentrosGestores() {
		return listaCentrosGestores;
	}

	/**
	 * @param listaCentrosGestores the listaCentrosGestores to set
	 */
	public void setListaCentrosGestores(List<CentroGestorBean> listaCentrosGestores) {
		this.listaCentrosGestores = listaCentrosGestores;
	}
	/*FIN-TRA042*/
}

