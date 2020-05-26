package es.map.ipsg.bean;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import es.map.ips.model.MotivoReduccionTasa;

/**
 * El Class ConvocatoriasBean.
 */
public class ConvocatoriasBean {
	
/** el id convocatoria. */
private String idConvocatoria;
	
	/** el ejercicio. */
	private String ejercicio;
	
	/** el estado convocatoria. */
	private String estadoConvocatoria;
	
	/** el centro gestor. */
	private String centroGestor;
	
	/** el cod centro gestor. */
	private int codCentroGestor;
	
	/** el siglas centro gestor. */
	private String siglasCentroGestor;
	
	/** el cuerpo escala. */
	private String cuerpoEscala;
	
	/** el grupo. */
	private String grupo;
	
	/** el forma acceso. */
	private String formaAcceso;
	
	/** el fecha inicio. */
	private String fechaInicio;
	
	/** el fecha fin. */
	private String fechaFin;
	
	/** el fecha boe. */
	private String fechaBoe;
	
	/** el eliminar. */
	private boolean eliminar;
	
	/** el detalle. */
	private boolean detalle;
	
	/** el modificar. */
	private boolean modificar;
	
	/** el num total. */
	private int numTotal;
	
	/** el num registros. */
	private String numRegistros;
	
	/** el desc. */
	private String desc;
	
	/** el ministerio. */
	private String ministerio;
	
	/** el ministerio convocatoria. */
	private String ministerioConvocatoria;

	/** el dirigido A. */
	private String dirigidoA;

	/** el id plantilla. */
	private Long idPlantilla;
	
	/** el fecha ini sub. */
	private String fechaIniSub;
	
	/** el fecha fin sub. */
	private String fechaFinSub;	
	
	/** el motivo reduccion tasas. */
	private Set<MotivoReduccionTasa> motivoReduccionTasas = new HashSet<MotivoReduccionTasa>(0);
	
	/** el motivo reduccion tasas completo. */
	private ArrayList<MotivoReduccionTasa> motivoReduccionTasasCompleto;
	
	/** el motivo reduccion tasas incompleto. */
	private ArrayList<MotivoReduccionTasa> motivoReduccionTasasIncompleto;
	
	/** el plantilla propios. */
	private List<PlantillaPropiosBean> plantillaPropios;
	
	private String enlace;
	
	/**
	 * Obtiene el dirigido A.
	 *
	 * @return el dirigido A
	 */
	public String getDirigidoA() {
		return dirigidoA;
	}

	/**
	 * Establece el dirigido A.
	 *
	 * @param dirigidoA el nuevo dirigido A
	 */
	public void setDirigidoA(String dirigidoA) {
		this.dirigidoA = dirigidoA;
	}

	/**
	 * Obtiene el fecha boe.
	 *
	 * @return el fecha boe
	 */
	public String getFechaBoe() {
		return fechaBoe;
	}

	/**
	 * Establece el fecha boe.
	 *
	 * @param fechaBoe el nuevo fecha boe
	 */
	public void setFechaBoe(String fechaBoe) {
		this.fechaBoe = fechaBoe;
	}

	/**
	 * Obtiene el ministerio.
	 *
	 * @return el ministerio
	 */
	public String getMinisterio() {
		return ministerio;
	}

	/**
	 * Establece el ministerio.
	 *
	 * @param ministerio el nuevo ministerio
	 */
	public void setMinisterio(String ministerio) {
		this.ministerio = ministerio;
	}

	/**
	 * Obtiene el ministerio convocatoria.
	 *
	 * @return el ministerio convocatoria
	 */
	public String getMinisterioConvocatoria() {
		return ministerioConvocatoria;
	}

	/**
	 * Establece el ministerio convocatoria.
	 *
	 * @param ministerioConvocatoria el nuevo ministerio convocatoria
	 */
	public void setMinisterioConvocatoria(String ministerioConvocatoria) {
		this.ministerioConvocatoria = ministerioConvocatoria;
	}

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
	 * Obtiene el num registros.
	 *
	 * @return el num registros
	 */
	public String getNumRegistros() {
		return numRegistros;
	}

	/**
	 * Establece el num registros.
	 *
	 * @param numRegistros el nuevo num registros
	 */
	public void setNumRegistros(String numRegistros) {
		this.numRegistros = numRegistros;
	}

	/**
	 * Obtiene el num total.
	 *
	 * @return el num total
	 */
	public int getNumTotal() {
		return numTotal;
	}

	/**
	 * Establece el num total.
	 *
	 * @param numTotal el nuevo num total
	 */
	public void setNumTotal(int numTotal) {
		this.numTotal = numTotal;
	}

	/**
	 * Obtiene el cod centro gestor.
	 *
	 * @return el cod centro gestor
	 */
	public int getCodCentroGestor() {
		return codCentroGestor;
	}

