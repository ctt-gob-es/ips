/**
 * Retorno.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.redsara.intermediacion.scsp.esquemas.fnumerosa.datosespecificos;

/**
 * El Class Retorno.
 */
public class Retorno  implements java.io.Serializable {
    
    /** el estado. */
    private es.redsara.intermediacion.scsp.esquemas.fnumerosa.datosespecificos.Estado estado;

    /** el titulo familia numerosa retorno. */
    /* Obligatorio si Estado no es un ERROR */
    private es.redsara.intermediacion.scsp.esquemas.fnumerosa.datosespecificos.TituloFamiliaNumerosaRetorno tituloFamiliaNumerosaRetorno;

    /** el lista beneficiarios retorno. */
    private es.redsara.intermediacion.scsp.esquemas.fnumerosa.datosespecificos.BeneficiarioRetorno[] listaBeneficiariosRetorno;

    /**
     * Crea una nueva retorno.
     */
    public Retorno() {
    }

    /**
     * Crea una nueva retorno.
     *
     * @param estado el estado
     * @param tituloFamiliaNumerosaRetorno el titulo familia numerosa retorno
     * @param listaBeneficiariosRetorno el lista beneficiarios retorno
     */
    public Retorno(
           es.redsara.intermediacion.scsp.esquemas.fnumerosa.datosespecificos.Estado estado,
           es.redsara.intermediacion.scsp.esquemas.fnumerosa.datosespecificos.TituloFamiliaNumerosaRetorno tituloFamiliaNumerosaRetorno,
           es.redsara.intermediacion.scsp.esquemas.fnumerosa.datosespecificos.BeneficiarioRetorno[] listaBeneficiariosRetorno) {
           this.estado = estado;
           this.tituloFamiliaNumerosaRetorno = tituloFamiliaNumerosaRetorno;
           this.listaBeneficiariosRetorno = listaBeneficiariosRetorno;
    }


    /**
     * Gets the estado value for this Retorno.
     * 
     * @return estado
     */
    public es.redsara.intermediacion.scsp.esquemas.fnumerosa.datosespecificos.Estado getEstado() {
        return estado;
    }


    /**
     * Sets the estado value for this Retorno.
     *
     * @param estado el nuevo estado
     */
    public void setEstado(es.redsara.intermediacion.scsp.esquemas.fnumerosa.datosespecificos.Estado estado) {
        this.estado = estado;
    }


    /**
     * Gets the tituloFamiliaNumerosaRetorno value for this Retorno.
     * 
     * @return tituloFamiliaNumerosaRetorno   * Obligatorio si Estado no es un ERROR
     */
    public es.redsara.intermediacion.scsp.esquemas.fnumerosa.datosespecificos.TituloFamiliaNumerosaRetorno getTituloFamiliaNumerosaRetorno() {
        return tituloFamiliaNumerosaRetorno;
    }


    /**
     * Sets the tituloFamiliaNumerosaRetorno value for this Retorno.
     * 
     * @param tituloFamiliaNumerosaRetorno   * Obligatorio si Estado no es un ERROR
     */
    public void setTituloFamiliaNumerosaRetorno(es.redsara.intermediacion.scsp.esquemas.fnumerosa.datosespecificos.TituloFamiliaNumerosaRetorno tituloFamiliaNumerosaRetorno) {
        this.tituloFamiliaNumerosaRetorno = tituloFamiliaNumerosaRetorno;
    }


    /**
     * Gets the listaBeneficiariosRetorno value for this Retorno.
     * 
     * @return listaBeneficiariosRetorno
     */
    public es.redsara.intermediacion.scsp.esquemas.fnumerosa.datosespecificos.BeneficiarioRetorno[] getListaBeneficiariosRetorno() {
        return listaBeneficiariosRetorno;
    }


    /**
     * Sets the listaBeneficiariosRetorno value for this Retorno.
     *
     * @param listaBeneficiariosRetorno el nuevo lista beneficiarios retorno
     */
    public void setListaBeneficiariosRetorno(es.redsara.intermediacion.scsp.esquemas.fnumerosa.datosespecificos.BeneficiarioRetorno[] listaBeneficiariosRetorno) {
        this.listaBeneficiariosRetorno = listaBeneficiariosRetorno;
    }

    /** el equals calc. */
    private java.lang.Object __equalsCalc = null;
    
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Retorno)) return false;
        Retorno other = (Retorno) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.estado==null && other.getEstado()==null) || 
             (this.estado!=null &&
              this.estado.equals(other.getEstado()))) &&
            ((this.tituloFamiliaNumerosaRetorno==null && other.getTituloFamiliaNumerosaRetorno()==null) || 
             (this.tituloFamiliaNumerosaRetorno!=null &&
              this.tituloFamiliaNumerosaRetorno.equals(other.getTituloFamiliaNumerosaRetorno()))) &&
            ((this.listaBeneficiariosRetorno==null && other.getListaBeneficiariosRetorno()==null) || 
             (this.listaBeneficiariosRetorno!=null &&
              java.util.Arrays.equals(this.listaBeneficiariosRetorno, other.getListaBeneficiariosRetorno())));
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
        if (getEstado() != null) {
            _hashCode += getEstado().hashCode();
        }
        if (getTituloFamiliaNumerosaRetorno() != null) {
            _hashCode += getTituloFamiliaNumerosaRetorno().hashCode();
        }
        if (getListaBeneficiariosRetorno() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaBeneficiariosRetorno());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaBeneficiariosRetorno(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    /** el type desc. */
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Retorno.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">Retorno"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("estado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "Estado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">Estado"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tituloFamiliaNumerosaRetorno");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "TituloFamiliaNumerosaRetorno"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">TituloFamiliaNumerosaRetorno"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaBeneficiariosRetorno");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "ListaBeneficiariosRetorno"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">ListaBeneficiariosRetorno"));
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
