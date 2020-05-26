package es.map.ipsg.action.logs;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import es.map.ips.common.model.Comparator;
import es.map.ips.common.model.OrderType;
import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.CentroGestorQuery;
import es.map.ips.model.ConvocatoriaQuery;
import es.map.ips.model.CuerpoEscalaQuery;
import es.map.ips.model.LogAccesoQuery;
import es.map.ips.model.LogSolicitudQuery;
import es.map.ips.model.SolicitudComunQuery;
import es.map.ipsg.bean.LogSolicitudBean;
import es.map.ipsg.form.LogSolicitudesForm;
import es.map.ipsg.manager.LogServicioManager;
import es.map.ipsg.manager.LogSolicitudManager;
import es.map.ipsg.util.Constantes;
import es.map.ipsg.util.UtilesIPSG;

/**
 * El Class BuscarLogSolicitudesSpring.
 */
public class BuscarLogSolicitudesSpring extends AbstractSpring {

	/** La constante MESSAGE_RESOUCE. */
	//Declarar los resource
	private static final String MESSAGE_RESOUCE = "MessageResources";
	
	/** La constante RESOURCE_MESSAGE. */
	private static final ResourceBundle RESOURCE_MESSAGE = ResourceBundle.getBundle(MESSAGE_RESOUCE);
	

	//Declaracion de manager
	/**
	 * Para recuperar los Errores se utiliza la tabla y ls manager LogServicio pero con el Resultado de error (ER).
	 */
	private LogSolicitudManager logSolicitudManager;

	
	/** La constante logger. */
	//Declaracion de variables
	private static final Logger logger = Logger.getLogger(LogServicioManager.class);
	
	/** La constante STRING_LLAMADA. */
	private static final String STRING_LLAMADA = "llamada";
	
