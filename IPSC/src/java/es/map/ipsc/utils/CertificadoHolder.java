package es.map.ipsc.utils;

import java.security.cert.X509Certificate;

/**
 * El Class CertificadoHolder.
 */
public class CertificadoHolder {
	
	/** el certificado TL. */
	private static ThreadLocal<X509Certificate> certificadoTL = new ThreadLocal<X509Certificate>();
	
	/**
	 * Establece el certificado.
	 *
	 * @param certificado el nuevo certificado
	 */
	public static void setCertificado(X509Certificate certificado) {
		certificadoTL.set(certificado);
	}
	
	/**
	 * Obtiene el certificado.
	 *
	 * @return el certificado
	 */
	public static X509Certificate getCertificado() {
		return certificadoTL.get();
	}
}
