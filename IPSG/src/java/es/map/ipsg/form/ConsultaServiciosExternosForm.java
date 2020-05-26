package es.map.ipsg.form;

import javax.servlet.http.HttpServletRequest;

import es.map.ips.common.spring.SpringErrors;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringMapping;
import es.map.ips.common.spring.SpringMessage;
import es.map.ipsg.util.UtilesIPSG;
import es.map.ipsg.util.Validacion;

/**
 * El Class ConsultaServiciosExternosForm.
 */
public class ConsultaServiciosExternosForm extends SpringForm {

	/** el pagina actual. */
	private String paginaActual;
	
	/** el paginas totales. */
	private String paginasTotales;
	
	/** el accion. */
	private String accion;
	
	/** el campo. */
	private String campo;
	
	/** el num registro. */
	private String numRegistro;
	
	/** el direccion. */
	private String direccion;
	
	/** el cambios. */
	private String cambios;
	
	/** el fecha desde. */
	private String fechaDesde;
	
	/** el fecha hasta. */
	private String fechaHasta;
	
	/** el id servicio. */
	private String idServicio;
	
	/** el resultado. */
	private String resultado;
	
	/** el numero pagina ir. */
	private Integer numeroPaginaIr;
	
	/** el pulsa ir. */
	private boolean pulsaIr;
	
	/** La constante STRING_ERRORFECHADESDE. */
	private static final String STRING_ERRORFECHADESDE = "ErrorFechaDesde";
	
	/** La constante STRING_ERRORFECHAHASTA. */
	private static final String STRING_ERRORFECHAHASTA = "ErrorFechaHasta";
	
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
	 * Obtiene el fecha desde.
	 *
	 * @return el fecha desde
	 */
	public String getFechaDesde() {
		return fechaDesde;
	}
	
	/**
	 * Establece el fecha desde.
	 *
	 * @param fechaDesde el nuevo fecha desde
	 */
	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}
	
	/**
	 * Obtiene el fecha hasta.
	 *
	 * @return el fecha hasta
	 */
	public String getFechaHasta() {
		return fechaHasta;
	}
	
	/**
	 * Establece el fecha hasta.
	 *
	 * @param fechaHasta el nuevo fecha hasta
	 */
	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
	}
	
	/**
	 * Obtiene el id servicio.
	 *
	 * @return el id servicio
	 */
	public String getIdServicio() {
		return idServicio;
	}
	
	/**
	 * Establece el id servicio.
	 *
	 * @param idServicio el nuevo id servicio
	 */
	public void setIdServicio(String idServicio) {
		this.idServicio = idServicio;
	}
	
	/**
	 * Obtiene el resultado.
	 *
	 * @return el resultado
	 */
	public String getResultado() {
		return resultado;
	}
	
	/**
	 * Establece el resultado.
	 *
	 * @param resultado el nuevo resultado
	 */
	public void setResultado(String resultado) {
		this.resultado = resultado;
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
}
