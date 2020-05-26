/**
 * CertificadoDatosDiscapacidadRespuestaMovilidad.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.redsara.intermediacion.scsp.esquemas.discapacidad.datosespecificos;

/**
 * El Class CertificadoDatosDiscapacidadRespuestaMovilidad.
 */
public class CertificadoDatosDiscapacidadRespuestaMovilidad  implements java.io.Serializable {
    
    /** el puntuacion. */
    private java.math.BigInteger puntuacion;

    /** el factor. */
    private es.redsara.intermediacion.scsp.esquemas.discapacidad.datosespecificos.Factor factor;

    /**
     * Crea una nueva certificado datos discapacidad respuesta movilidad.
     */
    public CertificadoDatosDiscapacidadRespuestaMovilidad() {
    }

    /**
     * Crea una nueva certificado datos discapacidad respuesta movilidad.
     *
     * @param puntuacion el puntuacion
     * @param factor el factor
     */
    public CertificadoDatosDiscapacidadRespuestaMovilidad(
           java.math.BigInteger puntuacion,
           es.redsara.intermediacion.scsp.esquemas.discapacidad.datosespecificos.Factor factor) {
           this.puntuacion = puntuacion;
           this.factor = factor;
    }


    /**
     * Gets the puntuacion value for this CertificadoDatosDiscapacidadRespuestaMovilidad.
     * 
     * @return puntuacion
     */
    public java.math.BigInteger getPuntuacion() {
        return puntuacion;
    }


    /**
     * Sets the puntuacion value for this CertificadoDatosDiscapacidadRespuestaMovilidad.
     *
     * @param puntuacion el nuevo puntuacion
     */
    public void setPuntuacion(java.math.BigInteger puntuacion) {
        this.puntuacion = puntuacion;
    }


    /**
     * Gets the factor value for this CertificadoDatosDiscapacidadRespuestaMovilidad.
     * 
     * @return factor
     */
    public es.redsara.intermediacion.scsp.esquemas.discapacidad.datosespecificos.Factor getFactor() {
        return factor;
    }


    /**
     * Sets the factor value for this CertificadoDatosDiscapacidadRespuestaMovilidad.
     *
     * @param factor el nuevo factor
     */
    public void setFactor(es.redsara.intermediacion.scsp.esquemas.discapacidad.datosespecificos.Factor factor) {
        this.factor = factor;
    }

    /** el equals calc. */
    private java.lang.Object __equalsCalc = null;
    
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CertificadoDatosDiscapacidadRespuestaMovilidad)) return false;
        CertificadoDatosDiscapacidadRespuestaMovilidad other = (CertificadoDatosDiscapacidadRespuestaMovilidad) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.puntuacion==null && other.getPuntuacion()==null) || 
             (this.puntuacion!=null &&
              this.puntuacion.equals(other.getPuntuacion()))) &&
            ((this.factor==null && other.getFactor()==null) || 
             (this.factor!=null &&
              this.factor.equals(other.getFactor())));
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
        if (getPuntuacion() != null) {
            _hashCode += getPuntuacion().hashCode();
        }
        if (getFactor() != null) {
            _hashCode += getFactor().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    /** el type desc. */
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CertificadoDatosDiscapacidadRespuestaMovilidad.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">>CertificadoDatosDiscapacidad>RespuestaMovilidad"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("puntuacion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "Puntuacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">Puntuacion"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("factor");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "Factor"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">Factor"));
        elemField.setMinOccurs(0);
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
