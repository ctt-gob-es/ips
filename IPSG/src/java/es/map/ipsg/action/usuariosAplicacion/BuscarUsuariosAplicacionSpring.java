package es.map.ipsg.action.usuariosAplicacion;

import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import es.map.ips.common.model.Comparator;
import es.map.ips.common.model.OrderType;
import es.map.ips.common.spring.*;
import es.map.ips.model.*;
import es.map.ipsg.bean.*;
import es.map.ipsg.form.BuscarUsuariosAplicacionForm;
import es.map.ipsg.manager.*;
import es.map.ipsg.util.Constantes;

/**
 * El Class BuscarUsuariosAplicacionSpring.
 */
public class BuscarUsuariosAplicacionSpring extends AbstractSpring{
	
	/** La constante MESSAGE_RESOUCE. */
	private static final String MESSAGE_RESOUCE = "MessageResources";
	
	/** La constante RESOURCE_MESSAGE. */
	private static final ResourceBundle RESOURCE_MESSAGE = ResourceBundle.getBundle(MESSAGE_RESOUCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(BuscarUsuariosAplicacionSpring.class);
	
	/** el properties. */
	private static Properties properties;
	
	/** el usuario aplicacion manager. */
	private UsuarioAplicacionManager usuarioAplicacionManager;
	
	/** el pagina actual. */
	private String accion, paginaActual;
	
	/**
	 * Crea una nueva buscar usuarios aplicacion spring.
	 */
	public BuscarUsuariosAplicacionSpring() {
		try {
			setUsuarioAplicacionManager((UsuarioAplicacionManager) getBean("usuarioAplicacionManager"));
			properties = (Properties) getBean("propertiesBean");
		}catch (Exception e) {
			logger.warn(e);
			logger.error("Error BuscarUsuariosAplicacionSpring - crear constructor",e);
		}
	}
	
	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		getLogger().debug("BuscarUsuariosAplicacionSpring -start");
		logger.info("Entra en el Action BuscarUsuariosAplicacion");
		
		
		//*********** METO EL PARAMETRO QUE ME INDICA EN QUE MENU ESTOY **********
		String menu_tablaBase = RESOURCE_MESSAGE.getString("field.menu.tablasBase");
		this.getRequest().getSession().setAttribute("pagActiva", menu_tablaBase);
		String subMenu_usuario = RESOURCE_MESSAGE.getString("field.menuLateral.tablasBase.usuario");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_usuario);
		//******************************************************************
		
		
		try {
			//Coger el form de jsp
			BuscarUsuariosAplicacionForm formulario = (BuscarUsuariosAplicacionForm) form;
											
			//Llama a la funcion para pasar al form
			String numReg = this.getRequest().getParameter("numRegistro");
			logger.info("NumRegistros: " + numReg);
			String cambios = this.getRequest().getParameter("cambios");
			
			//Comprobar si viene del menu
			String menu = this.getRequest().getParameter("menu");
			if(menu != null && menu.equals("S")){
				formulario.setUsuario("");
				formulario.setNombre("");
				formulario.setDescripcion("");
				formulario.setEstado("");
			}else {
				menu = "N";
			}
			
			String usuario = formulario.getUsuario();
			String nombre = formulario.getNombre();
			String descripcion = formulario.getDescripcion();
			String estado = formulario.getEstado();
			
			accion = formulario.getAccion();
			
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
						logger.error("Error BuscarUsuariosAplicacionSpring - numRegistros"+ numRegistros,e);
						numRegistros = Integer.parseInt(Constantes.NUMERO_REGISTROS_PAGINA);
					}
				}else{
					try{
					numRegistros = Integer.parseInt(formulario.getNumRegistro());
					}catch(Exception e){
						logger.error("Error BuscarUsuariosAplicacionSpring - numRegistros"+ numRegistros,e);
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
				
				UsuarioAplicacionQuery usuarioAplicacionQuery = new UsuarioAplicacionQuery();
				if(paginaActual != null){
					usuarioAplicacionQuery.setMaxResults(tamanyoPaginacion);
					logger.info("PaginacionMax: "+ tamanyoPaginacion);
					usuarioAplicacionQuery.setFirstResult(primerRegistro);
					logger.info("PaginacionMin: "+primerRegistro);
				}
				usuarioAplicacionQuery.setCalculateNumResults(true);
				
				if(estado!=null && !estado.equals("")){
					usuarioAplicacionQuery.setEstado(estado.charAt(0));
				}
				
				//----Añadidos-----
				//Usuario
				if (usuario != null && !usuario.equals("")){
					try {
					usuarioAplicacionQuery.setUsuarioAplicacion(usuario);
					usuarioAplicacionQuery.setUsuarioAplicacionComparator(Comparator.LIKE_LEFT_RIGHT);
					}catch(Exception e){
						logger.error("Error BuscarUsuariosAplicacionSpring() - crearQueryUsuarioAplicacion- Usuario :",e);
					}
				}
				//Nombre
				if (nombre != null && !nombre.equals("") ){
					try {
					usuarioAplicacionQuery.setNombreAplicacion(nombre.toUpperCase());
					usuarioAplicacionQuery.setNombreAplicacionComparator(Comparator.LIKE_LEFT_RIGHT);
					}catch(Exception e){
						logger.error("Error BuscarUsuariosAplicacionSpring() - crearQueryUsuarioAplicacion- Nombre :",e);
					}
				}
				//Descripcion
				if (descripcion != null && !descripcion.equals("") ){
					try{
					usuarioAplicacionQuery.setDescripcionAplicacion(descripcion.toUpperCase());
					usuarioAplicacionQuery.setDescripcionAplicacionComparator(Comparator.LIKE_LEFT_RIGHT);
					}catch(Exception e){
						logger.error("Error BuscarUsuariosAplicacionSpring() - crearQueryUsuarioAplicacion- Descripcion :",e);
					}
				}
				//-----------------
								
				if(ordernarPorCampos && !"".equals(campo)) {
					usuarioAplicacionQuery.addOrder(campo,orden);
				}
				
				if(usuarioAplicacionQuery.getOrders()!=null && usuarioAplicacionQuery.getOrders().size()==0){
					usuarioAplicacionQuery.addOrder(UsuarioQuery.ID,OrderType.ASC);
				}
											
				List<UsuarioAplicacionBean> usuarios = usuarioAplicacionManager.buscarUsuariosAplicacion(usuarioAplicacionQuery);
				if(usuarios != null && usuarios.size() == 0) {
					SpringMessages errors = new SpringMessages();
					errors.add("errorSinResultados", new SpringMessage("field.usuariosaplicacion.error"));
					this.setRequestAttribute("org.apache.spring.ERROR", errors);
				}
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
				setRequestAttribute("usuarioAplicacion",usuarios);
				setRequestAttribute("paginaActual", paginaActual);
				setRequestAttribute("paginasTotales", numPaginas);
				setRequestAttribute("cambios", cambios);
				setRequestAttribute("submit", accion);
			}
			getLogger().debug("BuscarUsuariosAplicacionSpring -end");
			return "success";
															
		}catch (Exception e){
			logger.warn(e);
			this.getRequest().setAttribute("descripcionError", e.getMessage());
			logger.error("Error BuscarUsuariosAplicacionSpring - doExecute ",e);
			return "nosuccess";
		}
		
	}
	
	/**
	 * Obtener orden.
	 *
	 * @param direccion el direccion
	 * @return el order type
	 */
	private OrderType obtenerOrden(String direccion) {
		if(direccion.equals("up")){
			return OrderType.ASC;
		}else{
			return OrderType.DESC;
		}
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
			logger.error("Error BuscarUsuariosAplicacionSpring - obtenerCampos - campo "+ codCampo,e);
		}
		
		if (campo != null && !"".equals(campo) && !campo.equals("null")){
			switch(codCampo){
			//Los campos de ordenacion del jsp
				case 1: return UsuarioAplicacionQuery.IDUSUARIO;
				case 2: return UsuarioAplicacionQuery.USUARIOAPLICACION;
				case 3: return UsuarioAplicacionQuery.NOMBREAPLICACION;
				case 4: return UsuarioAplicacionQuery.DESCRIPCIONAPLICACION;
				case 5: return UsuarioAplicacionQuery.ESTADO;
				default: break;
			}
		}
		return campo;
	}
	
	/**
	 * Obtiene el usuario aplicacion manager.
	 *
	 * @return the especialidadManager
	 */
	public UsuarioAplicacionManager getUsuarioAplicacionManager() {
		return usuarioAplicacionManager;
	}

	/**
	 * Establece el usuario aplicacion manager.
	 *
	 * @param usuarioAplicacionManager el nuevo usuario aplicacion manager
	 */
	public void setUsuarioAplicacionManager(
			UsuarioAplicacionManager usuarioAplicacionManager) {
		this.usuarioAplicacionManager = usuarioAplicacionManager;
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
