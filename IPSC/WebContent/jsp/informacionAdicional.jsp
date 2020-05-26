<script type="text/javascript">
function validarDatosInformacionAdicional(error){


	if (document.getElementById("obl_titulo")!=null && document.getElementById("obl_titulo").value == "true") {
		if(document.getElementById("titulo").value == null  || document.getElementById("titulo").value=="-" || document.getElementById("titulo").selectedIndex==0){
			error=1;
			document.getElementById("titulo").className += " pae-form__input--error";
			if($("#setFocus").val() == "")
				$("#setFocus").val('titulo');
		} else {
			$("#titulo").removeClass("pae-form__input--error");
		}
	}

	if (document.getElementById("obl_otrosTitulos")!=null && document.getElementById("obl_otrosTitulos").value == "true") {
		if(document.getElementById("otrosTitulos").value == null  || document.getElementById("otrosTitulos").value == "" || document.getElementById("otrosTitulos").selectedIndex==0){
			$("#anyadeOtroTitulo").click();
			$("#papelera").hide();
			error=1;
			document.getElementById("otrosTitulos").className += " pae-form__input--error";
			if($("#setFocus").val() == "")
				$("#setFocus").val('otrosTitulos');
		} else {
			$("#otrosTitulos").removeClass("pae-form__input--error");
			$("#papelera").show();
		}
	}

	 $('[id^=campoDinamico]').each(function() {
  	   var nombreId = $(this).attr("id");
	   var idCampoDinamico = nombreId.split("-")[1];
		        	
	   if ($("#obl_campoPropio"+idCampoDinamico)!=null && $("#obl_campoPropio"+idCampoDinamico).val()=="true") {
		   if ($("#plantilla"+idCampoDinamico).val()==null || $("#plantilla"+idCampoDinamico).val()=="" || document.getElementById("plantilla"+idCampoDinamico).selectedIndex==0) {
			   error=1;
			   $("#plantilla"+idCampoDinamico).addClass("pae-form__input--error");
			   if($("#setFocus").val()=="")
				   $("#setFocus").val('plantilla'+idCampoDinamico);
					} else {
						$("#plantilla"+idCampoDinamico).removeClass("pae-form__input--error");
					}
	    		}
	 });
	
	if(error == 1) {
		document.getElementById("boxInformacionAdicional").className = "pae-box pae-box--error";
		if ($("#setFocus").val() != "") {
			document.getElementById($("#setFocus").val()).focus();
		}
		$("#setFocus").val("");
		return false;
	}else {
		document.getElementById("boxInformacionAdicional").className = "pae-box";
		document.getElementById("titulo").className = "pae-form__input";
		return true;
	}
}

