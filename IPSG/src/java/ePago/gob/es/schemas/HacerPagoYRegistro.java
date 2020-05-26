/**
 * HacerPagoYRegistro.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ePago.gob.es.schemas;

/**
 * El Class HacerPagoYRegistro.
 */
public class HacerPagoYRegistro  implements java.io.Serializable {
    
    /** el datos pago in. */
    private ePago.gob.es.schemas.DefaultDatosPagoIn datosPagoIn;

    /** el almacen. */
    private ePago.gob.es.schemas.MapItem[] almacen;

    /** el id organismo. */
    private int idOrganismo;

    /**
     * Crea una nueva hacer pago Y registro.
     */
    public HacerPagoYRegistro() {
    }

    /**
     * Crea una nueva hacer pago Y registro.
     *
     * @param datosPagoIn el datos pago in
     * @param almacen el almacen
     * @param idOrganismo el id organismo
     */
    public HacerPagoYRegistro(
           ePago.gob.es.schemas.DefaultDatosPagoIn datosPagoIn,
           ePago.gob.es.schemas.MapItem[] almacen,
           int idOrganismo) {
           this.datosPagoIn = datosPagoIn;
           this.almacen = almacen;
           this.idOrganismo = idOrganismo;
    }


    /**
     * Gets the datosPagoIn value for this HacerPagoYRegistro.
     * 
     * @return datosPagoIn
     */
    public ePago.gob.es.schemas.DefaultDatosPagoIn getDatosPagoIn() {
        return datosPagoIn;
    }


    /**
     * Sets the datosPagoIn value for this HacerPagoYRegistro.
     *
     * @param datosPagoIn el nuevo datos pago in
     */
    public void setDatosPagoIn(ePago.gob.es.schemas.DefaultDatosPagoIn datosPagoIn) {
        this.datosPagoIn = datosPagoIn;
    }


    /**
     * Gets the almacen value for this HacerPagoYRegistro.
     * 
     * @return almacen
     */
    public ePago.gob.es.schemas.MapItem[] getAlmacen() {
        return almacen;
    }


    /**
     * Sets the almacen value for this HacerPagoYRegistro.
     *
     * @param almacen el nuevo almacen
     */
    public void setAlmacen(ePago.gob.es.schemas.MapItem[] almacen) {
        this.almacen = almacen;
    }


    /**
     * Gets the idOrganismo value for this HacerPagoYRegistro.
     * 
     * @return idOrganismo
     */
    public int getIdOrganismo() {
        return idOrganismo;
    }


    /**
     * Sets the idOrganismo value for this HacerPagoYRegistro.
     *
     * @param idOrganismo el nuevo id organismo
     */
    public void setIdOrganismo(int idOrganismo) {
        this.idOrganismo = idOrganismo;
    }

    /** el equals calc. */
    private java.lang.Object __equalsCalc = null;
    
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof HacerPagoYRegistro)) return false;
        HacerPagoYRegistro other = (HacerPagoYRegistro) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.datosPagoIn==null && other.getDatosPagoIn()==null) || 
             (this.datosPagoIn!=null &&
              this.datosPagoIn.equals(other.getDatosPagoIn()))) &&
            ((this.almacen==null && other.getAlmacen()==null) || 
             (this.almacen!=null &&
              java.util.Arrays.equals(this.almacen, other.getAlmacen()))) &&
            this.idOrganismo == other.getIdOrganismo();
        __equalsCalc = null;
        return _equals;
    }

    /** el hash code calc. */
    private boolean __hashCodeCalc = false;
    
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getDatosPagoIn() != null) {
            _hashCode += getDatosPagoIn().hashCode();
        }
        if (getAlmacen() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAlmacen());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAlmacen(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        _hashCode += getIdOrganismo();
        __hashCodeCalc = false;
        return _hashCode;
    }

    /** el type desc. */
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(HacerPagoYRegistro.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", ">hacerPagoYRegistro"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("datosPagoIn");
        elemField.setXmlName(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "datosPagoIn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "DefaultDatosPagoIn"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("almacen");
        elemField.setXmlName(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "almacen"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "mapItem"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idOrganismo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "idOrganismo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object.
     *
     * @return el type desc
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer.
     *
     * @param mechType el mech type
     * @param _javaType el java type
     * @param _xmlType el xml type
     * @return el serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer.
     *
     * @param mechType el mech type
     * @param _javaType el java type
     * @param _xmlType el xml type
     * @return el deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
