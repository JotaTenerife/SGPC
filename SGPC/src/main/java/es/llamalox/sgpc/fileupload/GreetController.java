package es.llamalox.sgpc.fileupload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Controller
@RequestMapping("greet")
public class GreetController {

	@Autowired
	ServletContext servletContext;

	@RequestMapping(method = RequestMethod.GET)
	public @ModelAttribute("fileFormBean") FileFormBean getInitialMessage() {
		return new FileFormBean();
	}

	@RequestMapping(method = RequestMethod.POST)
	public @ModelAttribute("message") String guardaFichero(
			@ModelAttribute FileFormBean fileFormBean) {

		try {
			grabarFicheroALocal(fileFormBean);
		} catch (Exception e) {
			e.printStackTrace();
			return "No se ha podido grabar el fichero";
		}

		return "Fichero grabado correctamente";
	}

	private void grabarFicheroALocal(FileFormBean fileFormBean)
			throws Exception {
		CommonsMultipartFile uploaded = fileFormBean.getFichero();
		// esta ruta es donde está ubicado el servidor 'jboss'
		File localFile = new File(
				"C:/ATOS/atos-dev/jboss-as-7.1.1.Final/welcome-content/img/"
						+ uploaded.getOriginalFilename());
		FileOutputStream os = null;

		try {

			os = new FileOutputStream(localFile);
			os.write(uploaded.getBytes());

		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
