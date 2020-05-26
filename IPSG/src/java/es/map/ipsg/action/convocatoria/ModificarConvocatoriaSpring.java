package es.map.ipsg.action.convocatoria;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.util.StringUtils;

import es.map.ips.common.model.OrderType;
import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringMessage;
import es.map.ips.common.spring.SpringMessages;
import es.map.ips.model.CamposPropiosQuery;
import es.map.ips.model.CentroGestorQuery;
import es.map.ips.model.ConvocatoriaQuery;
import es.map.ips.model.CuerpoEscalaQuery;
import es.map.ips.model.DatosPropiosConfiguracionQuery;
import es.map.ips.model.DiscapacidadQuery;
import es.map.ips.model.EspecialidadQuery;
import es.map.ips.model.FormaAccesoQuery;
import es.map.ips.model.MinisterioQuery;
import es.map.ips.model.Modelo;
import es.map.ips.model.ModeloQuery;
import es.map.ips.model.MotivoReduccionTasaQuery;
import es.map.ips.model.OtrosTitulosQuery;
import es.map.ips.model.PlantillaPropiosQuery;
import es.map.ips.model.ProvinciaExamenQuery;
import es.map.ips.model.TarifaQuery;
import es.map.ips.model.TituloOficialQuery;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.bean.CamposPropiosBean;
import es.map.ipsg.bean.CentroGestorBean;
import es.map.ipsg.bean.CrearConvocatoriaBean;
import es.map.ipsg.bean.CuerpoEscalaBean;
import es.map.ipsg.bean.DatosPropiosConfigBean;
import es.map.ipsg.bean.DiscapacidadBean;
import es.map.ipsg.bean.EspecialidadBean;
import es.map.ipsg.bean.FormaAccesoBean;
import es.map.ipsg.bean.LogConvocatoriaBean;
import es.map.ipsg.bean.MinisterioBean;
import es.map.ipsg.bean.MotivoReduccionTasaBean;
import es.map.ipsg.bean.OtrosTitulosBean;
import es.map.ipsg.bean.PlantillaPropiosBean;
import es.map.ipsg.bean.ProvinciaExamenBean;
import es.map.ipsg.bean.TipoDocumentoBean;
import es.map.ipsg.bean.TituloOficialBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.form.CrearConvocatoriaForm;
import es.map.ipsg.manager.CamposPropiosManager;
import es.map.ipsg.manager.CentroGestorManager;
import es.map.ipsg.manager.ConvocatoriasManager;
import es.map.ipsg.manager.CuerpoEscalaManager;
import es.map.ipsg.manager.DatosPropiosConfiguracionManager;
import es.map.ipsg.manager.DiscapacidadManager;
import es.map.ipsg.manager.EspecialidadManager;
import es.map.ipsg.manager.FormaAccesoManager;
import es.map.ipsg.manager.LogConvocatoriaManager;
import es.map.ipsg.manager.MinisterioManager;
import es.map.ipsg.manager.MotivoReduccionTasaManager;
import es.map.ipsg.manager.OtrosTitulosManager;
import es.map.ipsg.manager.PlantillaPropiosManager;
import es.map.ipsg.manager.ProvinciaExamenManager;
import es.map.ipsg.manager.TituloOficialManager;
import es.map.ipsg.manager.UsuarioCentrogestorManager;
import es.map.ipsg.manager.UsuarioManager;
import es.map.ipsg.util.Constantes;
import es.map.ipsg.util.Validacion;

/**
 * El Class ModificarConvocatoriaSpring.
 */
public class ModificarConvocatoriaSpring extends AbstractSpring {

	/** La constante BUNDLE_RESOURCE. */
	private static final String BUNDLE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_RESOURCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(ModificarConvocatoriaSpring.class);
	
	/** el convocatorias manager. */
	private ConvocatoriasManager convocatoriasManager;
	
	/** el cuerpo escala manager. */
	private CuerpoEscalaManager cuerpoEscalaManager;
	
	/** el centro gestor manager. */
	private CentroGestorManager centroGestorManager;
	
	/** el forma acceso manager. */
	private FormaAccesoManager formaAccesoManager;
	
	/** el titulo oficial manager. */
	private TituloOficialManager tituloOficialManager;
	
	/** el provincia examen manager. */
	private ProvinciaExamenManager provinciaExamenManager;
	
	/** el motivo reduccion tasa manager. */
	private MotivoReduccionTasaManager motivoReduccionTasaManager;
	
	/** el especialidad manager. */
	private EspecialidadManager especialidadManager;
	
	/** el usuario manager. */
	private UsuarioManager usuarioManager;
	
	/** el log convocatoria manager. */
	private LogConvocatoriaManager logConvocatoriaManager;
	
	/** el ministerios manager. */
	private MinisterioManager ministeriosManager;
	
	/** el otros titulos manager. */
	private OtrosTitulosManager otrosTitulosManager;
	
	/** el discapacidad manager. */
	private DiscapacidadManager discapacidadManager;
	
	/** el campos propios manager. */
	private CamposPropiosManager camposPropiosManager;
	
	/** el datos propios configuracion manager. */
	private DatosPropiosConfiguracionManager datosPropiosConfiguracionManager;
	
	/** el plantilla propios manager. */
	private PlantillaPropiosManager plantillaPropiosManager;
	
	/** el usuario centro gestor manager. */
	private UsuarioCentrogestorManager usuarioCentrogestorManager;
	
	/** el properties. */
	private Properties properties;
	
	/** La constante STRING_SIMPLEDATEFORMAT. */
	private static final String STRING_SIMPLEDATEFORMAT = "dd/MM/yyyy";
	
	/** La constante STRING_RESOURCEBOUNDLE. */
	private static final String STRING_RESOURCEBOUNDLE = "ResourceBoundle: ";
	
	/** La constante STRING_ERROR_PARSEANDO_FECHA_INICIO. */
	private static final String STRING_ERROR_PARSEANDO_FECHA_INICIO = "Error parseando la Fecha Inicio: ";
	
	/** La constante STRING_IMPORTE. */
	private static final String STRING_IMPORTE = "importe";
	
	/** La constante CENTRO_GESTOR. */
	private static final Character CENTRO_GESTOR = 'G';
	
	/**
	 * Crea una nueva modificar convocatoria spring.
	 */
	public ModificarConvocatoriaSpring() {
		try {
			setConvocatoriasManager((ConvocatoriasManager) getBean("convocatoriaManager"));
			setCuerpoEscalaManager((CuerpoEscalaManager) getBean("cuerposEscalaManager"));
			setCentroGestorManager((CentroGestorManager) getBean("centrosGestoresManager"));
			setFormaAccesoManager((FormaAccesoManager) getBean("formasAccesoManager"));
			setTituloOficialManager((TituloOficialManager) getBean("tituloOficialManager"));
			setProvinciaExamenManager((ProvinciaExamenManager) getBean("provinciaExamenManager"));
			setMotivoReduccionTasaManager((MotivoReduccionTasaManager) getBean("motivoReduccionTasaManager"));
			setEspecialidadManager((EspecialidadManager) getBean("especialidadManager"));
			setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
			setLogConvocatoriaManager((LogConvocatoriaManager) getBean("logConvocatoriaManager"));
			setMinisteriosManager((MinisterioManager) getBean("ministeriosManager"));
			setOtrosTitulosManager((OtrosTitulosManager) getBean("otrosTitulosManager"));
			setDiscapacidadManager((DiscapacidadManager) getBean("discapacidadManager"));
			setCamposPropiosManager((CamposPropiosManager) getBean("camposPropiosManager"));
			setDatosPropiosConfiguracionManager((DatosPropiosConfiguracionManager) getBean("datosPropiosConfiguracionManager"));
			setPlantillaPropiosManager((PlantillaPropiosManager)getBean("plantillaPropiosManager"));
			setUsuarioCentrogestorManager((UsuarioCentrogestorManager) getBean("usuarioCentrogestorManager"));
			properties = (Properties) getBean("propertiesBean");

		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error ModificarConvocatoriaSpring(): ",e);
		}
	}

	/**
	 * Obtiene el titulo oficial manager.
	 *
	 * @return el titulo oficial manager
	 */
	public TituloOficialManager getTituloOficialManager() {
		return tituloOficialManager;
	}

	/**
	 * Establece el titulo oficial manager.
	 *
	 * @param tituloOficialManager el nuevo titulo oficial manager
	 */
	public void setTituloOficialManager(
			TituloOficialManager tituloOficialManager) {
		this.tituloOficialManager = tituloOficialManager;
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		
		String menu_convocatoria = RESOURCE_BUNDLE.getString("field.menu.convocatorias");
		this.getRequest().getSession().setAttribute("pagActiva", menu_convocatoria);
		String subMenu_convocatoria = RESOURCE_BUNDLE.getString("field.menuLateral.convocatorias.buscar");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_convocatoria);
		
		getLogger().debug("ModificarConvocatoriaSpring -start");
		logger.info("Entra en el Action");
		CrearConvocatoriaForm formulario;
		formulario = (CrearConvocatoriaForm) form;

