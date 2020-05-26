package es.map.ipsg.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.CRC32;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.log4j.Logger;

/**
 * El Class Zipeador.
 */
public class Zipeador {

	/** el m base path. */
	private String m_basePath = null;
	
	/** el m dir. */
	private File m_dir = null;
	
	/** el m output file name. */
	private String m_OutputFileName;
	
	/** La constante logger. */
	private static final Logger logger  = Logger.getLogger(Zipeador.class);
	
	/**
	 * Crea una nueva zipeador.
	 */
	public Zipeador() {

	}

	// Esta clase obtiene los directorios y los comprime recursivamente
	

	/**
	 * Crea una nueva zipeador.
	 *
	 * @param directory el directory
	 * @param outputFileName el output file name
	 */
	public Zipeador(File directory, String outputFileName) {

		m_dir = directory;
		m_OutputFileName = outputFileName;
	}

	/**
	 * Comprimir.
	 *
	 * @throws Exception el exception
	 */
	public void comprimir() throws Exception {

		try (FileOutputStream zipFilename = new FileOutputStream(m_OutputFileName)
		){
			logger.info("m_OutputFileName: " +m_OutputFileName);
			logger.info("m_dir: " +m_dir.getPath());
			
			ZipOutputStream zipoutputstream = new ZipOutputStream(zipFilename);
			m_basePath = m_dir.getPath();
			comprimirDirectorio(m_dir, zipoutputstream);
			logger.info("Comprimido");
			zipoutputstream.setMethod(ZipOutputStream.DEFLATED);
			logger.info("Se va a hacer el close");
			zipoutputstream.close();
		}

		catch (Exception e) {
			logger.error("Error fallo la comprimir: ",e);
			throw new Exception("Algun fallo al comprimir: " + e);
		}
	}

	
	// Navega a través de los directorios

	/**
	 * Comprimir directorio.
	 *
	 * @param f el f
	 * @param zipoutputstream el zipoutputstream
	 */
	private void comprimirDirectorio(File f, ZipOutputStream zipoutputstream) {

		System.out.println(f);
		if (f.isDirectory()) {
			File[] files = f.listFiles();
			int i;
			for (i = 0; i < files.length; i++) {
				if (files[i].isDirectory()) {
					
					comprimirDirectorio(files[i], zipoutputstream);
				}
				if (files[i].isFile()) {
					
					anadirArchivo(files[i], zipoutputstream);
				}
			}
		}
		
	}

	// Comprime el archivo actual

	/**
	 * Anadir archivo.
	 *
	 * @param file el file
	 * @param zipoutputstream el zipoutputstream
	 */
	private void anadirArchivo(File file, ZipOutputStream zipoutputstream) {

		ZipEntry zipentry = new ZipEntry(file.getPath().substring(
				m_basePath.length() + 1));
		
		CRC32 crc32 = new CRC32();

		byte[] rgb = new byte[1024];
		int n;

		// Calculando CRC
		try (FileInputStream fileinputstream = new FileInputStream(file)){
			
			while ((n = fileinputstream.read(rgb)) > -1) {
				crc32.update(rgb, 0, n);
			}
			fileinputstream.close();
		}

		catch (Exception e) {

			logger.warn("Error calculando CRC:");
			logger.error("Error calculando CRC:",e);
		}

		
		zipentry.setSize(file.length());
		zipentry.setTime(file.lastModified());
		zipentry.setCrc(crc32.getValue());

		// Escribiendo datos
		try (FileInputStream fileinputstream = new FileInputStream(file)){
			zipoutputstream.putNextEntry(zipentry);			

			while ((n = fileinputstream.read(rgb)) > -1) {
				zipoutputstream.write(rgb, 0, n);
			}			
			zipoutputstream.closeEntry();
		}

		catch (Exception ex) {

			logger.warn("Error escribiendo datos:");
			logger.error("Error escribiendo datos",ex);
		}
	}

	/**
	 * Establece el directory.
	 *
	 * @param dir el nuevo directory
	 */
	public void setDirectory(File dir) {

		m_dir = dir;
	}

	/**
	 * Establece el output file name.
	 *
	 * @param FileName el nuevo output file name
	 */
	public void setOutputFileName(String FileName) {

		m_OutputFileName = FileName;
	}

	/**
	 * Obtiene el directory.
	 *
	 * @return el directory
	 */
	public File getDirectory() {

		return m_dir;
	}

	/**
	 * Obtiene el output file name.
	 *
	 * @return el output file name
	 */
	public String getOutputFileName() {

		return m_OutputFileName;
	}
	
	/**
	 * Obtiene el logger.
	 *
	 * @return the logger
	 */
	public static Logger getLogger() {
		return logger;
	}
}