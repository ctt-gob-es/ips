<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.mpr.es/ipsg/bean-tags" prefix="bean"%>
<%@ taglib uri="http://www.mpr.es/ipsg/html-tags" prefix="html"%>
<%@ taglib uri="http://www.mpr.es/ipsg/logic-tags" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page import="es.map.ipsc.Constantes"%>
<%@ page import="es.map.ipsc.bean.*"%>


<script type="text/javascript" language="javascript"
	src="<%=request.getContextPath()%>/javascript/constantes.js"></script>
<script type="text/javascript">
window.location.href ='#inscripciones';
function abrirJustificante(id){
	ventanaPopup = window.open("<%=request.getContextPath()%>/secure/descargarDocumentoSolicitud?ent=Solicitudes&id="+id);
	ventanaPopup.focus();
}

function eliminarSolicitud(variable){
	if(confirm("<spring:message code='field.mensajeEliminar'/>")){ 
		document.getElementById("idSol").value = variable;
		document.getElementById("eliminarSol").action ="<%=request.getContextPath()%>/secure/ActualizarEstadoSolicitud?llamada=<%=request.getParameter("llamada")%>&registro="+variable+"#inscripciones";
		document.getElementById("eliminarSol").submit(); 
		return true;
	}else{
		return false;
	}	
}

function busqueda(){
	document.getElementById("submit").value="Buscar";
	document.getElementById("cambios").value="Correcto";
}

function recuperarSolicitud(variable){
	
	document.getElementById("idSol").value = variable;
	document.getElementById("formularioSol").submit(); 
}

function Continuar(variable){	
	llamadaInscribirseContinuar(variable);
}

function Modificar(variable, idSolicitud){	
	llamadaInscribirseModificar(variable, idSolicitud);
}

function subsanar(variable, idSolicitud){	
	llamadaSubsanarModificar(variable, idSolicitud);
}

</script>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title></title>
	</head>
