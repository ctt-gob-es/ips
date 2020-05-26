/**
 * CodigoComunidadAutonoma.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.redsara.intermediacion.scsp.esquemas.fnumerosa.datosespecificos;

/**
 * El Class CodigoComunidadAutonoma.
 */
public class CodigoComunidadAutonoma implements java.io.Serializable {
    
    /** el value. */
    private java.lang.String _value_;
    
    /** el table. */
    private static java.util.HashMap _table_ = new java.util.HashMap();

    /**
     * Crea una nueva codigo comunidad autonoma.
     *
     * @param value el value
     */
    // Constructor
    protected CodigoComunidadAutonoma(java.lang.String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    /** La constante _value1. */
    public static final java.lang.String _value1 = "01";
    
    /** La constante _value2. */
    public static final java.lang.String _value2 = "02";
    
    /** La constante _value3. */
    public static final java.lang.String _value3 = "03";
    
    /** La constante _value4. */
    public static final java.lang.String _value4 = "04";
    
    /** La constante _value5. */
    public static final java.lang.String _value5 = "05";
    
    /** La constante _value6. */
    public static final java.lang.String _value6 = "06";
    
    /** La constante _value7. */
    public static final java.lang.String _value7 = "07";
    
    /** La constante _value8. */
    public static final java.lang.String _value8 = "08";
    
    /** La constante _value9. */
    public static final java.lang.String _value9 = "09";
    
    /** La constante _value10. */
    public static final java.lang.String _value10 = "10";
    
    /** La constante _value11. */
    public static final java.lang.String _value11 = "11";
    
    /** La constante _value12. */
    public static final java.lang.String _value12 = "12";
    
    /** La constante _value13. */
    public static final java.lang.String _value13 = "13";
    
    /** La constante _value14. */
    public static final java.lang.String _value14 = "14";
    
    /** La constante _value15. */
    public static final java.lang.String _value15 = "15";
    
    /** La constante _value16. */
    public static final java.lang.String _value16 = "16";
    
    /** La constante _value17. */
    public static final java.lang.String _value17 = "17";
    
    /** La constante _value18. */
    public static final java.lang.String _value18 = "18";
    
    /** La constante _value19. */
    public static final java.lang.String _value19 = "19";
    
    /** La constante value1. */
    public static final CodigoComunidadAutonoma value1 = new CodigoComunidadAutonoma(_value1);
    
    /** La constante value2. */
    public static final CodigoComunidadAutonoma value2 = new CodigoComunidadAutonoma(_value2);
    
    /** La constante value3. */
    public static final CodigoComunidadAutonoma value3 = new CodigoComunidadAutonoma(_value3);
    
    /** La constante value4. */
    public static final CodigoComunidadAutonoma value4 = new CodigoComunidadAutonoma(_value4);
    
    /** La constante value5. */
    public static final CodigoComunidadAutonoma value5 = new CodigoComunidadAutonoma(_value5);
    
    /** La constante value6. */
    public static final CodigoComunidadAutonoma value6 = new CodigoComunidadAutonoma(_value6);
    
    /** La constante value7. */
    public static final CodigoComunidadAutonoma value7 = new CodigoComunidadAutonoma(_value7);
    
    /** La constante value8. */
    public static final CodigoComunidadAutonoma value8 = new CodigoComunidadAutonoma(_value8);
    
    /** La constante value9. */
    public static final CodigoComunidadAutonoma value9 = new CodigoComunidadAutonoma(_value9);
    
    /** La constante value10. */
    public static final CodigoComunidadAutonoma value10 = new CodigoComunidadAutonoma(_value10);
    
    /** La constante value11. */
    public static final CodigoComunidadAutonoma value11 = new CodigoComunidadAutonoma(_value11);
    
    /** La constante value12. */
    public static final CodigoComunidadAutonoma value12 = new CodigoComunidadAutonoma(_value12);
    
    /** La constante value13. */
    public static final CodigoComunidadAutonoma value13 = new CodigoComunidadAutonoma(_value13);
    
    /** La constante value14. */
    public static final CodigoComunidadAutonoma value14 = new CodigoComunidadAutonoma(_value14);
    
    /** La constante value15. */
    public static final CodigoComunidadAutonoma value15 = new CodigoComunidadAutonoma(_value15);
    
    /** La constante value16. */
    public static final CodigoComunidadAutonoma value16 = new CodigoComunidadAutonoma(_value16);
    
    /** La constante value17. */
    public static final CodigoComunidadAutonoma value17 = new CodigoComunidadAutonoma(_value17);
    
    /** La constante value18. */
    public static final CodigoComunidadAutonoma value18 = new CodigoComunidadAutonoma(_value18);
    
    /** La constante value19. */
    public static final CodigoComunidadAutonoma value19 = new CodigoComunidadAutonoma(_value19);
    
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
     * @return el codigo comunidad autonoma
     * @throws IllegalArgumentException el illegal argument exception
     */
    public static CodigoComunidadAutonoma fromValue(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        CodigoComunidadAutonoma enumeration = (CodigoComunidadAutonoma)
            _table_.get(value);
        if (enumeration==null) throw new java.lang.IllegalArgumentException();
        return enumeration;
    }
    
    /**
     * From string.
     *
     * @param value el value
     * @return el codigo comunidad autonoma
     * @throws IllegalArgumentException el illegal argument exception
     */
    public static CodigoComunidadAutonoma fromString(java.lang.String value)
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
        new org.apache.axis.description.TypeDesc(CodigoComunidadAutonoma.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">CodigoComunidadAutonoma"));
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
