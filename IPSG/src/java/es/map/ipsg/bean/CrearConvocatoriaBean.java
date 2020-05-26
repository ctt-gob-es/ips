package es.map.ipsg.bean;

import java.util.Date;

/**
 * El Class CrearConvocatoriaBean.
 */
public class CrearConvocatoriaBean {

	/** el id centro gestor. */
	private int idCentroGestor;
	
	/** el id cuerpo escala. */
	private int idCuerpoEscala;
	
	/** el num plazas. */
	private int numPlazas;
	
	/** el num plazas disc. */
	private int numPlazasDisc;
	
	/** el id forma acceso. */
	private int idFormaAcceso;
	
	/** el importe. */
	private float importe;
	
	/** el dirigido A. */
	private String dirigidoA;
	
	/** el Tipo documentacion. */
	private char TipoDocumentacion;
	
	/** el id titulos. */
	private int[] idTitulos;	
	
	/** el fecha boe. */
	private String fechaBoe;
	
	/** el fecha inicio. */
	private String fechaInicio;
	
	/** el fecha termino. */
	private String fechaTermino;
	
	/** el fecha ini sub. */
	private String fechaIniSub;
	
	/** el fecha fin sub. */
	private String fechaFinSub;	
	
	/** el ejercicio. */
	private String ejercicio;
	
	/** el id convocatoria. */
	private long idConvocatoria;
	
	/** el id ministerio convocante. */
	private int idMinisterioConvocante;

	/** el fecha nacimiento minima. */
	private Date fechaNacimientoMinima;
	
	/** el fecha nacimiento maxima. */
	private Date fechaNacimientoMaxima;
	
	/** el id provincias. */
	private int[] idProvincias;
	
	/** el id motivos exencion. */
	private int[] idMotivosExencion;
	
	/** el id especialidades. */
	private int[] idEspecialidades;
	
	/** el id otros titulos. */
	private int[] idOtrosTitulos;
	
	/** el id discapacidad. */
	private int[] idDiscapacidad;
	
	/** el id datos propios. */
	private int[] idDatosPropios;
	
	/** el codigo forma acceso. */
	private String codigoFormaAcceso;
	
	/** el id modelo asociado. */
	private String idModeloAsociado;
	
	/** el presencial. */
	private Character presencial;
	
	/** enlace informativo. */
	private String enlace;
	
	/** el ocultarDatosPropios. */
	private Character ocultarDatosPropios;
	
	/**
	 * Obtiene el id modelo asociado.
	 *
	 * @return el id modelo asociado
	 */
	public String getIdModeloAsociado() {
		return idModeloAsociado;
	}
	
	/**
	 * Establece el id modelo asociado.
	 *
	 * @param idModeloAsociado el nuevo id modelo asociado
	 */
	public void setIdModeloAsociado(String idModeloAsociado) {
		this.idModeloAsociado = idModeloAsociado;
	}
	
	/**
	 * Obtiene el id centro gestor.
	 *
	 * @return el id centro gestor
	 */
	public int getIdCentroGestor() {
		return idCentroGestor;
	}
	
	/**
	 * Establece el id centro gestor.
	 *
	 * @param idCentroGestor el nuevo id centro gestor
	 */
	public void setIdCentroGestor(int idCentroGestor) {
		this.idCentroGestor = idCentroGestor;
	}
	
	/**
	 * Obtiene el id cuerpo escala.
	 *
	 * @return el id cuerpo escala
	 */
	public int getIdCuerpoEscala() {
		return idCuerpoEscala;
	}
	
	/**
	 * Establece el id cuerpo escala.
	 *
	 * @param idCuerpoEscala el nuevo id cuerpo escala
	 */
	public void setIdCuerpoEscala(int idCuerpoEscala) {
		this.idCuerpoEscala = idCuerpoEscala;
	}
	
	/**
	 * Obtiene el num plazas.
	 *
	 * @return el num plazas
	 */
	public int getNumPlazas() {
		return numPlazas;
	}
	
