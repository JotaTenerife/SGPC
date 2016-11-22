package es.llamalox.sgpc.web.tests;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.AutoPopulatingList;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import es.llamalox.sgpc.fileupload.FileFormBean;
import es.llamalox.sgpc.model.tests.Intento;
import es.llamalox.sgpc.model.tests.Pregunta;
import es.llamalox.sgpc.model.tests.Respuesta;
import es.llamalox.sgpc.service.tests.IntentoService;
import es.llamalox.sgpc.service.tests.PreguntaService;
import es.llamalox.sgpc.service.usuarios.UsuarioService;
import es.llamalox.sgpc.validators.PreguntaValidator;
import es.llamalox.sgpc.web.BaseController;
import es.llamalox.sgpc.web.GenericController;

@Controller
@RequestMapping("/gestion/pregunta")
@SessionAttributes("correcta, loggedinuser, basepath, model")
public class PreguntaController implements GenericController {

	static final Logger LOGGER = LoggerFactory
			.getLogger(PreguntaController.class);

	private static final String MODEL = "Pregunta";
	private static final String BASE = "/gestion/pregunta";
	private static final String MESSAGE_START = "La pregunta";

	@Autowired
	private PreguntaService service;

	@Autowired
	private IntentoService intentoService;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private BaseController controller;

	@Autowired
	private PreguntaValidator preguntaValidator;

