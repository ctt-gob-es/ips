<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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

<html>
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">
<base target="_self">
</head>
<body style="margin-left: 0.4em;" class="background-color-white-ip">
<div class="pae-body2" style="overflow-y:hidden">

<form:form modelAttribute="buscarSolicitudesForm" action="/IPSG/spring/exportarSigpSolicitudes" id="formPadre" >
	<input type="hidden" id="idSolicitud" value="${idSolicitud}"/>	
	<input type="hidden" id="entorno" value="${entorno}"/>
	
	<%-- MAQUETACION INI --%>
	<h1 class="pae-title2"><spring:message code="field.exportarXmlSigp.titulo" /></h1>
	
	 <logic:present name="mensajeConfirmacion" scope="request">
 	<div id="error">
		<div id="rellenarCampos" class="pae-alertbox--success">	
				<ul>
					<li>
						<p class="pae-alertbox__text">&nbsp&nbsp&nbsp<bean:write name="mensajeConfirmacion" scope="request"/></p>
					</li>
				</ul>
		</div>
	</div>
 </logic:present>
	<logic:present name="org.apache.spring.ERROR">
		<div id="error">
			<html:errors/>
		</div>
		<div class="clear"></div>	
	</logic:present>
	<div class="pae-box">
	<div class="pae-box__header2">	
		<fieldset style="border: none;">
		<div class="pae-layout">
		<h3 class="pae-box__header--title"><spring:message code="field.exportarXmlSigp.pregunta" /></h3>	
		</div>
			<div class="pae-layout">
							
			<div class="pae-layout__item">

			</div>
			</div>					
			<%-- Boton --%>
		<div class="filaBtn">
			<div class="pae-box-buttons"  style="width: 106%;">
						<input type="submit" name="accion" value="Fichero" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline pae-separation"/>	
						<logic:notPresent name="mensajeConfirmacion" scope="request">
							<input type="submit" name="accion" value="Correo"  class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline pae-separation"/>					
						</logic:notPresent>	
					<input type="submit" name="accion" value="Cerrar" onclick="javascript: window.close()" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline pae-separation"/>	
				</div>
			</div>
			<%-- Boton --%>
		</fieldset>	
	</div>			
	<%-- MAQUETACION FIN --%>

</form:form></div>
</body>
</html>