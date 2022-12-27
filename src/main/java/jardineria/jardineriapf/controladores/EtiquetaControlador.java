package jardineria.jardineriapf.controladores;

import jardineria.jardineriapf.entidades.*;
import jardineria.jardineriapf.servicios.*;
import java.io.*;
import java.nio.file.Paths;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@RestController
@RequestMapping("etiquetas")
public class EtiquetaControlador implements WebMvcConfigurer {

	@Autowired
    EtiquetaServicio EtiquetaServicio;

    
	@GetMapping
    private ModelAndView index()
    {
        ModelAndView maw = new ModelAndView();
        maw.setViewName("fragments/base");
        maw.addObject("titulo", "Listado de etiquetas");
        maw.addObject("vista", "etiquetas/index");
        maw.addObject("etiquetas", EtiquetaServicio.getAll());
        return maw;
    }

	@GetMapping("/{id}")
    private ModelAndView one(@PathVariable("id") Long id)
    {
        ModelAndView maw = new ModelAndView();
        maw.setViewName("fragments/base");
        maw.addObject("titulo", "Detalle del Etiqueta #" + id);
        maw.addObject("vista", "etiquetas/ver");
        maw.addObject("Etiqueta", EtiquetaServicio.getById(id));
        return maw;
    }

	@GetMapping("/crear")
	public ModelAndView crear(Etiqueta etiqueta)
    {
        ModelAndView maw = new ModelAndView();
        maw.setViewName("fragments/base");
        maw.addObject("titulo", "Crear Etiqueta");
        maw.addObject("vista", "etiquetas/crear");
        return maw;
	}

	@PostMapping("/crear")
	public ModelAndView guardar(@RequestParam("archivo") MultipartFile archivo, @Valid Etiqueta etiqueta, BindingResult br, RedirectAttributes ra)
    {
        if ( archivo.isEmpty() )
			br.reject("archivo", "Por favor, cargue una imagen"); 

		if ( br.hasErrors() ) {
			return this.crear(etiqueta);
		}

		EtiquetaServicio.save(etiqueta);

        String tipo = archivo.getContentType();
        String extension = "." + tipo.substring(tipo.indexOf('/') + 1, tipo.length());
        String foto = etiqueta.getId() + extension;
        String path = Paths.get("src/main/resources/static/images/eticketas", foto).toAbsolutePath().toString();
        ModelAndView maw = this.index();

        try {
            archivo.transferTo( new File(path) );
        } catch (Exception e) {
            maw.addObject("error", "No se pudo guardar la imagen");
            return maw;
        }

        // etiqueta.setFoto(foto);
        EtiquetaServicio.save(etiqueta);
        maw.addObject("exito", "Etiqueta guardado exitosamente");
		return maw;
	}

	@GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable("id") Long id, Etiqueta etiqueta)
    {
        return this.editar(id, etiqueta, true);
    }

    public ModelAndView editar(@PathVariable("id") Long id, Etiqueta etiqueta, boolean estaPersistido)
    {
        ModelAndView maw = new ModelAndView();
        maw.setViewName("fragments/base");
        maw.addObject("titulo", "Editar Etiqueta");
        maw.addObject("vista", "etiquetas/editar");

        if (estaPersistido)
            maw.addObject("Etiqueta", EtiquetaServicio.getById(id));
        // else
            // etiqueta.setFoto( EtiquetaServicio.getById(id).getFoto() );

        return maw;
    }

    @PutMapping("/editar/{id}")
    private ModelAndView update(@PathVariable("id") Long id,
    @RequestParam(value = "archivo", required = false) MultipartFile archivo,
    @Valid Etiqueta etiqueta, BindingResult br, RedirectAttributes ra)
    {
        if ( br.hasErrors() ) {
			return this.editar(id, etiqueta, false);
		}

        Etiqueta registro = EtiquetaServicio.getById(id);
        ModelAndView maw = this.index();

        if ( ! archivo.isEmpty() ) {
            String tipo = archivo.getContentType();
            String extension = "." + tipo.substring(tipo.indexOf('/') + 1, tipo.length());
            String foto = etiqueta.getId() + extension;
            String path = Paths.get("src/main/resources/static/images/eticketas", foto).toAbsolutePath().toString();

            try {
                archivo.transferTo( new File(path) );
            } catch (Exception e) {
                maw.addObject("error", "No se pudo guardar la imagen");
                return maw;
            }

            // registro.setFoto(foto);
        }

        EtiquetaServicio.save(registro);
        maw.addObject("exito", "Etiqueta editado exitosamente");
		return maw;
    }

    @DeleteMapping("/{id}")
    private ModelAndView delete(@PathVariable("id") Long id)
    {
        EtiquetaServicio.delete(id);
        ModelAndView maw = this.index();
        maw.addObject("exito", "Etiqueta borrado exitosamente");
		return maw;
    }
    
}