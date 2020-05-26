package es.map.ipsg.form;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import es.map.ips.common.spring.SpringErrors;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringMapping;
import es.map.ips.common.spring.SpringMessage;

/**
 * El Class UsuariosAplicacionForm.
 */
public class UsuariosAplicacionForm extends SpringForm{
	
	/** el id. */
	private Integer id;
	
	/** el usuario. */
	private String usuario;
	
	/** el nombre. */
	private String nombre;
	
	/** el descripcion. */
	private String descripcion;
	
	/** el estado. */
	private String estado;
	
	/** el accion. */
	private String accion;
	
	/** el submit. */
	private String submit;
	
	/** el cambios. */
	private String cambios;
	
	/** el correcto. */
	private String correcto;
	
	/** el pagina actual. */
	private String paginaActual;
	
	/** el paginas totales. */
	private String paginasTotales;
	
	/** el numero pagina ir. */
	private Integer numeroPaginaIr;
	
	/** el pulsa ir. */
	private boolean pulsaIr;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(UsuariosAplicacionForm.class);
	
	/** La constante STRING_USUARIO. */
	private static final String STRING_USUARIO = "errorUsuario";
	

	/**
	 * Obtiene el logger.
	 *
	 * @return el logger
	 */
	public static Logger getLogger() {
		return logger;
	}
	
	/**
	 * Obtiene el string usuario.
	 *
	 * @return el string usuario
	 */
	public static String getStringUsuario() {
		return STRING_USUARIO;
	}
	
	/**
	 * Obtiene el id.
	 *
	 * @return el id
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * Establece el id.
	 *
	 * @param id el nuevo id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * Obtiene el usuario.
	 *
	 * @return el usuario
	 */
	public String getUsuario() {
		return usuario;
	}
	
	/**
	 * Establece el usuario.
	 *
	 * @param usuario el nuevo usuario
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	/**
	 * Obtiene el nombre.
	 *
	 * @return el nombre
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * Establece el nombre.
	 *
	 * @param nombre el nuevo nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
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
	 * Obtiene el estado.
	 *
	 * @return el estado
	 */
	public String getEstado() {
		return estado;
	}
	
	/**
	 * Establece el estado.
	 *
	 * @param estado el nuevo estado
	 */
	public void setEstado(String estado) {
		this.estado = estado;
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
	 * Obtiene el correcto.
	 *
	 * @return el correcto
	 */
	public String getCorrecto() {
		return correcto;
	}
	
	/**
	 * Establece el correcto.
	 *
	 * @param correcto el nuevo correcto
	 */
	public void setCorrecto(String correcto) {
		this.correcto = correcto;
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
	public SpringErrors validate(SpringMapping mapping,	HttpServletRequest request) {
		logger.info("Validando");
		SpringErrors SpringErrors = new SpringErrors();
		
		if("GUARDAR".equalsIgnoreCase(accion) || "MODIFICAR".equalsIgnoreCase(accion)){
			
			validateAux(SpringErrors,request);		

			if(usuario == null || usuario.equalsIgnoreCase("")){
				request.setAttribute("errorUsuario", "errorUsuario");
				SpringErrors.add("dsErrorUsuario", new SpringMessage("field.usuario.errores.usuarioRequerido"));
			}
			
			if(nombre == null || nombre.equalsIgnoreCase("")){
				request.setAttribute("errorNombre", "errorNombre");
				SpringErrors.add("dsErrorNombre", new SpringMessage("field.usuario.errores.nombreRequerido"));
			}
			
			if (!StringUtils.isEmpty(usuario)) {
				Pattern pat = Pattern.compile("[‡·‚‰ËÈÍÎÏÌÓÔÚÛÙˆ˘˙˚¸¿¡¬ƒ√»… ÀÃÕŒœ“”‘÷’Ÿ⁄€‹]");
				Matcher mat = pat.matcher(usuario);
				 
				if(mat.find()){
					request.setAttribute("errorUsuario", "errorUsuario");
					SpringErrors.add("dsErrorUsuario", new SpringMessage("field.usuario.errores.usuarioInvalido"));
			     }
			}
		}
		
		return SpringErrors;
	}
	
	/**
	 * Validate aux.
	 *
	 * @param SpringErrors el spring errors
	 * @param request el request
	 */
	public void validateAux(SpringErrors SpringErrors, HttpServletRequest request) {
		if(descripcion == null || descripcion.equalsIgnoreCase("")){
			request.setAttribute("errorDescripcion", "errorDescripcion");
			SpringErrors.add("dsErrorDescripcion", new SpringMessage("field.cuerpoEscala.errores.descripcionRequerido"));
		}
	}

}
