package es.map.ipsc.spring.subsanar;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import es.map.ips.common.model.Comparator;
import es.map.ips.common.model.OrderType;
import es.map.ips.common.model.SearchResult;
import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.AvisoQuery;
import es.map.ips.model.CentroGestor;
import es.map.ips.model.CentroGestorQuery;
import es.map.ips.model.ConvocatoriaQuery;
import es.map.ips.model.ConvocatoriasAbiertasViewQuery;
import es.map.ips.model.ConvocatoriasSubsanarViewQuery;
import es.map.ips.model.CuerpoEscala;
import es.map.ips.model.CuerpoEscalaQuery;
import es.map.ips.model.DocumentoQuery;
import es.map.ips.model.EstadoAvisoQuery;
import es.map.ips.model.SolComunViewQuery;
import es.map.ipsc.Constantes;
import es.map.ipsc.bean.AvisoBean;
import es.map.ipsc.bean.BuscarConvocatoriasBean;
import es.map.ipsc.bean.BuscarSolicitudesBean;
import es.map.ipsc.bean.CiudadanoBean;
import es.map.ipsc.bean.DocumentoBean;
import es.map.ipsc.form.BuscaConvocatoriasForm;
import es.map.ipsc.manager.convocatorias.ConvocatoriasManager;
import es.map.ipsc.manager.convocatorias.DocumentosConvocatoriaManager;
import es.map.ipsc.manager.cuerpoEscala.CuerpoEscalaManager;
import es.map.ipsc.manager.solicitudes.SolicitudManager;
import es.map.ipsc.manager.tablaBase.TablaBaseManager;

/**
 * El Class BuscarConvocatoriasSubSpring.
 */
public class BuscarConvocatoriasSubSpring extends AbstractSpring {
	
	/** La constante MESSAGE_RESOUCE. */
	private static final String MESSAGE_RESOUCE = "MessageResources";
	
