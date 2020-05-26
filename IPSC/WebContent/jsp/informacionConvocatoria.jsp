       <input type="hidden" value="<%=siglasCentroGestorJusticia%>" name="siglasCentroGestorJusticia"/>
       <input type="hidden" value="<%=siglasCentroGestor%>" name="siglasCentroGestor"/>
       
       
       
       <h2 class="pae-title">
       	<spring:message code="field.solicitud.inscripcionOnline"/>
       		<% if(!tipoPersona.equals("FH")) { %>
       			<span class="pae-title pae-title--highlighted"><bean:write name="altaSolicitud" property="numSolicitud"/></span>
       		<% } else {  %>
       			<span id="numSolFH" class="pae-title pae-title--highlighted"></span>
       		<% } %>
       </h2>
       
       
       <div id="rellenarCampos" class="pae-alertbox pae-alertbox--warning pae-hidden">
              <p class="pae-alertbox__text"><spring:message code="field.solicitud.errorCampos" /></p>
          </div>
          <div class="pae-box-details">
          <% if(subsanarInscripcion != null && subsanarInscripcion  ){ %>
            <h3 class="pae-box-details__title"><spring:message code="field.solicitud.realizandoSubsanacion"/></h3>
          <% } else { %>
            <h3 class="pae-box-details__title"><spring:message code="field.solicitud.realizandoInscripcion"/></h3>
          <% } %> 
            <div class="pae-box-list">
              <ul class="pae-list-details">
                <li class="pae-list-details__item-head"><span class="pae-list-details__item-text pae-list-details__item-text--highlighted"><spring:message code="field.cuerpoMin"/></span></li>
                <li class="pae-list-details__item-body--last"><span class="pae-list-details__item-text"><bean:write name="altaSolicitud" property="desCuerpoEscala"/></span></li>
              </ul>
              <ul class="pae-list-details">
                <li class="pae-list-details__item-head"><span class="pae-list-details__item-text pae-list-details__item-text--highlighted"><spring:message code="field.centroMin"/></span></li>
                <li class="pae-list-details__item-body"><span class="pae-list-details__item-text"><bean:write name="altaSolicitud" property="desMinisterio"/></span></li>
                <li class="pae-list-details__item-body--last"><span class="pae-list-details__item-text"><bean:write name="altaSolicitud" property="desCentroGestor"/></span></li>
              </ul>
              <ul class="pae-list-details">
                <li class="pae-list-details__item-head"><span class="pae-list-details__item-text pae-list-details__item-text--highlighted"><spring:message code="field.nivelAcceso"/></span></li>
                <li class="pae-list-details__item-body"><span class="pae-list-details__item-text">${siglasGrupoConvocatoria}</span></li>
                <li class="pae-list-details__item-body--last"><span class="pae-list-details__item-text"><bean:write name="altaSolicitud" property="desFormaAcceso"/></span></li>
              </ul>
            </div>
          </div>