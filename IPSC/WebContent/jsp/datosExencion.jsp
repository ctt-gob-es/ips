			<div id="boxExento" data-function="accordion-box" class="pae-box" id="boxExento">
              <div class="pae-box__header">
                <h3 class="pae-box__header--title"><spring:message code="field.pagoSolicitud.exencionTitulo" /></h3>
              </div>
              <div class="pae-box__body">
                <fieldset>
                  <legend class="pae-form__legend-text"><spring:message code="field.pagoSolicitud.subtitulo" /></span></legend>
                  <div class="pae-layout">
                    <div class="pae-layout__item pae-u-4/12 pae-u-4/12-lap pae-u-1/1-palm">
                      <div class="pae-form__cell">
                        <ul class="pae-form__list">
                        <jsp:useBean id="motivosCompletos" scope="request" class="java.util.ArrayList" />
							<%int i = 1;%>
							<logic:iterate id="motivosExencion" name="motivosCompletos">
	                          <li class="pae-form__list-item">
	                            <input type="checkbox" name="checkbox-exemption" id="checkbox-exemption-<%=i%>" value="<bean:write name="motivosExencion" property = "id"/>" 
	                            	onchange="actualizarImporte(this.value);" data-function="checkbox-custom-input" class="pae-form__custom-check" >
	                            <label for="checkbox-exemption-<%=i%>" id="<bean:write name="motivosExencion" property = "descripcion"/>" data-function="checkbox-custom" 
	                            title="<spring:message code="field.solicitud.mostrarInfo"/>
	                            	<bean:write name="motivosExencion" property = "descripcion"/> " class="pae-form__custom-check-label pae-form__custom-check-label--option unchecked">
	                            	<bean:write name="motivosExencion" property = "descripcion"/>
	                            </label>
	                          </li>
	                          <%i++; %>
	                          </logic:iterate>
                          
                          	<jsp:useBean id="motivos" scope="request" class="java.util.ArrayList" />
							<logic:iterate id="motivosExencionIncompletos" name="motivos">
                          	<li class="pae-form__list-item">
	                            <input type="checkbox" name="checkbox-exemption" id="checkbox-exemption-<%=i%>" value="<bean:write name="motivosExencionIncompletos" property = "id"/>" 
	                            	onchange="actualizarImporte(this.value);" data-function="checkbox-custom-input" class="pae-form__custom-check" >
	                            <label for="checkbox-exemption-<%=i%>" id="<bean:write name="motivosExencionIncompletos" property = "descripcion"/>" data-function="checkbox-custom" title="<spring:message code="field.solicitud.mostrarInfo"/>
	                           		<bean:write name="motivosExencionIncompletos" property = "descripcion"/>" class="pae-form__custom-check-label pae-form__custom-check-label--option unchecked">
	                            	<bean:write name="motivosExencionIncompletos" property = "descripcion"/>
	                            </label>
							</li>
                          
                          <%i++; %>
                          
                          </logic:iterate>
                        </ul>
                        <input type="hidden" id="motivosTipificado"	value="<%=motivoTipificado%>">
                      </div>
                    </div>
                    <div class="pae-layout__item pae-u-8/12 pae-u-8/12-lap pae-u-1/1-palm">
                      <div class="pae-form__advisor-tooltip pae-form__advisor-tooltip--dynamic">
                      	<span class="hiddenAccesible">
                      		<spring:message code="field.convocatoria.mensajeAlerta"/>
                      	</span>
                      </div>
                      <div class="pae-form__advisor-contain hiddenAccesible">
                      	<logic:iterate id="motivosExencion" name="motivosCompletos">
                      		<div class="pae-form__advisor-checkbox pae-form__advisor-checkbox--<bean:write name="motivosExencion" property = "id"/>">
	                        	<p class="pae-form__advisor-text">
	                        		<spring:message code="field.convocatoria.mensajeExencion" />
	                        	</p>
	                          	<ul class="pae-form__advisor-list">
	                          		<bean:write name="motivosExencion" property="textoExplicativo" filter="false"/>
	                          	</ul>
                        	</div>                       
                    	</logic:iterate>
                      
                    	<logic:iterate id="motivosExencionIncompletos" name="motivos">
                      		<div class="pae-form__advisor-checkbox pae-form__advisor-checkbox--<bean:write name="motivosExencionIncompletos" property="id"/>">
                        		<p class="pae-form__advisor-text">
                        			<spring:message code="field.convocatoria.mensajeExencion" />
                        		</p>
                          		<ul class="pae-form__advisor-list">
                            		<bean:write name="motivosExencionIncompletos" property="textoExplicativo" filter="false"/>
                          		</ul>
                       		</div>                       
                    	</logic:iterate>  
                      </div>
                    </div>
                  </div>
                </fieldset>
                <fieldset>
               	<div id="adjDocumentos" class="pae-layout">
					<div class="pae-layout__item pae-u-6/12 pae-u-6/12-lap pae-u-1/1-palm">
                     		<div class="pae-form__cell">
							<logic:present name="registroSolicitud" scope="request">
							<logic:equal value="SI" name="registroSolicitud" property="aniadirDocumentos">
							<logic:present name="documentos" scope="request">
							<bean:size id="numResultados" name="documentos" scope="request"/>
							<logic:greaterThan name="numResultados" value="0">
								<legend id="DocumentosAdjuntados" class="pae-form__legend-text"><spring:message code="field.solicitud.documentosAdjuntados"/></legend>
									<div class="pae-form-files pae-form-files--active hiddenAccesible" id="tablaDocumentos4">
										<div class="fc-pae-form__document--files pae-form__document">
											<span class="pae-form__doc-list-title"><spring:message code="field.registro.documentos.adjuntoSolicitud"/></span>
											<logic:iterate id="registro" name="documentos" >
												<% String urlDescarga = request.getContextPath()+"/secure/descargarDocumento?ent=Solicitudes";%>
												<logic:equal value="ANEXO DE SOLICITUD" name="registro" property="dsTipoDocumento">
												<script>
												$("#tablaDocumentos4").removeClass("hiddenAccesible");
												</script>
												<input type="hidden" id="documentoEncontrado4" value="1"/>
												<ul class="fc-load-files pae-form__list--document" id="table${registro.id}">
													<li class="pae-form__list-item--document">
														<a href="<%=urlDescarga %>&id=${registro.id}">
															<span class="pae-form__list-item-text">
															<bean:write name="registro" property="nombre" />
														</a>
														</span>
														<span class="pae-form__list-item-text pae-form__list-item-text--size">(${registro.tamano} KB)</span>
														<button type="button" class="pae-form__input-button" onclick="eliminarDocumentoSubido(${registro.id},4)" name="documentos4">
															<span class="hiddenAccesible">
																<spring:message code="field.solicitud.botonEliminar"/>
															</span>
														</button>
													</li>
												</ul>
												</logic:equal>
											</logic:iterate>
											</div>
										</div>
										<div class="pae-form-files pae-form-files--active hiddenAccesible" id="tablaDocumentos5">
											<div class="fc-pae-form__document--files pae-form__document">
												<span class="pae-form__doc-list-title"><spring:message code="field.registro.documentos.justificanteDiscapacidad"/></span>
												<logic:iterate id="registro" name="documentos" >
													<% String urlDescarga = request.getContextPath()+"/secure/descargarDocumento?ent=Solicitudes";%>
													<logic:equal value="JUSTIFICANTE DE DISCAPACIDAD" name="registro" property="dsTipoDocumento">
													<script>
													$("#tablaDocumentos5").removeClass("hiddenAccesible");
													</script>
													<input type="hidden" id="documentoEncontrado5" value="1"/>
													<ul class="fc-load-files pae-form__list--document" id="table${registro.id}">
														<li class="pae-form__list-item--document">
															<a href="<%=urlDescarga %>&id=${registro.id}">
															<span class="pae-form__list-item-text"><bean:write name="registro" property="nombre" />
															</a></span>
															<span class="pae-form__list-item-text pae-form__list-item-text--size">(${registro.tamano} KB)</span>
															<button type="button" class="pae-form__input-button" onclick="eliminarDocumentoSubido(${registro.id},5)" name="documentos5">
															<span class="hiddenAccesible"><spring:message code="field.solicitud.botonEliminar"/></span></button></li>
													</ul>
													</logic:equal>
												</logic:iterate>
											</div>
										</div>
										<div class="pae-form-files pae-form-files--active hiddenAccesible" id="tablaDocumentos6">
											<div class="fc-pae-form__document--files pae-form__document">
												<span class="pae-form__doc-list-title"><spring:message code="field.registro.documentos.justificanteExencion2"/></span>
												<logic:iterate id="registro" name="documentos" >
													<% String urlDescarga = request.getContextPath()+"/secure/descargarDocumento?ent=Solicitudes";%>
													<logic:equal value="JUSTIFICANTE DE REDUCCION DE TASA" name="registro" property="dsTipoDocumento">
													<script>
													$("#tablaDocumentos6").removeClass("hiddenAccesible");
													</script>
													<input type="hidden" id="documentoEncontrado6" value="1"/>
													<ul class="fc-load-files pae-form__list--document" id="table${registro.id}">
														<li class="pae-form__list-item--document">
															<a href="<%=urlDescarga %>&id=${registro.id}">
															<span class="pae-form__list-item-text"><bean:write name="registro" property="nombre" />
															</a></span>
															<span class="pae-form__list-item-text pae-form__list-item-text--size">(${registro.tamano} KB)</span>
															<button type="button" class="pae-form__input-button" onclick="eliminarDocumentoSubido(${registro.id},6)" name="documentos6" >
															<span class="hiddenAccesible"><spring:message code="field.solicitud.botonEliminar"/></span></button></li>
													</ul>
													</logic:equal>
												</logic:iterate>
											</div>
										</div>
						</logic:greaterThan>
						</logic:present>
					</logic:equal>
				</logic:present>
                      <legend class="pae-form__legend-text"><spring:message code="field.convocatoria.documentos"/></legend>
                        <div class="fc-form-files pae-form-files"></div><a href="#" title="Adjuntar documentos" data-function="fc-add-document" class="pae-form__icon-link--add-nomargin">
                        	<span class="pae-form__icon-link-text"><spring:message code="field.convocatoria.adjuntarDocumento"/></span></a>
                      </div>
                    </div>
                    <div class="pae-layout__item pae-u-6/12 pae-u-6/12-lap pae-u-1/1-palm pae-hidden pae-margin-top" id="comunidadesFNExento">
                    	<input type="hidden" id="obl_CCAA" value="true">
                    	<label for="state" id="labelComunidadFNExento" class="pae-form__label"><strong class="pae-form__advisor-text--bold"><spring:message code="field.convocatoria.comunidadFamNumGeneral"/></strong></label>
                        <form:select path="idComunidadFNExento" class="pae-form__input" id="idComunidadFNExento" onchange="tituloRequerido()">
                           	<option value="0" class="pae-form__option"><spring:message code="field.solicitud.selecciona"/></option>
                      	 	<form:options items="${listcomunidadesFN}" itemLabel="descripcion" itemValue="idComunidad" style="text-transform: uppercase;"/>	
                      	 </form:select>
                      	 <div id="tituloFN" class="pae-hidden"/>
                      	 <label for="tituloFNExento" class="pae-form__label pae-form__span-label" id="labelTituloFN"><strong class="pae-form__advisor-text--bold"><spring:message code="field.solicitud.numeroTitulo"/>*</strong></label>
                      	 <input type="text" id="tituloFNExento" name="tituloFNExento" class="pae-form__input" value="" maxlength="20"/>
                      	  <span id="tituloFNExentoError" class="pae-form__text-error__2 hiddenAccesible">
								<spring:message code="field.solicitud.campoObligatorio" />
						  </span>
                    </div>
                  </div>
                  <%@ include file="/jsp/datosDiscapacidad.jsp" %>
                </fieldset>
              </div>
            </div>

