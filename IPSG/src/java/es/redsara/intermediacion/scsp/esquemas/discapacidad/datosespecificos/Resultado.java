/**
 * Resultado.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.redsara.intermediacion.scsp.esquemas.discapacidad.datosespecificos;

/**
 * El Class Resultado.
 */
public class Resultado implements java.io.Serializable {
    
    /** el value. */
    private java.lang.String _value_;
    
    /** el table. */
    private static java.util.HashMap _table_ = new java.util.HashMap();

    /**
     * Crea una nueva resultado.
     *
     * @param value el value
     */
    // Constructor
    protected Resultado(java.lang.String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    /** La constante _S. */
    public static final java.lang.String _S = "S";
    
    /** La constante _N. */
    public static final java.lang.String _N = "N";
    
    /** La constante S. */
    public static final Resultado S = new Resultado(_S);
    
    /** La constante N. */
    public static final Resultado N = new Resultado(_N);
    
    /**
     * Obtiene el value.
     *
     * @return el value
     */
    public java.lang.String getValue() { return _value_;}
    
    /**
     * From value.
     *
     * @param value el value
     * @return el resultado
     * @throws IllegalArgumentException el illegal argument exception
     */
    public static Resultado fromValue(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        Resultado enumeration = (Resultado)
            _table_.get(value);
        if (enumeration==null) throw new java.lang.IllegalArgumentException();
        return enumeration;
    }
    
    /**
     * From string.
     *
     * @param value el value
     * @return el resultado
     * @throws IllegalArgumentException el illegal argument exception
     */
    public static Resultado fromString(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        return fromValue(value);
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(java.lang.Object obj) {return (obj == this);}
    
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() { return toString().hashCode();}
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public java.lang.String toString() { return _value_;}
    
    /**
     * Read resolve.
     *
     * @return el java.lang. object
     * @throws ObjectStreamException el object stream exception
     */
    public java.lang.Object readResolve() throws java.io.ObjectStreamException { return fromValue(_value_);}
    
    /**
     * Obtiene el serializer.
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
          new org.apache.axis.encoding.ser.EnumSerializer(
            _javaType, _xmlType);
    }
    
    /**
     * Obtiene el deserializer.
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
          new org.apache.axis.encoding.ser.EnumDeserializer(
            _javaType, _xmlType);
    }
    
    /** el type desc. */
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Resultado.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "Resultado"));
    }
    
    /**
     * Return type metadata object.
     *
     * @return el type desc
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
