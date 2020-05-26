/**
 * PPServiceInterfaceSoap11Stub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ePago.gob.es.schemas;

import org.apache.log4j.Logger;

import es.map.ipsc.service.ClientEPagoHandler;


/**
 * El Class PPServiceInterfaceSoap11Stub.
 */
public class PPServiceInterfaceSoap11Stub extends org.apache.axis.client.Stub implements ePago.gob.es.schemas.PPServiceInterface {
    
    /** el cached ser classes. */
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    
    /** el cached ser Q names. */
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    
    /** el cached ser factories. */
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    
    /** el cached deser factories. */
    private java.util.Vector cachedDeserFactories = new java.util.Vector();
    
    /** La constante logger. */
    private static final Logger logger = Logger.getLogger(PPServiceInterfaceSoap11Stub.class);

    /** el operations. */
    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[7];
        _initOperationDesc1();
    }

    /**
     * En el operation desc 1.
     */
    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("hacerPagoYRegistro");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "datosPagoIn"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "DefaultDatosPagoIn"), ePago.gob.es.schemas.DefaultDatosPagoIn.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "almacen"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "Map"), ePago.gob.es.schemas.MapItem[].class, false, false);
        param.setItemQName(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "item"));
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "idOrganismo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "DefaultDatosPagoRegistroOut"));
        oper.setReturnClass(ePago.gob.es.schemas.DefaultDatosPagoRegistroOut.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "hacerPagoYRegistroReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("verificarNRC");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "datosNRCIn"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "DefaultDatosNRCIn"), ePago.gob.es.schemas.DefaultDatosNRCIn.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "idOrganismo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "DefaultDatosNRCOut"));
        oper.setReturnClass(ePago.gob.es.schemas.DefaultDatosNRCOut.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "verificarNRCReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("consultarPagoClave");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "datosPagoClaveIn"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "DefaultDatosPagoClaveIn"), ePago.gob.es.schemas.DefaultDatosPagoClaveIn.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "idOrganismo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "DefaultDatosPagoClaveOut"));
        oper.setReturnClass(ePago.gob.es.schemas.DefaultDatosPagoClaveOut.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "consultarPagoClaveReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("hacerPago");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "datosPagoIn"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "DefaultDatosPagoIn"), ePago.gob.es.schemas.DefaultDatosPagoIn.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "idOrganismo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "DefaultDatosPagoOut"));
        oper.setReturnClass(ePago.gob.es.schemas.DefaultDatosPagoOut.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "hacerPagoReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[3] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("consultarPago");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "datosPagoIn"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "DefaultDatosPagoIn"), ePago.gob.es.schemas.DefaultDatosPagoIn.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "idOrganismo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "DefaultDatosPagoOut"));
        oper.setReturnClass(ePago.gob.es.schemas.DefaultDatosPagoOut.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "consultarPagoReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[4] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("hacerPagoClave");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "datosPagoClaveIn"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "DefaultDatosPagoClaveIn"), ePago.gob.es.schemas.DefaultDatosPagoClaveIn.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "idOrganismo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "DefaultDatosPagoClaveOut"));
        oper.setReturnClass(ePago.gob.es.schemas.DefaultDatosPagoClaveOut.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "hacerPagoClaveReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[5] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getJustificante");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "modelo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "codigoTasa"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "idOrganismo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "getJustificanteReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[6] = oper;

    }

    /**
     * Crea una nueva PP service interface soap 11 stub.
     *
     * @throws AxisFault el axis fault
     */
    public PPServiceInterfaceSoap11Stub() throws org.apache.axis.AxisFault {
         this(null);
    }

    /**
     * Crea una nueva PP service interface soap 11 stub.
     *
     * @param endpointURL el endpoint URL
     * @param service el service
     * @throws AxisFault el axis fault
     */
    public PPServiceInterfaceSoap11Stub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    /**
     * Crea una nueva PP service interface soap 11 stub.
     *
     * @param service el service
     * @throws AxisFault el axis fault
     */
    public PPServiceInterfaceSoap11Stub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
        if (service == null) {
            super.service = new org.apache.axis.client.Service();
        } else {
            super.service = service;
        }
        ((org.apache.axis.client.Service)super.service).setTypeMappingVersion("1.2");
            java.lang.Class cls;
            javax.xml.namespace.QName qName;
            javax.xml.namespace.QName qName2;
            java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
            java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
            qName = new javax.xml.namespace.QName("http://es.gob.ePago/schemas", ">consultarPago");
            cachedSerQNames.add(qName);
            cls = ePago.gob.es.schemas.ConsultarPago.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://es.gob.ePago/schemas", ">consultarPagoClave");
            cachedSerQNames.add(qName);
            cls = ePago.gob.es.schemas.ConsultarPagoClave.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://es.gob.ePago/schemas", ">consultarPagoClaveResponse");
            cachedSerQNames.add(qName);
            cls = ePago.gob.es.schemas.ConsultarPagoClaveResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://es.gob.ePago/schemas", ">consultarPagoResponse");
            cachedSerQNames.add(qName);
            cls = ePago.gob.es.schemas.ConsultarPagoResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://es.gob.ePago/schemas", ">getJustificante");
            cachedSerQNames.add(qName);
            cls = ePago.gob.es.schemas.GetJustificante.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://es.gob.ePago/schemas", ">getJustificanteResponse");
            cachedSerQNames.add(qName);
            cls = ePago.gob.es.schemas.GetJustificanteResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://es.gob.ePago/schemas", ">hacerPago");
            cachedSerQNames.add(qName);
            cls = ePago.gob.es.schemas.HacerPago.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://es.gob.ePago/schemas", ">hacerPagoClave");
            cachedSerQNames.add(qName);
            cls = ePago.gob.es.schemas.HacerPagoClave.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://es.gob.ePago/schemas", ">hacerPagoClaveResponse");
            cachedSerQNames.add(qName);
            cls = ePago.gob.es.schemas.HacerPagoClaveResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://es.gob.ePago/schemas", ">hacerPagoResponse");
            cachedSerQNames.add(qName);
            cls = ePago.gob.es.schemas.HacerPagoResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://es.gob.ePago/schemas", ">hacerPagoYRegistro");
            cachedSerQNames.add(qName);
            cls = ePago.gob.es.schemas.HacerPagoYRegistro.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://es.gob.ePago/schemas", ">hacerPagoYRegistroResponse");
            cachedSerQNames.add(qName);
            cls = ePago.gob.es.schemas.HacerPagoYRegistroResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://es.gob.ePago/schemas", ">verificarNRC");
            cachedSerQNames.add(qName);
            cls = ePago.gob.es.schemas.VerificarNRC.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://es.gob.ePago/schemas", ">verificarNRCResponse");
            cachedSerQNames.add(qName);
            cls = ePago.gob.es.schemas.VerificarNRCResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "DefaultDatosNRCIn");
            cachedSerQNames.add(qName);
            cls = ePago.gob.es.schemas.DefaultDatosNRCIn.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "DefaultDatosNRCOut");
            cachedSerQNames.add(qName);
            cls = ePago.gob.es.schemas.DefaultDatosNRCOut.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "DefaultDatosPagoClaveIn");
            cachedSerQNames.add(qName);
            cls = ePago.gob.es.schemas.DefaultDatosPagoClaveIn.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "DefaultDatosPagoClaveOut");
            cachedSerQNames.add(qName);
            cls = ePago.gob.es.schemas.DefaultDatosPagoClaveOut.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "DefaultDatosPagoIn");
            cachedSerQNames.add(qName);
            cls = ePago.gob.es.schemas.DefaultDatosPagoIn.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "DefaultDatosPagoOut");
            cachedSerQNames.add(qName);
            cls = ePago.gob.es.schemas.DefaultDatosPagoOut.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "DefaultDatosPagoRegistroOut");
            cachedSerQNames.add(qName);
            cls = ePago.gob.es.schemas.DefaultDatosPagoRegistroOut.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "DefaultDatosRegistroOut");
            cachedSerQNames.add(qName);
            cls = ePago.gob.es.schemas.DefaultDatosRegistroOut.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "Funcionario");
            cachedSerQNames.add(qName);
            cls = ePago.gob.es.schemas.Funcionario.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "Map");
            cachedSerQNames.add(qName);
            cls = ePago.gob.es.schemas.MapItem[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "mapItem");
            qName2 = new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "mapItem");
            cachedSerQNames.add(qName);
            cls = ePago.gob.es.schemas.MapItem.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "NifFuncionario");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "NombreCompletoFuncionario");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "TiposCargo");
            cachedSerQNames.add(qName);
            cls = ePago.gob.es.schemas.TiposCargo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "TiposDocumento");
            cachedSerQNames.add(qName);
            cls = ePago.gob.es.schemas.TiposDocumento.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

    }

    /**
     * Crea el call.
     *
     * @return el org.apache.axis.client. call
     * @throws RemoteException el remote exception
     */
    protected org.apache.axis.client.Call createCall() throws java.rmi.RemoteException {
        try {
            org.apache.axis.client.Call _call = super._createCall();
            if (super.maintainSessionSet) {
                _call.setMaintainSession(super.maintainSession);
            }
            if (super.cachedUsername != null) {
                _call.setUsername(super.cachedUsername);
            }
            if (super.cachedPassword != null) {
                _call.setPassword(super.cachedPassword);
            }
            if (super.cachedEndpoint != null) {
                _call.setTargetEndpointAddress(super.cachedEndpoint);
            }
            if (super.cachedTimeout != null) {
                _call.setTimeout(super.cachedTimeout);
            }
            if (super.cachedPortName != null) {
                _call.setPortName(super.cachedPortName);
            }
            java.util.Enumeration keys = super.cachedProperties.keys();
            while (keys.hasMoreElements()) {
                java.lang.String key = (java.lang.String) keys.nextElement();
                _call.setProperty(key, super.cachedProperties.get(key));
            }
            // All the type mapping information is registered
            // when the first call is made.
            // The type mapping information is actually registered in
            // the TypeMappingRegistry of the service, which
            // is the reason why registration is only needed for the first call.
            synchronized (this) {
                if (firstCall()) {
                    // must set encoding style before registering serializers
                    _call.setEncodingStyle(null);
                    for (int i = 0; i < cachedSerFactories.size(); ++i) {
                        java.lang.Class cls = (java.lang.Class) cachedSerClasses.get(i);
                        javax.xml.namespace.QName qName =
                                (javax.xml.namespace.QName) cachedSerQNames.get(i);
                        java.lang.Object x = cachedSerFactories.get(i);
                        if (x instanceof Class) {
                            java.lang.Class sf = (java.lang.Class)
                                 cachedSerFactories.get(i);
                            java.lang.Class df = (java.lang.Class)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                        else if (x instanceof javax.xml.rpc.encoding.SerializerFactory) {
                            org.apache.axis.encoding.SerializerFactory sf = (org.apache.axis.encoding.SerializerFactory)
                                 cachedSerFactories.get(i);
                            org.apache.axis.encoding.DeserializerFactory df = (org.apache.axis.encoding.DeserializerFactory)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                    }
                }
            }
            return _call;
        }
        catch (java.lang.Throwable _t) {
            throw new org.apache.axis.AxisFault("Failure trying to get the Call object", _t);
        }
    }

    /* (non-Javadoc)
     * @see ePago.gob.es.schemas.PPServiceInterface#hacerPagoYRegistro(ePago.gob.es.schemas.DefaultDatosPagoIn, ePago.gob.es.schemas.MapItem[], int)
     */
    public ePago.gob.es.schemas.DefaultDatosPagoRegistroOut hacerPagoYRegistro(ePago.gob.es.schemas.DefaultDatosPagoIn datosPagoIn, ePago.gob.es.schemas.MapItem[] almacen, int idOrganismo) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "hacerPagoYRegistro"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {datosPagoIn, almacen, new java.lang.Integer(idOrganismo)});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (ePago.gob.es.schemas.DefaultDatosPagoRegistroOut) _resp;
            } catch (java.lang.Exception _exception) {
                return (ePago.gob.es.schemas.DefaultDatosPagoRegistroOut) org.apache.axis.utils.JavaUtils.convert(_resp, ePago.gob.es.schemas.DefaultDatosPagoRegistroOut.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    /* (non-Javadoc)
     * @see ePago.gob.es.schemas.PPServiceInterface#verificarNRC(ePago.gob.es.schemas.DefaultDatosNRCIn, int)
     */
    public ePago.gob.es.schemas.DefaultDatosNRCOut verificarNRC(ePago.gob.es.schemas.DefaultDatosNRCIn datosNRCIn, int idOrganismo) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "verificarNRC"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {datosNRCIn, new java.lang.Integer(idOrganismo)});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (ePago.gob.es.schemas.DefaultDatosNRCOut) _resp;
            } catch (java.lang.Exception _exception) {
                return (ePago.gob.es.schemas.DefaultDatosNRCOut) org.apache.axis.utils.JavaUtils.convert(_resp, ePago.gob.es.schemas.DefaultDatosNRCOut.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    /* (non-Javadoc)
     * @see ePago.gob.es.schemas.PPServiceInterface#consultarPagoClave(ePago.gob.es.schemas.DefaultDatosPagoClaveIn, int)
     */
    public ePago.gob.es.schemas.DefaultDatosPagoClaveOut consultarPagoClave(ePago.gob.es.schemas.DefaultDatosPagoClaveIn datosPagoClaveIn, int idOrganismo) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "consultarPagoClave"));
        //INI - cpasculi - IPS-147 - Actualizacion de ePago
        
        ClientEPagoHandler clienteEPago = new ClientEPagoHandler();
        _call.setClientHandlers(clienteEPago, null);
        
      //FIN - cpasculi - IPS-147 - Actualizacion de ePago

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {datosPagoClaveIn, new java.lang.Integer(idOrganismo)});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (ePago.gob.es.schemas.DefaultDatosPagoClaveOut) _resp;
            } catch (java.lang.Exception _exception) {
                return (ePago.gob.es.schemas.DefaultDatosPagoClaveOut) org.apache.axis.utils.JavaUtils.convert(_resp, ePago.gob.es.schemas.DefaultDatosPagoClaveOut.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    /* (non-Javadoc)
     * @see ePago.gob.es.schemas.PPServiceInterface#hacerPago(ePago.gob.es.schemas.DefaultDatosPagoIn, int)
     */
    public ePago.gob.es.schemas.DefaultDatosPagoOut hacerPago(ePago.gob.es.schemas.DefaultDatosPagoIn datosPagoIn, int idOrganismo) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "hacerPago"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {datosPagoIn, new java.lang.Integer(idOrganismo)});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (ePago.gob.es.schemas.DefaultDatosPagoOut) _resp;
            } catch (java.lang.Exception _exception) {
                return (ePago.gob.es.schemas.DefaultDatosPagoOut) org.apache.axis.utils.JavaUtils.convert(_resp, ePago.gob.es.schemas.DefaultDatosPagoOut.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    /* (non-Javadoc)
     * @see ePago.gob.es.schemas.PPServiceInterface#consultarPago(ePago.gob.es.schemas.DefaultDatosPagoIn, int)
     */
    public ePago.gob.es.schemas.DefaultDatosPagoOut consultarPago(ePago.gob.es.schemas.DefaultDatosPagoIn datosPagoIn, int idOrganismo) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[4]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "consultarPago"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {datosPagoIn, new java.lang.Integer(idOrganismo)});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (ePago.gob.es.schemas.DefaultDatosPagoOut) _resp;
            } catch (java.lang.Exception _exception) {
                return (ePago.gob.es.schemas.DefaultDatosPagoOut) org.apache.axis.utils.JavaUtils.convert(_resp, ePago.gob.es.schemas.DefaultDatosPagoOut.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    /* (non-Javadoc)
     * @see ePago.gob.es.schemas.PPServiceInterface#hacerPagoClave(ePago.gob.es.schemas.DefaultDatosPagoClaveIn, int)
     */
    public ePago.gob.es.schemas.DefaultDatosPagoClaveOut hacerPagoClave(ePago.gob.es.schemas.DefaultDatosPagoClaveIn datosPagoClaveIn, int idOrganismo) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[5]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "hacerPagoClave"));
     
        
        ClientEPagoHandler clienteEPago = new ClientEPagoHandler();
        _call.setClientHandlers(clienteEPago, null);
        
        
        setRequestHeaders(_call);
        setAttachments(_call);
		
 try {       java.lang.Object _resp = _call.invoke(new java.lang.Object[] {datosPagoClaveIn, new java.lang.Integer(idOrganismo)});
 
		 //Log de la peticion soap
		 try {
			 String llamada = _call.getMessageContext().getRequestMessage().getSOAPPart().getEnvelope().toString();
			 logger.info("CONSULTA ePago - request: " + llamada);
		 } catch (Exception e) {
			 logger.error("CONSULTA ePago: Error mostrando request: ",e);
		 }
		 // Log de la respuesta soap
		 try {
			 String respuesta = _call.getMessageContext().getResponseMessage().getSOAPPart().getEnvelope().toString();
			 logger.info("Respuesta ePago - response: " + respuesta);
		 } catch (Exception e) {
			 logger.error("Respuesta ePago: Error mostrando response: ",e);
		 }
	 
        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (ePago.gob.es.schemas.DefaultDatosPagoClaveOut) _resp;
            } catch (java.lang.Exception _exception) {
                return (ePago.gob.es.schemas.DefaultDatosPagoClaveOut) org.apache.axis.utils.JavaUtils.convert(_resp, ePago.gob.es.schemas.DefaultDatosPagoClaveOut.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    /* (non-Javadoc)
     * @see ePago.gob.es.schemas.PPServiceInterface#getJustificante(java.lang.String, java.lang.String, int)
     */
    public java.lang.String getJustificante(java.lang.String modelo, java.lang.String codigoTasa, int idOrganismo) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[6]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "getJustificante"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {modelo, codigoTasa, new java.lang.Integer(idOrganismo)});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

}
