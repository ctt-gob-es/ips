/**
 * Retorno.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.redsara.intermediacion.scsp.esquemas.sepe.datosespecificos;

/**
 * El Class Retorno.
 */
public class Retorno  implements java.io.Serializable {
    
    /** el estado. */
    /* Bloque que contiene información sobre el estado
     * 						del retorno del servicio. */
    private es.redsara.intermediacion.scsp.esquemas.sepe.datosespecificos.Estado estado;

    /** el demandante empleo. */
    /* Bloque que contiene la información sobre el
     * 						demandante de empleo. */
    private es.redsara.intermediacion.scsp.esquemas.sepe.datosespecificos.DemandanteEmpleo demandanteEmpleo;

    /**
     * Crea una nueva retorno.
     */
    public Retorno() {
    }

    /**
     * Crea una nueva retorno.
     *
     * @param estado el estado
     * @param demandanteEmpleo el demandante empleo
     */
    public Retorno(
           es.redsara.intermediacion.scsp.esquemas.sepe.datosespecificos.Estado estado,
           es.redsara.intermediacion.scsp.esquemas.sepe.datosespecificos.DemandanteEmpleo demandanteEmpleo) {
           this.estado = estado;
           this.demandanteEmpleo = demandanteEmpleo;
    }


    /**
     * Gets the estado value for this Retorno.
     * 
     * @return estado   * Bloque que contiene información sobre el estado
     * 						del retorno del servicio.
     */
    public es.redsara.intermediacion.scsp.esquemas.sepe.datosespecificos.Estado getEstado() {
        return estado;
    }


    /**
     * Sets the estado value for this Retorno.
     * 
     * @param estado   * Bloque que contiene información sobre el estado
     * 						del retorno del servicio.
     */
    public void setEstado(es.redsara.intermediacion.scsp.esquemas.sepe.datosespecificos.Estado estado) {
        this.estado = estado;
    }


    /**
     * Gets the demandanteEmpleo value for this Retorno.
     * 
     * @return demandanteEmpleo   * Bloque que contiene la información sobre el
     * 						demandante de empleo.
     */
    public es.redsara.intermediacion.scsp.esquemas.sepe.datosespecificos.DemandanteEmpleo getDemandanteEmpleo() {
        return demandanteEmpleo;
    }


    /**
     * Sets the demandanteEmpleo value for this Retorno.
     * 
     * @param demandanteEmpleo   * Bloque que contiene la información sobre el
     * 						demandante de empleo.
     */
    public void setDemandanteEmpleo(es.redsara.intermediacion.scsp.esquemas.sepe.datosespecificos.DemandanteEmpleo demandanteEmpleo) {
        this.demandanteEmpleo = demandanteEmpleo;
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
            ((this.demandanteEmpleo==null && other.getDemandanteEmpleo()==null) || 
             (this.demandanteEmpleo!=null &&
              this.demandanteEmpleo.equals(other.getDemandanteEmpleo())));
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
        if (getDemandanteEmpleo() != null) {
            _hashCode += getDemandanteEmpleo().hashCode();
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
        elemField.setFieldName("demandanteEmpleo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "DemandanteEmpleo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">DemandanteEmpleo"));
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
