/**
 * RegistrarService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.map.ipsc.clienteWS.registrarRec3;

/**
 * El Interface RegistrarService.
 */
public interface RegistrarService extends javax.xml.rpc.Service {
    
    /**
     * Obtiene el registrar service address.
     *
     * @return el registrar service address
     */
    public java.lang.String getRegistrarServiceAddress();

    /**
     * Obtiene el registrar service.
     *
     * @return el registrar service
     * @throws ServiceException el service exception
     */
    public es.map.ipsc.clienteWS.registrarRec3.RegistrarElectronicosService getRegistrarService() throws javax.xml.rpc.ServiceException;

    /**
     * Obtiene el registrar service.
     *
     * @param portAddress el port address
     * @return el registrar service
     * @throws ServiceException el service exception
     */
    public es.map.ipsc.clienteWS.registrarRec3.RegistrarElectronicosService getRegistrarService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