		try{
			//Obtengo los datos del usuario que esta logeado en la aplicacion
			UsuarioBean usuario = (UsuarioBean) getRequest().getSession().getAttribute("usuario");

			UsuarioQuery usuarioQuery = new UsuarioQuery();
			usuarioQuery.setLogin(usuario.getLogin());
			UsuarioBean usuarioBean = usuarioManager.buscarUsuario(usuarioQuery);			
			
			String accion = formulario.getAccion();
			String idConvocatoria = formulario.getIdConvocatoria();
			String ejercicio = formulario.getEjercicio();
			String centroGestor = formulario.getCentroGestor();
			String cuerpoEscala = formulario.getCuerpoEscala();
			String ministerioConvocante= formulario.getMinisterioConvocante();
			String numPlazas = formulario.getNumPlazas();
			String numPlazasDisc = formulario.getNumPlazasDisc();
			String FormaAcceso = formulario.getFormaAcceso();
			String codigoFormaAcceso = formulario.getCodigoFormaAcceso();
			String importe = formulario.getImporte();
			String dirigidoA = formulario.getDirigidoA();
			String TipoDocumentacion = formulario.getTipoDocumentacion();
			String[] Titulos = formulario.getTitulosSeleccionadosInput().split(",");			
			String fechaBoe = formulario.getFechaBoe();
			String fechaInicio = formulario.getFechaInicio();
			String fechaTermino = formulario.getFechaTermino();
			String fechaIniSub = (formulario.getFechaIniSub() != null)?formulario.getFechaIniSub():"";
			String fechaFinSub = (formulario.getFechaFinSub() != null)?formulario.getFechaFinSub():"";			
			String[] provincias = formulario.getProvinciaExamenSeleccionados();
			String[] motivosExencion = formulario.getMotivosExencionSeleccionados();
			String[] especialidades = formulario.getEspecialidadesSeleccionados();		
			String[] otrosTitulos = formulario.getOtrosTitulosSeleccionados();
			String[] discapacidad = formulario.getDiscapacidadSeleccionados();		
			String[] datosPropios =  (formulario.getDatosPropiosSeleccionados() != null)?formulario.getDatosPropiosSeleccionados():new String[0];
			boolean presencial = formulario.getPresencial();
			String enlace = formulario.getEnlace();
			boolean ocultarDatosPropios = formulario.getOcultarDatosPropios();
			
			try {
				logger.info("Accion: " + accion);
				if (accion == null) {
					accion = "nulo";
					this.comprobaciones(formulario);
				}
				if (accion.equals("null")) {
					accion = "nulo";
					this.comprobaciones(formulario);
				}
				
				if (accion.equals(STRING_IMPORTE) || (accion.equals("siglas"))) {
					this.comprobaciones(formulario);
				}
				if (accion.equals("siglas")) {
					MinisterioBean ministerio=this.cargarComboSiglasBoe(centroGestor);
					String siglasMinisterio = ministerio.getSiglas();
					String ministeConvocante = ministerio.getId().toString();
					if(siglasMinisterio!=null){
						formulario.setMinisterioSiglasAux(siglasMinisterio);
						formulario.setMinisterioConvocante(ministeConvocante);
					}else{
						formulario.setMinisterioSiglasAux("");
						formulario.setMinisterioConvocante("");
					}
					
					formulario.setModificadoFechaInhabil("-1");
				}
				if (accion.equals(STRING_IMPORTE))
				{		
					// tambien se setea el codigo de la forma de acceso seleccionada
					
					FormaAccesoQuery formaAccesoQuery = new FormaAccesoQuery();
					List<FormaAccesoBean> listFormaAcceso = formaAccesoManager.buscarFormaAccesoComboVisibilidad(formaAccesoQuery);
					
					Integer formaAccesoAux;
					String codFormaAccesoAux ="";
					
					FormaAccesoBean formAcceso;
					if( listFormaAcceso != null)
					{
						for(int j=0;j<listFormaAcceso.size();j++)
						{
							formAcceso = (FormaAccesoBean)listFormaAcceso.get(j);
							formaAccesoAux= (Integer)formAcceso.getId();
							codFormaAccesoAux=  (String)formAcceso.getCodigo();
				
							if(formaAccesoAux.intValue() == Integer.valueOf(formulario.getFormaAcceso()))
							{	
								if(codFormaAccesoAux != null)
								{
									formulario.setCodigoFormaAcceso(codFormaAccesoAux) ;		
								}
								else
								{
									formulario.setCodigoFormaAcceso("") ;	
								}			
							}	
						}
					}
				}
				if (accion.equals(STRING_IMPORTE)
						&& !(formulario.getCuerpoEscala().equals(""))
						&& !(formulario.getFormaAcceso().equals("0"))) {
	
					try {
						String idCentroGestor = formulario.getCentroGestor();
						String cuerpoEscala2 = formulario.getCuerpoEscala();
						String formaAcceso2 = formulario.getFormaAcceso();
						
						CentroGestorQuery centroGestorQuery = new CentroGestorQuery();
						centroGestorQuery.setId(Integer.valueOf(idCentroGestor));
						CentroGestorBean centroGestorBean = centroGestorManager.buscarCentroGestorUnico(centroGestorQuery);
						String ejercicio2 = centroGestorBean.getEjercicio();
	
						String importeFinal = this.calcularImporte(ejercicio2,
								cuerpoEscala2, formaAcceso2);
						formulario.setImporte(importeFinal);
	
					} catch (Exception e) {
						SpringMessages messages = new SpringMessages();
						messages.add("errorImporteNoExiste", new SpringMessage(
								"field.convocatoria.errores.importeNoExiste"));
						saveErrors(this.getRequest(), messages);
						formulario.setImporte("");
					}
	
				} else if (accion.equals("especialidad")){
					if(!(cuerpoEscala.equalsIgnoreCase(""))){
						logger.info("Cargando Especialidades Action");
						CuerpoEscalaQuery cuerpoEscalaQuery = new CuerpoEscalaQuery();
						cuerpoEscalaQuery.setId(Integer.parseInt(cuerpoEscala));
						List<EspecialidadBean> especialidadesList;
						especialidadesList = cuerpoEscalaManager
								.obtenerEspecialidadesCuerpoEscala(cuerpoEscalaQuery);
		
						setRequestAttribute("especialidadesDisp", especialidadesList);
						if (!(formulario.getFormaAcceso().equals("0"))
								&& !(cuerpoEscala.equalsIgnoreCase(""))) {
							String idCentroGestor = formulario.getCentroGestor();
							String cuerpoEscala2 = formulario.getCuerpoEscala();
							String formaAcceso = formulario.getFormaAcceso();
							
							CentroGestorQuery centroGestorQuery = new CentroGestorQuery();
							centroGestorQuery.setId(Integer.valueOf(idCentroGestor));
							CentroGestorBean centroGestorBean = centroGestorManager.buscarCentroGestorUnico(centroGestorQuery);
							String ejercicio2 = centroGestorBean.getEjercicio();
		
							String importeFinal = this.calcularImporte(ejercicio2,
									cuerpoEscala2, formaAcceso);
							formulario.setImporte(importeFinal);
						}
					}
					this.comprobaciones(formulario);
					
				} else if (accion.equals("alta")) {
					logger.info("Dando de alta convocatoria");
					logger.info("Id Convocatoria: " + idConvocatoria);
					logger.info("Ejercicio: " + ejercicio);
					logger.info("Centro gestor: " + centroGestor);
					logger.info("Cuerpo escala: " + cuerpoEscala);
					logger.info("Numero Plazas: " + numPlazas);
					logger.info("Numero Plazas Discapacitados: "+ numPlazasDisc);
					logger.info("FormaAcceso: " + FormaAcceso);
					logger.info("Importe: " + importe);
					logger.info("DirigidoA: " + dirigidoA);
					logger.info("TipoDocumentacion: " + TipoDocumentacion);
					logger.info("Presencial: " + presencial);
					logger.info("OcultarDatosPropios: " + ocultarDatosPropios);
					
					if (Titulos != null) {
						for (int i = 0; i < Titulos.length; i++) {
							logger.info("Titulo [" + i + "]: " + Titulos[i]);
						}
					}					
					logger.info("FechaBoe: " + fechaBoe);
					logger.info("Fecha inicio: " + fechaInicio);
					logger.info("Fecha Termino: " + fechaTermino);
					logger.info("Fecha inicio subsanacion: " + fechaIniSub);
					logger.info("Fecha Fin subsanacion: " + fechaFinSub);					
					if (provincias != null) {
						for (int i = 0; i < provincias.length; i++) {
							logger.info("Provincia [" + i + "]: "
									+ provincias[i]);
						}
					}
					if (motivosExencion != null) {
						for (int i = 0; i < motivosExencion.length; i++) {
							logger.info("Motivo Exencion [" + i + "]: "
									+ motivosExencion[i]);
						}
					}
					if (especialidades != null) {
						for (int i = 0; i < especialidades.length; i++) {
							logger.info("Especialidad [" + i + "]: "
									+ especialidades[i]);
						}
					}
					//MODIFICO LA CONVOCATORIA
					logger.info("Antes de crear el bean");
					CrearConvocatoriaBean bean = new CrearConvocatoriaBean();
					bean.setIdConvocatoria(Long.parseLong(idConvocatoria));
					bean.setEjercicio(ejercicio);
					bean.setIdCentroGestor(Integer.parseInt(centroGestor));
					bean.setIdCuerpoEscala(Integer.parseInt(cuerpoEscala));
					bean.setNumPlazas(Integer.parseInt(numPlazas));
					bean.setNumPlazasDisc(Integer.parseInt(numPlazasDisc));
					bean.setIdFormaAcceso(Integer.parseInt(FormaAcceso));
					bean.setCodigoFormaAcceso(codigoFormaAcceso);
					bean.setImporte(Float.parseFloat(importe));
					bean.setDirigidoA(dirigidoA);
					bean.setTipoDocumentacion(TipoDocumentacion.charAt(0));
					bean.setEnlace(enlace);
					
					if(presencial){
						bean.setPresencial('S');
					}else{
						bean.setPresencial('N');
					}

					if(ocultarDatosPropios) {
						bean.setOcultarDatosPropios('S');
					} else {
						bean.setOcultarDatosPropios('N');
					}
					
					if(Titulos!=null && Titulos.length>0 && !formulario.getTitulosSeleccionadosInput().isEmpty()){
						int[] idTitulos = new int[Titulos.length];
						logger.info("idTitulos length: " + idTitulos.length);
						logger.info("Titulos length: " + Titulos.length);

						for (int j = 0; j < Titulos.length; j++) {
							logger.info("j: " + j);
							idTitulos[j] = Integer.parseInt(Titulos[j]);
						}
						bean.setIdTitulos(idTitulos);
					}										
					bean.setFechaBoe(fechaBoe);
					bean.setFechaInicio(fechaInicio);
					bean.setFechaTermino(fechaTermino);
					bean.setFechaIniSub(fechaIniSub);
					bean.setFechaFinSub(fechaFinSub);				
					if (provincias != null) {
						int[] idProvincias = new int[provincias.length];
						logger.info("idProvincias length: " + idProvincias.length);
						logger.info("Provincias length: " + provincias.length);

						for (int j = 0; j < provincias.length; j++) {
							logger.info("j: " + j);
							idProvincias[j] = Integer.parseInt(provincias[j]);
						}
						bean.setIdProvincias(idProvincias);
					}
					if (motivosExencion != null) {
						int[] idMotivos = new int[motivosExencion.length];
						logger.info("idMotivosExencion length: " + idMotivos.length);
						logger.info("motivosExencion length: "
								+ motivosExencion.length);

						for (int j = 0; j < motivosExencion.length; j++) {
							logger.info("j: " + j);
							idMotivos[j] = Integer.parseInt(motivosExencion[j]);
						}
						bean.setIdMotivosExencion(idMotivos);
					}
					if (especialidades != null) {
						int[] idEspecialidades = new int[especialidades.length];

						logger.info("idEspecialidades length: "
								+ idEspecialidades.length);
						logger.info("Especialidades length: "
								+ especialidades.length);

						for (int j = 0; j < especialidades.length; j++) {
							logger.info("j: " + j);
							idEspecialidades[j] = Integer.parseInt(especialidades[j]);
						}
						bean.setIdEspecialidades(idEspecialidades);
					}
					
					if (discapacidad != null) {
						int[] idDiscapacidad = new int[discapacidad.length];
						logger.info("idDiscapacidad length: " + idDiscapacidad.length);
						logger.info("discapacidad length: " + discapacidad.length);

						for (int j = 0; j < discapacidad.length; j++) {
							logger.info("j: " + j);
							idDiscapacidad[j] = Integer.parseInt(discapacidad[j]);
						}
						bean.setIdDiscapacidad(idDiscapacidad);
					} 
					
					if (otrosTitulos != null) {
						int[] idOtroTitulo = new int[otrosTitulos.length];
						logger.info("idOtroTitulo length: " + idOtroTitulo.length);
						logger.info("otroTitulo length: " + otrosTitulos.length);

						for (int j = 0; j < otrosTitulos.length; j++) {
							logger.info("j: " + j);
							idOtroTitulo[j] = Integer.parseInt(otrosTitulos[j]);
						}
						bean.setIdOtrosTitulos(idOtroTitulo);
					} 
					
					if (datosPropios != null) {
						int[] idDatoPropio = new int[datosPropios.length];
						logger.info("idDatoPropio length: " + idDatoPropio.length);
						logger.info("datosPropios length: " + datosPropios.length);

						for (int j = 0; j < datosPropios.length; j++) {
							logger.info("j: " + j);
							idDatoPropio[j] = Integer.parseInt(datosPropios[j]);
						}
						bean.setIdDatosPropios(idDatoPropio);
					} 
					
					CentroGestorBean centroGestorBean = null;

					//Se cargan los combos de otrosTitulos y Discapacidad
					if (!StringUtils.isEmpty(formulario.getCentroGestor())) {
						centroGestorBean = obtenerCentroGestor(formulario);
						cargarOtrosDatos(formulario, otrosTitulos, discapacidad, datosPropios, bean, centroGestorBean);
					}
					
					//consultamos el modelo para evitar error
					if (centroGestorBean == null) {
						centroGestorBean = obtenerCentroGestor(formulario);
					}	
					Modelo modeloConvocatoria=centroGestorBean.getModelo();
					bean.setIdModeloAsociado((modeloConvocatoria.getIdModelo()).toString());
					logger.info("Despues de crear el bean");
					
					if(ministerioConvocante==null || "".equals(ministerioConvocante)){
						bean.setIdMinisterioConvocante(centroGestorBean.getIdMinisterio());
					}else{
					bean.setIdMinisterioConvocante(Integer.parseInt(ministerioConvocante));
					}
										
					convocatoriasManager.modificarConvocatoria(bean, formulario);
					
					//Se actualiza obligatoriedad de los datos propios - INI
					actualizarFormularioDatosPropios(formulario, datosPropios, bean);
					//Se actualiza obligatoriedad de los datos propios - FIN
					
					String cambios = "";
					
					
					generarRegistroLogConvocatoria(usuarioBean.getId(),cambios,new Long(idConvocatoria));
					this.getRequest().setAttribute("idConvocatoria",
							String.valueOf(idConvocatoria));
					if (formulario.getEstado() != null && Constantes.CONVOCATORIA_ESTADO_FINALIZADA.equals(formulario.getEstado())) {
						setRequestAttribute("mensajeConfirmacion" , RESOURCE_BUNDLE.getString("field.convocatoria.subsanacion.confirmacion"));
					} else {
					setRequestAttribute("mensajeConfirmacion" , RESOURCE_BUNDLE.getString("field.convocatoria.modificacion.confirmacion"));
					}
					
					return "successModifica";
	
				}
				
				checkRolUsuario(usuarioBean,formulario);
				cargaCombos(formulario);
				getLogger().debug("ModificarConvocatoriaSpring -end");
				return "success";
			} catch (Exception e) {
				logger.error("Error ModificarConvocatoriaSpring -accion ",e);
				return "nosuccess";
			}
	
		}catch(Exception e){
			logger.warn(e);
			this.getRequest().setAttribute("descripcionError", e.getMessage());
			logger.error("Error ModificarConvocatoriaSpring - doExecute: ",e);
			return "nosuccess";
		}
	}

	/**
	 * Actualizar formulario datos propios.
	 *
	 * @param formulario el formulario
	 * @param datosPropios el datos propios
	 * @param bean el bean
	 */
	 private void actualizarFormularioDatosPropios(CrearConvocatoriaForm formulario, String[] datosPropios,
			CrearConvocatoriaBean bean) {
		if(bean.getOcultarDatosPropios() != 'S') {
			if (bean != null && bean.getIdConvocatoria() > 0) {
				ArrayList<PlantillaPropiosBean> plantillaDatosPropios = obtenerPlantillaDatosPropios(bean);
				DatosPropiosConfigBean datosPropioConf = null;
				ArrayList<Integer> listaCamposId = new ArrayList<Integer>();
				
				for (int i = 0; i < datosPropios.length; i++) {
					datosPropioConf = datosPropiosConfiguracionManager.obtenerDatoPropioConCampo(Integer.valueOf(datosPropios[i]));
					if (datosPropioConf != null && datosPropioConf.getCampo() != null) {
						listaCamposId.add(datosPropioConf.getCampo().getIdCampo());
					}
				}
			
				if (plantillaDatosPropios != null && plantillaDatosPropios.size() > 0) {
					for (PlantillaPropiosBean plantillaPropiosBean : plantillaDatosPropios) {
						boolean isObligatorio = false;
						if (listaCamposId != null && listaCamposId.size() > 0 
								&& plantillaPropiosBean.getCampoPropioBean() != null
								&& listaCamposId.contains(plantillaPropiosBean.getCampoPropioBean().getId().intValue())
								&& formulario.isDatosPropiosFlag()) {
							isObligatorio = true;
						}
						plantillaPropiosBean.setObligatorio(isObligatorio);
						plantillaPropiosBean.setConvocatoriaBean(bean);
						plantillaPropiosManager.actualizarPlantillaPropios(plantillaPropiosBean);
					}
				}
			}
		}
	}

	/**
	 * Obtener plantilla datos propios.
	 *
	 * @param bean el bean
	 * @return el array list
	 */
	private ArrayList<PlantillaPropiosBean> obtenerPlantillaDatosPropios(CrearConvocatoriaBean bean) {
		PlantillaPropiosQuery plantillasPropiosQuery = new PlantillaPropiosQuery();
		ConvocatoriaQuery convocatoriaQuery = new ConvocatoriaQuery();
		convocatoriaQuery.setId(bean.getIdConvocatoria());
		plantillasPropiosQuery.setConvocatoria(convocatoriaQuery);
		plantillasPropiosQuery.addOrder("id", OrderType.ASC);
		plantillasPropiosQuery.setJoin_camposPropios(true);
		return plantillaPropiosManager.buscarPlantillaPropiosAll(plantillasPropiosQuery);
	}
	
	/**
	 * Cargar otros datos.
	 *
	 * @param formulario el formulario
	 * @param otrosTitulos el otros titulos
	 * @param discapacidad el discapacidad
	 * @param datosPropios el datos propios
	 * @param bean el bean
	 * @param centroGestorBean el centro gestor bean
	 */
	private void cargarOtrosDatos(CrearConvocatoriaForm formulario, String[] otrosTitulos, String[] discapacidad,
			String[] datosPropios, CrearConvocatoriaBean bean, CentroGestorBean centroGestorBean) {
		CentroGestorQuery centroGestorQuery = new CentroGestorQuery();
		centroGestorQuery.addIdCentroGestorIn(centroGestorBean.getId());
		
		OtrosTitulosQuery otrosTitulosQuery = new OtrosTitulosQuery();
		otrosTitulosQuery.setCentroGestor(centroGestorQuery);
		
		//Lista de Otros Titulos segun centro gestor
		List<OtrosTitulosBean> otrosTitulosList = obtenerListaOtrosTitulos(formulario, otrosTitulosQuery);
		
		setRequestAttribute("otrosTitulosDisp", otrosTitulosList);
		
		if (!(otrosTitulos != null && otrosTitulos.length > 0)) {
			otrosTitulos = new String[0];
		}
		
		int[] idOtrosTitulos = new int[otrosTitulos.length];

		logger.info("idOtrosTitulos length: "+ idOtrosTitulos.length);
		logger.info("otrosTitulos length: "+ otrosTitulos.length);

		for (int j = 0; j < otrosTitulos.length; j++) {
			logger.info("j: " + j);
			idOtrosTitulos[j] = Integer.parseInt(otrosTitulos[j]);
		}
		
		bean.setIdOtrosTitulos((formulario.isOtrosTitulosFlag())?idOtrosTitulos:null);

		//Lista de Discapacidad segun centro gestor
		List<DiscapacidadBean> discapacidadList = obtenerListaDiscapacidad(formulario, centroGestorQuery);
		
		setRequestAttribute("discapacidadDisp", discapacidadList);	
		
		if (!(discapacidad != null && discapacidad.length > 0)) {
			discapacidad = new String[0];
		}
		
		int[] idDiscapacidad = new int[discapacidad.length];

		logger.info("idDiscapacidad length: "+ idDiscapacidad.length);
		logger.info("discapacidad length: "+ discapacidad.length);

		for (int j = 0; j < discapacidad.length; j++) {
			logger.info("j: " + j);
			idDiscapacidad[j] = Integer.parseInt(discapacidad[j]);
		}
		bean.setIdDiscapacidad((formulario.isDiscapacidadFlag())?idDiscapacidad:null);
		
		//Lista de datos propios segun centro gestor
		ArrayList<DatosPropiosConfigBean> datosPropiosConfigList = obtenerListaCamposPropios(formulario, centroGestorBean);
		
		ArrayList<String> camposDisponibles = obtenerCamposDisponibles(datosPropiosConfigList);
		
		setRequestAttribute("camposDisponibles", camposDisponibles);	
		setRequestAttribute("datosPropiosConfigDisp", datosPropiosConfigList);	
		
		if (!(datosPropios != null && datosPropios.length > 0)) {
			datosPropios = new String[0];
		}
		
		int[] idDatosPropios = new int[datosPropios.length];

		logger.info("idDatosPropios length: " + idDatosPropios.length);
		logger.info("idDatosPropios length: " + datosPropios.length);

		for (int j = 0; j < datosPropios.length; j++) {
			logger.info("j: " + j);
			idDatosPropios[j] = Integer.parseInt(datosPropios[j]);
		}

		bean.setIdDatosPropios((formulario.isDatosPropiosFlag())?idDatosPropios:null);
	}
	
	/**
	 * Generar registro log convocatoria.
	 *
	 * @param idUsuario el id usuario
	 * @param cambios el cambios
	 * @param idConvocatoria el id convocatoria
	 */
	public void generarRegistroLogConvocatoria(Integer idUsuario, String cambios, Long idConvocatoria){
		//Cargo los datos en el bean del log generico que se usara para crear el registro en la tabla
		LogConvocatoriaBean logConvocatoriaBean = new LogConvocatoriaBean();
	
		logConvocatoriaBean.setTipoOperacion(Constantes.OPERACION_MODIFICACION);
		logConvocatoriaBean.setDetalle(RESOURCE_BUNDLE.getString("field.logConvocatoria.modificacion")+idConvocatoria.toString());
		logConvocatoriaBean.setIdConvocatoria(idConvocatoria);
		logConvocatoriaBean.setIdUsuario(idUsuario);
		
		logConvocatoriaManager.guardarLogConvocatoria(logConvocatoriaBean);
	}

	/**
	 * Carga combos.
	 *
	 * @param formulario el formulario
	 */
	private void cargaCombos(CrearConvocatoriaForm formulario) {
		
		logger.info("Cargando Combos");
		/*INI-TRA042*/
		if(formulario.getCentroGestor()!=null && !formulario.getCentroGestor().isEmpty()){
			CentroGestorQuery centroGestorQuery3 = new CentroGestorQuery();
			centroGestorQuery3.setId(Integer.parseInt(formulario.getCentroGestor()));
			CentroGestorBean centroGestorBean=centroGestorManager.buscarCentroGestorUnico(centroGestorQuery3);
			String modeloAsociado=centroGestorBean.getModelo().getNumModelo();
			setRequestAttribute("modeloAsociado", modeloAsociado);
			formulario.setModeloAsociado(modeloAsociado);
		}
		/*FIN-TRA042*/		
		
		CuerpoEscalaQuery cuerpoEscalaQuery;
		FormaAccesoQuery formaAccesoQuery = new FormaAccesoQuery();

		List<FormaAccesoBean> formaAccesoList;
		formaAccesoList = formaAccesoManager.buscarFormaAccesoComboVisibilidad(formaAccesoQuery);
		setRequestAttribute("formasAcceso", formaAccesoList);

		TituloOficialQuery tituloOficialQuery = new TituloOficialQuery();
		List<TituloOficialBean> tituloOficialList;
		tituloOficialList = tituloOficialManager.buscarTituloOficialComboVisibilidad(tituloOficialQuery);

		// Borramos los seleccionados del combo disponible
		String[] titulos=formulario.getTitulosSeleccionadosInput().split(",");
		if (titulos!=null && titulos.length > 0 && !formulario.getTitulosSeleccionadosInput().isEmpty()) {
			for (int i = 0; i < titulos.length; i++) {
				boolean noEncontrado = true;
				int j = 0;
				if(tituloOficialList != null && tituloOficialList.size()>0)
				{	
					while (j < tituloOficialList.size() && noEncontrado) {
						int idTituloSel = Integer.parseInt(titulos[i]);
						int idTituloOficial = tituloOficialList.get(j).getId()
								.intValue();
						if (idTituloSel == idTituloOficial) {
							noEncontrado = false;
							tituloOficialList.remove(j);
						} else {
							j++;
						}
					}
				}	
			}
		}
		setRequestAttribute("titulosDisp", tituloOficialList);

		ProvinciaExamenQuery provinciaExamenQuery = new ProvinciaExamenQuery();
		List<ProvinciaExamenBean> provinciasExamenList;
		provinciasExamenList = provinciaExamenManager.buscarProvinciaExamenComboVisibilidad(provinciaExamenQuery);

		// Borramos los seleccionados del combo disponible
		String[] provinciasExamen = formulario.getProvinciaExamenSeleccionados();
		if (provinciasExamen != null) {
			for (int i = 0; i < provinciasExamen.length; i++) {
				boolean noEncontrado = true;
				int j = 0;
				while (j < provinciasExamenList.size() && noEncontrado) {
					int idProvinciaSel = Integer.parseInt(provinciasExamen[i]);
					int idProvinciasList = provinciasExamenList.get(j).getId()
							.intValue();
					if (idProvinciaSel == idProvinciasList) {
						noEncontrado = false;
						provinciasExamenList.remove(j);
					} else {
						j++;
					}
				}
			}
		}
		setRequestAttribute("provinciasDisp", provinciasExamenList);

		MotivoReduccionTasaQuery motivoReduccionTasaQuery = new MotivoReduccionTasaQuery();
		List<MotivoReduccionTasaBean> motivosList;
		motivosList = motivoReduccionTasaManager
				.buscarMotivoReduccionTasaComboVisibilidad(motivoReduccionTasaQuery);

		// Borramos los seleccionados del combo disponible
		String[] motivosExencion = formulario.getMotivosExencionSeleccionados();
		if (motivosExencion != null) {
			for (int i = 0; i < motivosExencion.length; i++) {
				boolean noEncontrado = true;
				int j = 0;
				while (j < motivosList.size() && noEncontrado) {
					int idMotivoSel = Integer.parseInt(motivosExencion[i]);
					int idMotivosList = motivosList.get(j).getId().intValue();
					if (idMotivoSel == idMotivosList) {
						noEncontrado = false;
						motivosList.remove(j);
					} else {
						j++;
					}
				}
			}
		}
		
		String cuerpoEscala = formulario.getCuerpoEscala();
		setRequestAttribute("motivosDisp", motivosList);
		logger.info("CuerpoEscala antes del combo especialidad: #"+ cuerpoEscala + "#");
		if (cuerpoEscala != null && !(cuerpoEscala.equalsIgnoreCase(""))) {
			EspecialidadQuery especialidadesQuery= new EspecialidadQuery();
			especialidadesQuery.setEstado(Constantes.ESPECIALIDAD_ESTADO_ACTIVO);
			cuerpoEscalaQuery = new CuerpoEscalaQuery();
			cuerpoEscalaQuery.setId(Integer.parseInt(cuerpoEscala));
			especialidadesQuery.setCuerpoEscala(cuerpoEscalaQuery);
			
			List<EspecialidadBean> especialidadesList;
			especialidadesList = especialidadManager.buscarEspecialidadCombo(especialidadesQuery);

			// Borramos los seleccionados del combo disponible
			String[] especialidades = formulario.getEspecialidadesSeleccionados();
			if (especialidades != null) {
				for (int i = 0; i < especialidades.length; i++) {
					boolean noEncontrado = true;
					int j = 0;
					while (j < especialidadesList.size() && noEncontrado) {
						int idEspecialidadSel = Integer
								.parseInt(especialidades[i]);
						int idEspecialidadesList = especialidadesList.get(j)
								.getId().intValue();
						if (idEspecialidadSel == idEspecialidadesList) {
							noEncontrado = false;
							especialidadesList.remove(j);
						} else {
							j++;
						}
					}
				}
			}

			setRequestAttribute("especialidadesDisp", especialidadesList);
		}
		
		//Lista siglas ministerios Boe
		List<MinisterioBean> ministerioList;
		MinisterioQuery ministerioQuery = new MinisterioQuery();
		ministerioList = ministeriosManager.buscarMinisterioCombo(ministerioQuery);
		
		setRequestAttribute("ministerioList", ministerioList);
		

		ArrayList<TipoDocumentoBean> tipoDocumentoList = new ArrayList<TipoDocumentoBean>();
		TipoDocumentoBean tipoDocumentoBean = new TipoDocumentoBean();

		tipoDocumentoBean.setId("C");
		tipoDocumentoBean.setDescripcion(RESOURCE_BUNDLE
				.getString("field.tipoDocumento.Cualquiera"));
		tipoDocumentoList.add(tipoDocumentoBean);

		tipoDocumentoBean = new TipoDocumentoBean();
		tipoDocumentoBean.setId("N");
		tipoDocumentoBean.setDescripcion(RESOURCE_BUNDLE
				.getString("field.tipoDocumento.Ninguna"));
		tipoDocumentoList.add(tipoDocumentoBean);

		tipoDocumentoBean = new TipoDocumentoBean();
		tipoDocumentoBean.setId("R");
		tipoDocumentoBean.setDescripcion(RESOURCE_BUNDLE
				.getString("field.tipoDocumento.Exencion2"));
		tipoDocumentoList.add(tipoDocumentoBean);

		
		if (!formulario.getAccion().equals("alta")) {
			CentroGestorBean centroGestorBean = null;
			centroGestorBean = obtenerCentroGestor(formulario);
			CentroGestorQuery centroGestorQuery = new CentroGestorQuery();
			centroGestorQuery.addIdCentroGestorIn(centroGestorBean.getId());
			
			OtrosTitulosQuery otrosTitulosQuery = new OtrosTitulosQuery();
			otrosTitulosQuery.setCentroGestor(centroGestorQuery);
			
			//Lista de Otros Titulos segun centro gestor
			List<OtrosTitulosBean> otrosTitulosList = obtenerListaOtrosTitulos(formulario, otrosTitulosQuery);
			
			setRequestAttribute("otrosTitulosDisp", otrosTitulosList);

			//Lista de Discapacidad segun centro gestor
			List<DiscapacidadBean> discapacidadList = obtenerListaDiscapacidad(formulario, centroGestorQuery);
			
			setRequestAttribute("discapacidadDisp", discapacidadList);	
			
			//Lista de Campos Propios
			ArrayList<DatosPropiosConfigBean> datosPropiosConfigList = obtenerListaCamposPropios(formulario, centroGestorBean);
			
			ArrayList<String> camposDisponibles = obtenerCamposDisponibles(datosPropiosConfigList);
			
			setRequestAttribute("camposDisponibles", camposDisponibles);	
			setRequestAttribute("datosPropiosConfigDisp", datosPropiosConfigList);	
		}
	
		
		logger.info(STRING_RESOURCEBOUNDLE
				+ RESOURCE_BUNDLE.getString("field.tipoDocumento.Cualquiera"));
		logger.info(STRING_RESOURCEBOUNDLE
				+ RESOURCE_BUNDLE.getString("field.tipoDocumento.Ninguna"));
		logger.info(STRING_RESOURCEBOUNDLE
				+ RESOURCE_BUNDLE.getString("field.tipoDocumento.Exencion"));
		logger.info("Antes de rellenar los Atributos");
		
		setRequestAttribute("tipoDocumentoDisp", tipoDocumentoList);

		cargarCombosSeleccionados(formulario);

	}

	/**
	 * Calcular importe.
	 *
	 * @param pEjercicio el ejercicio
	 * @param pCuerpoEscala el cuerpo escala
	 * @param pFormaAcceso el forma acceso
	 * @return el string
	 * @throws Exception el exception
	 */
	private String calcularImporte(String pEjercicio, String pCuerpoEscala,
			String pFormaAcceso)throws Exception  {
		logger.info("Calcular Importe");

		String ejercicio = pEjercicio;
		String cuerpoEscala = pCuerpoEscala;
		String formaAcceso = pFormaAcceso;

		logger.info("Ejercicio: " + ejercicio);
		logger.info("CuerpoEscala: " + cuerpoEscala);
		logger.info("FormaAcceso: " + formaAcceso);

		try{
			int cuerpoEscalaId = Integer.parseInt(cuerpoEscala);

			int formaAccesoId = Integer.parseInt(formaAcceso);

			TarifaQuery tarifaQuery = new TarifaQuery();
			tarifaQuery.setEjercicio(ejercicio);
			String importeFinal = convocatoriasManager.buscarImporte(
			cuerpoEscalaId, formaAccesoId, tarifaQuery);
			logger.info("Importe Calculado: " + importeFinal);
			return importeFinal;
		}catch (Exception e) {
			logger.error("Error ModificarConvocatoriaSpring - calcularImporte: ",e);
			SpringMessages messages = new SpringMessages();
			messages.add("errorImporteNoExiste", new SpringMessage(
					"field.convocatoria.errores.importeNoExiste"));
			saveErrors(this.getRequest(), messages);
			return "";
		}
	}

	/**
	 * Cargar combos seleccionados.
	 *
	 * @param formulario el formulario
	 */
	private void cargarCombosSeleccionados(CrearConvocatoriaForm formulario) {
		ArrayList<TituloOficialBean> tituloSel = new ArrayList<TituloOficialBean>();
		ArrayList<ProvinciaExamenBean> provinciaSel = new ArrayList<ProvinciaExamenBean>();
		ArrayList<MotivoReduccionTasaBean> motivoSel = new ArrayList<MotivoReduccionTasaBean>();
		ArrayList<EspecialidadBean> especialidadSel = new ArrayList<EspecialidadBean>();
		ArrayList<DiscapacidadBean> discapacidadSel = new ArrayList<DiscapacidadBean>(); 
		ArrayList<OtrosTitulosBean> otrosTitulosSel = new ArrayList<OtrosTitulosBean>(); 
		ArrayList<DatosPropiosConfigBean> datosPropiosSel = new ArrayList<DatosPropiosConfigBean>();

		String[] titulos=formulario.getTitulosSeleccionadosInput().split(",");
		if(titulos!=null && titulos.length > 0 && !formulario.getTitulosSeleccionadosInput().isEmpty()){
			for (int i = 0; i < titulos.length; i++) {
				TituloOficialQuery tituloOficialQuery = new TituloOficialQuery();
				tituloOficialQuery.setId(Integer.parseInt(titulos[i]));
				List<TituloOficialBean> tituloOficialList;
				tituloOficialList = tituloOficialManager
						.buscarTituloOficialComboVisibilidad(tituloOficialQuery);
				if(tituloOficialList != null && tituloOficialList.size()>0)
				{	
					tituloSel.add(tituloOficialList.get(0));
				}	
			}
		}
		
		String[] provinciasExamen = formulario.getProvinciaExamenSeleccionados();
		if (provinciasExamen != null) {
			for (int i = 0; i < provinciasExamen.length; i++) {
				ProvinciaExamenQuery provinciaExamenQuery = new ProvinciaExamenQuery();
				provinciaExamenQuery.setId(Integer.parseInt(provinciasExamen[i]));
				List<ProvinciaExamenBean> provinciaExamenList;
				provinciaExamenList = provinciaExamenManager
						.buscarProvinciaExamenComboVisibilidad(provinciaExamenQuery);
				if(provinciaExamenList != null && provinciaExamenList.size()>0)
				{	
					provinciaSel.add(provinciaExamenList.get(0));
				}	
			}
		}
		
		String[] motivosExencion = formulario.getMotivosExencionSeleccionados();
		if (motivosExencion != null) {
			for (int i = 0; i < motivosExencion.length; i++) {
				MotivoReduccionTasaQuery motivoReduccionTasaQuery = new MotivoReduccionTasaQuery();
				motivoReduccionTasaQuery.setId(Integer
						.parseInt(motivosExencion[i]));
				List<MotivoReduccionTasaBean> motivosList;
				motivosList = motivoReduccionTasaManager
						.buscarMotivoReduccionTasaComboVisibilidad(motivoReduccionTasaQuery);
				motivoSel.add(motivosList.get(0));
			}
		}
		
		String[] especialidades = formulario.getEspecialidadesSeleccionados();
		if (especialidades != null) {
			for (int i = 0; i < especialidades.length; i++) {
				EspecialidadQuery especialidadQuery = new EspecialidadQuery();
				especialidadQuery.setId(Integer.parseInt(especialidades[i]));
				List<EspecialidadBean> especialidadesList;
				especialidadesList = especialidadManager
						.buscarEspecialidadCombo(especialidadQuery);
				especialidadSel.add(especialidadesList.get(0));
			}
		}
		
		String[] discapacidades = formulario.getDiscapacidadSeleccionados();
		if (discapacidades!=null && discapacidades.length > 0) {
			for (int i = 0; i < discapacidades.length; i++) {
				DiscapacidadQuery discapacidadQuery = new DiscapacidadQuery();
				discapacidadQuery.setIdDiscapacidad(Integer.valueOf(discapacidades[i]));
				List<DiscapacidadBean> discapacidadesList;
				discapacidadQuery.setJoin_centroGestor(true);
				discapacidadesList = discapacidadManager.buscarDiscapacidadComboVisibilidad(discapacidadQuery);
				if (discapacidadesList != null && discapacidadesList.get(0) != null 
						&& discapacidadesList.get(0).getCentroGestor() != null
						&& discapacidadesList.get(0).getCentroGestor().equals(formulario.getCentroGestor())) {
					discapacidadSel.add(discapacidadesList.get(0));
				}	
			}
		} 
		
		String[] otrosTitulos = formulario.getOtrosTitulosSeleccionados();
		if (otrosTitulos!=null && otrosTitulos.length > 0) {
			for (int i = 0; i < otrosTitulos.length; i++) {
				OtrosTitulosQuery otrosTitulosQuery = new OtrosTitulosQuery();
				otrosTitulosQuery.setIdOtroTitulo(Integer.valueOf(otrosTitulos[i]));
				List<OtrosTitulosBean> otrosTitulosList;
				otrosTitulosQuery.setJoin_centroGestor(true);
				otrosTitulosList = otrosTitulosManager.buscarOtrosTitulosComboVisibilidad(otrosTitulosQuery);
				if (otrosTitulosList != null && otrosTitulosList.get(0) != null 
						&& otrosTitulosList.get(0).getCentroGestor() != null
						&& otrosTitulosList.get(0).getCentroGestor().equals(formulario.getCentroGestor())) {
					otrosTitulosSel.add(otrosTitulosList.get(0));
				}	
			}
		} 
		
		String[] datosPropios = formulario.getDatosPropiosSeleccionados();
		if (datosPropios!=null && datosPropios.length > 0) {
			for (int i = 0; i < datosPropios.length; i++) {
				DatosPropiosConfiguracionQuery datosPropiosQuery = new DatosPropiosConfiguracionQuery();
				datosPropiosQuery.setIdDesplegable(Integer.valueOf(datosPropios[i]));
				List<DatosPropiosConfigBean> datosPropiosList;
				datosPropiosQuery.setJoin_campo(true);
				datosPropiosList = datosPropiosConfiguracionManager.buscarDatosPropiosConfigbyCampo(datosPropiosQuery);
				if (formulario.getCentroGestorAnterior() != null && formulario.getCentroGestor() != null 
						&& formulario.getCentroGestorAnterior().equals(formulario.getCentroGestor())) {
					datosPropiosSel.add(datosPropiosList.get(0));
				}
			}
		}
		
		setRequestAttribute("titulosSel", tituloSel);
		setRequestAttribute("provinciasSel", provinciaSel);
		setRequestAttribute("motivosSel", motivoSel);
		setRequestAttribute("especialidadesSel", especialidadSel);
		setRequestAttribute("discapacidadSel", discapacidadSel);
		setRequestAttribute("otrosTitulosSel", otrosTitulosSel);
		setRequestAttribute("datosPropiosSel", datosPropiosSel);
	}

	
	/**
	 * Check rol usuario.
	 *
	 * @param usuarioBean el usuario bean
	 * @param form el form
	 */
	public void checkRolUsuario(UsuarioBean usuarioBean, CrearConvocatoriaForm form){
		logger.info("Perfil usuario: "+usuarioBean.getIdPerfil());
		setRequestAttribute("rol", usuarioBean.getIdPerfil());
		
		String sPerfilUsuario = comprobarPerfilUsuario(usuarioBean);
		List<CentroGestorBean> listaCentrosGestores = new ArrayList<CentroGestorBean>();

		if(sPerfilUsuario.equalsIgnoreCase(Constantes.ROL_GESTOR))
		{
			listaCentrosGestores = usuarioCentrogestorManager.buscarCentrosGestoresByIdUsuario(usuarioBean.getId());
			form.setListaCentrosGestores(listaCentrosGestores);
			setRequestAttribute("listaCentrosGestores", listaCentrosGestores);
		}
	}

	/*INI-TRA042*/	
	/**
	 * Comprobar perfil usuario.
	 *
	 * @param usuarioBean2 el id usuario
	 * @return el string
	 */
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
	 * Cargar combo siglas boe.
	 *
	 * @param idCentroGestor el id centro gestor
	 * @return el ministerio bean
	 */
	private MinisterioBean cargarComboSiglasBoe(String idCentroGestor) {
		try{
			if(!"".equals(idCentroGestor)){
				CentroGestorQuery centroGestorQuery = new CentroGestorQuery();
				centroGestorQuery.setId(Integer.valueOf(idCentroGestor));
				CentroGestorBean centroGestorBean = centroGestorManager.buscarCentroGestorUnico(centroGestorQuery);
				Integer idMinisterio = centroGestorBean.getIdMinisterio();
				
				MinisterioQuery ministerioQuery = new MinisterioQuery();
				ministerioQuery.setId(idMinisterio);
				MinisterioBean ministerioBean = ministeriosManager.buscarMinisterio(ministerioQuery);
				
				return ministerioBean;
			}else{
				return null;
			}
		}catch (Exception e){
			logger.error("Error ModificarConvocatoriaSpring - cargarCombosSiglasBoe: ",e);
			SpringMessages messages = new SpringMessages();
			messages.add("errorCentroGestorNoExiste", new SpringMessage("field.convocatoria.errores.centroGestorNoExiste"));
			saveErrors(this.getRequest(), messages);
			return null;			
		}
	}
	
