package es.map.ipsg.bean;

import java.util.List;

import es.map.ips.model.LogConvocatoria;



/**
 * El Class DetalleConvocatoriaBean.
 */
public class DetalleConvocatoriaBean {
		
	/** el id convocatoria. */
	private long idConvocatoria;
	
	/** el centro gestor. */
	private String centroGestor;
	
	/** el cuerpo escala. */
	private String cuerpoEscala;
	
	/** el ministerio convocante. */
	private String ministerioConvocante;
	
	/** el ejercicio. */
	private String ejercicio;
	
	/** el forma acceso. */
	private String formaAcceso;
	
	/** el importe. */
	private float importe;
	
	/** el fecha boe. */
	private String fechaBoe;
	
	/** el fecha inicio. */
	private String fechaInicio;
	
	/** el fecha fin. */
	private String fechaFin;
	
	/** el fecha inicio sub. */
	private String fechaInicioSub;
	
	/** el fecha fin sub. */
	private String fechaFinSub;	
	
	/** el num plazas totales. */
	private int numPlazasTotales;
	
	/** el num plazas discapacitados. */
	private int numPlazasDiscapacitados;
	
	/** el id estado convocatoria. */
	private Integer idEstadoConvocatoria;
	
	/** el estado convocatoria. */
	private String estadoConvocatoria;
	
	/** el dirigido A. */
	private String dirigidoA;
	
	/** el tipo documentacion anexar. */
	private String tipoDocumentacionAnexar;	
	
	/** el id plantilla. */
	private Long idPlantilla;
	
	/** el provincias examen. */
	private List<String> provinciasExamen;
	
	/** el titulos exigidos. */
	private List<?> titulosExigidos;
	
	/** el motivos exencion. */
	private List<?> motivosExencion;
	
	/** el especialidades. */
	private List<?> especialidades;
	
	/** el otros titulos. */
	private List<?> otrosTitulos;
	
	/** el discapacidad. */
	private List<?> discapacidad;
	
	/** el datos propios. */
	private List<?> datosPropios;
    
    /** el log convocatorias. */
    private List<LogConvocatoria> logConvocatorias;
	
	/** el actualizar. */
	private boolean actualizar;
	
	/** el detalle error. */
	private String detalleError;	
	
	/** el presencial. */
	private boolean presencial;
	
	private String enlace;
	



	/**
	 * Obtiene el detalle error.
	 *
	 * @return el detalle error
	 */
	public String getDetalleError() {
		return detalleError;
	}
	
	/**
	 * Establece el detalle error.
	 *
	 * @param detalleError el nuevo detalle error
	 */
	public void setDetalleError(String detalleError) {
		this.detalleError = detalleError;
	}
	
	/**
	 * Comprueba si es actualizar.
	 *
	 * @return verdadero, si es actualizar
	 */
	public boolean isActualizar() {
		return actualizar;
	}
	
	/**
	 * Establece el actualizar.
	 *
	 * @param actualizar el nuevo actualizar
	 */
	public void setActualizar(boolean actualizar) {
		this.actualizar = actualizar;
	}
	
	/**
	 * Obtiene el id convocatoria.
	 *
	 * @return el id convocatoria
	 */
	public long getIdConvocatoria() {
		return idConvocatoria;
	}
	