	/** La constante RESOURCE_MESSAGE. */
	private static final ResourceBundle RESOURCE_MESSAGE = ResourceBundle.getBundle(MESSAGE_RESOUCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(BuscarConvocatoriasSubSpring.class);
	
	/** La constante STRING_LLAMADA. */
	private static final String STRING_LLAMADA = "llamada";
	
	/** el convocatorias manager. */
	private ConvocatoriasManager convocatoriasManager;
	
	/** el solicitudes manager. */
	private SolicitudManager solicitudesManager;
	
	/** el tabla base manager. */
	private TablaBaseManager tablaBaseManager;
	
	/** el cuerpo escala manager. */
	private CuerpoEscalaManager cuerpoEscalaManager;
	
	/** el documento convocatorias manager. */
	private DocumentosConvocatoriaManager documentoConvocatoriasManager;

	
	/**
	 * Crea una nueva buscar convocatorias sub spring.
	 */
	public BuscarConvocatoriasSubSpring() {
		try {
			setConvocatoriasManager((ConvocatoriasManager) getBean("convocatoriaManager"));
			setSolicitudesManager((SolicitudManager) getBean("solicitudesManager"));
			setTablasBaseManager((TablaBaseManager) getBean("tablasBaseManager"));
			setCuerpoEscalaManager((CuerpoEscalaManager) getBean("cuerpoEscalaManager"));
			setDocumentoConvocatoriasManager((DocumentosConvocatoriaManager) getBean("documentoConvocatoriasManager"));
			
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error buscar convocatorias",e);
		}
	}


	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		logger.debug("BuscarConvocatoriasSubSpring -start");
		//Recoger valores del request
		String busqueda = this.getRequest().getParameter("submit");
		String numReg = this.getRequest().getParameter("numRegistro");
		String palabraBusqueda = this.getRequest().getParameter("search");
		
		if (!StringUtils.isEmpty(palabraBusqueda)) {
			busqueda = new String("Buscar");
		}
		
		//*********** METO EL PARAMETRO QUE ME INDICA EN QUE MENU ESTOY **********
		String menu_convocatoria = RESOURCE_MESSAGE.getString("field.menu.listaConvoc");
		this.getRequest().getSession().setAttribute("pagActiva", menu_convocatoria);
		//****************************************************************** 
		
		// Si viene de contactar se limpia el usuario de la sesion
		
		String vienede = (String) this.getRequest().getParameter("vienede");
		if (vienede != null && vienede.equals("Contactar")){
			this.getRequest().getSession().removeAttribute("usuarioClave");
		}
		
		BuscaConvocatoriasForm formulario = null;
		formulario = (BuscaConvocatoriasForm) form;
		formulario.setCentro(null);
		formulario.setCuerpoEscala(null);
		boolean verTodoSub = formulario.isVerTodoSub();
		boolean verTodo = formulario.isVerTodo();
		if(!"Buscar".equals(busqueda)){
			formulario.setSearch(null);
			formulario.setCampo(null);
			formulario.setSubmit(null);
		}
		
		//Si llega el parametroCentroGestor
		if (!"".equals(formulario.getSearch()) && formulario.getSearch() != null){
			CentroGestorQuery centroGestorQueryDescripcion = new CentroGestorQuery();
			centroGestorQueryDescripcion.setDescripcion(formulario.getSearch());
			centroGestorQueryDescripcion.setDescripcionComparator(Comparator.LIKE_LEFT_RIGHT);
			SearchResult<CentroGestor> centroGestorAuxDescripcion = tablaBaseManager.buscarCentroGestorCodModel(centroGestorQueryDescripcion);
			if(centroGestorAuxDescripcion.size() > 0){
				List<CentroGestor> ListaCentroGestor = centroGestorAuxDescripcion.getResults();
				String[] Centro = new String[ListaCentroGestor.size()];
				for(int i=0; i<ListaCentroGestor.size(); i++) {
					CentroGestor CentroGestorAux = ListaCentroGestor.get(i);
					Centro[i] = String.valueOf(CentroGestorAux.getIdCentroGestor());
				}
				formulario.setCentro(Centro);
			}else{
				CentroGestorQuery centroGestorQuerySiglas = new CentroGestorQuery();
				centroGestorQuerySiglas.setSiglas(formulario.getSearch());
				centroGestorQuerySiglas.setSiglasComparator(Comparator.LIKE_LEFT_RIGHT);
				SearchResult<CentroGestor> centroGestorAuxSiglas = tablaBaseManager.buscarCentroGestorCodModel(centroGestorQuerySiglas);
				if(centroGestorAuxSiglas.size() > 0){
					List<CentroGestor> ListaSiglasCentroGestor = centroGestorAuxSiglas.getResults();
					String[] Centro = new String[ListaSiglasCentroGestor.size()];
					for(int i=0; i<ListaSiglasCentroGestor.size(); i++) {
						CentroGestor CentroGestorAux = ListaSiglasCentroGestor.get(i);
						Centro[i] = String.valueOf(CentroGestorAux.getIdCentroGestor());
					}
					formulario.setCentro(Centro);
				}
			}
			CuerpoEscalaQuery cuerpoEscalaQueryDescripcion = new CuerpoEscalaQuery();
			cuerpoEscalaQueryDescripcion.setDescripcion(formulario.getSearch());
			cuerpoEscalaQueryDescripcion.setDescripcionComparator(Comparator.LIKE_LEFT_RIGHT);
			try {
				SearchResult<CuerpoEscala> cuerpoEscalaAuxDescripcion = cuerpoEscalaManager.buscarCuerpoEscalaId(cuerpoEscalaQueryDescripcion);
				if (cuerpoEscalaAuxDescripcion.size() > 0){
					List<CuerpoEscala> ListaCuerpoEscala = cuerpoEscalaAuxDescripcion.getResults();
					String[] CuerpoEscala = new String[ListaCuerpoEscala.size()];
					for(int i=0; i<ListaCuerpoEscala.size(); i++) {
						CuerpoEscala CuerpoEscalaAux = ListaCuerpoEscala.get(i);
						CuerpoEscala[i] = String.valueOf(CuerpoEscalaAux.getId());
					}
					formulario.setCuerpoEscala(CuerpoEscala);
				}
			}catch(Exception e) {
				this.setRequestAttribute("BúsquedaFallida", 0);
				logger.info("Búsqueda incorrecta");
				logger.error("Error Busqueda incorrecta",e);
			}
		}
		
		String formRequest = this.getRequest().getParameter("form");
		logger.info("FormRequest: "+formRequest);
		
		if(StringUtils.isEmpty(busqueda)) {
			if(formRequest != null){
				formulario.setMinisterio("");
				formulario.setGrupo("");
				String[] cuerpoEscala = null;
				formulario.setCuerpoEscala(cuerpoEscala);
				formulario.setFormaAcceso("");
				String[] centro = null;
				formulario.setCentro(centro);
				formulario.setEspecialidad("");
				formulario.setTitulacion("");
				formulario.setProvinciaExamen("");
				formulario.setNumRegistro(Constantes.NUMERO_REGISTROS_TOTALES);	
			}
		}
		
		ConvocatoriasSubsanarViewQuery convocatoriaQuery;
		ConvocatoriasAbiertasViewQuery convocatoriaQueryMain = new ConvocatoriasAbiertasViewQuery();
		
		if(this.getRequest().getParameter(STRING_LLAMADA) != null){
			busqueda = this.getRequest().getParameter(STRING_LLAMADA);
		}
		
		//Si ha seleccionado un campo de ordenacion se le asigna el atributo de ordenacion
		logger.debug("Busqueda: "+busqueda);
		
		if(this.getRequest().getParameter(STRING_LLAMADA) != null){
			busqueda = this.getRequest().getParameter(STRING_LLAMADA);
		}
		
		logger.info("Busqueda: "+busqueda);
				
		//Crea el query de la busqueda		
		convocatoriaQuery=crearQueryConvocatoria(formulario,numReg,busqueda);
		
		//Busca las convocatorias por los parametros introducidos
		List<BuscarConvocatoriasBean> convocatorias = null;
		Integer convocatoriasMain = 0;
		
		convocatorias = convocatoriasManager.buscarConvocatoriasSubsanadasView(convocatoriaQuery);
		
		convocatoriasMain = convocatoriasManager.recuperarNumConvocatoriaAbiertas(convocatoriaQueryMain);
		
		for (int contadorConvocatoria = 0; contadorConvocatoria < convocatorias.size(); contadorConvocatoria++){
			DocumentoQuery documentosQuery = new DocumentoQuery();
			ConvocatoriaQuery convocatoriaQueryDocs = new ConvocatoriaQuery();
			convocatoriaQueryDocs.setId(convocatorias.get(contadorConvocatoria).getId());
			documentosQuery.setConvocatoria(convocatoriaQueryDocs);
			ArrayList<DocumentoBean> documentos = documentoConvocatoriasManager.buscarDocumentosConvocatoria(documentosQuery);
			Long tamanioTotal = 0L;
			convocatorias.get(contadorConvocatoria).setEstadoSolicitud("No inscrito");
		        if(documentos != null){
		        	for(int i=0;i<documentos.size();i++){
		        		tamanioTotal = tamanioTotal + documentos.get(i).getTamano();
		        	}
		        	convocatorias.get(contadorConvocatoria).setNumDocumentos(tamanioTotal);
		        	convocatorias.get(contadorConvocatoria).setDocumentos(documentos);
		        }
		}
		
		if (verTodoSub && convocatorias != null && convocatorias.size()!= 0){
			this.setRequestAttribute("verTodoSub", 0);
			this.setRequestAttribute("numTotalConvocatoriasSub", convocatorias.get(0).getNumTotal());
		}else{
			this.setRequestAttribute("verTodoSub", 1);
			this.setRequestAttribute("numTotalConvocatoriasSub", convocatorias.size());
		}
		
		if (verTodo && convocatoriasMain != null && convocatoriasMain>0){
			this.setRequestAttribute("verTodo", 0);
			this.setRequestAttribute("numTotalConvocatorias", convocatoriasMain);
		}else{
			this.setRequestAttribute("verTodo", 1);
			this.setRequestAttribute("numTotalConvocatorias", convocatoriasMain);
		}
		
		
		if(convocatorias != null && convocatorias.size()!= 0){
			if(!verTodoSub) {
				for(int i=convocatorias.size(); i > Integer.parseInt(Constantes.NUMERO_REGISTROS_TOTALES_MOSTRAR); i--) {
					convocatorias.remove(i-1);
				}
			}
			CiudadanoBean usuActual = (CiudadanoBean) this.getRequest().getSession().getAttribute("usuarioClave");
			if (usuActual == null) {
				usuActual = new CiudadanoBean();
				// el tipo de usuario es un usuario no logado en cl@ave
				usuActual.setTipoPersona("U");
			}
			this.getRequest().setAttribute("usuActual", usuActual);
			
			if(usuActual != null && usuActual.getNif() != null){
				SolComunViewQuery solicitudComunQuery = crearQuerySolicitud(formulario,numReg,busqueda);
				ArrayList<BuscarSolicitudesBean> solicitudes = null;
				solicitudComunQuery.setNifSolicitud(usuActual.getNif());
				solicitudes = solicitudesManager.buscarSolicitudesAll(solicitudComunQuery);
				for (BuscarSolicitudesBean solicitudCheck: solicitudes){
					for (BuscarConvocatoriasBean convocatoriaCheck: convocatorias){
						
						// TODO crear la logica de no inscrito, inscrito y continuar para FH
						if (solicitudCheck.getIdConvocatoria() == convocatoriaCheck.getId()){
							
							
							if (solicitudCheck.getIdEstadoInscripcion() == 3){
								convocatoriaCheck.setEstadoSolicitud("Inscrito");
							}
							else{
								convocatoriaCheck.setEstadoSolicitud("Continuar");
							}
							break;
						}
					}
				}
			}
		}else{
			if((formulario.getEspecialidad() == null || "".equals(formulario.getEspecialidad())) &&
					(formulario.getProvinciaExamen() == null || "".equals(formulario.getProvinciaExamen())) &&
					(formulario.getTitulacion() == null || "".equals(formulario.getTitulacion())) &&
					(formulario.getMinisterio() == null || "".equals(formulario.getMinisterio())) &&
					(formulario.getCentro() == null || "".equals(formulario.getCentro())) && 
					(formulario.getFormaAcceso() == null || "".equals(formulario.getFormaAcceso())) && 
					(formulario.getGrupo() == null || "".equals(formulario.getGrupo())) &&
					(formulario.getCuerpoEscala() == null || "".equals(formulario.getCuerpoEscala()))
					){
				setRequestAttribute("noConvocatoriasAbiertas", "true");
			}
		}
		
		
		//Busca los avisos publicados para mostrarlos en la pagina
		buscarAvisos();
		
		setRequestAttribute("convocatorias", convocatorias);
		setRequestAttribute("submit", busqueda);
		setRequestAttribute("mensajeNoRegistrado", this.getRequest().getAttribute("mensajeNoRegistrado"));
		
		return "success";
	}
	
