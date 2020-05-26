/**
 * RegistroType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.map.ipsc.clienteWS.registrarRec3;

/**
 * El Class RegistroType.
 */
public class RegistroType  implements java.io.Serializable {
    
    /** el cd oficina origen. */
    private java.lang.String cdOficinaOrigen;

    /** el cd tipo registro. */
    private java.lang.String cdTipoRegistro;

    /** el cd unidad destino. */
    private java.lang.String cdUnidadDestino;

    /** el cd unidad origen. */
    private java.lang.String cdUnidadOrigen;

    /** el interesados. */
    private es.map.ipsc.clienteWS.registroRec3.type.InteresadoType[] interesados;

    /** el documentos. */
    private es.map.ipsc.clienteWS.registroRec3.type.DocumentoType[] documentos;

    /** el tl resumen. */
    private java.lang.String tlResumen;

    /** el cd asunto. */
    private java.lang.String cdAsunto;

    /** el tl referencia externa. */
    private java.lang.String tlReferenciaExterna;

    /** el tl numero expediente. */
    private java.lang.String tlNumeroExpediente;

    /** el cd tipo transporte. */
    private java.lang.String cdTipoTransporte;

    /** el tl numero transporte. */
    private java.lang.String tlNumeroTransporte;

    /** el tl nombre usuario. */
    private java.lang.String tlNombreUsuario;

    /** el tl contacto usuario. */
    private java.lang.String tlContactoUsuario;

    /** el cd documentacion fisica soportes. */
    private java.lang.String cdDocumentacionFisicaSoportes;

    /** el tl expone. */
    private java.lang.String tlExpone;

    /** el tl solicita. */
    private java.lang.String tlSolicita;

    /** el tl observaciones. */
    private java.lang.String tlObservaciones;
    
    /** el fl justificante. */
    private java.lang.String flJustificante;

    /**
     * Crea una nueva registro type.
     */
    public RegistroType() {
    }

    /**
     * Crea una nueva registro type.
     *
     * @param cdOficinaOrigen el cd oficina origen
     * @param cdTipoRegistro el cd tipo registro
     * @param cdUnidadDestino el cd unidad destino
     * @param cdUnidadOrigen el cd unidad origen
     * @param interesados el interesados
     * @param documentos el documentos
     * @param tlResumen el tl resumen
     * @param cdAsunto el cd asunto
     * @param tlReferenciaExterna el tl referencia externa
     * @param tlNumeroExpediente el tl numero expediente
     * @param cdTipoTransporte el cd tipo transporte
     * @param tlNumeroTransporte el tl numero transporte
     * @param tlNombreUsuario el tl nombre usuario
     * @param tlContactoUsuario el tl contacto usuario
     * @param cdDocumentacionFisicaSoportes el cd documentacion fisica soportes
     * @param tlExpone el tl expone
     * @param tlSolicita el tl solicita
     * @param tlObservaciones el tl observaciones
     * @param flJustificante el fl justificante
     */
    public RegistroType(
           java.lang.String cdOficinaOrigen,
           java.lang.String cdTipoRegistro,
           java.lang.String cdUnidadDestino,
           java.lang.String cdUnidadOrigen,
           es.map.ipsc.clienteWS.registroRec3.type.InteresadoType[] interesados,
           es.map.ipsc.clienteWS.registroRec3.type.DocumentoType[] documentos,
           java.lang.String tlResumen,
           java.lang.String cdAsunto,
           java.lang.String tlReferenciaExterna,
           java.lang.String tlNumeroExpediente,
           java.lang.String cdTipoTransporte,
           java.lang.String tlNumeroTransporte,
           java.lang.String tlNombreUsuario,
           java.lang.String tlContactoUsuario,
           java.lang.String cdDocumentacionFisicaSoportes,
           java.lang.String tlExpone,
           java.lang.String tlSolicita,
           java.lang.String tlObservaciones,
           java.lang.String flJustificante) {
           this.cdOficinaOrigen = cdOficinaOrigen;
           this.cdTipoRegistro = cdTipoRegistro;
           this.cdUnidadDestino = cdUnidadDestino;
           this.cdUnidadOrigen = cdUnidadOrigen;
           this.interesados = interesados;
           this.documentos = documentos;
           this.tlResumen = tlResumen;
           this.cdAsunto = cdAsunto;
           this.tlReferenciaExterna = tlReferenciaExterna;
           this.tlNumeroExpediente = tlNumeroExpediente;
           this.cdTipoTransporte = cdTipoTransporte;
           this.tlNumeroTransporte = tlNumeroTransporte;
           this.tlNombreUsuario = tlNombreUsuario;
           this.tlContactoUsuario = tlContactoUsuario;
           this.cdDocumentacionFisicaSoportes = cdDocumentacionFisicaSoportes;
           this.tlExpone = tlExpone;
           this.tlSolicita = tlSolicita;
           this.tlObservaciones = tlObservaciones;
           this.flJustificante = flJustificante;
    }