	/**
	 * Establece el id convocatoria.
	 *
	 * @param idConvocatoria el nuevo id convocatoria
	 */
	public void setIdConvocatoria(long idConvocatoria) {
		this.idConvocatoria = idConvocatoria;
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
	 * Obtiene el importe.
	 *
	 * @return el importe
	 */
	public float getImporte() {
		return importe;
	}
	
	/**
	 * Establece el importe.
	 *
	 * @param importe el nuevo importe
	 */
	public void setImporte(float importe) {
		this.importe = importe;
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
	 * Obtiene el num plazas totales.
	 *
	 * @return el num plazas totales
	 */
	public int getNumPlazasTotales() {
		return numPlazasTotales;
	}
	
	/**
	 * Establece el num plazas totales.
	 *
	 * @param numPlazasTotales el nuevo num plazas totales
	 */
	public void setNumPlazasTotales(int numPlazasTotales) {
		this.numPlazasTotales = numPlazasTotales;
	}
	
	/**
	 * Obtiene el num plazas discapacitados.
	 *
	 * @return el num plazas discapacitados
	 */
	public int getNumPlazasDiscapacitados() {
		return numPlazasDiscapacitados;
	}
	
	/**
	 * Establece el num plazas discapacitados.
	 *
	 * @param numPlazasDiscapacitados el nuevo num plazas discapacitados
	 */
	public void setNumPlazasDiscapacitados(int numPlazasDiscapacitados) {
		this.numPlazasDiscapacitados = numPlazasDiscapacitados;
	}
	
	/**
	 * Obtiene el id estado convocatoria.
	 *
	 * @return el id estado convocatoria
	 */
	public Integer getIdEstadoConvocatoria() {
		return idEstadoConvocatoria;
	}
	
	/**
	 * Establece el id estado convocatoria.
	 *
	 * @param idEstadoConvocatoria el nuevo id estado convocatoria
	 */
	public void setIdEstadoConvocatoria(Integer idEstadoConvocatoria) {
		this.idEstadoConvocatoria = idEstadoConvocatoria;
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
	 * Obtiene el tipo documentacion anexar.
	 *
	 * @return el tipo documentacion anexar
	 */
	public String getTipoDocumentacionAnexar() {
		return tipoDocumentacionAnexar;
	}
	
	/**
	 * Establece el tipo documentacion anexar.
	 *
	 * @param tipoDocumentacionAnexar el nuevo tipo documentacion anexar
	 */
	public void setTipoDocumentacionAnexar(String tipoDocumentacionAnexar) {
		this.tipoDocumentacionAnexar = tipoDocumentacionAnexar;
	}
	
	/**
	 * Obtiene el provincias examen.
	 *
	 * @return el provincias examen
	 */
	public List<String> getProvinciasExamen() {
		return provinciasExamen;
	}
	
	/**
	 * Establece el provincias examen.
	 *
	 * @param provinciasExamen el nuevo provincias examen
	 */
	public void setProvinciasExamen(List<String> provinciasExamen) {
		this.provinciasExamen = provinciasExamen;
	}
	
	/**
	 * Obtiene el titulos exigidos.
	 *
	 * @return el titulos exigidos
	 */
	public List<?> getTitulosExigidos() {
		return titulosExigidos;
	}
	
	/**
	 * Establece el titulos exigidos.
	 *
	 * @param titulosExigidos el nuevo titulos exigidos
	 */
	public void setTitulosExigidos(List<?> titulosExigidos) {
		this.titulosExigidos = titulosExigidos;
	}
	
	/**
	 * Obtiene el motivos exencion.
	 *
	 * @return el motivos exencion
	 */
	public List<?> getMotivosExencion() {
		return motivosExencion;
	}
	
	/**
	 * Establece el motivos exencion.
	 *
	 * @param motivosExencion el nuevo motivos exencion
	 */
	public void setMotivosExencion(List<?> motivosExencion) {
		this.motivosExencion = motivosExencion;
	}
	
	/**
	 * Obtiene el especialidades.
	 *
	 * @return el especialidades
	 */
	public List<?> getEspecialidades() {
		return especialidades;
	}
	
	/**
	 * Establece el especialidades.
	 *
	 * @param especialidades el nuevo especialidades
	 */
	public void setEspecialidades(List<?> especialidades) {
		this.especialidades = especialidades;
	}
	
	/**
	 * Obtiene el otros titulos.
	 *
	 * @return el otros titulos
	 */
	public List<?> getOtrosTitulos() {
		return otrosTitulos;
	}
	
	/**
	 * Establece el otros titulos.
	 *
	 * @param otrosTitulos el nuevo otros titulos
	 */
	public void setOtrosTitulos(List<?> otrosTitulos) {
		this.otrosTitulos = otrosTitulos;
	}
	
	/**
	 * Obtiene el discapacidad.
	 *
	 * @return el discapacidad
	 */
	public List<?> getDiscapacidad() {
		return discapacidad;
	}
	
	/**
	 * Establece el discapacidad.
	 *
	 * @param discapacidad el nuevo discapacidad
	 */
	public void setDiscapacidad(List<?> discapacidad) {
		this.discapacidad = discapacidad;
	}
	
	/**
	 * Obtiene el log convocatorias.
	 *
	 * @return el log convocatorias
	 */
	public List<LogConvocatoria> getLogConvocatorias() {
		return logConvocatorias;
	}
	
	/**
	 * Establece el log convocatorias.
	 *
	 * @param logConvocatorias el nuevo log convocatorias
	 */
	public void setLogConvocatorias(List<LogConvocatoria> logConvocatorias) {
		this.logConvocatorias = logConvocatorias;
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
	 * Obtiene el ministerio convocante.
	 *
	 * @return el ministerio convocante
	 */
	public String getMinisterioConvocante() {
		return ministerioConvocante;
	}
	
	/**
	 * Establece el ministerio convocante.
	 *
	 * @param ministerioConvocante el nuevo ministerio convocante
	 */
	public void setMinisterioConvocante(String ministerioConvocante) {
		this.ministerioConvocante = ministerioConvocante;
	}
	
	/**
	 * Comprueba si es presencial.
	 *
	 * @return verdadero, si es presencial
	 */
	public boolean isPresencial() {
		return presencial;
	}
	
	/**
	 * Establece el presencial.
	 *
	 * @param presencial el nuevo presencial
	 */
	public void setPresencial(boolean presencial) {
		this.presencial = presencial;
	}
	
	/**
	 * Obtiene el fecha inicio sub.
	 *
	 * @return el fecha inicio sub
	 */
	public String getFechaInicioSub() {
		return fechaInicioSub;
	}
	
	/**
	 * Establece el fecha inicio sub.
	 *
	 * @param fechaInicioSub el nuevo fecha inicio sub
	 */
	public void setFechaInicioSub(String fechaInicioSub) {
		this.fechaInicioSub = fechaInicioSub;
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
	
	/**
	 * Obtiene el datos propios.
	 *
	 * @return el datos propios
	 */
	public List<?> getDatosPropios() {
		return datosPropios;
	}
	
	/**
	 * Establece el datos propios.
	 *
	 * @param datosPropios el nuevo datos propios
	 */
	public void setDatosPropios(List<?> datosPropios) {
		this.datosPropios = datosPropios;
	}

	public String getEnlace() {
		return enlace;
	}

	public void setEnlace(String enlace) {
		this.enlace = enlace;
	}
}
