/**
 * ServicioIntegracion.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.map.pasarelapago.service;

/**
 * El Interface ServicioIntegracion.
 */
public interface ServicioIntegracion extends java.rmi.Remote {
    
    /**
     * Consultar pago.
     *
     * @param numeroJustificante el numero justificante
     * @param documentoObligado el documento obligado
     * @param idTramitacion el id tramitacion
     * @param organismo el organismo
     * @return el es.map.pasarelapago.service. datos pago IM
     * @throws RemoteException el remote exception
     * @throws IntegracionMixtaGenericException el integracion mixta generic exception
     * @throws IntegracionMixtaNoPagadoException el integracion mixta no pagado exception
     * @throws IntegracionMixtaIncidenciaPagoException el integracion mixta incidencia pago exception
     * @throws IntegracionMixtaPagoNotFoundException el integracion mixta pago not found exception
     */
    public es.map.pasarelapago.service.DatosPagoIM consultarPago(java.lang.String numeroJustificante, java.lang.String documentoObligado, java.lang.String idTramitacion, java.lang.String organismo) throws java.rmi.RemoteException, es.map.pasarelapago.service.IntegracionMixtaGenericException, es.map.pasarelapago.service.IntegracionMixtaNoPagadoException, es.map.pasarelapago.service.IntegracionMixtaIncidenciaPagoException, es.map.pasarelapago.service.IntegracionMixtaPagoNotFoundException;
    
    /**
     * Crear pago pendiente.
     *
     * @param pagoPendiente el pago pendiente
     * @param organismo el organismo
     * @return el java.lang. string
     * @throws RemoteException el remote exception
     * @throws IntegracionMixtaIdTramitacionRepetidoException el integracion mixta id tramitacion repetido exception
     * @throws IntegracionMixtaGenericException el integracion mixta generic exception
     * @throws IntegracionMixtaNifJustificanteRepetidoException el integracion mixta nif justificante repetido exception
     */
    public java.lang.String crearPagoPendiente(es.map.pasarelapago.service.PagoPendiente pagoPendiente, java.lang.String organismo) throws java.rmi.RemoteException, es.map.pasarelapago.service.IntegracionMixtaIdTramitacionRepetidoException, es.map.pasarelapago.service.IntegracionMixtaGenericException, es.map.pasarelapago.service.IntegracionMixtaNifJustificanteRepetidoException;
}
