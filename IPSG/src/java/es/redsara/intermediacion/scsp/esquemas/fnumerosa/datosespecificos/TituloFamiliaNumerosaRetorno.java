/**
 * TituloFamiliaNumerosaRetorno.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.redsara.intermediacion.scsp.esquemas.fnumerosa.datosespecificos;

/**
 * El Class TituloFamiliaNumerosaRetorno.
 */
public class TituloFamiliaNumerosaRetorno  implements java.io.Serializable {
    
    /** el codigo comunidad autonoma. */
    private es.redsara.intermediacion.scsp.esquemas.fnumerosa.datosespecificos.CodigoComunidadAutonoma codigoComunidadAutonoma;

    /** el numero titulo. */
    private java.lang.String numeroTitulo;

    /** el categoria. */
    private es.redsara.intermediacion.scsp.esquemas.fnumerosa.datosespecificos.Categoria categoria;

    /** el titulo vigente. */
    private es.redsara.intermediacion.scsp.esquemas.fnumerosa.datosespecificos.TituloVigente tituloVigente;

    /** el fecha expedicion. */
    private java.lang.String fechaExpedicion;

    /** el fecha caducidad. */
    private java.lang.String fechaCaducidad;

    /** el numero hijos. */
    private java.math.BigInteger numeroHijos;

    /**
     * Crea una nueva titulo familia numerosa retorno.
     */
    public TituloFamiliaNumerosaRetorno() {
    }

    /**
     * Crea una nueva titulo familia numerosa retorno.
     *
     * @param codigoComunidadAutonoma el codigo comunidad autonoma
     * @param numeroTitulo el numero titulo
     * @param categoria el categoria
     * @param tituloVigente el titulo vigente
     * @param fechaExpedicion el fecha expedicion
     * @param fechaCaducidad el fecha caducidad
     * @param numeroHijos el numero hijos
     */
    public TituloFamiliaNumerosaRetorno(
           es.redsara.intermediacion.scsp.esquemas.fnumerosa.datosespecificos.CodigoComunidadAutonoma codigoComunidadAutonoma,
           java.lang.String numeroTitulo,
           es.redsara.intermediacion.scsp.esquemas.fnumerosa.datosespecificos.Categoria categoria,
           es.redsara.intermediacion.scsp.esquemas.fnumerosa.datosespecificos.TituloVigente tituloVigente,
           java.lang.String fechaExpedicion,
           java.lang.String fechaCaducidad,
           java.math.BigInteger numeroHijos) {
           this.codigoComunidadAutonoma = codigoComunidadAutonoma;
           this.numeroTitulo = numeroTitulo;
           this.categoria = categoria;
           this.tituloVigente = tituloVigente;
           this.fechaExpedicion = fechaExpedicion;
           this.fechaCaducidad = fechaCaducidad;
           this.numeroHijos = numeroHijos;
    }


    /**
     * Gets the codigoComunidadAutonoma value for this TituloFamiliaNumerosaRetorno.
     * 
     * @return codigoComunidadAutonoma
     */
    public es.redsara.intermediacion.scsp.esquemas.fnumerosa.datosespecificos.CodigoComunidadAutonoma getCodigoComunidadAutonoma() {
        return codigoComunidadAutonoma;
    }


    /**
     * Sets the codigoComunidadAutonoma value for this TituloFamiliaNumerosaRetorno.
     *
     * @param codigoComunidadAutonoma el nuevo codigo comunidad autonoma
     */
    public void setCodigoComunidadAutonoma(es.redsara.intermediacion.scsp.esquemas.fnumerosa.datosespecificos.CodigoComunidadAutonoma codigoComunidadAutonoma) {
        this.codigoComunidadAutonoma = codigoComunidadAutonoma;
    }


    /**
     * Gets the numeroTitulo value for this TituloFamiliaNumerosaRetorno.
     * 
     * @return numeroTitulo
     */
    public java.lang.String getNumeroTitulo() {
        return numeroTitulo;
    }


    /**
     * Sets the numeroTitulo value for this TituloFamiliaNumerosaRetorno.
     *
     * @param numeroTitulo el nuevo numero titulo
     */
    public void setNumeroTitulo(java.lang.String numeroTitulo) {
        this.numeroTitulo = numeroTitulo;
    }


    /**
     * Gets the categoria value for this TituloFamiliaNumerosaRetorno.
     * 
     * @return categoria
     */
    public es.redsara.intermediacion.scsp.esquemas.fnumerosa.datosespecificos.Categoria getCategoria() {
        return categoria;
    }


    /**
     * Sets the categoria value for this TituloFamiliaNumerosaRetorno.
     *
     * @param categoria el nuevo categoria
     */
    public void setCategoria(es.redsara.intermediacion.scsp.esquemas.fnumerosa.datosespecificos.Categoria categoria) {
        this.categoria = categoria;
    }


    /**
     * Gets the tituloVigente value for this TituloFamiliaNumerosaRetorno.
     * 
     * @return tituloVigente
     */
    public es.redsara.intermediacion.scsp.esquemas.fnumerosa.datosespecificos.TituloVigente getTituloVigente() {
        return tituloVigente;
    }