<body>
	
	
	
	
	
	<jsp:useBean id="solicitudes" scope="request" class="java.util.ArrayList" />
	<% 
		int i = 0; 
	%>
	<logic:present name="solicitudes" scope="request">
		
	<div class="pae-body pae-body--without-padding-mobile">	
		<logic:equal name="usuActual" property="tipoPersona" value="FH">
			<div class="pae-wrapper">
      			<div class="pae-layout">
        			<div class="pae-layout__item pae-u-12/12 pae-u-12/12-lap pae-u-1/1-palm">
								
						<h2 class="pae-title">
							<spring:message code="field.solicitud.inscripcionOnline.buscadorNif"/>
						</h2>
						
						<logic:notEmpty name="solicitudes" scope="request">
						 <div class="pae-alertbox pae-alertbox__2 pae-alertbox--success">
		            		<div class="pae-alertbox__body pae-alertbox__body__2 pae-alertbox__body__2--mobile">
		              			<p class="pae-alertbox__text">${numSolicitudes} <spring:message code="field.incidencias.busquedaCorrecta" /> </p>
		            		</div>
		            	</div>
		            	</logic:notEmpty>
		            	
		            	<form action="<%=request.getContextPath()%>/secure/buscarSolicitudes" method="POST" id="buscadorNif" >
							<table id="wrapperForm" style="margin-left: 15px ;" >
								<tr>
									<td>
										<span style="font-size: 110%;">NIF del ciudadano: </span>
									</td>
									<td style="width: 26%;" >
										<input type="text" id="buscadorNifInput" name="buscadorNifInput" size="9" maxlength="9" style="text-transform: uppercase;" class="pae-form__input"  />
									</td>
									<td>
										<div class="pae-box-buttons" >
		            						<input type="submit" value="Buscar" class="pae-buttons pae-buttons__2 pae-buttons--default pae-buttons--medium" style="margin-left: 30px;margin-top:-10px"/>  
						       			 </div>
									</td>
								</tr>
							</table>
						</form>
											
					</div>
				</div>
			</div>
        </logic:equal>
	
		<logic:iterate id="registro" name="solicitudes">
   	<div class="pae-wrapper">
      <div class="pae-layout">
        <div class="pae-layout__item pae-u-12/12 pae-u-12/12-lap pae-u-1/1-palm">
        	
        
        
          <%
			BuscarSolicitudesBean solicitud = (BuscarSolicitudesBean) solicitudes.get(i);
			i++;
			
			if (solicitud.getIdEstadoInscripcion().intValue() == 3 || solicitud.isSubsanar()) {
				String enlaceModificarSolicitud = "javascript: Modificar("+solicitud.getIdConvocatoria()+","+solicitud.getIdSolicitud()+")";
				String enlaceSubsanarSolicitud = "javascript: subsanar("+solicitud.getIdConvocatoria()+","+solicitud.getIdSolicitud()+")";
			%>
          <div class="pae-alertbox pae-alertbox__2 pae-alertbox--success">
            <div class="pae-alertbox__body pae-alertbox__body__2 pae-alertbox__body__2--mobile">
              <p class="pae-alertbox__text"><spring:message code="field.incidencias.inscripcionCorrecta" /></p>
            </div>
            <div class="pae-alertbox__footer pae-alertbox__footer__2 pae-alertbox__footer--right">
             	<h1 class="pae-title pae-title__2"><spring:message code="field.incidencias.inscripcionOnline" /><span class="pae-title pae-title__2 pae-title--highlighted pae-title--highlighted__2"> <bean:write name="registro" property="numJustificante" /></span></h1>
            </div>
          </div>
          <div class="pae-box-details">
            <h2 class="pae-box-details__title pae-box-details__title--default"><spring:message code="field.incidencias.inscrito" /></h2>
            <div class="pae-box-list">
              <ul class="pae-list-details">
                <li class="pae-list-details__item-head"><span class="pae-list-details__item-text pae-list-details__item-text--highlighted"><spring:message code="field.cuerpoMin"/></span></li>
                <li class="pae-list-details__item-body--last"><span class="pae-list-details__item-text"><bean:write name="registro" property="cuerpoEscala" /></span></li>
              </ul>
              <ul class="pae-list-details">
                <li class="pae-list-details__item-head"><span class="pae-list-details__item-text pae-list-details__item-text--highlighted"><spring:message code="field.centroMin"/></span></li>
                <li class="pae-list-details__item-body"><span class="pae-list-details__item-text"><bean:write name="registro" property="ministerio" /></span></li>
                <li class="pae-list-details__item-body--last"><span class="pae-list-details__item-text"><bean:write name="registro" property="centroGestor" /></span></li>
              </ul>
              <ul class="pae-list-details">
                <li class="pae-list-details__item-head"><span class="pae-list-details__item-text pae-list-details__item-text--highlighted"><spring:message code="field.nivelAcceso"/></span></li>
                <li class="pae-list-details__item-body"><span class="pae-list-details__item-text"><spring:message code="field.nivel"/> <bean:write name="registro" property="grupo" /></span></li>
                <li class="pae-list-details__item-body--last"><span class="pae-list-details__item-text"><bean:write name="registro" property="formaAcceso" /></span></li>
              </ul>
            </div>
          </div>
          <div class="pae-box-links" data-state="inline-block">
            <div class="pae-box-links__medium-box pae-box-links__medium-box__2 pae-box-links__medium-box__3">
            	<a href="#" title="Ver documentos adjuntos" data-function="fc-popover-download-form" data-event="fc-tooltip-doc" class="pae-box-links__link pae-box-links__link--clip">
            		<span class="pae-form__icon-link-text"><spring:message code="field.incidencias.verDocumentosAdjuntos" /></span></a>
            	<logic:greaterThan name="registro" property = "numDocumentos" value = "0">
                	<div data-function="fc-popover" class="pae-modal--small pae-modal--small__2"><span class="popover-title popover-title__2"><spring:message code="field.incidencias.descargarDocumentos" /></span>
                    	<logic:iterate name="registro" property = "documentos" id = "docs">
                        	<ul class="pae-modal--small__list">
                            	<% String urlDescarga = request.getContextPath()+"/secure/descargarDocumentoSolicitud?ent=Solicitudes"; %>
                            	<li class="pae-modal--small__item-list">
                            	<a href="<%=urlDescarga %>&id=${docs.id}" target="_blank" class="pae-modal__list pae-modal--small__link" title="field.DescargarDocumento">
                            	<bean:write name="docs" property = "nombre"/>
                            	</a>
                            	</li>
                            </ul>
                        </logic:iterate>
                    </div>
               </logic:greaterThan>
               <logic:equal name="registro" property="numDocumentos" value="0">
               		<div data-function="fc-popover" class="pae-modal--small pae-modal--small__2"><span class="popover-title popover-title__2"><spring:message code="field.incidencias.sinDocumentos" /></span></div>
               </logic:equal>
       		</div>
            <div class="pae-box-links__medium-box" >
            	<div class="pae-box-buttons">
            		<logic:iterate name="registro" property="justificante" id="just">
            		<logic:greaterThan name="registro" property="justificante" value="0">
						<button type="button" class="pae-buttons pae-buttons--default pae-buttons--medium pae-buttons--download pae-buttons--download-mobile outline"
							 onclick ="javascript: abrirJustificante(<bean:write name="just" property="id"/>);"><spring:message code="field.incidencias.justificanteRegistro"/></button>
						<logic:equal name="registro" property="modificar" value="true">
							<button type="submit" class="pae-buttons pae-buttons__2 pae-buttons--default pae-buttons--medium pae-margin_left_2" id="tarjeta-<%=i%>" data-function="fc-modal-call-modificar" >Modificar</button>
<%-- 							onclick="<%=enlaceModificarSolicitud%>";" --%>
							<div id="modal<%=i%>" data-function="fc-call-modificar" class="pae-modal" name="modal">
		            			<div class="pae-modal__body pae-modal__body--table">
		            				<!-- inscripcion online f.h -->
		              				<div class="pae-modal__col pae-modal__col--left">
		                				
		                				<div class="pae-modal__text pae-modal__text--special">
		                  					<p class="pae-modal__row-content">
		                  						<spring:message code="field.solicitud.mensaje.mod"/>
		                  						<spring:message code="field.solicitud.mensaje"/>
		                  					</p>
		                  					<div class="pae-modal__footer pae-modal__footer--left pae-modal__footer--no-border pae-modal__footer--mobile">
                								<div class="pae-box-buttons">
                  									<button type="button" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--arrow-right outline" onclick="<%=enlaceModificarSolicitud%>";">Continuar</button>
                								</div>
              								</div>
		                  				</div>
		                  					<div class="pae-modal__footer pae-modal__footer--left pae-modal__footer--no-border pae-modal__footer--padding-bottom">
	                    						<div class="pae-box-buttons">
	                      							<button type="button" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--arrow-right outline" onclick="<%=enlaceModificarSolicitud%>";">Continuar</button>
	                    						</div>
	                  						</div>
		                  				
		                				
		              				</div>
		              				
			            		</div>
		          			</div>
						</logic:equal>
						
						<%-- Subsanar solicitud INI --%>
						<logic:equal name="registro" property="subsanar" value="true">
							<button type="submit" class="pae-buttons pae-buttons__2 pae-buttons--default pae-buttons--medium pae-margin_left_2" id="tarjeta-<%=i%>" data-function="fc-modal-call-subsanar" >Subsanar</button>
							<div id="modal<%=i%>" data-function="fc-call-subsanar" class="pae-modal" name="modal">
		            			<div class="pae-modal__body pae-modal__body--table">
		            				<!-- inscripcion online f.h -->
		              				<div class="pae-modal__col pae-modal__col--left">
		                				<div class="pae-modal__text pae-modal__text--special">
		                  					<p class="pae-modal__row-content">
		                  						<spring:message code="field.solicitud.mensaje.sub"/>
		                  						<spring:message code="field.solicitud.mensaje"/>
		                  					</p>
		                  					<div class="pae-modal__footer pae-modal__footer--left pae-modal__footer--no-border pae-modal__footer--mobile">
                								<div class="pae-box-buttons">
                  									<button type="button" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--arrow-right outline" onclick="<%=enlaceSubsanarSolicitud%>";">Continuar</button>
                								</div>
              								</div>
		                  				</div>
	                  					<div class="pae-modal__footer pae-modal__footer--left pae-modal__footer--no-border pae-modal__footer--padding-bottom">
                    						<div class="pae-box-buttons">
                      							<button type="button" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--arrow-right outline" onclick="<%=enlaceSubsanarSolicitud%>";">Continuar</button>
                    						</div>
                  						</div>
		              				</div>
			            		</div>
		          			</div>
						</logic:equal>
						<%-- Subsanar solicitud FIN --%>						
					</logic:greaterThan>
					</logic:iterate>
              	</div>
            </div>
          </div>
          <%
 				} else {
 					String enlaceReanudarSolicitud = "javascript: Continuar("+solicitud.getIdConvocatoria()+")";
 					String actualizarEstadoSolicitud ="javascript: eliminarSolicitud("+solicitud.getIdSolicitud()+")";
 			%>
          <div class="pae-alertbox pae-alertbox__2 pae-alertbox--warning">
            <div class="pae-alertbox__body pae-alertbox__body__2 pae-alertbox__body__2--mobile">
              <p class="pae-alertbox__text"><bean:write name="registro" property="estadoInscripcion" /></p>
            </div>
            <div class="pae-alertbox__footer pae-alertbox__footer__2 pae-alertbox__footer--right">
             	<h1 class="pae-title pae-title__2"><spring:message code="field.incidencias.inscripcionOnline" /><span class="pae-title pae-title__2 pae-title--highlighted pae-title--highlighted__2"> <bean:write name="registro" property="numJustificante" /></span></h1>
            </div>
          </div>
          <div class="pae-box-details">
            <h2 class="pae-box-details__title"><spring:message code="field.incidencias.realizandoInscripcion"/></h2>
            <div class="pae-box-list">
              <ul class="pae-list-details">
                <li class="pae-list-details__item-head"><span class="pae-list-details__item-text pae-list-details__item-text--highlighted"><spring:message code="field.cuerpoMin"/></span></li>
                <li class="pae-list-details__item-body--last"><span class="pae-list-details__item-text"><bean:write name="registro" property="cuerpoEscala" /></span></li>
              </ul>
              <ul class="pae-list-details">
                <li class="pae-list-details__item-head"><span class="pae-list-details__item-text pae-list-details__item-text--highlighted"><spring:message code="field.centroMin"/></span></li>
				<li class="pae-list-details__item-body"><span class="pae-list-details__item-text"><bean:write name="registro" property="ministerio" /></span></li>
                <li class="pae-list-details__item-body--last"><span class="pae-list-details__item-text"><bean:write name="registro" property="centroGestor" /></span></li>
              </ul>
              <ul class="pae-list-details">
                <li class="pae-list-details__item-body"><span class="pae-list-details__item-text pae-list-details__item-text--highlighted"><spring:message code="field.nivelAcceso"/></span></li>
                <li class="pae-list-details__item-head"><span class="pae-list-details__item-text"><spring:message code="field.nivel"/><bean:write name="registro" property="grupo" /></span></li>
                <li class="pae-list-details__item-body--last"><span class="pae-list-details__item-text"><bean:write name="registro" property="formaAcceso" /></span></li>
              </ul>
            </div>
          </div>
            <div class="pae-box-links" data-state="inline-block">
            <div class="pae-box-links__medium-box pae-box-links__medium-box__2 pae-box-links__medium-box__3">
            	<a href="#" title="Ver documentos adjuntos" data-function="fc-popover-download-form" data-event="fc-tooltip-doc" class="pae-box-links__link pae-box-links__link--clip">
            		<span class="pae-form__icon-link-text"><spring:message code="field.incidencias.verDocumentosAdjuntos" /></span></a>
            	<logic:greaterThan name="registro" property = "numDocumentos" value = "0">
                	<div data-function="fc-popover" class="pae-modal--small pae-modal--small__2"><span class="popover-title popover-title__2"><spring:message code="field.incidencias.descargarDocumentos" /></span>
                    	<logic:iterate name="registro" property = "documentos" id = "docs">
                        	<ul class="pae-modal--small__list">
                            	<% String urlDescarga = request.getContextPath()+"/secure/descargarDocumentoSolicitud?ent=Solicitudes"; %>
                            	<li class="pae-modal--small__item-list">
                            	<a href="<%=urlDescarga %>&id=${docs.id}" target="_blank" class="pae-modal__list pae-modal--small__link" title="field.DescargarDocumento">
                            	<bean:write name="docs" property = "nombre"/>
                            	</a>
                            	</li>
                            </ul>
                        </logic:iterate>
                    </div>
               </logic:greaterThan>
               <logic:equal name="registro" property="numDocumentos" value="0">
               		<div data-function="fc-popover" class="pae-modal--small pae-modal--small__2"><span class="popover-title popover-title__2"><spring:message code="field.incidencias.sinDocumentos" /></span></div>
               </logic:equal>
       		</div>
            <div class="pae-box-links__medium-box">
            	<div class="pae-box-buttons">
                	<button type="button" class="pae-buttons pae-buttons__2 pae-buttons--default pae-buttons--medium pae-separation" onclick="<%=enlaceReanudarSolicitud%>"><spring:message code="field.incidencias.botonContinuar" /></button>
	                <logic:equal name="registro" property="eliminar" value="true">
	                	<button type="submit" class="pae-buttons pae-buttons__2 pae-buttons--default pae-buttons--medium" onclick="<%=actualizarEstadoSolicitud%>"><spring:message code="field.incidencias.botonEliminar" /></button> 
	                </logic:equal>
              	</div>
            </div>
          </div>
          <% } %>
        </div>
      </div>
    </div>
  </logic:iterate>
  <logic:empty name="solicitudes" scope="request">
  <div class="pae-wrapper">
  <div class="pae-layout">
   	<div class="pae-layout__item pae-u-12/12 pae-u-12/12-lap pae-u-1/1-palm">
  		<div class="pae-box-details">
            <h2 class="pae-box-details__title pae-box-details__title--default"><spring:message code="field.solicitud.sinSolicitudes"/></h2>
    	</div>
    </div>
   </div>
   </div>
  </logic:empty>
  </div>
  </logic:present>
   <form action="#" name="eliminarSol"  method="POST" id="eliminarSol" >
		<input type="hidden" id="idSol" name="id" value="id"> 
	</form>
</body>
