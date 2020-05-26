package es.map.ipsc.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * El Class TestRegExp.
 */
public class TestRegExp {

	
	/**
	 * El metodo principal.
	 *
	 * @param args los argumentos
	 */
	public static void main(String args[]){

		String expRegular = "a [\\d]*";
		String expRegular2 = "[\\d]+";
		String descripcion = "En TALAMANCA DEL JARAMA a 19 de junio de 2013";
		Pattern pattern = Pattern.compile(expRegular);
        Matcher matcher = pattern.matcher(descripcion);

        List<String> listMatches = new ArrayList<String>();
        while(matcher.find()) {
            listMatches.add(matcher.group());
        }
        String comienzo = "";
        String comienzoFecha = "";
        if(listMatches.size()>0){
        	comienzo = listMatches.get(0);
        	pattern = Pattern.compile(expRegular2);
            matcher = pattern.matcher(comienzo);
            listMatches = new ArrayList<String>();
            while(matcher.find()) {
                listMatches.add(matcher.group());
            }
            if(listMatches.size()>0){
            	comienzo = listMatches.get(0);
            	comienzoFecha = descripcion.substring(descripcion.indexOf(comienzo));
            	comienzoFecha = comienzoFecha.replaceAll(" de ", "/");
            	System.out.println(comienzoFecha);
            }
        	
        }
	}
}
