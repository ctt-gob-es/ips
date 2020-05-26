/**
 * Consulta.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.redsara.intermediacion.scsp.esquemas.discapacidad.datosespecificos;

/**
 * El Class Consulta.
 */
public class Consulta  implements java.io.Serializable {
    
    /** el codigo comunidad autonoma. */
    private es.redsara.intermediacion.scsp.esquemas.discapacidad.datosespecificos.CodigoComunidadAutonoma codigoComunidadAutonoma;

    /** el codigo provincia. */
    private java.lang.String codigoProvincia;

    /** el expediente. */
    private java.lang.String expediente;

    /** el fecha consulta. */
    private java.lang.String fechaConsulta;

    /** el datos adicionales titular. */
    private es.redsara.intermediacion.scsp.esquemas.discapacidad.datosespecificos.DatosAdicionalesTitular datosAdicionalesTitular;

    /** el consentimiento tipos discapacidad. */
    /* Indica si el ciudadno consiente que en la respuesta se proporcionen
     * sus tipos de discapacidad. Valores posibles: S o N */
    private es.redsara.intermediacion.scsp.esquemas.discapacidad.datosespecificos.Resultado consentimientoTiposDiscapacidad;

    /**
     * Crea una nueva consulta.
     */
    public Consulta() {
    }

    /**
     * Crea una nueva consulta.
     *
     * @param codigoComunidadAutonoma el codigo comunidad autonoma
     * @param codigoProvincia el codigo provincia
     * @param expediente el expediente
     * @param fechaConsulta el fecha consulta
     * @param datosAdicionalesTitular el datos adicionales titular
     * @param consentimientoTiposDiscapacidad el consentimiento tipos discapacidad
     */
    public Consulta(
           es.redsara.intermediacion.scsp.esquemas.discapacidad.datosespecificos.CodigoComunidadAutonoma codigoComunidadAutonoma,
           java.lang.String codigoProvincia,
           java.lang.String expediente,
           java.lang.String fechaConsulta,
           es.redsara.intermediacion.scsp.esquemas.discapacidad.datosespecificos.DatosAdicionalesTitular datosAdicionalesTitular,
           es.redsara.intermediacion.scsp.esquemas.discapacidad.datosespecificos.Resultado consentimientoTiposDiscapacidad) {
           this.codigoComunidadAutonoma = codigoComunidadAutonoma;
           this.codigoProvincia = codigoProvincia;
           this.expediente = expediente;
           this.fechaConsulta = fechaConsulta;
           this.datosAdicionalesTitular = datosAdicionalesTitular;
           this.consentimientoTiposDiscapacidad = consentimientoTiposDiscapacidad;
    }


    /**
     * Gets the codigoComunidadAutonoma value for this Consulta.
     * 
     * @return codigoComunidadAutonoma
     */
    public es.redsara.intermediacion.scsp.esquemas.discapacidad.datosespecificos.CodigoComunidadAutonoma getCodigoComunidadAutonoma() {
        return codigoComunidadAutonoma;
    }


    /**
     * Sets the codigoComunidadAutonoma value for this Consulta.
     *
     * @param codigoComunidadAutonoma el nuevo codigo comunidad autonoma
     */
    public void setCodigoComunidadAutonoma(es.redsara.intermediacion.scsp.esquemas.discapacidad.datosespecificos.CodigoComunidadAutonoma codigoComunidadAutonoma) {
        this.codigoComunidadAutonoma = codigoComunidadAutonoma;
    }


    /**
     * Gets the codigoProvincia value for this Consulta.
     * 
     * @return codigoProvincia
     */
    public java.lang.String getCodigoProvincia() {
        return codigoProvincia;
    }


    /**
     * Sets the codigoProvincia value for this Consulta.
     *
     * @param codigoProvincia el nuevo codigo provincia
     */
    public void setCodigoProvincia(java.lang.String codigoProvincia) {
        this.codigoProvincia = codigoProvincia;
    }


    /**
     * Gets the expediente value for this Consulta.
     * 
     * @return expediente
     */
    public java.lang.String getExpediente() {
        return expediente;
    }


    /**
     * Sets the expediente value for this Consulta.
     *
     * @param expediente el nuevo expediente
     */
    public void setExpediente(java.lang.String expediente) {
        this.expediente = expediente;
    }


