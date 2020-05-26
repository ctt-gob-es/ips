<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.mpr.es/ipsg/bean-tags" prefix="bean" %>
<%@ taglib uri="http://www.mpr.es/ipsg/html-tags" prefix="html" %>
<%@ taglib uri="http://www.mpr.es/ipsg/logic-tags" prefix="logic" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="es.map.ipsg.bean.*" %>
<%@ page import="es.map.ipsg.util.*" %>
<%@ page import="java.util.*" %>
<%@ page import="es.map.ipsg.bean.TituloOficialBean" %>

<% String estado = (String)request.getAttribute("estado");
	String boton = "Modificar";
	request.getSession().setAttribute("estado", estado); %>

<%-- Solo autocomplete INI --%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/convocatorias/autocomplete.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/convocatorias/autocomplete.js"></script>
<%-- Solo autocomplete FIN --%>

<script language='javascript'>

function modificarConvocatoria() {
	var ok = true;
	cargarInputTitulaciones();
	seleccionar_todo(); 
 	alta();
 	$('#titulos').prop('selected', false);
 	$('#titulosSeleccionados option').prop('selected', false);
 	if ('<%=estado%>' == 'FINALIZADA') {
 	 	if(!validaFormatoFecha(document.getElementById('fechaIniSub')) 
 	 			|| !validaFormatoFecha(document.getElementById('fechaFinSub'))){
 	 		alert("La fechas de subsanaciÛn son obligatorias.");
 	 		return false;
 	 	}
 	}
 	
 	if(validaFormatoFecha(document.getElementById('fechaInicio')) && validaFormatoFecha(document.getElementById('fechaIniSub'))){ 
 		if (!validarFechasSubsanacion()) {
 			ok = false;
 		} else {
 			ok = true;
 		}
	}
 	if (ok) {
 	document.getElementById('formPadre').submit();
}
 	return ok;
}

function cargarInputTitulaciones() {
	 $('#titulosSeleccionados option').prop('selected', true);
	 $('#titulosSeleccionadosInput').val($('#titulosSeleccionados').val());
	 
}

function calcularFormaAcceso() {
	if (calcularImporte()) {
		cargarInputTitulaciones();
		$('#titulos').prop('selected', false);
	 	$('#titulosSeleccionados option').prop('selected', false);
		document.getElementById('formPadre').submit();
	}
}

function sortLists(){
	obj1=document.getElementById("titulosSeleccionados");
	sortSelectByName(obj1);
	
	obj2=document.getElementById("provinciaExamenSeleccionados");
	sortSelectByName(obj2);
	
	obj3=document.getElementById("motivosExencionSeleccionados");
	sortSelectByName(obj3);

	obj4=document.getElementById("especialidadesSeleccionados");
	sortSelectByName(obj4);	
}

function replaceAccent(s){
	var r=s.toLowerCase();
    
    r = r.replace(new RegExp("[‡·‚„‰Â]", 'g'),"a");
    r = r.replace(new RegExp("[ËÈÍÎ]", 'g'),"e");
    r = r.replace(new RegExp("[ÏÌÓÔ]", 'g'),"i");
    r = r.replace(new RegExp("[ÚÛÙıˆ]", 'g'),"o");
    r = r.replace(new RegExp("[˘˙˚¸]", 'g'),"u");
    
    return r;
}

function customSort(a,b){
	var textA = a[0];
	var textB = b[0];
	
	var newA = replaceAccent(textA);
	var newB = replaceAccent(textB);
	
	if (newA < newB){
        return -1;
     }else if (newA > newB){
       return  1;
     }else{
       return 0;
     }
};

function pasar(formulario1,formulario2) { 

	obj=document.getElementById(formulario1); 
    if (obj.selectedIndex==-1) return;
	var lista = obj.options;
	for(var i=0; i<lista.length; i++) {
		var opcion = lista[i];
		if (opcion.selected) {
			valor = opcion.value;
			txt = opcion.text;
			obj.options[i] = null;
			obj2=document.getElementById(formulario2);
		 	opc = new Option(txt,valor); 
		    eval(obj2.options[obj2.options.length]=opc);   
			i--;
			sortSelectByName(obj2);	
			
		}
	}    
} 
function pasar2(formulario1,formulario2) { 

	obj=document.getElementById(formulario1); 
    if (obj.selectedIndex==-1) return;
    var lista = obj.options;
    for(var i=0; i<lista.length; i++) {
		var opcion = lista[i];
		if (opcion.selected) {
			valor = opcion.value;
			txt = opcion.text;
			obj.options[i] = null;
			obj2=document.getElementById(formulario2);
		 	opc = new Option(txt,valor); 
		    eval(obj2.options[obj2.options.length]=opc);   
			i--;
			sortSelectByName(obj2);			
		}
	}
 /*   
    var b1 = new Boolean(1);
    var f = 0;
    while(b1){
    	if(obj.options[f].value < opc.value){
    		for(i=obj2.options.length;i>f;i=i-1){
    			eval(obj2.options[i]=obj2.options[i-1]); 
    		}
    		eval(obj2.options[i-1]=opc);
    		b1=new Boolean();
    	}
    	f=f+1;
    } 
    */
}

function sortSelectByName(selectToSort) {
    var arrOptions = [];

    for (var i = 0; i < selectToSort.options.length; i++)  {
        arrOptions[i] = [];
        arrOptions[i][0] = selectToSort.options[i].text;
        arrOptions[i][1] = selectToSort.options[i].value;
        arrOptions[i][2] = selectToSort.options[i].selected;
    }
    
    arrOptions.sort(customSort);
    //arrOptions.sort();

    for (var i = 0; i < selectToSort.options.length; i++)  {
    	selectToSort.options[i].text = arrOptions[i][0];
        selectToSort.options[i].value = arrOptions[i][1];        
        selectToSort.options[i].selected = arrOptions[i][2];
    }
}

function sortSelect(selectToSort) {
    var arrOptions = [];

    for (var i = 0; i < selectToSort.options.length; i++)  {
        arrOptions[i] = [];
        arrOptions[i][0] = selectToSort.options[i].value;
        arrOptions[i][1] = selectToSort.options[i].text;
        arrOptions[i][2] = selectToSort.options[i].selected;
    }

    arrOptions.sort();

    for (var i = 0; i < selectToSort.options.length; i++)  {
        selectToSort.options[i].value = arrOptions[i][0];
        selectToSort.options[i].text = arrOptions[i][1];
        selectToSort.options[i].selected = arrOptions[i][2];
    }
}

