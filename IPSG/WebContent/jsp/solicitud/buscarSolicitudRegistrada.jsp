<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.mpr.es/ipsg/bean-tags" prefix="bean" %>
<%@ taglib uri="http://www.mpr.es/ipsg/html-tags" prefix="html" %>
<%@ taglib uri="http://www.mpr.es/ipsg/logic-tags" prefix="logic" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="es.map.ipsg.bean.*" %>
<%@ page import="es.map.ipsg.util.Constantes"%>
<%
String sVieneMenu = (String) request.getAttribute("sVieneMenu"); 
String numPag = (String) request.getAttribute("paginaActual");
String sPerfilUsuario =  request.getAttribute("sPerfilUsuario").toString();
Object mostrarInformacionLotes = request.getAttribute("informacionLotesBean");	
%>
<script type="text/javascript">

function isNumber(evt) {
    evt = (evt) ? evt : window.event;
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
        return false;
    }
    return true;
}

function irPagina(pagTotales)
{
	var actual = document.getElementById("numeroPaginaIr").value;
	var actualInt = parseInt(actual,10);
	
	if(isNaN(actualInt) || actualInt<=0){
		actualInt = 1;
	}
	
	if (document.getElementById("checkTodo").checked) {
		document.getElementById("marcarTodo").value="TodoOk";
	}
	
	document.getElementById("pulsaIr").value = true;	
	document.getElementById("submit").value="Paginar";
	document.getElementById("numeroPaginaIr").value = actualInt;
	document.getElementById("paginaActual").value=actualInt;
	document.getElementById("paginasTotales").value=pagTotales;
	document.forms[0].action = "../spring/buscarSolicitudesRegistradas";
}

function ordena(value,numPag){
	if(value==1){
		document.getElementById("campo").value = "1";
		document.getElementById("direccion").value="up";
	}
	if(value==2){
		document.getElementById("campo").value ="1";
		document.getElementById("direccion").value="down";
	}
	if(value==3){
		document.getElementById("campo").value ="2";
		document.getElementById("direccion").value="up";
	}
	if(value==4){
		document.getElementById("campo").value ="2";
		document.getElementById("direccion").value="down";
	}
	if(value==5){
		document.getElementById("campo").value ="3";
		document.getElementById("direccion").value="up";
	}
	if(value==6	){
		document.getElementById("campo").value ="3";
		document.getElementById("direccion").value="down";
	}
	if(value==7){
		document.getElementById("campo").value ="4";
		document.getElementById("direccion").value="up";
	}
	if(value==8){
		document.getElementById("campo").value ="4";
		document.getElementById("direccion").value="down";
	}
	if(value==9){
		document.getElementById("campo").value ="5";
		document.getElementById("direccion").value="up";
	}
	if(value==10){
		document.getElementById("campo").value ="5";
		document.getElementById("direccion").value="down";
	}
	if(value==11){
		document.getElementById("campo").value ="6";
		document.getElementById("direccion").value="up";
	}
	if(value==12){
		document.getElementById("campo").value ="6";
		document.getElementById("direccion").value="down";
	}
	if(value==13){
		document.getElementById("campo").value ="7";
		document.getElementById("direccion").value="up";
	}
	if(value==14){
		document.getElementById("campo").value ="7";
		document.getElementById("direccion").value="down";
	}
	if(value==15){
		document.getElementById("campo").value ="8";
		document.getElementById("direccion").value="up";
	}
	if(value==16){
		document.getElementById("campo").value ="8";
		document.getElementById("direccion").value="down";
	}	
	if(value==17){
		document.getElementById("campo").value ="9";
		document.getElementById("direccion").value="up";
	}
	if(value==18){
		document.getElementById("campo").value ="9";
		document.getElementById("direccion").value="down";
	}
	if(value==19){
		document.getElementById("campo").value ="10";
		document.getElementById("direccion").value="up";
	}
	if(value==20){
		document.getElementById("campo").value ="10";
		document.getElementById("direccion").value="down";
	}	
	/* if(value==21){
		document.getElementById("campo").value ="11";
		document.getElementById("direccion").value="up";
	}
	if(value==22){
		document.getElementById("campo").value ="11";
		document.getElementById("direccion").value="down";
	}	
	if(value==23){
		document.getElementById("campo").value ="12";
		document.getElementById("direccion").value="up";
	}	
	if(value==24){
		document.getElementById("campo").value ="12";
		document.getElementById("direccion").value="down";
	}	
	if(value==25){
		document.getElementById("campo").value ="13";
		document.getElementById("direccion").value="up";
	}	
	if(value==26){
		document.getElementById("campo").value ="13";
		document.getElementById("direccion").value="down";
	}
	if(value==27){
		document.getElementById("campo").value ="14";
		document.getElementById("direccion").value="up";
	}
	if(value==28){
		document.getElementById("campo").value ="14";
		document.getElementById("direccion").value="down";
	}
	if(value==29){
		document.getElementById("campo").value ="16";
		document.getElementById("direccion").value="up";
	}
	if(value==30){
		document.getElementById("campo").value ="16";
		document.getElementById("direccion").value="down";
	}
	if(value==31){
		document.getElementById("campo").value ="17";
		document.getElementById("direccion").value="up";
	}
	if(value==32){
		document.getElementById("campo").value ="17";
		document.getElementById("direccion").value="down";
	} */
	
	
	document.getElementById("paginaActual").value=numPag;
	document.getElementById("submit").value="Ordenar";
	document.forms[0].action = "../spring/buscarSolicitudesRegistradas";
}

function comprobarEliminar(){
	document.forms[0].action = "../spring/buscarSolicitudesRegistradas";
	return confirm('<spring:message code="field.mensajeEliminar"/>');
}

function busqueda(){
	document.getElementById("paginaActual").value="1";
	document.getElementById("accion").value="BUSCAR";
	document.getElementById("submit").value="Buscar";
	$("#formPadre").attr("action","../spring/buscarSolicitudesRegistradas");
	$( "#formPadre" ).trigger( "submit" );
}

function validarNif(){
	var identificacion = document.getElementById("nif").value;
	if(identificacion != ""){
		result = nifValidate(identificacion);
		if (result == false) {		
			alert(unescape("El NIF es incorrecto."));
			return false;
		}
	}
}
function nifValidate(nif) {
	if (!nif)
		return false;

	if (nif.length == 9) {
		nif = nif.toUpperCase();
		if (/[0-9]{8}[ABCDEFGHJKLMNPQRSTVWXYZ]/.test(nif)) {
			var temp = 'TRWAGMYFPDXBNJZSQVHLCKET';
			var posicion_letra = nif.substring(0, 8) % 23;
			if (nif.charAt(8) != temp.charAt(posicion_letra)) {
				return false;
			}
		} else {
			if (/[KML][0-9]{7}[ABCDEFGHJKLMNPQRSTVWXYZ]/.test(nif)){
				var temp = 'TRWAGMYFPDXBNJZSQVHLCKET';
				var posicion_letra = nif.substring(1, 8) % 23;
				if (nif.charAt(8) != temp.charAt(posicion_letra)) {
					return false;
				}
			} else {
				return false;
			}
		}
	} else {
		return false;
	}
	return true;
}

function paginaActual(paginaActual){
	document.forms[0].action = "../spring/buscarSolicitudesRegistradas";
	document.getElementById("paginaActual").value=paginaActual;
	document.getElementById("submit").value="Paginar";
}

function paginaSiguiente(pag,paginasTotales){
	document.forms[0].action = "../spring/buscarSolicitudesRegistradas";
	var next=pag+1;
	document.getElementById("submit").value="Paginar";
	document.getElementById("paginaActual").value=next;
	document.getElementById("paginasTotales").value=paginasTotales;

	if (document.getElementById("checkTodo").checked) {
		document.getElementById("marcarTodo").value="TodoOk";
	}
		
}

function paginaAnterior(pag,paginasTotales){
	document.forms[0].action = "../spring/buscarSolicitudesRegistradas";
	var next=pag-1;	
	document.getElementById("submit").value="Paginar";
	document.getElementById("paginaActual").value=next;
	document.getElementById("paginasTotales").value=paginasTotales;

	if (document.getElementById("checkTodo").checked) {
		document.getElementById("marcarTodo").value="TodoOk";
	}
}

function ultimaPagina(paginasTotales){
	document.forms[0].action = "../spring/buscarSolicitudesRegistradas";
	document.getElementById("submit").value="Paginar";
	document.getElementById("paginaActual").value=paginasTotales;
	document.getElementById("paginasTotales").value=paginasTotales;
}

function primeraPagina(paginasTotales){
	document.forms[0].action = "../spring/buscarSolicitudesRegistradas";
	document.getElementById("submit").value="Paginar";
	document.getElementById("paginaActual").value="1";
	document.getElementById("paginasTotales").value=paginasTotales;
}
///
function openWindowCentroGestor() {
	document.getElementById("cuerpoEscala").value = "";
	document.getElementById("dsCuerpoEscala").value = "";
	var param = "idCentroGestor";
	var param2 = "dsCentroGestor";
	var extract = new Object();
		ventanaPopup = window.open("../spring/listarCentroGestor?parametro="+param+"&parametro2="+param2, 'miventana', 'width=550, height=550,toolbar=no,directories=no,status=no,linemenubar=no,scrollbars=yes,resizable=no ,modal=yes');
	return false;
}

function openWindowCuerpoEscala() {
	var centro = document.getElementById("idCentroGestor").value;
	if(centro != null && centro != ""){
		var param = "cuerpoEscala";
		var param2 = "dsCuerpoEscala";
		var extract = new Object();
		ventanaPopup = window.open("../spring/listarCuerpoEscala?centro="+centro+"&parametro="+param+"&parametro2="+param2, 'miventana', 'width=550, height=550,toolbar=no,directories=no,status=no,linemenubar=no,scrollbars=yes,resizable=no ,modal=yes');	    		
	}else{
		alert("Debe seleccionar antes un centro gestor");
	}
	return false;
	
}


function openWindowEspecialidad() {
	var centro = document.getElementById("cuerpoEscala").value;
	if(centro != null && centro != ""){
		var param = "idEspecialidad";
		var param2 = "dsEspecialidad";
		var extract = new Object();
		ventanaPopup = window.open("../spring/listarEspecialidad?centro="+centro+"&parametro="+param+"&parametro2="+param2, 'miventana', 'width=550, height=550,toolbar=no,directories=no,status=no,linemenubar=no,scrollbars=yes,resizable=no ,modal=yes');	    		
	}else{
		alert("Debe seleccionar antes un cuerpo y escala");
	}
	return false;
	
}



