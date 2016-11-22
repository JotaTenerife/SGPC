package es.llamalox.sgpc.web.tests;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import es.llamalox.sgpc.model.tests.Intento;
import es.llamalox.sgpc.model.tests.Pregunta;
import es.llamalox.sgpc.model.tests.Test;
import es.llamalox.sgpc.model.usuarios.Usuario;
import es.llamalox.sgpc.service.tests.IntentoService;
import es.llamalox.sgpc.service.tests.PreguntaService;
import es.llamalox.sgpc.service.tests.TestService;
import es.llamalox.sgpc.service.usuarios.UsuarioService;
import es.llamalox.sgpc.validators.TestValidator;
import es.llamalox.sgpc.web.BaseController;
import es.llamalox.sgpc.web.GenericController;

@Controller
@RequestMapping("/gestion/test")
@SessionAttributes("usuarios, preguntas, loggedinuser, basepath, model")
public class TestController implements GenericController {

	static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);

	private static final String MODEL = "Test";
	private static final String BASE = "/gestion/test";
	private static final String MESSAGE_START = "El test ";

	@Autowired
	private TestService service;

	@Autowired
	private BaseController controller;

	@Autowired
	private PreguntaService preguntaService;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private IntentoService intentoService;

	@Autowired
	private TestValidator testValidator;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}

	@RequestMapping(method = RequestMethod.GET)
	public String list(ModelMap model) {
		List<Test> objetos = service.findAll();
		model.addAttribute("objetos", objetos);
		return MODEL + LIST;
	}

	@RequestMapping(value = { PATH }, method = RequestMethod.GET)
	public String view(@PathVariable(FIELD) String codigo, ModelMap model,
			RedirectAttributes attributes) {
		Test objeto = service.getByCodigo(codigo);
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
		Test objeto = new Test();
		model.addAttribute(OBJETO, objeto);
		model.addAttribute(EDIT, false);
		return MODEL + FORM;
	}

	@RequestMapping(value = { "/crear" }, method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute(OBJETO) Test objeto,
			BindingResult result, ModelMap model, RedirectAttributes attributes) {
		testValidator.validate(objeto, result);
		if (result.hasErrors()) {
			model.addAttribute(ERROR,
					"El formulario contiene errores. Por favor, compruebe los campos.");
			return MODEL + FORM;
		}
		try {
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
			Test objeto = service.getByCodigo(codigo);
			boolean tieneIntentos = false;
			List<Intento> intentos = intentoService
					.getIntentos(objeto, usuarioService.getByCodigo(controller
							.getCurrentUserCode()));
			if (intentos.size() > 0) {
				tieneIntentos = true;
			}
			model.addAttribute(OBJETO, objeto);
			model.addAttribute("tieneIntentos", tieneIntentos);
			model.addAttribute(EDIT, true);
			return MODEL + FORM;
		} catch (Exception e) {
			attributes.addFlashAttribute(ERROR, MESSAGE_START + codigo
					+ " no existe");
			return REDIRECT + BASE;
		}
	}

	@RequestMapping(value = { PATH + "/editar" }, method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute(OBJETO) Test objeto,
			BindingResult result, ModelMap model,
			@PathVariable(FIELD) String codigo, RedirectAttributes attributes) {
		testValidator.validate(objeto, result);
		if (result.hasErrors()) {
			model.addAttribute(EDIT, true);
			model.addAttribute(ERROR,
					"El formulario contiene errores. Por favor, compruebe los campos.");
			return MODEL + FORM;
		}
		try {
			service.update(codigo, objeto);
		} catch (Exception e) {
			model.addAttribute(EDIT, true);
			model.addAttribute(ERROR, e.getCause().getCause());
			return MODEL + FORM;
		}
		attributes.addFlashAttribute(SUCCESS, MESSAGE_START + codigo
				+ " se ha actualizado correctamente");
		return REDIRECT + BASE;
	}

	@RequestMapping(value = { PATH + "/asignar" }, method = RequestMethod.GET)
	public String asignar(@PathVariable(FIELD) String codigo, ModelMap model,
			RedirectAttributes attributes) {
		try {
			Test objeto = service.getByCodigo(codigo);
			model.addAttribute(OBJETO, objeto);
			model.addAttribute(EDIT, true);
			return MODEL + FORM + "Asignar";
		} catch (Exception e) {
			attributes.addFlashAttribute(ERROR, MESSAGE_START + codigo
					+ " no existe");
			return REDIRECT + BASE;
		}
	}

	@RequestMapping(value = { PATH + "/asignar" }, method = RequestMethod.POST)
	public String updateAsignar(@ModelAttribute(OBJETO) Test objeto,
			BindingResult result, ModelMap model,
			@PathVariable(FIELD) String codigo, RedirectAttributes attributes) {
		LOGGER.info("Objeto : {}", objeto);
		if (result.hasErrors()) {
			model.addAttribute(EDIT, true);
			model.addAttribute(ERROR,
					"El formulario contiene errores. Por favor, compruebe los campos. "
							+ result.getAllErrors());
			return MODEL + FORM + "Asignar";
		}
		try {
			service.asignarIntentos(objeto);
		} catch (Exception e) {
			model.addAttribute(EDIT, true);
			model.addAttribute(ERROR, e.getMessage());
			return MODEL + FORM + "Asignar";
		}
		attributes.addFlashAttribute(SUCCESS, MESSAGE_START + codigo
				+ " se le han asignado los usuarios correctamente");
		return REDIRECT + BASE;
	}

	@RequestMapping(value = { PATH + "/borrar" }, method = RequestMethod.GET)
	public String delete(@PathVariable(FIELD) String codigo, ModelMap model,
			RedirectAttributes attributes) {
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

	@ModelAttribute("preguntas")
	public List<Pregunta> initializeProfiles() {
		return preguntaService.findAll();
	}

	@ModelAttribute("usuarios")
	public List<Usuario> getUsuarios() {
		return usuarioService.findAll();
	}

	@ModelAttribute("loggedinuser")
	public String getLoggedInUser() {
		return controller.getCurrentUser();
	}

	@ModelAttribute("basepath")
	public String getPath() {
		return TestController.BASE;
	}

	@ModelAttribute("model")
	public String getModel() {
		return MODEL;
	}
}
