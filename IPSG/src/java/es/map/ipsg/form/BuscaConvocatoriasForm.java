package es.map.ipsg.form;

import java.util.ArrayList;
import java.util.List;

import es.map.ips.common.spring.SpringForm;
import es.map.ipsg.bean.CentroGestorBean;

/**
 * El Class BuscaConvocatoriasForm.
 */
public class BuscaConvocatoriasForm extends SpringForm {

	/** el id convocatoria. */
	private String idConvocatoria;
	
	/** el ejercicio. */
	private String ejercicio;
	
	/** el estado convocatoria. */
	private String estadoConvocatoria;
	
	/** el centro gestor. */
	private String centroGestor;
	
	/** el ds centro gestor. */
	private String dsCentroGestor;
	
	/** el cuerpo escala. */
	private String cuerpoEscala;
	
	/** el ds cuerpo escala. */
	private String dsCuerpoEscala;
	
	/** el grupo. */
	private String grupo;
	
	/** el forma acceso. */
	private String formaAcceso;
	
	/** el fecha inicio. */
	private String fechaInicio;
	
	/** el fecha fin. */
	private String fechaFin;
	
	/** el fecha BOE. */
	private String fechaBOE;
	
	/** el pagina actual. */
	private String paginaActual;
	
	/** el paginas totales. */
	private String paginasTotales;
	
	/** el accion. */
	private String accion;
	
	/** el campo. */
	private String campo;
	
	/** el direccion. */
	private String direccion;
	
	/** el eliminar. */
	private boolean eliminar;
	
	/** el modificar. */
	private boolean modificar;
	
	/** el actualizacion. */
	private boolean actualizacion;
	
	/** el num registro. */
	private String numRegistro;
	
	/** el cambios. */
	private String cambios;
		
	/** el desc. */
	private String desc;
	
	/** el convocatorias sel. */
	private String[] convocatoriasSel;
	
	/** el numero pagina ir. */
	private Integer numeroPaginaIr;
	
	/** el pulsa ir. */
	private boolean pulsaIr;
	
	/** el id modelo. */
	private int idModelo;

	/*INI-TRA042*/
	/** el lista centros gestores. */
	private List<CentroGestorBean> listaCentrosGestores = new ArrayList<CentroGestorBean>();
	/*FIN-TRA042*/
	
	/**
	 * Obtiene el desc.
	 *
	 * @return el desc
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * Establece el desc.
	 *
	 * @param desc el nuevo desc
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * Obtiene el fecha BOE.
	 *
	 * @return el fecha BOE
	 */
	public String getFechaBOE() {
		return fechaBOE;
	}

