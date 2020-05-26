package es.map.ipsc.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * El Class BuscarConvocatoriasBean.
 */
public class BuscarConvocatoriasBean {
	
	/** el ministerio. */
	//Ministerio al que pertenece el centro gestor
	private String ministerio;
	
	/** el centro. */
	//centro gestor que ha publicado la convocatoria
	private String centro;
	
	/** el forma acceso. */
	//formas de acceder a la convocatoria
	private String formaAcceso;
	
	/** el cuerpo. */
	//Cuerpo o Escala
	private String cuerpo;
	
	/** el codigo cuerpo. */
	//Codigo Cuerpo o Escala
	private String codigoCuerpo;
	
	/** el grupo. */
	//Grupo
	private String grupo;
	
	/** el fecha. */
	//fecha de finalización de la convocatoria
	private String fecha;
	
	/** el id. */
	//Codigo de Convocatoria	
	private long id;
	
	/** el num total. */
	private int numTotal;
	
	/** el id ministerio. */
	private int idMinisterio;
	
	/** el siglas centro gestor. */
	private String siglasCentroGestor;
	
	/** el num documentos. */
	private long numDocumentos;
	
	/** el documentos. */
	private ArrayList<DocumentoBean> documentos;
	
	/** el estado solicitud. */
	private String estadoSolicitud;
	
	/** el presencial. */
	private Character presencial;
	
	
	/**
	 * Obtiene el id ministerio.
	 *
	 * @return el id ministerio
	 */
	public int getIdMinisterio() {
		return idMinisterio;
	}
	
	/**
	 * Establece el id ministerio.
	 *
	 * @param idMinisterio el nuevo id ministerio
	 */
	public void setIdMinisterio(int idMinisterio) {
		this.idMinisterio = idMinisterio;
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
	 * @param pMinisterio el nuevo ministerio
	 */
	public void setMinisterio(String pMinisterio) {
		this.ministerio = pMinisterio;
	}
	
	/**
	 * Obtiene el centro.
	 *
	 * @return el centro
	 */
	public String getCentro() {
		return centro;
	}
	
	/**
	 * Establece el centro.
	 *
	 * @param pCentro el nuevo centro
	 */
	public void setCentro(String pCentro) {
		this.centro = pCentro;
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
	 * @param pFormaAcceso el nuevo forma acceso
	 */
	public void setFormaAcceso(String pFormaAcceso) {
		this.formaAcceso = pFormaAcceso;
	}
	
	/**
	 * Obtiene el cuerpo.
	 *
	 * @return el cuerpo
	 */
	public String getCuerpo() {
		return cuerpo;
	}
	
	/**
	 * Establece el cuerpo.
	 *
	 * @param pCuerpo el nuevo cuerpo
	 */
	public void setCuerpo(String pCuerpo) {
		this.cuerpo = pCuerpo;
	}
	
	/**
	 * Obtiene el codigo cuerpo.
	 *
	 * @return el codigo cuerpo
	 */
	public String getCodigoCuerpo() {
		return codigoCuerpo;
	}
	
	/**
	 * Establece el codigo cuerpo.
	 *
	 * @param codigoCuerpo el nuevo codigo cuerpo
	 */
	public void setCodigoCuerpo(String codigoCuerpo) {
		this.codigoCuerpo = codigoCuerpo;
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
	 * @param pGrupo el nuevo grupo
	 */
	public void setGrupo(String pGrupo) {
		this.grupo = pGrupo;
	}
	
	/**
	 * Obtiene el fecha.
	 *
	 * @return el fecha
	 */
	public String getFecha() {
		return fecha;
	}
	
	/**
	 * Establece el fecha.
	 *
	 * @param pFecha el nuevo fecha
	 */
	public void setFecha(String pFecha) {
		this.fecha = pFecha;
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
	 * Obtiene el num documentos.
	 *
	 * @return el num documentos
	 */
	public long getNumDocumentos() {
		return numDocumentos;
	}
	
	/**
	 * Establece el num documentos.
	 *
	 * @param numDocumentos el nuevo num documentos
	 */
	public void setNumDocumentos(long numDocumentos) {
		this.numDocumentos = numDocumentos;
	}
	
	/**
	 * Obtiene el documentos.
	 *
	 * @return el documentos
	 */
	public ArrayList<DocumentoBean> getDocumentos() {
		return documentos;
	}
	
	/**
	 * Establece el documentos.
	 *
	 * @param documentos el nuevo documentos
	 */
	public void setDocumentos(ArrayList<DocumentoBean> documentos) {
		this.documentos = documentos;
	}
	
	/**
	 * Obtiene el estado solicitud.
	 *
	 * @return el estado solicitud
	 */
	public String getEstadoSolicitud() {
		return estadoSolicitud;
	}
	
	/**
	 * Establece el estado solicitud.
	 *
	 * @param string el nuevo estado solicitud
	 */
	public void setEstadoSolicitud(String string) {
		this.estadoSolicitud = string;
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
	
}
