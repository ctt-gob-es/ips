package es.map.ipsg.action.alerta;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import es.map.ips.common.exception.BusinessException;
import es.map.ips.common.model.OrderType;
import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.AlertaQuery;
import es.map.ips.model.CentroGestorQuery;
import es.map.ips.model.ModoAlertaQuery;
import es.map.ips.model.PerfilQuery;
import es.map.ips.model.TipoAlertaQuery;
import es.map.ips.model.Usuario;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.bean.AlertaBean;
import es.map.ipsg.bean.CentroGestorBean;
import es.map.ipsg.bean.TipoAlertaBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.form.AlertaForm;
import es.map.ipsg.manager.AlertaManager;
import es.map.ipsg.manager.TipoAlertaManager;
import es.map.ipsg.manager.UsuarioCentrogestorManager;
import es.map.ipsg.manager.UsuarioManager;
import es.map.ipsg.util.Constantes;

/**
 * El Class BuscarAlertaSpring.
 */
public class BuscarAlertaSpring extends AbstractSpring {
	
	/** el alerta manager. */
	AlertaManager alertaManager;
	
	/** el usuario manager. */
	private UsuarioManager usuarioManager;
	
	/** el tipo alerta manager. */
	private TipoAlertaManager tipoAlertaManager;
	
	/** el usuario centro gestor manager. */
	private UsuarioCentrogestorManager usuarioCentrogestorManager;
	
	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(BuscarAlertaSpring.class);
	
	/** La constante STRING_BUSCAR. */
	private static final String STRING_BUSCAR = "Buscar";
	
