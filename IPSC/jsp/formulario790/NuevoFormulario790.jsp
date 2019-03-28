<% Boolean subsanar = (Boolean)request.getAttribute("subsanar"); %>
<script type="text/javascript" src="<%=request.getContextPath()%>/bower_components/jquery-ui/ui/accordion-custom.js"></script>
<script type="text/javascript">

function seleccionarEspaña(){
		if(document.getElementById("pais"))
			document.getElementById("pais").value = "1";
	}
//Funciones que validan los datos que se introducen por el ciudadano mientras 
//esta cumplimentando el formulario
function buscaCodigoProvincia(){

	var idProvincia = document.getElementById("provinciaDomicilio").value;
	<% 
		Boolean precargar = (Boolean)request.getAttribute("codProvinciaDomicilio");
		List objProvincias = (List)request.getAttribute("provincias");
		Integer elemAux =new Integer(0);
		String codAux ="";
		Object objAux= null;
		ProvinciaBean prov;
		if( objProvincias != null)
		{
			for(int i=0;i<objProvincias.size();i++)
			{
				objAux= objProvincias.get(i);
				prov = (ProvinciaBean)objAux;
				elemAux= (Integer)prov.getId();
				codAux=  (String)prov.getCodigo();
	%>
	if("<%=elemAux%>" == idProvincia)
	{
		if("<%=precargar%>" == "true")
		{	
			if("<%=codAux%>" != "null")
			{	
				document.getElementById("codigoProvinciaDomicilio").value = "<%=codAux%>" ;		
			}
			else
			{
				document.getElementById("codigoProvinciaDomicilio").value = "" ;
			}		

		}	
	}		
	
		
	<%}}%>	
			
}

function buscaCodigoPais(){

	var idPais = document.getElementById("pais").value;
	<% 
		Boolean precargarPaisDomicilio = (Boolean)request.getAttribute("codPaisDomicilio");
		List listPaisDomicilio = (List)request.getAttribute("paises");
		Integer paisAux =new Integer(0);
		String codPaisAux ="";
		PaisBean pais = new PaisBean();
		if( listPaisDomicilio != null)
		{
			for(int j=0;j<listPaisDomicilio.size();j++)
			{
				pais = (PaisBean)listPaisDomicilio.get(j);
				paisAux= (Integer)pais.getId();
				codPaisAux=  (String)pais.getCodigo();
	%>
	if("<%=paisAux%>" == idPais)
	{
		<%
		if(precargarPaisDomicilio)
		{	
			if(codPaisAux != null)
			{
				//if(codPaisAux.length()>2){
		%>
					<%--document.getElementById("codigoPaisDomicilio").value = "<%=codPaisAux.substring(0,3)%>" ;--%>
		<%
				//}else{*/
		%>
					document.getElementById("codigoPaisDomicilio").value = "<%=codPaisAux%>" ;
		<%
				//}	
			}	
			else
			{
		
		%>
				document.getElementById("codigoPaisDomicilio").value = "" ;
		<%
			}		
		}	
		%>
	}		
	
		
	<%}}%>	
			
}

function buscaCodigoCuerpoEscala(){

	var idCuerpoEscala = document.getElementById("cuerpoEscala").value;
	<% 
		Boolean precargarCuerpoEscala = (Boolean)request.getAttribute("codCuerpoEscala");
		List listCuerposEscala = (List)request.getAttribute("cuerposEscala");
		Long ceAux =new Long(0L);
		String codCeAux ="";
		CuerpoEscalaBean ccuerpoEscala = new CuerpoEscalaBean();
		if( listCuerposEscala != null)
		{
			for(int j=0;j<listCuerposEscala.size();j++)
			{
				ccuerpoEscala = (CuerpoEscalaBean)listCuerposEscala.get(j);
				ceAux= (Long)ccuerpoEscala.getId();
				codCeAux=  (String)ccuerpoEscala.getCodigo();
	%>
	
	if("<%=ceAux%>" == idCuerpoEscala)
	{
		if("<%=precargarCuerpoEscala%>" == "true")
		{	
			if("<%=codCeAux%>" != "null")
			{	
				document.getElementById("codigoCuerpoEscala").value = "<%=codCeAux%>" ;	
			}
			else
			{
				document.getElementById("codigoCuerpoEscala").value = "" ;
			}			
		}	
	}		
	<%}}%>				
}

