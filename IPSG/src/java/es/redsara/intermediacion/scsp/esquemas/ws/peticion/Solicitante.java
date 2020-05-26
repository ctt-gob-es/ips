/**
 * Solicitante.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.redsara.intermediacion.scsp.esquemas.ws.peticion;

/**
 * El Class Solicitante.
 */
public class Solicitante  implements java.io.Serializable, Cloneable {
    
    /** el identificador solicitante. */
    private java.lang.String identificadorSolicitante;

    /** el nombre solicitante. */
    private java.lang.String nombreSolicitante;

    /** el unidad tramitadora. */
    private java.lang.String unidadTramitadora;
    
    /** el unidad tramitadora. */
    private java.lang.String codigoUnidadTramitadora;

    /** el procedimiento. */
    private es.redsara.intermediacion.scsp.esquemas.ws.peticion.Procedimiento procedimiento;

    /** el finalidad. */
    private java.lang.String finalidad;

    /** el consentimiento. */
    private es.redsara.intermediacion.scsp.esquemas.ws.peticion.Consentimiento consentimiento;

    /** el funcionario. */
    private es.redsara.intermediacion.scsp.esquemas.ws.peticion.Funcionario funcionario;

    /** el id expediente. */
    private java.lang.String idExpediente;

    /**
     * Crea una nueva solicitante.
     */
    public Solicitante() {
    }

