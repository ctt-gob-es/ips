package es.map.ipsg.bean;


/**
 * ConvocatoriasViewBean.
 *
 * @author amartinl
 */
public class ConvocatoriasViewBean {
	
	/** el id convocatoria. */
	private Long idConvocatoria;
    
    /** el id ministerio. */
    private Integer idMinisterio;
    
    /** el des ministerio. */
    private String desMinisterio;
    
    /** el id centro gestor. */
    private Integer idCentroGestor;
    
    /** el des centro gestor. */
    private String desCentroGestor;
    
    /** el id forma acceso. */
    private Integer idFormaAcceso;
    
    /** el des forma acceso. */
    private String desFormaAcceso;
    
    /** el id cuerpo escala. */
    private Integer idCuerpoEscala;
    
    /** el des cuerpo escala. */
    private String desCuerpoEscala;
    
    /** el id grupo. */
    private Integer idGrupo;
    
    /** el des grupo. */
    private String desGrupo;
    
    /** el id estado convocatoria. */
    private Integer idEstadoConvocatoria;
    
    /** el des estado convocatoria. */
    private String desEstadoConvocatoria;
    
    /** el fecha boe. */
    private String fechaBoe;
    
    /** el plazas total. */
    private Integer plazasTotal;
    
    /** el plazas discapacitados. */
    private Integer plazasDiscapacitados;
    
    /** el ejercicio. */
    private String ejercicio;
    
    /** el num sol presencial. */
    //Campos extras calculativos
    private String numSolPresencial;
    
    /** el num sol telematica. */
    private String numSolTelematica;
    
    /** el num sol inc pago resuelta. */
    private String numSolIncPagoResuelta;
    
    /** el num sol inc pago sin resolver. */
    private String numSolIncPagoSinResolver;
	
	/** el num sol inc registro resuelta. */
	private String numSolIncRegistroResuelta;
	
	/** el num sol inc registro sin resolver. */
	private String numSolIncRegistroSinResolver;
	
	/** el num inscripcion sin pago. */
	private String numInscripcionSinPago;
    
    /** el num pagos sin registro. */
    private String numPagosSinRegistro;
    
    /** el num descargas manuales. */
    private String numDescargasManuales;
    
    /** el numero descargas modelo blanco. */
    private Integer numeroDescargasModeloBlanco;
	
	/** el num pagos sin intento registro. */
	private String numPagosSinIntentoRegistro;	
	
	/** el num sol sin intento pago. */
	private String numSolSinIntentoPago;
    
    /** el num total sol presencial. */
    //Campos extras calculativos Total por Ministerio
    private String numTotalSolPresencial;
    
    /** el num total sol telematica. */
    private String numTotalSolTelematica;
    
    /** el num total sol inc pago resuelta. */
    private String numTotalSolIncPagoResuelta;
    
    /** el num total sol inc pago sin resolver. */
    private String numTotalSolIncPagoSinResolver;
	
	/** el num total sol inc registro resuelta. */
	private String numTotalSolIncRegistroResuelta;
	
	/** el num total sol inc registro sin resolver. */
	private String numTotalSolIncRegistroSinResolver;
	
	/** el num total inscripcion sin pago. */
	private String numTotalInscripcionSinPago;
    
    /** el num total pagos sin registro. */
    private String numTotalPagosSinRegistro;
	
	/** el num total inscripcion sin intento pago. */
	private String numTotalInscripcionSinIntentoPago;
	
	/** el num total pagos sin intento registro. */
	private String numTotalPagosSinIntentoRegistro;
    
    /** el num total descargas manuales. */
    private String numTotalDescargasManuales;
    
    /** el num total plazas totales. */
    private Integer numTotalPlazasTotales;
    
    /** el num total plazas discapacitados. */
    private Integer numTotalPlazasDiscapacitados;
    
    /** el sustituido A otro. */
    //Campos para la comprobacion de mensajes finales
    private String sustituidoAOtro = "";
    
    /** el sustituido por otro. */
    private String sustituidoPorOtro = "";
    
    /** el des ministerio sust. */
    private String desMinisterioSust = "";
    
