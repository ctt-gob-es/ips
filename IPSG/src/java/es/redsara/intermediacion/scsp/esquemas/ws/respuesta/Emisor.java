/**
 * Emisor.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.redsara.intermediacion.scsp.esquemas.ws.respuesta;

/**
 * El Class Emisor.
 */
public class Emisor  implements java.io.Serializable {
    
    /** el nif emisor. */
    private java.lang.String nifEmisor;

    /** el nombre emisor. */
    private java.lang.String nombreEmisor;

    /**
     * Crea una nueva emisor.
     */
    public Emisor() {
    }

    /**
     * Crea una nueva emisor.
     *
     * @param nifEmisor el nif emisor
     * @param nombreEmisor el nombre emisor
     */
    public Emisor(
           java.lang.String nifEmisor,
           java.lang.String nombreEmisor) {
           this.nifEmisor = nifEmisor;
           this.nombreEmisor = nombreEmisor;
    }


    /**
     * Gets the nifEmisor value for this Emisor.
     * 
     * @return nifEmisor
     */
    public java.lang.String getNifEmisor() {
        return nifEmisor;
    }


    /**
     * Sets the nifEmisor value for this Emisor.
     *
     * @param nifEmisor el nuevo nif emisor
     */
    public void setNifEmisor(java.lang.String nifEmisor) {
        this.nifEmisor = nifEmisor;
    }


    /**
     * Gets the nombreEmisor value for this Emisor.
     * 
     * @return nombreEmisor
     */
    public java.lang.String getNombreEmisor() {
        return nombreEmisor;
    }


    /**
     * Sets the nombreEmisor value for this Emisor.
     *
     * @param nombreEmisor el nuevo nombre emisor
     */
    public void setNombreEmisor(java.lang.String nombreEmisor) {
        this.nombreEmisor = nombreEmisor;
    }

    /** el equals calc. */
    private java.lang.Object __equalsCalc = null;
    
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Emisor)) return false;
        Emisor other = (Emisor) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.nifEmisor==null && other.getNifEmisor()==null) || 
             (this.nifEmisor!=null &&
              this.nifEmisor.equals(other.getNifEmisor()))) &&
            ((this.nombreEmisor==null && other.getNombreEmisor()==null) || 
             (this.nombreEmisor!=null &&
              this.nombreEmisor.equals(other.getNombreEmisor())));
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
        if (getNifEmisor() != null) {
            _hashCode += getNifEmisor().hashCode();
        }
        if (getNombreEmisor() != null) {
            _hashCode += getNombreEmisor().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    /** el type desc. */
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Emisor.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/ws/respuesta", ">Emisor"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nifEmisor");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/ws/respuesta", "NifEmisor"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombreEmisor");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/ws/respuesta", "NombreEmisor"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
