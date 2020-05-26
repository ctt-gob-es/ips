package es.map.ipsc.form;

import java.util.List;

import es.map.ips.common.spring.SpringForm;

/**
 * El Class BuscaConvocatoriasForm.
 */
public class BuscaConvocatoriasForm extends SpringForm {

	
	/** el direccion. */
	private String direccion;
	
	/** el campo. */
	private String campo;
	
	/** el ministerio. */
	private String ministerio; 
	
	/** el grupo. */
	private String grupo;
	
	/** el forma acceso. */
	private String formaAcceso; 
	
	/** el centro. */
	private String centro[];
	
	/** el centro 2. */
	private String centro2;
	
	/** el especialidad. */
	private String especialidad; 
	
	/** el titulacion. */
	private String titulacion;
	
	/** el provincia examen. */
	private String provinciaExamen;
    
    /** el lugar. */
    private String lugar;
    
    /** el submit. */
    private String submit; 
    
    /** el num registro. */
    private String numRegistro;
    
    /** el pagina actual. */
    private String paginaActual;	
	
	/** el paginas totales. */
	private String paginasTotales;
	
	/** el cambios. */
	private String cambios;
	
	/** el idiomas. */
	private String idiomas;
	
	/** el certificado. */
	private String certificado;
	
	/** el certificado nuevo. */
	private String certificadoNuevo;
	
	/** el search. */
	private String search;
	
	/** el cuerpo escala. */
	private String cuerpoEscala[];
	
	/** el cuerpo escala 2. */
	private String cuerpoEscala2;
	
	/** el ver todo. */
	private boolean verTodo;
	
	/** el ver todo sub. */
	private boolean verTodoSub;
	
	/** el documentos. */
	private List<String> documentos;
	
	/** el numero pagina ir. */
	private Integer numeroPaginaIr;
	
	/** el pulsa ir. */
	private boolean pulsaIr;

	
	/**
	 * Obtiene el idiomas.
	 *
	 * @return el idiomas
	 */
	public String getIdiomas() {
		return idiomas;
	}
	
	/**
	 * Establece el idiomas.
	 *
	 * @param idiomas el nuevo idiomas
	 */
	public void setIdiomas(String idiomas) {
		this.idiomas = idiomas;
	}
	
	/**
	 * Obtiene el direccion.
	 *
	 * @return la direccion de la convocatoria
	 */
	public String getDireccion() {
		return direccion;
	}
	
	/**
	 * Guarda el valor en el objeto.
	 *
	 * @param pDireccion el nuevo direccion
	 */
	public void setDireccion(String pDireccion) {
		this.direccion = pDireccion;
	}
	
	/**
	 * Obtiene el campo.
	 *
	 * @return el campo por el que se ordena
	 */
	public String getCampo() {
		return campo;
	}
	
	/**
	 * Guarda el valor en el objeto.
	 *
	 * @param pCampo el nuevo campo
	 */
	public void setCampo(String pCampo) {
		this.campo = pCampo;
	}
	
	/**
	 * Obtiene el ministerio.
	 *
	 * @return la direccion de ordenacion de la convocatoria
	 */
	public String getMinisterio() {
		return ministerio;
	}
	
	/**
	 * Guarda el valor en el objeto.
	 *
	 * @param pMinisterio el nuevo ministerio
	 */
	public void setMinisterio(String pMinisterio) {
		this.ministerio = pMinisterio;
	}
	
	/**
	 * Obtiene el grupo.
	 *
	 * @return el grupo de la convocatoria
	 */
	public String getGrupo() {
		return grupo;
	}
	
	/**
	 * Guarda el valor en el objeto.
	 *
	 * @param pGrupo el nuevo grupo
	 */
	public void setGrupo(String pGrupo) {
		this.grupo = pGrupo;
	}
	
	/**
	 * Obtiene el forma acceso.
	 *
	 * @return la forma de acceso de la convocatoria
	 */
	public String getFormaAcceso() {
		return formaAcceso;
	}
	
	/**
	 * Guarda el valor en el objeto.
	 *
	 * @param pFormaAcceso el nuevo forma acceso
	 */
	public void setFormaAcceso(String pFormaAcceso) {
		this.formaAcceso = pFormaAcceso;
	}
	
	/**
	 * Obtiene el centro.
	 *
	 * @return el centro de la convocatoria
	 */
	public String[] getCentro() {
		return centro;
	}
	
	/**
	 * Guarda el valor en el objeto.
	 *
	 * @param pCentro el nuevo centro
	 */
	public void setCentro(String[] pCentro) {
		this.centro = pCentro;
	}
	
	/**
	 * Obtiene el especialidad.
	 *
	 * @return la especialidad de la convocatoria
	 */
	public String getEspecialidad() {
		return especialidad;
	}
	
	/**
	 * Guarda el valor en el objeto.
	 *
	 * @param pEspecialidad el nuevo especialidad
	 */
	public void setEspecialidad(String pEspecialidad) {
		this.especialidad = pEspecialidad;
	}
	
	/**
	 * Obtiene el titulacion.
	 *
	 * @return la titulacion de la convocatoria
	 */
	public String getTitulacion() {
		return titulacion;
	}
	