	/**
	 * Establece el fecha BOE.
	 *
	 * @param fechaBOE el nuevo fecha BOE
	 */
	public void setFechaBOE(String fechaBOE) {
		this.fechaBOE = fechaBOE;
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
	 * Comprueba si es actualizacion.
	 *
	 * @return verdadero, si es actualizacion
	 */
	public boolean isActualizacion() {
		return actualizacion;
	}

	/**
	 * Establece el actualizacion.
	 *
	 * @param actualizacion el nuevo actualizacion
	 */
	public void setActualizacion(boolean actualizacion) {
		this.actualizacion = actualizacion;
	}

	/**
	 * Comprueba si es modificar.
	 *
	 * @return verdadero, si es modificar
	 */
	public boolean isModificar() {
		return modificar;
	}

	/**
	 * Establece el modificar.
	 *
	 * @param modificar el nuevo modificar
	 */
	public void setModificar(boolean modificar) {
		this.modificar = modificar;
	}

	/**
	 * Comprueba si es eliminar.
	 *
	 * @return verdadero, si es eliminar
	 */
	public boolean isEliminar() {
		return eliminar;
	}

	/**
	 * Establece el eliminar.
	 *
	 * @param eliminar el nuevo eliminar
	 */
	public void setEliminar(boolean eliminar) {
		this.eliminar = eliminar;
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
	 * Obtiene el id convocatoria.
	 *
	 * @return el id convocatoria
	 */
	public String getIdConvocatoria() {
		return idConvocatoria;
	}

	/**
	 * Establece el id convocatoria.
	 *
	 * @param idConvocatoria el nuevo id convocatoria
	 */
	public void setIdConvocatoria(String idConvocatoria) {
		this.idConvocatoria = idConvocatoria;
	}

	/**
	 * Obtiene el ejercicio.
	 *
	 * @return el ejercicio
	 */
	public String getEjercicio() {
		return ejercicio;
	}

	/**
	 * Establece el ejercicio.
	 *
	 * @param ejercicio el nuevo ejercicio
	 */
	public void setEjercicio(String ejercicio) {
		this.ejercicio = ejercicio;
	}

	/**
	 * Obtiene el estado convocatoria.
	 *
	 * @return el estado convocatoria
	 */
	public String getEstadoConvocatoria() {
		return estadoConvocatoria;
	}

	/**
	 * Establece el estado convocatoria.
	 *
	 * @param estadoConvocatoria el nuevo estado convocatoria
	 */
	public void setEstadoConvocatoria(String estadoConvocatoria) {
		this.estadoConvocatoria = estadoConvocatoria;
	}

	/**
	 * Obtiene el centro gestor.
	 *
	 * @return el centro gestor
	 */
	public String getCentroGestor() {
		return centroGestor;
	}

	/**
	 * Establece el centro gestor.
	 *
	 * @param centroGestor el nuevo centro gestor
	 */
	public void setCentroGestor(String centroGestor) {
		this.centroGestor = centroGestor;
	}

	/**
	 * Obtiene el cuerpo escala.
	 *
	 * @return el cuerpo escala
	 */
	public String getCuerpoEscala() {
		return cuerpoEscala;
	}

	/**
	 * Establece el cuerpo escala.
	 *
	 * @param cuerpoEscala el nuevo cuerpo escala
	 */
	public void setCuerpoEscala(String cuerpoEscala) {
		this.cuerpoEscala = cuerpoEscala;
	}

	/**
	 * Obtiene el grupo.
	 *
	 * @return el grupo
	 */
	public String getGrupo() {
		return grupo;
	}

	/**
	 * Establece el grupo.
	 *
	 * @param grupo el nuevo grupo
	 */
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	/**
	 * Obtiene el forma acceso.
	 *
	 * @return el forma acceso
	 */
	public String getFormaAcceso() {
		return formaAcceso;
	}

	/**
	 * Establece el forma acceso.
	 *
	 * @param formaAcceso el nuevo forma acceso
	 */
	public void setFormaAcceso(String formaAcceso) {
		this.formaAcceso = formaAcceso;
	}

	/**
	 * Obtiene el fecha inicio.
	 *
	 * @return el fecha inicio
	 */
	public String getFechaInicio() {
		return fechaInicio;
	}

	/**
	 * Establece el fecha inicio.
	 *
	 * @param fechaInicio el nuevo fecha inicio
	 */
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	/**
	 * Obtiene el fecha fin.
	 *
	 * @return el fecha fin
	 */
	public String getFechaFin() {
		return fechaFin;
	}

	/**
	 * Establece el fecha fin.
	 *
	 * @param fechaFin el nuevo fecha fin
	 */
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
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
	 * Obtiene el convocatorias sel.
	 *
	 * @return el convocatorias sel
	 */
	public String[] getConvocatoriasSel() {
		return convocatoriasSel;
	}

	/**
	 * Establece el convocatorias sel.
	 *
	 * @param convocatoriasSel el nuevo convocatorias sel
	 */
	public void setConvocatoriasSel(String[] convocatoriasSel) {
		this.convocatoriasSel = convocatoriasSel;
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
	 * Obtiene el ds cuerpo escala.
	 *
	 * @return el ds cuerpo escala
	 */
	public String getDsCuerpoEscala() {
		return dsCuerpoEscala;
	}

	/**
	 * Establece el ds cuerpo escala.
	 *
	 * @param dsCuerpoEscala el nuevo ds cuerpo escala
	 */
	public void setDsCuerpoEscala(String dsCuerpoEscala) {
		this.dsCuerpoEscala = dsCuerpoEscala;
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
	 * Obtiene el id modelo.
	 *
	 * @return el id modelo
	 */
	public int getIdModelo() {
		return idModelo;
	}

	/**
	 * Establece el id modelo.
	 *
	 * @param idModelo el nuevo id modelo
	 */
	public void setIdModelo(int idModelo) {
		this.idModelo = idModelo;
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
