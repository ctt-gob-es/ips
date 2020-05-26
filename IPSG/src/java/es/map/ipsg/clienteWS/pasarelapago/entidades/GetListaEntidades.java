/**
 * GetListaEntidades.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.map.ipsg.clienteWS.pasarelapago.entidades;

/**
 * El Interface GetListaEntidades.
 */
public interface GetListaEntidades extends java.rmi.Remote {
    
    /**
     * Peticion.
     *
     * @return el es.map.ipsg.cliente W s.pasarelapago.entidades. respuesta obtiene el lista entidades
     * @throws RemoteException el remote exception
     */
    public es.map.ipsg.clienteWS.pasarelapago.entidades.RespuestaGetListaEntidades peticion() throws java.rmi.RemoteException;
    
    /**
     * Peticion A cuenta.
     *
     * @return el es.map.ipsg.cliente W s.pasarelapago.entidades. respuesta obtiene el lista entidades
     * @throws RemoteException el remote exception
     */
    public es.map.ipsg.clienteWS.pasarelapago.entidades.RespuestaGetListaEntidades peticionACuenta() throws java.rmi.RemoteException;
    
    /**
     * Peticion con tarjeta.
     *
     * @return el es.map.ipsg.cliente W s.pasarelapago.entidades. respuesta obtiene el lista entidades
     * @throws RemoteException el remote exception
     */
    public es.map.ipsg.clienteWS.pasarelapago.entidades.RespuestaGetListaEntidades peticionConTarjeta() throws java.rmi.RemoteException;
}
