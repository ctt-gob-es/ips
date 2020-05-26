package es.map.ipsg.util;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * El Class CSVUtils.
 */
public class CSVUtils {

	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(SistemaFicheros.class);
	
	/** La constante DEFAULT_SEPARATOR. */
	private static final char DEFAULT_SEPARATOR = ';';

    /**
     * Write line.
     *
     * @param w el w
     * @param values el values
     * @throws IOException Señala que se ha producido una excepción de I/O.
     */
    public static void writeLine(Writer w, List<String> values) throws IOException {
        writeLine(w, values, DEFAULT_SEPARATOR, ' ');
    }

    /**
     * Write line.
     *
     * @param w el w
     * @param values el values
     * @param separators el separators
     * @throws IOException Señala que se ha producido una excepción de I/O.
     */
    public static void writeLine(Writer w, List<String> values, char separators) throws IOException {
        writeLine(w, values, separators, ' ');
    }

    /**
     * Follow CV sformat.
     *
     * @param value el value
     * @return el string
     */
    //https://tools.ietf.org/html/rfc4180
    private static String followCVSformat(String value) {

        String result = value;
        if (result.contains("\"")) {
            result = result.replace("\"", "\"\"");
        }
        return result;
    }

    /**
     * Write line.
     *
     * @param w el w
     * @param values el values
     * @param separators el separators
     * @param customQuote el custom quote
     * @throws IOException Señala que se ha producido una excepción de I/O.
     */
    public static void writeLine(Writer w, List<String> values, char separators, char customQuote) throws IOException {

        boolean first = true;

        //default customQuote is empty

        if (separators == ' ') {
            separators = DEFAULT_SEPARATOR;
        }

        StringBuilder sb = new StringBuilder();
        for (String value : values) {
            if (!first) {
                sb.append(separators);
            }
            if (customQuote == ' ') {
                sb.append(followCVSformat(value));
            } else {
                sb.append(customQuote).append(followCVSformat(value)).append(customQuote);
            }

            first = false;
        }
        sb.append("\r\n");
        w.append(sb.toString());
        logger.info(sb);
    }
}
