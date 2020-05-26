package es.map.ipsg.action.incidencia;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import es.map.ips.common.model.OrderType;
import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;


import es.map.ips.model.CiudadanoQuery;
import es.map.ips.model.CorreoElectronicoQuery;
import es.map.ips.model.IncidenciaQuery;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.bean.IncidenciasBean;
import es.map.ipsg.form.BuscarIncidenciasForm;
import es.map.ipsg.manager.IncidenciasManager;
import es.map.ipsg.util.Constantes;

/**
 * El Class BuscarIncidenciasSpring.
 */
public class BuscarIncidenciasSpring extends AbstractSpring {

	/** La constante MESSAGE_RESOUCE. */
	//Declarar los resource
	private static final String MESSAGE_RESOUCE = "MessageResources";
	
	/** La constante RESOURCE_MESSAGE. */
	private static final ResourceBundle RESOURCE_MESSAGE = ResourceBundle.getBundle(MESSAGE_RESOUCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(BuscarIncidenciasSpring.class);
	
	/** el properties. */
	private static Properties properties;

	//Declaracion de manager

	/** el incidencias manager. */
	private IncidenciasManager incidenciasManager;	
	
	/**
	 * Crea una nueva buscar incidencias spring.
	 */
	public BuscarIncidenciasSpring() {
		try {
			setIncidenciasManager((IncidenciasManager) getBean("incidenciasManager"));
			properties = (Properties) getBean("propertiesBean");
			
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error BuscarIncidenciasSpring:",e);
		}
		
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		getLogger().debug("BuscarIncidenciasSpring -start");
		//*********** METO EL PARAMETRO QUE ME INDICA EN QUE MENU ESTOY **********	
		String menu_consultas = RESOURCE_MESSAGE.getString("field.menu.consultas");
		this.getRequest().getSession().setAttribute("pagActiva",menu_consultas);
		String subMenu_incidencias = RESOURCE_MESSAGE.getString("field.menuLateral.consultas.incidencias");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_incidencias);
		//****************************************************************** 
		
		try{
			//Coger el form del jsp
			BuscarIncidenciasForm formulario;
			formulario = (BuscarIncidenciasForm) form;
			String busqueda = formulario.getSubmit();	
			if(this.getRequest().getParameter("llamada") != null){
				busqueda = this.getRequest().getParameter("llamada");
			}
			String numReg = this.getRequest().getParameter("numRegistro");
			logger.info("NumRegistros: "+numReg);
			String cambios = this.getRequest().getParameter("cambios");		
			
			//Comprobar si viene del menu
			String menu = this.getRequest().getParameter("menu");
			if(menu != null && menu.equals("S")){
				formulario.setNif("");
				formulario.setFechaDesde("");
				formulario.setFechaHasta("");				
				busqueda = "";
			}
			
			//Creamos la Query
			IncidenciaQuery incidenciaQuery;
			//Si ha seleccionado un campo de ordenacion se le asigna el atributo de ordenacion
			if(StringUtils.isNotEmpty(formulario.getCampo()) && !"Paginar".equals(busqueda)){
				try{
					String campo=cargarCampos(formulario.getCampo());
					formulario.setCampo(campo);
				}catch(Exception e){
					logger.error("Error BuscarIncidenciasSpring - CargarCampo - campo:"+ formulario.getCampo(),e);
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
			if("0".equals(paginaActual) || paginaActual == null || "".equals(paginaActual)){
				paginaActual = "1";
				formulario.setPaginaActual(paginaActual);
			}
	
			incidenciaQuery=crearQueryIncidencia(formulario,paginaActual,numReg);
			
			//Coger la lista de convocatorias a mostrar
		
			List<IncidenciasBean> incidencia = null;
			if("Buscar".equals(busqueda) || "Ordenar".equals(busqueda) || "Paginar".equals(busqueda)){
				incidencia=incidenciasManager.buscarIncidenciasFiltro(incidenciaQuery);
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
			if(incidencia != null && incidencia.size()!= 0){
				pagTotal= incidencia.get(0).getNumTotal();
				numPaginas = pagTotal / numRegistros;
				int resto = pagTotal % numRegistros;
				if(resto > 0){
					numPaginas++;
				}
				if(incidencia.size()>numRegistros){
					incidencia.remove(incidencia.size()-1);
				}
				
			} 
			
			//Pasar la convocatoria y la pagina al jsp
			if(paginaActual==null || "".equals(paginaActual))
				paginaActual="1";
						
			setRequestAttribute("incidencias", incidencia);
			setRequestAttribute("cambios", cambios);
			setRequestAttribute("paginaActual", paginaActual);
			setRequestAttribute("paginasTotales", numPaginas);	
			return "success";
		}catch (Exception e){
			logger.warn(e);
			this.getRequest().setAttribute("descripcionError", e.getMessage());
			logger.error("Error BuscarIncidenciasSpring -  doExecute",e);
			return "nosuccess";
		}
	}
	
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
			logger.error("Error comprobarEntero - numRegistros:"+ resultado,e);
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
			logger.error("Error BuscarIncidenciasSpring - cargarcampos- campo:"+ codCampo,e);
		}
			if (campo != null && !"".equals(campo) && !campo.equals("null")){
				
					switch(codCampo){
					//Los campos de ordenacion del jsp
						case 1:auxCampo = "id";break;
						case 2:auxCampo = "nif";break;
						case 3:auxCampo = "nombre";break;
						case 4:auxCampo = "primerApellido";break;
						case 5:auxCampo = "segundoApellido";break;
						case 6:auxCampo = "fechaEnvio";break;
						default:break;
						}
					
				}
			return auxCampo;
			}
			
	

	/**
	 * Crear query incidencia.
	 *
	 * @param formulario el formulario
	 * @param paginaActual el pagina actual
	 * @param numReg el num reg
	 * @return el incidencia query
	 */
	private IncidenciaQuery crearQueryIncidencia(BuscarIncidenciasForm formulario,String paginaActual,String numReg) {
		//Recoger los datos del formulario
		
		
		String nif=formulario.getNif();
		String fechaDesde=formulario.getFechaDesde();
		String fechaHasta=formulario.getFechaHasta();
		String tipo=formulario.getTipo();
		String campo = formulario.getCampo();
		String direccion = formulario.getDireccion();
		

		
		if(formulario.getNumRegistro() == null && numReg == null){
			//Para el primer paso para el action, se establece que los registros por pagina son 5
			formulario.setNumRegistro(Constantes.NUMERO_REGISTROS_PAGINA);
		}
		int numRegistros = 0;
		if(numReg != null && !"".equals(numReg)){
			try{
			numRegistros = Integer.parseInt(numReg);
			}catch(Exception e){
				logger.error("Error BuscarIncidenciasSpring - CrearQueryIncidencia - numRegistro:"+ numRegistros,e);
				numRegistros = Integer.parseInt(Constantes.NUMERO_REGISTROS_PAGINA);
			}
		}else{
			try{
			numRegistros = Integer.parseInt(formulario.getNumRegistro());
			}catch(Exception e){
				logger.error("Error BuscarIncidenciasSpring - CrearQueryIncidencia - numRegistro:"+ numRegistros,e);
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
		if("0".equals(paginaActual) || paginaActual == null || "".equals(paginaActual)){
			paginaActual = properties.getProperty("conf.primerPagina");
			formulario.setPaginaActual(paginaActual);
		}
		//Calcular el numero de lineas de cada paginacion
		int tamanyoPaginacionReal = numRegistros;
		int tamanyoPaginacion = tamanyoPaginacionReal+1;
		int numPagina = Integer.parseInt(paginaActual);
		int primerRegistro = (numPagina*tamanyoPaginacionReal)-tamanyoPaginacionReal;
		
		IncidenciaQuery incidenciaQuery = new IncidenciaQuery();
		if(paginaActual != null){
			incidenciaQuery.setMaxResults(tamanyoPaginacion);
			incidenciaQuery.setFirstResult(primerRegistro);
		}		
		incidenciaQuery.setCalculateNumResults(true);
		
		incidenciaQuery.setJoin_correoElectronico(true);
		//Comprobar los filtros para realizar la busqueda
		
	
			//Pregunto si el tipo es Ciudadano o Usuario

		CiudadanoQuery ciudadanoQuery= new CiudadanoQuery();
		UsuarioQuery usuarioQuery= new UsuarioQuery();
		Boolean usuario = false;
		Boolean  ciudadano = false;
		if (RESOURCE_MESSAGE.getString("field.incidencias.inicialCiudadano").equals(tipo)) {
			ciudadano = true;
		}
		else if (RESOURCE_MESSAGE.getString("field.incidencias.inicialUsuario").equals(tipo)) {
			usuario = true;
		}
		
		if (ciudadano == true) {			    
			if (nif!= null  && !"".equals(nif)){
				ciudadanoQuery.setNif(nif);
			}
			incidenciaQuery.setCiudadano(ciudadanoQuery);				
		} else if (usuario == true){
			if (nif!= null  && !"".equals(nif)) {
				usuarioQuery.setNif(nif);					
			}
			incidenciaQuery.setUsuario(usuarioQuery);
		}
			
			
		
		
		//Compruebo si las fechas están rellenas
		CorreoElectronicoQuery correoElectronicoQuery= new CorreoElectronicoQuery();
		SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
		
		if (fechaDesde !=null && !"".equals(fechaDesde)){
			try{	
				Date dFechad=formatoDelTexto.parse(fechaDesde);
				correoElectronicoQuery.setFechaEnvioMin(dFechad);
				}
			catch(Exception e){
				logger.error("Error BuscarIncidenciasSpring - Error parseado fechaDesde:"+ fechaDesde,e);
			}
		}
		if (fechaHasta !=null && !"".equals(fechaHasta)){
			try{
				Date dFechah=formatoDelTexto.parse(fechaHasta);
				correoElectronicoQuery.setFechaEnvioMax(dFechah);
				}
			catch(Exception e){	
				logger.error("Error BuscarIncidenciasSpring - Error parseado fechaHasta:"+ fechaHasta,e);
			}
		}
			
		
		if(campo != null && !"0".equals(campo) && !"".equals(campo)) {
			// Obtenemos si vamos a ordenar ascendentemente o descendentemente
			OrderType orden = ("up".equals(direccion) == true ? OrderType.ASC : OrderType.DESC);
			if ("nif".equals(campo)) {
				if (usuario == true) {
					usuarioQuery.addOrder(UsuarioQuery.NIF, orden);
					incidenciaQuery.setUsuario(usuarioQuery);
				}
				else if (ciudadano == true) {
					ciudadanoQuery.addOrder(CiudadanoQuery.NIF, orden);
					incidenciaQuery.setCiudadano(ciudadanoQuery);
				}				
			} 
			else if ("nombre".equals(campo)) {
				if (usuario == true) {
					usuarioQuery.addOrder(UsuarioQuery.NOMBRE, orden);
					incidenciaQuery.setUsuario(usuarioQuery);
				}
				else if (ciudadano == true) {
					ciudadanoQuery.addOrder(CiudadanoQuery.NOMBRE, orden);
					incidenciaQuery.setCiudadano(ciudadanoQuery);
				}					
			}
			else if ("primerApellido".equals(campo)) {
				if (usuario == true) {
					usuarioQuery.addOrder(UsuarioQuery.PRIMERAPELLIDO, orden);
					incidenciaQuery.setUsuario(usuarioQuery);
				}
				else if (ciudadano == true) {
					ciudadanoQuery.addOrder(CiudadanoQuery.PRIMERAPELLIDO, orden);
					incidenciaQuery.setCiudadano(ciudadanoQuery);
				}	
			}
			else if ("segundoApellido".equals(campo)) {
				if (usuario == true) {
					usuarioQuery.addOrder(UsuarioQuery.SEGUNDOAPELLIDO, orden);
					incidenciaQuery.setUsuario(usuarioQuery);
				}
				else if (ciudadano == true) {
					ciudadanoQuery.addOrder(CiudadanoQuery.SEGUNDOAPELLIDO, orden);
					incidenciaQuery.setCiudadano(ciudadanoQuery);
				}
			}
			else if ("fechaEnvio".equals(campo)) {
				correoElectronicoQuery.addOrder(campo,orden);
			} else {							
				incidenciaQuery.addOrder(campo,orden);				
			}
		}
		else{ //Por defecto ordenamos por el primer campo ascendente
			incidenciaQuery.addOrder("id",OrderType.ASC);
		}
		
		
		incidenciaQuery.setCorreoElectronico(correoElectronicoQuery);	
		
		//Devulelve la convocatoria con los filtros
		return incidenciaQuery;
		
	}






	/**
	 * Obtiene el incidencias manager.
	 *
	 * @return the incidenciasManager
	 */
	public IncidenciasManager getIncidenciasManager() {
		return incidenciasManager;
	}

	/**
	 * Establece el incidencias manager.
	 *
	 * @param incidenciasManager the incidenciasManager to set
	 */
	public void setIncidenciasManager(IncidenciasManager incidenciasManager) {
		this.incidenciasManager = incidenciasManager;
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
