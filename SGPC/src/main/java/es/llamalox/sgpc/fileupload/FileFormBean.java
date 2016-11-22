package es.llamalox.sgpc.fileupload;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class FileFormBean {

	private CommonsMultipartFile fichero;

	public CommonsMultipartFile getFichero() {
		return fichero;
	}

	public void setFichero(CommonsMultipartFile fichero) {
		this.fichero = fichero;
	}

	@Override
	public String toString() {
		return "FileFormBean [fichero=" + fichero + "]";
	}
	
}