    /**
     * Gets the cdOficinaOrigen value for this RegistroType.
     * 
     * @return cdOficinaOrigen
     */
    public java.lang.String getCdOficinaOrigen() {
        return cdOficinaOrigen;
    }


    /**
     * Sets the cdOficinaOrigen value for this RegistroType.
     *
     * @param cdOficinaOrigen el nuevo cd oficina origen
     */
    public void setCdOficinaOrigen(java.lang.String cdOficinaOrigen) {
        this.cdOficinaOrigen = cdOficinaOrigen;
    }


    /**
     * Gets the cdTipoRegistro value for this RegistroType.
     * 
     * @return cdTipoRegistro
     */
    public java.lang.String getCdTipoRegistro() {
        return cdTipoRegistro;
    }


    /**
     * Sets the cdTipoRegistro value for this RegistroType.
     *
     * @param cdTipoRegistro el nuevo cd tipo registro
     */
    public void setCdTipoRegistro(java.lang.String cdTipoRegistro) {
        this.cdTipoRegistro = cdTipoRegistro;
    }


    /**
     * Gets the cdUnidadDestino value for this RegistroType.
     * 
     * @return cdUnidadDestino
     */
    public java.lang.String getCdUnidadDestino() {
        return cdUnidadDestino;
    }


    /**
     * Sets the cdUnidadDestino value for this RegistroType.
     *
     * @param cdUnidadDestino el nuevo cd unidad destino
     */
    public void setCdUnidadDestino(java.lang.String cdUnidadDestino) {
        this.cdUnidadDestino = cdUnidadDestino;
    }


    /**
     * Gets the cdUnidadOrigen value for this RegistroType.
     * 
     * @return cdUnidadOrigen
     */
    public java.lang.String getCdUnidadOrigen() {
        return cdUnidadOrigen;
    }


    /**
     * Sets the cdUnidadOrigen value for this RegistroType.
     *
     * @param cdUnidadOrigen el nuevo cd unidad origen
     */
    public void setCdUnidadOrigen(java.lang.String cdUnidadOrigen) {
        this.cdUnidadOrigen = cdUnidadOrigen;
    }


    /**
     * Gets the interesados value for this RegistroType.
     * 
     * @return interesados
     */
    public es.map.ipsc.clienteWS.registroRec3.type.InteresadoType[] getInteresados() {
        return interesados;
    }


    /**
     * Sets the interesados value for this RegistroType.
     *
     * @param interesados el nuevo interesados
     */
    public void setInteresados(es.map.ipsc.clienteWS.registroRec3.type.InteresadoType[] interesados) {
        this.interesados = interesados;
    }


    /**
     * Gets the documentos value for this RegistroType.
     * 
     * @return documentos
     */
    public es.map.ipsc.clienteWS.registroRec3.type.DocumentoType[] getDocumentos() {
        return documentos;
    }


    /**
     * Sets the documentos value for this RegistroType.
     *
     * @param documentos el nuevo documentos
     */
    public void setDocumentos(es.map.ipsc.clienteWS.registroRec3.type.DocumentoType[] documentos) {
        this.documentos = documentos;
    }


    /**
     * Gets the tlResumen value for this RegistroType.
     * 
     * @return tlResumen
     */
    public java.lang.String getTlResumen() {
        return tlResumen;
    }


    /**
     * Sets the tlResumen value for this RegistroType.
     *
     * @param tlResumen el nuevo tl resumen
     */
    public void setTlResumen(java.lang.String tlResumen) {
        this.tlResumen = tlResumen;
    }


