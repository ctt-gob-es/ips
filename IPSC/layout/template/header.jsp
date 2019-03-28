
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page import= "java.util.ResourceBundle" %>
<%@page import="es.map.ipsc.bean.CiudadanoBean"%>

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

<%  
	String error = (String)request.getAttribute("errorContactar");
	String errorCont="";
	if(error!=null)
		errorCont=error;
	String llamada = (String)request.getParameter("llamada");
	String llamadaRequest="";
	if(llamada!=null)
		llamadaRequest=llamada;
%>

<%
	final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("MessageResources");
	//final ResourceBundle IPSC_RESOURCE_BUNDLE = ResourceBundle.getBundle("ipsc-config");
	//String urlJusticia = IPSC_RESOURCE_BUNDLE.getString("url.externa.modelo.justicia");
	String pagActiva = (String)request.getSession().getAttribute("pagActiva");
	String convocatorias = RESOURCE_BUNDLE.getString("field.menu.listaConvoc");
	String solicitudes = RESOURCE_BUNDLE.getString("field.menu.listaSolic");
	String descargarForm = RESOURCE_BUNDLE.getString("field.descargarFormulario");
%>

  <div class="pae-header" id="cabecera">
    <div class="pae-wrapper">
      <div class="pae-layout">
        <div class="pae-layout__item pae-u-1/1 pae-u-5/5-lap pae-u-5/5-palm" id="cabecera_menu">
          <figure class="pae-header__logo"><a href="<%=request.getContextPath()%>"><img src="<%=request.getContextPath()%>/img/content/logo.png" alt="PAE IPS" class="pae-header__logo-img"/></a></figure>
          <h1 class="pae-header__title"><a href="<%=request.getContextPath()%>"><spring:message code="field.header.title"/></a></h1>
          <nav class="pae-nav navbar navbar-default">
            <div class="container-fluid"  id="cabecera_menu_superior">
              <div class="navbar-header">
                <button type="button" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false" class="navbar-toggle collapsed"><span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></button>
              </div>
              <div id="bs-example-navbar-collapse-1" class="collapse navbar-collapse">
                <ul class="pae-nav__list-menu nav navbar-nav">
                  <li class="pae-nav__item-list"><a href="#" title="<spring:message code="field.cabecera.ayuda" />" class="pae-nav__icon pae-nav__link-item--icon-help outline" data-toggle="dropdown"><span class="pae-nav__link-item"><spring:message code="field.cabecera.ayuda" /></span></a>
                  	<ul class="pae-nav__list-submenu dropdown-menu">
                  		<li class="pae-nav__submenu-item"><a href="<%=request.getContextPath()%>/secure/descargarDocumento?ent=Ayuda" title="<spring:message code="field.cabecera.manual.usuario" />" class="pae-nav__submenu-link outline"><spring:message code="field.cabecera.manual.usuario" /></a></li>
                      	<li class="pae-nav__submenu-item"><a href="<%=request.getContextPath()%>/secure/descargarDocumento?ent=Requisitos" title="<spring:message code="field.cabecera.requisitos.tecnicos" />" class="pae-nav__submenu-link outline"><spring:message code="field.cabecera.requisitos.tecnicos" /></a></li>
                     </ul>
                  </li>
