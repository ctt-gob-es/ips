/**
 * PPServiceInterfaceService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ePago.gob.es.schemas;

/**
 * El Interface PPServiceInterfaceService.
 */
public interface PPServiceInterfaceService extends javax.xml.rpc.Service {
    
    /**
     * Obtiene el PP service interface soap 11 address.
     *
     * @return el PP service interface soap 11 address
     */
    public java.lang.String getPPServiceInterfaceSoap11Address();

    /**
     * Obtiene el PP service interface soap 11.
     *
     * @return el PP service interface soap 11
     * @throws ServiceException el service exception
     */
    public ePago.gob.es.schemas.PPServiceInterface getPPServiceInterfaceSoap11() throws javax.xml.rpc.ServiceException;

    /**
     * Obtiene el PP service interface soap 11.
     *
     * @param portAddress el port address
     * @return el PP service interface soap 11
     * @throws ServiceException el service exception
     */
    public ePago.gob.es.schemas.PPServiceInterface getPPServiceInterfaceSoap11(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
