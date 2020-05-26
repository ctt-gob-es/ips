package es.map.ipsg.action.convocatoria;


import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import es.map.ips.common.exception.BusinessException;
import es.map.ips.common.model.OrderType;
import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.CentroGestorQuery;
import es.map.ips.model.ConvocatoriaQuery;
import es.map.ips.model.CuerpoEscalaQuery;
import es.map.ips.model.ModeloQuery;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.bean.CentroGestorBean;
import es.map.ipsg.bean.ConvocatoriasBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.form.CrearConvocatoriaForm;
import es.map.ipsg.manager.CamposPropiosManager;
import es.map.ipsg.manager.ConvocatoriasManager;
import es.map.ipsg.manager.CuerpoEscalaManager;
import es.map.ipsg.manager.UsuarioCentrogestorManager;
import es.map.ipsg.manager.UsuarioManager;
import es.map.ipsg.util.Constantes;



/**
 * El Class ListarConvocatoriaSpring.
 */
public class ListarConvocatoriaSpring extends AbstractSpring {
	
	/** el convocatorias manager. */
	private ConvocatoriasManager convocatoriasManager;
	
	/** el usuario manager. */
	private UsuarioManager usuarioManager;
	
	/** el cuerpo escala manager. */
	private CuerpoEscalaManager cuerpoEscalaManager;
	
	/** el campo propios manager. */
	private CamposPropiosManager campoPropiosManager;
	
	/** el usuario centro gestor manager. */
	private UsuarioCentrogestorManager usuarioCentrogestorManager;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(ListarConvocatoriaSpring.class);
	
