package es.agencia.test;

import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import es.agenciatributaria.www.ListaEntidades.peticion.wsdl.ListaEntidades_PortType;
import es.agenciatributaria.www.ListaEntidades.peticion.wsdl.ListaEntidades_Service;
import es.agenciatributaria.www.ListaEntidades.peticion.wsdl.ListaEntidades_ServiceLocator;
import es.agenciatributaria.www.peticion.PeticionListaEntidades;
import es.agenciatributaria.www.respuesta.Entidad;
import es.agenciatributaria.www.respuesta.Respuesta;
import es.agenciatributaria.www.respuesta.RespuestaListaEntidades;


/**
 * El Class Test.
 */
public class Test {
	
	
	
	/**
	 * Test peticion.
	 *
	 * @return el list
	 */
	public List<Entidad> TestPeticion(){
		
		List<Entidad> listaEnt= null;
	
	try{
		
	System.getProperties().put("https.proxyHost", "proxy.mpt.es");
	System.getProperties().put("https.proxyPort", "8080");
	
	
	
	
	ListaEntidades_Service llamada = new ListaEntidades_ServiceLocator();
	ListaEntidades_PortType lista;
	lista = llamada.getListaEntidades();
	PeticionListaEntidades peticion = new PeticionListaEntidades();
	peticion.setNIFPeticionario("S2833002E");
	
	GregorianCalendar  c = new GregorianCalendar();
	c.setTime(new Date());
	XMLGregorianCalendar date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
	peticion.setTimestamp(date2);
	RespuestaListaEntidades respuesta = lista.listaEntidades(peticion);
	Respuesta r = respuesta.getRespuesta();
	Entidad ent[] = r.getEntidades();
	listaEnt = Arrays.asList(ent);
	for(int i=0;i<listaEnt.size();i++){
		System.out.println(listaEnt.get(i).getCodigo());
		System.out.println(listaEnt.get(i).getDescripcion());
		System.out.println(listaEnt.get(i).getMediosPago());
		System.out.println(listaEnt.get(i).getTipo());
		System.out.println("********************************");
		System.out.println("********************************");
	}
	}
	catch(Exception e){
		
		e.getStackTrace();
		
	}
	return listaEnt;
	
	}
}