function comprobarCuerpoEscala(){
	var centro = document.getElementById("idCentroGestor").value;
	if(centro == null || centro == ""){
		alert("Tiene que seleccionar antes un centro gestor");
		document.getElementById("cuerpoEscala").value = "";
	}
}



function comprobarEspecialidad(){
	var cuerpoescala = document.getElementById("cuerpoEscala").value;
	if(cuerpoescala == null || cuerpoescala == ""){
		alert("Debe seleccionar antes un cuerpo y escala");
		document.getElementById("idEspecialidad").value = "";
	}
}






function comprobarBusqueda(){
	document.getElementById("cambios").value="Incorrecto";
}
///
//Al cambiar por el número de Registros por página  
function comprobarRegistros(){
	document.forms[0].action = "../spring/buscarSolicitudesRegistradas";
	document.getElementById("submit").value = "Paginar";
	document.getElementById("cambios").value = "Incorrecto";
	document.getElementById("paginaActual").value="1";
	numeroPagina = document.getElementById("paginasTotales").value;
	pagina = document.getElementById("paginaActual").value;
	numRegistro = document.getElementById("numRegistro").value;
	var marcar = document.getElementById("marcarTodo").value;
	window.location.href = "../spring/buscarSolicitudesRegistradas?paginaActual="+pagina +"&paginasTotales="+numeroPagina +"&numRegistro="+numRegistro+"&marcarTodo="+marcar; 

}
function cambiarCombo ()
{
	document.getElementById("paginaActual").value="1";
}
function comprobarCheckMarcado()
{
	document.forms[0].action = "../spring/buscarSolicitudesRegistradas";
	var bSeleccionado = false;
	var formulario = document.forms[1];
	for (i=0; i < formulario.length; i++)
    {
		if (formulario.elements[i].type=='checkbox' && formulario.elements[i].name =='solicitudesSel')
		{	
			if (formulario.elements[i].checked)
			{
				bSeleccionado = true;
			}
		}
	}
	return bSeleccionado;
}

function exportarVerificar(opcion)
{
	//Se comprueba que exista algún check marcado
	if(comprobarCheckMarcado())
	{	
		if(opcion == 'Verificar')
		{
			var continuar = false;
			if (document.getElementById("marcarTodo").value == 'TodoOk') {				
				var pagTotal = document.getElementById("paginasTotales").value;				
				var numRegPagina = document.getElementById("numRegistro").value;				
				var numRegs = pagTotal * numRegPagina;

				if(numRegs<=50){
					continuar = true;
				}else if(numRegs>999){
					alert("No se puede verificar la fecha de nacimiento de más de 1000 solicitudes. Por favor, restringa el resultado de la búsqueda");
				}else if(confirm("Se va a proceder a verificar la fecha de nacimiento de aproximadamente " + numRegs + " registros. \nEsta operación puede tardar un poco. ¿Desea continuar?")) {
					continuar = true;
				}
			}else{
				continuar = true;
			}	

			if(continuar){	

				var parametros = "";
				var formulario = document.forms[1];
				var contChecked = 0;
				for (var i=0; i < formulario.length; i++)
			    {
					if (formulario.elements[i].type=='checkbox' && formulario.elements[i].name =='solicitudesSel')
					{	
						if (formulario.elements[i].checked)
						{
							if(contChecked == 0){
								parametros+="?sol="+formulario.elements[i].value;
							}else{
								parametros+="&sol="+formulario.elements[i].value;
							}
							contChecked++;
						}
					}
				}
			
				document.getElementById("submit").value="Verificar";
				venntanaPopup = window.open("../spring/verVerificarEdadSolicitantes"+parametros, 'miventana', 'width=650, height=650,toolbar=no,directories=no,status=no,linemenubar=no,scrollbars=yes,resizable=no ,modal=yes');
				document.forms[0].action = "../spring/buscarSolicitudesRegistradas";
			}
		}
		if(opcion == 'Exportar')
		{
			if (document.getElementById("marcarTodo").value == 'TodoOk') {				
				var pagTotal = document.getElementById("paginasTotales").value;				
				var numRegPagina = document.getElementById("numRegistro").value;				
				var numRegs = pagTotal * numRegPagina;
				
				if(numRegs<=50){
					document.getElementById("submit").value="Exportar";
					document.forms[1].action = "../spring/exportarExcelSolicitudes";
				}else if(confirm("Se va a proceder a la exportación de aproximadamente " + numRegs + " registros. \nEsta operación puede tardar un poco. ¿Desea continuar?")) {
					document.getElementById("submit").value="Exportar";
					document.forms[1].action = "../spring/exportarExcelSolicitudes";
				}
			}else {
				document.getElementById("submit").value="Exportar";
				document.forms[1].action = "../spring/exportarExcelSolicitudes";
			}
		}
		
		if(opcion == 'Lotes'){
			
		// setting form target to a window named 'formresult'
		//form.setAttribute("target", "formresult");
		
		if (document.getElementById("marcarTodo").value == 'TodoOk') {				
			var pagTotal = document.getElementById("paginasTotales").value;				
			var numRegPagina = document.getElementById("numRegistro").value;				
			var numRegs = pagTotal * numRegPagina;
																							
			if(numRegs<=50){
				//document.getElementById("submit").value="Lotes";
				//ventanaPopup = window.open("../spring/consultarLotes", 'formresult', 'width=650, height=280,toolbar=no,directories=no,status=no,linemenubar=yes,scrollbars=yes,resizable=yes ,modal=yes');
				document.forms[1].action = "../spring/consultarLotes";
			}else if(confirm("Se va a proceder a realizar la verificación de aproximadamente " + numRegs + " registros. \nEsta operación puede tardar un poco.")) {
				//document.getElementById("submit").value="Lotes";
				//ventanaPopup = window.open("../spring/consultarLotes", 'formresult', 'width=650, height=280,toolbar=no,directories=no,status=no,linemenubar=yes,scrollbars=yes,resizable=yes ,modal=yes');
				document.forms[1].action = "../spring/consultarLotes";
			}
		}else {
			//document.getElementById("submit").value="Lotes";
			//ventanaPopup = window.open("../spring/consultarLotes", 'formresult', 'width=650, height=280,toolbar=no,directories=no,status=no,linemenubar=yes,scrollbars=yes,resizable=yes ,modal=yes');
			document.forms[1].action = "../spring/consultarLotes";
		}		
	  }
	}else
	{
		alert('<spring:message code="field.solicitudes.mensajeVerificarCheck"/>');
		return false;
	}
	
}
function exportaDocumentos()
{
	if(comprobarCheckMarcado())
	{	
		if (document.getElementById("marcarTodo").value == 'TodoOk') {				
			var pagTotal = document.getElementById("paginasTotales").value;				
			var numRegPagina = document.getElementById("numRegistro").value;				
			var numRegs = pagTotal * numRegPagina;
			
			if (numRegs<=50){
				document.getElementById("submit").value="Exportar";
				ventanaPopup = window.open("../spring/verExportaDocumento?m=TodoOk", 'miventana', 'width=550, height=550,toolbar=no,directories=no,status=no,linemenubar=no,scrollbars=yes,resizable=no ,modal=yes');
				document.forms[1].action = "../spring/buscarSolicitudesRegistradas";
			}else{
				if (confirm("Se va a proceder a la exportación de aproximadamente " + numRegs + " registros. \nEsta operación puede tardar un poco. ¿Desea continuar?")) {
					document.getElementById("submit").value="Exportar";
					ventanaPopup = window.open("../spring/verExportaDocumento?m=TodoOk", 'miventana', 'width=550, height=550,toolbar=no,directories=no,status=no,linemenubar=no,scrollbars=yes,resizable=no ,modal=yes');
					document.forms[1].action = "../spring/buscarSolicitudesRegistradas";
				}
			}
		}else {	
			document.getElementById("submit").value="Exportar";

			var parametros = "";
			var formulario = document.forms[1];
			var contChecked = 0;
			for (var i=0; i < formulario.length; i++)
		    {
				if (!(typeof formulario.elements[i].type === "undefined") && !(typeof formulario.elements[i].name === "undefined") 
						&& formulario.elements[i].type != null && formulario.elements[i].name != null 
						&& formulario.elements[i].type=='checkbox' && formulario.elements[i].name =='solicitudesSel')
				{	
					if (formulario.elements[i].checked)
					{
						if(contChecked == 0){
							parametros+="?sol="+formulario.elements[i].value;
						}else{
							parametros+="&sol="+formulario.elements[i].value;
						}
						contChecked++;
					}
				}
			}
			parametros+="&m=TodoNo";
			ventanaPopup = window.open("../spring/verExportaDocumento"+parametros, 'miventana', 'width=550, height=550,toolbar=no,directories=no,status=no,linemenubar=no,scrollbars=yes,resizable=no ,modal=yes');
			document.forms[0].action = "../spring/buscarSolicitudesRegistradas";
		}
	}else
	{
		alert('<spring:message code="field.solicitudes.mensajeVerificarCheck"/>');
		return false;
	}
		
}

function exportaXmlSigp()
{
	if(comprobarCheckMarcado())
	{	
		if (document.getElementById("marcarTodo").value == 'TodoOk') {				
			var pagTotal = document.getElementById("paginasTotales").value;				
			var numRegPagina = document.getElementById("numRegistro").value;				
			var numRegs = pagTotal * numRegPagina;
			
			if (numRegs<=50){
				document.getElementById("submit").value="Exportar";
				ventanaPopup = window.open("../spring/exportarSigpSolicitudes", 'miventana', 'width=550, height=550,toolbar=no,directories=no,status=no,linemenubar=no,scrollbars=yes,resizable=no ,modal=yes');
			}else if(confirm("Se va a proceder a la exportación de aproximadamente " + numRegs + " registros. \nEsta operación puede tardar un poco. ¿Desea continuar?")) {
				document.getElementById("submit").value="Exportar";
				ventanaPopup = window.open("../spring/exportarSigpSolicitudes", 'miventana', 'width=550, height=550,toolbar=no,directories=no,status=no,linemenubar=no,scrollbars=yes,resizable=no ,modal=yes');
			}
		}else {
			var parametros = "";
			var formulario = document.forms[1];
			var contChecked = 0;
			for (var i=0; i < formulario.length; i++)
		    {
				if (formulario.elements[i].type=='checkbox' && formulario.elements[i].name =='solicitudesSel')
				{	
					if (formulario.elements[i].checked)
					{
						if(contChecked == 0){
							parametros+="?sol="+formulario.elements[i].value;
						}else{
							parametros+="&sol="+formulario.elements[i].value;
						}
						contChecked++;
					}
				}
			}
			
			document.getElementById("submit").value="Exportar";
			ventanaPopup = window.open("../spring/exportarSigpSolicitudes"+parametros, 'miventana', 'width=550, height=550,toolbar=no,directories=no,status=no,linemenubar=no,scrollbars=yes,resizable=no ,modal=yes');	
		}		
	}else
	{
		alert('<spring:message code="field.solicitudes.mensajeVerificarCheck"/>');
		return false;
	}
		
}