/**
 * Comprobaciones.
 *
 * @param formulario el formulario
 */
private void comprobaciones (CrearConvocatoriaForm formulario) {
		
		SpringMessages errors = new SpringMessages();
		Validacion validacion = new Validacion();

		if ((formulario.getCentroGestor() == null || formulario.getCentroGestor().equalsIgnoreCase("")) &&
			this.getSession().getAttribute("errorCentroGestor") != null){
				SpringMessage error = new SpringMessage("field.convocatoria.errores.centroGestorRequerido");
				errors.add("dsErrorCentroGestor",error);
				saveErrors(this.getRequest().getSession(),errors);
		}
		if ((formulario.getCuerpoEscala() == null || formulario.getCuerpoEscala().equalsIgnoreCase("")) &&
			this.getSession().getAttribute("errorCuerpoEscala") != null){
				SpringMessage error = new SpringMessage("field.convocatoria.errores.cuerpoEscalaRequerido");
				errors.add("dsErrorCuerpoEscala",error);
				saveErrors(this.getRequest().getSession(),errors);
		}
		
		if ((formulario.getMinisterioConvocante() == null || formulario.getMinisterioConvocante().equalsIgnoreCase("")) &&
			this.getSession().getAttribute("errorMinisterioConvocante") != null){
				SpringMessage error = new SpringMessage("field.convocatoria.errores.MinisterioConvocanteRequerido");
				errors.add("dsErrorMinisterioConvocante",error);
				saveErrors(this.getRequest().getSession(),errors);
		}
		
		if ((formulario.getNumPlazas()== null || formulario.getNumPlazas().equalsIgnoreCase("")) &&
			this.getSession().getAttribute("errorNumPlazas") != null) {
				SpringMessage error = new SpringMessage("field.convocatoria.errores.numPlazasRequerido");
				errors.add("dsErrorNumPlazas",error);
				saveErrors(this.getRequest().getSession(),errors);
		}
		if((formulario.getNumPlazas() != null && !"".equals(formulario.getNumPlazas())) &&
			this.getSession().getAttribute("errorNumPlazasNumerico") != null) {
				try{
					Integer.parseInt(formulario.getNumPlazas());
				}catch(Exception e){
					SpringMessage error = new SpringMessage("field.convocatoria.errores.numPlazasNumerico");
					errors.add("dsErrorNumPlazas",error);
					saveErrors(this.getRequest().getSession(),errors);
				}
		}		
		if ((formulario.getNumPlazasDisc() == null || formulario.getNumPlazasDisc().equalsIgnoreCase("")) &&
			this.getSession().getAttribute("errorNumPlazasDisc") != null){
				SpringMessage error = new SpringMessage("field.convocatoria.errores.NumPlazasDiscapacitadosRequerido");
				errors.add("dsErrorNumPlazasDisc",error);
				saveErrors(this.getRequest().getSession(),errors);
		}
		if((formulario.getNumPlazasDisc() != null && !"".equals(formulario.getNumPlazasDisc())) &&
			this.getSession().getAttribute("errorNumPlazasDiscNumerico") != null) {
				try{
					Integer.parseInt(formulario.getNumPlazasDisc());
				}catch(Exception e){
					SpringMessage error = new SpringMessage("field.convocatoria.errores.numPlazasDiscapacitadosNumerico");
					errors.add("dsErrorNumPlazasDisc",error);
					saveErrors(this.getRequest().getSession(),errors);
				}
		}
		
		if((formulario.getNumPlazas() != null && !(formulario.getNumPlazasDisc().equalsIgnoreCase("")))&&
				(formulario.getNumPlazasDisc() != null && !(formulario.getNumPlazasDisc().equalsIgnoreCase("")))){
			try{
				if ((this.getSession().getAttribute("errorNumPlazas<numPlazasDisc") != null) &&	
					Integer.parseInt(formulario.getNumPlazas())<Integer.parseInt(formulario.getNumPlazasDisc())){
						SpringMessage error = new SpringMessage("field.convocatoria.errores.numPlazas<numPlazasDisc");
						errors.add("dsErrorNumPlazas<numPlazasDisc",error);
						saveErrors(this.getRequest().getSession(),errors);
				}
			}catch(Exception e){
				logger.error("Error ModificarConvocatoriaSpring - numPlazas<numPlazasDisc: ",e);
			}
			
		}
		if ((formulario.getFormaAcceso() == null || formulario.getFormaAcceso().equalsIgnoreCase("0")) &&
			this.getSession().getAttribute("errorFormaAcceso") != null) {	
				SpringMessage error = new SpringMessage("field.convocatoria.errores.formaAccesoRequerido");
				errors.add("dsErrorFormaAcceso",error);
				saveErrors(this.getRequest().getSession(),errors);
		}
		
		if (!formulario.getAccion().equals(STRING_IMPORTE) &&
			 (formulario.getImporte()== null || formulario.getImporte().equalsIgnoreCase("")) &&
				this.getSession().getAttribute("errorImporte") != null) {
					SpringMessage error = new SpringMessage("field.convocatoria.errores.importeRequerido");
					errors.add("dsErrorImporte",error);
					saveErrors(this.getRequest().getSession(),errors);
		}

		if ((formulario.getDirigidoA()== null || formulario.getDirigidoA().equalsIgnoreCase("")) &&
			this.getSession().getAttribute("errorDirigidoA") != null) {
				SpringMessage error = new SpringMessage("field.convocatoria.errores.dirigidoARequerido");
				errors.add("dsErrorDirigidoA",error);
				saveErrors(this.getRequest().getSession(),errors);
		}

		if ((formulario.getTipoDocumentacion() == null || formulario.getTipoDocumentacion().equalsIgnoreCase("")) &&
			this.getSession().getAttribute("errorTipoDocumentacion") != null) {			
				SpringMessage error = new SpringMessage("field.convocatoria.errores.tipoDocumentoRequerido");
				errors.add("dsErrorTipoDocumentacion",error);
				saveErrors(this.getRequest().getSession(),errors);
		}

		if ((formulario.getTitulosSeleccionadosInput().split(",")==null || formulario.getTitulosSeleccionadosInput().split(",").length==0 || formulario.getTitulosSeleccionadosInput().isEmpty()) &&
			this.getSession().getAttribute("errorTitulosSeleccionados") != null) {
				SpringMessage error = new SpringMessage("field.convocatoria.errores.titulosExigidosRequerido");
				errors.add("dsErrorTitulosSeleccionados",error);
				saveErrors(this.getRequest().getSession(),errors);
		}

		if((formulario.getFechaBoe() != null && !"".equals(formulario.getFechaBoe())) &&
			(!(validacion.isFechaValida(formulario.getFechaBoe()))) &&
				this.getSession().getAttribute("errorFechaBoe") != null) {
					SpringMessage error = new SpringMessage("field.convocatoria.errores.fechaBoeRequerido");
					errors.add("dsErrorFechaBoe",error);
					saveErrors(this.getRequest().getSession(),errors);
		}
		if((formulario.getFechaInicio() != null && !"".equals(formulario.getFechaInicio())) &&
			(!(validacion.isFechaValida(formulario.getFechaInicio()))) &&
				this.getSession().getAttribute("errorFechaInicio") != null) {
					SpringMessage error = new SpringMessage("field.convocatoria.errores.fechaInicioRequerido");
					errors.add("dsErrorFechaInicio",error);
					saveErrors(this.getRequest().getSession(),errors);
		}
		if((formulario.getFechaTermino() != null && !"".equals(formulario.getFechaTermino())) &&
			(!(validacion.isFechaValida(formulario.getFechaTermino()))) &&
				this.getSession().getAttribute("errorFechaFin") != null) {
					SpringMessage error = new SpringMessage("field.convocatoria.errores.fechaFinRequerido");
					errors.add("dsErrorFechaFin",error);
					saveErrors(this.getRequest().getSession(),errors);
		}
		if ((formulario.getFechaBoe()!=null&&!(formulario.getFechaBoe().equalsIgnoreCase("")))
				&& (formulario.getFechaInicio()!=null&&!(formulario.getFechaInicio().equalsIgnoreCase("")))) {
			
			SimpleDateFormat formatoDelTexto = new SimpleDateFormat(STRING_SIMPLEDATEFORMAT);
			Date dFechaInicio = null;
			Date dFechaBoe = null;
			
			try {
				dFechaInicio = formatoDelTexto.parse(formulario.getFechaInicio());
			} catch (ParseException ex) {
				SpringMessage error = new SpringMessage("field.convocatoria.errores.fechaInicioNoValido");
				logger.warn(STRING_ERROR_PARSEANDO_FECHA_INICIO+formulario.getFechaInicio());
				logger.error(STRING_ERROR_PARSEANDO_FECHA_INICIO+formulario.getFechaInicio(),ex);
				errors.add("dsErrorFechaInicioFormato",error);
				saveErrors(this.getRequest().getSession(),errors);
			}
			
			try {
				dFechaBoe = formatoDelTexto.parse(formulario.getFechaBoe());
			} catch (ParseException ex) {
				logger.warn("Error parseando la Fecha Boe: "+formulario.getFechaBoe());
				logger.error("Error parseando la Fecha Boe: "+formulario.getFechaBoe(),ex);
				SpringMessage error = new SpringMessage("field.convocatoria.errores.fechaBoeNoValido");
				errors.add("dsErrorFechaBoeFormato",error);
				saveErrors(this.getRequest().getSession(),errors);
			}
			
			if((dFechaInicio!=null && dFechaBoe!=null) &&
				dFechaInicio.getTime()<dFechaBoe.getTime()){
					SpringMessage error = new SpringMessage("field.convocatoria.errores.fechaMayorBOE");
					errors.add("dsErrorFechaMayorBOE",error);
					saveErrors(this.getRequest().getSession(),errors);
			}
		}
		
		if ((formulario.getFechaInicio() != null && !(formulario.getFechaInicio().equalsIgnoreCase("")))&&
				(formulario.getFechaTermino() != null && !(formulario.getFechaTermino().equalsIgnoreCase("")))) {
			SimpleDateFormat formatoDelTexto = new SimpleDateFormat(STRING_SIMPLEDATEFORMAT);
			Date dFechaInicio = null;
			Date dFechaTermino = null;
			
			try {
				dFechaInicio = formatoDelTexto.parse(formulario.getFechaInicio());
			} catch (ParseException ex) {
				if(formulario.getFechaBoe()==null || formulario.getFechaBoe().equalsIgnoreCase("")){
					logger.warn(STRING_ERROR_PARSEANDO_FECHA_INICIO+formulario.getFechaInicio());
					logger.error(STRING_ERROR_PARSEANDO_FECHA_INICIO+formulario.getFechaInicio(),ex);
					SpringMessage error = new SpringMessage("field.convocatoria.errores.fechaInicioNoValido");
					errors.add("dsErrorFechaInicioFormato",error);
					saveErrors(this.getRequest().getSession(),errors);
				}
			}
			
			try {
				dFechaTermino = formatoDelTexto.parse(formulario.getFechaTermino());
			} catch (ParseException ex) {
				logger.warn("Error parseando la Fecha Termino: "+formulario.getFechaTermino());
				logger.error("Error parseando la Fecha Termino: "+formulario.getFechaTermino(),ex);
				SpringMessage error = new SpringMessage("field.convocatoria.errores.fechaFinNoValido");
				errors.add("dsErrorFechaFinFormato",error);
				saveErrors(this.getRequest().getSession(),errors);
			}
			
			if((dFechaInicio!=null && dFechaTermino!=null) &&
				dFechaInicio.getTime() > dFechaTermino.getTime()){
					SpringMessage error = new SpringMessage("field.convocatoria.errores.fechaMayorInicio");
					errors.add("dsErrorFechaMayorInicio",error);
					saveErrors(this.getRequest().getSession(),errors);
			}				
		}
		
	}

	/**
	 * Obtener lista otros titulos.
	 *
	 * @param formulario el formulario
	 * @param otrosTitulosQuery el otros titulos query
	 * @return el list
	 */
	private List<OtrosTitulosBean> obtenerListaOtrosTitulos(CrearConvocatoriaForm formulario,
			OtrosTitulosQuery otrosTitulosQuery) {
		List<OtrosTitulosBean> otrosTitulosList;
		otrosTitulosList = otrosTitulosManager.buscarOtrosTitulosComboVisibilidad(otrosTitulosQuery);
		if (formulario!=null && formulario.getOtrosTitulosSeleccionados()!=null && formulario.getOtrosTitulosSeleccionados().length>0) {
			for (String otroTituloSel  : formulario.getOtrosTitulosSeleccionados()) {
				boolean noEncontrado = true;
				int j = 0;
				while (j < otrosTitulosList.size() && noEncontrado) {
					int idOtroTituloSel = Integer.valueOf(otroTituloSel);
					int idOtroTituloList = otrosTitulosList.get(j).getId().intValue();
					if (idOtroTituloSel == idOtroTituloList) {
						noEncontrado = false;
						otrosTitulosList.remove(j);
					} else {
						j++;
					}
				}
			}
		}
		return otrosTitulosList;
	}

	/**
	 * Obtener campos disponibles.
	 *
	 * @param datosPropiosConfigList el datos propios config list
	 * @return el array list
	 */
	private ArrayList<String> obtenerCamposDisponibles(ArrayList<DatosPropiosConfigBean> datosPropiosConfigList) {
		ArrayList<String> camposDisponibles = new ArrayList<String>();
		
		for (DatosPropiosConfigBean datoPropioBean : datosPropiosConfigList) {
			if (datoPropioBean.getCampo() != null && !camposDisponibles.contains(datoPropioBean.getCampo().getCampo())) {
				camposDisponibles.add(datoPropioBean.getCampo().getCampo());
			}
		}
		return camposDisponibles;
	}
		
	/**
	 * Obtener lista campos propios.
	 *
	 * @param formulario el formulario
	 * @param centroGestorBean el centro gestor bean
	 * @return el array list
	 */
	private ArrayList<DatosPropiosConfigBean> obtenerListaCamposPropios(CrearConvocatoriaForm formulario,
			CentroGestorBean centroGestorBean) {
		ArrayList<DatosPropiosConfigBean> datosPropiosConfigList = null;
		ArrayList<CamposPropiosBean> camposPropiosList = null;
		CamposPropiosQuery camposPropiosQuery = new CamposPropiosQuery();
		DatosPropiosConfiguracionQuery datosPropiosConfigQuery = new DatosPropiosConfiguracionQuery();
		
		if (centroGestorBean != null && centroGestorBean.getModelo() != null && centroGestorBean.getModelo().getIdModelo() != null) {
			camposPropiosQuery.addModeloIn(centroGestorBean.getModelo().getIdModelo());
			
			//Se obtienen las plantilla
			ArrayList<PlantillaPropiosBean> plantillasPropias = obtenerPlantillasPropios(formulario, centroGestorBean);
			
			if (plantillasPropias != null && plantillasPropias.size() > 0) {
				for (PlantillaPropiosBean plantillaPropiosBean : plantillasPropias) {
					camposPropiosQuery.addIdIn(plantillaPropiosBean.getCampoPropioBean().getId().intValue());
				}
			}
			
			camposPropiosList = camposPropiosManager.buscarCamposPropiosbyCampo(camposPropiosQuery);
			
			for (CamposPropiosBean camposPropiosBean : camposPropiosList) {
				if (camposPropiosBean.getId() != null && camposPropiosBean.getId() > 0) {
					datosPropiosConfigQuery.addCampoIn(camposPropiosBean.getId().intValue());
				}
			}
			
			datosPropiosConfigQuery.setJoin_campo(true);
			datosPropiosConfigList = datosPropiosConfiguracionManager.buscarDatosPropiosConfigbyCampo(datosPropiosConfigQuery);
		}
		return datosPropiosConfigList;
	}
	
	/**
	 * Obtener lista discapacidad.
	 *
	 * @param formulario el formulario
	 * @param centroGestorQuery el centro gestor query
	 * @return el list
	 */
	private List<DiscapacidadBean> obtenerListaDiscapacidad(CrearConvocatoriaForm formulario,
			CentroGestorQuery centroGestorQuery) {
		DiscapacidadQuery discapacidadQuery = new DiscapacidadQuery();
		discapacidadQuery.setCentroGestor(centroGestorQuery);
		
		List<DiscapacidadBean> discapacidadList;
		discapacidadList = discapacidadManager.buscarDiscapacidadComboVisibilidad(discapacidadQuery);
		if (formulario!=null && formulario.getDiscapacidadSeleccionados()!=null && formulario.getDiscapacidadSeleccionados().length>0) {
			for (String otroTituloSel  : formulario.getDiscapacidadSeleccionados()) {
				boolean noEncontrado = true;
				int j = 0;
				while (j < discapacidadList.size() && noEncontrado) {
					int idOtroTituloSel = Integer.valueOf(otroTituloSel);
					int idOtroTituloList = discapacidadList.get(j).getId().intValue();
					if (idOtroTituloSel == idOtroTituloList) {
						noEncontrado = false;
						discapacidadList.remove(j);
					} else {
						j++;
					}
				}
			}
		}
		return discapacidadList;
	}
	
	/**
	 * Obtener plantillas propios.
	 *
	 * @param formulario el formulario
	 * @param centroGestorBean el centro gestor bean
	 * @return el array list
	 */
	private ArrayList<PlantillaPropiosBean> obtenerPlantillasPropios(CrearConvocatoriaForm formulario, CentroGestorBean centroGestorBean) {
		PlantillaPropiosQuery plantillasPropiosQuery = new PlantillaPropiosQuery();
		plantillasPropiosQuery.setTipoPlantilla(CENTRO_GESTOR);
		ModeloQuery modeloQuery = new ModeloQuery();
		modeloQuery.setIdModelo(centroGestorBean.getModelo().getIdModelo());
		plantillasPropiosQuery.setModelo(modeloQuery);
		CentroGestorQuery centroGestorQueryAux = new CentroGestorQuery();
		centroGestorQueryAux.setId(centroGestorBean.getId());
		plantillasPropiosQuery.setCentroGestor(centroGestorQueryAux);
		plantillasPropiosQuery.addOrder("id", OrderType.ASC);
		ArrayList<PlantillaPropiosBean> plantillasPropias = plantillaPropiosManager.buscarPlantillaPropiosAll(plantillasPropiosQuery);
		
		if(centroGestorBean.getModelo().getNumModelo().equals(Constantes.MODELO79007)){
			plantillasPropias = obtenerPlantillaFinal(formulario.getCuerpoEscala(), plantillasPropias);
		}
		return plantillasPropias;
	}
	
	/**
	 * Obtener centro gestor.
	 *
	 * @param formulario el formulario
	 * @return el centro gestor bean
	 */
	private CentroGestorBean obtenerCentroGestor(CrearConvocatoriaForm formulario) {
		CentroGestorQuery centroGestorQuery = new CentroGestorQuery();
		centroGestorQuery.setId(Integer.valueOf(formulario.getCentroGestor()));
		return  centroGestorManager.buscarCentroGestorUnico(centroGestorQuery);
	}
	
	/**
	 * Obtener plantilla final.
	 *
	 * @param idCuerpoEscala el id cuerpo escala
	 * @param plantillasPropias el plantillas propias
	 * @return el array list
	 */
	private ArrayList<PlantillaPropiosBean> obtenerPlantillaFinal(String idCuerpoEscala,
			ArrayList<PlantillaPropiosBean> plantillasPropias) {
		// Adaptaciones Secretarios Judiciales (solo campos Datos A y Datos B)
		CuerpoEscalaBean cuerpoEscalaBean = new CuerpoEscalaBean(); 

		if(!StringUtils.isEmpty(idCuerpoEscala)){
			cuerpoEscalaBean = cuerpoEscalaManager.obtenerCuerpoEscala(Integer.parseInt(idCuerpoEscala));
		}
		
		ArrayList<String> codSecretarios = new ArrayList<String>(Arrays.asList(properties.getProperty("codigos.secretarios.judiciales").split(",")));	
		ArrayList<String> codCamposSecretario = new ArrayList<String>(Arrays.asList(properties.getProperty("codigos.modelo007.pruebaOptativa").split(",")));
		
		ArrayList<PlantillaPropiosBean> plantillasEliminarPrueba = new ArrayList<PlantillaPropiosBean>();
		ArrayList<PlantillaPropiosBean> plantillasEliminarDatos = new ArrayList<PlantillaPropiosBean>();
		for (PlantillaPropiosBean plantillaBean : plantillasPropias) {
			if (codCamposSecretario.contains(plantillaBean.getCampoPropioBean().getId().toString())) {
				plantillasEliminarPrueba.add(plantillaBean);
			} else {
				plantillasEliminarDatos.add(plantillaBean);
			}
		}
		
		//Si no es secretario se cargan del modelo 790007 solo los datos asociados 1-Prueba Optativa Idioma y 2-Prueba Derecho Foral
		if (!StringUtils.isEmpty(cuerpoEscalaBean.getCodigo()) && !codSecretarios.contains(cuerpoEscalaBean.getCodigo())) {
			for (PlantillaPropiosBean plantillaPrueba : plantillasEliminarDatos) {
				plantillasPropias.remove(plantillaPrueba);
			}
		//Si es secretario se cargan del modelo 790007 solo los datos asociados 1-Prueba Optativa Idioma y 2-Prueba Derecho Foral	
		}else{
			for (PlantillaPropiosBean plantillaDatos : plantillasEliminarPrueba) {
				plantillasPropias.remove(plantillaDatos);
			}
		}
		return plantillasPropias;
	}

	/**
	 * Obtiene el convocatorias manager.
	 *
	 * @return el convocatorias manager
	 */
	public ConvocatoriasManager getConvocatoriasManager() {
		return convocatoriasManager;
	}

	/**
	 * Establece el convocatorias manager.
	 *
	 * @param convocatoriasManager el nuevo convocatorias manager
	 */
	public void setConvocatoriasManager(
			ConvocatoriasManager convocatoriasManager) {
		this.convocatoriasManager = convocatoriasManager;
	}

	/**
	 * Obtiene el cuerpo escala manager.
	 *
	 * @return el cuerpo escala manager
	 */
	public CuerpoEscalaManager getCuerpoEscalaManager() {
		return cuerpoEscalaManager;
	}

	/**
	 * Establece el cuerpo escala manager.
	 *
	 * @param cuerpoEscalaManager el nuevo cuerpo escala manager
	 */
	public void setCuerpoEscalaManager(CuerpoEscalaManager cuerpoEscalaManager) {
		this.cuerpoEscalaManager = cuerpoEscalaManager;
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
	 * Obtiene el centro gestor manager.
	 *
	 * @return el centro gestor manager
	 */
	public CentroGestorManager getCentroGestorManager() {
		return centroGestorManager;
	}

	/**
	 * Obtiene el forma acceso manager.
	 *
	 * @return el forma acceso manager
	 */
	public FormaAccesoManager getFormaAccesoManager() {
		return formaAccesoManager;
	}

	/**
	 * Establece el forma acceso manager.
	 *
	 * @param formaAccesoManager el nuevo forma acceso manager
	 */
	public void setFormaAccesoManager(FormaAccesoManager formaAccesoManager) {
		this.formaAccesoManager = formaAccesoManager;
	}
	
	/**
	 * Obtiene el provincia examen manager.
	 *
	 * @return el provincia examen manager
	 */
	public ProvinciaExamenManager getProvinciaExamenManager() {
		return provinciaExamenManager;
	}

	/**
	 * Establece el provincia examen manager.
	 *
	 * @param provinciaExamenManager el nuevo provincia examen manager
	 */
	public void setProvinciaExamenManager(
			ProvinciaExamenManager provinciaExamenManager) {
		this.provinciaExamenManager = provinciaExamenManager;
	}

	/**
	 * Obtiene el motivo reduccion tasa manager.
	 *
	 * @return el motivo reduccion tasa manager
	 */
	public MotivoReduccionTasaManager getMotivoReduccionTasaManager() {
		return motivoReduccionTasaManager;
	}

	/**
	 * Establece el motivo reduccion tasa manager.
	 *
	 * @param motivoReduccionTasaManager el nuevo motivo reduccion tasa manager
	 */
	public void setMotivoReduccionTasaManager(
			MotivoReduccionTasaManager motivoReduccionTasaManager) {
		this.motivoReduccionTasaManager = motivoReduccionTasaManager;
	}

	/**
	 * Obtiene el especialidad manager.
	 *
	 * @return el especialidad manager
	 */
	public EspecialidadManager getEspecialidadManager() {
		return especialidadManager;
	}

	/**
	 * Establece el especialidad manager.
	 *
	 * @param especialidadManager el nuevo especialidad manager
	 */
	public void setEspecialidadManager(EspecialidadManager especialidadManager) {
		this.especialidadManager = especialidadManager;
	}	
	
	/**
	 * Obtiene el log convocatoria manager.
	 *
	 * @return el log convocatoria manager
	 */
	public LogConvocatoriaManager getLogConvocatoriaManager() {
		return logConvocatoriaManager;
	}

	/**
	 * Establece el log convocatoria manager.
	 *
	 * @param logConvocatoriaManager el nuevo log convocatoria manager
	 */
	public void setLogConvocatoriaManager(LogConvocatoriaManager logConvocatoriaManager) {
		this.logConvocatoriaManager = logConvocatoriaManager;
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
	 * Obtiene el logger.
	 *
	 * @return el logger
	 */
	public static Logger getLogger() {
		return logger;
	}

	/**
	 * Obtiene el ministerios manager.
	 *
	 * @return el ministerios manager
	 */
	public MinisterioManager getMinisteriosManager() {
		return ministeriosManager;
	}

	/**
	 * Establece el ministerios manager.
	 *
	 * @param ministeriosManager el nuevo ministerios manager
	 */
	public void setMinisteriosManager(MinisterioManager ministeriosManager) {
		this.ministeriosManager = ministeriosManager;
	}

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
	 * Obtiene el discapacidad manager.
	 *
	 * @return el discapacidad manager
	 */
	public DiscapacidadManager getDiscapacidadManager() {
		return discapacidadManager;
	}

	/**
	 * Establece el discapacidad manager.
	 *
	 * @param discapacidadManager el nuevo discapacidad manager
	 */
	public void setDiscapacidadManager(DiscapacidadManager discapacidadManager) {
		this.discapacidadManager = discapacidadManager;
	}

	/**
	 * Obtiene el campos propios manager.
	 *
	 * @return el campos propios manager
	 */
	public CamposPropiosManager getCamposPropiosManager() {
		return camposPropiosManager;
	}

	/**
	 * Establece el campos propios manager.
	 *
	 * @param camposPropiosManager el nuevo campos propios manager
	 */
	public void setCamposPropiosManager(CamposPropiosManager camposPropiosManager) {
		this.camposPropiosManager = camposPropiosManager;
	}

	/**
	 * Obtiene el datos propios configuracion manager.
	 *
	 * @return el datos propios configuracion manager
	 */
	public DatosPropiosConfiguracionManager getDatosPropiosConfiguracionManager() {
		return datosPropiosConfiguracionManager;
	}

	/**
	 * Establece el datos propios configuracion manager.
	 *
	 * @param datosPropiosConfiguracionManager el nuevo datos propios configuracion manager
	 */
	public void setDatosPropiosConfiguracionManager(DatosPropiosConfiguracionManager datosPropiosConfiguracionManager) {
		this.datosPropiosConfiguracionManager = datosPropiosConfiguracionManager;
	}

	/**
	 * Obtiene el plantilla propios manager.
	 *
	 * @return el plantilla propios manager
	 */
	public PlantillaPropiosManager getPlantillaPropiosManager() {
		return plantillaPropiosManager;
	}

	/**
	 * Establece el plantilla propios manager.
	 *
	 * @param plantillaPropiosManager el nuevo plantilla propios manager
	 */
	public void setPlantillaPropiosManager(PlantillaPropiosManager plantillaPropiosManager) {
		this.plantillaPropiosManager = plantillaPropiosManager;
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