    /**
     * Sets the tituloVigente value for this TituloFamiliaNumerosaRetorno.
     *
     * @param tituloVigente el nuevo titulo vigente
     */
    public void setTituloVigente(es.redsara.intermediacion.scsp.esquemas.fnumerosa.datosespecificos.TituloVigente tituloVigente) {
        this.tituloVigente = tituloVigente;
    }


    /**
     * Gets the fechaExpedicion value for this TituloFamiliaNumerosaRetorno.
     * 
     * @return fechaExpedicion
     */
    public java.lang.String getFechaExpedicion() {
        return fechaExpedicion;
    }


    /**
     * Sets the fechaExpedicion value for this TituloFamiliaNumerosaRetorno.
     *
     * @param fechaExpedicion el nuevo fecha expedicion
     */
    public void setFechaExpedicion(java.lang.String fechaExpedicion) {
        this.fechaExpedicion = fechaExpedicion;
    }


    /**
     * Gets the fechaCaducidad value for this TituloFamiliaNumerosaRetorno.
     * 
     * @return fechaCaducidad
     */
    public java.lang.String getFechaCaducidad() {
        return fechaCaducidad;
    }


    /**
     * Sets the fechaCaducidad value for this TituloFamiliaNumerosaRetorno.
     *
     * @param fechaCaducidad el nuevo fecha caducidad
     */
    public void setFechaCaducidad(java.lang.String fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }


    /**
     * Gets the numeroHijos value for this TituloFamiliaNumerosaRetorno.
     * 
     * @return numeroHijos
     */
    public java.math.BigInteger getNumeroHijos() {
        return numeroHijos;
    }


    /**
     * Sets the numeroHijos value for this TituloFamiliaNumerosaRetorno.
     *
     * @param numeroHijos el nuevo numero hijos
     */
    public void setNumeroHijos(java.math.BigInteger numeroHijos) {
        this.numeroHijos = numeroHijos;
    }

    /** el equals calc. */
    private java.lang.Object __equalsCalc = null;
    
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TituloFamiliaNumerosaRetorno)) return false;
        TituloFamiliaNumerosaRetorno other = (TituloFamiliaNumerosaRetorno) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.codigoComunidadAutonoma==null && other.getCodigoComunidadAutonoma()==null) || 
             (this.codigoComunidadAutonoma!=null &&
              this.codigoComunidadAutonoma.equals(other.getCodigoComunidadAutonoma()))) &&
            ((this.numeroTitulo==null && other.getNumeroTitulo()==null) || 
             (this.numeroTitulo!=null &&
              this.numeroTitulo.equals(other.getNumeroTitulo()))) &&
            ((this.categoria==null && other.getCategoria()==null) || 
             (this.categoria!=null &&
              this.categoria.equals(other.getCategoria()))) &&
            ((this.tituloVigente==null && other.getTituloVigente()==null) || 
             (this.tituloVigente!=null &&
              this.tituloVigente.equals(other.getTituloVigente()))) &&
            ((this.fechaExpedicion==null && other.getFechaExpedicion()==null) || 
             (this.fechaExpedicion!=null &&
              this.fechaExpedicion.equals(other.getFechaExpedicion()))) &&
            ((this.fechaCaducidad==null && other.getFechaCaducidad()==null) || 
             (this.fechaCaducidad!=null &&
              this.fechaCaducidad.equals(other.getFechaCaducidad()))) &&
            ((this.numeroHijos==null && other.getNumeroHijos()==null) || 
             (this.numeroHijos!=null &&
              this.numeroHijos.equals(other.getNumeroHijos())));
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
        if (getCodigoComunidadAutonoma() != null) {
            _hashCode += getCodigoComunidadAutonoma().hashCode();
        }
        if (getNumeroTitulo() != null) {
            _hashCode += getNumeroTitulo().hashCode();
        }
        if (getCategoria() != null) {
            _hashCode += getCategoria().hashCode();
        }
        if (getTituloVigente() != null) {
            _hashCode += getTituloVigente().hashCode();
        }
        if (getFechaExpedicion() != null) {
            _hashCode += getFechaExpedicion().hashCode();
        }
        if (getFechaCaducidad() != null) {
            _hashCode += getFechaCaducidad().hashCode();
        }
        if (getNumeroHijos() != null) {
            _hashCode += getNumeroHijos().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    /** el type desc. */
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TituloFamiliaNumerosaRetorno.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">TituloFamiliaNumerosaRetorno"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoComunidadAutonoma");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "CodigoComunidadAutonoma"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">CodigoComunidadAutonoma"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroTitulo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "NumeroTitulo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">NumeroTitulo"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("categoria");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "Categoria"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">Categoria"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tituloVigente");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "TituloVigente"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">TituloVigente"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaExpedicion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "FechaExpedicion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "Fecha"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaCaducidad");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "FechaCaducidad"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "Fecha"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroHijos");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "NumeroHijos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">NumeroHijos"));
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
