package es.map.ipsg.form;

import javax.servlet.http.HttpServletRequest;

import es.map.ips.common.spring.SpringErrors;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringMapping;
import es.map.ips.common.spring.SpringMessage;
import es.map.ipsg.util.Validacion;

/**
 * El Class MotivoReduccionTasaForm.
 */
public class MotivoReduccionTasaForm extends SpringForm {

	/** el id. */
	private String id;
	
	/** el descripcion. */
	private String descripcion;
	
	/** el estado. */
	private char estado;
	
	/** el submit. */
	private String submit;
	
	/** el texto explicativo. */
	private String textoExplicativo;
	
	/** el porcentaje descuento. */
	private String porcentajeDescuento;
	
	/** el campo. */
	private String campo;
	
	/** el pagina actual. */
	private String paginaActual;
	
	/** el paginas totales. */
	private String paginasTotales;
	
	/** el direccion. */
	private String direccion;
	
	/** el num registro. */
	private String numRegistro;
	
	/** el modificar. */
	public boolean modificar;
	
	/** el eliminar. */
	public boolean eliminar;
	
	/** el forma acceso. */
	public String formaAcceso;
	
	/** el id centro gestor. */
	private String idCentroGestor;
	
	/** el id grupo. */
	private String idGrupo;
	
	/** el id categoria. */
	private String idCategoria;
	
	/** el accion. */
	private String accion;
	
	/** el cambios. */
	private String cambios;
	
	/** el codigo. */
	private String codigo;
	
	/** el visibilidad. */
	private Boolean visibilidad;
	
	/** el numero pagina ir. */
	private Integer numeroPaginaIr;
	
	/** el pulsa ir. */
	private boolean pulsaIr;
	
	/** La constante STRING_ERRORTEXTOEXPLICATIVO. */
	private static final String STRING_ERRORTEXTOEXPLICATIVO = "errorTextoExplicativo";
	
	/** La constante STRING_DSERRORTEXTOEXPLICATIVO. */
	private static final String STRING_DSERRORTEXTOEXPLICATIVO = "dsErrorTextoExplicativo";
	
	/** La constante STRING_ERRORPORCENTAJEDESCUENTO. */
	private static final String STRING_ERRORPORCENTAJEDESCUENTO = "errorPorcentajeDescuento";
	
	/** La constante STRING_DSERRORPORCENTAJEDESCUENTO. */
	private static final String STRING_DSERRORPORCENTAJEDESCUENTO = "dsErrorPorcentajeDescuento";
	
	/** La constante STRING_ERRORCODIGO. */
	private static final String STRING_ERRORCODIGO = "errorCodigo";
	
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
	 * Obtiene el descripcion.
	 *
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return (descripcion!=null ? descripcion.trim() : null);
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
	 * Obtiene el estado.
	 *
	 * @return the estado
	 */
	public char getEstado() {
		return estado;
	}

