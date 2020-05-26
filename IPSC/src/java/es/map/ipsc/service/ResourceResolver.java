package es.map.ipsc.service;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.apache.log4j.Logger;
import org.apache.xml.security.signature.XMLSignatureInput;
import org.apache.xml.security.utils.resolver.ResourceResolverException;
import org.apache.xml.security.utils.resolver.ResourceResolverSpi;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * El Class ResourceResolver.
 *
 * @author jdsantaella
 */
public class ResourceResolver extends ResourceResolverSpi {

    /** el resolver. */
    private static ResourceResolver resolver = null;
    
    /** La constante logger. */
    private static final Logger logger = Logger.getLogger( ResourceResolver.class);

    /**
     * Obtiene la instancia unica de ResourceResolver.
     *
     * @return instancia unica de ResourceResolver
     */
    public static synchronized ResourceResolverSpi getInstance() {

        if (resolver == null) {
            resolver = new ResourceResolver();
        }
        return resolver;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.xml.security.utils.resolver.ResourceResolverSpi#engineResolve(org.w3c.dom.Attr, java.lang.String)
     */
    public XMLSignatureInput engineResolve(Attr uri, String BaseURI) throws ResourceResolverException {

        Document doc = uri.getOwnerDocument();
        String uriNodeValue = uri.getNodeValue();

        try {

            XPath xpath = XPathFactory.newInstance().newXPath();
            XPathExpression exp =
                    xpath.compile(String.format("//*[@Id='%s']", new Object[] { uriNodeValue.substring(1) }));
            Element element = (Element) exp.evaluate(doc, XPathConstants.NODE);
            XMLSignatureInput result = new XMLSignatureInput(element);
            result.setMIMEType("text/xml");
            return result;

        } catch (Exception ex) {
        	logger.error("Error al resolver la referencia", ex);

            throw new RuntimeException("Error al resolver la referencia " + uriNodeValue);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.xml.security.utils.resolver.ResourceResolverSpi#engineCanResolve(org.w3c.dom.Attr,
     * java.lang.String)
     */
    public boolean engineCanResolve(Attr uri, String BaseURI) {

        if (uri == null) {
            return false;
        }
        String uriNodeValue = uri.getNodeValue();
        return uriNodeValue.startsWith("#");
    }
}
