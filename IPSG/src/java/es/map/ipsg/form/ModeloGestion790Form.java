package es.map.ipsg.form;

import javax.servlet.http.HttpServletRequest;

import es.map.ips.common.spring.SpringErrors;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringMapping;
import es.map.ips.common.spring.SpringMessage;
import es.map.ipsg.util.Validacion;

/**
 * El Class ModeloGestion790Form.
 */
public class ModeloGestion790Form extends SpringForm{
	
	/** La constante serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** el id. */
	private String id;
	
	/** el id campo. */
	private String idCampo;
	
	/** el id modelo. */
	private String idModelo;
	
	/** el descripcion. */
	private String descripcion;
	
	/** el num modelo. */
	private String numModelo;
	
	/** el fecha alta. */
	private String fechaAlta;
	
	/** el accion. */
	private String accion;
	
	/** el submit. */
	private String submit;
	
	/** el titulo campo. */
	private String tituloCampo;
	
	/** el descripcion campo. */
	private String descripcionCampo;
	
	/** el titulo campo ca. */
	private String tituloCampo_ca;
	
	/** el titulo campo gl. */
	private String tituloCampo_gl;
	
	/** el titulo campo eu. */
	private String tituloCampo_eu;
	
	/** el titulo campo vl. */
	private String tituloCampo_vl;
	
	/** La constante STRING_ERRORNUMMODELO. */
	private static final String STRING_ERRORNUMMODELO = "errorNumModelo";
	
	/**
	 * Obtiene el id modelo.
	 *
	 * @return el id modelo
	 */
	public String getIdModelo() {
		return idModelo;
	}
	
	/**
	 * Establece el id modelo.
	 *
	 * @param idModelo el nuevo id modelo
	 */
	public void setIdModelo(String idModelo) {
		this.idModelo = idModelo;
	}
	
	/**
	 * Obtiene el id campo.
	 *
	 * @return el id campo
	 */
	public String getIdCampo() {
		return idCampo;
	}
	
	/**
	 * Establece el id campo.
	 *
	 * @param idCampo el nuevo id campo
	 */
	public void setIdCampo(String idCampo) {
		this.idCampo = idCampo;
	}

	/**
	 * Obtiene el titulo campo.
	 *
	 * @return el titulo campo
	 */
	public String getTituloCampo() {
		return tituloCampo;
	}
	
	/**
	 * Establece el titulo campo.
	 *
	 * @param tituloCampo el nuevo titulo campo
	 */
	public void setTituloCampo(String tituloCampo) {
		this.tituloCampo = tituloCampo;
	}
	
	/**
	 * Obtiene el descripcion campo.
	 *
	 * @return el descripcion campo
	 */
	public String getDescripcionCampo() {
		return descripcionCampo;
	}
	
