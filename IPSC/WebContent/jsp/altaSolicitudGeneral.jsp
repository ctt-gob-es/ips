<script type="text/javascript" src="<%=request.getContextPath()%>/bower_components/jquery-ui/ui/accordion-custom.js"></script>
<script type="text/javascript">
function validarDatosSolicitante(error){

	if (document.getElementById("obl_inputNombre")!=null && document.getElementById("obl_inputNombre").value == "true") {
		if(document.getElementById("nombre").value == null || document.getElementById("nombre").value == ""){
			error=1;
		}
	}
	
	
	if (document.getElementById("obl_inputPrimerApellido")!=null && document.getElementById("obl_inputPrimerApellido").value == "true") {
		if(document.getElementById("primerApellido").value == null || document.getElementById("primerApellido").value == ""){
			error=1;
		}
	}

	if (document.getElementById("obl_inputSegundoApellido")!=null && document.getElementById("obl_inputSegundoApellido").value == "true") {
		if(document.getElementById("segundoApellido").value == null || document.getElementById("segundoApellido").value == ""){
				error=1;
		}
	}

	if (document.getElementById("obl_inputNif")!=null && document.getElementById("obl_inputNif").value == "true") {
		if(document.getElementById("nif").value == null || document.getElementById("nif").value == ""){
			error=1;
		}
	}	

	if (document.getElementById("obl_sexo")!=null && document.getElementById("obl_sexo").value == "true") {
		if(document.getElementById("mujer").checked != true && document.getElementById("hombre").checked != true) {
			error=1;
		}
	}

	if (document.getElementById("obl_fechaNacimiento")!=null && document.getElementById("obl_fechaNacimiento").value == "true") {
		if($("#fechaNacimiento").val() == null || $("#fechaNacimiento").val() == ""){
			error=1;
			$("#fechaNacimiento").addClass("pae-form__input--error");
			$("#fechaNacimientoError").removeClass("hiddenAccesible");
			if($("#setFocus").val() == "")
				$("#setFocus").val('fechaNacimiento');
		} else {
			$("#fechaNacimiento").removeClass("pae-form__input--error");
			$("#fechaNacimientoError").addClass("hiddenAccesible");
		}
	} 

	if (document.getElementById("obl_inputNacionalidad")!=null && document.getElementById("obl_inputNacionalidad").value == "true") {
		if($("#nacionalidad").val() == null || $("#nacionalidad").val() == ""){
			error=1;
			$("#nacionalidad").addClass("pae-form__input--error");
			if($("#setFocus").val() == "")
				$("#setFocus").val('nacionalidad');
		}else {
			$("#nacionalidad").removeClass("pae-form__input--error");
		}
	} 

	
	if (document.getElementById("obl_inputEmail")!=null && document.getElementById("obl_inputEmail").value == "true") {
		if($("#email").val() == null || $("#email").val() == ""){
			error=1;
			$("#email").addClass("pae-form__input--error");
			if($("#setFocus").val() == "") 
				$("#setFocus").val('email');
		}else{
			$("#email").removeClass("pae-form__input--error");
		    expr = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
		    if (!expr.test(document.getElementById("email").value)){
				error=1;
				$("#email").addClass("pae-form__input--error");
				if($("#setFocus").val() == "") 
					$("#setFocus").val('email');
			}
		}
	} else {
		$("#email").removeClass("pae-form__input--error");
	    expr = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	    if (!expr.test(document.getElementById("email").value)){
			error=1;
			$("#email").addClass("pae-form__input--error");
			if($("#setFocus").val() == "") 
				$("#setFocus").val('email');
		}
	}

		

	if (document.getElementById("obl_inputTelefono")!=null && document.getElementById("obl_inputTelefono").value == "true") {
		if(document.getElementById("telefono1").value == null  || document.getElementById("telefono1").value==""){
			error=1;
			document.getElementById("telefono1").className += " pae-form__input--error";
			if($("#setFocus").val() == "")
				$("#setFocus").val('telefono1');
		}else {
			document.getElementById("telefono1").className = "pae-form__input";
			// Validacion formato tfno
			if(!validarTelefono(document.getElementById("telefono1"))){
				error=1;
				document.getElementById("telefono1").className += " pae-form__input--error";
				if($("#setFocus").val() == "")
					$("#setFocus").val('telefono1');
			}
		}
	} else {
		document.getElementById("telefono1").className = "pae-form__input";
		// Validacion formato tfno
		if(!validarTelefono(document.getElementById("telefono1"))){
			error=1;
			document.getElementById("telefono1").className += " pae-form__input--error";
			if($("#setFocus").val() == "")
				$("#setFocus").val('telefono1');
		}
	}

	if (document.getElementById("telefono2").value != "" || document.getElementById("telefono2").value != null){
		if(!validarTelefono(document.getElementById("telefono2"))){
			document.getElementById("telefono2").className += " pae-form__input--error";
			error=1;
			if($("#setFocus").val() == "")
				$("#setFocus").val('telefono2');
		}else if(isNaN(document.getElementById("telefono2").value)){
			error=1;
			document.getElementById("telefono2").className += " pae-form__input--error";
			if($("#setFocus").val() == "")
				$("#setFocus").val('telefono2');
		} 
		else {
			document.getElementById("telefono2").className = "pae-form__input";
		}	
	}

	
	if ($("#obl_inputCalleDomicilio")!=null && $("#obl_inputCalleDomicilio").val() == "true") {

		var r = new RegExp("[^a-zA-Z0-9áéíóúÁÉÍÓàèìòùÀÈÌÒÙâêîôûÂÊÎÔÛ/ÚÑñüÜç¿!¡'Ç\"%·´>_.,;:\\?\\-\\(\\)\\ª\\º\\s\\+\\*]");  
		var a1 = $("#calleDomicilio").val().match(r)              // Find matches.  
		
		if(($("#calleDomicilio").val() == null  || $("#calleDomicilio").val()=="") || (a1 != null)){
			error=1;
			$("#calleDomicilio").addClass("pae-form__input--error");
			if($("#setFocus").val() == "")
				$("#setFocus").val('calleDomicilio');
		}	else {
			$("#calleDomicilio").removeClass("pae-form__input--error");
		}
	}	
	
	if ($("#obl_inputMunicipioDomicilio")!=null && $("#obl_inputMunicipioDomicilio").val() == "true") {
		if($("#municipioDomicilio").val() == null  || $("#municipioDomicilio").val()==""){
			error=1;
			$("#municipioDomicilio").addClass("pae-form__input--error");
			if($("#setFocus").val() == "")
				$("#setFocus").val('municipioDomicilio');
		}else {
			$("#municipioDomicilio").removeClass("pae-form__input--error");
		}
	}	

	if (($("#obl_inputProvinciaDomicilio")!=null && $("#obl_inputProvinciaDomicilio").val() == "true")) { //1 == ESPAÑA
		if(($("#provinciaDomicilio").val() == null  || $("#provinciaDomicilio").value=="" || document.getElementById("provinciaDomicilio").selectedIndex==0) && (document.getElementById("pais").value == 1)){
			error=1;
			$("#provinciaDomicilio").addClass("pae-form__input--error");
			if($("#setFocus").val() == "")
				$("#setFocus").val('provinciaDomicilio');
		}else {
			$("#provinciaDomicilio").removeClass("pae-form__input--error");
		}		
	}

	if ($("#obl_pais")!=null && $("#obl_pais").val() == "true") {
		if(document.getElementById("pais").value == null  || document.getElementById("pais").value=="" || document.getElementById("pais").selectedIndex==0){
			error=1;
			document.getElementById("pais").className += " pae-form__input--error";
			if($("#setFocus").val() == "")
				$("#setFocus").val('pais');
		}else {
			document.getElementById("pais").className = "pae-form__input";
		}
	}	


		errorCP = error;
		if(document.getElementById("pais").value==1){ // Solo obligatorio y se valida cuando el pais es españa
		if(document.getElementById("codigoPostalDomicilio") && document.getElementById("pais").value==1) {			
			if (document.getElementById("obl_inputCodigoPostalDomicilio")!=null && document.getElementById("obl_inputCodigoPostalDomicilio").value == "true") {
				if(document.getElementById("codigoPostalDomicilio").value == null  || document.getElementById("codigoPostalDomicilio").value=="" || isNaN(document.getElementById("codigoPostalDomicilio").value)){
					error=1;
					document.getElementById("codigoPostalDomicilio").className += " pae-form__input--error";
					if($("#setFocus").val() == "")
						$("#setFocus").val('codigoPostalDomicilio');
				}else{
					//validacion formato del codigo postal
					if(!validarCodigoPostal(document.getElementById("codigoPostalDomicilio"))){
				    	error=1;
				    	document.getElementById("codigoPostalDomicilio").className += " pae-form__input--error";
				    	if($("#setFocus").val() == "")
				    		$("#setFocus").val('codigoPostalDomicilio');
				    }else {
				    	document.getElementById("codigoPostalDomicilio").className = "pae-form__input";
				    }	
										
					var provincia = document.getElementById("provinciaDomicilio").value; 
					var cp = document.getElementById("codigoPostalDomicilio").value;
		  
					 $.ajax({
					        url: "<%=request.getContextPath()%>/secure/validarCP",
					    	type: 'POST',
					        data:  "provincia="+provincia+"&CP="+cp,
					        async : false,
						    contentType: false,
						    cache: false,
						    processData:false,
						    complete: function(data, textStatus, jqXHR){
						    	var str=data.responseText;
								var resto = 16;
								var start=str.indexOf("<div id=\"error\">");
								
								if(start == -1){
									start=str.indexOf("<DIV id=error>");
									if(start != -1)
									 	resto = 14;
								}
		
								var strFin=str.substr(start);
								var fin=strFin.indexOf("</div>");
								
								if(fin==-1)
								    fin=strFin.indexOf("</DIV>");						 
							
								var errores = data.responseText.substr(start+resto, fin-resto); 
								
								if(errores!=""){
									error=1;
									$("#codigoPostalDomicilio").addClass("pae-form__input--error");
									if($("#setFocus").val() == "")
										$("#setFocus").val('codigoPostalDomicilio');
								}
						    }   
					   }); 
				}	
			} else {
				//validacion formato del codigo postal
				if(!validarCodigoPostal(document.getElementById("codigoPostalDomicilio"))){
			    	error=1;
			    	document.getElementById("codigoPostalDomicilio").className += " pae-form__input--error";
			    	if($("#setFocus").val() == "")
			    		$("#setFocus").val('codigoPostalDomicilio');
			    }else {
			    	document.getElementById("codigoPostalDomicilio").className = "pae-form__input";
			    }	
									
				var provincia = document.getElementById("provinciaDomicilio").value; 
				var cp = document.getElementById("codigoPostalDomicilio").value;
	  
				 $.ajax({
				        url: "<%=request.getContextPath()%>/secure/validarCP",
				    	type: 'POST',
				        data:  "provincia="+provincia+"&CP="+cp,
				        async : false,
					    contentType: false,
					    cache: false,
					    processData:false,
					    complete: function(data, textStatus, jqXHR){
					    	var str=data.responseText;
							var resto = 16;
							var start=str.indexOf("<div id=\"error\">");
							
							if(start == -1){
								start=str.indexOf("<DIV id=error>");
								if(start != -1)
								 	resto = 14;
							}
	
							var strFin=str.substr(start);
							var fin=strFin.indexOf("</div>");
							
							if(fin==-1)
							    fin=strFin.indexOf("</DIV>");						 
						
							var errores = data.responseText.substr(start+resto, fin-resto); 
							
							if(errores!=""){
								error=1;
								$("#codigoPostalDomicilio").addClass("pae-form__input--error");
								if($("#setFocus").val() == "")
									$("#setFocus").val('codigoPostalDomicilio');
							}
					    }   
				   }); 

			}

		} else {
			error=1;
			document.getElementById("codigoPostalDomicilio").className += " pae-form__input--error";
			if($("#setFocus").val() == "")
				$("#setFocus").val('codigoPostalDomicilio');
		}
		}else{
			document.getElementById("codigoPostalDomicilio").className = "pae-form__input";
		}

	
	if(error == 1) {
		document.getElementById("boxSolicitante").className = "pae-box pae-box--error";
		if ($("#setFocus").val() != "") {
			document.getElementById($("#setFocus").val()).focus();
		}
		$("#setFocus").val("");
		return false;
	}else {
		document.getElementById("boxSolicitante").className = "pae-box";
		return true;
	}
	
}

