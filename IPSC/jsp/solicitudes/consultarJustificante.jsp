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

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
</head>

<div class="pae-body pae-body--without-padding-mobile" style="min-height: 50em;">
	<div class="pae-wrapper">
		<div class="pae-layout">
			<div class="pae-layout__item pae-u-12/12 pae-u-12/12-lap pae-u-1/1-palm">
		<h1 class="pae-title pae-title__2" style="margin-left:1em; margin-right: 1em;float: left;"><spring:message code="field.documento.documentosJustificado"/></h1>

<div style="margin-left:1em; margin-right: 1em;float: left;width: 85%;">
		
	<logic:present name="org.apache.spring.ERROR">
		<div id="error">
			<html:errors/>
		</div>
		<div class="clear"></div>
	</logic:present>
	
<%-- MAQUETACION INI --%>
<div class="pae-box">
	<div data-function="accordion-box" class="pae-box__header2">
		<div class="pae-list-details__item-text">	
			<div class="pae-list-details">
				<p style="font-size:125%; text-align:center;"><spring:message code="field.documento.error.descarga"></spring:message></p>
			</div>
			
	<div class="clear"></div><br><br>
	
	<%-- Boton --%>
	<div class="filaBtn">		
		<div class="pae-box-buttons">	
			<input type="submit" name="accion" value="Cerrar" class="pae-buttons pae-buttons__2 pae-buttons--default pae-buttons--medium pae-separation" onclick="javascript: window.close()">	
		</div>				
	</div>
	
	</div>
</div>
</div>	
</div>
</div>
</div>
</div>
</html>
