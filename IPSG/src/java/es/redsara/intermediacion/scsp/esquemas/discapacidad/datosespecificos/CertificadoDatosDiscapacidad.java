/**
 * CertificadoDatosDiscapacidad.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.redsara.intermediacion.scsp.esquemas.discapacidad.datosespecificos;

/**
 * El Class CertificadoDatosDiscapacidad.
 */
public class CertificadoDatosDiscapacidad  implements java.io.Serializable {
    
    /** el codigo comunidad autonoma. */
    private es.redsara.intermediacion.scsp.esquemas.discapacidad.datosespecificos.CodigoComunidadAutonoma codigoComunidadAutonoma;

    /** el codigo provincia. */
    private java.lang.String codigoProvincia;

    /** el expediente. */
    private java.lang.String expediente;

    /** el respuesta movilidad. */
    private es.redsara.intermediacion.scsp.esquemas.discapacidad.datosespecificos.CertificadoDatosDiscapacidadRespuestaMovilidad respuestaMovilidad;

    /** el respuesta dependencia. */
    /* Valores posibles: S o N */
    private es.redsara.intermediacion.scsp.esquemas.discapacidad.datosespecificos.Resultado respuestaDependencia;

    /** el respuesta acompanante T publico. */
    /* Valores posibles: S o N */
    private es.redsara.intermediacion.scsp.esquemas.discapacidad.datosespecificos.Resultado respuestaAcompananteTPublico;

    /** el grado discapacidad. */
    /* Valores posibles: 0-100 */
    private int gradoDiscapacidad;

    /** el tipos discapacidad. */
    private es.redsara.intermediacion.scsp.esquemas.discapacidad.datosespecificos.TipoDiscapacidad[] tiposDiscapacidad;

    /** el fecha efectos. */
    /* Formato: DD/MM/AAAA */
    private java.lang.String fechaEfectos;

    /** el fecha revision. */
    /* Formato: DD/MM/AAAA */
    private java.lang.String fechaRevision;

    /** el validez permanente. */
    /* Valores posibles S o N */
    private es.redsara.intermediacion.scsp.esquemas.discapacidad.datosespecificos.Resultado validezPermanente;

    /**
     * Crea una nueva certificado datos discapacidad.
     */
    public CertificadoDatosDiscapacidad() {
    }

    /**
     * Crea una nueva certificado datos discapacidad.
     *
     * @param codigoComunidadAutonoma el codigo comunidad autonoma
     * @param codigoProvincia el codigo provincia
     * @param expediente el expediente
     * @param respuestaMovilidad el respuesta movilidad
     * @param respuestaDependencia el respuesta dependencia
     * @param respuestaAcompananteTPublico el respuesta acompanante T publico
     * @param gradoDiscapacidad el grado discapacidad
     * @param tiposDiscapacidad el tipos discapacidad
     * @param fechaEfectos el fecha efectos
     * @param fechaRevision el fecha revision
     * @param validezPermanente el validez permanente
     */
    public CertificadoDatosDiscapacidad(
           es.redsara.intermediacion.scsp.esquemas.discapacidad.datosespecificos.CodigoComunidadAutonoma codigoComunidadAutonoma,
           java.lang.String codigoProvincia,
           java.lang.String expediente,
           es.redsara.intermediacion.scsp.esquemas.discapacidad.datosespecificos.CertificadoDatosDiscapacidadRespuestaMovilidad respuestaMovilidad,
           es.redsara.intermediacion.scsp.esquemas.discapacidad.datosespecificos.Resultado respuestaDependencia,
           es.redsara.intermediacion.scsp.esquemas.discapacidad.datosespecificos.Resultado respuestaAcompananteTPublico,
           int gradoDiscapacidad,
           es.redsara.intermediacion.scsp.esquemas.discapacidad.datosespecificos.TipoDiscapacidad[] tiposDiscapacidad,
           java.lang.String fechaEfectos,
           java.lang.String fechaRevision,
           es.redsara.intermediacion.scsp.esquemas.discapacidad.datosespecificos.Resultado validezPermanente) {
           this.codigoComunidadAutonoma = codigoComunidadAutonoma;
           this.codigoProvincia = codigoProvincia;
           this.expediente = expediente;
           this.respuestaMovilidad = respuestaMovilidad;
           this.respuestaDependencia = respuestaDependencia;
           this.respuestaAcompananteTPublico = respuestaAcompananteTPublico;
           this.gradoDiscapacidad = gradoDiscapacidad;
           this.tiposDiscapacidad = tiposDiscapacidad;
           this.fechaEfectos = fechaEfectos;
           this.fechaRevision = fechaRevision;
           this.validezPermanente = validezPermanente;
    }


