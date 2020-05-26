/**
 * Retorno.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.redsara.intermediacion.scsp.esquemas.discapacidad.datosespecificos;

/**
 * El Class Retorno.
 */
public class Retorno  implements java.io.Serializable {
    
    /** el estado. */
    private es.redsara.intermediacion.scsp.esquemas.discapacidad.datosespecificos.Estado estado;

    /** el datos adicionales titular respuesta. */
    /* Opcional si Estado no es un ERROR */
    private es.redsara.intermediacion.scsp.esquemas.discapacidad.datosespecificos.DatosAdicionalesTitular datosAdicionalesTitularRespuesta;

    /** el certificado datos discapacidad. */
    /* Opcional si Estado no es un ERROR */
    private es.redsara.intermediacion.scsp.esquemas.discapacidad.datosespecificos.CertificadoDatosDiscapacidad certificadoDatosDiscapacidad;

    /**
     * Crea una nueva retorno.
     */
    public Retorno() {
    }

    /**
     * Crea una nueva retorno.
     *
     * @param estado el estado
     * @param datosAdicionalesTitularRespuesta el datos adicionales titular respuesta
     * @param certificadoDatosDiscapacidad el certificado datos discapacidad
     */
    public Retorno(
           es.redsara.intermediacion.scsp.esquemas.discapacidad.datosespecificos.Estado estado,
           es.redsara.intermediacion.scsp.esquemas.discapacidad.datosespecificos.DatosAdicionalesTitular datosAdicionalesTitularRespuesta,
           es.redsara.intermediacion.scsp.esquemas.discapacidad.datosespecificos.CertificadoDatosDiscapacidad certificadoDatosDiscapacidad) {
           this.estado = estado;
           this.datosAdicionalesTitularRespuesta = datosAdicionalesTitularRespuesta;
           this.certificadoDatosDiscapacidad = certificadoDatosDiscapacidad;
    }


    /**
     * Gets the estado value for this Retorno.
     * 
     * @return estado
     */
    public es.redsara.intermediacion.scsp.esquemas.discapacidad.datosespecificos.Estado getEstado() {
        return estado;
    }


    /**
     * Sets the estado value for this Retorno.
     *
     * @param estado el nuevo estado
     */
    public void setEstado(es.redsara.intermediacion.scsp.esquemas.discapacidad.datosespecificos.Estado estado) {
        this.estado = estado;
    }


    /**
     * Gets the datosAdicionalesTitularRespuesta value for this Retorno.
     * 
     * @return datosAdicionalesTitularRespuesta   * Opcional si Estado no es un ERROR
     */
    public es.redsara.intermediacion.scsp.esquemas.discapacidad.datosespecificos.DatosAdicionalesTitular getDatosAdicionalesTitularRespuesta() {
        return datosAdicionalesTitularRespuesta;
    }


    /**
     * Sets the datosAdicionalesTitularRespuesta value for this Retorno.
     * 
     * @param datosAdicionalesTitularRespuesta   * Opcional si Estado no es un ERROR
     */
    public void setDatosAdicionalesTitularRespuesta(es.redsara.intermediacion.scsp.esquemas.discapacidad.datosespecificos.DatosAdicionalesTitular datosAdicionalesTitularRespuesta) {
        this.datosAdicionalesTitularRespuesta = datosAdicionalesTitularRespuesta;
    }


    /**
     * Gets the certificadoDatosDiscapacidad value for this Retorno.
     * 
     * @return certificadoDatosDiscapacidad   * Opcional si Estado no es un ERROR
     */
    public es.redsara.intermediacion.scsp.esquemas.discapacidad.datosespecificos.CertificadoDatosDiscapacidad getCertificadoDatosDiscapacidad() {
        return certificadoDatosDiscapacidad;
    }


    /**
     * Sets the certificadoDatosDiscapacidad value for this Retorno.
     * 
     * @param certificadoDatosDiscapacidad   * Opcional si Estado no es un ERROR
     */
    public void setCertificadoDatosDiscapacidad(es.redsara.intermediacion.scsp.esquemas.discapacidad.datosespecificos.CertificadoDatosDiscapacidad certificadoDatosDiscapacidad) {
        this.certificadoDatosDiscapacidad = certificadoDatosDiscapacidad;
    }

    /** el equals calc. */
    private java.lang.Object __equalsCalc = null;
    
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Retorno)) return false;
        Retorno other = (Retorno) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.estado==null && other.getEstado()==null) || 
             (this.estado!=null &&
              this.estado.equals(other.getEstado()))) &&
            ((this.datosAdicionalesTitularRespuesta==null && other.getDatosAdicionalesTitularRespuesta()==null) || 
             (this.datosAdicionalesTitularRespuesta!=null &&
              this.datosAdicionalesTitularRespuesta.equals(other.getDatosAdicionalesTitularRespuesta()))) &&
            ((this.certificadoDatosDiscapacidad==null && other.getCertificadoDatosDiscapacidad()==null) || 
             (this.certificadoDatosDiscapacidad!=null &&
              this.certificadoDatosDiscapacidad.equals(other.getCertificadoDatosDiscapacidad())));
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
        if (getEstado() != null) {
            _hashCode += getEstado().hashCode();
        }
        if (getDatosAdicionalesTitularRespuesta() != null) {
            _hashCode += getDatosAdicionalesTitularRespuesta().hashCode();
        }
        if (getCertificadoDatosDiscapacidad() != null) {
            _hashCode += getCertificadoDatosDiscapacidad().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    /** el type desc. */
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Retorno.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">Retorno"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("estado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "Estado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">Estado"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("datosAdicionalesTitularRespuesta");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "DatosAdicionalesTitularRespuesta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "DatosAdicionalesTitular"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("certificadoDatosDiscapacidad");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "CertificadoDatosDiscapacidad"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">CertificadoDatosDiscapacidad"));
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