function openWindowVerTitulos(id){
	ventanaPopup = window.open("../spring/consultarDatosTitulos?idSolicitud="+id, 'miventana', 'width=550, height=470,toolbar=no,directories=no,status=no,linemenubar=no,scrollbars=yes,resizable=no ,modal=yes');
	ventanaPopup.focus();
	return false;
}

function openWindowVerFecNacimiento(id){
	ventanaPopup = window.open("../spring/verificarFechaNacimiento?idSolicitud="+id, 'miventana', 'width=550, height=470,toolbar=no,directories=no,status=no,linemenubar=no,scrollbars=yes,resizable=no ,modal=yes');
	ventanaPopup.focus();
	return false;
}

function openWindowVerDesempleo(id){
	ventanaPopup = window.open("../spring/consultarDesempleo?idSolicitud="+id, 'miventana', 'width=680, height=500,toolbar=no,directories=no,status=no,linemenubar=no,scrollbars=yes,resizable=no ,modal=yes');
	ventanaPopup.focus();
	return false;
}
function openWindowVerFnumerosa(id){
	ventanaPopup = window.open("../spring/consultarFNumerosa?idSolicitud="+id, 'miventana', 'width=550, height=470,toolbar=no,directories=no,status=no,linemenubar=no,scrollbars=yes,resizable=no ,modal=yes');
	ventanaPopup.focus();
	return false;
}
function openWindowVerDiscapacidad(id){
	ventanaPopup = window.open("../spring/consultarDiscapacidad?idSolicitud="+id, 'miventana', 'width=550, height=470,toolbar=no,directories=no,status=no,linemenubar=no,scrollbars=yes,resizable=no ,modal=yes');
	ventanaPopup.focus();
	return false;
}
function openWindowVerVictimaTerrorismo(id){
	ventanaPopup = window.open("../spring/consultarVictimasTerrorismo?idSolicitud="+id, 'miventana', 'width=550, height=470,toolbar=no,directories=no,status=no,linemenubar=no,scrollbars=yes,resizable=no ,modal=yes');
	ventanaPopup.focus();
	return false;
}

function openWindowInformacionLotes(parametro) {
	 if (parametro != "null") {
		var contextPath = '<%=request.getContextPath()%>';
		var estilos = '<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/pae-modules.css"/>';
		estilos += '  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/pae-general.css"/>';
		var ventanaPopup = window.open("", 'miventana', 'width=550, height=650,toolbar=no,directories=no,status=no,linemenubar=no,scrollbars=yes,resizable=no ,modal=yes');
		var tablaLotes = document.getElementById("informacionLotesBean").innerHTML;
		ventanaPopup.document.write(estilos+tablaLotes);
		ventanaPopup.document.body.style.background = 'white';  
		ventanaPopup.document.title = 'Consulta de Lotes';
	}
}

function openWindowDocumentos(id,tpDoc) {
	var navegador = navigator.appName;
	var extract = new Object();
	if (tpDoc=="AnexoSol" ){
		ventanaPopup = window.open("../spring/documentosAnexoSolicitud?id="+id+"&tpDoc="+tpDoc, 'miventana', 'width=550, height=470,toolbar=no,directories=no,status=no,linemenubar=no,scrollbars=yes,resizable=no ,modal=yes');	    
		
	}else{
		if (tpDoc=="JustificanteSol" ){
			ventanaPopup = window.open("../spring/documentosJustificanteSolicitud?id="+id+"&tpDoc="+tpDoc, 'miventana', 'width=550, height=470,toolbar=no,directories=no,status=no,linemenubar=no,scrollbars=no,resizable=no ,modal=yes');
		}else{
			if (tpDoc=="JustificantePag" ){
				ventanaPopup = window.open("../spring/documentosSinJustificantePago?ent=Solicitudes&idSol="+id, 'miventana', 'width=550, height=470,toolbar=no,directories=no,status=no,linemenubar=no,scrollbars=yes,resizable=no ,modal=yes');
			}
			
		}
	}	
	ventanaPopup.focus();
	return false;
	 
}

function deshabilitar()
{
	document.forms[1].action = "../spring/buscarSolicitudesRegistradas";

	if (document.getElementById("checkTodo") != null) {
		//Marcar todos los check
		if (document.getElementById("marcarTodo").value == 'TodoOk') {
			document.getElementById("checkTodo").checked = true;
	
			
			var formulario = document.forms[0];
			var i;
			document.getElementById("marcarTodo").value="TodoOk";
			for (i=0; i < formulario.length; i++)
		    {
				if (formulario.elements[i].type=='checkbox' && formulario.elements[i].name =='solicitudesSel')
				{
					formulario.elements[i].checked = true;
				}			
			}
		}
	
		//desmarcar todos los check
		if (document.getElementById("marcarTodo").value == 'TodoNo') {
			document.getElementById("checkTodo").checked = false;
	
			
			var formul = document.forms[0];
			var j;
			document.getElementById("marcarTodo").value="TodoNo";
			for (j=0; j < formul.length; j++)
		    {
				if (formul.elements[j].type=='checkbox' && formul.elements[j].name =='solicitudesSel')
				{
					formul.elements[j].checked = false;
				}			
			}
		}
	}
}

function marcarCheck(activa)
{
	document.forms[1].action = "../spring/buscarSolicitudesRegistradas";
	var formulario = document.forms[1];
	var i;
	document.getElementById("marcarTodo").value="TodoOk";
	for (i=0; i < formulario.length; i++)
    {
		if (formulario.elements[i].type=='checkbox' && formulario.elements[i].name =='solicitudesSel')
		{
			formulario.elements[i].checked = activa;
		}
		//if ((i+1) != formulario.length) {
		//	document.getElementById("marcarTodo").value="TodoNo";
		//}
		if (!activa) {
			document.getElementById("marcarTodo").value="TodoNo";
		}
	}	
}

function limpiarCentroGestor(){
var desCentro = document.getElementById("dsCentroGestor").value;
	if(desCentro != ""){
		document.getElementById("idCentroGestor").value = "";
		document.getElementById("cuerpoEscala").value = "";
		document.getElementById("dsCuerpoEscala").value = "";
	}
}

function limpiarCuerpoEscala(){
	document.getElementById("cuerpoEscala").value = "";
	document.getElementById("dsCuerpoEscala").value = "";
}


function limpiarEspecialidad(){
	document.getElementById("idEspecialidad").value = "";
	document.getElementById("dsEspecialidad").value = "";
}

function resetCheck() {
	document.getElementById("marcarTodo").value="TodoNo";
	document.getElementById("checkTodo").checked = false;
	
}

function marcarCheckPaginacion(pag,paginasTotales) {
	
	if (document.getElementById("checkTodo").checked) {
		document.getElementById("marcarTodo").value="TodoOk";
	}
	
	var marcar = document.getElementById("marcarTodo").value;
	
	window.location.href = "../spring/buscarSolicitudesRegistradas?paginaActual="+pag +"&paginasTotales="+paginasTotales +"&llamada="+"Paginar" +"&marcarTodo="+marcar;
}

</script>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 
<title></title>
</head>

<body style="margin-left:0.4em;" onload="deshabilitar();marcarCheck(document.getElementById('checkTodo').checked); openWindowInformacionLotes('<%=mostrarInformacionLotes%>'); ">



<!-- <div style="margin-left:1em; margin-right: 1em;float: left;width: 99%;"> -->

<form:form modelAttribute="buscarSolicitudesForm" action="/IPSG/spring/buscarSolicitudesRegistradas" id="formPadre" onsubmit="return validarNif()" >
	<form:hidden path="paginaActual" id="paginaActual"/>
	<form:hidden path="paginasTotales" id="paginasTotales"/>
	<form:hidden path="submit" id="submit"/>
	<form:hidden path="campo" id="campo"/>
	<form:hidden path="direccion" id="direccion"/>	
	<input type="hidden" name="menu"  id="menu" value="N">	
	<form:hidden path="accion" id="accion"/>
	<form:hidden path="cambios" id="cambios"/>
	<form:hidden path="perfilUsuario" id="perfilUsuario"/>
	<form:hidden path="marcarTodo" id="marcarTodo"/>
	<form:hidden path="pulsaIr" id="pulsaIr" />
<div class="capaAll">