	/**
	 * Establece el descripcion campo.
	 *
	 * @param descripcionCampo el nuevo descripcion campo
	 */
	public void setDescripcionCampo(String descripcionCampo) {
		this.descripcionCampo = descripcionCampo;
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
	 * Obtiene el num modelo.
	 *
	 * @return el num modelo
	 */
	public String getNumModelo() {
		return numModelo;
	}
	
	/**
	 * Establece el num modelo.
	 *
	 * @param numModelo el nuevo num modelo
	 */
	public void setNumModelo(String numModelo) {
		this.numModelo = numModelo;
	}
	
	/**
	 * Obtiene el fecha alta.
	 *
	 * @return el fecha alta
	 */
	public String getFechaAlta() {
		return fechaAlta;
	}
	
	/**
	 * Establece el fecha alta.
	 *
	 * @param fechaAlta el nuevo fecha alta
	 */
	public void setFechaAlta(String fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	
	/**
	 * Obtiene el titulo campo ca.
	 *
	 * @return el titulo campo ca
	 */
	public String getTituloCampo_ca() {
		return tituloCampo_ca;
	}
	
	/**
	 * Establece el titulo campo ca.
	 *
	 * @param tituloCampo_ca el nuevo titulo campo ca
	 */
	public void setTituloCampo_ca(String tituloCampo_ca) {
		this.tituloCampo_ca = tituloCampo_ca;
	}
	
	/**
	 * Obtiene el titulo campo gl.
	 *
	 * @return el titulo campo gl
	 */
	public String getTituloCampo_gl() {
		return tituloCampo_gl;
	}
	
	/**
	 * Establece el titulo campo gl.
	 *
	 * @param tituloCampo_gl el nuevo titulo campo gl
	 */
	public void setTituloCampo_gl(String tituloCampo_gl) {
		this.tituloCampo_gl = tituloCampo_gl;
	}
	
	/**
	 * Obtiene el titulo campo eu.
	 *
	 * @return el titulo campo eu
	 */
	public String getTituloCampo_eu() {
		return tituloCampo_eu;
	}
	
	/**
	 * Establece el titulo campo eu.
	 *
	 * @param tituloCampo_eu el nuevo titulo campo eu
	 */
	public void setTituloCampo_eu(String tituloCampo_eu) {
		this.tituloCampo_eu = tituloCampo_eu;
	}
	
	/**
	 * Obtiene el titulo campo vl.
	 *
	 * @return el titulo campo vl
	 */
	public String getTituloCampo_vl() {
		return tituloCampo_vl;
	}
	
	/**
	 * Establece el titulo campo vl.
	 *
	 * @param tituloCampo_vl el nuevo titulo campo vl
	 */
	public void setTituloCampo_vl(String tituloCampo_vl) {
		this.tituloCampo_vl = tituloCampo_vl;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.SpringForm#validate(es.map.ips.common.spring.SpringMapping, javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public SpringErrors validate(SpringMapping mapping,
			HttpServletRequest request) {
		SpringErrors SpringErrors = new SpringErrors();
		Validacion validacion;
		if ("GUARDAR".equalsIgnoreCase(accion) || "MODIFICAR".equalsIgnoreCase(accion)) {
			
			validateAux(request,SpringErrors);			
			
			if (descripcion == null || descripcion.equalsIgnoreCase("")){
				request.setAttribute("errorDescripcion", "errorDescripcion");
				SpringErrors.add("dsErrorDescripcion", new SpringMessage(
						"field.gestionModelos790.errores.descripcionRequerido"));
			}
			
		}else if("GUARDAR CAMPO".equalsIgnoreCase(accion) || "MODIFICAR CAMPO".equalsIgnoreCase(accion)){
			
			if (tituloCampo == null || tituloCampo.equalsIgnoreCase("")) {

				request.setAttribute("errorTituloCampo", "errorTituloCampo");
				SpringErrors.add("dserrorTituloCampo", new SpringMessage(
						"field.gestionModelos790.errores.tituloRequerido"));
			}
			if (descripcionCampo == null || descripcionCampo.equalsIgnoreCase("")){
				request.setAttribute("errorDescripcion", "errorDescripcion");
				SpringErrors.add("dsErrorDescripcion", new SpringMessage(
						"field.gestionModelos790.errores.descripcionRequerido"));
			}
			if (tituloCampo_ca == null || tituloCampo_ca.equalsIgnoreCase("") 
					|| tituloCampo_eu == null || tituloCampo_eu.equalsIgnoreCase("")
					|| tituloCampo_gl == null || tituloCampo_gl.equalsIgnoreCase("")
					|| tituloCampo_vl == null || tituloCampo_vl.equalsIgnoreCase("")) {

				request.setAttribute("errorTituloCampoMulti", "errorTituloCampoMulti");
				SpringErrors.add("dserrorTituloCampoMulti", new SpringMessage(
						"field.gestionModelos790.errores.titulos.multiidioma"));
			}
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
		if (numModelo == null || numModelo.equalsIgnoreCase("")) {

			request.setAttribute(STRING_ERRORNUMMODELO, STRING_ERRORNUMMODELO);
			SpringErrors.add("dserrorNumModelo", new SpringMessage(
					"field.gestionModelos790.errores.numModeloRequerido"));
		}
		
		if(numModelo!=null && !numModelo.equalsIgnoreCase("")){
		    Validacion validate = new Validacion();
		    boolean blancos=validate.validateEspacios(numModelo);
		    if(!blancos){
		    	request.setAttribute("errorNumModeloBlanco", "errorNumModeloBlanco");
				SpringErrors.add("dserrorNumModeloBlanco", new SpringMessage(
						"field.gestionModelos790.errores.numModeloBlanco"));
		    }else{
				boolean esNumero=validate.validateNumero(numModelo);
				if(!esNumero){
					request.setAttribute(STRING_ERRORNUMMODELO, STRING_ERRORNUMMODELO);
					SpringErrors.add("dserrorNumModelo", new SpringMessage(
							"field.gestionModelos790.errores.numModeloNoNumero"));
				}
		    }
		}
	}
}