	/**
	 * Buscar avisos.
	 */
	private void buscarAvisos() {
		AvisoQuery avisoQuery = new AvisoQuery();
		EstadoAvisoQuery estadoAvisoQuery = new EstadoAvisoQuery();
		estadoAvisoQuery.setId(Constantes.AVISO_ESTADO_PUBLICADO);
		avisoQuery.setEstadoAviso(estadoAvisoQuery);
		ArrayList<AvisoBean> avisoBean = tablaBaseManager.buscarAvisos(avisoQuery);
		setRequestAttribute("avisos", avisoBean);
	}

	/**
	 * Crear query convocatoria.
	 *
	 * @param formulario el formulario
	 * @param numReg el num reg
	 * @param busqueda el busqueda
	 * @return el convocatorias subsanar view query
	 */
	private ConvocatoriasSubsanarViewQuery crearQueryConvocatoria(BuscaConvocatoriasForm formulario,String numReg,String busqueda) {
		logger.info("Entra a crear la Query");
		String[] centroGestor = formulario.getCentro();
		String campo = formulario.getCampo();
		String direccion = formulario.getDireccion();
		String[] cuerpoEscala = formulario.getCuerpoEscala();
		
			//Para el primer paso para el action, se establece que los registros por pagina son 10
		formulario.setNumRegistro(Constantes.NUMERO_REGISTROS_TOTALES);
		
		ConvocatoriasSubsanarViewQuery convocatoriaQuery = new ConvocatoriasSubsanarViewQuery();
		
		convocatoriaQuery.setMaxResults(Integer.parseInt(Constantes.NUMERO_REGISTROS_TOTALES));
		convocatoriaQuery.setFirstResult(0);
		convocatoriaQuery.setCalculateNumResults(true);
		
		if(centroGestor != null && !"".equals(centroGestor) && !"0".equals(centroGestor)){
			for(int i=0; i<centroGestor.length; i++) {
				convocatoriaQuery.addIdCentroGestorIn(Integer.parseInt(centroGestor[i])); 
			}
		}
		
		if(cuerpoEscala != null && !"".equals(cuerpoEscala) && !"0".equals(cuerpoEscala)){
			for(int i=0; i<cuerpoEscala.length; i++) {
				convocatoriaQuery.addIdCuerpoEscalaIn(Integer.parseInt(cuerpoEscala[i]));
			}
		}
		
		logger.info("Campo: "+campo);
		logger.info("Direccion: "+direccion);
		
		if(campo != null && !"0".equals(campo) && !"".equals(campo)){
			if("up".equals(direccion)){
					convocatoriaQuery.addOrder(campo,OrderType.ASC);
			}else{
					convocatoriaQuery.addOrder(campo,OrderType.DESC);
			}
		}
		
		return convocatoriaQuery;
	}	
	