<%-- MAQUETACION INI --%>
<div class="">
	<h1 class="pae-title"><spring:message code="field.solicitudRegistrada.solicitudBuscar"/></h1>
	
	<logic:present name="org.apache.spring.ERROR">
	<div>
		<html:errors/>
	</div>
	<div class="clear"></div>
	</logic:present>
	
	<div data-function="accordion-box" class="pae-box">
		<div class="pae-box__header">
			<h3 class="pae-box__header--title">
				<spring:message code="field.solicitudRegistrada.explicacion" />
			</h3>
		</div>
		<div class="pae-box__body">
			<fieldset>
				<div class="pae-layout">
					<%-- NIF --%>
					<div class="pae-layout__item pae-u-3/12 pae-u-2/12-lap pae-u-1/1-palm">
						<div class="pae-form__cell">
							<span class="pae-form__label pae-form__span-label">
								<spring:message code="field.solicitudRegistrada.nif.mayus" />
							</span>
							<form:input path="nif" id="nif" class="pae-form__input" maxlength="9"/>						
						</div>
					</div>
					<%-- NIF --%>
					<%-- Num Sol --%>
					<div class="pae-layout__item pae-u-3/12 pae-u-2/12-lap pae-u-1/1-palm">
						<div class="pae-form__cell">
							<span class="pae-form__label pae-form__span-label pae-ancho">
								<spring:message code="field.solicitudRegistrada.numeroSolicitud.mayus" />
							</span>
							<form:input path="numSolicitud" class="pae-form__input" maxlength="20" onkeypress="return isNumber(event)"/>						
						</div>
					</div>
					<%-- Num Sol --%>
					
					<%-- Solicitud --%>
					<div class="pae-layout__item pae-u-3/12 pae-u-2/12-lap pae-u-1/1-palm">
						<div class="pae-form__cell">
							<span class="pae-form__label pae-form__span-label">
								<spring:message code="field.solicitudRegistrada.tipoSolicitud.mayus"/>
							</span>
							<form:select path="idTipo" class="pae-form__input" id="idTipo" >
									<form:option value=""></form:option>
									<logic:present name="tipoSolicitud" scope="request">
										<bean:size id="codigo" name="tipoSolicitud" scope="request"/>
											<logic:greaterThan name="tipoSolicitud" value="0">
												<form:options items="${tipoSolicitud}" itemLabel="descripcion" itemValue="id"/>								
											</logic:greaterThan>
									</logic:present>
							</form:select>
						</div>
					</div>
					<%--Solicitud --%>	
					
				</div>
				<div class="pae-layout">
					<%-- Ministerio --%>
					<div class="pae-layout__item pae-u-6/12 pae-u-2/12-lap pae-u-1/1-palm">
						<div class="pae-form__cell">
							<span class="pae-form__label pae-form__span-label">
								<spring:message code="field.ministerioMay"/>
							</span>
							<form:select path="idMinisterio" class="pae-form__input" id="idMinisterio" >
									<form:option value=""></form:option>
									<logic:present name="ministerio" scope="request">
										<bean:size id="numMinisterio" name="ministerio" scope="request"/>
											<logic:greaterThan name="ministerio" value="0">
												<form:options items="${ministerio}" itemLabel="descripcion" itemValue="id"/>								
											</logic:greaterThan>
									</logic:present>
							</form:select>
						</div>
					</div>
					<%--Ministerio --%>
					
					<%if( !sPerfilUsuario.equals(Constantes.ROL_GESTOR) && !sPerfilUsuario.equals(Constantes.ROL_CONSULTOR)) { %>
						<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm">
							<div class="pae-form__cell">
								<span class="pae-form__label pae-form__span-label">
									<spring:message code="field.centroMay" />
								</span>
								<form:input
					 				path="idCentroGestor" class="pae-form__input"
					 				readonly="true" onchange="comprobarBusqueda();comprobar()" 
					 				onclick="limpiarCentroGestor();"></form:input> 
					 				<form:input 
									path="dsCentroGestor" readonly="true" 
					 				class="input_texto_border0 em100"></form:input>
					 				
									
							</div>
						</div>	
						<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm search_btn">
							<input type="button"
									class="pae-form__btn-search" alt="Buscar Centro Gestor"
									onclick="return openWindowCentroGestor()"> 
						</div>
						<div class="pae-layout__item pae-u-7/12 pae-u-2/12-lap pae-u-1/1-palm"></div>
					<% } else { %>
					<div class="pae-layout__item pae-u-6/12 pae-u-2/12-lap pae-u-1/1-palm">
						<div class="pae-form__cell">
							<span class="pae-form__label pae-form__span-label">
								<spring:message code="field.centroMay" />
							</span>
							<!--INI-TRA042-->
							<logic:present name="listaCentrosGestores" scope="request">
								<div class="pae-form__cell">
									<form:select path="idCentroGestor" class="pae-form__input">
										<option value=""></option>
										<form:options items="${listaCentrosGestores}" itemValue="id" itemLabel="descripcion" />
									</form:select>
								</div>
							</logic:present>
							<!--FIN-TRA042-->
						</div>
					</div>				
					<% } %>
					</div>
					<div class="pae-layout">
					<%-- Centro Gestor --%>		
					
					<%-- Cuerpo Escala --%>
					<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm">
						<div class="pae-form__cell">
							<span class="pae-form__label pae-form__span-label">
								<spring:message code="field.cuerpoMay"/>
							</span>
						<form:input path="cuerpoEscala" class="pae-form__input" id="cuerpoEscala" maxlength="5" readonly="true"  onchange="return comprobarCuerpoEscala()" onclick="limpiarCuerpoEscala()" ></form:input>			
						<form:input path="dsCuerpoEscala" id="dsCuerpoEscala" class="input_texto_border0 em100" readonly="true"></form:input>
						</div>
					</div>
					<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm search_btn">
						<input type="button" class="pae-form__btn-search" alt="Buscar Cuerpo Escala" onclick="return openWindowCuerpoEscala()"/> 
					</div>
					<%-- Cuerpo Escala --%>
					
					<%-- Especialidad --%>		
					<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm">
						<div class="pae-form__cell">
							<span class="pae-form__label pae-form__span-label">
								<spring:message code="field.especMay"/>
							</span>
						<form:input path="idEspecialidad" class="pae-form__input" id="idEspecialidad" maxlength="5" readonly="true"  onchange="return comprobarEspecialidad()" onclick="limpiarEspecialidad()" ></form:input>			
						<form:input path="dsEspecialidad" id="dsEspecialidad" class="input_texto_border0 em100" readonly="true"></form:input>
						</div>
					</div>
					<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm search_btn">
						<input type="button" class="pae-form__btn-search" alt="Buscar Centro Gestor" onclick="return openWindowEspecialidad()"/> 
					</div>
					<%-- Especialidad --%>	
					</div>
					<div class="pae-layout">
					<%-- Tipo Acceso --%>
					<div class="pae-layout__item pae-u-3/12 pae-u-2/12-lap pae-u-1/1-palm">
						<div class="pae-form__cell">
							<span class="pae-form__label pae-form__span-label">
								<spring:message code="field.formasAcceso.tipoAccesoMay"/>
							</span>
							<form:select path="idTipoAcceso" class="pae-form__input" id="idTipoAcceso" >
									<form:option value=""></form:option>
									<logic:present name="tiposAcceso" scope="request">
										<bean:size id="numtiposAcceso" name="tiposAcceso" scope="request"/>
											<logic:greaterThan name="numtiposAcceso" value="0">
												<form:options items="${tiposAcceso}" itemLabel="descripcion" itemValue="id"/>								
											</logic:greaterThan>
									</logic:present>				
							</form:select>
						</div>
					</div>
					<%-- Tipo Acceso --%>
					<%-- Modelo --%>
					<div class="pae-layout__item pae-u-3/12 pae-u-2/12-lap pae-u-1/1-palm">
						<div class="pae-form__cell">
							<span class="pae-form__label pae-form__span-label">
								<spring:message code="field.modeloMay"/>
							</span>
							<form:select path="idModelo" class="pae-form__input" id="idModelo" >	
									<logic:present name="listaModelosBean" scope="request">
										<bean:size id="numModelos" name="listaModelosBean" scope="request"/>
											<logic:greaterThan name="listaModelosBean" value="0">
												<option value="">   </option>
												<form:options items="${listaModelosBean}" itemLabel="numModelo" itemValue="id"/>								
											</logic:greaterThan>
									</logic:present>
							</form:select>
						</div>
					</div>
					<%-- Modelo --%>
					
					<!-- Ini Fecha Desde -->
					<div class="pae-layout__item pae-u-3/12 pae-u-4/12-lap pae-u-2/3-palm">
						<div class="pae-form__cell">
							<label for="fechaNacimiento" class="pae-form__label">
								<span class="pae-form__label--text">
									<spring:message code="field.solicitudRegistrada.fechaDesde"/>
								</span>
							</label>
							<div class="pae-form__box-datepicker">
								<form:input type="text" path="fechaDesde"
									name="fechaDesde" data-function="function-datepicker"
									placeholder="dd/mm/aaaa"  class="pae-form__input"/>
							</div>
						</div>
					</div>					
					<!-- Fin Fecha Desde -->
					<!-- Ini Fecha Hasta -->				
					<div class="pae-layout__item pae-u-3/12 pae-u-4/12-lap pae-u-2/3-palm">
						<div class="pae-form__cell">
							<label for="fechaNacimiento" class="pae-form__label">
								<span class="pae-form__label--text">
									<spring:message code="field.solicitudRegistrada.fechaHasta"/>
								</span>
							</label>
							<div class="pae-form__box-datepicker">
								<form:input type="text" path="fechaHasta"
									name="fechaHasta" data-function="function-datepicker"
									placeholder="dd/mm/aaaa" class="pae-form__input"/>
							</div>
						</div>
					</div>					
					<!-- Fin Fecha Hasta -->
					<!-- Solicita Exención -->
					<div class="pae-layout__item pae-u-3/12 pae-u-2/12-lap pae-u-1/1-palm">
						<div class="pae-form__cell">
							<span class="pae-form__label pae-form__span-label">
								<spring:message code="field.solicitudRegistrada.solicitaExcencion.mayus"/>
							</span>
							<form:select path="solExencion" class="pae-form__input" id="solExencion" >
									<form:option value=""></form:option>
									<form:option value="N">No</form:option>		
									<form:option value="S">Sí</form:option>											
							</form:select>
						</div>
					</div>
					<!-- Fin Solicita Exención -->
					</div>
					<%-- Boton --%>
					<div class="pae-layout">
						<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm pd-left-89">
							<div class="pae-box-buttons">
								<input type="submit" name="buscar" value="Buscar" onclick="busqueda()" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline"/>		
							</div>
						</div>
					</div>
				</div>
			</fieldset>
		</div>
	</div>
</div>
<%-- MAQUETACION FIN --%>

