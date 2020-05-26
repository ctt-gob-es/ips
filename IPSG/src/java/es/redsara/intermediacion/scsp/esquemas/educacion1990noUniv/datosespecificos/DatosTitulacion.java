/**
 * DatosTitulacion.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.redsara.intermediacion.scsp.esquemas.educacion1990noUniv.datosespecificos;

/**
 * El Class DatosTitulacion.
 */
public class DatosTitulacion  implements java.io.Serializable {
    
    /** el datos centro. */
    private es.redsara.intermediacion.scsp.esquemas.educacion1990noUniv.datosespecificos.DatosCentro datosCentro;

    /** el datos titulo. */
    /* Datos del título */
    private es.redsara.intermediacion.scsp.esquemas.educacion1990noUniv.datosespecificos.DatosTitulacionDatosTitulo datosTitulo;

    /**
     * Crea una nueva datos titulacion.
     */
    public DatosTitulacion() {
    }

    /**
     * Crea una nueva datos titulacion.
     *
     * @param datosCentro el datos centro
     * @param datosTitulo el datos titulo
     */
    public DatosTitulacion(
    		es.redsara.intermediacion.scsp.esquemas.educacion1990noUniv.datosespecificos.DatosCentro datosCentro,
    		es.redsara.intermediacion.scsp.esquemas.educacion1990noUniv.datosespecificos.DatosTitulacionDatosTitulo datosTitulo) {
           this.datosCentro = datosCentro;
           this.datosTitulo = datosTitulo;
    }


    /**
     * Gets the datosCentro value for this DatosTitulacion.
     * 
     * @return datosCentro
     */
    public es.redsara.intermediacion.scsp.esquemas.educacion1990noUniv.datosespecificos.DatosCentro getDatosCentro() {
        return datosCentro;
    }


    /**
     * Sets the datosCentro value for this DatosTitulacion.
     *
     * @param datosCentro el nuevo datos centro
     */
    public void setDatosCentro(es.redsara.intermediacion.scsp.esquemas.educacion1990noUniv.datosespecificos.DatosCentro datosCentro) {
        this.datosCentro = datosCentro;
    }


    /**
     * Gets the datosTitulo value for this DatosTitulacion.
     * 
     * @return datosTitulo   * Datos del título
     */
    public es.redsara.intermediacion.scsp.esquemas.educacion1990noUniv.datosespecificos.DatosTitulacionDatosTitulo getDatosTitulo() {
        return datosTitulo;
    }


    /**
     * Sets the datosTitulo value for this DatosTitulacion.
     * 
     * @param datosTitulo   * Datos del título
     */
    public void setDatosTitulo(es.redsara.intermediacion.scsp.esquemas.educacion1990noUniv.datosespecificos.DatosTitulacionDatosTitulo datosTitulo) {
        this.datosTitulo = datosTitulo;
    }

    /** el equals calc. */
    private java.lang.Object __equalsCalc = null;
    
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DatosTitulacion)) return false;
        DatosTitulacion other = (DatosTitulacion) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.datosCentro==null && other.getDatosCentro()==null) || 
             (this.datosCentro!=null &&
              this.datosCentro.equals(other.getDatosCentro()))) &&
            ((this.datosTitulo==null && other.getDatosTitulo()==null) || 
             (this.datosTitulo!=null &&
              this.datosTitulo.equals(other.getDatosTitulo())));
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
        if (getDatosCentro() != null) {
            _hashCode += getDatosCentro().hashCode();
        }
        if (getDatosTitulo() != null) {
            _hashCode += getDatosTitulo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    /** el type desc. */
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DatosTitulacion.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">DatosTitulacion"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("datosCentro");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "DatosCentro"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">DatosCentro"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("datosTitulo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "DatosTitulo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">>DatosTitulacion>DatosTitulo"));
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
