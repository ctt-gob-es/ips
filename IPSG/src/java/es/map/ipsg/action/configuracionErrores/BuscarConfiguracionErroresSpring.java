package es.map.ipsg.action.configuracionErrores;

import java.util.List;
import java.util.ResourceBundle;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import es.map.ips.common.model.Comparator;
import es.map.ips.common.model.OrderType;
import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringMessage;
import es.map.ips.common.spring.SpringMessages;
import es.map.ips.model.ConfiguracionErroresQuery;
import es.map.ips.model.EntidadFinancieraQuery;
import es.map.ips.model.TipoPagoQuery;
import es.map.ipsg.bean.ConfiguracionErroresBean;
import es.map.ipsg.form.ConfiguracionErroresForm;
import es.map.ipsg.form.BuscarEntidadFinancieraForm;
import es.map.ipsg.manager.ConfiguracionErroresManager;
import es.map.ipsg.util.Constantes;

public class BuscarConfiguracionErroresSpring extends AbstractSpring {

	// Declarar los resource
	
	private static final String MESSAGE_RESOUCE = "MessageResources";
	
	private static final ResourceBundle RESOURCE_MESSAGE = ResourceBundle.getBundle(MESSAGE_RESOUCE);
	private static final Logger logger = Logger.getLogger(BuscarConfiguracionErroresSpring.class);

	// Declaracion de manager
	private ConfiguracionErroresManager configuracionErroresManager;

	public BuscarConfiguracionErroresSpring() {
		try {
			setConfiguracionErroresManager((ConfiguracionErroresManager) getBean("configuracionErroresManager"));
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error BuscarConfiguracionErroresSpring :",e);
		}
	}

