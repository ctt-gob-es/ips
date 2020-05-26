/**
 * Funcionario.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.redsara.intermediacion.scsp.esquemas.ws.respuesta;

/**
 * El Class Funcionario.
 */
public class Funcionario  implements java.io.Serializable {
    
    /** el nombre completo funcionario. */
    private java.lang.String nombreCompletoFuncionario;

    /** el nif funcionario. */
    private java.lang.String nifFuncionario;

    /**
     * Crea una nueva funcionario.
     */
    public Funcionario() {
    }

    /**
     * Crea una nueva funcionario.
     *
     * @param nombreCompletoFuncionario el nombre completo funcionario
     * @param nifFuncionario el nif funcionario
     */
    public Funcionario(
           java.lang.String nombreCompletoFuncionario,
           java.lang.String nifFuncionario) {
           this.nombreCompletoFuncionario = nombreCompletoFuncionario;
           this.nifFuncionario = nifFuncionario;
    }


    /**
     * Gets the nombreCompletoFuncionario value for this Funcionario.
     * 
     * @return nombreCompletoFuncionario
     */
    public java.lang.String getNombreCompletoFuncionario() {
        return nombreCompletoFuncionario;
    }


    /**
     * Sets the nombreCompletoFuncionario value for this Funcionario.
     *
     * @param nombreCompletoFuncionario el nuevo nombre completo funcionario
     */
    public void setNombreCompletoFuncionario(java.lang.String nombreCompletoFuncionario) {
        this.nombreCompletoFuncionario = nombreCompletoFuncionario;
    }


    /**
     * Gets the nifFuncionario value for this Funcionario.
     * 
     * @return nifFuncionario
     */
    public java.lang.String getNifFuncionario() {
        return nifFuncionario;
    }


    /**
     * Sets the nifFuncionario value for this Funcionario.
     *
     * @param nifFuncionario el nuevo nif funcionario
     */
    public void setNifFuncionario(java.lang.String nifFuncionario) {
        this.nifFuncionario = nifFuncionario;
    }

    /** el equals calc. */
    private java.lang.Object __equalsCalc = null;
    
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Funcionario)) return false;
        Funcionario other = (Funcionario) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.nombreCompletoFuncionario==null && other.getNombreCompletoFuncionario()==null) || 
             (this.nombreCompletoFuncionario!=null &&
              this.nombreCompletoFuncionario.equals(other.getNombreCompletoFuncionario()))) &&
            ((this.nifFuncionario==null && other.getNifFuncionario()==null) || 
             (this.nifFuncionario!=null &&
              this.nifFuncionario.equals(other.getNifFuncionario())));
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
        if (getNombreCompletoFuncionario() != null) {
            _hashCode += getNombreCompletoFuncionario().hashCode();
        }
        if (getNifFuncionario() != null) {
            _hashCode += getNifFuncionario().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    /** el type desc. */
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Funcionario.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/ws/respuesta", ">Funcionario"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombreCompletoFuncionario");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/ws/respuesta", "NombreCompletoFuncionario"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nifFuncionario");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/ws/respuesta", "NifFuncionario"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
