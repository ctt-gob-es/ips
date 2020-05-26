package es.map.ipsg.action.adaptacionesDiscapacidad;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import es.map.ips.common.model.Comparator;
import es.map.ips.common.model.OrderType;
import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringMessage;
import es.map.ips.common.spring.SpringMessages;
import es.map.ips.model.CentroGestorQuery;
import es.map.ips.model.DiscapacidadQuery;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.bean.CentroGestorBean;
import es.map.ipsg.bean.DiscapacidadBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.form.DiscapacidadForm;
import es.map.ipsg.manager.DiscapacidadManager;
import es.map.ipsg.manager.UsuarioCentrogestorManager;
import es.map.ipsg.manager.UsuarioManager;
import es.map.ipsg.util.Constantes;

/**
 * El Class BuscarDiscapacidadSpring.
 */
public class BuscarDiscapacidadSpring extends AbstractSpring {

	/** La constante MESSAGE_RESOUCE. */
	//Declarar los resource
	private static final String MESSAGE_RESOUCE = "MessageResources";
	
	/** La constante RESOURCE_MESSAGE. */
	private static final ResourceBundle RESOURCE_MESSAGE = ResourceBundle.getBundle(MESSAGE_RESOUCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(BuscarDiscapacidadSpring.class);
	
	/** La constante STRINGDESCRIPCION. */
	private static final String STRINGDESCRIPCION = "descripcion";
	
	/** el properties. */
	private static Properties properties;

	/** el discapacidad manager. */
	//Declaracion de manager
	private DiscapacidadManager discapacidadManager;
	
	/** el usuario manager. */
	private UsuarioManager usuarioManager;
	
	/** el usuario centro gestor manager. */
	private UsuarioCentrogestorManager usuarioCentrogestorManager;
	
	
	/**
	 * Crea una nueva buscar discapacidad spring.
	 */
	public BuscarDiscapacidadSpring() {
		try {
			
			setDiscapacidadManager((DiscapacidadManager) getBean("discapacidadManager"));
			setUsuarioCentrogestorManager((UsuarioCentrogestorManager) getBean("usuarioCentrogestorManager"));
			
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error BuscarDiscapacidadSpring():",e);
		}
		
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		getLogger().debug("BuscarDiscapacidadSpring -start");
		//*********** METO EL PARAMETRO QUE ME INDICA EN QUE MENU ESTOY **********
		String menu_tablaBase = RESOURCE_MESSAGE.getString("field.menu.tablasBase");
		this.getRequest().getSession().setAttribute("pagActiva", menu_tablaBase);
		String subMenu_Discapacidad = RESOURCE_MESSAGE.getString("field.menuLateral.tablasBase.discapacidad");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_Discapacidad);
		//****************************************************************** 
		
				setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
				UsuarioBean usuarioBean = (UsuarioBean) getRequest().getSession().getAttribute("usuario");
				usuarioBean = recuperarUsuario(usuarioBean.getNif());
		
		
		try{
			
			//Cojo el lugar de la llamada
			
			//Coger el form del jsp
			DiscapacidadForm formulario;
			formulario = (DiscapacidadForm) form;
			String busqueda = formulario.getSubmit();
			String numReg = this.getRequest().getParameter("numRegistro");
			logger.info("NumRegistros: "+numReg);
			String cambios = this.getRequest().getParameter("cambios");
			
			if(this.getRequest().getParameter("llamada") != null){
				busqueda = this.getRequest().getParameter("llamada");
			}
			
			// Si viene desde el menu borramos los datos previamente introducidos
			if ("S".equals(this.getRequest().getParameter("menu")))
			{
				formulario.setDescripcion("");	// Si viene del menu borramos todos sus campos
				formulario.setCentroGestor("");
				formulario.setDsCentroGestor("");
				busqueda = "";
			}
			
			//Creamos la Query
			DiscapacidadQuery discapacidadQuery;	
			
			//Si ha seleccionado un campo de ordenacion se le asigna el atributo de ordenacion
			if(StringUtils.isNotEmpty(formulario.getCampo()) && !"Paginar".equals(busqueda)){
				try{
					Integer.parseInt(formulario.getCampo());
					String campo=cargarCampos(formulario.getCampo());
					formulario.setCampo(campo);
				}catch(Exception e){
					logger.error("Error BuscarDiscapacidadSpring()- ordenar por campo:",e);
				}			
			}
			
			//Llama a la funcion para crear la Query pasandole el formulario
			
			String paginaActual = this.getRequest().getParameter("paginaActual");
			String paginaTotal = this.getRequest().getParameter("paginasTotales");
			
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
			
			if("0".equals(paginaActual) || paginaActual == null || "".equals(paginaActual) || "Buscar".equals(busqueda)){
				paginaActual = Constantes.PAGINA_PRINCIPAL;
				formulario.setPaginaActual(paginaActual);
			}
			
			/*INI-TRA042*/			
			List<CentroGestorBean> listaCentrosGestores = new ArrayList<CentroGestorBean>();
			
			if(usuarioBean!=null){
				logger.info("Perfil usuario: "+usuarioBean.getIdPerfil());
				setRequestAttribute("rol", usuarioBean.getIdPerfil());

				String sPerfilUsuario = comprobarPerfilUsuario(usuarioBean);

				if(sPerfilUsuario.equalsIgnoreCase(Constantes.ROL_GESTOR))
				{
					listaCentrosGestores = usuarioCentrogestorManager.buscarCentrosGestoresByIdUsuario(usuarioBean.getId());
					this.setRequestAttribute("listaCentrosGestores",listaCentrosGestores);
				}
			}
			
			discapacidadQuery=crearQueryDiscapacidad(formulario,paginaActual,numReg,listaCentrosGestores);
			/*FIN-TRA042*/	
	
			
			//Coger la lista de convocatorias a mostrar
		
			List<DiscapacidadBean> discapacidad = null;
			if("Buscar".equals(busqueda) || "Ordenar".equals(busqueda) || "Paginar".equals(busqueda)){ 
				discapacidad = discapacidadManager.buscarDiscapacidadAll(discapacidadQuery);
				//Error sin resultados
				if (discapacidad != null && discapacidad.size() == 0) {
					SpringMessages errors = new SpringMessages();
					errors.add("errorSinResultados", new SpringMessage("field.discapacidad.error"));
					this.setRequestAttribute("org.apache.spring.ERROR", errors);
				}
				cambios = "Correcto";
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
			//Recorrer la lista, para comprobar los estados de cada convocatoria para activar los enlaces
			if(discapacidad != null && discapacidad.size()!= 0){
				pagTotal= discapacidad.get(0).getNumTotal();
				numPaginas = pagTotal / numRegistros;
				int resto = pagTotal % numRegistros;
				if(resto > 0){
					numPaginas++;
				}
				if(discapacidad.size()>numRegistros){
					discapacidad.remove(discapacidad.size()-1);
				}
				
			}
								
			
			//Pasar la convocatoria y la pagina al jsp
			
			if(paginaActual==null||"".equals(paginaActual))
				paginaActual="1";
			
			setRequestAttribute("discapacidad", discapacidad);			
			setRequestAttribute("paginaActual", paginaActual);
			setRequestAttribute("paginasTotales", numPaginas);
			setRequestAttribute("cambios", cambios);
			getLogger().debug("BuscarDiscapacidadSpring -end");
			return "success";
			
		}catch(Exception e){
			logger.warn(e);
			this.getRequest().setAttribute("descripcionError", e.getMessage());
			logger.error("Error BuscarDiscapacidadSpring() - doExecute :",e);
			return "nosuccess";			
		}
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
	 * Comprobar entero.
	 *
	 * @param numero el numero
	 * @return el int
	 */
	private int comprobarEntero(String numero) {
		int resultado = 0;
		try{
			resultado = Integer.parseInt(numero);
		}catch(Exception e){
			logger.error("Error comprobarEntero():"+resultado,e);
			resultado = Integer.parseInt(Constantes.NUMERO_REGISTROS_PAGINA);
		}
		return resultado;
	}
	
	//INI - CPL - IPS-148 - Campo CentroGestor
	
		/**
	 * Recuperar usuario.
	 *
	 * @param username el username
	 * @return el usuario bean
	 */
	private UsuarioBean recuperarUsuario(String username){
			UsuarioQuery usuarioQuery = new UsuarioQuery();
			usuarioQuery.setNif(username);
			UsuarioBean usuarioBean = usuarioManager.buscarUsuario(usuarioQuery);
			return usuarioBean;
		}
		
	
	//FIN - CPL - IPS-148 - Campo CentroGestor
	

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
			logger.error("Error BuscarDiscapacidadSpring() - cargarCampos :"+codCampo,e);
		}
			if (campo != null && !"".equals(campo) && !campo.equals("null")){
				
					switch(codCampo){
					//Los campos de ordenacion del jsp
						case 1:auxCampo = "id";break;
						case 2:auxCampo = STRINGDESCRIPCION;break;
						case 3:auxCampo = "codigo";break;
						case 4:auxCampo = "categoria";break;
						case 5:auxCampo = "centroGestor";break;
						case 6:auxCampo = "grupo";break;	
						case 7:auxCampo = "visible";break;
						default:break;
					}
			
			}
			return auxCampo;
			}
			
	

