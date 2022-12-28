package jardineria.jardineriapf.controladores;

import jardineria.jardineriapf.dto.PublicacionesDto;
import jardineria.jardineriapf.entidades.*;
import jardineria.jardineriapf.servicios.*;
import java.io.*;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@RequestMapping("publicaciones")
public class PublicacionControlador implements WebMvcConfigurer {

	@Autowired
	PublicacionServicio publicacionServicio;

	@Autowired
	PlantaServicio plantaServicio;

	@Autowired
	UsuarioServicio usuarioServicio;



	// @GetMapping
	// private List<Publicacion> index() {
	// 	return publicacionServicio.findAll();
	// }

	// @PostMapping
	// @ResponseStatus(HttpStatus.CREATED)
	// private Publicacion create(@RequestBody Publicacion publicacion) {
	// 	return publicacionServicio.save_Publicacion(publicacion);
	// }

	// @PutMapping("{id}")
	// @ResponseStatus(HttpStatus.OK)
	// private Publicacion update(@PathVariable Long id, @RequestBody Publicacion publicacion) {
	// 	return publicacionServicio.update_Publicacion(id, publicacion);
	// }

	// @DeleteMapping("{id}")
	// @ResponseStatus(HttpStatus.NO_CONTENT)
	// private Publicacion delete(@PathVariable Long id) {
	// 	return publicacionServicio.delete_Publicacion(id);
	// }

	@GetMapping("/home")
	private ModelAndView index_AndView() {
		ModelAndView maw = new ModelAndView();
		maw.setViewName("fragments/base");
		maw.addObject("titulo", "Listado de Publicaciones");
		maw.addObject("vista", "publicaciones/index");
		maw.addObject("publicaciones", publicacionServicio.getAll());
		return maw;
	}

	@GetMapping("/{id}")
	private ModelAndView one(@PathVariable("id") Long id) {
		ModelAndView maw = new ModelAndView();
		maw.setViewName("fragments/base");
		maw.addObject("titulo", "Detalle del Publicacion #" + id);
		maw.addObject("vista", "publicaciones/ver");
		maw.addObject("publicacion", publicacionServicio.getById(id));
		maw.addObject("_plantas", plantaServicio.getAll());
		return maw;
	}

	@GetMapping("/crear")
	public ModelAndView crear(Publicacion publicacion, HttpServletRequest request) {
			Principal principal = request.getUserPrincipal();
		publicacion.setUsuarioId((Usuario) usuarioServicio.findByEmail(principal.getName()));

		ModelAndView maw = new ModelAndView();
		maw.setViewName("fragments/base");
		maw.addObject("titulo", "Crear Publicacion");
		maw.addObject("vista", "publicaciones/crear");
		maw.addObject("publicacion", publicacion);
		maw.addObject("_plantas", plantaServicio.getAll());
		return maw;
	}

	@PostMapping("/crear")
	public ModelAndView guardar(@RequestParam(value="archivo", required=false) MultipartFile archivo, 
		HttpServletRequest request, 
		@Valid Publicacion publicacion,
		BindingResult br, RedirectAttributes ra) {
		
		if (br.hasErrors()) {
			return this.crear(publicacion, request);
		}

		publicacionServicio.save(publicacion);

		ModelAndView maw = this.index_AndView();

		if (!archivo.isEmpty()) {
	
			String tipo = archivo.getContentType();
			String extension = "." + tipo.substring(tipo.indexOf('/') + 1, tipo.length());
			String foto = publicacion.getId() + extension;
			String path = Paths.get("src/main/resources/static/images/publicaciones", foto).toAbsolutePath().toString();

			try {
				archivo.transferTo(new File(path));
			} catch (Exception e) {
				maw.addObject("error", "No se pudo guardar la imagen");
				return maw;
			}

			publicacion.setFoto(foto);
			publicacionServicio.save(publicacion);
		}
		
		maw.addObject("exito", "Publicacion guardado exitosamente");
		return maw;
	}

	@GetMapping("/editar/{id}")
	public ModelAndView editar(@PathVariable("id") Long id, Publicacion publicacion) {
		return this.editar(id, publicacion, true);
	}

	public ModelAndView editar(@PathVariable("id") Long id, Publicacion publicacion, boolean estaPersistido) {
		ModelAndView maw = new ModelAndView();
		maw.setViewName("fragments/base");
		maw.addObject("titulo", "Editar Publicacion");
		maw.addObject("vista", "publicaciones/editar");
		maw.addObject("_plantas", plantaServicio.getAll());

		if (estaPersistido)
			maw.addObject("publicacion", publicacionServicio.getById(id));
		else
			publicacion.setFoto(publicacionServicio.getById(id).getFoto());

		return maw;
	}

	@PutMapping("/editar/{id}")
	private ModelAndView update_AndView(@PathVariable("id") Long id,
			@RequestParam(value = "archivo", required = false) MultipartFile archivo,
			@Valid Publicacion publicacion, BindingResult br, RedirectAttributes ra) {
		if (br.hasErrors()) {
			return this.editar(id, publicacion, false);
		}

		ModelAndView maw = this.index_AndView();

		if (!archivo.isEmpty()) {
			String tipo = archivo.getContentType();
			String extension = "." + tipo.substring(tipo.indexOf('/') + 1, tipo.length());
			String foto = publicacion.getId() + extension;
			String path = Paths.get("src/main/resources/static/images/publicaciones", foto).toAbsolutePath().toString();

			try {
				archivo.transferTo(new File(path));
			} catch (Exception e) {
				maw.addObject("error", "No se pudo guardar la imagen");
				return maw;
			}

			publicacion.setFoto(foto);
		}

		publicacionServicio.save(publicacion);
		maw.addObject("exito", "Publicacion editado exitosamente");
		return maw;
	}

	@DeleteMapping("/delete/{id}")
	private ModelAndView delete_AndView(@PathVariable("id") Long id) {
		publicacionServicio.delete(id);
		ModelAndView maw = this.index_AndView();
		maw.addObject("exito", "Publicacion borrado exitosamente");
		return maw;
	}

}