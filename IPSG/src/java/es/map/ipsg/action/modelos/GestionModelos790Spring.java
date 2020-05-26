package es.map.ipsg.action.modelos;


import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import es.map.ips.common.model.Comparator;
import es.map.ips.common.model.FieldOrder;
import es.map.ips.common.model.OrderType;
import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringMessage;
import es.map.ips.common.spring.SpringMessages;
import es.map.ips.model.ModeloQuery;
import es.map.ipsg.bean.ModeloBean;
import es.map.ipsg.form.Modelo790Form;
import es.map.ipsg.manager.ModeloManager;
import es.map.ipsg.util.Constantes;

/**
 * El Class GestionModelos790Spring.
 */
public class GestionModelos790Spring extends AbstractSpring{
	
	/** La constante MESSAGE_RESOUCE. */
	//Declarar los resource
		private static final String MESSAGE_RESOUCE = "MessageResources";
		
		/** La constante RESOURCE_MESSAGE. */
		private static final ResourceBundle RESOURCE_MESSAGE = ResourceBundle.getBundle(MESSAGE_RESOUCE);
		
		/** La constante logger. */
		private static final Logger logger = Logger.getLogger(GestionModelos790Spring.class);
		
		/** el properties. */
		private static Properties properties;
		
		/** La constante STRING_LLAMADA. */
		private static final String STRING_LLAMADA = "llamada";
		
		/** el modelo manager. */
		//Declaracion de manager
		private ModeloManager modeloManager;
		
		
		/**
		 * Crea una nueva gestion modelos 790 spring.
		 */
		public GestionModelos790Spring() {
			try {
				setModeloManager((ModeloManager) getBean("modelosManager"));
				properties = (Properties) getBean("propertiesBean");
			} catch (Exception e) {
				logger.warn(e);
				logger.error(" Error GestionModelos790Spring:" ,e);
			}
		}

