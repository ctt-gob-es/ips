/**
 * DefaultDatosNRCIn.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ePago.gob.es.schemas;

/**
 * El Class DefaultDatosNRCIn.
 */
public class DefaultDatosNRCIn  implements java.io.Serializable {
    
    /** el nrc. */
    private java.lang.String NRC;

    /** el apellido 1. */
    private java.lang.String apellido1;

    /** el apellido 2. */
    private java.lang.String apellido2;

    /** el codigo banco. */
    private java.lang.String codigoBanco;

    /** el documento obligado. */
    private java.lang.String documentoObligado;

    /** el documento pagador. */
    private java.lang.String documentoPagador;

    /** el fecha operacion. */
    private java.util.Calendar fechaOperacion;

    /** el importe operacion. */
    private double importeOperacion;

    /** el nombre. */
    private java.lang.String nombre;

    /** el tipo documento obligado. */
    private ePago.gob.es.schemas.TiposDocumento tipoDocumentoObligado;

    /**
     * Crea una nueva default datos NRC in.
     */
    public DefaultDatosNRCIn() {
    }

    /**
     * Crea una nueva default datos NRC in.
     *
     * @param NRC el nrc
     * @param apellido1 el apellido 1
     * @param apellido2 el apellido 2
     * @param codigoBanco el codigo banco
     * @param documentoObligado el documento obligado
     * @param documentoPagador el documento pagador
     * @param fechaOperacion el fecha operacion
     * @param importeOperacion el importe operacion
     * @param nombre el nombre
     * @param tipoDocumentoObligado el tipo documento obligado
     */
    public DefaultDatosNRCIn(
           java.lang.String NRC,
           java.lang.String apellido1,
           java.lang.String apellido2,
           java.lang.String codigoBanco,
           java.lang.String documentoObligado,
           java.lang.String documentoPagador,
           java.util.Calendar fechaOperacion,
           double importeOperacion,
           java.lang.String nombre,
           ePago.gob.es.schemas.TiposDocumento tipoDocumentoObligado) {
           this.NRC = NRC;
           this.apellido1 = apellido1;
           this.apellido2 = apellido2;
           this.codigoBanco = codigoBanco;
           this.documentoObligado = documentoObligado;
           this.documentoPagador = documentoPagador;
           this.fechaOperacion = fechaOperacion;
           this.importeOperacion = importeOperacion;
           this.nombre = nombre;
           this.tipoDocumentoObligado = tipoDocumentoObligado;
    }


    /**
     * Gets the NRC value for this DefaultDatosNRCIn.
     * 
     * @return NRC
     */
    public java.lang.String getNRC() {
        return NRC;
    }


    /**
     * Sets the NRC value for this DefaultDatosNRCIn.
     *
     * @param NRC el nuevo nrc
     */
    public void setNRC(java.lang.String NRC) {
        this.NRC = NRC;
    }


    /**
     * Gets the apellido1 value for this DefaultDatosNRCIn.
     * 
     * @return apellido1
     */
    public java.lang.String getApellido1() {
        return apellido1;
    }


    /**
     * Sets the apellido1 value for this DefaultDatosNRCIn.
     *
     * @param apellido1 el nuevo apellido 1
     */
    public void setApellido1(java.lang.String apellido1) {
        this.apellido1 = apellido1;
    }


    /**
     * Gets the apellido2 value for this DefaultDatosNRCIn.
     * 
     * @return apellido2
     */
    public java.lang.String getApellido2() {
        return apellido2;
    }


    /**
     * Sets the apellido2 value for this DefaultDatosNRCIn.
     *
     * @param apellido2 el nuevo apellido 2
     */
    public void setApellido2(java.lang.String apellido2) {
        this.apellido2 = apellido2;
    }


    /**
     * Gets the codigoBanco value for this DefaultDatosNRCIn.
     * 
     * @return codigoBanco
     */
    public java.lang.String getCodigoBanco() {
        return codigoBanco;
    }


    /**
     * Sets the codigoBanco value for this DefaultDatosNRCIn.
     *
     * @param codigoBanco el nuevo codigo banco
     */
    public void setCodigoBanco(java.lang.String codigoBanco) {
        this.codigoBanco = codigoBanco;
    }


    /**
     * Gets the documentoObligado value for this DefaultDatosNRCIn.
     * 
     * @return documentoObligado
     */
    public java.lang.String getDocumentoObligado() {
        return documentoObligado;
    }


    /**
     * Sets the documentoObligado value for this DefaultDatosNRCIn.
     *
     * @param documentoObligado el nuevo documento obligado
     */
    public void setDocumentoObligado(java.lang.String documentoObligado) {
        this.documentoObligado = documentoObligado;
    }


    /**
     * Gets the documentoPagador value for this DefaultDatosNRCIn.
     * 
     * @return documentoPagador
     */
    public java.lang.String getDocumentoPagador() {
        return documentoPagador;
    }


    /**
     * Sets the documentoPagador value for this DefaultDatosNRCIn.
     *
     * @param documentoPagador el nuevo documento pagador
     */
    public void setDocumentoPagador(java.lang.String documentoPagador) {
        this.documentoPagador = documentoPagador;
    }


    /**
     * Gets the fechaOperacion value for this DefaultDatosNRCIn.
     * 
     * @return fechaOperacion
     */
    public java.util.Calendar getFechaOperacion() {
        return fechaOperacion;
    }