function seleccionar_todo(){ 
	
	
	obj3=document.getElementById('titulosSeleccionados'); 
	if(obj3.options.length>0){
	   for (j=0;j<obj3.options.length;j++){ 
	      obj3.options[j].selected=true;  
	   }
	}
	
	obj4=document.getElementById('provinciaExamenSeleccionados');
	if(obj4.options.length>0){ 
		for (k=0;k<obj4.options.length;k++){
	      obj4.options[k].selected=true;
	   }
	}
	obj5=document.getElementById('motivosExencionSeleccionados');
	if(obj5.options.length>0){
		for (l=0;l<obj5.options.length;l++){ 
	      obj5.options[l].selected=true;
	   }
	}

	obj6=document.getElementById('especialidadesSeleccionados');
	if(obj6!=null){
		if(obj6.options.length>0){
			for (m=0;m<obj6.options.length;m++){ 
	      		obj6.options[m].selected=true;
	   		}
		}
	}
	
	obj7=document.getElementById('otrosTitulosSeleccionados');
	if(obj7!=null){
		if(obj7.options.length>0){
			for (m=0;m<obj7.options.length;m++){ 
	      		obj7.options[m].selected=true;
	   		}
		}
	}
	
	obj8=document.getElementById('discapacidadSeleccionados');
	if(obj8!=null){
		if(obj8.options.length>0){
			for (m=0;m<obj8.options.length;m++){ 
	      		obj8.options[m].selected=true;
	   		}
		}
	}
	
	obj9 = document.getElementsByName("datosPropiosSeleccionados");
	if(obj9!=null){
		if(obj9.length>0){
			for (m=0;m<obj9.length;m++){ 
				for (a=0;a<obj9[m].options.length;a++){ 
					obj9[m].options[a].selected=true;
				}
	   		}
		}
	}
}

function desseleccionar_todo(){ 
	
	
	obj3=document.getElementById('titulosSeleccionados'); 
	if(obj3.options.length>0){
	   for (j=0;j<obj3.options.length;j++){ 
	      obj3.options[j].selected=false;  
	   }
	}
	
	obj4=document.getElementById('provinciaExamenSeleccionados');
	if(obj4.options.length>0){ 
		for (k=0;k<obj4.options.length;k++){
	      obj4.options[k].selected=false;
	   }
	}
	obj5=document.getElementById('motivosExencionSeleccionados');
	if(obj5.options.length>0){
		for (l=0;l<obj5.options.length;l++){ 
	      obj5.options[l].selected=false;
	   }
	}

	obj6=document.getElementById('especialidadesSeleccionados');
	if(obj6!=null){
		if(obj6.options.length>0){
			for (m=0;m<obj6.options.length;m++){ 
	      		obj6.options[m].selected=false;
	   		}
		}
	}
	
	obj7=document.getElementById('otrosTitulosSeleccionados');
	if(obj7!=null){
		if(obj7.options.length>0){
			for (m=0;m<obj7.options.length;m++){ 
	      		obj7.options[m].selected=false;
	   		}
		}
	}
	
	obj8=document.getElementById('discapacidadSeleccionados');
	if(obj8!=null){
		if(obj8.options.length>0){
			for (m=0;m<obj8.options.length;m++){ 
	      		obj8.options[m].selected=false;
	   		}
		}
	}
	
	obj9 = document.getElementsByName("datosPropiosSeleccionados");
	if(obj9!=null){
		if(obj9.length>0){
			for (m=0;m<obj9.length;m++){ 
				for (a=0;a<obj9[m].options.length;a++){ 
					obj9[m].options[a].selected=false;
				}
	   		}
		}
	}
}


function calcularEspecialidad() {
	if (obtenerEspecialidad()){
		cargarInputTitulaciones();
		$('#titulos').prop('selected', false);
	 	$('#titulosSeleccionados option').prop('selected', false);
		document.getElementById('formPadre').submit();
	}
}

function obtenerEspecialidad(){
	var cuerpoEscala = document.getElementById("cuerpoEscala").value;
	if(cuerpoEscala !=null && cuerpoEscala!=""){
		seleccionar_todo();	
		document.getElementById("accion").value="especialidad";
		return true;
	}
	return false;
}

function volverDetalle(){
	var idCon = document.getElementById("idConvocatoria").value;
	window.location.href = "detalleConvocatoria?registro="+idCon; 
}


function calcularImporte(){
	if(document.getElementById("formaAcceso").value != "0"){
		seleccionar_todo();
		document.getElementById("accion").value="importe";
		document.getElementById("importe").value="";
		return true;
	}else{
		document.getElementById("accion").value=null;
		document.getElementById("importe").value="";
		return false;
	}

	//this.document.forms[0].action="../spring/modificarConvocatoria";
	//this.document.forms[0].submit;
}
function alta(){
	document.getElementById('accion').value="alta";
}

function borrarAccion(){
	document.getElementById("accion").value="";
}
function fechasInicio(){
	if(document.getElementById("fechaBoe").value=="" &&
	   document.getElementById("fechaInicio").value=="" &&
	   document.getElementById("fechaTermino").value==""){
		   
		fecha=new Date();
		dia=fecha.getDate();
		mes=fecha.getMonth()+1;
		if(mes<10){
			mes="0"+mes;
		}
		anio=fecha.getFullYear();
		fechaFormat=dia+"/"+mes+"/"+anio;
		var j = 0;
		var fecha1;
		var fecha2;
		var fecha3;
		//No se valida que la fecha boe no sea festivo o domingo
		fecha1=fechaFormat;
		document.getElementById("fechaBoe").value=fecha1;
		$("#fechaBoe").attr("value",fecha1);
		j=j+1;
		
		//No se valida que la fecha de inicio no sea festivo o domingo
		fecha2=addToDate(fechaFormat, j);
		document.getElementById("fechaInicio").value=fecha2;
		$("#fechaInicio").attr("value",fecha2);
		j=j+1;

		fecha3=addToDate(fechaFormat, j + 18);
		document.getElementById("fechaTermino").value=fecha3;
		$("#fechaTermino").attr("value",fecha3);
		j=j+1;
		
	}	
}

function actualizarFecha(){
	var fecha = document.getElementById("fechaBoe").value;
	if(fecha != null && fecha != ""){

		var arrayFecha = fecha.split("/");
		var dia = arrayFecha[0];
		var mes = arrayFecha[1];
		var anio = arrayFecha[2];

		if (dia.length <= 1) {
			dia = "0" +dia;
		}

		if (mes.length <=1) {
			mes= "0" + mes;
		}
		fechaFormat=dia+"/"+mes+"/"+anio;

		document.getElementById("fechaInicio").value = "";
		document.getElementById("fechaTermino").value = "";
		$("#fechaInicio").attr("value","");
		$("#fechaTermino").attr("value","");
		var j = 0;
		var fecha1;
		var fecha2;
		var fecha3;

		//No se valida que la fecha de inicio no sea festivo o domingo
		fecha2=addToDate(fechaFormat, j + 1);
		document.getElementById("fechaInicio").value=fecha2;
		$("#fechaInicio").attr("value",fecha2);
		j=j+1;
			
		fecha3=addToDate(fechaFormat, j + 19);
		document.getElementById("fechaTermino").value=fecha3;
		$("#fechaTermino").attr("value",fecha3);
		j=j+1;
	}else{
		document.getElementById("fechaInicio").value = "";
		document.getElementById("fechaTermino").value = "";
		$("#fechaInicio").attr("value","");
		$("#fechaTermino").attr("value","");
	}
}