    /** el fecha sustitucion. */
    private String fechaSustitucion = "";
    
    /** el des ministerio sustitutivo. */
    private String desMinisterioSustitutivo = "";
    
    /** el fecha sustitucion 2. */
    private String fechaSustitucion2 = "";
    
    
    
	/**
	 * Obtiene el des ministerio sustitutivo.
	 *
	 * @return el des ministerio sustitutivo
	 */
	public String getDesMinisterioSustitutivo() {
		return desMinisterioSustitutivo;
	}
	
	/**
	 * Establece el des ministerio sustitutivo.
	 *
	 * @param desMinisterioSustitutivo el nuevo des ministerio sustitutivo
	 */
	public void setDesMinisterioSustitutivo(String desMinisterioSustitutivo) {
		this.desMinisterioSustitutivo = desMinisterioSustitutivo;
	}
	
	/**
	 * Obtiene el fecha sustitucion 2.
	 *
	 * @return el fecha sustitucion 2
	 */
	public String getFechaSustitucion2() {
		return fechaSustitucion2;
	}
	
	/**
	 * Establece el fecha sustitucion 2.
	 *
	 * @param fechaSustitucion2 el nuevo fecha sustitucion 2
	 */
	public void setFechaSustitucion2(String fechaSustitucion2) {
		this.fechaSustitucion2 = fechaSustitucion2;
	}
	
	/**
	 * Obtiene el des ministerio sust.
	 *
	 * @return el des ministerio sust
	 */
	public String getDesMinisterioSust() {
		return desMinisterioSust;
	}
	
	/**
	 * Establece el des ministerio sust.
	 *
	 * @param desMinisterioSust el nuevo des ministerio sust
	 */
	public void setDesMinisterioSust(String desMinisterioSust) {
		this.desMinisterioSust = desMinisterioSust;
	}
	
	/**
	 * Obtiene el fecha sustitucion.
	 *
	 * @return el fecha sustitucion
	 */
	public String getFechaSustitucion() {
		return fechaSustitucion;
	}
	
	/**
	 * Establece el fecha sustitucion.
	 *
	 * @param fechaSustitucion el nuevo fecha sustitucion
	 */
	public void setFechaSustitucion(String fechaSustitucion) {
		this.fechaSustitucion = fechaSustitucion;
	}
	
	/**
	 * Obtiene el id convocatoria.
	 *
	 * @return idConvocatoria Long
	 */ 
	public Long getIdConvocatoria() {
		return idConvocatoria;
	}
	
	/**
	 * Establece el id convocatoria.
	 *
	 * @param idConvocatoria Long
	 */
	public void setIdConvocatoria(Long idConvocatoria) {
		this.idConvocatoria = idConvocatoria;
	}
	
	/**
	 * Obtiene el id ministerio.
	 *
	 * @return idMinisterio Integer
	 */
	public Integer getIdMinisterio() {
		return idMinisterio;
	}
	
	/**
	 * Establece el id ministerio.
	 *
	 * @param idMinisterio el nuevo id ministerio
	 */
	public void setIdMinisterio(Integer idMinisterio) {
		this.idMinisterio = idMinisterio;
	}
	
	/**
	 * Obtiene el des ministerio.
	 *
	 * @return desMinisterio String
	 */
	public String getDesMinisterio() {
		return desMinisterio;
	}
	
	/**
	 * Establece el des ministerio.
	 *
	 * @param desMinisterio String
	 */
	public void setDesMinisterio(String desMinisterio) {
		this.desMinisterio = desMinisterio;
	}
	
	/**
	 * Obtiene el id centro gestor.
	 *
	 * @return idCentroGestor Integer
	 */ 
	public Integer getIdCentroGestor() {
		return idCentroGestor;
	}
	
	/**
	 * Establece el id centro gestor.
	 *
	 * @param idCentroGestor Integer
	 */
	public void setIdCentroGestor(Integer idCentroGestor) {
		this.idCentroGestor = idCentroGestor;
	}
	
	/**
	 * Obtiene el des centro gestor.
	 *
	 * @return desCentroGestor String
	 */
	public String getDesCentroGestor() {
		return desCentroGestor;
	}
	
