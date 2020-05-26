package es.map.ipsg.bean;

import java.util.ArrayList;



/**
 * El Class ModificarConvocatoriaBean.
 */
public class ModificarConvocatoriaBean {

	/** el id convocatoria. */
	private long idConvocatoria;
	
	/** el id centro gestor. */
	private int idCentroGestor;
	
	/** el ds centro gestor. */
	private String dsCentroGestor;
	
	/** el id cuerpo escala. */
	private int idCuerpoEscala;
	
	/** el ds cuerpo escala. */
	private String dsCuerpoEscala;
	
	/** el id ministerio convocante. */
	private String idMinisterioConvocante;
	
	/** el num plazas. */
	private int numPlazas;
	
	/** el num plazas disc. */
	private int numPlazasDisc;
	
	/** el Forma acceso. */
	private FormaAccesoBean FormaAcceso;
	
	/** el importe. */
	private float importe;
	
	/** el dirigido A. */
	private String dirigidoA;
	
	/** el Tipo documentacion. */
	private char TipoDocumentacion;
	
	/** el Titulos. */
	private ArrayList<TituloOficialBean> Titulos;
	
	/** el ejercicio. */
	private String ejercicio;
	
	/** el fecha boe. */
	private String fechaBoe;
	
	/** el fecha inicio. */
	private String fechaInicio;
	
	/** el fecha termino. */
	private String fechaTermino;
	
	/** el fecha inicio sub. */
	private String fechaInicioSub;
	
	/** el fecha fin sub. */
	private String fechaFinSub;	
	
	/** el estado. */
	private String estado;
	
	/** el codigo forma acceso. */
	private String codigoFormaAcceso;

	/** el provincias. */
	private ArrayList<ProvinciaExamenBean> provincias;
	
	/** el motivos exencion. */
	private ArrayList<MotivoReduccionTasaBean> motivosExencion;
	
	/** el especialidades. */
	private ArrayList<EspecialidadBean> especialidades;
	
	/** el otros titulos. */
	private ArrayList<OtrosTitulosBean> otrosTitulos;
	
	/** el discapacidades. */
	private ArrayList<DiscapacidadBean> discapacidades;
	
	/** el datos propios config bean. */
	private ArrayList<DatosPropiosConfigBean> datosPropiosConfigBean;
	
	/** el presencial. */
	private boolean presencial;
	
	/** el enlace informativo. */
	private String enlace;
	
	/** el ocultarDatosPropios. */
	private Character ocultarDatosPropios;
	
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
	 * Obtiene el forma acceso.
	 *
	 * @return el forma acceso
	 */
	public FormaAccesoBean getFormaAcceso() {
		return FormaAcceso;
	}
	
	/**
	 * Establece el forma acceso.
	 *
	 * @param formaAcceso el nuevo forma acceso
	 */
	public void setFormaAcceso(FormaAccesoBean formaAcceso) {
		FormaAcceso = formaAcceso;
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
	 * Obtiene el titulos.
	 *
	 * @return el titulos
	 */
	public ArrayList<TituloOficialBean> getTitulos() {
		return Titulos;
	}
	
	/**
	 * Establece el titulos.
	 *
	 * @param titulos el nuevo titulos
	 */
	public void setTitulos(ArrayList<TituloOficialBean> titulos) {
		Titulos = titulos;
	}
	
	/**
	 * Obtiene el provincias.
	 *
	 * @return el provincias
	 */
	public ArrayList<ProvinciaExamenBean> getProvincias() {
		return provincias;
	}
	
	/**
	 * Establece el provincias.
	 *
	 * @param provincias el nuevo provincias
	 */
	public void setProvincias(ArrayList<ProvinciaExamenBean> provincias) {
		this.provincias = provincias;
	}
	
	/**
	 * Obtiene el motivos exencion.
	 *
	 * @return el motivos exencion
	 */
	public ArrayList<MotivoReduccionTasaBean> getMotivosExencion() {
		return motivosExencion;
	}
	
	/**
	 * Establece el motivos exencion.
	 *
	 * @param motivosExencion el nuevo motivos exencion
	 */
	public void setMotivosExencion(
			ArrayList<MotivoReduccionTasaBean> motivosExencion) {
		this.motivosExencion = motivosExencion;
	}
	
	/**
	 * Obtiene el especialidades.
	 *
	 * @return el especialidades
	 */
	public ArrayList<EspecialidadBean> getEspecialidades() {
		return especialidades;
	}
	
	/**
	 * Establece el especialidades.
	 *
	 * @param especialidades el nuevo especialidades
	 */
	public void setEspecialidades(ArrayList<EspecialidadBean> especialidades) {
		this.especialidades = especialidades;
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
	public String getIdMinisterioConvocante() {
		return idMinisterioConvocante;
	}
	
	/**
	 * Establece el id ministerio convocante.
	 *
	 * @param idMinisterioConvocante el nuevo id ministerio convocante
	 */
	public void setIdMinisterioConvocante(String idMinisterioConvocante) {
		this.idMinisterioConvocante = idMinisterioConvocante;
	}
	
	/**
	 * Obtiene el presencial.
	 *
	 * @return el presencial
	 */
	public boolean getPresencial() {
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
	 * Obtiene el otros titulos.
	 *
	 * @return el otros titulos
	 */
	public ArrayList<OtrosTitulosBean> getOtrosTitulos() {
		return otrosTitulos;
	}
	
	/**
	 * Establece el otros titulos.
	 *
	 * @param otrosTitulos el nuevo otros titulos
	 */
	public void setOtrosTitulos(ArrayList<OtrosTitulosBean> otrosTitulos) {
		this.otrosTitulos = otrosTitulos;
	}
	
	/**
	 * Obtiene el discapacidades.
	 *
	 * @return el discapacidades
	 */
	public ArrayList<DiscapacidadBean> getDiscapacidades() {
		return discapacidades;
	}
	
	/**
	 * Establece el discapacidades.
	 *
	 * @param discapacidades el nuevo discapacidades
	 */
	public void setDiscapacidades(ArrayList<DiscapacidadBean> discapacidades) {
		this.discapacidades = discapacidades;
	}
	
	/**
	 * Obtiene el datos propios config bean.
	 *
	 * @return el datos propios config bean
	 */
	public ArrayList<DatosPropiosConfigBean> getDatosPropiosConfigBean() {
		return datosPropiosConfigBean;
	}
	
	/**
	 * Establece el datos propios config bean.
	 *
	 * @param datosPropiosConfigBean el nuevo datos propios config bean
	 */
	public void setDatosPropiosConfigBean(ArrayList<DatosPropiosConfigBean> datosPropiosConfigBean) {
		this.datosPropiosConfigBean = datosPropiosConfigBean;
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