<%	
	
	if (sVieneMenu.equals("N"))
	{
%>
	<logic:present name="solicitudes" scope="request">	
		<bean:size id="numResultados" name="solicitudes" scope="request"/>
		<logic:greaterThan name="numResultados" value="0">
		<div class="filaBtn">
			<div class="pae-box-buttons">
		<%
		//Botones de 'Verificar edades' y 'Exportar a Excel'
		if(sPerfilUsuario.equals(Constantes.ROL_CONSULTOR) 	//Muestra Exportar
	   ||  sPerfilUsuario.equals(Constantes.ROL_SOPORTE) 	//Muestra Exportar
	   ||  sPerfilUsuario.equals(Constantes.ROL_GESTOR) 		//Muestra Exportar y Verificar
	   ||  sPerfilUsuario.equals(Constantes.ROL_ADMINISTRADOR))//Muestra Exportar y Verificar
		{%>

				
				<%if(sPerfilUsuario.equals(Constantes.ROL_GESTOR) 
				  || sPerfilUsuario.equals(Constantes.ROL_ADMINISTRADOR))
				{%>
					<input type="submit" name="lotes" value="Consulta lotes" onclick="return exportarVerificar('Lotes');" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline pae-separation">					
					<input type="button" name="verificar" value="Verificar edades" onclick="return exportarVerificar('Verificar');" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline pae-separation">
				<%}%>
				<input type="submit" name="exportar" value="Exportar Excel" onclick="return exportarVerificar('Exportar');" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline pae-separation">
				<input type="button" name="exportar" value="Exportar Documentos" onclick="return exportaDocumentos();" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline pae-separation">				
				<input type="button" name="exportar" value="Exportar SIGP" onclick="return exportaXmlSigp();" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline pae-separation">
				
			<!-- Fin Verificar y Exportar Excel-->
		<%} %>	
			</div>
		</div>
		<%-- <!-- Advertencia SEPE a AEAT INI -->
		<div class="pae-layout__item pae-u-12/12 pae-u-2/12-lap pae-u-1/1-palm pae-pv1"> 
			<div class="pae-form__cell">
				<span class="pae-form__label pae-form__span-label pae-orange">
					<spring:message code="field.solicitudRegistrada.advertencia.AEAT"/>
				</span>
			</div>
		</div>
		<!-- Advertencia SEPE a AEAT FIN --> --%>
		
		<div class="scrollmenu">
		<table id="paetable" class="pae-table pae-table--margin">
			<caption class="hiddenAccesible">Tabla de solicitudes registradas</caption>
			<thead class="pae-table__header">
			<tr class="pae-table__row"><!-- Nombres de cabecera de la tabla de resultados -->
				<%if(sPerfilUsuario.equals(Constantes.ROL_CONSULTOR) 
						 ||  sPerfilUsuario.equals(Constantes.ROL_SOPORTE)
						 ||  sPerfilUsuario.equals(Constantes.ROL_GESTOR) 
						 ||  sPerfilUsuario.equals(Constantes.ROL_ADMINISTRADOR))
				{%>
				<th data-col="col5" class="pae-table__cell-header">
					<input type="checkbox" name="check" property="check" id="checkTodo" data-function="checkbox-custom-input" class="pae-form__custom-check" onclick="marcarCheck(this.checked)">
					<label for="checkTodo" data-function="checkbox-custom" class="pae-form__custom-check-label pae-form__custom-check-label--option"/>
				</th>
				<%} %>
				<th class="pae-table__cell-header">
					<table class="tabla_resultadosInterno">
						<tbody>
							<tr>											
								<th>
									<div class="titulo_izq_tabla">									
										<span class="pae-table__txt-title">
											<spring:message code="field.solicitudRegistrada.numSol.mayus"/>
										</span>	
									</div>
								</th>
								<th>											
									<div class="titulo_der_tabla">
										<input type="image" 
											src="../images/order_desc.png" value="accion" alt="Ascendente" 
											onclick="ordena(1,<%=numPag %>)"><input type="image" 
											src="../images/order_asc.png" value="accion" alt="Ordenar" onclick="ordena(2,<%=numPag %>)">
									</div>
								</th>
							</tr>
						</tbody>
					</table>
				</th>	
				<th class="pae-table__cell-header">
					<table class="tabla_resultadosInterno">
						<tbody>
							<tr>											
								<th>
									<div class="titulo_izq_tabla">									
										<span class="pae-table__txt-title">
											<spring:message code="field.solicitudRegistrada.nif.mayus"/>
										</span>	
									</div>
								</th>
								<th>											
									<div class="titulo_der_tabla">
										<input type="image" 
											src="../images/order_desc.png" value="accion" alt="Ascendente" 
											onclick="ordena(3,<%=numPag %>)"><input type="image" 
											src="../images/order_asc.png" value="accion" alt="Descendente" onclick="ordena(4,<%=numPag %>)">
									</div>
								</th>
							</tr>
						</tbody>
					</table>
				</th>	
				<th class="pae-table__cell-header">
					<table class="tabla_resultadosInterno">
						<tbody>
							<tr>											
								<th>
									<div class="titulo_izq_tabla">									
										<span class="pae-table__txt-title">
											<spring:message code="field.solicitudRegistrada.nombre.mayus"/>
										</span>	
									</div>
								</th>
								<th>											
									<div class="titulo_der_tabla">
										<input type="image" 
											src="../images/order_desc.png" value="accion" alt="Ascendente" 
											onclick="ordena(5,<%=numPag %>)"><input type="image" 
											src="../images/order_asc.png" value="accion" alt="Descendente" onclick="ordena(6,<%=numPag %>)">
									</div>
								</th>
							</tr>
						</tbody>
					</table>
				</th>
				<th class="pae-table__cell-header">
					<table class="tabla_resultadosInterno">
						<tbody>
							<tr>											
								<th>
									<div class="titulo_izq_tabla">									
										<span class="pae-table__txt-title">
											<spring:message code="field.solicitudRegistrada.primerApellido.mayus"/>
										</span>	
									</div>
								</th>
								<th>											
									<div class="titulo_der_tabla">
										<input type="image" 
											src="../images/order_desc.png" value="accion" alt="Ascendente" 
											onclick="ordena(7,<%=numPag %>)"><input type="image" 
											src="../images/order_asc.png" value="accion" alt="Descendente" onclick="ordena(8,<%=numPag %>)">
									</div>
								</th>
							</tr>
						</tbody>
					</table>
				</th>
				<th class="pae-table__cell-header">
					<table class="tabla_resultadosInterno">
						<tbody>
							<tr>											
								<th>
									<div class="titulo_izq_tabla">									
										<span class="pae-table__txt-title">
											<spring:message code="field.solicitudRegistrada.segundoApellido.mayus"/>
										</span>	
									</div>
								</th>
								<th>											
									<div class="titulo_der_tabla">
										<input type="image" 
											src="../images/order_desc.png" value="accion" alt="Ascendente" 
											onclick="ordena(9,<%=numPag %>)"><input type="image" 
											src="../images/order_asc.png" value="accion" alt="Descendente" onclick="ordena(10,<%=numPag %>)">
									</div>
								</th>
							</tr>
						</tbody>
					</table>
				</th>					
				<th class="pae-table__cell-header">
					<table class="tabla_resultadosInterno">
						<tbody>
							<tr>											
								<th>
									<div class="titulo_izq_tabla">									
										<span class="pae-table__txt-title">
											<spring:message code="field.solicitudRegistrada.ejercicio.mayus"/>
										</span>	
									</div>
								</th>
								<th>											
									<div class="titulo_der_tabla">
										<input type="image" 
											src="../images/order_desc.png" value="accion" alt="Ascendente" 
											onclick="ordena(11,<%=numPag %>)"><input type="image" 
											src="../images/order_asc.png" value="accion" alt="Descendente" onclick="ordena(12,<%=numPag %>)">
									</div>
								</th>
							</tr>
						</tbody>
					</table>
				</th>	
				<th class="pae-table__cell-header">
					<table class="tabla_resultadosInterno">
						<tbody>
							<tr>											
								<th>
									<div class="titulo_izq_tabla">									
										<span class="pae-table__txt-title">
											<spring:message code="field.solicitudRegistrada.centroGestor.mayus"/>
										</span>	
									</div>
								</th>
								<th>											
									<div class="titulo_der_tabla">
										<input type="image" 
											src="../images/order_desc.png" value="accion" alt="Ascendente" 
											onclick="ordena(13,<%=numPag %>)"><input type="image" 
											src="../images/order_asc.png" value="accion" alt="Descendente" onclick="ordena(14,<%=numPag %>)">
									</div>
								</th>
							</tr>
						</tbody>
					</table>
				</th>				
				<th class="pae-table__cell-header">
					<table class="tabla_resultadosInterno">
						<tbody>
							<tr>											
								<th>
									<div class="titulo_izq_tabla">									
										<span class="pae-table__txt-title">
											<spring:message code="field.solicitudRegistrada.convocatoria.mayus"/>
										</span>	
									</div>
								</th>
								<th>											
									<div class="titulo_der_tabla">
										<input type="image" 
											src="../images/order_desc.png" value="accion" alt="Ascendente" 
											onclick="ordena(15,<%=numPag %>)"><input type="image" 
											src="../images/order_asc.png" value="accion" alt="Descendente" onclick="ordena(16,<%=numPag %>)">
									</div>
								</th>
							</tr>
						</tbody>
					</table>
				</th>	
				<th class="pae-table__cell-header">
					<table class="tabla_resultadosInterno">
						<tbody>
							<tr>											
								<th>
									<div class="titulo_izq_tabla">									
										<span class="pae-table__txt-title">
											<spring:message code="field.solicitudRegistrada.solicitaExcencion.mayus"/>
										</span>	
									</div>
								</th>								
							</tr>
						</tbody>
					</table>
				</th>		
				<th class="pae-table__cell-header">
					<table class="tabla_resultadosInterno">
						<tbody>
							<tr>											
								<th>
									<div class="titulo_izq_tabla">									
										<span class="pae-table__txt-title">
											<spring:message code="field.solicitudRegistrada.tipoSolicitud.mayus"/>
										</span>	
									</div>
								</th>
								<th>											
									<div class="titulo_der_tabla">
										<input type="image" 
											src="../images/order_desc.png" value="accion" alt="Ascendente" 
											onclick="ordena(17,<%=numPag %>)"><input type="image" 
											src="../images/order_asc.png" value="accion" alt="Descendente" onclick="ordena(18,<%=numPag %>)">
									</div>
								</th>
							</tr>
						</tbody>
					</table>
				</th>	
				<th class="pae-table__cell-header">
					<table class="tabla_resultadosInterno">
						<tbody>
							<tr>											
								<th>
									<div class="titulo_izq_tabla">									
										<span class="pae-table__txt-title">
											<spring:message code="field.solicitudRegistrada.Consentimiento.mayus"/>
										</span>	
									</div>
								</th>
								<th>											
									<div class="titulo_der_tabla">
										<input type="image" 
											src="../images/order_desc.png" value="accion" alt="Ascendente" 
											onclick="ordena(19,<%=numPag %>)"><input type="image" 
											src="../images/order_asc.png" value="accion" alt="Descendente" onclick="ordena(20,<%=numPag %>)">
									</div>
								</th>
							</tr>
						</tbody>
					</table>
				</th>
				<th class="pae-table__cell-header">
					<table class="tabla_resultadosInterno">
						<tbody>
							<tr>											
								<th>
									<div class="titulo_izq_tabla">									
										<span class="pae-table__txt-title">
											<spring:message code="field.solicitudRegistrada.EdadVer.mayus"/>
										</span>	
									</div>
								</th>
								<%-- <th>											
									<div class="titulo_der_tabla">
										<input type="image" 
											src="../images/order_desc.png" value="accion" alt="Ascendente" 
											onclick="ordena(21,<%=numPag %>)"><input type="image" 
											src="../images/order_asc.png" value="accion" alt="Descendente" onclick="ordena(22,<%=numPag %>)">
									</div>
								</th> --%>
							</tr>
						</tbody>
					</table>
				</th>					
				<th class="pae-table__cell-header">
					<table class="tabla_resultadosInterno">
						<tbody>
							<tr>											
								<th>
									<div class="titulo_izq_tabla">									
										<span class="pae-table__txt-title">
											<spring:message code="field.solicitudRegistrada.FechaVer.mayus"/>
										</span>	
									</div>
								</th>
								<%-- <th>											
									<div class="titulo_der_tabla">
										<input type="image" 
											src="../images/order_desc.png" value="accion" alt="Ascendente" 
											onclick="ordena(23,<%=numPag %>)"><input type="image" 
											src="../images/order_asc.png" value="accion" alt="Descendente" onclick="ordena(24,<%=numPag %>)">
									</div>
								</th> --%>
							</tr>
						</tbody>
					</table>
				</th>					
<!-- 				<th class="pae-table__cell-header"> -->
<!-- 					<table class="tabla_resultadosInterno"> -->
<!-- 						<tbody> -->
<!-- 							<tr>											 -->
<!-- 								<th> -->
<!-- 									<div class="titulo_izq_tabla">									 -->
<!-- 										<span class="pae-table__txt-title"> -->
<%-- 											<spring:message code="field.solicitudRegistrada.TituloVer.mayus"/> --%>
<!-- 										</span>	 -->
<!-- 									</div> -->
<!-- 								</th> -->
<!-- 								<th>											 -->
<!-- 									<div class="titulo_der_tabla"> -->
<!-- 										<input type="image"  -->
<!-- 											src="../images/order_desc.png" value="accion" alt="Ascendente"  -->
<%-- 											onclick="ordena(25,<%=numPag %>)"><input type="image"  --%>
<%-- 											src="../images/order_asc.png" value="accion" alt="Descendente" onclick="ordena(26,<%=numPag %>)"> --%>
<!-- 									</div> -->
<!-- 								</th> -->
<!-- 							</tr> -->
<!-- 						</tbody> -->
<!-- 					</table> -->
<!-- 				</th>	 -->
				<th class="pae-table__cell-header">
					<table class="tabla_resultadosInterno">
						<tbody>
							<tr>											
								<th>
									<div class="titulo_izq_tabla">									
										<span class="pae-table__txt-title">
											<spring:message code="field.solicitudRegistrada.Desempleo.mayus"/>
										</span>	
									</div>
								</th>
								<%-- <th>											
									<div class="titulo_der_tabla">
										<input type="image" 
											src="../images/order_desc.png" value="accion" alt="Ascendente" 
											onclick="ordena(27,<%=numPag %>)"><input type="image" 
											src="../images/order_asc.png" value="accion" alt="Descendente" onclick="ordena(28,<%=numPag %>)">
									</div>
								</th> --%>
							</tr>
						</tbody>
					</table>
				</th>	
				<th class="pae-table__cell-header">
					<table class="tabla_resultadosInterno">
						<tbody>
							<tr>											
								<th>
									<div class="titulo_izq_tabla">									
										<span class="pae-table__txt-title">
											<spring:message code="field.solicitudRegistrada.FNumerosa.mayus"/>
										</span>	
									</div>
								</th>
								<%-- <th>											
									<div class="titulo_der_tabla">
										<input type="image" 
											src="../images/order_desc.png" value="accion" alt="Ascendente" 
											onclick="ordena(29,<%=numPag %>)"><input type="image" 
											src="../images/order_asc.png" value="accion" alt="Descendente" onclick="ordena(30,<%=numPag %>)">
									</div>
								</th> --%>
							</tr>
						</tbody>
					</table>
				</th>	
				<th class="pae-table__cell-header">
					<table class="tabla_resultadosInterno">
						<tbody>
							<tr>											
								<th>
									<div class="titulo_izq_tabla">									
										<span class="pae-table__txt-title">
											<spring:message code="field.solicitudRegistrada.Discapacidad.mayus"/>
										</span>	
									</div>
								</th>
								<%-- <th>											
									<div class="titulo_der_tabla">
										<input type="image" 
											src="../images/order_desc.png" value="accion" alt="Ascendente" 
											onclick="ordena(31,<%=numPag %>)"><input type="image" 
											src="../images/order_asc.png" value="accion" alt="Descendente" onclick="ordena(32,<%=numPag %>)">
									</div>
								</th> --%>
							</tr>
						</tbody>
					</table>
				</th>	
				<%-- Victimas Verificado INI --%>
				<th class="pae-table__cell-header">
					<table class="tabla_resultadosInterno">
						<tbody>
							<tr>											
								<th>
									<div class="titulo_izq_tabla">									
										<span class="pae-table__txt-title">
											<spring:message code="field.solicitudRegistrada.Victimas.mayus"/>
										</span>	
									</div>
								</th>
							</tr>
						</tbody>
					</table>
				</th>	
				<%-- Victimas Verificado INI --%>
				<th class="pae-table__cell-header">
					<table class="tabla_resultadosInterno">
						<tbody>
							<tr>											
								<th>
									<div class="titulo_izq_tabla">									
										<span class="pae-table__txt-title">
											<spring:message code="field.solicitud.descargaDocumentos.mayus"/>
										</span>	
									</div>
								</th>								
							</tr>
						</tbody>
					</table>
				</th>				
				<th class="pae-table__cell-header">
					<table class="tabla_resultadosInterno">
						<tbody>
							<tr>											
								<th>
									<div class="titulo_izq_tabla">									
										<span class="pae-table__txt-title">
											<spring:message code="field.solicitudRegistrada.Admitido.mayus"/>
										</span>	
									</div>
								</th>								
							</tr>
						</tbody>
					</table>
				</th>	
				<th class="pae-table__cell-header">
					<table class="tabla_resultadosInterno">
						<tbody>
							<tr>											
								<th>
									<div class="titulo_izq_tabla">									
										<span class="pae-table__txt-title">
											<spring:message code="field.solicitudRegistrada.EstadoPID.mayus"/>
										</span>	
									</div>
								</th>								
							</tr>
						</tbody>
					</table>
				</th>										
			</tr> <!-- Resultados de la consulta -->
			<logic:iterate id="registro" name="solicitudes" indexId="index">
				<tr class="pae-table__row" name="row">
				<%if(sPerfilUsuario.equals(Constantes.ROL_CONSULTOR) 
						 ||  sPerfilUsuario.equals(Constantes.ROL_SOPORTE)
						 ||  sPerfilUsuario.equals(Constantes.ROL_GESTOR)
						 ||  sPerfilUsuario.equals(Constantes.ROL_ADMINISTRADOR))
				{%>
					<td data-content="" data-col="col5" data-function="fc-collapse-table" class="pae-table__cell-body">
						<input type="checkbox" name="solicitudesSel" onclick="resetCheck()" property="solicitudesSel" id="solicitudesSel${index+1}" value="${registro.id}" data-function="checkbox-custom-input" class="pae-form__custom-check" onclick="marcarCheck(this.checked)">
						<label for="solicitudesSel${index+1}" data-function="checkbox-custom" class="pae-form__custom-check-label pae-form__custom-check-label--option"/>
					</td>
					
				 <%}%>
					<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body"><span class="pae-table__txt-base pae-table__span-head"><bean:write name="registro" property="numeroSolicitud" /></span></td>
					<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body"><span class="pae-table__txt-base pae-table__span-head"><bean:write name="registro" property="nif" /></span></td>
					<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body"><span class="pae-table__txt-base pae-table__span-head"><bean:write name="registro" property="nombre" /></span></td>
					<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body"><span class="pae-table__txt-base pae-table__span-head"><bean:write name="registro" property="primerApellido" /></span></td>
					<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body"><span class="pae-table__txt-base pae-table__span-head"><bean:write name="registro" property="segundoApellido" /></span></td>
					<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body"><span class="pae-table__txt-base pae-table__span-head"><bean:write name="registro" property="ejercicio" /></span></td>
					<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body"><span class="pae-table__txt-base pae-table__span-head"><bean:write name="registro" property="siglasCentroGestor" /></span></td>
					<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body"><span class="pae-table__txt-base pae-table__span-head"><bean:write name="registro" property="idConvocatoria" /></span></td>
					<c:if test="${registro.solExencion eq true }" >	
					<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body">
						<span class="pae-table__txt-base pae-table__span-head">
							<input type="checkbox" id="checkRegEx" value="${registro.solExencion}" data-function="checkbox-custom-input" class="pae-form__custom-check" disabled="true" checked>
							<label for="checkRegEx" data-function="checkbox-custom" class="pae-form__custom-check-label pae-form__custom-check-label--option"/>
						</span>
					</td>
					</c:if>
					<c:if test="${registro.solExencion eq false}" >	
					<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body">
						<span class="pae-table__txt-base pae-table__span-head">
							<input type="checkbox" id="checkRegEx" value="${registro.solExencion}" data-function="checkbox-custom-input" class="pae-form__custom-check" disabled="true">
							<label for="checkRegEx" data-function="checkbox-custom" class="pae-form__custom-check-label pae-form__custom-check-label--option"/>
						</span>
					</td>
					</c:if>
					<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body"><span class="pae-table__txt-base pae-table__span-head"><bean:write name="registro" property="tipoDescripcion" /></span></td>
					<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body"><logic:equal name="registro" property="idConsentimiento" value="true"><span class="pae-table__txt-base pae-table__span-head"><spring:message code="field.solicitudRegistrada.consiente.Si"/></span></logic:equal>
						<logic:equal name="registro" property="idConsentimiento" value="false"><span class="pae-table__txt-base pae-table__span-head"><spring:message code="field.solicitudRegistrada.consiente.No"/></span></logic:equal>
					</td>
					<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body"><!--<bean:write name="registro" property="edadVerificada" />-->
						<span class="pae-table__txt-base pae-table__span-head">
							<logic:equal name="registro" property="edadVerificada" value="A"><spring:message code="field.solicitudRegistrada.verificada.Si"/></logic:equal>
							<logic:equal name="registro" property="edadVerificada" value="R"><spring:message code="field.solicitudRegistrada.verificada.No"/> </logic:equal>
							<logic:equal name="registro" property="edadVerificada" value="N"><spring:message code="field.solicitudRegistrada.verificada.Pdte"/> </logic:equal>		
						</span>	
					</td>
					<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body"><!--<bean:write name="registro" property="fechaNacimientoVerificada" />-->
						<span class="pae-table__txt-base pae-table__span-head">
							<logic:equal name="registro" property="fechaNacimientoVerificada" value="A"><spring:message code="field.solicitudRegistrada.verificada.Si"/></logic:equal>
							<logic:equal name="registro" property="fechaNacimientoVerificada" value="R">
								<spring:message code="field.solicitudRegistrada.verificada.No"/> <br>
								<logic:notEmpty name="registro" property="fechaNacimientoSvdi">
									<bean:write name="registro" property="fechaNacimientoSvdi" format="dd/MM/yyyy"/>
								</logic:notEmpty>
							</logic:equal>
							<logic:equal name="registro" property="fechaNacimientoVerificada" value="N"><spring:message code="field.solicitudRegistrada.verificada.Pdte"/> </logic:equal>
							<logic:equal name="registro" property="fechaNacimientoVerificada" value="E"><spring:message code="field.solicitudRegistrada.verificada.PdteErrorWs"/> </logic:equal>
						</span>
					</td>
<!-- 					<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body"> -->
<!-- 						<span class="pae-table__txt-base pae-table__span-head"> -->
<!-- 						<bean:write name="registro" property="tituloVerificado" /> -->
<%-- 							<logic:equal name="registro" property="tituloVerificado" value="A"><spring:message code="field.solicitudRegistrada.verificada.Si"/></logic:equal> --%>
<%-- 							<logic:equal name="registro" property="tituloVerificado" value="R"><spring:message code="field.solicitudRegistrada.verificada.No"/> </logic:equal> --%>
<%-- 							<logic:equal name="registro" property="tituloVerificado" value="N"><spring:message code="field.solicitudRegistrada.verificada.Pdte"/> </logic:equal> --%>
<!-- 						</span> -->
<!-- 					</td> -->
					<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body">
						<span class="pae-table__txt-base pae-table__span-head">
					    <bean:define id="idConsentimiento" name="registro"  type="es.map.ipsg.bean.SolicitudBean"/>			    
					     					     
  							<c:if test="${registro.idConsentimiento eq true }"> 
  							<%if ( (sPerfilUsuario == Constantes.ROL_GESTOR) || (sPerfilUsuario == Constantes.ROL_GESTOR) || (sPerfilUsuario == Constantes.ROL_ADMINISTRADOR)  )
								{%>					     					   
									<logic:equal name="registro" property="esDesempleo" value="true">
										<logic:equal name="registro" property="desempleoVerificado" value="A"><spring:message code="field.solicitudRegistrada.verificada.Si"/></logic:equal>
										<logic:equal name="registro" property="desempleoVerificado" value="R"><spring:message code="field.solicitudRegistrada.verificada.No"/> </logic:equal>
										<logic:equal name="registro" property="desempleoVerificado" value="N"><spring:message code="field.solicitudRegistrada.verificada.Pdte"/> </logic:equal>
										<logic:equal name="registro" property="desempleoVerificado" value="E"><spring:message code="field.solicitudRegistrada.verificada.PdteErrorWs"/> </logic:equal>
									</logic:equal>
									<logic:notEqual name="registro" property="esDesempleo" value="true">
										<spring:message code="field.solicitudRegistrada.motivoReduccion.NoAplica"/>
									</logic:notEqual>
					   		<%} else { %>
					   			<spring:message code="field.solicitudRegistrada.motivoReduccion.NoAplica"/>
					   		<%} %>
					   		</c:if>
							<c:if test="${registro.idConsentimiento eq false }">
								<spring:message code="field.solicitudRegistrada.motivoReduccion.NoAplica"/>
							</c:if>
					   </span>
					</td>
					<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body">
						<span class="pae-table__txt-base pae-table__span-head">
					    <bean:define id="idConsentimiento" name="registro"  type="es.map.ipsg.bean.SolicitudBean"/>
					     	<c:if test="${registro.idConsentimiento eq true }">
  							 <%if ( (sPerfilUsuario == Constantes.ROL_GESTOR) || (sPerfilUsuario == Constantes.ROL_GESTOR) || (sPerfilUsuario == Constantes.ROL_ADMINISTRADOR)  )
								{%>							
									<logic:equal name="registro" property="esFNumerosa" value="true">
										<!--<bean:write name="registro" property="fnumerosaVerificado" />-->
										<logic:equal name="registro" property="fnumerosaVerificado" value="A"><spring:message code="field.solicitudRegistrada.verificada.Si"/></logic:equal>
										<logic:equal name="registro" property="fnumerosaVerificado" value="R"><spring:message code="field.solicitudRegistrada.verificada.No"/> </logic:equal>
										<logic:equal name="registro" property="fnumerosaVerificado" value="N"><spring:message code="field.solicitudRegistrada.verificada.Pdte"/> </logic:equal>
										<logic:equal name="registro" property="fnumerosaVerificado" value="E"><spring:message code="field.solicitudRegistrada.verificada.PdteErrorWs"/> </logic:equal>
									</logic:equal>
									<logic:notEqual name="registro" property="esFNumerosa" value="true">
										<spring:message code="field.solicitudRegistrada.motivoReduccion.NoAplica"/>
									</logic:notEqual>
							<%} else { %>
								<spring:message code="field.solicitudRegistrada.motivoReduccion.NoAplica"/>
							<%} %>
							</c:if>
							<c:if test="${registro.idConsentimiento eq false }">
								<spring:message code="field.solicitudRegistrada.motivoReduccion.NoAplica"/>
							</c:if>
						</span>
					</td>
					<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body">
						<span class="pae-table__txt-base pae-table__span-head">					    
					    <bean:define id="idConsentimiento" name="registro"  type="es.map.ipsg.bean.SolicitudBean"/>
							<c:if test="${registro.idConsentimiento eq true }">
								<%if ( (sPerfilUsuario == Constantes.ROL_GESTOR) || (sPerfilUsuario == Constantes.ROL_GESTOR) || (sPerfilUsuario == Constantes.ROL_ADMINISTRADOR)  )
									{%>					
								 	<logic:equal name="registro" property="esDiscapacidad" value="true">
										<!--<bean:write name="registro" property="discapacidadVerificado" />-->
										<logic:equal name="registro" property="discapacidadVerificado" value="A"><spring:message code="field.solicitudRegistrada.verificada.Si"/></logic:equal>
										<logic:equal name="registro" property="discapacidadVerificado" value="R"><spring:message code="field.solicitudRegistrada.verificada.No"/> </logic:equal>
										<logic:equal name="registro" property="discapacidadVerificado" value="N"><spring:message code="field.solicitudRegistrada.verificada.Pdte"/> </logic:equal>
										<logic:equal name="registro" property="discapacidadVerificado" value="E"><spring:message code="field.solicitudRegistrada.verificada.PdteErrorWs"/> </logic:equal>
									</logic:equal>
										<logic:notEqual name="registro" property="esDiscapacidad" value="true">
											<spring:message code="field.solicitudRegistrada.motivoReduccion.NoAplica"/>
										</logic:notEqual>
									<%} else { %>
										<spring:message code="field.solicitudRegistrada.motivoReduccion.NoAplica"/>
									<%} %>
							</c:if>
							<c:if test="${registro.idConsentimiento eq false }">
								<spring:message code="field.solicitudRegistrada.motivoReduccion.NoAplica"/>
							</c:if>
						</span>
					</td>
					<%-- Victimas Verificado INI --%>
					<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body">
						<span class="pae-table__txt-base pae-table__span-head">					    
					    <bean:define id="idConsentimiento" name="registro"  type="es.map.ipsg.bean.SolicitudBean"/>
							<c:if test="${registro.idConsentimiento eq true }">
								<%if ( (sPerfilUsuario == Constantes.ROL_GESTOR) || (sPerfilUsuario == Constantes.ROL_GESTOR) || (sPerfilUsuario == Constantes.ROL_ADMINISTRADOR)  )
									{%>					
								 	<logic:equal name="registro" property="esVictimaTerrorismo" value="true">
										<!--<bean:write name="registro" property="discapacidadVerificado" />-->
										<logic:equal name="registro" property="victimasVerificado" value="A"><spring:message code="field.solicitudRegistrada.verificada.Si"/></logic:equal>
										<logic:equal name="registro" property="victimasVerificado" value="R"><spring:message code="field.solicitudRegistrada.verificada.No"/> </logic:equal>
										<logic:equal name="registro" property="victimasVerificado" value="N"><spring:message code="field.solicitudRegistrada.verificada.Pdte"/> </logic:equal>
										<logic:equal name="registro" property="victimasVerificado" value="E"><spring:message code="field.solicitudRegistrada.verificada.PdteErrorWs"/> </logic:equal>
									</logic:equal>
										<logic:notEqual name="registro" property="esVictimaTerrorismo" value="true">
											<spring:message code="field.solicitudRegistrada.motivoReduccion.NoAplica"/>
										</logic:notEqual>
									<%} else { %>
										<spring:message code="field.solicitudRegistrada.motivoReduccion.NoAplica"/>
									<%} %>
							</c:if>
							<c:if test="${registro.idConsentimiento eq false }">
								<spring:message code="field.solicitudRegistrada.motivoReduccion.NoAplica"/>
							</c:if>
						</span>
					</td>
					<%-- Victimas Verificado FIN --%>
						
					<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body">
						<span class="pae-table__txt-base pae-table__span-head">
						<%if(sPerfilUsuario.equals(Constantes.ROL_CONSULTOR) 
						 ||  sPerfilUsuario.equals(Constantes.ROL_SOPORTE)
						 ||  sPerfilUsuario.equals(Constantes.ROL_GESTOR) 
						 ||  sPerfilUsuario.equals(Constantes.ROL_ADMINISTRADOR))
						{%>	
						<!-- Enlace: Descarga Anexos -->
				    	<A href="#" onclick="javascript: return openWindowDocumentos(${registro.id},'AnexoSol');" >
			 		    <font color="#c33400">
			 		   		<spring:message code="field.solicitudRegistrada.descargaAnexos.mayus"/>
			 		   	</font>
			 		   	
			 		   	</A>
			 		   	<!-- Enlace: Justificante Registro-->
			 		   	<A href="#" onclick="javascript: return openWindowDocumentos(${registro.id},'JustificanteSol');" >
			 		    <font color="#c33400">
			 		   		<spring:message code="field.solicitudRegistrada.descargaJustificante.mayus"/>
			 		   	</font>
			 		   	</A>
					 	<!-- Enlace: Verificar fecha nacimiento (solo si consentimiento=true) -->
					 	
				    	<bean:define id="idConsentimiento" name="registro"  type="es.map.ipsg.bean.SolicitudBean"/>				    				    	
  							<c:if test="${registro.idConsentimiento eq true }" >
  							 <% if ( (sPerfilUsuario == Constantes.ROL_GESTOR) || (sPerfilUsuario == Constantes.ROL_GESTOR) || (sPerfilUsuario == Constantes.ROL_ADMINISTRADOR)  )
  							 	{%>  								
						    	<A href="#" onclick="javascript: return openWindowVerFecNacimiento(${registro.id});" >
					 		    <font color="#c33400">
					 		   		<spring:message code="field.solicitudRegistrada.verificarFechaNacimiento.mayus"/>
					 		   	</font>
					 		   	
					 		   	</A>
			 		   		<%}%>		
			 		   		</c:if>	
			 		   		<c:if test="${registro.idConsentimientoAeat eq true }" >
  							 <% if ( (sPerfilUsuario == Constantes.ROL_GESTOR) || (sPerfilUsuario == Constantes.ROL_GESTOR) || (sPerfilUsuario == Constantes.ROL_ADMINISTRADOR)  )
  							 	{%>  								
 					 		   	<!-- Enlace: Verificar titulo oficial (solo si consentimiento=true) -->
<%-- 					 		   	<A href="#" onclick="javascript: return openWindowVerTitulos(${registro.id});" > --%>
<!-- 					 		    <font color="#c33400"> -->
<%-- 					 		   		<spring:message code="field.solicitudRegistrada.verificarTituloOficial.mayus"/> --%>
<!-- 					 		   	</font> -->
<!-- 					 		   	</A> -->
					 		   	<!-- Enlace: Verificar Desempleo (solo si consentimiento=true) -->
					 		   	<logic:equal name="registro" property="esDesempleo" value="true">
					 		   	<A href="#" onclick="javascript: return openWindowVerDesempleo(${registro.id});" >
					 		    <font color="#c33400">
					 		   		<spring:message code="field.solicitudRegistrada.verificarDesempleo.mayus"/>
					 		   	</font>
					 		   	</A>
								</logic:equal>					
					 		   	<!-- Enlace: Verificar Fnumerosa (solo si consentimiento=true) -->
					 		   	<logic:equal name="registro" property="esFNumerosa" value="true">
					 		   	<A href="#" onclick="javascript: return openWindowVerFnumerosa(${registro.id});" >
					 		    <font color="#c33400">
					 		   		<spring:message code="field.solicitudRegistrada.verificarFnumerosa.mayus"/>
					 		   	</font>
					 		   	</A>
					 		   	</logic:equal>			
					 		   	<!-- Enlace: Verificar Discapacidad (solo si consentimiento=true) -->
					 		   	<logic:equal name="registro" property="esDiscapacidad" value="true">
					 		   	<A href="#" onclick="javascript: return openWindowVerDiscapacidad(${registro.id});" >
					 		    <font color="#c33400">
					 		   		<spring:message code="field.solicitudRegistrada.verificarDiscapacidad.mayus"/>
					 		   	</font>
					 		   	</A>
					 		   	</logic:equal>							 		  		 		   	
					 		   	<!-- Enlace: Verificar Victimas Terrorismo (solo si consentimiento=true) -->
					 		   	<logic:equal name="registro" property="esVictimaTerrorismo" value="true">
					 		   	<A href="#" onclick="javascript: return openWindowVerVictimaTerrorismo(${registro.id});" >
					 		    <font color="#c33400">
					 		   		<spring:message code="field.solicitudRegistrada.verificarVictima.mayus"/>
					 		   	</font>
					 		   	</A>
					 		   	</logic:equal>						 		  		 		   	
			 		   		<%}%>		
			 		   		</c:if>	 		   	
			 		    <%}%>	
			 		    </span>					 	
					</td>									
					<!-- ADMITIDO - INI -->
					<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body">
						<c:choose>
						    <c:when test="${registro.admitido eq 'Pendiente'}">
						        <span class="pae-table__txt-base pae-table__span-head" style="color:orange;">
						    </c:when> 
						 	<c:when test="${registro.admitido eq 'Posible admitido'}">
						        <span class="pae-table__txt-base pae-table__span-head" style="color:green;">
						    </c:when>  
						    <c:when test="${registro.admitido eq 'Posible excluido'}">
						        <span class="pae-table__txt-base pae-table__span-head" style="color:red;">
						    </c:when>    
						    <c:otherwise>
						        <span class="pae-table__txt-base pae-table__span-head" >
						    </c:otherwise>
						</c:choose>
							<bean:write name="registro" property="admitido" />
						</span>
					</td>
					<!-- ADMITIDO - FIN -->	
					<!-- ESTADO PID - INI -->
					<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body">
						<span class="pae-table__txt-base pae-table__span-head" >
							<bean:write name="registro" property="estadoPID" />
						</span>
					</td>
					<!-- ESTADO PID - FIN -->								
    			</tr>
			</logic:iterate>			 
		</table>
			</div>
		
			<%
			Object numPat = request.getAttribute("paginasTotales");
			Object pagActual = request.getAttribute("paginaActual");
			int numeroPagina = Integer.parseInt(numPat.toString());
			int paginaActual = Integer.parseInt(pagActual.toString());
			String paginar = "Paginar";%>
			<div class="capaPaginacion" id="cap">
				<span class="pae-form__span-label-regxp">
					<spring:message code="field.numRegistros"/>
						<form:select path="numRegistro" id="numRegistro" onchange="comprobarRegistros()" class="pae-form__input wauto">
							<form:option value="10"><spring:message code="field.10Registros"/></form:option>
							<form:option value="20"><spring:message code="field.20Registros"/></form:option>
							<form:option value="50"><spring:message code="field.50Registros"/></form:option>
						</form:select>
					</span>
					
					<div class="pagination">
					<%if(paginaActual != 1) { %>
						<strong><a href="#" class="" onclick="return marcarCheckPaginacion(<%=paginaActual-1%>,<%=numeroPagina %>)">&laquo;</a></strong>
					<% } else{  %>	
			 			<a style="text-decoration: none;cursor: default;background-color:#FFFFFF"></a> 
					<% }
			if(numeroPagina <= 6)
			{	
				for(int x = 1 ;x<numeroPagina+1; x++){
					String enlacePagina = "buscarSolicitudesRegistradas?paginaActual="
						+ x
						+ "&paginasTotales="
						+ numeroPagina
						+ "&llamada=" + paginar;
					if(x==paginaActual){%>
						<strong><a href="#" class="active" onclick="javascript: return marcarCheckPaginacion(<%=x%>,<%=numeroPagina %>);"><%=x %></a></strong>
						 
					<%}else{%>
						<strong><a href="#" onclick="javascript: return marcarCheckPaginacion(<%=x%>,<%=numeroPagina %>);"><%=x %></a></strong>>
						 
					<%}%>
				<%}%>
			<%}
			else
			{
				for(int x = 1 ;x<numeroPagina+1; x++){
					String enlacePagina = "buscarSolicitudesRegistradas?paginaActual="
						+ x
						+ "&paginasTotales="
						+ numeroPagina
						+ "&llamada=" + paginar;
					if(x==paginaActual && !(x<= 3 || x+3>numeroPagina)){%>
						<div><spring:message code="field.puntosPaginacion"/></div>
						<strong><a href="#" class="active" onclick="javascript: return marcarCheckPaginacion(<%=x%>,<%=numeroPagina %>);"><%=x %></a></strong>
						<div><spring:message code="field.puntosPaginacion"/></div>
					<%}else if (x<= 3 || x+3>numeroPagina){
						if(x==paginaActual){%>	
							<strong><a href="#" class="active" onclick="javascript: return marcarCheckPaginacion(<%=x%>,<%=numeroPagina %>);"><%=x %></a></strong>
							<%if(x == 3 && (paginaActual<= 3 || paginaActual+3>numeroPagina)){	%>
								 <a href="#"><div><spring:message code="field.puntosPaginacion"/></div></a>
							<%}%>
						 
						<%}
						else
						{%>	
							<strong><a href="#" onclick="javascript: return marcarCheckPaginacion(<%=x%>,<%=numeroPagina %>);"><%=x %></a></strong>
							<%if(x == 3 && (paginaActual<= 3 || paginaActual+3>numeroPagina)){	%>
								 <div><spring:message code="field.puntosPaginacion"/></div>
							<%}%>
							 
						<%}%>	
					<%}%>
				<%}%>
			<%}%>
			<%if(paginaActual != numeroPagina) { %>
				<strong><a href="#" class="" onclick="return marcarCheckPaginacion(<%=paginaActual+1%>,<%=numeroPagina %>)">&raquo;</a></strong>
			<% } else{  %>	
			 <a style="text-decoration: none;cursor: default;background-color:#FFFFFF"></a> 
			<% }  %> 			
			</div>
			<span class="pae-form__span-label-regxp">
				<spring:message code="field.general.irAPagina"/>
			</span>
			<form:input path="numeroPaginaIr" maxlength="4" class="pae-form__input w6"/>
			<div class="pae-box-buttons w10 margin-button-ir">
				<input type="submit" value="Ir" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline"  onclick="irPagina(<%=numeroPagina %>)">	
			</div>
		</div>
		<!--  Selección Paginación de Registros -->
		
		</logic:greaterThan>		
	</logic:present>
<%}%>