    /**
     * Gets the cdAsunto value for this RegistroType.
     * 
     * @return cdAsunto
     */
    public java.lang.String getCdAsunto() {
        return cdAsunto;
    }


    /**
     * Sets the cdAsunto value for this RegistroType.
     *
     * @param cdAsunto el nuevo cd asunto
     */
    public void setCdAsunto(java.lang.String cdAsunto) {
        this.cdAsunto = cdAsunto;
    }


    /**
     * Gets the tlReferenciaExterna value for this RegistroType.
     * 
     * @return tlReferenciaExterna
     */
    public java.lang.String getTlReferenciaExterna() {
        return tlReferenciaExterna;
    }


    /**
     * Sets the tlReferenciaExterna value for this RegistroType.
     *
     * @param tlReferenciaExterna el nuevo tl referencia externa
     */
    public void setTlReferenciaExterna(java.lang.String tlReferenciaExterna) {
        this.tlReferenciaExterna = tlReferenciaExterna;
    }


    /**
     * Gets the tlNumeroExpediente value for this RegistroType.
     * 
     * @return tlNumeroExpediente
     */
    public java.lang.String getTlNumeroExpediente() {
        return tlNumeroExpediente;
    }


    /**
     * Sets the tlNumeroExpediente value for this RegistroType.
     *
     * @param tlNumeroExpediente el nuevo tl numero expediente
     */
    public void setTlNumeroExpediente(java.lang.String tlNumeroExpediente) {
        this.tlNumeroExpediente = tlNumeroExpediente;
    }


    /**
     * Gets the cdTipoTransporte value for this RegistroType.
     * 
     * @return cdTipoTransporte
     */
    public java.lang.String getCdTipoTransporte() {
        return cdTipoTransporte;
    }


    /**
     * Sets the cdTipoTransporte value for this RegistroType.
     *
     * @param cdTipoTransporte el nuevo cd tipo transporte
     */
    public void setCdTipoTransporte(java.lang.String cdTipoTransporte) {
        this.cdTipoTransporte = cdTipoTransporte;
    }


    /**
     * Gets the tlNumeroTransporte value for this RegistroType.
     * 
     * @return tlNumeroTransporte
     */
    public java.lang.String getTlNumeroTransporte() {
        return tlNumeroTransporte;
    }


    /**
     * Sets the tlNumeroTransporte value for this RegistroType.
     *
     * @param tlNumeroTransporte el nuevo tl numero transporte
     */
    public void setTlNumeroTransporte(java.lang.String tlNumeroTransporte) {
        this.tlNumeroTransporte = tlNumeroTransporte;
    }


    /**
     * Gets the tlNombreUsuario value for this RegistroType.
     * 
     * @return tlNombreUsuario
     */
    public java.lang.String getTlNombreUsuario() {
        return tlNombreUsuario;
    }


    /**
     * Sets the tlNombreUsuario value for this RegistroType.
     *
     * @param tlNombreUsuario el nuevo tl nombre usuario
     */
    public void setTlNombreUsuario(java.lang.String tlNombreUsuario) {
        this.tlNombreUsuario = tlNombreUsuario;
    }


    /**
     * Gets the tlContactoUsuario value for this RegistroType.
     * 
     * @return tlContactoUsuario
     */
    public java.lang.String getTlContactoUsuario() {
        return tlContactoUsuario;
    }


    /**
     * Sets the tlContactoUsuario value for this RegistroType.
     *
     * @param tlContactoUsuario el nuevo tl contacto usuario
     */
    public void setTlContactoUsuario(java.lang.String tlContactoUsuario) {
        this.tlContactoUsuario = tlContactoUsuario;
    }


    /**
     * Gets the cdDocumentacionFisicaSoportes value for this RegistroType.
     * 
     * @return cdDocumentacionFisicaSoportes
     */
    public java.lang.String getCdDocumentacionFisicaSoportes() {
        return cdDocumentacionFisicaSoportes;
    }


    /**
     * Sets the cdDocumentacionFisicaSoportes value for this RegistroType.
     *
     * @param cdDocumentacionFisicaSoportes el nuevo cd documentacion fisica soportes
     */
    public void setCdDocumentacionFisicaSoportes(java.lang.String cdDocumentacionFisicaSoportes) {
        this.cdDocumentacionFisicaSoportes = cdDocumentacionFisicaSoportes;
    }