	/**
	 * Crear query discapacidad.
	 *
	 * @param formulario el formulario
	 * @param paginaActual el pagina actual
	 * @param numReg el num reg
	 * @param listaCentrosGestores 
	 * @return el discapacidad query
	 */
	private DiscapacidadQuery crearQueryDiscapacidad(DiscapacidadForm formulario,String paginaActual,String numReg, List<CentroGestorBean> listaCentrosGestores) {
		//Recoger los datos del formulario
		
		String descripcion = formulario.getDescripcion();
		String campo = formulario.getCampo();	
		String direccion = formulario.getDireccion();
		
		
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
		//Si la pagina actual no esta definida, se establece a 1
		if("0".equals(paginaActual) || paginaActual == null || "".equals(paginaActual)){
			paginaActual = properties.getProperty("conf.primerPagina");
			formulario.setPaginaActual(paginaActual);
		}
		//Calcular el numero de lineas de cada paginacion
		int tamanyoPaginacionReal = numRegistros;
		int tamanyoPaginacion = tamanyoPaginacionReal+1;
		int numPagina = Integer.parseInt(paginaActual);
		int primerRegistro = (numPagina*tamanyoPaginacionReal)-tamanyoPaginacionReal;
		
		DiscapacidadQuery discapacidadQuery = new DiscapacidadQuery();
		if(paginaActual != null){
			discapacidadQuery.setMaxResults(tamanyoPaginacion);
			discapacidadQuery.setFirstResult(primerRegistro);
		}
		discapacidadQuery.setCalculateNumResults(true);
		//Comprobar los filtros para realizar la busqueda
			
		if(!StringUtils.isEmpty(descripcion)){
			try{
			
				discapacidadQuery.setDescripcion(descripcion.toUpperCase());
				discapacidadQuery.setDescripcionComparator(Comparator.LIKE_LEFT_RIGHT);
			
			}catch(Exception e){
				logger.error("Error BuscarDiscapacidadSpring() - crearQueryDiscapacidad- Descripcion :",e);
			}
		}
		
			
		//String estado= RESOURCE_MESSAGE.getString("field.estado.activo");						
		//discapacidadQuery.setEstado(estado.charAt(0));
		CentroGestorQuery centroGestorQuery = new CentroGestorQuery();
		/*INI-TRA042*/
		if(formulario.getCentroGestor() != null && !formulario.getCentroGestor().isEmpty()) {
			centroGestorQuery.setId(Integer.parseInt(formulario.getCentroGestor()));
		} else {
			if(listaCentrosGestores != null && listaCentrosGestores.size() > 0){
				for(CentroGestorBean cg: listaCentrosGestores) {
					centroGestorQuery.addIdCentroGestorIn(cg.getId());
				}
				
			}
		}
		discapacidadQuery.setCentroGestor(centroGestorQuery);
		/*FIN-TRA042*/		
		if(campo != null && !"0".equals(campo) && !"".equals(campo)){
			crearQueryDiscapacidadAux(discapacidadQuery,campo,direccion,centroGestorQuery);
		}	

		//Devulelve la convocatoria con los filtros
		return discapacidadQuery;
		
	}