    /**
     * Crea una nueva solicitante.
     *
     * @param identificadorSolicitante el identificador solicitante
     * @param nombreSolicitante el nombre solicitante
     * @param unidadTramitadora el unidad tramitadora
     * @param procedimiento el procedimiento
     * @param finalidad el finalidad
     * @param consentimiento el consentimiento
     * @param funcionario el funcionario
     * @param idExpediente el id expediente
     */
    public Solicitante(
           java.lang.String identificadorSolicitante,
           java.lang.String nombreSolicitante,
           java.lang.String unidadTramitadora,
           es.redsara.intermediacion.scsp.esquemas.ws.peticion.Procedimiento procedimiento,
           java.lang.String finalidad,
           es.redsara.intermediacion.scsp.esquemas.ws.peticion.Consentimiento consentimiento,
           es.redsara.intermediacion.scsp.esquemas.ws.peticion.Funcionario funcionario,
           java.lang.String idExpediente) {
           this.identificadorSolicitante = identificadorSolicitante;
           this.nombreSolicitante = nombreSolicitante;
           this.unidadTramitadora = unidadTramitadora;
           this.procedimiento = procedimiento;
           this.finalidad = finalidad;
           this.consentimiento = consentimiento;
           this.funcionario = funcionario;
           this.idExpediente = idExpediente;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#clone()
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    
    
    /**
     * Gets the identificadorSolicitante value for this Solicitante.
     * 
     * @return identificadorSolicitante
     */
    public java.lang.String getIdentificadorSolicitante() {
        return identificadorSolicitante;
    }


    /**
     * Sets the identificadorSolicitante value for this Solicitante.
     *
     * @param identificadorSolicitante el nuevo identificador solicitante
     */
    public void setIdentificadorSolicitante(java.lang.String identificadorSolicitante) {
        this.identificadorSolicitante = identificadorSolicitante;
    }


    /**
     * Gets the nombreSolicitante value for this Solicitante.
     * 
     * @return nombreSolicitante
     */
    public java.lang.String getNombreSolicitante() {
        return nombreSolicitante;
    }


    /**
     * Sets the nombreSolicitante value for this Solicitante.
     *
     * @param nombreSolicitante el nuevo nombre solicitante
     */
    public void setNombreSolicitante(java.lang.String nombreSolicitante) {
        this.nombreSolicitante = nombreSolicitante;
    }


    /**
     * Gets the unidadTramitadora value for this Solicitante.
     * 
     * @return unidadTramitadora
     */
    public java.lang.String getUnidadTramitadora() {
        return unidadTramitadora;
    }


    /**
     * Sets the unidadTramitadora value for this Solicitante.
     *
     * @param unidadTramitadora el nuevo unidad tramitadora
     */
    public void setUnidadTramitadora(java.lang.String unidadTramitadora) {
        this.unidadTramitadora = unidadTramitadora;
    }


    /**
     * Gets the procedimiento value for this Solicitante.
     * 
     * @return procedimiento
     */
    public es.redsara.intermediacion.scsp.esquemas.ws.peticion.Procedimiento getProcedimiento() {
        return procedimiento;
    }


    /**
     * Sets the procedimiento value for this Solicitante.
     *
     * @param procedimiento el nuevo procedimiento
     */
    public void setProcedimiento(es.redsara.intermediacion.scsp.esquemas.ws.peticion.Procedimiento procedimiento) {
        this.procedimiento = procedimiento;
    }


    /**
     * Gets the finalidad value for this Solicitante.
     * 
     * @return finalidad
     */
    public java.lang.String getFinalidad() {
        return finalidad;
    }


    /**
     * Sets the finalidad value for this Solicitante.
     *
     * @param finalidad el nuevo finalidad
     */
    public void setFinalidad(java.lang.String finalidad) {
        this.finalidad = finalidad;
    }


    /**
     * Gets the consentimiento value for this Solicitante.
     * 
     * @return consentimiento
     */
    public es.redsara.intermediacion.scsp.esquemas.ws.peticion.Consentimiento getConsentimiento() {
        return consentimiento;
    }


    /**
     * Sets the consentimiento value for this Solicitante.
     *
     * @param consentimiento el nuevo consentimiento
     */
    public void setConsentimiento(es.redsara.intermediacion.scsp.esquemas.ws.peticion.Consentimiento consentimiento) {
        this.consentimiento = consentimiento;
    }


    /**
     * Gets the funcionario value for this Solicitante.
     * 
     * @return funcionario
     */
    public es.redsara.intermediacion.scsp.esquemas.ws.peticion.Funcionario getFuncionario() {
        return funcionario;
    }


    /**
     * Sets the funcionario value for this Solicitante.
     *
     * @param funcionario el nuevo funcionario
     */
    public void setFuncionario(es.redsara.intermediacion.scsp.esquemas.ws.peticion.Funcionario funcionario) {
        this.funcionario = funcionario;
    }


    /**
     * Gets the idExpediente value for this Solicitante.
     * 
     * @return idExpediente
     */
    public java.lang.String getIdExpediente() {
        return idExpediente;
    }


    /**
     * Sets the idExpediente value for this Solicitante.
     *
     * @param idExpediente el nuevo id expediente
     */
    public void setIdExpediente(java.lang.String idExpediente) {
        this.idExpediente = idExpediente;
    }

    public java.lang.String getCodigoUnidadTramitadora() {
		return codigoUnidadTramitadora;
	}

	public void setCodigoUnidadTramitadora(java.lang.String codigoUnidadTramitadora) {
		this.codigoUnidadTramitadora = codigoUnidadTramitadora;
	}



	/** el equals calc. */
    private java.lang.Object __equalsCalc = null;
    
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Solicitante)) return false;
        Solicitante other = (Solicitante) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.identificadorSolicitante==null && other.getIdentificadorSolicitante()==null) || 
             (this.identificadorSolicitante!=null &&
              this.identificadorSolicitante.equals(other.getIdentificadorSolicitante()))) &&
            ((this.nombreSolicitante==null && other.getNombreSolicitante()==null) || 
             (this.nombreSolicitante!=null &&
              this.nombreSolicitante.equals(other.getNombreSolicitante()))) &&
            ((this.unidadTramitadora==null && other.getUnidadTramitadora()==null) || 
             (this.unidadTramitadora!=null &&
              this.unidadTramitadora.equals(other.getUnidadTramitadora()))) &&
            ((this.procedimiento==null && other.getProcedimiento()==null) || 
             (this.procedimiento!=null &&
              this.procedimiento.equals(other.getProcedimiento()))) &&
            ((this.finalidad==null && other.getFinalidad()==null) || 
             (this.finalidad!=null &&
              this.finalidad.equals(other.getFinalidad()))) &&
            ((this.consentimiento==null && other.getConsentimiento()==null) || 
             (this.consentimiento!=null &&
              this.consentimiento.equals(other.getConsentimiento()))) &&
            ((this.funcionario==null && other.getFuncionario()==null) || 
             (this.funcionario!=null &&
              this.funcionario.equals(other.getFuncionario()))) &&
            ((this.idExpediente==null && other.getIdExpediente()==null) || 
             (this.idExpediente!=null &&
              this.idExpediente.equals(other.getIdExpediente())));
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
        if (getIdentificadorSolicitante() != null) {
            _hashCode += getIdentificadorSolicitante().hashCode();
        }
        if (getNombreSolicitante() != null) {
            _hashCode += getNombreSolicitante().hashCode();
        }
        if (getUnidadTramitadora() != null) {
            _hashCode += getUnidadTramitadora().hashCode();
        }
        if (getProcedimiento() != null) {
            _hashCode += getProcedimiento().hashCode();
        }
        if (getFinalidad() != null) {
            _hashCode += getFinalidad().hashCode();
        }
        if (getConsentimiento() != null) {
            _hashCode += getConsentimiento().hashCode();
        }
        if (getFuncionario() != null) {
            _hashCode += getFuncionario().hashCode();
        }
        if (getIdExpediente() != null) {
            _hashCode += getIdExpediente().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    /** el type desc. */
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Solicitante.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/ws/peticion", ">Solicitante"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("identificadorSolicitante");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/ws/peticion", "IdentificadorSolicitante"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/ws/peticion", ">IdentificadorSolicitante"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombreSolicitante");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/ws/peticion", "NombreSolicitante"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/ws/peticion", ">NombreSolicitante"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("unidadTramitadora");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/ws/peticion", "UnidadTramitadora"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/ws/peticion", ">UnidadTramitadora"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("procedimiento");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/ws/peticion", "Procedimiento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/ws/peticion", ">Procedimiento"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("finalidad");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/ws/peticion", "Finalidad"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/ws/peticion", ">Finalidad"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("consentimiento");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/ws/peticion", "Consentimiento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/ws/peticion", ">Consentimiento"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("funcionario");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/ws/peticion", "Funcionario"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/ws/peticion", ">Funcionario"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idExpediente");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/ws/peticion", "IdExpediente"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/ws/peticion", ">IdExpediente"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoUnidadTramitadora");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/ws/peticion", "CodigoUnidadTramitadora"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/ws/peticion", ">CodigoUnidadTramitadora"));
        elemField.setMinOccurs(0);
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