	/** La constante MESSAGE_RESOUCE. */
	private static final String MESSAGE_RESOUCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOUCE);
	
	/** La constante STRING_PARAMETRO. */
	private static final String STRING_PARAMETRO = "parametro";
	
	/** La constante STRING_PARAMETRO2. */
	private static final String STRING_PARAMETRO2 = "parametro2";
	
	/**
	 * Crea una nueva listar convocatoria spring.
	 */
	public ListarConvocatoriaSpring() {
		try{
			setConvocatoriasManager((ConvocatoriasManager) getBean("convocatoriaManager"));
			setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
			setCuerpoEscalaManager((CuerpoEscalaManager) getBean("cuerposEscalaManager"));
			setCampoPropiosManager((CamposPropiosManager)getBean("camposPropiosManager"));
			setUsuarioCentrogestorManager((UsuarioCentrogestorManager) getBean("usuarioCentrogestorManager"));
		}catch(Exception e){
			logger.warn(e);
			logger.error("Error ListarConvocatoriaSpring() ",e);
		}
	}


	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {

		getLogger().debug("ListarConvocatoriaSpring -start");
	try{
		CrearConvocatoriaForm formulario;
		formulario = (CrearConvocatoriaForm) form;
		
		logger.info("Entra en el action");
		String busqueda = this.getRequest().getParameter("submit");
		String centro = this.getRequest().getParameter("centro");
		
		String cambios; 
		String paginaActual = this.getRequest().getParameter("paginaActual");
		String paginaTotal = this.getRequest().getParameter("paginasTotales");
		String numRegistro =this.getRequest().getParameter("numRegistro");
		
		if(numRegistro!=null && !"".equals(numRegistro)){
			formulario.setNumRegistro(numRegistro);
		}
		
		if(this.getRequest().getParameter("llamada") != null){
			busqueda = this.getRequest().getParameter("llamada");
		}
		if(paginaActual != null && !"".equals(paginaActual) && paginaTotal != null && !"".equals(paginaTotal)){
			int pagActu = Integer.parseInt(paginaActual);		
			int pagTotales = Integer.parseInt(paginaTotal);

			if(pagActu > pagTotales){
				paginaActual = String.valueOf(pagActu-1);
			}
		}
		
		
		String parametro = this.getRequest().getParameter(STRING_PARAMETRO);
		logger.info("parametro: "+ this.getRequest().getParameter(STRING_PARAMETRO));
		logger.info("parametroGuARDADO: "+ this.getRequest().getAttribute(STRING_PARAMETRO));
		String parametro2 = this.getRequest().getParameter(STRING_PARAMETRO2);
		logger.info("parametro2: "+ this.getRequest().getParameter(STRING_PARAMETRO2));
		logger.info("parametroGuARDADO2: "+ this.getRequest().getAttribute(STRING_PARAMETRO2));
		if(parametro != null && !"".equals(parametro)){
			setRequestAttribute(STRING_PARAMETRO, parametro);
			formulario.setParametro(parametro);
		}else{
			if(formulario.getParametro() != null && !"".equals(formulario.getParametro())){
				setRequestAttribute(STRING_PARAMETRO, formulario.getParametro());				
			}else{
				String paramAux = (String) this.getRequest().getAttribute(STRING_PARAMETRO);
				if(paramAux != null && !"".equals(paramAux)){
					setRequestAttribute(STRING_PARAMETRO, parametro);
					formulario.setParametro(parametro);
				}
			}
		}
		
		if(parametro2 != null && !"".equals(parametro2)){
			setRequestAttribute(STRING_PARAMETRO2, parametro2);
			formulario.setParametro2(parametro2);
		}else{
			if(formulario.getParametro2() != null && !"".equals(formulario.getParametro2())){
				setRequestAttribute(STRING_PARAMETRO2, formulario.getParametro2());				
			}else{
				String paramAux = (String) this.getRequest().getAttribute(STRING_PARAMETRO2);
				if(paramAux != null && !"".equals(paramAux)){
					setRequestAttribute(STRING_PARAMETRO2, parametro2);
					formulario.setParametro2(parametro2);
				}
			}
		}
		
		
		ConvocatoriaQuery convocatoriaQuery = new ConvocatoriaQuery();
		// TODO En caso de usuarios con perfiles Gestor y Operador, se muestran sólo los centros asociados a ellos.
		//Tomamos el usuario que se ha logueado
		String sUsernameLogin = recuperarUsuario();
		
		if (sUsernameLogin.equals("error")) {
			return sUsernameLogin;
		}

		UsuarioQuery usuarioQuery = new UsuarioQuery();
		CuerpoEscalaQuery cuerpoEscalaQuery = new CuerpoEscalaQuery();
		CentroGestorQuery centroGestorQuery = new CentroGestorQuery();
		usuarioQuery.setLogin(sUsernameLogin); //Le pasamos el login del que está logueado
		UsuarioBean usuarioBean = usuarioManager.buscarUsuario(usuarioQuery);
		
		/*INI-TRA042*/
		if (usuarioBean.getIdPerfil() == Constantes.PERFIL_GESTOR_INT || usuarioBean.getIdPerfil() == Constantes.PERFIL_OPERADOR_INT)
		{	
			String sPerfilUsuario = comprobarPerfilUsuario(usuarioBean);
			List<CentroGestorBean> listaCentrosGestores = new ArrayList<CentroGestorBean>();

			if(sPerfilUsuario.equalsIgnoreCase(Constantes.ROL_GESTOR))
			{
				listaCentrosGestores = usuarioCentrogestorManager.buscarCentrosGestoresByIdUsuario(usuarioBean.getId());
			}
			
			for(CentroGestorBean cg: listaCentrosGestores) {
				centroGestorQuery.addIdCentroGestorIn(cg.getId());
			}
			cuerpoEscalaQuery.setCentroGestor(centroGestorQuery);
			convocatoriaQuery.setCuerpoEscala(cuerpoEscalaQuery);
		}
		/*FIN-TRA042*/		
		
		if(formulario.getCampo()!= null && !"Paginar".equals(busqueda)){
			String campo=cargarCampos(formulario.getCampo());
			formulario.setCampo(campo);			
		}
		

		String campo = formulario.getCampo();
		String direccion = formulario.getDireccion();
		
		if(formulario.getNumRegistro() == null){
			//Para el primer paso para el action, se establece que los registros por pagina son 5
			formulario.setNumRegistro(Constantes.NUMERO_REGISTROS_PAGINA);
		}
		int numRegistros = Integer.parseInt(formulario.getNumRegistro());
		if("0".equals(paginaActual) || paginaActual == null || "".equals(paginaActual) || "Buscar".equals(busqueda)){
			paginaActual = "1";
		}
		int tamanyoPaginacionReal = numRegistros;
		int tamanyoPaginacion = tamanyoPaginacionReal+1;
		int numPagina = Integer.parseInt(paginaActual);
		int primerRegistro = (numPagina*tamanyoPaginacionReal)-tamanyoPaginacionReal;
		
		if(campo != null && !"0".equals(campo) && !"".equals(campo)){
			OrderType orden=("up".equals(direccion) == true ? OrderType.ASC : OrderType.DESC);
			if(campo.equals("cuerpoEscala")) {
				cuerpoEscalaQuery.addOrder("descripcion",orden);
				convocatoriaQuery.setCuerpoEscala(cuerpoEscalaQuery);				
			}
			else {
				convocatoriaQuery.addOrder(campo,orden);
			}
				
			
		}
		
		long codCuerpo;
		if(formulario.getIdConvocatoria() != null && !formulario.getIdConvocatoria().equals("0") && !formulario.getIdConvocatoria().equals("")){
			codCuerpo = comprobarLong(formulario.getIdConvocatoria());
			convocatoriaQuery.setId(codCuerpo);
		}else{
			//Ordenamos la lista de convocatorias por id, de mas nuevas a mas antiguas
			convocatoriaQuery.addOrder(convocatoriaQuery.ID,OrderType.DESC);
		}
		
		
		convocatoriaQuery.setCalculateNumResults(true);
		if(paginaActual != null){
			convocatoriaQuery.setMaxResults(tamanyoPaginacion);
			convocatoriaQuery.setFirstResult(primerRegistro);
		}
		
		List<ConvocatoriasBean> convocatoriasBean = null;
		if("Buscar".equals(busqueda) || "Ordenar".equals(busqueda) || "Paginar".equals(busqueda)){
			//Añadimos el filtro de busqueda por modelo 790001 para mostrar solo las convocatorias asociadas al modelo 790001
			ModeloQuery modeloQuery = new ModeloQuery();
			convocatoriaQuery.setModelo(modeloQuery);
			convocatoriasBean = convocatoriasManager.buscarConvocatoriasAll(convocatoriaQuery);
		}
		int pagTotal = 0;
		int numPaginas=0;
		if(formulario.getNumRegistro() == null){
			//Para el primer paso para el action, se establece que los registros por pagina son 5
			formulario.setNumRegistro(Constantes.NUMERO_REGISTROS_PAGINA);
		}

		cambios = "Correcto";	
		numRegistros = Integer.parseInt(formulario.getNumRegistro());

		if(convocatoriasBean != null && convocatoriasBean.size()!= 0){
			pagTotal= convocatoriasBean.get(0).getNumTotal();
			numPaginas = pagTotal / numRegistros;
			int resto = pagTotal % numRegistros;
			if(resto > 0){
				numPaginas++;
			}
			if(convocatoriasBean.size()>numRegistros){
				convocatoriasBean.remove(convocatoriasBean.size()-1);
			}	
		}
		
		setRequestAttribute(STRING_PARAMETRO, parametro);
		setRequestAttribute("centro", centro);
		setRequestAttribute("submit", busqueda);		
		setRequestAttribute("convocatorias", convocatoriasBean);
		setRequestAttribute("paginaActual", paginaActual);
		setRequestAttribute("paginasTotales", numPaginas);	
		setRequestAttribute("cambios", cambios);
		setRequestAttribute("numRegistro", formulario.getNumRegistro());
		logger.info("Termina el action");
		getLogger().debug("ListarConvocatoriaSpring -end");
	}catch(Exception eGenerico){
		logger.error("Error  ListarConvocatoriaSpring - doExecute: "+ eGenerico);
		this.getRequest().setAttribute("descripcionError", RESOURCE_BUNDLE.getString("field.errorGenerico"));
		return "errorGenerico";
	}
		return "success";
	}
	
	/**
	 * Comprobar long.
	 *
	 * @param numero el numero
	 * @return el long
	 */
	private long comprobarLong(String numero) {
		long resultado = 0L;	
		try{
			resultado = Long.parseLong(numero);
		}catch(Exception e){
			logger.error("Imposible parsear el id de Convocatoria: "+numero,e);
		}
		return resultado;
	}
	
	/**
	 * Recuperar usuario.
	 *
	 * @return el string
	 */
	private String recuperarUsuario() {
		try{
			UsuarioBean usuarioBean = (UsuarioBean) getRequest().getSession().getAttribute("usuario");
			return usuarioBean.getLogin();
		}catch(Exception e){
			logger.error("Error ListarConvocatoriaSpring - recuperarUsuarioSesion ",e);
			new BusinessException("exception.recuperarUsuarioSesion");
			return "error";
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
			logger.error("Error ListarConvocatoriaSpring - cargarCampos"+ codCampo,e);
		}
			if (campo != null && !"".equals(campo) && !campo.equals("null")){
				
					switch(codCampo){
					//Los campos de ordenacion del jsp
						case 1:auxCampo = "id";break;
						case 2:auxCampo = "ejercicio";break;
						case 3:auxCampo = "cuerpoEscala";break;
						case 4:auxCampo = "formaAcceso";break;
						default:break;
					}
				
			}
		
		return auxCampo;
	}

	/*INI-TRA042*/
	/**
	 * Comprobar perfil usuario.
	 *
	 * @param usuarioBean2 el id usuario
	 * @return el string
	 */
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
	 * Obtiene el campo propios manager.
	 *
	 * @return el campo propios manager
	 */
	public CamposPropiosManager getCampoPropiosManager() {
		return campoPropiosManager;
	}

	/**
	 * Establece el campo propios manager.
	 *
	 * @param campoPropiosManager el nuevo campo propios manager
	 */
	public void setCampoPropiosManager(CamposPropiosManager campoPropiosManager) {
		this.campoPropiosManager = campoPropiosManager;
	}

	/**
	 * Obtiene el logger.
	 *
	 * @return el logger
	 */
	public static Logger getLogger() {
		return logger;
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
