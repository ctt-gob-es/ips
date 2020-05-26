/**
 * ServicioIntegracionService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.map.pasarelapago.service;

/**
 * El Interface ServicioIntegracionService.
 */
public interface ServicioIntegracionService extends javax.xml.rpc.Service {
    
    /**
     * Obtiene el integracion mixta address.
     *
     * @return el integracion mixta address
     */
    public java.lang.String getIntegracionMixtaAddress();

    /**
     * Obtiene el integracion mixta.
     *
     * @return el integracion mixta
     * @throws ServiceException el service exception
     */
    public es.map.pasarelapago.service.ServicioIntegracion getIntegracionMixta() throws javax.xml.rpc.ServiceException;

    /**
     * Obtiene el integracion mixta.
     *
     * @param portAddress el port address
     * @return el integracion mixta
     * @throws ServiceException el service exception
     */
    public es.map.pasarelapago.service.ServicioIntegracion getIntegracionMixta(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
