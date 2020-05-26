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
 * Clase BuscarSolicitudesForm.
 *
 * @author amartinl
 */
public class VerificarEdadSolicitantesForm extends SpringForm {

	/** el fecha min. */
	private String fechaMin;
	
	/** el fecha max. */
	private String fechaMax;
	
	/** el id convocatoria. */
	private String[] idConvocatoria;
	
	


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

	
	


	/**
	 * Obtiene el id convocatoria.
	 *
	 * @return el id convocatoria
	 */
	public String [] getIdConvocatoria() {
		return idConvocatoria;
	}


	/**
	 * Establece el id convocatoria.
	 *
	 * @param idConvocatoria el nuevo id convocatoria
	 */
	public void setIdConvocatoria(String [] idConvocatoria) {
		this.idConvocatoria = idConvocatoria;
	}


	/**
	 * Obtiene el fecha min.
	 *
	 * @return el fecha min
	 */
	public String getFechaMin() {
		return fechaMin;
	}


	/**
	 * Establece el fecha min.
	 *
	 * @param fechaMin el nuevo fecha min
	 */
	public void setFechaMin(String fechaMin) {
		this.fechaMin = fechaMin;
	}


	/**
	 * Obtiene el fecha max.
	 *
	 * @return el fecha max
	 */
	public String getFechaMax() {
		return fechaMax;
	}


	/**
	 * Establece el fecha max.
	 *
	 * @param fechaMax el nuevo fecha max
	 */
	public void setFechaMax(String fechaMax) {
		this.fechaMax = fechaMax;
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
		SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
		Date dFechaMin = null;
		Date dFechaMax = null;

		if("VERIFICAR".equalsIgnoreCase(accion))
		{
			if(fechaMin == null || fechaMin.equals("") || fechaMax == null || fechaMax.equals(""))
			{
				request.setAttribute("errorFechasInformadas", "errorFechasInformadas");
				SpringErrors.add("dsErrorFechasInformadas", new SpringMessage("field.verificarEdad.errores.fechasNoInformadas"));
			}
			else
			{
				if(fechaMin != null && !fechaMin.equalsIgnoreCase("") && !validacion.isFechaValida(fechaMin))
				{
					request.setAttribute("errorFechaMin", "errorFechaMin");
					SpringErrors.add("dsErrorFechaMin", new SpringMessage("field.verificarEdad.errores.fechaMinNoValido"));
				}	
				if(fechaMax != null && !fechaMax.equalsIgnoreCase("") && !validacion.isFechaValida(fechaMax))
				{
					request.setAttribute("errorFechaMax", "errorFechaMax");
					SpringErrors.add("dsErrorFechaMax", new SpringMessage("field.verificarEdad.errores.fechaMaxNoValido"));
				}
				validateAux(validacion,dFechaMin,dFechaMax,formatoDelTexto,request,SpringErrors);
				
			}
				
		}
		return SpringErrors;
	}
	
	/**
	 * Validate aux.
	 *
	 * @param validacion el validacion
	 * @param dFechaMin el d fecha min
	 * @param dFechaMax el d fecha max
	 * @param formatoDelTexto el formato del texto
	 * @param request el request
	 * @param SpringErrors el spring errors
	 */
	public void validateAux(Validacion validacion, Date dFechaMin, Date dFechaMax, SimpleDateFormat formatoDelTexto, HttpServletRequest request, SpringErrors SpringErrors) {
		
		if(validacion.isFechaValida(fechaMin) && validacion.isFechaValida(fechaMax))
		{
	
			try
			{
				dFechaMin = formatoDelTexto.parse(fechaMin);
				dFechaMax = formatoDelTexto.parse(fechaMax);
				if (dFechaMin!=null && dFechaMax !=null && dFechaMin.after(dFechaMax))
				{
					
						request.setAttribute("errorFechaMayorMin", "errorFechaMayorMin");
						SpringErrors.add("dsErrorFechaMayorMin", new SpringMessage(
								"field.verificarEdad.errores.fechaMayorMin"));
					
				}
				
			}catch (ParseException ex) {
				ex.printStackTrace();
			}
		}
	}
}

