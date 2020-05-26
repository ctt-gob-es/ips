/**
 * 
 */
package es.map.ipsg.form;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import es.map.ips.common.spring.SpringErrors;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringMapping;
import es.map.ips.common.spring.SpringMessage;
import es.map.ipsg.util.Validacion;

/**
 * El Class LogOperacionesForm.
 *
 * @author jalonson
 */
public class LogOperacionesForm  extends SpringForm{
	
	
	/** el usuario. */
	private String usuario;
	
	/** el tipo operacion. */
	private String tipoOperacion;
	
	/** el fecha desde. */
	private String fechaDesde;
	
	/** el fecha hasta. */
	private String fechaHasta;
	
	/** el submit. */
	private String submit;
	
	/** el cambios. */
	private String cambios;
	
	/** el pagina actual. */
	private String paginaActual;
	
	/** el paginas totales. */
	private String paginasTotales;
	
	/** el campo. */
	private String campo;
	
	/** el direccion. */
	private String direccion;
	
	/** el num registro. */
	private String numRegistro;
	
	/** el numero pagina ir. */
	private Integer numeroPaginaIr;
	
	/** el pulsa ir. */
	private boolean pulsaIr;
	
	/**
	 * Obtiene el tipo operacion.
	 *
	 * @return the tipoOperacion
	 */
	public String getTipoOperacion() {
		return tipoOperacion;
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
	 * Obtiene el usuario.
	 *
	 * @return the usuario
	 */
	public String getUsuario() {
		return (usuario!=null ? usuario.trim():null);
	}

	/**
	 * Establece el usuario.
	 *
	 * @param usuario the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * Establece el tipo operacion.
	 *
	 * @param tipoOperacion the tipoOperacion to set
	 */
	public void setTipoOperacion(String tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}



	/**
	 * Obtiene el fecha desde.
	 *
	 * @return the fechaDesde
	 */
	public String getFechaDesde() {
		return fechaDesde;
	}



	/**
	 * Obtiene el fecha hasta.
	 *
	 * @return the fechaHasta
	 */
	public String getFechaHasta() {
		return fechaHasta;
	}



	/**
	 * Establece el fecha desde.
	 *
	 * @param fechaDesde the fechaDesde to set
	 */
	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}



	/**
	 * Establece el fecha hasta.
	 *
	 * @param fechaHasta the fechaHasta to set
	 */
	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
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
	 * Obtiene el pagina actual.
	 *
	 * @return the paginaActual
	 */
	public String getPaginaActual() {
		return paginaActual;
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
	 * Obtiene el campo.
	 *
	 * @return the campo
	 */
	public String getCampo() {
		return campo;
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
	 * Establece el pagina actual.
	 *
	 * @param paginaActual the paginaActual to set
	 */
	public void setPaginaActual(String paginaActual) {
		this.paginaActual = paginaActual;
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
	 * Establece el campo.
	 *
	 * @param campo the campo to set
	 */
	public void setCampo(String campo) {
		this.campo = campo;
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


	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.SpringForm#validate(es.map.ips.common.spring.SpringMapping, javax.servlet.http.HttpServletRequest)
	 */
	public SpringErrors validate(SpringMapping mapping,
			HttpServletRequest request) {
		
		System.out.println("ENTRA VALIDATE");
		
		String menu=(String) request.getParameter("menu");
		SpringErrors SpringErrors = new SpringErrors();
		Validacion validacion = new Validacion();
		SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
		Date dFechaDesde = null;
		Date dFechaHasta = null;
		
		if (menu==null) {
			dFechaDesde = validateFechaDesde(validacion,SpringErrors,dFechaDesde,request,formatoDelTexto);
			
						
			if (fechaHasta != null && !fechaHasta.equalsIgnoreCase("")){
				
					if (!validacion.isFechaValida(fechaHasta)) {
						request.setAttribute("errorFechaHasta", "errorFechaHasta");
						SpringErrors.add("dsErrorFechaHasta", new SpringMessage(
						"field.logOperaciones.errores.fechaHastaNoValida"));
						}
					else{
						try{
							dFechaHasta = formatoDelTexto.parse(fechaHasta);
							
							}catch (ParseException ex) {
								ex.printStackTrace();
								}					
							}
					
				}
					
			if (dFechaDesde!=null && dFechaHasta!=null && dFechaDesde.getTime()>dFechaHasta.getTime()){
				
					request.setAttribute("errorFechaMayorDesde", "errorFechaMayorDesde");
					SpringErrors.add("dsErrorFechaMayorBOE", new SpringMessage(
							"field.logOperaciones.errores.fechaMayorDesde"));
				
			}			
		}
		return SpringErrors;
	}

	/**
	 * Validate fecha desde.
	 *
	 * @param validacion el validacion
	 * @param SpringErrors el spring errors
	 * @param dFechaDesde el d fecha desde
	 * @param request el request
	 * @param formatoDelTexto el formato del texto
	 * @return el date
	 */
	public Date validateFechaDesde(Validacion validacion, SpringErrors SpringErrors, Date dFechaDesde, HttpServletRequest request, SimpleDateFormat formatoDelTexto) {
		if (fechaDesde != null && !fechaDesde.equalsIgnoreCase("")){
			
			if (!validacion.isFechaValida(fechaDesde)) {
				request.setAttribute("errorFechaDesde", "errorFechaDesde");				
				SpringErrors.add("dsErrorFechaDesde", new SpringMessage(
						"field.logOperaciones.errores.fechaDesdeNoValida"));
			}
			else{
				try{
				dFechaDesde = formatoDelTexto.parse(fechaDesde);
				
				}catch (ParseException ex) {
					ex.printStackTrace();
					}
				}
		
		
	}
		return dFechaDesde;
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
