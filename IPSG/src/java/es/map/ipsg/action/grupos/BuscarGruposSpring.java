package es.map.ipsg.action.grupos;

import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import es.map.ips.common.model.Comparator;
import es.map.ips.common.model.OrderType;
import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringMessage;
import es.map.ips.common.spring.SpringMessages;
import es.map.ips.model.GrupoQuery;
import es.map.ipsg.bean.GrupoBean;
import es.map.ipsg.form.GrupoForm;
import es.map.ipsg.manager.GrupoManager;
import es.map.ipsg.util.Constantes;

/**
 * El Class BuscarGruposSpring.
 */
public class BuscarGruposSpring extends AbstractSpring {
	
	/** el grupo manager. */
	GrupoManager grupoManager;
	
	/** La constante MESSAGE_RESOUCE. */
	private static final String MESSAGE_RESOUCE = "MessageResources";
	
	/** La constante RESOURCE_MESSAGE. */
	private static final ResourceBundle RESOURCE_MESSAGE = ResourceBundle.getBundle(MESSAGE_RESOUCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(BuscarGruposSpring.class);
	
	/** La constante STRING_BUSCAR. */
	private static final String STRING_BUSCAR = "Buscar";
	
	/** el properties. */
	private static Properties properties;
	
	/**
	 * Crea una nueva buscar grupos spring.
	 */
	public BuscarGruposSpring() {
		try {
			setGrupoManager((GrupoManager) getBean("grupoManager"));
			properties = (Properties) getBean("propertiesBean");
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error BuscarGruposSpring:",e);
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		
		String menu_tablaBase = RESOURCE_MESSAGE.getString("field.menu.tablasBase");
		this.getRequest().getSession().setAttribute("pagActiva", menu_tablaBase);
		String subMenu_grupo = RESOURCE_MESSAGE.getString("field.menuLateral.tablasBase.grupo");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_grupo);
		
		getLogger().debug("BuscarGruposSpring -start");
		GrupoForm formulario;
		formulario = (GrupoForm) form;
		
		try{
		
			String cambios = this.getRequest().getParameter("cambios");
			String paginaActual = this.getRequest().getParameter("paginaActual");
			String numReg = this.getRequest().getParameter("numRegistro");
			logger.info("NumRegistros: "+numReg);
			String paginaTotal = this.getRequest().getParameter("paginasTotales");
			String busqueda = formulario.getSubmit();
			if(this.getRequest().getParameter("llamada") != null){
				busqueda = this.getRequest().getParameter("llamada");
			}
			
			// Si viene desde el menu borramos los datos previamente introducidos
			if ("S".equals(this.getRequest().getParameter("menu")))
			{
				formulario.setDescripcion("");	// Si viene del menu borramos todos sus campos
				busqueda = null;
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
			
			GrupoQuery grupoQuery;
			//Si ha seleccionado un campo de ordenacion se le asigna el atributo de ordenacion
			if(formulario.getCampo()!= null && !"Paginar".equals(busqueda)){
				try{
					String campo=cargarCampos(formulario.getCampo());
					formulario.setCampo(campo);
				}catch(Exception e){
					logger.error("Error BuscarGruposSpring - cargarCampos - campo:"+ formulario.getCampo(),e);
				}			
			}
			grupoQuery=crearQueryConvocatoria(formulario,paginaActual,numReg,busqueda);
			List<GrupoBean> grupos = null;
			if(STRING_BUSCAR.equals(busqueda) || "Ordenar".equals(busqueda) || "Paginar".equals(busqueda)){ 
				grupos = grupoManager.buscarGrupoAll(grupoQuery);
				//Error sin resultados
				if (grupos != null && grupos.size() == 0) {
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
			if(grupos != null && grupos.size()!= 0){
				pagTotal= grupos.get(0).getNumTotal();
				numPaginas = pagTotal / numRegistros;
				int resto = pagTotal % numRegistros;
				if(resto > 0){
					numPaginas++;
				}
				if(grupos.size()>numRegistros){
					grupos.remove(grupos.size()-1);
				}			
			}
			logger.info("Pagina Actual: "+paginaActual);
			logger.info("Paginas Totales: "+numPaginas);
			
			if(paginaActual==null || "".equals(paginaActual))
				paginaActual="1";
			setRequestAttribute("grupos", grupos);
			setRequestAttribute("paginaActual", paginaActual);
			setRequestAttribute("paginasTotales", numPaginas);	
			setRequestAttribute("submit", busqueda);
			setRequestAttribute("cambios", cambios);
			
			getLogger().debug("BuscarGruposSpring -end");
			return "success";
		}catch (Exception e){
			logger.warn(e);
			this.getRequest().setAttribute("descripcionError", e.getMessage());
			logger.error("Error BuscarGruposSpring - doExecute:",e);
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
			logger.error("Error comprobarEntero() - numRegistros:"+ resultado,e);
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
			logger.error("Error BuscarGruposSpring - cargarCampos - campo:"+ codCampo,e);
		}
			if (campo != null && !"".equals(campo) && !campo.equals("null")){
				
					switch(codCampo){
					//Los campos de ordenacion del jsp
						case 1:auxCampo = "id";break;
						case 2:auxCampo = "siglas";break;
						case 3:auxCampo = "descripcion";break;
						case 4:auxCampo = "codigo";break;
						case 5:auxCampo = "visible";break;
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
	 * @param numReg el num reg
	 * @param busqueda el busqueda
	 * @return el grupo query
	 */
	private GrupoQuery crearQueryConvocatoria(GrupoForm formulario,String paginaActual,String numReg,String busqueda) {
		
		String descripcion = formulario.getDescripcion();
		String campo = formulario.getCampo();
		String direccion = formulario.getDireccion();
		int numRegistros;
		if(formulario.getNumRegistro() == null && numReg == null){
			//Para el primer paso para el action, se establece que los registros por pagina son 5
			formulario.setNumRegistro(Constantes.NUMERO_REGISTROS_PAGINA);
		}
		if(numReg != null && !"".equals(numReg)){
			try{
			numRegistros = Integer.parseInt(numReg);
			}catch(Exception e){
				logger.error("Error BuscarGruposSpring - CrearQueryConvocatoria - numRegistros:"+ formulario.getNumRegistro(),e);
				numRegistros = Integer.parseInt(Constantes.NUMERO_REGISTROS_PAGINA);
			}
		}else{
			try{
			numRegistros = Integer.parseInt(formulario.getNumRegistro());
			}catch(Exception e){
				logger.error("Error BuscarGruposSpring - CrearQueryConvocatoria - numRegistros:"+ formulario.getNumRegistro(),e);
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
			paginaActual = properties.getProperty("conf.primerPagina");
			formulario.setPaginaActual(paginaActual);
		}
		//Calcular el numero de lineas de cada paginacion
		GrupoQuery grupoQueryAux = new GrupoQuery();
		calcularNumeroLineas(numRegistros,paginaActual,grupoQueryAux,campo,direccion,descripcion,formulario);
		
		
		return grupoQueryAux;
	}
	
	/**
	 * Calcular numero lineas.
	 *
	 * @param numRegistros el num registros
	 * @param paginaActual el pagina actual
	 * @param grupoQueryAux el grupo query aux
	 * @param campo el campo
	 * @param direccion el direccion
	 * @param descripcion el descripcion
	 * @param formulario el formulario
	 */
	private void calcularNumeroLineas(int numRegistros, String paginaActual, GrupoQuery grupoQueryAux, String campo, String direccion, String descripcion,GrupoForm formulario) {
		
		int tamanyoPaginacionReal = numRegistros;
		int tamanyoPaginacion = tamanyoPaginacionReal+1;
		int numPagina = Integer.parseInt(paginaActual);
		int primerRegistro = (numPagina*tamanyoPaginacionReal)-tamanyoPaginacionReal;
		
		
		grupoQueryAux.setEstado('A');
		if(descripcion != null && !"".equals(descripcion)){
			grupoQueryAux.setDescripcionComparator(Comparator.LIKE_LEFT_RIGHT);
			grupoQueryAux.setDescripcion(formulario.getDescripcion());
		}
		if(campo != null && !"0".equals(campo) && !"".equals(campo)){
			OrderType orden = ("up".equals(direccion)==true ? OrderType.ASC:OrderType.DESC);
			grupoQueryAux.addOrder(campo, orden);
		}
		grupoQueryAux.setCalculateNumResults(true);
		
		if(paginaActual != null){
			grupoQueryAux.setMaxResults(tamanyoPaginacion);
			grupoQueryAux.setFirstResult(primerRegistro);
		}
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
	 * Obtiene el logger.
	 *
	 * @return el logger
	 */
	public static Logger getLogger() {
		return logger;
	}

	
}
