package es.map.ipsg.action.otrosTitulos;

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
import es.map.ips.model.OtrosTitulosQuery;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.bean.CentroGestorBean;
import es.map.ipsg.bean.OtrosTitulosBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.form.OtrosTitulosForm;
import es.map.ipsg.manager.OtrosTitulosManager;
import es.map.ipsg.manager.UsuarioCentrogestorManager;
import es.map.ipsg.manager.UsuarioManager;
import es.map.ipsg.util.Constantes;

/**
 * El Class BuscarOtrosTitulosSpring.
 */
@SuppressWarnings("rawtypes")
public class BuscarOtrosTitulosSpring extends AbstractSpring {

	/** La constante MESSAGE_RESOUCE. */
	//Declarar los resource
	private static final String MESSAGE_RESOUCE = "MessageResources";
	
	/** La constante RESOURCE_MESSAGE. */
	private static final ResourceBundle RESOURCE_MESSAGE = ResourceBundle.getBundle(MESSAGE_RESOUCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(BuscarOtrosTitulosSpring.class);
	
	/** La constante STRINGDESCRIPCION. */
	private static final String STRINGDESCRIPCION = "descripcion";
	
	/** el properties. */
	private static Properties properties;

	/** el otros titulos manager. */
	//Declaracion de manager
	private OtrosTitulosManager otrosTitulosManager;
	
	/** el usuario manager. */
	private UsuarioManager usuarioManager;
	
	/** el usuario centro gestor manager. */
	private UsuarioCentrogestorManager usuarioCentrogestorManager;
	
	/**
	 * Crea una nueva buscar otros titulos spring.
	 */
	public BuscarOtrosTitulosSpring() {
		try {
			
			setOtrosTitulosManager((OtrosTitulosManager) getBean("otrosTitulosManager"));
			setUsuarioCentrogestorManager((UsuarioCentrogestorManager) getBean("usuarioCentrogestorManager"));
			
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error BuscarOtrosTitulosSpring():",e);
		}
		
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		getLogger().debug("BuscarOtrosTitulosSpring -start");
		//*********** METO EL PARAMETRO QUE ME INDICA EN QUE MENU ESTOY **********
		String menu_tablaBase = RESOURCE_MESSAGE.getString("field.menu.tablasBase");
		this.getRequest().getSession().setAttribute("pagActiva", menu_tablaBase);
		String subMenu_OtrosTitulos = RESOURCE_MESSAGE.getString("field.menuLateral.tablasBase.otrosTitulos");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_OtrosTitulos);
		//****************************************************************** 
		
				setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
				UsuarioBean usuarioBean = (UsuarioBean) getRequest().getSession().getAttribute("usuario");
				usuarioBean = recuperarUsuario(usuarioBean.getNif());
		
		
		try{
			
			//Cojo el lugar de la llamada
			
			//Coger el form del jsp
			OtrosTitulosForm formulario;
			formulario = (OtrosTitulosForm) form;
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
			
			//Comprobamos el perfil
			
			
			//Creamos la Query
			OtrosTitulosQuery otrosTitulosQuery;	
			
			//Si ha seleccionado un campo de ordenacion se le asigna el atributo de ordenacion
			if(StringUtils.isNotEmpty(formulario.getCampo()) && !"Paginar".equals(busqueda)){
				try{
					Integer.parseInt(formulario.getCampo());
					String campo=cargarCampos(formulario.getCampo());
					formulario.setCampo(campo);
				}catch(Exception e){
					logger.error("Error BuscarOtrosTitulosSpring()- ordenar por campo:",e);
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
			
			otrosTitulosQuery=crearQueryOtrosTitulos(formulario,paginaActual,numReg,listaCentrosGestores);
			/*FIN-TRA042*/	
	
			
			//Coger la lista de convocatorias a mostrar
		
			List<OtrosTitulosBean> otrosTitulos = null;
			if("Buscar".equals(busqueda) || "Ordenar".equals(busqueda) || "Paginar".equals(busqueda)){ 
				otrosTitulos = otrosTitulosManager.buscarOtrosTitulosAll(otrosTitulosQuery);
				//Error sin resultados
				if (otrosTitulos != null && otrosTitulos.size() == 0) {
					SpringMessages errors = new SpringMessages();
					errors.add("errorSinResultados", new SpringMessage("field.otrosTitulos.error"));
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
			if(otrosTitulos != null && otrosTitulos.size()!= 0){
				pagTotal= otrosTitulos.get(0).getNumTotal();
				numPaginas = pagTotal / numRegistros;
				int resto = pagTotal % numRegistros;
				if(resto > 0){
					numPaginas++;
				}
				if(otrosTitulos.size()>numRegistros){
					otrosTitulos.remove(otrosTitulos.size()-1);
				}
				
			}
							
			
			//Pasar la convocatoria y la pagina al jsp
			
			if(paginaActual==null||"".equals(paginaActual))
				paginaActual="1";
			
			setRequestAttribute("otrosTitulos", otrosTitulos);			
			setRequestAttribute("paginaActual", paginaActual);
			setRequestAttribute("paginasTotales", numPaginas);
			setRequestAttribute("cambios", cambios);
			getLogger().debug("BuscarOtrosTitulosSpring -end");
			return "success";
			
		}catch(Exception e){
			logger.warn(e);
			this.getRequest().setAttribute("descripcionError", e.getMessage());
			logger.error("Error BuscarOtrosTitulosSpring() - doExecute :",e);
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
			logger.error("Error BuscarOtrosTitulosSpring() - cargarCampos :"+codCampo,e);
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
	 * Crear query otros titulos.
	 *
	 * @param formulario el formulario
	 * @param paginaActual el pagina actual
	 * @param numReg el num reg
	 * @param listaCentrosGestores 
	 * @return el otros titulos query
	 */
	private OtrosTitulosQuery crearQueryOtrosTitulos(OtrosTitulosForm formulario,String paginaActual,String numReg, List<CentroGestorBean> listaCentrosGestores) {
		//Recoger los datos del formulario
		
		String descripcion = formulario.getDescripcion();
		String campo = formulario.getCampo();
		String centroGestor = formulario.getCentroGestor();
		Integer idCentroGestor = null;
		if(centroGestor != null && !centroGestor.equals("") )
		{	
			idCentroGestor = Integer.valueOf(centroGestor);
		}	
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
		
		OtrosTitulosQuery otrosTitulosQuery = new OtrosTitulosQuery();
		if(paginaActual != null){
			otrosTitulosQuery.setMaxResults(tamanyoPaginacion);
			otrosTitulosQuery.setFirstResult(primerRegistro);
		}
		otrosTitulosQuery.setCalculateNumResults(true);
		//Comprobar los filtros para realizar la busqueda
			
		if(!StringUtils.isEmpty(descripcion)){
			try{
			
				otrosTitulosQuery.setDescripcion(descripcion.toUpperCase());
				otrosTitulosQuery.setDescripcionComparator(Comparator.LIKE_LEFT_RIGHT);
			
			}catch(Exception e){
				logger.error("Error BuscarOtrosTitulosSpring() - crearQueryOtrosTitulos- Descripcion :",e);
			}
		}
		
		CentroGestorQuery centroGestorQuery = new CentroGestorQuery();
		/*INI-TRA042*/
		if(idCentroGestor != null){
			centroGestorQuery.setId(idCentroGestor);
		} else {
			if(listaCentrosGestores != null && listaCentrosGestores.size()>0) {
				for(CentroGestorBean cg: listaCentrosGestores) {
					centroGestorQuery.addIdCentroGestorIn(cg.getId());
				}
			}
		}
		otrosTitulosQuery.setCentroGestor(centroGestorQuery);
							
		if(campo != null && !"0".equals(campo) && !"".equals(campo)){
			crearQueryOtrosTitulosAux(otrosTitulosQuery,campo,direccion,centroGestorQuery);
		}	
		/*FIN-TRA042*/
		//Devuelve la convocatoria con los filtros
		return otrosTitulosQuery;
		
	}

	/**
	 * Crear query otros titulos aux.
	 *
	 * @param otrosTitulosQuery el otros titulos query
	 * @param campo el campo
	 * @param direccion el direccion
	 * @param centroGestorQuery el centro gestor query
	 */
	private void crearQueryOtrosTitulosAux(OtrosTitulosQuery otrosTitulosQuery, String campo, String direccion, CentroGestorQuery centroGestorQuery) {

		OrderType orden = ("up".equals(direccion)==true ? OrderType.ASC:OrderType.DESC);
		if("centroGestor".equals(campo)){
			centroGestorQuery.addOrder(STRINGDESCRIPCION, orden);
			otrosTitulosQuery.setCentroGestor(centroGestorQuery);
		}else{
			otrosTitulosQuery.addOrder(campo, orden);
		}	
		
	}
	
//	private int numRegistros(String numReg,int numRegistros, OtrosTitulosForm formulario) {
//		if(numReg != null && !"".equals(numReg)){
//			try{
//			numRegistros = Integer.parseInt(numReg);
//			}catch(Exception e){
//				logger.error("Error BuscarOtrosTitulosSpring() - crearQueryOtrosTitulos- numRegistros :"+numRegistros,e);
//				numRegistros = Integer.parseInt(Constantes.NUMERO_REGISTROS_PAGINA);
//			}
//		}else{
//			try{
//			numRegistros = Integer.parseInt(formulario.getNumRegistro());
//			}catch(Exception e){
//				logger.error("Error BuscarOtrosTitulosSpring() - crearQueryOtrosTitulos- numRegistros :"+numRegistros,e);
//				numRegistros = Integer.parseInt(Constantes.NUMERO_REGISTROS_PAGINA);
//			}
//		}
//		if(numRegistros > 50){
//			numRegistros = 50;
//			formulario.setNumRegistro(String.valueOf(numRegistros));
//		}else{
//			formulario.setNumRegistro(String.valueOf(numRegistros));
//		}
//		return numRegistros;
//	}
	
	/**
 * Obtiene el otros titulos manager.
 *
 * @return el otros titulos manager
 */
public OtrosTitulosManager getOtrosTitulosManager() {
		return otrosTitulosManager;
	}

	/**
	 * Establece el otros titulos manager.
	 *
	 * @param otrosTitulosManager el nuevo otros titulos manager
	 */
	public void setOtrosTitulosManager(OtrosTitulosManager otrosTitulosManager) {
		this.otrosTitulosManager = otrosTitulosManager;
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
