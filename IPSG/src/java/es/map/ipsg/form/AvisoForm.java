package es.map.ipsg.form;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import es.map.ips.common.spring.SpringErrors;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringMapping;
import es.map.ips.common.spring.SpringMessage;
import es.map.ipsg.util.Constantes;
import es.map.ipsg.util.Validacion;

/**
 * El Class AvisoForm.
 *
 * @author djimenez
 */
public class AvisoForm extends SpringForm {
	
	/** el id aviso. */
	private Integer idAviso;
	
	/** el texto. */
	private String texto;
	
	/** el fecha inicio. */
	private String fechaInicio;
	
	/** el fecha fin. */
	private String fechaFin;
	
	/** el id estado aviso. */
	private Integer idEstadoAviso;
	
	/** el campo. */
	//Variables Paginacion
	private String campo;
	
	/** el direccion. */
	private String direccion;
	
	/** el pagina actual. */
	private String paginaActual;
	
	/** el paginas totales. */
	private String paginasTotales;
	
	/** el submit. */
	private String submit;
	
	/** el num registro. */
	private String numRegistro;
	
	/** el mensaje. */
	private String mensaje;
	
	/** el cambios. */
	private String cambios;
	
	/** el numero pagina ir. */
	private Integer numeroPaginaIr;
	
	/** el pulsa ir. */
	private boolean pulsaIr;
	
	/** La constante STRING_ERRORFECHAINICIO. */
	private static final String STRING_ERRORFECHAINICIO = "errorfechaInicio";
	
	/** La constante STRING_ERRORFECHAFIN. */
	private static final String STRING_ERRORFECHAFIN = "errorfechaFin";
	
	/**
	 * Obtiene el id aviso.
	 *
	 * @return the idAviso
	 */
	public Integer getIdAviso() {
		return idAviso;
	}
	
	/**
	 * Establece el id aviso.
	 *
	 * @param idAviso the idAviso to set
	 */
	public void setIdAviso(Integer idAviso) {
		this.idAviso = idAviso;
	}
	
	/**
	 * Obtiene el texto.
	 *
	 * @return the texto
	 */
	public String getTexto() {
		return texto;
	}
	
	/**
	 * Establece el texto.
	 *
	 * @param texto the texto to set
	 */
	public void setTexto(String texto) {
		this.texto = texto;
	}
	
	/**
	 * Obtiene el fecha inicio.
	 *
	 * @return the fechaInicio
	 */
	public String getFechaInicio() {
		return fechaInicio;
	}
	
	/**
	 * Establece el fecha inicio.
	 *
	 * @param fechaInicio the fechaInicio to set
	 */
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	
	/**
	 * Obtiene el fecha fin.
	 *
	 * @return the fechaFin
	 */
	public String getFechaFin() {
		return fechaFin;
	}
	
