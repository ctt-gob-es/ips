package es.map.ipsc.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import es.map.ips.model.CuerpoEscala;
import es.map.ips.model.DatosPropiosConfiguracion;
import es.map.ips.model.Discapacidad;
import es.map.ips.model.Especialidad;
import es.map.ips.model.FormaAcceso;
import es.map.ips.model.MotivoReduccionTasa;
import es.map.ips.model.OtrosTitulos;
import es.map.ips.model.Plantilla;
import es.map.ips.model.ProvinciaExamen;
import es.map.ips.model.TituloOficial;


/**
 * El Class ConvocatoriaBean.
 */
public class ConvocatoriaBean {
	
	
	/** el id. */
	private long id;
	
	/** el cuerpo escala. */
	private CuerpoEscala cuerpoEscala;
	
	/** el plantilla. */
	private Plantilla plantilla;
	
	/** el id plantilla. */
	private long idPlantilla;
	
	/** el forma acceso. */
	private FormaAcceso formaAcceso;
	
	/** el ejercicio. */
	private String ejercicio;
	
	/** el fecha boe. */
	private Date fechaBoe;
	
	/** el fecha inicio. */
	private Date fechaInicio;
	
	/** el fecha fin. */
	private Date fechaFin;
	
	/** el fecha ini sub. */
	private Date fechaIniSub;
	
	/** el fecha fin sub. */
	private Date fechaFinSub;
	
	/** el importe. */
	private float importe;
	
	/** el N plazas total. */
	private Integer NPlazasTotal;
	
	/** el N plazas discapacitados. */
	private Integer NPlazasDiscapacitados;	
	
	/** el tipo documento permitido. */
	private char tipoDocumentoPermitido;	
	
	/** el dirigido A. */
	private String dirigidoA;
	
	/** el fecha nacimiento minima. */
	private Date fechaNacimientoMinima;
	
	/** el fecha nacimiento maxima. */
	private Date fechaNacimientoMaxima;
	
	/** el tipo carga sol presencial. */
	private char tipoCargaSolPresencial;
	
	/** el solicituds. */
	private Set solicituds = new HashSet(0);
	
	/** el descarga modelo 790 s. */
	private Set descargaModelo790s = new HashSet(0);
	
	/** el documentos. */
	private Set documentos = new HashSet(0);
	
	/** el carga solicitud externos. */
	private Set cargaSolicitudExternos = new HashSet(0);
	
	/** el titulo oficials. */
	private List<TituloOficial> tituloOficials = new ArrayList<TituloOficial>(0);
	
	/** el motivo reduccion tasas. */
	private Set motivoReduccionTasas = new HashSet(0);
	
	/** el motivo reduccion tasas completo. */
	private ArrayList<MotivoReduccionTasa> motivoReduccionTasasCompleto;
	
	/** el motivo reduccion tasas incompleto. */
	private ArrayList<MotivoReduccionTasa> motivoReduccionTasasIncompleto;
	
	/** el especialidads. */
	private List<Especialidad> especialidads = new ArrayList<Especialidad>(0);
	
	/** el provincias examen. */
	private List<ProvinciaExamen> provinciasExamen = new ArrayList<ProvinciaExamen>(0);
	
	/** el otros titulos. */
	private List<OtrosTitulos> otrosTitulos = new ArrayList<OtrosTitulos>(0);
	
	/** el discapacidad. */
	private List<Discapacidad> discapacidad = new ArrayList<Discapacidad>(0);
	
	/** el datos propios configuracion. */
	private List<DatosPropiosConfiguracion> datosPropiosConfiguracion = new ArrayList<DatosPropiosConfiguracion>(0);

	
	/** el ministerio. */
	private String ministerio;
	
	/** el id ministerio. */
	private String idMinisterio;
	
	/** el cod forma acceso. */
	private String codFormaAcceso;
	
	/** el id cuerpo escala. */
	private String idCuerpoEscala;
	
	/** el des cuerpo escala. */
	private String desCuerpoEscala;
	
	/** el cod cuerpo escala. */
	private String codCuerpoEscala;
	
	/** el siglas centro gestor. */
	private String siglasCentroGestor;