<script type="text/javascript">
			
	function mascara() {
	   MaskedInput({
	      elm: document.getElementById('tituloFNExento'), // select only by id
	      format: '__/____/__',
	      separator: '-'
	   });
	};

	//Javascript para la mascara de titulo de familia numerosa
	// masked_input_1.4-min.js
	(function(a){a.MaskedInput=function(f){if(!f||!f.elm||!f.format){return null}if(!(this instanceof a.MaskedInput)){return new a.MaskedInput(f)}var o=this,d=f.elm,s=f.format,i=f.allowed||"0123456789",h=f.allowedfx||function(){return true},p=f.separator||"/:-",n=f.typeon||"_YMDhms",c=f.onbadkey||function(){},q=f.onfilled||function(){},w=f.badkeywait||0,A=f.hasOwnProperty("preserve")?!!f.preserve:true,l=true,y=false,t=s,j=(function(){if(window.addEventListener){return function(E,C,D,B){E.addEventListener(C,D,(B===undefined)?false:B)}}if(window.attachEvent){return function(D,B,C){D.attachEvent("on"+B,C)}}return function(D,B,C){D["on"+B]=C}}()),u=function(){for(var B=d.value.length-1;B>=0;B--){for(var D=0,C=n.length;D<C;D++){if(d.value[B]===n[D]){return false}}}return true},x=function(C){try{C.focus();if(C.selectionStart>=0){return C.selectionStart}if(document.selection){var B=document.selection.createRange();return -B.moveStart("character",-C.value.length)}return -1}catch(D){return -1}},b=function(C,E){try{if(C.selectionStart){C.focus();C.setSelectionRange(E,E)}else{if(C.createTextRange){var B=C.createTextRange();B.move("character",E);B.select()}}}catch(D){return false}return true},m=function(D){D=D||window.event;var C="",E=D.which,B=D.type;if(E===undefined||E===null){E=D.keyCode}if(E===undefined||E===null){return""}switch(E){case 8:C="bksp";break;case 46:C=(B==="keydown")?"del":".";break;case 16:C="shift";break;case 0:case 9:case 13:C="etc";break;case 37:case 38:case 39:case 40:C=(!D.shiftKey&&(D.charCode!==39&&D.charCode!==undefined))?"etc":String.fromCharCode(E);break;default:C=String.fromCharCode(E);break}return C},v=function(B,C){if(B.preventDefault){B.preventDefault()}B.returnValue=C||false},k=function(B){var D=x(d),F=d.value,E="",C=true;switch(C){case (i.indexOf(B)!==-1):D=D+1;if(D>s.length){return false}while(p.indexOf(F.charAt(D-1))!==-1&&D<=s.length){D=D+1}if(!h(B,D)){c(B);return false}E=F.substr(0,D-1)+B+F.substr(D);if(i.indexOf(F.charAt(D))===-1&&n.indexOf(F.charAt(D))===-1){D=D+1}break;case (B==="bksp"):D=D-1;if(D<0){return false}while(i.indexOf(F.charAt(D))===-1&&n.indexOf(F.charAt(D))===-1&&D>1){D=D-1}E=F.substr(0,D)+s.substr(D,1)+F.substr(D+1);break;case (B==="del"):if(D>=F.length){return false}while(p.indexOf(F.charAt(D))!==-1&&F.charAt(D)!==""){D=D+1}E=F.substr(0,D)+s.substr(D,1)+F.substr(D+1);D=D+1;break;case (B==="etc"):return true;default:return false}d.value="";d.value=E;b(d,D);return false},g=function(B){if(i.indexOf(B)===-1&&B!=="bksp"&&B!=="del"&&B!=="etc"){var C=x(d);y=true;c(B);setTimeout(function(){y=false;b(d,C)},w);return false}return true},z=function(C){if(!l){return true}C=C||event;if(y){v(C);return false}var B=m(C);if((C.metaKey||C.ctrlKey)&&(B==="X"||B==="V")){v(C);return false}if(C.metaKey||C.ctrlKey){return true}if(d.value===""){d.value=s;b(d,0)}if(B==="bksp"||B==="del"){k(B);v(C);return false}return true},e=function(C){if(!l){return true}C=C||event;if(y){v(C);return false}var B=m(C);if(B==="etc"||C.metaKey||C.ctrlKey||C.altKey){return true}if(B!=="bksp"&&B!=="del"&&B!=="shift"){if(!g(B)){v(C);return false}if(k(B)){if(u()){q()}v(C,true);return true}if(u()){q()}v(C);return false}return false},r=function(){if(!d.tagName||(d.tagName.toUpperCase()!=="INPUT"&&d.tagName.toUpperCase()!=="TEXTAREA")){return null}if(!A||d.value===""){d.value=s}j(d,"keydown",function(B){z(B)});j(d,"keypress",function(B){e(B)});j(d,"focus",function(){t=d.value});j(d,"blur",function(){if(d.value!==t&&d.onchange){d.onchange()}});return o};o.resetField=function(){d.value=s};o.setAllowed=function(B){i=B;o.resetField()};o.setFormat=function(B){s=B;o.resetField()};o.setSeparator=function(B){p=B;o.resetField()};o.setTypeon=function(B){n=B;o.resetField()};o.setEnabled=function(B){l=B};return r()}}(window));

	function toLowerCase(string) {
		string = str.toLowerCase();
		return string;
	}
			
	function tituloRequerido() {
		var listaComunidadesReqTitulo = document.getElementById("comunidadesReqTitulo").value;
		var comunidadExento= document.getElementById("idComunidadFNExento").value;
		var comunidadesRequierenTitulo = listaComunidadesReqTitulo.split('##');
		var encontrado = false;
		
		for(var i=0; i<=comunidadesRequierenTitulo.length && !encontrado; i++) {
			if(comunidadesRequierenTitulo[i]!=""){
				//Comprobamos si la comunidad autónoma seleccionada requiere que se inserte en número de título de FN 
				if(comunidadExento==comunidadesRequierenTitulo[i]){	
					document.getElementById("tituloFN").className = "";
					encontrado = true;
				} else {
					document.getElementById("tituloFNExento").value = "";
					document.getElementById("tituloFN").className = "pae-hidden";
				}
			}
		}
	}

	function validarDatosExencion(error) {
		var listaComunidadesReqTitulo = document.getElementById("comunidadesReqTitulo").value;
		var comunidadExento= document.getElementById("idComunidadFNExento").value;
		var comunidadesRequierenTitulo = listaComunidadesReqTitulo.split('##');
		var encontrado = false;
		
		for(var i=0; i<=comunidadesRequierenTitulo.length; i++) {
			if(comunidadesRequierenTitulo[i]!=""){
				//Comprobamos si la comunidad autónoma seleccionada requiere que se inserte en número de título de FN 
				if(comunidadExento==comunidadesRequierenTitulo[i]){	
					console.log("Encontrado");
					encontrado = true;
				}					
			}
		}

		if(encontrado){
			titulo = tituloFNExento.value;							
			titulo = titulo.split('_').join("");
			titulo = titulo.split('/').join("");

			if (document.getElementById("obl_CCAA")!=null && document.getElementById("obl_CCAA").value == "true") {
				if($("#tituloFNExento").val() == null || $("#tituloFNExento").val() == "" || titulo.length < 8){
					error=1;
					$("#tituloFNExento").addClass("pae-form__input--error");
					$("#tituloFNExentoError").removeClass("hiddenAccesible");
					if($("#setFocus").val() == "")
						$("#setFocus").val('tituloFNExento');
					} else {
						$("#tituloFNExento").removeClass("pae-form__input--error");
						$("#tituloFNExentoError").addClass("hiddenAccesible");
					}
				}

			}
	 					
			if(error == 1) {
				document.getElementById("boxExento").className = "pae-box pae-box--error";
				return false;
			}else {
				document.getElementById("boxExento").className = "pae-box";
				document.getElementById("tituloFNExento").className = "pae-form__input";
				return true;
			}
	}
</script>