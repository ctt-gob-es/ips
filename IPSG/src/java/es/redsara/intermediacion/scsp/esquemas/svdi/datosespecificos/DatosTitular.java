/**
 * DatosTitular.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.redsara.intermediacion.scsp.esquemas.svdi.datosespecificos;

/**
 * El Class DatosTitular.
 */
public class DatosTitular  implements java.io.Serializable {

	/** La constante serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** el nombre. */
	private java.lang.String nombre;
    
    /** el apellido 1. */
    private java.lang.String apellido1;
    
    /** el apellido 2. */
    private java.lang.String apellido2;
    
    /** el nacionalidad. */
    private java.lang.String nacionalidad;
    
    /** el sexo. */
    private es.redsara.intermediacion.scsp.esquemas.svdi.datosespecificos.Sexo sexo;
    
    /** el datos nacimiento. */
    private es.redsara.intermediacion.scsp.esquemas.svdi.datosespecificos.DatosNacimientoType datosNacimiento;
    
    /** el nombre padre. */
    private java.lang.String nombrePadre;
    
    /** el nombre madre. */
    private java.lang.String nombreMadre;
    
    /** el fecha caducidad. */
    private java.lang.String fechaCaducidad;

    /**
     * Crea una nueva datos titular.
     */
    public DatosTitular() {
    }

    /**
     * Crea una nueva datos titular.
     *
     * @param nombre el nombre
     * @param apellido1 el apellido 1
     * @param apellido2 el apellido 2
     * @param nacionalidad el nacionalidad
     * @param sexo el sexo
     * @param datosNacimiento el datos nacimiento
     * @param nomPadre el nom padre
     * @param nomMadre el nom madre
     * @param fechaCaducidad el fecha caducidad
     */
    public DatosTitular(
           
           java.lang.String nombre,
           java.lang.String apellido1,
           java.lang.String apellido2,
           java.lang.String nacionalidad,
           es.redsara.intermediacion.scsp.esquemas.svdi.datosespecificos.Sexo sexo,
           es.redsara.intermediacion.scsp.esquemas.svdi.datosespecificos.DatosNacimientoType datosNacimiento,
           java.lang.String nomPadre,
           java.lang.String nomMadre,
           java.lang.String fechaCaducidad) {
          
           this.nombre = nombre;
           this.apellido1 = apellido1;
           this.apellido2 = apellido2;
           this.nacionalidad = nacionalidad;
           this.sexo = sexo;
           this.datosNacimiento = datosNacimiento;
           this.nombrePadre = nomPadre;
           this.nombreMadre = nomMadre;
           this.fechaCaducidad = fechaCaducidad;
    }


    /**
     * Gets the nombre value for this DatosTitular.
     * 
     * @return nombre
     */
    public java.lang.String getNombre() {
        return nombre;
    }


    /**
     * Sets the nombre value for this DatosTitular.
     *
     * @param nombre el nuevo nombre
     */
    public void setNombre(java.lang.String nombre) {
        this.nombre = nombre;
    }


    /**
     * Gets the apellido1 value for this DatosTitular.
     * 
     * @return apellido1
     */
    public java.lang.String getApellido1() {
        return apellido1;
    }


    /**
     * Sets the apellido1 value for this DatosTitular.
     *
     * @param apellido1 el nuevo apellido 1
     */
    public void setApellido1(java.lang.String apellido1) {
        this.apellido1 = apellido1;
    }


    /**
     * Gets the apellido2 value for this DatosTitular.
     * 
     * @return apellido2
     */
    public java.lang.String getApellido2() {
        return apellido2;
    }


    /**
     * Sets the apellido2 value for this DatosTitular.
     *
     * @param apellido2 el nuevo apellido 2
     */
    public void setApellido2(java.lang.String apellido2) {
        this.apellido2 = apellido2;
    }


    /**
     * Gets the nacionalidad value for this DatosTitular.
     * 
     * @return nacionalidad
     */
    public java.lang.String getNacionalidad() {
        return nacionalidad;
    }


    /**
     * Sets the nacionalidad value for this DatosTitular.
     *
     * @param nacionalidad el nuevo nacionalidad
     */
    public void setNacionalidad(java.lang.String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }


    /**
     * Gets the sexo value for this DatosTitular.
     * 
     * @return sexo
     */
    public es.redsara.intermediacion.scsp.esquemas.svdi.datosespecificos.Sexo getSexo() {
        return sexo;
    }


    /**
     * Sets the sexo value for this DatosTitular.
     *
     * @param sexo el nuevo sexo
     */
    public void setSexo(es.redsara.intermediacion.scsp.esquemas.svdi.datosespecificos.Sexo sexo) {
        this.sexo = sexo;
    }


    /**
     * Gets the datosNacimiento value for this DatosTitular.
     * 
     * @return datosNacimiento
     */
    public es.redsara.intermediacion.scsp.esquemas.svdi.datosespecificos.DatosNacimientoType getDatosNacimiento() {
        return datosNacimiento;
    }


