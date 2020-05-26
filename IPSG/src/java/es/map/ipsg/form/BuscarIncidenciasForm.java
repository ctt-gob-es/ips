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
 * El Class BuscarIncidenciasForm.
 */
public class BuscarIncidenciasForm extends SpringForm {
	
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
	
	/** el fecha envio. */
	private String fechaEnvio;
	
	/** el tipo. */
	private String tipo;
	
	/** el submit. */
	private String submit;
	
	/** el campo. */
	private String campo;
	
	/** el pagina actual. */
	private String paginaActual;
	
	/** el paginas totales. */
	private String paginasTotales;
	
	/** el fecha desde. */
	private String fechaDesde;
	
	/** el fecha hasta. */
	private String fechaHasta;
	
	/** el direccion. */
	private String direccion;
	
	/** el num registro. */
	private String numRegistro;
	
	/** el accion. */
	private String accion;
	
	/** el cambios. */
	private String cambios;
	
	/** el numero pagina ir. */
	private Integer numeroPaginaIr;
	
	/** el pulsa ir. */
	private boolean pulsaIr;
	
	/**
	 * Obtiene el fecha desde.
	 *
	 * @return the fechaDesde
	 */
	public String getFechaDesde() {
		return fechaDesde;
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
	 * Obtiene el fecha hasta.
	 *
	 * @return the fechaHasta
	 */
	public String getFechaHasta() {
		return fechaHasta;
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
	 * Obtiene el id.
	 *
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * Establece el id.
	 *
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * Obtiene el nif.
	 *
	 * @return the nif
	 */
	public String getNif() {
		return nif;
	}
	
	/**
	 * Establece el nif.
	 *
	 * @param nif the nif to set
	 */
	public void setNif(String nif) {
		this.nif = nif;
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
	 * Obtiene el fecha envio.
	 *
	 * @return the fechaEnvio
	 */
	public String getFechaEnvio() {
		return fechaEnvio;
	}
	
	/**
	 * Establece el fecha envio.
	 *
	 * @param fechaEnvio the fechaEnvio to set
	 */
	public void setFechaEnvio(String fechaEnvio) {
		this.fechaEnvio = fechaEnvio;
	}
	
	/**
	 * Obtiene el tipo.
	 *
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}
	
	/**
	 * Establece el tipo.
	 *
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
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
 * Obtiene el accion.
 *
 * @return the accion
 */
public String getAccion() {
	return accion;
}

/**
 * Establece el accion.
 *
 * @param accion the accion to set
 */
public void setAccion(String accion) {
	this.accion = accion;
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

/* (non-Javadoc)
 * @see es.map.ips.common.spring.SpringForm#validate(es.map.ips.common.spring.SpringMapping, javax.servlet.http.HttpServletRequest)
 */
public SpringErrors validate(SpringMapping mapping,
		HttpServletRequest request) {
	
	String menu=(String) request.getParameter("menu");
	SpringErrors SpringErrors = new SpringErrors();
	Validacion validacion = new Validacion();
	SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
	Date dFechaDesde = null;
	Date dFechaHasta = null;
	
	if (menu==null) {
		dFechaDesde = dFechaDesdeValidate(validacion,request,SpringErrors,dFechaDesde,formatoDelTexto);	
		
		if (fechaHasta != null && !fechaHasta.equalsIgnoreCase("")){
			
				if (!validacion.isFechaValida(fechaHasta)) {
					request.setAttribute("errorFechaHasta", "errorFechaHasta");
					SpringErrors.add("dsErrorFechaHasta", new SpringMessage(
					"field.incidencia.errores.fechaHastaNoValida"));
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
						"field.incidencia.errores.fechaMayorDesde"));
			
		}
		
	}
	return SpringErrors;
}

/**
 * D fecha desde validate.
 *
 * @param validacion el validacion
 * @param request el request
 * @param SpringErrors el spring errors
 * @param dFechaDesde el d fecha desde
 * @param formatoDelTexto el formato del texto
 * @return el date
 */
public Date dFechaDesdeValidate(Validacion validacion, HttpServletRequest request, SpringErrors SpringErrors, Date dFechaDesde, SimpleDateFormat formatoDelTexto) {
	if (fechaDesde != null && !fechaDesde.equalsIgnoreCase("")){
		
		if (!validacion.isFechaValida(fechaDesde)) {
			request.setAttribute("errorFechaDesde", "errorFechaDesde");				
			SpringErrors.add("dsErrorFechaDesde", new SpringMessage(
					"field.incidencia.errores.fechaDesdeNoValida"));
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

	
	