/**
 * RegistrarElectronicosService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.map.ipsc.clienteWS.registrarRec3;

/**
 * El Interface RegistrarElectronicosService.
 */
public interface RegistrarElectronicosService extends java.rmi.Remote {
    
    /**
     * Registrar.
     *
     * @param autenticacion el autenticacion
     * @param registro el registro
     * @return el es.map.ipsc.cliente W s.registro rec 3 .type. justificante type
     * @throws RemoteException el remote exception
     */
    public es.map.ipsc.clienteWS.registroRec3.type.JustificanteType registrar(es.map.ipsc.clienteWS.registroRec3.type.AutenticacionType autenticacion, 
    		es.map.ipsc.clienteWS.registrarRec3.RegistroType registro) throws java.rmi.RemoteException;
}
