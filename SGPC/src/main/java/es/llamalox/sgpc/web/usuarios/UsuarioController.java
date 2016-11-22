package es.llamalox.sgpc.web.usuarios;

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

import es.llamalox.sgpc.model.usuarios.Rol;
import es.llamalox.sgpc.model.usuarios.Usuario;
import es.llamalox.sgpc.service.usuarios.RolService;
import es.llamalox.sgpc.service.usuarios.UsuarioService;
import es.llamalox.sgpc.web.BaseController;
import es.llamalox.sgpc.web.GenericController;

@Controller
@RequestMapping("/gestion/usuario")
@SessionAttributes("roles, loggedinuser, basepath, model")
public class UsuarioController implements GenericController {

	static final Logger LOGGER = LoggerFactory
			.getLogger(UsuarioController.class);

	private static final String MODEL = "Usuario";
	private static final String BASE = "/gestion/usuario";
	private static final String MESSAGE_START = "El usuario ";

	@Autowired
	private UsuarioService service;

	@Autowired
	private BaseController controller;

	@Autowired
	private RolService rolService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}

	@RequestMapping(method = RequestMethod.GET)
	public String list(ModelMap model) {
		List<Usuario> objetos = service.findAll();
		model.addAttribute("objetos", objetos);
		return MODEL + LIST;
	}

	@RequestMapping(value = { PATH }, method = RequestMethod.GET)
	public String view(@PathVariable(FIELD) String codigo, ModelMap model,
			RedirectAttributes attributes) {
		Usuario objeto = service.getByCodigo(codigo);
		if (objeto == null) {
			attributes.addFlashAttribute(ERROR, MESSAGE_START + codigo
					+ " no existe");
			return REDIRECT + BASE;
		}
		// model.addAttribute("tests", service.getTests(objeto));
		model.addAttribute(OBJETO, objeto);
		return MODEL + DISPLAY;
	}

	@RequestMapping(value = { "/crear" }, method = RequestMethod.GET)
	public String create(ModelMap model) {
		Usuario objeto = new Usuario();
		model.addAttribute(OBJETO, objeto);
		model.addAttribute(EDIT, false);
		return MODEL + FORM;
	}

	@RequestMapping(value = { "/crear" }, method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute(OBJETO) Usuario objeto,
			BindingResult result, ModelMap model, RedirectAttributes attributes) {
		LOGGER.info("Result : {}", result.toString());
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
			Usuario objeto = service.getByCodigo(codigo);
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
	public String update(@Valid @ModelAttribute(OBJETO) Usuario objeto,
			BindingResult result, ModelMap model,
			@PathVariable(FIELD) String codigo, RedirectAttributes attributes) {
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

	@ModelAttribute("roles")
	public List<Rol> initializeProfiles() {
		return rolService.findAll();
	}

	@ModelAttribute("loggedinuser")
	public String getLoggedInUser() {
		return controller.getCurrentUser();
	}

	@ModelAttribute("basepath")
	public String getPath() {
		return UsuarioController.BASE;
	}

	@ModelAttribute("model")
	public String getModel() {
		return MODEL;
	}
}