function buscaCodigoEspecialidad(){
	var idEspecialidad = document.getElementById("especialidad").value;
	<% 
		Boolean precargarEspecialidad = (Boolean)request.getAttribute("codEspecialidad");
		List listEspecialidades = (List)request.getAttribute("especialidades");
		Integer especialidadAux =new Integer(0);
		String codEspecialidadAux ="";
		EspecialidadBean especialidadBean = new EspecialidadBean();
		if( listEspecialidades != null)
		{
			for(int j=0;j<listEspecialidades.size();j++)
			{
				especialidadBean = (EspecialidadBean)listEspecialidades.get(j);
				especialidadAux= (Integer)especialidadBean.getId();
				codEspecialidadAux=  (String)especialidadBean.getCodigo();
	%>
	if("<%=especialidadAux%>" == idEspecialidad)
	{
		<%
		if(precargarEspecialidad)
		{	
			if(codEspecialidadAux != null)
			{
		%>
				document.getElementById("codigoEspecialidad").value = "<%= (codEspecialidadAux!=null && codEspecialidadAux.length() > 4) ? codEspecialidadAux.substring(0,3) : codEspecialidadAux%>" ;
				alert("valor code "+codEspecialidadAux);
		<%
			}
			else
			{
		%>
				document.getElementById("codigoEspecialidad").value = "" ;
		<%
			}			
		}
		%>
	}		
	
		
	<%}}%>	
			
}


function buscaCodigoEspecialidadConvocatoria(){
	var idEspecialidadC = document.getElementById("especialidad").value;
	<% 
		Boolean precargarEspecialidadC = (Boolean)request.getAttribute("codEspecialidad");
		ConvocatoriaBean conv = (ConvocatoriaBean)request.getAttribute("convocatoria");
		if(conv != null)
		{	
			List listEspecialidadesC = (List)conv.getEspecialidads();
			Integer especialidadAuxC =new Integer(0);
			String codEspecialidadAuxC ="";
			Especialidad especialidadBeanC = new Especialidad();
			if( listEspecialidadesC != null)
			{
				for(int j=0;j<listEspecialidadesC.size();j++)
				{
					especialidadBeanC = (Especialidad)listEspecialidadesC.get(j);
					especialidadAuxC= (Integer)especialidadBeanC.getId();
					codEspecialidadAuxC=  (String)especialidadBeanC.getCodigo();
	%>
	if("<%=especialidadAuxC%>" == idEspecialidadC)
	{
		<%
		if(precargarEspecialidadC)
		{	
			if(codEspecialidadAuxC != null)
			{
				if(codEspecialidadAuxC.length() > 4){
		%>
					document.getElementById("codigoEspecialidad").value = "<%=codEspecialidadAuxC.substring(0,3)%>" ;
		<%
				}else{
		%>
					document.getElementById("codigoEspecialidad").value = "<%=codEspecialidadAuxC%>" ;
		<%
				}
			}
			else
			{
		%>
				document.getElementById("codigoEspecialidad").value = "" ;
		<%
			}			
		}
		%>
	}		
	
		
	<%}}}%>	
			
}
function buscaCodigoMinisterio(){

	var idMinisterio = document.getElementById("ministerio").value;
	<% 
		Boolean precargarMinisterio = (Boolean)request.getAttribute("codMinisterio");
		List listMinisterio = (List)request.getAttribute("ministerios");
		Integer ministerioAux =new Integer(0);
		String codMinisterioAux ="";
		MinisterioBean ministerio = new MinisterioBean();
		if( listMinisterio != null)
		{
			for(int j=0;j<listMinisterio.size();j++)
			{
				ministerio = (MinisterioBean)listMinisterio.get(j);
				ministerioAux= (Integer)ministerio.getId();
				codMinisterioAux=  (String)ministerio.getCodigo();
	%>
	if("<%=ministerioAux%>" == idMinisterio)
	{
		if("<%=precargarMinisterio%>" == "true")
		{	
			if("<%=codMinisterioAux%>" != "null")
			{
				document.getElementById("codigoMinisterio").value = "<%=codMinisterioAux%>" ;		
			}
			else
			{
				document.getElementById("codigoMinisterio").value = "" ;
			}		
		}	
	}		
	
		
	<%}}%>	
			
}

