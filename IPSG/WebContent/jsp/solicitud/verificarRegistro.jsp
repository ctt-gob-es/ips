<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.mpr.es/ipsg/bean-tags" prefix="bean" %>
<%@ taglib uri="http://www.mpr.es/ipsg/html-tags" prefix="html" %>
<%@ taglib uri="http://www.mpr.es/ipsg/logic-tags" prefix="logic" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="es.map.ipsg.bean.*" %>

  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/pae-bootstrap.css"/>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/bower_components/selectize/dist/css/selectize.css"/>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/bower_components/font-awesome/css/font-awesome.css"/>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/bower_components/jquery-ui/themes/smoothness/jquery-ui.css"/>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/pae-general.css"/>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/pae-modules.css"/>

 <script type="text/javascript" src="<%=request.getContextPath()%>/bower_components/jquery/jquery.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/bower_components/jquery-ui/ui/core.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/bower_components/jquery-ui/ui/widget.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/bower_components/jquery-ui/ui/mouse.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/bower_components/jquery-ui/ui/position.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/bower_components/jquery-ui/ui/draggable.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/bower_components/jquery-ui/ui/resizable.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/bower_components/jquery-ui/ui/menu.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/bower_components/jquery-ui/ui/selectmenu.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/bower_components/jquery-ui/ui/tabs.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/bower_components/jquery-ui/ui/button.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/bower_components/jquery-ui/ui/datepicker.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/bower_components/jquery-ui/ui/dialog.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/bower_components/jquery-ui/ui/progressbar.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/bower_components/jquery-ui/ui/accordion.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/bower_components/jquery-ui/ui/tooltip.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/bower_components/tablesorter/jquery.tablesorter.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/bower_components/bootstrap-sass/assets/javascripts/bootstrap.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/bower_components/jquery-ui/ui/autocomplete.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/bower_components/selectize/dist/js/standalone/selectize.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/bower_components/jquery-placeholder/jquery.placeholder.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/js/scripts.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/Datepicker_calendario.js"></script> 
<%
String registro = (String)request.getAttribute("idRegistro");
System.out.println("Registro :" + registro);
String llamada = (String)request.getAttribute("llamada");
System.out.println("Llamada: " + llamada);
%>

<script type="text/javascript">
window.onunload = refreshParent;
function refreshParent() {
    window.opener.location.reload();
}

var reg = "<%=registro%>";
var llamadaVar = "<%=llamada%>";
function cerrar(){
	document.getElementById("enviar").style.display = "block";		
	//window.opener.location.href = "../spring/verBuscarSolicitudesIncidencias?menu=S";
	window.close();	 
}

function ocultar(){
	if(llamadaVar == "Verificar"){
		document.getElementById("enviar").style.display = "block";
		window.location.href = "../spring/verVerificarRegistro?idRegistro="+reg;
	}	
	return false;
}
</script>


<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title></title>
	</head>

	<body style="margin-left:0.4em;" onload="ocultar()">
	
	
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

		<div class="pae-title"><spring:message code="field.verificarRegistro.titulo"/></div>
	
		
	<form:form modelAttribute="consultarRegistrosRecForm" action="/IPSG/spring/verVerificarRegistro" >			
	<logic:present name="org.apache.spring.ERROR">
	<div id="error">
		<html:errors/>
	</div>
	<div class="clear"></div>
	</logic:present>			
			
	<%-- MAQUETACION INI --%>
	<div class="">	
		<div data-function="accordion-box" class="pae-box">
			<div class="pae-box__body">
				<fieldset>
					<div class="pae-layout">
					<logic:present name="actualizacion" scope="request">			
					<logic:equal name="actualizacion" value="S">
						<%-- Numero Registro --%>
						<div class="pae-layout__item pae-u-3/12 pae-u-2/12-lap pae-u-1/1-palm">
							<div class="pae-form__cell">
								<span class="pae-form__label pae-form__span-label">
									<spring:message code="field.consultarRegistrosRec.numRegistro.mayus"/>
								</span>
								<form:input path="numRegistro" id="numRegistro" disabled="true" class="pae-form__input"/>						
							</div>
						</div>
						<%-- Numero Registro --%>
						<%-- Fecha Registro --%>
						<div class="pae-layout__item pae-u-3/12 pae-u-4/12-lap pae-u-2/3-palm">
							<div class="pae-form__cell">
								<label for="fechaNacimiento" class="pae-form__label">
									<span class="pae-form__label--text">
										<spring:message code="field.consultarRegistrosRec.fechaRegistro.mayus"/>
									</span>
								</label>
								<div class="pae-form__box-datepicker">
									<form:input type="text" path="feRegistro"
										name="feRegistro" data-function="function-datepicker"
										placeholder="dd/mm/aaaa"  class="pae-form__input"/>
								</div>
							</div>
						</div>					
						<%-- Fecha Registro --%>
						<%-- Codigo Oficina --%>
						<div class="pae-layout__item pae-u-3/12 pae-u-2/12-lap pae-u-1/1-palm">
							<div class="pae-form__cell">
								<span class="pae-form__label pae-form__span-label">
									<spring:message code="field.consultarRegistrosRec.cdOficina.mayus"/>
								</span>
								<form:input path="cdOficinaOrigen" id="cdOficinaOrigen" disabled="true" class="pae-form__input"/>				
							</div>
						</div>
						<%-- Codigo Oficina --%>
						<div class="pae-layout__item pae-u-3/12 pae-u-2/12-lap pae-u-1/1-palm">
							<div align="justify" style="font-style: italic;color: blue; font-size: medium">			
								<logic:present name="actualizacion" scope="request">			
									<logic:equal name="actualizacion" value="S">
										<spring:message code="field.consultarRegistrosRec.actualizarSolicitud"/>
									</logic:equal>
								</logic:present>
								<div class="clear"></div>		
							</div>					
						</div>
						</logic:equal>
						</logic:present>
						<%-- Boton --%>
						<div class="pae-layout">
							<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm pd-left-80">
								<div class="pae-box-buttons">	
									<input type="button" value="Aceptar" name="aceptar" onclick="cerrar()" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline"/>
								</div>
							</div>
						</div>
					</div>
				</fieldset>
			</div>
		</div>
	</div>
	<%-- MAQUETACION FIN --%>			
	
	</form:form>
</body>
</html>