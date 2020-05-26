<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="bean" uri="http://www.mpr.es/ipsg/bean-tags" %>
<%@ taglib prefix="logic" uri="http://www.mpr.es/ipsg/logic-tags" %>
<%@ taglib prefix="html" uri="http://www.mpr.es/ipsg/html-tags" %>

<%-- <%@ page import="org.springframework.security.ui.webapp.AuthenticationProcessingFilter" %> --%>
<%-- <%@ page import="org.springframework.security.ui.AbstractProcessingFilter" %> --%>
<%-- <%@ page import="org.springframework.security.core.AuthenticationException" %> --%>
<%@ page import="java.util.ResourceBundle" %>

<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/md5.js"></script> 
 <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/javascript/constantes.js"></script> 
 <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/javascript/miniapplet.js"></script>
 <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/javascript/afirmaIPS.js"></script>

 
<c:set var="urlAutentica"><bean:messageExt key="autentica.servlet"/><bean:messageExt key="autentica.servlet.parameters"/></c:set>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta http-equiv='pragma' content='no-cache'>
	<meta http-equiv='cache-control' content='no-cache, mustrevalidate'>
	<meta http-equiv='expires' content='0'>
	<title></title>

  	<link rel="stylesheet" type="text/css" href='<%=request.getContextPath()%>/css/pae-general.css'/>
  	<link rel="stylesheet" type="text/css" href='<%=request.getContextPath()%>/css/pae-modules.css'/>
 
	<link rel="stylesheet" href='<%=request.getContextPath()%>/css/estilos.css' type="text/css">
	<link rel="stylesheet" href='<%=request.getContextPath()%>/css/estilo_060.css' type="text/css">
	<style type="text/css">
		@media screen and (min-width: 1026px) and (max-width: 1300px) {
				#formularioLogin {
					margin-right: 25%;
				}
		}
	</style>

</head>

<body onload="document.forms[0].elements['usuario'].focus();">

<!--  <div class=""> -->
 
<div class="pae-header">
	<div class="pae-wrapper">
		<div class="pae-layout">
			<div class="pae-layout__item pae-u-1/1 pae-u-5/5-lap pae-u-5/5-palm">
          		<figure class="pae-header__logo">
					<img src="${pageContext.request.contextPath}/images/logo_izq.PNG" alt="Gobierno de España" width="142" height="52" />
				</figure>
			</div>
		</div>
	</div>
</div>
 <div class="clear"></div>	

<div class="pae-body" style="width:100%; height:100%; min-height: 44em; overflow-y:hidden">

<form:form modelAttribute="validarUsuarioSpringForm" >
		<input id="certificado" name="certificado" type="hidden" value=""/>
		<input id="nombreCertificado" name="nombreCertificado" type="hidden" value=""/>
		<input type="hidden" id="passwordField" type="password" name="j_password" size="40"/>	
		<input type="hidden" id="compruebaUsuario" name="compruebaUsuario" value="Enviar"/>	



<!--  MAQUETACION INI  -->
<div class="pd-top-3">
	<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm"></div>
	<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm">
	<h1 class="pae-title"><spring:message code="field.login.titulo"/> </h1>
		<%-- ERRORES --%>
		<c:if test="${not empty param.login_error}">
			<div id="error" class="pae-mb">
				<div id="rellenarCampos" class="pae-alertbox pae-alertbox--warning">
					<p class="pae-alertbox__text fw-bold">Errores:</p>
					<ul>
						<c:if test="${SPRING_SECURITY_LAST_EXCEPTION.message == 'Bad credentials'}">
							<li><p class="pae-alertbox__text"><spring:message code="login.error.usuarioContrasena" /></p></li> 			
						</c:if>
						<c:if test="${SPRING_SECURITY_LAST_EXCEPTION.message == 'User is disabled'}">
							<li><p class="pae-alertbox__text"><spring:message code="login.error.cuentaDesactivada" /></p></li> 					
						</c:if>
						<c:if test="${SPRING_SECURITY_LAST_EXCEPTION.message == 'User account is locked'}">
							<li><p class="pae-alertbox__text"><spring:message code="login.error.cuentaBloqueada" /></p></li>					
						</c:if>	
						<c:if test="${SPRING_SECURITY_LAST_EXCEPTION.message == 'User no exists'}">
							<li><p class="pae-alertbox__text"><spring:message code="login.error.usuarioNoExiste" /></p></li>					
						</c:if>	
						<c:if test="${SPRING_SECURITY_LAST_EXCEPTION.message == 'User is receptor'}">
							<li><p class="pae-alertbox__text"><spring:message code="login.error.usuarioReceptor" /></p></li>					
						</c:if>																
					</ul>
				</div>
			</div>	
			<div class="pae-layout__item pae-u-3/12 pae-u-2/12-lap pae-u-1/1-palm"></div>	
		</c:if>					
		<!--  ERRORES  -->
	<div data-function="accordion-box" class="pae-box">
		<div class="pae-box__header">
			<h3 class="pae-box__header--title">
				<spring:message code="field.login.instruccionesAcceso"/>
			</h3>
		</div>
		<div class="pae-box__body">
			<fieldset>
				<div class="pae-layout">
