package jardineria.jardineriapf.controladores;

import jardineria.jardineriapf.dto.RecuperacionDto;
import jardineria.jardineriapf.entidades.Recuperacion;
import jardineria.jardineriapf.servicios.*;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("recuperacion")
public class RecuperacionControlador {
   
    @Autowired
    UsuarioServicio usuarioServicio;

    @Autowired
    RecuperacionServicio recuperacionServicio;

    @Autowired
    EmailServicio emailServicio;

    @Autowired
    AuthControlador authControlador;

    @GetMapping
    private ModelAndView index(){ //inicio

        ModelAndView maw = new ModelAndView();
        maw.setViewName("fragments/base");
        maw.addObject("titulo", "Lista de Recuperaciones");
        maw.addObject("vista", "recuperacion/index");
        maw.addObject("recuperacion", recuperacionServicio.getAll());
        return maw;

    }

    @GetMapping("/crear")
    public ModelAndView crear(Recuperacion recuperacion){
        ModelAndView maw = new ModelAndView();
        maw.setViewName("fragments/base");
        maw.addObject("titulo", "Crear Usuario");
        maw.addObject("vista", "recuperacion/crear");
        maw.addObject("recuperacion", recuperacionServicio.getAll());
        return maw;
    }

    @PostMapping("/crear")
	public ModelAndView guardar(@Valid Recuperacion recuperacion, BindingResult br){
		if ( br.hasErrors() ){
			return this.crear(recuperacion);
		}

        recuperacionServicio.save(recuperacion); 
		return null; //vuelvo al index
	}

    @DeleteMapping("/{id}")
    private ModelAndView delete(@PathVariable("id") Long id) {
        recuperacionServicio.delete(id);
        ModelAndView maw = this.index();
        maw.addObject("exito", "Mensaje borrado exitosamente");
        return maw;
    }
}
