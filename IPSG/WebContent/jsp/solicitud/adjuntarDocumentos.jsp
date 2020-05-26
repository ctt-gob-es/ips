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

<html>
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">
<base target="_self">
</head>
<body style="margin-left:0.4em;">

<form:form modelAttribute="contactarCiudadanoForm" action="/IPSG/spring/subirDocumentoAdjunto" 
style="width:100%; height:100%; min-height: 44em; overflow-y:hidden"
id="formPadre" method="post" class="pae-body" enctype="multipart/form-data" >
	<div class="pd-top-3">	
	<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm">	
	<form:hidden path="idSolicitud"/>
	<form:hidden path="entorno" value="Correos"/>
	<form:hidden path="de"/>
	<form:hidden path="asunto"/>
	<form:hidden path="cuerpoMensaje"/>
	<h1 class="pae-title"><spring:message code="field.documento.adjuntarDocumentos"/></h1>
	<logic:present name="org.apache.spring.ERROR">
	<div id="error">
		<html:errors/>
	</div>
	<div class="clear"></div>
	</logic:present>	

<%-- MAQUETACION INI --%>
	<div class="pae-box">				
		<div class="pae-box__header">	
			<fieldset>
				<div class="pae-layout">
					<%-- Nombre --%>
					<div class="pae-layout__item pae-u-3/12 pae-u-2/12-lap pae-u-1/1-palm">
						<div class="pae-form__cell">
							<span class="pae-form__label pae-form__span-label">
								<spring:message code="field.documento.nombre"/>
							</span>
							<form:input type="text" class="pae-form__input"  path="nombre" size="50" maxlength="50"/>					
						</div>
					</div>
					<%-- Nombre --%>
					<%-- Descripcion --%>
					<div class="pae-layout__item pae-u-3/12 pae-u-2/12-lap pae-u-1/1-palm">
						<div class="pae-form__cell">
							<span class="pae-form__label pae-form__span-label">
								<spring:message code="field.documento.descripcion"/>
							</span>
							<form:input type="text" class="pae-form__input"  size="50" path="descripcion" id="descripcion" maxlength="100"/>				
						</div>
					</div>
					<%-- Descripcion --%>
					<%-- File --%>
					<div class="pae-layout__item pae-u-3/12 pae-u-2/12-lap pae-u-1/1-palm">
						<div class="pae-form__cell">
							<span class="pae-form__label pae-form__span-label">
								<spring:message code="field.documento.fichero"/>
							</span>
							<input type="file" name="file" class="pae-form__input" size="20" id="nombre" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline"/>			
						</div>
					</div>
					<%-- File --%>		
					<%-- Boton --%>
					<div class="pae-layout">
						<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm pd-left-40">
							<div class="pae-box-buttons">			
								<input type="submit" name="submit" value="Subir" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline"/>
							</div>
						</div>
					</div>								
				</div>
			</fieldset>
		</div>	
	</div>
<%-- MAQUETACION FIN --%>	
</div>
</div>			
</form:form>
</div>
</body>
</html>