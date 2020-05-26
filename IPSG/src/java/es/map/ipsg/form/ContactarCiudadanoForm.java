package es.map.ipsg.form;

import javax.servlet.http.HttpServletRequest;

import es.map.ips.common.spring.SpringErrors;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringMapping;
import es.map.ips.common.spring.SpringMessage;
import es.map.ipsg.util.Validacion;

/**
 * El Class ContactarCiudadanoForm.
 */
public class ContactarCiudadanoForm extends SpringForm {

	/** el de. */
	private String de;
	
	/** el para. */
	private String para;
	
	/** el asunto. */
	private String asunto;
	
	/** el cuerpo mensaje. */
	private String cuerpoMensaje;
	
	/** el id solicitud. */
	private String idSolicitud;
	
	/** el id documentos. */
	private String[] idDocumentos;
	
	/** el entorno. */
	private String entorno;
	
	/** el id correo. */
	private String idCorreo;
	
	/** el nombre. */
	private String nombre;
	
	/** el descripcion. */
	private String descripcion;
	
	/** el solicitudes sel. */
	private String[] solicitudesSel;

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

	/** La constante STRING_ERRORPARA. */
	private static final String STRING_ERRORPARA = "errorPara";
	
	/**
	 * Obtiene el de.
	 *
	 * @return the de
	 */
	public String getDe() {
		return de;
	}


	/**
	 * Establece el de.
	 *
	 * @param de the de to set
	 */
	public void setDe(String de) {
		this.de = de;
	}


	/**
	 * Obtiene el para.
	 *
	 * @return the para
	 */
	public String getPara() {
		return para;
	}


	/**
	 * Establece el para.
	 *
	 * @param para the para to set
	 */
	public void setPara(String para) {
		this.para = para;
	}


	/**
	 * Obtiene el asunto.
	 *
	 * @return the asunto
	 */
	public String getAsunto() {
		return asunto;
	}


	/**
	 * Establece el asunto.
	 *
	 * @param asunto the asunto to set
	 */
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}


	/**
	 * Obtiene el cuerpo mensaje.
	 *
	 * @return the cuerpoMensaje
	 */
	public String getCuerpoMensaje() {
		return cuerpoMensaje;
	}


	/**
	 * Establece el cuerpo mensaje.
	 *
	 * @param cuerpoMensaje the cuerpoMensaje to set
	 */
	public void setCuerpoMensaje(String cuerpoMensaje) {
		this.cuerpoMensaje = cuerpoMensaje;
	}


	/**
	 * Obtiene el id solicitud.
	 *
	 * @return the idSolicitud
	 */
	public String getIdSolicitud() {
		return idSolicitud;
	}


	/**
	 * Establece el id solicitud.
	 *
	 * @param idSolicitud the idSolicitud to set
	 */
	public void setIdSolicitud(String idSolicitud) {
		this.idSolicitud = idSolicitud;
	}


	/**
	 * Obtiene el id documentos.
	 *
	 * @return the idDocumentos
	 */
	public String[] getIdDocumentos() {
		return idDocumentos;
	}


	/**
	 * Establece el id documentos.
	 *
	 * @param idDocumentos the idDocumentos to set
	 */
	public void setIdDocumentos(String[] idDocumentos) {
		this.idDocumentos = idDocumentos;
	}


	/**
	 * Obtiene el accion.
	 *
	 * @return el accion
	 */
	public String getAccion() {
		return accion;
	}


	/**
	 * Establece el accion.
	 *
	 * @param accion el nuevo accion
	 */
	public void setAccion(String accion) {
		this.accion = accion;
	}


	/**
	 * Obtiene el pagina actual.
	 *
	 * @return el pagina actual
	 */
	public String getPaginaActual() {
		return paginaActual;
	}


	/**
	 * Establece el pagina actual.
	 *
	 * @param paginaActual el nuevo pagina actual
	 */
	public void setPaginaActual(String paginaActual) {
		this.paginaActual = paginaActual;
	}


	/**
	 * Obtiene el paginas totales.
	 *
	 * @return el paginas totales
	 */
	public String getPaginasTotales() {
		return paginasTotales;
	}


	/**
	 * Establece el paginas totales.
	 *
	 * @param paginasTotales el nuevo paginas totales
	 */
	public void setPaginasTotales(String paginasTotales) {
		this.paginasTotales = paginasTotales;
	}


	/**
	 * Obtiene el direccion.
	 *
	 * @return el direccion
	 */
	public String getDireccion() {
		return direccion;
	}


	/**
	 * Establece el direccion.
	 *
	 * @param direccion el nuevo direccion
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
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
	 * Obtiene el campo.
	 *
	 * @return el campo
	 */
	public String getCampo() {
		return campo;
	}


	/**
	 * Establece el campo.
	 *
	 * @param campo el nuevo campo
	 */
	public void setCampo(String campo) {
		this.campo = campo;
	}


	/**
	 * Obtiene el cambios.
	 *
	 * @return el cambios
	 */
	public String getCambios() {
		return cambios;
	}


	/**
	 * Establece el cambios.
	 *
	 * @param cambios el nuevo cambios
	 */
	public void setCambios(String cambios) {
		this.cambios = cambios;
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
		
		if("Enviar".equalsIgnoreCase(accion)){
			if(de == null || de.equalsIgnoreCase("") ){
				request.setAttribute(STRING_ERRORPARA, STRING_ERRORPARA);
				SpringErrors.add("dsErrorPara", new SpringMessage("field.correoElectronico.error.deVacio"));				
			}else{
				if (validacion.validateEmail(de)){
					request.setAttribute(STRING_ERRORPARA, STRING_ERRORPARA);
					SpringErrors.add("dsErrorPara", new SpringMessage("field.correoElectronico.error.formatoIncorrecto"));	
				}
			}
			
			if (asunto ==null || asunto.equalsIgnoreCase("")){
				request.setAttribute("errorAsunto", "errorAsunto");
				SpringErrors.add("dsErrorAsunto", new SpringMessage("field.correoElectronico.error.asuntoVacio"));
			}
			
			if (cuerpoMensaje ==null || cuerpoMensaje.equalsIgnoreCase("")){
				request.setAttribute("errorMensaje", "errorMensaje");
				SpringErrors.add("dsErrorMensaje", new SpringMessage("field.correoElectronico.error.mensajeVacio"));
			}
		}
		
		return SpringErrors;
	}
	


	/**
	 * Obtiene el entorno.
	 *
	 * @return the entorno
	 */
	public String getEntorno() {
		return entorno;
	}


	/**
	 * Establece el entorno.
	 *
	 * @param entorno the entorno to set
	 */
	public void setEntorno(String entorno) {
		this.entorno = entorno;
	}


	/**
	 * Obtiene el solicitudes sel.
	 *
	 * @return the solicitudesSel
	 */
	public String[] getSolicitudesSel() {
		return solicitudesSel;
	}


	/**
	 * Establece el solicitudes sel.
	 *
	 * @param solicitudesSel the solicitudesSel to set
	 */
	public void setSolicitudesSel(String[] solicitudesSel) {
		this.solicitudesSel = solicitudesSel;
	}


	/**
	 * Obtiene el id correo.
	 *
	 * @return the idCorreo
	 */
	public String getIdCorreo() {
		return idCorreo;
	}


	/**
	 * Establece el id correo.
	 *
	 * @param idCorreo the idCorreo to set
	 */
	public void setIdCorreo(String idCorreo) {
		this.idCorreo = idCorreo;
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
	
	
}

