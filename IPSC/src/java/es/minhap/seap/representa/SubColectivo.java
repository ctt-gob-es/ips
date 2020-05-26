/**
 * SubColectivo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.minhap.seap.representa;

/**
 * El Class SubColectivo.
 */
public class SubColectivo  implements java.io.Serializable {
    
    /** el codigo. */
    private java.lang.String codigo;

    /** el nombre. */
    private java.lang.String nombre;

    /** el estado. */
    private java.lang.String estado;

    /** el convenio. */
    private es.minhap.seap.representa.Convenio convenio;

    /** el tramites representate. */
    private es.minhap.seap.representa.ListaRepresentado[] tramitesRepresentate;

    /**
     * Crea una nueva sub colectivo.
     */
    public SubColectivo() {
    }

    /**
     * Crea una nueva sub colectivo.
     *
     * @param codigo el codigo
     * @param nombre el nombre
     * @param estado el estado
     * @param convenio el convenio
     * @param tramitesRepresentate el tramites representate
     */
    public SubColectivo(
           java.lang.String codigo,
           java.lang.String nombre,
           java.lang.String estado,
           es.minhap.seap.representa.Convenio convenio,
           es.minhap.seap.representa.ListaRepresentado[] tramitesRepresentate) {
           this.codigo = codigo;
           this.nombre = nombre;
           this.estado = estado;
           this.convenio = convenio;
           this.tramitesRepresentate = tramitesRepresentate;
    }


    /**
     * Gets the codigo value for this SubColectivo.
     * 
     * @return codigo
     */
    public java.lang.String getCodigo() {
        return codigo;
    }


    /**
     * Sets the codigo value for this SubColectivo.
     *
     * @param codigo el nuevo codigo
     */
    public void setCodigo(java.lang.String codigo) {
        this.codigo = codigo;
    }


    /**
     * Gets the nombre value for this SubColectivo.
     * 
     * @return nombre
     */
    public java.lang.String getNombre() {
        return nombre;
    }


    /**
     * Sets the nombre value for this SubColectivo.
     *
     * @param nombre el nuevo nombre
     */
    public void setNombre(java.lang.String nombre) {
        this.nombre = nombre;
    }


    /**
     * Gets the estado value for this SubColectivo.
     * 
     * @return estado
     */
    public java.lang.String getEstado() {
        return estado;
    }


    /**
     * Sets the estado value for this SubColectivo.
     *
     * @param estado el nuevo estado
     */
    public void setEstado(java.lang.String estado) {
        this.estado = estado;
    }


    /**
     * Gets the convenio value for this SubColectivo.
     * 
     * @return convenio
     */
    public es.minhap.seap.representa.Convenio getConvenio() {
        return convenio;
    }


    /**
     * Sets the convenio value for this SubColectivo.
     *
     * @param convenio el nuevo convenio
     */
    public void setConvenio(es.minhap.seap.representa.Convenio convenio) {
        this.convenio = convenio;
    }


    /**
     * Gets the tramitesRepresentate value for this SubColectivo.
     * 
     * @return tramitesRepresentate
     */
    public es.minhap.seap.representa.ListaRepresentado[] getTramitesRepresentate() {
        return tramitesRepresentate;
    }


    /**
     * Sets the tramitesRepresentate value for this SubColectivo.
     *
     * @param tramitesRepresentate el nuevo tramites representate
     */
    public void setTramitesRepresentate(es.minhap.seap.representa.ListaRepresentado[] tramitesRepresentate) {
        this.tramitesRepresentate = tramitesRepresentate;
    }

    /** el equals calc. */
    private java.lang.Object __equalsCalc = null;
    
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public synchronized boolean equals(java.lang.Object obj) {
    	if (obj == null) { return false;}
    	if (!(obj instanceof SubColectivo)) return false;
        SubColectivo other = (SubColectivo) obj;
        
        if (this == obj) {return true;}
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals =
            ((this.codigo==null && other.getCodigo()==null) || 
             (this.codigo!=null &&
              this.codigo.equals(other.getCodigo()))) &&
            ((this.nombre==null && other.getNombre()==null) || 
             (this.nombre!=null &&
              this.nombre.equals(other.getNombre()))) &&
            ((this.estado==null && other.getEstado()==null) || 
             (this.estado!=null &&
              this.estado.equals(other.getEstado()))) &&
            ((this.convenio==null && other.getConvenio()==null) || 
             (this.convenio!=null &&
              this.convenio.equals(other.getConvenio()))) &&
            ((this.tramitesRepresentate==null && other.getTramitesRepresentate()==null) || 
             (this.tramitesRepresentate!=null &&
              java.util.Arrays.equals(this.tramitesRepresentate, other.getTramitesRepresentate())));
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
        if (getCodigo() != null) {
            _hashCode += getCodigo().hashCode();
        }
        if (getNombre() != null) {
            _hashCode += getNombre().hashCode();
        }
        if (getEstado() != null) {
            _hashCode += getEstado().hashCode();
        }
        if (getConvenio() != null) {
            _hashCode += getConvenio().hashCode();
        }
        if (getTramitesRepresentate() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTramitesRepresentate());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getTramitesRepresentate(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    /** el type desc. */
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SubColectivo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://representa.seap.minhap.es", "subColectivo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://representa.seap.minhap.es", "codigo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombre");
        elemField.setXmlName(new javax.xml.namespace.QName("http://representa.seap.minhap.es", "nombre"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("estado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://representa.seap.minhap.es", "estado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("convenio");
        elemField.setXmlName(new javax.xml.namespace.QName("http://representa.seap.minhap.es", "convenio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://representa.seap.minhap.es", "convenio"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tramitesRepresentate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://representa.seap.minhap.es", "tramitesRepresentate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://representa.seap.minhap.es", "listaRepresentado"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://representa.seap.minhap.es", "lista"));
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