	/**
	 * Establece el des centro gestor.
	 *
	 * @param desCentroGestor String
	 */
	public void setDesCentroGestor(String desCentroGestor) {
		this.desCentroGestor = desCentroGestor;
	}
	
	/**
	 * Obtiene el id forma acceso.
	 *
	 * @return idFormaAcceso Integer
	 */
	public Integer getIdFormaAcceso() {
		return idFormaAcceso;
	}
	
	/**
	 * Establece el id forma acceso.
	 *
	 * @param idFormaAcceso Integer
	 */
	public void setIdFormaAcceso(Integer idFormaAcceso) {
		this.idFormaAcceso = idFormaAcceso;
	}
	
	/**
	 * Obtiene el des forma acceso.
	 *
	 * @return desFormaAcceso String
	 */
	public String getDesFormaAcceso() {
		return desFormaAcceso;
	}
	
	/**
	 * Establece el des forma acceso.
	 *
	 * @param desFormaAcceso String
	 */
	public void setDesFormaAcceso(String desFormaAcceso) {
		this.desFormaAcceso = desFormaAcceso;
	}
	
	/**
	 * Obtiene el id cuerpo escala.
	 *
	 * @return  idCuerpoEscala Integer
	 */
	public Integer getIdCuerpoEscala() {
		return idCuerpoEscala;
	}
	
	/**
	 * Establece el id cuerpo escala.
	 *
	 * @param idCuerpoEscala Integer
	 */
	public void setIdCuerpoEscala(Integer idCuerpoEscala) {
		this.idCuerpoEscala = idCuerpoEscala;
	}
	
	/**
	 * Obtiene el des cuerpo escala.
	 *
	 * @return desCuerpoEscala String
	 */
	public String getDesCuerpoEscala() {
		return desCuerpoEscala;
	}
	
	/**
	 * Establece el des cuerpo escala.
	 *
	 * @param desCuerpoEscala String
	 */
	public void setDesCuerpoEscala(String desCuerpoEscala) {
		this.desCuerpoEscala = desCuerpoEscala;
	}
	
	/**
	 * Obtiene el id grupo.
	 *
	 * @return  idGrupo Integer
	 */
	public Integer getIdGrupo() {
		return idGrupo;
	}
	
	/**
	 * Establece el id grupo.
	 *
	 * @param idGrupo Integer
	 */
	public void setIdGrupo(Integer idGrupo) {
		this.idGrupo = idGrupo;
	}
	
	/**
	 * Obtiene el des grupo.
	 *
	 * @return  desGrupo String
	 */
	public String getDesGrupo() {
		return desGrupo;
	}
	
	/**
	 * Establece el des grupo.
	 *
	 * @param desGrupo String
	 */
	public void setDesGrupo(String desGrupo) {
		this.desGrupo = desGrupo;
	}
	
	/**
	 * Obtiene el id estado convocatoria.
	 *
	 * @return idEstadoConvocatoria Integer
	 */
	public Integer getIdEstadoConvocatoria() {
		return idEstadoConvocatoria;
	}
	
	/**
	 * Establece el id estado convocatoria.
	 *
	 * @param idEstadoConvocatoria Integer
	 */
	public void setIdEstadoConvocatoria(Integer idEstadoConvocatoria) {
		this.idEstadoConvocatoria = idEstadoConvocatoria;
	}
	
	/**
	 * Obtiene el des estado convocatoria.
	 *
	 * @return desEstadoConvocatoria String
	 */
	public String getDesEstadoConvocatoria() {
		return desEstadoConvocatoria;
	}
	
	/**
	 * Establece el des estado convocatoria.
	 *
	 * @param desEstadoConvocatoria String
	 */
	public void setDesEstadoConvocatoria(String desEstadoConvocatoria) {
		this.desEstadoConvocatoria = desEstadoConvocatoria;
	}
	
	/**
	 * Obtiene el fecha boe.
	 *
	 * @return fechaBoe String
	 */
	public String getFechaBoe() {
		return fechaBoe;
	}
	