    /**
     * Sets the datosNacimiento value for this DatosTitular.
     *
     * @param datosNacimiento el nuevo datos nacimiento
     */
    public void setDatosNacimiento(es.redsara.intermediacion.scsp.esquemas.svdi.datosespecificos.DatosNacimientoType datosNacimiento) {
        this.datosNacimiento = datosNacimiento;
    }


    /**
     * Gets the nomPadre value for this DatosTitular.
     * 
     * @return nomPadre
     */
    public java.lang.String getNombrePadre() {
        return nombrePadre;
    }


    /**
     * Sets the nomPadre value for this DatosTitular.
     *
     * @param nombrePadre el nuevo nom padre
     */
    public void setNomPadre(java.lang.String nombrePadre) {
        this.nombrePadre = nombrePadre;
    }


    /**
     * Gets the nomMadre value for this DatosTitular.
     * 
     * @return nomMadre
     */
    public java.lang.String getNombreMadre() {
        return nombreMadre;
    }


    /**
     * Sets the nomMadre value for this DatosTitular.
     *
     * @param nombreMadre el nuevo nombre madre
     */
    public void setNombreMadre(java.lang.String nombreMadre) {
        this.nombreMadre = nombreMadre;
    }


   


    /**
     * Gets the fechaCaducidad value for this DatosTitular.
     * 
     * @return fechaCaducidad
     */
    public java.lang.String getFechaCaducidad() {
        return fechaCaducidad;
    }


    /**
     * Sets the fechaCaducidad value for this DatosTitular.
     *
     * @param fechaCaducidad el nuevo fecha caducidad
     */
    public void setFechaCaducidad(java.lang.String fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    /** el equals calc. */
    private java.lang.Object __equalsCalc = null;
    
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DatosTitular)) return false;
        DatosTitular other = (DatosTitular) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            
            ((this.nombre==null && other.getNombre()==null) || 
             (this.nombre!=null &&
              this.nombre.equals(other.getNombre()))) &&
            ((this.apellido1==null && other.getApellido1()==null) || 
             (this.apellido1!=null &&
              this.apellido1.equals(other.getApellido1()))) &&
            ((this.apellido2==null && other.getApellido2()==null) || 
             (this.apellido2!=null &&
              this.apellido2.equals(other.getApellido2()))) &&
            ((this.nacionalidad==null && other.getNacionalidad()==null) || 
             (this.nacionalidad!=null &&
              this.nacionalidad.equals(other.getNacionalidad()))) &&
            ((this.sexo==null && other.getSexo()==null) || 
             (this.sexo!=null &&
              this.sexo.equals(other.getSexo()))) &&
            ((this.datosNacimiento==null && other.getDatosNacimiento()==null) || 
             (this.datosNacimiento!=null &&
              this.datosNacimiento.equals(other.getDatosNacimiento()))) &&
            ((this.nombrePadre==null && other.getNombrePadre()==null) || 
             (this.nombrePadre!=null &&
              this.nombrePadre.equals(other.getNombrePadre()))) &&
            ((this.nombreMadre==null && other.getNombreMadre()==null) || 
             (this.nombreMadre!=null &&
              this.nombreMadre.equals(other.getNombreMadre()))) &&
           
            ((this.fechaCaducidad==null && other.getFechaCaducidad()==null) || 
             (this.fechaCaducidad!=null &&
              this.fechaCaducidad.equals(other.getFechaCaducidad())));
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
       
        if (getNombre() != null) {
            _hashCode += getNombre().hashCode();
        }
        if (getApellido1() != null) {
            _hashCode += getApellido1().hashCode();
        }
        if (getApellido2() != null) {
            _hashCode += getApellido2().hashCode();
        }
        if (getNacionalidad() != null) {
            _hashCode += getNacionalidad().hashCode();
        }
        if (getSexo() != null) {
            _hashCode += getSexo().hashCode();
        }
        if (getDatosNacimiento() != null) {
            _hashCode += getDatosNacimiento().hashCode();
        }
        if (getNombrePadre() != null) {
            _hashCode += getNombrePadre().hashCode();
        }
        if (getNombreMadre() != null) {
            _hashCode += getNombreMadre().hashCode();
        }
       
        if (getFechaCaducidad() != null) {
            _hashCode += getFechaCaducidad().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    /** el type desc. */
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DatosTitular.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">DatosTitular"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        
        elemField.setFieldName("nombre");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "Nombre"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">Nombre"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("apellido1");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "Apellido1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">Apellido1"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("apellido2");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "Apellido2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">Apellido2"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nacionalidad");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "Nacionalidad"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">Nacionalidad"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sexo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "Sexo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">Sexo"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("datosNacimiento");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "DatosNacimiento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "DatosNacimientoType"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombrePadre");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "NombrePadre"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">NombrePadre"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombreMadre");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "NombreMadre"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">NombreMadre"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
       
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaCaducidad");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "FechaCaducidad"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">FechaCaducidad"));
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