    /**
     * Gets the fechaConsulta value for this Consulta.
     * 
     * @return fechaConsulta
     */
    public java.lang.String getFechaConsulta() {
        return fechaConsulta;
    }


    /**
     * Sets the fechaConsulta value for this Consulta.
     *
     * @param fechaConsulta el nuevo fecha consulta
     */
    public void setFechaConsulta(java.lang.String fechaConsulta) {
        this.fechaConsulta = fechaConsulta;
    }


    /**
     * Gets the datosAdicionalesTitular value for this Consulta.
     * 
     * @return datosAdicionalesTitular
     */
    public es.redsara.intermediacion.scsp.esquemas.discapacidad.datosespecificos.DatosAdicionalesTitular getDatosAdicionalesTitular() {
        return datosAdicionalesTitular;
    }


    /**
     * Sets the datosAdicionalesTitular value for this Consulta.
     *
     * @param datosAdicionalesTitular el nuevo datos adicionales titular
     */
    public void setDatosAdicionalesTitular(es.redsara.intermediacion.scsp.esquemas.discapacidad.datosespecificos.DatosAdicionalesTitular datosAdicionalesTitular) {
        this.datosAdicionalesTitular = datosAdicionalesTitular;
    }


    /**
     * Gets the consentimientoTiposDiscapacidad value for this Consulta.
     * 
     * @return consentimientoTiposDiscapacidad   * Indica si el ciudadno consiente que en la respuesta se proporcionen
     * sus tipos de discapacidad. Valores posibles: S o N
     */
    public es.redsara.intermediacion.scsp.esquemas.discapacidad.datosespecificos.Resultado getConsentimientoTiposDiscapacidad() {
        return consentimientoTiposDiscapacidad;
    }


    /**
     * Sets the consentimientoTiposDiscapacidad value for this Consulta.
     * 
     * @param consentimientoTiposDiscapacidad   * Indica si el ciudadno consiente que en la respuesta se proporcionen
     * sus tipos de discapacidad. Valores posibles: S o N
     */
    public void setConsentimientoTiposDiscapacidad(es.redsara.intermediacion.scsp.esquemas.discapacidad.datosespecificos.Resultado consentimientoTiposDiscapacidad) {
        this.consentimientoTiposDiscapacidad = consentimientoTiposDiscapacidad;
    }

    /** el equals calc. */
    private java.lang.Object __equalsCalc = null;
    
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Consulta)) return false;
        Consulta other = (Consulta) obj;
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
            ((this.fechaConsulta==null && other.getFechaConsulta()==null) || 
             (this.fechaConsulta!=null &&
              this.fechaConsulta.equals(other.getFechaConsulta()))) &&
            ((this.datosAdicionalesTitular==null && other.getDatosAdicionalesTitular()==null) || 
             (this.datosAdicionalesTitular!=null &&
              this.datosAdicionalesTitular.equals(other.getDatosAdicionalesTitular()))) &&
            ((this.consentimientoTiposDiscapacidad==null && other.getConsentimientoTiposDiscapacidad()==null) || 
             (this.consentimientoTiposDiscapacidad!=null &&
              this.consentimientoTiposDiscapacidad.equals(other.getConsentimientoTiposDiscapacidad())));
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
        if (getFechaConsulta() != null) {
            _hashCode += getFechaConsulta().hashCode();
        }
        if (getDatosAdicionalesTitular() != null) {
            _hashCode += getDatosAdicionalesTitular().hashCode();
        }
        if (getConsentimientoTiposDiscapacidad() != null) {
            _hashCode += getConsentimientoTiposDiscapacidad().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    /** el type desc. */
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Consulta.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/discapacidad/datosespecificos", ">Consulta"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoComunidadAutonoma");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "CodigoComunidadAutonoma"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">CodigoComunidadAutonoma"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoProvincia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "CodigoProvincia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">Codigo2DigitosINE"));
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
        elemField.setFieldName("fechaConsulta");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "FechaConsulta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "Fecha"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("datosAdicionalesTitular");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "DatosAdicionalesTitular"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">DatosAdicionalesTitular"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("consentimientoTiposDiscapacidad");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "ConsentimientoTiposDiscapacidad"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">Resultado"));
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