	/** La constante STRING_BUSCAR. */
	private static final String STRING_BUSCAR = "Buscar";
	
		
	/**
	 * Crea una nueva buscar log solicitudes spring.
	 */
	public BuscarLogSolicitudesSpring() {
		try {
			setLogSolicitudManager((LogSolicitudManager) getBean("logSolicitudManager"));
		} catch (Exception e) {
			logger.error("Error BuscarLogSolicitudesSpring :"  ,e);
		}
	}

	




	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		
		//*********** METO EL PARAMETRO QUE ME INDICA EN QUE MENU ESTOY **********
		String menu_consultas = RESOURCE_MESSAGE.getString("field.menu.consultas");
		this.getRequest().getSession().setAttribute("pagActiva",menu_consultas);
		String subMenu_log = RESOURCE_MESSAGE.getString("field.menuLateral.consultas.logSolicitudes");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_log);
		//****************************************************************** 
		getLogger().debug("BuscarLogSolicitudesSpring -start");
	try{
		LogSolicitudesForm formulario;
		formulario = (LogSolicitudesForm) form;
		
		String paginaActual = this.getRequest().getParameter("paginaActual");
		String paginaTotal = this.getRequest().getParameter("paginasTotales");
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
			paginaActual = "1";
			formulario.setPaginaActual(paginaActual);
		}
		
		String numReg = this.getRequest().getParameter("numRegistro");
		logger.info("NumRegistros: "+numReg);
		String busqueda="";
		if(this.getRequest().getParameter(STRING_LLAMADA) != null && !"".equals(this.getRequest().getParameter(STRING_LLAMADA))){
			busqueda = this.getRequest().getParameter(STRING_LLAMADA);
		}else{
			if (formulario.getSubmit()!=null&& !"".equals(formulario.getSubmit())){
				busqueda=formulario.getSubmit();
			}else{
				busqueda=STRING_BUSCAR;
			}
		}
		
		if("0".equals(paginaActual) || paginaActual == null || "".equals(paginaActual) || STRING_BUSCAR.equals(busqueda)){
			paginaActual = Constantes.PAGINA_PRINCIPAL;		
		}
		
		LogSolicitudQuery logSolicitudQuery;
		String campo="";
		String direccion ="";
		
		//Si ha seleccionado un campo de ordenacion se le asigna el atributo de ordenacion
		if(formulario.getCampo()!= null){
			try{
				if (formulario.getCampo()==null ||formulario.getCampo().equals("")){
					campo=cargarCampos("1");
					direccion="up";
				}else{
					campo=cargarCampos(formulario.getCampo());
					direccion=formulario.getDireccion();
				}
				
			}catch(Exception e){
				logger.error("Error BuscarLogSolicitudesSpring - campo :" +formulario.getCampo() ,e);
			}			
		}
		logSolicitudQuery=crearQuerySolicitudes(formulario,paginaActual,numReg,campo,direccion);
		List<LogSolicitudBean> solicitudes = null;
		
		if(STRING_BUSCAR.equals(busqueda) || "Ordenar".equals(busqueda) || "Paginar".equals(busqueda)){ 
			solicitudes = logSolicitudManager.buscarLogSolicitudAll(logSolicitudQuery);
		}
		//PRUEBA PAGINACION 
		int numRegistros = Integer.parseInt(formulario.getNumRegistro());
		//Calcular el numero de lineas de cada paginacion
			int tamanyoPaginacionReal = numRegistros;
			int numPagina = Integer.parseInt(paginaActual);
			int primerRegistro = (numPagina*tamanyoPaginacionReal)-tamanyoPaginacionReal;
		
			List<LogSolicitudBean> solicitudesAux=new ArrayList<LogSolicitudBean>();
			List<LogSolicitudBean> solicitudesDistintas=new ArrayList<LogSolicitudBean>();
			Boolean existe=false;
			//Recuperamos el numero de Solicitudes Distintas que tienen actualizacion de Estado
			for (int i=0;i<solicitudes.size();i++){
				existe= false;
				for(int j=0;j<solicitudesDistintas.size();j++){
					if (solicitudes.get(i).getNumeroJustificante().equals(solicitudesDistintas.get(j).getNumeroJustificante()) && solicitudes.get(i).getIdConvocatoria().toString().equals(solicitudesDistintas.get(j).getIdConvocatoria().toString())){
						existe=true;
					}
				}
				if (!existe ){
					solicitudesDistintas.add(solicitudes.get(i));
				}
			}
			//Por cada Solicitud realizamos la paginacion
			for (int i=0;i<solicitudesDistintas.size();i++){	
				//Cuando el cursor llege al primer registro de la pagina se carga los valores del registro en las variables anteriores para comparar
				if(i==primerRegistro && primerRegistro==0){
					
					solicitudesAux.add(solicitudesDistintas.get(i));
					
				}
				
				existe=false;
				//Recuperamos solo las solicitudes distintas que se deben mostrar segun la paginacion
				for(int j=0;j<solicitudesAux.size();j++){
					if (solicitudesDistintas.get(i).getNumeroJustificante().equals(solicitudesAux.get(j).getNumeroJustificante()) && solicitudesDistintas.get(i).getIdConvocatoria().toString().equals(solicitudesAux.get(j).getIdConvocatoria().toString())&& i>=primerRegistro ){
						existe=true;
					}
				}
				if (!existe && i>primerRegistro && i<tamanyoPaginacionReal*numPagina){
					solicitudesAux.add(solicitudesDistintas.get(i));
				}
				
				
			}
			//Si el Array auxsiliar realizado para la paginacion esta relleno se asigna al que pasaremos al jsp
			if (solicitudesAux.size()>0){
				solicitudes=solicitudesAux;
				solicitudes.get(0).setNumTotal(solicitudesDistintas.size());
			}
			
		//FIN PRUEBA PAGINACION 
		int pagTotal = 0;
		int numPaginas=0;
		if(formulario.getNumRegistro() == null && numReg == null){
			//Para el primer paso para el action, se establece que los registros por pagina son 5
			formulario.setNumRegistro(Constantes.NUMERO_REGISTROS_PAGINA);
		}
		numRegistros = 0;
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
		if(solicitudes != null && solicitudes.size()!= 0){
			pagTotal= solicitudes.get(0).getNumTotal();
			numPaginas = pagTotal / numRegistros;
			int resto = pagTotal % numRegistros;
			if(resto > 0){
				numPaginas++;
			}
			if(solicitudes.size()>numRegistros){
				solicitudes.remove(solicitudes.size()-1);
			}			
		}
		
	
		setRequestAttribute("solicitudes", solicitudes);
		setRequestAttribute("paginaActual", paginaActual);
		setRequestAttribute("paginasTotales", numPaginas);	
		setRequestAttribute("numRegistro",numReg);
		setRequestAttribute("submit", busqueda);
		setRequestAttribute("cambios", cambios);

		getLogger().debug("BuscarLogSolicitudesSpring -end");
		
	}catch(Exception eGenerico){
		logger.error("Error BuscarLogSolicitudesSpring - doExecute :" ,eGenerico);
		this.getRequest().setAttribute("descripcionError", RESOURCE_MESSAGE.getString("field.errorGenerico"));
		return "errorGenerico";
	}	
		
		return "success";
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
			logger.error("Error comprobarEntero() - numRegistros :" + resultado ,e);
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
			logger.error("Error BuscarLogSolicitudesSpring - cargarCampos - campo :" + codCampo ,e);
		}
			if (campo != null && !"".equals(campo) && !campo.equals("null")){
				
					switch(codCampo){
					//Los campos de ordenacion del jsp
						case 1:auxCampo = "solicitud";break;
						case 2:auxCampo = "actor";break;
						case 3:auxCampo = "convocatoria";break;	
						case 4:auxCampo = "cuerpoEscala";break;
						case 5:auxCampo = "centroGestor";break;	
						default:break;
						}
					
				}
			return auxCampo;
			}
			
	

	/**
	 * Crear query solicitudes.
	 *
	 * @param formulario el formulario
	 * @param paginaActual el pagina actual
	 * @param numReg el num reg
	 * @param campo el campo
	 * @param direccion el direccion
	 * @return el log solicitud query
	 */
	private LogSolicitudQuery crearQuerySolicitudes(LogSolicitudesForm formulario,String paginaActual,String numReg,String campo,String direccion) {
	
		UtilesIPSG utilesIPSG=new UtilesIPSG();
		
		
		
		if(formulario.getNumRegistro() == null && numReg == null){
			//Para el primer paso para el action, se establece que los registros por pagina son 5
			formulario.setNumRegistro(Constantes.NUMERO_REGISTROS_PAGINA);
		}
		int numRegistros = 0;
		if(numReg != null && !"".equals(numReg)){
			try{
			numRegistros = Integer.parseInt(numReg);
			}catch(Exception e){
				logger.error("Error BuscarLogSolicitudesSpring - crearQuerySolicitudes - numRegistros :" + numRegistros ,e);
				numRegistros = Integer.parseInt(Constantes.NUMERO_REGISTROS_PAGINA);
			}
		}else{
			try{
			numRegistros = Integer.parseInt(formulario.getNumRegistro());
			}catch(Exception e){
				logger.error("Error BuscarLogSolicitudesSpring - crearQuerySolicitudes - numRegistros :" + numRegistros ,e);
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
			paginaActual = "1";
			formulario.setPaginaActual(paginaActual);
		}
		
		//Calcular el numero de lineas de cada paginacion
		int tamanyoPaginacionReal = numRegistros;
		int tamanyoPaginacion = tamanyoPaginacionReal+1;
		int numPagina = Integer.parseInt(paginaActual);
		int primerRegistro = (numPagina*tamanyoPaginacionReal)-tamanyoPaginacionReal;
		ConvocatoriaQuery convQuery = new ConvocatoriaQuery();
		
		LogAccesoQuery logAccesoQuery = new LogAccesoQuery();
		if(paginaActual != null){
			logAccesoQuery.setMaxResults(tamanyoPaginacion);
			logAccesoQuery.setFirstResult(primerRegistro);
		}		
		logAccesoQuery.setCalculateNumResults(true);

		SolicitudComunQuery solicitudQuery= new SolicitudComunQuery();
		LogSolicitudQuery logSolicitudQuery= new LogSolicitudQuery();
		
		logSolicitudQuery.setCalculateNumResults(true);
		if(formulario.getFechaDesde()!=null && !"".equals(formulario.getFechaDesde())){
			logSolicitudQuery.setFechaMin(utilesIPSG.stringToDate(formulario.getFechaDesde()));
		}
		if(formulario.getFechaHasta()!=null && !"".equals(formulario.getFechaHasta())){
			logSolicitudQuery.setFechaMax(utilesIPSG.stringToDate(formulario.getFechaHasta()));
		}
		
		if(formulario.getIdConvocatoria()!=null && !"".equals(formulario.getIdConvocatoria())){
			convQuery.addIdIn(Long.valueOf(formulario.getIdConvocatoria()));
			solicitudQuery.setConvocatoria(convQuery);
		}
		
		if(formulario.getNif()!=null && !"".equals(formulario.getNif())){
			
			logSolicitudQuery.setActor(formulario.getNif());
			
		}
		if(formulario.getNumeroJustificante()!=null && !"".equals(formulario.getNumeroJustificante())){
			solicitudQuery.setNumeroSolicitudComparator(Comparator.LIKE_LEFT_RIGHT);
			solicitudQuery.setNumeroSolicitud(formulario.getNumeroJustificante());
		}
		logSolicitudQuery.setSolicitudComun(solicitudQuery);
		
		//Añadimos el order
		OrderType orden = ("up".equals(direccion) == true ? OrderType.ASC : OrderType.DESC);
		if(campo != null && !"0".equals(campo) && !"".equals(campo)){			
			if("solicitud".equals(campo)){
				solicitudQuery.addOrder("numeroSolicitud", orden);
				logSolicitudQuery.setSolicitudComun(solicitudQuery);
			}else{
				if("convocatoria".equals(campo)){
					
					convQuery.addOrder("id",orden);
					
					solicitudQuery.setConvocatoria(convQuery);
					logSolicitudQuery.setSolicitudComun(solicitudQuery);
				}else{
					if("actor".equals(campo)){
						
						solicitudQuery.addOrder("nif", orden);
						logSolicitudQuery.setSolicitudComun(solicitudQuery);
					}else{
						if("cuerpoEscala".equals(campo)){
							CuerpoEscalaQuery cuerpoEscalaQuery = new CuerpoEscalaQuery();
							cuerpoEscalaQuery.addOrder("descripcion", orden);
							
							convQuery.setCuerpoEscala(cuerpoEscalaQuery);
							solicitudQuery.setConvocatoria(convQuery);
							logSolicitudQuery.setSolicitudComun(solicitudQuery);
						}else{
							if("centroGestor".equals(campo)){
								CentroGestorQuery centroGestorQuery = new CentroGestorQuery();
								centroGestorQuery.addOrder("descripcion", orden);			
								CuerpoEscalaQuery cuerpoEscalaQuery = new CuerpoEscalaQuery();
								cuerpoEscalaQuery.setCentroGestor(centroGestorQuery);
								
								convQuery.setCuerpoEscala(cuerpoEscalaQuery);
								solicitudQuery.setConvocatoria(convQuery);
								logSolicitudQuery.setSolicitudComun(solicitudQuery);
							}else{
								if("up".equals(direccion)){
									logSolicitudQuery.addOrder(campo,OrderType.ASC);
								}else{
									logSolicitudQuery.addOrder(campo,OrderType.DESC);
								}
							}
						}
					}
				}
			}
		}else{
			solicitudQuery.addOrder("numeroSolicitud", orden);
		}
		
		//realizamos select distinct
		//El tipo de operacion es  E
		logSolicitudQuery.setTipoOperacion(Constantes.OPERACION_ACTUALIZAR_ESTADO_SOLICITUD);
		//Devulelve los errores con los filtros
		return logSolicitudQuery;
	}
	
	/**
	 * Obtiene el log solicitud manager.
	 *
	 * @return the logErroresManager
	 */
	public LogSolicitudManager getLogSolicitudManager() {
		return logSolicitudManager;
	}

	/**
	 * Establece el log solicitud manager.
	 *
	 * @param logSolicitudManager el nuevo log solicitud manager
	 */
	public void setLogSolicitudManager(LogSolicitudManager logSolicitudManager) {
		this.logSolicitudManager = logSolicitudManager;
	}



	/**
	 * Obtiene el logger.
	 *
	 * @return the logger
	 */
	public static Logger getLogger() {
		return logger;
	}


}
