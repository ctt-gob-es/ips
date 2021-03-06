/**
 * DatosTitulo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.redsara.intermediacion.scsp.esquemas.educacionnouniv.datosespecificos;

/**
 * El Class DatosTitulo.
 */
public class DatosTitulo  implements java.io.Serializable {
    
    /** el codigo titulacion. */
    private java.lang.String codigoTitulacion;

    /** el titulacion. */
    private java.lang.String titulacion;

    /** el codigo tipo titulo. */
    private java.lang.String codigoTipoTitulo;

    /** el tipo titulo. */
    private java.lang.String tipoTitulo;

    /** el tipo estudio. */
    private java.lang.String tipoEstudio;

    /** el nivel. */
    private java.lang.String nivel;

    /** el fecha finalizacion. */
    private java.lang.String fechaFinalizacion;

    /** el fecha expedicion. */
    private java.lang.String fechaExpedicion;

    /** el codigo pais expedicion. */
    private java.lang.String codigoPaisExpedicion;

    /** el pais expedicion. */
    private java.lang.String paisExpedicion;

    /** el numero registro autonomico. */
    private java.lang.String numeroRegistroAutonomico;

    /** el numero registro mec. */
    private java.lang.String numeroRegistroMec;

    /** el registro. */
    private es.redsara.intermediacion.scsp.esquemas.educacionnouniv.datosespecificos.Registro registro;

    /**
     * Crea una nueva datos titulo.
     */
    public DatosTitulo() {
    }

    /**
     * Crea una nueva datos titulo.
     *
     * @param codigoTitulacion el codigo titulacion
     * @param titulacion el titulacion
     * @param codigoTipoTitulo el codigo tipo titulo
     * @param tipoTitulo el tipo titulo
     * @param tipoEstudio el tipo estudio
     * @param nivel el nivel
     * @param fechaFinalizacion el fecha finalizacion
     * @param fechaExpedicion el fecha expedicion
     * @param codigoPaisExpedicion el codigo pais expedicion
     * @param paisExpedicion el pais expedicion
     * @param numeroRegistroAutonomico el numero registro autonomico
     * @param numeroRegistroMec el numero registro mec
     * @param registro el registro
     */
    public DatosTitulo(
           java.lang.String codigoTitulacion,
           java.lang.String titulacion,
           java.lang.String codigoTipoTitulo,
           java.lang.String tipoTitulo,
           java.lang.String tipoEstudio,
           java.lang.String nivel,
           java.lang.String fechaFinalizacion,
           java.lang.String fechaExpedicion,
           java.lang.String codigoPaisExpedicion,
           java.lang.String paisExpedicion,
           java.lang.String numeroRegistroAutonomico,
           java.lang.String numeroRegistroMec,
           es.redsara.intermediacion.scsp.esquemas.educacionnouniv.datosespecificos.Registro registro) {
           this.codigoTitulacion = codigoTitulacion;
           this.titulacion = titulacion;
           this.codigoTipoTitulo = codigoTipoTitulo;
           this.tipoTitulo = tipoTitulo;
           this.tipoEstudio = tipoEstudio;
           this.nivel = nivel;
           this.fechaFinalizacion = fechaFinalizacion;
           this.fechaExpedicion = fechaExpedicion;
           this.codigoPaisExpedicion = codigoPaisExpedicion;
           this.paisExpedicion = paisExpedicion;
           this.numeroRegistroAutonomico = numeroRegistroAutonomico;
           this.numeroRegistroMec = numeroRegistroMec;
           this.registro = registro;
    }


    /**
     * Gets the codigoTitulacion value for this DatosTitulo.
     * 
     * @return codigoTitulacion
     */
    public java.lang.String getCodigoTitulacion() {
        return codigoTitulacion;
    }


    /**
     * Sets the codigoTitulacion value for this DatosTitulo.
     *
     * @param codigoTitulacion el nuevo codigo titulacion
     */
    public void setCodigoTitulacion(java.lang.String codigoTitulacion) {
        this.codigoTitulacion = codigoTitulacion;
    }


    /**
     * Gets the titulacion value for this DatosTitulo.
     * 
     * @return titulacion
     */
    public java.lang.String getTitulacion() {
        return titulacion;
    }


    /**
     * Sets the titulacion value for this DatosTitulo.
     *
     * @param titulacion el nuevo titulacion
     */
    public void setTitulacion(java.lang.String titulacion) {
        this.titulacion = titulacion;
    }


    /**
     * Gets the codigoTipoTitulo value for this DatosTitulo.
     * 
     * @return codigoTipoTitulo
     */
    public java.lang.String getCodigoTipoTitulo() {
        return codigoTipoTitulo;
    }


    /**
     * Sets the codigoTipoTitulo value for this DatosTitulo.
     *
     * @param codigoTipoTitulo el nuevo codigo tipo titulo
     */
    public void setCodigoTipoTitulo(java.lang.String codigoTipoTitulo) {
        this.codigoTipoTitulo = codigoTipoTitulo;
    }


    /**
     * Gets the tipoTitulo value for this DatosTitulo.
     * 
     * @return tipoTitulo
     */
    public java.lang.String getTipoTitulo() {
        return tipoTitulo;
    }


