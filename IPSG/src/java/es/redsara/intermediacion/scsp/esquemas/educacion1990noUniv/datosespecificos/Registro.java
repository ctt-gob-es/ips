/**
 * Registro.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.redsara.intermediacion.scsp.esquemas.educacion1990noUniv.datosespecificos;

/**
 * El Class Registro.
 */
public class Registro  implements java.io.Serializable {
    
    /** el numero orden libro. */
    private java.lang.String numeroOrdenLibro;

    /** el numero libro. */
    private java.lang.String numeroLibro;

    /** el numero folio. */
    private java.lang.String numeroFolio;

    /**
     * Crea una nueva registro.
     */
    public Registro() {
    }

    /**
     * Crea una nueva registro.
     *
     * @param numeroOrdenLibro el numero orden libro
     * @param numeroLibro el numero libro
     * @param numeroFolio el numero folio
     */
    public Registro(
           java.lang.String numeroOrdenLibro,
           java.lang.String numeroLibro,
           java.lang.String numeroFolio) {
           this.numeroOrdenLibro = numeroOrdenLibro;
           this.numeroLibro = numeroLibro;
           this.numeroFolio = numeroFolio;
    }


    /**
     * Gets the numeroOrdenLibro value for this Registro.
     * 
     * @return numeroOrdenLibro
     */
    public java.lang.String getNumeroOrdenLibro() {
        return numeroOrdenLibro;
    }


    /**
     * Sets the numeroOrdenLibro value for this Registro.
     *
     * @param numeroOrdenLibro el nuevo numero orden libro
     */
    public void setNumeroOrdenLibro(java.lang.String numeroOrdenLibro) {
        this.numeroOrdenLibro = numeroOrdenLibro;
    }


    /**
     * Gets the numeroLibro value for this Registro.
     * 
     * @return numeroLibro
     */
    public java.lang.String getNumeroLibro() {
        return numeroLibro;
    }


    /**
     * Sets the numeroLibro value for this Registro.
     *
     * @param numeroLibro el nuevo numero libro
     */
    public void setNumeroLibro(java.lang.String numeroLibro) {
        this.numeroLibro = numeroLibro;
    }


    /**
     * Gets the numeroFolio value for this Registro.
     * 
     * @return numeroFolio
     */
    public java.lang.String getNumeroFolio() {
        return numeroFolio;
    }


    /**
     * Sets the numeroFolio value for this Registro.
     *
     * @param numeroFolio el nuevo numero folio
     */
    public void setNumeroFolio(java.lang.String numeroFolio) {
        this.numeroFolio = numeroFolio;
    }

    /** el equals calc. */
    private java.lang.Object __equalsCalc = null;
    
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Registro)) return false;
        Registro other = (Registro) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.numeroOrdenLibro==null && other.getNumeroOrdenLibro()==null) || 
             (this.numeroOrdenLibro!=null &&
              this.numeroOrdenLibro.equals(other.getNumeroOrdenLibro()))) &&
            ((this.numeroLibro==null && other.getNumeroLibro()==null) || 
             (this.numeroLibro!=null &&
              this.numeroLibro.equals(other.getNumeroLibro()))) &&
            ((this.numeroFolio==null && other.getNumeroFolio()==null) || 
             (this.numeroFolio!=null &&
              this.numeroFolio.equals(other.getNumeroFolio())));
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
        if (getNumeroOrdenLibro() != null) {
            _hashCode += getNumeroOrdenLibro().hashCode();
        }
        if (getNumeroLibro() != null) {
            _hashCode += getNumeroLibro().hashCode();
        }
        if (getNumeroFolio() != null) {
            _hashCode += getNumeroFolio().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    /** el type desc. */
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Registro.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">Registro"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroOrdenLibro");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "NumeroOrdenLibro"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">NumeroOrdenLibro"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroLibro");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "NumeroLibro"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">NumeroLibro"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroFolio");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "NumeroFolio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">NumeroFolio"));
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
