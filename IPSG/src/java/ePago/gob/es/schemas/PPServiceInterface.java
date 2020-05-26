/**
 * PPServiceInterface.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ePago.gob.es.schemas;

/**
 * El Interface PPServiceInterface.
 */
public interface PPServiceInterface extends java.rmi.Remote {
    
    /**
     * Hacer pago.
     *
     * @param datosPagoIn el datos pago in
     * @param idOrganismo el id organismo
     * @return el e pago.gob.es.schemas. default datos pago out
     * @throws RemoteException el remote exception
     */
    public ePago.gob.es.schemas.DefaultDatosPagoOut hacerPago(ePago.gob.es.schemas.DefaultDatosPagoIn datosPagoIn, int idOrganismo) throws java.rmi.RemoteException;
    
    /**
     * Hacer pago Y registro.
     *
     * @param datosPagoIn el datos pago in
     * @param almacen el almacen
     * @param idOrganismo el id organismo
     * @return el e pago.gob.es.schemas. default datos pago registro out
     * @throws RemoteException el remote exception
     */
    public ePago.gob.es.schemas.DefaultDatosPagoRegistroOut hacerPagoYRegistro(ePago.gob.es.schemas.DefaultDatosPagoIn datosPagoIn, ePago.gob.es.schemas.MapItem[] almacen, int idOrganismo) throws java.rmi.RemoteException;
    
    /**
     * Verificar NRC.
     *
     * @param datosNRCIn el datos NRC in
     * @param idOrganismo el id organismo
     * @return el e pago.gob.es.schemas. default datos NRC out
     * @throws RemoteException el remote exception
     */
    public ePago.gob.es.schemas.DefaultDatosNRCOut verificarNRC(ePago.gob.es.schemas.DefaultDatosNRCIn datosNRCIn, int idOrganismo) throws java.rmi.RemoteException;
    
    /**
     * Obtiene el justificante.
     *
     * @param modelo el modelo
     * @param codigoTasa el codigo tasa
     * @param idOrganismo el id organismo
     * @return el justificante
     * @throws RemoteException el remote exception
     */
    public java.lang.String getJustificante(java.lang.String modelo, java.lang.String codigoTasa, int idOrganismo) throws java.rmi.RemoteException;
    
    /**
     * Consultar pago.
     *
     * @param datosPagoIn el datos pago in
     * @param idOrganismo el id organismo
     * @return el e pago.gob.es.schemas. default datos pago out
     * @throws RemoteException el remote exception
     */
    public ePago.gob.es.schemas.DefaultDatosPagoOut consultarPago(ePago.gob.es.schemas.DefaultDatosPagoIn datosPagoIn, int idOrganismo) throws java.rmi.RemoteException;
}
