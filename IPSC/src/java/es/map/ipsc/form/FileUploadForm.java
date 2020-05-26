package es.map.ipsc.form;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import es.map.ips.common.spring.SpringForm;
 
/**
 * El Class FileUploadForm.
 */
public class FileUploadForm extends SpringForm{
 
	/** justificante adjuntos */
	private List<MultipartFile> file1;
	/** justificante discapacidad */
	private List<MultipartFile> file10;
	/** justificante reduccion tasa */
	private List<MultipartFile> file6;
	private String numSolicitudFile;
	/** La constante MAX_FILES. */
	private static final int MAX_FILES = 20;
	/** el documento file. */
	private String[] documentoFile = new String[MAX_FILES];
	/** el tipo doc adjunto. */
	private String[] tipoDocAdjunto = new String[MAX_FILES];	
	/** el extracto. */
	private String[] extracto = new String[MAX_FILES];
	/** el tipo documento. */
	private String[] tipoDocumento = new String[MAX_FILES];

	public List<MultipartFile> getFile1() {
		return file1;
	}

	public void setFile1(List<MultipartFile> file1) {
		this.file1 = file1;
	}
	
	public List<MultipartFile> getFile10() {
		return file10;
	}

	public void setFile10(List<MultipartFile> file10) {
		this.file10 = file10;
	}

	public List<MultipartFile> getFile6() {
		return file6;
	}

	public void setFile6(List<MultipartFile> file6) {
		this.file6 = file6;
	}

	public String getNumSolicitudFile() {
		return numSolicitudFile;
	}

	public void setNumSolicitudFile(String numSolicitudFile) {
		this.numSolicitudFile = numSolicitudFile;
	}

	public String[] getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String[] tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String[] getDocumentoFile() {
		return documentoFile;
	}

	public void setDocumentoFile(String[] documentoFile) {
		this.documentoFile = documentoFile;
	}

	public String[] getTipoDocAdjunto() {
		return tipoDocAdjunto;
	}

	public void setTipoDocAdjunto(String[] tipoDocAdjunto) {
		this.tipoDocAdjunto = tipoDocAdjunto;
	}

	public String[] getExtracto() {
		return extracto;
	}

	public void setExtracto(String[] extracto) {
		this.extracto = extracto;
	}	
	
}
