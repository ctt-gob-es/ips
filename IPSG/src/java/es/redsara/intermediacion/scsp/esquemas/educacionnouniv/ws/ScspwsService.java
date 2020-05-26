/**
 * ScspwsService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.redsara.intermediacion.scsp.esquemas.educacionnouniv.ws;

/**
 * El Interface ScspwsService.
 */
public interface ScspwsService extends javax.xml.rpc.Service {
    
    /**
     * Obtiene el scspws soap 11 address.
     *
     * @return el scspws soap 11 address
     */
    public java.lang.String getscspwsSoap11Address();

    /**
     * Obtiene el scspws soap 11.
     *
     * @return el scspws soap 11
     * @throws ServiceException el service exception
     */
    public es.redsara.intermediacion.scsp.esquemas.educacionnouniv.ws.Scspws getscspwsSoap11() throws javax.xml.rpc.ServiceException;

    /**
     * Obtiene el scspws soap 11.
     *
     * @param portAddress el port address
     * @return el scspws soap 11
     * @throws ServiceException el service exception
     */
    public es.redsara.intermediacion.scsp.esquemas.educacionnouniv.ws.Scspws getscspwsSoap11(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