	/**
	 * Crear query solicitud.
	 *
	 * @param formulario el formulario
	 * @param numReg el num reg
	 * @param busqueda el busqueda
	 * @return el sol comun view query
	 */
	private SolComunViewQuery crearQuerySolicitud(BuscaConvocatoriasForm formulario,String numReg,String busqueda) {
		String campo = formulario.getCampo();
		String direccion = formulario.getDireccion();
		
		int numRegistros = 0;
		//Para el primer paso para el action, se establece que los registros por pagina son 5
		formulario.setNumRegistro(Constantes.NUMERO_REGISTROS_TOTALES);
		numRegistros = Integer.parseInt(formulario.getNumRegistro());
		
		SolComunViewQuery solicitudComunQuery = new SolComunViewQuery();
		if (!formulario.isVerTodoSub()) {
			solicitudComunQuery.setMaxResults(numRegistros);
			solicitudComunQuery.setFirstResult(0);
		}else{
			solicitudComunQuery.setMaxResults(100);
			solicitudComunQuery.setFirstResult(0);
		}
		solicitudComunQuery.setCalculateNumResults(true);
		solicitudComunQuery.addIdEstadoConvocatoriaIn(9);
		
		if(campo != null && !"0".equals(campo) && !"".equals(campo)){
			if("up".equals(direccion)){
				solicitudComunQuery.addOrder(campo,OrderType.ASC);
			}else{
				solicitudComunQuery.addOrder(campo,OrderType.DESC);
			}
		}
		
		return solicitudComunQuery;
	}