	/**
	 * Establece el fecha fin.
	 *
	 * @param fechaFin the fechaFin to set
	 */
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}
	
	/**
	 * Obtiene el id estado aviso.
	 *
	 * @return the idEstadoAviso
	 */
	public Integer getIdEstadoAviso() {
		return idEstadoAviso;
	}
	
	/**
	 * Establece el id estado aviso.
	 *
	 * @param idEstadoAviso the idEstadoAviso to set
	 */
	public void setIdEstadoAviso(Integer idEstadoAviso) {
		this.idEstadoAviso = idEstadoAviso;
	}
	
	/**
	 * Obtiene el campo.
	 *
	 * @return the campo
	 */
	public String getCampo() {
		return campo;
	}
	
	/**
	 * Establece el campo.
	 *
	 * @param campo the campo to set
	 */
	public void setCampo(String campo) {
		this.campo = campo;
	}
	
	/**
	 * Obtiene el direccion.
	 *
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}
	
	/**
	 * Establece el direccion.
	 *
	 * @param direccion the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	/**
	 * Obtiene el pagina actual.
	 *
	 * @return the paginaActual
	 */
	public String getPaginaActual() {
		return paginaActual;
	}
	
	/**
	 * Establece el pagina actual.
	 *
	 * @param paginaActual the paginaActual to set
	 */
	public void setPaginaActual(String paginaActual) {
		this.paginaActual = paginaActual;
	}
	
	/**
	 * Obtiene el paginas totales.
	 *
	 * @return the paginasTotales
	 */
	public String getPaginasTotales() {
		return paginasTotales;
	}
	
	/**
	 * Establece el paginas totales.
	 *
	 * @param paginasTotales the paginasTotales to set
	 */
	public void setPaginasTotales(String paginasTotales) {
		this.paginasTotales = paginasTotales;
	}
	
	/**
	 * Obtiene el submit.
	 *
	 * @return the submit
	 */
	public String getSubmit() {
		return submit;
	}
	
	/**
	 * Establece el submit.
	 *
	 * @param submit the submit to set
	 */
	public void setSubmit(String submit) {
		this.submit = submit;
	}
	
	/**
	 * Obtiene el num registro.
	 *
	 * @return the numRegistro
	 */
	public String getNumRegistro() {
		return numRegistro;
	}
	
	/**
	 * Establece el num registro.
	 *
	 * @param numRegistro the numRegistro to set
	 */
	public void setNumRegistro(String numRegistro) {
		this.numRegistro = numRegistro;
	}
	
	/**
	 * Obtiene el mensaje.
	 *
	 * @return the mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}
	
	/**
	 * Establece el mensaje.
	 *
	 * @param mensaje the mensaje to set
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	/**
	 * Obtiene el cambios.
	 *
	 * @return the cambios
	 */
	public String getCambios() {
		return cambios;
	}
	
	/**
	 * Establece el cambios.
	 *
	 * @param cambios the cambios to set
	 */
	public void setCambios(String cambios) {
		this.cambios = cambios;
	}
	
	/**
	 * Validate.
	 *
	 * @param mapping el mapping
	 * @param request el request
	 * @return the desEstadoAviso
	 */
	public SpringErrors validate(SpringMapping mapping,	HttpServletRequest request) {
		
		String menu=(String) request.getParameter("menu");
		SpringErrors SpringErrors = new SpringErrors();
		Validacion validacion = new Validacion();
		SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
		Date dFechaInicio = null;
		Date dFechaFin = null;
		Date fechaSistema=new Date();
		
		if (menu!=null && !menu.equals("S") ) {
			dFechaInicio = fechaInicioValidate(SpringErrors,request,validacion,formatoDelTexto,dFechaInicio);
					
			
			if (fechaFin != null && !"".equals(fechaFin)){
				if (!fechaFin.toString().equalsIgnoreCase("")){
					dFechaFin = fechaFinValidate(validacion,request,SpringErrors,dFechaFin,formatoDelTexto,fechaSistema);
					}
				}else{
					request.setAttribute(STRING_ERRORFECHAFIN, STRING_ERRORFECHAFIN);				
					SpringErrors.add("dsErrorfechaFin", new SpringMessage(
							"field.aviso.errores.fechaFinVacia"));
				}
			
			
			
			if (dFechaInicio!=null && dFechaFin!=null && (dFechaInicio.getTime()>dFechaFin.getTime())){
				
					request.setAttribute("errorFechaMayorDesde", "errorFechaMayorDesde");
					SpringErrors.add("dsErrorFechaMayorBOE", new SpringMessage(
							"field.aviso.errores.fechaMayorDesde"));
				
			}
			if(texto.length()==0){
				request.setAttribute("errorTexto", "errorTexto");
				SpringErrors.add("dsErrorTexto", new SpringMessage(
						"field.aviso.errores.textoVacia"));
				
			}
			
		}
		return SpringErrors;
	}
	
	/**
	 * Fecha fin validate.
	 *
	 * @param validacion el validacion
	 * @param request el request
	 * @param SpringErrors el spring errors
	 * @param dFechaFin el d fecha fin
	 * @param formatoDelTexto el formato del texto
	 * @param fechaSistema el fecha sistema
	 * @return el date
	 */
	public Date fechaFinValidate(Validacion validacion, HttpServletRequest request, SpringErrors SpringErrors, Date dFechaFin, SimpleDateFormat formatoDelTexto, Date fechaSistema) {
		if (!validacion.isFechaValida(fechaFin.toString())) {
			request.setAttribute(STRING_ERRORFECHAFIN, STRING_ERRORFECHAFIN);
			SpringErrors.add("dsErrorfechaFin", new SpringMessage(
			"field.aviso.errores.fechaHastaNoValida"));
			}
		else{
			try{
				dFechaFin = (Date) formatoDelTexto.parse(fechaFin.toString());
				
				}catch (ParseException ex) {
					ex.printStackTrace();
					}					
				
		//Si la fecha de fin es menor a la actual no se puede marcar como activa
		Calendar aux = Calendar.getInstance();
		aux.setTime(dFechaFin);
		aux.add(Calendar.DATE,1);
		Date fechaAuxFin = aux.getTime();
			if (fechaAuxFin.getTime()<fechaSistema.getTime()&&idEstadoAviso==Constantes.ESTADO_AVISO_ACTIVO) {
			request.setAttribute("fechaFinEstadoActivo", "fechaFinEstadoActivo");
			SpringErrors.add("dsFechaFinEstadoActivo", new SpringMessage(
			"field.aviso.errores.fechaFinEstadoActivo"));
			}
		
		}
		return dFechaFin;
		
	}
	
	/**
	 * Fecha inicio validate.
	 *
	 * @param SpringErrors el spring errors
	 * @param request el request
	 * @param validacion el validacion
	 * @param formatoDelTexto el formato del texto
	 * @param dFechaInicio el d fecha inicio
	 * @return el date
	 */
	public Date fechaInicioValidate(SpringErrors SpringErrors, HttpServletRequest request, Validacion validacion, SimpleDateFormat formatoDelTexto, Date dFechaInicio) {
		if (fechaInicio != null && !"".equals(fechaInicio)){
			if (!fechaInicio.toString().equalsIgnoreCase("")){
				if (!validacion.isFechaValida((fechaInicio.toString()))) {
					request.setAttribute(STRING_ERRORFECHAINICIO, STRING_ERRORFECHAINICIO);				
					SpringErrors.add("dsErrorfechaInicio", new SpringMessage(
							"field.aviso.errores.fechaDesdeNoValida"));
				}
				else{
					try{
						dFechaInicio = (Date) formatoDelTexto.parse(fechaInicio.toString());
					
					}catch (ParseException ex) {
						ex.printStackTrace();
						}
					}
			}
			
		}else{
			request.setAttribute(STRING_ERRORFECHAINICIO, STRING_ERRORFECHAINICIO);				
			SpringErrors.add("dsErrorfechaInicio", new SpringMessage(
					"field.aviso.errores.fechaInicioVacia"));
		}
		return dFechaInicio;
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
}
