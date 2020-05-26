package es.map.ipsg.action.serviciosExternos;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.LogServicioQuery;
import es.map.ipsg.bean.ConsultarServiciosExternosBean;
import es.map.ipsg.bean.LogServicioBean;
import es.map.ipsg.form.ConsultaServiciosExternosForm;
import es.map.ipsg.manager.LogServicioManager;
import es.map.ipsg.util.Constantes;


/**
 * El Class ConsultarResultadosServiciosExternosSpring.
 */
public class ConsultarResultadosServiciosExternosSpring extends AbstractSpring {

	/** el log servicio manager. */
	private LogServicioManager logServicioManager;
			
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(ConsultarResultadosServiciosExternosSpring.class);
	
	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);


	/**
	 * Crea una nueva consultar resultados servicios externos spring.
	 */
	public ConsultarResultadosServiciosExternosSpring() {
		try {
			setLogServicioManager((LogServicioManager) getBean("logServicioManager"));
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error ConsultarResultadosServiciosExternosSpring:",e);
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		logger.info("ConsultarResultadosServiciosExternosSpring -start");
		
		String menu_alerta = RESOURCE_BUNDLE.getString("field.menu.adminServExt");
		this.getRequest().getSession().setAttribute("pagActiva", menu_alerta);		
		String subMenu_ServExt = RESOURCE_BUNDLE.getString("field.menuLateral.serviciosExternos.consultarEstadistica");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_ServExt);
		
		ConsultaServiciosExternosForm formulario = (ConsultaServiciosExternosForm) form;
		
		//Indicamos si viene desde el enlace del menú para mostrar los resultados de búsqueda o no 
		//y guardar la descripción para el formulario de búsqueda
		String sVieneMenu = this.getRequest().getParameter("menu"); 
		if (sVieneMenu != null && sVieneMenu.equals("S")){
			//Poner todos los campos del formulario en blanco			
			formulario.setIdServicio("");			
			formulario.setResultado("");
			formulario.setFechaDesde("");
			formulario.setFechaHasta("");
			formulario.setAccion("");
		}
		//Si viene a null es que viene de las demás páginas que no son del menú principal

		//FIN Limpiado de formulario y muestra de resultados.
		
		String submit = formulario.getAccion();
		String numPagina = formulario.getPaginaActual();
		String numRegistros = formulario.getNumRegistro();
		String paginasTotales = formulario.getPaginasTotales();
		String campo = formulario.getCampo();
		String direccion = formulario.getDireccion();
						
		if(this.getRequest().getParameter("llamada")!=null)
			submit=this.getRequest().getParameter("llamada");
		
		if(numPagina == null || numPagina.equals("") || paginasTotales == null || paginasTotales.equals("") || "Buscar".equals(submit)){
			numPagina = "1";
			paginasTotales="0";
		}else{
			int pagActu = Integer.parseInt(numPagina);		
			int pagTotales = Integer.parseInt(paginasTotales);
			
			if(pagActu<1){
				numPagina = "1";
			}
			if(pagActu > pagTotales){
				numPagina = String.valueOf(pagTotales);
			}
		}
		
		formulario.setPaginaActual(numPagina);
		formulario.setPaginasTotales(paginasTotales);
		
		if(numRegistros == null || numRegistros.equals("")){
			numRegistros = "10";
			formulario.setNumRegistro(numRegistros);
		}	
		
		
		if(submit != null && (submit.equals("Buscar") || submit.equals("Paginar") || submit.equals("Ordenar"))){			
			
			ConsultarServiciosExternosBean consultarServiciosExternosBean = logServicioFormToBean(formulario);
			
			if(campo != null){
				campo = cargarCampos(campo);
				consultarServiciosExternosBean.setCampo(campo);
				consultarServiciosExternosBean.setDireccion(direccion);
			}
			
			List<LogServicioBean> result = logServicioManager.consultarEstadisticasLogServicio(consultarServiciosExternosBean);
			setRequestAttribute("resultados",result);			
			
			//Recorrer la lista, para comprobar los estados de cada convocatoria para activar los enlaces
			if(result != null && result.size()!= 0){
				int pagTotal= result.get(0).getNumTotal();
				int nRegistros = Integer.valueOf(numRegistros);
				int numPaginas = pagTotal / nRegistros;
				int resto = pagTotal % nRegistros;
				if(resto > 0){
					numPaginas++;
				}
				if(result.size()>nRegistros){
					result.remove(result.size()-1);
				}
				formulario.setPaginasTotales(String.valueOf(numPaginas));
			}
		}
		
		if(formulario.getPaginasTotales()==null){
			formulario.setPaginasTotales("0");
		}
		
		formulario.setPulsaIr(false);
		formulario.setNumeroPaginaIr(null);
		setRequestAttribute("paginaActual", formulario.getPaginaActual());
		setRequestAttribute("paginasTotales", formulario.getPaginasTotales());
		
		logger.info("ConsultarResultadosServiciosExternosSpring -end");
		
		return "success";
		
	}
	
	/**
	 * Cargar campos.
	 *
	 * @param campo el campo
	 * @return el string
	 */
	public String cargarCampos(String campo){
		String auxCampo=null;
		int codCampo = 0;
		try{
			codCampo = Integer.parseInt(campo);
		}catch(Exception e){
			logger.error("Error ConsultarResultadosServiciosExternosSpring - cargarCampos - error  parsear campo:"+ codCampo,e);
		}
			if (campo != null && !"".equals(campo) && !campo.equals("null")){
				
					switch(codCampo){
					//Los campos de ordenacion del jsp
						case 1:auxCampo = LogServicioQuery.ID;break;
						case 2:auxCampo = LogServicioQuery.FECHA;break;
						case 3:auxCampo = LogServicioQuery.RESULTADO;break;
						case 4:auxCampo = LogServicioQuery.TIEMPORESPUESTA;break;
						case 5:auxCampo = LogServicioQuery.TIPOERROR;break;
						case 6:auxCampo = LogServicioQuery.DESCRIPCIONERROR;break;
						default:break;
					}
				
			}
		return auxCampo;		
	}
	
	/**
	 * Log servicio form to bean.
	 *
	 * @param formulario el formulario
	 * @return el consultar servicios externos bean
	 */
	public ConsultarServiciosExternosBean logServicioFormToBean(ConsultaServiciosExternosForm formulario){
		ConsultarServiciosExternosBean consultarServiciosExternosBean = new ConsultarServiciosExternosBean();
		
		String resultado = formulario.getResultado();
		String sFechaDesde = formulario.getFechaDesde();
		String sFechaHasta = formulario.getFechaHasta();
		String sServicio = formulario.getIdServicio();
		Integer iServicio = 0;
		try{
			iServicio = Integer.valueOf(sServicio);
		}catch(Exception e){
			logger.error("Error ConsultarResultadosServiciosExternosSpring - logServicioFormToBean - error  parsear servicio:"+ iServicio,e);
		}
		
		Integer idServicio = null;
		
		if(resultado != null && resultado.equals("")){
			resultado = null;
		}
		
		switch(iServicio){
			case Constantes.PRUEBA_SERVICIO_EXTERNO_PASARELA_PAGO:
				idServicio = 3;
				break;
			case Constantes.PRUEBA_SERVICIO_EXTERNO_REC:
				idServicio = 4;
				break;
			case Constantes.PRUEBA_SERVICIO_EXTERNO_EJB:
				idServicio = 1;
				break;
			case Constantes.PRUEBA_SERVICIO_EXTERNO_FILESYSTEM:
				idServicio = 6;
				break;
			case Constantes.PRUEBA_SERVICIO_EXTERNO_FIRMA:
				idServicio = 2;
				break;	
			default:
				break;
		}
		
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date fechaDesde = sdf.parse(sFechaDesde);
			Date fechaHasta = sdf.parse(sFechaHasta);
			consultarServiciosExternosBean.setFechaDesde(fechaDesde);
			consultarServiciosExternosBean.setFechaHasta(fechaHasta);
		}catch(Exception e){
			logger.error("No se ha podido parsear la fecha. Se buscara con fechaDesde y fechaHasta nulo",e);
		}
	
		consultarServiciosExternosBean.setResultado(resultado);
		consultarServiciosExternosBean.setIdServicio(idServicio);
		consultarServiciosExternosBean.setPrueba('S');		
		
		//Para paginacion
		consultarServiciosExternosBean.setPaginaActual(formulario.getPaginaActual());
		consultarServiciosExternosBean.setNumRegistros(formulario.getNumRegistro());
		
		return consultarServiciosExternosBean;
	}
			
	/**
	 * Obtiene el log servicio manager.
	 *
	 * @return el log servicio manager
	 */
	public LogServicioManager getLogServicioManager(){
		return logServicioManager;
	}
	
	/**
	 * Establece el log servicio manager.
	 *
	 * @param logServicioManager el nuevo log servicio manager
	 */
	public void setLogServicioManager(LogServicioManager logServicioManager){
		this.logServicioManager = logServicioManager;
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