function buscaProvinciaExamen(){

	var idProvExamen = document.getElementById("provinciaExamen").value;
	<% 
		Boolean precargarProvinciasExamen = (Boolean)request.getAttribute("codProvinciaExamen");
		List listProvinciasExamen = (List)request.getAttribute("provinciasExamen");
		Integer provinciaExamenAux =new Integer(0);
		String codProvinciaExamenAux ="";
		ProvinciaExamenBean provinciaExamenBean = new ProvinciaExamenBean();
		if( listProvinciasExamen != null)
		{
			for(int j=0;j<listProvinciasExamen.size();j++)
			{
				provinciaExamenBean = (ProvinciaExamenBean)listProvinciasExamen.get(j);
				provinciaExamenAux= (Integer)provinciaExamenBean.getId();
				codProvinciaExamenAux=  (String)provinciaExamenBean.getCodigo();
	%>
	if("<%=provinciaExamenAux%>" == idProvExamen)
	{
		
		if("<%=precargarProvinciasExamen%>" == "true")
		{	
			if("<%=codProvinciaExamenAux%>" != "null")
			{
				document.getElementById("codigoProvinciaExamen").value = "<%=codProvinciaExamenAux%>" ;	
			}
			else
			{
				document.getElementById("codigoProvinciaExamen").value = "" ;	
			}			
		}	
	}		
	
		
	<%}}%>	
			
}

function buscaProvinciaExamenConvocatoria(){

	var idProvExamenC = document.getElementById("provinciaExamen").value;
	<% 
		Boolean precargarProvinciasExamenC = (Boolean)request.getAttribute("codProvinciaExamen");
		ConvocatoriaBean convExamen = (ConvocatoriaBean)request.getAttribute("convocatoria");
		if(convExamen != null)
		{	
			List listProvinciasExamenC = (List)convExamen.getProvinciasExamen();
			Integer provinciaExamenAuxC =new Integer(0);
			String codProvinciaExamenAuxC ="";
			ProvinciaExamen provinciaExamenBeanC = new ProvinciaExamen();
			if( listProvinciasExamenC != null)
			{
				for(int j=0;j<listProvinciasExamenC.size();j++)
				{
					provinciaExamenBeanC = (ProvinciaExamen)listProvinciasExamenC.get(j);
					provinciaExamenAuxC= (Integer)provinciaExamenBeanC.getId();
					codProvinciaExamenAuxC=  (String)provinciaExamenBeanC.getCodigo();
	%>
	if("<%=provinciaExamenAuxC%>" == idProvExamenC)
	{
		
		if("<%=precargarProvinciasExamenC%>" == "true")
		{	
			if("<%=codProvinciaExamenAuxC%>" != "null")
			{
				document.getElementById("codigoProvinciaExamen").value = "<%=codProvinciaExamenAuxC%>" ;	
			}
			else
			{
				document.getElementById("codigoProvinciaExamen").value = "" ;	
			}			
		}	
	}				
	<%}}}%>	
			
}

function buscaTituloOficial(){

	var idTitulo = document.getElementById("tituloOficial").value;
	<% 
		Boolean precargarTitulo = (Boolean)request.getAttribute("codTituloOficial");
		List listTitulos = (List)request.getAttribute("titulosOficiales");
		Integer tituloAux =new Integer(0);
		String codTituloAux ="";
		TituloOficialBean tituloBean = new TituloOficialBean();
		if( listTitulos != null)
		{
			for(int j=0;j<listTitulos.size();j++)
			{
				tituloBean = (TituloOficialBean)listTitulos.get(j);
				tituloAux= (Integer)tituloBean.getId();
				codTituloAux=  (String)tituloBean.getCodigo();
	%>
	if("<%=tituloAux%>" == idTitulo)
	{
	
		if("<%=precargarTitulo%>" == "true")
		{	
			if("<%=codTituloAux%>" != "null")
			{	
				document.getElementById("codigoTituloExigido").value = "<%=codTituloAux%>" ;	
			}	
			else
			{
				document.getElementById("codigoTituloExigido").value = "" ;
			}		
		}	
	}		
	
		
	<%}}%>	
			
}