    /**
     * Gets the codigoComunidadAutonoma value for this CertificadoDatosDiscapacidad.
     * 
     * @return codigoComunidadAutonoma
     */
    public es.redsara.intermediacion.scsp.esquemas.discapacidad.datosespecificos.CodigoComunidadAutonoma getCodigoComunidadAutonoma() {
        return codigoComunidadAutonoma;
    }


    /**
     * Sets the codigoComunidadAutonoma value for this CertificadoDatosDiscapacidad.
     *
     * @param codigoComunidadAutonoma el nuevo codigo comunidad autonoma
     */
    public void setCodigoComunidadAutonoma(es.redsara.intermediacion.scsp.esquemas.discapacidad.datosespecificos.CodigoComunidadAutonoma codigoComunidadAutonoma) {
        this.codigoComunidadAutonoma = codigoComunidadAutonoma;
    }


    /**
     * Gets the codigoProvincia value for this CertificadoDatosDiscapacidad.
     * 
     * @return codigoProvincia
     */
    public java.lang.String getCodigoProvincia() {
        return codigoProvincia;
    }


    /**
     * Sets the codigoProvincia value for this CertificadoDatosDiscapacidad.
     *
     * @param codigoProvincia el nuevo codigo provincia
     */
    public void setCodigoProvincia(java.lang.String codigoProvincia) {
        this.codigoProvincia = codigoProvincia;
    }


    /**
     * Gets the expediente value for this CertificadoDatosDiscapacidad.
     * 
     * @return expediente
     */
    public java.lang.String getExpediente() {
        return expediente;
    }


    /**
     * Sets the expediente value for this CertificadoDatosDiscapacidad.
     *
     * @param expediente el nuevo expediente
     */
    public void setExpediente(java.lang.String expediente) {
        this.expediente = expediente;
    }


    /**
     * Gets the respuestaMovilidad value for this CertificadoDatosDiscapacidad.
     * 
     * @return respuestaMovilidad
     */
    public es.redsara.intermediacion.scsp.esquemas.discapacidad.datosespecificos.CertificadoDatosDiscapacidadRespuestaMovilidad getRespuestaMovilidad() {
        return respuestaMovilidad;
    }


    /**
     * Sets the respuestaMovilidad value for this CertificadoDatosDiscapacidad.
     *
     * @param respuestaMovilidad el nuevo respuesta movilidad
     */
    public void setRespuestaMovilidad(es.redsara.intermediacion.scsp.esquemas.discapacidad.datosespecificos.CertificadoDatosDiscapacidadRespuestaMovilidad respuestaMovilidad) {
        this.respuestaMovilidad = respuestaMovilidad;
    }


    /**
     * Gets the respuestaDependencia value for this CertificadoDatosDiscapacidad.
     * 
     * @return respuestaDependencia   * Valores posibles: S o N
     */
    public es.redsara.intermediacion.scsp.esquemas.discapacidad.datosespecificos.Resultado getRespuestaDependencia() {
        return respuestaDependencia;
    }


    /**
     * Sets the respuestaDependencia value for this CertificadoDatosDiscapacidad.
     * 
     * @param respuestaDependencia   * Valores posibles: S o N
     */
    public void setRespuestaDependencia(es.redsara.intermediacion.scsp.esquemas.discapacidad.datosespecificos.Resultado respuestaDependencia) {
        this.respuestaDependencia = respuestaDependencia;
    }


