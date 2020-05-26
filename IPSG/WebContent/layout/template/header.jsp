
<link rel="shortcut icon" type="image/ico" href="../../images/ipsFavicon.ico" type="image/x-icon"/>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="bean" uri="http://www.mpr.es/ipsg/bean-tags" %>
<%@ taglib prefix="logic" uri="http://www.mpr.es/ipsg/logic-tags" %>
<%@ taglib prefix="html" uri="http://www.mpr.es/ipsg/html-tags" %>

<%@page import="org.springframework.security.core.context.SecurityContext"%>
<%@page import="org.springframework.security.core.context.SecurityContextHolder"%>

<%@page import="es.map.ips.common.exception.BusinessException"%>
<%@page import="es.map.ips.common.util.BeanFormatter"%>
<%@page import="es.map.ips.model.UsuarioQuery"%>
<%@page import="es.map.ipsg.bean.UsuarioBean"%>
<%@page import="es.map.ipsg.manager.UsuarioManager"%>
<%@page import="es.map.ips.common.spring.ApplicationContextProvider"%>
<%@page import="es.map.ipsg.util.Constantes"%>
<%@page import="org.springframework.security.autentica.authentication.AutenticaAuthenticationToken"%>
<%@page import="es.map.ipsg.manager.UsuarioManager"%>

<script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/javascript/jquery-1.10.2.js"></script>
<script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/javascript/jquery-ui-1.10.4.custom.js"></script>
<script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/javascript/Datepicker_calendario.js"></script>
<script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/javascript/utilsFechas.js"></script>

  <!-- Archivos CSS-->
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/pae-bootstrap.css"/>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/bower_components/selectize/dist/css/selectize.css"/>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/bower_components/font-awesome/css/font-awesome.css"/>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/bower_components/jquery-ui/themes/smoothness/jquery-ui.css"/>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/pae-general.css"/>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/pae-modules.css"/>
  <!-- Fin archivos CSS-->
  <!-- Archivos JS-->
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
  <!-- Fin rchivos JS-->


<script type="text/javascript">
function openWindow(action, width, height) {
	var extract = new Object();
	ventanaPopup = window.open(action, 'miventana', 'width='+width+', height='+height+',toolbar=no,directories=no,status=no,linemenubar=no,scrollbars=yes,resizable=yes ,modal=yes');	    	    
	ventanaPopup.focus();
}
</script>

<div class="pae-header">
	<div class="pae-layout">
		<div class="pae-layout__item pae-u-1/1 pae-u-5/5-lap pae-u-5/5-palm">
         	<figure class="pae-header__logo"><a href="<%=request.getContextPath()%>"><img src="<%=request.getContextPath()%>/img/content/logo.png" alt="IPS" class="pae-header__logo-img"/></a></figure>
			<h1 class="pae-header__title">Inscripción de Pruebas Selectivas</h1>
				<nav class="pae-nav navbar navbar-default pae-head">
				<div class="container-fluid" id="cabecera_menu_superior">
					<div class="navbar-header">
						<button type="button" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false" class="navbar-toggle collapsed">
							<span class="sr-only">Toggle navigation</span>
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
						</button>
					</div>
					<logic:notPresent name="usuario" scope="session">
						<%
						/* RECUPERA EL USUARIO DEL CONTEXTO DE SPRING Y LO GUARDA EN SESION*/
						/* ****************************************** */
						String username = null;
						UsuarioManager usuarioManager = (UsuarioManager) ApplicationContextProvider.getInstance().getBean("usuarioManager");
						try{
							
							SecurityContext context = (SecurityContext) session.getAttribute("SPRING_SECURITY_CONTEXT");
							SecurityContextHolder.getContext().setAuthentication(context.getAuthentication());
							
							AutenticaAuthenticationToken user = (AutenticaAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
							
							// Obtenemos el usuario de base de datos a partir del identificador obtenido de autentica
							UsuarioQuery usuarioQuery = new UsuarioQuery();
							usuarioQuery.setNif(user.getIdentificador());
							UsuarioBean userIPS = usuarioManager.buscarUsuario(usuarioQuery);
							
							username = userIPS.getLogin();
						}catch(Exception e){}
						
						UsuarioQuery usuarioQuery = new UsuarioQuery();
						usuarioQuery.setLogin(username);
						
						
						UsuarioBean usuarioBean = usuarioManager.buscarUsuario(usuarioQuery);
						
						String pagActiva = (String) session.getAttribute("pagActiva");
													
						session.setAttribute("usuario", usuarioBean);
						/* ****************************************** */

						%>
					</logic:notPresent>
					<logic:present name="usuario" scope="session">
					<div  id="bs-example-navbar-collapse-1" class="collapse navbar-collapse">
					<ul class="pae-nav__list-menu nav navbar-nav">
					    	 <logic:equal name="usuario" property="desPerfil" value="<%=Constantes.DENOM_PERFIL_ADMIN%>">
							 	 <li class="pae-nav__item-list"><a href="<%=request.getContextPath()%>/spring/descargarDocumentoAdjunto?ent=Ayuda_admin" title="<spring:message code="field.header.ayuda" />" class="pae-nav__item-list  pae-nav__icon pae-nav__link-item--icon-help outline"><span class="pae-nav__link-item"><spring:message code="field.header.ayuda" /></span></a>  </li>
							 </logic:equal>
							 <logic:notEqual name="usuario" property="desPerfil" value="<%=Constantes.DENOM_PERFIL_ADMIN%>">
							 	 <li class="pae-nav__item-list" ><a href="<%=request.getContextPath()%>/spring/descargarDocumentoAdjunto?ent=Ayuda_gestor" title="<spring:message code="field.header.ayuda" />" class="pae-nav__item-list  pae-nav__icon pae-nav__link-item--icon-help outline"><span class="pae-nav__link-item"><spring:message code="field.header.ayuda" /></span></a>  </li>
							 </logic:notEqual>
					
<!--						<li><a href="javascript:openWindow('${pageContext.request.contextPath}/spring/reportarIncidencias',560,500)"><spring:message code="field.header.reportarIncidencia" /></a> | </li>-->
								 <li class="pae-nav__item-list"><a href="javascript:openWindow('https://valide.redsara.es/formulariosCAID/irAltaSolicitud?idServicio=6')" class=" pae-nav__link-item outline">
								 <spring:message code="field.header.reportarIncidencia" /></a></li>
							     <li class="pae-nav__item-list"><a href="javascript:openWindow('${pageContext.request.contextPath}/spring/verCambiarContrasenaUsuarios?id=${usuario.id}',600,400)" class=" pae-nav__link-item outline"><spring:message code="field.header.cambiarContrasena" /></a></li>
							     <li class="pae-nav__item-list"><a href="${pageContext.request.contextPath}/j_spring_security_logout" title="Salir" class="pae-nav__link-item outline"><spring:message code="field.header.salir"/></a></li>
					   
					    </ul>
					    
					    <ul>
							<li> <b><spring:message code="field.portada.perfil"/></b> 
								<bean:write name="usuario" scope="session" property="desPerfil"/></li>
												
						</ul>
						<ul>	
							<li><b><spring:message code="field.portada.usuario"/></b>
								<bean:write name="usuario" scope="session" property="nombre"/> 
								<bean:write name="usuario" scope="session" property="primerApellido"/>
								<bean:write name="usuario" scope="session" property="segundoApellido"/>
							</li>
						</ul>							
					    </div>
					</logic:present>
				</div>
			</nav>
		</div>
	</div>
</div>

 	
 		
		
	  