	/**
	 * Establece el fecha boe.
	 *
	 * @param fechaBoe String
	 */
	public void setFechaBoe(String fechaBoe) {
		this.fechaBoe = fechaBoe;
	}
	
	/**
	 * Obtiene el plazas total.
	 *
	 * @return plazasTotal Integer
	 */
	public Integer getPlazasTotal() {
		return plazasTotal;
	}
	
	/**
	 * Establece el plazas total.
	 *
	 * @param plazasTotal Integer
	 */
	public void setPlazasTotal(Integer plazasTotal) {
		this.plazasTotal = plazasTotal;
	}
	
	/**
	 * Obtiene el plazas discapacitados.
	 *
	 * @return plazasDiscapacitados Integer
	 */
	public Integer getPlazasDiscapacitados() {
		return plazasDiscapacitados;
	}
	
	/**
	 * Establece el plazas discapacitados.
	 *
	 * @param plazasDiscapacitados Integer
	 */
	public void setPlazasDiscapacitados(Integer plazasDiscapacitados) {
		this.plazasDiscapacitados = plazasDiscapacitados;
	}
	
	/**
	 * Obtiene el ejercicio.
	 *
	 * @return ejercicio String
	 */
	public String getEjercicio() {
		return ejercicio;
	}
	
	/**
	 * Establece el ejercicio.
	 *
	 * @param ejercicio String
	 */
	public void setEjercicio(String ejercicio) {
		this.ejercicio = ejercicio;
	}
	
	/**
	 *  Numero de Solicitudes Presencial.
	 *
	 * @return numSolPresencial String
	 */ 
	public String getNumSolPresencial() {
		return numSolPresencial;
	}
	
	/**
	 *  
	 * Numero de Solicitudes Presencial.
	 *
	 * @param numSolPresencial String
	 */
	public void setNumSolPresencial(String numSolPresencial) {
		this.numSolPresencial = numSolPresencial;
	}
	
	/**
	 * Obtiene el num sol telematica.
	 *
	 * @return numSolTelematica String
	 */
	public String getNumSolTelematica() {
		return numSolTelematica;
	}
	
	/**
	 * Establece el num sol telematica.
	 *
	 * @param numSolTelematica String
	 */
	public void setNumSolTelematica(String numSolTelematica) {
		this.numSolTelematica = numSolTelematica;
	}
	
	/**
	 * Obtiene el num sol inc pago resuelta.
	 *
	 * @return numSolIncPagoResuelta String
	 */
	public String getNumSolIncPagoResuelta() {
		return numSolIncPagoResuelta;
	}
	
	/**
	 * Establece el num sol inc pago resuelta.
	 *
	 * @param numSolIncPagoResuelta String
	 */
	public void setNumSolIncPagoResuelta(String numSolIncPagoResuelta) {
		this.numSolIncPagoResuelta = numSolIncPagoResuelta;
	}
	
	/**
	 * Obtiene el num sol inc pago sin resolver.
	 *
	 * @return  numSolIncPagoSinResolver String
	 */
	public String getNumSolIncPagoSinResolver() {
		return numSolIncPagoSinResolver;
	}
	
	/**
	 * Establece el num sol inc pago sin resolver.
	 *
	 * @param numSolIncPagoSinResolver String
	 */
	public void setNumSolIncPagoSinResolver(String numSolIncPagoSinResolver) {
		this.numSolIncPagoSinResolver = numSolIncPagoSinResolver;
	}
	
	/**
	 * Obtiene el num sol inc registro resuelta.
	 *
	 * @return numSolIncRegistroResuelta String
	 */
	public String getNumSolIncRegistroResuelta() {
		return numSolIncRegistroResuelta;
	}
	
	/**
	 * Establece el num sol inc registro resuelta.
	 *
	 * @param numSolIncRegistroResuelta String
	 */
	public void setNumSolIncRegistroResuelta(String numSolIncRegistroResuelta) {
		this.numSolIncRegistroResuelta = numSolIncRegistroResuelta;
	}
	
