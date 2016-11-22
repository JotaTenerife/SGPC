package es.llamalox.sgpc.web.usuarios;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import es.llamalox.sgpc.model.usuarios.Permiso;
import es.llamalox.sgpc.model.usuarios.Rol;
import es.llamalox.sgpc.service.usuarios.PermisoService;
import es.llamalox.sgpc.service.usuarios.RolService;
import es.llamalox.sgpc.web.BaseController;
import es.llamalox.sgpc.web.GenericController;

@Controller
@RequestMapping("/gestion/permiso")
@SessionAttributes("roles, loggedinuser, basepath, model")
public class PermisoController implements GenericController {

	private static final String MODEL = "Permiso";
	private static final String BASE = "/gestion/permiso";
	private static final String MESSAGE_START = "El permiso ";

	@Autowired
	private PermisoService service;

	@Autowired
	private RolService rolService;

	@Autowired
	private BaseController controller;

	@RequestMapping(method = RequestMethod.GET)
	public String list(ModelMap model) {
		List<Permiso> objetos = service.findAll();
		model.addAttribute("objetos", objetos);
		return MODEL + LIST;
	}

	@RequestMapping(value = { PATH }, method = RequestMethod.GET)
	public String view(@PathVariable(FIELD) String codigo, ModelMap model,
			RedirectAttributes attributes) {
		Permiso objeto = service.getByCodigo(codigo);
		if (objeto == null) {
			attributes.addFlashAttribute(ERROR, MESSAGE_START + codigo
					+ " no existe");
			return REDIRECT + BASE;
		}
		model.addAttribute(OBJETO, objeto);
		return MODEL + DISPLAY;
	}

	@RequestMapping(value = { "/crear" }, method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute(OBJETO) Permiso objeto,
			BindingResult result, ModelMap model, RedirectAttributes attributes) {
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
	public String edit(@PathVariable(FIELD) String codigo, ModelMap model) {
		Permiso objeto = service.getByCodigo(codigo);
		model.addAttribute(OBJETO, objeto);
		model.addAttribute(EDIT, true);
		return MODEL + FORM;
	}

	@RequestMapping(value = { PATH + "/editar" }, method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute(OBJETO) Permiso objeto,
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

	public RolService getRolService() {
		return rolService;
	}

	public void setRolService(RolService rolService) {
		this.rolService = rolService;
	}

	public BaseController getController() {
		return controller;
	}

	public void setController(BaseController controller) {
		this.controller = controller;
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
		return BASE;
	}

	@ModelAttribute("model")
	public String getModel() {
		return MODEL;
	}

}
