package es.map.ipsg.util;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

/**
 * El Class Validacion.
 */
public class Validacion {
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(Validacion.class);

	/**
	 * Crea una nueva validacion.
	 */
	public Validacion() { //Metodo vacio
	}
	
	/**
	 * Función que comprueba que el tamanyoMax sea mayor que el tamanyoMin.
	 *
	 * @param tamnyoMin el tamnyo min
	 * @param tamanyoMax el tamanyo max
	 * @return verdadero, si tiene exito
	 */
	public boolean tamanyoValidate(String tamnyoMin, String tamanyoMax){
		return Integer.parseInt(tamnyoMin)>Integer.parseInt(tamanyoMax);
	}
	
	/**
	 * Función que valida una hora.
	 *
	 * @param hora el hora
	 * @return verdadero, si tiene exito
	 */
	public boolean horasValidate(String hora){
		if (validateNumero(hora)){
			return true;
		}else if (Integer.parseInt(hora)<0||Integer.parseInt(hora)>23){
			return true;
		}
		return false;			
	}
	
	/**
	 * Función que valida los minutos.
	 *
	 * @param minutos el minutos
	 * @return verdadero, si tiene exito
	 */
	public boolean minutosValidate(String minutos){
		if (validateNumero(minutos)){
			return true;
		}else if (Integer.parseInt(minutos)<0||Integer.parseInt(minutos)>59){
			return true;
		}
		return false;			
	}
	
	/**
	 * Funcion que valida que un NIF sea correcto.
	 *
	 * @param nif el nif
	 * @return verdadero, si tiene exito
	 */
	public boolean nifValidate(String nif) {
		//Si el valor es null, lo transformamos en un valor vacío
		String valor = nif;
		if(valor == null){
			valor = "";
		}
		int posicion_letra = 0;
		
		//Se pasan las letras a mayusculas
		valor=valor.toUpperCase();
		if(valor.length() == 9){
			//El valor debe tener 9 posiciones de las cuales los 8 primeros son numeros
	    	Pattern mask = Pattern.compile("[0-9]{8}[ABCDEFGHJKLMNPQRSTVWXYZ]{1}");
	    	Matcher matcher = mask.matcher(valor);
		 
	    	if(!matcher.matches()){
	    		 return false;
		    }
	    	 
			posicion_letra = Integer.parseInt(valor.substring(0,8))%23;
			String digitoControl = valor.substring(8,9);
			String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
			String digitoControlCalculado = letras.substring(posicion_letra,posicion_letra+1);
			
			if(!digitoControl.equals(digitoControlCalculado)){
				return false;
			}
		}else{
			return false;
		}
		return true;
	}

	/**
	 * Funcion que valida que un NIE sea correcto.
	 *
	 * @param nie el nie
	 * @return verdadero, si tiene exito
	 */
	public boolean nieValidate(String nie) {
		//Si el valor es null, lo transformamos en un valor vacío
		String valor = nie;
		if(valor == null){
			valor = "";
		}
		int posicion_letra = 0;
		
		//Se pasan las letras a mayusculas
		valor=valor.toUpperCase();
		if(valor.length() == 9){
			//El valor debe tener 9 posiciones de las cuales la primera debe ser una X, los 7 siguientes 
			//son numeros y la ultima letra
			Pattern mask = Pattern.compile("[XYZ][0-9]{7}[ABCDEFGHJKLMNPQRSTVWXYZ]{1}");
	    	Matcher matcher = mask.matcher(valor);
		 
	    	if(!matcher.matches()){
	    		 return false;
		    }
	    	
	    	if(nie.startsWith("X"))
	    		valor = nie.replaceFirst("X", "0");
	    	else if(nie.startsWith("Y"))
	    		valor = nie.replaceFirst("Y", "1");
	    	else if(nie.startsWith("Z"))
	    		valor = nie.replaceFirst("Z", "2");
	    	
			posicion_letra = Integer.parseInt(valor.substring(0,8))%23;
			String digitoControl = valor.substring(8,9);
			String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
			String digitoControlCalculado = letras.substring(posicion_letra,posicion_letra+1);
			
			if(!digitoControl.equals(digitoControlCalculado)){
				return false;
			}
		}else{
			return false;
		}
		return true;
	}
	