	/**
	 * Obtiene el num sol inc registro sin resolver.
	 *
	 * @return numSolIncRegistroSinResolver  String
	 */
	public String getNumSolIncRegistroSinResolver() {
		return numSolIncRegistroSinResolver;
	}
	
	/**
	 * Establece el num sol inc registro sin resolver.
	 *
	 * @param numSolIncRegistroSinResolver  String
	 */
	public void setNumSolIncRegistroSinResolver(String numSolIncRegistroSinResolver) {
		this.numSolIncRegistroSinResolver = numSolIncRegistroSinResolver;
	}

	/**
	 * Obtiene el num descargas manuales.
	 *
	 * @return numDescargasManuales String
	 */
	public String getNumDescargasManuales() {
		return numDescargasManuales;
	}
	
	/**
	 * Establece el num descargas manuales.
	 *
	 * @param numDescargasManuales String
	 */
	public void setNumDescargasManuales(String numDescargasManuales) {
		this.numDescargasManuales = numDescargasManuales;
	}
	
	/**
	 * Obtiene el num total sol presencial.
	 *
	 * @return numTotalSolPresencial String
	 */
	public String getNumTotalSolPresencial() {
		return numTotalSolPresencial;
	}
	
	/**
	 * Establece el num total sol presencial.
	 *
	 * @param numTotalSolPresencial String
	 */
	public void setNumTotalSolPresencial(String numTotalSolPresencial) {
		this.numTotalSolPresencial = numTotalSolPresencial;
	}
	
	/**
	 * Obtiene el num total sol telematica.
	 *
	 * @return numTotalSolTelematica String
	 */
	public String getNumTotalSolTelematica() {
		return numTotalSolTelematica;
	}
	
	/**
	 * Establece el num total sol telematica.
	 *
	 * @param numTotalSolTelematica String
	 */
	public void setNumTotalSolTelematica(String numTotalSolTelematica) {
		this.numTotalSolTelematica = numTotalSolTelematica;
	}
	
	/**
	 * Obtiene el num total sol inc pago resuelta.
	 *
	 * @return numTotalSolIncPagoResuelta String
	 */
	public String getNumTotalSolIncPagoResuelta() {
		return numTotalSolIncPagoResuelta;
	}
	
	/**
	 * Establece el num total sol inc pago resuelta.
	 *
	 * @param numTotalSolIncPagoResuelta String
	 */
	public void setNumTotalSolIncPagoResuelta(String numTotalSolIncPagoResuelta) {
		this.numTotalSolIncPagoResuelta = numTotalSolIncPagoResuelta;
	}
	
	/**
	 * Obtiene el num total sol inc pago sin resolver.
	 *
	 * @return numTotalSolIncPagoSinResolver String
	 */
	public String getNumTotalSolIncPagoSinResolver() {
		return numTotalSolIncPagoSinResolver;
	}
	
	/**
	 * Establece el num total sol inc pago sin resolver.
	 *
	 * @param numTotalSolIncPagoSinResolver String
	 */
	public void setNumTotalSolIncPagoSinResolver(
			String numTotalSolIncPagoSinResolver) {
		this.numTotalSolIncPagoSinResolver = numTotalSolIncPagoSinResolver;
	}
	
	/**
	 * Obtiene el num total sol inc registro resuelta.
	 *
	 * @return numTotalSolIncRegistroResuelta String
	 */
	public String getNumTotalSolIncRegistroResuelta() {
		return numTotalSolIncRegistroResuelta;
	}
	
	/**
	 * Establece el num total sol inc registro resuelta.
	 *
	 * @param numTotalSolIncRegistroResuelta String
	 */
	public void setNumTotalSolIncRegistroResuelta(
			String numTotalSolIncRegistroResuelta) {
		this.numTotalSolIncRegistroResuelta = numTotalSolIncRegistroResuelta;
	}
	
	/**
	 * Obtiene el num total sol inc registro sin resolver.
	 *
	 * @return numTotalSolIncRegistroSinResolver String
	 */
	public String getNumTotalSolIncRegistroSinResolver() {
		return numTotalSolIncRegistroSinResolver;
	}
	
