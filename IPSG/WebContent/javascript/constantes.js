
/*******************************************************************************
 * Ruta al directorio del miniapplet.										   *
 * Las rutas absolutas deben comenzar por "file:///", "http://" o "https://"   *
 * por ejemplo, "file:///C:/Instalador", "http://www.mpr.es/instalador",...).  *
 * Se debe usar siempre el separador "/", nunca "\".						   *
 *******************************************************************************/

var base = 'https://'+window.location.host+'/IPSG/sign';

/********************************************************************************
 * Algoritmo de firma. Puede ser 'SHA1withRSA', 'MD5withRSA' o, salvo que sea	*
 * firma XML, MD2withRSA. También se puede utilizar un algoritmo SHA-2			*
 * ('SHA512withRSA', 'SHA384withRSA' o 'SHA256withRSA'), teniendo en cuenta que	*
 * el usuario sólo podrá ejecutar esta firma si cuenta con Java 6u30 o superior *
 * o Java 7u2 o superior. 														*
 *******************************************************************************/
var signatureAlgorithm = 'SHA1withRSA'; // Valor por defecto

/*******************************************************************************
 * Formato de firma. Puede ser 'CMS', 'XADES', 'XMLDSIGN' o 'NONE'.            *
 * Por defecto: CADES.										 				   *
 ******************************************************************************/
// Registro
var signatureFormat = 'XAdES';

// Pago
var signatureFormatPago = 'CADES';
