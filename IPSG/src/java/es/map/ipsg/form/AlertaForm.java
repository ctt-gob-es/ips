package es.map.ipsg.form;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;

import es.map.ips.common.spring.SpringErrors;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringMapping;
import es.map.ips.common.spring.SpringMessage;
import es.map.ipsg.bean.CentroGestorBean;
import es.map.ipsg.util.Validacion;

/**
 * El Class AlertaForm.
 *
 * @author djimenez
 */
public class AlertaForm extends SpringForm {
	
	/** el id. */
	private Integer id;
	
	/** el id tipo. */
	private Integer idTipo;
	
	/** el id perfil. */
	private Integer idPerfil;
	
	/** el estado. */
	private Integer estado;
	
	/** el id centro gestor. */
	private String idCentroGestor;
	
	/** el ds centro gestor. */
	private String dsCentroGestor;
	
	/** el id centro gestor busqueda. */
	private String idCentroGestorBusqueda;
	
	/** el codigo centro gestor. */
	private String codigoCentroGestor;
	
	/** el id modo. */
	private Integer idModo;
	
	/** el des tipo alerta. */
	private String desTipoAlerta;
	
	/** el id usuario. */
	private String[] idUsuario;
	
	/** el id usuario seleccionados. */
	private String[] idUsuarioSeleccionados;
	
	/** el tipo alerta. */
	private String tipoAlerta;
	
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
	
	/*INI-TRA042*/
	/** el lista centros gestores. */
	private List<CentroGestorBean> listaCentrosGestores = new ArrayList<CentroGestorBean>();
	/*FIN-TRA042*/
	
	/** La constante STRING_ERRORTIPOALERTA. */
	private static final String STRING_ERRORTIPOALERTA = "ErrorTipoAlerta";
	
	/** La constante STRING_ERRORMODOALERTA. */
	private static final String STRING_ERRORMODOALERTA = "ErrorModoAlerta";
	
	/** La constante STRING_ERRORUSUARIO. */
	private static final String STRING_ERRORUSUARIO = "ErrorUsuario";
	
	/**
	 * Obtiene el tipo alerta.
	 *
	 * @return el tipo alerta
	 */
	public String getTipoAlerta() {
		return tipoAlerta;
	}
	
