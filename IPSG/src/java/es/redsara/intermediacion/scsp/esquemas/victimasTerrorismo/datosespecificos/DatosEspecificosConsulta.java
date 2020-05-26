/**
 * DatosEspecificosConsulta.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.redsara.intermediacion.scsp.esquemas.victimasTerrorismo.datosespecificos;

public class DatosEspecificosConsulta  implements java.io.Serializable {
    private java.util.Date fechaNacimiento;

    private java.lang.String poblacionNacimiento;


    private es.redsara.intermediacion.scsp.esquemas.victimasTerrorismo.datosespecificos.DerechoReclamado derechoReclamado;

    public DatosEspecificosConsulta() {
    }

    public DatosEspecificosConsulta(
            java.util.Date fechaNacimiento,
            java.lang.String poblacionNacimiento,
            es.redsara.intermediacion.scsp.esquemas.victimasTerrorismo.datosespecificos.DerechoReclamado derechoReclamado) {
            this.fechaNacimiento = fechaNacimiento;
            this.poblacionNacimiento = poblacionNacimiento;
            this.derechoReclamado = derechoReclamado;
    }

    public java.util.Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(java.util.Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public java.lang.String getPoblacionNacimiento() {
		return poblacionNacimiento;
	}

	public void setPoblacionNacimiento(java.lang.String poblacionNacimiento) {
		this.poblacionNacimiento = poblacionNacimiento;
	}

	public es.redsara.intermediacion.scsp.esquemas.victimasTerrorismo.datosespecificos.DerechoReclamado getDerechoReclamado() {
		return derechoReclamado;
	}

	public void setDerechoReclamado(
			es.redsara.intermediacion.scsp.esquemas.victimasTerrorismo.datosespecificos.DerechoReclamado derechoReclamado) {
		this.derechoReclamado = derechoReclamado;
	}

	private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DatosEspecificosConsulta)) return false;
        DatosEspecificosConsulta other = (DatosEspecificosConsulta) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
                ((this.fechaNacimiento==null && other.getFechaNacimiento()==null) || 
                (this.fechaNacimiento!=null &&
                 this.fechaNacimiento.equals(other.getFechaNacimiento()))) &&
               ((this.poblacionNacimiento==null && other.getPoblacionNacimiento()==null) || 
                (this.poblacionNacimiento!=null &&
                 this.poblacionNacimiento.equals(other.getPoblacionNacimiento()))) &&
               ((this.derechoReclamado==null && other.getDerechoReclamado()==null) || 
                (this.derechoReclamado!=null &&
                 this.derechoReclamado.equals(other.getDerechoReclamado())));
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
        if (getFechaNacimiento() != null) {
            _hashCode += getFechaNacimiento().hashCode();
        }
        if (getPoblacionNacimiento() != null) {
            _hashCode += getPoblacionNacimiento().hashCode();
        }
        if (getDerechoReclamado() != null) {
            _hashCode += getDerechoReclamado().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DatosEspecificosConsulta.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">DatosEspecificos>Consulta"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaNacimiento");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "FechaNacimiento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("poblacionNacimiento");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "PoblacionNacimiento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">PoblacionNacimiento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("derechoReclamado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "DerechoReclamado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">DerechoReclamado"));
        elemField.setNillable(true);
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
