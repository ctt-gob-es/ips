/**
 * Consulta.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.redsara.intermediacion.scsp.esquemas.fnumerosa.datosespecificos;

/**
 * El Class Consulta.
 */
public class Consulta  implements java.io.Serializable {
    
    /** el titulo familia numerosa. */
    private es.redsara.intermediacion.scsp.esquemas.fnumerosa.datosespecificos.TituloFamiliaNumerosa tituloFamiliaNumerosa;

    /** el datos adicionales titular. */
    private es.redsara.intermediacion.scsp.esquemas.fnumerosa.datosespecificos.DatosAdicionalesTitular datosAdicionalesTitular;

    /**
     * Crea una nueva consulta.
     */
    public Consulta() {
    }

    /**
     * Crea una nueva consulta.
     *
     * @param tituloFamiliaNumerosa el titulo familia numerosa
     * @param datosAdicionalesTitular el datos adicionales titular
     */
    public Consulta(
           es.redsara.intermediacion.scsp.esquemas.fnumerosa.datosespecificos.TituloFamiliaNumerosa tituloFamiliaNumerosa,
           es.redsara.intermediacion.scsp.esquemas.fnumerosa.datosespecificos.DatosAdicionalesTitular datosAdicionalesTitular) {
           this.tituloFamiliaNumerosa = tituloFamiliaNumerosa;
           this.datosAdicionalesTitular = datosAdicionalesTitular;
    }


    /**
     * Gets the tituloFamiliaNumerosa value for this Consulta.
     * 
     * @return tituloFamiliaNumerosa
     */
    public es.redsara.intermediacion.scsp.esquemas.fnumerosa.datosespecificos.TituloFamiliaNumerosa getTituloFamiliaNumerosa() {
        return tituloFamiliaNumerosa;
    }


    /**
     * Sets the tituloFamiliaNumerosa value for this Consulta.
     *
     * @param tituloFamiliaNumerosa el nuevo titulo familia numerosa
     */
    public void setTituloFamiliaNumerosa(es.redsara.intermediacion.scsp.esquemas.fnumerosa.datosespecificos.TituloFamiliaNumerosa tituloFamiliaNumerosa) {
        this.tituloFamiliaNumerosa = tituloFamiliaNumerosa;
    }


    /**
     * Gets the datosAdicionalesTitular value for this Consulta.
     * 
     * @return datosAdicionalesTitular
     */
    public es.redsara.intermediacion.scsp.esquemas.fnumerosa.datosespecificos.DatosAdicionalesTitular getDatosAdicionalesTitular() {
        return datosAdicionalesTitular;
    }


    /**
     * Sets the datosAdicionalesTitular value for this Consulta.
     *
     * @param datosAdicionalesTitular el nuevo datos adicionales titular
     */
    public void setDatosAdicionalesTitular(es.redsara.intermediacion.scsp.esquemas.fnumerosa.datosespecificos.DatosAdicionalesTitular datosAdicionalesTitular) {
        this.datosAdicionalesTitular = datosAdicionalesTitular;
    }

    /** el equals calc. */
    private java.lang.Object __equalsCalc = null;
    
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Consulta)) return false;
        Consulta other = (Consulta) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.tituloFamiliaNumerosa==null && other.getTituloFamiliaNumerosa()==null) || 
             (this.tituloFamiliaNumerosa!=null &&
              this.tituloFamiliaNumerosa.equals(other.getTituloFamiliaNumerosa()))) &&
            ((this.datosAdicionalesTitular==null && other.getDatosAdicionalesTitular()==null) || 
             (this.datosAdicionalesTitular!=null &&
              this.datosAdicionalesTitular.equals(other.getDatosAdicionalesTitular())));
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
        if (getTituloFamiliaNumerosa() != null) {
            _hashCode += getTituloFamiliaNumerosa().hashCode();
        }
        if (getDatosAdicionalesTitular() != null) {
            _hashCode += getDatosAdicionalesTitular().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    /** el type desc. */
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Consulta.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">Consulta"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tituloFamiliaNumerosa");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "TituloFamiliaNumerosa"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">TituloFamiliaNumerosa"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("datosAdicionalesTitular");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "DatosAdicionalesTitular"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "DatosAdicionalesTitular"));
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