    /**
     * Sets the tipoTitulo value for this DatosTitulo.
     *
     * @param tipoTitulo el nuevo tipo titulo
     */
    public void setTipoTitulo(java.lang.String tipoTitulo) {
        this.tipoTitulo = tipoTitulo;
    }


    /**
     * Gets the tipoEstudio value for this DatosTitulo.
     * 
     * @return tipoEstudio
     */
    public java.lang.String getTipoEstudio() {
        return tipoEstudio;
    }


    /**
     * Sets the tipoEstudio value for this DatosTitulo.
     *
     * @param tipoEstudio el nuevo tipo estudio
     */
    public void setTipoEstudio(java.lang.String tipoEstudio) {
        this.tipoEstudio = tipoEstudio;
    }


    /**
     * Gets the nivel value for this DatosTitulo.
     * 
     * @return nivel
     */
    public java.lang.String getNivel() {
        return nivel;
    }


    /**
     * Sets the nivel value for this DatosTitulo.
     *
     * @param nivel el nuevo nivel
     */
    public void setNivel(java.lang.String nivel) {
        this.nivel = nivel;
    }


    /**
     * Gets the fechaFinalizacion value for this DatosTitulo.
     * 
     * @return fechaFinalizacion
     */
    public java.lang.String getFechaFinalizacion() {
        return fechaFinalizacion;
    }


    /**
     * Sets the fechaFinalizacion value for this DatosTitulo.
     *
     * @param fechaFinalizacion el nuevo fecha finalizacion
     */
    public void setFechaFinalizacion(java.lang.String fechaFinalizacion) {
        this.fechaFinalizacion = fechaFinalizacion;
    }


    /**
     * Gets the fechaExpedicion value for this DatosTitulo.
     * 
     * @return fechaExpedicion
     */
    public java.lang.String getFechaExpedicion() {
        return fechaExpedicion;
    }


    /**
     * Sets the fechaExpedicion value for this DatosTitulo.
     *
     * @param fechaExpedicion el nuevo fecha expedicion
     */
    public void setFechaExpedicion(java.lang.String fechaExpedicion) {
        this.fechaExpedicion = fechaExpedicion;
    }


    /**
     * Gets the codigoPaisExpedicion value for this DatosTitulo.
     * 
     * @return codigoPaisExpedicion
     */
    public java.lang.String getCodigoPaisExpedicion() {
        return codigoPaisExpedicion;
    }


    /**
     * Sets the codigoPaisExpedicion value for this DatosTitulo.
     *
     * @param codigoPaisExpedicion el nuevo codigo pais expedicion
     */
    public void setCodigoPaisExpedicion(java.lang.String codigoPaisExpedicion) {
        this.codigoPaisExpedicion = codigoPaisExpedicion;
    }


    /**
     * Gets the paisExpedicion value for this DatosTitulo.
     * 
     * @return paisExpedicion
     */
    public java.lang.String getPaisExpedicion() {
        return paisExpedicion;
    }


    /**
     * Sets the paisExpedicion value for this DatosTitulo.
     *
     * @param paisExpedicion el nuevo pais expedicion
     */
    public void setPaisExpedicion(java.lang.String paisExpedicion) {
        this.paisExpedicion = paisExpedicion;
    }


    /**
     * Gets the numeroRegistroAutonomico value for this DatosTitulo.
     * 
     * @return numeroRegistroAutonomico
     */
    public java.lang.String getNumeroRegistroAutonomico() {
        return numeroRegistroAutonomico;
    }


    /**
     * Sets the numeroRegistroAutonomico value for this DatosTitulo.
     *
     * @param numeroRegistroAutonomico el nuevo numero registro autonomico
     */
    public void setNumeroRegistroAutonomico(java.lang.String numeroRegistroAutonomico) {
        this.numeroRegistroAutonomico = numeroRegistroAutonomico;
    }


    /**
     * Gets the numeroRegistroMec value for this DatosTitulo.
     * 
     * @return numeroRegistroMec
     */
    public java.lang.String getNumeroRegistroMec() {
        return numeroRegistroMec;
    }


    /**
     * Sets the numeroRegistroMec value for this DatosTitulo.
     *
     * @param numeroRegistroMec el nuevo numero registro mec
     */
    public void setNumeroRegistroMec(java.lang.String numeroRegistroMec) {
        this.numeroRegistroMec = numeroRegistroMec;
    }


    /**
     * Gets the registro value for this DatosTitulo.
     * 
     * @return registro
     */
    public es.redsara.intermediacion.scsp.esquemas.educacionnouniv.datosespecificos.Registro getRegistro() {
        return registro;
    }


    /**
     * Sets the registro value for this DatosTitulo.
     *
     * @param registro el nuevo registro
     */
    public void setRegistro(es.redsara.intermediacion.scsp.esquemas.educacionnouniv.datosespecificos.Registro registro) {
        this.registro = registro;
    }