function actualizarFechaFinSub(){

	var fecha = document.getElementById("fechaIniSub").value;
	if(fecha != null && fecha != ""){

		fechaFormat=obtenerFormatoFecha(fecha);

		document.getElementById("fechaFinSub").value = "";
		$("#fechaFinSub").attr("value","");
		var j = 0;

		var fecha3;

		fecha3=addToDate(fechaFormat, j + 19);
		document.getElementById("fechaFinSub").value=fecha3;
		$("#fechaFinSub").attr("value",fecha3);
		j=j+1;

	}else{
		document.getElementById("fechaIniSub").value = "";
		document.getElementById("fechaFinSub").value = "";
		$("#fechaIniSub").attr("value","");
		$("#fechaFinSub").attr("value","");
	}
	
}

function obtenerFormatoFecha(fecha) {
	var arrayFecha = fecha.split("/");
	var dia = arrayFecha[0];
	var mes = arrayFecha[1];
	var anio = arrayFecha[2];

	if (dia.length <= 1) {
		dia = "0" +dia;
	}

	if (mes.length <=1) {
		mes= "0" + mes;
	}
	fechaFormat=dia+"/"+mes+"/"+anio;
	
	return fechaFormat;
}

function validaFecha(objeto){
	var fecha = objeto.value;
	$("#"+objeto.id).attr("value",objeto.value);
	
	if(fecha!=null && fecha!=""){
		if(fecha.length==10){
			if(!/^\d{2}\/\d{2}\/\d{4}$/.test(fecha)){
				alert("El formato de fecha introducido no es v·lido (dd/mm/aaaa)");
			}
		}else{
			alert("El formato de fecha introducido no es v·lido (dd/mm/aaaa)");
		}
	}
	return false;
}

function validarFechasSubsanacion() {
	var fecha_ini_sub = document.getElementById('fechaIniSub').value;
	var fecha_fin_sub = document.getElementById('fechaFinSub').value;
	
 	if(fecha_ini_sub!="" && fecha_fin_sub!=""){
	 	var fecha_ini_sub_array = fecha_ini_sub.split("/");
	 	fecha_ini_sub_seg = new Date(fecha_ini_sub_array[2]+"/"+fecha_ini_sub_array[1]+"/"+fecha_ini_sub_array[0]);
	 	
	 	var fecha_fin_sub_array = fecha_fin_sub.split("/");
	 	fecha_fin_sub_seg = new Date(fecha_fin_sub_array[2]+"/"+fecha_fin_sub_array[1]+"/"+fecha_fin_sub_array[0]);
	 	
		var fecha_fin = document.getElementById('fechaTermino').value;
	 	var fecha_fin_array = fecha_fin.split("/");
	 	fecha_fin_seg = new Date(fecha_fin_array[2]+"/"+fecha_fin_array[1]+"/"+fecha_fin_array[0]);
	 	
	 	
	 	if (fecha_fin_seg.getTime() >= fecha_ini_sub_seg.getTime()) {
			alert("La fecha de inicio de la subsanaciÛn no puede ser inferior o igual a la fecha fin de la convocatorÌa.");
			return false;
	 	} 
	 	if (fecha_ini_sub_seg.getTime() > fecha_fin_sub_seg.getTime()) {
			alert("La fecha fin de la subsanaciÛn no puede ser inferior a la fecha de inicio de la subsanaciÛn.");
			return false;
	 	}
 	} /* else {
		alert("La fecha de inicio y fin de la subsanaciÛn son obligatorias");
		return false;
 	}	 */
 	
 	return true;
}

function validaFormatoFecha(objeto){
	var fecha = objeto.value;
	$("#"+objeto.id).attr("value",objeto.value);
	
	if(fecha!=null && fecha!=""){
		if(fecha.length==10){
			if(!/^\d{2}\/\d{2}\/\d{4}$/.test(fecha)){
				alert("El formato de fecha introducido no es v·lido (dd/mm/aaaa)");
			}else{
				return true;
			}
		}else{
			alert("El formato de fecha introducido no es v·lido (dd/mm/aaaa)");
		}
	}
	
	return false;
}

function openWindowCentroGestor() {
	limpiarCuerpoEscala();
	limpiarCentroGestor();
	document.getElementById("cuerpoEscala").value = "";
	document.getElementById("dsCuerpoEscala").value = "";
	var param = "centroGestor";
	var param2 = "dsCentroGestor";
	var extract = new Object();
	ventanaPopup = window.open("listarCentroGestor?parametro="+param+"&parametro2="+param2+"&listarTodos=N", 'miventana', 'width=550, height=550,toolbar=no,directories=no,status=no,linemenubar=no,scrollbars=yes,resizable=no ,modal=yes');	    
	ventanaPopup.focus();
	limpiarCuerpoEscala();
	limpiarCentroGestor();
	return false;
}

function comprobar(){
	seleccionar_todo();	
	//limpiarCuerpoEscala();
	//document.getElementById("cuerpoEscala").value = "";
	cargarInputTitulaciones();
	$('#titulos').prop('selected', false);
	$('#titulosSeleccionados option').prop('selected', false);
	document.getElementById("accion").value="siglas";
}

function openWindowCuerpoEscala() {
	seleccionar_todo();
	limpiarCuerpoEscala();
	var centro = document.getElementById("centroGestor").value;
	if(centro != null && centro != ""){
		var param = "cuerpoEscala";
		var param2 = "dsCuerpoEscala";
		var extract = new Object();
		ventanaPopup = window.open("listarCuerpoEscala?centro="+centro+"&parametro="+param+"&parametro2="+param2+"&listarTodos=N", 'miventana', 'width=550, height=550,toolbar=no,directories=no,status=no,linemenubar=no,scrollbars=yes,resizable=no ,modal=yes');	    
		ventanaPopup.focus();
	limpiarCuerpoEscala();
	}else{
		alert("Tiene que seleccionar antes un centro gestor");
	}
	return false;
}
function openWindowDocumentos(id) {
	var navegador = navigator.appName;
	var extract = new Object();
	
	ventanaPopup = window.open("documentosConvocatoria?id="+id, 'miventana', 'width=550, height=470,toolbar=no,directories=no,status=no,linemenubar=no,scrollbars=yes,resizable=no ,modal=yes');	    
	ventanaPopup.focus();
	 
}

function actualizarFechaFin(){

	var fecha = document.getElementById("fechaInicio").value;
	if(fecha != null && fecha != ""){

		fechaFormat=obtenerFormatoFecha(fecha);

		//document.getElementById("fechaInicio").value = "";
		document.getElementById("fechaTermino").value = "";
		$("#fechaTermino").attr("value","");
		var j = 0;

		var fecha3;

		fecha3=addToDate(fechaFormat, j + 19);
		document.getElementById("fechaTermino").value=fecha3;
		$("#fechaTermino").attr("value",fecha3);
		j=j+1;
	}else{
		document.getElementById("fechaInicio").value = "";
		document.getElementById("fechaTermino").value = "";
		$("#fechaInicio").attr("value","");
		$("#fechaTermino").attr("value","");
	}
	
}

function limpiarCentroGestor(){
var desCentro = document.getElementById("dsCentroGestor").value;
	if(desCentro != ""){
		document.getElementById("centroGestor").value = "";
		document.getElementById("dsCentroGestor").value = "";
		document.getElementById("cuerpoEscala").value = "";
		document.getElementById("dsCuerpoEscala").value = "";
		document.getElementById("siglasMinisterioBoe").value = "0";
	}
	limpiarCuerpoEscala();
}

