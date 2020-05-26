<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<%
	List listaExt = (List) request.getAttribute("extensiones");
	String ext="";
	
	if(listaExt != null){
		for(int i=0;i<listaExt.size();i++){
			ext = (String)listaExt.get(i)+ ", " + ext;
		}
		ext = ext.substring(0,ext.length()-2);
		ext.toLowerCase();
	}
	 
	int numDocsAdjuntos = 0;
	String auxNumDocs = "";
	
	if (request.getAttribute("numDocsAdjuntos") != null
			&& !"".equals(request.getAttribute("numDocsAdjuntos"))) {
		auxNumDocs = (String) request.getAttribute("numDocsAdjuntos");
		numDocsAdjuntos = Integer.parseInt(auxNumDocs);
	}

%>

<script type="text/javascript">

	var numInput=0;
	var archivosTamanio=new Array(<%=totalDocumentosJS%>);
	var objectExtracto = new Object(); 
	var maxDocsAdjuntos = "<%=numDocsAdjuntos%>";
	
	function volver(){
		window.location.href = "<%=request.getContextPath()%>/secure/buscarSolicitudes";
	}
	
	function guardar(){
		document.getElementById("accion").value = "Guardar";
	}
	
	function comprobarEliminar(){
		return confirm("<spring:message code='field.registro.documentos.avisoFirma'/>");
	}
	
	function cambiarConsentimiento(valor){
	
		document.getElementById("idConsentimiento").value = valor;
		
		/* Deshabilitamos el cuadro de texto segun el marcado del check */
		if(valor){
			document.getElementById("motivoOposicion2").disabled = false;
		}else{
			document.getElementById("motivoOposicion2").disabled = true;
			document.getElementById("motivoOposicion2").value = "";
		}
	}

	function cambiarAutorizar(valor){
			
			document.getElementById("idConsentimientoAEAT").value = valor;
	}
	
	function validar(e) {
		  tecla = (document.all) ? e.keyCode : e.which;

		  if (tecla==13) {
			  return false;
		  }
	}
	
	function validarDocumentos(){
		
		for(var it=0; it<10; it++){
			var docFile = document.getElementById("documentoFile["+it+"]").value;
			var docExt = document.getElementById("extracto["+it+"]").value;
	
			if((docFile!=null && docFile!="") || (docExt!=null && docExt!="")){//Si uno de los dos campos esta informados entramos
				if(docFile==null || docFile=="" || docExt==null || docExt==""){//Si uno es nulo quiere decir que solo hay uno informado, por lo tanto mostramos el mensaje
					alert("<spring:message code='field.documento.validarDocumento'/>");
					return false;
				}
			}
			var extracto = document.getElementById("extracto["+it+"]").value;
			//Valido que el extracto introducido tenga el patro permitido por el REC
			if(extracto.match("[^a-zA-Z0-9áéíóúÁÉÍÓàèìòùÀÈÌÒÙâêîôûÂÊÎÔÛ/ÚÑñüÜç¿!¡'Ç\"%·´>_.,;:\\?\\-\\(\\)\\ª\\º\\s\\+\\*]+")){
				alert("<spring:message code='field.documento.validarDocumento.campoExtractoError'/>");
				return false;
			}
		}
		return true;
	}
	
	function firmarArchivos(){ 
		$("#formReg").submit();
		$("#numSolicitudFile").val($("#numSolicitud").val());
		$("#formArchivo").attr("action","<%=request.getContextPath()%>/secure/fileUpload");
		$("#formArchivo").submit();
	    return true;
	}
	
	
	function validarExtension(pru){
		var validado=true;
		var ndoc;
		
		if(document.getElementById("documentoFile["+pru+"]").value == null || document.getElementById("documentoFile["+pru+"]").value == ""){
			return false;
		}else{
			nDoc =document.getElementById("documentoFile["+pru+"]").value;
		}
		
		var nom=nDoc.lastIndexOf("\\");
		var ext = nDoc.lastIndexOf("\.");
		var tam = nDoc.length;
		var nombre = nDoc.substring(nom+1,tam);
		var extDoc = nDoc.substring(ext+1,tam);
		correcto = esCorrecto(extDoc);
		extValidas= extensiones();
		var textoError= "";
		
		if (correcto==false){
			textoError=textoError + "<spring:message code='field.documento.errorSubida1'/> " + nombre + " <spring:message code='field.documento.errorSubida2'/> \n<spring:message code='field.documento.errorSubida3'/>" + extValidas + "\n";
			alert(textoError);
			validado=false;
			document.getElementById("rutaArchivo"+pru).value = "";
			document.getElementById("documentoFile["+pru+"]").value = "-";
			document.getElementById("extracto["+pru+"]").value="-";
			document.getElementById("documentosFicheros["+pru+"]").value="-";	
		}
		
		return validado;
	}
	
	function esCorrecto(elem){
		var correcto = false;
		<%
			List lista = (List) request.getAttribute("extensiones");
			String elemAux ="";
			if(lista != null){
				for(int i=0;i<lista.size();i++){
					elemAux= (String)lista.get(i);
					elemAux.toLowerCase();
		%>
		if("<%=elemAux%>" == elem.toLowerCase()){
			correcto = true;
		}
		<%}}%>	
		return correcto;
	}
	
	function extensiones(){
		var extensiones = "<%=ext%>";
		return extensiones;
	}
	
	function abrirJustificante(){
		var id = document.getElementById("idSolicitud").value;
		ventanaPopup = window.open("<%=request.getContextPath()%>/secure/documentosJustificanteSolicitud?id="+id+"&llamada=P", 'miventana', 'width=550, height=250,toolbar=no,directories=no,status=no,linemenubar=no,scrollbars=no,resizable=no ,modal=yes');
		ventanaPopup.focus();
	}
	
	function ventanaInformacionRegistro(op){
		ventanaPopup = window.open("<%=request.getContextPath()%>/secure/informacionAdicional?llamada="+op, 'miventana', 'width=550, height=300,toolbar=no,directories=no,status=no,linemenubar=no,scrollbars=yes,resizable=no ,modal=yes');
		return false;
	}
	function eliminarDocumentoSubido(id,tabla)
	{
		if(id!=null){
			if(confirm("Seguro que quieres eliminar el documento?")){
				var ultimo = document.getElementsByName("documentos"+tabla).length;
				$.ajax({
			        url: "<%=request.getContextPath()%>/secure/eliminarArchivoAjax",
			    	type: 'POST',
			        data:  "idDocumento="+id,
				    contentType: false,
				    cache: false,
				    processData:false,
				    beforeSend: function(data, textStatus, jqXHR)
				    {
						//document.getElementById("enlaceElim"+id).innerHTML = "";
				    },
				    complete: function(data, textStatus, jqXHR)
				    {
					    if(data.responseText!="" || textStatus=="error") {
						    alert("<spring:message code='gestorDocumental.error.eliminarDocumento'/>");
					    }
						else {
							$("#table"+id).remove();
					    	if(ultimo == 1 ) 
								$("#tablaDocumentos"+tabla).remove();
					    	if(($("#tablaDocumentos4").length == 0 || $("#tablaDocumentos4").hasClass("hiddenAccesible")) && ($("#tablaDocumentos5").length == 0 || $("#tablaDocumentos5").hasClass("hiddenAccesible")) && ($("#tablaDocumentos6").length == 0 || $("#tablaDocumentos6").hasClass("hiddenAccesible"))) 
					    		$("#DocumentosAdjuntados").remove();
						}
									    	
				    },error: function(jqXHR, textStatus, errorThrown) 
				    {
						alert("<spring:message code='registro.errorConexion'/>");
				    }       
			   }); 
			}
		}
	}
	
	function eliminarVariosDocumentos(select) {
		var x = document.getElementsByName("file"+select);
		var limit2 = limit+1;
		$("#label_file_"+ select + "_"+ limit2).removeClass("hiddenAccesible");
		for(var i = 0; i < x; i++) {
			var aux = parseInt(document.querySelector("#file_"+select+"_"+i).textContent);
			//Varibale limit usada para saber el número máximo de archivos que se pueden adjuntar.
			limit++;
			if($("#rutaArchivo"+aux)) {
				$("#rutaArchivo"+aux).val("-");
				document.getElementById("documentoFile["+aux+"]").value = "-";
				$("#extracto["+aux+"]").val("-");
				//document.getElementById("documentosFicheros["+aux+"]").value = "-";
				var tamanio = document.getElementById("tamanioArchivo["+aux+"]").value;
				//var tamanio = document.getElementById("tamanioArchivo["+i+"]").value;
				document.getElementById("tamanioTotal").value = parseInt($("#tamanioTotal").val()) - parseInt(tamanio);
				$("#tamanioArchivo\\["+aux+"\\]").val("0");
				$("#iframeSolicitud"+aux).remove();
			}
		}
	}
	
	function eliminarDocumento(select, num){  
		var aux = parseInt(document.querySelector("#file_"+select+"_"+num).textContent);
		if(aux<maxDocsAdjuntos){
			limit++;
			$("#label_file_"+ select + "_"+ limit).removeClass("hiddenAccesible");
			$("#rutaArchivo"+aux).val("");
			//document.getElementById("tamanioArchivo["+aux+"]").value = "";
			document.getElementById("documentoFile["+aux+"]").value = "";
			document.getElementById("extracto["+aux+"]").value = "";
			$("#documentosFicheros["+aux+"]").val("");
			
			//$("#extracto"+num).val("");
			//document.getElementById("contenido"+num).style.display = "none"; 
			//var tamanio = document.getElementById("tamanioArchivo["+aux+"]").value;
			
// 			var tamanio = $("#tamanioArchivo\\["+aux+"\\]").val();
// 			document.getElementById("tamanioTotal").value = parseInt(document.getElementById("tamanioTotal").value) - parseInt(tamanio);
			var tamanio = parseInt($("#tamanioArchivo\\["+aux+"\\]").val());
			var tamanioTotal = parseInt($("#tamanioTotal").val()); 
			$("#tamanioTotal").val(tamanioTotal - tamanio); 
			$("#tamanioArchivo\\["+aux+"\\]").val("0");
			
			$("#iframeSolicitud"+aux).remove();
		}
	}
	
	function subirArchivos(num){
		//var find = false;
		var aux = num;
/* 		var documentos = document.getElementsByName("documentos4").length + document.getElementsByName("documentos5").length + document.getElementsByName("documentos6").length;
		$("#stopFileUpload").val(false);
		 for(var j=0; j<10-documentos && !find;j++) {
			if((document.getElementById("rutaArchivo"+j).value=="") || document.getElementById("rutaArchivo"+j).value=="-") {
				num = j;
				find = true;
			}
	 	} */
		//if(find) {
		var select = document.getElementById("selectDocumento").value;
/* 		if($("#extracto"+select).val() == "" || $("#extracto"+select).val() == null) {
			alert("<spring:message code='field.documento.errorCampoExtracto2'/>");
			$("#stopFileUpload").val(true);
			return false;
		} */
		
/* 		if(document.getElementById("extracto"+select).value.match("[^a-zA-Z0-9 áéíóúÁÉÍÓàèìòùÀÈÌÒÙâêîôûÂÊÎÔÛ/ÚÑñüÜç¿!¡'Ç\"%·´>_.,;:\\?\\-\\(\\)\\ª\\º\\s\\+\\*]+")){
			alert("<spring:message code='field.documento.errorCampoExtracto'/>");
			$("#stopFileUpload").val(true);
			return false;
		} */
		
			document.getElementById("numeroDeArchivo").value = num;
			document.getElementById("tipoDocumento["+num+"]").value = select;
			document.getElementById("file_"+select+"_"+aux).innerHTML=num;
			var nameActual = $("#file_"+select+"_"+aux).attr("name");
			$("#file_"+select+"_"+aux).attr("name","file");
			//$("#formArchivo").submit();
			$("#file_"+select+"_"+aux).attr("name",nameActual);
		//}else {
			/* alert("<spring:message code='field.documento.errorNumeroMaximo'/>");
			$("#stopFileUpload").val(true);
			return false; */
		//}
		
}
</script>

