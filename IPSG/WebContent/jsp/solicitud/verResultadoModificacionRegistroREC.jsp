<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.mpr.es/ipsg/bean-tags" prefix="bean" %>
<%@ taglib uri="http://www.mpr.es/ipsg/html-tags" prefix="html" %>
<%@ taglib uri="http://www.mpr.es/ipsg/logic-tags" prefix="logic" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/pae-bootstrap.css"/>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/bower_components/selectize/dist/css/selectize.css"/>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/bower_components/font-awesome/css/font-awesome.css"/>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/bower_components/jquery-ui/themes/smoothness/jquery-ui.css"/>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/pae-general.css"/>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/pae-modules.css"/>

<%
String registro = (String)request.getAttribute("numeroSolicitud");
System.out.println("Registro :" + registro);
String llamada = (String)request.getAttribute("llamada");
System.out.println("Llamada: " + llamada);

String scroll = (String)request.getAttribute("scroll"); 
%>

<script type="text/javascript">

var reg = "<%=registro%>";
var llamadaVar = "<%=llamada%>";

function cerrar(){
	window.opener.location.href = "../spring/verCrearSolicitudPresencial?scroll=<%=scroll%>";
	window.close();
}

function ocultar(){
	if(llamadaVar == "Registrar"){
		document.getElementById("capaBoton").classList.remove("pae-box-buttons");
		document.getElementById("enviar").style.display = "block";
		window.location.href = "../spring/modificarSolicitudPresencial?numeroSolicitud="+reg;
	}	
	return false;
}

function openWindowDocumentos(id,tpDoc) {
	var navegador = navigator.appName;
	var extract = new Object();
	if (tpDoc=="AnexoSol" ){
		ventanaPopup = window.open("../spring/documentosAnexoSolicitud?id="+id+"&tpDoc="+tpDoc, 'miventana', 'width=550, height=470,toolbar=no,directories=no,status=no,linemenubar=no,scrollbars=yes,resizable=no ,modal=yes');	    
		
	}else{
		if (tpDoc=="JustificanteSol" ){
			ventanaPopup = window.open("../spring/documentosJustificanteSolicitud?id="+id+"&tpDoc="+tpDoc, 'miventana', 'width=550, height=470,toolbar=no,directories=no,status=no,linemenubar=no,scrollbars=no,resizable=no ,modal=yes');
		}else{
			if (tpDoc=="JustificantePag" ){
				ventanaPopup = window.open("../spring/documentosSinJustificantePago?ent=Solicitudes&idSol="+id, 'miventana', 'width=550, height=470,toolbar=no,directories=no,status=no,linemenubar=no,scrollbars=yes,resizable=no ,modal=yes');
			}
			
		}
	}	
	ventanaPopup.focus();
	return false;
	 
}

</script>

<html>
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">
<base target="_self">
</head>
<body style="margin-left: 0.4em; background-color:#ffffff;" onload="ocultar()">

<div id="enviar" 
			style="display:none;
				   position:absolute;
			       background-color:#ffffff;
			       filter:Alpha(Opacity=70);
			       top:0px;
			       left:0px;
			       width:100%;
			       height:50em">
			<div style="margin-top:6%; margin-left:30%">
				<img alt="Esperar..." src="${pageContext.request.contextPath}/images/PleaseWait.gif">
			</div>
			<div style="margin-top:10%; margin-left:30%; font-size:1.5em">
				<spring:message code="field.reintentoRec.conectar"/>
			</div>
</div>

<div style="margin-left: 1em; margin-right: 1em; float: left; width: 95%;">
<br>
	<h1 class="pae-title"><spring:message code="field.registroSolicitud.mensajeConfirmacion"/></h1>

<input type="hidden" name="idSolicitud" value="${requestScope.id}"/>
	<div class="capaAll" align="center">
		<logic:present name="mensajeRegistro">
			<br><bean:write name="mensajeRegistro"/>
			<!-- Enlace: Justificante Registro-->
			<c:set property="id" scope="request" var="id"/>
			<logic:notEmpty name="idSolicitud" scope="request">
	 		   	<A href="#" onclick="javascript: return openWindowDocumentos(${idSolicitud},'JustificanteSol');" >
		 		    <font color="#c33400">
		 		   		<spring:message code="field.solicitudRegistrada.descargaJustificanteREC"/>
		 		   	</font>
	 		   	</A>
			</logic:notEmpty>
					 	<!-- Enlace: Verificar fecha nacimiento (solo si consentimiento=true) -->
		</logic:present>
		<div class="clear"></div>
		<br><br>
		
	</div>
	<div class="filaBtn">	
		<div id="capaBoton" class="pae-box-buttons">
			<input type="button" value="Aceptar" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline pae-separation" onclick="cerrar()" />
		</div>	
	</div>
	<div class="clear"></div>
	<br>
</div>
</body>
</html>