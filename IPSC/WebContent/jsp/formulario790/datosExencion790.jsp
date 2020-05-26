			<div data-function="accordion-box" class="pae-box" id="boxExento">
              <div class="pae-box__header">
                <h3 class="pae-box__header--title"><spring:message code="field.pagoSolicitud.exencionTitulo" /></h3>
              </div>
              <div class="pae-box__body">
                <fieldset>
                  <legend class="pae-form__legend-text"><spring:message code="field.pagoSolicitud.subtitulo" /></legend>
                  <div class="pae-layout">
                    <div class="pae-layout__item pae-u-4/12 pae-u-4/12-lap pae-u-1/1-palm">
                      <div class="pae-form__cell">
                        <ul class="pae-form__list">
                         
                          <jsp:useBean id="motivosCompletos" scope="request" class="java.util.ArrayList" />
                          <%int i = 1;%>
                          <c:if test="${not empty motivosCompletos }">
								<logic:iterate id="motivosExencion" name="motivosCompletos">
                          			<li class="pae-form__list-item">
                            			<input type="checkbox"name="motivoRedEx" property="motivoRedEx" id="checkbox-exemption-<%=i%>" value="<bean:write name="motivosExencion" property = "id"/>" 
                            				onchange="motivoReduccion(this)" data-function="checkbox-custom-input" class="pae-form__custom-check" >
                            				<label for="checkbox-exemption-<%=i%>" id="<bean:write name="motivosExencion" property = "descripcion"/>" data-function="checkbox-custom" title="<spring:message code="field.solicitud.mostrarInfo"/>
                            					<bean:write name="motivosExencion" property = "descripcion"/> " class="pae-form__custom-check-label pae-form__custom-check-label--option unchecked">
                            					<bean:write name="motivosExencion" property = "descripcion"/>
                            				</label>
                          			</li>
                           		<%i++; %>
                          		</logic:iterate>
                          </c:if>		
                          <jsp:useBean id="motivos" scope="request" class="java.util.ArrayList" />
                          <c:if test="${not empty motivos }">
								<logic:iterate id="motivosExencionIncompletos" name="motivos">
                          			<li class="pae-form__list-item">
                            			<input type="checkbox" name="motivoRedEx" property="motivoRedEx" id="checkbox-exemption-<%=i%>" value="<bean:write name="motivosExencionIncompletos" property = "id"/>" 
                            				onchange="motivoReduccion(this)" data-function="checkbox-custom-input" class="pae-form__custom-check" >
                            			<label for="checkbox-exemption-<%=i%>" id="<bean:write name="motivosExencionIncompletos" property = "descripcion"/>" data-function="checkbox-custom" title="<spring:message code="field.solicitud.mostrarInfo"/>
                            				<bean:write name="motivosExencionIncompletos" property = "descripcion"/>" class="pae-form__custom-check-label pae-form__custom-check-label--option unchecked">
                            				<bean:write name="motivosExencionIncompletos" property = "descripcion"/>
                            			</label>
                          			</li>
                          
                          		<%i++; %>
                          	    </logic:iterate>
                          </c:if>        
                        </ul>
                      </div>
                      </div>
                          
                          
                    <div class="pae-layout__item pae-u-8/12 pae-u-8/12-lap pae-u-1/1-palm">
                      <div class="pae-form__advisor-tooltip pae-form__advisor-tooltip--dynamic"><span class="hiddenAccesible"><spring:message code="field.convocatoria.mensajeAlerta"/></span></div>
                      <div class="pae-form__advisor-contain hiddenAccesible">
                      <logic:iterate id="motivosExencion" name="motivosCompletos">
	                      <div class="pae-form__advisor-checkbox pae-form__advisor-checkbox--<bean:write name="motivosExencion" property = "id"/>">
	                          <p class="pae-form__advisor-text"><spring:message code="field.convocatoria.mensajeExencion" /></p>
	                          <ul class="pae-form__advisor-list">
	                          	<bean:write name="motivosExencion" property ="textoExplicativo" filter="false"/>
	                          </ul>                          
	                      </div>                       
	                    </logic:iterate>
	                    <logic:iterate id="motivosExencionIncompletos" name="motivos">
	                      <div class="pae-form__advisor-checkbox pae-form__advisor-checkbox--<bean:write name="motivosExencionIncompletos" property = "id"/>">
	                          <p class="pae-form__advisor-text"><spring:message code="field.convocatoria.mensajeExencion" /></p>
	                          <ul class="pae-form__advisor-list">
	                            <bean:write name="motivosExencionIncompletos" property ="textoExplicativo" filter="false"/>                            
	                          </ul>
	                      </div>                       
	                    </logic:iterate>
                      </div>
                    </div>
                    
              <div class="pae-layout__item pae-u-6/12 pae-u-6/12-lap pae-u-1/1-palm pae-hidden" id="comunidadesFN">
              <div class="animated fadeInLeft">
                    	<label for="state" id="labelComunidadFNExento" class="pae-form__label"><strong class="pae-form__advisor-text--bold"><spring:message code="field.convocatoria.comunidadFamNumGeneral"/></strong></label>
                      	 <form:select path="comunidadFN" class="pae-form__input" id="idComunidadFN" onchange="comunidadFNChecked();">
                           	<option value="0" class="pae-form__option"><spring:message code="field.solicitud.selecciona"/></option>
                            <form:options items="${listcomunidadesFN}" itemValue="idComunidad" itemLabel="descripcion" style="text-transform: uppercase;"/>
                      	 </form:select>
                      	 </div>
                      	 </div>
                     <div id="idNumeroTitulo" class="pae-layout__item pae-u-6/12 pae-u-6/12-lap pae-u-1/1-palm pae-hidden">
                      	 <label for="numeroTituloFN" class="pae-form__label pae-form__span-label" id="labelidNumeroTitulo"><strong class="pae-form__advisor-text--bold"><spring:message code="field.solicitud.numeroTitulo"/>*</strong></label>
                      	 <input type="text" id="numeroTituloFN" name="numeroTituloFN" class="pae-form__input" value="" maxlength="20"/>
                    </div>
                    <%@ include file="/jsp/formulario790/datosDiscapacidad790.jsp" %>
                </fieldset>
                </div>
              </div>
            
			
