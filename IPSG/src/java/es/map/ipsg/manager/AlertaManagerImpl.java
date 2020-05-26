package es.map.ipsg.manager;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Set;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.apache.log4j.Logger;

import es.map.ips.common.exception.ApplicationException;
import es.map.ips.common.model.OrderType;
import es.map.ips.common.model.SearchResult;
import es.map.ips.common.spring.ApplicationContextProvider;
import es.map.ips.common.spring.SpringMessage;
import es.map.ips.common.spring.SpringMessages;
import es.map.ips.common.util.DateUtil;
import es.map.ips.dao.AlertaDAO;
import es.map.ips.model.Alerta;
import es.map.ips.model.AlertaQuery;
import es.map.ips.model.CentroGestor;
import es.map.ips.model.CentroGestorQuery;
import es.map.ips.model.ConvocatoriaQuery;
import es.map.ips.model.CuerpoEscalaQuery;
import es.map.ips.model.EstadoConvocatoriaQuery;
import es.map.ips.model.EstadoSolicitudQuery;
import es.map.ips.model.LogConvocatoriaQuery;
import es.map.ips.model.ModoAlerta;
import es.map.ips.model.ModoAlertaQuery;
import es.map.ips.model.ParametrosConfiguracionQuery;
import es.map.ips.model.Perfil;
import es.map.ips.model.PerfilQuery;
import es.map.ips.model.SolicitudComunQuery;
import es.map.ips.model.TipoAlerta;
import es.map.ips.model.Usuario;
import es.map.ipsg.bean.AlertaBean;
import es.map.ipsg.bean.CentroGestorBean;
import es.map.ipsg.bean.ConvocatoriasBean;
import es.map.ipsg.bean.CorreoElectronicoBean;
import es.map.ipsg.bean.LogConvocatoriaBean;
import es.map.ipsg.bean.ParametrosConfiguracionBean;
import es.map.ipsg.bean.SolicitudBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.util.Constantes;
import es.map.ipsg.util.EnvioMails;


/**
 *  Clase que implementa el TituloOficialManager.
 *
 * @author amartinl
 */
public class AlertaManagerImpl implements AlertaManager {

	/** el alerta DAO. */
	//Variables
	private AlertaDAO alertaDAO;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(AlertaManagerImpl.class);
	
	/** el log convocatoria manager. */
	private LogConvocatoriaManager logConvocatoriaManager;
	
	/** el convocatorias manager. */
	private ConvocatoriasManager convocatoriasManager;
	
	/** el solicitudes registradas manager. */
	private SolicitudesRegistradasManager solicitudesRegistradasManager;
	
	/** el correo electronico manager. */
	private CorreoElectronicoManager correoElectronicoManager;
	
	/** el parametro configuracion manager. */
	private ParametroConfiguracionManager parametroConfiguracionManager;

	/** el usuario centro gestor manager. */
	private UsuarioCentrogestorManager usuarioCentrogestorManager;
	
	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** La constante STRING_ERRORCONVOCATORIA. */
	private static final String STRING_ERRORCONVOCATORIA = "errorConvocatoria";
	
	/** el properties. */
	private Properties properties;
	
	/**
	 * Buscar alertas all.
	 *
	 * @param alertaQuery el alerta query
	 * @return el list
	 */
	public List<AlertaBean> buscarAlertasAll(AlertaQuery alertaQuery) {
		alertaQuery.setEstado(Constantes.MINISTERIO_ESTADO_ACTIVO);
		List<AlertaBean> list = new ArrayList<AlertaBean>();
		SearchResult<Alerta> alertas = alertaDAO.search(alertaQuery);
		int numTotal = 0;

		if(alertas != null){
			numTotal = alertas.getNumResults();
		}

		for(Alerta u:alertas.getResults()){
			AlertaBean alertaBean = new AlertaBean();
			try{
				alertaBean = toAlertaBean(u,numTotal);
			}catch(Exception e){
				logger.error("Error AlertaManagerImpl -  buscarAlertasAll",e);
			}
			list.add(alertaBean);
		}
		
		return list;
	}

	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.AlertaManager#buscarAlertaCombo(es.map.ips.model.AlertaQuery)
	 */
	public ArrayList<AlertaBean> buscarAlertaCombo(AlertaQuery alertaQuery){
		
		SearchResult<Alerta> alerta = buscarAlertas(alertaQuery);
		
		ArrayList<AlertaBean> arrAlerta= new ArrayList<AlertaBean>();
		for(int i=0;i<alerta.getResults().size();i++){
			AlertaBean aux;
			aux = toAlertaComboBean(alerta.getResults().get(i));
			if(aux != null){
				arrAlerta.add(aux);
			}
		}	
		return arrAlerta;		
	}
	

	/**
	 * Buscar alertas.
	 *
	 * @param alertaQuery el alerta query
	 * @return el search result
	 */
	private SearchResult<Alerta> buscarAlertas (AlertaQuery alertaQuery) {
		ApplicationException.assertNotNull(alertaQuery, "alertaQuery no puede ser null");
	
		return alertaDAO.search(alertaQuery);
	}

	/**
	 * Buscar alerta all.
	 *
	 * @param alertaQuery  AlertaQuery
	 * @return arrAlerta ArrayList(AlertaBean)
	 */
	public ArrayList<AlertaBean> buscarAlertaAll(AlertaQuery alertaQuery){	
		logger.info("Entra en buscarAlertaAll");
		SearchResult<Alerta> alerta = buscarAlertas(alertaQuery);
		int numTotal ;
		if(alerta.getNumResults()==null){
			numTotal=0;;
		}else{
			numTotal=alerta.getNumResults();
		}
		ArrayList<AlertaBean> arrAlerta = new ArrayList<AlertaBean>();
		for(int i=0;i<alerta.getResults().size();i++){
			AlertaBean aux;
			aux = toAlertaBean(alerta.getResults().get(i),numTotal);
			if(aux != null){
				arrAlerta.add(aux);
			}
		}	
		return arrAlerta;		
	}
	