	/** el id forma acceso. */
	private String idFormaAcceso;
	
	/** el des forma acceso. */
	private String desFormaAcceso;
	
	/** el des ministerio. */
	private String desMinisterio;
	
	/** el id ministerio convocante. */
	private String idMinisterioConvocante;
	
	/** el des ministerio convocante. */
	private String desMinisterioConvocante;
	
	/** el id centro gestor. */
	private String idCentroGestor;
	
	/** el des centro gestor. */
	private String desCentroGestor;
	
	/** el cod centro gestor. */
	private String codCentroGestor;
	
	/** el cod titulo oficial. */
	private String codTituloOficial;
	
	/** el id estado. */
	private Integer idEstado;
	
	/** el cod nuevo forma acceso. */
	// nuevo campor codigo acceso modificado
	private String codNuevoFormaAcceso;
	
	/** el modelo. */
	// Identificador del modelo (tabla MODELO)
	private String modelo;
	
	/** el num modelo. */
	// Número de modelo
	private String numModelo;
	
	/** el plantilla propios. */
	private List<PlantillaPropiosBean> plantillaPropios;

	/** el ocultar datos propios. */
	private Character ocultarDatosPropios;
	
	/**enlace informativo. */
	private String enlace;
	
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
	 * Obtiene el cod titulo oficial.
	 *
	 * @return el cod titulo oficial
	 */
	public String getCodTituloOficial() {
		return codTituloOficial;
	}
	
	/**
	 * Establece el cod titulo oficial.
	 *
	 * @param codTituloOficial el nuevo cod titulo oficial
	 */
	public void setCodTituloOficial(String codTituloOficial) {
		this.codTituloOficial = codTituloOficial;
	}
	
	/**
	 * Obtiene el cod centro gestor.
	 *
	 * @return el cod centro gestor
	 */
	public String getCodCentroGestor() {
		return codCentroGestor;
	}
	
