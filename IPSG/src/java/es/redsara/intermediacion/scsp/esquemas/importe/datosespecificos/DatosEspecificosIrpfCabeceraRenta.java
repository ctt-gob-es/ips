/**
 * DatosEspecificosIrpfCabeceraRenta.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.redsara.intermediacion.scsp.esquemas.importe.datosespecificos;

public class DatosEspecificosIrpfCabeceraRenta  implements java.io.Serializable {
    private java.lang.String nifSolicitante;

    private java.lang.String nombreSolicitante;

    private java.lang.String primerDeclarante;

    private java.lang.String segundoTitular;

    private java.lang.String modelo;

    private java.lang.String tributacion;

    private java.lang.String origenDatos;

    public DatosEspecificosIrpfCabeceraRenta() {
    }

    public DatosEspecificosIrpfCabeceraRenta(
           java.lang.String nifSolicitante,
           java.lang.String nombreSolicitante,
           java.lang.String primerDeclarante,
           java.lang.String segundoTitular,
           java.lang.String modelo,
           java.lang.String tributacion,
           java.lang.String origenDatos) {
           this.nifSolicitante = nifSolicitante;
           this.nombreSolicitante = nombreSolicitante;
           this.primerDeclarante = primerDeclarante;
           this.segundoTitular = segundoTitular;
           this.modelo = modelo;
           this.tributacion = tributacion;
           this.origenDatos = origenDatos;
    }


    /**
     * Gets the nifSolicitante value for this DatosEspecificosIrpfCabeceraRenta.
     * 
     * @return nifSolicitante
     */
    public java.lang.String getNifSolicitante() {
        return nifSolicitante;
    }


    /**
     * Sets the nifSolicitante value for this DatosEspecificosIrpfCabeceraRenta.
     * 
     * @param nifSolicitante
     */
    public void setNifSolicitante(java.lang.String nifSolicitante) {
        this.nifSolicitante = nifSolicitante;
    }


    /**
     * Gets the nombreSolicitante value for this DatosEspecificosIrpfCabeceraRenta.
     * 
     * @return nombreSolicitante
     */
    public java.lang.String getNombreSolicitante() {
        return nombreSolicitante;
    }


    /**
     * Sets the nombreSolicitante value for this DatosEspecificosIrpfCabeceraRenta.
     * 
     * @param nombreSolicitante
     */
    public void setNombreSolicitante(java.lang.String nombreSolicitante) {
        this.nombreSolicitante = nombreSolicitante;
    }


    /**
     * Gets the primerDeclarante value for this DatosEspecificosIrpfCabeceraRenta.
     * 
     * @return primerDeclarante
     */
    public java.lang.String getPrimerDeclarante() {
        return primerDeclarante;
    }


    /**
     * Sets the primerDeclarante value for this DatosEspecificosIrpfCabeceraRenta.
     * 
     * @param primerDeclarante
     */
    public void setPrimerDeclarante(java.lang.String primerDeclarante) {
        this.primerDeclarante = primerDeclarante;
    }


    /**
     * Gets the segundoTitular value for this DatosEspecificosIrpfCabeceraRenta.
     * 
     * @return segundoTitular
     */
    public java.lang.String getSegundoTitular() {
        return segundoTitular;
    }


    /**
     * Sets the segundoTitular value for this DatosEspecificosIrpfCabeceraRenta.
     * 
     * @param segundoTitular
     */
    public void setSegundoTitular(java.lang.String segundoTitular) {
        this.segundoTitular = segundoTitular;
    }


    /**
     * Gets the modelo value for this DatosEspecificosIrpfCabeceraRenta.
     * 
     * @return modelo
     */
    public java.lang.String getModelo() {
        return modelo;
    }


    /**
     * Sets the modelo value for this DatosEspecificosIrpfCabeceraRenta.
     * 
     * @param modelo
     */
    public void setModelo(java.lang.String modelo) {
        this.modelo = modelo;
    }


    /**
     * Gets the tributacion value for this DatosEspecificosIrpfCabeceraRenta.
     * 
     * @return tributacion
     */
    public java.lang.String getTributacion() {
        return tributacion;
    }


    /**
     * Sets the tributacion value for this DatosEspecificosIrpfCabeceraRenta.
     * 
     * @param tributacion
     */
    public void setTributacion(java.lang.String tributacion) {
        this.tributacion = tributacion;
    }


    /**
     * Gets the origenDatos value for this DatosEspecificosIrpfCabeceraRenta.
     * 
     * @return origenDatos
     */
    public java.lang.String getOrigenDatos() {
        return origenDatos;
    }


    /**
     * Sets the origenDatos value for this DatosEspecificosIrpfCabeceraRenta.
     * 
     * @param origenDatos
     */
    public void setOrigenDatos(java.lang.String origenDatos) {
        this.origenDatos = origenDatos;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DatosEspecificosIrpfCabeceraRenta)) return false;
        DatosEspecificosIrpfCabeceraRenta other = (DatosEspecificosIrpfCabeceraRenta) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.nifSolicitante==null && other.getNifSolicitante()==null) || 
             (this.nifSolicitante!=null &&
              this.nifSolicitante.equals(other.getNifSolicitante()))) &&
            ((this.nombreSolicitante==null && other.getNombreSolicitante()==null) || 
             (this.nombreSolicitante!=null &&
              this.nombreSolicitante.equals(other.getNombreSolicitante()))) &&
            ((this.primerDeclarante==null && other.getPrimerDeclarante()==null) || 
             (this.primerDeclarante!=null &&
              this.primerDeclarante.equals(other.getPrimerDeclarante()))) &&
            ((this.segundoTitular==null && other.getSegundoTitular()==null) || 
             (this.segundoTitular!=null &&
              this.segundoTitular.equals(other.getSegundoTitular()))) &&
            ((this.modelo==null && other.getModelo()==null) || 
             (this.modelo!=null &&
              this.modelo.equals(other.getModelo()))) &&
            ((this.tributacion==null && other.getTributacion()==null) || 
             (this.tributacion!=null &&
              this.tributacion.equals(other.getTributacion()))) &&
            ((this.origenDatos==null && other.getOrigenDatos()==null) || 
             (this.origenDatos!=null &&
              this.origenDatos.equals(other.getOrigenDatos())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getNifSolicitante() != null) {
            _hashCode += getNifSolicitante().hashCode();
        }
        if (getNombreSolicitante() != null) {
            _hashCode += getNombreSolicitante().hashCode();
        }
        if (getPrimerDeclarante() != null) {
            _hashCode += getPrimerDeclarante().hashCode();
        }
        if (getSegundoTitular() != null) {
            _hashCode += getSegundoTitular().hashCode();
        }
        if (getModelo() != null) {
            _hashCode += getModelo().hashCode();
        }
        if (getTributacion() != null) {
            _hashCode += getTributacion().hashCode();
        }
        if (getOrigenDatos() != null) {
            _hashCode += getOrigenDatos().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DatosEspecificosIrpfCabeceraRenta.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">>>DatosEspecificos>irpf>CabeceraRenta"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nifSolicitante");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "NifSolicitante"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombreSolicitante");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "NombreSolicitante"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("primerDeclarante");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "PrimerDeclarante"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("segundoTitular");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "SegundoTitular"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("modelo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "Modelo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tributacion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "Tributacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("origenDatos");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "OrigenDatos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
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
     * Get Custom Deserializer
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