	/**
	 * Crear query discapacidad aux.
	 *
	 * @param discapacidadQuery el discapacidad query
	 * @param campo el campo
	 * @param direccion el direccion
	 * @param centroGestorQuery el centro gestor query
	 */
	private void crearQueryDiscapacidadAux(DiscapacidadQuery discapacidadQuery, String campo, String direccion, CentroGestorQuery centroGestorQuery) {

		OrderType orden = ("up".equals(direccion)==true ? OrderType.ASC:OrderType.DESC);
		if("centroGestor".equals(campo)){
			centroGestorQuery.addOrder(STRINGDESCRIPCION, orden);
			discapacidadQuery.setCentroGestor(centroGestorQuery);
		}else{
			discapacidadQuery.addOrder(campo, orden);
		}	
		
	}
	
	/**
	 * Obtiene el discapacidad manager.
	 *
	 * @return el discapacidad manager
	 */
	public DiscapacidadManager getDiscapacidadManager() {
		return discapacidadManager;
	}

	/**
	 * Establece el discapacidad manager.
	 *
	 * @param discapacidadManager el nuevo discapacidad manager
	 */
	public void setDiscapacidadManager(DiscapacidadManager discapacidadManager) {
		this.discapacidadManager = discapacidadManager;
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
	 * Establece el usuario manager.
	 *
	 * @param usuarioManager el nuevo usuario manager
	 */
	public void setUsuarioManager(UsuarioManager usuarioManager) {
		this.usuarioManager = usuarioManager;
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
