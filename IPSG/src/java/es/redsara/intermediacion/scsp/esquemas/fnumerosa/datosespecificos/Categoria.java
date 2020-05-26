/**
 * Categoria.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.redsara.intermediacion.scsp.esquemas.fnumerosa.datosespecificos;

/**
 * El Class Categoria.
 */
public class Categoria implements java.io.Serializable {
    
    /** el value. */
    private java.lang.String _value_;
    
    /** el table. */
    private static java.util.HashMap _table_ = new java.util.HashMap();

    /**
     * Crea una nueva categoria.
     *
     * @param value el value
     */
    // Constructor
    protected Categoria(java.lang.String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    /** La constante _G. */
    public static final java.lang.String _G = "G";
    
    /** La constante _E. */
    public static final java.lang.String _E = "E";
    
    /** La constante _P. */
    public static final java.lang.String _P = "P";
    
    /** La constante _S. */
    public static final java.lang.String _S = "S";
    
    /** La constante _H. */
    public static final java.lang.String _H = "H";
    
    /** La constante G. */
    public static final Categoria G = new Categoria(_G);
    
    /** La constante E. */
    public static final Categoria E = new Categoria(_E);
    
    /** La constante P. */
    public static final Categoria P = new Categoria(_P);
    
    /** La constante S. */
    public static final Categoria S = new Categoria(_S);
    
    /** La constante H. */
    public static final Categoria H = new Categoria(_H);
    
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
     * @return el categoria
     * @throws IllegalArgumentException el illegal argument exception
     */
    public static Categoria fromValue(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        Categoria enumeration = (Categoria)
            _table_.get(value);
        if (enumeration==null) throw new java.lang.IllegalArgumentException();
        return enumeration;
    }
    
    /**
     * From string.
     *
     * @param value el value
     * @return el categoria
     * @throws IllegalArgumentException el illegal argument exception
     */
    public static Categoria fromString(java.lang.String value)
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
        new org.apache.axis.description.TypeDesc(Categoria.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">Categoria"));
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