	/**
	 * Crea una nueva buscar alerta spring.
	 */
	public BuscarAlertaSpring() {
		try {
			setAlertaManager((AlertaManager) getBean("alertaManager"));
			setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
			setTipoAlertaManager((TipoAlertaManager) getBean("tipoAlertaManager"));
			setUsuarioCentrogestorManager((UsuarioCentrogestorManager) getBean("usuarioCentrogestorManager"));
			
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error en BuscarAlerta:", e);
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		logger.info("Comienza Buscar Alerta Accion");
		//*********** METO EL PARAMETRO QUE ME INDICA EN QUE MENU ESTOY **********		
		String menu_alertas = RESOURCE_BUNDLE.getString("field.menu.alertas");
		this.getRequest().getSession().setAttribute("pagActiva",menu_alertas);
		String subMenu_alerta = RESOURCE_BUNDLE.getString("field.menuLateral.alertas.buscar");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_alerta);
		//****************************************************************** 
		getLogger().debug("BuscarAlertasSpring -start");
	try{
		AlertaForm formulario = (AlertaForm) form;
		String busqueda=null;
		String campo=null;
		String direccion=null;
		String auxCampo = formulario.getCampo();
		
		String paginaActual = this.getRequest().getParameter("paginaActual");
		String paginaTotal = this.getRequest().getParameter("paginasTotales");
		String cambios = this.getRequest().getParameter("cambios");
		
		String numReg = this.getRequest().getParameter("numRegistro");
		logger.info("NumRegistros: "+numReg);
		
		
		//Inicializamos el form
		formulario.setIdCentroGestor(null);
		formulario.setIdModo(null);
		formulario.setIdPerfil(null);
		formulario.setIdTipo(null);
		formulario.setIdUsuario(null);
		formulario.setIdUsuarioSeleccionados(null);
		if(this.getRequest().getParameter("menu")!=null){
			if(this.getRequest().getParameter("menu").equals("S")){
				formulario.setIdCentroGestorBusqueda(null);
				formulario.setDsCentroGestor(null);
				busqueda = null;
				formulario.setNumRegistro(null);
			}
		}else{
			busqueda = this.getRequest().getParameter("submit");
		}
		
		if(this.getRequest().getParameter("llamada") != null){
			busqueda = this.getRequest().getParameter("llamada");
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
		
		//Tomamos el usuario que se ha logueado
		Integer perfilUsuario ;
		String sUsernameLogin = recuperarUsuario();
		
		
		
		//Obtenemos el Usuario que está logueado en la aplicación
		
		UsuarioQuery usuarioQuery = new UsuarioQuery();
		usuarioQuery.setLogin(sUsernameLogin); //Le pasamos el login del que está logueado
		UsuarioBean usuarioBean = usuarioManager.buscarUsuario(usuarioQuery);
		Usuario usuario = usuarioManager.toUsuario(usuarioBean);
		perfilUsuario=usuario.getPerfil().getId();
		//Si es Gestor Recuperamos el Centro Gestor.
		/*INI-TRA042*/
		List<CentroGestorBean> listaCentrosGestores = usuarioCentrogestorManager.buscarCentrosGestoresByIdUsuario(usuario.getId());
		setRequestAttribute("listaCentrosGestores",listaCentrosGestores);
		
		if( perfilUsuario==Constantes.PERFIL_GESTOR_INT){
			formulario.setListaCentrosGestores(listaCentrosGestores);
		}
		/*FIN-TRA042*/		
		//Se realiza solo la busqueda de las alertas creadas por el perfil del usuario
		formulario.setIdPerfil(perfilUsuario);
		
		
		//Creamos la consulta
		AlertaQuery alertaQuery;
		
		//Si ha seleccionado un campo de ordenacion se le asigna el atributo de ordenacion
		if(formulario.getCampo()!= null && !formulario.getCampo().isEmpty()){
				campo=cargarCampos(formulario.getCampo());
				formulario.setCampo(campo);
				direccion=formulario.getDireccion();		
		}
		cargarCombos();
		
		alertaQuery=crearQueryAlerta(formulario,paginaActual,numReg,busqueda,campo,direccion);
		List<AlertaBean> alertas = null;
		if(STRING_BUSCAR.equals(busqueda) || "Ordenar".equals(busqueda) || "Paginar".equals(busqueda)){ 
			alertas = alertaManager.buscarAlertaAll(alertaQuery);
		}
		int pagTotal = 0;
		int numPaginas=0;
		if(formulario.getNumRegistro() == null && numReg == null){
			//Para el primer paso para el action, se establece que los registros por pagina son 5
			formulario.setNumRegistro(Constantes.NUMERO_REGISTROS_PAGINA);
		}
		int numRegistros = 0;
		if(numReg != null && !"".equals(numReg)){ 
			numRegistros = comprobarEntero(numReg);
		}else{
			numRegistros = comprobarEntero(formulario.getNumRegistro());
		}
		if(numRegistros > 50){
			numRegistros = 50;
			formulario.setNumRegistro(String.valueOf(numRegistros));
		}else{
			formulario.setNumRegistro(String.valueOf(numRegistros));
		}
		//Recorrer la lista, para activar los enlaces
		if(alertas != null && alertas.size()!= 0){
			pagTotal= alertas.get(0).getNumTotal();
			numPaginas = pagTotal / numRegistros;
			int resto = pagTotal % numRegistros;
			if(resto > 0){
				numPaginas++;
			}
			if(alertas.size()>numRegistros){
				alertas.remove(alertas.size()-1);
			}			
		}
		
		if(paginaActual==null || paginaActual.equals(""))
			paginaActual="1";
		
		setRequestAttribute("perfilUsuario", perfilUsuario);
		setRequestAttribute("alertas", alertas);
		setRequestAttribute("paginaActual", paginaActual);
		setRequestAttribute("paginasTotales", numPaginas);	
		setRequestAttribute("submit", busqueda);
		setRequestAttribute("cambios", cambios);
		formulario.setCampo(auxCampo);
		getLogger().debug("BuscarAlertasSpring -end");
		logger.info("Termina Buscar Alerta Accion");
		
	}catch(Exception eGenerico){
		
		logger.error("Error en buscar alerta: "+ eGenerico);
		this.getRequest().setAttribute("descripcionError", RESOURCE_BUNDLE.getString("field.errorGenerico"));
		return "errorGenerico";
	}
		return "success";
	}
	
	/**
	 * Recuperar usuario.
	 *
	 * @return el string
	 */
	private String recuperarUsuario() {
		String sUsernameLogin = "";
		try{
			UsuarioBean usuarioBean = (UsuarioBean) getRequest().getSession().getAttribute("usuario");
			sUsernameLogin=usuarioBean.getLogin();
			logger.info("sUsernameLogin: "+sUsernameLogin);
			return sUsernameLogin;
		}catch(Exception e){
			logger.error("Error en recuperarUsuarioSesion:"+ sUsernameLogin , e);
			new BusinessException("exception.recuperarUsuarioSesion");
			logger.info("Termina Buscar Alerta Accion");
			return "error";
		}
	}

	/**
	 * Cargar combos.
	 */
	private void cargarCombos() {
		TipoAlertaQuery tipoAlertaQuery = new TipoAlertaQuery();
		ArrayList<TipoAlertaBean> alertas;
		alertas = tipoAlertaManager.buscarTipoAlertaCombo(tipoAlertaQuery);
		setRequestAttribute("tiposAlertas",alertas);		
	}

	/**
	 * Comprobar entero.
	 *
	 * @param campo el campo
	 * @return el int
	 */
	private int comprobarEntero(String campo) {
		try{
			return Integer.parseInt(campo);
		}catch(Exception e){
			logger.error(e);
			e.printStackTrace();
			return Integer.parseInt(Constantes.NUMERO_REGISTROS_PAGINA);
		}
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
			logger.error("Error al cargar campo:" + codCampo , e);
		}
			if (campo != null && !"".equals(campo) && !campo.equals("null")){
				
					switch(codCampo){
					//Los campos de ordenacion del jsp
						case 1:auxCampo = "id";break;
						case 2:auxCampo = "centroGestor";break;
						case 3:auxCampo = "tipoAlerta";break;
						case 4:auxCampo = "modoAlerta";break;
						case 5:auxCampo = "perfil";break;
						default:break;
					}
				
			}
		return auxCampo;
	}
	
