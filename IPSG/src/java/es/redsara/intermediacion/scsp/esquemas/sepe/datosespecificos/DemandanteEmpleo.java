/**
 * DemandanteEmpleo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.redsara.intermediacion.scsp.esquemas.sepe.datosespecificos;

/**
 * El Class DemandanteEmpleo.
 */
public class DemandanteEmpleo  implements java.io.Serializable {
    
    /** el fecha consulta. */
    /* Fecha por la que se pregunta si el demandante
     * 						estaba inscrito en los servicios públicos de empleo. Formato:
     * 						AAAAMMDD */
    private java.lang.String fechaConsulta;

    /** el inscrito. */
    /* Devolverá valor S cuando cumpla que a la fecha
     * 						indicada el demandante estaba inscrito en los servicios públicos
     * 						de empleo. Si no cumple la condición, se devolverá valor N. */
    private es.redsara.intermediacion.scsp.esquemas.sepe.datosespecificos.Inscrito inscrito;

    /** el fecha ultima inscripcion. */
    /* Si el valor Inscrito es SI, Fecha desde la que
     * 						lleva inscrito de manera continua en los servicios públicos
     * de
     * 						empleo. Formato: AAAAMMDD */
    private java.lang.String fechaUltimaInscripcion;

    /** el larga duracion. */
    /* Bloque informativo como demandante de empleo de
     * 						larga duración */
    private es.redsara.intermediacion.scsp.esquemas.sepe.datosespecificos.LargaDuracion largaDuracion;

    /**
     * Crea una nueva demandante empleo.
     */
    public DemandanteEmpleo() {
    }

    /**
     * Crea una nueva demandante empleo.
     *
     * @param fechaConsulta el fecha consulta
     * @param inscrito el inscrito
     * @param fechaUltimaInscripcion el fecha ultima inscripcion
     * @param largaDuracion el larga duracion
     */
    public DemandanteEmpleo(
           java.lang.String fechaConsulta,
           es.redsara.intermediacion.scsp.esquemas.sepe.datosespecificos.Inscrito inscrito,
           java.lang.String fechaUltimaInscripcion,
           es.redsara.intermediacion.scsp.esquemas.sepe.datosespecificos.LargaDuracion largaDuracion) {
           this.fechaConsulta = fechaConsulta;
           this.inscrito = inscrito;
           this.fechaUltimaInscripcion = fechaUltimaInscripcion;
           this.largaDuracion = largaDuracion;
    }


    /**
     * Gets the fechaConsulta value for this DemandanteEmpleo.
     * 
     * @return fechaConsulta   * Fecha por la que se pregunta si el demandante
     * 						estaba inscrito en los servicios públicos de empleo. Formato:
     * 						AAAAMMDD
     */
    public java.lang.String getFechaConsulta() {
        return fechaConsulta;
    }


    /**
     * Sets the fechaConsulta value for this DemandanteEmpleo.
     * 
     * @param fechaConsulta   * Fecha por la que se pregunta si el demandante
     * 						estaba inscrito en los servicios públicos de empleo. Formato:
     * 						AAAAMMDD
     */
    public void setFechaConsulta(java.lang.String fechaConsulta) {
        this.fechaConsulta = fechaConsulta;
    }


    /**
     * Gets the inscrito value for this DemandanteEmpleo.
     * 
     * @return inscrito   * Devolverá valor S cuando cumpla que a la fecha
     * 						indicada el demandante estaba inscrito en los servicios públicos
     * 						de empleo. Si no cumple la condición, se devolverá valor N.
     */
    public es.redsara.intermediacion.scsp.esquemas.sepe.datosespecificos.Inscrito getInscrito() {
        return inscrito;
    }


    /**
     * Sets the inscrito value for this DemandanteEmpleo.
     * 
     * @param inscrito   * Devolverá valor S cuando cumpla que a la fecha
     * 						indicada el demandante estaba inscrito en los servicios públicos
     * 						de empleo. Si no cumple la condición, se devolverá valor N.
     */
    public void setInscrito(es.redsara.intermediacion.scsp.esquemas.sepe.datosespecificos.Inscrito inscrito) {
        this.inscrito = inscrito;
    }


    /**
     * Gets the fechaUltimaInscripcion value for this DemandanteEmpleo.
     * 
     * @return fechaUltimaInscripcion   * Si el valor Inscrito es SI, Fecha desde la que
     * 						lleva inscrito de manera continua en los servicios públicos
     * de
     * 						empleo. Formato: AAAAMMDD
     */
    public java.lang.String getFechaUltimaInscripcion() {
        return fechaUltimaInscripcion;
    }


    /**
     * Sets the fechaUltimaInscripcion value for this DemandanteEmpleo.
     * 
     * @param fechaUltimaInscripcion   * Si el valor Inscrito es SI, Fecha desde la que
     * 						lleva inscrito de manera continua en los servicios públicos
     * de
     * 						empleo. Formato: AAAAMMDD
     */
    public void setFechaUltimaInscripcion(java.lang.String fechaUltimaInscripcion) {
        this.fechaUltimaInscripcion = fechaUltimaInscripcion;
    }


    /**
     * Gets the largaDuracion value for this DemandanteEmpleo.
     * 
     * @return largaDuracion   * Bloque informativo como demandante de empleo de
     * 						larga duración
     */
    public es.redsara.intermediacion.scsp.esquemas.sepe.datosespecificos.LargaDuracion getLargaDuracion() {
        return largaDuracion;
    }


    /**
     * Sets the largaDuracion value for this DemandanteEmpleo.
     * 
     * @param largaDuracion   * Bloque informativo como demandante de empleo de
     * 						larga duración
     */
    public void setLargaDuracion(es.redsara.intermediacion.scsp.esquemas.sepe.datosespecificos.LargaDuracion largaDuracion) {
        this.largaDuracion = largaDuracion;
    }

    /** el equals calc. */
    private java.lang.Object __equalsCalc = null;
    
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DemandanteEmpleo)) return false;
        DemandanteEmpleo other = (DemandanteEmpleo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.fechaConsulta==null && other.getFechaConsulta()==null) || 
             (this.fechaConsulta!=null &&
              this.fechaConsulta.equals(other.getFechaConsulta()))) &&
            ((this.inscrito==null && other.getInscrito()==null) || 
             (this.inscrito!=null &&
              this.inscrito.equals(other.getInscrito()))) &&
            ((this.fechaUltimaInscripcion==null && other.getFechaUltimaInscripcion()==null) || 
             (this.fechaUltimaInscripcion!=null &&
              this.fechaUltimaInscripcion.equals(other.getFechaUltimaInscripcion()))) &&
            ((this.largaDuracion==null && other.getLargaDuracion()==null) || 
             (this.largaDuracion!=null &&
              this.largaDuracion.equals(other.getLargaDuracion())));
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
        if (getFechaConsulta() != null) {
            _hashCode += getFechaConsulta().hashCode();
        }
        if (getInscrito() != null) {
            _hashCode += getInscrito().hashCode();
        }
        if (getFechaUltimaInscripcion() != null) {
            _hashCode += getFechaUltimaInscripcion().hashCode();
        }
        if (getLargaDuracion() != null) {
            _hashCode += getLargaDuracion().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    /** el type desc. */
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DemandanteEmpleo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">DemandanteEmpleo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaConsulta");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "FechaConsulta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("inscrito");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "Inscrito"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">Inscrito"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaUltimaInscripcion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "FechaUltimaInscripcion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("largaDuracion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "LargaDuracion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">LargaDuracion"));
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
