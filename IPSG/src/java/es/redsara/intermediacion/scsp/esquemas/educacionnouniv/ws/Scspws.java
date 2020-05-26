/**
 * Scspws.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.redsara.intermediacion.scsp.esquemas.educacionnouniv.ws;

/**
 * El Interface Scspws.
 */
public interface Scspws extends java.rmi.Remote {
    
    /**
     * Peticion sincrona.
     *
     * @param peticionSincrona el peticion sincrona
     * @return el es.redsara.intermediacion.scsp.esquemas.educacionnouniv.ws.respuesta. respuesta
     * @throws RemoteException el remote exception
     */
    public es.redsara.intermediacion.scsp.esquemas.educacionnouniv.ws.respuesta.Respuesta peticionSincrona(es.redsara.intermediacion.scsp.esquemas.ws.peticion.PeticionSincrona peticionSincrona) throws java.rmi.RemoteException;
    
    /**
     * Peticion asincrona.
     *
     * @param peticionAsincrona el peticion asincrona
     * @return el es.redsara.intermediacion.scsp.esquemas.ws.confirmacion peticion. confirmacion peticion
     * @throws RemoteException el remote exception
     */
    public es.redsara.intermediacion.scsp.esquemas.ws.confirmacionPeticion.ConfirmacionPeticion peticionAsincrona(es.redsara.intermediacion.scsp.esquemas.ws.peticion.PeticionAsincrona peticionAsincrona) throws java.rmi.RemoteException;
    
    /**
     * Solicitud respuesta.
     *
     * @param solicitudRespuesta el solicitud respuesta
     * @return el es.redsara.intermediacion.scsp.esquemas.educacionnouniv.ws.respuesta. respuesta
     * @throws RemoteException el remote exception
     */
    public es.redsara.intermediacion.scsp.esquemas.educacionnouniv.ws.respuesta.Respuesta solicitudRespuesta(es.redsara.intermediacion.scsp.esquemas.ws.solicitudRespuesta.SolicitudRespuesta solicitudRespuesta) throws java.rmi.RemoteException;
}