function buscaTituloOficialConvocatoria(){
	var idTituloC = document.getElementById("tituloOficial").value;
	<% 
		Boolean precargarTituloC = (Boolean)request.getAttribute("codTituloOficial");
		ConvocatoriaBean convTitulo = (ConvocatoriaBean)request.getAttribute("convocatoria");	
		if(convTitulo != null)
		{	
			List listTitulosC = (List)convTitulo.getTituloOficials();
			Integer tituloAuxC =new Integer(0);
			String codTituloAuxC ="";
			TituloOficial tituloBeanC = new TituloOficial();
			if( listTitulosC != null)
			{
				for(int j=0;j<listTitulosC.size();j++)
				{
					tituloBeanC = (TituloOficial)listTitulosC.get(j);
					tituloAuxC= (Integer)tituloBeanC.getId();
					codTituloAuxC=  (String)tituloBeanC.getCodigo();
	%>
	if("<%=tituloAuxC%>" == idTituloC)
	{
		if("<%=precargarTituloC%>" == "true")
		{	
			if("<%=codTituloAuxC%>" != "null")
			{	
				document.getElementById("codigoTituloExigido").value = "<%=codTituloAuxC%>" ;	
			}	
			else
			{
				document.getElementById("codigoTituloExigido").value = "" ;
			}		
		}	
	}		
	
		
	<%}}}%>	
			
}

</script>

<link rel="shortcut icon" type="image/ico" href="../../images/ipsFavicon.ico" type="image/x-icon"/>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.mpr.es/ipsg/bean-tags" prefix="bean" %>
<%@ taglib uri="http://www.mpr.es/ipsg/html-tags" prefix="html" %>
<%@ taglib uri="http://www.mpr.es/ipsg/logic-tags" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.util.*" %>
<%@ page import="es.map.ipsc.bean.ProvinciaBean" %>
<%@ page import="es.map.ipsc.bean.ProvinciaExamenBean" %>
<%@ page import="es.map.ipsc.bean.PaisBean" %>
<%@ page import="es.map.ipsc.bean.CuerpoEscalaBean" %>
<%@ page import="es.map.ipsc.bean.EspecialidadBean" %>
<%@ page import="es.map.ipsc.bean.MinisterioBean" %>
<%@ page import="es.map.ipsc.bean.TituloOficialBean" %>
<%@ page import="es.map.ipsc.bean.ConvocatoriaBean" %>
<%@ page import="es.map.ips.model.Especialidad" %>
<%@ page import="es.map.ips.model.Provincia" %>
<%@ page import="es.map.ips.model.ProvinciaExamen" %>
<%@ page import="es.map.ips.model.TituloOficial" %>
<%@ page import="es.map.ips.model.DatosPropiosConfiguracion" %>
<%@ page import="es.map.ipsc.Constantes" %>
<%@ page import="es.map.ipsc.bean.*" %>
<%@ page import="es.map.ipsc.spring.*" %>

<head>
  <!-- Etiquetas META-->
  <meta charset="UTF-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
