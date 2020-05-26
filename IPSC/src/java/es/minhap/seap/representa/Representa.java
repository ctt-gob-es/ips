/**
 * Representa.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.minhap.seap.representa;

/**
 * El Interface Representa.
 */
public interface Representa extends java.rmi.Remote {
    
    /**
     * Comprobar colectivos.
     *
     * @param representaRequest el representa request
     * @return el es.minhap.seap.representa. representa response
     * @throws RemoteException el remote exception
     */
    public es.minhap.seap.representa.RepresentaResponse comprobarColectivos(es.minhap.seap.representa.RepresentaRequest representaRequest) throws java.rmi.RemoteException;
}