function limpiarCuerpoEscala(){
	document.getElementById("cuerpoEscala").value = "";
	document.getElementById("dsCuerpoEscala").value = "";
	document.getElementById("importe").value = "";

	objEsp=document.getElementById('especialidades');			
	if(objEsp!=null){
		var lengthEsp = objEsp.options.length;	
		if(lengthEsp>0){
			for (var m=0;m<=lengthEsp;m++){ 
				objEsp.options[0] = null;
	   		}
		}
	}

	objEspSel=document.getElementById('especialidadesSeleccionados');		
	if(objEspSel!=null){
		var lengthEspSel = objEspSel.options.length;	
		if(lengthEspSel>0){
			for (var n=0;n<lengthEspSel;n++){
				objEspSel.options[0] = null;
	   		}
		}
	}
	
}

//Rutinas Javascript fechas


	function pulsarCheck(){
        if ($("#checkbox-exemption-2").is(':checked') ) {
        	$("#otrosTitulosDiv").show(1000);
        } else {
        	$("#otrosTitulosDiv").hide(1000);
        }
	
        if ($("#checkbox-exemption-3").is(':checked') ) {
        	$("#discapacidadDiv").show(1000);
        } else {
        	$("#discapacidadDiv").hide(1000);
        }
        
        if ($("#checkbox-exemption-4").is(':checked') ) {
        	$("#checkbox-ocultar-datos").prop('checked', false);
        	$("#datosPropiosDiv").show(1000);
        } else {
        	$("#datosPropiosDiv").hide(1000);
        }
	}
	
	function ocultarDatos() {
	   if ($("#checkbox-ocultar-datos").is(':checked')) {
		   $("#checkbox-exemption-4").prop('checked', false);
		   $("#datosPropiosDiv").hide(1000);
	   }
		$("#ocultarDivDatosPropios").toggle(225);
	}
	
	function ocultarDatosPropios() {
		 if ($("#checkbox-ocultar-datos").is(':checked')) {
			$("#ocultarDivDatosPropios").hide();
		 }
	}
	
	//Array de titulos
	var listaTituloJs = [
	<% 
	  List<TituloOficialBean> listTitulos = (List)request.getAttribute("titulosDisp");
		for (TituloOficialBean titulo : listTitulos)
	    {

	        %> "<%=titulo.getDescripcion()%>", <%
	
	    } 
	%> ]

</script>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title></title>
</head>

<body style="margin-left:0.4em;" onload="desseleccionar_todo();sortLists();ocultarDatosPropios();">



<div>

<form:form modelAttribute="crearConvocatoriaForm" action="/IPSG/spring/modificarConvocatoria" id="formPadre" method="post">
	<form:hidden path="accion" class="input_texto" id="accion"/>
	<form:hidden path="idConvocatoria" class="input_texto" id="idConvocatoria"/>
	<form:hidden path="ejercicio" class="input_texto" id="ejercicio"/>
	<form:hidden path="titulosSeleccionadosInput" id="titulosSeleccionadosInput"/>
	<form:hidden path="centroGestorAnterior" id="centroGestorAnterior" value="${crearConvocatoriaForm.centroGestor}"/>
	
