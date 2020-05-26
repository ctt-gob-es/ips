<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="ipsg" uri="http://www.mpr.es/ipsg/tags" %>

  <!-- Archivos CSS-->
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/pae-bootstrap.css"/>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/bower_components/selectize/dist/css/selectize.css"/>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/bower_components/font-awesome/css/font-awesome.css"/>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/bower_components/jquery-ui/themes/smoothness/jquery-ui.css"/>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/pae-general.css"/>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/pae-modules.css"/>
<link rel="stylesheet" href='<%=request.getContextPath()%>/css/estilos.css' type="text/css">
<link rel="stylesheet" href='<%=request.getContextPath()%>/css/estilo_060.css' type="text/css">
  <!-- Fin archivos CSS-->
  <!-- Archivos JS-->

  <script type="text/javascript" src="<%=request.getContextPath()%>/js/scripts.min.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/javascript/utilsSubmit.js"></script>
  <!-- Fin rchivos JS-->

<%@page import="java.util.ResourceBundle"%>
<%
	
	final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("MessageResources");
	
	String usuarios = RESOURCE_BUNDLE.getString("field.menu.usuarios");
	
	String consultas = RESOURCE_BUNDLE.getString("field.menu.consultas");
	String solicitudes = RESOURCE_BUNDLE.getString("field.menu.solicitudes");
	String tablasBase = RESOURCE_BUNDLE.getString("field.menu.tablasBase");
	String convocatorias = RESOURCE_BUNDLE.getString("field.menu.convocatorias");
	String alertas = RESOURCE_BUNDLE.getString("field.menu.alertas");
	String avisos = RESOURCE_BUNDLE.getString("field.menu.avisos");
	String solicPresenciales = RESOURCE_BUNDLE.getString("field.menu.solicPresenciales");
	String adminServExt = RESOURCE_BUNDLE.getString("field.menu.adminServExt");
	String formBaseCentroGestor = RESOURCE_BUNDLE.getString("field.menu.formBaseCentroGestor");
	
	String pagActiva = (String)request.getSession().getAttribute("pagActiva");
	
%>
<% if ("Usuarios".equals(pagActiva)){ %>
<script type="text/javascript">
var href = location.href;
	if(href.indexOf("#Usuarios") < 0){
		location.href = location.href + "#Usuarios";
	}
</script>
<%}%>
<% if ("Convocatorias".equals(pagActiva)){ %>
<script type="text/javascript">
var href = location.href;
	if(href.indexOf("#Convocatorias") < 0){
		location.href = location.href + "#Convocatorias";
	}
</script>
<%}%>
<% if ("Consultas".equals(pagActiva)){ %>
<script type="text/javascript">
var href = location.href;
	if(href.indexOf("#Consultas") < 0){
		location.href = location.href + "#Consultas";
	}
</script>
<%}%>
<% if ("Solicitudes".equals(pagActiva)){ %>
<script type="text/javascript">
var href = location.href;
	if(href.indexOf("#Solicitudes") < 0){
		location.href = location.href + "#Solicitudes";
	}
</script>
<%}%>
<% if ("Tablas&nbspBase".equals(pagActiva)){ %>
<script type="text/javascript">
var href = location.href;
	if(href.indexOf("#Tablas&nbspBase") < 0){
		location.href = location.href + "#Tablas&nbspBase";
	}
</script>
<%}%>
<% if ("Alertas".equals(pagActiva)){ %>
<script type="text/javascript">
var href = location.href;
	if(href.indexOf("#Alertas") < 0){
		location.href = location.href + "#Alertas";
	}
</script>
<%}%>
<% if ("Avisos".equals(pagActiva)){ %>
<script type="text/javascript">
var href = location.href;
	if(href.indexOf("#Avisos") < 0){
		location.href = location.href + "#Avisos";
	}
</script>
<%}%>
<% if ("Sol.&nbspPresenciales".equals(pagActiva)){ %>
<script type="text/javascript">
var href = location.href;
	if(href.indexOf("#Sol.&nbspPresenciales") < 0){
		location.href = location.href + "#Sol.&nbspPresenciales";
	}
</script>
<%}%>
<% if ("Servicios&nbspExternos".equals(pagActiva)){ %>
<script type="text/javascript">
var href = location.href;
	if(href.indexOf("#Servicios&nbspExternos") < 0){
		location.href = location.href + "#Servicios&nbspExternos";
	}
</script>
<%}%>


 <% if (usuarios.equals(pagActiva) || consultas.equals(pagActiva) || solicitudes.equals(pagActiva) || tablasBase.equals(pagActiva) || convocatorias.equals(pagActiva) || alertas.equals(pagActiva) || avisos.equals(pagActiva) || solicPresenciales.equals(pagActiva) || adminServExt.equals(pagActiva) || formBaseCentroGestor.equals(pagActiva)){%>
  <div class="pae-wrapper w100">
    <div class="pae-layout">
      <div class="pae-layout__item pae-u-11/12 pae-u-1/1-lap pae-u-1/1-palm w100">
        <div class="pae-tabs-pest"></div>
      </div>
    </div>
  </div>
  <%}else{%>
  	<div class="pae-wrapper w100 hiddenAccesible">
    <div class="pae-layout">
      <div class="pae-layout__item pae-u-11/12 pae-u-1/1-lap pae-u-1/1-palm w100">
        <div class="pae-tabs-pest"></div>
      </div>
    </div>
  </div>
  <%}%>
    <div class="pae-wrapper w100">
      <div class="pae-layout">
        <div class="pae-layout__item pae-u-12/12 pae-u-1/1-lap pae-u-1/1-palm w100" >
          <div id="tabs" data-function="fc-tabs" class="pae-tabs">
            <ul class="pae-tabs__list-item pae-tabs-pest__list-item">
				<ipsg:menu-authorize key="field.menu.convocatorias" />
				<ipsg:menu-authorize key="field.menu.usuarios" />
				<ipsg:menu-authorize key="field.menu.consultas" />
				<ipsg:menu-authorize key="field.menu.solicitudes" />
		 		<ipsg:menu-authorize key="field.menu.tablasBase" />
		 		<ipsg:menu-authorize key="field.menu.alertas" />
		 		<ipsg:menu-authorize key="field.menu.avisos" />
		 		<ipsg:menu-authorize key="field.menu.solicPresenciales" />
		 		<ipsg:menu-authorize key="field.menu.documentos" />
		 		<ipsg:menu-authorize key="field.menu.adminServExt" />
		 		<ipsg:menu-authorize key="field.menu.formBaseCentroGestor" />


            </ul>
          </div>
        </div>
      </div>
    </div>
  
<form  name="BuscarIncripciones" id="BuscarIncripciones" action=""  method="POST"> </form>
<script type="text/javascript">
function llamadaBuscarIncripciones(href){
		document.BuscarIncripciones.action =href;
		document.getElementById("BuscarIncripciones").submit();
}
</script>