	/**
	 * To alerta bean.
	 *
	 * @param alertaAux el alerta aux
	 * @param numTotal el num total
	 * @return el alerta bean
	 */
	private AlertaBean toAlertaBean(Alerta alertaAux, int numTotal) {
		AlertaBean aux = new AlertaBean();
		aux.setId(alertaAux.getId());
		if (alertaAux.getCentroGestor()!=null){
			if (alertaAux.getCentroGestor().getIdCentroGestor()!=null){
				aux.setIdCentroGestor(alertaAux.getCentroGestor().getIdCentroGestor());
				aux.setCodigoCentroGestor(alertaAux.getCentroGestor().getCodigo());
				aux.setDesCentroGestor(alertaAux.getCentroGestor().getDescripcion());
			}
		}else{
			aux.setCodigoCentroGestor(Constantes.ALERTA_CENTRO_GESTOR_TODOS);
		}
		aux.setIdModo(alertaAux.getModoAlerta().getId());
		aux.setIdPerfil(alertaAux.getPerfil().getId());
		aux.setIdPeticion(alertaAux.getIdPeticion());
		aux.setIdTipo(alertaAux.getTipoAlerta().getId());
		if (alertaAux.getTipoAlerta().getDescripcion()!=null){
			aux.setDesTipoAlerta(alertaAux.getTipoAlerta().getDescripcion());	
		}
		if (alertaAux.getPerfil().getDescripcion()!=null){
			aux.setDesTipoUsuario(alertaAux.getPerfil().getDescripcion());
		}

		aux.setNumTotal(numTotal);
		if (alertaAux.getModoAlerta().getDescripcion()!=null){
		aux.setDesModoAlerta(alertaAux.getModoAlerta().getDescripcion());
		}
		aux.setUsuarios(alertaAux.getUsuarios());
		
		if(alertaAux.getUsuarios() != null){
			Iterator<Usuario> itUsuarios = alertaAux.getUsuarios().iterator();
			InternetAddress [] bcc = new InternetAddress[alertaAux.getUsuarios().size()];
			int h = 0;
			while(itUsuarios.hasNext()){
				
				Usuario u = (Usuario) itUsuarios.next();
				try {
					bcc[h]= new InternetAddress(u.getEmail());
				} catch (AddressException e) {
					// TODO Auto-generated catch block
					logger.error("Error AlertaManagerImpl - InternetAddress",e);
				}
				h++;
			}
			aux.setBcc(bcc);
		}
		return aux;
	}
	
