/**
 * Colectivo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.minhap.seap.representa;

/**
 * El Class Colectivo.
 */
public class Colectivo  implements java.io.Serializable {
    
    /** el error. */
    private es.minhap.seap.representa.Error error;

    /** el id. */
    private java.lang.String id;

    /** el nombre. */
    private java.lang.String nombre;

    /** el codigo. */
    private java.lang.String codigo;

    /** el pertenece. */
    private java.lang.Boolean pertenece;

    /** el estado. */
    private java.lang.String estado;

    /** el convenio. */
    private es.minhap.seap.representa.Convenio convenio;

    /** el tramites representante. */
    private es.minhap.seap.representa.ListaRepresentado[] tramitesRepresentante;

    /** el sub colectivos. */
    private es.minhap.seap.representa.SubColectivo[] subColectivos;

    /**
     * Crea una nueva colectivo.
     */
    public Colectivo() {
    }

    /**
     * Crea una nueva colectivo.
     *
     * @param error el error
     * @param id el id
     * @param nombre el nombre
     * @param codigo el codigo
     * @param pertenece el pertenece
     * @param estado el estado
     * @param convenio el convenio
     * @param tramitesRepresentante el tramites representante
     * @param subColectivos el sub colectivos
     */
    public Colectivo(
           es.minhap.seap.representa.Error error,
           java.lang.String id,
           java.lang.String nombre,
           java.lang.String codigo,
           java.lang.Boolean pertenece,
           java.lang.String estado,
           es.minhap.seap.representa.Convenio convenio,
           es.minhap.seap.representa.ListaRepresentado[] tramitesRepresentante,
           es.minhap.seap.representa.SubColectivo[] subColectivos) {
           this.error = error;
           this.id = id;
           this.nombre = nombre;
           this.codigo = codigo;
           this.pertenece = pertenece;
           this.estado = estado;
           this.convenio = convenio;
           this.tramitesRepresentante = tramitesRepresentante;
           this.subColectivos = subColectivos;
    }


    /**
     * Gets the error value for this Colectivo.
     * 
     * @return error
     */
    public es.minhap.seap.representa.Error getError() {
        return error;
    }


    /**
     * Sets the error value for this Colectivo.
     *
     * @param error el nuevo error
     */
    public void setError(es.minhap.seap.representa.Error error) {
        this.error = error;
    }


    /**
     * Gets the id value for this Colectivo.
     * 
     * @return id
     */
    public java.lang.String getId() {
        return id;
    }


    /**
     * Sets the id value for this Colectivo.
     *
     * @param id el nuevo id
     */
    public void setId(java.lang.String id) {
        this.id = id;
    }


    /**
     * Gets the nombre value for this Colectivo.
     * 
     * @return nombre
     */
    public java.lang.String getNombre() {
        return nombre;
    }


    /**
     * Sets the nombre value for this Colectivo.
     *
     * @param nombre el nuevo nombre
     */
    public void setNombre(java.lang.String nombre) {
        this.nombre = nombre;
    }


    /**
     * Gets the codigo value for this Colectivo.
     * 
     * @return codigo
     */
    public java.lang.String getCodigo() {
        return codigo;
    }


    /**
     * Sets the codigo value for this Colectivo.
     *
     * @param codigo el nuevo codigo
     */
    public void setCodigo(java.lang.String codigo) {
        this.codigo = codigo;
    }


    /**
     * Gets the pertenece value for this Colectivo.
     * 
     * @return pertenece
     */
    public java.lang.Boolean getPertenece() {
        return pertenece;
    }


    /**
     * Sets the pertenece value for this Colectivo.
     *
     * @param pertenece el nuevo pertenece
     */
    public void setPertenece(java.lang.Boolean pertenece) {
        this.pertenece = pertenece;
    }


    /**
     * Gets the estado value for this Colectivo.
     * 
     * @return estado
     */
    public java.lang.String getEstado() {
        return estado;
    }


    /**
     * Sets the estado value for this Colectivo.
     *
     * @param estado el nuevo estado
     */
    public void setEstado(java.lang.String estado) {
        this.estado = estado;
    }


    /**
     * Gets the convenio value for this Colectivo.
     * 
     * @return convenio
     */
    public es.minhap.seap.representa.Convenio getConvenio() {
        return convenio;
    }


    /**
     * Sets the convenio value for this Colectivo.
     *
     * @param convenio el nuevo convenio
     */
    public void setConvenio(es.minhap.seap.representa.Convenio convenio) {
        this.convenio = convenio;
    }