	/**
	 * Funcion que valida que un .
	 *
	 * @param cif el cif
	 * @return verdadero, si tiene exito
	 */
	public boolean cifValidate(String cif) {
		// Si el valor es null, lo transformamos en un valor vacío
		if(cif == null){
			cif = "";
		}

		//pasamos las letras que haya en la cadena del CIF a mayúsculas
		cif=cif.toUpperCase();
		
		// el valor debe tener 9 posiciones, de las cuales la primera ha de ser una letra, las siete siguientes números y la última un número o una letra
		Pattern mask = Pattern.compile("[ABCDEFGHKLMNPQS]{1}[0-9]{7}[0-9[A-J]]{1}");
		Matcher matcher = mask.matcher(cif);
		
		if(!matcher.matches()){
			return false;
		}
		
		String digitoControl = cif.substring(8,9);
		
		//Pasos para la validación del dígito de control del cif
		//1. Se suman las posiciones pares de 'cif'
		//2. Se multiplican las posiciones pares multiplicadas por dos, se suman los digitos y se suman entre si para las posiciones impares
		int suma_par = 0;
		int aux_impar = 0;
		int suma_impar = 0;
		//partiendo de la cadena formada por el cif completo:
		for (int i=1; i<7; i=i+2){
			aux_impar = Integer.parseInt(cif.substring(i,i+1))*2;
			suma_impar = suma_impar + (aux_impar / 10) + (aux_impar % 10);
			suma_par = suma_par + Integer.parseInt(cif.substring(i+1,i+2));
		}
		//nos falta el último dígito impar
		aux_impar = Integer.parseInt(cif.substring(7,8))*2;
		suma_impar = suma_impar + (aux_impar / 10) + (aux_impar % 10);
		
		//3. se suman ambas, y de la suma se seleccionan las unidades
		String suma = String.valueOf(suma_par + suma_impar);
		
		int unidad_suma = Integer.parseInt(suma.substring(suma.length()-1,suma.length()));
			
		//4. Se resta a 10 la unidad de la suma y ese o su letra asociada es el dígito de control
		String[] dig_letra = {"J","A","B","C","D","E","F","G","H","I","J"};
		String dig_num = String.valueOf(10 - unidad_suma);
		
		if (dig_num.equals("10")){
		  dig_num = "0";
		}
		
		if ( !(digitoControl.equals(dig_num)) && !(digitoControl.equals(dig_letra[Integer.parseInt(dig_num)])) ){
			return false;
		}
		return true;
	}
	
	/**
	 * Funcion que valida cualquier cadena a partir de un patron.
	 *
	 * @param patron el patron
	 * @param cadena el cadena
	 * @return verdadero, si tiene exito
	 */
	public boolean validateCadena(String patron, String cadena) {
		
		Pattern p;
		Matcher m;
		StringBuffer sb;
		boolean resultado;
		boolean caracteresIlegales = false;

		p = Pattern.compile(patron);
		m = p.matcher(cadena);
		sb = new StringBuffer();
		resultado = m.find();
		caracteresIlegales = false;

		while(resultado) {
			caracteresIlegales = true;
			m.appendReplacement(sb, "");
			resultado = m.find();
		}
		
		return !caracteresIlegales;
	}
	
	/**
	 * Validate email.
	 *
	 * @param cadena el cadena
	 * @return verdadero, si tiene exito
	 */
	public boolean validateEmail(String cadena) {
		
		if((cadena != null) && (!"".equals(cadena))){
			// comprueba que no empieze por punto o @
			Pattern p = Pattern.compile("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*((\\.[A-Za-z]{2,4}){1}$)");
			Matcher m = p.matcher(cadena);
			if (!m.find()){
				return true;
			}else{
				return false;
			}
		}
		return false;
	}