    /**
     * Gets the tlExpone value for this RegistroType.
     * 
     * @return tlExpone
     */
    public java.lang.String getTlExpone() {
        return tlExpone;
    }


    /**
     * Sets the tlExpone value for this RegistroType.
     *
     * @param tlExpone el nuevo tl expone
     */
    public void setTlExpone(java.lang.String tlExpone) {
        this.tlExpone = tlExpone;
    }


    /**
     * Gets the tlSolicita value for this RegistroType.
     * 
     * @return tlSolicita
     */
    public java.lang.String getTlSolicita() {
        return tlSolicita;
    }


    /**
     * Sets the tlSolicita value for this RegistroType.
     *
     * @param tlSolicita el nuevo tl solicita
     */
    public void setTlSolicita(java.lang.String tlSolicita) {
        this.tlSolicita = tlSolicita;
    }


    /**
     * Gets the tlObservaciones value for this RegistroType.
     * 
     * @return tlObservaciones
     */
    public java.lang.String getTlObservaciones() {
        return tlObservaciones;
    }


    /**
     * Sets the tlObservaciones value for this RegistroType.
     *
     * @param tlObservaciones el nuevo tl observaciones
     */
    public void setTlObservaciones(java.lang.String tlObservaciones) {
        this.tlObservaciones = tlObservaciones;
    }
    
    

    /**
     * Obtiene el fl justificante.
     *
     * @return el fl justificante
     */
    public java.lang.String getFlJustificante() {
		return flJustificante;
	}

	/**
	 * Establece el fl justificante.
	 *
	 * @param flJustificante el nuevo fl justificante
	 */
	public void setFlJustificante(java.lang.String flJustificante) {
		this.flJustificante = flJustificante;
	}