	/**
	 * Establece el estado.
	 *
	 * @param estado the estado to set
	 */
	public void setEstado(char estado) {
		this.estado = estado;
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
	 * Obtiene el texto explicativo.
	 *
	 * @return the textoExplicativo
	 */
	public String getTextoExplicativo() {
		return textoExplicativo;
	}

	/**
	 * Establece el texto explicativo.
	 *
	 * @param textoExplicativo the textoExplicativo to set
	 */
	public void setTextoExplicativo(String textoExplicativo) {
		this.textoExplicativo = textoExplicativo;
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
	 * Comprueba si es modificar.
	 *
	 * @return the modificar
	 */
	public boolean isModificar() {
		return modificar;
	}

	/**
	 * Establece el modificar.
	 *
	 * @param modificar the modificar to set
	 */
	public void setModificar(boolean modificar) {
		this.modificar = modificar;
	}

	/**
	 * Comprueba si es eliminar.
	 *
	 * @return the eliminar
	 */
	public boolean isEliminar() {
		return eliminar;
	}

	/**
	 * Establece el eliminar.
	 *
	 * @param eliminar the eliminar to set
	 */
	public void setEliminar(boolean eliminar) {
		this.eliminar = eliminar;
	}

	/**
	 * Obtiene el forma acceso.
	 *
	 * @return the formaAcceso
	 */
	public String getFormaAcceso() {
		return formaAcceso;
	}

	/**
	 * Establece el forma acceso.
	 *
	 * @param formaAcceso the formaAcceso to set
	 */
	public void setFormaAcceso(String formaAcceso) {
		this.formaAcceso = formaAcceso;
	}

	/**
	 * Obtiene el id centro gestor.
	 *
	 * @return the idCentroGestor
	 */
	public String getIdCentroGestor() {
		return idCentroGestor;
	}

	/**
	 * Establece el id centro gestor.
	 *
	 * @param idCentroGestor the idCentroGestor to set
	 */
	public void setIdCentroGestor(String idCentroGestor) {
		this.idCentroGestor = idCentroGestor;
	}

	/**
	 * Obtiene el id grupo.
	 *
	 * @return the idGrupo
	 */
	public String getIdGrupo() {
		return idGrupo;
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
	 * Establece el id grupo.
	 *
	 * @param idGrupo the idGrupo to set
	 */
	public void setIdGrupo(String idGrupo) {
		this.idGrupo = idGrupo;
	}

	/**
	 * Obtiene el id categoria.
	 *
	 * @return the idCategoria
	 */
	public String getIdCategoria() {
		return idCategoria;
	}

	/**
	 * Establece el id categoria.
	 *
	 * @param idCategoria the idCategoria to set
	 */
	public void setIdCategoria(String idCategoria) {
		this.idCategoria = idCategoria;
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
 	 * Obtiene el porcentaje descuento.
 	 *
 	 * @return the porcentajeDescuento
 	 */
	public String getPorcentajeDescuento() {
		return porcentajeDescuento;
	}

	/**
	 * Establece el porcentaje descuento.
	 *
	 * @param porcentajeDescuento the porcentajeDescuento to set
	 */
	public void setPorcentajeDescuento(String porcentajeDescuento) {
		this.porcentajeDescuento = porcentajeDescuento;
	}
	
	/**
	 * Establece el codigo.
	 *
	 * @param codigo el nuevo codigo
	 * @return the codigo
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * Obtiene el codigo.
	 *
	 * @return el codigo
	 */	
	public String getCodigo() {
		return codigo;
	} 	
	
	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.SpringForm#validate(es.map.ips.common.spring.SpringMapping, javax.servlet.http.HttpServletRequest)
	 */
	public SpringErrors validate(SpringMapping mapping,	HttpServletRequest request) {
		
		SpringErrors SpringErrors = new SpringErrors();
		Validacion validacion = new Validacion();
		
		
		
		if("GUARDAR".equalsIgnoreCase(accion) || "MODIFICAR".equalsIgnoreCase(accion)){
			if(descripcion == null || descripcion.equalsIgnoreCase("")){
				request.setAttribute("errorDescripcion", "errorDescripcion");
				SpringErrors.add("dsErrorDescripcion", new SpringMessage("field.cuerpoEscala.errores.descripcionRequerido"));
			}						
						
						
			
			
			if(textoExplicativo == null || textoExplicativo.equalsIgnoreCase("")){
				request.setAttribute(STRING_ERRORTEXTOEXPLICATIVO, STRING_ERRORTEXTOEXPLICATIVO);
				SpringErrors.add(STRING_DSERRORTEXTOEXPLICATIVO, new SpringMessage("field.motivoReduccionTasa.errores.textoExplicativoRequerido"));
			}else{
				if (textoExplicativo.length()>2000){
					request.setAttribute(STRING_ERRORTEXTOEXPLICATIVO, STRING_ERRORTEXTOEXPLICATIVO);
					SpringErrors.add(STRING_DSERRORTEXTOEXPLICATIVO, new SpringMessage("field.motivoReduccionTasa.errores.textoExplicativoLongitud"));
				}
			}		
			validateAux(SpringErrors,request,validacion);
			
		}
		
		return SpringErrors;
	}

	/**
	 * Validate aux.
	 *
	 * @param SpringErrors el spring errors
	 * @param request el request
	 * @param validacion el validacion
	 */
	public void validateAux(SpringErrors SpringErrors, HttpServletRequest request, Validacion validacion) {
		
		if(porcentajeDescuento == null || porcentajeDescuento.equalsIgnoreCase("")){
			request.setAttribute(STRING_ERRORPORCENTAJEDESCUENTO, STRING_ERRORPORCENTAJEDESCUENTO);
			SpringErrors.add(STRING_DSERRORPORCENTAJEDESCUENTO, new SpringMessage("field.motivoReduccionTasa.errores.porcentajeDescuentoRequerido"));
		}else{
			
			if(!validacion.isNumeric(porcentajeDescuento)){
				request.setAttribute(STRING_ERRORPORCENTAJEDESCUENTO, STRING_ERRORPORCENTAJEDESCUENTO);
				SpringErrors.add(STRING_DSERRORPORCENTAJEDESCUENTO, new SpringMessage("field.motivoReduccionTasa.errores.porcentajeDescuentoNumerico"));
			}else{
				Integer intPorcentaje=Integer.parseInt(porcentajeDescuento);
				if (intPorcentaje.intValue()<0 || intPorcentaje.intValue()>100){
				 request.setAttribute(STRING_ERRORPORCENTAJEDESCUENTO, STRING_ERRORPORCENTAJEDESCUENTO);
					SpringErrors.add(STRING_DSERRORPORCENTAJEDESCUENTO, new SpringMessage("field.motivoReduccionTasa.errores.porcentajeDescuentoIntervalo"));
					}
			}
			
			
		}
		
		if(codigo == null || codigo.equalsIgnoreCase("")){
			request.setAttribute(STRING_ERRORCODIGO, STRING_ERRORCODIGO);
			SpringErrors.add(STRING_DSERRORTEXTOEXPLICATIVO, new SpringMessage("field.motivoReduccionTasa.errores.codigo"));
		}else{
			if (codigo.length()>10){
				request.setAttribute(STRING_ERRORCODIGO, STRING_ERRORCODIGO);
				SpringErrors.add("dsErrorCodigo", new SpringMessage("field.motivoReduccionTasa.errores.codigoLongitud"));
			}
		}
	}
	
	/**
	 * Obtiene el visibilidad.
	 *
	 * @return el visibilidad
	 */
	public Boolean getVisibilidad() {
		return visibilidad;
	}

	/**
	 * Establece el visibilidad.
	 *
	 * @param visibilidad el nuevo visibilidad
	 */
	public void setVisibilidad(Boolean visibilidad) {
		this.visibilidad = visibilidad;
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
