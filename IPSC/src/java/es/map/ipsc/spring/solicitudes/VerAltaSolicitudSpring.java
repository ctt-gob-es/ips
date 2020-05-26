package es.map.ipsc.spring.solicitudes;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.ConvocatoriaQuery;
import es.map.ips.model.ParametrosConfiguracionQuery;
import es.map.ips.model.PlantillaQuery;
import es.map.ips.model.SolicitudComunQuery;
import es.map.ipsc.Constantes;
import es.map.ipsc.bean.AltaSolicitudBean;
import es.map.ipsc.bean.CiudadanoBean;
import es.map.ipsc.bean.ConvocatoriaBean;
import es.map.ipsc.bean.PaisBean;
import es.map.ipsc.bean.ParametrosConfiguracionBean;
import es.map.ipsc.bean.PlantillaBean;
import es.map.ipsc.bean.ProvinciaBean;
import es.map.ipsc.bean.SolicitudBean;
import es.map.ipsc.bean.TipoDiscapacidadBean;
import es.map.ipsc.form.SolicitudForm;
import es.map.ipsc.manager.convocatorias.ConvocatoriasManager;
import es.map.ipsc.manager.plantilla.PlantillaManager;
import es.map.ipsc.manager.solicitudes.SolicitudManager;
import es.map.ipsc.manager.tablaBase.ParametroConfiguracionManager;
import es.map.ipsc.manager.tablaBase.TablaBaseManager;
import es.map.ipsc.spring.AbstractSecureSpring;

/**
 * El Class VerAltaSolicitudSpring.
 */
public class VerAltaSolicitudSpring extends AbstractSecureSpring {

	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(VerAltaSolicitudSpring.class);

