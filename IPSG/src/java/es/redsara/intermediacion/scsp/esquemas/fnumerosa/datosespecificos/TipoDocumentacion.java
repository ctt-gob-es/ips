/**
 * TipoDocumentacion.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.redsara.intermediacion.scsp.esquemas.fnumerosa.datosespecificos;

/**
 * El Class TipoDocumentacion.
 */
public class TipoDocumentacion implements java.io.Serializable {
    
    /** el value. */
    private java.lang.String _value_;
    
    /** el table. */
    private static java.util.HashMap _table_ = new java.util.HashMap();

    /**
     * Crea una nueva tipo documentacion.
     *
     * @param value el value
     */
    // Constructor
    protected TipoDocumentacion(java.lang.String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    /** La constante _NIF. */
    public static final java.lang.String _NIF = "NIF";
    
    /** La constante _DNI. */
    public static final java.lang.String _DNI = "DNI";
    
    /** La constante _Pasaporte. */
    public static final java.lang.String _Pasaporte = "Pasaporte";
    
    /** La constante _NIE. */
    public static final java.lang.String _NIE = "NIE";
    
    /** La constante NIF. */
    public static final TipoDocumentacion NIF = new TipoDocumentacion(_NIF);
    
    /** La constante DNI. */
    public static final TipoDocumentacion DNI = new TipoDocumentacion(_DNI);
    
    /** La constante Pasaporte. */
    public static final TipoDocumentacion Pasaporte = new TipoDocumentacion(_Pasaporte);
    
    /** La constante NIE. */
    public static final TipoDocumentacion NIE = new TipoDocumentacion(_NIE);
    
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
     * @return el tipo documentacion
     * @throws IllegalArgumentException el illegal argument exception
     */
    public static TipoDocumentacion fromValue(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        TipoDocumentacion enumeration = (TipoDocumentacion)
            _table_.get(value);
        if (enumeration==null) throw new java.lang.IllegalArgumentException();
        return enumeration;
    }
    
    /**
     * From string.
     *
     * @param value el value
     * @return el tipo documentacion
     * @throws IllegalArgumentException el illegal argument exception
     */
    public static TipoDocumentacion fromString(java.lang.String value)
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
        new org.apache.axis.description.TypeDesc(TipoDocumentacion.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">TipoDocumentacion"));
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
