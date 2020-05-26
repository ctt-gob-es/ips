package es.map.ipsc.utils;

import org.apache.log4j.Logger;

/**
 * El Class UtilsBase64.
 */
public class UtilsBase64 {
 
    /** el base 64 code. */
    public static String base64code = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
 
        "abcdefghijklmnopqrstuvwxyz" + "0123456789" + "+/";
    
    /** La constante logger. */
    private static final Logger logger = Logger.getLogger(UtilsBase64.class);
 
    /** el split lines at. */
    public static int splitLinesAt = 76;
 
    /**
     * Zero pad.
     *
     * @param length el length
     * @param bytes el bytes
     * @return el byte[]
     */
    public static byte[] zeroPad(int length, byte[] bytes) {
        byte[] padded = new byte[length]; // initialized to zero by JVM
        System.arraycopy(bytes, 0, padded, 0, bytes.length);
        return padded;
    }
 
    /**
     * Encode.
     *
     * @param string el string
     * @return el string
     */
    public static String encode(String string) {
 
        String encoded = "";
        byte[] stringArray;
        try {
            stringArray = string.getBytes("UTF-8");  // use appropriate encoding string!
        } catch (Exception ignored) {
        	logger.error("Error encode: ",ignored);
            stringArray = string.getBytes();  // use locale default rather than croak
        }
        // determine how many padding bytes to add to the output
        int paddingCount = (3 - (stringArray.length % 3)) % 3;
        // add any necessary padding to the input
        stringArray = zeroPad(stringArray.length + paddingCount, stringArray);
        // process 3 bytes at a time, churning out 4 output bytes
        // worry about CRLF insertions later
        for (int i = 0; i < stringArray.length; i += 3) {
            int j = (stringArray[i] << 16) + (stringArray[i + 1] << 8) + 
                stringArray[i + 2];
            encoded = encoded + base64code.charAt((j >> 18) & 0x3f) +
                base64code.charAt((j >> 12) & 0x3f) +
                base64code.charAt((j >> 6) & 0x3f) +
                base64code.charAt(j & 0x3f);
        }
        // replace encoded padding nulls with "="
        return splitLines(encoded.substring(0, encoded.length() -
            paddingCount) + "==".substring(0, paddingCount));
 
    }
    
    /**
     * Split lines.
     *
     * @param string el string
     * @return el string
     */
    public static String splitLines(String string) {
 
        String lines = "";
        for (int i = 0; i < string.length(); i += splitLinesAt) {
 
            lines += string.substring(i, Math.min(string.length(), i + splitLinesAt));
            lines += "\r\n";
 
        }
        return lines;
 
    }

 
}
