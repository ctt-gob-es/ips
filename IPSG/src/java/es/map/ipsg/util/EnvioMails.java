package es.map.ipsg.util;

import java.io.File;
import java.util.Iterator;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.map.j2ee.mail.MailManager;
import com.map.j2ee.mail.MailMessageVO;

import es.map.ips.common.spring.ApplicationContextProvider;
import es.map.ipsg.bean.CorreoElectronicoBean;

/**
 * El Class EnvioMails.
 */
public class EnvioMails {
    
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(EnvioMails.class);
	
	/** Nombre del archivo de propiedades. */
	private static final String BUNDLE_MESSAGES = "MessageResources";

	/** El contenedor de recursos que se usara. */
	private static final ResourceBundle RESOURCE_MESSAGES = ResourceBundle.getBundle(BUNDLE_MESSAGES);

	/** el properties. */
	private static Properties properties;
	
	/** La constante STRING_ERROR_ENVIAR_EMAIL. */
	private static final String STRING_ERROR_ENVIAR_EMAIL = "Error no se ha podido enviar el email ";
	
	/** La constante STRING_MAIL_USERNAME. */
	private static final String STRING_MAIL_USERNAME = "mail.username";
	
	/** La constante STRING_MAIL_PASS. */
	private static final String STRING_MAIL_PASS = "mail.password";
	
	
	
	
	/**
	 * Envio mail deprecado.
	 *
	 * @param correoElectronicoBean el correo electronico bean
	 * @return verdadero, si tiene exito
	 */
	public static boolean envioMailDeprecado(CorreoElectronicoBean correoElectronicoBean){
    	
		logger.debug("envioMail");
				
		MailManager envioMail = null;
		
		try{
				
			envioMail = (MailManager)ApplicationContextProvider.getInstance().getBean("templateMailManager");
			
			MailMessageVO mensaje = new MailMessageVO();
			
			mensaje.setFrom(correoElectronicoBean.getDe());
			mensaje.setTo(correoElectronicoBean.getPara());
			
			if(correoElectronicoBean.getAdjuntos()!=null && correoElectronicoBean.getAdjuntos().size()>0){
				mensaje.setAttachments(correoElectronicoBean.getAdjuntos());
			}
			if(correoElectronicoBean.getAdjuntosFile()!=null && correoElectronicoBean.getAdjuntosFile().size()>0){
				mensaje.setAttachments(correoElectronicoBean.getAdjuntosFile());
			}			
			
			if(correoElectronicoBean.getBcc()!=null && correoElectronicoBean.getBcc().length>0){
				mensaje.setBcc(correoElectronicoBean.getBcc());
			}
			mensaje.setSubject(correoElectronicoBean.getAsunto());
			
			String mailMessage = UtilesIPSG.replaceIntros(correoElectronicoBean.getMensaje());
			String mailMessageIni = RESOURCE_MESSAGES.getString("mail.envio.textoInicio");
			String mailMessageFin = RESOURCE_MESSAGES.getString("mail.envio.textoFin"); 
			String mailMessageToSend = mailMessageIni + mailMessage + mailMessageFin;
			
			mensaje.setHtmlContents(mailMessageToSend);
			mensaje.setContentType("text/html");
			
			if(mensaje.getAttachments()!=null)
				logger.info("Se va a enviar el email con "+mensaje.getAttachments().size()+ " documentos adjuntos");
			
			envioMail.sendEmail(mensaje); 
			logger.debug("Se ha enviado el mail correctamente");
		}catch(Exception e){
			logger.warn(STRING_ERROR_ENVIAR_EMAIL,e);
			logger.error(STRING_ERROR_ENVIAR_EMAIL,e);
			return false;
		}
		logger.debug("Fin envioMail");
		return true;
	}
	
