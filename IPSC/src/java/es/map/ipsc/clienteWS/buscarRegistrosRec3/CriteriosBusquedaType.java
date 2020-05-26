/**
 * CriteriosBusquedaType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.map.ipsc.clienteWS.buscarRegistrosRec3;

/**
 * El Class CriteriosBusquedaType.
 */
public class CriteriosBusquedaType  implements java.io.Serializable {
    
    /** el cd oficina origen. */
    private java.lang.String cdOficinaOrigen;

    /** el cd tipo registro. */
    private java.lang.String cdTipoRegistro;

    /** el nm registro. */
    private java.lang.String nmRegistro;

    /** el fe registro desde. */
    private java.lang.String feRegistroDesde;

    /** el fe registro hasta. */
    private java.lang.String feRegistroHasta;

    /** el cd unidad origen. */
    private java.lang.String cdUnidadOrigen;

    /** el cd unidad destino. */
    private java.lang.String cdUnidadDestino;

    /** el cd asunto. */
    private java.lang.String cdAsunto;

    /** el tl num doc identificativo interesado. */
    private java.lang.String tlNumDocIdentificativoInteresado;

    /** el tl nombre interesado. */
    private java.lang.String tlNombreInteresado;

    /** el tl apellido 1 interesado. */
    private java.lang.String tlApellido1Interesado;

    /** el tl apellido 2 interesado. */
    private java.lang.String tlApellido2Interesado;

    /** el tl razon social interesado. */
    private java.lang.String tlRazonSocialInteresado;

    /** el tl num doc identificativo representante. */
    private java.lang.String tlNumDocIdentificativoRepresentante;

    /** el tl nombre representante. */
    private java.lang.String tlNombreRepresentante;

    /** el tl apellido 1 representante. */
    private java.lang.String tlApellido1Representante;

    /** el tl apellido 2 representante. */
    private java.lang.String tlApellido2Representante;

    /** el tl razon social representante. */
    private java.lang.String tlRazonSocialRepresentante;

    /** el first result. */
    private java.lang.String firstResult;

    /** el max result. */
    private java.lang.String maxResult;

    /**
     * Crea una nueva criterios busqueda type.
     */
    public CriteriosBusquedaType() {
    }

    /**
     * Crea una nueva criterios busqueda type.
     *
     * @param cdOficinaOrigen el cd oficina origen
     * @param cdTipoRegistro el cd tipo registro
     * @param nmRegistro el nm registro
     * @param feRegistroDesde el fe registro desde
     * @param feRegistroHasta el fe registro hasta
     * @param cdUnidadOrigen el cd unidad origen
     * @param cdUnidadDestino el cd unidad destino
     * @param cdAsunto el cd asunto
     * @param tlNumDocIdentificativoInteresado el tl num doc identificativo interesado
     * @param tlNombreInteresado el tl nombre interesado
     * @param tlApellido1Interesado el tl apellido 1 interesado
     * @param tlApellido2Interesado el tl apellido 2 interesado
     * @param tlRazonSocialInteresado el tl razon social interesado
     * @param tlNumDocIdentificativoRepresentante el tl num doc identificativo representante
     * @param tlNombreRepresentante el tl nombre representante
     * @param tlApellido1Representante el tl apellido 1 representante
     * @param tlApellido2Representante el tl apellido 2 representante
     * @param tlRazonSocialRepresentante el tl razon social representante
     * @param firstResult el first result
     * @param maxResult el max result
     */
    public CriteriosBusquedaType(
           java.lang.String cdOficinaOrigen,
           java.lang.String cdTipoRegistro,
           java.lang.String nmRegistro,
           java.lang.String feRegistroDesde,
           java.lang.String feRegistroHasta,
           java.lang.String cdUnidadOrigen,
           java.lang.String cdUnidadDestino,
           java.lang.String cdAsunto,
           java.lang.String tlNumDocIdentificativoInteresado,
           java.lang.String tlNombreInteresado,
           java.lang.String tlApellido1Interesado,
           java.lang.String tlApellido2Interesado,
           java.lang.String tlRazonSocialInteresado,
           java.lang.String tlNumDocIdentificativoRepresentante,
           java.lang.String tlNombreRepresentante,
           java.lang.String tlApellido1Representante,
           java.lang.String tlApellido2Representante,
           java.lang.String tlRazonSocialRepresentante,
           java.lang.String firstResult,
           java.lang.String maxResult) {
           this.cdOficinaOrigen = cdOficinaOrigen;
           this.cdTipoRegistro = cdTipoRegistro;
           this.nmRegistro = nmRegistro;
           this.feRegistroDesde = feRegistroDesde;
           this.feRegistroHasta = feRegistroHasta;
           this.cdUnidadOrigen = cdUnidadOrigen;
           this.cdUnidadDestino = cdUnidadDestino;
           this.cdAsunto = cdAsunto;
           this.tlNumDocIdentificativoInteresado = tlNumDocIdentificativoInteresado;
           this.tlNombreInteresado = tlNombreInteresado;
           this.tlApellido1Interesado = tlApellido1Interesado;
           this.tlApellido2Interesado = tlApellido2Interesado;
           this.tlRazonSocialInteresado = tlRazonSocialInteresado;
           this.tlNumDocIdentificativoRepresentante = tlNumDocIdentificativoRepresentante;
           this.tlNombreRepresentante = tlNombreRepresentante;
           this.tlApellido1Representante = tlApellido1Representante;
           this.tlApellido2Representante = tlApellido2Representante;
           this.tlRazonSocialRepresentante = tlRazonSocialRepresentante;
           this.firstResult = firstResult;
           this.maxResult = maxResult;
    }


