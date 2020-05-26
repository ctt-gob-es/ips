/**
 * DerechoReclamado.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.redsara.intermediacion.scsp.esquemas.victimasTerrorismo.datosespecificos;

public class DerechoReclamado implements java.io.Serializable {
    private java.math.BigInteger _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected DerechoReclamado(java.math.BigInteger value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final java.math.BigInteger _value1 = new java.math.BigInteger("1");
    public static final java.math.BigInteger _value2 = new java.math.BigInteger("2");
    public static final java.math.BigInteger _value3 = new java.math.BigInteger("3");
    public static final java.math.BigInteger _value4 = new java.math.BigInteger("4");
    public static final java.math.BigInteger _value5 = new java.math.BigInteger("5");
    public static final java.math.BigInteger _value6 = new java.math.BigInteger("6");
    public static final java.math.BigInteger _value7 = new java.math.BigInteger("7");
    public static final java.math.BigInteger _value8 = new java.math.BigInteger("8");
    public static final java.math.BigInteger _value9 = new java.math.BigInteger("9");
    public static final java.math.BigInteger _value10 = new java.math.BigInteger("10");
    public static final DerechoReclamado value1 = new DerechoReclamado(_value1);
    public static final DerechoReclamado value2 = new DerechoReclamado(_value2);
    public static final DerechoReclamado value3 = new DerechoReclamado(_value3);
    public static final DerechoReclamado value4 = new DerechoReclamado(_value4);
    public static final DerechoReclamado value5 = new DerechoReclamado(_value5);
    public static final DerechoReclamado value6 = new DerechoReclamado(_value6);
    public static final DerechoReclamado value7 = new DerechoReclamado(_value7);
    public static final DerechoReclamado value8 = new DerechoReclamado(_value8);
    public static final DerechoReclamado value9 = new DerechoReclamado(_value9);
    public static final DerechoReclamado value10 = new DerechoReclamado(_value10);
    public java.math.BigInteger getValue() { return _value_;}
    public static DerechoReclamado fromValue(java.math.BigInteger value)
          throws java.lang.IllegalArgumentException {
        DerechoReclamado enumeration = (DerechoReclamado)
            _table_.get(value);
        if (enumeration==null) throw new java.lang.IllegalArgumentException();
        return enumeration;
    }
    public static DerechoReclamado fromString(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        try {
            return fromValue(new java.math.BigInteger(value));
        } catch (Exception e) {
            throw new java.lang.IllegalArgumentException();
        }
    }
    public boolean equals(java.lang.Object obj) {return (obj == this);}
    public int hashCode() { return toString().hashCode();}
    public java.lang.String toString() { return _value_.toString();}
    public java.lang.Object readResolve() throws java.io.ObjectStreamException { return fromValue(_value_);}
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new org.apache.axis.encoding.ser.EnumSerializer(
            _javaType, _xmlType);
    }
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new org.apache.axis.encoding.ser.EnumDeserializer(
            _javaType, _xmlType);
    }
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DerechoReclamado.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">DerechoReclamado"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