<%--                   <li class="pae-nav__item-list"><a href="" onclick="javascript: llamadaContactar(); return false;" title="<spring:message code="field.cabecera.contactenos" />" class="pae-nav__link-item outline"><spring:message code="field.cabecera.contactenos" /></a></li> --%>
					<li class="pae-nav__item-list"><a href="mailto:cau.060@correo.gob.es" title="<spring:message code="field.cabecera.contactenos" />" class="pae-nav__link-item outline"><spring:message code="field.cabecera.contactenos" /></a></li>
                  <li class="pae-nav__item-list dropdown"><a href="#" title="<spring:message code="field.idioma" />" data-toggle="dropdown" class="pae-nav__link-item pae-nav__link-item--icon outline dropdown-toggle"><spring:message code="field.idioma" /></a>
                    <ul class="pae-nav__list-submenu dropdown-menu">
                    	<%
							String loc = "";
							if(request.getParameter("lang") != null)
								loc = request.getParameter("lang");						
						%>
                      <li class="pae-nav__submenu-item"><a href="<%=request.getContextPath()%>/secure/locale?lang=es" onclick="return confirmacion()" title="<spring:message code="field.idioma.castellano" />" class="pae-nav__submenu-link outline"><spring:message code="field.idioma.castellano" /></a></li>
                      <li class="pae-nav__submenu-item"><a href="<%=request.getContextPath()%>/secure/locale?lang=ca" onclick="return confirmacion()" title="<spring:message code="field.idioma.catalan" />" class="pae-nav__submenu-link outline"><spring:message code="field.idioma.catalan" /></a></li>
                      <li class="pae-nav__submenu-item"><a href="<%=request.getContextPath()%>/secure/locale?lang=gl" onclick="return confirmacion()" title="<spring:message code="field.idioma.gallego" />" class="pae-nav__submenu-link outline"><spring:message code="field.idioma.gallego" /></a></li>
                      <li class="pae-nav__submenu-item"><a href="<%=request.getContextPath()%>/secure/locale?lang=eu" onclick="return confirmacion()" title="<spring:message code="field.idioma.euskera" />" class="pae-nav__submenu-link outline"><spring:message code="field.idioma.euskera" /></a></li>
                      <li class="pae-nav__submenu-item"><a href="<%=request.getContextPath()%>/secure/locale?lang=vl" onclick="return confirmacion()" title="<spring:message code="field.idioma.valenciano" />" class="pae-nav__submenu-link outline"><spring:message code="field.idioma.valenciano" /></a></li>
                    </ul>
                  </li>
                </ul>
              </div>
            </div>
          </nav>
        </div>
      </div>
    </div>
  </div>
  <% if (convocatorias.equals(pagActiva) || solicitudes.equals(pagActiva)){%>
  <div class="pae-wrapper">
    <div class="pae-layout">
      <div class="pae-layout__item pae-u-11/12 pae-u-1/1-lap pae-u-1/1-palm">
        <div class="pae-tabs-pest"></div>
      </div>
    </div>
  </div>
  <%}else{%>
  	<div class="pae-wrapper hiddenAccesible">
    <div class="pae-layout">
      <div class="pae-layout__item pae-u-11/12 pae-u-1/1-lap pae-u-1/1-palm">
        <div class="pae-tabs-pest"></div>
      </div>
    </div>
  </div>
  <%}%>
    <div class="pae-wrapper">
    	<% CiudadanoBean usuActual = (CiudadanoBean) request.getSession().getAttribute("usuarioClave"); %>
      <div class="pae-layout">
        <div class="pae-layout__item pae-u-12/12 pae-u-1/1-lap pae-u-1/1-palm">
          <div id="tabs" data-function="fc-tabs" class="pae-tabs">
            <ul class="pae-tabs__list-item pae-tabs-pest__list-item" id="cabecera_menu_inferior">
              <li class="pae-tabs__item pae-tabs-pest__item outline"><a href="#convocatoriasRef" onclick="javascript: llamadaConvocatorias();return false;" class="pae-tabs__link pae-tabs__link--left pae-tabs-pest__link pae-tabs-pest__link--left" title="<spring:message code="field.menu.listaConvoc"/>"><span class="pae-tabs__link-text pae-tabs-pest__link-text"><spring:message code="field.menu.listaConvoc"/><span class="pae-tabs__link-text pae-tabs-pest__text-add">&nbsp;(<%=request.getAttribute("numTotalConvocatorias")%>)</span></span></a></li>
              <% if (null!=usuActual){%>
              <li class="pae-tabs__item pae-tabs-pest__item outline"><a href="#inscripciones" onclick="javascript: llamadaBuscarIncripciones();return false;" class="pae-tabs__link pae-tabs__link--icon pae-tabs__link--right pae-tabs-pest__link--icon pae-tabs-pest__link-right"><span class="pae-tabs__link-text pae-tabs-pest__link-text" title="<spring:message code="field.menu.misInscripciones"/>"><spring:message code="field.menu.misInscripciones"/></span></a></li>
            	<%}else{ %>
            	<li class="pae-tabs__item pae-tabs-pest__item outline"><a href="#inscripciones" onclick="javascript: llamadaBuscarIncripciones();return false;" class="pae-tabs__link pae-tabs__link--icon_close pae-tabs__link--right pae-tabs-pest__link--icon_close pae-tabs-pest__link-right"><span class="pae-tabs__link-text pae-tabs-pest__link-text" title="<spring:message code="field.menu.misInscripciones"/>"><spring:message code="field.menu.misInscripciones"/></span></a></li>
            	<%} %>
              <li class="pae-tabs__item pae-tabs-pest__item outline"><a href="#convocatoriasSubRef" onclick="javascript: llamadaConvocatoriasSub();return false;" class="pae-tabs__link pae-tabs__link--left pae-tabs-pest__link pae-tabs-pest__link--left" title="<spring:message code="field.menu.listaConvocSub"/>"><span class="pae-tabs__link-text pae-tabs-pest__link-text"><spring:message code="field.menu.listaConvocSub"/><span class="pae-tabs__link-text pae-tabs-pest__text-add">&nbsp;(<%=request.getAttribute("numTotalConvocatoriasSub")%>)</span></span></a></li>	
            </ul>
          </div>
        </div>
      </div>
    </div>
    
	  
  <form action="" method="post" name="formularioNuevo" id="formularioNuevo" >
		<input type="hidden" name="certificadoNuevo" value="certificadoNuevo" id="certificadoNuevo"> 
  </form>
  <form  name="Contactar" id="Contactar" method="POST"> </form>
 	<script>
 		console.log("antes del form de BuscarIncripciones");
	</script>
  <form  name="BuscarIncripciones" id="BuscarIncripciones" method="POST"> </form>
 	<script>
		console.log("antes del form de Inscribirse");
	</script>
  <form  name="Inscribirse" id="Inscribirse" action=""  method="POST"> 
  	<input type="hidden" id="convocatoria" name="convocatoria" />
  	<input type="hidden" id="idSolicitud" name="idSolicitud" />
  </form>
  <form  name="Subsanar" id="Subsanar" action=""  method="POST"> 
  	<input type="hidden" id="convocatoriaSub" name="convocatoriaSub" />
  	<input type="hidden" id="idSolicitudSub" name="idSolicitudSub" />
  </form>  
  <form name="ConvocatoriasEnlace" id="ConvocatoriasEnlace" action="<%=request.getContextPath()%>/secure/buscarConvocatorias?form=L"  method="POST">
  		<input type="hidden" name="verTodo" id="verTodo"/>
  </form>
  <form name="ConvocatoriasEnlaceSub" id="ConvocatoriasEnlaceSub" action="<%=request.getContextPath()%>/secure/buscarConvocatoriasSubsanar#convocatoriasSubRef"  method="POST">
  		<input type="hidden" name="verTodoSub" id="verTodoSub"/>
  </form>  

