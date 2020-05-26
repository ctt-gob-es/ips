package es.map.ipsc.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;

import es.map.ips.common.exception.ApplicationException;
import es.map.ips.common.exception.BusinessException;

/**
 * El Class CryptUtil.
 */
public class CryptUtil {
	
	/** el logger. */
	static Logger logger = Logger.getLogger(CryptUtil.class);
	
	/**
	 * Cifrar.
	 *
	 * @param pwd el pwd
	 * @return el string
	 */
	public String cifrar(String pwd){
		String resultado = null;
		try{
			resultado = cryptMD5(pwd);
		} catch (Exception e) {
			logger.error("Error cifrar",e);
		}
		return resultado;
	}
	
	/**
	 * Crypt MD 5.
	 *
	 * @param passwd el passwd
	 * @return el string
	 * @throws BusinessException el business exception
	 */
	private String cryptMD5(String passwd) throws BusinessException{
		MessageDigest algorithm = null;

        try{
            algorithm = MessageDigest.getInstance("MD5");
        }catch (NoSuchAlgorithmException nsae){
        	logger.error("Error cryptMD5",nsae);
        	throw new ApplicationException(nsae.getMessage());
        }
        
        byte[] defaultBytes = passwd.getBytes();
        algorithm.reset();
        algorithm.update(defaultBytes);
        byte messageDigest[] = algorithm.digest();
   
        String md5val = hexDump(messageDigest);
   
        return md5val;
	}
	
	/**
	 * Hex dump.
	 *
	 * @param messageDigest el message digest
	 * @return el string
	 */
	private String hexDump(byte[] messageDigest){
		
		StringBuffer hexString = new StringBuffer();

        for (int i = 0; i < messageDigest.length; i++)
        {
            String hex = Integer.toHexString(0xFF & messageDigest[i]);
            if (hex.length() == 1)
            {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        String md5val = hexString.toString();

        return md5val;
	}

}