    /**
     * Gets the cdOficinaOrigen value for this CriteriosBusquedaType.
     * 
     * @return cdOficinaOrigen
     */
    public java.lang.String getCdOficinaOrigen() {
        return cdOficinaOrigen;
    }


    /**
     * Sets the cdOficinaOrigen value for this CriteriosBusquedaType.
     *
     * @param cdOficinaOrigen el nuevo cd oficina origen
     */
    public void setCdOficinaOrigen(java.lang.String cdOficinaOrigen) {
        this.cdOficinaOrigen = cdOficinaOrigen;
    }


    /**
     * Gets the cdTipoRegistro value for this CriteriosBusquedaType.
     * 
     * @return cdTipoRegistro
     */
    public java.lang.String getCdTipoRegistro() {
        return cdTipoRegistro;
    }


    /**
     * Sets the cdTipoRegistro value for this CriteriosBusquedaType.
     *
     * @param cdTipoRegistro el nuevo cd tipo registro
     */
    public void setCdTipoRegistro(java.lang.String cdTipoRegistro) {
        this.cdTipoRegistro = cdTipoRegistro;
    }


    /**
     * Gets the nmRegistro value for this CriteriosBusquedaType.
     * 
     * @return nmRegistro
     */
    public java.lang.String getNmRegistro() {
        return nmRegistro;
    }


    /**
     * Sets the nmRegistro value for this CriteriosBusquedaType.
     *
     * @param nmRegistro el nuevo nm registro
     */
    public void setNmRegistro(java.lang.String nmRegistro) {
        this.nmRegistro = nmRegistro;
    }


    /**
     * Gets the feRegistroDesde value for this CriteriosBusquedaType.
     * 
     * @return feRegistroDesde
     */
    public java.lang.String getFeRegistroDesde() {
        return feRegistroDesde;
    }


    /**
     * Sets the feRegistroDesde value for this CriteriosBusquedaType.
     *
     * @param feRegistroDesde el nuevo fe registro desde
     */
    public void setFeRegistroDesde(java.lang.String feRegistroDesde) {
        this.feRegistroDesde = feRegistroDesde;
    }


    /**
     * Gets the feRegistroHasta value for this CriteriosBusquedaType.
     * 
     * @return feRegistroHasta
     */
    public java.lang.String getFeRegistroHasta() {
        return feRegistroHasta;
    }


    /**
     * Sets the feRegistroHasta value for this CriteriosBusquedaType.
     *
     * @param feRegistroHasta el nuevo fe registro hasta
     */
    public void setFeRegistroHasta(java.lang.String feRegistroHasta) {
        this.feRegistroHasta = feRegistroHasta;
    }


    /**
     * Gets the cdUnidadOrigen value for this CriteriosBusquedaType.
     * 
     * @return cdUnidadOrigen
     */
    public java.lang.String getCdUnidadOrigen() {
        return cdUnidadOrigen;
    }