	/**
	 * Establece el convocatorias manager.
	 *
	 * @param convocatoriasManager el nuevo convocatorias manager
	 */
	public void setConvocatoriasManager(ConvocatoriasManager convocatoriasManager) {
		this.convocatoriasManager = convocatoriasManager;
	}


	/**
	 * Obtiene el convocatorias manager.
	 *
	 * @return el convocatorias manager
	 */
	public ConvocatoriasManager getConvocatoriasManager() {
		return convocatoriasManager;
	}


	/**
	 * Obtiene el tablas base manager.
	 *
	 * @return el tablas base manager
	 */
	public TablaBaseManager getTablasBaseManager() {
		return tablaBaseManager;
	}


	/**
	 * Establece el tablas base manager.
	 *
	 * @param tablasBaseManager el nuevo tablas base manager
	 */
	public void setTablasBaseManager(TablaBaseManager tablasBaseManager) {
		this.tablaBaseManager = tablasBaseManager;
	}
	
	/**
	 * Establece el cuerpo escala manager.
	 *
	 * @param cuerpoEscalaManager el nuevo cuerpo escala manager
	 */
	public void setCuerpoEscalaManager(CuerpoEscalaManager cuerpoEscalaManager) {
		this.cuerpoEscalaManager = cuerpoEscalaManager;
	}
	
	/**
	 * Obtiene el documento convocatorias manager.
	 *
	 * @return el documento convocatorias manager
	 */
	public DocumentosConvocatoriaManager getDocumentoConvocatoriasManager() {
		return documentoConvocatoriasManager;
	}
	
	/**
	 * Establece el documento convocatorias manager.
	 *
	 * @param documentoConvocatoriasManager el nuevo documento convocatorias manager
	 */
	public void setDocumentoConvocatoriasManager(
			DocumentosConvocatoriaManager documentoConvocatoriasManager) {
		this.documentoConvocatoriasManager = documentoConvocatoriasManager;
	}
	
	/**
	 * Obtiene el solicitudes manager.
	 *
	 * @return el solicitudes manager
	 */
	public SolicitudManager getSolicitudesManager() {
		return solicitudesManager;
	}

	/**
	 * Establece el solicitudes manager.
	 *
	 * @param solicitudesManager el nuevo solicitudes manager
	 */
	public void setSolicitudesManager(SolicitudManager solicitudesManager) {
		this.solicitudesManager = solicitudesManager;
	}


	/**
	 * Obtiene el logger.
	 *
	 * @return el logger
	 */
	public static Logger getLogger() {
		return logger;
	}
}
