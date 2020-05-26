package es.map.ipsg.form;

import javax.servlet.http.HttpServletRequest;

import es.map.ips.common.spring.SpringErrors;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringMapping;
import es.map.ips.common.spring.SpringMessage;
import es.map.ipsg.util.Constantes;

/**
 * El Class ParametrosConfiguracionForm.
 */
public class ParametrosConfiguracionForm extends SpringForm {
	
	/** el id. */
	private String[] id;
	
	/** el valor campos. */
	private String[] valorCampos;
	
	/** el nombre campos. */
	private String[] nombreCampos;
	
	/** el descripcion. */
	private String descripcion;
	
	/** el accion. */
	private String accion;	
	
	/** el submit. */
	private String submit;
	
	/** el contador. */
	private String contador;
	
	/** el valor contrasenia nueva. */
	private String valorContraseniaNueva;
	
	/**
	 * Obtiene el id.
	 *
	 * @return the id
	 */
	public String[] getId() {
		return id;
	}
	
	/**
	 * Establece el id.
	 *
	 * @param id the id to set
	 */
	public void setId(String[] id) {
		this.id = id;
	}
	
	/**
	 * Obtiene el descripcion.
	 *
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	
	/**
	 * Establece el descripcion.
	 *
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
	
	
	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.SpringForm#validate(es.map.ips.common.spring.SpringMapping, javax.servlet.http.HttpServletRequest)
	 */
	public SpringErrors validate(SpringMapping mapping,	HttpServletRequest request) {
		SpringErrors SpringErrors = new SpringErrors();
	
		boolean encontradoValorBlanco = false;
		
		if(valorCampos != null && valorCampos.length>0){
			
				for(int i=0;i<valorCampos.length && encontradoValorBlanco==false;i++){
					if(Integer.valueOf(id[i]) != Constantes.PARAMETRO_CONFIGURACION_ID_PASSWORD && (valorCampos [i] == null || valorCampos[i].equalsIgnoreCase(""))){//El password puede ser vacio
						
							request.setAttribute("errorValorCampo", "errorValorCampo");
							SpringErrors.add("dsErrorValorCampo", new SpringMessage("field.parametroConfiguracion.errores.valorCampoRequerido"));
							encontradoValorBlanco = true;
						
					}
				}
				validateAux(request,SpringErrors);								
			
		}
		
		return SpringErrors;
	}
	
	/**
	 * Validate aux.
	 *
	 * @param request el request
	 * @param SpringErrors el spring errors
	 */
	public void validateAux(HttpServletRequest request, SpringErrors SpringErrors) {
		if(nombreCampos!=null){
			for(int i=0;i<nombreCampos.length;i++){
				if(nombreCampos[i].equalsIgnoreCase(Constantes.PARAMETRO_CONFIGURACION_NOMBRE_CAMPO_METODO_NUMERO_SOLICITUD)){					
					if(!valorCampos[i].equalsIgnoreCase(Constantes.PARAMETRO_CONFIGURACION_VALOR_CAMPO_METODO_NUMERO_SOLICITUD_EJB)
						&& !valorCampos[i].equalsIgnoreCase(Constantes.PARAMETRO_CONFIGURACION_VALOR_CAMPO_METODO_NUMERO_SOLICITUD_LOCAL)){
						
						request.setAttribute("errorValorCampoIncorrecto", "errorValorCampoIncorrecto");
						SpringErrors.add("dsErrorValorCampoIncorrecto", new SpringMessage("field.parametroConfiguracion.errores.valorCampoIncorrecto"));
					}else{
						valorCampos[i]= valorCampos[i].toUpperCase();
					}
				}
			}
		}
	}
	
	/**
	 * Obtiene el valor campos.
	 *
	 * @return the valorCampos
	 */
	public String[] getValorCampos() {
		return valorCampos;
	}
	
	/**
	 * Establece el valor campos.
	 *
	 * @param valorCampos the valorCampos to set
	 */
	public void setValorCampos(String[] valorCampos) {
		this.valorCampos = valorCampos;
	}
	
	/**
	 * Obtiene el nombre campos.
	 *
	 * @return the nombreCampos
	 */
	public String[] getNombreCampos() {
		return nombreCampos;
	}
	
	/**
	 * Establece el nombre campos.
	 *
	 * @param nombreCampos the nombreCampos to set
	 */
	public void setNombreCampos(String[] nombreCampos) {
		this.nombreCampos = nombreCampos;
	}
	
	/**
	 * Obtiene el contador.
	 *
	 * @return el contador
	 */
	public String getContador() {
		return contador;
	}
	
	/**
	 * Establece el contador.
	 *
	 * @param contador el nuevo contador
	 */
	public void setContador(String contador) {
		this.contador = contador;
	}
	
	/**
	 * Obtiene el valor contrasenia nueva.
	 *
	 * @return el valor contrasenia nueva
	 */
	public String getValorContraseniaNueva() {
		return valorContraseniaNueva;
	}
	
	/**
	 * Establece el valor contrasenia nueva.
	 *
	 * @param valorContraseniaNueva el nuevo valor contrasenia nueva
	 */
	public void setValorContraseniaNueva(String valorContraseniaNueva) {
		this.valorContraseniaNueva = valorContraseniaNueva;
	}
	
	
}