	/**
	 * To alerta combo bean.
	 *
	 * @param alertaAux el alerta aux
	 * @return el alerta bean
	 */
	private AlertaBean toAlertaComboBean(Alerta alertaAux) {
		AlertaBean aux = new AlertaBean();
		aux.setId(alertaAux.getId());
		if (alertaAux.getCentroGestor()!=null){
			if (alertaAux.getCentroGestor().getIdCentroGestor()!=null){
				aux.setIdCentroGestor(alertaAux.getCentroGestor().getIdCentroGestor());
				aux.setCodigoCentroGestor(alertaAux.getCentroGestor().getCodigo());
			}
		}else{
			aux.setCodigoCentroGestor(Constantes.ALERTA_CENTRO_GESTOR_TODOS);
		}
		aux.setIdModo(alertaAux.getModoAlerta().getId());
		aux.setIdPerfil(alertaAux.getPerfil().getId());
		aux.setIdTipo(alertaAux.getTipoAlerta().getId());
		return aux;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.AlertaManager#buscarAlerta(es.map.ips.model.AlertaQuery)
	 */
	public AlertaBean buscarAlerta(AlertaQuery alertaQuery) {
		alertaQuery.setEstado(Constantes.MINISTERIO_ESTADO_ACTIVO);
		Alerta alerta;
		alerta = alertaDAO.searchUnique(alertaQuery);
		AlertaBean alertaBean = new AlertaBean();
		try{
			alertaBean = toAlertaBean(alerta,0);
		}catch(Exception e){
			logger.error("Error AlertaManagerImpl - buscarAlerta",e);
		}
		return alertaBean;
	}

	/**
	 * Obtiene el alerta DAO.
	 *
	 * @return alertaDAO AlertaDAO
	 */
	public AlertaDAO getAlertaDAO() {
		return alertaDAO;
	}



	/**
	 * Establece el alerta DAO.
	 *
	 * @param alertaDAO AlertaDAO
	 */
	public void setAlertaDAO(AlertaDAO alertaDAO) {
		this.alertaDAO = alertaDAO;
	}

	/**
	 * To alerta.
	 *
	 * @param alertaBean el alerta bean
	 * @return el alerta
	 */
	private Alerta toAlerta(AlertaBean alertaBean) {
		Alerta alertaAux = new Alerta();
		Perfil perfilAux=new Perfil();
		perfilAux.setId(alertaBean.getIdPerfil());
		perfilAux.getAlertas().add(alertaAux);
		
		TipoAlerta tipoAlertaAux=new TipoAlerta();
		tipoAlertaAux.setId(alertaBean.getIdTipo());
		tipoAlertaAux.getAlertas().add(alertaAux);
		
		ModoAlerta modoAlertaAux=new ModoAlerta();
		modoAlertaAux.setId(alertaBean.getIdModo());
		modoAlertaAux.getAlertas().add(alertaAux);
		
		if (alertaBean.getIdCentroGestor()!=null){
		CentroGestor centroGestorAux= new CentroGestor();
		centroGestorAux.setIdCentroGestor(alertaBean.getIdCentroGestor());
		alertaAux.setCentroGestor(centroGestorAux);
		}
		alertaAux.setId(alertaBean.getId());
		alertaAux.setEstado(alertaBean.getEstado());
		alertaAux.setIdPeticion(alertaBean.getIdPeticion());
		alertaAux.setPerfil(perfilAux);
		alertaAux.setTipoAlerta(tipoAlertaAux);
		alertaAux.setModoAlerta(modoAlertaAux);
		alertaAux.setUsuarios(alertaBean.getUsuarios());
		return alertaAux;
	}

	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.AlertaManager#guardarAlerta(es.map.ipsg.bean.AlertaBean)
	 */
	public int guardarAlerta(AlertaBean alertaBean) {
		Alerta alerta;
		alerta = toAlerta(alertaBean);
		int idAlerta = alertaDAO.insert(alerta);
		
		return idAlerta;
	}

/* (non-Javadoc)
 * @see es.map.ipsg.manager.AlertaManager#actualizarAlerta(es.map.ipsg.bean.AlertaBean)
 */
public void actualizarAlerta(AlertaBean alertaBean) {
		Alerta alerta;
		alerta = toAlerta(alertaBean);
		alertaDAO.update(alerta);
	}

	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.AlertaManager#obtenerAlerta(java.lang.Integer)
	 */
	public AlertaBean obtenerAlerta(Integer idAlerta){
		Alerta alerta = alertaDAO.get(idAlerta);
		return toAlertaBean(alerta);
	}
	
	/**
	 * To alerta bean.
	 *
	 * @param alerta el alerta
	 * @return el alerta bean
	 */
	public AlertaBean toAlertaBean(Alerta alerta){
		AlertaBean alertaBean = new AlertaBean();
		
		alertaBean.setId(alerta.getId());
		alertaBean.setEstado(alerta.getEstado());
		alertaBean.setIdPeticion(alerta.getIdPeticion());
		if (alerta.getCentroGestor()!=null){
			alertaBean.setIdCentroGestor(alerta.getCentroGestor().getIdCentroGestor());	
		}
		alertaBean.setIdPerfil(alerta.getPerfil().getId());
		alertaBean.setIdTipo(alerta.getTipoAlerta().getId());
		alertaBean.setIdModo(alerta.getModoAlerta().getId());		
		Iterator<Usuario> iUsuarios=null;
		/*INI-TRA042*/
		List<String> usuariosList=new ArrayList<String>();
		/*FIN-TRA042*/
		iUsuarios= alerta.getUsuarios().iterator();
		while (iUsuarios.hasNext()){
			usuariosList.add(iUsuarios.next().getId().toString());
		}
		alertaBean.setUsuariosList(usuariosList);
		
		
		return alertaBean;
	}

	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.AlertaManager#modificarAlerta(es.map.ipsg.bean.AlertaBean)
	 */
	public void modificarAlerta(AlertaBean alertaBean){
		Alerta alerta = toAlerta(alertaBean);
		alertaDAO.update(alerta);
	}
	
	/**
	 * Obtiene el logger.
	 *
	 * @return el logger
	 */
	public static Logger getLogger() {
		return logger;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.AlertaManager#comprobarAlertas(es.map.ipsg.bean.UsuarioBean, es.map.ips.common.spring.SpringMessages)
	 */
	/*INI-TRA042*/
	@SuppressWarnings("rawtypes")
	public void comprobarAlertas(UsuarioBean usuarioBean,SpringMessages mensajes){
		if(usuarioBean.getIdPerfil().equals(Integer.valueOf(Constantes.PERFIL_ADMINISTRADOR)) || 
				usuarioBean.getIdPerfil().equals(Integer.valueOf(Constantes.PERFIL_GESTOR))){
			
			AlertaQuery alertaQuery = new AlertaQuery();
			alertaQuery.setEstado(Constantes.ALERTA_ESTADO_ACTIVO);
			
			ModoAlertaQuery modoAlerta = new ModoAlertaQuery();
			modoAlerta.setId(Constantes.MODO_ALERTA_POPUP);
			alertaQuery.setModoAlerta(modoAlerta);
			
			PerfilQuery perfilQuery = new PerfilQuery();
			perfilQuery.setId(usuarioBean.getIdPerfil());
			alertaQuery.setPerfil(perfilQuery);
									
			if(usuarioBean.getIdPerfil().equals(Integer.valueOf(Constantes.PERFIL_GESTOR))){
				CentroGestorQuery centroGestorQuery = new CentroGestorQuery();

				List<CentroGestorBean> listaCentrosGestores = usuarioCentrogestorManager.buscarCentrosGestoresByIdUsuario(usuarioBean.getId());
				
				for(CentroGestorBean cg: listaCentrosGestores){
					centroGestorQuery.addIdCentroGestorIn(cg.getId());
				}
				
				alertaQuery.setCentroGestor(centroGestorQuery);
			}
					
			ArrayList<AlertaBean> listadoAlertas = buscarAlertaAll(alertaQuery);
	
			if(listadoAlertas.size()>0){
				
				Iterator itListadoAlertas = listadoAlertas.iterator();
				while (itListadoAlertas.hasNext()){
					AlertaBean alertaBean = (AlertaBean)itListadoAlertas.next();
					comprobarTipoAlerta(alertaBean,mensajes);
					alertaBean.setEstado(Constantes.ALERTA_ESTADO_DESACTIVADO);
					this.actualizarAlerta(alertaBean);
				}				
			}			
		}
	}
	/*FIN-TRA042*/
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.AlertaManager#comprobarTipoAlerta(es.map.ipsg.bean.AlertaBean, es.map.ips.common.spring.SpringMessages)
	 */
	public void comprobarTipoAlerta(AlertaBean alertaBean,SpringMessages mensajes){
		
		//Cargamos los querys para hacer las consulta
		CentroGestorQuery centroGestorQuery = new CentroGestorQuery();
		centroGestorQuery.setId(alertaBean.getIdCentroGestor());
		centroGestorQuery.setEstado(Constantes.CENTROGESTOR_ESTADO_ACTIVO);
		
		CuerpoEscalaQuery cuerpoEscalaQuery = new CuerpoEscalaQuery();
		cuerpoEscalaQuery.setCentroGestor(centroGestorQuery);
		cuerpoEscalaQuery.setEstado(Constantes.CUERPO_ESCALA_ESTADO_ACTIVO);
		
		ConvocatoriaQuery convocatoriaQuery = new ConvocatoriaQuery();
		convocatoriaQuery.setCuerpoEscala(cuerpoEscalaQuery);
		
		ArrayList<String> listadoMensajesEmail = new ArrayList<String>();
		
		switch (alertaBean.getIdTipo()) {
			case 1:
				ningunaInscripcion(convocatoriaQuery,alertaBean,mensajes,listadoMensajesEmail);
				break;
			case 2:
				ningunaInscripcionAyer(convocatoriaQuery,alertaBean,mensajes,listadoMensajesEmail);
				break;				
			case 3:
				inscripcionPagadaNoRegistrada(convocatoriaQuery,alertaBean,mensajes,listadoMensajesEmail);					
				break;									
			case 4:
				cambiarEstadoTipo4(convocatoriaQuery,alertaBean,mensajes,listadoMensajesEmail);
				break;
			case 5:
				cambiarEstadoTipo5(convocatoriaQuery,alertaBean,mensajes,listadoMensajesEmail);
				break;
			case 6:		
				cambiarEstadoTipo6(convocatoriaQuery,alertaBean,mensajes,listadoMensajesEmail);
				break;
			case 7:
				cambiarEstadoTipo7(convocatoriaQuery,alertaBean,mensajes,listadoMensajesEmail);
				break;
			default:
				break;
			}
		
		if(alertaBean.getIdTipo() >= 8 && alertaBean.getIdTipo() <= 15){
			batchIntermediacionFinalizado(alertaBean,mensajes,alertaBean.getIdTipo());
		}
		
		//Comprobamos si listadoMensajesEmail es distinto de null, ya que si es distinto de null, 
		//significa que la alerta sera enviada por email.
		if(listadoMensajesEmail!=null && listadoMensajesEmail.size()>0){
			
			//Construimos el correo electronico
			crearCorreoElectronico(listadoMensajesEmail,alertaBean);
		}
	}
	
	/**
	 * Crear correo electronico.
	 *
	 * @param listadoMensajesEmail el listado mensajes email
	 * @param alerta el alerta
	 */
	private void crearCorreoElectronico(ArrayList<String> listadoMensajesEmail,AlertaBean alerta){
		CorreoElectronicoBean correoElectronicoBean = new CorreoElectronicoBean();
		String mensaje = new String();
		properties = (Properties) ApplicationContextProvider.getInstance().getBean("propertiesBean");
		
		correoElectronicoBean.setDe(properties.getProperty("mail.de"));
		
		ParametrosConfiguracionQuery parametrosConfiguracionQuery = new ParametrosConfiguracionQuery();
		parametrosConfiguracionQuery.setNombreCampo(RESOURCE_BUNDLE.getString("field.parametroConfiguracion.nombreCampo.correoAlertas"));
		ParametrosConfiguracionBean parametroConfig = parametroConfiguracionManager.buscarParametroConfiguracionUnico(parametrosConfiguracionQuery);
		
		//TODO Modificar Para y cogerlo de la tabla parametros_configuracion
		correoElectronicoBean.setPara(parametroConfig.getValorCampo());
		correoElectronicoBean.setAsunto(RESOURCE_BUNDLE.getString("field.alertaMensaje.correoElectronico.asunto"));
		
		
		
		
		for(int i=0;i<listadoMensajesEmail.size();i++){
			mensaje+=listadoMensajesEmail.get(i)+"\n";			
		}
		if(mensaje.length()>2000){
			String mensajeModificado = mensaje.substring(0, 1990);
			correoElectronicoBean.setMensaje(mensajeModificado);
		}else{
			correoElectronicoBean.setMensaje(mensaje);
		}
		
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE,0);
		Date fechaEnvio = c.getTime();
		
		correoElectronicoBean.setFechaEnvio(fechaEnvio);
		correoElectronicoBean.setEstado(Constantes.CORREO_ELECTRONICO_ESTADO_ENVIADO);
		
		Set<Integer> idAlerta = new HashSet<Integer>();
		
		if(alerta.getIdModo().equals(Constantes.MODO_ALERTA_CORREO_ELECTRONICO)){
			idAlerta.add(alerta.getId());
		}
		correoElectronicoBean.setIdAlertas(idAlerta);
		
		if(EnvioMails.envioMailAlertas(correoElectronicoBean,alerta.getBcc())){
			
			InternetAddress[] direcciones = alerta.getBcc();
			String direcionesPara = "";
			for(int i = 0; i<direcciones.length;i++){
				if((direcciones.length - 1) == i){
					direcionesPara=direcionesPara+direcciones[i].getAddress();
				}else{
					direcionesPara=direcionesPara+direcciones[i].getAddress()+",";
				}
			}
			
			if(!direcionesPara.equals("")){
				if(direcionesPara.length() > 1000){
					correoElectronicoBean.setPara(direcionesPara.substring(0,999));
				}else{
					correoElectronicoBean.setPara(direcionesPara);
				}
				
			}
			
			correoElectronicoManager.guardarCorreoElectronico(correoElectronicoBean);		
		}
		
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.AlertaManager#ningunaInscripcion(es.map.ips.model.ConvocatoriaQuery, es.map.ipsg.bean.AlertaBean, es.map.ips.common.spring.SpringMessages, java.util.ArrayList)
	 */
	public void ningunaInscripcion(ConvocatoriaQuery convocatoriaQuery,AlertaBean alertaBean,SpringMessages mensajes,ArrayList<String> listadoMensajesEmail){

		EstadoConvocatoriaQuery estadoConvocatoriaQuery = new EstadoConvocatoriaQuery();
		estadoConvocatoriaQuery.setId(Constantes.ESTADO_CONVOCATORIA_PUBLICADO);
		convocatoriaQuery.setEstadoConvocatoria(estadoConvocatoriaQuery);
		

		//Hacemos la consulta y obtenemos un listado con el resultado de la busqueda
		ArrayList<ConvocatoriasBean> listadoConvocatorias = convocatoriasManager.buscarConvocatoriasAll(convocatoriaQuery);
		int contador = 0;
		
		if (listadoConvocatorias!=null && listadoConvocatorias.size() >0){
			
			Iterator<ConvocatoriasBean> itConvocatorias =  listadoConvocatorias.iterator();
			//Por cada convocatoria, vamos a obtener lo que queremos comprobar. En este caso, la fecha hasta el dia de hoy, o hasta ayer
			while (itConvocatorias.hasNext()) {
				ConvocatoriasBean convocatoriasBean = (ConvocatoriasBean) itConvocatorias.next();
				
			
				
				SolicitudComunQuery solicitudQuery = new SolicitudComunQuery();
				solicitudQuery.setConvocatoria(convocatoriaQuery);
				
					Calendar c = Calendar.getInstance();
					c.add(Calendar.DATE,0);
					Date fechaHoy = c.getTime();	
					solicitudQuery.setFechaSolicitudMax(fechaHoy);
										
				//Hacemos la consulta, si vuelve 1 o mas registros, no habría que alertar. Si no devuelve registros, mostramos la alerta 
				ArrayList<SolicitudBean> listadoSolicitudes = solicitudesRegistradasManager.buscarSolicitud(Long.valueOf(convocatoriasBean.getIdConvocatoria()));
				
				if(listadoSolicitudes.size() == 0)
				{				
					//Comprobamos el modo de alerta, para que salte un popup o la mande por email
					if(alertaBean.getIdModo().equals(Constantes.MODO_ALERTA_POPUP)){
						//Creamos el cuerpo del mensaje
						SpringMessage mensaje= new SpringMessage("field.alertaMensaje.tipo1",convocatoriasBean.getIdConvocatoria().toString());
						mensajes.add(STRING_ERRORCONVOCATORIA,mensaje);
					}else{
						contador++;
						if(contador ==1){
							//Creamos la cabezara del mensaje
							String cabecera =RESOURCE_BUNDLE.getString("field.alertaCorreo.tipo1");
							listadoMensajesEmail.add(cabecera);
						}
						
						//Creamos el cuerpo del mensaje
						String mensaje = convocatoriasBean.getIdConvocatoria().toString();
						listadoMensajesEmail.add(mensaje);
					}								
				}
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.AlertaManager#ningunaInscripcionAyer(es.map.ips.model.ConvocatoriaQuery, es.map.ipsg.bean.AlertaBean, es.map.ips.common.spring.SpringMessages, java.util.ArrayList)
	 */
	public void ningunaInscripcionAyer(ConvocatoriaQuery convocatoriaQuery,AlertaBean alertaBean,SpringMessages mensajes,ArrayList<String> listadoMensajesEmail){

		EstadoConvocatoriaQuery estadoConvocatoriaQuery = new EstadoConvocatoriaQuery();
		estadoConvocatoriaQuery.setId(Constantes.ESTADO_CONVOCATORIA_PUBLICADO);
		convocatoriaQuery.setEstadoConvocatoria(estadoConvocatoriaQuery);
		
		//Hacemos la consulta y obtenemos un listado con el resultado de la busqueda
		ArrayList<ConvocatoriasBean> listadoConvocatorias = convocatoriasManager.buscarConvocatoriasAll(convocatoriaQuery);
		int contador = 0;
		//Por cada convocatoria, vamos a obtener lo que queremos comprobar. En este caso, la fecha hasta el dia de hoy, o hasta ayer
	
		for(int i=0;i<listadoConvocatorias.size();i++){
			convocatoriaQuery.setId(Long.valueOf(listadoConvocatorias.get(i).getIdConvocatoria()));
			SolicitudComunQuery solicitudQuery = new SolicitudComunQuery();
			solicitudQuery.setConvocatoria(convocatoriaQuery);			
					
			Calendar c = Calendar.getInstance();
			c.add(Calendar.DATE, -1);
			
			try {
				Date fechaAyer = DateUtil.parse(DateUtil.format(c.getTime(), "dd/MM/yyyy"), "dd/MM/yyyy");
				solicitudQuery.setFechaSolicitudMax(fechaAyer);
				solicitudQuery.setFechaSolicitudMin(fechaAyer);	
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				logger.error("Error AlertaManagerImpl -  ningunaInscripcionAyer",e);
				
			}
			
					
			
			
			//Hacemos la consulta, si vuelve 1 o mas registros, no habría que alertar. Si no devuelve registros, mostramos la alerta 
			ArrayList<SolicitudBean> listadoSolicitudes = solicitudesRegistradasManager.buscarSolicitudAll(solicitudQuery);
			
			if(listadoSolicitudes.size() == 0)
			{				
				//Comprobamos el modo de alerta, para que salte un popup o la mande por email
				if(alertaBean.getIdModo().equals(Constantes.MODO_ALERTA_POPUP)){
					SpringMessage mensaje= new SpringMessage("field.alertaMensaje.tipo2",listadoConvocatorias.get(i).getIdConvocatoria().toString());
					mensajes.add(STRING_ERRORCONVOCATORIA,mensaje);
				}else{
					contador++;
					if(contador ==1){
						//Creamos la cabezara del mensaje
						String cabecera =RESOURCE_BUNDLE.getString("field.alertaCorreo.tipo2");
						listadoMensajesEmail.add(cabecera);
					}
					
					//Creamos el cuerpo del mensaje
					String mensaje = listadoConvocatorias.get(i).getIdConvocatoria().toString();
					listadoMensajesEmail.add(mensaje);
				}					
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.AlertaManager#inscripcionPagadaNoRegistrada(es.map.ips.model.ConvocatoriaQuery, es.map.ipsg.bean.AlertaBean, es.map.ips.common.spring.SpringMessages, java.util.ArrayList)
	 */
	public void inscripcionPagadaNoRegistrada(ConvocatoriaQuery convocatoriaQuery,AlertaBean alertaBean,SpringMessages mensajes,ArrayList<String> listadoMensajesEmail){

		EstadoConvocatoriaQuery estadoConvocatoriaQuery = new EstadoConvocatoriaQuery();
		estadoConvocatoriaQuery.setId(Constantes.ESTADO_CONVOCATORIA_PUBLICADO);
		convocatoriaQuery.setEstadoConvocatoria(estadoConvocatoriaQuery);
		//Hacemos la consulta y obtenemos un listado con el resultado de la busqueda
		ArrayList<ConvocatoriasBean> listadoConvocatorias = convocatoriasManager.buscarConvocatoriasAll(convocatoriaQuery);
		int contador = 0;
		//Por cada convocatoria, vamos a obtener lo que queremos comprobar. En este caso, inscripciones pagadas y no registradas
		for(int i=0;i<listadoConvocatorias.size();i++){
			SolicitudComunQuery solicitudQuery = new SolicitudComunQuery();
			convocatoriaQuery.setId(Long.valueOf(listadoConvocatorias.get(i).getIdConvocatoria()));
			solicitudQuery.setConvocatoria(convocatoriaQuery);
			
			EstadoSolicitudQuery estadoSolicitudQuery = new EstadoSolicitudQuery();
			estadoSolicitudQuery.setId(Constantes.ESTADO_SOLICITUD_NO_REGISTRADO);
			
			solicitudQuery.setEstadoSolicitud(estadoSolicitudQuery);
			//falta asiganarle que la solicitud esté pagada
			
			//Hacemos la consulta, si vuelve 1 o mas registros, no habría que alertar. Si no devuelve registros, mostramos la alerta 
			ArrayList<SolicitudBean> listadoSolicitudes = solicitudesRegistradasManager.buscarSolicitudAll(solicitudQuery);
			
			if(listadoSolicitudes.size() > 0)
			{
				//Comprobamos el modo de alerta, para que salte un popup o la mande por email
				if(alertaBean.getIdModo().equals(Constantes.MODO_ALERTA_POPUP)){
					//Creamos el mensaje
					SpringMessage mensaje= new SpringMessage("field.alertaMensaje.tipo3",listadoConvocatorias.get(i).getIdConvocatoria().toString());
 					mensajes.add("alertaIntermediacion",mensaje);
				}else{
					contador++;
					if(contador ==1){
						//Creamos la cabezara del mensaje
						
						String cabecera =RESOURCE_BUNDLE.getString("field.alertaCorreo.tipo3");
						listadoMensajesEmail.add(cabecera);
					}
					
					//Creamos el cuerpo del mensaje
					String mensaje = listadoConvocatorias.get(i).getIdConvocatoria().toString();
					listadoMensajesEmail.add(mensaje);
					
				}	
			}
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.AlertaManager#cambiarEstadoTipo4(es.map.ips.model.ConvocatoriaQuery, es.map.ipsg.bean.AlertaBean, es.map.ips.common.spring.SpringMessages, java.util.ArrayList)
	 */
	@SuppressWarnings("static-access")
	public void cambiarEstadoTipo4(ConvocatoriaQuery convocatoriaQuery,AlertaBean alertaBean,SpringMessages mensajes,ArrayList<String> listadoMensajesEmail){
		
		//Hacemos la consulta y obtenemos un listado con el resultado de la busqueda
		ArrayList<ConvocatoriasBean> listadoConvocatorias = convocatoriasManager.buscarConvocatoriasAll(convocatoriaQuery);
		int contador = 0;
		
		LogConvocatoriaQuery logConvocatoriaQuery = new LogConvocatoriaQuery();
		//Por cada convocatoria, vamos a obtener lo que queremos comprobar.
		
		Iterator<ConvocatoriasBean> itConvocatorias = listadoConvocatorias.iterator();
		
		while (itConvocatorias.hasNext()) {
			ConvocatoriasBean convocatoriasBean = (ConvocatoriasBean) itConvocatorias.next();
			
			logConvocatoriaQuery.addConvocatoriaIn(Long.valueOf(convocatoriasBean.getIdConvocatoria()));
			
			
		}
		
		EstadoConvocatoriaQuery estadoConvocatoriaQueryActual = new EstadoConvocatoriaQuery();
		estadoConvocatoriaQueryActual.setId(Constantes.ESTADO_CONVOCATORIA_APROBADO);
		
		logConvocatoriaQuery.setEstadoConvocatoriaByIdEstadoActual(estadoConvocatoriaQueryActual);
		
		EstadoConvocatoriaQuery estadoConvocatoriaQueryAnterior = new EstadoConvocatoriaQuery();
		estadoConvocatoriaQueryAnterior.setId(Constantes.ESTADO_CONVOCATORIA_CONFIGURACION);
		
		logConvocatoriaQuery.setEstadoConvocatoriaByIdEstadoAnterior(estadoConvocatoriaQueryAnterior);
		
		//Le establezco el orden
		logConvocatoriaQuery.addOrder(logConvocatoriaQuery.CONVOCATORIA, OrderType.ASC );
		
		ArrayList<LogConvocatoriaBean> listaLogConvocatorias = logConvocatoriaManager.buscarConvocatoriaAll(logConvocatoriaQuery);
		
		if(listaLogConvocatorias!=null && listaLogConvocatorias.size() > 0)
		{
			
			String convocatoriaIdAux = "0";
			for (LogConvocatoriaBean logConvocatoriaBean : listaLogConvocatorias) {
				
				String convocatoriaId =logConvocatoriaBean.getNombreConvocatoria(); 
			//Comprobamos el modo de alerta, para que salte un popup o la mande por email
				if(alertaBean.getIdModo().equals(Constantes.MODO_ALERTA_POPUP) ){
					
					if (!convocatoriaIdAux.equalsIgnoreCase(convocatoriaId)){
						//Creamos el mensaje
						SpringMessage mensaje= new SpringMessage("field.alertaMensaje.tipo4",convocatoriaId);
						mensajes.add(STRING_ERRORCONVOCATORIA,mensaje);
						
						convocatoriaIdAux = convocatoriaId;	
					}
				}else{
					contador++;
					if(contador ==1){
						//Creamos la cabezara del mensaje
					
						String cabecera =RESOURCE_BUNDLE.getString("field.alertaCorreo.tipo4");
						listadoMensajesEmail.add(cabecera);
					}
					
					if (!convocatoriaIdAux.equalsIgnoreCase(convocatoriaId)){
						//Creamos el cuerpo del mensaje
						String mensaje = convocatoriaId;
						listadoMensajesEmail.add(mensaje);
					}
				}	
			}
		}
		
	}
	
/* (non-Javadoc)
 * @see es.map.ipsg.manager.AlertaManager#cambiarEstadoTipo5(es.map.ips.model.ConvocatoriaQuery, es.map.ipsg.bean.AlertaBean, es.map.ips.common.spring.SpringMessages, java.util.ArrayList)
 */
@SuppressWarnings("static-access")
public void cambiarEstadoTipo5(ConvocatoriaQuery convocatoriaQuery,AlertaBean alertaBean,SpringMessages mensajes,ArrayList<String> listadoMensajesEmail){
		
		//Hacemos la consulta y obtenemos un listado con el resultado de la busqueda
		ArrayList<ConvocatoriasBean> listadoConvocatorias = convocatoriasManager.buscarConvocatoriasAll(convocatoriaQuery);
		int contador = 0;
		
		LogConvocatoriaQuery logConvocatoriaQuery = new LogConvocatoriaQuery();
		//Por cada convocatoria, vamos a obtener lo que queremos comprobar.
		
		Iterator<ConvocatoriasBean> itConvocatorias = listadoConvocatorias.iterator();
		
		while (itConvocatorias.hasNext()) {
			ConvocatoriasBean convocatoriasBean = (ConvocatoriasBean) itConvocatorias.next();
			
			logConvocatoriaQuery.addConvocatoriaIn(Long.valueOf(convocatoriasBean.getIdConvocatoria()));
			
			
		}
		
		EstadoConvocatoriaQuery estadoConvocatoriaQueryActual = new EstadoConvocatoriaQuery();
		estadoConvocatoriaQueryActual.setId(Constantes.ESTADO_CONVOCATORIA_PUBLICADO);
		
		logConvocatoriaQuery.setEstadoConvocatoriaByIdEstadoActual(estadoConvocatoriaQueryActual);
		
		EstadoConvocatoriaQuery estadoConvocatoriaQueryAnterior = new EstadoConvocatoriaQuery();
		estadoConvocatoriaQueryAnterior.setId(Constantes.ESTADO_CONVOCATORIA_APROBADO);
		
		logConvocatoriaQuery.setEstadoConvocatoriaByIdEstadoAnterior(estadoConvocatoriaQueryAnterior);
		
		//Le establezco el orden
		logConvocatoriaQuery.addOrder(logConvocatoriaQuery.CONVOCATORIA, OrderType.ASC );
		
		ArrayList<LogConvocatoriaBean> listaLogConvocatorias = logConvocatoriaManager.buscarConvocatoriaAll(logConvocatoriaQuery);
		
		if(listaLogConvocatorias!=null && listaLogConvocatorias.size() > 0)
		{
			
			String convocatoriaIdAux = "0";
			for (LogConvocatoriaBean logConvocatoriaBean : listaLogConvocatorias) {
				
				String convocatoriaId =logConvocatoriaBean.getNombreConvocatoria(); 
			//Comprobamos el modo de alerta, para que salte un popup o la mande por email
				if(alertaBean.getIdModo().equals(Constantes.MODO_ALERTA_POPUP) ){
					
					if (!convocatoriaIdAux.equalsIgnoreCase(convocatoriaId)){
						//Creamos el mensaje
						SpringMessage mensaje= new SpringMessage("field.alertaMensaje.tipo5",convocatoriaId);
						mensajes.add(STRING_ERRORCONVOCATORIA,mensaje);
						
						convocatoriaIdAux = convocatoriaId;	
					}
				}else{
					contador++;
					if(contador ==1){
						//Creamos la cabezara del mensaje
					
						String cabecera =RESOURCE_BUNDLE.getString("field.alertaCorreo.tipo5");
						listadoMensajesEmail.add(cabecera);
					}
					
					if (!convocatoriaIdAux.equalsIgnoreCase(convocatoriaId)){
						//Creamos el cuerpo del mensaje
						String mensaje = convocatoriaId;
						listadoMensajesEmail.add(mensaje);
					}
				}	
			}
		}
		
	}
	
/* (non-Javadoc)
 * @see es.map.ipsg.manager.AlertaManager#cambiarEstadoTipo6(es.map.ips.model.ConvocatoriaQuery, es.map.ipsg.bean.AlertaBean, es.map.ips.common.spring.SpringMessages, java.util.ArrayList)
 */
@SuppressWarnings("static-access")
public void cambiarEstadoTipo6(ConvocatoriaQuery convocatoriaQuery,AlertaBean alertaBean,SpringMessages mensajes,ArrayList<String> listadoMensajesEmail){
	
	//Hacemos la consulta y obtenemos un listado con el resultado de la busqueda
	ArrayList<ConvocatoriasBean> listadoConvocatorias = convocatoriasManager.buscarConvocatoriasAll(convocatoriaQuery);
	int contador = 0;
	
	LogConvocatoriaQuery logConvocatoriaQuery = new LogConvocatoriaQuery();
	//Por cada convocatoria, vamos a obtener lo que queremos comprobar.
	
	Iterator<ConvocatoriasBean> itConvocatorias = listadoConvocatorias.iterator();
	
	while (itConvocatorias.hasNext()) {
		ConvocatoriasBean convocatoriasBean = (ConvocatoriasBean) itConvocatorias.next();
		
		logConvocatoriaQuery.addConvocatoriaIn(Long.valueOf(convocatoriasBean.getIdConvocatoria()));
		
		
	}
	
	EstadoConvocatoriaQuery estadoConvocatoriaQueryActual = new EstadoConvocatoriaQuery();
	estadoConvocatoriaQueryActual.setId(Constantes.ESTADO_CONVOCATORIA_FINALIZADO);
	
	logConvocatoriaQuery.setEstadoConvocatoriaByIdEstadoActual(estadoConvocatoriaQueryActual);
	
	EstadoConvocatoriaQuery estadoConvocatoriaQueryAnterior = new EstadoConvocatoriaQuery();
	estadoConvocatoriaQueryAnterior.setId(Constantes.ESTADO_CONVOCATORIA_PUBLICADO);
	
	logConvocatoriaQuery.setEstadoConvocatoriaByIdEstadoAnterior(estadoConvocatoriaQueryAnterior);
	
	//Le establezco el orden
	logConvocatoriaQuery.addOrder(logConvocatoriaQuery.CONVOCATORIA, OrderType.ASC );
	
	ArrayList<LogConvocatoriaBean> listaLogConvocatorias = logConvocatoriaManager.buscarConvocatoriaAll(logConvocatoriaQuery);
	
	if(listaLogConvocatorias!=null && listaLogConvocatorias.size() > 0)
	{
		
		String convocatoriaIdAux = "0";
		for (LogConvocatoriaBean logConvocatoriaBean : listaLogConvocatorias) {
			
			String convocatoriaId =logConvocatoriaBean.getNombreConvocatoria(); 
		//Comprobamos el modo de alerta, para que salte un popup o la mande por email
			if(alertaBean.getIdModo().equals(Constantes.MODO_ALERTA_POPUP) ){
				
				if (!convocatoriaIdAux.equalsIgnoreCase(convocatoriaId)){
					//Creamos el mensaje
					SpringMessage mensaje= new SpringMessage("field.alertaMensaje.tipo6",convocatoriaId);
					mensajes.add(STRING_ERRORCONVOCATORIA,mensaje);
					
					convocatoriaIdAux = convocatoriaId;	
				}
			}else{
				contador++;
				if(contador ==1){
					//Creamos la cabezara del mensaje
				
					String cabecera =RESOURCE_BUNDLE.getString("field.alertaCorreo.tipo6");
					listadoMensajesEmail.add(cabecera);
				}
				
				if (!convocatoriaIdAux.equalsIgnoreCase(convocatoriaId)){
					//Creamos el cuerpo del mensaje
					String mensaje = convocatoriaId;
					listadoMensajesEmail.add(mensaje);
				}
			}	
		}
	}
	
}
	
	
/* (non-Javadoc)
 * @see es.map.ipsg.manager.AlertaManager#cambiarEstadoTipo7(es.map.ips.model.ConvocatoriaQuery, es.map.ipsg.bean.AlertaBean, es.map.ips.common.spring.SpringMessages, java.util.ArrayList)
 */
@SuppressWarnings("static-access")
public void cambiarEstadoTipo7(ConvocatoriaQuery convocatoriaQuery,AlertaBean alertaBean,SpringMessages mensajes,ArrayList<String> listadoMensajesEmail){
	
	//Hacemos la consulta y obtenemos un listado con el resultado de la busqueda
	ArrayList<ConvocatoriasBean> listadoConvocatorias = convocatoriasManager.buscarConvocatoriasAll(convocatoriaQuery);
	int contador = 0;
	
	LogConvocatoriaQuery logConvocatoriaQuery = new LogConvocatoriaQuery();
	//Por cada convocatoria, vamos a obtener lo que queremos comprobar.
	
	Iterator<ConvocatoriasBean> itConvocatorias = listadoConvocatorias.iterator();
	
	while (itConvocatorias.hasNext()) {
		ConvocatoriasBean convocatoriasBean = (ConvocatoriasBean) itConvocatorias.next();
		
		logConvocatoriaQuery.addConvocatoriaIn(Long.valueOf(convocatoriasBean.getIdConvocatoria()));
		
		
	}
	
	EstadoConvocatoriaQuery estadoConvocatoriaQueryActual = new EstadoConvocatoriaQuery();
	estadoConvocatoriaQueryActual.setId(Constantes.ESTADO_CONVOCATORIA_CERRADO);
	
	logConvocatoriaQuery.setEstadoConvocatoriaByIdEstadoActual(estadoConvocatoriaQueryActual);
	
	EstadoConvocatoriaQuery estadoConvocatoriaQueryAnterior = new EstadoConvocatoriaQuery();
	estadoConvocatoriaQueryAnterior.setId(Constantes.ESTADO_CONVOCATORIA_FINALIZADO);
	
	logConvocatoriaQuery.setEstadoConvocatoriaByIdEstadoAnterior(estadoConvocatoriaQueryAnterior);
	
	//Le establezco el orden
	logConvocatoriaQuery.addOrder(logConvocatoriaQuery.CONVOCATORIA, OrderType.ASC );
	
	ArrayList<LogConvocatoriaBean> listaLogConvocatorias = logConvocatoriaManager.buscarConvocatoriaAll(logConvocatoriaQuery);
	
	if(listaLogConvocatorias!=null && listaLogConvocatorias.size() > 0)
	{
		
		String convocatoriaIdAux = "0";
		for (LogConvocatoriaBean logConvocatoriaBean : listaLogConvocatorias) {
			
			String convocatoriaId =logConvocatoriaBean.getNombreConvocatoria(); 
		//Comprobamos el modo de alerta, para que salte un popup o la mande por email
			if(alertaBean.getIdModo().equals(Constantes.MODO_ALERTA_POPUP) ){
				
				if (!convocatoriaIdAux.equalsIgnoreCase(convocatoriaId)){
					//Creamos el mensaje
					SpringMessage mensaje= new SpringMessage("field.alertaMensaje.tipo7",convocatoriaId);
					mensajes.add(STRING_ERRORCONVOCATORIA,mensaje);
					
					convocatoriaIdAux = convocatoriaId;	
				}
			}else{
				contador++;
				if(contador ==1){
					//Creamos la cabezara del mensaje
				
					String cabecera =RESOURCE_BUNDLE.getString("field.alertaCorreo.tipo7");
					listadoMensajesEmail.add(cabecera);
				}
				
				if (!convocatoriaIdAux.equalsIgnoreCase(convocatoriaId)){
					//Creamos el cuerpo del mensaje
					String mensaje = convocatoriaId;
					listadoMensajesEmail.add(mensaje);
				}
			}	
		}
	}
	
}
	
	/**
	 * Batch intermediacion finalizado.
	 *
	 * @param alertaBean el alerta bean
	 * @param mensajes el mensajes
	 * @param tipoAlerta el tipo alerta
	 */
	public void batchIntermediacionFinalizado(AlertaBean alertaBean, SpringMessages mensajes, Integer tipoAlerta) {
		if (alertaBean.getIdModo().equals(Constantes.MODO_ALERTA_POPUP)) {
			SpringMessage mensaje= new SpringMessage("field.alertaMensaje.tipo" + tipoAlerta, alertaBean.getIdPeticion());
			mensajes.add("alertaIntermediacion",mensaje);
		}
	}

	
	/**
	 * Obtiene el convocatorias manager.
	 *
	 * @return the convocatoriasManager
	 */
	public ConvocatoriasManager getConvocatoriasManager() {
		return convocatoriasManager;
	}

	/**
	 * Establece el convocatorias manager.
	 *
	 * @param convocatoriasManager the convocatoriasManager to set
	 */
	public void setConvocatoriasManager(ConvocatoriasManager convocatoriasManager) {
		this.convocatoriasManager = convocatoriasManager;
	}

	/**
	 * Obtiene el solicitudes registradas manager.
	 *
	 * @return the solicitudesRegistradasManager
	 */
	public SolicitudesRegistradasManager getSolicitudesRegistradasManager() {
		return solicitudesRegistradasManager;
	}

	/**
	 * Establece el solicitudes registradas manager.
	 *
	 * @param solicitudesRegistradasManager the solicitudesRegistradasManager to set
	 */
	public void setSolicitudesRegistradasManager(
			SolicitudesRegistradasManager solicitudesRegistradasManager) {
		this.solicitudesRegistradasManager = solicitudesRegistradasManager;
	}
	
	/**
	 * Obtiene el correo electronico manager.
	 *
	 * @return the correoElectronicoManager
	 */
	public CorreoElectronicoManager getCorreoElectronicoManager() {
		return correoElectronicoManager;
	}

	/**
	 * Establece el correo electronico manager.
	 *
	 * @param correoElectronicoManager the correoElectronicoManager to set
	 */
	public void setCorreoElectronicoManager(
			CorreoElectronicoManager correoElectronicoManager) {
		this.correoElectronicoManager = correoElectronicoManager;
	}

	/**
	 * Obtiene el parametro configuracion manager.
	 *
	 * @return the parametroConfiguracionManager
	 */
	public ParametroConfiguracionManager getParametroConfiguracionManager() {
		return parametroConfiguracionManager;
	}

	/**
	 * Establece el parametro configuracion manager.
	 *
	 * @param parametroConfiguracionManager the parametroConfiguracionManager to set
	 */
	public void setParametroConfiguracionManager(
			ParametroConfiguracionManager parametroConfiguracionManager) {
		this.parametroConfiguracionManager = parametroConfiguracionManager;
	}
	
	/**
	 * Obtiene el log convocatoria manager.
	 *
	 * @return the logConvocatoriaManager
	 */
	public LogConvocatoriaManager getLogConvocatoriaManager() {
		return logConvocatoriaManager;
	}

	/**
	 * Establece el log convocatoria manager.
	 *
	 * @param logConvocatoriaManager the logConvocatoriaManager to set
	 */
	public void setLogConvocatoriaManager(
			LogConvocatoriaManager logConvocatoriaManager) {
		this.logConvocatoriaManager = logConvocatoriaManager;
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