	/**
	 * Valida que una cadena contega solo caracteres permitidos.
	 * Patron: [^a-zA-Z0-9.:_/\\-\\s]+  
	 *
	 * @param cadena el cadena
	 * @return verdadero, si tiene exito
	 */
	public boolean validateNumRef(String cadena) {
		
		Pattern p = Pattern.compile("[^a-zA-Z0-9.:_/\\-\\s]+");
		Matcher m = p.matcher(cadena);
		
		return m.find();
	}
	
	
	/**
	 * Valida que una cadena contega solo caracteres alfanumericos.
	 * Patron: [^A-Za-z0-9\\s]+  
	 *
	 * @param cadena el cadena
	 * @return verdadero, si tiene exito
	 */
	public boolean validateCodigo(String cadena) {
		
		Pattern p = Pattern.compile("[^A-Za-z0-9\\s]+");  ;
		Matcher m = p.matcher(cadena);
		
		return m.find();
	}
	
	
	/**
	 * Valida que una cadena contega solo caracteres permitidos.
	 * Patron: [^a-zA-Z0-9áéíóúÁÉÍÓÚÑñüÜçÇ'\"_.,:\\-\\(\\)\\ª\\º\\s]+  
	 *
	 * @param cadena el cadena
	 * @return verdadero, si tiene exito
	 */
	public boolean validateDescripcion(String cadena) {
		
		Pattern p = Pattern.compile("[^a-zA-Z0-9áéíóúÁÉÍÓÚÑñüÜçÇ'\"_.,:\\-\\(\\)\\ª\\º\\s]+");
		Matcher m = p.matcher(cadena);
		
		return m.find();
	}

	
	/**
	 * Valida que una cadena contega solo caracteres permitidos para apellidos.
	 * Patron: [^a-zA-Z0-9áéíóúÁÉÍÓÚÑñüÜçÇ'.,:\\-\\ª\\º\\s]+  
	 *
	 * @param cadena el cadena
	 * @return verdadero, si tiene exito
	 */
	public boolean validateNombre(String cadena) {
		
		Pattern p = Pattern.compile("[^a-zA-Z0-9áéíóúÁÉÍÓÚÑñüÜçÇ'.,:\\-\\ª\\º\\s]+");
		Matcher m = p.matcher(cadena);
		
		return m.find();
	}
	
	
	/**
	 * Valida que una cadena contega solo caracteres permitidos para apellidos.
	 * Patron: [^a-zA-Z0-9áéíóúÁÉÍÓÚÑñüÜçÇ'.,:\\-\\s]+  
	 *
	 * @param cadena el cadena
	 * @return verdadero, si tiene exito
	 */
	public boolean validateApellido(String cadena) {
		
		Pattern p = Pattern.compile("[^a-zA-Z0-9áéíóúÁÉÍÓÚÑñüÜçÇ'.,:\\-\\s]+");
		Matcher m = p.matcher(cadena);
		
		return m.find();
	}
	
	
	/**
	 * Valida que una cadena contega solo caracteres permitidos para apellidos.
	 * Patron: [^a-zA-Z0-9áéíóúÁÉÍÓÚÑñüÜçÇ'\".,:/\\-\\s]+  
	 *
	 * @param cadena el cadena
	 * @return verdadero, si tiene exito
	 */
	public boolean validateInteresado(String cadena) {
		
		Pattern p = Pattern.compile("[^a-zA-Z0-9áéíóúÁÉÍÓÚÑñüÜçÇ'\".,:/\\-\\s]+");
		Matcher m = p.matcher(cadena);
		
		return m.find();
	}
	
	
	/**
	 * Valida que una cadena contega solo caracteres permitidos para apellidos.
	 * Patron: [^a-zA-Z0-9áéíóúÁÉÍÓÚÑñüÜçÇ'\"_.,:¡!¿?/\\-\\s]+  
	 *
	 * @param cadena el cadena
	 * @return verdadero, si tiene exito
	 */
	public boolean validateText(String cadena) {
		
		Pattern p = Pattern.compile("[^a-zA-Z0-9áéíóúÁÉÍÓÚÑñüÜçÇ'\"_.,:¡!¿?/\\-\\s]+");
		Matcher m = p.matcher(cadena);
		
		return m.find();
	}
	
	
	/**
	 * Valida que una cadena numero contega solo numeros.
	 * Patron: [^0-9]+
	 *
	 * @param cadena el cadena
	 * @return verdadero, si tiene exito
	 */
	public boolean validateTelefono(String cadena) {
		
		Pattern p = Pattern.compile("[^0-9]+");
		Matcher m = p.matcher(cadena);
		
		return m.find();
	}
	