    /**
     * Gets the respuestaAcompananteTPublico value for this CertificadoDatosDiscapacidad.
     * 
     * @return respuestaAcompananteTPublico   * Valores posibles: S o N
     */
    public es.redsara.intermediacion.scsp.esquemas.discapacidad.datosespecificos.Resultado getRespuestaAcompananteTPublico() {
        return respuestaAcompananteTPublico;
    }


    /**
     * Sets the respuestaAcompananteTPublico value for this CertificadoDatosDiscapacidad.
     * 
     * @param respuestaAcompananteTPublico   * Valores posibles: S o N
     */
    public void setRespuestaAcompananteTPublico(es.redsara.intermediacion.scsp.esquemas.discapacidad.datosespecificos.Resultado respuestaAcompananteTPublico) {
        this.respuestaAcompananteTPublico = respuestaAcompananteTPublico;
    }


    /**
     * Gets the gradoDiscapacidad value for this CertificadoDatosDiscapacidad.
     * 
     * @return gradoDiscapacidad   * Valores posibles: 0-100
     */
    public int getGradoDiscapacidad() {
        return gradoDiscapacidad;
    }


    /**
     * Sets the gradoDiscapacidad value for this CertificadoDatosDiscapacidad.
     * 
     * @param gradoDiscapacidad   * Valores posibles: 0-100
     */
    public void setGradoDiscapacidad(int gradoDiscapacidad) {
        this.gradoDiscapacidad = gradoDiscapacidad;
    }


    /**
     * Gets the tiposDiscapacidad value for this CertificadoDatosDiscapacidad.
     * 
     * @return tiposDiscapacidad
     */
    public es.redsara.intermediacion.scsp.esquemas.discapacidad.datosespecificos.TipoDiscapacidad[] getTiposDiscapacidad() {
        return tiposDiscapacidad;
    }


    /**
     * Sets the tiposDiscapacidad value for this CertificadoDatosDiscapacidad.
     *
     * @param tiposDiscapacidad el nuevo tipos discapacidad
     */
    public void setTiposDiscapacidad(es.redsara.intermediacion.scsp.esquemas.discapacidad.datosespecificos.TipoDiscapacidad[] tiposDiscapacidad) {
        this.tiposDiscapacidad = tiposDiscapacidad;
    }


    /**
     * Gets the fechaEfectos value for this CertificadoDatosDiscapacidad.
     * 
     * @return fechaEfectos   * Formato: DD/MM/AAAA
     */
    public java.lang.String getFechaEfectos() {
        return fechaEfectos;
    }


    /**
     * Sets the fechaEfectos value for this CertificadoDatosDiscapacidad.
     * 
     * @param fechaEfectos   * Formato: DD/MM/AAAA
     */
    public void setFechaEfectos(java.lang.String fechaEfectos) {
        this.fechaEfectos = fechaEfectos;
    }


    /**
     * Gets the fechaRevision value for this CertificadoDatosDiscapacidad.
     * 
     * @return fechaRevision   * Formato: DD/MM/AAAA
     */
    public java.lang.String getFechaRevision() {
        return fechaRevision;
    }


    /**
     * Sets the fechaRevision value for this CertificadoDatosDiscapacidad.
     * 
     * @param fechaRevision   * Formato: DD/MM/AAAA
     */
    public void setFechaRevision(java.lang.String fechaRevision) {
        this.fechaRevision = fechaRevision;
    }


    /**
     * Gets the validezPermanente value for this CertificadoDatosDiscapacidad.
     * 
     * @return validezPermanente   * Valores posibles S o N
     */
    public es.redsara.intermediacion.scsp.esquemas.discapacidad.datosespecificos.Resultado getValidezPermanente() {
        return validezPermanente;
    }


    /**
     * Sets the validezPermanente value for this CertificadoDatosDiscapacidad.
     * 
     * @param validezPermanente   * Valores posibles S o N
     */
    public void setValidezPermanente(es.redsara.intermediacion.scsp.esquemas.discapacidad.datosespecificos.Resultado validezPermanente) {
        this.validezPermanente = validezPermanente;
    }

