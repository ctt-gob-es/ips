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
 * El Class SolicitudesIncidenciasForm.
 */
public class SolicitudesIncidenciasForm extends SpringForm {

	/** el nif. */
	private String nif;
	
	/** el num solicitud. */
	private String numSolicitud; //numero Justificante
	
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
	private String dsCentroGestor;
	
	/** el cuerpo escala. */
	private String cuerpoEscala;
	
	/** el ds cuerpo escala. */
	private String dsCuerpoEscala;
	
	/** el id cuerpo escala. */
	private String idCuerpoEscala;
	
	/** el id especialidad. */
	private String idEspecialidad;
	
	/** el ds especialidad. */
	private String dsEspecialidad;
	
	/** el fecha desde. */
	private String fechaDesde; //Fecha Desde
	
	/** el fecha hasta. */
	private String fechaHasta; //Fecha Hasta
	
	/** el id estado. */
	private Integer idEstado;
	
	/** el estado. */
	private String estado;
	
	/** el perfil usuario. */
	private String perfilUsuario; //Perfil Usuario
	
	/** el solicitudes sel. */
	private String[] solicitudesSel; //Solicitudes que se han seleccionado
	
	/** el id tipo acceso. */
	private Integer idTipoAcceso;
	
	/** el id estado convoc. */
	private Integer idEstadoConvoc;
	
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
	
	/** el marcar todo. */
	//Para indicar si se ha marcado todo
	private String marcarTodo;
	
	/** el campo ordenacion. */
	private String campoOrdenacion;

	/** el numero pagina ir. */
	private Integer numeroPaginaIr;
	
	/** el pulsa ir. */
	private boolean pulsaIr;
	
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
		return (numSolicitud!=null ? numSolicitud.trim() : null);
	}
	
	/**
	 * Establece el Número de Solicitud.
	 *
	 * @param numSolicitud String
	 */
	public void setNumSolicitud(String numSolicitud) {
		this.numSolicitud = numSolicitud;
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
	 * Obtiene el id estado.
	 *
	 * @return the idEstado
	 */
	public Integer getIdEstado() {
		return idEstado;
	}
	
	
	/**
	 * Devuelve la especialidad.
	 *
	 * @return especialidad String
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
	 * Establece el id estado.
	 *
	 * @param idEstado the idEstado to set
	 */
	public void setIdEstado(Integer idEstado) {
		this.idEstado = idEstado;
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
	 * Establece el Cuerpo Escala.
	 *
	 * @param cuerpoEscala String
	 */
	public void setCuerpoEscala(String cuerpoEscala) {
		this.cuerpoEscala = cuerpoEscala;
	}
	
	/**
	 * Obtiene el id cuerpo escala.
	 *
	 * @return the idCuerpoEscala
	 */
	public String getIdCuerpoEscala() {
		return idCuerpoEscala;
	}
	
	/**
	 * Establece el id cuerpo escala.
	 *
	 * @param idCuerpoEscala the idCuerpoEscala to set
	 */
	public void setIdCuerpoEscala(String idCuerpoEscala) {
		this.idCuerpoEscala = idCuerpoEscala;
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
	 * Obtiene el id centro gestor.
	 *
	 * @return idCentroGestor Integer
	 */ 
	public String getIdCentroGestor() {
		return idCentroGestor;
	}
	
	/**
	 * Establece el id centro gestor.
	 *
	 * @param idCentroGestor Integer
	 */
	public void setIdCentroGestor(String idCentroGestor) {
		this.idCentroGestor = idCentroGestor;
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
	 * Obtiene el solicitudes sel.
	 *
	 * @return  solicitudesSel String[]
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
	 * Comprueba si es anexo.
	 *
	 * @return verdadero, si es anexo
	 */
	public boolean isAnexo() {
		return anexo;
	}
	
	/**
	 * Establece el anexo.
	 *
	 * @param anexo el nuevo anexo
	 */
	public void setAnexo(boolean anexo) {
		this.anexo = anexo;
	}
	
	/**
	 * Comprueba si es justifica pago.
	 *
	 * @return verdadero, si es justifica pago
	 */
	public boolean isJustificaPago() {
		return justificaPago;
	}
	
	/**
	 * Establece el justifica pago.
	 *
	 * @param justificaPago el nuevo justifica pago
	 */
	public void setJustificaPago(boolean justificaPago) {
		this.justificaPago = justificaPago;
	}
	
	/**
	 * Comprueba si es registro PDF.
	 *
	 * @return verdadero, si es registro PDF
	 */
	public boolean isRegistroPDF() {
		return registroPDF;
	}
	
	/**
	 * Establece el registro PDF.
	 *
	 * @param registroPDF el nuevo registro PDF
	 */
	public void setRegistroPDF(boolean registroPDF) {
		this.registroPDF = registroPDF;
	}
	
	/**
	 * Comprueba si es registro XML.
	 *
	 * @return verdadero, si es registro XML
	 */
	public boolean isRegistroXML() {
		return registroXML;
	}
	
	/**
	 * Establece el registro XML.
	 *
	 * @param registroXML el nuevo registro XML
	 */
	public void setRegistroXML(boolean registroXML) {
		this.registroXML = registroXML;
	}
	
	/**
	 * Obtiene el str anexo.
	 *
	 * @return el str anexo
	 */
	public String getStrAnexo() {
		return strAnexo;
	}
	
	/**
	 * Establece el str anexo.
	 *
	 * @param strAnexo el nuevo str anexo
	 */
	public void setStrAnexo(String strAnexo) {
		this.strAnexo = strAnexo;
	}
	
	/**
	 * Obtiene el str justifica pago.
	 *
	 * @return el str justifica pago
	 */
	public String getStrJustificaPago() {
		return strJustificaPago;
	}
	
	/**
	 * Establece el str justifica pago.
	 *
	 * @param strJustificaPago el nuevo str justifica pago
	 */
	public void setStrJustificaPago(String strJustificaPago) {
		this.strJustificaPago = strJustificaPago;
	}
	
	/**
	 * Obtiene el str registro PDF.
	 *
	 * @return el str registro PDF
	 */
	public String getStrRegistroPDF() {
		return strRegistroPDF;
	}
	
	/**
	 * Establece el str registro PDF.
	 *
	 * @param strRegistroPDF el nuevo str registro PDF
	 */
	public void setStrRegistroPDF(String strRegistroPDF) {
		this.strRegistroPDF = strRegistroPDF;
	}
	
	/**
	 * Obtiene el str registro XML.
	 *
	 * @return el str registro XML
	 */
	public String getStrRegistroXML() {
		return strRegistroXML;
	}
	
	/**
	 * Establece el str registro XML.
	 *
	 * @param strRegistroXML el nuevo str registro XML
	 */
	public void setStrRegistroXML(String strRegistroXML) {
		this.strRegistroXML = strRegistroXML;
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
				validateAux(SpringErrors,request,dFechaDesde,dFechaHasta,formatoDelTexto);				
			}			
				
		}
		return SpringErrors;
	}
	
	/**
	 * Validate aux.
	 *
	 * @param SpringErrors el spring errors
	 * @param request el request
	 * @param dFechaDesde el d fecha desde
	 * @param dFechaHasta el d fecha hasta
	 * @param formatoDelTexto el formato del texto
	 */
	public void validateAux(SpringErrors SpringErrors,HttpServletRequest request,Date dFechaDesde,Date dFechaHasta,SimpleDateFormat formatoDelTexto) {
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
	
}

