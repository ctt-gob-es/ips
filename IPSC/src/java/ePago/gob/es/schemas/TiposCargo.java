/**
 * TiposCargo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ePago.gob.es.schemas;

/**
 * El Class TiposCargo.
 */
public class TiposCargo implements java.io.Serializable {
    
    /** el value. */
    private int _value_;
    
    /** el table. */
    private static java.util.HashMap _table_ = new java.util.HashMap();

    /**
     * Crea una nueva tipos cargo.
     *
     * @param value el value
     */
    // Constructor
    protected TiposCargo(int value) {
        _value_ = value;
        _table_.put(new java.lang.Integer(_value_),this);
    }

    /** La constante _value1. */
    public static final int _value1 = 0;
    
    /** La constante _value2. */
    public static final int _value2 = 1;
    
    /** La constante value1. */
    public static final TiposCargo value1 = new TiposCargo(_value1);
    
    /** La constante value2. */
    public static final TiposCargo value2 = new TiposCargo(_value2);
    
    /**
     * Obtiene el value.
     *
     * @return el value
     */
    public int getValue() { return _value_;}
    
    /**
     * From value.
     *
     * @param value el value
     * @return el tipos cargo
     * @throws IllegalArgumentException el illegal argument exception
     */
    public static TiposCargo fromValue(int value)
          throws java.lang.IllegalArgumentException {
        TiposCargo enumeration = (TiposCargo)
            _table_.get(new java.lang.Integer(value));
        if (enumeration==null) throw new java.lang.IllegalArgumentException();
        return enumeration;
    }
    
    /**
     * From string.
     *
     * @param value el value
     * @return el tipos cargo
     * @throws IllegalArgumentException el illegal argument exception
     */
    public static TiposCargo fromString(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        try {
            return fromValue(java.lang.Integer.parseInt(value));
        } catch (Exception e) {
            throw new java.lang.IllegalArgumentException();
        }
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
    public java.lang.String toString() { return java.lang.String.valueOf(_value_);}
    
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
        new org.apache.axis.description.TypeDesc(TiposCargo.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "TiposCargo"));
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