		/* (non-Javadoc)
		 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
		 */
		@Override
		protected String doExecute(SpringForm form) throws Exception {
			getLogger().debug("BuscarGestionModelos790Spring -start");
			try{
				//*********** METO EL PARAMETRO QUE ME INDICA EN QUE MENU ESTOY **********	
				String menu_tablabase = RESOURCE_MESSAGE.getString("field.menu.tablasBase");
				this.getRequest().getSession().setAttribute("pagActiva",menu_tablabase);
				String subMenu_modelo790 = RESOURCE_MESSAGE.getString("field.menuLateral.tablasBase.gestionModelos");
				this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_modelo790);
				//****************************************************************** 
				
				//Coger el form del jsp
				Modelo790Form formulario;
				formulario = (Modelo790Form) form;
				String busqueda = formulario.getSubmit();
				if(this.getRequest().getParameter(STRING_LLAMADA) != null){
					busqueda = this.getRequest().getParameter(STRING_LLAMADA);
				}
				String numReg = this.getRequest().getParameter("numRegistro");
				logger.info("NumRegistros: "+numReg);
				String cambios = this.getRequest().getParameter("cambios");
				
				//Creamos la Query		
				ModeloQuery modeloQuery;
				logger.info(busqueda);
				logger.info(this.getRequest().getParameter(STRING_LLAMADA));
				
				
				if(formulario.getCampo()!= null && !"Paginar".equals(busqueda)){
					try{						
						String campo=cargarCampos(formulario.getCampo());
						formulario.setCampo(campo);
					}catch(Exception e){
						logger.error("Error BuscarMinisteriosSpring - campo :"+ formulario.getCampo(),e);
					}			
				}
				
				// Si viene desde el menu borramos los datos previamente introducidos
				if ("S".equals(this.getRequest().getParameter("menu"))){
					formulario.setDescripcion("");	// Si viene del menu borramos todos sus campos
					busqueda = "";
				}			
				
				if(this.getRequest().getParameter(STRING_LLAMADA) != null && !"".equals(this.getRequest().getParameter(STRING_LLAMADA))){
					busqueda = this.getRequest().getParameter(STRING_LLAMADA);
				}
				
				//Llama a la funcion para crear la Query pasandole el formulario		
				String paginaActual = this.getRequest().getParameter("paginaActual");
				String paginaTotal = this.getRequest().getParameter("paginasTotales");		

				if(formulario.isPulsaIr() == true){
					paginaActual = formulario.getNumeroPaginaIr().toString();
					logger.info("PaginaActualIr: "+ paginaActual);
					formulario.setPulsaIr(false);
					formulario.setNumeroPaginaIr(null);
				}else{
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
				
				if("0".equals(paginaActual) || paginaActual == null || "".equals(paginaActual) /*|| "Buscar".equals(busqueda)*/){
					paginaActual = Constantes.PAGINA_PRINCIPAL;
					formulario.setPaginaActual(paginaActual);
				}
				
				//Creamos la query para busca la especialidad
				modeloQuery=crearQueryModelo790(formulario,paginaActual,numReg);
						
				List<ModeloBean> modelo790 = null;
				if("Buscar".equals(busqueda) || "Ordenar".equals(busqueda) || "Paginar".equals(busqueda)){ 
					modelo790 = modeloManager.buscarModelosAll(modeloQuery);
					//Error sin resultados
					if (modelo790 != null && modelo790.size() == 0) {
						SpringMessages errors = new SpringMessages();
						errors.add("errorSinResultados", new SpringMessage("field.gestionModelos790.error"));
						this.setRequestAttribute("org.apache.spring.ERROR", errors);
					}
				}
				int pagTotal = 0, numPaginas=0;
				if(formulario.getNumRegistro() == null && numReg == null){
					//Para el primer paso para el action, se establece que los registros por pagina son 5
					formulario.setNumRegistro(properties.getProperty("conf.numRegistrosListados"));
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
				if(modelo790 != null && modelo790.size()!= 0){
					pagTotal= modelo790.get(0).getNumTotal();
					numPaginas = pagTotal / numRegistros;
					int resto = pagTotal % numRegistros;
					if(resto > 0){
						numPaginas++;
					}
					if(modelo790.size()>numRegistros){
						modelo790.remove(modelo790.size()-1);
					}
					
				}
				
				//Pasar la convocatoria y la pagina al jsp			
				if(paginaActual==null || "".equals(paginaActual))
					paginaActual = "1";
				
				setRequestAttribute("modelo790", modelo790);
				setRequestAttribute("cambios", cambios);
				setRequestAttribute("paginaActual", paginaActual);
				setRequestAttribute("paginasTotales", numPaginas);	
				return "success";
				
			}catch(Exception e){
				logger.warn(e);
				logger.error(" Error GestionModelos790Spring - doExecute:" ,e);
				this.getRequest().setAttribute("descripcionError", e.getMessage());
				return "nosuccess";
			}
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
				logger.error("Error BuscarMinisteriosSpring - cargarCampos- campo :"+ codCampo,e);
			}
				if (campo != null && !"".equals(campo) && !campo.equals("null")){
					
						switch(codCampo){
						//Los campos de ordenacion del jsp
							case 1:auxCampo = "id";break;
							case 2:auxCampo = "descripcion";break;
							case 3:auxCampo = "num_modelo";break;							
							default:break;
						}
					
				}
			return auxCampo;
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
			logger.error(" Error GestionModelos790Spring  - comprobarEntero() - numeroRegistros:b"+ resultado ,e);
			resultado = Integer.parseInt(Constantes.NUMERO_REGISTROS_PAGINA);
		}
		return resultado;
	}

	/**
	 * Crear query modelo 790.
	 *
	 * @param formulario el formulario
	 * @param paginaActual el pagina actual
	 * @param numReg el num reg
	 * @return el modelo query
	 */
	private ModeloQuery crearQueryModelo790(Modelo790Form formulario, String paginaActual, String numReg) {
		//Recoger los datos del formulario
		String numModelo = formulario.getNumModelo();
		String campo = formulario.getCampo();
		String direccion = formulario.getDireccion();
				
		if(formulario.getNumRegistro() == null && numReg == null){
			//Para el primer paso para el action, se establece que los registros por pagina son 5
			formulario.setNumRegistro(properties.getProperty("conf.numRegistrosListados"));
		}
		int numRegistros = 0;
		if(numReg != null && !"".equals(numReg)){
			try{
				numRegistros = Integer.parseInt(numReg);
			}catch(Exception e){
				logger.error(" Error GestionModelos790Spring - crearQueryModelo790 - numeroRegistros:"+ numRegistros ,e);
				numRegistros = Integer.parseInt(Constantes.NUMERO_REGISTROS_PAGINA);
			}
		}else{
				try{
					numRegistros = Integer.parseInt(formulario.getNumRegistro());
				}catch(Exception e){
					logger.error(" Error GestionModelos790Spring - crearQueryModelo790 - numeroRegistros:"+ numRegistros ,e);
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
			
		ModeloQuery modelo790Query = new ModeloQuery();
		if(paginaActual != null){
			modelo790Query.setMaxResults(tamanyoPaginacion);
			modelo790Query.setFirstResult(primerRegistro);
		}
		modelo790Query.setCalculateNumResults(true);
		//Comprobar los filtros para realizar la busqueda
				
		if(numModelo!= null  && !"".equals(numModelo)){
			try{	
				modelo790Query.setNumModelo(numModelo);
				modelo790Query.setNumModeloComparator(Comparator.LIKE_LEFT_RIGHT);		
			}catch(Exception e){
				logger.error(" Error GestionModelos790Spring - crearQueryModelo790 - numeroModelo:"+ numModelo ,e);
			}
		}					
	
		if(campo != null && !"0".equals(campo) && !"".equals(campo)){
			OrderType orden=("up".equals(direccion) == true ? OrderType.ASC : OrderType.DESC);			
			modelo790Query.addOrder(campo,orden);
		}
		modelo790Query.setCalculateNumResults(true);
		
		if(paginaActual!=null){
			modelo790Query.setMaxResults(tamanyoPaginacion);
			modelo790Query.setFirstResult(primerRegistro);
		}
		
		//Devulelve el centro gestor con los filtros
		return modelo790Query;		
	}
		
		/**
		 * Modelo manager.
		 *
		 * @return the modeloManager
		 */
		public ModeloManager modeloManager() {
			return modeloManager;
		}

		/**
		 * Establece el modelo manager.
		 *
		 * @param modeloManager the modeloManager to set
		 */
		public void setModeloManager(ModeloManager modeloManager) {
			this.modeloManager = modeloManager;
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