    /** el equals calc. */
    private java.lang.Object __equalsCalc = null;
    
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DatosTitulo)) return false;
        DatosTitulo other = (DatosTitulo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.codigoTitulacion==null && other.getCodigoTitulacion()==null) || 
             (this.codigoTitulacion!=null &&
              this.codigoTitulacion.equals(other.getCodigoTitulacion()))) &&
            ((this.titulacion==null && other.getTitulacion()==null) || 
             (this.titulacion!=null &&
              this.titulacion.equals(other.getTitulacion()))) &&
            ((this.codigoTipoTitulo==null && other.getCodigoTipoTitulo()==null) || 
             (this.codigoTipoTitulo!=null &&
              this.codigoTipoTitulo.equals(other.getCodigoTipoTitulo()))) &&
            ((this.tipoTitulo==null && other.getTipoTitulo()==null) || 
             (this.tipoTitulo!=null &&
              this.tipoTitulo.equals(other.getTipoTitulo()))) &&
            ((this.tipoEstudio==null && other.getTipoEstudio()==null) || 
             (this.tipoEstudio!=null &&
              this.tipoEstudio.equals(other.getTipoEstudio()))) &&
            ((this.nivel==null && other.getNivel()==null) || 
             (this.nivel!=null &&
              this.nivel.equals(other.getNivel()))) &&
            ((this.fechaFinalizacion==null && other.getFechaFinalizacion()==null) || 
             (this.fechaFinalizacion!=null &&
              this.fechaFinalizacion.equals(other.getFechaFinalizacion()))) &&
            ((this.fechaExpedicion==null && other.getFechaExpedicion()==null) || 
             (this.fechaExpedicion!=null &&
              this.fechaExpedicion.equals(other.getFechaExpedicion()))) &&
            ((this.codigoPaisExpedicion==null && other.getCodigoPaisExpedicion()==null) || 
             (this.codigoPaisExpedicion!=null &&
              this.codigoPaisExpedicion.equals(other.getCodigoPaisExpedicion()))) &&
            ((this.paisExpedicion==null && other.getPaisExpedicion()==null) || 
             (this.paisExpedicion!=null &&
              this.paisExpedicion.equals(other.getPaisExpedicion()))) &&
            ((this.numeroRegistroAutonomico==null && other.getNumeroRegistroAutonomico()==null) || 
             (this.numeroRegistroAutonomico!=null &&
              this.numeroRegistroAutonomico.equals(other.getNumeroRegistroAutonomico()))) &&
            ((this.numeroRegistroMec==null && other.getNumeroRegistroMec()==null) || 
             (this.numeroRegistroMec!=null &&
              this.numeroRegistroMec.equals(other.getNumeroRegistroMec()))) &&
            ((this.registro==null && other.getRegistro()==null) || 
             (this.registro!=null &&
              this.registro.equals(other.getRegistro())));
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
        if (getCodigoTitulacion() != null) {
            _hashCode += getCodigoTitulacion().hashCode();
        }
        if (getTitulacion() != null) {
            _hashCode += getTitulacion().hashCode();
        }
        if (getCodigoTipoTitulo() != null) {
            _hashCode += getCodigoTipoTitulo().hashCode();
        }
        if (getTipoTitulo() != null) {
            _hashCode += getTipoTitulo().hashCode();
        }
        if (getTipoEstudio() != null) {
            _hashCode += getTipoEstudio().hashCode();
        }
        if (getNivel() != null) {
            _hashCode += getNivel().hashCode();
        }
        if (getFechaFinalizacion() != null) {
            _hashCode += getFechaFinalizacion().hashCode();
        }
        if (getFechaExpedicion() != null) {
            _hashCode += getFechaExpedicion().hashCode();
        }
        if (getCodigoPaisExpedicion() != null) {
            _hashCode += getCodigoPaisExpedicion().hashCode();
        }
        if (getPaisExpedicion() != null) {
            _hashCode += getPaisExpedicion().hashCode();
        }
        if (getNumeroRegistroAutonomico() != null) {
            _hashCode += getNumeroRegistroAutonomico().hashCode();
        }
        if (getNumeroRegistroMec() != null) {
            _hashCode += getNumeroRegistroMec().hashCode();
        }
        if (getRegistro() != null) {
            _hashCode += getRegistro().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    /** el type desc. */
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DatosTitulo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">DatosTitulo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoTitulacion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "CodigoTitulacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">CodigoTitulacion"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("titulacion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "Titulacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">Titulacion"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoTipoTitulo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "CodigoTipoTitulo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">CodigoTipoTitulo"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoTitulo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "TipoTitulo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">TipoTitulo"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoEstudio");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "TipoEstudio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">TipoEstudio"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nivel");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "Nivel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">Nivel"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaFinalizacion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "FechaFinalizacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">FechaFinalizacion"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaExpedicion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "FechaExpedicion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">FechaExpedicion"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoPaisExpedicion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "CodigoPaisExpedicion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">CodigoPaisExpedicion"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("paisExpedicion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "PaisExpedicion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">PaisExpedicion"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroRegistroAutonomico");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "NumeroRegistroAutonomico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">NumeroRegistroAutonomico"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroRegistroMec");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "NumeroRegistroMec"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">NumeroRegistroMec"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("registro");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "Registro"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">Registro"));
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