	/**
	 * Envio mail alertas.
	 *
	 * @param correoElectronicoBean el correo electronico bean
	 * @param direcciones el direcciones
	 * @return verdadero, si tiene exito
	 */
	public static boolean envioMailAlertas(CorreoElectronicoBean correoElectronicoBean,InternetAddress[] direcciones){
    	
		logger.debug("envioMail");
		
		try{
			
			logger.debug("Vamos a coger las propiedades del ApplicationResources.");
			
			
			MailMessageVO mensaje = new MailMessageVO();
			
			mensaje.setFrom(correoElectronicoBean.getDe());
			mensaje.setTo(correoElectronicoBean.getPara());			
			mensaje.setBcc(direcciones);
			mensaje.setSubject(correoElectronicoBean.getAsunto());
			mensaje.setHtmlContents(correoElectronicoBean.getMensaje());
			mensaje.setContentType("text/html");
			
			EnvioMails.envioMail(correoElectronicoBean);
			logger.debug("Se ha enviado el mail correctamente");
		}catch(Exception e){
			logger.debug(STRING_ERROR_ENVIAR_EMAIL+e);
			logger.error(STRING_ERROR_ENVIAR_EMAIL,e);
			return false;
		}
		logger.debug("Fin envioMail");
		return true;
	}

	/**
	 * Envio mail.
	 *
	 * @param correoElectronicoBean el correo electronico bean
	 * @return verdadero, si tiene exito
	 */
	public static boolean envioMail(CorreoElectronicoBean correoElectronicoBean) {

		try {
			JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
			properties = (Properties) ApplicationContextProvider.getInstance().getBean("propertiesBean");
			// Parametros del configuracion del servidor correo
			mailSender.setHost(properties.getProperty("mail.server"));
			if (!"".equals(properties.getProperty(STRING_MAIL_USERNAME))
					&& !"".equals(properties.getProperty(STRING_MAIL_PASS))) {
				mailSender
						.setUsername(properties.getProperty(STRING_MAIL_USERNAME));
				mailSender
						.setPassword(properties.getProperty(STRING_MAIL_PASS));
			}
			mailSender.setPort(Integer.parseInt(properties.getProperty("mail.port")));
			mailSender.setProtocol(properties.getProperty("mail.protocol"));

			// plantilla para el envío de email
			MimeMessage message = mailSender.createMimeMessage();
			// el flag a true indica que va a ser multipart
			MimeMessageHelper helper = new MimeMessageHelper(message, true,
					"ISO-8859-1");

			String mailMessage = IpsUtils.replaceIntros(correoElectronicoBean
					.getMensaje());
			String mailMessageIni = RESOURCE_MESSAGES
					.getString("mail.envio.textoInicio");
			String mailMessageFin = RESOURCE_MESSAGES
					.getString("mail.envio.textoFin");
			String mailMessageToSend = mailMessageIni + mailMessage
					+ mailMessageFin;

			helper.setFrom(correoElectronicoBean.getDe());
			helper.setTo(correoElectronicoBean.getPara());
			
			if(correoElectronicoBean.getAdjuntos()!=null && correoElectronicoBean.getAdjuntos().size()>0){
				
			}
			if(correoElectronicoBean.getAdjuntosFile()!=null && correoElectronicoBean.getAdjuntosFile().size()>0){
				for (Iterator<File> itAdjunto = correoElectronicoBean.getAdjuntosFile().iterator(); itAdjunto
						.hasNext();) {
					File adjunto = (File) itAdjunto.next();
					helper.addAttachment(adjunto.getName(), adjunto);
				}
			}
			if(correoElectronicoBean.getBcc()!=null && correoElectronicoBean.getBcc().length>0){
				helper.setBcc(correoElectronicoBean.getBcc());
			}
			helper.setSubject(correoElectronicoBean.getAsunto());
			helper.setText(mailMessageToSend, true);

			Properties props = new Properties();
			props.put("mail.transport.protocol", mailSender.getProtocol());
			props.put("mail.smtp.host", mailSender.getHost());

			Session sess = null;
			if (!"".equals(properties.getProperty(STRING_MAIL_USERNAME)) && !"".equals(properties.getProperty(STRING_MAIL_PASS))) {
				props.put("mail.smtp.auth", "true");
				Authenticator auth = new SmtpAuthenticator(mailSender.getUsername(), mailSender.getPassword());
				sess = Session.getInstance(props, auth);
			} else {
				props.put("mail.smtp.auth", "false");
				sess = Session.getInstance(props, null);
			}
			mailSender.setSession(sess);

			// el envío
			mailSender.send(message);
		} catch (Exception e) {
			logger.warn(STRING_ERROR_ENVIAR_EMAIL + e);
			logger.error(STRING_ERROR_ENVIAR_EMAIL , e);
			return false;
		}
		return true;
	}
	
}

class SmtpAuthenticator extends Authenticator {
	private String username;
	private String password;

	public SmtpAuthenticator(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(username, password);
	}
}