	/**
	 * Crear query alerta.
	 *
	 * @param formulario el formulario
	 * @param paginaActual el pagina actual
	 * @param numReg el num reg
	 * @param busqueda el busqueda
	 * @param campo el campo
	 * @param direccion el direccion
	 * @return el alerta query
	 */
	private AlertaQuery crearQueryAlerta(AlertaForm formulario, String paginaActual, String numReg, String busqueda, String campo, String direccion) {
		
		String id = formulario.getIdCentroGestorBusqueda();
		Integer perfil=formulario.getIdPerfil();
		String tipoAlerta = formulario.getTipoAlerta();

		if(formulario.getNumRegistro() == null && numReg == null){
			//Para el primer paso para el action, se establece que los registros por pagina son 5
			formulario.setNumRegistro(Constantes.NUMERO_REGISTROS_PAGINA);
		}
		int numRegistros = 0;
		if(StringUtils.isNotEmpty(numReg)){ 
			try{
				numRegistros = Integer.parseInt(numReg);
			}catch(Exception e){
				logger.error("Error al crear QueryAlerta:" ,e);
				numRegistros = Integer.parseInt(Constantes.NUMERO_REGISTROS_PAGINA);
			}
		}else{
			try{
				numRegistros = Integer.parseInt(formulario.getNumRegistro());
			}catch(Exception e){
				logger.error("Error numero de registros:" + numRegistros , e);
				numRegistros = Integer.parseInt(Constantes.NUMERO_REGISTROS_PAGINA);
			}
		}
		if(numRegistros > 50){
			numRegistros = 50;
			formulario.setNumRegistro(String.valueOf(numRegistros));
		}else{
			formulario.setNumRegistro(String.valueOf(numRegistros));
		}
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
		
		AlertaQuery alertaQueryAux = new AlertaQuery();
	
		crearQueryAlerta2(alertaQueryAux,id,tipoAlerta,perfil,campo,direccion);
		
		alertaQueryAux.setMaxResults(tamanyoPaginacion);
		alertaQueryAux.setFirstResult(primerRegistro);
		
		return alertaQueryAux;
	}
	