    /**
     * Sets the cdUnidadOrigen value for this CriteriosBusquedaType.
     *
     * @param cdUnidadOrigen el nuevo cd unidad origen
     */
    public void setCdUnidadOrigen(java.lang.String cdUnidadOrigen) {
        this.cdUnidadOrigen = cdUnidadOrigen;
    }


    /**
     * Gets the cdUnidadDestino value for this CriteriosBusquedaType.
     * 
     * @return cdUnidadDestino
     */
    public java.lang.String getCdUnidadDestino() {
        return cdUnidadDestino;
    }


    /**
     * Sets the cdUnidadDestino value for this CriteriosBusquedaType.
     *
     * @param cdUnidadDestino el nuevo cd unidad destino
     */
    public void setCdUnidadDestino(java.lang.String cdUnidadDestino) {
        this.cdUnidadDestino = cdUnidadDestino;
    }


    /**
     * Gets the cdAsunto value for this CriteriosBusquedaType.
     * 
     * @return cdAsunto
     */
    public java.lang.String getCdAsunto() {
        return cdAsunto;
    }


    /**
     * Sets the cdAsunto value for this CriteriosBusquedaType.
     *
     * @param cdAsunto el nuevo cd asunto
     */
    public void setCdAsunto(java.lang.String cdAsunto) {
        this.cdAsunto = cdAsunto;
    }


    /**
     * Gets the tlNumDocIdentificativoInteresado value for this CriteriosBusquedaType.
     * 
     * @return tlNumDocIdentificativoInteresado
     */
    public java.lang.String getTlNumDocIdentificativoInteresado() {
        return tlNumDocIdentificativoInteresado;
    }


    /**
     * Sets the tlNumDocIdentificativoInteresado value for this CriteriosBusquedaType.
     *
     * @param tlNumDocIdentificativoInteresado el nuevo tl num doc identificativo interesado
     */
    public void setTlNumDocIdentificativoInteresado(java.lang.String tlNumDocIdentificativoInteresado) {
        this.tlNumDocIdentificativoInteresado = tlNumDocIdentificativoInteresado;
    }


    /**
     * Gets the tlNombreInteresado value for this CriteriosBusquedaType.
     * 
     * @return tlNombreInteresado
     */
    public java.lang.String getTlNombreInteresado() {
        return tlNombreInteresado;
    }


    /**
     * Sets the tlNombreInteresado value for this CriteriosBusquedaType.
     *
     * @param tlNombreInteresado el nuevo tl nombre interesado
     */
    public void setTlNombreInteresado(java.lang.String tlNombreInteresado) {
        this.tlNombreInteresado = tlNombreInteresado;
    }


    /**
     * Gets the tlApellido1Interesado value for this CriteriosBusquedaType.
     * 
     * @return tlApellido1Interesado
     */
    public java.lang.String getTlApellido1Interesado() {
        return tlApellido1Interesado;
    }


    /**
     * Sets the tlApellido1Interesado value for this CriteriosBusquedaType.
     *
     * @param tlApellido1Interesado el nuevo tl apellido 1 interesado
     */
    public void setTlApellido1Interesado(java.lang.String tlApellido1Interesado) {
        this.tlApellido1Interesado = tlApellido1Interesado;
    }


    /**
     * Gets the tlApellido2Interesado value for this CriteriosBusquedaType.
     * 
     * @return tlApellido2Interesado
     */
    public java.lang.String getTlApellido2Interesado() {
        return tlApellido2Interesado;
    }


    /**
     * Sets the tlApellido2Interesado value for this CriteriosBusquedaType.
     *
     * @param tlApellido2Interesado el nuevo tl apellido 2 interesado
     */
    public void setTlApellido2Interesado(java.lang.String tlApellido2Interesado) {
        this.tlApellido2Interesado = tlApellido2Interesado;
    }


    /**
     * Gets the tlRazonSocialInteresado value for this CriteriosBusquedaType.
     * 
     * @return tlRazonSocialInteresado
     */
    public java.lang.String getTlRazonSocialInteresado() {
        return tlRazonSocialInteresado;
    }