    /** el equals calc. */
    private java.lang.Object __equalsCalc = null;
    
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CertificadoDatosDiscapacidad)) return false;
        CertificadoDatosDiscapacidad other = (CertificadoDatosDiscapacidad) obj;
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
            ((this.codigoProvincia==null && other.getCodigoProvincia()==null) || 
             (this.codigoProvincia!=null &&
              this.codigoProvincia.equals(other.getCodigoProvincia()))) &&
            ((this.expediente==null && other.getExpediente()==null) || 
             (this.expediente!=null &&
              this.expediente.equals(other.getExpediente()))) &&
            ((this.respuestaMovilidad==null && other.getRespuestaMovilidad()==null) || 
             (this.respuestaMovilidad!=null &&
              this.respuestaMovilidad.equals(other.getRespuestaMovilidad()))) &&
            ((this.respuestaDependencia==null && other.getRespuestaDependencia()==null) || 
             (this.respuestaDependencia!=null &&
              this.respuestaDependencia.equals(other.getRespuestaDependencia()))) &&
            ((this.respuestaAcompananteTPublico==null && other.getRespuestaAcompananteTPublico()==null) || 
             (this.respuestaAcompananteTPublico!=null &&
              this.respuestaAcompananteTPublico.equals(other.getRespuestaAcompananteTPublico()))) &&
            this.gradoDiscapacidad == other.getGradoDiscapacidad() &&
            ((this.tiposDiscapacidad==null && other.getTiposDiscapacidad()==null) || 
             (this.tiposDiscapacidad!=null &&
              java.util.Arrays.equals(this.tiposDiscapacidad, other.getTiposDiscapacidad()))) &&
            ((this.fechaEfectos==null && other.getFechaEfectos()==null) || 
             (this.fechaEfectos!=null &&
              this.fechaEfectos.equals(other.getFechaEfectos()))) &&
            ((this.fechaRevision==null && other.getFechaRevision()==null) || 
             (this.fechaRevision!=null &&
              this.fechaRevision.equals(other.getFechaRevision()))) &&
            ((this.validezPermanente==null && other.getValidezPermanente()==null) || 
             (this.validezPermanente!=null &&
              this.validezPermanente.equals(other.getValidezPermanente())));
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
        if (getCodigoProvincia() != null) {
            _hashCode += getCodigoProvincia().hashCode();
        }
        if (getExpediente() != null) {
            _hashCode += getExpediente().hashCode();
        }
        if (getRespuestaMovilidad() != null) {
            _hashCode += getRespuestaMovilidad().hashCode();
        }
        if (getRespuestaDependencia() != null) {
            _hashCode += getRespuestaDependencia().hashCode();
        }
        if (getRespuestaAcompananteTPublico() != null) {
            _hashCode += getRespuestaAcompananteTPublico().hashCode();
        }
        _hashCode += getGradoDiscapacidad();
        if (getTiposDiscapacidad() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTiposDiscapacidad());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getTiposDiscapacidad(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getFechaEfectos() != null) {
            _hashCode += getFechaEfectos().hashCode();
        }
        if (getFechaRevision() != null) {
            _hashCode += getFechaRevision().hashCode();
        }
        if (getValidezPermanente() != null) {
            _hashCode += getValidezPermanente().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    /** el type desc. */
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CertificadoDatosDiscapacidad.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">CertificadoDatosDiscapacidad"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoComunidadAutonoma");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "CodigoComunidadAutonoma"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">CodigoComunidadAutonoma"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoProvincia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "CodigoProvincia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "Codigo2DigitosINE"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("expediente");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "Expediente"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">Expediente"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("respuestaMovilidad");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "RespuestaMovilidad"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">>CertificadoDatosDiscapacidad>RespuestaMovilidad"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("respuestaDependencia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "RespuestaDependencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "Resultado"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("respuestaAcompananteTPublico");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "RespuestaAcompananteTPublico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "Resultado"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("gradoDiscapacidad");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "GradoDiscapacidad"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "Grado"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tiposDiscapacidad");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "TiposDiscapacidad"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "TipoDiscapacidad"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaEfectos");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "FechaEfectos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaRevision");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "FechaRevision"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("validezPermanente");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "ValidezPermanente"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "Resultado"));
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