</script>
		<div data-function="accordion-box" class="pae-box" id="boxInformacionAdicional">
              <div class="pae-box__header">
                <h3 class="pae-box__header--title"><spring:message code="field.formulario790.informacionAdicional" /></h3>
              </div>
              <div class="pae-box__body">
                <fieldset>
                  <legend class="pae-form__legend-text">
                  	<spring:message code="field.formulario790.titulos.tituloAux" />
                  </legend>
                  <div class="pae-layout">
                    <div class="pae-layout__item pae-u-6/12 pae-u-6/12-lap pae-u-1/1-palm">
                      <div class="pae-form__cell">
                        <label for="titulo" class="pae-form__label">
                        	<span class="pae-form__label--text">
                        		<spring:message code="field.formulario790.titulos.exigiidosAux" />
                        		<logic:equal name="plantilla" property="titulosExigidos" value="S">
									<input type="hidden" id="obl_titulo" value="true">
									<span class="obligatorio">*</span>
								</logic:equal>
                        	</span>
                        </label>                     
                        <form:select path="titulo" id="titulo" onchange="cambiarMayusculas(this)" class="pae-form__input">
							<option value="0"><spring:message code="field.solicitud.selecciona" /></option>
							<form:options items="${altaSolicitud.titulosOficiales}" itemLabel="descripcion" itemValue="id" style="text-transform: uppercase;"/>
						</form:select>
							<span class="pae-form__text-error">
								<spring:message code="field.solicitud.campoObligatorio" />
                      </div>
                    </div>
                    <div class="pae-layout__item pae-u-6/12 pae-u-6/12-lap pae-u-1/1-palm">
                      <div class="pae-form__cell pae-form__cell--margin-bottom">
                      	<a name="eliminaAnyadirTitulo" href="#" id="anyadeOtroTitulo" title="Añadir otro título" data-function="function-click-add-title" class="pae-form__icon-link--add">
                      	<span class="pae-form__icon-link-text">
                      		<spring:message code="field.formulario790.titulos.agregarTitulo" />
                      	</span>
                      	</a>
                      </div>
                      <div class="pae-form__cell pae-form__cell--hide">
                        <label for="otrosTitulos" class="pae-form__label">
                        	<span class="pae-form__label--text">
                        		<spring:message code="field.formulario790.titulos.exigiidosAux2" />
                        		<logic:equal name="plantilla" property="otrosTitulos" value="S">
									<input type="hidden" id="obl_otrosTitulos" value="true">
									<span class="obligatorio">*</span>
								</logic:equal>
                        	</span>
                        </label>
                        <!-- Otros Titulos - INI -->
                        <c:choose>
						    <c:when test="${solicitudForm.otrosTitulosList != null && solicitudForm.otrosTitulosList.size() > 0}">
					       		<form:select path="otrosTitulos" id="otrosTitulos" class="pae-form__input pae-form__input--s-large" style="text-transform: uppercase;">
									<option value="0"><spring:message code="field.solicitud.selecciona" /></option>
									<form:options items="${solicitudForm.otrosTitulosList}" itemValue="descripcion" itemLabel="descripcion" />
								</form:select>
						    </c:when>    
						    <c:otherwise>
                        <input type="text" name="otrosTitulos" property="otrosTitulos" id="otrosTitulos" value="" class="pae-form__input pae-form__input--s-large" maxlength="200" 
                        	style="text-transform: uppercase;" onchange="cambiarMayusculas(this)">
						    </c:otherwise>
						</c:choose>	
                        <!-- Otros Titulos - FIN -->	                        
                        <span class="pae-form__text-error">
							<spring:message code="field.solicitud.campoObligatorio" />
						</span>
                        <div id="papelera" class="pae-form__icon-contain">
                        	<a href="#" name="eliminaEliminarTitulo" title="Eliminar Campo" data-function="function-click-remove-title" class="pae-form__icon-link--remove">
                        		<span class="pae-form__icon-link-text">
                        			<spring:message code="field.solicitud.botonEliminar"/>
                        		</span>
                        	</a>
                        </div>
                       
                      </div>
                    </div>
                  </div>
                </fieldset>
                <c:choose>
	                <c:when test="${ocultarDatosPropios.toString() != 'S'}">
			           <fieldset>
		                  <legend class="pae-form__legend-text">
		                  	<spring:message code="field.formulario790.datosConsignarMin" />
		                  </legend>
		                  <div class="pae-layout">
							<logic:present name="listaPlantillasPropias" scope="request">
								<logic:iterate id="campo" name="listaPlantillasPropias" >
									<div id="campoDinamico-${campo.campoPropioBean.id}" class="pae-layout__item pae-u-6/12 pae-u-6/12-lap pae-u-1/1-palm">
		                   				<div class="pae-form__cell" name="campPropio">
		                       				<label for="plantilla${campo.campoPropioBean.id}" class="pae-form__label">
		                       					<span class="pae-form__label--text">
		                       						<bean:write name="campo"  property="campoPropioBean.tituloCampo"/>
		                       						<logic:equal name="campo" property="obligatorio" value="true">
														<input type="hidden" id="obl_campoPropio${campo.campoPropioBean.id}" value="true">
														<span class="obligatorio">*</span>
													</logic:equal> 
		                       					</span>
		                       				</label>
		                       				<!-- DATOS PROPIOS CONF INI -->
		                       				<c:choose>
			           						    <c:when test="${campo.campoPropioBean.datosPropiosConfiguracion != null && campo.campoPropioBean.datosPropiosConfiguracion.size() > 0}">
											    	<select id="plantilla${campo.campoPropioBean.id}" name="plantilla${campo.campoPropioBean.id}" class="pae-form__input" >
														<option value=""><spring:message code="field.solicitud.selecciona" /></option>
														 <c:forEach items="${campo.campoPropioBean.datosPropiosConfiguracion}" var="datoConf">
														 	<option value="${datoConf.descripcion}">${datoConf.descripcion}</option>
														 </c:forEach>
													</select>
											    </c:when>    
											    <c:otherwise>
			                       					<input type="text" id="plantilla${campo.campoPropioBean.id}" name="plantilla${campo.campoPropioBean.id}" class="pae-form__input" style="text-transform: uppercase;" onpaste="comprobarPasteCaracteresEspeciales(this);">
												</c:otherwise>
										    </c:choose>
											 <!-- DATOS PROPIOS CONF FIN -->              				
		                       				<span class="pae-form__text-error">
												<spring:message code="field.solicitud.campoObligatorio" />
											</span>
		                       			</div>	
		                   			</div>
		                   		</logic:iterate>
							</logic:present>
						  </div>
			           </fieldset>
			       </c:when>
		        </c:choose>
             </div>
          </div> 