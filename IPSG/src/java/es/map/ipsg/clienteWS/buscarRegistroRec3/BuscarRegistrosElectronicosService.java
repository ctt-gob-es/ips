/**
 * BuscarRegistrosElectronicosService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.map.ipsg.clienteWS.buscarRegistroRec3;

/**
 * El Interface BuscarRegistrosElectronicosService.
 */
public interface BuscarRegistrosElectronicosService extends javax.xml.rpc.Service {
    
    /**
     * Obtiene el buscar registros electronicos service address.
     *
     * @return el buscar registros electronicos service address
     */
    public java.lang.String getBuscarRegistrosElectronicosServiceAddress();

    /**
     * Obtiene el buscar registros electronicos service.
     *
     * @return el buscar registros electronicos service
     * @throws ServiceException el service exception
     */
    public es.map.ipsg.clienteWS.buscarRegistroRec3.BuscarRegistrosElectronicos getBuscarRegistrosElectronicosService() throws javax.xml.rpc.ServiceException;

    /**
     * Obtiene el buscar registros electronicos service.
     *
     * @param portAddress el port address
     * @return el buscar registros electronicos service
     * @throws ServiceException el service exception
     */
    public es.map.ipsg.clienteWS.buscarRegistroRec3.BuscarRegistrosElectronicos getBuscarRegistrosElectronicosService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
