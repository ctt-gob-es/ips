package es.map.ipsg.action.especialidad;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import es.map.ips.common.model.Comparator;
import es.map.ips.common.model.OrderType;
import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringMessage;
import es.map.ips.common.spring.SpringMessages;
import es.map.ips.model.CentroGestorQuery;
import es.map.ips.model.CuerpoEscalaQuery;
import es.map.ips.model.EspecialidadQuery;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.bean.CentroGestorBean;
import es.map.ipsg.bean.EspecialidadBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.form.EspecialidadForm;
import es.map.ipsg.manager.EspecialidadManager;
import es.map.ipsg.manager.UsuarioCentrogestorManager;
import es.map.ipsg.manager.UsuarioManager;
import es.map.ipsg.util.Constantes;

/**
 * El Class BuscarEspecialidadSpring.
 */
@SuppressWarnings("rawtypes")
public class BuscarEspecialidadSpring extends AbstractSpring {

	/** La constante MESSAGE_RESOUCE. */
	//Declarar los resource
	private static final String MESSAGE_RESOUCE = "MessageResources";
	
	/** La constante RESOURCE_MESSAGE. */
	private static final ResourceBundle RESOURCE_MESSAGE = ResourceBundle.getBundle(MESSAGE_RESOUCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(BuscarEspecialidadSpring.class);
	
	/** el properties. */
	private static Properties properties;
	
	/** La constante STRING_ERROR_BUSCARESPECIALIDAD_NUMEROREGISTRO. */
	private static final String STRING_ERROR_BUSCARESPECIALIDAD_NUMEROREGISTRO = "Error BuscarEspecialidadSpring() - numero registro:";
	
	/** el especialidad manager. */
	private EspecialidadManager especialidadManager;
	
	/** el usuario centro gestor manager. */
	private UsuarioCentrogestorManager usuarioCentrogestorManager;
	
	/** el usuario manager. */
	private UsuarioManager usuarioManager;
	
	/**
	 * Crea una nueva buscar especialidad spring.
	 */
	public BuscarEspecialidadSpring() {
		try {
			
			setEspecialidadManager((EspecialidadManager) getBean("especialidadManager"));
			setUsuarioCentrogestorManager((UsuarioCentrogestorManager) getBean("usuarioCentrogestorManager"));
			properties = (Properties) getBean("propertiesBean");
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error BuscarEspecialidadSpring():",e);
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		getLogger().debug("BuscarEspecialidadSpring -start");
		//*********** METO EL PARAMETRO QUE ME INDICA EN QUE MENU ESTOY **********
		String menu_tablaBase = RESOURCE_MESSAGE.getString("field.menu.tablasBase");
		this.getRequest().getSession().setAttribute("pagActiva", menu_tablaBase);
		String subMenu_especialidad = RESOURCE_MESSAGE.getString("field.menuLateral.tablasBase.especialidad");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_especialidad);
		//****************************************************************** 
		
		//INI - cpasculi - IPS-148 - Campo CentroGestor
		
		setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
		UsuarioBean usuarioSession = (UsuarioBean) getRequest().getSession().getAttribute("usuario");
		UsuarioBean usuarioBean = recuperarUsuario(usuarioSession.getLogin());
		
		//FIN - cpasculi - IPS-148 - Campo CentroGestor
		try{
		
			//Cojo el lugar de la llamada
			
			//Coger el form del jsp
			EspecialidadForm formulario;
			formulario = (EspecialidadForm) form;
			String busqueda = formulario.getSubmit();
			if(this.getRequest().getParameter("llamada") != null){
				busqueda = this.getRequest().getParameter("llamada");
			}
			
			// Si viene desde el menu borramos los datos previamente introducidos
			if ("S".equals(this.getRequest().getParameter("menu")))
			{
				formulario.setDescripcion("");	// Si viene del menu borramos todos sus campos
				formulario.setCuerpoEscala("");
				formulario.setDsCuerpoEscala("");
				formulario.setCentroGestor("");
				formulario.setCuerpoEscala("");
				busqueda = "";
			}	

			//Creamos la Query
			EspecialidadQuery especialidadQuery;
			
			
			if(StringUtils.isNotEmpty(formulario.getCampo()) && !"Paginar".equals(busqueda)){
				try{	
					Integer.parseInt(formulario.getCampo());
					String campo=cargarCampos(formulario.getCampo());
					formulario.setCampo(campo);
				}catch(Exception e){
					logger.error("Error BuscarEspecialidadSpring() - cargarCampos:"+ formulario.getCampo(),e);
				}			
			}
			//Llama a la funcion para crear la Query pasandole el formulario		
			String paginaActual = this.getRequest().getParameter("paginaActual");
			String paginaTotal = this.getRequest().getParameter("paginasTotales");
			String numReg = this.getRequest().getParameter("numRegistro");
			logger.info("NumRegistros: "+numReg);
			String cambios = this.getRequest().getParameter("cambios");
			
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
			
			if("0".equals(paginaActual) || paginaActual == null || "".equals(paginaActual)){
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
			
			//Creamos la query para busca la especialidad
			especialidadQuery=crearQueryEspecialidad(formulario,paginaActual,numReg,listaCentrosGestores);
			/*FIN-TRA042*/
					
			List<EspecialidadBean> especialidad = null;
			if("Buscar".equals(busqueda) || "Ordenar".equals(busqueda) || "Paginar".equals(busqueda)){ 
				especialidad = especialidadManager.buscarEspecialidadAll(especialidadQuery);
				//Error sin resultados
				if (especialidad != null && especialidad.size() == 0) {
					SpringMessages errors = new SpringMessages();
					errors.add("errorSinResultados", new SpringMessage("field.cuerpoEscala.error"));
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
			int numRegistros;
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
			if(especialidad != null && especialidad.size()!= 0){
				pagTotal= especialidad.get(0).getNumTotal();
				numPaginas = pagTotal / numRegistros;
				int resto = pagTotal % numRegistros;
				if(resto > 0){
					numPaginas++;
				}
				if(especialidad.size()>numRegistros){
					especialidad.remove(especialidad.size()-1);
				}
				
			}
			
			//Pasar la convocatoria y la pagina al jsp
			if(paginaActual==null||"".equals(paginaActual))
				paginaActual="1";
			
			setRequestAttribute("especialidades", especialidad);			
			setRequestAttribute("paginaActual", paginaActual);
			setRequestAttribute("paginasTotales", numPaginas);	
			setRequestAttribute("cambios", cambios);
			getLogger().debug("BuscarEspecialidadSpring -end");
			return "success";
		}catch (Exception e){
			logger.warn(e);
			this.getRequest().setAttribute("descripcionError", e.getMessage());
			logger.error("Error BuscarEspecialidadSpring()-doExecute() ",e);
			return "nosuccess";
		}
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
			logger.error(STRING_ERROR_BUSCARESPECIALIDAD_NUMEROREGISTRO+ numero,e);
			resultado = Integer.parseInt(Constantes.NUMERO_REGISTROS_PAGINA);
		}
		return resultado;
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
			logger.error("Error BuscarEspecialidadSpring() - cargarCampos:"+ codCampo,e);
		}
			if (campo != null && !"".equals(campo) && !campo.equals("null")){
				
					switch(codCampo){
					//Los campos de ordenacion del jsp
						case 1:auxCampo = "id";break;
						case 2:auxCampo = "codigo";break;
						case 3:auxCampo = "descripcion";break;
						case 4:auxCampo = "cuerpoEscala";break;
						case 5:auxCampo = "visible";break;
						default:break;
					}
				
			}
		return auxCampo;
	}
	
	//INI - cpasculi - IPS-148 - Campo CentroGestor
	
	/**
	 * Recuperar usuario.
	 *
	 * @param username el username
	 * @return el usuario bean
	 */
	private UsuarioBean recuperarUsuario(String username){
		UsuarioQuery usuarioQuery = new UsuarioQuery();
		usuarioQuery.setLogin(username);
		UsuarioBean usuarioBean = usuarioManager.buscarUsuario(usuarioQuery);
		return usuarioBean;
	}
	
	//FIN - cpasculi - IPS-148 - Campo CentroGestor
	
	
	/**
	 * Crear query especialidad.
	 *
	 * @param formulario el formulario
	 * @param paginaActual el pagina actual
	 * @param numReg el num reg
	 * @param listaCentrosGestores 
	 * @return el especialidad query
	 */
	/*INI-TRA042*/
	private EspecialidadQuery crearQueryEspecialidad(EspecialidadForm formulario,String paginaActual,String numReg, List<CentroGestorBean> listaCentrosGestores) {
//Recoger los datos del formulario
		
		String descripcion = formulario.getDescripcion();
		String cuerpoEscala = formulario.getCuerpoEscala();
		String centroGestor = formulario.getCentroGestor();
		Integer idCuerpoEscala = null;
		Integer idCentroGestor = null;
		
		if(StringUtils.isNotEmpty(centroGestor)){
			idCentroGestor = Integer.valueOf(centroGestor);
			if(cuerpoEscala != null && !cuerpoEscala.equals("") )
			{	
				idCuerpoEscala = Integer.valueOf(cuerpoEscala);
			}
		}
			
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
		
		EspecialidadQuery especialidadQuery = new EspecialidadQuery();
		if(paginaActual != null){
			especialidadQuery.setMaxResults(tamanyoPaginacion);
			especialidadQuery.setFirstResult(primerRegistro);
		}
		especialidadQuery.setCalculateNumResults(true);
		//Comprobar los filtros para realizar la busqueda
			
		crearQueryEspecialidad2(campo,direccion,especialidadQuery,idCentroGestor,idCuerpoEscala,descripcion,listaCentrosGestores);
		
		return especialidadQuery;		
	}
	
/**
 * Crear query especialidad 2.
 *
 * @param campo el campo
 * @param direccion el direccion
 * @param especialidadQuery el especialidad query
 * @param idCentroGestor el id centro gestor
 * @param idCuerpoEscala el id cuerpo escala
 * @param descripcion el descripcion
 * @param listaCentrosGestores 
 */
private void crearQueryEspecialidad2(String campo, String direccion, EspecialidadQuery especialidadQuery, Integer idCentroGestor, Integer idCuerpoEscala, String descripcion, List<CentroGestorBean> listaCentrosGestores) {
		
		if(StringUtils.isNotEmpty(descripcion)){
			try{
			
				especialidadQuery.setDescripcion(descripcion);
				especialidadQuery.setDescripcionComparator(Comparator.LIKE_LEFT_RIGHT);
			
			}catch(Exception e){
				logger.error("Error BuscarEspecialidadSpring() - descripcion:",e);
			}
		}
			
		String estado= RESOURCE_MESSAGE.getString("field.estado.activo");						
		especialidadQuery.setEstado(estado.charAt(0));
		CuerpoEscalaQuery cuerpoEscalaQuery = new CuerpoEscalaQuery();
		CentroGestorQuery centroGestorQuery = new CentroGestorQuery();
		
		
		if(campo!=null&&!"0".equals(campo)&&!"".equals(campo)){
			OrderType orden = ("up".equals(direccion)==true ? OrderType.ASC : OrderType.DESC);
			if("cuerpoEscala".equals(campo)){
				cuerpoEscalaQuery.addOrder("descripcion",orden);
				especialidadQuery.setCuerpoEscala(cuerpoEscalaQuery);
			}else
				especialidadQuery.addOrder(campo, orden);
		}
		if(idCentroGestor!=null){
			if(idCuerpoEscala != null){
				cuerpoEscalaQuery.setId(idCuerpoEscala);
				especialidadQuery.setCuerpoEscala(cuerpoEscalaQuery);
			}else{
				centroGestorQuery.setId(idCentroGestor);
				cuerpoEscalaQuery.setCentroGestor(centroGestorQuery);
				especialidadQuery.setCuerpoEscala(cuerpoEscalaQuery);
			}
		} else {
			if(listaCentrosGestores != null && listaCentrosGestores.size()>0) {
				for(CentroGestorBean cg: listaCentrosGestores) {
					centroGestorQuery.addIdCentroGestorIn(cg.getId());
				}
				cuerpoEscalaQuery.setCentroGestor(centroGestorQuery);
				especialidadQuery.setCuerpoEscala(cuerpoEscalaQuery);
			}
		}

	}
	/*FIN-TRA042*/
	
	/**
	 * Obtiene el especialidad manager.
	 *
	 * @return the especialidadManager
	 */
	public EspecialidadManager getEspecialidadManager() {
		return especialidadManager;
	}

	/**
	 * Establece el especialidad manager.
	 *
	 * @param especialidadManager the especialidadManager to set
	 */
	public void setEspecialidadManager(EspecialidadManager especialidadManager) {
		this.especialidadManager = especialidadManager;
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