	@Autowired
	private ServletContext servletContext;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}

	@RequestMapping(method = RequestMethod.GET)
	public String list(ModelMap model) {
		List<Pregunta> objetos = service.findAll();
		model.addAttribute("objetos", objetos);
		return MODEL + LIST;
	}

	@RequestMapping(value = { PATH }, method = RequestMethod.GET)
	public String view(@PathVariable(FIELD) String codigo, ModelMap model,
			RedirectAttributes attributes) {
		Pregunta objeto = service.getByCodigo(codigo);
		if (objeto == null) {
			attributes.addFlashAttribute(ERROR, MESSAGE_START + codigo
					+ " no existe");
			return REDIRECT + BASE;
		}
		model.addAttribute(OBJETO, objeto);
		return MODEL + DISPLAY;
	}

	@RequestMapping(value = { "/crear" }, method = RequestMethod.GET)
	public String create(ModelMap model) {
		Pregunta objeto = new Pregunta();
		objeto.setRespuestas(new AutoPopulatingList<Respuesta>(Respuesta.class));
		model.addAttribute(OBJETO, objeto);
		model.addAttribute(EDIT, false);
		return MODEL + FORM;
	}

	@RequestMapping(value = { "/crear" }, method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute(OBJETO) Pregunta objeto,
			BindingResult result, ModelMap model,
			RedirectAttributes attributes,
			@ModelAttribute FileFormBean fileFormBean) {
		preguntaValidator.validate(objeto, result);
		if (result.hasErrors()) {
			model.addAttribute(ERROR,
					"El formulario contiene errores. Por favor, compruebe los campos.");
			return MODEL + FORM;
		}
		try {
			if (fileFormBean.getFichero() == null
					|| fileFormBean.getFichero().isEmpty()) {
				// No file was been selected.
			} else {
				grabarFicheroALocal(fileFormBean);
				objeto.setImagen(("/sgpc/static/imagenes/" + fileFormBean
						.getFichero().getOriginalFilename()));
			}
			LOGGER.info("objeto : {}", objeto);
			service.save(objeto);
		} catch (Exception e) {
			model.addAttribute(ERROR, e.getCause().getCause());
			return MODEL + FORM;
		}
		attributes.addFlashAttribute(SUCCESS,
				MESSAGE_START + objeto.getCodigo()
						+ " se ha creado correctamente");
		return REDIRECT + BASE;
	}

	@RequestMapping(value = { PATH + "/editar" }, method = RequestMethod.GET)
	public String edit(@PathVariable(FIELD) String codigo, ModelMap model,
			RedirectAttributes attributes) {
		try {
			Pregunta objeto = service.getByCodigo(codigo);
			List<Intento> intentos = intentoService.getIntentos(objeto);
			LOGGER.info("intentos : {}", intentos);
			if (intentos.size() > 0) {
				attributes
						.addFlashAttribute(ERROR,
								"La pregunta no puede ser modificada porque ya ha sido asignada");
				return REDIRECT + BASE;
			}
			model.addAttribute(OBJETO, objeto);
			model.addAttribute(EDIT, true);
			return MODEL + FORM;
		} catch (Exception e) {
			attributes.addFlashAttribute(ERROR, MESSAGE_START + codigo
					+ " no existe");
			return REDIRECT + BASE;
		}
	}

	@RequestMapping(value = { PATH + "/editar" }, method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute(OBJETO) Pregunta objeto,
			BindingResult result, ModelMap model,
			@PathVariable(FIELD) String codigo, RedirectAttributes attributes,
			@ModelAttribute FileFormBean fileFormBean) {
		preguntaValidator.validate(objeto, result);
		if (result.hasErrors()) {
			model.addAttribute(EDIT, true);
			model.addAttribute(ERROR,
					"El formulario contiene errores. Por favor, compruebe los campos.");
			return MODEL + FORM;
		}
		try {

			if (fileFormBean.getFichero() == null
					|| fileFormBean.getFichero().isEmpty()) {
				// No file was been selected.
			} else {
				grabarFicheroALocal(fileFormBean);
				objeto.setImagen(("/sgpc/static/imagenes/" + fileFormBean
						.getFichero().getOriginalFilename()));
			}
			service.update(codigo, objeto);

		} catch (Exception e) {
			model.addAttribute(EDIT, true);
			model.addAttribute(ERROR, e.getMessage());
			return MODEL + FORM;
		}

		attributes.addFlashAttribute(SUCCESS, MESSAGE_START + codigo
				+ " se ha actualizado correctamente");
		return REDIRECT + BASE;
	}

	@RequestMapping(value = { PATH + "/borrar" }, method = RequestMethod.GET)
	public String delete(@PathVariable(FIELD) String codigo, ModelMap model,
			RedirectAttributes attributes) {
		Pregunta objeto = service.getByCodigo(codigo);
		List<Intento> intentos = intentoService.getIntentos(objeto);
		LOGGER.info("intentos : {}", intentos);
		if (intentos.size() > 0) {
			attributes
					.addFlashAttribute(ERROR,
							"La pregunta no puede ser modificada porque ya ha sido asignada");
			return REDIRECT + BASE;
		}
		boolean success = service.deleteByCodigo(codigo);
		if (success) {
			attributes.addFlashAttribute(SUCCESS, MESSAGE_START + codigo
					+ " se ha dado de baja correctamente");
		} else {
			attributes.addFlashAttribute(ERROR, MESSAGE_START + codigo
					+ " no ha podido darse de baja");
		}
		return REDIRECT + BASE;
	}

	@RequestMapping(value = { PATH + "/reactivar" }, method = RequestMethod.GET)
	public String reactivate(@PathVariable(FIELD) String codigo,
			ModelMap model, RedirectAttributes attributes) {
		boolean success = service.reactivateByCodigo(codigo);
		if (success) {
			attributes.addFlashAttribute(SUCCESS, MESSAGE_START + codigo
					+ " se ha reactivado correctamente");
		} else {
			attributes.addFlashAttribute(ERROR, MESSAGE_START + codigo
					+ " no ha podido reactivarse");
		}
		return REDIRECT + BASE;
	}

	public PreguntaValidator getPreguntaValidator() {
		return preguntaValidator;
	}

	public void setPreguntaValidator(PreguntaValidator preguntaValidator) {
		this.preguntaValidator = preguntaValidator;
	}

	// GUARDAR FICHERO *********************************
	private void grabarFicheroALocal(FileFormBean fileFormBean)
			throws Exception {
		CommonsMultipartFile uploaded = fileFormBean.getFichero();
		// esta ruta es donde está ubicado el servidor 'jboss'
		File localFile = new File(
				servletContext.getRealPath("/static/imagenes/"
						+ uploaded.getOriginalFilename()));
		FileOutputStream os = null;

		try {
			if (os == null) {
				os = new FileOutputStream(localFile);
				os.write(uploaded.getBytes());
			}

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

	// FIN GUARDAR FICHERO **************************************

	@ModelAttribute("correcta")
	public Map<Integer, String> opcionesCorrecta() {
		Map<Integer, String> hmap = new HashMap<Integer, String>();
		hmap.put(0, "No");
		hmap.put(1, "Sí");
		return hmap;
	}

	public ServletContext getServletContext() {
		return servletContext;
	}

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	@ModelAttribute("loggedinuser")
	public String getLoggedInUser() {
		return controller.getCurrentUser();
	}

	@ModelAttribute("basepath")
	public String getPath() {
		return PreguntaController.BASE;
	}

	@ModelAttribute("model")
	public String getModel() {
		return MODEL;
	}
}
