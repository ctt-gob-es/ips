/**
 * DatosNacimiento.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.redsara.intermediacion.scsp.esquemas.svdi.datosespecificos;

/**
 * El Class DatosNacimiento.
 */
public class DatosNacimiento  implements java.io.Serializable {

	/** La constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** el fecha. */
	/* Fecha de Nacimiento del ciudadano
     * Formato: AAAAMMDD.
     * Si los datos del día y del mes son desconocidos para un ciudadano
     * extranjero, se deberá preguntar por 99 para ambos datos, siendo el
     * año obligatorio que venga correctamente informado, Por ejemplo: 19749999 */
    private java.lang.String fecha;

    /** el provincia. */
    /* Código de la Provincia de nacimiento del ciudadano
     * Este campo irá codificado con los códigos del INE (2 dígitos del 01
     * al 52). Si el ciudadano es extranjero, el código de Provincia será
     * el 66.
     * https://idapadron.ine.es/ape403expl/inicio.menu */
    private java.lang.String provincia;

    /** el pais. */
    /* Código del país de nacimiento del ciudadano
     * Este campo va codificado según la normativa OACI (o en inglés ICAO)
     * que codifica con 3 caracteres todos los países/nacionalidades.
     * - Caso de un ciudadano español: si la provincia de nacimiento es española,
     * este campo va codificado con el código OACI ESP e indica que el país
     * de nacimiento es España. Si es un ciudadano español que no nació en
     * España, este campo vendrá codificado con el código OACI de su país
     * de nacimiento.
     * - Caso de ciudadano extranjero: Este campo tiene la codificación OACI
     * de su país de nacimiento.
     * 
     * ICAO establece la codificación ISO 3166-1 Alfa-3 */
    private java.lang.String pais;

    /**
     * Crea una nueva datos nacimiento.
     */
    public DatosNacimiento() {
    }

    /**
     * Crea una nueva datos nacimiento.
     *
     * @param fecha el fecha
     * @param provincia el provincia
     * @param pais el pais
     */
    public DatosNacimiento(
           java.lang.String fecha,
           java.lang.String provincia,
           java.lang.String pais) {
           this.fecha = fecha;
           this.provincia = provincia;
           this.pais = pais;
    }


    /**
     * Gets the fecha value for this DatosNacimiento.
     * 
     * @return fecha   * Fecha de Nacimiento del ciudadano
     * Formato: AAAAMMDD.
     * Si los datos del día y del mes son desconocidos para un ciudadano
     * extranjero, se deberá preguntar por 99 para ambos datos, siendo el
     * año obligatorio que venga correctamente informado, Por ejemplo: 19749999
     */
    public java.lang.String getFecha() {
        return fecha;
    }


    /**
     * Sets the fecha value for this DatosNacimiento.
     * 
     * @param fecha   * Fecha de Nacimiento del ciudadano
     * Formato: AAAAMMDD.
     * Si los datos del día y del mes son desconocidos para un ciudadano
     * extranjero, se deberá preguntar por 99 para ambos datos, siendo el
     * año obligatorio que venga correctamente informado, Por ejemplo: 19749999
     */
    public void setFecha(java.lang.String fecha) {
        this.fecha = fecha;
    }


    /**
     * Gets the provincia value for this DatosNacimiento.
     * 
     * @return provincia   * Código de la Provincia de nacimiento del ciudadano
     * Este campo irá codificado con los códigos del INE (2 dígitos del 01
     * al 52). Si el ciudadano es extranjero, el código de Provincia será
     * el 66.
     * https://idapadron.ine.es/ape403expl/inicio.menu
     */
    public java.lang.String getProvincia() {
        return provincia;
    }


    /**
     * Sets the provincia value for this DatosNacimiento.
     * 
     * @param provincia   * Código de la Provincia de nacimiento del ciudadano
     * Este campo irá codificado con los códigos del INE (2 dígitos del 01
     * al 52). Si el ciudadano es extranjero, el código de Provincia será
     * el 66.
     * https://idapadron.ine.es/ape403expl/inicio.menu
     */
    public void setProvincia(java.lang.String provincia) {
        this.provincia = provincia;
    }


    /**
     * Gets the pais value for this DatosNacimiento.
     * 
     * @return pais   * Código del país de nacimiento del ciudadano
     * Este campo va codificado según la normativa OACI (o en inglés ICAO)
     * que codifica con 3 caracteres todos los países/nacionalidades.
     * - Caso de un ciudadano español: si la provincia de nacimiento es española,
     * este campo va codificado con el código OACI ESP e indica que el país
     * de nacimiento es España. Si es un ciudadano español que no nació en
     * España, este campo vendrá codificado con el código OACI de su país
     * de nacimiento.
     * - Caso de ciudadano extranjero: Este campo tiene la codificación OACI
     * de su país de nacimiento.
     * 
     * ICAO establece la codificación ISO 3166-1 Alfa-3
     */
    public java.lang.String getPais() {
        return pais;
    }


    /**
     * Sets the pais value for this DatosNacimiento.
     * 
     * @param pais   * Código del país de nacimiento del ciudadano
     * Este campo va codificado según la normativa OACI (o en inglés ICAO)
     * que codifica con 3 caracteres todos los países/nacionalidades.
     * - Caso de un ciudadano español: si la provincia de nacimiento es española,
     * este campo va codificado con el código OACI ESP e indica que el país
     * de nacimiento es España. Si es un ciudadano español que no nació en
     * España, este campo vendrá codificado con el código OACI de su país
     * de nacimiento.
     * - Caso de ciudadano extranjero: Este campo tiene la codificación OACI
     * de su país de nacimiento.
     * 
     * ICAO establece la codificación ISO 3166-1 Alfa-3
     */
    public void setPais(java.lang.String pais) {
        this.pais = pais;
    }

    /** el equals calc. */
    private java.lang.Object __equalsCalc = null;
    
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DatosNacimiento)) return false;
        DatosNacimiento other = (DatosNacimiento) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.fecha==null && other.getFecha()==null) || 
             (this.fecha!=null &&
              this.fecha.equals(other.getFecha()))) &&
            ((this.provincia==null && other.getProvincia()==null) || 
             (this.provincia!=null &&
              this.provincia.equals(other.getProvincia()))) &&
            ((this.pais==null && other.getPais()==null) || 
             (this.pais!=null &&
              this.pais.equals(other.getPais())));
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
        if (getFecha() != null) {
            _hashCode += getFecha().hashCode();
        }
        if (getProvincia() != null) {
            _hashCode += getProvincia().hashCode();
        }
        if (getPais() != null) {
            _hashCode += getPais().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    /** el type desc. */
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DatosNacimiento.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">DatosNacimiento"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fecha");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "Fecha"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">Fecha"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("provincia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "Provincia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">Provincia"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pais");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "Pais"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">Pais"));
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
