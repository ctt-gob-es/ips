package es.map.ipsg.util;

import java.io.File;
import java.io.StringWriter;
import java.net.URL;

import org.apache.log4j.Logger;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.ParseErrorException;

import es.map.ipsg.res.ResourceLocator;


/**
 * El Class VelocityGenerator.
 */
public class VelocityGenerator {
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(VelocityGenerator.class);

	/**
	 * Generate content.
	 *
	 * @param templateName el template name
	 * @param model el model
	 * @return el string
	 */
	public static String generateContent(String templateName, Object model) {

		org.apache.velocity.app.VelocityEngine vEngine = null;

		URL tempURL = ResourceLocator.getInstance().getVelocityTemplate(templateName + ".vm");
		File template = new File(tempURL.getFile());

		Template veltemp = loadTemplate(template, vEngine);

		VelocityContext context = new VelocityContext();

		context.put("model", model);

		String contentGen = mergeAndWrite(veltemp, context);
		
		return contentGen;
	}

	/**
	 * Load template.
	 *
	 * @param template el template
	 * @param vEngine el v engine
	 * @return el org.apache.velocity. template
	 */
	private static org.apache.velocity.Template loadTemplate(File template, VelocityEngine vEngine) {
		org.apache.velocity.Template veltemp;
		try {
			vEngine = new org.apache.velocity.app.VelocityEngine();
			String dir = template.getParent();
			vEngine.setProperty("file.resource.loader.path", dir);
			vEngine.init();
			veltemp = vEngine.getTemplate(template.getName());
		} catch (ParseErrorException x) {
			logger.error("Invalid Velocity Template. ",x);
			throw new RuntimeException("Invalid Velocity Template. " + x.getMessage(), x);
		} catch (Exception x) {
			logger.error("Could not load template: ",x);
			throw new RuntimeException("Could not load template: " + template.getAbsoluteFile(), x);
		}
		return veltemp;
	}

	/**
	 * Merge and write.
	 *
	 * @param veltemp el veltemp
	 * @param context el context
	 * @return el string
	 */
	private static String mergeAndWrite(org.apache.velocity.Template veltemp, VelocityContext context) {
		try {

			StringWriter writer = new StringWriter();
			veltemp.merge(context, writer);
			
			return writer.toString();
		} catch (Exception x) {
			logger.error("could not write content String. ",x);
			throw new RuntimeException("could not write content String.", x);
		}
	}		
	
}



