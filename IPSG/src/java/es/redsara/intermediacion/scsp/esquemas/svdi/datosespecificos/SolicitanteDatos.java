/**
 * SolicitanteDatos.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.redsara.intermediacion.scsp.esquemas.svdi.datosespecificos;

/**
 * El Class SolicitanteDatos.
 */
public class SolicitanteDatos  implements java.io.Serializable {
    
    /** el tipo. */
    private es.redsara.intermediacion.scsp.esquemas.svdi.datosespecificos.Tipo tipo;

    /** el nombre. */
    private java.lang.String nombre;

    /** el apellido 1. */
    private java.lang.String apellido1;

    /** el apellido 2. */
    private java.lang.String apellido2;

    /** el id funcionario. */
    private java.lang.String idFuncionario;

    /** el num funcionario. */
    private java.lang.String numFuncionario;

    /** el telefono. */
    private java.lang.String telefono;

    /** el organizacion. */
    private es.redsara.intermediacion.scsp.esquemas.svdi.datosespecificos.Organizacion organizacion;

    /**
     * Crea una nueva solicitante datos.
     */
    public SolicitanteDatos() {
    }

    /**
     * Crea una nueva solicitante datos.
     *
     * @param tipo el tipo
     * @param nombre el nombre
     * @param apellido1 el apellido 1
     * @param apellido2 el apellido 2
     * @param idFuncionario el id funcionario
     * @param numFuncionario el num funcionario
     * @param telefono el telefono
     * @param organizacion el organizacion
     */
    public SolicitanteDatos(
           es.redsara.intermediacion.scsp.esquemas.svdi.datosespecificos.Tipo tipo,
           java.lang.String nombre,
           java.lang.String apellido1,
           java.lang.String apellido2,
           java.lang.String idFuncionario,
           java.lang.String numFuncionario,
           java.lang.String telefono,
           es.redsara.intermediacion.scsp.esquemas.svdi.datosespecificos.Organizacion organizacion) {
           this.tipo = tipo;
           this.nombre = nombre;
           this.apellido1 = apellido1;
           this.apellido2 = apellido2;
           this.idFuncionario = idFuncionario;
           this.numFuncionario = numFuncionario;
           this.telefono = telefono;
           this.organizacion = organizacion;
    }


    /**
     * Gets the tipo value for this SolicitanteDatos.
     * 
     * @return tipo
     */
    public es.redsara.intermediacion.scsp.esquemas.svdi.datosespecificos.Tipo getTipo() {
        return tipo;
    }


    /**
     * Sets the tipo value for this SolicitanteDatos.
     *
     * @param tipo el nuevo tipo
     */
    public void setTipo(es.redsara.intermediacion.scsp.esquemas.svdi.datosespecificos.Tipo tipo) {
        this.tipo = tipo;
    }


    /**
     * Gets the nombre value for this SolicitanteDatos.
     * 
     * @return nombre
     */
    public java.lang.String getNombre() {
        return nombre;
    }


    /**
     * Sets the nombre value for this SolicitanteDatos.
     *
     * @param nombre el nuevo nombre
     */
    public void setNombre(java.lang.String nombre) {
        this.nombre = nombre;
    }


    /**
     * Gets the apellido1 value for this SolicitanteDatos.
     * 
     * @return apellido1
     */
    public java.lang.String getApellido1() {
        return apellido1;
    }


    /**
     * Sets the apellido1 value for this SolicitanteDatos.
     *
     * @param apellido1 el nuevo apellido 1
     */
    public void setApellido1(java.lang.String apellido1) {
        this.apellido1 = apellido1;
    }


    /**
     * Gets the apellido2 value for this SolicitanteDatos.
     * 
     * @return apellido2
     */
    public java.lang.String getApellido2() {
        return apellido2;
    }


    /**
     * Sets the apellido2 value for this SolicitanteDatos.
     *
     * @param apellido2 el nuevo apellido 2
     */
    public void setApellido2(java.lang.String apellido2) {
        this.apellido2 = apellido2;
    }


    /**
     * Gets the idFuncionario value for this SolicitanteDatos.
     * 
     * @return idFuncionario
     */
    public java.lang.String getIdFuncionario() {
        return idFuncionario;
    }


    /**
     * Sets the idFuncionario value for this SolicitanteDatos.
     *
     * @param idFuncionario el nuevo id funcionario
     */
    public void setIdFuncionario(java.lang.String idFuncionario) {
        this.idFuncionario = idFuncionario;
    }


    /**
     * Gets the numFuncionario value for this SolicitanteDatos.
     * 
     * @return numFuncionario
     */
    public java.lang.String getNumFuncionario() {
        return numFuncionario;
    }