</head>
<form:form modelAttribute="formulario790Form" action="/IPSC/secure/generarFormulario790" onsubmit="return alEnviar()" method="post">
	<form:hidden path="idCuerpoEscala" id="idCuerpoEscala"/>
	<form:hidden path="idMinisterio" id="idMinisterio"/>
	<form:hidden path="idFormaAcceso" id="idFormaAcceso"/>
	<form:hidden path="idCentroGestor" id="idCentroGestor"/>
	<form:hidden path="idConvocatoria" id="idConvocatoria"/>
	<form:hidden path="codCentroGestor" id="codCentroGestor"/>
	<form:hidden path="porcentajeMinDiscapacidad" id="porcentajeMinDiscapacidad"/>
	<form:hidden path="importeSolicitud" id="importeSolicitud"/>
    <form:hidden path="importeSolicitudDecimal" id="importeSolicitudDecimal"/>
    <form:hidden path="formaPago" id="formaPago" value="E"/>
    <form:hidden path="diaFechaBoe" id="diaFechaBoe"/>
    <form:hidden path="mesFechaBoe" id="mesFechaBoe"/>
    <form:hidden path="anioFechaBoe" id="anioFechaBoe"/>
    <form:hidden path="diaNacimiento" id="diaNacimiento"/>
    <form:hidden path="mesNacimiento" id="mesNacimiento"/>
    <form:hidden path="anioNacimiento" id="anioNacimiento"/>
    <form:hidden path="consentimiento" id="consentimiento"/>
    <form:hidden path="iban" id="iban"/>
    <form:hidden path="entidad" id="entidad"/>
    <form:hidden path="oficina" id="oficina"/>
    <form:hidden path="digitoConttrol" id="digitoControl"/>
    <form:hidden path="numeroCuenta" id="numeroCuenta"/>
    <form:hidden path="codigoTasa" id="codigoTasa"/>
    <form:hidden path="numModelo" id="numModelo"/>
    <form:hidden path="subsanar" id="subsanar"/>
    
	<input type="hidden" id="motivosTipificado" value="${motivoTipificado}">
	<input type="hidden" id="borrarMotivo" value="">
	<input type="hidden" id="comunidadesReqTitulo" value="${comunidadesReqTitulo}">
	<input type="hidden" id="importe" value="${importe}">
	<input type="hidden" id="setFocus" value=""/>
	<logic:present name="convocatoria">			                  		
		<input type="hidden" id="esConvocatoria">
	</logic:present>	
	
  <div class="pae-wrapper">
    <div class="pae-head-return">
      <div class="pae-layout">
     	<c:choose>
		    <c:when test="${formulario790Form.subsanar}">
		    	<div class="pae-layout__item pae-u-12/12 pae-u-12/12-lap pae-u-1/1-palm"><a href="#" onclick="llamadaConvocatoriasSub();" title="<spring:message code="field.header.volverSub"/>" class="pae-head-return__link fa fa-chevron-left"><spring:message code="field.header.volverSub"/></a></div>
		    </c:when>    
		    <c:otherwise>
        <div class="pae-layout__item pae-u-12/12 pae-u-12/12-lap pae-u-1/1-palm"><a href="#" onclick="llamadaConvocatorias();" title="<spring:message code="field.header.volver"/>" class="pae-head-return__link fa fa-chevron-left"><spring:message code="field.header.volver"/></a></div>
		    </c:otherwise>
		</c:choose>
      </div>
    </div>
  </div>
  <div class="pae-body pae-body--without-border pae-body--without-padding-mobile">
    <div class="pae-wrapper">
      <div class="pae-layout">
        <div class="pae-layout__item pae-u-12/12 pae-u-12/12-lap pae-u-1/1-palm">
        
          <%@ include file="/jsp/formulario790/informacionConvocatoria790.jsp" %>
          <%@ include file="/jsp/formulario790/datosSolicitante790.jsp" %>
          <%@ include file="/jsp/formulario790/datosConvocatoria790.jsp" %>
          
          <%@ include file="/jsp/formulario790/informacionAdicional790.jsp" %>
          <%@ include file="/jsp/formulario790/datosExencion790.jsp" %>
          <%@ include file="/jsp/formulario790/datosPago790.jsp" %>
          <%@ include file="/jsp/formulario790/firmaConsentimiento790.jsp" %>
          
        </div>
      </div>
    </div>
  </div>
  
	<script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/javascript/validaciones.js"  charset="utf-8"></script>
</form:form>

