package es.map.ipsg.form;

import javax.servlet.http.HttpServletRequest;

import es.map.ips.common.spring.SpringErrors;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringMapping;
import es.map.ips.common.spring.SpringMessage;
import es.map.ipsg.util.UtilesIPSG;
import es.map.ipsg.util.Validacion;

/**
 * El Class LogErroresForm.
 *
 * @author djimenez
 */
public class LogErroresForm extends SpringForm {
	
	/** el id. */
	private String id;
	
	/** el fecha. */
	private String fecha;
	
	/** el fecha desde. */
	private String fechaDesde;
	
	/** el fecha hasta. */
	private String fechaHasta;
	
	/** el id tipo servicio. */
	private String idTipoServicio;
	
	/** el id tipo error. */
	private String idTipoError;
	
	/** el codigo error. */
	private String codigoError;
	
	/** el Num total. */
	private String NumTotal;
	
	/** el numero solicitud. */
	private String numeroSolicitud;
	
	/** el nif. */
	private String nif;
	
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
	
	/** La constante STRING_ERRORFECHADESDE. */
	private static final String STRING_ERRORFECHADESDE = "ErrorFechaDesde";
	
	/** La constante STRING_ERRORFECHAHASTA. */
	private static final String STRING_ERRORFECHAHASTA = "ErrorFechaHasta";

	/**
	 * Obtiene el id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}



	/**
	 * Establece el id.
	 *
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}



	/**
	 * Obtiene el fecha.
	 *
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}



	/**
	 * Establece el fecha.
	 *
	 * @param fecha the fecha to set
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}



	/**
	 * Obtiene el id tipo servicio.
	 *
	 * @return the idTipoServicio
	 */
	public String getIdTipoServicio() {
		return idTipoServicio;
	}



	/**
	 * Establece el id tipo servicio.
	 *
	 * @param idTipoServicio the idTipoServicio to set
	 */
	public void setIdTipoServicio(String idTipoServicio) {
		this.idTipoServicio = idTipoServicio;
	}



	/**
	 * Obtiene el id tipo error.
	 *
	 * @return the idTipoError
	 */
	public String getIdTipoError() {
		return idTipoError;
	}



	/**
	 * Establece el id tipo error.
	 *
	 * @param idTipoError the idTipoError to set
	 */
	public void setIdTipoError(String idTipoError) {
		this.idTipoError = idTipoError;
	}



	/**
	 * Obtiene el codigo error.
	 *
	 * @return the codigoError
	 */
	public String getCodigoError() {
		return codigoError;
	}



	/**
	 * Establece el codigo error.
	 *
	 * @param codigoError the codigoError to set
	 */
	public void setCodigoError(String codigoError) {
		this.codigoError = codigoError;
	}



	/**
	 * Obtiene el num total.
	 *
	 * @return the numTotal
	 */
	public String getNumTotal() {
		return NumTotal;
	}



	/**
	 * Establece el num total.
	 *
	 * @param numTotal the numTotal to set
	 */
	public void setNumTotal(String numTotal) {
		NumTotal = numTotal;
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


	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.SpringForm#validate(es.map.ips.common.spring.SpringMapping, javax.servlet.http.HttpServletRequest)
	 */
	public SpringErrors validate(SpringMapping mapping,
			HttpServletRequest request) {
		
		String menu=(String) request.getParameter("menu");
		SpringErrors SpringErrors = new SpringErrors();
		Validacion validacion = new Validacion();
		UtilesIPSG utilesIPSG=new UtilesIPSG();
		Boolean fechaDesdeOK=true;
		Boolean fechaHastaOK=true;
		if (menu!=null && !menu.equals("S")){
			fechaDesdeOK = validateFechaDesde(SpringErrors,request,validacion,fechaDesdeOK);			
			
			if (fechaHasta!=null && !"".equals(fechaHasta)){
				if (!validacion.isFechaValida(fechaHasta) ){
						request.setAttribute(STRING_ERRORFECHAHASTA, STRING_ERRORFECHAHASTA);
						SpringErrors.add(STRING_ERRORFECHAHASTA, new SpringMessage("field.logErrores.ErrorFechaHasta"));
						fechaHastaOK=false;
					}
			}else{
				fechaHastaOK=false;
			}
			if(fechaDesdeOK && fechaHastaOK && utilesIPSG.stringToDate(fechaHasta).getTime() < utilesIPSG.stringToDate(fechaDesde).getTime()){
				
						request.setAttribute(STRING_ERRORFECHAHASTA, STRING_ERRORFECHAHASTA);
						SpringErrors.add(STRING_ERRORFECHAHASTA, new SpringMessage("field.logErrores.FechaHastaMayorFechaDesde"));
				
			}
		}	
		
		return SpringErrors;
	}

	/**
	 * Validate fecha desde.
	 *
	 * @param SpringErrors el spring errors
	 * @param request el request
	 * @param validacion el validacion
	 * @param fechaDesdeOK el fecha desde OK
	 * @return el boolean
	 */
	public Boolean validateFechaDesde(SpringErrors SpringErrors,HttpServletRequest request,Validacion validacion,Boolean fechaDesdeOK) {
		
		if (fechaDesde!=null && !"".equals(fechaDesde)){
			if (!validacion.isFechaValida(fechaDesde) ){
					request.setAttribute(STRING_ERRORFECHADESDE, STRING_ERRORFECHADESDE);
					SpringErrors.add(STRING_ERRORFECHADESDE, new SpringMessage("field.logErrores.ErrorFechaDesde"));
					fechaDesdeOK=false;
				}
		}else{
			fechaDesdeOK=false;
		}
		return fechaDesdeOK;
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
	 * Obtiene el numero solicitud.
	 *
	 * @return el numero solicitud
	 */
	public String getNumeroSolicitud() {
		return (numeroSolicitud!=null ? numeroSolicitud.trim():null);
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
	 * Obtiene el nif.
	 *
	 * @return el nif
	 */
	public String getNif() {
		return (nif!=null ? nif.trim():null);
	}



	/**
	 * Establece el nif.
	 *
	 * @param nif el nuevo nif
	 */
	public void setNif(String nif) {
		this.nif = nif;
	}


}