	/**
	 * Valida que una cadena empieza por 6,7 (numero movil) o 9,8 (numero fijo) 
	 * Patron: [^0-9]+.
	 *
	 * @param cadena el cadena
	 * @return verdadero, si tiene exito
	 */
	public boolean validateTelefonoFijoMovil(String cadena) {
		
		return !( (cadena.charAt(0) == '6') || (cadena.charAt(0) == '7') || (cadena.charAt(0) == '8') || (cadena.charAt(0) == '9') );

		
		
	}
	
	/**
	 * Valida que una cadena numero contega solo numeros.
	 * Patron: [^0-9]+
	 *
	 * @param cadena el cadena
	 * @return verdadero, si tiene exito
	 */
	public boolean validateNumero(String cadena) {
		
		Pattern p = Pattern.compile("^[0-9]+$");
		Matcher m = p.matcher(cadena);
		
		return m.find();
	}
	
	
	/**
	 * Valida que una cadena direccion contega solo caracteres permitidos.
	 * Patron: [^a-zA-Z0-9áéíóúÁÉÍÓÚÑñüÜçÇ'.,:;¡!¿?/\\-\\ª\\º\\s]+
	 *
	 * @param cadena el cadena
	 * @return verdadero, si tiene exito
	 */
	public boolean validateDireccion(String cadena) {
		
		Pattern p = Pattern.compile("[^a-zA-Z0-9áéíóúÁÉÍÓÚÑñüÜçÇ'.,:;¡!¿?/\\-\\ª\\º\\s]+");
		Matcher m = p.matcher(cadena);
		
		return m.find();
	}


	/**
	 * Valida que una cadena contega solo caracteres permitidos para nombres.
	 * Patron: [^a-zA-Z0-9áéíóúÁÉÍÓÚÑñüÜçÇ'.,:\\-\\ª\\º\\s]+  
	 *
	 * @param cadena el cadena
	 * @return verdadero, si tiene exito
	 */
	public boolean validateNameDoc(String cadena) {
		
		Pattern p = Pattern.compile("[^a-zA-Z0-9áéíóúÁÉÍÓÚÑñüÜçÇ'.,:\\-\\ª\\º\\s]+");
		Matcher m = p.matcher(cadena);
		
		return m.find();
	}
    