	/**
	 * Establece el num total sol inc registro sin resolver.
	 *
	 * @param numTotalSolIncRegistroSinResolver String
	 */
	public void setNumTotalSolIncRegistroSinResolver(
			String numTotalSolIncRegistroSinResolver) {
		this.numTotalSolIncRegistroSinResolver = numTotalSolIncRegistroSinResolver;
	}
	
	/**
	 * Obtiene el num total inscripcion sin pago.
	 *
	 * @return numTotalInscripcionSinPago String
	 */
	public String getNumTotalInscripcionSinPago() {
		return numTotalInscripcionSinPago;
	}
	
	/**
	 * Establece el num total inscripcion sin pago.
	 *
	 * @param numTotalInscripcionSinPago String
	 */
	public void setNumTotalInscripcionSinPago(String numTotalInscripcionSinPago) {
		this.numTotalInscripcionSinPago = numTotalInscripcionSinPago;
	}
	
	/**
	 * Obtiene el num total pagos sin registro.
	 *
	 * @return numTotalPagosSinRegistro String
	 */
	public String getNumTotalPagosSinRegistro() {
		return numTotalPagosSinRegistro;
	}
	
	/**
	 * Establece el num total pagos sin registro.
	 *
	 * @param numTotalPagosSinRegistro String
	 */
	public void setNumTotalPagosSinRegistro(String numTotalPagosSinRegistro) {
		this.numTotalPagosSinRegistro = numTotalPagosSinRegistro;
	}
	
	/**
	 * Obtiene el num total descargas manuales.
	 *
	 * @return numTotalDescargasManuales String
	 */
	public String getNumTotalDescargasManuales() {
		return numTotalDescargasManuales;
	}
	
	/**
	 * Establece el num total descargas manuales.
	 *
	 * @param numTotalDescargasManuales String
	 */
	public void setNumTotalDescargasManuales(String numTotalDescargasManuales) {
		this.numTotalDescargasManuales = numTotalDescargasManuales;
	}
	
	/**
	 * Obtiene el sustituido A otro.
	 *
	 * @return sustituidoAOtro String
	 */
	public String getSustituidoAOtro() {
		return sustituidoAOtro;
	}
	
	/**
	 * Establece el sustituido A otro.
	 *
	 * @param sustituidoAOtro String
	 */
	public void setSustituidoAOtro(String sustituidoAOtro) {
		this.sustituidoAOtro = sustituidoAOtro;
	}
	
	/**
	 * Obtiene el sustituido por otro.
	 *
	 * @return sustituidoPorOtro String
	 */
	public String getSustituidoPorOtro() {
		return sustituidoPorOtro;
	}
	
	/**
	 * Establece el sustituido por otro.
	 *
	 * @param sustituidoPorOtro String
	 */
	public void setSustituidoPorOtro(String sustituidoPorOtro) {
		this.sustituidoPorOtro = sustituidoPorOtro;
	}
	
	/**
	 * Obtiene el num total plazas totales.
	 *
	 * @return el num total plazas totales
	 */
	public Integer getNumTotalPlazasTotales() {
		return numTotalPlazasTotales;
	}
	
	/**
	 * Establece el num total plazas totales.
	 *
	 * @param numTotalPlazasTotales el nuevo num total plazas totales
	 */
	public void setNumTotalPlazasTotales(Integer numTotalPlazasTotales) {
		this.numTotalPlazasTotales = numTotalPlazasTotales;
	}
	
	/**
	 * Obtiene el num total plazas discapacitados.
	 *
	 * @return el num total plazas discapacitados
	 */
	public Integer getNumTotalPlazasDiscapacitados() {
		return numTotalPlazasDiscapacitados;
	}
	
	/**
	 * Establece el num total plazas discapacitados.
	 *
	 * @param numTotalPlazasDiscapacitados el nuevo num total plazas discapacitados
	 */
	public void setNumTotalPlazasDiscapacitados(Integer numTotalPlazasDiscapacitados) {
		this.numTotalPlazasDiscapacitados = numTotalPlazasDiscapacitados;
	}
	
