<div data-function="accordion-box" class="pae-box" id="detalleBoxInformacionAdicional">
              <div class="pae-box__header">
                <h3 class="pae-box__header--title"><spring:message code="field.formulario790.informacionAdicional" /></h3>
              </div>
              <div class="pae-box__body">
                <fieldset>
                  <legend class="pae-form__legend-text"><spring:message code="field.formulario790.titulos.tituloAux" /></legend>
                  <div class="pae-layout">
                    <div class="pae-layout__item pae-u-6/12 pae-u-6/12-lap pae-u-1/1-palm">
                      <div class="pae-form__cell">
                        <label for="titulo" class="pae-form__label"><span class="pae-form__label--text"><spring:message code="field.formulario790.titulos.exigiidosAux" /></span></label>                     
                        <span class="pae-form__text-readonly"><bean:write name="solicitud" property="titulos" scope="request" /></span>
                      </div>
                    </div>
                    <logic:notEmpty name="solicitud" property="otrosTitulos">
                    <div class="pae-layout__item pae-u-6/12 pae-u-6/12-lap pae-u-1/1-palm">
                      <div class="pae-form__cell">
                        <label for="otrosTitulos" class="pae-form__label"><span class="pae-form__label--text"><spring:message code="field.formulario790.titulos.exigiidosAux2" /></span></label>
                        <span class="pae-form__text-readonly"><bean:write name="solicitud" property="otrosTitulos" scope="request" /></span>
                      </div>
                    </div>
                    </logic:notEmpty>
                  </div>
                </fieldset>
                <c:choose>
	            	<c:when test="${ocultarDatosPropios.toString() != 'S'}">
	                	<fieldset>
	                  		<legend class="pae-form__legend-text"><spring:message code="field.formulario790.datosConsignarMin" /></legend>
	                  		<div class="pae-layout">
		                  		<logic:iterate id="campoPropio" name="solicitud" property="solicitudPropiosBean">
		                  		     <div class="pae-layout__item pae-u-6/12 pae-u-6/12-lap pae-u-1/1-palm">
				                     	<div class="pae-form__cell">
				                        	<label  class="pae-form__label">
				                        		<span class="pae-form__label--text">
				                        			<bean:write name="campoPropio"  property="camposPropiosBean.campo"/>
				                        		</span>
				                        	</label>  
				                        	<span class="pae-form__text-readonly">
				                        		<bean:write name="campoPropio" property="valor" />
				                        	</span>                   
				                        </div>
		               				</div> 
			                    </logic:iterate>
	                  		</div>
	                	</fieldset>
	                 </c:when>
	               </c:choose>
                </div>
            </div>