function validarDatosConvocatoria(error) {

	if (document.getElementById("obl_especialidad")!=null && document.getElementById("obl_especialidad").value == "true") {
		if($("#especialidad").val() == null || $("#especialidad").val() == "" || $("#especialidad").val() == 0) {
			error=1;
			$("#especialidad").addClass("pae-form__input--error");
			if($("#setFocus").val() == "")
			$("#setFocus").val('especialidad');
		} else {
			$("#especialidad").removeClass("pae-form__input--error");
		}
	}

	if (document.getElementById("obl_provinciaExamen")!=null && document.getElementById("obl_provinciaExamen").value == "true") {
		if(document.getElementById("provinciaExamen").value == null  || document.getElementById("provinciaExamen").value=="74" || document.getElementById("provinciaExamen").value=="-" || document.getElementById("provinciaExamen").selectedIndex==0){
			error=1;
			document.getElementById("provinciaExamen").className += " pae-form__input--error";
			if($("#setFocus").val() == "")
				$("#setFocus").val('provinciaExamen');
		} else {
			$("#provinciaExamen").removeClass("pae-form__input--error");
		}
	}
	
	if(error == 1) {
		document.getElementById("boxConvocatoria").className = "pae-box pae-box--error";
		if ($("#setFocus").val() != "") {
			document.getElementById($("#setFocus").val()).focus();
		}
		$("#setFocus").val("");
		return false;
	}else {
		document.getElementById("boxConvocatoria").className = "pae-box";
		document.getElementById("provinciaExamen").className = "pae-form__input";
		return true;
	}

}