<form:form modelAttribute="registroSolicitudForm" action="/IPSC/secure/registroSolicitud" id="formReg" name="registroSolicitudForm" method="post" enctype="multipart/form-data">
	<form:hidden path="idConvocatoria" id="idConvocatoria" value="${registroSolicitud.idConvocatoria}"/>

 	<form:hidden path="nombre" name="registroSolicitud" value="${registroSolicitud.nombre}"/>
 	<form:hidden path="nif" name="registroSolicitud" value="${registroSolicitud.nif}"/>
 	<form:hidden path="numJustificante" name="registroSolicitud" value="${registroSolicitud.numJustificante}"/>
 	
 	<form:hidden path="idSolicitudInicial" value="${altaSolicitud.idSolicitudInicial}"/>
 	<form:hidden path="idSolicitud" id="idSolicitud" value="${registroSolicitud.idSolicitud}"/>
 	<form:hidden path="fechaPago" id="fechaPago" value="${registroSolicitud.fechaPago}"/>
 	<form:hidden path="ejercicio" id="ejercicio" value="${registroSolicitud.ejercicio}"/>
 	<form:hidden path="ministerio" id="ministerio" value="${registroSolicitud.ministerio}"/>
 	<form:hidden path="formaPago" id="formaPago" value="${registroSolicitud.formaPago}" />
 	<form:hidden path="importe" id="importe" value="${registroSolicitud.importe}"/>
 	<form:hidden path="nrc" id="nrc"  value="${registroSolicitud.nrc}"/>	
 	<form:hidden path="aceptar" id="aceptar"/>
 	<form:hidden path="signature" id="signatureReg"/>
 	<form:hidden path="signerCert" id="signerCertReg"/>
 	<form:hidden path="documentoHTML" id="documentoHTML"/>
 	<form:hidden path="fechaNacimiento" id="fechaNacimiento" name="registroSolicitud"/>
 	<form:hidden path="nacionalidad" id="nacionalidad" name="registroSolicitud"/>
 	<form:hidden path="consentimiento" id="consentimiento" name="registroSolicitud"/>
 	<form:hidden path="telefono" id="telefono" name="registroSolicitud"/>
 	<form:hidden path="email" id="email" name="registroSolicitud"/>
 	<form:hidden path="calle" id="calle" name="registroSolicitud"/>
 	<form:hidden path="codPostal" id="codPostal" name="registroSolicitud"/>
 	<form:hidden path="municipioDomicilio" id="municipioDomicilio" name="registroSolicitud"/>
 	<form:hidden path="provinciaDomicilio" id="provinciaDomicilio" name="registroSolicitud"/>
 	<form:hidden path="paisDomicilio" id="paisDomicilio" name="registroSolicitud"/>
 	<form:hidden path="tipoDiscapacidad" id="tipoDiscapacidad" name="registroSolicitud"/>
 	<form:hidden path="porcentajeDiscapacidad" id="porcentajeDiscapacidad" name="registroSolicitud"/>
 	<form:hidden path="reservaDiscapacidad" id="reservaDiscapacidad" name="registroSolicitud"/>
 	<form:hidden path="detalleDiscapacidad" id="detalleDiscapacidad" name="registroSolicitud"/>
 	<form:hidden path="datosA" id="datosA" name="registroSolicitud"/>
 	<form:hidden path="datosB" id="datosB" name="registroSolicitud"/>
 	<form:hidden path="datosC" id="datosC" name="registroSolicitud"/>
 	<form:hidden path="fechaSolicitud" id="fechaSolicitud" name="registroSolicitud"/>
 	<form:hidden path="solicitaReduccion" id="solicitaReduccion" name="registroSolicitud"/>
 	<form:hidden path="motivoReduccionDes" id="motivoReduccionDes" name="registroSolicitud"/>
 	<form:hidden path="entidadFinanciera" id="entidadFinanciera" name="registroSolicitud"/>
 	<form:hidden path="tipoPago" id="tipoPago" name="registroSolicitud"/>
 	<form:hidden path="desProvinciaExamen" id="desProvinciaExamen" name="registroSolicitud"/>
 	<form:hidden path="desTituloOficial" id="desTituloOficial" name="registroSolicitud"/>
 	<form:hidden path="desEspecialidad" id="desEspecialidad" name="registroSolicitud"/>
 	<form:hidden path="otrosTitulos" id="otrosTitulos" name="registroSolicitud"/>
 	<form:hidden path="desConsentimiento" id="desConsentimiento" name="registroSolicitud"/>
 	<form:hidden path="extractoRegistro" id="extractoRegistro"/>
	<form:hidden path="firmaExtractoRegistro" id="firmaExtractoRegistro"/>
	<form:hidden path="estaEnRegistro" id="estaEnRegistro"/>

	<input type="hidden" id="ordenList" value="0"/>
	<input type="hidden" id="stopFileUpload" value="false" />
 	<input type="hidden" id="htmlFormulario"/>
 	
		<div style="display: none">

	</div>
		
 			<%@ include file="/jsp/firmaConsentimiento.jsp" %>
 	
