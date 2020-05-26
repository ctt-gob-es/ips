/**
 * BuscarRegistrosElectronicos.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.map.ipsc.clienteWS.buscarRegistrosRec3;

/**
 * El Interface BuscarRegistrosElectronicos.
 */
public interface BuscarRegistrosElectronicos extends java.rmi.Remote {
    
    /**
     * Buscar registro.
     *
     * @param autenticacion el autenticacion
     * @param criteriosBusqueda el criterios busqueda
     * @return el es.map.ipsc.cliente W s.registro rec 3 .type. justificante type[]
     * @throws RemoteException el remote exception
     */
    public es.map.ipsc.clienteWS.registroRec3.type.JustificanteType[] buscarRegistro(es.map.ipsc.clienteWS.registroRec3.type.AutenticacionType autenticacion, es.map.ipsc.clienteWS.buscarRegistrosRec3.CriteriosBusquedaType criteriosBusqueda) throws java.rmi.RemoteException;
}