    /**
     * Sets the fechaOperacion value for this DefaultDatosNRCIn.
     *
     * @param fechaOperacion el nuevo fecha operacion
     */
    public void setFechaOperacion(java.util.Calendar fechaOperacion) {
        this.fechaOperacion = fechaOperacion;
    }


    /**
     * Gets the importeOperacion value for this DefaultDatosNRCIn.
     * 
     * @return importeOperacion
     */
    public double getImporteOperacion() {
        return importeOperacion;
    }


    /**
     * Sets the importeOperacion value for this DefaultDatosNRCIn.
     *
     * @param importeOperacion el nuevo importe operacion
     */
    public void setImporteOperacion(double importeOperacion) {
        this.importeOperacion = importeOperacion;
    }


    /**
     * Gets the nombre value for this DefaultDatosNRCIn.
     * 
     * @return nombre
     */
    public java.lang.String getNombre() {
        return nombre;
    }


    /**
     * Sets the nombre value for this DefaultDatosNRCIn.
     *
     * @param nombre el nuevo nombre
     */
    public void setNombre(java.lang.String nombre) {
        this.nombre = nombre;
    }


    /**
     * Gets the tipoDocumentoObligado value for this DefaultDatosNRCIn.
     * 
     * @return tipoDocumentoObligado
     */
    public ePago.gob.es.schemas.TiposDocumento getTipoDocumentoObligado() {
        return tipoDocumentoObligado;
    }


    /**
     * Sets the tipoDocumentoObligado value for this DefaultDatosNRCIn.
     *
     * @param tipoDocumentoObligado el nuevo tipo documento obligado
     */
    public void setTipoDocumentoObligado(ePago.gob.es.schemas.TiposDocumento tipoDocumentoObligado) {
        this.tipoDocumentoObligado = tipoDocumentoObligado;
    }

    /** el equals calc. */
    private java.lang.Object __equalsCalc = null;
    
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DefaultDatosNRCIn)) return false;
        DefaultDatosNRCIn other = (DefaultDatosNRCIn) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.NRC==null && other.getNRC()==null) || 
             (this.NRC!=null &&
              this.NRC.equals(other.getNRC()))) &&
            ((this.apellido1==null && other.getApellido1()==null) || 
             (this.apellido1!=null &&
              this.apellido1.equals(other.getApellido1()))) &&
            ((this.apellido2==null && other.getApellido2()==null) || 
             (this.apellido2!=null &&
              this.apellido2.equals(other.getApellido2()))) &&
            ((this.codigoBanco==null && other.getCodigoBanco()==null) || 
             (this.codigoBanco!=null &&
              this.codigoBanco.equals(other.getCodigoBanco()))) &&
            ((this.documentoObligado==null && other.getDocumentoObligado()==null) || 
             (this.documentoObligado!=null &&
              this.documentoObligado.equals(other.getDocumentoObligado()))) &&
            ((this.documentoPagador==null && other.getDocumentoPagador()==null) || 
             (this.documentoPagador!=null &&
              this.documentoPagador.equals(other.getDocumentoPagador()))) &&
            ((this.fechaOperacion==null && other.getFechaOperacion()==null) || 
             (this.fechaOperacion!=null &&
              this.fechaOperacion.equals(other.getFechaOperacion()))) &&
            this.importeOperacion == other.getImporteOperacion() &&
            ((this.nombre==null && other.getNombre()==null) || 
             (this.nombre!=null &&
              this.nombre.equals(other.getNombre()))) &&
            ((this.tipoDocumentoObligado==null && other.getTipoDocumentoObligado()==null) || 
             (this.tipoDocumentoObligado!=null &&
              this.tipoDocumentoObligado.equals(other.getTipoDocumentoObligado())));
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
        if (getNRC() != null) {
            _hashCode += getNRC().hashCode();
        }
        if (getApellido1() != null) {
            _hashCode += getApellido1().hashCode();
        }
        if (getApellido2() != null) {
            _hashCode += getApellido2().hashCode();
        }
        if (getCodigoBanco() != null) {
            _hashCode += getCodigoBanco().hashCode();
        }
        if (getDocumentoObligado() != null) {
            _hashCode += getDocumentoObligado().hashCode();
        }
        if (getDocumentoPagador() != null) {
            _hashCode += getDocumentoPagador().hashCode();
        }
        if (getFechaOperacion() != null) {
            _hashCode += getFechaOperacion().hashCode();
        }
        _hashCode += new Double(getImporteOperacion()).hashCode();
        if (getNombre() != null) {
            _hashCode += getNombre().hashCode();
        }
        if (getTipoDocumentoObligado() != null) {
            _hashCode += getTipoDocumentoObligado().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    /** el type desc. */
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DefaultDatosNRCIn.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "DefaultDatosNRCIn"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NRC");
        elemField.setXmlName(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "NRC"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("apellido1");
        elemField.setXmlName(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "apellido1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("apellido2");
        elemField.setXmlName(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "apellido2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoBanco");
        elemField.setXmlName(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "codigoBanco"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("documentoObligado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "documentoObligado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("documentoPagador");
        elemField.setXmlName(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "documentoPagador"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaOperacion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "fechaOperacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("importeOperacion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "importeOperacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombre");
        elemField.setXmlName(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "nombre"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoDocumentoObligado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "tipoDocumentoObligado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "TiposDocumento"));
        elemField.setNillable(true);
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