	@Override
	protected String doExecute(SpringForm form) throws Exception {
		getLogger().debug("BuscarConfiguracionErroresSpring -start");
		// *********** METO EL PARAMETRO QUE ME INDICA EN QUE MENU ESTOY
		// **********
		
		String menu_tablaBase = RESOURCE_MESSAGE.getString("field.menu.tablasBase");
		this.getRequest().getSession().setAttribute("pagActiva", menu_tablaBase);
		String subMenu_ConfiguracionErrores = RESOURCE_MESSAGE.getString("field.menuLateral.tablasBase.configuracionErrores");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_ConfiguracionErrores);
		// ******************************************************************
	try{
		// Coger el form del jsp
		ConfiguracionErroresForm formulario;
		formulario = (ConfiguracionErroresForm) form;
		String busqueda = formulario.getSubmit();

		// Creamos la Query
		ConfiguracionErroresQuery configuracionErroresQuery;
		
		// Llama a la funcion para crear la Query pasandole el formulario
		String paginaActual = this.getRequest().getParameter("paginaActual");
		String paginaTotal = this.getRequest().getParameter("paginasTotales");
		String numReg = this.getRequest().getParameter("numRegistro");
		logger.info("NumRegistros: " + numReg);
		String cambios = this.getRequest().getParameter("cambios");

		//Comprobar si viene del menu
		String menu = this.getRequest().getParameter("menu");
		if(menu != null && menu.equals("S")){
			formulario.setDescripcion("");
			busqueda = "";
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
		
		if (paginaActual != null && !"".equals(paginaActual) && paginaTotal != null && !"".equals(paginaTotal)) {
			int pagActu = Integer.parseInt(paginaActual);
			int pagTotales = Integer.parseInt(paginaTotal);

			if (pagActu > pagTotales) {
				paginaActual = String.valueOf(pagTotales);
			}
		}
		
		if("0".equals(paginaActual) || paginaActual == null || "".equals(paginaActual)/* || "Buscar".equals(busqueda)*/){
			paginaActual = Constantes.PAGINA_PRINCIPAL;	
			formulario.setPaginaActual(paginaActual);
		}

		// Creamos la query para busca la especialidad
		configuracionErroresQuery = crearConfiguracionErroresQuery(formulario,numReg,paginaActual);

		List<ConfiguracionErroresBean> configuracionErrores = null;
		if ("Buscar".equals(busqueda) || "Ordenar".equals(busqueda) || "Paginar".equals(busqueda)) {
			configuracionErrores = configuracionErroresManager.buscarConfiguracionErrorAll(configuracionErroresQuery);
			cambios = "Correcto";
		}
		int pagTotal = 0;
		int numPaginas = 0;
		if (formulario.getNumRegistro() == null && numReg == null) {
			// Para el primer paso para el action, se establece que los registros por pagina son 5
			formulario.setNumRegistro(Constantes.NUMERO_REGISTROS_PAGINA);
		}
		int numRegistros = 0;
		if(StringUtils.isNotEmpty(numReg)){
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

		// Recorrer la lista, para comprobar los estados de cada entidad
		// financiera para activar los enlaces
		if (configuracionErrores != null && configuracionErrores.size() != 0) {
			pagTotal = configuracionErrores.get(0).getNumTotal();
			numPaginas = pagTotal / numRegistros;
			int resto = pagTotal % numRegistros;
			if (resto > 0) {
				numPaginas++;
			}
			if (configuracionErrores.size() > numRegistros) {
				configuracionErrores.remove(configuracionErrores.size() - 1);
			}
		}
		
		// Si no hemos encontrado ningun registro mostramos mensaje de error
		if (pagTotal == 0 && "Correcto".equals(cambios)) {
			logger.info("No existe ningun registro ");
			SpringMessages errors = new SpringMessages();
			errors.add("errorSinResultados", new SpringMessage("field.configuracionErrores.error"));
			this.setRequestAttribute("org.apache.spring.ERROR", errors);
		}

		// Pasar la convocatoria y la pagina al jsp
		if(paginaActual==null||"".equals(paginaActual))
			paginaActual = "1";
		
		setRequestAttribute("configuracionErrores", configuracionErrores);
		setRequestAttribute("paginaActual", paginaActual);
		setRequestAttribute("paginasTotales", numPaginas);
		setRequestAttribute("cambios", cambios);
		getLogger().debug("BuscarConfiguracionErroresSpring -end");
		
	}catch(Exception eGenerico){
		logger.error("Error BuscarConfiguracionErroresSpring - doExecute:",eGenerico);
		this.getRequest().setAttribute("descripcionError", RESOURCE_MESSAGE.getString("field.errorGenerico"));
		return "errorGenerico";
	}
	
		return "success";
	}
	
	private int comprobarEntero(String numero) {
		int resultado = 0;
		try{
			resultado = Integer.parseInt(numero);
		}catch(Exception e){
			logger.error(e);
			e.printStackTrace();
			resultado = Integer.parseInt(Constantes.NUMERO_REGISTROS_PAGINA);
		}
		return resultado;
	}

	private String cargarCampos(String campo) {
		String auxCampo = null;
		int codCampo = 0;
		try {
			if (!StringUtils.isEmpty(campo)) {
				codCampo = Integer.parseInt(campo);
				switch (codCampo) {
					// Los campos de ordenacion del jsp
					case 1: auxCampo = "id";break;
					case 2: auxCampo = "descripcion";break;
					case 3:	auxCampo = "visibilidad";break;
					default: break;
				}
			}
		} catch (Exception e) {
			logger.error("Error BuscarConfiguracionErroresSpring - cargarCampos:"+ codCampo,e);
		}
		
		return auxCampo;
	}

	private ConfiguracionErroresQuery crearConfiguracionErroresQuery(ConfiguracionErroresForm formulario, String numReg, String paginaActual) {

		// Recoger los datos del formulario
		String descripcion = formulario.getDescripcion();
		String campo = cargarCampos(formulario.getCampo()); 
		String direccion = formulario.getDireccion();

		if (formulario.getNumRegistro() == null && numReg == null) {
			// Para el primer paso para el action, se establece que los
			// registros por pagina son 5
			formulario.setNumRegistro(Constantes.NUMERO_REGISTROS_PAGINA);
		}
		int numRegistros = 0;
		if(StringUtils.isNotEmpty(numReg)){
			try{
			numRegistros = Integer.parseInt(numReg);
			}catch(Exception e){
				logger.error("Error BuscarConfiguracionErroresSpring - numRegistros:"+ numRegistros,e);
				numRegistros = Integer.parseInt(Constantes.NUMERO_REGISTROS_PAGINA);
			}
		}else{
			try{
			numRegistros = Integer.parseInt(formulario.getNumRegistro());
			}catch(Exception e){
				logger.error("Error BuscarConfiguracionErroresSpring - numRegistros:"+ numRegistros,e);
				numRegistros = Integer.parseInt(Constantes.NUMERO_REGISTROS_PAGINA);
			}
		}
		if(numRegistros > 50){
			numRegistros = 50;
			formulario.setNumRegistro(String.valueOf(numRegistros));
		}else{
			formulario.setNumRegistro(String.valueOf(numRegistros));
		}
		// Si la pagina actual no esta definida, se establece a 1
		if ("0".equals(paginaActual) || paginaActual == null
				|| "".equals(paginaActual)) {
			paginaActual = "1";
			formulario.setPaginaActual(paginaActual);
		}
		// Calcular el numero de lineas de cada paginacion
		ConfiguracionErroresQuery configuracionErroresQuery = new ConfiguracionErroresQuery();
		calcularNumeroLineas(numRegistros,paginaActual,descripcion,configuracionErroresQuery,campo,direccion);
		
		// Devulelve la entidad financiera con los filtros
		return configuracionErroresQuery;
	}
	private void calcularNumeroLineas(int numRegistros, String paginaActual, String descripcion, ConfiguracionErroresQuery configuracionErroresQuery, String campo, String direccion) {
		int tamanyoPaginacionReal = numRegistros;
		int tamanyoPaginacion = tamanyoPaginacionReal + 1;
		int numPagina = Integer.parseInt(paginaActual);
		int primerRegistro = (numPagina * tamanyoPaginacionReal) - tamanyoPaginacionReal;

		
		if (paginaActual != null) {
			configuracionErroresQuery.setMaxResults(tamanyoPaginacion);
			configuracionErroresQuery.setFirstResult(primerRegistro);
		}
		configuracionErroresQuery.setCalculateNumResults(true);
		// Comprobar los filtros para realizar la busqueda

		if (StringUtils.isNotEmpty(descripcion)) {
			configuracionErroresQuery.setDescripcion(descripcion);
			configuracionErroresQuery.setDescripcionComparator(Comparator.LIKE_LEFT_RIGHT);
		}
		
		OrderType orden;
		if (campo != null && !"0".equals(campo) && !"".equals(campo)) {
			orden =("up".equals(direccion) == true ? OrderType.ASC : OrderType.DESC);	
		} else {
			campo = "id";
			orden = OrderType.ASC;
		}
		configuracionErroresQuery.addOrder(campo, orden);
		
	}

	/**
	 * @return the especialidadManager
	 */
	public ConfiguracionErroresManager getConfiguracionErroresManager() {
		return configuracionErroresManager;
	}

	/**
	 * @param configuracionErroresManager
	 *            the configuracionErroresManager to set
	 */
	public void setConfiguracionErroresManager(
			ConfiguracionErroresManager configuracionErroresManager) {
		this.configuracionErroresManager = configuracionErroresManager;
	}

	public static Logger getLogger() {
		return logger;
	}

}
