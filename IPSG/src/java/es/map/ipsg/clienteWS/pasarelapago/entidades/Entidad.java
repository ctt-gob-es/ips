/**
 * Entidad.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.map.ipsg.clienteWS.pasarelapago.entidades;

/**
 * El Class Entidad.
 */
public class Entidad  implements java.io.Serializable {
	
    /** el acuenta. */
    private boolean acuenta;

    /** el codigo. */
    private java.lang.String codigo;

    /** el descripcion. */
    private java.lang.String descripcion;

    /** el horario apertura. */
    private java.lang.String horarioApertura;

    /** el horario cierre. */
    private java.lang.String horarioCierre;

    /** el tarjeta. */
    private boolean tarjeta;

    /**
     * Crea una nueva entidad.
     */
    public Entidad() {
    }

    /**
     * Crea una nueva entidad.
     *
     * @param acuenta el acuenta
     * @param codigo el codigo
     * @param descripcion el descripcion
     * @param horarioApertura el horario apertura
     * @param horarioCierre el horario cierre
     * @param tarjeta el tarjeta
     */
    public Entidad(
           boolean acuenta,
           java.lang.String codigo,
           java.lang.String descripcion,
           java.lang.String horarioApertura,
           java.lang.String horarioCierre,
           boolean tarjeta) {
           this.acuenta = acuenta;
           this.codigo = codigo;
           this.descripcion = descripcion;
           this.horarioApertura = horarioApertura;
           this.horarioCierre = horarioCierre;
           this.tarjeta = tarjeta;
    }


    /**
     * Gets the acuenta value for this Entidad.
     * 
     * @return acuenta
     */
    public boolean isAcuenta() {
        return acuenta;
    }


    /**
     * Sets the acuenta value for this Entidad.
     *
     * @param acuenta el nuevo acuenta
     */
    public void setAcuenta(boolean acuenta) {
        this.acuenta = acuenta;
    }


    /**
     * Gets the codigo value for this Entidad.
     * 
     * @return codigo
     */
    public java.lang.String getCodigo() {
        return codigo;
    }


    /**
     * Sets the codigo value for this Entidad.
     *
     * @param codigo el nuevo codigo
     */
    public void setCodigo(java.lang.String codigo) {
        this.codigo = codigo;
    }


    /**
     * Gets the descripcion value for this Entidad.
     * 
     * @return descripcion
     */
    public java.lang.String getDescripcion() {
        return descripcion;
    }


    /**
     * Sets the descripcion value for this Entidad.
     *
     * @param descripcion el nuevo descripcion
     */
    public void setDescripcion(java.lang.String descripcion) {
        this.descripcion = descripcion;
    }


    /**
     * Gets the horarioApertura value for this Entidad.
     * 
     * @return horarioApertura
     */
    public java.lang.String getHorarioApertura() {
        return horarioApertura;
    }


    /**
     * Sets the horarioApertura value for this Entidad.
     *
     * @param horarioApertura el nuevo horario apertura
     */
    public void setHorarioApertura(java.lang.String horarioApertura) {
        this.horarioApertura = horarioApertura;
    }


    /**
     * Gets the horarioCierre value for this Entidad.
     * 
     * @return horarioCierre
     */
    public java.lang.String getHorarioCierre() {
        return horarioCierre;
    }


    /**
     * Sets the horarioCierre value for this Entidad.
     *
     * @param horarioCierre el nuevo horario cierre
     */
    public void setHorarioCierre(java.lang.String horarioCierre) {
        this.horarioCierre = horarioCierre;
    }


    /**
     * Gets the tarjeta value for this Entidad.
     * 
     * @return tarjeta
     */
    public boolean isTarjeta() {
        return tarjeta;
    }


    /**
     * Sets the tarjeta value for this Entidad.
     *
     * @param tarjeta el nuevo tarjeta
     */
    public void setTarjeta(boolean tarjeta) {
        this.tarjeta = tarjeta;
    }

    /** el equals calc. */
    private java.lang.Object __equalsCalc = null;
    
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Entidad)) return false;
        Entidad other = (Entidad) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.acuenta == other.isAcuenta() &&
            ((this.codigo==null && other.getCodigo()==null) || 
             (this.codigo!=null &&
              this.codigo.equals(other.getCodigo()))) &&
            ((this.descripcion==null && other.getDescripcion()==null) || 
             (this.descripcion!=null &&
              this.descripcion.equals(other.getDescripcion()))) &&
            ((this.horarioApertura==null && other.getHorarioApertura()==null) || 
             (this.horarioApertura!=null &&
              this.horarioApertura.equals(other.getHorarioApertura()))) &&
            ((this.horarioCierre==null && other.getHorarioCierre()==null) || 
             (this.horarioCierre!=null &&
              this.horarioCierre.equals(other.getHorarioCierre()))) &&
            this.tarjeta == other.isTarjeta();
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
        _hashCode += (isAcuenta() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getCodigo() != null) {
            _hashCode += getCodigo().hashCode();
        }
        if (getDescripcion() != null) {
            _hashCode += getDescripcion().hashCode();
        }
        if (getHorarioApertura() != null) {
            _hashCode += getHorarioApertura().hashCode();
        }
        if (getHorarioCierre() != null) {
            _hashCode += getHorarioCierre().hashCode();
        }
        _hashCode += (isTarjeta() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    /** el type desc. */
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Entidad.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://pasarelapago.clienteWS.ipsg.map.es/entidades", "Entidad"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("acuenta");
        elemField.setXmlName(new javax.xml.namespace.QName("http://pasarelapago.clienteWS.ipsg.map.es/entidades", "acuenta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://pasarelapago.clienteWS.ipsg.map.es/entidades", "codigo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descripcion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://pasarelapago.clienteWS.ipsg.map.es/entidades", "descripcion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("horarioApertura");
        elemField.setXmlName(new javax.xml.namespace.QName("http://pasarelapago.clienteWS.ipsg.map.es/entidades", "horarioApertura"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("horarioCierre");
        elemField.setXmlName(new javax.xml.namespace.QName("http://pasarelapago.clienteWS.ipsg.map.es/entidades", "horarioCierre"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tarjeta");
        elemField.setXmlName(new javax.xml.namespace.QName("http://pasarelapago.clienteWS.ipsg.map.es/entidades", "tarjeta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
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