<script type="text/javascript">
	var errorContactar = "<%=errorCont%>";
	var llamadaReq ="<%=llamadaRequest%>";
	
	if(errorContactar != null && errorContactar != ""){
		window.close();
	}
	
	function openWindow(action, width, height) {
		var extract = new Object();
		ventanaPopup = window.open(action, 'miventana', 'width='+width+', height='+height+',toolbar=no,directories=no,status=no,linemenubar=no,scrollbars=yes,resizable=no ,modal=yes');	    
		ventanaPopup.focus();
	}

	function confirmacion(){
 		var resultado =   !confirm("<spring:message code='filed.message.lang'/>");
		if(resultado  == true)
			return false;
		else
			return true;			
	}	
	
	function llamadaBuscarIncripciones(){
		if (llamadaReq=="Buscar" ||llamadaReq=="Inscribirse" || llamadaReq=="Subsanar" ||llamadaReq==null||llamadaReq==""){
			document.BuscarIncripciones.action ="<%=request.getContextPath()%>/secure/comprobarSesionClave?llamada=Buscar#inscripciones";
			document.getElementById("BuscarIncripciones").submit();
		}else if(llamadaReq=="Volver"){
			document.BuscarIncripciones.action ="<%=request.getContextPath()%>/secure/comprobarSesionClave?llamada=Volver#inscripciones";
			document.getElementById("BuscarIncripciones").submit();
		}
	}
	
	function llamadaConvocatorias(){
		if(document.getElementById("search")) {
			document.getElementById("search").value = null;
		}
		document.getElementById("verTodo").value = false;
		document.getElementById("ConvocatoriasEnlace").submit();
	}
 	   	
	function llamadaConvocatoriasSub(){
		if(document.getElementById("search")) {
			document.getElementById("search").value = null;
		}
		document.getElementById("verTodoSub").value = false;
		document.getElementById("ConvocatoriasEnlaceSub").submit();
	}	
 	   	
	function llamadaContactar(){
		document.Contactar.action= "<%=request.getContextPath()%>/secure/comprobarSesionClave?llamada=Contactar"; 
		document.getElementById("Contactar").submit(); 
	 }
	
	function llamadaInscribirse(idConvocatoria){
		if (llamadaReq=="Inscribirse"|| llamadaReq=="Subsanar" || llamadaReq==null||llamadaReq==""||llamadaReq=="N"||llamadaReq=="Buscar"){
			document.Inscribirse.action ="<%=request.getContextPath()%>/secure/comprobarSesionClave?llamada=Inscribirse&convocatoria="+idConvocatoria;
			document.getElementById("convocatoria").value = idConvocatoria;
			document.getElementById("Inscribirse").submit();
		}
	}

	function llamadaSubsanar(idConvocatoria, tipoPersona){
		if (llamadaReq=="Inscribirse"|| llamadaReq=="Subsanar"||llamadaReq==null||llamadaReq==""||llamadaReq=="N"||llamadaReq=="Buscar"){
			document.Subsanar.action ="<%=request.getContextPath()%>/secure/comprobarSesionClave?llamada=Subsanar&convocatoria="+idConvocatoria+"&tipoPersona="+tipoPersona+"&modificar=true#convocatoriasSubRef";
			document.getElementById("convocatoriaSub").value = idConvocatoria;
			document.getElementById("Subsanar").submit();
		}
	}	

	function llamadaInscribirseUsuario(idConvocatoria,tipoPersona){
		if (llamadaReq=="Subsanar"||llamadaReq=="Inscribirse"||llamadaReq==null||llamadaReq==""||llamadaReq=="N"||llamadaReq=="Buscar"){
			document.Inscribirse.action =
				"<%=request.getContextPath()%>/secure/comprobarSesionClave?llamada=Inscribirse&convocatoria="+idConvocatoria+"&tipoPersona="+tipoPersona;
			document.getElementById("convocatoria").value = idConvocatoria;
			document.getElementById("Inscribirse").submit();
		}
	}

	function llamadaInscribirseContinuar(idConvocatoria){
		if (llamadaReq=="Subsanar"||llamadaReq=="Inscribirse"||llamadaReq==null||llamadaReq==""||llamadaReq=="N"||llamadaReq=="Buscar"){
			document.Inscribirse.action ="<%=request.getContextPath()%>/secure/comprobarSesionClave?continuar=true&llamada=Inscribirse&convocatoria="+idConvocatoria;
			document.getElementById("convocatoria").value = idConvocatoria;
			document.getElementById("Inscribirse").submit();
		}
	}

	function llamadaInscribirseModificar(idConvocatoria, idSolicitud){
		if (llamadaReq=="Subsanar"||llamadaReq=="Inscribirse"||llamadaReq==null||llamadaReq==""||llamadaReq=="N"||llamadaReq=="Buscar"){
			document.Inscribirse.action ="<%=request.getContextPath()%>/secure/comprobarSesionClave?modificar=true&llamada=Inscribirse&convocatoria="+idConvocatoria;
			document.getElementById("convocatoria").value = idConvocatoria;
			document.getElementById("idSolicitud").value = idSolicitud;
			document.getElementById("Inscribirse").submit();
		}
	}
	    
	function llamadaSubsanarModificar(idConvocatoria, idSolicitud){
		if (llamadaReq=="Subsanar"||llamadaReq=="Inscribirse"||llamadaReq==null||llamadaReq==""||llamadaReq=="N"||llamadaReq=="Buscar"){
			document.Subsanar.action ="<%=request.getContextPath()%>/secure/comprobarSesionClave?llamada=Subsanar&modificar=true&convocatoria="+idConvocatoria;
			document.getElementById("convocatoriaSub").value = idConvocatoria;
			document.getElementById("idSolicitudSub").value = idSolicitud;
			document.getElementById("Subsanar").submit();
		}
	}
	    
</script>