function validarDatosDiscapacidad(error){
	var iteracion = document.getElementsByName("checkbox-exemption");
	var discapacidadChecked = false;
	for(var i=1;i<=iteracion.length;i++) {
		if(document.getElementById("checkbox-exemption-"+i).value == "1" && 
				document.getElementById("checkbox-exemption-"+i).checked==true){//DISCAPACIDAD
			discapacidadChecked = true;
			break;
		}
	}
	if (discapacidadChecked == true) {
		if (document.getElementById("obl_porcentajeDiscapacidad")!=null && document.getElementById("obl_porcentajeDiscapacidad").value == "true") {
			if($("#porcentajeDiscapacidad").val() == null || $("#porcentajeDiscapacidad").val() == "") {
				error=1;
				$("#porcentajeDiscapacidad").addClass("pae-form__input--error");
				if($("#setFocus").val() == "")
				$("#setFocus").val('porcentajeDiscapacidad');
			} else {
				$("#porcentajeDiscapacidad").removeClass("pae-form__input--error");
			}
		}

		/* if(document.getElementById("porcentajeDiscapacidad").value >= 33) {
			if(document.getElementById("idComunidadDDExento").value == null  || document.getElementById("idComunidadDDExento").value=="-" || document.getElementById("idComunidadDDExento").selectedIndex==0){
				error=1;
				document.getElementById("idComunidadDDExento").className += " pae-form__input--error";
				if($("#setFocus").val() == "")
					$("#setFocus").val('idComunidadDDExento');
				document.getElementById("advisor-disabilities").className = "pae-form__cell";
			}
		} */


		if (document.getElementById("obl_detalleDiscapacidad")!=null && document.getElementById("obl_detalleDiscapacidad").value == "true") {
			if($("#detalleDiscapacidad").val() == null || $("#detalleDiscapacidad").val() == "" || document.getElementById("detalleDiscapacidad").selectedIndex==0) {
				error=1;
				$("#detalleDiscapacidad").addClass("pae-form__input--error");
				if($("#setFocus").val() == "")
				$("#setFocus").val('detalleDiscapacidad');
			} else {
				$("#detalleDiscapacidad").removeClass("pae-form__input--error");
			}
		}

		// Si esta habilitado el combo CCAA de discapacidad (21) se marca como obligatorio
		if(($("#idComunidadDDExento").prop("disabled") == false) &&
				($("#idComunidadDDExento").val() == null  || $("#idComunidadDDExento").val()=="" || document.getElementById("idComunidadDDExento").selectedIndex==0)){
			error=1;
			$("#idComunidadDDExento").addClass("pae-form__input--error");
			if($("#setFocus").val() == "")
			$("#setFocus").val('idComunidadDDExento');
		}else{
			$("#idComunidadDDExento").removeClass("pae-form__input--error");
		}	

		if(error == 1) {
			if ($("#setFocus").val() != "") {
				document.getElementById($("#setFocus").val()).focus();
			}
			$("#setFocus").val("");
			return false;
		}else {
			document.getElementById("idComunidadDDExento").className = "pae-form__input";
			document.getElementById("advisor-disabilities").className = "pae-form__cell pae-hidden";
			return true;
		}
	}else{
		$("#idComunidadDDExento").removeClass("pae-form__input--error");
		$("#porcentajeDiscapacidad").removeClass("pae-form__input--error");
		$("#detalleDiscapacidad").removeClass("pae-form__input--error");
		return true;
	}
}

	var busquedaJustResult = null;
	function cargaJustificantesAjax(idConvocatoria) {
	
		 // capta los valores introducidos en formulario de nif y numero de justficante presencial
	     var numJust = $('#numJustInput').val();
		
		// llamada AJAX
		$.ajax({
	        type: "POST",
	        url: "<%=request.getContextPath()%>/secure/cargaJustificanteAjax",
	        data: "numJustificantePresencial="+numJust+"&idConvocatoria="+idConvocatoria,
	        dataType: 'json',
	        success: function(response){
	
				// sino ha encontrado el action registros muestro el error cargado en dicho action 
				if (response.length==1 && response[0].error!=null && response[0].error!=""){
					alert(response[0].error);
				} else {
					// limpio registros de la tabla de resultados
					$("tr[name=fila]").remove();
					// listo los registros de la busqueda en una tabla dentro de la ventana modal
					var registrosTabla = "";
					for (var i = 0; i < response.length; i++) {
						registro = 
							"<tr class=\"pae-table__row\" name=\"fila\"><td class=\"pae-table__cell-body\">"+response[i].nif+
							"</td><td class=\"pae-table__cell-body\">"+response[i].nombre+
							"</td><td class=\"pae-table__cell-body\">"+response[i].primerApellido+
							"</td><td class=\"pae-table__cell-body\">"+response[i].segundoApellido+
							"</td><td class=\"pae-table__cell-body\"><input type=\"radio\" name=\"justificante\" id=\"radio-" + i + "\"></td></tr>"
						registrosTabla = registrosTabla + registro;
					}
					$("#numJustTexto").text(response[0].numJustificante);
					$("#tablaJustificantes").append(registrosTabla);
					$("#radio-0").prop("checked",true);
					// carga de ventana modal
					abreVentanaJustificantes();
					// seteo de la respuesta json en una variable global javascript como objeto
					busquedaJustResult = response;
				}
			},
	        error: function(xhr, status, thrownError){
	            alert('Error en la búsqueda');
	//          alert('Error: ' + xhr.status + xhr.statusText);
	//          alert(status);
	//   		alert(thrownError);
	        }
	    });
	}

	function abreVentanaJustificantes() {
	    $('#dialogTablaJustificantes').dialog({
	        title: "Listado de solicitudes presenciales",
	        draggable: !1,
	        modal: !0,
	        closeOnEscape: !0,
	        resizable: !0,
	        dialogClass: "",
	        height: "auto",
	        width: 942,
	    })
	}

	var timeout = null;
	function cargaPistola(idConvocatoria, event) {
		// limpia timeout
		clearTimeout(timeout);

		// si el caracter es un arterisco de pistola, oculta el input principal y se tapa con el auxiliar
		// asi no se muestra la ristra de caracteres que genera la pistola al disparar sobre el codigo
		if(event.keyCode == 16){
			$('#numJustInput').css("width", "0");
			$('#numJustInput').css("height", "0");
			$('#numJustInput').css("margin", "0");
			$('#numJustInput').css("padding", "0");
			$('#numJustInput').css("border", "none");
			$('#numJustInput').css("display", "block");
			$('#numJustInputHide').show();
		}

		// se ejecutra la siguiente funcion al pasar medio segundo de la escritura de un caracter en el input
	    timeout = setTimeout(function() {
		    // se localiza el numero de justificante dentro de la ristra de caracteres de la pistola
			var textoPistola = document.getElementById('numJustInput').value;
			var textoPistolaArray = textoPistola.split("*",5);
			var numeroJustificante = textoPistolaArray[4];

			// si se localiza entonces se carga en el input principal y se vuelve a ocultar el auxiliar que tapaba
			if (numeroJustificante!=undefined) {
				$('#numJustInput').val(numeroJustificante);
				$('#numJustInput').css("width", "121%");
				$('#numJustInput').css("height", "");
				$('#numJustInput').css("margin", "");
				$('#numJustInput').css("padding", "");
				$('#numJustInput').css("border", "");
				$('#numJustInput').css("display", "");
				$('#numJustInputHide').hide();
				cargaJustificantesAjax(idConvocatoria);
			}
	    }, 500);	// 500 ms
	}

	function rellenaSolicitud() {

		// seleccion de la solicitud presencial seleccionada en el radio-button
		var i = $('input[name=justificante]:checked').attr('id').split("-")[1];
		
		// abro inputs NRC y subida de documentos
		$('#nrcCiudadano').prop("disabled",false);
		$("input[name = checkbox-exemption]").prop("disabled",false);
		$("#adjDocumentos").show(); 
		$('#selectDocumento').prop("disabled",false);
				
		// cambio del nº de justificante aleatorio de inscripcion online al mismo de la solicitud presencial
		$("#numSolicitud").val($("#numJustInput").val());
		// ademas lo muestro en el encabezado de la interfaz
		$("#numSolFH").text($("#numJustInput").val());
		
		// datos personales
		$("#nifCiudadano").val(busquedaJustResult[i].nif);
		$("input[name=primerApellido]").val(busquedaJustResult[i].primerApellido);
		$("input[name=segundoApellido]").val(busquedaJustResult[i].segundoApellido);
		$("input[name=nombre]").val(busquedaJustResult[i].nombre);
		$("#fechaNacimiento").val(busquedaJustResult[i].fechaNacimiento);

		//checkeo sexo
		if(busquedaJustResult[i].sexo=='H') {
			$("#hombre").prop("checked",true);
		} else if (busquedaJustResult[i].sexo=='M')  {
			$("#mujer").prop("checked",true);
		}
		
		$("#nacionalidad").val(busquedaJustResult[i].nacionalidad);
		$("#email").val(busquedaJustResult[i].correoElectronico);
		$("#telefono1").val(busquedaJustResult[i].telefono1);
		
		// muestra telefono 2
		$("#telefono2").parent().removeClass("pae-form__cell--hide");
		$("a[name = linkTelefono2]").parent().addClass("pae-form__cell--hide");
		$("a[name = eliminarCampo]").parent().hide();
        $("#telefono2").val(busquedaJustResult[i].telefonoAux);

		
		$("#calleDomicilio").val(busquedaJustResult[i].calleDomicilio);
		$("#codigoPostalDomicilio").val(busquedaJustResult[i].codPostalDomicilio);
		$("#municipioDomicilio").val(busquedaJustResult[i].municipioDomicilio);

		// seleccion combo de provincia
		var provincia = busquedaJustResult[i].provinciaDomicilio;
		var valueProvincia = 0;
		for (var j = 1; j < $("#provinciaDomicilio option").size() ; j++) {
			var provinciaPunteroNombre = $("#provinciaDomicilio option")[j].text;
			if (provincia == provinciaPunteroNombre) {
				valueProvincia = $("#provinciaDomicilio option")[j].value;
			}
		}
		$("#provinciaDomicilio").val(valueProvincia).change();
		

		// seleccion combo de pais
		var pais = busquedaJustResult[i].nacionDomicilio;
		var valuePais = 0;
		for (var j = 1; j < $("#pais option").size() ; j++) {
			var paisPunteroNombre = $("#pais option")[j].text;
			if (pais == paisPunteroNombre) {
				valuePais = $("#pais option")[j].value;
				break;
			}
		}
		$("#pais").val(valuePais).change();

		// seleccion combo de especialidad
		var especialidad = busquedaJustResult[i].especialidad;
		var valueEspecialidad = 0;
		for (var j = 1; j < $("#especialidad option").size() ; j++) {
			var especialidadPunteroNombre = $("#especialidad option")[j].text;
			if (especialidad == especialidadPunteroNombre) {
				valueEspecialidad = $("#especialidad option")[j].value;
				break;
			}
		}
		$("#especialidad").val(valueEspecialidad).change();

		// seleccion combo de provincia de examen
		var provinciaExamen = busquedaJustResult[i].provinciaExamen;
		var provinciaExamenValue = 0;
		for (var j = 1; j < $("#provinciaExamen option").size() ; j++) {
			var provinciaExamenPunteroNombre = $("#provinciaExamen option")[j].text;
			if (provinciaExamen == provinciaExamenPunteroNombre) {
				provinciaExamenValue = $("#provinciaExamen option")[j].value;
				break;
			}
		}
		$("#provinciaExamen").val(provinciaExamenValue).change();

		// porcentaje de discapacidad
		var porcentaje = busquedaJustResult[i].porcentajeDiscapacidad;
		if (porcentaje=="0") {
			$("#porcentajeDiscapacidad").val("");
		}  else {
			$("#porcentajeDiscapacidad").val(porcentaje);
		}
	
		//checkeo reserva de discapacidad
		if(busquedaJustResult[i].reservaDiscapacitados=='S') {
			$("#reservation-general").prop("checked",true);
		}
		
		$("#detalleDiscapacidad").val(busquedaJustResult[i].motivoDiscapacidad);

		// seleccion combo de titulos de la convocatoria
		var titulo = busquedaJustResult[i].titulos;
		var tituloValue = 0;
		for (var j = 1; j < $("#titulo option").size() ; j++) {
			var tituloPunteroNombre = $("#titulo option")[j].text;
			if (titulo == tituloPunteroNombre) {
				tituloValue = $("#titulo option")[j].value;
				break;
			}
		}
		$("#titulo").val(tituloValue).change();
			
		// muestra titulo 2
		$("a[name = eliminaAnyadirTitulo]").parent().hide();
		$("#otrosTitulos").parent().show();
		$("a[name = eliminaEliminarTitulo]").parent().hide();
        $("#otrosTitulos").val(busquedaJustResult[i].otrosTitulos);

       	// relleno campos propios
       	for (var j = 0; j < busquedaJustResult[i].solicitudPropiosBean.length; j++) {
       		$('#plantilla'+(j+1)).val(busquedaJustResult[i].solicitudPropiosBean[j].valor);
		}    

		// seleccion check motivo de reduccion
		var idMotivoReduccionTasa = busquedaJustResult[i].idMotivoReduccionTasa.toString();
		var todosMotivos = $("input[name = checkbox-exemption]");
		for (var j = 0; j < todosMotivos.size(); j++) {
			if (idMotivoReduccionTasa == todosMotivos[j].value) {
				todosMotivos[j].click();
				$("#motivo").val(idMotivoReduccionTasa);
				break;
			}
		}

		var idComunidadDD = busquedaJustResult[i].idComunidadDD;
		var idComunidadFN= busquedaJustResult[i].idComunidadFN;
		var tituloFamiliaNumerosa = busquedaJustResult[i].tituloFamiliaNumerosa;		
		if (idMotivoReduccionTasa == 1) {
			$("#idComunidadDDExento").val(idComunidadDD).change();
		} else if (idMotivoReduccionTasa == 3 || idMotivoReduccionTasa == 5 ){
			$("#idComunidadFNExento").val(idComunidadFN).change();
			$("#tituloFNExento").val(tituloFamiliaNumerosa);
		}

		$("input[name = checkbox-exemption]").prop("disabled",true);
		$('#provinciaDomicilio').prop("disabled",true);
		$('#idComunidadDDExento').prop("disabled",true);
		if ($("#importe").val() == 0.00) {
			$('#nrcCiudadano').prop("disabled",true);
		}

		//checkeo consentimiento
		if(busquedaJustResult[i].consentimiento=='NO') {	//Al cambiar  el check consiento a no consiento, invertimos la logica, con false consiente el acceso
			$("#ck-accept-access-db").prop("checked",false);
			$("#idConsentimiento").prop("value",true);
		} 
		else {
			$("#ck-accept-access-db").prop("checked",true);
			$("#idConsentimiento").prop("value",false);
			}
		$("#conforme").prop("checked",true);

		//checkeo consentimiento AEAT
		if(busquedaJustResult[i].consentimiento=='SI') {
			$("#ck-accept-access-db-AEAT").prop("checked",true);
			$("#idConsentimientoAEAT").prop("value",false);
		} 
		else {
			$("#ck-accept-access-db-AEAT").prop("checked",false);
			$("#idConsentimientoAEAT").prop("value",true);
			}
		$("#conformeAEAT").prop("checked",true);
		
		$("#motivoOposicion2").val(busquedaJustResult[i].motivoOposicion);
		
		
		$('#dialogTablaJustificantes').dialog("close");
	}

