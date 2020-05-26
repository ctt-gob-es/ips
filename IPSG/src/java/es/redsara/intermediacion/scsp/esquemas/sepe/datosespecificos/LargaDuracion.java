/**
 * LargaDuracion.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.redsara.intermediacion.scsp.esquemas.sepe.datosespecificos;

/**
 * El Class LargaDuracion.
 */
public class LargaDuracion  implements java.io.Serializable {
    
    /** el estado demandante empleo. */
    /* Devolverá valor S cuando cumpla que a la fecha
     * 						indicada el demandante había estado inscrito al menos 360 días
     * en
     * 						los 540 días anteriores a la fecha indicada. Si no cumple la
     * 						condición, se devolverá valor N. Se tiene en cuenta el calculo
     * de
     * 						ERE, etc.. A CONCRETAR POR SEPE */
    private es.redsara.intermediacion.scsp.esquemas.sepe.datosespecificos.Inscrito estadoDemandanteEmpleo;

    /** el dias demandante empleo. */
    /* Número de días dado de alta como demandante de
     * 						empleo desde la fecha de la consulta, hasta la inscripción
     * 						considerada. COINCIDIR�? CON EL VALOR MOSTRADO AL CIUDADANO. */
    private int diasDemandanteEmpleo;

    /**
     * Crea una nueva larga duracion.
     */
    public LargaDuracion() {
    }

    /**
     * Crea una nueva larga duracion.
     *
     * @param estadoDemandanteEmpleo el estado demandante empleo
     * @param diasDemandanteEmpleo el dias demandante empleo
     */
    public LargaDuracion(
           es.redsara.intermediacion.scsp.esquemas.sepe.datosespecificos.Inscrito estadoDemandanteEmpleo,
           int diasDemandanteEmpleo) {
           this.estadoDemandanteEmpleo = estadoDemandanteEmpleo;
           this.diasDemandanteEmpleo = diasDemandanteEmpleo;
    }


    /**
     * Gets the estadoDemandanteEmpleo value for this LargaDuracion.
     * 
     * @return estadoDemandanteEmpleo   * Devolverá valor S cuando cumpla que a la fecha
     * 						indicada el demandante había estado inscrito al menos 360 días
     * en
     * 						los 540 días anteriores a la fecha indicada. Si no cumple la
     * 						condición, se devolverá valor N. Se tiene en cuenta el calculo
     * de
     * 						ERE, etc.. A CONCRETAR POR SEPE
     */
    public es.redsara.intermediacion.scsp.esquemas.sepe.datosespecificos.Inscrito getEstadoDemandanteEmpleo() {
        return estadoDemandanteEmpleo;
    }


    /**
     * Sets the estadoDemandanteEmpleo value for this LargaDuracion.
     * 
     * @param estadoDemandanteEmpleo   * Devolverá valor S cuando cumpla que a la fecha
     * 						indicada el demandante había estado inscrito al menos 360 días
     * en
     * 						los 540 días anteriores a la fecha indicada. Si no cumple la
     * 						condición, se devolverá valor N. Se tiene en cuenta el calculo
     * de
     * 						ERE, etc.. A CONCRETAR POR SEPE
     */
    public void setEstadoDemandanteEmpleo(es.redsara.intermediacion.scsp.esquemas.sepe.datosespecificos.Inscrito estadoDemandanteEmpleo) {
        this.estadoDemandanteEmpleo = estadoDemandanteEmpleo;
    }


    /**
     * Gets the diasDemandanteEmpleo value for this LargaDuracion.
     * 
     * @return diasDemandanteEmpleo   * Número de días dado de alta como demandante de
     * 						empleo desde la fecha de la consulta, hasta la inscripción
     * 						considerada. COINCIDIR�? CON EL VALOR MOSTRADO AL CIUDADANO.
     */
    public int getDiasDemandanteEmpleo() {
        return diasDemandanteEmpleo;
    }


    /**
     * Sets the diasDemandanteEmpleo value for this LargaDuracion.
     * 
     * @param diasDemandanteEmpleo   * Número de días dado de alta como demandante de
     * 						empleo desde la fecha de la consulta, hasta la inscripción
     * 						considerada. COINCIDIR�? CON EL VALOR MOSTRADO AL CIUDADANO.
     */
    public void setDiasDemandanteEmpleo(int diasDemandanteEmpleo) {
        this.diasDemandanteEmpleo = diasDemandanteEmpleo;
    }

    /** el equals calc. */
    private java.lang.Object __equalsCalc = null;
    
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof LargaDuracion)) return false;
        LargaDuracion other = (LargaDuracion) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.estadoDemandanteEmpleo==null && other.getEstadoDemandanteEmpleo()==null) || 
             (this.estadoDemandanteEmpleo!=null &&
              this.estadoDemandanteEmpleo.equals(other.getEstadoDemandanteEmpleo()))) &&
            this.diasDemandanteEmpleo == other.getDiasDemandanteEmpleo();
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
        if (getEstadoDemandanteEmpleo() != null) {
            _hashCode += getEstadoDemandanteEmpleo().hashCode();
        }
        _hashCode += getDiasDemandanteEmpleo();
        __hashCodeCalc = false;
        return _hashCode;
    }

    /** el type desc. */
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(LargaDuracion.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">LargaDuracion"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("estadoDemandanteEmpleo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "EstadoDemandanteEmpleo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">Inscrito"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("diasDemandanteEmpleo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "DiasDemandanteEmpleo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