	/**
	 * Establece el cod centro gestor.
	 *
	 * @param codCentroGestor el nuevo cod centro gestor
	 */
	public void setCodCentroGestor(int codCentroGestor) {
		this.codCentroGestor = codCentroGestor;
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
	 * Comprueba si es detalle.
	 *
	 * @return verdadero, si es detalle
	 */
	public boolean isDetalle() {
		return detalle;
	}

	/**
	 * Establece el detalle.
	 *
	 * @param detalle el nuevo detalle
	 */
	public void setDetalle(boolean detalle) {
		this.detalle = detalle;
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
	 * Obtiene el siglas centro gestor.
	 *
	 * @return el siglas centro gestor
	 */
	public String getSiglasCentroGestor() {
		return siglasCentroGestor;
	}

	/**
	 * Establece el siglas centro gestor.
	 *
	 * @param siglasCentroGestor el nuevo siglas centro gestor
	 */
	public void setSiglasCentroGestor(String siglasCentroGestor) {
		this.siglasCentroGestor = siglasCentroGestor;
	}

	/**
	 * Obtiene el id plantilla.
	 *
	 * @return el id plantilla
	 */
	public Long getIdPlantilla() {
		return idPlantilla;
	}

	/**
	 * Establece el id plantilla.
	 *
	 * @param idPlantilla el nuevo id plantilla
	 */
	public void setIdPlantilla(Long idPlantilla) {
		this.idPlantilla = idPlantilla;
	}

	/**
	 * Obtiene el motivo reduccion tasas.
	 *
	 * @return el motivo reduccion tasas
	 */
	public Set getMotivoReduccionTasas() {
		return motivoReduccionTasas;
	}

	/**
	 * Establece el motivo reduccion tasas.
	 *
	 * @param motivoReduccionTasas el nuevo motivo reduccion tasas
	 */
	public void setMotivoReduccionTasas(Set<MotivoReduccionTasa> motivoReduccionTasas) {
		this.motivoReduccionTasas = motivoReduccionTasas;
	}

	/**
	 * Obtiene el motivo reduccion tasas completo.
	 *
	 * @return el motivo reduccion tasas completo
	 */
	public ArrayList<MotivoReduccionTasa> getMotivoReduccionTasasCompleto() {
		return motivoReduccionTasasCompleto;
	}

	/**
	 * Establece el motivo reduccion tasas completo.
	 *
	 * @param motivoReduccionTasasCompleto el nuevo motivo reduccion tasas completo
	 */
	public void setMotivoReduccionTasasCompleto(
			ArrayList<MotivoReduccionTasa> motivoReduccionTasasCompleto) {
		this.motivoReduccionTasasCompleto = motivoReduccionTasasCompleto;
	}

	/**
	 * Obtiene el motivo reduccion tasas incompleto.
	 *
	 * @return el motivo reduccion tasas incompleto
	 */
	public ArrayList<MotivoReduccionTasa> getMotivoReduccionTasasIncompleto() {
		return motivoReduccionTasasIncompleto;
	}

	/**
	 * Establece el motivo reduccion tasas incompleto.
	 *
	 * @param motivoReduccionTasasIncompleto el nuevo motivo reduccion tasas incompleto
	 */
	public void setMotivoReduccionTasasIncompleto(
			ArrayList<MotivoReduccionTasa> motivoReduccionTasasIncompleto) {
		this.motivoReduccionTasasIncompleto = motivoReduccionTasasIncompleto;
	}
	
	/**
	 * Obtiene el plantilla propios.
	 *
	 * @return el plantilla propios
	 */
	public List<PlantillaPropiosBean> getPlantillaPropios() {
		return plantillaPropios;
	}

	/**
	 * Establece el plantilla propios.
	 *
	 * @param plantillaPropios el nuevo plantilla propios
	 */
	public void setPlantillaPropios(List<PlantillaPropiosBean> plantillaPropios) {
		this.plantillaPropios = plantillaPropios;
	}
	
	/**
	 * Obtiene el fecha ini sub.
	 *
	 * @return el fecha ini sub
	 */
	public String getFechaIniSub() {
		return fechaIniSub;
	}

	/**
	 * Establece el fecha ini sub.
	 *
	 * @param fechaIniSub el nuevo fecha ini sub
	 */
	public void setFechaIniSub(String fechaIniSub) {
		this.fechaIniSub = fechaIniSub;
	}

	/**
	 * Obtiene el fecha fin sub.
	 *
	 * @return el fecha fin sub
	 */
	public String getFechaFinSub() {
		return fechaFinSub;
	}

	/**
	 * Establece el fecha fin sub.
	 *
	 * @param fechaFinSub el nuevo fecha fin sub
	 */
	public void setFechaFinSub(String fechaFinSub) {
		this.fechaFinSub = fechaFinSub;
	}

	public String getEnlace() {
		return enlace;
	}

	public void setEnlace(String enlace) {
		this.enlace = enlace;
	}
	

}
