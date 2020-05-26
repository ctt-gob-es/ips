/**
 * GetListaEntidadesService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.map.ipsg.clienteWS.pasarelapago.entidades;

/**
 * El Interface GetListaEntidadesService.
 */
public interface GetListaEntidadesService extends javax.xml.rpc.Service {
    
    /**
     * Obtiene el entidades address.
     *
     * @return el entidades address
     */
    public java.lang.String getEntidadesAddress();

    /**
     * Obtiene el entidades.
     *
     * @return el entidades
     * @throws ServiceException el service exception
     */
    public es.map.ipsg.clienteWS.pasarelapago.entidades.GetListaEntidades getEntidades() throws javax.xml.rpc.ServiceException;

    /**
     * Obtiene el entidades.
     *
     * @param portAddress el port address
     * @return el entidades
     * @throws ServiceException el service exception
     */
    public es.map.ipsg.clienteWS.pasarelapago.entidades.GetListaEntidades getEntidades(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
