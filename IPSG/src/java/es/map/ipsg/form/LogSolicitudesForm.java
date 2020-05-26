package es.map.ipsg.form;


import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import es.map.ips.common.spring.SpringErrors;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringMapping;
import es.map.ips.common.spring.SpringMessage;
import es.map.ipsg.util.UtilesIPSG;
import es.map.ipsg.util.Validacion;

/**
 * El Class LogSolicitudesForm.
 *
 * @author djimenez
 */
public class LogSolicitudesForm extends SpringForm {
	
	/** el nif. */
	//Busqueda
	private String nif;
	
	/** el fecha desde. */
	private String fechaDesde;
	
	/** el fecha hasta. */
	private String fechaHasta;
	
	/** el numero justificante. */
	private String numeroJustificante;

	/** el id. */
	//resultado
	private String id;
	
	/** el fecha. */
	private String fecha;
	
	/** el estado anterior. */
	private String estadoAnterior;
	
	/** el estado actual. */
	private String estadoActual;
	
	/** el dni ciudadano. */
	private String dniCiudadano;
	
	/** el id convocatoria. */
	private String idConvocatoria;
	
	/** el ds convocatoria. */
	private String dsConvocatoria;
	
	/** el num total. */
	private String numTotal;
	
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
	private static final String STRING_ERRORFECHADESDE = "ErrorfechaDesde";
	
	/** La constante STRING_ERRORFECHAHASTA. */
	private static final String STRING_ERRORFECHAHASTA = "ErrorfechaHasta";
	
	/**
	 * Obtiene el nif.
	 *
	 * @return the nif
	 */
	public String getNif() {
		return (nif!=null ? nif.trim():null);
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
	 * @param fechaHasta el nuevo fecha hasta
	 */
	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
	}
	
	/**
	 * Obtiene el numero justificante.
	 *
	 * @return the numeroJustificante
	 */
	public String getNumeroJustificante() {
		return (numeroJustificante!=null ? numeroJustificante.trim():null);
	}
	
	/**
	 * Establece el numero justificante.
	 *
	 * @param numeroJustificante the numeroJustificante to set
	 */
	public void setNumeroJustificante(String numeroJustificante) {
		this.numeroJustificante = numeroJustificante;
	}
	
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
	 * Obtiene el estado anterior.
	 *
	 * @return the estadoAnterior
	 */
	public String getEstadoAnterior() {
		return estadoAnterior;
	}
	
	/**
	 * Establece el estado anterior.
	 *
	 * @param estadoAnterior the estadoAnterior to set
	 */
	public void setEstadoAnterior(String estadoAnterior) {
		this.estadoAnterior = estadoAnterior;
	}
	
	/**
	 * Obtiene el estado actual.
	 *
	 * @return the estadoActual
	 */
	public String getEstadoActual() {
		return estadoActual;
	}
	
	/**
	 * Establece el estado actual.
	 *
	 * @param estadoActual the estadoActual to set
	 */
	public void setEstadoActual(String estadoActual) {
		this.estadoActual = estadoActual;
	}
	
	/**
	 * Obtiene el dni ciudadano.
	 *
	 * @return the dniCiudadano
	 */
	public String getDniCiudadano() {
		return dniCiudadano;
	}
	
	/**
	 * Establece el dni ciudadano.
	 *
	 * @param dniCiudadano the dniCiudadano to set
	 */
	public void setDniCiudadano(String dniCiudadano) {
		this.dniCiudadano = dniCiudadano;
	}
	
	/**
	 * Obtiene el id convocatoria.
	 *
	 * @return the numeroJustificante
	 */
	
	/**
	 * @return the idConvocatoria
	 */
	public String getIdConvocatoria() {
		return idConvocatoria;
	}
	
	/**
	 * Establece el id convocatoria.
	 *
	 * @param idConvocatoria the idConvocatoria to set
	 */
	public void setIdConvocatoria(String idConvocatoria) {
		this.idConvocatoria = idConvocatoria;
	}
	
	/**
	 * Obtiene el num total.
	 *
	 * @return the numTotal
	 */
	public String getNumTotal() {
		return numTotal;
	}
	
	/**
	 * Establece el num total.
	 *
	 * @param numTotal the numTotal to set
	 */
	public void setNumTotal(String numTotal) {
		this.numTotal = numTotal;
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
	public SpringErrors validate(SpringMapping mapping,HttpServletRequest request) {
		
		String menu=(String) request.getParameter("menu");
		SpringErrors SpringErrors = new SpringErrors();
		SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
		Validacion validacion = new Validacion();
		UtilesIPSG utilesIPSG=new UtilesIPSG();
		if (menu!=null && !menu.equals("S")){
			if (fechaDesde!=null && !"".equals(fechaDesde) && !validacion.isFechaValida(fechaDesde.toString())){
				
						request.setAttribute(STRING_ERRORFECHADESDE, STRING_ERRORFECHADESDE);
						SpringErrors.add(STRING_ERRORFECHADESDE, new SpringMessage("field.logSolicitudes.ErrorFechaDesde"));
					
			}
			if (fechaHasta!=null && !"".equals(fechaHasta) && !validacion.isFechaValida(fechaHasta.toString())){
				
						request.setAttribute(STRING_ERRORFECHAHASTA, STRING_ERRORFECHAHASTA);
						SpringErrors.add(STRING_ERRORFECHAHASTA, new SpringMessage("field.logSolicitudes.ErrorFechaHasta"));
					
			}
			if (fechaDesde!=null && !"".equals(fechaDesde) && fechaHasta!=null && !"".equals(fechaHasta)
					&& utilesIPSG.stringToDate(fechaDesde).getTime()>utilesIPSG.stringToDate(fechaHasta).getTime()){
				
					request.setAttribute("errorFechaMayorDesde", "errorFechaMayorDesde");
					SpringErrors.add("dsErrorFechaMayorBOE", new SpringMessage("field.logSolicitudes.FechaHastaMayorFechaDesde"));
				
			}
		
		}
		return SpringErrors;
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
