package es.llamalox.sgpc.rest.usuarios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import es.llamalox.sgpc.model.usuarios.Usuario;
import es.llamalox.sgpc.service.usuarios.UsuarioService;

@RestController
@RequestMapping("/rest/gestion/usuario")
public class UsuarioRestController {

	private static final String PATH = "/{codigo}";
	private static final String FIELD = "codigo";

	@Autowired
	private UsuarioService service;

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Usuario> list() {
		return service.findAll();
	}

	@RequestMapping(value = PATH, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Usuario get(@PathVariable(FIELD) String codigo) {
		return service.getByCodigo(codigo);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> create(@RequestBody Usuario body) {
		Usuario resultado = service.save(body);
		if (resultado != null) {
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setLocation(ServletUriComponentsBuilder
					.fromCurrentRequest().path(PATH)
					.buildAndExpand(resultado.getCodigo()).toUri());
			return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}

	@RequestMapping(value = PATH, method = RequestMethod.PUT)
	public ResponseEntity<?> edit(@PathVariable(FIELD) String codigo,
			@RequestBody Usuario body) {
		Usuario resultado;
		try {
			resultado = service.update(codigo, body);
		} catch (Exception e) {
			resultado = null;
		}
		if (resultado != null) {
			return ResponseEntity.status(HttpStatus.OK).body(null);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}

	@RequestMapping(value = PATH, method = RequestMethod.DELETE)
	public ResponseEntity<?> delete(@PathVariable(FIELD) String codigo) {
		boolean resultado = service.deleteByCodigo(codigo);
		if (resultado) {
			return ResponseEntity.status(HttpStatus.OK).body(null);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}

}