    /**
     * Sets the numFuncionario value for this SolicitanteDatos.
     *
     * @param numFuncionario el nuevo num funcionario
     */
    public void setNumFuncionario(java.lang.String numFuncionario) {
        this.numFuncionario = numFuncionario;
    }


    /**
     * Gets the telefono value for this SolicitanteDatos.
     * 
     * @return telefono
     */
    public java.lang.String getTelefono() {
        return telefono;
    }


    /**
     * Sets the telefono value for this SolicitanteDatos.
     *
     * @param telefono el nuevo telefono
     */
    public void setTelefono(java.lang.String telefono) {
        this.telefono = telefono;
    }


    /**
     * Gets the organizacion value for this SolicitanteDatos.
     * 
     * @return organizacion
     */
    public es.redsara.intermediacion.scsp.esquemas.svdi.datosespecificos.Organizacion getOrganizacion() {
        return organizacion;
    }


    /**
     * Sets the organizacion value for this SolicitanteDatos.
     *
     * @param organizacion el nuevo organizacion
     */
    public void setOrganizacion(es.redsara.intermediacion.scsp.esquemas.svdi.datosespecificos.Organizacion organizacion) {
        this.organizacion = organizacion;
    }

    /** el equals calc. */
    private java.lang.Object __equalsCalc = null;
    
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SolicitanteDatos)) return false;
        SolicitanteDatos other = (SolicitanteDatos) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.tipo==null && other.getTipo()==null) || 
             (this.tipo!=null &&
              this.tipo.equals(other.getTipo()))) &&
            ((this.nombre==null && other.getNombre()==null) || 
             (this.nombre!=null &&
              this.nombre.equals(other.getNombre()))) &&
            ((this.apellido1==null && other.getApellido1()==null) || 
             (this.apellido1!=null &&
              this.apellido1.equals(other.getApellido1()))) &&
            ((this.apellido2==null && other.getApellido2()==null) || 
             (this.apellido2!=null &&
              this.apellido2.equals(other.getApellido2()))) &&
            ((this.idFuncionario==null && other.getIdFuncionario()==null) || 
             (this.idFuncionario!=null &&
              this.idFuncionario.equals(other.getIdFuncionario()))) &&
            ((this.numFuncionario==null && other.getNumFuncionario()==null) || 
             (this.numFuncionario!=null &&
              this.numFuncionario.equals(other.getNumFuncionario()))) &&
            ((this.telefono==null && other.getTelefono()==null) || 
             (this.telefono!=null &&
              this.telefono.equals(other.getTelefono()))) &&
            ((this.organizacion==null && other.getOrganizacion()==null) || 
             (this.organizacion!=null &&
              this.organizacion.equals(other.getOrganizacion())));
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
        if (getTipo() != null) {
            _hashCode += getTipo().hashCode();
        }
        if (getNombre() != null) {
            _hashCode += getNombre().hashCode();
        }
        if (getApellido1() != null) {
            _hashCode += getApellido1().hashCode();
        }
        if (getApellido2() != null) {
            _hashCode += getApellido2().hashCode();
        }
        if (getIdFuncionario() != null) {
            _hashCode += getIdFuncionario().hashCode();
        }
        if (getNumFuncionario() != null) {
            _hashCode += getNumFuncionario().hashCode();
        }
        if (getTelefono() != null) {
            _hashCode += getTelefono().hashCode();
        }
        if (getOrganizacion() != null) {
            _hashCode += getOrganizacion().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    /** el type desc. */
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SolicitanteDatos.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.map.es/scsp/esquemas/datosespecificos", ">SolicitanteDatos"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/scsp/esquemas/datosespecificos", "Tipo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/scsp/esquemas/datosespecificos", ">Tipo"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombre");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/scsp/esquemas/datosespecificos", "Nombre"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/scsp/esquemas/datosespecificos", ">Nombre"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("apellido1");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/scsp/esquemas/datosespecificos", "Apellido1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/scsp/esquemas/datosespecificos", ">Apellido1"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("apellido2");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/scsp/esquemas/datosespecificos", "Apellido2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/scsp/esquemas/datosespecificos", ">Apellido2"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idFuncionario");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/scsp/esquemas/datosespecificos", "IdFuncionario"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/scsp/esquemas/datosespecificos", ">IdFuncionario"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numFuncionario");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/scsp/esquemas/datosespecificos", "NumFuncionario"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/scsp/esquemas/datosespecificos", ">NumFuncionario"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("telefono");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/scsp/esquemas/datosespecificos", "Telefono"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/scsp/esquemas/datosespecificos", ">Telefono"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("organizacion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.map.es/scsp/esquemas/datosespecificos", "Organizacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.map.es/scsp/esquemas/datosespecificos", ">Organizacion"));
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