	/**
	 * Establece el tipo alerta.
	 *
	 * @param tipoAlerta el nuevo tipo alerta
	 */
	public void setTipoAlerta(String tipoAlerta) {
		this.tipoAlerta = tipoAlerta;
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
	 * Obtiene el id tipo.
	 *
	 * @return the idTipo
	 */
	public Integer getIdTipo() {
		return idTipo;
	}
	
	/**
	 * Establece el id tipo.
	 *
	 * @param idTipo the idTipo to set
	 */
	public void setIdTipo(Integer idTipo) {
		this.idTipo = idTipo;
	}
	
	/**
	 * Obtiene el id perfil.
	 *
	 * @return the idPerfil
	 */
	public Integer getIdPerfil() {
		return idPerfil;
	}
	
	/**
	 * Establece el id perfil.
	 *
	 * @param idPerfil the idPerfil to set
	 */
	public void setIdPerfil(Integer idPerfil) {
		this.idPerfil = idPerfil;
	}
	
	/**
	 * Obtiene el estado.
	 *
	 * @return the estado
	 */
	public Integer getEstado() {
		return estado;
	}
	
	/**
	 * Establece el estado.
	 *
	 * @param estado the estado to set
	 */
	public void setEstado(Integer estado) {
		this.estado = estado;
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
	
	/**
	 * Obtiene el codigo centro gestor.
	 *
	 * @return the codigoCentroGestor
	 */
	public String getCodigoCentroGestor() {
		return codigoCentroGestor;
	}
	
	/**
	 * Establece el codigo centro gestor.
	 *
	 * @param codigoCentroGestor the codigoCentroGestor to set
	 */
	public void setCodigoCentroGestor(String codigoCentroGestor) {
		this.codigoCentroGestor = codigoCentroGestor;
	}
	
	/**
	 * Obtiene el id modo.
	 *
	 * @return the idModoAlerta
	 */
	public Integer getIdModo() {
		return idModo;
	}
	
	/**
	 * Establece el id modo.
	 *
	 * @param idModo el nuevo id modo
	 */
	public void setIdModo(Integer idModo) {
		this.idModo = idModo;
	}
	
	/**
	 * Obtiene el des tipo alerta.
	 *
	 * @return the desTipoAlerta
	 */
	public String getDesTipoAlerta() {
		return desTipoAlerta;
	}
	
	/**
	 * Establece el des tipo alerta.
	 *
	 * @param desTipoAlerta the desTipoAlerta to set
	 */
	public void setDesTipoAlerta(String desTipoAlerta) {
		this.desTipoAlerta = desTipoAlerta;
	}
	
	/**
	 * Obtiene el id usuario.
	 *
	 * @return the idUsuario
	 */
	public String[] getIdUsuario() {
		return idUsuario;
	}
	
	/**
	 * Establece el id usuario.
	 *
	 * @param idUsuario the idUsuario to set
	 */
	public void setIdUsuario(String[] idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	/**
	 * Obtiene el id usuario seleccionados.
	 *
	 * @return the idUsuarioSeleccionados
	 */
	public String[] getIdUsuarioSeleccionados() {
		return idUsuarioSeleccionados;
	}
	
	/**
	 * Establece el id usuario seleccionados.
	 *
	 * @param idUsuarioSeleccionados the idUsuarioSeleccionados to set
	 */
	public void setIdUsuarioSeleccionados(String[] idUsuarioSeleccionados) {
		this.idUsuarioSeleccionados = idUsuarioSeleccionados;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.SpringForm#validate(es.map.ips.common.spring.SpringMapping, javax.servlet.http.HttpServletRequest)
	 */
	public SpringErrors validate(SpringMapping mapping,
			HttpServletRequest request) {
		
		String menu=(String) request.getParameter("menu");
		SpringErrors SpringErrors = new SpringErrors();
		Validacion validacion = new Validacion();
		if (menu!=null && !menu.equals("S")){
			if (idCentroGestor!=null && !"".equals(idCentroGestor) && !validacion.isNumeric(idCentroGestor)){
				
				request.setAttribute("ErrorCentroGestor", "ErrorCentroGestor");
				SpringErrors.add("dsErrorfechaFin", new SpringMessage("field.alerta.alta.ErrorCentroGestorFormato"));
					
			} else if (StringUtils.isEmpty(idCentroGestor)) {
				request.setAttribute(STRING_ERRORUSUARIO, STRING_ERRORUSUARIO);
				SpringErrors.add(STRING_ERRORUSUARIO, new SpringMessage("field.alerta.alta.ErrorCentroGestor"));		
			}
			if (idTipo==null || idTipo==0){
				request.setAttribute(STRING_ERRORTIPOALERTA, STRING_ERRORTIPOALERTA);
				SpringErrors.add(STRING_ERRORTIPOALERTA, new SpringMessage("field.alerta.alta.ErrorTipoAlerta"));
			}
			if ( idModo==null ||idModo==0 ){
				request.setAttribute(STRING_ERRORMODOALERTA, STRING_ERRORMODOALERTA);
				SpringErrors.add(STRING_ERRORMODOALERTA, new SpringMessage("field.alerta.alta.ErrorModoAlerta"));
			}
			
			if (idModo!=null && idModo==2 && idUsuarioSeleccionados==null){
				request.setAttribute(STRING_ERRORUSUARIO, STRING_ERRORUSUARIO);
				SpringErrors.add(STRING_ERRORUSUARIO, new SpringMessage("field.alerta.alta.ErrorUsuario"));
			}
			
		}	
		return SpringErrors;
	}
	
	/**
	 * Obtiene el id centro gestor busqueda.
	 *
	 * @return the idCentroGestorBusqueda
	 */
	public String getIdCentroGestorBusqueda() {
		return idCentroGestorBusqueda;
	}
	
	/**
	 * Establece el id centro gestor busqueda.
	 *
	 * @param idCentroGestorBusqueda the idCentroGestorBusqueda to set
	 */
	public void setIdCentroGestorBusqueda(String idCentroGestorBusqueda) {
		this.idCentroGestorBusqueda = idCentroGestorBusqueda;
	}
	
	/**
	 * Obtiene el ds centro gestor.
	 *
	 * @return el ds centro gestor
	 */
	public String getDsCentroGestor() {
		return dsCentroGestor;
	}
	
	/**
	 * Establece el ds centro gestor.
	 *
	 * @param dsCentroGestor el nuevo ds centro gestor
	 */
	public void setDsCentroGestor(String dsCentroGestor) {
		this.dsCentroGestor = dsCentroGestor;
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

	/*INI-TRA042*/
	/**
	 * @return the listaCentrosGestores
	 */
	public List<CentroGestorBean> getListaCentrosGestores() {
		return listaCentrosGestores;
	}

	/**
	 * @param listaCentrosGestores the listaCentrosGestores to set
	 */
	public void setListaCentrosGestores(List<CentroGestorBean> listaCentrosGestores) {
		this.listaCentrosGestores = listaCentrosGestores;
	}
	/*FIN-TRA042*/
}