	/** el equals calc. */
	private java.lang.Object __equalsCalc = null;
    
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RegistroType)) return false;
        RegistroType other = (RegistroType) obj;
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
            ((this.cdUnidadDestino==null && other.getCdUnidadDestino()==null) || 
             (this.cdUnidadDestino!=null &&
              this.cdUnidadDestino.equals(other.getCdUnidadDestino()))) &&
            ((this.cdUnidadOrigen==null && other.getCdUnidadOrigen()==null) || 
             (this.cdUnidadOrigen!=null &&
              this.cdUnidadOrigen.equals(other.getCdUnidadOrigen()))) &&
            ((this.interesados==null && other.getInteresados()==null) || 
             (this.interesados!=null &&
              java.util.Arrays.equals(this.interesados, other.getInteresados()))) &&
            ((this.documentos==null && other.getDocumentos()==null) || 
             (this.documentos!=null &&
              java.util.Arrays.equals(this.documentos, other.getDocumentos()))) &&
            ((this.tlResumen==null && other.getTlResumen()==null) || 
             (this.tlResumen!=null &&
              this.tlResumen.equals(other.getTlResumen()))) &&
            ((this.cdAsunto==null && other.getCdAsunto()==null) || 
             (this.cdAsunto!=null &&
              this.cdAsunto.equals(other.getCdAsunto()))) &&
            ((this.tlReferenciaExterna==null && other.getTlReferenciaExterna()==null) || 
             (this.tlReferenciaExterna!=null &&
              this.tlReferenciaExterna.equals(other.getTlReferenciaExterna()))) &&
            ((this.tlNumeroExpediente==null && other.getTlNumeroExpediente()==null) || 
             (this.tlNumeroExpediente!=null &&
              this.tlNumeroExpediente.equals(other.getTlNumeroExpediente()))) &&
            ((this.cdTipoTransporte==null && other.getCdTipoTransporte()==null) || 
             (this.cdTipoTransporte!=null &&
              this.cdTipoTransporte.equals(other.getCdTipoTransporte()))) &&
            ((this.tlNumeroTransporte==null && other.getTlNumeroTransporte()==null) || 
             (this.tlNumeroTransporte!=null &&
              this.tlNumeroTransporte.equals(other.getTlNumeroTransporte()))) &&
            ((this.tlNombreUsuario==null && other.getTlNombreUsuario()==null) || 
             (this.tlNombreUsuario!=null &&
              this.tlNombreUsuario.equals(other.getTlNombreUsuario()))) &&
            ((this.tlContactoUsuario==null && other.getTlContactoUsuario()==null) || 
             (this.tlContactoUsuario!=null &&
              this.tlContactoUsuario.equals(other.getTlContactoUsuario()))) &&
            ((this.cdDocumentacionFisicaSoportes==null && other.getCdDocumentacionFisicaSoportes()==null) || 
             (this.cdDocumentacionFisicaSoportes!=null &&
              this.cdDocumentacionFisicaSoportes.equals(other.getCdDocumentacionFisicaSoportes()))) &&
            ((this.tlExpone==null && other.getTlExpone()==null) || 
             (this.tlExpone!=null &&
              this.tlExpone.equals(other.getTlExpone()))) &&
            ((this.tlSolicita==null && other.getTlSolicita()==null) || 
             (this.tlSolicita!=null &&
              this.tlSolicita.equals(other.getTlSolicita()))) &&
            ((this.tlObservaciones==null && other.getTlObservaciones()==null) || 
             (this.tlObservaciones!=null &&
              this.tlObservaciones.equals(other.getTlObservaciones()))) &&
              ((this.flJustificante==null && other.getFlJustificante()==null) || 
                      (this.flJustificante!=null &&
                       this.flJustificante.equals(other.getFlJustificante())))
              ;
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
        if (getCdUnidadDestino() != null) {
            _hashCode += getCdUnidadDestino().hashCode();
        }
        if (getCdUnidadOrigen() != null) {
            _hashCode += getCdUnidadOrigen().hashCode();
        }
        if (getInteresados() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getInteresados());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getInteresados(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getDocumentos() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDocumentos());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDocumentos(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getTlResumen() != null) {
            _hashCode += getTlResumen().hashCode();
        }
        if (getCdAsunto() != null) {
            _hashCode += getCdAsunto().hashCode();
        }
        if (getTlReferenciaExterna() != null) {
            _hashCode += getTlReferenciaExterna().hashCode();
        }
        if (getTlNumeroExpediente() != null) {
            _hashCode += getTlNumeroExpediente().hashCode();
        }
        if (getCdTipoTransporte() != null) {
            _hashCode += getCdTipoTransporte().hashCode();
        }
        if (getTlNumeroTransporte() != null) {
            _hashCode += getTlNumeroTransporte().hashCode();
        }
        if (getTlNombreUsuario() != null) {
            _hashCode += getTlNombreUsuario().hashCode();
        }
        if (getTlContactoUsuario() != null) {
            _hashCode += getTlContactoUsuario().hashCode();
        }
        if (getCdDocumentacionFisicaSoportes() != null) {
            _hashCode += getCdDocumentacionFisicaSoportes().hashCode();
        }
        if (getTlExpone() != null) {
            _hashCode += getTlExpone().hashCode();
        }
        if (getTlSolicita() != null) {
            _hashCode += getTlSolicita().hashCode();
        }
        if (getTlObservaciones() != null) {
            _hashCode += getTlObservaciones().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    /** el type desc. */
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RegistroType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://registrar.ws2.rec2are.map.es", "RegistroType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cdOficinaOrigen");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cdOficinaOrigen"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cdTipoRegistro");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cdTipoRegistro"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cdUnidadDestino");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cdUnidadDestino"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cdUnidadOrigen");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cdUnidadOrigen"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("interesados");
        elemField.setXmlName(new javax.xml.namespace.QName("", "interesados"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://type.ws2.rec2are.map.es", "InteresadoType"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("documentos");
        elemField.setXmlName(new javax.xml.namespace.QName("", "documentos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://type.ws2.rec2are.map.es", "DocumentoType"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tlResumen");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tlResumen"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cdAsunto");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cdAsunto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tlReferenciaExterna");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tlReferenciaExterna"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tlNumeroExpediente");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tlNumeroExpediente"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cdTipoTransporte");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cdTipoTransporte"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tlNumeroTransporte");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tlNumeroTransporte"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tlNombreUsuario");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tlNombreUsuario"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tlContactoUsuario");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tlContactoUsuario"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cdDocumentacionFisicaSoportes");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cdDocumentacionFisicaSoportes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tlExpone");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tlExpone"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tlSolicita");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tlSolicita"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tlObservaciones");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tlObservaciones"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("flJustificante");
        elemField.setXmlName(new javax.xml.namespace.QName("", "flJustificante"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