	/**
	 * Establece el num plazas.
	 *
	 * @param numPlazas el nuevo num plazas
	 */
	public void setNumPlazas(int numPlazas) {
		this.numPlazas = numPlazas;
	}
	
	/**
	 * Obtiene el num plazas disc.
	 *
	 * @return el num plazas disc
	 */
	public int getNumPlazasDisc() {
		return numPlazasDisc;
	}
	
	/**
	 * Establece el num plazas disc.
	 *
	 * @param numPlazasDisc el nuevo num plazas disc
	 */
	public void setNumPlazasDisc(int numPlazasDisc) {
		this.numPlazasDisc = numPlazasDisc;
	}
	
	/**
	 * Obtiene el id forma acceso.
	 *
	 * @return el id forma acceso
	 */
	public int getIdFormaAcceso() {
		return idFormaAcceso;
	}
	
	/**
	 * Establece el id forma acceso.
	 *
	 * @param idFormaAcceso el nuevo id forma acceso
	 */
	public void setIdFormaAcceso(int idFormaAcceso) {
		this.idFormaAcceso = idFormaAcceso;
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
	 * Obtiene el tipo documentacion.
	 *
	 * @return el tipo documentacion
	 */
	public char getTipoDocumentacion() {
		return TipoDocumentacion;
	}
	
	/**
	 * Establece el tipo documentacion.
	 *
	 * @param tipoDocumentacion el nuevo tipo documentacion
	 */
	public void setTipoDocumentacion(char tipoDocumentacion) {
		TipoDocumentacion = tipoDocumentacion;
	}
	
	/**
	 * Obtiene el id titulos.
	 *
	 * @return el id titulos
	 */
	public int[] getIdTitulos() {
		return idTitulos;
	}
	
	/**
	 * Establece el id titulos.
	 *
	 * @param idTitulos el nuevo id titulos
	 */
	public void setIdTitulos(int[] idTitulos) {
		this.idTitulos = idTitulos;
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
	 * Obtiene el fecha termino.
	 *
	 * @return el fecha termino
	 */
	public String getFechaTermino() {
		return fechaTermino;
	}
	
	/**
	 * Establece el fecha termino.
	 *
	 * @param fechaTermino el nuevo fecha termino
	 */
	public void setFechaTermino(String fechaTermino) {
		this.fechaTermino = fechaTermino;
	}
	
	/**
	 * Obtiene el id provincias.
	 *
	 * @return el id provincias
	 */
	public int[] getIdProvincias() {
		return idProvincias;
	}
	
	/**
	 * Establece el id provincias.
	 *
	 * @param idProvincias el nuevo id provincias
	 */
	public void setIdProvincias(int[] idProvincias) {
		this.idProvincias = idProvincias;
	}
	
	/**
	 * Obtiene el id motivos exencion.
	 *
	 * @return el id motivos exencion
	 */
	public int[] getIdMotivosExencion() {
		return idMotivosExencion;
	}
	
	/**
	 * Establece el id motivos exencion.
	 *
	 * @param idMotivosExencion el nuevo id motivos exencion
	 */
	public void setIdMotivosExencion(int[] idMotivosExencion) {
		this.idMotivosExencion = idMotivosExencion;
	}
	
	/**
	 * Obtiene el id especialidades.
	 *
	 * @return el id especialidades
	 */
	public int[] getIdEspecialidades() {
		return idEspecialidades;
	}
	
	/**
	 * Establece el id especialidades.
	 *
	 * @param idEspecialidades el nuevo id especialidades
	 */
	public void setIdEspecialidades(int[] idEspecialidades) {
		this.idEspecialidades = idEspecialidades;
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
	 * Obtiene el fecha nacimiento minima.
	 *
	 * @return el fecha nacimiento minima
	 */
	public Date getFechaNacimientoMinima() {
		return fechaNacimientoMinima;
	}
	
	/**
	 * Establece el fecha nacimiento minima.
	 *
	 * @param fechaNacimientoMinima el nuevo fecha nacimiento minima
	 */
	public void setFechaNacimientoMinima(Date fechaNacimientoMinima) {
		this.fechaNacimientoMinima = fechaNacimientoMinima;
	}
	
	/**
	 * Obtiene el fecha nacimiento maxima.
	 *
	 * @return el fecha nacimiento maxima
	 */
	public Date getFechaNacimientoMaxima() {
		return fechaNacimientoMaxima;
	}
	
	/**
	 * Establece el fecha nacimiento maxima.
	 *
	 * @param fechaNacimientoMaxima el nuevo fecha nacimiento maxima
	 */
	public void setFechaNacimientoMaxima(Date fechaNacimientoMaxima) {
		this.fechaNacimientoMaxima = fechaNacimientoMaxima;
	}
	
	/**
	 * Obtiene el codigo forma acceso.
	 *
	 * @return el codigo forma acceso
	 */
	public String getCodigoFormaAcceso() {
		return codigoFormaAcceso;
	}
	
	/**
	 * Establece el codigo forma acceso.
	 *
	 * @param codigoFormaAcceso el nuevo codigo forma acceso
	 */
	public void setCodigoFormaAcceso(String codigoFormaAcceso) {
		this.codigoFormaAcceso = codigoFormaAcceso;
	}
	
	/**
	 * Obtiene el id ministerio convocante.
	 *
	 * @return el id ministerio convocante
	 */
	public int getIdMinisterioConvocante() {
		return idMinisterioConvocante;
	}
	
	/**
	 * Establece el id ministerio convocante.
	 *
	 * @param idMinisterioConvocante el nuevo id ministerio convocante
	 */
	public void setIdMinisterioConvocante(int idMinisterioConvocante) {
		this.idMinisterioConvocante = idMinisterioConvocante;
	}
	
	/**
	 * Obtiene el presencial.
	 *
	 * @return el presencial
	 */
	public Character getPresencial() {
		return presencial;
	}
	
	/**
	 * Establece el presencial.
	 *
	 * @param presencial el nuevo presencial
	 */
	public void setPresencial(Character presencial) {
		this.presencial = presencial;
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
	
	/**
	 * Obtiene el id otros titulos.
	 *
	 * @return el id otros titulos
	 */
	public int[] getIdOtrosTitulos() {
		return idOtrosTitulos;
	}
	
	/**
	 * Establece el id otros titulos.
	 *
	 * @param idOtrosTitulos el nuevo id otros titulos
	 */
	public void setIdOtrosTitulos(int[] idOtrosTitulos) {
		this.idOtrosTitulos = idOtrosTitulos;
	}
	
	/**
	 * Obtiene el id discapacidad.
	 *
	 * @return el id discapacidad
	 */
	public int[] getIdDiscapacidad() {
		return idDiscapacidad;
	}
	
	/**
	 * Establece el id discapacidad.
	 *
	 * @param idDiscapacidad el nuevo id discapacidad
	 */
	public void setIdDiscapacidad(int[] idDiscapacidad) {
		this.idDiscapacidad = idDiscapacidad;
	}
	
	/**
	 * Obtiene el id datos propios.
	 *
	 * @return el id datos propios
	 */
	public int[] getIdDatosPropios() {
		return idDatosPropios;
	}
	
	/**
	 * Establece el id datos propios.
	 *
	 * @param idDatosPropios el nuevo id datos propios
	 */
	public void setIdDatosPropios(int[] idDatosPropios) {
		this.idDatosPropios = idDatosPropios;
	}
	
	public String getEnlace() {
		return enlace;
	}

	public void setEnlace(String enlace) {
		this.enlace = enlace;
	}

	/**
	 * Obtiene el ocultar datos propios.
	 *
	 * @return el ocultar datos propios
	 */
	public Character getOcultarDatosPropios() {
		return ocultarDatosPropios;
	}

	/**
	 * Establece el ocultar datos propios.
	 *
	 * @param ocultarDatosPropios el nuevo ocultar datos propios
	 */
	public void setOcultarDatosPropios(Character ocultarDatosPropios) {
		this.ocultarDatosPropios = ocultarDatosPropios;
	}
}