    /**
     * Sets the tlRazonSocialInteresado value for this CriteriosBusquedaType.
     *
     * @param tlRazonSocialInteresado el nuevo tl razon social interesado
     */
    public void setTlRazonSocialInteresado(java.lang.String tlRazonSocialInteresado) {
        this.tlRazonSocialInteresado = tlRazonSocialInteresado;
    }


    /**
     * Gets the tlNumDocIdentificativoRepresentante value for this CriteriosBusquedaType.
     * 
     * @return tlNumDocIdentificativoRepresentante
     */
    public java.lang.String getTlNumDocIdentificativoRepresentante() {
        return tlNumDocIdentificativoRepresentante;
    }


    /**
     * Sets the tlNumDocIdentificativoRepresentante value for this CriteriosBusquedaType.
     *
     * @param tlNumDocIdentificativoRepresentante el nuevo tl num doc identificativo representante
     */
    public void setTlNumDocIdentificativoRepresentante(java.lang.String tlNumDocIdentificativoRepresentante) {
        this.tlNumDocIdentificativoRepresentante = tlNumDocIdentificativoRepresentante;
    }


    /**
     * Gets the tlNombreRepresentante value for this CriteriosBusquedaType.
     * 
     * @return tlNombreRepresentante
     */
    public java.lang.String getTlNombreRepresentante() {
        return tlNombreRepresentante;
    }


    /**
     * Sets the tlNombreRepresentante value for this CriteriosBusquedaType.
     *
     * @param tlNombreRepresentante el nuevo tl nombre representante
     */
    public void setTlNombreRepresentante(java.lang.String tlNombreRepresentante) {
        this.tlNombreRepresentante = tlNombreRepresentante;
    }


    /**
     * Gets the tlApellido1Representante value for this CriteriosBusquedaType.
     * 
     * @return tlApellido1Representante
     */
    public java.lang.String getTlApellido1Representante() {
        return tlApellido1Representante;
    }


    /**
     * Sets the tlApellido1Representante value for this CriteriosBusquedaType.
     *
     * @param tlApellido1Representante el nuevo tl apellido 1 representante
     */
    public void setTlApellido1Representante(java.lang.String tlApellido1Representante) {
        this.tlApellido1Representante = tlApellido1Representante;
    }


    /**
     * Gets the tlApellido2Representante value for this CriteriosBusquedaType.
     * 
     * @return tlApellido2Representante
     */
    public java.lang.String getTlApellido2Representante() {
        return tlApellido2Representante;
    }


    /**
     * Sets the tlApellido2Representante value for this CriteriosBusquedaType.
     *
     * @param tlApellido2Representante el nuevo tl apellido 2 representante
     */
    public void setTlApellido2Representante(java.lang.String tlApellido2Representante) {
        this.tlApellido2Representante = tlApellido2Representante;
    }


    /**
     * Gets the tlRazonSocialRepresentante value for this CriteriosBusquedaType.
     * 
     * @return tlRazonSocialRepresentante
     */
    public java.lang.String getTlRazonSocialRepresentante() {
        return tlRazonSocialRepresentante;
    }


    /**
     * Sets the tlRazonSocialRepresentante value for this CriteriosBusquedaType.
     *
     * @param tlRazonSocialRepresentante el nuevo tl razon social representante
     */
    public void setTlRazonSocialRepresentante(java.lang.String tlRazonSocialRepresentante) {
        this.tlRazonSocialRepresentante = tlRazonSocialRepresentante;
    }


    /**
     * Gets the firstResult value for this CriteriosBusquedaType.
     * 
     * @return firstResult
     */
    public java.lang.String getFirstResult() {
        return firstResult;
    }


    /**
     * Sets the firstResult value for this CriteriosBusquedaType.
     *
     * @param firstResult el nuevo first result
     */
    public void setFirstResult(java.lang.String firstResult) {
        this.firstResult = firstResult;
    }


    /**
     * Gets the maxResult value for this CriteriosBusquedaType.
     * 
     * @return maxResult
     */
    public java.lang.String getMaxResult() {
        return maxResult;
    }


    /**
     * Sets the maxResult value for this CriteriosBusquedaType.
     *
     * @param maxResult el nuevo max result
     */
    public void setMaxResult(java.lang.String maxResult) {
        this.maxResult = maxResult;
    }

