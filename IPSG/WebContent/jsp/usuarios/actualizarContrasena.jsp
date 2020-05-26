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
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">
function validaDatos(){
	var pwdNueva = document.getElementById("passwordNueva").value;
	var pwdNuevaConf = document.getElementById("confirmacionPasswordNueva").value;
	
	if(pwdNueva==""){
		alert('<spring:message code="usuario.errores.passwordNuevaRequerido"/>');
		return false;
	}
	if(pwdNuevaConf==""){
		alert('<spring:message code="usuario.errores.confirmacionPasswordNuevaRequerido"/>');
		return false;
	}
	
	return true;
}


function volver(){
	window.location.href = "${pageContext.request.contextPath}/j_spring_security_logout"; 
}

</script>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
</head>
<body style="margin-left:0.4em;">

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


<form:form modelAttribute="actualizarContrasenaForm" action="/IPSG/spring/actualizarContrasena" method="post">
<%-- 	<form:hidden path="id" value="${param.id}"/> --%>

<logic:present name="usuario" scope="request">
	<form:hidden name="usuario" path="id"/>
	<form:hidden name="usuario" path="nombre"/>
	<form:hidden name="usuario" path="primerApellido"/>
	<form:hidden name="usuario" path="segundoApellido"/>
	<form:hidden name="usuario" path="desPerfil"/>
</logic:present>
<div class="pd-top-3">
	<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm"></div>
	<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm">
	<h1 class="pae-title"><spring:message code="field.login.titulo"/></h1>
	<logic:present name="org.apache.spring.ERROR">
		<div id="error">
			<html:errors/>
		</div>
		<div class="clear"></div>
	</logic:present>
	<logic:present name="mensajeConfirmacion">
		<div class="">
				<div data-function="accordion-box" class="pae-box">
					<div class="pae-box__header">
						<h3 class="pae-box__header--title">
							<bean:write name="mensajeConfirmacion"/>
						</h3>
					</div>
		
			<div data-function="accordion-box" class="pae-box">
				<div class="pae-box__body">
					<fieldset>
						<div class="pae-layout">		
							<%-- Boton --%>
							<div class="pae-layout">
								<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm pd-left-40">
									<div class="pae-box-buttons">
										<input type="submit" value="Cerrar" onclick="javascript:window.close();" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline pae-separation"" id="REC"/>								
									</div>
								</div>
							</div>	
						</div>
					</fieldset>
				</div>
			</div>
		</div>
	</logic:present>
	<logic:notPresent name="mensajeConfirmacion">
	<div data-function="accordion-box" class="pae-box">
		<div class="pae-box__header">
			<h3 class="pae-box__header--title">
				<spring:message code="field.usuario.tituloCambiarContrasena"/> 
			</h3>
		</div>
		<div class="pae-box__body">
			<fieldset>
				<div class="pae-layout">
<!-- 					USER -->
					<div class="pae-layout__item pae-u-7/12 pae-u-2/12-lap pae-u-1/1-palm">
						<div class="pae-form__cell">
							<span class="pae-form__label pae-form__span-label">
								<spring:message code="field.usuario.passwordNueva.mayus"/>
				
							</span>
							<form:input path="passwordNueva" type="password" class="pae-form__input" size="15" maxlength="15"/>		
						</div>
					</div>
<!-- 					USER -->
				</div>
				<div class="pae-layout">
<!-- 					PASS -->
					<div class="pae-layout__item pae-u-7/12 pae-u-2/12-lap pae-u-1/1-palm">
						<div class="pae-form__cell">
							<span class="pae-form__label pae-form__span-label">								
								<spring:message code="field.usuario.confirmacionPasswordNueva.mayus"/> 
							</span>			
							<form:input path="confirmacionPasswordNueva" type="password" class="pae-form__input" size="15" maxlength="15"/>		
						</div>
					</div>
<!-- 					PASS -->
				</div>		
				<div class="filaBtn">
					<div class="pae-box-buttons">
						<input type="submit" value="Guardar" onclick="return validaDatos();" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-separation"/>
						<input type="button" value="Cancelar" onclick="volver()" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium mg-left-1"/>		
					</div>
				</div>
											
			</fieldset>
		</div>
	</div>
	</logic:notPresent>

</div>
</div>
</form:form>
</div>

</div>
<div class="pae-footer">
 	<div class="pae-wrapper">
      	<div class="pae-layout">
	        <div class="pae-layout__item pae-u-3/12 pae-u-3/12-lap pae-u-1/1-palm">
				<figure class="pae-footer__images pae-footer__images--left">	
				<a href="http://administracion.gob.es" target="_blank" title="Punto de Acceso General (administracion.gob.es)"><img src="<%=request.getContextPath()%>/images/logo_admon.png" alt="Punto de Acceso General" class="pae-footer__images-img"/></a>
				</figure>
			</div>
			<div
				class="pae-layout__item pae-u-6/12 pae-u-6/12-lap pae-u-1/1-palm"
				align="center">
				<nav class="pae-footer__nav">
				<ul class="pae-footer__nav__list-menu">
					<li class="pae-footer__nav__item-list"><a
						href="http://administracion.gob.es/pag_Home/avisoLegal.html"
						class="pae-footer__nav__link-item outline" target="_new"> <spring:message
								code="field.pie.avisoLegal" />
					</a></li>
					<li class="pae-footer__nav__item-list"><a
						href="http://www.060.es/060/appmanager/portal/desktop/?_nfpb=true&_pageLabel=guiaNavegacion"
						class="pae-footer__nav__link-item outline" target="_new"> <spring:message
								code="field.pie.guiaNavegacion" />
	
					</a></li>
					<li class="pae-footer__nav__item-list"><a
						href="http://administracion.gob.es/pag_Home/politicaDePrivacidad.html"
						class="pae-footer__nav__link-item outline" target="_new"> <spring:message
								code="field.pie.politicaPrivacidad" />
	
					</a></li>
					<li class="pae-footer__nav__item-list"><img
						src="<%=request.getContextPath()%>/images/xml.PNG"
						alt="xml_inside" width="46" height="20" /></li>
	
					<li class="pae-footer__nav__item-list">| <img
						src="<%=request.getContextPath()%>/images/w3c.PNG" alt="w3c"
						width="57" height="20" /></li>
				</ul>
				</nav>
			</div>
			<div
				class="pae-layout__item pae-u-3/12 pae-u-3/12-lap pae-u-1/1-palm">
				<figure class="pae-footer__images pae-footer__images--right">
				<img src="<%=request.getContextPath()%>/img/content/ue.png"
					alt="Proyecto Financiado Union Europea"
					class="pae-footer__images-img" /></figure>
			</div>
		</div>
	</div>
</div> 	
</body>
</html>