<!-- 					USER -->
					<div class="pae-layout__item pae-u-7/12 pae-u-2/12-lap pae-u-1/1-palm">
						<div class="pae-form__cell">
							<span class="pae-form__label pae-form__span-label">
								<spring:message code="field.login.usuario"/>  
				
							</span>
							<input id="usuario" type="text" name="usuario" value="" class="pae-form__input" maxlength="20" size="40"/>
						</div>
					</div>
<!-- 					USER -->
				</div>
				<div class="pae-layout">
<!-- 					PASS -->
					<div class="pae-layout__item pae-u-7/12 pae-u-2/12-lap pae-u-1/1-palm">
						<div class="pae-form__cell">
							<span class="pae-form__label pae-form__span-label">								
								<spring:message code="field.login.contrasena"/> 
							</span>			
							<input id="password" type="password" name="password" class="pae-form__input" maxlength="15" size="40"/>	
						</div>
					</div>
<!-- 					PASS -->
				</div>						
					<div class="pae-box-buttons">
						<input type="submit" value="Enviar" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium">
						<input type="button" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium mg-left-1" value="Limpiar" onclick="clean()">	
					</div>				
											
			</fieldset>
		</div>
	</div>

	<h3 class="pae-sentence">
		<spring:message code="field.login.certificado" />
		<a href="${urlAutentica}" style="font-weight: bold; color: #c33400;"  title="Identificarse mediante Certificado Digital">
			<spring:message code="field.login.certificadoAqui" />
		</a>
	</h3>
		
	</div>
	<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm"></div>	
</div>							
<!--  MAQUETACION FIN  -->
</form:form>
</div>

<div class="pae-footer">
 <div class="pae-wrapper">
      <div class="pae-layout">
        <div class="pae-layout__item pae-u-3/12 pae-u-3/12-lap pae-u-1/1-palm">
			<figure class="pae-footer__images pae-footer__images--left">	
			<a href="http://administracion.gob.es" target="_blank" title="Punto de Acceso General (administracion.gob.es)"><img src="<%=request.getContextPath()%>/images/logo_admon.png" alt="Punto de Acceso General" class="pae-footer__images-img"/></a>
			</figure>
		</div>
				<div class="pae-layout__item pae-u-6/12 pae-u-6/12-lap pae-u-1/1-palm" align="center">
          		<nav class="pae-footer__nav">
				<ul class="pae-footer__nav__list-menu">				
			     <li class="pae-footer__nav__item-list"><a href="http://administracion.gob.es/pag_Home/avisoLegal.html"  class="pae-footer__nav__link-item outline" target="_new">
			     <spring:message code="field.pie.avisoLegal" />
			     </a> 
			     </li>
				 <li class="pae-footer__nav__item-list"><a href="http://www.060.es/060/appmanager/portal/desktop/?_nfpb=true&_pageLabel=guiaNavegacion"  class="pae-footer__nav__link-item outline" target="_new">
				<spring:message code="field.pie.guiaNavegacion"/> 
				
				 </a> 
				 </li>
				 <li class="pae-footer__nav__item-list"><a href="http://administracion.gob.es/pag_Home/politicaDePrivacidad.html"  class="pae-footer__nav__link-item outline" target="_new">
	             <spring:message code="field.pie.politicaPrivacidad" />
	           
				 </a> 
				 </li>
				 <li class="pae-footer__nav__item-list"> 
				 <img src="<%=request.getContextPath()%>/images/xml.PNG" alt="xml_inside" width="46" height="20"/></li>
				 
				 <li class="pae-footer__nav__item-list"> |
				 <img src="<%=request.getContextPath()%>/images/w3c.PNG" alt="w3c" width="57" height="20"/></li>
				</ul>
				</nav>
				</div>
				<div class="pae-layout__item pae-u-3/12 pae-u-3/12-lap pae-u-1/1-palm">
				<figure class="pae-footer__images pae-footer__images--right">
				<img src="<%=request.getContextPath()%>/img/content/ue.png"  alt="Proyecto Financiado Union Europea" class="pae-footer__images-img"/></figure>
				</div>
</div>
</div> 
</div>



<script type="text/javascript" defer="defer">	

function enviar(){ 
	document.getElementById("formLogin").action ="login";
	document.getElementById("formLogin").submit();
}

function clean(){
	document.getElementById("usuario").value = "";
	document.getElementById("password").value = "";
	document.getElementById("recordarmeField").checked = false;		
}

function inscripcionApplet(){
	cargarMiniAppletFirma();
}
	
 </script> 

	<form action="<%=request.getContextPath()%>/springPub/cert/certificadoAction" method=post id="formularioCert">
		<input type="hidden" id="nuevoCertif" name="nuevoCertif" value="nuevoCertif" /> 
	</form>
</div>

</body>
</html>
