/**
 * TituloFamiliaNumerosa.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.redsara.intermediacion.scsp.esquemas.fnumerosa.datosespecificos;

/**
 * El Class TituloFamiliaNumerosa.
 */
public class TituloFamiliaNumerosa  implements java.io.Serializable {
    
    /** el codigo comunidad autonoma. */
    private es.redsara.intermediacion.scsp.esquemas.fnumerosa.datosespecificos.CodigoComunidadAutonoma codigoComunidadAutonoma;

    /** el numero titulo. */
    private java.lang.String numeroTitulo;

    /** el fecha consulta. */
    private java.lang.String fechaConsulta;

    /**
     * Crea una nueva titulo familia numerosa.
     */
    public TituloFamiliaNumerosa() {
    }

    /**
     * Crea una nueva titulo familia numerosa.
     *
     * @param codigoComunidadAutonoma el codigo comunidad autonoma
     * @param numeroTitulo el numero titulo
     * @param fechaConsulta el fecha consulta
     */
    public TituloFamiliaNumerosa(
           es.redsara.intermediacion.scsp.esquemas.fnumerosa.datosespecificos.CodigoComunidadAutonoma codigoComunidadAutonoma,
           java.lang.String numeroTitulo,
           java.lang.String fechaConsulta) {
           this.codigoComunidadAutonoma = codigoComunidadAutonoma;
           this.numeroTitulo = numeroTitulo;
           this.fechaConsulta = fechaConsulta;
    }


    /**
     * Gets the codigoComunidadAutonoma value for this TituloFamiliaNumerosa.
     * 
     * @return codigoComunidadAutonoma
     */
    public es.redsara.intermediacion.scsp.esquemas.fnumerosa.datosespecificos.CodigoComunidadAutonoma getCodigoComunidadAutonoma() {
        return codigoComunidadAutonoma;
    }


    /**
     * Sets the codigoComunidadAutonoma value for this TituloFamiliaNumerosa.
     *
     * @param codigoComunidadAutonoma el nuevo codigo comunidad autonoma
     */
    public void setCodigoComunidadAutonoma(es.redsara.intermediacion.scsp.esquemas.fnumerosa.datosespecificos.CodigoComunidadAutonoma codigoComunidadAutonoma) {
        this.codigoComunidadAutonoma = codigoComunidadAutonoma;
    }


    /**
     * Gets the numeroTitulo value for this TituloFamiliaNumerosa.
     * 
     * @return numeroTitulo
     */
    public java.lang.String getNumeroTitulo() {
        return numeroTitulo;
    }


    /**
     * Sets the numeroTitulo value for this TituloFamiliaNumerosa.
     *
     * @param numeroTitulo el nuevo numero titulo
     */
    public void setNumeroTitulo(java.lang.String numeroTitulo) {
        this.numeroTitulo = numeroTitulo;
    }


    /**
     * Gets the fechaConsulta value for this TituloFamiliaNumerosa.
     * 
     * @return fechaConsulta
     */
    public java.lang.String getFechaConsulta() {
        return fechaConsulta;
    }


    /**
     * Sets the fechaConsulta value for this TituloFamiliaNumerosa.
     *
     * @param fechaConsulta el nuevo fecha consulta
     */
    public void setFechaConsulta(java.lang.String fechaConsulta) {
        this.fechaConsulta = fechaConsulta;
    }

    /** el equals calc. */
    private java.lang.Object __equalsCalc = null;
    
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TituloFamiliaNumerosa)) return false;
        TituloFamiliaNumerosa other = (TituloFamiliaNumerosa) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.codigoComunidadAutonoma==null && other.getCodigoComunidadAutonoma()==null) || 
             (this.codigoComunidadAutonoma!=null &&
              this.codigoComunidadAutonoma.equals(other.getCodigoComunidadAutonoma()))) &&
            ((this.numeroTitulo==null && other.getNumeroTitulo()==null) || 
             (this.numeroTitulo!=null &&
              this.numeroTitulo.equals(other.getNumeroTitulo()))) &&
            ((this.fechaConsulta==null && other.getFechaConsulta()==null) || 
             (this.fechaConsulta!=null &&
              this.fechaConsulta.equals(other.getFechaConsulta())));
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
        if (getCodigoComunidadAutonoma() != null) {
            _hashCode += getCodigoComunidadAutonoma().hashCode();
        }
        if (getNumeroTitulo() != null) {
            _hashCode += getNumeroTitulo().hashCode();
        }
        if (getFechaConsulta() != null) {
            _hashCode += getFechaConsulta().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    /** el type desc. */
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TituloFamiliaNumerosa.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">TituloFamiliaNumerosa"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoComunidadAutonoma");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "CodigoComunidadAutonoma"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">CodigoComunidadAutonoma"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroTitulo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "NumeroTitulo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">NumeroTitulo"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaConsulta");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "FechaConsulta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "Fecha"));
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
