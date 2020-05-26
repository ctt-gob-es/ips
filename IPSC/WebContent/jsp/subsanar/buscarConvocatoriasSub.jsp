<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.mpr.es/ipsg/bean-tags" prefix="bean"%>
<%@ taglib uri="http://www.mpr.es/ipsg/html-tags" prefix="html"%>
<%@ taglib uri="http://www.mpr.es/ipsg/logic-tags" prefix="logic"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.ResourceBundle"%>
<%@ page import="java.util.*"%>
<%@ page import="es.map.ipsc.bean.*"%>
<%@ page import="es.map.ipsc.bean.BuscarConvocatoriasBean"%>
<%@ page import="es.map.ipsc.Constantes" %>
<script type="text/javascript" src="<%=request.getContextPath()%>/bower_components/jquery-ui/ui/accordion-custom.js"></script>
<script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/javascript/constantes.js"></script>

<%
/* 	final ResourceBundle IPSC_RESOURCE_BUNDLE = ResourceBundle.getBundle("ipsc-config");
	final String urlJusticia = IPSC_RESOURCE_BUNDLE.getString("url.externa.modelo.justicia");
	final String urlJusticiaSSJJ = IPSC_RESOURCE_BUNDLE.getString("url.externa.modelo.justicia.ssjj");
	final String centroGestorJusticia = IPSC_RESOURCE_BUNDLE.getString("siglas.centro.gestor.justicia");
	final String ssjj = Constantes.COD_SECRETARIO_JUDICIAL; */
%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
</head>