	/** La constante BUNDLE_ERROR. */
	private static final String BUNDLE_ERROR = "MessageResources";

	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_ERROR);
	
	/** La constante STRING_USUARIO. */
	private static final String STRING_USUARIO = "usuario";
	
	/** La constante STRING_SOLICITUD_ERROR_USUARIO. */
	private static final String STRING_SOLICITUD_ERROR_USUARIO = "solicitud.error.usuario";
	
	/** La constante STRING_ERRORDESCRIPCION. */
	private static final String STRING_ERRORDESCRIPCION = "errorDescripcion";
	
	/** La constante STRING_ERRORUSUARIO. */
	private static final String STRING_ERRORUSUARIO = "errorUsuario";
	
	/** La constante STRING_ERROR. */
	private static final String STRING_ERROR = "error";
	

	/** el solicitudes manager. */
	private SolicitudManager solicitudesManager;
	
	/** el tablas base manager. */
	private TablaBaseManager tablasBaseManager;
	
	/** el convocatoria manager. */
	private ConvocatoriasManager convocatoriaManager;
	
	/** el plantilla manager. */
	private PlantillaManager plantillaManager;
	
	/** el parametro configuracion manager. */
	private ParametroConfiguracionManager parametroConfiguracionManager;
	
	/** el properties. */
	private static Properties properties;

	/**
	 * Crea una nueva ver alta solicitud spring.
	 */
	public VerAltaSolicitudSpring() {
		try{
			setSolicitudesManager((SolicitudManager) getBean("solicitudesManager"));
			setTablasBaseManager((TablaBaseManager) getBean("tablasBaseManager"));
			setConvocatoriaManager((ConvocatoriasManager) getBean("convocatoriaManager"));
			setPlantillaManager((PlantillaManager) getBean("plantillaManager"));
			setParametroConfiguracionManager((ParametroConfiguracionManager) getBean("parametroConfiguracionManager"));
			properties = (Properties) getBean("propertiesBean");

		}catch(Exception e){
			logger.warn(e);
			logger.error("Error ver alta solicitud ",e);
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ipsc.spring.AbstractSecureSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		logger.info("doExecute SolicitudSpring");

		String resultado = null;
		SolicitudForm solicitudForm = (SolicitudForm) form;
		String action = solicitudForm.getAction();
		AltaSolicitudBean altaSolicitudBean = new AltaSolicitudBean();

		String convocatoriaRepetida_Unico = properties.getProperty("altaSolicitud.inscripcionMultiple_Unica");


		String siglasCentroGestorJusticia = "";

		//*********** METO EL PARAMETRO QUE ME INDICA EN QUE MENU ESTOY **********
		String menu_solicitudes = RESOURCE_BUNDLE.getString("field.menu.listaSolic");
		this.getRequest().getSession().setAttribute("pagActiva", menu_solicitudes);
		//****************************************************************** 

		//Cargar el porcentaje de discapacidad minimo actual
		ParametrosConfiguracionQuery paramConfQuery = new ParametrosConfiguracionQuery();
		paramConfQuery.setNombreCampo(Constantes.PARAMETROS_PORCENTAJE_DISCAPACIDAD);
		ParametrosConfiguracionBean parametrosConfiguracion = parametroConfiguracionManager.buscarParametroConfiguracionUnico(paramConfQuery);
		solicitudForm.setPorcentajeMinDiscapacidad(parametrosConfiguracion.getValorCampo());
		logger.info("Porcentaje Discapacidad: "+ parametrosConfiguracion.getValorCampo());

		if(action == null){
			resultado =  "show";			
			String s_idConvocatoria = (String)this.getRequest().getParameter("id");

			if(!StringUtils.isEmpty(s_idConvocatoria)){
				long idConvocatoria = Long.valueOf(s_idConvocatoria);

				CiudadanoBean ciudadanoBean = (CiudadanoBean) this.getRequest().getSession().getAttribute(STRING_USUARIO);

				//Comprobar si el usuario esta en la sesion
				CiudadanoBean usuActual = (CiudadanoBean) this.getRequest().getSession().getAttribute(STRING_USUARIO);

				if(usuActual == null){
					this.getRequest().setAttribute(STRING_ERRORDESCRIPCION,RESOURCE_BUNDLE.getString(STRING_SOLICITUD_ERROR_USUARIO));
					return STRING_ERRORUSUARIO;
				}else{
					if(usuActual.getNif() == null){
						this.getRequest().setAttribute(STRING_ERRORDESCRIPCION,RESOURCE_BUNDLE.getString(STRING_SOLICITUD_ERROR_USUARIO));
						return STRING_ERRORUSUARIO;
					}
				}

				CiudadanoBean ciudadanoAux = new CiudadanoBean();				
				ciudadanoAux.setNif(ciudadanoBean.getNif());

				//Comprobar si el ciudadano ya se ha inscrito en esa convocatoria
				ConvocatoriaQuery convocatoriaQuery = new ConvocatoriaQuery();
				convocatoriaQuery.setId(idConvocatoria);			
				SolicitudComunQuery solicitudComunQuery = new SolicitudComunQuery();
				solicitudComunQuery.setConvocatoria(convocatoriaQuery);
				solicitudComunQuery.setNif(ciudadanoAux.getNif());
				solicitudComunQuery.addEstadoSolicitudIn(Constantes.SOLICITUD_ID_NOPAGADO);
				solicitudComunQuery.addEstadoSolicitudIn(Constantes.SOLICITUD_ID_NOREGISTRADO);
				solicitudComunQuery.addEstadoSolicitudIn(Constantes.SOLICITUD_ID_REGISTRADO);
				solicitudComunQuery.addEstadoSolicitudIn(Constantes.SOLICITUD_ID_PENDIENTE_REGISTRO);
				solicitudComunQuery.addEstadoSolicitudIn(Constantes.SOLICITUD_ID_PROCESO_REGISTRO);
				solicitudComunQuery.addEstadoSolicitudIn(Constantes.SOLICITUD_ID_PROCESO_PAGO);
				//Se busca en id Solicitud en la base de datos

				ConvocatoriaBean convocatoriaBean = convocatoriaManager.recuperarConvocatoria(idConvocatoria);
				String siglasCentroGestor = convocatoriaBean.getSiglasCentroGestor();
				solicitudForm.setSiglasCentroGestor(siglasCentroGestor);
				solicitudForm.setSiglasCentroGestorJusticia(siglasCentroGestorJusticia);

				this.getRequest().getSession().setAttribute("sCG", siglasCentroGestor); 
				this.getRequest().getSession().setAttribute("sCGJ", siglasCentroGestorJusticia); 

				if(convocatoriaRepetida_Unico.equals("U") && "true".equals(properties.getProperty("conf.validacionInscripcion"))){

					
						SolicitudBean solicitudRegistroBean = solicitudesManager.buscarRegistroSolicitud(solicitudComunQuery);
						//Si se encuentra la solicitud redirecciona al detalle
						if(solicitudRegistroBean!=null){
							this.setRequestAttribute("registro", solicitudRegistroBean.getId().toString());
							logger.info(solicitudRegistroBean.getId());
							if("1".equals(solicitudRegistroBean.getIdEstadoSolicitud())){
								logger.info("Redireccionar pagarSolicitud");
								this.setRequestAttribute("id", solicitudRegistroBean.getId().toString());				
								return "pagarSolicitud";
							}else{
								if("2".equals(solicitudRegistroBean.getIdEstadoSolicitud())){
									this.setRequestAttribute("id", solicitudRegistroBean.getId().toString());								
									logger.info("Redireccionar registrarSolicitud");
									return "registrarSolicitud";
								}else{
									if("3".equals(solicitudRegistroBean.getIdEstadoSolicitud()) 
											|| "5".equals(solicitudRegistroBean.getIdEstadoSolicitud())){									
										logger.info("Redireccionar detalleRegistro");
										return "detalleRegistrado";
									}
								}
							}
							return "detalle";
						}
											
				}	

				//Si no se ha registrado aun
				if(convocatoriaBean == null){
					resultado = STRING_ERROR;
					this.getRequest().setAttribute(STRING_ERRORDESCRIPCION,RESOURCE_BUNDLE.getString("solicitud.error.alta.convocatoriaNull"));
					return resultado;
				}else if(!convocatoriaBean.getIdEstado().equals(Constantes.CONVOCATORIA_PUBLICADA)){
					resultado = STRING_ERROR;
					this.getRequest().setAttribute(STRING_ERRORDESCRIPCION,RESOURCE_BUNDLE.getString("solicitud.error.convocatoriaNoPublicada"));
					return resultado;
				}
				SolicitudBean solicitudBean = solicitudesManager.buscarUltimaSolicitud2(ciudadanoBean);
				PlantillaQuery plantillaQuery = new PlantillaQuery();
				plantillaQuery.setId(convocatoriaBean.getIdPlantilla());
				PlantillaBean plantillaBean = plantillaManager.buscarPlantillaById(plantillaQuery);
				
				if(plantillaBean != null){
					this.setRequestAttribute("plantilla", plantillaBean);
				}

				List<ProvinciaBean> provincias = tablasBaseManager.obtenerProvinciasActivas();
				List<PaisBean> paises	= tablasBaseManager.buscarPaises();
				List<TipoDiscapacidadBean> tiposDiscapacidad = tablasBaseManager.buscarTiposDiscapacidad();

				if(solicitudBean!=null){
					int idPaisDomicilio=0;
					

					if(solicitudBean.getPaisDomicilio() != null){
						try{
							idPaisDomicilio= solicitudBean.getPaisDomicilio().getId();
						}catch(Exception e){
							logger.error("Error pais domicilio "+idPaisDomicilio,e);
						}
					}
					int idProvinciaDomicilio=0;
					if(solicitudBean.getProvinciaDomicilio() != null){
						try{
							idProvinciaDomicilio= solicitudBean.getProvinciaDomicilio().getId();
						}catch(Exception e){
							logger.error("Error provincia domicilio "+idProvinciaDomicilio,e);
						}
					}
					solicitudForm.setPais(String.valueOf(idPaisDomicilio));
					solicitudForm.setProvinciaDomicilio(String.valueOf(idProvinciaDomicilio));
				}else{
					solicitudBean = new SolicitudBean();
				}

				//Obtienes el numJustificante
				String nSolicitud = "";
				long nSolicitudL = 0 ;

				try{
					boolean is007 = false;
					if(convocatoriaBean.getSiglasCentroGestor().equals("")){
						is007 = true;
					}
					
					nSolicitud = solicitudesManager.obtenerNumeroSolicitud(is007, convocatoriaBean.getNumModelo());
					// Comprobamos si ya se ha generado un numero de justificante igual
					while (solicitudesManager.existeNumeroSolicitud(nSolicitud)) {
						nSolicitud = solicitudesManager.obtenerNumeroSolicitud(is007, convocatoriaBean.getNumModelo());
					}
					
					nSolicitudL = Long.parseLong(nSolicitud);

					if(nSolicitudL== 0){
						throw new Exception();
					}
				}catch(Exception e){
					logger.error("Error parsear num solicitud "+nSolicitudL,e);
					this.getRequest().setAttribute(STRING_ERRORDESCRIPCION, RESOURCE_BUNDLE.getString("field.numeroSolicitud.error"));	
					resultado = STRING_ERROR;						
				}

				String nSolicitudNuevo = String.valueOf(nSolicitudL);
				solicitudBean.setNumeroSolicitud(nSolicitudNuevo);
				altaSolicitudBean = toAltaSolicitudBean(convocatoriaBean,ciudadanoBean,solicitudBean);

				this.setRequestAttribute("siglasCentroGestor", siglasCentroGestor);
				this.setRequestAttribute("siglasCentroGestorJusticia", siglasCentroGestorJusticia);
				this.setRequestAttribute("provincias", provincias);
				this.setRequestAttribute("provinciasExamen", convocatoriaBean.getProvinciasExamen());
				this.setRequestAttribute("paises", paises);
				this.setRequestAttribute("altaSolicitud", altaSolicitudBean);
				this.setRequestAttribute("tiposDiscapacidad",tiposDiscapacidad);

			}else{
				resultado = STRING_ERROR;
				this.getRequest().setAttribute(STRING_ERRORDESCRIPCION,RESOURCE_BUNDLE.getString("solicitud.error.alta.convocatoriaNull"));
			}

		}else{
			//Carga los datos de la solicitud
			resultado =  "show";
			Long idConvocatoria = Long.parseLong(solicitudForm.getIdConvocatoria());
			ConvocatoriaBean convocatoriaBean = convocatoriaManager.recuperarConvocatoria(idConvocatoria);

			String siglasCentroGestor = convocatoriaBean.getSiglasCentroGestor();
			CiudadanoBean ciudadanoBean = (CiudadanoBean) this.getRequest().getSession().getAttribute(STRING_USUARIO);

			if(ciudadanoBean == null || ciudadanoBean.getNif() == null){
				this.getRequest().setAttribute(STRING_ERRORDESCRIPCION,RESOURCE_BUNDLE.getString(STRING_SOLICITUD_ERROR_USUARIO));
				return STRING_ERRORUSUARIO;
			}

			List<ProvinciaBean> provincias = tablasBaseManager.obtenerProvinciasActivas();
			List<PaisBean> paises	= tablasBaseManager.buscarPaises();
			List<TipoDiscapacidadBean> tiposDiscapacidad = tablasBaseManager.buscarTiposDiscapacidad();
			
			logger.info("Recuperando Convocatoria con id: "+idConvocatoria);
			convocatoriaBean = convocatoriaManager.recuperarConvocatoria(idConvocatoria);

			logger.info("Recuperando Ultima Solicitud del Ciudadano con NIF: "+ciudadanoBean.getNif());
			SolicitudBean solicitudBean = solicitudesManager.buscarUltimaSolicitud2(ciudadanoBean);

			if(solicitudBean==null){
				solicitudBean = new SolicitudBean();
			}

			logger.info("Recuperando Plantilla de la convocatoria. Id de Plantilla "+convocatoriaBean.getIdPlantilla());
			PlantillaQuery plantillaQuery = new PlantillaQuery();
			plantillaQuery.setId(convocatoriaBean.getIdPlantilla());
			PlantillaBean plantillaBean = plantillaManager.buscarPlantillaById(plantillaQuery);
			
			if(plantillaBean != null){
				this.setRequestAttribute("plantilla", plantillaBean);
			}

			String nSolicitud = "";
			long nSolicitudL = 0 ;
			boolean is007= false;
			
			if(convocatoriaBean.getSiglasCentroGestor().equals("")){
				is007=true;
			}
			nSolicitud = solicitudesManager.obtenerNumeroSolicitud(is007);
			// Comprobamos si ya se ha generado un numero de justificante igual
			while (solicitudesManager.existeNumeroSolicitud(nSolicitud)) {
				nSolicitud = solicitudesManager.obtenerNumeroSolicitud(is007);
			}
			nSolicitudL = Long.parseLong(nSolicitud);

			if(nSolicitudL== 0){
				throw new Exception();
			}
			
			logger.info("Numero de justificante obtenido: "+nSolicitudL);

			String nSolicitudNuevo = String.valueOf(nSolicitudL);
			solicitudBean.setNumeroSolicitud(nSolicitudNuevo);
			altaSolicitudBean = toAltaSolicitudBeanForm(solicitudForm,convocatoriaBean,solicitudBean,ciudadanoBean);

			if(convocatoriaBean.getEspecialidads() != null){
				altaSolicitudBean.setEspecialidades(convocatoriaBean.getEspecialidads());
			}
			
			if(solicitudForm.getImporteOriginal() != null && !"".equals(solicitudForm.getImporteOriginal())){
				altaSolicitudBean.setImporteOriginal(solicitudForm.getImporteOriginal());
			}

			solicitudForm.setSiglasCentroGestor(siglasCentroGestor);
			solicitudForm.setSiglasCentroGestorJusticia(siglasCentroGestorJusticia);

			this.setRequestAttribute("siglasCentroGestor", siglasCentroGestor);
			this.setRequestAttribute("siglasCentroGestorJusticia", siglasCentroGestorJusticia);
			this.setRequestAttribute("provincias", provincias);
			this.setRequestAttribute("provinciasExamen", convocatoriaBean.getProvinciasExamen());
			this.setRequestAttribute("paises", paises);
			this.setRequestAttribute("tiposDiscapacidad",tiposDiscapacidad);
			this.setRequestAttribute("altaSolicitud", altaSolicitudBean);
		}

		logger.info("Resultado: "+resultado);
		logger.info("Fin VerAlta");
		return resultado;
	}

	/**
	 * To alta solicitud bean form.
	 *
	 * @param solicitudForm el solicitud form
	 * @param convocatoriaBean el convocatoria bean
	 * @param solicitudBean el solicitud bean
	 * @param ciudadanoBean el ciudadano bean
	 * @return el alta solicitud bean
	 */
	private AltaSolicitudBean toAltaSolicitudBeanForm(
			SolicitudForm solicitudForm, ConvocatoriaBean convocatoriaBean, SolicitudBean solicitudBean, CiudadanoBean ciudadanoBean) {
		
		AltaSolicitudBean aux = new AltaSolicitudBean();

		aux.setNumSolicitud(solicitudForm.getNumSolicitud());
		aux.setIdConvocatoria(String.valueOf(solicitudForm.getId()));
		//Convocatoria
		aux.setDesCentroGestor(convocatoriaBean.getDesCentroGestor());
		aux.setEjercicio(convocatoriaBean.getEjercicio());
		//Personales
		aux.setNif(ciudadanoBean.getNif());
		aux.setNombre(ciudadanoBean.getNombre());
		aux.setPrimerApellido(ciudadanoBean.getPrimerApellido());
		aux.setSegundoApellido(ciudadanoBean.getSegundoApellido());
		aux.setEmail(solicitudForm.getEmail());
		//Direccion
		aux.setCalleDomicilio(solicitudForm.getCalleDomicilio());
		aux.setCodigoPostalDomicilio(solicitudForm.getCodigoPostalDomicilio());
		aux.setMunicipioDomicilio(solicitudForm.getMunicipioDomicilio());
		aux.setTelefono(solicitudForm.getTelefono());
		aux.setTelefonoAux(solicitudForm.getTelefonoAux());
		//Convocatoria
		aux.setDesCuerpoEscala(convocatoriaBean.getDesCuerpoEscala());
		if(convocatoriaBean.getEspecialidads() != null && convocatoriaBean.getEspecialidads().size()>0){
			aux.setEspecialidades(convocatoriaBean.getEspecialidads());
		}
		aux.setDesFormaAcceso(convocatoriaBean.getDesFormaAcceso());
		aux.setIdFormaAcceso(convocatoriaBean.getIdFormaAcceso());
		aux.setDesMinisterio(convocatoriaBean.getDesMinisterioConvocante());

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		aux.setFechaBOE(sdf.format(convocatoriaBean.getFechaBoe()));

		if(convocatoriaBean.getProvinciasExamen() != null && convocatoriaBean.getProvinciasExamen().size()>0){
			aux.setProvinciasExamen(convocatoriaBean.getProvinciasExamen());
		}
		aux.setImporte(String.valueOf(convocatoriaBean.getImporte()));
		//Titulos
		if(convocatoriaBean.getTituloOficials() != null && convocatoriaBean.getTituloOficials().size()>0){
			aux.setTitulosOficiales(convocatoriaBean.getTituloOficials());
		}
		//Bases
		return aux;
	}

	/**
	 * To alta solicitud bean.
	 *
	 * @param convocatoria el convocatoria
	 * @param ciudadano el ciudadano
	 * @param solicitud el solicitud
	 * @return el alta solicitud bean
	 */
	private AltaSolicitudBean toAltaSolicitudBean(
			ConvocatoriaBean convocatoria, CiudadanoBean ciudadano,
			SolicitudBean solicitud) {
		SimpleDateFormat formatoFecha= new SimpleDateFormat("dd/MM/yyyy");
		AltaSolicitudBean aux = new AltaSolicitudBean();

		aux.setIdConvocatoria(String.valueOf(convocatoria.getId()));
		//Convocatoria
		aux.setDesCentroGestor(convocatoria.getDesCentroGestor().toUpperCase());
		if(solicitud.getNumeroSolicitud()!=null){
			aux.setNumSolicitud(solicitud.getNumeroSolicitud());
		}
		aux.setEjercicio(convocatoria.getEjercicio());

		//Personales
		aux.setNif(ciudadano.getNif().toUpperCase());
		aux.setNombre(ciudadano.getNombre().toUpperCase());
		aux.setPrimerApellido(ciudadano.getPrimerApellido().toUpperCase());
		aux.setSegundoApellido(ciudadano.getSegundoApellido().toUpperCase());

		if(solicitud!=null){
			if(solicitud.getEmail()!=null){
				aux.setEmail(solicitud.getEmail());
			}

			if(solicitud.getCalleDomicilio()!=null){
				aux.setCalleDomicilio(solicitud.getCalleDomicilio().toUpperCase());
			}

			if(solicitud.getCodigoPostalDomicilio()!=null){
				aux.setCodigoPostalDomicilio(solicitud.getCodigoPostalDomicilio().toUpperCase());
			}

			if(solicitud.getMunicipioDomicilio()!=null){
				aux.setMunicipioDomicilio(solicitud.getMunicipioDomicilio().toUpperCase());
			}

			boolean separador = false;
			String telefono1 = "";
			String telefono2 = "";
			int delimitador = 0;
			if(solicitud.getTelefono() != null){
				for(int i=0;i<solicitud.getTelefono().length();i++){
					delimitador = 0;
					if((solicitud.getTelefono().charAt(i)) == '/'){
						separador = true;
						delimitador=1;
					}
					if(separador == false){
						telefono1 = telefono1 + solicitud.getTelefono().charAt(i);
					}else{
						if(separador == true && delimitador == 0){
							telefono2 = telefono2 + solicitud.getTelefono().charAt(i);
						}
					}
				}
			}

			aux.setTelefono(telefono1);
			aux.setTelefonoAux(telefono2);
		}

		//Convocatoria
		aux.setDesCuerpoEscala(convocatoria.getDesCuerpoEscala().toUpperCase());
		if(convocatoria.getEspecialidads() != null && convocatoria.getEspecialidads().size()>0){
			aux.setEspecialidades(convocatoria.getEspecialidads());
		}
		aux.setDesFormaAcceso(convocatoria.getDesFormaAcceso().toUpperCase());
		aux.setIdFormaAcceso(convocatoria.getIdFormaAcceso());
		aux.setDesMinisterio(convocatoria.getDesMinisterioConvocante().toUpperCase());
		if(convocatoria.getFechaBoe() != null){
			String fechaAuxBoe = formatoFecha.format(convocatoria.getFechaBoe());
			aux.setFechaBOE(fechaAuxBoe);
		}
		if(convocatoria.getProvinciasExamen() != null && convocatoria.getProvinciasExamen().size()>0){
			aux.setProvinciasExamen(convocatoria.getProvinciasExamen());
		}
		aux.setImporte(String.valueOf(convocatoria.getImporte()));
		aux.setImporteOriginal(String.valueOf(convocatoria.getImporte()));
		//Titulos
		if(convocatoria.getTituloOficials() != null && convocatoria.getTituloOficials().size()>0){			
			aux.setTitulosOficiales(convocatoria.getTituloOficials());
		}
		//Bases
		return aux;
	}

	/**
	 * Obtiene el solicitudes manager.
	 *
	 * @return el solicitudes manager
	 */
	public SolicitudManager getSolicitudesManager() {
		return solicitudesManager;
	}

	/**
	 * Establece el solicitudes manager.
	 *
	 * @param solicitudesManager el nuevo solicitudes manager
	 */
	public void setSolicitudesManager(SolicitudManager solicitudesManager) {
		this.solicitudesManager = solicitudesManager;
	}

	/**
	 * Obtiene el tablas base manager.
	 *
	 * @return el tablas base manager
	 */
	public TablaBaseManager getTablasBaseManager() {
		return tablasBaseManager;
	}

	/**
	 * Establece el tablas base manager.
	 *
	 * @param tablasBaseManager el nuevo tablas base manager
	 */
	public void setTablasBaseManager(TablaBaseManager tablasBaseManager) {
		this.tablasBaseManager = tablasBaseManager;
	}

	/**
	 * Obtiene el convocatoria manager.
	 *
	 * @return el convocatoria manager
	 */
	public ConvocatoriasManager getConvocatoriaManager() {
		return convocatoriaManager;
	}

	/**
	 * Establece el convocatoria manager.
	 *
	 * @param convocatoriaManager el nuevo convocatoria manager
	 */
	public void setConvocatoriaManager(ConvocatoriasManager convocatoriaManager) {
		this.convocatoriaManager = convocatoriaManager;
	}

	/**
	 * Obtiene el plantilla manager.
	 *
	 * @return el plantilla manager
	 */
	public PlantillaManager getPlantillaManager() {
		return plantillaManager;
	}

	/**
	 * Establece el plantilla manager.
	 *
	 * @param plantillaManager el nuevo plantilla manager
	 */
	public void setPlantillaManager(PlantillaManager plantillaManager) {
		this.plantillaManager = plantillaManager;
	}

	/**
	 * Obtiene el parametro configuracion manager.
	 *
	 * @return el parametro configuracion manager
	 */
	public ParametroConfiguracionManager getParametroConfiguracionManager() {
		return parametroConfiguracionManager;
	}

	/**
	 * Establece el parametro configuracion manager.
	 *
	 * @param parametroConfiguracionManager el nuevo parametro configuracion manager
	 */
	public void setParametroConfiguracionManager(
			ParametroConfiguracionManager parametroConfiguracionManager) {
		this.parametroConfiguracionManager = parametroConfiguracionManager;
	}
}
