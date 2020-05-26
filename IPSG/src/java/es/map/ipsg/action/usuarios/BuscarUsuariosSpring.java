package es.map.ipsg.action.usuarios;

import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import es.map.ips.common.model.OrderType;
import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.CentroGestorQuery;
import es.map.ips.model.PerfilQuery;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.bean.CentroGestorBean;
import es.map.ipsg.bean.PerfilBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.form.BuscarUsuariosForm;
import es.map.ipsg.manager.CentroGestorManager;
import es.map.ipsg.manager.PerfilManager;
import es.map.ipsg.manager.UsuarioCentrogestorManager;
import es.map.ipsg.manager.UsuarioManager;
import es.map.ipsg.util.Constantes;

/**
 * El Class BuscarUsuariosSpring.
 */
public class BuscarUsuariosSpring extends AbstractSpring {
	
	/** La constante BUNDLE_RESOURCE. */
	private static final String BUNDLE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_RESOURCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(BuscarUsuariosSpring.class);
	
	/** el properties. */
	private static Properties properties;

	/** el usuario manager. */
	private UsuarioManager usuarioManager;
	
	/** el perfil manager. */
	private PerfilManager perfilManager;
	
	/** el centro gestor manager. */
	private CentroGestorManager centroGestorManager;
	
	/** el usuario centro gestor manager. */
	private UsuarioCentrogestorManager usuarioCentrogestorManager;
	
	/** el num reg. */
	private String paginaActual, accion,numReg;
	