<body>	
<div class="pae-body">
	<div class="pae-wrapper">
      <div class="pae-layout">
        <div class="pae-layout__item pae-u-12/12 pae-u-1/1-lap pae-u-1/1-palm">
        <jsp:useBean id="avisos" scope="request" class="java.util.ArrayList" />
				<logic:present name="avisos" scope="request">
					<bean:size id="numAvisos" name="avisos" scope="request" />
					<logic:greaterThan name="numAvisos" value="0">
						<div id="boxAvisos" data-function="accordion-box" class="pae-box pae-box__body__2">
	   						<div class="pae-box__header">
            					<h3 class="pae-box__header--title"><spring:message code="field.solicitud.tituloAviso" /></h3>
            				</div>
            				<div class="pae-box__body pae-box__body__2">
            					<logic:iterate id="aviso" name="avisos">
	            					<legend class="pae-form__legend-text"><bean:write name="aviso" property="texto" filter="false" /></legend>
    							</logic:iterate>
            				</div>
            			</div>
					</logic:greaterThan>
				</logic:present>
        <logic:present name="BúsquedaFallida" scope="request">
        <logic:equal name="BúsquedaFallida" scope="request" value="0">
			<div class="pae-alertbox pae-alertbox--warning">
            	<p class="pae-alertbox__text"><spring:message code="field.convocatoria.error" /></p>
          	</div>
          </logic:equal>
          </logic:present>
		  <section id="#convocatoriasSubRef" class="pae-tabs__content">
			
			<logic:present name="errorNifNoExiste">
				<div id="error" class="pae-alertbox pae-alertbox--warning">
              		<p class="pae-alertbox__text"><spring:message code="certificados.error.validacion" /></p>
          		</div>
			</logic:present>
			<logic:present name="descripcionErrorClave">
				<div id="error" class="pae-alertbox pae-alertbox--warning">
              		<p class="pae-alertbox__text"><bean:write name="descripcionErrorClave"/></p>
          		</div>
			</logic:present>
			<logic:present name="funcionarioErrorClave">
				<div id="error" class="pae-alertbox pae-alertbox--warning">
              		<p class="pae-alertbox__text"><bean:write name="funcionarioErrorClave"/></p>
          		</div>
			</logic:present>
		
			<div class="pae-layout">
                <div class="pae-layout__item pae-u-6/12 pae-u-6/12-lap pae-u-1/1-palm hidden-palm">
                  <h2 class="pae-title pae-title__icon-help"><spring:message code="field.cabecera.convocatoriasSub" /> (<%=request.getAttribute("numTotalConvocatoriasSub")%>)</h2>
                </div>
                <div class="pae-layout__item pae-u-6/12 pae-u-6/12-lap pae-u-1/1-palm">
                  
                  <form:form modelAttribute="buscaConvocatoriasForm" action="/IPSC/secure/buscarConvocatoriasSubsanar#convocatoriasSubRef" id="formConvocatoriasId" method="post">
                    <fieldset>
                      <legend class="hiddenAccesible"><spring:message code="field.convocatoria.busqueda"/></legend>
                      <div class="pae-table__content-form">
                        <form:input path="search" id="search" onchange="cambiarMayusculas(this);" placeholder="Buscar por cuerpo o centro gestor" class="pae-form__input-search"/>
                        <button type="submit" action="/secure/buscarConvocatorias" value="Buscar" name="submit" class="pae-form__btn-search" onclick="busqueda();"></button>
                      </div>
                    </fieldset>
                </div>
                <logic:present name="noConvocatoriasAbiertas" scope="request">
					<strong><spring:message code="field.convocatoria.noConvocatoriasAbiertas" /></strong>
				</logic:present>
				<logic:notPresent name="noConvocatoriasAbiertas" scope="request">
					<jsp:useBean id="convocatorias" scope="request"
						class="java.util.ArrayList" />
					<logic:present name="convocatorias" scope="request">
						<bean:size id="numResultados" name="convocatorias" scope="request" />
						<logic:greaterThan name="numResultados" value="0">
                			<div class="pae-layout__item pae-u-1/1 pae-u-1/1-lap pae-u-1/1-palm">
								<form:hidden path="campo" styleId="campo" />
								<form:hidden path="direccion" styleId="direccion" />
								<form:hidden path="submit" styleId="submit"/>
								<form:hidden path="cambios" styleId="cambios" />
                  			<table id="paetable" data-function="fc-table-sorter" class="pae-table pae-table--margin tablesorter">
                    			<caption class="hiddenAccesible"><spring:message code="field.convocatoria.convocatoriasAbiertas"/></caption>
                    			<thead class="pae-table__header">
                      				<tr class="pae-table__row">
                        				<th data-col="col1" class="pae-table__cell-header"><span class="pae-table__txt-title"><spring:message code="field.cuerpoMin" /></span></th>
                        				<th data-col="col2" class="pae-table__cell-header"><span class="pae-table__txt-title"><spring:message code="field.centroMin" /></span></th>
                        				<th data-col="col3" class="pae-table__cell-header"><span class="pae-table__txt-title"><spring:message code="field.nivelAcceso" /></span></th>
                        				<th data-col="col4" class="pae-table__cell-header"><span class="pae-table__txt-title"><spring:message code="field.fechaTermina" /></span></th>
                        				<th data-col="col5" class="pae-table__cell-header"><span class="pae-table__txt-title">Docs.</span></th>
                        				<th data-col="col6" class="pae-table__cell-header"><span class="pae-table__txt-title"></span></th>
                      				</tr>
                    		</thead>
	                    	<tbody class="pae-table__body">
	                    	<%int i = 0; %>
	                    		<logic:iterate id="registro" name="convocatorias">
	                    			<tr class="pae-table__row" name="row">
				                        <td data-content="Cuerpo o escala" data-col="col1" data-function="fc-collapse-table" class="pae-table__cell-body"><span class="pae-table__txt-base pae-table__span-head"><bean:write name="registro" property="cuerpo" /></span></td>
				                        <td data-content="Centro gestor" data-col="col2" data-state="block" class="pae-table__cell-body"><span class="pae-table__txt-base pae-table__txt-base--light"><bean:write name="registro" property="ministerio" /></span><span class="pae-table__txt-base pae-table__txt-base--txt-td"><bean:write name="registro" property="centro" /></span></td>
				                        <td data-content="Acceso" data-col="col3" data-state="block" class="pae-table__cell-body"><span class="pae-table__txt-base"><bean:write name="registro" property="grupo" /></span><span class="pae-table__txt-base pae-table__txt-base--txt-td"><bean:write name="registro" property="formaAcceso" /></span></td>
				                        <td data-content="Fecha final" data-col="col4" data-state="block" class="pae-table__cell-body pae-table__cell-body--tcenter"><span class="pae-table__txt-base pae-table__txt-base--txt-td"><bean:write name="registro" property="fecha" /></span></td>
				                        <td data-col="col5" data-state="inline-block" class="pae-table__cell-body pae-table__cell-body--tcenter pae-table__cell-body--button pae-table__cell-body--popover">
				                          <div class="tab__rel"><a href="#" title="Descargar Documentos" data-function="fc-popover-download-form" data-event="fc-tooltip-doc" class="outline pae-table__link-button">
				                          	<span class="pae-table__icon pae-table__icon--file"></span></a>
				                            <logic:greaterThan name="registro" property = "numDocumentos" value = "0">
				                            <div data-function="fc-popover" class="pae-modal--small"><span class="popover-title"><spring:message code="field.incidencias.descargarDocumentos"/></span>
				                              <logic:iterate name = "registro" property = "documentos" id = "docs">
				                              <ul class="pae-modal--small__list">
				                              	<% String urlDescarga = request.getContextPath()+"/secure/descargarDocumento?ent=Convocatorias"; %>
				                                <li class="pae-modal--small__item-list">
				                                <a href="<%=urlDescarga %>&id=${docs.id}" class="pae-modal--small__link" title="field.DescargarDocumento">
				                                <bean:write name="docs" property = "nombre"/></a>
				                                </li>
				                              </ul>
				                              </logic:iterate>
				                            </div>
				                            </logic:greaterThan>
				                            <logic:equal name="registro" property = "numDocumentos" value = "0">
				                            <div data-function="fc-popover" class="pae-modal--small"><span class="popover-title"><spring:message code="field.incidencias.sinDocumentos"/></span>
				                            </div>
				                            </logic:equal>
				                          </div>
				                        </td>

				                        <logic:notEqual name="registro" property="estadoSolicitud" value = "Continuar">
					                        <td id="inscribirse<%=i%>" data-col="col6" data-state="inline-block" class="pae-table__cell-body pae-table__cell-body--tcenter pae-table__cell-body--button pae-table__cell-body--button-warning">
					                       	<logic:equal name="registro" property="presencial" value = "S">
					                        	<a href="#" id="tarjeta-<%=i%>" title="Inscribirse" data-function="fc-modal-call-sign" class="outline pae-table__link-button"><span class="pae-table__txt-base pae-table__txt-base--warning pae-table__txt-base--highlighted" ><spring:message code="field.convocatoria.subsanar"/></span></a>
					                        </logic:equal>
					                        <logic:equal name="registro" property="presencial" value = "N">
					                        	<a href="#" id="tarjeta-<%=i%>" title="Inscribirse" data-function="fc-modal-call-sign-nopre" class="outline pae-table__link-button"><span class="pae-table__txt-base pae-table__txt-base--warning pae-table__txt-base--highlighted" ><spring:message code="field.convocatoria.subsanar"/></span></a>
					                        </logic:equal>
					                        <div id="modal<%=i%>" data-function="fc-call-sub" class="pae-modal" name="modal">
					            				<div class="pae-modal__body pae-modal__body--table">
					            					<logic:notEqual name="usuActual" property="tipoPersona" value = "FH">
						              					<div class="pae-modal__col pae-modal__col--left">
						                					<div class="pae-modal__row-img">
						                  						<figure class="pae-modal__figure pae-modal__figure--first"><img src="<%=request.getContextPath()%>/img/icons/desktop.png" alt="Icono de una pantalla" class="pae-modal__figure-img"></figure>
						                					</div>
						                					<div class="pae-modal__row-content">
						                  						<div class="pae-box-buttons">
						                    						<button type="button" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--arrow-right outline" onclick ="llamadaSubsanar(<bean:write name="registro" property="id"/>,'C');"><spring:message code="field.convocatoria.online"/></button>
						                  						</div>
						                					</div>
						              					</div>
					              					</logic:notEqual>
					              					<logic:equal name="registro" property="presencial" value = "S">
					              					<div class="pae-modal__col pae-modal__col--right">
					                					<div class="pae-modal__row-img">
					                  						<figure class="pae-modal__figure pae-modal__figure--second"><img src="<%=request.getContextPath()%>/img/icons/print.png" alt="Icono de una impresora" class="pae-modal__figure-img"></figure>
					                					</div>
					                					<div class="pae-modal__row-content" >
						                  					<div class="pae-box-buttons">
					                    						<button type="button" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline" onclick="abrirVentanaDescarga('<bean:write name="registro" property="siglasCentroGestor"/>',<bean:write name="registro" property="id" />,'<bean:write name="registro" property="codigoCuerpo" />')"><spring:message code="field.convocatoria.presencial"/></button>
					                  						</div>
					                					</div>
						              				</div>
						              				</logic:equal>
						            			</div>
						          				</div>
					          				</td>
				          				</logic:notEqual>
				                      	<logic:equal name="registro" property="estadoSolicitud" value = "Continuar">
				                        	<td data-col="col6" data-state="inline-block" class="pae-table__cell-body pae-table__cell-body--tcenter pae-table__cell-body--button pae-table__cell-body--button-warning">
				                        		<a href="#" title="Continuar" onclick='llamadaInscribirseContinuar(<bean:write name="registro" property="id"/>);' class="outline pae-table__link-button"><span class="pae-table__txt-base pae-table__txt-base--warning pae-table__txt-base--highlighted"><spring:message code="field.incidencias.botonContinuar"/></span></a>
				                        	</td>
			                        	</logic:equal>
									</tr>
									<%i++;%>
                      			</logic:iterate>
                    		</tbody>
                    	</table>
                    		<div id="view-more" class="view-more">
                    			<a href="javascript: verMasConvocatorias()" id="view-more-link" value="verTodoSub" title="Ver más" class="view-more__link"><spring:message code="field.convocatoria.verMas"/></a>
                    		</div>
                    	<script type="text/javascript">
                    	if(<%=request.getAttribute("numTotalConvocatoriasSub")%> <= <%=Constantes.NUMERO_REGISTROS_TOTALES_MOSTRAR %> || <%=request.getAttribute("verTodoSub")%> == 0) {
                    		$("#view-more").remove();
                    	}
                    	
                    	$(document).ready(function() {
                        	if(<%=request.getAttribute("mensajeNoRegistrado")%>) {
                        		$("#noRegistro").click();
                        	}
                    	});
                    	</script>
                    </div>
                    </logic:greaterThan>
                    <logic:lessThan name="numResultados" value="1">
                    	<div class="pae-layout__item pae-u-1/1 pae-u-1/1-lap pae-u-1/1-palm">
							<div class="pae-alertbox pae-alertbox--warning">
              					<p class="pae-alertbox__text"><spring:message code="field.incidencias.sinConvocatorias"/></p>
          					</div>
          				</div>
					</logic:lessThan>
                    </logic:present>
                    </logic:notPresent>
                    
      				<%-- Usuario sin solicitud - INI --%>
                    <div id="noRegistrado" data-function="fc-call-no-reg" class="pae-modal" name="modal">
           				<div class="pae-modal__body pae-modal__body--table">
          					<div class="pae-modal__col pae-modal__col--left">
            					<div class="pae-modal__row-content">
              						<p class="pae-modal__text pae-modal__text--special"><spring:message code="field.convocatoria.mensajeNoRegistrado"/>
              						</p>
              						<div class="pae-modal__footer pae-modal__footer--left pae-modal__footer--no-border pae-modal__footer--mobile">
                						<div class="pae-box-buttons">
                  							<button type="button" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--arrow-right outline" onclick ="$('.ui-dialog-titlebar-close').click();"><spring:message code="field.convocatoria.aceptar"/></button>
                						</div>
              						</div>
            					</div>
          					</div>
            			</div>
            		</div>
            		<a href="#" id="noRegistro" data-function="fc-modal-call-no-reg" class="outline pae-table__link-button">ssdsd</a>
            		<%-- Usuario sin solicitud - FIN --%>	  
            		   
                    </form:form>
              	</div>
            </section>
			</div>
		</div>
	</div>