<% if(Constantes.CONVOCATORIA_ESTADO_FINALIZADA.equals(estado)){ 
	boton = "Subsanar"; %>
	<h1 class="pae-title"><spring:message code="field.solicitud.tituloSubsanarConvocatoria"/></h1>
<% } else { %>	
<h1 class="pae-title"><spring:message code="field.solicitud.tituloModificarConvocatoria"/></h1>
<% } %>	


	<logic:present name="org.apache.spring.ERROR">
		<html:errors/>
	</logic:present>

	<div id="divPrincipal">
	<div data-function="accordion-box" class="pae-box">
	<div class="pae-box__body">
	<fieldset>
	
	<div class="pae-layout">
	<!-- Ini Centro Gestor-->
	<logic:present name="rol" scope="request">
		<logic:equal name="rol" value="3">
			<div class="capaIzqAll">
				
				<div class="pae-layout__item pae-u-6/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-1_7">
					<div class="pae-form__cell">
						<span class="pae-form__label pae-form__span-label">
							<spring:message code="field.centroGestor" /><span class="obligatorio"> *</span>
						</span>
						<!--INI-TRA042-->
						<logic:present name="listaCentrosGestores" scope="request">
							<div class="pae-form__cell pae-u-9/12">
								<form:select path="centroGestor" class="pae-form__input" onchange="javascript: if(this.value!=''){comprobar();this.form.submit();}">
									<option value=""></option>
									<form:options items="${listaCentrosGestores}" itemValue="id" itemLabel="descripcion" />
								</form:select>
							</div>
						</logic:present>
						<!--FIN-TRA042-->
					</div>
				</div>				
				<!-- Ini Modelo Asociado --> 
				<logic:present name="modeloAsociado" scope="request">
					<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-1_7">
						<div class="pae-form__cell">
							<span class="pae-form__label pae-form__span-label">
								<spring:message code="field.modeloAsociado"/><span class="obligatorio"> *</span>
							</span>
							<form:input path="modeloAsociado" class="pae-form__input" maxlength="100" readonly="true"></form:input>
						</div>
					</div>
				</logic:present>
				
				<!-- Fin Modelo Asociado -->		
			</div>	
		</logic:equal>
	</logic:present>
		
	<logic:present name="rol" scope="request">
		<logic:notEqual name="rol" value="3">
					<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-1_7">
						<div class="pae-form__cell">
							<span class="pae-form__label pae-form__span-label">
								<spring:message code="field.centroMay" /><span class="obligatorio"> *</span>
							</span>
							<form:input
				 				path="centroGestor" class="pae-form__input"
				 				readonly="true" onfocus="javascript: comprobar();this.form.submit();"></form:input> 
				 				<form:input 
								path="dsCentroGestor" readonly="true" 
				 				class="input_texto_border0 em100"></form:input>
				 				
								
						</div>
					</div>	
					<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm search_btn pd-top-1_7">
						<input type="button"
								class="pae-form__btn-search" alt="Buscar Centro Gestor"
								onclick="return openWindowCentroGestor()"> 
					</div>
				
				<!-- Ini Modelo Asociado --> 
				<logic:present name="modeloAsociado" scope="request">
					<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-1_7">
						<div class="pae-form__cell">
							<span class="pae-form__label pae-form__span-label">
								<spring:message code="field.modeloAsociado"/><span class="obligatorio"> *</span>
							</span>
							<form:input path="modeloAsociado" class="pae-form__input" maxlength="100" readonly="true"></form:input>
						</div>
					</div>
				</logic:present>
				<!-- Fin Modelo Asociado -->		
		</logic:notEqual>
	</logic:present>
	<!-- Fin Centro Gestor-->	
	</div>

	<!-- Ini Cuerpo y Escala-->
	<div class="pae-layout">
		<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm">
			<div class="pae-form__cell">
				<span class="pae-form__label pae-form__span-label">
					<spring:message code="field.cuerpoMay" /><span class="obligatorio"> *</span>
				</span>
				<form:input 
			 		path="cuerpoEscala" class="pae-form__input" 
			 		readonly="true" 
			 		onfocus="calcularEspecialidad()"></form:input> 
			 		<form:input path="dsCuerpoEscala" id="dsCuerpoEscala"
					readonly="true" class="input_texto_border0 em100"></form:input>
					
				
			</div>
		</div>
		<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm search_btn">
			<input type="button"
					class="pae-form__btn-search" style="cursor: pointer;"
					alt="Buscar Cuerpo Escala" onclick="return openWindowCuerpoEscala()">
		</div>
		
		<!-- Fin Cuepor y Escala-->
		
		<!-- Ini Ministerio Convocante --> 
		<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm">
			<div class="pae-form__cell">
				<span class="pae-form__label pae-form__span-label">
					<spring:message code="field.ministerioConvocante" /><span class="obligatorio"> *</span>
				</span>
				<form:select path="ministerioConvocante" class="pae-form__input" >
					<option value="">         </option>
					<form:options items="${ministerioList}" itemValue="id" itemLabel="descripcion" />			
				</form:select>	
					
				
			</div>
		</div>
		<!-- Fin Ministerio Convocante -->
	
	</div>
	<br><br>
	<!-- Ini N˙m. Plazas Totales-->
	<div class="pae-layout">
		<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm">
			<span class="pae-form__label pae-form__span-label">
				<spring:message code="field.numPlazasTotalesMay"/><span class="obligatorio"> *</span>
			</span>
			<form:input path="numPlazas" class="pae-form__input" maxlength="6"></form:input> 
		</div>
		<!-- Fin N˙m. Plazas Totales-->		
		<!-- Ini N˙m. Plazas Discapacitados-->		
		<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm">		
			<span class="pae-form__label pae-form__span-label">
				<spring:message code="field.numPlazasDiscapacitadosMay"/><span class="obligatorio"> *</span>
			</span>
			<form:input path="numPlazasDisc" class="pae-form__input" maxlength="6"/>
		</div>
	</div>
	<!-- Fin N˙m. Plazas Discapacitados-->
	<br><br>
	<!-- Ini Forma Acceso-->	
	<div class="pae-layout">
		<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm">
			<span class="pae-form__label pae-form__span-label">
				<spring:message code="field.formaAccesoMay"/><span class="obligatorio"> *</span>
			</span>
			<form:select path="formaAcceso" id="formaAcceso" class="pae-form__input" onchange="calcularFormaAcceso()">
				<option value="0">         </option>
				<form:options items="${formasAcceso}" itemValue="id" itemLabel="descripcion" />
			</form:select>
		
			
		</div>	
		
		<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm">
			<span class="pae-form__label pae-form__span-label">
				<span class="obligatorio"> *</span>
			</span>
			<form:input path="codigoFormaAcceso" id="codigoFormaAcceso" class="pae-form__input" maxlength="5"/>
		</div>
		<!-- Fin Forma Acceso-->	
		<!-- Ini Importe-->	
		<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm">			
			<span class="pae-form__label pae-form__span-label">
				<spring:message code="field.importeMay"/><span class="obligatorio"> *</span>
			</span>
			<form:input path="importe" id="importe" maxlength="10" readonly="true" class="pae-form__input"/>
		</div>
	</div>
	<!-- Fin Importe-->
	<br><br>
	<!-- Ini Dirigido A-->	
	<div class="pae-layout">		
		<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm">			
			<span class="pae-form__label pae-form__span-label">
				<spring:message code="field.dirigidoAMay"/><span class="obligatorio"> *</span>
			</span>
			<form:input path="dirigidoA" class="pae-form__input" maxlength="100"/>
				
		</div>
		<!-- Fin Dirigido A-->
		<!-- Ini Tipo Documento a Indexar-->
		<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm">			
			<span class="pae-form__label pae-form__span-label">
				<spring:message code="field.tipoDocumentacionAnexarMay"/><span class="obligatorio"> *</span>
			</span>
			<form:select path="tipoDocumentacion" class="pae-form__input">
				<option value="">         </option>
					<form:options items="${tipoDocumentoDisp}" itemValue="id" itemLabel="descripcion" />		
			</form:select>
			
		</div>
	</div>
	<!-- Fin Tipo Documento a Indexar -->
	<br><br>
	<div class="pae-layout">
		<!-- Ini Fecha BOE-->
		<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-2/1-palm">			
			<span class="pae-form__label pae-form__span-label">
				<spring:message code="field.convocatoria.fechaBOEMay"/>
			</span>
	
			<form:input path="fechaBoe" id="fechaBoe" data-function="function-datepicker"  placeholder="dd/mm/aaaa" class="pae-form__input" maxlength="10" onchange="javascript: if(validaFormatoFecha(document.getElementById('fechaBoe'))){actualizarFecha();}"/>
					<script type="text/javascript">
							fncCalendario('fechaBoe', '<%=request.getContextPath()%>');
					</script>
		</div>
		<!-- Fin Fecha BOE-->
	</div>
	<br><br>
	<div class="pae-layout">
		<!-- Ini Fecha Inicio-->
		<div class="pae-layout__item pae-u-4/12 pae-u-4/12-lap pae-u-4/1-palm">			
			<span class="pae-form__label pae-form__span-label">
				<spring:message code="field.fechaInicioMay"/>
			</span>
			<form:input path="fechaInicio" id="fechaInicio" data-function="function-datepicker"  placeholder="dd/mm/aaaa" class="pae-form__input" maxlength="10" onchange="javascript: if(validaFormatoFecha(document.getElementById('fechaInicio'))) {actualizarFechaFin();}"/>
			<script type="text/javascript">
							fncCalendario('fechaInicio', '<%=request.getContextPath()%>');
			</script>
			<div class="clear"></div>
		</div>
		<!-- Fin Fecha Inicio-->
		<!-- Ini Fecha Fin-->
		<div class="pae-layout__item pae-u-4/12 pae-u-4/12-lap pae-u-4/1-palm">		
			<span class="pae-form__label pae-form__span-label">
				<spring:message code="field.fechaFinMay"/>
			</span>
			<form:input path="fechaTermino" id="fechaTermino" data-function="function-datepicker"  placeholder="dd/mm/aaaa" class="pae-form__input"  maxlength="10" onchange="javascript: validaFecha(document.getElementById('fechaTermino'));"/>
			<script type="text/javascript">
					fncCalendario('fechaTermino', '<%=request.getContextPath()%>');
			</script>
			
		</div>
		<!-- Fin Fecha Fin-->
	</div>
	<div class="clear"></div>
	<br>
	<%-- Ini Fecha Inicio Subsanacion --%>
	<div class="pae-layout">
		<div class="pae-layout__item pae-u-4/12 pae-u-4/12-lap pae-u-4/1-palm">	
			<span class="pae-form__label pae-form__span-label">
				<spring:message code="field.fechaIniSub"/>
				<% if(Constantes.CONVOCATORIA_ESTADO_FINALIZADA.equals(estado)){ %>
					<span class="obligatorio"> *</span>
				<% } %>	
			</span>
			<form:input path="fechaIniSub" id="fechaIniSub" data-function="function-datepicker"  placeholder="dd/mm/aaaa" class="pae-form__input" maxlength="10" onchange="javascript: if(validaFecha(document.getElementById('fechaIniSub'))) {actualizarFechaFinSub();}"/>
			<script type="text/javascript">
					fncCalendario('fechaIniSub', '<%=request.getContextPath()%>');
			</script>
			<div class="clear"></div>
		</div>
		<!-- Fin Fecha Inicio-->
		<!-- Ini Fecha Fin-->	
		<div class="pae-layout__item pae-u-4/12 pae-u-4/12-lap pae-u-4/1-palm">	
			<span class="pae-form__label pae-form__span-label">
				<spring:message code="field.fechaFinSub"/>
				<% if(Constantes.CONVOCATORIA_ESTADO_FINALIZADA.equals(estado)){ %>
					<span class="obligatorio"> *</span>
				<% } %>	
			</span>
			<form:input path="fechaFinSub" id="fechaFinSub" data-function="function-datepicker"  placeholder="dd/mm/aaaa" class="pae-form__input" maxlength="10" onchange="javascript: validaFecha(document.getElementById('fechaFinSub'));"/>
			<script type="text/javascript">
					fncCalendario('fechaFinSub', '<%=request.getContextPath()%>');
			</script>
		</div>
	</div>
	<%-- Fin Fecha Fin Subsanacion --%>	
	<div class="clear"></div>
	<br>
	<div class="pae-layout">
		<!-- Ini Presencial-->
		<div class="pae-layout__item pae-u-4/12 pae-u-4/12-lap pae-u-4/1-palm">		
			<form:checkbox path="presencial" id="presencial" ></form:checkbox>
			<label for="presencial" data-function="checkbox-custom" class="pae-form__custom-check-label pae-form__custom-check-label--option"/>
            <span class="pae-form__label pae-form__span-label"><spring:message code="field.presencialMay"/></span>
            </label>					
			<div class="clear"></div>			
		</div>
		<!-- Fin Presencial-->
	</div>

	<div class="clear"></div>
	<br>
	
	<!-- Ini TÌtulos Exigidos-->
	<div class="pae-layout">		
		<div class="pae-layout__item pae-u-4/12 pae-u-4/12-lap pae-u-4/1-palm">	
				<span class="pae-form__label pae-form__span-label">
					<spring:message code="field.titulosExigidosMay"/><span class="obligatorio"> *</span>
				</span>
				<form:select id="titulos" path="titulos" name="titulos" class="pae-form__input hide" multiple="true" size="5" >
						<form:options items="${titulosDisp}" itemLabel="descripcion" itemValue="id"/>	
					</form:select>
				<input id="titulosAutocomplete" class="pae-form__input" maxlength="20"/>
									
		</div>
		
		<div class="pae-layout__item pae-u-1/12 pae-u-1/12-lap pae-u-1/1-palm pd-top-1_7">	
			<div class="pae-box-buttons">
				<input type="button" value="<-" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium-pasarOption pae-buttons--download outline" onClick="pasarOptions('titulosSeleccionados','titulos')"> 
				<input type="button" value="->" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium-pasarOption pae-buttons--download outline" onClick="pasarOptions('titulos','titulosSeleccionados')"> 
			</div>
		</div>
		
		<div class="pae-layout__item pae-u-4/12 pae-u-4/12-lap pae-u-4/1-palm pd-top-1_7">	
			<form:select path="titulosSeleccionados" id="titulosSeleccionados"  class="pae-form__input" multiple="true" size="5">
				<form:options items="${titulosSel}" itemValue="id" itemLabel="descripcion" />
			</form:select>	
		</div>
	</div>
	<!-- Fin TÌtulos Exigidos-->
	<div class="clear"></div><br>
	<!-- Ini Provincias Examen-->
	<div class="pae-layout">		
		<div class="pae-layout__item pae-u-4/12 pae-u-4/12-lap pae-u-4/1-palm">	
				<span class="pae-form__label pae-form__span-label">
					<spring:message code="field.convocatoria.provinciaExamenMay"/>
				</span>
					
					<form:select path="provinciaExamen" id="provinciaExamen" class="pae-form__input" multiple="true" size="5">
							<form:options items="${provinciasDisp}" itemValue="id" itemLabel="descripcion" />
					</form:select>					
		</div>
		
		<div class="pae-layout__item pae-u-1/12 pae-u-1/12-lap pae-u-1/1-palm pd-top-1_7">	
		<div class="pae-box-buttons">
			<input type="button" value="<-" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium-pasarOption pae-buttons--download outline" onClick="pasarOptions('provinciaExamenSeleccionados','provinciaExamen')"> 
			<input type="button" value="->" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium-pasarOption pae-buttons--download outline" onClick="pasarOptions('provinciaExamen','provinciaExamenSeleccionados')"> 
		</div>
		</div>
		
		<div class="pae-layout__item pae-u-4/12 pae-u-4/12-lap pae-u-4/1-palm pd-top-1_7">	
			<form:select path="provinciaExamenSeleccionados" id="provinciaExamenSeleccionados" class="pae-form__input" multiple="true" size="5">
				<form:options items="${provinciasSel}" itemValue="id" itemLabel="descripcion" />
			</form:select>	
		</div>
	</div>
	
	<!-- Fin Provincias Examen-->
	<div class="clear"></div><br>
	<!-- Ini Motivos ExenciÛn -->
	<div class="clear"></div>
	<div class="pae-layout">		
		<div class="pae-layout__item pae-u-4/12 pae-u-4/12-lap pae-u-4/1-palm">	
				<span class="pae-form__label pae-form__span-label">
					<spring:message code="field.motivosExencionMay"/>
				</span>
						
					<form:select path="motivosExencion" id="motivosExencion" class="pae-form__input" multiple="true" size="5">
							<form:options items="${motivosDisp}" itemValue="id" itemLabel="descripcion" />
					</form:select>				
		</div>
		
		<div class="pae-layout__item pae-u-1/12 pae-u-1/12-lap pae-u-1/1-palm pd-top-1_7"">	
		<div class="pae-box-buttons">
			<input type="button" value="<-" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium-pasarOption pae-buttons--download outline" onClick="pasarOptions('motivosExencionSeleccionados','motivosExencion')"> 
			<input type="button" value="->" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium-pasarOption pae-buttons--download outline" onClick="pasarOptions('motivosExencion','motivosExencionSeleccionados')"> 
		</div>
		</div>
		
		<div class="pae-layout__item pae-u-4/12 pae-u-4/12-lap pae-u-4/1-palm pd-top-1_7"">	
			<form:select path="motivosExencionSeleccionados" id="motivosExencionSeleccionados" class="pae-form__input" multiple="true" size="5">
							<form:options items="${motivosSel}" itemValue="id" itemLabel="descripcion" />
					</form:select>	
		</div>
	</div>
	<div class="clear"></div><br>
	<!-- Fin Motivos ExenciÛn-->
	<div class="clear"></div><br>
	<!-- Ini Especialidades-->
	
	<div class="pae-layout">		
		<div class="pae-layout__item pae-u-4/12 pae-u-4/12-lap pae-u-4/1-palm">	
				<span class="pae-form__label pae-form__span-label">
					<spring:message code="field.especialidadesMay"/>
				</span>

					<form:select path="especialidades" id="especialidades" class="pae-form__input" multiple="true" size="5">
							<form:options items="${especialidadesDisp}" itemValue="id" itemLabel="descripcion" />
					</form:select>			
		</div>
		
		<div class="pae-layout__item pae-u-1/12 pae-u-1/12-lap pae-u-1/1-palm pd-top-1_7">	
		<div class="pae-box-buttons">
			<input type="button" value="<-" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium-pasarOption pae-buttons--download outline" onClick="pasarOptions('especialidadesSeleccionados','especialidades')"> 
			<input type="button" value="->" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium-pasarOption pae-buttons--download outline" onClick="pasarOptions('especialidades','especialidadesSeleccionados')"> 
		</div>
		</div>
		
		<div class="pae-layout__item pae-u-4/12 pae-u-4/12-lap pae-u-4/1-palm pd-top-1_7"">	
			<form:select path="especialidadesSeleccionados" id="especialidadesSeleccionados" class="pae-form__input" multiple="true" size="5">
				<form:options items="${especialidadesSel}" itemValue="id" itemLabel="descripcion" />
			</form:select>	
		</div>
	</div>
	<!-- Fin Especialidades-->
	<div class="clear"></div>
	<br>
	<div class="pae-layout">
		<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm">	
			<span class="pae-form__label pae-form__span-label">
				<spring:message code="field.enlaceInformativo"/>
			</span>
			<form:input path="enlace" class="pae-form__input ui-autocomplete-input" maxlength="100" id="enlace" name ="enlace" />
			<div class="clear"></div>
		</div>
	</div>
	<br><br>
	<c:if test="${crearConvocatoriaForm.centroGestor != null && crearConvocatoriaForm.centroGestor != ''}">
	<c:if test="${(otrosTitulosDisp != null && otrosTitulosDisp.size() > 0) || (otrosTitulosSel != null && otrosTitulosSel.size() > 0)}">
	<!-- Ini Mostrar Otros Titulos -->
	<div class="pae-layout">
		<div class="pae-layout__item pae-u-7/12 pae-u-4/12-lap pae-u-4/1-palm">		
			<c:choose>
			    <c:when test="${crearConvocatoriaForm.otrosTitulosFlag}">
			        <input type="checkbox" name="otrosTitulosFlag" property="otrosTitulosFlag" id="checkbox-exemption-2" data-function="checkbox-custom-alta-input" class="pae-form__custom-check" checked="checked">
			    </c:when>    
			    <c:otherwise>
			        <input type="checkbox" name="otrosTitulosFlag" property="otrosTitulosFlag" id="checkbox-exemption-2" data-function="checkbox-custom-alta-input" class="pae-form__custom-check"  >
			    </c:otherwise>
			</c:choose>			
			<label for="checkbox-exemption-2" data-function="checkbox-custom-alta" class="pae-form__custom-check-label pae-form__custom-check-label--option"/>
            <span class="pae-form__label pae-form__span-label"><spring:message code="field.otrosTitulosFlagMay"/></span>
            </label>					
			<div class="clear"></div>			
		</div>
	</div>
	<!-- Fin Mostrar Otros Titulos -->
	<div class="clear"></div>
	<br>	
	<!-- Ini Otros Titulos -->
	<c:choose>
	    <c:when test="${crearConvocatoriaForm.otrosTitulosFlag}">
	        <div id="otrosTitulosDiv" class="pae-layout">
	    </c:when>    
	    <c:otherwise>
	        <div id="otrosTitulosDiv" class="pae-layout" style="overflow: hidden; display: none;">
	    </c:otherwise>
	</c:choose>
		<logic:present name="otrosTitulosDisp" scope="request">
			<div class="pae-layout__item pae-u-4/12 pae-u-4/12-lap pae-u-4/1-palm">	
				<span class="pae-form__label pae-form__span-label">
					<spring:message code="field.otrosTitulosMay"/><span class="obligatorio"> *</span>
				</span>
				<form:select path="otrosTitulos" class="pae-form__input" multiple="true" size="5">
						<form:options items="${otrosTitulosDisp}" itemLabel="descripcion" itemValue="id"/>	
				</form:select>
			</div>
			<div class="pae-layout__item pae-u-1/12 pae-u-1/12-lap pae-u-1/1-palm pd-top-1_7">	
				<div class="pae-box-buttons">
					<input type="button" value="<-" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium-pasarOption" onClick="pasarOptions('otrosTitulosSeleccionados','otrosTitulos')"> 
					<input type="button" value="->" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium-pasarOption" onClick="pasarOptions('otrosTitulos','otrosTitulosSeleccionados')"> 
				</div>
			</div>
			<div class="pae-layout__item pae-u-4/12 pae-u-4/12-lap pae-u-4/1-palm pd-top-1_7"">		
				<form:select path="otrosTitulosSeleccionados" class="pae-form__input" multiple="true" size="5">
					<form:options items="${otrosTitulosSel}" itemLabel="descripcion" itemValue="id"/>	
				</form:select>
			</div>	
		
		</logic:present>
		<div class="clear"></div>
	</div>
	<!-- Fin Otros Titulos -->
	<div class="clear"></div>
	<br>
	</c:if>
	<c:if test="${(discapacidadDisp != null && discapacidadDisp.size() > 0) || (discapacidadSel != null && discapacidadSel.size() > 0)}">
	<!-- Ini Mostrar Discapacidad -->
	<div class="pae-layout">
		<div class="pae-layout__item pae-u-7/12 pae-u-4/12-lap pae-u-4/1-palm">		
			<c:choose>
			    <c:when test="${crearConvocatoriaForm.discapacidadFlag}">
			        <input type="checkbox" name="discapacidadFlag" property="discapacidadFlag" id="checkbox-exemption-3" data-function="checkbox-custom-alta-input" class="pae-form__custom-check"  checked="checked">
			    </c:when>    
			    <c:otherwise>
			        <input type="checkbox" name="discapacidadFlag" property="discapacidadFlag" id="checkbox-exemption-3" data-function="checkbox-custom-alta-input" class="pae-form__custom-check"  >
			    </c:otherwise>
			</c:choose>			
			<label for="checkbox-exemption-3" data-function="checkbox-custom-alta" class="pae-form__custom-check-label pae-form__custom-check-label--option"/>
            <span class="pae-form__label pae-form__span-label"><spring:message code="field.discapacidadFlagMay"/></span>
            </label>					
			<div class="clear"></div>			
		</div>	
	</div>
	<!-- Fin Mostrar Discapacidad -->
	<div class="clear"></div>
	<br>	
	<!-- Ini Discapacidad -->
	<c:choose>
	    <c:when test="${crearConvocatoriaForm.discapacidadFlag}">
	        <div id="discapacidadDiv" class="pae-layout">
	    </c:when>    
	    <c:otherwise>
	        <div id="discapacidadDiv" class="pae-layout" style="overflow: hidden; display: none;">
	    </c:otherwise>
	</c:choose>	
		<logic:present name="discapacidadDisp" scope="request">
			<div class="pae-layout__item pae-u-4/12 pae-u-4/12-lap pae-u-4/1-palm">	
				<span class="pae-form__label pae-form__span-label">
					<spring:message code="field.discapacidadMay"/><span class="obligatorio"> *</span>
				</span>
				<form:select path="discapacidad" class="pae-form__input" multiple="true" size="5">
						<form:options items="${discapacidadDisp}" itemLabel="descripcion" itemValue="id"/>	
				</form:select>
			</div>
			<div class="pae-layout__item pae-u-1/12 pae-u-1/12-lap pae-u-1/1-palm pd-top-1_7">	
				<div class="pae-box-buttons">
					<input type="button" value="<-" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium-pasarOption" onClick="pasarOptions('discapacidadSeleccionados','discapacidad')"> 
					<input type="button" value="->" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium-pasarOption" onClick="pasarOptions('discapacidad','discapacidadSeleccionados')"> 
				</div>
			</div>
			<div class="pae-layout__item pae-u-4/12 pae-u-4/12-lap pae-u-4/1-palm pd-top-1_7"">		
				<form:select path="discapacidadSeleccionados" class="pae-form__input" multiple="true" size="5">
					<form:options items="${discapacidadSel}" itemLabel="descripcion" itemValue="id"/>	
				</form:select>
			</div>	
		
		</logic:present>
		<div class="clear"></div>
	</div>
	<!-- Fin Discapacidad -->
	<div class="clear"></div>
	<br>
	</c:if>
	<c:if test="${camposDisponibles != null && camposDisponibles.size() > 0}">
	<!-- Ini Mostrar Datos Propios -->
	<div class="pae-layout" id="ocultarDivDatosPropios">
		<div class="pae-layout__item pae-u-7/12 pae-u-4/12-lap pae-u-4/1-palm">		
			<c:choose>
			    <c:when test="${crearConvocatoriaForm.datosPropiosFlag}">
			        <input type="checkbox" name="datosPropiosFlag" property="datosPropiosFlag" id="checkbox-exemption-4" data-function="checkbox-custom-alta-input" class="pae-form__custom-check"  checked="checked">
			    </c:when>    
			    <c:otherwise>
			        <input type="checkbox" name="datosPropiosFlag" property="datosPropiosFlag" id="checkbox-exemption-4" data-function="checkbox-custom-alta-input" class="pae-form__custom-check">
			    </c:otherwise>
			</c:choose>			
			<label for="checkbox-exemption-4" data-function="checkbox-custom-alta" class="pae-form__custom-check-label pae-form__custom-check-label--option"/>
            <span class="pae-form__label pae-form__span-label"><spring:message code="field.datosPropiosFlagMay"/></span>
            </label>					
			<div class="clear"></div>
			<br>		
		</div>	
	</div>
	<!-- Fin Mostrar Datos Propios -->
	<div class="clear"></div>
	<br>
	<!-- Ini Datos Propios -->
	<c:choose>
	    <c:when test="${crearConvocatoriaForm.datosPropiosFlag}">
	        <div id="datosPropiosDiv" class="pae-layout" style="display: block;">
	    </c:when>    
	    <c:otherwise>
	        <div id="datosPropiosDiv" class="pae-layout" style="overflow: hidden; display: none;">
	    </c:otherwise>
	</c:choose>	
	<logic:present name="datosPropiosConfigDisp" scope="request">
		<c:forEach var="tituloCampo" items="${camposDisponibles}" varStatus="campos" >
		<div class="pae-form__cell">
			<div class="pae-layout__item pae-u-4/12 pae-u-4/12-lap pae-u-4/1-palm">	
				<span class="pae-form__label pae-form__span-label">
					${tituloCampo}<span class="obligatorio"> *</span>
				</span>
				<select name="datosPropios" id="campo${campos.index}" class="pae-form__input" multiple="true" size="5">
					<c:forEach var="datoPopio" items="${datosPropiosConfigDisp}" >	
						<c:if test="${datoPopio.campo.campo eq tituloCampo}">
							<option value="${datoPopio.idDesplegable}">${datoPopio.descripcion}</option>
						</c:if>
					</c:forEach>	
				</select>					
			</div>
			<div class="pae-layout__item pae-u-1/12 pae-u-1/12-lap pae-u-1/1-palm pd-top-1_7">	
				<div class="pae-box-buttons">
					<input type="button" value="<-" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium-pasarOption" onClick="pasarOptions('campo${campos.index}Sel','campo${campos.index}')"> 
					<input type="button" value="->" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium-pasarOption" onClick="pasarOptions('campo${campos.index}','campo${campos.index}Sel')"> 
				</div>
			</div>
			<div class="pae-layout__item pae-u-4/12 pae-u-4/12-lap pae-u-4/1-palm pd-top-1_7"">	
				<select class="pae-form__input" id="campo${campos.index}Sel" name="datosPropiosSeleccionados" multiple="true" size="5">
					<c:forEach var="datoPopio" items="${datosPropiosSel}" >	
						<c:if test="${datoPopio.campo.campo eq tituloCampo}">
							<option value="${datoPopio.idDesplegable}">${datoPopio.descripcion}</option>
						</c:if>
					</c:forEach>	
				</select>		
			</div>
		</div>	
		</c:forEach>
	</logic:present>
	<div class="clear"></div>
	</div>
	<!-- Fin Datos Propios -->	
	<div class="clear"></div>
	<br>	
	<!-- Ini Ocultar Datos Propios -->
	<div class="pae-layout">
		<div class="pae-layout__item pae-u-4/12 pae-u-4/12-lap pae-u-4/1-palm">
			<c:choose>
				<c:when test="${crearConvocatoriaForm.ocultarDatosPropios == true}">
					<input type="checkbox" name="ocultarDatosPropios" property="ocultarDatosPropios" id="checkbox-ocultar-datos" 
						data-function="checkbox-custom-ocultar-datos" class="pae-form__custom-check" onclick="ocultarDatos()" checked="checked">
				</c:when>
				<c:otherwise>
					<input type="checkbox" name="ocultarDatosPropios" property="ocultarDatosPropios" id="checkbox-ocultar-datos" 
						data-function="checkbox-custom-ocultar-datos" class="pae-form__custom-check" onclick="ocultarDatos()">
				</c:otherwise>
			</c:choose>
			<label for="checkbox-ocultar-datos" data-function="checkbox-custom-ocultar-datos" class="pae-form__custom-check-label pae-form__custom-check-label--option"/>
            	<span class="pae-form__label pae-form__span-label"><spring:message code="field.ocultarDatosPropiosFlagMay"/></span>
            </label>					
			<div class="clear"></div>			
		</div>
	</div>
	<div class="clear"></div>
	<br>
	<!-- Fin Ocultar Datos Propios -->	
	</c:if>				
	</c:if>		
	</fieldset>
	</div>
	</div>
	</div>

	<div class="filaBtn">
		<div class="pae-box-buttons pd-right-1">
			<input type="button" name="mySubmitButton" value="<%=boton%>" property="button" onclick="if(!modificarConvocatoria()){return false;};"  class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium"/>
			<input type="button" value="Cancelar" property="volver" onclick="volverDetalle()"  class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium mg-left-1"/>
		</div>
	</div>
</form:form>
	
</body>
</html>