    /**
     * Gets the tramitesRepresentante value for this Colectivo.
     * 
     * @return tramitesRepresentante
     */
    public es.minhap.seap.representa.ListaRepresentado[] getTramitesRepresentante() {
        return tramitesRepresentante;
    }


    /**
     * Sets the tramitesRepresentante value for this Colectivo.
     *
     * @param tramitesRepresentante el nuevo tramites representante
     */
    public void setTramitesRepresentante(es.minhap.seap.representa.ListaRepresentado[] tramitesRepresentante) {
        this.tramitesRepresentante = tramitesRepresentante;
    }


    /**
     * Gets the subColectivos value for this Colectivo.
     * 
     * @return subColectivos
     */
    public es.minhap.seap.representa.SubColectivo[] getSubColectivos() {
        return subColectivos;
    }


    /**
     * Sets the subColectivos value for this Colectivo.
     *
     * @param subColectivos el nuevo sub colectivos
     */
    public void setSubColectivos(es.minhap.seap.representa.SubColectivo[] subColectivos) {
        this.subColectivos = subColectivos;
    }

    /** el equals calc. */
    private java.lang.Object __equalsCalc = null;
    
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Colectivo)) return false;
        Colectivo other = (Colectivo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.error==null && other.getError()==null) || 
             (this.error!=null &&
              this.error.equals(other.getError()))) &&
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.nombre==null && other.getNombre()==null) || 
             (this.nombre!=null &&
              this.nombre.equals(other.getNombre()))) &&
            ((this.codigo==null && other.getCodigo()==null) || 
             (this.codigo!=null &&
              this.codigo.equals(other.getCodigo()))) &&
            ((this.pertenece==null && other.getPertenece()==null) || 
             (this.pertenece!=null &&
              this.pertenece.equals(other.getPertenece()))) &&
            ((this.estado==null && other.getEstado()==null) || 
             (this.estado!=null &&
              this.estado.equals(other.getEstado()))) &&
            ((this.convenio==null && other.getConvenio()==null) || 
             (this.convenio!=null &&
              this.convenio.equals(other.getConvenio()))) &&
            ((this.tramitesRepresentante==null && other.getTramitesRepresentante()==null) || 
             (this.tramitesRepresentante!=null &&
              java.util.Arrays.equals(this.tramitesRepresentante, other.getTramitesRepresentante()))) &&
            ((this.subColectivos==null && other.getSubColectivos()==null) || 
             (this.subColectivos!=null &&
              java.util.Arrays.equals(this.subColectivos, other.getSubColectivos())));
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
        if (getError() != null) {
            _hashCode += getError().hashCode();
        }
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getNombre() != null) {
            _hashCode += getNombre().hashCode();
        }
        if (getCodigo() != null) {
            _hashCode += getCodigo().hashCode();
        }
        if (getPertenece() != null) {
            _hashCode += getPertenece().hashCode();
        }
        if (getEstado() != null) {
            _hashCode += getEstado().hashCode();
        }
        if (getConvenio() != null) {
            _hashCode += getConvenio().hashCode();
        }
        if (getTramitesRepresentante() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTramitesRepresentante());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getTramitesRepresentante(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getSubColectivos() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getSubColectivos());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getSubColectivos(), i);
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
        new org.apache.axis.description.TypeDesc(Colectivo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://representa.seap.minhap.es", "colectivo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("error");
        elemField.setXmlName(new javax.xml.namespace.QName("http://representa.seap.minhap.es", "error"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://representa.seap.minhap.es", "error"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://representa.seap.minhap.es", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombre");
        elemField.setXmlName(new javax.xml.namespace.QName("http://representa.seap.minhap.es", "nombre"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://representa.seap.minhap.es", "codigo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pertenece");
        elemField.setXmlName(new javax.xml.namespace.QName("http://representa.seap.minhap.es", "pertenece"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
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
        elemField.setFieldName("tramitesRepresentante");
        elemField.setXmlName(new javax.xml.namespace.QName("http://representa.seap.minhap.es", "tramitesRepresentante"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://representa.seap.minhap.es", "listaRepresentado"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://representa.seap.minhap.es", "lista"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("subColectivos");
        elemField.setXmlName(new javax.xml.namespace.QName("http://representa.seap.minhap.es", "subColectivos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://representa.seap.minhap.es", "subColectivo"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://representa.seap.minhap.es", "subColectivo"));
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