</div>
            
	
		
	<script type="text/javascript">
	    

	    
	    function openWindowDocumentos(id) {
	    	var navegador = navigator.appName;
	    	var extract = new Object();
	    	ventanaPopup = window.open("<%=request.getContextPath()%>/secure/documentosConvocatoria?id="+id, 'miventana', 'width=500, height=400,toolbar=no,directories=no,status=no,linemenubar=no,scrollbars=yes,resizable=no ,modal=yes');
	    }

	    function abrirVentanaDescarga(siglasCentroGestor,id,cuerpo){
	     	ventanaPopup = window.open("<%=request.getContextPath()%>/secure/descargarFormulario790?&subsanar=true&id="+id);	
	     	ventanaPopup.focus();	
	    }

	    function busqueda(){
	    	document.getElementById("submit").value="Buscar";
	    	document.getElementById("cambios").value="Correcto";
	    }

	    function verMasConvocatorias(){
	    	 if(document.getElementById("search").value != "") {
	     		document.getElementById("search_Sub").value = document.getElementById("search").value;
	     		document.getElementById("cambios_Sub").value="Correcto";
	     		document.getElementById("verTodoSub").value = true;
	     		document.getElementById("ConvocatoriasEnlaceSub").submit();
	     		return false;
	     	} else {
	     		document.getElementById("verTodoSub").value = true;
		    	document.getElementById("ConvocatoriasEnlaceSub").submit();
		    	return false;
	     	}
	    }
	    
	    function cambiarMayusculas(ctrl){
			ctrl.value=ctrl.value.toUpperCase();
		}
	    function ocultar(numero) {
	    	for(var i = 0; i < document.getElementsByName("row").length; i++) {
	    		if(i!=numero)
	    			$("#modal"+i).removeAttr("data-function");
	    	}
	   	 }
	    function mostrar() {
	    	for(var i = 0; i < document.getElementsByName("row").length; i++) {
	    			$("#modal"+i).attr("data-function", "fc-call-sub");
	    	}
	   	 }
 </script> 


<form action="<%=request.getContextPath()%>/secure/certificadoSpring?llamada=Inscribirse&form=L" method=post id="formulario" > 
	<input type="hidden" id="certificado" name="certificado" value="certificado"> 
	<input type="hidden" id="id" name="id" value="id"> 
</form>

</body>
