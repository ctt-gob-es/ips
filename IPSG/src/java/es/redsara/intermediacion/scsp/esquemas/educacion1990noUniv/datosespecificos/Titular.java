/**
 * Titular.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.redsara.intermediacion.scsp.esquemas.educacion1990noUniv.datosespecificos;

/**
 * El Class Titular.
 */
public class Titular  implements java.io.Serializable {
    
    /** el datos titular. */
    /* Datos del titular a consultar */
    private es.redsara.intermediacion.scsp.esquemas.educacion1990noUniv.datosespecificos.TitularDatosTitular datosTitular;

    /** el lista titulos. */
    private es.redsara.intermediacion.scsp.esquemas.educacion1990noUniv.datosespecificos.DatosTitulacion[] listaTitulos;

    /**
     * Crea una nueva titular.
     */
    public Titular() {
    }

    /**
     * Crea una nueva titular.
     *
     * @param datosTitular el datos titular
     * @param listaTitulos el lista titulos
     */
    public Titular(
           es.redsara.intermediacion.scsp.esquemas.educacion1990noUniv.datosespecificos.TitularDatosTitular datosTitular,
           es.redsara.intermediacion.scsp.esquemas.educacion1990noUniv.datosespecificos.DatosTitulacion[] listaTitulos) {
           this.datosTitular = datosTitular;
           this.listaTitulos = listaTitulos;
    }


    /**
     * Gets the datosTitular value for this Titular.
     * 
     * @return datosTitular   * Datos del titular a consultar
     */
    public es.redsara.intermediacion.scsp.esquemas.educacion1990noUniv.datosespecificos.TitularDatosTitular getDatosTitular() {
        return datosTitular;
    }


    /**
     * Sets the datosTitular value for this Titular.
     * 
     * @param datosTitular   * Datos del titular a consultar
     */
    public void setDatosTitular(es.redsara.intermediacion.scsp.esquemas.educacion1990noUniv.datosespecificos.TitularDatosTitular datosTitular) {
        this.datosTitular = datosTitular;
    }


    /**
     * Gets the listaTitulos value for this Titular.
     * 
     * @return listaTitulos
     */
    public es.redsara.intermediacion.scsp.esquemas.educacion1990noUniv.datosespecificos.DatosTitulacion[] getListaTitulos() {
        return listaTitulos;
    }


    /**
     * Sets the listaTitulos value for this Titular.
     *
     * @param listaTitulos el nuevo lista titulos
     */
    public void setListaTitulos(es.redsara.intermediacion.scsp.esquemas.educacion1990noUniv.datosespecificos.DatosTitulacion[] listaTitulos) {
        this.listaTitulos = listaTitulos;
    }

    /** el equals calc. */
    private java.lang.Object __equalsCalc = null;
    
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Titular)) return false;
        Titular other = (Titular) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.datosTitular==null && other.getDatosTitular()==null) || 
             (this.datosTitular!=null &&
              this.datosTitular.equals(other.getDatosTitular()))) &&
            ((this.listaTitulos==null && other.getListaTitulos()==null) || 
             (this.listaTitulos!=null &&
              java.util.Arrays.equals(this.listaTitulos, other.getListaTitulos())));
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
        if (getDatosTitular() != null) {
            _hashCode += getDatosTitular().hashCode();
        }
        if (getListaTitulos() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaTitulos());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaTitulos(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    /** el type desc. */
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Titular.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">Titular"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("datosTitular");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "DatosTitular"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">>Titular>DatosTitular"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaTitulos");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "ListaTitulos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">ListaTitulos"));
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