<script type="text/javascript">
function toLowerCase(string) {
	string = str.toLowerCase();
	return string;
}

function mascara() {
	   MaskedInput({
	      elm: document.getElementById('numeroTituloFN'), // select only by id
	      format: '__/____/__',
	      separator: '-'
	   });
	};


	//Javascript para la mascara de titulo de familia numerosa
	// masked_input_1.4-min.js
	(function(a){a.MaskedInput=function(f){if(!f||!f.elm||!f.format){return null}if(!(this instanceof a.MaskedInput)){return new a.MaskedInput(f)}var o=this,d=f.elm,s=f.format,i=f.allowed||"0123456789",h=f.allowedfx||function(){return true},p=f.separator||"/:-",n=f.typeon||"_YMDhms",c=f.onbadkey||function(){},q=f.onfilled||function(){},w=f.badkeywait||0,A=f.hasOwnProperty("preserve")?!!f.preserve:true,l=true,y=false,t=s,j=(function(){if(window.addEventListener){return function(E,C,D,B){E.addEventListener(C,D,(B===undefined)?false:B)}}if(window.attachEvent){return function(D,B,C){D.attachEvent("on"+B,C)}}return function(D,B,C){D["on"+B]=C}}()),u=function(){for(var B=d.value.length-1;B>=0;B--){for(var D=0,C=n.length;D<C;D++){if(d.value[B]===n[D]){return false}}}return true},x=function(C){try{C.focus();if(C.selectionStart>=0){return C.selectionStart}if(document.selection){var B=document.selection.createRange();return -B.moveStart("character",-C.value.length)}return -1}catch(D){return -1}},b=function(C,E){try{if(C.selectionStart){C.focus();C.setSelectionRange(E,E)}else{if(C.createTextRange){var B=C.createTextRange();B.move("character",E);B.select()}}}catch(D){return false}return true},m=function(D){D=D||window.event;var C="",E=D.which,B=D.type;if(E===undefined||E===null){E=D.keyCode}if(E===undefined||E===null){return""}switch(E){case 8:C="bksp";break;case 46:C=(B==="keydown")?"del":".";break;case 16:C="shift";break;case 0:case 9:case 13:C="etc";break;case 37:case 38:case 39:case 40:C=(!D.shiftKey&&(D.charCode!==39&&D.charCode!==undefined))?"etc":String.fromCharCode(E);break;default:C=String.fromCharCode(E);break}return C},v=function(B,C){if(B.preventDefault){B.preventDefault()}B.returnValue=C||false},k=function(B){var D=x(d),F=d.value,E="",C=true;switch(C){case (i.indexOf(B)!==-1):D=D+1;if(D>s.length){return false}while(p.indexOf(F.charAt(D-1))!==-1&&D<=s.length){D=D+1}if(!h(B,D)){c(B);return false}E=F.substr(0,D-1)+B+F.substr(D);if(i.indexOf(F.charAt(D))===-1&&n.indexOf(F.charAt(D))===-1){D=D+1}break;case (B==="bksp"):D=D-1;if(D<0){return false}while(i.indexOf(F.charAt(D))===-1&&n.indexOf(F.charAt(D))===-1&&D>1){D=D-1}E=F.substr(0,D)+s.substr(D,1)+F.substr(D+1);break;case (B==="del"):if(D>=F.length){return false}while(p.indexOf(F.charAt(D))!==-1&&F.charAt(D)!==""){D=D+1}E=F.substr(0,D)+s.substr(D,1)+F.substr(D+1);D=D+1;break;case (B==="etc"):return true;default:return false}d.value="";d.value=E;b(d,D);return false},g=function(B){if(i.indexOf(B)===-1&&B!=="bksp"&&B!=="del"&&B!=="etc"){var C=x(d);y=true;c(B);setTimeout(function(){y=false;b(d,C)},w);return false}return true},z=function(C){if(!l){return true}C=C||event;if(y){v(C);return false}var B=m(C);if((C.metaKey||C.ctrlKey)&&(B==="X"||B==="V")){v(C);return false}if(C.metaKey||C.ctrlKey){return true}if(d.value===""){d.value=s;b(d,0)}if(B==="bksp"||B==="del"){k(B);v(C);return false}return true},e=function(C){if(!l){return true}C=C||event;if(y){v(C);return false}var B=m(C);if(B==="etc"||C.metaKey||C.ctrlKey||C.altKey){return true}if(B!=="bksp"&&B!=="del"&&B!=="shift"){if(!g(B)){v(C);return false}if(k(B)){if(u()){q()}v(C,true);return true}if(u()){q()}v(C);return false}return false},r=function(){if(!d.tagName||(d.tagName.toUpperCase()!=="INPUT"&&d.tagName.toUpperCase()!=="TEXTAREA")){return null}if(!A||d.value===""){d.value=s}j(d,"keydown",function(B){z(B)});j(d,"keypress",function(B){e(B)});j(d,"focus",function(){t=d.value});j(d,"blur",function(){if(d.value!==t&&d.onchange){d.onchange()}});return o};o.resetField=function(){d.value=s};o.setAllowed=function(B){i=B;o.resetField()};o.setFormat=function(B){s=B;o.resetField()};o.setSeparator=function(B){p=B;o.resetField()};o.setTypeon=function(B){n=B;o.resetField()};o.setEnabled=function(B){l=B};return r()}}(window));


</script>