	/**
	 * Crear query alerta 2.
	 *
	 * @param alertaQueryAux el alerta query aux
	 * @param id el id
	 * @param tipoAlerta el tipo alerta
	 * @param perfil el perfil
	 * @param campo el campo
	 * @param direccion el direccion
	 */
	private void crearQueryAlerta2(AlertaQuery alertaQueryAux, String id, String tipoAlerta, Integer perfil, String campo, String direccion) {
		alertaQueryAux.setEstado(Constantes.ALERTA_ESTADO_ACTIVO);
		if(id != null && !"".equals(id)){
			alertaQueryAux.addCentroGestorIn(Integer.valueOf(id));
		}
		if(StringUtils.isNotEmpty(tipoAlerta)){
			TipoAlertaQuery tipoAlertaQuery = new TipoAlertaQuery();
			tipoAlertaQuery.setId(Integer.parseInt(tipoAlerta));
			alertaQueryAux.setTipoAlerta(tipoAlertaQuery);
		}
		
		alertaQueryAux.addPerfilIn(perfil);
		if(campo != null && !"0".equals(campo) && !"".equals(campo)){
			OrderType orden =("up".equals(direccion) == true ? OrderType.ASC : OrderType.DESC);	
			
			if("centroGestor".equals(campo)){
				CentroGestorQuery centroGestorQuery = new CentroGestorQuery();
				centroGestorQuery.addOrder(CentroGestorQuery.DESCRIPCION, orden);
				alertaQueryAux.setCentroGestor(centroGestorQuery);
			}else if("tipoAlerta".equals(campo)){
				TipoAlertaQuery tipoAlertaQuery = new TipoAlertaQuery();
				tipoAlertaQuery.addOrder(TipoAlertaQuery.DESCRIPCION, orden);
				alertaQueryAux.setTipoAlerta(tipoAlertaQuery);
			}else if("modoAlerta".equals(campo)){
				ModoAlertaQuery modoAlertaQuery = new ModoAlertaQuery();
				modoAlertaQuery.addOrder(ModoAlertaQuery.DESCRIPCION, orden);
				alertaQueryAux.setModoAlerta(modoAlertaQuery);
			}else if("perfil".equals(campo)){
				PerfilQuery perfilQuery = new PerfilQuery();
				perfilQuery.addOrder(PerfilQuery.DESCRIPCION, OrderType.ASC);
				alertaQueryAux.setPerfil(perfilQuery);
			}else{				
				alertaQueryAux.addOrder(campo,orden);
			}
		}
		alertaQueryAux.setCalculateNumResults(true);
	}

	/**
	 * Obtiene el alerta manager.
	 *
	 * @return el alerta manager
	 */
	public AlertaManager getAlertaManager() {
		return alertaManager;
	}

	/**
	 * Establece el ministerio manager.
	 *
	 * @param alertaManager el nuevo ministerio manager
	 */
	public void setMinisterioManager(AlertaManager alertaManager) {
		this.alertaManager = alertaManager;
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
	 * Establece el alerta manager.
	 *
	 * @param alertaManager the alertaManager to set
	 */
	public void setAlertaManager(AlertaManager alertaManager) {
		this.alertaManager = alertaManager;
	}

	/**
	 * Obtiene el usuario manager.
	 *
	 * @return the usuarioManager
	 */
	public UsuarioManager getUsuarioManager() {
		return usuarioManager;
	}

	/**
	 * Establece el usuario manager.
	 *
	 * @param usuarioManager the usuarioManager to set
	 */
	public void setUsuarioManager(UsuarioManager usuarioManager) {
		this.usuarioManager = usuarioManager;
	}

	/**
	 * Obtiene el tipo alerta manager.
	 *
	 * @return el tipo alerta manager
	 */
	public TipoAlertaManager getTipoAlertaManager() {
		return tipoAlertaManager;
	}

	/**
	 * Establece el tipo alerta manager.
	 *
	 * @param tipoAlertaManager el nuevo tipo alerta manager
	 */
	public void setTipoAlertaManager(TipoAlertaManager tipoAlertaManager) {
		this.tipoAlertaManager = tipoAlertaManager;
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
