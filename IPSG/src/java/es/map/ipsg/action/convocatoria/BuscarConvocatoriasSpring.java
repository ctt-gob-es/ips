package es.map.ipsg.action.convocatoria;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.security.autentica.authentication.AutenticaAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import es.map.ips.common.exception.BusinessException;
import es.map.ips.common.model.OrderType;
import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.CentroGestorQuery;
import es.map.ips.model.ConvocatoriaQuery;
import es.map.ips.model.CuerpoEscalaQuery;
import es.map.ips.model.EstadoConvocatoriaQuery;
import es.map.ips.model.FormaAccesoQuery;
import es.map.ips.model.GrupoQuery;
import es.map.ips.model.ModeloQuery;
import es.map.ips.model.Usuario;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.bean.CentroGestorBean;
import es.map.ipsg.bean.ConvocatoriasBean;
import es.map.ipsg.bean.EstadoConvocatoriaBean;
import es.map.ipsg.bean.FormaAccesoBean;
import es.map.ipsg.bean.GrupoBean;
import es.map.ipsg.bean.ModeloBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.form.BuscaConvocatoriasForm;
import es.map.ipsg.manager.CentroGestorManager;
import es.map.ipsg.manager.ConvocatoriasManager;
import es.map.ipsg.manager.CuerpoEscalaManager;
import es.map.ipsg.manager.EstadoConvocatoriaManager;
import es.map.ipsg.manager.FormaAccesoManager;
import es.map.ipsg.manager.GrupoManager;
import es.map.ipsg.manager.ModeloManager;
import es.map.ipsg.manager.UsuarioCentrogestorManager;
import es.map.ipsg.manager.UsuarioManager;
import es.map.ipsg.util.Constantes;


/**
 * El Class BuscarConvocatoriasSpring.
 */
@Component 
//@Scope ("prototype")
public class BuscarConvocatoriasSpring extends AbstractSpring {

	/** La constante MESSAGE_RESOUCE. */
	private static final String MESSAGE_RESOUCE = "MessageResources";