<script type="text/javascript">
  		
  		function validaFechaFormato(objeto){
  			var fecha = objeto.value; 
  			if(fecha!=null){
  				if(fecha.length==10){
  					if(!/^\d{2}\/\d{2}\/\d{4}$/.test(fecha)){
  						alert("<spring:message code='field.solicitud.alta.validate.fechaNacimiento'/>");
  						$("#fechaNacimiento").val("");
  					}else{
  						if(!validarFechaMinima(fecha)){
  							alert("<spring:message code='field.altaSolicitud.fechaNoValida'/>");
  							$("#fechaNacimiento").val("");
  						}else{
  							return true;
  						}				
  					}
  				}else{
  					alert("<spring:message code='field.solicitud.alta.validate.fechaNacimiento'/>");
  					$("#fechaNacimiento").val("");
  				}
  			}
  			return false;
  		}

  		function validarFechaMinima(entrada){
  			var fecha = new Date();
  			var anyoActual = fecha.getFullYear();
  			var anyoMinimo = anyoActual - 70;
  			fecha.setFullYear(parseInt(anyoMinimo));
  			var fechaEntrada = new Date();
  			fechaEntrada.setDate(parseInt(entrada.substring(0,2),10));
  			fechaEntrada.setMonth(parseInt(entrada.substring(3,5),10)-1);
  			fechaEntrada.setFullYear(parseInt(entrada.substring(6,10),10));
  			
  			if(fechaEntrada < fecha){
  				return false;
  			}
  			return true;	
  		}

  		function cambiarConsentimiento(valor){
  			
  			//$("#consentimiento").val(valor);
  			document.getElementById("consentimiento").value = valor;
  		}
  		
  		var obligatorio = "<spring:message code="field.formulario790.errorObligatorios"/>";
 		 var telefono = "<spring:message code="field.formulario790.javascript.telefonoError"/>";
 		 var codPostal = "<spring:message code="field.formulario790.javascript.codigoPostalError"/>";
		 var anioNacimiento= "<spring:message code="field.formulario790.javascript.anioNacimientoError"/>";
 		 var anioBOE = "<spring:message code="field.formulario790.javascript.anioBOEError"/>";
 		 var fechaNacimiento = "<spring:message code="field.formulario790.javascript.fechaNacimientoError"/>";
 		 var fechaBOE="<spring:message code="field.formulario790.javascript.fechaBoeError"/>";
 		 var email = "<spring:message code="field.formulario790.javascript.correoElectronicoError"/>";
 		 var CPFormat = "<spring:message code="field.formulario790.javascript.codPostalFormatError"/>";
 		 var ejercicio = "<spring:message code="field.formulario790.javascript.ejercicioError"/>";
 		 var importeLiq = "<spring:message code="field.formulario790.javascript.importeLiquidacionError"/>";
 		 var importe = "<spring:message code="field.formulario790.javascript.importeError"/>";
 		 var numeroTituloFNErr= "<spring:message code="field.formulario790.javascript.numeroTituloFNError"/>";
 		 var IBANErr ="<spring:message code="field.pagoSolicitud.incorrectoIBAN"/>";
 		 var ibanErr ="<spring:message code="field.formulario790.javascript.ibanError"/>";
 		 var entidadErr ="<spring:message code="field.formulario790.javascript.entidadError"/>";
 		 var oficinaErr ="<spring:message code="field.formulario790.javascript.oficinaError"/>";
 		 var dcErr ="<spring:message code="field.formulario790.javascript.dcError"/>";
 		 var cuentaErr ="<spring:message code="field.formulario790.javascript.cuentaError"/>";
 		 var numCuentaErr ="<spring:message code="field.formulario790.javascript.numCuentaError"/>";
 		 var edadErr ="<spring:message code="field.formulario790.javascript.edadError"/>";
 		 var diaErr ="<spring:message code="field.formulario790.javascript.dia"/>";
 		 var mesErr ="<spring:message code="field.formulario790.javascript.mes"/>";
 		 var anioErr ="<spring:message code="field.formulario790.javascript.anio"/>";
 		 var diaBOE ="<spring:message code="field.formulario790.javascript.diaBoe"/>";
 		 var mesBOE ="<spring:message code="field.formulario790.javascript.mesBoe"/>";
 		 var anioBOE ="<spring:message code="field.formulario790.javascript.anioBoe"/>";
 		 var dni1 ="<spring:message code="field.solicitud.alta.errors.dni1"/>";
 		 var dni2 ="<spring:message code="field.solicitud.alta.errors.dni2"/>";
 		 var dni3 ="<spring:message code="field.solicitud.alta.errors.dni3"/>";
 		 var gradoDiscapacidad ="<spring:message code="field.formulario790.javascript.gradoDiscapacidadError"/>";
 		 var gradoDiscapacidad1 ="<spring:message code="field.formulario790.javascript.gradoDiscapacidadError1"/>";
		 var porcentajeDiscapacidad = "<spring:message code="field.formulario790.javascript.porcentajeDiscapacidadError"/>";
 		 var caractEspeciales ='<spring:message code="field.formulario790.javascript.caracteresEspecialesError"/>';
 		 var gradoDiscapacidad2 ="<spring:message code="field.formulario790.javascript.gradoDiscapacidadError2%"/>";
 		 var dolar = "<spring:message code="field.formulario790.errorDolar"/>"; 
  	   </script>