package es.map.ipsc.utils;

import java.util.Properties;
import java.util.ResourceBundle;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.map.j2ee.mail.MailManager;
import com.map.j2ee.mail.MailMessageVO;

import es.map.ips.common.spring.ApplicationContextProvider;
import es.map.ipsc.bean.CorreoElectronicoBean;

/**
 * El Class EnvioMails.
 */
public class EnvioMails {

	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(EnvioMails.class);
	
	/** el properties. */
	private static Properties properties;
	
	/** Nombre del archivo de propiedades. */
	private static final String BUNDLE_MESSAGES = "MessageResources";
	
	/** La constante STRING_ERROR_ENVIAR_EMAIL. */
	private static final String STRING_ERROR_ENVIAR_EMAIL = "Error no se ha podido enviar el email ";
	
	/** La constante STRING_MAIL_USERNAME. */
	private static final String STRING_MAIL_USERNAME = "mail.username";
	
	/** La constante STRING_MAIL_PASS. */
	private static final String STRING_MAIL_PASS = "mail.password";

	

	/** El contenedor de recursos que se usara. */
	private static final ResourceBundle RESOURCE_MESSAGES = ResourceBundle
			.getBundle(BUNDLE_MESSAGES);

	

	/**
	 * Envio mail deprecado.
	 *
	 * @param correoElectronicoBean el correo electronico bean
	 * @return verdadero, si tiene exito
	 */
	public static boolean envioMailDeprecado(CorreoElectronicoBean correoElectronicoBean) {

		logger.debug("envioMail");

		MailManager envioMail = null;

		try {

			envioMail = (MailManager) ApplicationContextProvider.getInstance()
					.getBean("templateMailManager");

			if (envioMail != null) {
				MailMessageVO mensaje = new MailMessageVO();

				String mailMessage = IpsUtils
						.replaceIntros(correoElectronicoBean.getMensaje());
				String mailMessageIni = RESOURCE_MESSAGES
						.getString("mail.envio.textoInicio");
				String mailMessageFin = RESOURCE_MESSAGES
						.getString("mail.envio.textoFin");
				String mailMessageToSend = mailMessageIni + mailMessage
						+ mailMessageFin;

				mensaje.setFrom(correoElectronicoBean.getDe());
				mensaje.setTo(correoElectronicoBean.getPara());
				mensaje.setSubject(correoElectronicoBean.getAsunto());
				mensaje.setHtmlContents(mailMessageToSend);
				mensaje.setContentType("text/html");

				envioMail.sendEmail(mensaje);
				logger.debug("Se ha enviado el mail correctamente");
			} else {
				logger.warn(STRING_ERROR_ENVIAR_EMAIL);
				return false;
			}
		} catch (Exception e) {
			logger.warn(STRING_ERROR_ENVIAR_EMAIL, e);
			logger.error("Error no se ha podidio enviar el email",e);
			return false;
		}
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

			// Parametros del configuracion del servidor correo
			properties = (Properties) ApplicationContextProvider.getInstance().getBean("propertiesBean");
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
			String para = correoElectronicoBean.getPara();
			
			if(para.contains(",")){
				helper.setTo(para.split(","));
			}
			else{
				helper.setTo(correoElectronicoBean.getPara());
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
			logger.warn(STRING_ERROR_ENVIAR_EMAIL,e);
			logger.error(STRING_ERROR_ENVIAR_EMAIL,e);
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