	/**
	 * Guarda el valor en el objeto.
	 *
	 * @param pTitulacion el nuevo titulacion
	 */
	public void setTitulacion(String pTitulacion) {
		this.titulacion = pTitulacion;
	}
	
	/**
	 * Obtiene el lugar.
	 *
	 * @return el lugar de la convocatoria
	 */
	public String getLugar() {
		return lugar;
	}
	
	/**
	 * Guarda el valor en el objeto.
	 *
	 * @param pLugar el nuevo lugar
	 */
	public void setLugar(String pLugar) {
		this.lugar = pLugar;
	}
	
	/**
	 * Obtiene el submit.
	 *
	 * @return el valor del envio de la convocatoria
	 */
	public String getSubmit() {
		return submit;
	}
	
	/**
	 * Guarda el valor en el objeto.
	 *
	 * @param pSubmit el nuevo submit
	 */
	public void setSubmit(String pSubmit) {
		this.submit = pSubmit;
	}

	/**
	 * Obtiene el num registro.
	 *
	 * @return el numero de registros a mostrar por pagina
	 */
	public String getNumRegistro() {
		return numRegistro;
	}	
	
	/**
	 * Guarda el valor en el objeto.
	 *
	 * @param pNumRegistro el nuevo num registro
	 */
	public void setNumRegistro(String pNumRegistro) {
		this.numRegistro = pNumRegistro;
	}
	
	/**
	 * Obtiene el provincia examen.
	 *
	 * @return el provincia examen
	 */
	public String getProvinciaExamen() {
		return provinciaExamen;
	}
	
	/**
	 * Establece el provincia examen.
	 *
	 * @param provinciaExamen el nuevo provincia examen
	 */
	public void setProvinciaExamen(String provinciaExamen) {
		this.provinciaExamen = provinciaExamen;
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
	
	/**
	 * Obtiene el certificado.
	 *
	 * @return el certificado
	 */
	public String getCertificado() {
		return certificado;
	}
	
	/**
	 * Establece el certificado.
	 *
	 * @param certificado el nuevo certificado
	 */
	public void setCertificado(String certificado) {
		this.certificado = certificado;
	}
	
	/**
	 * Obtiene el certificado nuevo.
	 *
	 * @return el certificado nuevo
	 */
	public String getCertificadoNuevo() {
		return certificadoNuevo;
	}
	
	/**
	 * Establece el certificado nuevo.
	 *
	 * @param certificadoNuevo el nuevo certificado nuevo
	 */
	public void setCertificadoNuevo(String certificadoNuevo) {
		this.certificadoNuevo = certificadoNuevo;
	}
	
	/**
	 * Comprueba si es ver todo.
	 *
	 * @return verdadero, si es ver todo
	 */
	public boolean isVerTodo() {
		return verTodo;
	}
	
	/**
	 * Establece el ver todo.
	 *
	 * @param verTodo el nuevo ver todo
	 */
	public void setVerTodo(boolean verTodo) {
		this.verTodo = verTodo;
	}
	
	/**
	 * Obtiene el search.
	 *
	 * @return el search
	 */
	public String getSearch() {
		return search;
	}
	
	/**
	 * Establece el search.
	 *
	 * @param search el nuevo search
	 */
	public void setSearch(String search) {
		this.search = search;
	}
	
	/**
	 * Obtiene el cuerpo escala.
	 *
	 * @return el cuerpo escala
	 */
	public String[] getCuerpoEscala() {
		return cuerpoEscala;
	}
	
	/**
	 * Establece el cuerpo escala.
	 *
	 * @param cuerpoEscala el nuevo cuerpo escala
	 */
	public void setCuerpoEscala(String[] cuerpoEscala) {
		this.cuerpoEscala = cuerpoEscala;
	}
	
	/**
	 * Obtiene el documentos.
	 *
	 * @return el documentos
	 */
	public List<String> getDocumentos() {
		return documentos;
	}
	
	/**
	 * Establece el documentos.
	 *
	 * @param documentos el nuevo documentos
	 */
	public void setDocumentos(List<String> documentos) {
		this.documentos = documentos;
	}
	
	/**
	 * Obtiene el centro 2.
	 *
	 * @return el centro 2
	 */
	public String getCentro2() {
		return centro2;
	}
	
	/**
	 * Establece el centro 2.
	 *
	 * @param centro2 el nuevo centro 2
	 */
	public void setCentro2(String centro2) {
		this.centro2 = centro2;
	}
	
	/**
	 * Obtiene el cuerpo escala 2.
	 *
	 * @return el cuerpo escala 2
	 */
	public String getCuerpoEscala2() {
		return cuerpoEscala2;
	}
	
	/**
	 * Establece el cuerpo escala 2.
	 *
	 * @param cuerpoEscala2 el nuevo cuerpo escala 2
	 */
	public void setCuerpoEscala2(String cuerpoEscala2) {
		this.cuerpoEscala2 = cuerpoEscala2;
	}
	
	/**
	 * Comprueba si es ver todo sub.
	 *
	 * @return verdadero, si es ver todo sub
	 */
	public boolean isVerTodoSub() {
		return verTodoSub;
	}
	
	/**
	 * Establece el ver todo sub.
	 *
	 * @param verTodoSub el nuevo ver todo sub
	 */
	public void setVerTodoSub(boolean verTodoSub) {
		this.verTodoSub = verTodoSub;
	}
	
}
