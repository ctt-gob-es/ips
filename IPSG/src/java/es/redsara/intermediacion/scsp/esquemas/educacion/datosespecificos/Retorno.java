/**
 * Retorno.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.redsara.intermediacion.scsp.esquemas.educacion.datosespecificos;

/**
 * El Class Retorno.
 */
public class Retorno  implements java.io.Serializable {
    
    /** el estado. */
    private es.redsara.intermediacion.scsp.esquemas.educacion.datosespecificos.Estado estado;

    /** el datos titular. */
    /* Datos del titular del que obtenemos los títulos */
    private es.redsara.intermediacion.scsp.esquemas.educacion.datosespecificos.RetornoDatosTitular datosTitular;

    /** el lista titulos. */
    private es.redsara.intermediacion.scsp.esquemas.educacion.datosespecificos.DatosTitulacion[] listaTitulos;

    /**
     * Crea una nueva retorno.
     */
    public Retorno() {
    }

    /**
     * Crea una nueva retorno.
     *
     * @param estado el estado
     * @param datosTitular el datos titular
     * @param listaTitulos el lista titulos
     */
    public Retorno(
           es.redsara.intermediacion.scsp.esquemas.educacion.datosespecificos.Estado estado,
           es.redsara.intermediacion.scsp.esquemas.educacion.datosespecificos.RetornoDatosTitular datosTitular,
           es.redsara.intermediacion.scsp.esquemas.educacion.datosespecificos.DatosTitulacion[] listaTitulos) {
           this.estado = estado;
           this.datosTitular = datosTitular;
           this.listaTitulos = listaTitulos;
    }


    /**
     * Gets the estado value for this Retorno.
     * 
     * @return estado
     */
    public es.redsara.intermediacion.scsp.esquemas.educacion.datosespecificos.Estado getEstado() {
        return estado;
    }


    /**
     * Sets the estado value for this Retorno.
     *
     * @param estado el nuevo estado
     */
    public void setEstado(es.redsara.intermediacion.scsp.esquemas.educacion.datosespecificos.Estado estado) {
        this.estado = estado;
    }


    /**
     * Gets the datosTitular value for this Retorno.
     * 
     * @return datosTitular   * Datos del titular del que obtenemos los títulos
     */
    public es.redsara.intermediacion.scsp.esquemas.educacion.datosespecificos.RetornoDatosTitular getDatosTitular() {
        return datosTitular;
    }


    /**
     * Sets the datosTitular value for this Retorno.
     * 
     * @param datosTitular   * Datos del titular del que obtenemos los títulos
     */
    public void setDatosTitular(es.redsara.intermediacion.scsp.esquemas.educacion.datosespecificos.RetornoDatosTitular datosTitular) {
        this.datosTitular = datosTitular;
    }


    /**
     * Gets the listaTitulos value for this Retorno.
     * 
     * @return listaTitulos
     */
    public es.redsara.intermediacion.scsp.esquemas.educacion.datosespecificos.DatosTitulacion[] getListaTitulos() {
        return listaTitulos;
    }


    /**
     * Sets the listaTitulos value for this Retorno.
     *
     * @param listaTitulos el nuevo lista titulos
     */
    public void setListaTitulos(es.redsara.intermediacion.scsp.esquemas.educacion.datosespecificos.DatosTitulacion[] listaTitulos) {
        this.listaTitulos = listaTitulos;
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
        if (getEstado() != null) {
            _hashCode += getEstado().hashCode();
        }
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
        elemField.setFieldName("datosTitular");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "DatosTitular"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">>Retorno>DatosTitular"));
        elemField.setMinOccurs(0);
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