	/**
	 * Obtiene el numero descargas modelo blanco.
	 *
	 * @return el numero descargas modelo blanco
	 */
	public Integer getNumeroDescargasModeloBlanco() {
		return numeroDescargasModeloBlanco;
	}
	
	/**
	 * Establece el numero descargas modelo blanco.
	 *
	 * @param numeroDescargasModeloBlanco el nuevo numero descargas modelo blanco
	 */
	public void setNumeroDescargasModeloBlanco(Integer numeroDescargasModeloBlanco) {
		this.numeroDescargasModeloBlanco = numeroDescargasModeloBlanco;
	}
	
	/**
	 * Obtiene el num pagos sin intento registro.
	 *
	 * @return el num pagos sin intento registro
	 */
	public String getNumPagosSinIntentoRegistro() {
		return numPagosSinIntentoRegistro;
	}
	
	/**
	 * Establece el num pagos sin intento registro.
	 *
	 * @param numPagosSinIntentoRegistro el nuevo num pagos sin intento registro
	 */
	public void setNumPagosSinIntentoRegistro(String numPagosSinIntentoRegistro) {
		this.numPagosSinIntentoRegistro = numPagosSinIntentoRegistro;
	}
	
	/**
	 * Obtiene el num sol sin intento pago.
	 *
	 * @return el num sol sin intento pago
	 */
	public String getNumSolSinIntentoPago() {
		return numSolSinIntentoPago;
	}
	
	/**
	 * Establece el num sol sin intento pago.
	 *
	 * @param numSolSinIntentoPago el nuevo num sol sin intento pago
	 */
	public void setNumSolSinIntentoPago(String numSolSinIntentoPago) {
		this.numSolSinIntentoPago = numSolSinIntentoPago;
	}
	
	/**
	 * Obtiene el num total inscripcion sin intento pago.
	 *
	 * @return el num total inscripcion sin intento pago
	 */
	public String getNumTotalInscripcionSinIntentoPago() {
		return numTotalInscripcionSinIntentoPago;
	}
	
	/**
	 * Establece el num total inscripcion sin intento pago.
	 *
	 * @param numTotalInscripcionSinIntentoPago el nuevo num total inscripcion sin intento pago
	 */
	public void setNumTotalInscripcionSinIntentoPago(
			String numTotalInscripcionSinIntentoPago) {
		this.numTotalInscripcionSinIntentoPago = numTotalInscripcionSinIntentoPago;
	}
	
	/**
	 * Obtiene el num total pagos sin intento registro.
	 *
	 * @return el num total pagos sin intento registro
	 */
	public String getNumTotalPagosSinIntentoRegistro() {
		return numTotalPagosSinIntentoRegistro;
	}
	
	/**
	 * Establece el num total pagos sin intento registro.
	 *
	 * @param numTotalPagosSinIntentoRegistro el nuevo num total pagos sin intento registro
	 */
	public void setNumTotalPagosSinIntentoRegistro(
			String numTotalPagosSinIntentoRegistro) {
		this.numTotalPagosSinIntentoRegistro = numTotalPagosSinIntentoRegistro;
	}
	
	/**
	 * Obtiene el num inscripcion sin pago.
	 *
	 * @return el num inscripcion sin pago
	 */
	public String getNumInscripcionSinPago() {
		return numInscripcionSinPago;
	}
	
	/**
	 * Establece el num inscripcion sin pago.
	 *
	 * @param numInscripcionSinPago el nuevo num inscripcion sin pago
	 */
	public void setNumInscripcionSinPago(String numInscripcionSinPago) {
		this.numInscripcionSinPago = numInscripcionSinPago;
	}
	
	/**
	 * Obtiene el num pagos sin registro.
	 *
	 * @return el num pagos sin registro
	 */
	public String getNumPagosSinRegistro() {
		return numPagosSinRegistro;
	}
	
	/**
	 * Establece el num pagos sin registro.
	 *
	 * @param numPagosSinRegistro el nuevo num pagos sin registro
	 */
	public void setNumPagosSinRegistro(String numPagosSinRegistro) {
		this.numPagosSinRegistro = numPagosSinRegistro;
	}


    
    
}