    /** el equals calc. */
    private java.lang.Object __equalsCalc = null;
    
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CriteriosBusquedaType)) return false;
        CriteriosBusquedaType other = (CriteriosBusquedaType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.cdOficinaOrigen==null && other.getCdOficinaOrigen()==null) || 
             (this.cdOficinaOrigen!=null &&
              this.cdOficinaOrigen.equals(other.getCdOficinaOrigen()))) &&
            ((this.cdTipoRegistro==null && other.getCdTipoRegistro()==null) || 
             (this.cdTipoRegistro!=null &&
              this.cdTipoRegistro.equals(other.getCdTipoRegistro()))) &&
            ((this.nmRegistro==null && other.getNmRegistro()==null) || 
             (this.nmRegistro!=null &&
              this.nmRegistro.equals(other.getNmRegistro()))) &&
            ((this.feRegistroDesde==null && other.getFeRegistroDesde()==null) || 
             (this.feRegistroDesde!=null &&
              this.feRegistroDesde.equals(other.getFeRegistroDesde()))) &&
            ((this.feRegistroHasta==null && other.getFeRegistroHasta()==null) || 
             (this.feRegistroHasta!=null &&
              this.feRegistroHasta.equals(other.getFeRegistroHasta()))) &&
            ((this.cdUnidadOrigen==null && other.getCdUnidadOrigen()==null) || 
             (this.cdUnidadOrigen!=null &&
              this.cdUnidadOrigen.equals(other.getCdUnidadOrigen()))) &&
            ((this.cdUnidadDestino==null && other.getCdUnidadDestino()==null) || 
             (this.cdUnidadDestino!=null &&
              this.cdUnidadDestino.equals(other.getCdUnidadDestino()))) &&
            ((this.cdAsunto==null && other.getCdAsunto()==null) || 
             (this.cdAsunto!=null &&
              this.cdAsunto.equals(other.getCdAsunto()))) &&
            ((this.tlNumDocIdentificativoInteresado==null && other.getTlNumDocIdentificativoInteresado()==null) || 
             (this.tlNumDocIdentificativoInteresado!=null &&
              this.tlNumDocIdentificativoInteresado.equals(other.getTlNumDocIdentificativoInteresado()))) &&
            ((this.tlNombreInteresado==null && other.getTlNombreInteresado()==null) || 
             (this.tlNombreInteresado!=null &&
              this.tlNombreInteresado.equals(other.getTlNombreInteresado()))) &&
            ((this.tlApellido1Interesado==null && other.getTlApellido1Interesado()==null) || 
             (this.tlApellido1Interesado!=null &&
              this.tlApellido1Interesado.equals(other.getTlApellido1Interesado()))) &&
            ((this.tlApellido2Interesado==null && other.getTlApellido2Interesado()==null) || 
             (this.tlApellido2Interesado!=null &&
              this.tlApellido2Interesado.equals(other.getTlApellido2Interesado()))) &&
            ((this.tlRazonSocialInteresado==null && other.getTlRazonSocialInteresado()==null) || 
             (this.tlRazonSocialInteresado!=null &&
              this.tlRazonSocialInteresado.equals(other.getTlRazonSocialInteresado()))) &&
            ((this.tlNumDocIdentificativoRepresentante==null && other.getTlNumDocIdentificativoRepresentante()==null) || 
             (this.tlNumDocIdentificativoRepresentante!=null &&
              this.tlNumDocIdentificativoRepresentante.equals(other.getTlNumDocIdentificativoRepresentante()))) &&
            ((this.tlNombreRepresentante==null && other.getTlNombreRepresentante()==null) || 
             (this.tlNombreRepresentante!=null &&
              this.tlNombreRepresentante.equals(other.getTlNombreRepresentante()))) &&
            ((this.tlApellido1Representante==null && other.getTlApellido1Representante()==null) || 
             (this.tlApellido1Representante!=null &&
              this.tlApellido1Representante.equals(other.getTlApellido1Representante()))) &&
            ((this.tlApellido2Representante==null && other.getTlApellido2Representante()==null) || 
             (this.tlApellido2Representante!=null &&
              this.tlApellido2Representante.equals(other.getTlApellido2Representante()))) &&
            ((this.tlRazonSocialRepresentante==null && other.getTlRazonSocialRepresentante()==null) || 
             (this.tlRazonSocialRepresentante!=null &&
              this.tlRazonSocialRepresentante.equals(other.getTlRazonSocialRepresentante()))) &&
            ((this.firstResult==null && other.getFirstResult()==null) || 
             (this.firstResult!=null &&
              this.firstResult.equals(other.getFirstResult()))) &&
            ((this.maxResult==null && other.getMaxResult()==null) || 
             (this.maxResult!=null &&
              this.maxResult.equals(other.getMaxResult())));
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
        if (getCdOficinaOrigen() != null) {
            _hashCode += getCdOficinaOrigen().hashCode();
        }
        if (getCdTipoRegistro() != null) {
            _hashCode += getCdTipoRegistro().hashCode();
        }
        if (getNmRegistro() != null) {
            _hashCode += getNmRegistro().hashCode();
        }
        if (getFeRegistroDesde() != null) {
            _hashCode += getFeRegistroDesde().hashCode();
        }
        if (getFeRegistroHasta() != null) {
            _hashCode += getFeRegistroHasta().hashCode();
        }
        if (getCdUnidadOrigen() != null) {
            _hashCode += getCdUnidadOrigen().hashCode();
        }
        if (getCdUnidadDestino() != null) {
            _hashCode += getCdUnidadDestino().hashCode();
        }
        if (getCdAsunto() != null) {
            _hashCode += getCdAsunto().hashCode();
        }
        if (getTlNumDocIdentificativoInteresado() != null) {
            _hashCode += getTlNumDocIdentificativoInteresado().hashCode();
        }
        if (getTlNombreInteresado() != null) {
            _hashCode += getTlNombreInteresado().hashCode();
        }
        if (getTlApellido1Interesado() != null) {
            _hashCode += getTlApellido1Interesado().hashCode();
        }
        if (getTlApellido2Interesado() != null) {
            _hashCode += getTlApellido2Interesado().hashCode();
        }
        if (getTlRazonSocialInteresado() != null) {
            _hashCode += getTlRazonSocialInteresado().hashCode();
        }
        if (getTlNumDocIdentificativoRepresentante() != null) {
            _hashCode += getTlNumDocIdentificativoRepresentante().hashCode();
        }
        if (getTlNombreRepresentante() != null) {
            _hashCode += getTlNombreRepresentante().hashCode();
        }
        if (getTlApellido1Representante() != null) {
            _hashCode += getTlApellido1Representante().hashCode();
        }
        if (getTlApellido2Representante() != null) {
            _hashCode += getTlApellido2Representante().hashCode();
        }
        if (getTlRazonSocialRepresentante() != null) {
            _hashCode += getTlRazonSocialRepresentante().hashCode();
        }
        if (getFirstResult() != null) {
            _hashCode += getFirstResult().hashCode();
        }
        if (getMaxResult() != null) {
            _hashCode += getMaxResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    /** el type desc. */
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CriteriosBusquedaType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://busquedaregistros.ws2.rec2are.map.es", "CriteriosBusquedaType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cdOficinaOrigen");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cdOficinaOrigen"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cdTipoRegistro");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cdTipoRegistro"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmRegistro");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nmRegistro"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("feRegistroDesde");
        elemField.setXmlName(new javax.xml.namespace.QName("", "feRegistroDesde"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("feRegistroHasta");
        elemField.setXmlName(new javax.xml.namespace.QName("", "feRegistroHasta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cdUnidadOrigen");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cdUnidadOrigen"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cdUnidadDestino");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cdUnidadDestino"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cdAsunto");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cdAsunto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tlNumDocIdentificativoInteresado");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tlNumDocIdentificativoInteresado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tlNombreInteresado");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tlNombreInteresado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tlApellido1Interesado");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tlApellido1Interesado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tlApellido2Interesado");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tlApellido2Interesado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tlRazonSocialInteresado");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tlRazonSocialInteresado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tlNumDocIdentificativoRepresentante");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tlNumDocIdentificativoRepresentante"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tlNombreRepresentante");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tlNombreRepresentante"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tlApellido1Representante");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tlApellido1Representante"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tlApellido2Representante");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tlApellido2Representante"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tlRazonSocialRepresentante");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tlRazonSocialRepresentante"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("firstResult");
        elemField.setXmlName(new javax.xml.namespace.QName("", "firstResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("maxResult");
        elemField.setXmlName(new javax.xml.namespace.QName("", "maxResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
