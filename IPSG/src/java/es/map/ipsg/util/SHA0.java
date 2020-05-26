package es.map.ipsg.util;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * El Class SHA0.
 */
public class SHA0 {
    
    /** el md. */
    private MessageDigest md;
    
    /** el digest. */
    private byte[] buffer, digest;
    
    /** el hash. */
    private String hash = "";

    /**
     * Obtiene el hash.
     *
     * @param message el message
     * @return el hash
     * @throws NoSuchAlgorithmException el no such algorithm exception
     */
    public String getHash(byte[] message) throws NoSuchAlgorithmException {

    	buffer = message;
        md = MessageDigest.getInstance("MD5");
        md.update(buffer);
        digest = md.digest();
        
        hash = "";
        for(byte aux : digest) {
            int b = aux & 0xff;
            if (Integer.toHexString(b).length() == 1) hash += "0";
            hash += Integer.toHexString(b);
        }

        return hash;
    }
}