    /**
     * Este metodo devuelve true si la direccion ip pasada como parametro
     * es valida o false en caso contrario.
     *
     * @param dir String
     * @return boolean
     */
    public boolean esUnaDireccionIPValida( String dir ) {
        //En java un StringTokenizer es una version mejorada del String
        //  q permite darle formato a las cadenas. Al llamar el constructor
        //  y pasar por ej. la direccion="255.192.168.1" y el String punto "." lo q
        //  hago es separar la ip y dejar a st={"255","192","168","1"}
        StringTokenizer st = new StringTokenizer( dir, "." );
 
        //ahora verifico q si el tamaño de st no es 4, osea, los 4 numeros
        //  q debe tener toda direcicon ip, digo q esta mal la direccion ip.
        if ( st.countTokens() != 4 ) {
            return false;
        }
        
        //ahora sigo verificando, y le digo a st con el metodo nextTokens()
        //  q me de uno por uno sus elementos, es decir, los 4 numeros de las
        //  de la direcicon ip. Luego verifo q el numero tenga solo 1 o 3 digitos y
        //  q este entre 0 y 255. Y listo, eso es todo.
        while ( st.hasMoreTokens() ) {
            String nro = st.nextToken();
            if ( ( nro.length() > 3 ) || ( nro.length() < 1 ) ) {
                return false;
            }
            int nroInt = 0;
            try {
                nroInt = Integer.parseInt( nro );
            }
            catch ( NumberFormatException s ) {
            	logger.error("Error parsear numero: "+  nroInt,s);
                return false;
            }
            if ( ( nroInt < 0 ) || ( nroInt > 255 ) ) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Valida que una cadena contega solo caracteres permitidos para URL (no contenga " o '.
     * Patron: [^a-zA-Z0-9áéíóúÁÉÍÓÚÑñüÜçÇ'\"\\'s]+  
     *
     * @param cadena el cadena
     * @return verdadero, si tiene exito
     */
	public boolean validateURL(String cadena) {
		
		Pattern p = Pattern.compile("[^a-zA-Z0-9áéíóúÁÉÍÓÚÑñüÜçÇ_.,:¡!¿?/\\-]+");
		Matcher m = p.matcher(cadena);
		
		return m.find();
	}
    
    /**
     * Valida que una cadena contega no contenga espacios en blanco.
     *
     * @param cadena el cadena
     * @return verdadero, si tiene exito
     */
	public boolean validateEspacios(String cadena) {
		
		String obj[] = cadena.split(" ");
		if(cadena.contains(" ")){
			return false;
		}
		else
			return true;
	}
	
	/**
	 * Comprueba si es fecha valida.
	 *
	 * @param fechax el fechax
	 * @return verdadero, si es fecha valida
	 */
	public boolean isFechaValida(String fechax) {
		try {
			SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy",Locale.getDefault());
			if (fechax.trim().length() != formatoFecha.toPattern().length())
			{
				return false;
			}

			formatoFecha.setLenient(false);
			formatoFecha.parse(fechax);
		} catch (ParseException e) {
			logger.error("Error parsear fecha: "+  fechax,e);
			return false;
		}

		return true;
	}
	
	/**
	 * Validate tamanio cadena.
	 *
	 * @param cadena el cadena
	 * @param longitud el longitud
	 * @return verdadero, si tiene exito
	 */
	public boolean validateTamanioCadena (String cadena, int longitud){

		if (cadena.length()!=longitud){
			return false;
		}
		return true;	
	
	}

	/**
	 * Valida que el tamaño de la Cadena sea menor al de la longitud pasada.
	 *
	 * @param cadena el cadena
	 * @param longitudTextArea el longitud text area
	 * @return verdadero, si tiene exito
	 */
	public boolean validateTamanioTextArea (String cadena, int longitudTextArea){
		
		if (cadena.length() > longitudTextArea){
			return false;
		}
		return true;	
	
	}
	
	/**
	 * Comprueba si es numeric.
	 *
	 * @param cadena el cadena
	 * @return verdadero, si es numeric
	 */
	public boolean isNumeric (String cadena){
		
		try {		
			Double.parseDouble(cadena);
			return true;		
		} catch (NumberFormatException nfe){
			logger.error("Error parsear: "+  cadena,nfe);
			return false;		
		}	
	}
	
	/**
	 * Comprueba si es f loat.
	 *
	 * @param cadena el cadena
	 * @return verdadero, si es f loat
	 */
	public boolean isFLoat (String cadena){
		
		try {
			Float.parseFloat(cadena);				
			return true;		
		} catch (NumberFormatException nfe){
			logger.error("Error parsear: "+  cadena,nfe);
			return false;		
		}	
	}
	
}

