/**
 * DatosEspecificosConsultaConcreta.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.redsara.intermediacion.scsp.esquemas.sepe.datosespecificos;


/**
 * El Class DatosEspecificos.
 */
public class DatosEspecificos  implements java.io.Serializable {
    
    /** el consulta. */
    /* Bloque que contiene la información necesaria para
     * 						tramitar la petición. */
    private es.redsara.intermediacion.scsp.esquemas.sepe.datosespecificos.Consulta consulta;

    /** el retorno. */
    /* Bloque que contiene la información necesaria para
     * 						tramitar la respuesta. */
    private es.redsara.intermediacion.scsp.esquemas.sepe.datosespecificos.Retorno retorno;

    /** el id. */
    private java.lang.String id;  // attribute

    /**
     * Crea una nueva datos especificos.
     */
    public DatosEspecificos() {
    }

    /**
     * Crea una nueva datos especificos.
     *
     * @param consulta el consulta
     * @param retorno el retorno
     * @param id el id
     */
    public DatosEspecificos(
           es.redsara.intermediacion.scsp.esquemas.sepe.datosespecificos.Consulta consulta,
           es.redsara.intermediacion.scsp.esquemas.sepe.datosespecificos.Retorno retorno,
           java.lang.String id) {
           this.consulta = consulta;
           this.retorno = retorno;
           this.id = id;
    }


    /**
     * Gets the consulta value for this DatosEspecificosConsultaConcreta.
     * 
     * @return consulta   * Bloque que contiene la información necesaria para
     * 						tramitar la petición.
     */
    public es.redsara.intermediacion.scsp.esquemas.sepe.datosespecificos.Consulta getConsulta() {
        return consulta;
    }


    /**
     * Sets the consulta value for this DatosEspecificosConsultaConcreta.
     * 
     * @param consulta   * Bloque que contiene la información necesaria para
     * 						tramitar la petición.
     */
    public void setConsulta(es.redsara.intermediacion.scsp.esquemas.sepe.datosespecificos.Consulta consulta) {
        this.consulta = consulta;
    }


    /**
     * Gets the retorno value for this DatosEspecificosConsultaConcreta.
     * 
     * @return retorno   * Bloque que contiene la información necesaria para
     * 						tramitar la respuesta.
     */
    public es.redsara.intermediacion.scsp.esquemas.sepe.datosespecificos.Retorno getRetorno() {
        return retorno;
    }


    /**
     * Sets the retorno value for this DatosEspecificosConsultaConcreta.
     * 
     * @param retorno   * Bloque que contiene la información necesaria para
     * 						tramitar la respuesta.
     */
    public void setRetorno(es.redsara.intermediacion.scsp.esquemas.sepe.datosespecificos.Retorno retorno) {
        this.retorno = retorno;
    }


    /**
     * Gets the id value for this DatosEspecificosConsultaConcreta.
     * 
     * @return id
     */
    public java.lang.String getId() {
        return id;
    }


    /**
     * Sets the id value for this DatosEspecificosConsultaConcreta.
     *
     * @param id el nuevo id
     */
    public void setId(java.lang.String id) {
        this.id = id;
    }

    /** el equals calc. */
    private java.lang.Object __equalsCalc = null;
    
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DatosEspecificos)) return false;
        DatosEspecificos other = (DatosEspecificos) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.consulta==null && other.getConsulta()==null) || 
             (this.consulta!=null &&
              this.consulta.equals(other.getConsulta()))) &&
            ((this.retorno==null && other.getRetorno()==null) || 
             (this.retorno!=null &&
              this.retorno.equals(other.getRetorno()))) &&
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId())));
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
        if (getConsulta() != null) {
            _hashCode += getConsulta().hashCode();
        }
        if (getRetorno() != null) {
            _hashCode += getRetorno().hashCode();
        }
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    /** el type desc. */
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DatosEspecificos.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "DatosEspecificos"));
        org.apache.axis.description.AttributeDesc attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("id");
        attrField.setXmlName(new javax.xml.namespace.QName("", "Id"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("consulta");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "Consulta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">Consulta"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("retorno");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "Retorno"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">Retorno"));
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