	/**
	 * Establece el cod centro gestor.
	 *
	 * @param codCentroGestor el nuevo cod centro gestor
	 */
	public void setCodCentroGestor(String codCentroGestor) {
		this.codCentroGestor = codCentroGestor;
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
	 * Obtiene el id plantilla.
	 *
	 * @return el id plantilla
	 */
	public long getIdPlantilla() {
		return idPlantilla;
	}
	
	/**
	 * Establece el id plantilla.
	 *
	 * @param idPlantilla el nuevo id plantilla
	 */
	public void setIdPlantilla(long idPlantilla) {
		this.idPlantilla = idPlantilla;
	}
	
	/**
	 * Obtiene el id ministerio.
	 *
	 * @return el id ministerio
	 */
	public String getIdMinisterio() {
		return idMinisterio;
	}
	
	/**
	 * Establece el id ministerio.
	 *
	 * @param idMinisterio el nuevo id ministerio
	 */
	public void setIdMinisterio(String idMinisterio) {
		this.idMinisterio = idMinisterio;
	}
	
	/**
	 * Obtiene el id forma acceso.
	 *
	 * @return el id forma acceso
	 */
	public String getIdFormaAcceso() {
		return idFormaAcceso;
	}
	
	/**
	 * Establece el id forma acceso.
	 *
	 * @param idFormaAcceso el nuevo id forma acceso
	 */
	public void setIdFormaAcceso(String idFormaAcceso) {
		this.idFormaAcceso = idFormaAcceso;
	}
	
	/**
	 * Obtiene el id centro gestor.
	 *
	 * @return el id centro gestor
	 */
	public String getIdCentroGestor() {
		return idCentroGestor;
	}
	
	/**
	 * Establece el id centro gestor.
	 *
	 * @param idCentroGestor el nuevo id centro gestor
	 */
	public void setIdCentroGestor(String idCentroGestor) {
		this.idCentroGestor = idCentroGestor;
	}
	
	/**
	 * Obtiene el des centro gestor.
	 *
	 * @return el des centro gestor
	 */
	public String getDesCentroGestor() {
		return desCentroGestor;
	}
	
	/**
	 * Establece el des centro gestor.
	 *
	 * @param desCentroGestor el nuevo des centro gestor
	 */
	public void setDesCentroGestor(String desCentroGestor) {
		this.desCentroGestor = desCentroGestor;
	}
	
	/**
	 * Obtiene el des forma acceso.
	 *
	 * @return el des forma acceso
	 */
	public String getDesFormaAcceso() {
		return desFormaAcceso;
	}
	
	/**
	 * Establece el des forma acceso.
	 *
	 * @param desFormaAcceso el nuevo des forma acceso
	 */
	public void setDesFormaAcceso(String desFormaAcceso) {
		this.desFormaAcceso = desFormaAcceso;
	}
	
	/**
	 * Obtiene el des ministerio.
	 *
	 * @return el des ministerio
	 */
	public String getDesMinisterio() {
		return desMinisterio;
	}
	
	/**
	 * Establece el des ministerio.
	 *
	 * @param desMinisterio el nuevo des ministerio
	 */
	public void setDesMinisterio(String desMinisterio) {
		this.desMinisterio = desMinisterio;
	}
	
	/**
	 * Obtiene el des cuerpo escala.
	 *
	 * @return el des cuerpo escala
	 */
	public String getDesCuerpoEscala() {
		return desCuerpoEscala;
	}
	
	/**
	 * Establece el des cuerpo escala.
	 *
	 * @param desCuerpoEscala el nuevo des cuerpo escala
	 */
	public void setDesCuerpoEscala(String desCuerpoEscala) {
		this.desCuerpoEscala = desCuerpoEscala;
	}	
	
	/**
	 * Obtiene el id cuerpo escala.
	 *
	 * @return el id cuerpo escala
	 */
	public String getIdCuerpoEscala() {
		return idCuerpoEscala;
	}
	
	/**
	 * Establece el id cuerpo escala.
	 *
	 * @param idCuerpoEscala el nuevo id cuerpo escala
	 */
	public void setIdCuerpoEscala(String idCuerpoEscala) {
		this.idCuerpoEscala = idCuerpoEscala;
	}
	
	/**
	 * Obtiene el cod forma acceso.
	 *
	 * @return el cod forma acceso
	 */
	public String getCodFormaAcceso() {
		return codFormaAcceso;
	}
	
	/**
	 * Establece el cod forma acceso.
	 *
	 * @param codFormaAcceso el nuevo cod forma acceso
	 */
	public void setCodFormaAcceso(String codFormaAcceso) {
		this.codFormaAcceso = codFormaAcceso;
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
	 * Obtiene el id.
	 *
	 * @return el id
	 */
	public long getId() {
		return id;
	}
	
	/**
	 * Establece el id.
	 *
	 * @param pId el nuevo id
	 */
	public void setId(long pId) {
		this.id = pId;
	}
	
	/**
	 * Obtiene el cuerpo escala.
	 *
	 * @return el cuerpo escala
	 */
	public CuerpoEscala getCuerpoEscala() {
		return cuerpoEscala;
	}
	
	/**
	 * Establece el cuerpo escala.
	 *
	 * @param pCuerpoEscala el nuevo cuerpo escala
	 */
	public void setCuerpoEscala(CuerpoEscala pCuerpoEscala) {
		this.cuerpoEscala = pCuerpoEscala;
	}
	
	/**
	 * Obtiene el plantilla.
	 *
	 * @return el plantilla
	 */
	public Plantilla getPlantilla() {
		return plantilla;
	}
	
	/**
	 * Establece el plantilla.
	 *
	 * @param pPlantilla el nuevo plantilla
	 */
	public void setPlantilla(Plantilla pPlantilla) {
		this.plantilla = pPlantilla;
	}
	
	/**
	 * Obtiene el forma acceso.
	 *
	 * @return el forma acceso
	 */
	public FormaAcceso getFormaAcceso() {
		return formaAcceso;
	}
	
	/**
	 * Establece el forma acceso.
	 *
	 * @param pFormaAcceso el nuevo forma acceso
	 */
	public void setFormaAcceso(FormaAcceso pFormaAcceso) {
		this.formaAcceso = pFormaAcceso;
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
	 * @param pEjercicio el nuevo ejercicio
	 */
	public void setEjercicio(String pEjercicio) {
		this.ejercicio = pEjercicio;
	}
	
	/**
	 * Obtiene el fecha boe.
	 *
	 * @return el fecha boe
	 */
	public Date getFechaBoe() {
		return fechaBoe;
	}
	
	/**
	 * Establece el fecha boe.
	 *
	 * @param pFechaBoe el nuevo fecha boe
	 */
	public void setFechaBoe(Date pFechaBoe) {
		this.fechaBoe = pFechaBoe;
	}
	
	/**
	 * Obtiene el fecha inicio.
	 *
	 * @return el fecha inicio
	 */
	public Date getFechaInicio() {
		return fechaInicio;
	}
	
	/**
	 * Establece el fecha inicio.
	 *
	 * @param pFechaInicio el nuevo fecha inicio
	 */
	public void setFechaInicio(Date pFechaInicio) {
		this.fechaInicio = pFechaInicio;
	}
	
	/**
	 * Obtiene el fecha fin.
	 *
	 * @return el fecha fin
	 */
	public Date getFechaFin() {
		return fechaFin;
	}
	
	/**
	 * Establece el fecha fin.
	 *
	 * @param pFechaFin el nuevo fecha fin
	 */
	public void setFechaFin(Date pFechaFin) {
		this.fechaFin = pFechaFin;
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
	 * @param pImporte el nuevo importe
	 */
	public void setImporte(float pImporte) {
		this.importe = pImporte;
	}
	
	/**
	 * Obtiene el n plazas total.
	 *
	 * @return el n plazas total
	 */
	public Integer getNPlazasTotal() {
		return NPlazasTotal;
	}
	
	/**
	 * Establece el n plazas total.
	 *
	 * @param plazasTotal el nuevo n plazas total
	 */
	public void setNPlazasTotal(Integer plazasTotal) {
		NPlazasTotal = plazasTotal;
	}
	
	/**
	 * Obtiene el n plazas discapacitados.
	 *
	 * @return el n plazas discapacitados
	 */
	public Integer getNPlazasDiscapacitados() {
		return NPlazasDiscapacitados;
	}
	
	/**
	 * Establece el n plazas discapacitados.
	 *
	 * @param plazasDiscapacitados el nuevo n plazas discapacitados
	 */
	public void setNPlazasDiscapacitados(Integer plazasDiscapacitados) {
		NPlazasDiscapacitados = plazasDiscapacitados;
	}
	
	/**
	 * Obtiene el tipo documento permitido.
	 *
	 * @return el tipo documento permitido
	 */
	public char getTipoDocumentoPermitido() {
		return tipoDocumentoPermitido;
	}
	
	/**
	 * Establece el tipo documento permitido.
	 *
	 * @param pTipoDocumentoPermitido el nuevo tipo documento permitido
	 */
	public void setTipoDocumentoPermitido(char pTipoDocumentoPermitido) {
		this.tipoDocumentoPermitido = pTipoDocumentoPermitido;
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
	 * @param pDirigidoA el nuevo dirigido A
	 */
	public void setDirigidoA(String pDirigidoA) {
		this.dirigidoA = pDirigidoA;
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
	 * @param pFechaNacimientoMinima el nuevo fecha nacimiento minima
	 */
	public void setFechaNacimientoMinima(Date pFechaNacimientoMinima) {
		this.fechaNacimientoMinima = pFechaNacimientoMinima;
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
	 * @param pFechaNacimientoMaxima el nuevo fecha nacimiento maxima
	 */
	public void setFechaNacimientoMaxima(Date pFechaNacimientoMaxima) {
		this.fechaNacimientoMaxima = pFechaNacimientoMaxima;
	}
	
	/**
	 * Obtiene el tipo carga sol presencial.
	 *
	 * @return el tipo carga sol presencial
	 */
	public char getTipoCargaSolPresencial() {
		return tipoCargaSolPresencial;
	}
	
	/**
	 * Establece el tipo carga sol presencial.
	 *
	 * @param pTipoCargaSolPresencial el nuevo tipo carga sol presencial
	 */
	public void setTipoCargaSolPresencial(char pTipoCargaSolPresencial) {
		this.tipoCargaSolPresencial = pTipoCargaSolPresencial;
	}
	
	/**
	 * Obtiene el solicituds.
	 *
	 * @return el solicituds
	 */
	public Set getSolicituds() {
		return solicituds;
	}
	
	/**
	 * Establece el solicituds.
	 *
	 * @param pSolicituds el nuevo solicituds
	 */
	public void setSolicituds(Set pSolicituds) {
		this.solicituds = pSolicituds;
	}
	
	/**
	 * Obtiene el descarga modelo 790 s.
	 *
	 * @return el descarga modelo 790 s
	 */
	public Set getDescargaModelo790s() {
		return descargaModelo790s;
	}
	
	/**
	 * Establece el descarga modelo 790 s.
	 *
	 * @param pDescargaModelo790s el nuevo descarga modelo 790 s
	 */
	public void setDescargaModelo790s(Set pDescargaModelo790s) {
		this.descargaModelo790s = pDescargaModelo790s;
	}
	
	/**
	 * Obtiene el documentos.
	 *
	 * @return el documentos
	 */
	public Set getDocumentos() {
		return documentos;
	}
	
	/**
	 * Establece el documentos.
	 *
	 * @param pDocumentos el nuevo documentos
	 */
	public void setDocumentos(Set pDocumentos) {
		this.documentos = pDocumentos;
	}
	
	/**
	 * Obtiene el carga solicitud externos.
	 *
	 * @return el carga solicitud externos
	 */
	public Set getCargaSolicitudExternos() {
		return cargaSolicitudExternos;
	}
	
	/**
	 * Establece el carga solicitud externos.
	 *
	 * @param pCargaSolicitudExternos el nuevo carga solicitud externos
	 */
	public void setCargaSolicitudExternos(Set pCargaSolicitudExternos) {
		this.cargaSolicitudExternos = pCargaSolicitudExternos;
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
	 * @param pMotivoReduccionTasas el nuevo motivo reduccion tasas
	 */
	public void setMotivoReduccionTasas(Set pMotivoReduccionTasas) {
		this.motivoReduccionTasas = pMotivoReduccionTasas;
	}
	
	/**
	 * Obtiene el titulo oficials.
	 *
	 * @return el titulo oficials
	 */
	public List<TituloOficial> getTituloOficials() {
		return tituloOficials;
	}
	
	/**
	 * Establece el titulo oficials.
	 *
	 * @param tituloOficials el nuevo titulo oficials
	 */
	public void setTituloOficials(List<TituloOficial> tituloOficials) {
		this.tituloOficials = tituloOficials;
	}
	
	/**
	 * Obtiene el especialidads.
	 *
	 * @return el especialidads
	 */
	public List<Especialidad> getEspecialidads() {
		return especialidads;
	}
	
	/**
	 * Establece el especialidads.
	 *
	 * @param especialidads el nuevo especialidads
	 */
	public void setEspecialidads(List<Especialidad> especialidads) {
		this.especialidads = especialidads;
	}

	/**
	 * Obtiene el provincias examen.
	 *
	 * @return el provincias examen
	 */
	public List<ProvinciaExamen> getProvinciasExamen() {
		return provinciasExamen;
	}
	
	/**
	 * Establece el provincias examen.
	 *
	 * @param provinciasExamen el nuevo provincias examen
	 */
	public void setProvinciasExamen(List<ProvinciaExamen> provinciasExamen) {
		this.provinciasExamen = provinciasExamen;
	}
	
	/**
	 * Obtiene el id estado.
	 *
	 * @return el id estado
	 */
	public Integer getIdEstado() {
		return idEstado;
	}
	
	/**
	 * Establece el id estado.
	 *
	 * @param idEstado el nuevo id estado
	 */
	public void setIdEstado(Integer idEstado) {
		this.idEstado = idEstado;
	}
	
	/**
	 * Obtiene el cod nuevo forma acceso.
	 *
	 * @return el cod nuevo forma acceso
	 */
	public String getCodNuevoFormaAcceso() {
		return codNuevoFormaAcceso;
	}
	
	/**
	 * Establece el cod nuevo forma acceso.
	 *
	 * @param codNuevoFormaAcceso el nuevo cod nuevo forma acceso
	 */
	public void setCodNuevoFormaAcceso(String codNuevoFormaAcceso) {
		this.codNuevoFormaAcceso = codNuevoFormaAcceso;
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
	 * Obtiene el modelo.
	 *
	 * @return el modelo
	 */
	public String getModelo() {
		return modelo;
	}
	
	/**
	 * Establece el modelo.
	 *
	 * @param modelo el nuevo modelo
	 */
	public void setModelo(String modelo) {
		this.modelo = modelo;
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
	 * Obtiene el cod cuerpo escala.
	 *
	 * @return el cod cuerpo escala
	 */
	public String getCodCuerpoEscala() {
		return codCuerpoEscala;
	}
	
	/**
	 * Establece el cod cuerpo escala.
	 *
	 * @param codCuerpoEscala el nuevo cod cuerpo escala
	 */
	public void setCodCuerpoEscala(String codCuerpoEscala) {
		this.codCuerpoEscala = codCuerpoEscala;
	}
	
	/**
	 * Obtiene el des ministerio convocante.
	 *
	 * @return el des ministerio convocante
	 */
	public String getDesMinisterioConvocante() {
		return desMinisterioConvocante;
	}
	
	/**
	 * Establece el des ministerio convocante.
	 *
	 * @param desMinisterioConvocante el nuevo des ministerio convocante
	 */
	public void setDesMinisterioConvocante(String desMinisterioConvocante) {
		this.desMinisterioConvocante = desMinisterioConvocante;
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
	 * Obtiene el fecha ini sub.
	 *
	 * @return el fecha ini sub
	 */
	public Date getFechaIniSub() {
		return fechaIniSub;
	}
	
	/**
	 * Establece el fecha ini sub.
	 *
	 * @param fechaIniSub el nuevo fecha ini sub
	 */
	public void setFechaIniSub(Date fechaIniSub) {
		this.fechaIniSub = fechaIniSub;
	}
	
	/**
	 * Obtiene el fecha fin sub.
	 *
	 * @return el fecha fin sub
	 */
	public Date getFechaFinSub() {
		return fechaFinSub;
	}
	
	/**
	 * Establece el fecha fin sub.
	 *
	 * @param fechaFinSub el nuevo fecha fin sub
	 */
	public void setFechaFinSub(Date fechaFinSub) {
		this.fechaFinSub = fechaFinSub;
	}
	
	/**
	 * Obtiene el otros titulos.
	 *
	 * @return el otros titulos
	 */
	public List<OtrosTitulos> getOtrosTitulos() {
		return otrosTitulos;
	}
	
	/**
	 * Establece el otros titulos.
	 *
	 * @param otrosTitulos el nuevo otros titulos
	 */
	public void setOtrosTitulos(List<OtrosTitulos> otrosTitulos) {
		this.otrosTitulos = otrosTitulos;
	}
	
	/**
	 * Obtiene el discapacidad.
	 *
	 * @return el discapacidad
	 */
	public List<Discapacidad> getDiscapacidad() {
		return discapacidad;
	}
	
	/**
	 * Establece el discapacidad.
	 *
	 * @param discapacidad el nuevo discapacidad
	 */
	public void setDiscapacidad(List<Discapacidad> discapacidad) {
		this.discapacidad = discapacidad;
	}
	
	/**
	 * Obtiene el datos propios configuracion.
	 *
	 * @return el datos propios configuracion
	 */
	public List<DatosPropiosConfiguracion> getDatosPropiosConfiguracion() {
		return datosPropiosConfiguracion;
	}
	
	/**
	 * Establece el datos propios configuracion.
	 *
	 * @param datosPropiosConfiguracion el nuevo datos propios configuracion
	 */
	public void setDatosPropiosConfiguracion(List<DatosPropiosConfiguracion> datosPropiosConfiguracion) {
		this.datosPropiosConfiguracion = datosPropiosConfiguracion;
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

	public String getEnlace() {
		return enlace;
	}

	public void setEnlace(String enlace) {
		this.enlace = enlace;
	}
	
}