</script>

<head>
  <!-- Etiquetas META-->
  <meta charset="UTF-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
</head>
<form:form action="/IPSC/secure/iniciarSolicitud" modelAttribute="solicitudForm" id="formSolicitud" method="post" name="solicitudForm">
		<form:hidden path="id" id="id"/>
		<form:hidden path="action" id="action"/>
		<form:hidden path="numSolicitud" id="numSolicitud" name="altaSolicitud"/>
		<bean:define id="idConvocatoria" name="altaSolicitud" property="idConvocatoria"/>
     	<form:hidden path="idConvocatoria" value="${idConvocatoria}"/>
     	<form:hidden name="altaSolicitud" path="importeOriginal" id="importeOriginal"/>      	
      	<form:hidden path="porcentajeMinDiscapacidad" id="porcentajeMinDiscapacidad"/>
      	<form:hidden path="siglasCentroGestor" id="siglasCentroGestor"/>
      	<form:hidden path="siglasCentroGestorJusticia" id="siglasCentroGestorJusticia"/>
      	<form:hidden path="idConsentimiento" id="idConsentimiento" value="false"/>
      	<form:hidden path="idConsentimientoAEAT" id="idConsentimientoAEAT" value="false"/>
      	<form:hidden path="motivoOposicion" id="motivoOposicion" class="motivoOposicionHidden" name="altaSolicitud"/>
      	<!-- Campos formulario discapacidad -->
      	<form:hidden path="tipoDiscapacidad" id="tipoDisc"/>
      	<form:hidden path="idComunidadDDExento" id="idComunidadDisc"/>
	 	<form:hidden path="porcentajeDiscapacidad" id="porcentajeDisc"/>
	 	<form:hidden path="detalleDiscapacidad" id="detalleDisc"/>
	 	<!-- Fin campos formulario discapacidad -->
      	<input type="hidden" name="altaSolicitud"  id="modelo79007"/>
      	<input type="hidden" name="altaSolicitud"  id="secJudiciales"/>
      	<input type="hidden" name="altaSolicitud"  id="provExamenUnica"/>
      	<input type="hidden" name="altaSolicitud"  id="importe"/>
      	 <% if(!tipoPersona.equals("FH")) { %>
      	<form:hidden name="solicitudForm" path="nif" value="${altaSolicitud.nif}"/>
      	<form:hidden name="solicitudForm" path="nombre" id="nombre" value="${altaSolicitud.nombre}"/>
      	<form:hidden name="solicitudForm" path="primerApellido" id="primerApellido" value="${altaSolicitud.primerApellido}"/>
      	<form:hidden name="solicitudForm" path="segundoApellido" id="segundoApellido" value="${altaSolicitud.segundoApellido}"/>
      	
      	<% } %>
      	
       <input type="hidden" value="<%=siglasCentroGestorJusticia%>" name="siglasCentroGestorJusticia"/>
       <input type="hidden" value="<%=siglasCentroGestor%>" name="siglasCentroGestor"/>
	
        <%@ include file="/jsp/informacionConvocatoria.jsp" %>
        <%@ include file="/jsp/datosFuncionarioHabilitado.jsp" %>
        <%@ include file="/jsp/datosSolicitante.jsp" %>
        <%@ include file="/jsp/datosConvocatoria.jsp" %>
        
        <%@ include file="/jsp/informacionAdicional.jsp" %>
   		
    	</form:form>