	/** La constante RESOURCE_MESSAGE. */
	private static final ResourceBundle RESOURCE_MESSAGE = ResourceBundle.getBundle(MESSAGE_RESOUCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(BuscarConvocatoriasSpring.class);
	
	/** La constante STRING_ACCION. */
	private static final String STRING_ACCION = "accion";
	
	/** La constante STRING_BUSCAR. */
	private static final String STRING_BUSCAR = "Buscar";
	
	/** La constante STRING_COMPROBARFILTROS2. */
	private static final String STRING_COMPROBARFILTROS2 = "Error comprobarFiltros2():";
	
	/** La constante STRING_COMPROBARFILTROS1. */
	private static final String STRING_COMPROBARFILTROS1 = "Error comprobarFiltros1():";
	
	/** el properties. */
	private static Properties properties;

	/** el convocatorias manager. */
	//Declaracion de manager
	private ConvocatoriasManager convocatoriasManager;
	
	/** el cuerpo escala manager. */
	private CuerpoEscalaManager cuerpoEscalaManager;
	
	/** el centro gestor manager. */
	private CentroGestorManager centroGestorManager;
	
	/** el forma acceso manager. */
	private FormaAccesoManager formaAccesoManager;
	
	/** el estado convocatoria manager. */
	private EstadoConvocatoriaManager estadoConvocatoriaManager;
	
	/** el usuario manager. */
	private UsuarioManager usuarioManager;
	
	/** el grupo manager. */
	private GrupoManager grupoManager;
	
	/** el modelo manager. */
	private ModeloManager modeloManager;
	
	/** el usuario centro gestor manager. */
	private UsuarioCentrogestorManager usuarioCentrogestorManager;
	
	/**
	 * Crea una nueva buscar convocatorias spring.
	 */
	public BuscarConvocatoriasSpring() {
		try {
			setConvocatoriasManager((ConvocatoriasManager) getBean("convocatoriaManager"));
			setCuerpoEscalaManager((CuerpoEscalaManager) getBean("cuerposEscalaManager"));
			setCentroGestorManager((CentroGestorManager) getBean("centrosGestoresManager"));
			setGrupoManager((GrupoManager) getBean("grupoManager"));
			setFormaAccesoManager((FormaAccesoManager) getBean("formasAccesoManager"));
			setEstadoConvocatoriaManager((EstadoConvocatoriaManager) getBean("estadosConvocatoriaManager"));
			setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
			setModeloManager((ModeloManager)getBean("modelosManager"));
			setUsuarioCentrogestorManager((UsuarioCentrogestorManager) getBean("usuarioCentrogestorManager"));
			properties = (Properties) getBean("propertiesBean");
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error BuscarConvocatoriasSpring(): ",e);
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	public String doExecute(SpringForm form) throws Exception {
		getLogger().debug("BuscarConvocatoriasSpring -start");
		try{
			//*********** METO EL PARAMETRO QUE ME INDICA EN QUE MENU ESTOY **********
			String menu_convocatoria = RESOURCE_MESSAGE.getString("field.menu.convocatorias");
			this.getRequest().getSession().setAttribute("pagActiva", menu_convocatoria);
			String subMenu_convocatoria = RESOURCE_MESSAGE.getString("field.menuLateral.convocatorias.buscar");
			this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_convocatoria);
		//****************************************************************** 
		String busqueda = "";
		String numReg="";
		//Cojo el lugar de la llamada
		
		String menu = this.getRequest().getParameter("menu");
		if(menu != null && menu.equals("S")){
			limpiarSesion();
		}
		
		//Coger el form del jsp
		try{
			busqueda = this.getRequest().getParameter(STRING_ACCION);
			numReg = this.getRequest().getParameter("numRegistro");
			logger.info("NumRegistros: "+numReg);
		}catch(Exception e){
			logger.error("Error BuscarConvocatoriasSpring() - numRegistros: "+ numReg,e);
		}
		if(this.getRequest().getAttribute(STRING_ACCION) != null && !"".equals(this.getRequest().getAttribute(STRING_ACCION))){
			busqueda = (String) this.getRequest().getAttribute(STRING_ACCION);
		}
		logger.info("BusquedaAtributo: "+busqueda);
		BuscaConvocatoriasForm formulario = null;
		formulario = (BuscaConvocatoriasForm) form;
		String cambios = this.getRequest().getParameter("cambios");
		//Creamos la Query
		ConvocatoriaQuery convocatoriaQuery;
		//Si ha seleccionado un campo de ordenacion se le asigna el atributo de ordenacion
		String campo = "";
		int aux = 0;
		if(formulario.getCampo()!= null){
			try{
				aux = Integer.parseInt(formulario.getCampo());
				campo=cargarCampos(formulario.getCampo());
				formulario.setCampo(campo);
			}catch(Exception e){}			
		}
		
		/*INI-TRA042*/
		//Miro cual es el perfil del usuario
		//Tomamos el usuario que se ha logueado
		Integer perfilUsuario ;
		String sUsernameLogin = recuperarUsuario();
		if  (sUsernameLogin.equals("error")) {
			return sUsernameLogin;
		}
		UsuarioQuery usuarioQuery = new UsuarioQuery();
		usuarioQuery.setLogin(sUsernameLogin); //Le pasamos el login del que está logueado
		UsuarioBean usuarioBean = usuarioManager.buscarUsuario(usuarioQuery);
		Usuario usuario = usuarioManager.toUsuario(usuarioBean);
		perfilUsuario=usuario.getPerfil().getId();
		
		String sPerfilUsuario = comprobarPerfilUsuario(usuarioBean);
		List<CentroGestorBean> listaCentrosGestores = new ArrayList<CentroGestorBean>();

		if(sPerfilUsuario.equalsIgnoreCase(Constantes.ROL_GESTOR))
		{
			listaCentrosGestores = usuarioCentrogestorManager.buscarCentrosGestoresByIdUsuario(usuario.getId());

		}
		
		//Cargar los combos del jsp
		cargarCombos(listaCentrosGestores);
		/*FIN-TRA042*/
		
		//Indicamos si viene desde el enlace del menú para mostrar los resultados de búsqueda o no 
		//y guardar la descripción para el formulario de búsqueda
		String sVieneMenu = this.getRequest().getParameter("menu"); 
		if (sVieneMenu != null && sVieneMenu.equals("S"))
		{
			//Poner todos los campos del formulario en blanco			
			formulario.setCentroGestor("");			
			formulario.setCuerpoEscala("");
			formulario.setEstadoConvocatoria("");
			formulario.setEjercicio("");
			formulario.setGrupo("");
			formulario.setFormaAcceso("");
			formulario.setDsCentroGestor("");
			formulario.setDsCuerpoEscala("");
			formulario.setIdModelo(0);

		} //Si viene a null es que viene de las demás páginas que no son del menú principal
		//FIN Limpiado de formulario y muestra de resultados.
		
		
		//Llama a la funcion para crear la Query pasandole el formulario
		
		String paginaActual = this.getRequest().getParameter("paginaActual");
		String paginaTotal = this.getRequest().getParameter("paginasTotales");
		
		if(this.getRequest().getParameter("llamada") != null){
			busqueda = this.getRequest().getParameter("llamada");
		}
		
		checkRolUsuario(formulario,listaCentrosGestores);
		
		paginaActual = paginaActual(paginaActual,paginaTotal,formulario,busqueda);
		

		convocatoriaQuery=crearQueryConvocatoria(formulario,paginaActual,busqueda,numReg);
			
	
		
		//Coger la lista de convocatorias a mostrar
	
		List<ConvocatoriasBean> convocatorias = null;
		if(STRING_BUSCAR.equals(busqueda) || "Ordenar".equals(busqueda) || "Paginar".equals(busqueda) || "Aprobar Convocatorias".equals(busqueda) || "Desactivar Convocatorias".equals(busqueda)){ 
			convocatorias = convocatoriasManager.buscarConvocatoriasAll(convocatoriaQuery);
			cambios = "Correcto";
		}
		int pagTotal = 0;
		int numPaginas=0;
		if(formulario.getNumRegistro() == null && numReg == null){
			//Para el primer paso para el action, se establece que los registros por pagina son 10
			formulario.setNumRegistro(properties.getProperty("conf.numRegistrosListados"));
		}
		int numRegistros = 0;
		numRegistros = numerosRegistros(numReg,numRegistros,formulario);
		//Recorrer la lista, para comprobar los estados de cada convocatoria para activar los enlaces
		numPaginas = comprobarEstadosConvocatorias(convocatorias,pagTotal,numPaginas,numRegistros);
		
		logger.warn("Cambios: "+cambios);
		
		//Pasar la convocatoria y la pagina al jsp
		
		
		setRequestAttribute("perfilUsuario", perfilUsuario);
		setRequestAttribute("convocatorias", convocatorias);
		setRequestAttribute("paginaActual", paginaActual);
		setRequestAttribute("paginasTotales", numPaginas);
		setRequestAttribute(STRING_ACCION, busqueda);
		setRequestAttribute("cambios", cambios);
		
		
		getLogger().debug("BuscarConvocatoriasSpring -end");
		formulario.setCampo(String.valueOf(aux));
		return "success";
			
		}catch(Exception e){
			logger.warn(e);
			this.getRequest().setAttribute("descripcionError", e.getMessage());	
			logger.error("Error BuscarConvocatoriasSpring() - doExecute: ",e);
			return "nosuccess";
		}	
	}
	
	/**
	 * Recuperar usuario.
	 *
	 * @return el string
	 */
	private String recuperarUsuario() {
		String sUsernameLogin = "";
		try{
			AutenticaAuthenticationToken user = (AutenticaAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
			// Obtenemos el usuario de base de datos a partir del identificador obtenido de autentica
			UsuarioQuery usuarioQuery = new UsuarioQuery();
			usuarioQuery.setNif(user.getIdentificador());
			UsuarioBean usuarioBean = usuarioManager.buscarUsuario(usuarioQuery);
			
			sUsernameLogin=usuarioBean.getLogin();
			logger.info("sUsernameLogin: "+sUsernameLogin);
			return sUsernameLogin;
		}catch(Exception e){
			logger.error("Error BuscarConvocatoriasSpring() - recuperarUsuarioSesion - sUsernameLogin : " + sUsernameLogin ,e);
			new BusinessException("exception.recuperarUsuarioSesion");
			return "error";
		}
	}
	
	/**
	 * Numeros registros.
	 *
	 * @param numReg el num reg
	 * @param numRegistros el num registros
	 * @param formulario el formulario
	 * @return el int
	 */
	private int numerosRegistros(String numReg, int numRegistros, BuscaConvocatoriasForm formulario) {
		if(numReg != null && !"".equals(numReg)){
			try{
			numRegistros = Integer.parseInt(numReg);
			}catch(Exception e){
				logger.error(e);
				e.printStackTrace();
				numRegistros = Integer.parseInt(Constantes.NUMERO_REGISTROS_PAGINA);
			}
		}else{
			try{
			numRegistros = Integer.parseInt(formulario.getNumRegistro());
			}catch(Exception e){
				logger.error(e);
				e.printStackTrace();
				numRegistros = Integer.parseInt(Constantes.NUMERO_REGISTROS_PAGINA);
			}
		}
		if(numRegistros > 50){
			numRegistros = 50;
			formulario.setNumRegistro(String.valueOf(numRegistros));
		}else{
			formulario.setNumRegistro(String.valueOf(numRegistros));
		}
		return numRegistros;
	}
	
	/**
	 * Pagina actual.
	 *
	 * @param paginaActual el pagina actual
	 * @param paginaTotal el pagina total
	 * @param formulario el formulario
	 * @param busqueda el busqueda
	 * @return el string
	 */
	private String paginaActual(String paginaActual, String paginaTotal, BuscaConvocatoriasForm formulario, String busqueda) {
		if(paginaActual == null){
			paginaActual = formulario.getPaginaActual();
		}
		
		if(formulario.isPulsaIr() == true)
		{
			paginaActual = formulario.getNumeroPaginaIr().toString();
			logger.info("PaginaActualIr: "+ paginaActual);
			formulario.setPulsaIr(false);
			formulario.setNumeroPaginaIr(null);
		}
		else
		{
			formulario.setNumeroPaginaIr(null);
			formulario.setPulsaIr(false);
		}
		
		if(paginaTotal == null){
			paginaTotal = formulario.getPaginasTotales();
		}
		
	
		logger.info("PaginaActual: "+ paginaActual);
		logger.info("PaginaTotal: "+paginaTotal);
		if(paginaActual != null && !"".equals(paginaActual) && paginaTotal != null && !"".equals(paginaTotal)){
			int pagActu = Integer.parseInt(paginaActual);		
			int pagTotales = Integer.parseInt(paginaTotal);

			if(pagActu > pagTotales){
				paginaActual = String.valueOf(pagTotales);
			}
		}
		
		if("0".equals(paginaActual) || paginaActual == null || "".equals(paginaActual) || STRING_BUSCAR.equals(busqueda)){
			paginaActual = Constantes.PAGINA_PRINCIPAL;
			formulario.setPaginaActual(paginaActual);
		}
		
		if(paginaActual==null || "".equals(paginaActual))
			paginaActual="1";
		
		return paginaActual;
	}
	
	/**
	 * Comprobar estados convocatorias.
	 *
	 * @param convocatorias el convocatorias
	 * @param pagTotal el pag total
	 * @param numPaginas el num paginas
	 * @param numRegistros el num registros
	 * @return el int
	 */
	private int comprobarEstadosConvocatorias(List<ConvocatoriasBean> convocatorias, int pagTotal, int numPaginas, int numRegistros) {
		if(convocatorias != null && convocatorias.size()!= 0){
			pagTotal= convocatorias.get(0).getNumTotal();
			numPaginas = pagTotal / numRegistros;
			int resto = pagTotal % numRegistros;
			if(resto > 0){
				numPaginas++;
			}
			if(convocatorias.size()>numRegistros){
				convocatorias.remove(convocatorias.size()-1);
			}
			for(int i=0;i<convocatorias.size();i++){
				convocatorias.get(i).setEliminar(false);
				convocatorias.get(i).setModificar(false);
				convocatorias.get(i).setDetalle(false);
				convocatorias.get(i).setDetalle(true);
				if(RESOURCE_MESSAGE.getString("field.estado.convocatoria.Configuracion").equals(convocatorias.get(i).getEstadoConvocatoria()) 
						|| RESOURCE_MESSAGE.getString("field.estado.convocatoria.Desactivado").equals(convocatorias.get(i).getEstadoConvocatoria())){
					convocatorias.get(i).setEliminar(true);
					convocatorias.get(i).setModificar(true);
				}
			}
		}
		return numPaginas;
	}

	/**
	 * Cargar campos.
	 *
	 * @param campo el campo
	 * @return el string
	 */
	private String cargarCampos(String campo) {
		String auxCampo=null;
		int codCampo = 0;
		try{
			codCampo = Integer.parseInt(campo);
		}catch(Exception e){
			logger.error("Error BuscarConvocatoriasSpring() - cargarCampos - campo: " +codCampo,e);
		}
			if (campo != null && !"".equals(campo) && !campo.equals("null")){
				
					switch(codCampo){
					//Los campos de ordenacion del jsp
						case 1:auxCampo = ConvocatoriaQuery.ID;break;
						case 2:auxCampo = ConvocatoriaQuery.EJERCICIO;break;
						case 3:auxCampo = ConvocatoriaQuery.CUERPOESCALA;break;
						case 4:auxCampo = ConvocatoriaQuery.FECHAINICIO;break;
						case 5:auxCampo = ConvocatoriaQuery.FECHAFIN;break;
						case 6:auxCampo = ConvocatoriaQuery.FORMAACCESO;break;
						case 7:auxCampo = ConvocatoriaQuery.ESTADOCONVOCATORIA;break;
						case 8:auxCampo = CuerpoEscalaQuery.CENTROGESTOR;break;
						default:break;
					}
				
			}
		return auxCampo;
	}

	/**
	 * Crear query convocatoria.
	 *
	 * @param formulario el formulario
	 * @param paginaActual el pagina actual
	 * @param busqueda el busqueda
	 * @param numReg el num reg
	 * @return el convocatoria query
	 */
	private ConvocatoriaQuery crearQueryConvocatoria(BuscaConvocatoriasForm formulario, String paginaActual, String busqueda, String numReg) {
		//Recoger los datos del formulario
		String idConvocatoria = formulario.getIdConvocatoria();
		String ejercicio = formulario.getEjercicio();
		String centroGestor = formulario.getCentroGestor();
		String cuerpoEscala = formulario.getCuerpoEscala();
		String estado = formulario.getEstadoConvocatoria();
		formulario.getFechaFin();
		formulario.getFechaInicio();
		String formaAcceso = formulario.getFormaAcceso();
		String grupo = formulario.getGrupo();
		String campo = formulario.getCampo();
		String direccion = formulario.getDireccion();
		int idModelo = formulario.getIdModelo();
		
		int numRegistros = 0;
		
		numRegistros = crearQueryNumRegistros(numRegistros,formulario,numReg);
		
		//Si la pagina actual no esta definida, se establece a 1
		if("0".equals(paginaActual) || paginaActual == null || "".equals(paginaActual) || STRING_BUSCAR.equals(busqueda)){
			paginaActual = "1";
			formulario.setPaginaActual(paginaActual);
		}
		//Calcular el numero de lineas de cada paginacion
		int tamanyoPaginacionReal = numRegistros;
		int tamanyoPaginacion = tamanyoPaginacionReal+1;
		int numPagina = Integer.parseInt(paginaActual);
		int primerRegistro = (numPagina*tamanyoPaginacionReal)-tamanyoPaginacionReal;
		
		ConvocatoriaQuery convocatoriaQuery = new ConvocatoriaQuery();
		CuerpoEscalaQuery cuerpoEscalaQuery = new CuerpoEscalaQuery();
		CentroGestorQuery centroGestorQuery = new CentroGestorQuery();
		FormaAccesoQuery formaAccesoQuery = new FormaAccesoQuery();
		EstadoConvocatoriaQuery estadoConvocatoriaQuery = new EstadoConvocatoriaQuery();
		
		
		
		convocatoriaQuery.setMaxResults(tamanyoPaginacion);
		convocatoriaQuery.setFirstResult(primerRegistro);
			
		convocatoriaQuery.setCalculateNumResults(true);
		//Comprobar los filtros para realizar la busqueda
			
		/*INI-TRA042*/	
		comprobarFiltros1(convocatoriaQuery, idConvocatoria, cuerpoEscalaQuery, cuerpoEscala, centroGestorQuery, centroGestor, formulario.getListaCentrosGestores() , ejercicio);
		/*FIN-TRA042*/
		comprobarFiltros2(formaAcceso,formaAccesoQuery,grupo,cuerpoEscalaQuery,estado,estadoConvocatoriaQuery);
		
		
		// Añadimos la ordenación de los campos
		if(campo != null && !"0".equals(campo) && !"".equals(campo)) {
			// Obtenemos si vamos a ordenar ascendentemente o descendentemente
			OrderType orden = ("up".equals(direccion) == true ? OrderType.ASC : OrderType.DESC);
			
			//Si el la ordenacion se hace por un campo de otra tabla se asigna el orden a la query correspondiente
			if (ConvocatoriaQuery.CUERPOESCALA.equals(campo)) {
				cuerpoEscalaQuery.addOrder(CuerpoEscalaQuery.DESCRIPCION, orden);
			} else if (ConvocatoriaQuery.FORMAACCESO.equals(campo)) {
				formaAccesoQuery.addOrder(FormaAccesoQuery.DESCRIPCION, orden);
			} else if (ConvocatoriaQuery.ESTADOCONVOCATORIA.equals(campo)){
				estadoConvocatoriaQuery.addOrder(EstadoConvocatoriaQuery.DESCRIPCION, orden);
			} else if (CuerpoEscalaQuery.CENTROGESTOR.equals(campo)) {
				centroGestorQuery.addOrder(CentroGestorQuery.SIGLAS, orden);
			} else {			
				convocatoriaQuery.addOrder(campo, orden);
			}
			
		}
		
		if(cuerpoEscalaQuery.getCentroGestor() != null){
			logger.info("CentroGestor: "+cuerpoEscalaQuery.getCentroGestor().getId());
		}
		
		ModeloQuery modeloQuery = new ModeloQuery();
		if(idModelo>0){
			modeloQuery.setIdModelo(idModelo);	
		}
		
		cuerpoEscalaQuery.setCentroGestor(centroGestorQuery);
		convocatoriaQuery.setFormaAcceso(formaAccesoQuery);
		convocatoriaQuery.setCuerpoEscala(cuerpoEscalaQuery);
		convocatoriaQuery.setEstadoConvocatoria(estadoConvocatoriaQuery);
		convocatoriaQuery.setJoin_cuerpoEscala(true);
		convocatoriaQuery.setModelo(modeloQuery);
	
		//Devulelve la convocatoria con los filtros
		return convocatoriaQuery;
		
	}
	
	/**
	 * Comprobar filtros 2.
	 *
	 * @param formaAcceso el forma acceso
	 * @param formaAccesoQuery el forma acceso query
	 * @param grupo el grupo
	 * @param cuerpoEscalaQuery el cuerpo escala query
	 * @param estado el estado
	 * @param estadoConvocatoriaQuery el estado convocatoria query
	 */
	private void comprobarFiltros2(String formaAcceso, FormaAccesoQuery formaAccesoQuery, String grupo, CuerpoEscalaQuery cuerpoEscalaQuery, String estado, EstadoConvocatoriaQuery estadoConvocatoriaQuery) {
		if(formaAcceso != null && !"0".equals(formaAcceso) && !"".equals(formaAcceso)){			
			try{
			int codFormaAcceso = Integer.parseInt(formaAcceso);
			formaAccesoQuery.setId(codFormaAcceso);
			}catch(Exception e){
				logger.error(STRING_COMPROBARFILTROS2,e );
			}
		}
		
		if(grupo!= null && !"0".equals(grupo)&& !"".equals(grupo)){
			GrupoQuery grupoQuery = new GrupoQuery();
			try{
			int codGrupo = Integer.parseInt(grupo);
			grupoQuery.setId(codGrupo);
			cuerpoEscalaQuery.setGrupo(grupoQuery);			
			}catch(Exception e){
				logger.error(STRING_COMPROBARFILTROS2,e );
			}
		}
		
		
		if(estado != null && !"0".equals(estado) && !"".equals(estado)){			
			try{
			int codEstado = Integer.parseInt(estado);
			estadoConvocatoriaQuery.setId(codEstado);
			}catch(Exception e){
				logger.error(STRING_COMPROBARFILTROS2,e );
			}
		}
	}
	
	/**
	 * Comprobar filtros 1.
	 *
	 * @param convocatoriaQuery el convocatoria query
	 * @param idConvocatoria el id convocatoria
	 * @param cuerpoEscalaQuery el cuerpo escala query
	 * @param cuerpoEscala el cuerpo escala
	 * @param centroGestorQuery el centro gestor query
	 * @param centroGestor 
	 * @param listaCentrosGestores 
	 * @param centroGestor el centro gestor
	 * @param ejercicio el ejercicio
	 */
 	/*INI-TRA042*/
	private void comprobarFiltros1(ConvocatoriaQuery convocatoriaQuery, String idConvocatoria, CuerpoEscalaQuery cuerpoEscalaQuery, String cuerpoEscala, CentroGestorQuery centroGestorQuery, String centroGestor, List<CentroGestorBean> listaCentrosGestores, String ejercicio) {
		
		if(idConvocatoria!= null && !"0".equals(idConvocatoria) && !"".equals(idConvocatoria)){
			try{
			Long codId = Long.parseLong(idConvocatoria);
			convocatoriaQuery.setId(codId);
			}catch(Exception e){
				logger.error(STRING_COMPROBARFILTROS1,e );
			}
		}
		
		if(cuerpoEscala != null && !"0".equals(cuerpoEscala) && !"".equals(cuerpoEscala)){
			try{
			int codCuerpoEscala = Integer.parseInt(cuerpoEscala);
			cuerpoEscalaQuery.setId(codCuerpoEscala);		
			}catch(Exception e){
				logger.error(STRING_COMPROBARFILTROS1,e );
			}
		}
		
		if( centroGestor!= null && !"0".equals(centroGestor) && !"".equals(centroGestor)){
			try{
				int codCentro = Integer.parseInt(centroGestor);
				centroGestorQuery.setId(codCentro);
			}catch(Exception e){
				logger.error(STRING_COMPROBARFILTROS1,e );
			}
		} else if(listaCentrosGestores != null && listaCentrosGestores.size()>0) {
			try{
				for(CentroGestorBean cg: listaCentrosGestores) {
					centroGestorQuery.addIdCentroGestorIn(cg.getId());
				}
			}catch(Exception e){
				logger.error(STRING_COMPROBARFILTROS1,e );
			}
		}
		
		if(ejercicio != null && !"0".equals(ejercicio) && !"".equals(ejercicio)){
			convocatoriaQuery.setEjercicio(ejercicio);
		}
	}
	/*FIN-TRA042*/
	
	/**
	 * Crear query num registros.
	 *
	 * @param numRegistros el num registros
	 * @param formulario el formulario
	 * @param numReg el num reg
	 * @return el int
	 */
	private int crearQueryNumRegistros(int numRegistros, BuscaConvocatoriasForm formulario, String numReg) {
	
		if(formulario.getNumRegistro() == null && numReg == null){
			//Para el primer paso para el action, se establece que los registros por pagina son 5
			formulario.setNumRegistro(Constantes.NUMERO_REGISTROS_PAGINA);
			numRegistros = Integer.parseInt(formulario.getNumRegistro());
		}
		if(StringUtils.isNotEmpty(numReg)){
			try{
			numRegistros = Integer.parseInt(numReg);
			}catch(Exception e){
				logger.error("Error BuscarConvocatoriasSpring() -  numRegistros: " +numRegistros,e);
				numRegistros = Integer.parseInt(Constantes.NUMERO_REGISTROS_PAGINA);
			}
		}else{
			try{
			numRegistros = Integer.parseInt(formulario.getNumRegistro());
			}catch(Exception e){
				logger.error("Error BuscarConvocatoriasSpring() -  numRegistros: " +numRegistros,e);
				numRegistros = Integer.parseInt(Constantes.NUMERO_REGISTROS_PAGINA);
			}
		}
		if(numRegistros > 50){
			numRegistros = 50;
			formulario.setNumRegistro(String.valueOf(numRegistros));
		}else{
			formulario.setNumRegistro(String.valueOf(numRegistros));
		}
		return numRegistros;
	}

	/**
	 * Cargar combos.
	 * @param listaCentrosGestores 
	 */
	/*INI-TRA042*/
	public void cargarCombos(List<CentroGestorBean> listaCentrosGestores) {
		GrupoQuery grupoQuery = new GrupoQuery();
		grupoQuery.setVisible(Constantes.VISIBILIDAD_SI);
		List<GrupoBean> grupo;
		grupo = grupoManager.buscarGrupoCombo(grupoQuery);
		FormaAccesoQuery formaAccesoQuery = new FormaAccesoQuery();
		List<FormaAccesoBean> formaAcceso;
		formaAcceso = formaAccesoManager.buscarFormaAccesoComboVisibilidad(formaAccesoQuery);
		EstadoConvocatoriaQuery estadoConvocatoriaQuery = new EstadoConvocatoriaQuery();
		List<EstadoConvocatoriaBean> estadoConvocatoria;
		estadoConvocatoria = estadoConvocatoriaManager.buscarEstadoConvocatoriaCombo(estadoConvocatoriaQuery);
		List<EstadoConvocatoriaBean> estadoConvocatoriaOrdenadas = this.ordenarEstadosConvocatorias(estadoConvocatoria) ;
		
		List<ModeloBean> listaModelosBean;
		listaModelosBean = modeloManager.buscarModeloComboTodos();
		
		setRequestAttribute("grupos", grupo);
		setRequestAttribute("formasAcceso", formaAcceso);
		setRequestAttribute("estados", estadoConvocatoriaOrdenadas);
		setRequestAttribute("listaModelosBean", listaModelosBean);
		setRequestAttribute("listaCentrosGestores", listaCentrosGestores);		
	}
	/*FIN-TRA042*/
	/**
	 * Check rol usuario.
	 *
	 * @param form el form
	 * @param listaCentrosGestores 
	 */
	/*INI-TRA042*/
	public void checkRolUsuario(BuscaConvocatoriasForm form, List<CentroGestorBean> listaCentrosGestores){
		AutenticaAuthenticationToken user = (AutenticaAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
		// Obtenemos el usuario de base de datos a partir del identificador obtenido de autentica
		UsuarioQuery usuarioQuery = new UsuarioQuery();
		usuarioQuery.setNif(user.getIdentificador());
		UsuarioBean usuarioBean = usuarioManager.buscarUsuario(usuarioQuery);

		logger.info("Perfil usuario: "+usuarioBean.getIdPerfil());
		setRequestAttribute("rol", usuarioBean.getIdPerfil());
		form.setListaCentrosGestores(listaCentrosGestores);
	}
	/*FIN-TRA042*/
	
	/**
	 * Ordenar estados convocatorias.
	 *
	 * @param estadoConvocatoria el estado convocatoria
	 * @return el list
	 */
	private List<EstadoConvocatoriaBean> ordenarEstadosConvocatorias (List<EstadoConvocatoriaBean> estadoConvocatoria) {
		
		List <EstadoConvocatoriaBean> listaOrdenada = new ArrayList<EstadoConvocatoriaBean>();
		
		for (int i=0; i<estadoConvocatoria.size(); i++) {
			if (estadoConvocatoria.get(i).getDescripcion().equals(Constantes.CONVOCATORIA_ESTADO_CONFIGURACION)){
				listaOrdenada.add(estadoConvocatoria.get(i));
				break;
			}
		}
		for (int i=0; i<estadoConvocatoria.size(); i++) {
			if (estadoConvocatoria.get(i).getDescripcion().equals(Constantes.CONVOCATORIA_ESTADO_APROBADO)){
				listaOrdenada.add(estadoConvocatoria.get(i));
				break;
			}
		}
		for (int i=0; i<estadoConvocatoria.size(); i++) {
			if (estadoConvocatoria.get(i).getDescripcion().equals(Constantes.CONVOCATORIA_ESTADO_PUBLICADO)){
				listaOrdenada.add(estadoConvocatoria.get(i));
				break;
			}
		}
		for (int i=0; i<estadoConvocatoria.size(); i++) {
			if (estadoConvocatoria.get(i).getDescripcion().equals(Constantes.CONVOCATORIA_ESTADO_FINALIZADA)){//finalizada
				listaOrdenada.add(estadoConvocatoria.get(i));
				break;
			}
		}
		for (int i=0; i<estadoConvocatoria.size(); i++) {
			if (estadoConvocatoria.get(i).getDescripcion().equals(Constantes.CONVOCATORIA_ESTADO_CERRADA)){//cerrada
				listaOrdenada.add(estadoConvocatoria.get(i));
				break;
			}
		}
		for (int i=0; i<estadoConvocatoria.size(); i++) {
			if (estadoConvocatoria.get(i).getDescripcion().equals(Constantes.CONVOCATORIA_ESTADO_SUBSANACION)){
				listaOrdenada.add(estadoConvocatoria.get(i));
				break;
			}
		}
		ordenarEstadosConvocatorias2(estadoConvocatoria,listaOrdenada);
	
		
		return listaOrdenada;
	}
	
	/**
	 * Ordenar estados convocatorias 2.
	 *
	 * @param estadoConvocatoria el estado convocatoria
	 * @param listaOrdenada el lista ordenada
	 * @return el list
	 */
	private List<EstadoConvocatoriaBean> ordenarEstadosConvocatorias2(List<EstadoConvocatoriaBean> estadoConvocatoria,List <EstadoConvocatoriaBean> listaOrdenada) {
		for (int i=0; i<estadoConvocatoria.size(); i++) {
			if (estadoConvocatoria.get(i).getDescripcion().equals(Constantes.CONVOCATORIA_ESTADO_DESACTIVADO)){
				listaOrdenada.add(estadoConvocatoria.get(i));
				break;
			}
		}
		for (int i=0; i<estadoConvocatoria.size(); i++) {
			if (estadoConvocatoria.get(i).getDescripcion().equals(Constantes.CONVOCATORIA_ESTADO_ELIMINADO)){
				listaOrdenada.add(estadoConvocatoria.get(i));
				break;
			}
		}
		return listaOrdenada;
	}
	
	/**
	 * Comprobar perfil usuario.
	 *
	 * @param usuarioBean2 el id usuario
	 * @return el string
	 */
	/*INI-TRA042*/
	private String comprobarPerfilUsuario(UsuarioBean usuarioBean) {
		String sPerfil = "";
		
		if(usuarioBean.getIdPerfil() != null &&   usuarioBean.getIdPerfil().toString().equals(Constantes.PERFIL_CONSULTOR))
		{
			sPerfil = Constantes.ROL_CONSULTOR;
		}
		if(usuarioBean.getIdPerfil() != null &&   usuarioBean.getIdPerfil().toString().equals(Constantes.PERFIL_SOPORTE))
		{
			sPerfil = Constantes.ROL_SOPORTE;
		}
		if(usuarioBean.getIdPerfil() != null && usuarioBean.getIdPerfil().toString().equals(Constantes.PERFIL_GESTOR))
		{
			sPerfil = Constantes.ROL_GESTOR;
		}
		if(usuarioBean.getIdPerfil() != null && usuarioBean.getIdPerfil().toString().equals(Constantes.PERFIL_ADMINISTRADOR))
		{
			sPerfil = Constantes.ROL_ADMINISTRADOR;
		}

		return sPerfil;

	}
	/*FIN-TRA042*/
	
	/**
	 * Obtiene el convocatorias manager.
	 *
	 * @return el convocatorias manager
	 */
	public ConvocatoriasManager getConvocatoriasManager() {
		return convocatoriasManager;
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
	 * Obtiene el cuerpo escala manager.
	 *
	 * @return el cuerpo escala manager
	 */
	public CuerpoEscalaManager getCuerpoEscalaManager() {
		return cuerpoEscalaManager;
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
	 * Obtiene el centro gestor manager.
	 *
	 * @return el centro gestor manager
	 */
	public CentroGestorManager getCentroGestorManager() {
		return centroGestorManager;
	}

	/**
	 * Establece el centro gestor manager.
	 *
	 * @param centroGestorManager el nuevo centro gestor manager
	 */
	public void setCentroGestorManager(CentroGestorManager centroGestorManager) {
		this.centroGestorManager = centroGestorManager;
	}

	/**
	 * Obtiene el grupo manager.
	 *
	 * @return el grupo manager
	 */
	public GrupoManager getGrupoManager() {
		return grupoManager;
	}

	/**
	 * Establece el grupo manager.
	 *
	 * @param grupoManager el nuevo grupo manager
	 */
	public void setGrupoManager(GrupoManager grupoManager) {
		this.grupoManager = grupoManager;
	}

	/**
	 * Obtiene el forma acceso manager.
	 *
	 * @return el forma acceso manager
	 */
	public FormaAccesoManager getFormaAccesoManager() {
		return formaAccesoManager;
	}

	/**
	 * Establece el forma acceso manager.
	 *
	 * @param formaAccesoManager el nuevo forma acceso manager
	 */
	public void setFormaAccesoManager(FormaAccesoManager formaAccesoManager) {
		this.formaAccesoManager = formaAccesoManager;
	}

	/**
	 * Obtiene el estado convocatoria manager.
	 *
	 * @return el estado convocatoria manager
	 */
	public EstadoConvocatoriaManager getEstadoConvocatoriaManager() {
		return estadoConvocatoriaManager;
	}

	/**
	 * Establece el estado convocatoria manager.
	 *
	 * @param estadoConvocatoriaManager el nuevo estado convocatoria manager
	 */
	public void setEstadoConvocatoriaManager(
			EstadoConvocatoriaManager estadoConvocatoriaManager) {
		this.estadoConvocatoriaManager = estadoConvocatoriaManager;
	}

	/**
	 * Obtiene el usuario manager.
	 *
	 * @return el usuario manager
	 */
	public UsuarioManager getUsuarioManager() {
		return usuarioManager;
	}

	/**
	 * Establece el usuario manager.
	 *
	 * @param usuarioManager el nuevo usuario manager
	 */
	public void setUsuarioManager(UsuarioManager usuarioManager) {
		this.usuarioManager = usuarioManager;
	}

	/**
	 * Obtiene el modelo manager.
	 *
	 * @return el modelo manager
	 */
	public ModeloManager getModeloManager() {
		return modeloManager;
	}

	/**
	 * Establece el modelo manager.
	 *
	 * @param modeloManager el nuevo modelo manager
	 */
	public void setModeloManager(ModeloManager modeloManager) {
		this.modeloManager = modeloManager;
	}

	/**
	 * Obtiene el logger.
	 *
	 * @return el logger
	 */
	public static Logger getLogger() {
		return logger;
	}
	
	/**
	 * Limpiar sesion.
	 */
	private void limpiarSesion() {
		this.getSession().removeAttribute("org.apache.spring.ERROR");
	}

	/*INI-TRA042*/
	/**
	 * @return the usuarioCentrogestorManager
	 */
	public UsuarioCentrogestorManager getUsuarioCentrogestorManager() {
		return usuarioCentrogestorManager;
	}

	/**
	 * @param usuarioCentrogestorManager the usuarioCentrogestorManager to set
	 */
	public void setUsuarioCentrogestorManager(UsuarioCentrogestorManager usuarioCentrogestorManager) {
		this.usuarioCentrogestorManager = usuarioCentrogestorManager;
	}
	/*FIN-TRA042*/
}
