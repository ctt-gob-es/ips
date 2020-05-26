package es.map.ipsg.form;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;

import es.map.ips.common.spring.SpringErrors;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringMapping;
import es.map.ips.common.spring.SpringMessage;

/**
 * El Class DatosPropiosConfForm.
 */
public class DatosPropiosConfForm extends SpringForm{

	/** el id. */
	private String id;
	
	/** el id desplegable. */
	private String idDesplegable;
	
	/** el descripcion. */
	private String descripcion;
	
	/** el accion. */
	private String accion;
	
	/** el submit. */
	private String submit;
	
	
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
	 * Obtiene el id.
	 *
	 * @return el id
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Establece el id.
	 *
	 * @param id el nuevo id
	 */
	public void setId(String id) {
		this.id = id;
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
	
	/**
	 * Obtiene el id desplegable.
	 *
	 * @return el id desplegable
	 */
	public String getIdDesplegable() {
		return idDesplegable;
	}
	
	/**
	 * Establece el id desplegable.
	 *
	 * @param idDesplegable el nuevo id desplegable
	 */
	public void setIdDesplegable(String idDesplegable) {
		this.idDesplegable = idDesplegable;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.SpringForm#validate(es.map.ips.common.spring.SpringMapping, javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public SpringErrors validate(SpringMapping mapping,
			HttpServletRequest request) {
		SpringErrors SpringErrors = new SpringErrors();
		
		if("GUARDAR CAMPO".equalsIgnoreCase(accion) || "MODIFICAR CAMPO".equalsIgnoreCase(accion)){
			if (StringUtils.isEmpty(id)) {

				request.setAttribute("errorTituloCampo", "errorTituloCampo");
				SpringErrors.add("dserrorTituloCampo", new SpringMessage(
						"field.camposPropios.errores.id"));
			}
			if (StringUtils.isEmpty(descripcion)) {

				request.setAttribute("errorTituloCampoMulti", "errorTituloCampoMulti");
				SpringErrors.add("dserrorTituloCampoMulti", new SpringMessage(
						"field.camposPropios.errores.descripcion"));
			}
			if ("MODIFICAR CAMPO".equalsIgnoreCase(accion)) {
				if (StringUtils.isEmpty(idDesplegable)) {

					request.setAttribute("errorTituloCampoMulti", "errorTituloCampoMulti");
					SpringErrors.add("dserrorTituloCampoMulti", new SpringMessage(
							"field.camposPropios.errores.idDesplegable"));
				}
			}
		}
		
		return SpringErrors;
	}
}
