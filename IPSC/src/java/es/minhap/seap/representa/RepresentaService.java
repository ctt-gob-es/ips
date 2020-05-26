/**
 * RepresentaService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.minhap.seap.representa;

/**
 * El Interface RepresentaService.
 */
public interface RepresentaService extends javax.xml.rpc.Service {
    
    /**
     * Obtiene el representa port address.
     *
     * @return el representa port address
     */
    public java.lang.String getRepresentaPortAddress();

    /**
     * Obtiene el representa port.
     *
     * @return el representa port
     * @throws ServiceException el service exception
     */
    public es.minhap.seap.representa.Representa getRepresentaPort() throws javax.xml.rpc.ServiceException;

    /**
     * Obtiene el representa port.
     *
     * @param portAddress el port address
     * @return el representa port
     * @throws ServiceException el service exception
     */
    public es.minhap.seap.representa.Representa getRepresentaPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