</form:form>
	
		
			<%@ include file="/jsp/documentosAdjuntos.jsp" %>
			<%@ include file="/jsp/validacion.jsp" %>
            
		<input type="hidden" id="tamanioTotal" value="0">
		<%
			for (int i = 0; i < numDocsAdjuntos; i++) {
				pageContext.setAttribute("i", Integer.valueOf(i));		
				String nameDocFile = "documentoFile[" + i + "]";
				String nameTipoDoc = "tipoDocAdjunto[" + i + "]";
				String nameExtracto = "extracto[" + i + "]";
				String nameExtractoFirefox = "extractoFirefox[" + i + "]";
				String nameDiv = "div" + i;
				String nameDiv2 = "div2" + i;
				String docFic = "documentosFicheros[" + i + "]";
				String borrarDocumento = "borrarDocumento" + i;
				String cancelarSubida = "cancelarSubida" + i;
				String borrarDocumentoExplorer = "borrarDocumentoExplorer" + i;
				String cancelarSubidaExplorer = "cancelarSubidaExplorer" + i;
				String nameDocFileFirefox = "documentoFileFirefox[" + i + "]";
				String tipoDocumento = "tipoDocumento[" + i + "]";
			}
		%>
			
<script type="text/javascript">
	
	function comprobacionesAdjunto() {
		var valueTipoDocumento = $("#selectDocumento").val(); // value del tipo de documento
		var textArea = $("#extracto"+valueTipoDocumento); // el campo textarea de documento

		if (valueTipoDocumento == "0") {
			 // el combo no ha seleccionado ningun tipo de documento
			 alert("Debe seleccionar un tipo de documento");
			 // cerramos la subida de ficheros
			$("input[type='file']").prop("disabled",true);
			 return false; 
		} 
		
		if (!$.trim(textArea.val())) { 
			 // el textarea esta vacio o contiene espacios en blanco
			 alert("Debe informar el campo Nombre o Descripción breve del documento");
			 // cerramos la subida de ficheros
			 $("input[type='file']").prop("disabled",true);
			 return false; 
		}
		// si pasa las validaciones se activa el boton subida
		//$("input[type='file'][name='file" + valueTipoDocumento + "']").prop("disabled",false);
		$("input[type='file']").prop("disabled",false);
	}

	function eliminaUltimoDocumentoAjunto() {
		var valueTipoDocumento = $("#selectDocumento").val(); // value del tipo de documento
		var numeroLi = $("div[data-index="+valueTipoDocumento+"] ul").children().length;
		$("div[data-index="+valueTipoDocumento+"] ul").children()[0].remove(); // borra primer li, que es la ultima imagen de documento subida
		$("div[data-index="+valueTipoDocumento+"] ul").children()[numeroLi-3].remove(); // borra penultimo li, que es el li del listado principal del formulario
	}
	

	$("#formArchivo").submit(function(e)
	{
		var archivo = document.getElementById("numeroDeArchivo").value;
		var select = document.getElementById("selectDocumento").value;
		var iframe = null; 
	    var formObj = $(this);
		var formURL = formObj.attr("action");
		
		 document.getElementById("extracto["+archivo+"]").value = document.getElementById("extracto"+select).value;
		 document.getElementById("extracto"+select).value = "";
		 document.getElementById("tamanioArchivo["+archivo+"]").value = 1;					 				 
		 document.getElementById("rutaArchivo"+archivo).value = "dd";		
		 
	     // Create the iframe...
		 var iframeId = 'unique' + (new Date().getTime());
	     //create an empty iframe
	     iframe = $('<iframe src="javascript:false;" name="' + iframeId + '" id="iframeSolicitud' +archivo+'" />');
	     //hide it
	     iframe.hide();
	     //set form target to iframe
	     formObj.attr('target', iframeId);
	     //Add iframe to body
	     iframe.appendTo('body');
	     $("#iframeSolicitud"+archivo).load(function (e) {
	    	 var doc = getDoc(iframe[0]);
	         var docRoot = doc.body ? doc.body : doc.documentElement;
	         var data = docRoot.innerHTML;
	 		 var resto = 16;
	 		 var start=data.indexOf("<div id=\"error\">");
	 		 
	 		 if(start == -1){
	 		 	start=data.indexOf("<DIV id=error>");
	 		 	resto = 14;
	 		 }
	 		 
	 		/*  if(start == -1){
	 			alert("<spring:message code='field.form.pagoSolicitud.error.tiempoConexion' />");
	 			eliminaUltimoDocumentoAjunto();
	 			iframe.remove();
	 			return false;
	 		 } */
	 			
	 		 var strFin=data.substr(start);
	 		 var fin=strFin.indexOf("</div>");
	 		
	 		 if(fin==-1){
	 			 fin=strFin.indexOf("</DIV>");
	     	 }
	     	 
	 		 var result = data.substr(start+resto, fin-resto);
	 		 var res = result.split("###");
	 		
	 		 //var nombre = res[0].replace(/\s/g,'');
	 		 var nombre = unescape(res[0].replace(/\s/g,''));
			/* 	 		 if(nombre == "") {
	 			alert("<spring:message code='field.documento.errorSubiendoArchivo'/>");
				$("#stopFileUpload").val(true);
				eliminaUltimoDocumentoAjunto();
				return false;
	 		 } */
	 		 //var certificado = res[1].replace(/\s/g,'');
	         var tamanioFichero = <%=tamanioFichero%>;             	
	 	     var totalMaxDocumentos = <%=tamanioTotalDocumento%>;     	     
	 		 var tamanio = 1;
	 	      
/* 	 		 if(res[2]==1){
	 				alert("<spring:message code='field.solicitud.registro.errorRealizacion' />");
	 		 }else if(res[2]==2){
	 				alert("<spring:message code='field.formulario790.javascript.tamanioFichero'/> "+parseInt(tamanioFichero/1000)+"Mb");
	 				eliminaUltimoDocumentoAjunto();
	 		 }else{ */
	 		/* 	 if(document.getElementById("tamanioArchivo["+archivo+"]").value==0){							
	 				 document.getElementById("tamanioTotal").value = parseInt(tamanio) +  parseInt(document.getElementById("tamanioTotal").value);
	 				 var tamanioTotal = document.getElementById("tamanioTotal").value;
	 			 }else{			
	 				    var tamanioAntiguo = document.getElementById("tamanioArchivo["+archivo+"]").value;	
	 					document.getElementById("tamanioTotal").value = parseInt(document.getElementById("tamanioTotal").value) - parseInt(tamanioAntiguo);		
	 					document.getElementById("tamanioTotal").value = parseInt(tamanio) +  parseInt(document.getElementById("tamanioTotal").value);
	 					var tamanioTotal = document.getElementById("tamanioTotal").value;
	 			 } */
	 			 
	 		/* 	 if(tamanioTotal >= totalMaxDocumentos){
	 	  				alert("<spring:message code='solicitud.error.registro.tamanioTotalFicheros'/> "+parseInt(totalMaxDocumentos/1000)+"Mb");
						var tamanioTotal = $("#tamanioTotal").val(); // tamanioTotal en megas
						$("#tamanioTotal").val(tamanioTotal - tamanio); // se le asigna al campo tamanioTotal la cantidad anterior al exceso
						eliminaUltimoDocumentoAjunto();
	 			 }else{ */
	 				 document.getElementById("documentoFile["+archivo+"]").value = nombre;
	 				 //if(validarExtension(archivo)) {		
	 					 document.getElementById("extracto["+archivo+"]").value = document.getElementById("extracto"+select).value;
	 					 document.getElementById("extracto"+select).value = "";
						 //document.getElementById("tamanioArchivo["+archivo+"]").value = tamanio;					 				 
						 document.getElementById("rutaArchivo"+archivo).value = nombre;		 			 
						 //document.getElementById("documentosFicheros["+archivo+"]").value = certificado; 	
	 					
	 				 //}
	 			 //}
	 			//}
 	 			iframe.remove();
	     })
	     });
	
	</script>