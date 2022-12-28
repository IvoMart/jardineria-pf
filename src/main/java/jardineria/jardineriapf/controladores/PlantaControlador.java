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
@RequestMapping("plantas")
public class PlantaControlador implements WebMvcConfigurer {

	@Autowired
	PlantaServicio plantaServicio;
	
	// @GetMapping
	// private List<Planta> index() {
	// 	return plantaServicio.findAll();
	// }

	// @PostMapping
	// @ResponseStatus(HttpStatus.CREATED)
	// private Planta create(@RequestBody Planta planta) {
	// 	return plantaServicio.save_Planta(planta);
	// }

	// @PutMapping("{id}")
	// @ResponseStatus(HttpStatus.OK)
	// private Planta update(@PathVariable Long id, @RequestBody Planta planta) {
	// 	return plantaServicio.update_Planta(id, planta);
	// }

	// @DeleteMapping("{id}")
	// @ResponseStatus(HttpStatus.NO_CONTENT)
	// private Planta delete(@PathVariable Long id) {
	// 	return plantaServicio.delete_Planta(id);
	// }

	@GetMapping("/home")
	private ModelAndView index_AndView() {
		ModelAndView maw = new ModelAndView();
		maw.setViewName("fragments/base");
		maw.addObject("titulo", "Listado de plantas");
		maw.addObject("vista", "plantas/index");
		maw.addObject("plantas", plantaServicio.getAll());
		return maw;
	}

	@GetMapping("/{id}")
	private ModelAndView one(@PathVariable("id") Long id) {
		ModelAndView maw = new ModelAndView();
		maw.setViewName("fragments/base");
		maw.addObject("titulo", "Detalle del Planta #" + id);
		maw.addObject("vista", "plantas/ver");
		maw.addObject("planta", plantaServicio.getById(id));
		return maw;
	}

	@GetMapping("/crear")
	public ModelAndView crear(Planta Planta) {
		ModelAndView maw = new ModelAndView();
		maw.setViewName("fragments/base");
		maw.addObject("titulo", "Crear Planta");
		maw.addObject("vista", "plantas/crear");
		return maw;
	}

	@PostMapping("/crear")
	public ModelAndView guardar(
		@RequestParam(value="archivo", required=false) MultipartFile archivo, 
		@Valid Planta planta,
		BindingResult br, 
		RedirectAttributes ra) 
	{

		// if (archivo.isEmpty())
		// 	br.reject("archivo", "Por favor, cargue una imagen");

		if (br.hasErrors()) {
			return this.crear(planta);
		}

		ModelAndView maw = this.index_AndView();
		plantaServicio.save(planta);
		if (!archivo.isEmpty()) {
	
			String tipo = archivo.getContentType();
			String extension = "." + tipo.substring(tipo.indexOf('/') + 1, tipo.length());
			String foto = planta.getId() + extension;
			String path = Paths.get("src/main/resources/static/images/plantas", foto).toAbsolutePath().toString();

			try {
				archivo.transferTo(new File(path));
			} catch (Exception e) {
				maw.addObject("error", "No se pudo guardar la imagen");
				return maw;
			}

			planta.setFoto(foto);
			plantaServicio.save(planta);
		}
		maw.addObject("exito", "Planta guardado exitosamente");
		return maw;
	}

	@GetMapping("/editar/{id}")
	public ModelAndView editar(@PathVariable("id") Long id, Planta planta) {
		return this.editar(id, planta, true);
	}

	public ModelAndView editar(@PathVariable("id") Long id, Planta planta, boolean estaPersistido) {
		ModelAndView maw = new ModelAndView();
		maw.setViewName("fragments/base");
		maw.addObject("titulo", "Editar Planta");
		maw.addObject("vista", "plantas/editar");

		if (estaPersistido)
			maw.addObject("planta", plantaServicio.getById(id));
		else
			planta.setFoto(plantaServicio.getById(id).getFoto());

		return maw;
	}

	@PutMapping("/editar/{id}")
	private ModelAndView update_AndView(@PathVariable("id") Long id,
			@RequestParam(value = "archivo", required = false) MultipartFile archivo,
			@Valid Planta planta, BindingResult br, RedirectAttributes ra) {
		if (br.hasErrors()) {
			return this.editar(id, planta, false);
		}

		Planta registro = plantaServicio.getById(id);
		ModelAndView maw = this.index_AndView();

		if (!archivo.isEmpty()) {
			String tipo = archivo.getContentType();
			String extension = "." + tipo.substring(tipo.indexOf('/') + 1, tipo.length());
			String foto = planta.getId() + extension;
			String path = Paths.get("src/main/resources/static/images/plantas", foto).toAbsolutePath().toString();

			try {
				archivo.transferTo(new File(path));
			} catch (Exception e) {
				maw.addObject("error", "No se pudo guardar la imagen");
				return maw;
			}

			registro.setFoto(foto);
		}

		plantaServicio.save(registro);
		maw.addObject("exito", "Planta editada exitosamente");
		return maw;
	}

	@DeleteMapping("/delete/{id}")
	private ModelAndView delete_AndView(@PathVariable("id") Long id) {
		plantaServicio.delete(id);
		ModelAndView maw = this.index_AndView();
		maw.addObject("exito", "Planta borrada exitosamente");
		return maw;
	}

}