	/**
	 * Crea una nueva buscar usuarios spring.
	 */
	public BuscarUsuariosSpring() {
		try {
			setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
			setPerfilManager((PerfilManager) getBean("perfilManager"));
			setCentroGestorManager((CentroGestorManager) getBean("centrosGestoresManager"));
			setUsuarioCentrogestorManager((UsuarioCentrogestorManager) getBean("usuarioCentrogestorManager"));
			properties = (Properties) getBean("propertiesBean");
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error BuscarUsuariosSpring - crear constructor",e);
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		getLogger().debug("BuscarUsuariosSpring -start");
		logger.info("Entra en el Action BuscarUsuarios");
		
		try{
			//*********** METO EL PARAMETRO QUE ME INDICA EN QUE MENU ESTOY **********	
			String menu_usuarios = RESOURCE_BUNDLE.getString("field.menu.usuarios");
			this.getRequest().getSession().setAttribute("pagActiva",menu_usuarios);
			String subMenu_usuarios = RESOURCE_BUNDLE.getString("field.menuLateral.usuarios.buscar");
			this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_usuarios);
			//****************************************************************** 
			
			String sUsernameLogin = "";
			UsuarioBean usuarioSession = (UsuarioBean) getRequest().getSession().getAttribute("usuario");
			sUsernameLogin = usuarioSession.getLogin();
			
			//Obtenemos el Usuario que está logueado en la aplicación para insertarlo en el log
			UsuarioQuery usuarioQuery2 = new UsuarioQuery();
			usuarioQuery2.setLogin(sUsernameLogin); //Le pasamos el login del que está logueado

			UsuarioBean usuarioBean = usuarioManager.buscarUsuario(usuarioQuery2);
			Integer idUsuario = usuarioBean.getId();
			
			BuscarUsuariosForm formulario = (BuscarUsuariosForm) form;
			//Cargar Combos de Busqueda
			PerfilQuery perfilQuery = new PerfilQuery();
			List<PerfilBean> perfiles = perfilManager.buscarPerfiles(perfilQuery);
			String cambios = this.getRequest().getParameter("cambios");
			numReg = this.getRequest().getParameter("numRegistro");
			logger.info("NumRegistros: "+numReg);
			CentroGestorQuery centroGestorQuery = new CentroGestorQuery();
			List<CentroGestorBean> centrosGestores = centroGestorManager.buscarCentroGestorCombo(centroGestorQuery);
			
			
			//Indicamos si viene desde el enlace del menú para mostrar los resultados de búsqueda o no 
			//y guardar la descripción para el formulario de búsqueda
			String sVieneMenu = this.getRequest().getParameter("menu"); 
			if (sVieneMenu != null && sVieneMenu.equals("S"))
			{
				//Poner todos los campos del formulario en blanco			
				formulario.setIdCentroGestor("");			
				formulario.setDsCentroGestor("");
				formulario.setIdPerfil("");
				formulario.setEstado("");
				formulario.setNif("");//añadido
				formulario.setLogin("");//añadido
				formulario.setEmail("");//añadido
				formulario.setAccion("");
				
			}else //Si viene a null es que viene de las demás páginas que no son del menú principal
			{
				sVieneMenu = "N";
			}
			//FIN Limpiado de formulario y muestra de resultados.
			
			
			setRequestAttribute("centrosGestores",centrosGestores);
			setRequestAttribute("perfiles",perfiles);
			
			String estado = formulario.getEstado();
			String idCentroGestor = formulario.getIdCentroGestor();
			String idPerfil = formulario.getIdPerfil();
			String nif = formulario.getNif();//añadido
			String login = formulario.getLogin();//añadido
			String email = formulario.getEmail();//añadido
			
			accion = formulario.getAccion();
			
			String sPerfilUsuario = "";
			sPerfilUsuario = comprobarPerfilUsuario(idUsuario);
			
			//Coger la pagina Actual y paginas totales
			paginaActual = this.getRequest().getParameter("paginaActual");
			String paginaTotal = this.getRequest().getParameter("paginasTotales");
			if(this.getRequest().getParameter("llamada") != null){
				accion = this.getRequest().getParameter("llamada");
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
			
			logger.info("PaginaActual: "+paginaActual);
			logger.info("PaginasTotales: "+paginaTotal);
			if(paginaActual != null && !"".equals(paginaActual) && paginaTotal != null && !"".equals(paginaTotal)){
				int pagActu = Integer.parseInt(paginaActual);		
				int pagTotales = Integer.parseInt(paginaTotal);
		
					if(pagActu > pagTotales){
						paginaActual = String.valueOf(pagTotales);
					}
			}
			logger.info("accion: "+accion);
				
			if((accion!=null) && !("".equals(accion))){
				//Buscar Usuarios segun Criterios de Busqueda
				logger.info("Buscando Usuarios");
				
				//////////////////////////////////////Codigo para la paginacion
				
				//Establecer valor para el numero de registros mostrados
				if(formulario.getNumRegistro() == null && numReg == null){
					//Para el primer paso para el action, se establece que los registros por pagina son 5
					formulario.setNumRegistro(Constantes.NUMERO_REGISTROS_PAGINA);
				}
				int numRegistros = 0;
				if(numReg != null && !"".equals(numReg)){
					try{
					numRegistros = Integer.parseInt(numReg);
					}catch(Exception e){
						logger.error("Error BuscarUsuariosSpring - numRegistros"+ numRegistros,e);
						numRegistros = Integer.parseInt(Constantes.NUMERO_REGISTROS_PAGINA);
					}
				}else{
					try{
					numRegistros = Integer.parseInt(formulario.getNumRegistro());
					}catch(Exception e){
						logger.error("Error BuscarUsuariosSpring - numRegistros"+ numRegistros,e);
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
				if("0".equals(paginaActual) || paginaActual == null || "".equals(paginaActual) || "Buscar".equals(accion)){
					paginaActual = properties.getProperty("conf.primerPagina");
					formulario.setPaginaActual(paginaActual);
					logger.info("PaginaActualForzada: "+paginaActual);
					cambios = "Correcto";
				}
				//Calcular el numero de lineas de cada paginacion
				int tamanyoPaginacionReal = numRegistros;
				int tamanyoPaginacion = tamanyoPaginacionReal /*+1*/;
				int numPagina = Integer.parseInt(paginaActual);
				int primerRegistro = (numPagina*tamanyoPaginacionReal)-tamanyoPaginacionReal;
				String campo = obtenerCampos(formulario.getCampo());
				OrderType orden = obtenerOrden(formulario.getDireccion());
				boolean ordernarPorCampos = "ORDENAR".equalsIgnoreCase(accion) || "Paginar".equalsIgnoreCase(accion.toUpperCase());			
				
				UsuarioQuery usuarioQuery = new UsuarioQuery();
				if(paginaActual != null){
					usuarioQuery.setMaxResults(tamanyoPaginacion);
					logger.info("PaginacionMax: "+ tamanyoPaginacion);
					usuarioQuery.setFirstResult(primerRegistro);
					logger.info("PaginacionMin: "+primerRegistro);
				}
				usuarioQuery.setCalculateNumResults(true);
				
				if(estado!=null && !estado.equals("")){
					usuarioQuery.setEstado(estado.charAt(0));
				}
				
				// Si estamos buscando por centro, añadimos el id del centro
				if(idCentroGestor!=null && !idCentroGestor.equals("")){
					centroGestorQuery.setId(Integer.valueOf(idCentroGestor));
					usuarioQuery.setCentroGestor(centroGestorQuery);
				} 
				//----Añadidos-----
				//Nif
				if (nif != null && !nif.equals("") ){
					usuarioQuery.setNif(nif);
				}
				//Login
				if (login != null && !login.equals("") ){
					usuarioQuery.setLogin(login);
				}
				//Email
				if (email != null && !email.equals("") ){
					usuarioQuery.setEmail(email);
				}
				//-----------------
				// No hacemos la ordenación por centro por el problema de los campos a null
				// Si estamos ordenando por centro, añadimor el orden de la descripción del centro.

				
				// Si estamos buscando por perfil, añadimos el id del perfil
				if(idPerfil!=null && !idPerfil.equals("")) {
					perfilQuery.setId(Integer.valueOf(idPerfil));
					usuarioQuery.setPerfil(perfilQuery);
				}
				// Si estamos ordenando por perfil, añadimor el orden de la descripción del perfil.
				if(ordernarPorCampos &&  UsuarioQuery.PERFIL.equals(campo)){
					perfilQuery.addOrder(PerfilQuery.DESCRIPCION, orden);
					usuarioQuery.setPerfil(perfilQuery);
				}		
				
		
				
				// No hacemos la ordenación por centro por el problema de los campos a null
				if (ordernarPorCampos && !UsuarioQuery.PERFIL.equals(campo) && !"".equals(campo)) {
					usuarioQuery.addOrder(campo,orden);
				}
				
				if(usuarioQuery.getOrders()!=null && usuarioQuery.getOrders().size()==0){
					usuarioQuery.addOrder(UsuarioQuery.ID,OrderType.ASC);
				}
				
				usuarioQuery.setNifProcesoAutomatico(properties.getProperty("log.usuario.automatico.nif"));
				
				List<UsuarioBean> usuarios = usuarioManager.buscarUsuarios(usuarioQuery);
				/*INI-TRA042*/
				cargarCentrosgestores(usuarios);
				/*FIN-TRA042*/
				logger.info("Usuarios: "+usuarios.size());
				int pagTotal = 0, numPaginas=0;
				
				if(usuarios != null && usuarios.size()!= 0){
					pagTotal= usuarios.get(0).getNumTotal();
					logger.info("pagTotal: "+pagTotal);
					numPaginas = pagTotal / numRegistros;
					logger.info("NumPaginas: "+numPaginas);
					int resto = pagTotal % numRegistros;
					if(resto > 0){
						numPaginas++;
					}
					if(usuarios.size()>numRegistros){
						usuarios.remove(usuarios.size()-1);
					}
				}
				setRequestAttribute("usuarios",usuarios);
				setRequestAttribute("paginaActual", paginaActual);
				setRequestAttribute("paginasTotales", numPaginas);	
				setRequestAttribute("cambios", cambios);
				setRequestAttribute("submit", accion);
			}
			setRequestAttribute("sPerfilUsuario", sPerfilUsuario);
			getLogger().debug("BuscarUsuariosSpring -end");		
			return "success";
			
		}catch (Exception e){
			logger.warn(e);
			this.getRequest().setAttribute("descripcionError", e.getMessage());
			logger.error("Error BuscarUsuariosSpring - doExecute ",e);
			return "nosuccess";
		}
	}
	
	/*INI-TRA042*/
	private void cargarCentrosgestores(List<UsuarioBean> usuarios) {
		for(UsuarioBean usuario: usuarios) {
			List<CentroGestorBean> listaCentrosGestores = usuarioCentrogestorManager.buscarCentrosGestoresByIdUsuario(usuario.getId());
			String listaCGconcat="";
			for(CentroGestorBean cg: listaCentrosGestores) {
				listaCGconcat+=cg.getDescripcion()+"\n";
			}
			usuario.setListaCGconcat(listaCGconcat);
		}
	}
	/*FIN-TRA042*/
	
	/**
	 * Comprobar perfil usuario.
	 *
	 * @param idUsuario el id usuario
	 * @return el string
	 */
	private String comprobarPerfilUsuario(Integer idUsuario) {
		String sPerfil = "";
		UsuarioBean usuarioBean;
		
		usuarioBean = usuarioManager.obtenerUsuario(idUsuario);
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
	
	/**
	 * Obtener campos.
	 *
	 * @param campo el campo
	 * @return el string
	 */
	private String obtenerCampos(String campo) {
		int codCampo = 0;
		try{
			codCampo = Integer.parseInt(campo);
		}catch(Exception e){
			logger.error("Error BuscarUsuariosSpring - obtenerCampos - campo "+ codCampo,e);
		}
		
		if (campo != null && !"".equals(campo) && !campo.equals("null")){
			switch(codCampo){
			//Los campos de ordenacion del jsp
				case 1: return UsuarioQuery.ID;
				case 2: return UsuarioQuery.NIF;
				case 3: return UsuarioQuery.LOGIN;
				case 4: return UsuarioQuery.NOMBRE;
				case 5: return UsuarioQuery.PRIMERAPELLIDO;
				case 6: return UsuarioQuery.SEGUNDOAPELLIDO;
				case 7: return UsuarioQuery.CENTROGESTOR;
				case 8: return UsuarioQuery.PERFIL;
				case 9: return UsuarioQuery.EMAIL;//añadido
				default: break;
			}
		}
		return campo;
	}		
	
	/**
	 * Obtener orden.
	 *
	 * @param direccion el direccion
	 * @return el order type
	 */
	public OrderType obtenerOrden(String direccion){
		if(direccion.equals("up")){
			return OrderType.ASC;
		}else{
			return OrderType.DESC;
		}
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
	 * Obtiene el perfil manager.
	 *
	 * @return el perfil manager
	 */
	public PerfilManager getPerfilManager() {
		return perfilManager;
	}

	/**
	 * Establece el perfil manager.
	 *
	 * @param perfilManager el nuevo perfil manager
	 */
	public void setPerfilManager(PerfilManager perfilManager) {
		this.perfilManager = perfilManager;
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
