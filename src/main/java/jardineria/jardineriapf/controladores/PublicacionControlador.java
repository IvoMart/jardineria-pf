package jardineria.jardineriapf.controladores;

import jardineria.jardineriapf.entidades.*;
import jardineria.jardineriapf.servicios.*;
import java.io.*;
import java.nio.file.Paths;
import java.util.List;

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
	PublicacionServicio PublicacionServicio;

	@GetMapping
	private List<Publicacion> index() {
		return PublicacionServicio.findAll();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	private Publicacion create(@RequestBody Publicacion publicacion) {
		return PublicacionServicio.save_Publicacion(publicacion);
	}

	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	private Publicacion update(@PathVariable Long id, @RequestBody Publicacion publicacion) {
		return PublicacionServicio.update_Publicacion(id, publicacion);
	}

	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	private Publicacion delete(@PathVariable Long id) {
		return PublicacionServicio.delete_Publicacion(id);
	}

	@GetMapping("/home")
	private ModelAndView index_AndView() {
		ModelAndView maw = new ModelAndView();
		maw.setViewName("fragments/base");
		maw.addObject("titulo", "Listado de Publicaciones");
		maw.addObject("vista", "Publicaciones/index");
		maw.addObject("Publicaciones", PublicacionServicio.getAll());
		return maw;
	}

	@GetMapping("/{id}")
	private ModelAndView one(@PathVariable("id") Long id) {
		ModelAndView maw = new ModelAndView();
		maw.setViewName("fragments/base");
		maw.addObject("titulo", "Detalle del Publicacion #" + id);
		maw.addObject("vista", "Publicaciones/ver");
		maw.addObject("Publicacion", PublicacionServicio.getById(id));
		return maw;
	}

	@GetMapping("/crear")
	public ModelAndView crear(Publicacion Publicacion) {
		ModelAndView maw = new ModelAndView();
		maw.setViewName("fragments/base");
		maw.addObject("titulo", "Crear Publicacion");
		maw.addObject("vista", "Publicaciones/crear");
		return maw;
	}

	@PostMapping("/crear")
	public ModelAndView guardar(@RequestParam("archivo") MultipartFile archivo, @Valid Publicacion Publicacion,
			BindingResult br, RedirectAttributes ra) {
		if (archivo.isEmpty())
			br.reject("archivo", "Por favor, cargue una imagen");

		if (br.hasErrors()) {
			return this.crear(Publicacion);
		}

		PublicacionServicio.save(Publicacion);

		String tipo = archivo.getContentType();
		String extension = "." + tipo.substring(tipo.indexOf('/') + 1, tipo.length());
		String foto = Publicacion.getId() + extension;
		String path = Paths.get("src/main/resources/static/images/publicaciones", foto).toAbsolutePath().toString();
		ModelAndView maw = this.index_AndView();

		try {
			archivo.transferTo(new File(path));
		} catch (Exception e) {
			maw.addObject("error", "No se pudo guardar la imagen");
			return maw;
		}

		Publicacion.setFoto(foto);
		PublicacionServicio.save(Publicacion);
		maw.addObject("exito", "Publicacion guardado exitosamente");
		return maw;
	}

	@GetMapping("/editar/{id}")
	public ModelAndView editar(@PathVariable("id") Long id, Publicacion Publicacion) {
		return this.editar(id, Publicacion, true);
	}

	public ModelAndView editar(@PathVariable("id") Long id, Publicacion Publicacion, boolean estaPersistido) {
		ModelAndView maw = new ModelAndView();
		maw.setViewName("fragments/base");
		maw.addObject("titulo", "Editar Publicacion");
		maw.addObject("vista", "Publicaciones/editar");

		if (estaPersistido)
			maw.addObject("Publicacion", PublicacionServicio.getById(id));
		else
			Publicacion.setFoto(PublicacionServicio.getById(id).getFoto());

		return maw;
	}

	@PutMapping("/editar/{id}")
	private ModelAndView update_AndView(@PathVariable("id") Long id,
			@RequestParam(value = "archivo", required = false) MultipartFile archivo,
			@Valid Publicacion Publicacion, BindingResult br, RedirectAttributes ra) {
		if (br.hasErrors()) {
			return this.editar(id, Publicacion, false);
		}

		Publicacion registro = PublicacionServicio.getById(id);
		ModelAndView maw = this.index_AndView();

		if (!archivo.isEmpty()) {
			String tipo = archivo.getContentType();
			String extension = "." + tipo.substring(tipo.indexOf('/') + 1, tipo.length());
			String foto = Publicacion.getId() + extension;
			String path = Paths.get("src/main/resources/static/images/publicaciones", foto).toAbsolutePath().toString();

			try {
				archivo.transferTo(new File(path));
			} catch (Exception e) {
				maw.addObject("error", "No se pudo guardar la imagen");
				return maw;
			}

			registro.setFoto(foto);
		}

		PublicacionServicio.save(registro);
		maw.addObject("exito", "Publicacion editado exitosamente");
		return maw;
	}

	@DeleteMapping("/delete/{id}")
	private ModelAndView delete_AndView(@PathVariable("id") Long id) {
		PublicacionServicio.delete(id);
		ModelAndView maw = this.index_AndView();
		maw.addObject("exito", "Publicacion borrado exitosamente");
		return maw;
	}

}