</form:form>
	
	
	<div id="informacionLotesBean" style = "display:none;">
		<div class="pae-body2" style="overflow-y:hidden">
			<div class="pae-layout__item">		
				<h1 class="pae-title2">				
		     		<spring:message code="field.consultaLotes"/>
				</h1>	
				
				<div>
					<br><b><h3 class="pae-box__header--title"><spring:message code="field.consultaLotes.peticiones"/></h3></b>
				</div>	
				<br>
				
				<logic:present name="informacionLotesBean" scope="request">
					<table id="paetable" class="pae-table pae-table--margin">
					<caption class="hiddenAccesible">Tabla info lotes</caption>
							<thead class="pae-table__header">	
							<tr class="pae-table__row"><!-- Nombres de cabecera de la tabla de resultados -->
								
								<th data-col="col1" class="pae-table__cell-header"><!-- Identificador Petición -->
									<span class="pae-table__txt-title">
										<spring:message code="field.consultaLotes.idPeticion"/>
									</span>
								</th>
								<th data-col="col2" class="pae-table__cell-header"><!-- Consulta realizada -->
									<span class="pae-table__txt-title">
										<spring:message code="field.consultaLotes.consulta"/>
									</span>
								</th>	
											
							</tr> <!-- Resultados de la consulta -->
							</thead>
							<logic:iterate id="registro" name="informacionLotesBean" >
								<tr class="pae-table__row" name="row">
									<td data-content="" data-col="col1" data-function="fc-collapse-table" class="pae-table__cell-body">
										<bean:write name="registro" property="idPeticion"/>
									</td>
									<td data-content="" data-col="col2" data-function="fc-collapse-table" class="pae-table__cell-body">
										<bean:write name="registro" property="descripcionConsulta" />
									</td>					
															
					   			</tr>
							</logic:iterate>
					</table>
				</logic:present>
			
				<div class="clear"></div><br><br>
				<%-- Boton --%>				
					<div class="filaBtn" style="text-align:right;">
						<div class="pae-box-buttons pd-right-1">
							<input type="button" value="Cerrar" name="accion" onclick="javascript: window.close()" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline"/>		
						</div>
					</div>				
			</div>
		</div>
	</div>

</body>
</html>